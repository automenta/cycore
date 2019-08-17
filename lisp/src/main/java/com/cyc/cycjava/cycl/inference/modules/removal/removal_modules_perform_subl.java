/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.inference.modules.removal;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.control_vars.$cheap_hl_module_check_cost$;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.eval_in_api;
import com.cyc.cycjava.cycl.mt_vars;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store;
import com.cyc.cycjava.cycl.inference.harness.inference_modules;
import com.cyc.cycjava.cycl.inference.harness.inference_worker;
import com.cyc.cycjava.cycl.inference.modules.preference_modules;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class removal_modules_perform_subl extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new removal_modules_perform_subl();

 public static final String myName = "com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_perform_subl";


    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $default_perform_subl_pos_cost$ = makeSymbol("*DEFAULT-PERFORM-SUBL-POS-COST*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $default_perform_subl_neg_cost$ = makeSymbol("*DEFAULT-PERFORM-SUBL-NEG-COST*");

    @LispMethod(comment = "Internal Constants")
    // Internal Constants
    private static final SubLSymbol $IGNORE_ERRORS_TARGET = makeKeyword("IGNORE-ERRORS-TARGET");

    private static final SubLSymbol IGNORE_ERRORS_HANDLER = makeSymbol("IGNORE-ERRORS-HANDLER", "SUBLISP");





    public static final SubLSymbol $perform_subl_defining_mt$ = makeSymbol("*PERFORM-SUBL-DEFINING-MT*");





    private static final SubLSymbol $REMOVAL_PERFORM_SUBL_POS = makeKeyword("REMOVAL-PERFORM-SUBL-POS");

    static private final SubLList $list10 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell("performSubL"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("performSubL"), makeKeyword("FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-PERFORM-SUBL-POS-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("CHECK"), T, makeKeyword("INPUT-EXTRACT-PATTERN"), list(makeKeyword("TEMPLATE"), list(reader_make_constant_shell("performSubL"), list($BIND, makeSymbol("SUBL"))), list(makeKeyword("VALUE"), makeSymbol("SUBL"))), makeKeyword("OUTPUT-CHECK-PATTERN"), list($CALL, makeSymbol("INFERENCE-PERFORM-SUBL"), makeKeyword("INPUT")), makeKeyword("SUPPORT-MODULE"), $CODE, makeKeyword("SUPPORT-MT"), makeSymbol("*PERFORM-SUBL-DEFINING-MT*"), makeKeyword("SUPPORT-STRENGTH"), makeKeyword("MONOTONIC"), makeKeyword("DOCUMENTATION"), makeString("(#$performSubL <fully-bound>)\n     by evaluating the argument as a SubL expression and succeeding if the evaluation completed."), makeKeyword("EXAMPLE"), makeString("(#$performSubL (#$ExpandSubLFn () (print :DONE)))  \n    or\n    (#$performSubL (#$SubLQuoteFn (print :DONE)))") });

    private static final SubLSymbol $REMOVAL_PERFORM_SUBL_NEG = makeKeyword("REMOVAL-PERFORM-SUBL-NEG");

    private static final SubLList $list12 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("NEG"), makeKeyword("PREDICATE"), reader_make_constant_shell("performSubL"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("performSubL"), makeKeyword("FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-PERFORM-SUBL-NEG-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("CHECK"), T, makeKeyword("INPUT-EXTRACT-PATTERN"), list(makeKeyword("TEMPLATE"), list(reader_make_constant_shell("performSubL"), list($BIND, makeSymbol("SUBL"))), list(makeKeyword("VALUE"), makeSymbol("SUBL"))), makeKeyword("OUTPUT-CHECK-PATTERN"), list($CALL, makeSymbol("INVERT-BOOLEAN-ANSWER"), list($CALL, makeSymbol("INFERENCE-PERFORM-SUBL"), makeKeyword("INPUT"))), makeKeyword("SUPPORT-MODULE"), $CODE, makeKeyword("SUPPORT-MT"), makeSymbol("*PERFORM-SUBL-DEFINING-MT*"), makeKeyword("SUPPORT-STRENGTH"), makeKeyword("MONOTONIC"), makeKeyword("DOCUMENTATION"), makeString("(#$not (#$performSubL <fully-bound>))\n by evaluating the argument as a SubL expression and succeeding if the evaluation failed to complete."), makeKeyword("EXAMPLE"), makeString("(#$not (#$performSubL (#$ExpandSubLFn () (/ 1 0)))) or\n    (#$not (#$performSubL (#$SubLQuoteFn (/ 1 0))))") });

    // Definitions
    public static final SubLObject evaluate_subl_expression_alt(SubLObject subl_expression) {
        {
            SubLObject result = NIL;
            SubLObject subl = NIL;
            SubLObject successfulP = NIL;
            SubLObject ignore_errors_tag = NIL;
            try {
                {
                    SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                    try {
                        bind(Errors.$error_handler$, symbol_function(IGNORE_ERRORS_HANDLER));
                        try {
                            {
                                SubLObject pcase_var = cycl_utilities.formula_operator(subl_expression);
                                if (pcase_var.eql($$ExpandSubLFn)) {
                                    subl = cycl_utilities.formula_arg2(subl_expression, UNPROVIDED);
                                } else {
                                    if (pcase_var.eql($$SubLQuoteFn)) {
                                        subl = cycl_utilities.formula_arg1(subl_expression, UNPROVIDED);
                                    }
                                }
                            }
                            result = eval_in_api.cyc_api_eval(subl);
                            successfulP = T;
                        } catch (Throwable catch_var) {
                            Errors.handleThrowable(catch_var, NIL);
                        }
                    } finally {
                        rebind(Errors.$error_handler$, _prev_bind_0);
                    }
                }
            } catch (Throwable ccatch_env_var) {
                ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
            }
            return values(result, successfulP);
        }
    }

    // Definitions
    public static SubLObject evaluate_subl_expression(final SubLObject subl_expression) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        SubLObject subl = NIL;
        SubLObject successfulP = NIL;
        SubLObject ignore_errors_tag = NIL;
        try {
            thread.throwStack.push($IGNORE_ERRORS_TARGET);
            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
            try {
                Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
                try {
                    final SubLObject pcase_var = cycl_utilities.formula_operator(subl_expression);
                    if (pcase_var.eql($$ExpandSubLFn)) {
                        subl = cycl_utilities.formula_arg2(subl_expression, UNPROVIDED);
                    } else
                        if (pcase_var.eql($$SubLQuoteFn)) {
                            subl = cycl_utilities.formula_arg1(subl_expression, UNPROVIDED);
                        }

                    result = eval_in_api.cyc_api_eval(subl);
                    successfulP = T;
                } catch (final Throwable catch_var) {
                    Errors.handleThrowable(catch_var, NIL);
                }
            } finally {
                Errors.$error_handler$.rebind(_prev_bind_0, thread);
            }
        } catch (final Throwable ccatch_env_var) {
            ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
        } finally {
            thread.throwStack.pop();
        }
        return values(result, successfulP);
    }

    public static final SubLObject inference_perform_subl_alt(SubLObject subl_expression) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject result = com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_perform_subl.inference_evaluate_subl_expression(subl_expression);
                SubLObject successfulP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                return successfulP;
            }
        }
    }

    public static SubLObject inference_perform_subl(final SubLObject subl_expression) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject result = inference_evaluate_subl_expression(subl_expression);
        final SubLObject successfulP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        return successfulP;
    }

    static private final SubLList $list_alt8 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell("performSubL"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("performSubL"), makeKeyword("FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-PERFORM-SUBL-POS-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("CHECK"), T, makeKeyword("INPUT-EXTRACT-PATTERN"), list(makeKeyword("TEMPLATE"), list(reader_make_constant_shell("performSubL"), list($BIND, makeSymbol("SUBL"))), list(makeKeyword("VALUE"), makeSymbol("SUBL"))), makeKeyword("OUTPUT-CHECK-PATTERN"), list($CALL, makeSymbol("INFERENCE-PERFORM-SUBL"), makeKeyword("INPUT")), makeKeyword("SUPPORT-MODULE"), $CODE, makeKeyword("SUPPORT-MT"), makeSymbol("*PERFORM-SUBL-DEFINING-MT*"), makeKeyword("SUPPORT-STRENGTH"), makeKeyword("MONOTONIC"), makeKeyword("DOCUMENTATION"), makeString("(#$performSubL <fully-bound>)\n     by evaluating the argument as a SubL expression and succeeding if the evaluation completed."), makeKeyword("EXAMPLE"), makeString("(#$performSubL (#$ExpandSubLFn () (print :DONE)))  \n    or\n    (#$performSubL (#$SubLQuoteFn (print :DONE)))") });

    public static final SubLObject inference_evaluate_subl_expression_alt(SubLObject subl_expression) {
        if ((NIL == inference_worker.currently_active_problem_store()) || (NIL != inference_datastructures_problem_store.problem_store_evaluate_subl_allowedP(inference_worker.currently_active_problem_store()))) {
            return com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_perform_subl.evaluate_subl_expression(subl_expression);
        } else {
            return values(NIL, NIL);
        }
    }

    public static SubLObject inference_evaluate_subl_expression(final SubLObject subl_expression) {
        if ((NIL == inference_worker.currently_active_problem_store()) || (NIL != inference_datastructures_problem_store.problem_store_evaluate_subl_allowedP(inference_worker.currently_active_problem_store()))) {
            return evaluate_subl_expression(subl_expression);
        }
        return values(NIL, NIL);
    }

    public static SubLObject declare_removal_modules_perform_subl_file() {
        declareFunction("evaluate_subl_expression", "EVALUATE-SUBL-EXPRESSION", 1, 0, false);
        declareFunction("inference_perform_subl", "INFERENCE-PERFORM-SUBL", 1, 0, false);
        declareFunction("inference_evaluate_subl_expression", "INFERENCE-EVALUATE-SUBL-EXPRESSION", 1, 0, false);
        return NIL;
    }

    public static final SubLObject init_removal_modules_perform_subl_file_alt() {
        deflexical("*PERFORM-SUBL-DEFINING-MT*", NIL != boundp($perform_subl_defining_mt$) ? ((SubLObject) ($perform_subl_defining_mt$.getGlobalValue())) : $$UniversalVocabularyMt);
        defparameter("*DEFAULT-PERFORM-SUBL-POS-COST*", $cheap_hl_module_check_cost$.getGlobalValue());
        defparameter("*DEFAULT-PERFORM-SUBL-NEG-COST*", $default_perform_subl_pos_cost$.getDynamicValue());
        return NIL;
    }

    public static SubLObject init_removal_modules_perform_subl_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*PERFORM-SUBL-DEFINING-MT*", SubLTrampolineFile.maybeDefault($perform_subl_defining_mt$, $perform_subl_defining_mt$, $$UniversalVocabularyMt));
            defparameter("*DEFAULT-PERFORM-SUBL-POS-COST*", $cheap_hl_module_check_cost$.getGlobalValue());
            defparameter("*DEFAULT-PERFORM-SUBL-NEG-COST*", $default_perform_subl_pos_cost$.getDynamicValue());
        }
        if (SubLFiles.USE_V2) {
            deflexical("*PERFORM-SUBL-DEFINING-MT*", NIL != boundp($perform_subl_defining_mt$) ? ((SubLObject) ($perform_subl_defining_mt$.getGlobalValue())) : $$UniversalVocabularyMt);
        }
        return NIL;
    }

    public static SubLObject init_removal_modules_perform_subl_file_Previous() {
        deflexical("*PERFORM-SUBL-DEFINING-MT*", SubLTrampolineFile.maybeDefault($perform_subl_defining_mt$, $perform_subl_defining_mt$, $$UniversalVocabularyMt));
        defparameter("*DEFAULT-PERFORM-SUBL-POS-COST*", $cheap_hl_module_check_cost$.getGlobalValue());
        defparameter("*DEFAULT-PERFORM-SUBL-NEG-COST*", $default_perform_subl_pos_cost$.getDynamicValue());
        return NIL;
    }

    static private final SubLList $list_alt10 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("NEG"), makeKeyword("PREDICATE"), reader_make_constant_shell("performSubL"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("performSubL"), makeKeyword("FULLY-BOUND")), makeKeyword("COST-EXPRESSION"), makeSymbol("*DEFAULT-PERFORM-SUBL-NEG-COST*"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("CHECK"), T, makeKeyword("INPUT-EXTRACT-PATTERN"), list(makeKeyword("TEMPLATE"), list(reader_make_constant_shell("performSubL"), list($BIND, makeSymbol("SUBL"))), list(makeKeyword("VALUE"), makeSymbol("SUBL"))), makeKeyword("OUTPUT-CHECK-PATTERN"), list($CALL, makeSymbol("INVERT-BOOLEAN-ANSWER"), list($CALL, makeSymbol("INFERENCE-PERFORM-SUBL"), makeKeyword("INPUT"))), makeKeyword("SUPPORT-MODULE"), $CODE, makeKeyword("SUPPORT-MT"), makeSymbol("*PERFORM-SUBL-DEFINING-MT*"), makeKeyword("SUPPORT-STRENGTH"), makeKeyword("MONOTONIC"), makeKeyword("DOCUMENTATION"), makeString("(#$not (#$performSubL <fully-bound>))\n by evaluating the argument as a SubL expression and succeeding if the evaluation failed to complete."), makeKeyword("EXAMPLE"), makeString("(#$not (#$performSubL (#$ExpandSubLFn () (/ 1 0)))) or\n    (#$not (#$performSubL (#$SubLQuoteFn (/ 1 0))))") });

    public static final SubLObject setup_removal_modules_perform_subl_file_alt() {
        declare_defglobal($perform_subl_defining_mt$);
        mt_vars.note_mt_var($perform_subl_defining_mt$, $$performSubL);
        inference_modules.register_solely_specific_removal_module_predicate($$performSubL);
        inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_POS, $list_alt8);
        inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_NEG, $list_alt10);
        return NIL;
    }

    public static SubLObject setup_removal_modules_perform_subl_file() {
        if (SubLFiles.USE_V1) {
            declare_defglobal($perform_subl_defining_mt$);
            mt_vars.note_mt_var($perform_subl_defining_mt$, $$performSubL);
            inference_modules.register_solely_specific_removal_module_predicate($$performSubL);
            preference_modules.doomed_unless_all_args_bindable($POS, $$performSubL);
            preference_modules.doomed_unless_all_args_bindable($NEG, $$performSubL);
            inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_POS, $list10);
            inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_NEG, $list12);
        }
        if (SubLFiles.USE_V2) {
            inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_POS, $list_alt8);
            inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_NEG, $list_alt10);
        }
        return NIL;
    }

    public static SubLObject setup_removal_modules_perform_subl_file_Previous() {
        declare_defglobal($perform_subl_defining_mt$);
        mt_vars.note_mt_var($perform_subl_defining_mt$, $$performSubL);
        inference_modules.register_solely_specific_removal_module_predicate($$performSubL);
        preference_modules.doomed_unless_all_args_bindable($POS, $$performSubL);
        preference_modules.doomed_unless_all_args_bindable($NEG, $$performSubL);
        inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_POS, $list10);
        inference_modules.inference_removal_module($REMOVAL_PERFORM_SUBL_NEG, $list12);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_removal_modules_perform_subl_file();
    }

    @Override
    public void initializeVariables() {
        init_removal_modules_perform_subl_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_removal_modules_perform_subl_file();
    }

    static {
    }
}

/**
 * Total time: 137 ms
 */
