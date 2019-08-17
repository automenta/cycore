/* For LarKC */
package com.cyc.tool.subl.jrtl.nativeCode.subLisp;

import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLEnvironment;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLVector;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLNil;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLTrampolineFile;

public class Values extends SubLTrampolineFile {
	public static SubLObject arg2(SubLObject arg1, SubLObject arg2) {
		return arg2;
	}

	public static SubLObject eighth_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().eighth_value_helper(arg1, result);
	}

	public static SubLObject eighthMultipleValue() {
		return SubLProcess.currentSubLThread().eighthMultipleValue();
	}

	public static SubLObject eighthMultipleValue(SubLThread thread) {
		return thread.eighthMultipleValue();
	}

	public static SubLObject fifth_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().fifth_value_helper(arg1, result);
	}

	public static SubLObject fifthMultipleValue() {
		return SubLProcess.currentSubLThread().fifthMultipleValue();
	}

	public static SubLObject fifthMultipleValue(SubLThread thread) {
		return thread.fifthMultipleValue();
	}

	public static SubLObject first_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().first_value_helper(arg1, result);
	}

	public static SubLObject firstMultipleValue() {
		return SubLProcess.currentSubLThread().firstMultipleValue();
	}

	public static SubLObject firstMultipleValue(SubLThread thread) {
		return thread.firstMultipleValue();
	}

	public static SubLObject fourth_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().fourth_value_helper(arg1, result);
	}

	public static SubLObject fourthMultipleValue() {
		return SubLProcess.currentSubLThread().fourthMultipleValue();
	}

	public static SubLObject fourthMultipleValue(SubLThread thread) {
		return thread.fourthMultipleValue();
	}

	public static SubLList getMultipleValues() {
		return SubLProcess.currentSubLThread().getMultipleValues();
	}

	public static SubLObject getValuesAsVector() {
		SubLThread currrentThread = SubLProcess.currentSubLThread();
		SubLVector result = currrentThread.getValuesAsVector();
		if (result == null)
			return SubLNil.NIL;
		return result;
	}

	public static SubLList multiple_value_list(SubLObject value1) {
		return SubLProcess.currentSubLThread().multiple_value_list(value1);
	}

	public static SubLList multiple_value_list_eval(SubLObject form, SubLEnvironment env) {
		return SubLProcess.currentSubLThread().multiple_value_list_eval(form, env);
	}

	public static SubLObject nth_value_step_1(SubLObject num) {
		return SubLProcess.currentSubLThread().nth_value_step_1(num);
	}

	public static SubLObject nth_value_step_2(SubLObject num, SubLObject form) {
		return SubLProcess.currentSubLThread().nth_value_step_2(num, form);
	}

	public static SubLObject nthMultipleValue(int n) {
		return SubLProcess.currentSubLThread().nthMultipleValue(n);
	}

	public static SubLObject nthMultipleValue(SubLObject n) {
		return SubLProcess.currentSubLThread().nthMultipleValue(n.intValue());
	}

	public static SubLObject nthMultipleValue(SubLThread thread, int n) {
		return thread.nthMultipleValue(n);
	}

	public static SubLObject nthMultipleValue(SubLThread thread, SubLObject n) {
		return thread.nthMultipleValue(n.intValue());
	}

	public static SubLObject resetMultipleValues() {
		SubLProcess.currentSubLThread().resetMultipleValues();
		return SubLNil.NIL;
	}

	public static void resetMultipleValues(SubLThread thread) {
		thread.resetMultipleValues();
	}

	public static SubLObject restoreValuesFromVector(SubLObject newValues) {
		SubLThread currrentThread = SubLProcess.currentSubLThread();
		if (newValues == SubLNil.NIL || newValues == null) {
			currrentThread.resetMultipleValues();
			return SubLNil.NIL;
		}
		currrentThread.restoreValuesFromVector(newValues.toVect());
		return SubLNil.NIL;
	}

	public static SubLObject second_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().second_value_helper(arg1, result);
	}

	public static SubLObject secondMultipleValue() {
		return SubLProcess.currentSubLThread().secondMultipleValue();
	}

	public static SubLObject secondMultipleValue(SubLThread thread) {
		return thread.secondMultipleValue();
	}

	public static SubLObject setFirstMultipleValue(SubLObject value1) {
		return SubLProcess.currentSubLThread().setFirstMultipleValue(value1);
	}

	public static SubLObject seventh_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().seventh_value_helper(arg1, result);
	}

	public static SubLObject seventhMultipleValue() {
		return SubLProcess.currentSubLThread().seventhMultipleValue();
	}

	public static SubLObject seventhMultipleValue(SubLThread thread) {
		return thread.seventhMultipleValue();
	}

	public static SubLObject sixth_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().sixth_value_helper(arg1, result);
	}

	public static SubLObject sixthMultipleValue() {
		return SubLProcess.currentSubLThread().sixthMultipleValue();
	}

	public static SubLObject sixthMultipleValue(SubLThread thread) {
		return thread.sixthMultipleValue();
	}

	public static SubLObject third_value_helper(SubLObject arg1, SubLObject result) {
		return SubLProcess.currentSubLThread().third_value_helper(arg1, result);
	}

	public static SubLObject thirdMultipleValue() {
		return SubLProcess.currentSubLThread().thirdMultipleValue();
	}

	public static SubLObject thirdMultipleValue(SubLThread thread) {
		return thread.thirdMultipleValue();
	}

	public static SubLObject values(SubLObject value1) {
		return SubLProcess.currentSubLThread().values(value1);
	}

	public static SubLObject values(SubLObject value1, SubLObject value2) {
		return SubLProcess.currentSubLThread().values(value1, value2);
	}

	public static SubLObject values(SubLObject value1, SubLObject value2, SubLObject value3) {
		return SubLProcess.currentSubLThread().values(value1, value2, value3);
	}

	public static SubLObject values(SubLObject value1, SubLObject value2, SubLObject value3, SubLObject value4) {
		return SubLProcess.currentSubLThread().values(value1, value2, value3, value4);
	}

	public static SubLObject values(SubLObject value1, SubLObject value2, SubLObject value3, SubLObject value4,
			SubLObject value5) {
		return SubLProcess.currentSubLThread().values(value1, value2, value3, value4, value5);
	}

	public static SubLObject values(SubLObject value1, SubLObject value2, SubLObject value3, SubLObject value4,
			SubLObject value5, SubLObject value6) {
		return SubLProcess.currentSubLThread().values(value1, value2, value3, value4, value5, value6);
	}

	public static SubLObject values(SubLObject value1, SubLObject value2, SubLObject value3, SubLObject value4,
			SubLObject value5, SubLObject value6, SubLObject value7) {
		return SubLProcess.currentSubLThread().values(value1, value2, value3, value4, value5, value6, value7);
	}

	public static SubLObject values(SubLObject value1, SubLObject value2, SubLObject value3, SubLObject value4,
			SubLObject value5, SubLObject value6, SubLObject value7, SubLObject value8) {
		return SubLProcess.currentSubLThread().values(value1, value2, value3, value4, value5, value6, value7, value8);
	}

	public static SubLObject values(SubLObject[] moreValues) {
		return SubLProcess.currentSubLThread().values(moreValues);
	}

	public static SubLFile me;
	public static SubLSymbol $multiple_values_limit$;
	static {
		me = new Values();
	}

	@Override
	public void declareFunctions() {
		SubLFiles.declareFunction(Values.me, "values", "VALUES", 0, 0, true);
	}

	@Override
	public void initializeVariables() {
		Values.$multiple_values_limit$ = SubLFiles.defconstant(Values.me, "*MULTIPLE-VALUES-LIMIT*",
				SubLObjectFactory.makeInteger(1024));
	}

	@Override
	public void runTopLevelForms() {
	}
}
