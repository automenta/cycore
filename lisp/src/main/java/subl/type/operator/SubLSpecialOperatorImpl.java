//
////
//
package subl.type.operator;

import abcl.Environment;
import abcl.LispObject;
import abcl.SpecialOperator;
import subl.type.core.SubLCons;
import subl.type.core.SubLEnvironment;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.exception.InvalidSubLExpressionException;
import subl.type.symbol.SubLSymbol;

public class SubLSpecialOperatorImpl extends SpecialOperator implements SubLOperator {
	public SubLSpecialOperatorImpl(SubLCompiledFunction evaluationFunction, SubLSymbol functionSymbol) {
		super(null);
		setLambdaName((LispObject) functionSymbol);
		this.evaluationFunction = evaluationFunction;
		if(functionSymbol!=null) {
			functionSymbol.setFunction(this);
		}
	}

//	public SubLSpecialOperatorImpl(Symbol symbol) {
//		super(symbol);
//		this.evaluationFunction = null;
//	}


	public boolean isSubLispBased() {
		return true;
	}
	
	@Override
	public boolean isSpecialRestOnly() {
		return false;
	}

	private final SubLFunction evaluationFunction;

	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this; // special operators are self-evaluating
	}
	@Override
	public LispObject execute(LispObject args, Environment env) {
		SubLFunction fun = evaluationFunction;
		if(fun!=this) {
			return ((LispObject) fun).execute(args, env);
		}
		return (LispObject) apply((SubLCons) args, env);
	}

	@Override
	public SubLList getArglist() {
		if(evaluationFunction==null) return super.getArglist();
		return evaluationFunction.getArglist();
	}

	@Override
	public SubLFunction getEvaluationFunction() {
		if(evaluationFunction==null) {
			return null;
		}
		return evaluationFunction;
	}

	@Override
	public int hashCode(int currentDepth) {
		if(evaluationFunction==null) return super.hashCode(currentDepth);
		if (currentDepth < SubLObject.MAX_HASH_DEPTH) {
			return evaluationFunction.hashCode(currentDepth + 1) << 3;
		} else {
			return SubLObject.DEFAULT_EXCEEDED_HASH_VALUE;
		}
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
