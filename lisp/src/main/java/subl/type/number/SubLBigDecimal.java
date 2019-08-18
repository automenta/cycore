/* For LarKC */
package subl.type.number;

import subl.CommonSymbols;
import subl.Types;
import subl.type.core.SubLObject;
import subl.type.symbol.SubLSymbol;

import java.math.BigDecimal;

public class SubLBigDecimal extends AbstractSubLFloat implements SubLFloat, SubLNumber, SubLObject {
	SubLBigDecimal(BigDecimal theBigInt) {
		this.value = theBigInt;
	}


    @Override
    public int hashCode() {
    	double value = this.doubleValue();
        long bits = Double.doubleToLongBits(value);
        return (int) (bits ^ (bits >>> 32));
    }

    @Override
    public int psxhash() {
    	double value = this.doubleValue();
        if ((value % 1) == 0)
            return (((int) value) & 0x7fffffff);
        else
            return (hashCode() & 0x7fffffff);
    }


	final public BigDecimal value;
	public static BigDecimal ZERO_BIGDECINT;
	public static BigDecimal ONE_BIGDECINT;
	public static String BIGDEC_TYPE_NAME;
	static {
		ZERO_BIGDECINT = new BigDecimal("0");
		ONE_BIGDECINT = new BigDecimal("1");
		BIGDEC_TYPE_NAME = "REALLY-BIGDEC-BIGDECNUM";
	}

	@Override
	public SubLNumber abs() {
		BigDecimal result = value.abs();
		return SubLNumberFactory.makeDecimal(result);
	}

	@Override
	public SubLObject add(SubLObject num) {
		if (num.getNumSize() > 2)
			return num.add(this);
		return SubLNumberFactory.makeDecimal(value.add(num.bigDecimalValue()));
	}

	@Override
	public SubLObject dec() {
		return SubLNumberFactory.makeDecimal(value.subtract(ONE_BIGDECINT));
	}

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

	@Override
	public boolean eql(SubLObject obj) {
		return obj.isBigIntegerBignum() && value.equals(obj.bigDecimalValue());
	}

	@Override
	public boolean equal(SubLObject obj) {
		return obj.isBigIntegerBignum() && value.equals(obj.bigDecimalValue());
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	public BigDecimal getBigInt() {
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
	public SubLSymbol getType() {
		return Types.$dtp_bignum$;
	}

	@Override
	public SubLFixnum getTypeCode() {
		return CommonSymbols.THIRTY_FOUR_INTEGER;
	}

	@Override
	public boolean greaterThanInternal(SubLObject num) {
		return value.compareTo(num.bigDecimalValue()) > 0;
	}

	@Override
	public boolean greaterThanOrEqualInternal(SubLObject num) {
		return value.compareTo(num.bigDecimalValue()) >= 0;
	}

	@Override
	public SubLObject inc() {
		return SubLNumberFactory.makeDecimal(value.add(ONE_BIGDECINT));
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
		return value.compareTo(ZERO_BIGDECINT) < 0;
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public boolean isPositive() {
		return value.compareTo(ZERO_BIGDECINT) > 0;
	}

	@Override
	public boolean isZero() {
		return value.compareTo(ZERO_BIGDECINT) == 0;
	}

	@Override
	public boolean lessThanInternal(SubLObject num) {
		return value.compareTo(num.bigDecimalValue()) < 0;
	}

	@Override
	public boolean lessThanOrEqualInternal(SubLObject num) {
		return value.compareTo(num.bigDecimalValue()) <= 0;
	}

	@Override
	public long longValue() {
		return value.longValue();
	}

	@Override
	public SubLObject mult(SubLObject num) {
		if (num.getNumSize() > 2)
			return num.mult(this);
		return SubLNumberFactory.makeDecimal(value.multiply(num.bigDecimalValue()));
	}

	@Override
	public boolean numericallyEqualInternal(SubLObject num) {
		return value.compareTo(num.bigDecimalValue()) == 0;
	}

	@Override
	public SubLObject sub(SubLObject num) {
		if (num.getNumSize() > 2)
			return num.mult(CommonSymbols.MINUS_ONE_INTEGER).add(this);
		return SubLNumberFactory.makeDecimal(value.subtract(num.bigDecimalValue()));
	}

	@Override
	public SubLFixnum toFixnum() {
        abcl.Lisp.lisp_type_error(this, "FIXNUM");
		return null;
	}

	@Override
	public String toTypeName() {
		return BIGDEC_TYPE_NAME;
	}

	@Override
	public BigDecimal bigDecimalValue() {
		return value;
	}
}
