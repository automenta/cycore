package com.cyc.misc;

//

import subl.type.core.SubLObject;
import subl.type.core.SubLStruct;

import static com.cyc.cycjava.cycl.assertions_low.$assertion_content_native.structDecl;

public final class assertion_content_holder {
    public static SubLStruct makeNewInstance() {
	return structDecl.newInstance();
    }

    public static SubLObject isInstance(SubLObject v_object) {
	final SubLObject was = structDecl.isInstance(v_object);
	return was;
    }
}