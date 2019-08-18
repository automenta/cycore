/* For LarKC */
package subl;

import abcl.ControlError;
import abcl.LispObject;
import abcl.LispThread;
import abcl.Throw;
import subl.type.core.SubLObject;
import subl.type.core.SubLProcess;
import subl.type.exception.SubLException;

public class CatchableThrowImpl extends Throw {
	CatchableThrowImpl(SubLObject target, SubLObject result) {
		super((LispObject)target, (LispObject)result, LispThread.currentThread());
		//this.tag = (LispObject) target;
		//this.result = (LispObject) result;
	}

    public CatchableThrowImpl(LispObject tag, LispObject result, LispThread thread)
    {
    	super(tag, result, thread);
    }

	//private SubLObject tag;
	//private SubLObject result;

	@Override
	public String toString() {
		String arg0 = this.getClass().getName();
		arg0 += " target=" + tag;
		arg0 += " result=" + result;
		return arg0;
		// return arg1 != null?arg0 + ": " + arg1:arg0;
	}

	@Override
	public String getMessage() {
		return "Attempt to throw to the non-existent tag " + tag + "\n expecting one of: "
				+ SubLProcess.currentSubLThread().throwStack + "\nError handled by: "
				+ SubLException.getStringForException(new Exception());
	}

	@Override
	public SubLObject getResult() {
		return result;
	}

	@Override
	public SubLObject getTarget() {
		return tag;
	}

	@Override
	public LispObject getCondition() {
		// TODO Auto-generated method stub
		if (true)
			Errors.unimplementedMethod("Auto-generated method stub:  ControlTransfer.getCondition");
        return new ControlError("Attempt to throw to the nonexistent tag " +
                tag.princToString() + ".");
	}

}
