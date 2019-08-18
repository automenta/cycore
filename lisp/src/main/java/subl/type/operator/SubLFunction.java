//
////
//
package subl.type.operator;

import subl.*;
import subl.type.core.SubLCons;
import subl.type.core.SubLEnvironment;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;

public interface SubLFunction extends SubLOperator {

	@Override
	SubLList getArglist();

	boolean allowsRest();

	SubLObject apply(Object[] p0);

	SubLObject apply(SubLCons p0, SubLEnvironment p1);

	int applyArity();

	SubLObject evalViaApply(SubLCons p0, SubLEnvironment p1);

	SubLObject funcall(SubLObject[] p0);

	SubLObject funcallVA(SubLObject... p0);

	BinaryFunction getBinaryFunction();

	int getOptionalArgCount();

	QuataryFunction getQuataryFunction();

	QuintaryFunction getQuintaryFunction();

	int getRequiredArgCount();

	TernaryFunction getTernaryFunction();

	UnaryFunction getUnaryFunction();

	ZeroArityFunction getZeroArityFunction();

	boolean isInterpreted();
	
	boolean isSubLispBased();

	void setBinaryFunction(BinaryFunction p0);

	void setQuataryFunction(QuataryFunction p0);

	void setQuintaryFunction(QuintaryFunction p0);

	void setTernaryFunction(TernaryFunction p0);

	void setUnaryFunction(UnaryFunction p0);

	void setZeroArityFunction(ZeroArityFunction p0);
}
