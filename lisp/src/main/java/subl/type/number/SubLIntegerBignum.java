/* For LarKC */
package subl.type.number;

import abcl.Fixnum;
import subl.CommonSymbols;
import subl.Types;
import subl.type.core.SubLObject;
import subl.type.symbol.SubLSymbol;

public class SubLIntegerBignum extends Fixnum implements SubLBignum, SubLNumber, SubLObject {
	SubLIntegerBignum(int theInteger) {
		super(theInteger);
	}

	SubLIntegerBignum(Integer theInteger) {
		super((int)theInteger);
	}

	public static String INTEGER_TYPE_NAME = "MARGINALLY-BIG-BIGNUM";

	@Override
	public SubLObject add(SubLObject num) {
		if (num.getNumSize() <= 0)
			return SubLNumberFactory.makeInteger((long) value + (long) num.intValue());
		return num.add(this);
	}

	@Override
	public boolean eql(SubLObject obj) {
		return obj.isIntBignum() && value == obj.intValue();
	}

	@Override
	public boolean equal(SubLObject obj) {
		return obj.isIntBignum() && value == obj.intValue();
	}

	@Override
	public int getNumSize() {
		return 0;
	}

	@Override
	public SubLSymbol getType() {
		return Types.$dtp_bignum$;
	}

	@Override
	public SubLFixnum getTypeCode() {
		return CommonSymbols.THIRTY_FOUR_INTEGER;
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
	public boolean isBignum() {
		return true;
	}

	@Override
	public boolean isIntBignum() {
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
		if (num.getNumSize() <= 0)
			return SubLNumberFactory.makeInteger((long) value - (long) num.intValue());
		return num.mult(CommonSymbols.MINUS_ONE_INTEGER).add(this);
	}

	@Override
	public SubLFixnum toFixnum() {
		abcl.Lisp.lisp_type_error(this,"FIXNUM");
		return null;
	}

	@Override
	public String toTypeName() {
		return "MARGINALLY-BIG-BIGNUM";
	}
}
