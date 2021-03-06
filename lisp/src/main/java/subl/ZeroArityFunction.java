/* For LarKC */
package subl;

import subl.type.core.SubLObject;
import subl.type.operator.FixedArityFunctor;
import subl.type.operator.SubLFunction;
import subl.type.symbol.SubLNil;
import subl.type.symbol.SubLSymbol;

public abstract class ZeroArityFunction extends FixedArityFunctor implements CommonSymbols {
	private static class NConcZeroArityFunction extends ZeroArityFunction {
		public NConcZeroArityFunction() {
			super(CommonSymbols.NCONC.getFunc());
		}

		@Override
		public SubLObject processItem() {
			return SubLNil.NIL;
		}
	}

	private static class VectorZeroArityFunction extends ZeroArityFunction {
		public VectorZeroArityFunction() {
			super(CommonSymbols.VECTOR.getFunc());
		}

		@Override
		public SubLObject processItem() {
			return Vectors.vector(Resourcer.EmptySublObjectArray);
		}
	}

	/**
         * 
         */
	protected ZeroArityFunction() {
            // TODO Auto-generated constructor stub
            if (true)
                throw new AbstractMethodError("ZeroArityFunction.ZeroArityFunction.");

        }
	protected ZeroArityFunction(SubLFunction func) {
		(this.func = func).setZeroArityFunction(this);
	}

	public static void initialize() {
	}

	public static ZeroArityFunction makeInstance(SubLFunction function) {
		ZeroArityFunction result = function.getZeroArityFunction();
		if (result == null)
			result = new ZeroArityFunction(function) {
				@Override
				public SubLObject processItem() {
					return func.funcall(Resourcer.EmptySublObjectArray);
				}
			};
		return result;
	}

	public static ZeroArityFunction makeInstance(SubLSymbol symbol) {
		return makeInstance(symbol.getFunc());
	}

	protected SubLFunction func;
	public static ZeroArityFunction NCONC_ZERO_ARITY_FUNC;
	public static ZeroArityFunction VECTOR_ZERO_ARITY_FUNC;
	static {
		NCONC_ZERO_ARITY_FUNC = new NConcZeroArityFunction();
		VECTOR_ZERO_ARITY_FUNC = new VectorZeroArityFunction();
	}

	@Override
	public SubLFunction getFunction() {
		return func;
	}

	public abstract SubLObject processItem();
}
