//
////
//
package subl.type.operator;

import abcl.Environment;
import abcl.LispObject;
import abcl.Operator;
import abcl.Symbol;
import subl.type.core.SubLEnvironment;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.exception.InvalidSubLExpressionException;

public abstract class SubLSpecialOperator extends Operator implements SubLOperator {
	public SubLSpecialOperator(Symbol functionSymbol) {
		super((Symbol)functionSymbol);
	//	this.evaluationFunction = evaluationFunction;
	}

	//private final SubLCompiledFunction evaluationFunction;

	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this; // special operators are self-evaluating
	}


	@Override
	abstract public LispObject execute(LispObject args, Environment env);

	@Override
	public SubLList getArglist() {
		return getEvaluationFunction().getArglist();
	}

	abstract public SubLFunction getEvaluationFunction() ;
	@Override
	public int hashCode(int currentDepth) {
		if (currentDepth < SubLObject.MAX_HASH_DEPTH) {
			return superHash();
		} else {
			return SubLObject.DEFAULT_EXCEEDED_HASH_VALUE;
		}
	}

	@Override
	public boolean isFunction() {
		return false;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}


	@Override
	public SubLSpecialOperator toSpecialOperator() {
		return this;
	}
//
//	@Override
//	public String toString() {
//		final SubLSymbol functionSymbol = getFunctionSymbol();
//		assert functionSymbol != null;
//		return "#<Special Operator " + functionSymbol + ">";
//	}
}
