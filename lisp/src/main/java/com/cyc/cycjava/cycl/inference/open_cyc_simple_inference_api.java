/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.inference;


import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.cyc_testing.generic_testing.define_test_case_table_int;
import static com.cyc.cycjava.cycl.el_utilities.closedP;
import static com.cyc.cycjava.cycl.el_utilities.make_formula;
import static com.cyc.cycjava.cycl.subl_macro_promotions.$with_timeout_nesting_depth$;
import static com.cyc.cycjava.cycl.subl_macro_promotions.$within_with_timeout$;
import static com.cyc.cycjava.cycl.subl_macro_promotions.with_timeout_make_tag;
import static com.cyc.cycjava.cycl.subl_macro_promotions.with_timeout_start_timer;
import static com.cyc.cycjava.cycl.subl_macro_promotions.with_timeout_stop_timer;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_tab;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Eval.eval;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.divide;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.copy_seq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove_if;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.resetMultipleValues;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.butlast;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.set_difference;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_pretty$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.close;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.api_widgets;
import com.cyc.cycjava.cycl.at_var_types;
import com.cyc.cycjava.cycl.builder_utilities;
import com.cyc.cycjava.cycl.collection_defns;
import com.cyc.cycjava.cycl.concept_tagger;
import com.cyc.cycjava.cycl.cyc_kernel;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.czer_main;
import com.cyc.cycjava.cycl.document;
import com.cyc.cycjava.cycl.format_nil;
import com.cyc.cycjava.cycl.fort_types_interface;
import com.cyc.cycjava.cycl.isa;
import com.cyc.cycjava.cycl.kb_accessors;
import com.cyc.cycjava.cycl.kb_utilities;
import com.cyc.cycjava.cycl.lexicon_accessors;
import com.cyc.cycjava.cycl.lexicon_utilities;
import com.cyc.cycjava.cycl.lexicon_vars;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.memoization_state;
import com.cyc.cycjava.cycl.methods;
import com.cyc.cycjava.cycl.mt_relevance_macros;
import com.cyc.cycjava.cycl.number_utilities;
import com.cyc.cycjava.cycl.parsing_macros;
import com.cyc.cycjava.cycl.semtrans_lexicon;
import com.cyc.cycjava.cycl.verbosifier;
import com.cyc.cycjava.cycl.wff;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store;
import com.cyc.cycjava.cycl.inference.harness.inference_kernel;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.compatibility;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.stream_macros;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class open_cyc_simple_inference_api extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new open_cyc_simple_inference_api();

 public static final String myName = "com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api";


    // defparameter
    // Definitions
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $simple_query_default_mt$ = makeSymbol("*SIMPLE-QUERY-DEFAULT-MT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $simple_query_default_nl_mt$ = makeSymbol("*SIMPLE-QUERY-DEFAULT-NL-MT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $simple_boolean_default_time$ = makeSymbol("*SIMPLE-BOOLEAN-DEFAULT-TIME*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $simple_boolean_nl_default_time$ = makeSymbol("*SIMPLE-BOOLEAN-NL-DEFAULT-TIME*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $first_order_collectionP_caching_state$ = makeSymbol("*FIRST-ORDER-COLLECTION?-CACHING-STATE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $variable_order_collectionP_caching_state$ = makeSymbol("*VARIABLE-ORDER-COLLECTION?-CACHING-STATE*");

    // defparameter
    // The semtrans-lexicon object used by the open-cyc-simple-inference API
    /**
     * The semtrans-lexicon object used by the open-cyc-simple-inference API
     */
    @LispMethod(comment = "The semtrans-lexicon object used by the open-cyc-simple-inference API\ndefparameter")
    private static final SubLSymbol $get_simple_inference_semtrans_lexicon$ = makeSymbol("*GET-SIMPLE-INFERENCE-SEMTRANS-LEXICON*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list0 = list(reader_make_constant_shell("MtSpace"), list(reader_make_constant_shell("MtTimeDimFn"), reader_make_constant_shell("Now")), list(reader_make_constant_shell("MtUnionFn"), reader_make_constant_shell("AssistedReaderSourceSpindleCollectorMt"), reader_make_constant_shell("AllGeneralEnglishValidatedLexicalMicrotheoryPSC")));



    private static final SubLSymbol SIMPLE_BOOLEAN_QUERY_EXTERNAL_ID = makeSymbol("SIMPLE-BOOLEAN-QUERY-EXTERNAL-ID");

    private static final SubLSymbol FIND_OBJECT_BY_COMPACT_HL_EXTERNAL_ID_STRING = makeSymbol("FIND-OBJECT-BY-COMPACT-HL-EXTERNAL-ID-STRING");

    private static final SubLSymbol SIMPLE_BOOLEAN_QUERY_CYCL = makeSymbol("SIMPLE-BOOLEAN-QUERY-CYCL");

    static private final SubLString $str6$The_sentence_contains_an_open_var = makeString("The sentence contains an open variable.");

    static private final SubLString $$$The_sentence_ = makeString("The sentence ");

    static private final SubLString $str8$_is_not_a_valid_CycL_sentence_ = makeString(" is not a valid CycL sentence.");

    static private final SubLString $str10$_is_not_semantically_well_formed = makeString(" is not semantically well-formed");

    static private final SubLList $list13 = list(makeKeyword("BROWSABLE?"), T);

    private static final SubLString $$$Timed_out = makeString("Timed out");



    private static final SubLSymbol SIMPLE_BOOLEAN_QUERY_NL = makeSymbol("SIMPLE-BOOLEAN-QUERY-NL");

    private static final SubLObject $$is_Underspecified = reader_make_constant_shell("is-Underspecified");

    private static final SubLSymbol NL_QUERY_DENOTS_OF_STRING = makeSymbol("NL-QUERY-DENOTS-OF-STRING");

    private static final SubLList $list22 = list(makeSymbol("RESULT"), makeSymbol("ERROR-MSG"));

    private static final SubLString $$$Timed_out_before_completion = makeString("Timed out before completion");



    private static final SubLObject $const27$CurrentWorldDataCollectorMt_NonHo = reader_make_constant_shell("CurrentWorldDataCollectorMt-NonHomocentric");

    private static final SubLSymbol $sym28$BAD_FOR_TAGGING_ = makeSymbol("BAD-FOR-TAGGING?");

    private static final SubLSymbol $IGNORE_ERRORS_TARGET = makeKeyword("IGNORE-ERRORS-TARGET");

    private static final SubLSymbol IGNORE_ERRORS_HANDLER = makeSymbol("IGNORE-ERRORS-HANDLER", "SUBLISP");

    private static final SubLString $str31$_cyc_projects_doc_annotation_comp = makeString("/cyc/projects/doc-annotation/compounds.txt");

    private static final SubLString $str33$Unable_to_open__S = makeString("Unable to open ~S");

    private static final SubLString $str34$_S_A_S_A_S__ = makeString("~S~A~S~A~S~%");

    private static final SubLSymbol $nl_query_denots_of_string_caching_state$ = makeSymbol("*NL-QUERY-DENOTS-OF-STRING-CACHING-STATE*");

    private static final SubLInteger $int$128000 = makeInteger(128000);







    private static final SubLSymbol $sym41$FIRST_ORDER_COLLECTION_ = makeSymbol("FIRST-ORDER-COLLECTION?");



    private static final SubLSymbol $sym43$_FIRST_ORDER_COLLECTION__CACHING_STATE_ = makeSymbol("*FIRST-ORDER-COLLECTION?-CACHING-STATE*");

    private static final SubLSymbol $sym44$VARIABLE_ORDER_COLLECTION_ = makeSymbol("VARIABLE-ORDER-COLLECTION?");



    private static final SubLSymbol $sym46$_VARIABLE_ORDER_COLLECTION__CACHING_STATE_ = makeSymbol("*VARIABLE-ORDER-COLLECTION?-CACHING-STATE*");







    private static final SubLSymbol $sym55$_EVENT = makeSymbol("?EVENT");

    private static final SubLSymbol $sym59$_SUBJ = makeSymbol("?SUBJ");



    static private final SubLList $list61 = list(reader_make_constant_shell("isa"), makeSymbol("?SUBJ"), makeKeyword("SUBJECT"));

    private static final SubLSymbol $sym62$_FIRST_ORDER_SUBJ = makeSymbol("?FIRST-ORDER-SUBJ");

    private static final SubLList $list63 = list(reader_make_constant_shell("isa"), makeSymbol("?FIRST-ORDER-SUBJ"), makeKeyword("SUBJECT"));

    static private final SubLList $list64 = list(reader_make_constant_shell("isa"), makeSymbol("?SUBJ"), makeSymbol("?FIRST-ORDER-SUBJ"));

    private static final SubLSymbol $sym65$_OBJ = makeSymbol("?OBJ");

    private static final SubLList $list66 = list(reader_make_constant_shell("isa"), makeSymbol("?OBJ"), makeKeyword("OBJECT"));





    private static final SubLString $str70$Unable_to_initialize__A__ = makeString("Unable to initialize ~A~%");

    private static final SubLSymbol COMPACT_HL_EXTERNAL_ID_STRING = makeSymbol("COMPACT-HL-EXTERNAL-ID-STRING");

    private static final SubLSymbol SIMPLE_BOOLEAN_QUERY_TEST = makeSymbol("SIMPLE-BOOLEAN-QUERY-TEST");

    private static final SubLList $list80 = list(new SubLObject[]{ list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("EnglishMt"), reader_make_constant_shell("Microtheory"))), list($TRUE, NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("EnglishMt"), reader_make_constant_shell("Microtheory")), makeInteger(1000)), list($TRUE, NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("Microtheory"), reader_make_constant_shell("EnglishMt"))), list(makeKeyword("FALSE"), makeString("(#$isa #$Microtheory #$EnglishMt) is not semantically well-formed"))), list(list(reader_make_constant_shell("likesAsFriend"), list(reader_make_constant_shell("Microtheory"), reader_make_constant_shell("EnglishMt"))), list(makeKeyword("FALSE"), makeString("(#$likesAsFriend #$Microtheory #$EnglishMt) is not semantically well-formed"))), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("Microtheory"), makeSymbol("?X"))), list(makeKeyword("ERROR"), makeString("The sentence contains an open variable."))), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("RichardNixon"), reader_make_constant_shell("Plumber"))), list(makeKeyword("FALSE"), NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("BradPitt"), reader_make_constant_shell("Plumber"))), list(makeKeyword("UNKNOWN"), NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("BarackObama"), reader_make_constant_shell("UnitedStatesPresident"))), list($TRUE, NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("RichardNixon"), reader_make_constant_shell("UnitedStatesPresident")), ONE_INTEGER, reader_make_constant_shell("BaseKB")), list(makeKeyword("FALSE"), NIL)) });

    private static final SubLSymbol SIMPLE_BOOLEAN_QUERY_NL_TEST = makeSymbol("SIMPLE-BOOLEAN-QUERY-NL-TEST");

    private static final SubLList $list82 = list(list(list(makeString("is"), list(makeString("Tony Brusseau"), makeString("person"))), $TRUE), list(list(makeString("employs"), list(makeString("Cycorp"), makeString("computer scientist")), makeInteger(1000)), $TRUE), list(list(makeString("is"), list(makeString("Richard Nixon"), makeString("plumber"))), makeKeyword("FALSE")), list(list(makeString("is"), list(makeString("Brad Pitt"), makeString("plumber"))), makeKeyword("WFF")), list(list(makeString("is"), list(makeString("Barack Obama"), makeString("US President"))), $TRUE), list(list(makeString("hates"), list(makeString("Richard Nixon"), makeString("World War II")), ONE_INTEGER, reader_make_constant_shell("BaseKB")), makeKeyword("FALSE")), list(list(makeString("hates"), list(makeString("Richard Nixon"), makeString("World War II")), ONE_INTEGER, reader_make_constant_shell("InferencePSC")), makeKeyword("WFF")));

    public static final SubLObject get_simple_query_default_mt_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return $simple_query_default_mt$.getDynamicValue(thread);
        }
    }

    public static SubLObject get_simple_query_default_mt() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return $simple_query_default_mt$.getDynamicValue(thread);
    }

    public static final SubLObject get_simple_query_default_nl_mt_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return $simple_query_default_nl_mt$.getDynamicValue(thread);
        }
    }

    public static SubLObject get_simple_query_default_nl_mt() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return $simple_query_default_nl_mt$.getDynamicValue(thread);
    }

    /**
     *
     *
     * @unknown currently (2009-08) limited to removal-only queries, but that would be easy to change.
     * @param PRED-ID
     * 		compact-hl-external-id-p; the compact-hl-external-id of the predicate for this query
     * @param ARG-IDS
     * 		listp of compact-hl-external-id-p;
     * @param MT-ID
    compact-hl-external-id-p;
     * 		
     * @param TIME-LIMIT-MSEC
     * 		integer-p; number of milliseconds to allow for the entire call
     * @unknown keyword-p; is the query provably true (:TRUE), provably false (:FALSE), neither (:UNK), or did
    an error occur (:ERROR).
     * @unknown stringp; if return0 is :ERROR, a string explaining the problem
     */
    @LispMethod(comment = "@unknown currently (2009-08) limited to removal-only queries, but that would be easy to change.\r\n@param PRED-ID\r\n\t\tcompact-hl-external-id-p; the compact-hl-external-id of the predicate for this query\r\n@param ARG-IDS\r\n\t\tlistp of compact-hl-external-id-p;\r\n@param MT-ID\ncompact-hl-external-id-p;\r\n\t\t\r\n@param TIME-LIMIT-MSEC\r\n\t\tinteger-p; number of milliseconds to allow for the entire call\r\n@unknown keyword-p; is the query provably true (:TRUE), provably false (:FALSE), neither (:UNK), or did\r\nan error occur (:ERROR).\r\n@unknown stringp; if return0 is :ERROR, a string explaining the problem")
    public static final SubLObject simple_boolean_query_external_id_alt(SubLObject pred_id, SubLObject arg_ids, SubLObject max_time_sec, SubLObject mt_id) {
        if (max_time_sec == UNPROVIDED) {
            max_time_sec = ONE_INTEGER;
        }
        if (mt_id == UNPROVIDED) {
            mt_id = NIL;
        }
        {
            SubLObject pred = kb_utilities.find_object_by_compact_hl_external_id_string(pred_id);
            SubLObject args = Mapping.mapcar(FIND_OBJECT_BY_COMPACT_HL_EXTERNAL_ID_STRING, arg_ids);
            SubLObject mt = (NIL != mt_id) ? ((SubLObject) (kb_utilities.find_object_by_compact_hl_external_id_string(mt_id))) : com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.get_simple_query_default_mt();
            return com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.simple_boolean_query_cycl(pred, args, max_time_sec, mt);
        }
    }

    /**
     *
     *
     * @unknown currently (2009-08) limited to removal-only queries, but that would be easy to change.
     * @param PRED-ID
     * 		compact-hl-external-id-p; the compact-hl-external-id of the predicate for this query
     * @param ARG-IDS
     * 		listp of compact-hl-external-id-p;
     * @param MT-ID
    compact-hl-external-id-p;
     * 		
     * @param TIME-LIMIT-MSEC
     * 		integer-p; number of milliseconds to allow for the entire call
     * @unknown keyword-p; is the query provably true (:TRUE), provably false (:FALSE), neither (:UNK), or did
    an error occur (:ERROR).
     * @unknown stringp; if return0 is :ERROR, a string explaining the problem
     */
    @LispMethod(comment = "@unknown currently (2009-08) limited to removal-only queries, but that would be easy to change.\r\n@param PRED-ID\r\n\t\tcompact-hl-external-id-p; the compact-hl-external-id of the predicate for this query\r\n@param ARG-IDS\r\n\t\tlistp of compact-hl-external-id-p;\r\n@param MT-ID\ncompact-hl-external-id-p;\r\n\t\t\r\n@param TIME-LIMIT-MSEC\r\n\t\tinteger-p; number of milliseconds to allow for the entire call\r\n@unknown keyword-p; is the query provably true (:TRUE), provably false (:FALSE), neither (:UNK), or did\r\nan error occur (:ERROR).\r\n@unknown stringp; if return0 is :ERROR, a string explaining the problem")
    public static SubLObject simple_boolean_query_external_id(final SubLObject pred_id, final SubLObject arg_ids, SubLObject max_time_sec, SubLObject mt_id) {
        if (max_time_sec == UNPROVIDED) {
            max_time_sec = ONE_INTEGER;
        }
        if (mt_id == UNPROVIDED) {
            mt_id = NIL;
        }
        final SubLObject pred = kb_utilities.find_object_by_compact_hl_external_id_string(pred_id);
        final SubLObject args = Mapping.mapcar(FIND_OBJECT_BY_COMPACT_HL_EXTERNAL_ID_STRING, arg_ids);
        final SubLObject mt = (NIL != mt_id) ? kb_utilities.find_object_by_compact_hl_external_id_string(mt_id) : get_simple_query_default_mt();
        return simple_boolean_query_cycl(pred, args, max_time_sec, mt);
    }

    public static final SubLObject simple_boolean_query_cycl_alt(SubLObject pred, SubLObject args, SubLObject max_time_sec, SubLObject mt) {
        if (max_time_sec == UNPROVIDED) {
            max_time_sec = $simple_boolean_default_time$.getDynamicValue();
        }
        if (mt == UNPROVIDED) {
            mt = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.get_simple_query_default_mt();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                SubLObject result2 = NIL;
                SubLObject sentence = cons(pred, args);
                if (NIL == closedP(sentence, UNPROVIDED)) {
                    result = $ERROR;
                    result2 = $str_alt6$The_sentence_contains_an_open_var;
                }
                if (NIL == collection_defns.cycl_sentenceP(sentence)) {
                    result = $ERROR;
                    result2 = cconcatenate($str_alt7$The_sentence_, new SubLObject[]{ format_nil.format_nil_s_no_copy(sentence), $str_alt8$_is_not_a_valid_CycL_sentence_ });
                }
                if (NIL == result) {
                    if (NIL == wff.el_wffP(verbosifier.el_expansion(sentence, mt), mt, UNPROVIDED)) {
                        result = $FALSE;
                        result2 = cconcatenate(format_nil.format_nil_s_no_copy(sentence), $str_alt10$_is_not_semantically_well_formed);
                    }
                }
                if (NIL == result) {
                    {
                        SubLObject problem_store = inference_datastructures_problem_store.new_problem_store(UNPROVIDED);
                        SubLObject positive_query_max_time = divide(max_time_sec, TWO_INTEGER);
                        SubLObject inference_props = listS($MAX_TIME, positive_query_max_time, $PROBLEM_STORE, problem_store, $list_alt13);
                        thread.resetMultipleValues();
                        {
                            SubLObject temp_result = inference_kernel.new_cyc_query(sentence, mt, inference_props);
                            SubLObject halt_reason = thread.secondMultipleValue();
                            SubLObject inference = thread.thirdMultipleValue();
                            thread.resetMultipleValues();
                            if (NIL != cyc_kernel.closed_query_success_token_p(temp_result)) {
                                result = $TRUE;
                            }
                            if (NIL == result) {
                                {
                                    SubLObject negative_query_max_time = subtract(max_time_sec, inference_datastructures_inference.inference_cumulative_time_so_far(inference, NIL));
                                    SubLObject negative_query = list($$not, sentence);
                                    SubLObject inference_props_1 = list($MAX_TIME, negative_query_max_time, $PROBLEM_STORE, problem_store);
                                    inference_datastructures_inference.destroy_inference(inference);
                                    temp_result = inference_kernel.new_cyc_query(negative_query, mt, inference_props_1);
                                    if (NIL != cyc_kernel.closed_query_success_token_p(temp_result)) {
                                        result = $FALSE;
                                    }
                                }
                            }
                        }
                        inference_datastructures_problem_store.destroy_problem_store(problem_store);
                    }
                }
                if (NIL == result) {
                    result = $UNKNOWN;
                }
                return list(result, result2);
            }
        }
    }

    public static SubLObject simple_boolean_query_cycl(final SubLObject pred, final SubLObject args, SubLObject max_time_sec, SubLObject mt) {
        if (max_time_sec == UNPROVIDED) {
            max_time_sec = $simple_boolean_default_time$.getDynamicValue();
        }
        if (mt == UNPROVIDED) {
            mt = get_simple_query_default_mt();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        SubLObject result2 = NIL;
        final SubLObject sentence = cons(pred, args);
        if (NIL == closedP(sentence, UNPROVIDED)) {
            result = $ERROR;
            result2 = $str6$The_sentence_contains_an_open_var;
        }
        if (NIL == collection_defns.cycl_sentenceP(sentence)) {
            result = $ERROR;
            result2 = cconcatenate($$$The_sentence_, new SubLObject[]{ format_nil.format_nil_s_no_copy(sentence), $str8$_is_not_a_valid_CycL_sentence_ });
        }
        if ((NIL == result) && (NIL == wff.el_wffP(verbosifier.el_expansion(sentence, mt), mt, UNPROVIDED))) {
            result = $FALSE;
            result2 = cconcatenate(format_nil.format_nil_s_no_copy(sentence), $str10$_is_not_semantically_well_formed);
        }
        if (NIL == result) {
            final SubLObject problem_store = inference_datastructures_problem_store.new_problem_store(UNPROVIDED);
            final SubLObject positive_query_max_time = divide(max_time_sec, TWO_INTEGER);
            final SubLObject inference_props = listS($MAX_TIME, positive_query_max_time, $PROBLEM_STORE, problem_store, $list13);
            thread.resetMultipleValues();
            SubLObject temp_result = inference_kernel.new_cyc_query(sentence, mt, inference_props);
            final SubLObject halt_reason = thread.secondMultipleValue();
            final SubLObject inference = thread.thirdMultipleValue();
            thread.resetMultipleValues();
            if (halt_reason == $MAX_TIME) {
                result = $ERROR;
                result2 = copy_seq($$$Timed_out);
            }
            if (NIL != cyc_kernel.closed_query_success_token_p(temp_result)) {
                result = $TRUE;
            }
            if (NIL == result) {
                final SubLObject negative_query_max_time = subtract(max_time_sec, inference_datastructures_inference.inference_cumulative_time_so_far(inference, NIL));
                final SubLObject negative_query = list($$not, sentence);
                final SubLObject inference_props_$1 = list($MAX_TIME, negative_query_max_time, $PROBLEM_STORE, problem_store);
                inference_datastructures_inference.destroy_inference(inference);
                temp_result = inference_kernel.new_cyc_query(negative_query, mt, inference_props_$1);
                if (NIL != cyc_kernel.closed_query_success_token_p(temp_result)) {
                    result = $FALSE;
                }
            }
            inference_datastructures_problem_store.destroy_problem_store(problem_store);
        }
        if (NIL == result) {
            result = $UNKNOWN;
        }
        return list(result, result2);
    }

    /**
     *
     *
     * @param PRED-STRING
     * 		stringp; the string for the linguistic predicate (which might correspond to a CycL
     * 		predicate or an event-type
     * @param ARG-STRINGS
     * 		listp of stringp; a list of linguistic arguments to PRED-STRING.  They should be in
     * 		the order SUBJECT DIRECT-OBJECT INDIRECT-OBJECT; the list should contain at least one argument, but can
     * 		contain more.
     * @param MAX-TIME-SEC
     * 		integerp; how many seconds should be spent overall working on this problem
     * @param MT
     * 		hlmt-p; where should any CycL queries be asked from
     * @param LANGUAGE-MT
     * 		hlmt-p; from what Mt should all NL lookups be performed?
     * @return listp; a list of query-results, each of which is the 'answer' to one of the CycL queries that
    PRED-STRING and ARG-STRINGS were transformed into.  The answers are of the form
    (cycl-query RESULT error-string), where result is currently one of {:TRUE, :FALSE, :UNKNOWN}.
     */
    @LispMethod(comment = "@param PRED-STRING\r\n\t\tstringp; the string for the linguistic predicate (which might correspond to a CycL\r\n\t\tpredicate or an event-type\r\n@param ARG-STRINGS\r\n\t\tlistp of stringp; a list of linguistic arguments to PRED-STRING.  They should be in\r\n\t\tthe order SUBJECT DIRECT-OBJECT INDIRECT-OBJECT; the list should contain at least one argument, but can\r\n\t\tcontain more.\r\n@param MAX-TIME-SEC\r\n\t\tintegerp; how many seconds should be spent overall working on this problem\r\n@param MT\r\n\t\thlmt-p; where should any CycL queries be asked from\r\n@param LANGUAGE-MT\r\n\t\thlmt-p; from what Mt should all NL lookups be performed?\r\n@return listp; a list of query-results, each of which is the \'answer\' to one of the CycL queries that\r\nPRED-STRING and ARG-STRINGS were transformed into.  The answers are of the form\r\n(cycl-query RESULT error-string), where result is currently one of {:TRUE, :FALSE, :UNKNOWN}.")
    public static final SubLObject simple_boolean_query_nl_alt(SubLObject pred_string, SubLObject arg_strings, SubLObject max_time_sec, SubLObject mt, SubLObject language_mt) {
        if (max_time_sec == UNPROVIDED) {
            max_time_sec = $simple_boolean_nl_default_time$.getDynamicValue();
        }
        if (mt == UNPROVIDED) {
            mt = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.get_simple_query_default_mt();
        }
        if (language_mt == UNPROVIDED) {
            language_mt = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.get_simple_query_default_nl_mt();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject results = NIL;
                SubLObject timed_out = NIL;
                {
                    SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = parsing_macros.$parsing_timeout_time_check_count$.currentBinding(thread);
                    SubLObject _prev_bind_2 = parsing_macros.$parsing_timeout_time$.currentBinding(thread);
                    SubLObject _prev_bind_3 = parsing_macros.$parsing_timeout_reachedP$.currentBinding(thread);
                    try {
                        lexicon_vars.$lexicon_lookup_mt$.bind(language_mt, thread);
                        parsing_macros.$parsing_timeout_time_check_count$.bind(ZERO_INTEGER, thread);
                        parsing_macros.$parsing_timeout_time$.bind(parsing_macros.parsing_compute_timeout_time(max_time_sec), thread);
                        parsing_macros.$parsing_timeout_reachedP$.bind(parsing_macros.$parsing_timeout_reachedP$.getDynamicValue(thread), thread);
                        parsing_macros.$parsing_timeout_reachedP$.setDynamicValue(parsing_macros.parsing_timeout_time_reachedP(), thread);
                        if (NIL == parsing_macros.$parsing_timeout_reachedP$.getDynamicValue(thread)) {
                            {
                                SubLObject pred_interps = list_utilities.remove_if_not(PREDICATE_P, lexicon_accessors.denots_of_string(pred_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                SubLObject args_interps = Mapping.mapcar(NL_QUERY_DENOTS_OF_STRING, arg_strings);
                                SubLObject all_raw_semtrans_sentences = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.get_semtrans_sentences(pred_string, args_interps);
                                SubLObject all_raw_pred_sentences = list_utilities.cartesian_product(cons(pred_interps, args_interps), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                SubLObject all_pred_sentences = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.add_quantifiers_if_needed(all_raw_pred_sentences, mt);
                                SubLObject all_sentences = cconcatenate(all_raw_semtrans_sentences, all_pred_sentences);
                                SubLObject tag = with_timeout_make_tag();
                                try {
                                    {
                                        SubLObject _prev_bind_0_2 = $within_with_timeout$.currentBinding(thread);
                                        try {
                                            $within_with_timeout$.bind(T, thread);
                                            {
                                                SubLObject timer = NIL;
                                                try {
                                                    {
                                                        SubLObject _prev_bind_0_3 = $with_timeout_nesting_depth$.currentBinding(thread);
                                                        try {
                                                            $with_timeout_nesting_depth$.bind(add(ONE_INTEGER, $with_timeout_nesting_depth$.getDynamicValue(thread)), thread);
                                                            timer = with_timeout_start_timer(max_time_sec, tag);
                                                            {
                                                                SubLObject cdolist_list_var = all_sentences;
                                                                SubLObject query_sentence = NIL;
                                                                for (query_sentence = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , query_sentence = cdolist_list_var.first()) {
                                                                    {
                                                                        SubLObject datum = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.simple_boolean_query_cycl(query_sentence.first(), query_sentence.rest(), max_time_sec, mt);
                                                                        SubLObject current = datum;
                                                                        SubLObject result = NIL;
                                                                        SubLObject error_msg = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt20);
                                                                        result = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt20);
                                                                        error_msg = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            results = cons(list(query_sentence, result, error_msg), results);
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt20);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } finally {
                                                            $with_timeout_nesting_depth$.rebind(_prev_bind_0_3, thread);
                                                        }
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0_4 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                        try {
                                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                                            with_timeout_stop_timer(timer);
                                                        } finally {
                                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_4, thread);
                                                        }
                                                    }
                                                }
                                            }
                                        } finally {
                                            $within_with_timeout$.rebind(_prev_bind_0_2, thread);
                                        }
                                    }
                                } catch (Throwable ccatch_env_var) {
                                    timed_out = Errors.handleThrowable(ccatch_env_var, tag);
                                }
                            }
                        }
                        timed_out = parsing_macros.parsing_timeout_time_reachedP();
                    } finally {
                        parsing_macros.$parsing_timeout_reachedP$.rebind(_prev_bind_3, thread);
                        parsing_macros.$parsing_timeout_time$.rebind(_prev_bind_2, thread);
                        parsing_macros.$parsing_timeout_time_check_count$.rebind(_prev_bind_1, thread);
                        lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
                    }
                }
                if (NIL != timed_out) {
                    results = cons(list(NIL, $UNKNOWN, $$$Timed_out_before_completion), results);
                }
                return results;
            }
        }
    }

    /**
     *
     *
     * @param PRED-STRING
     * 		stringp; the string for the linguistic predicate (which might correspond to a CycL
     * 		predicate or an event-type
     * @param ARG-STRINGS
     * 		listp of stringp; a list of linguistic arguments to PRED-STRING.  They should be in
     * 		the order SUBJECT DIRECT-OBJECT INDIRECT-OBJECT; the list should contain at least one argument, but can
     * 		contain more.
     * @param MAX-TIME-SEC
     * 		integerp; how many seconds should be spent overall working on this problem
     * @param MT
     * 		hlmt-p; where should any CycL queries be asked from
     * @param LANGUAGE-MT
     * 		hlmt-p; from what Mt should all NL lookups be performed?
     * @return listp; a list of query-results, each of which is the 'answer' to one of the CycL queries that
    PRED-STRING and ARG-STRINGS were transformed into.  The answers are of the form
    (cycl-query RESULT error-string), where result is currently one of {:TRUE, :FALSE, :UNKNOWN}.
     */
    @LispMethod(comment = "@param PRED-STRING\r\n\t\tstringp; the string for the linguistic predicate (which might correspond to a CycL\r\n\t\tpredicate or an event-type\r\n@param ARG-STRINGS\r\n\t\tlistp of stringp; a list of linguistic arguments to PRED-STRING.  They should be in\r\n\t\tthe order SUBJECT DIRECT-OBJECT INDIRECT-OBJECT; the list should contain at least one argument, but can\r\n\t\tcontain more.\r\n@param MAX-TIME-SEC\r\n\t\tintegerp; how many seconds should be spent overall working on this problem\r\n@param MT\r\n\t\thlmt-p; where should any CycL queries be asked from\r\n@param LANGUAGE-MT\r\n\t\thlmt-p; from what Mt should all NL lookups be performed?\r\n@return listp; a list of query-results, each of which is the \'answer\' to one of the CycL queries that\r\nPRED-STRING and ARG-STRINGS were transformed into.  The answers are of the form\r\n(cycl-query RESULT error-string), where result is currently one of {:TRUE, :FALSE, :UNKNOWN}.")
    public static SubLObject simple_boolean_query_nl(final SubLObject pred_string, final SubLObject arg_strings, SubLObject max_time_sec, SubLObject mt, SubLObject language_mt) {
        if (max_time_sec == UNPROVIDED) {
            max_time_sec = $simple_boolean_nl_default_time$.getDynamicValue();
        }
        if (mt == UNPROVIDED) {
            mt = get_simple_query_default_mt();
        }
        if (language_mt == UNPROVIDED) {
            language_mt = get_simple_query_default_nl_mt();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject results = NIL;
        SubLObject timed_out = NIL;
        final SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = parsing_macros.$parsing_timeout_time_check_count$.currentBinding(thread);
        final SubLObject _prev_bind_3 = parsing_macros.$parsing_timeout_time$.currentBinding(thread);
        final SubLObject _prev_bind_4 = parsing_macros.$parsing_timeout_reachedP$.currentBinding(thread);
        try {
            lexicon_vars.$lexicon_lookup_mt$.bind(language_mt, thread);
            parsing_macros.$parsing_timeout_time_check_count$.bind(ZERO_INTEGER, thread);
            parsing_macros.$parsing_timeout_time$.bind(parsing_macros.parsing_compute_timeout_time(max_time_sec), thread);
            parsing_macros.$parsing_timeout_reachedP$.bind(parsing_macros.$parsing_timeout_reachedP$.getDynamicValue(thread), thread);
            parsing_macros.$parsing_timeout_reachedP$.setDynamicValue(parsing_macros.parsing_timeout_time_reachedP(), thread);
            if (NIL == parsing_macros.$parsing_timeout_reachedP$.getDynamicValue(thread)) {
                final SubLObject pred_interps = remove($$is_Underspecified, list_utilities.remove_if_not(PREDICATE_P, lexicon_accessors.denots_of_string(pred_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                final SubLObject args_interps = Mapping.mapcar(NL_QUERY_DENOTS_OF_STRING, arg_strings);
                final SubLObject all_raw_semtrans_sentences = get_semtrans_sentences(pred_string, args_interps);
                final SubLObject all_raw_pred_sentences = list_utilities.cartesian_product(cons(pred_interps, args_interps), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                final SubLObject all_pred_sentences = add_quantifiers_if_needed(all_raw_pred_sentences, mt);
                final SubLObject all_sentences = cconcatenate(all_raw_semtrans_sentences, all_pred_sentences);
                final SubLObject tag = with_timeout_make_tag();
                try {
                    thread.throwStack.push(tag);
                    final SubLObject _prev_bind_0_$2 = $within_with_timeout$.currentBinding(thread);
                    try {
                        $within_with_timeout$.bind(T, thread);
                        SubLObject timer = NIL;
                        try {
                            final SubLObject _prev_bind_0_$3 = $with_timeout_nesting_depth$.currentBinding(thread);
                            try {
                                $with_timeout_nesting_depth$.bind(add(ONE_INTEGER, $with_timeout_nesting_depth$.getDynamicValue(thread)), thread);
                                timer = with_timeout_start_timer(max_time_sec, tag);
                                SubLObject cdolist_list_var = all_sentences;
                                SubLObject query_sentence = NIL;
                                query_sentence = cdolist_list_var.first();
                                while (NIL != cdolist_list_var) {
                                    SubLObject current;
                                    final SubLObject datum = current = simple_boolean_query_cycl(query_sentence.first(), query_sentence.rest(), max_time_sec, mt);
                                    SubLObject result = NIL;
                                    SubLObject error_msg = NIL;
                                    destructuring_bind_must_consp(current, datum, $list22);
                                    result = current.first();
                                    current = current.rest();
                                    destructuring_bind_must_consp(current, datum, $list22);
                                    error_msg = current.first();
                                    current = current.rest();
                                    if (NIL == current) {
                                        final SubLObject pcase_var = result;
                                        if (pcase_var.eql($ERROR)) {
                                            result = $UNKNOWN;
                                        } else
                                            if (pcase_var.eql($UNKNOWN)) {
                                                result = $WFF;
                                            }

                                        results = cons(list(query_sentence, result, error_msg), results);
                                    } else {
                                        cdestructuring_bind_error(datum, $list22);
                                    }
                                    cdolist_list_var = cdolist_list_var.rest();
                                    query_sentence = cdolist_list_var.first();
                                } 
                            } finally {
                                $with_timeout_nesting_depth$.rebind(_prev_bind_0_$3, thread);
                            }
                        } finally {
                            final SubLObject _prev_bind_0_$4 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                final SubLObject _values = getValuesAsVector();
                                with_timeout_stop_timer(timer);
                                restoreValuesFromVector(_values);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$4, thread);
                            }
                        }
                    } finally {
                        $within_with_timeout$.rebind(_prev_bind_0_$2, thread);
                    }
                } catch (final Throwable ccatch_env_var) {
                    timed_out = Errors.handleThrowable(ccatch_env_var, tag);
                } finally {
                    thread.throwStack.pop();
                }
            }
            timed_out = parsing_macros.parsing_timeout_time_reachedP();
        } finally {
            parsing_macros.$parsing_timeout_reachedP$.rebind(_prev_bind_4, thread);
            parsing_macros.$parsing_timeout_time$.rebind(_prev_bind_3, thread);
            parsing_macros.$parsing_timeout_time_check_count$.rebind(_prev_bind_2, thread);
            lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
        }
        if (NIL != timed_out) {
            results = cons(list(NIL, $UNKNOWN, $$$Timed_out_before_completion), results);
        }
        return results;
    }

    public static final SubLObject clear_nl_query_denots_of_string_alt() {
        {
            SubLObject cs = $nl_query_denots_of_string_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_nl_query_denots_of_string() {
        final SubLObject cs = $nl_query_denots_of_string_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_nl_query_denots_of_string_alt(SubLObject string, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return memoization_state.caching_state_remove_function_results_with_args($nl_query_denots_of_string_caching_state$.getGlobalValue(), list(string, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_nl_query_denots_of_string(final SubLObject string, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return memoization_state.caching_state_remove_function_results_with_args($nl_query_denots_of_string_caching_state$.getGlobalValue(), list(string, mt), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject nl_query_denots_of_string_internal_alt(SubLObject string, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject determiner_free = lexicon_utilities.strip_determiner(string, UNPROVIDED);
                SubLObject possible_denots = Mapping.mapcar(SECOND, api_widgets.get_constrained_cycl_for_string(determiner_free, $$Thing, $const24$CurrentWorldDataCollectorMt_NonHo, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                SubLObject usable_denots = remove_if($sym25$BAD_FOR_TAGGING_, possible_denots, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                SubLObject ignore_errors_tag = NIL;
                try {
                    {
                        SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                        try {
                            Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
                            try {
                                {
                                    SubLObject compound_file = $str_alt28$_cyc_projects_doc_annotation_comp;
                                    SubLObject non_lexical = set_difference(usable_denots, concept_tagger.all_tagged_concepts(document.new_document(string, UNPROVIDED), UNPROVIDED), UNPROVIDED, UNPROVIDED);
                                    {
                                        SubLObject _prev_bind_0_5 = $print_pretty$.currentBinding(thread);
                                        try {
                                            $print_pretty$.bind(NIL, thread);
                                            if (NIL != non_lexical) {
                                                {
                                                    SubLObject stream = NIL;
                                                    try {
                                                        {
                                                            SubLObject _prev_bind_0_6 = stream_macros.$stream_requires_locking$.currentBinding(thread);
                                                            try {
                                                                stream_macros.$stream_requires_locking$.bind(NIL, thread);
                                                                stream = compatibility.open_text(compound_file, $APPEND, NIL);
                                                            } finally {
                                                                stream_macros.$stream_requires_locking$.rebind(_prev_bind_0_6, thread);
                                                            }
                                                        }
                                                        if (!stream.isStream()) {
                                                            Errors.error($str_alt30$Unable_to_open__S, compound_file);
                                                        }
                                                        {
                                                            SubLObject s = stream;
                                                            format(s, $str_alt31$_S_A_S_A_S__, new SubLObject[]{ string, CHAR_tab, non_lexical, CHAR_tab, kb_utilities.compact_hl_external_id_string(non_lexical) });
                                                        }
                                                    } finally {
                                                        {
                                                            SubLObject _prev_bind_0_7 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                            try {
                                                                $is_thread_performing_cleanupP$.bind(T, thread);
                                                                if (stream.isStream()) {
                                                                    close(stream, UNPROVIDED);
                                                                }
                                                            } finally {
                                                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_7, thread);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } finally {
                                            $print_pretty$.rebind(_prev_bind_0_5, thread);
                                        }
                                    }
                                }
                            } catch (Throwable catch_var) {
                                Errors.handleThrowable(catch_var, NIL);
                            }
                        } finally {
                            Errors.$error_handler$.rebind(_prev_bind_0, thread);
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
                }
                return usable_denots;
            }
        }
    }

    public static SubLObject nl_query_denots_of_string_internal(final SubLObject string, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject determiner_free = lexicon_utilities.strip_determiner(string, UNPROVIDED);
        final SubLObject possible_denots = Mapping.mapcar(SECOND, api_widgets.get_constrained_cycl_for_string(determiner_free, $$Thing, $const27$CurrentWorldDataCollectorMt_NonHo, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        final SubLObject usable_denots = remove_if($sym28$BAD_FOR_TAGGING_, possible_denots, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        SubLObject ignore_errors_tag = NIL;
        try {
            thread.throwStack.push($IGNORE_ERRORS_TARGET);
            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
            try {
                Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
                try {
                    final SubLObject compound_file = $str31$_cyc_projects_doc_annotation_comp;
                    final SubLObject non_lexical = set_difference(usable_denots, concept_tagger.all_tagged_concepts(document.new_document(string, UNPROVIDED, UNPROVIDED), UNPROVIDED), UNPROVIDED, UNPROVIDED);
                    final SubLObject _prev_bind_0_$5 = $print_pretty$.currentBinding(thread);
                    try {
                        $print_pretty$.bind(NIL, thread);
                        if (NIL != non_lexical) {
                            SubLObject stream = NIL;
                            try {
                                final SubLObject _prev_bind_0_$6 = stream_macros.$stream_requires_locking$.currentBinding(thread);
                                try {
                                    stream_macros.$stream_requires_locking$.bind(NIL, thread);
                                    stream = compatibility.open_text(compound_file, $APPEND);
                                } finally {
                                    stream_macros.$stream_requires_locking$.rebind(_prev_bind_0_$6, thread);
                                }
                                if (!stream.isStream()) {
                                    Errors.error($str33$Unable_to_open__S, compound_file);
                                }
                                final SubLObject s = stream;
                                format(s, $str34$_S_A_S_A_S__, new SubLObject[]{ string, CHAR_tab, non_lexical, CHAR_tab, kb_utilities.compact_hl_external_id_string(non_lexical) });
                            } finally {
                                final SubLObject _prev_bind_0_$7 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    if (stream.isStream()) {
                                        close(stream, UNPROVIDED);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$7, thread);
                                }
                            }
                        }
                    } finally {
                        $print_pretty$.rebind(_prev_bind_0_$5, thread);
                    }
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
        return usable_denots;
    }

    public static final SubLObject nl_query_denots_of_string_alt(SubLObject string, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            SubLObject caching_state = $nl_query_denots_of_string_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(NL_QUERY_DENOTS_OF_STRING, $nl_query_denots_of_string_caching_state$, $int$128000, EQUALP, TWO_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(string, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw34$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (string.equalp(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equalp(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.nl_query_denots_of_string_internal(string, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(string, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject nl_query_denots_of_string(final SubLObject string, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        SubLObject caching_state = $nl_query_denots_of_string_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(NL_QUERY_DENOTS_OF_STRING, $nl_query_denots_of_string_caching_state$, $int$128000, EQUALP, TWO_INTEGER, ZERO_INTEGER);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(string, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (string.equalp(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equalp(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(nl_query_denots_of_string_internal(string, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(string, mt));
        return memoization_state.caching_results(results3);
    }

    /**
     *
     *
     * @param SENTENCES
     * 		listp; a list of cycl-sentence-p
     * @param MT
    hlmt-p
     * 		
     * @return listp of cycl-sentence-p; a list of CycL sentences with quantification weakly corrected.
    In other words, if given an arg2 that is a collection and a pred that requires an individual,
    return a sentence with quantifiers that at least make the sentence meaningful to ask
     */
    @LispMethod(comment = "@param SENTENCES\r\n\t\tlistp; a list of cycl-sentence-p\r\n@param MT\nhlmt-p\r\n\t\t\r\n@return listp of cycl-sentence-p; a list of CycL sentences with quantification weakly corrected.\r\nIn other words, if given an arg2 that is a collection and a pred that requires an individual,\r\nreturn a sentence with quantifiers that at least make the sentence meaningful to ask")
    public static final SubLObject add_quantifiers_if_needed_alt(SubLObject sentences, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject all_sentences = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        {
                            SubLObject cdolist_list_var = sentences;
                            SubLObject sentence = NIL;
                            for (sentence = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sentence = cdolist_list_var.first()) {
                                {
                                    SubLObject pred = cycl_utilities.formula_arg0(sentence);
                                    if (NIL != kb_accessors.binary_predicateP(pred)) {
                                        {
                                            SubLObject arg1_predicted_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.pred_arg_expected_order(pred, ONE_INTEGER);
                                            SubLObject arg2_predicted_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.pred_arg_expected_order(pred, TWO_INTEGER);
                                            SubLObject arg1_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.term_order_integer(cycl_utilities.formula_arg1(sentence, UNPROVIDED));
                                            SubLObject arg2_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.term_order_integer(cycl_utilities.formula_arg2(sentence, UNPROVIDED));
                                            if ((arg1_predicted_order == arg1_order) && (arg2_predicted_order == arg2_order)) {
                                                all_sentences = cons(sentence, all_sentences);
                                            } else {
                                                if (arg1_predicted_order.numL(arg1_order) && (arg2_predicted_order == arg2_order)) {
                                                    all_sentences = cons(cons($$relationExistsInstance, sentence), all_sentences);
                                                } else {
                                                    if (arg2_predicted_order.numL(arg2_order) && (arg1_predicted_order == arg1_order)) {
                                                        all_sentences = cons(cons($$relationInstanceExists, sentence), all_sentences);
                                                    } else {
                                                        if (arg2_predicted_order.numL(arg2_order) && arg2_predicted_order.numL(arg2_order)) {
                                                            all_sentences = cons(cons($$relationExistsExists, sentence), all_sentences);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return all_sentences;
            }
        }
    }

    /**
     *
     *
     * @param SENTENCES
     * 		listp; a list of cycl-sentence-p
     * @param MT
    hlmt-p
     * 		
     * @return listp of cycl-sentence-p; a list of CycL sentences with quantification weakly corrected.
    In other words, if given an arg2 that is a collection and a pred that requires an individual,
    return a sentence with quantifiers that at least make the sentence meaningful to ask
     */
    @LispMethod(comment = "@param SENTENCES\r\n\t\tlistp; a list of cycl-sentence-p\r\n@param MT\nhlmt-p\r\n\t\t\r\n@return listp of cycl-sentence-p; a list of CycL sentences with quantification weakly corrected.\r\nIn other words, if given an arg2 that is a collection and a pred that requires an individual,\r\nreturn a sentence with quantifiers that at least make the sentence meaningful to ask")
    public static SubLObject add_quantifiers_if_needed(final SubLObject sentences, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject all_sentences = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            SubLObject cdolist_list_var = sentences;
            SubLObject sentence = NIL;
            sentence = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject pred = cycl_utilities.formula_arg0(sentence);
                if (NIL != kb_accessors.binary_predicateP(pred)) {
                    final SubLObject arg1_predicted_order = pred_arg_expected_order(pred, ONE_INTEGER);
                    final SubLObject arg2_predicted_order = pred_arg_expected_order(pred, TWO_INTEGER);
                    final SubLObject arg1_order = term_order_integer(cycl_utilities.formula_arg1(sentence, UNPROVIDED));
                    final SubLObject arg2_order = term_order_integer(cycl_utilities.formula_arg2(sentence, UNPROVIDED));
                    if (arg1_predicted_order.eql(arg1_order) && arg2_predicted_order.eql(arg2_order)) {
                        all_sentences = cons(sentence, all_sentences);
                    } else
                        if (arg1_predicted_order.numL(arg1_order) && arg2_predicted_order.eql(arg2_order)) {
                            all_sentences = cons(cons($$relationExistsInstance, sentence), all_sentences);
                        } else
                            if (arg2_predicted_order.numL(arg2_order) && arg1_predicted_order.eql(arg1_order)) {
                                all_sentences = cons(cons($$relationInstanceExists, sentence), all_sentences);
                            } else
                                if (arg2_predicted_order.numL(arg2_order) && arg2_predicted_order.numL(arg2_order)) {
                                    all_sentences = cons(cons($$relationExistsExists, sentence), all_sentences);
                                }



                }
                cdolist_list_var = cdolist_list_var.rest();
                sentence = cdolist_list_var.first();
            } 
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return all_sentences;
    }

    /**
     *
     *
     * @return integer; is this a first, second, or zeroth (i.e. Individual), order term?
     */
    @LispMethod(comment = "@return integer; is this a first, second, or zeroth (i.e. Individual), order term?")
    public static final SubLObject term_order_integer_alt(SubLObject v_term) {
        if (NIL != kb_accessors.individualP(v_term)) {
            return ZERO_INTEGER;
        } else {
            if (NIL != com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.first_order_collectionP(v_term, UNPROVIDED)) {
                return ONE_INTEGER;
            } else {
                if (NIL != builder_utilities.higher_order_collectionP(v_term)) {
                    return TWO_INTEGER;
                } else {
                    if (NIL != com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.variable_order_collectionP(v_term, UNPROVIDED)) {
                        return $VARIABLE;
                    } else {
                        return NIL;
                    }
                }
            }
        }
    }

    /**
     *
     *
     * @return integer; is this a first, second, or zeroth (i.e. Individual), order term?
     */
    @LispMethod(comment = "@return integer; is this a first, second, or zeroth (i.e. Individual), order term?")
    public static SubLObject term_order_integer(final SubLObject v_term) {
        final SubLObject canon_term = czer_main.canonicalize_term(v_term, UNPROVIDED);
        if (NIL != kb_accessors.individualP(canon_term)) {
            return ZERO_INTEGER;
        }
        if (NIL != first_order_collectionP(canon_term, UNPROVIDED)) {
            return ONE_INTEGER;
        }
        if (NIL != builder_utilities.higher_order_collectionP(canon_term)) {
            return TWO_INTEGER;
        }
        if (NIL != variable_order_collectionP(canon_term, UNPROVIDED)) {
            return $VARIABLE;
        }
        return NIL;
    }

    public static final SubLObject clear_first_order_collectionP_alt() {
        {
            SubLObject cs = $first_order_collectionP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_first_order_collectionP() {
        final SubLObject cs = $first_order_collectionP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_first_order_collectionP_alt(SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($first_order_collectionP_caching_state$.getGlobalValue(), list(v_term, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_first_order_collectionP(final SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($first_order_collectionP_caching_state$.getGlobalValue(), list(v_term, mt), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject first_order_collectionP_internal_alt(SubLObject v_term, SubLObject mt) {
        return isa.isaP(v_term, $$FirstOrderCollection, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject first_order_collectionP_internal(final SubLObject v_term, final SubLObject mt) {
        return isa.isaP(v_term, $$FirstOrderCollection, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject first_order_collectionP_alt(SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        {
            SubLObject caching_state = $first_order_collectionP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym39$FIRST_ORDER_COLLECTION_, $sym41$_FIRST_ORDER_COLLECTION__CACHING_STATE_, NIL, EQUAL, TWO_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(v_term, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw34$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (v_term.equal(cached_args.first())) {
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
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.first_order_collectionP_internal(v_term, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(v_term, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject first_order_collectionP(final SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        SubLObject caching_state = $first_order_collectionP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym41$FIRST_ORDER_COLLECTION_, $sym43$_FIRST_ORDER_COLLECTION__CACHING_STATE_, NIL, EQUAL, TWO_INTEGER, ZERO_INTEGER);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(v_term, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (v_term.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(first_order_collectionP_internal(v_term, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(v_term, mt));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject clear_variable_order_collectionP_alt() {
        {
            SubLObject cs = $variable_order_collectionP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_variable_order_collectionP() {
        final SubLObject cs = $variable_order_collectionP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_variable_order_collectionP_alt(SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($variable_order_collectionP_caching_state$.getGlobalValue(), list(v_term, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_variable_order_collectionP(final SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($variable_order_collectionP_caching_state$.getGlobalValue(), list(v_term, mt), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject variable_order_collectionP_internal_alt(SubLObject v_term, SubLObject mt) {
        return isa.isaP(v_term, $$VariedOrderCollection, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject variable_order_collectionP_internal(final SubLObject v_term, final SubLObject mt) {
        return isa.isaP(v_term, $$VariedOrderCollection, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject variable_order_collectionP_alt(SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        {
            SubLObject caching_state = $variable_order_collectionP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym42$VARIABLE_ORDER_COLLECTION_, $sym44$_VARIABLE_ORDER_COLLECTION__CACHING_STATE_, NIL, EQUAL, TWO_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(v_term, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw34$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (v_term.equal(cached_args.first())) {
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
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.variable_order_collectionP_internal(v_term, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(v_term, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject variable_order_collectionP(final SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        SubLObject caching_state = $variable_order_collectionP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym44$VARIABLE_ORDER_COLLECTION_, $sym46$_VARIABLE_ORDER_COLLECTION__CACHING_STATE_, NIL, EQUAL, TWO_INTEGER, ZERO_INTEGER);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(v_term, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (v_term.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(variable_order_collectionP_internal(v_term, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(v_term, mt));
        return memoization_state.caching_results(results3);
    }

    /**
     *
     *
     * @return element of {0,1,2}: is the ARGNUM arg of PRED expected to be an individual (0),
    a first order collection (1), or a higher order collection (2)?
     */
    @LispMethod(comment = "@return element of {0,1,2}: is the ARGNUM arg of PRED expected to be an individual (0),\r\na first order collection (1), or a higher order collection (2)?")
    public static final SubLObject pred_arg_expected_order_alt(SubLObject pred, SubLObject argnum) {
        {
            SubLObject cdolist_list_var = kb_accessors.argn_isa(pred, argnum, UNPROVIDED);
            SubLObject arg_isa_constraint = NIL;
            for (arg_isa_constraint = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg_isa_constraint = cdolist_list_var.first()) {
                if (NIL != builder_utilities.higher_order_collectionP(arg_isa_constraint)) {
                    return ONE_INTEGER;
                } else {
                    if (NIL != fort_types_interface.collectionP(arg_isa_constraint)) {
                        return ZERO_INTEGER;
                    }
                }
            }
        }
        {
            SubLObject cdolist_list_var = kb_accessors.argn_genl(pred, argnum, UNPROVIDED);
            SubLObject arg_genl_constraint = NIL;
            for (arg_genl_constraint = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg_genl_constraint = cdolist_list_var.first()) {
                if (NIL != builder_utilities.higher_order_collectionP(arg_genl_constraint)) {
                    return TWO_INTEGER;
                } else {
                    if (NIL != fort_types_interface.collectionP(arg_genl_constraint)) {
                        return ONE_INTEGER;
                    }
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return element of {0,1,2}: is the ARGNUM arg of PRED expected to be an individual (0),
    a first order collection (1), or a higher order collection (2)?
     */
    @LispMethod(comment = "@return element of {0,1,2}: is the ARGNUM arg of PRED expected to be an individual (0),\r\na first order collection (1), or a higher order collection (2)?")
    public static SubLObject pred_arg_expected_order(final SubLObject pred, final SubLObject argnum) {
        SubLObject cdolist_list_var = kb_accessors.argn_isa(pred, argnum, UNPROVIDED);
        SubLObject arg_isa_constraint = NIL;
        arg_isa_constraint = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != builder_utilities.higher_order_collectionP(arg_isa_constraint)) {
                return ONE_INTEGER;
            }
            if (NIL != fort_types_interface.collectionP(arg_isa_constraint)) {
                return ZERO_INTEGER;
            }
            cdolist_list_var = cdolist_list_var.rest();
            arg_isa_constraint = cdolist_list_var.first();
        } 
        cdolist_list_var = kb_accessors.argn_genl(pred, argnum, UNPROVIDED);
        SubLObject arg_genl_constraint = NIL;
        arg_genl_constraint = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != builder_utilities.higher_order_collectionP(arg_genl_constraint)) {
                return TWO_INTEGER;
            }
            if (NIL != fort_types_interface.collectionP(arg_genl_constraint)) {
                return ONE_INTEGER;
            }
            cdolist_list_var = cdolist_list_var.rest();
            arg_genl_constraint = cdolist_list_var.first();
        } 
        return NIL;
    }

    public static final SubLObject get_semtrans_sentences_alt(SubLObject pred_string, SubLObject arg_cycls) {
        {
            SubLObject lex = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.get_simple_inference_semtrans_lexicon();
            SubLObject lex_entries = methods.funcall_instance_method_with_1_args(lex, GET, pred_string);
            SubLObject semtrans_sentences = NIL;
            SubLObject cdolist_list_var = lex_entries;
            SubLObject entry = NIL;
            for (entry = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , entry = cdolist_list_var.first()) {
                {
                    SubLObject semtrans = methods.funcall_instance_method_with_1_args(entry, GET, $SEMTRANS);
                    SubLObject pos = methods.funcall_instance_method_with_1_args(entry, GET, $CYC_POS);
                    SubLObject frame = methods.funcall_instance_method_with_1_args(entry, GET, $FRAME);
                    if ((NIL != lexicon_accessors.genl_posP(pos, $$Verb, UNPROVIDED)) && (frame == $$TransitiveNPFrame)) {
                        {
                            SubLObject cdolist_list_var_8 = arg_cycls.first();
                            SubLObject arg1 = NIL;
                            for (arg1 = cdolist_list_var_8.first(); NIL != cdolist_list_var_8; cdolist_list_var_8 = cdolist_list_var_8.rest() , arg1 = cdolist_list_var_8.first()) {
                                {
                                    SubLObject cdolist_list_var_9 = second(arg_cycls);
                                    SubLObject arg2 = NIL;
                                    for (arg2 = cdolist_list_var_9.first(); NIL != cdolist_list_var_9; cdolist_list_var_9 = cdolist_list_var_9.rest() , arg2 = cdolist_list_var_9.first()) {
                                        {
                                            SubLObject cdolist_list_var_10 = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.add_quantifiers_to_semtrans_if_needed(semtrans, arg1, arg2, UNPROVIDED);
                                            SubLObject quantified = NIL;
                                            for (quantified = cdolist_list_var_10.first(); NIL != cdolist_list_var_10; cdolist_list_var_10 = cdolist_list_var_10.rest() , quantified = cdolist_list_var_10.first()) {
                                                {
                                                    SubLObject verb_substituted = (NIL != cycl_utilities.expression_find($ACTION, quantified, UNPROVIDED, UNPROVIDED, UNPROVIDED)) ? ((SubLObject) (make_formula($$thereExists, list($sym53$_EVENT, cycl_utilities.expression_subst($sym53$_EVENT, $ACTION, quantified, UNPROVIDED, UNPROVIDED)), UNPROVIDED))) : quantified;
                                                    if (NIL == cycl_utilities.expression_find_if(KEYWORDP, verb_substituted, UNPROVIDED, UNPROVIDED)) {
                                                        semtrans_sentences = cons(verb_substituted, semtrans_sentences);
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
            return semtrans_sentences;
        }
    }

    public static SubLObject get_semtrans_sentences(final SubLObject pred_string, final SubLObject arg_cycls) {
        final SubLObject lex = get_simple_inference_semtrans_lexicon();
        final SubLObject lex_entries = methods.funcall_instance_method_with_1_args(lex, GET, pred_string);
        SubLObject semtrans_sentences = NIL;
        SubLObject cdolist_list_var = lex_entries;
        SubLObject entry = NIL;
        entry = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject semtrans = methods.funcall_instance_method_with_1_args(entry, GET, $SEMTRANS);
            final SubLObject pos = methods.funcall_instance_method_with_1_args(entry, GET, $CYC_POS);
            final SubLObject frame = methods.funcall_instance_method_with_1_args(entry, GET, $FRAME);
            if ((NIL != lexicon_accessors.genl_posP(pos, $$Verb, UNPROVIDED)) && frame.eql($$TransitiveNPFrame)) {
                SubLObject cdolist_list_var_$8 = arg_cycls.first();
                SubLObject arg1 = NIL;
                arg1 = cdolist_list_var_$8.first();
                while (NIL != cdolist_list_var_$8) {
                    SubLObject cdolist_list_var_$9 = second(arg_cycls);
                    SubLObject arg2 = NIL;
                    arg2 = cdolist_list_var_$9.first();
                    while (NIL != cdolist_list_var_$9) {
                        SubLObject cdolist_list_var_$10 = add_quantifiers_to_semtrans_if_needed(semtrans, arg1, arg2, UNPROVIDED);
                        SubLObject quantified = NIL;
                        quantified = cdolist_list_var_$10.first();
                        while (NIL != cdolist_list_var_$10) {
                            final SubLObject verb_substituted = (NIL != cycl_utilities.expression_find($ACTION, quantified, UNPROVIDED, UNPROVIDED, UNPROVIDED)) ? make_formula($$thereExists, list($sym55$_EVENT, cycl_utilities.expression_subst($sym55$_EVENT, $ACTION, quantified, UNPROVIDED, UNPROVIDED)), UNPROVIDED) : quantified;
                            if (NIL == cycl_utilities.expression_find_if(KEYWORDP, verb_substituted, UNPROVIDED, UNPROVIDED)) {
                                semtrans_sentences = cons(verb_substituted, semtrans_sentences);
                            }
                            cdolist_list_var_$10 = cdolist_list_var_$10.rest();
                            quantified = cdolist_list_var_$10.first();
                        } 
                        cdolist_list_var_$9 = cdolist_list_var_$9.rest();
                        arg2 = cdolist_list_var_$9.first();
                    } 
                    cdolist_list_var_$8 = cdolist_list_var_$8.rest();
                    arg1 = cdolist_list_var_$8.first();
                } 
            }
            cdolist_list_var = cdolist_list_var.rest();
            entry = cdolist_list_var.first();
        } 
        return semtrans_sentences;
    }

    /**
     *
     *
     * @param SEMTRANS-TEMPLATE
     * 		cycl-sentence-p; a semtrans-template
     * @param SUBJ
     * 		cyc-denotational-term-p; the 'subject' of the sentence
     * @param OBJ
     * 		cyc-denotational-term-p; the 'object' of the sentence
     * @param MT
    hlmt-p
     * 		
     * @return listp of cycl-sentence-p; SEMTRANS-TEMPLATE with ARG1 and ARG2 substituted in, and with
    quantification weakly corrected. In other words, if given an arg2 that is a collection
    and a pred that requires an individual, return a sentence with quantifiers that at least
    make the sentence meaningful to ask.
     */
    @LispMethod(comment = "@param SEMTRANS-TEMPLATE\r\n\t\tcycl-sentence-p; a semtrans-template\r\n@param SUBJ\r\n\t\tcyc-denotational-term-p; the \'subject\' of the sentence\r\n@param OBJ\r\n\t\tcyc-denotational-term-p; the \'object\' of the sentence\r\n@param MT\nhlmt-p\r\n\t\t\r\n@return listp of cycl-sentence-p; SEMTRANS-TEMPLATE with ARG1 and ARG2 substituted in, and with\r\nquantification weakly corrected. In other words, if given an arg2 that is a collection\r\nand a pred that requires an individual, return a sentence with quantifiers that at least\r\nmake the sentence meaningful to ask.")
    public static final SubLObject add_quantifiers_to_semtrans_if_needed_alt(SubLObject semtrans_template, SubLObject subj, SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject quantified = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        {
                            SubLObject constraints = at_var_types.inter_formula_variables_arg_constraints(semtrans_template, mt, KEYWORDP);
                            SubLObject subj_predicted_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.arg_expected_order_from_constraints(constraints, $SUBJECT);
                            SubLObject obj_predicted_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.arg_expected_order_from_constraints(constraints, $OBJECT);
                            SubLObject subj_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.term_order_integer(subj);
                            SubLObject obj_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.term_order_integer(obj);
                            if (NIL == subj_order) {
                                quantified = cons(semtrans_template, quantified);
                            } else {
                                if (subj_predicted_order == $VARIABLE) {
                                    quantified = cons(list($$thereExists, $sym57$_SUBJ, list($$and, $list_alt59, cycl_utilities.expression_subst($sym57$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))), quantified);
                                    quantified = cons(semtrans_template, quantified);
                                } else {
                                    if (number_utilities.f_1X(subj_predicted_order).numE(subj_order)) {
                                        quantified = cons(list($$thereExists, $sym57$_SUBJ, list($$and, $list_alt59, cycl_utilities.expression_subst($sym57$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))), quantified);
                                    } else {
                                        if (add(TWO_INTEGER, subj_predicted_order).numE(subj_order)) {
                                            quantified = cons(list($$thereExists, $sym57$_SUBJ, list($$and, $list_alt59, cycl_utilities.expression_subst($sym57$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))), quantified);
                                            quantified = cons(list($$thereExists, $sym60$_FIRST_ORDER_SUBJ, list($$and, $list_alt61, list($$thereExists, $sym57$_SUBJ, list($$and, $list_alt62, cycl_utilities.expression_subst($sym57$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))))), quantified);
                                        } else {
                                            quantified = cons(semtrans_template, quantified);
                                        }
                                    }
                                }
                            }
                            {
                                SubLObject obj_quantified = NIL;
                                SubLObject cdolist_list_var = quantified;
                                SubLObject curr_quantified = NIL;
                                for (curr_quantified = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , curr_quantified = cdolist_list_var.first()) {
                                    if (NIL == obj_order) {
                                        obj_quantified = cons(curr_quantified, obj_quantified);
                                    } else {
                                        if (obj_predicted_order == $VARIABLE) {
                                            obj_quantified = cons(list($$thereExists, $sym63$_OBJ, list($$and, $list_alt64, cycl_utilities.expression_subst($sym63$_OBJ, $OBJECT, curr_quantified, UNPROVIDED, UNPROVIDED))), obj_quantified);
                                            obj_quantified = cons(curr_quantified, obj_quantified);
                                        } else {
                                            if (obj_predicted_order.numL(obj_order)) {
                                                obj_quantified = cons(list($$thereExists, $sym63$_OBJ, list($$and, $list_alt64, cycl_utilities.expression_subst($sym63$_OBJ, $OBJECT, curr_quantified, UNPROVIDED, UNPROVIDED))), obj_quantified);
                                            } else {
                                                obj_quantified = cons(curr_quantified, obj_quantified);
                                            }
                                        }
                                    }
                                }
                                quantified = obj_quantified;
                            }
                            {
                                SubLObject substituted = NIL;
                                SubLObject cdolist_list_var = quantified;
                                SubLObject sent = NIL;
                                for (sent = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sent = cdolist_list_var.first()) {
                                    sent = cycl_utilities.expression_subst(subj, $SUBJECT, sent, UNPROVIDED, UNPROVIDED);
                                    substituted = cons(cycl_utilities.expression_subst(obj, $OBJECT, sent, UNPROVIDED, UNPROVIDED), substituted);
                                }
                                quantified = substituted;
                            }
                        }
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return quantified;
            }
        }
    }

    /**
     *
     *
     * @param SEMTRANS-TEMPLATE
     * 		cycl-sentence-p; a semtrans-template
     * @param SUBJ
     * 		cyc-denotational-term-p; the 'subject' of the sentence
     * @param OBJ
     * 		cyc-denotational-term-p; the 'object' of the sentence
     * @param MT
    hlmt-p
     * 		
     * @return listp of cycl-sentence-p; SEMTRANS-TEMPLATE with ARG1 and ARG2 substituted in, and with
    quantification weakly corrected. In other words, if given an arg2 that is a collection
    and a pred that requires an individual, return a sentence with quantifiers that at least
    make the sentence meaningful to ask.
     */
    @LispMethod(comment = "@param SEMTRANS-TEMPLATE\r\n\t\tcycl-sentence-p; a semtrans-template\r\n@param SUBJ\r\n\t\tcyc-denotational-term-p; the \'subject\' of the sentence\r\n@param OBJ\r\n\t\tcyc-denotational-term-p; the \'object\' of the sentence\r\n@param MT\nhlmt-p\r\n\t\t\r\n@return listp of cycl-sentence-p; SEMTRANS-TEMPLATE with ARG1 and ARG2 substituted in, and with\r\nquantification weakly corrected. In other words, if given an arg2 that is a collection\r\nand a pred that requires an individual, return a sentence with quantifiers that at least\r\nmake the sentence meaningful to ask.")
    public static SubLObject add_quantifiers_to_semtrans_if_needed(final SubLObject semtrans_template, final SubLObject subj, final SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = mt_relevance_macros.$mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject quantified = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            final SubLObject constraints = at_var_types.inter_formula_variables_arg_constraints(semtrans_template, mt, KEYWORDP);
            final SubLObject subj_predicted_order = arg_expected_order_from_constraints(constraints, $SUBJECT);
            final SubLObject obj_predicted_order = arg_expected_order_from_constraints(constraints, $OBJECT);
            final SubLObject subj_order = term_order_integer(subj);
            final SubLObject obj_order = term_order_integer(obj);
            if (NIL == subj_order) {
                quantified = cons(semtrans_template, quantified);
            } else
                if (subj_predicted_order == $VARIABLE) {
                    quantified = cons(list($$thereExists, $sym59$_SUBJ, list($$and, $list61, cycl_utilities.expression_subst($sym59$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))), quantified);
                    quantified = cons(semtrans_template, quantified);
                } else
                    if (number_utilities.f_1X(subj_predicted_order).numE(subj_order)) {
                        quantified = cons(list($$thereExists, $sym59$_SUBJ, list($$and, $list61, cycl_utilities.expression_subst($sym59$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))), quantified);
                    } else
                        if (add(TWO_INTEGER, subj_predicted_order).numE(subj_order)) {
                            quantified = cons(list($$thereExists, $sym59$_SUBJ, list($$and, $list61, cycl_utilities.expression_subst($sym59$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))), quantified);
                            quantified = cons(list($$thereExists, $sym62$_FIRST_ORDER_SUBJ, list($$and, $list63, list($$thereExists, $sym59$_SUBJ, list($$and, $list64, cycl_utilities.expression_subst($sym59$_SUBJ, $SUBJECT, semtrans_template, UNPROVIDED, UNPROVIDED))))), quantified);
                        } else {
                            quantified = cons(semtrans_template, quantified);
                        }



            SubLObject obj_quantified = NIL;
            SubLObject cdolist_list_var = quantified;
            SubLObject curr_quantified = NIL;
            curr_quantified = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL == obj_order) {
                    obj_quantified = cons(curr_quantified, obj_quantified);
                } else
                    if (obj_predicted_order == $VARIABLE) {
                        obj_quantified = cons(list($$thereExists, $sym65$_OBJ, list($$and, $list66, cycl_utilities.expression_subst($sym65$_OBJ, $OBJECT, curr_quantified, UNPROVIDED, UNPROVIDED))), obj_quantified);
                        obj_quantified = cons(curr_quantified, obj_quantified);
                    } else
                        if (obj_predicted_order.numL(obj_order)) {
                            obj_quantified = cons(list($$thereExists, $sym65$_OBJ, list($$and, $list66, cycl_utilities.expression_subst($sym65$_OBJ, $OBJECT, curr_quantified, UNPROVIDED, UNPROVIDED))), obj_quantified);
                        } else {
                            obj_quantified = cons(curr_quantified, obj_quantified);
                        }


                cdolist_list_var = cdolist_list_var.rest();
                curr_quantified = cdolist_list_var.first();
            } 
            quantified = obj_quantified;
            SubLObject substituted = NIL;
            cdolist_list_var = quantified;
            SubLObject sent = NIL;
            sent = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                sent = cycl_utilities.expression_subst(subj, $SUBJECT, sent, UNPROVIDED, UNPROVIDED);
                substituted = cons(cycl_utilities.expression_subst(obj, $OBJECT, sent, UNPROVIDED, UNPROVIDED), substituted);
                cdolist_list_var = cdolist_list_var.rest();
                sent = cdolist_list_var.first();
            } 
            quantified = substituted;
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return quantified;
    }

    public static final SubLObject arg_expected_order_from_constraints_alt(SubLObject constraints, SubLObject v_term) {
        {
            SubLObject term_arg_poses = cycl_utilities.arg_positions_bfs(v_term, constraints, UNPROVIDED);
            SubLObject cdolist_list_var = term_arg_poses;
            SubLObject term_arg_pos = NIL;
            for (term_arg_pos = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , term_arg_pos = cdolist_list_var.first()) {
                {
                    SubLObject constraint = cycl_utilities.formula_arg_position(constraints, butlast(term_arg_pos, UNPROVIDED), UNPROVIDED);
                    SubLObject constraint_order = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.term_order_integer(cycl_utilities.formula_arg2(constraint, UNPROVIDED));
                    if (constraint_order.isKeyword()) {
                        return constraint_order;
                    } else {
                        if (cycl_utilities.formula_arg0(constraint) == $$genls) {
                            return constraint_order;
                        } else {
                            if (cycl_utilities.formula_arg0(constraint) == $$isa) {
                                return constraint_order.isNumber() ? ((SubLObject) (subtract(constraint_order, ONE_INTEGER))) : constraint_order;
                            }
                        }
                    }
                }
            }
            return ZERO_INTEGER;
        }
    }

    public static SubLObject arg_expected_order_from_constraints(final SubLObject constraints, final SubLObject v_term) {
        SubLObject cdolist_list_var;
        final SubLObject term_arg_poses = cdolist_list_var = cycl_utilities.arg_positions_bfs(v_term, constraints, UNPROVIDED);
        SubLObject term_arg_pos = NIL;
        term_arg_pos = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject constraint = cycl_utilities.formula_arg_position(constraints, butlast(term_arg_pos, UNPROVIDED), UNPROVIDED);
            final SubLObject constraint_order = term_order_integer(cycl_utilities.formula_arg2(constraint, UNPROVIDED));
            if (constraint_order.isKeyword()) {
                return constraint_order;
            }
            if (cycl_utilities.formula_arg0(constraint).eql($$genls)) {
                return constraint_order;
            }
            if (cycl_utilities.formula_arg0(constraint).eql($$isa)) {
                return constraint_order.isNumber() ? subtract(constraint_order, ONE_INTEGER) : constraint_order;
            }
            cdolist_list_var = cdolist_list_var.rest();
            term_arg_pos = cdolist_list_var.first();
        } 
        return ZERO_INTEGER;
    }

    public static final SubLObject get_simple_inference_semtrans_lexicon_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if ($get_simple_inference_semtrans_lexicon$.getDynamicValue(thread) == $UNINITIALIZED) {
                {
                    SubLObject init_value = eval(com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.make_simple_inference_semtrans_lexicon());
                    if (init_value == $UNINITIALIZED) {
                        Errors.error($str_alt68$Unable_to_initialize__A__, $get_simple_inference_semtrans_lexicon$.getDynamicValue(thread));
                    }
                    $get_simple_inference_semtrans_lexicon$.setDynamicValue(eval(init_value), thread);
                }
            }
            return $get_simple_inference_semtrans_lexicon$.getDynamicValue(thread);
        }
    }

    public static SubLObject get_simple_inference_semtrans_lexicon() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ($get_simple_inference_semtrans_lexicon$.getDynamicValue(thread) == $UNINITIALIZED) {
            final SubLObject init_value = make_simple_inference_semtrans_lexicon();
            if (init_value == $UNINITIALIZED) {
                Errors.error($str70$Unable_to_initialize__A__, $get_simple_inference_semtrans_lexicon$.getDynamicValue(thread));
            }
            $get_simple_inference_semtrans_lexicon$.setDynamicValue(init_value, thread);
        }
        return $get_simple_inference_semtrans_lexicon$.getDynamicValue(thread);
    }

    public static final SubLObject make_simple_inference_semtrans_lexicon_alt() {
        {
            SubLObject lex = semtrans_lexicon.new_semtrans_lexicon();
            methods.funcall_instance_method_with_0_args(lex, IGNORE_CACHE);
            return lex;
        }
    }

    public static SubLObject make_simple_inference_semtrans_lexicon() {
        final SubLObject lex = semtrans_lexicon.new_semtrans_lexicon();
        methods.funcall_instance_method_with_0_args(lex, IGNORE_CACHE);
        return lex;
    }

    public static final SubLObject simple_boolean_query_test_alt(SubLObject pred, SubLObject args, SubLObject time, SubLObject mt) {
        if (time == UNPROVIDED) {
            time = ONE_INTEGER;
        }
        if (mt == UNPROVIDED) {
            mt = com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.get_simple_query_default_mt();
        }
        return com.cyc.cycjava.cycl.inference.open_cyc_simple_inference_api.simple_boolean_query_external_id(kb_utilities.compact_hl_external_id_string(pred), Mapping.mapcar(COMPACT_HL_EXTERNAL_ID_STRING, args), time, kb_utilities.compact_hl_external_id_string(mt));
    }

    public static SubLObject simple_boolean_query_test(final SubLObject pred, final SubLObject args, SubLObject time, SubLObject mt) {
        if (time == UNPROVIDED) {
            time = ONE_INTEGER;
        }
        if (mt == UNPROVIDED) {
            mt = get_simple_query_default_mt();
        }
        return simple_boolean_query_external_id(kb_utilities.compact_hl_external_id_string(pred), Mapping.mapcar(COMPACT_HL_EXTERNAL_ID_STRING, args), time, kb_utilities.compact_hl_external_id_string(mt));
    }

    public static final SubLObject simple_boolean_query_nl_test_alt(SubLObject expected, SubLObject actual) {
        return cycl_utilities.expression_find(actual, expected, EQUAL, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject simple_boolean_query_nl_test(final SubLObject expected, final SubLObject actual) {
        return cycl_utilities.expression_find(actual, expected, EQUAL, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject declare_open_cyc_simple_inference_api_file() {
        declareFunction("get_simple_query_default_mt", "GET-SIMPLE-QUERY-DEFAULT-MT", 0, 0, false);
        declareFunction("get_simple_query_default_nl_mt", "GET-SIMPLE-QUERY-DEFAULT-NL-MT", 0, 0, false);
        declareFunction("simple_boolean_query_external_id", "SIMPLE-BOOLEAN-QUERY-EXTERNAL-ID", 2, 2, false);
        declareFunction("simple_boolean_query_cycl", "SIMPLE-BOOLEAN-QUERY-CYCL", 2, 2, false);
        declareFunction("simple_boolean_query_nl", "SIMPLE-BOOLEAN-QUERY-NL", 2, 3, false);
        declareFunction("clear_nl_query_denots_of_string", "CLEAR-NL-QUERY-DENOTS-OF-STRING", 0, 0, false);
        declareFunction("remove_nl_query_denots_of_string", "REMOVE-NL-QUERY-DENOTS-OF-STRING", 1, 1, false);
        declareFunction("nl_query_denots_of_string_internal", "NL-QUERY-DENOTS-OF-STRING-INTERNAL", 2, 0, false);
        declareFunction("nl_query_denots_of_string", "NL-QUERY-DENOTS-OF-STRING", 1, 1, false);
        declareFunction("add_quantifiers_if_needed", "ADD-QUANTIFIERS-IF-NEEDED", 2, 0, false);
        declareFunction("term_order_integer", "TERM-ORDER-INTEGER", 1, 0, false);
        declareFunction("clear_first_order_collectionP", "CLEAR-FIRST-ORDER-COLLECTION?", 0, 0, false);
        declareFunction("remove_first_order_collectionP", "REMOVE-FIRST-ORDER-COLLECTION?", 1, 1, false);
        declareFunction("first_order_collectionP_internal", "FIRST-ORDER-COLLECTION?-INTERNAL", 2, 0, false);
        declareFunction("first_order_collectionP", "FIRST-ORDER-COLLECTION?", 1, 1, false);
        declareFunction("clear_variable_order_collectionP", "CLEAR-VARIABLE-ORDER-COLLECTION?", 0, 0, false);
        declareFunction("remove_variable_order_collectionP", "REMOVE-VARIABLE-ORDER-COLLECTION?", 1, 1, false);
        declareFunction("variable_order_collectionP_internal", "VARIABLE-ORDER-COLLECTION?-INTERNAL", 2, 0, false);
        declareFunction("variable_order_collectionP", "VARIABLE-ORDER-COLLECTION?", 1, 1, false);
        declareFunction("pred_arg_expected_order", "PRED-ARG-EXPECTED-ORDER", 2, 0, false);
        declareFunction("get_semtrans_sentences", "GET-SEMTRANS-SENTENCES", 2, 0, false);
        declareFunction("add_quantifiers_to_semtrans_if_needed", "ADD-QUANTIFIERS-TO-SEMTRANS-IF-NEEDED", 3, 1, false);
        declareFunction("arg_expected_order_from_constraints", "ARG-EXPECTED-ORDER-FROM-CONSTRAINTS", 2, 0, false);
        declareFunction("get_simple_inference_semtrans_lexicon", "GET-SIMPLE-INFERENCE-SEMTRANS-LEXICON", 0, 0, false);
        declareFunction("make_simple_inference_semtrans_lexicon", "MAKE-SIMPLE-INFERENCE-SEMTRANS-LEXICON", 0, 0, false);
        declareFunction("simple_boolean_query_test", "SIMPLE-BOOLEAN-QUERY-TEST", 2, 2, false);
        declareFunction("simple_boolean_query_nl_test", "SIMPLE-BOOLEAN-QUERY-NL-TEST", 2, 0, false);
        return NIL;
    }

    public static SubLObject init_open_cyc_simple_inference_api_file() {
        defparameter("*SIMPLE-QUERY-DEFAULT-MT*", $list0);
        defparameter("*SIMPLE-QUERY-DEFAULT-NL-MT*", $$RelationParaphraseMt);
        defparameter("*SIMPLE-BOOLEAN-DEFAULT-TIME*", ONE_INTEGER);
        defparameter("*SIMPLE-BOOLEAN-NL-DEFAULT-TIME*", TWENTY_INTEGER);
        deflexical("*NL-QUERY-DENOTS-OF-STRING-CACHING-STATE*", NIL);
        deflexical("*FIRST-ORDER-COLLECTION?-CACHING-STATE*", NIL);
        deflexical("*VARIABLE-ORDER-COLLECTION?-CACHING-STATE*", NIL);
        defparameter("*GET-SIMPLE-INFERENCE-SEMTRANS-LEXICON*", $UNINITIALIZED);
        return NIL;
    }

    public static final SubLObject setup_open_cyc_simple_inference_api_file_alt() {
        register_external_symbol(SIMPLE_BOOLEAN_QUERY_EXTERNAL_ID);
        register_external_symbol(SIMPLE_BOOLEAN_QUERY_CYCL);
        register_external_symbol(SIMPLE_BOOLEAN_QUERY_NL);
        memoization_state.note_globally_cached_function(NL_QUERY_DENOTS_OF_STRING);
        memoization_state.note_globally_cached_function($sym39$FIRST_ORDER_COLLECTION_);
        memoization_state.note_globally_cached_function($sym42$VARIABLE_ORDER_COLLECTION_);
        define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_TEST, list(new SubLObject[]{ $TEST, EQUALP, $OWNER, $$$daves, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list_alt79);
        define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_NL, list(new SubLObject[]{ $TEST, SIMPLE_BOOLEAN_QUERY_NL_TEST, $OWNER, $$$daves, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list_alt81);
        return NIL;
    }

    public static SubLObject setup_open_cyc_simple_inference_api_file() {
        if (SubLFiles.USE_V1) {
            register_external_symbol(SIMPLE_BOOLEAN_QUERY_EXTERNAL_ID);
            register_external_symbol(SIMPLE_BOOLEAN_QUERY_CYCL);
            register_external_symbol(SIMPLE_BOOLEAN_QUERY_NL);
            memoization_state.note_globally_cached_function(NL_QUERY_DENOTS_OF_STRING);
            memoization_state.note_globally_cached_function($sym41$FIRST_ORDER_COLLECTION_);
            memoization_state.note_globally_cached_function($sym44$VARIABLE_ORDER_COLLECTION_);
            define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_TEST, list(new SubLObject[]{ $TEST, EQUALP, $OWNER, NIL, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list80);
            define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_NL, list(new SubLObject[]{ $TEST, SIMPLE_BOOLEAN_QUERY_NL_TEST, $OWNER, NIL, $CLASSES, NIL, $KB, $FULL, $WORKING_, NIL }), $list82);
        }
        if (SubLFiles.USE_V2) {
            memoization_state.note_globally_cached_function($sym39$FIRST_ORDER_COLLECTION_);
            memoization_state.note_globally_cached_function($sym42$VARIABLE_ORDER_COLLECTION_);
            define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_TEST, list(new SubLObject[]{ $TEST, EQUALP, $OWNER, $$$daves, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list_alt79);
            define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_NL, list(new SubLObject[]{ $TEST, SIMPLE_BOOLEAN_QUERY_NL_TEST, $OWNER, $$$daves, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list_alt81);
        }
        return NIL;
    }

    public static SubLObject setup_open_cyc_simple_inference_api_file_Previous() {
        register_external_symbol(SIMPLE_BOOLEAN_QUERY_EXTERNAL_ID);
        register_external_symbol(SIMPLE_BOOLEAN_QUERY_CYCL);
        register_external_symbol(SIMPLE_BOOLEAN_QUERY_NL);
        memoization_state.note_globally_cached_function(NL_QUERY_DENOTS_OF_STRING);
        memoization_state.note_globally_cached_function($sym41$FIRST_ORDER_COLLECTION_);
        memoization_state.note_globally_cached_function($sym44$VARIABLE_ORDER_COLLECTION_);
        define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_TEST, list(new SubLObject[]{ $TEST, EQUALP, $OWNER, NIL, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list80);
        define_test_case_table_int(SIMPLE_BOOLEAN_QUERY_NL, list(new SubLObject[]{ $TEST, SIMPLE_BOOLEAN_QUERY_NL_TEST, $OWNER, NIL, $CLASSES, NIL, $KB, $FULL, $WORKING_, NIL }), $list82);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_open_cyc_simple_inference_api_file();
    }

    @Override
    public void initializeVariables() {
        init_open_cyc_simple_inference_api_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_open_cyc_simple_inference_api_file();
    }

    static {
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list_alt0 = list(reader_make_constant_shell("MtSpace"), list(reader_make_constant_shell("MtTimeDimFn"), reader_make_constant_shell("Now")), list(reader_make_constant_shell("MtUnionFn"), reader_make_constant_shell("AssistedReaderSourceSpindleCollectorMt"), reader_make_constant_shell("AllGeneralEnglishValidatedLexicalMicrotheoryPSC")));

    static private final SubLString $str_alt6$The_sentence_contains_an_open_var = makeString("The sentence contains an open variable.");

    static private final SubLString $str_alt7$The_sentence_ = makeString("The sentence ");

    static private final SubLString $str_alt8$_is_not_a_valid_CycL_sentence_ = makeString(" is not a valid CycL sentence.");

    static private final SubLString $str_alt10$_is_not_semantically_well_formed = makeString(" is not semantically well-formed");

    static private final SubLList $list_alt13 = list(makeKeyword("BROWSABLE?"), T);

    static private final SubLList $list_alt20 = list(makeSymbol("RESULT"), makeSymbol("ERROR-MSG"));

    public static final SubLObject $const24$CurrentWorldDataCollectorMt_NonHo = reader_make_constant_shell("CurrentWorldDataCollectorMt-NonHomocentric");

    static private final SubLSymbol $sym25$BAD_FOR_TAGGING_ = makeSymbol("BAD-FOR-TAGGING?");

    static private final SubLString $str_alt28$_cyc_projects_doc_annotation_comp = makeString("/cyc/projects/doc-annotation/compounds.txt");

    static private final SubLString $str_alt30$Unable_to_open__S = makeString("Unable to open ~S");

    static private final SubLString $str_alt31$_S_A_S_A_S__ = makeString("~S~A~S~A~S~%");

    public static final SubLSymbol $kw34$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLSymbol $sym39$FIRST_ORDER_COLLECTION_ = makeSymbol("FIRST-ORDER-COLLECTION?");

    static private final SubLSymbol $sym41$_FIRST_ORDER_COLLECTION__CACHING_STATE_ = makeSymbol("*FIRST-ORDER-COLLECTION?-CACHING-STATE*");

    static private final SubLSymbol $sym42$VARIABLE_ORDER_COLLECTION_ = makeSymbol("VARIABLE-ORDER-COLLECTION?");

    static private final SubLSymbol $sym44$_VARIABLE_ORDER_COLLECTION__CACHING_STATE_ = makeSymbol("*VARIABLE-ORDER-COLLECTION?-CACHING-STATE*");

    static private final SubLSymbol $sym53$_EVENT = makeSymbol("?EVENT");

    static private final SubLSymbol $sym57$_SUBJ = makeSymbol("?SUBJ");

    static private final SubLList $list_alt59 = list(reader_make_constant_shell("isa"), makeSymbol("?SUBJ"), makeKeyword("SUBJECT"));

    static private final SubLSymbol $sym60$_FIRST_ORDER_SUBJ = makeSymbol("?FIRST-ORDER-SUBJ");

    static private final SubLList $list_alt61 = list(reader_make_constant_shell("isa"), makeSymbol("?FIRST-ORDER-SUBJ"), makeKeyword("SUBJECT"));

    static private final SubLList $list_alt62 = list(reader_make_constant_shell("isa"), makeSymbol("?SUBJ"), makeSymbol("?FIRST-ORDER-SUBJ"));

    static private final SubLSymbol $sym63$_OBJ = makeSymbol("?OBJ");

    static private final SubLList $list_alt64 = list(reader_make_constant_shell("isa"), makeSymbol("?OBJ"), makeKeyword("OBJECT"));

    static private final SubLString $str_alt68$Unable_to_initialize__A__ = makeString("Unable to initialize ~A~%");

    static private final SubLString $$$daves = makeString("daves");

    static private final SubLList $list_alt79 = list(list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("EnglishMt"), reader_make_constant_shell("Microtheory"))), list($TRUE, NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("EnglishMt"), reader_make_constant_shell("Microtheory")), makeInteger(1000)), list($TRUE, NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("Microtheory"), reader_make_constant_shell("EnglishMt"))), list(makeKeyword("FALSE"), NIL)), list(list(reader_make_constant_shell("likesAsFriend"), list(reader_make_constant_shell("Microtheory"), reader_make_constant_shell("EnglishMt"))), list(makeKeyword("FALSE"), NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("Microtheory"), makeSymbol("?X"))), list(makeKeyword("ERROR"), makeString("The sentence contains an open variable."))), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("RichardNixon"), reader_make_constant_shell("Plumber"))), list(makeKeyword("UNKNOWN"), NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("BarackObama"), reader_make_constant_shell("UnitedStatesPresident"))), list($TRUE, NIL)), list(list(reader_make_constant_shell("isa"), list(reader_make_constant_shell("RichardNixon"), reader_make_constant_shell("UnitedStatesPresident")), ONE_INTEGER, reader_make_constant_shell("BaseKB")), list(makeKeyword("FALSE"), NIL)));

    static private final SubLList $list_alt81 = list(list(list(makeString("is"), list(makeString("Tony Brusseau"), makeString("person"))), $TRUE), list(list(makeString("employs"), list(makeString("Cycorp"), makeString("computer scientist")), makeInteger(1000)), $TRUE), list(list(makeString("is"), list(makeString("Richard Nixon"), makeString("plumber"))), makeKeyword("UNKNOWN")), list(list(makeString("is"), list(makeString("Barack Obama"), makeString("US President"))), $TRUE), list(list(makeString("hates"), list(makeString("Richard Nixon"), makeString("World War II")), ONE_INTEGER, reader_make_constant_shell("BaseKB")), makeKeyword("UNKNOWN")));
}

/**
 * Total time: 310 ms
 */
