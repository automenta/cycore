/* For LarKC */
package subl.type.number;

import abcl.Bignum;
import subl.CommonSymbols;
import subl.type.core.SubLObject;
import subl.type.core.SubLObjectFactory;

import java.math.BigInteger;

public class SubLLongBignum extends Bignum implements SubLBignum, SubLInteger, SubLNumber, SubLObject {
/* this fits into CL as a Bignum (haskish but it needs a 'long' type)*/
	public SubLLongBignum(long l) {
		super(BigInteger.valueOf(l));
		longValue = l;
	}

	protected SubLLongBignum(BigInteger l) {
		super(l);
		longValue = l.longValue();
	}

	protected SubLLongBignum(Long theLong) {
		super(BigInteger.valueOf(theLong));
		nativeObject = theLong;
		longValue = theLong;
	}

	private long longValue;
	private Long nativeObject;
	public static String LONG_TYPE_NAME;
	static {
		SubLLongBignum.LONG_TYPE_NAME = "SOMEWHAT-BIG-BIGNUM";
	}

	@Override
	public SubLNumber abs() {
		if (longValue == Long.MIN_VALUE)
			return SubLObjectFactory.makeInteger(new BigInteger("-9223372036854775808").abs());
		long result = Math.abs(longValue);
		return SubLObjectFactory.makeInteger(result);
	}

	@Override
	public SubLObject add(SubLObject num) {
		if (num.getNumSize() > 1)
			return num.add(this);
		BigInteger a = bigIntegerValue();
		BigInteger b = num.bigIntegerValue();
		return SubLNumberFactory.makeInteger(a.add(b));
	}

	@Override
	public SubLObject dec() {
		if (longValue == Long.MIN_VALUE)
			return SubLNumberFactory.makeInteger(new BigInteger(longValue + "").subtract(BigInteger.ONE));
		return SubLNumberFactory.makeInteger(longValue - 1L);
	}

	@Override
	public double doubleValue() {
		return longValue;
	}

	@Override
	public boolean eql(SubLObject obj) {
		return obj.isLongBignum() && longValue == obj.longValue();
	}

	@Override
	public boolean equal(SubLObject obj) {
		return obj.isLongBignum() && longValue == obj.longValue();
	}

	@Override
	public float floatValue() {
		return longValue;
	}

	public synchronized Long getLong() {
		if (nativeObject == null)
			nativeObject = new Long(longValue);
		return nativeObject;
	}

	@Override
	public Number getNativeNumber() {
		return getLong();
	}

	@Override
	public int getNumSize() {
		return 1;
	}

	@Override
	public boolean greaterThanInternal(SubLObject num) {
		return longValue > num.longValue();
	}

	@Override
	public boolean greaterThanOrEqualInternal(SubLObject num) {
		return longValue >= num.longValue();
	}

	@Override
	public SubLObject inc() {
		if (longValue == Long.MAX_VALUE)
			return SubLNumberFactory.makeInteger(new BigInteger(longValue + "").add(BigInteger.ONE));
		return SubLNumberFactory.makeInteger(longValue + 1L);
	}

	@Override
	public int intValue() {
		return (int) longValue;
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
	public boolean isInteger() {
		return true;
	}

	@Override
	public boolean isLongBignum() {
		return true;
	}

	@Override
	public boolean isNegative() {
		return longValue < 0L;
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public boolean isPositive() {
		return longValue > 0L;
	}

	@Override
	public boolean isZero() {
		return longValue == 0L;
	}

	@Override
	public boolean lessThanInternal(SubLObject num) {
		return longValue < num.longValue();
	}

	@Override
	public boolean lessThanOrEqualInternal(SubLObject num) {
		return longValue <= num.longValue();
	}

	@Override
	public long longValue() {
		return longValue;
	}

	@Override
	public SubLObject mult(SubLObject num) {
		if (num.getNumSize() > 1)
			return num.mult(this);
		BigInteger a = bigIntegerValue();
		BigInteger b = num.bigIntegerValue();
		return SubLNumberFactory.makeInteger(a.multiply(b));
	}

	@Override
	public boolean numericallyEqualInternal(SubLObject num) {
		return longValue == num.longValue();
	}

	@Override
	public SubLObject sub(SubLObject num) {
		if (num.getNumSize() > 1)
			return num.mult(CommonSymbols.MINUS_ONE_INTEGER).add(this);
		BigInteger a = bigIntegerValue();
		BigInteger b = num.bigIntegerValue();
		return SubLNumberFactory.makeInteger(a.subtract(b));
	}

	@Override
	public SubLFixnum toFixnum() {
		abcl.Lisp.lisp_type_error(this,"FIXNUM");
		return null;
	}


	@Override
	public String toTypeName() {
		return SubLLongBignum.LONG_TYPE_NAME;
	}
}
