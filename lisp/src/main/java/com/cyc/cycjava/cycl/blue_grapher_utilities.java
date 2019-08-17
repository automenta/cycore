/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.cb_utilities.cb_form;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.el_utilities.binary_lit_p;
import static com.cyc.cycjava.cycl.el_utilities.el_extensional_set_elements;
import static com.cyc.cycjava.cycl.el_utilities.literal_arg1;
import static com.cyc.cycjava.cycl.el_utilities.literal_arg2;
import static com.cyc.cycjava.cycl.el_utilities.literal_args;
import static com.cyc.cycjava.cycl.el_utilities.unary_lit_p;
import static com.cyc.cycjava.cycl.genl_predicates.all_spec_preds;
import static com.cyc.cycjava.cycl.html_utilities.html_align;
import static com.cyc.cycjava.cycl.html_utilities.html_br;
import static com.cyc.cycjava.cycl.html_utilities.html_char;
import static com.cyc.cycjava.cycl.html_utilities.html_glyph;
import static com.cyc.cycjava.cycl.html_utilities.html_markup;
import static com.cyc.cycjava.cycl.html_utilities.html_princ;
import static com.cyc.cycjava.cycl.html_utilities.html_source_readability_terpri;
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
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.cycjava.cycl.utilities_macros.register_api_predefined_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_greater;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_quotation;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.make_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.set_nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.apply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryKey;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryValue;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.gethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorNextEntry;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.make_hash_table;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.releaseEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.sethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.make_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.release_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.seize_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.intern;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove_duplicates;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.fifth;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.fourth;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.getf;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.intersection;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.set_difference;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.third;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import java.util.Iterator;
import java.util.Map;

import org.armedbear.lisp.Lisp;

import com.cyc.cycjava.cycl.inference.browser.cb_query_browser;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_query;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store;
import com.cyc.cycjava.cycl.inference.modules.removal.removal_modules_rtv;
import com.cyc.cycjava.cycl.sbhl.sbhl_module_utilities;
import com.cyc.cycjava.cycl.sbhl.sbhl_module_vars;
import com.cyc.cycjava.cycl.sbhl.sbhl_search_methods;
import com.cyc.cycjava.cycl.sbhl.sbhl_search_what_mts;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
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
 * module:      BLUE-GRAPHER-UTILITIES
 * source file: /cyc/top/cycl/blue-grapher-utilities.lisp
 * created:     2019/07/03 17:38:09
 */
public final class blue_grapher_utilities extends SubLTranslatedFile implements V12 {
    public static final SubLObject bff_rtv_irrelevant_terms(SubLObject terms, SubLObject mt) {
        {
            SubLObject result = NIL;
            SubLObject cdolist_list_var = terms;
            SubLObject v_term = NIL;
            for (v_term = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , v_term = cdolist_list_var.first()) {
                if (NIL != isa.quoted_isa_in_any_mtP(v_term, $$LDSCSharedOntologyConstant)) {
                    result = cons(v_term, result);
                }
            }
            return result;
        }
    }

    public static final SubLObject bff_rkf_irrelevant_terms(SubLObject terms, SubLObject mt) {
        return rkf_relevance_utilities.rkf_filter_irrelevant_terms(terms, mt);
    }

    static private final SubLString $str_alt159$ = makeString("");

    public static final SubLFile me = new blue_grapher_utilities();

 public static final String myName = "com.cyc.cycjava.cycl.blue_grapher_utilities";


    // deflexical
    /**
     * The default relation to show as an edge label when a more specific relation
     * can't be found.
     */
    @LispMethod(comment = "The default relation to show as an edge label when a more specific relation\r\ncan\'t be found.\ndeflexical\nThe default relation to show as an edge label when a more specific relation\ncan\'t be found.")
    private static final SubLSymbol $inference_answers_default_undetermined_relation$ = makeSymbol("*INFERENCE-ANSWERS-DEFAULT-UNDETERMINED-RELATION*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $blue_event_dispatcher_lock$ = makeSymbol("*BLUE-EVENT-DISPATCHER-LOCK*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $blue_message_mailbox_suite$ = makeSymbol("*BLUE-MESSAGE-MAILBOX-SUITE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $blue_event_to_blue_event_listener$ = makeSymbol("*BLUE-EVENT-TO-BLUE-EVENT-LISTENER*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_blue_graph$ = makeSymbol("*DTP-BLUE-GRAPH*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    public static final SubLSymbol $blue_filter_fns$ = makeSymbol("*BLUE-FILTER-FNS*");

    static private final SubLList $list1 = list(makeSymbol("NAME"), makeSymbol("ARGLIST"), makeSymbol("DEFLIST"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol REGISTER_API_PREDEFINED_FUNCTION = makeSymbol("REGISTER-API-PREDEFINED-FUNCTION");

    public static final SubLSymbol $default_blue_filter_fn_cost$ = makeSymbol("*DEFAULT-BLUE-FILTER-FN-COST*");

    public static final SubLSymbol $default_blue_filter_fn_strength$ = makeSymbol("*DEFAULT-BLUE-FILTER-FN-STRENGTH*");

    public static final SubLSymbol $default_blue_filter_fn_type$ = makeSymbol("*DEFAULT-BLUE-FILTER-FN-TYPE*");

    static private final SubLString $str13$APPLY_BLUE_FILTER_FN___A_is_not_a = makeString("APPLY-BLUE-FILTER-FN: ~A is not a blue-filter-fn-p");

    static private final SubLSymbol $sym14$_ = makeSymbol("<");

    private static final SubLSymbol GET_BLUE_FILTER_FN_STRENGTH = makeSymbol("GET-BLUE-FILTER-FN-STRENGTH");

    private static final SubLSymbol MAKE_KEYWORD = makeSymbol("MAKE-KEYWORD");

    private static final SubLSymbol BFF_ARBITRARY_UNIONS = makeSymbol("BFF-ARBITRARY-UNIONS");

    static private final SubLList $list18 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), TEN_INTEGER);



    private static final SubLSymbol BFF_CYC_KB_SUBSET_COLLECTIONS = makeSymbol("BFF-CYC-KB-SUBSET-COLLECTIONS");



    private static final SubLSymbol BFF_MOST_GENERAL_5 = makeSymbol("BFF-MOST-GENERAL-5");

    private static final SubLList $list23 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), makeInteger(500), $TYPE, makeKeyword("POST-MINIMIZATION"));

    private static final SubLSymbol $sym24$GENERALITY_ESTIMATE_ = makeSymbol("GENERALITY-ESTIMATE>");

    private static final SubLSymbol BFF_MOST_GENERAL_10 = makeSymbol("BFF-MOST-GENERAL-10");

    static private final SubLList $list26 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), makeInteger(1000), $TYPE, makeKeyword("POST-MINIMIZATION"));

    private static final SubLSymbol BFF_MOST_GENERAL_20 = makeSymbol("BFF-MOST-GENERAL-20");

    private static final SubLList $list28 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), makeInteger(2000), $TYPE, makeKeyword("POST-MINIMIZATION"));

    public static final SubLSymbol $blue_builder_fns$ = makeSymbol("*BLUE-BUILDER-FNS*");

    public static final SubLSymbol $blue_builder_fn_to_deflist_hash$ = makeSymbol("*BLUE-BUILDER-FN-TO-DEFLIST-HASH*");

    static private final SubLList $list31 = list(makeSymbol("*BLUE-BUILDER-FNS*"), T);

    private static final SubLString $str32$BLUE_BUILDER___A_is_not_a_blue_bu = makeString("BLUE-BUILDER: ~A is not a blue-builder-fn-p");

    private static final SubLSymbol $sym33$STRING_ = makeSymbol("STRING<");

    private static final SubLSymbol BBF_FORWARD_TRUE = makeSymbol("BBF-FORWARD-TRUE");

    private static final SubLList $list36 = list(makeKeyword("HELP-STRING"), makeString("Show forward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    private static final SubLSymbol BBF_BACKWARD_TRUE = makeSymbol("BBF-BACKWARD-TRUE");

    private static final SubLList $list44 = list(makeKeyword("HELP-STRING"), makeString("Show backward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    private static final SubLSymbol BBF_MIN_FORWARD_TRUE = makeSymbol("BBF-MIN-FORWARD-TRUE");

    private static final SubLList $list46 = list(makeKeyword("HELP-STRING"), makeString("Show minimum forward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    private static final SubLSymbol BBF_MIN_BACKWARD_TRUE = makeSymbol("BBF-MIN-BACKWARD-TRUE");

    private static final SubLList $list48 = list(makeKeyword("HELP-STRING"), makeString("Show minimum backward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    private static final SubLSymbol BBF_MIN_CEILINGS_FORWARD_TRUE = makeSymbol("BBF-MIN-CEILINGS-FORWARD-TRUE");

    private static final SubLList $list50 = list(makeKeyword("HELP-STRING"), makeString("Show the minimal ceilings of all forward edges from FORTS (nodes) through PREDS (edges) in MT and iterate DEPTH times.  (Forts failing FILTER-FNS are not shown.) (EXPERIMENTAL)"));

    private static final SubLObject $$True_JustificationTruth = reader_make_constant_shell("True-JustificationTruth");

    private static final SubLSymbol BBF_MIN_FORWARD_AND_BACKWARD_TRUE = makeSymbol("BBF-MIN-FORWARD-AND-BACKWARD-TRUE");

    static private final SubLList $list53 = list(makeKeyword("HELP-STRING"), makeString("Show minimum forward and backward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)  (Note that this is *expensive* for higher depths) (EXPERIMENTAL)"));

    private static final SubLSymbol BBF_SCRIPT = makeSymbol("BBF-SCRIPT");

    private static final SubLList $list57 = list(makeKeyword("HELP-STRING"), makeString("Create a graph representation of a script (a spec of #$Situation) that shows the temporal ordering of scenes and the actors and roles involved in each scene.  FORTS must be a singleton containing the script term."));

    private static final SubLObject $const58$CurrentWorldDataCollectorMt_NonHo = reader_make_constant_shell("CurrentWorldDataCollectorMt-NonHomocentric");



    static private final SubLList $list60 = list(TWO_INTEGER, THREE_INTEGER);

    private static final SubLSymbol $sym61$TERNARY_PREDICATE_ = makeSymbol("TERNARY-PREDICATE?");

    private static final SubLObject $const62$QuasiTemporalSubSituationTypesPre = reader_make_constant_shell("QuasiTemporalSubSituationTypesPredicate");



    private static final SubLList $list68 = list(TWO_INTEGER, THREE_INTEGER, FOUR_INTEGER);

    private static final SubLObject $const69$SituationTypeRelation_BasicActorT = reader_make_constant_shell("SituationTypeRelation-BasicActorTypesAndRolesForSubSitTypes");

    private static final SubLObject $const70$individualPlaysRoleInSubSituation = reader_make_constant_shell("individualPlaysRoleInSubSituationType");



    private static final SubLObject $const72$SituationTypeRelation_SitTypeToSu = reader_make_constant_shell("SituationTypeRelation-SitTypeToSubSitTypeBasic");

    private static final SubLList $list73 = list(TWO_INTEGER, THREE_INTEGER, FOUR_INTEGER, FIVE_INTEGER, SIX_INTEGER);

    private static final SubLObject $const74$GraphicallyEditableRoleMappingPre = reader_make_constant_shell("GraphicallyEditableRoleMappingPredicate");



    private static final SubLSymbol BBF_INFERENCE_ANSWERS = makeSymbol("BBF-INFERENCE-ANSWERS");

    private static final SubLList $list80 = list(makeKeyword("HELP-STRING"), makeString("Graph the results of an inference by graphing the GAFs the result when filling in the bindings back into the query."));

    private static final SubLSymbol $INFERENCE_STORE_ID = makeKeyword("INFERENCE-STORE-ID");





    private static final SubLObject $$CycLSentence_Assertible = reader_make_constant_shell("CycLSentence-Assertible");



    private static final SubLString $$$Query = makeString("Query");

    private static final SubLString $$$Edge_Templates = makeString("Edge Templates");

    private static final SubLString $str95$_____ = makeString(" --- ");

    private static final SubLString $str96$______ = makeString(" ---> ");

    private static final SubLSymbol BBF_INFERENCE = makeSymbol("BBF-INFERENCE");

    private static final SubLList $list98 = list(makeKeyword("HELP-STRING"), makeString("Graph an inference query formula by taking the neg-lits and pos-lits, converting the HL vars to EL, then treating them as edges."));

    private static final SubLString $str101$Inference_not_found_ = makeString("Inference not found.");

    private static final SubLList $list103 = list(makeSymbol("MT"), makeSymbol("ASENT"));

    private static final SubLSymbol BBF_RULE = makeSymbol("BBF-RULE");

    static private final SubLList $list106 = list(makeKeyword("HELP-STRING"), makeString("Graph a rule by taking the neg-lits and pos-lits, converting the HL vars to EL, then treating them as edges."));

    private static final SubLList $list108 = cons(makeSymbol("EL-VAR"), makeSymbol("HL-VAR"));

    private static final SubLSymbol INTERN_EL_VAR = makeSymbol("INTERN-EL-VAR");

    private static final SubLSymbol $BLUE_GRAPHER_BASE_EVENT = makeKeyword("BLUE-GRAPHER-BASE-EVENT");

    private static final SubLSymbol $CYC_APPLICATION_EVENT = makeKeyword("CYC-APPLICATION-EVENT");

    private static final SubLString $str121$BLUE_EVENT_DISPATCHER_Lock = makeString("BLUE-EVENT-DISPATCHER Lock");

    private static final SubLSymbol $blue_event_dispatcher_listeners$ = makeSymbol("*BLUE-EVENT-DISPATCHER-LISTENERS*");

    private static final SubLSymbol BLUE_FETCH_EVENT = makeSymbol("BLUE-FETCH-EVENT");

    private static final SubLSymbol BLUE_MAP_EVENT_TO_BLUE_EVENT = makeSymbol("BLUE-MAP-EVENT-TO-BLUE-EVENT");

    private static final SubLSymbol BLUE_GRAPH = makeSymbol("BLUE-GRAPH");

    private static final SubLSymbol BLUE_GRAPH_P = makeSymbol("BLUE-GRAPH-P");

    private static final SubLList $list127 = list(makeSymbol("ISG"), makeSymbol("NODE-LABEL-MAP"), makeSymbol("NODES"), makeSymbol("EDGE-LABEL-MAP"), makeSymbol("EDGES"), makeSymbol("NODE-DEFINITIONS"), makeSymbol("EDGE-DEFINITIONS"), makeSymbol("OPTIONS"));

    static private final SubLList $list128 = list(makeKeyword("ISG"), makeKeyword("NODE-LABEL-MAP"), makeKeyword("NODES"), makeKeyword("EDGE-LABEL-MAP"), makeKeyword("EDGES"), makeKeyword("NODE-DEFINITIONS"), makeKeyword("EDGE-DEFINITIONS"), makeKeyword("OPTIONS"));

    static private final SubLList $list129 = list(makeSymbol("BLUE-GRAPH-ISG"), makeSymbol("BLUE-GRAPH-NODE-LABEL-MAP"), makeSymbol("BLUE-GRAPH-NODES"), makeSymbol("BLUE-GRAPH-EDGE-LABEL-MAP"), makeSymbol("BLUE-GRAPH-EDGES"), makeSymbol("BLUE-GRAPH-NODE-DEFINITIONS"), makeSymbol("BLUE-GRAPH-EDGE-DEFINITIONS"), makeSymbol("BLUE-GRAPH-OPTIONS"));

    static private final SubLList $list130 = list(makeSymbol("_CSETF-BLUE-GRAPH-ISG"), makeSymbol("_CSETF-BLUE-GRAPH-NODE-LABEL-MAP"), makeSymbol("_CSETF-BLUE-GRAPH-NODES"), makeSymbol("_CSETF-BLUE-GRAPH-EDGE-LABEL-MAP"), makeSymbol("_CSETF-BLUE-GRAPH-EDGES"), makeSymbol("_CSETF-BLUE-GRAPH-NODE-DEFINITIONS"), makeSymbol("_CSETF-BLUE-GRAPH-EDGE-DEFINITIONS"), makeSymbol("_CSETF-BLUE-GRAPH-OPTIONS"));

    private static final SubLSymbol BLUE_GRAPH_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("BLUE-GRAPH-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list133 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("BLUE-GRAPH-P"));

    private static final SubLSymbol BLUE_GRAPH_ISG = makeSymbol("BLUE-GRAPH-ISG");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_ISG = makeSymbol("_CSETF-BLUE-GRAPH-ISG");

    private static final SubLSymbol BLUE_GRAPH_NODE_LABEL_MAP = makeSymbol("BLUE-GRAPH-NODE-LABEL-MAP");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_NODE_LABEL_MAP = makeSymbol("_CSETF-BLUE-GRAPH-NODE-LABEL-MAP");

    private static final SubLSymbol BLUE_GRAPH_NODES = makeSymbol("BLUE-GRAPH-NODES");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_NODES = makeSymbol("_CSETF-BLUE-GRAPH-NODES");

    private static final SubLSymbol BLUE_GRAPH_EDGE_LABEL_MAP = makeSymbol("BLUE-GRAPH-EDGE-LABEL-MAP");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_EDGE_LABEL_MAP = makeSymbol("_CSETF-BLUE-GRAPH-EDGE-LABEL-MAP");

    private static final SubLSymbol BLUE_GRAPH_EDGES = makeSymbol("BLUE-GRAPH-EDGES");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_EDGES = makeSymbol("_CSETF-BLUE-GRAPH-EDGES");

    private static final SubLSymbol BLUE_GRAPH_NODE_DEFINITIONS = makeSymbol("BLUE-GRAPH-NODE-DEFINITIONS");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_NODE_DEFINITIONS = makeSymbol("_CSETF-BLUE-GRAPH-NODE-DEFINITIONS");

    private static final SubLSymbol BLUE_GRAPH_EDGE_DEFINITIONS = makeSymbol("BLUE-GRAPH-EDGE-DEFINITIONS");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_EDGE_DEFINITIONS = makeSymbol("_CSETF-BLUE-GRAPH-EDGE-DEFINITIONS");

    private static final SubLSymbol BLUE_GRAPH_OPTIONS = makeSymbol("BLUE-GRAPH-OPTIONS");

    public static final SubLSymbol _CSETF_BLUE_GRAPH_OPTIONS = makeSymbol("_CSETF-BLUE-GRAPH-OPTIONS");

    private static final SubLSymbol $NODE_LABEL_MAP = makeKeyword("NODE-LABEL-MAP");

    private static final SubLSymbol $EDGE_LABEL_MAP = makeKeyword("EDGE-LABEL-MAP");

    static private final SubLString $str156$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_BLUE_GRAPH = makeSymbol("MAKE-BLUE-GRAPH");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_BLUE_GRAPH_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-BLUE-GRAPH-METHOD");

    private static final SubLString $str164$ = makeString("");

    private static final SubLSymbol BBF_RTV_FROM_INFERENCE = makeSymbol("BBF-RTV-FROM-INFERENCE");

    private static final SubLList $list175 = list(makeKeyword("HELP-STRING"), makeString(""));

    private static final SubLSymbol $PROBLEM_STORE_ID = makeKeyword("PROBLEM-STORE-ID");

    private static final SubLSymbol RTV_CLAUSE_P = makeSymbol("RTV-CLAUSE-P");

    private static final SubLSymbol BBF_RTV_UNBOUND = makeSymbol("BBF-RTV-UNBOUND");

    private static final SubLSymbol BBF_RTV_FROM_INFERENCE_NEW = makeSymbol("BBF-RTV-FROM-INFERENCE-NEW");

    public static final SubLObject define_blue_filter_fn_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject name = NIL;
            SubLObject arglist = NIL;
            SubLObject deflist = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt1);
            name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt1);
            arglist = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt1);
            deflist = current.first();
            current = current.rest();
            {
                SubLObject body = current;
                return list(PROGN, list(SETHASH, list(QUOTE, name), $blue_filter_fns$, list(QUOTE, deflist)), listS(DEFINE_PROTECTED, name, arglist, append(body, NIL)), list(REGISTER_API_PREDEFINED_FUNCTION, list(QUOTE, name)));
            }
        }
    }

    public static SubLObject define_blue_filter_fn(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject name = NIL;
        SubLObject arglist = NIL;
        SubLObject deflist = NIL;
        destructuring_bind_must_consp(current, datum, $list1);
        name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list1);
        arglist = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list1);
        deflist = current.first();
        final SubLObject body;
        current = body = current.rest();
        return list(PROGN, list(SETHASH, list(QUOTE, name), $blue_filter_fns$, list(QUOTE, deflist)), listS(DEFINE_PROTECTED, name, arglist, append(body, NIL)), list(REGISTER_API_PREDEFINED_FUNCTION, list(QUOTE, name)));
    }

    public static final SubLObject blue_filter_fns_alt() {
        return hash_table_utilities.hash_table_keys($blue_filter_fns$.getGlobalValue());
    }

    public static SubLObject blue_filter_fns() {
        return hash_table_utilities.hash_table_keys($blue_filter_fns$.getGlobalValue());
    }

    /**
     * wrapper around a gethash
     */
    @LispMethod(comment = "wrapper around a gethash")
    public static final SubLObject get_blue_filter_fn_data_alt(SubLObject blue_filter_fn) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject blue_filter_fn_data = gethash(blue_filter_fn, $blue_filter_fns$.getGlobalValue(), UNPROVIDED);
                SubLObject foundP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                return values(blue_filter_fn_data, foundP);
            }
        }
    }

    /**
     * wrapper around a gethash
     */
    @LispMethod(comment = "wrapper around a gethash")
    public static SubLObject get_blue_filter_fn_data(final SubLObject blue_filter_fn) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject blue_filter_fn_data = gethash(blue_filter_fn, $blue_filter_fns$.getGlobalValue(), UNPROVIDED);
        final SubLObject foundP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        return values(blue_filter_fn_data, foundP);
    }

    public static final SubLObject blue_filter_fn_p_alt(SubLObject blue_filter_fn) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject blue_filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
                SubLObject foundP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL != foundP) {
                    return T;
                } else {
                    return NIL;
                }
            }
        }
    }

    public static SubLObject blue_filter_fn_p(final SubLObject blue_filter_fn) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject blue_filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
        final SubLObject foundP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != foundP) {
            return T;
        }
        return NIL;
    }

    public static final SubLObject get_blue_filter_fn_cost_alt(SubLObject blue_filter_fn) {
        {
            SubLObject filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
            SubLObject cost = getf(filter_fn_data, $COST, UNPROVIDED);
            return cost.isInteger() ? ((SubLObject) (cost)) : $default_blue_filter_fn_cost$.getGlobalValue();
        }
    }

    public static SubLObject get_blue_filter_fn_cost(final SubLObject blue_filter_fn) {
        final SubLObject filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
        final SubLObject cost = getf(filter_fn_data, $COST, UNPROVIDED);
        return cost.isInteger() ? cost : $default_blue_filter_fn_cost$.getGlobalValue();
    }

    public static final SubLObject get_blue_filter_fn_strength_alt(SubLObject blue_filter_fn) {
        {
            SubLObject filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
            SubLObject strength = getf(filter_fn_data, $STRENGTH, UNPROVIDED);
            return strength.isInteger() ? ((SubLObject) (strength)) : $default_blue_filter_fn_strength$.getGlobalValue();
        }
    }

    public static SubLObject get_blue_filter_fn_strength(final SubLObject blue_filter_fn) {
        final SubLObject filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
        final SubLObject strength = getf(filter_fn_data, $STRENGTH, UNPROVIDED);
        return strength.isInteger() ? strength : $default_blue_filter_fn_strength$.getGlobalValue();
    }

    public static final SubLObject get_blue_filter_fn_type_alt(SubLObject blue_filter_fn) {
        {
            SubLObject filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
            SubLObject type = getf(filter_fn_data, $TYPE, UNPROVIDED);
            return type.isKeyword() ? ((SubLObject) (type)) : $default_blue_filter_fn_type$.getGlobalValue();
        }
    }

    public static SubLObject get_blue_filter_fn_type(final SubLObject blue_filter_fn) {
        final SubLObject filter_fn_data = get_blue_filter_fn_data(blue_filter_fn);
        final SubLObject type = getf(filter_fn_data, $TYPE, UNPROVIDED);
        return type.isKeyword() ? type : $default_blue_filter_fn_type$.getGlobalValue();
    }

    public static final SubLObject apply_blue_filter_fn_alt(SubLObject blue_filter_fn, SubLObject list, SubLObject mt, SubLObject type) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (NIL == blue_filter_fn_p(blue_filter_fn)) {
            Errors.warn($str_alt13$APPLY_BLUE_FILTER_FN___A_is_not_a, blue_filter_fn);
        } else {
            if (get_blue_filter_fn_type(blue_filter_fn) == type) {
                return funcall(blue_filter_fn, list, mt);
            } else {
                return list;
            }
        }
        return NIL;
    }

    public static SubLObject apply_blue_filter_fn(final SubLObject blue_filter_fn, final SubLObject list, final SubLObject mt, SubLObject type) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (NIL == blue_filter_fn_p(blue_filter_fn)) {
            Errors.warn($str13$APPLY_BLUE_FILTER_FN___A_is_not_a, blue_filter_fn);
            return NIL;
        }
        if (get_blue_filter_fn_type(blue_filter_fn).eql(type)) {
            return funcall(blue_filter_fn, list, mt);
        }
        return list;
    }

    public static final SubLObject blue_filter_fns_sorted_alt(SubLObject blue_filter_fns) {
        if (blue_filter_fns == UNPROVIDED) {
            blue_filter_fns = blue_filter_fns();
        }
        return Sort.sort(blue_filter_fns, symbol_function($sym14$_), symbol_function(GET_BLUE_FILTER_FN_STRENGTH));
    }

    public static SubLObject blue_filter_fns_sorted(SubLObject blue_filter_fns) {
        if (blue_filter_fns == UNPROVIDED) {
            blue_filter_fns = blue_filter_fns();
        }
        return Sort.sort(blue_filter_fns, symbol_function($sym14$_), symbol_function(GET_BLUE_FILTER_FN_STRENGTH));
    }

    public static final SubLObject blue_apply_filter_fns_alt(SubLObject term_list, SubLObject blue_filter_fns, SubLObject mt, SubLObject type) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        {
            SubLObject sorted_blue_filter_fns = blue_filter_fns_sorted(blue_filter_fns);
            SubLObject result_term_list = term_list;
            SubLObject cdolist_list_var = sorted_blue_filter_fns;
            SubLObject blue_filter_fn = NIL;
            for (blue_filter_fn = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , blue_filter_fn = cdolist_list_var.first()) {
                result_term_list = apply_blue_filter_fn(blue_filter_fn, result_term_list, mt, type);
            }
            return result_term_list;
        }
    }

    public static SubLObject blue_apply_filter_fns(final SubLObject term_list, final SubLObject blue_filter_fns, final SubLObject mt, SubLObject type) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        final SubLObject sorted_blue_filter_fns = blue_filter_fns_sorted(blue_filter_fns);
        SubLObject result_term_list = term_list;
        SubLObject cdolist_list_var = sorted_blue_filter_fns;
        SubLObject blue_filter_fn = NIL;
        blue_filter_fn = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            result_term_list = apply_blue_filter_fn(blue_filter_fn, result_term_list, mt, type);
            cdolist_list_var = cdolist_list_var.rest();
            blue_filter_fn = cdolist_list_var.first();
        } 
        return result_term_list;
    }

    public static final SubLObject blue_filter_fn_keys_alt(SubLObject blue_filter_fns) {
        if (blue_filter_fns == UNPROVIDED) {
            blue_filter_fns = blue_filter_fns();
        }
        return Mapping.mapcar(symbol_function(MAKE_KEYWORD), blue_filter_fns_sorted(blue_filter_fns));
    }

    public static SubLObject blue_filter_fn_keys(SubLObject blue_filter_fns) {
        if (blue_filter_fns == UNPROVIDED) {
            blue_filter_fns = blue_filter_fns();
        }
        return Mapping.mapcar(symbol_function(MAKE_KEYWORD), blue_filter_fns_sorted(blue_filter_fns));
    }

    public static final SubLObject blue_filter_fn_by_key_alt(SubLObject bbf_key) {
        {
            SubLObject poss_function_spec = intern(string_utilities.string_from_keyword(bbf_key), UNPROVIDED);
            if ((NIL != gethash(poss_function_spec, $blue_filter_fns$.getGlobalValue(), UNPROVIDED)) && poss_function_spec.isFunctionSpec()) {
                return poss_function_spec;
            }
        }
        return NIL;
    }

    public static SubLObject blue_filter_fn_by_key(final SubLObject bbf_key) {
        final SubLObject poss_function_spec = intern(string_utilities.string_from_keyword(bbf_key), UNPROVIDED);
        if ((NIL != gethash(poss_function_spec, $blue_filter_fns$.getGlobalValue(), UNPROVIDED)) && poss_function_spec.isFunctionSpec()) {
            return poss_function_spec;
        }
        return NIL;
    }

    public static final SubLObject bff_arbitrary_unions_alt(SubLObject terms, SubLObject mt) {
        {
            SubLObject result = NIL;
            SubLObject cdolist_list_var = terms;
            SubLObject v_term = NIL;
            for (v_term = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , v_term = cdolist_list_var.first()) {
                if (NIL == isa.isa_in_any_mtP(v_term, $$ArbitraryUnion)) {
                    result = cons(v_term, result);
                }
            }
            return result;
        }
    }

    public static SubLObject bff_arbitrary_unions(final SubLObject terms, final SubLObject mt) {
        SubLObject result = NIL;
        SubLObject cdolist_list_var = terms;
        SubLObject v_term = NIL;
        v_term = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == isa.isa_in_any_mtP(v_term, $$ArbitraryUnion)) {
                result = cons(v_term, result);
            }
            cdolist_list_var = cdolist_list_var.rest();
            v_term = cdolist_list_var.first();
        } 
        return result;
    }

    public static final SubLObject bff_cyc_kb_subset_collections_alt(SubLObject terms, SubLObject mt) {
        {
            SubLObject result = NIL;
            SubLObject cdolist_list_var = terms;
            SubLObject v_term = NIL;
            for (v_term = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , v_term = cdolist_list_var.first()) {
                if (NIL == isa.isa_in_any_mtP(v_term, $$CycKBSubsetCollection)) {
                    result = cons(v_term, result);
                }
            }
            return result;
        }
    }

    public static SubLObject bff_cyc_kb_subset_collections(final SubLObject terms, final SubLObject mt) {
        SubLObject result = NIL;
        SubLObject cdolist_list_var = terms;
        SubLObject v_term = NIL;
        v_term = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == isa.isa_in_any_mtP(v_term, $$CycKBSubsetCollection)) {
                result = cons(v_term, result);
            }
            cdolist_list_var = cdolist_list_var.rest();
            v_term = cdolist_list_var.first();
        } 
        return result;
    }

    public static final SubLObject bff_most_general_5_alt(SubLObject terms, SubLObject mt) {
        return list_utilities.first_n(FIVE_INTEGER, Sort.sort(terms, symbol_function($sym27$GENERALITY_ESTIMATE_), UNPROVIDED));
    }

    public static SubLObject bff_most_general_5(final SubLObject terms, final SubLObject mt) {
        return list_utilities.first_n(FIVE_INTEGER, Sort.sort(terms, symbol_function($sym24$GENERALITY_ESTIMATE_), UNPROVIDED));
    }

    public static final SubLObject bff_most_general_10_alt(SubLObject terms, SubLObject mt) {
        return list_utilities.first_n(TEN_INTEGER, Sort.sort(terms, symbol_function($sym27$GENERALITY_ESTIMATE_), UNPROVIDED));
    }

    public static SubLObject bff_most_general_10(final SubLObject terms, final SubLObject mt) {
        return list_utilities.first_n(TEN_INTEGER, Sort.sort(terms, symbol_function($sym24$GENERALITY_ESTIMATE_), UNPROVIDED));
    }

    public static final SubLObject bff_most_general_20_alt(SubLObject terms, SubLObject mt) {
        return list_utilities.first_n(TWENTY_INTEGER, Sort.sort(terms, symbol_function($sym27$GENERALITY_ESTIMATE_), UNPROVIDED));
    }

    public static SubLObject bff_most_general_20(final SubLObject terms, final SubLObject mt) {
        return list_utilities.first_n(TWENTY_INTEGER, Sort.sort(terms, symbol_function($sym24$GENERALITY_ESTIMATE_), UNPROVIDED));
    }

    public static final SubLObject define_blue_builder_fn_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject name = NIL;
            SubLObject arglist = NIL;
            SubLObject deflist = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt1);
            name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt1);
            arglist = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt1);
            deflist = current.first();
            current = current.rest();
            {
                SubLObject body = current;
                return list(PROGN, listS(SETHASH, list(QUOTE, name), $list_alt34), list(SETHASH, list(QUOTE, name), $blue_builder_fn_to_deflist_hash$, list(QUOTE, deflist)), listS(DEFINE_PROTECTED, name, arglist, append(body, NIL)), list(REGISTER_API_PREDEFINED_FUNCTION, list(QUOTE, name)));
            }
        }
    }

    public static SubLObject define_blue_builder_fn(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject name = NIL;
        SubLObject arglist = NIL;
        SubLObject deflist = NIL;
        destructuring_bind_must_consp(current, datum, $list1);
        name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list1);
        arglist = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list1);
        deflist = current.first();
        final SubLObject body;
        current = body = current.rest();
        return list(PROGN, listS(SETHASH, list(QUOTE, name), $list31), list(SETHASH, list(QUOTE, name), $blue_builder_fn_to_deflist_hash$, list(QUOTE, deflist)), listS(DEFINE_PROTECTED, name, arglist, append(body, NIL)), list(REGISTER_API_PREDEFINED_FUNCTION, list(QUOTE, name)));
    }

    /**
     * wrapper around a gethash
     */
    @LispMethod(comment = "wrapper around a gethash")
    public static final SubLObject get_blue_builder_fn_data_alt(SubLObject blue_builder_fn) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject blue_builder_fn_data = gethash(blue_builder_fn, $blue_builder_fns$.getGlobalValue(), UNPROVIDED);
                SubLObject foundP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                return values(blue_builder_fn_data, foundP);
            }
        }
    }

    /**
     * wrapper around a gethash
     */
    @LispMethod(comment = "wrapper around a gethash")
    public static SubLObject get_blue_builder_fn_data(final SubLObject blue_builder_fn) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject blue_builder_fn_data = gethash(blue_builder_fn, $blue_builder_fns$.getGlobalValue(), UNPROVIDED);
        final SubLObject foundP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        return values(blue_builder_fn_data, foundP);
    }

    public static final SubLObject blue_builder_fn_p_alt(SubLObject blue_builder_fn) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject blue_builder_fn_data = get_blue_builder_fn_data(blue_builder_fn);
                SubLObject foundP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL != foundP) {
                    return T;
                } else {
                    return NIL;
                }
            }
        }
    }

    public static SubLObject blue_builder_fn_p(final SubLObject blue_builder_fn) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject blue_builder_fn_data = get_blue_builder_fn_data(blue_builder_fn);
        final SubLObject foundP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != foundP) {
            return T;
        }
        return NIL;
    }

    public static final SubLObject blue_builder_alt(SubLObject blue_builder_fn, SubLObject key_list) {
        if (NIL != blue_builder_fn_p(blue_builder_fn)) {
            return funcall(blue_builder_fn, key_list);
        } else {
            Errors.warn($str_alt35$BLUE_BUILDER___A_is_not_a_blue_bu, blue_builder_fn);
            return NIL;
        }
    }

    public static SubLObject blue_builder(final SubLObject blue_builder_fn, final SubLObject key_list) {
        if (NIL != blue_builder_fn_p(blue_builder_fn)) {
            return funcall(blue_builder_fn, key_list);
        }
        Errors.warn($str32$BLUE_BUILDER___A_is_not_a_blue_bu, blue_builder_fn);
        return NIL;
    }

    public static final SubLObject blue_builder_fn_keys_alt() {
        return Mapping.mapcar(symbol_function(MAKE_KEYWORD), Sort.sort(hash_table_utilities.hash_table_keys($blue_builder_fns$.getGlobalValue()), $sym36$STRING_, SYMBOL_NAME));
    }

    public static SubLObject blue_builder_fn_keys() {
        return Mapping.mapcar(symbol_function(MAKE_KEYWORD), Sort.sort(hash_table_utilities.hash_table_keys($blue_builder_fns$.getGlobalValue()), $sym33$STRING_, SYMBOL_NAME));
    }

    public static final SubLObject blue_builder_fn_by_key_alt(SubLObject bbf_key) {
        {
            SubLObject poss_function_spec = intern(string_utilities.string_from_keyword(bbf_key), UNPROVIDED);
            if ((NIL != gethash(poss_function_spec, $blue_builder_fns$.getGlobalValue(), UNPROVIDED)) && poss_function_spec.isFunctionSpec()) {
                return poss_function_spec;
            }
        }
        return NIL;
    }

    public static SubLObject blue_builder_fn_by_key(final SubLObject bbf_key) {
        final SubLObject poss_function_spec = intern(string_utilities.string_from_keyword(bbf_key), UNPROVIDED);
        if ((NIL != gethash(poss_function_spec, $blue_builder_fns$.getGlobalValue(), UNPROVIDED)) && poss_function_spec.isFunctionSpec()) {
            return poss_function_spec;
        }
        return NIL;
    }

    public static final SubLObject get_blue_builder_fn_def_value_alt(SubLObject bbf_key, SubLObject def_key) {
        {
            SubLObject bbf = blue_builder_fn_by_key(bbf_key);
            if (NIL != blue_builder_fn_p(bbf)) {
                return getf(gethash(bbf, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), UNPROVIDED), def_key, UNPROVIDED);
            } else {
                return NIL;
            }
        }
    }

    public static SubLObject get_blue_builder_fn_def_value(final SubLObject bbf_key, final SubLObject def_key) {
        final SubLObject bbf = blue_builder_fn_by_key(bbf_key);
        if (NIL != blue_builder_fn_p(bbf)) {
            return getf(gethash(bbf, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), UNPROVIDED), def_key, UNPROVIDED);
        }
        return NIL;
    }

    public static final SubLObject bbf_forward_true_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
                SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
                SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
                SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
                SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                SubLObject cdolist_list_var = v_forts;
                SubLObject fort = NIL;
                for (fort = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fort = cdolist_list_var.first()) {
                    {
                        SubLObject cdolist_list_var_1 = preds;
                        SubLObject pred = NIL;
                        for (pred = cdolist_list_var_1.first(); NIL != cdolist_list_var_1; cdolist_list_var_1 = cdolist_list_var_1.rest() , pred = cdolist_list_var_1.first()) {
                            thread.resetMultipleValues();
                            {
                                SubLObject protoedges = bbf_forward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                                SubLObject protonodes = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                {
                                    SubLObject cdolist_list_var_2 = protonodes;
                                    SubLObject protonode = NIL;
                                    for (protonode = cdolist_list_var_2.first(); NIL != cdolist_list_var_2; cdolist_list_var_2 = cdolist_list_var_2.rest() , protonode = cdolist_list_var_2.first()) {
                                        result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                                    }
                                }
                                {
                                    SubLObject cdolist_list_var_3 = protoedges;
                                    SubLObject protoedge = NIL;
                                    for (protoedge = cdolist_list_var_3.first(); NIL != cdolist_list_var_3; cdolist_list_var_3 = cdolist_list_var_3.rest() , protoedge = cdolist_list_var_3.first()) {
                                        result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                                    }
                                }
                            }
                        }
                    }
                }
                return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
            }
        }
    }

    public static SubLObject bbf_forward_true(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
        final SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
        final SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
        final SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
        SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        SubLObject cdolist_list_var = v_forts;
        SubLObject fort = NIL;
        fort = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$1 = preds;
            SubLObject pred = NIL;
            pred = cdolist_list_var_$1.first();
            while (NIL != cdolist_list_var_$1) {
                thread.resetMultipleValues();
                final SubLObject protoedges = bbf_forward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                final SubLObject protonodes = thread.secondMultipleValue();
                thread.resetMultipleValues();
                SubLObject cdolist_list_var_$2 = protonodes;
                SubLObject protonode = NIL;
                protonode = cdolist_list_var_$2.first();
                while (NIL != cdolist_list_var_$2) {
                    result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                    cdolist_list_var_$2 = cdolist_list_var_$2.rest();
                    protonode = cdolist_list_var_$2.first();
                } 
                SubLObject cdolist_list_var_$3 = protoedges;
                SubLObject protoedge = NIL;
                protoedge = cdolist_list_var_$3.first();
                while (NIL != cdolist_list_var_$3) {
                    result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                    cdolist_list_var_$3 = cdolist_list_var_$3.rest();
                    protoedge = cdolist_list_var_$3.first();
                } 
                cdolist_list_var_$1 = cdolist_list_var_$1.rest();
                pred = cdolist_list_var_$1.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            fort = cdolist_list_var.first();
        } 
        return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
    }

    public static final SubLObject bbf_forward_true_internal_alt(SubLObject pred, SubLObject tail_node, SubLObject mt, SubLObject filter_fns, SubLObject depth, SubLObject done_tail_nodes) {
        if (done_tail_nodes == UNPROVIDED) {
            done_tail_nodes = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject recurseP = NIL;
                SubLObject protoedges = NIL;
                SubLObject head_nodes = NIL;
                SubLObject coextensional_head_nodes = NIL;
                head_nodes = (NIL != kb_accessors.transitive_predicateP(pred)) ? ((SubLObject) (gt_methods.gt_superiors(pred, tail_node, mt))) : kb_mapping_utilities.pred_values_in_mt(tail_node, pred, mt, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                if (NIL != head_nodes) {
                    head_nodes = blue_apply_filter_fns(head_nodes, filter_fns, mt, UNPROVIDED);
                    if (NIL != head_nodes) {
                        coextensional_head_nodes = intersection(head_nodes, NIL != kb_accessors.transitive_predicateP(pred) ? ((SubLObject) (gt_methods.gt_inferiors(pred, tail_node, mt))) : kb_mapping_utilities.pred_values_in_mt(tail_node, pred, mt, TWO_INTEGER, ONE_INTEGER, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                        head_nodes = set_difference(head_nodes, coextensional_head_nodes, UNPROVIDED, UNPROVIDED);
                        coextensional_head_nodes = set_difference(coextensional_head_nodes, list(tail_node), UNPROVIDED, UNPROVIDED);
                    }
                }
                head_nodes = blue_apply_filter_fns(head_nodes, filter_fns, mt, $POST_MINIMIZATION);
                {
                    SubLObject cdolist_list_var = head_nodes;
                    SubLObject head_node = NIL;
                    for (head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head_node = cdolist_list_var.first()) {
                        protoedges = cons(list(tail_node, head_node), protoedges);
                    }
                }
                {
                    SubLObject cdolist_list_var = coextensional_head_nodes;
                    SubLObject coextensional_head_node = NIL;
                    for (coextensional_head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , coextensional_head_node = cdolist_list_var.first()) {
                        protoedges = cons(list(tail_node, coextensional_head_node), protoedges);
                        protoedges = cons(list(coextensional_head_node, tail_node), protoedges);
                        {
                            SubLObject cdolist_list_var_4 = coextensional_head_nodes;
                            SubLObject another_coextensional_head_node = NIL;
                            for (another_coextensional_head_node = cdolist_list_var_4.first(); NIL != cdolist_list_var_4; cdolist_list_var_4 = cdolist_list_var_4.rest() , another_coextensional_head_node = cdolist_list_var_4.first()) {
                                if (coextensional_head_node != another_coextensional_head_node) {
                                    protoedges = cons(list(coextensional_head_node, another_coextensional_head_node), protoedges);
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject cdolist_list_var = head_nodes;
                    SubLObject head_node = NIL;
                    for (head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head_node = cdolist_list_var.first()) {
                        {
                            SubLObject cdolist_list_var_5 = coextensional_head_nodes;
                            SubLObject coextensional_head_node = NIL;
                            for (coextensional_head_node = cdolist_list_var_5.first(); NIL != cdolist_list_var_5; cdolist_list_var_5 = cdolist_list_var_5.rest() , coextensional_head_node = cdolist_list_var_5.first()) {
                                protoedges = cons(list(coextensional_head_node, head_node), protoedges);
                            }
                        }
                    }
                }
                if (depth.numE(ONE_INTEGER)) {
                    recurseP = NIL;
                } else {
                    recurseP = T;
                    depth = subtract(depth, ONE_INTEGER);
                }
                if (NIL != recurseP) {
                    {
                        SubLObject cdolist_list_var = head_nodes;
                        SubLObject recurse_tail_node = NIL;
                        for (recurse_tail_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , recurse_tail_node = cdolist_list_var.first()) {
                            if (NIL == subl_promotions.memberP(recurse_tail_node, done_tail_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                                done_tail_nodes = cons(recurse_tail_node, done_tail_nodes);
                                thread.resetMultipleValues();
                                {
                                    SubLObject recurse_protoedges = bbf_forward_true_internal(pred, recurse_tail_node, mt, filter_fns, depth, done_tail_nodes);
                                    SubLObject recurse_done_tail_nodes = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    done_tail_nodes = recurse_done_tail_nodes;
                                    recurse_protoedges = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_6 = recurse_protoedges;
                                        SubLObject recurse_protoedge = NIL;
                                        for (recurse_protoedge = cdolist_list_var_6.first(); NIL != cdolist_list_var_6; cdolist_list_var_6 = cdolist_list_var_6.rest() , recurse_protoedge = cdolist_list_var_6.first()) {
                                            protoedges = cons(recurse_protoedge, protoedges);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return values(protoedges, done_tail_nodes);
            }
        }
    }

    public static SubLObject bbf_forward_true_internal(final SubLObject pred, final SubLObject tail_node, final SubLObject mt, final SubLObject filter_fns, SubLObject depth, SubLObject done_tail_nodes) {
        if (done_tail_nodes == UNPROVIDED) {
            done_tail_nodes = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject recurseP = NIL;
        SubLObject protoedges = NIL;
        SubLObject head_nodes = NIL;
        SubLObject coextensional_head_nodes = NIL;
        head_nodes = (NIL != kb_accessors.transitive_predicateP(pred)) ? gt_methods.gt_superiors(pred, tail_node, mt) : kb_mapping_utilities.pred_values_in_mt(tail_node, pred, mt, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        if (NIL != head_nodes) {
            head_nodes = blue_apply_filter_fns(head_nodes, filter_fns, mt, UNPROVIDED);
            if (NIL != head_nodes) {
                coextensional_head_nodes = intersection(head_nodes, NIL != kb_accessors.transitive_predicateP(pred) ? gt_methods.gt_inferiors(pred, tail_node, mt) : kb_mapping_utilities.pred_values_in_mt(tail_node, pred, mt, TWO_INTEGER, ONE_INTEGER, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                head_nodes = set_difference(head_nodes, coextensional_head_nodes, UNPROVIDED, UNPROVIDED);
                coextensional_head_nodes = set_difference(coextensional_head_nodes, list(tail_node), UNPROVIDED, UNPROVIDED);
            }
        }
        SubLObject cdolist_list_var;
        head_nodes = cdolist_list_var = blue_apply_filter_fns(head_nodes, filter_fns, mt, $POST_MINIMIZATION);
        SubLObject head_node = NIL;
        head_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(tail_node, head_node), protoedges);
            cdolist_list_var = cdolist_list_var.rest();
            head_node = cdolist_list_var.first();
        } 
        cdolist_list_var = coextensional_head_nodes;
        SubLObject coextensional_head_node = NIL;
        coextensional_head_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(tail_node, coextensional_head_node), protoedges);
            protoedges = cons(list(coextensional_head_node, tail_node), protoedges);
            SubLObject cdolist_list_var_$4 = coextensional_head_nodes;
            SubLObject another_coextensional_head_node = NIL;
            another_coextensional_head_node = cdolist_list_var_$4.first();
            while (NIL != cdolist_list_var_$4) {
                if (!coextensional_head_node.eql(another_coextensional_head_node)) {
                    protoedges = cons(list(coextensional_head_node, another_coextensional_head_node), protoedges);
                }
                cdolist_list_var_$4 = cdolist_list_var_$4.rest();
                another_coextensional_head_node = cdolist_list_var_$4.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            coextensional_head_node = cdolist_list_var.first();
        } 
        cdolist_list_var = head_nodes;
        head_node = NIL;
        head_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$5 = coextensional_head_nodes;
            SubLObject coextensional_head_node2 = NIL;
            coextensional_head_node2 = cdolist_list_var_$5.first();
            while (NIL != cdolist_list_var_$5) {
                protoedges = cons(list(coextensional_head_node2, head_node), protoedges);
                cdolist_list_var_$5 = cdolist_list_var_$5.rest();
                coextensional_head_node2 = cdolist_list_var_$5.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            head_node = cdolist_list_var.first();
        } 
        if (depth.numE(ONE_INTEGER)) {
            recurseP = NIL;
        } else {
            recurseP = T;
            depth = subtract(depth, ONE_INTEGER);
        }
        if (NIL != recurseP) {
            cdolist_list_var = head_nodes;
            SubLObject recurse_tail_node = NIL;
            recurse_tail_node = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL == subl_promotions.memberP(recurse_tail_node, done_tail_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                    done_tail_nodes = cons(recurse_tail_node, done_tail_nodes);
                    thread.resetMultipleValues();
                    SubLObject recurse_protoedges = bbf_forward_true_internal(pred, recurse_tail_node, mt, filter_fns, depth, done_tail_nodes);
                    final SubLObject recurse_done_tail_nodes = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    done_tail_nodes = recurse_done_tail_nodes;
                    SubLObject cdolist_list_var_$6;
                    recurse_protoedges = cdolist_list_var_$6 = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    SubLObject recurse_protoedge = NIL;
                    recurse_protoedge = cdolist_list_var_$6.first();
                    while (NIL != cdolist_list_var_$6) {
                        protoedges = cons(recurse_protoedge, protoedges);
                        cdolist_list_var_$6 = cdolist_list_var_$6.rest();
                        recurse_protoedge = cdolist_list_var_$6.first();
                    } 
                }
                cdolist_list_var = cdolist_list_var.rest();
                recurse_tail_node = cdolist_list_var.first();
            } 
        }
        return values(protoedges, done_tail_nodes);
    }

    public static final SubLObject bbf_backward_true_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
                SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
                SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
                SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
                SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                SubLObject cdolist_list_var = v_forts;
                SubLObject fort = NIL;
                for (fort = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fort = cdolist_list_var.first()) {
                    {
                        SubLObject cdolist_list_var_7 = preds;
                        SubLObject pred = NIL;
                        for (pred = cdolist_list_var_7.first(); NIL != cdolist_list_var_7; cdolist_list_var_7 = cdolist_list_var_7.rest() , pred = cdolist_list_var_7.first()) {
                            thread.resetMultipleValues();
                            {
                                SubLObject protoedges = bbf_backward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                                SubLObject protonodes = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                {
                                    SubLObject cdolist_list_var_8 = protonodes;
                                    SubLObject protonode = NIL;
                                    for (protonode = cdolist_list_var_8.first(); NIL != cdolist_list_var_8; cdolist_list_var_8 = cdolist_list_var_8.rest() , protonode = cdolist_list_var_8.first()) {
                                        result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                                    }
                                }
                                {
                                    SubLObject cdolist_list_var_9 = protoedges;
                                    SubLObject protoedge = NIL;
                                    for (protoedge = cdolist_list_var_9.first(); NIL != cdolist_list_var_9; cdolist_list_var_9 = cdolist_list_var_9.rest() , protoedge = cdolist_list_var_9.first()) {
                                        result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                                    }
                                }
                            }
                        }
                    }
                }
                return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
            }
        }
    }

    public static SubLObject bbf_backward_true(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
        final SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
        final SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
        final SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
        SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        SubLObject cdolist_list_var = v_forts;
        SubLObject fort = NIL;
        fort = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$7 = preds;
            SubLObject pred = NIL;
            pred = cdolist_list_var_$7.first();
            while (NIL != cdolist_list_var_$7) {
                thread.resetMultipleValues();
                final SubLObject protoedges = bbf_backward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                final SubLObject protonodes = thread.secondMultipleValue();
                thread.resetMultipleValues();
                SubLObject cdolist_list_var_$8 = protonodes;
                SubLObject protonode = NIL;
                protonode = cdolist_list_var_$8.first();
                while (NIL != cdolist_list_var_$8) {
                    result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                    cdolist_list_var_$8 = cdolist_list_var_$8.rest();
                    protonode = cdolist_list_var_$8.first();
                } 
                SubLObject cdolist_list_var_$9 = protoedges;
                SubLObject protoedge = NIL;
                protoedge = cdolist_list_var_$9.first();
                while (NIL != cdolist_list_var_$9) {
                    result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                    cdolist_list_var_$9 = cdolist_list_var_$9.rest();
                    protoedge = cdolist_list_var_$9.first();
                } 
                cdolist_list_var_$7 = cdolist_list_var_$7.rest();
                pred = cdolist_list_var_$7.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            fort = cdolist_list_var.first();
        } 
        return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
    }

    public static final SubLObject bbf_backward_true_internal_alt(SubLObject pred, SubLObject head_node, SubLObject mt, SubLObject filter_fns, SubLObject depth, SubLObject done_head_nodes) {
        if (done_head_nodes == UNPROVIDED) {
            done_head_nodes = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject recurseP = NIL;
                SubLObject protoedges = NIL;
                SubLObject tail_nodes = NIL;
                SubLObject coextensional_tail_nodes = NIL;
                tail_nodes = (NIL != kb_accessors.transitive_predicateP(pred)) ? ((SubLObject) (gt_methods.gt_inferiors(pred, head_node, mt))) : kb_mapping_utilities.pred_values_in_mt(head_node, pred, mt, TWO_INTEGER, ONE_INTEGER, UNPROVIDED);
                if (NIL != tail_nodes) {
                    tail_nodes = blue_apply_filter_fns(tail_nodes, filter_fns, mt, UNPROVIDED);
                    if (NIL != tail_nodes) {
                        coextensional_tail_nodes = intersection(tail_nodes, NIL != kb_accessors.transitive_predicateP(pred) ? ((SubLObject) (gt_methods.gt_superiors(pred, head_node, mt))) : kb_mapping_utilities.pred_values_in_mt(head_node, pred, mt, ONE_INTEGER, TWO_INTEGER, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                        tail_nodes = set_difference(tail_nodes, coextensional_tail_nodes, UNPROVIDED, UNPROVIDED);
                        coextensional_tail_nodes = set_difference(coextensional_tail_nodes, list(head_node), UNPROVIDED, UNPROVIDED);
                    }
                }
                tail_nodes = blue_apply_filter_fns(tail_nodes, filter_fns, mt, $POST_MINIMIZATION);
                {
                    SubLObject cdolist_list_var = tail_nodes;
                    SubLObject tail_node = NIL;
                    for (tail_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , tail_node = cdolist_list_var.first()) {
                        protoedges = cons(list(tail_node, head_node), protoedges);
                    }
                }
                {
                    SubLObject cdolist_list_var = coextensional_tail_nodes;
                    SubLObject coextensional_tail_node = NIL;
                    for (coextensional_tail_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , coextensional_tail_node = cdolist_list_var.first()) {
                        protoedges = cons(list(head_node, coextensional_tail_node), protoedges);
                        protoedges = cons(list(coextensional_tail_node, head_node), protoedges);
                    }
                }
                if (depth.numE(ONE_INTEGER)) {
                    recurseP = NIL;
                } else {
                    recurseP = T;
                    depth = subtract(depth, ONE_INTEGER);
                }
                if (NIL != recurseP) {
                    {
                        SubLObject cdolist_list_var = tail_nodes;
                        SubLObject recurse_head_node = NIL;
                        for (recurse_head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , recurse_head_node = cdolist_list_var.first()) {
                            if (NIL == subl_promotions.memberP(recurse_head_node, done_head_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                                done_head_nodes = cons(recurse_head_node, done_head_nodes);
                                thread.resetMultipleValues();
                                {
                                    SubLObject recurse_protoedges = bbf_backward_true_internal(pred, recurse_head_node, mt, filter_fns, depth, done_head_nodes);
                                    SubLObject recurse_done_head_nodes = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    done_head_nodes = recurse_done_head_nodes;
                                    recurse_protoedges = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_10 = recurse_protoedges;
                                        SubLObject recurse_protoedge = NIL;
                                        for (recurse_protoedge = cdolist_list_var_10.first(); NIL != cdolist_list_var_10; cdolist_list_var_10 = cdolist_list_var_10.rest() , recurse_protoedge = cdolist_list_var_10.first()) {
                                            protoedges = cons(recurse_protoedge, protoedges);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return values(protoedges, done_head_nodes);
            }
        }
    }

    public static SubLObject bbf_backward_true_internal(final SubLObject pred, final SubLObject head_node, final SubLObject mt, final SubLObject filter_fns, SubLObject depth, SubLObject done_head_nodes) {
        if (done_head_nodes == UNPROVIDED) {
            done_head_nodes = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject recurseP = NIL;
        SubLObject protoedges = NIL;
        SubLObject tail_nodes = NIL;
        SubLObject coextensional_tail_nodes = NIL;
        tail_nodes = (NIL != kb_accessors.transitive_predicateP(pred)) ? gt_methods.gt_inferiors(pred, head_node, mt) : kb_mapping_utilities.pred_values_in_mt(head_node, pred, mt, TWO_INTEGER, ONE_INTEGER, UNPROVIDED);
        if (NIL != tail_nodes) {
            tail_nodes = blue_apply_filter_fns(tail_nodes, filter_fns, mt, UNPROVIDED);
            if (NIL != tail_nodes) {
                coextensional_tail_nodes = intersection(tail_nodes, NIL != kb_accessors.transitive_predicateP(pred) ? gt_methods.gt_superiors(pred, head_node, mt) : kb_mapping_utilities.pred_values_in_mt(head_node, pred, mt, ONE_INTEGER, TWO_INTEGER, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                tail_nodes = set_difference(tail_nodes, coextensional_tail_nodes, UNPROVIDED, UNPROVIDED);
                coextensional_tail_nodes = set_difference(coextensional_tail_nodes, list(head_node), UNPROVIDED, UNPROVIDED);
            }
        }
        SubLObject cdolist_list_var;
        tail_nodes = cdolist_list_var = blue_apply_filter_fns(tail_nodes, filter_fns, mt, $POST_MINIMIZATION);
        SubLObject tail_node = NIL;
        tail_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(tail_node, head_node), protoedges);
            cdolist_list_var = cdolist_list_var.rest();
            tail_node = cdolist_list_var.first();
        } 
        cdolist_list_var = coextensional_tail_nodes;
        SubLObject coextensional_tail_node = NIL;
        coextensional_tail_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(head_node, coextensional_tail_node), protoedges);
            protoedges = cons(list(coextensional_tail_node, head_node), protoedges);
            cdolist_list_var = cdolist_list_var.rest();
            coextensional_tail_node = cdolist_list_var.first();
        } 
        if (depth.numE(ONE_INTEGER)) {
            recurseP = NIL;
        } else {
            recurseP = T;
            depth = subtract(depth, ONE_INTEGER);
        }
        if (NIL != recurseP) {
            cdolist_list_var = tail_nodes;
            SubLObject recurse_head_node = NIL;
            recurse_head_node = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL == subl_promotions.memberP(recurse_head_node, done_head_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                    done_head_nodes = cons(recurse_head_node, done_head_nodes);
                    thread.resetMultipleValues();
                    SubLObject recurse_protoedges = bbf_backward_true_internal(pred, recurse_head_node, mt, filter_fns, depth, done_head_nodes);
                    final SubLObject recurse_done_head_nodes = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    done_head_nodes = recurse_done_head_nodes;
                    SubLObject cdolist_list_var_$10;
                    recurse_protoedges = cdolist_list_var_$10 = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    SubLObject recurse_protoedge = NIL;
                    recurse_protoedge = cdolist_list_var_$10.first();
                    while (NIL != cdolist_list_var_$10) {
                        protoedges = cons(recurse_protoedge, protoedges);
                        cdolist_list_var_$10 = cdolist_list_var_$10.rest();
                        recurse_protoedge = cdolist_list_var_$10.first();
                    } 
                }
                cdolist_list_var = cdolist_list_var.rest();
                recurse_head_node = cdolist_list_var.first();
            } 
        }
        return values(protoedges, done_head_nodes);
    }

    public static final SubLObject bbf_min_forward_true_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
                SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
                SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
                SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
                SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                SubLObject cdolist_list_var = v_forts;
                SubLObject fort = NIL;
                for (fort = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fort = cdolist_list_var.first()) {
                    {
                        SubLObject cdolist_list_var_11 = preds;
                        SubLObject pred = NIL;
                        for (pred = cdolist_list_var_11.first(); NIL != cdolist_list_var_11; cdolist_list_var_11 = cdolist_list_var_11.rest() , pred = cdolist_list_var_11.first()) {
                            thread.resetMultipleValues();
                            {
                                SubLObject protoedges = bbf_min_forward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                                SubLObject protonodes = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                {
                                    SubLObject cdolist_list_var_12 = protonodes;
                                    SubLObject protonode = NIL;
                                    for (protonode = cdolist_list_var_12.first(); NIL != cdolist_list_var_12; cdolist_list_var_12 = cdolist_list_var_12.rest() , protonode = cdolist_list_var_12.first()) {
                                        result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                                    }
                                }
                                {
                                    SubLObject cdolist_list_var_13 = protoedges;
                                    SubLObject protoedge = NIL;
                                    for (protoedge = cdolist_list_var_13.first(); NIL != cdolist_list_var_13; cdolist_list_var_13 = cdolist_list_var_13.rest() , protoedge = cdolist_list_var_13.first()) {
                                        result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                                    }
                                }
                            }
                        }
                    }
                }
                return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
            }
        }
    }

    public static SubLObject bbf_min_forward_true(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
        final SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
        final SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
        final SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
        SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        SubLObject cdolist_list_var = v_forts;
        SubLObject fort = NIL;
        fort = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$11 = preds;
            SubLObject pred = NIL;
            pred = cdolist_list_var_$11.first();
            while (NIL != cdolist_list_var_$11) {
                thread.resetMultipleValues();
                final SubLObject protoedges = bbf_min_forward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                final SubLObject protonodes = thread.secondMultipleValue();
                thread.resetMultipleValues();
                SubLObject cdolist_list_var_$12 = protonodes;
                SubLObject protonode = NIL;
                protonode = cdolist_list_var_$12.first();
                while (NIL != cdolist_list_var_$12) {
                    result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                    cdolist_list_var_$12 = cdolist_list_var_$12.rest();
                    protonode = cdolist_list_var_$12.first();
                } 
                SubLObject cdolist_list_var_$13 = protoedges;
                SubLObject protoedge = NIL;
                protoedge = cdolist_list_var_$13.first();
                while (NIL != cdolist_list_var_$13) {
                    result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                    cdolist_list_var_$13 = cdolist_list_var_$13.rest();
                    protoedge = cdolist_list_var_$13.first();
                } 
                cdolist_list_var_$11 = cdolist_list_var_$11.rest();
                pred = cdolist_list_var_$11.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            fort = cdolist_list_var.first();
        } 
        return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
    }

    public static final SubLObject bbf_min_forward_true_internal_alt(SubLObject pred, SubLObject tail_node, SubLObject mt, SubLObject filter_fns, SubLObject depth, SubLObject done_tail_nodes) {
        if (done_tail_nodes == UNPROVIDED) {
            done_tail_nodes = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject recurseP = NIL;
                SubLObject protoedges = NIL;
                SubLObject head_nodes = NIL;
                SubLObject coextensional_head_nodes = NIL;
                if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
                    head_nodes = sbhl_search_methods.sbhl_all_forward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), tail_node, mt, UNPROVIDED);
                    if (NIL != head_nodes) {
                        head_nodes = blue_apply_filter_fns(head_nodes, filter_fns, mt, UNPROVIDED);
                        if (NIL != head_nodes) {
                            coextensional_head_nodes = sbhl_search_methods.sbhl_all_backward_true_nodes_among(sbhl_module_vars.get_sbhl_module(pred), tail_node, head_nodes, mt, UNPROVIDED);
                            head_nodes = set_difference(head_nodes, coextensional_head_nodes, UNPROVIDED, UNPROVIDED);
                            coextensional_head_nodes = set_difference(coextensional_head_nodes, list(tail_node), UNPROVIDED, UNPROVIDED);
                            if (length(head_nodes).numG(ONE_INTEGER)) {
                                head_nodes = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), head_nodes, mt, UNPROVIDED);
                            }
                            if (length(coextensional_head_nodes).numG(ONE_INTEGER)) {
                                coextensional_head_nodes = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), coextensional_head_nodes, mt, UNPROVIDED);
                            }
                        }
                    }
                }
                head_nodes = blue_apply_filter_fns(head_nodes, filter_fns, mt, $POST_MINIMIZATION);
                {
                    SubLObject cdolist_list_var = head_nodes;
                    SubLObject head_node = NIL;
                    for (head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head_node = cdolist_list_var.first()) {
                        protoedges = cons(list(tail_node, head_node), protoedges);
                    }
                }
                {
                    SubLObject cdolist_list_var = coextensional_head_nodes;
                    SubLObject coextensional_head_node = NIL;
                    for (coextensional_head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , coextensional_head_node = cdolist_list_var.first()) {
                        protoedges = cons(list(tail_node, coextensional_head_node), protoedges);
                        protoedges = cons(list(coextensional_head_node, tail_node), protoedges);
                        {
                            SubLObject cdolist_list_var_14 = coextensional_head_nodes;
                            SubLObject another_coextensional_head_node = NIL;
                            for (another_coextensional_head_node = cdolist_list_var_14.first(); NIL != cdolist_list_var_14; cdolist_list_var_14 = cdolist_list_var_14.rest() , another_coextensional_head_node = cdolist_list_var_14.first()) {
                                if (coextensional_head_node != another_coextensional_head_node) {
                                    protoedges = cons(list(coextensional_head_node, another_coextensional_head_node), protoedges);
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject cdolist_list_var = head_nodes;
                    SubLObject head_node = NIL;
                    for (head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head_node = cdolist_list_var.first()) {
                        {
                            SubLObject cdolist_list_var_15 = coextensional_head_nodes;
                            SubLObject coextensional_head_node = NIL;
                            for (coextensional_head_node = cdolist_list_var_15.first(); NIL != cdolist_list_var_15; cdolist_list_var_15 = cdolist_list_var_15.rest() , coextensional_head_node = cdolist_list_var_15.first()) {
                                protoedges = cons(list(coextensional_head_node, head_node), protoedges);
                            }
                        }
                    }
                }
                if (depth.numE(ONE_INTEGER)) {
                    recurseP = NIL;
                } else {
                    recurseP = T;
                    depth = subtract(depth, ONE_INTEGER);
                }
                if (NIL != recurseP) {
                    {
                        SubLObject cdolist_list_var = head_nodes;
                        SubLObject recurse_tail_node = NIL;
                        for (recurse_tail_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , recurse_tail_node = cdolist_list_var.first()) {
                            if (NIL == subl_promotions.memberP(recurse_tail_node, done_tail_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                                done_tail_nodes = cons(recurse_tail_node, done_tail_nodes);
                                thread.resetMultipleValues();
                                {
                                    SubLObject recurse_protoedges = bbf_min_forward_true_internal(pred, recurse_tail_node, mt, filter_fns, depth, done_tail_nodes);
                                    SubLObject recurse_done_tail_nodes = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    done_tail_nodes = recurse_done_tail_nodes;
                                    recurse_protoedges = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_16 = recurse_protoedges;
                                        SubLObject recurse_protoedge = NIL;
                                        for (recurse_protoedge = cdolist_list_var_16.first(); NIL != cdolist_list_var_16; cdolist_list_var_16 = cdolist_list_var_16.rest() , recurse_protoedge = cdolist_list_var_16.first()) {
                                            protoedges = cons(recurse_protoedge, protoedges);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return values(protoedges, done_tail_nodes);
            }
        }
    }

    public static SubLObject bbf_min_forward_true_internal(final SubLObject pred, final SubLObject tail_node, final SubLObject mt, final SubLObject filter_fns, SubLObject depth, SubLObject done_tail_nodes) {
        if (done_tail_nodes == UNPROVIDED) {
            done_tail_nodes = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject recurseP = NIL;
        SubLObject protoedges = NIL;
        SubLObject head_nodes = NIL;
        SubLObject coextensional_head_nodes = NIL;
        if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
            head_nodes = sbhl_search_methods.sbhl_all_forward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), tail_node, mt, UNPROVIDED);
            if (NIL != head_nodes) {
                head_nodes = blue_apply_filter_fns(head_nodes, filter_fns, mt, UNPROVIDED);
                if (NIL != head_nodes) {
                    coextensional_head_nodes = sbhl_search_methods.sbhl_all_backward_true_nodes_among(sbhl_module_vars.get_sbhl_module(pred), tail_node, head_nodes, mt, UNPROVIDED);
                    head_nodes = set_difference(head_nodes, coextensional_head_nodes, UNPROVIDED, UNPROVIDED);
                    coextensional_head_nodes = set_difference(coextensional_head_nodes, list(tail_node), UNPROVIDED, UNPROVIDED);
                    if (length(head_nodes).numG(ONE_INTEGER)) {
                        head_nodes = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), head_nodes, mt, UNPROVIDED);
                    }
                    if (length(coextensional_head_nodes).numG(ONE_INTEGER)) {
                        coextensional_head_nodes = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), coextensional_head_nodes, mt, UNPROVIDED);
                    }
                }
            }
        }
        SubLObject cdolist_list_var;
        head_nodes = cdolist_list_var = blue_apply_filter_fns(head_nodes, filter_fns, mt, $POST_MINIMIZATION);
        SubLObject head_node = NIL;
        head_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(tail_node, head_node), protoedges);
            cdolist_list_var = cdolist_list_var.rest();
            head_node = cdolist_list_var.first();
        } 
        cdolist_list_var = coextensional_head_nodes;
        SubLObject coextensional_head_node = NIL;
        coextensional_head_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(tail_node, coextensional_head_node), protoedges);
            protoedges = cons(list(coextensional_head_node, tail_node), protoedges);
            SubLObject cdolist_list_var_$14 = coextensional_head_nodes;
            SubLObject another_coextensional_head_node = NIL;
            another_coextensional_head_node = cdolist_list_var_$14.first();
            while (NIL != cdolist_list_var_$14) {
                if (!coextensional_head_node.eql(another_coextensional_head_node)) {
                    protoedges = cons(list(coextensional_head_node, another_coextensional_head_node), protoedges);
                }
                cdolist_list_var_$14 = cdolist_list_var_$14.rest();
                another_coextensional_head_node = cdolist_list_var_$14.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            coextensional_head_node = cdolist_list_var.first();
        } 
        cdolist_list_var = head_nodes;
        head_node = NIL;
        head_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$15 = coextensional_head_nodes;
            SubLObject coextensional_head_node2 = NIL;
            coextensional_head_node2 = cdolist_list_var_$15.first();
            while (NIL != cdolist_list_var_$15) {
                protoedges = cons(list(coextensional_head_node2, head_node), protoedges);
                cdolist_list_var_$15 = cdolist_list_var_$15.rest();
                coextensional_head_node2 = cdolist_list_var_$15.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            head_node = cdolist_list_var.first();
        } 
        if (depth.numE(ONE_INTEGER)) {
            recurseP = NIL;
        } else {
            recurseP = T;
            depth = subtract(depth, ONE_INTEGER);
        }
        if (NIL != recurseP) {
            cdolist_list_var = head_nodes;
            SubLObject recurse_tail_node = NIL;
            recurse_tail_node = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL == subl_promotions.memberP(recurse_tail_node, done_tail_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                    done_tail_nodes = cons(recurse_tail_node, done_tail_nodes);
                    thread.resetMultipleValues();
                    SubLObject recurse_protoedges = bbf_min_forward_true_internal(pred, recurse_tail_node, mt, filter_fns, depth, done_tail_nodes);
                    final SubLObject recurse_done_tail_nodes = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    done_tail_nodes = recurse_done_tail_nodes;
                    SubLObject cdolist_list_var_$16;
                    recurse_protoedges = cdolist_list_var_$16 = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    SubLObject recurse_protoedge = NIL;
                    recurse_protoedge = cdolist_list_var_$16.first();
                    while (NIL != cdolist_list_var_$16) {
                        protoedges = cons(recurse_protoedge, protoedges);
                        cdolist_list_var_$16 = cdolist_list_var_$16.rest();
                        recurse_protoedge = cdolist_list_var_$16.first();
                    } 
                }
                cdolist_list_var = cdolist_list_var.rest();
                recurse_tail_node = cdolist_list_var.first();
            } 
        }
        return values(protoedges, done_tail_nodes);
    }

    public static final SubLObject bbf_min_backward_true_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
                SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
                SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
                SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
                SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                SubLObject cdolist_list_var = v_forts;
                SubLObject fort = NIL;
                for (fort = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fort = cdolist_list_var.first()) {
                    {
                        SubLObject cdolist_list_var_17 = preds;
                        SubLObject pred = NIL;
                        for (pred = cdolist_list_var_17.first(); NIL != cdolist_list_var_17; cdolist_list_var_17 = cdolist_list_var_17.rest() , pred = cdolist_list_var_17.first()) {
                            thread.resetMultipleValues();
                            {
                                SubLObject protoedges = bbf_min_backward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                                SubLObject protonodes = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                {
                                    SubLObject cdolist_list_var_18 = protonodes;
                                    SubLObject protonode = NIL;
                                    for (protonode = cdolist_list_var_18.first(); NIL != cdolist_list_var_18; cdolist_list_var_18 = cdolist_list_var_18.rest() , protonode = cdolist_list_var_18.first()) {
                                        result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                                    }
                                }
                                {
                                    SubLObject cdolist_list_var_19 = protoedges;
                                    SubLObject protoedge = NIL;
                                    for (protoedge = cdolist_list_var_19.first(); NIL != cdolist_list_var_19; cdolist_list_var_19 = cdolist_list_var_19.rest() , protoedge = cdolist_list_var_19.first()) {
                                        result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                                    }
                                }
                            }
                        }
                    }
                }
                return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
            }
        }
    }

    public static SubLObject bbf_min_backward_true(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
        final SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
        final SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
        final SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
        SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        SubLObject cdolist_list_var = v_forts;
        SubLObject fort = NIL;
        fort = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$17 = preds;
            SubLObject pred = NIL;
            pred = cdolist_list_var_$17.first();
            while (NIL != cdolist_list_var_$17) {
                thread.resetMultipleValues();
                final SubLObject protoedges = bbf_min_backward_true_internal(pred, fort, mt, filter_fns, depth, UNPROVIDED);
                final SubLObject protonodes = thread.secondMultipleValue();
                thread.resetMultipleValues();
                SubLObject cdolist_list_var_$18 = protonodes;
                SubLObject protonode = NIL;
                protonode = cdolist_list_var_$18.first();
                while (NIL != cdolist_list_var_$18) {
                    result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                    cdolist_list_var_$18 = cdolist_list_var_$18.rest();
                    protonode = cdolist_list_var_$18.first();
                } 
                SubLObject cdolist_list_var_$19 = protoedges;
                SubLObject protoedge = NIL;
                protoedge = cdolist_list_var_$19.first();
                while (NIL != cdolist_list_var_$19) {
                    result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                    cdolist_list_var_$19 = cdolist_list_var_$19.rest();
                    protoedge = cdolist_list_var_$19.first();
                } 
                cdolist_list_var_$17 = cdolist_list_var_$17.rest();
                pred = cdolist_list_var_$17.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            fort = cdolist_list_var.first();
        } 
        return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
    }

    public static final SubLObject bbf_min_backward_true_internal_alt(SubLObject pred, SubLObject head_node, SubLObject mt, SubLObject filter_fns, SubLObject depth, SubLObject done_head_nodes) {
        if (done_head_nodes == UNPROVIDED) {
            done_head_nodes = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject recurseP = NIL;
                SubLObject protoedges = NIL;
                SubLObject tail_nodes = NIL;
                SubLObject coextensional_tail_nodes = NIL;
                if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
                    tail_nodes = sbhl_search_methods.sbhl_all_backward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), head_node, mt, UNPROVIDED);
                    if (NIL != tail_nodes) {
                        tail_nodes = blue_apply_filter_fns(tail_nodes, filter_fns, mt, UNPROVIDED);
                        if (NIL != tail_nodes) {
                            coextensional_tail_nodes = sbhl_search_methods.sbhl_all_forward_true_nodes_among(sbhl_module_vars.get_sbhl_module(pred), head_node, tail_nodes, mt, UNPROVIDED);
                            tail_nodes = set_difference(tail_nodes, coextensional_tail_nodes, UNPROVIDED, UNPROVIDED);
                            coextensional_tail_nodes = set_difference(coextensional_tail_nodes, list(head_node), UNPROVIDED, UNPROVIDED);
                            if (length(tail_nodes).numG(ONE_INTEGER)) {
                                tail_nodes = sbhl_search_methods.sbhl_max_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), tail_nodes, mt, UNPROVIDED, UNPROVIDED);
                            }
                            if (length(coextensional_tail_nodes).numG(ONE_INTEGER)) {
                                coextensional_tail_nodes = sbhl_search_methods.sbhl_max_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), coextensional_tail_nodes, mt, UNPROVIDED, UNPROVIDED);
                            }
                        }
                    }
                }
                tail_nodes = blue_apply_filter_fns(tail_nodes, filter_fns, mt, $POST_MINIMIZATION);
                {
                    SubLObject cdolist_list_var = tail_nodes;
                    SubLObject tail_node = NIL;
                    for (tail_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , tail_node = cdolist_list_var.first()) {
                        protoedges = cons(list(tail_node, head_node), protoedges);
                    }
                }
                {
                    SubLObject cdolist_list_var = coextensional_tail_nodes;
                    SubLObject coextensional_tail_node = NIL;
                    for (coextensional_tail_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , coextensional_tail_node = cdolist_list_var.first()) {
                        protoedges = cons(list(head_node, coextensional_tail_node), protoedges);
                        protoedges = cons(list(coextensional_tail_node, head_node), protoedges);
                    }
                }
                if (depth.numE(ONE_INTEGER)) {
                    recurseP = NIL;
                } else {
                    recurseP = T;
                    depth = subtract(depth, ONE_INTEGER);
                }
                if (NIL != recurseP) {
                    {
                        SubLObject cdolist_list_var = tail_nodes;
                        SubLObject recurse_head_node = NIL;
                        for (recurse_head_node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , recurse_head_node = cdolist_list_var.first()) {
                            if (NIL == subl_promotions.memberP(recurse_head_node, done_head_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                                done_head_nodes = cons(recurse_head_node, done_head_nodes);
                                thread.resetMultipleValues();
                                {
                                    SubLObject recurse_protoedges = bbf_min_backward_true_internal(pred, recurse_head_node, mt, filter_fns, depth, done_head_nodes);
                                    SubLObject recurse_done_head_nodes = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    done_head_nodes = recurse_done_head_nodes;
                                    recurse_protoedges = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_20 = recurse_protoedges;
                                        SubLObject recurse_protoedge = NIL;
                                        for (recurse_protoedge = cdolist_list_var_20.first(); NIL != cdolist_list_var_20; cdolist_list_var_20 = cdolist_list_var_20.rest() , recurse_protoedge = cdolist_list_var_20.first()) {
                                            protoedges = cons(recurse_protoedge, protoedges);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return values(protoedges, done_head_nodes);
            }
        }
    }

    public static SubLObject bbf_min_backward_true_internal(final SubLObject pred, final SubLObject head_node, final SubLObject mt, final SubLObject filter_fns, SubLObject depth, SubLObject done_head_nodes) {
        if (done_head_nodes == UNPROVIDED) {
            done_head_nodes = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject recurseP = NIL;
        SubLObject protoedges = NIL;
        SubLObject tail_nodes = NIL;
        SubLObject coextensional_tail_nodes = NIL;
        if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
            tail_nodes = sbhl_search_methods.sbhl_all_backward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), head_node, mt, UNPROVIDED);
            if (NIL != tail_nodes) {
                tail_nodes = blue_apply_filter_fns(tail_nodes, filter_fns, mt, UNPROVIDED);
                if (NIL != tail_nodes) {
                    coextensional_tail_nodes = sbhl_search_methods.sbhl_all_forward_true_nodes_among(sbhl_module_vars.get_sbhl_module(pred), head_node, tail_nodes, mt, UNPROVIDED);
                    tail_nodes = set_difference(tail_nodes, coextensional_tail_nodes, UNPROVIDED, UNPROVIDED);
                    coextensional_tail_nodes = set_difference(coextensional_tail_nodes, list(head_node), UNPROVIDED, UNPROVIDED);
                    if (length(tail_nodes).numG(ONE_INTEGER)) {
                        tail_nodes = sbhl_search_methods.sbhl_max_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), tail_nodes, mt, UNPROVIDED, UNPROVIDED);
                    }
                    if (length(coextensional_tail_nodes).numG(ONE_INTEGER)) {
                        coextensional_tail_nodes = sbhl_search_methods.sbhl_max_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), coextensional_tail_nodes, mt, UNPROVIDED, UNPROVIDED);
                    }
                }
            }
        }
        SubLObject cdolist_list_var;
        tail_nodes = cdolist_list_var = blue_apply_filter_fns(tail_nodes, filter_fns, mt, $POST_MINIMIZATION);
        SubLObject tail_node = NIL;
        tail_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(tail_node, head_node), protoedges);
            cdolist_list_var = cdolist_list_var.rest();
            tail_node = cdolist_list_var.first();
        } 
        cdolist_list_var = coextensional_tail_nodes;
        SubLObject coextensional_tail_node = NIL;
        coextensional_tail_node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            protoedges = cons(list(head_node, coextensional_tail_node), protoedges);
            protoedges = cons(list(coextensional_tail_node, head_node), protoedges);
            cdolist_list_var = cdolist_list_var.rest();
            coextensional_tail_node = cdolist_list_var.first();
        } 
        if (depth.numE(ONE_INTEGER)) {
            recurseP = NIL;
        } else {
            recurseP = T;
            depth = subtract(depth, ONE_INTEGER);
        }
        if (NIL != recurseP) {
            cdolist_list_var = tail_nodes;
            SubLObject recurse_head_node = NIL;
            recurse_head_node = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL == subl_promotions.memberP(recurse_head_node, done_head_nodes, symbol_function(EQUAL), UNPROVIDED)) {
                    done_head_nodes = cons(recurse_head_node, done_head_nodes);
                    thread.resetMultipleValues();
                    SubLObject recurse_protoedges = bbf_min_backward_true_internal(pred, recurse_head_node, mt, filter_fns, depth, done_head_nodes);
                    final SubLObject recurse_done_head_nodes = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    done_head_nodes = recurse_done_head_nodes;
                    SubLObject cdolist_list_var_$20;
                    recurse_protoedges = cdolist_list_var_$20 = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    SubLObject recurse_protoedge = NIL;
                    recurse_protoedge = cdolist_list_var_$20.first();
                    while (NIL != cdolist_list_var_$20) {
                        protoedges = cons(recurse_protoedge, protoedges);
                        cdolist_list_var_$20 = cdolist_list_var_$20.rest();
                        recurse_protoedge = cdolist_list_var_$20.first();
                    } 
                }
                cdolist_list_var = cdolist_list_var.rest();
                recurse_head_node = cdolist_list_var.first();
            } 
        }
        return values(protoedges, done_head_nodes);
    }

    public static final SubLObject bbf_min_ceilings_forward_true_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
                SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
                SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
                SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
                SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                SubLObject cdolist_list_var = preds;
                SubLObject pred = NIL;
                for (pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pred = cdolist_list_var.first()) {
                    thread.resetMultipleValues();
                    {
                        SubLObject protoedges = bbf_min_ceilings_forward_true_internal(pred, v_forts, mt, filter_fns, depth, UNPROVIDED);
                        SubLObject protonodes = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        {
                            SubLObject cdolist_list_var_21 = protonodes;
                            SubLObject protonode = NIL;
                            for (protonode = cdolist_list_var_21.first(); NIL != cdolist_list_var_21; cdolist_list_var_21 = cdolist_list_var_21.rest() , protonode = cdolist_list_var_21.first()) {
                                result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                            }
                        }
                        {
                            SubLObject cdolist_list_var_22 = protoedges;
                            SubLObject protoedge = NIL;
                            for (protoedge = cdolist_list_var_22.first(); NIL != cdolist_list_var_22; cdolist_list_var_22 = cdolist_list_var_22.rest() , protoedge = cdolist_list_var_22.first()) {
                                result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                            }
                        }
                    }
                }
                return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
            }
        }
    }

    public static SubLObject bbf_min_ceilings_forward_true(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
        final SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
        final SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
        final SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
        SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        SubLObject cdolist_list_var = preds;
        SubLObject pred = NIL;
        pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            thread.resetMultipleValues();
            final SubLObject protoedges = bbf_min_ceilings_forward_true_internal(pred, v_forts, mt, filter_fns, depth, UNPROVIDED);
            final SubLObject protonodes = thread.secondMultipleValue();
            thread.resetMultipleValues();
            SubLObject cdolist_list_var_$21 = protonodes;
            SubLObject protonode = NIL;
            protonode = cdolist_list_var_$21.first();
            while (NIL != cdolist_list_var_$21) {
                result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                cdolist_list_var_$21 = cdolist_list_var_$21.rest();
                protonode = cdolist_list_var_$21.first();
            } 
            SubLObject cdolist_list_var_$22 = protoedges;
            SubLObject protoedge = NIL;
            protoedge = cdolist_list_var_$22.first();
            while (NIL != cdolist_list_var_$22) {
                result_edges = cons(bbf_make_edge(pred, protoedge.first(), second(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                cdolist_list_var_$22 = cdolist_list_var_$22.rest();
                protoedge = cdolist_list_var_$22.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            pred = cdolist_list_var.first();
        } 
        return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
    }

    public static final SubLObject bbf_min_ceilings_forward_true_internal_alt(SubLObject pred, SubLObject arg_ins, SubLObject mt, SubLObject filter_fns, SubLObject depth, SubLObject done_arg_ins) {
        if (done_arg_ins == UNPROVIDED) {
            done_arg_ins = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if ((NIL == arg_ins) || length(arg_ins).numLE(ONE_INTEGER)) {
                return values(NIL, done_arg_ins);
            }
            {
                SubLObject recurseP = NIL;
                SubLObject protoedges = NIL;
                SubLObject arg_outs = NIL;
                if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
                    {
                        SubLObject arg_in_1 = NIL;
                        SubLObject arg_ins_copy = NIL;
                        for (arg_in_1 = arg_ins.first(), arg_ins_copy = arg_ins.rest(); NIL != arg_ins_copy; arg_in_1 = arg_ins_copy.first() , arg_ins_copy = arg_ins_copy.rest()) {
                            {
                                SubLObject cdolist_list_var = arg_ins_copy;
                                SubLObject arg_in_2 = NIL;
                                for (arg_in_2 = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg_in_2 = cdolist_list_var.first()) {
                                    {
                                        SubLObject local_arg_outs = NIL;
                                        local_arg_outs = sbhl_search_methods.sbhl_ceilings(sbhl_module_vars.get_sbhl_module(pred), list(arg_in_1, arg_in_2), NIL, mt, $$True_JustificationTruth);
                                        if (NIL != local_arg_outs) {
                                            local_arg_outs = blue_apply_filter_fns(local_arg_outs, filter_fns, mt, UNPROVIDED);
                                            local_arg_outs = set_difference(local_arg_outs, arg_ins, UNPROVIDED, UNPROVIDED);
                                            if (length(local_arg_outs).numG(ONE_INTEGER)) {
                                                local_arg_outs = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), local_arg_outs, mt, $$True_JustificationTruth);
                                            }
                                        }
                                        local_arg_outs = blue_apply_filter_fns(local_arg_outs, filter_fns, mt, $POST_MINIMIZATION);
                                        {
                                            SubLObject cdolist_list_var_23 = local_arg_outs;
                                            SubLObject local_arg_out = NIL;
                                            for (local_arg_out = cdolist_list_var_23.first(); NIL != cdolist_list_var_23; cdolist_list_var_23 = cdolist_list_var_23.rest() , local_arg_out = cdolist_list_var_23.first()) {
                                                arg_outs = cons(local_arg_out, arg_outs);
                                                protoedges = cons(list(arg_in_1, local_arg_out), protoedges);
                                                protoedges = cons(list(arg_in_2, local_arg_out), protoedges);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    protoedges = remove_duplicates(protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    arg_outs = remove_duplicates(arg_outs, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                }
                if (depth.numE(ONE_INTEGER)) {
                    recurseP = NIL;
                } else {
                    recurseP = T;
                    depth = subtract(depth, ONE_INTEGER);
                }
                if (NIL != recurseP) {
                    done_arg_ins = arg_ins;
                    thread.resetMultipleValues();
                    {
                        SubLObject recurse_protoedges = bbf_min_ceilings_forward_true_internal(pred, arg_outs, mt, filter_fns, depth, done_arg_ins);
                        SubLObject recurse_done_arg_ins = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        recurse_protoedges = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        {
                            SubLObject cdolist_list_var = recurse_protoedges;
                            SubLObject recurse_protoedge = NIL;
                            for (recurse_protoedge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , recurse_protoedge = cdolist_list_var.first()) {
                                protoedges = cons(recurse_protoedge, protoedges);
                            }
                        }
                    }
                }
                return values(protoedges, done_arg_ins);
            }
        }
    }

    public static SubLObject bbf_min_ceilings_forward_true_internal(final SubLObject pred, final SubLObject arg_ins, final SubLObject mt, final SubLObject filter_fns, SubLObject depth, SubLObject done_arg_ins) {
        if (done_arg_ins == UNPROVIDED) {
            done_arg_ins = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == arg_ins) || length(arg_ins).numLE(ONE_INTEGER)) {
            return values(NIL, done_arg_ins);
        }
        SubLObject recurseP = NIL;
        SubLObject protoedges = NIL;
        SubLObject arg_outs = NIL;
        if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
            SubLObject arg_in_1 = NIL;
            SubLObject arg_ins_copy = NIL;
            arg_in_1 = arg_ins.first();
            for (arg_ins_copy = arg_ins.rest(); NIL != arg_ins_copy; arg_ins_copy = arg_ins_copy.rest()) {
                SubLObject cdolist_list_var = arg_ins_copy;
                SubLObject arg_in_2 = NIL;
                arg_in_2 = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject local_arg_outs = NIL;
                    local_arg_outs = sbhl_search_methods.sbhl_ceilings(sbhl_module_vars.get_sbhl_module(pred), list(arg_in_1, arg_in_2), NIL, mt, $$True_JustificationTruth);
                    if (NIL != local_arg_outs) {
                        local_arg_outs = blue_apply_filter_fns(local_arg_outs, filter_fns, mt, UNPROVIDED);
                        local_arg_outs = set_difference(local_arg_outs, arg_ins, UNPROVIDED, UNPROVIDED);
                        if (length(local_arg_outs).numG(ONE_INTEGER)) {
                            local_arg_outs = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), local_arg_outs, mt, $$True_JustificationTruth);
                        }
                    }
                    SubLObject cdolist_list_var_$23;
                    local_arg_outs = cdolist_list_var_$23 = blue_apply_filter_fns(local_arg_outs, filter_fns, mt, $POST_MINIMIZATION);
                    SubLObject local_arg_out = NIL;
                    local_arg_out = cdolist_list_var_$23.first();
                    while (NIL != cdolist_list_var_$23) {
                        arg_outs = cons(local_arg_out, arg_outs);
                        protoedges = cons(list(arg_in_1, local_arg_out), protoedges);
                        protoedges = cons(list(arg_in_2, local_arg_out), protoedges);
                        cdolist_list_var_$23 = cdolist_list_var_$23.rest();
                        local_arg_out = cdolist_list_var_$23.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    arg_in_2 = cdolist_list_var.first();
                } 
                arg_in_1 = arg_ins_copy.first();
            }
            protoedges = remove_duplicates(protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
            arg_outs = remove_duplicates(arg_outs, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        if (depth.numE(ONE_INTEGER)) {
            recurseP = NIL;
        } else {
            recurseP = T;
            depth = subtract(depth, ONE_INTEGER);
        }
        if (NIL != recurseP) {
            done_arg_ins = arg_ins;
            thread.resetMultipleValues();
            SubLObject recurse_protoedges = bbf_min_ceilings_forward_true_internal(pred, arg_outs, mt, filter_fns, depth, done_arg_ins);
            final SubLObject recurse_done_arg_ins = thread.secondMultipleValue();
            thread.resetMultipleValues();
            SubLObject cdolist_list_var;
            recurse_protoedges = cdolist_list_var = remove_duplicates(recurse_protoedges, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject recurse_protoedge = NIL;
            recurse_protoedge = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                protoedges = cons(recurse_protoedge, protoedges);
                cdolist_list_var = cdolist_list_var.rest();
                recurse_protoedge = cdolist_list_var.first();
            } 
        }
        return values(protoedges, done_arg_ins);
    }

    public static final SubLObject bbf_min_forward_and_backward_true_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
                SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
                SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
                SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
                SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                thread.resetMultipleValues();
                {
                    SubLObject protoedges = bbf_min_forward_and_backward_true_internal(preds, mt, filter_fns, depth, v_forts);
                    SubLObject protonodes = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    {
                        SubLObject cdolist_list_var = protonodes;
                        SubLObject protonode = NIL;
                        for (protonode = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , protonode = cdolist_list_var.first()) {
                            result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
                        }
                    }
                    {
                        SubLObject cdolist_list_var = protoedges;
                        SubLObject protoedge = NIL;
                        for (protoedge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , protoedge = cdolist_list_var.first()) {
                            result_edges = cons(bbf_make_edge(protoedge.first(), second(protoedge), third(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
                        }
                    }
                    return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
                }
            }
        }
    }

    public static SubLObject bbf_min_forward_and_backward_true(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject preds = getf(arg_plist, $PREDS, UNPROVIDED);
        final SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
        final SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject filter_fns = getf(arg_plist, $FILTER_FNS, UNPROVIDED);
        final SubLObject depth = getf(arg_plist, $DEPTH, UNPROVIDED);
        SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        thread.resetMultipleValues();
        final SubLObject protoedges = bbf_min_forward_and_backward_true_internal(preds, mt, filter_fns, depth, v_forts);
        final SubLObject protonodes = thread.secondMultipleValue();
        thread.resetMultipleValues();
        SubLObject cdolist_list_var = protonodes;
        SubLObject protonode = NIL;
        protonode = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            result_nodes = cons(bbf_make_node(protonode, UNPROVIDED, UNPROVIDED), result_nodes);
            cdolist_list_var = cdolist_list_var.rest();
            protonode = cdolist_list_var.first();
        } 
        cdolist_list_var = protoedges;
        SubLObject protoedge = NIL;
        protoedge = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            result_edges = cons(bbf_make_edge(protoedge.first(), second(protoedge), third(protoedge), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), result_edges);
            cdolist_list_var = cdolist_list_var.rest();
            protoedge = cdolist_list_var.first();
        } 
        return bbf_make_graph(result_nodes, result_edges, list($MT, mt));
    }

    public static final SubLObject bbf_min_forward_and_backward_true_internal_alt(SubLObject preds, SubLObject mt, SubLObject filter_fns, SubLObject depth, SubLObject args) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == args) {
                return values(NIL, NIL);
            }
            {
                SubLObject arg_step_map = list(args);
                thread.resetMultipleValues();
                {
                    SubLObject arg_edge_map = bbf_min_forward_and_backward_true_internal_internal(preds, mt, filter_fns, depth, arg_step_map, UNPROVIDED, UNPROVIDED);
                    SubLObject arg_depth_map = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    {
                        SubLObject protoedges = NIL;
                        SubLObject protonodes = NIL;
                        SubLObject arg_in = NIL;
                        SubLObject pred_arg_outs = NIL;
                        {
                            final Iterator cdohash_iterator = getEntrySetIterator(arg_edge_map);
                            try {
                                while (iteratorHasNext(cdohash_iterator)) {
                                    final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                                    arg_in = getEntryKey(cdohash_entry);
                                    pred_arg_outs = getEntryValue(cdohash_entry);
                                    {
                                        SubLObject cdolist_list_var = pred_arg_outs;
                                        SubLObject pred_arg_out = NIL;
                                        for (pred_arg_out = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pred_arg_out = cdolist_list_var.first()) {
                                            {
                                                SubLObject pred = pred_arg_out.first();
                                                SubLObject arg_out = second(pred_arg_out);
                                                protoedges = cons(list(pred, arg_out, arg_in), protoedges);
                                            }
                                        }
                                    }
                                } 
                            } finally {
                                releaseEntrySetIterator(cdohash_iterator);
                            }
                        }
                        {
                            SubLObject arg = NIL;
                            SubLObject arg_depth = NIL;
                            {
                                final Iterator cdohash_iterator_24 = getEntrySetIterator(arg_depth_map);
                                try {
                                    while (iteratorHasNext(cdohash_iterator_24)) {
                                        final Map.Entry cdohash_entry_25 = iteratorNextEntry(cdohash_iterator_24);
                                        arg = getEntryKey(cdohash_entry_25);
                                        arg_depth = getEntryValue(cdohash_entry_25);
                                        protonodes = cons(list(arg, arg_depth), protonodes);
                                    } 
                                } finally {
                                    releaseEntrySetIterator(cdohash_iterator_24);
                                }
                            }
                        }
                        return values(protoedges, protonodes);
                    }
                }
            }
        }
    }

    public static SubLObject bbf_min_forward_and_backward_true_internal(final SubLObject preds, final SubLObject mt, final SubLObject filter_fns, final SubLObject depth, final SubLObject args) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == args) {
            return values(NIL, NIL);
        }
        final SubLObject arg_step_map = list(args);
        thread.resetMultipleValues();
        final SubLObject arg_edge_map = bbf_min_forward_and_backward_true_internal_internal(preds, mt, filter_fns, depth, arg_step_map, UNPROVIDED, UNPROVIDED);
        final SubLObject arg_depth_map = thread.secondMultipleValue();
        thread.resetMultipleValues();
        SubLObject protoedges = NIL;
        SubLObject protonodes = NIL;
        SubLObject arg_in = NIL;
        SubLObject pred_arg_outs = NIL;
        final Iterator cdohash_iterator = getEntrySetIterator(arg_edge_map);
        try {
            while (iteratorHasNext(cdohash_iterator)) {
                final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                arg_in = getEntryKey(cdohash_entry);
                SubLObject cdolist_list_var;
                pred_arg_outs = cdolist_list_var = getEntryValue(cdohash_entry);
                SubLObject pred_arg_out = NIL;
                pred_arg_out = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject pred = pred_arg_out.first();
                    final SubLObject arg_out = second(pred_arg_out);
                    protoedges = cons(list(pred, arg_out, arg_in), protoedges);
                    cdolist_list_var = cdolist_list_var.rest();
                    pred_arg_out = cdolist_list_var.first();
                } 
            } 
        } finally {
            releaseEntrySetIterator(cdohash_iterator);
        }
        SubLObject arg = NIL;
        SubLObject arg_depth = NIL;
        final Iterator cdohash_iterator_$24 = getEntrySetIterator(arg_depth_map);
        try {
            while (iteratorHasNext(cdohash_iterator_$24)) {
                final Map.Entry cdohash_entry_$25 = iteratorNextEntry(cdohash_iterator_$24);
                arg = getEntryKey(cdohash_entry_$25);
                arg_depth = getEntryValue(cdohash_entry_$25);
                protonodes = cons(list(arg, arg_depth), protonodes);
            } 
        } finally {
            releaseEntrySetIterator(cdohash_iterator_$24);
        }
        return values(protoedges, protonodes);
    }

    public static final SubLObject bbf_min_forward_and_backward_true_internal_internal_alt(SubLObject preds, SubLObject mt, SubLObject filter_fns, SubLObject depth, SubLObject arg_step_map, SubLObject arg_edge_map, SubLObject arg_depth_map) {
        if (arg_edge_map == UNPROVIDED) {
            arg_edge_map = NIL;
        }
        if (arg_depth_map == UNPROVIDED) {
            arg_depth_map = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!arg_edge_map.isHashtable()) {
                arg_edge_map = make_hash_table($int$32, UNPROVIDED, UNPROVIDED);
            }
            if (!arg_depth_map.isHashtable()) {
                arg_depth_map = make_hash_table($int$32, UNPROVIDED, UNPROVIDED);
                {
                    SubLObject cdolist_list_var = arg_step_map.first();
                    SubLObject arg_step = NIL;
                    for (arg_step = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg_step = cdolist_list_var.first()) {
                        sethash(arg_step, arg_depth_map, depth);
                    }
                }
            }
            {
                SubLObject recurseP = NIL;
                SubLObject arg_nexts = NIL;
                SubLObject arg_nexts_list = NIL;
                {
                    SubLObject cdolist_list_var = preds;
                    SubLObject pred = NIL;
                    for (pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pred = cdolist_list_var.first()) {
                        if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
                            {
                                SubLObject args = arg_step_map.first();
                                SubLObject cdolist_list_var_26 = args;
                                SubLObject arg = NIL;
                                for (arg = cdolist_list_var_26.first(); NIL != cdolist_list_var_26; cdolist_list_var_26 = cdolist_list_var_26.rest() , arg = cdolist_list_var_26.first()) {
                                    {
                                        SubLObject arg_outs = NIL;
                                        arg_outs = sbhl_search_methods.sbhl_all_forward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), arg, mt, UNPROVIDED);
                                        if (NIL != arg_outs) {
                                            arg_outs = blue_apply_filter_fns(arg_outs, filter_fns, mt, UNPROVIDED);
                                            if (length(arg_outs).numG(ONE_INTEGER)) {
                                                arg_outs = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), arg_outs, mt, UNPROVIDED);
                                            }
                                        }
                                        {
                                            SubLObject cdolist_list_var_27 = arg_outs;
                                            SubLObject arg_out = NIL;
                                            for (arg_out = cdolist_list_var_27.first(); NIL != cdolist_list_var_27; cdolist_list_var_27 = cdolist_list_var_27.rest() , arg_out = cdolist_list_var_27.first()) {
                                                hash_table_utilities.pushnew_hash(arg, list(pred, arg_out), arg_edge_map, UNPROVIDED);
                                            }
                                        }
                                        arg_nexts_list = cons(arg_outs, arg_nexts_list);
                                    }
                                    {
                                        SubLObject arg_ins = NIL;
                                        arg_ins = sbhl_search_methods.sbhl_all_backward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), arg, mt, UNPROVIDED);
                                        if (NIL != arg_ins) {
                                            arg_ins = blue_apply_filter_fns(arg_ins, filter_fns, mt, UNPROVIDED);
                                            if (length(arg_ins).numG(ONE_INTEGER)) {
                                                arg_ins = sbhl_search_methods.sbhl_max_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), arg_ins, mt, UNPROVIDED, UNPROVIDED);
                                            }
                                        }
                                        {
                                            SubLObject cdolist_list_var_28 = arg_ins;
                                            SubLObject arg_in = NIL;
                                            for (arg_in = cdolist_list_var_28.first(); NIL != cdolist_list_var_28; cdolist_list_var_28 = cdolist_list_var_28.rest() , arg_in = cdolist_list_var_28.first()) {
                                                hash_table_utilities.push_hash(arg_in, list(pred, arg), arg_edge_map);
                                            }
                                        }
                                        arg_nexts_list = cons(arg_ins, arg_nexts_list);
                                    }
                                }
                            }
                        }
                    }
                }
                arg_nexts = remove_duplicates(apply(symbol_function(APPEND), arg_nexts_list), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                arg_nexts = remove_duplicates(arg_nexts, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                arg_nexts = blue_apply_filter_fns(arg_nexts, filter_fns, mt, $POST_MINIMIZATION);
                arg_nexts = set_difference(arg_nexts, apply(symbol_function(APPEND), arg_step_map), UNPROVIDED, UNPROVIDED);
                arg_step_map = cons(arg_nexts, arg_step_map);
                if (depth.numG(ONE_INTEGER)) {
                    recurseP = T;
                }
                depth = subtract(depth, ONE_INTEGER);
                {
                    SubLObject cdolist_list_var = arg_nexts;
                    SubLObject arg_next = NIL;
                    for (arg_next = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg_next = cdolist_list_var.first()) {
                        {
                            SubLObject arg_depth = gethash(arg_next, arg_depth_map, UNPROVIDED);
                            if (!(arg_depth.isInteger() && arg_depth.numG(depth))) {
                                sethash(arg_next, arg_depth_map, depth);
                            }
                        }
                    }
                }
                if (NIL != recurseP) {
                    thread.resetMultipleValues();
                    {
                        SubLObject new_arg_edge_map = bbf_min_forward_and_backward_true_internal_internal(preds, mt, filter_fns, depth, arg_step_map, arg_edge_map, arg_depth_map);
                        SubLObject new_arg_depth_map = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        arg_edge_map = new_arg_edge_map;
                        arg_depth_map = new_arg_depth_map;
                    }
                }
                return values(arg_edge_map, arg_depth_map);
            }
        }
    }

    public static SubLObject bbf_min_forward_and_backward_true_internal_internal(final SubLObject preds, final SubLObject mt, final SubLObject filter_fns, SubLObject depth, SubLObject arg_step_map, SubLObject arg_edge_map, SubLObject arg_depth_map) {
        if (arg_edge_map == UNPROVIDED) {
            arg_edge_map = NIL;
        }
        if (arg_depth_map == UNPROVIDED) {
            arg_depth_map = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (!arg_edge_map.isHashtable()) {
            arg_edge_map = make_hash_table($int$32, UNPROVIDED, UNPROVIDED);
        }
        if (!arg_depth_map.isHashtable()) {
            arg_depth_map = make_hash_table($int$32, UNPROVIDED, UNPROVIDED);
            SubLObject cdolist_list_var = arg_step_map.first();
            SubLObject arg_step = NIL;
            arg_step = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                sethash(arg_step, arg_depth_map, depth);
                cdolist_list_var = cdolist_list_var.rest();
                arg_step = cdolist_list_var.first();
            } 
        }
        SubLObject recurseP = NIL;
        SubLObject arg_nexts = NIL;
        SubLObject arg_nexts_list = NIL;
        SubLObject cdolist_list_var2 = preds;
        SubLObject pred = NIL;
        pred = cdolist_list_var2.first();
        while (NIL != cdolist_list_var2) {
            if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
                SubLObject cdolist_list_var_$26;
                final SubLObject args = cdolist_list_var_$26 = arg_step_map.first();
                SubLObject arg = NIL;
                arg = cdolist_list_var_$26.first();
                while (NIL != cdolist_list_var_$26) {
                    SubLObject arg_outs = NIL;
                    arg_outs = sbhl_search_methods.sbhl_all_forward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), arg, mt, UNPROVIDED);
                    if (NIL != arg_outs) {
                        arg_outs = blue_apply_filter_fns(arg_outs, filter_fns, mt, UNPROVIDED);
                        if (length(arg_outs).numG(ONE_INTEGER)) {
                            arg_outs = sbhl_search_methods.sbhl_min_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), arg_outs, mt, UNPROVIDED);
                        }
                    }
                    SubLObject cdolist_list_var_$27 = arg_outs;
                    SubLObject arg_out = NIL;
                    arg_out = cdolist_list_var_$27.first();
                    while (NIL != cdolist_list_var_$27) {
                        hash_table_utilities.pushnew_hash(arg, list(pred, arg_out), arg_edge_map, UNPROVIDED);
                        cdolist_list_var_$27 = cdolist_list_var_$27.rest();
                        arg_out = cdolist_list_var_$27.first();
                    } 
                    arg_nexts_list = cons(arg_outs, arg_nexts_list);
                    SubLObject arg_ins = NIL;
                    arg_ins = sbhl_search_methods.sbhl_all_backward_true_nodes(sbhl_module_vars.get_sbhl_module(pred), arg, mt, UNPROVIDED);
                    if (NIL != arg_ins) {
                        arg_ins = blue_apply_filter_fns(arg_ins, filter_fns, mt, UNPROVIDED);
                        if (length(arg_ins).numG(ONE_INTEGER)) {
                            arg_ins = sbhl_search_methods.sbhl_max_nodes(sbhl_module_utilities.get_sbhl_reductions_module(sbhl_module_vars.get_sbhl_module(pred)), arg_ins, mt, UNPROVIDED, UNPROVIDED);
                        }
                    }
                    SubLObject cdolist_list_var_$28 = arg_ins;
                    SubLObject arg_in = NIL;
                    arg_in = cdolist_list_var_$28.first();
                    while (NIL != cdolist_list_var_$28) {
                        hash_table_utilities.push_hash(arg_in, list(pred, arg), arg_edge_map);
                        cdolist_list_var_$28 = cdolist_list_var_$28.rest();
                        arg_in = cdolist_list_var_$28.first();
                    } 
                    arg_nexts_list = cons(arg_ins, arg_nexts_list);
                    cdolist_list_var_$26 = cdolist_list_var_$26.rest();
                    arg = cdolist_list_var_$26.first();
                } 
            }
            cdolist_list_var2 = cdolist_list_var2.rest();
            pred = cdolist_list_var2.first();
        } 
        arg_nexts = remove_duplicates(apply(symbol_function(APPEND), arg_nexts_list), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        arg_nexts = remove_duplicates(arg_nexts, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        arg_nexts = blue_apply_filter_fns(arg_nexts, filter_fns, mt, $POST_MINIMIZATION);
        arg_nexts = set_difference(arg_nexts, apply(symbol_function(APPEND), arg_step_map), UNPROVIDED, UNPROVIDED);
        arg_step_map = cons(arg_nexts, arg_step_map);
        if (depth.numG(ONE_INTEGER)) {
            recurseP = T;
        }
        depth = subtract(depth, ONE_INTEGER);
        cdolist_list_var2 = arg_nexts;
        SubLObject arg_next = NIL;
        arg_next = cdolist_list_var2.first();
        while (NIL != cdolist_list_var2) {
            final SubLObject arg_depth = gethash(arg_next, arg_depth_map, UNPROVIDED);
            if ((!arg_depth.isInteger()) || (!arg_depth.numG(depth))) {
                sethash(arg_next, arg_depth_map, depth);
            }
            cdolist_list_var2 = cdolist_list_var2.rest();
            arg_next = cdolist_list_var2.first();
        } 
        if (NIL != recurseP) {
            thread.resetMultipleValues();
            final SubLObject new_arg_edge_map = bbf_min_forward_and_backward_true_internal_internal(preds, mt, filter_fns, depth, arg_step_map, arg_edge_map, arg_depth_map);
            final SubLObject new_arg_depth_map = thread.secondMultipleValue();
            thread.resetMultipleValues();
            arg_edge_map = new_arg_edge_map;
            arg_depth_map = new_arg_depth_map;
        }
        return values(arg_edge_map, arg_depth_map);
    }

    public static final SubLObject bbf_script_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
                SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
                SubLObject script_fort = v_forts.first();
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                if (NIL == mt) {
                    mt = kb_accessors.defining_mt(script_fort);
                }
                if (NIL == mt) {
                    mt = $$EverythingPSC;
                }
                {
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
                                SubLObject cdolist_list_var = isa.all_fort_instances($const64$QuasiTemporalSubSituationTypesPre, UNPROVIDED, UNPROVIDED);
                                SubLObject quasi_temporal_pred = NIL;
                                for (quasi_temporal_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , quasi_temporal_pred = cdolist_list_var.first()) {
                                    {
                                        SubLObject cdolist_list_var_29 = kb_mapping_utilities.pred_value_tuples(script_fort, quasi_temporal_pred, ONE_INTEGER, $list_alt63, UNPROVIDED);
                                        SubLObject scene2_scene1 = NIL;
                                        for (scene2_scene1 = cdolist_list_var_29.first(); NIL != cdolist_list_var_29; cdolist_list_var_29 = cdolist_list_var_29.rest() , scene2_scene1 = cdolist_list_var_29.first()) {
                                            {
                                                SubLObject scene2 = scene2_scene1.first();
                                                SubLObject scene1 = second(scene2_scene1);
                                                SubLObject node2 = bbf_make_node(scene2, $$Situation, UNPROVIDED);
                                                SubLObject node1 = bbf_make_node(scene1, $$Situation, UNPROVIDED);
                                                result_nodes = cons(node1, result_nodes);
                                                result_nodes = cons(node2, result_nodes);
                                                result_edges = cons(bbf_make_edge_with_nodes(quasi_temporal_pred, node1, node2, mt, NIL, UNPROVIDED), result_edges);
                                            }
                                        }
                                    }
                                }
                            }
                            {
                                SubLObject cdolist_list_var = isa.all_fort_instances($const71$SituationTypeRelation_BasicActorT, UNPROVIDED, UNPROVIDED);
                                SubLObject actor_scene_role_pred = NIL;
                                for (actor_scene_role_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , actor_scene_role_pred = cdolist_list_var.first()) {
                                    {
                                        SubLObject cdolist_list_var_30 = kb_mapping_utilities.pred_value_tuples(script_fort, actor_scene_role_pred, ONE_INTEGER, $list_alt70, UNPROVIDED);
                                        SubLObject actor_scene_role = NIL;
                                        for (actor_scene_role = cdolist_list_var_30.first(); NIL != cdolist_list_var_30; cdolist_list_var_30 = cdolist_list_var_30.rest() , actor_scene_role = cdolist_list_var_30.first()) {
                                            {
                                                SubLObject actor = actor_scene_role.first();
                                                SubLObject scene = second(actor_scene_role);
                                                SubLObject role = third(actor_scene_role);
                                                SubLObject tailnode = bbf_make_node(scene, $$Situation, UNPROVIDED);
                                                SubLObject headnode = bbf_make_node(list(actor, role, scene), $$SomethingExisting, list($ACTOR, actor, $ROLE, role, $SCENE, scene));
                                                result_nodes = cons(tailnode, result_nodes);
                                                result_nodes = cons(headnode, result_nodes);
                                                result_edges = cons(bbf_make_edge_with_nodes(role, tailnode, headnode, mt, NIL, list($ORIGINAL_PREDICATE, actor_scene_role_pred)), result_edges);
                                            }
                                        }
                                    }
                                }
                            }
                            {
                                SubLObject actor_scene_role_pred = $const72$individualPlaysRoleInSubSituation;
                                SubLObject headnodes = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
                                SubLObject cdolist_list_var = kb_mapping_utilities.pred_value_tuples(script_fort, actor_scene_role_pred, ONE_INTEGER, $list_alt70, UNPROVIDED);
                                SubLObject actor_scene_role = NIL;
                                for (actor_scene_role = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , actor_scene_role = cdolist_list_var.first()) {
                                    {
                                        SubLObject actor = actor_scene_role.first();
                                        SubLObject scene = second(actor_scene_role);
                                        SubLObject role = third(actor_scene_role);
                                        SubLObject tailnode = bbf_make_node(scene, $$Situation, UNPROVIDED);
                                        SubLObject headnode = dictionary.dictionary_lookup(headnodes, actor, UNPROVIDED);
                                        if (NIL == headnode) {
                                            headnode = bbf_make_node(list(actor, role, scene), $$Individual, list($ACTOR, actor, $ROLE, role, $SCENE, scene));
                                            dictionary.dictionary_enter(headnodes, actor, headnode);
                                        }
                                        result_nodes = cons(tailnode, result_nodes);
                                        result_nodes = cons(headnode, result_nodes);
                                        result_edges = cons(bbf_make_edge_with_nodes(role, tailnode, headnode, mt, NIL, list($ORIGINAL_PREDICATE, actor_scene_role_pred)), result_edges);
                                    }
                                }
                            }
                            {
                                SubLObject cdolist_list_var = isa.all_fort_instances($const74$SituationTypeRelation_SitTypeToSu, UNPROVIDED, UNPROVIDED);
                                SubLObject script_pred = NIL;
                                for (script_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , script_pred = cdolist_list_var.first()) {
                                    {
                                        SubLObject cdolist_list_var_31 = kb_mapping_utilities.pred_values(script_fort, script_pred, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                        SubLObject scene = NIL;
                                        for (scene = cdolist_list_var_31.first(); NIL != cdolist_list_var_31; cdolist_list_var_31 = cdolist_list_var_31.rest() , scene = cdolist_list_var_31.first()) {
                                            result_nodes = cons(bbf_make_node(scene, $$Situation, UNPROVIDED), result_nodes);
                                        }
                                    }
                                }
                            }
                            {
                                SubLObject cdolist_list_var = isa.all_fort_instances_in_all_mts($const76$GraphicallyEditableRoleMappingPre);
                                SubLObject scene_to_scene_actor_relation = NIL;
                                for (scene_to_scene_actor_relation = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , scene_to_scene_actor_relation = cdolist_list_var.first()) {
                                    {
                                        SubLObject cdolist_list_var_32 = kb_mapping_utilities.pred_value_tuples(script_fort, scene_to_scene_actor_relation, ONE_INTEGER, $list_alt75, UNPROVIDED);
                                        SubLObject scene1_role1_scene2_role2_type = NIL;
                                        for (scene1_role1_scene2_role2_type = cdolist_list_var_32.first(); NIL != cdolist_list_var_32; cdolist_list_var_32 = cdolist_list_var_32.rest() , scene1_role1_scene2_role2_type = cdolist_list_var_32.first()) {
                                            {
                                                SubLObject scene1 = scene1_role1_scene2_role2_type.first();
                                                SubLObject role1 = second(scene1_role1_scene2_role2_type);
                                                SubLObject scene2 = third(scene1_role1_scene2_role2_type);
                                                SubLObject role2 = fourth(scene1_role1_scene2_role2_type);
                                                SubLObject type = fifth(scene1_role1_scene2_role2_type);
                                                SubLObject tailnode = bbf_make_node(list(type, role1, scene1), $$SomethingExisting, list($ACTOR, type, $ROLE, role1, $SCENE, scene1));
                                                SubLObject headnode = bbf_make_node(list(type, role2, scene2), $$SomethingExisting, list($ACTOR, type, $ROLE, role2, $SCENE, scene2));
                                                SubLObject edge = bbf_make_edge_with_nodes(scene_to_scene_actor_relation, tailnode, headnode, mt, UNPROVIDED, UNPROVIDED);
                                                result_nodes = cons(tailnode, result_nodes);
                                                result_nodes = cons(headnode, result_nodes);
                                                result_edges = cons(edge, result_edges);
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
                }
                return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), mt == $$EverythingPSC ? ((SubLObject) (NIL)) : list($MT, mt));
            }
        }
    }

    public static SubLObject bbf_script(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_forts = getf(arg_plist, $FORTS, UNPROVIDED);
        SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject script_fort = v_forts.first();
        SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        if (NIL == mt) {
            mt = kb_accessors.defining_mt(script_fort);
        }
        if (NIL == mt) {
            mt = $const58$CurrentWorldDataCollectorMt_NonHo;
        }
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            SubLObject cdolist_list_var = list_utilities.remove_if_not($sym61$TERNARY_PREDICATE_, isa.all_fort_instances($const62$QuasiTemporalSubSituationTypesPre, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject quasi_temporal_pred = NIL;
            quasi_temporal_pred = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cdolist_list_var_$29 = kb_mapping_utilities.pred_value_tuples(script_fort, quasi_temporal_pred, ONE_INTEGER, $list60, UNPROVIDED);
                SubLObject scene2_scene1 = NIL;
                scene2_scene1 = cdolist_list_var_$29.first();
                while (NIL != cdolist_list_var_$29) {
                    final SubLObject scene2 = scene2_scene1.first();
                    final SubLObject scene3 = second(scene2_scene1);
                    final SubLObject node2 = bbf_make_node(scene2, $$Situation, UNPROVIDED);
                    final SubLObject node3 = bbf_make_node(scene3, $$Situation, UNPROVIDED);
                    result_nodes = cons(node3, result_nodes);
                    result_nodes = cons(node2, result_nodes);
                    result_edges = cons(bbf_make_edge_with_nodes(quasi_temporal_pred, node3, node2, mt, NIL, UNPROVIDED), result_edges);
                    cdolist_list_var_$29 = cdolist_list_var_$29.rest();
                    scene2_scene1 = cdolist_list_var_$29.first();
                } 
                cdolist_list_var = cdolist_list_var.rest();
                quasi_temporal_pred = cdolist_list_var.first();
            } 
            cdolist_list_var = isa.all_fort_instances($const69$SituationTypeRelation_BasicActorT, UNPROVIDED, UNPROVIDED);
            SubLObject actor_scene_role_pred = NIL;
            actor_scene_role_pred = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cdolist_list_var_$30 = kb_mapping_utilities.pred_value_tuples(script_fort, actor_scene_role_pred, ONE_INTEGER, $list68, UNPROVIDED);
                SubLObject actor_scene_role = NIL;
                actor_scene_role = cdolist_list_var_$30.first();
                while (NIL != cdolist_list_var_$30) {
                    final SubLObject actor = actor_scene_role.first();
                    final SubLObject scene4 = second(actor_scene_role);
                    final SubLObject role = third(actor_scene_role);
                    final SubLObject tailnode = bbf_make_node(scene4, $$Situation, UNPROVIDED);
                    final SubLObject headnode = bbf_make_node(list(actor, role, scene4), $$SomethingExisting, list($ACTOR, actor, $ROLE, role, $SCENE, scene4));
                    result_nodes = cons(tailnode, result_nodes);
                    result_nodes = cons(headnode, result_nodes);
                    result_edges = cons(bbf_make_edge_with_nodes(role, tailnode, headnode, mt, NIL, list($ORIGINAL_PREDICATE, actor_scene_role_pred)), result_edges);
                    cdolist_list_var_$30 = cdolist_list_var_$30.rest();
                    actor_scene_role = cdolist_list_var_$30.first();
                } 
                cdolist_list_var = cdolist_list_var.rest();
                actor_scene_role_pred = cdolist_list_var.first();
            } 
            final SubLObject actor_scene_role_pred2 = $const70$individualPlaysRoleInSubSituation;
            final SubLObject headnodes = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
            SubLObject cdolist_list_var2 = kb_mapping_utilities.pred_value_tuples(script_fort, actor_scene_role_pred2, ONE_INTEGER, $list68, UNPROVIDED);
            SubLObject actor_scene_role = NIL;
            actor_scene_role = cdolist_list_var2.first();
            while (NIL != cdolist_list_var2) {
                final SubLObject actor = actor_scene_role.first();
                final SubLObject scene4 = second(actor_scene_role);
                final SubLObject role = third(actor_scene_role);
                final SubLObject tailnode = bbf_make_node(scene4, $$Situation, UNPROVIDED);
                SubLObject headnode = dictionary.dictionary_lookup(headnodes, actor, UNPROVIDED);
                if (NIL == headnode) {
                    headnode = bbf_make_node(list(actor, role, scene4), $$Individual, list($ACTOR, actor, $ROLE, role, $SCENE, scene4));
                    dictionary.dictionary_enter(headnodes, actor, headnode);
                }
                result_nodes = cons(tailnode, result_nodes);
                result_nodes = cons(headnode, result_nodes);
                result_edges = cons(bbf_make_edge_with_nodes(role, tailnode, headnode, mt, NIL, list($ORIGINAL_PREDICATE, actor_scene_role_pred2)), result_edges);
                cdolist_list_var2 = cdolist_list_var2.rest();
                actor_scene_role = cdolist_list_var2.first();
            } 
            cdolist_list_var = isa.all_fort_instances($const72$SituationTypeRelation_SitTypeToSu, UNPROVIDED, UNPROVIDED);
            SubLObject script_pred = NIL;
            script_pred = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cdolist_list_var_$31 = kb_mapping_utilities.pred_values(script_fort, script_pred, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                SubLObject scene5 = NIL;
                scene5 = cdolist_list_var_$31.first();
                while (NIL != cdolist_list_var_$31) {
                    result_nodes = cons(bbf_make_node(scene5, $$Situation, UNPROVIDED), result_nodes);
                    cdolist_list_var_$31 = cdolist_list_var_$31.rest();
                    scene5 = cdolist_list_var_$31.first();
                } 
                cdolist_list_var = cdolist_list_var.rest();
                script_pred = cdolist_list_var.first();
            } 
            cdolist_list_var = isa.all_fort_instances_in_all_mts($const74$GraphicallyEditableRoleMappingPre);
            SubLObject scene_to_scene_actor_relation = NIL;
            scene_to_scene_actor_relation = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cdolist_list_var_$32 = kb_mapping_utilities.pred_value_tuples(script_fort, scene_to_scene_actor_relation, ONE_INTEGER, $list73, UNPROVIDED);
                SubLObject scene1_role1_scene2_role2_type = NIL;
                scene1_role1_scene2_role2_type = cdolist_list_var_$32.first();
                while (NIL != cdolist_list_var_$32) {
                    final SubLObject scene6 = scene1_role1_scene2_role2_type.first();
                    final SubLObject role2 = second(scene1_role1_scene2_role2_type);
                    final SubLObject scene7 = third(scene1_role1_scene2_role2_type);
                    final SubLObject role3 = fourth(scene1_role1_scene2_role2_type);
                    final SubLObject type = fifth(scene1_role1_scene2_role2_type);
                    final SubLObject tailnode2 = bbf_make_node(list(type, role2, scene6), $$SomethingExisting, list($ACTOR, type, $ROLE, role2, $SCENE, scene6));
                    final SubLObject headnode2 = bbf_make_node(list(type, role3, scene7), $$SomethingExisting, list($ACTOR, type, $ROLE, role3, $SCENE, scene7));
                    final SubLObject edge = bbf_make_edge_with_nodes(scene_to_scene_actor_relation, tailnode2, headnode2, mt, UNPROVIDED, UNPROVIDED);
                    result_nodes = cons(tailnode2, result_nodes);
                    result_nodes = cons(headnode2, result_nodes);
                    result_edges = cons(edge, result_edges);
                    cdolist_list_var_$32 = cdolist_list_var_$32.rest();
                    scene1_role1_scene2_role2_type = cdolist_list_var_$32.first();
                } 
                cdolist_list_var = cdolist_list_var.rest();
                scene_to_scene_actor_relation = cdolist_list_var.first();
            } 
            SubLObject removed_result_edges = NIL;
            SubLObject cdolist_list_var3 = result_edges;
            SubLObject edge_1 = NIL;
            edge_1 = cdolist_list_var3.first();
            while (NIL != cdolist_list_var3) {
                SubLObject cdolist_list_var_$33 = result_edges;
                SubLObject edge_2 = NIL;
                edge_2 = cdolist_list_var_$33.first();
                while (NIL != cdolist_list_var_$33) {
                    if ((getf(edge_1, $HEAD, UNPROVIDED).equal(getf(edge_2, $HEAD, UNPROVIDED)) && getf(edge_1, $TAIL, UNPROVIDED).equal(getf(edge_2, $TAIL, UNPROVIDED))) && (!getf(edge_1, $RELATION, UNPROVIDED).equal(getf(edge_2, $RELATION, UNPROVIDED)))) {
                        if (NIL != genl_predicates.genl_predicateP(getf(edge_1, $RELATION, UNPROVIDED), getf(edge_2, $RELATION, UNPROVIDED), UNPROVIDED, UNPROVIDED)) {
                            final SubLObject item_var = edge_2;
                            if (NIL == member(item_var, removed_result_edges, EQUAL, symbol_function(IDENTITY))) {
                                removed_result_edges = cons(item_var, removed_result_edges);
                            }
                        } else {
                            final SubLObject item_var = edge_1;
                            if (NIL == member(item_var, removed_result_edges, EQUAL, symbol_function(IDENTITY))) {
                                removed_result_edges = cons(item_var, removed_result_edges);
                            }
                        }
                    }
                    cdolist_list_var_$33 = cdolist_list_var_$33.rest();
                    edge_2 = cdolist_list_var_$33.first();
                } 
                cdolist_list_var3 = cdolist_list_var3.rest();
                edge_1 = cdolist_list_var3.first();
            } 
            SubLObject new_result_edges = NIL;
            cdolist_list_var2 = result_edges;
            SubLObject edge2 = NIL;
            edge2 = cdolist_list_var2.first();
            while (NIL != cdolist_list_var2) {
                if (NIL == subl_promotions.memberP(edge2, removed_result_edges, EQUAL, UNPROVIDED)) {
                    new_result_edges = cons(edge2, new_result_edges);
                }
                cdolist_list_var2 = cdolist_list_var2.rest();
                edge2 = cdolist_list_var2.first();
            } 
            result_edges = new_result_edges;
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), mt.eql($$EverythingPSC) ? NIL : list($MT, mt));
    }

    public static final SubLObject bbf_inference_answers_alt(SubLObject arg_plist) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
                SubLObject inference_store_id = getf(params, $INFERENCE_STORE_ID, UNPROVIDED);
                SubLObject inference_id = getf(params, $INFERENCE_ID, UNPROVIDED);
                SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(inference_store_id, inference_id);
                SubLObject var_var_pred_map = determine_edge_templates_for_inference(inference);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                thread.resetMultipleValues();
                {
                    SubLObject current_answers = cb_query_browser.cb_inference_current_answers(inference);
                    SubLObject free_el_variables = thread.secondMultipleValue();
                    SubLObject inference_new_justification_set = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    {
                        SubLObject cdolist_list_var = current_answers;
                        SubLObject v_answer = NIL;
                        for (v_answer = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , v_answer = cdolist_list_var.first()) {
                            {
                                SubLObject v_bindings = inference_datastructures_inference.inference_answer_bindings(v_answer);
                                SubLObject cdolist_list_var_33 = var_var_pred_map;
                                SubLObject var_var_pred = NIL;
                                for (var_var_pred = cdolist_list_var_33.first(); NIL != cdolist_list_var_33; cdolist_list_var_33 = cdolist_list_var_33.rest() , var_var_pred = cdolist_list_var_33.first()) {
                                    {
                                        SubLObject pred = (NIL != cycl_variables.el_varP(third(var_var_pred))) ? ((SubLObject) (list_utilities.alist_lookup(v_bindings, third(var_var_pred), UNPROVIDED, UNPROVIDED))) : third(var_var_pred);
                                        SubLObject node_in = bbf_make_node(list_utilities.alist_lookup(v_bindings, var_var_pred.first(), UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                                        SubLObject node_out = bbf_make_node(list_utilities.alist_lookup(v_bindings, second(var_var_pred), UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                                        SubLObject mt = fourth(var_var_pred);
                                        SubLObject type = NIL;
                                        SubLObject v_properties = (NIL != fort_types_interface.commutative_relationP(pred)) ? ((SubLObject) (list($DIRECTION, $UNDIRECTED))) : NIL;
                                        result_edges = cons(bbf_make_edge_with_nodes(pred, node_in, node_out, mt, type, v_properties), result_edges);
                                    }
                                }
                            }
                        }
                    }
                }
                return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), NIL != inference_datastructures_inference.inference_mt(inference) ? ((SubLObject) (list($MT, inference_datastructures_inference.inference_mt(inference)))) : NIL);
            }
        }
    }

    public static SubLObject bbf_inference_answers(final SubLObject arg_plist) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
        final SubLObject inference_store_id = getf(params, $INFERENCE_STORE_ID, UNPROVIDED);
        final SubLObject inference_id = getf(params, $INFERENCE_ID, UNPROVIDED);
        final SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(inference_store_id, inference_id);
        final SubLObject var_var_pred_map = determine_edge_templates_for_inference(inference);
        final SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        thread.resetMultipleValues();
        final SubLObject current_answers = cb_query_browser.cb_inference_current_answers(inference);
        final SubLObject free_el_variables = thread.secondMultipleValue();
        final SubLObject inference_new_justification_set = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        SubLObject cdolist_list_var = current_answers;
        SubLObject v_answer = NIL;
        v_answer = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject v_bindings = inference_datastructures_inference.inference_answer_bindings(v_answer);
            SubLObject cdolist_list_var_$34 = var_var_pred_map;
            SubLObject var_var_pred = NIL;
            var_var_pred = cdolist_list_var_$34.first();
            while (NIL != cdolist_list_var_$34) {
                final SubLObject pred = (NIL != cycl_variables.el_varP(third(var_var_pred))) ? list_utilities.alist_lookup(v_bindings, third(var_var_pred), UNPROVIDED, UNPROVIDED) : third(var_var_pred);
                final SubLObject node_in = bbf_make_node(list_utilities.alist_lookup(v_bindings, var_var_pred.first(), UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                final SubLObject node_out = bbf_make_node(list_utilities.alist_lookup(v_bindings, second(var_var_pred), UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED);
                final SubLObject mt = fourth(var_var_pred);
                final SubLObject type = NIL;
                final SubLObject v_properties = (NIL != fort_types_interface.commutative_relationP(pred)) ? list($DIRECTION, $UNDIRECTED) : NIL;
                result_edges = cons(bbf_make_edge_with_nodes(pred, node_in, node_out, mt, type, v_properties), result_edges);
                cdolist_list_var_$34 = cdolist_list_var_$34.rest();
                var_var_pred = cdolist_list_var_$34.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            v_answer = cdolist_list_var.first();
        } 
        return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), NIL != inference_datastructures_inference.inference_mt(inference) ? list($MT, inference_datastructures_inference.inference_mt(inference)) : NIL);
    }

    public static final SubLObject determine_edge_templates_for_inference_alt(SubLObject inference) {
        {
            SubLObject el_bindings = inference_datastructures_inference.inference_el_bindings(inference);
            SubLObject free_el_variables = inference_datastructures_inference.inference_free_el_vars(inference);
            SubLObject var_var_pred_map = NIL;
            {
                SubLObject cdolist_list_var = inference_datastructures_inference.inference_hl_query(inference);
                SubLObject clause = NIL;
                for (clause = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , clause = cdolist_list_var.first()) {
                    {
                        SubLObject cdolist_list_var_34 = clauses.neg_lits(clause);
                        SubLObject lit = NIL;
                        for (lit = cdolist_list_var_34.first(); NIL != cdolist_list_var_34; cdolist_list_var_34 = cdolist_list_var_34.rest() , lit = cdolist_list_var_34.first()) {
                            {
                                SubLObject lit_asent = inference_datastructures_problem_query.contextualized_asent_asent(lit);
                                SubLObject lit_mt = inference_datastructures_problem_query.contextualized_asent_mt(lit);
                                SubLObject pred = lit_asent.first();
                                if ((NIL != forts.fort_p(pred)) && (kb_mapping_utilities.fpred_value_in_any_mt(pred, $$arg1Isa, UNPROVIDED, UNPROVIDED, UNPROVIDED) == $$CycLSentence_Assertible)) {
                                    lit_asent = second(lit_asent);
                                }
                                {
                                    SubLObject list_var = NIL;
                                    SubLObject arg1 = NIL;
                                    SubLObject arg1_pos = NIL;
                                    for (list_var = cycl_utilities.sentence_args(lit_asent, UNPROVIDED), arg1 = list_var.first(), arg1_pos = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , arg1 = list_var.first() , arg1_pos = add(ONE_INTEGER, arg1_pos)) {
                                        {
                                            SubLObject list_var_35 = NIL;
                                            SubLObject arg2 = NIL;
                                            SubLObject arg2_pos = NIL;
                                            for (list_var_35 = cycl_utilities.sentence_args(lit_asent, UNPROVIDED), arg2 = list_var_35.first(), arg2_pos = ZERO_INTEGER; NIL != list_var_35; list_var_35 = list_var_35.rest() , arg2 = list_var_35.first() , arg2_pos = add(ONE_INTEGER, arg2_pos)) {
                                                if ((arg1_pos.numL(arg2_pos) && (NIL != cycl_grammar.hl_variable_p(arg1))) && (NIL != cycl_grammar.hl_variable_p(arg2))) {
                                                    {
                                                        SubLObject el_var1 = list_utilities.alist_reverse_lookup(el_bindings, arg1, UNPROVIDED, UNPROVIDED);
                                                        SubLObject el_var2 = list_utilities.alist_reverse_lookup(el_bindings, arg2, UNPROVIDED, UNPROVIDED);
                                                        if ((NIL != subl_promotions.memberP(el_var1, free_el_variables, UNPROVIDED, UNPROVIDED)) && (NIL != subl_promotions.memberP(el_var2, free_el_variables, UNPROVIDED, UNPROVIDED))) {
                                                            {
                                                                SubLObject pred_36 = (NIL != cycl_grammar.hl_variable_p(cycl_utilities.sentence_arg0(lit_asent))) ? ((SubLObject) (list_utilities.alist_reverse_lookup(el_bindings, cycl_utilities.sentence_arg0(lit_asent), UNPROVIDED, UNPROVIDED))) : cycl_utilities.sentence_arg0(lit_asent);
                                                                if (pred_36 == $$different) {
                                                                    pred_36 = $inference_answers_default_undetermined_relation$.getGlobalValue();
                                                                }
                                                                var_var_pred_map = cons(list(el_var1, el_var2, pred_36, lit_mt), var_var_pred_map);
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
                        SubLObject cdolist_list_var_37 = clauses.pos_lits(clause);
                        SubLObject lit = NIL;
                        for (lit = cdolist_list_var_37.first(); NIL != cdolist_list_var_37; cdolist_list_var_37 = cdolist_list_var_37.rest() , lit = cdolist_list_var_37.first()) {
                            {
                                SubLObject lit_asent = inference_datastructures_problem_query.contextualized_asent_asent(lit);
                                SubLObject lit_mt = inference_datastructures_problem_query.contextualized_asent_mt(lit);
                                SubLObject pred = lit_asent.first();
                                if ((NIL != forts.fort_p(pred)) && (kb_mapping_utilities.fpred_value_in_any_mt(pred, $$arg1Isa, UNPROVIDED, UNPROVIDED, UNPROVIDED) == $$CycLSentence_Assertible)) {
                                    lit_asent = second(lit_asent);
                                }
                                {
                                    SubLObject list_var = NIL;
                                    SubLObject arg1 = NIL;
                                    SubLObject arg1_pos = NIL;
                                    for (list_var = cycl_utilities.sentence_args(lit_asent, UNPROVIDED), arg1 = list_var.first(), arg1_pos = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , arg1 = list_var.first() , arg1_pos = add(ONE_INTEGER, arg1_pos)) {
                                        {
                                            SubLObject list_var_38 = NIL;
                                            SubLObject arg2 = NIL;
                                            SubLObject arg2_pos = NIL;
                                            for (list_var_38 = cycl_utilities.sentence_args(lit_asent, UNPROVIDED), arg2 = list_var_38.first(), arg2_pos = ZERO_INTEGER; NIL != list_var_38; list_var_38 = list_var_38.rest() , arg2 = list_var_38.first() , arg2_pos = add(ONE_INTEGER, arg2_pos)) {
                                                if ((arg1_pos.numL(arg2_pos) && (NIL != cycl_grammar.hl_variable_p(arg1))) && (NIL != cycl_grammar.hl_variable_p(arg2))) {
                                                    {
                                                        SubLObject el_var1 = list_utilities.alist_reverse_lookup(el_bindings, arg1, UNPROVIDED, UNPROVIDED);
                                                        SubLObject el_var2 = list_utilities.alist_reverse_lookup(el_bindings, arg2, UNPROVIDED, UNPROVIDED);
                                                        if ((NIL != subl_promotions.memberP(el_var1, free_el_variables, UNPROVIDED, UNPROVIDED)) && (NIL != subl_promotions.memberP(el_var2, free_el_variables, UNPROVIDED, UNPROVIDED))) {
                                                            {
                                                                SubLObject pred_39 = (NIL != cycl_grammar.hl_variable_p(cycl_utilities.sentence_arg0(lit_asent))) ? ((SubLObject) (list_utilities.alist_reverse_lookup(el_bindings, cycl_utilities.sentence_arg0(lit_asent), UNPROVIDED, UNPROVIDED))) : cycl_utilities.sentence_arg0(lit_asent);
                                                                if (pred_39 == $$different) {
                                                                    pred_39 = $inference_answers_default_undetermined_relation$.getGlobalValue();
                                                                }
                                                                var_var_pred_map = cons(list(el_var1, el_var2, pred_39, lit_mt), var_var_pred_map);
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
            if (NIL == var_var_pred_map) {
                {
                    SubLObject focus_el_var = free_el_variables.first();
                    SubLObject other_el_vars = free_el_variables.rest();
                    SubLObject cdolist_list_var = other_el_vars;
                    SubLObject other_el_var = NIL;
                    for (other_el_var = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , other_el_var = cdolist_list_var.first()) {
                        var_var_pred_map = cons(list(focus_el_var, other_el_var, $inference_answers_default_undetermined_relation$.getGlobalValue(), $$EverythingPSC), var_var_pred_map);
                    }
                }
            }
            return var_var_pred_map;
        }
    }

    public static SubLObject determine_edge_templates_for_inference(final SubLObject inference) {
        final SubLObject el_bindings = inference_datastructures_inference.inference_el_bindings(inference);
        final SubLObject free_el_variables = inference_datastructures_inference.inference_free_el_vars(inference);
        SubLObject var_var_pred_map = NIL;
        SubLObject cdolist_list_var = inference_datastructures_inference.inference_hl_query(inference);
        SubLObject clause = NIL;
        clause = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$35 = clauses.neg_lits(clause);
            SubLObject lit = NIL;
            lit = cdolist_list_var_$35.first();
            while (NIL != cdolist_list_var_$35) {
                SubLObject lit_asent = inference_datastructures_problem_query.contextualized_asent_asent(lit);
                final SubLObject lit_mt = inference_datastructures_problem_query.contextualized_asent_mt(lit);
                final SubLObject pred = lit_asent.first();
                if ((NIL != forts.fort_p(pred)) && kb_mapping_utilities.fpred_value_in_any_mt(pred, $$arg1Isa, UNPROVIDED, UNPROVIDED, UNPROVIDED).eql($$CycLSentence_Assertible)) {
                    lit_asent = second(lit_asent);
                }
                SubLObject list_var = NIL;
                SubLObject arg1 = NIL;
                SubLObject arg1_pos = NIL;
                list_var = cycl_utilities.sentence_args(lit_asent, UNPROVIDED);
                arg1 = list_var.first();
                for (arg1_pos = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , arg1 = list_var.first() , arg1_pos = add(ONE_INTEGER, arg1_pos)) {
                    SubLObject list_var_$36 = NIL;
                    SubLObject arg2 = NIL;
                    SubLObject arg2_pos = NIL;
                    list_var_$36 = cycl_utilities.sentence_args(lit_asent, UNPROVIDED);
                    arg2 = list_var_$36.first();
                    for (arg2_pos = ZERO_INTEGER; NIL != list_var_$36; list_var_$36 = list_var_$36.rest() , arg2 = list_var_$36.first() , arg2_pos = add(ONE_INTEGER, arg2_pos)) {
                        if ((arg1_pos.numL(arg2_pos) && (NIL != cycl_grammar.hl_variable_p(arg1))) && (NIL != cycl_grammar.hl_variable_p(arg2))) {
                            final SubLObject el_var1 = list_utilities.alist_reverse_lookup(el_bindings, arg1, UNPROVIDED, UNPROVIDED);
                            final SubLObject el_var2 = list_utilities.alist_reverse_lookup(el_bindings, arg2, UNPROVIDED, UNPROVIDED);
                            if ((NIL != subl_promotions.memberP(el_var1, free_el_variables, UNPROVIDED, UNPROVIDED)) && (NIL != subl_promotions.memberP(el_var2, free_el_variables, UNPROVIDED, UNPROVIDED))) {
                                SubLObject pred_$37 = (NIL != cycl_grammar.hl_variable_p(cycl_utilities.sentence_arg0(lit_asent))) ? list_utilities.alist_reverse_lookup(el_bindings, cycl_utilities.sentence_arg0(lit_asent), UNPROVIDED, UNPROVIDED) : cycl_utilities.sentence_arg0(lit_asent);
                                if (pred_$37.eql($$different)) {
                                    pred_$37 = $inference_answers_default_undetermined_relation$.getGlobalValue();
                                }
                                var_var_pred_map = cons(list(el_var1, el_var2, pred_$37, lit_mt), var_var_pred_map);
                            }
                        }
                    }
                }
                cdolist_list_var_$35 = cdolist_list_var_$35.rest();
                lit = cdolist_list_var_$35.first();
            } 
            SubLObject cdolist_list_var_$36 = clauses.pos_lits(clause);
            lit = NIL;
            lit = cdolist_list_var_$36.first();
            while (NIL != cdolist_list_var_$36) {
                SubLObject lit_asent = inference_datastructures_problem_query.contextualized_asent_asent(lit);
                final SubLObject lit_mt = inference_datastructures_problem_query.contextualized_asent_mt(lit);
                final SubLObject pred = lit_asent.first();
                if ((NIL != forts.fort_p(pred)) && kb_mapping_utilities.fpred_value_in_any_mt(pred, $$arg1Isa, UNPROVIDED, UNPROVIDED, UNPROVIDED).eql($$CycLSentence_Assertible)) {
                    lit_asent = second(lit_asent);
                }
                SubLObject list_var = NIL;
                SubLObject arg1 = NIL;
                SubLObject arg1_pos = NIL;
                list_var = cycl_utilities.sentence_args(lit_asent, UNPROVIDED);
                arg1 = list_var.first();
                for (arg1_pos = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , arg1 = list_var.first() , arg1_pos = add(ONE_INTEGER, arg1_pos)) {
                    SubLObject list_var_$37 = NIL;
                    SubLObject arg2 = NIL;
                    SubLObject arg2_pos = NIL;
                    list_var_$37 = cycl_utilities.sentence_args(lit_asent, UNPROVIDED);
                    arg2 = list_var_$37.first();
                    for (arg2_pos = ZERO_INTEGER; NIL != list_var_$37; list_var_$37 = list_var_$37.rest() , arg2 = list_var_$37.first() , arg2_pos = add(ONE_INTEGER, arg2_pos)) {
                        if ((arg1_pos.numL(arg2_pos) && (NIL != cycl_grammar.hl_variable_p(arg1))) && (NIL != cycl_grammar.hl_variable_p(arg2))) {
                            final SubLObject el_var1 = list_utilities.alist_reverse_lookup(el_bindings, arg1, UNPROVIDED, UNPROVIDED);
                            final SubLObject el_var2 = list_utilities.alist_reverse_lookup(el_bindings, arg2, UNPROVIDED, UNPROVIDED);
                            if ((NIL != subl_promotions.memberP(el_var1, free_el_variables, UNPROVIDED, UNPROVIDED)) && (NIL != subl_promotions.memberP(el_var2, free_el_variables, UNPROVIDED, UNPROVIDED))) {
                                SubLObject pred_$38 = (NIL != cycl_grammar.hl_variable_p(cycl_utilities.sentence_arg0(lit_asent))) ? list_utilities.alist_reverse_lookup(el_bindings, cycl_utilities.sentence_arg0(lit_asent), UNPROVIDED, UNPROVIDED) : cycl_utilities.sentence_arg0(lit_asent);
                                if (pred_$38.eql($$different)) {
                                    pred_$38 = $inference_answers_default_undetermined_relation$.getGlobalValue();
                                }
                                var_var_pred_map = cons(list(el_var1, el_var2, pred_$38, lit_mt), var_var_pred_map);
                            }
                        }
                    }
                }
                cdolist_list_var_$36 = cdolist_list_var_$36.rest();
                lit = cdolist_list_var_$36.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            clause = cdolist_list_var.first();
        } 
        if (NIL == var_var_pred_map) {
            final SubLObject focus_el_var = free_el_variables.first();
            SubLObject cdolist_list_var2;
            final SubLObject other_el_vars = cdolist_list_var2 = free_el_variables.rest();
            SubLObject other_el_var = NIL;
            other_el_var = cdolist_list_var2.first();
            while (NIL != cdolist_list_var2) {
                var_var_pred_map = cons(list(focus_el_var, other_el_var, $inference_answers_default_undetermined_relation$.getGlobalValue(), $$EverythingPSC), var_var_pred_map);
                cdolist_list_var2 = cdolist_list_var2.rest();
                other_el_var = cdolist_list_var2.first();
            } 
        }
        return var_var_pred_map;
    }

    public static final SubLObject html_determine_edge_templates_for_inference_alt(SubLObject inference) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject var_var_pred_map = determine_edge_templates_for_inference(inference);
                SubLObject el_query = inference_datastructures_inference.inference_el_query(inference);
                html_markup(html_macros.$html_table_head$.getGlobalValue());
                if (true) {
                    html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                if (true) {
                    html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
                    html_char(CHAR_quotation, UNPROVIDED);
                    html_markup(ZERO_INTEGER);
                    html_char(CHAR_quotation, UNPROVIDED);
                }
                html_char(CHAR_greater, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_40 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                                if (true) {
                                    html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($LEFT));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_41 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_princ($$$Query);
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_41, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_42 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_glyph($NBSP, THREE_INTEGER);
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_42, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                                if (true) {
                                    html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($LEFT));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_43 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_princ($$$Edge_Templates);
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_43, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_40, thread);
                            }
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                        html_markup(html_macros.$html_table_row_head$.getGlobalValue());
                        html_char(CHAR_greater, UNPROVIDED);
                        {
                            SubLObject _prev_bind_0_44 = html_macros.$html_safe_print$.currentBinding(thread);
                            try {
                                html_macros.$html_safe_print$.bind(T, thread);
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                if (true) {
                                    html_markup(html_macros.$html_table_data_valign$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($TOP));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_45 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        {
                                            SubLObject size_val = ONE_INTEGER;
                                            html_markup(html_macros.$html_font_head$.getGlobalValue());
                                            if (NIL != size_val) {
                                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(size_val);
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_46 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    cb_form(el_query, ZERO_INTEGER, T);
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_46, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                        }
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_45, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_47 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        html_glyph($NBSP, THREE_INTEGER);
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_47, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                                if (true) {
                                    html_markup(html_macros.$html_table_data_valign$.getGlobalValue());
                                    html_char(CHAR_quotation, UNPROVIDED);
                                    html_markup(html_align($TOP));
                                    html_char(CHAR_quotation, UNPROVIDED);
                                }
                                html_char(CHAR_greater, UNPROVIDED);
                                {
                                    SubLObject _prev_bind_0_48 = html_macros.$html_safe_print$.currentBinding(thread);
                                    try {
                                        html_macros.$html_safe_print$.bind(T, thread);
                                        {
                                            SubLObject size_val = ONE_INTEGER;
                                            html_markup(html_macros.$html_font_head$.getGlobalValue());
                                            if (NIL != size_val) {
                                                html_markup(html_macros.$html_font_size$.getGlobalValue());
                                                html_char(CHAR_quotation, UNPROVIDED);
                                                html_markup(size_val);
                                                html_char(CHAR_quotation, UNPROVIDED);
                                            }
                                            html_char(CHAR_greater, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_49 = html_macros.$html_safe_print$.currentBinding(thread);
                                                try {
                                                    html_macros.$html_safe_print$.bind(T, thread);
                                                    {
                                                        SubLObject cdolist_list_var = var_var_pred_map;
                                                        SubLObject var_var_pred = NIL;
                                                        for (var_var_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , var_var_pred = cdolist_list_var.first()) {
                                                            html_markup(html_macros.$html_no_break_head$.getGlobalValue());
                                                            cb_form(var_var_pred.first(), UNPROVIDED, UNPROVIDED);
                                                            html_princ($str_alt93$_____);
                                                            cb_form(third(var_var_pred), UNPROVIDED, UNPROVIDED);
                                                            if (NIL != fort_types_interface.commutative_relationP(third(var_var_pred))) {
                                                                html_princ($str_alt93$_____);
                                                            } else {
                                                                html_princ($str_alt94$______);
                                                            }
                                                            cb_form(second(var_var_pred), UNPROVIDED, UNPROVIDED);
                                                            html_markup(html_macros.$html_no_break_tail$.getGlobalValue());
                                                            html_br();
                                                        }
                                                    }
                                                } finally {
                                                    html_macros.$html_safe_print$.rebind(_prev_bind_0_49, thread);
                                                }
                                            }
                                            html_markup(html_macros.$html_font_tail$.getGlobalValue());
                                        }
                                    } finally {
                                        html_macros.$html_safe_print$.rebind(_prev_bind_0_48, thread);
                                    }
                                }
                                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                            } finally {
                                html_macros.$html_safe_print$.rebind(_prev_bind_0_44, thread);
                            }
                        }
                        html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
                        html_source_readability_terpri(UNPROVIDED);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
                    }
                }
                html_markup(html_macros.$html_table_tail$.getGlobalValue());
            }
            return NIL;
        }
    }

    public static SubLObject html_determine_edge_templates_for_inference(final SubLObject inference) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject var_var_pred_map = determine_edge_templates_for_inference(inference);
        final SubLObject el_query = inference_datastructures_inference.inference_el_query(inference);
        html_markup(html_macros.$html_table_head$.getGlobalValue());
        html_markup(html_macros.$html_table_cellpadding$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(html_macros.$html_table_cellspacing$.getGlobalValue());
        html_char(CHAR_quotation, UNPROVIDED);
        html_markup(ZERO_INTEGER);
        html_char(CHAR_quotation, UNPROVIDED);
        html_char(CHAR_greater, UNPROVIDED);
        final SubLObject _prev_bind_0 = html_macros.$html_safe_print$.currentBinding(thread);
        try {
            html_macros.$html_safe_print$.bind(T, thread);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$41 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($LEFT));
                html_char(CHAR_quotation, UNPROVIDED);
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$42 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ($$$Query);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$42, thread);
                }
                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$43 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_glyph($NBSP, THREE_INTEGER);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$43, thread);
                }
                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_header_head$.getGlobalValue());
                html_markup(html_macros.$html_table_data_align$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($LEFT));
                html_char(CHAR_quotation, UNPROVIDED);
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$44 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_princ($$$Edge_Templates);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$44, thread);
                }
                html_markup(html_macros.$html_table_header_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$41, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
            html_markup(html_macros.$html_table_row_head$.getGlobalValue());
            html_char(CHAR_greater, UNPROVIDED);
            final SubLObject _prev_bind_0_$45 = html_macros.$html_safe_print$.currentBinding(thread);
            try {
                html_macros.$html_safe_print$.bind(T, thread);
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_markup(html_macros.$html_table_data_valign$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($TOP));
                html_char(CHAR_quotation, UNPROVIDED);
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$46 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    final SubLObject size_val = ONE_INTEGER;
                    html_markup(html_macros.$html_font_head$.getGlobalValue());
                    if (NIL != size_val) {
                        html_markup(html_macros.$html_font_size$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(size_val);
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$47 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        cb_form(el_query, ZERO_INTEGER, T);
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$47, thread);
                    }
                    html_markup(html_macros.$html_font_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$46, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$48 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    html_glyph($NBSP, THREE_INTEGER);
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$48, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
                html_markup(html_macros.$html_table_data_head$.getGlobalValue());
                html_markup(html_macros.$html_table_data_valign$.getGlobalValue());
                html_char(CHAR_quotation, UNPROVIDED);
                html_markup(html_align($TOP));
                html_char(CHAR_quotation, UNPROVIDED);
                html_char(CHAR_greater, UNPROVIDED);
                final SubLObject _prev_bind_0_$49 = html_macros.$html_safe_print$.currentBinding(thread);
                try {
                    html_macros.$html_safe_print$.bind(T, thread);
                    final SubLObject size_val = ONE_INTEGER;
                    html_markup(html_macros.$html_font_head$.getGlobalValue());
                    if (NIL != size_val) {
                        html_markup(html_macros.$html_font_size$.getGlobalValue());
                        html_char(CHAR_quotation, UNPROVIDED);
                        html_markup(size_val);
                        html_char(CHAR_quotation, UNPROVIDED);
                    }
                    html_char(CHAR_greater, UNPROVIDED);
                    final SubLObject _prev_bind_0_$50 = html_macros.$html_safe_print$.currentBinding(thread);
                    try {
                        html_macros.$html_safe_print$.bind(T, thread);
                        SubLObject cdolist_list_var = var_var_pred_map;
                        SubLObject var_var_pred = NIL;
                        var_var_pred = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            html_markup(html_macros.$html_no_break_head$.getGlobalValue());
                            cb_form(var_var_pred.first(), UNPROVIDED, UNPROVIDED);
                            html_princ($str95$_____);
                            cb_form(third(var_var_pred), UNPROVIDED, UNPROVIDED);
                            if (NIL != fort_types_interface.commutative_relationP(third(var_var_pred))) {
                                html_princ($str95$_____);
                            } else {
                                html_princ($str96$______);
                            }
                            cb_form(second(var_var_pred), UNPROVIDED, UNPROVIDED);
                            html_markup(html_macros.$html_no_break_tail$.getGlobalValue());
                            html_br();
                            cdolist_list_var = cdolist_list_var.rest();
                            var_var_pred = cdolist_list_var.first();
                        } 
                    } finally {
                        html_macros.$html_safe_print$.rebind(_prev_bind_0_$50, thread);
                    }
                    html_markup(html_macros.$html_font_tail$.getGlobalValue());
                } finally {
                    html_macros.$html_safe_print$.rebind(_prev_bind_0_$49, thread);
                }
                html_markup(html_macros.$html_table_data_tail$.getGlobalValue());
            } finally {
                html_macros.$html_safe_print$.rebind(_prev_bind_0_$45, thread);
            }
            html_markup(html_macros.$html_table_row_tail$.getGlobalValue());
            html_source_readability_terpri(UNPROVIDED);
        } finally {
            html_macros.$html_safe_print$.rebind(_prev_bind_0, thread);
        }
        html_markup(html_macros.$html_table_tail$.getGlobalValue());
        return NIL;
    }

    public static final SubLObject bbf_inference_alt(SubLObject arg_plist) {
        {
            SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
            SubLObject store_suid = getf(params, $STORE_SUID, UNPROVIDED);
            SubLObject inference_suid = getf(params, $INFERENCE_SUID, UNPROVIDED);
            SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(store_suid, inference_suid);
            if (NIL == inference_datastructures_inference.inference_p(inference)) {
                Errors.error($str_alt99$Inference_not_found_);
            }
            {
                SubLObject inference_mt = inference_datastructures_inference.inference_mt(inference);
                SubLObject hl_query = inference_datastructures_inference.inference_hl_query(inference);
                SubLObject hl_to_el_var_list = make_inference_hl_to_el_var_list(inference);
                SubLObject result_nodes = NIL;
                SubLObject result_edges = NIL;
                SubLObject cdolist_list_var = hl_query;
                SubLObject contextualized_clause = NIL;
                for (contextualized_clause = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , contextualized_clause = cdolist_list_var.first()) {
                    {
                        SubLObject sense = $NEG;
                        SubLObject index_var = ZERO_INTEGER;
                        SubLObject cdolist_list_var_50 = clauses.neg_lits(contextualized_clause);
                        SubLObject contextualized_asent = NIL;
                        for (contextualized_asent = cdolist_list_var_50.first(); NIL != cdolist_list_var_50; cdolist_list_var_50 = cdolist_list_var_50.rest() , contextualized_asent = cdolist_list_var_50.first()) {
                            {
                                SubLObject datum = contextualized_asent;
                                SubLObject current = datum;
                                SubLObject mt = NIL;
                                SubLObject asent = NIL;
                                destructuring_bind_must_consp(current, datum, $list_alt101);
                                mt = current.first();
                                current = current.rest();
                                destructuring_bind_must_consp(current, datum, $list_alt101);
                                asent = current.first();
                                current = current.rest();
                                if (NIL == current) {
                                    result_edges = cons(blue_literal_to_edge(asent, hl_to_el_var_list, mt), result_edges);
                                } else {
                                    cdestructuring_bind_error(datum, $list_alt101);
                                }
                            }
                            index_var = add(index_var, ONE_INTEGER);
                        }
                    }
                    {
                        SubLObject sense = $POS;
                        SubLObject index_var = ZERO_INTEGER;
                        SubLObject cdolist_list_var_51 = clauses.pos_lits(contextualized_clause);
                        SubLObject contextualized_asent = NIL;
                        for (contextualized_asent = cdolist_list_var_51.first(); NIL != cdolist_list_var_51; cdolist_list_var_51 = cdolist_list_var_51.rest() , contextualized_asent = cdolist_list_var_51.first()) {
                            {
                                SubLObject datum = contextualized_asent;
                                SubLObject current = datum;
                                SubLObject mt = NIL;
                                SubLObject asent = NIL;
                                destructuring_bind_must_consp(current, datum, $list_alt101);
                                mt = current.first();
                                current = current.rest();
                                destructuring_bind_must_consp(current, datum, $list_alt101);
                                asent = current.first();
                                current = current.rest();
                                if (NIL == current) {
                                    result_edges = cons(blue_literal_to_edge(asent, hl_to_el_var_list, mt), result_edges);
                                } else {
                                    cdestructuring_bind_error(datum, $list_alt101);
                                }
                            }
                            index_var = add(index_var, ONE_INTEGER);
                        }
                    }
                }
                return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, inference_mt));
            }
        }
    }

    public static SubLObject bbf_inference(final SubLObject arg_plist) {
        final SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
        final SubLObject store_suid = getf(params, $STORE_SUID, UNPROVIDED);
        final SubLObject inference_suid = getf(params, $INFERENCE_SUID, UNPROVIDED);
        final SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(store_suid, inference_suid);
        if (NIL == inference_datastructures_inference.inference_p(inference)) {
            Errors.error($str101$Inference_not_found_);
        }
        final SubLObject inference_mt = inference_datastructures_inference.inference_mt(inference);
        final SubLObject hl_query = inference_datastructures_inference.inference_hl_query(inference);
        final SubLObject hl_to_el_var_list = make_inference_hl_to_el_var_list(inference);
        final SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        SubLObject cdolist_list_var = hl_query;
        SubLObject contextualized_clause = NIL;
        contextualized_clause = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject sense = $NEG;
            SubLObject index_var = ZERO_INTEGER;
            SubLObject cdolist_list_var_$51 = clauses.neg_lits(contextualized_clause);
            SubLObject contextualized_asent = NIL;
            contextualized_asent = cdolist_list_var_$51.first();
            while (NIL != cdolist_list_var_$51) {
                SubLObject current;
                final SubLObject datum = current = contextualized_asent;
                SubLObject mt = NIL;
                SubLObject asent = NIL;
                destructuring_bind_must_consp(current, datum, $list103);
                mt = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list103);
                asent = current.first();
                current = current.rest();
                if (NIL == current) {
                    result_edges = cons(blue_literal_to_edge(asent, hl_to_el_var_list, mt), result_edges);
                } else {
                    cdestructuring_bind_error(datum, $list103);
                }
                index_var = add(index_var, ONE_INTEGER);
                cdolist_list_var_$51 = cdolist_list_var_$51.rest();
                contextualized_asent = cdolist_list_var_$51.first();
            } 
            sense = $POS;
            index_var = ZERO_INTEGER;
            SubLObject cdolist_list_var_$52 = clauses.pos_lits(contextualized_clause);
            contextualized_asent = NIL;
            contextualized_asent = cdolist_list_var_$52.first();
            while (NIL != cdolist_list_var_$52) {
                SubLObject current;
                final SubLObject datum = current = contextualized_asent;
                SubLObject mt = NIL;
                SubLObject asent = NIL;
                destructuring_bind_must_consp(current, datum, $list103);
                mt = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list103);
                asent = current.first();
                current = current.rest();
                if (NIL == current) {
                    result_edges = cons(blue_literal_to_edge(asent, hl_to_el_var_list, mt), result_edges);
                } else {
                    cdestructuring_bind_error(datum, $list103);
                }
                index_var = add(index_var, ONE_INTEGER);
                cdolist_list_var_$52 = cdolist_list_var_$52.rest();
                contextualized_asent = cdolist_list_var_$52.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            contextualized_clause = cdolist_list_var.first();
        } 
        return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, inference_mt));
    }

    public static final SubLObject bbf_rule_alt(SubLObject arg_plist) {
        {
            SubLObject assertions = getf(arg_plist, $ASSERTIONS, UNPROVIDED);
            SubLObject mt = NIL;
            SubLObject result_nodes = NIL;
            SubLObject result_edges = NIL;
            SubLObject cdolist_list_var = assertions;
            SubLObject assertion = NIL;
            for (assertion = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , assertion = cdolist_list_var.first()) {
                mt = assertions_high.assertion_mt(assertion);
                {
                    SubLObject clause = assertions_high.assertion_cnf(assertion);
                    SubLObject neg_lits = clauses.neg_lits(clause);
                    SubLObject pos_lits = clauses.pos_lits(clause);
                    SubLObject hl_to_el_var_list = make_assertion_hl_to_el_var_list(assertion);
                    {
                        SubLObject cdolist_list_var_52 = neg_lits;
                        SubLObject neg_lit = NIL;
                        for (neg_lit = cdolist_list_var_52.first(); NIL != cdolist_list_var_52; cdolist_list_var_52 = cdolist_list_var_52.rest() , neg_lit = cdolist_list_var_52.first()) {
                            result_edges = cons(blue_literal_to_edge(neg_lit, hl_to_el_var_list, mt), result_edges);
                        }
                    }
                    {
                        SubLObject cdolist_list_var_53 = pos_lits;
                        SubLObject pos_lit = NIL;
                        for (pos_lit = cdolist_list_var_53.first(); NIL != cdolist_list_var_53; cdolist_list_var_53 = cdolist_list_var_53.rest() , pos_lit = cdolist_list_var_53.first()) {
                            result_edges = cons(blue_literal_to_edge(pos_lit, hl_to_el_var_list, mt), result_edges);
                        }
                    }
                }
            }
            return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, mt));
        }
    }

    public static SubLObject bbf_rule(final SubLObject arg_plist) {
        final SubLObject assertions = getf(arg_plist, $ASSERTIONS, UNPROVIDED);
        SubLObject mt = NIL;
        final SubLObject result_nodes = NIL;
        SubLObject result_edges = NIL;
        SubLObject cdolist_list_var = assertions;
        SubLObject assertion = NIL;
        assertion = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            mt = assertions_high.assertion_mt(assertion);
            final SubLObject clause = assertions_high.assertion_cnf(assertion);
            final SubLObject neg_lits = clauses.neg_lits(clause);
            final SubLObject pos_lits = clauses.pos_lits(clause);
            final SubLObject hl_to_el_var_list = make_assertion_hl_to_el_var_list(assertion);
            SubLObject cdolist_list_var_$53 = neg_lits;
            SubLObject neg_lit = NIL;
            neg_lit = cdolist_list_var_$53.first();
            while (NIL != cdolist_list_var_$53) {
                result_edges = cons(blue_literal_to_edge(neg_lit, hl_to_el_var_list, mt), result_edges);
                cdolist_list_var_$53 = cdolist_list_var_$53.rest();
                neg_lit = cdolist_list_var_$53.first();
            } 
            SubLObject cdolist_list_var_$54 = pos_lits;
            SubLObject pos_lit = NIL;
            pos_lit = cdolist_list_var_$54.first();
            while (NIL != cdolist_list_var_$54) {
                result_edges = cons(blue_literal_to_edge(pos_lit, hl_to_el_var_list, mt), result_edges);
                cdolist_list_var_$54 = cdolist_list_var_$54.rest();
                pos_lit = cdolist_list_var_$54.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            assertion = cdolist_list_var.first();
        } 
        return bbf_make_graph(list_utilities.hash_remove_duplicates(result_nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list_utilities.hash_remove_duplicates(result_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, mt));
    }

    public static final SubLObject blue_literal_to_edge_alt(SubLObject lit, SubLObject hl_to_el_var_list, SubLObject mt) {
        if (NIL != unary_lit_p(lit)) {
            return NIL;
        }
        {
            SubLObject predicate = cycl_utilities.formula_arg0(lit);
            SubLObject arg1 = literal_arg1(lit, UNPROVIDED);
            SubLObject arg2 = (NIL != binary_lit_p(lit)) ? ((SubLObject) (literal_arg2(lit, UNPROVIDED))) : literal_args(lit, UNPROVIDED).rest();
            SubLObject list_var = NIL;
            SubLObject el_var = NIL;
            SubLObject hl_var_id = NIL;
            for (list_var = hl_to_el_var_list, el_var = list_var.first(), hl_var_id = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , el_var = list_var.first() , hl_var_id = add(ONE_INTEGER, hl_var_id)) {
                {
                    SubLObject hl_var = variables.get_variable(hl_var_id);
                    arg1 = list_utilities.tree_substitute(list(arg1), hl_var, el_var).first();
                    arg2 = list_utilities.tree_substitute(list(arg2), hl_var, el_var).first();
                }
            }
            {
                SubLObject node_in = bbf_make_node(arg1, UNPROVIDED, UNPROVIDED);
                SubLObject node_out = bbf_make_node(arg2, UNPROVIDED, UNPROVIDED);
                return bbf_make_edge_with_nodes(predicate, node_in, node_out, mt, UNPROVIDED, UNPROVIDED);
            }
        }
    }

    public static SubLObject blue_literal_to_edge(final SubLObject lit, final SubLObject hl_to_el_var_list, final SubLObject mt) {
        if (NIL != unary_lit_p(lit)) {
            return NIL;
        }
        final SubLObject predicate = cycl_utilities.formula_arg0(lit);
        SubLObject arg1 = literal_arg1(lit, UNPROVIDED);
        SubLObject arg2 = (NIL != binary_lit_p(lit)) ? literal_arg2(lit, UNPROVIDED) : literal_args(lit, UNPROVIDED).rest();
        SubLObject list_var = NIL;
        SubLObject el_var = NIL;
        SubLObject hl_var_id = NIL;
        list_var = hl_to_el_var_list;
        el_var = list_var.first();
        for (hl_var_id = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , el_var = list_var.first() , hl_var_id = add(ONE_INTEGER, hl_var_id)) {
            final SubLObject hl_var = variables.get_variable(hl_var_id);
            arg1 = list_utilities.tree_substitute(list(arg1), hl_var, el_var).first();
            arg2 = list_utilities.tree_substitute(list(arg2), hl_var, el_var).first();
        }
        final SubLObject node_in = bbf_make_node(arg1, UNPROVIDED, UNPROVIDED);
        final SubLObject node_out = bbf_make_node(arg2, UNPROVIDED, UNPROVIDED);
        return bbf_make_edge_with_nodes(predicate, node_in, node_out, mt, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject make_inference_hl_to_el_var_list_alt(SubLObject inference) {
        {
            SubLObject inference_el_bindings = inference_datastructures_inference.inference_el_bindings(inference);
            SubLObject list_length = ZERO_INTEGER;
            SubLObject result = NIL;
            {
                SubLObject cdolist_list_var = inference_el_bindings;
                SubLObject cons = NIL;
                for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                    {
                        SubLObject datum = cons;
                        SubLObject current = datum;
                        SubLObject el_var = NIL;
                        SubLObject hl_var = NIL;
                        destructuring_bind_must_consp(current, datum, $list_alt106);
                        el_var = current.first();
                        current = current.rest();
                        hl_var = current;
                        {
                            SubLObject hl_var_pos = add(ONE_INTEGER, variables.variable_id(hl_var));
                            if (hl_var_pos.numG(list_length)) {
                                list_length = hl_var_pos;
                            }
                        }
                    }
                }
            }
            result = make_list(list_length, UNPROVIDED);
            {
                SubLObject cdolist_list_var = inference_el_bindings;
                SubLObject cons = NIL;
                for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                    {
                        SubLObject datum = cons;
                        SubLObject current = datum;
                        SubLObject el_var = NIL;
                        SubLObject hl_var = NIL;
                        destructuring_bind_must_consp(current, datum, $list_alt106);
                        el_var = current.first();
                        current = current.rest();
                        hl_var = current;
                        set_nth(variables.variable_id(hl_var), result, el_var);
                    }
                }
            }
            return result;
        }
    }

    public static SubLObject make_inference_hl_to_el_var_list(final SubLObject inference) {
        final SubLObject inference_el_bindings = inference_datastructures_inference.inference_el_bindings(inference);
        SubLObject list_length = ZERO_INTEGER;
        SubLObject result = NIL;
        SubLObject cdolist_list_var = inference_el_bindings;
        SubLObject cons = NIL;
        cons = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = cons;
            SubLObject el_var = NIL;
            SubLObject hl_var = NIL;
            destructuring_bind_must_consp(current, datum, $list108);
            el_var = current.first();
            current = hl_var = current.rest();
            final SubLObject hl_var_pos = add(ONE_INTEGER, variables.variable_id(hl_var));
            if (hl_var_pos.numG(list_length)) {
                list_length = hl_var_pos;
            }
            cdolist_list_var = cdolist_list_var.rest();
            cons = cdolist_list_var.first();
        } 
        result = make_list(list_length, UNPROVIDED);
        cdolist_list_var = inference_el_bindings;
        cons = NIL;
        cons = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = cons;
            SubLObject el_var = NIL;
            SubLObject hl_var = NIL;
            destructuring_bind_must_consp(current, datum, $list108);
            el_var = current.first();
            current = hl_var = current.rest();
            set_nth(variables.variable_id(hl_var), result, el_var);
            cdolist_list_var = cdolist_list_var.rest();
            cons = cdolist_list_var.first();
        } 
        return result;
    }

    public static final SubLObject make_assertion_hl_to_el_var_list_alt(SubLObject assertion) {
        return Mapping.mapcar(INTERN_EL_VAR, assertions_high.assertion_variable_names(assertion));
    }

    public static SubLObject make_assertion_hl_to_el_var_list(final SubLObject assertion) {
        return Mapping.mapcar(INTERN_EL_VAR, assertions_high.assertion_variable_names(assertion));
    }

    public static final SubLObject bbf_make_edge_from_assertion_alt(SubLObject assertion) {
        if (NIL == assertion_handles.assertion_p(assertion)) {
            return NIL;
        }
        {
            SubLObject formula = assertions_high.assertion_formula(assertion);
            return bbf_make_edge(cycl_utilities.formula_arg0(formula), cycl_utilities.formula_arg1(formula, UNPROVIDED), cycl_utilities.formula_arg2(formula, UNPROVIDED), assertions_high.assertion_mt(assertion), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
    }

    public static SubLObject bbf_make_edge_from_assertion(final SubLObject assertion) {
        if (NIL == assertion_handles.assertion_p(assertion)) {
            return NIL;
        }
        final SubLObject formula = assertions_high.assertion_formula(assertion);
        return bbf_make_edge(cycl_utilities.formula_arg0(formula), cycl_utilities.formula_arg1(formula, UNPROVIDED), cycl_utilities.formula_arg2(formula, UNPROVIDED), assertions_high.assertion_mt(assertion), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject bbf_make_graph_alt(SubLObject nodes, SubLObject edges, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        if ((NIL != edges) && (NIL == nodes)) {
            {
                SubLObject cdolist_list_var = edges;
                SubLObject edge = NIL;
                for (edge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , edge = cdolist_list_var.first()) {
                    nodes = cons(getf(edge, $HEAD, UNPROVIDED), nodes);
                    nodes = cons(getf(edge, $TAIL, UNPROVIDED), nodes);
                }
            }
            nodes = list_utilities.hash_remove_duplicates(nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        return list($NODES, nodes, $EDGES, edges, $PROPERTIES, v_properties);
    }

    public static SubLObject bbf_make_graph(SubLObject nodes, final SubLObject edges, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        if ((NIL != edges) && (NIL == nodes)) {
            SubLObject cdolist_list_var = edges;
            SubLObject edge = NIL;
            edge = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                nodes = cons(getf(edge, $HEAD, UNPROVIDED), nodes);
                nodes = cons(getf(edge, $TAIL, UNPROVIDED), nodes);
                cdolist_list_var = cdolist_list_var.rest();
                edge = cdolist_list_var.first();
            } 
            nodes = list_utilities.hash_remove_duplicates(nodes, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        return list($NODES, nodes, $EDGES, edges, $PROPERTIES, v_properties);
    }

    public static final SubLObject bbf_make_edge_alt(SubLObject pred, SubLObject arg_in, SubLObject arg_out, SubLObject mt, SubLObject type, SubLObject arg_in_type, SubLObject arg_out_type, SubLObject v_properties) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (arg_in_type == UNPROVIDED) {
            arg_in_type = NIL;
        }
        if (arg_out_type == UNPROVIDED) {
            arg_out_type = NIL;
        }
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        {
            SubLObject possible_assertion = kb_indexing.find_gaf_genl_mts(list(pred, arg_in, arg_out), mt);
            SubLObject trueP = T;
            SubLObject assertedP = NIL;
            SubLObject strength = $DEFAULT;
            if (NIL != assertion_handles.assertion_p(possible_assertion)) {
                trueP = (assertions_high.assertion_truth(possible_assertion) == $TRUE) ? ((SubLObject) (T)) : NIL;
                assertedP = assertions_high.asserted_assertionP(possible_assertion);
                strength = assertions_high.assertion_strength(possible_assertion);
                mt = assertions_high.assertion_mt(possible_assertion);
            } else {
                if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
                    mt = sbhl_search_what_mts.sbhl_max_floor_mts_of_predicate_paths(sbhl_module_vars.get_sbhl_module(pred), arg_in, arg_out, $$True_JustificationTruth).first();
                }
            }
            return list(new SubLObject[]{ $RELATION, pred, $TAIL, bbf_make_node(arg_in, arg_in_type, UNPROVIDED), $HEAD, bbf_make_node(arg_out, arg_out_type, UNPROVIDED), $MT, mt, $TRUE_, trueP, $ASSERTED_, assertedP, $STRENGTH, strength, $TYPE, type, $PROPERTIES, v_properties });
        }
    }

    public static SubLObject bbf_make_edge(final SubLObject pred, final SubLObject arg_in, final SubLObject arg_out, SubLObject mt, SubLObject type, SubLObject arg_in_type, SubLObject arg_out_type, SubLObject v_properties) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (arg_in_type == UNPROVIDED) {
            arg_in_type = NIL;
        }
        if (arg_out_type == UNPROVIDED) {
            arg_out_type = NIL;
        }
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        final SubLObject possible_assertion = kb_indexing.find_gaf_genl_mts(list(pred, arg_in, arg_out), mt);
        SubLObject trueP = T;
        SubLObject assertedP = NIL;
        SubLObject strength = $DEFAULT;
        if (NIL != assertion_handles.assertion_p(possible_assertion)) {
            trueP = (assertions_high.assertion_truth(possible_assertion) == $TRUE) ? T : NIL;
            assertedP = assertions_high.asserted_assertionP(possible_assertion);
            strength = assertions_high.assertion_strength(possible_assertion);
            mt = assertions_high.assertion_mt(possible_assertion);
        } else
            if (NIL != sbhl_module_utilities.sbhl_predicate_p(pred)) {
                mt = sbhl_search_what_mts.sbhl_max_floor_mts_of_predicate_paths(sbhl_module_vars.get_sbhl_module(pred), arg_in, arg_out, $$True_JustificationTruth).first();
            }

        return list(new SubLObject[]{ $RELATION, pred, $TAIL, bbf_make_node(arg_in, arg_in_type, UNPROVIDED), $HEAD, bbf_make_node(arg_out, arg_out_type, UNPROVIDED), $MT, mt, $TRUE_, trueP, $ASSERTED_, assertedP, $STRENGTH, strength, $TYPE, type, $PROPERTIES, v_properties });
    }

    public static final SubLObject bbf_make_edge_with_nodes_alt(SubLObject pred, SubLObject node_in, SubLObject node_out, SubLObject mt, SubLObject type, SubLObject v_properties) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return list(new SubLObject[]{ $RELATION, pred, $TAIL, node_in, $HEAD, node_out, $MT, mt, $TYPE, type, $PROPERTIES, v_properties });
    }

    public static SubLObject bbf_make_edge_with_nodes(final SubLObject pred, final SubLObject node_in, final SubLObject node_out, final SubLObject mt, SubLObject type, SubLObject v_properties) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return list(new SubLObject[]{ $RELATION, pred, $TAIL, node_in, $HEAD, node_out, $MT, mt, $TYPE, type, $PROPERTIES, v_properties });
    }

    public static final SubLObject bbf_make_node_alt(SubLObject v_core, SubLObject type, SubLObject v_properties) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return list($CORE, v_core, $TYPE, type, $PROPERTIES, v_properties);
    }

    public static SubLObject bbf_make_node(final SubLObject v_core, SubLObject type, SubLObject v_properties) {
        if (type == UNPROVIDED) {
            type = NIL;
        }
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return list($CORE, v_core, $TYPE, type, $PROPERTIES, v_properties);
    }

    public static final SubLObject blue_event_dispatcher_runningP_alt() {
        return event_utilities.generic_event_dispatcher_process_running_for_application_p($blue_event_dispatcher_listeners$);
    }

    public static SubLObject blue_event_dispatcher_runningP() {
        return event_utilities.generic_event_dispatcher_process_running_for_application_p($blue_event_dispatcher_listeners$);
    }

    public static final SubLObject ensure_blue_event_dispatcher_running_alt() {
        if (NIL == blue_event_dispatcher_runningP()) {
            start_blue_event_dispatcher();
        }
        return event_utilities.generic_event_dispatcher_process();
    }

    public static SubLObject ensure_blue_event_dispatcher_running() {
        if (NIL == blue_event_dispatcher_runningP()) {
            start_blue_event_dispatcher();
        }
        return event_utilities.generic_event_dispatcher_process();
    }

    public static final SubLObject register_listener_with_blue_event_dispatcher_alt(SubLObject listener_var) {
        {
            SubLObject lock = $blue_event_dispatcher_lock$.getGlobalValue();
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                if (NIL == find(listener_var, $blue_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                    $blue_event_dispatcher_listeners$.setGlobalValue(cons(listener_var, $blue_event_dispatcher_listeners$.getGlobalValue()));
                    if (NIL != blue_event_dispatcher_runningP()) {
                        event_broker.register_event_listener(listener_var, NIL);
                    }
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return listener_var;
    }

    public static SubLObject register_listener_with_blue_event_dispatcher(final SubLObject listener_var) {
        SubLObject release = NIL;
        try {
            release = seize_lock($blue_event_dispatcher_lock$.getGlobalValue());
            if (NIL == find(listener_var, $blue_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                $blue_event_dispatcher_listeners$.setGlobalValue(cons(listener_var, $blue_event_dispatcher_listeners$.getGlobalValue()));
                if (NIL != blue_event_dispatcher_runningP()) {
                    event_broker.register_event_listener(listener_var, NIL);
                }
            }
        } finally {
            if (NIL != release) {
                release_lock($blue_event_dispatcher_lock$.getGlobalValue());
            }
        }
        return listener_var;
    }

    public static final SubLObject deregister_listener_with_blue_event_dispatcher_alt(SubLObject listener_var) {
        {
            SubLObject lock = $blue_event_dispatcher_lock$.getGlobalValue();
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                $blue_event_dispatcher_listeners$.setGlobalValue(remove(listener_var, $blue_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                if (NIL != blue_event_dispatcher_runningP()) {
                    event_broker.deregister_event_listener(listener_var, NIL);
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return listener_var;
    }

    public static SubLObject deregister_listener_with_blue_event_dispatcher(final SubLObject listener_var) {
        SubLObject release = NIL;
        try {
            release = seize_lock($blue_event_dispatcher_lock$.getGlobalValue());
            $blue_event_dispatcher_listeners$.setGlobalValue(remove(listener_var, $blue_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            if (NIL != blue_event_dispatcher_runningP()) {
                event_broker.deregister_event_listener(listener_var, NIL);
            }
        } finally {
            if (NIL != release) {
                release_lock($blue_event_dispatcher_lock$.getGlobalValue());
            }
        }
        return listener_var;
    }

    public static final SubLObject stop_blue_event_dispatcher_alt() {
        return event_utilities.stop_generic_event_dispatcher_process_for_application($blue_event_dispatcher_listeners$);
    }

    public static SubLObject stop_blue_event_dispatcher() {
        return event_utilities.stop_generic_event_dispatcher_process_for_application($blue_event_dispatcher_listeners$);
    }

    public static final SubLObject start_blue_event_dispatcher_alt() {
        return event_utilities.start_generic_event_dispatcher_process_for_application($blue_event_dispatcher_listeners$);
    }

    public static SubLObject start_blue_event_dispatcher() {
        return event_utilities.start_generic_event_dispatcher_process_for_application($blue_event_dispatcher_listeners$);
    }

    public static final SubLObject blue_event_alt(SubLObject signature, SubLObject blue_event) {
        ensure_blue_event_dispatcher_running();
        {
            SubLObject event = event_model.new_event($BLUE_EVENT, blue_event, signature);
            return event_broker.post_event(event, event_broker.core_event_broker());
        }
    }

    public static SubLObject blue_event(final SubLObject signature, final SubLObject blue_event) {
        ensure_blue_event_dispatcher_running();
        final SubLObject event = event_model.new_event($BLUE_EVENT, blue_event, signature);
        return event_broker.post_event(event, event_broker.core_event_broker());
    }

    public static final SubLObject blue_fetch_event_alt(SubLObject signature) {
        {
            SubLObject message = message_mailboxes.retrieve_mail_from_message_mailbox(signature);
            if (NIL != string_utilities.empty_string_p(message)) {
                message = NIL;
            }
            return message;
        }
    }

    public static SubLObject blue_fetch_event(final SubLObject signature) {
        SubLObject message = message_mailboxes.retrieve_mail_from_message_mailbox(signature);
        if (NIL != string_utilities.empty_string_p(message)) {
            message = NIL;
        }
        return message;
    }

    public static final SubLObject blue_fetch_uia_blue_event_alt(SubLObject signature) {
        return blue_fetch_event(signature);
    }

    public static SubLObject blue_fetch_uia_blue_event(final SubLObject signature) {
        return blue_fetch_event(signature);
    }

    public static final SubLObject blue_current_message_mailbox_suite_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return $blue_message_mailbox_suite$.getDynamicValue(thread);
        }
    }

    public static SubLObject blue_current_message_mailbox_suite() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return $blue_message_mailbox_suite$.getDynamicValue(thread);
    }

    public static final SubLObject blue_ensure_blue_event_address_alt(SubLObject address) {
        {
            SubLObject mbox_suite = blue_current_message_mailbox_suite();
            message_mailboxes.ensure_message_mailbox_allocation(address, mbox_suite);
        }
        return address;
    }

    public static SubLObject blue_ensure_blue_event_address(final SubLObject address) {
        final SubLObject mbox_suite = blue_current_message_mailbox_suite();
        message_mailboxes.ensure_message_mailbox_allocation(address, mbox_suite);
        return address;
    }

    public static final SubLObject map_blue_event_to_mailbox_alt(SubLObject event) {
        {
            SubLObject source = event_model.event_source(event);
            SubLObject message = event_model.event_message(event);
            message_mailboxes.store_mail_in_message_mailbox(source, message, UNPROVIDED);
        }
        return event;
    }

    public static SubLObject map_blue_event_to_mailbox(final SubLObject event) {
        final SubLObject source = event_model.event_source(event);
        final SubLObject message = event_model.event_message(event);
        message_mailboxes.store_mail_in_message_mailbox(source, message, UNPROVIDED);
        return event;
    }

    public static final SubLObject blue_map_event_to_blue_event_alt(SubLObject event, SubLObject params) {
        if (params == UNPROVIDED) {
            params = NIL;
        }
        map_blue_event_to_mailbox(event);
        return event;
    }

    public static SubLObject blue_map_event_to_blue_event(final SubLObject event, SubLObject params) {
        if (params == UNPROVIDED) {
            params = NIL;
        }
        map_blue_event_to_mailbox(event);
        return event;
    }

    public static final SubLObject blue_graph_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject blue_graph_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject blue_graph_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.blue_grapher_utilities.$blue_graph_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject blue_graph_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.blue_grapher_utilities.$blue_graph_native.class ? T : NIL;
    }

    public static final SubLObject blue_graph_isg_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField2();
    }

    public static SubLObject blue_graph_isg(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject blue_graph_node_label_map_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField3();
    }

    public static SubLObject blue_graph_node_label_map(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject blue_graph_nodes_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField4();
    }

    public static SubLObject blue_graph_nodes(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject blue_graph_edge_label_map_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField5();
    }

    public static SubLObject blue_graph_edge_label_map(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject blue_graph_edges_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField6();
    }

    public static SubLObject blue_graph_edges(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject blue_graph_node_definitions_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField7();
    }

    public static SubLObject blue_graph_node_definitions(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject blue_graph_edge_definitions_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField8();
    }

    public static SubLObject blue_graph_edge_definitions(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField8();
    }

    public static final SubLObject blue_graph_options_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.getField9();
    }

    public static SubLObject blue_graph_options(final SubLObject v_object) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.getField9();
    }

    public static final SubLObject _csetf_blue_graph_isg_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_blue_graph_isg(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_blue_graph_node_label_map_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_blue_graph_node_label_map(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_blue_graph_nodes_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_blue_graph_nodes(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_blue_graph_edge_label_map_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_blue_graph_edge_label_map(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_blue_graph_edges_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_blue_graph_edges(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_blue_graph_node_definitions_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_blue_graph_node_definitions(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject _csetf_blue_graph_edge_definitions_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField8(value);
    }

    public static SubLObject _csetf_blue_graph_edge_definitions(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField8(value);
    }

    public static final SubLObject _csetf_blue_graph_options_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, BLUE_GRAPH_P);
        return v_object.setField9(value);
    }

    public static SubLObject _csetf_blue_graph_options(final SubLObject v_object, final SubLObject value) {
        assert NIL != blue_graph_p(v_object) : "! blue_grapher_utilities.blue_graph_p(v_object) " + "blue_grapher_utilities.blue_graph_p error :" + v_object;
        return v_object.setField9(value);
    }

    public static final SubLObject make_blue_graph_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.blue_grapher_utilities.$blue_graph_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($ISG)) {
                        _csetf_blue_graph_isg(v_new, current_value);
                    } else {
                        if (pcase_var.eql($NODE_LABEL_MAP)) {
                            _csetf_blue_graph_node_label_map(v_new, current_value);
                        } else {
                            if (pcase_var.eql($NODES)) {
                                _csetf_blue_graph_nodes(v_new, current_value);
                            } else {
                                if (pcase_var.eql($EDGE_LABEL_MAP)) {
                                    _csetf_blue_graph_edge_label_map(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($EDGES)) {
                                        _csetf_blue_graph_edges(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($NODE_DEFINITIONS)) {
                                            _csetf_blue_graph_node_definitions(v_new, current_value);
                                        } else {
                                            if (pcase_var.eql($EDGE_DEFINITIONS)) {
                                                _csetf_blue_graph_edge_definitions(v_new, current_value);
                                            } else {
                                                if (pcase_var.eql($OPTIONS)) {
                                                    _csetf_blue_graph_options(v_new, current_value);
                                                } else {
                                                    Errors.error($str_alt156$Invalid_slot__S_for_construction_, current_arg);
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
            return v_new;
        }
    }

    public static SubLObject make_blue_graph(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.blue_grapher_utilities.$blue_graph_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($ISG)) {
                _csetf_blue_graph_isg(v_new, current_value);
            } else
                if (pcase_var.eql($NODE_LABEL_MAP)) {
                    _csetf_blue_graph_node_label_map(v_new, current_value);
                } else
                    if (pcase_var.eql($NODES)) {
                        _csetf_blue_graph_nodes(v_new, current_value);
                    } else
                        if (pcase_var.eql($EDGE_LABEL_MAP)) {
                            _csetf_blue_graph_edge_label_map(v_new, current_value);
                        } else
                            if (pcase_var.eql($EDGES)) {
                                _csetf_blue_graph_edges(v_new, current_value);
                            } else
                                if (pcase_var.eql($NODE_DEFINITIONS)) {
                                    _csetf_blue_graph_node_definitions(v_new, current_value);
                                } else
                                    if (pcase_var.eql($EDGE_DEFINITIONS)) {
                                        _csetf_blue_graph_edge_definitions(v_new, current_value);
                                    } else
                                        if (pcase_var.eql($OPTIONS)) {
                                            _csetf_blue_graph_options(v_new, current_value);
                                        } else {
                                            Errors.error($str156$Invalid_slot__S_for_construction_, current_arg);
                                        }







        }
        return v_new;
    }

    public static SubLObject visit_defstruct_blue_graph(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_BLUE_GRAPH, EIGHT_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $ISG, blue_graph_isg(obj));
        funcall(visitor_fn, obj, $SLOT, $NODE_LABEL_MAP, blue_graph_node_label_map(obj));
        funcall(visitor_fn, obj, $SLOT, $NODES, blue_graph_nodes(obj));
        funcall(visitor_fn, obj, $SLOT, $EDGE_LABEL_MAP, blue_graph_edge_label_map(obj));
        funcall(visitor_fn, obj, $SLOT, $EDGES, blue_graph_edges(obj));
        funcall(visitor_fn, obj, $SLOT, $NODE_DEFINITIONS, blue_graph_node_definitions(obj));
        funcall(visitor_fn, obj, $SLOT, $EDGE_DEFINITIONS, blue_graph_edge_definitions(obj));
        funcall(visitor_fn, obj, $SLOT, $OPTIONS, blue_graph_options(obj));
        funcall(visitor_fn, obj, $END, MAKE_BLUE_GRAPH, EIGHT_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_blue_graph_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_blue_graph(obj, visitor_fn);
    }

    public static final SubLObject new_blue_graph_alt() {
        {
            SubLObject blue_graph = make_blue_graph(UNPROVIDED);
            _csetf_blue_graph_isg(blue_graph, integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED));
            _csetf_blue_graph_node_label_map(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            _csetf_blue_graph_edge_label_map(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            _csetf_blue_graph_edges(blue_graph, NIL);
            _csetf_blue_graph_nodes(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            _csetf_blue_graph_node_definitions(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            _csetf_blue_graph_edge_definitions(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            _csetf_blue_graph_options(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            return blue_graph;
        }
    }

    public static SubLObject new_blue_graph() {
        final SubLObject blue_graph = make_blue_graph(UNPROVIDED);
        _csetf_blue_graph_isg(blue_graph, integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED));
        _csetf_blue_graph_node_label_map(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        _csetf_blue_graph_edge_label_map(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        _csetf_blue_graph_edges(blue_graph, NIL);
        _csetf_blue_graph_nodes(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        _csetf_blue_graph_node_definitions(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        _csetf_blue_graph_edge_definitions(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        _csetf_blue_graph_options(blue_graph, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        return blue_graph;
    }

    public static final SubLObject blue_graph_add_node_type_alt(SubLObject blue_graph, SubLObject type, SubLObject color) {
        {
            SubLObject node_definitions = blue_graph_node_definitions(blue_graph);
            dictionary.dictionary_enter(node_definitions, type, list($COLOR, color));
            return blue_graph;
        }
    }

    public static SubLObject blue_graph_add_node_type(final SubLObject blue_graph, final SubLObject type, final SubLObject color) {
        final SubLObject node_definitions = blue_graph_node_definitions(blue_graph);
        dictionary.dictionary_enter(node_definitions, type, list($COLOR, color));
        return blue_graph;
    }

    public static final SubLObject blue_graph_add_edge_type_alt(SubLObject blue_graph, SubLObject type, SubLObject color, SubLObject orientation) {
        if (orientation == UNPROVIDED) {
            orientation = $DEFAULT;
        }
        {
            SubLObject edge_definitions = blue_graph_edge_definitions(blue_graph);
            dictionary.dictionary_enter(edge_definitions, type, list($COLOR, color, $ORIENTATION, orientation));
            return blue_graph;
        }
    }

    public static SubLObject blue_graph_add_edge_type(final SubLObject blue_graph, final SubLObject type, final SubLObject color, SubLObject orientation) {
        if (orientation == UNPROVIDED) {
            orientation = $DEFAULT;
        }
        final SubLObject edge_definitions = blue_graph_edge_definitions(blue_graph);
        dictionary.dictionary_enter(edge_definitions, type, list($COLOR, color, $ORIENTATION, orientation));
        return blue_graph;
    }

    public static final SubLObject blue_graph_add_option_alt(SubLObject blue_graph, SubLObject option, SubLObject plist) {
        {
            SubLObject options = blue_graph_options(blue_graph);
            dictionary.dictionary_enter(options, option, plist);
            return blue_graph;
        }
    }

    public static SubLObject blue_graph_add_option(final SubLObject blue_graph, final SubLObject option, final SubLObject plist) {
        final SubLObject options = blue_graph_options(blue_graph);
        dictionary.dictionary_enter(options, option, plist);
        return blue_graph;
    }

    public static final SubLObject blue_graph_add_node_alt(SubLObject blue_graph, SubLObject type, SubLObject label) {
        if (type == UNPROVIDED) {
            type = $DEFAULT;
        }
        if (label == UNPROVIDED) {
            label = $str_alt159$;
        }
        {
            SubLObject new_id = blue_graph_next_id(blue_graph);
            SubLObject nodes = blue_graph_nodes(blue_graph);
            SubLObject node_label_map = blue_graph_node_label_map(blue_graph);
            SubLObject node = list($ID, new_id, $TYPE, type, $LABEL, label);
            dictionary.dictionary_enter(nodes, new_id, node);
            if (NIL == string_utilities.empty_string_p(label)) {
                dictionary.dictionary_enter(node_label_map, label, node);
            }
        }
        return blue_graph;
    }

    public static SubLObject blue_graph_add_node(final SubLObject blue_graph, SubLObject type, SubLObject label) {
        if (type == UNPROVIDED) {
            type = $DEFAULT;
        }
        if (label == UNPROVIDED) {
            label = $str164$;
        }
        final SubLObject new_id = blue_graph_next_id(blue_graph);
        final SubLObject nodes = blue_graph_nodes(blue_graph);
        final SubLObject node_label_map = blue_graph_node_label_map(blue_graph);
        final SubLObject node = list($ID, new_id, $TYPE, type, $LABEL, label);
        dictionary.dictionary_enter(nodes, new_id, node);
        if (NIL == string_utilities.empty_string_p(label)) {
            dictionary.dictionary_enter(node_label_map, label, node);
        }
        return blue_graph;
    }

    public static final SubLObject blue_graph_node_p_alt(SubLObject node) {
        return makeBoolean(node.isList() && blue_graph_node_get_id(node).isFixnum());
    }

    public static SubLObject blue_graph_node_p(final SubLObject node) {
        return makeBoolean(node.isList() && blue_graph_node_get_id(node).isFixnum());
    }

    public static final SubLObject blue_graph_maybe_add_node_alt(SubLObject blue_graph, SubLObject label, SubLObject type) {
        if (type == UNPROVIDED) {
            type = $DEFAULT;
        }
        if (NIL == blue_graph_node_p(blue_graph_get_node_by_label(blue_graph, label))) {
            blue_graph_add_node(blue_graph, type, label);
        }
        return blue_graph;
    }

    public static SubLObject blue_graph_maybe_add_node(final SubLObject blue_graph, final SubLObject label, SubLObject type) {
        if (type == UNPROVIDED) {
            type = $DEFAULT;
        }
        if (NIL == blue_graph_node_p(blue_graph_get_node_by_label(blue_graph, label))) {
            blue_graph_add_node(blue_graph, type, label);
        }
        return blue_graph;
    }

    public static final SubLObject blue_graph_node_get_id_alt(SubLObject node) {
        return getf(node, $ID, UNPROVIDED);
    }

    public static SubLObject blue_graph_node_get_id(final SubLObject node) {
        return getf(node, $ID, UNPROVIDED);
    }

    public static final SubLObject blue_graph_node_get_type_alt(SubLObject node) {
        return getf(node, $TYPE, $DEFAULT);
    }

    public static SubLObject blue_graph_node_get_type(final SubLObject node) {
        return getf(node, $TYPE, $DEFAULT);
    }

    public static final SubLObject blue_graph_node_get_label_alt(SubLObject node) {
        return getf(node, $LABEL, $str_alt159$);
    }

    public static SubLObject blue_graph_node_get_label(final SubLObject node) {
        return getf(node, $LABEL, $str164$);
    }

    public static final SubLObject blue_graph_get_node_alt(SubLObject blue_graph, SubLObject id) {
        {
            SubLObject nodes = blue_graph_nodes(blue_graph);
            return dictionary.dictionary_lookup(nodes, id, UNPROVIDED);
        }
    }

    public static SubLObject blue_graph_get_node(final SubLObject blue_graph, final SubLObject id) {
        final SubLObject nodes = blue_graph_nodes(blue_graph);
        return dictionary.dictionary_lookup(nodes, id, UNPROVIDED);
    }

    public static final SubLObject blue_graph_get_node_by_label_alt(SubLObject blue_graph, SubLObject label) {
        {
            SubLObject node_label_map = blue_graph_node_label_map(blue_graph);
            return dictionary.dictionary_lookup(node_label_map, label, UNPROVIDED);
        }
    }

    public static SubLObject blue_graph_get_node_by_label(final SubLObject blue_graph, final SubLObject label) {
        final SubLObject node_label_map = blue_graph_node_label_map(blue_graph);
        return dictionary.dictionary_lookup(node_label_map, label, UNPROVIDED);
    }

    public static final SubLObject blue_graph_resolve_node_label_reference_alt(SubLObject blue_graph, SubLObject label) {
        {
            SubLObject node = blue_graph_get_node_by_label(blue_graph, label);
            return blue_graph_node_get_id(node);
        }
    }

    public static SubLObject blue_graph_resolve_node_label_reference(final SubLObject blue_graph, final SubLObject label) {
        final SubLObject node = blue_graph_get_node_by_label(blue_graph, label);
        return blue_graph_node_get_id(node);
    }

    public static final SubLObject blue_graph_edge_from_alt(SubLObject edge) {
        return getf(edge, $FROM, UNPROVIDED);
    }

    public static SubLObject blue_graph_edge_from(final SubLObject edge) {
        return getf(edge, $FROM, UNPROVIDED);
    }

    public static final SubLObject blue_graph_edge_to_alt(SubLObject edge) {
        return getf(edge, $TO, UNPROVIDED);
    }

    public static SubLObject blue_graph_edge_to(final SubLObject edge) {
        return getf(edge, $TO, UNPROVIDED);
    }

    public static final SubLObject blue_graph_edge_get_type_alt(SubLObject edge) {
        return getf(edge, $TYPE, $DEFAULT);
    }

    public static SubLObject blue_graph_edge_get_type(final SubLObject edge) {
        return getf(edge, $TYPE, $DEFAULT);
    }

    public static final SubLObject blue_graph_edge_get_label_alt(SubLObject edge) {
        return getf(edge, $LABEL, $str_alt159$);
    }

    public static SubLObject blue_graph_edge_get_label(final SubLObject edge) {
        return getf(edge, $LABEL, $str164$);
    }

    public static final SubLObject blue_graph_add_edge_alt(SubLObject blue_graph, SubLObject from, SubLObject to, SubLObject type, SubLObject label) {
        if (type == UNPROVIDED) {
            type = $DEFAULT;
        }
        if (label == UNPROVIDED) {
            label = $str_alt159$;
        }
        {
            SubLObject edges = blue_graph_edges(blue_graph);
            SubLObject edge_label_map = blue_graph_edge_label_map(blue_graph);
            SubLObject edge = NIL;
            if (from.isString()) {
                from = blue_graph_resolve_node_label_reference(blue_graph, from);
            }
            if (to.isString()) {
                to = blue_graph_resolve_node_label_reference(blue_graph, to);
            }
            edge = list($FROM, from, $TO, to, $TYPE, type, $LABEL, label);
            edges = cons(edge, edges);
            _csetf_blue_graph_edges(blue_graph, edges);
            if (NIL == string_utilities.empty_string_p(label)) {
                dictionary_utilities.dictionary_push(edge_label_map, label, edge);
            }
        }
        return blue_graph;
    }

    public static SubLObject blue_graph_add_edge(final SubLObject blue_graph, SubLObject from, SubLObject to, SubLObject type, SubLObject label) {
        if (type == UNPROVIDED) {
            type = $DEFAULT;
        }
        if (label == UNPROVIDED) {
            label = $str164$;
        }
        SubLObject edges = blue_graph_edges(blue_graph);
        final SubLObject edge_label_map = blue_graph_edge_label_map(blue_graph);
        SubLObject edge = NIL;
        if (from.isString()) {
            from = blue_graph_resolve_node_label_reference(blue_graph, from);
        }
        if (to.isString()) {
            to = blue_graph_resolve_node_label_reference(blue_graph, to);
        }
        edge = list($FROM, from, $TO, to, $TYPE, type, $LABEL, label);
        edges = cons(edge, edges);
        _csetf_blue_graph_edges(blue_graph, edges);
        if (NIL == string_utilities.empty_string_p(label)) {
            dictionary_utilities.dictionary_push(edge_label_map, label, edge);
        }
        return blue_graph;
    }

    public static final SubLObject blue_graph_next_id_alt(SubLObject blue_graph) {
        {
            SubLObject isg = blue_graph_isg(blue_graph);
            return integer_sequence_generator.integer_sequence_generator_next(isg);
        }
    }

    public static SubLObject blue_graph_next_id(final SubLObject blue_graph) {
        final SubLObject isg = blue_graph_isg(blue_graph);
        return integer_sequence_generator.integer_sequence_generator_next(isg);
    }

    public static final SubLObject blue_graph_generate_description_alt(SubLObject blue_graph) {
        return listS(blue_graph_generate_definition_description(blue_graph), blue_graph_generate_option_description(blue_graph), append(blue_graph_generate_node_description(blue_graph), blue_graph_generate_edge_description(blue_graph), NIL));
    }

    public static SubLObject blue_graph_generate_description(final SubLObject blue_graph) {
        return listS(blue_graph_generate_definition_description(blue_graph), blue_graph_generate_option_description(blue_graph), append(blue_graph_generate_node_description(blue_graph), blue_graph_generate_edge_description(blue_graph), NIL));
    }

    public static final SubLObject blue_graph_generate_definition_description_alt(SubLObject blue_graph) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject definitions = NIL;
                {
                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_node_definitions(blue_graph)));
                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                        thread.resetMultipleValues();
                        {
                            SubLObject type = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                            SubLObject definition = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            definitions = cons(listS($NODE_TYPE, type, append(definition, NIL)), definitions);
                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                        }
                    } 
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                }
                {
                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_edge_definitions(blue_graph)));
                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                        thread.resetMultipleValues();
                        {
                            SubLObject type = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                            SubLObject definition = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            definitions = cons(listS($EDGE_TYPE, type, append(definition, NIL)), definitions);
                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                        }
                    } 
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                }
                return list($DEFINITIONS, definitions);
            }
        }
    }

    public static SubLObject blue_graph_generate_definition_description(final SubLObject blue_graph) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject definitions = NIL;
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_node_definitions(blue_graph))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject type = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject definition = thread.secondMultipleValue();
            thread.resetMultipleValues();
            definitions = cons(listS($NODE_TYPE, type, append(definition, NIL)), definitions);
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_edge_definitions(blue_graph))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject type = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject definition = thread.secondMultipleValue();
            thread.resetMultipleValues();
            definitions = cons(listS($EDGE_TYPE, type, append(definition, NIL)), definitions);
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return list($DEFINITIONS, definitions);
    }

    public static final SubLObject blue_graph_generate_option_description_alt(SubLObject blue_graph) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject options = NIL;
                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_options(blue_graph)));
                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject option = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                        SubLObject value = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        options = cons(list(option, value), options);
                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                    }
                } 
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                return list($OPTIONS, options);
            }
        }
    }

    public static SubLObject blue_graph_generate_option_description(final SubLObject blue_graph) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject options = NIL;
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_options(blue_graph))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject option = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject value = thread.secondMultipleValue();
            thread.resetMultipleValues();
            options = cons(list(option, value), options);
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return list($OPTIONS, options);
    }

    public static final SubLObject blue_graph_generate_node_description_alt(SubLObject blue_graph) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject nodes = NIL;
                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_nodes(blue_graph)));
                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject id = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                        SubLObject node = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        nodes = cons(list($NODE, node), nodes);
                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                    }
                } 
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                return nodes;
            }
        }
    }

    public static SubLObject blue_graph_generate_node_description(final SubLObject blue_graph) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject nodes = NIL;
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(blue_graph_nodes(blue_graph))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject id = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject node = thread.secondMultipleValue();
            thread.resetMultipleValues();
            nodes = cons(list($NODE, node), nodes);
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return nodes;
    }

    public static final SubLObject blue_graph_generate_edge_description_alt(SubLObject blue_graph) {
        {
            SubLObject edges = NIL;
            SubLObject cdolist_list_var = blue_graph_edges(blue_graph);
            SubLObject edge = NIL;
            for (edge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , edge = cdolist_list_var.first()) {
                edges = cons(list($EDGE, edge), edges);
            }
            return edges;
        }
    }

    public static SubLObject blue_graph_generate_edge_description(final SubLObject blue_graph) {
        SubLObject edges = NIL;
        SubLObject cdolist_list_var = blue_graph_edges(blue_graph);
        SubLObject edge = NIL;
        edge = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            edges = cons(list($EDGE, edge), edges);
            cdolist_list_var = cdolist_list_var.rest();
            edge = cdolist_list_var.first();
        } 
        return edges;
    }

    public static final SubLObject bbf_rtv_from_inference_alt(SubLObject arg_plist) {
        {
            SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
            SubLObject formula = getf(params, $FORMULA, UNPROVIDED);
            SubLObject problem_store_id = getf(params, $PROBLEM_STORE_ID, UNPROVIDED);
            SubLObject inference_id = getf(params, $INFERENCE_ID, UNPROVIDED);
            SubLObject rtv_clause = list_utilities.tree_find_if(RTV_CLAUSE_P, formula, UNPROVIDED);
            SubLObject rtv_preds_list = fourth(rtv_clause);
            SubLObject all_rtv_relevant_preds_set = NIL;
            SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(problem_store_id, inference_id);
            SubLObject mt = inference_datastructures_inference.inference_mt(inference);
            SubLObject bbf_edges = NIL;
            all_rtv_relevant_preds_set = set_utilities.construct_set_from_list(rtv_preds_list.rest(), UNPROVIDED, UNPROVIDED);
            {
                SubLObject cdolist_list_var = rtv_preds_list;
                SubLObject each_pred = NIL;
                for (each_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , each_pred = cdolist_list_var.first()) {
                    set_utilities.set_add_all(all_spec_preds(each_pred, mt, UNPROVIDED), all_rtv_relevant_preds_set);
                }
            }
            {
                SubLObject idx = inference_datastructures_inference.inference_answer_id_index(inference);
                if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                    {
                        SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                        SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                        SubLObject inference_answer = NIL;
                        while (NIL != id) {
                            inference_answer = do_id_index_state_object(idx, $SKIP, id, state_var);
                            if (NIL != do_id_index_id_and_object_validP(id, inference_answer, $SKIP)) {
                                {
                                    SubLObject proofs = pph_proof.inference_answer_get_proofs(inference_answer);
                                    SubLObject cdolist_list_var = proofs;
                                    SubLObject proof = NIL;
                                    for (proof = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , proof = cdolist_list_var.first()) {
                                        {
                                            SubLObject v_proof_view = proof_view.construct_inference_proof_view(proof, inference_answer, mt, UNPROVIDED);
                                            SubLObject facts_used = proof_view.proof_view_get_facts_used(v_proof_view);
                                            SubLObject cdolist_list_var_54 = facts_used;
                                            SubLObject fact = NIL;
                                            for (fact = cdolist_list_var_54.first(); NIL != cdolist_list_var_54; cdolist_list_var_54 = cdolist_list_var_54.rest() , fact = cdolist_list_var_54.first()) {
                                                if (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(fact), all_rtv_relevant_preds_set)) {
                                                    bbf_edges = cons(bbf_make_edge_from_assertion(fact), bbf_edges);
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
            return bbf_make_graph(NIL, list_utilities.hash_remove_duplicates(bbf_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, mt));
        }
    }

    public static SubLObject bbf_rtv_from_inference(final SubLObject arg_plist) {
        final SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
        final SubLObject formula = getf(params, $FORMULA, UNPROVIDED);
        final SubLObject problem_store_id = getf(params, $PROBLEM_STORE_ID, UNPROVIDED);
        final SubLObject inference_id = getf(params, $INFERENCE_ID, UNPROVIDED);
        final SubLObject rtv_clause = list_utilities.tree_find_if(RTV_CLAUSE_P, formula, UNPROVIDED);
        final SubLObject rtv_preds_list = fourth(rtv_clause);
        SubLObject all_rtv_relevant_preds_set = NIL;
        final SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(problem_store_id, inference_id);
        final SubLObject mt = inference_datastructures_inference.inference_mt(inference);
        SubLObject bbf_edges = NIL;
        all_rtv_relevant_preds_set = set_utilities.construct_set_from_list(rtv_preds_list.rest(), UNPROVIDED, UNPROVIDED);
        SubLObject cdolist_list_var = rtv_preds_list;
        SubLObject each_pred = NIL;
        each_pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            set_utilities.set_add_all(genl_predicates.all_spec_preds(each_pred, mt, UNPROVIDED), all_rtv_relevant_preds_set);
            cdolist_list_var = cdolist_list_var.rest();
            each_pred = cdolist_list_var.first();
        } 
        final SubLObject idx = inference_datastructures_inference.inference_answer_id_index(inference);
        if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
            final SubLObject idx_$55 = idx;
            if (NIL == id_index_dense_objects_empty_p(idx_$55, $SKIP)) {
                final SubLObject vector_var = id_index_dense_objects(idx_$55);
                final SubLObject backwardP_var = NIL;
                SubLObject length;
                SubLObject v_iteration;
                SubLObject id;
                SubLObject inference_answer;
                SubLObject cdolist_list_var2;
                SubLObject proofs;
                SubLObject proof;
                SubLObject v_proof_view;
                SubLObject cdolist_list_var_$56;
                SubLObject facts_used;
                SubLObject fact;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    inference_answer = aref(vector_var, id);
                    if ((NIL == id_index_tombstone_p(inference_answer)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                        if (NIL != id_index_tombstone_p(inference_answer)) {
                            inference_answer = $SKIP;
                        }
                        proofs = cdolist_list_var2 = pph_proof.inference_answer_get_proofs(inference_answer);
                        proof = NIL;
                        proof = cdolist_list_var2.first();
                        while (NIL != cdolist_list_var2) {
                            v_proof_view = proof_view.construct_inference_proof_view(proof, inference_answer, mt);
                            facts_used = cdolist_list_var_$56 = proof_view.proof_view_get_facts_used(v_proof_view);
                            fact = NIL;
                            fact = cdolist_list_var_$56.first();
                            while (NIL != cdolist_list_var_$56) {
                                if (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(fact), all_rtv_relevant_preds_set)) {
                                    bbf_edges = cons(bbf_make_edge_from_assertion(fact), bbf_edges);
                                }
                                cdolist_list_var_$56 = cdolist_list_var_$56.rest();
                                fact = cdolist_list_var_$56.first();
                            } 
                            cdolist_list_var2 = cdolist_list_var2.rest();
                            proof = cdolist_list_var2.first();
                        } 
                    }
                }
            }
            final SubLObject idx_$56 = idx;
            if (NIL == id_index_sparse_objects_empty_p(idx_$56)) {
                final SubLObject cdohash_table = id_index_sparse_objects(idx_$56);
                SubLObject id2 = NIL;
                SubLObject inference_answer2 = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        id2 = getEntryKey(cdohash_entry);
                        inference_answer2 = getEntryValue(cdohash_entry);
                        SubLObject cdolist_list_var3;
                        final SubLObject proofs2 = cdolist_list_var3 = pph_proof.inference_answer_get_proofs(inference_answer2);
                        SubLObject proof2 = NIL;
                        proof2 = cdolist_list_var3.first();
                        while (NIL != cdolist_list_var3) {
                            final SubLObject v_proof_view2 = proof_view.construct_inference_proof_view(proof2, inference_answer2, mt);
                            SubLObject cdolist_list_var_$57;
                            final SubLObject facts_used2 = cdolist_list_var_$57 = proof_view.proof_view_get_facts_used(v_proof_view2);
                            SubLObject fact2 = NIL;
                            fact2 = cdolist_list_var_$57.first();
                            while (NIL != cdolist_list_var_$57) {
                                if (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(fact2), all_rtv_relevant_preds_set)) {
                                    bbf_edges = cons(bbf_make_edge_from_assertion(fact2), bbf_edges);
                                }
                                cdolist_list_var_$57 = cdolist_list_var_$57.rest();
                                fact2 = cdolist_list_var_$57.first();
                            } 
                            cdolist_list_var3 = cdolist_list_var3.rest();
                            proof2 = cdolist_list_var3.first();
                        } 
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
            }
        }
        return bbf_make_graph(NIL, list_utilities.hash_remove_duplicates(bbf_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, mt));
    }

    public static final SubLObject bbf_rtv_unbound_alt(SubLObject arg_plist) {
        {
            SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
            SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
            SubLObject formula = getf(params, $FORMULA, UNPROVIDED);
            SubLObject arg1_answers = getf(params, $ARG1_ANSWERS, UNPROVIDED);
            SubLObject arg2_answers = getf(params, $ARG2_ANSWERS, UNPROVIDED);
            SubLObject rtv_clause = list_utilities.tree_find_if(RTV_CLAUSE_P, formula, UNPROVIDED);
            SubLObject arg1_var = cycl_utilities.formula_arg1(rtv_clause, UNPROVIDED);
            SubLObject arg2_var = cycl_utilities.formula_arg2(rtv_clause, UNPROVIDED);
            SubLObject depth = cycl_utilities.formula_arg4(rtv_clause, UNPROVIDED);
            SubLObject preds = el_extensional_set_elements(cycl_utilities.formula_arg3(rtv_clause, UNPROVIDED));
            SubLObject new_formula = NIL;
            SubLObject just_assertions = NIL;
            SubLObject all_relevant_assertions = set.new_set(UNPROVIDED, UNPROVIDED);
            SubLObject all_rtv_relevant_preds_set = set.new_set(UNPROVIDED, UNPROVIDED);
            SubLObject result_links = set.new_set(UNPROVIDED, UNPROVIDED);
            SubLObject goal_terms = NIL;
            SubLObject start_terms = NIL;
            SubLObject subst_var = NIL;
            if (length(arg1_answers).numG(length(arg2_answers))) {
                goal_terms = arg1_answers;
                start_terms = arg2_answers;
                subst_var = arg1_var;
            } else {
                goal_terms = arg2_answers;
                start_terms = arg1_answers;
                subst_var = arg2_var;
            }
            all_rtv_relevant_preds_set = set_utilities.construct_set_from_list(preds, UNPROVIDED, UNPROVIDED);
            {
                SubLObject cdolist_list_var = preds;
                SubLObject each_pred = NIL;
                for (each_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , each_pred = cdolist_list_var.first()) {
                    set_utilities.set_add_all(all_spec_preds(each_pred, mt, UNPROVIDED), all_rtv_relevant_preds_set);
                }
            }
            {
                SubLObject cdolist_list_var = start_terms;
                SubLObject fort = NIL;
                for (fort = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fort = cdolist_list_var.first()) {
                    new_formula = list_utilities.tree_substitute(formula, subst_var, fort);
                    just_assertions = remove_duplicates(removal_modules_rtv.rtv_all_edges_from_node_to_nodes_satisfying(fort, new_formula, preds, depth, mt), symbol_function(EQUAL), symbol_function(ASSERTION_FORMULA), UNPROVIDED, UNPROVIDED);
                    set_utilities.set_add_all(just_assertions, all_relevant_assertions);
                }
            }
            all_relevant_assertions = remove_rtv_irrelevant_justifications(all_relevant_assertions, all_rtv_relevant_preds_set);
            {
                SubLObject set_contents_var = set.do_set_internal(all_relevant_assertions);
                SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                SubLObject state = NIL;
                for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                    {
                        SubLObject each_ass = set_contents.do_set_contents_next(basis_object, state);
                        if (NIL != set_contents.do_set_contents_element_validP(state, each_ass)) {
                            set.set_add(bbf_make_edge_from_assertion(each_ass), result_links);
                        }
                    }
                }
            }
            return bbf_make_graph(NIL, set.set_element_list(result_links), list($MT, mt));
        }
    }

    public static SubLObject bbf_rtv_unbound(final SubLObject arg_plist) {
        final SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
        final SubLObject mt = getf(arg_plist, $MT, UNPROVIDED);
        final SubLObject formula = getf(params, $FORMULA, UNPROVIDED);
        final SubLObject arg1_answers = getf(params, $ARG1_ANSWERS, UNPROVIDED);
        final SubLObject arg2_answers = getf(params, $ARG2_ANSWERS, UNPROVIDED);
        final SubLObject rtv_clause = list_utilities.tree_find_if(RTV_CLAUSE_P, formula, UNPROVIDED);
        final SubLObject arg1_var = cycl_utilities.formula_arg1(rtv_clause, UNPROVIDED);
        final SubLObject arg2_var = cycl_utilities.formula_arg2(rtv_clause, UNPROVIDED);
        final SubLObject depth = cycl_utilities.formula_arg4(rtv_clause, UNPROVIDED);
        final SubLObject preds = el_extensional_set_elements(cycl_utilities.formula_arg3(rtv_clause, UNPROVIDED));
        SubLObject new_formula = NIL;
        SubLObject just_assertions = NIL;
        SubLObject all_relevant_assertions = set.new_set(UNPROVIDED, UNPROVIDED);
        SubLObject all_rtv_relevant_preds_set = set.new_set(UNPROVIDED, UNPROVIDED);
        final SubLObject result_links = set.new_set(UNPROVIDED, UNPROVIDED);
        SubLObject goal_terms = NIL;
        SubLObject start_terms = NIL;
        SubLObject subst_var = NIL;
        if (length(arg1_answers).numG(length(arg2_answers))) {
            goal_terms = arg1_answers;
            start_terms = arg2_answers;
            subst_var = arg1_var;
        } else {
            goal_terms = arg2_answers;
            start_terms = arg1_answers;
            subst_var = arg2_var;
        }
        all_rtv_relevant_preds_set = set_utilities.construct_set_from_list(preds, UNPROVIDED, UNPROVIDED);
        SubLObject cdolist_list_var = preds;
        SubLObject each_pred = NIL;
        each_pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            set_utilities.set_add_all(genl_predicates.all_spec_preds(each_pred, mt, UNPROVIDED), all_rtv_relevant_preds_set);
            cdolist_list_var = cdolist_list_var.rest();
            each_pred = cdolist_list_var.first();
        } 
        cdolist_list_var = start_terms;
        SubLObject fort = NIL;
        fort = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            new_formula = list_utilities.tree_substitute(formula, subst_var, fort);
            just_assertions = remove_duplicates(removal_modules_rtv.rtv_all_edges_from_node_to_nodes_satisfying(fort, new_formula, preds, depth, mt), symbol_function(EQUAL), symbol_function(ASSERTION_FORMULA), UNPROVIDED, UNPROVIDED);
            set_utilities.set_add_all(just_assertions, all_relevant_assertions);
            cdolist_list_var = cdolist_list_var.rest();
            fort = cdolist_list_var.first();
        } 
        all_relevant_assertions = remove_rtv_irrelevant_justifications(all_relevant_assertions, all_rtv_relevant_preds_set);
        final SubLObject set_contents_var = set.do_set_internal(all_relevant_assertions);
        SubLObject basis_object;
        SubLObject state;
        SubLObject each_ass;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            each_ass = set_contents.do_set_contents_next(basis_object, state);
            if (NIL != set_contents.do_set_contents_element_validP(state, each_ass)) {
                set.set_add(bbf_make_edge_from_assertion(each_ass), result_links);
            }
        }
        return bbf_make_graph(NIL, set.set_element_list(result_links), list($MT, mt));
    }

    public static final SubLObject bbf_rtv_from_inference_new_alt(SubLObject arg_plist) {
        {
            SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
            SubLObject formula = getf(params, $FORMULA, UNPROVIDED);
            SubLObject answers = set_utilities.construct_set_from_list(getf(params, $ANSWERS, UNPROVIDED), UNPROVIDED, UNPROVIDED);
            SubLObject problem_store_id = getf(params, $PROBLEM_STORE_ID, UNPROVIDED);
            SubLObject inference_id = getf(params, $INFERENCE_ID, UNPROVIDED);
            SubLObject rtv_clause = list_utilities.tree_find_if(RTV_CLAUSE_P, formula, UNPROVIDED);
            SubLObject rtv_preds_list = fourth(rtv_clause);
            SubLObject all_rtv_relevant_preds_set = NIL;
            SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(problem_store_id, inference_id);
            SubLObject mt = inference_datastructures_inference.inference_mt(inference);
            SubLObject supports = NIL;
            SubLObject justification = NIL;
            SubLObject bbf_edges = set.new_set(UNPROVIDED, UNPROVIDED);
            SubLObject answer_terms_set = set.new_set(UNPROVIDED, UNPROVIDED);
            all_rtv_relevant_preds_set = set_utilities.construct_set_from_list(rtv_preds_list.rest(), UNPROVIDED, UNPROVIDED);
            {
                SubLObject cdolist_list_var = rtv_preds_list;
                SubLObject each_pred = NIL;
                for (each_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , each_pred = cdolist_list_var.first()) {
                    set_utilities.set_add_all(all_spec_preds(each_pred, mt, UNPROVIDED), all_rtv_relevant_preds_set);
                }
            }
            {
                SubLObject idx = inference_datastructures_inference.inference_answer_id_index(inference);
                if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                    {
                        SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                        SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                        SubLObject inference_answer = NIL;
                        while (NIL != id) {
                            inference_answer = do_id_index_state_object(idx, $SKIP, id, state_var);
                            if (NIL != do_id_index_id_and_object_validP(id, inference_answer, $SKIP)) {
                                answer_terms_set = set_utilities.construct_set_from_list(Mapping.mapcar(symbol_function(CDR), inference_datastructures_inference.inference_answer_bindings(inference_answer)), UNPROVIDED, UNPROVIDED);
                                if (NIL != set.non_empty_set_p(set_utilities.set_intersection(list(answers, answer_terms_set), UNPROVIDED))) {
                                    {
                                        SubLObject cdolist_list_var = inference_datastructures_inference.inference_answer_justifications(inference_answer);
                                        SubLObject inference_justify = NIL;
                                        for (inference_justify = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , inference_justify = cdolist_list_var.first()) {
                                            supports = inference_datastructures_inference.inference_answer_justification_supports(inference_justify);
                                            {
                                                SubLObject cdolist_list_var_55 = supports;
                                                SubLObject support = NIL;
                                                for (support = cdolist_list_var_55.first(); NIL != cdolist_list_var_55; cdolist_list_var_55 = cdolist_list_var_55.rest() , support = cdolist_list_var_55.first()) {
                                                    justification = arguments.support_justification(support);
                                                    {
                                                        SubLObject cdolist_list_var_56 = justification;
                                                        SubLObject each_sentence = NIL;
                                                        for (each_sentence = cdolist_list_var_56.first(); NIL != cdolist_list_var_56; cdolist_list_var_56 = cdolist_list_var_56.rest() , each_sentence = cdolist_list_var_56.first()) {
                                                            if (NIL != assertion_handles.assertion_p(each_sentence)) {
                                                                if (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(each_sentence), all_rtv_relevant_preds_set)) {
                                                                    set.set_add(bbf_make_edge_from_assertion(each_sentence), bbf_edges);
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
                            id = do_id_index_next_id(idx, NIL, id, state_var);
                            state_var = do_id_index_next_state(idx, NIL, id, state_var);
                        } 
                    }
                }
            }
            return bbf_make_graph(NIL, list_utilities.hash_remove_duplicates(set.set_element_list(bbf_edges), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, mt));
        }
    }

    public static SubLObject bbf_rtv_from_inference_new(final SubLObject arg_plist) {
        final SubLObject params = getf(arg_plist, $PARAMS, UNPROVIDED);
        final SubLObject formula = getf(params, $FORMULA, UNPROVIDED);
        final SubLObject answers = set_utilities.construct_set_from_list(getf(params, $ANSWERS, UNPROVIDED), UNPROVIDED, UNPROVIDED);
        final SubLObject problem_store_id = getf(params, $PROBLEM_STORE_ID, UNPROVIDED);
        final SubLObject inference_id = getf(params, $INFERENCE_ID, UNPROVIDED);
        final SubLObject rtv_clause = list_utilities.tree_find_if(RTV_CLAUSE_P, formula, UNPROVIDED);
        final SubLObject rtv_preds_list = fourth(rtv_clause);
        SubLObject all_rtv_relevant_preds_set = NIL;
        final SubLObject inference = inference_datastructures_problem_store.find_inference_by_ids(problem_store_id, inference_id);
        final SubLObject mt = inference_datastructures_inference.inference_mt(inference);
        SubLObject supports = NIL;
        SubLObject justification = NIL;
        final SubLObject bbf_edges = set.new_set(UNPROVIDED, UNPROVIDED);
        SubLObject answer_terms_set = set.new_set(UNPROVIDED, UNPROVIDED);
        all_rtv_relevant_preds_set = set_utilities.construct_set_from_list(rtv_preds_list.rest(), UNPROVIDED, UNPROVIDED);
        SubLObject cdolist_list_var = rtv_preds_list;
        SubLObject each_pred = NIL;
        each_pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            set_utilities.set_add_all(genl_predicates.all_spec_preds(each_pred, mt, UNPROVIDED), all_rtv_relevant_preds_set);
            cdolist_list_var = cdolist_list_var.rest();
            each_pred = cdolist_list_var.first();
        } 
        final SubLObject idx = inference_datastructures_inference.inference_answer_id_index(inference);
        if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
            final SubLObject idx_$59 = idx;
            if (NIL == id_index_dense_objects_empty_p(idx_$59, $SKIP)) {
                final SubLObject vector_var = id_index_dense_objects(idx_$59);
                final SubLObject backwardP_var = NIL;
                SubLObject length;
                SubLObject v_iteration;
                SubLObject id;
                SubLObject inference_answer;
                SubLObject cdolist_list_var2;
                SubLObject inference_justify;
                SubLObject cdolist_list_var_$60;
                SubLObject support;
                SubLObject cdolist_list_var_$61;
                SubLObject each_sentence;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    inference_answer = aref(vector_var, id);
                    if ((NIL == id_index_tombstone_p(inference_answer)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                        if (NIL != id_index_tombstone_p(inference_answer)) {
                            inference_answer = $SKIP;
                        }
                        answer_terms_set = set_utilities.construct_set_from_list(Mapping.mapcar(symbol_function(CDR), inference_datastructures_inference.inference_answer_bindings(inference_answer)), UNPROVIDED, UNPROVIDED);
                        if (NIL != set.non_empty_set_p(set_utilities.set_intersection(list(answers, answer_terms_set), UNPROVIDED))) {
                            cdolist_list_var2 = inference_datastructures_inference.inference_answer_justifications(inference_answer);
                            inference_justify = NIL;
                            inference_justify = cdolist_list_var2.first();
                            while (NIL != cdolist_list_var2) {
                                supports = cdolist_list_var_$60 = inference_datastructures_inference.inference_answer_justification_supports(inference_justify);
                                support = NIL;
                                support = cdolist_list_var_$60.first();
                                while (NIL != cdolist_list_var_$60) {
                                    justification = cdolist_list_var_$61 = arguments.support_justification(support);
                                    each_sentence = NIL;
                                    each_sentence = cdolist_list_var_$61.first();
                                    while (NIL != cdolist_list_var_$61) {
                                        if ((NIL != assertion_handles.assertion_p(each_sentence)) && (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(each_sentence), all_rtv_relevant_preds_set))) {
                                            set.set_add(bbf_make_edge_from_assertion(each_sentence), bbf_edges);
                                        }
                                        cdolist_list_var_$61 = cdolist_list_var_$61.rest();
                                        each_sentence = cdolist_list_var_$61.first();
                                    } 
                                    cdolist_list_var_$60 = cdolist_list_var_$60.rest();
                                    support = cdolist_list_var_$60.first();
                                } 
                                cdolist_list_var2 = cdolist_list_var2.rest();
                                inference_justify = cdolist_list_var2.first();
                            } 
                        }
                    }
                }
            }
            final SubLObject idx_$60 = idx;
            if (NIL == id_index_sparse_objects_empty_p(idx_$60)) {
                final SubLObject cdohash_table = id_index_sparse_objects(idx_$60);
                SubLObject id2 = NIL;
                SubLObject inference_answer2 = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        id2 = getEntryKey(cdohash_entry);
                        inference_answer2 = getEntryValue(cdohash_entry);
                        answer_terms_set = set_utilities.construct_set_from_list(Mapping.mapcar(symbol_function(CDR), inference_datastructures_inference.inference_answer_bindings(inference_answer2)), UNPROVIDED, UNPROVIDED);
                        if (NIL != set.non_empty_set_p(set_utilities.set_intersection(list(answers, answer_terms_set), UNPROVIDED))) {
                            SubLObject cdolist_list_var3 = inference_datastructures_inference.inference_answer_justifications(inference_answer2);
                            SubLObject inference_justify2 = NIL;
                            inference_justify2 = cdolist_list_var3.first();
                            while (NIL != cdolist_list_var3) {
                                SubLObject cdolist_list_var_$62;
                                supports = cdolist_list_var_$62 = inference_datastructures_inference.inference_answer_justification_supports(inference_justify2);
                                SubLObject support2 = NIL;
                                support2 = cdolist_list_var_$62.first();
                                while (NIL != cdolist_list_var_$62) {
                                    SubLObject cdolist_list_var_$63;
                                    justification = cdolist_list_var_$63 = arguments.support_justification(support2);
                                    SubLObject each_sentence2 = NIL;
                                    each_sentence2 = cdolist_list_var_$63.first();
                                    while (NIL != cdolist_list_var_$63) {
                                        if ((NIL != assertion_handles.assertion_p(each_sentence2)) && (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(each_sentence2), all_rtv_relevant_preds_set))) {
                                            set.set_add(bbf_make_edge_from_assertion(each_sentence2), bbf_edges);
                                        }
                                        cdolist_list_var_$63 = cdolist_list_var_$63.rest();
                                        each_sentence2 = cdolist_list_var_$63.first();
                                    } 
                                    cdolist_list_var_$62 = cdolist_list_var_$62.rest();
                                    support2 = cdolist_list_var_$62.first();
                                } 
                                cdolist_list_var3 = cdolist_list_var3.rest();
                                inference_justify2 = cdolist_list_var3.first();
                            } 
                        }
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
            }
        }
        return bbf_make_graph(NIL, list_utilities.hash_remove_duplicates(set.set_element_list(bbf_edges), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), list($MT, mt));
    }

    public static final SubLObject valid_rtv_justificationP_alt(SubLObject formula, SubLObject rtv_preds_set) {
        if (NIL != set.set_memberP(cycl_utilities.formula_arg0(formula), rtv_preds_set)) {
            return T;
        } else {
            return NIL;
        }
    }

    public static SubLObject valid_rtv_justificationP(final SubLObject formula, final SubLObject rtv_preds_set) {
        if (NIL != set.set_memberP(cycl_utilities.formula_arg0(formula), rtv_preds_set)) {
            return T;
        }
        return NIL;
    }

    public static final SubLObject remove_rtv_irrelevant_justifications_alt(SubLObject all_relevant_assertions, SubLObject rtv_preds_set) {
        {
            SubLObject rtv_filtered_set = set.new_set(UNPROVIDED, UNPROVIDED);
            SubLObject set_contents_var = set.do_set_internal(all_relevant_assertions);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject each_ass = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, each_ass)) {
                        if (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(each_ass), rtv_preds_set)) {
                            set.set_add(each_ass, rtv_filtered_set);
                        }
                    }
                }
            }
            return rtv_filtered_set;
        }
    }

    public static SubLObject remove_rtv_irrelevant_justifications(final SubLObject all_relevant_assertions, final SubLObject rtv_preds_set) {
        final SubLObject rtv_filtered_set = set.new_set(UNPROVIDED, UNPROVIDED);
        final SubLObject set_contents_var = set.do_set_internal(all_relevant_assertions);
        SubLObject basis_object;
        SubLObject state;
        SubLObject each_ass;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            each_ass = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, each_ass)) && (NIL != valid_rtv_justificationP(assertions_high.assertion_formula(each_ass), rtv_preds_set))) {
                set.set_add(each_ass, rtv_filtered_set);
            }
        }
        return rtv_filtered_set;
    }

    public static final SubLObject declare_blue_grapher_utilities_file_alt() {
        declareMacro("define_blue_filter_fn", "DEFINE-BLUE-FILTER-FN");
        declareFunction("blue_filter_fns", "BLUE-FILTER-FNS", 0, 0, false);
        declareFunction("get_blue_filter_fn_data", "GET-BLUE-FILTER-FN-DATA", 1, 0, false);
        declareFunction("blue_filter_fn_p", "BLUE-FILTER-FN-P", 1, 0, false);
        declareFunction("get_blue_filter_fn_cost", "GET-BLUE-FILTER-FN-COST", 1, 0, false);
        declareFunction("get_blue_filter_fn_strength", "GET-BLUE-FILTER-FN-STRENGTH", 1, 0, false);
        declareFunction("get_blue_filter_fn_type", "GET-BLUE-FILTER-FN-TYPE", 1, 0, false);
        declareFunction("apply_blue_filter_fn", "APPLY-BLUE-FILTER-FN", 3, 1, false);
        declareFunction("blue_filter_fns_sorted", "BLUE-FILTER-FNS-SORTED", 0, 1, false);
        declareFunction("blue_apply_filter_fns", "BLUE-APPLY-FILTER-FNS", 3, 1, false);
        declareFunction("blue_filter_fn_keys", "BLUE-FILTER-FN-KEYS", 0, 1, false);
        declareFunction("blue_filter_fn_by_key", "BLUE-FILTER-FN-BY-KEY", 1, 0, false);
        declareFunction("bff_arbitrary_unions", "BFF-ARBITRARY-UNIONS", 2, 0, false);
        declareFunction("bff_rkf_irrelevant_terms", "BFF-RKF-IRRELEVANT-TERMS", 2, 0, false);
        declareFunction("bff_cyc_kb_subset_collections", "BFF-CYC-KB-SUBSET-COLLECTIONS", 2, 0, false);
        declareFunction("bff_rtv_irrelevant_terms", "BFF-RTV-IRRELEVANT-TERMS", 2, 0, false);
        declareFunction("bff_most_general_5", "BFF-MOST-GENERAL-5", 2, 0, false);
        declareFunction("bff_most_general_10", "BFF-MOST-GENERAL-10", 2, 0, false);
        declareFunction("bff_most_general_20", "BFF-MOST-GENERAL-20", 2, 0, false);
        declareMacro("define_blue_builder_fn", "DEFINE-BLUE-BUILDER-FN");
        declareFunction("get_blue_builder_fn_data", "GET-BLUE-BUILDER-FN-DATA", 1, 0, false);
        declareFunction("blue_builder_fn_p", "BLUE-BUILDER-FN-P", 1, 0, false);
        declareFunction("blue_builder", "BLUE-BUILDER", 2, 0, false);
        declareFunction("blue_builder_fn_keys", "BLUE-BUILDER-FN-KEYS", 0, 0, false);
        declareFunction("blue_builder_fn_by_key", "BLUE-BUILDER-FN-BY-KEY", 1, 0, false);
        declareFunction("get_blue_builder_fn_def_value", "GET-BLUE-BUILDER-FN-DEF-VALUE", 2, 0, false);
        declareFunction("bbf_forward_true", "BBF-FORWARD-TRUE", 1, 0, false);
        declareFunction("bbf_forward_true_internal", "BBF-FORWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_backward_true", "BBF-BACKWARD-TRUE", 1, 0, false);
        declareFunction("bbf_backward_true_internal", "BBF-BACKWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_forward_true", "BBF-MIN-FORWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_forward_true_internal", "BBF-MIN-FORWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_backward_true", "BBF-MIN-BACKWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_backward_true_internal", "BBF-MIN-BACKWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_ceilings_forward_true", "BBF-MIN-CEILINGS-FORWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_ceilings_forward_true_internal", "BBF-MIN-CEILINGS-FORWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_forward_and_backward_true", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_forward_and_backward_true_internal", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE-INTERNAL", 5, 0, false);
        declareFunction("bbf_min_forward_and_backward_true_internal_internal", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE-INTERNAL-INTERNAL", 5, 2, false);
        declareFunction("bbf_script", "BBF-SCRIPT", 1, 0, false);
        declareFunction("bbf_inference_answers", "BBF-INFERENCE-ANSWERS", 1, 0, false);
        declareFunction("determine_edge_templates_for_inference", "DETERMINE-EDGE-TEMPLATES-FOR-INFERENCE", 1, 0, false);
        declareFunction("html_determine_edge_templates_for_inference", "HTML-DETERMINE-EDGE-TEMPLATES-FOR-INFERENCE", 1, 0, false);
        declareFunction("bbf_inference", "BBF-INFERENCE", 1, 0, false);
        declareFunction("bbf_rule", "BBF-RULE", 1, 0, false);
        declareFunction("blue_literal_to_edge", "BLUE-LITERAL-TO-EDGE", 3, 0, false);
        declareFunction("make_inference_hl_to_el_var_list", "MAKE-INFERENCE-HL-TO-EL-VAR-LIST", 1, 0, false);
        declareFunction("make_assertion_hl_to_el_var_list", "MAKE-ASSERTION-HL-TO-EL-VAR-LIST", 1, 0, false);
        declareFunction("bbf_make_edge_from_assertion", "BBF-MAKE-EDGE-FROM-ASSERTION", 1, 0, false);
        declareFunction("bbf_make_graph", "BBF-MAKE-GRAPH", 2, 1, false);
        declareFunction("bbf_make_edge", "BBF-MAKE-EDGE", 4, 4, false);
        declareFunction("bbf_make_edge_with_nodes", "BBF-MAKE-EDGE-WITH-NODES", 4, 2, false);
        declareFunction("bbf_make_node", "BBF-MAKE-NODE", 1, 2, false);
        declareFunction("blue_event_dispatcher_runningP", "BLUE-EVENT-DISPATCHER-RUNNING?", 0, 0, false);
        declareFunction("ensure_blue_event_dispatcher_running", "ENSURE-BLUE-EVENT-DISPATCHER-RUNNING", 0, 0, false);
        declareFunction("register_listener_with_blue_event_dispatcher", "REGISTER-LISTENER-WITH-BLUE-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("deregister_listener_with_blue_event_dispatcher", "DEREGISTER-LISTENER-WITH-BLUE-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("stop_blue_event_dispatcher", "STOP-BLUE-EVENT-DISPATCHER", 0, 0, false);
        declareFunction("start_blue_event_dispatcher", "START-BLUE-EVENT-DISPATCHER", 0, 0, false);
        declareFunction("blue_event", "BLUE-EVENT", 2, 0, false);
        declareFunction("blue_fetch_event", "BLUE-FETCH-EVENT", 1, 0, false);
        declareFunction("blue_fetch_uia_blue_event", "BLUE-FETCH-UIA-BLUE-EVENT", 1, 0, false);
        declareFunction("blue_current_message_mailbox_suite", "BLUE-CURRENT-MESSAGE-MAILBOX-SUITE", 0, 0, false);
        declareFunction("blue_ensure_blue_event_address", "BLUE-ENSURE-BLUE-EVENT-ADDRESS", 1, 0, false);
        declareFunction("map_blue_event_to_mailbox", "MAP-BLUE-EVENT-TO-MAILBOX", 1, 0, false);
        declareFunction("blue_map_event_to_blue_event", "BLUE-MAP-EVENT-TO-BLUE-EVENT", 1, 1, false);
        declareFunction("blue_graph_print_function_trampoline", "BLUE-GRAPH-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("blue_graph_p", "BLUE-GRAPH-P", 1, 0, false);
        new com.cyc.cycjava.cycl.blue_grapher_utilities.$blue_graph_p$UnaryFunction();
        declareFunction("blue_graph_isg", "BLUE-GRAPH-ISG", 1, 0, false);
        declareFunction("blue_graph_node_label_map", "BLUE-GRAPH-NODE-LABEL-MAP", 1, 0, false);
        declareFunction("blue_graph_nodes", "BLUE-GRAPH-NODES", 1, 0, false);
        declareFunction("blue_graph_edge_label_map", "BLUE-GRAPH-EDGE-LABEL-MAP", 1, 0, false);
        declareFunction("blue_graph_edges", "BLUE-GRAPH-EDGES", 1, 0, false);
        declareFunction("blue_graph_node_definitions", "BLUE-GRAPH-NODE-DEFINITIONS", 1, 0, false);
        declareFunction("blue_graph_edge_definitions", "BLUE-GRAPH-EDGE-DEFINITIONS", 1, 0, false);
        declareFunction("blue_graph_options", "BLUE-GRAPH-OPTIONS", 1, 0, false);
        declareFunction("_csetf_blue_graph_isg", "_CSETF-BLUE-GRAPH-ISG", 2, 0, false);
        declareFunction("_csetf_blue_graph_node_label_map", "_CSETF-BLUE-GRAPH-NODE-LABEL-MAP", 2, 0, false);
        declareFunction("_csetf_blue_graph_nodes", "_CSETF-BLUE-GRAPH-NODES", 2, 0, false);
        declareFunction("_csetf_blue_graph_edge_label_map", "_CSETF-BLUE-GRAPH-EDGE-LABEL-MAP", 2, 0, false);
        declareFunction("_csetf_blue_graph_edges", "_CSETF-BLUE-GRAPH-EDGES", 2, 0, false);
        declareFunction("_csetf_blue_graph_node_definitions", "_CSETF-BLUE-GRAPH-NODE-DEFINITIONS", 2, 0, false);
        declareFunction("_csetf_blue_graph_edge_definitions", "_CSETF-BLUE-GRAPH-EDGE-DEFINITIONS", 2, 0, false);
        declareFunction("_csetf_blue_graph_options", "_CSETF-BLUE-GRAPH-OPTIONS", 2, 0, false);
        declareFunction("make_blue_graph", "MAKE-BLUE-GRAPH", 0, 1, false);
        declareFunction("new_blue_graph", "NEW-BLUE-GRAPH", 0, 0, false);
        declareFunction("blue_graph_add_node_type", "BLUE-GRAPH-ADD-NODE-TYPE", 3, 0, false);
        declareFunction("blue_graph_add_edge_type", "BLUE-GRAPH-ADD-EDGE-TYPE", 3, 1, false);
        declareFunction("blue_graph_add_option", "BLUE-GRAPH-ADD-OPTION", 3, 0, false);
        declareFunction("blue_graph_add_node", "BLUE-GRAPH-ADD-NODE", 1, 2, false);
        declareFunction("blue_graph_node_p", "BLUE-GRAPH-NODE-P", 1, 0, false);
        declareFunction("blue_graph_maybe_add_node", "BLUE-GRAPH-MAYBE-ADD-NODE", 2, 1, false);
        declareFunction("blue_graph_node_get_id", "BLUE-GRAPH-NODE-GET-ID", 1, 0, false);
        declareFunction("blue_graph_node_get_type", "BLUE-GRAPH-NODE-GET-TYPE", 1, 0, false);
        declareFunction("blue_graph_node_get_label", "BLUE-GRAPH-NODE-GET-LABEL", 1, 0, false);
        declareFunction("blue_graph_get_node", "BLUE-GRAPH-GET-NODE", 2, 0, false);
        declareFunction("blue_graph_get_node_by_label", "BLUE-GRAPH-GET-NODE-BY-LABEL", 2, 0, false);
        declareFunction("blue_graph_resolve_node_label_reference", "BLUE-GRAPH-RESOLVE-NODE-LABEL-REFERENCE", 2, 0, false);
        declareFunction("blue_graph_edge_from", "BLUE-GRAPH-EDGE-FROM", 1, 0, false);
        declareFunction("blue_graph_edge_to", "BLUE-GRAPH-EDGE-TO", 1, 0, false);
        declareFunction("blue_graph_edge_get_type", "BLUE-GRAPH-EDGE-GET-TYPE", 1, 0, false);
        declareFunction("blue_graph_edge_get_label", "BLUE-GRAPH-EDGE-GET-LABEL", 1, 0, false);
        declareFunction("blue_graph_add_edge", "BLUE-GRAPH-ADD-EDGE", 3, 2, false);
        declareFunction("blue_graph_next_id", "BLUE-GRAPH-NEXT-ID", 1, 0, false);
        declareFunction("blue_graph_generate_description", "BLUE-GRAPH-GENERATE-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_definition_description", "BLUE-GRAPH-GENERATE-DEFINITION-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_option_description", "BLUE-GRAPH-GENERATE-OPTION-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_node_description", "BLUE-GRAPH-GENERATE-NODE-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_edge_description", "BLUE-GRAPH-GENERATE-EDGE-DESCRIPTION", 1, 0, false);
        declareFunction("bbf_rtv_from_inference", "BBF-RTV-FROM-INFERENCE", 1, 0, false);
        declareFunction("bbf_rtv_unbound", "BBF-RTV-UNBOUND", 1, 0, false);
        declareFunction("bbf_rtv_from_inference_new", "BBF-RTV-FROM-INFERENCE-NEW", 1, 0, false);
        declareFunction("valid_rtv_justificationP", "VALID-RTV-JUSTIFICATION?", 2, 0, false);
        declareFunction("remove_rtv_irrelevant_justifications", "REMOVE-RTV-IRRELEVANT-JUSTIFICATIONS", 2, 0, false);
        return NIL;
    }

    public static SubLObject declare_blue_grapher_utilities_file() {
        if (SubLFiles.USE_V1) {
            declareMacro("define_blue_filter_fn", "DEFINE-BLUE-FILTER-FN");
            declareFunction("blue_filter_fns", "BLUE-FILTER-FNS", 0, 0, false);
            declareFunction("get_blue_filter_fn_data", "GET-BLUE-FILTER-FN-DATA", 1, 0, false);
            declareFunction("blue_filter_fn_p", "BLUE-FILTER-FN-P", 1, 0, false);
            declareFunction("get_blue_filter_fn_cost", "GET-BLUE-FILTER-FN-COST", 1, 0, false);
            declareFunction("get_blue_filter_fn_strength", "GET-BLUE-FILTER-FN-STRENGTH", 1, 0, false);
            declareFunction("get_blue_filter_fn_type", "GET-BLUE-FILTER-FN-TYPE", 1, 0, false);
            declareFunction("apply_blue_filter_fn", "APPLY-BLUE-FILTER-FN", 3, 1, false);
            declareFunction("blue_filter_fns_sorted", "BLUE-FILTER-FNS-SORTED", 0, 1, false);
            declareFunction("blue_apply_filter_fns", "BLUE-APPLY-FILTER-FNS", 3, 1, false);
            declareFunction("blue_filter_fn_keys", "BLUE-FILTER-FN-KEYS", 0, 1, false);
            declareFunction("blue_filter_fn_by_key", "BLUE-FILTER-FN-BY-KEY", 1, 0, false);
            declareFunction("bff_arbitrary_unions", "BFF-ARBITRARY-UNIONS", 2, 0, false);
            declareFunction("bff_cyc_kb_subset_collections", "BFF-CYC-KB-SUBSET-COLLECTIONS", 2, 0, false);
            declareFunction("bff_most_general_5", "BFF-MOST-GENERAL-5", 2, 0, false);
            declareFunction("bff_most_general_10", "BFF-MOST-GENERAL-10", 2, 0, false);
            declareFunction("bff_most_general_20", "BFF-MOST-GENERAL-20", 2, 0, false);
            declareMacro("define_blue_builder_fn", "DEFINE-BLUE-BUILDER-FN");
            declareFunction("get_blue_builder_fn_data", "GET-BLUE-BUILDER-FN-DATA", 1, 0, false);
            declareFunction("blue_builder_fn_p", "BLUE-BUILDER-FN-P", 1, 0, false);
            declareFunction("blue_builder", "BLUE-BUILDER", 2, 0, false);
            declareFunction("blue_builder_fn_keys", "BLUE-BUILDER-FN-KEYS", 0, 0, false);
            declareFunction("blue_builder_fn_by_key", "BLUE-BUILDER-FN-BY-KEY", 1, 0, false);
            declareFunction("get_blue_builder_fn_def_value", "GET-BLUE-BUILDER-FN-DEF-VALUE", 2, 0, false);
            declareFunction("bbf_forward_true", "BBF-FORWARD-TRUE", 1, 0, false);
            declareFunction("bbf_forward_true_internal", "BBF-FORWARD-TRUE-INTERNAL", 5, 1, false);
            declareFunction("bbf_backward_true", "BBF-BACKWARD-TRUE", 1, 0, false);
            declareFunction("bbf_backward_true_internal", "BBF-BACKWARD-TRUE-INTERNAL", 5, 1, false);
            declareFunction("bbf_min_forward_true", "BBF-MIN-FORWARD-TRUE", 1, 0, false);
            declareFunction("bbf_min_forward_true_internal", "BBF-MIN-FORWARD-TRUE-INTERNAL", 5, 1, false);
            declareFunction("bbf_min_backward_true", "BBF-MIN-BACKWARD-TRUE", 1, 0, false);
            declareFunction("bbf_min_backward_true_internal", "BBF-MIN-BACKWARD-TRUE-INTERNAL", 5, 1, false);
            declareFunction("bbf_min_ceilings_forward_true", "BBF-MIN-CEILINGS-FORWARD-TRUE", 1, 0, false);
            declareFunction("bbf_min_ceilings_forward_true_internal", "BBF-MIN-CEILINGS-FORWARD-TRUE-INTERNAL", 5, 1, false);
            declareFunction("bbf_min_forward_and_backward_true", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE", 1, 0, false);
            declareFunction("bbf_min_forward_and_backward_true_internal", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE-INTERNAL", 5, 0, false);
            declareFunction("bbf_min_forward_and_backward_true_internal_internal", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE-INTERNAL-INTERNAL", 5, 2, false);
            declareFunction("bbf_script", "BBF-SCRIPT", 1, 0, false);
            declareFunction("bbf_inference_answers", "BBF-INFERENCE-ANSWERS", 1, 0, false);
            declareFunction("determine_edge_templates_for_inference", "DETERMINE-EDGE-TEMPLATES-FOR-INFERENCE", 1, 0, false);
            declareFunction("html_determine_edge_templates_for_inference", "HTML-DETERMINE-EDGE-TEMPLATES-FOR-INFERENCE", 1, 0, false);
            declareFunction("bbf_inference", "BBF-INFERENCE", 1, 0, false);
            declareFunction("bbf_rule", "BBF-RULE", 1, 0, false);
            declareFunction("blue_literal_to_edge", "BLUE-LITERAL-TO-EDGE", 3, 0, false);
            declareFunction("make_inference_hl_to_el_var_list", "MAKE-INFERENCE-HL-TO-EL-VAR-LIST", 1, 0, false);
            declareFunction("make_assertion_hl_to_el_var_list", "MAKE-ASSERTION-HL-TO-EL-VAR-LIST", 1, 0, false);
            declareFunction("bbf_make_edge_from_assertion", "BBF-MAKE-EDGE-FROM-ASSERTION", 1, 0, false);
            declareFunction("bbf_make_graph", "BBF-MAKE-GRAPH", 2, 1, false);
            declareFunction("bbf_make_edge", "BBF-MAKE-EDGE", 4, 4, false);
            declareFunction("bbf_make_edge_with_nodes", "BBF-MAKE-EDGE-WITH-NODES", 4, 2, false);
            declareFunction("bbf_make_node", "BBF-MAKE-NODE", 1, 2, false);
            declareFunction("blue_event_dispatcher_runningP", "BLUE-EVENT-DISPATCHER-RUNNING?", 0, 0, false);
            declareFunction("ensure_blue_event_dispatcher_running", "ENSURE-BLUE-EVENT-DISPATCHER-RUNNING", 0, 0, false);
            declareFunction("register_listener_with_blue_event_dispatcher", "REGISTER-LISTENER-WITH-BLUE-EVENT-DISPATCHER", 1, 0, false);
            declareFunction("deregister_listener_with_blue_event_dispatcher", "DEREGISTER-LISTENER-WITH-BLUE-EVENT-DISPATCHER", 1, 0, false);
            declareFunction("stop_blue_event_dispatcher", "STOP-BLUE-EVENT-DISPATCHER", 0, 0, false);
            declareFunction("start_blue_event_dispatcher", "START-BLUE-EVENT-DISPATCHER", 0, 0, false);
            declareFunction("blue_event", "BLUE-EVENT", 2, 0, false);
            declareFunction("blue_fetch_event", "BLUE-FETCH-EVENT", 1, 0, false);
            declareFunction("blue_fetch_uia_blue_event", "BLUE-FETCH-UIA-BLUE-EVENT", 1, 0, false);
            declareFunction("blue_current_message_mailbox_suite", "BLUE-CURRENT-MESSAGE-MAILBOX-SUITE", 0, 0, false);
            declareFunction("blue_ensure_blue_event_address", "BLUE-ENSURE-BLUE-EVENT-ADDRESS", 1, 0, false);
            declareFunction("map_blue_event_to_mailbox", "MAP-BLUE-EVENT-TO-MAILBOX", 1, 0, false);
            declareFunction("blue_map_event_to_blue_event", "BLUE-MAP-EVENT-TO-BLUE-EVENT", 1, 1, false);
            declareFunction("blue_graph_print_function_trampoline", "BLUE-GRAPH-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
            declareFunction("blue_graph_p", "BLUE-GRAPH-P", 1, 0, false);
            new blue_grapher_utilities.$blue_graph_p$UnaryFunction();
            declareFunction("blue_graph_isg", "BLUE-GRAPH-ISG", 1, 0, false);
            declareFunction("blue_graph_node_label_map", "BLUE-GRAPH-NODE-LABEL-MAP", 1, 0, false);
            declareFunction("blue_graph_nodes", "BLUE-GRAPH-NODES", 1, 0, false);
            declareFunction("blue_graph_edge_label_map", "BLUE-GRAPH-EDGE-LABEL-MAP", 1, 0, false);
            declareFunction("blue_graph_edges", "BLUE-GRAPH-EDGES", 1, 0, false);
            declareFunction("blue_graph_node_definitions", "BLUE-GRAPH-NODE-DEFINITIONS", 1, 0, false);
            declareFunction("blue_graph_edge_definitions", "BLUE-GRAPH-EDGE-DEFINITIONS", 1, 0, false);
            declareFunction("blue_graph_options", "BLUE-GRAPH-OPTIONS", 1, 0, false);
            declareFunction("_csetf_blue_graph_isg", "_CSETF-BLUE-GRAPH-ISG", 2, 0, false);
            declareFunction("_csetf_blue_graph_node_label_map", "_CSETF-BLUE-GRAPH-NODE-LABEL-MAP", 2, 0, false);
            declareFunction("_csetf_blue_graph_nodes", "_CSETF-BLUE-GRAPH-NODES", 2, 0, false);
            declareFunction("_csetf_blue_graph_edge_label_map", "_CSETF-BLUE-GRAPH-EDGE-LABEL-MAP", 2, 0, false);
            declareFunction("_csetf_blue_graph_edges", "_CSETF-BLUE-GRAPH-EDGES", 2, 0, false);
            declareFunction("_csetf_blue_graph_node_definitions", "_CSETF-BLUE-GRAPH-NODE-DEFINITIONS", 2, 0, false);
            declareFunction("_csetf_blue_graph_edge_definitions", "_CSETF-BLUE-GRAPH-EDGE-DEFINITIONS", 2, 0, false);
            declareFunction("_csetf_blue_graph_options", "_CSETF-BLUE-GRAPH-OPTIONS", 2, 0, false);
            declareFunction("make_blue_graph", "MAKE-BLUE-GRAPH", 0, 1, false);
            declareFunction("visit_defstruct_blue_graph", "VISIT-DEFSTRUCT-BLUE-GRAPH", 2, 0, false);
            declareFunction("visit_defstruct_object_blue_graph_method", "VISIT-DEFSTRUCT-OBJECT-BLUE-GRAPH-METHOD", 2, 0, false);
            declareFunction("new_blue_graph", "NEW-BLUE-GRAPH", 0, 0, false);
            declareFunction("blue_graph_add_node_type", "BLUE-GRAPH-ADD-NODE-TYPE", 3, 0, false);
            declareFunction("blue_graph_add_edge_type", "BLUE-GRAPH-ADD-EDGE-TYPE", 3, 1, false);
            declareFunction("blue_graph_add_option", "BLUE-GRAPH-ADD-OPTION", 3, 0, false);
            declareFunction("blue_graph_add_node", "BLUE-GRAPH-ADD-NODE", 1, 2, false);
            declareFunction("blue_graph_node_p", "BLUE-GRAPH-NODE-P", 1, 0, false);
            declareFunction("blue_graph_maybe_add_node", "BLUE-GRAPH-MAYBE-ADD-NODE", 2, 1, false);
            declareFunction("blue_graph_node_get_id", "BLUE-GRAPH-NODE-GET-ID", 1, 0, false);
            declareFunction("blue_graph_node_get_type", "BLUE-GRAPH-NODE-GET-TYPE", 1, 0, false);
            declareFunction("blue_graph_node_get_label", "BLUE-GRAPH-NODE-GET-LABEL", 1, 0, false);
            declareFunction("blue_graph_get_node", "BLUE-GRAPH-GET-NODE", 2, 0, false);
            declareFunction("blue_graph_get_node_by_label", "BLUE-GRAPH-GET-NODE-BY-LABEL", 2, 0, false);
            declareFunction("blue_graph_resolve_node_label_reference", "BLUE-GRAPH-RESOLVE-NODE-LABEL-REFERENCE", 2, 0, false);
            declareFunction("blue_graph_edge_from", "BLUE-GRAPH-EDGE-FROM", 1, 0, false);
            declareFunction("blue_graph_edge_to", "BLUE-GRAPH-EDGE-TO", 1, 0, false);
            declareFunction("blue_graph_edge_get_type", "BLUE-GRAPH-EDGE-GET-TYPE", 1, 0, false);
            declareFunction("blue_graph_edge_get_label", "BLUE-GRAPH-EDGE-GET-LABEL", 1, 0, false);
            declareFunction("blue_graph_add_edge", "BLUE-GRAPH-ADD-EDGE", 3, 2, false);
            declareFunction("blue_graph_next_id", "BLUE-GRAPH-NEXT-ID", 1, 0, false);
            declareFunction("blue_graph_generate_description", "BLUE-GRAPH-GENERATE-DESCRIPTION", 1, 0, false);
            declareFunction("blue_graph_generate_definition_description", "BLUE-GRAPH-GENERATE-DEFINITION-DESCRIPTION", 1, 0, false);
            declareFunction("blue_graph_generate_option_description", "BLUE-GRAPH-GENERATE-OPTION-DESCRIPTION", 1, 0, false);
            declareFunction("blue_graph_generate_node_description", "BLUE-GRAPH-GENERATE-NODE-DESCRIPTION", 1, 0, false);
            declareFunction("blue_graph_generate_edge_description", "BLUE-GRAPH-GENERATE-EDGE-DESCRIPTION", 1, 0, false);
            declareFunction("bbf_rtv_from_inference", "BBF-RTV-FROM-INFERENCE", 1, 0, false);
            declareFunction("bbf_rtv_unbound", "BBF-RTV-UNBOUND", 1, 0, false);
            declareFunction("bbf_rtv_from_inference_new", "BBF-RTV-FROM-INFERENCE-NEW", 1, 0, false);
            declareFunction("valid_rtv_justificationP", "VALID-RTV-JUSTIFICATION?", 2, 0, false);
            declareFunction("remove_rtv_irrelevant_justifications", "REMOVE-RTV-IRRELEVANT-JUSTIFICATIONS", 2, 0, false);
        }
        if (SubLFiles.USE_V2) {
            declareFunction("bff_rkf_irrelevant_terms", "BFF-RKF-IRRELEVANT-TERMS", 2, 0, false);
            declareFunction("bff_rtv_irrelevant_terms", "BFF-RTV-IRRELEVANT-TERMS", 2, 0, false);
        }
        return NIL;
    }

    public static SubLObject declare_blue_grapher_utilities_file_Previous() {
        declareMacro("define_blue_filter_fn", "DEFINE-BLUE-FILTER-FN");
        declareFunction("blue_filter_fns", "BLUE-FILTER-FNS", 0, 0, false);
        declareFunction("get_blue_filter_fn_data", "GET-BLUE-FILTER-FN-DATA", 1, 0, false);
        declareFunction("blue_filter_fn_p", "BLUE-FILTER-FN-P", 1, 0, false);
        declareFunction("get_blue_filter_fn_cost", "GET-BLUE-FILTER-FN-COST", 1, 0, false);
        declareFunction("get_blue_filter_fn_strength", "GET-BLUE-FILTER-FN-STRENGTH", 1, 0, false);
        declareFunction("get_blue_filter_fn_type", "GET-BLUE-FILTER-FN-TYPE", 1, 0, false);
        declareFunction("apply_blue_filter_fn", "APPLY-BLUE-FILTER-FN", 3, 1, false);
        declareFunction("blue_filter_fns_sorted", "BLUE-FILTER-FNS-SORTED", 0, 1, false);
        declareFunction("blue_apply_filter_fns", "BLUE-APPLY-FILTER-FNS", 3, 1, false);
        declareFunction("blue_filter_fn_keys", "BLUE-FILTER-FN-KEYS", 0, 1, false);
        declareFunction("blue_filter_fn_by_key", "BLUE-FILTER-FN-BY-KEY", 1, 0, false);
        declareFunction("bff_arbitrary_unions", "BFF-ARBITRARY-UNIONS", 2, 0, false);
        declareFunction("bff_cyc_kb_subset_collections", "BFF-CYC-KB-SUBSET-COLLECTIONS", 2, 0, false);
        declareFunction("bff_most_general_5", "BFF-MOST-GENERAL-5", 2, 0, false);
        declareFunction("bff_most_general_10", "BFF-MOST-GENERAL-10", 2, 0, false);
        declareFunction("bff_most_general_20", "BFF-MOST-GENERAL-20", 2, 0, false);
        declareMacro("define_blue_builder_fn", "DEFINE-BLUE-BUILDER-FN");
        declareFunction("get_blue_builder_fn_data", "GET-BLUE-BUILDER-FN-DATA", 1, 0, false);
        declareFunction("blue_builder_fn_p", "BLUE-BUILDER-FN-P", 1, 0, false);
        declareFunction("blue_builder", "BLUE-BUILDER", 2, 0, false);
        declareFunction("blue_builder_fn_keys", "BLUE-BUILDER-FN-KEYS", 0, 0, false);
        declareFunction("blue_builder_fn_by_key", "BLUE-BUILDER-FN-BY-KEY", 1, 0, false);
        declareFunction("get_blue_builder_fn_def_value", "GET-BLUE-BUILDER-FN-DEF-VALUE", 2, 0, false);
        declareFunction("bbf_forward_true", "BBF-FORWARD-TRUE", 1, 0, false);
        declareFunction("bbf_forward_true_internal", "BBF-FORWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_backward_true", "BBF-BACKWARD-TRUE", 1, 0, false);
        declareFunction("bbf_backward_true_internal", "BBF-BACKWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_forward_true", "BBF-MIN-FORWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_forward_true_internal", "BBF-MIN-FORWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_backward_true", "BBF-MIN-BACKWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_backward_true_internal", "BBF-MIN-BACKWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_ceilings_forward_true", "BBF-MIN-CEILINGS-FORWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_ceilings_forward_true_internal", "BBF-MIN-CEILINGS-FORWARD-TRUE-INTERNAL", 5, 1, false);
        declareFunction("bbf_min_forward_and_backward_true", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE", 1, 0, false);
        declareFunction("bbf_min_forward_and_backward_true_internal", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE-INTERNAL", 5, 0, false);
        declareFunction("bbf_min_forward_and_backward_true_internal_internal", "BBF-MIN-FORWARD-AND-BACKWARD-TRUE-INTERNAL-INTERNAL", 5, 2, false);
        declareFunction("bbf_script", "BBF-SCRIPT", 1, 0, false);
        declareFunction("bbf_inference_answers", "BBF-INFERENCE-ANSWERS", 1, 0, false);
        declareFunction("determine_edge_templates_for_inference", "DETERMINE-EDGE-TEMPLATES-FOR-INFERENCE", 1, 0, false);
        declareFunction("html_determine_edge_templates_for_inference", "HTML-DETERMINE-EDGE-TEMPLATES-FOR-INFERENCE", 1, 0, false);
        declareFunction("bbf_inference", "BBF-INFERENCE", 1, 0, false);
        declareFunction("bbf_rule", "BBF-RULE", 1, 0, false);
        declareFunction("blue_literal_to_edge", "BLUE-LITERAL-TO-EDGE", 3, 0, false);
        declareFunction("make_inference_hl_to_el_var_list", "MAKE-INFERENCE-HL-TO-EL-VAR-LIST", 1, 0, false);
        declareFunction("make_assertion_hl_to_el_var_list", "MAKE-ASSERTION-HL-TO-EL-VAR-LIST", 1, 0, false);
        declareFunction("bbf_make_edge_from_assertion", "BBF-MAKE-EDGE-FROM-ASSERTION", 1, 0, false);
        declareFunction("bbf_make_graph", "BBF-MAKE-GRAPH", 2, 1, false);
        declareFunction("bbf_make_edge", "BBF-MAKE-EDGE", 4, 4, false);
        declareFunction("bbf_make_edge_with_nodes", "BBF-MAKE-EDGE-WITH-NODES", 4, 2, false);
        declareFunction("bbf_make_node", "BBF-MAKE-NODE", 1, 2, false);
        declareFunction("blue_event_dispatcher_runningP", "BLUE-EVENT-DISPATCHER-RUNNING?", 0, 0, false);
        declareFunction("ensure_blue_event_dispatcher_running", "ENSURE-BLUE-EVENT-DISPATCHER-RUNNING", 0, 0, false);
        declareFunction("register_listener_with_blue_event_dispatcher", "REGISTER-LISTENER-WITH-BLUE-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("deregister_listener_with_blue_event_dispatcher", "DEREGISTER-LISTENER-WITH-BLUE-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("stop_blue_event_dispatcher", "STOP-BLUE-EVENT-DISPATCHER", 0, 0, false);
        declareFunction("start_blue_event_dispatcher", "START-BLUE-EVENT-DISPATCHER", 0, 0, false);
        declareFunction("blue_event", "BLUE-EVENT", 2, 0, false);
        declareFunction("blue_fetch_event", "BLUE-FETCH-EVENT", 1, 0, false);
        declareFunction("blue_fetch_uia_blue_event", "BLUE-FETCH-UIA-BLUE-EVENT", 1, 0, false);
        declareFunction("blue_current_message_mailbox_suite", "BLUE-CURRENT-MESSAGE-MAILBOX-SUITE", 0, 0, false);
        declareFunction("blue_ensure_blue_event_address", "BLUE-ENSURE-BLUE-EVENT-ADDRESS", 1, 0, false);
        declareFunction("map_blue_event_to_mailbox", "MAP-BLUE-EVENT-TO-MAILBOX", 1, 0, false);
        declareFunction("blue_map_event_to_blue_event", "BLUE-MAP-EVENT-TO-BLUE-EVENT", 1, 1, false);
        declareFunction("blue_graph_print_function_trampoline", "BLUE-GRAPH-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("blue_graph_p", "BLUE-GRAPH-P", 1, 0, false);
        new blue_grapher_utilities.$blue_graph_p$UnaryFunction();
        declareFunction("blue_graph_isg", "BLUE-GRAPH-ISG", 1, 0, false);
        declareFunction("blue_graph_node_label_map", "BLUE-GRAPH-NODE-LABEL-MAP", 1, 0, false);
        declareFunction("blue_graph_nodes", "BLUE-GRAPH-NODES", 1, 0, false);
        declareFunction("blue_graph_edge_label_map", "BLUE-GRAPH-EDGE-LABEL-MAP", 1, 0, false);
        declareFunction("blue_graph_edges", "BLUE-GRAPH-EDGES", 1, 0, false);
        declareFunction("blue_graph_node_definitions", "BLUE-GRAPH-NODE-DEFINITIONS", 1, 0, false);
        declareFunction("blue_graph_edge_definitions", "BLUE-GRAPH-EDGE-DEFINITIONS", 1, 0, false);
        declareFunction("blue_graph_options", "BLUE-GRAPH-OPTIONS", 1, 0, false);
        declareFunction("_csetf_blue_graph_isg", "_CSETF-BLUE-GRAPH-ISG", 2, 0, false);
        declareFunction("_csetf_blue_graph_node_label_map", "_CSETF-BLUE-GRAPH-NODE-LABEL-MAP", 2, 0, false);
        declareFunction("_csetf_blue_graph_nodes", "_CSETF-BLUE-GRAPH-NODES", 2, 0, false);
        declareFunction("_csetf_blue_graph_edge_label_map", "_CSETF-BLUE-GRAPH-EDGE-LABEL-MAP", 2, 0, false);
        declareFunction("_csetf_blue_graph_edges", "_CSETF-BLUE-GRAPH-EDGES", 2, 0, false);
        declareFunction("_csetf_blue_graph_node_definitions", "_CSETF-BLUE-GRAPH-NODE-DEFINITIONS", 2, 0, false);
        declareFunction("_csetf_blue_graph_edge_definitions", "_CSETF-BLUE-GRAPH-EDGE-DEFINITIONS", 2, 0, false);
        declareFunction("_csetf_blue_graph_options", "_CSETF-BLUE-GRAPH-OPTIONS", 2, 0, false);
        declareFunction("make_blue_graph", "MAKE-BLUE-GRAPH", 0, 1, false);
        declareFunction("visit_defstruct_blue_graph", "VISIT-DEFSTRUCT-BLUE-GRAPH", 2, 0, false);
        declareFunction("visit_defstruct_object_blue_graph_method", "VISIT-DEFSTRUCT-OBJECT-BLUE-GRAPH-METHOD", 2, 0, false);
        declareFunction("new_blue_graph", "NEW-BLUE-GRAPH", 0, 0, false);
        declareFunction("blue_graph_add_node_type", "BLUE-GRAPH-ADD-NODE-TYPE", 3, 0, false);
        declareFunction("blue_graph_add_edge_type", "BLUE-GRAPH-ADD-EDGE-TYPE", 3, 1, false);
        declareFunction("blue_graph_add_option", "BLUE-GRAPH-ADD-OPTION", 3, 0, false);
        declareFunction("blue_graph_add_node", "BLUE-GRAPH-ADD-NODE", 1, 2, false);
        declareFunction("blue_graph_node_p", "BLUE-GRAPH-NODE-P", 1, 0, false);
        declareFunction("blue_graph_maybe_add_node", "BLUE-GRAPH-MAYBE-ADD-NODE", 2, 1, false);
        declareFunction("blue_graph_node_get_id", "BLUE-GRAPH-NODE-GET-ID", 1, 0, false);
        declareFunction("blue_graph_node_get_type", "BLUE-GRAPH-NODE-GET-TYPE", 1, 0, false);
        declareFunction("blue_graph_node_get_label", "BLUE-GRAPH-NODE-GET-LABEL", 1, 0, false);
        declareFunction("blue_graph_get_node", "BLUE-GRAPH-GET-NODE", 2, 0, false);
        declareFunction("blue_graph_get_node_by_label", "BLUE-GRAPH-GET-NODE-BY-LABEL", 2, 0, false);
        declareFunction("blue_graph_resolve_node_label_reference", "BLUE-GRAPH-RESOLVE-NODE-LABEL-REFERENCE", 2, 0, false);
        declareFunction("blue_graph_edge_from", "BLUE-GRAPH-EDGE-FROM", 1, 0, false);
        declareFunction("blue_graph_edge_to", "BLUE-GRAPH-EDGE-TO", 1, 0, false);
        declareFunction("blue_graph_edge_get_type", "BLUE-GRAPH-EDGE-GET-TYPE", 1, 0, false);
        declareFunction("blue_graph_edge_get_label", "BLUE-GRAPH-EDGE-GET-LABEL", 1, 0, false);
        declareFunction("blue_graph_add_edge", "BLUE-GRAPH-ADD-EDGE", 3, 2, false);
        declareFunction("blue_graph_next_id", "BLUE-GRAPH-NEXT-ID", 1, 0, false);
        declareFunction("blue_graph_generate_description", "BLUE-GRAPH-GENERATE-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_definition_description", "BLUE-GRAPH-GENERATE-DEFINITION-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_option_description", "BLUE-GRAPH-GENERATE-OPTION-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_node_description", "BLUE-GRAPH-GENERATE-NODE-DESCRIPTION", 1, 0, false);
        declareFunction("blue_graph_generate_edge_description", "BLUE-GRAPH-GENERATE-EDGE-DESCRIPTION", 1, 0, false);
        declareFunction("bbf_rtv_from_inference", "BBF-RTV-FROM-INFERENCE", 1, 0, false);
        declareFunction("bbf_rtv_unbound", "BBF-RTV-UNBOUND", 1, 0, false);
        declareFunction("bbf_rtv_from_inference_new", "BBF-RTV-FROM-INFERENCE-NEW", 1, 0, false);
        declareFunction("valid_rtv_justificationP", "VALID-RTV-JUSTIFICATION?", 2, 0, false);
        declareFunction("remove_rtv_irrelevant_justifications", "REMOVE-RTV-IRRELEVANT-JUSTIFICATIONS", 2, 0, false);
        return NIL;
    }

    public static final SubLObject init_blue_grapher_utilities_file_alt() {
        deflexical("*BLUE-FILTER-FNS*", NIL != boundp($blue_filter_fns$) ? ((SubLObject) ($blue_filter_fns$.getGlobalValue())) : make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED));
        deflexical("*DEFAULT-BLUE-FILTER-FN-COST*", NIL != boundp($default_blue_filter_fn_cost$) ? ((SubLObject) ($default_blue_filter_fn_cost$.getGlobalValue())) : ZERO_INTEGER);
        deflexical("*DEFAULT-BLUE-FILTER-FN-STRENGTH*", NIL != boundp($default_blue_filter_fn_strength$) ? ((SubLObject) ($default_blue_filter_fn_strength$.getGlobalValue())) : ZERO_INTEGER);
        deflexical("*DEFAULT-BLUE-FILTER-FN-TYPE*", NIL != boundp($default_blue_filter_fn_type$) ? ((SubLObject) ($default_blue_filter_fn_type$.getGlobalValue())) : NIL);
        deflexical("*BLUE-BUILDER-FNS*", NIL != boundp($blue_builder_fns$) ? ((SubLObject) ($blue_builder_fns$.getGlobalValue())) : make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED));
        deflexical("*BLUE-BUILDER-FN-TO-DEFLIST-HASH*", NIL != boundp($blue_builder_fn_to_deflist_hash$) ? ((SubLObject) ($blue_builder_fn_to_deflist_hash$.getGlobalValue())) : make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED));
        deflexical("*INFERENCE-ANSWERS-DEFAULT-UNDETERMINED-RELATION*", $$conceptuallyRelated);
        deflexical("*BLUE-EVENT-DISPATCHER-LOCK*", make_lock($str_alt122$BLUE_EVENT_DISPATCHER_Lock));
        deflexical("*BLUE-EVENT-DISPATCHER-LISTENERS*", NIL != boundp($blue_event_dispatcher_listeners$) ? ((SubLObject) ($blue_event_dispatcher_listeners$.getGlobalValue())) : NIL);
        defparameter("*BLUE-MESSAGE-MAILBOX-SUITE*", message_mailboxes.new_message_mailbox_suite());
        deflexical("*BLUE-EVENT-TO-BLUE-EVENT-LISTENER*", event_broker.describe_funcall_listener($BLUE_EVENT, BLUE_MAP_EVENT_TO_BLUE_EVENT, UNPROVIDED, UNPROVIDED));
        defconstant("*DTP-BLUE-GRAPH*", BLUE_GRAPH);
        return NIL;
    }

    public static SubLObject init_blue_grapher_utilities_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*BLUE-FILTER-FNS*", SubLTrampolineFile.maybeDefault($blue_filter_fns$, $blue_filter_fns$, () -> make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED)));
            deflexical("*DEFAULT-BLUE-FILTER-FN-COST*", SubLTrampolineFile.maybeDefault($default_blue_filter_fn_cost$, $default_blue_filter_fn_cost$, ZERO_INTEGER));
            deflexical("*DEFAULT-BLUE-FILTER-FN-STRENGTH*", SubLTrampolineFile.maybeDefault($default_blue_filter_fn_strength$, $default_blue_filter_fn_strength$, ZERO_INTEGER));
            deflexical("*DEFAULT-BLUE-FILTER-FN-TYPE*", SubLTrampolineFile.maybeDefault($default_blue_filter_fn_type$, $default_blue_filter_fn_type$, NIL));
            deflexical("*BLUE-BUILDER-FNS*", SubLTrampolineFile.maybeDefault($blue_builder_fns$, $blue_builder_fns$, () -> make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED)));
            deflexical("*BLUE-BUILDER-FN-TO-DEFLIST-HASH*", SubLTrampolineFile.maybeDefault($blue_builder_fn_to_deflist_hash$, $blue_builder_fn_to_deflist_hash$, () -> make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED)));
            deflexical("*INFERENCE-ANSWERS-DEFAULT-UNDETERMINED-RELATION*", $$conceptuallyRelated);
            deflexical("*BLUE-EVENT-DISPATCHER-LOCK*", make_lock($str121$BLUE_EVENT_DISPATCHER_Lock));
            deflexical("*BLUE-EVENT-DISPATCHER-LISTENERS*", SubLTrampolineFile.maybeDefault($blue_event_dispatcher_listeners$, $blue_event_dispatcher_listeners$, NIL));
            defparameter("*BLUE-MESSAGE-MAILBOX-SUITE*", message_mailboxes.new_message_mailbox_suite());
            deflexical("*BLUE-EVENT-TO-BLUE-EVENT-LISTENER*", event_broker.describe_funcall_listener($BLUE_EVENT, BLUE_MAP_EVENT_TO_BLUE_EVENT, UNPROVIDED, UNPROVIDED));
            defconstant("*DTP-BLUE-GRAPH*", BLUE_GRAPH);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*BLUE-FILTER-FNS*", NIL != boundp($blue_filter_fns$) ? ((SubLObject) ($blue_filter_fns$.getGlobalValue())) : make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED));
            deflexical("*DEFAULT-BLUE-FILTER-FN-COST*", NIL != boundp($default_blue_filter_fn_cost$) ? ((SubLObject) ($default_blue_filter_fn_cost$.getGlobalValue())) : ZERO_INTEGER);
            deflexical("*DEFAULT-BLUE-FILTER-FN-STRENGTH*", NIL != boundp($default_blue_filter_fn_strength$) ? ((SubLObject) ($default_blue_filter_fn_strength$.getGlobalValue())) : ZERO_INTEGER);
            deflexical("*DEFAULT-BLUE-FILTER-FN-TYPE*", NIL != boundp($default_blue_filter_fn_type$) ? ((SubLObject) ($default_blue_filter_fn_type$.getGlobalValue())) : NIL);
            deflexical("*BLUE-BUILDER-FNS*", NIL != boundp($blue_builder_fns$) ? ((SubLObject) ($blue_builder_fns$.getGlobalValue())) : make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED));
            deflexical("*BLUE-BUILDER-FN-TO-DEFLIST-HASH*", NIL != boundp($blue_builder_fn_to_deflist_hash$) ? ((SubLObject) ($blue_builder_fn_to_deflist_hash$.getGlobalValue())) : make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED));
            deflexical("*BLUE-EVENT-DISPATCHER-LOCK*", make_lock($str_alt122$BLUE_EVENT_DISPATCHER_Lock));
            deflexical("*BLUE-EVENT-DISPATCHER-LISTENERS*", NIL != boundp($blue_event_dispatcher_listeners$) ? ((SubLObject) ($blue_event_dispatcher_listeners$.getGlobalValue())) : NIL);
        }
        return NIL;
    }

    public static SubLObject init_blue_grapher_utilities_file_Previous() {
        deflexical("*BLUE-FILTER-FNS*", SubLTrampolineFile.maybeDefault($blue_filter_fns$, $blue_filter_fns$, () -> make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED)));
        deflexical("*DEFAULT-BLUE-FILTER-FN-COST*", SubLTrampolineFile.maybeDefault($default_blue_filter_fn_cost$, $default_blue_filter_fn_cost$, ZERO_INTEGER));
        deflexical("*DEFAULT-BLUE-FILTER-FN-STRENGTH*", SubLTrampolineFile.maybeDefault($default_blue_filter_fn_strength$, $default_blue_filter_fn_strength$, ZERO_INTEGER));
        deflexical("*DEFAULT-BLUE-FILTER-FN-TYPE*", SubLTrampolineFile.maybeDefault($default_blue_filter_fn_type$, $default_blue_filter_fn_type$, NIL));
        deflexical("*BLUE-BUILDER-FNS*", SubLTrampolineFile.maybeDefault($blue_builder_fns$, $blue_builder_fns$, () -> make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED)));
        deflexical("*BLUE-BUILDER-FN-TO-DEFLIST-HASH*", SubLTrampolineFile.maybeDefault($blue_builder_fn_to_deflist_hash$, $blue_builder_fn_to_deflist_hash$, () -> make_hash_table(SIXTEEN_INTEGER, UNPROVIDED, UNPROVIDED)));
        deflexical("*INFERENCE-ANSWERS-DEFAULT-UNDETERMINED-RELATION*", $$conceptuallyRelated);
        deflexical("*BLUE-EVENT-DISPATCHER-LOCK*", make_lock($str121$BLUE_EVENT_DISPATCHER_Lock));
        deflexical("*BLUE-EVENT-DISPATCHER-LISTENERS*", SubLTrampolineFile.maybeDefault($blue_event_dispatcher_listeners$, $blue_event_dispatcher_listeners$, NIL));
        defparameter("*BLUE-MESSAGE-MAILBOX-SUITE*", message_mailboxes.new_message_mailbox_suite());
        deflexical("*BLUE-EVENT-TO-BLUE-EVENT-LISTENER*", event_broker.describe_funcall_listener($BLUE_EVENT, BLUE_MAP_EVENT_TO_BLUE_EVENT, UNPROVIDED, UNPROVIDED));
        defconstant("*DTP-BLUE-GRAPH*", BLUE_GRAPH);
        return NIL;
    }

    public static final SubLObject setup_blue_grapher_utilities_file_alt() {
        declare_defglobal($blue_filter_fns$);
        declare_defglobal($default_blue_filter_fn_cost$);
        declare_defglobal($default_blue_filter_fn_strength$);
        declare_defglobal($default_blue_filter_fn_type$);
        sethash(BFF_ARBITRARY_UNIONS, $blue_filter_fns$.getGlobalValue(), $list_alt18);
        register_api_predefined_function(BFF_ARBITRARY_UNIONS);
        sethash(BFF_RKF_IRRELEVANT_TERMS, $blue_filter_fns$.getGlobalValue(), $list_alt18);
        register_api_predefined_function(BFF_RKF_IRRELEVANT_TERMS);
        sethash(BFF_CYC_KB_SUBSET_COLLECTIONS, $blue_filter_fns$.getGlobalValue(), $list_alt18);
        register_api_predefined_function(BFF_CYC_KB_SUBSET_COLLECTIONS);
        sethash(BFF_RTV_IRRELEVANT_TERMS, $blue_filter_fns$.getGlobalValue(), $list_alt18);
        register_api_predefined_function(BFF_RTV_IRRELEVANT_TERMS);
        sethash(BFF_MOST_GENERAL_5, $blue_filter_fns$.getGlobalValue(), $list_alt26);
        register_api_predefined_function(BFF_MOST_GENERAL_5);
        sethash(BFF_MOST_GENERAL_10, $blue_filter_fns$.getGlobalValue(), $list_alt29);
        register_api_predefined_function(BFF_MOST_GENERAL_10);
        sethash(BFF_MOST_GENERAL_20, $blue_filter_fns$.getGlobalValue(), $list_alt31);
        register_api_predefined_function(BFF_MOST_GENERAL_20);
        declare_defglobal($blue_builder_fns$);
        declare_defglobal($blue_builder_fn_to_deflist_hash$);
        sethash(BBF_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt39);
        register_api_predefined_function(BBF_FORWARD_TRUE);
        sethash(BBF_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt47);
        register_api_predefined_function(BBF_BACKWARD_TRUE);
        sethash(BBF_MIN_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt49);
        register_api_predefined_function(BBF_MIN_FORWARD_TRUE);
        sethash(BBF_MIN_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt51);
        register_api_predefined_function(BBF_MIN_BACKWARD_TRUE);
        sethash(BBF_MIN_CEILINGS_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_CEILINGS_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt53);
        register_api_predefined_function(BBF_MIN_CEILINGS_FORWARD_TRUE);
        sethash(BBF_MIN_FORWARD_AND_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_FORWARD_AND_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt56);
        register_api_predefined_function(BBF_MIN_FORWARD_AND_BACKWARD_TRUE);
        sethash(BBF_SCRIPT, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_SCRIPT, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt60);
        register_api_predefined_function(BBF_SCRIPT);
        sethash(BBF_INFERENCE_ANSWERS, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_INFERENCE_ANSWERS, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt78);
        register_api_predefined_function(BBF_INFERENCE_ANSWERS);
        sethash(BBF_INFERENCE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt96);
        register_api_predefined_function(BBF_INFERENCE);
        sethash(BBF_RULE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RULE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt104);
        register_api_predefined_function(BBF_RULE);
        event_model.register_event_class($BLUE_GRAPHER_BASE_EVENT, $CYC_APPLICATION_EVENT, NIL);
        event_model.register_event_class($BLUE_EVENT, $BLUE_GRAPHER_BASE_EVENT, NIL);
        declare_defglobal($blue_event_dispatcher_listeners$);
        register_external_symbol(BLUE_FETCH_EVENT);
        register_listener_with_blue_event_dispatcher($blue_event_to_blue_event_listener$.getGlobalValue());
        register_method($print_object_method_table$.getGlobalValue(), $dtp_blue_graph$.getGlobalValue(), symbol_function(BLUE_GRAPH_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(BLUE_GRAPH_ISG, _CSETF_BLUE_GRAPH_ISG);
        def_csetf(BLUE_GRAPH_NODE_LABEL_MAP, _CSETF_BLUE_GRAPH_NODE_LABEL_MAP);
        def_csetf(BLUE_GRAPH_NODES, _CSETF_BLUE_GRAPH_NODES);
        def_csetf(BLUE_GRAPH_EDGE_LABEL_MAP, _CSETF_BLUE_GRAPH_EDGE_LABEL_MAP);
        def_csetf(BLUE_GRAPH_EDGES, _CSETF_BLUE_GRAPH_EDGES);
        def_csetf(BLUE_GRAPH_NODE_DEFINITIONS, _CSETF_BLUE_GRAPH_NODE_DEFINITIONS);
        def_csetf(BLUE_GRAPH_EDGE_DEFINITIONS, _CSETF_BLUE_GRAPH_EDGE_DEFINITIONS);
        def_csetf(BLUE_GRAPH_OPTIONS, _CSETF_BLUE_GRAPH_OPTIONS);
        identity(BLUE_GRAPH);
        sethash(BBF_RTV_FROM_INFERENCE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RTV_FROM_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt170);
        register_api_predefined_function(BBF_RTV_FROM_INFERENCE);
        sethash(BBF_RTV_UNBOUND, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RTV_UNBOUND, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt170);
        register_api_predefined_function(BBF_RTV_UNBOUND);
        sethash(BBF_RTV_FROM_INFERENCE_NEW, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RTV_FROM_INFERENCE_NEW, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt170);
        register_api_predefined_function(BBF_RTV_FROM_INFERENCE_NEW);
        return NIL;
    }

    public static SubLObject setup_blue_grapher_utilities_file() {
        if (SubLFiles.USE_V1) {
            declare_defglobal($blue_filter_fns$);
            declare_defglobal($default_blue_filter_fn_cost$);
            declare_defglobal($default_blue_filter_fn_strength$);
            declare_defglobal($default_blue_filter_fn_type$);
            sethash(BFF_ARBITRARY_UNIONS, $blue_filter_fns$.getGlobalValue(), $list18);
            register_api_predefined_function(BFF_ARBITRARY_UNIONS);
            sethash(BFF_CYC_KB_SUBSET_COLLECTIONS, $blue_filter_fns$.getGlobalValue(), $list18);
            register_api_predefined_function(BFF_CYC_KB_SUBSET_COLLECTIONS);
            sethash(BFF_MOST_GENERAL_5, $blue_filter_fns$.getGlobalValue(), $list23);
            register_api_predefined_function(BFF_MOST_GENERAL_5);
            sethash(BFF_MOST_GENERAL_10, $blue_filter_fns$.getGlobalValue(), $list26);
            register_api_predefined_function(BFF_MOST_GENERAL_10);
            sethash(BFF_MOST_GENERAL_20, $blue_filter_fns$.getGlobalValue(), $list28);
            register_api_predefined_function(BFF_MOST_GENERAL_20);
            declare_defglobal($blue_builder_fns$);
            declare_defglobal($blue_builder_fn_to_deflist_hash$);
            sethash(BBF_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list36);
            register_api_predefined_function(BBF_FORWARD_TRUE);
            sethash(BBF_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list44);
            register_api_predefined_function(BBF_BACKWARD_TRUE);
            sethash(BBF_MIN_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_MIN_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list46);
            register_api_predefined_function(BBF_MIN_FORWARD_TRUE);
            sethash(BBF_MIN_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_MIN_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list48);
            register_api_predefined_function(BBF_MIN_BACKWARD_TRUE);
            sethash(BBF_MIN_CEILINGS_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_MIN_CEILINGS_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list50);
            register_api_predefined_function(BBF_MIN_CEILINGS_FORWARD_TRUE);
            sethash(BBF_MIN_FORWARD_AND_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_MIN_FORWARD_AND_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list53);
            register_api_predefined_function(BBF_MIN_FORWARD_AND_BACKWARD_TRUE);
            sethash(BBF_SCRIPT, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_SCRIPT, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list57);
            register_api_predefined_function(BBF_SCRIPT);
            sethash(BBF_INFERENCE_ANSWERS, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_INFERENCE_ANSWERS, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list80);
            register_api_predefined_function(BBF_INFERENCE_ANSWERS);
            sethash(BBF_INFERENCE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list98);
            register_api_predefined_function(BBF_INFERENCE);
            sethash(BBF_RULE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_RULE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list106);
            register_api_predefined_function(BBF_RULE);
            event_model.register_event_class($BLUE_GRAPHER_BASE_EVENT, $CYC_APPLICATION_EVENT, NIL);
            event_model.register_event_class($BLUE_EVENT, $BLUE_GRAPHER_BASE_EVENT, NIL);
            declare_defglobal($blue_event_dispatcher_listeners$);
            register_external_symbol(BLUE_FETCH_EVENT);
            register_listener_with_blue_event_dispatcher($blue_event_to_blue_event_listener$.getGlobalValue());
            register_method($print_object_method_table$.getGlobalValue(), $dtp_blue_graph$.getGlobalValue(), symbol_function(BLUE_GRAPH_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list133);
            def_csetf(BLUE_GRAPH_ISG, _CSETF_BLUE_GRAPH_ISG);
            def_csetf(BLUE_GRAPH_NODE_LABEL_MAP, _CSETF_BLUE_GRAPH_NODE_LABEL_MAP);
            def_csetf(BLUE_GRAPH_NODES, _CSETF_BLUE_GRAPH_NODES);
            def_csetf(BLUE_GRAPH_EDGE_LABEL_MAP, _CSETF_BLUE_GRAPH_EDGE_LABEL_MAP);
            def_csetf(BLUE_GRAPH_EDGES, _CSETF_BLUE_GRAPH_EDGES);
            def_csetf(BLUE_GRAPH_NODE_DEFINITIONS, _CSETF_BLUE_GRAPH_NODE_DEFINITIONS);
            def_csetf(BLUE_GRAPH_EDGE_DEFINITIONS, _CSETF_BLUE_GRAPH_EDGE_DEFINITIONS);
            def_csetf(BLUE_GRAPH_OPTIONS, _CSETF_BLUE_GRAPH_OPTIONS);
            identity(BLUE_GRAPH);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_blue_graph$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_BLUE_GRAPH_METHOD));
            sethash(BBF_RTV_FROM_INFERENCE, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_RTV_FROM_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list175);
            register_api_predefined_function(BBF_RTV_FROM_INFERENCE);
            sethash(BBF_RTV_UNBOUND, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_RTV_UNBOUND, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list175);
            register_api_predefined_function(BBF_RTV_UNBOUND);
            sethash(BBF_RTV_FROM_INFERENCE_NEW, $blue_builder_fns$.getGlobalValue(), T);
            sethash(BBF_RTV_FROM_INFERENCE_NEW, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list175);
            register_api_predefined_function(BBF_RTV_FROM_INFERENCE_NEW);
        }
        if (SubLFiles.USE_V2) {
            sethash(BFF_RKF_IRRELEVANT_TERMS, $blue_filter_fns$.getGlobalValue(), $list_alt18);
            register_api_predefined_function(BFF_RKF_IRRELEVANT_TERMS);
            sethash(BFF_RTV_IRRELEVANT_TERMS, $blue_filter_fns$.getGlobalValue(), $list_alt18);
            register_api_predefined_function(BFF_RTV_IRRELEVANT_TERMS);
            sethash(BFF_MOST_GENERAL_5, $blue_filter_fns$.getGlobalValue(), $list_alt26);
            sethash(BFF_MOST_GENERAL_10, $blue_filter_fns$.getGlobalValue(), $list_alt29);
            sethash(BFF_MOST_GENERAL_20, $blue_filter_fns$.getGlobalValue(), $list_alt31);
            sethash(BBF_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt39);
            sethash(BBF_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt47);
            sethash(BBF_MIN_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt49);
            sethash(BBF_MIN_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt51);
            sethash(BBF_MIN_CEILINGS_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt53);
            sethash(BBF_MIN_FORWARD_AND_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt56);
            sethash(BBF_SCRIPT, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt60);
            sethash(BBF_INFERENCE_ANSWERS, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt78);
            sethash(BBF_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt96);
            sethash(BBF_RULE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt104);
            sethash(BBF_RTV_FROM_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt170);
            sethash(BBF_RTV_UNBOUND, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt170);
            sethash(BBF_RTV_FROM_INFERENCE_NEW, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list_alt170);
        }
        return NIL;
    }

    public static SubLObject setup_blue_grapher_utilities_file_Previous() {
        declare_defglobal($blue_filter_fns$);
        declare_defglobal($default_blue_filter_fn_cost$);
        declare_defglobal($default_blue_filter_fn_strength$);
        declare_defglobal($default_blue_filter_fn_type$);
        sethash(BFF_ARBITRARY_UNIONS, $blue_filter_fns$.getGlobalValue(), $list18);
        register_api_predefined_function(BFF_ARBITRARY_UNIONS);
        sethash(BFF_CYC_KB_SUBSET_COLLECTIONS, $blue_filter_fns$.getGlobalValue(), $list18);
        register_api_predefined_function(BFF_CYC_KB_SUBSET_COLLECTIONS);
        sethash(BFF_MOST_GENERAL_5, $blue_filter_fns$.getGlobalValue(), $list23);
        register_api_predefined_function(BFF_MOST_GENERAL_5);
        sethash(BFF_MOST_GENERAL_10, $blue_filter_fns$.getGlobalValue(), $list26);
        register_api_predefined_function(BFF_MOST_GENERAL_10);
        sethash(BFF_MOST_GENERAL_20, $blue_filter_fns$.getGlobalValue(), $list28);
        register_api_predefined_function(BFF_MOST_GENERAL_20);
        declare_defglobal($blue_builder_fns$);
        declare_defglobal($blue_builder_fn_to_deflist_hash$);
        sethash(BBF_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list36);
        register_api_predefined_function(BBF_FORWARD_TRUE);
        sethash(BBF_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list44);
        register_api_predefined_function(BBF_BACKWARD_TRUE);
        sethash(BBF_MIN_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list46);
        register_api_predefined_function(BBF_MIN_FORWARD_TRUE);
        sethash(BBF_MIN_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list48);
        register_api_predefined_function(BBF_MIN_BACKWARD_TRUE);
        sethash(BBF_MIN_CEILINGS_FORWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_CEILINGS_FORWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list50);
        register_api_predefined_function(BBF_MIN_CEILINGS_FORWARD_TRUE);
        sethash(BBF_MIN_FORWARD_AND_BACKWARD_TRUE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_MIN_FORWARD_AND_BACKWARD_TRUE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list53);
        register_api_predefined_function(BBF_MIN_FORWARD_AND_BACKWARD_TRUE);
        sethash(BBF_SCRIPT, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_SCRIPT, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list57);
        register_api_predefined_function(BBF_SCRIPT);
        sethash(BBF_INFERENCE_ANSWERS, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_INFERENCE_ANSWERS, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list80);
        register_api_predefined_function(BBF_INFERENCE_ANSWERS);
        sethash(BBF_INFERENCE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list98);
        register_api_predefined_function(BBF_INFERENCE);
        sethash(BBF_RULE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RULE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list106);
        register_api_predefined_function(BBF_RULE);
        event_model.register_event_class($BLUE_GRAPHER_BASE_EVENT, $CYC_APPLICATION_EVENT, NIL);
        event_model.register_event_class($BLUE_EVENT, $BLUE_GRAPHER_BASE_EVENT, NIL);
        declare_defglobal($blue_event_dispatcher_listeners$);
        register_external_symbol(BLUE_FETCH_EVENT);
        register_listener_with_blue_event_dispatcher($blue_event_to_blue_event_listener$.getGlobalValue());
        register_method($print_object_method_table$.getGlobalValue(), $dtp_blue_graph$.getGlobalValue(), symbol_function(BLUE_GRAPH_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list133);
        def_csetf(BLUE_GRAPH_ISG, _CSETF_BLUE_GRAPH_ISG);
        def_csetf(BLUE_GRAPH_NODE_LABEL_MAP, _CSETF_BLUE_GRAPH_NODE_LABEL_MAP);
        def_csetf(BLUE_GRAPH_NODES, _CSETF_BLUE_GRAPH_NODES);
        def_csetf(BLUE_GRAPH_EDGE_LABEL_MAP, _CSETF_BLUE_GRAPH_EDGE_LABEL_MAP);
        def_csetf(BLUE_GRAPH_EDGES, _CSETF_BLUE_GRAPH_EDGES);
        def_csetf(BLUE_GRAPH_NODE_DEFINITIONS, _CSETF_BLUE_GRAPH_NODE_DEFINITIONS);
        def_csetf(BLUE_GRAPH_EDGE_DEFINITIONS, _CSETF_BLUE_GRAPH_EDGE_DEFINITIONS);
        def_csetf(BLUE_GRAPH_OPTIONS, _CSETF_BLUE_GRAPH_OPTIONS);
        identity(BLUE_GRAPH);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_blue_graph$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_BLUE_GRAPH_METHOD));
        sethash(BBF_RTV_FROM_INFERENCE, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RTV_FROM_INFERENCE, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list175);
        register_api_predefined_function(BBF_RTV_FROM_INFERENCE);
        sethash(BBF_RTV_UNBOUND, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RTV_UNBOUND, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list175);
        register_api_predefined_function(BBF_RTV_UNBOUND);
        sethash(BBF_RTV_FROM_INFERENCE_NEW, $blue_builder_fns$.getGlobalValue(), T);
        sethash(BBF_RTV_FROM_INFERENCE_NEW, $blue_builder_fn_to_deflist_hash$.getGlobalValue(), $list175);
        register_api_predefined_function(BBF_RTV_FROM_INFERENCE_NEW);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_blue_grapher_utilities_file();
    }

    @Override
    public void initializeVariables() {
        init_blue_grapher_utilities_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_blue_grapher_utilities_file();
    }

    static {
    }

    public static final class $blue_graph_native extends SubLStructNative {
        public SubLObject $isg;

        public SubLObject $node_label_map;

        public SubLObject $nodes;

        public SubLObject $edge_label_map;

        public SubLObject $edges;

        public SubLObject $node_definitions;

        public SubLObject $edge_definitions;

        public SubLObject $options;

        private static final SubLStructDeclNative structDecl;

        public $blue_graph_native() {
            blue_grapher_utilities.$blue_graph_native.this.$isg = Lisp.NIL;
            blue_grapher_utilities.$blue_graph_native.this.$node_label_map = Lisp.NIL;
            blue_grapher_utilities.$blue_graph_native.this.$nodes = Lisp.NIL;
            blue_grapher_utilities.$blue_graph_native.this.$edge_label_map = Lisp.NIL;
            blue_grapher_utilities.$blue_graph_native.this.$edges = Lisp.NIL;
            blue_grapher_utilities.$blue_graph_native.this.$node_definitions = Lisp.NIL;
            blue_grapher_utilities.$blue_graph_native.this.$edge_definitions = Lisp.NIL;
            blue_grapher_utilities.$blue_graph_native.this.$options = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return blue_grapher_utilities.$blue_graph_native.this.$isg;
        }

        @Override
        public SubLObject getField3() {
            return blue_grapher_utilities.$blue_graph_native.this.$node_label_map;
        }

        @Override
        public SubLObject getField4() {
            return blue_grapher_utilities.$blue_graph_native.this.$nodes;
        }

        @Override
        public SubLObject getField5() {
            return blue_grapher_utilities.$blue_graph_native.this.$edge_label_map;
        }

        @Override
        public SubLObject getField6() {
            return blue_grapher_utilities.$blue_graph_native.this.$edges;
        }

        @Override
        public SubLObject getField7() {
            return blue_grapher_utilities.$blue_graph_native.this.$node_definitions;
        }

        @Override
        public SubLObject getField8() {
            return blue_grapher_utilities.$blue_graph_native.this.$edge_definitions;
        }

        @Override
        public SubLObject getField9() {
            return blue_grapher_utilities.$blue_graph_native.this.$options;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$isg = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$node_label_map = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$nodes = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$edge_label_map = value;
        }

        @Override
        public SubLObject setField6(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$edges = value;
        }

        @Override
        public SubLObject setField7(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$node_definitions = value;
        }

        @Override
        public SubLObject setField8(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$edge_definitions = value;
        }

        @Override
        public SubLObject setField9(final SubLObject value) {
            return blue_grapher_utilities.$blue_graph_native.this.$options = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.blue_grapher_utilities.$blue_graph_native.class, BLUE_GRAPH, BLUE_GRAPH_P, $list127, $list128, new String[]{ "$isg", "$node_label_map", "$nodes", "$edge_label_map", "$edges", "$node_definitions", "$edge_definitions", "$options" }, $list129, $list130, DEFAULT_STRUCT_PRINT_FUNCTION);
        }
    }

    public static final class $blue_graph_p$UnaryFunction extends UnaryFunction {
        public $blue_graph_p$UnaryFunction() {
            super(extractFunctionNamed("BLUE-GRAPH-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return blue_graph_p(arg1);
        }
    }

    static private final SubLList $list_alt1 = list(makeSymbol("NAME"), makeSymbol("ARGLIST"), makeSymbol("DEFLIST"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLString $str_alt13$APPLY_BLUE_FILTER_FN___A_is_not_a = makeString("APPLY-BLUE-FILTER-FN: ~A is not a blue-filter-fn-p");

    static private final SubLList $list_alt18 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), TEN_INTEGER);

    private static final SubLSymbol BFF_RKF_IRRELEVANT_TERMS = makeSymbol("BFF-RKF-IRRELEVANT-TERMS");

    private static final SubLSymbol BFF_RTV_IRRELEVANT_TERMS = makeSymbol("BFF-RTV-IRRELEVANT-TERMS");



    static private final SubLList $list_alt26 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), makeInteger(500), $TYPE, makeKeyword("POST-MINIMIZATION"));

    static private final SubLSymbol $sym27$GENERALITY_ESTIMATE_ = makeSymbol("GENERALITY-ESTIMATE>");

    static private final SubLList $list_alt29 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), makeInteger(1000), $TYPE, makeKeyword("POST-MINIMIZATION"));

    static private final SubLList $list_alt31 = list($COST, ONE_INTEGER, makeKeyword("STRENGTH"), makeInteger(2000), $TYPE, makeKeyword("POST-MINIMIZATION"));

    static private final SubLList $list_alt34 = list(makeSymbol("*BLUE-BUILDER-FNS*"), T);

    static private final SubLString $str_alt35$BLUE_BUILDER___A_is_not_a_blue_bu = makeString("BLUE-BUILDER: ~A is not a blue-builder-fn-p");

    static private final SubLSymbol $sym36$STRING_ = makeSymbol("STRING<");

    static private final SubLList $list_alt39 = list(makeKeyword("HELP-STRING"), makeString("Show forward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    static private final SubLList $list_alt47 = list(makeKeyword("HELP-STRING"), makeString("Show backward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    static private final SubLList $list_alt49 = list(makeKeyword("HELP-STRING"), makeString("Show minimum forward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    static private final SubLList $list_alt51 = list(makeKeyword("HELP-STRING"), makeString("Show minimum backward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)"));

    static private final SubLList $list_alt53 = list(makeKeyword("HELP-STRING"), makeString("Show the minimal ceilings of all forward edges from FORTS (nodes) through PREDS (edges) in MT and iterate DEPTH times.  (Forts failing FILTER-FNS are not shown.) (EXPERIMENTAL)"));

    static private final SubLList $list_alt56 = list(makeKeyword("HELP-STRING"), makeString("Show minimum forward and backward edges from FORTS (nodes) through PREDS (edges) in MT to DEPTH.  (Forts failing FILTER-FNS are not shown.)  (Note that this is *expensive* for higher depths) (EXPERIMENTAL)"));

    static private final SubLList $list_alt60 = list(makeKeyword("HELP-STRING"), makeString("Create a graph representation of a script (a spec of #$Situation) that shows the temporal ordering of scenes and the actors and roles involved in each scene.  FORTS must be a singleton containing the script term."));

    static private final SubLList $list_alt63 = list(TWO_INTEGER, THREE_INTEGER);

    public static final SubLObject $const64$QuasiTemporalSubSituationTypesPre = reader_make_constant_shell("QuasiTemporalSubSituationTypesPredicate");

    static private final SubLList $list_alt70 = list(TWO_INTEGER, THREE_INTEGER, FOUR_INTEGER);

    public static final SubLObject $const71$SituationTypeRelation_BasicActorT = reader_make_constant_shell("SituationTypeRelation-BasicActorTypesAndRolesForSubSitTypes");

    public static final SubLObject $const72$individualPlaysRoleInSubSituation = reader_make_constant_shell("individualPlaysRoleInSubSituationType");

    public static final SubLObject $const74$SituationTypeRelation_SitTypeToSu = reader_make_constant_shell("SituationTypeRelation-SitTypeToSubSitTypeBasic");

    static private final SubLList $list_alt75 = list(TWO_INTEGER, THREE_INTEGER, FOUR_INTEGER, FIVE_INTEGER, SIX_INTEGER);

    public static final SubLObject $const76$GraphicallyEditableRoleMappingPre = reader_make_constant_shell("GraphicallyEditableRoleMappingPredicate");

    static private final SubLList $list_alt78 = list(makeKeyword("HELP-STRING"), makeString("Graph the results of an inference by graphing the GAFs the result when filling in the bindings back into the query."));

    static private final SubLString $str_alt93$_____ = makeString(" --- ");

    static private final SubLString $str_alt94$______ = makeString(" ---> ");

    static private final SubLList $list_alt96 = list(makeKeyword("HELP-STRING"), makeString("Graph an inference query formula by taking the neg-lits and pos-lits, converting the HL vars to EL, then treating them as edges."));

    static private final SubLString $str_alt99$Inference_not_found_ = makeString("Inference not found.");

    static private final SubLList $list_alt101 = list(makeSymbol("MT"), makeSymbol("ASENT"));

    static private final SubLList $list_alt104 = list(makeKeyword("HELP-STRING"), makeString("Graph a rule by taking the neg-lits and pos-lits, converting the HL vars to EL, then treating them as edges."));

    static private final SubLList $list_alt106 = cons(makeSymbol("EL-VAR"), makeSymbol("HL-VAR"));

    static private final SubLString $str_alt122$BLUE_EVENT_DISPATCHER_Lock = makeString("BLUE-EVENT-DISPATCHER Lock");

    static private final SubLList $list_alt128 = list(makeSymbol("ISG"), makeSymbol("NODE-LABEL-MAP"), makeSymbol("NODES"), makeSymbol("EDGE-LABEL-MAP"), makeSymbol("EDGES"), makeSymbol("NODE-DEFINITIONS"), makeSymbol("EDGE-DEFINITIONS"), makeSymbol("OPTIONS"));

    static private final SubLList $list_alt129 = list(makeKeyword("ISG"), makeKeyword("NODE-LABEL-MAP"), makeKeyword("NODES"), makeKeyword("EDGE-LABEL-MAP"), makeKeyword("EDGES"), makeKeyword("NODE-DEFINITIONS"), makeKeyword("EDGE-DEFINITIONS"), makeKeyword("OPTIONS"));

    static private final SubLList $list_alt130 = list(makeSymbol("BLUE-GRAPH-ISG"), makeSymbol("BLUE-GRAPH-NODE-LABEL-MAP"), makeSymbol("BLUE-GRAPH-NODES"), makeSymbol("BLUE-GRAPH-EDGE-LABEL-MAP"), makeSymbol("BLUE-GRAPH-EDGES"), makeSymbol("BLUE-GRAPH-NODE-DEFINITIONS"), makeSymbol("BLUE-GRAPH-EDGE-DEFINITIONS"), makeSymbol("BLUE-GRAPH-OPTIONS"));

    static private final SubLList $list_alt131 = list(makeSymbol("_CSETF-BLUE-GRAPH-ISG"), makeSymbol("_CSETF-BLUE-GRAPH-NODE-LABEL-MAP"), makeSymbol("_CSETF-BLUE-GRAPH-NODES"), makeSymbol("_CSETF-BLUE-GRAPH-EDGE-LABEL-MAP"), makeSymbol("_CSETF-BLUE-GRAPH-EDGES"), makeSymbol("_CSETF-BLUE-GRAPH-NODE-DEFINITIONS"), makeSymbol("_CSETF-BLUE-GRAPH-EDGE-DEFINITIONS"), makeSymbol("_CSETF-BLUE-GRAPH-OPTIONS"));

    static private final SubLString $str_alt156$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLList $list_alt170 = list(makeKeyword("HELP-STRING"), makeString(""));
}

/**
 * Total time: 917 ms
 */
