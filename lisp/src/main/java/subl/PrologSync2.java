package subl;

import org.jpl7.Compound;
import org.jpl7.Term;
import subl.type.core.SubLObject;
import subl.type.core.SubLStruct;
import subl.type.symbol.SubLSymbol;
import subl.util.SubLFile;
import subl.util.SubLFiles;
import subl.util.SubLTrampolineFile;

import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.Map.Entry;

public class PrologSync2 extends SubLTrampolineFile
{

	public static final Map bowls = new HashMap();
	public static final Map<Object, LinkedList<WeakReference<Object>>> mapClass2Refs = new HashMap<>();
	public static final SubLFile me = new PrologSync();
	volatile public static Map<String, Map> sublbowls = new HashMap<>();
	volatile public static Map<String, SubLFile> sublRefs = new HashMap<>();
	static volatile Set<String> toDiskTypes = new HashSet<>();

	public static void addStaticObject(SubLFile file)
	{
		sublRefs.put(file.getClass().getName(), file);
	}

	public static void addThis(SubLStruct abstractSubLStruct)
	{
		String typeOf = abstractSubLStruct.getStructDecl().getStructName().getName();
		if (!isSavedToDisk(typeOf))
			return;
		LinkedList<WeakReference<Object>> objStack;
		synchronized (mapClass2Refs)
		{
			objStack = mapClass2Refs.computeIfAbsent(typeOf, k -> new LinkedList<>());
		}
		synchronized (objStack)
		{
			objStack.add(new WeakReference<Object>(abstractSubLStruct)
			{
				@Override
				public String toString()
				{
					return String.valueOf(get());
				}
			});
			if ((objStack.size() % 1000) == 2)
			{
				// cleanRefs();
			}
		}
	}

	public static void addThis(SubLSymbol structName, SubLObject id, SubLObject kb_object_content)
	{
		sync_println("ADD-" + structName + " id =" + id + ": " + kb_object_content);
	}

	public static void copyBowlsToLiveMem() throws IllegalArgumentException, IllegalAccessException
	{
		sublbowls = (Map<String, Map>) bowls.get("SUBLFILES");
		synchronized (sublRefs)
		{
			for (Entry<String, SubLFile> fileKV : sublRefs.entrySet())
			{
				SubLFile file = fileKV.getValue();
				Map fieldMap = sublbowls.get(fileKV.getKey());
				for (Field f : file.getClass().getFields())
				{
					int mod = f.getModifiers();
					if (!Modifier.isVolatile(mod))
					{
						boolean wasAccessable = f.isAccessible();
						if (!wasAccessable)
						{
							f.setAccessible(true);
						}
						String fname = f.getName();
						if (fname.equals("me"))
							continue;

						f.set(Modifier.isStatic(mod) ? null : file, fieldMap.get(fname));
						if (!wasAccessable)
							f.setAccessible(false);
					}
				}
			}
		}
	}

	public static void copyRefsToBowls() throws IllegalArgumentException, IllegalAccessException
	{
		Object[] keys = null;

		synchronized (sublRefs)
		{
			for (Entry<String, SubLFile> fileKV : sublRefs.entrySet())
			{
				Map fieldMap = new HashMap();
				SubLFile file = fileKV.getValue();
				sublbowls.put(fileKV.getKey(), fieldMap);
				for (Field f : file.getClass().getFields())
				{
					int mod = f.getModifiers();
					if (!Modifier.isVolatile(mod))
					{
						boolean wasAccessable = f.isAccessible();
						if (!wasAccessable)
						{
							f.setAccessible(true);
						}
						String fname = f.getName();
						if (fname.equals("me"))
							continue;
						fieldMap.put(fname, f.get(Modifier.isStatic(mod) ? null : file));
						if (!wasAccessable)
							f.setAccessible(false);
					}
				}
			}
		}
		bowls.put("SUBLFILES", sublbowls);

		synchronized (mapClass2Refs)
		{
			keys = mapClass2Refs.keySet().toArray();
		}
		for (Object k : keys)
		{
			LinkedList<WeakReference<Object>> objStack = mapClass2Refs.get(k);
			LinkedList bowl = new LinkedList();
			bowls.put(k, bowl);
			ListIterator<WeakReference<Object>> iter = objStack.listIterator();
			while (iter.hasNext())
			{
				Object object = iter.next().get();
				if (object == null)
				{
					iter.remove();
				} else
				{
					bowl.add(object);
				}
			}
		}
	}

	public static void initializeTypes()
	{

	}

	private static boolean isSavedToDisk(String class1)
	{
		return toDiskTypes.contains(class1) || true;
	}

	public static SubLObject load_content_state(SubLObject filename)
	{
		ObjectInputStream objectinputstream = null;
		try
		{
			FileInputStream streamIn = new FileInputStream(filename.toStr().getString());
			objectinputstream = new ObjectInputStream(streamIn);
			Map readCase = (Map) objectinputstream.readObject();
			copyBowlsToLiveMem();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (objectinputstream != null)
			{
				try
				{
					objectinputstream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
		return NIL;
	}
	//// Initializers

	public static void remThis(SubLSymbol structName, SubLObject id)
	{
		sync_println("REM-" + structName + " id =" + id);

	}

	public static SubLObject save_content_state(SubLObject filename)
	{
		ObjectOutputStream oos = null;
		try
		{
			copyRefsToBowls();
			FileOutputStream fout = new FileOutputStream(filename.toStr().getString());
			oos = new ObjectOutputStream(fout);
			oos.writeObject(bowls);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (oos != null)
			{
				try
				{
					oos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return NIL;
	}

	private static void sync_println(String string)
	{
		System.out.println(string);

	}

	public static Term toProlog(String name, SubLStruct s)
	{
		int arity = s.toStruct().getFieldCount();
		Term[] args = new Term[arity];
		for (int i = 0; i < arity; i++)
		{
			SubLObject o = s.getField(i);
			args[i] = toProlog(o);
		}
		return new Compound(name, args);
	}

	public static Term toProlog(SubLObject o)
	{
		if (o instanceof IPrologifiable)
		{
			return ((IPrologifiable) o).toProlog();
		}
		return Term.objectToJRef(o);
	}

	public static void updThis(SubLSymbol structName, SubLObject id, SubLObject content)
	{
		sync_println("UPDATE-" + structName + " id =" + id + ": " + content);

	}

	@Override
	public void declareFunctions()
	{
		SubLFiles.declareFunction(me, "save_content_state", "SAVE-CONTENT-STATE", 0, 0, false);
		SubLFiles.declareFunction(me, "load_content_state", "LOAD-CONTENT-STATE", 0, 0, false);
	}

	@Override
	public void initializeVariables()
	{
	}

	@Override
	public void runTopLevelForms()
	{
	}

	static
	{
		synchronized (toDiskTypes)
		{
			toDiskTypes.add("CONSTANT");
			toDiskTypes.add("ASSERTION-CONTENT");
			toDiskTypes.add("NART");
			toDiskTypes.add("DEDUCTION");
		}

	}

	public interface IPrologifiable
	{
		Term toProlog();
	}

}
