/* For LarKC */
package subl.util;

import subl.BinaryFunction;
import subl.type.core.SubLObject;
import subl.type.symbol.SubLNil;

import java.util.Comparator;

public class ComparatorIdentityKey<T> implements Comparator<T> {
	public ComparatorIdentityKey(BinaryFunction pred) {
		this.pred = pred;
	}

	private final BinaryFunction pred;

	@Override
	public int compare(T o1, T o2) {
		if (o1==o2) return 0;
		SubLObject obj1 = (SubLObject) o1;
		SubLObject obj2 = (SubLObject) o2;
		boolean val1 = this.pred.processItem(obj1, obj2) == SubLNil.NIL;
		boolean val2 = this.pred.processItem(obj2, obj1) == SubLNil.NIL;
		return val1 ? val2 ? 0 : 1 : val2 ? -1 : 0;
	}

//	public ComparatorIdentityKey init(BinaryFunction pred) {
//		this.pred = pred;
//		return this;
//	}

	@Override
	public String toString() {
		return "Comparator: " + this.pred + " with key: IDENTITY";
	}
}
