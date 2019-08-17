/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.inference.harness;


import static com.cyc.cycjava.cycl.bindings.add_variable_binding;
import static com.cyc.cycjava.cycl.bindings.apply_bindings;
import static com.cyc.cycjava.cycl.bindings.apply_bindings_backwards;
import static com.cyc.cycjava.cycl.bindings.apply_bindings_to_values;
import static com.cyc.cycjava.cycl.bindings.bindings_to_closed;
import static com.cyc.cycjava.cycl.bindings.inference_simplify_unification_bindings;
import static com.cyc.cycjava.cycl.bindings.make_variable_binding;
import static com.cyc.cycjava.cycl.bindings.possibly_optimize_bindings_wrt_equivalence;
import static com.cyc.cycjava.cycl.bindings.transfer_variable_map_to_bindings_backwards;
import static com.cyc.cycjava.cycl.bindings.transfer_variable_map_to_bindings_filtered;
import static com.cyc.cycjava.cycl.bindings.variable_binding_value;
import static com.cyc.cycjava.cycl.bindings.variable_binding_variable;
import static com.cyc.cycjava.cycl.constant_handles.valid_constantP;
import static com.cyc.cycjava.cycl.control_vars.$evaluatable_backchain_enabled$;
import static com.cyc.cycjava.cycl.control_vars.$hl_failure_backchaining$;
import static com.cyc.cycjava.cycl.control_vars.$unbound_rule_backchain_enabled$;
import static com.cyc.cycjava.cycl.cycl_variables.make_el_var;
import static com.cyc.cycjava.cycl.el_utilities.copy_clause;
import static com.cyc.cycjava.cycl.el_utilities.designated_mt;
import static com.cyc.cycjava.cycl.el_utilities.designated_sentence;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_with_operator_p;
import static com.cyc.cycjava.cycl.el_utilities.el_meets_pragmatic_requirement_p;
import static com.cyc.cycjava.cycl.el_utilities.ist_sentence_p;
import static com.cyc.cycjava.cycl.el_utilities.literal_arg1;
import static com.cyc.cycjava.cycl.forts.fort_p;
import static com.cyc.cycjava.cycl.id_index.do_id_index_empty_p;
import static com.cyc.cycjava.cycl.id_index.do_id_index_id_and_object_validP;
import static com.cyc.cycjava.cycl.id_index.do_id_index_next_id;
import static com.cyc.cycjava.cycl.id_index.do_id_index_next_state;
import static com.cyc.cycjava.cycl.id_index.do_id_index_state_object;
import static com.cyc.cycjava.cycl.id_index.id_index_dense_objects;
import static com.cyc.cycjava.cycl.id_index.id_index_dense_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_skip_tombstones_p;
import static com.cyc.cycjava.cycl.id_index.id_index_sparse_objects;
import static com.cyc.cycjava.cycl.id_index.id_index_sparse_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_tombstone_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_activeP;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_asent_cost;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_direction_relevantP;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_exclusive_func;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_expand_func;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_predicate_relevant_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_required_func;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_required_mt_relevantP;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_required_pattern_matched_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_rule_filter_func;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_rule_select_func;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.hl_module_sense_relevant_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.inference_meta_transformation_module;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.meta_transformation_module_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_modules.transformation_modules;
import static com.cyc.cycjava.cycl.iteration.iteration_next_without_values_macro_helper;
import static com.cyc.cycjava.cycl.iteration.iteration_state_peek;
import static com.cyc.cycjava.cycl.iteration.iterator_p;
import static com.cyc.cycjava.cycl.kb_accessors.backchain_forbidden_unless_arg_chosen_argnums;
import static com.cyc.cycjava.cycl.kb_accessors.some_backchain_forbidden_unless_arg_chosen_assertion_somewhereP;
import static com.cyc.cycjava.cycl.memoization_state.$memoization_state$;
import static com.cyc.cycjava.cycl.memoization_state.$memoized_item_not_found$;
import static com.cyc.cycjava.cycl.memoization_state.caching_results;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_enter_multi_key_n;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_lookup;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_put;
import static com.cyc.cycjava.cycl.memoization_state.create_caching_state;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_lock;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_lookup;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_put;
import static com.cyc.cycjava.cycl.memoization_state.note_memoized_function;
import static com.cyc.cycjava.cycl.memoization_state.sxhash_calc_2;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryKey;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryValue;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorNextEntry;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.releaseEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.plusp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete_if;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.intersection;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;
import static com.cyc.tool.subl.util.SubLFiles.defvar;

import java.util.Iterator;
import java.util.Map;

import org.armedbear.lisp.Lisp;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.arguments;
import com.cyc.cycjava.cycl.assertion_handles;
import com.cyc.cycjava.cycl.assertion_utilities;
import com.cyc.cycjava.cycl.assertions_high;
import com.cyc.cycjava.cycl.backward;
import com.cyc.cycjava.cycl.backward_utilities;
import com.cyc.cycjava.cycl.bindings;
import com.cyc.cycjava.cycl.clause_utilities;
import com.cyc.cycjava.cycl.clauses;
import com.cyc.cycjava.cycl.cycl_grammar;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.cycl_variables;
import com.cyc.cycjava.cycl.czer_utilities;
import com.cyc.cycjava.cycl.forts;
import com.cyc.cycjava.cycl.gt_methods;
import com.cyc.cycjava.cycl.iteration;
import com.cyc.cycjava.cycl.kb_accessors;
import com.cyc.cycjava.cycl.kb_indexing;
import com.cyc.cycjava.cycl.kb_mapping_macros;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.memoization_state;
import com.cyc.cycjava.cycl.mt_relevance_macros;
import com.cyc.cycjava.cycl.set;
import com.cyc.cycjava.cycl.set_contents;
import com.cyc.cycjava.cycl.somewhere_cache;
import com.cyc.cycjava.cycl.subl_promotions;
import com.cyc.cycjava.cycl.unification;
import com.cyc.cycjava.cycl.variables;
import com.cyc.cycjava.cycl.inference.inference_completeness_utilities;
import com.cyc.cycjava.cycl.inference.inference_trampolines;
import com.cyc.cycjava.cycl.inference.leviathan;
import com.cyc.cycjava.cycl.inference.modules.transformation_modules;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
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
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      INFERENCE-WORKER-TRANSFORMATION
 * source file: /cyc/top/cycl/inference/harness/inference-worker-transformation.lisp
 * created:     2019/07/03 17:37:40
 */
public final class inference_worker_transformation extends SubLTranslatedFile implements V12 {
    public static final SubLObject memoized_inference_excepted_assertionP_internal(SubLObject assertion, SubLObject mt) {
        return assertion_utilities.excepted_assertion_in_mtP(assertion, mt_relevance_macros.conservative_constraint_mt(mt));
    }

    public static final SubLObject memoized_inference_excepted_assertionP(SubLObject assertion, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.memoized_inference_excepted_assertionP_internal(assertion, mt);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, $sym50$MEMOIZED_INFERENCE_EXCEPTED_ASSERTION_, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), $sym50$MEMOIZED_INFERENCE_EXCEPTED_ASSERTION_, TWO_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, $sym50$MEMOIZED_INFERENCE_EXCEPTED_ASSERTION_, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_2(assertion, mt);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw51$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (assertion.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                                            return memoization_state.caching_results(results2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.memoized_inference_excepted_assertionP_internal(assertion, mt)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(assertion, mt));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static final SubLObject inference_excepted_assertionP(SubLObject assertion) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.memoized_inference_excepted_assertionP(assertion, mt_relevance_macros.current_mt_relevance_mt());
    }

    /**
     * Temporary control variable; whether or not #$pragmaticRequirement is enabled for forward inference.
     */
    // deflexical
    @LispMethod(comment = "Temporary control variable; whether or not #$pragmaticRequirement is enabled for forward inference.\ndeflexical")
    private static final SubLSymbol $forward_pragmatic_requirement_enabledP$ = makeSymbol("*FORWARD-PRAGMATIC-REQUIREMENT-ENABLED?*");

    public static final SubLFile me = new inference_worker_transformation();

 public static final String myName = "com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation";


    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_transformation_link_data$ = makeSymbol("*DTP-TRANSFORMATION-LINK-DATA*");

    // defparameter
    /**
     * The number of expected transformation tactic results at which we generate
     * them iteratively.
     */
    @LispMethod(comment = "The number of expected transformation tactic results at which we generate\r\nthem iteratively.\ndefparameter\nThe number of expected transformation tactic results at which we generate\nthem iteratively.")
    private static final SubLSymbol $transformation_tactic_iteration_threshold$ = makeSymbol("*TRANSFORMATION-TACTIC-ITERATION-THRESHOLD*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $stitch_up_bindings_loopP$ = makeSymbol("*STITCH-UP-BINDINGS-LOOP?*");

    // defparameter
    /**
     * Whether we allow the possibility of adding type constraints during
     * transformation.
     */
    @LispMethod(comment = "Whether we allow the possibility of adding type constraints during\r\ntransformation.\ndefparameter\nWhether we allow the possibility of adding type constraints during\ntransformation.")
    public static final SubLSymbol $inference_transformation_type_checking_enabledP$ = makeSymbol("*INFERENCE-TRANSFORMATION-TYPE-CHECKING-ENABLED?*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $merge_dnf_lambda_var$ = makeSymbol("*MERGE-DNF-LAMBDA-VAR*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $rule_dnf_lambda_var$ = makeSymbol("*RULE-DNF-LAMBDA-VAR*");

    // defvar
    /**
     * Temporary control variable; when non-nil #$genlRules is used to filter the
     * use of overly specific rules in transformation when a more general rule is
     * also applicable. Eventually should stay T.
     */
    @LispMethod(comment = "Temporary control variable; when non-nil #$genlRules is used to filter the\r\nuse of overly specific rules in transformation when a more general rule is\r\nalso applicable. Eventually should stay T.\ndefvar\nTemporary control variable; when non-nil #$genlRules is used to filter the\nuse of overly specific rules in transformation when a more general rule is\nalso applicable. Eventually should stay T.")
    private static final SubLSymbol $genl_rules_enabledP$ = makeSymbol("*GENL-RULES-ENABLED?*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol TRANSFORMATION_LINK_DATA = makeSymbol("TRANSFORMATION-LINK-DATA");

    private static final SubLSymbol TRANSFORMATION_LINK_DATA_P = makeSymbol("TRANSFORMATION-LINK-DATA-P");

    static private final SubLList $list2 = list(makeSymbol("HL-MODULE"), makeSymbol("BINDINGS"), makeSymbol("SUPPORTS"), makeSymbol("NON-EXPLANATORY-SUBQUERY"));

    static private final SubLList $list3 = list(makeKeyword("HL-MODULE"), makeKeyword("BINDINGS"), makeKeyword("SUPPORTS"), makeKeyword("NON-EXPLANATORY-SUBQUERY"));

    static private final SubLList $list4 = list(makeSymbol("TRANS-LINK-DATA-HL-MODULE"), makeSymbol("TRANS-LINK-DATA-BINDINGS"), makeSymbol("TRANS-LINK-DATA-SUPPORTS"), makeSymbol("TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY"));

    static private final SubLList $list5 = list(makeSymbol("_CSETF-TRANS-LINK-DATA-HL-MODULE"), makeSymbol("_CSETF-TRANS-LINK-DATA-BINDINGS"), makeSymbol("_CSETF-TRANS-LINK-DATA-SUPPORTS"), makeSymbol("_CSETF-TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY"));

    private static final SubLSymbol TRANSFORMATION_LINK_DATA_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("TRANSFORMATION-LINK-DATA-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list8 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("TRANSFORMATION-LINK-DATA-P"));

    private static final SubLSymbol TRANS_LINK_DATA_HL_MODULE = makeSymbol("TRANS-LINK-DATA-HL-MODULE");

    private static final SubLSymbol _CSETF_TRANS_LINK_DATA_HL_MODULE = makeSymbol("_CSETF-TRANS-LINK-DATA-HL-MODULE");

    private static final SubLSymbol TRANS_LINK_DATA_BINDINGS = makeSymbol("TRANS-LINK-DATA-BINDINGS");

    private static final SubLSymbol _CSETF_TRANS_LINK_DATA_BINDINGS = makeSymbol("_CSETF-TRANS-LINK-DATA-BINDINGS");

    private static final SubLSymbol TRANS_LINK_DATA_SUPPORTS = makeSymbol("TRANS-LINK-DATA-SUPPORTS");

    private static final SubLSymbol _CSETF_TRANS_LINK_DATA_SUPPORTS = makeSymbol("_CSETF-TRANS-LINK-DATA-SUPPORTS");

    private static final SubLSymbol TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY = makeSymbol("TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY");

    private static final SubLSymbol _CSETF_TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY = makeSymbol("_CSETF-TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY");

    private static final SubLSymbol $NON_EXPLANATORY_SUBQUERY = makeKeyword("NON-EXPLANATORY-SUBQUERY");

    private static final SubLString $str21$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_TRANSFORMATION_LINK_DATA = makeSymbol("MAKE-TRANSFORMATION-LINK-DATA");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_TRANSFORMATION_LINK_DATA_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-TRANSFORMATION-LINK-DATA-METHOD");

    private static final SubLSymbol TRANSFORMATION_LINK_P = makeSymbol("TRANSFORMATION-LINK-P");

    private static final SubLSymbol BINDING_LIST_P = makeSymbol("BINDING-LIST-P");

    private static final SubLSymbol NON_EXPLANATORY_SUBQUERY_SPEC_P = makeSymbol("NON-EXPLANATORY-SUBQUERY-SPEC-P");

    private static final SubLString $str36$No_tactic_found_for__S = makeString("No tactic found for ~S");

    static private final SubLList $list37 = cons(makeSymbol("VARIABLE"), makeSymbol("VALUE"));

    private static final SubLList $list40 = list(makeSymbol("STORE"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol $sym41$STORE_VAR = makeUninternedSymbol("STORE-VAR");

    private static final SubLList $list43 = list(makeSymbol("*HL-FAILURE-BACKCHAINING*"), T);

    private static final SubLList $list44 = list(makeSymbol("*UNBOUND-RULE-BACKCHAIN-ENABLED*"), T);

    private static final SubLList $list45 = list(makeSymbol("*EVALUATABLE-BACKCHAIN-ENABLED*"), T);

    private static final SubLSymbol $negation_by_failure$ = makeSymbol("*NEGATION-BY-FAILURE*");

    private static final SubLSymbol $sym47$PROBLEM_STORE_NEGATION_BY_FAILURE_ = makeSymbol("PROBLEM-STORE-NEGATION-BY-FAILURE?");

    private static final SubLSymbol $determine_new_transformation_tactics_module$ = makeSymbol("*DETERMINE-NEW-TRANSFORMATION-TACTICS-MODULE*");

    private static final SubLSymbol $DETERMINE_NEW_TRANSFORMATION_TACTICS = makeKeyword("DETERMINE-NEW-TRANSFORMATION-TACTICS");

    private static final SubLSymbol TRANSFORMATION_GENERATOR_TACTIC_P = makeSymbol("TRANSFORMATION-GENERATOR-TACTIC-P");

    private static final SubLSymbol TRANSFORMATION_PROOF_P = makeSymbol("TRANSFORMATION-PROOF-P");

    private static final SubLString $str54$generalized_transformation_link_o = makeString("generalized transformation link of unexpected type: ~s");

    private static final SubLString $str55$generalized_transformation_proof_ = makeString("generalized transformation proof of unexpected type: ~s");

    private static final SubLList $list56 = list(makeSymbol("HL-MODULE"), makeSymbol("PRODUCTIVITY"));

    private static final SubLSymbol $sym57$INFERENCE_EXCEPTED_ASSERTION_ = makeSymbol("INFERENCE-EXCEPTED-ASSERTION?");

    private static final SubLSymbol $sym58$_EXIT = makeSymbol("%EXIT");

    private static final SubLSymbol SINGLE_LITERAL_PROBLEM_P = makeSymbol("SINGLE-LITERAL-PROBLEM-P");

    private static final SubLString $str62$unexpected_tactic_specs_return_ty = makeString("unexpected tactic-specs return type ~a");

    private static final SubLString $str63$pruning__s__s__s = makeString("pruning ~s ~s ~s");

    private static final SubLList $list64 = list(list(makeSymbol("TACTIC"), makeSymbol("MT"), makeSymbol("SENSE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol $sym65$TACTIC_VAR = makeUninternedSymbol("TACTIC-VAR");

    private static final SubLSymbol $inference_expand_hl_module$ = makeSymbol("*INFERENCE-EXPAND-HL-MODULE*");

    private static final SubLSymbol TACTIC_HL_MODULE = makeSymbol("TACTIC-HL-MODULE");

    private static final SubLSymbol $inference_expand_sense$ = makeSymbol("*INFERENCE-EXPAND-SENSE*");

    private static final SubLSymbol WITH_PROBLEM_STORE_TRANSFORMATION_ASSUMPTIONS = makeSymbol("WITH-PROBLEM-STORE-TRANSFORMATION-ASSUMPTIONS");

    private static final SubLSymbol TACTIC_STORE = makeSymbol("TACTIC-STORE");

    private static final SubLString $str72$time_to_add_meta_transformation_s = makeString("time to add meta-transformation support for ~S");

    private static final SubLSymbol $TRANSFORMATION_RULE_SELECT = makeKeyword("TRANSFORMATION-RULE-SELECT");

    private static final SubLString $str74$transformation_tactic__S_already_ = makeString("transformation tactic ~S already has rule ~S");

    private static final SubLSymbol HANDLE_TRANSFORMATION_ADD_NODE_FOR_EXPAND_RESULTS = makeSymbol("HANDLE-TRANSFORMATION-ADD-NODE-FOR-EXPAND-RESULTS");

    private static final SubLSymbol $sym76$HL_VAR_ = makeSymbol("HL-VAR?");

    private static final SubLList $list79 = list(makeSymbol("MT"), makeSymbol("ASENT"));



    private static final SubLSymbol BINDINGS_P = makeSymbol("BINDINGS-P");

    private static final SubLString $str83$Could_not_ground_out__s_and__s = makeString("Could not ground out ~s and ~s");

    private static final SubLString $str84$Could_not_unify_transformation_bi = makeString("Could not unify transformation bindings ~a with subproof bindings ~a");

    private static final SubLSymbol $sym85$RULE_ASSERTION_ = makeSymbol("RULE-ASSERTION?");







    private static final SubLSymbol HL_VARIABLE_NOT_MENTIONED_IN_RULE_DNF_BUT_MENTIONED_IN_MERGE_DNF = makeSymbol("HL-VARIABLE-NOT-MENTIONED-IN-RULE-DNF-BUT-MENTIONED-IN-MERGE-DNF");

    private static final SubLSymbol PROBLEM_LINK_WITH_SINGLE_SUPPORTING_PROBLEM_P = makeSymbol("PROBLEM-LINK-WITH-SINGLE-SUPPORTING-PROBLEM-P");

    private static final SubLSymbol $sym93$TRANSFORMATION_PROOF_ABNORMAL_ = makeSymbol("TRANSFORMATION-PROOF-ABNORMAL?");

    private static final SubLSymbol INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED = makeSymbol("INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS-MEMOIZED");

    private static final SubLSymbol $sym96$_ = makeSymbol("<");

    private static final SubLSymbol VARIABLE_ID = makeSymbol("VARIABLE-ID");



    public static final SubLObject transformation_link_data_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject transformation_link_data_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject transformation_link_data_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$transformation_link_data_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject transformation_link_data_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$transformation_link_data_native.class ? T : NIL;
    }

    public static final SubLObject trans_link_data_hl_module_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.getField2();
    }

    public static SubLObject trans_link_data_hl_module(final SubLObject v_object) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject trans_link_data_bindings_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.getField3();
    }

    public static SubLObject trans_link_data_bindings(final SubLObject v_object) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject trans_link_data_supports_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.getField4();
    }

    public static SubLObject trans_link_data_supports(final SubLObject v_object) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject trans_link_data_non_explanatory_subquery_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.getField5();
    }

    public static SubLObject trans_link_data_non_explanatory_subquery(final SubLObject v_object) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject _csetf_trans_link_data_hl_module_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_trans_link_data_hl_module(final SubLObject v_object, final SubLObject value) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_trans_link_data_bindings_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_trans_link_data_bindings(final SubLObject v_object, final SubLObject value) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_trans_link_data_supports_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_trans_link_data_supports(final SubLObject v_object, final SubLObject value) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_trans_link_data_non_explanatory_subquery_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, TRANSFORMATION_LINK_DATA_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_trans_link_data_non_explanatory_subquery(final SubLObject v_object, final SubLObject value) {
        assert NIL != transformation_link_data_p(v_object) : "! inference_worker_transformation.transformation_link_data_p(v_object) " + "inference_worker_transformation.transformation_link_data_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject make_transformation_link_data_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$transformation_link_data_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($HL_MODULE)) {
                        com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_hl_module(v_new, current_value);
                    } else {
                        if (pcase_var.eql($BINDINGS)) {
                            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_bindings(v_new, current_value);
                        } else {
                            if (pcase_var.eql($SUPPORTS)) {
                                com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_supports(v_new, current_value);
                            } else {
                                if (pcase_var.eql($NON_EXPLANATORY_SUBQUERY)) {
                                    com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_non_explanatory_subquery(v_new, current_value);
                                } else {
                                    Errors.error($str_alt20$Invalid_slot__S_for_construction_, current_arg);
                                }
                            }
                        }
                    }
                }
            }
            return v_new;
        }
    }

    public static SubLObject make_transformation_link_data(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$transformation_link_data_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($HL_MODULE)) {
                _csetf_trans_link_data_hl_module(v_new, current_value);
            } else
                if (pcase_var.eql($BINDINGS)) {
                    _csetf_trans_link_data_bindings(v_new, current_value);
                } else
                    if (pcase_var.eql($SUPPORTS)) {
                        _csetf_trans_link_data_supports(v_new, current_value);
                    } else
                        if (pcase_var.eql($NON_EXPLANATORY_SUBQUERY)) {
                            _csetf_trans_link_data_non_explanatory_subquery(v_new, current_value);
                        } else {
                            Errors.error($str21$Invalid_slot__S_for_construction_, current_arg);
                        }



        }
        return v_new;
    }

    public static SubLObject visit_defstruct_transformation_link_data(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_TRANSFORMATION_LINK_DATA, FOUR_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $HL_MODULE, trans_link_data_hl_module(obj));
        funcall(visitor_fn, obj, $SLOT, $BINDINGS, trans_link_data_bindings(obj));
        funcall(visitor_fn, obj, $SLOT, $SUPPORTS, trans_link_data_supports(obj));
        funcall(visitor_fn, obj, $SLOT, $NON_EXPLANATORY_SUBQUERY, trans_link_data_non_explanatory_subquery(obj));
        funcall(visitor_fn, obj, $END, MAKE_TRANSFORMATION_LINK_DATA, FOUR_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_transformation_link_data_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_transformation_link_data(obj, visitor_fn);
    }

    /**
     *
     *
     * @return transformation-link-p
     */
    @LispMethod(comment = "@return transformation-link-p")
    public static final SubLObject new_transformation_link_alt(SubLObject supported_problem, SubLObject supporting_mapped_problem, SubLObject hl_module, SubLObject transformation_bindings, SubLObject rule_assertion, SubLObject more_supports, SubLObject non_explanatory_subquery) {
        SubLTrampolineFile.checkType(supported_problem, PROBLEM_P);
        if (NIL != supporting_mapped_problem) {
            SubLTrampolineFile.checkType(supporting_mapped_problem, MAPPED_PROBLEM_P);
        }
        {
            SubLObject supports = cons(rule_assertion, more_supports);
            SubLObject transformation_link = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_link_int(supported_problem, hl_module, transformation_bindings, supports, non_explanatory_subquery);
            if (NIL != supporting_mapped_problem) {
                inference_datastructures_problem_link.connect_supporting_mapped_problem_with_dependent_link(supporting_mapped_problem, transformation_link);
            }
            inference_datastructures_problem_link.problem_link_open_all(transformation_link);
            inference_worker.propagate_problem_link(transformation_link);
            return transformation_link;
        }
    }

    /**
     *
     *
     * @return transformation-link-p
     */
    @LispMethod(comment = "@return transformation-link-p")
    public static SubLObject new_transformation_link(final SubLObject supported_problem, final SubLObject supporting_mapped_problem, final SubLObject hl_module, final SubLObject transformation_bindings, final SubLObject rule_assertion, final SubLObject more_supports, final SubLObject non_explanatory_subquery) {
        assert NIL != inference_datastructures_problem.problem_p(supported_problem) : "! inference_datastructures_problem.problem_p(supported_problem) " + ("inference_datastructures_problem.problem_p(supported_problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(supported_problem) ") + supported_problem;
        if (((NIL != supporting_mapped_problem) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == inference_datastructures_problem_link.mapped_problem_p(supporting_mapped_problem))) {
            throw new AssertionError(supporting_mapped_problem);
        }
        final SubLObject supports = cons(rule_assertion, more_supports);
        final SubLObject transformation_link = new_transformation_link_int(supported_problem, hl_module, transformation_bindings, supports, non_explanatory_subquery);
        if (NIL != supporting_mapped_problem) {
            inference_datastructures_problem_link.connect_supporting_mapped_problem_with_dependent_link(supporting_mapped_problem, transformation_link);
        }
        inference_datastructures_problem_link.problem_link_open_all(transformation_link);
        inference_worker.propagate_problem_link(transformation_link);
        return transformation_link;
    }

    /**
     * Returns a new :transformation link
     * with its data properties set to HL-MODULE, BINDINGS, and SUPPORTS,
     * with a supported problem of PROBLEM, and no supporting problems yet.
     */
    @LispMethod(comment = "Returns a new :transformation link\r\nwith its data properties set to HL-MODULE, BINDINGS, and SUPPORTS,\r\nwith a supported problem of PROBLEM, and no supporting problems yet.\nReturns a new :transformation link\nwith its data properties set to HL-MODULE, BINDINGS, and SUPPORTS,\nwith a supported problem of PROBLEM, and no supporting problems yet.")
    public static final SubLObject new_transformation_link_int_alt(SubLObject problem, SubLObject hl_module, SubLObject transformation_bindings, SubLObject supports, SubLObject non_explanatory_subquery) {
        {
            SubLObject transformation_link = inference_datastructures_problem_link.new_problem_link($TRANSFORMATION, problem);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_link_data(transformation_link);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.set_transformation_link_hl_module(transformation_link, hl_module);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.set_transformation_link_bindings(transformation_link, transformation_bindings);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.set_transformation_link_supports(transformation_link, supports);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.set_transformation_link_non_explanatory_subquery(transformation_link, non_explanatory_subquery);
            if (NIL != non_explanatory_subquery) {
                inference_datastructures_problem_store.problem_store_note_non_explanatory_subproofs_possible(inference_datastructures_problem.problem_store(problem));
            }
            return transformation_link;
        }
    }

    /**
     * Returns a new :transformation link
     * with its data properties set to HL-MODULE, BINDINGS, and SUPPORTS,
     * with a supported problem of PROBLEM, and no supporting problems yet.
     */
    @LispMethod(comment = "Returns a new :transformation link\r\nwith its data properties set to HL-MODULE, BINDINGS, and SUPPORTS,\r\nwith a supported problem of PROBLEM, and no supporting problems yet.\nReturns a new :transformation link\nwith its data properties set to HL-MODULE, BINDINGS, and SUPPORTS,\nwith a supported problem of PROBLEM, and no supporting problems yet.")
    public static SubLObject new_transformation_link_int(final SubLObject problem, final SubLObject hl_module, final SubLObject transformation_bindings, final SubLObject supports, final SubLObject non_explanatory_subquery) {
        final SubLObject transformation_link = inference_datastructures_problem_link.new_problem_link($TRANSFORMATION, problem);
        new_transformation_link_data(transformation_link);
        set_transformation_link_hl_module(transformation_link, hl_module);
        set_transformation_link_bindings(transformation_link, transformation_bindings);
        set_transformation_link_supports(transformation_link, supports);
        set_transformation_link_non_explanatory_subquery(transformation_link, non_explanatory_subquery);
        if (NIL != non_explanatory_subquery) {
            inference_datastructures_problem_store.problem_store_note_non_explanatory_subproofs_possible(inference_datastructures_problem.problem_store(problem));
        }
        return transformation_link;
    }

    public static final SubLObject new_transformation_link_data_alt(SubLObject transformation_link) {
        {
            SubLObject data = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.make_transformation_link_data(UNPROVIDED);
            inference_datastructures_problem_link.set_problem_link_data(transformation_link, data);
        }
        return transformation_link;
    }

    public static SubLObject new_transformation_link_data(final SubLObject transformation_link) {
        final SubLObject data = make_transformation_link_data(UNPROVIDED);
        inference_datastructures_problem_link.set_problem_link_data(transformation_link, data);
        return transformation_link;
    }

    public static final SubLObject destroy_transformation_link_alt(SubLObject transformation_link) {
        inference_worker_residual_transformation.destroy_transformation_link_wrt_residual_transformation_links(transformation_link);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_hl_module(data, $FREE);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_bindings(data, $FREE);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_supports(data, $FREE);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_non_explanatory_subquery(data, $FREE);
        }
        return transformation_link;
    }

    public static SubLObject destroy_transformation_link(final SubLObject transformation_link) {
        inference_worker_residual_transformation.destroy_transformation_link_wrt_residual_transformation_links(transformation_link);
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        _csetf_trans_link_data_hl_module(data, $FREE);
        _csetf_trans_link_data_bindings(data, $FREE);
        _csetf_trans_link_data_supports(data, $FREE);
        _csetf_trans_link_data_non_explanatory_subquery(data, $FREE);
        return transformation_link;
    }

    public static final SubLObject transformation_link_hl_module_alt(SubLObject transformation_link) {
        SubLTrampolineFile.checkType(transformation_link, TRANSFORMATION_LINK_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.trans_link_data_hl_module(data);
        }
    }

    public static SubLObject transformation_link_hl_module(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        return trans_link_data_hl_module(data);
    }

    /**
     * The first elements of these bindings are in the space of TRANSFORMATION-LINK's
     * supported problem, and their second elements are in the space of
     * TRANSFORMATION-LINK's unique supporting problem.
     */
    @LispMethod(comment = "The first elements of these bindings are in the space of TRANSFORMATION-LINK\'s\r\nsupported problem, and their second elements are in the space of\r\nTRANSFORMATION-LINK\'s unique supporting problem.\nThe first elements of these bindings are in the space of TRANSFORMATION-LINK\'s\nsupported problem, and their second elements are in the space of\nTRANSFORMATION-LINK\'s unique supporting problem.")
    public static final SubLObject transformation_link_bindings_alt(SubLObject transformation_link) {
        SubLTrampolineFile.checkType(transformation_link, TRANSFORMATION_LINK_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.trans_link_data_bindings(data);
        }
    }

    /**
     * The first elements of these bindings are in the space of TRANSFORMATION-LINK's
     * supported problem, and their second elements are in the space of
     * TRANSFORMATION-LINK's unique supporting problem.
     */
    @LispMethod(comment = "The first elements of these bindings are in the space of TRANSFORMATION-LINK\'s\r\nsupported problem, and their second elements are in the space of\r\nTRANSFORMATION-LINK\'s unique supporting problem.\nThe first elements of these bindings are in the space of TRANSFORMATION-LINK\'s\nsupported problem, and their second elements are in the space of\nTRANSFORMATION-LINK\'s unique supporting problem.")
    public static SubLObject transformation_link_bindings(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        return trans_link_data_bindings(data);
    }

    public static final SubLObject transformation_link_supports_alt(SubLObject transformation_link) {
        SubLTrampolineFile.checkType(transformation_link, TRANSFORMATION_LINK_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.trans_link_data_supports(data);
        }
    }

    public static SubLObject transformation_link_supports(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        return trans_link_data_supports(data);
    }

    public static final SubLObject transformation_link_rule_assertion_alt(SubLObject transformation_link) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_supports(transformation_link).first();
    }

    public static SubLObject transformation_link_rule_assertion(final SubLObject transformation_link) {
        return transformation_link_supports(transformation_link).first();
    }

    public static final SubLObject transformation_link_more_supports_alt(SubLObject transformation_link) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_supports(transformation_link).rest();
    }

    public static SubLObject transformation_link_more_supports(final SubLObject transformation_link) {
        return transformation_link_supports(transformation_link).rest();
    }

    public static final SubLObject transformation_link_non_explanatory_subquery_alt(SubLObject transformation_link) {
        SubLTrampolineFile.checkType(transformation_link, TRANSFORMATION_LINK_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.trans_link_data_non_explanatory_subquery(data);
        }
    }

    public static SubLObject transformation_link_non_explanatory_subquery(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        return trans_link_data_non_explanatory_subquery(data);
    }

    public static final SubLObject set_transformation_link_hl_module_alt(SubLObject transformation_link, SubLObject hl_module) {
        SubLTrampolineFile.checkType(hl_module, HL_MODULE_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_hl_module(data, hl_module);
        }
        return transformation_link;
    }

    public static SubLObject set_transformation_link_hl_module(final SubLObject transformation_link, final SubLObject hl_module) {
        assert NIL != inference_modules.hl_module_p(hl_module) : "! inference_modules.hl_module_p(hl_module) " + ("inference_modules.hl_module_p(hl_module) " + "CommonSymbols.NIL != inference_modules.hl_module_p(hl_module) ") + hl_module;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        _csetf_trans_link_data_hl_module(data, hl_module);
        return transformation_link;
    }

    public static final SubLObject set_transformation_link_bindings_alt(SubLObject transformation_link, SubLObject v_bindings) {
        SubLTrampolineFile.checkType(v_bindings, BINDING_LIST_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_bindings(data, v_bindings);
        }
        return transformation_link;
    }

    public static SubLObject set_transformation_link_bindings(final SubLObject transformation_link, final SubLObject v_bindings) {
        assert NIL != bindings.binding_list_p(v_bindings) : "! bindings.binding_list_p(v_bindings) " + ("bindings.binding_list_p(v_bindings) " + "CommonSymbols.NIL != bindings.binding_list_p(v_bindings) ") + v_bindings;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        _csetf_trans_link_data_bindings(data, v_bindings);
        return transformation_link;
    }

    public static final SubLObject set_transformation_link_supports_alt(SubLObject transformation_link, SubLObject supports) {
        SubLTrampolineFile.checkType(supports, HL_JUSTIFICATION_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_supports(data, supports);
        }
        return transformation_link;
    }

    public static SubLObject set_transformation_link_supports(final SubLObject transformation_link, final SubLObject supports) {
        assert NIL != arguments.hl_justification_p(supports) : "! arguments.hl_justification_p(supports) " + ("arguments.hl_justification_p(supports) " + "CommonSymbols.NIL != arguments.hl_justification_p(supports) ") + supports;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        _csetf_trans_link_data_supports(data, supports);
        return transformation_link;
    }

    public static final SubLObject set_transformation_link_non_explanatory_subquery_alt(SubLObject transformation_link, SubLObject subquery) {
        SubLTrampolineFile.checkType(subquery, NON_EXPLANATORY_SUBQUERY_SPEC_P);
        {
            SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation._csetf_trans_link_data_non_explanatory_subquery(data, subquery);
        }
        return transformation_link;
    }

    public static SubLObject set_transformation_link_non_explanatory_subquery(final SubLObject transformation_link, final SubLObject subquery) {
        assert NIL != inference_datastructures_problem_query.non_explanatory_subquery_spec_p(subquery) : "! inference_datastructures_problem_query.non_explanatory_subquery_spec_p(subquery) " + ("inference_datastructures_problem_query.non_explanatory_subquery_spec_p(subquery) " + "CommonSymbols.NIL != inference_datastructures_problem_query.non_explanatory_subquery_spec_p(subquery) ") + subquery;
        final SubLObject data = inference_datastructures_problem_link.problem_link_data(transformation_link);
        _csetf_trans_link_data_non_explanatory_subquery(data, subquery);
        return transformation_link;
    }

    public static final SubLObject transformation_link_tactic_alt(SubLObject transformation_link) {
        SubLTrampolineFile.checkType(transformation_link, TRANSFORMATION_LINK_P);
        {
            SubLObject problem = inference_datastructures_problem_link.problem_link_supported_problem(transformation_link);
            SubLObject hl_module = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_hl_module(transformation_link);
            SubLObject rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_rule_assertion(transformation_link);
            SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
            SubLObject candidate_tactic = NIL;
            for (candidate_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , candidate_tactic = cdolist_list_var.first()) {
                if (NIL != inference_datastructures_problem.do_problem_tactics_type_match(candidate_tactic, $TRANSFORMATION)) {
                    if ((hl_module == inference_datastructures_tactic.tactic_hl_module(candidate_tactic)) && (rule == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_rule(candidate_tactic))) {
                        return candidate_tactic;
                    }
                }
            }
            if (NIL == inference_datastructures_problem.tactically_unexamined_problem_p(problem)) {
                return Errors.error($str_alt30$No_tactic_found_for__S, transformation_link);
            }
        }
        return NIL;
    }

    public static SubLObject transformation_link_tactic(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        final SubLObject problem = inference_datastructures_problem_link.problem_link_supported_problem(transformation_link);
        final SubLObject hl_module = transformation_link_hl_module(transformation_link);
        final SubLObject rule = transformation_link_rule_assertion(transformation_link);
        SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
        SubLObject candidate_tactic = NIL;
        candidate_tactic = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (((NIL != inference_datastructures_problem.do_problem_tactics_type_match(candidate_tactic, $TRANSFORMATION)) && hl_module.eql(inference_datastructures_tactic.tactic_hl_module(candidate_tactic))) && rule.eql(transformation_tactic_rule(candidate_tactic))) {
                return candidate_tactic;
            }
            cdolist_list_var = cdolist_list_var.rest();
            candidate_tactic = cdolist_list_var.first();
        } 
        if (NIL == inference_datastructures_problem.tactically_unexamined_problem_p(problem)) {
            return Errors.error($str36$No_tactic_found_for__S, transformation_link);
        }
        return NIL;
    }

    /**
     *
     *
     * @param TRANSFORMATION-LINK;
     * 		a transformation link in ths process of being constructed
     * 		which has its supports already set.
     * @return a contextualized DNF with variables in the problem space of the extended supported problem
     */
    @LispMethod(comment = "@param TRANSFORMATION-LINK;\r\n\t\ta transformation link in ths process of being constructed\r\n\t\twhich has its supports already set.\r\n@return a contextualized DNF with variables in the problem space of the extended supported problem")
    public static final SubLObject transformation_link_pragmatic_requirements_alt(SubLObject transformation_link) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject rule_assertion = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_rule_assertion(transformation_link);
                SubLObject transformation_mt = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_transformation_mt(transformation_link);
                SubLObject pragmatic_requirements_dnf = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(transformation_mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        pragmatic_requirements_dnf = unification.variable_base_inversion(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.backward_rule_pragmatic_dnf(rule_assertion, transformation_mt));
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return inference_czer.contextualize_clause(pragmatic_requirements_dnf, transformation_mt, UNPROVIDED);
            }
        }
    }

    /**
     *
     *
     * @param TRANSFORMATION-LINK;
     * 		a transformation link in ths process of being constructed
     * 		which has its supports already set.
     * @return a contextualized DNF with variables in the problem space of the extended supported problem
     */
    @LispMethod(comment = "@param TRANSFORMATION-LINK;\r\n\t\ta transformation link in ths process of being constructed\r\n\t\twhich has its supports already set.\r\n@return a contextualized DNF with variables in the problem space of the extended supported problem")
    public static SubLObject transformation_link_pragmatic_requirements(final SubLObject transformation_link) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject rule_assertion = transformation_link_rule_assertion(transformation_link);
        final SubLObject transformation_mt = transformation_link_transformation_mt(transformation_link);
        SubLObject pragmatic_requirements_dnf = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(transformation_mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            pragmatic_requirements_dnf = unification.variable_base_inversion(backward_rule_pragmatic_dnf(rule_assertion, transformation_mt));
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return inference_czer.contextualize_clause(pragmatic_requirements_dnf, transformation_mt, UNPROVIDED);
    }

    /**
     *
     *
     * @return nil or mapped-problem-p
     */
    @LispMethod(comment = "@return nil or mapped-problem-p")
    public static final SubLObject transformation_link_supporting_mapped_problem_alt(SubLObject transformation_link) {
        return inference_datastructures_problem_link.problem_link_first_supporting_mapped_problem(transformation_link);
    }

    /**
     *
     *
     * @return nil or mapped-problem-p
     */
    @LispMethod(comment = "@return nil or mapped-problem-p")
    public static SubLObject transformation_link_supporting_mapped_problem(final SubLObject transformation_link) {
        return inference_datastructures_problem_link.problem_link_first_supporting_mapped_problem(transformation_link);
    }

    /**
     *
     *
     * @return nil or problem-p
     */
    @LispMethod(comment = "@return nil or problem-p")
    public static final SubLObject transformation_link_supporting_problem_alt(SubLObject transformation_link) {
        {
            SubLObject supporting_mapped_problem = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_supporting_mapped_problem(transformation_link);
            if (NIL != supporting_mapped_problem) {
                return inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem);
            } else {
                return NIL;
            }
        }
    }

    /**
     *
     *
     * @return nil or problem-p
     */
    @LispMethod(comment = "@return nil or problem-p")
    public static SubLObject transformation_link_supporting_problem(final SubLObject transformation_link) {
        final SubLObject supporting_mapped_problem = transformation_link_supporting_mapped_problem(transformation_link);
        if (NIL != supporting_mapped_problem) {
            return inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem);
        }
        return NIL;
    }

    /**
     *
     *
     * @return variable-map-p
     */
    @LispMethod(comment = "@return variable-map-p")
    public static final SubLObject transformation_link_supporting_variable_map_alt(SubLObject transformation_link) {
        {
            SubLObject supporting_mapped_problem = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_supporting_mapped_problem(transformation_link);
            if (NIL != supporting_mapped_problem) {
                return inference_datastructures_problem_link.mapped_problem_variable_map(supporting_mapped_problem);
            } else {
                return NIL;
            }
        }
    }

    /**
     *
     *
     * @return variable-map-p
     */
    @LispMethod(comment = "@return variable-map-p")
    public static SubLObject transformation_link_supporting_variable_map(final SubLObject transformation_link) {
        final SubLObject supporting_mapped_problem = transformation_link_supporting_mapped_problem(transformation_link);
        if (NIL != supporting_mapped_problem) {
            return inference_datastructures_problem_link.mapped_problem_variable_map(supporting_mapped_problem);
        }
        return NIL;
    }

    public static final SubLObject transformation_link_transformation_mt_alt(SubLObject transformation_link) {
        {
            SubLObject problem = inference_datastructures_problem_link.problem_link_supported_problem(transformation_link);
            return inference_datastructures_problem.single_literal_problem_mt(problem);
        }
    }

    public static SubLObject transformation_link_transformation_mt(final SubLObject transformation_link) {
        final SubLObject problem = inference_datastructures_problem_link.problem_link_supported_problem(transformation_link);
        return inference_datastructures_problem.single_literal_problem_mt(problem);
    }

    public static final SubLObject transformation_link_supporting_problem_wholly_explanatoryP_alt(SubLObject transformation_link) {
        return sublisp_null(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_non_explanatory_subquery(transformation_link));
    }

    public static SubLObject transformation_link_supporting_problem_wholly_explanatoryP(final SubLObject transformation_link) {
        return sublisp_null(transformation_link_non_explanatory_subquery(transformation_link));
    }

    /**
     *
     *
     * @return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was
    transformed via a transformation link using RULE.
     */
    @LispMethod(comment = "@return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was\r\ntransformed via a transformation link using RULE.")
    public static final SubLObject transformed_problem_using_rule_alt(SubLObject supported_problem, SubLObject rule) {
        {
            SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(supported_problem);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject transformation_link = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) {
                        if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION)) {
                            {
                                SubLObject transformation_link_rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_rule_assertion(transformation_link);
                                if (rule == transformation_link_rule) {
                                    return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_supporting_problem(transformation_link);
                                }
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was
    transformed via a transformation link using RULE.
     */
    @LispMethod(comment = "@return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was\r\ntransformed via a transformation link using RULE.")
    public static SubLObject transformed_problem_using_rule(final SubLObject supported_problem, final SubLObject rule) {
        final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(supported_problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject transformation_link;
        SubLObject transformation_link_rule;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            transformation_link = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION))) {
                transformation_link_rule = transformation_link_rule_assertion(transformation_link);
                if (rule.eql(transformation_link_rule)) {
                    return transformation_link_supporting_problem(transformation_link);
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was
    transformed via a transformation link using RULE and HL-MODULE.
     */
    @LispMethod(comment = "@return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was\r\ntransformed via a transformation link using RULE and HL-MODULE.")
    public static final SubLObject transformed_problem_using_rule_and_hl_module_alt(SubLObject supported_problem, SubLObject rule, SubLObject hl_module) {
        {
            SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(supported_problem);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject transformation_link = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) {
                        if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION)) {
                            {
                                SubLObject transformation_link_rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_rule_assertion(transformation_link);
                                if (rule == transformation_link_rule) {
                                    {
                                        SubLObject transformation_link_hl_module = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_hl_module(transformation_link);
                                        if (hl_module == transformation_link_hl_module) {
                                            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_supporting_problem(transformation_link);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was
    transformed via a transformation link using RULE and HL-MODULE.
     */
    @LispMethod(comment = "@return nil or problem-p; the supporting transformed problem of SUPPORTED-PROBLEM which was\r\ntransformed via a transformation link using RULE and HL-MODULE.")
    public static SubLObject transformed_problem_using_rule_and_hl_module(final SubLObject supported_problem, final SubLObject rule, final SubLObject hl_module) {
        final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(supported_problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject transformation_link;
        SubLObject transformation_link_rule;
        SubLObject transformation_link_hl_module;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            transformation_link = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION))) {
                transformation_link_rule = transformation_link_rule_assertion(transformation_link);
                if (rule.eql(transformation_link_rule)) {
                    transformation_link_hl_module = transformation_link_hl_module(transformation_link);
                    if (hl_module.eql(transformation_link_hl_module)) {
                        return transformation_link_supporting_problem(transformation_link);
                    }
                }
            }
        }
        return NIL;
    }

    public static final SubLObject transformation_link_rule_bindings_to_closed_alt(SubLObject transformation_link) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_rule_bindings_to_closed(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_bindings(transformation_link));
    }

    public static SubLObject transformation_link_rule_bindings_to_closed(final SubLObject transformation_link) {
        return transformation_rule_bindings_to_closed(transformation_link_bindings(transformation_link));
    }

    /**
     * The subset of TRANSFORMATION-LINK's bindings that map variables in the rule to closed terms.
     */
    @LispMethod(comment = "The subset of TRANSFORMATION-LINK\'s bindings that map variables in the rule to closed terms.")
    public static final SubLObject transformation_rule_bindings_to_closed_alt(SubLObject transformation_bindings) {
        {
            SubLObject v_bindings = NIL;
            SubLObject cdolist_list_var = transformation_bindings;
            SubLObject binding = NIL;
            for (binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , binding = cdolist_list_var.first()) {
                {
                    SubLObject datum = binding;
                    SubLObject current = datum;
                    SubLObject variable = NIL;
                    SubLObject value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt31);
                    variable = current.first();
                    current = current.rest();
                    value = current;
                    if (NIL != unification.non_base_variable_p(variable)) {
                        if (NIL != variables.fully_bound_p(value)) {
                            v_bindings = cons(binding, v_bindings);
                        }
                    }
                }
            }
            return nreverse(v_bindings);
        }
    }

    /**
     * The subset of TRANSFORMATION-LINK's bindings that map variables in the rule to closed terms.
     */
    @LispMethod(comment = "The subset of TRANSFORMATION-LINK\'s bindings that map variables in the rule to closed terms.")
    public static SubLObject transformation_rule_bindings_to_closed(final SubLObject transformation_bindings) {
        SubLObject v_bindings = NIL;
        SubLObject cdolist_list_var = transformation_bindings;
        SubLObject binding = NIL;
        binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = binding;
            SubLObject variable = NIL;
            SubLObject value = NIL;
            destructuring_bind_must_consp(current, datum, $list37);
            variable = current.first();
            current = value = current.rest();
            if ((NIL != unification.non_base_variable_p(variable)) && (NIL != variables.fully_bound_p(value))) {
                v_bindings = cons(binding, v_bindings);
            }
            cdolist_list_var = cdolist_list_var.rest();
            binding = cdolist_list_var.first();
        } 
        return nreverse(v_bindings);
    }

    public static SubLObject transformation_link_el_bindings(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        final SubLObject rule_assertion = transformation_link_rule_assertion(transformation_link);
        final SubLObject rule_assertion_variable_map = rule_assertion_variable_map(rule_assertion);
        final SubLObject hl_bindings = transformation_link_bindings(transformation_link);
        final SubLObject el_bindings = bindings.apply_bindings_backwards(rule_assertion_variable_map, hl_bindings);
        final SubLObject canon_el_bindings = canonicalize_bindings_wrt_el_vars(el_bindings);
        return canon_el_bindings;
    }

    public static SubLObject canonicalize_bindings_wrt_el_vars(final SubLObject v_bindings) {
        SubLObject new_bindings = NIL;
        SubLObject cdolist_list_var = v_bindings;
        SubLObject binding = NIL;
        binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = binding;
            SubLObject variable = NIL;
            SubLObject value = NIL;
            destructuring_bind_must_consp(current, datum, $list37);
            variable = current.first();
            current = value = current.rest();
            if ((NIL != variables.variable_p(variable)) && (NIL != cycl_variables.el_varP(value))) {
                binding = bindings.make_variable_binding(value, variable);
            }
            new_bindings = cons(binding, new_bindings);
            cdolist_list_var = cdolist_list_var.rest();
            binding = cdolist_list_var.first();
        } 
        return nreverse(new_bindings);
    }

    public static SubLObject transformation_link_motivated_residual_transformation_links(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        SubLObject rt_links = NIL;
        final SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(transformation_link);
        final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(supported_problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject jo_link_var;
        SubLObject jo_link_var_$1;
        SubLObject motivating_conjunction_problem;
        SubLObject set_contents_var_$2;
        SubLObject basis_object_$3;
        SubLObject state_$4;
        SubLObject rt_link;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            jo_link_var = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, jo_link_var)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(jo_link_var, $JOIN_ORDERED))) {
                jo_link_var_$1 = jo_link_var;
                motivating_conjunction_problem = inference_datastructures_problem_link.problem_link_supported_problem(jo_link_var_$1);
                set_contents_var_$2 = inference_datastructures_problem.problem_argument_links(motivating_conjunction_problem);
                for (basis_object_$3 = set_contents.do_set_contents_basis_object(set_contents_var_$2), state_$4 = NIL, state_$4 = set_contents.do_set_contents_initial_state(basis_object_$3, set_contents_var_$2); NIL == set_contents.do_set_contents_doneP(basis_object_$3, state_$4); state_$4 = set_contents.do_set_contents_update_state(state_$4)) {
                    rt_link = set_contents.do_set_contents_next(basis_object_$3, state_$4);
                    if ((((NIL != set_contents.do_set_contents_element_validP(state_$4, rt_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(rt_link, $RESIDUAL_TRANSFORMATION))) && (NIL != inference_worker_residual_transformation.residual_transformation_link_motivated_by_join_ordered_linkP(rt_link, jo_link_var_$1))) && (NIL != inference_worker_residual_transformation.residual_transformation_link_motivated_by_transformation_linkP(rt_link, transformation_link))) {
                        rt_links = cons(rt_link, rt_links);
                    }
                }
            }
        }
        return nreverse(rt_links);
    }

    public static SubLObject transformation_link_motivated_residual_transformation_link_count(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        SubLObject total = ZERO_INTEGER;
        final SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(transformation_link);
        final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(supported_problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject jo_link_var;
        SubLObject jo_link_var_$5;
        SubLObject motivating_conjunction_problem;
        SubLObject set_contents_var_$6;
        SubLObject basis_object_$7;
        SubLObject state_$8;
        SubLObject rt_link;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            jo_link_var = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, jo_link_var)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(jo_link_var, $JOIN_ORDERED))) {
                jo_link_var_$5 = jo_link_var;
                motivating_conjunction_problem = inference_datastructures_problem_link.problem_link_supported_problem(jo_link_var_$5);
                set_contents_var_$6 = inference_datastructures_problem.problem_argument_links(motivating_conjunction_problem);
                for (basis_object_$7 = set_contents.do_set_contents_basis_object(set_contents_var_$6), state_$8 = NIL, state_$8 = set_contents.do_set_contents_initial_state(basis_object_$7, set_contents_var_$6); NIL == set_contents.do_set_contents_doneP(basis_object_$7, state_$8); state_$8 = set_contents.do_set_contents_update_state(state_$8)) {
                    rt_link = set_contents.do_set_contents_next(basis_object_$7, state_$8);
                    if ((((NIL != set_contents.do_set_contents_element_validP(state_$8, rt_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(rt_link, $RESIDUAL_TRANSFORMATION))) && (NIL != inference_worker_residual_transformation.residual_transformation_link_motivated_by_join_ordered_linkP(rt_link, jo_link_var_$5))) && (NIL != inference_worker_residual_transformation.residual_transformation_link_motivated_by_transformation_linkP(rt_link, transformation_link))) {
                        total = add(total, ONE_INTEGER);
                    }
                }
            }
        }
        return total;
    }

    public static SubLObject transformation_link_good_motivated_residual_transformation_link_count(final SubLObject transformation_link) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        SubLObject total = ZERO_INTEGER;
        final SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(transformation_link);
        final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(supported_problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject jo_link_var;
        SubLObject jo_link_var_$9;
        SubLObject motivating_conjunction_problem;
        SubLObject set_contents_var_$10;
        SubLObject basis_object_$11;
        SubLObject state_$12;
        SubLObject rt_link;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            jo_link_var = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, jo_link_var)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(jo_link_var, $JOIN_ORDERED))) {
                jo_link_var_$9 = jo_link_var;
                motivating_conjunction_problem = inference_datastructures_problem_link.problem_link_supported_problem(jo_link_var_$9);
                set_contents_var_$10 = inference_datastructures_problem.problem_argument_links(motivating_conjunction_problem);
                for (basis_object_$11 = set_contents.do_set_contents_basis_object(set_contents_var_$10), state_$12 = NIL, state_$12 = set_contents.do_set_contents_initial_state(basis_object_$11, set_contents_var_$10); NIL == set_contents.do_set_contents_doneP(basis_object_$11, state_$12); state_$12 = set_contents.do_set_contents_update_state(state_$12)) {
                    rt_link = set_contents.do_set_contents_next(basis_object_$11, state_$12);
                    if (((((NIL != set_contents.do_set_contents_element_validP(state_$12, rt_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(rt_link, $RESIDUAL_TRANSFORMATION))) && (NIL != inference_worker_residual_transformation.residual_transformation_link_motivated_by_join_ordered_linkP(rt_link, jo_link_var_$9))) && (NIL != inference_worker_residual_transformation.residual_transformation_link_motivated_by_transformation_linkP(rt_link, transformation_link))) && (NIL != inference_datastructures_problem_link.problem_link_goodP(rt_link))) {
                        total = add(total, ONE_INTEGER);
                    }
                }
            }
        }
        return total;
    }

    public static final SubLObject with_problem_store_transformation_assumptions_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject store = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt32);
            store = current.first();
            current = current.rest();
            {
                SubLObject body = current;
                SubLObject store_var = $sym33$STORE_VAR;
                return listS(CLET, list(list(store_var, store), $list_alt35, $list_alt36, $list_alt37, list($negation_by_failure$, list($sym39$PROBLEM_STORE_NEGATION_BY_FAILURE_, store_var))), append(body, NIL));
            }
        }
    }

    public static SubLObject with_problem_store_transformation_assumptions(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject store = NIL;
        destructuring_bind_must_consp(current, datum, $list40);
        store = current.first();
        final SubLObject body;
        current = body = current.rest();
        final SubLObject store_var = $sym41$STORE_VAR;
        return listS(CLET, list(list(store_var, store), $list43, $list44, $list45, list($negation_by_failure$, list($sym47$PROBLEM_STORE_NEGATION_BY_FAILURE_, store_var))), append(body, NIL));
    }

    public static final SubLObject meta_transformation_tactic_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(v_object)) && (NIL != meta_transformation_module_p(inference_datastructures_tactic.tactic_hl_module(v_object))));
    }

    public static SubLObject meta_transformation_tactic_p(final SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(v_object)) && (NIL != inference_modules.meta_transformation_module_p(inference_datastructures_tactic.tactic_hl_module(v_object))));
    }

    /**
     * First we add a tactic which, if executed, determines the rest of the transformation tactics for PROBLEM.
     */
    @LispMethod(comment = "First we add a tactic which, if executed, determines the rest of the transformation tactics for PROBLEM.")
    public static final SubLObject add_tactic_to_determine_new_literal_transformation_tactics_alt(SubLObject problem, SubLObject asent, SubLObject sense, SubLObject mt) {
        if (NIL == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_backchain_forbidden_asentP(asent, mt)) {
            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_meta_transformation_tactic(problem, asent, sense);
            return T;
        }
        return NIL;
    }

    /**
     * First we add a tactic which, if executed, determines the rest of the transformation tactics for PROBLEM.
     */
    @LispMethod(comment = "First we add a tactic which, if executed, determines the rest of the transformation tactics for PROBLEM.")
    public static SubLObject add_tactic_to_determine_new_literal_transformation_tactics(final SubLObject problem, final SubLObject asent, final SubLObject sense, final SubLObject mt) {
        if (NIL == inference_backchain_forbidden_asentP(asent, mt)) {
            new_meta_transformation_tactic(problem, asent, sense);
            return T;
        }
        return NIL;
    }

    public static final SubLObject inference_backchain_forbidden_asentP_alt(SubLObject asent, SubLObject mt) {
        {
            SubLObject predicate = cycl_utilities.atomic_sentence_predicate(asent);
            if ((NIL != forts.fort_p(predicate)) && (NIL != inference_trampolines.inference_backchain_forbiddenP(predicate, mt))) {
                return T;
            } else {
                if (NIL != inference_completeness_utilities.inference_complete_asentP(asent, mt)) {
                    return T;
                } else {
                    return NIL;
                }
            }
        }
    }

    public static SubLObject inference_backchain_forbidden_asentP(final SubLObject asent, final SubLObject mt) {
        final SubLObject predicate = cycl_utilities.atomic_sentence_predicate(asent);
        if ((NIL != fort_p(predicate)) && (NIL != inference_trampolines.inference_backchain_forbiddenP(predicate, mt))) {
            return T;
        }
        if (NIL != inference_completeness_utilities.inference_complete_asentP(asent, mt)) {
            return T;
        }
        return NIL;
    }

    public static final SubLObject new_meta_transformation_tactic_alt(SubLObject problem, SubLObject asent, SubLObject sense) {
        {
            SubLObject tactic = inference_datastructures_tactic.new_tactic(problem, $determine_new_transformation_tactics_module$.getGlobalValue(), UNPROVIDED);
            SubLObject productivity = ZERO_INTEGER;
            inference_datastructures_tactic.set_tactic_completeness(tactic, $GROSSLY_INCOMPLETE);
            inference_datastructures_tactic.set_tactic_productivity(tactic, productivity, UNPROVIDED);
            {
                SubLObject prob = problem;
                SubLObject store = inference_datastructures_problem.problem_store(prob);
                SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
                if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                    {
                        SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                        SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                        SubLObject inference = NIL;
                        while (NIL != id) {
                            inference = do_id_index_state_object(idx, $SKIP, id, state_var);
                            if (NIL != do_id_index_id_and_object_validP(id, inference, $SKIP)) {
                                if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(prob, inference)) {
                                    {
                                        SubLObject inference_var = inference;
                                        SubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));
                                        SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                                        SubLObject state = NIL;
                                        for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                            {
                                                SubLObject strategy = set_contents.do_set_contents_next(basis_object, state);
                                                if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                                    inference_tactician.strategy_note_new_tactic(strategy, tactic);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            id = do_id_index_next_id(idx, NIL, id, state_var);
                            state_var = do_id_index_next_state(idx, NIL, id, state_var);
                        } 
                    }
                }
            }
            return tactic;
        }
    }

    public static SubLObject new_meta_transformation_tactic(final SubLObject problem, final SubLObject asent, final SubLObject sense) {
        final SubLObject tactic = inference_datastructures_tactic.new_tactic(problem, $determine_new_transformation_tactics_module$.getGlobalValue(), UNPROVIDED);
        final SubLObject productivity = ZERO_INTEGER;
        inference_datastructures_tactic.set_tactic_completeness(tactic, $GROSSLY_INCOMPLETE);
        inference_datastructures_tactic.set_tactic_productivity(tactic, productivity, UNPROVIDED);
        final SubLObject store = inference_datastructures_problem.problem_store(problem);
        final SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
        if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
            final SubLObject idx_$13 = idx;
            if (NIL == id_index_dense_objects_empty_p(idx_$13, $SKIP)) {
                final SubLObject vector_var = id_index_dense_objects(idx_$13);
                final SubLObject backwardP_var = NIL;
                SubLObject length;
                SubLObject v_iteration;
                SubLObject id;
                SubLObject inference;
                SubLObject inference_var;
                SubLObject set_var;
                SubLObject set_contents_var;
                SubLObject basis_object;
                SubLObject state;
                SubLObject strategy;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    inference = aref(vector_var, id);
                    if ((NIL == id_index_tombstone_p(inference)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                        if (NIL != id_index_tombstone_p(inference)) {
                            inference = $SKIP;
                        }
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference)) {
                            inference_var = inference;
                            set_var = inference_datastructures_inference.inference_strategy_set(inference_var);
                            set_contents_var = set.do_set_internal(set_var);
                            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                strategy = set_contents.do_set_contents_next(basis_object, state);
                                if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                    inference_tactician.strategy_note_new_tactic(strategy, tactic);
                                }
                            }
                        }
                    }
                }
            }
            final SubLObject idx_$14 = idx;
            if (NIL == id_index_sparse_objects_empty_p(idx_$14)) {
                final SubLObject cdohash_table = id_index_sparse_objects(idx_$14);
                SubLObject id2 = NIL;
                SubLObject inference2 = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        id2 = getEntryKey(cdohash_entry);
                        inference2 = getEntryValue(cdohash_entry);
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference2)) {
                            final SubLObject inference_var2 = inference2;
                            final SubLObject set_var2 = inference_datastructures_inference.inference_strategy_set(inference_var2);
                            final SubLObject set_contents_var2 = set.do_set_internal(set_var2);
                            SubLObject basis_object2;
                            SubLObject state2;
                            SubLObject strategy2;
                            for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2, state2); state2 = set_contents.do_set_contents_update_state(state2)) {
                                strategy2 = set_contents.do_set_contents_next(basis_object2, state2);
                                if (NIL != set_contents.do_set_contents_element_validP(state2, strategy2)) {
                                    inference_tactician.strategy_note_new_tactic(strategy2, tactic);
                                }
                            }
                        }
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
            }
        }
        return tactic;
    }

    public static final SubLObject compute_strategic_properties_of_meta_transformation_tactic_alt(SubLObject tactic, SubLObject strategy) {
        return tactic;
    }

    public static SubLObject compute_strategic_properties_of_meta_transformation_tactic(final SubLObject tactic, final SubLObject strategy) {
        return tactic;
    }

    public static final SubLObject transformation_link_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_problem_link.problem_link_p(v_object)) && ($TRANSFORMATION == inference_datastructures_problem_link.problem_link_type(v_object)));
    }

    public static SubLObject transformation_link_p(final SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_problem_link.problem_link_p(v_object)) && ($TRANSFORMATION == inference_datastructures_problem_link.problem_link_type(v_object)));
    }

    public static final SubLObject transformation_tactic_p_alt(SubLObject tactic) {
        return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(tactic)) && ($TRANSFORMATION == inference_datastructures_tactic.tactic_type(tactic)));
    }

    public static SubLObject transformation_tactic_p(final SubLObject tactic) {
        return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(tactic)) && ($TRANSFORMATION == inference_datastructures_tactic.tactic_type(tactic)));
    }

    /**
     *
     *
     * @return rule-assertion?; the rule assertion associated with TACTIC
    temporarily sometimes returns NIL while transformation modules are in transition.
     */
    @LispMethod(comment = "@return rule-assertion?; the rule assertion associated with TACTIC\r\ntemporarily sometimes returns NIL while transformation modules are in transition.")
    public static final SubLObject transformation_tactic_rule_alt(SubLObject transformation_tactic) {
        return inference_datastructures_tactic.tactic_data(transformation_tactic);
    }

    /**
     *
     *
     * @return rule-assertion?; the rule assertion associated with TACTIC
    temporarily sometimes returns NIL while transformation modules are in transition.
     */
    @LispMethod(comment = "@return rule-assertion?; the rule assertion associated with TACTIC\r\ntemporarily sometimes returns NIL while transformation modules are in transition.")
    public static SubLObject transformation_tactic_rule(final SubLObject transformation_tactic) {
        return inference_datastructures_tactic.tactic_data(transformation_tactic);
    }

    public static final SubLObject transformation_rule_tactic_p_alt(SubLObject v_object) {
        if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_p(v_object)) {
            return list_utilities.sublisp_boolean(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_rule(v_object));
        }
        return NIL;
    }

    public static SubLObject transformation_rule_tactic_p(final SubLObject v_object) {
        if (NIL != transformation_tactic_p(v_object)) {
            return list_utilities.sublisp_boolean(transformation_tactic_rule(v_object));
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; whether OBJECT is a transformation tactic that generates other
    transformation tactics.
     */
    @LispMethod(comment = "@return booleanp; whether OBJECT is a transformation tactic that generates other\r\ntransformation tactics.")
    public static final SubLObject transformation_generator_tactic_p_alt(SubLObject v_object) {
        if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_p(v_object)) {
            return sublisp_null(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_rule(v_object));
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; whether OBJECT is a transformation tactic that generates other
    transformation tactics.
     */
    @LispMethod(comment = "@return booleanp; whether OBJECT is a transformation tactic that generates other\r\ntransformation tactics.")
    public static SubLObject transformation_generator_tactic_p(final SubLObject v_object) {
        if (NIL != transformation_tactic_p(v_object)) {
            return sublisp_null(transformation_tactic_rule(v_object));
        }
        return NIL;
    }

    /**
     * Return the next rule that TRANSFORMATION-GENERATOR-TACTIC would generate, if any.
     */
    @LispMethod(comment = "Return the next rule that TRANSFORMATION-GENERATOR-TACTIC would generate, if any.")
    public static final SubLObject transformation_generator_tactic_lookahead_rule_alt(SubLObject transformation_generator_tactic) {
        SubLTrampolineFile.checkType(transformation_generator_tactic, TRANSFORMATION_GENERATOR_TACTIC_P);
        {
            SubLObject iterator = inference_datastructures_tactic.tactic_progress_iterator(transformation_generator_tactic);
            if (NIL != iteration.iterator_p(iterator)) {
                {
                    SubLObject state = iteration.iteration_state_peek(iterator);
                    if (state.isList()) {
                        {
                            SubLObject rules = state.first();
                            if (rules.isList()) {
                                {
                                    SubLObject rule = rules.first();
                                    if (NIL != assertion_handles.assertion_p(rule)) {
                                        return rule;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Return the next rule that TRANSFORMATION-GENERATOR-TACTIC would generate, if any.
     */
    @LispMethod(comment = "Return the next rule that TRANSFORMATION-GENERATOR-TACTIC would generate, if any.")
    public static SubLObject transformation_generator_tactic_lookahead_rule(final SubLObject transformation_generator_tactic) {
        assert NIL != transformation_generator_tactic_p(transformation_generator_tactic) : "! inference_worker_transformation.transformation_generator_tactic_p(transformation_generator_tactic) " + ("inference_worker_transformation.transformation_generator_tactic_p(transformation_generator_tactic) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_generator_tactic_p(transformation_generator_tactic) ") + transformation_generator_tactic;
        final SubLObject iterator = inference_datastructures_tactic.tactic_progress_iterator(transformation_generator_tactic);
        if (NIL != iterator_p(iterator)) {
            final SubLObject state = iteration_state_peek(iterator);
            if (state.isList()) {
                final SubLObject rules = state.first();
                if (rules.isList()) {
                    final SubLObject rule = rules.first();
                    if (NIL != assertion_handles.assertion_p(rule)) {
                        return rule;
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Return the rule to use for lookahead heuristic analysis of TRANSFORMATION-TACTIC.
     */
    @LispMethod(comment = "Return the rule to use for lookahead heuristic analysis of TRANSFORMATION-TACTIC.")
    public static final SubLObject transformation_tactic_lookahead_rule_alt(SubLObject transformation_tactic) {
        {
            SubLObject rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_rule(transformation_tactic);
            if (NIL == rule) {
                rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_generator_tactic_lookahead_rule(transformation_tactic);
            }
            return rule;
        }
    }

    /**
     * Return the rule to use for lookahead heuristic analysis of TRANSFORMATION-TACTIC.
     */
    @LispMethod(comment = "Return the rule to use for lookahead heuristic analysis of TRANSFORMATION-TACTIC.")
    public static SubLObject transformation_tactic_lookahead_rule(final SubLObject transformation_tactic) {
        SubLObject rule = transformation_tactic_rule(transformation_tactic);
        if (NIL == rule) {
            rule = transformation_generator_tactic_lookahead_rule(transformation_tactic);
        }
        return rule;
    }

    public static final SubLObject transformation_proof_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_proof.proof_p(v_object)) && (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_p(inference_datastructures_proof.proof_link(v_object))));
    }

    public static SubLObject transformation_proof_p(final SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_proof.proof_p(v_object)) && (NIL != transformation_link_p(inference_datastructures_proof.proof_link(v_object))));
    }

    public static final SubLObject transformation_proof_rule_assertion_alt(SubLObject proof) {
        SubLTrampolineFile.checkType(proof, TRANSFORMATION_PROOF_P);
        return inference_datastructures_proof.proof_supports(proof).first();
    }

    public static SubLObject transformation_proof_rule_assertion(final SubLObject proof) {
        assert NIL != transformation_proof_p(proof) : "! inference_worker_transformation.transformation_proof_p(proof) " + ("inference_worker_transformation.transformation_proof_p(proof) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_proof_p(proof) ") + proof;
        return inference_datastructures_proof.proof_supports(proof).first();
    }

    public static final SubLObject transformation_proof_additional_supports_alt(SubLObject proof) {
        SubLTrampolineFile.checkType(proof, TRANSFORMATION_PROOF_P);
        return inference_datastructures_proof.proof_supports(proof).rest();
    }

    public static SubLObject transformation_proof_additional_supports(final SubLObject proof) {
        assert NIL != transformation_proof_p(proof) : "! inference_worker_transformation.transformation_proof_p(proof) " + ("inference_worker_transformation.transformation_proof_p(proof) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_proof_p(proof) ") + proof;
        return inference_datastructures_proof.proof_supports(proof).rest();
    }

    /**
     *
     *
     * @return nil or proof-p
     */
    @LispMethod(comment = "@return nil or proof-p")
    public static final SubLObject transformation_proof_subproof_alt(SubLObject proof) {
        SubLTrampolineFile.checkType(proof, TRANSFORMATION_PROOF_P);
        return inference_datastructures_proof.proof_first_subproof(proof);
    }

    /**
     *
     *
     * @return nil or proof-p
     */
    @LispMethod(comment = "@return nil or proof-p")
    public static SubLObject transformation_proof_subproof(final SubLObject proof) {
        assert NIL != transformation_proof_p(proof) : "! inference_worker_transformation.transformation_proof_p(proof) " + ("inference_worker_transformation.transformation_proof_p(proof) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_proof_p(proof) ") + proof;
        return inference_datastructures_proof.proof_first_subproof(proof);
    }

    public static final SubLObject generalized_transformation_link_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_p(v_object)) || (NIL != inference_worker_residual_transformation.residual_transformation_link_p(v_object)));
    }

    public static SubLObject generalized_transformation_link_p(final SubLObject v_object) {
        return makeBoolean((NIL != transformation_link_p(v_object)) || (NIL != inference_worker_residual_transformation.residual_transformation_link_p(v_object)));
    }

    public static final SubLObject generalized_transformation_link_rule_assertion_alt(SubLObject link) {
        if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_p(link)) {
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_rule_assertion(link);
        } else {
            if (NIL != inference_worker_residual_transformation.residual_transformation_link_p(link)) {
                return inference_worker_residual_transformation.residual_transformation_link_rule_assertion(link);
            } else {
                return Errors.error($str_alt46$generalized_transformation_link_o, link);
            }
        }
    }

    public static SubLObject generalized_transformation_link_rule_assertion(final SubLObject link) {
        if (NIL != transformation_link_p(link)) {
            return transformation_link_rule_assertion(link);
        }
        if (NIL != inference_worker_residual_transformation.residual_transformation_link_p(link)) {
            return inference_worker_residual_transformation.residual_transformation_link_rule_assertion(link);
        }
        return Errors.error($str54$generalized_transformation_link_o, link);
    }

    public static final SubLObject generalized_transformation_link_unaffected_by_exceptionsP_alt(SubLObject link) {
        return makeBoolean(NIL == abnormal.rule_has_exceptionsP(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.generalized_transformation_link_rule_assertion(link)));
    }

    public static SubLObject generalized_transformation_link_unaffected_by_exceptionsP(final SubLObject link) {
        return makeBoolean(NIL == abnormal.rule_has_exceptionsP(generalized_transformation_link_rule_assertion(link)));
    }

    public static final SubLObject generalized_transformation_proof_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_proof.proof_p(v_object)) && (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.generalized_transformation_link_p(inference_datastructures_proof.proof_link(v_object))));
    }

    public static SubLObject generalized_transformation_proof_p(final SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_proof.proof_p(v_object)) && (NIL != generalized_transformation_link_p(inference_datastructures_proof.proof_link(v_object))));
    }

    public static final SubLObject generalized_transformation_proof_rule_assertion_alt(SubLObject proof) {
        if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_p(proof)) {
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_rule_assertion(proof);
        } else {
            if (NIL != inference_worker_residual_transformation.residual_transformation_proof_p(proof)) {
                return inference_worker_residual_transformation.residual_transformation_proof_rule_assertion(proof);
            } else {
                return Errors.error($str_alt47$generalized_transformation_proof_, proof);
            }
        }
    }

    public static SubLObject generalized_transformation_proof_rule_assertion(final SubLObject proof) {
        if (NIL != transformation_proof_p(proof)) {
            return transformation_proof_rule_assertion(proof);
        }
        if (NIL != inference_worker_residual_transformation.residual_transformation_proof_p(proof)) {
            return inference_worker_residual_transformation.residual_transformation_proof_rule_assertion(proof);
        }
        return Errors.error($str55$generalized_transformation_proof_, proof);
    }

    public static SubLObject generalized_transformation_proof_rule_bindings(final SubLObject proof) {
        if (NIL != transformation_proof_p(proof)) {
            return transformation_proof_rule_bindings(proof);
        }
        if (NIL != inference_worker_residual_transformation.residual_transformation_proof_p(proof)) {
            return inference_worker_residual_transformation.residual_transformation_proof_rule_bindings(proof);
        }
        return Errors.error($str55$generalized_transformation_proof_, proof);
    }

    public static final SubLObject generalized_transformation_proof_transformation_link_alt(SubLObject proof) {
        if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_p(proof)) {
            return inference_datastructures_proof.proof_link(proof);
        } else {
            if (NIL != inference_worker_residual_transformation.residual_transformation_proof_p(proof)) {
                return inference_worker_residual_transformation.residual_transformation_proof_motivating_transformation_link(proof);
            } else {
                return Errors.error($str_alt47$generalized_transformation_proof_, proof);
            }
        }
    }

    public static SubLObject generalized_transformation_proof_transformation_link(final SubLObject proof) {
        if (NIL != transformation_proof_p(proof)) {
            return inference_datastructures_proof.proof_link(proof);
        }
        if (NIL != inference_worker_residual_transformation.residual_transformation_proof_p(proof)) {
            return inference_worker_residual_transformation.residual_transformation_proof_motivating_transformation_link(proof);
        }
        return Errors.error($str55$generalized_transformation_proof_, proof);
    }

    public static final SubLObject determine_new_literal_transformation_tactics_alt(SubLObject problem, SubLObject asent, SubLObject sense) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject store_var = inference_datastructures_problem.problem_store(problem);
                {
                    SubLObject _prev_bind_0 = $hl_failure_backchaining$.currentBinding(thread);
                    SubLObject _prev_bind_1 = $unbound_rule_backchain_enabled$.currentBinding(thread);
                    SubLObject _prev_bind_2 = $evaluatable_backchain_enabled$.currentBinding(thread);
                    SubLObject _prev_bind_3 = $negation_by_failure$.currentBinding(thread);
                    try {
                        $hl_failure_backchaining$.bind(T, thread);
                        $unbound_rule_backchain_enabled$.bind(T, thread);
                        $evaluatable_backchain_enabled$.bind(T, thread);
                        $negation_by_failure$.bind(inference_datastructures_problem_store.problem_store_negation_by_failureP(store_var), thread);
                        com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.determine_new_literal_transformation_tactics_int(problem, asent, sense, UNPROVIDED);
                    } finally {
                        $negation_by_failure$.rebind(_prev_bind_3, thread);
                        $evaluatable_backchain_enabled$.rebind(_prev_bind_2, thread);
                        $unbound_rule_backchain_enabled$.rebind(_prev_bind_1, thread);
                        $hl_failure_backchaining$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject determine_new_literal_transformation_tactics(final SubLObject problem, final SubLObject asent, final SubLObject sense) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject store_var = inference_datastructures_problem.problem_store(problem);
        final SubLObject _prev_bind_0 = $hl_failure_backchaining$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $unbound_rule_backchain_enabled$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $evaluatable_backchain_enabled$.currentBinding(thread);
        final SubLObject _prev_bind_4 = $negation_by_failure$.currentBinding(thread);
        try {
            $hl_failure_backchaining$.bind(T, thread);
            $unbound_rule_backchain_enabled$.bind(T, thread);
            $evaluatable_backchain_enabled$.bind(T, thread);
            $negation_by_failure$.bind(inference_datastructures_problem_store.problem_store_negation_by_failureP(store_var), thread);
            determine_new_literal_transformation_tactics_int(problem, asent, sense, UNPROVIDED);
        } finally {
            $negation_by_failure$.rebind(_prev_bind_4, thread);
            $evaluatable_backchain_enabled$.rebind(_prev_bind_3, thread);
            $unbound_rule_backchain_enabled$.rebind(_prev_bind_2, thread);
            $hl_failure_backchaining$.rebind(_prev_bind_0, thread);
        }
        return NIL;
    }

    public static final SubLObject determine_new_literal_transformation_tactics_int_alt(SubLObject problem, SubLObject asent, SubLObject sense, SubLObject disabled_modules) {
        if (disabled_modules == UNPROVIDED) {
            disabled_modules = NIL;
        }
        {
            SubLObject tactic_specs = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.determine_literal_transformation_tactic_specs(asent, sense, disabled_modules);
            SubLObject cdolist_list_var = tactic_specs;
            SubLObject tactic_spec = NIL;
            for (tactic_spec = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , tactic_spec = cdolist_list_var.first()) {
                {
                    SubLObject datum = tactic_spec;
                    SubLObject current = datum;
                    SubLObject hl_module = NIL;
                    SubLObject productivity = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt48);
                    hl_module = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt48);
                    productivity = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_tactic(problem, hl_module, productivity, NIL);
                    } else {
                        cdestructuring_bind_error(datum, $list_alt48);
                    }
                }
            }
            return tactic_specs;
        }
    }

    public static SubLObject determine_new_literal_transformation_tactics_int(final SubLObject problem, final SubLObject asent, final SubLObject sense, SubLObject disabled_modules) {
        if (disabled_modules == UNPROVIDED) {
            disabled_modules = NIL;
        }
        SubLObject cdolist_list_var;
        final SubLObject tactic_specs = cdolist_list_var = determine_literal_transformation_tactic_specs(asent, sense, disabled_modules);
        SubLObject tactic_spec = NIL;
        tactic_spec = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = tactic_spec;
            SubLObject hl_module = NIL;
            SubLObject productivity = NIL;
            destructuring_bind_must_consp(current, datum, $list56);
            hl_module = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list56);
            productivity = current.first();
            current = current.rest();
            if (NIL == current) {
                new_transformation_tactic(problem, hl_module, productivity, NIL);
            } else {
                cdestructuring_bind_error(datum, $list56);
            }
            cdolist_list_var = cdolist_list_var.rest();
            tactic_spec = cdolist_list_var.first();
        } 
        return tactic_specs;
    }

    public static final SubLObject determine_rules_for_literal_transformation_tactics_alt(SubLObject problem, SubLObject asent, SubLObject hl_module) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject candidate_rules = NIL;
                thread.resetMultipleValues();
                {
                    SubLObject inference = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.problem_inference_and_non_continuable_problem_store_private(problem);
                    SubLObject non_continuable_private_problem_storeP = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if ((NIL != non_continuable_private_problem_storeP) && (NIL == inference_datastructures_inference.inference_allows_use_of_all_rulesP(inference))) {
                        {
                            SubLObject rules = NIL;
                            SubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_allowed_rules(inference));
                            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                            SubLObject state = NIL;
                            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                {
                                    SubLObject rule = set_contents.do_set_contents_next(basis_object, state);
                                    if (NIL != set_contents.do_set_contents_element_validP(state, rule)) {
                                        rules = cons(rule, rules);
                                    }
                                }
                            }
                            candidate_rules = transformation_modules.sort_rules_via_current_inference_rule_preference(rules);
                        }
                    } else {
                        {
                            SubLObject rule_select_method = hl_module_rule_select_func(hl_module);
                            if (rule_select_method.isFunctionSpec()) {
                                candidate_rules = funcall(rule_select_method, asent);
                            }
                        }
                    }
                    {
                        SubLObject rule_filter_method = hl_module_rule_filter_func(hl_module);
                        SubLObject rules = NIL;
                        if (!rule_filter_method.isFunctionSpec()) {
                            rules = copy_list(candidate_rules);
                        } else {
                            {
                                SubLObject cdolist_list_var = candidate_rules;
                                SubLObject rule = NIL;
                                for (rule = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , rule = cdolist_list_var.first()) {
                                    if (NIL != funcall(rule_filter_method, asent, rule)) {
                                        rules = cons(rule, rules);
                                    }
                                }
                            }
                            rules = nreverse(rules);
                        }
                        rules = delete_if(symbol_function($sym49$INFERENCE_EXCEPTED_ASSERTION_), rules, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.genl_rules_enabledP()) {
                            rules = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.max_rules(rules, UNPROVIDED);
                        }
                        return rules;
                    }
                }
            }
        }
    }

    public static SubLObject determine_rules_for_literal_transformation_tactics(final SubLObject problem, SubLObject asent, final SubLObject hl_module) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject mt = mt_relevance_macros.$mt$.getDynamicValue(thread);
        if (((NIL != inference_czer.backchaining_on_ist_enabledP()) && (NIL != ist_sentence_p(asent, UNPROVIDED))) && (NIL != variables.fully_bound_p(designated_mt(asent)))) {
            mt = designated_mt(asent);
            asent = designated_sentence(asent);
        }
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            SubLObject candidate_rules = NIL;
            thread.resetMultipleValues();
            final SubLObject inference = problem_inference_and_non_continuable_problem_store_private(problem);
            final SubLObject non_continuable_private_problem_storeP = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if ((NIL != non_continuable_private_problem_storeP) && (NIL == inference_datastructures_inference.inference_allows_use_of_all_rulesP(inference))) {
                SubLObject rules = NIL;
                final SubLObject set_var = inference_datastructures_inference.inference_allowed_rules(inference);
                final SubLObject set_contents_var = set.do_set_internal(set_var);
                SubLObject basis_object;
                SubLObject state;
                SubLObject rule;
                for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                    rule = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, rule)) {
                        rules = cons(rule, rules);
                    }
                }
                candidate_rules = transformation_modules.sort_rules_via_current_inference_rule_preference(rules);
            } else {
                final SubLObject rule_select_method = inference_modules.hl_module_rule_select_func(hl_module);
                if (rule_select_method.isFunctionSpec()) {
                    candidate_rules = funcall(rule_select_method, asent);
                }
            }
            final SubLObject rule_filter_method = inference_modules.hl_module_rule_filter_func(hl_module);
            SubLObject rules2 = NIL;
            if (!rule_filter_method.isFunctionSpec()) {
                rules2 = copy_list(candidate_rules);
            } else {
                SubLObject cdolist_list_var = candidate_rules;
                SubLObject rule2 = NIL;
                rule2 = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if (NIL != funcall(rule_filter_method, asent, rule2)) {
                        rules2 = cons(rule2, rules2);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    rule2 = cdolist_list_var.first();
                } 
                rules2 = nreverse(rules2);
            }
            rules2 = delete_if(symbol_function($sym57$INFERENCE_EXCEPTED_ASSERTION_), rules2, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            if (NIL != genl_rules_enabledP()) {
                rules2 = max_rules(rules2, UNPROVIDED);
            }
            return rules2;
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
    }

    /**
     * Given a problem get its inference if the problem store it is in is private for its inference
     * Also return whether the problem store is private and the inference is non-continuable.
     */
    @LispMethod(comment = "Given a problem get its inference if the problem store it is in is private for its inference\r\nAlso return whether the problem store is private and the inference is non-continuable.\nGiven a problem get its inference if the problem store it is in is private for its inference\nAlso return whether the problem store is private and the inference is non-continuable.")
    public static final SubLObject problem_inference_and_non_continuable_problem_store_private_alt(SubLObject problem) {
        {
            SubLObject problem_store = inference_datastructures_problem.problem_store(problem);
            SubLObject non_continuableP = NIL;
            SubLObject problem_store_privateP = NIL;
            SubLObject inference = NIL;
            if (NIL != inference_datastructures_problem_store.problem_store_has_only_one_inferenceP(problem_store)) {
                inference = inference_datastructures_problem_store.first_problem_store_inference(problem_store);
                problem_store_privateP = inference_datastructures_inference.inference_problem_store_privateP(inference);
                non_continuableP = makeBoolean(NIL == inference_datastructures_inference.inference_continuableP(inference));
            }
            return values(inference, makeBoolean((NIL != problem_store_privateP) && (NIL != non_continuableP)));
        }
    }

    /**
     * Given a problem get its inference if the problem store it is in is private for its inference
     * Also return whether the problem store is private and the inference is non-continuable.
     */
    @LispMethod(comment = "Given a problem get its inference if the problem store it is in is private for its inference\r\nAlso return whether the problem store is private and the inference is non-continuable.\nGiven a problem get its inference if the problem store it is in is private for its inference\nAlso return whether the problem store is private and the inference is non-continuable.")
    public static SubLObject problem_inference_and_non_continuable_problem_store_private(final SubLObject problem) {
        final SubLObject problem_store = inference_datastructures_problem.problem_store(problem);
        SubLObject non_continuableP = NIL;
        SubLObject problem_store_privateP = NIL;
        SubLObject inference = NIL;
        if (NIL != inference_datastructures_problem_store.problem_store_has_only_one_inferenceP(problem_store)) {
            inference = inference_datastructures_problem_store.first_problem_store_inference(problem_store);
            problem_store_privateP = inference_datastructures_inference.inference_problem_store_privateP(inference);
            non_continuableP = makeBoolean(NIL == inference_datastructures_inference.inference_continuableP(inference));
        }
        return values(inference, makeBoolean((NIL != problem_store_privateP) && (NIL != non_continuableP)));
    }

    public static final SubLObject single_literal_problem_candidate_transformation_tactic_specs_alt(SubLObject problem) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(problem, SINGLE_LITERAL_PROBLEM_P);
            {
                SubLObject asent = inference_datastructures_problem.single_literal_problem_atomic_sentence(problem);
                SubLObject sense = inference_datastructures_problem.single_literal_problem_sense(problem);
                SubLObject tactic_specs = NIL;
                SubLObject store_var = inference_datastructures_problem.problem_store(problem);
                {
                    SubLObject _prev_bind_0 = $hl_failure_backchaining$.currentBinding(thread);
                    SubLObject _prev_bind_1 = $unbound_rule_backchain_enabled$.currentBinding(thread);
                    SubLObject _prev_bind_2 = $evaluatable_backchain_enabled$.currentBinding(thread);
                    SubLObject _prev_bind_3 = $negation_by_failure$.currentBinding(thread);
                    try {
                        $hl_failure_backchaining$.bind(T, thread);
                        $unbound_rule_backchain_enabled$.bind(T, thread);
                        $evaluatable_backchain_enabled$.bind(T, thread);
                        $negation_by_failure$.bind(inference_datastructures_problem_store.problem_store_negation_by_failureP(store_var), thread);
                        tactic_specs = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.determine_literal_transformation_tactic_specs(asent, sense, NIL);
                    } finally {
                        $negation_by_failure$.rebind(_prev_bind_3, thread);
                        $evaluatable_backchain_enabled$.rebind(_prev_bind_2, thread);
                        $unbound_rule_backchain_enabled$.rebind(_prev_bind_1, thread);
                        $hl_failure_backchaining$.rebind(_prev_bind_0, thread);
                    }
                }
                return tactic_specs;
            }
        }
    }

    public static SubLObject single_literal_problem_candidate_transformation_tactic_specs(final SubLObject problem) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != inference_datastructures_problem.single_literal_problem_p(problem) : "! inference_datastructures_problem.single_literal_problem_p(problem) " + ("inference_datastructures_problem.single_literal_problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.single_literal_problem_p(problem) ") + problem;
        final SubLObject asent = inference_datastructures_problem.single_literal_problem_atomic_sentence(problem);
        final SubLObject sense = inference_datastructures_problem.single_literal_problem_sense(problem);
        SubLObject tactic_specs = NIL;
        final SubLObject store_var = inference_datastructures_problem.problem_store(problem);
        final SubLObject _prev_bind_0 = $hl_failure_backchaining$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $unbound_rule_backchain_enabled$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $evaluatable_backchain_enabled$.currentBinding(thread);
        final SubLObject _prev_bind_4 = $negation_by_failure$.currentBinding(thread);
        try {
            $hl_failure_backchaining$.bind(T, thread);
            $unbound_rule_backchain_enabled$.bind(T, thread);
            $evaluatable_backchain_enabled$.bind(T, thread);
            $negation_by_failure$.bind(inference_datastructures_problem_store.problem_store_negation_by_failureP(store_var), thread);
            tactic_specs = determine_literal_transformation_tactic_specs(asent, sense, NIL);
        } finally {
            $negation_by_failure$.rebind(_prev_bind_4, thread);
            $evaluatable_backchain_enabled$.rebind(_prev_bind_3, thread);
            $unbound_rule_backchain_enabled$.rebind(_prev_bind_2, thread);
            $hl_failure_backchaining$.rebind(_prev_bind_0, thread);
        }
        return tactic_specs;
    }

    /**
     * Returns lists of the form (hl-module productivity), :complete is the assumed completeness
     */
    @LispMethod(comment = "Returns lists of the form (hl-module productivity), :complete is the assumed completeness")
    public static final SubLObject determine_literal_transformation_tactic_specs_alt(SubLObject asent, SubLObject sense, SubLObject disabled_modules) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.determine_literal_transformation_tactic_specs_int(asent, sense, disabled_modules, $TACTIC_SPECS);
    }

    /**
     * Returns lists of the form (hl-module productivity), :complete is the assumed completeness
     */
    @LispMethod(comment = "Returns lists of the form (hl-module productivity), :complete is the assumed completeness")
    public static SubLObject determine_literal_transformation_tactic_specs(final SubLObject asent, final SubLObject sense, final SubLObject disabled_modules) {
        return determine_literal_transformation_tactic_specs_int(asent, sense, disabled_modules, $TACTIC_SPECS);
    }

    /**
     *
     *
     * @param RETURN-TYPE
     * 		keywordp; either :tactic-spec or :total-productivity.
     * 		If :tactic-specs, returns lists of the form (hl-module productivity), where :complete is the assumed completeness.
     * 		If :total-productivity, returns a productivity-p which is the sum of all the applicable productivities.
     */
    @LispMethod(comment = "@param RETURN-TYPE\r\n\t\tkeywordp; either :tactic-spec or :total-productivity.\r\n\t\tIf :tactic-specs, returns lists of the form (hl-module productivity), where :complete is the assumed completeness.\r\n\t\tIf :total-productivity, returns a productivity-p which is the sum of all the applicable productivities.")
    public static final SubLObject determine_literal_transformation_tactic_specs_int_alt(SubLObject asent, SubLObject sense, SubLObject disabled_modules, SubLObject return_type) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject tactic_specs = NIL;
                SubLObject total_productivity = ZERO_INTEGER;
                SubLObject predicate = cycl_utilities.atomic_sentence_predicate(asent);
                SubLObject supplanted_modules = NIL;
                SubLObject exclusive_foundP = NIL;
                SubLObject rest = NIL;
                for (rest = transformation_modules(); !((NIL != exclusive_foundP) || (NIL == rest)); rest = rest.rest()) {
                    {
                        SubLObject hl_module = rest.first();
                        if (NIL != hl_module_activeP(hl_module, disabled_modules)) {
                            if (!((NIL != supplanted_modules) && (NIL != member(hl_module, supplanted_modules, UNPROVIDED, UNPROVIDED)))) {
                                if (((((NIL != hl_module_sense_relevant_p(hl_module, sense)) && (NIL != hl_module_predicate_relevant_p(hl_module, predicate))) && (NIL != hl_module_required_pattern_matched_p(hl_module, asent))) && (NIL != hl_module_required_mt_relevantP(hl_module))) && (NIL != hl_module_direction_relevantP(hl_module))) {
                                    {
                                        SubLObject exclusive_func = hl_module_exclusive_func(hl_module);
                                        if ((NIL == exclusive_func) || (exclusive_func.isFunctionSpec() && (NIL != funcall(exclusive_func, asent)))) {
                                            if (NIL != exclusive_func) {
                                                thread.resetMultipleValues();
                                                {
                                                    SubLObject exclusive_foundP_1 = inference_worker_removal.update_supplanted_modules_wrt_tactic_specs(hl_module, tactic_specs, supplanted_modules);
                                                    SubLObject tactic_specs_2 = thread.secondMultipleValue();
                                                    SubLObject supplanted_modules_3 = thread.thirdMultipleValue();
                                                    thread.resetMultipleValues();
                                                    exclusive_foundP = exclusive_foundP_1;
                                                    tactic_specs = tactic_specs_2;
                                                    supplanted_modules = supplanted_modules_3;
                                                }
                                            }
                                            {
                                                SubLObject required_func = hl_module_required_func(hl_module);
                                                if ((NIL == required_func) || (required_func.isFunctionSpec() && (NIL != funcall(required_func, asent)))) {
                                                    {
                                                        SubLObject cost = hl_module_asent_cost(hl_module, asent);
                                                        if (NIL != cost) {
                                                            {
                                                                SubLObject productivity = inference_datastructures_enumerated_types.productivity_for_number_of_children(cost);
                                                                SubLObject pcase_var = return_type;
                                                                if (pcase_var.eql($TACTIC_SPECS)) {
                                                                    {
                                                                        SubLObject tactic_spec = list(hl_module, productivity);
                                                                        tactic_specs = cons(tactic_spec, tactic_specs);
                                                                    }
                                                                } else {
                                                                    if (pcase_var.eql($TOTAL_PRODUCTIVITY)) {
                                                                        total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, productivity);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject pcase_var = return_type;
                    if (pcase_var.eql($TACTIC_SPECS)) {
                        return tactic_specs;
                    } else {
                        if (pcase_var.eql($TOTAL_PRODUCTIVITY)) {
                            return total_productivity;
                        } else {
                            Errors.error($str_alt55$unexpected_tactic_specs_return_ty, return_type);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param RETURN-TYPE
     * 		keywordp; either :tactic-spec or :total-productivity.
     * 		If :tactic-specs, returns lists of the form (hl-module productivity), where :complete is the assumed completeness.
     * 		If :total-productivity, returns a productivity-p which is the sum of all the applicable productivities.
     */
    @LispMethod(comment = "@param RETURN-TYPE\r\n\t\tkeywordp; either :tactic-spec or :total-productivity.\r\n\t\tIf :tactic-specs, returns lists of the form (hl-module productivity), where :complete is the assumed completeness.\r\n\t\tIf :total-productivity, returns a productivity-p which is the sum of all the applicable productivities.")
    public static SubLObject determine_literal_transformation_tactic_specs_int(final SubLObject asent, final SubLObject sense, final SubLObject disabled_modules, final SubLObject return_type) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject tactic_specs = NIL;
        SubLObject total_productivity = ZERO_INTEGER;
        final SubLObject predicate = cycl_utilities.atomic_sentence_predicate(asent);
        SubLObject supplanted_modules = NIL;
        SubLObject exclusive_foundP;
        SubLObject rest;
        SubLObject hl_module;
        SubLObject exclusive_func;
        SubLObject exclusive_foundP_$15;
        SubLObject tactic_specs_$16;
        SubLObject supplanted_modules_$17;
        SubLObject required_func;
        SubLObject cost;
        SubLObject productivity;
        SubLObject tactic_spec;
        for (exclusive_foundP = NIL, rest = NIL, rest = inference_modules.transformation_modules(); (NIL == exclusive_foundP) && (NIL != rest); rest = rest.rest()) {
            hl_module = rest.first();
            if (((((((NIL != inference_modules.hl_module_activeP(hl_module, disabled_modules)) && ((NIL == supplanted_modules) || (NIL == member(hl_module, supplanted_modules, UNPROVIDED, UNPROVIDED)))) && (NIL != inference_modules.hl_module_sense_relevant_p(hl_module, sense))) && (NIL != inference_modules.hl_module_predicate_relevant_p(hl_module, predicate))) && (NIL != inference_modules.hl_module_required_pattern_matched_p(hl_module, asent))) && (NIL != inference_modules.hl_module_required_mt_relevantP(hl_module))) && (NIL != inference_modules.hl_module_direction_relevantP(hl_module))) {
                exclusive_func = inference_modules.hl_module_exclusive_func(hl_module);
                if ((NIL == exclusive_func) || (exclusive_func.isFunctionSpec() && (NIL != funcall(exclusive_func, asent)))) {
                    if (NIL != exclusive_func) {
                        thread.resetMultipleValues();
                        exclusive_foundP_$15 = inference_worker_removal.update_supplanted_modules_wrt_tactic_specs(hl_module, tactic_specs, supplanted_modules);
                        tactic_specs_$16 = thread.secondMultipleValue();
                        supplanted_modules_$17 = thread.thirdMultipleValue();
                        thread.resetMultipleValues();
                        exclusive_foundP = exclusive_foundP_$15;
                        tactic_specs = tactic_specs_$16;
                        supplanted_modules = supplanted_modules_$17;
                    }
                    required_func = inference_modules.hl_module_required_func(hl_module);
                    if ((NIL == required_func) || (required_func.isFunctionSpec() && (NIL != funcall(required_func, asent)))) {
                        cost = inference_modules.hl_module_asent_cost(hl_module, asent);
                        if (NIL != cost) {
                            productivity = inference_datastructures_enumerated_types.productivity_for_number_of_children(cost);
                            if (return_type.eql($TACTIC_SPECS)) {
                                tactic_spec = list(hl_module, productivity);
                                tactic_specs = cons(tactic_spec, tactic_specs);
                            } else
                                if (return_type.eql($TOTAL_PRODUCTIVITY)) {
                                    total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, productivity);
                                }

                        }
                    }
                }
            }
        }
        if (return_type.eql($TACTIC_SPECS)) {
            return tactic_specs;
        }
        if (return_type.eql($TOTAL_PRODUCTIVITY)) {
            return total_productivity;
        }
        Errors.error($str62$unexpected_tactic_specs_return_ty, return_type);
        return NIL;
    }

    public static final SubLObject literal_level_transformation_tactic_p_alt(SubLObject tactic) {
        return makeBoolean((NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_p(tactic)) && (NIL != inference_worker.literal_level_tactic_p(tactic)));
    }

    public static SubLObject literal_level_transformation_tactic_p(final SubLObject tactic) {
        return makeBoolean((NIL != transformation_tactic_p(tactic)) && (NIL != inference_worker.literal_level_tactic_p(tactic)));
    }

    /**
     *
     *
     * @return nil or transformation-link-p
    Creates a new transformation link iff it would be interesting to do so.
     */
    @LispMethod(comment = "@return nil or transformation-link-p\r\nCreates a new transformation link iff it would be interesting to do so.")
    public static final SubLObject maybe_new_transformation_link_alt(SubLObject supported_problem, SubLObject supporting_mapped_problem, SubLObject tactic, SubLObject transformation_bindings, SubLObject rule_assertion, SubLObject more_supports, SubLObject non_explanatory_subquery) {
        {
            SubLObject mt = inference_datastructures_problem.single_literal_problem_mt(supported_problem);
            if (NIL == leviathan.rule_bindings_wff_cachedP(rule_assertion, transformation_bindings, mt)) {
                Errors.warn($str_alt56$pruning__s__s__s, rule_assertion, mt, transformation_bindings);
                return NIL;
            }
        }
        {
            SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(tactic);
            SubLObject transformation_link = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_link(supported_problem, supporting_mapped_problem, hl_module, transformation_bindings, rule_assertion, more_supports, non_explanatory_subquery);
            SubLObject store = inference_datastructures_problem.problem_store(supported_problem);
            inference_datastructures_problem_store.problem_store_note_transformation_rule_considered(store, rule_assertion);
            inference_worker_residual_transformation.maybe_possibly_add_residual_transformation_links_via_transformation_link(transformation_link);
            if (NIL != supporting_mapped_problem) {
                com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.recompute_thrown_away_due_to_new_transformation_link(inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem));
            }
            return transformation_link;
        }
    }

    /**
     *
     *
     * @return nil or transformation-link-p
    Creates a new transformation link iff it would be interesting to do so.
     */
    @LispMethod(comment = "@return nil or transformation-link-p\r\nCreates a new transformation link iff it would be interesting to do so.")
    public static SubLObject maybe_new_transformation_link(final SubLObject supported_problem, final SubLObject supporting_mapped_problem, final SubLObject tactic, final SubLObject transformation_bindings, final SubLObject rule_assertion, final SubLObject more_supports, final SubLObject non_explanatory_subquery) {
        final SubLObject mt = inference_datastructures_problem.single_literal_problem_mt(supported_problem);
        if (NIL == leviathan.rule_bindings_wff_cachedP(rule_assertion, transformation_bindings, mt)) {
            Errors.warn($str63$pruning__s__s__s, rule_assertion, mt, transformation_bindings);
            return NIL;
        }
        final SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(tactic);
        final SubLObject transformation_link = new_transformation_link(supported_problem, supporting_mapped_problem, hl_module, transformation_bindings, rule_assertion, more_supports, non_explanatory_subquery);
        final SubLObject store = inference_datastructures_problem.problem_store(supported_problem);
        inference_datastructures_problem_store.problem_store_note_transformation_rule_considered(store, rule_assertion);
        inference_worker_residual_transformation.maybe_possibly_add_residual_transformation_links_via_transformation_link(transformation_link);
        if (NIL != supporting_mapped_problem) {
            recompute_thrown_away_due_to_new_transformation_link(inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem));
        }
        return transformation_link;
    }

    public static final SubLObject recompute_thrown_away_due_to_new_transformation_link_alt(SubLObject problem) {
        {
            SubLObject prob = problem;
            SubLObject store = inference_datastructures_problem.problem_store(prob);
            SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
            if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                {
                    SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                    SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                    SubLObject inference = NIL;
                    while (NIL != id) {
                        inference = do_id_index_state_object(idx, $SKIP, id, state_var);
                        if (NIL != do_id_index_id_and_object_validP(id, inference, $SKIP)) {
                            if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(prob, inference)) {
                                {
                                    SubLObject inference_var = inference;
                                    SubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));
                                    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                                    SubLObject state = NIL;
                                    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                        {
                                            SubLObject strategy = set_contents.do_set_contents_next(basis_object, state);
                                            if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                                inference_datastructures_strategy.set_problem_recompute_thrown_away_wrt_all_motivations(problem, strategy);
                                                if (NIL != inference_abduction_utilities.abductive_strategy_p(strategy)) {
                                                    {
                                                        SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
                                                        SubLObject sibling_tactic = NIL;
                                                        for (sibling_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sibling_tactic = cdolist_list_var.first()) {
                                                            inference_datastructures_strategy.set_tactic_recompute_thrown_away_wrt(sibling_tactic, strategy, $REMOVAL);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        id = do_id_index_next_id(idx, NIL, id, state_var);
                        state_var = do_id_index_next_state(idx, NIL, id, state_var);
                    } 
                }
            }
        }
        return NIL;
    }

    public static SubLObject recompute_thrown_away_due_to_new_transformation_link(final SubLObject problem) {
        final SubLObject store = inference_datastructures_problem.problem_store(problem);
        final SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
        if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
            final SubLObject idx_$18 = idx;
            if (NIL == id_index_dense_objects_empty_p(idx_$18, $SKIP)) {
                final SubLObject vector_var = id_index_dense_objects(idx_$18);
                final SubLObject backwardP_var = NIL;
                SubLObject length;
                SubLObject v_iteration;
                SubLObject id;
                SubLObject inference;
                SubLObject inference_var;
                SubLObject set_var;
                SubLObject set_contents_var;
                SubLObject basis_object;
                SubLObject state;
                SubLObject strategy;
                SubLObject cdolist_list_var;
                SubLObject sibling_tactic;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    inference = aref(vector_var, id);
                    if ((NIL == id_index_tombstone_p(inference)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                        if (NIL != id_index_tombstone_p(inference)) {
                            inference = $SKIP;
                        }
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference)) {
                            inference_var = inference;
                            set_var = inference_datastructures_inference.inference_strategy_set(inference_var);
                            set_contents_var = set.do_set_internal(set_var);
                            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                strategy = set_contents.do_set_contents_next(basis_object, state);
                                if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                    inference_datastructures_strategy.set_problem_recompute_thrown_away(problem, strategy);
                                    if (NIL != abductive_tactician.abductive_strategy_p(strategy)) {
                                        cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
                                        sibling_tactic = NIL;
                                        sibling_tactic = cdolist_list_var.first();
                                        while (NIL != cdolist_list_var) {
                                            inference_datastructures_strategy.set_tactic_recompute_thrown_away(sibling_tactic, strategy);
                                            cdolist_list_var = cdolist_list_var.rest();
                                            sibling_tactic = cdolist_list_var.first();
                                        } 
                                    }
                                }
                            }
                        }
                    }
                }
            }
            final SubLObject idx_$19 = idx;
            if (NIL == id_index_sparse_objects_empty_p(idx_$19)) {
                final SubLObject cdohash_table = id_index_sparse_objects(idx_$19);
                SubLObject id2 = NIL;
                SubLObject inference2 = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        id2 = getEntryKey(cdohash_entry);
                        inference2 = getEntryValue(cdohash_entry);
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference2)) {
                            final SubLObject inference_var2 = inference2;
                            final SubLObject set_var2 = inference_datastructures_inference.inference_strategy_set(inference_var2);
                            final SubLObject set_contents_var2 = set.do_set_internal(set_var2);
                            SubLObject basis_object2;
                            SubLObject state2;
                            SubLObject strategy2;
                            SubLObject cdolist_list_var2;
                            SubLObject sibling_tactic2;
                            for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2, state2); state2 = set_contents.do_set_contents_update_state(state2)) {
                                strategy2 = set_contents.do_set_contents_next(basis_object2, state2);
                                if (NIL != set_contents.do_set_contents_element_validP(state2, strategy2)) {
                                    inference_datastructures_strategy.set_problem_recompute_thrown_away(problem, strategy2);
                                    if (NIL != abductive_tactician.abductive_strategy_p(strategy2)) {
                                        cdolist_list_var2 = inference_datastructures_problem.problem_tactics(problem);
                                        sibling_tactic2 = NIL;
                                        sibling_tactic2 = cdolist_list_var2.first();
                                        while (NIL != cdolist_list_var2) {
                                            inference_datastructures_strategy.set_tactic_recompute_thrown_away(sibling_tactic2, strategy2);
                                            cdolist_list_var2 = cdolist_list_var2.rest();
                                            sibling_tactic2 = cdolist_list_var2.first();
                                        } 
                                    }
                                }
                            }
                        }
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
            }
        }
        return NIL;
    }

    public static final SubLObject new_transformation_tactic_alt(SubLObject problem, SubLObject hl_module, SubLObject productivity, SubLObject rule) {
        {
            SubLObject tactic = inference_datastructures_tactic.new_tactic(problem, hl_module, rule);
            SubLObject completeness = $GROSSLY_INCOMPLETE;
            inference_datastructures_tactic.set_tactic_productivity(tactic, productivity, UNPROVIDED);
            inference_datastructures_tactic.set_tactic_completeness(tactic, completeness);
            {
                SubLObject prob = problem;
                SubLObject store = inference_datastructures_problem.problem_store(prob);
                SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
                if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                    {
                        SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                        SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                        SubLObject inference = NIL;
                        while (NIL != id) {
                            inference = do_id_index_state_object(idx, $SKIP, id, state_var);
                            if (NIL != do_id_index_id_and_object_validP(id, inference, $SKIP)) {
                                if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(prob, inference)) {
                                    {
                                        SubLObject inference_var = inference;
                                        SubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));
                                        SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                                        SubLObject state = NIL;
                                        for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                            {
                                                SubLObject strategy = set_contents.do_set_contents_next(basis_object, state);
                                                if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                                    inference_tactician.strategy_note_new_tactic(strategy, tactic);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            id = do_id_index_next_id(idx, NIL, id, state_var);
                            state_var = do_id_index_next_state(idx, NIL, id, state_var);
                        } 
                    }
                }
            }
            return tactic;
        }
    }

    public static SubLObject new_transformation_tactic(final SubLObject problem, final SubLObject hl_module, final SubLObject productivity, final SubLObject rule) {
        final SubLObject tactic = inference_datastructures_tactic.new_tactic(problem, hl_module, rule);
        final SubLObject completeness = $GROSSLY_INCOMPLETE;
        inference_datastructures_tactic.set_tactic_productivity(tactic, productivity, UNPROVIDED);
        inference_datastructures_tactic.set_tactic_completeness(tactic, completeness);
        final SubLObject store = inference_datastructures_problem.problem_store(problem);
        final SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
        if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
            final SubLObject idx_$20 = idx;
            if (NIL == id_index_dense_objects_empty_p(idx_$20, $SKIP)) {
                final SubLObject vector_var = id_index_dense_objects(idx_$20);
                final SubLObject backwardP_var = NIL;
                SubLObject length;
                SubLObject v_iteration;
                SubLObject id;
                SubLObject inference;
                SubLObject inference_var;
                SubLObject set_var;
                SubLObject set_contents_var;
                SubLObject basis_object;
                SubLObject state;
                SubLObject strategy;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    inference = aref(vector_var, id);
                    if ((NIL == id_index_tombstone_p(inference)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                        if (NIL != id_index_tombstone_p(inference)) {
                            inference = $SKIP;
                        }
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference)) {
                            inference_var = inference;
                            set_var = inference_datastructures_inference.inference_strategy_set(inference_var);
                            set_contents_var = set.do_set_internal(set_var);
                            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                strategy = set_contents.do_set_contents_next(basis_object, state);
                                if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                    inference_tactician.strategy_note_new_tactic(strategy, tactic);
                                }
                            }
                        }
                    }
                }
            }
            final SubLObject idx_$21 = idx;
            if (NIL == id_index_sparse_objects_empty_p(idx_$21)) {
                final SubLObject cdohash_table = id_index_sparse_objects(idx_$21);
                SubLObject id2 = NIL;
                SubLObject inference2 = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        id2 = getEntryKey(cdohash_entry);
                        inference2 = getEntryValue(cdohash_entry);
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference2)) {
                            final SubLObject inference_var2 = inference2;
                            final SubLObject set_var2 = inference_datastructures_inference.inference_strategy_set(inference_var2);
                            final SubLObject set_contents_var2 = set.do_set_internal(set_var2);
                            SubLObject basis_object2;
                            SubLObject state2;
                            SubLObject strategy2;
                            for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2, state2); state2 = set_contents.do_set_contents_update_state(state2)) {
                                strategy2 = set_contents.do_set_contents_next(basis_object2, state2);
                                if (NIL != set_contents.do_set_contents_element_validP(state2, strategy2)) {
                                    inference_tactician.strategy_note_new_tactic(strategy2, tactic);
                                }
                            }
                        }
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
            }
        }
        return tactic;
    }

    public static final SubLObject compute_strategic_properties_of_transformation_tactic_alt(SubLObject tactic, SubLObject strategy) {
        return tactic;
    }

    public static SubLObject compute_strategic_properties_of_transformation_tactic(final SubLObject tactic, final SubLObject strategy) {
        return tactic;
    }

    public static final SubLObject with_transformation_tactic_execution_assumptions_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt58);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject tactic = NIL;
                    SubLObject mt = NIL;
                    SubLObject sense = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt58);
                    tactic = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt58);
                    mt = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt58);
                    sense = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            SubLObject tactic_var = $sym59$TACTIC_VAR;
                            return list(CLET, list(list(tactic_var, tactic)), list(WITH_INFERENCE_MT_RELEVANCE, mt, list(CLET, list(list($inference_expand_hl_module$, list(TACTIC_HL_MODULE, tactic_var)), list($inference_expand_sense$, sense)), listS(WITH_PROBLEM_STORE_TRANSFORMATION_ASSUMPTIONS, list(TACTIC_STORE, tactic_var), append(body, NIL)))));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt58);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject with_transformation_tactic_execution_assumptions(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list64);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject tactic = NIL;
        SubLObject mt = NIL;
        SubLObject sense = NIL;
        destructuring_bind_must_consp(current, datum, $list64);
        tactic = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list64);
        mt = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list64);
        sense = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            final SubLObject tactic_var = $sym65$TACTIC_VAR;
            return list(CLET, list(list(tactic_var, tactic)), list(WITH_INFERENCE_MT_RELEVANCE, mt, list(CLET, list(list($inference_expand_hl_module$, list(TACTIC_HL_MODULE, tactic_var)), list($inference_expand_sense$, sense)), listS(WITH_PROBLEM_STORE_TRANSFORMATION_ASSUMPTIONS, list(TACTIC_STORE, tactic_var), append(body, NIL)))));
        }
        cdestructuring_bind_error(datum, $list64);
        return NIL;
    }

    public static final SubLObject execute_literal_level_transformation_tactic_alt(SubLObject tactic, SubLObject mt, SubLObject asent, SubLObject sense) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject tactic_var = tactic;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    SubLObject _prev_bind_3 = backward.$inference_expand_hl_module$.currentBinding(thread);
                    SubLObject _prev_bind_4 = backward.$inference_expand_sense$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        backward.$inference_expand_hl_module$.bind(inference_datastructures_tactic.tactic_hl_module(tactic_var), thread);
                        backward.$inference_expand_sense$.bind(sense, thread);
                        {
                            SubLObject store_var = inference_datastructures_tactic.tactic_store(tactic_var);
                            {
                                SubLObject _prev_bind_0_4 = $hl_failure_backchaining$.currentBinding(thread);
                                SubLObject _prev_bind_1_5 = $unbound_rule_backchain_enabled$.currentBinding(thread);
                                SubLObject _prev_bind_2_6 = $evaluatable_backchain_enabled$.currentBinding(thread);
                                SubLObject _prev_bind_3_7 = $negation_by_failure$.currentBinding(thread);
                                try {
                                    $hl_failure_backchaining$.bind(T, thread);
                                    $unbound_rule_backchain_enabled$.bind(T, thread);
                                    $evaluatable_backchain_enabled$.bind(T, thread);
                                    $negation_by_failure$.bind(inference_datastructures_problem_store.problem_store_negation_by_failureP(store_var), thread);
                                    if (NIL != inference_datastructures_tactic.tactic_in_progressP(tactic)) {
                                        inference_datastructures_tactic.tactic_in_progress_next(tactic);
                                    } else {
                                        {
                                            SubLObject progress_iterator = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.maybe_make_transformation_tactic_progress_iterator(tactic, asent, sense);
                                            if (NIL == progress_iterator) {
                                            } else {
                                                if (progress_iterator.isList()) {
                                                    {
                                                        SubLObject cdolist_list_var = progress_iterator;
                                                        SubLObject rule = NIL;
                                                        for (rule = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , rule = cdolist_list_var.first()) {
                                                            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.handle_one_transformation_tactic_rule_select_result(tactic, rule);
                                                        }
                                                    }
                                                } else {
                                                    inference_datastructures_tactic.note_tactic_progress_iterator(tactic, progress_iterator);
                                                }
                                            }
                                        }
                                    }
                                } finally {
                                    $negation_by_failure$.rebind(_prev_bind_3_7, thread);
                                    $evaluatable_backchain_enabled$.rebind(_prev_bind_2_6, thread);
                                    $unbound_rule_backchain_enabled$.rebind(_prev_bind_1_5, thread);
                                    $hl_failure_backchaining$.rebind(_prev_bind_0_4, thread);
                                }
                            }
                        }
                    } finally {
                        backward.$inference_expand_sense$.rebind(_prev_bind_4, thread);
                        backward.$inference_expand_hl_module$.rebind(_prev_bind_3, thread);
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            probably_approximately_done.pad_note_transformation_tactic_executed(tactic);
            return tactic;
        }
    }

    public static SubLObject execute_literal_level_transformation_tactic(final SubLObject tactic, final SubLObject mt, final SubLObject asent, final SubLObject sense) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        final SubLObject _prev_bind_4 = backward.$inference_expand_hl_module$.currentBinding(thread);
        final SubLObject _prev_bind_5 = backward.$inference_expand_sense$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            backward.$inference_expand_hl_module$.bind(inference_datastructures_tactic.tactic_hl_module(tactic), thread);
            backward.$inference_expand_sense$.bind(sense, thread);
            final SubLObject store_var = inference_datastructures_tactic.tactic_store(tactic);
            final SubLObject _prev_bind_0_$22 = $hl_failure_backchaining$.currentBinding(thread);
            final SubLObject _prev_bind_1_$23 = $unbound_rule_backchain_enabled$.currentBinding(thread);
            final SubLObject _prev_bind_2_$24 = $evaluatable_backchain_enabled$.currentBinding(thread);
            final SubLObject _prev_bind_3_$25 = $negation_by_failure$.currentBinding(thread);
            try {
                $hl_failure_backchaining$.bind(T, thread);
                $unbound_rule_backchain_enabled$.bind(T, thread);
                $evaluatable_backchain_enabled$.bind(T, thread);
                $negation_by_failure$.bind(inference_datastructures_problem_store.problem_store_negation_by_failureP(store_var), thread);
                if (NIL != inference_datastructures_tactic.tactic_in_progressP(tactic)) {
                    inference_datastructures_tactic.tactic_in_progress_next(tactic);
                } else {
                    final SubLObject progress_iterator = maybe_make_transformation_tactic_progress_iterator(tactic, asent, sense);
                    if (NIL != progress_iterator) {
                        if (progress_iterator.isList()) {
                            SubLObject cdolist_list_var = progress_iterator;
                            SubLObject rule = NIL;
                            rule = cdolist_list_var.first();
                            while (NIL != cdolist_list_var) {
                                handle_one_transformation_tactic_rule_select_result(tactic, rule);
                                cdolist_list_var = cdolist_list_var.rest();
                                rule = cdolist_list_var.first();
                            } 
                        } else {
                            inference_datastructures_tactic.note_tactic_progress_iterator(tactic, progress_iterator);
                        }
                    }
                }
            } finally {
                $negation_by_failure$.rebind(_prev_bind_3_$25, thread);
                $evaluatable_backchain_enabled$.rebind(_prev_bind_2_$24, thread);
                $unbound_rule_backchain_enabled$.rebind(_prev_bind_1_$23, thread);
                $hl_failure_backchaining$.rebind(_prev_bind_0_$22, thread);
            }
        } finally {
            backward.$inference_expand_sense$.rebind(_prev_bind_5, thread);
            backward.$inference_expand_hl_module$.rebind(_prev_bind_4, thread);
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return tactic;
    }

    public static final SubLObject maybe_make_transformation_tactic_progress_iterator_alt(SubLObject tactic, SubLObject asent, SubLObject sense) {
        if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.meta_transformation_tactic_p(tactic)) {
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.maybe_make_meta_transformation_progress_iterator(tactic, asent, sense);
        } else {
            if (NIL == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_rule(tactic)) {
                return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.maybe_make_transformation_rule_select_progress_iterator(tactic, asent);
            } else {
                return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.maybe_make_transformation_expand_progress_iterator(tactic, asent);
            }
        }
    }

    public static SubLObject maybe_make_transformation_tactic_progress_iterator(final SubLObject tactic, final SubLObject asent, final SubLObject sense) {
        if (NIL != meta_transformation_tactic_p(tactic)) {
            return maybe_make_meta_transformation_progress_iterator(tactic, asent, sense);
        }
        if (NIL == transformation_tactic_rule(tactic)) {
            return maybe_make_transformation_rule_select_progress_iterator(tactic, asent);
        }
        return maybe_make_transformation_expand_progress_iterator(tactic, asent);
    }

    public static final SubLObject maybe_make_meta_transformation_progress_iterator_alt(SubLObject tactic, SubLObject asent, SubLObject sense) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject name = inference_datastructures_tactic.tactic_hl_module_name(tactic);
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (name != $DETERMINE_NEW_TRANSFORMATION_TACTICS) {
                        Errors.error($str_alt66$time_to_add_meta_transformation_s, name);
                    }
                }
            }
            {
                SubLObject progress_iterator = NIL;
                SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
                SubLObject tactic_specs = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.determine_literal_transformation_tactic_specs(asent, sense, NIL);
                SubLObject cdolist_list_var = tactic_specs;
                SubLObject tactic_spec = NIL;
                for (tactic_spec = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , tactic_spec = cdolist_list_var.first()) {
                    {
                        SubLObject datum = tactic_spec;
                        SubLObject current = datum;
                        SubLObject hl_module = NIL;
                        SubLObject productivity = NIL;
                        destructuring_bind_must_consp(current, datum, $list_alt48);
                        hl_module = current.first();
                        current = current.rest();
                        destructuring_bind_must_consp(current, datum, $list_alt48);
                        productivity = current.first();
                        current = current.rest();
                        if (NIL == current) {
                            com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_tactic(problem, hl_module, productivity, NIL);
                        } else {
                            cdestructuring_bind_error(datum, $list_alt48);
                        }
                    }
                }
                return progress_iterator;
            }
        }
    }

    public static SubLObject maybe_make_meta_transformation_progress_iterator(final SubLObject tactic, final SubLObject asent, final SubLObject sense) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject name = inference_datastructures_tactic.tactic_hl_module_name(tactic);
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (name != $DETERMINE_NEW_TRANSFORMATION_TACTICS)) {
            Errors.error($str72$time_to_add_meta_transformation_s, name);
        }
        final SubLObject progress_iterator = NIL;
        final SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
        SubLObject cdolist_list_var;
        final SubLObject tactic_specs = cdolist_list_var = determine_literal_transformation_tactic_specs(asent, sense, NIL);
        SubLObject tactic_spec = NIL;
        tactic_spec = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = tactic_spec;
            SubLObject hl_module = NIL;
            SubLObject productivity = NIL;
            destructuring_bind_must_consp(current, datum, $list56);
            hl_module = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list56);
            productivity = current.first();
            current = current.rest();
            if (NIL == current) {
                new_transformation_tactic(problem, hl_module, productivity, NIL);
            } else {
                cdestructuring_bind_error(datum, $list56);
            }
            cdolist_list_var = cdolist_list_var.rest();
            tactic_spec = cdolist_list_var.first();
        } 
        return progress_iterator;
    }

    public static final SubLObject maybe_make_transformation_rule_select_progress_iterator_alt(SubLObject tactic, SubLObject asent) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject rules = NIL;
                SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
                SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(tactic);
                rules = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.determine_rules_for_literal_transformation_tactics(problem, asent, hl_module);
                {
                    SubLObject old_productivity = inference_datastructures_tactic.tactic_productivity(tactic);
                    SubLObject new_productivity = inference_datastructures_enumerated_types.productivity_for_number_of_children(length(rules));
                    inference_datastructures_tactic.set_tactic_productivity(tactic, new_productivity, NIL);
                }
                if (NIL != list_utilities.lengthGE(rules, $transformation_tactic_iteration_threshold$.getDynamicValue(thread), UNPROVIDED)) {
                    rules = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_rule_select_progress_iterator(tactic, rules);
                }
                return rules;
            }
        }
    }

    public static SubLObject maybe_make_transformation_rule_select_progress_iterator(final SubLObject tactic, final SubLObject asent) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject rules = NIL;
        final SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
        final SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(tactic);
        rules = determine_rules_for_literal_transformation_tactics(problem, asent, hl_module);
        final SubLObject old_productivity = inference_datastructures_tactic.tactic_productivity(tactic);
        final SubLObject new_productivity = inference_datastructures_enumerated_types.productivity_for_number_of_children(length(rules));
        inference_datastructures_tactic.set_tactic_productivity(tactic, new_productivity, NIL);
        if (NIL != list_utilities.lengthGE(rules, $transformation_tactic_iteration_threshold$.getDynamicValue(thread), UNPROVIDED)) {
            rules = new_transformation_rule_select_progress_iterator(tactic, rules);
        }
        return rules;
    }

    public static final SubLObject new_transformation_rule_select_progress_iterator_alt(SubLObject tactic, SubLObject tactic_specs) {
        return inference_datastructures_tactic.new_tactic_progress_iterator($TRANSFORMATION_RULE_SELECT, tactic, tactic_specs);
    }

    public static SubLObject new_transformation_rule_select_progress_iterator(final SubLObject tactic, final SubLObject tactic_specs) {
        return inference_datastructures_tactic.new_tactic_progress_iterator($TRANSFORMATION_RULE_SELECT, tactic, tactic_specs);
    }

    public static final SubLObject handle_one_transformation_tactic_rule_select_result_alt(SubLObject transformation_tactic, SubLObject rule) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject existing_rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_rule(transformation_tactic);
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL != existing_rule) {
                        Errors.error($str_alt68$transformation_tactic__S_already_, transformation_tactic, existing_rule);
                    }
                }
            }
            {
                SubLObject problem = inference_datastructures_tactic.tactic_problem(transformation_tactic);
                SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(transformation_tactic);
                SubLObject productivity = inference_datastructures_enumerated_types.productivity_for_number_of_children(ONE_INTEGER);
                inference_datastructures_tactic.decrement_tactic_productivity_for_number_of_children(transformation_tactic, UNPROVIDED);
                return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_tactic(problem, hl_module, productivity, rule);
            }
        }
    }

    public static SubLObject handle_one_transformation_tactic_rule_select_result(final SubLObject transformation_tactic, final SubLObject rule) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject existing_rule = transformation_tactic_rule(transformation_tactic);
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL != existing_rule)) {
            Errors.error($str74$transformation_tactic__S_already_, transformation_tactic, existing_rule);
        }
        final SubLObject problem = inference_datastructures_tactic.tactic_problem(transformation_tactic);
        final SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(transformation_tactic);
        final SubLObject productivity = inference_datastructures_enumerated_types.productivity_for_number_of_children(ONE_INTEGER);
        inference_datastructures_tactic.decrement_tactic_productivity_for_number_of_children(transformation_tactic, UNPROVIDED);
        return new_transformation_tactic(problem, hl_module, productivity, rule);
    }

    public static final SubLObject maybe_make_transformation_expand_progress_iterator_alt(SubLObject tactic, SubLObject asent) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject progress_iterator = NIL;
                {
                    SubLObject _prev_bind_0 = backward.$transformation_add_node_method$.currentBinding(thread);
                    try {
                        backward.$transformation_add_node_method$.bind(HANDLE_TRANSFORMATION_ADD_NODE_FOR_EXPAND_RESULTS, thread);
                        {
                            SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(tactic);
                            SubLObject rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_tactic_rule(tactic);
                            SubLObject expand_method = hl_module_expand_func(hl_module);
                            if (expand_method.isFunctionSpec()) {
                                funcall(expand_method, asent, rule);
                            }
                        }
                    } finally {
                        backward.$transformation_add_node_method$.rebind(_prev_bind_0, thread);
                    }
                }
                return progress_iterator;
            }
        }
    }

    public static SubLObject maybe_make_transformation_expand_progress_iterator(final SubLObject tactic, final SubLObject asent) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject progress_iterator = NIL;
        final SubLObject _prev_bind_0 = backward.$transformation_add_node_method$.currentBinding(thread);
        try {
            backward.$transformation_add_node_method$.bind(HANDLE_TRANSFORMATION_ADD_NODE_FOR_EXPAND_RESULTS, thread);
            final SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(tactic);
            final SubLObject rule = transformation_tactic_rule(tactic);
            final SubLObject expand_method = inference_modules.hl_module_expand_func(hl_module);
            if (expand_method.isFunctionSpec()) {
                funcall(expand_method, asent, rule);
            }
        } finally {
            backward.$transformation_add_node_method$.rebind(_prev_bind_0, thread);
        }
        return progress_iterator;
    }

    /**
     *
     *
     * @param UNIFICATION-BINDINGS;
     * 		current tactic's problem vars -> something
     * @param UNIFICATION-DEPENDENT-DNF
     * 		the new transformed query
     */
    @LispMethod(comment = "@param UNIFICATION-BINDINGS;\r\n\t\tcurrent tactic\'s problem vars -> something\r\n@param UNIFICATION-DEPENDENT-DNF\r\n\t\tthe new transformed query")
    public static final SubLObject handle_transformation_add_node_for_expand_results_alt(SubLObject rule_assertion, SubLObject rule_pivot_asent, SubLObject rule_pivot_sense, SubLObject unification_bindings, SubLObject unification_dependent_dnf, SubLObject more_supports) {
        unification_bindings = inference_simplify_unification_bindings(unification_bindings);
        unification_bindings = possibly_optimize_bindings_wrt_equivalence(unification_bindings);
        {
            SubLObject tactic = inference_worker.currently_executing_tactic();
            SubLObject unification_explanatory_dnf = copy_clause(unification_dependent_dnf);
            if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_has_some_pragmatic_requirementP(rule_assertion, NIL)) {
                {
                    SubLObject pragmatic_requirements_dnf = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.backward_rule_pragmatic_dnf(rule_assertion, NIL);
                    unification_dependent_dnf = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.nmerge_dnf(unification_dependent_dnf, pragmatic_requirements_dnf);
                }
            }
            if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_worth_adding_type_constraintsP(rule_assertion)) {
                {
                    SubLObject type_constraint_dnf = inference_trampolines.inference_rule_type_constraints(rule_assertion);
                    unification_dependent_dnf = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.nmerge_dnf(unification_dependent_dnf, type_constraint_dnf);
                }
            }
            {
                SubLObject dont_care_constraints = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_additional_dont_care_constraints(rule_pivot_asent, unification_dependent_dnf, rule_assertion, unification_bindings);
                if (NIL != dont_care_constraints) {
                    {
                        SubLObject dont_care_dnf = clauses.make_dnf(NIL, dont_care_constraints);
                        unification_dependent_dnf = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.nmerge_dnf(unification_dependent_dnf, dont_care_dnf);
                    }
                }
            }
            {
                SubLObject unrestricted_transformation_dependent_dnf = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.unification_dependent_dnf_to_transformation_dependent_dnf(unification_dependent_dnf);
                SubLObject unrestricted_transformation_explanatory_dnf = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.unification_dependent_dnf_to_transformation_dependent_dnf(unification_explanatory_dnf);
                SubLObject transformation_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.unification_bindings_to_transformation_bindings(unification_bindings);
                return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.complete_execution_of_transformation_tactic(tactic, transformation_bindings, rule_assertion, more_supports, unrestricted_transformation_dependent_dnf, unrestricted_transformation_explanatory_dnf);
            }
        }
    }

    /**
     *
     *
     * @param UNIFICATION-BINDINGS;
     * 		current tactic's problem vars -> something
     * @param UNIFICATION-DEPENDENT-DNF
     * 		the new transformed query
     */
    @LispMethod(comment = "@param UNIFICATION-BINDINGS;\r\n\t\tcurrent tactic\'s problem vars -> something\r\n@param UNIFICATION-DEPENDENT-DNF\r\n\t\tthe new transformed query")
    public static SubLObject handle_transformation_add_node_for_expand_results(final SubLObject rule_assertion, final SubLObject rule_pivot_asent, final SubLObject rule_pivot_sense, SubLObject unification_bindings, SubLObject unification_dependent_dnf, final SubLObject more_supports) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        unification_bindings = bindings.inference_simplify_unification_bindings(unification_bindings);
        unification_bindings = bindings.possibly_optimize_bindings_wrt_equivalence(unification_bindings);
        if (NIL != $stitch_up_bindings_loopP$.getDynamicValue(thread)) {
            unification_bindings = bindings.possibly_stitch_up_bindings_loop(unification_bindings);
        }
        final SubLObject tactic = inference_worker.currently_executing_tactic();
        final SubLObject unification_explanatory_dnf = copy_clause(unification_dependent_dnf);
        if (NIL != rule_assertion_has_some_pragmatic_requirementP(rule_assertion, NIL)) {
            final SubLObject pragmatic_requirements_dnf = backward_rule_pragmatic_dnf(rule_assertion, NIL);
            unification_dependent_dnf = nmerge_dnf(unification_dependent_dnf, pragmatic_requirements_dnf);
        }
        if (NIL != rule_assertion_worth_adding_type_constraintsP(rule_assertion)) {
            final SubLObject type_constraint_dnf = inference_trampolines.inference_rule_type_constraints(rule_assertion);
            unification_dependent_dnf = nmerge_dnf(unification_dependent_dnf, type_constraint_dnf);
        }
        final SubLObject dont_care_constraints = transformation_additional_dont_care_constraints(rule_pivot_asent, unification_dependent_dnf, rule_assertion, unification_bindings);
        if (NIL != dont_care_constraints) {
            final SubLObject dont_care_dnf = clauses.make_dnf(NIL, dont_care_constraints);
            unification_dependent_dnf = nmerge_dnf(unification_dependent_dnf, dont_care_dnf);
        }
        final SubLObject unrestricted_transformation_dependent_dnf = unification_dependent_dnf_to_transformation_dependent_dnf(unification_dependent_dnf);
        final SubLObject unrestricted_transformation_explanatory_dnf = unification_dependent_dnf_to_transformation_dependent_dnf(unification_explanatory_dnf);
        final SubLObject transformation_bindings = unification_bindings_to_transformation_bindings(unification_bindings);
        return complete_execution_of_transformation_tactic(tactic, transformation_bindings, rule_assertion, more_supports, unrestricted_transformation_dependent_dnf, unrestricted_transformation_explanatory_dnf);
    }

    public static final SubLObject rule_assertion_worth_adding_type_constraintsP_alt(SubLObject rule) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == $inference_transformation_type_checking_enabledP$.getDynamicValue(thread)) {
                return NIL;
            }
            return T;
        }
    }

    public static SubLObject rule_assertion_worth_adding_type_constraintsP(final SubLObject rule) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == $inference_transformation_type_checking_enabledP$.getDynamicValue(thread)) {
            return NIL;
        }
        return T;
    }

    public static final SubLObject transformation_additional_dont_care_constraints_alt(SubLObject rule_pivot_asent, SubLObject unification_dependent_dnf, SubLObject rule_assertion, SubLObject unification_bindings) {
        {
            SubLObject source_var_pos_lits = backward_utilities.additional_source_variable_pos_lits(rule_pivot_asent, unification_dependent_dnf, rule_assertion);
            SubLObject dont_care_constraints = NIL;
            SubLObject cdolist_list_var = source_var_pos_lits;
            SubLObject source_var_pos_lit = NIL;
            for (source_var_pos_lit = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , source_var_pos_lit = cdolist_list_var.first()) {
                {
                    SubLObject substituted_pos_lit = apply_bindings(unification_bindings, source_var_pos_lit);
                    if (NIL == variables.fully_bound_p(substituted_pos_lit)) {
                        dont_care_constraints = cons(source_var_pos_lit, dont_care_constraints);
                    }
                }
            }
            return nreverse(dont_care_constraints);
        }
    }

    public static SubLObject transformation_additional_dont_care_constraints(final SubLObject rule_pivot_asent, final SubLObject unification_dependent_dnf, final SubLObject rule_assertion, final SubLObject unification_bindings) {
        final SubLObject source_var_pos_lits = backward_utilities.additional_source_variable_pos_lits(rule_pivot_asent, unification_dependent_dnf, rule_assertion);
        SubLObject dont_care_constraints = NIL;
        SubLObject cdolist_list_var = source_var_pos_lits;
        SubLObject source_var_pos_lit = NIL;
        source_var_pos_lit = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject substituted_pos_lit = bindings.apply_bindings(unification_bindings, source_var_pos_lit);
            if (NIL == variables.fully_bound_p(substituted_pos_lit)) {
                dont_care_constraints = cons(source_var_pos_lit, dont_care_constraints);
            }
            cdolist_list_var = cdolist_list_var.rest();
            source_var_pos_lit = cdolist_list_var.first();
        } 
        return nreverse(dont_care_constraints);
    }

    /**
     * Destructively modify EXISTING-DNF by merging ADDED-DNF into it.
     * Return the modified EXISTING-DNF.
     */
    @LispMethod(comment = "Destructively modify EXISTING-DNF by merging ADDED-DNF into it.\r\nReturn the modified EXISTING-DNF.\nDestructively modify EXISTING-DNF by merging ADDED-DNF into it.\nReturn the modified EXISTING-DNF.")
    public static final SubLObject nmerge_dnf_alt(SubLObject existing_dnf, SubLObject added_dnf) {
        return clauses.make_dnf(append(clauses.neg_lits(existing_dnf), clauses.neg_lits(added_dnf)), append(clauses.pos_lits(existing_dnf), clauses.pos_lits(added_dnf)));
    }

    /**
     * Destructively modify EXISTING-DNF by merging ADDED-DNF into it.
     * Return the modified EXISTING-DNF.
     */
    @LispMethod(comment = "Destructively modify EXISTING-DNF by merging ADDED-DNF into it.\r\nReturn the modified EXISTING-DNF.\nDestructively modify EXISTING-DNF by merging ADDED-DNF into it.\nReturn the modified EXISTING-DNF.")
    public static SubLObject nmerge_dnf(final SubLObject existing_dnf, final SubLObject added_dnf) {
        return clauses.make_dnf(append(clauses.neg_lits(existing_dnf), clauses.neg_lits(added_dnf)), append(clauses.pos_lits(existing_dnf), clauses.pos_lits(added_dnf)));
    }

    public static final SubLObject merge_dnf_alt(SubLObject dnf1, SubLObject dnf2) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.nmerge_dnf(copy_clause(dnf1), dnf2);
    }

    public static SubLObject merge_dnf(final SubLObject dnf1, final SubLObject dnf2) {
        return nmerge_dnf(copy_clause(dnf1), dnf2);
    }

    public static final SubLObject complete_execution_of_transformation_tactic_alt(SubLObject tactic, SubLObject transformation_bindings, SubLObject rule_assertion, SubLObject more_supports, SubLObject unrestricted_transformation_dependent_dnf, SubLObject unrestricted_transformation_explanatory_dnf) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject supported_problem = inference_datastructures_tactic.tactic_problem(tactic);
                SubLObject store = inference_datastructures_problem.problem_store(supported_problem);
                SubLObject restricted_transformation_dependent_dnf = apply_bindings(transformation_bindings, unrestricted_transformation_dependent_dnf);
                SubLObject supporting_mapped_problem = NIL;
                if (NIL == clauses.empty_clauseP(restricted_transformation_dependent_dnf)) {
                    {
                        SubLObject dependent_query = inference_czer.dnf_and_mt_to_hl_query(restricted_transformation_dependent_dnf, mt_relevance_macros.$mt$.getDynamicValue(thread));
                        SubLObject abduction_allowedP = inference_datastructures_problem_store.problem_store_abduction_allowedP(inference_datastructures_problem.problem_store(inference_datastructures_tactic.tactic_problem(tactic)));
                        if (NIL == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.potentially_wf_transformation_dependent_query(dependent_query, abduction_allowedP)) {
                            return NIL;
                        }
                        supporting_mapped_problem = inference_worker.find_or_create_problem(store, dependent_query, UNPROVIDED);
                    }
                }
                {
                    SubLObject non_explanatory_subquery = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.compute_transformation_non_explanatory_subquery(unrestricted_transformation_dependent_dnf, unrestricted_transformation_explanatory_dnf, restricted_transformation_dependent_dnf, transformation_bindings, supporting_mapped_problem);
                    return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.maybe_new_transformation_link(supported_problem, supporting_mapped_problem, tactic, transformation_bindings, rule_assertion, more_supports, non_explanatory_subquery);
                }
            }
        }
    }

    public static SubLObject complete_execution_of_transformation_tactic(final SubLObject tactic, final SubLObject transformation_bindings, final SubLObject rule_assertion, final SubLObject more_supports, final SubLObject unrestricted_transformation_dependent_dnf, final SubLObject unrestricted_transformation_explanatory_dnf) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject supported_problem = inference_datastructures_tactic.tactic_problem(tactic);
        final SubLObject store = inference_datastructures_problem.problem_store(supported_problem);
        final SubLObject restricted_transformation_dependent_dnf = bindings.apply_bindings(transformation_bindings, unrestricted_transformation_dependent_dnf);
        SubLObject supporting_mapped_problem = NIL;
        if (NIL == clauses.empty_clauseP(restricted_transformation_dependent_dnf)) {
            final SubLObject dependent_query = inference_czer.dnf_and_mt_to_hl_query(restricted_transformation_dependent_dnf, mt_relevance_macros.$mt$.getDynamicValue(thread));
            final SubLObject abduction_allowedP = inference_datastructures_problem_store.problem_store_abduction_allowedP(inference_datastructures_problem.problem_store(inference_datastructures_tactic.tactic_problem(tactic)));
            if (NIL == potentially_wf_transformation_dependent_query(dependent_query, abduction_allowedP)) {
                return NIL;
            }
            final SubLObject dependent_query_free_hl_vars = variables.sort_hl_variable_list_memoized(intersection(cycl_utilities.expression_gather(dependent_query, $sym76$HL_VAR_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), cycl_utilities.expression_gather(bindings.apply_bindings(transformation_bindings, inference_datastructures_problem.problem_free_hl_vars(inference_datastructures_tactic.tactic_problem(tactic))), $sym76$HL_VAR_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED));
            supporting_mapped_problem = inference_worker.find_or_create_problem(store, dependent_query, dependent_query_free_hl_vars, UNPROVIDED);
        }
        final SubLObject non_explanatory_subquery = compute_transformation_non_explanatory_subquery(unrestricted_transformation_dependent_dnf, unrestricted_transformation_explanatory_dnf, restricted_transformation_dependent_dnf, transformation_bindings, supporting_mapped_problem);
        return maybe_new_transformation_link(supported_problem, supporting_mapped_problem, tactic, transformation_bindings, rule_assertion, more_supports, non_explanatory_subquery);
    }

    public static final SubLObject compute_transformation_non_explanatory_subquery_alt(SubLObject unrestricted_transformation_dependent_dnf, SubLObject unrestricted_transformation_explanatory_dnf, SubLObject restricted_transformation_dependent_dnf, SubLObject transformation_bindings, SubLObject supporting_mapped_problem) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (unrestricted_transformation_dependent_dnf.equal(unrestricted_transformation_explanatory_dnf)) {
                return NIL;
            }
            {
                SubLObject non_explanatory_dnf = clause_utilities.clause_difference(unrestricted_transformation_dependent_dnf, unrestricted_transformation_explanatory_dnf);
                SubLObject restricted_non_explanatory_dnf = apply_bindings(transformation_bindings, non_explanatory_dnf);
                SubLObject non_explanatory_query = inference_czer.dnf_and_mt_to_hl_query(restricted_non_explanatory_dnf, mt_relevance_macros.$mt$.getDynamicValue(thread));
                SubLObject non_explanatory_subquery = apply_bindings_backwards(inference_datastructures_problem_link.mapped_problem_variable_map(supporting_mapped_problem), non_explanatory_query);
                return non_explanatory_subquery;
            }
        }
    }

    public static SubLObject compute_transformation_non_explanatory_subquery(final SubLObject unrestricted_transformation_dependent_dnf, final SubLObject unrestricted_transformation_explanatory_dnf, final SubLObject restricted_transformation_dependent_dnf, final SubLObject transformation_bindings, final SubLObject supporting_mapped_problem) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (unrestricted_transformation_dependent_dnf.equal(unrestricted_transformation_explanatory_dnf)) {
            return NIL;
        }
        final SubLObject non_explanatory_dnf = clause_utilities.clause_difference(unrestricted_transformation_dependent_dnf, unrestricted_transformation_explanatory_dnf);
        final SubLObject restricted_non_explanatory_dnf = bindings.apply_bindings(transformation_bindings, non_explanatory_dnf);
        final SubLObject non_explanatory_query = inference_czer.dnf_and_mt_to_hl_query(restricted_non_explanatory_dnf, mt_relevance_macros.$mt$.getDynamicValue(thread));
        final SubLObject non_explanatory_subquery = bindings.apply_bindings_backwards(inference_datastructures_problem_link.mapped_problem_variable_map(supporting_mapped_problem), non_explanatory_query);
        return non_explanatory_subquery;
    }

    public static final SubLObject potentially_wf_transformation_dependent_query_alt(SubLObject dependent_query, SubLObject abduction_allowedP) {
        {
            SubLObject cdolist_list_var = dependent_query;
            SubLObject contextualized_dnf = NIL;
            for (contextualized_dnf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , contextualized_dnf = cdolist_list_var.first()) {
                {
                    SubLObject index_var = ZERO_INTEGER;
                    SubLObject cdolist_list_var_8 = clauses.neg_lits(contextualized_dnf);
                    SubLObject contextualized_asent = NIL;
                    for (contextualized_asent = cdolist_list_var_8.first(); NIL != cdolist_list_var_8; cdolist_list_var_8 = cdolist_list_var_8.rest() , contextualized_asent = cdolist_list_var_8.first()) {
                        {
                            SubLObject sense = $NEG;
                            if (NIL == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.potentially_wf_restricted_transformation_dependent_asent(contextualized_asent, sense, abduction_allowedP)) {
                                return NIL;
                            }
                        }
                        index_var = add(index_var, ONE_INTEGER);
                    }
                }
                {
                    SubLObject index_var = ZERO_INTEGER;
                    SubLObject cdolist_list_var_9 = clauses.pos_lits(contextualized_dnf);
                    SubLObject contextualized_asent = NIL;
                    for (contextualized_asent = cdolist_list_var_9.first(); NIL != cdolist_list_var_9; cdolist_list_var_9 = cdolist_list_var_9.rest() , contextualized_asent = cdolist_list_var_9.first()) {
                        {
                            SubLObject sense = $POS;
                            if (NIL == com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.potentially_wf_restricted_transformation_dependent_asent(contextualized_asent, sense, abduction_allowedP)) {
                                return NIL;
                            }
                        }
                        index_var = add(index_var, ONE_INTEGER);
                    }
                }
            }
        }
        return T;
    }

    public static SubLObject potentially_wf_transformation_dependent_query(final SubLObject dependent_query, final SubLObject abduction_allowedP) {
        SubLObject cdolist_list_var = dependent_query;
        SubLObject contextualized_dnf = NIL;
        contextualized_dnf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject index_var = ZERO_INTEGER;
            SubLObject cdolist_list_var_$26 = clauses.neg_lits(contextualized_dnf);
            SubLObject contextualized_asent = NIL;
            contextualized_asent = cdolist_list_var_$26.first();
            while (NIL != cdolist_list_var_$26) {
                final SubLObject sense = $NEG;
                if (NIL == potentially_wf_restricted_transformation_dependent_asent(contextualized_asent, sense, abduction_allowedP)) {
                    return NIL;
                }
                index_var = add(index_var, ONE_INTEGER);
                cdolist_list_var_$26 = cdolist_list_var_$26.rest();
                contextualized_asent = cdolist_list_var_$26.first();
            } 
            index_var = ZERO_INTEGER;
            SubLObject cdolist_list_var_$27 = clauses.pos_lits(contextualized_dnf);
            contextualized_asent = NIL;
            contextualized_asent = cdolist_list_var_$27.first();
            while (NIL != cdolist_list_var_$27) {
                final SubLObject sense = $POS;
                if (NIL == potentially_wf_restricted_transformation_dependent_asent(contextualized_asent, sense, abduction_allowedP)) {
                    return NIL;
                }
                index_var = add(index_var, ONE_INTEGER);
                cdolist_list_var_$27 = cdolist_list_var_$27.rest();
                contextualized_asent = cdolist_list_var_$27.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            contextualized_dnf = cdolist_list_var.first();
        } 
        return T;
    }

    public static final SubLObject potentially_wf_restricted_transformation_dependent_asent_alt(SubLObject contextualized_asent, SubLObject sense, SubLObject abduction_allowedP) {
        {
            SubLObject datum = contextualized_asent;
            SubLObject current = datum;
            SubLObject mt = NIL;
            SubLObject asent = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt72);
            mt = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt72);
            asent = current.first();
            current = current.rest();
            if (NIL == current) {
                return makeBoolean((NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.syntactically_valid_asent(asent)) && ((!((NIL == abduction_allowedP) && (NIL != cycl_utilities.atomic_sentence_with_pred_p(asent, $$termOfUnit)))) || (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.syntactically_valid_contextualized_term_of_unit_asent(asent, sense))));
            } else {
                cdestructuring_bind_error(datum, $list_alt72);
            }
        }
        return NIL;
    }

    public static SubLObject potentially_wf_restricted_transformation_dependent_asent(final SubLObject contextualized_asent, final SubLObject sense, final SubLObject abduction_allowedP) {
        SubLObject mt = NIL;
        SubLObject asent = NIL;
        destructuring_bind_must_consp(contextualized_asent, contextualized_asent, $list79);
        mt = contextualized_asent.first();
        SubLObject current = contextualized_asent.rest();
        destructuring_bind_must_consp(current, contextualized_asent, $list79);
        asent = current.first();
        current = current.rest();
        if (NIL == current) {
            return makeBoolean((NIL != syntactically_valid_asent(asent)) && (((NIL != abduction_allowedP) || (NIL == cycl_utilities.atomic_sentence_with_pred_p(asent, $$termOfUnit))) || (NIL != syntactically_valid_contextualized_term_of_unit_asent(asent, sense))));
        }
        cdestructuring_bind_error(contextualized_asent, $list79);
        return NIL;
    }

    public static final SubLObject syntactically_valid_asent_alt(SubLObject asent) {
        return cycl_grammar.cycl_atomic_sentence_p(asent);
    }

    public static SubLObject syntactically_valid_asent(final SubLObject asent) {
        return cycl_grammar.cycl_atomic_sentence_p(asent);
    }

    public static final SubLObject syntactically_valid_contextualized_term_of_unit_asent_alt(SubLObject asent, SubLObject sense) {
        return makeBoolean(($POS == sense) && (NIL != backward.syntactically_valid_term_of_unit_asent(asent)));
    }

    public static SubLObject syntactically_valid_contextualized_term_of_unit_asent(final SubLObject asent, final SubLObject sense) {
        return makeBoolean(($POS == sense) && (NIL != backward.syntactically_valid_term_of_unit_asent(asent)));
    }

    /**
     *
     *
     * @param SUBPROOF
     * 		nil or proof-p
     * @param VARIABLE-MAP;
     * 		TRANSFORMATION-LINK's supporting problem -> TRANSFORMATION-LINK's extended supported problem
     * @return 0 proof-p
     * @return 1 whether the returned proof was newly created
     * @unknown see the unit test :heinous-unification-backchain for an example walkthrough of
    the bindings processing of this function.
     */
    @LispMethod(comment = "@param SUBPROOF\r\n\t\tnil or proof-p\r\n@param VARIABLE-MAP;\r\n\t\tTRANSFORMATION-LINK\'s supporting problem -> TRANSFORMATION-LINK\'s extended supported problem\r\n@return 0 proof-p\r\n@return 1 whether the returned proof was newly created\r\n@unknown see the unit test :heinous-unification-backchain for an example walkthrough of\r\nthe bindings processing of this function.")
    public static final SubLObject new_transformation_proof_alt(SubLObject transformation_link, SubLObject subproof, SubLObject variable_map) {
        SubLTrampolineFile.checkType(transformation_link, TRANSFORMATION_LINK_P);
        if (NIL != subproof) {
            SubLTrampolineFile.checkType(subproof, PROOF_P);
        }
        {
            SubLObject transformation_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_bindings(transformation_link);
            SubLObject supporting_subproof_bindings = (NIL != subproof) ? ((SubLObject) (inference_datastructures_proof.proof_bindings(subproof))) : NIL;
            SubLObject subproofs = (NIL != subproof) ? ((SubLObject) (list(subproof))) : NIL;
            SubLObject canonical_proof_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.compute_canonical_transformation_proof_bindings(variable_map, transformation_bindings, supporting_subproof_bindings);
            return inference_worker.propose_new_proof_with_bindings(transformation_link, canonical_proof_bindings, subproofs);
        }
    }

    /**
     *
     *
     * @param SUBPROOF
     * 		nil or proof-p
     * @param VARIABLE-MAP;
     * 		TRANSFORMATION-LINK's supporting problem -> TRANSFORMATION-LINK's extended supported problem
     * @return 0 proof-p
     * @return 1 whether the returned proof was newly created
     * @unknown see the unit test :heinous-unification-backchain for an example walkthrough of
    the bindings processing of this function.
     */
    @LispMethod(comment = "@param SUBPROOF\r\n\t\tnil or proof-p\r\n@param VARIABLE-MAP;\r\n\t\tTRANSFORMATION-LINK\'s supporting problem -> TRANSFORMATION-LINK\'s extended supported problem\r\n@return 0 proof-p\r\n@return 1 whether the returned proof was newly created\r\n@unknown see the unit test :heinous-unification-backchain for an example walkthrough of\r\nthe bindings processing of this function.")
    public static SubLObject new_transformation_proof(final SubLObject transformation_link, final SubLObject subproof, final SubLObject variable_map) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        if (((NIL != subproof) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == inference_datastructures_proof.proof_p(subproof))) {
            throw new AssertionError(subproof);
        }
        final SubLObject transformation_bindings = transformation_link_bindings(transformation_link);
        final SubLObject supporting_subproof_bindings = (NIL != subproof) ? inference_datastructures_proof.proof_bindings(subproof) : NIL;
        final SubLObject subproofs = (NIL != subproof) ? list(subproof) : NIL;
        final SubLObject canonical_proof_bindings = compute_canonical_transformation_proof_bindings(variable_map, transformation_bindings, supporting_subproof_bindings);
        return inference_worker.propose_new_proof_with_bindings(transformation_link, canonical_proof_bindings, subproofs);
    }

    /**
     *
     *
     * @param T-LINK-VARIABLE-MAP;
     * 		TRANSFORMATION-LINK's supporting problem -> TRANSFORMATION-LINK's extended supported problem
     * @param TRANSFORMATION-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem vars -> extended supported problem vars or new contents
     * @param SUPPORTING-SUBPROOF-BINDINGS;
     * 		TRANSFORMATION-LINK's supporting problem vars -> old contents
     */
    @LispMethod(comment = "@param T-LINK-VARIABLE-MAP;\r\n\t\tTRANSFORMATION-LINK\'s supporting problem -> TRANSFORMATION-LINK\'s extended supported problem\r\n@param TRANSFORMATION-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem vars -> extended supported problem vars or new contents\r\n@param SUPPORTING-SUBPROOF-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s supporting problem vars -> old contents")
    public static final SubLObject compute_canonical_transformation_proof_bindings_alt(SubLObject t_link_variable_map, SubLObject transformation_bindings, SubLObject supporting_subproof_bindings) {
        {
            SubLObject subproof_bindings = transfer_variable_map_to_bindings_filtered(t_link_variable_map, supporting_subproof_bindings);
            SubLObject final_combined_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.unify_transformation_and_subproof_bindings(transformation_bindings, subproof_bindings);
            SubLObject proof_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.extended_supported_problem_bindings_to_supported_problem_bindings(final_combined_bindings);
            SubLObject canonical_proof_bindings = inference_worker.canonicalize_proof_bindings(proof_bindings);
            return canonical_proof_bindings;
        }
    }

    /**
     *
     *
     * @param T-LINK-VARIABLE-MAP;
     * 		TRANSFORMATION-LINK's supporting problem -> TRANSFORMATION-LINK's extended supported problem
     * @param TRANSFORMATION-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem vars -> extended supported problem vars or new contents
     * @param SUPPORTING-SUBPROOF-BINDINGS;
     * 		TRANSFORMATION-LINK's supporting problem vars -> old contents
     */
    @LispMethod(comment = "@param T-LINK-VARIABLE-MAP;\r\n\t\tTRANSFORMATION-LINK\'s supporting problem -> TRANSFORMATION-LINK\'s extended supported problem\r\n@param TRANSFORMATION-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem vars -> extended supported problem vars or new contents\r\n@param SUPPORTING-SUBPROOF-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s supporting problem vars -> old contents")
    public static SubLObject compute_canonical_transformation_proof_bindings(final SubLObject t_link_variable_map, final SubLObject transformation_bindings, final SubLObject supporting_subproof_bindings) {
        final SubLObject subproof_bindings = bindings.transfer_variable_map_to_bindings_filtered(t_link_variable_map, supporting_subproof_bindings);
        final SubLObject final_combined_bindings = unify_transformation_and_subproof_bindings(transformation_bindings, subproof_bindings);
        final SubLObject proof_bindings = extended_supported_problem_bindings_to_supported_problem_bindings(final_combined_bindings);
        final SubLObject canonical_proof_bindings = inference_worker.canonicalize_proof_bindings(proof_bindings);
        return canonical_proof_bindings;
    }

    public static final SubLObject unification_dependent_dnf_to_transformation_dependent_dnf_alt(SubLObject unification_dependent_dnf) {
        return unification.variable_base_inversion(unification_dependent_dnf);
    }

    public static SubLObject unification_dependent_dnf_to_transformation_dependent_dnf(final SubLObject unification_dependent_dnf) {
        return unification.variable_base_inversion(unification_dependent_dnf);
    }

    /**
     *
     *
     * @param UNIFICATION-BINDINGS;
     * 		the bindings returned by @xref transformation-add-node.
     * 		UNIFICATION-BINDINGS has the base variables (0-99) being the variables of the support (the rule),
     * 		and the non-base vars (100-199) being the variables of the supported problem.
     * 		This swaps the base and non-base variables.
     * 		It also does a little bit of bindings simplification.
     */
    @LispMethod(comment = "@param UNIFICATION-BINDINGS;\r\n\t\tthe bindings returned by @xref transformation-add-node.\r\n\t\tUNIFICATION-BINDINGS has the base variables (0-99) being the variables of the support (the rule),\r\n\t\tand the non-base vars (100-199) being the variables of the supported problem.\r\n\t\tThis swaps the base and non-base variables.\r\n\t\tIt also does a little bit of bindings simplification.")
    public static final SubLObject unification_bindings_to_transformation_bindings_alt(SubLObject unification_bindings) {
        {
            SubLObject swapped_unification_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.swap_variable_spaces_of_unification_bindings(unification_bindings);
            SubLObject bindings_to_closed = bindings_to_closed(swapped_unification_bindings);
            if (NIL != bindings_to_closed) {
                {
                    SubLObject transformation_bindings = NIL;
                    SubLObject cdolist_list_var = swapped_unification_bindings;
                    SubLObject binding = NIL;
                    for (binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , binding = cdolist_list_var.first()) {
                        {
                            SubLObject old_value = variable_binding_value(binding);
                            SubLObject new_value = apply_bindings(bindings_to_closed, old_value);
                            transformation_bindings = cons(make_variable_binding(variable_binding_variable(binding), new_value), transformation_bindings);
                        }
                    }
                    swapped_unification_bindings = nreverse(transformation_bindings);
                }
            }
            return swapped_unification_bindings;
        }
    }

    /**
     *
     *
     * @param UNIFICATION-BINDINGS;
     * 		the bindings returned by @xref transformation-add-node.
     * 		UNIFICATION-BINDINGS has the base variables (0-99) being the variables of the support (the rule),
     * 		and the non-base vars (100-199) being the variables of the supported problem.
     * 		This swaps the base and non-base variables.
     * 		It also does a little bit of bindings simplification.
     */
    @LispMethod(comment = "@param UNIFICATION-BINDINGS;\r\n\t\tthe bindings returned by @xref transformation-add-node.\r\n\t\tUNIFICATION-BINDINGS has the base variables (0-99) being the variables of the support (the rule),\r\n\t\tand the non-base vars (100-199) being the variables of the supported problem.\r\n\t\tThis swaps the base and non-base variables.\r\n\t\tIt also does a little bit of bindings simplification.")
    public static SubLObject unification_bindings_to_transformation_bindings(final SubLObject unification_bindings) {
        SubLObject swapped_unification_bindings = swap_variable_spaces_of_unification_bindings(unification_bindings);
        final SubLObject bindings_to_closed = bindings.bindings_to_closed(swapped_unification_bindings);
        if (NIL != bindings_to_closed) {
            SubLObject transformation_bindings = NIL;
            SubLObject cdolist_list_var = swapped_unification_bindings;
            SubLObject binding = NIL;
            binding = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject old_value = bindings.variable_binding_value(binding);
                final SubLObject new_value = bindings.apply_bindings(bindings_to_closed, old_value);
                transformation_bindings = cons(bindings.make_variable_binding(bindings.variable_binding_variable(binding), new_value), transformation_bindings);
                cdolist_list_var = cdolist_list_var.rest();
                binding = cdolist_list_var.first();
            } 
            swapped_unification_bindings = nreverse(transformation_bindings);
        }
        return swapped_unification_bindings;
    }

    /**
     * Adds or subtracts 100 from all variables in UNIFICATION-BINDINGS.
     * This is tied with the assumptions inside the transformation modules about how they
     * call transformation-asent-unify.
     */
    @LispMethod(comment = "Adds or subtracts 100 from all variables in UNIFICATION-BINDINGS.\r\nThis is tied with the assumptions inside the transformation modules about how they\r\ncall transformation-asent-unify.\nAdds or subtracts 100 from all variables in UNIFICATION-BINDINGS.\nThis is tied with the assumptions inside the transformation modules about how they\ncall transformation-asent-unify.")
    public static final SubLObject swap_variable_spaces_of_unification_bindings_alt(SubLObject unification_bindings) {
        return unification.variable_base_inversion(unification_bindings);
    }

    /**
     * Adds or subtracts 100 from all variables in UNIFICATION-BINDINGS.
     * This is tied with the assumptions inside the transformation modules about how they
     * call transformation-asent-unify.
     */
    @LispMethod(comment = "Adds or subtracts 100 from all variables in UNIFICATION-BINDINGS.\r\nThis is tied with the assumptions inside the transformation modules about how they\r\ncall transformation-asent-unify.\nAdds or subtracts 100 from all variables in UNIFICATION-BINDINGS.\nThis is tied with the assumptions inside the transformation modules about how they\ncall transformation-asent-unify.")
    public static SubLObject swap_variable_spaces_of_unification_bindings(final SubLObject unification_bindings) {
        return unification.variable_base_inversion(unification_bindings);
    }

    /**
     *
     *
     * @return bindings-p; TRANSFORMATION-LINK's rule assertion vars -> contents
    i.e. the variables in the TRANSFORMATION-LINK's rule assertion that were bound by SUBPROOF
     */
    @LispMethod(comment = "@return bindings-p; TRANSFORMATION-LINK\'s rule assertion vars -> contents\r\ni.e. the variables in the TRANSFORMATION-LINK\'s rule assertion that were bound by SUBPROOF")
    public static final SubLObject transformation_proof_rule_bindings_alt(SubLObject transformation_proof) {
        SubLTrampolineFile.checkType(transformation_proof, TRANSFORMATION_PROOF_P);
        {
            SubLObject transformation_link = inference_datastructures_proof.proof_link(transformation_proof);
            SubLObject subproof = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_subproof(transformation_proof);
            SubLObject supporting_subproof_bindings = (NIL != subproof) ? ((SubLObject) (inference_datastructures_proof.proof_bindings(subproof))) : NIL;
            SubLObject rule_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.compute_transformation_link_rule_bindings(transformation_link, supporting_subproof_bindings);
            return rule_bindings;
        }
    }

    /**
     *
     *
     * @return bindings-p; TRANSFORMATION-LINK's rule assertion vars -> contents
    i.e. the variables in the TRANSFORMATION-LINK's rule assertion that were bound by SUBPROOF
     */
    @LispMethod(comment = "@return bindings-p; TRANSFORMATION-LINK\'s rule assertion vars -> contents\r\ni.e. the variables in the TRANSFORMATION-LINK\'s rule assertion that were bound by SUBPROOF")
    public static SubLObject transformation_proof_rule_bindings(final SubLObject transformation_proof) {
        assert NIL != transformation_proof_p(transformation_proof) : "! inference_worker_transformation.transformation_proof_p(transformation_proof) " + ("inference_worker_transformation.transformation_proof_p(transformation_proof) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_proof_p(transformation_proof) ") + transformation_proof;
        final SubLObject transformation_link = inference_datastructures_proof.proof_link(transformation_proof);
        final SubLObject subproof = transformation_proof_subproof(transformation_proof);
        final SubLObject supporting_subproof_bindings = (NIL != subproof) ? inference_datastructures_proof.proof_bindings(subproof) : NIL;
        final SubLObject rule_bindings = compute_transformation_link_rule_bindings(transformation_link, supporting_subproof_bindings);
        return rule_bindings;
    }

    public static final SubLObject compute_transformation_link_rule_bindings_alt(SubLObject transformation_link, SubLObject supporting_subproof_bindings) {
        SubLTrampolineFile.checkType(transformation_link, TRANSFORMATION_LINK_P);
        SubLTrampolineFile.checkType(supporting_subproof_bindings, BINDINGS_P);
        {
            SubLObject t_link_variable_map = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_supporting_variable_map(transformation_link);
            SubLObject transformation_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_bindings(transformation_link);
            SubLObject subproof_bindings = transfer_variable_map_to_bindings_filtered(t_link_variable_map, supporting_subproof_bindings);
            SubLObject final_combined_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.unify_transformation_and_subproof_bindings(transformation_bindings, subproof_bindings);
            SubLObject rule_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.extended_supported_problem_bindings_to_rule_bindings(final_combined_bindings);
            return rule_bindings;
        }
    }

    public static SubLObject compute_transformation_link_rule_bindings(final SubLObject transformation_link, final SubLObject supporting_subproof_bindings) {
        assert NIL != transformation_link_p(transformation_link) : "! inference_worker_transformation.transformation_link_p(transformation_link) " + ("inference_worker_transformation.transformation_link_p(transformation_link) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_link_p(transformation_link) ") + transformation_link;
        assert NIL != bindings.bindings_p(supporting_subproof_bindings) : "! bindings.bindings_p(supporting_subproof_bindings) " + ("bindings.bindings_p(supporting_subproof_bindings) " + "CommonSymbols.NIL != bindings.bindings_p(supporting_subproof_bindings) ") + supporting_subproof_bindings;
        final SubLObject t_link_variable_map = transformation_link_supporting_variable_map(transformation_link);
        final SubLObject transformation_bindings = transformation_link_bindings(transformation_link);
        final SubLObject subproof_bindings = bindings.transfer_variable_map_to_bindings_filtered(t_link_variable_map, supporting_subproof_bindings);
        final SubLObject final_combined_bindings = unify_transformation_and_subproof_bindings(transformation_bindings, subproof_bindings);
        final SubLObject rule_bindings = extended_supported_problem_bindings_to_rule_bindings(final_combined_bindings);
        return rule_bindings;
    }

    /**
     *
     *
     * @return bindings-p; TRANSFORMATION-LINK's rule assertion EL vars -> contents
    i.e. the free EL variables in the TRANSFORMATION-LINK's rule assertion that were bound by SUBPROOF
     */
    @LispMethod(comment = "@return bindings-p; TRANSFORMATION-LINK\'s rule assertion EL vars -> contents\r\ni.e. the free EL variables in the TRANSFORMATION-LINK\'s rule assertion that were bound by SUBPROOF")
    public static final SubLObject transformation_proof_rule_el_bindings_alt(SubLObject transformation_proof) {
        SubLTrampolineFile.checkType(transformation_proof, TRANSFORMATION_PROOF_P);
        {
            SubLObject rule_assertion = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_rule_assertion(transformation_proof);
            SubLObject variable_map = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_variable_map(rule_assertion);
            SubLObject rule_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_rule_bindings(transformation_proof);
            SubLObject rule_el_bindings = transfer_variable_map_to_bindings_backwards(variable_map, rule_bindings);
            return rule_el_bindings;
        }
    }

    /**
     *
     *
     * @return bindings-p; TRANSFORMATION-LINK's rule assertion EL vars -> contents
    i.e. the free EL variables in the TRANSFORMATION-LINK's rule assertion that were bound by SUBPROOF
     */
    @LispMethod(comment = "@return bindings-p; TRANSFORMATION-LINK\'s rule assertion EL vars -> contents\r\ni.e. the free EL variables in the TRANSFORMATION-LINK\'s rule assertion that were bound by SUBPROOF")
    public static SubLObject transformation_proof_rule_el_bindings(final SubLObject transformation_proof) {
        assert NIL != transformation_proof_p(transformation_proof) : "! inference_worker_transformation.transformation_proof_p(transformation_proof) " + ("inference_worker_transformation.transformation_proof_p(transformation_proof) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_proof_p(transformation_proof) ") + transformation_proof;
        final SubLObject rule_assertion = transformation_proof_rule_assertion(transformation_proof);
        final SubLObject variable_map = rule_assertion_variable_map(rule_assertion);
        final SubLObject rule_bindings = transformation_proof_rule_bindings(transformation_proof);
        final SubLObject rule_el_bindings = bindings.transfer_variable_map_to_bindings_backwards(variable_map, rule_bindings);
        return rule_el_bindings;
    }

    /**
     *
     *
     * @return BINDINGS-P; the EL bindings (using the variable names
    from the transformation rule) proven by TRANSFORMATION-PROOF.
     * @unknown baxter
     */
    @LispMethod(comment = "@return BINDINGS-P; the EL bindings (using the variable names\r\nfrom the transformation rule) proven by TRANSFORMATION-PROOF.\r\n@unknown baxter")
    public static final SubLObject transformation_proof_el_bindings_alt(SubLObject transformation_proof) {
        {
            SubLObject proof_bindings = inference_datastructures_proof.proof_bindings(transformation_proof);
            SubLObject transformation_link = inference_datastructures_proof.proof_link(transformation_proof);
            SubLObject transformation_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_bindings(transformation_link);
            SubLObject hl_bindings = apply_bindings(transformation_bindings, proof_bindings);
            SubLObject rule_assertion = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_rule_assertion(transformation_proof);
            SubLObject rule_variable_map = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_variable_map(rule_assertion);
            return transfer_variable_map_to_bindings_backwards(rule_variable_map, hl_bindings);
        }
    }

    /**
     *
     *
     * @return BINDINGS-P; the EL bindings (using the variable names
    from the transformation rule) proven by TRANSFORMATION-PROOF.
     * @unknown baxter
     */
    @LispMethod(comment = "@return BINDINGS-P; the EL bindings (using the variable names\r\nfrom the transformation rule) proven by TRANSFORMATION-PROOF.\r\n@unknown baxter")
    public static SubLObject transformation_proof_el_bindings(final SubLObject transformation_proof) {
        final SubLObject proof_bindings = inference_datastructures_proof.proof_bindings(transformation_proof);
        final SubLObject transformation_link = inference_datastructures_proof.proof_link(transformation_proof);
        final SubLObject transformation_bindings = transformation_link_bindings(transformation_link);
        final SubLObject hl_bindings = bindings.apply_bindings(transformation_bindings, proof_bindings);
        final SubLObject rule_assertion = transformation_proof_rule_assertion(transformation_proof);
        final SubLObject rule_variable_map = rule_assertion_variable_map(rule_assertion);
        return bindings.transfer_variable_map_to_bindings_backwards(rule_variable_map, hl_bindings);
    }

    /**
     *
     *
     * @param TRANSFORMATION-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem vars -> extended supported problem vars or new contents
     * @param SUBPROOF-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem vars -> old contents
     * 		This function recursively reduces all loops and dependencies between TRANSFORMATION-BINDINGS and SUBPROOF-BINDINGS
     * 		until all bindings have fully-bound values.
     */
    @LispMethod(comment = "@param TRANSFORMATION-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem vars -> extended supported problem vars or new contents\r\n@param SUBPROOF-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem vars -> old contents\r\n\t\tThis function recursively reduces all loops and dependencies between TRANSFORMATION-BINDINGS and SUBPROOF-BINDINGS\r\n\t\tuntil all bindings have fully-bound values.")
    public static final SubLObject unify_transformation_and_subproof_bindings_alt(SubLObject transformation_bindings, SubLObject subproof_bindings) {
        {
            SubLObject combined_bindings = append(subproof_bindings, transformation_bindings);
            if (NIL != inference_worker.all_bindings_ground_outP(combined_bindings)) {
                return combined_bindings;
            }
            {
                SubLObject new_unified_bindings = inference_worker.unify_all_equal_bindings(combined_bindings);
                SubLObject recombined_bindings = append(new_unified_bindings, combined_bindings);
                SubLObject final_bindings = NIL;
                SubLObject working_bindings = NIL;
                SubLObject cdolist_list_var = recombined_bindings;
                SubLObject binding = NIL;
                for (binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , binding = cdolist_list_var.first()) {
                    if (NIL != inference_worker.binding_ground_outP(binding)) {
                        final_bindings = cons(binding, final_bindings);
                    } else {
                        working_bindings = cons(binding, working_bindings);
                    }
                }
                final_bindings = nreverse(final_bindings);
                working_bindings = nreverse(working_bindings);
                if (NIL == final_bindings) {
                    Errors.error($str_alt76$Could_not_ground_out__s_and__s, transformation_bindings, subproof_bindings);
                }
                {
                    SubLObject substituted_bindings = apply_bindings_to_values(final_bindings, working_bindings);
                    if (transformation_bindings.equal(substituted_bindings) && subproof_bindings.equal(final_bindings)) {
                        Errors.error($str_alt77$Could_not_unify_transformation_bi, transformation_bindings, subproof_bindings);
                    }
                    return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.unify_transformation_and_subproof_bindings(substituted_bindings, final_bindings);
                }
            }
        }
    }

    /**
     *
     *
     * @param TRANSFORMATION-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem vars -> extended supported problem vars or new contents
     * @param SUBPROOF-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem vars -> old contents
     * 		This function recursively reduces all loops and dependencies between TRANSFORMATION-BINDINGS and SUBPROOF-BINDINGS
     * 		until all bindings have fully-bound values.
     */
    @LispMethod(comment = "@param TRANSFORMATION-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem vars -> extended supported problem vars or new contents\r\n@param SUBPROOF-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem vars -> old contents\r\n\t\tThis function recursively reduces all loops and dependencies between TRANSFORMATION-BINDINGS and SUBPROOF-BINDINGS\r\n\t\tuntil all bindings have fully-bound values.")
    public static SubLObject unify_transformation_and_subproof_bindings(final SubLObject transformation_bindings, final SubLObject subproof_bindings) {
        final SubLObject combined_bindings = append(subproof_bindings, transformation_bindings);
        if (NIL != inference_worker.all_bindings_ground_outP(combined_bindings)) {
            return combined_bindings;
        }
        final SubLObject new_unified_bindings = inference_worker.unify_all_equal_bindings(combined_bindings);
        final SubLObject recombined_bindings = append(new_unified_bindings, combined_bindings);
        SubLObject final_bindings = NIL;
        SubLObject working_bindings = NIL;
        SubLObject cdolist_list_var = recombined_bindings;
        SubLObject binding = NIL;
        binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != inference_worker.binding_ground_outP(binding)) {
                final_bindings = cons(binding, final_bindings);
            } else {
                working_bindings = cons(binding, working_bindings);
            }
            cdolist_list_var = cdolist_list_var.rest();
            binding = cdolist_list_var.first();
        } 
        final_bindings = nreverse(final_bindings);
        working_bindings = nreverse(working_bindings);
        if (NIL == final_bindings) {
            Errors.error($str83$Could_not_ground_out__s_and__s, transformation_bindings, subproof_bindings);
        }
        final SubLObject substituted_bindings = bindings.apply_bindings_to_values(final_bindings, working_bindings);
        if (transformation_bindings.equal(substituted_bindings) && subproof_bindings.equal(final_bindings)) {
            Errors.error($str84$Could_not_unify_transformation_bi, transformation_bindings, subproof_bindings);
        }
        return unify_transformation_and_subproof_bindings(substituted_bindings, final_bindings);
    }

    /**
     * Extended supported problem bindings include both base and non-base variables;
     * the base variables are the variables of the supported problem and the non-base
     * variables are the variables of the rule assertion.  This function filters out
     * the non-base variables, leaving only the bindings whose variables are in the space
     * of the supported problem.  In other words:
     *
     * @param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem bindings -> content
     * @return SUPPORTED-PROBLEM-BINDINGS; TRANSFORMATION-LINK's supported problem bindings -> content
     */
    @LispMethod(comment = "Extended supported problem bindings include both base and non-base variables;\r\nthe base variables are the variables of the supported problem and the non-base\r\nvariables are the variables of the rule assertion.  This function filters out\r\nthe non-base variables, leaving only the bindings whose variables are in the space\r\nof the supported problem.  In other words:\r\n\r\n@param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem bindings -> content\r\n@return SUPPORTED-PROBLEM-BINDINGS; TRANSFORMATION-LINK\'s supported problem bindings -> content\nExtended supported problem bindings include both base and non-base variables;\nthe base variables are the variables of the supported problem and the non-base\nvariables are the variables of the rule assertion.  This function filters out\nthe non-base variables, leaving only the bindings whose variables are in the space\nof the supported problem.  In other words:")
    public static final SubLObject extended_supported_problem_bindings_to_supported_problem_bindings_alt(SubLObject extended_supported_problem_bindings) {
        {
            SubLObject supported_problem_bindings = NIL;
            SubLObject cdolist_list_var = extended_supported_problem_bindings;
            SubLObject binding = NIL;
            for (binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , binding = cdolist_list_var.first()) {
                {
                    SubLObject variable = variable_binding_variable(binding);
                    if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.supported_problem_variable_p(variable)) {
                        supported_problem_bindings = cons(binding, supported_problem_bindings);
                    }
                }
            }
            return nreverse(supported_problem_bindings);
        }
    }

    /**
     * Extended supported problem bindings include both base and non-base variables;
     * the base variables are the variables of the supported problem and the non-base
     * variables are the variables of the rule assertion.  This function filters out
     * the non-base variables, leaving only the bindings whose variables are in the space
     * of the supported problem.  In other words:
     *
     * @param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem bindings -> content
     * @return SUPPORTED-PROBLEM-BINDINGS; TRANSFORMATION-LINK's supported problem bindings -> content
     */
    @LispMethod(comment = "Extended supported problem bindings include both base and non-base variables;\r\nthe base variables are the variables of the supported problem and the non-base\r\nvariables are the variables of the rule assertion.  This function filters out\r\nthe non-base variables, leaving only the bindings whose variables are in the space\r\nof the supported problem.  In other words:\r\n\r\n@param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem bindings -> content\r\n@return SUPPORTED-PROBLEM-BINDINGS; TRANSFORMATION-LINK\'s supported problem bindings -> content\nExtended supported problem bindings include both base and non-base variables;\nthe base variables are the variables of the supported problem and the non-base\nvariables are the variables of the rule assertion.  This function filters out\nthe non-base variables, leaving only the bindings whose variables are in the space\nof the supported problem.  In other words:")
    public static SubLObject extended_supported_problem_bindings_to_supported_problem_bindings(final SubLObject extended_supported_problem_bindings) {
        SubLObject supported_problem_bindings = NIL;
        SubLObject cdolist_list_var = extended_supported_problem_bindings;
        SubLObject binding = NIL;
        binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject variable = bindings.variable_binding_variable(binding);
            if (NIL != supported_problem_variable_p(variable)) {
                supported_problem_bindings = cons(binding, supported_problem_bindings);
            }
            cdolist_list_var = cdolist_list_var.rest();
            binding = cdolist_list_var.first();
        } 
        return nreverse(supported_problem_bindings);
    }

    public static final SubLObject supported_problem_variable_p_alt(SubLObject variable) {
        return unification.base_variable_p(variable);
    }

    public static SubLObject supported_problem_variable_p(final SubLObject variable) {
        return unification.base_variable_p(variable);
    }

    /**
     * Extended supported problem bindings include both base and non-base variables;
     * the base variables are the variables of the supported problem and the non-base
     * variables are the variables of the rule assertion.  This function filters out
     * the base variables, leaving only the bindings whose variables are in the space
     * of the rule assertion.  In other words:
     *
     * @param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem bindings -> content
     * @return RULE-BINDINGS; TRANSFORMATION-LINK's rule assertion bindings -> content
     */
    @LispMethod(comment = "Extended supported problem bindings include both base and non-base variables;\r\nthe base variables are the variables of the supported problem and the non-base\r\nvariables are the variables of the rule assertion.  This function filters out\r\nthe base variables, leaving only the bindings whose variables are in the space\r\nof the rule assertion.  In other words:\r\n\r\n@param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem bindings -> content\r\n@return RULE-BINDINGS; TRANSFORMATION-LINK\'s rule assertion bindings -> content\nExtended supported problem bindings include both base and non-base variables;\nthe base variables are the variables of the supported problem and the non-base\nvariables are the variables of the rule assertion.  This function filters out\nthe base variables, leaving only the bindings whose variables are in the space\nof the rule assertion.  In other words:")
    public static final SubLObject extended_supported_problem_bindings_to_rule_bindings_alt(SubLObject extended_supported_problem_bindings) {
        {
            SubLObject rule_bindings = NIL;
            SubLObject cdolist_list_var = extended_supported_problem_bindings;
            SubLObject binding = NIL;
            for (binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , binding = cdolist_list_var.first()) {
                {
                    SubLObject variable = variable_binding_variable(binding);
                    if (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_variable_p(variable)) {
                        rule_bindings = cons(binding, rule_bindings);
                    }
                }
            }
            return nreverse(rule_bindings);
        }
    }

    /**
     * Extended supported problem bindings include both base and non-base variables;
     * the base variables are the variables of the supported problem and the non-base
     * variables are the variables of the rule assertion.  This function filters out
     * the base variables, leaving only the bindings whose variables are in the space
     * of the rule assertion.  In other words:
     *
     * @param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;
     * 		TRANSFORMATION-LINK's extended supported problem bindings -> content
     * @return RULE-BINDINGS; TRANSFORMATION-LINK's rule assertion bindings -> content
     */
    @LispMethod(comment = "Extended supported problem bindings include both base and non-base variables;\r\nthe base variables are the variables of the supported problem and the non-base\r\nvariables are the variables of the rule assertion.  This function filters out\r\nthe base variables, leaving only the bindings whose variables are in the space\r\nof the rule assertion.  In other words:\r\n\r\n@param EXTENDED-SUPPORTED-PROBLEM-BINDINGS;\r\n\t\tTRANSFORMATION-LINK\'s extended supported problem bindings -> content\r\n@return RULE-BINDINGS; TRANSFORMATION-LINK\'s rule assertion bindings -> content\nExtended supported problem bindings include both base and non-base variables;\nthe base variables are the variables of the supported problem and the non-base\nvariables are the variables of the rule assertion.  This function filters out\nthe base variables, leaving only the bindings whose variables are in the space\nof the rule assertion.  In other words:")
    public static SubLObject extended_supported_problem_bindings_to_rule_bindings(final SubLObject extended_supported_problem_bindings) {
        SubLObject rule_bindings = NIL;
        SubLObject cdolist_list_var = extended_supported_problem_bindings;
        SubLObject binding = NIL;
        binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject variable = bindings.variable_binding_variable(binding);
            if (NIL != rule_assertion_variable_p(variable)) {
                rule_bindings = cons(binding, rule_bindings);
            }
            cdolist_list_var = cdolist_list_var.rest();
            binding = cdolist_list_var.first();
        } 
        return nreverse(rule_bindings);
    }

    public static final SubLObject rule_assertion_variable_p_alt(SubLObject variable) {
        return unification.non_base_variable_p(variable);
    }

    public static SubLObject rule_assertion_variable_p(final SubLObject variable) {
        return unification.non_base_variable_p(variable);
    }

    /**
     * Returns a variable-map of the form EL-VARIABLE -> HL-VARIABLE for the variables in RULE-ASSERTION.
     * The HL-VARIABLE is in the space of the transformation link's extended supported problem.
     */
    @LispMethod(comment = "Returns a variable-map of the form EL-VARIABLE -> HL-VARIABLE for the variables in RULE-ASSERTION.\r\nThe HL-VARIABLE is in the space of the transformation link\'s extended supported problem.\nReturns a variable-map of the form EL-VARIABLE -> HL-VARIABLE for the variables in RULE-ASSERTION.\nThe HL-VARIABLE is in the space of the transformation link\'s extended supported problem.")
    public static final SubLObject rule_assertion_variable_map_alt(SubLObject rule_assertion) {
        SubLTrampolineFile.checkType(rule_assertion, $sym78$RULE_ASSERTION_);
        {
            SubLObject variable_names = assertions_high.assertion_variable_names(rule_assertion);
            SubLObject variable_map = NIL;
            SubLObject list_var = NIL;
            SubLObject variable_name = NIL;
            SubLObject variable_number = NIL;
            for (list_var = variable_names, variable_name = list_var.first(), variable_number = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , variable_name = list_var.first() , variable_number = add(ONE_INTEGER, variable_number)) {
                {
                    SubLObject hl_variable = unification.variable_non_base_version(variables.find_variable_by_id(variable_number));
                    SubLObject el_variable = make_el_var(variable_name);
                    variable_map = add_variable_binding(el_variable, hl_variable, variable_map);
                }
            }
            variable_map = nreverse(variable_map);
            return variable_map;
        }
    }

    @LispMethod(comment = "Returns a variable-map of the form EL-VARIABLE -> HL-VARIABLE for the variables in RULE-ASSERTION.\r\nThe HL-VARIABLE is in the space of the transformation link\'s extended supported problem.\nReturns a variable-map of the form EL-VARIABLE -> HL-VARIABLE for the variables in RULE-ASSERTION.\nThe HL-VARIABLE is in the space of the transformation link\'s extended supported problem.")
    public static SubLObject rule_assertion_variable_map(final SubLObject rule_assertion) {
        assert NIL != assertions_high.rule_assertionP(rule_assertion) : "! assertions_high.rule_assertionP(rule_assertion) " + ("assertions_high.rule_assertionP(rule_assertion) " + "CommonSymbols.NIL != assertions_high.rule_assertionP(rule_assertion) ") + rule_assertion;
        final SubLObject variable_names = assertions_high.assertion_variable_names(rule_assertion);
        SubLObject variable_map = NIL;
        SubLObject list_var = NIL;
        SubLObject variable_name = NIL;
        SubLObject variable_number = NIL;
        list_var = variable_names;
        variable_name = list_var.first();
        for (variable_number = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , variable_name = list_var.first() , variable_number = add(ONE_INTEGER, variable_number)) {
            final SubLObject hl_variable = unification.variable_non_base_version(variables.find_variable_by_id(variable_number));
            final SubLObject el_variable = cycl_variables.make_el_var(variable_name);
            variable_map = bindings.add_variable_binding(el_variable, hl_variable, variable_map);
        }
        variable_map = nreverse(variable_map);
        return variable_map;
    }

    public static SubLObject rule_assertion_base_variable_map(final SubLObject rule_assertion) {
        assert NIL != assertions_high.rule_assertionP(rule_assertion) : "! assertions_high.rule_assertionP(rule_assertion) " + ("assertions_high.rule_assertionP(rule_assertion) " + "CommonSymbols.NIL != assertions_high.rule_assertionP(rule_assertion) ") + rule_assertion;
        final SubLObject variable_names = assertions_high.assertion_variable_names(rule_assertion);
        SubLObject variable_map = NIL;
        SubLObject list_var = NIL;
        SubLObject variable_name = NIL;
        SubLObject variable_number = NIL;
        list_var = variable_names;
        variable_name = list_var.first();
        for (variable_number = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , variable_name = list_var.first() , variable_number = add(ONE_INTEGER, variable_number)) {
            final SubLObject hl_variable = variables.find_variable_by_id(variable_number);
            final SubLObject el_variable = cycl_variables.make_el_var(variable_name);
            variable_map = bindings.add_variable_binding(el_variable, hl_variable, variable_map);
        }
        variable_map = nreverse(variable_map);
        return variable_map;
    }

    /**
     * Return T iff RULE-ASSERTION has some relevant #$pragmaticRequirement in MT
     */
    @LispMethod(comment = "Return T iff RULE-ASSERTION has some relevant #$pragmaticRequirement in MT")
    public static final SubLObject rule_assertion_has_some_pragmatic_requirementP_alt(SubLObject rule_assertion, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(rule_assertion, $sym78$RULE_ASSERTION_);
            {
                SubLObject v_answer = NIL;
                SubLObject mt_var = mt;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
                        v_answer = plusp(kb_indexing.relevant_num_pragma_rule_index(rule_assertion));
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return v_answer;
            }
        }
    }

    /**
     * Return T iff RULE-ASSERTION has some relevant #$pragmaticRequirement in MT
     */
    @LispMethod(comment = "Return T iff RULE-ASSERTION has some relevant #$pragmaticRequirement in MT")
    public static SubLObject rule_assertion_has_some_pragmatic_requirementP(final SubLObject rule_assertion, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != assertions_high.rule_assertionP(rule_assertion) : "! assertions_high.rule_assertionP(rule_assertion) " + ("assertions_high.rule_assertionP(rule_assertion) " + "CommonSymbols.NIL != assertions_high.rule_assertionP(rule_assertion) ") + rule_assertion;
        SubLObject v_answer = NIL;
        final SubLObject mt_var = mt;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
            v_answer = plusp(kb_indexing.relevant_num_pragma_rule_index(rule_assertion));
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return v_answer;
    }

    /**
     * Like @xref rule-assertion-pragmatic-requirements-dnf, but filters out forward-inference-specific literals.
     */
    @LispMethod(comment = "Like @xref rule-assertion-pragmatic-requirements-dnf, but filters out forward-inference-specific literals.")
    public static final SubLObject backward_rule_pragmatic_dnf_alt(SubLObject rule_assertion, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return forward.filter_forward_pragmatic_dnf(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_pragmatic_requirements_dnf(rule_assertion, mt));
    }

    @LispMethod(comment = "Like @xref rule-assertion-pragmatic-requirements-dnf, but filters out forward-inference-specific literals.")
    public static SubLObject backward_rule_pragmatic_dnf(final SubLObject rule_assertion, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return forward.filter_forward_pragmatic_dnf(rule_assertion_pragmatic_requirements_dnf(rule_assertion, mt));
    }

    public static final SubLObject forward_rule_pragmatic_dnf_alt(SubLObject rule, SubLObject propagation_mt) {
        {
            SubLObject pragmatics_mt = ($$InferencePSC.equal(propagation_mt)) ? ((SubLObject) (assertions_high.assertion_mt(rule))) : propagation_mt;
            SubLObject pragmatic_dnf = ((NIL != $forward_pragmatic_requirement_enabledP$.getGlobalValue()) && (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_has_some_pragmatic_requirementP(rule, pragmatics_mt))) ? ((SubLObject) (com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.rule_assertion_pragmatic_requirements_dnf(rule, pragmatics_mt))) : clauses.empty_clause();
            return pragmatic_dnf;
        }
    }

    public static SubLObject forward_rule_pragmatic_dnf(final SubLObject rule, final SubLObject propagation_mt) {
        final SubLObject pragmatics_mt = ($$InferencePSC.equal(propagation_mt)) ? assertions_high.assertion_mt(rule) : propagation_mt;
        final SubLObject pragmatic_dnf = (NIL != rule_assertion_has_some_pragmatic_requirementP(rule, pragmatics_mt)) ? rule_assertion_pragmatic_requirements_dnf(rule, pragmatics_mt) : clauses.empty_clause();
        return pragmatic_dnf;
    }

    /**
     * Return a DNF clause expressing all the known #$pragmaticRequirements for RULE-ASSERTION in MT
     */
    @LispMethod(comment = "Return a DNF clause expressing all the known #$pragmaticRequirements for RULE-ASSERTION in MT")
    public static final SubLObject rule_assertion_pragmatic_requirements_dnf_alt(SubLObject rule_assertion, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(rule_assertion, ASSERTION_P);
            {
                SubLObject dnf = clauses.make_dnf(NIL, NIL);
                SubLObject mt_var = mt;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
                        if (NIL != kb_mapping_macros.do_pragma_rule_index_key_validator(rule_assertion, NIL)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_pragma_rule_final_index_spec_iterator(rule_assertion, NIL);
                                SubLObject done_var = NIL;
                                SubLObject token_var = NIL;
                                while (NIL == done_var) {
                                    {
                                        SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                                        SubLObject valid = makeBoolean(token_var != final_index_spec);
                                        if (NIL != valid) {
                                            {
                                                SubLObject final_index_iterator = NIL;
                                                try {
                                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $RULE, NIL, NIL);
                                                    {
                                                        SubLObject done_var_10 = NIL;
                                                        SubLObject token_var_11 = NIL;
                                                        while (NIL == done_var_10) {
                                                            {
                                                                SubLObject pragma_assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_11);
                                                                SubLObject valid_12 = makeBoolean(token_var_11 != pragma_assertion);
                                                                if (NIL != valid_12) {
                                                                    dnf = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.merge_pragmatic_requirement(rule_assertion, pragma_assertion, dnf);
                                                                }
                                                                done_var_10 = makeBoolean(NIL == valid_12);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0_13 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                        try {
                                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_13, thread);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        done_var = makeBoolean(NIL == valid);
                                    }
                                } 
                            }
                        }
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return dnf;
            }
        }
    }

    @LispMethod(comment = "Return a DNF clause expressing all the known #$pragmaticRequirements for RULE-ASSERTION in MT")
    public static SubLObject rule_assertion_pragmatic_requirements_dnf(final SubLObject rule_assertion, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != assertion_handles.assertion_p(rule_assertion) : "! assertion_handles.assertion_p(rule_assertion) " + ("assertion_handles.assertion_p(rule_assertion) " + "CommonSymbols.NIL != assertion_handles.assertion_p(rule_assertion) ") + rule_assertion;
        SubLObject dnf = clauses.make_dnf(NIL, NIL);
        final SubLObject mt_var = mt;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
            if (NIL != kb_mapping_macros.do_pragma_rule_index_key_validator(rule_assertion, NIL)) {
                final SubLObject iterator_var = kb_mapping_macros.new_pragma_rule_final_index_spec_iterator(rule_assertion, NIL);
                SubLObject done_var = NIL;
                final SubLObject token_var = NIL;
                while (NIL == done_var) {
                    final SubLObject final_index_spec = iteration_next_without_values_macro_helper(iterator_var, token_var);
                    final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                    if (NIL != valid) {
                        SubLObject final_index_iterator = NIL;
                        try {
                            final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $RULE, NIL, NIL);
                            SubLObject done_var_$28 = NIL;
                            final SubLObject token_var_$29 = NIL;
                            while (NIL == done_var_$28) {
                                final SubLObject pragma_assertion = iteration_next_without_values_macro_helper(final_index_iterator, token_var_$29);
                                final SubLObject valid_$30 = makeBoolean(!token_var_$29.eql(pragma_assertion));
                                if (NIL != valid_$30) {
                                    dnf = merge_pragmatic_requirement(rule_assertion, pragma_assertion, dnf);
                                }
                                done_var_$28 = makeBoolean(NIL == valid_$30);
                            } 
                        } finally {
                            final SubLObject _prev_bind_0_$31 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                final SubLObject _values = getValuesAsVector();
                                if (NIL != final_index_iterator) {
                                    kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                }
                                restoreValuesFromVector(_values);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$31, thread);
                            }
                        }
                    }
                    done_var = makeBoolean(NIL == valid);
                } 
            }
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return dnf;
    }

    public static SubLObject forward_rule_non_trigger_literals(final SubLObject rule_assertion, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != assertion_handles.assertion_p(rule_assertion) : "! assertion_handles.assertion_p(rule_assertion) " + ("assertion_handles.assertion_p(rule_assertion) " + "CommonSymbols.NIL != assertion_handles.assertion_p(rule_assertion) ") + rule_assertion;
        SubLObject result = NIL;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt), thread);
            if (NIL != kb_mapping_macros.do_pragma_rule_index_key_validator(rule_assertion, NIL)) {
                final SubLObject iterator_var = kb_mapping_macros.new_pragma_rule_final_index_spec_iterator(rule_assertion, NIL);
                SubLObject done_var = NIL;
                final SubLObject token_var = NIL;
                while (NIL == done_var) {
                    final SubLObject final_index_spec = iteration_next_without_values_macro_helper(iterator_var, token_var);
                    final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                    if (NIL != valid) {
                        SubLObject final_index_iterator = NIL;
                        try {
                            final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $RULE, NIL, NIL);
                            SubLObject done_var_$32 = NIL;
                            final SubLObject token_var_$33 = NIL;
                            while (NIL == done_var_$32) {
                                final SubLObject pragma_assertion = iteration_next_without_values_macro_helper(final_index_iterator, token_var_$33);
                                final SubLObject valid_$34 = makeBoolean(!token_var_$33.eql(pragma_assertion));
                                if (NIL != valid_$34) {
                                    final SubLObject neg_lits = clauses.neg_lits(assertions_high.assertion_cnf(pragma_assertion));
                                    if (NIL != list_utilities.singletonP(neg_lits)) {
                                        final SubLObject lit = neg_lits.first();
                                        if (NIL != el_formula_with_operator_p(lit, $$forwardNonTriggerLiteral)) {
                                            final SubLObject item_var = literal_arg1(lit, UNPROVIDED);
                                            if (NIL == member(item_var, result, symbol_function(EQL), symbol_function(IDENTITY))) {
                                                result = cons(item_var, result);
                                            }
                                        }
                                    }
                                }
                                done_var_$32 = makeBoolean(NIL == valid_$34);
                            } 
                        } finally {
                            final SubLObject _prev_bind_0_$35 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                final SubLObject _values = getValuesAsVector();
                                if (NIL != final_index_iterator) {
                                    kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                }
                                restoreValuesFromVector(_values);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$35, thread);
                            }
                        }
                    }
                    done_var = makeBoolean(NIL == valid);
                } 
            }
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static SubLObject forward_rule_trigger_literals(final SubLObject rule_assertion, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != assertion_handles.assertion_p(rule_assertion) : "! assertion_handles.assertion_p(rule_assertion) " + ("assertion_handles.assertion_p(rule_assertion) " + "CommonSymbols.NIL != assertion_handles.assertion_p(rule_assertion) ") + rule_assertion;
        SubLObject result = NIL;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt), thread);
            if (NIL != kb_mapping_macros.do_pragma_rule_index_key_validator(rule_assertion, NIL)) {
                final SubLObject iterator_var = kb_mapping_macros.new_pragma_rule_final_index_spec_iterator(rule_assertion, NIL);
                SubLObject done_var = NIL;
                final SubLObject token_var = NIL;
                while (NIL == done_var) {
                    final SubLObject final_index_spec = iteration_next_without_values_macro_helper(iterator_var, token_var);
                    final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                    if (NIL != valid) {
                        SubLObject final_index_iterator = NIL;
                        try {
                            final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $RULE, NIL, NIL);
                            SubLObject done_var_$36 = NIL;
                            final SubLObject token_var_$37 = NIL;
                            while (NIL == done_var_$36) {
                                final SubLObject pragma_assertion = iteration_next_without_values_macro_helper(final_index_iterator, token_var_$37);
                                final SubLObject valid_$38 = makeBoolean(!token_var_$37.eql(pragma_assertion));
                                if (NIL != valid_$38) {
                                    final SubLObject neg_lits = clauses.neg_lits(assertions_high.assertion_cnf(pragma_assertion));
                                    if (NIL != list_utilities.singletonP(neg_lits)) {
                                        final SubLObject lit = neg_lits.first();
                                        if (NIL != el_formula_with_operator_p(lit, $$forwardTriggerLiteral)) {
                                            final SubLObject item_var = literal_arg1(lit, UNPROVIDED);
                                            if (NIL == member(item_var, result, symbol_function(EQL), symbol_function(IDENTITY))) {
                                                result = cons(item_var, result);
                                            }
                                        }
                                    }
                                }
                                done_var_$36 = makeBoolean(NIL == valid_$38);
                            } 
                        } finally {
                            final SubLObject _prev_bind_0_$39 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                final SubLObject _values = getValuesAsVector();
                                if (NIL != final_index_iterator) {
                                    kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                }
                                restoreValuesFromVector(_values);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$39, thread);
                            }
                        }
                    }
                    done_var = makeBoolean(NIL == valid);
                } 
            }
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    /**
     * Merge the pragmatic requirements for RULE-ASSERTION expressed in PRAGMA-ASSERTION into MERGE-DNF and return it.
     */
    @LispMethod(comment = "Merge the pragmatic requirements for RULE-ASSERTION expressed in PRAGMA-ASSERTION into MERGE-DNF and return it.")
    public static final SubLObject merge_pragmatic_requirement_alt(SubLObject rule_assertion, SubLObject pragma_assertion, SubLObject merge_dnf) {
        {
            SubLObject neg_lits = clauses.neg_lits(merge_dnf);
            SubLObject pos_lits = clauses.pos_lits(merge_dnf);
            SubLObject rule_cnf = assertions_high.assertion_cnf(rule_assertion);
            SubLObject pragma_cnf = assertions_high.assertion_cnf(pragma_assertion);
            {
                SubLObject cdolist_list_var = clauses.neg_lits(pragma_cnf);
                SubLObject pragmatic_lit = NIL;
                for (pragmatic_lit = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pragmatic_lit = cdolist_list_var.first()) {
                    pragmatic_lit = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.compute_pragmatic_literal_for_merge(pragmatic_lit, merge_dnf, rule_cnf);
                    {
                        SubLObject item_var = pragmatic_lit;
                        if (NIL == member(item_var, pos_lits, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                            pos_lits = cons(item_var, pos_lits);
                        }
                    }
                }
            }
            {
                SubLObject cdolist_list_var = clauses.pos_lits(pragma_cnf);
                SubLObject pragmatic_lit = NIL;
                for (pragmatic_lit = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pragmatic_lit = cdolist_list_var.first()) {
                    if (NIL == el_meets_pragmatic_requirement_p(pragmatic_lit)) {
                        pragmatic_lit = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.compute_pragmatic_literal_for_merge(pragmatic_lit, merge_dnf, rule_cnf);
                        {
                            SubLObject item_var = pragmatic_lit;
                            if (NIL == member(item_var, neg_lits, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                neg_lits = cons(item_var, neg_lits);
                            }
                        }
                    }
                }
            }
            return clause_utilities.nmake_dnf(neg_lits, pos_lits, merge_dnf);
        }
    }

    /**
     * Merge the pragmatic requirements for RULE-ASSERTION expressed in PRAGMA-ASSERTION into MERGE-DNF and return it.
     */
    @LispMethod(comment = "Merge the pragmatic requirements for RULE-ASSERTION expressed in PRAGMA-ASSERTION into MERGE-DNF and return it.")
    public static SubLObject merge_pragmatic_requirement(final SubLObject rule_assertion, final SubLObject pragma_assertion, final SubLObject merge_dnf) {
        SubLObject neg_lits = clauses.neg_lits(merge_dnf);
        SubLObject pos_lits = clauses.pos_lits(merge_dnf);
        final SubLObject rule_cnf = assertions_high.assertion_cnf(rule_assertion);
        final SubLObject pragma_cnf = assertions_high.assertion_cnf(pragma_assertion);
        SubLObject cdolist_list_var = clauses.neg_lits(pragma_cnf);
        SubLObject pragmatic_lit = NIL;
        pragmatic_lit = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject item_var;
            pragmatic_lit = item_var = compute_pragmatic_literal_for_merge(pragmatic_lit, merge_dnf, rule_cnf);
            if (NIL == member(item_var, pos_lits, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                pos_lits = cons(item_var, pos_lits);
            }
            cdolist_list_var = cdolist_list_var.rest();
            pragmatic_lit = cdolist_list_var.first();
        } 
        cdolist_list_var = clauses.pos_lits(pragma_cnf);
        pragmatic_lit = NIL;
        pragmatic_lit = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == el_meets_pragmatic_requirement_p(pragmatic_lit)) {
                final SubLObject item_var;
                pragmatic_lit = item_var = compute_pragmatic_literal_for_merge(pragmatic_lit, merge_dnf, rule_cnf);
                if (NIL == member(item_var, neg_lits, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                    neg_lits = cons(item_var, neg_lits);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            pragmatic_lit = cdolist_list_var.first();
        } 
        return clause_utilities.nmake_dnf(neg_lits, pos_lits, merge_dnf);
    }

    /**
     * If LITERAL contains any HL variables that are not mentioned in RULE-DNF
     * but _are_ mentioned in MERGE-DNF, returns a new literal which is LITERAL
     * with those HL variables substituted with new HL variables which do not occur
     * in either MERGE-DNF or RULE-DNF.  Otherwise returns LITERAL.
     */
    @LispMethod(comment = "If LITERAL contains any HL variables that are not mentioned in RULE-DNF\r\nbut _are_ mentioned in MERGE-DNF, returns a new literal which is LITERAL\r\nwith those HL variables substituted with new HL variables which do not occur\r\nin either MERGE-DNF or RULE-DNF.  Otherwise returns LITERAL.\nIf LITERAL contains any HL variables that are not mentioned in RULE-DNF\nbut _are_ mentioned in MERGE-DNF, returns a new literal which is LITERAL\nwith those HL variables substituted with new HL variables which do not occur\nin either MERGE-DNF or RULE-DNF.  Otherwise returns LITERAL.")
    public static final SubLObject compute_pragmatic_literal_for_merge_alt(SubLObject literal, SubLObject merge_dnf, SubLObject rule_dnf) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = literal;
                {
                    SubLObject _prev_bind_0 = $merge_dnf_lambda_var$.currentBinding(thread);
                    SubLObject _prev_bind_1 = $rule_dnf_lambda_var$.currentBinding(thread);
                    try {
                        $merge_dnf_lambda_var$.bind(merge_dnf, thread);
                        $rule_dnf_lambda_var$.bind(rule_dnf, thread);
                        {
                            SubLObject conflicting_hl_var = cycl_utilities.expression_find_if(HL_VARIABLE_NOT_MENTIONED_IN_RULE_DNF_BUT_MENTIONED_IN_MERGE_DNF, literal, NIL, UNPROVIDED);
                            if (NIL != conflicting_hl_var) {
                                {
                                    SubLObject unique_hl_var = czer_utilities.unique_hl_var_wrt_expression(merge_dnf, rule_dnf);
                                    SubLObject new_literal = cycl_utilities.expression_subst(unique_hl_var, conflicting_hl_var, literal, UNPROVIDED, UNPROVIDED);
                                    result = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.compute_pragmatic_literal_for_merge(new_literal, merge_dnf, rule_dnf);
                                }
                            }
                        }
                    } finally {
                        $rule_dnf_lambda_var$.rebind(_prev_bind_1, thread);
                        $merge_dnf_lambda_var$.rebind(_prev_bind_0, thread);
                    }
                }
                return result;
            }
        }
    }

    /**
     * If LITERAL contains any HL variables that are not mentioned in RULE-DNF
     * but _are_ mentioned in MERGE-DNF, returns a new literal which is LITERAL
     * with those HL variables substituted with new HL variables which do not occur
     * in either MERGE-DNF or RULE-DNF.  Otherwise returns LITERAL.
     */
    @LispMethod(comment = "If LITERAL contains any HL variables that are not mentioned in RULE-DNF\r\nbut _are_ mentioned in MERGE-DNF, returns a new literal which is LITERAL\r\nwith those HL variables substituted with new HL variables which do not occur\r\nin either MERGE-DNF or RULE-DNF.  Otherwise returns LITERAL.\nIf LITERAL contains any HL variables that are not mentioned in RULE-DNF\nbut _are_ mentioned in MERGE-DNF, returns a new literal which is LITERAL\nwith those HL variables substituted with new HL variables which do not occur\nin either MERGE-DNF or RULE-DNF.  Otherwise returns LITERAL.")
    public static SubLObject compute_pragmatic_literal_for_merge(final SubLObject literal, final SubLObject merge_dnf, final SubLObject rule_dnf) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = literal;
        final SubLObject _prev_bind_0 = $merge_dnf_lambda_var$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $rule_dnf_lambda_var$.currentBinding(thread);
        try {
            $merge_dnf_lambda_var$.bind(merge_dnf, thread);
            $rule_dnf_lambda_var$.bind(rule_dnf, thread);
            final SubLObject conflicting_hl_var = cycl_utilities.expression_find_if(HL_VARIABLE_NOT_MENTIONED_IN_RULE_DNF_BUT_MENTIONED_IN_MERGE_DNF, literal, NIL, UNPROVIDED);
            if (NIL != conflicting_hl_var) {
                final SubLObject unique_hl_var = czer_utilities.unique_hl_var_wrt_expression(merge_dnf, rule_dnf);
                final SubLObject new_literal = cycl_utilities.expression_subst(unique_hl_var, conflicting_hl_var, literal, UNPROVIDED, UNPROVIDED);
                result = compute_pragmatic_literal_for_merge(new_literal, merge_dnf, rule_dnf);
            }
        } finally {
            $rule_dnf_lambda_var$.rebind(_prev_bind_2, thread);
            $merge_dnf_lambda_var$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static final SubLObject hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf_alt(SubLObject v_object) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return makeBoolean(((NIL != cycl_grammar.hl_variable_p(v_object)) && (NIL == cycl_utilities.expression_find(v_object, $rule_dnf_lambda_var$.getDynamicValue(thread), NIL, UNPROVIDED, UNPROVIDED))) && (NIL != cycl_utilities.expression_find(v_object, $merge_dnf_lambda_var$.getDynamicValue(thread), NIL, UNPROVIDED, UNPROVIDED)));
        }
    }

    public static SubLObject hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf(final SubLObject v_object) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return makeBoolean(((NIL != cycl_grammar.hl_variable_p(v_object)) && (NIL == cycl_utilities.expression_find(v_object, $rule_dnf_lambda_var$.getDynamicValue(thread), NIL, UNPROVIDED, UNPROVIDED))) && (NIL != cycl_utilities.expression_find(v_object, $merge_dnf_lambda_var$.getDynamicValue(thread), NIL, UNPROVIDED, UNPROVIDED)));
    }

    public static final SubLObject bubble_up_proof_to_transformation_link_alt(SubLObject supporting_proof, SubLObject variable_map, SubLObject transformation_link) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(transformation_link, PROBLEM_LINK_WITH_SINGLE_SUPPORTING_PROBLEM_P);
            thread.resetMultipleValues();
            {
                SubLObject proof = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.new_transformation_proof(transformation_link, supporting_proof, variable_map);
                SubLObject newP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL != newP) {
                    inference_worker.bubble_up_proof(proof);
                } else {
                    inference_worker.possibly_note_proof_processed(supporting_proof);
                }
                return proof;
            }
        }
    }

    public static SubLObject bubble_up_proof_to_transformation_link(final SubLObject supporting_proof, final SubLObject variable_map, final SubLObject transformation_link) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != inference_datastructures_problem_link.problem_link_with_single_supporting_problem_p(transformation_link) : "! inference_datastructures_problem_link.problem_link_with_single_supporting_problem_p(transformation_link) " + ("inference_datastructures_problem_link.problem_link_with_single_supporting_problem_p(transformation_link) " + "CommonSymbols.NIL != inference_datastructures_problem_link.problem_link_with_single_supporting_problem_p(transformation_link) ") + transformation_link;
        thread.resetMultipleValues();
        final SubLObject proof = new_transformation_proof(transformation_link, supporting_proof, variable_map);
        final SubLObject newP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != newP) {
            inference_worker.bubble_up_proof(proof);
        } else {
            inference_worker.possibly_note_proof_processed(supporting_proof);
        }
        return proof;
    }

    public static final SubLObject transformation_proof_abnormalP_internal_alt(SubLObject transformation_proof) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_abnormal_intP(transformation_proof);
    }

    public static SubLObject transformation_proof_abnormalP_internal(final SubLObject transformation_proof) {
        return transformation_proof_abnormal_intP(transformation_proof);
    }

    public static final SubLObject transformation_proof_abnormalP_alt(SubLObject transformation_proof) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_abnormalP_internal(transformation_proof);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, $sym84$TRANSFORMATION_PROOF_ABNORMAL_, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), $sym84$TRANSFORMATION_PROOF_ABNORMAL_, ONE_INTEGER, NIL, EQ, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, $sym84$TRANSFORMATION_PROOF_ABNORMAL_, caching_state);
                }
                {
                    SubLObject results = memoization_state.caching_state_lookup(caching_state, transformation_proof, $kw51$_MEMOIZED_ITEM_NOT_FOUND_);
                    if (results == $kw51$_MEMOIZED_ITEM_NOT_FOUND_) {
                        results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_abnormalP_internal(transformation_proof)));
                        memoization_state.caching_state_put(caching_state, transformation_proof, results, UNPROVIDED);
                    }
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject transformation_proof_abnormalP(final SubLObject transformation_proof) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = $memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return transformation_proof_abnormalP_internal(transformation_proof);
        }
        caching_state = memoization_state_lookup(v_memoization_state, $sym93$TRANSFORMATION_PROOF_ABNORMAL_, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = create_caching_state(memoization_state_lock(v_memoization_state), $sym93$TRANSFORMATION_PROOF_ABNORMAL_, ONE_INTEGER, NIL, EQ, UNPROVIDED);
            memoization_state_put(v_memoization_state, $sym93$TRANSFORMATION_PROOF_ABNORMAL_, caching_state);
        }
        SubLObject results = caching_state_lookup(caching_state, transformation_proof, $memoized_item_not_found$.getGlobalValue());
        if (results.eql($memoized_item_not_found$.getGlobalValue())) {
            results = arg2(thread.resetMultipleValues(), multiple_value_list(transformation_proof_abnormalP_internal(transformation_proof)));
            caching_state_put(caching_state, transformation_proof, results, UNPROVIDED);
        }
        return caching_results(results);
    }

    public static final SubLObject transformation_proof_abnormal_intP_alt(SubLObject transformation_proof) {
        SubLTrampolineFile.checkType(transformation_proof, TRANSFORMATION_PROOF_P);
        {
            SubLObject link = inference_datastructures_proof.proof_link(transformation_proof);
            SubLObject store = inference_datastructures_problem_link.problem_link_store(link);
            SubLObject rule = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_rule_assertion(link);
            SubLObject transformation_mt = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_link_transformation_mt(link);
            SubLObject rule_bindings = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.transformation_proof_rule_bindings(transformation_proof);
            return abnormal.rule_bindings_abnormalP(store, rule, rule_bindings, transformation_mt);
        }
    }

    public static SubLObject transformation_proof_abnormal_intP(final SubLObject transformation_proof) {
        assert NIL != transformation_proof_p(transformation_proof) : "! inference_worker_transformation.transformation_proof_p(transformation_proof) " + ("inference_worker_transformation.transformation_proof_p(transformation_proof) " + "CommonSymbols.NIL != inference_worker_transformation.transformation_proof_p(transformation_proof) ") + transformation_proof;
        final SubLObject link = inference_datastructures_proof.proof_link(transformation_proof);
        final SubLObject store = inference_datastructures_problem_link.problem_link_store(link);
        final SubLObject rule = transformation_link_rule_assertion(link);
        final SubLObject transformation_mt = transformation_link_transformation_mt(link);
        final SubLObject rule_bindings = transformation_proof_rule_bindings(transformation_proof);
        return abnormal.rule_bindings_abnormalP(store, rule, rule_bindings, transformation_mt);
    }

    public static final SubLObject proof_depends_on_excepted_assertionP_alt(SubLObject proof) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.supports_contain_excepted_assertionP(inference_datastructures_proof.proof_supports(proof));
    }

    public static SubLObject proof_depends_on_excepted_assertionP(final SubLObject proof) {
        final SubLObject problem_mt = inference_datastructures_problem.problem_mt(inference_datastructures_proof.proof_supported_problem(proof));
        if (NIL != problem_mt) {
            return supports_contain_excepted_assertion_in_mtP(inference_datastructures_proof.proof_supports(proof), problem_mt, UNPROVIDED);
        }
        return supports_contain_excepted_assertionP(inference_datastructures_proof.proof_supports(proof), UNPROVIDED);
    }

    public static final SubLObject supports_contain_excepted_assertionP(SubLObject supports) {
        {
            SubLObject cdolist_list_var = supports;
            SubLObject support = NIL;
            for (support = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , support = cdolist_list_var.first()) {
                if ((NIL != assertion_handles.assertion_p(support)) && (NIL != com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_excepted_assertionP(support))) {
                    return T;
                }
            }
        }
        return NIL;
    }

    public static SubLObject supports_contain_excepted_assertionP(final SubLObject supports, SubLObject justifyP) {
        if (justifyP == UNPROVIDED) {
            justifyP = NIL;
        }
        SubLObject result = NIL;
        if (NIL == result) {
            SubLObject csome_list_var = supports;
            SubLObject support = NIL;
            support = csome_list_var.first();
            while ((NIL == result) && (NIL != csome_list_var)) {
                if (NIL != assertion_handles.assertion_p(support)) {
                    result = inference_trampolines.inference_excepted_assertionP(support, justifyP);
                }
                csome_list_var = csome_list_var.rest();
                support = csome_list_var.first();
            } 
        }
        return result;
    }

    public static final SubLObject supports_contain_excepted_assertion_in_mtP(SubLObject supports, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        result = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.supports_contain_excepted_assertionP(supports);
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return result;
            }
        }
    }

    public static SubLObject supports_contain_excepted_assertion_in_mtP(final SubLObject supports, final SubLObject mt, SubLObject justifyP) {
        if (justifyP == UNPROVIDED) {
            justifyP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            result = supports_contain_excepted_assertionP(supports, justifyP);
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    /**
     *
     *
     * @return nil or list of integerp ;
    The argnums of PREDICATE in MT which must be #$termChosen in order to allow transformation.
     */
    @LispMethod(comment = "@return nil or list of integerp ;\r\nThe argnums of PREDICATE in MT which must be #$termChosen in order to allow transformation.")
    public static final SubLObject inference_backchain_forbidden_unless_arg_chosen_argnums_alt(SubLObject predicate, SubLObject mt) {
        {
            SubLObject argnums = NIL;
            if (((NIL != forts.fort_p(predicate)) && (NIL != variables.fully_bound_p(mt))) && (NIL != kb_accessors.some_backchain_forbidden_unless_arg_chosen_assertion_somewhereP(predicate))) {
                argnums = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_backchain_forbidden_unless_arg_chosen_argnums_memoized(predicate, mt);
            }
            return argnums;
        }
    }

    /**
     *
     *
     * @return nil or list of integerp ;
    The argnums of PREDICATE in MT which must be #$termChosen in order to allow transformation.
     */
    @LispMethod(comment = "@return nil or list of integerp ;\r\nThe argnums of PREDICATE in MT which must be #$termChosen in order to allow transformation.")
    public static SubLObject inference_backchain_forbidden_unless_arg_chosen_argnums(final SubLObject predicate, final SubLObject mt) {
        SubLObject argnums = NIL;
        if (((NIL != fort_p(predicate)) && (NIL != variables.fully_bound_p(mt))) && (NIL != some_backchain_forbidden_unless_arg_chosen_assertion_somewhereP(predicate))) {
            argnums = inference_backchain_forbidden_unless_arg_chosen_argnums_memoized(predicate, mt);
        }
        return argnums;
    }

    public static final SubLObject inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal_alt(SubLObject predicate, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject argnums = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        argnums = kb_accessors.backchain_forbidden_unless_arg_chosen_argnums(predicate, mt);
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return argnums;
            }
        }
    }

    public static SubLObject inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal(final SubLObject predicate, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject argnums = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            argnums = backchain_forbidden_unless_arg_chosen_argnums(predicate, mt);
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return argnums;
    }

    public static final SubLObject inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_alt(SubLObject predicate, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal(predicate, mt);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED, TWO_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_2(predicate, mt);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw51$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (predicate.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                                            return memoization_state.caching_results(results2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal(predicate, mt)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(predicate, mt));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject inference_backchain_forbidden_unless_arg_chosen_argnums_memoized(final SubLObject predicate, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = $memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal(predicate, mt);
        }
        caching_state = memoization_state_lookup(v_memoization_state, INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = create_caching_state(memoization_state_lock(v_memoization_state), INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED, TWO_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state_put(v_memoization_state, INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED, caching_state);
        }
        final SubLObject sxhash = sxhash_calc_2(predicate, mt);
        final SubLObject collisions = caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql($memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (predicate.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                        return caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal(predicate, mt)));
        caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(predicate, mt));
        return caching_results(results3);
    }

    /**
     * Return T iff ASENT is an atomic sentence in MT for which
     * transformation is forbidden due to some of its arguments not being chosen
     */
    @LispMethod(comment = "Return T iff ASENT is an atomic sentence in MT for which\r\ntransformation is forbidden due to some of its arguments not being chosen\nReturn T iff ASENT is an atomic sentence in MT for which\ntransformation is forbidden due to some of its arguments not being chosen")
    public static final SubLObject inference_backchain_forbidden_unless_arg_chosen_asentP_alt(SubLObject asent, SubLObject mt) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_backchain_forbidden_unless_arg_chosen_asent_variables_int(asent, mt, NIL);
    }

    /**
     * Return T iff ASENT is an atomic sentence in MT for which
     * transformation is forbidden due to some of its arguments not being chosen
     */
    @LispMethod(comment = "Return T iff ASENT is an atomic sentence in MT for which\r\ntransformation is forbidden due to some of its arguments not being chosen\nReturn T iff ASENT is an atomic sentence in MT for which\ntransformation is forbidden due to some of its arguments not being chosen")
    public static SubLObject inference_backchain_forbidden_unless_arg_chosen_asentP(final SubLObject asent, final SubLObject mt) {
        return inference_backchain_forbidden_unless_arg_chosen_asent_variables_int(asent, mt, NIL);
    }

    /**
     *
     *
     * @return nil or list of variable-p;
    The free variables of ASENT that require transformation progress.
     */
    @LispMethod(comment = "@return nil or list of variable-p;\r\nThe free variables of ASENT that require transformation progress.")
    public static final SubLObject inference_backchain_forbidden_unless_arg_chosen_asent_variables_alt(SubLObject asent, SubLObject mt) {
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_backchain_forbidden_unless_arg_chosen_asent_variables_int(asent, mt, T);
    }

    /**
     *
     *
     * @return nil or list of variable-p;
    The free variables of ASENT that require transformation progress.
     */
    @LispMethod(comment = "@return nil or list of variable-p;\r\nThe free variables of ASENT that require transformation progress.")
    public static SubLObject inference_backchain_forbidden_unless_arg_chosen_asent_variables(final SubLObject asent, final SubLObject mt) {
        return inference_backchain_forbidden_unless_arg_chosen_asent_variables_int(asent, mt, T);
    }

    public static final SubLObject inference_backchain_forbidden_unless_arg_chosen_asent_variables_int_alt(SubLObject asent, SubLObject mt, SubLObject justifyP) {
        {
            SubLObject v_variables = NIL;
            if (NIL == variables.fully_bound_p(asent)) {
                {
                    SubLObject predicate = cycl_utilities.atomic_sentence_predicate(asent);
                    SubLObject argnums = com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.inference_backchain_forbidden_unless_arg_chosen_argnums(predicate, mt);
                    if (NIL != argnums) {
                        {
                            SubLObject argnum = ZERO_INTEGER;
                            SubLObject args = cycl_utilities.formula_args(asent, $IGNORE);
                            SubLObject cdolist_list_var = args;
                            SubLObject arg = NIL;
                            for (arg = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg = cdolist_list_var.first()) {
                                argnum = add(argnum, ONE_INTEGER);
                                if (NIL != subl_promotions.memberP(argnum, argnums, symbol_function(EQL), UNPROVIDED)) {
                                    if (NIL == variables.fully_bound_p(arg)) {
                                        if (NIL != justifyP) {
                                            {
                                                SubLObject cdolist_list_var_14 = variables.gather_hl_variables(arg);
                                                SubLObject variable = NIL;
                                                for (variable = cdolist_list_var_14.first(); NIL != cdolist_list_var_14; cdolist_list_var_14 = cdolist_list_var_14.rest() , variable = cdolist_list_var_14.first()) {
                                                    {
                                                        SubLObject item_var = variable;
                                                        if (NIL == member(item_var, v_variables, symbol_function(EQ), symbol_function(IDENTITY))) {
                                                            v_variables = cons(item_var, v_variables);
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            return T;
                                        }
                                    }
                                }
                            }
                        }
                        v_variables = Sort.sort(v_variables, symbol_function($sym87$_), symbol_function(VARIABLE_ID));
                    }
                }
            }
            return v_variables;
        }
    }

    public static SubLObject inference_backchain_forbidden_unless_arg_chosen_asent_variables_int(final SubLObject asent, final SubLObject mt, final SubLObject justifyP) {
        SubLObject v_variables = NIL;
        if (NIL == variables.fully_bound_p(asent)) {
            final SubLObject predicate = cycl_utilities.atomic_sentence_predicate(asent);
            final SubLObject argnums = inference_backchain_forbidden_unless_arg_chosen_argnums(predicate, mt);
            if (NIL != argnums) {
                SubLObject argnum = ZERO_INTEGER;
                SubLObject cdolist_list_var;
                final SubLObject args = cdolist_list_var = cycl_utilities.formula_args(asent, $IGNORE);
                SubLObject arg = NIL;
                arg = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    argnum = add(argnum, ONE_INTEGER);
                    if ((NIL != subl_promotions.memberP(argnum, argnums, symbol_function(EQL), UNPROVIDED)) && (NIL == variables.fully_bound_p(arg))) {
                        if (NIL == justifyP) {
                            return T;
                        }
                        SubLObject cdolist_list_var_$40 = variables.gather_hl_variables(arg);
                        SubLObject variable = NIL;
                        variable = cdolist_list_var_$40.first();
                        while (NIL != cdolist_list_var_$40) {
                            final SubLObject item_var = variable;
                            if (NIL == member(item_var, v_variables, symbol_function(EQL), symbol_function(IDENTITY))) {
                                v_variables = cons(item_var, v_variables);
                            }
                            cdolist_list_var_$40 = cdolist_list_var_$40.rest();
                            variable = cdolist_list_var_$40.first();
                        } 
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    arg = cdolist_list_var.first();
                } 
                v_variables = Sort.sort(v_variables, symbol_function($sym96$_), symbol_function(VARIABLE_ID));
            }
        }
        return v_variables;
    }

    static private final SubLList $list_alt2 = list(makeSymbol("HL-MODULE"), makeSymbol("BINDINGS"), makeSymbol("SUPPORTS"), makeSymbol("NON-EXPLANATORY-SUBQUERY"));

    static private final SubLList $list_alt3 = list(makeKeyword("HL-MODULE"), makeKeyword("BINDINGS"), makeKeyword("SUPPORTS"), makeKeyword("NON-EXPLANATORY-SUBQUERY"));

    public static final SubLObject genl_rules_enabledP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return $genl_rules_enabledP$.getDynamicValue(thread);
        }
    }

    public static SubLObject genl_rules_enabledP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return $genl_rules_enabledP$.getDynamicValue(thread);
    }

    static private final SubLList $list_alt4 = list(makeSymbol("TRANS-LINK-DATA-HL-MODULE"), makeSymbol("TRANS-LINK-DATA-BINDINGS"), makeSymbol("TRANS-LINK-DATA-SUPPORTS"), makeSymbol("TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY"));

    public static final SubLObject genl_rules_alt(SubLObject rule, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return gt_methods.gt_superiors($$genlRules, rule, mt);
    }

    public static SubLObject genl_rules(final SubLObject rule, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return gt_methods.gt_superiors($$genlRules, rule, mt);
    }

    static private final SubLList $list_alt5 = list(makeSymbol("_CSETF-TRANS-LINK-DATA-HL-MODULE"), makeSymbol("_CSETF-TRANS-LINK-DATA-BINDINGS"), makeSymbol("_CSETF-TRANS-LINK-DATA-SUPPORTS"), makeSymbol("_CSETF-TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY"));

    /**
     * Returns the most-general rules (via #$genlRules) among RULES,
     * which are those rules that have no proper genlRule among RULES.
     */
    @LispMethod(comment = "Returns the most-general rules (via #$genlRules) among RULES,\r\nwhich are those rules that have no proper genlRule among RULES.\nReturns the most-general rules (via #$genlRules) among RULES,\nwhich are those rules that have no proper genlRule among RULES.")
    public static final SubLObject max_rules_alt(SubLObject rules, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        if (NIL == valid_constantP($$genlRules, UNPROVIDED)) {
            return rules;
        }
        return gt_methods.gt_max_nodes($$genlRules, rules, mt, UNPROVIDED);
    }

    @LispMethod(comment = "Returns the most-general rules (via #$genlRules) among RULES,\r\nwhich are those rules that have no proper genlRule among RULES.\nReturns the most-general rules (via #$genlRules) among RULES,\nwhich are those rules that have no proper genlRule among RULES.")
    public static SubLObject max_rules(final SubLObject rules, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        if (NIL == valid_constantP($$genlRules, UNPROVIDED)) {
            return rules;
        }
        SubLObject any_relevant_genl_rules_assertionsP = NIL;
        if (NIL == any_relevant_genl_rules_assertionsP) {
            SubLObject csome_list_var = rules;
            SubLObject rule = NIL;
            rule = csome_list_var.first();
            while ((NIL == any_relevant_genl_rules_assertionsP) && (NIL != csome_list_var)) {
                if (NIL != somewhere_cache.some_pred_assertion_somewhereP($$genlRules, rule, ONE_INTEGER, UNPROVIDED)) {
                    any_relevant_genl_rules_assertionsP = T;
                }
                csome_list_var = csome_list_var.rest();
                rule = csome_list_var.first();
            } 
        }
        if (NIL == any_relevant_genl_rules_assertionsP) {
            return rules;
        }
        return gt_methods.gt_max_nodes($$genlRules, rules, mt, UNPROVIDED);
    }

    public static final SubLObject declare_inference_worker_transformation_file_alt() {
        declareFunction("transformation_link_data_print_function_trampoline", "TRANSFORMATION-LINK-DATA-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("transformation_link_data_p", "TRANSFORMATION-LINK-DATA-P", 1, 0, false);
        new com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$transformation_link_data_p$UnaryFunction();
        declareFunction("trans_link_data_hl_module", "TRANS-LINK-DATA-HL-MODULE", 1, 0, false);
        declareFunction("trans_link_data_bindings", "TRANS-LINK-DATA-BINDINGS", 1, 0, false);
        declareFunction("trans_link_data_supports", "TRANS-LINK-DATA-SUPPORTS", 1, 0, false);
        declareFunction("trans_link_data_non_explanatory_subquery", "TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY", 1, 0, false);
        declareFunction("_csetf_trans_link_data_hl_module", "_CSETF-TRANS-LINK-DATA-HL-MODULE", 2, 0, false);
        declareFunction("_csetf_trans_link_data_bindings", "_CSETF-TRANS-LINK-DATA-BINDINGS", 2, 0, false);
        declareFunction("_csetf_trans_link_data_supports", "_CSETF-TRANS-LINK-DATA-SUPPORTS", 2, 0, false);
        declareFunction("_csetf_trans_link_data_non_explanatory_subquery", "_CSETF-TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY", 2, 0, false);
        declareFunction("make_transformation_link_data", "MAKE-TRANSFORMATION-LINK-DATA", 0, 1, false);
        declareFunction("new_transformation_link", "NEW-TRANSFORMATION-LINK", 7, 0, false);
        declareFunction("new_transformation_link_int", "NEW-TRANSFORMATION-LINK-INT", 5, 0, false);
        declareFunction("new_transformation_link_data", "NEW-TRANSFORMATION-LINK-DATA", 1, 0, false);
        declareFunction("destroy_transformation_link", "DESTROY-TRANSFORMATION-LINK", 1, 0, false);
        declareFunction("transformation_link_hl_module", "TRANSFORMATION-LINK-HL-MODULE", 1, 0, false);
        declareFunction("transformation_link_bindings", "TRANSFORMATION-LINK-BINDINGS", 1, 0, false);
        declareFunction("transformation_link_supports", "TRANSFORMATION-LINK-SUPPORTS", 1, 0, false);
        declareFunction("transformation_link_rule_assertion", "TRANSFORMATION-LINK-RULE-ASSERTION", 1, 0, false);
        declareFunction("transformation_link_more_supports", "TRANSFORMATION-LINK-MORE-SUPPORTS", 1, 0, false);
        declareFunction("transformation_link_non_explanatory_subquery", "TRANSFORMATION-LINK-NON-EXPLANATORY-SUBQUERY", 1, 0, false);
        declareFunction("set_transformation_link_hl_module", "SET-TRANSFORMATION-LINK-HL-MODULE", 2, 0, false);
        declareFunction("set_transformation_link_bindings", "SET-TRANSFORMATION-LINK-BINDINGS", 2, 0, false);
        declareFunction("set_transformation_link_supports", "SET-TRANSFORMATION-LINK-SUPPORTS", 2, 0, false);
        declareFunction("set_transformation_link_non_explanatory_subquery", "SET-TRANSFORMATION-LINK-NON-EXPLANATORY-SUBQUERY", 2, 0, false);
        declareFunction("transformation_link_tactic", "TRANSFORMATION-LINK-TACTIC", 1, 0, false);
        declareFunction("transformation_link_pragmatic_requirements", "TRANSFORMATION-LINK-PRAGMATIC-REQUIREMENTS", 1, 0, false);
        declareFunction("transformation_link_supporting_mapped_problem", "TRANSFORMATION-LINK-SUPPORTING-MAPPED-PROBLEM", 1, 0, false);
        declareFunction("transformation_link_supporting_problem", "TRANSFORMATION-LINK-SUPPORTING-PROBLEM", 1, 0, false);
        declareFunction("transformation_link_supporting_variable_map", "TRANSFORMATION-LINK-SUPPORTING-VARIABLE-MAP", 1, 0, false);
        declareFunction("transformation_link_transformation_mt", "TRANSFORMATION-LINK-TRANSFORMATION-MT", 1, 0, false);
        declareFunction("transformation_link_supporting_problem_wholly_explanatoryP", "TRANSFORMATION-LINK-SUPPORTING-PROBLEM-WHOLLY-EXPLANATORY?", 1, 0, false);
        declareFunction("transformed_problem_using_rule", "TRANSFORMED-PROBLEM-USING-RULE", 2, 0, false);
        declareFunction("transformed_problem_using_rule_and_hl_module", "TRANSFORMED-PROBLEM-USING-RULE-AND-HL-MODULE", 3, 0, false);
        declareFunction("transformation_link_rule_bindings_to_closed", "TRANSFORMATION-LINK-RULE-BINDINGS-TO-CLOSED", 1, 0, false);
        declareFunction("transformation_rule_bindings_to_closed", "TRANSFORMATION-RULE-BINDINGS-TO-CLOSED", 1, 0, false);
        declareMacro("with_problem_store_transformation_assumptions", "WITH-PROBLEM-STORE-TRANSFORMATION-ASSUMPTIONS");
        declareFunction("meta_transformation_tactic_p", "META-TRANSFORMATION-TACTIC-P", 1, 0, false);
        declareFunction("add_tactic_to_determine_new_literal_transformation_tactics", "ADD-TACTIC-TO-DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS", 4, 0, false);
        declareFunction("inference_backchain_forbidden_asentP", "INFERENCE-BACKCHAIN-FORBIDDEN-ASENT?", 2, 0, false);
        declareFunction("new_meta_transformation_tactic", "NEW-META-TRANSFORMATION-TACTIC", 3, 0, false);
        declareFunction("compute_strategic_properties_of_meta_transformation_tactic", "COMPUTE-STRATEGIC-PROPERTIES-OF-META-TRANSFORMATION-TACTIC", 2, 0, false);
        declareFunction("transformation_link_p", "TRANSFORMATION-LINK-P", 1, 0, false);
        declareFunction("transformation_tactic_p", "TRANSFORMATION-TACTIC-P", 1, 0, false);
        new com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$transformation_tactic_p$UnaryFunction();
        declareFunction("transformation_tactic_rule", "TRANSFORMATION-TACTIC-RULE", 1, 0, false);
        declareFunction("transformation_rule_tactic_p", "TRANSFORMATION-RULE-TACTIC-P", 1, 0, false);
        declareFunction("transformation_generator_tactic_p", "TRANSFORMATION-GENERATOR-TACTIC-P", 1, 0, false);
        declareFunction("transformation_generator_tactic_lookahead_rule", "TRANSFORMATION-GENERATOR-TACTIC-LOOKAHEAD-RULE", 1, 0, false);
        declareFunction("transformation_tactic_lookahead_rule", "TRANSFORMATION-TACTIC-LOOKAHEAD-RULE", 1, 0, false);
        declareFunction("transformation_proof_p", "TRANSFORMATION-PROOF-P", 1, 0, false);
        declareFunction("transformation_proof_rule_assertion", "TRANSFORMATION-PROOF-RULE-ASSERTION", 1, 0, false);
        declareFunction("transformation_proof_additional_supports", "TRANSFORMATION-PROOF-ADDITIONAL-SUPPORTS", 1, 0, false);
        declareFunction("transformation_proof_subproof", "TRANSFORMATION-PROOF-SUBPROOF", 1, 0, false);
        declareFunction("generalized_transformation_link_p", "GENERALIZED-TRANSFORMATION-LINK-P", 1, 0, false);
        declareFunction("generalized_transformation_link_rule_assertion", "GENERALIZED-TRANSFORMATION-LINK-RULE-ASSERTION", 1, 0, false);
        declareFunction("generalized_transformation_link_unaffected_by_exceptionsP", "GENERALIZED-TRANSFORMATION-LINK-UNAFFECTED-BY-EXCEPTIONS?", 1, 0, false);
        declareFunction("generalized_transformation_proof_p", "GENERALIZED-TRANSFORMATION-PROOF-P", 1, 0, false);
        declareFunction("generalized_transformation_proof_rule_assertion", "GENERALIZED-TRANSFORMATION-PROOF-RULE-ASSERTION", 1, 0, false);
        declareFunction("generalized_transformation_proof_transformation_link", "GENERALIZED-TRANSFORMATION-PROOF-TRANSFORMATION-LINK", 1, 0, false);
        declareFunction("determine_new_literal_transformation_tactics", "DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS", 3, 0, false);
        declareFunction("determine_new_literal_transformation_tactics_int", "DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS-INT", 3, 1, false);
        declareFunction("determine_rules_for_literal_transformation_tactics", "DETERMINE-RULES-FOR-LITERAL-TRANSFORMATION-TACTICS", 3, 0, false);
        declareFunction("inference_excepted_assertionP", "INFERENCE-EXCEPTED-ASSERTION?", 1, 0, false);
        declareFunction("memoized_inference_excepted_assertionP_internal", "MEMOIZED-INFERENCE-EXCEPTED-ASSERTION?-INTERNAL", 2, 0, false);
        declareFunction("memoized_inference_excepted_assertionP", "MEMOIZED-INFERENCE-EXCEPTED-ASSERTION?", 2, 0, false);
        declareFunction("problem_inference_and_non_continuable_problem_store_private", "PROBLEM-INFERENCE-AND-NON-CONTINUABLE-PROBLEM-STORE-PRIVATE", 1, 0, false);
        declareFunction("single_literal_problem_candidate_transformation_tactic_specs", "SINGLE-LITERAL-PROBLEM-CANDIDATE-TRANSFORMATION-TACTIC-SPECS", 1, 0, false);
        declareFunction("determine_literal_transformation_tactic_specs", "DETERMINE-LITERAL-TRANSFORMATION-TACTIC-SPECS", 3, 0, false);
        declareFunction("determine_literal_transformation_tactic_specs_int", "DETERMINE-LITERAL-TRANSFORMATION-TACTIC-SPECS-INT", 4, 0, false);
        declareFunction("literal_level_transformation_tactic_p", "LITERAL-LEVEL-TRANSFORMATION-TACTIC-P", 1, 0, false);
        declareFunction("maybe_new_transformation_link", "MAYBE-NEW-TRANSFORMATION-LINK", 7, 0, false);
        declareFunction("recompute_thrown_away_due_to_new_transformation_link", "RECOMPUTE-THROWN-AWAY-DUE-TO-NEW-TRANSFORMATION-LINK", 1, 0, false);
        declareFunction("new_transformation_tactic", "NEW-TRANSFORMATION-TACTIC", 4, 0, false);
        declareFunction("compute_strategic_properties_of_transformation_tactic", "COMPUTE-STRATEGIC-PROPERTIES-OF-TRANSFORMATION-TACTIC", 2, 0, false);
        declareMacro("with_transformation_tactic_execution_assumptions", "WITH-TRANSFORMATION-TACTIC-EXECUTION-ASSUMPTIONS");
        declareFunction("execute_literal_level_transformation_tactic", "EXECUTE-LITERAL-LEVEL-TRANSFORMATION-TACTIC", 4, 0, false);
        declareFunction("maybe_make_transformation_tactic_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-TACTIC-PROGRESS-ITERATOR", 3, 0, false);
        declareFunction("maybe_make_meta_transformation_progress_iterator", "MAYBE-MAKE-META-TRANSFORMATION-PROGRESS-ITERATOR", 3, 0, false);
        declareFunction("maybe_make_transformation_rule_select_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-RULE-SELECT-PROGRESS-ITERATOR", 2, 0, false);
        declareFunction("new_transformation_rule_select_progress_iterator", "NEW-TRANSFORMATION-RULE-SELECT-PROGRESS-ITERATOR", 2, 0, false);
        declareFunction("handle_one_transformation_tactic_rule_select_result", "HANDLE-ONE-TRANSFORMATION-TACTIC-RULE-SELECT-RESULT", 2, 0, false);
        declareFunction("maybe_make_transformation_expand_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-EXPAND-PROGRESS-ITERATOR", 2, 0, false);
        declareFunction("handle_transformation_add_node_for_expand_results", "HANDLE-TRANSFORMATION-ADD-NODE-FOR-EXPAND-RESULTS", 6, 0, false);
        declareFunction("rule_assertion_worth_adding_type_constraintsP", "RULE-ASSERTION-WORTH-ADDING-TYPE-CONSTRAINTS?", 1, 0, false);
        declareFunction("transformation_additional_dont_care_constraints", "TRANSFORMATION-ADDITIONAL-DONT-CARE-CONSTRAINTS", 4, 0, false);
        declareFunction("nmerge_dnf", "NMERGE-DNF", 2, 0, false);
        declareFunction("merge_dnf", "MERGE-DNF", 2, 0, false);
        declareFunction("complete_execution_of_transformation_tactic", "COMPLETE-EXECUTION-OF-TRANSFORMATION-TACTIC", 6, 0, false);
        declareFunction("compute_transformation_non_explanatory_subquery", "COMPUTE-TRANSFORMATION-NON-EXPLANATORY-SUBQUERY", 5, 0, false);
        declareFunction("potentially_wf_transformation_dependent_query", "POTENTIALLY-WF-TRANSFORMATION-DEPENDENT-QUERY", 2, 0, false);
        declareFunction("potentially_wf_restricted_transformation_dependent_asent", "POTENTIALLY-WF-RESTRICTED-TRANSFORMATION-DEPENDENT-ASENT", 3, 0, false);
        declareFunction("syntactically_valid_asent", "SYNTACTICALLY-VALID-ASENT", 1, 0, false);
        declareFunction("syntactically_valid_contextualized_term_of_unit_asent", "SYNTACTICALLY-VALID-CONTEXTUALIZED-TERM-OF-UNIT-ASENT", 2, 0, false);
        declareFunction("new_transformation_proof", "NEW-TRANSFORMATION-PROOF", 3, 0, false);
        declareFunction("compute_canonical_transformation_proof_bindings", "COMPUTE-CANONICAL-TRANSFORMATION-PROOF-BINDINGS", 3, 0, false);
        declareFunction("unification_dependent_dnf_to_transformation_dependent_dnf", "UNIFICATION-DEPENDENT-DNF-TO-TRANSFORMATION-DEPENDENT-DNF", 1, 0, false);
        declareFunction("unification_bindings_to_transformation_bindings", "UNIFICATION-BINDINGS-TO-TRANSFORMATION-BINDINGS", 1, 0, false);
        declareFunction("swap_variable_spaces_of_unification_bindings", "SWAP-VARIABLE-SPACES-OF-UNIFICATION-BINDINGS", 1, 0, false);
        declareFunction("transformation_proof_rule_bindings", "TRANSFORMATION-PROOF-RULE-BINDINGS", 1, 0, false);
        declareFunction("compute_transformation_link_rule_bindings", "COMPUTE-TRANSFORMATION-LINK-RULE-BINDINGS", 2, 0, false);
        declareFunction("transformation_proof_rule_el_bindings", "TRANSFORMATION-PROOF-RULE-EL-BINDINGS", 1, 0, false);
        declareFunction("transformation_proof_el_bindings", "TRANSFORMATION-PROOF-EL-BINDINGS", 1, 0, false);
        declareFunction("unify_transformation_and_subproof_bindings", "UNIFY-TRANSFORMATION-AND-SUBPROOF-BINDINGS", 2, 0, false);
        declareFunction("extended_supported_problem_bindings_to_supported_problem_bindings", "EXTENDED-SUPPORTED-PROBLEM-BINDINGS-TO-SUPPORTED-PROBLEM-BINDINGS", 1, 0, false);
        declareFunction("supported_problem_variable_p", "SUPPORTED-PROBLEM-VARIABLE-P", 1, 0, false);
        declareFunction("extended_supported_problem_bindings_to_rule_bindings", "EXTENDED-SUPPORTED-PROBLEM-BINDINGS-TO-RULE-BINDINGS", 1, 0, false);
        declareFunction("rule_assertion_variable_p", "RULE-ASSERTION-VARIABLE-P", 1, 0, false);
        declareFunction("rule_assertion_variable_map", "RULE-ASSERTION-VARIABLE-MAP", 1, 0, false);
        declareFunction("rule_assertion_has_some_pragmatic_requirementP", "RULE-ASSERTION-HAS-SOME-PRAGMATIC-REQUIREMENT?", 1, 1, false);
        declareFunction("backward_rule_pragmatic_dnf", "BACKWARD-RULE-PRAGMATIC-DNF", 1, 1, false);
        declareFunction("forward_rule_pragmatic_dnf", "FORWARD-RULE-PRAGMATIC-DNF", 2, 0, false);
        declareFunction("rule_assertion_pragmatic_requirements_dnf", "RULE-ASSERTION-PRAGMATIC-REQUIREMENTS-DNF", 1, 1, false);
        declareFunction("merge_pragmatic_requirement", "MERGE-PRAGMATIC-REQUIREMENT", 3, 0, false);
        declareFunction("compute_pragmatic_literal_for_merge", "COMPUTE-PRAGMATIC-LITERAL-FOR-MERGE", 3, 0, false);
        declareFunction("hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf", "HL-VARIABLE-NOT-MENTIONED-IN-RULE-DNF-BUT-MENTIONED-IN-MERGE-DNF", 1, 0, false);
        new com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf$UnaryFunction();
        declareFunction("bubble_up_proof_to_transformation_link", "BUBBLE-UP-PROOF-TO-TRANSFORMATION-LINK", 3, 0, false);
        declareFunction("transformation_proof_abnormalP_internal", "TRANSFORMATION-PROOF-ABNORMAL?-INTERNAL", 1, 0, false);
        declareFunction("transformation_proof_abnormalP", "TRANSFORMATION-PROOF-ABNORMAL?", 1, 0, false);
        declareFunction("transformation_proof_abnormal_intP", "TRANSFORMATION-PROOF-ABNORMAL-INT?", 1, 0, false);
        declareFunction("proof_depends_on_excepted_assertionP", "PROOF-DEPENDS-ON-EXCEPTED-ASSERTION?", 1, 0, false);
        declareFunction("supports_contain_excepted_assertionP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION?", 1, 0, false);
        declareFunction("supports_contain_excepted_assertion_in_mtP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION-IN-MT?", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS-MEMOIZED-INTERNAL", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums_memoized", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS-MEMOIZED", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_asentP", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT?", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_asent_variables", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT-VARIABLES", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_asent_variables_int", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT-VARIABLES-INT", 3, 0, false);
        declareFunction("genl_rules_enabledP", "GENL-RULES-ENABLED?", 0, 0, false);
        declareFunction("genl_rules", "GENL-RULES", 1, 1, false);
        declareFunction("max_rules", "MAX-RULES", 1, 1, false);
        return NIL;
    }

    public static SubLObject declare_inference_worker_transformation_file() {
        if (SubLFiles.USE_V1) {
            declareFunction("transformation_link_data_print_function_trampoline", "TRANSFORMATION-LINK-DATA-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
            declareFunction("transformation_link_data_p", "TRANSFORMATION-LINK-DATA-P", 1, 0, false);
            new inference_worker_transformation.$transformation_link_data_p$UnaryFunction();
            declareFunction("trans_link_data_hl_module", "TRANS-LINK-DATA-HL-MODULE", 1, 0, false);
            declareFunction("trans_link_data_bindings", "TRANS-LINK-DATA-BINDINGS", 1, 0, false);
            declareFunction("trans_link_data_supports", "TRANS-LINK-DATA-SUPPORTS", 1, 0, false);
            declareFunction("trans_link_data_non_explanatory_subquery", "TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY", 1, 0, false);
            declareFunction("_csetf_trans_link_data_hl_module", "_CSETF-TRANS-LINK-DATA-HL-MODULE", 2, 0, false);
            declareFunction("_csetf_trans_link_data_bindings", "_CSETF-TRANS-LINK-DATA-BINDINGS", 2, 0, false);
            declareFunction("_csetf_trans_link_data_supports", "_CSETF-TRANS-LINK-DATA-SUPPORTS", 2, 0, false);
            declareFunction("_csetf_trans_link_data_non_explanatory_subquery", "_CSETF-TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY", 2, 0, false);
            declareFunction("make_transformation_link_data", "MAKE-TRANSFORMATION-LINK-DATA", 0, 1, false);
            declareFunction("visit_defstruct_transformation_link_data", "VISIT-DEFSTRUCT-TRANSFORMATION-LINK-DATA", 2, 0, false);
            declareFunction("visit_defstruct_object_transformation_link_data_method", "VISIT-DEFSTRUCT-OBJECT-TRANSFORMATION-LINK-DATA-METHOD", 2, 0, false);
            declareFunction("new_transformation_link", "NEW-TRANSFORMATION-LINK", 7, 0, false);
            declareFunction("new_transformation_link_int", "NEW-TRANSFORMATION-LINK-INT", 5, 0, false);
            declareFunction("new_transformation_link_data", "NEW-TRANSFORMATION-LINK-DATA", 1, 0, false);
            declareFunction("destroy_transformation_link", "DESTROY-TRANSFORMATION-LINK", 1, 0, false);
            declareFunction("transformation_link_hl_module", "TRANSFORMATION-LINK-HL-MODULE", 1, 0, false);
            declareFunction("transformation_link_bindings", "TRANSFORMATION-LINK-BINDINGS", 1, 0, false);
            declareFunction("transformation_link_supports", "TRANSFORMATION-LINK-SUPPORTS", 1, 0, false);
            declareFunction("transformation_link_rule_assertion", "TRANSFORMATION-LINK-RULE-ASSERTION", 1, 0, false);
            declareFunction("transformation_link_more_supports", "TRANSFORMATION-LINK-MORE-SUPPORTS", 1, 0, false);
            declareFunction("transformation_link_non_explanatory_subquery", "TRANSFORMATION-LINK-NON-EXPLANATORY-SUBQUERY", 1, 0, false);
            declareFunction("set_transformation_link_hl_module", "SET-TRANSFORMATION-LINK-HL-MODULE", 2, 0, false);
            declareFunction("set_transformation_link_bindings", "SET-TRANSFORMATION-LINK-BINDINGS", 2, 0, false);
            declareFunction("set_transformation_link_supports", "SET-TRANSFORMATION-LINK-SUPPORTS", 2, 0, false);
            declareFunction("set_transformation_link_non_explanatory_subquery", "SET-TRANSFORMATION-LINK-NON-EXPLANATORY-SUBQUERY", 2, 0, false);
            declareFunction("transformation_link_tactic", "TRANSFORMATION-LINK-TACTIC", 1, 0, false);
            declareFunction("transformation_link_pragmatic_requirements", "TRANSFORMATION-LINK-PRAGMATIC-REQUIREMENTS", 1, 0, false);
            declareFunction("transformation_link_supporting_mapped_problem", "TRANSFORMATION-LINK-SUPPORTING-MAPPED-PROBLEM", 1, 0, false);
            declareFunction("transformation_link_supporting_problem", "TRANSFORMATION-LINK-SUPPORTING-PROBLEM", 1, 0, false);
            declareFunction("transformation_link_supporting_variable_map", "TRANSFORMATION-LINK-SUPPORTING-VARIABLE-MAP", 1, 0, false);
            declareFunction("transformation_link_transformation_mt", "TRANSFORMATION-LINK-TRANSFORMATION-MT", 1, 0, false);
            declareFunction("transformation_link_supporting_problem_wholly_explanatoryP", "TRANSFORMATION-LINK-SUPPORTING-PROBLEM-WHOLLY-EXPLANATORY?", 1, 0, false);
            declareFunction("transformed_problem_using_rule", "TRANSFORMED-PROBLEM-USING-RULE", 2, 0, false);
            declareFunction("transformed_problem_using_rule_and_hl_module", "TRANSFORMED-PROBLEM-USING-RULE-AND-HL-MODULE", 3, 0, false);
            declareFunction("transformation_link_rule_bindings_to_closed", "TRANSFORMATION-LINK-RULE-BINDINGS-TO-CLOSED", 1, 0, false);
            declareFunction("transformation_rule_bindings_to_closed", "TRANSFORMATION-RULE-BINDINGS-TO-CLOSED", 1, 0, false);
            declareFunction("transformation_link_el_bindings", "TRANSFORMATION-LINK-EL-BINDINGS", 1, 0, false);
            declareFunction("canonicalize_bindings_wrt_el_vars", "CANONICALIZE-BINDINGS-WRT-EL-VARS", 1, 0, false);
            declareFunction("transformation_link_motivated_residual_transformation_links", "TRANSFORMATION-LINK-MOTIVATED-RESIDUAL-TRANSFORMATION-LINKS", 1, 0, false);
            declareFunction("transformation_link_motivated_residual_transformation_link_count", "TRANSFORMATION-LINK-MOTIVATED-RESIDUAL-TRANSFORMATION-LINK-COUNT", 1, 0, false);
            declareFunction("transformation_link_good_motivated_residual_transformation_link_count", "TRANSFORMATION-LINK-GOOD-MOTIVATED-RESIDUAL-TRANSFORMATION-LINK-COUNT", 1, 0, false);
            declareMacro("with_problem_store_transformation_assumptions", "WITH-PROBLEM-STORE-TRANSFORMATION-ASSUMPTIONS");
            declareFunction("meta_transformation_tactic_p", "META-TRANSFORMATION-TACTIC-P", 1, 0, false);
            declareFunction("add_tactic_to_determine_new_literal_transformation_tactics", "ADD-TACTIC-TO-DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS", 4, 0, false);
            declareFunction("inference_backchain_forbidden_asentP", "INFERENCE-BACKCHAIN-FORBIDDEN-ASENT?", 2, 0, false);
            declareFunction("new_meta_transformation_tactic", "NEW-META-TRANSFORMATION-TACTIC", 3, 0, false);
            declareFunction("compute_strategic_properties_of_meta_transformation_tactic", "COMPUTE-STRATEGIC-PROPERTIES-OF-META-TRANSFORMATION-TACTIC", 2, 0, false);
            declareFunction("transformation_link_p", "TRANSFORMATION-LINK-P", 1, 0, false);
            declareFunction("transformation_tactic_p", "TRANSFORMATION-TACTIC-P", 1, 0, false);
            new inference_worker_transformation.$transformation_tactic_p$UnaryFunction();
            declareFunction("transformation_tactic_rule", "TRANSFORMATION-TACTIC-RULE", 1, 0, false);
            declareFunction("transformation_rule_tactic_p", "TRANSFORMATION-RULE-TACTIC-P", 1, 0, false);
            declareFunction("transformation_generator_tactic_p", "TRANSFORMATION-GENERATOR-TACTIC-P", 1, 0, false);
            declareFunction("transformation_generator_tactic_lookahead_rule", "TRANSFORMATION-GENERATOR-TACTIC-LOOKAHEAD-RULE", 1, 0, false);
            declareFunction("transformation_tactic_lookahead_rule", "TRANSFORMATION-TACTIC-LOOKAHEAD-RULE", 1, 0, false);
            declareFunction("transformation_proof_p", "TRANSFORMATION-PROOF-P", 1, 0, false);
            declareFunction("transformation_proof_rule_assertion", "TRANSFORMATION-PROOF-RULE-ASSERTION", 1, 0, false);
            declareFunction("transformation_proof_additional_supports", "TRANSFORMATION-PROOF-ADDITIONAL-SUPPORTS", 1, 0, false);
            declareFunction("transformation_proof_subproof", "TRANSFORMATION-PROOF-SUBPROOF", 1, 0, false);
            declareFunction("generalized_transformation_link_p", "GENERALIZED-TRANSFORMATION-LINK-P", 1, 0, false);
            declareFunction("generalized_transformation_link_rule_assertion", "GENERALIZED-TRANSFORMATION-LINK-RULE-ASSERTION", 1, 0, false);
            declareFunction("generalized_transformation_link_unaffected_by_exceptionsP", "GENERALIZED-TRANSFORMATION-LINK-UNAFFECTED-BY-EXCEPTIONS?", 1, 0, false);
            declareFunction("generalized_transformation_proof_p", "GENERALIZED-TRANSFORMATION-PROOF-P", 1, 0, false);
            declareFunction("generalized_transformation_proof_rule_assertion", "GENERALIZED-TRANSFORMATION-PROOF-RULE-ASSERTION", 1, 0, false);
            declareFunction("generalized_transformation_proof_rule_bindings", "GENERALIZED-TRANSFORMATION-PROOF-RULE-BINDINGS", 1, 0, false);
            declareFunction("generalized_transformation_proof_transformation_link", "GENERALIZED-TRANSFORMATION-PROOF-TRANSFORMATION-LINK", 1, 0, false);
            declareFunction("determine_new_literal_transformation_tactics", "DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS", 3, 0, false);
            declareFunction("determine_new_literal_transformation_tactics_int", "DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS-INT", 3, 1, false);
            declareFunction("determine_rules_for_literal_transformation_tactics", "DETERMINE-RULES-FOR-LITERAL-TRANSFORMATION-TACTICS", 3, 0, false);
            declareFunction("problem_inference_and_non_continuable_problem_store_private", "PROBLEM-INFERENCE-AND-NON-CONTINUABLE-PROBLEM-STORE-PRIVATE", 1, 0, false);
            declareFunction("single_literal_problem_candidate_transformation_tactic_specs", "SINGLE-LITERAL-PROBLEM-CANDIDATE-TRANSFORMATION-TACTIC-SPECS", 1, 0, false);
            declareFunction("determine_literal_transformation_tactic_specs", "DETERMINE-LITERAL-TRANSFORMATION-TACTIC-SPECS", 3, 0, false);
            declareFunction("determine_literal_transformation_tactic_specs_int", "DETERMINE-LITERAL-TRANSFORMATION-TACTIC-SPECS-INT", 4, 0, false);
            declareFunction("literal_level_transformation_tactic_p", "LITERAL-LEVEL-TRANSFORMATION-TACTIC-P", 1, 0, false);
            declareFunction("maybe_new_transformation_link", "MAYBE-NEW-TRANSFORMATION-LINK", 7, 0, false);
            declareFunction("recompute_thrown_away_due_to_new_transformation_link", "RECOMPUTE-THROWN-AWAY-DUE-TO-NEW-TRANSFORMATION-LINK", 1, 0, false);
            declareFunction("new_transformation_tactic", "NEW-TRANSFORMATION-TACTIC", 4, 0, false);
            declareFunction("compute_strategic_properties_of_transformation_tactic", "COMPUTE-STRATEGIC-PROPERTIES-OF-TRANSFORMATION-TACTIC", 2, 0, false);
            declareMacro("with_transformation_tactic_execution_assumptions", "WITH-TRANSFORMATION-TACTIC-EXECUTION-ASSUMPTIONS");
            declareFunction("execute_literal_level_transformation_tactic", "EXECUTE-LITERAL-LEVEL-TRANSFORMATION-TACTIC", 4, 0, false);
            declareFunction("maybe_make_transformation_tactic_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-TACTIC-PROGRESS-ITERATOR", 3, 0, false);
            declareFunction("maybe_make_meta_transformation_progress_iterator", "MAYBE-MAKE-META-TRANSFORMATION-PROGRESS-ITERATOR", 3, 0, false);
            declareFunction("maybe_make_transformation_rule_select_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-RULE-SELECT-PROGRESS-ITERATOR", 2, 0, false);
            declareFunction("new_transformation_rule_select_progress_iterator", "NEW-TRANSFORMATION-RULE-SELECT-PROGRESS-ITERATOR", 2, 0, false);
            declareFunction("handle_one_transformation_tactic_rule_select_result", "HANDLE-ONE-TRANSFORMATION-TACTIC-RULE-SELECT-RESULT", 2, 0, false);
            declareFunction("maybe_make_transformation_expand_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-EXPAND-PROGRESS-ITERATOR", 2, 0, false);
            declareFunction("handle_transformation_add_node_for_expand_results", "HANDLE-TRANSFORMATION-ADD-NODE-FOR-EXPAND-RESULTS", 6, 0, false);
            declareFunction("rule_assertion_worth_adding_type_constraintsP", "RULE-ASSERTION-WORTH-ADDING-TYPE-CONSTRAINTS?", 1, 0, false);
            declareFunction("transformation_additional_dont_care_constraints", "TRANSFORMATION-ADDITIONAL-DONT-CARE-CONSTRAINTS", 4, 0, false);
            declareFunction("nmerge_dnf", "NMERGE-DNF", 2, 0, false);
            declareFunction("merge_dnf", "MERGE-DNF", 2, 0, false);
            declareFunction("complete_execution_of_transformation_tactic", "COMPLETE-EXECUTION-OF-TRANSFORMATION-TACTIC", 6, 0, false);
            declareFunction("compute_transformation_non_explanatory_subquery", "COMPUTE-TRANSFORMATION-NON-EXPLANATORY-SUBQUERY", 5, 0, false);
            declareFunction("potentially_wf_transformation_dependent_query", "POTENTIALLY-WF-TRANSFORMATION-DEPENDENT-QUERY", 2, 0, false);
            declareFunction("potentially_wf_restricted_transformation_dependent_asent", "POTENTIALLY-WF-RESTRICTED-TRANSFORMATION-DEPENDENT-ASENT", 3, 0, false);
            declareFunction("syntactically_valid_asent", "SYNTACTICALLY-VALID-ASENT", 1, 0, false);
            declareFunction("syntactically_valid_contextualized_term_of_unit_asent", "SYNTACTICALLY-VALID-CONTEXTUALIZED-TERM-OF-UNIT-ASENT", 2, 0, false);
            declareFunction("new_transformation_proof", "NEW-TRANSFORMATION-PROOF", 3, 0, false);
            declareFunction("compute_canonical_transformation_proof_bindings", "COMPUTE-CANONICAL-TRANSFORMATION-PROOF-BINDINGS", 3, 0, false);
            declareFunction("unification_dependent_dnf_to_transformation_dependent_dnf", "UNIFICATION-DEPENDENT-DNF-TO-TRANSFORMATION-DEPENDENT-DNF", 1, 0, false);
            declareFunction("unification_bindings_to_transformation_bindings", "UNIFICATION-BINDINGS-TO-TRANSFORMATION-BINDINGS", 1, 0, false);
            declareFunction("swap_variable_spaces_of_unification_bindings", "SWAP-VARIABLE-SPACES-OF-UNIFICATION-BINDINGS", 1, 0, false);
            declareFunction("transformation_proof_rule_bindings", "TRANSFORMATION-PROOF-RULE-BINDINGS", 1, 0, false);
            declareFunction("compute_transformation_link_rule_bindings", "COMPUTE-TRANSFORMATION-LINK-RULE-BINDINGS", 2, 0, false);
            declareFunction("transformation_proof_rule_el_bindings", "TRANSFORMATION-PROOF-RULE-EL-BINDINGS", 1, 0, false);
            declareFunction("transformation_proof_el_bindings", "TRANSFORMATION-PROOF-EL-BINDINGS", 1, 0, false);
            declareFunction("unify_transformation_and_subproof_bindings", "UNIFY-TRANSFORMATION-AND-SUBPROOF-BINDINGS", 2, 0, false);
            declareFunction("extended_supported_problem_bindings_to_supported_problem_bindings", "EXTENDED-SUPPORTED-PROBLEM-BINDINGS-TO-SUPPORTED-PROBLEM-BINDINGS", 1, 0, false);
            declareFunction("supported_problem_variable_p", "SUPPORTED-PROBLEM-VARIABLE-P", 1, 0, false);
            declareFunction("extended_supported_problem_bindings_to_rule_bindings", "EXTENDED-SUPPORTED-PROBLEM-BINDINGS-TO-RULE-BINDINGS", 1, 0, false);
            declareFunction("rule_assertion_variable_p", "RULE-ASSERTION-VARIABLE-P", 1, 0, false);
            declareFunction("rule_assertion_variable_map", "RULE-ASSERTION-VARIABLE-MAP", 1, 0, false);
            declareFunction("rule_assertion_base_variable_map", "RULE-ASSERTION-BASE-VARIABLE-MAP", 1, 0, false);
            declareFunction("rule_assertion_has_some_pragmatic_requirementP", "RULE-ASSERTION-HAS-SOME-PRAGMATIC-REQUIREMENT?", 1, 1, false);
            declareFunction("backward_rule_pragmatic_dnf", "BACKWARD-RULE-PRAGMATIC-DNF", 1, 1, false);
            declareFunction("forward_rule_pragmatic_dnf", "FORWARD-RULE-PRAGMATIC-DNF", 2, 0, false);
            declareFunction("rule_assertion_pragmatic_requirements_dnf", "RULE-ASSERTION-PRAGMATIC-REQUIREMENTS-DNF", 1, 1, false);
            declareFunction("forward_rule_non_trigger_literals", "FORWARD-RULE-NON-TRIGGER-LITERALS", 2, 0, false);
            declareFunction("forward_rule_trigger_literals", "FORWARD-RULE-TRIGGER-LITERALS", 2, 0, false);
            declareFunction("merge_pragmatic_requirement", "MERGE-PRAGMATIC-REQUIREMENT", 3, 0, false);
            declareFunction("compute_pragmatic_literal_for_merge", "COMPUTE-PRAGMATIC-LITERAL-FOR-MERGE", 3, 0, false);
            declareFunction("hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf", "HL-VARIABLE-NOT-MENTIONED-IN-RULE-DNF-BUT-MENTIONED-IN-MERGE-DNF", 1, 0, false);
            new inference_worker_transformation.$hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf$UnaryFunction();
            declareFunction("bubble_up_proof_to_transformation_link", "BUBBLE-UP-PROOF-TO-TRANSFORMATION-LINK", 3, 0, false);
            declareFunction("transformation_proof_abnormalP_internal", "TRANSFORMATION-PROOF-ABNORMAL?-INTERNAL", 1, 0, false);
            declareFunction("transformation_proof_abnormalP", "TRANSFORMATION-PROOF-ABNORMAL?", 1, 0, false);
            declareFunction("transformation_proof_abnormal_intP", "TRANSFORMATION-PROOF-ABNORMAL-INT?", 1, 0, false);
            declareFunction("proof_depends_on_excepted_assertionP", "PROOF-DEPENDS-ON-EXCEPTED-ASSERTION?", 1, 0, false);
            declareFunction("supports_contain_excepted_assertionP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION?", 1, 1, false);
            declareFunction("supports_contain_excepted_assertion_in_mtP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION-IN-MT?", 2, 1, false);
            declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS", 2, 0, false);
            declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS-MEMOIZED-INTERNAL", 2, 0, false);
            declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums_memoized", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS-MEMOIZED", 2, 0, false);
            declareFunction("inference_backchain_forbidden_unless_arg_chosen_asentP", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT?", 2, 0, false);
            declareFunction("inference_backchain_forbidden_unless_arg_chosen_asent_variables", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT-VARIABLES", 2, 0, false);
            declareFunction("inference_backchain_forbidden_unless_arg_chosen_asent_variables_int", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT-VARIABLES-INT", 3, 0, false);
            declareFunction("genl_rules_enabledP", "GENL-RULES-ENABLED?", 0, 0, false);
            declareFunction("genl_rules", "GENL-RULES", 1, 1, false);
            declareFunction("max_rules", "MAX-RULES", 1, 1, false);
        }
        if (SubLFiles.USE_V2) {
            declareFunction("inference_excepted_assertionP", "INFERENCE-EXCEPTED-ASSERTION?", 1, 0, false);
            declareFunction("memoized_inference_excepted_assertionP_internal", "MEMOIZED-INFERENCE-EXCEPTED-ASSERTION?-INTERNAL", 2, 0, false);
            declareFunction("memoized_inference_excepted_assertionP", "MEMOIZED-INFERENCE-EXCEPTED-ASSERTION?", 2, 0, false);
            declareFunction("supports_contain_excepted_assertionP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION?", 1, 0, false);
            declareFunction("supports_contain_excepted_assertion_in_mtP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION-IN-MT?", 2, 0, false);
        }
        return NIL;
    }

    public static SubLObject declare_inference_worker_transformation_file_Previous() {
        declareFunction("transformation_link_data_print_function_trampoline", "TRANSFORMATION-LINK-DATA-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("transformation_link_data_p", "TRANSFORMATION-LINK-DATA-P", 1, 0, false);
        new inference_worker_transformation.$transformation_link_data_p$UnaryFunction();
        declareFunction("trans_link_data_hl_module", "TRANS-LINK-DATA-HL-MODULE", 1, 0, false);
        declareFunction("trans_link_data_bindings", "TRANS-LINK-DATA-BINDINGS", 1, 0, false);
        declareFunction("trans_link_data_supports", "TRANS-LINK-DATA-SUPPORTS", 1, 0, false);
        declareFunction("trans_link_data_non_explanatory_subquery", "TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY", 1, 0, false);
        declareFunction("_csetf_trans_link_data_hl_module", "_CSETF-TRANS-LINK-DATA-HL-MODULE", 2, 0, false);
        declareFunction("_csetf_trans_link_data_bindings", "_CSETF-TRANS-LINK-DATA-BINDINGS", 2, 0, false);
        declareFunction("_csetf_trans_link_data_supports", "_CSETF-TRANS-LINK-DATA-SUPPORTS", 2, 0, false);
        declareFunction("_csetf_trans_link_data_non_explanatory_subquery", "_CSETF-TRANS-LINK-DATA-NON-EXPLANATORY-SUBQUERY", 2, 0, false);
        declareFunction("make_transformation_link_data", "MAKE-TRANSFORMATION-LINK-DATA", 0, 1, false);
        declareFunction("visit_defstruct_transformation_link_data", "VISIT-DEFSTRUCT-TRANSFORMATION-LINK-DATA", 2, 0, false);
        declareFunction("visit_defstruct_object_transformation_link_data_method", "VISIT-DEFSTRUCT-OBJECT-TRANSFORMATION-LINK-DATA-METHOD", 2, 0, false);
        declareFunction("new_transformation_link", "NEW-TRANSFORMATION-LINK", 7, 0, false);
        declareFunction("new_transformation_link_int", "NEW-TRANSFORMATION-LINK-INT", 5, 0, false);
        declareFunction("new_transformation_link_data", "NEW-TRANSFORMATION-LINK-DATA", 1, 0, false);
        declareFunction("destroy_transformation_link", "DESTROY-TRANSFORMATION-LINK", 1, 0, false);
        declareFunction("transformation_link_hl_module", "TRANSFORMATION-LINK-HL-MODULE", 1, 0, false);
        declareFunction("transformation_link_bindings", "TRANSFORMATION-LINK-BINDINGS", 1, 0, false);
        declareFunction("transformation_link_supports", "TRANSFORMATION-LINK-SUPPORTS", 1, 0, false);
        declareFunction("transformation_link_rule_assertion", "TRANSFORMATION-LINK-RULE-ASSERTION", 1, 0, false);
        declareFunction("transformation_link_more_supports", "TRANSFORMATION-LINK-MORE-SUPPORTS", 1, 0, false);
        declareFunction("transformation_link_non_explanatory_subquery", "TRANSFORMATION-LINK-NON-EXPLANATORY-SUBQUERY", 1, 0, false);
        declareFunction("set_transformation_link_hl_module", "SET-TRANSFORMATION-LINK-HL-MODULE", 2, 0, false);
        declareFunction("set_transformation_link_bindings", "SET-TRANSFORMATION-LINK-BINDINGS", 2, 0, false);
        declareFunction("set_transformation_link_supports", "SET-TRANSFORMATION-LINK-SUPPORTS", 2, 0, false);
        declareFunction("set_transformation_link_non_explanatory_subquery", "SET-TRANSFORMATION-LINK-NON-EXPLANATORY-SUBQUERY", 2, 0, false);
        declareFunction("transformation_link_tactic", "TRANSFORMATION-LINK-TACTIC", 1, 0, false);
        declareFunction("transformation_link_pragmatic_requirements", "TRANSFORMATION-LINK-PRAGMATIC-REQUIREMENTS", 1, 0, false);
        declareFunction("transformation_link_supporting_mapped_problem", "TRANSFORMATION-LINK-SUPPORTING-MAPPED-PROBLEM", 1, 0, false);
        declareFunction("transformation_link_supporting_problem", "TRANSFORMATION-LINK-SUPPORTING-PROBLEM", 1, 0, false);
        declareFunction("transformation_link_supporting_variable_map", "TRANSFORMATION-LINK-SUPPORTING-VARIABLE-MAP", 1, 0, false);
        declareFunction("transformation_link_transformation_mt", "TRANSFORMATION-LINK-TRANSFORMATION-MT", 1, 0, false);
        declareFunction("transformation_link_supporting_problem_wholly_explanatoryP", "TRANSFORMATION-LINK-SUPPORTING-PROBLEM-WHOLLY-EXPLANATORY?", 1, 0, false);
        declareFunction("transformed_problem_using_rule", "TRANSFORMED-PROBLEM-USING-RULE", 2, 0, false);
        declareFunction("transformed_problem_using_rule_and_hl_module", "TRANSFORMED-PROBLEM-USING-RULE-AND-HL-MODULE", 3, 0, false);
        declareFunction("transformation_link_rule_bindings_to_closed", "TRANSFORMATION-LINK-RULE-BINDINGS-TO-CLOSED", 1, 0, false);
        declareFunction("transformation_rule_bindings_to_closed", "TRANSFORMATION-RULE-BINDINGS-TO-CLOSED", 1, 0, false);
        declareFunction("transformation_link_el_bindings", "TRANSFORMATION-LINK-EL-BINDINGS", 1, 0, false);
        declareFunction("canonicalize_bindings_wrt_el_vars", "CANONICALIZE-BINDINGS-WRT-EL-VARS", 1, 0, false);
        declareFunction("transformation_link_motivated_residual_transformation_links", "TRANSFORMATION-LINK-MOTIVATED-RESIDUAL-TRANSFORMATION-LINKS", 1, 0, false);
        declareFunction("transformation_link_motivated_residual_transformation_link_count", "TRANSFORMATION-LINK-MOTIVATED-RESIDUAL-TRANSFORMATION-LINK-COUNT", 1, 0, false);
        declareFunction("transformation_link_good_motivated_residual_transformation_link_count", "TRANSFORMATION-LINK-GOOD-MOTIVATED-RESIDUAL-TRANSFORMATION-LINK-COUNT", 1, 0, false);
        declareMacro("with_problem_store_transformation_assumptions", "WITH-PROBLEM-STORE-TRANSFORMATION-ASSUMPTIONS");
        declareFunction("meta_transformation_tactic_p", "META-TRANSFORMATION-TACTIC-P", 1, 0, false);
        declareFunction("add_tactic_to_determine_new_literal_transformation_tactics", "ADD-TACTIC-TO-DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS", 4, 0, false);
        declareFunction("inference_backchain_forbidden_asentP", "INFERENCE-BACKCHAIN-FORBIDDEN-ASENT?", 2, 0, false);
        declareFunction("new_meta_transformation_tactic", "NEW-META-TRANSFORMATION-TACTIC", 3, 0, false);
        declareFunction("compute_strategic_properties_of_meta_transformation_tactic", "COMPUTE-STRATEGIC-PROPERTIES-OF-META-TRANSFORMATION-TACTIC", 2, 0, false);
        declareFunction("transformation_link_p", "TRANSFORMATION-LINK-P", 1, 0, false);
        declareFunction("transformation_tactic_p", "TRANSFORMATION-TACTIC-P", 1, 0, false);
        new inference_worker_transformation.$transformation_tactic_p$UnaryFunction();
        declareFunction("transformation_tactic_rule", "TRANSFORMATION-TACTIC-RULE", 1, 0, false);
        declareFunction("transformation_rule_tactic_p", "TRANSFORMATION-RULE-TACTIC-P", 1, 0, false);
        declareFunction("transformation_generator_tactic_p", "TRANSFORMATION-GENERATOR-TACTIC-P", 1, 0, false);
        declareFunction("transformation_generator_tactic_lookahead_rule", "TRANSFORMATION-GENERATOR-TACTIC-LOOKAHEAD-RULE", 1, 0, false);
        declareFunction("transformation_tactic_lookahead_rule", "TRANSFORMATION-TACTIC-LOOKAHEAD-RULE", 1, 0, false);
        declareFunction("transformation_proof_p", "TRANSFORMATION-PROOF-P", 1, 0, false);
        declareFunction("transformation_proof_rule_assertion", "TRANSFORMATION-PROOF-RULE-ASSERTION", 1, 0, false);
        declareFunction("transformation_proof_additional_supports", "TRANSFORMATION-PROOF-ADDITIONAL-SUPPORTS", 1, 0, false);
        declareFunction("transformation_proof_subproof", "TRANSFORMATION-PROOF-SUBPROOF", 1, 0, false);
        declareFunction("generalized_transformation_link_p", "GENERALIZED-TRANSFORMATION-LINK-P", 1, 0, false);
        declareFunction("generalized_transformation_link_rule_assertion", "GENERALIZED-TRANSFORMATION-LINK-RULE-ASSERTION", 1, 0, false);
        declareFunction("generalized_transformation_link_unaffected_by_exceptionsP", "GENERALIZED-TRANSFORMATION-LINK-UNAFFECTED-BY-EXCEPTIONS?", 1, 0, false);
        declareFunction("generalized_transformation_proof_p", "GENERALIZED-TRANSFORMATION-PROOF-P", 1, 0, false);
        declareFunction("generalized_transformation_proof_rule_assertion", "GENERALIZED-TRANSFORMATION-PROOF-RULE-ASSERTION", 1, 0, false);
        declareFunction("generalized_transformation_proof_rule_bindings", "GENERALIZED-TRANSFORMATION-PROOF-RULE-BINDINGS", 1, 0, false);
        declareFunction("generalized_transformation_proof_transformation_link", "GENERALIZED-TRANSFORMATION-PROOF-TRANSFORMATION-LINK", 1, 0, false);
        declareFunction("determine_new_literal_transformation_tactics", "DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS", 3, 0, false);
        declareFunction("determine_new_literal_transformation_tactics_int", "DETERMINE-NEW-LITERAL-TRANSFORMATION-TACTICS-INT", 3, 1, false);
        declareFunction("determine_rules_for_literal_transformation_tactics", "DETERMINE-RULES-FOR-LITERAL-TRANSFORMATION-TACTICS", 3, 0, false);
        declareFunction("problem_inference_and_non_continuable_problem_store_private", "PROBLEM-INFERENCE-AND-NON-CONTINUABLE-PROBLEM-STORE-PRIVATE", 1, 0, false);
        declareFunction("single_literal_problem_candidate_transformation_tactic_specs", "SINGLE-LITERAL-PROBLEM-CANDIDATE-TRANSFORMATION-TACTIC-SPECS", 1, 0, false);
        declareFunction("determine_literal_transformation_tactic_specs", "DETERMINE-LITERAL-TRANSFORMATION-TACTIC-SPECS", 3, 0, false);
        declareFunction("determine_literal_transformation_tactic_specs_int", "DETERMINE-LITERAL-TRANSFORMATION-TACTIC-SPECS-INT", 4, 0, false);
        declareFunction("literal_level_transformation_tactic_p", "LITERAL-LEVEL-TRANSFORMATION-TACTIC-P", 1, 0, false);
        declareFunction("maybe_new_transformation_link", "MAYBE-NEW-TRANSFORMATION-LINK", 7, 0, false);
        declareFunction("recompute_thrown_away_due_to_new_transformation_link", "RECOMPUTE-THROWN-AWAY-DUE-TO-NEW-TRANSFORMATION-LINK", 1, 0, false);
        declareFunction("new_transformation_tactic", "NEW-TRANSFORMATION-TACTIC", 4, 0, false);
        declareFunction("compute_strategic_properties_of_transformation_tactic", "COMPUTE-STRATEGIC-PROPERTIES-OF-TRANSFORMATION-TACTIC", 2, 0, false);
        declareMacro("with_transformation_tactic_execution_assumptions", "WITH-TRANSFORMATION-TACTIC-EXECUTION-ASSUMPTIONS");
        declareFunction("execute_literal_level_transformation_tactic", "EXECUTE-LITERAL-LEVEL-TRANSFORMATION-TACTIC", 4, 0, false);
        declareFunction("maybe_make_transformation_tactic_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-TACTIC-PROGRESS-ITERATOR", 3, 0, false);
        declareFunction("maybe_make_meta_transformation_progress_iterator", "MAYBE-MAKE-META-TRANSFORMATION-PROGRESS-ITERATOR", 3, 0, false);
        declareFunction("maybe_make_transformation_rule_select_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-RULE-SELECT-PROGRESS-ITERATOR", 2, 0, false);
        declareFunction("new_transformation_rule_select_progress_iterator", "NEW-TRANSFORMATION-RULE-SELECT-PROGRESS-ITERATOR", 2, 0, false);
        declareFunction("handle_one_transformation_tactic_rule_select_result", "HANDLE-ONE-TRANSFORMATION-TACTIC-RULE-SELECT-RESULT", 2, 0, false);
        declareFunction("maybe_make_transformation_expand_progress_iterator", "MAYBE-MAKE-TRANSFORMATION-EXPAND-PROGRESS-ITERATOR", 2, 0, false);
        declareFunction("handle_transformation_add_node_for_expand_results", "HANDLE-TRANSFORMATION-ADD-NODE-FOR-EXPAND-RESULTS", 6, 0, false);
        declareFunction("rule_assertion_worth_adding_type_constraintsP", "RULE-ASSERTION-WORTH-ADDING-TYPE-CONSTRAINTS?", 1, 0, false);
        declareFunction("transformation_additional_dont_care_constraints", "TRANSFORMATION-ADDITIONAL-DONT-CARE-CONSTRAINTS", 4, 0, false);
        declareFunction("nmerge_dnf", "NMERGE-DNF", 2, 0, false);
        declareFunction("merge_dnf", "MERGE-DNF", 2, 0, false);
        declareFunction("complete_execution_of_transformation_tactic", "COMPLETE-EXECUTION-OF-TRANSFORMATION-TACTIC", 6, 0, false);
        declareFunction("compute_transformation_non_explanatory_subquery", "COMPUTE-TRANSFORMATION-NON-EXPLANATORY-SUBQUERY", 5, 0, false);
        declareFunction("potentially_wf_transformation_dependent_query", "POTENTIALLY-WF-TRANSFORMATION-DEPENDENT-QUERY", 2, 0, false);
        declareFunction("potentially_wf_restricted_transformation_dependent_asent", "POTENTIALLY-WF-RESTRICTED-TRANSFORMATION-DEPENDENT-ASENT", 3, 0, false);
        declareFunction("syntactically_valid_asent", "SYNTACTICALLY-VALID-ASENT", 1, 0, false);
        declareFunction("syntactically_valid_contextualized_term_of_unit_asent", "SYNTACTICALLY-VALID-CONTEXTUALIZED-TERM-OF-UNIT-ASENT", 2, 0, false);
        declareFunction("new_transformation_proof", "NEW-TRANSFORMATION-PROOF", 3, 0, false);
        declareFunction("compute_canonical_transformation_proof_bindings", "COMPUTE-CANONICAL-TRANSFORMATION-PROOF-BINDINGS", 3, 0, false);
        declareFunction("unification_dependent_dnf_to_transformation_dependent_dnf", "UNIFICATION-DEPENDENT-DNF-TO-TRANSFORMATION-DEPENDENT-DNF", 1, 0, false);
        declareFunction("unification_bindings_to_transformation_bindings", "UNIFICATION-BINDINGS-TO-TRANSFORMATION-BINDINGS", 1, 0, false);
        declareFunction("swap_variable_spaces_of_unification_bindings", "SWAP-VARIABLE-SPACES-OF-UNIFICATION-BINDINGS", 1, 0, false);
        declareFunction("transformation_proof_rule_bindings", "TRANSFORMATION-PROOF-RULE-BINDINGS", 1, 0, false);
        declareFunction("compute_transformation_link_rule_bindings", "COMPUTE-TRANSFORMATION-LINK-RULE-BINDINGS", 2, 0, false);
        declareFunction("transformation_proof_rule_el_bindings", "TRANSFORMATION-PROOF-RULE-EL-BINDINGS", 1, 0, false);
        declareFunction("transformation_proof_el_bindings", "TRANSFORMATION-PROOF-EL-BINDINGS", 1, 0, false);
        declareFunction("unify_transformation_and_subproof_bindings", "UNIFY-TRANSFORMATION-AND-SUBPROOF-BINDINGS", 2, 0, false);
        declareFunction("extended_supported_problem_bindings_to_supported_problem_bindings", "EXTENDED-SUPPORTED-PROBLEM-BINDINGS-TO-SUPPORTED-PROBLEM-BINDINGS", 1, 0, false);
        declareFunction("supported_problem_variable_p", "SUPPORTED-PROBLEM-VARIABLE-P", 1, 0, false);
        declareFunction("extended_supported_problem_bindings_to_rule_bindings", "EXTENDED-SUPPORTED-PROBLEM-BINDINGS-TO-RULE-BINDINGS", 1, 0, false);
        declareFunction("rule_assertion_variable_p", "RULE-ASSERTION-VARIABLE-P", 1, 0, false);
        declareFunction("rule_assertion_variable_map", "RULE-ASSERTION-VARIABLE-MAP", 1, 0, false);
        declareFunction("rule_assertion_base_variable_map", "RULE-ASSERTION-BASE-VARIABLE-MAP", 1, 0, false);
        declareFunction("rule_assertion_has_some_pragmatic_requirementP", "RULE-ASSERTION-HAS-SOME-PRAGMATIC-REQUIREMENT?", 1, 1, false);
        declareFunction("backward_rule_pragmatic_dnf", "BACKWARD-RULE-PRAGMATIC-DNF", 1, 1, false);
        declareFunction("forward_rule_pragmatic_dnf", "FORWARD-RULE-PRAGMATIC-DNF", 2, 0, false);
        declareFunction("rule_assertion_pragmatic_requirements_dnf", "RULE-ASSERTION-PRAGMATIC-REQUIREMENTS-DNF", 1, 1, false);
        declareFunction("forward_rule_non_trigger_literals", "FORWARD-RULE-NON-TRIGGER-LITERALS", 2, 0, false);
        declareFunction("forward_rule_trigger_literals", "FORWARD-RULE-TRIGGER-LITERALS", 2, 0, false);
        declareFunction("merge_pragmatic_requirement", "MERGE-PRAGMATIC-REQUIREMENT", 3, 0, false);
        declareFunction("compute_pragmatic_literal_for_merge", "COMPUTE-PRAGMATIC-LITERAL-FOR-MERGE", 3, 0, false);
        declareFunction("hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf", "HL-VARIABLE-NOT-MENTIONED-IN-RULE-DNF-BUT-MENTIONED-IN-MERGE-DNF", 1, 0, false);
        new inference_worker_transformation.$hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf$UnaryFunction();
        declareFunction("bubble_up_proof_to_transformation_link", "BUBBLE-UP-PROOF-TO-TRANSFORMATION-LINK", 3, 0, false);
        declareFunction("transformation_proof_abnormalP_internal", "TRANSFORMATION-PROOF-ABNORMAL?-INTERNAL", 1, 0, false);
        declareFunction("transformation_proof_abnormalP", "TRANSFORMATION-PROOF-ABNORMAL?", 1, 0, false);
        declareFunction("transformation_proof_abnormal_intP", "TRANSFORMATION-PROOF-ABNORMAL-INT?", 1, 0, false);
        declareFunction("proof_depends_on_excepted_assertionP", "PROOF-DEPENDS-ON-EXCEPTED-ASSERTION?", 1, 0, false);
        declareFunction("supports_contain_excepted_assertionP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION?", 1, 1, false);
        declareFunction("supports_contain_excepted_assertion_in_mtP", "SUPPORTS-CONTAIN-EXCEPTED-ASSERTION-IN-MT?", 2, 1, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums_memoized_internal", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS-MEMOIZED-INTERNAL", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_argnums_memoized", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ARGNUMS-MEMOIZED", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_asentP", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT?", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_asent_variables", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT-VARIABLES", 2, 0, false);
        declareFunction("inference_backchain_forbidden_unless_arg_chosen_asent_variables_int", "INFERENCE-BACKCHAIN-FORBIDDEN-UNLESS-ARG-CHOSEN-ASENT-VARIABLES-INT", 3, 0, false);
        declareFunction("genl_rules_enabledP", "GENL-RULES-ENABLED?", 0, 0, false);
        declareFunction("genl_rules", "GENL-RULES", 1, 1, false);
        declareFunction("max_rules", "MAX-RULES", 1, 1, false);
        return NIL;
    }

    static private final SubLString $str_alt20$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLString $str_alt30$No_tactic_found_for__S = makeString("No tactic found for ~S");

    static private final SubLList $list_alt31 = cons(makeSymbol("VARIABLE"), makeSymbol("VALUE"));

    static private final SubLList $list_alt32 = list(makeSymbol("STORE"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym33$STORE_VAR = makeUninternedSymbol("STORE-VAR");

    static private final SubLList $list_alt35 = list(makeSymbol("*HL-FAILURE-BACKCHAINING*"), T);

    static private final SubLList $list_alt36 = list(makeSymbol("*UNBOUND-RULE-BACKCHAIN-ENABLED*"), T);

    static private final SubLList $list_alt37 = list(makeSymbol("*EVALUATABLE-BACKCHAIN-ENABLED*"), T);

    static private final SubLSymbol $sym39$PROBLEM_STORE_NEGATION_BY_FAILURE_ = makeSymbol("PROBLEM-STORE-NEGATION-BY-FAILURE?");

    static private final SubLString $str_alt46$generalized_transformation_link_o = makeString("generalized transformation link of unexpected type: ~s");

    static private final SubLString $str_alt47$generalized_transformation_proof_ = makeString("generalized transformation proof of unexpected type: ~s");

    static private final SubLList $list_alt48 = list(makeSymbol("HL-MODULE"), makeSymbol("PRODUCTIVITY"));

    static private final SubLSymbol $sym49$INFERENCE_EXCEPTED_ASSERTION_ = makeSymbol("INFERENCE-EXCEPTED-ASSERTION?");

    static private final SubLSymbol $sym50$MEMOIZED_INFERENCE_EXCEPTED_ASSERTION_ = makeSymbol("MEMOIZED-INFERENCE-EXCEPTED-ASSERTION?");

    public static final SubLSymbol $kw51$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLString $str_alt55$unexpected_tactic_specs_return_ty = makeString("unexpected tactic-specs return type ~a");

    static private final SubLString $str_alt56$pruning__s__s__s = makeString("pruning ~s ~s ~s");

    static private final SubLList $list_alt58 = list(list(makeSymbol("TACTIC"), makeSymbol("MT"), makeSymbol("SENSE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym59$TACTIC_VAR = makeUninternedSymbol("TACTIC-VAR");

    static private final SubLString $str_alt66$time_to_add_meta_transformation_s = makeString("time to add meta-transformation support for ~S");

    static private final SubLString $str_alt68$transformation_tactic__S_already_ = makeString("transformation tactic ~S already has rule ~S");

    static private final SubLList $list_alt72 = list(makeSymbol("MT"), makeSymbol("ASENT"));

    static private final SubLString $str_alt76$Could_not_ground_out__s_and__s = makeString("Could not ground out ~s and ~s");

    static private final SubLString $str_alt77$Could_not_unify_transformation_bi = makeString("Could not unify transformation bindings ~a with subproof bindings ~a");

    static private final SubLSymbol $sym78$RULE_ASSERTION_ = makeSymbol("RULE-ASSERTION?");

    static private final SubLSymbol $sym84$TRANSFORMATION_PROOF_ABNORMAL_ = makeSymbol("TRANSFORMATION-PROOF-ABNORMAL?");

    static private final SubLSymbol $sym87$_ = makeSymbol("<");

    public static final SubLObject init_inference_worker_transformation_file_alt() {
        defconstant("*DTP-TRANSFORMATION-LINK-DATA*", TRANSFORMATION_LINK_DATA);
        deflexical("*DETERMINE-NEW-TRANSFORMATION-TACTICS-MODULE*", NIL != boundp($determine_new_transformation_tactics_module$) ? ((SubLObject) ($determine_new_transformation_tactics_module$.getGlobalValue())) : inference_meta_transformation_module($DETERMINE_NEW_TRANSFORMATION_TACTICS, UNPROVIDED));
        defparameter("*TRANSFORMATION-TACTIC-ITERATION-THRESHOLD*", TWO_INTEGER);
        defparameter("*INFERENCE-TRANSFORMATION-TYPE-CHECKING-ENABLED?*", NIL);
        deflexical("*FORWARD-PRAGMATIC-REQUIREMENT-ENABLED?*", T);
        defparameter("*MERGE-DNF-LAMBDA-VAR*", NIL);
        defparameter("*RULE-DNF-LAMBDA-VAR*", NIL);
        defvar("*GENL-RULES-ENABLED?*", T);
        return NIL;
    }

    public static SubLObject init_inference_worker_transformation_file() {
        if (SubLFiles.USE_V1) {
            defconstant("*DTP-TRANSFORMATION-LINK-DATA*", TRANSFORMATION_LINK_DATA);
            deflexical("*DETERMINE-NEW-TRANSFORMATION-TACTICS-MODULE*", SubLTrampolineFile.maybeDefault($determine_new_transformation_tactics_module$, $determine_new_transformation_tactics_module$, () -> inference_modules.inference_meta_transformation_module($DETERMINE_NEW_TRANSFORMATION_TACTICS, UNPROVIDED)));
            defparameter("*TRANSFORMATION-TACTIC-ITERATION-THRESHOLD*", TWO_INTEGER);
            defparameter("*STITCH-UP-BINDINGS-LOOP?*", T);
            defparameter("*INFERENCE-TRANSFORMATION-TYPE-CHECKING-ENABLED?*", NIL);
            defparameter("*MERGE-DNF-LAMBDA-VAR*", NIL);
            defparameter("*RULE-DNF-LAMBDA-VAR*", NIL);
            defvar("*GENL-RULES-ENABLED?*", T);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*DETERMINE-NEW-TRANSFORMATION-TACTICS-MODULE*", NIL != boundp($determine_new_transformation_tactics_module$) ? ((SubLObject) ($determine_new_transformation_tactics_module$.getGlobalValue())) : inference_meta_transformation_module($DETERMINE_NEW_TRANSFORMATION_TACTICS, UNPROVIDED));
            deflexical("*FORWARD-PRAGMATIC-REQUIREMENT-ENABLED?*", T);
        }
        return NIL;
    }

    public static SubLObject init_inference_worker_transformation_file_Previous() {
        defconstant("*DTP-TRANSFORMATION-LINK-DATA*", TRANSFORMATION_LINK_DATA);
        deflexical("*DETERMINE-NEW-TRANSFORMATION-TACTICS-MODULE*", SubLTrampolineFile.maybeDefault($determine_new_transformation_tactics_module$, $determine_new_transformation_tactics_module$, () -> inference_modules.inference_meta_transformation_module($DETERMINE_NEW_TRANSFORMATION_TACTICS, UNPROVIDED)));
        defparameter("*TRANSFORMATION-TACTIC-ITERATION-THRESHOLD*", TWO_INTEGER);
        defparameter("*STITCH-UP-BINDINGS-LOOP?*", T);
        defparameter("*INFERENCE-TRANSFORMATION-TYPE-CHECKING-ENABLED?*", NIL);
        defparameter("*MERGE-DNF-LAMBDA-VAR*", NIL);
        defparameter("*RULE-DNF-LAMBDA-VAR*", NIL);
        defvar("*GENL-RULES-ENABLED?*", T);
        return NIL;
    }

    public static final SubLObject setup_inference_worker_transformation_file_alt() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_transformation_link_data$.getGlobalValue(), symbol_function(TRANSFORMATION_LINK_DATA_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(TRANS_LINK_DATA_HL_MODULE, _CSETF_TRANS_LINK_DATA_HL_MODULE);
        def_csetf(TRANS_LINK_DATA_BINDINGS, _CSETF_TRANS_LINK_DATA_BINDINGS);
        def_csetf(TRANS_LINK_DATA_SUPPORTS, _CSETF_TRANS_LINK_DATA_SUPPORTS);
        def_csetf(TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY, _CSETF_TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY);
        identity(TRANSFORMATION_LINK_DATA);
        declare_defglobal($determine_new_transformation_tactics_module$);
        memoization_state.note_memoized_function($sym50$MEMOIZED_INFERENCE_EXCEPTED_ASSERTION_);
        memoization_state.note_memoized_function($sym84$TRANSFORMATION_PROOF_ABNORMAL_);
        memoization_state.note_memoized_function(INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED);
        return NIL;
    }

    public static SubLObject setup_inference_worker_transformation_file() {
        if (SubLFiles.USE_V1) {
            register_method($print_object_method_table$.getGlobalValue(), $dtp_transformation_link_data$.getGlobalValue(), symbol_function(TRANSFORMATION_LINK_DATA_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list8);
            def_csetf(TRANS_LINK_DATA_HL_MODULE, _CSETF_TRANS_LINK_DATA_HL_MODULE);
            def_csetf(TRANS_LINK_DATA_BINDINGS, _CSETF_TRANS_LINK_DATA_BINDINGS);
            def_csetf(TRANS_LINK_DATA_SUPPORTS, _CSETF_TRANS_LINK_DATA_SUPPORTS);
            def_csetf(TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY, _CSETF_TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY);
            identity(TRANSFORMATION_LINK_DATA);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_transformation_link_data$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_TRANSFORMATION_LINK_DATA_METHOD));
            declare_defglobal($determine_new_transformation_tactics_module$);
            note_memoized_function($sym93$TRANSFORMATION_PROOF_ABNORMAL_);
            note_memoized_function(INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED);
        }
        if (SubLFiles.USE_V2) {
            memoization_state.note_memoized_function($sym50$MEMOIZED_INFERENCE_EXCEPTED_ASSERTION_);
            memoization_state.note_memoized_function($sym84$TRANSFORMATION_PROOF_ABNORMAL_);
        }
        return NIL;
    }

    public static SubLObject setup_inference_worker_transformation_file_Previous() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_transformation_link_data$.getGlobalValue(), symbol_function(TRANSFORMATION_LINK_DATA_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list8);
        def_csetf(TRANS_LINK_DATA_HL_MODULE, _CSETF_TRANS_LINK_DATA_HL_MODULE);
        def_csetf(TRANS_LINK_DATA_BINDINGS, _CSETF_TRANS_LINK_DATA_BINDINGS);
        def_csetf(TRANS_LINK_DATA_SUPPORTS, _CSETF_TRANS_LINK_DATA_SUPPORTS);
        def_csetf(TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY, _CSETF_TRANS_LINK_DATA_NON_EXPLANATORY_SUBQUERY);
        identity(TRANSFORMATION_LINK_DATA);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_transformation_link_data$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_TRANSFORMATION_LINK_DATA_METHOD));
        declare_defglobal($determine_new_transformation_tactics_module$);
        note_memoized_function($sym93$TRANSFORMATION_PROOF_ABNORMAL_);
        note_memoized_function(INFERENCE_BACKCHAIN_FORBIDDEN_UNLESS_ARG_CHOSEN_ARGNUMS_MEMOIZED);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_inference_worker_transformation_file();
    }

    @Override
    public void initializeVariables() {
        init_inference_worker_transformation_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_inference_worker_transformation_file();
    }

    static {
    }

    public static final class $transformation_link_data_native extends SubLStructNative {
        public SubLObject $hl_module;

        public SubLObject $bindings;

        public SubLObject $supports;

        public SubLObject $non_explanatory_subquery;

        private static final SubLStructDeclNative structDecl;

        public $transformation_link_data_native() {
            inference_worker_transformation.$transformation_link_data_native.this.$hl_module = Lisp.NIL;
            inference_worker_transformation.$transformation_link_data_native.this.$bindings = Lisp.NIL;
            inference_worker_transformation.$transformation_link_data_native.this.$supports = Lisp.NIL;
            inference_worker_transformation.$transformation_link_data_native.this.$non_explanatory_subquery = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return inference_worker_transformation.$transformation_link_data_native.this.$hl_module;
        }

        @Override
        public SubLObject getField3() {
            return inference_worker_transformation.$transformation_link_data_native.this.$bindings;
        }

        @Override
        public SubLObject getField4() {
            return inference_worker_transformation.$transformation_link_data_native.this.$supports;
        }

        @Override
        public SubLObject getField5() {
            return inference_worker_transformation.$transformation_link_data_native.this.$non_explanatory_subquery;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return inference_worker_transformation.$transformation_link_data_native.this.$hl_module = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return inference_worker_transformation.$transformation_link_data_native.this.$bindings = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return inference_worker_transformation.$transformation_link_data_native.this.$supports = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return inference_worker_transformation.$transformation_link_data_native.this.$non_explanatory_subquery = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation.$transformation_link_data_native.class, TRANSFORMATION_LINK_DATA, TRANSFORMATION_LINK_DATA_P, $list2, $list3, new String[]{ "$hl_module", "$bindings", "$supports", "$non_explanatory_subquery" }, $list4, $list5, DEFAULT_STRUCT_PRINT_FUNCTION);
        }
    }

    public static final class $transformation_link_data_p$UnaryFunction extends UnaryFunction {
        public $transformation_link_data_p$UnaryFunction() {
            super(extractFunctionNamed("TRANSFORMATION-LINK-DATA-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return transformation_link_data_p(arg1);
        }
    }

    public static final class $transformation_tactic_p$UnaryFunction extends UnaryFunction {
        public $transformation_tactic_p$UnaryFunction() {
            super(extractFunctionNamed("TRANSFORMATION-TACTIC-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return transformation_tactic_p(arg1);
        }
    }

    public static final class $hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf$UnaryFunction extends UnaryFunction {
        public $hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf$UnaryFunction() {
            super(extractFunctionNamed("HL-VARIABLE-NOT-MENTIONED-IN-RULE-DNF-BUT-MENTIONED-IN-MERGE-DNF"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return hl_variable_not_mentioned_in_rule_dnf_but_mentioned_in_merge_dnf(arg1);
        }
    }
}

/**
 * Total time: 605 ms synthetic
 */
