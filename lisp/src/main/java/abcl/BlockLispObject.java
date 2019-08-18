package abcl;

public class BlockLispObject extends SLispObject {

//	@Override
//	final public int eq_hashCode() {
//		return ref_hashCode();
//	}

	@Override
	public String printObjectImpl() {
		return unreadableString("BLOCK", true);
	}
}
