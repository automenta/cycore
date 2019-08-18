/* For LarKC */
package com.cyc.tool.subl.util;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.FromSubLisp;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLEnvironment;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.exception.InvalidSubLExpressionException;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLPackage;
import org.armedbear.lisp.ControlTransfer;
import org.armedbear.lisp.Lisp;
import cyc.CYC;
import org.armedbear.lisp.Package;

import java.util.logging.Level;

public abstract class SafeRunnable extends FromSubLisp implements Runnable {

	@Override
	public SubLObject eval(SubLEnvironment env) throws InvalidSubLExpressionException {
		return this; // self-evaluating
	}
    protected boolean outerSubLisp;
    protected Package outerPackage;

    public SafeRunnable() {
        this.outerSubLisp = CYC.isSubLisp();
        this.outerPackage = Lisp.getCurrentPackage();
    }

	@Override
    public SafeRunnable toLispObject() {
		return this;
	}
	
	@Override
	public void run() {

        final boolean wasSubLisp = CYC.isSubLisp();
        final SubLPackage prevPackage = Lisp.getCurrentPackage();
		try {
            CYC.setSubLisp(outerSubLisp);
            if (outerSubLisp) {
			SubLPackage.setCurrentPackage(SubLPackage.CYC_PACKAGE);
            } else {
                SubLPackage.setCurrentPackage(outerPackage);
            }
			safeRun();
		} catch (ControlTransfer tr) {
			throw tr;
		} catch (SubLProcess.TerminationRequest tr) {	
		} catch (Throwable e) {
			Errors.handleError(e);
        } finally {
            CYC.setSubLisp(outerSubLisp);
            if (!outerSubLisp) {
                SubLPackage.setCurrentPackage(prevPackage);
                CYC.setSubLisp(wasSubLisp);
            } else {

            }
            //   SubLPackage.setCurrentPackage(outerPackage);
		}
	}

	public abstract void safeRun();

	protected String getErrorMessage(Exception e) {
		return e.getMessage();
	}

	protected Level getLogLevel(Exception e) {
		return Level.WARNING;
	}
}
