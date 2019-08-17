/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.inference.modules.removal;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.control_vars.$expensive_hl_module_check_cost$;
import static com.cyc.cycjava.cycl.el_utilities.atomic_sentenceP;
import static com.cyc.cycjava.cycl.el_utilities.el_conjunction_p;
import static com.cyc.cycjava.cycl.el_utilities.make_negation;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.arguments;
import com.cyc.cycjava.cycl.backward;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.variables;
import com.cyc.cycjava.cycl.inference.ask_utilities;
import com.cyc.cycjava.cycl.inference.harness.inference_czer;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference;
import com.cyc.cycjava.cycl.inference.harness.inference_macros;
import com.cyc.cycjava.cycl.inference.harness.inference_modules;
import com.cyc.cycjava.cycl.inference.harness.inference_utilities;
import com.cyc.cycjava.cycl.inference.harness.inference_worker;
import com.cyc.cycjava.cycl.sksi.query_sks.sksi_conjunctive_removal_modules_cost;
import com.cyc.cycjava.cycl.sksi.query_sks.sksi_query_utilities;
import com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_infrastructure_utilities;
import com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_macros;
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
 * module:      REMOVAL-MODULES-SOURCE-SENTENCE
 * source file: /cyc/top/cycl/inference/modules/removal/removal-modules-source-sentence.lisp
 * created:     2019/07/03 17:37:47
 */
public final class removal_modules_source_sentence extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new removal_modules_source_sentence();

 public static final String myName = "com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_source_sentence";


    // deflexical
    // Definitions
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $default_source_sentence_check_cost$ = makeSymbol("*DEFAULT-SOURCE-SENTENCE-CHECK-COST*");



    private static final SubLSymbol $REMOVAL_SOURCE_SENTENCE_LOOKUP_POS = makeKeyword("REMOVAL-SOURCE-SENTENCE-LOOKUP-POS");

    static private final SubLList $list2 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell("sourceSentence"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("sourceSentence"), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), cons(makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND")), cons(list($TEST, makeSymbol("INFERENCE-PREDICATE-P")), makeKeyword("ANYTHING")), cons(reader_make_constant_shell("and"), makeKeyword("ANYTHING")))), $COST, makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-COST"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$sourceSentence <SOURCE> \n                      (:or\n                        (:fully-bound . :fully-bound)\n                        ((:test inference-predicate-p) . :anything)\n                        (#$and . :anything)\n                      ))"), makeKeyword("EXAMPLE"), makeString("(#$sourceSentence #$NWS-KS (#$weather #$CityOfLosAngelesCA ?WHAT))") });



    private static final SubLSymbol $BINDINGS_AND_SUPPORTS = makeKeyword("BINDINGS-AND-SUPPORTS");

    static private final SubLList $list9 = list(makeSymbol("BINDINGS"), makeSymbol("SUPPORTS"));

    private static final SubLSymbol $REMOVAL_SOURCE_SENTENCE_CHECK_NEG = makeKeyword("REMOVAL-SOURCE-SENTENCE-CHECK-NEG");

    static private final SubLList $list11 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("NEG"), makeKeyword("PREDICATE"), reader_make_constant_shell("sourceSentence"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("sourceSentence"), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), cons(makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND")), cons(list($TEST, makeSymbol("INFERENCE-PREDICATE-P")), makeKeyword("FULLY-BOUND")))), $COST, makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-NEG-COST"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-NEG-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$not (#$sourceSentence <fully-bound> <fully-bound>))"), makeKeyword("EXAMPLE"), makeString("(#$not (#$sourceSentence #$NWS-KS (#$weather #$CityOfLosAngelesCA #$Snowy)))") });

    public static final SubLObject removal_source_sentence_lookup_pos_cost_alt(SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        {
            SubLObject source = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
            SubLObject subsentence = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
            return com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_source_sentence.removal_source_sentence_lookup_pos_cost_int(source, subsentence);
        }
    }

    public static SubLObject removal_source_sentence_lookup_pos_cost(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLObject source = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject subsentence = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        return removal_source_sentence_lookup_pos_cost_int(source, subsentence);
    }

    public static final SubLObject removal_source_sentence_lookup_pos_cost_int_alt(SubLObject source, SubLObject sentence) {
        if (NIL != variables.fully_bound_p(sentence)) {
            return $default_source_sentence_check_cost$.getGlobalValue();
        } else {
            if (NIL != atomic_sentenceP(sentence)) {
                if (source == $$TheCurrentCycKB) {
                    return inference_utilities.literal_removal_cost(sentence, $POS, UNPROVIDED, UNPROVIDED);
                } else {
                    return sksi_query_utilities.sksi_sentence_cost(sentence, $POS, source);
                }
            } else {
                if (NIL != el_conjunction_p(sentence)) {
                    if (source == $$TheCurrentCycKB) {
                        return removal_modules_kb_sentence.removal_kb_sentence_lookup_pos_conjunction_cost(sentence);
                    } else {
                        {
                            SubLObject mt = inference_datastructures_inference.inference_mt(inference_macros.current_controlling_inference());
                            SubLObject contextualized_dnf_clause = inference_czer.inference_canonicalize_ask_memoized(sentence, mt, NIL).first();
                            SubLObject cost = sksi_conjunctive_removal_modules_cost.sksi_query_cost_contextualized_dnf(contextualized_dnf_clause, source);
                            return cost;
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject removal_source_sentence_lookup_pos_cost_int(SubLObject source, final SubLObject sentence) {
        if (NIL != variables.fully_bound_p(sentence)) {
            return $default_source_sentence_check_cost$.getGlobalValue();
        }
        if (NIL != atomic_sentenceP(sentence)) {
            if (source.eql($$TheCurrentCycKB)) {
                return inference_utilities.literal_removal_cost(sentence, $POS, UNPROVIDED, UNPROVIDED);
            }
            return sksi_query_utilities.sksi_sentence_cost(sentence, $POS, source);
        } else {
            if (NIL == el_conjunction_p(sentence)) {
                return NIL;
            }
            if (source.eql($$TheCurrentCycKB)) {
                return removal_modules_kb_sentence.removal_kb_sentence_lookup_pos_conjunction_cost(sentence);
            }
            final SubLObject mt = inference_datastructures_inference.inference_mt(inference_macros.current_controlling_inference());
            final SubLObject contextualized_dnf_clause = inference_czer.inference_canonicalize_ask_memoized(sentence, mt, NIL).first();
            final SubLObject cost = sksi_conjunctive_removal_modules_cost.sksi_query_cost_contextualized_dnf(contextualized_dnf_clause, source);
            return cost;
        }
    }

    public static final SubLObject removal_source_sentence_lookup_pos_expand_alt(SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        {
            SubLObject source = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
            SubLObject subsentence = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
            SubLObject mt = inference_worker.mt_of_currently_executing_tactic();
            if (source == $$TheCurrentCycKB) {
                return removal_modules_kb_sentence.removal_kb_sentence_lookup_pos_expand_subsentence(subsentence, mt, sense);
            } else {
                return com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_source_sentence.removal_source_sentence_lookup_pos_expand_int(source, subsentence, mt, sense);
            }
        }
    }

    public static SubLObject removal_source_sentence_lookup_pos_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLObject source = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject subsentence = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject mt = inference_worker.mt_of_currently_executing_tactic();
        if (source.eql($$TheCurrentCycKB)) {
            return removal_modules_kb_sentence.removal_kb_sentence_lookup_pos_expand_subsentence(subsentence, mt, sense);
        }
        return removal_source_sentence_lookup_pos_expand_int(source, subsentence, mt, sense);
    }

    public static final SubLObject removal_source_sentence_lookup_pos_expand_int_alt(SubLObject sks, SubLObject subsentence, SubLObject mt, SubLObject sense) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject allowed_modules_list = sksi_infrastructure_utilities.sks_to_allowed_modules_list(sks);
                {
                    SubLObject _prev_bind_0 = sksi_macros.$sksi_default_ms_type_check_strength$.currentBinding(thread);
                    try {
                        sksi_macros.$sksi_default_ms_type_check_strength$.bind($STRICT, thread);
                        {
                            SubLObject result = ask_utilities.inference_recursive_query(subsentence, mt, list($RETURN, $BINDINGS_AND_SUPPORTS, $ALLOWED_MODULES, allowed_modules_list));
                            SubLObject cdolist_list_var = result;
                            SubLObject bindings_and_supports = NIL;
                            for (bindings_and_supports = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , bindings_and_supports = cdolist_list_var.first()) {
                                {
                                    SubLObject datum = bindings_and_supports;
                                    SubLObject current = datum;
                                    SubLObject v_bindings = NIL;
                                    SubLObject supports = NIL;
                                    destructuring_bind_must_consp(current, datum, $list_alt9);
                                    v_bindings = current.first();
                                    current = current.rest();
                                    destructuring_bind_must_consp(current, datum, $list_alt9);
                                    supports = current.first();
                                    current = current.rest();
                                    if (NIL == current) {
                                        backward.removal_add_node(supports.first(), v_bindings, supports.rest());
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt9);
                                    }
                                }
                            }
                        }
                    } finally {
                        sksi_macros.$sksi_default_ms_type_check_strength$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject removal_source_sentence_lookup_pos_expand_int(final SubLObject sks, final SubLObject subsentence, final SubLObject mt, final SubLObject sense) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject allowed_modules_list = sksi_infrastructure_utilities.sks_to_allowed_modules_list(sks);
        final SubLObject _prev_bind_0 = sksi_macros.$sksi_default_ms_type_check_strength$.currentBinding(thread);
        try {
            sksi_macros.$sksi_default_ms_type_check_strength$.bind($STRICT, thread);
            SubLObject cdolist_list_var;
            final SubLObject result = cdolist_list_var = ask_utilities.inference_recursive_query(subsentence, mt, list($RETURN, $BINDINGS_AND_SUPPORTS, $ALLOWED_MODULES, allowed_modules_list));
            SubLObject bindings_and_supports = NIL;
            bindings_and_supports = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject current;
                final SubLObject datum = current = bindings_and_supports;
                SubLObject v_bindings = NIL;
                SubLObject supports = NIL;
                destructuring_bind_must_consp(current, datum, $list9);
                v_bindings = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list9);
                supports = current.first();
                current = current.rest();
                if (NIL == current) {
                    backward.removal_add_node(supports.first(), v_bindings, supports.rest());
                } else {
                    cdestructuring_bind_error(datum, $list9);
                }
                cdolist_list_var = cdolist_list_var.rest();
                bindings_and_supports = cdolist_list_var.first();
            } 
        } finally {
            sksi_macros.$sksi_default_ms_type_check_strength$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static final SubLObject removal_source_sentence_lookup_neg_cost_alt(SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        return $default_source_sentence_check_cost$.getGlobalValue();
    }

    public static SubLObject removal_source_sentence_lookup_neg_cost(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        return $default_source_sentence_check_cost$.getGlobalValue();
    }

    public static final SubLObject removal_source_sentence_lookup_neg_expand_alt(SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        {
            SubLObject source = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
            SubLObject subsentence = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
            SubLObject mt = inference_worker.mt_of_currently_executing_tactic();
            if (source == $$TheCurrentCycKB) {
                return removal_modules_kb_sentence.removal_kb_sentence_check_neg_expand_subsentence(subsentence, mt, sense);
            } else {
                return com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_source_sentence.removal_source_sentence_lookup_neg_expand_int(source, subsentence, mt, sense);
            }
        }
    }

    public static SubLObject removal_source_sentence_lookup_neg_expand(final SubLObject asent, SubLObject sense) {
        if (sense == UNPROVIDED) {
            sense = NIL;
        }
        final SubLObject source = cycl_utilities.atomic_sentence_arg1(asent, UNPROVIDED);
        final SubLObject subsentence = cycl_utilities.atomic_sentence_arg2(asent, UNPROVIDED);
        final SubLObject mt = inference_worker.mt_of_currently_executing_tactic();
        if (source.eql($$TheCurrentCycKB)) {
            return removal_modules_kb_sentence.removal_kb_sentence_check_neg_expand_subsentence(subsentence, mt, sense);
        }
        return removal_source_sentence_lookup_neg_expand_int(source, subsentence, mt, sense);
    }

    public static final SubLObject removal_source_sentence_lookup_neg_expand_int_alt(SubLObject sks, SubLObject subsentence, SubLObject mt, SubLObject sense) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject allowed_modules_list = sksi_infrastructure_utilities.sks_to_allowed_modules_list(sks);
                {
                    SubLObject _prev_bind_0 = sksi_macros.$sksi_default_ms_type_check_strength$.currentBinding(thread);
                    try {
                        sksi_macros.$sksi_default_ms_type_check_strength$.bind($STRICT, thread);
                        {
                            SubLObject result = ask_utilities.inference_recursive_query(subsentence, mt, list($ALLOWED_MODULES, allowed_modules_list));
                            if (NIL == result) {
                                backward.removal_add_node(arguments.make_hl_support($OPAQUE, make_negation(subsentence), mt, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                            }
                        }
                    } finally {
                        sksi_macros.$sksi_default_ms_type_check_strength$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject removal_source_sentence_lookup_neg_expand_int(final SubLObject sks, final SubLObject subsentence, final SubLObject mt, final SubLObject sense) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject allowed_modules_list = sksi_infrastructure_utilities.sks_to_allowed_modules_list(sks);
        final SubLObject _prev_bind_0 = sksi_macros.$sksi_default_ms_type_check_strength$.currentBinding(thread);
        try {
            sksi_macros.$sksi_default_ms_type_check_strength$.bind($STRICT, thread);
            final SubLObject result = ask_utilities.inference_recursive_query(subsentence, mt, list($ALLOWED_MODULES, allowed_modules_list));
            if (NIL == result) {
                backward.removal_add_node(arguments.make_hl_support($OPAQUE, make_negation(subsentence), mt, UNPROVIDED), UNPROVIDED, UNPROVIDED);
            }
        } finally {
            sksi_macros.$sksi_default_ms_type_check_strength$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static SubLObject declare_removal_modules_source_sentence_file() {
        declareFunction("removal_source_sentence_lookup_pos_cost", "REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-COST", 1, 1, false);
        declareFunction("removal_source_sentence_lookup_pos_cost_int", "REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-COST-INT", 2, 0, false);
        declareFunction("removal_source_sentence_lookup_pos_expand", "REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-EXPAND", 1, 1, false);
        declareFunction("removal_source_sentence_lookup_pos_expand_int", "REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-EXPAND-INT", 4, 0, false);
        declareFunction("removal_source_sentence_lookup_neg_cost", "REMOVAL-SOURCE-SENTENCE-LOOKUP-NEG-COST", 1, 1, false);
        declareFunction("removal_source_sentence_lookup_neg_expand", "REMOVAL-SOURCE-SENTENCE-LOOKUP-NEG-EXPAND", 1, 1, false);
        declareFunction("removal_source_sentence_lookup_neg_expand_int", "REMOVAL-SOURCE-SENTENCE-LOOKUP-NEG-EXPAND-INT", 4, 0, false);
        return NIL;
    }

    static private final SubLList $list_alt2 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("POS"), makeKeyword("PREDICATE"), reader_make_constant_shell("sourceSentence"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("sourceSentence"), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), cons(makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND")), cons(list($TEST, makeSymbol("INFERENCE-PREDICATE-P")), makeKeyword("ANYTHING")), cons(reader_make_constant_shell("and"), makeKeyword("ANYTHING")))), $COST, makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-COST"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-POS-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$sourceSentence <SOURCE> \n                      (:or\n                        (:fully-bound . :fully-bound)\n                        ((:test inference-predicate-p) . :anything)\n                        (#$and . :anything)\n                      ))"), makeKeyword("EXAMPLE"), makeString("(#$sourceSentence #$NWS-KS (#$weather #$CityOfLosAngelesCA ?WHAT))") });

    public static SubLObject init_removal_modules_source_sentence_file() {
        deflexical("*DEFAULT-SOURCE-SENTENCE-CHECK-COST*", $expensive_hl_module_check_cost$.getGlobalValue());
        return NIL;
    }

    public static SubLObject setup_removal_modules_source_sentence_file() {
        inference_modules.register_solely_specific_removal_module_predicate($$sourceSentence);
        inference_modules.inference_removal_module($REMOVAL_SOURCE_SENTENCE_LOOKUP_POS, $list2);
        inference_modules.inference_removal_module($REMOVAL_SOURCE_SENTENCE_CHECK_NEG, $list11);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_removal_modules_source_sentence_file();
    }

    @Override
    public void initializeVariables() {
        init_removal_modules_source_sentence_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_removal_modules_source_sentence_file();
    }

    static {
    }

    static private final SubLList $list_alt9 = list(makeSymbol("BINDINGS"), makeSymbol("SUPPORTS"));

    static private final SubLList $list_alt11 = list(new SubLObject[]{ makeKeyword("SENSE"), makeKeyword("NEG"), makeKeyword("PREDICATE"), reader_make_constant_shell("sourceSentence"), makeKeyword("REQUIRED-PATTERN"), list(reader_make_constant_shell("sourceSentence"), makeKeyword("FULLY-BOUND"), list(makeKeyword("OR"), cons(makeKeyword("FULLY-BOUND"), makeKeyword("FULLY-BOUND")), cons(list($TEST, makeSymbol("INFERENCE-PREDICATE-P")), makeKeyword("FULLY-BOUND")))), $COST, makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-NEG-COST"), makeKeyword("COMPLETENESS"), makeKeyword("COMPLETE"), makeKeyword("EXPAND"), makeSymbol("REMOVAL-SOURCE-SENTENCE-LOOKUP-NEG-EXPAND"), makeKeyword("DOCUMENTATION"), makeString("(#$not (#$sourceSentence <fully-bound> <fully-bound>))"), makeKeyword("EXAMPLE"), makeString("(#$not (#$sourceSentence #$NWS-KS (#$weather #$CityOfLosAngelesCA #$Snowy)))") });
}

/**
 * Total time: 95 ms
 */
