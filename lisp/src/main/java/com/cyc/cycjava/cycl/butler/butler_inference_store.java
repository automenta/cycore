/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.butler;


import static com.cyc.cycjava.cycl.constant_handles.constant_p;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.control_vars.kb_loaded;
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
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store.problem_store_creation_time;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store.problem_store_guid;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store.problem_store_id_index;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store.problem_store_problem_id_index;
import static com.cyc.cycjava.cycl.subl_macro_promotions.$catch_error_message_target$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryKey;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryValue;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorNextEntry;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.releaseEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeDouble;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import java.util.Iterator;
import java.util.Map;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.assertion_handles;
import com.cyc.cycjava.cycl.assertions_high;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.date_utilities;
import com.cyc.cycjava.cycl.format_nil;
import com.cyc.cycjava.cycl.iteration;
import com.cyc.cycjava.cycl.kb_utilities;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.mt_relevance_macros;
import com.cyc.cycjava.cycl.nart_handles;
import com.cyc.cycjava.cycl.narts_high;
import com.cyc.cycjava.cycl.number_utilities;
import com.cyc.cycjava.cycl.sdbc;
import com.cyc.cycjava.cycl.subl_promotions;
import com.cyc.cycjava.cycl.system_info;
import com.cyc.cycjava.cycl.variables;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_enumerated_types;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store;
import com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_access_path;
import com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_kb_accessors;
import com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_result_set_iterators;
import com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Environment;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Guids;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLFloat;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      BUTLER-INFERENCE-STORE
 * source file: /cyc/top/cycl/butler/butler-inference-store.lisp
 * created:     2019/07/03 17:39:04
 */
public final class butler_inference_store extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new butler_inference_store();

 public static final String myName = "com.cyc.cycjava.cycl.butler.butler_inference_store";


    // deflexical
    // Definitions
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_ks$ = makeSymbol("*BUTLER-INFERENCE-STORE-KS*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_access_path$ = makeSymbol("*BUTLER-INFERENCE-STORE-ACCESS-PATH*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_constant_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-CONSTANT-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_variable_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-VARIABLE-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_nart_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-NART-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_naut_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-NAUT-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_fully_bound_formula_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-FULLY-BOUND-FORMULA-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_not_fully_bound_formula_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-NOT-FULLY-BOUND-FORMULA-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_string_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-STRING-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_number_type$ = makeSymbol("*BUTLER-INFERENCE-STORE-NUMBER-TYPE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_good_status$ = makeSymbol("*BUTLER-INFERENCE-STORE-GOOD-STATUS*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_neutral_status$ = makeSymbol("*BUTLER-INFERENCE-STORE-NEUTRAL-STATUS*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $butler_inference_store_no_good_status$ = makeSymbol("*BUTLER-INFERENCE-STORE-NO-GOOD-STATUS*");

    @LispMethod(comment = "Internal Constants")
    // Internal Constants
    private static final SubLObject $$ButlerInferenceStore_KS = reader_make_constant_shell("ButlerInferenceStore-KS");

    static private final SubLString $str1$Could_not_find_access_path_for_Bu = makeString("Could not find access path for Butler inference store.");

    private static final SubLSymbol SQL_STATEMENT_P = makeSymbol("SQL-STATEMENT-P");

    private static final SubLFloat $float$0_5 = makeDouble(0.5);

    private static final SubLSymbol PROBABILITY_P = makeSymbol("PROBABILITY-P");

    static private final SubLString $str16$INSERT_into_problem_stores__guid_ = makeString("INSERT into problem_stores (guid, system, kb, machine, creation_time) VALUES ('");

    static private final SubLString $str17$____ = makeString("', '");

    static private final SubLString $str18$___ = makeString("', ");

    static private final SubLString $str19$___ = makeString(", '");

    static private final SubLString $str20$__ = makeString("')");

    static private final SubLString $str21$SELECT_id_FROM_problem_stores_WHE = makeString("SELECT id FROM problem_stores WHERE guid = '");

    static private final SubLString $str22$_ = makeString("'");

    static private final SubLString $str23$INSERT_into_problems__prob_store_ = makeString("INSERT into problems (prob_store, sentence, mt, suid, status) VALUES (");

    static private final SubLString $str24$__ = makeString(", ");

    static private final SubLString $str25$_ = makeString(")");

    static private final SubLString $str27$INSERT_INTO_complex_expressions__ = makeString("INSERT INTO complex_expressions (complex_exp, argnum, simple_exp) VALUES (");

    static private final SubLString $str28$INSERT_INTO_expressions__external = makeString("INSERT INTO expressions (external_id, type) VALUES ('");

    static private final SubLString $str29$SELECT_id_FROM_expressions_WHERE_ = makeString("SELECT id FROM expressions WHERE external_id = '");

    static private final SubLString $str30$SELECT_DISTINCT_system__kb__machi = makeString("SELECT DISTINCT system, kb, machine, creation_time FROM problem_stores where guid = '");

    static private final SubLList $list31 = list(makeSymbol("&OPTIONAL"), makeSymbol("ID"), makeSymbol("SYSTEM"), makeSymbol("KB"), makeSymbol("MACHINE"), makeSymbol("CREATION-TIME"));

    static private final SubLString $str32$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id, p.status FROM problems p, problem_stores ps, expressions e1, expressions e2 WHERE p.suid = ");

    static private final SubLString $str33$_AND_ps_id___p_prob_store_AND_ps_ = makeString(" AND ps.id = p.prob_store AND ps.guid = '");

    static private final SubLString $str34$__AND_e1_id___p_sentence_AND_e2_i = makeString("' AND e1.id = p.sentence AND e2.id = p.mt");

    static private final SubLList $list35 = list(makeSymbol("&OPTIONAL"), makeSymbol("ID"), makeSymbol("SENTENCE"), makeSymbol("MT"), makeSymbol("STATUS"));

    static private final SubLString $str36$SELECT_COUNT_DISTINCT_p_prob_stor = makeString("SELECT COUNT(DISTINCT p.prob_store) FROM problems p, expressions e1, expressions e2 WHERE p.sentence = e1.id AND p.mt = e2.id AND e1.external_id = '");

    static private final SubLString $str37$__AND_e2_external_id____ = makeString("' AND e2.external_id = '");

    static private final SubLString $str38$SELECT_COUNT_DISTINCT_p_prob_stor = makeString("SELECT COUNT(DISTINCT p.prob_store) FROM problems p, expressions e WHERE p.sentence = e.id AND e.external_id = '");

    static private final SubLString $str39$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e1, expressions e2 WHERE e1.id = p.sentence AND e2.id = p.mt AND p.status = ");

    static private final SubLString $str40$_GROUP_BY_e1_external_id__e2_exte = makeString(" GROUP BY e1.external_id, e2.external_id ORDER BY count DESC");

    static private final SubLList $list41 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), IDENTITY);

    static private final SubLString $str42$SELECT_DISTINCT_e_external_id__CO = makeString("SELECT DISTINCT e.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e WHERE e.id = p.sentence AND p.status = ");

    static private final SubLString $str43$_GROUP_BY_e_external_id_ORDER_BY_ = makeString(" GROUP BY e.external_id ORDER BY count DESC");

    static private final SubLList $list44 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), IDENTITY);

    static private final SubLString $str45$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e1, expressions e2 WHERE e1.id = p.sentence AND e2.id = p.mt GROUP BY e1.external_id, e2.external_id ORDER BY count DESC");

    static private final SubLString $str46$SELECT_DISTINCT_e_external_id__CO = makeString("SELECT DISTINCT e.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e WHERE e.id = p.sentence GROUP BY e.external_id ORDER BY count DESC");

    static private final SubLString $$$_LIMIT_ = makeString(" LIMIT ");

    static private final SubLString $str48$SELECT_DISTINCT_e3_external_id__e = makeString("SELECT DISTINCT e3.external_id, e4.external_id FROM problems p1, problems p2, expressions e1, expressions e2, expressions e3, expressions e4 WHERE e1.external_id = '");

    static private final SubLString $str49$__AND_p1_sentence___e1_id_AND_e2_ = makeString("' AND p1.sentence = e1.id AND e2.external_id = '");

    static private final SubLString $str50$__AND_p1_mt___e2_id_AND_p2_prob_s = makeString("' AND p1.mt = e2.id AND p2.prob_store = p1.prob_store AND p2.sentence = e3.id AND p2.mt = e4.id");

    static private final SubLList $list51 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"));

    static private final SubLString $str52$SELECT_DISTINCT_e2_external_id_FR = makeString("SELECT DISTINCT e2.external_id FROM problems p1, problems p2, expressions e1, expressions e2 WHERE e1.external_id = '");

    static private final SubLString $str53$__AND_p1_sentence___e1_id_AND_p2_ = makeString("' AND p1.sentence = e1.id AND p2.prob_store = p1.prob_store AND p2.sentence = e2.id");

    static private final SubLList $list54 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"));

    static private final SubLString $str55$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id FROM problems p, expressions e1, expressions e2, expressions e3, complex_expressions c WHERE p.sentence = e1.id AND p.mt = e2.id AND c.complex_exp = e1.id AND c.argnum = ");

    static private final SubLString $str56$_AND_c_simple_exp___e3_id_AND_e3_ = makeString(" AND c.simple_exp = e3.id AND e3.external_id = '");

    static private final SubLString $str57$SELECT_DISTINCT_e1_external_id_FR = makeString("SELECT DISTINCT e1.external_id FROM problems p, expressions e1, expressions e2, complex_expressions c WHERE p.sentence = e1.id AND c.complex_exp = e1.id AND c.argnum = ");

    static private final SubLString $str58$_AND_c_simple_exp___e2_id_AND_e2_ = makeString(" AND c.simple_exp = e2.id AND e2.external_id = '");

    static private final SubLString $str59$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id FROM problems p, expressions e1, expressions e2 WHERE p.status = ");

    static private final SubLString $str60$_AND_p_sentence___e1_id_AND_p_mt_ = makeString(" AND p.sentence = e1.id AND p.mt = e2.id");

    static private final SubLString $str61$SELECT_DISTINCT_e_external_id_FRO = makeString("SELECT DISTINCT e.external_id FROM problems p, expressions e WHERE p.status = ");

    static private final SubLString $str62$_AND_p_sentence___e_id = makeString(" AND p.sentence = e.id");

    static private final SubLList $list63 = list(list(makeSymbol("PROBLEM-STORE-COUNT-VAR"), makeSymbol("SENTENCE-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("MT-VAR"), makeSymbol("&KEY"), makeSymbol("N"), makeSymbol("STATUS"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list64 = list(makeKeyword("N"), makeKeyword("STATUS"), $DONE);

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    static private final SubLSymbol $sym69$ITERATOR = makeUninternedSymbol("ITERATOR");

    static private final SubLSymbol $sym70$ROW = makeUninternedSymbol("ROW");

    private static final SubLSymbol BUTLER_INFERENCE_STORE_MOST_INTERESTING_PROBLEM_QUERIES = makeSymbol("BUTLER-INFERENCE-STORE-MOST-INTERESTING-PROBLEM-QUERIES");

    private static final SubLSymbol DO_ITERATOR_WITHOUT_VALUES = makeSymbol("DO-ITERATOR-WITHOUT-VALUES");

    static private final SubLList $list75 = list(list(makeSymbol("STATUS"), makeSymbol("SENTENCE-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("MT-VAR"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list76 = list($DONE);

    static private final SubLSymbol $sym77$ITERATOR = makeUninternedSymbol("ITERATOR");

    static private final SubLSymbol $sym78$ROW = makeUninternedSymbol("ROW");

    private static final SubLSymbol BUTLER_INFERENCE_STORE_PROBLEM_QUERIES_WITH_STATUS = makeSymbol("BUTLER-INFERENCE-STORE-PROBLEM-QUERIES-WITH-STATUS");

    static private final SubLList $list80 = list(T);

    static private final SubLList $list81 = list(NIL);

    static private final SubLString $str82$Currently_cannot_handle_expressio = makeString("Currently cannot handle expression ~s");

    static private final SubLString $str83$YYYY_MM_DD_HH_MM_SS = makeString("YYYY-MM-DD HH:MM:SS");

    private static final SubLSymbol BUTLER_INFERENCE_STORE_ITERATOR_DONE = makeSymbol("BUTLER-INFERENCE-STORE-ITERATOR-DONE");

    private static final SubLSymbol BUTLER_INFERENCE_STORE_ITERATOR_NEXT = makeSymbol("BUTLER-INFERENCE-STORE-ITERATOR-NEXT");

    private static final SubLSymbol BUTLER_INFERENCE_STORE_ITERATOR_FINALIZE = makeSymbol("BUTLER-INFERENCE-STORE-ITERATOR-FINALIZE");

    static private final SubLList $list87 = list(makeSymbol("SUB-ITERATOR"), makeSymbol("DECODING-FUNCTIONS"));

    private static final SubLSymbol BUTLER_INFERENCE_STORE_ID_STATUS = makeSymbol("BUTLER-INFERENCE-STORE-ID-STATUS");

    private static final SubLSymbol SQL_TIME_STAMP_TO_UNIVERSAL_TIME = makeSymbol("SQL-TIME-STAMP-TO-UNIVERSAL-TIME");

    static private final SubLString $str95$Unknown_status__s = makeString("Unknown status ~s");

    public static final SubLObject butler_inference_store_access_path_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject access_path = $butler_inference_store_access_path$.getGlobalValue();
                if (NIL == sksi_access_path.access_path_p(access_path)) {
                    {
                        SubLObject mt_var = sksi_kb_accessors.sk_source_mapping_mt($butler_inference_store_ks$.getGlobalValue());
                        {
                            SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                            SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                            SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                            try {
                                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                                access_path = sksi_access_path.sksi_determine_access_path($butler_inference_store_ks$.getGlobalValue(), UNPROVIDED, UNPROVIDED);
                                $butler_inference_store_access_path$.setGlobalValue(access_path);
                            } finally {
                                mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                                mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                }
                if (NIL == sksi_access_path.access_path_p(access_path)) {
                    Errors.warn($str_alt1$Could_not_find_access_path_for_Bu);
                }
                return access_path;
            }
        }
    }

    public static SubLObject butler_inference_store_access_path() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject access_path = $butler_inference_store_access_path$.getGlobalValue();
        if (NIL == sksi_access_path.access_path_p(access_path)) {
            final SubLObject mt_var = sksi_kb_accessors.sk_source_mapping_mt($butler_inference_store_ks$.getGlobalValue());
            final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
            final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
            final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
            try {
                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                access_path = sksi_access_path.sksi_determine_access_path($butler_inference_store_ks$.getGlobalValue(), UNPROVIDED, UNPROVIDED);
                $butler_inference_store_access_path$.setGlobalValue(access_path);
            } finally {
                mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
                mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
            }
        }
        if (NIL == sksi_access_path.access_path_p(access_path)) {
            Errors.warn($str1$Could_not_find_access_path_for_Bu);
        }
        return access_path;
    }

    public static final SubLObject reset_bulter_inference_store_access_path_alt() {
        $butler_inference_store_access_path$.setGlobalValue(NIL);
        return NIL;
    }

    public static SubLObject reset_bulter_inference_store_access_path() {
        $butler_inference_store_access_path$.setGlobalValue(NIL);
        return NIL;
    }

    public static final SubLObject butler_inference_store_execute_sql_query_alt(SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        SubLTrampolineFile.checkType(sql, STRINGP);
        if (NIL != statement) {
            SubLTrampolineFile.checkType(statement, SQL_STATEMENT_P);
        }
        if (NIL != statement) {
            return sdbc.sqls_execute_query(statement, sql, UNPROVIDED);
        } else {
            {
                SubLObject access_path = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_access_path();
                SubLObject result = NIL;
                if (NIL != sksi_access_path.access_path_p(access_path)) {
                    result = sksi_sks_interaction.sksi_execute_sql_query(sql, access_path);
                }
                return result;
            }
        }
    }

    public static SubLObject butler_inference_store_execute_sql_query(final SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        assert NIL != stringp(sql) : "! stringp(sql) " + ("Types.stringp(sql) " + "CommonSymbols.NIL != Types.stringp(sql) ") + sql;
        if (((NIL != statement) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == sdbc.sql_statement_p(statement))) {
            throw new AssertionError(statement);
        }
        if (NIL != statement) {
            return sdbc.sqls_execute_query(statement, sql, UNPROVIDED);
        }
        final SubLObject access_path = butler_inference_store_access_path();
        SubLObject result = NIL;
        if (NIL != sksi_access_path.access_path_p(access_path)) {
            result = sksi_sks_interaction.sksi_execute_sql_query(sql, access_path);
        }
        return result;
    }

    public static final SubLObject butler_inference_store_execute_sql_update_alt(SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        SubLTrampolineFile.checkType(sql, STRINGP);
        if (NIL != statement) {
            SubLTrampolineFile.checkType(statement, SQL_STATEMENT_P);
        }
        if (NIL != statement) {
            return sdbc.sqls_execute_update(statement, sql);
        } else {
            {
                SubLObject access_path = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_access_path();
                SubLObject result = NIL;
                if (NIL != sksi_access_path.access_path_p(access_path)) {
                    result = sksi_sks_interaction.sksi_execute_sql_update(sql, access_path);
                }
                return result;
            }
        }
    }

    public static SubLObject butler_inference_store_execute_sql_update(final SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        assert NIL != stringp(sql) : "! stringp(sql) " + ("Types.stringp(sql) " + "CommonSymbols.NIL != Types.stringp(sql) ") + sql;
        if (((NIL != statement) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == sdbc.sql_statement_p(statement))) {
            throw new AssertionError(statement);
        }
        if (NIL != statement) {
            return sdbc.sqls_execute_update(statement, sql);
        }
        final SubLObject access_path = butler_inference_store_access_path();
        SubLObject result = NIL;
        if (NIL != sksi_access_path.access_path_p(access_path)) {
            result = sksi_sks_interaction.sksi_execute_sql_update(sql, access_path);
        }
        return result;
    }

    /**
     * Writes a problem store to the Butler inference store.
     * Returns 1 if successful, 0 otherwise.
     */
    @LispMethod(comment = "Writes a problem store to the Butler inference store.\r\nReturns 1 if successful, 0 otherwise.\nWrites a problem store to the Butler inference store.\nReturns 1 if successful, 0 otherwise.")
    public static final SubLObject butler_write_problem_store_to_inference_store_alt(SubLObject problem_store) {
        SubLTrampolineFile.checkType(problem_store, PROBLEM_STORE_P);
        {
            SubLObject access_path = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_access_path();
            SubLObject count = ZERO_INTEGER;
            SubLObject error = NIL;
            if (NIL != sksi_access_path.access_path_p(access_path)) {
                {
                    SubLObject db = sksi_access_path.access_path_db(access_path);
                    SubLObject user = sksi_access_path.access_path_user(access_path);
                    SubLObject password = sksi_access_path.access_path_password(access_path);
                    SubLObject server = sksi_access_path.access_path_server(access_path);
                    SubLObject port = sksi_access_path.access_path_port(access_path);
                    SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                    SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                    SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                    SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
                    SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
                    SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
                    SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getGlobalValue();
                    SubLObject statement = NIL;
                    SubLObject connection = NIL;
                    try {
                        connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                        if (NIL != sdbc.sql_open_connection_p(connection)) {
                            statement = sdbc.sqlc_create_statement(connection);
                        } else {
                            statement = connection;
                        }
                        if (NIL != sdbc.sql_open_statement_p(statement)) {
                            {
                                SubLObject connection_1 = sdbc.sqls_get_connection(statement);
                                SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_1);
                                SubLObject commit_error = NIL;
                                SubLObject rollback_result = NIL;
                                SubLObject auto_commit_result = NIL;
                                SubLObject errors = NIL;
                                if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                    errors = cons(auto_commit, errors);
                                } else {
                                    sdbc.sqlc_set_error_handling(connection_1, $THROW);
                                    try {
                                        {
                                            SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                                            try {
                                                bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                                                try {
                                                    if (NIL != auto_commit) {
                                                        sdbc.sqlc_set_auto_commit(connection_1, NIL);
                                                    }
                                                    if (NIL != com.cyc.cycjava.cycl.butler.butler_inference_store.butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                                        count = ONE_INTEGER;
                                                    }
                                                    sdbc.sqlc_commit(connection_1);
                                                } catch (Throwable catch_var) {
                                                    Errors.handleThrowable(catch_var, NIL);
                                                }
                                            } finally {
                                                rebind(Errors.$error_handler$, _prev_bind_0);
                                            }
                                        }
                                    } catch (Throwable ccatch_env_var) {
                                        commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                    }
                                    sdbc.sqlc_set_error_handling(connection_1, $RETURN);
                                    if (NIL != commit_error) {
                                        errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                        rollback_result = sdbc.sqls_handle_rollback(connection_1);
                                        if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                            errors = cons(rollback_result, errors);
                                        }
                                    }
                                    if (NIL != auto_commit) {
                                        auto_commit_result = sdbc.sqlc_set_auto_commit(connection_1, T);
                                    }
                                    if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                        errors = cons(auto_commit_result, errors);
                                    }
                                }
                                if (NIL != errors) {
                                    error = sdbc.sqls_handle_transaction_errors(errors);
                                }
                            }
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                            try {
                                bind($is_thread_performing_cleanupP$, T);
                                if (NIL != sdbc.sql_connection_p(connection)) {
                                    sdbc.sqlc_close(connection);
                                }
                            } finally {
                                rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                            }
                        }
                    }
                }
            }
            if (NIL != error) {
                count = ZERO_INTEGER;
                sdbc.sdbc_error_warn(error);
            }
            return count;
        }
    }

    /**
     * Writes a problem store to the Butler inference store.
     * Returns 1 if successful, 0 otherwise.
     */
    @LispMethod(comment = "Writes a problem store to the Butler inference store.\r\nReturns 1 if successful, 0 otherwise.\nWrites a problem store to the Butler inference store.\nReturns 1 if successful, 0 otherwise.")
    public static SubLObject butler_write_problem_store_to_inference_store(final SubLObject problem_store) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != inference_datastructures_problem_store.problem_store_p(problem_store) : "! inference_datastructures_problem_store.problem_store_p(problem_store) " + ("inference_datastructures_problem_store.problem_store_p(problem_store) " + "CommonSymbols.NIL != inference_datastructures_problem_store.problem_store_p(problem_store) ") + problem_store;
        final SubLObject access_path = butler_inference_store_access_path();
        SubLObject count = ZERO_INTEGER;
        SubLObject error = NIL;
        if (NIL != sksi_access_path.access_path_p(access_path)) {
            final SubLObject db = sksi_access_path.access_path_db(access_path);
            final SubLObject user = sksi_access_path.access_path_user(access_path);
            final SubLObject password = sksi_access_path.access_path_password(access_path);
            final SubLObject server = sksi_access_path.access_path_server(access_path);
            final SubLObject port = sksi_access_path.access_path_port(access_path);
            final SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
            final SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
            final SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
            final SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
            final SubLObject conn_server = (NIL != proxy_server) ? proxy_server : server;
            final SubLObject conn_port = (NIL != proxy_port) ? proxy_port : port;
            final SubLObject conn_timeout = (NIL != timeout) ? timeout : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
            SubLObject statement = NIL;
            SubLObject connection = NIL;
            try {
                connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                if (NIL != sdbc.sql_open_connection_p(connection)) {
                    statement = sdbc.sqlc_create_statement(connection);
                } else {
                    statement = connection;
                }
                if (NIL != sdbc.sql_open_statement_p(statement)) {
                    final SubLObject connection_$1 = sdbc.sqls_get_connection(statement);
                    final SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_$1);
                    SubLObject commit_error = NIL;
                    SubLObject rollback_result = NIL;
                    SubLObject auto_commit_result = NIL;
                    SubLObject errors = NIL;
                    if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                        errors = cons(auto_commit, errors);
                    } else {
                        sdbc.sqlc_set_error_handling(connection_$1, $THROW);
                        try {
                            thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                            try {
                                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                try {
                                    if (NIL != auto_commit) {
                                        sdbc.sqlc_set_auto_commit(connection_$1, NIL);
                                    }
                                    if (NIL != butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                        count = ONE_INTEGER;
                                    }
                                    sdbc.sqlc_commit(connection_$1);
                                } catch (final Throwable catch_var) {
                                    Errors.handleThrowable(catch_var, NIL);
                                }
                            } finally {
                                Errors.$error_handler$.rebind(_prev_bind_0, thread);
                            }
                        } catch (final Throwable ccatch_env_var) {
                            commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                        } finally {
                            thread.throwStack.pop();
                        }
                        sdbc.sqlc_set_error_handling(connection_$1, $RETURN);
                        if (NIL != commit_error) {
                            errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                            rollback_result = sdbc.sqls_handle_rollback(connection_$1);
                            if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                errors = cons(rollback_result, errors);
                            }
                        }
                        if (NIL != auto_commit) {
                            auto_commit_result = sdbc.sqlc_set_auto_commit(connection_$1, T);
                        }
                        if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                            errors = cons(auto_commit_result, errors);
                        }
                    }
                    if (NIL != errors) {
                        error = sdbc.sqls_handle_transaction_errors(errors);
                    }
                }
            } finally {
                final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    if (NIL != sdbc.sql_connection_p(connection)) {
                        sdbc.sqlc_close(connection);
                    }
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
                }
            }
        }
        if (NIL != error) {
            count = ZERO_INTEGER;
            sdbc.sdbc_error_warn(error);
        }
        return count;
    }

    /**
     * Writes all problem stores to the Butler inference store.  If MULTIPLE-TRANSACTIONS? is
     * non-NIL, then each problem store is written out in its own transaction.  Otherwise, all
     * problem stores will be written out in a single transaction (so that either all or none
     * are successfully written out).  Returns the number of stores successfully written out.
     */
    @LispMethod(comment = "Writes all problem stores to the Butler inference store.  If MULTIPLE-TRANSACTIONS? is\r\nnon-NIL, then each problem store is written out in its own transaction.  Otherwise, all\r\nproblem stores will be written out in a single transaction (so that either all or none\r\nare successfully written out).  Returns the number of stores successfully written out.\nWrites all problem stores to the Butler inference store.  If MULTIPLE-TRANSACTIONS? is\nnon-NIL, then each problem store is written out in its own transaction.  Otherwise, all\nproblem stores will be written out in a single transaction (so that either all or none\nare successfully written out).  Returns the number of stores successfully written out.")
    public static final SubLObject butler_write_all_problem_stores_to_inference_store_alt(SubLObject multiple_transactionsP) {
        if (multiple_transactionsP == UNPROVIDED) {
            multiple_transactionsP = NIL;
        }
        {
            SubLObject access_path = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_access_path();
            SubLObject count = ZERO_INTEGER;
            if (NIL != sksi_access_path.access_path_p(access_path)) {
                if (NIL != multiple_transactionsP) {
                    {
                        SubLObject idx = problem_store_id_index();
                        if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                            {
                                SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                                SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                                SubLObject problem_store = NIL;
                                while (NIL != id) {
                                    problem_store = do_id_index_state_object(idx, $SKIP, id, state_var);
                                    if (NIL != do_id_index_id_and_object_validP(id, problem_store, $SKIP)) {
                                        {
                                            SubLObject error = NIL;
                                            SubLObject db = sksi_access_path.access_path_db(access_path);
                                            SubLObject user = sksi_access_path.access_path_user(access_path);
                                            SubLObject password = sksi_access_path.access_path_password(access_path);
                                            SubLObject server = sksi_access_path.access_path_server(access_path);
                                            SubLObject port = sksi_access_path.access_path_port(access_path);
                                            SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                                            SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                                            SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                                            SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
                                            SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
                                            SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
                                            SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getGlobalValue();
                                            SubLObject statement = NIL;
                                            SubLObject connection = NIL;
                                            try {
                                                connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                                                if (NIL != sdbc.sql_open_connection_p(connection)) {
                                                    statement = sdbc.sqlc_create_statement(connection);
                                                } else {
                                                    statement = connection;
                                                }
                                                if (NIL != sdbc.sql_open_statement_p(statement)) {
                                                    {
                                                        SubLObject connection_2 = sdbc.sqls_get_connection(statement);
                                                        SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_2);
                                                        SubLObject commit_error = NIL;
                                                        SubLObject rollback_result = NIL;
                                                        SubLObject auto_commit_result = NIL;
                                                        SubLObject errors = NIL;
                                                        if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                                            errors = cons(auto_commit, errors);
                                                        } else {
                                                            sdbc.sqlc_set_error_handling(connection_2, $THROW);
                                                            try {
                                                                {
                                                                    SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                                                                    try {
                                                                        bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                                                                        try {
                                                                            if (NIL != auto_commit) {
                                                                                sdbc.sqlc_set_auto_commit(connection_2, NIL);
                                                                            }
                                                                            if (NIL != com.cyc.cycjava.cycl.butler.butler_inference_store.butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                                                                count = add(count, ONE_INTEGER);
                                                                            }
                                                                            sdbc.sqlc_commit(connection_2);
                                                                        } catch (Throwable catch_var) {
                                                                            Errors.handleThrowable(catch_var, NIL);
                                                                        }
                                                                    } finally {
                                                                        rebind(Errors.$error_handler$, _prev_bind_0);
                                                                    }
                                                                }
                                                            } catch (Throwable ccatch_env_var) {
                                                                commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                                            }
                                                            sdbc.sqlc_set_error_handling(connection_2, $RETURN);
                                                            if (NIL != commit_error) {
                                                                errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                                                rollback_result = sdbc.sqls_handle_rollback(connection_2);
                                                                if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                                                    errors = cons(rollback_result, errors);
                                                                }
                                                            }
                                                            if (NIL != auto_commit) {
                                                                auto_commit_result = sdbc.sqlc_set_auto_commit(connection_2, T);
                                                            }
                                                            if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                                                errors = cons(auto_commit_result, errors);
                                                            }
                                                        }
                                                        if (NIL != errors) {
                                                            error = sdbc.sqls_handle_transaction_errors(errors);
                                                        }
                                                    }
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != sdbc.sql_connection_p(connection)) {
                                                            sdbc.sqlc_close(connection);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                                                    }
                                                }
                                            }
                                            if (NIL != error) {
                                                sdbc.sdbc_error_warn(error);
                                            }
                                        }
                                    }
                                    id = do_id_index_next_id(idx, NIL, id, state_var);
                                    state_var = do_id_index_next_state(idx, NIL, id, state_var);
                                } 
                            }
                        }
                    }
                } else {
                    {
                        SubLObject error = NIL;
                        SubLObject db = sksi_access_path.access_path_db(access_path);
                        SubLObject user = sksi_access_path.access_path_user(access_path);
                        SubLObject password = sksi_access_path.access_path_password(access_path);
                        SubLObject server = sksi_access_path.access_path_server(access_path);
                        SubLObject port = sksi_access_path.access_path_port(access_path);
                        SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                        SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                        SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                        SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
                        SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
                        SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
                        SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getGlobalValue();
                        SubLObject statement = NIL;
                        SubLObject connection = NIL;
                        try {
                            connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                            if (NIL != sdbc.sql_open_connection_p(connection)) {
                                statement = sdbc.sqlc_create_statement(connection);
                            } else {
                                statement = connection;
                            }
                            if (NIL != sdbc.sql_open_statement_p(statement)) {
                                {
                                    SubLObject connection_3 = sdbc.sqls_get_connection(statement);
                                    SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_3);
                                    SubLObject commit_error = NIL;
                                    SubLObject rollback_result = NIL;
                                    SubLObject auto_commit_result = NIL;
                                    SubLObject errors = NIL;
                                    if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                        errors = cons(auto_commit, errors);
                                    } else {
                                        sdbc.sqlc_set_error_handling(connection_3, $THROW);
                                        try {
                                            {
                                                SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                                                try {
                                                    bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                                                    try {
                                                        if (NIL != auto_commit) {
                                                            sdbc.sqlc_set_auto_commit(connection_3, NIL);
                                                        }
                                                        {
                                                            SubLObject idx = problem_store_id_index();
                                                            if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                                                                {
                                                                    SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                                                                    SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                                                                    SubLObject problem_store = NIL;
                                                                    while (NIL != id) {
                                                                        problem_store = do_id_index_state_object(idx, $SKIP, id, state_var);
                                                                        if (NIL != do_id_index_id_and_object_validP(id, problem_store, $SKIP)) {
                                                                            if (NIL != com.cyc.cycjava.cycl.butler.butler_inference_store.butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                                                                count = add(count, ONE_INTEGER);
                                                                            }
                                                                        }
                                                                        id = do_id_index_next_id(idx, NIL, id, state_var);
                                                                        state_var = do_id_index_next_state(idx, NIL, id, state_var);
                                                                    } 
                                                                }
                                                            }
                                                        }
                                                        sdbc.sqlc_commit(connection_3);
                                                    } catch (Throwable catch_var) {
                                                        Errors.handleThrowable(catch_var, NIL);
                                                    }
                                                } finally {
                                                    rebind(Errors.$error_handler$, _prev_bind_0);
                                                }
                                            }
                                        } catch (Throwable ccatch_env_var) {
                                            commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                        }
                                        sdbc.sqlc_set_error_handling(connection_3, $RETURN);
                                        if (NIL != commit_error) {
                                            errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                            rollback_result = sdbc.sqls_handle_rollback(connection_3);
                                            if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                                errors = cons(rollback_result, errors);
                                            }
                                        }
                                        if (NIL != auto_commit) {
                                            auto_commit_result = sdbc.sqlc_set_auto_commit(connection_3, T);
                                        }
                                        if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                            errors = cons(auto_commit_result, errors);
                                        }
                                    }
                                    if (NIL != errors) {
                                        error = sdbc.sqls_handle_transaction_errors(errors);
                                    }
                                }
                            }
                        } finally {
                            {
                                SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    if (NIL != sdbc.sql_connection_p(connection)) {
                                        sdbc.sqlc_close(connection);
                                    }
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                                }
                            }
                        }
                        if (NIL != error) {
                            count = ZERO_INTEGER;
                            sdbc.sdbc_error_warn(error);
                        }
                    }
                }
            }
            return count;
        }
    }

    /**
     * Writes all problem stores to the Butler inference store.  If MULTIPLE-TRANSACTIONS? is
     * non-NIL, then each problem store is written out in its own transaction.  Otherwise, all
     * problem stores will be written out in a single transaction (so that either all or none
     * are successfully written out).  Returns the number of stores successfully written out.
     */
    @LispMethod(comment = "Writes all problem stores to the Butler inference store.  If MULTIPLE-TRANSACTIONS? is\r\nnon-NIL, then each problem store is written out in its own transaction.  Otherwise, all\r\nproblem stores will be written out in a single transaction (so that either all or none\r\nare successfully written out).  Returns the number of stores successfully written out.\nWrites all problem stores to the Butler inference store.  If MULTIPLE-TRANSACTIONS? is\nnon-NIL, then each problem store is written out in its own transaction.  Otherwise, all\nproblem stores will be written out in a single transaction (so that either all or none\nare successfully written out).  Returns the number of stores successfully written out.")
    public static SubLObject butler_write_all_problem_stores_to_inference_store(SubLObject multiple_transactionsP) {
        if (multiple_transactionsP == UNPROVIDED) {
            multiple_transactionsP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject access_path = butler_inference_store_access_path();
        SubLObject count = ZERO_INTEGER;
        if (NIL != sksi_access_path.access_path_p(access_path)) {
            if (NIL != multiple_transactionsP) {
                final SubLObject idx = inference_datastructures_problem_store.problem_store_id_index();
                if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
                    final SubLObject idx_$2 = idx;
                    if (NIL == id_index_dense_objects_empty_p(idx_$2, $SKIP)) {
                        final SubLObject vector_var = id_index_dense_objects(idx_$2);
                        final SubLObject backwardP_var = NIL;
                        SubLObject length;
                        SubLObject v_iteration;
                        SubLObject id;
                        SubLObject problem_store;
                        SubLObject error;
                        SubLObject db;
                        SubLObject user;
                        SubLObject password;
                        SubLObject server;
                        SubLObject port;
                        SubLObject proxy_server;
                        SubLObject proxy_port;
                        SubLObject subprotocol;
                        SubLObject timeout;
                        SubLObject conn_server;
                        SubLObject conn_port;
                        SubLObject conn_timeout;
                        SubLObject statement;
                        SubLObject connection;
                        SubLObject connection_$3;
                        SubLObject auto_commit;
                        SubLObject commit_error;
                        SubLObject rollback_result;
                        SubLObject auto_commit_result;
                        SubLObject errors;
                        SubLObject _prev_bind_0;
                        SubLObject _prev_bind_2;
                        SubLObject _values;
                        for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                            id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                            problem_store = aref(vector_var, id);
                            if ((NIL == id_index_tombstone_p(problem_store)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                                if (NIL != id_index_tombstone_p(problem_store)) {
                                    problem_store = $SKIP;
                                }
                                error = NIL;
                                db = sksi_access_path.access_path_db(access_path);
                                user = sksi_access_path.access_path_user(access_path);
                                password = sksi_access_path.access_path_password(access_path);
                                server = sksi_access_path.access_path_server(access_path);
                                port = sksi_access_path.access_path_port(access_path);
                                proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                                proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                                subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                                timeout = sksi_access_path.access_path_timeout(access_path);
                                conn_server = (NIL != proxy_server) ? proxy_server : server;
                                conn_port = (NIL != proxy_port) ? proxy_port : port;
                                conn_timeout = (NIL != timeout) ? timeout : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
                                statement = NIL;
                                connection = NIL;
                                try {
                                    connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                                    if (NIL != sdbc.sql_open_connection_p(connection)) {
                                        statement = sdbc.sqlc_create_statement(connection);
                                    } else {
                                        statement = connection;
                                    }
                                    if (NIL != sdbc.sql_open_statement_p(statement)) {
                                        connection_$3 = sdbc.sqls_get_connection(statement);
                                        auto_commit = sdbc.sqlc_get_auto_commit(connection_$3);
                                        commit_error = NIL;
                                        rollback_result = NIL;
                                        auto_commit_result = NIL;
                                        errors = NIL;
                                        if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                            errors = cons(auto_commit, errors);
                                        } else {
                                            sdbc.sqlc_set_error_handling(connection_$3, $THROW);
                                            try {
                                                thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                                                _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                                                try {
                                                    Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                    try {
                                                        if (NIL != auto_commit) {
                                                            sdbc.sqlc_set_auto_commit(connection_$3, NIL);
                                                        }
                                                        if (NIL != butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                                            count = add(count, ONE_INTEGER);
                                                        }
                                                        sdbc.sqlc_commit(connection_$3);
                                                    } catch (final Throwable catch_var) {
                                                        Errors.handleThrowable(catch_var, NIL);
                                                    }
                                                } finally {
                                                    Errors.$error_handler$.rebind(_prev_bind_0, thread);
                                                }
                                            } catch (final Throwable ccatch_env_var) {
                                                commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                            } finally {
                                                thread.throwStack.pop();
                                            }
                                            sdbc.sqlc_set_error_handling(connection_$3, $RETURN);
                                            if (NIL != commit_error) {
                                                errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                                rollback_result = sdbc.sqls_handle_rollback(connection_$3);
                                                if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                                    errors = cons(rollback_result, errors);
                                                }
                                            }
                                            if (NIL != auto_commit) {
                                                auto_commit_result = sdbc.sqlc_set_auto_commit(connection_$3, T);
                                            }
                                            if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                                errors = cons(auto_commit_result, errors);
                                            }
                                        }
                                        if (NIL != errors) {
                                            error = sdbc.sqls_handle_transaction_errors(errors);
                                        }
                                    }
                                } finally {
                                    _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        _values = getValuesAsVector();
                                        if (NIL != sdbc.sql_connection_p(connection)) {
                                            sdbc.sqlc_close(connection);
                                        }
                                        restoreValuesFromVector(_values);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
                                    }
                                }
                                if (NIL != error) {
                                    sdbc.sdbc_error_warn(error);
                                }
                            }
                        }
                    }
                    final SubLObject idx_$3 = idx;
                    if (NIL == id_index_sparse_objects_empty_p(idx_$3)) {
                        final SubLObject cdohash_table = id_index_sparse_objects(idx_$3);
                        SubLObject id2 = NIL;
                        SubLObject problem_store2 = NIL;
                        final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                        try {
                            while (iteratorHasNext(cdohash_iterator)) {
                                final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                                id2 = getEntryKey(cdohash_entry);
                                problem_store2 = getEntryValue(cdohash_entry);
                                SubLObject error2 = NIL;
                                final SubLObject db2 = sksi_access_path.access_path_db(access_path);
                                final SubLObject user2 = sksi_access_path.access_path_user(access_path);
                                final SubLObject password2 = sksi_access_path.access_path_password(access_path);
                                final SubLObject server2 = sksi_access_path.access_path_server(access_path);
                                final SubLObject port2 = sksi_access_path.access_path_port(access_path);
                                final SubLObject proxy_server2 = sksi_access_path.access_path_proxy_server(access_path);
                                final SubLObject proxy_port2 = sksi_access_path.access_path_proxy_port(access_path);
                                final SubLObject subprotocol2 = sksi_access_path.access_path_subprotocol(access_path);
                                final SubLObject timeout2 = sksi_access_path.access_path_timeout(access_path);
                                final SubLObject conn_server2 = (NIL != proxy_server2) ? proxy_server2 : server2;
                                final SubLObject conn_port2 = (NIL != proxy_port2) ? proxy_port2 : port2;
                                final SubLObject conn_timeout2 = (NIL != timeout2) ? timeout2 : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
                                SubLObject statement2 = NIL;
                                SubLObject connection2 = NIL;
                                try {
                                    connection2 = sdbc.new_sql_connection(db2, user2, password2, list(new SubLObject[]{ $DBMS_SERVER, server2, $PORT, conn_port2, $SUBPROTOCOL, subprotocol2, $PROXY_SERVER, conn_server2, $TIMEOUT, conn_timeout2 }));
                                    if (NIL != sdbc.sql_open_connection_p(connection2)) {
                                        statement2 = sdbc.sqlc_create_statement(connection2);
                                    } else {
                                        statement2 = connection2;
                                    }
                                    if (NIL != sdbc.sql_open_statement_p(statement2)) {
                                        final SubLObject connection_$4 = sdbc.sqls_get_connection(statement2);
                                        final SubLObject auto_commit2 = sdbc.sqlc_get_auto_commit(connection_$4);
                                        SubLObject commit_error2 = NIL;
                                        SubLObject rollback_result2 = NIL;
                                        SubLObject auto_commit_result2 = NIL;
                                        SubLObject errors2 = NIL;
                                        if (NIL != sdbc.sdbc_error_p(auto_commit2)) {
                                            errors2 = cons(auto_commit2, errors2);
                                        } else {
                                            sdbc.sqlc_set_error_handling(connection_$4, $THROW);
                                            try {
                                                thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                                                final SubLObject _prev_bind_3 = Errors.$error_handler$.currentBinding(thread);
                                                try {
                                                    Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                    try {
                                                        if (NIL != auto_commit2) {
                                                            sdbc.sqlc_set_auto_commit(connection_$4, NIL);
                                                        }
                                                        if (NIL != butler_write_problem_store_to_inference_store_int(problem_store2, statement2)) {
                                                            count = add(count, ONE_INTEGER);
                                                        }
                                                        sdbc.sqlc_commit(connection_$4);
                                                    } catch (final Throwable catch_var2) {
                                                        Errors.handleThrowable(catch_var2, NIL);
                                                    }
                                                } finally {
                                                    Errors.$error_handler$.rebind(_prev_bind_3, thread);
                                                }
                                            } catch (final Throwable ccatch_env_var2) {
                                                commit_error2 = Errors.handleThrowable(ccatch_env_var2, $catch_error_message_target$.getGlobalValue());
                                            } finally {
                                                thread.throwStack.pop();
                                            }
                                            sdbc.sqlc_set_error_handling(connection_$4, $RETURN);
                                            if (NIL != commit_error2) {
                                                errors2 = cons(sdbc.sqls_handle_commit_error(commit_error2), errors2);
                                                rollback_result2 = sdbc.sqls_handle_rollback(connection_$4);
                                                if (NIL != sdbc.sdbc_error_p(rollback_result2)) {
                                                    errors2 = cons(rollback_result2, errors2);
                                                }
                                            }
                                            if (NIL != auto_commit2) {
                                                auto_commit_result2 = sdbc.sqlc_set_auto_commit(connection_$4, T);
                                            }
                                            if (NIL != sdbc.sdbc_error_p(auto_commit_result2)) {
                                                errors2 = cons(auto_commit_result2, errors2);
                                            }
                                        }
                                        if (NIL != errors2) {
                                            error2 = sdbc.sqls_handle_transaction_errors(errors2);
                                        }
                                    }
                                } finally {
                                    final SubLObject _prev_bind_4 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values2 = getValuesAsVector();
                                        if (NIL != sdbc.sql_connection_p(connection2)) {
                                            sdbc.sqlc_close(connection2);
                                        }
                                        restoreValuesFromVector(_values2);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_4, thread);
                                    }
                                }
                                if (NIL != error2) {
                                    sdbc.sdbc_error_warn(error2);
                                }
                            } 
                        } finally {
                            releaseEntrySetIterator(cdohash_iterator);
                        }
                    }
                }
            } else {
                SubLObject error3 = NIL;
                final SubLObject db3 = sksi_access_path.access_path_db(access_path);
                final SubLObject user3 = sksi_access_path.access_path_user(access_path);
                final SubLObject password3 = sksi_access_path.access_path_password(access_path);
                final SubLObject server3 = sksi_access_path.access_path_server(access_path);
                final SubLObject port3 = sksi_access_path.access_path_port(access_path);
                final SubLObject proxy_server3 = sksi_access_path.access_path_proxy_server(access_path);
                final SubLObject proxy_port3 = sksi_access_path.access_path_proxy_port(access_path);
                final SubLObject subprotocol3 = sksi_access_path.access_path_subprotocol(access_path);
                final SubLObject timeout3 = sksi_access_path.access_path_timeout(access_path);
                final SubLObject conn_server3 = (NIL != proxy_server3) ? proxy_server3 : server3;
                final SubLObject conn_port3 = (NIL != proxy_port3) ? proxy_port3 : port3;
                final SubLObject conn_timeout3 = (NIL != timeout3) ? timeout3 : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
                SubLObject statement3 = NIL;
                SubLObject connection3 = NIL;
                try {
                    connection3 = sdbc.new_sql_connection(db3, user3, password3, list(new SubLObject[]{ $DBMS_SERVER, server3, $PORT, conn_port3, $SUBPROTOCOL, subprotocol3, $PROXY_SERVER, conn_server3, $TIMEOUT, conn_timeout3 }));
                    if (NIL != sdbc.sql_open_connection_p(connection3)) {
                        statement3 = sdbc.sqlc_create_statement(connection3);
                    } else {
                        statement3 = connection3;
                    }
                    if (NIL != sdbc.sql_open_statement_p(statement3)) {
                        final SubLObject connection_$5 = sdbc.sqls_get_connection(statement3);
                        final SubLObject auto_commit3 = sdbc.sqlc_get_auto_commit(connection_$5);
                        SubLObject commit_error3 = NIL;
                        SubLObject rollback_result3 = NIL;
                        SubLObject auto_commit_result3 = NIL;
                        SubLObject errors3 = NIL;
                        if (NIL != sdbc.sdbc_error_p(auto_commit3)) {
                            errors3 = cons(auto_commit3, errors3);
                        } else {
                            sdbc.sqlc_set_error_handling(connection_$5, $THROW);
                            try {
                                thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                                final SubLObject _prev_bind_5 = Errors.$error_handler$.currentBinding(thread);
                                try {
                                    Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                    try {
                                        if (NIL != auto_commit3) {
                                            sdbc.sqlc_set_auto_commit(connection_$5, NIL);
                                        }
                                        final SubLObject idx2 = inference_datastructures_problem_store.problem_store_id_index();
                                        if (NIL == id_index_objects_empty_p(idx2, $SKIP)) {
                                            final SubLObject idx_$4 = idx2;
                                            if (NIL == id_index_dense_objects_empty_p(idx_$4, $SKIP)) {
                                                final SubLObject vector_var2 = id_index_dense_objects(idx_$4);
                                                final SubLObject backwardP_var2 = NIL;
                                                SubLObject length2;
                                                SubLObject v_iteration2;
                                                SubLObject id3;
                                                SubLObject problem_store3;
                                                for (length2 = length(vector_var2), v_iteration2 = NIL, v_iteration2 = ZERO_INTEGER; v_iteration2.numL(length2); v_iteration2 = add(v_iteration2, ONE_INTEGER)) {
                                                    id3 = (NIL != backwardP_var2) ? subtract(length2, v_iteration2, ONE_INTEGER) : v_iteration2;
                                                    problem_store3 = aref(vector_var2, id3);
                                                    if ((NIL == id_index_tombstone_p(problem_store3)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                                                        if (NIL != id_index_tombstone_p(problem_store3)) {
                                                            problem_store3 = $SKIP;
                                                        }
                                                        if (NIL != butler_write_problem_store_to_inference_store_int(problem_store3, statement3)) {
                                                            count = add(count, ONE_INTEGER);
                                                        }
                                                    }
                                                }
                                            }
                                            final SubLObject idx_$5 = idx2;
                                            if (NIL == id_index_sparse_objects_empty_p(idx_$5)) {
                                                final SubLObject cdohash_table2 = id_index_sparse_objects(idx_$5);
                                                SubLObject id4 = NIL;
                                                SubLObject problem_store4 = NIL;
                                                final Iterator cdohash_iterator2 = getEntrySetIterator(cdohash_table2);
                                                try {
                                                    while (iteratorHasNext(cdohash_iterator2)) {
                                                        final Map.Entry cdohash_entry2 = iteratorNextEntry(cdohash_iterator2);
                                                        id4 = getEntryKey(cdohash_entry2);
                                                        problem_store4 = getEntryValue(cdohash_entry2);
                                                        if (NIL != butler_write_problem_store_to_inference_store_int(problem_store4, statement3)) {
                                                            count = add(count, ONE_INTEGER);
                                                        }
                                                    } 
                                                } finally {
                                                    releaseEntrySetIterator(cdohash_iterator2);
                                                }
                                            }
                                        }
                                        sdbc.sqlc_commit(connection_$5);
                                    } catch (final Throwable catch_var3) {
                                        Errors.handleThrowable(catch_var3, NIL);
                                    }
                                } finally {
                                    Errors.$error_handler$.rebind(_prev_bind_5, thread);
                                }
                            } catch (final Throwable ccatch_env_var3) {
                                commit_error3 = Errors.handleThrowable(ccatch_env_var3, $catch_error_message_target$.getGlobalValue());
                            } finally {
                                thread.throwStack.pop();
                            }
                            sdbc.sqlc_set_error_handling(connection_$5, $RETURN);
                            if (NIL != commit_error3) {
                                errors3 = cons(sdbc.sqls_handle_commit_error(commit_error3), errors3);
                                rollback_result3 = sdbc.sqls_handle_rollback(connection_$5);
                                if (NIL != sdbc.sdbc_error_p(rollback_result3)) {
                                    errors3 = cons(rollback_result3, errors3);
                                }
                            }
                            if (NIL != auto_commit3) {
                                auto_commit_result3 = sdbc.sqlc_set_auto_commit(connection_$5, T);
                            }
                            if (NIL != sdbc.sdbc_error_p(auto_commit_result3)) {
                                errors3 = cons(auto_commit_result3, errors3);
                            }
                        }
                        if (NIL != errors3) {
                            error3 = sdbc.sqls_handle_transaction_errors(errors3);
                        }
                    }
                } finally {
                    final SubLObject _prev_bind_6 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values3 = getValuesAsVector();
                        if (NIL != sdbc.sql_connection_p(connection3)) {
                            sdbc.sqlc_close(connection3);
                        }
                        restoreValuesFromVector(_values3);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_6, thread);
                    }
                }
                if (NIL != error3) {
                    count = ZERO_INTEGER;
                    sdbc.sdbc_error_warn(error3);
                }
            }
        }
        return count;
    }

    /**
     * Writes a random selection of problem stores to the Butler inference store.  The optional
     * parameter P, which defaults to 0.5, is the probability that any particular problem store
     * will be written out.  If MULTIPLE-TRANSACTIONS? is non-NIL, then each problem store is
     * written out in its own transaction; otherwise, all problem stores are written out in a
     * single transaction (so that either all or none are successfully written out).  Returns the
     * number of stores successfully written out.
     */
    @LispMethod(comment = "Writes a random selection of problem stores to the Butler inference store.  The optional\r\nparameter P, which defaults to 0.5, is the probability that any particular problem store\r\nwill be written out.  If MULTIPLE-TRANSACTIONS? is non-NIL, then each problem store is\r\nwritten out in its own transaction; otherwise, all problem stores are written out in a\r\nsingle transaction (so that either all or none are successfully written out).  Returns the\r\nnumber of stores successfully written out.\nWrites a random selection of problem stores to the Butler inference store.  The optional\nparameter P, which defaults to 0.5, is the probability that any particular problem store\nwill be written out.  If MULTIPLE-TRANSACTIONS? is non-NIL, then each problem store is\nwritten out in its own transaction; otherwise, all problem stores are written out in a\nsingle transaction (so that either all or none are successfully written out).  Returns the\nnumber of stores successfully written out.")
    public static final SubLObject butler_write_random_problem_stores_to_inference_store_alt(SubLObject p, SubLObject multiple_transactionsP) {
        if (p == UNPROVIDED) {
            p = $float$0_5;
        }
        if (multiple_transactionsP == UNPROVIDED) {
            multiple_transactionsP = NIL;
        }
        SubLTrampolineFile.checkType(p, PROBABILITY_P);
        {
            SubLObject access_path = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_access_path();
            SubLObject count = ZERO_INTEGER;
            if (NIL != sksi_access_path.access_path_p(access_path)) {
                if (NIL != multiple_transactionsP) {
                    {
                        SubLObject idx = problem_store_id_index();
                        if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                            {
                                SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                                SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                                SubLObject problem_store = NIL;
                                while (NIL != id) {
                                    problem_store = do_id_index_state_object(idx, $SKIP, id, state_var);
                                    if (NIL != do_id_index_id_and_object_validP(id, problem_store, $SKIP)) {
                                        if (NIL != number_utilities.true_with_probability(p)) {
                                            {
                                                SubLObject error = NIL;
                                                SubLObject db = sksi_access_path.access_path_db(access_path);
                                                SubLObject user = sksi_access_path.access_path_user(access_path);
                                                SubLObject password = sksi_access_path.access_path_password(access_path);
                                                SubLObject server = sksi_access_path.access_path_server(access_path);
                                                SubLObject port = sksi_access_path.access_path_port(access_path);
                                                SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                                                SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                                                SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                                                SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
                                                SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
                                                SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
                                                SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getGlobalValue();
                                                SubLObject statement = NIL;
                                                SubLObject connection = NIL;
                                                try {
                                                    connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                                                    if (NIL != sdbc.sql_open_connection_p(connection)) {
                                                        statement = sdbc.sqlc_create_statement(connection);
                                                    } else {
                                                        statement = connection;
                                                    }
                                                    if (NIL != sdbc.sql_open_statement_p(statement)) {
                                                        {
                                                            SubLObject connection_4 = sdbc.sqls_get_connection(statement);
                                                            SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_4);
                                                            SubLObject commit_error = NIL;
                                                            SubLObject rollback_result = NIL;
                                                            SubLObject auto_commit_result = NIL;
                                                            SubLObject errors = NIL;
                                                            if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                                                errors = cons(auto_commit, errors);
                                                            } else {
                                                                sdbc.sqlc_set_error_handling(connection_4, $THROW);
                                                                try {
                                                                    {
                                                                        SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                                                                        try {
                                                                            bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                                                                            try {
                                                                                if (NIL != auto_commit) {
                                                                                    sdbc.sqlc_set_auto_commit(connection_4, NIL);
                                                                                }
                                                                                if (NIL != com.cyc.cycjava.cycl.butler.butler_inference_store.butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                                                                    count = add(count, ONE_INTEGER);
                                                                                }
                                                                                sdbc.sqlc_commit(connection_4);
                                                                            } catch (Throwable catch_var) {
                                                                                Errors.handleThrowable(catch_var, NIL);
                                                                            }
                                                                        } finally {
                                                                            rebind(Errors.$error_handler$, _prev_bind_0);
                                                                        }
                                                                    }
                                                                } catch (Throwable ccatch_env_var) {
                                                                    commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                                                }
                                                                sdbc.sqlc_set_error_handling(connection_4, $RETURN);
                                                                if (NIL != commit_error) {
                                                                    errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                                                    rollback_result = sdbc.sqls_handle_rollback(connection_4);
                                                                    if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                                                        errors = cons(rollback_result, errors);
                                                                    }
                                                                }
                                                                if (NIL != auto_commit) {
                                                                    auto_commit_result = sdbc.sqlc_set_auto_commit(connection_4, T);
                                                                }
                                                                if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                                                    errors = cons(auto_commit_result, errors);
                                                                }
                                                            }
                                                            if (NIL != errors) {
                                                                error = sdbc.sqls_handle_transaction_errors(errors);
                                                            }
                                                        }
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != sdbc.sql_connection_p(connection)) {
                                                                sdbc.sqlc_close(connection);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                                                        }
                                                    }
                                                }
                                                if (NIL != error) {
                                                    sdbc.sdbc_error_warn(error);
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
                } else {
                    {
                        SubLObject error = NIL;
                        SubLObject db = sksi_access_path.access_path_db(access_path);
                        SubLObject user = sksi_access_path.access_path_user(access_path);
                        SubLObject password = sksi_access_path.access_path_password(access_path);
                        SubLObject server = sksi_access_path.access_path_server(access_path);
                        SubLObject port = sksi_access_path.access_path_port(access_path);
                        SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                        SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                        SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                        SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
                        SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
                        SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
                        SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getGlobalValue();
                        SubLObject statement = NIL;
                        SubLObject connection = NIL;
                        try {
                            connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                            if (NIL != sdbc.sql_open_connection_p(connection)) {
                                statement = sdbc.sqlc_create_statement(connection);
                            } else {
                                statement = connection;
                            }
                            if (NIL != sdbc.sql_open_statement_p(statement)) {
                                {
                                    SubLObject connection_5 = sdbc.sqls_get_connection(statement);
                                    SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_5);
                                    SubLObject commit_error = NIL;
                                    SubLObject rollback_result = NIL;
                                    SubLObject auto_commit_result = NIL;
                                    SubLObject errors = NIL;
                                    if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                        errors = cons(auto_commit, errors);
                                    } else {
                                        sdbc.sqlc_set_error_handling(connection_5, $THROW);
                                        try {
                                            {
                                                SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                                                try {
                                                    bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                                                    try {
                                                        if (NIL != auto_commit) {
                                                            sdbc.sqlc_set_auto_commit(connection_5, NIL);
                                                        }
                                                        {
                                                            SubLObject idx = problem_store_id_index();
                                                            if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                                                                {
                                                                    SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                                                                    SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                                                                    SubLObject problem_store = NIL;
                                                                    while (NIL != id) {
                                                                        problem_store = do_id_index_state_object(idx, $SKIP, id, state_var);
                                                                        if (NIL != do_id_index_id_and_object_validP(id, problem_store, $SKIP)) {
                                                                            if (NIL != number_utilities.true_with_probability(p)) {
                                                                                if (NIL != com.cyc.cycjava.cycl.butler.butler_inference_store.butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                                                                    count = add(count, ONE_INTEGER);
                                                                                }
                                                                            }
                                                                        }
                                                                        id = do_id_index_next_id(idx, NIL, id, state_var);
                                                                        state_var = do_id_index_next_state(idx, NIL, id, state_var);
                                                                    } 
                                                                }
                                                            }
                                                        }
                                                        sdbc.sqlc_commit(connection_5);
                                                    } catch (Throwable catch_var) {
                                                        Errors.handleThrowable(catch_var, NIL);
                                                    }
                                                } finally {
                                                    rebind(Errors.$error_handler$, _prev_bind_0);
                                                }
                                            }
                                        } catch (Throwable ccatch_env_var) {
                                            commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                        }
                                        sdbc.sqlc_set_error_handling(connection_5, $RETURN);
                                        if (NIL != commit_error) {
                                            errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                            rollback_result = sdbc.sqls_handle_rollback(connection_5);
                                            if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                                errors = cons(rollback_result, errors);
                                            }
                                        }
                                        if (NIL != auto_commit) {
                                            auto_commit_result = sdbc.sqlc_set_auto_commit(connection_5, T);
                                        }
                                        if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                            errors = cons(auto_commit_result, errors);
                                        }
                                    }
                                    if (NIL != errors) {
                                        error = sdbc.sqls_handle_transaction_errors(errors);
                                    }
                                }
                            }
                        } finally {
                            {
                                SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    if (NIL != sdbc.sql_connection_p(connection)) {
                                        sdbc.sqlc_close(connection);
                                    }
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                                }
                            }
                        }
                        if (NIL != error) {
                            count = ZERO_INTEGER;
                            sdbc.sdbc_error_warn(error);
                        }
                    }
                }
            }
            return count;
        }
    }

    /**
     * Writes a random selection of problem stores to the Butler inference store.  The optional
     * parameter P, which defaults to 0.5, is the probability that any particular problem store
     * will be written out.  If MULTIPLE-TRANSACTIONS? is non-NIL, then each problem store is
     * written out in its own transaction; otherwise, all problem stores are written out in a
     * single transaction (so that either all or none are successfully written out).  Returns the
     * number of stores successfully written out.
     */
    @LispMethod(comment = "Writes a random selection of problem stores to the Butler inference store.  The optional\r\nparameter P, which defaults to 0.5, is the probability that any particular problem store\r\nwill be written out.  If MULTIPLE-TRANSACTIONS? is non-NIL, then each problem store is\r\nwritten out in its own transaction; otherwise, all problem stores are written out in a\r\nsingle transaction (so that either all or none are successfully written out).  Returns the\r\nnumber of stores successfully written out.\nWrites a random selection of problem stores to the Butler inference store.  The optional\nparameter P, which defaults to 0.5, is the probability that any particular problem store\nwill be written out.  If MULTIPLE-TRANSACTIONS? is non-NIL, then each problem store is\nwritten out in its own transaction; otherwise, all problem stores are written out in a\nsingle transaction (so that either all or none are successfully written out).  Returns the\nnumber of stores successfully written out.")
    public static SubLObject butler_write_random_problem_stores_to_inference_store(SubLObject p, SubLObject multiple_transactionsP) {
        if (p == UNPROVIDED) {
            p = $float$0_5;
        }
        if (multiple_transactionsP == UNPROVIDED) {
            multiple_transactionsP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != number_utilities.probability_p(p) : "! number_utilities.probability_p(p) " + ("number_utilities.probability_p(p) " + "CommonSymbols.NIL != number_utilities.probability_p(p) ") + p;
        final SubLObject access_path = butler_inference_store_access_path();
        SubLObject count = ZERO_INTEGER;
        if (NIL != sksi_access_path.access_path_p(access_path)) {
            if (NIL != multiple_transactionsP) {
                final SubLObject idx = inference_datastructures_problem_store.problem_store_id_index();
                if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
                    final SubLObject idx_$9 = idx;
                    if (NIL == id_index_dense_objects_empty_p(idx_$9, $SKIP)) {
                        final SubLObject vector_var = id_index_dense_objects(idx_$9);
                        final SubLObject backwardP_var = NIL;
                        SubLObject length;
                        SubLObject v_iteration;
                        SubLObject id;
                        SubLObject problem_store;
                        SubLObject error;
                        SubLObject db;
                        SubLObject user;
                        SubLObject password;
                        SubLObject server;
                        SubLObject port;
                        SubLObject proxy_server;
                        SubLObject proxy_port;
                        SubLObject subprotocol;
                        SubLObject timeout;
                        SubLObject conn_server;
                        SubLObject conn_port;
                        SubLObject conn_timeout;
                        SubLObject statement;
                        SubLObject connection;
                        SubLObject connection_$10;
                        SubLObject auto_commit;
                        SubLObject commit_error;
                        SubLObject rollback_result;
                        SubLObject auto_commit_result;
                        SubLObject errors;
                        SubLObject _prev_bind_0;
                        SubLObject _prev_bind_2;
                        SubLObject _values;
                        for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                            id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                            problem_store = aref(vector_var, id);
                            if ((NIL == id_index_tombstone_p(problem_store)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                                if (NIL != id_index_tombstone_p(problem_store)) {
                                    problem_store = $SKIP;
                                }
                                if (NIL != number_utilities.true_with_probability(p)) {
                                    error = NIL;
                                    db = sksi_access_path.access_path_db(access_path);
                                    user = sksi_access_path.access_path_user(access_path);
                                    password = sksi_access_path.access_path_password(access_path);
                                    server = sksi_access_path.access_path_server(access_path);
                                    port = sksi_access_path.access_path_port(access_path);
                                    proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                                    proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                                    subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                                    timeout = sksi_access_path.access_path_timeout(access_path);
                                    conn_server = (NIL != proxy_server) ? proxy_server : server;
                                    conn_port = (NIL != proxy_port) ? proxy_port : port;
                                    conn_timeout = (NIL != timeout) ? timeout : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
                                    statement = NIL;
                                    connection = NIL;
                                    try {
                                        connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                                        if (NIL != sdbc.sql_open_connection_p(connection)) {
                                            statement = sdbc.sqlc_create_statement(connection);
                                        } else {
                                            statement = connection;
                                        }
                                        if (NIL != sdbc.sql_open_statement_p(statement)) {
                                            connection_$10 = sdbc.sqls_get_connection(statement);
                                            auto_commit = sdbc.sqlc_get_auto_commit(connection_$10);
                                            commit_error = NIL;
                                            rollback_result = NIL;
                                            auto_commit_result = NIL;
                                            errors = NIL;
                                            if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                                errors = cons(auto_commit, errors);
                                            } else {
                                                sdbc.sqlc_set_error_handling(connection_$10, $THROW);
                                                try {
                                                    thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                                                    _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                                                    try {
                                                        Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                        try {
                                                            if (NIL != auto_commit) {
                                                                sdbc.sqlc_set_auto_commit(connection_$10, NIL);
                                                            }
                                                            if (NIL != butler_write_problem_store_to_inference_store_int(problem_store, statement)) {
                                                                count = add(count, ONE_INTEGER);
                                                            }
                                                            sdbc.sqlc_commit(connection_$10);
                                                        } catch (final Throwable catch_var) {
                                                            Errors.handleThrowable(catch_var, NIL);
                                                        }
                                                    } finally {
                                                        Errors.$error_handler$.rebind(_prev_bind_0, thread);
                                                    }
                                                } catch (final Throwable ccatch_env_var) {
                                                    commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                                } finally {
                                                    thread.throwStack.pop();
                                                }
                                                sdbc.sqlc_set_error_handling(connection_$10, $RETURN);
                                                if (NIL != commit_error) {
                                                    errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                                    rollback_result = sdbc.sqls_handle_rollback(connection_$10);
                                                    if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                                        errors = cons(rollback_result, errors);
                                                    }
                                                }
                                                if (NIL != auto_commit) {
                                                    auto_commit_result = sdbc.sqlc_set_auto_commit(connection_$10, T);
                                                }
                                                if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                                    errors = cons(auto_commit_result, errors);
                                                }
                                            }
                                            if (NIL != errors) {
                                                error = sdbc.sqls_handle_transaction_errors(errors);
                                            }
                                        }
                                    } finally {
                                        _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                        try {
                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                            _values = getValuesAsVector();
                                            if (NIL != sdbc.sql_connection_p(connection)) {
                                                sdbc.sqlc_close(connection);
                                            }
                                            restoreValuesFromVector(_values);
                                        } finally {
                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
                                        }
                                    }
                                    if (NIL != error) {
                                        sdbc.sdbc_error_warn(error);
                                    }
                                }
                            }
                        }
                    }
                    final SubLObject idx_$10 = idx;
                    if (NIL == id_index_sparse_objects_empty_p(idx_$10)) {
                        final SubLObject cdohash_table = id_index_sparse_objects(idx_$10);
                        SubLObject id2 = NIL;
                        SubLObject problem_store2 = NIL;
                        final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                        try {
                            while (iteratorHasNext(cdohash_iterator)) {
                                final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                                id2 = getEntryKey(cdohash_entry);
                                problem_store2 = getEntryValue(cdohash_entry);
                                if (NIL != number_utilities.true_with_probability(p)) {
                                    SubLObject error2 = NIL;
                                    final SubLObject db2 = sksi_access_path.access_path_db(access_path);
                                    final SubLObject user2 = sksi_access_path.access_path_user(access_path);
                                    final SubLObject password2 = sksi_access_path.access_path_password(access_path);
                                    final SubLObject server2 = sksi_access_path.access_path_server(access_path);
                                    final SubLObject port2 = sksi_access_path.access_path_port(access_path);
                                    final SubLObject proxy_server2 = sksi_access_path.access_path_proxy_server(access_path);
                                    final SubLObject proxy_port2 = sksi_access_path.access_path_proxy_port(access_path);
                                    final SubLObject subprotocol2 = sksi_access_path.access_path_subprotocol(access_path);
                                    final SubLObject timeout2 = sksi_access_path.access_path_timeout(access_path);
                                    final SubLObject conn_server2 = (NIL != proxy_server2) ? proxy_server2 : server2;
                                    final SubLObject conn_port2 = (NIL != proxy_port2) ? proxy_port2 : port2;
                                    final SubLObject conn_timeout2 = (NIL != timeout2) ? timeout2 : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
                                    SubLObject statement2 = NIL;
                                    SubLObject connection2 = NIL;
                                    try {
                                        connection2 = sdbc.new_sql_connection(db2, user2, password2, list(new SubLObject[]{ $DBMS_SERVER, server2, $PORT, conn_port2, $SUBPROTOCOL, subprotocol2, $PROXY_SERVER, conn_server2, $TIMEOUT, conn_timeout2 }));
                                        if (NIL != sdbc.sql_open_connection_p(connection2)) {
                                            statement2 = sdbc.sqlc_create_statement(connection2);
                                        } else {
                                            statement2 = connection2;
                                        }
                                        if (NIL != sdbc.sql_open_statement_p(statement2)) {
                                            final SubLObject connection_$11 = sdbc.sqls_get_connection(statement2);
                                            final SubLObject auto_commit2 = sdbc.sqlc_get_auto_commit(connection_$11);
                                            SubLObject commit_error2 = NIL;
                                            SubLObject rollback_result2 = NIL;
                                            SubLObject auto_commit_result2 = NIL;
                                            SubLObject errors2 = NIL;
                                            if (NIL != sdbc.sdbc_error_p(auto_commit2)) {
                                                errors2 = cons(auto_commit2, errors2);
                                            } else {
                                                sdbc.sqlc_set_error_handling(connection_$11, $THROW);
                                                try {
                                                    thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                                                    final SubLObject _prev_bind_3 = Errors.$error_handler$.currentBinding(thread);
                                                    try {
                                                        Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                        try {
                                                            if (NIL != auto_commit2) {
                                                                sdbc.sqlc_set_auto_commit(connection_$11, NIL);
                                                            }
                                                            if (NIL != butler_write_problem_store_to_inference_store_int(problem_store2, statement2)) {
                                                                count = add(count, ONE_INTEGER);
                                                            }
                                                            sdbc.sqlc_commit(connection_$11);
                                                        } catch (final Throwable catch_var2) {
                                                            Errors.handleThrowable(catch_var2, NIL);
                                                        }
                                                    } finally {
                                                        Errors.$error_handler$.rebind(_prev_bind_3, thread);
                                                    }
                                                } catch (final Throwable ccatch_env_var2) {
                                                    commit_error2 = Errors.handleThrowable(ccatch_env_var2, $catch_error_message_target$.getGlobalValue());
                                                } finally {
                                                    thread.throwStack.pop();
                                                }
                                                sdbc.sqlc_set_error_handling(connection_$11, $RETURN);
                                                if (NIL != commit_error2) {
                                                    errors2 = cons(sdbc.sqls_handle_commit_error(commit_error2), errors2);
                                                    rollback_result2 = sdbc.sqls_handle_rollback(connection_$11);
                                                    if (NIL != sdbc.sdbc_error_p(rollback_result2)) {
                                                        errors2 = cons(rollback_result2, errors2);
                                                    }
                                                }
                                                if (NIL != auto_commit2) {
                                                    auto_commit_result2 = sdbc.sqlc_set_auto_commit(connection_$11, T);
                                                }
                                                if (NIL != sdbc.sdbc_error_p(auto_commit_result2)) {
                                                    errors2 = cons(auto_commit_result2, errors2);
                                                }
                                            }
                                            if (NIL != errors2) {
                                                error2 = sdbc.sqls_handle_transaction_errors(errors2);
                                            }
                                        }
                                    } finally {
                                        final SubLObject _prev_bind_4 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                        try {
                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                            final SubLObject _values2 = getValuesAsVector();
                                            if (NIL != sdbc.sql_connection_p(connection2)) {
                                                sdbc.sqlc_close(connection2);
                                            }
                                            restoreValuesFromVector(_values2);
                                        } finally {
                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_4, thread);
                                        }
                                    }
                                    if (NIL == error2) {
                                        continue;
                                    }
                                    sdbc.sdbc_error_warn(error2);
                                }
                            } 
                        } finally {
                            releaseEntrySetIterator(cdohash_iterator);
                        }
                    }
                }
            } else {
                SubLObject error3 = NIL;
                final SubLObject db3 = sksi_access_path.access_path_db(access_path);
                final SubLObject user3 = sksi_access_path.access_path_user(access_path);
                final SubLObject password3 = sksi_access_path.access_path_password(access_path);
                final SubLObject server3 = sksi_access_path.access_path_server(access_path);
                final SubLObject port3 = sksi_access_path.access_path_port(access_path);
                final SubLObject proxy_server3 = sksi_access_path.access_path_proxy_server(access_path);
                final SubLObject proxy_port3 = sksi_access_path.access_path_proxy_port(access_path);
                final SubLObject subprotocol3 = sksi_access_path.access_path_subprotocol(access_path);
                final SubLObject timeout3 = sksi_access_path.access_path_timeout(access_path);
                final SubLObject conn_server3 = (NIL != proxy_server3) ? proxy_server3 : server3;
                final SubLObject conn_port3 = (NIL != proxy_port3) ? proxy_port3 : port3;
                final SubLObject conn_timeout3 = (NIL != timeout3) ? timeout3 : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
                SubLObject statement3 = NIL;
                SubLObject connection3 = NIL;
                try {
                    connection3 = sdbc.new_sql_connection(db3, user3, password3, list(new SubLObject[]{ $DBMS_SERVER, server3, $PORT, conn_port3, $SUBPROTOCOL, subprotocol3, $PROXY_SERVER, conn_server3, $TIMEOUT, conn_timeout3 }));
                    if (NIL != sdbc.sql_open_connection_p(connection3)) {
                        statement3 = sdbc.sqlc_create_statement(connection3);
                    } else {
                        statement3 = connection3;
                    }
                    if (NIL != sdbc.sql_open_statement_p(statement3)) {
                        final SubLObject connection_$12 = sdbc.sqls_get_connection(statement3);
                        final SubLObject auto_commit3 = sdbc.sqlc_get_auto_commit(connection_$12);
                        SubLObject commit_error3 = NIL;
                        SubLObject rollback_result3 = NIL;
                        SubLObject auto_commit_result3 = NIL;
                        SubLObject errors3 = NIL;
                        if (NIL != sdbc.sdbc_error_p(auto_commit3)) {
                            errors3 = cons(auto_commit3, errors3);
                        } else {
                            sdbc.sqlc_set_error_handling(connection_$12, $THROW);
                            try {
                                thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                                final SubLObject _prev_bind_5 = Errors.$error_handler$.currentBinding(thread);
                                try {
                                    Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                    try {
                                        if (NIL != auto_commit3) {
                                            sdbc.sqlc_set_auto_commit(connection_$12, NIL);
                                        }
                                        final SubLObject idx2 = inference_datastructures_problem_store.problem_store_id_index();
                                        if (NIL == id_index_objects_empty_p(idx2, $SKIP)) {
                                            final SubLObject idx_$11 = idx2;
                                            if (NIL == id_index_dense_objects_empty_p(idx_$11, $SKIP)) {
                                                final SubLObject vector_var2 = id_index_dense_objects(idx_$11);
                                                final SubLObject backwardP_var2 = NIL;
                                                SubLObject length2;
                                                SubLObject v_iteration2;
                                                SubLObject id3;
                                                SubLObject problem_store3;
                                                for (length2 = length(vector_var2), v_iteration2 = NIL, v_iteration2 = ZERO_INTEGER; v_iteration2.numL(length2); v_iteration2 = add(v_iteration2, ONE_INTEGER)) {
                                                    id3 = (NIL != backwardP_var2) ? subtract(length2, v_iteration2, ONE_INTEGER) : v_iteration2;
                                                    problem_store3 = aref(vector_var2, id3);
                                                    if ((NIL == id_index_tombstone_p(problem_store3)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                                                        if (NIL != id_index_tombstone_p(problem_store3)) {
                                                            problem_store3 = $SKIP;
                                                        }
                                                        if ((NIL != number_utilities.true_with_probability(p)) && (NIL != butler_write_problem_store_to_inference_store_int(problem_store3, statement3))) {
                                                            count = add(count, ONE_INTEGER);
                                                        }
                                                    }
                                                }
                                            }
                                            final SubLObject idx_$12 = idx2;
                                            if (NIL == id_index_sparse_objects_empty_p(idx_$12)) {
                                                final SubLObject cdohash_table2 = id_index_sparse_objects(idx_$12);
                                                SubLObject id4 = NIL;
                                                SubLObject problem_store4 = NIL;
                                                final Iterator cdohash_iterator2 = getEntrySetIterator(cdohash_table2);
                                                try {
                                                    while (iteratorHasNext(cdohash_iterator2)) {
                                                        final Map.Entry cdohash_entry2 = iteratorNextEntry(cdohash_iterator2);
                                                        id4 = getEntryKey(cdohash_entry2);
                                                        problem_store4 = getEntryValue(cdohash_entry2);
                                                        if ((NIL != number_utilities.true_with_probability(p)) && (NIL != butler_write_problem_store_to_inference_store_int(problem_store4, statement3))) {
                                                            count = add(count, ONE_INTEGER);
                                                        }
                                                    } 
                                                } finally {
                                                    releaseEntrySetIterator(cdohash_iterator2);
                                                }
                                            }
                                        }
                                        sdbc.sqlc_commit(connection_$12);
                                    } catch (final Throwable catch_var3) {
                                        Errors.handleThrowable(catch_var3, NIL);
                                    }
                                } finally {
                                    Errors.$error_handler$.rebind(_prev_bind_5, thread);
                                }
                            } catch (final Throwable ccatch_env_var3) {
                                commit_error3 = Errors.handleThrowable(ccatch_env_var3, $catch_error_message_target$.getGlobalValue());
                            } finally {
                                thread.throwStack.pop();
                            }
                            sdbc.sqlc_set_error_handling(connection_$12, $RETURN);
                            if (NIL != commit_error3) {
                                errors3 = cons(sdbc.sqls_handle_commit_error(commit_error3), errors3);
                                rollback_result3 = sdbc.sqls_handle_rollback(connection_$12);
                                if (NIL != sdbc.sdbc_error_p(rollback_result3)) {
                                    errors3 = cons(rollback_result3, errors3);
                                }
                            }
                            if (NIL != auto_commit3) {
                                auto_commit_result3 = sdbc.sqlc_set_auto_commit(connection_$12, T);
                            }
                            if (NIL != sdbc.sdbc_error_p(auto_commit_result3)) {
                                errors3 = cons(auto_commit_result3, errors3);
                            }
                        }
                        if (NIL != errors3) {
                            error3 = sdbc.sqls_handle_transaction_errors(errors3);
                        }
                    }
                } finally {
                    final SubLObject _prev_bind_6 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values3 = getValuesAsVector();
                        if (NIL != sdbc.sql_connection_p(connection3)) {
                            sdbc.sqlc_close(connection3);
                        }
                        restoreValuesFromVector(_values3);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_6, thread);
                    }
                }
                if (NIL != error3) {
                    count = ZERO_INTEGER;
                    sdbc.sdbc_error_warn(error3);
                }
            }
        }
        return count;
    }

    public static final SubLObject butler_write_problem_store_to_inference_store_int_alt(SubLObject problem_store, SubLObject statement) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject problem_store_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_problem_store(problem_store, statement);
                SubLObject already_presentP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL == already_presentP) {
                    if (problem_store_id.isInteger()) {
                        {
                            SubLObject idx = problem_store_problem_id_index(problem_store);
                            if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                                {
                                    SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                                    SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                                    SubLObject problem = NIL;
                                    while (NIL != id) {
                                        problem = do_id_index_state_object(idx, $SKIP, id, state_var);
                                        if (NIL != do_id_index_id_and_object_validP(id, problem, $SKIP)) {
                                            if (NIL != inference_datastructures_problem.single_literal_problem_p(problem)) {
                                                com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_problem(problem, problem_store_id, statement);
                                            }
                                        }
                                        id = do_id_index_next_id(idx, NIL, id, state_var);
                                        state_var = do_id_index_next_state(idx, NIL, id, state_var);
                                    } 
                                }
                            }
                        }
                    }
                }
                return makeBoolean(NIL == already_presentP);
            }
        }
    }

    public static SubLObject butler_write_problem_store_to_inference_store_int(final SubLObject problem_store, final SubLObject statement) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject problem_store_id = butler_inference_store_insert_problem_store(problem_store, statement);
        final SubLObject already_presentP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if ((NIL == already_presentP) && problem_store_id.isInteger()) {
            final SubLObject idx = inference_datastructures_problem_store.problem_store_problem_id_index(problem_store);
            if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
                final SubLObject idx_$16 = idx;
                if (NIL == id_index_dense_objects_empty_p(idx_$16, $SKIP)) {
                    final SubLObject vector_var = id_index_dense_objects(idx_$16);
                    final SubLObject backwardP_var = NIL;
                    SubLObject length;
                    SubLObject v_iteration;
                    SubLObject id;
                    SubLObject problem;
                    for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                        id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                        problem = aref(vector_var, id);
                        if ((NIL == id_index_tombstone_p(problem)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                            if (NIL != id_index_tombstone_p(problem)) {
                                problem = $SKIP;
                            }
                            if (NIL != inference_datastructures_problem.single_literal_problem_p(problem)) {
                                butler_inference_store_insert_problem(problem, problem_store_id, statement);
                            }
                        }
                    }
                }
                final SubLObject idx_$17 = idx;
                if (NIL == id_index_sparse_objects_empty_p(idx_$17)) {
                    final SubLObject cdohash_table = id_index_sparse_objects(idx_$17);
                    SubLObject id2 = NIL;
                    SubLObject problem2 = NIL;
                    final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                    try {
                        while (iteratorHasNext(cdohash_iterator)) {
                            final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                            id2 = getEntryKey(cdohash_entry);
                            problem2 = getEntryValue(cdohash_entry);
                            if (NIL != inference_datastructures_problem.single_literal_problem_p(problem2)) {
                                butler_inference_store_insert_problem(problem2, problem_store_id, statement);
                            }
                        } 
                    } finally {
                        releaseEntrySetIterator(cdohash_iterator);
                    }
                }
            }
        }
        return makeBoolean(NIL == already_presentP);
    }

    public static final SubLObject butler_inference_store_insert_problem_store_alt(SubLObject problem_store, SubLObject statement) {
        {
            SubLObject guid_string = Guids.guid_to_string(problem_store_guid(problem_store));
            SubLObject system = system_info.cyc_revision_string();
            SubLObject kb = kb_loaded();
            SubLObject machine = Environment.get_machine_name(UNPROVIDED);
            SubLObject creation_time = com.cyc.cycjava.cycl.butler.butler_inference_store.universal_time_to_sql_timestamp(problem_store_creation_time(problem_store));
            SubLObject insert_sql = cconcatenate($str_alt16$INSERT_into_problem_stores__guid_, new SubLObject[]{ format_nil.format_nil_a_no_copy(guid_string), $str_alt17$____, format_nil.format_nil_a_no_copy(system), $str_alt18$___, format_nil.format_nil_a_no_copy(kb), $str_alt19$___, format_nil.format_nil_a_no_copy(machine), $str_alt17$____, format_nil.format_nil_a_no_copy(creation_time), $str_alt20$__ });
            SubLObject query_sql = cconcatenate($str_alt21$SELECT_id_FROM_problem_stores_WHE, new SubLObject[]{ format_nil.format_nil_a_no_copy(guid_string), $str_alt22$_ });
            SubLObject problem_store_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_query_for_single_value(query_sql, statement);
            SubLObject already_presentP = list_utilities.sublisp_boolean(problem_store_id);
            if (NIL == already_presentP) {
                com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_execute_sql_update(insert_sql, statement);
                problem_store_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_query_for_single_value(query_sql, statement);
            }
            return values(problem_store_id, already_presentP);
        }
    }

    public static SubLObject butler_inference_store_insert_problem_store(final SubLObject problem_store, final SubLObject statement) {
        final SubLObject guid_string = Guids.guid_to_string(inference_datastructures_problem_store.problem_store_guid(problem_store));
        final SubLObject system = system_info.cyc_revision_string();
        final SubLObject kb = kb_loaded();
        final SubLObject machine = Environment.get_machine_name(UNPROVIDED);
        final SubLObject creation_time = universal_time_to_sql_timestamp(inference_datastructures_problem_store.problem_store_creation_time(problem_store));
        final SubLObject insert_sql = cconcatenate($str16$INSERT_into_problem_stores__guid_, new SubLObject[]{ format_nil.format_nil_a_no_copy(guid_string), $str17$____, format_nil.format_nil_a_no_copy(system), $str18$___, format_nil.format_nil_a_no_copy(kb), $str19$___, format_nil.format_nil_a_no_copy(machine), $str17$____, format_nil.format_nil_a_no_copy(creation_time), $str20$__ });
        final SubLObject query_sql = cconcatenate($str21$SELECT_id_FROM_problem_stores_WHE, new SubLObject[]{ format_nil.format_nil_a_no_copy(guid_string), $str22$_ });
        SubLObject problem_store_id = butler_inference_store_query_for_single_value(query_sql, statement);
        final SubLObject already_presentP = list_utilities.sublisp_boolean(problem_store_id);
        if (NIL == already_presentP) {
            butler_inference_store_execute_sql_update(insert_sql, statement);
            problem_store_id = butler_inference_store_query_for_single_value(query_sql, statement);
        }
        return values(problem_store_id, already_presentP);
    }

    public static final SubLObject butler_inference_store_insert_problem_alt(SubLObject problem, SubLObject problem_store_id, SubLObject statement) {
        {
            SubLObject sentence = inference_datastructures_problem.single_literal_problem_atomic_sentence(problem);
            SubLObject mt = inference_datastructures_problem.single_literal_problem_mt(problem);
            SubLObject suid = inference_datastructures_problem.problem_suid(problem);
            SubLObject status = inference_datastructures_problem.problem_status(problem);
            SubLObject sentence_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_problem_query_sentence(sentence, statement);
            SubLObject mt_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_problem_query_mt(mt, statement);
            SubLObject status_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_status_id(status);
            SubLObject insert_sql = cconcatenate($str_alt23$INSERT_into_problems__prob_store_, new SubLObject[]{ format_nil.format_nil_a_no_copy(problem_store_id), $str_alt24$__, format_nil.format_nil_a_no_copy(sentence_id), $str_alt24$__, format_nil.format_nil_a_no_copy(mt_id), $str_alt24$__, format_nil.format_nil_a_no_copy(suid), $str_alt24$__, format_nil.format_nil_a_no_copy(status_id), $str_alt25$_ });
            com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_execute_sql_update(insert_sql, statement);
            return NIL;
        }
    }

    public static SubLObject butler_inference_store_insert_problem(final SubLObject problem, final SubLObject problem_store_id, final SubLObject statement) {
        final SubLObject sentence = inference_datastructures_problem.single_literal_problem_atomic_sentence(problem);
        final SubLObject mt = inference_datastructures_problem.single_literal_problem_mt(problem);
        final SubLObject suid = inference_datastructures_problem.problem_suid(problem);
        final SubLObject status = inference_datastructures_problem.problem_status(problem);
        final SubLObject sentence_id = butler_inference_store_insert_problem_query_sentence(sentence, statement);
        final SubLObject mt_id = butler_inference_store_insert_problem_query_mt(mt, statement);
        final SubLObject status_id = butler_inference_store_status_id(status);
        final SubLObject insert_sql = cconcatenate($str23$INSERT_into_problems__prob_store_, new SubLObject[]{ format_nil.format_nil_a_no_copy(problem_store_id), $str24$__, format_nil.format_nil_a_no_copy(sentence_id), $str24$__, format_nil.format_nil_a_no_copy(mt_id), $str24$__, format_nil.format_nil_a_no_copy(suid), $str24$__, format_nil.format_nil_a_no_copy(status_id), $str25$_ });
        butler_inference_store_execute_sql_update(insert_sql, statement);
        return NIL;
    }

    public static final SubLObject butler_inference_store_insert_problem_query_sentence_alt(SubLObject sentence, SubLObject statement) {
        if (NIL != variables.fully_bound_p(sentence)) {
            return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_expression(sentence, $butler_inference_store_fully_bound_formula_type$.getGlobalValue(), statement);
        } else {
            return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_expression(sentence, $butler_inference_store_not_fully_bound_formula_type$.getGlobalValue(), statement);
        }
    }

    public static SubLObject butler_inference_store_insert_problem_query_sentence(final SubLObject sentence, final SubLObject statement) {
        if (NIL != variables.fully_bound_p(sentence)) {
            return butler_inference_store_insert_expression(sentence, $butler_inference_store_fully_bound_formula_type$.getGlobalValue(), statement);
        }
        return butler_inference_store_insert_expression(sentence, $butler_inference_store_not_fully_bound_formula_type$.getGlobalValue(), statement);
    }

    public static final SubLObject butler_inference_store_insert_problem_query_mt_alt(SubLObject mt, SubLObject statement) {
        if (NIL != constant_p(mt)) {
            return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_expression(mt, $butler_inference_store_constant_type$.getGlobalValue(), statement);
        } else {
            if (NIL != nart_handles.nart_p(mt)) {
                return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_expression(mt, $butler_inference_store_nart_type$.getGlobalValue(), statement);
            } else {
                if (NIL != narts_high.naut_p(mt)) {
                    return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_expression(mt, $butler_inference_store_naut_type$.getGlobalValue(), statement);
                } else {
                    return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_unknown_expression_type_warning(mt);
                }
            }
        }
    }

    public static SubLObject butler_inference_store_insert_problem_query_mt(final SubLObject mt, final SubLObject statement) {
        if (NIL != constant_p(mt)) {
            return butler_inference_store_insert_expression(mt, $butler_inference_store_constant_type$.getGlobalValue(), statement);
        }
        if (NIL != nart_handles.nart_p(mt)) {
            return butler_inference_store_insert_expression(mt, $butler_inference_store_nart_type$.getGlobalValue(), statement);
        }
        if (NIL != narts_high.naut_p(mt)) {
            return butler_inference_store_insert_expression(mt, $butler_inference_store_naut_type$.getGlobalValue(), statement);
        }
        return butler_inference_store_unknown_expression_type_warning(mt);
    }

    public static final SubLObject butler_inference_store_insert_expression_alt(SubLObject expression, SubLObject type, SubLObject statement) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject expression_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_expression_int(expression, type, statement);
                SubLObject already_presentP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL == already_presentP) {
                    if (NIL != com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_complex_type_p(type)) {
                        {
                            SubLObject terms = cycl_utilities.formula_terms(expression, $IGNORE);
                            SubLObject list_var = NIL;
                            SubLObject subexpression = NIL;
                            SubLObject argnum = NIL;
                            for (list_var = terms, subexpression = list_var.first(), argnum = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , subexpression = list_var.first() , argnum = number_utilities.f_1X(argnum)) {
                                {
                                    SubLObject type_6 = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_expression_type(subexpression);
                                    if (NIL != type_6) {
                                        com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_subexpression(subexpression, type_6, argnum, expression_id, statement);
                                    } else {
                                        if (NIL != assertion_handles.assertion_p(subexpression)) {
                                            {
                                                SubLObject formula = assertions_high.assertion_formula(subexpression);
                                                SubLObject formula_type = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_expression_type(formula);
                                                SubLObject mt = assertions_high.assertion_mt(subexpression);
                                                SubLObject mt_type = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_expression_type(mt);
                                                if (NIL != formula_type) {
                                                    com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_subexpression(formula, formula_type, argnum, expression_id, statement);
                                                } else {
                                                    com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_unknown_expression_type_warning(formula);
                                                }
                                                if (NIL != mt_type) {
                                                    com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_subexpression(mt, mt_type, argnum, expression_id, statement);
                                                } else {
                                                    com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_unknown_expression_type_warning(mt);
                                                }
                                            }
                                        } else {
                                            com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_unknown_expression_type_warning(subexpression);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return expression_id;
            }
        }
    }

    public static SubLObject butler_inference_store_insert_expression(final SubLObject expression, final SubLObject type, final SubLObject statement) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject expression_id = butler_inference_store_insert_expression_int(expression, type, statement);
        final SubLObject already_presentP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if ((NIL == already_presentP) && (NIL != butler_inference_store_complex_type_p(type))) {
            final SubLObject terms = cycl_utilities.formula_terms(expression, $IGNORE);
            SubLObject list_var = NIL;
            SubLObject subexpression = NIL;
            SubLObject argnum = NIL;
            list_var = terms;
            subexpression = list_var.first();
            for (argnum = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , subexpression = list_var.first() , argnum = number_utilities.f_1X(argnum)) {
                final SubLObject type_$18 = butler_inference_store_expression_type(subexpression);
                if (NIL != type_$18) {
                    butler_inference_store_insert_subexpression(subexpression, type_$18, argnum, expression_id, statement);
                } else
                    if (NIL != assertion_handles.assertion_p(subexpression)) {
                        final SubLObject formula = assertions_high.assertion_formula(subexpression);
                        final SubLObject formula_type = butler_inference_store_expression_type(formula);
                        final SubLObject mt = assertions_high.assertion_mt(subexpression);
                        final SubLObject mt_type = butler_inference_store_expression_type(mt);
                        if (NIL != formula_type) {
                            butler_inference_store_insert_subexpression(formula, formula_type, argnum, expression_id, statement);
                        } else {
                            butler_inference_store_unknown_expression_type_warning(formula);
                        }
                        if (NIL != mt_type) {
                            butler_inference_store_insert_subexpression(mt, mt_type, argnum, expression_id, statement);
                        } else {
                            butler_inference_store_unknown_expression_type_warning(mt);
                        }
                    } else {
                        butler_inference_store_unknown_expression_type_warning(subexpression);
                    }

            }
        }
        return expression_id;
    }

    public static final SubLObject butler_inference_store_insert_subexpression_alt(SubLObject subexpression, SubLObject type, SubLObject argnum, SubLObject expression_id, SubLObject statement) {
        {
            SubLObject subexpression_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_insert_expression(subexpression, type, statement);
            SubLObject insert_sql = cconcatenate($str_alt27$INSERT_INTO_complex_expressions__, new SubLObject[]{ format_nil.format_nil_a_no_copy(expression_id), $str_alt24$__, format_nil.format_nil_a_no_copy(argnum), $str_alt24$__, format_nil.format_nil_a_no_copy(subexpression_id), $str_alt25$_ });
            com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_execute_sql_update(insert_sql, statement);
        }
        return NIL;
    }

    public static SubLObject butler_inference_store_insert_subexpression(final SubLObject subexpression, final SubLObject type, final SubLObject argnum, final SubLObject expression_id, final SubLObject statement) {
        final SubLObject subexpression_id = butler_inference_store_insert_expression(subexpression, type, statement);
        final SubLObject insert_sql = cconcatenate($str27$INSERT_INTO_complex_expressions__, new SubLObject[]{ format_nil.format_nil_a_no_copy(expression_id), $str24$__, format_nil.format_nil_a_no_copy(argnum), $str24$__, format_nil.format_nil_a_no_copy(subexpression_id), $str25$_ });
        butler_inference_store_execute_sql_update(insert_sql, statement);
        return NIL;
    }

    public static final SubLObject butler_inference_store_insert_expression_int_alt(SubLObject expression, SubLObject type, SubLObject statement) {
        {
            SubLObject external_id_string = kb_utilities.hl_external_id_string(expression);
            SubLObject insert_sql = cconcatenate($str_alt28$INSERT_INTO_expressions__external, new SubLObject[]{ format_nil.format_nil_a_no_copy(external_id_string), $str_alt18$___, format_nil.format_nil_a_no_copy(type), $str_alt25$_ });
            SubLObject query_sql = cconcatenate($str_alt29$SELECT_id_FROM_expressions_WHERE_, new SubLObject[]{ format_nil.format_nil_a_no_copy(external_id_string), $str_alt22$_ });
            SubLObject expression_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_query_for_single_value(query_sql, statement);
            SubLObject already_presentP = list_utilities.sublisp_boolean(expression_id);
            if (NIL == already_presentP) {
                com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_execute_sql_update(insert_sql, statement);
                expression_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_query_for_single_value(query_sql, statement);
            }
            return values(expression_id, already_presentP);
        }
    }

    public static SubLObject butler_inference_store_insert_expression_int(final SubLObject expression, final SubLObject type, final SubLObject statement) {
        final SubLObject external_id_string = kb_utilities.hl_external_id_string(expression);
        final SubLObject insert_sql = cconcatenate($str28$INSERT_INTO_expressions__external, new SubLObject[]{ format_nil.format_nil_a_no_copy(external_id_string), $str18$___, format_nil.format_nil_a_no_copy(type), $str25$_ });
        final SubLObject query_sql = cconcatenate($str29$SELECT_id_FROM_expressions_WHERE_, new SubLObject[]{ format_nil.format_nil_a_no_copy(external_id_string), $str22$_ });
        SubLObject expression_id = butler_inference_store_query_for_single_value(query_sql, statement);
        final SubLObject already_presentP = list_utilities.sublisp_boolean(expression_id);
        if (NIL == already_presentP) {
            butler_inference_store_execute_sql_update(insert_sql, statement);
            expression_id = butler_inference_store_query_for_single_value(query_sql, statement);
        }
        return values(expression_id, already_presentP);
    }

    /**
     * Given a GUID, returns the system, KB, machine name, and creation time
     * for the corresponding problem store.
     */
    @LispMethod(comment = "Given a GUID, returns the system, KB, machine name, and creation time\r\nfor the corresponding problem store.\nGiven a GUID, returns the system, KB, machine name, and creation time\nfor the corresponding problem store.")
    public static final SubLObject butler_inference_store_problem_store_properties_alt(SubLObject guid) {
        {
            SubLObject guid_string = Guids.guid_to_string(guid);
            SubLObject sql = cconcatenate($str_alt30$SELECT_DISTINCT_system__kb__machi, new SubLObject[]{ format_nil.format_nil_a_no_copy(guid_string), $str_alt22$_ });
            SubLObject datum = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_query_for_single_row(sql, UNPROVIDED);
            SubLObject current = datum;
            SubLObject id = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
            destructuring_bind_must_listp(current, datum, $list_alt31);
            current = current.rest();
            {
                SubLObject system = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                destructuring_bind_must_listp(current, datum, $list_alt31);
                current = current.rest();
                {
                    SubLObject kb = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                    destructuring_bind_must_listp(current, datum, $list_alt31);
                    current = current.rest();
                    {
                        SubLObject machine = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt31);
                        current = current.rest();
                        {
                            SubLObject creation_time = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt31);
                            current = current.rest();
                            if (NIL == current) {
                                return values(system, kb, machine, creation_time);
                            } else {
                                cdestructuring_bind_error(datum, $list_alt31);
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Given a GUID, returns the system, KB, machine name, and creation time
     * for the corresponding problem store.
     */
    @LispMethod(comment = "Given a GUID, returns the system, KB, machine name, and creation time\r\nfor the corresponding problem store.\nGiven a GUID, returns the system, KB, machine name, and creation time\nfor the corresponding problem store.")
    public static SubLObject butler_inference_store_problem_store_properties(final SubLObject guid) {
        final SubLObject guid_string = Guids.guid_to_string(guid);
        final SubLObject sql = cconcatenate($str30$SELECT_DISTINCT_system__kb__machi, new SubLObject[]{ format_nil.format_nil_a_no_copy(guid_string), $str22$_ });
        SubLObject current;
        final SubLObject datum = current = butler_inference_store_query_for_single_row(sql, UNPROVIDED);
        final SubLObject id = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list31);
        current = current.rest();
        final SubLObject system = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list31);
        current = current.rest();
        final SubLObject kb = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list31);
        current = current.rest();
        final SubLObject machine = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list31);
        current = current.rest();
        final SubLObject creation_time = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list31);
        current = current.rest();
        if (NIL == current) {
            return values(system, kb, machine, creation_time);
        }
        cdestructuring_bind_error(datum, $list31);
        return NIL;
    }

    /**
     * Given PROBLEM-STORE-GUID and PROBLEM-SUID, returns the query sentence,
     * query Mt, and provability status for the corresponding problem.
     */
    @LispMethod(comment = "Given PROBLEM-STORE-GUID and PROBLEM-SUID, returns the query sentence,\r\nquery Mt, and provability status for the corresponding problem.\nGiven PROBLEM-STORE-GUID and PROBLEM-SUID, returns the query sentence,\nquery Mt, and provability status for the corresponding problem.")
    public static final SubLObject butler_inference_store_problem_properties_alt(SubLObject problem_store_guid, SubLObject problem_suid) {
        {
            SubLObject guid_string = Guids.guid_to_string(problem_store_guid);
            SubLObject sql = cconcatenate($str_alt32$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(problem_suid), $str_alt33$_AND_ps_id___p_prob_store_AND_ps_, format_nil.format_nil_a_no_copy(guid_string), $str_alt34$__AND_e1_id___p_sentence_AND_e2_i });
            SubLObject datum = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_query_for_single_row(sql, UNPROVIDED);
            SubLObject current = datum;
            SubLObject id = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
            destructuring_bind_must_listp(current, datum, $list_alt35);
            current = current.rest();
            {
                SubLObject sentence = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                destructuring_bind_must_listp(current, datum, $list_alt35);
                current = current.rest();
                {
                    SubLObject mt = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                    destructuring_bind_must_listp(current, datum, $list_alt35);
                    current = current.rest();
                    {
                        SubLObject status = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt35);
                        current = current.rest();
                        if (NIL == current) {
                            if (NIL != sentence) {
                                sentence = kb_utilities.find_object_by_hl_external_id_string(sentence);
                            }
                            if (NIL != mt) {
                                mt = kb_utilities.find_object_by_hl_external_id_string(mt);
                            }
                            if (NIL != status) {
                                status = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_id_status(status);
                            }
                            return values(sentence, mt, status);
                        } else {
                            cdestructuring_bind_error(datum, $list_alt35);
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Given PROBLEM-STORE-GUID and PROBLEM-SUID, returns the query sentence,
     * query Mt, and provability status for the corresponding problem.
     */
    @LispMethod(comment = "Given PROBLEM-STORE-GUID and PROBLEM-SUID, returns the query sentence,\r\nquery Mt, and provability status for the corresponding problem.\nGiven PROBLEM-STORE-GUID and PROBLEM-SUID, returns the query sentence,\nquery Mt, and provability status for the corresponding problem.")
    public static SubLObject butler_inference_store_problem_properties(final SubLObject problem_store_guid, final SubLObject problem_suid) {
        final SubLObject guid_string = Guids.guid_to_string(problem_store_guid);
        final SubLObject sql = cconcatenate($str32$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(problem_suid), $str33$_AND_ps_id___p_prob_store_AND_ps_, format_nil.format_nil_a_no_copy(guid_string), $str34$__AND_e1_id___p_sentence_AND_e2_i });
        SubLObject current;
        final SubLObject datum = current = butler_inference_store_query_for_single_row(sql, UNPROVIDED);
        final SubLObject id = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list35);
        current = current.rest();
        SubLObject sentence = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list35);
        current = current.rest();
        SubLObject mt = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list35);
        current = current.rest();
        SubLObject status = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list35);
        current = current.rest();
        if (NIL == current) {
            if (NIL != sentence) {
                sentence = kb_utilities.find_object_by_hl_external_id_string(sentence);
            }
            if (NIL != mt) {
                mt = kb_utilities.find_object_by_hl_external_id_string(mt);
            }
            if (NIL != status) {
                status = butler_inference_store_id_status(status);
            }
            return values(sentence, mt, status);
        }
        cdestructuring_bind_error(datum, $list35);
        return NIL;
    }

    /**
     * Return the number of problem stores in which SENTENCE appears as a problem
     * query.  If MT is non-NIL, then return the number of problem stores in which
     * SENTENCE is the problem query and MT is the problem Mt.
     */
    @LispMethod(comment = "Return the number of problem stores in which SENTENCE appears as a problem\r\nquery.  If MT is non-NIL, then return the number of problem stores in which\r\nSENTENCE is the problem query and MT is the problem Mt.\nReturn the number of problem stores in which SENTENCE appears as a problem\nquery.  If MT is non-NIL, then return the number of problem stores in which\nSENTENCE is the problem query and MT is the problem Mt.")
    public static final SubLObject butler_inference_store_problem_query_problem_store_count_alt(SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            SubLObject sql = NIL;
            if (NIL != mt) {
                {
                    SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
                    SubLObject mt_id = kb_utilities.hl_external_id_string(mt);
                    sql = cconcatenate($str_alt36$SELECT_COUNT_DISTINCT_p_prob_stor, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str_alt37$__AND_e2_external_id____, format_nil.format_nil_a_no_copy(mt_id), $str_alt22$_ });
                }
            } else {
                {
                    SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
                    sql = cconcatenate($str_alt38$SELECT_COUNT_DISTINCT_p_prob_stor, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str_alt22$_ });
                }
            }
            return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_query_for_single_value(sql, UNPROVIDED);
        }
    }

    /**
     * Return the number of problem stores in which SENTENCE appears as a problem
     * query.  If MT is non-NIL, then return the number of problem stores in which
     * SENTENCE is the problem query and MT is the problem Mt.
     */
    @LispMethod(comment = "Return the number of problem stores in which SENTENCE appears as a problem\r\nquery.  If MT is non-NIL, then return the number of problem stores in which\r\nSENTENCE is the problem query and MT is the problem Mt.\nReturn the number of problem stores in which SENTENCE appears as a problem\nquery.  If MT is non-NIL, then return the number of problem stores in which\nSENTENCE is the problem query and MT is the problem Mt.")
    public static SubLObject butler_inference_store_problem_query_problem_store_count(final SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        SubLObject sql = NIL;
        if (NIL != mt) {
            final SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
            final SubLObject mt_id = kb_utilities.hl_external_id_string(mt);
            sql = cconcatenate($str36$SELECT_COUNT_DISTINCT_p_prob_stor, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str37$__AND_e2_external_id____, format_nil.format_nil_a_no_copy(mt_id), $str22$_ });
        } else {
            final SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
            sql = cconcatenate($str38$SELECT_COUNT_DISTINCT_p_prob_stor, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str22$_ });
        }
        return butler_inference_store_query_for_single_value(sql, UNPROVIDED);
    }

    /**
     * Returns an iterator the most 'interesting' problem queries.  A query is
     * 'interesting' if it appears in a large number of problem stores.  The results
     * are returned in descending order of the number of problem stores in which a
     * problem appears.  If N is non-NIL, then only the first N results are returned.
     * If CONSIDER-MT? is NIL, then only the problem query sentence is returned;
     * otherwise both the sentence and the Mt are returned. If STATUS is either a
     * problem-status-p or a provability-status-p, then only problems with the
     * appropriate status will be considered.
     */
    @LispMethod(comment = "Returns an iterator the most \'interesting\' problem queries.  A query is\r\n\'interesting\' if it appears in a large number of problem stores.  The results\r\nare returned in descending order of the number of problem stores in which a\r\nproblem appears.  If N is non-NIL, then only the first N results are returned.\r\nIf CONSIDER-MT? is NIL, then only the problem query sentence is returned;\r\notherwise both the sentence and the Mt are returned. If STATUS is either a\r\nproblem-status-p or a provability-status-p, then only problems with the\r\nappropriate status will be considered.\nReturns an iterator the most \'interesting\' problem queries.  A query is\n\'interesting\' if it appears in a large number of problem stores.  The results\nare returned in descending order of the number of problem stores in which a\nproblem appears.  If N is non-NIL, then only the first N results are returned.\nIf CONSIDER-MT? is NIL, then only the problem query sentence is returned;\notherwise both the sentence and the Mt are returned. If STATUS is either a\nproblem-status-p or a provability-status-p, then only problems with the\nappropriate status will be considered.")
    public static final SubLObject butler_inference_store_most_interesting_problem_queries_alt(SubLObject n, SubLObject consider_mtP, SubLObject status) {
        if (n == UNPROVIDED) {
            n = NIL;
        }
        if (consider_mtP == UNPROVIDED) {
            consider_mtP = NIL;
        }
        if (status == UNPROVIDED) {
            status = NIL;
        }
        {
            SubLObject status_id = (NIL != status) ? ((SubLObject) (com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_status_id(status))) : NIL;
            SubLObject sql = NIL;
            SubLObject funcs = NIL;
            if ((NIL != status) && (NIL == status_id)) {
                return iteration.new_null_iterator();
            } else {
                if ((NIL != status_id) && (NIL != consider_mtP)) {
                    sql = cconcatenate($str_alt39$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str_alt40$_GROUP_BY_e1_external_id__e2_exte });
                    funcs = $list_alt41;
                } else {
                    if (NIL != status_id) {
                        sql = cconcatenate($str_alt42$SELECT_DISTINCT_e_external_id__CO, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str_alt43$_GROUP_BY_e_external_id_ORDER_BY_ });
                        funcs = $list_alt44;
                    } else {
                        if (NIL != consider_mtP) {
                            sql = $str_alt45$SELECT_DISTINCT_e1_external_id__e;
                            funcs = $list_alt41;
                        } else {
                            sql = $str_alt46$SELECT_DISTINCT_e_external_id__CO;
                            funcs = $list_alt44;
                        }
                    }
                }
            }
            if (NIL != subl_promotions.positive_integer_p(n)) {
                sql = cconcatenate(sql, cconcatenate($str_alt47$_LIMIT_, format_nil.format_nil_s_no_copy(n)));
            }
            return com.cyc.cycjava.cycl.butler.butler_inference_store.new_butler_inference_store_query_iterator(sql, funcs);
        }
    }

    /**
     * Returns an iterator the most 'interesting' problem queries.  A query is
     * 'interesting' if it appears in a large number of problem stores.  The results
     * are returned in descending order of the number of problem stores in which a
     * problem appears.  If N is non-NIL, then only the first N results are returned.
     * If CONSIDER-MT? is NIL, then only the problem query sentence is returned;
     * otherwise both the sentence and the Mt are returned. If STATUS is either a
     * problem-status-p or a provability-status-p, then only problems with the
     * appropriate status will be considered.
     */
    @LispMethod(comment = "Returns an iterator the most \'interesting\' problem queries.  A query is\r\n\'interesting\' if it appears in a large number of problem stores.  The results\r\nare returned in descending order of the number of problem stores in which a\r\nproblem appears.  If N is non-NIL, then only the first N results are returned.\r\nIf CONSIDER-MT? is NIL, then only the problem query sentence is returned;\r\notherwise both the sentence and the Mt are returned. If STATUS is either a\r\nproblem-status-p or a provability-status-p, then only problems with the\r\nappropriate status will be considered.\nReturns an iterator the most \'interesting\' problem queries.  A query is\n\'interesting\' if it appears in a large number of problem stores.  The results\nare returned in descending order of the number of problem stores in which a\nproblem appears.  If N is non-NIL, then only the first N results are returned.\nIf CONSIDER-MT? is NIL, then only the problem query sentence is returned;\notherwise both the sentence and the Mt are returned. If STATUS is either a\nproblem-status-p or a provability-status-p, then only problems with the\nappropriate status will be considered.")
    public static SubLObject butler_inference_store_most_interesting_problem_queries(SubLObject n, SubLObject consider_mtP, SubLObject status) {
        if (n == UNPROVIDED) {
            n = NIL;
        }
        if (consider_mtP == UNPROVIDED) {
            consider_mtP = NIL;
        }
        if (status == UNPROVIDED) {
            status = NIL;
        }
        final SubLObject status_id = (NIL != status) ? butler_inference_store_status_id(status) : NIL;
        SubLObject sql = NIL;
        SubLObject funcs = NIL;
        if ((NIL != status) && (NIL == status_id)) {
            return iteration.new_null_iterator();
        }
        if ((NIL != status_id) && (NIL != consider_mtP)) {
            sql = cconcatenate($str39$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str40$_GROUP_BY_e1_external_id__e2_exte });
            funcs = $list41;
        } else
            if (NIL != status_id) {
                sql = cconcatenate($str42$SELECT_DISTINCT_e_external_id__CO, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str43$_GROUP_BY_e_external_id_ORDER_BY_ });
                funcs = $list44;
            } else
                if (NIL != consider_mtP) {
                    sql = $str45$SELECT_DISTINCT_e1_external_id__e;
                    funcs = $list41;
                } else {
                    sql = $str46$SELECT_DISTINCT_e_external_id__CO;
                    funcs = $list44;
                }


        if (NIL != subl_promotions.positive_integer_p(n)) {
            sql = cconcatenate(sql, cconcatenate($$$_LIMIT_, format_nil.format_nil_s_no_copy(n)));
        }
        return new_butler_inference_store_query_iterator(sql, funcs);
    }

    /**
     * Returns an iterator of the problem queries 'related' to SENTENCE (and MT, if non-NIL).
     * Two problem queries Q1 and Q2 are related if any of the following hold:
     * (i) Q1 = Q2,
     * (ii) there is some problem store in which both Q1 and Q2 appear,
     * (iii) there is some Q3 such that both Q1 and Q2 are related to Q3.
     * Notice that this is an equivalence relation.
     */
    @LispMethod(comment = "Returns an iterator of the problem queries \'related\' to SENTENCE (and MT, if non-NIL).\r\nTwo problem queries Q1 and Q2 are related if any of the following hold:\r\n(i) Q1 = Q2,\r\n(ii) there is some problem store in which both Q1 and Q2 appear,\r\n(iii) there is some Q3 such that both Q1 and Q2 are related to Q3.\r\nNotice that this is an equivalence relation.\nReturns an iterator of the problem queries \'related\' to SENTENCE (and MT, if non-NIL).\nTwo problem queries Q1 and Q2 are related if any of the following hold:\n(i) Q1 = Q2,\n(ii) there is some problem store in which both Q1 and Q2 appear,\n(iii) there is some Q3 such that both Q1 and Q2 are related to Q3.\nNotice that this is an equivalence relation.")
    public static final SubLObject butler_inference_store_problem_query_related_problem_queries_alt(SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            SubLObject sql = NIL;
            SubLObject funcs = NIL;
            if (NIL != mt) {
                {
                    SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
                    SubLObject mt_id = kb_utilities.hl_external_id_string(mt);
                    sql = cconcatenate($str_alt48$SELECT_DISTINCT_e3_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str_alt49$__AND_p1_sentence___e1_id_AND_e2_, format_nil.format_nil_a_no_copy(mt_id), $str_alt50$__AND_p1_mt___e2_id_AND_p2_prob_s });
                    funcs = $list_alt51;
                }
            } else {
                {
                    SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
                    sql = cconcatenate($str_alt52$SELECT_DISTINCT_e2_external_id_FR, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str_alt53$__AND_p1_sentence___e1_id_AND_p2_ });
                    funcs = $list_alt54;
                }
            }
            return com.cyc.cycjava.cycl.butler.butler_inference_store.new_butler_inference_store_query_iterator(sql, funcs);
        }
    }

    /**
     * Returns an iterator of the problem queries 'related' to SENTENCE (and MT, if non-NIL).
     * Two problem queries Q1 and Q2 are related if any of the following hold:
     * (i) Q1 = Q2,
     * (ii) there is some problem store in which both Q1 and Q2 appear,
     * (iii) there is some Q3 such that both Q1 and Q2 are related to Q3.
     * Notice that this is an equivalence relation.
     */
    @LispMethod(comment = "Returns an iterator of the problem queries \'related\' to SENTENCE (and MT, if non-NIL).\r\nTwo problem queries Q1 and Q2 are related if any of the following hold:\r\n(i) Q1 = Q2,\r\n(ii) there is some problem store in which both Q1 and Q2 appear,\r\n(iii) there is some Q3 such that both Q1 and Q2 are related to Q3.\r\nNotice that this is an equivalence relation.\nReturns an iterator of the problem queries \'related\' to SENTENCE (and MT, if non-NIL).\nTwo problem queries Q1 and Q2 are related if any of the following hold:\n(i) Q1 = Q2,\n(ii) there is some problem store in which both Q1 and Q2 appear,\n(iii) there is some Q3 such that both Q1 and Q2 are related to Q3.\nNotice that this is an equivalence relation.")
    public static SubLObject butler_inference_store_problem_query_related_problem_queries(final SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        SubLObject sql = NIL;
        SubLObject funcs = NIL;
        if (NIL != mt) {
            final SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
            final SubLObject mt_id = kb_utilities.hl_external_id_string(mt);
            sql = cconcatenate($str48$SELECT_DISTINCT_e3_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str49$__AND_p1_sentence___e1_id_AND_e2_, format_nil.format_nil_a_no_copy(mt_id), $str50$__AND_p1_mt___e2_id_AND_p2_prob_s });
            funcs = $list51;
        } else {
            final SubLObject sentence_id = kb_utilities.hl_external_id_string(sentence);
            sql = cconcatenate($str52$SELECT_DISTINCT_e2_external_id_FR, new SubLObject[]{ format_nil.format_nil_a_no_copy(sentence_id), $str53$__AND_p1_sentence___e1_id_AND_p2_ });
            funcs = $list54;
        }
        return new_butler_inference_store_query_iterator(sql, funcs);
    }

    /**
     * Returns an iterator of the problem queries in which TERM appears in the ARGNUM
     * position of the query sentence.  If CONSIDER-MT? is non-NIL, then both the
     * query sentence and the Mt will be returned; otherwise, only the query sentence
     * will be returned.
     */
    @LispMethod(comment = "Returns an iterator of the problem queries in which TERM appears in the ARGNUM\r\nposition of the query sentence.  If CONSIDER-MT? is non-NIL, then both the\r\nquery sentence and the Mt will be returned; otherwise, only the query sentence\r\nwill be returned.\nReturns an iterator of the problem queries in which TERM appears in the ARGNUM\nposition of the query sentence.  If CONSIDER-MT? is non-NIL, then both the\nquery sentence and the Mt will be returned; otherwise, only the query sentence\nwill be returned.")
    public static final SubLObject butler_inference_store_problem_queries_with_term_in_sentence_arg_alt(SubLObject v_term, SubLObject argnum, SubLObject consider_mtP) {
        if (consider_mtP == UNPROVIDED) {
            consider_mtP = NIL;
        }
        {
            SubLObject term_id = kb_utilities.hl_external_id_string(v_term);
            SubLObject sql = (NIL != consider_mtP) ? ((SubLObject) (cconcatenate($str_alt55$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(argnum), $str_alt56$_AND_c_simple_exp___e3_id_AND_e3_, format_nil.format_nil_a_no_copy(term_id), $str_alt22$_ }))) : cconcatenate($str_alt57$SELECT_DISTINCT_e1_external_id_FR, new SubLObject[]{ format_nil.format_nil_a_no_copy(argnum), $str_alt58$_AND_c_simple_exp___e2_id_AND_e2_, format_nil.format_nil_a_no_copy(term_id), $str_alt22$_ });
            SubLObject funcs = (NIL != consider_mtP) ? ((SubLObject) ($list_alt51)) : $list_alt54;
            return com.cyc.cycjava.cycl.butler.butler_inference_store.new_butler_inference_store_query_iterator(sql, funcs);
        }
    }

    /**
     * Returns an iterator of the problem queries in which TERM appears in the ARGNUM
     * position of the query sentence.  If CONSIDER-MT? is non-NIL, then both the
     * query sentence and the Mt will be returned; otherwise, only the query sentence
     * will be returned.
     */
    @LispMethod(comment = "Returns an iterator of the problem queries in which TERM appears in the ARGNUM\r\nposition of the query sentence.  If CONSIDER-MT? is non-NIL, then both the\r\nquery sentence and the Mt will be returned; otherwise, only the query sentence\r\nwill be returned.\nReturns an iterator of the problem queries in which TERM appears in the ARGNUM\nposition of the query sentence.  If CONSIDER-MT? is non-NIL, then both the\nquery sentence and the Mt will be returned; otherwise, only the query sentence\nwill be returned.")
    public static SubLObject butler_inference_store_problem_queries_with_term_in_sentence_arg(final SubLObject v_term, final SubLObject argnum, SubLObject consider_mtP) {
        if (consider_mtP == UNPROVIDED) {
            consider_mtP = NIL;
        }
        final SubLObject term_id = kb_utilities.hl_external_id_string(v_term);
        final SubLObject sql = (NIL != consider_mtP) ? cconcatenate($str55$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(argnum), $str56$_AND_c_simple_exp___e3_id_AND_e3_, format_nil.format_nil_a_no_copy(term_id), $str22$_ }) : cconcatenate($str57$SELECT_DISTINCT_e1_external_id_FR, new SubLObject[]{ format_nil.format_nil_a_no_copy(argnum), $str58$_AND_c_simple_exp___e2_id_AND_e2_, format_nil.format_nil_a_no_copy(term_id), $str22$_ });
        final SubLObject funcs = (NIL != consider_mtP) ? $list51 : $list54;
        return new_butler_inference_store_query_iterator(sql, funcs);
    }

    /**
     * Returns an iterator of the problem queries with status STATUS (which may be either
     * a problem status or provability status).  If CONSIDER-MT? is NIL, then only the query
     * sentence is returned, otherwise the sentence and Mt are returned.
     */
    @LispMethod(comment = "Returns an iterator of the problem queries with status STATUS (which may be either\r\na problem status or provability status).  If CONSIDER-MT? is NIL, then only the query\r\nsentence is returned, otherwise the sentence and Mt are returned.\nReturns an iterator of the problem queries with status STATUS (which may be either\na problem status or provability status).  If CONSIDER-MT? is NIL, then only the query\nsentence is returned, otherwise the sentence and Mt are returned.")
    public static final SubLObject butler_inference_store_problem_queries_with_status_alt(SubLObject status, SubLObject consider_mtP) {
        if (consider_mtP == UNPROVIDED) {
            consider_mtP = NIL;
        }
        {
            SubLObject status_id = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_status_id(status);
            if (NIL != status_id) {
                {
                    SubLObject sql = (NIL != consider_mtP) ? ((SubLObject) (cconcatenate($str_alt59$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str_alt60$_AND_p_sentence___e1_id_AND_p_mt_ }))) : cconcatenate($str_alt61$SELECT_DISTINCT_e_external_id_FRO, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str_alt62$_AND_p_sentence___e_id });
                    SubLObject funcs = (NIL != consider_mtP) ? ((SubLObject) ($list_alt51)) : $list_alt54;
                    return com.cyc.cycjava.cycl.butler.butler_inference_store.new_butler_inference_store_query_iterator(sql, funcs);
                }
            } else {
                return iteration.new_null_iterator();
            }
        }
    }

    /**
     * Returns an iterator of the problem queries with status STATUS (which may be either
     * a problem status or provability status).  If CONSIDER-MT? is NIL, then only the query
     * sentence is returned, otherwise the sentence and Mt are returned.
     */
    @LispMethod(comment = "Returns an iterator of the problem queries with status STATUS (which may be either\r\na problem status or provability status).  If CONSIDER-MT? is NIL, then only the query\r\nsentence is returned, otherwise the sentence and Mt are returned.\nReturns an iterator of the problem queries with status STATUS (which may be either\na problem status or provability status).  If CONSIDER-MT? is NIL, then only the query\nsentence is returned, otherwise the sentence and Mt are returned.")
    public static SubLObject butler_inference_store_problem_queries_with_status(final SubLObject status, SubLObject consider_mtP) {
        if (consider_mtP == UNPROVIDED) {
            consider_mtP = NIL;
        }
        final SubLObject status_id = butler_inference_store_status_id(status);
        if (NIL != status_id) {
            final SubLObject sql = (NIL != consider_mtP) ? cconcatenate($str59$SELECT_DISTINCT_e1_external_id__e, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str60$_AND_p_sentence___e1_id_AND_p_mt_ }) : cconcatenate($str61$SELECT_DISTINCT_e_external_id_FRO, new SubLObject[]{ format_nil.format_nil_a_no_copy(status_id), $str62$_AND_p_sentence___e_id });
            final SubLObject funcs = (NIL != consider_mtP) ? $list51 : $list54;
            return new_butler_inference_store_query_iterator(sql, funcs);
        }
        return iteration.new_null_iterator();
    }

    public static final SubLObject do_butler_inference_store_most_interesting_problem_queries_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt63);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject problem_store_count_var = NIL;
                    SubLObject sentence_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt63);
                    problem_store_count_var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt63);
                    sentence_var = current.first();
                    current = current.rest();
                    {
                        SubLObject mt_var = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt63);
                        current = current.rest();
                        {
                            SubLObject allow_other_keys_p = NIL;
                            SubLObject rest = current;
                            SubLObject bad = NIL;
                            SubLObject current_7 = NIL;
                            for (; NIL != rest;) {
                                destructuring_bind_must_consp(rest, datum, $list_alt63);
                                current_7 = rest.first();
                                rest = rest.rest();
                                destructuring_bind_must_consp(rest, datum, $list_alt63);
                                if (NIL == member(current_7, $list_alt64, UNPROVIDED, UNPROVIDED)) {
                                    bad = T;
                                }
                                if (current_7 == $ALLOW_OTHER_KEYS) {
                                    allow_other_keys_p = rest.first();
                                }
                                rest = rest.rest();
                            }
                            if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                                cdestructuring_bind_error(datum, $list_alt63);
                            }
                            {
                                SubLObject n_tail = property_list_member($N, current);
                                SubLObject n = (NIL != n_tail) ? ((SubLObject) (cadr(n_tail))) : NIL;
                                SubLObject status_tail = property_list_member($STATUS, current);
                                SubLObject status = (NIL != status_tail) ? ((SubLObject) (cadr(status_tail))) : NIL;
                                SubLObject done_tail = property_list_member($DONE, current);
                                SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
                                current = temp;
                                {
                                    SubLObject body = current;
                                    SubLObject iterator = $sym69$ITERATOR;
                                    SubLObject row = $sym70$ROW;
                                    return NIL != mt_var ? ((SubLObject) (list(CLET, list(list(iterator, list(BUTLER_INFERENCE_STORE_MOST_INTERESTING_PROBLEM_QUERIES, n, T, status))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var, mt_var, problem_store_count_var), row, append(body, NIL)))))) : list(CLET, list(list(iterator, list(BUTLER_INFERENCE_STORE_MOST_INTERESTING_PROBLEM_QUERIES, n, NIL, status))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var, problem_store_count_var), row, append(body, NIL))));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static SubLObject do_butler_inference_store_most_interesting_problem_queries(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list63);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject problem_store_count_var = NIL;
        SubLObject sentence_var = NIL;
        destructuring_bind_must_consp(current, datum, $list63);
        problem_store_count_var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list63);
        sentence_var = current.first();
        current = current.rest();
        final SubLObject mt_var = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list63);
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$19 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list63);
            current_$19 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list63);
            if (NIL == member(current_$19, $list64, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$19 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list63);
        }
        final SubLObject n_tail = property_list_member($N, current);
        final SubLObject n = (NIL != n_tail) ? cadr(n_tail) : NIL;
        final SubLObject status_tail = property_list_member($STATUS, current);
        final SubLObject status = (NIL != status_tail) ? cadr(status_tail) : NIL;
        final SubLObject done_tail = property_list_member($DONE, current);
        final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
        final SubLObject body;
        current = body = temp;
        final SubLObject iterator = $sym69$ITERATOR;
        final SubLObject row = $sym70$ROW;
        return NIL != mt_var ? list(CLET, list(list(iterator, list(BUTLER_INFERENCE_STORE_MOST_INTERESTING_PROBLEM_QUERIES, n, T, status))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var, mt_var, problem_store_count_var), row, append(body, NIL)))) : list(CLET, list(list(iterator, list(BUTLER_INFERENCE_STORE_MOST_INTERESTING_PROBLEM_QUERIES, n, NIL, status))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var, problem_store_count_var), row, append(body, NIL))));
    }

    static private final SubLString $str_alt1$Could_not_find_access_path_for_Bu = makeString("Could not find access path for Butler inference store.");

    static private final SubLString $str_alt16$INSERT_into_problem_stores__guid_ = makeString("INSERT into problem_stores (guid, system, kb, machine, creation_time) VALUES ('");

    static private final SubLString $str_alt17$____ = makeString("', '");

    static private final SubLString $str_alt18$___ = makeString("', ");

    static private final SubLString $str_alt19$___ = makeString(", '");

    static private final SubLString $str_alt20$__ = makeString("')");

    static private final SubLString $str_alt21$SELECT_id_FROM_problem_stores_WHE = makeString("SELECT id FROM problem_stores WHERE guid = '");

    static private final SubLString $str_alt22$_ = makeString("'");

    static private final SubLString $str_alt23$INSERT_into_problems__prob_store_ = makeString("INSERT into problems (prob_store, sentence, mt, suid, status) VALUES (");

    static private final SubLString $str_alt24$__ = makeString(", ");

    static private final SubLString $str_alt25$_ = makeString(")");

    static private final SubLString $str_alt27$INSERT_INTO_complex_expressions__ = makeString("INSERT INTO complex_expressions (complex_exp, argnum, simple_exp) VALUES (");

    static private final SubLString $str_alt28$INSERT_INTO_expressions__external = makeString("INSERT INTO expressions (external_id, type) VALUES ('");

    static private final SubLString $str_alt29$SELECT_id_FROM_expressions_WHERE_ = makeString("SELECT id FROM expressions WHERE external_id = '");

    static private final SubLString $str_alt30$SELECT_DISTINCT_system__kb__machi = makeString("SELECT DISTINCT system, kb, machine, creation_time FROM problem_stores where guid = '");

    public static final SubLObject do_butler_inference_store_problem_queries_with_status_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt75);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject status = NIL;
                    SubLObject sentence_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt75);
                    status = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt75);
                    sentence_var = current.first();
                    current = current.rest();
                    {
                        SubLObject mt_var = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt75);
                        current = current.rest();
                        {
                            SubLObject allow_other_keys_p = NIL;
                            SubLObject rest = current;
                            SubLObject bad = NIL;
                            SubLObject current_8 = NIL;
                            for (; NIL != rest;) {
                                destructuring_bind_must_consp(rest, datum, $list_alt75);
                                current_8 = rest.first();
                                rest = rest.rest();
                                destructuring_bind_must_consp(rest, datum, $list_alt75);
                                if (NIL == member(current_8, $list_alt76, UNPROVIDED, UNPROVIDED)) {
                                    bad = T;
                                }
                                if (current_8 == $ALLOW_OTHER_KEYS) {
                                    allow_other_keys_p = rest.first();
                                }
                                rest = rest.rest();
                            }
                            if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                                cdestructuring_bind_error(datum, $list_alt75);
                            }
                            {
                                SubLObject done_tail = property_list_member($DONE, current);
                                SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
                                current = temp;
                                {
                                    SubLObject body = current;
                                    SubLObject iterator = $sym77$ITERATOR;
                                    SubLObject row = $sym78$ROW;
                                    return NIL != mt_var ? ((SubLObject) (list(CLET, list(list(iterator, listS(BUTLER_INFERENCE_STORE_PROBLEM_QUERIES_WITH_STATUS, status, $list_alt80))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var, mt_var), row, append(body, NIL)))))) : list(CLET, list(list(iterator, listS(BUTLER_INFERENCE_STORE_PROBLEM_QUERIES_WITH_STATUS, status, $list_alt81))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var), row, append(body, NIL))));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static SubLObject do_butler_inference_store_problem_queries_with_status(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list75);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject status = NIL;
        SubLObject sentence_var = NIL;
        destructuring_bind_must_consp(current, datum, $list75);
        status = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list75);
        sentence_var = current.first();
        current = current.rest();
        final SubLObject mt_var = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list75);
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$20 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list75);
            current_$20 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list75);
            if (NIL == member(current_$20, $list76, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$20 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list75);
        }
        final SubLObject done_tail = property_list_member($DONE, current);
        final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
        final SubLObject body;
        current = body = temp;
        final SubLObject iterator = $sym77$ITERATOR;
        final SubLObject row = $sym78$ROW;
        return NIL != mt_var ? list(CLET, list(list(iterator, listS(BUTLER_INFERENCE_STORE_PROBLEM_QUERIES_WITH_STATUS, status, $list80))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var, mt_var), row, append(body, NIL)))) : list(CLET, list(list(iterator, listS(BUTLER_INFERENCE_STORE_PROBLEM_QUERIES_WITH_STATUS, status, $list81))), list(DO_ITERATOR_WITHOUT_VALUES, list(row, iterator, $DONE, done), listS(CDESTRUCTURING_BIND, list(sentence_var), row, append(body, NIL))));
    }

    static private final SubLList $list_alt31 = list(makeSymbol("&OPTIONAL"), makeSymbol("ID"), makeSymbol("SYSTEM"), makeSymbol("KB"), makeSymbol("MACHINE"), makeSymbol("CREATION-TIME"));

    static private final SubLString $str_alt32$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id, p.status FROM problems p, problem_stores ps, expressions e1, expressions e2 WHERE p.suid = ");

    static private final SubLString $str_alt33$_AND_ps_id___p_prob_store_AND_ps_ = makeString(" AND ps.id = p.prob_store AND ps.guid = '");

    static private final SubLString $str_alt34$__AND_e1_id___p_sentence_AND_e2_i = makeString("' AND e1.id = p.sentence AND e2.id = p.mt");

    static private final SubLList $list_alt35 = list(makeSymbol("&OPTIONAL"), makeSymbol("ID"), makeSymbol("SENTENCE"), makeSymbol("MT"), makeSymbol("STATUS"));

    static private final SubLString $str_alt36$SELECT_COUNT_DISTINCT_p_prob_stor = makeString("SELECT COUNT(DISTINCT p.prob_store) FROM problems p, expressions e1, expressions e2 WHERE p.sentence = e1.id AND p.mt = e2.id AND e1.external_id = '");

    static private final SubLString $str_alt37$__AND_e2_external_id____ = makeString("' AND e2.external_id = '");

    static private final SubLString $str_alt38$SELECT_COUNT_DISTINCT_p_prob_stor = makeString("SELECT COUNT(DISTINCT p.prob_store) FROM problems p, expressions e WHERE p.sentence = e.id AND e.external_id = '");

    static private final SubLString $str_alt39$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e1, expressions e2 WHERE e1.id = p.sentence AND e2.id = p.mt AND p.status = ");

    static private final SubLString $str_alt40$_GROUP_BY_e1_external_id__e2_exte = makeString(" GROUP BY e1.external_id, e2.external_id ORDER BY count DESC");

    static private final SubLList $list_alt41 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), IDENTITY);

    static private final SubLString $str_alt42$SELECT_DISTINCT_e_external_id__CO = makeString("SELECT DISTINCT e.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e WHERE e.id = p.sentence AND p.status = ");

    static private final SubLString $str_alt43$_GROUP_BY_e_external_id_ORDER_BY_ = makeString(" GROUP BY e.external_id ORDER BY count DESC");

    public static final SubLObject butler_inference_store_unknown_expression_type_warning_alt(SubLObject expression) {
        return Errors.warn($str_alt82$Currently_cannot_handle_expressio, expression);
    }

    public static SubLObject butler_inference_store_unknown_expression_type_warning(final SubLObject expression) {
        return Errors.warn($str82$Currently_cannot_handle_expressio, expression);
    }

    static private final SubLList $list_alt44 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), IDENTITY);

    static private final SubLString $str_alt45$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e1, expressions e2 WHERE e1.id = p.sentence AND e2.id = p.mt GROUP BY e1.external_id, e2.external_id ORDER BY count DESC");

    public static final SubLObject butler_inference_store_query_for_single_value_alt(SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        {
            SubLObject result_set = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_execute_sql_query(sql, statement);
            SubLObject value = NIL;
            if (NIL != sdbc.sql_open_result_set_p(result_set)) {
                if (!sdbc.sqlrs_row_count(result_set).isZero()) {
                    sdbc.sqlrs_next(result_set);
                    value = sdbc.sqlrs_get_object(result_set, ONE_INTEGER);
                }
                sksi_result_set_iterators.finalize_result_set(result_set);
            }
            return value;
        }
    }

    public static SubLObject butler_inference_store_query_for_single_value(final SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        final SubLObject result_set = butler_inference_store_execute_sql_query(sql, statement);
        SubLObject value = NIL;
        if (NIL != sdbc.sql_open_result_set_p(result_set)) {
            if (!sdbc.sqlrs_row_count(result_set).isZero()) {
                sdbc.sqlrs_next(result_set);
                value = sdbc.sqlrs_get_object(result_set, ONE_INTEGER);
            }
            sksi_result_set_iterators.finalize_result_set(result_set);
        }
        return value;
    }

    static private final SubLString $str_alt46$SELECT_DISTINCT_e_external_id__CO = makeString("SELECT DISTINCT e.external_id, COUNT(DISTINCT p.prob_store) FROM problems p, expressions e WHERE e.id = p.sentence GROUP BY e.external_id ORDER BY count DESC");

    static private final SubLString $str_alt47$_LIMIT_ = makeString(" LIMIT ");

    static private final SubLString $str_alt48$SELECT_DISTINCT_e3_external_id__e = makeString("SELECT DISTINCT e3.external_id, e4.external_id FROM problems p1, problems p2, expressions e1, expressions e2, expressions e3, expressions e4 WHERE e1.external_id = '");

    public static final SubLObject butler_inference_store_query_for_single_row_alt(SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        {
            SubLObject result_set = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_execute_sql_query(sql, statement);
            SubLObject row = NIL;
            if (NIL != sdbc.sql_open_result_set_p(result_set)) {
                if (!sdbc.sqlrs_row_count(result_set).isZero()) {
                    sdbc.sqlrs_next(result_set);
                    row = sksi_result_set_iterators.sqlrs_current_row_as_list(result_set);
                }
                sksi_result_set_iterators.finalize_result_set(result_set);
            }
            return row;
        }
    }

    public static SubLObject butler_inference_store_query_for_single_row(final SubLObject sql, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        final SubLObject result_set = butler_inference_store_execute_sql_query(sql, statement);
        SubLObject row = NIL;
        if (NIL != sdbc.sql_open_result_set_p(result_set)) {
            if (!sdbc.sqlrs_row_count(result_set).isZero()) {
                sdbc.sqlrs_next(result_set);
                row = sksi_result_set_iterators.sqlrs_current_row_as_list(result_set);
            }
            sksi_result_set_iterators.finalize_result_set(result_set);
        }
        return row;
    }

    static private final SubLString $str_alt49$__AND_p1_sentence___e1_id_AND_e2_ = makeString("' AND p1.sentence = e1.id AND e2.external_id = '");

    static private final SubLString $str_alt50$__AND_p1_mt___e2_id_AND_p2_prob_s = makeString("' AND p1.mt = e2.id AND p2.prob_store = p1.prob_store AND p2.sentence = e3.id AND p2.mt = e4.id");

    static private final SubLList $list_alt51 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"), makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"));

    static private final SubLString $str_alt52$SELECT_DISTINCT_e2_external_id_FR = makeString("SELECT DISTINCT e2.external_id FROM problems p1, problems p2, expressions e1, expressions e2 WHERE e1.external_id = '");

    public static final SubLObject universal_time_to_sql_timestamp_alt(SubLObject universal_time) {
        return date_utilities.cyc_date_encode_string_internal($str_alt83$YYYY_MM_DD_HH_MM_SS, date_utilities.universal_time_to_cycl_date(universal_time));
    }

    public static SubLObject universal_time_to_sql_timestamp(final SubLObject universal_time) {
        return date_utilities.cyc_date_encode_string_internal($str83$YYYY_MM_DD_HH_MM_SS, date_utilities.universal_time_to_cycl_date(universal_time));
    }

    static private final SubLString $str_alt53$__AND_p1_sentence___e1_id_AND_p2_ = makeString("' AND p1.sentence = e1.id AND p2.prob_store = p1.prob_store AND p2.sentence = e2.id");

    public static final SubLObject sql_time_stamp_to_universal_time_alt(SubLObject time_stamp) {
        return date_utilities.cycl_date_to_universal_time(date_utilities.cyc_date_decode_string_internal($str_alt83$YYYY_MM_DD_HH_MM_SS, time_stamp));
    }

    public static SubLObject sql_time_stamp_to_universal_time(final SubLObject time_stamp) {
        return date_utilities.cycl_date_to_universal_time(date_utilities.cyc_date_decode_string_internal($str83$YYYY_MM_DD_HH_MM_SS, time_stamp));
    }

    static private final SubLList $list_alt54 = list(makeSymbol("FIND-OBJECT-BY-HL-EXTERNAL-ID-STRING"));

    static private final SubLString $str_alt55$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id FROM problems p, expressions e1, expressions e2, expressions e3, complex_expressions c WHERE p.sentence = e1.id AND p.mt = e2.id AND c.complex_exp = e1.id AND c.argnum = ");

    public static final SubLObject new_butler_inference_store_query_iterator_alt(SubLObject sql, SubLObject decoding_functions) {
        {
            SubLObject result_set = com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_execute_sql_query(sql, UNPROVIDED);
            if (NIL != sdbc.sql_open_result_set_p(result_set)) {
                return iteration.new_iterator(com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_iterator_state(result_set, decoding_functions), BUTLER_INFERENCE_STORE_ITERATOR_DONE, BUTLER_INFERENCE_STORE_ITERATOR_NEXT, BUTLER_INFERENCE_STORE_ITERATOR_FINALIZE);
            } else {
                return iteration.new_null_iterator();
            }
        }
    }

    public static SubLObject new_butler_inference_store_query_iterator(final SubLObject sql, final SubLObject decoding_functions) {
        final SubLObject result_set = butler_inference_store_execute_sql_query(sql, UNPROVIDED);
        if (NIL != sdbc.sql_open_result_set_p(result_set)) {
            return iteration.new_iterator(butler_inference_store_iterator_state(result_set, decoding_functions), BUTLER_INFERENCE_STORE_ITERATOR_DONE, BUTLER_INFERENCE_STORE_ITERATOR_NEXT, BUTLER_INFERENCE_STORE_ITERATOR_FINALIZE);
        }
        return iteration.new_null_iterator();
    }

    static private final SubLString $str_alt56$_AND_c_simple_exp___e3_id_AND_e3_ = makeString(" AND c.simple_exp = e3.id AND e3.external_id = '");

    static private final SubLString $str_alt57$SELECT_DISTINCT_e1_external_id_FR = makeString("SELECT DISTINCT e1.external_id FROM problems p, expressions e1, expressions e2, complex_expressions c WHERE p.sentence = e1.id AND c.complex_exp = e1.id AND c.argnum = ");

    public static final SubLObject butler_inference_store_iterator_state_alt(SubLObject result_set, SubLObject decoding_functions) {
        return list(sksi_result_set_iterators.new_sqlrs_iterator(result_set, UNPROVIDED, UNPROVIDED), decoding_functions);
    }

    public static SubLObject butler_inference_store_iterator_state(final SubLObject result_set, final SubLObject decoding_functions) {
        return list(sksi_result_set_iterators.new_sqlrs_iterator(result_set, UNPROVIDED, UNPROVIDED), decoding_functions);
    }

    static private final SubLString $str_alt58$_AND_c_simple_exp___e2_id_AND_e2_ = makeString(" AND c.simple_exp = e2.id AND e2.external_id = '");

    static private final SubLString $str_alt59$SELECT_DISTINCT_e1_external_id__e = makeString("SELECT DISTINCT e1.external_id, e2.external_id FROM problems p, expressions e1, expressions e2 WHERE p.status = ");

    public static final SubLObject butler_inference_store_iterator_done_alt(SubLObject state) {
        {
            SubLObject datum = state;
            SubLObject current = datum;
            SubLObject sub_iterator = NIL;
            SubLObject decoding_functions = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt87);
            sub_iterator = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt87);
            decoding_functions = current.first();
            current = current.rest();
            if (NIL == current) {
                return iteration.iteration_done(sub_iterator);
            } else {
                cdestructuring_bind_error(datum, $list_alt87);
            }
        }
        return NIL;
    }

    public static SubLObject butler_inference_store_iterator_done(final SubLObject state) {
        SubLObject sub_iterator = NIL;
        SubLObject decoding_functions = NIL;
        destructuring_bind_must_consp(state, state, $list87);
        sub_iterator = state.first();
        SubLObject current = state.rest();
        destructuring_bind_must_consp(current, state, $list87);
        decoding_functions = current.first();
        current = current.rest();
        if (NIL == current) {
            return iteration.iteration_done(sub_iterator);
        }
        cdestructuring_bind_error(state, $list87);
        return NIL;
    }

    static private final SubLString $str_alt60$_AND_p_sentence___e1_id_AND_p_mt_ = makeString(" AND p.sentence = e1.id AND p.mt = e2.id");

    static private final SubLString $str_alt61$SELECT_DISTINCT_e_external_id_FRO = makeString("SELECT DISTINCT e.external_id FROM problems p, expressions e WHERE p.status = ");

    static private final SubLString $str_alt62$_AND_p_sentence___e_id = makeString(" AND p.sentence = e.id");

    static private final SubLList $list_alt63 = list(list(makeSymbol("PROBLEM-STORE-COUNT-VAR"), makeSymbol("SENTENCE-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("MT-VAR"), makeSymbol("&KEY"), makeSymbol("N"), makeSymbol("STATUS"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final SubLObject butler_inference_store_iterator_next_alt(SubLObject state) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = state;
                SubLObject current = datum;
                SubLObject sub_iterator = NIL;
                SubLObject decoding_functions = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt87);
                sub_iterator = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt87);
                decoding_functions = current.first();
                current = current.rest();
                if (NIL == current) {
                    {
                        SubLObject next = NIL;
                        thread.resetMultipleValues();
                        {
                            SubLObject raw_row = iteration.iteration_next(sub_iterator);
                            SubLObject validP = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if (NIL != validP) {
                                {
                                    SubLObject raw_next = NIL;
                                    SubLObject raw_next_9 = NIL;
                                    SubLObject decoding_function = NIL;
                                    SubLObject decoding_function_10 = NIL;
                                    for (raw_next = raw_row, raw_next_9 = raw_next.first(), decoding_function = decoding_functions, decoding_function_10 = decoding_function.first(); !((NIL == decoding_function) && (NIL == raw_next)); raw_next = raw_next.rest() , raw_next_9 = raw_next.first() , decoding_function = decoding_function.rest() , decoding_function_10 = decoding_function.first()) {
                                        next = cons(com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_iterator_decoding_funcall(decoding_function_10, raw_next_9), next);
                                    }
                                }
                                next = nreverse(next);
                            }
                            return values(next, state, makeBoolean(NIL == validP));
                        }
                    }
                } else {
                    cdestructuring_bind_error(datum, $list_alt87);
                }
            }
            return NIL;
        }
    }

    public static SubLObject butler_inference_store_iterator_next(final SubLObject state) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject sub_iterator = NIL;
        SubLObject decoding_functions = NIL;
        destructuring_bind_must_consp(state, state, $list87);
        sub_iterator = state.first();
        SubLObject current = state.rest();
        destructuring_bind_must_consp(current, state, $list87);
        decoding_functions = current.first();
        current = current.rest();
        if (NIL == current) {
            SubLObject next = NIL;
            thread.resetMultipleValues();
            final SubLObject raw_row = iteration.iteration_next(sub_iterator);
            final SubLObject validP = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != validP) {
                SubLObject raw_next = NIL;
                SubLObject raw_next_$21 = NIL;
                SubLObject decoding_function = NIL;
                SubLObject decoding_function_$22 = NIL;
                raw_next = raw_row;
                raw_next_$21 = raw_next.first();
                decoding_function = decoding_functions;
                decoding_function_$22 = decoding_function.first();
                while ((NIL != decoding_function) || (NIL != raw_next)) {
                    next = cons(butler_inference_store_iterator_decoding_funcall(decoding_function_$22, raw_next_$21), next);
                    raw_next = raw_next.rest();
                    raw_next_$21 = raw_next.first();
                    decoding_function = decoding_function.rest();
                    decoding_function_$22 = decoding_function.first();
                } 
                next = nreverse(next);
            }
            return values(next, state, makeBoolean(NIL == validP));
        }
        cdestructuring_bind_error(state, $list87);
        return NIL;
    }

    static private final SubLList $list_alt64 = list(makeKeyword("N"), makeKeyword("STATUS"), $DONE);

    static private final SubLList $list_alt75 = list(list(makeSymbol("STATUS"), makeSymbol("SENTENCE-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("MT-VAR"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt76 = list($DONE);

    static private final SubLList $list_alt80 = list(T);

    static private final SubLList $list_alt81 = list(NIL);

    static private final SubLString $str_alt82$Currently_cannot_handle_expressio = makeString("Currently cannot handle expression ~s");

    static private final SubLString $str_alt83$YYYY_MM_DD_HH_MM_SS = makeString("YYYY-MM-DD HH:MM:SS");

    public static final SubLObject butler_inference_store_iterator_decoding_funcall_alt(SubLObject function, SubLObject arg) {
        {
            SubLObject pcase_var = function;
            if (pcase_var.eql(QUOTE) || pcase_var.eql(IDENTITY)) {
                return arg;
            } else {
                if (pcase_var.eql(QUOTE) || pcase_var.eql(FIND_OBJECT_BY_HL_EXTERNAL_ID_STRING)) {
                    return kb_utilities.find_object_by_hl_external_id_string(arg);
                } else {
                    if (pcase_var.eql(QUOTE) || pcase_var.eql(BUTLER_INFERENCE_STORE_ID_STATUS)) {
                        return com.cyc.cycjava.cycl.butler.butler_inference_store.butler_inference_store_id_status(arg);
                    } else {
                        if (pcase_var.eql(QUOTE) || pcase_var.eql(SQL_TIME_STAMP_TO_UNIVERSAL_TIME)) {
                            return com.cyc.cycjava.cycl.butler.butler_inference_store.sql_time_stamp_to_universal_time(arg);
                        } else {
                            return funcall(function, arg);
                        }
                    }
                }
            }
        }
    }

    public static SubLObject butler_inference_store_iterator_decoding_funcall(final SubLObject function, final SubLObject arg) {
        if (function.eql(QUOTE) || function.eql(IDENTITY)) {
            return arg;
        }
        if (function.eql(QUOTE) || function.eql(FIND_OBJECT_BY_HL_EXTERNAL_ID_STRING)) {
            return kb_utilities.find_object_by_hl_external_id_string(arg);
        }
        if (function.eql(QUOTE) || function.eql(BUTLER_INFERENCE_STORE_ID_STATUS)) {
            return butler_inference_store_id_status(arg);
        }
        if (function.eql(QUOTE) || function.eql(SQL_TIME_STAMP_TO_UNIVERSAL_TIME)) {
            return sql_time_stamp_to_universal_time(arg);
        }
        return funcall(function, arg);
    }

    static private final SubLList $list_alt87 = list(makeSymbol("SUB-ITERATOR"), makeSymbol("DECODING-FUNCTIONS"));

    static private final SubLString $str_alt95$Unknown_status__s = makeString("Unknown status ~s");

    public static final SubLObject butler_inference_store_iterator_finalize_alt(SubLObject state) {
        {
            SubLObject datum = state;
            SubLObject current = datum;
            SubLObject sub_iterator = NIL;
            SubLObject decoding_functions = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt87);
            sub_iterator = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt87);
            decoding_functions = current.first();
            current = current.rest();
            if (NIL == current) {
                return iteration.iteration_finalize(sub_iterator);
            } else {
                cdestructuring_bind_error(datum, $list_alt87);
            }
        }
        return NIL;
    }

    public static SubLObject butler_inference_store_iterator_finalize(final SubLObject state) {
        SubLObject sub_iterator = NIL;
        SubLObject decoding_functions = NIL;
        destructuring_bind_must_consp(state, state, $list87);
        sub_iterator = state.first();
        SubLObject current = state.rest();
        destructuring_bind_must_consp(current, state, $list87);
        decoding_functions = current.first();
        current = current.rest();
        if (NIL == current) {
            return iteration.iteration_finalize(sub_iterator);
        }
        cdestructuring_bind_error(state, $list87);
        return NIL;
    }

    public static final SubLObject butler_inference_store_simple_type_p_alt(SubLObject type) {
        return makeBoolean(((type.eql($butler_inference_store_constant_type$.getGlobalValue()) || type.eql($butler_inference_store_variable_type$.getGlobalValue())) || type.eql($butler_inference_store_string_type$.getGlobalValue())) || type.eql($butler_inference_store_number_type$.getGlobalValue()));
    }

    public static SubLObject butler_inference_store_simple_type_p(final SubLObject type) {
        return makeBoolean(((type.eql($butler_inference_store_constant_type$.getGlobalValue()) || type.eql($butler_inference_store_variable_type$.getGlobalValue())) || type.eql($butler_inference_store_string_type$.getGlobalValue())) || type.eql($butler_inference_store_number_type$.getGlobalValue()));
    }

    public static final SubLObject butler_inference_store_complex_type_p_alt(SubLObject type) {
        return makeBoolean(((type.eql($butler_inference_store_nart_type$.getGlobalValue()) || type.eql($butler_inference_store_naut_type$.getGlobalValue())) || type.eql($butler_inference_store_fully_bound_formula_type$.getGlobalValue())) || type.eql($butler_inference_store_not_fully_bound_formula_type$.getGlobalValue()));
    }

    public static SubLObject butler_inference_store_complex_type_p(final SubLObject type) {
        return makeBoolean(((type.eql($butler_inference_store_nart_type$.getGlobalValue()) || type.eql($butler_inference_store_naut_type$.getGlobalValue())) || type.eql($butler_inference_store_fully_bound_formula_type$.getGlobalValue())) || type.eql($butler_inference_store_not_fully_bound_formula_type$.getGlobalValue()));
    }

    public static final SubLObject butler_inference_store_expression_type_alt(SubLObject expression) {
        if (NIL != constant_p(expression)) {
            return $butler_inference_store_constant_type$.getGlobalValue();
        } else {
            if (NIL != variables.variable_p(expression)) {
                return $butler_inference_store_variable_type$.getGlobalValue();
            } else {
                if (NIL != nart_handles.nart_p(expression)) {
                    return $butler_inference_store_nart_type$.getGlobalValue();
                } else {
                    if (NIL != narts_high.naut_p(expression)) {
                        return $butler_inference_store_naut_type$.getGlobalValue();
                    } else {
                        if (expression.isString()) {
                            return $butler_inference_store_string_type$.getGlobalValue();
                        } else {
                            if (expression.isNumber()) {
                                return $butler_inference_store_number_type$.getGlobalValue();
                            } else {
                                if (NIL != assertion_handles.assertion_p(expression)) {
                                    return NIL;
                                } else {
                                    if (NIL != variables.fully_bound_p(expression)) {
                                        return $butler_inference_store_fully_bound_formula_type$.getGlobalValue();
                                    } else {
                                        if (NIL != variables.not_fully_bound_p(expression)) {
                                            return $butler_inference_store_not_fully_bound_formula_type$.getGlobalValue();
                                        } else {
                                            return NIL;
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

    public static SubLObject butler_inference_store_expression_type(final SubLObject expression) {
        if (NIL != constant_p(expression)) {
            return $butler_inference_store_constant_type$.getGlobalValue();
        }
        if (NIL != variables.variable_p(expression)) {
            return $butler_inference_store_variable_type$.getGlobalValue();
        }
        if (NIL != nart_handles.nart_p(expression)) {
            return $butler_inference_store_nart_type$.getGlobalValue();
        }
        if (NIL != narts_high.naut_p(expression)) {
            return $butler_inference_store_naut_type$.getGlobalValue();
        }
        if (expression.isString()) {
            return $butler_inference_store_string_type$.getGlobalValue();
        }
        if (expression.isNumber()) {
            return $butler_inference_store_number_type$.getGlobalValue();
        }
        if (NIL != assertion_handles.assertion_p(expression)) {
            return NIL;
        }
        if (NIL != variables.fully_bound_p(expression)) {
            return $butler_inference_store_fully_bound_formula_type$.getGlobalValue();
        }
        if (NIL != variables.not_fully_bound_p(expression)) {
            return $butler_inference_store_not_fully_bound_formula_type$.getGlobalValue();
        }
        return NIL;
    }

    public static final SubLObject butler_inference_store_status_id_alt(SubLObject status) {
        {
            SubLObject provability_status = (NIL != inference_datastructures_enumerated_types.provability_status_p(status)) ? ((SubLObject) (status)) : inference_datastructures_enumerated_types.provability_status_from_problem_status(status);
            SubLObject pcase_var = provability_status;
            if (pcase_var.eql($GOOD)) {
                return $butler_inference_store_good_status$.getGlobalValue();
            } else {
                if (pcase_var.eql($NEUTRAL)) {
                    return $butler_inference_store_neutral_status$.getGlobalValue();
                } else {
                    if (pcase_var.eql($NO_GOOD)) {
                        return $butler_inference_store_no_good_status$.getGlobalValue();
                    } else {
                        return Errors.warn($str_alt95$Unknown_status__s, status);
                    }
                }
            }
        }
    }

    public static SubLObject butler_inference_store_status_id(final SubLObject status) {
        final SubLObject pcase_var;
        final SubLObject provability_status = pcase_var = (NIL != inference_datastructures_enumerated_types.provability_status_p(status)) ? status : inference_datastructures_enumerated_types.provability_status_from_problem_status(status);
        if (pcase_var.eql($GOOD)) {
            return $butler_inference_store_good_status$.getGlobalValue();
        }
        if (pcase_var.eql($NEUTRAL)) {
            return $butler_inference_store_neutral_status$.getGlobalValue();
        }
        if (pcase_var.eql($NO_GOOD)) {
            return $butler_inference_store_no_good_status$.getGlobalValue();
        }
        return Errors.warn($str95$Unknown_status__s, status);
    }

    public static final SubLObject butler_inference_store_id_status_alt(SubLObject id) {
        if (id.eql($butler_inference_store_good_status$.getGlobalValue())) {
            return $GOOD;
        } else {
            if (id.eql($butler_inference_store_neutral_status$.getGlobalValue())) {
                return $NEUTRAL;
            } else {
                if (id.eql($butler_inference_store_no_good_status$.getGlobalValue())) {
                    return $NO_GOOD;
                } else {
                    return NIL;
                }
            }
        }
    }

    public static SubLObject butler_inference_store_id_status(final SubLObject id) {
        if (id.eql($butler_inference_store_good_status$.getGlobalValue())) {
            return $GOOD;
        }
        if (id.eql($butler_inference_store_neutral_status$.getGlobalValue())) {
            return $NEUTRAL;
        }
        if (id.eql($butler_inference_store_no_good_status$.getGlobalValue())) {
            return $NO_GOOD;
        }
        return NIL;
    }

    public static SubLObject declare_butler_inference_store_file() {
        declareFunction("butler_inference_store_access_path", "BUTLER-INFERENCE-STORE-ACCESS-PATH", 0, 0, false);
        declareFunction("reset_bulter_inference_store_access_path", "RESET-BULTER-INFERENCE-STORE-ACCESS-PATH", 0, 0, false);
        declareFunction("butler_inference_store_execute_sql_query", "BUTLER-INFERENCE-STORE-EXECUTE-SQL-QUERY", 1, 1, false);
        declareFunction("butler_inference_store_execute_sql_update", "BUTLER-INFERENCE-STORE-EXECUTE-SQL-UPDATE", 1, 1, false);
        declareFunction("butler_write_problem_store_to_inference_store", "BUTLER-WRITE-PROBLEM-STORE-TO-INFERENCE-STORE", 1, 0, false);
        declareFunction("butler_write_all_problem_stores_to_inference_store", "BUTLER-WRITE-ALL-PROBLEM-STORES-TO-INFERENCE-STORE", 0, 1, false);
        declareFunction("butler_write_random_problem_stores_to_inference_store", "BUTLER-WRITE-RANDOM-PROBLEM-STORES-TO-INFERENCE-STORE", 0, 2, false);
        declareFunction("butler_write_problem_store_to_inference_store_int", "BUTLER-WRITE-PROBLEM-STORE-TO-INFERENCE-STORE-INT", 2, 0, false);
        declareFunction("butler_inference_store_insert_problem_store", "BUTLER-INFERENCE-STORE-INSERT-PROBLEM-STORE", 2, 0, false);
        declareFunction("butler_inference_store_insert_problem", "BUTLER-INFERENCE-STORE-INSERT-PROBLEM", 3, 0, false);
        declareFunction("butler_inference_store_insert_problem_query_sentence", "BUTLER-INFERENCE-STORE-INSERT-PROBLEM-QUERY-SENTENCE", 2, 0, false);
        declareFunction("butler_inference_store_insert_problem_query_mt", "BUTLER-INFERENCE-STORE-INSERT-PROBLEM-QUERY-MT", 2, 0, false);
        declareFunction("butler_inference_store_insert_expression", "BUTLER-INFERENCE-STORE-INSERT-EXPRESSION", 3, 0, false);
        declareFunction("butler_inference_store_insert_subexpression", "BUTLER-INFERENCE-STORE-INSERT-SUBEXPRESSION", 5, 0, false);
        declareFunction("butler_inference_store_insert_expression_int", "BUTLER-INFERENCE-STORE-INSERT-EXPRESSION-INT", 3, 0, false);
        declareFunction("butler_inference_store_problem_store_properties", "BUTLER-INFERENCE-STORE-PROBLEM-STORE-PROPERTIES", 1, 0, false);
        declareFunction("butler_inference_store_problem_properties", "BUTLER-INFERENCE-STORE-PROBLEM-PROPERTIES", 2, 0, false);
        declareFunction("butler_inference_store_problem_query_problem_store_count", "BUTLER-INFERENCE-STORE-PROBLEM-QUERY-PROBLEM-STORE-COUNT", 1, 1, false);
        declareFunction("butler_inference_store_most_interesting_problem_queries", "BUTLER-INFERENCE-STORE-MOST-INTERESTING-PROBLEM-QUERIES", 0, 3, false);
        declareFunction("butler_inference_store_problem_query_related_problem_queries", "BUTLER-INFERENCE-STORE-PROBLEM-QUERY-RELATED-PROBLEM-QUERIES", 1, 1, false);
        declareFunction("butler_inference_store_problem_queries_with_term_in_sentence_arg", "BUTLER-INFERENCE-STORE-PROBLEM-QUERIES-WITH-TERM-IN-SENTENCE-ARG", 2, 1, false);
        declareFunction("butler_inference_store_problem_queries_with_status", "BUTLER-INFERENCE-STORE-PROBLEM-QUERIES-WITH-STATUS", 1, 1, false);
        declareMacro("do_butler_inference_store_most_interesting_problem_queries", "DO-BUTLER-INFERENCE-STORE-MOST-INTERESTING-PROBLEM-QUERIES");
        declareMacro("do_butler_inference_store_problem_queries_with_status", "DO-BUTLER-INFERENCE-STORE-PROBLEM-QUERIES-WITH-STATUS");
        declareFunction("butler_inference_store_unknown_expression_type_warning", "BUTLER-INFERENCE-STORE-UNKNOWN-EXPRESSION-TYPE-WARNING", 1, 0, false);
        declareFunction("butler_inference_store_query_for_single_value", "BUTLER-INFERENCE-STORE-QUERY-FOR-SINGLE-VALUE", 1, 1, false);
        declareFunction("butler_inference_store_query_for_single_row", "BUTLER-INFERENCE-STORE-QUERY-FOR-SINGLE-ROW", 1, 1, false);
        declareFunction("universal_time_to_sql_timestamp", "UNIVERSAL-TIME-TO-SQL-TIMESTAMP", 1, 0, false);
        declareFunction("sql_time_stamp_to_universal_time", "SQL-TIME-STAMP-TO-UNIVERSAL-TIME", 1, 0, false);
        declareFunction("new_butler_inference_store_query_iterator", "NEW-BUTLER-INFERENCE-STORE-QUERY-ITERATOR", 2, 0, false);
        declareFunction("butler_inference_store_iterator_state", "BUTLER-INFERENCE-STORE-ITERATOR-STATE", 2, 0, false);
        declareFunction("butler_inference_store_iterator_done", "BUTLER-INFERENCE-STORE-ITERATOR-DONE", 1, 0, false);
        declareFunction("butler_inference_store_iterator_next", "BUTLER-INFERENCE-STORE-ITERATOR-NEXT", 1, 0, false);
        declareFunction("butler_inference_store_iterator_decoding_funcall", "BUTLER-INFERENCE-STORE-ITERATOR-DECODING-FUNCALL", 2, 0, false);
        declareFunction("butler_inference_store_iterator_finalize", "BUTLER-INFERENCE-STORE-ITERATOR-FINALIZE", 1, 0, false);
        declareFunction("butler_inference_store_simple_type_p", "BUTLER-INFERENCE-STORE-SIMPLE-TYPE-P", 1, 0, false);
        declareFunction("butler_inference_store_complex_type_p", "BUTLER-INFERENCE-STORE-COMPLEX-TYPE-P", 1, 0, false);
        declareFunction("butler_inference_store_expression_type", "BUTLER-INFERENCE-STORE-EXPRESSION-TYPE", 1, 0, false);
        declareFunction("butler_inference_store_status_id", "BUTLER-INFERENCE-STORE-STATUS-ID", 1, 0, false);
        declareFunction("butler_inference_store_id_status", "BUTLER-INFERENCE-STORE-ID-STATUS", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_butler_inference_store_file() {
        deflexical("*BUTLER-INFERENCE-STORE-KS*", $$ButlerInferenceStore_KS);
        deflexical("*BUTLER-INFERENCE-STORE-ACCESS-PATH*", NIL);
        deflexical("*BUTLER-INFERENCE-STORE-CONSTANT-TYPE*", ONE_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-VARIABLE-TYPE*", TWO_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-NART-TYPE*", THREE_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-NAUT-TYPE*", FOUR_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-FULLY-BOUND-FORMULA-TYPE*", FIVE_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-NOT-FULLY-BOUND-FORMULA-TYPE*", SIX_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-STRING-TYPE*", SEVEN_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-NUMBER-TYPE*", EIGHT_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-GOOD-STATUS*", ONE_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-NEUTRAL-STATUS*", TWO_INTEGER);
        deflexical("*BUTLER-INFERENCE-STORE-NO-GOOD-STATUS*", THREE_INTEGER);
        return NIL;
    }

    public static SubLObject setup_butler_inference_store_file() {
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_butler_inference_store_file();
    }

    @Override
    public void initializeVariables() {
        init_butler_inference_store_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_butler_inference_store_file();
    }

    static {
    }
}

/**
 * Total time: 1265 ms synthetic
 */
