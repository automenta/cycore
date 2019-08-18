package subl.type.core;

import abcl.LispObject;
import subl.type.exception.InvalidSubLExpressionException;

abstract public class FromSubLisp extends LispObject {
	
	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this; // self-evaluating
	}


}
