/* For LarKC */
package subl.type.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SubLKeyhashIterator extends FromSubLisp {
	SubLKeyhashIterator(SubLHashtable hashTable) {
		iter = hashTable.getEntrySetIterator();
		next();
	}

	private Iterator iter;
	private Map.Entry entry;
	public static String HASHTABLE_ITERATOR_TYPE_NAME = "HASHTABLE-ITERATOR";

	@Override
	public boolean canFastHash() {
		return true;
	}

	public void clear() {
		iter = null;
		entry = null;
	}

	public SubLObject getCurrentKey() {
		return (SubLObject) entry.getKey();
	}

	public SubLObject getCurrentValue() {
		return (SubLObject) entry.getValue();
	}

	@Override
	public int hashCode(int currentDepth) {
		if (currentDepth < 8)
			return iter.hashCode();
		return 0;
	}

	public boolean hasNext() {
		return entry != null;
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	@Override
	public boolean isHashtableIterator() {
		return true;
	}

	@Override
	public boolean isKeyhashIterator() {
		return true;
	}

	public Object next() {
		if (iter.hasNext())
			entry = (Entry) iter.next();
		else
			entry = null;
		return entry;
	}

	@Override
	public SubLKeyhashIterator toKeyhashIterator() {
		return this;
	}
//
//	@Override
//	public String printObject() {
//		return "#<" + toTypeName() + " @ " + super.toString() + ">";
//	}

	@Override
	public String toTypeName() {
		return "HASHTABLE-ITERATOR";
	}
}
