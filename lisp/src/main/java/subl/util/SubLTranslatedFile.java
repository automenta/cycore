/* For LarKC */
package subl.util;

import subl.CommonSymbols;
import subl.CommonSymbols_CYC;
import subl.Resourcer;
import subl.type.core.SubLObject;
import subl.type.core.SubLObjectFactory;
import subl.type.core.SubLProcess;
import subl.type.core.SubLStruct;
import subl.type.operator.SubLFunction;

import java.util.Deque;

public abstract class SubLTranslatedFile extends SubLTrampolineFile implements CommonSymbols, CommonSymbols_CYC
//
{
	// static protected SubLFiles SubLFiles;
    public static boolean installingUnderlay = false;
    
    public static boolean transferOverwrite = false;

    public @interface SubL {
		long position();

		String source();
	}

	public static boolean areAssertionsDisabledFor(SubLFile me) {
		return true;
	}

	public static SubLFunction extractFunctionNamed(String name) {
		return SubLObjectFactory.makeSymbol(name).getFunc();
	}

	public static SubLObject[] EMPTY_SUBL_OBJECT_ARRAY;

	public static Deque<SubLObject> getThrowStack() {
		return SubLProcess.currentSubLThread().throwStack;
	}

	static {
		EMPTY_SUBL_OBJECT_ARRAY = Resourcer.EmptySublObjectArray;
	}

//    public static SubLStruct makeConstSym(String name) {
//	return SubLObjectFactory.makeConstSym(name);
//    }
	
}
