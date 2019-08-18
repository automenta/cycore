
/* For LarKC */
package subl.type.core;

import subl.type.core.SubLHashtable.SubLHashtableKeyEntry;
import subl.type.symbol.SubLSymbol;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SubLHashtableIterator extends FromSubLisp implements Iterator<Map.Entry<SubLObject, SubLObject>> {
	final boolean advancedToNext;

	SubLHashtableIterator(SubLHashtable hashTable, boolean advanceToNext) {
		iter = hashTable.getKeyEntrySetIterator();
		advancedToNext = advanceToNext;
		if (advanceToNext)
			next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see subl.type.core.AbstractSubLObject#getType()
	 */
	@Override
	public SubLSymbol getType() {
		return PACKAGE_SUBLISP.intern(toTypeName());
	}

	private Iterator<Map.Entry<SubLHashtableKeyEntry, SubLObject>> iter;
	private Map.Entry<SubLHashtableKeyEntry, SubLObject> entry;
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
		if (entry == null && !advancedToNext)
			next();
		return SubLHashtable.unwrap(entry.getKey());
	}

	public SubLObject getCurrentValue() {
		if (entry == null && !advancedToNext)
			next();
		return (SubLObject) entry.getValue();
	}

	@Override
	public int hashCode(int currentDepth) {
		if (currentDepth < 8)
			return iter.hashCode();
		return 0;
	}

	public boolean hasNext() {
		if (!advancedToNext)
			return iter.hasNext();
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

	public Entry<SubLObject, SubLObject> next() {
		if (iter.hasNext()) {
			final Entry preventry = entry = iter.next();
			return new Entry<SubLObject, SubLObject>() {
				@Override
				public SubLObject setValue(SubLObject p0) {
					return (SubLObject) preventry.setValue(p0);
				}

				@Override
				public SubLObject getValue() {
					return (SubLObject) preventry.getValue();
				}

				@Override
				public SubLObject getKey() {
					return SubLHashtable.unwrap(preventry.getKey());
				}
			};
		} else {
			entry = null;
			return null;
		}

	}

	@Override
	public SubLHashtableIterator toHashtableIterator() {
		return this;
	}
//
//	@Override
//	public String toString() {
//		return "#<" + toTypeName() + " @ " + super.toString() + ">";
//	}

	@Override
	public String toTypeName() {
		return "HASHTABLE-ITERATOR";
	}
}
