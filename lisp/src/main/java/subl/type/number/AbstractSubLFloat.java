/* For LarKC */
package subl.type.number;

import subl.Errors;
import subl.type.core.SubLObject;
import subl.type.core.SubLObjectFactory;

import java.util.List;

public abstract class AbstractSubLFloat extends AbstractSubLNumber
		implements SubLFloat, SubLNumber, SubLObject, Comparable {
	@Override
	public SubLNumber abs() {
		double result = Math.abs(doubleValue());
		return SubLObjectFactory.makeDouble(result);
	}

	@Override
	public List decode() {
		Errors.unimplementedMethod("AbstractSubLFloat.decode");
		return null;
	}

	@Override
	public SubLNumber digits() {
		Errors.unimplementedMethod("AbstractSubLFloat.digits");
		return null;
	}

	@Override
	public List integerDecode() {
		Errors.unimplementedMethod("AbstractSubLFloat.integerDecode");
		return null;
	}

	@Override
	public SubLNumber precision() {
		Errors.unimplementedMethod("AbstractSubLFloat.precision");
		return null;
	}

	@Override
	public SubLNumber radix() {
		Errors.unimplementedMethod("AbstractSubLFloat.radix");
		return null;
	}

	@Override
	public SubLFloat scale(SubLInteger val) {
		Errors.unimplementedMethod("AbstractSubLFloat.scale");
		return null;
	}

	@Override
	public SubLNumber sign() {
		Errors.unimplementedMethod("AbstractSubLFloat.sign");
		return null;
	}

	@Override
	public SubLInteger toInteger() {
		abcl.Lisp.lisp_type_error(this,"INTEGER");
		return null;
	}
}
