/* For LarKC */
package subl.type.exception;

import abcl.JavaException;
import abcl.LispObject;
import subl.Errors;
import subl.SubLMain;
import subl.SubLThread;
import subl.UnaryFunction;
import subl.jrtl.translatedCode.sublisp.conses_high;
import subl.type.core.*;
import subl.type.number.SubLDoubleFloat;
import subl.type.number.SubLFixnum;
import subl.type.number.SubLInteger;
import subl.type.number.SubLNumber;
import subl.type.operator.SubLFunction;
import subl.type.operator.SubLMacro;
import subl.type.stream.*;
import subl.type.symbol.SubLNil;
import subl.type.symbol.SubLPackage;
import subl.type.symbol.SubLPackageIterator;
import subl.type.symbol.SubLSymbol;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;

public class SubLException extends RuntimeException implements SubLObject {
	SubLException() {
		date = new Date();
		restarts = SubLNil.NIL;
		threadName = SubLThread.currentThread().getName();
		description = null;
		init();
	}

	public SubLException(String message) {
		super(message);
		date = new Date();
		restarts = SubLNil.NIL;
		threadName = SubLThread.currentThread().getName();
		description = null;
		init();
	}

	SubLException(String message, Throwable cause) {
		super(message, cause);
		date = new Date();
		restarts = SubLNil.NIL;
		threadName = SubLThread.currentThread().getName();
		description = null;
		init();
	}

	SubLException(Throwable cause) {
		super(cause);
		date = new Date();
		restarts = SubLNil.NIL;
		threadName = SubLThread.currentThread().getName();
		description = null;
		init();
	}

	public static String getStringForException(Throwable e) {
		if (e == null)
			return "<no exception>";
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(new BufferedWriter(writer));
		e.printStackTrace(out);
		out.flush();
		return writer.getBuffer().toString();
	}

	private Date date;
	private SubLList restarts;
	private String threadName;
	private String description;
	public static String EXCPETION_TYPE_NAME;
	private static DateFormat formatter;
	static {
		SubLException.EXCPETION_TYPE_NAME = "Error";
		formatter = DateFormat.getDateTimeInstance(2, 1);
	}

	private synchronized void init() {
		Throwable t = getCause();
		if (t != null && t instanceof Unhandleable) {
			if (t instanceof RuntimeException)
				throw (RuntimeException) t;
			Errors.error("Unexpected situation.", t);
		}
		if (SubLMain.isInitialized())
			restarts = conses_high.copy_list(Errors.$restarts$.getValue().toList()).toList();
		else
			restarts = SubLNil.NIL;
	}

	private void structFieldError(int fieldNum) {
		if (!(this instanceof SubLStruct))
			Errors.error(this + " is not of type STRUCTURE.");
		else
			Errors.error(this + " does not have a slot: " + fieldNum + ".");
	}

	@Override
	public SubLObject add(SubLObject num) {
		return Errors.error("Not a number: " + this + ".");
	}

	@Override
	public void addKey(SubLObject key) {
		abcl.Lisp.lisp_type_error(this, "KEYHASH");
	}

	@Override
	public SubLList asArrayList() {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return null;
	}

	@Override
	public SubLList asConsList() {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return null;
	}

	@Override
	public BigInteger bigIntegerValue() {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return BigInteger.ZERO;
	}

	@Override
	public void bind(SubLObject newValue, SubLObject[] bindings) {
		Errors.error("Illegal attempt to bind non symbol: " + this + ".");
	}

	@Override
	public boolean canFastHash() {
		return true;
	}

	@Override
	public char charValue() {
		abcl.Lisp.lisp_type_error(this, "CHAR");
		return '\0';
	}

	@Override
	public void checkType(SubLSymbol predicate) throws SubLException {
	}

	@Override
	public void checkTypeInternal(SubLSymbol predicate) throws SubLException {
	}

	@Override
	public Object clone() {
		return this;
	}

	@Override
	public SubLObject currentBinding(SubLObject[] bindings) {
		Errors.error("Illegal attempt to get binding for non symbol: " + this + ".");
		return SubLNil.NIL;
	}

	@Override
	public SubLObject dec() {
		return Errors.error("Not a number: " + this + ".");
	}

	@Override
	public double doubleValue() {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return Double.MIN_VALUE;
	}

	@Override
	public SubLObject eighth() {
		return this.get(7);
	}

	@Override
	public void enforceType(SubLSymbol predicate) throws SubLException {
		UnaryFunction Function = UnaryFunction.makeInstance(predicate);
		if (SubLNil.NIL == Function.processItem(this))
			throw new SubLException(
					"Got invalid type for object: " + this + "." + "\nWanted: " + predicate + "\nGot: " + toTypeName());
	}

	@Override
	public void enforceTypeInternal(SubLSymbol predicate) throws SubLException {
	}

	@Override
	public boolean eql(SubLObject obj) {
		return equals(obj);
	}

	@Override
	public boolean equal(SubLObject obj) {
		return eql(obj);
	}

	@Override
	public boolean equalp(SubLObject obj) {
		return equal(obj);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		SubLException other = (SubLException) obj;
		return super.equals(other);
	}

	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		throw new InvalidSubLExpressionException("Error: Attempt to take the value of an exception '" + this + "'.");
	}

	@Override
	public SubLObject fifth() {
		return this.get(4);
	}

	@Override
	public SubLObject first() {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return null;
	}

	@Override
	public SubLObject fourth() {
		return this.get(3);
	}

	@Override
	public SubLObject get(int index) {
		abcl.Lisp.lisp_type_error(this, "SEQUENCE");
		return SubLNil.NIL;
	}

	@Override
	public SubLObject get(SubLObject obj) {
		abcl.Lisp.lisp_type_error(this, "HASHTABLE");
		return SubLNil.NIL;
	}

	@Override
	public SubLCharacter getCharacter(int index) {
		abcl.Lisp.lisp_type_error(this, "STRING");
		return null;
	}

	@Override
	public SubLObject getField(int fieldNum) {
		abcl.Lisp.lisp_type_error(this, "STRUCT");
		return SubLNil.NIL;
	}

	@Override
	public SubLObject getField0() {
		structFieldError(0);
		return null;
	}

	@Override
	public SubLObject getField1() {
		structFieldError(1);
		return null;
	}

	@Override
	public SubLObject getField10() {
		structFieldError(10);
		return null;
	}

	@Override
	public SubLObject getField11() {
		structFieldError(11);
		return null;
	}

	@Override
	public SubLObject getField12() {
		structFieldError(12);
		return null;
	}

	@Override
	public SubLObject getField13() {
		structFieldError(13);
		return null;
	}

	@Override
	public SubLObject getField14() {
		structFieldError(14);
		return null;
	}

	@Override
	public SubLObject getField15() {
		structFieldError(15);
		return null;
	}

	@Override
	public SubLObject getField16() {
		structFieldError(16);
		return null;
	}

	@Override
	public SubLObject getField17() {
		structFieldError(17);
		return null;
	}

	@Override
	public SubLObject getField18() {
		structFieldError(18);
		return null;
	}

	@Override
	public SubLObject getField19() {
		structFieldError(19);
		return null;
	}

	@Override
	public SubLObject getField2() {
		structFieldError(2);
		return null;
	}

	@Override
	public SubLObject getField20() {
		structFieldError(20);
		return null;
	}

	@Override
	public SubLObject getField3() {
		structFieldError(3);
		return null;
	}

	@Override
	public SubLObject getField4() {
		structFieldError(4);
		return null;
	}

	@Override
	public SubLObject getField5() {
		structFieldError(5);
		return null;
	}

	@Override
	public SubLObject getField6() {
		structFieldError(6);
		return null;
	}

	@Override
	public SubLObject getField7() {
		structFieldError(7);
		return null;
	}

	@Override
	public SubLObject getField8() {
		structFieldError(8);
		return null;
	}

	@Override
	public SubLObject getField9() {
		structFieldError(9);
		return null;
	}

	@Override
	public String getFileDesignator() {
		Errors.error(this + " is not a file name designator.");
		return null;
	}

	@Override
	public SubLFunction getFunc() {
		abcl.Lisp.lisp_type_error(this, "FUNCTION");
		return null;
	}

	@Override
	public int getNumSize() {
		Errors.error("Not a number: " + this + ".");
		return -1;
	}

	public SubLList getRestarts() {
		return restarts;
	}

	@Override
	public SubLStream getStream(boolean followSynonymStream) {
		abcl.Lisp.lisp_type_error(this, "STREAM");
		return null;
	}

	@Override
	public String getStringValue() {
		abcl.Lisp.lisp_type_error(this, "STRING");
		return "";
	}

	@Override
	public SubLSymbol getType() {
		Errors.error(this + " does not have a type defined.");
		return null;
	}

	@Override
	public SubLFixnum getTypeCode() {
		Errors.error(this + " does not have a typecode defined.");
		return null;
	}

	@Override
	public int hashCode() {
		return this.hashCode(0);
	}

	@Override
	public int hashCode(int currentDepth) {
		if (currentDepth < 8)
			return super.hashCode();
		return 0;
	}

	@Override
	public boolean hasKey(SubLObject obj) {
		abcl.Lisp.lisp_type_error(this, "KEYHASH");
		return false;
	}

	@Override
	public SubLObject inc() {
		return Errors.error("Not a number: " + this + ".");
	}

	@Override
	public int intValue() {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return Integer.MIN_VALUE;
	}

	@Override
	public boolean isAlien() {
		return false;
	}

	@Override
	public boolean isArrayBased() {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return true;
	}

	@Override
	public boolean isAtom() {
		return true;
	}

	@Override
	public boolean isBigIntegerBignum() {
		return false;
	}

	@Override
	public boolean isBignum() {
		return false;
	}

	@Override
	public boolean isBoolean() {
		return false;
	}

	@Override
	public boolean isChar() {
		return false;
	}

	@Override
	public boolean isCons() {
		return false;
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public boolean isEnvironment() {
		return false;
	}

	@Override
	public boolean isError() {
		return true;
	}

	@Override
	public boolean isFixnum() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

	@Override
	public boolean isFunctionSpec() {
		return false;
	}

	@Override
	public boolean isGuid() {
		return false;
	}

	@Override
	public boolean isHashtable() {
		return false;
	}

	@Override
	public boolean isHashtableIterator() {
		return false;
	}

	@Override
	public boolean isIntBignum() {
		return false;
	}

	@Override
	public boolean isInteger() {
		return false;
	}

	@Override
	public boolean isKeyhash() {
		return false;
	}

	@Override
	public boolean isKeyhashIterator() {
		return false;
	}

	@Override
	public boolean isKeyword() {
		return false;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isLock() {
		return false;
	}

	@Override
	public boolean isLongBignum() {
		return false;
	}

	@Override
	public boolean isMacroOperator() {
		return false;
	}

	@Override
	public boolean isNegative() {
		return false;
	}

	@Override
	public boolean isNil() {
		return false;
	}

	@Override
	public boolean isNumber() {
		return false;
	}

	@Override
	public boolean isPackage() {
		return false;
	}

	@Override
	public boolean isPackageIterator() {
		return false;
	}

	@Override
	public boolean isPositive() {
		return false;
	}

	@Override
	public boolean isProcess() {
		return false;
	}

	@Override
	public boolean isReadWriteLock() {
		return false;
	}

	@Override
	public boolean isRegexPattern() {
		return false;
	}

	@Override
	public boolean isSemaphore() {
		return false;
	}

	@Override
	public boolean isSequence() {
		return false;
	}

	@Override
	public boolean isStream() {
		return false;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isStructure() {
		return false;
	}

	@Override
	public boolean isSymbol() {
		return false;
	}

	@Override
	public boolean isVector() {
		return false;
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public SubLObject last(int i) {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return this;
	}

	@Override
	public long longValue() {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return Long.MIN_VALUE;
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
	public SubLObject mult(SubLObject num) {
		return Errors.error("Not a number: " + this + ".");
	}

	@Override
	public SubLObject ninth() {
		return this.get(8);
	}

	@Override
	public SubLObject nthCdr(int index) {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return null;
	}

	@Override
	public boolean numE(SubLObject x) {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return false;
	}

	@Override
	public boolean numG(SubLObject x) {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return false;
	}

	@Override
	public boolean numGE(SubLObject x) {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return false;
	}

	@Override
	public boolean numL(SubLObject x) {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return false;
	}

	@Override
	public boolean numLE(SubLObject x) {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return false;
	}

	@Override
	public void printStackTrace() {
		System.out.println(toDetailedString());
	}

	@Override
	public SubLObject put(SubLObject key, SubLObject value) {
		abcl.Lisp.lisp_type_error(this, "HASHTABLE");
		return SubLNil.NIL;
	}

	@Override
	public void rebind(SubLObject oldValue, SubLObject[] bindings) {
		Errors.error("Illegal attempt to rebind non symbol: " + this + ".");
	}

	@Override
	public boolean remKey(SubLObject obj) {
		abcl.Lisp.lisp_type_error(this, "HASHTABLE");
		return false;
	}

	@Override
	public SubLObject remove(SubLObject obj) {
		abcl.Lisp.lisp_type_error(this, "HASHTABLE");
		return SubLNil.NIL;
	}

	@Override
	public SubLObject rest() {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return SubLNil.NIL;
	}

	@Override
	public SubLSequence reverse(boolean isDestructive) {
		abcl.Lisp.lisp_type_error(this, "SEQUENCE");
		return SubLNil.NIL;
	}

	@Override
	public SubLObject second() {
		return this.get(1);
	}

	@Override
	public void set(int index, SubLObject val) {
		abcl.Lisp.lisp_type_error(this, "SEQUENCE");
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setField(int fieldNum, SubLObject value) {
		abcl.Lisp.lisp_type_error(this, "STRUCT");
	}

	@Override
	public SubLObject setField0(SubLObject newVal) {
		structFieldError(0);
		return null;
	}

	@Override
	public SubLObject setField1(SubLObject newVal) {
		structFieldError(1);
		return null;
	}

	@Override
	public SubLObject setField10(SubLObject newVal) {
		structFieldError(10);
		return null;
	}

	@Override
	public SubLObject setField11(SubLObject newVal) {
		structFieldError(11);
		return null;
	}

	@Override
	public SubLObject setField12(SubLObject newVal) {
		structFieldError(12);
		return null;
	}

	@Override
	public SubLObject setField13(SubLObject newVal) {
		structFieldError(13);
		return null;
	}

	@Override
	public SubLObject setField14(SubLObject newVal) {
		structFieldError(14);
		return null;
	}

	@Override
	public SubLObject setField15(SubLObject newVal) {
		structFieldError(15);
		return null;
	}

	@Override
	public SubLObject setField16(SubLObject newVal) {
		structFieldError(16);
		return null;
	}

	@Override
	public SubLObject setField17(SubLObject newVal) {
		structFieldError(17);
		return null;
	}

	@Override
	public SubLObject setField18(SubLObject newVal) {
		structFieldError(18);
		return null;
	}

	@Override
	public SubLObject setField19(SubLObject newVal) {
		structFieldError(19);
		return null;
	}

	@Override
	public SubLObject setField2(SubLObject newVal) {
		structFieldError(2);
		return null;
	}

	@Override
	public SubLObject setField20(SubLObject newVal) {
		structFieldError(20);
		return null;
	}

	@Override
	public SubLObject setField3(SubLObject newVal) {
		structFieldError(3);
		return null;
	}

	@Override
	public SubLObject setField4(SubLObject newVal) {
		structFieldError(4);
		return null;
	}

	@Override
	public SubLObject setField5(SubLObject newVal) {
		structFieldError(5);
		return null;
	}

	@Override
	public SubLObject setField6(SubLObject newVal) {
		structFieldError(6);
		return null;
	}

	@Override
	public SubLObject setField7(SubLObject newVal) {
		structFieldError(7);
		return null;
	}

	@Override
	public SubLObject setField8(SubLObject newVal) {
		structFieldError(8);
		return null;
	}

	@Override
	public SubLObject setField9(SubLObject newVal) {
		structFieldError(9);
		return null;
	}

	@Override
	public SubLCons setFirst(SubLObject first) {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return null;
	}

	@Override
	public SubLCons setRest(SubLObject rest) {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return null;
	}

	@Override
	public SubLObject seventh() {
		return this.get(6);
	}

	@Override
	public SubLObject sixth() {
		return this.get(5);
	}

	@Override
	public int size() {
		abcl.Lisp.lisp_type_error(this, "SEQUENCE");
		return -1;
	}

	@Override
	public int size(int max) {
		abcl.Lisp.lisp_type_error(this, "SEQUENCE");
		return -1;
	}

	@Override
	public SubLObject sub(SubLObject num) {
		return Errors.error("Not a number: " + this + ".");
	}

	@Override
	public int superHash() {
		return super.hashCode();
	}

	@Override
	public SubLObject tenth() {
		return this.get(9);
	}

	@Override
	public SubLObject third() {
		return this.get(2);
	}

	@Override
	public SubLCharacter toChar() {
		abcl.Lisp.lisp_type_error(this, "CHAR");
		return null;
	}

	@Override
	public SubLCons toCons() {
		abcl.Lisp.lisp_type_error(this, "CONS");
		return null;
	}

	public String toDetailedString() {
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(new BufferedWriter(writer));
		out.println("#<" + toTypeName() + "\n    Message: " + getMessage() + "\n    Thread: " + threadName
				+ "\n    Time: " + SubLException.formatter.format(date));
		if (description != null && !"".equals(description))
			out.println("    Description: " + description + "\n    Trace:");
		super.printStackTrace(out);
		out.print(">");
		out.flush();
		return writer.getBuffer().toString();
	}

	@Override
	public SubLDoubleFloat toDouble() {
		abcl.Lisp.lisp_type_error(this, "FLOAT");
		return null;
	}

	@Override
	public SubLEnvironment toEnv() {
		abcl.Lisp.lisp_type_error(this, "ENVIRONMENT");
		return null;
	}

	@Override
	public SubLFixnum toFixnum() {
		abcl.Lisp.lisp_type_error(this, "FIXNUM");
		return null;
	}

	@Override
	public SubLGuid toGuid() {
		abcl.Lisp.lisp_type_error(this, "GUID");
		return null;
	}

	@Override
	public SubLHashtable toHashtable() {
		abcl.Lisp.lisp_type_error(this, "HASHTABLE");
		return null;
	}

	@Override
	public SubLHashtableIterator toHashtableIterator() {
		abcl.Lisp.lisp_type_error(this, "HASHTABLE-ITERATOR");
		return null;
	}

	@Override
	public SubLInputBinaryStream toInputBinaryStream() {
		abcl.Lisp.lisp_type_error(this, "INPUT-BINARY-STREAM");
		return null;
	}

	@Override
	public SubLInputStream toInputStream() {
		abcl.Lisp.lisp_type_error(this, "INPUT-STREAM");
		return null;
	}

	@Override
	public SubLInputTextStream toInputTextStream() {
		abcl.Lisp.lisp_type_error(this, "INPUT-TEXT-STREAM");
		return null;
	}

	@Override
	public SubLInteger toInteger() {
		abcl.Lisp.lisp_type_error(this, "INTEGER");
		return null;
	}

	@Override
	public SubLKeyhash toKeyhash() {
		abcl.Lisp.lisp_type_error(this, "KEYHASH");
		return null;
	}

	@Override
	public SubLKeyhashIterator toKeyhashIterator() {
		abcl.Lisp.lisp_type_error(this, "KEYHASH-ITERATOR");
		return null;
	}

	@Override
	public SubLList toList() {
		abcl.Lisp.lisp_type_error(this, "LIST");
		return null;
	}

	@Override
	public SubLLock toLock() {
		abcl.Lisp.lisp_type_error(this, "LOCK");
		return null;
	}

	@Override
	public SubLMacro toMacro() {
		abcl.Lisp.lisp_type_error(this, "MACRO");
		return null;
	}

	@Override
	public SubLNumber toNumber() {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return null;
	}

	@Override
	public SubLOutputBinaryStream toOutputBinaryStream() {
		abcl.Lisp.lisp_type_error(this, "OUTPUT-BINARY-STREAM");
		return null;
	}

	@Override
	public SubLOutputStream toOutputStream() {
		abcl.Lisp.lisp_type_error(this, "OUTPUT-STREAM");
		return null;
	}

	@Override
	public SubLOutputTextStream toOutputTextStream() {
		abcl.Lisp.lisp_type_error(this, "OUTPUT-TEXT-STREAM");
		return null;
	}

	@Override
	public SubLPackage toPackage() {
		abcl.Lisp.lisp_type_error(this, "PACKAGE");
		return null;
	}

	@Override
	public SubLPackageIterator toPackageIterator() {
		abcl.Lisp.lisp_type_error(this, "PACKAGE-ITERATOR");
		return null;
	}

	@Override
	public SubLProcess toProcess() {
		abcl.Lisp.lisp_type_error(this, "PROCESS");
		return null;
	}

	@Override
	public SubLReadWriteLock toReadWriteLock() {
		abcl.Lisp.lisp_type_error(this, "READ-WRITE-LOCK");
		return null;
	}

	@Override
	public SubLRegexPattern toRegexPattern() {
		abcl.Lisp.lisp_type_error(this, "REGEX-PATTERN");
		return null;
	}

	@Override
	public SubLSemaphore toSemaphore() {
		abcl.Lisp.lisp_type_error(this, "SEMAPHORE");
		return null;
	}

	@Override
	public SubLSequence toSeq() {
		abcl.Lisp.lisp_type_error(this, "SEQUENCE");
		return null;
	}

	@Override
	public SubLString toStr() {
		abcl.Lisp.lisp_type_error(this, "STRING");
		return null;
	}

	@Override
	public String toString() {
		return "    " + super.toString();
	}

	@Override
	public String princToString() {
		return "    " + super.toString();
	}

	@Override
	public SubLStruct toStruct() {
		abcl.Lisp.lisp_type_error(this, "STRUCTURE");
		return null;
	}

	@Override
	public SubLSymbol toSymbol() {
		abcl.Lisp.lisp_type_error(this, "SYMBOL");
		return null;
	}

	@Override
	public String toTypeName() {
		return SubLException.EXCPETION_TYPE_NAME;
	}

	@Override
	public SubLVector toVect() {
		abcl.Lisp.lisp_type_error(this, "VECTOR");
		return null;
	}

	@Override
	public String getString() {
		return getStringValue();
	}

	@Override
	public LispObject toLispObject() {
		return new JavaException(this);
	}

	@Override
	public BigDecimal bigDecimalValue() {
		abcl.Lisp.lisp_type_error(this, "NUMBER");
		return BigDecimal.ZERO;
	}

	@Override
	public String printObject() {
		return toString();
	}

	// @Override
	@Override
	public RuntimeException javaInstance() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * subl.type.core.SubLObject#lispEquals(com.cyc.
	 * tool.subl.jrtl.nativeCode.type.core.SubLObject)
	 */
	@Override
	public boolean lispEquals(Object obj) {
		return obj == this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see subl.type.core.SubLObject#getBooleanValue()
	 */
	@Override
	public boolean getBooleanValue() {
		return true;
	}
}
