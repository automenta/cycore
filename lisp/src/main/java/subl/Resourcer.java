/* For LarKC */
package subl;

import subl.type.core.SubLHashtable;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.core.SubLProcess;
import subl.util.ObjectPool;

import java.util.ArrayList;
import java.util.Arrays;

public class Resourcer extends RuntimeException {
	private static class ArrayListPool extends ObjectPool {
		ArrayListPool() {
			super(16);
		}

		public static int DEFAULT_SIZE = 8192;

		@Override
		public Object makePoolItem() {
			return new ArrayList(8192);
		}

		@Override
		public void resetPoolItem(Object item) {
			((ArrayList) item).clear();
		}
	}

	private static class HashtableKeyEntryPool extends ObjectPool {
		HashtableKeyEntryPool() {
			super(16);
		}

		@Override
		public Object makePoolItem() {
			return new SubLHashtable.SubLHashtableKeyEntryImpl();
		}

		@Override
		public void resetPoolItem(Object item) {
			((SubLHashtable.SubLHashtableKeyEntry) item).clear();
		}
	}

	private static class StringBuilderPool extends ObjectPool {
		StringBuilderPool(int size) {
			super(16);
		}

		public static int DEFAULT_SIZE = 8192;

		@Override
		public Object makePoolItem() {
			return new StringBuilder(8192);
		}

		@Override
		public void resetPoolItem(Object item) {
			((StringBuilder) item).setLength(0);
		}
	}

	private static class SubLArrayListListIteratorObjectPool extends ObjectPool {
		@Override
		public Object makePoolItem() {
			return new SubLArrayListListIterator();
		}

		@Override
		public void resetPoolItem(Object item) {
			((SubLListListIterator) item).reset();
		}
	}

	private static class SubLConsListListIteratorObjectPool extends ObjectPool {
		@Override
		public Object makePoolItem() {
			return new SubLConsListListIterator();
		}

		@Override
		public void resetPoolItem(Object item) {
			((SubLListListIterator) item).reset();
		}
	}

	static class ObjectArrayObjectPool extends ObjectPool {
		ObjectArrayObjectPool(int size) {
			super(32);
			arraySize = size;
		}

		private int arraySize;
		public static int MAX_ARRAY_SIZE = 24;

		@Override
		public Object makePoolItem() {
			return new Object[arraySize];
		}

		@Override
		public void resetPoolItem(Object item) {
			Arrays.fill((Object[]) item, null);
		}
	}

	static class SubLObjectArrayObjectPool extends ObjectPool {
		SubLObjectArrayObjectPool(int size) {
			super(32);
			arraySize = size;
		}

		private int arraySize;
		public static int MAX_ARRAY_SIZE = 24;

		@Override
		public Object makePoolItem() {
			return new SubLObject[arraySize];
		}

		@Override
		public void resetPoolItem(Object item) {
			Arrays.fill((Object[]) item, null);
		}
	}

	public Resourcer() {
//		sublArrayListListIteratorPool = new SubLArrayListListIteratorObjectPool().init();
//		sublConsListListIteratorPool = new SubLConsListListIteratorObjectPool().init();
		hashtableKeyEntryPool = new HashtableKeyEntryPool().init();
		objectArrayObjectPools = new ObjectArrayObjectPool[24];
		sublObjectArrayObjectPools = new SubLObjectArrayObjectPool[24];
		for (int i = 0, size = 24; i < size; ++i)
			objectArrayObjectPools[i] = new ObjectArrayObjectPool(i).init();
		for (int i = 0, size = 24; i < size; ++i)
			sublObjectArrayObjectPools[i] = new SubLObjectArrayObjectPool(i).init();
	}

	public static Resourcer getInstance() {
		return SubLProcess.currentSubLThread().getResourcer();
	}

//	private ObjectPool sublArrayListListIteratorPool;
	private ObjectPool sublConsListListIteratorPool;
	private ObjectPool hashtableKeyEntryPool;
	private ObjectPool[] objectArrayObjectPools;
	private ObjectPool[] sublObjectArrayObjectPools;

	public static final SubLObject[] EmptySublObjectArray = new SubLObject[0];

	public SubLHashtable.SubLHashtableKeyEntryImpl acquireHashtableKeyEntry() {
		SubLHashtable.SubLHashtableKeyEntryImpl keyEntry = (SubLHashtable.SubLHashtableKeyEntryImpl) hashtableKeyEntryPool
				.acquire();
		return keyEntry;
	}

	public Object[] acquireObjectArray(int size) {
		if (size >= 24)
			return new Object[size];
		return (Object[]) objectArrayObjectPools[size].acquire();
	}

	public static SubLListListIterator acquireSubLListListIterator(SubLList list) {
		return Resourcer.acquireSubLListListIterator(list, 0, -1);
	}

	public static SubLListListIterator acquireSubLListListIterator(SubLList list, int start) {
		return Resourcer.acquireSubLListListIterator(list, start, list == null ? 0 : -1);
	}

	public static SubLListListIterator acquireSubLListListIterator(SubLList list, int start, int end) {
		SubLListListIterator iter;// = null;
		if (list.isArrayBased())
			iter = new SubLArrayListListIterator(); // (SubLListListIterator) sublArrayListListIteratorPool.acquire();
		else
			iter = new SubLConsListListIterator(); //(SubLListListIterator) sublConsListListIteratorPool.acquire();
		iter.init(list, start, end);
		return iter;
	}

	public SubLObject[] acquireSubLObjectArray(ArrayList<SubLObject> list) {
		if (list == null || list.size() <= 0)
			return Resourcer.EmptySublObjectArray;
		int size = list.size();
		SubLObject[] result;
		if (size >= 24)
			result = new SubLObject[size];
		else
			result = (SubLObject[]) sublObjectArrayObjectPools[size].acquire();
		return list.toArray(result);
	}

	public SubLObject[] acquireSubLObjectArray(int size) {
		if (size <= 0)
			return Resourcer.EmptySublObjectArray;
		if (size >= 24)
			return new SubLObject[size];
		return (SubLObject[]) sublObjectArrayObjectPools[size].acquire();
	}

	public SubLObject[] acquireSubLObjectArray(SubLList list) {
		if (list == null || list.size() <= 0)
			return Resourcer.EmptySublObjectArray;
		int size = list.size();
		SubLObject[] result;
		if (size >= 24)
			result = new SubLObject[size];
		else
			result = (SubLObject[]) sublObjectArrayObjectPools[size].acquire();
		return (SubLObject[]) list.toArray(result);
	}

	public void releaseHashtableKeyEntry(SubLHashtable.SubLHashtableKeyEntry keyEntry) {
		if (keyEntry != null)
			hashtableKeyEntryPool.release(keyEntry);
	}

	public void releaseObjectArray(Object[] array) {
		if (array == null)
			return;
		if (array.length >= 24)
			return;
		objectArrayObjectPools[array.length].release(array);
	}

	@Deprecated public void releaseSubLListListIterator(SubLListListIterator iter) {
//		if (iter == null)
//			return;
//		if (iter.isArrayBased())
//			sublArrayListListIteratorPool.release(iter);
//		else
//			sublConsListListIteratorPool.release(iter);
	}

	public void releaseSubLObjectArray(SubLObject[] array) {
		if (array == null)
			return;
		if (array.length == 0)
			return;
		if (array.length >= 24)
			return;
		sublObjectArrayObjectPools[array.length].release(array);
	}
}
