package subl;

import subl.type.core.SubLObject;
import subl.type.core.SubLStruct;
import subl.util.SubLFile;
import subl.util.SubLFiles;
import subl.util.SubLTrampolineFile;

import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.Map.Entry;

public class DiskDumper extends SubLTrampolineFile
{

	volatile public static Map bowls = new HashMap();
	volatile public static Map<Object, LinkedList<WeakReference<Object>>> mapClass2Refs = new HashMap<>();
	public static SubLFile me = new DiskDumper();
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

	public static SubLObject load_image_file(SubLObject filename)
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

	public static SubLObject save_image_file(SubLObject filename)
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

	@Override
	public void declareFunctions()
	{
		SubLFiles.declareFunction(me, "save_image_file", "SAVE-IMAGE-FILE", 1, 0, false);
		SubLFiles.declareFunction(me, "load_image_file", "LOAD-IMAGE-FILE", 1, 0, false);
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
			toDiskTypes.add("ASSERTION");
			toDiskTypes.add("NART");
			toDiskTypes.add("DEDUCTION");
		}

	}

}
