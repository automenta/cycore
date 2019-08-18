//
////
//
package com.cyc.tool.subl.jrtl.nativeCode.type.operator;

import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import org.armedbear.lisp.LispObject;

public abstract class AbstractSubLOperator extends LispObject implements SubLOperator {
	AbstractSubLOperator() {
		this(null);
	}
	
    /* (non-Javadoc)
     * @see org.armedbear.lisp.LispObject#lispEquals(java.lang.Object)
     */
    @Override
    public boolean lispEquals(Object obj) {
    	return obj == this;
    }

	AbstractSubLOperator(SubLSymbol functionSymbol) {
		setFunctionSymbol(functionSymbol);
		if (functionSymbol != null) {
			functionSymbol.setFunction(this);
		}
	}

	public LispObject lambdaName;
	public static String SPECIAL_OPERATOR_NAME;
	static {
		AbstractSubLOperator.SPECIAL_OPERATOR_NAME = "SPECIAL-OPERATOR";
	}

	@Override
	public boolean canFastHash() {
		return true;
	}

	@Override
	public SubLSymbol getFunctionSymbol() {
		return (SubLSymbol) lambdaName;
	}

	@Override
	public SubLSpecialOperator toSpecialOperator() {
		if(this instanceof SubLSpecialOperator) return (SubLSpecialOperator) this;
		org.armedbear.lisp.Lisp.lisp_type_error(this,"SPECIAL-OPERATOR");
		return null;
	}

	@Override
	public String toTypeName() {
		return AbstractSubLOperator.SPECIAL_OPERATOR_NAME;
	}

	private void setFunctionSymbol(SubLSymbol functionSymbol) {
		this.lambdaName = (LispObject) functionSymbol;
	}


}
