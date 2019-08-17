/**
 *
 */
/**
 * //
 */
/**
 *
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.el_utilities.el_conjunction_p;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.make_conjunction;
import static com.cyc.cycjava.cycl.el_utilities.make_ist_sentence;
import static com.cyc.cycjava.cycl.el_utilities.make_unary_formula;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.inference.harness.forward;
import com.cyc.cycjava.cycl.inference.harness.hl_prototypes;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class fi_edit extends SubLTranslatedFile implements V10 {
    public static final SubLFile me = new fi_edit();

    public static final String myName = "com.cyc.cycjava_2.cycl.fi_edit";


    // defparameter
    public static final SubLSymbol $within_fi_edit_add_old_exceptP$ = makeSymbol("*WITHIN-FI-EDIT-ADD-OLD-EXCEPT?*");





    private static final SubLString $str2$Expected_a_cons__got__S = makeString("Expected a cons, got ~S");

    private static final SubLString $str3$Expected__default_or__monotonic__ = makeString("Expected :default or :monotonic, got ~S");

    private static final SubLString $str4$Expected_a_direction__got__S = makeString("Expected a direction, got ~S");

    private static final SubLString $str5$some_old_formulas_missing___S = makeString("some old formulas missing: ~S");

    private static final SubLString $str6$unable_to_except___S = makeString("unable to except: ~S");

    private static final SubLString $str7$some_new_formulas_invalid___S = makeString("some new formulas invalid: ~S");

    private static final SubLString $str8$some_new_formulas_failed___S = makeString("some new formulas failed: ~S");

    private static final SubLSymbol $FI_EDIT = makeKeyword("FI-EDIT");

    private static final SubLList $list10 = list(makeSymbol("CNF"), makeSymbol("&OPTIONAL"), makeSymbol("VARIABLE-MAP"), makeSymbol("QUERY-FREE-VARS"));



    private static final SubLObject $$except = reader_make_constant_shell(makeString("except"));

    private static final SubLList $list13 = list(makeKeyword("STRENGTH"), makeKeyword("MONOTONIC"), makeKeyword("DIRECTION"), makeKeyword("BACKWARD"));

    private static final SubLList $list14 = list(makeSymbol("CANON-VERSIONS"), makeSymbol("CANON-MT"));

    private static final SubLList $list15 = list(makeSymbol("CNF"), makeSymbol("&OPTIONAL"), makeSymbol("VARIABLE-MAP"), makeSymbol("&REST"), makeSymbol("REST"));

    private static final SubLList $list16 = list(makeSymbol("CNF"), makeSymbol("MT"));

    private static final SubLSymbol $IGNORE_ERRORS_TARGET = makeKeyword("IGNORE-ERRORS-TARGET");

    private static final SubLSymbol IGNORE_ERRORS_HANDLER = makeSymbol("IGNORE-ERRORS-HANDLER", "SUBLISP");

    private static final SubLSymbol KBEQ = makeSymbol("KBEQ");

    public static SubLObject new_fi_edit_int(SubLObject old_sentence, SubLObject new_sentence, SubLObject old_mt, SubLObject new_mt, SubLObject strength, SubLObject direction) {
        if (old_mt == UNPROVIDED) {
            old_mt = NIL;
        }
        if (new_mt == UNPROVIDED) {
            new_mt = old_mt;
        }
        if (strength == UNPROVIDED) {
            strength = $DEFAULT;
        }
        if (direction == UNPROVIDED) {
            direction = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        fi.reset_fi_error_state();
        if (NIL == el_formula_p(old_sentence)) {
            fi.signal_fi_error(list($ARG_ERROR, $str2$Expected_a_cons__got__S, old_sentence));
            return NIL;
        }
        if (NIL == el_formula_p(new_sentence)) {
            fi.signal_fi_error(list($ARG_ERROR, $str2$Expected_a_cons__got__S, new_sentence));
            return NIL;
        }
        old_sentence = canon_tl.transform_tl_terms_to_hl(old_sentence);
        new_sentence = canon_tl.transform_tl_terms_to_hl(new_sentence);
        if (NIL != old_mt) {
            old_mt = canon_tl.transform_tl_terms_to_hl(old_mt);
            old_mt = fi.fi_convert_to_assert_hlmt_if_wft(old_mt);
            if (NIL != fi.fi_error_signaledP()) {
                return NIL;
            }
        }
        if (NIL != new_mt) {
            new_mt = canon_tl.transform_tl_terms_to_hl(new_mt);
            new_mt = fi.fi_convert_to_assert_hlmt_if_wft(new_mt);
            if (NIL != fi.fi_error_signaledP()) {
                return NIL;
            }
        }
        if (NIL == enumeration_types.el_strength_p(strength)) {
            fi.signal_fi_error(list($ARG_ERROR, $str3$Expected__default_or__monotonic__, strength));
            return NIL;
        }
        if ((NIL != direction) && (NIL == enumeration_types.direction_p(direction))) {
            fi.signal_fi_error(list($ARG_ERROR, $str4$Expected_a_direction__got__S, direction));
            return NIL;
        }
        thread.resetMultipleValues();
        SubLObject old_assertions = fi_edit_compute_old_assertions(old_sentence, old_mt);
        final SubLObject missing_old = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != missing_old) {
            return fi_edit_error($str5$some_old_formulas_missing___S, missing_old);
        }
        thread.resetMultipleValues();
        final SubLObject old_excepts = fi_edit_add_old_excepts(old_assertions);
        final SubLObject invalid_excepts = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != invalid_excepts) {
            fi_edit_remove_old_excepts(old_excepts);
            return fi_edit_error($str6$unable_to_except___S, invalid_excepts);
        }
        thread.resetMultipleValues();
        final SubLObject new_canon_tuples = fi_edit_compute_new_canon_tuples(new_sentence, new_mt);
        final SubLObject invalid_new = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != invalid_new) {
            fi_edit_remove_old_excepts(old_excepts);
            return fi_edit_error($str7$some_new_formulas_invalid___S, invalid_new);
        }
        thread.resetMultipleValues();
        final SubLObject new_asserts = fi_edit_add_asserts(new_canon_tuples, direction, strength);
        final SubLObject new_cnfs = thread.secondMultipleValue();
        final SubLObject invalid_asserts = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        if (NIL != invalid_asserts) {
            try {
                fi_edit_remove_new_cnfs(new_cnfs);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    try {
                        fi_edit_remove_old_excepts(old_excepts);
                    } finally {
                        final SubLObject _prev_bind_0_$1 = $is_thread_performing_cleanupP$.currentBinding(thread);
                        try {
                            $is_thread_performing_cleanupP$.bind(T, thread);
                            final SubLObject _values_$2 = getValuesAsVector();
                            fi_edit_remove_new_assertions(new_asserts);
                            restoreValuesFromVector(_values_$2);
                        } finally {
                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$1, thread);
                        }
                    }
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
            return fi_edit_error($str8$some_new_formulas_failed___S, invalid_asserts);
        }
        old_assertions = fi_edit_update_old_assertions_wrt_new(old_assertions, new_asserts);
        fi_edit_remove_old_excepts(old_excepts);
        fi_edit_remove_old_assertions(old_assertions);
        fi_edit_forward_propagate_new_asserts(new_asserts);
        return add(length(new_asserts), length(new_cnfs));
    }

    public static SubLObject fi_edit_error(final SubLObject format_string, SubLObject arg) {
        if (arg == UNPROVIDED) {
            arg = NIL;
        }
        fi.signal_fi_error(listS($FI_EDIT, format_string, append(NIL != arg ? list(arg) : NIL, NIL)));
        return NIL;
    }

    public static SubLObject fi_edit_precanonicalize(SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        sentence = make_ist_sentence(mt, sentence);
        sentence = simplifier.simplify_cycl_sentence_syntax(sentence, UNPROVIDED);
        if (NIL == el_conjunction_p(sentence)) {
            sentence = make_conjunction(list(sentence));
        }
        return sentence;
    }

    public static SubLObject fi_edit_canon_versions_invalidP(final SubLObject canon_versions) {
        return makeBoolean(((NIL == canon_versions) || (NIL != cycl_grammar.cycl_true_p(canon_versions))) || (NIL != cycl_grammar.cycl_false_p(canon_versions)));
    }

    public static SubLObject canonicalize_fi_assert_sentence(final SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject canon_versions = (NIL != fi.$assume_assert_sentence_is_wfP$.getDynamicValue(thread)) ? czer_main.canonicalize_wf_assert_sentence(sentence, mt) : czer_main.canonicalize_assert_sentence(sentence, mt);
        SubLObject canon_mt = thread.secondMultipleValue();
        thread.resetMultipleValues();
        canon_mt = fi.fi_convert_to_assert_hlmt(canon_mt);
        return values(canon_versions, canon_mt);
    }

    public static SubLObject fi_edit_compute_old_assertions(final SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject ist_sentence_conjuction = fi_edit_precanonicalize(sentence, mt);
        final SubLObject ist_sentences = cycl_utilities.el_formula_args(ist_sentence_conjuction, UNPROVIDED);
        SubLObject all_old_assertions = NIL;
        SubLObject missing_old_subsentences = NIL;
        SubLObject cdolist_list_var = ist_sentences;
        SubLObject ist_sentence = NIL;
        ist_sentence = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            thread.resetMultipleValues();
            final SubLObject canon_versions = fi.canonicalize_fi_unassert_sentence(ist_sentence, mt);
            SubLObject canonical_mt = thread.secondMultipleValue();
            thread.resetMultipleValues();
            canonical_mt = fi.fi_convert_to_assert_hlmt(canonical_mt);
            thread.resetMultipleValues();
            final SubLObject old_assertions = fi_edit_compute_old_assertions_from_canon_versions(canon_versions, canonical_mt);
            final SubLObject some_missingP = thread.secondMultipleValue();
            thread.resetMultipleValues();
            SubLObject cdolist_list_var_$3 = old_assertions;
            SubLObject old_assertion = NIL;
            old_assertion = cdolist_list_var_$3.first();
            while (NIL != cdolist_list_var_$3) {
                all_old_assertions = cons(old_assertion, all_old_assertions);
                cdolist_list_var_$3 = cdolist_list_var_$3.rest();
                old_assertion = cdolist_list_var_$3.first();
            } 
            if (NIL != some_missingP) {
                missing_old_subsentences = cons(ist_sentence, missing_old_subsentences);
            }
            cdolist_list_var = cdolist_list_var.rest();
            ist_sentence = cdolist_list_var.first();
        } 
        all_old_assertions = nreverse(all_old_assertions);
        missing_old_subsentences = nreverse(missing_old_subsentences);
        return values(all_old_assertions, missing_old_subsentences);
    }

    public static SubLObject fi_edit_compute_old_assertions_from_canon_versions(final SubLObject canon_versions, final SubLObject canonical_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject old_assertions = NIL;
        SubLObject some_missingP = NIL;
        if (((NIL == canon_versions) || (NIL != cycl_grammar.cycl_true_p(canon_versions))) || (NIL != cycl_grammar.cycl_false_p(canon_versions))) {
            some_missingP = T;
        } else {
            final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(canonical_mt);
            final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
            final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
            final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
            try {
                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                SubLObject cdolist_list_var = canon_versions;
                SubLObject canon_version = NIL;
                canon_version = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject current;
                    final SubLObject datum = current = canon_version;
                    SubLObject cnf = NIL;
                    destructuring_bind_must_consp(current, datum, $list10);
                    cnf = current.first();
                    current = current.rest();
                    final SubLObject variable_map = (current.isCons()) ? current.first() : NIL;
                    destructuring_bind_must_listp(current, datum, $list10);
                    current = current.rest();
                    final SubLObject query_free_vars = (current.isCons()) ? current.first() : NIL;
                    destructuring_bind_must_listp(current, datum, $list10);
                    current = current.rest();
                    if (NIL == current) {
                        final SubLObject old_assertion = kb_indexing.find_assertion(cnf, canonical_mt);
                        if ((NIL != old_assertion) && (NIL != assertions_high.asserted_assertionP(old_assertion))) {
                            old_assertions = cons(old_assertion, old_assertions);
                        } else {
                            some_missingP = T;
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list10);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    canon_version = cdolist_list_var.first();
                } 
            } finally {
                mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
                mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
            }
        }
        return values(nreverse(old_assertions), some_missingP);
    }

    public static SubLObject fi_edit_compute_new_canon_tuples(final SubLObject sentence, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject ist_sentence_conjuction = fi_edit_precanonicalize(sentence, mt);
        final SubLObject ist_sentences = cycl_utilities.el_formula_args(ist_sentence_conjuction, UNPROVIDED);
        SubLObject new_canon_tuples = NIL;
        SubLObject invalid_new_subsentences = NIL;
        SubLObject cdolist_list_var = ist_sentences;
        SubLObject ist_sentence = NIL;
        ist_sentence = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == cycl_grammar.cycl_true_p(ist_sentence)) {
                thread.resetMultipleValues();
                final SubLObject inner_sentence = czer_utilities.unwrap_if_ist(ist_sentence, mt, UNPROVIDED);
                final SubLObject inner_mt = thread.secondMultipleValue();
                thread.resetMultipleValues();
                thread.resetMultipleValues();
                final SubLObject canon_versions = canonicalize_fi_assert_sentence(inner_sentence, inner_mt);
                final SubLObject canonical_mt = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL != fi_edit_canon_versions_invalidP(canon_versions)) {
                    invalid_new_subsentences = cons(ist_sentence, invalid_new_subsentences);
                } else {
                    final SubLObject canon_tuple = list(canon_versions, canonical_mt);
                    new_canon_tuples = cons(canon_tuple, new_canon_tuples);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            ist_sentence = cdolist_list_var.first();
        } 
        new_canon_tuples = nreverse(new_canon_tuples);
        invalid_new_subsentences = nreverse(invalid_new_subsentences);
        return values(new_canon_tuples, invalid_new_subsentences);
    }

    public static SubLObject fi_edit_add_old_excepts(final SubLObject old_assertions) {
        SubLObject old_excepts = NIL;
        SubLObject invalid_excepts = NIL;
        SubLObject cdolist_list_var = old_assertions;
        SubLObject old_assertion = NIL;
        old_assertion = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject old_except = fi_edit_add_old_except(old_assertion);
            if (NIL != old_except) {
                old_excepts = cons(old_except, old_excepts);
            } else {
                invalid_excepts = cons(old_assertion, invalid_excepts);
            }
            cdolist_list_var = cdolist_list_var.rest();
            old_assertion = cdolist_list_var.first();
        } 
        old_excepts = nreverse(old_excepts);
        invalid_excepts = nreverse(invalid_excepts);
        return values(old_excepts, invalid_excepts);
    }

    public static SubLObject fi_edit_add_old_except(final SubLObject old_assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != assertion_handles.assertion_p(old_assertion) : "assertion_handles.assertion_p(old_assertion) " + "CommonSymbols.NIL != assertion_handles.assertion_p(old_assertion) " + old_assertion;
        final SubLObject mt = assertions_high.assertion_mt(old_assertion);
        final SubLObject except_sentence = make_unary_formula($$except, old_assertion);
        final SubLObject v_properties = $list13;
        SubLObject old_except = NIL;
        final SubLObject _prev_bind_0 = kb_control_vars.$forward_inference_enabledP$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $within_fi_edit_add_old_exceptP$.currentBinding(thread);
        try {
            kb_control_vars.$forward_inference_enabledP$.bind(NIL, thread);
            $within_fi_edit_add_old_exceptP$.bind(T, thread);
            if (NIL != cyc_kernel.cyc_assert_wff(except_sentence, mt, v_properties)) {
                old_except = kb_indexing.find_gaf(except_sentence, mt);
            }
        } finally {
            $within_fi_edit_add_old_exceptP$.rebind(_prev_bind_2, thread);
            kb_control_vars.$forward_inference_enabledP$.rebind(_prev_bind_0, thread);
        }
        if (NIL != assertion_handles.assertion_p(old_except)) {
            return old_except;
        }
        return NIL;
    }

    public static SubLObject fi_edit_add_asserts(final SubLObject canon_tuples, SubLObject direction, final SubLObject strength) {
        SubLObject assertions_found_or_created = NIL;
        SubLObject cnfs_otherwise_asserted = NIL;
        SubLObject invalid_witness = NIL;
        fi.set_fi_last_assertions_asserted(NIL);
        if (NIL == invalid_witness) {
            SubLObject csome_list_var = canon_tuples;
            SubLObject canon_tuple = NIL;
            canon_tuple = csome_list_var.first();
            while ((NIL == invalid_witness) && (NIL != csome_list_var)) {
                SubLObject current;
                final SubLObject datum = current = canon_tuple;
                SubLObject canon_versions = NIL;
                SubLObject canon_mt = NIL;
                destructuring_bind_must_consp(current, datum, $list14);
                canon_versions = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list14);
                canon_mt = current.first();
                current = current.rest();
                if (NIL == current) {
                    if (NIL == invalid_witness) {
                        SubLObject csome_list_var_$4 = canon_versions;
                        SubLObject canon_version = NIL;
                        canon_version = csome_list_var_$4.first();
                        while ((NIL == invalid_witness) && (NIL != csome_list_var_$4)) {
                            SubLObject current_$6;
                            final SubLObject datum_$5 = current_$6 = canon_version;
                            SubLObject cnf = NIL;
                            destructuring_bind_must_consp(current_$6, datum_$5, $list15);
                            cnf = current_$6.first();
                            current_$6 = current_$6.rest();
                            final SubLObject variable_map = (current_$6.isCons()) ? current_$6.first() : NIL;
                            destructuring_bind_must_listp(current_$6, datum_$5, $list15);
                            final SubLObject rest;
                            current_$6 = rest = current_$6.rest();
                            if (NIL == direction) {
                                direction = fi.fi_cnf_default_direction(cnf);
                            }
                            final SubLObject assertion = hl_storage_modules.hl_assert(cnf, canon_mt, strength, direction, variable_map);
                            if (NIL == assertion) {
                                invalid_witness = canon_version;
                            } else
                                if (NIL != assertion_handles.assertion_p(assertion)) {
                                    assertions_found_or_created = cons(assertion, assertions_found_or_created);
                                } else {
                                    final SubLObject cnf_tuple = list(cnf, canon_mt);
                                    cnfs_otherwise_asserted = cons(cnf_tuple, cnfs_otherwise_asserted);
                                }

                            csome_list_var_$4 = csome_list_var_$4.rest();
                            canon_version = csome_list_var_$4.first();
                        } 
                    }
                } else {
                    cdestructuring_bind_error(datum, $list14);
                }
                csome_list_var = csome_list_var.rest();
                canon_tuple = csome_list_var.first();
            } 
        }
        assertions_found_or_created = nreverse(assertions_found_or_created);
        cnfs_otherwise_asserted = nreverse(cnfs_otherwise_asserted);
        return values(assertions_found_or_created, cnfs_otherwise_asserted, invalid_witness);
    }

    public static SubLObject fi_edit_remove_new_cnfs(final SubLObject new_cnf_tuples) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject total = ZERO_INTEGER;
        SubLObject cdolist_list_var = new_cnf_tuples;
        SubLObject cnf_tuple = NIL;
        cnf_tuple = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = cnf_tuple;
            SubLObject cnf = NIL;
            SubLObject mt = NIL;
            destructuring_bind_must_consp(current, datum, $list16);
            cnf = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list16);
            mt = current.first();
            current = current.rest();
            if (NIL == current) {
                SubLObject ignore_errors_tag = NIL;
                try {
                    thread.throwStack.push($IGNORE_ERRORS_TARGET);
                    final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                    try {
                        Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
                        try {
                            hl_storage_modules.hl_unassert(cnf, mt);
                            total = add(total, ONE_INTEGER);
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
            } else {
                cdestructuring_bind_error(datum, $list16);
            }
            cdolist_list_var = cdolist_list_var.rest();
            cnf_tuple = cdolist_list_var.first();
        } 
        return total;
    }

    public static SubLObject fi_edit_remove_new_assertions(final SubLObject new_assertions) {
        return hl_unassert_all_assertions(new_assertions);
    }

    public static SubLObject fi_edit_update_old_assertions_wrt_new(SubLObject old_assertions, final SubLObject new_asserts) {
        final SubLObject preserved_assertions = keyhash_utilities.fast_intersection(old_assertions, new_asserts, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        if (NIL != preserved_assertions) {
            old_assertions = list_utilities.fast_set_difference(old_assertions, preserved_assertions, symbol_function(KBEQ));
        }
        return old_assertions;
    }

    public static SubLObject fi_edit_remove_old_excepts(final SubLObject old_excepts) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        final SubLObject _prev_bind_0 = kb_control_vars.$forward_inference_enabledP$.currentBinding(thread);
        try {
            kb_control_vars.$forward_inference_enabledP$.bind(NIL, thread);
            result = hl_unassert_all_assertions(old_excepts);
        } finally {
            kb_control_vars.$forward_inference_enabledP$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static SubLObject fi_edit_remove_old_assertions(final SubLObject old_assertions) {
        return hl_unassert_all_assertions(old_assertions);
    }

    public static SubLObject hl_unassert_all_assertions(final SubLObject assertions) {
        SubLObject cdolist_list_var = assertions;
        SubLObject assertion = NIL;
        assertion = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            hl_storage_modules.hl_unassert(assertions_high.assertion_cnf(assertion), assertions_high.assertion_mt(assertion));
            cdolist_list_var = cdolist_list_var.rest();
            assertion = cdolist_list_var.first();
        } 
        return length(assertions);
    }

    public static SubLObject fi_edit_forward_propagate_new_asserts(final SubLObject new_asserts) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject deductions_found_or_created = NIL;
        if (NIL == fi.fi_error_signaledP()) {
            final SubLObject _prev_bind_0 = kb_control_vars.$forward_inference_allowed_rules$.currentBinding(thread);
            final SubLObject _prev_bind_2 = kb_control_vars.$forward_inference_forbidden_rules$.currentBinding(thread);
            try {
                kb_control_vars.$forward_inference_allowed_rules$.bind(hl_prototypes.hl_prototype_allowed_forward_rules(new_asserts), thread);
                kb_control_vars.$forward_inference_forbidden_rules$.bind(hl_prototypes.hl_prototype_forbidden_forward_rules(new_asserts), thread);
                deductions_found_or_created = forward.perform_forward_inference();
            } finally {
                kb_control_vars.$forward_inference_forbidden_rules$.rebind(_prev_bind_2, thread);
                kb_control_vars.$forward_inference_allowed_rules$.rebind(_prev_bind_0, thread);
            }
            fi.perform_assert_post_processing(new_asserts, deductions_found_or_created);
        }
        return length(deductions_found_or_created);
    }

    public static SubLObject declare_fi_edit_file() {
        declareFunction("new_fi_edit_int", "NEW-FI-EDIT-INT", 2, 4, false);
        declareFunction("fi_edit_error", "FI-EDIT-ERROR", 1, 1, false);
        declareFunction("fi_edit_precanonicalize", "FI-EDIT-PRECANONICALIZE", 1, 1, false);
        declareFunction("fi_edit_canon_versions_invalidP", "FI-EDIT-CANON-VERSIONS-INVALID?", 1, 0, false);
        declareFunction("canonicalize_fi_assert_sentence", "CANONICALIZE-FI-ASSERT-SENTENCE", 1, 1, false);
        declareFunction("fi_edit_compute_old_assertions", "FI-EDIT-COMPUTE-OLD-ASSERTIONS", 1, 1, false);
        declareFunction("fi_edit_compute_old_assertions_from_canon_versions", "FI-EDIT-COMPUTE-OLD-ASSERTIONS-FROM-CANON-VERSIONS", 2, 0, false);
        declareFunction("fi_edit_compute_new_canon_tuples", "FI-EDIT-COMPUTE-NEW-CANON-TUPLES", 2, 0, false);
        declareFunction("fi_edit_add_old_excepts", "FI-EDIT-ADD-OLD-EXCEPTS", 1, 0, false);
        declareFunction("fi_edit_add_old_except", "FI-EDIT-ADD-OLD-EXCEPT", 1, 0, false);
        declareFunction("fi_edit_add_asserts", "FI-EDIT-ADD-ASSERTS", 3, 0, false);
        declareFunction("fi_edit_remove_new_cnfs", "FI-EDIT-REMOVE-NEW-CNFS", 1, 0, false);
        declareFunction("fi_edit_remove_new_assertions", "FI-EDIT-REMOVE-NEW-ASSERTIONS", 1, 0, false);
        declareFunction("fi_edit_update_old_assertions_wrt_new", "FI-EDIT-UPDATE-OLD-ASSERTIONS-WRT-NEW", 2, 0, false);
        declareFunction("fi_edit_remove_old_excepts", "FI-EDIT-REMOVE-OLD-EXCEPTS", 1, 0, false);
        declareFunction("fi_edit_remove_old_assertions", "FI-EDIT-REMOVE-OLD-ASSERTIONS", 1, 0, false);
        declareFunction("hl_unassert_all_assertions", "HL-UNASSERT-ALL-ASSERTIONS", 1, 0, false);
        declareFunction("fi_edit_forward_propagate_new_asserts", "FI-EDIT-FORWARD-PROPAGATE-NEW-ASSERTS", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_fi_edit_file() {
        defparameter("*WITHIN-FI-EDIT-ADD-OLD-EXCEPT?*", NIL);
        return NIL;
    }

    public static SubLObject setup_fi_edit_file() {
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_fi_edit_file();
    }

    @Override
    public void initializeVariables() {
        init_fi_edit_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_fi_edit_file();
    }

    static {






















    }
}

