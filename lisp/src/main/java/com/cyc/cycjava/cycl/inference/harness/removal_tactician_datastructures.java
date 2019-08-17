/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.inference.harness;


import static com.cyc.cycjava.cycl.dictionary.clear_dictionary;
import static com.cyc.cycjava.cycl.dictionary.dictionary_contents;
import static com.cyc.cycjava.cycl.dictionary.dictionary_enter;
import static com.cyc.cycjava.cycl.dictionary.dictionary_lookup_without_values;
import static com.cyc.cycjava.cycl.dictionary.new_dictionary;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_doneP;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_finalize;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_key_value;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_next;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_state;
import static com.cyc.cycjava.cycl.inference.harness.inference_tactician.strategem_problem;
import static com.cyc.cycjava.cycl.set.new_set;
import static com.cyc.cycjava.cycl.set.set_add;
import static com.cyc.cycjava.cycl.set.set_emptyP;
import static com.cyc.cycjava.cycl.set.set_memberP;
import static com.cyc.cycjava.cycl.set.set_p;
import static com.cyc.cycjava.cycl.set.set_remove;
import static com.cyc.cycjava.cycl.stacks.clear_stack;
import static com.cyc.cycjava.cycl.stacks.stack_elements;
import static com.cyc.cycjava.cycl.stacks.stack_empty_p;
import static com.cyc.cycjava.cycl.stacks.stack_peek;
import static com.cyc.cycjava.cycl.stacks.stack_pop;
import static com.cyc.cycjava.cycl.stacks.stack_push;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.plusp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;

import org.armedbear.lisp.Lisp;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.dictionary;
import com.cyc.cycjava.cycl.dictionary_contents;
import com.cyc.cycjava.cycl.set;
import com.cyc.cycjava.cycl.stacks;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLSpecialOperatorDeclarations;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDecl;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDeclNative;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.UnaryFunction;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLStructNative;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.compatibility;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class removal_tactician_datastructures extends SubLTranslatedFile implements V12 {
    // Definitions
    public static final class $removal_strategy_data_native extends SubLStructNative {
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        public SubLObject getField2() {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$link_heads_motivated;
        }

        public SubLObject getField3() {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problems_pending;
        }

        public SubLObject getField4() {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$removal_strategem_index;
        }

        public SubLObject getField5() {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problem_total_strategems_active;
        }

        public SubLObject getField6() {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problem_strategems_set_aside;
        }

        public SubLObject getField7() {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problem_strategems_thrown_away;
        }

        public SubLObject setField2(SubLObject value) {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$link_heads_motivated = value;
        }

        public SubLObject setField3(SubLObject value) {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problems_pending = value;
        }

        public SubLObject setField4(SubLObject value) {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$removal_strategem_index = value;
        }

        public SubLObject setField5(SubLObject value) {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problem_total_strategems_active = value;
        }

        public SubLObject setField6(SubLObject value) {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problem_strategems_set_aside = value;
        }

        public SubLObject setField7(SubLObject value) {
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.this.$problem_strategems_thrown_away = value;
        }

        public SubLObject $link_heads_motivated = Lisp.NIL;

        public SubLObject $problems_pending = Lisp.NIL;

        public SubLObject $removal_strategem_index = Lisp.NIL;

        public SubLObject $problem_total_strategems_active = Lisp.NIL;

        public SubLObject $problem_strategems_set_aside = Lisp.NIL;

        public SubLObject $problem_strategems_thrown_away = Lisp.NIL;

        private static final SubLStructDeclNative structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.class, REMOVAL_STRATEGY_DATA, REMOVAL_STRATEGY_DATA_P, $list_alt2, $list_alt3, new String[]{ "$link_heads_motivated", "$problems_pending", "$removal_strategem_index", "$problem_total_strategems_active", "$problem_strategems_set_aside", "$problem_strategems_thrown_away" }, $list_alt4, $list_alt5, DEFAULT_STRUCT_PRINT_FUNCTION);
    }

    public static final SubLFile me = new removal_tactician_datastructures();

 public static final String myName = "com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures";


    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_removal_strategy_data$ = makeSymbol("*DTP-REMOVAL-STRATEGY-DATA*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol REMOVAL_STRATEGY_DATA = makeSymbol("REMOVAL-STRATEGY-DATA");

    private static final SubLSymbol REMOVAL_STRATEGY_DATA_P = makeSymbol("REMOVAL-STRATEGY-DATA-P");

    static private final SubLList $list2 = list(makeSymbol("LINK-HEADS-MOTIVATED"), makeSymbol("PROBLEMS-PENDING"), makeSymbol("REMOVAL-STRATEGEM-INDEX"), makeSymbol("PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeSymbol("PROBLEM-STRATEGEMS-SET-ASIDE"), makeSymbol("PROBLEM-STRATEGEMS-THROWN-AWAY"));

    static private final SubLList $list3 = list(makeKeyword("LINK-HEADS-MOTIVATED"), makeKeyword("PROBLEMS-PENDING"), makeKeyword("REMOVAL-STRATEGEM-INDEX"), makeKeyword("PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeKeyword("PROBLEM-STRATEGEMS-SET-ASIDE"), makeKeyword("PROBLEM-STRATEGEMS-THROWN-AWAY"));

    static private final SubLList $list4 = list(makeSymbol("REM-STRAT-DATA-LINK-HEADS-MOTIVATED"), makeSymbol("REM-STRAT-DATA-PROBLEMS-PENDING"), makeSymbol("REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX"), makeSymbol("REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeSymbol("REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE"), makeSymbol("REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY"));

    static private final SubLList $list5 = list(makeSymbol("_CSETF-REM-STRAT-DATA-LINK-HEADS-MOTIVATED"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEMS-PENDING"), makeSymbol("_CSETF-REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY"));

    private static final SubLSymbol REMOVAL_STRATEGY_DATA_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("REMOVAL-STRATEGY-DATA-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list8 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("REMOVAL-STRATEGY-DATA-P"));

    private static final SubLSymbol REM_STRAT_DATA_LINK_HEADS_MOTIVATED = makeSymbol("REM-STRAT-DATA-LINK-HEADS-MOTIVATED");

    private static final SubLSymbol _CSETF_REM_STRAT_DATA_LINK_HEADS_MOTIVATED = makeSymbol("_CSETF-REM-STRAT-DATA-LINK-HEADS-MOTIVATED");

    private static final SubLSymbol REM_STRAT_DATA_PROBLEMS_PENDING = makeSymbol("REM-STRAT-DATA-PROBLEMS-PENDING");

    private static final SubLSymbol _CSETF_REM_STRAT_DATA_PROBLEMS_PENDING = makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEMS-PENDING");

    private static final SubLSymbol REM_STRAT_DATA_REMOVAL_STRATEGEM_INDEX = makeSymbol("REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX");

    private static final SubLSymbol _CSETF_REM_STRAT_DATA_REMOVAL_STRATEGEM_INDEX = makeSymbol("_CSETF-REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX");

    private static final SubLSymbol REM_STRAT_DATA_PROBLEM_TOTAL_STRATEGEMS_ACTIVE = makeSymbol("REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE");

    private static final SubLSymbol _CSETF_REM_STRAT_DATA_PROBLEM_TOTAL_STRATEGEMS_ACTIVE = makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE");

    private static final SubLSymbol REM_STRAT_DATA_PROBLEM_STRATEGEMS_SET_ASIDE = makeSymbol("REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE");

    private static final SubLSymbol _CSETF_REM_STRAT_DATA_PROBLEM_STRATEGEMS_SET_ASIDE = makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE");

    private static final SubLSymbol REM_STRAT_DATA_PROBLEM_STRATEGEMS_THROWN_AWAY = makeSymbol("REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY");

    private static final SubLSymbol _CSETF_REM_STRAT_DATA_PROBLEM_STRATEGEMS_THROWN_AWAY = makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY");

    private static final SubLSymbol $LINK_HEADS_MOTIVATED = makeKeyword("LINK-HEADS-MOTIVATED");

    private static final SubLSymbol $REMOVAL_STRATEGEM_INDEX = makeKeyword("REMOVAL-STRATEGEM-INDEX");

    private static final SubLSymbol $PROBLEM_TOTAL_STRATEGEMS_ACTIVE = makeKeyword("PROBLEM-TOTAL-STRATEGEMS-ACTIVE");

    private static final SubLSymbol $PROBLEM_STRATEGEMS_SET_ASIDE = makeKeyword("PROBLEM-STRATEGEMS-SET-ASIDE");

    private static final SubLSymbol $PROBLEM_STRATEGEMS_THROWN_AWAY = makeKeyword("PROBLEM-STRATEGEMS-THROWN-AWAY");

    private static final SubLString $str27$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_REMOVAL_STRATEGY_DATA = makeSymbol("MAKE-REMOVAL-STRATEGY-DATA");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_REMOVAL_STRATEGY_DATA_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-REMOVAL-STRATEGY-DATA-METHOD");

    private static final SubLSymbol REMOVAL_STRATEGY_P = makeSymbol("REMOVAL-STRATEGY-P");

    private static final SubLSymbol MOTIVATION_STRATEGEM_P = makeSymbol("MOTIVATION-STRATEGEM-P");

    private static final SubLSymbol CONNECTED_CONJUNCTION_LINK_P = makeSymbol("CONNECTED-CONJUNCTION-LINK-P");

    private static final SubLSymbol REMOVAL_STRATEGEM_P = makeSymbol("REMOVAL-STRATEGEM-P");

    public static final SubLObject removal_strategy_data_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject removal_strategy_data_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject removal_strategy_data_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject removal_strategy_data_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native.class ? T : NIL;
    }

    public static final SubLObject rem_strat_data_link_heads_motivated_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.getField2();
    }

    public static SubLObject rem_strat_data_link_heads_motivated(final SubLObject v_object) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject rem_strat_data_problems_pending_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.getField3();
    }

    public static SubLObject rem_strat_data_problems_pending(final SubLObject v_object) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject rem_strat_data_removal_strategem_index_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.getField4();
    }

    public static SubLObject rem_strat_data_removal_strategem_index(final SubLObject v_object) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject rem_strat_data_problem_total_strategems_active_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.getField5();
    }

    public static SubLObject rem_strat_data_problem_total_strategems_active(final SubLObject v_object) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject rem_strat_data_problem_strategems_set_aside_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.getField6();
    }

    public static SubLObject rem_strat_data_problem_strategems_set_aside(final SubLObject v_object) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject rem_strat_data_problem_strategems_thrown_away_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.getField7();
    }

    public static SubLObject rem_strat_data_problem_strategems_thrown_away(final SubLObject v_object) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject _csetf_rem_strat_data_link_heads_motivated_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_rem_strat_data_link_heads_motivated(final SubLObject v_object, final SubLObject value) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_rem_strat_data_problems_pending_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_rem_strat_data_problems_pending(final SubLObject v_object, final SubLObject value) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_rem_strat_data_removal_strategem_index_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_rem_strat_data_removal_strategem_index(final SubLObject v_object, final SubLObject value) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_rem_strat_data_problem_total_strategems_active_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_rem_strat_data_problem_total_strategems_active(final SubLObject v_object, final SubLObject value) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_rem_strat_data_problem_strategems_set_aside_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_rem_strat_data_problem_strategems_set_aside(final SubLObject v_object, final SubLObject value) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_rem_strat_data_problem_strategems_thrown_away_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, REMOVAL_STRATEGY_DATA_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_rem_strat_data_problem_strategems_thrown_away(final SubLObject v_object, final SubLObject value) {
        assert NIL != removal_tactician_datastructures.removal_strategy_data_p(v_object) : "! removal_tactician_datastructures.removal_strategy_data_p(v_object) " + "removal_tactician_datastructures.removal_strategy_data_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject make_removal_strategy_data_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($LINK_HEADS_MOTIVATED)) {
                        com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_link_heads_motivated(v_new, current_value);
                    } else {
                        if (pcase_var.eql($PROBLEMS_PENDING)) {
                            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problems_pending(v_new, current_value);
                        } else {
                            if (pcase_var.eql($REMOVAL_STRATEGEM_INDEX)) {
                                com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_removal_strategem_index(v_new, current_value);
                            } else {
                                if (pcase_var.eql($PROBLEM_TOTAL_STRATEGEMS_ACTIVE)) {
                                    com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problem_total_strategems_active(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($PROBLEM_STRATEGEMS_SET_ASIDE)) {
                                        com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_set_aside(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($PROBLEM_STRATEGEMS_THROWN_AWAY)) {
                                            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_thrown_away(v_new, current_value);
                                        } else {
                                            Errors.error($str_alt26$Invalid_slot__S_for_construction_, current_arg);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return v_new;
        }
    }

    public static SubLObject make_removal_strategy_data(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.$removal_strategy_data_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql(removal_tactician_datastructures.$LINK_HEADS_MOTIVATED)) {
                removal_tactician_datastructures._csetf_rem_strat_data_link_heads_motivated(v_new, current_value);
            } else
                if (pcase_var.eql($PROBLEMS_PENDING)) {
                    removal_tactician_datastructures._csetf_rem_strat_data_problems_pending(v_new, current_value);
                } else
                    if (pcase_var.eql(removal_tactician_datastructures.$REMOVAL_STRATEGEM_INDEX)) {
                        removal_tactician_datastructures._csetf_rem_strat_data_removal_strategem_index(v_new, current_value);
                    } else
                        if (pcase_var.eql(removal_tactician_datastructures.$PROBLEM_TOTAL_STRATEGEMS_ACTIVE)) {
                            removal_tactician_datastructures._csetf_rem_strat_data_problem_total_strategems_active(v_new, current_value);
                        } else
                            if (pcase_var.eql(removal_tactician_datastructures.$PROBLEM_STRATEGEMS_SET_ASIDE)) {
                                removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_set_aside(v_new, current_value);
                            } else
                                if (pcase_var.eql(removal_tactician_datastructures.$PROBLEM_STRATEGEMS_THROWN_AWAY)) {
                                    removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_thrown_away(v_new, current_value);
                                } else {
                                    Errors.error(removal_tactician_datastructures.$str27$Invalid_slot__S_for_construction_, current_arg);
                                }





        }
        return v_new;
    }

    public static SubLObject visit_defstruct_removal_strategy_data(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, removal_tactician_datastructures.MAKE_REMOVAL_STRATEGY_DATA, SIX_INTEGER);
        funcall(visitor_fn, obj, $SLOT, removal_tactician_datastructures.$LINK_HEADS_MOTIVATED, removal_tactician_datastructures.rem_strat_data_link_heads_motivated(obj));
        funcall(visitor_fn, obj, $SLOT, $PROBLEMS_PENDING, removal_tactician_datastructures.rem_strat_data_problems_pending(obj));
        funcall(visitor_fn, obj, $SLOT, removal_tactician_datastructures.$REMOVAL_STRATEGEM_INDEX, removal_tactician_datastructures.rem_strat_data_removal_strategem_index(obj));
        funcall(visitor_fn, obj, $SLOT, removal_tactician_datastructures.$PROBLEM_TOTAL_STRATEGEMS_ACTIVE, removal_tactician_datastructures.rem_strat_data_problem_total_strategems_active(obj));
        funcall(visitor_fn, obj, $SLOT, removal_tactician_datastructures.$PROBLEM_STRATEGEMS_SET_ASIDE, removal_tactician_datastructures.rem_strat_data_problem_strategems_set_aside(obj));
        funcall(visitor_fn, obj, $SLOT, removal_tactician_datastructures.$PROBLEM_STRATEGEMS_THROWN_AWAY, removal_tactician_datastructures.rem_strat_data_problem_strategems_thrown_away(obj));
        funcall(visitor_fn, obj, $END, removal_tactician_datastructures.MAKE_REMOVAL_STRATEGY_DATA, SIX_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_removal_strategy_data_method(final SubLObject obj, final SubLObject visitor_fn) {
        return removal_tactician_datastructures.visit_defstruct_removal_strategy_data(obj, visitor_fn);
    }

    public static final SubLObject new_removal_strategy_data_alt(SubLObject removal_index) {
        {
            SubLObject data = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.make_removal_strategy_data(UNPROVIDED);
            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_link_heads_motivated(data, set.new_set(symbol_function(EQ), UNPROVIDED));
            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problems_pending(data, set.new_set(symbol_function(EQ), UNPROVIDED));
            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_removal_strategem_index(data, removal_index);
            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problem_total_strategems_active(data, dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_set_aside(data, dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
            com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_thrown_away(data, dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
            return data;
        }
    }

    public static SubLObject new_removal_strategy_data(final SubLObject removal_index) {
        final SubLObject data = removal_tactician_datastructures.make_removal_strategy_data(UNPROVIDED);
        removal_tactician_datastructures._csetf_rem_strat_data_link_heads_motivated(data, new_set(symbol_function(EQ), UNPROVIDED));
        removal_tactician_datastructures._csetf_rem_strat_data_problems_pending(data, new_set(symbol_function(EQ), UNPROVIDED));
        removal_tactician_datastructures._csetf_rem_strat_data_removal_strategem_index(data, removal_index);
        removal_tactician_datastructures._csetf_rem_strat_data_problem_total_strategems_active(data, new_dictionary(symbol_function(EQ), UNPROVIDED));
        removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_set_aside(data, new_dictionary(symbol_function(EQ), UNPROVIDED));
        removal_tactician_datastructures._csetf_rem_strat_data_problem_strategems_thrown_away(data, new_dictionary(symbol_function(EQ), UNPROVIDED));
        return data;
    }

    /**
     *
     *
     * @return set-p of motivation-strategem-p
     */
    @LispMethod(comment = "@return set-p of motivation-strategem-p")
    public static final SubLObject removal_strategy_link_heads_motivated_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        {
            SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.rem_strat_data_link_heads_motivated(data);
        }
    }

    /**
     *
     *
     * @return set-p of motivation-strategem-p
     */
    @LispMethod(comment = "@return set-p of motivation-strategem-p")
    public static SubLObject removal_strategy_link_heads_motivated(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        final SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
        return removal_tactician_datastructures.rem_strat_data_link_heads_motivated(data);
    }

    /**
     *
     *
     * @return set-p of problem-p
     */
    @LispMethod(comment = "@return set-p of problem-p")
    public static final SubLObject removal_strategy_problems_pending_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        {
            SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.rem_strat_data_problems_pending(data);
        }
    }

    /**
     *
     *
     * @return set-p of problem-p
     */
    @LispMethod(comment = "@return set-p of problem-p")
    public static SubLObject removal_strategy_problems_pending(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        final SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
        return removal_tactician_datastructures.rem_strat_data_problems_pending(data);
    }

    public static final SubLObject removal_strategy_strategem_index_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        {
            SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.rem_strat_data_removal_strategem_index(data);
        }
    }

    public static SubLObject removal_strategy_strategem_index(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        final SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
        return removal_tactician_datastructures.rem_strat_data_removal_strategem_index(data);
    }

    /**
     *
     *
     * @return #'EQ dictionary of problem-p -> non-negative-integer-p
     */
    @LispMethod(comment = "@return #\'EQ dictionary of problem-p -> non-negative-integer-p")
    public static final SubLObject removal_strategy_problem_total_strategems_active_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        {
            SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.rem_strat_data_problem_total_strategems_active(data);
        }
    }

    /**
     *
     *
     * @return #'EQ dictionary of problem-p -> non-negative-integer-p
     */
    @LispMethod(comment = "@return #\'EQ dictionary of problem-p -> non-negative-integer-p")
    public static SubLObject removal_strategy_problem_total_strategems_active(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        final SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
        return removal_tactician_datastructures.rem_strat_data_problem_total_strategems_active(data);
    }

    /**
     *
     *
     * @return #'EQ dictionary of problem-p -> #'EQ set of strategem-p
     */
    @LispMethod(comment = "@return #\'EQ dictionary of problem-p -> #\'EQ set of strategem-p")
    public static final SubLObject removal_strategy_problem_strategems_set_aside_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        {
            SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.rem_strat_data_problem_strategems_set_aside(data);
        }
    }

    /**
     *
     *
     * @return #'EQ dictionary of problem-p -> #'EQ set of strategem-p
     */
    @LispMethod(comment = "@return #\'EQ dictionary of problem-p -> #\'EQ set of strategem-p")
    public static SubLObject removal_strategy_problem_strategems_set_aside(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        final SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
        return removal_tactician_datastructures.rem_strat_data_problem_strategems_set_aside(data);
    }

    /**
     *
     *
     * @return #'EQ dictionary of problem-p -> #'EQ set of strategem-p
     */
    @LispMethod(comment = "@return #\'EQ dictionary of problem-p -> #\'EQ set of strategem-p")
    public static final SubLObject removal_strategy_problem_strategems_thrown_away_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        {
            SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.rem_strat_data_problem_strategems_thrown_away(data);
        }
    }

    /**
     *
     *
     * @return #'EQ dictionary of problem-p -> #'EQ set of strategem-p
     */
    @LispMethod(comment = "@return #\'EQ dictionary of problem-p -> #\'EQ set of strategem-p")
    public static SubLObject removal_strategy_problem_strategems_thrown_away(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        final SubLObject data = inference_datastructures_strategy.strategy_data(strategy);
        return removal_tactician_datastructures.rem_strat_data_problem_strategems_thrown_away(data);
    }

    /**
     * Return T iff removal motivation should propagate through LINK-HEAD in STRATEGY
     */
    @LispMethod(comment = "Return T iff removal motivation should propagate through LINK-HEAD in STRATEGY")
    public static final SubLObject removal_strategy_link_head_motivatedP_alt(SubLObject strategy, SubLObject link_head) {
        SubLTrampolineFile.checkType(link_head, MOTIVATION_STRATEGEM_P);
        return set.set_memberP(link_head, com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_link_heads_motivated(strategy));
    }

    @LispMethod(comment = "Return T iff removal motivation should propagate through LINK-HEAD in STRATEGY")
    public static SubLObject removal_strategy_link_head_motivatedP(final SubLObject strategy, final SubLObject link_head) {
        assert NIL != inference_tactician.motivation_strategem_p(link_head) : "! inference_tactician.motivation_strategem_p(link_head) " + ("inference_tactician.motivation_strategem_p(link_head) " + "CommonSymbols.NIL != inference_tactician.motivation_strategem_p(link_head) ") + link_head;
        return set_memberP(link_head, removal_tactician_datastructures.removal_strategy_link_heads_motivated(strategy));
    }

    public static final SubLObject removal_strategy_connected_conjunction_link_motivatedP_alt(SubLObject strategy, SubLObject connected_conjunction_link) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(connected_conjunction_link, CONNECTED_CONJUNCTION_LINK_P);
        {
            SubLObject link_head = inference_worker.connected_conjunction_link_tactic(connected_conjunction_link);
            return com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_link_head_motivatedP(strategy, link_head);
        }
    }

    public static SubLObject removal_strategy_connected_conjunction_link_motivatedP(final SubLObject strategy, final SubLObject connected_conjunction_link) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_worker.connected_conjunction_link_p(connected_conjunction_link) : "! inference_worker.connected_conjunction_link_p(connected_conjunction_link) " + ("inference_worker.connected_conjunction_link_p(connected_conjunction_link) " + "CommonSymbols.NIL != inference_worker.connected_conjunction_link_p(connected_conjunction_link) ") + connected_conjunction_link;
        final SubLObject link_head = inference_worker.connected_conjunction_link_tactic(connected_conjunction_link);
        return removal_tactician_datastructures.removal_strategy_link_head_motivatedP(strategy, link_head);
    }

    /**
     * Return T iff PROBLEM is pending in STRATEGY
     */
    @LispMethod(comment = "Return T iff PROBLEM is pending in STRATEGY")
    public static final SubLObject removal_strategy_problem_pendingP_alt(SubLObject strategy, SubLObject problem) {
        SubLTrampolineFile.checkType(problem, PROBLEM_P);
        return set.set_memberP(problem, com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problems_pending(strategy));
    }

    @LispMethod(comment = "Return T iff PROBLEM is pending in STRATEGY")
    public static SubLObject removal_strategy_problem_pendingP(final SubLObject strategy, final SubLObject problem) {
        assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
        return set_memberP(problem, removal_tactician_datastructures.removal_strategy_problems_pending(strategy));
    }

    /**
     *
     *
     * @return booleanp; whether PROBLEM is actively being considered for removal by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether PROBLEM is actively being considered for removal by STRATEGY.")
    public static final SubLObject removal_strategy_problem_activeP_alt(SubLObject strategy, SubLObject problem) {
        SubLTrampolineFile.checkType(problem, PROBLEM_P);
        {
            SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_total_strategems_active(strategy);
            return plusp(dictionary.dictionary_lookup_without_values(index, problem, ZERO_INTEGER));
        }
    }

    /**
     *
     *
     * @return booleanp; whether PROBLEM is actively being considered for removal by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether PROBLEM is actively being considered for removal by STRATEGY.")
    public static SubLObject removal_strategy_problem_activeP(final SubLObject strategy, final SubLObject problem) {
        assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
        final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_total_strategems_active(strategy);
        return plusp(dictionary_lookup_without_values(index, problem, ZERO_INTEGER));
    }

    /**
     *
     *
     * @return booleanp; whether PROBLEM has been set aside for later removal consideration by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether PROBLEM has been set aside for later removal consideration by STRATEGY.")
    public static final SubLObject removal_strategy_problem_set_asideP_alt(SubLObject strategy, SubLObject problem) {
        SubLTrampolineFile.checkType(problem, PROBLEM_P);
        if (NIL == com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_activeP(strategy, problem)) {
            {
                SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
                SubLObject v_set = dictionary.dictionary_lookup_without_values(index, problem, NIL);
                if ((NIL != v_set) && (NIL == set.set_emptyP(v_set))) {
                    return T;
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; whether PROBLEM has been set aside for later removal consideration by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether PROBLEM has been set aside for later removal consideration by STRATEGY.")
    public static SubLObject removal_strategy_problem_set_asideP(final SubLObject strategy, final SubLObject problem) {
        assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
        if (NIL == removal_tactician_datastructures.removal_strategy_problem_activeP(strategy, problem)) {
            final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
            final SubLObject v_set = dictionary_lookup_without_values(index, problem, NIL);
            if ((NIL != v_set) && (NIL == set_emptyP(v_set))) {
                return T;
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; whether STRATEGEM has been set aside for later removal consideration by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether STRATEGEM has been set aside for later removal consideration by STRATEGY.")
    public static final SubLObject removal_strategy_strategem_set_asideP_alt(SubLObject strategy, SubLObject strategem) {
        SubLTrampolineFile.checkType(strategem, STRATEGEM_P);
        {
            SubLObject problem = strategem_problem(strategem);
            SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
            SubLObject v_set = dictionary.dictionary_lookup_without_values(index, problem, NIL);
            return makeBoolean((NIL != set.set_p(v_set)) && (NIL != set.set_memberP(strategem, v_set)));
        }
    }

    /**
     *
     *
     * @return booleanp; whether STRATEGEM has been set aside for later removal consideration by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether STRATEGEM has been set aside for later removal consideration by STRATEGY.")
    public static SubLObject removal_strategy_strategem_set_asideP(final SubLObject strategy, final SubLObject strategem) {
        assert NIL != inference_tactician.strategem_p(strategem) : "! inference_tactician.strategem_p(strategem) " + ("inference_tactician.strategem_p(strategem) " + "CommonSymbols.NIL != inference_tactician.strategem_p(strategem) ") + strategem;
        final SubLObject problem = inference_tactician.strategem_problem(strategem);
        final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
        final SubLObject v_set = dictionary_lookup_without_values(index, problem, NIL);
        return makeBoolean((NIL != set_p(v_set)) && (NIL != set_memberP(strategem, v_set)));
    }

    /**
     *
     *
     * @return booleanp; whether STRATEGEM has been thrown away by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether STRATEGEM has been thrown away by STRATEGY.")
    public static final SubLObject removal_strategy_strategem_thrown_awayP_alt(SubLObject strategy, SubLObject strategem) {
        SubLTrampolineFile.checkType(strategem, STRATEGEM_P);
        {
            SubLObject problem = strategem_problem(strategem);
            SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_strategems_thrown_away(strategy);
            SubLObject v_set = dictionary.dictionary_lookup_without_values(index, problem, NIL);
            return makeBoolean((NIL != set.set_p(v_set)) && (NIL != set.set_memberP(strategem, v_set)));
        }
    }

    /**
     *
     *
     * @return booleanp; whether STRATEGEM has been thrown away by STRATEGY.
     */
    @LispMethod(comment = "@return booleanp; whether STRATEGEM has been thrown away by STRATEGY.")
    public static SubLObject removal_strategy_strategem_thrown_awayP(final SubLObject strategy, final SubLObject strategem) {
        assert NIL != inference_tactician.strategem_p(strategem) : "! inference_tactician.strategem_p(strategem) " + ("inference_tactician.strategem_p(strategem) " + "CommonSymbols.NIL != inference_tactician.strategem_p(strategem) ") + strategem;
        final SubLObject problem = inference_tactician.strategem_problem(strategem);
        final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_strategems_thrown_away(strategy);
        final SubLObject v_set = dictionary_lookup_without_values(index, problem, NIL);
        return makeBoolean((NIL != set_p(v_set)) && (NIL != set_memberP(strategem, v_set)));
    }

    /**
     *
     *
     * @return nil or productivity-p
     */
    @LispMethod(comment = "@return nil or productivity-p")
    public static final SubLObject removal_strategy_removal_backtracking_productivity_limit_alt(SubLObject strategy) {
        return inference_datastructures_strategy.strategy_removal_backtracking_productivity_limit(strategy);
    }

    /**
     *
     *
     * @return nil or productivity-p
     */
    @LispMethod(comment = "@return nil or productivity-p")
    public static SubLObject removal_strategy_removal_backtracking_productivity_limit(final SubLObject strategy) {
        return inference_datastructures_strategy.strategy_removal_backtracking_productivity_limit(strategy);
    }

    /**
     *
     *
     * @return nil or removal-strategem-p
     */
    @LispMethod(comment = "@return nil or removal-strategem-p")
    public static final SubLObject removal_strategy_peek_strategem_alt(SubLObject strategy) {
        {
            SubLObject removal_index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_strategem_index(strategy);
            if (NIL == stacks.stack_empty_p(removal_index)) {
                return stacks.stack_peek(removal_index);
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return nil or removal-strategem-p
     */
    @LispMethod(comment = "@return nil or removal-strategem-p")
    public static SubLObject removal_strategy_peek_strategem(final SubLObject strategy) {
        final SubLObject removal_index = removal_tactician_datastructures.removal_strategy_strategem_index(strategy);
        if (NIL == stack_empty_p(removal_index)) {
            return stack_peek(removal_index);
        }
        return NIL;
    }

    /**
     * note R
     */
    @LispMethod(comment = "note R")
    public static final SubLObject removal_strategy_note_problem_motivated_alt(SubLObject strategy, SubLObject problem) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(problem, PROBLEM_P);
        return inference_datastructures_strategy.strategy_note_problem_motivated(strategy, problem);
    }

    @LispMethod(comment = "note R")
    public static SubLObject removal_strategy_note_problem_motivated(final SubLObject strategy, final SubLObject problem) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
        return inference_datastructures_strategy.strategy_note_problem_motivated(strategy, problem);
    }

    /**
     * note R
     */
    @LispMethod(comment = "note R")
    public static final SubLObject removal_strategy_note_link_head_motivated_alt(SubLObject strategy, SubLObject link_head) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(link_head, MOTIVATION_STRATEGEM_P);
        return set.set_add(link_head, com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_link_heads_motivated(strategy));
    }

    @LispMethod(comment = "note R")
    public static SubLObject removal_strategy_note_link_head_motivated(final SubLObject strategy, final SubLObject link_head) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_tactician.motivation_strategem_p(link_head) : "! inference_tactician.motivation_strategem_p(link_head) " + ("inference_tactician.motivation_strategem_p(link_head) " + "CommonSymbols.NIL != inference_tactician.motivation_strategem_p(link_head) ") + link_head;
        return set_add(link_head, removal_tactician_datastructures.removal_strategy_link_heads_motivated(strategy));
    }

    public static final SubLObject removal_strategy_note_problem_pending_alt(SubLObject strategy, SubLObject problem) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(problem, PROBLEM_P);
        return set.set_add(problem, com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problems_pending(strategy));
    }

    public static SubLObject removal_strategy_note_problem_pending(final SubLObject strategy, final SubLObject problem) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
        set_add(problem, removal_tactician_datastructures.removal_strategy_problems_pending(strategy));
        inference_datastructures_strategy.set_problem_recompute_thrown_away(problem, strategy);
        return NIL;
    }

    public static final SubLObject removal_strategy_note_problem_unpending_alt(SubLObject strategy, SubLObject problem) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(problem, PROBLEM_P);
        return set.set_remove(problem, com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problems_pending(strategy));
    }

    public static SubLObject removal_strategy_note_problem_unpending(final SubLObject strategy, final SubLObject problem) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
        set_remove(problem, removal_tactician_datastructures.removal_strategy_problems_pending(strategy));
        inference_datastructures_strategy.set_problem_tactics_recompute_thrown_away(problem, strategy);
        inference_datastructures_strategy.set_problem_tactics_recompute_set_aside(problem, strategy);
        return NIL;
    }

    /**
     * note that STRATEGEM is in STRATEGY's R-box
     */
    @LispMethod(comment = "note that STRATEGEM is in STRATEGY\'s R-box")
    public static final SubLObject removal_strategy_activate_strategem_alt(SubLObject strategy, SubLObject strategem) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(strategem, REMOVAL_STRATEGEM_P);
        {
            SubLObject removal_index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_strategem_index(strategy);
            stacks.stack_push(strategem, removal_index);
        }
        {
            SubLObject problem = strategem_problem(strategem);
            SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_total_strategems_active(strategy);
            SubLObject count = dictionary.dictionary_lookup_without_values(index, problem, ZERO_INTEGER);
            count = add(count, ONE_INTEGER);
            if (ONE_INTEGER.numE(count)) {
                com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_note_problem_unpending(strategy, problem);
            }
            dictionary.dictionary_enter(index, problem, count);
            return count;
        }
    }

    @LispMethod(comment = "note that STRATEGEM is in STRATEGY\'s R-box")
    public static SubLObject removal_strategy_activate_strategem(final SubLObject strategy, final SubLObject strategem) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_tactician.removal_strategem_p(strategem) : "! inference_tactician.removal_strategem_p(strategem) " + ("inference_tactician.removal_strategem_p(strategem) " + "CommonSymbols.NIL != inference_tactician.removal_strategem_p(strategem) ") + strategem;
        final SubLObject removal_index = removal_tactician_datastructures.removal_strategy_strategem_index(strategy);
        stack_push(strategem, removal_index);
        final SubLObject problem = inference_tactician.strategem_problem(strategem);
        final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_total_strategems_active(strategy);
        SubLObject count = dictionary_lookup_without_values(index, problem, ZERO_INTEGER);
        count = add(count, ONE_INTEGER);
        if (ONE_INTEGER.numE(count)) {
            removal_tactician_datastructures.removal_strategy_note_problem_unpending(strategy, problem);
        }
        dictionary_enter(index, problem, count);
        return count;
    }

    public static SubLObject removal_strategy_force_done(final SubLObject strategy) {
        clear_stack(removal_tactician_datastructures.removal_strategy_strategem_index(strategy));
        clear_dictionary(removal_tactician_datastructures.removal_strategy_problem_total_strategems_active(strategy));
        return strategy;
    }

    public static final SubLObject removal_strategy_current_contents_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        return stacks.stack_elements(com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_strategem_index(strategy));
    }

    public static SubLObject removal_strategy_current_contents(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        return stack_elements(removal_tactician_datastructures.removal_strategy_strategem_index(strategy));
    }

    public static final SubLObject removal_strategy_pop_strategem_alt(SubLObject strategy) {
        {
            SubLObject removal_index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_strategem_index(strategy);
            return stacks.stack_pop(removal_index);
        }
    }

    public static SubLObject removal_strategy_pop_strategem(final SubLObject strategy) {
        final SubLObject removal_index = removal_tactician_datastructures.removal_strategy_strategem_index(strategy);
        return stack_pop(removal_index);
    }

    public static final SubLObject removal_strategy_note_strategem_set_aside_alt(SubLObject strategy, SubLObject strategem) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(strategem, REMOVAL_STRATEGEM_P);
        {
            SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
            SubLObject problem = strategem_problem(strategem);
            SubLObject v_set = dictionary.dictionary_lookup_without_values(index, problem, NIL);
            if (NIL == set.set_p(v_set)) {
                v_set = set.new_set(symbol_function(EQ), UNPROVIDED);
                dictionary.dictionary_enter(index, problem, v_set);
            }
            return set.set_add(strategem, v_set);
        }
    }

    public static SubLObject removal_strategy_note_strategem_set_aside(final SubLObject strategy, final SubLObject strategem) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_tactician.removal_strategem_p(strategem) : "! inference_tactician.removal_strategem_p(strategem) " + ("inference_tactician.removal_strategem_p(strategem) " + "CommonSymbols.NIL != inference_tactician.removal_strategem_p(strategem) ") + strategem;
        final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
        final SubLObject problem = inference_tactician.strategem_problem(strategem);
        SubLObject v_set = dictionary_lookup_without_values(index, problem, NIL);
        if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
            inference_datastructures_strategy.set_tactic_set_aside(strategem, strategy, UNPROVIDED);
        }
        inference_datastructures_strategy.set_problem_recompute_set_aside(problem, strategy);
        inference_datastructures_strategy.set_problem_recompute_thrown_away(problem, strategy);
        if (NIL == set_p(v_set)) {
            v_set = new_set(symbol_function(EQ), UNPROVIDED);
            dictionary_enter(index, problem, v_set);
        }
        return set_add(strategem, v_set);
    }

    public static final SubLObject removal_strategy_clear_strategems_set_aside_alt(SubLObject strategy) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
            {
                SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(index));
                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject problem = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                        SubLObject strategem_set = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_note_problem_unpending(strategy, problem);
                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                    }
                } 
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                dictionary.clear_dictionary(index);
            }
            return strategy;
        }
    }

    public static SubLObject removal_strategy_clear_strategems_set_aside(final SubLObject strategy) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_strategems_set_aside(strategy);
        SubLObject iteration_state;
        for (iteration_state = do_dictionary_contents_state(dictionary_contents(index)); NIL == do_dictionary_contents_doneP(iteration_state); iteration_state = do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject problem = do_dictionary_contents_key_value(iteration_state);
            final SubLObject strategem_set = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != inference_datastructures_problem.valid_problem_p(problem)) {
                removal_tactician_datastructures.removal_strategy_note_problem_unpending(strategy, problem);
            }
        }
        do_dictionary_contents_finalize(iteration_state);
        clear_dictionary(index);
        return strategy;
    }

    public static final SubLObject removal_strategy_note_strategem_thrown_away_alt(SubLObject strategy, SubLObject strategem) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        SubLTrampolineFile.checkType(strategem, REMOVAL_STRATEGEM_P);
        {
            SubLObject index = com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_problem_strategems_thrown_away(strategy);
            SubLObject problem = strategem_problem(strategem);
            SubLObject v_set = dictionary.dictionary_lookup_without_values(index, problem, NIL);
            if (NIL == set.set_p(v_set)) {
                v_set = set.new_set(symbol_function(EQ), UNPROVIDED);
                dictionary.dictionary_enter(index, problem, v_set);
            }
            return set.set_add(strategem, v_set);
        }
    }

    public static SubLObject removal_strategy_note_strategem_thrown_away(final SubLObject strategy, final SubLObject strategem) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        assert NIL != inference_tactician.removal_strategem_p(strategem) : "! inference_tactician.removal_strategem_p(strategem) " + ("inference_tactician.removal_strategem_p(strategem) " + "CommonSymbols.NIL != inference_tactician.removal_strategem_p(strategem) ") + strategem;
        if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
            inference_datastructures_strategy.set_tactic_thrown_away(strategem, strategy, UNPROVIDED);
            if (NIL != inference_worker_split.split_tactic_p(strategem)) {
                SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(inference_datastructures_tactic.tactic_problem(strategem));
                SubLObject sibling_split_tactic = NIL;
                sibling_split_tactic = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(sibling_split_tactic, $SPLIT)) && (!sibling_split_tactic.eql(strategem))) {
                        inference_datastructures_strategy.set_tactic_recompute_set_aside(sibling_split_tactic, strategy);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    sibling_split_tactic = cdolist_list_var.first();
                } 
            }
        }
        final SubLObject index = removal_tactician_datastructures.removal_strategy_problem_strategems_thrown_away(strategy);
        final SubLObject problem = inference_tactician.strategem_problem(strategem);
        SubLObject v_set = dictionary_lookup_without_values(index, problem, NIL);
        if (NIL == set_p(v_set)) {
            v_set = new_set(symbol_function(EQ), UNPROVIDED);
            dictionary_enter(index, problem, v_set);
        }
        return set_add(strategem, v_set);
    }

    static private final SubLList $list_alt2 = list(makeSymbol("LINK-HEADS-MOTIVATED"), makeSymbol("PROBLEMS-PENDING"), makeSymbol("REMOVAL-STRATEGEM-INDEX"), makeSymbol("PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeSymbol("PROBLEM-STRATEGEMS-SET-ASIDE"), makeSymbol("PROBLEM-STRATEGEMS-THROWN-AWAY"));

    static private final SubLList $list_alt3 = list(makeKeyword("LINK-HEADS-MOTIVATED"), makeKeyword("PROBLEMS-PENDING"), makeKeyword("REMOVAL-STRATEGEM-INDEX"), makeKeyword("PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeKeyword("PROBLEM-STRATEGEMS-SET-ASIDE"), makeKeyword("PROBLEM-STRATEGEMS-THROWN-AWAY"));

    static private final SubLList $list_alt4 = list(makeSymbol("REM-STRAT-DATA-LINK-HEADS-MOTIVATED"), makeSymbol("REM-STRAT-DATA-PROBLEMS-PENDING"), makeSymbol("REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX"), makeSymbol("REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeSymbol("REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE"), makeSymbol("REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY"));

    public static final SubLObject removal_strategy_no_strategems_activeP_alt(SubLObject strategy) {
        SubLTrampolineFile.checkType(strategy, REMOVAL_STRATEGY_P);
        return stacks.stack_empty_p(com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_strategem_index(strategy));
    }

    public static SubLObject removal_strategy_no_strategems_activeP(final SubLObject strategy) {
        assert NIL != removal_tactician.removal_strategy_p(strategy) : "! removal_tactician.removal_strategy_p(strategy) " + ("removal_tactician.removal_strategy_p(strategy) " + "CommonSymbols.NIL != removal_tactician.removal_strategy_p(strategy) ") + strategy;
        return stack_empty_p(removal_tactician_datastructures.removal_strategy_strategem_index(strategy));
    }

    static private final SubLList $list_alt5 = list(makeSymbol("_CSETF-REM-STRAT-DATA-LINK-HEADS-MOTIVATED"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEMS-PENDING"), makeSymbol("_CSETF-REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE"), makeSymbol("_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY"));

    public static final SubLObject removal_strategy_clear_set_aside_problems_alt(SubLObject strategy) {
        com.cyc.cycjava.cycl.inference.harness.removal_tactician_datastructures.removal_strategy_clear_strategems_set_aside(strategy);
        return strategy;
    }

    public static SubLObject removal_strategy_clear_set_aside_problems(final SubLObject strategy) {
        removal_tactician_datastructures.removal_strategy_clear_strategems_set_aside(strategy);
        return strategy;
    }

    public static SubLObject declare_removal_tactician_datastructures_file() {
        declareFunction("removal_strategy_data_print_function_trampoline", "REMOVAL-STRATEGY-DATA-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("removal_strategy_data_p", "REMOVAL-STRATEGY-DATA-P", 1, 0, false);
        new removal_tactician_datastructures.$removal_strategy_data_p$UnaryFunction();
        declareFunction("rem_strat_data_link_heads_motivated", "REM-STRAT-DATA-LINK-HEADS-MOTIVATED", 1, 0, false);
        declareFunction("rem_strat_data_problems_pending", "REM-STRAT-DATA-PROBLEMS-PENDING", 1, 0, false);
        declareFunction("rem_strat_data_removal_strategem_index", "REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX", 1, 0, false);
        declareFunction("rem_strat_data_problem_total_strategems_active", "REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE", 1, 0, false);
        declareFunction("rem_strat_data_problem_strategems_set_aside", "REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE", 1, 0, false);
        declareFunction("rem_strat_data_problem_strategems_thrown_away", "REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY", 1, 0, false);
        declareFunction("_csetf_rem_strat_data_link_heads_motivated", "_CSETF-REM-STRAT-DATA-LINK-HEADS-MOTIVATED", 2, 0, false);
        declareFunction("_csetf_rem_strat_data_problems_pending", "_CSETF-REM-STRAT-DATA-PROBLEMS-PENDING", 2, 0, false);
        declareFunction("_csetf_rem_strat_data_removal_strategem_index", "_CSETF-REM-STRAT-DATA-REMOVAL-STRATEGEM-INDEX", 2, 0, false);
        declareFunction("_csetf_rem_strat_data_problem_total_strategems_active", "_CSETF-REM-STRAT-DATA-PROBLEM-TOTAL-STRATEGEMS-ACTIVE", 2, 0, false);
        declareFunction("_csetf_rem_strat_data_problem_strategems_set_aside", "_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-SET-ASIDE", 2, 0, false);
        declareFunction("_csetf_rem_strat_data_problem_strategems_thrown_away", "_CSETF-REM-STRAT-DATA-PROBLEM-STRATEGEMS-THROWN-AWAY", 2, 0, false);
        declareFunction("make_removal_strategy_data", "MAKE-REMOVAL-STRATEGY-DATA", 0, 1, false);
        declareFunction("visit_defstruct_removal_strategy_data", "VISIT-DEFSTRUCT-REMOVAL-STRATEGY-DATA", 2, 0, false);
        declareFunction("visit_defstruct_object_removal_strategy_data_method", "VISIT-DEFSTRUCT-OBJECT-REMOVAL-STRATEGY-DATA-METHOD", 2, 0, false);
        declareFunction("new_removal_strategy_data", "NEW-REMOVAL-STRATEGY-DATA", 1, 0, false);
        declareFunction("removal_strategy_link_heads_motivated", "REMOVAL-STRATEGY-LINK-HEADS-MOTIVATED", 1, 0, false);
        declareFunction("removal_strategy_problems_pending", "REMOVAL-STRATEGY-PROBLEMS-PENDING", 1, 0, false);
        declareFunction("removal_strategy_strategem_index", "REMOVAL-STRATEGY-STRATEGEM-INDEX", 1, 0, false);
        declareFunction("removal_strategy_problem_total_strategems_active", "REMOVAL-STRATEGY-PROBLEM-TOTAL-STRATEGEMS-ACTIVE", 1, 0, false);
        declareFunction("removal_strategy_problem_strategems_set_aside", "REMOVAL-STRATEGY-PROBLEM-STRATEGEMS-SET-ASIDE", 1, 0, false);
        declareFunction("removal_strategy_problem_strategems_thrown_away", "REMOVAL-STRATEGY-PROBLEM-STRATEGEMS-THROWN-AWAY", 1, 0, false);
        declareFunction("removal_strategy_link_head_motivatedP", "REMOVAL-STRATEGY-LINK-HEAD-MOTIVATED?", 2, 0, false);
        declareFunction("removal_strategy_connected_conjunction_link_motivatedP", "REMOVAL-STRATEGY-CONNECTED-CONJUNCTION-LINK-MOTIVATED?", 2, 0, false);
        declareFunction("removal_strategy_problem_pendingP", "REMOVAL-STRATEGY-PROBLEM-PENDING?", 2, 0, false);
        declareFunction("removal_strategy_problem_activeP", "REMOVAL-STRATEGY-PROBLEM-ACTIVE?", 2, 0, false);
        declareFunction("removal_strategy_problem_set_asideP", "REMOVAL-STRATEGY-PROBLEM-SET-ASIDE?", 2, 0, false);
        declareFunction("removal_strategy_strategem_set_asideP", "REMOVAL-STRATEGY-STRATEGEM-SET-ASIDE?", 2, 0, false);
        declareFunction("removal_strategy_strategem_thrown_awayP", "REMOVAL-STRATEGY-STRATEGEM-THROWN-AWAY?", 2, 0, false);
        declareFunction("removal_strategy_removal_backtracking_productivity_limit", "REMOVAL-STRATEGY-REMOVAL-BACKTRACKING-PRODUCTIVITY-LIMIT", 1, 0, false);
        declareFunction("removal_strategy_peek_strategem", "REMOVAL-STRATEGY-PEEK-STRATEGEM", 1, 0, false);
        declareFunction("removal_strategy_note_problem_motivated", "REMOVAL-STRATEGY-NOTE-PROBLEM-MOTIVATED", 2, 0, false);
        declareFunction("removal_strategy_note_link_head_motivated", "REMOVAL-STRATEGY-NOTE-LINK-HEAD-MOTIVATED", 2, 0, false);
        declareFunction("removal_strategy_note_problem_pending", "REMOVAL-STRATEGY-NOTE-PROBLEM-PENDING", 2, 0, false);
        declareFunction("removal_strategy_note_problem_unpending", "REMOVAL-STRATEGY-NOTE-PROBLEM-UNPENDING", 2, 0, false);
        declareFunction("removal_strategy_activate_strategem", "REMOVAL-STRATEGY-ACTIVATE-STRATEGEM", 2, 0, false);
        declareFunction("removal_strategy_force_done", "REMOVAL-STRATEGY-FORCE-DONE", 1, 0, false);
        declareFunction("removal_strategy_current_contents", "REMOVAL-STRATEGY-CURRENT-CONTENTS", 1, 0, false);
        declareFunction("removal_strategy_pop_strategem", "REMOVAL-STRATEGY-POP-STRATEGEM", 1, 0, false);
        declareFunction("removal_strategy_note_strategem_set_aside", "REMOVAL-STRATEGY-NOTE-STRATEGEM-SET-ASIDE", 2, 0, false);
        declareFunction("removal_strategy_clear_strategems_set_aside", "REMOVAL-STRATEGY-CLEAR-STRATEGEMS-SET-ASIDE", 1, 0, false);
        declareFunction("removal_strategy_note_strategem_thrown_away", "REMOVAL-STRATEGY-NOTE-STRATEGEM-THROWN-AWAY", 2, 0, false);
        declareFunction("removal_strategy_no_strategems_activeP", "REMOVAL-STRATEGY-NO-STRATEGEMS-ACTIVE?", 1, 0, false);
        declareFunction("removal_strategy_clear_set_aside_problems", "REMOVAL-STRATEGY-CLEAR-SET-ASIDE-PROBLEMS", 1, 0, false);
        return NIL;
    }

    static private final SubLString $str_alt26$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    public static SubLObject init_removal_tactician_datastructures_file() {
        defconstant("*DTP-REMOVAL-STRATEGY-DATA*", removal_tactician_datastructures.REMOVAL_STRATEGY_DATA);
        return NIL;
    }

    public static SubLObject setup_removal_tactician_datastructures_file() {
        register_method($print_object_method_table$.getGlobalValue(), removal_tactician_datastructures.$dtp_removal_strategy_data$.getGlobalValue(), symbol_function(removal_tactician_datastructures.REMOVAL_STRATEGY_DATA_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim(removal_tactician_datastructures.$list8);
        def_csetf(removal_tactician_datastructures.REM_STRAT_DATA_LINK_HEADS_MOTIVATED, removal_tactician_datastructures._CSETF_REM_STRAT_DATA_LINK_HEADS_MOTIVATED);
        def_csetf(removal_tactician_datastructures.REM_STRAT_DATA_PROBLEMS_PENDING, removal_tactician_datastructures._CSETF_REM_STRAT_DATA_PROBLEMS_PENDING);
        def_csetf(removal_tactician_datastructures.REM_STRAT_DATA_REMOVAL_STRATEGEM_INDEX, removal_tactician_datastructures._CSETF_REM_STRAT_DATA_REMOVAL_STRATEGEM_INDEX);
        def_csetf(removal_tactician_datastructures.REM_STRAT_DATA_PROBLEM_TOTAL_STRATEGEMS_ACTIVE, removal_tactician_datastructures._CSETF_REM_STRAT_DATA_PROBLEM_TOTAL_STRATEGEMS_ACTIVE);
        def_csetf(removal_tactician_datastructures.REM_STRAT_DATA_PROBLEM_STRATEGEMS_SET_ASIDE, removal_tactician_datastructures._CSETF_REM_STRAT_DATA_PROBLEM_STRATEGEMS_SET_ASIDE);
        def_csetf(removal_tactician_datastructures.REM_STRAT_DATA_PROBLEM_STRATEGEMS_THROWN_AWAY, removal_tactician_datastructures._CSETF_REM_STRAT_DATA_PROBLEM_STRATEGEMS_THROWN_AWAY);
        identity(removal_tactician_datastructures.REMOVAL_STRATEGY_DATA);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), removal_tactician_datastructures.$dtp_removal_strategy_data$.getGlobalValue(), symbol_function(removal_tactician_datastructures.VISIT_DEFSTRUCT_OBJECT_REMOVAL_STRATEGY_DATA_METHOD));
        return NIL;
    }

    @Override
    public void declareFunctions() {
        removal_tactician_datastructures.declare_removal_tactician_datastructures_file();
    }

    @Override
    public void initializeVariables() {
        removal_tactician_datastructures.init_removal_tactician_datastructures_file();
    }

    @Override
    public void runTopLevelForms() {
        removal_tactician_datastructures.setup_removal_tactician_datastructures_file();
    }

    static {
    }

    public static final class $removal_strategy_data_p$UnaryFunction extends UnaryFunction {
        public $removal_strategy_data_p$UnaryFunction() {
            super(extractFunctionNamed("REMOVAL-STRATEGY-DATA-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return removal_tactician_datastructures.removal_strategy_data_p(arg1);
        }
    }
}

/**
 * Total time: 124 ms
 */
