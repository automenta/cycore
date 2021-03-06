/* For LarKC */
package subl.type.core;

import abcl.NLispObject;
import subl.CommonSymbols;
import subl.Types;
import subl.type.number.SubLFixnum;
import subl.type.number.SubLInteger;
import subl.type.number.SubLNumberFactory;
import subl.type.symbol.SubLSymbol;
import subl.util.UUID;

public class SubLGuid extends NLispObject implements SubLObject, Comparable<SubLGuid> {
	SubLGuid() {
		this(UUID.randomUUID());
	}

	SubLGuid(String guidString) {
		this(UUID.fromString(guidString), guidString);
	}

	SubLGuid(SubLVector data) {
		this(new UUID(guidMSB(data), guidLSB(data)));
	}

	SubLGuid(UUID guid) {
		this(guid, guid.toString());
	}

	SubLGuid(UUID guid, String guidString) {
		this.guid = guid;
		this.guidString = guidString;
	}

	private static long guidLSB(SubLVector data) {
		long lsb = 0L;
		assert data.size() == 16;
		for (int i = 8; i < 16; ++i)
			lsb = lsb << 8 | (byte) data.get(i).toInteger().intValue() & 0xFF;
		return lsb;
	}

	private static long guidMSB(SubLVector data) {
		long msb = 0L;
		assert data.size() == 16;
		for (int i = 0; i < 8; ++i)
			msb = msb << 8 | (byte) data.get(i).toInteger().intValue() & 0xFF;
		return msb;
	}

	public static boolean isGuidString(String string) {
		boolean isValid = false;
		try {
			UUID testUUID = UUID.fromString(string);
			isValid = true;
		} catch (Exception ex) {
		}
		return isValid;
	}

	protected static int compareTimeVersionedGUIDs(UUID guid1, UUID guid2) {
		int var1 = guid1.variant();
		int var2 = guid2.variant();
		if (var1 != var2)
			return var1 > var2 ? 1 : -1;
		int seq1 = guid1.clockSequence();
		int seq2 = guid2.clockSequence();
		if (seq1 != seq2)
			return seq1 > seq2 ? 1 : -1;
		long stamp1 = guid1.timestamp();
		long stamp2 = guid2.timestamp();
		if (stamp1 != stamp2)
			return stamp1 > stamp2 ? 1 : -1;
		long node1 = guid1.node();
		long node2 = guid2.node();
		if (node1 != node2)
			return node1 > node2 ? 1 : -1;
		return 0;
	}

	private UUID guid;
	private String guidString;
	public static String GUID_TYPE_NAME = "GUID";

	@Override
	public boolean canFastHash() {
		return true;
	}

	@Override
	public int compareTo(SubLGuid obj) {
		if (obj == null)
			throw new NullPointerException();
		int thisVersion = guid.version();
		int otherVersion = obj.getNativeGuid().version();
		if (thisVersion != otherVersion)
			return thisVersion > otherVersion ? 1 : -1;
		if (guid.version() == 1)
			return compareTimeVersionedGUIDs(guid, obj.getNativeGuid());
		if (guid.version() == 4)
			return stringRepresentation().compareTo(obj.stringRepresentation());
		return guid.compareTo(obj.getNativeGuid());
	}

	@Override
	public boolean equalp(SubLObject obj) {
		return obj != null && (obj == this || obj.isGuid() && compareTo(obj.toGuid()) == 0);
	}

	@Override
	public boolean lispEquals(Object obj) {
		return obj != null && (obj == this || obj instanceof SubLGuid && compareTo((SubLGuid) obj) == 0);
	}

	public void fillByteVector(SubLVector byteVector) {
		assert byteVector.size() == 16;
		long msb = guid.getMostSignificantBits();
		for (int i = 7; i >= 0; --i) {
			int nextByte = (int) msb & 0xFF;
			SubLInteger nextSubLByte = SubLNumberFactory.makeSmallInteger(nextByte);
			byteVector.set(i, nextSubLByte);
			msb >>= 8;
		}
		long lsb = guid.getLeastSignificantBits();
		for (int j = 15; j >= 8; --j) {
			int nextByte2 = (int) lsb & 0xFF;
			SubLInteger nextSubLByte2 = SubLNumberFactory.makeSmallInteger(nextByte2);
			byteVector.set(j, nextSubLByte2);
			lsb >>= 8;
		}
	}

	public SubLString getGuidString() {
		return SubLObjectFactory.makeString(stringRepresentation());
	}

	public UUID getNativeGuid() {
		return guid;
	}

	@Override
	public SubLSymbol getType() {
		return Types.$dtp_guid$;
	}

	@Override
	public SubLFixnum getTypeCode() {
		return CommonSymbols.ONE_HUNDRED_TWENTY_SEVEN_INTEGER;
	}

	@Override
	public int hashCode(int depth) {
		return guid.hashCode();
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	@Override
	public boolean isGuid() {
		return true;
	}

	public String stringRepresentation() {
		return guidString;
	}

	@Override
	public SubLGuid toGuid() {
		return this;
	}

	@Override
	public String printObjectImpl() {
		return "#G\"" + stringRepresentation() + "\"";
	}

	@Override
	public String toTypeName() {
		return "GUID";
	}
}
