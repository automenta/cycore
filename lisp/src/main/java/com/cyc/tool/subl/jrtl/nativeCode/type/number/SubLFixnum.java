/* For LarKC */
package com.cyc.tool.subl.jrtl.nativeCode.type.number;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.CommonSymbols;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import org.armedbear.lisp.Fixnum;

public class SubLFixnum extends Fixnum implements SubLBignum, SubLInteger, SubLNumber, SubLObject {
	public SubLFixnum(int theInteger) {
		super(theInteger);
	}

	public SubLFixnum(Integer theInteger) {
		super(theInteger);
	}

	public static String FIXNUM_TYPE_NAME = "FIXNUM";

	@Override
	public SubLObject add(SubLObject num) {
		if(num==Fixnum.ONE) return inc();
		if (num.isFixnum()) {
			int other = num.intValue();
			return add(other);
			//return SubLNumberFactory.makeInteger(value + other);

		}
		return num.add(this);
	}

	@Override
	public boolean eql(SubLObject obj) {
		return this == obj;
	}

	@Override
	public boolean equal(SubLObject obj) {
		return obj.isInteger() && this.longValue() == obj.longValue();
	}

	@Override
	public int getNumSize() {
		return 0;
	}

	@Override
	public boolean greaterThanInternal(SubLObject num) {
		return value > num.intValue();
	}

	@Override
	public boolean greaterThanOrEqualInternal(SubLObject num) {
		return value >= num.intValue();
	}

	@Override
	public int hashCode(int currentDepth) {
		return value;
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	@Override
	public boolean isFixnum() {
		return true;
	}

	@Override
	public boolean isInteger() {
		return true;
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public boolean lessThanInternal(SubLObject num) {
		return value < num.intValue();
	}

	@Override
	public boolean lessThanOrEqualInternal(SubLObject num) {
		return value <= num.intValue();
	}

	@Override
	public boolean numericallyEqualInternal(SubLObject num) {
		return value == num.intValue();
	}

	@Override
	public SubLObject sub(SubLObject num) {
		if (num.isFixnum())
			return SubLNumberFactory.makeInteger(value - num.intValue());
		return num.mult(CommonSymbols.MINUS_ONE_INTEGER).add(this);
	}

	@Override
	public SubLFixnum toFixnum() {
		return this;
	}

	@Override
	public String toTypeName() {
		return "FIXNUM";
	}
}
