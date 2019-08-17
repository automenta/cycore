/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.control_vars.$rkf_mt$;
import static com.cyc.cycjava.cycl.control_vars.$within_assert$;
import static com.cyc.cycjava.cycl.el_utilities.gafP;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;

import com.cyc.cycjava.cycl.inference.harness.inference_kernel;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      RKF-REDUNDANCY-DETECTOR
 * source file: /cyc/top/cycl/rkf-redundancy-detector.lisp
 * created:     2019/07/03 17:37:59
 */
public final class rkf_redundancy_detector extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new rkf_redundancy_detector();

 public static final String myName = "com.cyc.cycjava.cycl.rkf_redundancy_detector";


    private static final SubLSymbol $kw2$CACHE_INFERENCE_RESULTS_ = makeKeyword("CACHE-INFERENCE-RESULTS?");

    private static final SubLSymbol $BINDINGS_AND_SUPPORTS = makeKeyword("BINDINGS-AND-SUPPORTS");

    private static final SubLSymbol $MAX_TRANSFORMATION_DEPTH = makeKeyword("MAX-TRANSFORMATION-DEPTH");

    static private final SubLList $list10 = list(makeSymbol("BINDINGS"), makeSymbol("SUPPORTS"));



    // Definitions
    public static final SubLObject rkf_redundant_inference_properties_alt(SubLObject backchain, SubLObject number, SubLObject time) {
        return list(new SubLObject[]{ $ANSWER_LANGUAGE, $HL, $kw2$CACHE_INFERENCE_RESULTS_, NIL, $RETURN, $BINDINGS_AND_SUPPORTS, $RESULT_UNIQUENESS, $PROOF, $MAX_NUMBER, number, $MAX_TIME, time, $MAX_TRANSFORMATION_DEPTH, backchain });
    }

    // Definitions
    public static SubLObject rkf_redundant_inference_properties(final SubLObject backchain, final SubLObject number, final SubLObject time) {
        return list(new SubLObject[]{ $ANSWER_LANGUAGE, $HL, $kw2$CACHE_INFERENCE_RESULTS_, NIL, $RETURN, $BINDINGS_AND_SUPPORTS, $RESULT_UNIQUENESS, $PROOF, $MAX_NUMBER, number, $MAX_TIME, time, $MAX_TRANSFORMATION_DEPTH, backchain });
    }

    /**
     *
     *
     * @return LISTP; a list of sorted lists of supports
     */
    @LispMethod(comment = "@return LISTP; a list of sorted lists of supports")
    public static final SubLObject rkf_redundant_alt(SubLObject formula, SubLObject mt, SubLObject number, SubLObject time) {
        if (mt == UNPROVIDED) {
            mt = $rkf_mt$.getDynamicValue();
        }
        if (number == UNPROVIDED) {
            number = ONE_INTEGER;
        }
        if (time == UNPROVIDED) {
            time = TEN_INTEGER;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == gafP(formula)) {
                return NIL;
            }
            {
                SubLObject v_arguments = NIL;
                {
                    SubLObject _prev_bind_0 = $within_assert$.currentBinding(thread);
                    SubLObject _prev_bind_1 = wff_utilities.$check_arg_typesP$.currentBinding(thread);
                    SubLObject _prev_bind_2 = at_vars.$at_check_arg_typesP$.currentBinding(thread);
                    SubLObject _prev_bind_3 = wff_utilities.$check_wff_semanticsP$.currentBinding(thread);
                    SubLObject _prev_bind_4 = wff_utilities.$check_wff_coherenceP$.currentBinding(thread);
                    SubLObject _prev_bind_5 = wff_utilities.$check_var_typesP$.currentBinding(thread);
                    SubLObject _prev_bind_6 = czer_vars.$simplify_literalP$.currentBinding(thread);
                    SubLObject _prev_bind_7 = at_vars.$at_check_relator_constraintsP$.currentBinding(thread);
                    SubLObject _prev_bind_8 = at_vars.$at_check_arg_formatP$.currentBinding(thread);
                    SubLObject _prev_bind_9 = wff_vars.$validate_constantsP$.currentBinding(thread);
                    SubLObject _prev_bind_10 = system_parameters.$suspend_sbhl_type_checkingP$.currentBinding(thread);
                    try {
                        $within_assert$.bind(NIL, thread);
                        wff_utilities.$check_arg_typesP$.bind(NIL, thread);
                        at_vars.$at_check_arg_typesP$.bind(NIL, thread);
                        wff_utilities.$check_wff_semanticsP$.bind(NIL, thread);
                        wff_utilities.$check_wff_coherenceP$.bind(NIL, thread);
                        wff_utilities.$check_var_typesP$.bind(NIL, thread);
                        czer_vars.$simplify_literalP$.bind(NIL, thread);
                        at_vars.$at_check_relator_constraintsP$.bind(NIL, thread);
                        at_vars.$at_check_arg_formatP$.bind(NIL, thread);
                        wff_vars.$validate_constantsP$.bind(NIL, thread);
                        system_parameters.$suspend_sbhl_type_checkingP$.bind(T, thread);
                        {
                            SubLObject v_properties = com.cyc.cycjava.cycl.rkf_redundancy_detector.rkf_redundant_inference_properties(ONE_INTEGER, number, time);
                            SubLObject cdolist_list_var = inference_kernel.new_cyc_query(list($$redundantSentence, formula), mt, v_properties);
                            SubLObject result = NIL;
                            for (result = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , result = cdolist_list_var.first()) {
                                {
                                    SubLObject datum = result;
                                    SubLObject current = datum;
                                    SubLObject v_bindings = NIL;
                                    SubLObject supports = NIL;
                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                    v_bindings = current.first();
                                    current = current.rest();
                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                    supports = current.first();
                                    current = current.rest();
                                    if (NIL == current) {
                                        if (NIL != cyc_kernel.closed_query_bindings_p(v_bindings)) {
                                            {
                                                SubLObject item_var = supports;
                                                if (NIL == member(item_var, v_arguments, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                                    v_arguments = cons(item_var, v_arguments);
                                                }
                                            }
                                        }
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt10);
                                    }
                                }
                            }
                        }
                        {
                            SubLObject v_properties = com.cyc.cycjava.cycl.rkf_redundancy_detector.rkf_redundant_inference_properties(ZERO_INTEGER, number, time);
                            SubLObject cdolist_list_var = inference_kernel.new_cyc_query(formula, mt, v_properties);
                            SubLObject result = NIL;
                            for (result = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , result = cdolist_list_var.first()) {
                                {
                                    SubLObject datum = result;
                                    SubLObject current = datum;
                                    SubLObject v_bindings = NIL;
                                    SubLObject supports = NIL;
                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                    v_bindings = current.first();
                                    current = current.rest();
                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                    supports = current.first();
                                    current = current.rest();
                                    if (NIL == current) {
                                        if (NIL != cyc_kernel.closed_query_bindings_p(v_bindings)) {
                                            {
                                                SubLObject item_var = supports;
                                                if (NIL == member(item_var, v_arguments, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                                    v_arguments = cons(item_var, v_arguments);
                                                }
                                            }
                                        }
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt10);
                                    }
                                }
                            }
                        }
                    } finally {
                        system_parameters.$suspend_sbhl_type_checkingP$.rebind(_prev_bind_10, thread);
                        wff_vars.$validate_constantsP$.rebind(_prev_bind_9, thread);
                        at_vars.$at_check_arg_formatP$.rebind(_prev_bind_8, thread);
                        at_vars.$at_check_relator_constraintsP$.rebind(_prev_bind_7, thread);
                        czer_vars.$simplify_literalP$.rebind(_prev_bind_6, thread);
                        wff_utilities.$check_var_typesP$.rebind(_prev_bind_5, thread);
                        wff_utilities.$check_wff_coherenceP$.rebind(_prev_bind_4, thread);
                        wff_utilities.$check_wff_semanticsP$.rebind(_prev_bind_3, thread);
                        at_vars.$at_check_arg_typesP$.rebind(_prev_bind_2, thread);
                        wff_utilities.$check_arg_typesP$.rebind(_prev_bind_1, thread);
                        $within_assert$.rebind(_prev_bind_0, thread);
                    }
                }
                {
                    SubLObject sorted_arguments = NIL;
                    SubLObject cdolist_list_var = v_arguments;
                    SubLObject result = NIL;
                    for (result = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , result = cdolist_list_var.first()) {
                        sorted_arguments = cons(rkf_argument_communicator.rkf_sort_supports(result), sorted_arguments);
                    }
                    return nreverse(sorted_arguments);
                }
            }
        }
    }

    /**
     *
     *
     * @return LISTP; a list of sorted lists of supports
     */
    @LispMethod(comment = "@return LISTP; a list of sorted lists of supports")
    public static SubLObject rkf_redundant(final SubLObject formula, SubLObject mt, SubLObject number, SubLObject time) {
        if (mt == UNPROVIDED) {
            mt = $rkf_mt$.getDynamicValue();
        }
        if (number == UNPROVIDED) {
            number = ONE_INTEGER;
        }
        if (time == UNPROVIDED) {
            time = TEN_INTEGER;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == gafP(formula)) {
            return NIL;
        }
        SubLObject v_arguments = NIL;
        final SubLObject _prev_bind_0 = $within_assert$.currentBinding(thread);
        final SubLObject _prev_bind_2 = wff_utilities.$check_arg_typesP$.currentBinding(thread);
        final SubLObject _prev_bind_3 = at_vars.$at_check_arg_typesP$.currentBinding(thread);
        final SubLObject _prev_bind_4 = wff_utilities.$check_wff_semanticsP$.currentBinding(thread);
        final SubLObject _prev_bind_5 = wff_utilities.$check_wff_coherenceP$.currentBinding(thread);
        final SubLObject _prev_bind_6 = wff_utilities.$check_var_typesP$.currentBinding(thread);
        final SubLObject _prev_bind_7 = czer_vars.$simplify_literalP$.currentBinding(thread);
        final SubLObject _prev_bind_8 = at_vars.$at_check_relator_constraintsP$.currentBinding(thread);
        final SubLObject _prev_bind_9 = at_vars.$at_check_arg_formatP$.currentBinding(thread);
        final SubLObject _prev_bind_10 = wff_vars.$validate_constantsP$.currentBinding(thread);
        final SubLObject _prev_bind_11 = system_parameters.$suspend_sbhl_type_checkingP$.currentBinding(thread);
        try {
            $within_assert$.bind(NIL, thread);
            wff_utilities.$check_arg_typesP$.bind(NIL, thread);
            at_vars.$at_check_arg_typesP$.bind(NIL, thread);
            wff_utilities.$check_wff_semanticsP$.bind(NIL, thread);
            wff_utilities.$check_wff_coherenceP$.bind(NIL, thread);
            wff_utilities.$check_var_typesP$.bind(NIL, thread);
            czer_vars.$simplify_literalP$.bind(NIL, thread);
            at_vars.$at_check_relator_constraintsP$.bind(NIL, thread);
            at_vars.$at_check_arg_formatP$.bind(NIL, thread);
            wff_vars.$validate_constantsP$.bind(NIL, thread);
            system_parameters.$suspend_sbhl_type_checkingP$.bind(T, thread);
            SubLObject v_properties = rkf_redundant_inference_properties(ONE_INTEGER, number, time);
            SubLObject cdolist_list_var = inference_kernel.new_cyc_query(list($$redundantSentence, formula), mt, v_properties);
            SubLObject result = NIL;
            result = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject current;
                final SubLObject datum = current = result;
                SubLObject v_bindings = NIL;
                SubLObject supports = NIL;
                destructuring_bind_must_consp(current, datum, $list10);
                v_bindings = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list10);
                supports = current.first();
                current = current.rest();
                if (NIL == current) {
                    if (NIL != cyc_kernel.closed_query_bindings_p(v_bindings)) {
                        final SubLObject item_var = supports;
                        if (NIL == member(item_var, v_arguments, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                            v_arguments = cons(item_var, v_arguments);
                        }
                    }
                } else {
                    cdestructuring_bind_error(datum, $list10);
                }
                cdolist_list_var = cdolist_list_var.rest();
                result = cdolist_list_var.first();
            } 
            v_properties = rkf_redundant_inference_properties(ZERO_INTEGER, number, time);
            cdolist_list_var = inference_kernel.new_cyc_query(formula, mt, v_properties);
            result = NIL;
            result = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject current;
                final SubLObject datum = current = result;
                SubLObject v_bindings = NIL;
                SubLObject supports = NIL;
                destructuring_bind_must_consp(current, datum, $list10);
                v_bindings = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list10);
                supports = current.first();
                current = current.rest();
                if (NIL == current) {
                    if (NIL != cyc_kernel.closed_query_bindings_p(v_bindings)) {
                        final SubLObject item_var = supports;
                        if (NIL == member(item_var, v_arguments, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                            v_arguments = cons(item_var, v_arguments);
                        }
                    }
                } else {
                    cdestructuring_bind_error(datum, $list10);
                }
                cdolist_list_var = cdolist_list_var.rest();
                result = cdolist_list_var.first();
            } 
        } finally {
            system_parameters.$suspend_sbhl_type_checkingP$.rebind(_prev_bind_11, thread);
            wff_vars.$validate_constantsP$.rebind(_prev_bind_10, thread);
            at_vars.$at_check_arg_formatP$.rebind(_prev_bind_9, thread);
            at_vars.$at_check_relator_constraintsP$.rebind(_prev_bind_8, thread);
            czer_vars.$simplify_literalP$.rebind(_prev_bind_7, thread);
            wff_utilities.$check_var_typesP$.rebind(_prev_bind_6, thread);
            wff_utilities.$check_wff_coherenceP$.rebind(_prev_bind_5, thread);
            wff_utilities.$check_wff_semanticsP$.rebind(_prev_bind_4, thread);
            at_vars.$at_check_arg_typesP$.rebind(_prev_bind_3, thread);
            wff_utilities.$check_arg_typesP$.rebind(_prev_bind_2, thread);
            $within_assert$.rebind(_prev_bind_0, thread);
        }
        SubLObject sorted_arguments = NIL;
        SubLObject cdolist_list_var2 = v_arguments;
        SubLObject result2 = NIL;
        result2 = cdolist_list_var2.first();
        while (NIL != cdolist_list_var2) {
            sorted_arguments = cons(rkf_argument_communicator.rkf_sort_supports(result2), sorted_arguments);
            cdolist_list_var2 = cdolist_list_var2.rest();
            result2 = cdolist_list_var2.first();
        } 
        return nreverse(sorted_arguments);
    }

    public static SubLObject declare_rkf_redundancy_detector_file() {
        declareFunction("rkf_redundant_inference_properties", "RKF-REDUNDANT-INFERENCE-PROPERTIES", 3, 0, false);
        declareFunction("rkf_redundant", "RKF-REDUNDANT", 1, 3, false);
        return NIL;
    }

    public static SubLObject init_rkf_redundancy_detector_file() {
        return NIL;
    }

    public static SubLObject setup_rkf_redundancy_detector_file() {
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_rkf_redundancy_detector_file();
    }

    @Override
    public void initializeVariables() {
        init_rkf_redundancy_detector_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_rkf_redundancy_detector_file();
    }

    static {
    }

    static private final SubLList $list_alt10 = list(makeSymbol("BINDINGS"), makeSymbol("SUPPORTS"));
}

/**
 * Total time: 126 ms
 */
