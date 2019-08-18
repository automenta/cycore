/* For LarKC */
package subl.type.core;

import subl.*;
import subl.type.exception.InvalidSubLExpressionException;
import subl.type.number.SubLFixnum;
import subl.type.symbol.SubLSymbol;
import subl.util.ComparatorGenericKey;
import subl.util.ComparatorIdentityKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class AbstractSubLVector extends AbstractSubLArraySequence implements SubLSequence {

	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this; // vectors are self-evaluating
	}

	protected AbstractSubLVector() {

	}

	//private SubLObject[] vect;
	//private int size;
	final private static int SXHASH_TYPE_SPEC_PRIME_VECTOR = 199;
	final public static String VECTOR_TYPE_NAME = "VECTOR";

	@Override
	public Object clone() {
		return makeCopy();
	}

	@Override
	public SubLSequence delete(int startIndex, int endIndexExclusive) {
		if (endIndexExclusive <= startIndex)
			return this;
		int deleteCount = endIndexExclusive - startIndex;
		final int size = size();
		for (int i = endIndexExclusive, j = startIndex; i < size; ++i, ++j)
			aset(j, AREF(i));

		for (int i = size - deleteCount; i < size; ++i)
			aset(i, null);
		int newSize = size - deleteCount;
		//size -= deleteCount;
		shrink(newSize);
		return this;
	}

	abstract public void shrink(int newSize);

	@Override
	public boolean equalp(SubLObject obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!obj.isVector())
			return false;
		final int size = size();
		AbstractSubLVector otherVect = obj.toVect();
		if (size != otherVect.size())
			return false;
		for (int i = 0; i < size; ++i)
			if (!AREF(i).equalp(otherVect.AREF(i)))
				return false;
		return true;
	}

	@Override
	public boolean lispEquals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SubLObject))
			return false;
		SubLObject sublObj = (SubLObject) obj;
		if (!sublObj.isVector())
			return false;
		AbstractSubLVector otherVect = sublObj.toVect();
		final int size = size();

		if (size != otherVect.size())
			return false;
		for (int i = 0; i < size; ++i)
			if (!AREF(i).lispEquals(otherVect.AREF(i)))
				return false;
		return true;
	}

//	@Override
//	public SubLSequence fill(SubLObject item, int start, int end) {
//		if (end < 0)
//			return this;
//		if (start < 0)
//			start = 0;
//		if (end > this.size())
//			end = this.size();
//		Arrays.fill(vect, start, end, item);
//		return this;
//	}

	@Override
	public SubLObject get(int i) {
		return AREF(i);
	}

	@Override
	public SubLSymbol getType() {
		return Types.$dtp_vector$;
	}

	@Override
	public SubLFixnum getTypeCode() {
		return CommonSymbols.THIRTY_THREE_INTEGER;
	}

	@Override
	public int hashCode(int currentDepth) {
		if (currentDepth >= 8)
			return 0;
		int compositeHash = 199;
		int compositeState = 4;
		final int size = size();

		for (int i = 0; i < size && i < 8; ++i) {
			int partHash = AREF(i).hashCode(currentDepth + 1);
			compositeState = Sxhash.updateCompositeState(compositeState);
			compositeHash = Sxhash.compositeUpdate(compositeHash, partHash, compositeState);
		}
		return compositeHash;
	}

	@Override
	public boolean isArrayBased() {
		return true;
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	public boolean isEmpty() {
		final int size = size();
		return size == 0;
	}

	@Override
	public boolean isSequence() {
		return true;
	}

	@Override
	public boolean isVector() {
		return true;
	}

	@Override
	public SubLObject makeCopy() {
		final int size = size();
		return new SubLVector((SubLObject[]) this.toArray(new SubLObject[size]));
	}

	@Override
	public SubLObject makeDeepCopy() {
		Errors.unimplementedMethod("AbstractSubLVector.makeDeepCopy()");
		return null;
	}

	@Override
	public SubLSequence makeSequenceFromJavaList(List<SubLObject> newBuf) {
		return new SubLVector(newBuf.toArray(new SubLObject[newBuf.size()]));
	}

	@Override
	abstract public void set(int i, SubLObject obj);

	@Override
	abstract public int size() ;

	@Override
	public int size(int max) {
		return size();
	}

	@Override
	public SubLSequence sort(boolean isDestructive, BinaryFunction pred, UnaryFunction key) {
		SubLVector result = (SubLVector) (isDestructive ? this : makeCopy().toVect());
		if (key == AbstractSubLSequence.IDENTITY_UNARY_FUNC)
			Arrays.sort(result.vect, new ComparatorIdentityKey<Object>(pred));
		else
			Arrays.sort(result.vect, new ComparatorGenericKey<Object>(pred, key));
		return result;
	}

	@Override
	abstract public Object[] toArray() ;

	@Override
	public Object[] toArray(Object[] obj) {
		final int size = size();
		for (int i = 0, size1 = obj.length < size ? obj.length : size; i < size1; ++i)
			obj[i] = AREF(i);
		return obj;
	}

	public List<SubLObject> toListOfSubLObjects() {
		SubLObject[] vect = copyToArray();
		List<SubLObject> result = new ArrayList<SubLObject>(vect.length);
		for (int i = 0, realSize = vect.length; i < realSize; ++i)
			result.add(AREF(i));
		return result;
	}

	@Override
	public String printObjectImpl() {
		if (PrintLow.shouldPrintAtCurrentLevel()) {
			SubLObject oldLevel = PrintLow.maybeIncreasePrintLevel();
			try {
				StringBuilder buf = new StringBuilder("#(");
				boolean checkLength = PrintLow.controlPrintLength();
				int printLength = PrintLow.maxPrintLength();
				int counter = 0;
				final int size = size();

				boolean terminatedEarly = false;
				for (int i = 0; i < size; ++i) {
					buf.append(get(i));
					if (checkLength && ++counter >= printLength) {
						buf.append(" ... ");
						terminatedEarly = true;
						break;
					}
					if (i < size - 1)
						buf.append(" ");
				}
				buf.append(")");
				return buf.toString();
			} finally {
				PrintLow.maybeDecreasePrintLevel(oldLevel);
			}
		}
		return "#( # )";
	}

	@Override
	public String toTypeName() {
		return AbstractSubLVector.VECTOR_TYPE_NAME;
	}

	@Override
	public SubLVector toVect() {
		return (SubLVector)this;
	}
}
