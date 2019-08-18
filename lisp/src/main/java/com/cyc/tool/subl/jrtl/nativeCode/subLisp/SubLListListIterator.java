/* For LarKC */
package com.cyc.tool.subl.jrtl.nativeCode.subLisp;

import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;

import java.util.ListIterator;

public interface SubLListListIterator extends ListIterator {
	int ITERATE_TO_END = -1;

	SubLObject currentSubSeq();

	SubLObject getDottedElement();

	void init(SubLList p0);

	void init(SubLList p0, int p1);

	void init(SubLList p0, int p1, int p2);

	boolean isArrayBased();

	boolean isNextImproperElement();

	int itemsRemaining();

	SubLObject nextSubLObject();

	SubLObject previousSubSeq();

	void reset();
}
