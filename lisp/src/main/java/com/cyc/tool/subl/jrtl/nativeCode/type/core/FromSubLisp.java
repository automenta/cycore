package com.cyc.tool.subl.jrtl.nativeCode.type.core;

import com.cyc.tool.subl.jrtl.nativeCode.type.exception.InvalidSubLExpressionException;
import org.armedbear.lisp.LispObject;

abstract public class FromSubLisp extends LispObject {
	
	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this; // self-evaluating
	}


}
