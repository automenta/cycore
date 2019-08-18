/* For LarKC */
package com.cyc.tool.subl.jrtl.nativeCode.type.number;

import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;

import java.util.List;

public interface SubLFloat extends SubLNumber, SubLObject {
	List decode();

	SubLNumber digits();

	List integerDecode();

	SubLNumber precision();

	SubLNumber radix();

	SubLFloat scale(SubLInteger p0);

	SubLNumber sign();
}
