/* For LarKC */
package subl;

import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.core.SubLProcess;
import subl.type.symbol.SubLNil;
import subl.type.symbol.SubLSymbol;
import subl.util.SubLFile;
import subl.util.SubLFiles;
import subl.util.SubLTrampolineFile;

import java.util.ArrayList;

public class Dynamic extends SubLTrampolineFile {
    public static void bind(SubLSymbol symbol, SubLObject newValue) {
	symbol.bind(newValue, SubLProcess.currentSubLThread().bindingsList);
    }

    public static void bind_dynamic_vars(SubLObject variables, SubLObject values) {
	SubLList variablesTyped = variables.toList();
	SubLList valuesTyped = values.toList();
	SubLListListIterator iter = null;
	SubLListListIterator iter2 = null;
//	Resourcer resourcer = Resourcer.getInstance();
//	try {
	    iter = Resourcer.acquireSubLListListIterator(variablesTyped);
	    iter2 = Resourcer.acquireSubLListListIterator(valuesTyped);
	    while (iter.hasNext() && iter2.hasNext())
		bind(iter.nextSubLObject().toSymbol(), iter2.nextSubLObject());
	    while (iter.hasNext())
		bind(iter.nextSubLObject().toSymbol(), SubLSymbol.UNBOUND);
//	} finally {
//	    resourcer.releaseSubLListListIterator(iter);
//	    resourcer.releaseSubLListListIterator(iter2);
//	}
    }

    public static SubLObject currentBinding(SubLSymbol symbol) {
	return symbol.currentBinding(SubLProcess.currentSubLThread().bindingsList);
    }

    public static ArrayList<SubLObject> extract_dynamic_values(SubLObject variables) {
	SubLThread $thread = SubLProcess.currentSubLThread();
	SubLObject[] $bindings = $thread.bindingsList;
	ArrayList<SubLObject> oldValues = new ArrayList<>();
	for (SubLObject cur = variables; cur != SubLNil.NIL; cur = cur.rest()) {
	    SubLSymbol sym = cur.first().toSymbol();
	    if (!sym.isDynamic())
		Errors.error("Unable to get dynamic value for non-dynamic variable: " + sym + ".");
	    oldValues.add(sym.getDynamicValue($bindings));
	}
	return oldValues;
    }

    public static SubLObject getResult(CatchableThrow ct) {
	return ct.getResult();
    }

    public static SubLObject getTarget(CatchableThrow ct) {
	return ct.getTarget();
    }

    public static SubLObject handleCatchableThrow(CatchableThrow ct, SubLObject target) {
	final SubLObject ctTarget = getTarget(ct);
	boolean catchit = ctTarget == target;
	if (!catchit) {
	    if (target == SubLNil.NIL) {
		// catchit = true;
	    }
	}
	if (catchit)
	    return getResult(ct);
	throw ct;
    }

    public static SubLObject possibly_throw(SubLObject tag, SubLObject result) {
	CatchableThrowImpl.throwToCatch(tag, result, false);
	return null;
    }

    public static void rebind(SubLSymbol $stream_requires_locking$) {
	// TODO Auto-generated method stub
    }

    public static void rebind(SubLSymbol symbol, SubLObject oldValue) {
	symbol.rebind(oldValue, SubLProcess.currentSubLThread().bindingsList);
    }

    public static void rebind_dynamic_vars(SubLObject variables, ArrayList oldValues) {
	SubLList variablesTyped = variables.toList();
	SubLListListIterator iter = null;
	Resourcer resourcer = Resourcer.getInstance();
	try {
	    iter = Resourcer.acquireSubLListListIterator(variablesTyped);
	    int oldIndex = 0;
	    SubLSymbol variable = null;
	    while (iter.hasNext()) {
		variable = iter.nextSubLObject().toSymbol();
		rebind(variable, (SubLObject) oldValues.get(oldIndex++));
	    }
	} finally {
	    resourcer.releaseSubLListListIterator(iter);
	}
    }

    public static SubLObject sublisp_throw(SubLObject tag, SubLObject result) {
	CatchableThrowImpl.throwToCatchLegacy(tag, result);
	return null;
    }

    public static SubLFile me;
    static {
	me = new Dynamic();
    }

    @Override
    public void declareFunctions() {
	SubLFiles.declareFunction(Dynamic.me, "sublisp_throw", "THROW", 2, 0, false);
	SubLFiles.declareFunction(Dynamic.me, "possibly_throw", "POSSIBLY-THROW", 2, 0, false);
    }

    @Override
    public void initializeVariables() {
    }

    @Override
    public void runTopLevelForms() {
    }
}
