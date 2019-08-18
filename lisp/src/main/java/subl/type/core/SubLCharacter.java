/* For LarKC */
package subl.type.core;

import abcl.LispCharacter;
import subl.CommonSymbols;
import subl.Errors;
import subl.Types;
import subl.type.number.SubLFixnum;
import subl.type.symbol.SubLSymbol;

final public class SubLCharacter extends LispCharacter implements SubLObject, Comparable {

	@Override
	final public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SubLCharacter))
			return super.equals(obj);
		assert charValue() != ((SubLCharacter) obj).charValue();
		return false;
	}

	private static boolean charArrayInited;

	public SubLCharacter(int charNum, String... namez) {
		super((char) charNum);
		if (charNum < 256) {
			// Errors.error("Got invalid character code: " + charNum);
			// value = (char) charNum;
			SubLCharacter.constants[charNum] = this;
		}
		charNames = namez;
		if (namez.length > 0)
			mainName = namez[0];
		hashCode = Character.toLowerCase(value);
	}

	public static char caseInsensitiveConvert(char theChar) {
		return Character.toUpperCase(theChar);
	}

	public static SubLCharacter getCharFromName(SubLString name) {
		Object myChar = null;
		myChar = LarKCCharacter.charNameToSubLCharacterMap_CaseSensitive.get(name);
		if (myChar != null)
			return (SubLCharacter) myChar;
		myChar = LarKCCharacter.charNameToSubLCharacterMap_CaseInsensitive.get(name.toLowerCase());
		if (myChar != null)
			return (SubLCharacter) myChar;
		int n = nameToChar(name.getStringValue());
		if (n >= 0) {
			myChar = LispCharacter.getInstance((char) n);
		}
		if (myChar == null)
			Errors.error("Invalid character " + name);
		return (SubLCharacter) myChar;
	}

	public static SubLCharacter makeChar(int aChar) {
		if (!charArrayInited) {
			initCharArray();
		}
		return SubLCharacter.constants[aChar];
	}

	private static void initCharArray() {
		// constants = (SubLCharacter[])LispCharacter.constants;
	}

	// private char value;
	String[] charNames;
	private int hashCode;
	private String mainName;

//	static SubLCharacter[] constants;// = new SubLCharacter[256];
	@Override
	public boolean canFastHash() {
		return true;
	}

	public int charInDigitNumber(int radix) {
		if (radix > 36)
			Errors.error(radix + " is too large to be an input radix.");
		if (radix < 2)
			Errors.error(radix + " is too small to be an input radix.");
		int val = Character.digit(value, radix);
		return val;
	}

	@Override
	public char charValue() {
		return value;
	}

	@Override
	public Object clone() {
		return this;
	}

	@Override
	public int compareTo(Object o) {
		if (lispEquals(o))
			return 0;
		return lessThan((SubLCharacter) o) ? -1 : 1;
	}

	@Override
	public boolean equalp(SubLObject obj) {
		return this == obj || obj.isChar() && equalsIgnoringCase(obj.toChar());
	}

	@Override
	public boolean lispEquals(Object obj) {
		return obj == this;
	}

	public boolean equalsIgnoringCase(SubLCharacter c) {
		return caseInsensitiveConvert(value) == caseInsensitiveConvert(c.value);
	}

	@Override
	public SubLSymbol getType() {
		return Types.$dtp_character$;
	}

	@Override
	public SubLFixnum getTypeCode() {
		return CommonSymbols.EIGHT_INTEGER;
	}

	public boolean greaterThan(SubLCharacter c) {
		return value > c.value;
	}

	public boolean greaterThanIgnoringCase(SubLCharacter c) {
		return caseInsensitiveConvert(value) > caseInsensitiveConvert(c.value);
	}

	public boolean greaterThanOrEqual(SubLCharacter c) {
		return value >= c.value;
	}

	public boolean greaterThanOrEqualIgnoringCase(SubLCharacter c) {
		return caseInsensitiveConvert(value) >= caseInsensitiveConvert(c.value);
	}

	@Override
	public int hashCode(int currentDepth) {
		if (currentDepth < 8)
			return hashCode;
		return 0;
	}

	public boolean isAlphaChar() {
		return Character.isLetter(value);
	}

	public boolean isAlphNumeric() {
		return Character.isLetterOrDigit(value);
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	public boolean isBothCases() {
		return isUpperCase() && isLowerCase();
	}

	@Override
	public boolean isChar() {
		return true;
	}

	public boolean isCharInDigitNumber(int radix) {
		int val = charInDigitNumber(radix);
		return val >= 0;
	}

	public boolean isDigit() {
		return Character.isDigit(value);
	}

	public boolean isLowerCase() {
		return Character.isLowerCase(value);
	}

	public boolean isUpperCase() {
		return Character.isUpperCase(value);
	}

	public boolean lessThan(SubLCharacter c) {
		return value < c.value;
	}

	public boolean lessThanIgnoringCase(SubLCharacter c) {
		return caseInsensitiveConvert(value) < caseInsensitiveConvert(c.value);
	}

	public boolean lessThanOrEqual(SubLCharacter c) {
		return value <= c.value;
	}

	public boolean lessThanOrEqualIgnoringCase(SubLCharacter c) {
		return caseInsensitiveConvert(value) <= caseInsensitiveConvert(c.value);
	}

	@Override
	public SubLObject makeCopy() {
		return this;
	}

	@Override
	public SubLObject makeDeepCopy() {
		return this;
	}

	@Override
	public SubLCharacter toChar() {
		return this;
	}

	public SubLCharacter toLowerCase() {
		return makeChar(Character.toLowerCase(value));
	}

	// @Override
	public String toStringSubL() {
		if (mainName == null) {
			return charToName(value);
		}
		return "#\\" + mainName;
	}

	@Override
	public String toTypeName() {
		return "CHAR";
	}

	public SubLCharacter toUpperCase() {
		return makeChar(Character.toUpperCase(value));
	}

}
