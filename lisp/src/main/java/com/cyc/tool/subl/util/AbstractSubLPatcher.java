/* For LarKC */
package com.cyc.tool.subl.util;

import subl.Eval;
import subl.type.core.SubLObjectFactory;

public abstract class AbstractSubLPatcher implements SubLPatcher {
	@Override
	public void doPatch() {
		String[] patchedClasses = getPatchedClasses();
		if (patchedClasses == null)
			return;
		for (String className : patchedClasses)
			Eval.patchSubLFile(SubLObjectFactory.makeString(className));
	}
}
