/* For LarKC */
package com.cyc.tool.subl.jrtl.nativeCode.type.symbol;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.StreamsLow;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLEnvironment;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.exception.InvalidSubLExpressionException;
import com.cyc.tool.subl.jrtl.nativeCode.type.stream.*;
import com.cyc.tool.subl.util.SubLFiles;
import org.armedbear.lisp.Lisp;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Symbol;

public final class SubLT extends Symbol implements SubLObject, SubLBoolean, SubLSymbol {

	private SubLT() {
		super(T_SYMBOL_NAME, Lisp.PACKAGE_CL);
		Lisp.PACKAGE_CL.addSymbol_ImplUseOnly(this);
		Lisp.PACKAGE_CL.export(this);
		initializeConstant(this);
	}

	@Override
	public SubLT toLispObject() {
		return this;
	}

	@Override
	public void setPackage(SubLPackage thePackage) {
		Errors.error("Can't set package on T.");
	}

	@Override
	public LispObject typeOf() {
		return Symbol.BOOLEAN;
	}

	@Override
	public int hashCode(int currentDepth) {
		return T_SYMBOL_NAME_SUBLSTRING.hashCode(currentDepth);
	}

	@Override
	public String getName() {
		return T_SYMBOL_NAME;
	}

	@Override
	public CharSequence getPackageNamePrecise() {
		return "SL";
	}

	@Override
	public SubLPackage getPackage() {
		return SubLPackage.SUBLISP_PACKAGE.toPackage();
	}


	@Override
	public int intValue() {
		if (true) return super.intValue();
		return 1;
	}

	@Override
	public boolean boundp() {
		return true;
	}

	@Override
	public SubLString getSubLName() {
		return T_SYMBOL_NAME_SUBLSTRING;
	}

	public static SubLBoolean toSubLBoolean(boolean val) {
		return val ? SubLT.T : SubLNil.NIL;
	}

	final public static String T_TYPE_NAME = "BOOLEAN";
	final public static String T_SYMBOL_NAME = "T";
	final public static SubLString T_SYMBOL_NAME_SUBLSTRING = SubLObjectFactory.makeString(T_SYMBOL_NAME);
	final public static SubLT T = new SubLT();

	@Override
	public void bind(SubLObject newValue, SubLObject[] bindings) {
		Errors.error("Can't bind T.");
	}

	@Override
	public void bind(SubLObject newValue, SubLThread thread) {
		Errors.error("Can't bind T.");
	}

	@Override
	public SubLObject currentBinding(SubLObject[] bindings) {
		return Errors.error("T does not have a dynamic binding.");
	}

	@Override
	public SubLObject currentBinding(SubLThread thread) {
		return Errors.error("T does not have a dynamic binding.");
	}

	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this;
	}

	@Override
	protected SubLObject getValueSL(boolean canThrow) {
		return this;
	}

	@Override
	public void forceGlobalValue(SubLObject newValue) {
		Errors.error("T is immutable.");
	}

	@Override
	public int getBindingId() {
		return Integer.MIN_VALUE;
	}

	@Override
	public SubLObject getDynamicValue() {
		return this;
	}

	@Override
	public SubLObject getDynamicValue(SubLObject[] bindings) {
		return this;
	}

	@Override
	public SubLObject getDynamicValue(SubLThread thread) {
		return this;
	}

	@Override
	public SubLObject getGlobalValue() {
		return this;
	}

	@Override
	public SubLStream getStream(boolean followSynonymStream) {
		return StreamsLow.$terminal_io$.getValue().getStream(true);
	}

	@Override
	public SubLObject getValue() {
		return this;
	}

	@Override
	public boolean isBoolean() {
		return true;
	}

	@Override
	public boolean isConstantSymbol() {
		return true;
	}

	@Override
	public boolean isDynamic() {
		return false;
	}

	@Override
	public boolean isFunctionSpec() {
		return false;
	}

	@Override
	public boolean isGlobal() {
		return true;
	}

	@Override
	public boolean isKeyword() {
		return false;
	}

	@Override
	public boolean isMacroOperator() {
		return false;
	}

	@Override
	public boolean isUndeclared() {
		return false;
	}

	@Override
	public void rebind(SubLObject oldValue, SubLObject[] bindings) {
		Errors.error("Can't rebind T.");
	}

	@Override
	public void rebind(SubLObject oldValue, SubLThread thread) {
		Errors.error("Can't rebind T.");
	}

	@Override
	public void setAccessMode(SubLFiles.VariableAccessMode accessMode) {
	}

	@Override
	public void setDynamicValue(SubLObject value) {
		Errors.error("Can't change the value of T.");
	}

	@Override
	public void setDynamicValue(SubLObject newValue, SubLObject[] bindings) {
		Errors.error("Can't change the value of T.");
	}

	@Override
	public void setDynamicValue(SubLObject newValue, SubLThread thread) {
		Errors.error("Can't change the value of T.");
	}

	@Override
	public void setGlobalValue(SubLObject value) {
		Errors.error("Can't change the value of T.");
	}

	@Override
	public void setValue(SubLObject value) {
		Errors.error("Can't change the value of T.");
	}

	@Override
	public boolean toBoolean() {
		return true;
	}

	@Override
	public SubLInputBinaryStream toInputBinaryStream() {
		return getStream(false).toInputBinaryStream();
	}

	@Override
	public SubLInputStream toInputStream() {
		return getStream(false).toInputStream();
	}

	@Override
	public SubLInputTextStream toInputTextStream() {
		return getStream(false).toInputTextStream();
	}

	@Override
	public SubLOutputBinaryStream toOutputBinaryStream() {
		return getStream(false).toOutputBinaryStream();
	}

	@Override
	public SubLOutputStream toOutputStream() {
		return getStream(false).toOutputStream();
	}

	@Override
	public SubLOutputTextStream toOutputTextStream() {
		return getStream(false).toOutputTextStream();
	}

	//	@Override
	//	public SubLSymbol toSymbol() {
	//		return this;
	//	}

	@Override
	public String toTypeName() {
		return SubLT.T_TYPE_NAME;
	}

	@Override
	public Object javaInstance() {
		return Boolean.TRUE;
	}
}
