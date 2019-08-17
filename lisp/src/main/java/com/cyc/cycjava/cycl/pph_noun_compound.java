/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_without_sequence_termP;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.equal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.resetMultipleValues;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.assoc;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import com.cyc.cycjava.cycl.sbhl.sbhl_graphs;
import com.cyc.cycjava.cycl.sbhl.sbhl_link_methods;
import com.cyc.cycjava.cycl.sbhl.sbhl_link_vars;
import com.cyc.cycjava.cycl.sbhl.sbhl_links;
import com.cyc.cycjava.cycl.sbhl.sbhl_macros;
import com.cyc.cycjava.cycl.sbhl.sbhl_marking_utilities;
import com.cyc.cycjava.cycl.sbhl.sbhl_marking_vars;
import com.cyc.cycjava.cycl.sbhl.sbhl_module_utilities;
import com.cyc.cycjava.cycl.sbhl.sbhl_module_vars;
import com.cyc.cycjava.cycl.sbhl.sbhl_paranoia;
import com.cyc.cycjava.cycl.sbhl.sbhl_search_vars;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      PPH-NOUN-COMPOUND
 * source file: /cyc/top/cycl/pph-noun-compound.lisp
 * created:     2019/07/03 17:38:21
 */
public final class pph_noun_compound extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new pph_noun_compound();

 public static final String myName = "com.cyc.cycjava.cycl.pph_noun_compound";


    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $nc_pos_constraint_fn$ = makeSymbol("*NC-POS-CONSTRAINT-FN*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $nc_pos_pred_constraint_fn$ = makeSymbol("*NC-POS-PRED-CONSTRAINT-FN*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $nc_equals_constraint_fn$ = makeSymbol("*NC-EQUALS-CONSTRAINT-FN*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $nc_genls_constraint_fn$ = makeSymbol("*NC-GENLS-CONSTRAINT-FN*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $nc_isa_constraint_fn$ = makeSymbol("*NC-ISA-CONSTRAINT-FN*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $the_nc_modifier$ = makeSymbol("*THE-NC-MODIFIER*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $the_nc_head$ = makeSymbol("*THE-NC-HEAD*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $noun_compound_rule$ = makeSymbol("*NOUN-COMPOUND-RULE*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $nc_rule_template$ = makeSymbol("*NC-RULE-TEMPLATE*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $nc_rule_constraint$ = makeSymbol("*NC-RULE-CONSTRAINT*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $nc_mt$ = makeSymbol("*NC-MT*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol PPH_NC_RULE_LIST = makeSymbol("PPH-NC-RULE-LIST");



    private static final SubLObject $$True_JustificationTruth = reader_make_constant_shell("True-JustificationTruth");

    static private final SubLString $str8$_A_is_not_a__A = makeString("~A is not a ~A");

    static private final SubLString $$$continue_anyway = makeString("continue anyway");

    static private final SubLString $str13$_A_is_not_a_valid__sbhl_type_erro = makeString("~A is not a valid *sbhl-type-error-action* value");

    private static final SubLString $str14$_A_is_neither_SET_P_nor_LISTP_ = makeString("~A is neither SET-P nor LISTP.");

    private static final SubLString $str15$attempting_to_bind_direction_link = makeString("attempting to bind direction link variable, to NIL. macro body not executed.");

    private static final SubLList $list16 = list(makeUninternedSymbol("LINK-NODE"), makeUninternedSymbol("MT"), makeUninternedSymbol("TV"));

    private static final SubLString $str17$Node__a_does_not_pass_sbhl_type_t = makeString("Node ~a does not pass sbhl-type-test ~a~%");

    private static final SubLSymbol $pph_nc_rule_list_caching_state$ = makeSymbol("*PPH-NC-RULE-LIST-CACHING-STATE*");

    static private final SubLList $list19 = list(list(makeSymbol("RULE-VAR"), makeSymbol("TEMPLATE-VAR"), makeSymbol("CONSTRAINTS-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("DONE-VAR")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym20$RULE = makeUninternedSymbol("RULE");

    static private final SubLList $list22 = list(makeSymbol("PPH-NC-RULE-LIST"));























    private static final SubLSymbol GENERATE_NOUN_COMPOUND_PHRASE = makeSymbol("GENERATE-NOUN-COMPOUND-PHRASE");

    private static final SubLSymbol $COLLECTION_DENOTING_NAT = makeKeyword("COLLECTION-DENOTING-NAT");

    private static final SubLSymbol PPH_FIND_NC_RULE_FOR_NAUT = makeSymbol("PPH-FIND-NC-RULE-FOR-NAUT");

    static private final SubLList $list40 = list(makeSymbol("RULE"), makeSymbol("TEMPLATE"), makeSymbol("CONSTRAINTS"));

    static private final SubLList $list41 = list(makeSymbol("HEAD-POS-CONSTRAINT"), makeSymbol("MOD-POS-CONSTRAINT"), makeSymbol("HEAD-POS-PRED-CONSTRAINT"), makeSymbol("MOD-POS-PRED-CONSTRAINT"));



    static private final SubLString $str43$_PPH_error_level_ = makeString("(PPH error level ");

    static private final SubLString $str44$__ = makeString(") ");

    static private final SubLString $str45$___S_isn_t_a_semantic_constraint_ = makeString("~&~S isn't a semantic constraint.~%");



    static private final SubLString $str48$___S_isn_t_a_recognized_noun_comp = makeString("~&~S isn't a recognized noun-compound constraint function.~%");

    public static final SubLObject clear_pph_nc_rule_list_alt() {
        {
            SubLObject cs = $pph_nc_rule_list_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_pph_nc_rule_list() {
        final SubLObject cs = $pph_nc_rule_list_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_pph_nc_rule_list_alt(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $nc_mt$.getGlobalValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($pph_nc_rule_list_caching_state$.getGlobalValue(), list(mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_pph_nc_rule_list(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $nc_mt$.getGlobalValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($pph_nc_rule_list_caching_state$.getGlobalValue(), list(mt), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @unknown macro-helper for do-nc-rules
     */
    @LispMethod(comment = "@unknown macro-helper for do-nc-rules")
    public static final SubLObject pph_nc_rule_list_internal_alt(SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                SubLObject node_var = $noun_compound_rule$.getGlobalValue();
                {
                    SubLObject _prev_bind_0 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                    SubLObject _prev_bind_1 = sbhl_marking_vars.$sbhl_gather_space$.currentBinding(thread);
                    try {
                        sbhl_module_vars.$sbhl_module$.bind(sbhl_module_vars.get_sbhl_module($$isa), thread);
                        sbhl_marking_vars.$sbhl_gather_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
                        {
                            SubLObject node_var_1 = node_var;
                            SubLObject deck_type = (false) ? ((SubLObject) ($QUEUE)) : $STACK;
                            SubLObject recur_deck = deck.create_deck(deck_type);
                            {
                                SubLObject _prev_bind_0_2 = sbhl_marking_vars.$sbhl_space$.currentBinding(thread);
                                try {
                                    sbhl_marking_vars.$sbhl_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
                                    {
                                        SubLObject mt_var = mt;
                                        {
                                            SubLObject _prev_bind_0_3 = mt_relevance_macros.$mt$.currentBinding(thread);
                                            SubLObject _prev_bind_1_4 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                                            SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                                            try {
                                                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                                                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                                                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                                                {
                                                    SubLObject tv_var = $$True_JustificationTruth;
                                                    {
                                                        SubLObject _prev_bind_0_5 = sbhl_search_vars.$sbhl_tv$.currentBinding(thread);
                                                        SubLObject _prev_bind_1_6 = sbhl_search_vars.$relevant_sbhl_tv_function$.currentBinding(thread);
                                                        try {
                                                            sbhl_search_vars.$sbhl_tv$.bind(NIL != tv_var ? ((SubLObject) (tv_var)) : sbhl_search_vars.get_sbhl_true_tv(), thread);
                                                            sbhl_search_vars.$relevant_sbhl_tv_function$.bind(NIL != tv_var ? ((SubLObject) (RELEVANT_SBHL_TV_IS_GENERAL_TV)) : sbhl_search_vars.$relevant_sbhl_tv_function$.getDynamicValue(thread), thread);
                                                            if (NIL != tv_var) {
                                                                if (NIL != sbhl_paranoia.sbhl_object_type_checking_p()) {
                                                                    if (NIL == sbhl_search_vars.sbhl_true_tv_p(tv_var)) {
                                                                        {
                                                                            SubLObject pcase_var = sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread);
                                                                            if (pcase_var.eql($ERROR)) {
                                                                                sbhl_paranoia.sbhl_error(ONE_INTEGER, $str_alt8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                            } else {
                                                                                if (pcase_var.eql($CERROR)) {
                                                                                    sbhl_paranoia.sbhl_cerror(ONE_INTEGER, $$$continue_anyway, $str_alt8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                } else {
                                                                                    if (pcase_var.eql($WARN)) {
                                                                                        Errors.warn($str_alt8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                                                                    } else {
                                                                                        Errors.warn($str_alt13$_A_is_not_a_valid__sbhl_type_erro, sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread));
                                                                                        Errors.cerror($$$continue_anyway, $str_alt8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            {
                                                                SubLObject _prev_bind_0_7 = sbhl_search_vars.$sbhl_search_module$.currentBinding(thread);
                                                                SubLObject _prev_bind_1_8 = sbhl_search_vars.$sbhl_search_module_type$.currentBinding(thread);
                                                                SubLObject _prev_bind_2_9 = sbhl_search_vars.$sbhl_add_node_to_result_test$.currentBinding(thread);
                                                                SubLObject _prev_bind_3 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                                SubLObject _prev_bind_4 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                                try {
                                                                    sbhl_search_vars.$sbhl_search_module$.bind(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa)), thread);
                                                                    sbhl_search_vars.$sbhl_search_module_type$.bind(sbhl_module_utilities.get_sbhl_module_type(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa))), thread);
                                                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.bind(sbhl_module_utilities.get_sbhl_add_node_to_result_test(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa))), thread);
                                                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                                                    sbhl_module_vars.$sbhl_module$.bind(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa)), thread);
                                                                    if ((NIL != sbhl_paranoia.suspend_sbhl_type_checkingP()) || (NIL != sbhl_module_utilities.apply_sbhl_module_type_test(node_var, sbhl_module_vars.get_sbhl_module(UNPROVIDED)))) {
                                                                        {
                                                                            SubLObject _prev_bind_0_10 = sbhl_search_vars.$sbhl_search_direction$.currentBinding(thread);
                                                                            SubLObject _prev_bind_1_11 = sbhl_link_vars.$sbhl_link_direction$.currentBinding(thread);
                                                                            SubLObject _prev_bind_2_12 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                                            try {
                                                                                sbhl_search_vars.$sbhl_search_direction$.bind(sbhl_search_vars.get_sbhl_backward_search_direction(), thread);
                                                                                sbhl_link_vars.$sbhl_link_direction$.bind(sbhl_module_utilities.sbhl_search_direction_to_link_direction(sbhl_search_vars.get_sbhl_backward_search_direction(), sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa))), thread);
                                                                                sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                                                                sbhl_marking_utilities.sbhl_mark_node_marked(node_var_1, UNPROVIDED);
                                                                                while (NIL != node_var_1) {
                                                                                    {
                                                                                        SubLObject tt_node_var = node_var_1;
                                                                                        SubLObject accessible_modules = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_vars.get_sbhl_module($$isa));
                                                                                        SubLObject cdolist_list_var = accessible_modules;
                                                                                        SubLObject module_var = NIL;
                                                                                        for (module_var = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , module_var = cdolist_list_var.first()) {
                                                                                            {
                                                                                                SubLObject _prev_bind_0_13 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                                                                SubLObject _prev_bind_1_14 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                                                                try {
                                                                                                    sbhl_module_vars.$sbhl_module$.bind(module_var, thread);
                                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? ((SubLObject) (makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)))) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                                                                    {
                                                                                                        SubLObject node = function_terms.naut_to_nart(tt_node_var);
                                                                                                        if (NIL != sbhl_link_vars.sbhl_node_object_p(node)) {
                                                                                                            {
                                                                                                                SubLObject d_link = sbhl_graphs.get_sbhl_graph_link(node, sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                                if (NIL != d_link) {
                                                                                                                    {
                                                                                                                        SubLObject mt_links = sbhl_links.get_sbhl_mt_links(d_link, sbhl_module_utilities.get_sbhl_module_backward_direction(sbhl_module_vars.get_sbhl_module($$isa)), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                                        if (NIL != mt_links) {
                                                                                                                            {
                                                                                                                                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(mt_links));
                                                                                                                                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                                                                                                                                    thread.resetMultipleValues();
                                                                                                                                    {
                                                                                                                                        SubLObject mt_15 = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                                                                                                                        SubLObject tv_links = thread.secondMultipleValue();
                                                                                                                                        thread.resetMultipleValues();
                                                                                                                                        if (NIL != mt_relevance_macros.relevant_mtP(mt_15)) {
                                                                                                                                            {
                                                                                                                                                SubLObject _prev_bind_0_16 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                                                                                                try {
                                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.bind(mt_15, thread);
                                                                                                                                                    {
                                                                                                                                                        SubLObject iteration_state_17 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links));
                                                                                                                                                        while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_17)) {
                                                                                                                                                            thread.resetMultipleValues();
                                                                                                                                                            {
                                                                                                                                                                SubLObject tv = dictionary_contents.do_dictionary_contents_key_value(iteration_state_17);
                                                                                                                                                                SubLObject link_nodes = thread.secondMultipleValue();
                                                                                                                                                                thread.resetMultipleValues();
                                                                                                                                                                if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                                                                                                    {
                                                                                                                                                                        SubLObject _prev_bind_0_18 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                                                                                                        try {
                                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                                                                                                            {
                                                                                                                                                                                SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                                                                                SubLObject cdolist_list_var_19 = new_list;
                                                                                                                                                                                SubLObject rule = NIL;
                                                                                                                                                                                for (rule = cdolist_list_var_19.first(); NIL != cdolist_list_var_19; cdolist_list_var_19 = cdolist_list_var_19.rest() , rule = cdolist_list_var_19.first()) {
                                                                                                                                                                                    if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread))) {
                                                                                                                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                                                                                                                        if (NIL != forts.fort_p(rule)) {
                                                                                                                                                                                            {
                                                                                                                                                                                                SubLObject template = kb_mapping_utilities.fpred_value(rule, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                                                                                                SubLObject constraints = kb_mapping_utilities.pred_values(rule, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                                                                                                result = cons(list(rule, template, constraints), result);
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        } finally {
                                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_18, thread);
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                iteration_state_17 = dictionary_contents.do_dictionary_contents_next(iteration_state_17);
                                                                                                                                                            }
                                                                                                                                                        } 
                                                                                                                                                        dictionary_contents.do_dictionary_contents_finalize(iteration_state_17);
                                                                                                                                                    }
                                                                                                                                                } finally {
                                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_16, thread);
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                                                                                                                    }
                                                                                                                                } 
                                                                                                                                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str_alt14$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                }
                                                                                                            }
                                                                                                            if (NIL != sbhl_macros.do_sbhl_non_fort_linksP(node, sbhl_module_vars.get_sbhl_module(UNPROVIDED))) {
                                                                                                                {
                                                                                                                    SubLObject csome_list_var = sbhl_link_methods.non_fort_instance_table_lookup(node);
                                                                                                                    SubLObject instance_tuple = NIL;
                                                                                                                    for (instance_tuple = csome_list_var.first(); NIL != csome_list_var; csome_list_var = csome_list_var.rest() , instance_tuple = csome_list_var.first()) {
                                                                                                                        {
                                                                                                                            SubLObject datum = instance_tuple;
                                                                                                                            SubLObject current = datum;
                                                                                                                            SubLObject link_node = NIL;
                                                                                                                            SubLObject mt_20 = NIL;
                                                                                                                            SubLObject tv = NIL;
                                                                                                                            destructuring_bind_must_consp(current, datum, $list_alt15);
                                                                                                                            link_node = current.first();
                                                                                                                            current = current.rest();
                                                                                                                            destructuring_bind_must_consp(current, datum, $list_alt15);
                                                                                                                            mt_20 = current.first();
                                                                                                                            current = current.rest();
                                                                                                                            destructuring_bind_must_consp(current, datum, $list_alt15);
                                                                                                                            tv = current.first();
                                                                                                                            current = current.rest();
                                                                                                                            if (NIL == current) {
                                                                                                                                if (NIL != mt_relevance_macros.relevant_mtP(mt_20)) {
                                                                                                                                    {
                                                                                                                                        SubLObject _prev_bind_0_21 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                                                                                        try {
                                                                                                                                            sbhl_link_vars.$sbhl_link_mt$.bind(mt_20, thread);
                                                                                                                                            if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                                                                                {
                                                                                                                                                    SubLObject _prev_bind_0_22 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                                                                                    try {
                                                                                                                                                        sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                                                                                        {
                                                                                                                                                            SubLObject link_nodes = list(link_node);
                                                                                                                                                            SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                                                            SubLObject cdolist_list_var_23 = new_list;
                                                                                                                                                            SubLObject rule = NIL;
                                                                                                                                                            for (rule = cdolist_list_var_23.first(); NIL != cdolist_list_var_23; cdolist_list_var_23 = cdolist_list_var_23.rest() , rule = cdolist_list_var_23.first()) {
                                                                                                                                                                if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread))) {
                                                                                                                                                                    sbhl_marking_utilities.sbhl_mark_node_marked(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                                                                                                    if (NIL != forts.fort_p(rule)) {
                                                                                                                                                                        {
                                                                                                                                                                            SubLObject template = kb_mapping_utilities.fpred_value(rule, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                                                                            SubLObject constraints = kb_mapping_utilities.pred_values(rule, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                                                                            result = cons(list(rule, template, constraints), result);
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    } finally {
                                                                                                                                                        sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_22, thread);
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        } finally {
                                                                                                                                            sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_21, thread);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                cdestructuring_bind_error(datum, $list_alt15);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        } else {
                                                                                                            if (NIL != obsolete.cnat_p(node, UNPROVIDED)) {
                                                                                                                {
                                                                                                                    SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_module_utilities.get_sbhl_module_backward_direction(sbhl_module_vars.get_sbhl_module($$isa)), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_module_utilities.get_sbhl_module_backward_direction(sbhl_module_vars.get_sbhl_module($$isa)), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                                    SubLObject cdolist_list_var_24 = new_list;
                                                                                                                    SubLObject generating_fn = NIL;
                                                                                                                    for (generating_fn = cdolist_list_var_24.first(); NIL != cdolist_list_var_24; cdolist_list_var_24 = cdolist_list_var_24.rest() , generating_fn = cdolist_list_var_24.first()) {
                                                                                                                        {
                                                                                                                            SubLObject _prev_bind_0_25 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                                                                                            try {
                                                                                                                                sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn, thread);
                                                                                                                                {
                                                                                                                                    SubLObject link_nodes = funcall(generating_fn, node);
                                                                                                                                    SubLObject new_list_26 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                                    SubLObject cdolist_list_var_27 = new_list_26;
                                                                                                                                    SubLObject rule = NIL;
                                                                                                                                    for (rule = cdolist_list_var_27.first(); NIL != cdolist_list_var_27; cdolist_list_var_27 = cdolist_list_var_27.rest() , rule = cdolist_list_var_27.first()) {
                                                                                                                                        if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread))) {
                                                                                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                                                                            if (NIL != forts.fort_p(rule)) {
                                                                                                                                                {
                                                                                                                                                    SubLObject template = kb_mapping_utilities.fpred_value(rule, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                                                    SubLObject constraints = kb_mapping_utilities.pred_values(rule, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                                                    result = cons(list(rule, template, constraints), result);
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            } finally {
                                                                                                                                sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_25, thread);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                } finally {
                                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_14, thread);
                                                                                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_13, thread);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    {
                                                                                        SubLObject accessible_modules = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa)));
                                                                                        SubLObject cdolist_list_var = accessible_modules;
                                                                                        SubLObject module_var = NIL;
                                                                                        for (module_var = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , module_var = cdolist_list_var.first()) {
                                                                                            {
                                                                                                SubLObject _prev_bind_0_28 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                                                                SubLObject _prev_bind_1_29 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                                                                try {
                                                                                                    sbhl_module_vars.$sbhl_module$.bind(module_var, thread);
                                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? ((SubLObject) (makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)))) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                                                                    {
                                                                                                        SubLObject node = function_terms.naut_to_nart(node_var_1);
                                                                                                        if (NIL != sbhl_link_vars.sbhl_node_object_p(node)) {
                                                                                                            {
                                                                                                                SubLObject d_link = sbhl_graphs.get_sbhl_graph_link(node, sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                                if (NIL != d_link) {
                                                                                                                    {
                                                                                                                        SubLObject mt_links = sbhl_links.get_sbhl_mt_links(d_link, sbhl_link_vars.get_sbhl_link_direction(), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                                        if (NIL != mt_links) {
                                                                                                                            {
                                                                                                                                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(mt_links));
                                                                                                                                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                                                                                                                                    thread.resetMultipleValues();
                                                                                                                                    {
                                                                                                                                        SubLObject mt_30 = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                                                                                                                        SubLObject tv_links = thread.secondMultipleValue();
                                                                                                                                        thread.resetMultipleValues();
                                                                                                                                        if (NIL != mt_relevance_macros.relevant_mtP(mt_30)) {
                                                                                                                                            {
                                                                                                                                                SubLObject _prev_bind_0_31 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                                                                                                try {
                                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.bind(mt_30, thread);
                                                                                                                                                    {
                                                                                                                                                        SubLObject iteration_state_32 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links));
                                                                                                                                                        while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_32)) {
                                                                                                                                                            thread.resetMultipleValues();
                                                                                                                                                            {
                                                                                                                                                                SubLObject tv = dictionary_contents.do_dictionary_contents_key_value(iteration_state_32);
                                                                                                                                                                SubLObject link_nodes = thread.secondMultipleValue();
                                                                                                                                                                thread.resetMultipleValues();
                                                                                                                                                                if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                                                                                                    {
                                                                                                                                                                        SubLObject _prev_bind_0_33 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                                                                                                        try {
                                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                                                                                                            {
                                                                                                                                                                                SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                                                                                SubLObject cdolist_list_var_34 = new_list;
                                                                                                                                                                                SubLObject node_vars_link_node = NIL;
                                                                                                                                                                                for (node_vars_link_node = cdolist_list_var_34.first(); NIL != cdolist_list_var_34; cdolist_list_var_34 = cdolist_list_var_34.rest() , node_vars_link_node = cdolist_list_var_34.first()) {
                                                                                                                                                                                    if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED)) {
                                                                                                                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                                                                                                        deck.deck_push(node_vars_link_node, recur_deck);
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        } finally {
                                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_33, thread);
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                iteration_state_32 = dictionary_contents.do_dictionary_contents_next(iteration_state_32);
                                                                                                                                                            }
                                                                                                                                                        } 
                                                                                                                                                        dictionary_contents.do_dictionary_contents_finalize(iteration_state_32);
                                                                                                                                                    }
                                                                                                                                                } finally {
                                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_31, thread);
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                                                                                                                    }
                                                                                                                                } 
                                                                                                                                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str_alt14$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                }
                                                                                                            }
                                                                                                        } else {
                                                                                                            if (NIL != obsolete.cnat_p(node, UNPROVIDED)) {
                                                                                                                {
                                                                                                                    SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                                    SubLObject cdolist_list_var_35 = new_list;
                                                                                                                    SubLObject generating_fn = NIL;
                                                                                                                    for (generating_fn = cdolist_list_var_35.first(); NIL != cdolist_list_var_35; cdolist_list_var_35 = cdolist_list_var_35.rest() , generating_fn = cdolist_list_var_35.first()) {
                                                                                                                        {
                                                                                                                            SubLObject _prev_bind_0_36 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                                                                                            try {
                                                                                                                                sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn, thread);
                                                                                                                                {
                                                                                                                                    SubLObject link_nodes = funcall(generating_fn, node);
                                                                                                                                    SubLObject new_list_37 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                                    SubLObject cdolist_list_var_38 = new_list_37;
                                                                                                                                    SubLObject node_vars_link_node = NIL;
                                                                                                                                    for (node_vars_link_node = cdolist_list_var_38.first(); NIL != cdolist_list_var_38; cdolist_list_var_38 = cdolist_list_var_38.rest() , node_vars_link_node = cdolist_list_var_38.first()) {
                                                                                                                                        if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED)) {
                                                                                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                                                            deck.deck_push(node_vars_link_node, recur_deck);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            } finally {
                                                                                                                                sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_36, thread);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                } finally {
                                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_29, thread);
                                                                                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_28, thread);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    node_var_1 = deck.deck_pop(recur_deck);
                                                                                } 
                                                                            } finally {
                                                                                sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_2_12, thread);
                                                                                sbhl_link_vars.$sbhl_link_direction$.rebind(_prev_bind_1_11, thread);
                                                                                sbhl_search_vars.$sbhl_search_direction$.rebind(_prev_bind_0_10, thread);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        sbhl_paranoia.sbhl_warn(TWO_INTEGER, $str_alt16$Node__a_does_not_pass_sbhl_type_t, node_var, sbhl_module_utilities.get_sbhl_type_test(sbhl_module_vars.get_sbhl_module(UNPROVIDED)), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                    }
                                                                } finally {
                                                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_4, thread);
                                                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_3, thread);
                                                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.rebind(_prev_bind_2_9, thread);
                                                                    sbhl_search_vars.$sbhl_search_module_type$.rebind(_prev_bind_1_8, thread);
                                                                    sbhl_search_vars.$sbhl_search_module$.rebind(_prev_bind_0_7, thread);
                                                                }
                                                            }
                                                        } finally {
                                                            sbhl_search_vars.$relevant_sbhl_tv_function$.rebind(_prev_bind_1_6, thread);
                                                            sbhl_search_vars.$sbhl_tv$.rebind(_prev_bind_0_5, thread);
                                                        }
                                                    }
                                                }
                                            } finally {
                                                mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                                                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1_4, thread);
                                                mt_relevance_macros.$mt$.rebind(_prev_bind_0_3, thread);
                                            }
                                        }
                                        sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_space$.getDynamicValue(thread));
                                    }
                                } finally {
                                    sbhl_marking_vars.$sbhl_space$.rebind(_prev_bind_0_2, thread);
                                }
                            }
                            sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                        }
                    } finally {
                        sbhl_marking_vars.$sbhl_gather_space$.rebind(_prev_bind_1, thread);
                        sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0, thread);
                    }
                }
                return result;
            }
        }
    }

    /**
     *
     *
     * @unknown macro-helper for do-nc-rules
     */
    @LispMethod(comment = "@unknown macro-helper for do-nc-rules")
    public static SubLObject pph_nc_rule_list_internal(final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        final SubLObject node_var = $noun_compound_rule$.getGlobalValue();
        final SubLObject _prev_bind_0 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
        final SubLObject _prev_bind_2 = sbhl_marking_vars.$sbhl_gather_space$.currentBinding(thread);
        try {
            sbhl_module_vars.$sbhl_module$.bind(sbhl_module_vars.get_sbhl_module($$isa), thread);
            sbhl_marking_vars.$sbhl_gather_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
            try {
                SubLObject node_var_$1 = node_var;
                final SubLObject deck_type = $STACK;
                final SubLObject recur_deck = deck.create_deck(deck_type);
                final SubLObject _prev_bind_0_$2 = sbhl_marking_vars.$sbhl_space$.currentBinding(thread);
                try {
                    sbhl_marking_vars.$sbhl_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
                    try {
                        final SubLObject _prev_bind_0_$3 = mt_relevance_macros.$mt$.currentBinding(thread);
                        final SubLObject _prev_bind_1_$4 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                        try {
                            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt), thread);
                            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt), thread);
                            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt), thread);
                            final SubLObject tv_var = $$True_JustificationTruth;
                            final SubLObject _prev_bind_0_$4 = sbhl_search_vars.$sbhl_tv$.currentBinding(thread);
                            final SubLObject _prev_bind_1_$5 = sbhl_search_vars.$relevant_sbhl_tv_function$.currentBinding(thread);
                            try {
                                sbhl_search_vars.$sbhl_tv$.bind(NIL != tv_var ? tv_var : sbhl_search_vars.get_sbhl_true_tv(), thread);
                                sbhl_search_vars.$relevant_sbhl_tv_function$.bind(NIL != tv_var ? RELEVANT_SBHL_TV_IS_GENERAL_TV : sbhl_search_vars.$relevant_sbhl_tv_function$.getDynamicValue(thread), thread);
                                if (((NIL != tv_var) && (NIL != sbhl_paranoia.sbhl_object_type_checking_p())) && (NIL == sbhl_search_vars.sbhl_true_tv_p(tv_var))) {
                                    final SubLObject pcase_var = sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread);
                                    if (pcase_var.eql($ERROR)) {
                                        sbhl_paranoia.sbhl_error(ONE_INTEGER, $str8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    } else
                                        if (pcase_var.eql($CERROR)) {
                                            sbhl_paranoia.sbhl_cerror(ONE_INTEGER, $$$continue_anyway, $str8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                        } else
                                            if (pcase_var.eql($WARN)) {
                                                Errors.warn($str8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                            } else {
                                                Errors.warn($str13$_A_is_not_a_valid__sbhl_type_erro, sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread));
                                                Errors.cerror($$$continue_anyway, $str8$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                            }


                                }
                                final SubLObject _prev_bind_0_$5 = sbhl_search_vars.$sbhl_search_module$.currentBinding(thread);
                                final SubLObject _prev_bind_1_$6 = sbhl_search_vars.$sbhl_search_module_type$.currentBinding(thread);
                                final SubLObject _prev_bind_2_$9 = sbhl_search_vars.$sbhl_add_node_to_result_test$.currentBinding(thread);
                                final SubLObject _prev_bind_4 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                final SubLObject _prev_bind_5 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                try {
                                    sbhl_search_vars.$sbhl_search_module$.bind(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa)), thread);
                                    sbhl_search_vars.$sbhl_search_module_type$.bind(sbhl_module_utilities.get_sbhl_module_type(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa))), thread);
                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.bind(sbhl_module_utilities.get_sbhl_add_node_to_result_test(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa))), thread);
                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                    sbhl_module_vars.$sbhl_module$.bind(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa)), thread);
                                    if ((NIL != sbhl_paranoia.suspend_sbhl_type_checkingP()) || (NIL != sbhl_module_utilities.apply_sbhl_module_type_test(node_var, sbhl_module_vars.get_sbhl_module(UNPROVIDED)))) {
                                        final SubLObject _prev_bind_0_$6 = sbhl_search_vars.$sbhl_search_direction$.currentBinding(thread);
                                        final SubLObject _prev_bind_1_$7 = sbhl_link_vars.$sbhl_link_direction$.currentBinding(thread);
                                        final SubLObject _prev_bind_2_$10 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                        try {
                                            sbhl_search_vars.$sbhl_search_direction$.bind(sbhl_search_vars.get_sbhl_backward_search_direction(), thread);
                                            sbhl_link_vars.$sbhl_link_direction$.bind(sbhl_module_utilities.sbhl_search_direction_to_link_direction(sbhl_search_vars.get_sbhl_backward_search_direction(), sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa))), thread);
                                            sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                            sbhl_marking_utilities.sbhl_mark_node_marked(node_var_$1, UNPROVIDED);
                                            while (NIL != node_var_$1) {
                                                final SubLObject tt_node_var = node_var_$1;
                                                SubLObject cdolist_list_var;
                                                final SubLObject accessible_modules = cdolist_list_var = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_vars.get_sbhl_module($$isa));
                                                SubLObject module_var = NIL;
                                                module_var = cdolist_list_var.first();
                                                while (NIL != cdolist_list_var) {
                                                    final SubLObject _prev_bind_0_$7 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                    final SubLObject _prev_bind_1_$8 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                    try {
                                                        sbhl_module_vars.$sbhl_module$.bind(module_var, thread);
                                                        sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                        final SubLObject node = function_terms.naut_to_nart(tt_node_var);
                                                        if (NIL != sbhl_link_vars.sbhl_node_object_p(node)) {
                                                            final SubLObject d_link = sbhl_graphs.get_sbhl_graph_link(node, sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                            if (NIL != d_link) {
                                                                final SubLObject mt_links = sbhl_links.get_sbhl_mt_links(d_link, sbhl_module_utilities.get_sbhl_module_backward_direction(sbhl_module_vars.get_sbhl_module($$isa)), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                if (NIL != mt_links) {
                                                                    SubLObject iteration_state;
                                                                    for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(mt_links)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                                                                        thread.resetMultipleValues();
                                                                        final SubLObject mt_$15 = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                                                        final SubLObject tv_links = thread.secondMultipleValue();
                                                                        thread.resetMultipleValues();
                                                                        if (NIL != mt_relevance_macros.relevant_mtP(mt_$15)) {
                                                                            final SubLObject _prev_bind_0_$8 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                            try {
                                                                                sbhl_link_vars.$sbhl_link_mt$.bind(mt_$15, thread);
                                                                                SubLObject iteration_state_$17;
                                                                                for (iteration_state_$17 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_$17); iteration_state_$17 = dictionary_contents.do_dictionary_contents_next(iteration_state_$17)) {
                                                                                    thread.resetMultipleValues();
                                                                                    final SubLObject tv = dictionary_contents.do_dictionary_contents_key_value(iteration_state_$17);
                                                                                    final SubLObject link_nodes = thread.secondMultipleValue();
                                                                                    thread.resetMultipleValues();
                                                                                    if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                        final SubLObject _prev_bind_0_$9 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                        try {
                                                                                            sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                            final SubLObject sol = link_nodes;
                                                                                            if (NIL != set.set_p(sol)) {
                                                                                                final SubLObject set_contents_var = set.do_set_internal(sol);
                                                                                                SubLObject basis_object;
                                                                                                SubLObject state;
                                                                                                SubLObject rule;
                                                                                                SubLObject template;
                                                                                                SubLObject constraints;
                                                                                                for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                                                                                    rule = set_contents.do_set_contents_next(basis_object, state);
                                                                                                    if ((NIL != set_contents.do_set_contents_element_validP(state, rule)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread)))) {
                                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                                        if (NIL != forts.fort_p(rule)) {
                                                                                                            template = kb_mapping_utilities.fpred_value(rule, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                            constraints = kb_mapping_utilities.pred_values(rule, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                            result = cons(list(rule, template, constraints), result);
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            } else
                                                                                                if (sol.isList()) {
                                                                                                    SubLObject csome_list_var = sol;
                                                                                                    SubLObject rule2 = NIL;
                                                                                                    rule2 = csome_list_var.first();
                                                                                                    while (NIL != csome_list_var) {
                                                                                                        if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule2, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread))) {
                                                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(rule2, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                                            if (NIL != forts.fort_p(rule2)) {
                                                                                                                final SubLObject template2 = kb_mapping_utilities.fpred_value(rule2, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                final SubLObject constraints2 = kb_mapping_utilities.pred_values(rule2, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                                result = cons(list(rule2, template2, constraints2), result);
                                                                                                            }
                                                                                                        }
                                                                                                        csome_list_var = csome_list_var.rest();
                                                                                                        rule2 = csome_list_var.first();
                                                                                                    } 
                                                                                                } else {
                                                                                                    Errors.error($str14$_A_is_neither_SET_P_nor_LISTP_, sol);
                                                                                                }

                                                                                        } finally {
                                                                                            sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_$9, thread);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                dictionary_contents.do_dictionary_contents_finalize(iteration_state_$17);
                                                                            } finally {
                                                                                sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_$8, thread);
                                                                            }
                                                                        }
                                                                    }
                                                                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                                                                }
                                                            } else {
                                                                sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str15$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                            }
                                                            if (NIL != sbhl_macros.do_sbhl_non_fort_linksP(node, sbhl_module_vars.get_sbhl_module(UNPROVIDED))) {
                                                                SubLObject csome_list_var2 = sbhl_link_methods.non_fort_instance_table_lookup(node);
                                                                SubLObject instance_tuple = NIL;
                                                                instance_tuple = csome_list_var2.first();
                                                                while (NIL != csome_list_var2) {
                                                                    SubLObject current;
                                                                    final SubLObject datum = current = instance_tuple;
                                                                    SubLObject link_node = NIL;
                                                                    SubLObject mt_$16 = NIL;
                                                                    SubLObject tv2 = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list16);
                                                                    link_node = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list16);
                                                                    mt_$16 = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list16);
                                                                    tv2 = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (NIL != mt_relevance_macros.relevant_mtP(mt_$16)) {
                                                                            final SubLObject _prev_bind_0_$10 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                            try {
                                                                                sbhl_link_vars.$sbhl_link_mt$.bind(mt_$16, thread);
                                                                                if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv2)) {
                                                                                    final SubLObject _prev_bind_0_$11 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                    try {
                                                                                        sbhl_link_vars.$sbhl_link_tv$.bind(tv2, thread);
                                                                                        final SubLObject sol;
                                                                                        final SubLObject link_nodes2 = sol = list(link_node);
                                                                                        if (NIL != set.set_p(sol)) {
                                                                                            final SubLObject set_contents_var = set.do_set_internal(sol);
                                                                                            SubLObject basis_object;
                                                                                            SubLObject state;
                                                                                            SubLObject rule;
                                                                                            SubLObject template;
                                                                                            SubLObject constraints;
                                                                                            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                                                                                rule = set_contents.do_set_contents_next(basis_object, state);
                                                                                                if ((NIL != set_contents.do_set_contents_element_validP(state, rule)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread)))) {
                                                                                                    sbhl_marking_utilities.sbhl_mark_node_marked(rule, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                                    if (NIL != forts.fort_p(rule)) {
                                                                                                        template = kb_mapping_utilities.fpred_value(rule, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                        constraints = kb_mapping_utilities.pred_values(rule, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                        result = cons(list(rule, template, constraints), result);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else
                                                                                            if (sol.isList()) {
                                                                                                SubLObject csome_list_var_$22 = sol;
                                                                                                SubLObject rule2 = NIL;
                                                                                                rule2 = csome_list_var_$22.first();
                                                                                                while (NIL != csome_list_var_$22) {
                                                                                                    if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule2, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread))) {
                                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(rule2, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                                        if (NIL != forts.fort_p(rule2)) {
                                                                                                            final SubLObject template2 = kb_mapping_utilities.fpred_value(rule2, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                            final SubLObject constraints2 = kb_mapping_utilities.pred_values(rule2, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                            result = cons(list(rule2, template2, constraints2), result);
                                                                                                        }
                                                                                                    }
                                                                                                    csome_list_var_$22 = csome_list_var_$22.rest();
                                                                                                    rule2 = csome_list_var_$22.first();
                                                                                                } 
                                                                                            } else {
                                                                                                Errors.error($str14$_A_is_neither_SET_P_nor_LISTP_, sol);
                                                                                            }

                                                                                    } finally {
                                                                                        sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_$11, thread);
                                                                                    }
                                                                                }
                                                                            } finally {
                                                                                sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_$10, thread);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list16);
                                                                    }
                                                                    csome_list_var2 = csome_list_var2.rest();
                                                                    instance_tuple = csome_list_var2.first();
                                                                } 
                                                            }
                                                        } else
                                                            if (NIL != obsolete.cnat_p(node, UNPROVIDED)) {
                                                                SubLObject cdolist_list_var_$23;
                                                                final SubLObject new_list = cdolist_list_var_$23 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_module_utilities.get_sbhl_module_backward_direction(sbhl_module_vars.get_sbhl_module($$isa)), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_module_utilities.get_sbhl_module_backward_direction(sbhl_module_vars.get_sbhl_module($$isa)), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                SubLObject generating_fn = NIL;
                                                                generating_fn = cdolist_list_var_$23.first();
                                                                while (NIL != cdolist_list_var_$23) {
                                                                    final SubLObject _prev_bind_0_$12 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                                    try {
                                                                        sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn, thread);
                                                                        final SubLObject sol2;
                                                                        final SubLObject link_nodes3 = sol2 = funcall(generating_fn, node);
                                                                        if (NIL != set.set_p(sol2)) {
                                                                            final SubLObject set_contents_var2 = set.do_set_internal(sol2);
                                                                            SubLObject basis_object2;
                                                                            SubLObject state2;
                                                                            SubLObject rule3;
                                                                            SubLObject template3;
                                                                            SubLObject constraints3;
                                                                            for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2, state2); state2 = set_contents.do_set_contents_update_state(state2)) {
                                                                                rule3 = set_contents.do_set_contents_next(basis_object2, state2);
                                                                                if ((NIL != set_contents.do_set_contents_element_validP(state2, rule3)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule3, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread)))) {
                                                                                    sbhl_marking_utilities.sbhl_mark_node_marked(rule3, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                    if (NIL != forts.fort_p(rule3)) {
                                                                                        template3 = kb_mapping_utilities.fpred_value(rule3, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                        constraints3 = kb_mapping_utilities.pred_values(rule3, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                        result = cons(list(rule3, template3, constraints3), result);
                                                                                    }
                                                                                }
                                                                            }
                                                                        } else
                                                                            if (sol2.isList()) {
                                                                                SubLObject csome_list_var3 = sol2;
                                                                                SubLObject rule4 = NIL;
                                                                                rule4 = csome_list_var3.first();
                                                                                while (NIL != csome_list_var3) {
                                                                                    if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(rule4, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread))) {
                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(rule4, sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                                                                                        if (NIL != forts.fort_p(rule4)) {
                                                                                            final SubLObject template4 = kb_mapping_utilities.fpred_value(rule4, $nc_rule_template$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                            final SubLObject constraints4 = kb_mapping_utilities.pred_values(rule4, $nc_rule_constraint$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                            result = cons(list(rule4, template4, constraints4), result);
                                                                                        }
                                                                                    }
                                                                                    csome_list_var3 = csome_list_var3.rest();
                                                                                    rule4 = csome_list_var3.first();
                                                                                } 
                                                                            } else {
                                                                                Errors.error($str14$_A_is_neither_SET_P_nor_LISTP_, sol2);
                                                                            }

                                                                    } finally {
                                                                        sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_$12, thread);
                                                                    }
                                                                    cdolist_list_var_$23 = cdolist_list_var_$23.rest();
                                                                    generating_fn = cdolist_list_var_$23.first();
                                                                } 
                                                            }

                                                    } finally {
                                                        sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_$8, thread);
                                                        sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_$7, thread);
                                                    }
                                                    cdolist_list_var = cdolist_list_var.rest();
                                                    module_var = cdolist_list_var.first();
                                                } 
                                                SubLObject cdolist_list_var2;
                                                final SubLObject accessible_modules2 = cdolist_list_var2 = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_utilities.get_sbhl_transfers_through_module(sbhl_module_vars.get_sbhl_module($$isa)));
                                                SubLObject module_var2 = NIL;
                                                module_var2 = cdolist_list_var2.first();
                                                while (NIL != cdolist_list_var2) {
                                                    final SubLObject _prev_bind_0_$13 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                    final SubLObject _prev_bind_1_$9 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                    try {
                                                        sbhl_module_vars.$sbhl_module$.bind(module_var2, thread);
                                                        sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                        final SubLObject node2 = function_terms.naut_to_nart(node_var_$1);
                                                        if (NIL != sbhl_link_vars.sbhl_node_object_p(node2)) {
                                                            final SubLObject d_link2 = sbhl_graphs.get_sbhl_graph_link(node2, sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                            if (NIL != d_link2) {
                                                                final SubLObject mt_links2 = sbhl_links.get_sbhl_mt_links(d_link2, sbhl_link_vars.get_sbhl_link_direction(), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                if (NIL != mt_links2) {
                                                                    SubLObject iteration_state2;
                                                                    for (iteration_state2 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(mt_links2)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state2); iteration_state2 = dictionary_contents.do_dictionary_contents_next(iteration_state2)) {
                                                                        thread.resetMultipleValues();
                                                                        final SubLObject mt_$17 = dictionary_contents.do_dictionary_contents_key_value(iteration_state2);
                                                                        final SubLObject tv_links2 = thread.secondMultipleValue();
                                                                        thread.resetMultipleValues();
                                                                        if (NIL != mt_relevance_macros.relevant_mtP(mt_$17)) {
                                                                            final SubLObject _prev_bind_0_$14 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                            try {
                                                                                sbhl_link_vars.$sbhl_link_mt$.bind(mt_$17, thread);
                                                                                SubLObject iteration_state_$18;
                                                                                for (iteration_state_$18 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links2)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_$18); iteration_state_$18 = dictionary_contents.do_dictionary_contents_next(iteration_state_$18)) {
                                                                                    thread.resetMultipleValues();
                                                                                    final SubLObject tv2 = dictionary_contents.do_dictionary_contents_key_value(iteration_state_$18);
                                                                                    final SubLObject link_nodes4 = thread.secondMultipleValue();
                                                                                    thread.resetMultipleValues();
                                                                                    if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv2)) {
                                                                                        final SubLObject _prev_bind_0_$15 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                        try {
                                                                                            sbhl_link_vars.$sbhl_link_tv$.bind(tv2, thread);
                                                                                            final SubLObject sol3 = link_nodes4;
                                                                                            if (NIL != set.set_p(sol3)) {
                                                                                                final SubLObject set_contents_var3 = set.do_set_internal(sol3);
                                                                                                SubLObject basis_object3;
                                                                                                SubLObject state3;
                                                                                                SubLObject node_vars_link_node;
                                                                                                for (basis_object3 = set_contents.do_set_contents_basis_object(set_contents_var3), state3 = NIL, state3 = set_contents.do_set_contents_initial_state(basis_object3, set_contents_var3); NIL == set_contents.do_set_contents_doneP(basis_object3, state3); state3 = set_contents.do_set_contents_update_state(state3)) {
                                                                                                    node_vars_link_node = set_contents.do_set_contents_next(basis_object3, state3);
                                                                                                    if ((NIL != set_contents.do_set_contents_element_validP(state3, node_vars_link_node)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED))) {
                                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                        deck.deck_push(node_vars_link_node, recur_deck);
                                                                                                    }
                                                                                                }
                                                                                            } else
                                                                                                if (sol3.isList()) {
                                                                                                    SubLObject csome_list_var4 = sol3;
                                                                                                    SubLObject node_vars_link_node2 = NIL;
                                                                                                    node_vars_link_node2 = csome_list_var4.first();
                                                                                                    while (NIL != csome_list_var4) {
                                                                                                        if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node2, UNPROVIDED)) {
                                                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node2, UNPROVIDED);
                                                                                                            deck.deck_push(node_vars_link_node2, recur_deck);
                                                                                                        }
                                                                                                        csome_list_var4 = csome_list_var4.rest();
                                                                                                        node_vars_link_node2 = csome_list_var4.first();
                                                                                                    } 
                                                                                                } else {
                                                                                                    Errors.error($str14$_A_is_neither_SET_P_nor_LISTP_, sol3);
                                                                                                }

                                                                                        } finally {
                                                                                            sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_$15, thread);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                dictionary_contents.do_dictionary_contents_finalize(iteration_state_$18);
                                                                            } finally {
                                                                                sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_$14, thread);
                                                                            }
                                                                        }
                                                                    }
                                                                    dictionary_contents.do_dictionary_contents_finalize(iteration_state2);
                                                                }
                                                            } else {
                                                                sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str15$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                            }
                                                        } else
                                                            if (NIL != obsolete.cnat_p(node2, UNPROVIDED)) {
                                                                SubLObject cdolist_list_var_$24;
                                                                final SubLObject new_list2 = cdolist_list_var_$24 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                SubLObject generating_fn2 = NIL;
                                                                generating_fn2 = cdolist_list_var_$24.first();
                                                                while (NIL != cdolist_list_var_$24) {
                                                                    final SubLObject _prev_bind_0_$16 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                                    try {
                                                                        sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn2, thread);
                                                                        final SubLObject sol4;
                                                                        final SubLObject link_nodes5 = sol4 = funcall(generating_fn2, node2);
                                                                        if (NIL != set.set_p(sol4)) {
                                                                            final SubLObject set_contents_var4 = set.do_set_internal(sol4);
                                                                            SubLObject basis_object4;
                                                                            SubLObject state4;
                                                                            SubLObject node_vars_link_node3;
                                                                            for (basis_object4 = set_contents.do_set_contents_basis_object(set_contents_var4), state4 = NIL, state4 = set_contents.do_set_contents_initial_state(basis_object4, set_contents_var4); NIL == set_contents.do_set_contents_doneP(basis_object4, state4); state4 = set_contents.do_set_contents_update_state(state4)) {
                                                                                node_vars_link_node3 = set_contents.do_set_contents_next(basis_object4, state4);
                                                                                if ((NIL != set_contents.do_set_contents_element_validP(state4, node_vars_link_node3)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node3, UNPROVIDED))) {
                                                                                    sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node3, UNPROVIDED);
                                                                                    deck.deck_push(node_vars_link_node3, recur_deck);
                                                                                }
                                                                            }
                                                                        } else
                                                                            if (sol4.isList()) {
                                                                                SubLObject csome_list_var5 = sol4;
                                                                                SubLObject node_vars_link_node4 = NIL;
                                                                                node_vars_link_node4 = csome_list_var5.first();
                                                                                while (NIL != csome_list_var5) {
                                                                                    if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node4, UNPROVIDED)) {
                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node4, UNPROVIDED);
                                                                                        deck.deck_push(node_vars_link_node4, recur_deck);
                                                                                    }
                                                                                    csome_list_var5 = csome_list_var5.rest();
                                                                                    node_vars_link_node4 = csome_list_var5.first();
                                                                                } 
                                                                            } else {
                                                                                Errors.error($str14$_A_is_neither_SET_P_nor_LISTP_, sol4);
                                                                            }

                                                                    } finally {
                                                                        sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_$16, thread);
                                                                    }
                                                                    cdolist_list_var_$24 = cdolist_list_var_$24.rest();
                                                                    generating_fn2 = cdolist_list_var_$24.first();
                                                                } 
                                                            }

                                                    } finally {
                                                        sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_$9, thread);
                                                        sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_$13, thread);
                                                    }
                                                    cdolist_list_var2 = cdolist_list_var2.rest();
                                                    module_var2 = cdolist_list_var2.first();
                                                } 
                                                node_var_$1 = deck.deck_pop(recur_deck);
                                            } 
                                        } finally {
                                            sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_2_$10, thread);
                                            sbhl_link_vars.$sbhl_link_direction$.rebind(_prev_bind_1_$7, thread);
                                            sbhl_search_vars.$sbhl_search_direction$.rebind(_prev_bind_0_$6, thread);
                                        }
                                    } else {
                                        sbhl_paranoia.sbhl_warn(TWO_INTEGER, $str17$Node__a_does_not_pass_sbhl_type_t, node_var, sbhl_module_utilities.get_sbhl_type_test(sbhl_module_vars.get_sbhl_module(UNPROVIDED)), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    }
                                } finally {
                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_5, thread);
                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_4, thread);
                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.rebind(_prev_bind_2_$9, thread);
                                    sbhl_search_vars.$sbhl_search_module_type$.rebind(_prev_bind_1_$6, thread);
                                    sbhl_search_vars.$sbhl_search_module$.rebind(_prev_bind_0_$5, thread);
                                }
                            } finally {
                                sbhl_search_vars.$relevant_sbhl_tv_function$.rebind(_prev_bind_1_$5, thread);
                                sbhl_search_vars.$sbhl_tv$.rebind(_prev_bind_0_$4, thread);
                            }
                        } finally {
                            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
                            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1_$4, thread);
                            mt_relevance_macros.$mt$.rebind(_prev_bind_0_$3, thread);
                        }
                    } finally {
                        final SubLObject _prev_bind_0_$17 = $is_thread_performing_cleanupP$.currentBinding(thread);
                        try {
                            $is_thread_performing_cleanupP$.bind(T, thread);
                            final SubLObject _values = getValuesAsVector();
                            sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_space$.getDynamicValue(thread));
                            restoreValuesFromVector(_values);
                        } finally {
                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$17, thread);
                        }
                    }
                } finally {
                    sbhl_marking_vars.$sbhl_space$.rebind(_prev_bind_0_$2, thread);
                }
            } finally {
                final SubLObject _prev_bind_0_$18 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values2 = getValuesAsVector();
                    sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_gather_space$.getDynamicValue(thread));
                    restoreValuesFromVector(_values2);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$18, thread);
                }
            }
        } finally {
            sbhl_marking_vars.$sbhl_gather_space$.rebind(_prev_bind_2, thread);
            sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static final SubLObject pph_nc_rule_list_alt(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $nc_mt$.getGlobalValue();
        }
        {
            SubLObject caching_state = $pph_nc_rule_list_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(PPH_NC_RULE_LIST, $pph_nc_rule_list_caching_state$, NIL, EQUAL, ONE_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject results = memoization_state.caching_state_lookup(caching_state, mt, $kw18$_MEMOIZED_ITEM_NOT_FOUND_);
                if (results == $kw18$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.pph_noun_compound.pph_nc_rule_list_internal(mt)));
                    memoization_state.caching_state_put(caching_state, mt, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject pph_nc_rule_list(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $nc_mt$.getGlobalValue();
        }
        SubLObject caching_state = $pph_nc_rule_list_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(PPH_NC_RULE_LIST, $pph_nc_rule_list_caching_state$, NIL, EQUAL, ONE_INTEGER, ZERO_INTEGER);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, mt, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(pph_nc_rule_list_internal(mt)));
            memoization_state.caching_state_put(caching_state, mt, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    /**
     * Iterate through instances of #$NounCompoundRule, where
     *
     * @param RULE-VAR
     * 		holds the name of the rule (e.g. #$TourHighlights-NCR)
     * @param TEMPLATE-VAR
     * 		holds the value of #$ncRuleTemplate for that rule
     * @param CONSTRAINTS-VAR
     * 		holds the #$ncRuleConstraint-s for that rule, until
     * @param DONE-VAR
     * 		is non-nil.
     */
    @LispMethod(comment = "Iterate through instances of #$NounCompoundRule, where\r\n\r\n@param RULE-VAR\r\n\t\tholds the name of the rule (e.g. #$TourHighlights-NCR)\r\n@param TEMPLATE-VAR\r\n\t\tholds the value of #$ncRuleTemplate for that rule\r\n@param CONSTRAINTS-VAR\r\n\t\tholds the #$ncRuleConstraint-s for that rule, until\r\n@param DONE-VAR\r\n\t\tis non-nil.")
    public static final SubLObject do_nc_rules_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt19);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject rule_var = NIL;
                    SubLObject template_var = NIL;
                    SubLObject constraints_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt19);
                    rule_var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt19);
                    template_var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt19);
                    constraints_var = current.first();
                    current = current.rest();
                    {
                        SubLObject done_var = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt19);
                        current = current.rest();
                        if (NIL == current) {
                            current = temp;
                            {
                                SubLObject body = current;
                                SubLObject rule = $sym20$RULE;
                                return list(CSOME, list(rule, $list_alt22, done_var), list(CDESTRUCTURING_BIND, list(rule_var, template_var, constraints_var), rule, bq_cons(PROGN, append(body, NIL))));
                            }
                        } else {
                            cdestructuring_bind_error(datum, $list_alt19);
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Iterate through instances of #$NounCompoundRule, where
     *
     * @param RULE-VAR
     * 		holds the name of the rule (e.g. #$TourHighlights-NCR)
     * @param TEMPLATE-VAR
     * 		holds the value of #$ncRuleTemplate for that rule
     * @param CONSTRAINTS-VAR
     * 		holds the #$ncRuleConstraint-s for that rule, until
     * @param DONE-VAR
     * 		is non-nil.
     */
    @LispMethod(comment = "Iterate through instances of #$NounCompoundRule, where\r\n\r\n@param RULE-VAR\r\n\t\tholds the name of the rule (e.g. #$TourHighlights-NCR)\r\n@param TEMPLATE-VAR\r\n\t\tholds the value of #$ncRuleTemplate for that rule\r\n@param CONSTRAINTS-VAR\r\n\t\tholds the #$ncRuleConstraint-s for that rule, until\r\n@param DONE-VAR\r\n\t\tis non-nil.")
    public static SubLObject do_nc_rules(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list19);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject rule_var = NIL;
        SubLObject template_var = NIL;
        SubLObject constraints_var = NIL;
        destructuring_bind_must_consp(current, datum, $list19);
        rule_var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list19);
        template_var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list19);
        constraints_var = current.first();
        current = current.rest();
        final SubLObject done_var = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list19);
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            final SubLObject rule = $sym20$RULE;
            return list(CSOME, list(rule, $list22, done_var), list(CDESTRUCTURING_BIND, list(rule_var, template_var, constraints_var), rule, bq_cons(PROGN, append(body, NIL))));
        }
        cdestructuring_bind_error(datum, $list19);
        return NIL;
    }/**
     * Iterate through instances of #$NounCompoundRule, where
     *
     * @param RULE-VAR
     * 		holds the name of the rule (e.g. #$TourHighlights-NCR)
     * @param TEMPLATE-VAR
     * 		holds the value of #$ncRuleTemplate for that rule
     * @param CONSTRAINTS-VAR
     * 		holds the #$ncRuleConstraint-s for that rule, until
     * @param DONE-VAR
     * 		is non-nil.
     */


    /**
     * Generate PHRASE as a noun compound
     */
    @LispMethod(comment = "Generate PHRASE as a noun compound")
    public static final SubLObject generate_noun_compound_phrase_alt(SubLObject phrase) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == pph_utilities.pph_current_precision()) {
                return NIL;
            }
            {
                SubLObject naut = function_terms.nart_to_naut(pph_phrase.pph_phrase_cycl(phrase, UNPROVIDED));
                thread.resetMultipleValues();
                {
                    SubLObject rule = com.cyc.cycjava.cycl.pph_noun_compound.pph_find_nc_rule_for_naut(naut);
                    SubLObject mod_cycl = thread.secondMultipleValue();
                    SubLObject head_cycl = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL != forts.fort_p(rule)) {
                        {
                            SubLObject syn_constraints = com.cyc.cycjava.cycl.pph_noun_compound.organize_rule_syntactic_constraints(rule);
                            SubLObject mod_phrase = pph_phrase.new_pph_phrase_for_cycl(mod_cycl, UNPROVIDED, UNPROVIDED);
                            SubLObject head_phrase = pph_phrase.new_pph_phrase_for_cycl(head_cycl, UNPROVIDED, UNPROVIDED);
                            com.cyc.cycjava.cycl.pph_noun_compound.set_nc_phrase_constraints(syn_constraints, head_phrase, mod_phrase);
                            pph_phrase.set_pph_phrase_dtrs_from_list(phrase, list(mod_phrase, head_phrase), ONE_INTEGER);
                            pph_phrase_resolution.pph_phrase_get_string(phrase, UNPROVIDED, UNPROVIDED);
                        }
                        pph_phrase.pph_phrase_add_justification(phrase, rule);
                    }
                }
            }
            return pph_phrase.pph_phrase_output_list(phrase);
        }
    }

    /**
     * Generate PHRASE as a noun compound
     */
    @LispMethod(comment = "Generate PHRASE as a noun compound")
    public static SubLObject generate_noun_compound_phrase(final SubLObject phrase) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == pph_utilities.pph_current_precision()) {
            return NIL;
        }
        final SubLObject naut = function_terms.nart_to_naut(pph_phrase.pph_phrase_cycl(phrase, UNPROVIDED));
        thread.resetMultipleValues();
        final SubLObject rule = pph_find_nc_rule_for_naut(naut);
        final SubLObject mod_cycl = thread.secondMultipleValue();
        final SubLObject head_cycl = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        if (NIL != forts.fort_p(rule)) {
            final SubLObject syn_constraints = organize_rule_syntactic_constraints(rule);
            final SubLObject mod_phrase = pph_phrase.new_pph_phrase_for_cycl(mod_cycl, UNPROVIDED, UNPROVIDED);
            final SubLObject head_phrase = pph_phrase.new_pph_phrase_for_cycl(head_cycl, UNPROVIDED, UNPROVIDED);
            set_nc_phrase_constraints(syn_constraints, head_phrase, mod_phrase);
            pph_phrase.set_pph_phrase_dtrs_from_list(phrase, list(mod_phrase, head_phrase), ONE_INTEGER);
            pph_phrase_resolution.pph_phrase_get_string(phrase, UNPROVIDED, UNPROVIDED);
            pph_phrase.pph_phrase_add_justification(phrase, rule);
        }
        return pph_phrase.pph_phrase_output_list(phrase);
    }/**
     * Generate PHRASE as a noun compound
     */


    /**
     * Find a noun-compound rule to paraphrase NAUT.
     */
    @LispMethod(comment = "Find a noun-compound rule to paraphrase NAUT.")
    public static final SubLObject pph_find_nc_rule_for_naut_internal_alt(SubLObject naut) {
        {
            SubLObject found_rule = NIL;
            SubLObject mod_cycl = NIL;
            SubLObject head_cycl = NIL;
            if (NIL == found_rule) {
                {
                    SubLObject csome_list_var = com.cyc.cycjava.cycl.pph_noun_compound.pph_nc_rule_list(UNPROVIDED);
                    SubLObject rule = NIL;
                    for (rule = csome_list_var.first(); !((NIL != found_rule) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , rule = csome_list_var.first()) {
                        {
                            SubLObject datum = rule;
                            SubLObject current = datum;
                            SubLObject rule_39 = NIL;
                            SubLObject template = NIL;
                            SubLObject constraints = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt40);
                            rule_39 = current.first();
                            current = current.rest();
                            destructuring_bind_must_consp(current, datum, $list_alt40);
                            template = current.first();
                            current = current.rest();
                            destructuring_bind_must_consp(current, datum, $list_alt40);
                            constraints = current.first();
                            current = current.rest();
                            if (NIL == current) {
                                template = function_terms.nart_to_naut(template);
                                {
                                    SubLObject v_bindings = com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_match_template(template, naut, constraints);
                                    if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_bindings_completeP(v_bindings)) {
                                        mod_cycl = com.cyc.cycjava.cycl.pph_noun_compound.bindings_get_modifier_target(v_bindings);
                                        head_cycl = com.cyc.cycjava.cycl.pph_noun_compound.bindings_get_head_target(v_bindings);
                                        found_rule = rule_39;
                                    }
                                }
                            } else {
                                cdestructuring_bind_error(datum, $list_alt40);
                            }
                        }
                    }
                }
            }
            return values(found_rule, mod_cycl, head_cycl);
        }
    }

    /**
     * Find a noun-compound rule to paraphrase NAUT.
     */
    @LispMethod(comment = "Find a noun-compound rule to paraphrase NAUT.")
    public static SubLObject pph_find_nc_rule_for_naut_internal(final SubLObject naut) {
        SubLObject found_rule = NIL;
        SubLObject mod_cycl = NIL;
        SubLObject head_cycl = NIL;
        if (NIL == found_rule) {
            SubLObject csome_list_var = pph_nc_rule_list(UNPROVIDED);
            SubLObject rule = NIL;
            rule = csome_list_var.first();
            while ((NIL == found_rule) && (NIL != csome_list_var)) {
                SubLObject current;
                final SubLObject datum = current = rule;
                SubLObject rule_$35 = NIL;
                SubLObject template = NIL;
                SubLObject constraints = NIL;
                destructuring_bind_must_consp(current, datum, $list40);
                rule_$35 = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list40);
                template = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list40);
                constraints = current.first();
                current = current.rest();
                if (NIL == current) {
                    template = function_terms.nart_to_naut(template);
                    final SubLObject v_bindings = nc_rule_match_template(template, naut, constraints);
                    if (NIL != nc_rule_bindings_completeP(v_bindings)) {
                        mod_cycl = bindings_get_modifier_target(v_bindings);
                        head_cycl = bindings_get_head_target(v_bindings);
                        found_rule = rule_$35;
                    }
                } else {
                    cdestructuring_bind_error(datum, $list40);
                }
                csome_list_var = csome_list_var.rest();
                rule = csome_list_var.first();
            } 
        }
        return values(found_rule, mod_cycl, head_cycl);
    }/**
     * Find a noun-compound rule to paraphrase NAUT.
     */


    public static final SubLObject pph_find_nc_rule_for_naut_alt(SubLObject naut) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.pph_noun_compound.pph_find_nc_rule_for_naut_internal(naut);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, PPH_FIND_NC_RULE_FOR_NAUT, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), PPH_FIND_NC_RULE_FOR_NAUT, ONE_INTEGER, NIL, EQUALP, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, PPH_FIND_NC_RULE_FOR_NAUT, caching_state);
                }
                {
                    SubLObject results = memoization_state.caching_state_lookup(caching_state, naut, $kw18$_MEMOIZED_ITEM_NOT_FOUND_);
                    if (results == $kw18$_MEMOIZED_ITEM_NOT_FOUND_) {
                        results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.pph_noun_compound.pph_find_nc_rule_for_naut_internal(naut)));
                        memoization_state.caching_state_put(caching_state, naut, results, UNPROVIDED);
                    }
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject pph_find_nc_rule_for_naut(final SubLObject naut) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return pph_find_nc_rule_for_naut_internal(naut);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, PPH_FIND_NC_RULE_FOR_NAUT, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), PPH_FIND_NC_RULE_FOR_NAUT, ONE_INTEGER, NIL, EQUALP, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, PPH_FIND_NC_RULE_FOR_NAUT, caching_state);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, naut, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(thread.resetMultipleValues(), multiple_value_list(pph_find_nc_rule_for_naut_internal(naut)));
            memoization_state.caching_state_put(caching_state, naut, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    /**
     * Generate CYCL using a #$NounCompoundRule.
     *
     * @unknown (#$SubcollectionOfWithRelationToTypeFn #$Event #$subEvents #$Kissing) --> 'kissing event'
     */
    @LispMethod(comment = "Generate CYCL using a #$NounCompoundRule.\r\n\r\n@unknown (#$SubcollectionOfWithRelationToTypeFn #$Event #$subEvents #$Kissing) --> \'kissing event\'")
    public static final SubLObject generate_noun_compound_alt(SubLObject cycl) {
        {
            SubLObject phrase = pph_phrase.new_pph_phrase_for_cycl(cycl, UNPROVIDED, UNPROVIDED);
            com.cyc.cycjava.cycl.pph_noun_compound.generate_noun_compound_phrase(phrase);
            return pph_phrase.pph_phrase_string(phrase, UNPROVIDED);
        }
    }

    /**
     * Generate CYCL using a #$NounCompoundRule.
     *
     * @unknown (#$SubcollectionOfWithRelationToTypeFn #$Event #$subEvents #$Kissing) --> 'kissing event'
     */
    @LispMethod(comment = "Generate CYCL using a #$NounCompoundRule.\r\n\r\n@unknown (#$SubcollectionOfWithRelationToTypeFn #$Event #$subEvents #$Kissing) --> \'kissing event\'")
    public static SubLObject generate_noun_compound(final SubLObject cycl) {
        final SubLObject phrase = pph_phrase.new_pph_phrase_for_cycl(cycl, UNPROVIDED, UNPROVIDED);
        generate_noun_compound_phrase(phrase);
        return pph_phrase.pph_phrase_string(phrase, UNPROVIDED);
    }/**
     * Generate CYCL using a #$NounCompoundRule.
     *
     * @unknown (#$SubcollectionOfWithRelationToTypeFn #$Event #$subEvents #$Kissing) --> 'kissing event'
     */


    /**
     *
     *
     * @return LISTP of strings corresponding to possible noun compounds for CYCL.
     */
    @LispMethod(comment = "@return LISTP of strings corresponding to possible noun compounds for CYCL.")
    public static final SubLObject all_noun_compounds_for_term_alt(SubLObject cycl) {
        {
            SubLObject noun_compounds = NIL;
            SubLObject csome_list_var = com.cyc.cycjava.cycl.pph_noun_compound.pph_nc_rule_list(UNPROVIDED);
            SubLObject rule = NIL;
            for (rule = csome_list_var.first(); NIL != csome_list_var; csome_list_var = csome_list_var.rest() , rule = csome_list_var.first()) {
                {
                    SubLObject datum = rule;
                    SubLObject current = datum;
                    SubLObject rule_40 = NIL;
                    SubLObject template = NIL;
                    SubLObject constraints = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt40);
                    rule_40 = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt40);
                    template = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt40);
                    constraints = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        template = function_terms.nart_to_naut(template);
                        {
                            SubLObject v_bindings = com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_match_template(template, cycl, constraints);
                            if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_bindings_completeP(v_bindings)) {
                                {
                                    SubLObject syn_constraints = com.cyc.cycjava.cycl.pph_noun_compound.organize_rule_syntactic_constraints(rule_40);
                                    SubLObject mod_phrase = pph_phrase.new_pph_phrase_for_cycl(com.cyc.cycjava.cycl.pph_noun_compound.bindings_get_modifier_target(v_bindings), UNPROVIDED, UNPROVIDED);
                                    SubLObject head_phrase = pph_phrase.new_pph_phrase_for_cycl(com.cyc.cycjava.cycl.pph_noun_compound.bindings_get_head_target(v_bindings), UNPROVIDED, UNPROVIDED);
                                    SubLObject phrase = pph_phrase.new_pph_phrase_for_cycl(cycl, UNPROVIDED, UNPROVIDED);
                                    com.cyc.cycjava.cycl.pph_noun_compound.set_nc_phrase_constraints(syn_constraints, head_phrase, mod_phrase);
                                    pph_phrase.set_pph_phrase_dtrs_from_list(phrase, list(mod_phrase, head_phrase), UNPROVIDED);
                                    pph_phrase.pph_phrase_set_head_dtr_num(phrase, ONE_INTEGER);
                                    pph_phrase_resolution.pph_phrase_get_string(phrase, UNPROVIDED, UNPROVIDED);
                                    if (NIL != pph_phrase.pph_phrase_doneP(phrase)) {
                                        noun_compounds = cons(pph_phrase.pph_phrase_string(phrase, UNPROVIDED), noun_compounds);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt40);
                    }
                }
            }
            return noun_compounds;
        }
    }

    /**
     *
     *
     * @return LISTP of strings corresponding to possible noun compounds for CYCL.
     */
    @LispMethod(comment = "@return LISTP of strings corresponding to possible noun compounds for CYCL.")
    public static SubLObject all_noun_compounds_for_term(final SubLObject cycl) {
        SubLObject noun_compounds = NIL;
        SubLObject csome_list_var = pph_nc_rule_list(UNPROVIDED);
        SubLObject rule = NIL;
        rule = csome_list_var.first();
        while (NIL != csome_list_var) {
            SubLObject current;
            final SubLObject datum = current = rule;
            SubLObject rule_$36 = NIL;
            SubLObject template = NIL;
            SubLObject constraints = NIL;
            destructuring_bind_must_consp(current, datum, $list40);
            rule_$36 = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list40);
            template = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list40);
            constraints = current.first();
            current = current.rest();
            if (NIL == current) {
                template = function_terms.nart_to_naut(template);
                final SubLObject v_bindings = nc_rule_match_template(template, cycl, constraints);
                if (NIL != nc_rule_bindings_completeP(v_bindings)) {
                    final SubLObject syn_constraints = organize_rule_syntactic_constraints(rule_$36);
                    final SubLObject mod_phrase = pph_phrase.new_pph_phrase_for_cycl(bindings_get_modifier_target(v_bindings), UNPROVIDED, UNPROVIDED);
                    final SubLObject head_phrase = pph_phrase.new_pph_phrase_for_cycl(bindings_get_head_target(v_bindings), UNPROVIDED, UNPROVIDED);
                    final SubLObject phrase = pph_phrase.new_pph_phrase_for_cycl(cycl, UNPROVIDED, UNPROVIDED);
                    set_nc_phrase_constraints(syn_constraints, head_phrase, mod_phrase);
                    pph_phrase.set_pph_phrase_dtrs_from_list(phrase, list(mod_phrase, head_phrase), UNPROVIDED);
                    pph_phrase.pph_phrase_set_head_dtr_num(phrase, ONE_INTEGER);
                    pph_phrase_resolution.pph_phrase_get_string(phrase, UNPROVIDED, UNPROVIDED);
                    if (NIL != pph_phrase.pph_phrase_doneP(phrase)) {
                        noun_compounds = cons(pph_phrase.pph_phrase_string(phrase, UNPROVIDED), noun_compounds);
                    }
                }
            } else {
                cdestructuring_bind_error(datum, $list40);
            }
            csome_list_var = csome_list_var.rest();
            rule = csome_list_var.first();
        } 
        return noun_compounds;
    }

    public static final SubLObject nc_rule_pos_constraint_p_alt(SubLObject constraint) {
        return eq(com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_op(constraint), $nc_pos_constraint_fn$.getGlobalValue());
    }

    public static SubLObject nc_rule_pos_constraint_p(final SubLObject constraint) {
        return eq(nc_constraint_op(constraint), $nc_pos_constraint_fn$.getGlobalValue());
    }

    public static final SubLObject nc_rule_pos_pred_constraint_p_alt(SubLObject constraint) {
        return eq(com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_op(constraint), $nc_pos_pred_constraint_fn$.getGlobalValue());
    }

    public static SubLObject nc_rule_pos_pred_constraint_p(final SubLObject constraint) {
        return eq(nc_constraint_op(constraint), $nc_pos_pred_constraint_fn$.getGlobalValue());
    }

    public static final SubLObject nc_rule_modifier_constraint_p_alt(SubLObject constraint) {
        return eq(com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_subject(constraint), $the_nc_modifier$.getGlobalValue());
    }

    public static SubLObject nc_rule_modifier_constraint_p(final SubLObject constraint) {
        return eq(nc_constraint_subject(constraint), $the_nc_modifier$.getGlobalValue());
    }

    public static final SubLObject nc_rule_head_constraint_p_alt(SubLObject constraint) {
        return eq(com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_subject(constraint), $the_nc_head$.getGlobalValue());
    }

    public static SubLObject nc_rule_head_constraint_p(final SubLObject constraint) {
        return eq(nc_constraint_subject(constraint), $the_nc_head$.getGlobalValue());
    }

    public static final SubLObject nc_rule_indexical_p_alt(SubLObject base) {
        return subl_promotions.memberP(base, list($the_nc_head$.getGlobalValue(), $the_nc_modifier$.getGlobalValue()), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject nc_rule_indexical_p(final SubLObject base) {
        return subl_promotions.memberP(base, list($the_nc_head$.getGlobalValue(), $the_nc_modifier$.getGlobalValue()), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject nc_rule_semantic_constraint_p_alt(SubLObject constraint) {
        return member(com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_op(constraint), list($nc_isa_constraint_fn$.getGlobalValue(), $nc_genls_constraint_fn$.getGlobalValue(), $nc_equals_constraint_fn$.getGlobalValue()), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject nc_rule_semantic_constraint_p(final SubLObject constraint) {
        return member(nc_constraint_op(constraint), list($nc_isa_constraint_fn$.getGlobalValue(), $nc_genls_constraint_fn$.getGlobalValue(), $nc_equals_constraint_fn$.getGlobalValue()), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject nc_constraint_op_alt(SubLObject constraint) {
        return cycl_utilities.nat_arg0(constraint);
    }

    public static SubLObject nc_constraint_op(final SubLObject constraint) {
        return cycl_utilities.nat_arg0(constraint);
    }

    public static final SubLObject nc_constraint_subject_alt(SubLObject constraint) {
        return cycl_utilities.nat_arg1(constraint, UNPROVIDED);
    }

    public static SubLObject nc_constraint_subject(final SubLObject constraint) {
        return cycl_utilities.nat_arg1(constraint, UNPROVIDED);
    }

    public static final SubLObject nc_constraint_object_alt(SubLObject constraint) {
        return cycl_utilities.nat_arg2(constraint, UNPROVIDED);
    }

    public static SubLObject nc_constraint_object(final SubLObject constraint) {
        return cycl_utilities.nat_arg2(constraint, UNPROVIDED);
    }

    public static final SubLObject binding_base_alt(SubLObject binding) {
        return binding.first();
    }

    public static SubLObject binding_base(final SubLObject binding) {
        return binding.first();
    }

    public static final SubLObject binding_target_alt(SubLObject binding) {
        return binding.rest();
    }

    public static SubLObject binding_target(final SubLObject binding) {
        return binding.rest();
    }

    public static final SubLObject nc_rule_bindings_completeP_alt(SubLObject v_bindings) {
        return makeBoolean(v_bindings.isList() && (NIL != list_utilities.lengthE(v_bindings, TWO_INTEGER, UNPROVIDED)));
    }

    public static SubLObject nc_rule_bindings_completeP(final SubLObject v_bindings) {
        return makeBoolean(v_bindings.isList() && (NIL != list_utilities.lengthE(v_bindings, TWO_INTEGER, UNPROVIDED)));
    }

    /**
     * Get the item in BINDINGS associated with #$TheNCModifier
     *
     * @unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?
     */
    @LispMethod(comment = "Get the item in BINDINGS associated with #$TheNCModifier\r\n\r\n@unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?")
    public static final SubLObject bindings_get_modifier_target_alt(SubLObject v_bindings) {
        {
            SubLObject binding = assoc($the_nc_modifier$.getGlobalValue(), v_bindings, UNPROVIDED, UNPROVIDED);
            return com.cyc.cycjava.cycl.pph_noun_compound.binding_target(binding);
        }
    }

    /**
     * Get the item in BINDINGS associated with #$TheNCModifier
     *
     * @unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?
     */
    @LispMethod(comment = "Get the item in BINDINGS associated with #$TheNCModifier\r\n\r\n@unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?")
    public static SubLObject bindings_get_modifier_target(final SubLObject v_bindings) {
        final SubLObject binding = assoc($the_nc_modifier$.getGlobalValue(), v_bindings, UNPROVIDED, UNPROVIDED);
        return binding_target(binding);
    }/**
     * Get the item in BINDINGS associated with #$TheNCModifier
     *
     * @unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?
     */


    /**
     * Get the item in BINDINGS associated with #$TheNCHead
     *
     * @unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?
     */
    @LispMethod(comment = "Get the item in BINDINGS associated with #$TheNCHead\r\n\r\n@unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?")
    public static final SubLObject bindings_get_head_target_alt(SubLObject v_bindings) {
        {
            SubLObject binding = assoc($the_nc_head$.getGlobalValue(), v_bindings, UNPROVIDED, UNPROVIDED);
            return com.cyc.cycjava.cycl.pph_noun_compound.binding_target(binding);
        }
    }

    /**
     * Get the item in BINDINGS associated with #$TheNCHead
     *
     * @unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?
     */
    @LispMethod(comment = "Get the item in BINDINGS associated with #$TheNCHead\r\n\r\n@unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?")
    public static SubLObject bindings_get_head_target(final SubLObject v_bindings) {
        final SubLObject binding = assoc($the_nc_head$.getGlobalValue(), v_bindings, UNPROVIDED, UNPROVIDED);
        return binding_target(binding);
    }/**
     * Get the item in BINDINGS associated with #$TheNCHead
     *
     * @unknown assumes BINDINGS already passed NC-RULE-BINDINGS-COMPLETE?
     */


    /**
     * Use CONSTRAINTS to set the agreement information on HEAD-PHRASE and MOD-PHRASE.
     *
     * @param CONSTRAINTS
     * 		an organized list of noun compound rule constraints.
     * @param HEAD-PHRASE;
    PPH-PHRASE-P.
     * 		
     * @param MOD-PHRASE;
    PPH-PHRASE-P.
     * 		
     * @return NIL.
     */
    @LispMethod(comment = "Use CONSTRAINTS to set the agreement information on HEAD-PHRASE and MOD-PHRASE.\r\n\r\n@param CONSTRAINTS\r\n\t\tan organized list of noun compound rule constraints.\r\n@param HEAD-PHRASE;\nPPH-PHRASE-P.\r\n\t\t\r\n@param MOD-PHRASE;\nPPH-PHRASE-P.\r\n\t\t\r\n@return NIL.")
    public static final SubLObject set_nc_phrase_constraints_alt(SubLObject constraints, SubLObject head_phrase, SubLObject mod_phrase) {
        {
            SubLObject datum = constraints;
            SubLObject current = datum;
            SubLObject head_pos_constraint = NIL;
            SubLObject mod_pos_constraint = NIL;
            SubLObject head_pos_pred_constraint = NIL;
            SubLObject mod_pos_pred_constraint = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt41);
            head_pos_constraint = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt41);
            mod_pos_constraint = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt41);
            head_pos_pred_constraint = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt41);
            mod_pos_pred_constraint = current.first();
            current = current.rest();
            if (NIL == current) {
                pph_phrase.pph_phrase_set_category(head_phrase, NIL != head_pos_constraint ? ((SubLObject) (head_pos_constraint)) : $$CountNoun, UNPROVIDED);
                pph_phrase.pph_phrase_set_category(mod_phrase, mod_pos_constraint, UNPROVIDED);
                pph_phrase.pph_phrase_set_agr_pred_unless_nil(head_phrase, head_pos_pred_constraint);
                pph_phrase.pph_phrase_set_agr_pred_unless_nil(mod_phrase, mod_pos_pred_constraint);
            } else {
                cdestructuring_bind_error(datum, $list_alt41);
            }
        }
        return values(head_phrase, mod_phrase);
    }

    /**
     * Use CONSTRAINTS to set the agreement information on HEAD-PHRASE and MOD-PHRASE.
     *
     * @param CONSTRAINTS
     * 		an organized list of noun compound rule constraints.
     * @param HEAD-PHRASE;
    		PPH-PHRASE-P.
     * 		
     * @param MOD-PHRASE;
    		PPH-PHRASE-P.
     * 		
     * @return NIL.
     */
    @LispMethod(comment = "Use CONSTRAINTS to set the agreement information on HEAD-PHRASE and MOD-PHRASE.\r\n\r\n@param CONSTRAINTS\r\n\t\tan organized list of noun compound rule constraints.\r\n@param HEAD-PHRASE;\n\t\tPPH-PHRASE-P.\r\n\t\t\r\n@param MOD-PHRASE;\n\t\tPPH-PHRASE-P.\r\n\t\t\r\n@return NIL.")
    public static SubLObject set_nc_phrase_constraints(final SubLObject constraints, final SubLObject head_phrase, final SubLObject mod_phrase) {
        SubLObject head_pos_constraint = NIL;
        SubLObject mod_pos_constraint = NIL;
        SubLObject head_pos_pred_constraint = NIL;
        SubLObject mod_pos_pred_constraint = NIL;
        destructuring_bind_must_consp(constraints, constraints, $list41);
        head_pos_constraint = constraints.first();
        SubLObject current = constraints.rest();
        destructuring_bind_must_consp(current, constraints, $list41);
        mod_pos_constraint = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, constraints, $list41);
        head_pos_pred_constraint = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, constraints, $list41);
        mod_pos_pred_constraint = current.first();
        current = current.rest();
        if (NIL == current) {
            pph_phrase.pph_phrase_set_category(head_phrase, NIL != head_pos_constraint ? head_pos_constraint : $$CountNoun, UNPROVIDED);
            pph_phrase.pph_phrase_set_category(mod_phrase, mod_pos_constraint, UNPROVIDED);
            pph_phrase.pph_phrase_set_agr_pred_unless_nil(head_phrase, head_pos_pred_constraint);
            pph_phrase.pph_phrase_set_agr_pred_unless_nil(mod_phrase, mod_pos_pred_constraint);
        } else {
            cdestructuring_bind_error(constraints, $list41);
        }
        return values(head_phrase, mod_phrase);
    }/**
     * Use CONSTRAINTS to set the agreement information on HEAD-PHRASE and MOD-PHRASE.
     *
     * @param CONSTRAINTS
     * 		an organized list of noun compound rule constraints.
     * @param HEAD-PHRASE;
    		PPH-PHRASE-P.
     * 		
     * @param MOD-PHRASE;
    		PPH-PHRASE-P.
     * 		
     * @return NIL.
     */


    /**
     * Put the syntactic constraints of RULE into an organized list
     */
    @LispMethod(comment = "Put the syntactic constraints of RULE into an organized list")
    public static final SubLObject organize_rule_syntactic_constraints_alt(SubLObject rule) {
        {
            SubLObject constraints = kb_mapping_utilities.pred_values_in_mt(rule, $nc_rule_constraint$.getGlobalValue(), $nc_mt$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
            return com.cyc.cycjava.cycl.pph_noun_compound.organize_syntactic_constraints(constraints);
        }
    }

    /**
     * Put the syntactic constraints of RULE into an organized list
     */
    @LispMethod(comment = "Put the syntactic constraints of RULE into an organized list")
    public static SubLObject organize_rule_syntactic_constraints(final SubLObject rule) {
        final SubLObject constraints = kb_mapping_utilities.pred_values_in_mt(rule, $nc_rule_constraint$.getGlobalValue(), $nc_mt$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED);
        return organize_syntactic_constraints(constraints);
    }/**
     * Put the syntactic constraints of RULE into an organized list
     */


    /**
     * Organize CONSTRAINTS into a list of the form:
     *
     * @return LISTP (<head-pos> <head-pos-pred> <mod-pos> <mod-pos-pred>).
     */
    @LispMethod(comment = "Organize CONSTRAINTS into a list of the form:\r\n\r\n@return LISTP (<head-pos> <head-pos-pred> <mod-pos> <mod-pos-pred>).")
    public static final SubLObject organize_syntactic_constraints_alt(SubLObject constraints) {
        {
            SubLObject head_pos = NIL;
            SubLObject head_pos_pred = NIL;
            SubLObject mod_pos = NIL;
            SubLObject mod_pos_pred = NIL;
            SubLObject cdolist_list_var = constraints;
            SubLObject constraint = NIL;
            for (constraint = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , constraint = cdolist_list_var.first()) {
                {
                    SubLObject obj = com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_object(constraint);
                    if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_pos_constraint_p(constraint)) {
                        if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_head_constraint_p(constraint)) {
                            head_pos = obj;
                        } else {
                            if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_modifier_constraint_p(constraint)) {
                                mod_pos = obj;
                            }
                        }
                    } else {
                        if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_pos_pred_constraint_p(constraint)) {
                            if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_head_constraint_p(constraint)) {
                                head_pos_pred = obj;
                            } else {
                                if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_modifier_constraint_p(constraint)) {
                                    mod_pos_pred = obj;
                                }
                            }
                        }
                    }
                }
            }
            return list(head_pos, head_pos_pred, mod_pos, mod_pos_pred);
        }
    }

    /**
     * Organize CONSTRAINTS into a list of the form:
     *
     * @return LISTP (<head-pos> <head-pos-pred> <mod-pos> <mod-pos-pred>).
     */
    @LispMethod(comment = "Organize CONSTRAINTS into a list of the form:\r\n\r\n@return LISTP (<head-pos> <head-pos-pred> <mod-pos> <mod-pos-pred>).")
    public static SubLObject organize_syntactic_constraints(final SubLObject constraints) {
        SubLObject head_pos = NIL;
        SubLObject head_pos_pred = NIL;
        SubLObject mod_pos = NIL;
        SubLObject mod_pos_pred = NIL;
        SubLObject cdolist_list_var = constraints;
        SubLObject constraint = NIL;
        constraint = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject obj = nc_constraint_object(constraint);
            if (NIL != nc_rule_pos_constraint_p(constraint)) {
                if (NIL != nc_rule_head_constraint_p(constraint)) {
                    head_pos = obj;
                } else
                    if (NIL != nc_rule_modifier_constraint_p(constraint)) {
                        mod_pos = obj;
                    }

            } else
                if (NIL != nc_rule_pos_pred_constraint_p(constraint)) {
                    if (NIL != nc_rule_head_constraint_p(constraint)) {
                        head_pos_pred = obj;
                    } else
                        if (NIL != nc_rule_modifier_constraint_p(constraint)) {
                            mod_pos_pred = obj;
                        }

                }

            cdolist_list_var = cdolist_list_var.rest();
            constraint = cdolist_list_var.first();
        } 
        return list(head_pos, head_pos_pred, mod_pos, mod_pos_pred);
    }/**
     * Organize CONSTRAINTS into a list of the form:
     *
     * @return LISTP (<head-pos> <head-pos-pred> <mod-pos> <mod-pos-pred>).
     */


    /**
     * Does NAT match TEMPLATE, with CONSTRAINTS?
     *
     * @return LISTP of bindings or NIL.
     */
    @LispMethod(comment = "Does NAT match TEMPLATE, with CONSTRAINTS?\r\n\r\n@return LISTP of bindings or NIL.")
    public static final SubLObject nc_rule_match_template_alt(SubLObject template, SubLObject nat, SubLObject constraints) {
        template = function_terms.nart_to_naut(template);
        if (!(((NIL != el_formula_without_sequence_termP(template)) && (NIL != el_formula_without_sequence_termP(nat))) && (NIL != list_utilities.same_length_p(template, nat)))) {
            return NIL;
        }
        {
            SubLObject v_bindings = NIL;
            SubLObject failP = NIL;
            if (NIL == failP) {
                {
                    SubLObject base = NIL;
                    SubLObject base_41 = NIL;
                    SubLObject target = NIL;
                    SubLObject target_42 = NIL;
                    for (base = template, base_41 = base.first(), target = nat, target_42 = target.first(); !((NIL != failP) || ((NIL == target) && (NIL == base))); base = base.rest() , base_41 = base.first() , target = target.rest() , target_42 = target.first()) {
                        if ((NIL != el_formula_p(base_41)) || (NIL != el_formula_p(target_42))) {
                            {
                                SubLObject match = com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_match_template(base_41, target_42, constraints);
                                if (NIL != match) {
                                    if (match.isList()) {
                                        v_bindings = append(match, v_bindings);
                                    }
                                } else {
                                    failP = T;
                                }
                            }
                        } else {
                            {
                                SubLObject match = com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_pat_match(base_41, target_42, constraints);
                                if (NIL != match) {
                                    if (match.isCons()) {
                                        v_bindings = cons(match, v_bindings);
                                    }
                                } else {
                                    failP = T;
                                }
                            }
                        }
                    }
                }
            }
            if (NIL != failP) {
                return NIL;
            } else {
                return NIL != v_bindings ? ((SubLObject) (v_bindings)) : T;
            }
        }
    }

    /**
     * Does NAT match TEMPLATE, with CONSTRAINTS?
     *
     * @return LISTP of bindings or NIL.
     */
    @LispMethod(comment = "Does NAT match TEMPLATE, with CONSTRAINTS?\r\n\r\n@return LISTP of bindings or NIL.")
    public static SubLObject nc_rule_match_template(SubLObject template, final SubLObject nat, final SubLObject constraints) {
        template = function_terms.nart_to_naut(template);
        if (((NIL == el_formula_without_sequence_termP(template)) || (NIL == el_formula_without_sequence_termP(nat))) || (NIL == list_utilities.same_length_p(template, nat))) {
            return NIL;
        }
        SubLObject v_bindings = NIL;
        SubLObject failP = NIL;
        if (NIL == failP) {
            SubLObject base = NIL;
            SubLObject base_$37 = NIL;
            SubLObject target = NIL;
            SubLObject target_$38 = NIL;
            base = template;
            base_$37 = base.first();
            target = nat;
            target_$38 = target.first();
            while ((NIL == failP) && ((NIL != target) || (NIL != base))) {
                if ((NIL != el_formula_p(base_$37)) || (NIL != el_formula_p(target_$38))) {
                    final SubLObject match = nc_rule_match_template(base_$37, target_$38, constraints);
                    if (NIL != match) {
                        if (match.isList()) {
                            v_bindings = append(match, v_bindings);
                        }
                    } else {
                        failP = T;
                    }
                } else {
                    final SubLObject match = nc_rule_pat_match(base_$37, target_$38, constraints);
                    if (NIL != match) {
                        if (match.isCons()) {
                            v_bindings = cons(match, v_bindings);
                        }
                    } else {
                        failP = T;
                    }
                }
                base = base.rest();
                base_$37 = base.first();
                target = target.rest();
                target_$38 = target.first();
            } 
        }
        if (NIL != failP) {
            return NIL;
        }
        return NIL != v_bindings ? v_bindings : T;
    }/**
     * Does NAT match TEMPLATE, with CONSTRAINTS?
     *
     * @return LISTP of bindings or NIL.
     */


    /**
     * See if BINDING is a valid binding, according to CONSTRAINTS.
     *
     * @return BINDING, T, or NIL.
     */
    @LispMethod(comment = "See if BINDING is a valid binding, according to CONSTRAINTS.\r\n\r\n@return BINDING, T, or NIL.")
    public static final SubLObject nc_rule_pat_match_alt(SubLObject base, SubLObject target, SubLObject constraints) {
        if (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_indexical_p(base)) {
            return NIL != com.cyc.cycjava.cycl.pph_noun_compound.binding_satisfies_nc_rule_semantic_constraintsP(base, target, constraints) ? ((SubLObject) (cons(base, target))) : NIL;
        } else {
            if (base == target) {
                return T;
            } else {
                return NIL;
            }
        }
    }

    /**
     * See if BINDING is a valid binding, according to CONSTRAINTS.
     *
     * @return BINDING, T, or NIL.
     */
    @LispMethod(comment = "See if BINDING is a valid binding, according to CONSTRAINTS.\r\n\r\n@return BINDING, T, or NIL.")
    public static SubLObject nc_rule_pat_match(final SubLObject base, final SubLObject target, final SubLObject constraints) {
        if (NIL != nc_rule_indexical_p(base)) {
            return NIL != binding_satisfies_nc_rule_semantic_constraintsP(base, target, constraints) ? cons(base, target) : NIL;
        }
        if (base.eql(target)) {
            return T;
        }
        return NIL;
    }/**
     * See if BINDING is a valid binding, according to CONSTRAINTS.
     *
     * @return BINDING, T, or NIL.
     */


    /**
     * See if BINDING satisfies the semantic constraints among CONSTRAINTS
     */
    @LispMethod(comment = "See if BINDING satisfies the semantic constraints among CONSTRAINTS")
    public static final SubLObject binding_satisfies_nc_rule_semantic_constraintsP_alt(SubLObject base, SubLObject target, SubLObject constraints) {
        {
            SubLObject failP = NIL;
            if (NIL == failP) {
                {
                    SubLObject csome_list_var = constraints;
                    SubLObject constraint = NIL;
                    for (constraint = csome_list_var.first(); !((NIL != failP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , constraint = csome_list_var.first()) {
                        failP = makeBoolean(((NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_semantic_constraint_p(constraint)) && (NIL != com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_appliesP(constraint, base))) && (NIL == com.cyc.cycjava.cycl.pph_noun_compound.target_satisfies_nc_rule_semantic_constraintP(target, constraint)));
                    }
                }
            }
            return makeBoolean(NIL == failP);
        }
    }

    /**
     * See if BINDING satisfies the semantic constraints among CONSTRAINTS
     */
    @LispMethod(comment = "See if BINDING satisfies the semantic constraints among CONSTRAINTS")
    public static SubLObject binding_satisfies_nc_rule_semantic_constraintsP(final SubLObject base, final SubLObject target, final SubLObject constraints) {
        SubLObject failP = NIL;
        if (NIL == failP) {
            SubLObject csome_list_var;
            SubLObject constraint;
            for (csome_list_var = constraints, constraint = NIL, constraint = csome_list_var.first(); (NIL == failP) && (NIL != csome_list_var); failP = makeBoolean(((NIL != nc_rule_semantic_constraint_p(constraint)) && (NIL != nc_constraint_appliesP(constraint, base))) && (NIL == target_satisfies_nc_rule_semantic_constraintP(target, constraint))) , csome_list_var = csome_list_var.rest() , constraint = csome_list_var.first()) {
            }
        }
        return makeBoolean(NIL == failP);
    }/**
     * See if BINDING satisfies the semantic constraints among CONSTRAINTS
     */


    /**
     * See if TARGET satisfies CONSTRAINT.
     *
     * @return BOOLEAN.
     * @unknown assumes CONSTRAINT is NC-RULE-SEMANTIC-CONSTRAINT-P.
     */
    @LispMethod(comment = "See if TARGET satisfies CONSTRAINT.\r\n\r\n@return BOOLEAN.\r\n@unknown assumes CONSTRAINT is NC-RULE-SEMANTIC-CONSTRAINT-P.")
    public static final SubLObject target_satisfies_nc_rule_semantic_constraintP_alt(SubLObject target, SubLObject constraint) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == com.cyc.cycjava.cycl.pph_noun_compound.nc_rule_semantic_constraint_p(constraint)) {
                {
                    SubLObject new_format_string = cconcatenate($str_alt43$_PPH_error_level_, new SubLObject[]{ format_nil.format_nil_s_no_copy(ONE_INTEGER), $str_alt44$__, format_nil.format_nil_a_no_copy($str_alt45$___S_isn_t_a_semantic_constraint_) });
                    pph_error.pph_handle_error(new_format_string, list(constraint));
                }
            }
            {
                SubLObject ans = T;
                SubLObject obj = com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_object(constraint);
                SubLObject op = com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_op(constraint);
                if (op == $nc_equals_constraint_fn$.getGlobalValue()) {
                    return equal(target, obj);
                } else {
                    if (op == $nc_genls_constraint_fn$.getGlobalValue()) {
                        {
                            SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                            SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                            try {
                                mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                                mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                                ans = pph_utilities.pph_genlP(target, obj, UNPROVIDED);
                            } finally {
                                mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                            }
                        }
                    } else {
                        if (op == $nc_isa_constraint_fn$.getGlobalValue()) {
                            {
                                SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                                SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                                try {
                                    mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                                    mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                                    ans = pph_utilities.pph_isaP(target, obj, UNPROVIDED);
                                } finally {
                                    mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                                    mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                                }
                            }
                        } else {
                            {
                                SubLObject new_format_string = cconcatenate($str_alt43$_PPH_error_level_, new SubLObject[]{ format_nil.format_nil_s_no_copy(ONE_INTEGER), $str_alt44$__, format_nil.format_nil_a_no_copy($str_alt48$___S_isn_t_a_recognized_noun_comp) });
                                pph_error.pph_handle_error(new_format_string, list(op));
                            }
                        }
                    }
                }
                return ans;
            }
        }
    }

    /**
     * See if TARGET satisfies CONSTRAINT.
     *
     * @return BOOLEAN.
     * @unknown assumes CONSTRAINT is NC-RULE-SEMANTIC-CONSTRAINT-P.
     */
    @LispMethod(comment = "See if TARGET satisfies CONSTRAINT.\r\n\r\n@return BOOLEAN.\r\n@unknown assumes CONSTRAINT is NC-RULE-SEMANTIC-CONSTRAINT-P.")
    public static SubLObject target_satisfies_nc_rule_semantic_constraintP(final SubLObject target, final SubLObject constraint) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == nc_rule_semantic_constraint_p(constraint)) {
            final SubLObject new_format_string = cconcatenate($str43$_PPH_error_level_, new SubLObject[]{ format_nil.format_nil_s_no_copy(ONE_INTEGER), $str44$__, format_nil.format_nil_a_no_copy($str45$___S_isn_t_a_semantic_constraint_) });
            pph_error.pph_handle_error(new_format_string, list(constraint));
        }
        SubLObject ans = T;
        final SubLObject obj = nc_constraint_object(constraint);
        final SubLObject op = nc_constraint_op(constraint);
        if (op.eql($nc_equals_constraint_fn$.getGlobalValue())) {
            return equal(target, obj);
        }
        if (op.eql($nc_genls_constraint_fn$.getGlobalValue())) {
            final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
            final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
            try {
                mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                ans = pph_utilities.pph_genlP(target, obj, UNPROVIDED);
            } finally {
                mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
            }
        } else
            if (op.eql($nc_isa_constraint_fn$.getGlobalValue())) {
                final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
                try {
                    mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                    mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                    ans = pph_utilities.pph_isaP(target, obj, UNPROVIDED);
                } finally {
                    mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
                    mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                }
            } else {
                final SubLObject new_format_string2 = cconcatenate($str43$_PPH_error_level_, new SubLObject[]{ format_nil.format_nil_s_no_copy(ONE_INTEGER), $str44$__, format_nil.format_nil_a_no_copy($str48$___S_isn_t_a_recognized_noun_comp) });
                pph_error.pph_handle_error(new_format_string2, list(op));
            }

        return ans;
    }/**
     * See if TARGET satisfies CONSTRAINT.
     *
     * @return BOOLEAN.
     * @unknown assumes CONSTRAINT is NC-RULE-SEMANTIC-CONSTRAINT-P.
     */


    /**
     * Does CONSTRAINT apply to BASE?
     *
     * @param CONSTRAINT;
     * 		a noun compound rule constraint
     * @param BASE;
     * 		expected to be either #$TheNCHead or #$TheNCModifier.
     */
    @LispMethod(comment = "Does CONSTRAINT apply to BASE?\r\n\r\n@param CONSTRAINT;\r\n\t\ta noun compound rule constraint\r\n@param BASE;\r\n\t\texpected to be either #$TheNCHead or #$TheNCModifier.")
    public static final SubLObject nc_constraint_appliesP_alt(SubLObject constraint, SubLObject base) {
        return eq(com.cyc.cycjava.cycl.pph_noun_compound.nc_constraint_subject(constraint), base);
    }

    @LispMethod(comment = "Does CONSTRAINT apply to BASE?\r\n\r\n@param CONSTRAINT;\r\n\t\ta noun compound rule constraint\r\n@param BASE;\r\n\t\texpected to be either #$TheNCHead or #$TheNCModifier.")
    public static SubLObject nc_constraint_appliesP(final SubLObject constraint, final SubLObject base) {
        return eq(nc_constraint_subject(constraint), base);
    }/**
     * Does CONSTRAINT apply to BASE?
     *
     * @param CONSTRAINT;
     * 		a noun compound rule constraint
     * @param BASE;
     * 		expected to be either #$TheNCHead or #$TheNCModifier.
     */


    public static SubLObject declare_pph_noun_compound_file() {
        declareFunction("clear_pph_nc_rule_list", "CLEAR-PPH-NC-RULE-LIST", 0, 0, false);
        declareFunction("remove_pph_nc_rule_list", "REMOVE-PPH-NC-RULE-LIST", 0, 1, false);
        declareFunction("pph_nc_rule_list_internal", "PPH-NC-RULE-LIST-INTERNAL", 1, 0, false);
        declareFunction("pph_nc_rule_list", "PPH-NC-RULE-LIST", 0, 1, false);
        declareMacro("do_nc_rules", "DO-NC-RULES");
        declareFunction("generate_noun_compound_phrase", "GENERATE-NOUN-COMPOUND-PHRASE", 1, 0, false);
        declareFunction("pph_find_nc_rule_for_naut_internal", "PPH-FIND-NC-RULE-FOR-NAUT-INTERNAL", 1, 0, false);
        declareFunction("pph_find_nc_rule_for_naut", "PPH-FIND-NC-RULE-FOR-NAUT", 1, 0, false);
        declareFunction("generate_noun_compound", "GENERATE-NOUN-COMPOUND", 1, 0, false);
        declareFunction("all_noun_compounds_for_term", "ALL-NOUN-COMPOUNDS-FOR-TERM", 1, 0, false);
        declareFunction("nc_rule_pos_constraint_p", "NC-RULE-POS-CONSTRAINT-P", 1, 0, false);
        declareFunction("nc_rule_pos_pred_constraint_p", "NC-RULE-POS-PRED-CONSTRAINT-P", 1, 0, false);
        declareFunction("nc_rule_modifier_constraint_p", "NC-RULE-MODIFIER-CONSTRAINT-P", 1, 0, false);
        declareFunction("nc_rule_head_constraint_p", "NC-RULE-HEAD-CONSTRAINT-P", 1, 0, false);
        declareFunction("nc_rule_indexical_p", "NC-RULE-INDEXICAL-P", 1, 0, false);
        declareFunction("nc_rule_semantic_constraint_p", "NC-RULE-SEMANTIC-CONSTRAINT-P", 1, 0, false);
        declareFunction("nc_constraint_op", "NC-CONSTRAINT-OP", 1, 0, false);
        declareFunction("nc_constraint_subject", "NC-CONSTRAINT-SUBJECT", 1, 0, false);
        declareFunction("nc_constraint_object", "NC-CONSTRAINT-OBJECT", 1, 0, false);
        declareFunction("binding_base", "BINDING-BASE", 1, 0, false);
        declareFunction("binding_target", "BINDING-TARGET", 1, 0, false);
        declareFunction("nc_rule_bindings_completeP", "NC-RULE-BINDINGS-COMPLETE?", 1, 0, false);
        declareFunction("bindings_get_modifier_target", "BINDINGS-GET-MODIFIER-TARGET", 1, 0, false);
        declareFunction("bindings_get_head_target", "BINDINGS-GET-HEAD-TARGET", 1, 0, false);
        declareFunction("set_nc_phrase_constraints", "SET-NC-PHRASE-CONSTRAINTS", 3, 0, false);
        declareFunction("organize_rule_syntactic_constraints", "ORGANIZE-RULE-SYNTACTIC-CONSTRAINTS", 1, 0, false);
        declareFunction("organize_syntactic_constraints", "ORGANIZE-SYNTACTIC-CONSTRAINTS", 1, 0, false);
        declareFunction("nc_rule_match_template", "NC-RULE-MATCH-TEMPLATE", 3, 0, false);
        declareFunction("nc_rule_pat_match", "NC-RULE-PAT-MATCH", 3, 0, false);
        declareFunction("binding_satisfies_nc_rule_semantic_constraintsP", "BINDING-SATISFIES-NC-RULE-SEMANTIC-CONSTRAINTS?", 3, 0, false);
        declareFunction("target_satisfies_nc_rule_semantic_constraintP", "TARGET-SATISFIES-NC-RULE-SEMANTIC-CONSTRAINT?", 2, 0, false);
        declareFunction("nc_constraint_appliesP", "NC-CONSTRAINT-APPLIES?", 2, 0, false);
        return NIL;
    }

    public static SubLObject init_pph_noun_compound_file() {
        deflexical("*PPH-NC-RULE-LIST-CACHING-STATE*", NIL);
        defconstant("*NC-POS-CONSTRAINT-FN*", $$NCPOSConstraintFn);
        defconstant("*NC-POS-PRED-CONSTRAINT-FN*", $$NCPOSPredConstraintFn);
        defconstant("*NC-EQUALS-CONSTRAINT-FN*", $$NCEqualsConstraintFn);
        defconstant("*NC-GENLS-CONSTRAINT-FN*", $$NCGenlsConstraintFn);
        defconstant("*NC-ISA-CONSTRAINT-FN*", $$NCIsaConstraintFn);
        defconstant("*THE-NC-MODIFIER*", $$TheNCModifier);
        defconstant("*THE-NC-HEAD*", $$TheNCHead);
        defconstant("*NOUN-COMPOUND-RULE*", $$NounCompoundRule);
        defconstant("*NC-RULE-TEMPLATE*", $$ncRuleTemplate);
        defconstant("*NC-RULE-CONSTRAINT*", $$ncRuleConstraint);
        deflexical("*NC-MT*", $$GeneralLexiconMt);
        return NIL;
    }

    public static SubLObject setup_pph_noun_compound_file() {
        memoization_state.note_globally_cached_function(PPH_NC_RULE_LIST);
        pph_types.pph_register_method(GENERATE_NOUN_COMPOUND_PHRASE, $COLLECTION_DENOTING_NAT, $BEST);
        memoization_state.note_memoized_function(PPH_FIND_NC_RULE_FOR_NAUT);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_pph_noun_compound_file();
    }

    @Override
    public void initializeVariables() {
        init_pph_noun_compound_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_pph_noun_compound_file();
    }

    static {
    }

    static private final SubLString $str_alt8$_A_is_not_a__A = makeString("~A is not a ~A");

    static private final SubLString $str_alt13$_A_is_not_a_valid__sbhl_type_erro = makeString("~A is not a valid *sbhl-type-error-action* value");

    static private final SubLString $str_alt14$attempting_to_bind_direction_link = makeString("attempting to bind direction link variable, to NIL. macro body not executed.");

    static private final SubLList $list_alt15 = list(makeUninternedSymbol("LINK-NODE"), makeUninternedSymbol("MT"), makeUninternedSymbol("TV"));

    static private final SubLString $str_alt16$Node__a_does_not_pass_sbhl_type_t = makeString("Node ~a does not pass sbhl-type-test ~a~%");

    public static final SubLSymbol $kw18$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLList $list_alt19 = list(list(makeSymbol("RULE-VAR"), makeSymbol("TEMPLATE-VAR"), makeSymbol("CONSTRAINTS-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("DONE-VAR")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt22 = list(makeSymbol("PPH-NC-RULE-LIST"));

    static private final SubLList $list_alt40 = list(makeSymbol("RULE"), makeSymbol("TEMPLATE"), makeSymbol("CONSTRAINTS"));

    static private final SubLList $list_alt41 = list(makeSymbol("HEAD-POS-CONSTRAINT"), makeSymbol("MOD-POS-CONSTRAINT"), makeSymbol("HEAD-POS-PRED-CONSTRAINT"), makeSymbol("MOD-POS-PRED-CONSTRAINT"));

    static private final SubLString $str_alt43$_PPH_error_level_ = makeString("(PPH error level ");

    static private final SubLString $str_alt44$__ = makeString(") ");

    static private final SubLString $str_alt45$___S_isn_t_a_semantic_constraint_ = makeString("~&~S isn't a semantic constraint.~%");

    static private final SubLString $str_alt48$___S_isn_t_a_recognized_noun_comp = makeString("~&~S isn't a recognized noun-compound constraint function.~%");
}

/**
 * Total time: 1090 ms
 */
