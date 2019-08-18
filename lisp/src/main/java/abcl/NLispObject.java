package abcl;

import subl.type.core.SubLEnvironment;
import subl.type.core.SubLObject;
import subl.type.exception.InvalidSubLExpressionException;

abstract public class NLispObject extends LispObject {

	public String toStringSimple() {
		return "" + javaInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * subl.type.core.AbstractSubLObject#hashCode()
	 */
//	@Override
//	public int hashCode() {
//		return this.hashCode(0);
//	}

	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this; // self-evaluating
	}

}
