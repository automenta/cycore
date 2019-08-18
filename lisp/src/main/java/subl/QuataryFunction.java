//
//
//
package subl;

import subl.type.core.SubLObject;
import subl.type.operator.FixedArityFunctor;
import subl.type.operator.SubLFunction;
import subl.type.symbol.SubLSymbol;

public abstract class QuataryFunction extends FixedArityFunctor {
	protected QuataryFunction(SubLFunction func) {
		(this.func = func).setQuataryFunction(this);
	}

	public static void initialize() {
	}

	public static QuataryFunction makeInstance(SubLFunction function) {
		QuataryFunction result = function.getQuataryFunction();
		if (result == null)
			result = new QuataryFunction(function) {
				@Override
				public SubLObject processItem(SubLObject obj1, SubLObject obj2, SubLObject obj3, SubLObject obj4) {
					return func.funcallVA(obj1, obj2, obj3, obj4);
				}
			};
		return result;
	}

	public static QuataryFunction makeInstance(SubLSymbol symbol) {
		return makeInstance(symbol.getFunc());
	}

	protected SubLFunction func;

	@Override
	public SubLFunction getFunction() {
		return func.getFunc();
	}

	public abstract SubLObject processItem(SubLObject p0, SubLObject p1, SubLObject p2, SubLObject p3);
}
