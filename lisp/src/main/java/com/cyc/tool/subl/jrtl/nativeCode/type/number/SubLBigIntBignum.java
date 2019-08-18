/* For LarKC */
package com.cyc.tool.subl.jrtl.nativeCode.type.number;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.CommonSymbols;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory;
import org.armedbear.lisp.Bignum;

import java.math.BigInteger;

public class SubLBigIntBignum extends Bignum implements SubLBignum, SubLInteger, SubLNumber, SubLObject {
	public SubLBigIntBignum(BigInteger theBigInt) {
		super(theBigInt);
	 	this.value = theBigInt;
	}
	  public final BigInteger value;
//	final public BigInteger value;
	public static BigInteger ZERO_BIGINT;
	public static BigInteger ONE_BIGINT;
	public static String BIG_INT_TYPE_NAME;
	static {
		ZERO_BIGINT = new BigInteger("0");
		ONE_BIGINT = new BigInteger("1");
		SubLBigIntBignum.BIG_INT_TYPE_NAME = "REALLY-BIG-BIGNUM";
	}

	@Override
	public SubLNumber abs() {
		BigInteger result = value.abs();
		return SubLObjectFactory.makeInteger(result);
	}

	@Override
	public SubLObject add(SubLObject num) {
		if (num.getNumSize() > 2)
			return num.add(this);
		return SubLNumberFactory.makeInteger(value.add(num.bigIntegerValue()));
	}

	@Override
	public SubLObject dec() {
		return SubLNumberFactory.makeInteger(value.subtract(SubLBigIntBignum.ONE_BIGINT));
	}

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

	@Override
	public boolean eql(SubLObject obj) {
		return obj.isBigIntegerBignum() && value.equals(obj.bigIntegerValue());
	}

	@Override
	public boolean equal(SubLObject obj) {
		return obj.isBigIntegerBignum() && value.equals(obj.bigIntegerValue());
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	public BigInteger getBigInt() {
		return value;
	}

	@Override
	public Number getNativeNumber() {
		return value;
	}

	@Override
	public int getNumSize() {
		return 2;
	}

	@Override
	public boolean greaterThanInternal(SubLObject num) {
		return value.compareTo(num.bigIntegerValue()) > 0;
	}

	@Override
	public boolean greaterThanOrEqualInternal(SubLObject num) {
		return value.compareTo(num.bigIntegerValue()) >= 0;
	}

	@Override
	public SubLObject inc() {
		return SubLNumberFactory.makeInteger(value.add(SubLBigIntBignum.ONE_BIGINT));
	}

	@Override
	public int intValue() {
		return value.intValue();
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	@Override
	public boolean isBigIntegerBignum() {
		return true;
	}

	@Override
	public boolean isBignum() {
		return true;
	}

	@Override
	public boolean isInteger() {
		return true;
	}

	@Override
	public boolean isNegative() {
		return value.compareTo(SubLBigIntBignum.ZERO_BIGINT) < 0;
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public boolean isPositive() {
		return value.compareTo(SubLBigIntBignum.ZERO_BIGINT) > 0;
	}

	@Override
	public boolean isZero() {
		return value.compareTo(SubLBigIntBignum.ZERO_BIGINT) == 0;
	}

	@Override
	public boolean lessThanInternal(SubLObject num) {
		return value.compareTo(num.bigIntegerValue()) < 0;
	}

	@Override
	public boolean lessThanOrEqualInternal(SubLObject num) {
		return value.compareTo(num.bigIntegerValue()) <= 0;
	}

	@Override
	public long longValue() {
		return value.longValue();
	}

	@Override
	public SubLObject mult(SubLObject num) {
		if (num.getNumSize() > 2)
			return num.mult(this);
		return SubLNumberFactory.makeInteger(value.multiply(num.bigIntegerValue()));
	}

	@Override
	public boolean numericallyEqualInternal(SubLObject num) {
		return value.compareTo(num.bigIntegerValue()) == 0;
	}

	@Override
	public SubLObject sub(SubLObject num) {
		if (num.getNumSize() > 2)
			return num.mult(CommonSymbols.MINUS_ONE_INTEGER).add(this);
		return SubLNumberFactory.makeInteger(value.subtract(num.bigIntegerValue()));
	}

	@Override
	public SubLFixnum toFixnum() {
		lisp_type_error(this,"FIXNUM");
		return null;
	}

}
