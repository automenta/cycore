/* For LarKC */
package subl.type.symbol;

import subl.Errors;
import subl.type.core.FromSubLisp;
import subl.type.core.SubLObject;

import java.util.Iterator;

public class SubLPackageIterator extends FromSubLisp implements Iterator {
	SubLPackageIterator(SubLPackage thePackage) {
		iter = thePackage.getLocalSymbols().iterator();
	}

	private Iterator iter;
	private SubLObject entry;
	public static String PACKAGE_ITERATOR_TYPE_NAME = "PACKAGE-ITERATOR";

	@Override
	public boolean canFastHash() {
		return true;
	}

	public void clear() {
		iter = null;
		entry = null;
	}

	public SubLObject getCurrentKey() {
		return entry;
	}

	public SubLObject getCurrentValue() {
		return entry;
	}

	@Override
	public int hashCode(int currentDepth) {
		if (currentDepth < 8)
			return iter.hashCode();
		return 0;
	}

	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	@Override
	public boolean isPackageIterator() {
		return true;
	}

	@Override
	public Object next() {
		if (iter.hasNext())
			entry = (SubLObject) iter.next();
		else
			entry = null;
		return entry;
	}

	@Override
	public void remove() {
		Errors.error("Remove on a package iterator is not supported.");
	}

	@Override
	public SubLPackageIterator toPackageIterator() {
		return this;
	}

//	@Override
//	public String toString() {
//		return "#<" + toTypeName() + " @ " + super.toString() + ">";
//	}

	@Override
	public String toTypeName() {
		return "PACKAGE-ITERATOR";
	}
}
