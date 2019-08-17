/**
 * import com.cyc.cycjava.cycl.access_macros;
 */
/**
 * import com.cyc.cycjava.cycl.clauses;
 */
/**
 * import com.cyc.cycjava.cycl.constant_handles;
 */
/**
 * import com.cyc.cycjava.cycl.control_vars;
 */
/**
 * import com.cyc.cycjava.cycl.dictionary;
 */
/**
 * import com.cyc.cycjava.cycl.dictionary_contents;
 */
/**
 * import com.cyc.cycjava.cycl.dictionary_utilities;
 */
/**
 * import com.cyc.cycjava.cycl.el_utilities;
 */
/**
 * import com.cyc.cycjava.cycl.id_index;
 */
/**
 * import com.cyc.cycjava.cycl.kb_accessors;
 */
/**
 * import com.cyc.cycjava.cycl.list_utilities;
 */
/**
 * import com.cyc.cycjava.cycl.mt_relevance_macros;
 */
/**
 * import com.cyc.cycjava.cycl.number_utilities;
 */
/**
 * import com.cyc.cycjava.cycl.set;
 */
/**
 * import com.cyc.cycjava.cycl.set_contents;
 */
/**
 * import com.cyc.cycjava.cycl.subl_macro_promotions;
 */
/**
 * import com.cyc.cycjava.cycl.subl_promotions;
 */
/**
 * import com.cyc.cycjava.cycl.utilities_macros;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_czer;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_enumerated_types;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.*;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_link;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_query;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_proof;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_tactic;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_macros;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_metrics;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_min_transformation_depth;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_modules;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_tactician_strategic_uninterestingness;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_answer;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_join;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_join_ordered;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_removal;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_restriction;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_rewrite;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_split;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_transformation;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_worker_union;
 */
/**
 * import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem.$problem_native;
 */
package com.cyc.cycjava.cycl.inference.harness;

import static com.cyc.cycjava.cycl.access_macros.register_macro_helper;
import static com.cyc.cycjava.cycl.control_vars.$inference_debugP$;
import static com.cyc.cycjava.cycl.el_utilities.hl_ground_tree_p;
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
import static com.cyc.cycjava.cycl.utilities_macros.within_normal_forward_inferenceP;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eql;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryKey;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryValue;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.gethash_without_values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorNextEntry;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.make_hash_table;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.releaseEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.sethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find_if;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.booleanp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
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
import com.cyc.cycjava.cycl.bindings;
import com.cyc.cycjava.cycl.clauses;
import com.cyc.cycjava.cycl.dictionary;
import com.cyc.cycjava.cycl.dictionary_contents;
import com.cyc.cycjava.cycl.dictionary_utilities;
import com.cyc.cycjava.cycl.format_nil;
import com.cyc.cycjava.cycl.kb_accessors;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.memoization_state;
import com.cyc.cycjava.cycl.mt_relevance_macros;
import com.cyc.cycjava.cycl.number_utilities;
import com.cyc.cycjava.cycl.set;
import com.cyc.cycjava.cycl.set_contents;
import com.cyc.cycjava.cycl.subl_promotions;
import com.cyc.cycjava.cycl.inference.inference_trampolines;
import com.cyc.cycjava.cycl.inference.leviathan;
import com.cyc.cycjava.cycl.inference.modules.preference_modules;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLSpecialOperatorDeclarations;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDecl;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDeclNative;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sxhash;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.UnaryFunction;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLStructNative;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;

// public static final class $sxhash_problem_method$UnaryFunction extends UnaryFunction {
// public $sxhash_problem_method$UnaryFunction() {
// super(extractFunctionNamed("SXHASH-PROBLEM-METHOD"));
// }
// 
// public SubLObject processItem_alt(SubLObject arg1) {
// return sxhash_problem_method(arg1);
// }
// }
public final class inference_datastructures_problem extends SubLTranslatedFile implements V12 {
    public static final SubLSymbol _CSETF_PROB_ARGUMENT_LINK_BINDINGS_INDEX = makeSymbol("_CSETF-PROB-ARGUMENT-LINK-BINDINGS-INDEX");

    public static final SubLSymbol _CSETF_PROB_ARGUMENT_LINKS = makeSymbol("_CSETF-PROB-ARGUMENT-LINKS");

    public static final SubLSymbol _CSETF_PROB_DEPENDENT_LINKS = makeSymbol("_CSETF-PROB-DEPENDENT-LINKS");

    public static final SubLSymbol _CSETF_PROB_PROOF_BINDINGS_INDEX = makeSymbol("_CSETF-PROB-PROOF-BINDINGS-INDEX");

    public static final SubLSymbol _CSETF_PROB_QUERY = makeSymbol("_CSETF-PROB-QUERY");

    public static final SubLSymbol _CSETF_PROB_STATUS = makeSymbol("_CSETF-PROB-STATUS");

    public static final SubLSymbol _CSETF_PROB_STORE = makeSymbol("_CSETF-PROB-STORE");

    public static final SubLSymbol _CSETF_PROB_SUID = makeSymbol("_CSETF-PROB-SUID");

    public static final SubLSymbol _CSETF_PROB_TACTICS = makeSymbol("_CSETF-PROB-TACTICS");

    static private final SubLString $$$Ignore_the_crazy_problems = makeString("Ignore the crazy problems");

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    private static final SubLSymbol $ARGUMENT_LINK_BINDINGS_INDEX = makeKeyword("ARGUMENT-LINK-BINDINGS-INDEX");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_problem$ = makeSymbol("*DTP-PROBLEM*");

    public static final SubLSymbol $empty_clauses$ = makeSymbol("*EMPTY-CLAUSES*");

    private static final SubLSymbol $GENERALIZED_REMOVAL_OR_REWRITE = makeKeyword("GENERALIZED-REMOVAL-OR-REWRITE");

    public static final SubLInteger $int$10000 = makeInteger(10000);

    public static final SubLInteger $int$212 = makeInteger(212);

    static private final SubLList $list131 = list(list(makeSymbol("SUPPORTED-PROBLEM-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list137 = list(list(makeSymbol("SUPPORTING-PROBLEM-VAR"), makeSymbol("VARIABLE-MAP-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list3 = list(new SubLObject[] { makeSymbol("SUID"), makeSymbol("STORE"), makeSymbol("QUERY"), makeSymbol("FREE-HL-VARS"), makeSymbol("STATUS"), makeSymbol("DEPENDENT-LINKS"), makeSymbol("ARGUMENT-LINKS"), makeSymbol("TACTICS"), makeSymbol("PROOF-BINDINGS-INDEX"),
	    makeSymbol("ARGUMENT-LINK-BINDINGS-INDEX"), makeSymbol("BACKCHAIN-REQUIRED"), makeSymbol("MEMOIZATION-STATE") });

    static private final SubLList $list4 = list(new SubLObject[] { $SUID, makeKeyword("STORE"), makeKeyword("QUERY"), makeKeyword("FREE-HL-VARS"), makeKeyword("STATUS"), makeKeyword("DEPENDENT-LINKS"), makeKeyword("ARGUMENT-LINKS"), makeKeyword("TACTICS"),
	    makeKeyword("PROOF-BINDINGS-INDEX"), makeKeyword("ARGUMENT-LINK-BINDINGS-INDEX"), makeKeyword("BACKCHAIN-REQUIRED"), makeKeyword("MEMOIZATION-STATE") });

    static private final SubLList $list5 = list(new SubLObject[] { makeSymbol("PROB-SUID"), makeSymbol("PROB-STORE"), makeSymbol("PROB-QUERY"), makeSymbol("PROB-FREE-HL-VARS"), makeSymbol("PROB-STATUS"), makeSymbol("PROB-DEPENDENT-LINKS"), makeSymbol("PROB-ARGUMENT-LINKS"),
	    makeSymbol("PROB-TACTICS"), makeSymbol("PROB-PROOF-BINDINGS-INDEX"), makeSymbol("PROB-ARGUMENT-LINK-BINDINGS-INDEX"), makeSymbol("PROB-BACKCHAIN-REQUIRED"), makeSymbol("PROB-MEMOIZATION-STATE") });

    static private final SubLList $list6 = list(new SubLObject[] { makeSymbol("_CSETF-PROB-SUID"), makeSymbol("_CSETF-PROB-STORE"), makeSymbol("_CSETF-PROB-QUERY"), makeSymbol("_CSETF-PROB-FREE-HL-VARS"), makeSymbol("_CSETF-PROB-STATUS"), makeSymbol("_CSETF-PROB-DEPENDENT-LINKS"),
	    makeSymbol("_CSETF-PROB-ARGUMENT-LINKS"), makeSymbol("_CSETF-PROB-TACTICS"), makeSymbol("_CSETF-PROB-PROOF-BINDINGS-INDEX"), makeSymbol("_CSETF-PROB-ARGUMENT-LINK-BINDINGS-INDEX"), makeSymbol("_CSETF-PROB-BACKCHAIN-REQUIRED"), makeSymbol("_CSETF-PROB-MEMOIZATION-STATE") });

    // deflexical
    /**
     * The maximum number of tactics (of any status) that can be on a single
     * problem. Attempting to add an additional tactic after this number yields an
     * error.
     */
    @LispMethod(comment = "The maximum number of tactics (of any status) that can be on a single\r\nproblem. Attempting to add an additional tactic after this number yields an\r\nerror.\ndeflexical\nThe maximum number of tactics (of any status) that can be on a single\nproblem. Attempting to add an additional tactic after this number yields an\nerror.")
    public static final SubLSymbol $max_problem_tactics$ = makeSymbol("*MAX-PROBLEM-TACTICS*");

    // defparameter
    /**
     * Temporary control variable; when non-nil min-transformation-depth is computed
     * from the min-transformation-depth-signature. Should eventually stay T.
     */
    @LispMethod(comment = "Temporary control variable; when non-nil min-transformation-depth is computed\r\nfrom the min-transformation-depth-signature. Should eventually stay T.\ndefparameter\nTemporary control variable; when non-nil min-transformation-depth is computed\nfrom the min-transformation-depth-signature. Should eventually stay T.")
    public static final SubLSymbol $problem_min_transformation_depth_from_signature_enabledP$ = makeSymbol("*PROBLEM-MIN-TRANSFORMATION-DEPTH-FROM-SIGNATURE-ENABLED?*");

    /**
     * Temporary control variable; when non-nil min-transformation-depth is computed from the min-transformation-depth-signature.
     * Should eventually stay T.
     */
    // defparameter
    // public static final SubLSymbol $problem_min_transformation_depth_from_signature_enabledP$ = makeSymbol("*PROBLEM-MIN-TRANSFORMATION-DEPTH-FROM-SIGNATURE-ENABLED?*");
    // private static final SubLSymbol $PROOF_BINDINGS_INDEX = makeKeyword("PROOF-BINDINGS-INDEX");
    @LispMethod(comment = "Temporary control variable; when non-nil min-transformation-depth is computed from the min-transformation-depth-signature.\r\nShould eventually stay T.\nTemporary control variable; when non-nil min-transformation-depth is computed from the min-transformation-depth-signature.\nShould eventually stay T.\ndefparameter\npublic static final SubLSymbol $problem_min_transformation_depth_from_signature_enabledP$ = makeSymbol(\"*PROBLEM-MIN-TRANSFORMATION-DEPTH-FROM-SIGNATURE-ENABLED?*\");\nprivate static final SubLSymbol $PROOF_BINDINGS_INDEX = makeKeyword(\"PROOF-BINDINGS-INDEX\");")
    static private final SubLSymbol $sym103$PROBLEM_VAR = makeUninternedSymbol("PROBLEM-VAR");

    static private final SubLSymbol $sym108$LINK = makeUninternedSymbol("LINK");

    static private final SubLSymbol $sym111$LINK = makeUninternedSymbol("LINK");

    static private final SubLSymbol $sym114$LINK = makeUninternedSymbol("LINK");

    static private final SubLSymbol $sym119$PROOF_LIST = makeUninternedSymbol("PROOF-LIST");

    static private final SubLSymbol $sym120$BINDINGS = makeUninternedSymbol("BINDINGS");

    static private final SubLSymbol $sym124$STATUS_VAR = makeUninternedSymbol("STATUS-VAR");

    static private final SubLSymbol $sym126$PROOF_HAS_STATUS_ = makeSymbol("PROOF-HAS-STATUS?");

    static private final SubLSymbol $sym128$STRATEGY = makeUninternedSymbol("STRATEGY");

    static private final SubLSymbol $sym132$STORE = makeUninternedSymbol("STORE");

    static private final SubLSymbol $sym133$PROB = makeUninternedSymbol("PROB");

    static private final SubLSymbol $sym136$PROBLEM_RELEVANT_TO_INFERENCE_ = makeSymbol("PROBLEM-RELEVANT-TO-INFERENCE?");

    static private final SubLSymbol $sym138$STORE = makeUninternedSymbol("STORE");

    static private final SubLSymbol $sym139$PROB = makeUninternedSymbol("PROB");

    static private final SubLSymbol $sym141$PROBLEM_ACTIVE_IN_STRATEGY_ = makeSymbol("PROBLEM-ACTIVE-IN-STRATEGY?");

    static private final SubLSymbol $sym143$INFERENCE = makeUninternedSymbol("INFERENCE");

    static private final SubLSymbol $sym162$_ = makeSymbol("<");

    static private final SubLSymbol $sym168$TACTIC_POSSIBLE_ = makeSymbol("TACTIC-POSSIBLE?");

    static private final SubLSymbol $sym169$TACTIC_EXECUTED_ = makeSymbol("TACTIC-EXECUTED?");

    static private final SubLSymbol $sym170$TACTIC_DISCARDED_ = makeSymbol("TACTIC-DISCARDED?");

    static private final SubLSymbol $sym177$PROBLEM_HAS_AN_IN_PROGRESS_TACTIC_ = makeSymbol("PROBLEM-HAS-AN-IN-PROGRESS-TACTIC?");

    static private final SubLSymbol $sym194$_ = makeSymbol(">");

    static private final SubLSymbol $sym99$PROBLEM_LINK_HAS_TYPE_ = makeSymbol("PROBLEM-LINK-HAS-TYPE?");

    // defvar
    /**
     * :intuitive or :counterintuitive. :intuitive means that any transformation or
     * residual transformation link increments the transformation depth by 1. This
     * corresponds to the number of times that the problem has been transformed.
     * :counterintuitive means that transformation-depth indicates the maximum
     * number of times that any /literal/ in the problem has been transformed.
     */
    @LispMethod(comment = ":intuitive or :counterintuitive. :intuitive means that any transformation or\r\nresidual transformation link increments the transformation depth by 1. This\r\ncorresponds to the number of times that the problem has been transformed.\r\n:counterintuitive means that transformation-depth indicates the maximum\r\nnumber of times that any /literal/ in the problem has been transformed.\ndefvar\n:intuitive or :counterintuitive. :intuitive means that any transformation or\nresidual transformation link increments the transformation depth by 1. This\ncorresponds to the number of times that the problem has been transformed.\n:counterintuitive means that transformation-depth indicates the maximum\nnumber of times that any /literal/ in the problem has been transformed.")
    public static final SubLSymbol $transformation_depth_computation$ = makeSymbol("*TRANSFORMATION-DEPTH-COMPUTATION*");

    static private final SubLList alt_list100 = list(list(makeSymbol("LINK-VAR"), makeSymbol("MAPPED-PROBLEM-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("TYPE"), makeSymbol("OPEN?"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list101 = list($TYPE, makeKeyword("OPEN?"), $DONE);

    static private final SubLList alt_list107 = list(list(makeSymbol("SUPPORTED-PROBLEM-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list110 = list(list(makeSymbol("SUPPORTED-INFERENCE-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list113 = list(list(makeSymbol("SUPPORTING-PROBLEM-VAR"), makeSymbol("VARIABLE-MAP-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list116 = list(list(makeSymbol("PROOF-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("PROOF-STATUS"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list117 = list(makeKeyword("PROOF-STATUS"), $DONE);

    static private final SubLList alt_list127 = list(list(makeSymbol("INFERENCE-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list131 = list(list(makeSymbol("INFERENCE-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list137 = list(list(makeSymbol("STRATEGY-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list142 = list(list(makeSymbol("STRATEGY-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list146 = list(list(makeSymbol("STRATEGIC-CONTEXT-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list148 = list(makeKeyword("TACTICAL"));

    static private final SubLList alt_list153 = list(makeSymbol("MT"), makeSymbol("ASENT"));

    static private final SubLList alt_list158 = list(makeSymbol("DNF-CLAUSE"));

    static private final SubLList alt_list3 = list(
	    new SubLObject[] { makeSymbol("SUID"), makeSymbol("STORE"), makeSymbol("QUERY"), makeSymbol("STATUS"), makeSymbol("DEPENDENT-LINKS"), makeSymbol("ARGUMENT-LINKS"), makeSymbol("TACTICS"), makeSymbol("PROOF-BINDINGS-INDEX"), makeSymbol("ARGUMENT-LINK-BINDINGS-INDEX") });

    static private final SubLList alt_list4 = list(
	    new SubLObject[] { $SUID, makeKeyword("STORE"), makeKeyword("QUERY"), makeKeyword("STATUS"), makeKeyword("DEPENDENT-LINKS"), makeKeyword("ARGUMENT-LINKS"), makeKeyword("TACTICS"), makeKeyword("PROOF-BINDINGS-INDEX"), makeKeyword("ARGUMENT-LINK-BINDINGS-INDEX") });

    static private final SubLList alt_list47 = list(list(makeSymbol("ASENT-VAR"), makeSymbol("MT-VAR"), makeSymbol("SENSE-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list48 = list($DONE);

    static private final SubLList alt_list5 = list(new SubLObject[] { makeSymbol("PROB-SUID"), makeSymbol("PROB-STORE"), makeSymbol("PROB-QUERY"), makeSymbol("PROB-STATUS"), makeSymbol("PROB-DEPENDENT-LINKS"), makeSymbol("PROB-ARGUMENT-LINKS"), makeSymbol("PROB-TACTICS"),
	    makeSymbol("PROB-PROOF-BINDINGS-INDEX"), makeSymbol("PROB-ARGUMENT-LINK-BINDINGS-INDEX") });

    static private final SubLList alt_list53 = list(
	    list(new SubLObject[] { makeSymbol("TACTIC-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE"), makeSymbol("STATUS"), makeSymbol("COMPLETENESS"), makeSymbol("PREFERENCE-LEVEL"), makeSymbol("HL-MODULE"), makeSymbol("TYPE"), makeSymbol("PRODUCTIVITY") }),
	    makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list54 = list($DONE, makeKeyword("STATUS"), makeKeyword("COMPLETENESS"), makeKeyword("PREFERENCE-LEVEL"), makeKeyword("HL-MODULE"), $TYPE, makeKeyword("PRODUCTIVITY"));

    static private final SubLList alt_list6 = list(new SubLObject[] { makeSymbol("_CSETF-PROB-SUID"), makeSymbol("_CSETF-PROB-STORE"), makeSymbol("_CSETF-PROB-QUERY"), makeSymbol("_CSETF-PROB-STATUS"), makeSymbol("_CSETF-PROB-DEPENDENT-LINKS"), makeSymbol("_CSETF-PROB-ARGUMENT-LINKS"),
	    makeSymbol("_CSETF-PROB-TACTICS"), makeSymbol("_CSETF-PROB-PROOF-BINDINGS-INDEX"), makeSymbol("_CSETF-PROB-ARGUMENT-LINK-BINDINGS-INDEX") });

    static private final SubLList alt_list75 = list(new SubLObject[] { makeKeyword("NON-TRANSFORMATION"), makeKeyword("GENERALIZED-REMOVAL"), makeKeyword("GENERALIZED-REMOVAL-OR-REWRITE"), makeKeyword("CONNECTED-CONJUNCTION"), makeKeyword("CONJUNCTIVE"), makeKeyword("DISJUNCTIVE"),
	    makeKeyword("LOGICAL"), makeKeyword("LOGICAL-CONJUNCTIVE"), makeKeyword("STRUCTURAL-CONJUNCTIVE"), makeKeyword("META-STRUCTURAL"), makeKeyword("CONTENT"), makeKeyword("UNION"), makeKeyword("SPLIT"), makeKeyword("JOIN-ORDERED"), $JOIN });

    static private final SubLList alt_list94 = list(list(makeSymbol("LINK-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("TYPE"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList alt_list95 = list($TYPE, $DONE);

    static private final SubLString alt_str157$The_problem__S_did_not_have_a_sin = makeString("The problem ~S did not have a single-clause query.");

    static private final SubLString alt_str159$Found_more_than_one__a_argument_l = makeString("Found more than one ~a argument link on ~a");

    static private final SubLString alt_str160$Expected__a_to_have_a__a_argument = makeString("Expected ~a to have a ~a argument link");

    static private final SubLString alt_str164$_a_had_more_than_one_dependent_li = makeString("~a had more than one dependent link");

    static private final SubLString alt_str165$_a_had_no_dependent_links = makeString("~a had no dependent links");

    static private final SubLString alt_str188$Tried_to_add__s_to__s__which_woul = makeString("Tried to add ~s to ~s, which would result in more than ~s tactics on ~s");

    static private final SubLString alt_str36$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLString alt_str38$_Invalid_PROBLEM__s_ = makeString("<Invalid PROBLEM ~s>");

    static private final SubLString alt_str39$__a_PROBLEM__a__a__s_ = makeString("<~a PROBLEM ~a.~a:~s>");

    static private final SubLString alt_str44$Crazy_amount_of_problems___a__in_ = makeString("Crazy amount of problems (~a) in store ~a");

    // private static final SubLSymbol BINDINGS_P = makeSymbol("BINDINGS-P");
    // 
    // private static final SubLSymbol COMPLETENESS_P = makeSymbol("COMPLETENESS-P");
    // 
    // private static final SubLSymbol CONTEXTUALIZED_DNF_CLAUSES_P = makeSymbol("CONTEXTUALIZED-DNF-CLAUSES-P");
    // 
    // private static final SubLSymbol DO_DICTIONARY_CONTENTS = makeSymbol("DO-DICTIONARY-CONTENTS");
    // 
    // private static final SubLSymbol DO_INFERENCE_STRATEGIES = makeSymbol("DO-INFERENCE-STRATEGIES");
    // 
    // private static final SubLSymbol DO_PROBLEM_ACTIVE_STRATEGIES = makeSymbol("DO-PROBLEM-ACTIVE-STRATEGIES");
    // 
    // private static final SubLSymbol DO_PROBLEM_ARGUMENT_LINKS = makeSymbol("DO-PROBLEM-ARGUMENT-LINKS");
    // 
    // private static final SubLSymbol DO_PROBLEM_DEPENDENT_LINKS = makeSymbol("DO-PROBLEM-DEPENDENT-LINKS");
    // 
    // private static final SubLSymbol DO_PROBLEM_LINK_SUPPORTING_MAPPED_PROBLEMS = makeSymbol("DO-PROBLEM-LINK-SUPPORTING-MAPPED-PROBLEMS");
    // 
    // private static final SubLSymbol DO_PROBLEM_LINK_SUPPORTING_PROBLEMS = makeSymbol("DO-PROBLEM-LINK-SUPPORTING-PROBLEMS");
    // 
    // private static final SubLSymbol DO_PROBLEM_PROOFS = makeSymbol("DO-PROBLEM-PROOFS");
    // 
    // private static final SubLSymbol DO_PROBLEM_QUERY_LITERALS = makeSymbol("DO-PROBLEM-QUERY-LITERALS");
    // 
    // private static final SubLSymbol DO_PROBLEM_RELEVANT_INFERENCES = makeSymbol("DO-PROBLEM-RELEVANT-INFERENCES");
    // 
    // private static final SubLSymbol DO_PROBLEM_RELEVANT_STRATEGIES = makeSymbol("DO-PROBLEM-RELEVANT-STRATEGIES");
    // 
    // private static final SubLSymbol DO_PROBLEM_STORE_INFERENCES = makeSymbol("DO-PROBLEM-STORE-INFERENCES");
    // 
    // private static final SubLSymbol DO_PROBLEM_STORE_STRATEGIES = makeSymbol("DO-PROBLEM-STORE-STRATEGIES");
    // 
    // private static final SubLSymbol DO_PROBLEM_TACTICS = makeSymbol("DO-PROBLEM-TACTICS");
    // 
    // private static final SubLSymbol DO_PROBLEM_TACTICS_COMPLETENESS_MATCH = makeSymbol("DO-PROBLEM-TACTICS-COMPLETENESS-MATCH");
    // 
    // private static final SubLSymbol DO_PROBLEM_TACTICS_HL_MODULE_MATCH = makeSymbol("DO-PROBLEM-TACTICS-HL-MODULE-MATCH");
    // 
    // private static final SubLSymbol DO_PROBLEM_TACTICS_PREFERENCE_LEVEL_MATCH = makeSymbol("DO-PROBLEM-TACTICS-PREFERENCE-LEVEL-MATCH");
    // 
    // private static final SubLSymbol DO_PROBLEM_TACTICS_PRODUCTIVITY_MATCH = makeSymbol("DO-PROBLEM-TACTICS-PRODUCTIVITY-MATCH");
    // 
    // private static final SubLSymbol DO_PROBLEM_TACTICS_STATUS_MATCH = makeSymbol("DO-PROBLEM-TACTICS-STATUS-MATCH");
    // 
    // private static final SubLSymbol DO_PROBLEM_TACTICS_TYPE_MATCH = makeSymbol("DO-PROBLEM-TACTICS-TYPE-MATCH");
    // 
    // private static final SubLSymbol GENERALIZED_TACTIC_TYPE_P = makeSymbol("GENERALIZED-TACTIC-TYPE-P");
    // 
    // private static final SubLSymbol MAPPED_PROBLEM_PROBLEM = makeSymbol("MAPPED-PROBLEM-PROBLEM");
    @LispMethod(comment = "private static final SubLSymbol BINDINGS_P = makeSymbol(\"BINDINGS-P\");\nprivate static final SubLSymbol COMPLETENESS_P = makeSymbol(\"COMPLETENESS-P\");\nprivate static final SubLSymbol CONTEXTUALIZED_DNF_CLAUSES_P = makeSymbol(\"CONTEXTUALIZED-DNF-CLAUSES-P\");\nprivate static final SubLSymbol DO_DICTIONARY_CONTENTS = makeSymbol(\"DO-DICTIONARY-CONTENTS\");\nprivate static final SubLSymbol DO_INFERENCE_STRATEGIES = makeSymbol(\"DO-INFERENCE-STRATEGIES\");\nprivate static final SubLSymbol DO_PROBLEM_ACTIVE_STRATEGIES = makeSymbol(\"DO-PROBLEM-ACTIVE-STRATEGIES\");\nprivate static final SubLSymbol DO_PROBLEM_ARGUMENT_LINKS = makeSymbol(\"DO-PROBLEM-ARGUMENT-LINKS\");\nprivate static final SubLSymbol DO_PROBLEM_DEPENDENT_LINKS = makeSymbol(\"DO-PROBLEM-DEPENDENT-LINKS\");\nprivate static final SubLSymbol DO_PROBLEM_LINK_SUPPORTING_MAPPED_PROBLEMS = makeSymbol(\"DO-PROBLEM-LINK-SUPPORTING-MAPPED-PROBLEMS\");\nprivate static final SubLSymbol DO_PROBLEM_LINK_SUPPORTING_PROBLEMS = makeSymbol(\"DO-PROBLEM-LINK-SUPPORTING-PROBLEMS\");\nprivate static final SubLSymbol DO_PROBLEM_PROOFS = makeSymbol(\"DO-PROBLEM-PROOFS\");\nprivate static final SubLSymbol DO_PROBLEM_QUERY_LITERALS = makeSymbol(\"DO-PROBLEM-QUERY-LITERALS\");\nprivate static final SubLSymbol DO_PROBLEM_RELEVANT_INFERENCES = makeSymbol(\"DO-PROBLEM-RELEVANT-INFERENCES\");\nprivate static final SubLSymbol DO_PROBLEM_RELEVANT_STRATEGIES = makeSymbol(\"DO-PROBLEM-RELEVANT-STRATEGIES\");\nprivate static final SubLSymbol DO_PROBLEM_STORE_INFERENCES = makeSymbol(\"DO-PROBLEM-STORE-INFERENCES\");\nprivate static final SubLSymbol DO_PROBLEM_STORE_STRATEGIES = makeSymbol(\"DO-PROBLEM-STORE-STRATEGIES\");\nprivate static final SubLSymbol DO_PROBLEM_TACTICS = makeSymbol(\"DO-PROBLEM-TACTICS\");\nprivate static final SubLSymbol DO_PROBLEM_TACTICS_COMPLETENESS_MATCH = makeSymbol(\"DO-PROBLEM-TACTICS-COMPLETENESS-MATCH\");\nprivate static final SubLSymbol DO_PROBLEM_TACTICS_HL_MODULE_MATCH = makeSymbol(\"DO-PROBLEM-TACTICS-HL-MODULE-MATCH\");\nprivate static final SubLSymbol DO_PROBLEM_TACTICS_PREFERENCE_LEVEL_MATCH = makeSymbol(\"DO-PROBLEM-TACTICS-PREFERENCE-LEVEL-MATCH\");\nprivate static final SubLSymbol DO_PROBLEM_TACTICS_PRODUCTIVITY_MATCH = makeSymbol(\"DO-PROBLEM-TACTICS-PRODUCTIVITY-MATCH\");\nprivate static final SubLSymbol DO_PROBLEM_TACTICS_STATUS_MATCH = makeSymbol(\"DO-PROBLEM-TACTICS-STATUS-MATCH\");\nprivate static final SubLSymbol DO_PROBLEM_TACTICS_TYPE_MATCH = makeSymbol(\"DO-PROBLEM-TACTICS-TYPE-MATCH\");\nprivate static final SubLSymbol GENERALIZED_TACTIC_TYPE_P = makeSymbol(\"GENERALIZED-TACTIC-TYPE-P\");\nprivate static final SubLSymbol MAPPED_PROBLEM_PROBLEM = makeSymbol(\"MAPPED-PROBLEM-PROBLEM\");")
    public static final SubLFile me = new inference_datastructures_problem();

    // public static final SubLFile me = new inference_datastructures_problem();
    // 
    @LispMethod(comment = "public static final SubLFile me = new inference_datastructures_problem();")


    public static final String myName = "com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem";

    // private static final SubLSymbol PREFERENCE_LEVEL_P = makeSymbol("PREFERENCE-LEVEL-P");
    // 
    @LispMethod(comment = "public static final String myName = \"com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem\";\nprivate static final SubLSymbol PREFERENCE_LEVEL_P = makeSymbol(\"PREFERENCE-LEVEL-P\");")
    private static final SubLSymbol PRINT_PROBLEM = makeSymbol("PRINT-PROBLEM");

    // 
    // private static final SubLSymbol PRINT_PROBLEM = makeSymbol("PRINT-PROBLEM");
    // 
    // private static final SubLSymbol PROB_ARGUMENT_LINK_BINDINGS_INDEX = makeSymbol("PROB-ARGUMENT-LINK-BINDINGS-INDEX");
    // 
    // private static final SubLSymbol PROB_ARGUMENT_LINKS = makeSymbol("PROB-ARGUMENT-LINKS");
    // 
    // private static final SubLSymbol PROB_DEPENDENT_LINKS = makeSymbol("PROB-DEPENDENT-LINKS");
    // 
    // private static final SubLSymbol PROB_PROOF_BINDINGS_INDEX = makeSymbol("PROB-PROOF-BINDINGS-INDEX");
    // 
    // private static final SubLSymbol PROB_QUERY = makeSymbol("PROB-QUERY");
    // 
    // private static final SubLSymbol PROB_STATUS = makeSymbol("PROB-STATUS");
    // 
    // private static final SubLSymbol PROB_STORE = makeSymbol("PROB-STORE");
    // 
    // private static final SubLSymbol PROB_SUID = makeSymbol("PROB-SUID");
    // 
    // private static final SubLSymbol PROB_TACTICS = makeSymbol("PROB-TACTICS");
    // 
    // private static final SubLSymbol PROBLEM_ARGUMENT_LINKS = makeSymbol("PROBLEM-ARGUMENT-LINKS");
    // 
    // private static final SubLSymbol PROBLEM_DEPENDENT_LINKS = makeSymbol("PROBLEM-DEPENDENT-LINKS");
    // 
    // private static final SubLSymbol PROBLEM_LINK_SUPPORTED_INFERENCE = makeSymbol("PROBLEM-LINK-SUPPORTED-INFERENCE");
    // 
    // private static final SubLSymbol PROBLEM_LINK_SUPPORTED_PROBLEM = makeSymbol("PROBLEM-LINK-SUPPORTED-PROBLEM");
    // 
    // private static final SubLSymbol PROBLEM_LINK_TYPE_P = makeSymbol("PROBLEM-LINK-TYPE-P");
    // 
    @LispMethod(comment = "private static final SubLSymbol PRINT_PROBLEM = makeSymbol(\"PRINT-PROBLEM\");\nprivate static final SubLSymbol PROB_ARGUMENT_LINK_BINDINGS_INDEX = makeSymbol(\"PROB-ARGUMENT-LINK-BINDINGS-INDEX\");\nprivate static final SubLSymbol PROB_ARGUMENT_LINKS = makeSymbol(\"PROB-ARGUMENT-LINKS\");\nprivate static final SubLSymbol PROB_DEPENDENT_LINKS = makeSymbol(\"PROB-DEPENDENT-LINKS\");\nprivate static final SubLSymbol PROB_PROOF_BINDINGS_INDEX = makeSymbol(\"PROB-PROOF-BINDINGS-INDEX\");\nprivate static final SubLSymbol PROB_QUERY = makeSymbol(\"PROB-QUERY\");\nprivate static final SubLSymbol PROB_STATUS = makeSymbol(\"PROB-STATUS\");\nprivate static final SubLSymbol PROB_STORE = makeSymbol(\"PROB-STORE\");\nprivate static final SubLSymbol PROB_SUID = makeSymbol(\"PROB-SUID\");\nprivate static final SubLSymbol PROB_TACTICS = makeSymbol(\"PROB-TACTICS\");\nprivate static final SubLSymbol PROBLEM_ARGUMENT_LINKS = makeSymbol(\"PROBLEM-ARGUMENT-LINKS\");\nprivate static final SubLSymbol PROBLEM_DEPENDENT_LINKS = makeSymbol(\"PROBLEM-DEPENDENT-LINKS\");\nprivate static final SubLSymbol PROBLEM_LINK_SUPPORTED_INFERENCE = makeSymbol(\"PROBLEM-LINK-SUPPORTED-INFERENCE\");\nprivate static final SubLSymbol PROBLEM_LINK_SUPPORTED_PROBLEM = makeSymbol(\"PROBLEM-LINK-SUPPORTED-PROBLEM\");\nprivate static final SubLSymbol PROBLEM_LINK_TYPE_P = makeSymbol(\"PROBLEM-LINK-TYPE-P\");")
    private static final SubLSymbol PROBLEM_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("PROBLEM-PRINT-FUNCTION-TRAMPOLINE");

    // 
    // private static final SubLSymbol PROBLEM_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("PROBLEM-PRINT-FUNCTION-TRAMPOLINE");
    // 
    // private static final SubLSymbol PROBLEM_PROOF_BINDINGS_INDEX = makeSymbol("PROBLEM-PROOF-BINDINGS-INDEX");
    // 
    // private static final SubLSymbol PROBLEM_QUERY = makeSymbol("PROBLEM-QUERY");
    // 
    // private static final SubLSymbol PROBLEM_QUERY_DEPTH_SIGNATURE_P = makeSymbol("PROBLEM-QUERY-DEPTH-SIGNATURE-P");
    // 
    // private static final SubLSymbol PROBLEM_STATUS_P = makeSymbol("PROBLEM-STATUS-P");
    // 
    // private static final SubLSymbol PROBLEM_STORE = makeSymbol("PROBLEM-STORE");
    // 
    // private static final SubLSymbol PROBLEM_TACTICS = makeSymbol("PROBLEM-TACTICS");
    // 
    // private static final SubLSymbol PRODUCTIVITY_P = makeSymbol("PRODUCTIVITY-P");
    // 
    // private static final SubLSymbol STRATEGIC_CONTEXT_P = makeSymbol("STRATEGIC-CONTEXT-P");
    // 
    // private static final SubLSymbol STRATEGY_INFERENCE = makeSymbol("STRATEGY-INFERENCE");
    // 
    // private static final SubLSymbol SXHASH_PROBLEM_METHOD = makeSymbol("SXHASH-PROBLEM-METHOD");
    // 
    // private static final SubLSymbol TACTIC_STATUS_P = makeSymbol("TACTIC-STATUS-P");
    // 
    // private static final SubLSymbol _CSETF_PROB_ARGUMENT_LINK_BINDINGS_INDEX = makeSymbol("_CSETF-PROB-ARGUMENT-LINK-BINDINGS-INDEX");
    // 
    // private static final SubLSymbol _CSETF_PROB_ARGUMENT_LINKS = makeSymbol("_CSETF-PROB-ARGUMENT-LINKS");
    @LispMethod(comment = "private static final SubLSymbol PROBLEM_PRINT_FUNCTION_TRAMPOLINE = makeSymbol(\"PROBLEM-PRINT-FUNCTION-TRAMPOLINE\");\nprivate static final SubLSymbol PROBLEM_PROOF_BINDINGS_INDEX = makeSymbol(\"PROBLEM-PROOF-BINDINGS-INDEX\");\nprivate static final SubLSymbol PROBLEM_QUERY = makeSymbol(\"PROBLEM-QUERY\");\nprivate static final SubLSymbol PROBLEM_QUERY_DEPTH_SIGNATURE_P = makeSymbol(\"PROBLEM-QUERY-DEPTH-SIGNATURE-P\");\nprivate static final SubLSymbol PROBLEM_STATUS_P = makeSymbol(\"PROBLEM-STATUS-P\");\nprivate static final SubLSymbol PROBLEM_STORE = makeSymbol(\"PROBLEM-STORE\");\nprivate static final SubLSymbol PROBLEM_TACTICS = makeSymbol(\"PROBLEM-TACTICS\");\nprivate static final SubLSymbol PRODUCTIVITY_P = makeSymbol(\"PRODUCTIVITY-P\");\nprivate static final SubLSymbol STRATEGIC_CONTEXT_P = makeSymbol(\"STRATEGIC-CONTEXT-P\");\nprivate static final SubLSymbol STRATEGY_INFERENCE = makeSymbol(\"STRATEGY-INFERENCE\");\nprivate static final SubLSymbol SXHASH_PROBLEM_METHOD = makeSymbol(\"SXHASH-PROBLEM-METHOD\");\nprivate static final SubLSymbol TACTIC_STATUS_P = makeSymbol(\"TACTIC-STATUS-P\");\nprivate static final SubLSymbol _CSETF_PROB_ARGUMENT_LINK_BINDINGS_INDEX = makeSymbol(\"_CSETF-PROB-ARGUMENT-LINK-BINDINGS-INDEX\");\nprivate static final SubLSymbol _CSETF_PROB_ARGUMENT_LINKS = makeSymbol(\"_CSETF-PROB-ARGUMENT-LINKS\");")
    private static final SubLSymbol _CSETF_PROB_BACKCHAIN_REQUIRED = makeSymbol("_CSETF-PROB-BACKCHAIN-REQUIRED");

    // private static final SubLSymbol _CSETF_PROB_DEPENDENT_LINKS = makeSymbol("_CSETF-PROB-DEPENDENT-LINKS");
    @LispMethod(comment = "private static final SubLSymbol _CSETF_PROB_DEPENDENT_LINKS = makeSymbol(\"_CSETF-PROB-DEPENDENT-LINKS\");")
    private static final SubLSymbol _CSETF_PROB_FREE_HL_VARS = makeSymbol("_CSETF-PROB-FREE-HL-VARS");

    private static final SubLSymbol _CSETF_PROB_MEMOIZATION_STATE = makeSymbol("_CSETF-PROB-MEMOIZATION-STATE");

    // private static final SubLSymbol _CSETF_PROB_PROOF_BINDINGS_INDEX = makeSymbol("_CSETF-PROB-PROOF-BINDINGS-INDEX");
    // 
    // private static final SubLSymbol _CSETF_PROB_QUERY = makeSymbol("_CSETF-PROB-QUERY");
    // 
    // private static final SubLSymbol _CSETF_PROB_STATUS = makeSymbol("_CSETF-PROB-STATUS");
    // 
    // private static final SubLSymbol _CSETF_PROB_STORE = makeSymbol("_CSETF-PROB-STORE");
    // 
    // private static final SubLSymbol _CSETF_PROB_SUID = makeSymbol("_CSETF-PROB-SUID");
    // 
    // private static final SubLSymbol _CSETF_PROB_TACTICS = makeSymbol("_CSETF-PROB-TACTICS");
    // 
    // private static final SubLString $$$Ignore_the_crazy_problems = makeString("Ignore the crazy problems");
    // 
    // private static final SubLObject $$InferencePSC = reader_make_constant_shell(("InferencePSC"));
    // 
    // private static final SubLObject $$ist = reader_make_constant_shell(("ist"));
    // 
    // private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");
    // 
    // private static final SubLSymbol $ARGUMENT_LINK_BINDINGS_INDEX = makeKeyword("ARGUMENT-LINK-BINDINGS-INDEX");
    @LispMethod(comment = "private static final SubLSymbol _CSETF_PROB_PROOF_BINDINGS_INDEX = makeSymbol(\"_CSETF-PROB-PROOF-BINDINGS-INDEX\");\nprivate static final SubLSymbol _CSETF_PROB_QUERY = makeSymbol(\"_CSETF-PROB-QUERY\");\nprivate static final SubLSymbol _CSETF_PROB_STATUS = makeSymbol(\"_CSETF-PROB-STATUS\");\nprivate static final SubLSymbol _CSETF_PROB_STORE = makeSymbol(\"_CSETF-PROB-STORE\");\nprivate static final SubLSymbol _CSETF_PROB_SUID = makeSymbol(\"_CSETF-PROB-SUID\");\nprivate static final SubLSymbol _CSETF_PROB_TACTICS = makeSymbol(\"_CSETF-PROB-TACTICS\");\nprivate static final SubLString $$$Ignore_the_crazy_problems = makeString(\"Ignore the crazy problems\");\nprivate static final SubLObject $$InferencePSC = reader_make_constant_shell((\"InferencePSC\"));\nprivate static final SubLObject $$ist = reader_make_constant_shell((\"ist\"));\nprivate static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword(\"ALLOW-OTHER-KEYS\");\nprivate static final SubLSymbol $ARGUMENT_LINK_BINDINGS_INDEX = makeKeyword(\"ARGUMENT-LINK-BINDINGS-INDEX\");")
    private static final SubLString $$$problem_memoization_state = makeString("problem memoization state");

    private static final SubLSymbol $FREE_HL_VARS = makeKeyword("FREE-HL-VARS");

    // private static final SubLSymbol $GENERALIZED_REMOVAL_OR_REWRITE = makeKeyword("GENERALIZED-REMOVAL-OR-REWRITE");
    // deflexical
    /**
     * Generalized tactic types which specify more than one actual tactic-type-p.
     */
    @LispMethod(comment = "Generalized tactic types which specify more than one actual tactic-type-p.\nprivate static final SubLSymbol $GENERALIZED_REMOVAL_OR_REWRITE = makeKeyword(\"GENERALIZED-REMOVAL-OR-REWRITE\");\ndeflexical")
    private static final SubLSymbol $generalized_tactic_types$ = makeSymbol("*GENERALIZED-TACTIC-TYPES*");

    private static final SubLList $list118 = list(list(makeSymbol("LINK-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("TYPE"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list119 = list($TYPE, $DONE);

    private static final SubLList $list124 = list(list(makeSymbol("LINK-VAR"), makeSymbol("MAPPED-PROBLEM-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("TYPE"), makeSymbol("OPEN?"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list125 = list($TYPE, makeKeyword("OPEN?"), $DONE);

    private static final SubLList $list134 = list(list(makeSymbol("SUPPORTED-INFERENCE-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list140 = list(list(makeSymbol("PROOF-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("PROOF-STATUS"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list141 = list(makeKeyword("PROOF-STATUS"), $DONE);

    private static final SubLList $list151 = list(list(makeSymbol("INFERENCE-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list155 = list(list(makeSymbol("INFERENCE-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list161 = list(list(makeSymbol("STRATEGY-VAR"), makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list166 = list(list(makeSymbol("STRATEGY-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list170 = list(list(makeSymbol("STRATEGIC-CONTEXT-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list172 = list(makeKeyword("TACTICAL"));

    private static final SubLList $list178 = list(makeSymbol("MT"), makeSymbol("ASENT"));

    private static final SubLList $list183 = list(makeSymbol("DNF-CLAUSE"));

    private static final SubLList $list67 = list(list(makeSymbol("PROBLEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list71 = list(list(makeSymbol("ASENT-VAR"), makeSymbol("MT-VAR"), makeSymbol("SENSE-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list72 = list($DONE);

    private static final SubLList $list77 = list(
	    list(new SubLObject[] { makeSymbol("TACTIC-VAR"), makeSymbol("PROBLEM"), makeSymbol("&KEY"), makeSymbol("DONE"), makeSymbol("STATUS"), makeSymbol("COMPLETENESS"), makeSymbol("PREFERENCE-LEVEL"), makeSymbol("HL-MODULE"), makeSymbol("TYPE"), makeSymbol("PRODUCTIVITY") }),
	    makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list78 = list($DONE, makeKeyword("STATUS"), makeKeyword("COMPLETENESS"), makeKeyword("PREFERENCE-LEVEL"), makeKeyword("HL-MODULE"), $TYPE, makeKeyword("PRODUCTIVITY"));

    private static final SubLList $list9 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("PROBLEM-P"));

    private static final SubLList $list99 = list(new SubLObject[] { makeKeyword("NON-TRANSFORMATION"), makeKeyword("GENERALIZED-REMOVAL"), makeKeyword("GENERALIZED-REMOVAL-OR-REWRITE"), makeKeyword("CONNECTED-CONJUNCTION"), makeKeyword("CONJUNCTIVE"), makeKeyword("DISJUNCTIVE"),
	    makeKeyword("LOGICAL"), makeKeyword("LOGICAL-CONJUNCTIVE"), makeKeyword("STRUCTURAL-CONJUNCTIVE"), makeKeyword("META-STRUCTURAL"), makeKeyword("CONTENT"), makeKeyword("UNION"), makeKeyword("SPLIT"), makeKeyword("JOIN-ORDERED"), $JOIN });

    private static final SubLSymbol $PROOF_BINDINGS_INDEX = makeKeyword("PROOF-BINDINGS-INDEX");

    private static final SubLString $str176$poking_good_status__s_into_proble = makeString("poking good status ~s into problem ~s");

    private static final SubLString $str182$The_problem__S_did_not_have_a_sin = makeString("The problem ~S did not have a single-clause query.");

    private static final SubLString $str184$Found_more_than_one__a_argument_l = makeString("Found more than one ~a argument link on ~a");

    private static final SubLString $str185$Expected__a_to_have_a__a_argument = makeString("Expected ~a to have a ~a argument link");

    private static final SubLString $str189$_a_had_more_than_one_dependent_li = makeString("~a had more than one dependent link");

    private static final SubLString $str190$_a_had_no_dependent_links = makeString("~a had no dependent links");

    private static final SubLString $str214$Tried_to_add__s_to__s__which_woul = makeString("Tried to add ~s to ~s, which would result in more than ~s tactics on ~s");

    private static final SubLString $str46$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLString $str53$_Invalid_PROBLEM__s_ = makeString("<Invalid PROBLEM ~s>");

    private static final SubLString $str54$__a_PROBLEM__a__a__s_a_ = makeString("<~a PROBLEM ~a.~a:~s~a>");

    private static final SubLString $str55$_free_ = makeString(" free=");

    private static final SubLString $str57$ = makeString("");

    private static final SubLString $str62$Crazy_amount_of_problems___a__in_ = makeString("Crazy amount of problems (~a) in store ~a");

    private static final SubLSymbol $sym123$PROBLEM_LINK_HAS_TYPE_ = makeSymbol("PROBLEM-LINK-HAS-TYPE?");

    private static final SubLSymbol $sym127$PROBLEM_VAR = makeUninternedSymbol("PROBLEM-VAR");

    private static final SubLSymbol $sym132$LINK = makeUninternedSymbol("LINK");

    private static final SubLSymbol $sym135$LINK = makeUninternedSymbol("LINK");

    private static final SubLSymbol $sym138$LINK = makeUninternedSymbol("LINK");

    private static final SubLSymbol $sym143$PROOF_LIST = makeUninternedSymbol("PROOF-LIST");

    private static final SubLSymbol $sym144$BINDINGS = makeUninternedSymbol("BINDINGS");

    private static final SubLSymbol $sym148$STATUS_VAR = makeUninternedSymbol("STATUS-VAR");

    private static final SubLSymbol $sym150$PROOF_HAS_STATUS_ = makeSymbol("PROOF-HAS-STATUS?");

    private static final SubLSymbol $sym152$STRATEGY = makeUninternedSymbol("STRATEGY");

    private static final SubLSymbol $sym156$STORE = makeUninternedSymbol("STORE");

    private static final SubLSymbol $sym157$PROB = makeUninternedSymbol("PROB");

    private static final SubLSymbol $sym160$PROBLEM_RELEVANT_TO_INFERENCE_ = makeSymbol("PROBLEM-RELEVANT-TO-INFERENCE?");

    private static final SubLSymbol $sym162$STORE = makeUninternedSymbol("STORE");

    private static final SubLSymbol $sym163$PROB = makeUninternedSymbol("PROB");

    private static final SubLSymbol $sym165$PROBLEM_ACTIVE_IN_STRATEGY_ = makeSymbol("PROBLEM-ACTIVE-IN-STRATEGY?");

    private static final SubLSymbol $sym167$INFERENCE = makeUninternedSymbol("INFERENCE");

    private static final SubLSymbol $sym187$_ = makeSymbol("<");

    private static final SubLSymbol $sym193$TACTIC_POSSIBLE_ = makeSymbol("TACTIC-POSSIBLE?");

    private static final SubLSymbol $sym194$TACTIC_EXECUTED_ = makeSymbol("TACTIC-EXECUTED?");

    private static final SubLSymbol $sym195$TACTIC_DISCARDED_ = makeSymbol("TACTIC-DISCARDED?");

    private static final SubLSymbol $sym202$PROBLEM_HAS_AN_IN_PROGRESS_TACTIC_ = makeSymbol("PROBLEM-HAS-AN-IN-PROGRESS-TACTIC?");

    private static final SubLSymbol $sym210$_EXIT = makeSymbol("%EXIT");

    private static final SubLSymbol $sym220$_ = makeSymbol(">");

    private static final SubLSymbol BINDINGS_P = makeSymbol("BINDINGS-P");

    private static final SubLSymbol COMPLETENESS_P = makeSymbol("COMPLETENESS-P");

    private static final SubLSymbol CONTEXTUALIZED_DNF_CLAUSES_P = makeSymbol("CONTEXTUALIZED-DNF-CLAUSES-P");

    private static final SubLSymbol DO_DICTIONARY_CONTENTS = makeSymbol("DO-DICTIONARY-CONTENTS");

    private static final SubLSymbol DO_INFERENCE_STRATEGIES = makeSymbol("DO-INFERENCE-STRATEGIES");

    private static final SubLSymbol DO_PROBLEM_ACTIVE_STRATEGIES = makeSymbol("DO-PROBLEM-ACTIVE-STRATEGIES");

    private static final SubLSymbol DO_PROBLEM_ARGUMENT_LINKS = makeSymbol("DO-PROBLEM-ARGUMENT-LINKS");

    private static final SubLSymbol DO_PROBLEM_DEPENDENT_LINKS = makeSymbol("DO-PROBLEM-DEPENDENT-LINKS");

    private static final SubLSymbol DO_PROBLEM_LINK_SUPPORTING_MAPPED_PROBLEMS = makeSymbol("DO-PROBLEM-LINK-SUPPORTING-MAPPED-PROBLEMS");

    private static final SubLSymbol DO_PROBLEM_LINK_SUPPORTING_PROBLEMS = makeSymbol("DO-PROBLEM-LINK-SUPPORTING-PROBLEMS");

    private static final SubLSymbol DO_PROBLEM_PROOFS = makeSymbol("DO-PROBLEM-PROOFS");

    private static final SubLSymbol DO_PROBLEM_QUERY_LITERALS = makeSymbol("DO-PROBLEM-QUERY-LITERALS");

    private static final SubLSymbol DO_PROBLEM_RELEVANT_INFERENCES = makeSymbol("DO-PROBLEM-RELEVANT-INFERENCES");

    private static final SubLSymbol DO_PROBLEM_RELEVANT_STRATEGIES = makeSymbol("DO-PROBLEM-RELEVANT-STRATEGIES");

    private static final SubLSymbol DO_PROBLEM_STORE_INFERENCES = makeSymbol("DO-PROBLEM-STORE-INFERENCES");

    private static final SubLSymbol DO_PROBLEM_STORE_STRATEGIES = makeSymbol("DO-PROBLEM-STORE-STRATEGIES");

    private static final SubLSymbol DO_PROBLEM_TACTICS = makeSymbol("DO-PROBLEM-TACTICS");

    private static final SubLSymbol DO_PROBLEM_TACTICS_COMPLETENESS_MATCH = makeSymbol("DO-PROBLEM-TACTICS-COMPLETENESS-MATCH");

    private static final SubLSymbol DO_PROBLEM_TACTICS_HL_MODULE_MATCH = makeSymbol("DO-PROBLEM-TACTICS-HL-MODULE-MATCH");

    private static final SubLSymbol DO_PROBLEM_TACTICS_PREFERENCE_LEVEL_MATCH = makeSymbol("DO-PROBLEM-TACTICS-PREFERENCE-LEVEL-MATCH");

    private static final SubLSymbol DO_PROBLEM_TACTICS_PRODUCTIVITY_MATCH = makeSymbol("DO-PROBLEM-TACTICS-PRODUCTIVITY-MATCH");

    private static final SubLSymbol DO_PROBLEM_TACTICS_STATUS_MATCH = makeSymbol("DO-PROBLEM-TACTICS-STATUS-MATCH");

    private static final SubLSymbol DO_PROBLEM_TACTICS_TYPE_MATCH = makeSymbol("DO-PROBLEM-TACTICS-TYPE-MATCH");

    private static final SubLSymbol GENERALIZED_TACTIC_TYPE_P = makeSymbol("GENERALIZED-TACTIC-TYPE-P");

    private static final SubLSymbol MAKE_PROBLEM = makeSymbol("MAKE-PROBLEM");

    private static final SubLSymbol MAPPED_PROBLEM_PROBLEM = makeSymbol("MAPPED-PROBLEM-PROBLEM");

    private static final SubLSymbol PREFERENCE_LEVEL_P = makeSymbol("PREFERENCE-LEVEL-P");

    private static final SubLSymbol PROB_ARGUMENT_LINK_BINDINGS_INDEX = makeSymbol("PROB-ARGUMENT-LINK-BINDINGS-INDEX");

    private static final SubLSymbol PROB_ARGUMENT_LINKS = makeSymbol("PROB-ARGUMENT-LINKS");

    private static final SubLSymbol PROB_BACKCHAIN_REQUIRED = makeSymbol("PROB-BACKCHAIN-REQUIRED");

    private static final SubLSymbol PROB_DEPENDENT_LINKS = makeSymbol("PROB-DEPENDENT-LINKS");

    private static final SubLSymbol PROB_FREE_HL_VARS = makeSymbol("PROB-FREE-HL-VARS");

    private static final SubLSymbol PROB_MEMOIZATION_STATE = makeSymbol("PROB-MEMOIZATION-STATE");

    private static final SubLSymbol PROB_PROOF_BINDINGS_INDEX = makeSymbol("PROB-PROOF-BINDINGS-INDEX");

    private static final SubLSymbol PROB_QUERY = makeSymbol("PROB-QUERY");

    private static final SubLSymbol PROB_STATUS = makeSymbol("PROB-STATUS");

    private static final SubLSymbol PROB_STORE = makeSymbol("PROB-STORE");

    private static final SubLSymbol PROB_SUID = makeSymbol("PROB-SUID");

    private static final SubLSymbol PROB_TACTICS = makeSymbol("PROB-TACTICS");

    private static final SubLSymbol PROBLEM_ARGUMENT_LINKS = makeSymbol("PROBLEM-ARGUMENT-LINKS");

    private static final SubLSymbol PROBLEM_DEPENDENT_LINKS = makeSymbol("PROBLEM-DEPENDENT-LINKS");

    private static final SubLSymbol PROBLEM_LINK_SUPPORTED_INFERENCE = makeSymbol("PROBLEM-LINK-SUPPORTED-INFERENCE");

    private static final SubLSymbol PROBLEM_LINK_SUPPORTED_PROBLEM = makeSymbol("PROBLEM-LINK-SUPPORTED-PROBLEM");

    private static final SubLSymbol PROBLEM_LINK_TYPE_P = makeSymbol("PROBLEM-LINK-TYPE-P");

    private static final SubLSymbol PROBLEM_MEMOIZATION_STATE = makeSymbol("PROBLEM-MEMOIZATION-STATE");

    private static final SubLSymbol PROBLEM_PROOF_BINDINGS_INDEX = makeSymbol("PROBLEM-PROOF-BINDINGS-INDEX");

    private static final SubLSymbol PROBLEM_QUERY = makeSymbol("PROBLEM-QUERY");

    private static final SubLSymbol PROBLEM_QUERY_DEPTH_SIGNATURE_P = makeSymbol("PROBLEM-QUERY-DEPTH-SIGNATURE-P");

    private static final SubLSymbol PROBLEM_STATUS_P = makeSymbol("PROBLEM-STATUS-P");

    private static final SubLSymbol PROBLEM_STORE = makeSymbol("PROBLEM-STORE");

    private static final SubLSymbol PROBLEM_TACTICS = makeSymbol("PROBLEM-TACTICS");

    private static final SubLSymbol PRODUCTIVITY_P = makeSymbol("PRODUCTIVITY-P");

    private static final SubLSymbol STRATEGIC_CONTEXT_P = makeSymbol("STRATEGIC-CONTEXT-P");

    private static final SubLSymbol STRATEGY_INFERENCE = makeSymbol("STRATEGY-INFERENCE");

    private static final SubLSymbol SXHASH_PROBLEM_METHOD = makeSymbol("SXHASH-PROBLEM-METHOD");

    private static final SubLSymbol TACTIC_STATUS_P = makeSymbol("TACTIC-STATUS-P");

    private static final SubLSymbol VARIABLE_ID = makeSymbol("VARIABLE-ID");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_PROBLEM_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-PROBLEM-METHOD");

    private static final SubLSymbol WITH_PROBLEM_MEMOIZATION_STATE = makeSymbol("WITH-PROBLEM-MEMOIZATION-STATE");

    public static final SubLObject _csetf_prob_argument_link_bindings_index(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField11(value);
    }

    public static final SubLObject _csetf_prob_argument_link_bindings_index_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField10(value);
    }

    public static final SubLObject _csetf_prob_argument_links(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField8(value);
    }

    public static final SubLObject _csetf_prob_argument_links_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField7(value);
    }

    public static final SubLObject _csetf_prob_backchain_required(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField12(value);
    }

    public static final SubLObject _csetf_prob_dependent_links(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField7(value);
    }

    public static final SubLObject _csetf_prob_dependent_links_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField6(value);
    }

    public static final SubLObject _csetf_prob_free_hl_vars(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField5(value);
    }

    public static final SubLObject _csetf_prob_memoization_state(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField13(value);
    }

    public static final SubLObject _csetf_prob_proof_bindings_index(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField10(value);
    }

    public static final SubLObject _csetf_prob_proof_bindings_index_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField9(value);
    }

    public static final SubLObject _csetf_prob_query(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField4(value);
    }

    public static final SubLObject _csetf_prob_query_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField4(value);
    }

    public static final SubLObject _csetf_prob_status(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField6(value);
    }

    public static final SubLObject _csetf_prob_status_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField5(value);
    }

    public static final SubLObject _csetf_prob_store(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField3(value);
    }

    public static final SubLObject _csetf_prob_store_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField3(value);
    }

    public static final SubLObject _csetf_prob_suid(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField2(value);
    }

    public static final SubLObject _csetf_prob_suid_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField2(value);
    }

    public static final SubLObject _csetf_prob_tactics(final SubLObject v_object, final SubLObject value) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.setField9(value);
    }

    public static final SubLObject _csetf_prob_tactics_alt(SubLObject v_object, SubLObject value) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.setField8(value);
    }

    public static final SubLObject add_problem_argument_link(final SubLObject problem, final SubLObject argument_link) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_problem_link.problem_link_p(argument_link) : "! inference_datastructures_problem_link.problem_link_p(argument_link) "
		+ ("inference_datastructures_problem_link.problem_link_p(argument_link) " + "CommonSymbols.NIL != inference_datastructures_problem_link.problem_link_p(argument_link) ") + argument_link;
	inference_datastructures_problem._csetf_prob_argument_links(problem, set_contents.set_contents_add(argument_link, inference_datastructures_problem.prob_argument_links(problem), symbol_function(EQ)));
	return problem;
    }

    /**
     * Puts ARGUMENT-LINK _below_ PROBLEM.
     */
    @LispMethod(comment = "Puts ARGUMENT-LINK _below_ PROBLEM.")
    public static final SubLObject add_problem_argument_link_alt(SubLObject problem, SubLObject argument_link) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(argument_link, PROBLEM_LINK_P);
	inference_datastructures_problem._csetf_prob_argument_links(problem, set_contents.set_contents_add(argument_link, inference_datastructures_problem.prob_argument_links(problem), symbol_function(EQ)));
	return problem;
    }

    public static final SubLObject add_problem_dependent_link(final SubLObject problem, final SubLObject dependent_link) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_problem_link.problem_link_p(dependent_link) : "! inference_datastructures_problem_link.problem_link_p(dependent_link) "
		+ ("inference_datastructures_problem_link.problem_link_p(dependent_link) " + "CommonSymbols.NIL != inference_datastructures_problem_link.problem_link_p(dependent_link) ") + dependent_link;
	inference_datastructures_problem._csetf_prob_dependent_links(problem, set_contents.set_contents_add(dependent_link, inference_datastructures_problem.prob_dependent_links(problem), symbol_function(EQ)));
	inference_metrics.increment_dependent_link_historical_count();
	if (NIL != inference_datastructures_problem.single_literal_problem_p(problem)) {
	    inference_metrics.increment_single_literal_problem_dependent_link_historical_count();
	}
	return problem;
    }

    /**
     * Puts DEPENDENT-LINK _above_ PROBLEM.
     */
    @LispMethod(comment = "Puts DEPENDENT-LINK _above_ PROBLEM.")
    public static final SubLObject add_problem_dependent_link_alt(SubLObject problem, SubLObject dependent_link) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(dependent_link, PROBLEM_LINK_P);
	inference_datastructures_problem._csetf_prob_dependent_links(problem, set_contents.set_contents_add(dependent_link, inference_datastructures_problem.prob_dependent_links(problem), symbol_function(EQ)));
	inference_metrics.increment_dependent_link_historical_count();
	if (NIL != inference_datastructures_problem.single_literal_problem_p(problem)) {
	    inference_metrics.increment_single_literal_problem_dependent_link_historical_count();
	}
	return problem;
    }

    public static final SubLObject add_problem_proof(final SubLObject problem, final SubLObject proof) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_proof.proof_p(proof) : "! inference_datastructures_proof.proof_p(proof) " + ("inference_datastructures_proof.proof_p(proof) " + "CommonSymbols.NIL != inference_datastructures_proof.proof_p(proof) ") + proof;
	final SubLObject index = inference_datastructures_problem.prob_proof_bindings_index(problem);
	final SubLObject v_bindings = inference_datastructures_proof.proof_bindings(proof);
	SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	existing = cons(proof, existing);
	inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, existing, symbol_function(EQUAL)));
	return problem;
    }

    public static final SubLObject add_problem_proof_alt(SubLObject problem, SubLObject proof) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(proof, PROOF_P);
	{
	    SubLObject index = inference_datastructures_problem.prob_proof_bindings_index(problem);
	    SubLObject v_bindings = inference_datastructures_proof.proof_bindings(proof);
	    SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	    existing = cons(proof, existing);
	    inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, existing, symbol_function(EQUAL)));
	}
	return problem;
    }

    public static final SubLObject add_problem_tactic(final SubLObject problem, final SubLObject tactic) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_tactic.tactic_p(tactic) : "! inference_datastructures_tactic.tactic_p(tactic) " + ("inference_datastructures_tactic.tactic_p(tactic) " + "CommonSymbols.NIL != inference_datastructures_tactic.tactic_p(tactic) ") + tactic;
	if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (!inference_datastructures_problem.problem_tactic_count(problem).numLE(inference_datastructures_problem.$max_problem_tactics$.getGlobalValue()))) {
	    Errors.error(inference_datastructures_problem.$str214$Tried_to_add__s_to__s__which_woul, new SubLObject[] { tactic, problem, inference_datastructures_problem.$max_problem_tactics$.getGlobalValue(), problem });
	}
	inference_datastructures_problem._csetf_prob_tactics(problem, cons(tactic, inference_datastructures_problem.prob_tactics(problem)));
	return problem;
    }

    public static final SubLObject add_problem_tactic_alt(SubLObject problem, SubLObject tactic) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    SubLTrampolineFile.checkType(problem, PROBLEM_P);
	    SubLTrampolineFile.checkType(tactic, TACTIC_P);
	    if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
		if (!inference_datastructures_problem.problem_tactic_count(problem).numLE(inference_datastructures_problem.$max_problem_tactics$.getGlobalValue())) {
		    Errors.error(inference_datastructures_problem.alt_str188$Tried_to_add__s_to__s__which_woul, new SubLObject[] { tactic, problem, inference_datastructures_problem.$max_problem_tactics$.getGlobalValue(), problem });
		}
	    }
	    inference_datastructures_problem._csetf_prob_tactics(problem, cons(tactic, inference_datastructures_problem.prob_tactics(problem)));
	    return problem;
	}
    }

    public static final SubLObject all_problem_argument_problems(final SubLObject problem) {
	SubLObject problem_set = set_contents.new_set_contents(inference_datastructures_problem.problem_argument_link_count(problem), symbol_function(EQ));
	problem_set = inference_datastructures_problem.all_problem_argument_problems_recursive(problem, problem_set);
	return Sort.sort(set_contents.set_contents_element_list(problem_set), inference_datastructures_problem.$sym187$_, PROBLEM_SUID);
    }

    public static final SubLObject all_problem_argument_problems_alt(SubLObject problem) {
	{
	    SubLObject problem_set = set_contents.new_set_contents(inference_datastructures_problem.problem_argument_link_count(problem), symbol_function(EQ));
	    problem_set = inference_datastructures_problem.all_problem_argument_problems_recursive(problem, problem_set);
	    return Sort.sort(set_contents.set_contents_element_list(problem_set), inference_datastructures_problem.$sym162$_, PROBLEM_SUID);
	}
    }

    public static final SubLObject all_problem_argument_problems_recursive(final SubLObject problem, SubLObject problem_set) {
	if (NIL == set_contents.set_contents_memberP(problem, problem_set, symbol_function(EQ))) {
	    problem_set = set_contents.set_contents_add(problem, problem_set, symbol_function(EQ));
	    final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	    SubLObject basis_object;
	    SubLObject state;
	    SubLObject link;
	    SubLObject link_var;
	    SubLObject cdolist_list_var;
	    SubLObject supporting_mapped_problem;
	    SubLObject argument_problem;
	    SubLObject variable_map;
	    for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		    state); state = set_contents.do_set_contents_update_state(state)) {
		link = set_contents.do_set_contents_next(basis_object, state);
		if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
		    link_var = link;
		    cdolist_list_var = inference_datastructures_problem_link.problem_link_supporting_mapped_problems(link_var);
		    supporting_mapped_problem = NIL;
		    supporting_mapped_problem = cdolist_list_var.first();
		    while (NIL != cdolist_list_var) {
			if (NIL != inference_macros.do_problem_link_open_matchP(NIL, link_var, supporting_mapped_problem)) {
			    argument_problem = inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem);
			    variable_map = inference_datastructures_problem_link.mapped_problem_variable_map(supporting_mapped_problem);
			    problem_set = inference_datastructures_problem.all_problem_argument_problems_recursive(argument_problem, problem_set);
			}
			cdolist_list_var = cdolist_list_var.rest();
			supporting_mapped_problem = cdolist_list_var.first();
		    }
		}
	    }
	}
	return problem_set;
    }

    public static final SubLObject all_problem_argument_problems_recursive_alt(SubLObject problem, SubLObject problem_set) {
	if (NIL == set_contents.set_contents_memberP(problem, problem_set, symbol_function(EQ))) {
	    problem_set = set_contents.set_contents_add(problem, problem_set, symbol_function(EQ));
	    {
		SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
		SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
		SubLObject state = NIL;
		for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		    {
			SubLObject link = set_contents.do_set_contents_next(basis_object, state);
			if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			    {
				SubLObject link_var = link;
				SubLObject cdolist_list_var = inference_datastructures_problem_link.problem_link_supporting_mapped_problems(link_var);
				SubLObject supporting_mapped_problem = NIL;
				for (supporting_mapped_problem = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), supporting_mapped_problem = cdolist_list_var.first()) {
				    if (NIL != inference_macros.do_problem_link_open_matchP(NIL, link_var, supporting_mapped_problem)) {
					{
					    SubLObject argument_problem = inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem);
					    SubLObject variable_map = inference_datastructures_problem_link.mapped_problem_variable_map(supporting_mapped_problem);
					    problem_set = inference_datastructures_problem.all_problem_argument_problems_recursive(argument_problem, problem_set);
					}
				    }
				}
			    }
			}
		    }
		}
	    }
	}
	return problem_set;
    }

    public static final SubLObject all_problem_proofs(final SubLObject problem, SubLObject proof_status) {
	if (proof_status == UNPROVIDED) {
	    proof_status = NIL;
	}
	final SubLThread thread = SubLProcess.currentSubLThread();
	SubLObject all_proofs = NIL;
	final SubLObject status_var = proof_status;
	SubLObject iteration_state;
	for (iteration_state = dictionary_contents.do_dictionary_contents_state(inference_datastructures_problem.problem_proof_bindings_index(problem)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(
		iteration_state)) {
	    thread.resetMultipleValues();
	    final SubLObject v_bindings = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
	    final SubLObject proof_list = thread.secondMultipleValue();
	    thread.resetMultipleValues();
	    SubLObject cdolist_list_var = proof_list;
	    SubLObject proof = NIL;
	    proof = cdolist_list_var.first();
	    while (NIL != cdolist_list_var) {
		if (NIL != inference_datastructures_problem.proof_has_statusP(proof, status_var)) {
		    all_proofs = cons(proof, all_proofs);
		}
		cdolist_list_var = cdolist_list_var.rest();
		proof = cdolist_list_var.first();
	    }
	}
	dictionary_contents.do_dictionary_contents_finalize(iteration_state);
	return all_proofs;
    }

    /**
     * Return all the proofs on PROBLEM, with status PROOF-STATUS.
     */
    @LispMethod(comment = "Return all the proofs on PROBLEM, with status PROOF-STATUS.")
    public static final SubLObject all_problem_proofs_alt(SubLObject problem, SubLObject proof_status) {
	if (proof_status == UNPROVIDED) {
	    proof_status = NIL;
	}
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject all_proofs = NIL;
		SubLObject status_var = proof_status;
		SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(inference_datastructures_problem.problem_proof_bindings_index(problem));
		while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
		    thread.resetMultipleValues();
		    {
			SubLObject v_bindings = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
			SubLObject proof_list = thread.secondMultipleValue();
			thread.resetMultipleValues();
			{
			    SubLObject cdolist_list_var = proof_list;
			    SubLObject proof = NIL;
			    for (proof = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), proof = cdolist_list_var.first()) {
				if (NIL != inference_datastructures_problem.proof_has_statusP(proof, status_var)) {
				    all_proofs = cons(proof, all_proofs);
				}
			    }
			}
			iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
		    }
		}
		dictionary_contents.do_dictionary_contents_finalize(iteration_state);
		return all_proofs;
	    }
	}
    }

    public static final SubLObject any_problem_has_an_in_progress_tacticP(final SubLObject problems) {
	return list_utilities.any_in_list(inference_datastructures_problem.$sym202$PROBLEM_HAS_AN_IN_PROGRESS_TACTIC_, problems, UNPROVIDED);
    }

    public static final SubLObject any_problem_has_an_in_progress_tacticP_alt(SubLObject problems) {
	return list_utilities.any_in_list(inference_datastructures_problem.$sym177$PROBLEM_HAS_AN_IN_PROGRESS_TACTIC_, problems, UNPROVIDED);
    }

    public static final SubLObject closed_problem_p(final SubLObject problem) {
	return inference_datastructures_problem.closed_problem_query_p(inference_datastructures_problem.problem_query(problem));
    }

    /**
     *
     *
     * @return booleanp; whether PROBLEM contains no variables
     */
    @LispMethod(comment = "@return booleanp; whether PROBLEM contains no variables")
    public static final SubLObject closed_problem_p_alt(SubLObject problem) {
	return inference_datastructures_problem.closed_problem_query_p(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject closed_problem_query_p(final SubLObject query) {
	return hl_ground_tree_p(query);
    }

    public static final SubLObject closed_problem_query_p_alt(SubLObject query) {
	return hl_ground_tree_p(query);
    }

    public static final SubLObject closed_single_literal_problem_query_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem_query.single_literal_problem_query_p(v_object)) && (NIL != inference_datastructures_problem.closed_problem_query_p(v_object)));
    }

    public static final SubLObject closed_single_literal_problem_query_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem_query.single_literal_problem_query_p(v_object)) && (NIL != inference_datastructures_problem.closed_problem_query_p(v_object)));
    }

    public static final SubLObject conjunctive_problem_p(final SubLObject v_object) {
	return inference_datastructures_problem.multi_literal_problem_p(v_object);
    }

    public static final SubLObject conjunctive_problem_p_alt(SubLObject v_object) {
	return inference_datastructures_problem.multi_literal_problem_p(v_object);
    }

    public static final SubLObject declare_inference_datastructures_problem_file() {
	if (false) {
	    declareFunction("problem_print_function_trampoline", "PROBLEM-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
	    declareFunction("problem_p", "PROBLEM-P", 1, 0, false);
	    new inference_datastructures_problem.$problem_p$UnaryFunction();
	    declareFunction("prob_suid", "PROB-SUID", 1, 0, false);
	    declareFunction("prob_store", "PROB-STORE", 1, 0, false);
	    declareFunction("prob_query", "PROB-QUERY", 1, 0, false);
	    declareFunction("prob_status", "PROB-STATUS", 1, 0, false);
	    declareFunction("prob_dependent_links", "PROB-DEPENDENT-LINKS", 1, 0, false);
	    declareFunction("prob_argument_links", "PROB-ARGUMENT-LINKS", 1, 0, false);
	    declareFunction("prob_tactics", "PROB-TACTICS", 1, 0, false);
	    declareFunction("prob_proof_bindings_index", "PROB-PROOF-BINDINGS-INDEX", 1, 0, false);
	    declareFunction("prob_argument_link_bindings_index", "PROB-ARGUMENT-LINK-BINDINGS-INDEX", 1, 0, false);
	    declareFunction("_csetf_prob_suid", "_CSETF-PROB-SUID", 2, 0, false);
	    declareFunction("_csetf_prob_store", "_CSETF-PROB-STORE", 2, 0, false);
	    declareFunction("_csetf_prob_query", "_CSETF-PROB-QUERY", 2, 0, false);
	    declareFunction("_csetf_prob_status", "_CSETF-PROB-STATUS", 2, 0, false);
	    declareFunction("_csetf_prob_dependent_links", "_CSETF-PROB-DEPENDENT-LINKS", 2, 0, false);
	    declareFunction("_csetf_prob_argument_links", "_CSETF-PROB-ARGUMENT-LINKS", 2, 0, false);
	    declareFunction("_csetf_prob_tactics", "_CSETF-PROB-TACTICS", 2, 0, false);
	    declareFunction("_csetf_prob_proof_bindings_index", "_CSETF-PROB-PROOF-BINDINGS-INDEX", 2, 0, false);
	    declareFunction("_csetf_prob_argument_link_bindings_index", "_CSETF-PROB-ARGUMENT-LINK-BINDINGS-INDEX", 2, 0, false);
	    declareFunction("make_problem", "MAKE-PROBLEM", 0, 1, false);
	    declareFunction("valid_problem_p", "VALID-PROBLEM-P", 1, 0, false);
	    declareFunction("problem_invalid_p", "PROBLEM-INVALID-P", 1, 0, false);
	    declareFunction("print_problem", "PRINT-PROBLEM", 3, 0, false);
	    declareFunction("sxhash_problem_method", "SXHASH-PROBLEM-METHOD", 1, 0, false);
	    new inference_datastructures_problem.$sxhash_problem_method$UnaryFunction();
	    declareFunction("new_problem", "NEW-PROBLEM", 2, 0, false);
	    declareMacro("do_problem_literals", "DO-PROBLEM-LITERALS");
	    declareMacro("do_problem_tactics", "DO-PROBLEM-TACTICS");
	    declareFunction("problem_tactics", "PROBLEM-TACTICS", 1, 0, false);
	    declareFunction("do_problem_tactics_status_match", "DO-PROBLEM-TACTICS-STATUS-MATCH", 2, 0, false);
	    declareFunction("do_problem_tactics_completeness_match", "DO-PROBLEM-TACTICS-COMPLETENESS-MATCH", 2, 0, false);
	    declareFunction("do_problem_tactics_preference_level_match", "DO-PROBLEM-TACTICS-PREFERENCE-LEVEL-MATCH", 2, 0, false);
	    declareFunction("do_problem_tactics_productivity_match", "DO-PROBLEM-TACTICS-PRODUCTIVITY-MATCH", 2, 0, false);
	    declareFunction("do_problem_tactics_hl_module_match", "DO-PROBLEM-TACTICS-HL-MODULE-MATCH", 2, 0, false);
	    declareFunction("generalized_tactic_type_p", "GENERALIZED-TACTIC-TYPE-P", 1, 0, false);
	    declareFunction("do_problem_tactics_type_match", "DO-PROBLEM-TACTICS-TYPE-MATCH", 2, 0, false);
	    declareFunction("tactic_matches_type_specP", "TACTIC-MATCHES-TYPE-SPEC?", 2, 0, false);
	    declareFunction("tactic_matches_any_of_type_specsP", "TACTIC-MATCHES-ANY-OF-TYPE-SPECS?", 2, 0, false);
	    declareFunction("problem_argument_links", "PROBLEM-ARGUMENT-LINKS", 1, 0, false);
	    declareFunction("problem_all_argument_links", "PROBLEM-ALL-ARGUMENT-LINKS", 1, 0, false);
	    declareMacro("do_problem_dependent_links", "DO-PROBLEM-DEPENDENT-LINKS");
	    declareFunction("problem_dependent_links", "PROBLEM-DEPENDENT-LINKS", 1, 0, false);
	    declareFunction("problem_all_dependent_links", "PROBLEM-ALL-DEPENDENT-LINKS", 1, 0, false);
	    declareMacro("do_problem_dependent_link_interpretations", "DO-PROBLEM-DEPENDENT-LINK-INTERPRETATIONS");
	    declareMacro("do_problem_supported_problems", "DO-PROBLEM-SUPPORTED-PROBLEMS");
	    declareMacro("do_problem_supported_inferences", "DO-PROBLEM-SUPPORTED-INFERENCES");
	    declareMacro("do_problem_supporting_problems", "DO-PROBLEM-SUPPORTING-PROBLEMS");
	    declareMacro("do_problem_proofs", "DO-PROBLEM-PROOFS");
	    declareFunction("problem_proof_bindings_index", "PROBLEM-PROOF-BINDINGS-INDEX", 1, 0, false);
	    declareFunction("proof_has_statusP", "PROOF-HAS-STATUS?", 2, 0, false);
	    declareMacro("do_problem_active_inferences", "DO-PROBLEM-ACTIVE-INFERENCES");
	    declareMacro("do_problem_relevant_inferences", "DO-PROBLEM-RELEVANT-INFERENCES");
	    declareMacro("do_problem_active_strategies", "DO-PROBLEM-ACTIVE-STRATEGIES");
	    declareMacro("do_problem_relevant_strategies", "DO-PROBLEM-RELEVANT-STRATEGIES");
	    declareMacro("do_problem_relevant_strategic_contexts", "DO-PROBLEM-RELEVANT-STRATEGIC-CONTEXTS");
	    declareFunction("destroy_problem", "DESTROY-PROBLEM", 1, 0, false);
	    declareFunction("destroy_problem_store_problem", "DESTROY-PROBLEM-STORE-PROBLEM", 1, 0, false);
	    declareFunction("destroy_problem_int", "DESTROY-PROBLEM-INT", 1, 0, false);
	    declareFunction("note_problem_invalid", "NOTE-PROBLEM-INVALID", 1, 0, false);
	    declareFunction("problem_suid", "PROBLEM-SUID", 1, 0, false);
	    declareFunction("problem_store", "PROBLEM-STORE", 1, 0, false);
	    declareFunction("problem_query", "PROBLEM-QUERY", 1, 0, false);
	    declareFunction("problem_status", "PROBLEM-STATUS", 1, 0, false);
	    declareFunction("set_problem_status", "SET-PROBLEM-STATUS", 2, 0, false);
	    declareFunction("problem_formula", "PROBLEM-FORMULA", 1, 0, false);
	    declareFunction("problem_el_formula", "PROBLEM-EL-FORMULA", 1, 0, false);
	    declareFunction("closed_problem_p", "CLOSED-PROBLEM-P", 1, 0, false);
	    declareFunction("open_problem_p", "OPEN-PROBLEM-P", 1, 0, false);
	    declareFunction("closed_problem_query_p", "CLOSED-PROBLEM-QUERY-P", 1, 0, false);
	    declareFunction("open_problem_query_p", "OPEN-PROBLEM-QUERY-P", 1, 0, false);
	    declareFunction("closed_single_literal_problem_query_p", "CLOSED-SINGLE-LITERAL-PROBLEM-QUERY-P", 1, 0, false);
	    declareFunction("open_single_literal_problem_query_p", "OPEN-SINGLE-LITERAL-PROBLEM-QUERY-P", 1, 0, false);
	    declareFunction("problem_variables", "PROBLEM-VARIABLES", 1, 0, false);
	    declareFunction("problem_literal_count", "PROBLEM-LITERAL-COUNT", 1, 1, false);
	    declareFunction("problem_query_literal_count", "PROBLEM-QUERY-LITERAL-COUNT", 1, 1, false);
	    declareFunction("single_literal_problem_p", "SINGLE-LITERAL-PROBLEM-P", 1, 0, false);
	    declareFunction("single_literal_problem_predicate", "SINGLE-LITERAL-PROBLEM-PREDICATE", 1, 0, false);
	    declareFunction("single_literal_problem_atomic_sentence", "SINGLE-LITERAL-PROBLEM-ATOMIC-SENTENCE", 1, 0, false);
	    declareFunction("single_literal_problem_mt", "SINGLE-LITERAL-PROBLEM-MT", 1, 0, false);
	    declareFunction("single_literal_problem_sense", "SINGLE-LITERAL-PROBLEM-SENSE", 1, 0, false);
	    declareFunction("mt_asent_sense_from_single_literal_problem", "MT-ASENT-SENSE-FROM-SINGLE-LITERAL-PROBLEM", 1, 0, false);
	    declareFunction("single_literal_problem_with_predicate_p", "SINGLE-LITERAL-PROBLEM-WITH-PREDICATE-P", 2, 0, false);
	    declareFunction("single_clause_problem_p", "SINGLE-CLAUSE-PROBLEM-P", 1, 0, false);
	    declareFunction("conjunctive_problem_p", "CONJUNCTIVE-PROBLEM-P", 1, 0, false);
	    declareFunction("ist_problem_p", "IST-PROBLEM-P", 1, 0, false);
	    declareFunction("join_problem_p", "JOIN-PROBLEM-P", 1, 0, false);
	    declareFunction("split_problem_p", "SPLIT-PROBLEM-P", 1, 0, false);
	    declareFunction("multi_literal_problem_p", "MULTI-LITERAL-PROBLEM-P", 1, 0, false);
	    declareFunction("disjunctive_problem_p", "DISJUNCTIVE-PROBLEM-P", 1, 0, false);
	    declareFunction("multi_clause_problem_p", "MULTI-CLAUSE-PROBLEM-P", 1, 0, false);
	    declareFunction("multi_clause_problem_query_p", "MULTI-CLAUSE-PROBLEM-QUERY-P", 1, 0, false);
	    declareFunction("problem_sole_clause", "PROBLEM-SOLE-CLAUSE", 1, 0, false);
	    declareFunction("problem_query_sole_clause", "PROBLEM-QUERY-SOLE-CLAUSE", 1, 0, false);
	    declareFunction("problem_in_equality_reasoning_domainP", "PROBLEM-IN-EQUALITY-REASONING-DOMAIN?", 1, 0, false);
	    declareFunction("problem_relevant_to_some_inferenceP", "PROBLEM-RELEVANT-TO-SOME-INFERENCE?", 1, 0, false);
	    declareFunction("first_problem_relevant_inference", "FIRST-PROBLEM-RELEVANT-INFERENCE", 1, 0, false);
	    declareFunction("problem_relevant_to_only_one_inferenceP", "PROBLEM-RELEVANT-TO-ONLY-ONE-INFERENCE?", 1, 0, false);
	    declareFunction("problem_relevant_to_inferenceP", "PROBLEM-RELEVANT-TO-INFERENCE?", 2, 0, false);
	    declareFunction("problem_relevant_to_strategyP", "PROBLEM-RELEVANT-TO-STRATEGY?", 2, 0, false);
	    declareFunction("problem_relevant_strategies", "PROBLEM-RELEVANT-STRATEGIES", 1, 0, false);
	    declareFunction("problem_active_in_some_strategyP", "PROBLEM-ACTIVE-IN-SOME-STRATEGY?", 1, 0, false);
	    declareFunction("first_problem_active_strategy", "FIRST-PROBLEM-ACTIVE-STRATEGY", 1, 0, false);
	    declareFunction("problem_argument_link_count", "PROBLEM-ARGUMENT-LINK-COUNT", 1, 0, false);
	    declareFunction("problem_argument_link_of_type_count", "PROBLEM-ARGUMENT-LINK-OF-TYPE-COUNT", 2, 0, false);
	    declareFunction("problem_has_argument_link_p", "PROBLEM-HAS-ARGUMENT-LINK-P", 1, 0, false);
	    declareFunction("problem_has_argument_link_of_typeP", "PROBLEM-HAS-ARGUMENT-LINK-OF-TYPE?", 2, 0, false);
	    declareFunction("problem_first_argument_link_of_type", "PROBLEM-FIRST-ARGUMENT-LINK-OF-TYPE", 2, 0, false);
	    declareFunction("problem_sole_argument_link_of_type", "PROBLEM-SOLE-ARGUMENT-LINK-OF-TYPE", 2, 0, false);
	    declareFunction("problem_all_argument_links_have_typeP", "PROBLEM-ALL-ARGUMENT-LINKS-HAVE-TYPE?", 2, 0, false);
	    declareFunction("problem_has_supporting_problem_p", "PROBLEM-HAS-SUPPORTING-PROBLEM-P", 1, 0, false);
	    declareFunction("all_problem_argument_problems", "ALL-PROBLEM-ARGUMENT-PROBLEMS", 1, 0, false);
	    declareFunction("all_problem_argument_problems_recursive", "ALL-PROBLEM-ARGUMENT-PROBLEMS-RECURSIVE", 2, 0, false);
	    declareFunction("problem_dependent_link_count", "PROBLEM-DEPENDENT-LINK-COUNT", 1, 0, false);
	    declareFunction("problem_sole_dependent_link", "PROBLEM-SOLE-DEPENDENT-LINK", 1, 0, false);
	    declareFunction("problem_has_dependent_link_p", "PROBLEM-HAS-DEPENDENT-LINK-P", 1, 0, false);
	    declareFunction("problem_has_dependent_link_of_typeP", "PROBLEM-HAS-DEPENDENT-LINK-OF-TYPE?", 2, 0, false);
	    declareFunction("problem_has_answer_link_p", "PROBLEM-HAS-ANSWER-LINK-P", 1, 0, false);
	    declareFunction("problem_has_non_answer_dependent_link_p", "PROBLEM-HAS-NON-ANSWER-DEPENDENT-LINK-P", 1, 0, false);
	    declareFunction("problem_has_only_non_abducible_rule_transformation_dependent_linksP", "PROBLEM-HAS-ONLY-NON-ABDUCIBLE-RULE-TRANSFORMATION-DEPENDENT-LINKS?", 1, 0, false);
	    declareFunction("problem_supported_problems", "PROBLEM-SUPPORTED-PROBLEMS", 1, 0, false);
	    declareFunction("problem_supported_problem_count", "PROBLEM-SUPPORTED-PROBLEM-COUNT", 1, 0, false);
	    declareFunction("problem_has_more_than_one_supported_problemP", "PROBLEM-HAS-MORE-THAN-ONE-SUPPORTED-PROBLEM?", 1, 0, false);
	    declareFunction("problem_next_tactic_suid", "PROBLEM-NEXT-TACTIC-SUID", 1, 0, false);
	    declareFunction("problem_tactic_count", "PROBLEM-TACTIC-COUNT", 1, 0, false);
	    declareFunction("problem_tactic_count_with_hl_module", "PROBLEM-TACTIC-COUNT-WITH-HL-MODULE", 2, 0, false);
	    declareFunction("problem_tactic_count_with_hl_module_and_status", "PROBLEM-TACTIC-COUNT-WITH-HL-MODULE-AND-STATUS", 3, 0, false);
	    declareFunction("problem_possible_tactics", "PROBLEM-POSSIBLE-TACTICS", 1, 0, false);
	    declareFunction("problem_has_possible_tacticsP", "PROBLEM-HAS-POSSIBLE-TACTICS?", 1, 0, false);
	    declareFunction("problem_no_tactics_possibleP", "PROBLEM-NO-TACTICS-POSSIBLE?", 1, 0, false);
	    declareFunction("problem_executed_tactics", "PROBLEM-EXECUTED-TACTICS", 1, 0, false);
	    declareFunction("problem_discarded_tactics", "PROBLEM-DISCARDED-TACTICS", 1, 0, false);
	    declareFunction("problem_possible_tactic_count", "PROBLEM-POSSIBLE-TACTIC-COUNT", 1, 0, false);
	    declareFunction("problem_executed_tactic_count", "PROBLEM-EXECUTED-TACTIC-COUNT", 1, 0, false);
	    declareFunction("problem_discarded_tactic_count", "PROBLEM-DISCARDED-TACTIC-COUNT", 1, 0, false);
	    declareFunction("problem_tactic_of_type_with_status_count", "PROBLEM-TACTIC-OF-TYPE-WITH-STATUS-COUNT", 1, 2, false);
	    declareFunction("problem_tactic_with_status_count", "PROBLEM-TACTIC-WITH-STATUS-COUNT", 1, 1, false);
	    declareFunction("problem_has_tactic_of_type_with_statusP", "PROBLEM-HAS-TACTIC-OF-TYPE-WITH-STATUS?", 2, 1, false);
	    declareFunction("problem_has_tactic_of_typeP", "PROBLEM-HAS-TACTIC-OF-TYPE?", 2, 0, false);
	    declareFunction("problem_has_removal_tacticsP", "PROBLEM-HAS-REMOVAL-TACTICS?", 1, 0, false);
	    declareFunction("problem_has_transformation_tacticsP", "PROBLEM-HAS-TRANSFORMATION-TACTICS?", 1, 0, false);
	    declareFunction("problem_has_possible_transformation_tacticsP", "PROBLEM-HAS-POSSIBLE-TRANSFORMATION-TACTICS?", 1, 0, false);
	    declareFunction("problem_has_possible_removal_tacticP", "PROBLEM-HAS-POSSIBLE-REMOVAL-TACTIC?", 2, 0, false);
	    declareFunction("problem_has_complete_possible_removal_tacticP", "PROBLEM-HAS-COMPLETE-POSSIBLE-REMOVAL-TACTIC?", 2, 0, false);
	    declareFunction("problem_has_split_tacticsP", "PROBLEM-HAS-SPLIT-TACTICS?", 1, 0, false);
	    declareFunction("problem_has_an_in_progress_tacticP", "PROBLEM-HAS-AN-IN-PROGRESS-TACTIC?", 1, 0, false);
	    declareFunction("any_problem_has_an_in_progress_tacticP", "ANY-PROBLEM-HAS-AN-IN-PROGRESS-TACTIC?", 1, 0, false);
	    declareFunction("problem_has_no_logical_tacticsP", "PROBLEM-HAS-NO-LOGICAL-TACTICS?", 1, 0, false);
	    declareFunction("problem_total_removal_productivity", "PROBLEM-TOTAL-REMOVAL-PRODUCTIVITY", 1, 0, false);
	    declareFunction("problem_total_deductive_removal_productivity", "PROBLEM-TOTAL-DEDUCTIVE-REMOVAL-PRODUCTIVITY", 1, 0, false);
	    declareFunction("problem_total_actual_removal_productivity", "PROBLEM-TOTAL-ACTUAL-REMOVAL-PRODUCTIVITY", 1, 0, false);
	    declareFunction("problem_possible_removal_tactics", "PROBLEM-POSSIBLE-REMOVAL-TACTICS", 1, 0, false);
	    declareFunction("problem_executed_removal_tactic_productivities", "PROBLEM-EXECUTED-REMOVAL-TACTIC-PRODUCTIVITIES", 1, 0, false);
	    declareFunction("all_problem_proofs", "ALL-PROBLEM-PROOFS", 1, 1, false);
	    declareFunction("problem_proof_count", "PROBLEM-PROOF-COUNT", 1, 1, false);
	    declareFunction("problem_proven_proof_count", "PROBLEM-PROVEN-PROOF-COUNT", 1, 0, false);
	    declareFunction("problem_has_some_proofP", "PROBLEM-HAS-SOME-PROOF?", 1, 1, false);
	    declareFunction("problem_has_some_proven_proofP", "PROBLEM-HAS-SOME-PROVEN-PROOF?", 1, 0, false);
	    declareFunction("problem_has_some_rejected_proofP", "PROBLEM-HAS-SOME-REJECTED-PROOF?", 1, 0, false);
	    declareFunction("problem_proofs_lookup", "PROBLEM-PROOFS-LOOKUP", 2, 0, false);
	    declareFunction("problem_argument_links_lookup", "PROBLEM-ARGUMENT-LINKS-LOOKUP", 2, 0, false);
	    declareFunction("problem_indestructibleP", "PROBLEM-INDESTRUCTIBLE?", 1, 0, false);
	    declareFunction("problem_destructibleP", "PROBLEM-DESTRUCTIBLE?", 1, 0, false);
	    declareFunction("problem_destructibility_status", "PROBLEM-DESTRUCTIBILITY-STATUS", 1, 0, false);
	    declareFunction("problem_min_depth", "PROBLEM-MIN-DEPTH", 1, 0, false);
	    declareFunction("problem_min_proof_depth", "PROBLEM-MIN-PROOF-DEPTH", 2, 0, false);
	    declareFunction("problem_min_transformation_depth", "PROBLEM-MIN-TRANSFORMATION-DEPTH", 2, 0, false);
	    declareFunction("problem_min_transformation_depth_signature", "PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 2, 0, false);
	    declareFunction("add_problem_argument_link", "ADD-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	    declareFunction("remove_problem_argument_link", "REMOVE-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	    declareFunction("index_problem_argument_link", "INDEX-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	    declareFunction("deindex_problem_argument_link", "DEINDEX-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	    declareFunction("add_problem_dependent_link", "ADD-PROBLEM-DEPENDENT-LINK", 2, 0, false);
	    declareFunction("remove_problem_dependent_link", "REMOVE-PROBLEM-DEPENDENT-LINK", 2, 0, false);
	    declareFunction("add_problem_tactic", "ADD-PROBLEM-TACTIC", 2, 0, false);
	    declareFunction("remove_problem_tactic", "REMOVE-PROBLEM-TACTIC", 2, 0, false);
	    declareFunction("add_problem_proof", "ADD-PROBLEM-PROOF", 2, 0, false);
	    declareFunction("remove_problem_proof", "REMOVE-PROBLEM-PROOF", 2, 0, false);
	    declareFunction("remove_problem_proof_with_bindings", "REMOVE-PROBLEM-PROOF-WITH-BINDINGS", 3, 0, false);
	    declareFunction("set_problem_min_depth", "SET-PROBLEM-MIN-DEPTH", 2, 0, false);
	    declareFunction("set_problem_min_proof_depth", "SET-PROBLEM-MIN-PROOF-DEPTH", 3, 0, false);
	    declareFunction("set_problem_min_transformation_depth", "SET-PROBLEM-MIN-TRANSFORMATION-DEPTH", 3, 0, false);
	    declareFunction("set_problem_min_transformation_depth_signature", "SET-PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 3, 0, false);
	    declareFunction("set_root_problem_min_transformation_depth_signature", "SET-ROOT-PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 2, 0, false);
	    declareFunction("note_problem_indestructible", "NOTE-PROBLEM-INDESTRUCTIBLE", 1, 0, false);
	    declareFunction("note_problem_destructible", "NOTE-PROBLEM-DESTRUCTIBLE", 1, 0, false);
	    declareFunction("note_problem_min_transformation_depth_signature", "NOTE-PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 3, 0, false);
	    declareFunction("problem_tactical_provability_status", "PROBLEM-TACTICAL-PROVABILITY-STATUS", 1, 0, false);
	    declareFunction("tactically_good_problem_p", "TACTICALLY-GOOD-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_no_good_problem_p", "TACTICALLY-NO-GOOD-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_neutral_problem_p", "TACTICALLY-NEUTRAL-PROBLEM-P", 1, 0, false);
	    declareFunction("problem_tactical_status", "PROBLEM-TACTICAL-STATUS", 1, 0, false);
	    declareFunction("tactically_new_problem_p", "TACTICALLY-NEW-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_unexamined_problem_p", "TACTICALLY-UNEXAMINED-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_examined_problem_p", "TACTICALLY-EXAMINED-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_possible_problem_p", "TACTICALLY-POSSIBLE-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_pending_problem_p", "TACTICALLY-PENDING-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_finished_problem_p", "TACTICALLY-FINISHED-PROBLEM-P", 1, 0, false);
	    declareFunction("tactical_problem_p", "TACTICAL-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_potentially_possible_problem_p", "TACTICALLY-POTENTIALLY-POSSIBLE-PROBLEM-P", 1, 0, false);
	    declareFunction("tactically_not_potentially_possible_problem_p", "TACTICALLY-NOT-POTENTIALLY-POSSIBLE-PROBLEM-P", 1, 0, false);
	    declareFunction("problem_store_all_modules", "PROBLEM-STORE-ALL-MODULES", 1, 0, false);
	}
	declareFunction("problem_print_function_trampoline", "PROBLEM-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
	declareFunction("problem_p", "PROBLEM-P", 1, 0, false);
	new inference_datastructures_problem.$problem_p$UnaryFunction();
	declareFunction("prob_suid", "PROB-SUID", 1, 0, false);
	declareFunction("prob_store", "PROB-STORE", 1, 0, false);
	declareFunction("prob_query", "PROB-QUERY", 1, 0, false);
	declareFunction("prob_free_hl_vars", "PROB-FREE-HL-VARS", 1, 0, false);
	declareFunction("prob_status", "PROB-STATUS", 1, 0, false);
	declareFunction("prob_dependent_links", "PROB-DEPENDENT-LINKS", 1, 0, false);
	declareFunction("prob_argument_links", "PROB-ARGUMENT-LINKS", 1, 0, false);
	declareFunction("prob_tactics", "PROB-TACTICS", 1, 0, false);
	declareFunction("prob_proof_bindings_index", "PROB-PROOF-BINDINGS-INDEX", 1, 0, false);
	declareFunction("prob_argument_link_bindings_index", "PROB-ARGUMENT-LINK-BINDINGS-INDEX", 1, 0, false);
	declareFunction("prob_backchain_required", "PROB-BACKCHAIN-REQUIRED", 1, 0, false);
	declareFunction("prob_memoization_state", "PROB-MEMOIZATION-STATE", 1, 0, false);
	declareFunction("_csetf_prob_suid", "_CSETF-PROB-SUID", 2, 0, false);
	declareFunction("_csetf_prob_store", "_CSETF-PROB-STORE", 2, 0, false);
	declareFunction("_csetf_prob_query", "_CSETF-PROB-QUERY", 2, 0, false);
	declareFunction("_csetf_prob_free_hl_vars", "_CSETF-PROB-FREE-HL-VARS", 2, 0, false);
	declareFunction("_csetf_prob_status", "_CSETF-PROB-STATUS", 2, 0, false);
	declareFunction("_csetf_prob_dependent_links", "_CSETF-PROB-DEPENDENT-LINKS", 2, 0, false);
	declareFunction("_csetf_prob_argument_links", "_CSETF-PROB-ARGUMENT-LINKS", 2, 0, false);
	declareFunction("_csetf_prob_tactics", "_CSETF-PROB-TACTICS", 2, 0, false);
	declareFunction("_csetf_prob_proof_bindings_index", "_CSETF-PROB-PROOF-BINDINGS-INDEX", 2, 0, false);
	declareFunction("_csetf_prob_argument_link_bindings_index", "_CSETF-PROB-ARGUMENT-LINK-BINDINGS-INDEX", 2, 0, false);
	declareFunction("_csetf_prob_backchain_required", "_CSETF-PROB-BACKCHAIN-REQUIRED", 2, 0, false);
	declareFunction("_csetf_prob_memoization_state", "_CSETF-PROB-MEMOIZATION-STATE", 2, 0, false);
	declareFunction("make_problem", "MAKE-PROBLEM", 0, 1, false);
	declareFunction("visit_defstruct_problem", "VISIT-DEFSTRUCT-PROBLEM", 2, 0, false);
	declareFunction("visit_defstruct_object_problem_method", "VISIT-DEFSTRUCT-OBJECT-PROBLEM-METHOD", 2, 0, false);
	declareFunction("valid_problem_p", "VALID-PROBLEM-P", 1, 0, false);
	declareFunction("problem_invalid_p", "PROBLEM-INVALID-P", 1, 0, false);
	declareFunction("print_problem", "PRINT-PROBLEM", 3, 0, false);
	declareFunction("sxhash_problem_method", "SXHASH-PROBLEM-METHOD", 1, 0, false);
	new inference_datastructures_problem.$sxhash_problem_method$UnaryFunction();
	declareFunction("new_problem", "NEW-PROBLEM", 3, 0, false);
	declareMacro("with_problem_memoization_state", "WITH-PROBLEM-MEMOIZATION-STATE");
	declareFunction("problem_memoization_state", "PROBLEM-MEMOIZATION-STATE", 1, 0, false);
	declareMacro("do_problem_literals", "DO-PROBLEM-LITERALS");
	declareMacro("do_problem_tactics", "DO-PROBLEM-TACTICS");
	declareFunction("problem_tactics", "PROBLEM-TACTICS", 1, 0, false);
	declareFunction("do_problem_tactics_status_match", "DO-PROBLEM-TACTICS-STATUS-MATCH", 2, 0, false);
	declareFunction("do_problem_tactics_completeness_match", "DO-PROBLEM-TACTICS-COMPLETENESS-MATCH", 2, 0, false);
	declareFunction("do_problem_tactics_preference_level_match", "DO-PROBLEM-TACTICS-PREFERENCE-LEVEL-MATCH", 2, 0, false);
	declareFunction("do_problem_tactics_productivity_match", "DO-PROBLEM-TACTICS-PRODUCTIVITY-MATCH", 2, 0, false);
	declareFunction("do_problem_tactics_hl_module_match", "DO-PROBLEM-TACTICS-HL-MODULE-MATCH", 2, 0, false);
	declareFunction("generalized_tactic_type_p", "GENERALIZED-TACTIC-TYPE-P", 1, 0, false);
	declareFunction("do_problem_tactics_type_match", "DO-PROBLEM-TACTICS-TYPE-MATCH", 2, 0, false);
	declareFunction("tactic_matches_type_specP", "TACTIC-MATCHES-TYPE-SPEC?", 2, 0, false);
	declareFunction("tactic_matches_any_of_type_specsP", "TACTIC-MATCHES-ANY-OF-TYPE-SPECS?", 2, 0, false);
	declareFunction("problem_argument_links", "PROBLEM-ARGUMENT-LINKS", 1, 0, false);
	declareFunction("problem_all_argument_links", "PROBLEM-ALL-ARGUMENT-LINKS", 1, 0, false);
	declareMacro("do_problem_dependent_links", "DO-PROBLEM-DEPENDENT-LINKS");
	declareFunction("problem_dependent_links", "PROBLEM-DEPENDENT-LINKS", 1, 0, false);
	declareFunction("problem_all_dependent_links", "PROBLEM-ALL-DEPENDENT-LINKS", 1, 0, false);
	declareMacro("do_problem_dependent_link_interpretations", "DO-PROBLEM-DEPENDENT-LINK-INTERPRETATIONS");
	declareMacro("do_problem_supported_problems", "DO-PROBLEM-SUPPORTED-PROBLEMS");
	declareMacro("do_problem_supported_inferences", "DO-PROBLEM-SUPPORTED-INFERENCES");
	declareMacro("do_problem_supporting_problems", "DO-PROBLEM-SUPPORTING-PROBLEMS");
	declareMacro("do_problem_proofs", "DO-PROBLEM-PROOFS");
	declareFunction("problem_proof_bindings_index", "PROBLEM-PROOF-BINDINGS-INDEX", 1, 0, false);
	declareFunction("proof_has_statusP", "PROOF-HAS-STATUS?", 2, 0, false);
	declareMacro("do_problem_active_inferences", "DO-PROBLEM-ACTIVE-INFERENCES");
	declareMacro("do_problem_relevant_inferences", "DO-PROBLEM-RELEVANT-INFERENCES");
	declareMacro("do_problem_active_strategies", "DO-PROBLEM-ACTIVE-STRATEGIES");
	declareMacro("do_problem_relevant_strategies", "DO-PROBLEM-RELEVANT-STRATEGIES");
	declareMacro("do_problem_relevant_strategic_contexts", "DO-PROBLEM-RELEVANT-STRATEGIC-CONTEXTS");
	declareFunction("destroy_problem", "DESTROY-PROBLEM", 1, 0, false);
	declareFunction("destroy_problem_store_problem", "DESTROY-PROBLEM-STORE-PROBLEM", 1, 0, false);
	declareFunction("destroy_problem_int", "DESTROY-PROBLEM-INT", 1, 0, false);
	declareFunction("note_problem_invalid", "NOTE-PROBLEM-INVALID", 1, 0, false);
	declareFunction("problem_suid", "PROBLEM-SUID", 1, 0, false);
	declareFunction("problem_store", "PROBLEM-STORE", 1, 0, false);
	declareFunction("problem_query", "PROBLEM-QUERY", 1, 0, false);
	declareFunction("problem_free_hl_vars", "PROBLEM-FREE-HL-VARS", 1, 0, false);
	declareFunction("problem_status", "PROBLEM-STATUS", 1, 0, false);
	declareFunction("problem_dwimmed_status", "PROBLEM-DWIMMED-STATUS", 1, 0, false);
	declareFunction("set_problem_free_hl_vars", "SET-PROBLEM-FREE-HL-VARS", 2, 0, false);
	declareFunction("set_problem_status", "SET-PROBLEM-STATUS", 2, 0, false);
	declareFunction("problem_formula", "PROBLEM-FORMULA", 1, 0, false);
	declareFunction("problem_el_formula", "PROBLEM-EL-FORMULA", 1, 0, false);
	declareFunction("closed_problem_p", "CLOSED-PROBLEM-P", 1, 0, false);
	declareFunction("open_problem_p", "OPEN-PROBLEM-P", 1, 0, false);
	declareFunction("closed_problem_query_p", "CLOSED-PROBLEM-QUERY-P", 1, 0, false);
	declareFunction("open_problem_query_p", "OPEN-PROBLEM-QUERY-P", 1, 0, false);
	declareFunction("closed_single_literal_problem_query_p", "CLOSED-SINGLE-LITERAL-PROBLEM-QUERY-P", 1, 0, false);
	declareFunction("open_single_literal_problem_query_p", "OPEN-SINGLE-LITERAL-PROBLEM-QUERY-P", 1, 0, false);
	declareFunction("problem_variables", "PROBLEM-VARIABLES", 1, 0, false);
	declareFunction("problem_literal_count", "PROBLEM-LITERAL-COUNT", 1, 1, false);
	declareFunction("problem_query_literal_count", "PROBLEM-QUERY-LITERAL-COUNT", 1, 1, false);
	declareFunction("single_literal_problem_p", "SINGLE-LITERAL-PROBLEM-P", 1, 0, false);
	declareFunction("single_literal_problem_predicate", "SINGLE-LITERAL-PROBLEM-PREDICATE", 1, 0, false);
	declareFunction("single_literal_problem_atomic_sentence", "SINGLE-LITERAL-PROBLEM-ATOMIC-SENTENCE", 1, 0, false);
	declareFunction("single_literal_problem_mt", "SINGLE-LITERAL-PROBLEM-MT", 1, 0, false);
	declareFunction("problem_mt", "PROBLEM-MT", 1, 0, false);
	declareFunction("problem_mts", "PROBLEM-MTS", 1, 0, false);
	declareFunction("single_literal_problem_sense", "SINGLE-LITERAL-PROBLEM-SENSE", 1, 0, false);
	declareFunction("mt_asent_sense_from_single_literal_problem", "MT-ASENT-SENSE-FROM-SINGLE-LITERAL-PROBLEM", 1, 0, false);
	declareFunction("single_literal_problem_with_predicate_p", "SINGLE-LITERAL-PROBLEM-WITH-PREDICATE-P", 2, 0, false);
	declareFunction("single_clause_problem_p", "SINGLE-CLAUSE-PROBLEM-P", 1, 0, false);
	declareFunction("conjunctive_problem_p", "CONJUNCTIVE-PROBLEM-P", 1, 0, false);
	declareFunction("ist_problem_p", "IST-PROBLEM-P", 1, 0, false);
	declareFunction("join_problem_p", "JOIN-PROBLEM-P", 1, 0, false);
	declareFunction("split_problem_p", "SPLIT-PROBLEM-P", 1, 0, false);
	declareFunction("multi_literal_problem_p", "MULTI-LITERAL-PROBLEM-P", 1, 0, false);
	declareFunction("disjunctive_problem_p", "DISJUNCTIVE-PROBLEM-P", 1, 0, false);
	declareFunction("multi_clause_problem_p", "MULTI-CLAUSE-PROBLEM-P", 1, 0, false);
	declareFunction("multi_clause_problem_query_p", "MULTI-CLAUSE-PROBLEM-QUERY-P", 1, 0, false);
	declareFunction("problem_sole_clause", "PROBLEM-SOLE-CLAUSE", 1, 0, false);
	declareFunction("problem_query_sole_clause", "PROBLEM-QUERY-SOLE-CLAUSE", 1, 0, false);
	declareFunction("problem_in_equality_reasoning_domainP", "PROBLEM-IN-EQUALITY-REASONING-DOMAIN?", 1, 0, false);
	declareFunction("problem_relevant_to_some_inferenceP", "PROBLEM-RELEVANT-TO-SOME-INFERENCE?", 1, 0, false);
	declareFunction("first_problem_relevant_inference", "FIRST-PROBLEM-RELEVANT-INFERENCE", 1, 0, false);
	declareFunction("problem_relevant_to_only_one_inferenceP", "PROBLEM-RELEVANT-TO-ONLY-ONE-INFERENCE?", 1, 0, false);
	declareFunction("problem_relevant_to_inferenceP", "PROBLEM-RELEVANT-TO-INFERENCE?", 2, 0, false);
	declareFunction("problem_relevant_to_strategyP", "PROBLEM-RELEVANT-TO-STRATEGY?", 2, 0, false);
	declareFunction("problem_relevant_strategies", "PROBLEM-RELEVANT-STRATEGIES", 1, 0, false);
	declareFunction("problem_active_in_some_strategyP", "PROBLEM-ACTIVE-IN-SOME-STRATEGY?", 1, 0, false);
	declareFunction("first_problem_active_strategy", "FIRST-PROBLEM-ACTIVE-STRATEGY", 1, 0, false);
	declareFunction("problem_argument_link_count", "PROBLEM-ARGUMENT-LINK-COUNT", 1, 0, false);
	declareFunction("problem_argument_link_of_type_count", "PROBLEM-ARGUMENT-LINK-OF-TYPE-COUNT", 2, 0, false);
	declareFunction("problem_has_argument_link_p", "PROBLEM-HAS-ARGUMENT-LINK-P", 1, 0, false);
	declareFunction("problem_has_argument_link_of_typeP", "PROBLEM-HAS-ARGUMENT-LINK-OF-TYPE?", 2, 0, false);
	declareFunction("problem_first_argument_link_of_type", "PROBLEM-FIRST-ARGUMENT-LINK-OF-TYPE", 2, 0, false);
	declareFunction("problem_sole_argument_link_of_type", "PROBLEM-SOLE-ARGUMENT-LINK-OF-TYPE", 2, 0, false);
	declareFunction("problem_all_argument_links_have_typeP", "PROBLEM-ALL-ARGUMENT-LINKS-HAVE-TYPE?", 2, 0, false);
	declareFunction("problem_has_supporting_problem_p", "PROBLEM-HAS-SUPPORTING-PROBLEM-P", 1, 0, false);
	declareFunction("all_problem_argument_problems", "ALL-PROBLEM-ARGUMENT-PROBLEMS", 1, 0, false);
	declareFunction("all_problem_argument_problems_recursive", "ALL-PROBLEM-ARGUMENT-PROBLEMS-RECURSIVE", 2, 0, false);
	declareFunction("problem_dependent_link_count", "PROBLEM-DEPENDENT-LINK-COUNT", 1, 0, false);
	declareFunction("problem_sole_dependent_link", "PROBLEM-SOLE-DEPENDENT-LINK", 1, 0, false);
	declareFunction("problem_has_dependent_link_p", "PROBLEM-HAS-DEPENDENT-LINK-P", 1, 0, false);
	declareFunction("problem_has_dependent_link_of_typeP", "PROBLEM-HAS-DEPENDENT-LINK-OF-TYPE?", 2, 0, false);
	declareFunction("problem_has_answer_link_p", "PROBLEM-HAS-ANSWER-LINK-P", 1, 0, false);
	declareFunction("problem_has_non_answer_dependent_link_p", "PROBLEM-HAS-NON-ANSWER-DEPENDENT-LINK-P", 1, 0, false);
	declareFunction("problem_has_only_non_abducible_rule_transformation_dependent_linksP", "PROBLEM-HAS-ONLY-NON-ABDUCIBLE-RULE-TRANSFORMATION-DEPENDENT-LINKS?", 1, 0, false);
	declareFunction("problem_supported_problems", "PROBLEM-SUPPORTED-PROBLEMS", 1, 0, false);
	declareFunction("problem_supported_problem_count", "PROBLEM-SUPPORTED-PROBLEM-COUNT", 1, 0, false);
	declareFunction("problem_has_more_than_one_supported_problemP", "PROBLEM-HAS-MORE-THAN-ONE-SUPPORTED-PROBLEM?", 1, 0, false);
	declareFunction("problem_next_tactic_suid", "PROBLEM-NEXT-TACTIC-SUID", 1, 0, false);
	declareFunction("problem_tactic_count", "PROBLEM-TACTIC-COUNT", 1, 0, false);
	declareFunction("problem_has_no_tacticsP", "PROBLEM-HAS-NO-TACTICS?", 1, 0, false);
	declareFunction("problem_tactic_count_with_hl_module", "PROBLEM-TACTIC-COUNT-WITH-HL-MODULE", 2, 0, false);
	declareFunction("problem_tactic_count_with_hl_module_and_status", "PROBLEM-TACTIC-COUNT-WITH-HL-MODULE-AND-STATUS", 3, 0, false);
	declareFunction("problem_possible_tactics", "PROBLEM-POSSIBLE-TACTICS", 1, 0, false);
	declareFunction("problem_has_possible_tacticsP", "PROBLEM-HAS-POSSIBLE-TACTICS?", 1, 0, false);
	declareFunction("problem_no_tactics_possibleP", "PROBLEM-NO-TACTICS-POSSIBLE?", 1, 0, false);
	declareFunction("problem_executed_tactics", "PROBLEM-EXECUTED-TACTICS", 1, 0, false);
	declareFunction("problem_discarded_tactics", "PROBLEM-DISCARDED-TACTICS", 1, 0, false);
	declareFunction("problem_possible_tactic_count", "PROBLEM-POSSIBLE-TACTIC-COUNT", 1, 0, false);
	declareFunction("problem_executed_tactic_count", "PROBLEM-EXECUTED-TACTIC-COUNT", 1, 0, false);
	declareFunction("problem_discarded_tactic_count", "PROBLEM-DISCARDED-TACTIC-COUNT", 1, 0, false);
	declareFunction("problem_tactic_of_type_with_status_count", "PROBLEM-TACTIC-OF-TYPE-WITH-STATUS-COUNT", 1, 2, false);
	declareFunction("problem_tactic_with_status_count", "PROBLEM-TACTIC-WITH-STATUS-COUNT", 1, 1, false);
	declareFunction("problem_has_tactic_of_type_with_statusP", "PROBLEM-HAS-TACTIC-OF-TYPE-WITH-STATUS?", 2, 1, false);
	declareFunction("problem_has_tactic_of_typeP", "PROBLEM-HAS-TACTIC-OF-TYPE?", 2, 0, false);
	declareFunction("problem_has_removal_tacticsP", "PROBLEM-HAS-REMOVAL-TACTICS?", 1, 0, false);
	declareFunction("problem_has_transformation_tacticsP", "PROBLEM-HAS-TRANSFORMATION-TACTICS?", 1, 0, false);
	declareFunction("problem_has_possible_transformation_tacticsP", "PROBLEM-HAS-POSSIBLE-TRANSFORMATION-TACTICS?", 1, 0, false);
	declareFunction("problem_has_possible_removal_tacticP", "PROBLEM-HAS-POSSIBLE-REMOVAL-TACTIC?", 2, 0, false);
	declareFunction("problem_has_complete_possible_removal_tacticP", "PROBLEM-HAS-COMPLETE-POSSIBLE-REMOVAL-TACTIC?", 2, 0, false);
	declareFunction("problem_has_split_tacticsP", "PROBLEM-HAS-SPLIT-TACTICS?", 1, 0, false);
	declareFunction("problem_has_an_in_progress_tacticP", "PROBLEM-HAS-AN-IN-PROGRESS-TACTIC?", 1, 0, false);
	declareFunction("problem_has_an_in_progress_complete_removal_tacticP", "PROBLEM-HAS-AN-IN-PROGRESS-COMPLETE-REMOVAL-TACTIC?", 2, 0, false);
	declareFunction("any_problem_has_an_in_progress_tacticP", "ANY-PROBLEM-HAS-AN-IN-PROGRESS-TACTIC?", 1, 0, false);
	declareFunction("problem_has_no_logical_tacticsP", "PROBLEM-HAS-NO-LOGICAL-TACTICS?", 1, 0, false);
	declareFunction("problem_total_removal_productivity", "PROBLEM-TOTAL-REMOVAL-PRODUCTIVITY", 1, 0, false);
	declareFunction("problem_total_deductive_removal_productivity", "PROBLEM-TOTAL-DEDUCTIVE-REMOVAL-PRODUCTIVITY", 1, 0, false);
	declareFunction("problem_total_actual_removal_productivity", "PROBLEM-TOTAL-ACTUAL-REMOVAL-PRODUCTIVITY", 1, 0, false);
	declareFunction("problem_possible_removal_tactics", "PROBLEM-POSSIBLE-REMOVAL-TACTICS", 1, 0, false);
	declareFunction("problem_executed_removal_tactic_productivities", "PROBLEM-EXECUTED-REMOVAL-TACTIC-PRODUCTIVITIES", 1, 0, false);
	declareFunction("all_problem_proofs", "ALL-PROBLEM-PROOFS", 1, 1, false);
	declareFunction("problem_proof_count", "PROBLEM-PROOF-COUNT", 1, 1, false);
	declareFunction("problem_proven_proof_count", "PROBLEM-PROVEN-PROOF-COUNT", 1, 0, false);
	declareFunction("problem_has_some_proofP", "PROBLEM-HAS-SOME-PROOF?", 1, 1, false);
	declareFunction("problem_has_some_proven_proofP", "PROBLEM-HAS-SOME-PROVEN-PROOF?", 1, 0, false);
	declareFunction("problem_has_some_rejected_proofP", "PROBLEM-HAS-SOME-REJECTED-PROOF?", 1, 0, false);
	declareFunction("problem_proofs_lookup", "PROBLEM-PROOFS-LOOKUP", 2, 0, false);
	declareFunction("problem_argument_links_lookup", "PROBLEM-ARGUMENT-LINKS-LOOKUP", 2, 0, false);
	declareFunction("problem_potentially_processedP", "PROBLEM-POTENTIALLY-PROCESSED?", 1, 0, false);
	declareFunction("note_problem_potentially_processed", "NOTE-PROBLEM-POTENTIALLY-PROCESSED", 1, 0, false);
	declareFunction("problem_indestructibleP", "PROBLEM-INDESTRUCTIBLE?", 1, 0, false);
	declareFunction("problem_destructibleP", "PROBLEM-DESTRUCTIBLE?", 1, 0, false);
	declareFunction("problem_destructibility_status", "PROBLEM-DESTRUCTIBILITY-STATUS", 1, 0, false);
	declareFunction("problem_min_depth", "PROBLEM-MIN-DEPTH", 1, 0, false);
	declareFunction("problem_min_proof_depth", "PROBLEM-MIN-PROOF-DEPTH", 2, 0, false);
	declareFunction("problem_intuitive_min_transformation_depth", "PROBLEM-INTUITIVE-MIN-TRANSFORMATION-DEPTH", 2, 0, false);
	declareFunction("problem_min_transformation_depth", "PROBLEM-MIN-TRANSFORMATION-DEPTH", 2, 0, false);
	declareFunction("problem_min_transformation_depth_signature", "PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 2, 0, false);
	declareFunction("problem_backchain_requiredP", "PROBLEM-BACKCHAIN-REQUIRED?", 1, 0, false);
	declareFunction("add_problem_argument_link", "ADD-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	declareFunction("remove_problem_argument_link", "REMOVE-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	declareFunction("index_problem_argument_link", "INDEX-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	declareFunction("deindex_problem_argument_link", "DEINDEX-PROBLEM-ARGUMENT-LINK", 2, 0, false);
	declareFunction("add_problem_dependent_link", "ADD-PROBLEM-DEPENDENT-LINK", 2, 0, false);
	declareFunction("remove_problem_dependent_link", "REMOVE-PROBLEM-DEPENDENT-LINK", 2, 0, false);
	declareFunction("add_problem_tactic", "ADD-PROBLEM-TACTIC", 2, 0, false);
	declareFunction("remove_problem_tactic", "REMOVE-PROBLEM-TACTIC", 2, 0, false);
	declareFunction("add_problem_proof", "ADD-PROBLEM-PROOF", 2, 0, false);
	declareFunction("remove_problem_proof", "REMOVE-PROBLEM-PROOF", 2, 0, false);
	declareFunction("remove_problem_proof_with_bindings", "REMOVE-PROBLEM-PROOF-WITH-BINDINGS", 3, 0, false);
	declareFunction("set_problem_min_depth", "SET-PROBLEM-MIN-DEPTH", 2, 0, false);
	declareFunction("set_problem_min_proof_depth", "SET-PROBLEM-MIN-PROOF-DEPTH", 3, 0, false);
	declareFunction("set_problem_min_transformation_depth", "SET-PROBLEM-MIN-TRANSFORMATION-DEPTH", 3, 0, false);
	declareFunction("set_problem_min_transformation_depth_signature", "SET-PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 3, 0, false);
	declareFunction("set_root_problem_min_transformation_depth_signature", "SET-ROOT-PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 2, 0, false);
	declareFunction("note_problem_indestructible", "NOTE-PROBLEM-INDESTRUCTIBLE", 1, 0, false);
	declareFunction("note_problem_destructible", "NOTE-PROBLEM-DESTRUCTIBLE", 1, 0, false);
	declareFunction("note_problem_min_transformation_depth_signature", "NOTE-PROBLEM-MIN-TRANSFORMATION-DEPTH-SIGNATURE", 3, 0, false);
	declareFunction("problem_tactical_provability_status", "PROBLEM-TACTICAL-PROVABILITY-STATUS", 1, 0, false);
	declareFunction("tactically_good_problem_p", "TACTICALLY-GOOD-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_no_good_problem_p", "TACTICALLY-NO-GOOD-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_neutral_problem_p", "TACTICALLY-NEUTRAL-PROBLEM-P", 1, 0, false);
	declareFunction("problem_tactical_status", "PROBLEM-TACTICAL-STATUS", 1, 0, false);
	declareFunction("tactically_new_problem_p", "TACTICALLY-NEW-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_unexamined_problem_p", "TACTICALLY-UNEXAMINED-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_examined_problem_p", "TACTICALLY-EXAMINED-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_possible_problem_p", "TACTICALLY-POSSIBLE-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_pending_problem_p", "TACTICALLY-PENDING-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_finished_problem_p", "TACTICALLY-FINISHED-PROBLEM-P", 1, 0, false);
	declareFunction("tactical_problem_p", "TACTICAL-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_potentially_possible_problem_p", "TACTICALLY-POTENTIALLY-POSSIBLE-PROBLEM-P", 1, 0, false);
	declareFunction("tactically_not_potentially_possible_problem_p", "TACTICALLY-NOT-POTENTIALLY-POSSIBLE-PROBLEM-P", 1, 0, false);
	declareFunction("problem_store_all_modules", "PROBLEM-STORE-ALL-MODULES", 1, 0, false);
	return NIL;
    }

    public static final SubLObject deindex_problem_argument_link(final SubLObject problem, final SubLObject argument_link) {
	final SubLObject index = inference_datastructures_problem.prob_argument_link_bindings_index(problem);
	final SubLObject v_bindings = (NIL != inference_worker_removal.removal_link_p(argument_link)) ? inference_worker_removal.removal_link_bindings(argument_link)
		: NIL != inference_worker_restriction.restriction_link_p(argument_link) ? inference_worker_restriction.restriction_link_bindings(argument_link) : NIL;
	final SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	final SubLObject updated = list_utilities.delete_first(argument_link, existing, symbol_function(EQ));
	if (!existing.eql(updated)) {
	    if (NIL == updated) {
		inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.dictionary_contents_delete(index, v_bindings, symbol_function(EQUAL)));
	    } else {
		inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, updated, symbol_function(EQUAL)));
	    }
	}
	return problem;
    }

    public static final SubLObject deindex_problem_argument_link_alt(SubLObject problem, SubLObject argument_link) {
	{
	    SubLObject index = inference_datastructures_problem.prob_argument_link_bindings_index(problem);
	    SubLObject v_bindings = (NIL != inference_worker_removal.removal_link_p(argument_link)) ? ((SubLObject) (inference_worker_removal.removal_link_bindings(argument_link)))
		    : NIL != inference_worker_restriction.restriction_link_p(argument_link) ? ((SubLObject) (inference_worker_restriction.restriction_link_bindings(argument_link))) : NIL;
	    SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	    SubLObject updated = list_utilities.delete_first(argument_link, existing, symbol_function(EQ));
	    if (existing != updated) {
		if (NIL == updated) {
		    inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.dictionary_contents_delete(index, v_bindings, symbol_function(EQUAL)));
		} else {
		    inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, updated, symbol_function(EQUAL)));
		}
	    }
	}
	return problem;
    }

    public static final SubLObject destroy_problem(final SubLObject problem) {
	if (NIL != inference_datastructures_problem.valid_problem_p(problem)) {
	    inference_datastructures_problem.note_problem_invalid(problem);
	    SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	    SubLObject basis_object;
	    SubLObject state;
	    SubLObject argument_link;
	    for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		    state); state = set_contents.do_set_contents_update_state(state)) {
		argument_link = set_contents.do_set_contents_next(basis_object, state);
		if ((NIL != set_contents.do_set_contents_element_validP(state, argument_link)) && (NIL != inference_datastructures_problem_link.valid_problem_link_p(argument_link))) {
		    inference_datastructures_problem_link.destroy_problem_link(argument_link);
		}
	    }
	    set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject dependent_link;
	    for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		    state); state = set_contents.do_set_contents_update_state(state)) {
		dependent_link = set_contents.do_set_contents_next(basis_object, state);
		if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
		    if (NIL != inference_worker_restriction.restriction_link_p(dependent_link)) {
			inference_worker_join_ordered.note_all_triggering_proofs_processed(dependent_link);
		    }
		    if (NIL != inference_datastructures_problem_link.valid_problem_link_p(dependent_link)) {
			inference_datastructures_problem_link.destroy_problem_link(dependent_link);
		    }
		}
	    }
	    if (NIL != inference_datastructures_problem.problem_potentially_processedP(problem)) {
		inference_datastructures_problem_store.problem_store_note_problem_unprocessed(inference_datastructures_problem.problem_store(problem), problem);
	    }
	    final SubLObject store = inference_datastructures_problem.problem_store(problem);
	    inference_datastructures_problem_store.remove_problem_store_problem(store, problem);
	    final SubLObject store2 = inference_datastructures_problem.problem_store(problem);
	    final SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store2);
	    if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
		final SubLObject idx_$10 = idx;
		if (NIL == id_index_dense_objects_empty_p(idx_$10, $SKIP)) {
		    final SubLObject vector_var = id_index_dense_objects(idx_$10);
		    final SubLObject backwardP_var = NIL;
		    SubLObject length;
		    SubLObject v_iteration;
		    SubLObject id;
		    SubLObject inference;
		    SubLObject inference_var;
		    SubLObject set_var;
		    SubLObject set_contents_var2;
		    SubLObject basis_object2;
		    SubLObject state2;
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
				set_contents_var2 = set.do_set_internal(set_var);
				for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2,
					state2); state2 = set_contents.do_set_contents_update_state(state2)) {
				    strategy = set_contents.do_set_contents_next(basis_object2, state2);
				    if (NIL != set_contents.do_set_contents_element_validP(state2, strategy)) {
					inference_datastructures_strategy.remove_strategy_problem(strategy, problem);
				    }
				}
			    }
			}
		    }
		}
		final SubLObject idx_$11 = idx;
		if (NIL == id_index_sparse_objects_empty_p(idx_$11)) {
		    final SubLObject cdohash_table = id_index_sparse_objects(idx_$11);
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
				final SubLObject set_contents_var3 = set.do_set_internal(set_var2);
				SubLObject basis_object3;
				SubLObject state3;
				SubLObject strategy2;
				for (basis_object3 = set_contents.do_set_contents_basis_object(set_contents_var3), state3 = NIL, state3 = set_contents.do_set_contents_initial_state(basis_object3, set_contents_var3); NIL == set_contents.do_set_contents_doneP(basis_object3,
					state3); state3 = set_contents.do_set_contents_update_state(state3)) {
				    strategy2 = set_contents.do_set_contents_next(basis_object3, state3);
				    if (NIL != set_contents.do_set_contents_element_validP(state3, strategy2)) {
					inference_datastructures_strategy.remove_strategy_problem(strategy2, problem);
				    }
				}
			    }
			}
		    } finally {
			releaseEntrySetIterator(cdohash_iterator);
		    }
		}
	    }
	    return inference_datastructures_problem.destroy_problem_int(problem);
	}
	return NIL;
    }

    // 
    // public static final SubLObject destroy_problem_alt(SubLObject problem) {
    // if (NIL != valid_problem_p(problem)) {
    // note_problem_invalid(problem);
    // {
    // SubLObject set_contents_var = problem_argument_links(problem);
    // SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
    // SubLObject state = NIL;
    // for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
    // {
    // SubLObject argument_link = set_contents.do_set_contents_next(basis_object, state);
    // if (NIL != set_contents.do_set_contents_element_validP(state, argument_link)) {
    // if (NIL != inference_datastructures_problem_link.valid_problem_link_p(argument_link)) {
    // inference_datastructures_problem_link.destroy_problem_link(argument_link);
    // }
    // }
    // }
    // }
    // }
    // {
    // SubLObject set_contents_var = problem_dependent_links(problem);
    // SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
    // SubLObject state = NIL;
    // for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
    // {
    // SubLObject dependent_link = set_contents.do_set_contents_next(basis_object, state);
    // if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
    // if (NIL != inference_worker_restriction.restriction_link_p(dependent_link)) {
    // inference_worker_join_ordered.note_all_triggering_proofs_processed(dependent_link);
    // }
    // if (NIL != inference_datastructures_problem_link.valid_problem_link_p(dependent_link)) {
    // inference_datastructures_problem_link.destroy_problem_link(dependent_link);
    // }
    // }
    // }
    // }
    // }
    // {
    // SubLObject store = problem_store(problem);
    // inference_datastructures_problem_store.remove_problem_store_problem(store, problem);
    // }
    // {
    // SubLObject prob = problem;
    // SubLObject store = problem_store(prob);
    // SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
    // if (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {
    // {
    // SubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);
    // SubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);
    // SubLObject inference = NIL;
    // while (NIL != id) {
    // inference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);
    // if (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {
    // if (NIL != problem_relevant_to_inferenceP(prob, inference)) {
    // {
    // SubLObject inference_var = inference;
    // SubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));
    // SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
    // SubLObject state = NIL;
    // for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
    // {
    // SubLObject strategy = set_contents.do_set_contents_next(basis_object, state);
    // if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
    // inference_datastructures_strategy.remove_strategy_problem(strategy, problem);
    // }
    // }
    // }
    // }
    // }
    // }
    // id = id_index.do_id_index_next_id(idx, NIL, id, state_var);
    // state_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);
    // }
    // }
    // }
    // }
    // return destroy_problem_int(problem);
    // }
    // return NIL;
    // }
    @LispMethod(comment = "public static final SubLObject destroy_problem_alt(SubLObject problem) {\nif (NIL != valid_problem_p(problem)) {\nnote_problem_invalid(problem);\nSubLObject set_contents_var = problem_argument_links(problem);\nSubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);\nSubLObject state = NIL;\nfor (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {\nSubLObject argument_link = set_contents.do_set_contents_next(basis_object, state);\nif (NIL != set_contents.do_set_contents_element_validP(state, argument_link)) {\nif (NIL != inference_datastructures_problem_link.valid_problem_link_p(argument_link)) {\ninference_datastructures_problem_link.destroy_problem_link(argument_link);\n}\nSubLObject set_contents_var = problem_dependent_links(problem);\nSubLObject dependent_link = set_contents.do_set_contents_next(basis_object, state);\nif (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {\nif (NIL != inference_worker_restriction.restriction_link_p(dependent_link)) {\ninference_worker_join_ordered.note_all_triggering_proofs_processed(dependent_link);\nif (NIL != inference_datastructures_problem_link.valid_problem_link_p(dependent_link)) {\ninference_datastructures_problem_link.destroy_problem_link(dependent_link);\nSubLObject store = problem_store(problem);\ninference_datastructures_problem_store.remove_problem_store_problem(store, problem);\nSubLObject prob = problem;\nSubLObject store = problem_store(prob);\nSubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);\nif (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {\nSubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);\nSubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);\nSubLObject inference = NIL;\nwhile (NIL != id) {\ninference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);\nif (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {\nif (NIL != problem_relevant_to_inferenceP(prob, inference)) {\nSubLObject inference_var = inference;\nSubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));\nSubLObject strategy = set_contents.do_set_contents_next(basis_object, state);\nif (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {\ninference_datastructures_strategy.remove_strategy_problem(strategy, problem);\nid = id_index.do_id_index_next_id(idx, NIL, id, state_var);\nstate_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);\nreturn destroy_problem_int(problem);\nreturn NIL;")
    public static final SubLObject destroy_problem_int(final SubLObject problem) {
	dictionary_contents.clear_dictionary_contents(inference_datastructures_problem.prob_argument_link_bindings_index(problem));
	inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, $FREE);
	dictionary_contents.clear_dictionary_contents(inference_datastructures_problem.prob_proof_bindings_index(problem));
	inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, $FREE);
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    inference_datastructures_tactic.destroy_problem_tactic(tactic);
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	inference_datastructures_problem._csetf_prob_tactics(problem, $FREE);
	set_contents.clear_set_contents(inference_datastructures_problem.prob_dependent_links(problem));
	inference_datastructures_problem._csetf_prob_dependent_links(problem, $FREE);
	set_contents.clear_set_contents(inference_datastructures_problem.prob_argument_links(problem));
	inference_datastructures_problem._csetf_prob_argument_links(problem, $FREE);
	inference_datastructures_problem._csetf_prob_query(problem, $FREE);
	inference_datastructures_problem._csetf_prob_free_hl_vars(problem, $FREE);
	inference_datastructures_problem._csetf_prob_store(problem, $FREE);
	inference_datastructures_problem._csetf_prob_memoization_state(problem, $FREE);
	return NIL;
    }

    public static final SubLObject destroy_problem_int_alt(SubLObject problem) {
	dictionary_contents.clear_dictionary_contents(inference_datastructures_problem.prob_argument_link_bindings_index(problem));
	inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, $FREE);
	dictionary_contents.clear_dictionary_contents(inference_datastructures_problem.prob_proof_bindings_index(problem));
	inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, $FREE);
	{
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		inference_datastructures_tactic.destroy_problem_tactic(tactic);
	    }
	    inference_datastructures_problem._csetf_prob_tactics(problem, $FREE);
	}
	set_contents.clear_set_contents(inference_datastructures_problem.prob_dependent_links(problem));
	inference_datastructures_problem._csetf_prob_dependent_links(problem, $FREE);
	set_contents.clear_set_contents(inference_datastructures_problem.prob_argument_links(problem));
	inference_datastructures_problem._csetf_prob_argument_links(problem, $FREE);
	inference_datastructures_problem._csetf_prob_query(problem, $FREE);
	inference_datastructures_problem._csetf_prob_store(problem, $FREE);
	return NIL;
    }

    public static final SubLObject destroy_problem_store_problem(final SubLObject problem) {
	if (NIL != inference_datastructures_problem.valid_problem_p(problem)) {
	    inference_datastructures_problem.note_problem_invalid(problem);
	    return inference_datastructures_problem.destroy_problem_int(problem);
	}
	return NIL;
    }

    public static final SubLObject destroy_problem_store_problem_alt(SubLObject problem) {
	if (NIL != inference_datastructures_problem.valid_problem_p(problem)) {
	    inference_datastructures_problem.note_problem_invalid(problem);
	    return inference_datastructures_problem.destroy_problem_int(problem);
	}
	return NIL;
    }

    public static final SubLObject disjunctive_problem_p(final SubLObject v_object) {
	return inference_datastructures_problem.multi_clause_problem_p(v_object);
    }

    public static final SubLObject disjunctive_problem_p_alt(SubLObject v_object) {
	return inference_datastructures_problem.multi_clause_problem_p(v_object);
    }

    public static final SubLObject do_problem_active_inferences(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list151);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject inference_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list151);
	inference_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list151);
	problem = current.first();
	current = current.rest();
	if (NIL == current) {
	    final SubLObject body;
	    current = body = temp;
	    final SubLObject strategy = inference_datastructures_problem.$sym152$STRATEGY;
	    return list(inference_datastructures_problem.DO_PROBLEM_ACTIVE_STRATEGIES, list(strategy, problem), listS(CLET, list(list(inference_var, list(inference_datastructures_problem.STRATEGY_INFERENCE, strategy))), append(body, NIL)));
	}
	cdestructuring_bind_error(datum, inference_datastructures_problem.$list151);
	return NIL;
    }

    /**
     *
     *
     * @unknown INFERENCE-VAR inference-p; the inferences of PROBLEM's active strategies.
     * @see do-problem-active-strategies
     */
    @LispMethod(comment = "@unknown INFERENCE-VAR inference-p; the inferences of PROBLEM\'s active strategies.\r\n@see do-problem-active-strategies")
    public static final SubLObject do_problem_active_inferences_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list127);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject inference_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list127);
		    inference_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list127);
		    problem = current.first();
		    current = current.rest();
		    if (NIL == current) {
			current = temp;
			{
			    SubLObject body = current;
			    SubLObject strategy = inference_datastructures_problem.$sym128$STRATEGY;
			    return list(inference_datastructures_problem.DO_PROBLEM_ACTIVE_STRATEGIES, list(strategy, problem), listS(CLET, list(list(inference_var, list(inference_datastructures_problem.STRATEGY_INFERENCE, strategy))), append(body, NIL)));
			}
		    } else {
			cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list127);
		    }
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject do_problem_active_strategies(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list161);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject strategy_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list161);
	strategy_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list161);
	problem = current.first();
	current = current.rest();
	if (NIL == current) {
	    final SubLObject body;
	    current = body = temp;
	    final SubLObject store = inference_datastructures_problem.$sym162$STORE;
	    final SubLObject prob = inference_datastructures_problem.$sym163$PROB;
	    return list(CLET, list(list(prob, problem), list(store, list(inference_datastructures_problem.PROBLEM_STORE, prob))),
		    list(inference_datastructures_problem.DO_PROBLEM_STORE_STRATEGIES, list(strategy_var, store), listS(PWHEN, list(inference_datastructures_problem.$sym165$PROBLEM_ACTIVE_IN_STRATEGY_, prob, strategy_var), append(body, NIL))));
	}
	cdestructuring_bind_error(datum, inference_datastructures_problem.$list161);
	return NIL;
    }

    /**
     *
     *
     * @unknown STRATEGY-VAR strategy-p; the strategies in which PROBLEM is currently active.
     */
    @LispMethod(comment = "@unknown STRATEGY-VAR strategy-p; the strategies in which PROBLEM is currently active.")
    public static final SubLObject do_problem_active_strategies_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list137);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject strategy_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list137);
		    strategy_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list137);
		    problem = current.first();
		    current = current.rest();
		    if (NIL == current) {
			current = temp;
			{
			    SubLObject body = current;
			    SubLObject store = inference_datastructures_problem.$sym138$STORE;
			    SubLObject prob = inference_datastructures_problem.$sym139$PROB;
			    return list(CLET, list(list(prob, problem), list(store, list(inference_datastructures_problem.PROBLEM_STORE, prob))),
				    list(inference_datastructures_problem.DO_PROBLEM_STORE_STRATEGIES, list(strategy_var, store), listS(PWHEN, list(inference_datastructures_problem.$sym141$PROBLEM_ACTIVE_IN_STRATEGY_, prob, strategy_var), append(body, NIL))));
			}
		    } else {
			cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list137);
		    }
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject do_problem_dependent_link_interpretations(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list124);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject link_var = NIL;
	SubLObject mapped_problem_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list124);
	link_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list124);
	mapped_problem_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list124);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$4 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list124);
	    current_$4 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list124);
	    if (NIL == member(current_$4, inference_datastructures_problem.$list125, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$4 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list124);
	}
	final SubLObject type_tail = property_list_member($TYPE, current);
	final SubLObject type = (NIL != type_tail) ? cadr(type_tail) : NIL;
	final SubLObject openP_tail = property_list_member($OPEN_, current);
	final SubLObject openP = (NIL != openP_tail) ? cadr(openP_tail) : NIL;
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	final SubLObject problem_var = inference_datastructures_problem.$sym127$PROBLEM_VAR;
	return list(CLET, list(list(problem_var, problem)), list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link_var, problem, $TYPE, type, $DONE, done), list(inference_datastructures_problem.DO_PROBLEM_LINK_SUPPORTING_MAPPED_PROBLEMS,
		list(mapped_problem_var, link_var, $OPEN_, openP, $DONE, done), listS(PWHEN, list(EQ, problem_var, list(inference_datastructures_problem.MAPPED_PROBLEM_PROBLEM, mapped_problem_var)), append(body, NIL)))));
    }

    /**
     * Like @xref do-problem-dependent-links, except also binds MAPPED-PROBLEM-VAR
     * to each supporting mapped problem of LINK-VAR whose problem is equal to PROBLEM.
     *
     * @see do-problem-link-supporting-mapped-problem-interpretations
     */
    @LispMethod(comment = "Like @xref do-problem-dependent-links, except also binds MAPPED-PROBLEM-VAR\r\nto each supporting mapped problem of LINK-VAR whose problem is equal to PROBLEM.\r\n\r\n@see do-problem-link-supporting-mapped-problem-interpretations\nLike @xref do-problem-dependent-links, except also binds MAPPED-PROBLEM-VAR\nto each supporting mapped problem of LINK-VAR whose problem is equal to PROBLEM.")
    public static final SubLObject do_problem_dependent_link_interpretations_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list100);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject link_var = NIL;
		    SubLObject mapped_problem_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list100);
		    link_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list100);
		    mapped_problem_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list100);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_4 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list100);
			    current_4 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list100);
			    if (NIL == member(current_4, inference_datastructures_problem.alt_list101, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_4 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list100);
			}
			{
			    SubLObject type_tail = property_list_member($TYPE, current);
			    SubLObject type = (NIL != type_tail) ? ((SubLObject) (cadr(type_tail))) : NIL;
			    SubLObject openP_tail = property_list_member($OPEN_, current);
			    SubLObject openP = (NIL != openP_tail) ? ((SubLObject) (cadr(openP_tail))) : NIL;
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				SubLObject problem_var = inference_datastructures_problem.$sym103$PROBLEM_VAR;
				return list(CLET, list(list(problem_var, problem)), list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link_var, problem, $TYPE, type, $DONE, done), list(inference_datastructures_problem.DO_PROBLEM_LINK_SUPPORTING_MAPPED_PROBLEMS,
					list(mapped_problem_var, link_var, $OPEN_, openP, $DONE, done), listS(PWHEN, list(EQ, problem_var, list(inference_datastructures_problem.MAPPED_PROBLEM_PROBLEM, mapped_problem_var)), append(body, NIL)))));
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_dependent_links(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list118);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject link_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list118);
	link_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list118);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$3 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list118);
	    current_$3 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list118);
	    if (NIL == member(current_$3, inference_datastructures_problem.$list119, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$3 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list118);
	}
	final SubLObject type_tail = property_list_member($TYPE, current);
	final SubLObject type = (NIL != type_tail) ? cadr(type_tail) : NIL;
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	if (NIL == type) {
	    return listS(DO_SET_CONTENTS, list(link_var, list(inference_datastructures_problem.PROBLEM_DEPENDENT_LINKS, problem), $DONE, done), append(body, NIL));
	}
	return list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link_var, problem, $DONE, done), listS(PWHEN, list(inference_datastructures_problem.$sym123$PROBLEM_LINK_HAS_TYPE_, link_var, type), append(body, NIL)));
    }

    public static final SubLObject do_problem_dependent_links_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list94);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject link_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list94);
		    link_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list94);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_3 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list94);
			    current_3 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list94);
			    if (NIL == member(current_3, inference_datastructures_problem.alt_list95, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_3 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list94);
			}
			{
			    SubLObject type_tail = property_list_member($TYPE, current);
			    SubLObject type = (NIL != type_tail) ? ((SubLObject) (cadr(type_tail))) : NIL;
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				if (NIL == type) {
				    return listS(DO_SET_CONTENTS, list(link_var, list(inference_datastructures_problem.PROBLEM_DEPENDENT_LINKS, problem), $DONE, done), append(body, NIL));
				} else {
				    return list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link_var, problem, $DONE, done), listS(PWHEN, list(inference_datastructures_problem.$sym99$PROBLEM_LINK_HAS_TYPE_, link_var, type), append(body, NIL)));
				}
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_literals(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list71);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject asent_var = NIL;
	SubLObject mt_var = NIL;
	SubLObject sense_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list71);
	asent_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list71);
	mt_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list71);
	sense_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list71);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$1 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list71);
	    current_$1 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list71);
	    if (NIL == member(current_$1, inference_datastructures_problem.$list72, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$1 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list71);
	}
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	return listS(inference_datastructures_problem.DO_PROBLEM_QUERY_LITERALS, list(asent_var, mt_var, sense_var, list(inference_datastructures_problem.PROBLEM_QUERY, problem), $DONE, done), append(body, NIL));
    }

    public static final SubLObject do_problem_literals_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list47);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject asent_var = NIL;
		    SubLObject mt_var = NIL;
		    SubLObject sense_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list47);
		    asent_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list47);
		    mt_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list47);
		    sense_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list47);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_1 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list47);
			    current_1 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list47);
			    if (NIL == member(current_1, inference_datastructures_problem.alt_list48, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_1 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list47);
			}
			{
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				return listS(inference_datastructures_problem.DO_PROBLEM_QUERY_LITERALS, list(asent_var, mt_var, sense_var, list(inference_datastructures_problem.PROBLEM_QUERY, problem), $DONE, done), append(body, NIL));
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_proofs(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list140);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject proof_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list140);
	proof_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list140);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$6 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list140);
	    current_$6 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list140);
	    if (NIL == member(current_$6, inference_datastructures_problem.$list141, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$6 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list140);
	}
	final SubLObject proof_status_tail = property_list_member($PROOF_STATUS, current);
	final SubLObject proof_status = (NIL != proof_status_tail) ? cadr(proof_status_tail) : NIL;
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	final SubLObject proof_list = inference_datastructures_problem.$sym143$PROOF_LIST;
	final SubLObject v_bindings = inference_datastructures_problem.$sym144$BINDINGS;
	if (NIL == proof_status) {
	    return list(inference_datastructures_problem.DO_DICTIONARY_CONTENTS, list(v_bindings, proof_list, list(inference_datastructures_problem.PROBLEM_PROOF_BINDINGS_INDEX, problem), $DONE, done), list(IGNORE, v_bindings),
		    listS(DO_LIST, list(proof_var, proof_list, $DONE, done), append(body, NIL)));
	}
	final SubLObject status_var = inference_datastructures_problem.$sym148$STATUS_VAR;
	return list(CLET, list(list(status_var, proof_status)), list(inference_datastructures_problem.DO_PROBLEM_PROOFS, list(proof_var, problem, $DONE, done), listS(PWHEN, list(inference_datastructures_problem.$sym150$PROOF_HAS_STATUS_, proof_var, status_var), append(body, NIL))));
    }

    public static final SubLObject do_problem_proofs_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list116);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject proof_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list116);
		    proof_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list116);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_6 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list116);
			    current_6 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list116);
			    if (NIL == member(current_6, inference_datastructures_problem.alt_list117, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_6 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list116);
			}
			{
			    SubLObject proof_status_tail = property_list_member($PROOF_STATUS, current);
			    SubLObject proof_status = (NIL != proof_status_tail) ? ((SubLObject) (cadr(proof_status_tail))) : NIL;
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				SubLObject proof_list = inference_datastructures_problem.$sym119$PROOF_LIST;
				SubLObject v_bindings = inference_datastructures_problem.$sym120$BINDINGS;
				if (NIL == proof_status) {
				    return list(inference_datastructures_problem.DO_DICTIONARY_CONTENTS, list(v_bindings, proof_list, list(inference_datastructures_problem.PROBLEM_PROOF_BINDINGS_INDEX, problem), $DONE, done), list(IGNORE, v_bindings),
					    listS(DO_LIST, list(proof_var, proof_list, $DONE, done), append(body, NIL)));
				} else {
				    {
					SubLObject status_var = inference_datastructures_problem.$sym124$STATUS_VAR;
					return list(CLET, list(list(status_var, proof_status)),
						list(inference_datastructures_problem.DO_PROBLEM_PROOFS, list(proof_var, problem, $DONE, done), listS(PWHEN, list(inference_datastructures_problem.$sym126$PROOF_HAS_STATUS_, proof_var, status_var), append(body, NIL))));
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_relevant_inferences(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list155);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject inference_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list155);
	inference_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list155);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$7 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list155);
	    current_$7 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list155);
	    if (NIL == member(current_$7, inference_datastructures_problem.$list72, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$7 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list155);
	}
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	final SubLObject store = inference_datastructures_problem.$sym156$STORE;
	final SubLObject prob = inference_datastructures_problem.$sym157$PROB;
	return list(CLET, list(list(prob, problem), list(store, list(inference_datastructures_problem.PROBLEM_STORE, prob))),
		list(inference_datastructures_problem.DO_PROBLEM_STORE_INFERENCES, list(inference_var, store, $DONE, done), listS(PWHEN, list(inference_datastructures_problem.$sym160$PROBLEM_RELEVANT_TO_INFERENCE_, prob, inference_var), append(body, NIL))));
    }

    /**
     *
     *
     * @unknown INFERENCE-VAR inference-p; the inferences in which PROBLEM could potentially
    impact the root proofs for INFERENCE-VAR.
     */
    @LispMethod(comment = "@unknown INFERENCE-VAR inference-p; the inferences in which PROBLEM could potentially\r\nimpact the root proofs for INFERENCE-VAR.")
    public static final SubLObject do_problem_relevant_inferences_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list131);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject inference_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list131);
		    inference_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list131);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_7 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list131);
			    current_7 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list131);
			    if (NIL == member(current_7, inference_datastructures_problem.alt_list48, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_7 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list131);
			}
			{
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				SubLObject store = inference_datastructures_problem.$sym132$STORE;
				SubLObject prob = inference_datastructures_problem.$sym133$PROB;
				return list(CLET, list(list(prob, problem), list(store, list(inference_datastructures_problem.PROBLEM_STORE, prob))),
					list(inference_datastructures_problem.DO_PROBLEM_STORE_INFERENCES, list(inference_var, store, $DONE, done), listS(PWHEN, list(inference_datastructures_problem.$sym136$PROBLEM_RELEVANT_TO_INFERENCE_, prob, inference_var), append(body, NIL))));
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_relevant_strategic_contexts(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list170);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject strategic_context_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list170);
	strategic_context_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list170);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$9 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list170);
	    current_$9 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list170);
	    if (NIL == member(current_$9, inference_datastructures_problem.$list72, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$9 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list170);
	}
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	return list(PROGN, listS(CLET, list(bq_cons(strategic_context_var, inference_datastructures_problem.$list172)), append(body, NIL)), listS(inference_datastructures_problem.DO_PROBLEM_RELEVANT_STRATEGIES, list(strategic_context_var, problem, $DONE, done), append(body, NIL)));
    }

    public static final SubLObject do_problem_relevant_strategic_contexts_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list146);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject strategic_context_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list146);
		    strategic_context_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list146);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_9 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list146);
			    current_9 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list146);
			    if (NIL == member(current_9, inference_datastructures_problem.alt_list48, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_9 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list146);
			}
			{
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				return list(PROGN, listS(CLET, list(bq_cons(strategic_context_var, inference_datastructures_problem.alt_list148)), append(body, NIL)),
					listS(inference_datastructures_problem.DO_PROBLEM_RELEVANT_STRATEGIES, list(strategic_context_var, problem, $DONE, done), append(body, NIL)));
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_relevant_strategies(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list166);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject strategy_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list166);
	strategy_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list166);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$8 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list166);
	    current_$8 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list166);
	    if (NIL == member(current_$8, inference_datastructures_problem.$list72, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$8 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list166);
	}
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	final SubLObject inference = inference_datastructures_problem.$sym167$INFERENCE;
	return list(inference_datastructures_problem.DO_PROBLEM_RELEVANT_INFERENCES, list(inference, problem, $DONE, done), listS(inference_datastructures_problem.DO_INFERENCE_STRATEGIES, list(strategy_var, inference), append(body, NIL)));
    }

    /**
     *
     *
     * @unknown STRATEGY-VAR strategy-p; all strategies of PROBLEM's relevant inferences.
     * @see do-problem-relevant-inferences
     */
    @LispMethod(comment = "@unknown STRATEGY-VAR strategy-p; all strategies of PROBLEM\'s relevant inferences.\r\n@see do-problem-relevant-inferences")
    public static final SubLObject do_problem_relevant_strategies_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list142);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject strategy_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list142);
		    strategy_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list142);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_8 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list142);
			    current_8 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list142);
			    if (NIL == member(current_8, inference_datastructures_problem.alt_list48, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_8 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list142);
			}
			{
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				SubLObject inference = inference_datastructures_problem.$sym143$INFERENCE;
				return list(inference_datastructures_problem.DO_PROBLEM_RELEVANT_INFERENCES, list(inference, problem, $DONE, done), listS(inference_datastructures_problem.DO_INFERENCE_STRATEGIES, list(strategy_var, inference), append(body, NIL)));
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_supported_inferences(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list134);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject supported_inference_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list134);
	supported_inference_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list134);
	problem = current.first();
	current = current.rest();
	if (NIL == current) {
	    final SubLObject body;
	    current = body = temp;
	    final SubLObject link = inference_datastructures_problem.$sym135$LINK;
	    return list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link, problem), list(CLET, list(list(supported_inference_var, list(inference_datastructures_problem.PROBLEM_LINK_SUPPORTED_INFERENCE, link))), listS(PWHEN, supported_inference_var, append(body, NIL))));
	}
	cdestructuring_bind_error(datum, inference_datastructures_problem.$list134);
	return NIL;
    }

    /**
     * walk up
     */
    @LispMethod(comment = "walk up")
    public static final SubLObject do_problem_supported_inferences_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list110);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject supported_inference_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list110);
		    supported_inference_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list110);
		    problem = current.first();
		    current = current.rest();
		    if (NIL == current) {
			current = temp;
			{
			    SubLObject body = current;
			    SubLObject link = inference_datastructures_problem.$sym111$LINK;
			    return list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link, problem),
				    list(CLET, list(list(supported_inference_var, list(inference_datastructures_problem.PROBLEM_LINK_SUPPORTED_INFERENCE, link))), listS(PWHEN, supported_inference_var, append(body, NIL))));
			}
		    } else {
			cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list110);
		    }
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject do_problem_supported_problems(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list131);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject supported_problem_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list131);
	supported_problem_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list131);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$5 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list131);
	    current_$5 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list131);
	    if (NIL == member(current_$5, inference_datastructures_problem.$list72, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$5 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list131);
	}
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	final SubLObject link = inference_datastructures_problem.$sym132$LINK;
	return list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link, problem, $DONE, done), list(CLET, list(list(supported_problem_var, list(inference_datastructures_problem.PROBLEM_LINK_SUPPORTED_PROBLEM, link))), listS(PWHEN, supported_problem_var, append(body, NIL))));
    }

    /**
     * walk up, may bind SUPPORTED-PROBLEM-VAR to duplicate problems
     */
    @LispMethod(comment = "walk up, may bind SUPPORTED-PROBLEM-VAR to duplicate problems")
    public static final SubLObject do_problem_supported_problems_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list107);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject supported_problem_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list107);
		    supported_problem_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list107);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_5 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list107);
			    current_5 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list107);
			    if (NIL == member(current_5, inference_datastructures_problem.alt_list48, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_5 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list107);
			}
			{
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				SubLObject link = inference_datastructures_problem.$sym108$LINK;
				return list(inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS, list(link, problem, $DONE, done),
					list(CLET, list(list(supported_problem_var, list(inference_datastructures_problem.PROBLEM_LINK_SUPPORTED_PROBLEM, link))), listS(PWHEN, supported_problem_var, append(body, NIL))));
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_supporting_problems(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list137);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject supporting_problem_var = NIL;
	SubLObject variable_map_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list137);
	supporting_problem_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list137);
	variable_map_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list137);
	problem = current.first();
	current = current.rest();
	if (NIL == current) {
	    final SubLObject body;
	    current = body = temp;
	    final SubLObject link = inference_datastructures_problem.$sym138$LINK;
	    return list(inference_datastructures_problem.DO_PROBLEM_ARGUMENT_LINKS, list(link, problem), listS(inference_datastructures_problem.DO_PROBLEM_LINK_SUPPORTING_PROBLEMS, list(supporting_problem_var, variable_map_var, link), append(body, NIL)));
	}
	cdestructuring_bind_error(datum, inference_datastructures_problem.$list137);
	return NIL;
    }

    /**
     * walk down
     */
    @LispMethod(comment = "walk down")
    public static final SubLObject do_problem_supporting_problems_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list113);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject supporting_problem_var = NIL;
		    SubLObject variable_map_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list113);
		    supporting_problem_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list113);
		    variable_map_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list113);
		    problem = current.first();
		    current = current.rest();
		    if (NIL == current) {
			current = temp;
			{
			    SubLObject body = current;
			    SubLObject link = inference_datastructures_problem.$sym114$LINK;
			    return list(inference_datastructures_problem.DO_PROBLEM_ARGUMENT_LINKS, list(link, problem), listS(inference_datastructures_problem.DO_PROBLEM_LINK_SUPPORTING_PROBLEMS, list(supporting_problem_var, variable_map_var, link), append(body, NIL)));
			}
		    } else {
			cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list113);
		    }
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject do_problem_tactics(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list77);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject tactic_var = NIL;
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list77);
	tactic_var = current.first();
	current = current.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list77);
	problem = current.first();
	current = current.rest();
	SubLObject allow_other_keys_p = NIL;
	SubLObject rest = current;
	SubLObject bad = NIL;
	SubLObject current_$2 = NIL;
	while (NIL != rest) {
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list77);
	    current_$2 = rest.first();
	    rest = rest.rest();
	    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.$list77);
	    if (NIL == member(current_$2, inference_datastructures_problem.$list78, UNPROVIDED, UNPROVIDED)) {
		bad = T;
	    }
	    if (current_$2 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
		allow_other_keys_p = rest.first();
	    }
	    rest = rest.rest();
	}
	if ((NIL != bad) && (NIL == allow_other_keys_p)) {
	    cdestructuring_bind_error(datum, inference_datastructures_problem.$list77);
	}
	final SubLObject done_tail = property_list_member($DONE, current);
	final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
	final SubLObject status_tail = property_list_member($STATUS, current);
	final SubLObject status = (NIL != status_tail) ? cadr(status_tail) : NIL;
	final SubLObject completeness_tail = property_list_member($COMPLETENESS, current);
	final SubLObject completeness = (NIL != completeness_tail) ? cadr(completeness_tail) : NIL;
	final SubLObject preference_level_tail = property_list_member($PREFERENCE_LEVEL, current);
	final SubLObject preference_level = (NIL != preference_level_tail) ? cadr(preference_level_tail) : NIL;
	final SubLObject hl_module_tail = property_list_member($HL_MODULE, current);
	final SubLObject hl_module = (NIL != hl_module_tail) ? cadr(hl_module_tail) : NIL;
	final SubLObject type_tail = property_list_member($TYPE, current);
	final SubLObject type = (NIL != type_tail) ? cadr(type_tail) : NIL;
	final SubLObject productivity_tail = property_list_member($PRODUCTIVITY, current);
	final SubLObject productivity = (NIL != productivity_tail) ? cadr(productivity_tail) : NIL;
	final SubLObject body;
	current = body = temp;
	SubLObject filter_forms = NIL;
	if (NIL != type) {
	    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_TYPE_MATCH, tactic_var, type), filter_forms);
	}
	if (NIL != status) {
	    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_STATUS_MATCH, tactic_var, status), filter_forms);
	}
	if (NIL != completeness) {
	    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_COMPLETENESS_MATCH, tactic_var, completeness), filter_forms);
	}
	if (NIL != preference_level) {
	    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_PREFERENCE_LEVEL_MATCH, tactic_var, preference_level), filter_forms);
	}
	if (NIL != hl_module) {
	    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_HL_MODULE_MATCH, tactic_var, hl_module), filter_forms);
	}
	if (NIL != productivity) {
	    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_PRODUCTIVITY_MATCH, tactic_var, productivity), filter_forms);
	}
	filter_forms = nreverse(filter_forms);
	if (NIL == filter_forms) {
	    return listS(DO_LIST, list(tactic_var, list(inference_datastructures_problem.PROBLEM_TACTICS, problem), $DONE, done), append(body, NIL));
	}
	return list(inference_datastructures_problem.DO_PROBLEM_TACTICS, list(tactic_var, problem, $DONE, done), listS(PWHEN, NIL != list_utilities.singletonP(filter_forms) ? filter_forms.first() : bq_cons(CAND, append(filter_forms, NIL)), append(body, NIL)));
    }

    public static final SubLObject do_problem_tactics_alt(SubLObject macroform, SubLObject environment) {
	{
	    SubLObject datum = macroform.rest();
	    SubLObject current = datum;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list53);
	    {
		SubLObject temp = current.rest();
		current = current.first();
		{
		    SubLObject tactic_var = NIL;
		    SubLObject problem = NIL;
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list53);
		    tactic_var = current.first();
		    current = current.rest();
		    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list53);
		    problem = current.first();
		    current = current.rest();
		    {
			SubLObject allow_other_keys_p = NIL;
			SubLObject rest = current;
			SubLObject bad = NIL;
			SubLObject current_2 = NIL;
			for (; NIL != rest;) {
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list53);
			    current_2 = rest.first();
			    rest = rest.rest();
			    destructuring_bind_must_consp(rest, datum, inference_datastructures_problem.alt_list53);
			    if (NIL == member(current_2, inference_datastructures_problem.alt_list54, UNPROVIDED, UNPROVIDED)) {
				bad = T;
			    }
			    if (current_2 == inference_datastructures_problem.$ALLOW_OTHER_KEYS) {
				allow_other_keys_p = rest.first();
			    }
			    rest = rest.rest();
			}
			if ((NIL != bad) && (NIL == allow_other_keys_p)) {
			    cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list53);
			}
			{
			    SubLObject done_tail = property_list_member($DONE, current);
			    SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
			    SubLObject status_tail = property_list_member($STATUS, current);
			    SubLObject status = (NIL != status_tail) ? ((SubLObject) (cadr(status_tail))) : NIL;
			    SubLObject completeness_tail = property_list_member($COMPLETENESS, current);
			    SubLObject completeness = (NIL != completeness_tail) ? ((SubLObject) (cadr(completeness_tail))) : NIL;
			    SubLObject preference_level_tail = property_list_member($PREFERENCE_LEVEL, current);
			    SubLObject preference_level = (NIL != preference_level_tail) ? ((SubLObject) (cadr(preference_level_tail))) : NIL;
			    SubLObject hl_module_tail = property_list_member($HL_MODULE, current);
			    SubLObject hl_module = (NIL != hl_module_tail) ? ((SubLObject) (cadr(hl_module_tail))) : NIL;
			    SubLObject type_tail = property_list_member($TYPE, current);
			    SubLObject type = (NIL != type_tail) ? ((SubLObject) (cadr(type_tail))) : NIL;
			    SubLObject productivity_tail = property_list_member($PRODUCTIVITY, current);
			    SubLObject productivity = (NIL != productivity_tail) ? ((SubLObject) (cadr(productivity_tail))) : NIL;
			    current = temp;
			    {
				SubLObject body = current;
				SubLObject filter_forms = NIL;
				if (NIL != type) {
				    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_TYPE_MATCH, tactic_var, type), filter_forms);
				}
				if (NIL != status) {
				    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_STATUS_MATCH, tactic_var, status), filter_forms);
				}
				if (NIL != completeness) {
				    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_COMPLETENESS_MATCH, tactic_var, completeness), filter_forms);
				}
				if (NIL != preference_level) {
				    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_PREFERENCE_LEVEL_MATCH, tactic_var, preference_level), filter_forms);
				}
				if (NIL != hl_module) {
				    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_HL_MODULE_MATCH, tactic_var, hl_module), filter_forms);
				}
				if (NIL != productivity) {
				    filter_forms = cons(list(inference_datastructures_problem.DO_PROBLEM_TACTICS_PRODUCTIVITY_MATCH, tactic_var, productivity), filter_forms);
				}
				filter_forms = nreverse(filter_forms);
				if (NIL == filter_forms) {
				    return listS(DO_LIST, list(tactic_var, list(inference_datastructures_problem.PROBLEM_TACTICS, problem), $DONE, done), append(body, NIL));
				} else {
				    return list(inference_datastructures_problem.DO_PROBLEM_TACTICS, list(tactic_var, problem, $DONE, done),
					    listS(PWHEN, NIL != list_utilities.singletonP(filter_forms) ? ((SubLObject) (filter_forms.first())) : bq_cons(CAND, append(filter_forms, NIL)), append(body, NIL)));
				}
			    }
			}
		    }
		}
	    }
	}
    }

    public static final SubLObject do_problem_tactics_completeness_match(final SubLObject tactic, final SubLObject completeness_spec) {
	if (NIL == completeness_spec) {
	    return T;
	}
	assert NIL != inference_datastructures_enumerated_types.completeness_p(completeness_spec) : "! inference_datastructures_enumerated_types.completeness_p(completeness_spec) "
		+ ("inference_datastructures_enumerated_types.completeness_p(completeness_spec) " + "CommonSymbols.NIL != inference_datastructures_enumerated_types.completeness_p(completeness_spec) ") + completeness_spec;
	return eq(completeness_spec, inference_datastructures_tactic.tactic_completeness(tactic));
    }

    public static final SubLObject do_problem_tactics_completeness_match_alt(SubLObject tactic, SubLObject completeness_spec) {
	if (NIL == completeness_spec) {
	    return T;
	} else {
	    SubLTrampolineFile.checkType(completeness_spec, inference_datastructures_problem.COMPLETENESS_P);
	    return eq(completeness_spec, inference_datastructures_tactic.tactic_completeness(tactic));
	}
    }

    public static final SubLObject do_problem_tactics_hl_module_match(final SubLObject tactic, final SubLObject hl_module_spec) {
	if (NIL == hl_module_spec) {
	    return T;
	}
	assert NIL != inference_modules.hl_module_p(hl_module_spec) : "! inference_modules.hl_module_p(hl_module_spec) " + ("inference_modules.hl_module_p(hl_module_spec) " + "CommonSymbols.NIL != inference_modules.hl_module_p(hl_module_spec) ") + hl_module_spec;
	return eq(hl_module_spec, inference_datastructures_tactic.tactic_hl_module(tactic));
    }

    public static final SubLObject do_problem_tactics_hl_module_match_alt(SubLObject tactic, SubLObject hl_module_spec) {
	if (NIL == hl_module_spec) {
	    return T;
	} else {
	    SubLTrampolineFile.checkType(hl_module_spec, HL_MODULE_P);
	    return eq(hl_module_spec, inference_datastructures_tactic.tactic_hl_module(tactic));
	}
    }

    public static final SubLObject do_problem_tactics_preference_level_match(final SubLObject tactic, final SubLObject preference_level_spec) {
	if (NIL == preference_level_spec) {
	    return T;
	}
	assert NIL != preference_modules.preference_level_p(preference_level_spec) : "! preference_modules.preference_level_p(preference_level_spec) "
		+ ("preference_modules.preference_level_p(preference_level_spec) " + "CommonSymbols.NIL != preference_modules.preference_level_p(preference_level_spec) ") + preference_level_spec;
	return eq(preference_level_spec, inference_datastructures_tactic.tactic_preference_level(tactic));
    }

    public static final SubLObject do_problem_tactics_preference_level_match_alt(SubLObject tactic, SubLObject preference_level_spec) {
	if (NIL == preference_level_spec) {
	    return T;
	} else {
	    SubLTrampolineFile.checkType(preference_level_spec, inference_datastructures_problem.PREFERENCE_LEVEL_P);
	    return eq(preference_level_spec, inference_datastructures_tactic.tactic_preference_level(tactic));
	}
    }

    public static final SubLObject do_problem_tactics_productivity_match(final SubLObject tactic, final SubLObject productivity_spec) {
	if (NIL == productivity_spec) {
	    return T;
	}
	assert NIL != inference_datastructures_enumerated_types.productivity_p(productivity_spec) : "! inference_datastructures_enumerated_types.productivity_p(productivity_spec) "
		+ ("inference_datastructures_enumerated_types.productivity_p(productivity_spec) " + "CommonSymbols.NIL != inference_datastructures_enumerated_types.productivity_p(productivity_spec) ") + productivity_spec;
	return inference_datastructures_enumerated_types.productivity_E(productivity_spec, inference_datastructures_tactic.tactic_productivity(tactic));
    }

    public static final SubLObject do_problem_tactics_productivity_match_alt(SubLObject tactic, SubLObject productivity_spec) {
	if (NIL == productivity_spec) {
	    return T;
	} else {
	    SubLTrampolineFile.checkType(productivity_spec, inference_datastructures_problem.PRODUCTIVITY_P);
	    return inference_datastructures_enumerated_types.productivity_E(productivity_spec, inference_datastructures_tactic.tactic_productivity(tactic));
	}
    }

    public static final SubLObject do_problem_tactics_status_match(final SubLObject tactic, final SubLObject status_spec) {
	return makeBoolean((NIL == status_spec) || (NIL != inference_datastructures_tactic.tactic_has_statusP(tactic, status_spec)));
    }

    public static final SubLObject do_problem_tactics_status_match_alt(SubLObject tactic, SubLObject status_spec) {
	return makeBoolean((NIL == status_spec) || (NIL != inference_datastructures_tactic.tactic_has_statusP(tactic, status_spec)));
    }

    public static final SubLObject do_problem_tactics_type_match(final SubLObject tactic, final SubLObject type_spec) {
	return inference_datastructures_problem.tactic_matches_type_specP(tactic, type_spec);
    }

    public static final SubLObject do_problem_tactics_type_match_alt(SubLObject tactic, SubLObject type_spec) {
	return inference_datastructures_problem.tactic_matches_type_specP(tactic, type_spec);
    }

    public static final SubLObject first_problem_active_strategy(final SubLObject problem) {
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject idx = inference_datastructures_problem_store.problem_store_strategy_id_index(store);
	if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
	    final SubLObject idx_$24 = idx;
	    if (NIL == id_index_dense_objects_empty_p(idx_$24, $SKIP)) {
		final SubLObject vector_var = id_index_dense_objects(idx_$24);
		final SubLObject backwardP_var = NIL;
		SubLObject length;
		SubLObject v_iteration;
		SubLObject id;
		SubLObject strategy;
		for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
		    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
		    strategy = aref(vector_var, id);
		    if ((NIL == id_index_tombstone_p(strategy)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
			if (NIL != id_index_tombstone_p(strategy)) {
			    strategy = $SKIP;
			}
			if (NIL != inference_datastructures_strategy.problem_active_in_strategyP(problem, strategy)) {
			    return strategy;
			}
		    }
		}
	    }
	    final SubLObject idx_$25 = idx;
	    if (NIL == id_index_sparse_objects_empty_p(idx_$25)) {
		final SubLObject cdohash_table = id_index_sparse_objects(idx_$25);
		SubLObject id2 = NIL;
		SubLObject strategy2 = NIL;
		final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
		try {
		    while (iteratorHasNext(cdohash_iterator)) {
			final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
			id2 = getEntryKey(cdohash_entry);
			strategy2 = getEntryValue(cdohash_entry);
			if (NIL != inference_datastructures_strategy.problem_active_in_strategyP(problem, strategy2)) {
			    return strategy2;
			}
		    }
		} finally {
		    releaseEntrySetIterator(cdohash_iterator);
		}
	    }
	}
	return NIL;
    }

    // 
    // public static final SubLObject first_problem_active_strategy_alt(SubLObject problem) {
    // {
    // SubLObject prob = problem;
    // SubLObject store = problem_store(prob);
    // SubLObject idx = inference_datastructures_problem_store.problem_store_strategy_id_index(store);
    // if (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {
    // {
    // SubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);
    // SubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);
    // SubLObject strategy = NIL;
    // while (NIL != id) {
    // strategy = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);
    // if (NIL != id_index.do_id_index_id_and_object_validP(id, strategy, $SKIP)) {
    // if (NIL != inference_datastructures_strategy.problem_active_in_strategyP(prob, strategy)) {
    // return strategy;
    // }
    // }
    // id = id_index.do_id_index_next_id(idx, NIL, id, state_var);
    // state_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);
    // }
    // }
    // }
    // }
    // return NIL;
    // }
    @LispMethod(comment = "public static final SubLObject first_problem_active_strategy_alt(SubLObject problem) {\nSubLObject prob = problem;\nSubLObject store = problem_store(prob);\nSubLObject idx = inference_datastructures_problem_store.problem_store_strategy_id_index(store);\nif (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {\nSubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);\nSubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);\nSubLObject strategy = NIL;\nwhile (NIL != id) {\nstrategy = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);\nif (NIL != id_index.do_id_index_id_and_object_validP(id, strategy, $SKIP)) {\nif (NIL != inference_datastructures_strategy.problem_active_in_strategyP(prob, strategy)) {\nreturn strategy;\n}\nid = id_index.do_id_index_next_id(idx, NIL, id, state_var);\nstate_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);\nreturn NIL;")
    public static final SubLObject first_problem_relevant_inference(final SubLObject problem) {
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
		for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
		    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
		    inference = aref(vector_var, id);
		    if ((NIL == id_index_tombstone_p(inference)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
			if (NIL != id_index_tombstone_p(inference)) {
			    inference = $SKIP;
			}
			if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference)) {
			    return inference;
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
			    return inference2;
			}
		    }
		} finally {
		    releaseEntrySetIterator(cdohash_iterator);
		}
	    }
	}
	return NIL;
    }

    // public static final SubLObject first_problem_relevant_inference_alt(SubLObject problem) {
    // {
    // SubLObject prob = problem;
    // SubLObject store = problem_store(prob);
    // SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
    // if (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {
    // {
    // SubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);
    // SubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);
    // SubLObject inference = NIL;
    // while (NIL != id) {
    // inference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);
    // if (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {
    // if (NIL != problem_relevant_to_inferenceP(prob, inference)) {
    // return inference;
    // }
    // }
    // id = id_index.do_id_index_next_id(idx, NIL, id, state_var);
    // state_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);
    // }
    // }
    // }
    // }
    // return NIL;
    // }
    @LispMethod(comment = "public static final SubLObject first_problem_relevant_inference_alt(SubLObject problem) {\nSubLObject prob = problem;\nSubLObject store = problem_store(prob);\nSubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);\nif (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {\nSubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);\nSubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);\nSubLObject inference = NIL;\nwhile (NIL != id) {\ninference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);\nif (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {\nif (NIL != problem_relevant_to_inferenceP(prob, inference)) {\nreturn inference;\n}\nid = id_index.do_id_index_next_id(idx, NIL, id, state_var);\nstate_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);\nreturn NIL;")
    public static final SubLObject generalized_tactic_type_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_enumerated_types.tactic_type_p(v_object)) || (NIL != list_utilities.member_eqP(v_object, inference_datastructures_problem.$generalized_tactic_types$.getGlobalValue())));
    }

    /**
     *
     *
     * @return booleanp; t iff OBJECT is a specification for some subset of tactics.
     */
    @LispMethod(comment = "@return booleanp; t iff OBJECT is a specification for some subset of tactics.")
    public static final SubLObject generalized_tactic_type_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_enumerated_types.tactic_type_p(v_object)) || (NIL != list_utilities.member_eqP(v_object, inference_datastructures_problem.$generalized_tactic_types$.getGlobalValue())));
    }

    public static final SubLObject index_problem_argument_link(final SubLObject problem, final SubLObject argument_link) {
	final SubLObject index = inference_datastructures_problem.prob_argument_link_bindings_index(problem);
	final SubLObject v_bindings = (NIL != inference_worker_removal.removal_link_p(argument_link)) ? inference_worker_removal.removal_link_bindings(argument_link)
		: NIL != inference_worker_restriction.restriction_link_p(argument_link) ? inference_worker_restriction.restriction_link_bindings(argument_link) : NIL;
	SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	existing = cons(argument_link, existing);
	inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, existing, symbol_function(EQUAL)));
	return problem;
    }

    /**
     * Indexes argument-link by bindings for fast lookup.  Used for removal and restriction links.
     */
    @LispMethod(comment = "Indexes argument-link by bindings for fast lookup.  Used for removal and restriction links.")
    public static final SubLObject index_problem_argument_link_alt(SubLObject problem, SubLObject argument_link) {
	{
	    SubLObject index = inference_datastructures_problem.prob_argument_link_bindings_index(problem);
	    SubLObject v_bindings = (NIL != inference_worker_removal.removal_link_p(argument_link)) ? ((SubLObject) (inference_worker_removal.removal_link_bindings(argument_link)))
		    : NIL != inference_worker_restriction.restriction_link_p(argument_link) ? ((SubLObject) (inference_worker_restriction.restriction_link_bindings(argument_link))) : NIL;
	    SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	    existing = cons(argument_link, existing);
	    inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, existing, symbol_function(EQUAL)));
	}
	return problem;
    }

    public static final SubLObject init_inference_datastructures_problem_file() {
	defconstant("*DTP-PROBLEM*", PROBLEM);
	deflexical("*EMPTY-CLAUSES*", SubLTrampolineFile.maybeDefault(inference_datastructures_problem.$empty_clauses$, inference_datastructures_problem.$empty_clauses$, () -> list(clauses.empty_clause())));
	deflexical("*GENERALIZED-TACTIC-TYPES*", inference_datastructures_problem.$list99);
	defvar("*TRANSFORMATION-DEPTH-COMPUTATION*", $COUNTERINTUITIVE);
	defparameter("*PROBLEM-MIN-TRANSFORMATION-DEPTH-FROM-SIGNATURE-ENABLED?*", T);
	deflexical("*MAX-PROBLEM-TACTICS*", inference_datastructures_problem.$int$10000);
	return NIL;
    }

    public static final SubLObject init_inference_datastructures_problem_file_alt() {
	defconstant("*DTP-PROBLEM*", PROBLEM);
	deflexical("*EMPTY-CLAUSES*", NIL != boundp(inference_datastructures_problem.$empty_clauses$) ? ((SubLObject) (inference_datastructures_problem.$empty_clauses$.getGlobalValue())) : list(clauses.empty_clause()));
	deflexical("*GENERALIZED-TACTIC-TYPES*", inference_datastructures_problem.alt_list75);
	defvar("*TRANSFORMATION-DEPTH-COMPUTATION*", $COUNTERINTUITIVE);
	defparameter("*PROBLEM-MIN-TRANSFORMATION-DEPTH-FROM-SIGNATURE-ENABLED?*", T);
	deflexical("*MAX-PROBLEM-TACTICS*", inference_datastructures_problem.$int$10000);
	return NIL;
    }

    public static final SubLObject ist_problem_p(final SubLObject v_object) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	return makeBoolean((NIL != inference_datastructures_problem.single_literal_problem_p(v_object))
		&& (inference_datastructures_problem.$$ist.eql(inference_datastructures_problem.single_literal_problem_predicate(v_object)) || ((NIL != within_normal_forward_inferenceP()) && (!mt_relevance_macros.$mt$.getDynamicValue(thread).eql(inference_datastructures_problem.$$InferencePSC)))));
    }

    public static final SubLObject ist_problem_p_alt(SubLObject v_object) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    return makeBoolean((NIL != inference_datastructures_problem.single_literal_problem_p(v_object))
		    && ((inference_datastructures_problem.$$ist == inference_datastructures_problem.single_literal_problem_predicate(v_object)) || ((NIL != within_normal_forward_inferenceP()) && (mt_relevance_macros.$mt$.getDynamicValue(thread) != inference_datastructures_problem.$$InferencePSC))));
	}
    }

    public static final SubLObject join_problem_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.conjunctive_problem_p(v_object)) && (NIL != inference_worker_split.all_literals_connected_by_shared_varsP(inference_datastructures_problem.problem_query(v_object).first())));
    }

    /**
     *
     *
     * @return booleanp; t iff OBJECT is a connected conjunctive problem.
     */
    @LispMethod(comment = "@return booleanp; t iff OBJECT is a connected conjunctive problem.")
    public static final SubLObject join_problem_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.conjunctive_problem_p(v_object)) && (NIL != inference_worker_split.all_literals_connected_by_shared_varsP(inference_datastructures_problem.problem_query(v_object).first())));
    }

    public static final SubLObject make_problem(SubLObject arglist) {
	if (arglist == UNPROVIDED) {
	    arglist = NIL;
	}
	final SubLObject v_new = new inference_datastructures_problem.$problem_native();
	SubLObject next;
	SubLObject current_arg;
	SubLObject current_value;
	SubLObject pcase_var;
	for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
	    current_arg = next.first();
	    current_value = cadr(next);
	    pcase_var = current_arg;
	    if (pcase_var.eql($SUID)) {
		inference_datastructures_problem._csetf_prob_suid(v_new, current_value);
	    } else if (pcase_var.eql($STORE)) {
		inference_datastructures_problem._csetf_prob_store(v_new, current_value);
	    } else if (pcase_var.eql($QUERY)) {
		inference_datastructures_problem._csetf_prob_query(v_new, current_value);
	    } else if (pcase_var.eql(inference_datastructures_problem.$FREE_HL_VARS)) {
		inference_datastructures_problem._csetf_prob_free_hl_vars(v_new, current_value);
	    } else if (pcase_var.eql($STATUS)) {
		inference_datastructures_problem._csetf_prob_status(v_new, current_value);
	    } else if (pcase_var.eql($DEPENDENT_LINKS)) {
		inference_datastructures_problem._csetf_prob_dependent_links(v_new, current_value);
	    } else if (pcase_var.eql($ARGUMENT_LINKS)) {
		inference_datastructures_problem._csetf_prob_argument_links(v_new, current_value);
	    } else if (pcase_var.eql($TACTICS)) {
		inference_datastructures_problem._csetf_prob_tactics(v_new, current_value);
	    } else if (pcase_var.eql(inference_datastructures_problem.$PROOF_BINDINGS_INDEX)) {
		inference_datastructures_problem._csetf_prob_proof_bindings_index(v_new, current_value);
	    } else if (pcase_var.eql(inference_datastructures_problem.$ARGUMENT_LINK_BINDINGS_INDEX)) {
		inference_datastructures_problem._csetf_prob_argument_link_bindings_index(v_new, current_value);
	    } else if (pcase_var.eql($BACKCHAIN_REQUIRED)) {
		inference_datastructures_problem._csetf_prob_backchain_required(v_new, current_value);
	    } else if (pcase_var.eql($MEMOIZATION_STATE)) {
		inference_datastructures_problem._csetf_prob_memoization_state(v_new, current_value);
	    } else {
		Errors.error(inference_datastructures_problem.$str46$Invalid_slot__S_for_construction_, current_arg);
	    }

	}
	return v_new;
    }

    public static final SubLObject make_problem_alt(SubLObject arglist) {
	if (arglist == UNPROVIDED) {
	    arglist = NIL;
	}
	{
	    SubLObject v_new = new inference_datastructures_problem.$problem_native();
	    SubLObject next = NIL;
	    for (next = arglist; NIL != next; next = cddr(next)) {
		{
		    SubLObject current_arg = next.first();
		    SubLObject current_value = cadr(next);
		    SubLObject pcase_var = current_arg;
		    if (pcase_var.eql($SUID)) {
			inference_datastructures_problem._csetf_prob_suid(v_new, current_value);
		    } else if (pcase_var.eql($STORE)) {
			inference_datastructures_problem._csetf_prob_store(v_new, current_value);
		    } else if (pcase_var.eql($QUERY)) {
			inference_datastructures_problem._csetf_prob_query(v_new, current_value);
		    } else if (pcase_var.eql($STATUS)) {
			inference_datastructures_problem._csetf_prob_status(v_new, current_value);
		    } else if (pcase_var.eql($DEPENDENT_LINKS)) {
			inference_datastructures_problem._csetf_prob_dependent_links(v_new, current_value);
		    } else if (pcase_var.eql($ARGUMENT_LINKS)) {
			inference_datastructures_problem._csetf_prob_argument_links(v_new, current_value);
		    } else if (pcase_var.eql($TACTICS)) {
			inference_datastructures_problem._csetf_prob_tactics(v_new, current_value);
		    } else if (pcase_var.eql(inference_datastructures_problem.$PROOF_BINDINGS_INDEX)) {
			inference_datastructures_problem._csetf_prob_proof_bindings_index(v_new, current_value);
		    } else if (pcase_var.eql(inference_datastructures_problem.$ARGUMENT_LINK_BINDINGS_INDEX)) {
			inference_datastructures_problem._csetf_prob_argument_link_bindings_index(v_new, current_value);
		    } else {
			Errors.error(inference_datastructures_problem.alt_str36$Invalid_slot__S_for_construction_, current_arg);
		    }

		}
	    }
	    return v_new;
	}
    }

    public static final SubLObject mt_asent_sense_from_single_literal_problem(final SubLObject problem) {
	return inference_datastructures_problem_query.mt_asent_sense_from_singleton_query(inference_datastructures_problem.problem_query(problem));
    }

    /**
     *
     *
     * @param problem
     * 		; single-literal-problem-p
     * @return 0 mt
     * @return 1 atomic-sentence-p
     * @return 2 sense-p
     */
    @LispMethod(comment = "@param problem\r\n\t\t; single-literal-problem-p\r\n@return 0 mt\r\n@return 1 atomic-sentence-p\r\n@return 2 sense-p")
    public static final SubLObject mt_asent_sense_from_single_literal_problem_alt(SubLObject problem) {
	return inference_datastructures_problem_query.mt_asent_sense_from_singleton_query(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject multi_clause_problem_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.problem_p(v_object)) && (NIL == inference_datastructures_problem.single_clause_problem_p(v_object)));
    }

    public static final SubLObject multi_clause_problem_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.problem_p(v_object)) && (NIL == inference_datastructures_problem.single_clause_problem_p(v_object)));
    }

    public static final SubLObject multi_clause_problem_query_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem_query.problem_query_p(v_object)) && (NIL == inference_datastructures_problem_query.single_clause_problem_query_p(v_object)));
    }

    public static final SubLObject multi_clause_problem_query_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem_query.problem_query_p(v_object)) && (NIL == inference_datastructures_problem_query.single_clause_problem_query_p(v_object)));
    }

    public static final SubLObject multi_literal_problem_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.single_clause_problem_p(v_object)) && (NIL == inference_datastructures_problem.single_literal_problem_p(v_object)));
    }

    public static final SubLObject multi_literal_problem_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.single_clause_problem_p(v_object)) && (NIL == inference_datastructures_problem.single_literal_problem_p(v_object)));
    }

    public static final SubLObject new_problem(final SubLObject store, final SubLObject query, SubLObject free_hl_vars) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	assert NIL != inference_datastructures_problem_store.problem_store_p(store) : "! inference_datastructures_problem_store.problem_store_p(store) "
		+ ("inference_datastructures_problem_store.problem_store_p(store) " + "CommonSymbols.NIL != inference_datastructures_problem_store.problem_store_p(store) ") + store;
	SubLTrampolineFile.enforceType(query, inference_datastructures_problem.CONTEXTUALIZED_DNF_CLAUSES_P);
	if (NIL == inference_datastructures_problem_store.problem_identity_depends_on_free_hl_varsP(store)) {
	    free_hl_vars = NIL;
	}
	leviathan.note_new_problem_created();
	if ((NIL != $inference_debugP$.getDynamicValue(thread)) && (NIL != inference_datastructures_problem_store.problem_store_crazy_max_problem_count_exactly_reachedP(store))) {
	    Errors.cerror(inference_datastructures_problem.$$$Ignore_the_crazy_problems, inference_datastructures_problem.$str62$Crazy_amount_of_problems___a__in_, inference_datastructures_problem_store.problem_store_problem_count(store), store);
	}
	final SubLObject problem = inference_datastructures_problem.make_problem(UNPROVIDED);
	final SubLObject suid = inference_datastructures_problem_store.problem_store_new_problem_id(store);
	inference_metrics.increment_problem_historical_count();
	if (NIL != inference_datastructures_problem_query.problem_query_has_single_literal_p(query)) {
	    inference_metrics.increment_single_literal_problem_historical_count();
	}
	inference_datastructures_problem._csetf_prob_suid(problem, suid);
	inference_datastructures_problem._csetf_prob_store(problem, store);
	inference_datastructures_problem._csetf_prob_query(problem, query);
	inference_datastructures_problem._csetf_prob_free_hl_vars(problem, free_hl_vars);
	inference_datastructures_problem.set_problem_status(problem, $NEW);
	inference_datastructures_problem._csetf_prob_argument_links(problem, set_contents.new_set_contents(ZERO_INTEGER, symbol_function(EQ)));
	inference_datastructures_problem._csetf_prob_dependent_links(problem, set_contents.new_set_contents(ZERO_INTEGER, symbol_function(EQ)));
	inference_datastructures_problem._csetf_prob_tactics(problem, NIL);
	inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.new_dictionary_contents(ZERO_INTEGER, symbol_function(EQUAL)));
	inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.new_dictionary_contents(ZERO_INTEGER, symbol_function(EQUAL)));
	inference_datastructures_problem._csetf_prob_backchain_required(problem, $UNDETERMINED);
	inference_datastructures_problem._csetf_prob_memoization_state(problem, memoization_state.new_memoization_state(inference_datastructures_problem.$$$problem_memoization_state, UNPROVIDED, UNPROVIDED, UNPROVIDED));
	inference_datastructures_problem_store.add_problem_store_problem(store, problem);
	inference_worker.note_problem_created(problem);
	return problem;
    }

    /**
     * Creates and canonicalizes a new problem.
     */
    @LispMethod(comment = "Creates and canonicalizes a new problem.")
    public static final SubLObject new_problem(SubLObject store, SubLObject query) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    SubLTrampolineFile.checkType(store, PROBLEM_STORE_P);
	    SubLTrampolineFile.checkType(query, inference_datastructures_problem.CONTEXTUALIZED_DNF_CLAUSES_P);
	    leviathan.note_new_problem_created();
	    if ((NIL != $inference_debugP$.getDynamicValue(thread)) && (NIL != inference_datastructures_problem_store.problem_store_crazy_max_problem_count_exactly_reachedP(store))) {
		Errors.cerror(inference_datastructures_problem.$$$Ignore_the_crazy_problems, inference_datastructures_problem.alt_str44$Crazy_amount_of_problems___a__in_, inference_datastructures_problem_store.problem_store_problem_count(store), store);
	    }
	    {
		SubLObject problem = inference_datastructures_problem.make_problem(UNPROVIDED);
		SubLObject suid = inference_datastructures_problem_store.problem_store_new_problem_id(store);
		inference_metrics.increment_problem_historical_count();
		if (NIL != inference_datastructures_problem_query.problem_query_has_single_literal_p(query)) {
		    inference_metrics.increment_single_literal_problem_historical_count();
		}
		inference_datastructures_problem._csetf_prob_suid(problem, suid);
		inference_datastructures_problem._csetf_prob_store(problem, store);
		inference_datastructures_problem._csetf_prob_query(problem, query);
		inference_datastructures_problem.set_problem_status(problem, $NEW);
		inference_datastructures_problem._csetf_prob_argument_links(problem, set_contents.new_set_contents(ZERO_INTEGER, symbol_function(EQ)));
		inference_datastructures_problem._csetf_prob_dependent_links(problem, set_contents.new_set_contents(ZERO_INTEGER, symbol_function(EQ)));
		inference_datastructures_problem._csetf_prob_tactics(problem, NIL);
		inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.new_dictionary_contents(ZERO_INTEGER, symbol_function(EQUAL)));
		inference_datastructures_problem._csetf_prob_argument_link_bindings_index(problem, dictionary_contents.new_dictionary_contents(ZERO_INTEGER, symbol_function(EQUAL)));
		inference_datastructures_problem_store.add_problem_store_problem(store, problem);
		inference_worker.note_problem_created(problem);
		return problem;
	    }
	}
    }

    public static final SubLObject note_problem_destructible(final SubLObject problem) {
	if (NIL == inference_datastructures_problem.problem_destructibleP(problem)) {
	    final SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
	    inference_datastructures_problem_store.problem_store_janitor_note_problem_destructible(janitor, problem);
	}
	return problem;
    }

    public static final SubLObject note_problem_destructible_alt(SubLObject problem) {
	if (NIL == inference_datastructures_problem.problem_destructibleP(problem)) {
	    {
		SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
		inference_datastructures_problem_store.problem_store_janitor_note_problem_destructible(janitor, problem);
	    }
	}
	return problem;
    }

    public static final SubLObject note_problem_indestructible(final SubLObject problem) {
	if (NIL == inference_datastructures_problem.problem_indestructibleP(problem)) {
	    final SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
	    inference_datastructures_problem_store.problem_store_janitor_note_problem_indestructible(janitor, problem);
	}
	return problem;
    }

    public static final SubLObject note_problem_indestructible_alt(SubLObject problem) {
	if (NIL == inference_datastructures_problem.problem_indestructibleP(problem)) {
	    {
		SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
		inference_datastructures_problem_store.problem_store_janitor_note_problem_indestructible(janitor, problem);
	    }
	}
	return problem;
    }

    public static final SubLObject note_problem_invalid(final SubLObject problem) {
	inference_datastructures_problem._csetf_prob_status(problem, $FREE);
	return problem;
    }

    public static final SubLObject note_problem_invalid_alt(SubLObject problem) {
	inference_datastructures_problem._csetf_prob_status(problem, $FREE);
	return problem;
    }

    public static final SubLObject note_problem_min_transformation_depth_signature(final SubLObject problem, final SubLObject inference, final SubLObject new_pqds) {
	final SubLObject old_pqds = inference_datastructures_problem.problem_min_transformation_depth_signature(problem, inference);
	final SubLObject updated_pqds = ($UNDETERMINED == old_pqds) ? new_pqds : inference_min_transformation_depth.pqds_merge(old_pqds, new_pqds);
	if (!old_pqds.equal(updated_pqds)) {
	    inference_datastructures_problem.set_problem_min_transformation_depth_signature(problem, inference, updated_pqds);
	    return T;
	}
	return NIL;
    }

    public static final SubLObject note_problem_min_transformation_depth_signature_alt(SubLObject problem, SubLObject inference, SubLObject new_pqds) {
	{
	    SubLObject old_pqds = inference_datastructures_problem.problem_min_transformation_depth_signature(problem, inference);
	    SubLObject updated_pqds = ($UNDETERMINED == old_pqds) ? ((SubLObject) (new_pqds)) : inference_min_transformation_depth.pqds_merge(old_pqds, new_pqds);
	    if (!old_pqds.equal(updated_pqds)) {
		inference_datastructures_problem.set_problem_min_transformation_depth_signature(problem, inference, updated_pqds);
		return T;
	    }
	}
	return NIL;
    }

    public static final SubLObject note_problem_potentially_processed(final SubLObject problem) {
	return inference_datastructures_problem_store.problem_store_note_problem_potentially_processed(inference_datastructures_problem.problem_store(problem), problem);
    }

    public static final SubLObject open_problem_p(final SubLObject problem) {
	return inference_datastructures_problem.open_problem_query_p(inference_datastructures_problem.problem_query(problem));
    }

    /**
     *
     *
     * @return booleanp; whether PROBLEM contains any variables
     */
    @LispMethod(comment = "@return booleanp; whether PROBLEM contains any variables")
    public static final SubLObject open_problem_p_alt(SubLObject problem) {
	return inference_datastructures_problem.open_problem_query_p(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject open_problem_query_p(final SubLObject query) {
	return makeBoolean(NIL == hl_ground_tree_p(query));
    }

    public static final SubLObject open_problem_query_p_alt(SubLObject query) {
	return makeBoolean(NIL == hl_ground_tree_p(query));
    }

    public static final SubLObject open_single_literal_problem_query_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem_query.single_literal_problem_query_p(v_object)) && (NIL == inference_datastructures_problem.closed_problem_query_p(v_object)));
    }

    public static final SubLObject open_single_literal_problem_query_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem_query.single_literal_problem_query_p(v_object)) && (NIL == inference_datastructures_problem.closed_problem_query_p(v_object)));
    }

    public static final SubLObject print_problem(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
	if (NIL != inference_datastructures_problem.problem_invalid_p(v_object)) {
	    format(stream, inference_datastructures_problem.$str53$_Invalid_PROBLEM__s_, inference_datastructures_problem.prob_suid(v_object));
	} else {
	    format(stream, inference_datastructures_problem.$str54$__a_PROBLEM__a__a__s_a_,
		    new SubLObject[] { inference_datastructures_problem.problem_status(v_object), inference_datastructures_problem_store.problem_store_suid(inference_datastructures_problem.problem_store(v_object)), inference_datastructures_problem.problem_suid(v_object),
			    inference_datastructures_problem.problem_query(v_object),
			    NIL != inference_datastructures_problem.problem_free_hl_vars(v_object)
				    ? cconcatenate(inference_datastructures_problem.$str55$_free_, format_nil.format_nil_a_no_copy(Mapping.mapcar(inference_datastructures_problem.VARIABLE_ID, inference_datastructures_problem.problem_free_hl_vars(v_object))))
				    : inference_datastructures_problem.$str57$ });
	}
	return NIL;
    }

    public static final SubLObject print_problem_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
	if (NIL != inference_datastructures_problem.problem_invalid_p(v_object)) {
	    format(stream, inference_datastructures_problem.alt_str38$_Invalid_PROBLEM__s_, inference_datastructures_problem.prob_suid(v_object));
	} else {
	    format(stream, inference_datastructures_problem.alt_str39$__a_PROBLEM__a__a__s_, new SubLObject[] { inference_datastructures_problem.problem_status(v_object), inference_datastructures_problem_store.problem_store_suid(inference_datastructures_problem.problem_store(v_object)),
		    inference_datastructures_problem.problem_suid(v_object), inference_datastructures_problem.problem_query(v_object) });
	}
	return NIL;
    }

    public static final SubLObject prob_argument_link_bindings_index(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField11();
    }

    public static final SubLObject prob_argument_link_bindings_index_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField10();
    }

    public static final SubLObject prob_argument_links(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField8();
    }

    public static final SubLObject prob_argument_links_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField7();
    }

    public static final SubLObject prob_backchain_required(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField12();
    }

    public static final SubLObject prob_dependent_links(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField7();
    }

    public static final SubLObject prob_dependent_links_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField6();
    }

    public static final SubLObject prob_free_hl_vars(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField5();
    }

    public static final SubLObject prob_memoization_state(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField13();
    }

    public static final SubLObject prob_proof_bindings_index(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField10();
    }

    public static final SubLObject prob_proof_bindings_index_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField9();
    }

    public static final SubLObject prob_query(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField4();
    }

    public static final SubLObject prob_query_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField4();
    }

    public static final SubLObject prob_status(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField6();
    }

    public static final SubLObject prob_status_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField5();
    }

    public static final SubLObject prob_store(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField3();
    }

    public static final SubLObject prob_store_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField3();
    }

    public static final SubLObject prob_suid(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField2();
    }

    public static final SubLObject prob_suid_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField2();
    }

    public static final SubLObject prob_tactics(final SubLObject v_object) {
	assert NIL != inference_datastructures_problem.problem_p(v_object) : "! inference_datastructures_problem.problem_p(v_object) " + "inference_datastructures_problem.problem_p error :" + v_object;
	return v_object.getField9();
    }

    public static final SubLObject prob_tactics_alt(SubLObject v_object) {
	SubLTrampolineFile.checkType(v_object, PROBLEM_P);
	return v_object.getField8();
    }

    public static final SubLObject problem_active_in_some_strategyP(final SubLObject problem) {
	return list_utilities.sublisp_boolean(inference_datastructures_problem.first_problem_active_strategy(problem));
    }

    public static final SubLObject problem_active_in_some_strategyP_alt(SubLObject problem) {
	return list_utilities.sublisp_boolean(inference_datastructures_problem.first_problem_active_strategy(problem));
    }

    public static final SubLObject problem_all_argument_links(final SubLObject problem) {
	return set_contents.set_contents_element_list(inference_datastructures_problem.prob_argument_links(problem));
    }

    public static final SubLObject problem_all_argument_links_alt(SubLObject problem) {
	return set_contents.set_contents_element_list(inference_datastructures_problem.prob_argument_links(problem));
    }

    public static final SubLObject problem_all_argument_links_have_typeP(final SubLObject problem, final SubLObject link_type) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_enumerated_types.problem_link_type_p(link_type) : "! inference_datastructures_enumerated_types.problem_link_type_p(link_type) "
		+ ("inference_datastructures_enumerated_types.problem_link_type_p(link_type) " + "CommonSymbols.NIL != inference_datastructures_enumerated_types.problem_link_type_p(link_type) ") + link_type;
	SubLObject failedP = NIL;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); (NIL == failedP)
		&& (NIL == set_contents.do_set_contents_doneP(basis_object, state)); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, link)) && (!link_type.eql(inference_datastructures_problem_link.problem_link_type(link)))) {
		failedP = T;
	    }
	}
	return makeBoolean(NIL == failedP);
    }

    public static final SubLObject problem_all_argument_links_have_typeP_alt(SubLObject problem, SubLObject link_type) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(link_type, inference_datastructures_problem.PROBLEM_LINK_TYPE_P);
	{
	    SubLObject failedP = NIL;
	    SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); !((NIL != failedP) || (NIL != set_contents.do_set_contents_doneP(basis_object, state))); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			if (link_type != inference_datastructures_problem_link.problem_link_type(link)) {
			    failedP = T;
			}
		    }
		}
	    }
	    return makeBoolean(NIL == failedP);
	}
    }

    public static final SubLObject problem_all_dependent_links(final SubLObject problem) {
	return set_contents.set_contents_element_list(inference_datastructures_problem.problem_dependent_links(problem));
    }

    public static final SubLObject problem_all_dependent_links_alt(SubLObject problem) {
	return set_contents.set_contents_element_list(inference_datastructures_problem.problem_dependent_links(problem));
    }

    public static final SubLObject problem_argument_link_count(final SubLObject problem) {
	return set_contents.set_contents_size(inference_datastructures_problem.prob_argument_links(problem));
    }

    /**
     * Return the count of PROBLEM's argument (child) links.
     */
    @LispMethod(comment = "Return the count of PROBLEM\'s argument (child) links.")
    public static final SubLObject problem_argument_link_count_alt(SubLObject problem) {
	return set_contents.set_contents_size(inference_datastructures_problem.prob_argument_links(problem));
    }

    public static final SubLObject problem_argument_link_of_type_count(final SubLObject problem, final SubLObject type) {
	SubLObject count = ZERO_INTEGER;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, type))) {
		count = add(count, ONE_INTEGER);
	    }
	}
	return count;
    }

    /**
     * Return the count of PROBLEM's argument (child) links that are of type TYPE.
     */
    @LispMethod(comment = "Return the count of PROBLEM\'s argument (child) links that are of type TYPE.")
    public static final SubLObject problem_argument_link_of_type_count_alt(SubLObject problem, SubLObject type) {
	{
	    SubLObject count = ZERO_INTEGER;
	    SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, type)) {
			    count = add(count, ONE_INTEGER);
			}
		    }
		}
	    }
	    return count;
	}
    }

    public static final SubLObject problem_argument_links(final SubLObject problem) {
	return inference_datastructures_problem.prob_argument_links(problem);
    }

    public static final SubLObject problem_argument_links_alt(SubLObject problem) {
	return inference_datastructures_problem.prob_argument_links(problem);
    }

    public static final SubLObject problem_argument_links_lookup(final SubLObject problem, final SubLObject v_bindings) {
	final SubLObject index = inference_datastructures_problem.prob_argument_link_bindings_index(problem);
	final SubLObject links = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	return links;
    }

    /**
     *
     *
     * @return list-of-problem-link-p (possibly empty)
     */
    @LispMethod(comment = "@return list-of-problem-link-p (possibly empty)")
    public static final SubLObject problem_argument_links_lookup_alt(SubLObject problem, SubLObject v_bindings) {
	{
	    SubLObject index = inference_datastructures_problem.prob_argument_link_bindings_index(problem);
	    SubLObject links = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	    return links;
	}
    }

    public static final SubLObject problem_backchain_requiredP(final SubLObject problem) {
	final SubLObject cache_value = inference_datastructures_problem.prob_backchain_required(problem);
	if (NIL != booleanp(cache_value)) {
	    return cache_value;
	}
	final SubLObject backchain_requiredP = inference_trampolines.compute_problem_backchain_requiredP(problem);
	inference_datastructures_problem._csetf_prob_backchain_required(problem, backchain_requiredP);
	return backchain_requiredP;
    }

    public static final SubLObject problem_dependent_link_count(final SubLObject problem) {
	return set_contents.set_contents_size(inference_datastructures_problem.prob_dependent_links(problem));
    }

    /**
     * Return the count of PROBLEM's dependent (parent) links.
     */
    @LispMethod(comment = "Return the count of PROBLEM\'s dependent (parent) links.")
    public static final SubLObject problem_dependent_link_count_alt(SubLObject problem) {
	return set_contents.set_contents_size(inference_datastructures_problem.prob_dependent_links(problem));
    }

    public static final SubLObject problem_dependent_links(final SubLObject problem) {
	return inference_datastructures_problem.prob_dependent_links(problem);
    }

    public static final SubLObject problem_dependent_links_alt(SubLObject problem) {
	return inference_datastructures_problem.prob_dependent_links(problem);
    }

    public static final SubLObject problem_destructibility_status(final SubLObject problem) {
	if (NIL != inference_datastructures_problem.problem_indestructibleP(problem)) {
	    return $INDESTRUCTIBLE;
	}
	if (NIL != inference_datastructures_problem.problem_destructibleP(problem)) {
	    return $DESTRUCTIBLE;
	}
	return $UNKNOWN;
    }

    /**
     *
     *
     * @return destructibility-status-p
     */
    @LispMethod(comment = "@return destructibility-status-p")
    public static final SubLObject problem_destructibility_status_alt(SubLObject problem) {
	if (NIL != inference_datastructures_problem.problem_indestructibleP(problem)) {
	    return $INDESTRUCTIBLE;
	} else if (NIL != inference_datastructures_problem.problem_destructibleP(problem)) {
	    return $DESTRUCTIBLE;
	} else {
	    return $UNKNOWN;
	}

    }

    public static final SubLObject problem_destructibleP(final SubLObject problem) {
	final SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
	return makeBoolean((NIL == inference_datastructures_problem_store.problem_store_janitor_staleP(janitor)) && (NIL == inference_datastructures_problem.problem_indestructibleP(problem)));
    }

    public static final SubLObject problem_destructibleP_alt(SubLObject problem) {
	{
	    SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
	    return makeBoolean((NIL == inference_datastructures_problem_store.problem_store_janitor_staleP(janitor)) && (NIL == inference_datastructures_problem.problem_indestructibleP(problem)));
	}
    }

    public static final SubLObject problem_discarded_tactic_count(final SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_with_status_count(problem, $DISCARDED);
    }

    public static final SubLObject problem_discarded_tactic_count_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_with_status_count(problem, $DISCARDED);
    }

    public static final SubLObject problem_discarded_tactics(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return list_utilities.remove_if_not(inference_datastructures_problem.$sym195$TACTIC_DISCARDED_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject problem_discarded_tactics_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return list_utilities.remove_if_not(inference_datastructures_problem.$sym170$TACTIC_DISCARDED_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject problem_dwimmed_status(final SubLObject problem) {
	final SubLObject status = inference_datastructures_problem.problem_status(problem);
	if (NIL != inference_worker.problem_goodP(problem)) {
	    final SubLObject dwimmed_status = inference_worker.good_version_of_problem_status(status);
	    return dwimmed_status;
	}
	return status;
    }

    public static final SubLObject problem_el_formula(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_czer.problem_query_el_formula(inference_datastructures_problem.problem_query(problem));
    }

    /**
     * Return an EL formula for the problem query of PROBLEM.
     */
    @LispMethod(comment = "Return an EL formula for the problem query of PROBLEM.")
    public static final SubLObject problem_el_formula_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return inference_czer.problem_query_el_formula(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject problem_executed_removal_tactic_productivities(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	SubLObject tuples = NIL;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $EXECUTED))) {
		final SubLObject removal_module = inference_datastructures_tactic.tactic_hl_module(tactic);
		final SubLObject module_name = inference_modules.hl_module_name(removal_module);
		final SubLObject original_productivity = inference_datastructures_tactic.tactic_original_productivity(tactic);
		final SubLObject actual_productivity = inference_worker.content_tactic_actual_productivity(tactic);
		tuples = cons(list(module_name, original_productivity, actual_productivity), tuples);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return nreverse(tuples);
    }

    /**
     * Return a list of tuples indicating the productivities for all executed removal tactics in STORE.
     * Tuples are of the form (HL-MODULE-NAME ESTIMATED ACTUAL)
     */
    @LispMethod(comment = "Return a list of tuples indicating the productivities for all executed removal tactics in STORE.\r\nTuples are of the form (HL-MODULE-NAME ESTIMATED ACTUAL)\nReturn a list of tuples indicating the productivities for all executed removal tactics in STORE.\nTuples are of the form (HL-MODULE-NAME ESTIMATED ACTUAL)")
    public static final SubLObject problem_executed_removal_tactic_productivities_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	{
	    SubLObject tuples = NIL;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $EXECUTED))) {
		    {
			SubLObject removal_module = inference_datastructures_tactic.tactic_hl_module(tactic);
			SubLObject module_name = inference_modules.hl_module_name(removal_module);
			SubLObject original_productivity = inference_datastructures_tactic.tactic_original_productivity(tactic);
			SubLObject actual_productivity = inference_worker.content_tactic_actual_productivity(tactic);
			tuples = cons(list(module_name, original_productivity, actual_productivity), tuples);
		    }
		}
	    }
	    return nreverse(tuples);
	}
    }

    public static final SubLObject problem_executed_tactic_count(final SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_with_status_count(problem, $EXECUTED);
    }

    public static final SubLObject problem_executed_tactic_count_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_with_status_count(problem, $EXECUTED);
    }

    public static final SubLObject problem_executed_tactics(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return list_utilities.remove_if_not(inference_datastructures_problem.$sym194$TACTIC_EXECUTED_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject problem_executed_tactics_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return list_utilities.remove_if_not(inference_datastructures_problem.$sym169$TACTIC_EXECUTED_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject problem_first_argument_link_of_type(final SubLObject problem, final SubLObject type) {
	SubLObject first_link = NIL;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if (((NIL != set_contents.do_set_contents_element_validP(state, link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, type))) && (NIL == first_link)) {
		first_link = link;
	    }
	}
	return first_link;
    }

    /**
     *
     *
     * @return nil or problem-link-p
     */
    @LispMethod(comment = "@return nil or problem-link-p")
    public static final SubLObject problem_first_argument_link_of_type_alt(SubLObject problem, SubLObject type) {
	{
	    SubLObject first_link = NIL;
	    SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, type)) {
			    if (NIL == first_link) {
				first_link = link;
			    }
			}
		    }
		}
	    }
	    return first_link;
	}
    }

    public static final SubLObject problem_formula(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_czer.problem_query_formula(inference_datastructures_problem.problem_query(problem));
    }

    /**
     * Return an HL formula for the problem query of PROBLEM.
     */
    @LispMethod(comment = "Return an HL formula for the problem query of PROBLEM.")
    public static final SubLObject problem_formula_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return inference_czer.problem_query_formula(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject problem_free_hl_vars(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_datastructures_problem.prob_free_hl_vars(problem);
    }

    public static final SubLObject problem_has_an_in_progress_complete_removal_tacticP(final SubLObject problem, final SubLObject strategic_context) {
	SubLObject has_oneP;
	SubLObject rest;
	SubLObject tactic;
	for (has_oneP = NIL, rest = NIL, rest = inference_datastructures_problem.problem_tactics(problem); (NIL == has_oneP) && (NIL != rest); rest = rest.rest()) {
	    tactic = rest.first();
	    if (((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_tactician_strategic_uninterestingness.tactic_completeP(tactic, strategic_context))) && (NIL != inference_datastructures_tactic.tactic_in_progressP(tactic))) {
		has_oneP = T;
	    }
	}
	return has_oneP;
    }

    public static final SubLObject problem_has_an_in_progress_tacticP(final SubLObject problem) {
	SubLObject has_oneP;
	SubLObject rest;
	SubLObject tactic;
	for (has_oneP = NIL, rest = NIL, rest = inference_datastructures_problem.problem_tactics(problem); (NIL == has_oneP) && (NIL != rest); rest = rest.rest()) {
	    tactic = rest.first();
	    if (NIL != inference_datastructures_tactic.tactic_in_progressP(tactic)) {
		has_oneP = T;
	    }
	}
	return has_oneP;
    }

    /**
     *
     *
     * @return booleanp; whether PROBLEM has at least one in-progress tactic
     */
    @LispMethod(comment = "@return booleanp; whether PROBLEM has at least one in-progress tactic")
    public static final SubLObject problem_has_an_in_progress_tacticP_alt(SubLObject problem) {
	{
	    SubLObject has_oneP = NIL;
	    SubLObject rest = NIL;
	    for (rest = inference_datastructures_problem.problem_tactics(problem); !((NIL != has_oneP) || (NIL == rest)); rest = rest.rest()) {
		{
		    SubLObject tactic = rest.first();
		    if (NIL != inference_datastructures_tactic.tactic_in_progressP(tactic)) {
			has_oneP = T;
		    }
		}
	    }
	    return has_oneP;
	}
    }

    public static final SubLObject problem_has_answer_link_p(final SubLObject problem) {
	return inference_datastructures_problem.problem_has_dependent_link_of_typeP(problem, $ANSWER);
    }

    public static final SubLObject problem_has_answer_link_p_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_has_dependent_link_of_typeP(problem, $ANSWER);
    }

    public static final SubLObject problem_has_argument_link_of_typeP(final SubLObject problem, final SubLObject type) {
	return list_utilities.sublisp_boolean(inference_datastructures_problem.problem_first_argument_link_of_type(problem, type));
    }

    public static final SubLObject problem_has_argument_link_of_typeP_alt(SubLObject problem, SubLObject type) {
	return list_utilities.sublisp_boolean(inference_datastructures_problem.problem_first_argument_link_of_type(problem, type));
    }

    public static final SubLObject problem_has_argument_link_p(final SubLObject problem) {
	return makeBoolean(NIL == set_contents.set_contents_emptyP(inference_datastructures_problem.prob_argument_links(problem)));
    }

    /**
     *
     *
     * @return boolean; t iff PROBLEM has any argument (child) links.
     */
    @LispMethod(comment = "@return boolean; t iff PROBLEM has any argument (child) links.")
    public static final SubLObject problem_has_argument_link_p_alt(SubLObject problem) {
	return makeBoolean(NIL == set_contents.set_contents_emptyP(inference_datastructures_problem.prob_argument_links(problem)));
    }

    public static final SubLObject problem_has_complete_possible_removal_tacticP(final SubLObject problem, final SubLObject strategic_context) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_worker.strategic_context_p(strategic_context) : "! inference_worker.strategic_context_p(strategic_context) " + ("inference_worker.strategic_context_p(strategic_context) " + "CommonSymbols.NIL != inference_worker.strategic_context_p(strategic_context) ")
		+ strategic_context;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if (((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE)))
		    && (NIL != inference_tactician_strategic_uninterestingness.tactic_completeP(tactic, strategic_context))) {
		return T;
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return NIL;
    }

    public static final SubLObject problem_has_complete_possible_removal_tacticP_alt(SubLObject problem, SubLObject strategic_context) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(strategic_context, inference_datastructures_problem.STRATEGIC_CONTEXT_P);
	{
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE))) {
		    if (NIL != inference_tactician_strategic_uninterestingness.tactic_completeP(tactic, strategic_context)) {
			return T;
		    }
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject problem_has_dependent_link_of_typeP(final SubLObject problem, final SubLObject type) {
	final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject dependent_link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    dependent_link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(dependent_link, type))) {
		return T;
	    }
	}
	return NIL;
    }

    public static final SubLObject problem_has_dependent_link_of_typeP_alt(SubLObject problem, SubLObject type) {
	{
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject dependent_link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
			if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(dependent_link, type)) {
			    return T;
			}
		    }
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject problem_has_dependent_link_p(final SubLObject problem) {
	return makeBoolean(NIL == set_contents.set_contents_emptyP(inference_datastructures_problem.prob_dependent_links(problem)));
    }

    /**
     *
     *
     * @return boolean; t iff PROBLEM has any dependent (parent) links.
     */
    @LispMethod(comment = "@return boolean; t iff PROBLEM has any dependent (parent) links.")
    public static final SubLObject problem_has_dependent_link_p_alt(SubLObject problem) {
	return makeBoolean(NIL == set_contents.set_contents_emptyP(inference_datastructures_problem.prob_dependent_links(problem)));
    }

    public static final SubLObject problem_has_more_than_one_supported_problemP(final SubLObject problem) {
	SubLObject first_supported_problem = NIL;
	SubLObject more_than_oneP = NIL;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	SubLObject supported_problem;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
		supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(link);
		if (NIL != supported_problem) {
		    if (NIL == first_supported_problem) {
			first_supported_problem = supported_problem;
		    } else if (!supported_problem.eql(first_supported_problem)) {
			more_than_oneP = T;
		    }

		}
	    }
	}
	return more_than_oneP;
    }

    public static final SubLObject problem_has_more_than_one_supported_problemP_alt(SubLObject problem) {
	{
	    SubLObject first_supported_problem = NIL;
	    SubLObject more_than_oneP = NIL;
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			{
			    SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(link);
			    if (NIL != supported_problem) {
				if (NIL == first_supported_problem) {
				    first_supported_problem = supported_problem;
				} else {
				    if (supported_problem != first_supported_problem) {
					more_than_oneP = T;
				    }
				}
			    }
			}
		    }
		}
	    }
	    return more_than_oneP;
	}
    }

    public static final SubLObject problem_has_no_logical_tacticsP(final SubLObject problem) {
	return makeBoolean(NIL == inference_datastructures_problem.problem_has_tactic_of_typeP(problem, $LOGICAL));
    }

    public static final SubLObject problem_has_no_logical_tacticsP_alt(SubLObject problem) {
	return makeBoolean(NIL == inference_datastructures_problem.problem_has_tactic_of_typeP(problem, $LOGICAL));
    }

    public static final SubLObject problem_has_no_tacticsP(final SubLObject problem) {
	return sublisp_null(inference_datastructures_problem.problem_tactics(problem));
    }

    public static final SubLObject problem_has_non_answer_dependent_link_p(final SubLObject problem) {
	if (NIL == inference_datastructures_problem.problem_has_dependent_link_p(problem)) {
	    return NIL;
	}
	final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject dependent_link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    dependent_link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) && (NIL == inference_worker_answer.answer_link_p(dependent_link))) {
		return T;
	    }
	}
	return NIL;
    }

    /**
     *
     *
     * @return boolean; t iff PROBLEM has any dependent (parent) links
    which are not answer links.
     */
    @LispMethod(comment = "@return boolean; t iff PROBLEM has any dependent (parent) links\r\nwhich are not answer links.")
    public static final SubLObject problem_has_non_answer_dependent_link_p_alt(SubLObject problem) {
	if (NIL == inference_datastructures_problem.problem_has_dependent_link_p(problem)) {
	    return NIL;
	} else {
	    {
		SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
		SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
		SubLObject state = NIL;
		for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		    {
			SubLObject dependent_link = set_contents.do_set_contents_next(basis_object, state);
			if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
			    if (NIL == inference_worker_answer.answer_link_p(dependent_link)) {
				return T;
			    }
			}
		    }
		}
		return NIL;
	    }
	}
    }

    public static final SubLObject problem_has_only_non_abducible_rule_transformation_dependent_linksP(final SubLObject problem) {
	SubLObject doneP = NIL;
	SubLObject result = NIL;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); (NIL == doneP)
		&& (NIL == set_contents.do_set_contents_doneP(basis_object, state)); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
		if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, $TRANSFORMATION)) {
		    if (NIL != kb_accessors.non_abducible_ruleP(inference_worker_transformation.transformation_link_rule_assertion(link))) {
			result = T;
		    } else {
			result = NIL;
			doneP = T;
		    }
		} else {
		    doneP = T;
		    result = NIL;
		}
	    }
	}
	return result;
    }

    public static final SubLObject problem_has_only_non_abducible_rule_transformation_dependent_linksP_alt(SubLObject problem) {
	{
	    SubLObject doneP = NIL;
	    SubLObject result = NIL;
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); !((NIL != doneP) || (NIL != set_contents.do_set_contents_doneP(basis_object, state))); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, $TRANSFORMATION)) {
			    if (NIL != kb_accessors.non_abducible_ruleP(inference_worker_transformation.transformation_link_rule_assertion(link))) {
				result = T;
			    } else {
				result = NIL;
				doneP = T;
			    }
			} else {
			    doneP = T;
			    result = NIL;
			}
		    }
		}
	    }
	    return result;
	}
    }

    public static final SubLObject problem_has_possible_removal_tacticP(final SubLObject problem, final SubLObject strategic_context) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_worker.strategic_context_p(strategic_context) : "! inference_worker.strategic_context_p(strategic_context) " + ("inference_worker.strategic_context_p(strategic_context) " + "CommonSymbols.NIL != inference_worker.strategic_context_p(strategic_context) ")
		+ strategic_context;
	return inference_datastructures_problem.problem_has_tactic_of_type_with_statusP(problem, $REMOVAL, $POSSIBLE);
    }

    public static final SubLObject problem_has_possible_removal_tacticP_alt(SubLObject problem, SubLObject strategic_context) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(strategic_context, inference_datastructures_problem.STRATEGIC_CONTEXT_P);
	return inference_datastructures_problem.problem_has_tactic_of_type_with_statusP(problem, $REMOVAL, $POSSIBLE);
    }

    public static final SubLObject problem_has_possible_tacticsP(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return list_utilities.sublisp_boolean(find_if(inference_datastructures_problem.$sym193$TACTIC_POSSIBLE_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject problem_has_possible_tacticsP_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return list_utilities.sublisp_boolean(find_if(inference_datastructures_problem.$sym168$TACTIC_POSSIBLE_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject problem_has_possible_transformation_tacticsP(final SubLObject problem) {
	return inference_datastructures_problem.problem_has_tactic_of_type_with_statusP(problem, $TRANSFORMATION, $POSSIBLE);
    }

    public static final SubLObject problem_has_possible_transformation_tacticsP_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_has_tactic_of_type_with_statusP(problem, $TRANSFORMATION, $POSSIBLE);
    }

    public static final SubLObject problem_has_removal_tacticsP(final SubLObject problem) {
	return inference_datastructures_problem.problem_has_tactic_of_typeP(problem, $GENERALIZED_REMOVAL);
    }

    public static final SubLObject problem_has_removal_tacticsP_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_has_tactic_of_typeP(problem, $GENERALIZED_REMOVAL);
    }

    public static final SubLObject problem_has_some_proofP(final SubLObject problem, SubLObject proof_status) {
	if (proof_status == UNPROVIDED) {
	    proof_status = NIL;
	}
	final SubLThread thread = SubLProcess.currentSubLThread();
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	if ((NIL != proof_status) && (NIL != inference_datastructures_problem_store.problem_store_some_rejected_proofsP(store))) {
	    final SubLObject status_var = proof_status;
	    SubLObject iteration_state;
	    for (iteration_state = dictionary_contents.do_dictionary_contents_state(inference_datastructures_problem.problem_proof_bindings_index(problem)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(
		    iteration_state)) {
		thread.resetMultipleValues();
		final SubLObject v_bindings = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
		final SubLObject proof_list = thread.secondMultipleValue();
		thread.resetMultipleValues();
		SubLObject cdolist_list_var = proof_list;
		SubLObject proof = NIL;
		proof = cdolist_list_var.first();
		while (NIL != cdolist_list_var) {
		    if (NIL != inference_datastructures_problem.proof_has_statusP(proof, status_var)) {
			return T;
		    }
		    cdolist_list_var = cdolist_list_var.rest();
		    proof = cdolist_list_var.first();
		}
	    }
	    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
	    return NIL;
	}
	if (proof_status == $REJECTED) {
	    return NIL;
	}
	return makeBoolean(NIL == dictionary_contents.dictionary_contents_empty_p(inference_datastructures_problem.prob_proof_bindings_index(problem)));
    }

    public static final SubLObject problem_has_some_proofP_alt(SubLObject problem, SubLObject proof_status) {
	if (proof_status == UNPROVIDED) {
	    proof_status = NIL;
	}
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject status_var = proof_status;
		SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(inference_datastructures_problem.problem_proof_bindings_index(problem));
		while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
		    thread.resetMultipleValues();
		    {
			SubLObject v_bindings = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
			SubLObject proof_list = thread.secondMultipleValue();
			thread.resetMultipleValues();
			{
			    SubLObject cdolist_list_var = proof_list;
			    SubLObject proof = NIL;
			    for (proof = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), proof = cdolist_list_var.first()) {
				if (NIL != inference_datastructures_problem.proof_has_statusP(proof, status_var)) {
				    return T;
				}
			    }
			}
			iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
		    }
		}
		dictionary_contents.do_dictionary_contents_finalize(iteration_state);
	    }
	    return NIL;
	}
    }

    public static final SubLObject problem_has_some_proven_proofP(final SubLObject problem) {
	return inference_datastructures_problem.problem_has_some_proofP(problem, $PROVEN);
    }

    public static final SubLObject problem_has_some_proven_proofP_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_has_some_proofP(problem, $PROVEN);
    }

    public static final SubLObject problem_has_some_rejected_proofP(final SubLObject problem) {
	return inference_datastructures_problem.problem_has_some_proofP(problem, $REJECTED);
    }

    public static final SubLObject problem_has_some_rejected_proofP_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_has_some_proofP(problem, $REJECTED);
    }

    public static final SubLObject problem_has_split_tacticsP(final SubLObject problem) {
	SubLObject foundP;
	SubLObject rest;
	SubLObject tactic;
	for (foundP = NIL, rest = NIL, rest = inference_datastructures_problem.problem_tactics(problem); (NIL == foundP) && (NIL != rest); foundP = inference_worker_split.split_tactic_p(tactic), rest = rest.rest()) {
	    tactic = rest.first();
	}
	return foundP;
    }

    public static final SubLObject problem_has_split_tacticsP_alt(SubLObject problem) {
	{
	    SubLObject foundP = NIL;
	    SubLObject rest = NIL;
	    for (rest = inference_datastructures_problem.problem_tactics(problem); !((NIL != foundP) || (NIL == rest)); rest = rest.rest()) {
		{
		    SubLObject tactic = rest.first();
		    foundP = inference_worker_split.split_tactic_p(tactic);
		}
	    }
	    return foundP;
	}
    }

    public static final SubLObject problem_has_supporting_problem_p(final SubLObject problem) {
	final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject argument_link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    argument_link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, argument_link)) && (NIL != inference_datastructures_problem_link.problem_link_with_supporting_problem_p(argument_link))) {
		return T;
	    }
	}
	return NIL;
    }

    /**
     *
     *
     * @return boolean; t iff PROBLEM has at least one supporting problem.
     */
    @LispMethod(comment = "@return boolean; t iff PROBLEM has at least one supporting problem.")
    public static final SubLObject problem_has_supporting_problem_p_alt(SubLObject problem) {
	{
	    SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject argument_link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, argument_link)) {
			if (NIL != inference_datastructures_problem_link.problem_link_with_supporting_problem_p(argument_link)) {
			    return T;
			}
		    }
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject problem_has_tactic_of_type_with_statusP(final SubLObject problem, final SubLObject type, SubLObject status) {
	if (status == UNPROVIDED) {
	    status = NIL;
	}
	SubLObject foundP;
	SubLObject rest;
	SubLObject tactic;
	for (foundP = NIL, rest = NIL, rest = inference_datastructures_problem.problem_tactics(problem); (NIL == foundP) && (NIL != rest); rest = rest.rest()) {
	    tactic = rest.first();
	    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, type)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, status))) {
		foundP = T;
	    }
	}
	return foundP;
    }

    public static final SubLObject problem_has_tactic_of_type_with_statusP_alt(SubLObject problem, SubLObject type, SubLObject status) {
	if (status == UNPROVIDED) {
	    status = NIL;
	}
	{
	    SubLObject foundP = NIL;
	    SubLObject rest = NIL;
	    for (rest = inference_datastructures_problem.problem_tactics(problem); !((NIL != foundP) || (NIL == rest)); rest = rest.rest()) {
		{
		    SubLObject tactic = rest.first();
		    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, type)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, status))) {
			foundP = T;
		    }
		}
	    }
	    return foundP;
	}
    }

    public static final SubLObject problem_has_tactic_of_typeP(final SubLObject problem, final SubLObject type) {
	return inference_datastructures_problem.problem_has_tactic_of_type_with_statusP(problem, type, UNPROVIDED);
    }

    public static final SubLObject problem_has_tactic_of_typeP_alt(SubLObject problem, SubLObject type) {
	return inference_datastructures_problem.problem_has_tactic_of_type_with_statusP(problem, type, UNPROVIDED);
    }

    public static final SubLObject problem_has_transformation_tacticsP(final SubLObject problem) {
	return inference_datastructures_problem.problem_has_tactic_of_typeP(problem, $TRANSFORMATION);
    }

    public static final SubLObject problem_has_transformation_tacticsP_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_has_tactic_of_typeP(problem, $TRANSFORMATION);
    }

    public static final SubLObject problem_in_equality_reasoning_domainP(final SubLObject problem) {
	final SubLObject query = inference_datastructures_problem.problem_query(problem);
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject equality_reasoning_domain = inference_datastructures_problem_store.problem_store_equality_reasoning_domain(store);
	return inference_datastructures_problem_query.problem_query_in_equality_reasoning_domainP(query, equality_reasoning_domain);
    }

    public static final SubLObject problem_in_equality_reasoning_domainP_alt(SubLObject problem) {
	{
	    SubLObject query = inference_datastructures_problem.problem_query(problem);
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject equality_reasoning_domain = inference_datastructures_problem_store.problem_store_equality_reasoning_domain(store);
	    return inference_datastructures_problem_query.problem_query_in_equality_reasoning_domainP(query, equality_reasoning_domain);
	}
    }

    public static final SubLObject problem_indestructibleP(final SubLObject problem) {
	final SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
	final SubLObject indestructible_problems = inference_datastructures_problem_store.problem_store_janitor_indestructible_problems(janitor);
	return set.set_memberP(problem, indestructible_problems);
    }

    public static final SubLObject problem_indestructibleP_alt(SubLObject problem) {
	{
	    SubLObject janitor = inference_datastructures_problem_store.problem_store_janitor(inference_datastructures_problem.problem_store(problem));
	    SubLObject indestructible_problems = inference_datastructures_problem_store.problem_store_janitor_indestructible_problems(janitor);
	    return set.set_memberP(problem, indestructible_problems);
	}
    }

    public static final SubLObject problem_intuitive_min_transformation_depth(final SubLObject problem, final SubLObject inference) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	final SubLObject _prev_bind_0 = inference_datastructures_problem.$transformation_depth_computation$.currentBinding(thread);
	try {
	    inference_datastructures_problem.$transformation_depth_computation$.bind($INTUITIVE, thread);
	    return inference_datastructures_problem.problem_min_transformation_depth(problem, inference);
	} finally {
	    inference_datastructures_problem.$transformation_depth_computation$.rebind(_prev_bind_0, thread);
	}
    }

    public static final SubLObject problem_invalid_p(final SubLObject problem) {
	return eq($FREE, inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject problem_invalid_p_alt(SubLObject problem) {
	return eq($FREE, inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject problem_literal_count(final SubLObject problem, SubLObject sense) {
	if (sense == UNPROVIDED) {
	    sense = NIL;
	}
	final SubLObject query = inference_datastructures_problem.problem_query(problem);
	return inference_datastructures_problem.problem_query_literal_count(query, sense);
    }

    public static final SubLObject problem_literal_count_alt(SubLObject problem, SubLObject sense) {
	if (sense == UNPROVIDED) {
	    sense = NIL;
	}
	{
	    SubLObject query = inference_datastructures_problem.problem_query(problem);
	    return inference_datastructures_problem.problem_query_literal_count(query, sense);
	}
    }

    public static final SubLObject problem_memoization_state(final SubLObject problem) {
	return inference_datastructures_problem.prob_memoization_state(problem);
    }

    public static final SubLObject problem_min_depth(final SubLObject problem) {
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject hash = inference_datastructures_problem_store.problem_store_min_depth_index(store);
	final SubLObject depth = gethash_without_values(problem, hash, $UNDETERMINED);
	return depth;
    }

    /**
     *
     *
     * @return :undetermined or non-negative-integer-p
     */
    @LispMethod(comment = "@return :undetermined or non-negative-integer-p")
    public static final SubLObject problem_min_depth_alt(SubLObject problem) {
	{
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject hash = inference_datastructures_problem_store.problem_store_min_depth_index(store);
	    SubLObject depth = gethash_without_values(problem, hash, $UNDETERMINED);
	    return depth;
	}
    }

    public static final SubLObject problem_min_proof_depth(final SubLObject problem, final SubLObject inference) {
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_proof_depth_index(store), inference, UNPROVIDED);
	if (NIL != hash) {
	    final SubLObject depth = gethash_without_values(problem, hash, UNPROVIDED);
	    if (NIL != subl_promotions.non_negative_integer_p(depth)) {
		return depth;
	    }
	}
	return $UNDETERMINED;
    }

    /**
     *
     *
     * @return non-negative-integer-p or :undetermined; the number of
    links on the shortest path between PROBLEM and INFERENCE.
     */
    @LispMethod(comment = "@return non-negative-integer-p or :undetermined; the number of\r\nlinks on the shortest path between PROBLEM and INFERENCE.")
    public static final SubLObject problem_min_proof_depth_alt(SubLObject problem, SubLObject inference) {
	{
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_proof_depth_index(store), inference, UNPROVIDED);
	    if (NIL != hash) {
		{
		    SubLObject depth = gethash_without_values(problem, hash, UNPROVIDED);
		    if (NIL != subl_promotions.non_negative_integer_p(depth)) {
			return depth;
		    }
		}
	    }
	}
	return $UNDETERMINED;
    }

    public static final SubLObject problem_min_transformation_depth(final SubLObject problem, final SubLObject inference) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	if (NIL != inference_datastructures_problem.$problem_min_transformation_depth_from_signature_enabledP$.getDynamicValue(thread)) {
	    return inference_min_transformation_depth.problem_min_transformation_depth_from_signature(problem, inference);
	}
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_index(store), inference, UNPROVIDED);
	if (NIL != hash) {
	    final SubLObject depth = gethash_without_values(problem, hash, UNPROVIDED);
	    if (NIL != subl_promotions.non_negative_integer_p(depth)) {
		return depth;
	    }
	}
	return $UNDETERMINED;
    }

    /**
     *
     *
     * @return non-negative-integer-p or :undetermined; the number of
    transformation links on the shortest path between PROBLEM and INFERENCE.
     */
    @LispMethod(comment = "@return non-negative-integer-p or :undetermined; the number of\r\ntransformation links on the shortest path between PROBLEM and INFERENCE.")
    public static final SubLObject problem_min_transformation_depth_alt(SubLObject problem, SubLObject inference) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    if (NIL != inference_datastructures_problem.$problem_min_transformation_depth_from_signature_enabledP$.getDynamicValue(thread)) {
		return inference_min_transformation_depth.problem_min_transformation_depth_from_signature(problem, inference);
	    }
	    {
		SubLObject store = inference_datastructures_problem.problem_store(problem);
		SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_index(store), inference, UNPROVIDED);
		if (NIL != hash) {
		    {
			SubLObject depth = gethash_without_values(problem, hash, UNPROVIDED);
			if (NIL != subl_promotions.non_negative_integer_p(depth)) {
			    return depth;
			}
		    }
		}
	    }
	    return $UNDETERMINED;
	}
    }

    public static final SubLObject problem_min_transformation_depth_signature(final SubLObject problem, final SubLObject inference) {
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_signature_index(store), inference, UNPROVIDED);
	if (NIL != hash) {
	    final SubLObject depth = gethash_without_values(problem, hash, UNPROVIDED);
	    if (NIL != depth) {
		return depth;
	    }
	}
	return $UNDETERMINED;
    }

    /**
     *
     *
     * @return problem-query-depth-signature-p or :undetermined; a signature of the the per-literal number of
    transformation links on the shortest path between PROBLEM and INFERENCE.
     */
    @LispMethod(comment = "@return problem-query-depth-signature-p or :undetermined; a signature of the the per-literal number of\r\ntransformation links on the shortest path between PROBLEM and INFERENCE.")
    public static final SubLObject problem_min_transformation_depth_signature_alt(SubLObject problem, SubLObject inference) {
	{
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_signature_index(store), inference, UNPROVIDED);
	    if (NIL != hash) {
		{
		    SubLObject depth = gethash_without_values(problem, hash, UNPROVIDED);
		    if (NIL != depth) {
			return depth;
		    }
		}
	    }
	}
	return $UNDETERMINED;
    }

    public static final SubLObject problem_mt(final SubLObject problem) {
	SubLObject result = NIL;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_query(problem);
	SubLObject contextualized_clause = NIL;
	contextualized_clause = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    SubLObject sense = $NEG;
	    SubLObject index_var = ZERO_INTEGER;
	    SubLObject cdolist_list_var_$14 = clauses.neg_lits(contextualized_clause);
	    SubLObject contextualized_asent = NIL;
	    contextualized_asent = cdolist_list_var_$14.first();
	    while (NIL != cdolist_list_var_$14) {
		SubLObject current;
		final SubLObject datum = current = contextualized_asent;
		SubLObject mt = NIL;
		SubLObject asent = NIL;
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		mt = current.first();
		current = current.rest();
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		asent = current.first();
		current = current.rest();
		if (NIL == current) {
		    if (NIL != result) {
			if (!mt.equal(result)) {
			    return NIL;
			}
		    } else {
			result = mt;
		    }
		} else {
		    cdestructuring_bind_error(datum, inference_datastructures_problem.$list178);
		}
		index_var = add(index_var, ONE_INTEGER);
		cdolist_list_var_$14 = cdolist_list_var_$14.rest();
		contextualized_asent = cdolist_list_var_$14.first();
	    }
	    sense = $POS;
	    index_var = ZERO_INTEGER;
	    SubLObject cdolist_list_var_$15 = clauses.pos_lits(contextualized_clause);
	    contextualized_asent = NIL;
	    contextualized_asent = cdolist_list_var_$15.first();
	    while (NIL != cdolist_list_var_$15) {
		SubLObject current;
		final SubLObject datum = current = contextualized_asent;
		SubLObject mt = NIL;
		SubLObject asent = NIL;
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		mt = current.first();
		current = current.rest();
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		asent = current.first();
		current = current.rest();
		if (NIL == current) {
		    if (NIL != result) {
			if (!mt.equal(result)) {
			    return NIL;
			}
		    } else {
			result = mt;
		    }
		} else {
		    cdestructuring_bind_error(datum, inference_datastructures_problem.$list178);
		}
		index_var = add(index_var, ONE_INTEGER);
		cdolist_list_var_$15 = cdolist_list_var_$15.rest();
		contextualized_asent = cdolist_list_var_$15.first();
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    contextualized_clause = cdolist_list_var.first();
	}
	return result;
    }

    public static final SubLObject problem_mts(final SubLObject problem) {
	final SubLObject one_mt = inference_datastructures_problem.problem_mt(problem);
	if (NIL != one_mt) {
	    return list(one_mt);
	}
	final SubLObject mt_set = set.new_set(symbol_function(EQUAL), UNPROVIDED);
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_query(problem);
	SubLObject contextualized_clause = NIL;
	contextualized_clause = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    SubLObject sense = $NEG;
	    SubLObject index_var = ZERO_INTEGER;
	    SubLObject cdolist_list_var_$16 = clauses.neg_lits(contextualized_clause);
	    SubLObject contextualized_asent = NIL;
	    contextualized_asent = cdolist_list_var_$16.first();
	    while (NIL != cdolist_list_var_$16) {
		SubLObject current;
		final SubLObject datum = current = contextualized_asent;
		SubLObject mt = NIL;
		SubLObject asent = NIL;
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		mt = current.first();
		current = current.rest();
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		asent = current.first();
		current = current.rest();
		if (NIL == current) {
		    set.set_add(mt, mt_set);
		} else {
		    cdestructuring_bind_error(datum, inference_datastructures_problem.$list178);
		}
		index_var = add(index_var, ONE_INTEGER);
		cdolist_list_var_$16 = cdolist_list_var_$16.rest();
		contextualized_asent = cdolist_list_var_$16.first();
	    }
	    sense = $POS;
	    index_var = ZERO_INTEGER;
	    SubLObject cdolist_list_var_$17 = clauses.pos_lits(contextualized_clause);
	    contextualized_asent = NIL;
	    contextualized_asent = cdolist_list_var_$17.first();
	    while (NIL != cdolist_list_var_$17) {
		SubLObject current;
		final SubLObject datum = current = contextualized_asent;
		SubLObject mt = NIL;
		SubLObject asent = NIL;
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		mt = current.first();
		current = current.rest();
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		asent = current.first();
		current = current.rest();
		if (NIL == current) {
		    set.set_add(mt, mt_set);
		} else {
		    cdestructuring_bind_error(datum, inference_datastructures_problem.$list178);
		}
		index_var = add(index_var, ONE_INTEGER);
		cdolist_list_var_$17 = cdolist_list_var_$17.rest();
		contextualized_asent = cdolist_list_var_$17.first();
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    contextualized_clause = cdolist_list_var.first();
	}
	return set.set_element_list(mt_set);
    }

    public static final SubLObject problem_next_tactic_suid(final SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_count(problem);
    }

    public static final SubLObject problem_next_tactic_suid_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_count(problem);
    }

    public static final SubLObject problem_no_tactics_possibleP(final SubLObject problem) {
	return makeBoolean(NIL == inference_datastructures_problem.problem_has_possible_tacticsP(problem));
    }

    public static final SubLObject problem_no_tactics_possibleP_alt(SubLObject problem) {
	return makeBoolean(NIL == inference_datastructures_problem.problem_has_possible_tacticsP(problem));
    }

    public static final SubLObject problem_p(final SubLObject v_object) {
	return v_object.getClass() == inference_datastructures_problem.$problem_native.class ? T : NIL;
    }

    public static final SubLObject problem_p_alt(SubLObject v_object) {
	return v_object.getClass() == inference_datastructures_problem.$problem_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static final SubLObject problem_possible_removal_tactics(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	SubLObject tactics = NIL;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE))) {
		tactics = cons(tactic, tactics);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return tactics;
    }

    public static final SubLObject problem_possible_removal_tactics_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	{
	    SubLObject tactics = NIL;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE))) {
		    tactics = cons(tactic, tactics);
		}
	    }
	    return tactics;
	}
    }

    public static final SubLObject problem_possible_tactic_count(final SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_with_status_count(problem, $POSSIBLE);
    }

    public static final SubLObject problem_possible_tactic_count_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_tactic_with_status_count(problem, $POSSIBLE);
    }

    public static final SubLObject problem_possible_tactics(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return list_utilities.remove_if_not(inference_datastructures_problem.$sym193$TACTIC_POSSIBLE_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject problem_possible_tactics_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return list_utilities.remove_if_not(inference_datastructures_problem.$sym168$TACTIC_POSSIBLE_, inference_datastructures_problem.problem_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject problem_potentially_processedP(final SubLObject problem) {
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject potentially_processed_problems = inference_datastructures_problem_store.problem_store_potentially_processed_problems(store);
	return set.set_memberP(problem, potentially_processed_problems);
    }

    public static final SubLObject problem_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
	inference_datastructures_problem.print_problem(v_object, stream, ZERO_INTEGER);
	return NIL;
    }

    public static final SubLObject problem_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
	inference_datastructures_problem.print_problem(v_object, stream, ZERO_INTEGER);
	return NIL;
    }

    public static final SubLObject problem_proof_bindings_index(final SubLObject problem) {
	return inference_datastructures_problem.prob_proof_bindings_index(problem);
    }

    public static final SubLObject problem_proof_bindings_index_alt(SubLObject problem) {
	return inference_datastructures_problem.prob_proof_bindings_index(problem);
    }

    public static final SubLObject problem_proof_count(final SubLObject problem, SubLObject proof_status) {
	if (proof_status == UNPROVIDED) {
	    proof_status = NIL;
	}
	final SubLThread thread = SubLProcess.currentSubLThread();
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	if ((NIL != proof_status) && (NIL != inference_datastructures_problem_store.problem_store_some_rejected_proofsP(store))) {
	    SubLObject count = ZERO_INTEGER;
	    final SubLObject status_var = proof_status;
	    SubLObject iteration_state;
	    for (iteration_state = dictionary_contents.do_dictionary_contents_state(inference_datastructures_problem.problem_proof_bindings_index(problem)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(
		    iteration_state)) {
		thread.resetMultipleValues();
		final SubLObject v_bindings = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
		final SubLObject proof_list = thread.secondMultipleValue();
		thread.resetMultipleValues();
		SubLObject cdolist_list_var = proof_list;
		SubLObject proof = NIL;
		proof = cdolist_list_var.first();
		while (NIL != cdolist_list_var) {
		    if (NIL != inference_datastructures_problem.proof_has_statusP(proof, status_var)) {
			count = add(count, ONE_INTEGER);
		    }
		    cdolist_list_var = cdolist_list_var.rest();
		    proof = cdolist_list_var.first();
		}
	    }
	    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
	    return count;
	}
	if (proof_status == $REJECTED) {
	    return ZERO_INTEGER;
	}
	return dictionary_contents.dictionary_contents_size(inference_datastructures_problem.prob_proof_bindings_index(problem));
    }

    public static final SubLObject problem_proof_count_alt(SubLObject problem, SubLObject proof_status) {
	if (proof_status == UNPROVIDED) {
	    proof_status = NIL;
	}
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject count = ZERO_INTEGER;
		SubLObject status_var = proof_status;
		SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(inference_datastructures_problem.problem_proof_bindings_index(problem));
		while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
		    thread.resetMultipleValues();
		    {
			SubLObject v_bindings = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
			SubLObject proof_list = thread.secondMultipleValue();
			thread.resetMultipleValues();
			{
			    SubLObject cdolist_list_var = proof_list;
			    SubLObject proof = NIL;
			    for (proof = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), proof = cdolist_list_var.first()) {
				if (NIL != inference_datastructures_problem.proof_has_statusP(proof, status_var)) {
				    count = add(count, ONE_INTEGER);
				}
			    }
			}
			iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
		    }
		}
		dictionary_contents.do_dictionary_contents_finalize(iteration_state);
		return count;
	    }
	}
    }

    public static final SubLObject problem_proofs_lookup(final SubLObject problem, final SubLObject v_bindings) {
	final SubLObject index = inference_datastructures_problem.prob_proof_bindings_index(problem);
	final SubLObject proofs = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	return proofs;
    }

    /**
     *
     *
     * @return list-of-proof-p (possibly empty)
     */
    @LispMethod(comment = "@return list-of-proof-p (possibly empty)")
    public static final SubLObject problem_proofs_lookup_alt(SubLObject problem, SubLObject v_bindings) {
	{
	    SubLObject index = inference_datastructures_problem.prob_proof_bindings_index(problem);
	    SubLObject proofs = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	    return proofs;
	}
    }

    public static final SubLObject problem_proven_proof_count(final SubLObject problem) {
	return inference_datastructures_problem.problem_proof_count(problem, $PROVEN);
    }

    public static final SubLObject problem_proven_proof_count_alt(SubLObject problem) {
	return inference_datastructures_problem.problem_proof_count(problem, $PROVEN);
    }

    public static final SubLObject problem_query(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_datastructures_problem.prob_query(problem);
    }

    public static final SubLObject problem_query_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return inference_datastructures_problem.prob_query(problem);
    }

    public static final SubLObject problem_query_literal_count(final SubLObject query, SubLObject sense) {
	if (sense == UNPROVIDED) {
	    sense = NIL;
	}
	SubLObject count = ZERO_INTEGER;
	SubLObject cdolist_list_var = query;
	SubLObject contextualized_clause = NIL;
	contextualized_clause = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    SubLObject literal_sense = $NEG;
	    SubLObject index_var = ZERO_INTEGER;
	    SubLObject cdolist_list_var_$12 = clauses.neg_lits(contextualized_clause);
	    SubLObject contextualized_asent = NIL;
	    contextualized_asent = cdolist_list_var_$12.first();
	    while (NIL != cdolist_list_var_$12) {
		SubLObject current;
		final SubLObject datum = current = contextualized_asent;
		SubLObject mt = NIL;
		SubLObject asent = NIL;
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		mt = current.first();
		current = current.rest();
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		asent = current.first();
		current = current.rest();
		if (NIL == current) {
		    if ((NIL == sense) || sense.eql(literal_sense)) {
			count = add(count, ONE_INTEGER);
		    }
		} else {
		    cdestructuring_bind_error(datum, inference_datastructures_problem.$list178);
		}
		index_var = add(index_var, ONE_INTEGER);
		cdolist_list_var_$12 = cdolist_list_var_$12.rest();
		contextualized_asent = cdolist_list_var_$12.first();
	    }
	    literal_sense = $POS;
	    index_var = ZERO_INTEGER;
	    SubLObject cdolist_list_var_$13 = clauses.pos_lits(contextualized_clause);
	    contextualized_asent = NIL;
	    contextualized_asent = cdolist_list_var_$13.first();
	    while (NIL != cdolist_list_var_$13) {
		SubLObject current;
		final SubLObject datum = current = contextualized_asent;
		SubLObject mt = NIL;
		SubLObject asent = NIL;
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		mt = current.first();
		current = current.rest();
		destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list178);
		asent = current.first();
		current = current.rest();
		if (NIL == current) {
		    if ((NIL == sense) || sense.eql(literal_sense)) {
			count = add(count, ONE_INTEGER);
		    }
		} else {
		    cdestructuring_bind_error(datum, inference_datastructures_problem.$list178);
		}
		index_var = add(index_var, ONE_INTEGER);
		cdolist_list_var_$13 = cdolist_list_var_$13.rest();
		contextualized_asent = cdolist_list_var_$13.first();
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    contextualized_clause = cdolist_list_var.first();
	}
	return count;
    }

    public static final SubLObject problem_query_literal_count_alt(SubLObject query, SubLObject sense) {
	if (sense == UNPROVIDED) {
	    sense = NIL;
	}
	{
	    SubLObject count = ZERO_INTEGER;
	    SubLObject cdolist_list_var = query;
	    SubLObject contextualized_clause = NIL;
	    for (contextualized_clause = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), contextualized_clause = cdolist_list_var.first()) {
		{
		    SubLObject literal_sense = $NEG;
		    SubLObject index_var = ZERO_INTEGER;
		    SubLObject cdolist_list_var_10 = clauses.neg_lits(contextualized_clause);
		    SubLObject contextualized_asent = NIL;
		    for (contextualized_asent = cdolist_list_var_10.first(); NIL != cdolist_list_var_10; cdolist_list_var_10 = cdolist_list_var_10.rest(), contextualized_asent = cdolist_list_var_10.first()) {
			{
			    SubLObject datum = contextualized_asent;
			    SubLObject current = datum;
			    SubLObject mt = NIL;
			    SubLObject asent = NIL;
			    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list153);
			    mt = current.first();
			    current = current.rest();
			    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list153);
			    asent = current.first();
			    current = current.rest();
			    if (NIL == current) {
				if ((NIL == sense) || (sense == literal_sense)) {
				    count = add(count, ONE_INTEGER);
				}
			    } else {
				cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list153);
			    }
			}
			index_var = add(index_var, ONE_INTEGER);
		    }
		}
		{
		    SubLObject literal_sense = $POS;
		    SubLObject index_var = ZERO_INTEGER;
		    SubLObject cdolist_list_var_11 = clauses.pos_lits(contextualized_clause);
		    SubLObject contextualized_asent = NIL;
		    for (contextualized_asent = cdolist_list_var_11.first(); NIL != cdolist_list_var_11; cdolist_list_var_11 = cdolist_list_var_11.rest(), contextualized_asent = cdolist_list_var_11.first()) {
			{
			    SubLObject datum = contextualized_asent;
			    SubLObject current = datum;
			    SubLObject mt = NIL;
			    SubLObject asent = NIL;
			    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list153);
			    mt = current.first();
			    current = current.rest();
			    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list153);
			    asent = current.first();
			    current = current.rest();
			    if (NIL == current) {
				if ((NIL == sense) || (sense == literal_sense)) {
				    count = add(count, ONE_INTEGER);
				}
			    } else {
				cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list153);
			    }
			}
			index_var = add(index_var, ONE_INTEGER);
		    }
		}
	    }
	    return count;
	}
    }

    public static final SubLObject problem_query_sole_clause(final SubLObject query) {
	SubLObject dnf_clause = NIL;
	destructuring_bind_must_consp(query, query, inference_datastructures_problem.$list183);
	dnf_clause = query.first();
	final SubLObject current = query.rest();
	if (NIL == current) {
	    return dnf_clause;
	}
	cdestructuring_bind_error(query, inference_datastructures_problem.$list183);
	return NIL;
    }

    public static final SubLObject problem_query_sole_clause_alt(SubLObject query) {
	{
	    SubLObject datum = query;
	    SubLObject current = datum;
	    SubLObject dnf_clause = NIL;
	    destructuring_bind_must_consp(current, datum, inference_datastructures_problem.alt_list158);
	    dnf_clause = current.first();
	    current = current.rest();
	    if (NIL == current) {
		return dnf_clause;
	    } else {
		cdestructuring_bind_error(datum, inference_datastructures_problem.alt_list158);
	    }
	}
	return NIL;
    }

    public static final SubLObject problem_relevant_strategies(final SubLObject problem) {
	SubLObject result = NIL;
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
	if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
	    final SubLObject idx_$22 = idx;
	    if (NIL == id_index_dense_objects_empty_p(idx_$22, $SKIP)) {
		final SubLObject vector_var = id_index_dense_objects(idx_$22);
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
			    for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
				    state); state = set_contents.do_set_contents_update_state(state)) {
				strategy = set_contents.do_set_contents_next(basis_object, state);
				if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
				    result = cons(strategy, result);
				}
			    }
			}
		    }
		}
	    }
	    final SubLObject idx_$23 = idx;
	    if (NIL == id_index_sparse_objects_empty_p(idx_$23)) {
		final SubLObject cdohash_table = id_index_sparse_objects(idx_$23);
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
			    for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2,
				    state2); state2 = set_contents.do_set_contents_update_state(state2)) {
				strategy2 = set_contents.do_set_contents_next(basis_object2, state2);
				if (NIL != set_contents.do_set_contents_element_validP(state2, strategy2)) {
				    result = cons(strategy2, result);
				}
			    }
			}
		    }
		} finally {
		    releaseEntrySetIterator(cdohash_iterator);
		}
	    }
	}
	return nreverse(result);
    }

    // public static final SubLObject problem_relevant_strategies_alt(SubLObject problem) {
    // {
    // SubLObject result = NIL;
    // SubLObject prob = problem;
    // SubLObject store = problem_store(prob);
    // SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
    // if (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {
    // {
    // SubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);
    // SubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);
    // SubLObject inference = NIL;
    // while (NIL != id) {
    // inference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);
    // if (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {
    // if (NIL != problem_relevant_to_inferenceP(prob, inference)) {
    // {
    // SubLObject inference_var = inference;
    // SubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));
    // SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
    // SubLObject state = NIL;
    // for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
    // {
    // SubLObject strategy = set_contents.do_set_contents_next(basis_object, state);
    // if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
    // result = cons(strategy, result);
    // }
    // }
    // }
    // }
    // }
    // }
    // id = id_index.do_id_index_next_id(idx, NIL, id, state_var);
    // state_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);
    // }
    // }
    // }
    // return nreverse(result);
    // }
    // }
    @LispMethod(comment = "public static final SubLObject problem_relevant_strategies_alt(SubLObject problem) {\nSubLObject result = NIL;\nSubLObject prob = problem;\nSubLObject store = problem_store(prob);\nSubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);\nif (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {\nSubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);\nSubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);\nSubLObject inference = NIL;\nwhile (NIL != id) {\ninference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);\nif (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {\nif (NIL != problem_relevant_to_inferenceP(prob, inference)) {\nSubLObject inference_var = inference;\nSubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));\nSubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);\nSubLObject state = NIL;\nfor (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {\nSubLObject strategy = set_contents.do_set_contents_next(basis_object, state);\nif (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {\nresult = cons(strategy, result);\n}\nid = id_index.do_id_index_next_id(idx, NIL, id, state_var);\nstate_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);\nreturn nreverse(result);")
    public static final SubLObject problem_relevant_to_inferenceP(final SubLObject problem, final SubLObject inference) {
	return NIL != inference_datastructures_inference.valid_inference_p(inference) ? set.set_memberP(problem, inference_datastructures_inference.inference_relevant_problems(inference)) : NIL;
    }

    public static final SubLObject problem_relevant_to_inferenceP_alt(SubLObject problem, SubLObject inference) {
	return set.set_memberP(problem, inference_datastructures_inference.inference_relevant_problems(inference));
    }

    public static final SubLObject problem_relevant_to_only_one_inferenceP(final SubLObject problem) {
	SubLObject inference1 = NIL;
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
		SubLObject inference2;
		for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
		    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
		    inference2 = aref(vector_var, id);
		    if ((NIL == id_index_tombstone_p(inference2)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
			if (NIL != id_index_tombstone_p(inference2)) {
			    inference2 = $SKIP;
			}
			if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference2)) {
			    if (NIL != inference1) {
				return NIL;
			    }
			    inference1 = inference2;
			}
		    }
		}
	    }
	    final SubLObject idx_$21 = idx;
	    if (NIL == id_index_sparse_objects_empty_p(idx_$21)) {
		final SubLObject cdohash_table = id_index_sparse_objects(idx_$21);
		SubLObject id2 = NIL;
		SubLObject inference3 = NIL;
		final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
		try {
		    while (iteratorHasNext(cdohash_iterator)) {
			final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
			id2 = getEntryKey(cdohash_entry);
			inference3 = getEntryValue(cdohash_entry);
			if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference3)) {
			    if (NIL != inference1) {
				return NIL;
			    }
			    inference1 = inference3;
			}
		    }
		} finally {
		    releaseEntrySetIterator(cdohash_iterator);
		}
	    }
	}
	return list_utilities.sublisp_boolean(inference1);
    }

    // 
    // public static final SubLObject problem_relevant_to_only_one_inferenceP_alt(SubLObject problem) {
    // {
    // SubLObject inference1 = NIL;
    // SubLObject prob = problem;
    // SubLObject store = problem_store(prob);
    // SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
    // if (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {
    // {
    // SubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);
    // SubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);
    // SubLObject inference = NIL;
    // while (NIL != id) {
    // inference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);
    // if (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {
    // if (NIL != problem_relevant_to_inferenceP(prob, inference)) {
    // if (NIL != inference1) {
    // return NIL;
    // } else {
    // inference1 = inference;
    // }
    // }
    // }
    // id = id_index.do_id_index_next_id(idx, NIL, id, state_var);
    // state_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);
    // }
    // }
    // }
    // return list_utilities.sublisp_boolean(inference1);
    // }
    // }
    @LispMethod(comment = "public static final SubLObject problem_relevant_to_only_one_inferenceP_alt(SubLObject problem) {\nSubLObject inference1 = NIL;\nSubLObject prob = problem;\nSubLObject store = problem_store(prob);\nSubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);\nif (NIL == id_index.do_id_index_empty_p(idx, $SKIP)) {\nSubLObject id = id_index.do_id_index_next_id(idx, NIL, NIL, NIL);\nSubLObject state_var = id_index.do_id_index_next_state(idx, NIL, id, NIL);\nSubLObject inference = NIL;\nwhile (NIL != id) {\ninference = id_index.do_id_index_state_object(idx, $SKIP, id, state_var);\nif (NIL != id_index.do_id_index_id_and_object_validP(id, inference, $SKIP)) {\nif (NIL != problem_relevant_to_inferenceP(prob, inference)) {\nif (NIL != inference1) {\nreturn NIL;\n} else {\ninference1 = inference;\nid = id_index.do_id_index_next_id(idx, NIL, id, state_var);\nstate_var = id_index.do_id_index_next_state(idx, NIL, id, state_var);\nreturn list_utilities.sublisp_boolean(inference1);")
    public static final SubLObject problem_relevant_to_some_inferenceP(final SubLObject problem) {
	return list_utilities.sublisp_boolean(inference_datastructures_problem.first_problem_relevant_inference(problem));
    }

    public static final SubLObject problem_relevant_to_some_inferenceP_alt(SubLObject problem) {
	return list_utilities.sublisp_boolean(inference_datastructures_problem.first_problem_relevant_inference(problem));
    }

    public static final SubLObject problem_relevant_to_strategyP(final SubLObject problem, final SubLObject strategy) {
	return inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference_datastructures_strategy.strategy_inference(strategy));
    }

    /**
     *
     *
     * @return boolean; t iff PROBLEM is relevant to STRATEGY's inference.
     */
    @LispMethod(comment = "@return boolean; t iff PROBLEM is relevant to STRATEGY\'s inference.")
    public static final SubLObject problem_relevant_to_strategyP_alt(SubLObject problem, SubLObject strategy) {
	return inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference_datastructures_strategy.strategy_inference(strategy));
    }

    public static final SubLObject problem_sole_argument_link_of_type(final SubLObject problem, final SubLObject type) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	SubLObject first_link = NIL;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, type))) {
		if (NIL != first_link) {
		    Errors.error(inference_datastructures_problem.$str184$Found_more_than_one__a_argument_l, type, problem);
		} else {
		    first_link = link;
		}
	    }
	}
	if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == first_link)) {
	    Errors.error(inference_datastructures_problem.$str185$Expected__a_to_have_a__a_argument, problem, type);
	}
	return first_link;
    }

    public static final SubLObject problem_sole_argument_link_of_type_alt(SubLObject problem, SubLObject type) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject first_link = NIL;
		SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
		SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
		SubLObject state = NIL;
		for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		    {
			SubLObject link = set_contents.do_set_contents_next(basis_object, state);
			if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			    if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(link, type)) {
				if (NIL != first_link) {
				    Errors.error(inference_datastructures_problem.alt_str159$Found_more_than_one__a_argument_l, type, problem);
				} else {
				    first_link = link;
				}
			    }
			}
		    }
		}
		if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
		    if (NIL == first_link) {
			Errors.error(inference_datastructures_problem.alt_str160$Expected__a_to_have_a__a_argument, problem, type);
		    }
		}
		return first_link;
	    }
	}
    }

    public static final SubLObject problem_sole_clause(final SubLObject problem) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	final SubLObject query = inference_datastructures_problem.problem_query(problem);
	if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == list_utilities.singletonP(query))) {
	    Errors.error(inference_datastructures_problem.$str182$The_problem__S_did_not_have_a_sin, problem);
	}
	return inference_datastructures_problem.problem_query_sole_clause(query);
    }

    /**
     *
     *
     * @return contextualized-dnf-clause-p
     */
    @LispMethod(comment = "@return contextualized-dnf-clause-p")
    public static final SubLObject problem_sole_clause_alt(SubLObject problem) {
	{
	    final SubLThread thread = SubLProcess.currentSubLThread();
	    {
		SubLObject query = inference_datastructures_problem.problem_query(problem);
		if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
		    if (NIL == list_utilities.singletonP(query)) {
			Errors.error(inference_datastructures_problem.alt_str157$The_problem__S_did_not_have_a_sin, problem);
		    }
		}
		return inference_datastructures_problem.problem_query_sole_clause(query);
	    }
	}
    }

    public static final SubLObject problem_sole_dependent_link(final SubLObject problem) {
	SubLObject result = NIL;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject dependent_link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    dependent_link = set_contents.do_set_contents_next(basis_object, state);
	    if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
		if (NIL != result) {
		    return Errors.error(inference_datastructures_problem.$str189$_a_had_more_than_one_dependent_li, problem);
		}
		result = dependent_link;
	    }
	}
	if (NIL != result) {
	    return result;
	}
	return Errors.error(inference_datastructures_problem.$str190$_a_had_no_dependent_links, problem);
    }

    public static final SubLObject problem_sole_dependent_link_alt(SubLObject problem) {
	{
	    SubLObject result = NIL;
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject dependent_link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
			if (NIL != result) {
			    return Errors.error(inference_datastructures_problem.alt_str164$_a_had_more_than_one_dependent_li, problem);
			} else {
			    result = dependent_link;
			}
		    }
		}
	    }
	    if (NIL != result) {
		return result;
	    } else {
		return Errors.error(inference_datastructures_problem.alt_str165$_a_had_no_dependent_links, problem);
	    }
	}
    }

    public static final SubLObject problem_status(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_datastructures_problem.prob_status(problem);
    }

    public static final SubLObject problem_status_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return inference_datastructures_problem.prob_status(problem);
    }

    public static final SubLObject problem_store(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_datastructures_problem.prob_store(problem);
    }

    public static final SubLObject problem_store_all_modules(final SubLObject store) {
	assert NIL != inference_datastructures_problem_store.problem_store_p(store) : "! inference_datastructures_problem_store.problem_store_p(store) "
		+ ("inference_datastructures_problem_store.problem_store_p(store) " + "CommonSymbols.NIL != inference_datastructures_problem_store.problem_store_p(store) ") + store;
	final SubLObject module_counts = dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED);
	final SubLObject idx = inference_datastructures_problem_store.problem_store_problem_id_index(store);
	if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
	    final SubLObject idx_$26 = idx;
	    if (NIL == id_index_dense_objects_empty_p(idx_$26, $SKIP)) {
		final SubLObject vector_var = id_index_dense_objects(idx_$26);
		final SubLObject backwardP_var = NIL;
		SubLObject length;
		SubLObject v_iteration;
		SubLObject id;
		SubLObject problem;
		SubLObject cdolist_list_var;
		SubLObject tactic;
		SubLObject module;
		SubLObject module_name;
		for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
		    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
		    problem = aref(vector_var, id);
		    if ((NIL == id_index_tombstone_p(problem)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
			if (NIL != id_index_tombstone_p(problem)) {
			    problem = $SKIP;
			}
			cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
			tactic = NIL;
			tactic = cdolist_list_var.first();
			while (NIL != cdolist_list_var) {
			    module = inference_datastructures_tactic.tactic_hl_module(tactic);
			    module_name = inference_modules.hl_module_name(module);
			    dictionary_utilities.dictionary_increment(module_counts, module_name, UNPROVIDED);
			    cdolist_list_var = cdolist_list_var.rest();
			    tactic = cdolist_list_var.first();
			}
		    }
		}
	    }
	    final SubLObject idx_$27 = idx;
	    if (NIL == id_index_sparse_objects_empty_p(idx_$27)) {
		final SubLObject cdohash_table = id_index_sparse_objects(idx_$27);
		SubLObject id2 = NIL;
		SubLObject problem2 = NIL;
		final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
		try {
		    while (iteratorHasNext(cdohash_iterator)) {
			final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
			id2 = getEntryKey(cdohash_entry);
			problem2 = getEntryValue(cdohash_entry);
			SubLObject cdolist_list_var2 = inference_datastructures_problem.problem_tactics(problem2);
			SubLObject tactic2 = NIL;
			tactic2 = cdolist_list_var2.first();
			while (NIL != cdolist_list_var2) {
			    final SubLObject module2 = inference_datastructures_tactic.tactic_hl_module(tactic2);
			    final SubLObject module_name2 = inference_modules.hl_module_name(module2);
			    dictionary_utilities.dictionary_increment(module_counts, module_name2, UNPROVIDED);
			    cdolist_list_var2 = cdolist_list_var2.rest();
			    tactic2 = cdolist_list_var2.first();
			}
		    }
		} finally {
		    releaseEntrySetIterator(cdohash_iterator);
		}
	    }
	}
	return Sort.sort(dictionary_utilities.dictionary_to_alist(module_counts), symbol_function(inference_datastructures_problem.$sym220$_), symbol_function(CDR));
    }

    public static final SubLObject problem_store_all_modules_alt2(SubLObject store) {
	SubLTrampolineFile.checkType(store, PROBLEM_STORE_P);
	{
	    SubLObject module_counts = dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED);
	    SubLObject idx = inference_datastructures_problem_store.problem_store_problem_id_index(store);
	    if (NIL == do_id_index_empty_p(idx, $SKIP)) {
		{
		    SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
		    SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
		    SubLObject problem = NIL;
		    while (NIL != id) {
			problem = do_id_index_state_object(idx, $SKIP, id, state_var);
			if (NIL != do_id_index_id_and_object_validP(id, problem, $SKIP)) {
			    {
				SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
				SubLObject tactic = NIL;
				for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
				    {
					SubLObject module = inference_datastructures_tactic.tactic_hl_module(tactic);
					SubLObject module_name = inference_modules.hl_module_name(module);
					dictionary_utilities.dictionary_increment(module_counts, module_name, UNPROVIDED);
				    }
				}
			    }
			}
			id = do_id_index_next_id(idx, NIL, id, state_var);
			state_var = do_id_index_next_state(idx, NIL, id, state_var);
		    }
		}
	    }
	    return Sort.sort(dictionary_utilities.dictionary_to_alist(module_counts), symbol_function(inference_datastructures_problem.$sym194$_), symbol_function(CDR));
	}
    }

    public static final SubLObject problem_store_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return inference_datastructures_problem.prob_store(problem);
    }

    public static final SubLObject problem_suid(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_datastructures_problem.prob_suid(problem);
    }

    public static final SubLObject problem_suid_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return inference_datastructures_problem.prob_suid(problem);
    }

    public static final SubLObject problem_supported_problem_count(final SubLObject problem) {
	final SubLObject result = set.new_set(symbol_function(EQ), UNPROVIDED);
	final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	SubLObject supported_problem;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
		supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(link);
		if (NIL != supported_problem) {
		    set.set_add(supported_problem, result);
		}
	    }
	}
	return set.set_size(result);
    }

    /**
     *
     *
     * @return non-negative-integer-p; the number of unique problems that PROBLEM supports via one of its dependent links.
     */
    @LispMethod(comment = "@return non-negative-integer-p; the number of unique problems that PROBLEM supports via one of its dependent links.")
    public static final SubLObject problem_supported_problem_count_alt(SubLObject problem) {
	{
	    SubLObject result = set.new_set(symbol_function(EQ), UNPROVIDED);
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			{
			    SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(link);
			    if (NIL != supported_problem) {
				set.set_add(supported_problem, result);
			    }
			}
		    }
		}
	    }
	    return set.set_size(result);
	}
    }

    public static final SubLObject problem_supported_problems(final SubLObject problem) {
	SubLObject result = NIL;
	final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject link;
	SubLObject supported_problem;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    link = set_contents.do_set_contents_next(basis_object, state);
	    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
		supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(link);
		if (NIL != supported_problem) {
		    result = cons(supported_problem, result);
		}
	    }
	}
	return nreverse(result);
    }

    /**
     * Return a list of problems that PROBLEM supports via one of its dependent links.  May include duplicates.
     */
    @LispMethod(comment = "Return a list of problems that PROBLEM supports via one of its dependent links.  May include duplicates.")
    public static final SubLObject problem_supported_problems_alt(SubLObject problem) {
	{
	    SubLObject result = NIL;
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, link)) {
			{
			    SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(link);
			    if (NIL != supported_problem) {
				result = cons(supported_problem, result);
			    }
			}
		    }
		}
	    }
	    return nreverse(result);
	}
    }

    public static final SubLObject problem_tactic_count(final SubLObject problem) {
	final SubLObject tactics = inference_datastructures_problem.problem_tactics(problem);
	if (NIL != tactics) {
	    return number_utilities.f_1X(inference_datastructures_tactic.tactic_suid(tactics.first()));
	}
	return ZERO_INTEGER;
    }

    public static final SubLObject problem_tactic_count_alt(SubLObject problem) {
	{
	    SubLObject tactics = inference_datastructures_problem.problem_tactics(problem);
	    if (NIL != tactics) {
		return number_utilities.f_1X(inference_datastructures_tactic.tactic_suid(tactics.first()));
	    } else {
		return ZERO_INTEGER;
	    }
	}
    }

    public static final SubLObject problem_tactic_count_with_hl_module(final SubLObject problem, final SubLObject tactic_hl_module) {
	SubLObject count = ZERO_INTEGER;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if (NIL != inference_datastructures_problem.do_problem_tactics_hl_module_match(tactic, tactic_hl_module)) {
		count = add(count, ONE_INTEGER);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return count;
    }

    public static final SubLObject problem_tactic_count_with_hl_module_alt(SubLObject problem, SubLObject tactic_hl_module) {
	{
	    SubLObject count = ZERO_INTEGER;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if (NIL != inference_datastructures_problem.do_problem_tactics_hl_module_match(tactic, tactic_hl_module)) {
		    count = add(count, ONE_INTEGER);
		}
	    }
	    return count;
	}
    }

    public static final SubLObject problem_tactic_count_with_hl_module_and_status(final SubLObject problem, final SubLObject tactic_hl_module, final SubLObject tactic_status) {
	SubLObject count = ZERO_INTEGER;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if ((NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, tactic_status)) && (NIL != inference_datastructures_problem.do_problem_tactics_hl_module_match(tactic, tactic_hl_module))) {
		count = add(count, ONE_INTEGER);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return count;
    }

    public static final SubLObject problem_tactic_count_with_hl_module_and_status_alt(SubLObject problem, SubLObject tactic_hl_module, SubLObject tactic_status) {
	{
	    SubLObject count = ZERO_INTEGER;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if ((NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, tactic_status)) && (NIL != inference_datastructures_problem.do_problem_tactics_hl_module_match(tactic, tactic_hl_module))) {
		    count = add(count, ONE_INTEGER);
		}
	    }
	    return count;
	}
    }

    public static final SubLObject problem_tactic_of_type_with_status_count(final SubLObject problem, SubLObject type, SubLObject status) {
	if (type == UNPROVIDED) {
	    type = NIL;
	}
	if (status == UNPROVIDED) {
	    status = NIL;
	}
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	if (((NIL != type) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == inference_datastructures_problem.generalized_tactic_type_p(type))) {
	    throw new AssertionError(type);
	}
	if (((NIL != status) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == inference_datastructures_enumerated_types.tactic_status_p(status))) {
	    throw new AssertionError(status);
	}
	SubLObject count = ZERO_INTEGER;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, type)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, status))) {
		count = add(count, ONE_INTEGER);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return count;
    }

    /**
     *
     *
     * @return non-negative-integer-p; the number of tactics on PROBLEM with type TYPE and status STATUS
     */
    @LispMethod(comment = "@return non-negative-integer-p; the number of tactics on PROBLEM with type TYPE and status STATUS")
    public static final SubLObject problem_tactic_of_type_with_status_count_alt(SubLObject problem, SubLObject type, SubLObject status) {
	if (type == UNPROVIDED) {
	    type = NIL;
	}
	if (status == UNPROVIDED) {
	    status = NIL;
	}
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	if (NIL != type) {
	    SubLTrampolineFile.checkType(type, inference_datastructures_problem.GENERALIZED_TACTIC_TYPE_P);
	}
	if (NIL != status) {
	    SubLTrampolineFile.checkType(status, inference_datastructures_problem.TACTIC_STATUS_P);
	}
	{
	    SubLObject count = ZERO_INTEGER;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, type)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, status))) {
		    count = add(count, ONE_INTEGER);
		}
	    }
	    return count;
	}
    }

    public static final SubLObject problem_tactic_with_status_count(final SubLObject problem, SubLObject status) {
	if (status == UNPROVIDED) {
	    status = NIL;
	}
	return inference_datastructures_problem.problem_tactic_of_type_with_status_count(problem, NIL, status);
    }

    /**
     *
     *
     * @return non-negative-integer-p; the number of tactics on PROBLEM with status STATUS
     */
    @LispMethod(comment = "@return non-negative-integer-p; the number of tactics on PROBLEM with status STATUS")
    public static final SubLObject problem_tactic_with_status_count_alt(SubLObject problem, SubLObject status) {
	if (status == UNPROVIDED) {
	    status = NIL;
	}
	return inference_datastructures_problem.problem_tactic_of_type_with_status_count(problem, NIL, status);
    }

    public static final SubLObject problem_tactical_provability_status(final SubLObject problem) {
	final SubLObject status = inference_datastructures_problem.problem_dwimmed_status(problem);
	return inference_datastructures_enumerated_types.provability_status_from_problem_status(status);
    }

    public static final SubLObject problem_tactical_provability_status_alt(SubLObject problem) {
	{
	    SubLObject status = inference_datastructures_problem.problem_status(problem);
	    return inference_datastructures_enumerated_types.provability_status_from_problem_status(status);
	}
    }

    public static final SubLObject problem_tactical_status(final SubLObject problem) {
	final SubLObject status = inference_datastructures_problem.problem_status(problem);
	return inference_datastructures_enumerated_types.tactical_status_from_problem_status(status);
    }

    public static final SubLObject problem_tactical_status_alt(SubLObject problem) {
	{
	    SubLObject status = inference_datastructures_problem.problem_status(problem);
	    return inference_datastructures_enumerated_types.tactical_status_from_problem_status(status);
	}
    }

    public static final SubLObject problem_tactics(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	return inference_datastructures_problem.prob_tactics(problem);
    }

    public static final SubLObject problem_tactics_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	return inference_datastructures_problem.prob_tactics(problem);
    }

    public static final SubLObject problem_total_actual_removal_productivity(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	SubLObject total_productivity = ZERO_INTEGER;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL == inference_datastructures_tactic.tactic_discardedP(tactic))) {
		final SubLObject tactic_productivity = inference_worker.content_tactic_actual_productivity(tactic);
		total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, tactic_productivity);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return total_productivity;
    }

    /**
     *
     *
     * @return productivity-p; the sum of the actual productivities of all the non-discarded removal tactics on PROBLEM.
     */
    @LispMethod(comment = "@return productivity-p; the sum of the actual productivities of all the non-discarded removal tactics on PROBLEM.")
    public static final SubLObject problem_total_actual_removal_productivity_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	{
	    SubLObject total_productivity = ZERO_INTEGER;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if (NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) {
		    if (NIL == inference_datastructures_tactic.tactic_discardedP(tactic)) {
			{
			    SubLObject tactic_productivity = inference_worker.content_tactic_actual_productivity(tactic);
			    total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, tactic_productivity);
			}
		    }
		}
	    }
	    return total_productivity;
	}
    }

    public static final SubLObject problem_total_deductive_removal_productivity(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	SubLObject total_productivity = ZERO_INTEGER;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if (((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE))) && (NIL == inference_datastructures_tactic.abductive_tacticP(tactic))) {
		final SubLObject tactic_productivity = inference_datastructures_tactic.tactic_productivity(tactic);
		total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, tactic_productivity);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return total_productivity;
    }

    /**
     *
     *
     * @return productivity-p; the sum of the productivities of all the possible removal tactics on PROBLEM.
     */
    @LispMethod(comment = "@return productivity-p; the sum of the productivities of all the possible removal tactics on PROBLEM.")
    public static final SubLObject problem_total_deductive_removal_productivity_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	{
	    SubLObject total_productivity = ZERO_INTEGER;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE))) {
		    if (NIL == inference_datastructures_tactic.abductive_tacticP(tactic)) {
			{
			    SubLObject tactic_productivity = inference_datastructures_tactic.tactic_productivity(tactic);
			    total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, tactic_productivity);
			}
		    }
		}
	    }
	    return total_productivity;
	}
    }

    public static final SubLObject problem_total_removal_productivity(final SubLObject problem) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	SubLObject total_productivity = ZERO_INTEGER;
	SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	SubLObject tactic = NIL;
	tactic = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE))) {
		final SubLObject tactic_productivity = inference_datastructures_tactic.tactic_productivity(tactic);
		total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, tactic_productivity);
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    tactic = cdolist_list_var.first();
	}
	return total_productivity;
    }

    /**
     *
     *
     * @return productivity-p; the sum of the productivities of all the possible removal tactics on PROBLEM.
     */
    @LispMethod(comment = "@return productivity-p; the sum of the productivities of all the possible removal tactics on PROBLEM.")
    public static final SubLObject problem_total_removal_productivity_alt(SubLObject problem) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	{
	    SubLObject total_productivity = ZERO_INTEGER;
	    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
	    SubLObject tactic = NIL;
	    for (tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), tactic = cdolist_list_var.first()) {
		if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(tactic, $REMOVAL)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(tactic, $POSSIBLE))) {
		    {
			SubLObject tactic_productivity = inference_datastructures_tactic.tactic_productivity(tactic);
			total_productivity = inference_datastructures_enumerated_types.productivity_X(total_productivity, tactic_productivity);
		    }
		}
	    }
	    return total_productivity;
	}
    }

    public static final SubLObject problem_variables(final SubLObject problem) {
	return inference_datastructures_problem_query.problem_query_variables(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject problem_variables_alt(SubLObject problem) {
	return inference_datastructures_problem_query.problem_query_variables(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject proof_has_statusP(final SubLObject proof, final SubLObject status) {
	if (NIL == status) {
	    return T;
	}
	return eq(status, inference_datastructures_proof.proof_status(proof));
    }

    public static final SubLObject proof_has_statusP_alt(SubLObject proof, SubLObject status) {
	if (NIL == status) {
	    return T;
	} else {
	    return eq(status, inference_datastructures_proof.proof_status(proof));
	}
    }

    public static final SubLObject remove_problem_argument_link(final SubLObject problem, final SubLObject argument_link) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_problem_link.problem_link_p(argument_link) : "! inference_datastructures_problem_link.problem_link_p(argument_link) "
		+ ("inference_datastructures_problem_link.problem_link_p(argument_link) " + "CommonSymbols.NIL != inference_datastructures_problem_link.problem_link_p(argument_link) ") + argument_link;
	inference_datastructures_problem._csetf_prob_argument_links(problem, set_contents.set_contents_delete(argument_link, inference_datastructures_problem.prob_argument_links(problem), symbol_function(EQ)));
	return problem;
    }

    /**
     * Removes ARGUMENT-LINK from _below_ PROBLEM.
     */
    @LispMethod(comment = "Removes ARGUMENT-LINK from _below_ PROBLEM.")
    public static final SubLObject remove_problem_argument_link_alt(SubLObject problem, SubLObject argument_link) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(argument_link, PROBLEM_LINK_P);
	inference_datastructures_problem._csetf_prob_argument_links(problem, set_contents.set_contents_delete(argument_link, inference_datastructures_problem.prob_argument_links(problem), symbol_function(EQ)));
	return problem;
    }

    public static final SubLObject remove_problem_dependent_link(final SubLObject problem, final SubLObject dependent_link) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_problem_link.problem_link_p(dependent_link) : "! inference_datastructures_problem_link.problem_link_p(dependent_link) "
		+ ("inference_datastructures_problem_link.problem_link_p(dependent_link) " + "CommonSymbols.NIL != inference_datastructures_problem_link.problem_link_p(dependent_link) ") + dependent_link;
	inference_datastructures_problem._csetf_prob_dependent_links(problem, set_contents.set_contents_delete(dependent_link, inference_datastructures_problem.prob_dependent_links(problem), symbol_function(EQ)));
	return problem;
    }

    /**
     * Removes DEPENDENT-LINK from _above_ PROBLEM.
     */
    @LispMethod(comment = "Removes DEPENDENT-LINK from _above_ PROBLEM.")
    public static final SubLObject remove_problem_dependent_link_alt(SubLObject problem, SubLObject dependent_link) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(dependent_link, PROBLEM_LINK_P);
	inference_datastructures_problem._csetf_prob_dependent_links(problem, set_contents.set_contents_delete(dependent_link, inference_datastructures_problem.prob_dependent_links(problem), symbol_function(EQ)));
	return problem;
    }

    public static final SubLObject remove_problem_proof(final SubLObject problem, final SubLObject proof) {
	final SubLObject v_bindings = inference_datastructures_proof.proof_bindings(proof);
	return inference_datastructures_problem.remove_problem_proof_with_bindings(problem, proof, v_bindings);
    }

    public static final SubLObject remove_problem_proof_alt(SubLObject problem, SubLObject proof) {
	{
	    SubLObject v_bindings = inference_datastructures_proof.proof_bindings(proof);
	    return inference_datastructures_problem.remove_problem_proof_with_bindings(problem, proof, v_bindings);
	}
    }

    public static final SubLObject remove_problem_proof_with_bindings(final SubLObject problem, final SubLObject proof, final SubLObject v_bindings) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_proof.proof_p(proof) : "! inference_datastructures_proof.proof_p(proof) " + ("inference_datastructures_proof.proof_p(proof) " + "CommonSymbols.NIL != inference_datastructures_proof.proof_p(proof) ") + proof;
	assert NIL != bindings.bindings_p(v_bindings) : "! bindings.bindings_p(v_bindings) " + ("bindings.bindings_p(v_bindings) " + "CommonSymbols.NIL != bindings.bindings_p(v_bindings) ") + v_bindings;
	SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject basis_object;
	SubLObject state;
	SubLObject join_ordered_link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    join_ordered_link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, join_ordered_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(join_ordered_link, $JOIN_ORDERED))) {
		inference_worker_join_ordered.remove_join_ordered_link_proof_both_ways(join_ordered_link, proof, v_bindings);
	    }
	}
	set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	SubLObject join_link;
	for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object,
		state); state = set_contents.do_set_contents_update_state(state)) {
	    join_link = set_contents.do_set_contents_next(basis_object, state);
	    if ((NIL != set_contents.do_set_contents_element_validP(state, join_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(join_link, $JOIN))) {
		inference_worker_join.remove_join_link_proof_both_ways(join_link, proof, v_bindings);
	    }
	}
	final SubLObject index = inference_datastructures_problem.prob_proof_bindings_index(problem);
	final SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	final SubLObject updated = list_utilities.delete_first(proof, existing, symbol_function(EQ));
	if (!existing.eql(updated)) {
	    if (NIL == updated) {
		inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.dictionary_contents_delete(index, v_bindings, symbol_function(EQUAL)));
	    } else {
		inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, updated, symbol_function(EQUAL)));
	    }
	}
	return problem;
    }

    public static final SubLObject remove_problem_proof_with_bindings_alt(SubLObject problem, SubLObject proof, SubLObject v_bindings) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(proof, PROOF_P);
	SubLTrampolineFile.checkType(v_bindings, inference_datastructures_problem.BINDINGS_P);
	{
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject join_ordered_link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, join_ordered_link)) {
			if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(join_ordered_link, $JOIN_ORDERED)) {
			    inference_worker_join_ordered.remove_join_ordered_link_proof_both_ways(join_ordered_link, proof, v_bindings);
			}
		    }
		}
	    }
	}
	{
	    SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
	    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
	    SubLObject state = NIL;
	    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
		{
		    SubLObject join_link = set_contents.do_set_contents_next(basis_object, state);
		    if (NIL != set_contents.do_set_contents_element_validP(state, join_link)) {
			if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(join_link, $JOIN)) {
			    inference_worker_join.remove_join_link_proof_both_ways(join_link, proof, v_bindings);
			}
		    }
		}
	    }
	}
	{
	    SubLObject index = inference_datastructures_problem.prob_proof_bindings_index(problem);
	    SubLObject existing = dictionary_contents.dictionary_contents_lookup_without_values(index, v_bindings, symbol_function(EQUAL), UNPROVIDED);
	    SubLObject updated = list_utilities.delete_first(proof, existing, symbol_function(EQ));
	    if (existing != updated) {
		if (NIL == updated) {
		    inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.dictionary_contents_delete(index, v_bindings, symbol_function(EQUAL)));
		} else {
		    inference_datastructures_problem._csetf_prob_proof_bindings_index(problem, dictionary_contents.dictionary_contents_enter(index, v_bindings, updated, symbol_function(EQUAL)));
		}
	    }
	}
	return problem;
    }

    public static final SubLObject remove_problem_tactic(final SubLObject problem, final SubLObject tactic) {
	assert NIL != inference_datastructures_tactic.tactic_p(tactic) : "! inference_datastructures_tactic.tactic_p(tactic) " + ("inference_datastructures_tactic.tactic_p(tactic) " + "CommonSymbols.NIL != inference_datastructures_tactic.tactic_p(tactic) ") + tactic;
	inference_datastructures_problem._csetf_prob_tactics(problem, delete(tactic, inference_datastructures_problem.prob_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
	return problem;
    }

    public static final SubLObject remove_problem_tactic_alt(SubLObject problem, SubLObject tactic) {
	SubLTrampolineFile.checkType(tactic, TACTIC_P);
	inference_datastructures_problem._csetf_prob_tactics(problem, delete(tactic, inference_datastructures_problem.prob_tactics(problem), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
	return problem;
    }

    public static final SubLObject set_problem_free_hl_vars(final SubLObject problem, final SubLObject free_hl_vars) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	inference_datastructures_problem._csetf_prob_free_hl_vars(problem, free_hl_vars);
	return problem;
    }

    public static final SubLObject set_problem_min_depth(final SubLObject problem, final SubLObject depth) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != subl_promotions.non_negative_integer_p(depth) : "! subl_promotions.non_negative_integer_p(depth) " + ("subl_promotions.non_negative_integer_p(depth) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(depth) ") + depth;
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	final SubLObject hash = inference_datastructures_problem_store.problem_store_min_depth_index(store);
	sethash(problem, hash, depth);
	return problem;
    }

    /**
     * Primitively sets PROBLEM's max depth to DEPTH.
     */
    @LispMethod(comment = "Primitively sets PROBLEM\'s max depth to DEPTH.")
    public static final SubLObject set_problem_min_depth_alt(SubLObject problem, SubLObject depth) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(depth, NON_NEGATIVE_INTEGER_P);
	{
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject hash = inference_datastructures_problem_store.problem_store_min_depth_index(store);
	    sethash(problem, hash, depth);
	}
	return problem;
    }

    public static final SubLObject set_problem_min_proof_depth(final SubLObject problem, final SubLObject inference, final SubLObject depth) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_inference.inference_p(inference) : "! inference_datastructures_inference.inference_p(inference) " + ("inference_datastructures_inference.inference_p(inference) " + "CommonSymbols.NIL != inference_datastructures_inference.inference_p(inference) ")
		+ inference;
	assert NIL != subl_promotions.non_negative_integer_p(depth) : "! subl_promotions.non_negative_integer_p(depth) " + ("subl_promotions.non_negative_integer_p(depth) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(depth) ") + depth;
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_proof_depth_index(store), inference, UNPROVIDED);
	if (NIL == hash) {
	    hash = make_hash_table(ONE_INTEGER, symbol_function(EQ), UNPROVIDED);
	    dictionary.dictionary_enter(inference_datastructures_problem_store.problem_store_min_proof_depth_index(store), inference, hash);
	}
	sethash(problem, hash, depth);
	return problem;
    }

    /**
     * Primitively sets PROBLEM's proof depth wrt INFERENCE to DEPTH.
     */
    @LispMethod(comment = "Primitively sets PROBLEM\'s proof depth wrt INFERENCE to DEPTH.")
    public static final SubLObject set_problem_min_proof_depth_alt(SubLObject problem, SubLObject inference, SubLObject depth) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(inference, INFERENCE_P);
	SubLTrampolineFile.checkType(depth, NON_NEGATIVE_INTEGER_P);
	{
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_proof_depth_index(store), inference, UNPROVIDED);
	    if (NIL == hash) {
		hash = make_hash_table(inference_datastructures_problem_store.problem_store_problem_count(store), symbol_function(EQ), UNPROVIDED);
		dictionary.dictionary_enter(inference_datastructures_problem_store.problem_store_min_proof_depth_index(store), inference, hash);
	    }
	    sethash(problem, hash, depth);
	}
	return problem;
    }

    public static final SubLObject set_problem_min_transformation_depth(final SubLObject problem, final SubLObject inference, final SubLObject depth) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_inference.inference_p(inference) : "! inference_datastructures_inference.inference_p(inference) " + ("inference_datastructures_inference.inference_p(inference) " + "CommonSymbols.NIL != inference_datastructures_inference.inference_p(inference) ")
		+ inference;
	assert NIL != subl_promotions.non_negative_integer_p(depth) : "! subl_promotions.non_negative_integer_p(depth) " + ("subl_promotions.non_negative_integer_p(depth) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(depth) ") + depth;
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_index(store), inference, UNPROVIDED);
	if (NIL == hash) {
	    hash = make_hash_table(ONE_INTEGER, symbol_function(EQ), UNPROVIDED);
	    dictionary.dictionary_enter(inference_datastructures_problem_store.problem_store_min_transformation_depth_index(store), inference, hash);
	}
	sethash(problem, hash, depth);
	inference_datastructures_inference.inference_note_transformation_depth(inference, depth);
	return problem;
    }

    /**
     * Primitively sets PROBLEM's transformation depth wrt INFERENCE to DEPTH.
     */
    @LispMethod(comment = "Primitively sets PROBLEM\'s transformation depth wrt INFERENCE to DEPTH.")
    public static final SubLObject set_problem_min_transformation_depth_alt(SubLObject problem, SubLObject inference, SubLObject depth) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(inference, INFERENCE_P);
	SubLTrampolineFile.checkType(depth, NON_NEGATIVE_INTEGER_P);
	{
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_index(store), inference, UNPROVIDED);
	    if (NIL == hash) {
		hash = make_hash_table(inference_datastructures_problem_store.problem_store_problem_count(store), symbol_function(EQ), UNPROVIDED);
		dictionary.dictionary_enter(inference_datastructures_problem_store.problem_store_min_transformation_depth_index(store), inference, hash);
	    }
	    sethash(problem, hash, depth);
	}
	inference_datastructures_inference.inference_note_transformation_depth(inference, depth);
	return problem;
    }

    public static final SubLObject set_problem_min_transformation_depth_signature(final SubLObject problem, final SubLObject inference, final SubLObject pqds) {
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_inference.inference_p(inference) : "! inference_datastructures_inference.inference_p(inference) " + ("inference_datastructures_inference.inference_p(inference) " + "CommonSymbols.NIL != inference_datastructures_inference.inference_p(inference) ")
		+ inference;
	assert NIL != inference_min_transformation_depth.problem_query_depth_signature_p(pqds) : "! inference_min_transformation_depth.problem_query_depth_signature_p(pqds) "
		+ ("inference_min_transformation_depth.problem_query_depth_signature_p(pqds) " + "CommonSymbols.NIL != inference_min_transformation_depth.problem_query_depth_signature_p(pqds) ") + pqds;
	final SubLObject store = inference_datastructures_problem.problem_store(problem);
	SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_signature_index(store), inference, UNPROVIDED);
	if (NIL == hash) {
	    hash = make_hash_table(ONE_INTEGER, symbol_function(EQ), UNPROVIDED);
	    dictionary.dictionary_enter(inference_datastructures_problem_store.problem_store_min_transformation_depth_signature_index(store), inference, hash);
	}
	sethash(problem, hash, pqds);
	final SubLObject depth = inference_min_transformation_depth.min_transformation_depth_from_signature(pqds);
	inference_datastructures_inference.inference_note_transformation_depth(inference, depth);
	return problem;
    }

    /**
     * Primitively sets PROBLEM's transformation depth signature wrt INFERENCE to PQDS.
     */
    @LispMethod(comment = "Primitively sets PROBLEM\'s transformation depth signature wrt INFERENCE to PQDS.")
    public static final SubLObject set_problem_min_transformation_depth_signature_alt(SubLObject problem, SubLObject inference, SubLObject pqds) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(inference, INFERENCE_P);
	SubLTrampolineFile.checkType(pqds, inference_datastructures_problem.PROBLEM_QUERY_DEPTH_SIGNATURE_P);
	{
	    SubLObject store = inference_datastructures_problem.problem_store(problem);
	    SubLObject hash = dictionary.dictionary_lookup_without_values(inference_datastructures_problem_store.problem_store_min_transformation_depth_signature_index(store), inference, UNPROVIDED);
	    if (NIL == hash) {
		hash = make_hash_table(inference_datastructures_problem_store.problem_store_problem_count(store), symbol_function(EQ), UNPROVIDED);
		dictionary.dictionary_enter(inference_datastructures_problem_store.problem_store_min_transformation_depth_signature_index(store), inference, hash);
	    }
	    sethash(problem, hash, pqds);
	}
	{
	    SubLObject depth = inference_min_transformation_depth.min_transformation_depth_from_signature(pqds);
	    inference_datastructures_inference.inference_note_transformation_depth(inference, depth);
	}
	return problem;
    }

    public static final SubLObject set_problem_status(final SubLObject problem, final SubLObject status) {
	final SubLThread thread = SubLProcess.currentSubLThread();
	assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
	assert NIL != inference_datastructures_enumerated_types.problem_status_p(status) : "! inference_datastructures_enumerated_types.problem_status_p(status) "
		+ ("inference_datastructures_enumerated_types.problem_status_p(status) " + "CommonSymbols.NIL != inference_datastructures_enumerated_types.problem_status_p(status) ") + status;
	if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL != inference_datastructures_enumerated_types.good_problem_status_p(status))) {
	    Errors.error(inference_datastructures_problem.$str176$poking_good_status__s_into_proble, status, problem);
	}
	inference_datastructures_problem._csetf_prob_status(problem, status);
	return problem;
    }

    public static final SubLObject set_problem_status_alt(SubLObject problem, SubLObject status) {
	SubLTrampolineFile.checkType(problem, PROBLEM_P);
	SubLTrampolineFile.checkType(status, inference_datastructures_problem.PROBLEM_STATUS_P);
	inference_datastructures_problem._csetf_prob_status(problem, status);
	return problem;
    }

    public static final SubLObject set_root_problem_min_transformation_depth_signature(final SubLObject problem, final SubLObject inference) {
	final SubLObject problem_query = inference_datastructures_problem.problem_query(problem);
	final SubLObject initial_pqds = inference_min_transformation_depth.new_initial_pqds(problem_query, UNPROVIDED);
	return inference_datastructures_problem.set_problem_min_transformation_depth_signature(problem, inference, initial_pqds);
    }

    public static final SubLObject set_root_problem_min_transformation_depth_signature_alt(SubLObject problem, SubLObject inference) {
	{
	    SubLObject problem_query = inference_datastructures_problem.problem_query(problem);
	    SubLObject initial_pqds = inference_min_transformation_depth.new_initial_pqds(problem_query, UNPROVIDED);
	    return inference_datastructures_problem.set_problem_min_transformation_depth_signature(problem, inference, initial_pqds);
	}
    }

    public static final SubLObject setup_inference_datastructures_problem_file() {
	register_method($print_object_method_table$.getGlobalValue(), inference_datastructures_problem.$dtp_problem$.getGlobalValue(), symbol_function(inference_datastructures_problem.PROBLEM_PRINT_FUNCTION_TRAMPOLINE));
	SubLSpecialOperatorDeclarations.proclaim(inference_datastructures_problem.$list9);
	def_csetf(inference_datastructures_problem.PROB_SUID, inference_datastructures_problem._CSETF_PROB_SUID);
	def_csetf(inference_datastructures_problem.PROB_STORE, inference_datastructures_problem._CSETF_PROB_STORE);
	def_csetf(inference_datastructures_problem.PROB_QUERY, inference_datastructures_problem._CSETF_PROB_QUERY);
	def_csetf(inference_datastructures_problem.PROB_FREE_HL_VARS, inference_datastructures_problem._CSETF_PROB_FREE_HL_VARS);
	def_csetf(inference_datastructures_problem.PROB_STATUS, inference_datastructures_problem._CSETF_PROB_STATUS);
	def_csetf(inference_datastructures_problem.PROB_DEPENDENT_LINKS, inference_datastructures_problem._CSETF_PROB_DEPENDENT_LINKS);
	def_csetf(inference_datastructures_problem.PROB_ARGUMENT_LINKS, inference_datastructures_problem._CSETF_PROB_ARGUMENT_LINKS);
	def_csetf(inference_datastructures_problem.PROB_TACTICS, inference_datastructures_problem._CSETF_PROB_TACTICS);
	def_csetf(inference_datastructures_problem.PROB_PROOF_BINDINGS_INDEX, inference_datastructures_problem._CSETF_PROB_PROOF_BINDINGS_INDEX);
	def_csetf(inference_datastructures_problem.PROB_ARGUMENT_LINK_BINDINGS_INDEX, inference_datastructures_problem._CSETF_PROB_ARGUMENT_LINK_BINDINGS_INDEX);
	def_csetf(inference_datastructures_problem.PROB_BACKCHAIN_REQUIRED, inference_datastructures_problem._CSETF_PROB_BACKCHAIN_REQUIRED);
	def_csetf(inference_datastructures_problem.PROB_MEMOIZATION_STATE, inference_datastructures_problem._CSETF_PROB_MEMOIZATION_STATE);
	identity(PROBLEM);
	register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), inference_datastructures_problem.$dtp_problem$.getGlobalValue(), symbol_function(inference_datastructures_problem.VISIT_DEFSTRUCT_OBJECT_PROBLEM_METHOD));
	register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), inference_datastructures_problem.$dtp_problem$.getGlobalValue(), symbol_function(inference_datastructures_problem.SXHASH_PROBLEM_METHOD));
	declare_defglobal(inference_datastructures_problem.$empty_clauses$);
	register_macro_helper(inference_datastructures_problem.PROBLEM_MEMOIZATION_STATE, inference_datastructures_problem.WITH_PROBLEM_MEMOIZATION_STATE);
	register_macro_helper(inference_datastructures_problem.PROBLEM_TACTICS, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_STATUS_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_COMPLETENESS_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_PREFERENCE_LEVEL_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_PRODUCTIVITY_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_HL_MODULE_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_TYPE_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.PROBLEM_ARGUMENT_LINKS, inference_datastructures_problem.DO_PROBLEM_ARGUMENT_LINKS);
	register_macro_helper(inference_datastructures_problem.PROBLEM_DEPENDENT_LINKS, inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS);
	register_macro_helper(inference_datastructures_problem.PROBLEM_PROOF_BINDINGS_INDEX, inference_datastructures_problem.DO_PROBLEM_PROOFS);
	register_macro_helper(inference_datastructures_problem.$sym150$PROOF_HAS_STATUS_, inference_datastructures_problem.DO_PROBLEM_PROOFS);
	register_method($print_object_method_table$.getGlobalValue(), inference_datastructures_problem.$dtp_problem$.getGlobalValue(), symbol_function(inference_datastructures_problem.PROBLEM_PRINT_FUNCTION_TRAMPOLINE));
	def_csetf(inference_datastructures_problem.PROB_SUID, inference_datastructures_problem._CSETF_PROB_SUID);
	def_csetf(inference_datastructures_problem.PROB_STORE, inference_datastructures_problem._CSETF_PROB_STORE);
	def_csetf(inference_datastructures_problem.PROB_QUERY, inference_datastructures_problem._CSETF_PROB_QUERY);
	def_csetf(inference_datastructures_problem.PROB_STATUS, inference_datastructures_problem._CSETF_PROB_STATUS);
	def_csetf(inference_datastructures_problem.PROB_DEPENDENT_LINKS, inference_datastructures_problem._CSETF_PROB_DEPENDENT_LINKS);
	def_csetf(inference_datastructures_problem.PROB_ARGUMENT_LINKS, inference_datastructures_problem._CSETF_PROB_ARGUMENT_LINKS);
	def_csetf(inference_datastructures_problem.PROB_TACTICS, inference_datastructures_problem._CSETF_PROB_TACTICS);
	def_csetf(inference_datastructures_problem.PROB_PROOF_BINDINGS_INDEX, inference_datastructures_problem._CSETF_PROB_PROOF_BINDINGS_INDEX);
	def_csetf(inference_datastructures_problem.PROB_ARGUMENT_LINK_BINDINGS_INDEX, inference_datastructures_problem._CSETF_PROB_ARGUMENT_LINK_BINDINGS_INDEX);
	identity(PROBLEM);
	register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), inference_datastructures_problem.$dtp_problem$.getGlobalValue(), symbol_function(inference_datastructures_problem.SXHASH_PROBLEM_METHOD));
	declare_defglobal(inference_datastructures_problem.$empty_clauses$);
	register_macro_helper(inference_datastructures_problem.PROBLEM_TACTICS, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_STATUS_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_COMPLETENESS_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_PREFERENCE_LEVEL_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_PRODUCTIVITY_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_HL_MODULE_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.DO_PROBLEM_TACTICS_TYPE_MATCH, inference_datastructures_problem.DO_PROBLEM_TACTICS);
	register_macro_helper(inference_datastructures_problem.PROBLEM_ARGUMENT_LINKS, inference_datastructures_problem.DO_PROBLEM_ARGUMENT_LINKS);
	register_macro_helper(inference_datastructures_problem.PROBLEM_DEPENDENT_LINKS, inference_datastructures_problem.DO_PROBLEM_DEPENDENT_LINKS);
	register_macro_helper(inference_datastructures_problem.PROBLEM_PROOF_BINDINGS_INDEX, inference_datastructures_problem.DO_PROBLEM_PROOFS);
	register_macro_helper(inference_datastructures_problem.$sym126$PROOF_HAS_STATUS_, inference_datastructures_problem.DO_PROBLEM_PROOFS);
	return NIL;
    }

    public static final SubLObject single_clause_problem_p(final SubLObject v_object) {
	if (NIL != inference_datastructures_problem.problem_p(v_object)) {
	    final SubLObject query = inference_datastructures_problem.problem_query(v_object);
	    return inference_datastructures_problem_query.single_clause_problem_query_p(query);
	}
	return NIL;
    }

    public static final SubLObject single_clause_problem_p_alt(SubLObject v_object) {
	if (NIL != inference_datastructures_problem.problem_p(v_object)) {
	    {
		SubLObject query = inference_datastructures_problem.problem_query(v_object);
		return inference_datastructures_problem_query.single_clause_problem_query_p(query);
	    }
	}
	return NIL;
    }

    public static final SubLObject single_literal_problem_atomic_sentence(final SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_atomic_sentence(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject single_literal_problem_atomic_sentence_alt(SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_atomic_sentence(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject single_literal_problem_mt(final SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_mt(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject single_literal_problem_mt_alt(SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_mt(inference_datastructures_problem.problem_query(problem));
    }

    /**
     *
     *
     * @return boolean; whether OBJECT is a problem whose query consists of
    a single contextualized literal (either positive or negative).
     */
    @LispMethod(comment = "@return boolean; whether OBJECT is a problem whose query consists of\r\na single contextualized literal (either positive or negative).")
    public static final SubLObject single_literal_problem_p(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.problem_p(v_object)) && (NIL != inference_datastructures_problem_query.problem_query_has_single_literal_p(inference_datastructures_problem.problem_query(v_object))));
    }

    public static final SubLObject single_literal_problem_predicate(final SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_predicate(inference_datastructures_problem.problem_query(problem));
    }

    /**
     * Assuming PROBLEM is a @xref single-literal-problem-p,
     * returns the predicate of its single contextualized literal.
     */
    @LispMethod(comment = "Assuming PROBLEM is a @xref single-literal-problem-p,\r\nreturns the predicate of its single contextualized literal.\nAssuming PROBLEM is a @xref single-literal-problem-p,\nreturns the predicate of its single contextualized literal.")
    public static final SubLObject single_literal_problem_predicate_alt(SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_predicate(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject single_literal_problem_sense(final SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_sense(inference_datastructures_problem.problem_query(problem));
    }

    /**
     *
     *
     * @return sense-p;  Assumes PROBLEM is a single literal problem, and returns the problem's query's sense.
     */
    @LispMethod(comment = "@return sense-p;  Assumes PROBLEM is a single literal problem, and returns the problem\'s query\'s sense.")
    public static final SubLObject single_literal_problem_sense_alt(SubLObject problem) {
	return inference_datastructures_problem_query.single_literal_problem_query_sense(inference_datastructures_problem.problem_query(problem));
    }

    public static final SubLObject single_literal_problem_with_predicate_p(final SubLObject v_object, final SubLObject predicate) {
	if (NIL != inference_datastructures_problem.single_literal_problem_p(v_object)) {
	    return eql(predicate, inference_datastructures_problem.single_literal_problem_predicate(v_object));
	}
	return NIL;
    }

    public static final SubLObject single_literal_problem_with_predicate_p_alt(SubLObject v_object, SubLObject predicate) {
	if (NIL != inference_datastructures_problem.single_literal_problem_p(v_object)) {
	    return eq(predicate, inference_datastructures_problem.single_literal_problem_predicate(v_object));
	}
	return NIL;
    }

    public static final SubLObject split_problem_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.conjunctive_problem_p(v_object)) && (NIL == inference_worker_split.all_literals_connected_by_shared_varsP(inference_datastructures_problem.problem_query(v_object).first())));
    }

    /**
     *
     *
     * @return booleanp; t iff OBJECT is a disconnected conjunctive problem.
     */
    @LispMethod(comment = "@return booleanp; t iff OBJECT is a disconnected conjunctive problem.")
    public static final SubLObject split_problem_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.conjunctive_problem_p(v_object)) && (NIL == inference_worker_split.all_literals_connected_by_shared_varsP(inference_datastructures_problem.problem_query(v_object).first())));
    }

    public static final SubLObject sxhash_problem_method(final SubLObject v_object) {
	return inference_datastructures_problem.prob_suid(v_object);
    }

    public static final SubLObject sxhash_problem_method_alt(SubLObject v_object) {
	return inference_datastructures_problem.prob_suid(v_object);
    }

    public static final SubLObject tactic_matches_any_of_type_specsP(final SubLObject tactic, final SubLObject type_specs) {
	SubLObject cdolist_list_var = type_specs;
	SubLObject type_spec = NIL;
	type_spec = cdolist_list_var.first();
	while (NIL != cdolist_list_var) {
	    if (NIL != inference_datastructures_problem.tactic_matches_type_specP(tactic, type_spec)) {
		return T;
	    }
	    cdolist_list_var = cdolist_list_var.rest();
	    type_spec = cdolist_list_var.first();
	}
	return NIL;
    }

    public static final SubLObject tactic_matches_any_of_type_specsP_alt(SubLObject tactic, SubLObject type_specs) {
	{
	    SubLObject cdolist_list_var = type_specs;
	    SubLObject type_spec = NIL;
	    for (type_spec = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest(), type_spec = cdolist_list_var.first()) {
		if (NIL != inference_datastructures_problem.tactic_matches_type_specP(tactic, type_spec)) {
		    return T;
		}
	    }
	}
	return NIL;
    }

    public static final SubLObject tactic_matches_type_specP(final SubLObject tactic, final SubLObject type_spec) {
	if (NIL == type_spec) {
	    return T;
	}
	assert NIL != inference_datastructures_problem.generalized_tactic_type_p(type_spec) : "! inference_datastructures_problem.generalized_tactic_type_p(type_spec) "
		+ ("inference_datastructures_problem.generalized_tactic_type_p(type_spec) " + "CommonSymbols.NIL != inference_datastructures_problem.generalized_tactic_type_p(type_spec) ") + type_spec;
	if (type_spec.eql($NON_TRANSFORMATION)) {
	    return makeBoolean(NIL == inference_worker_transformation.transformation_tactic_p(tactic));
	}
	if (type_spec.eql($GENERALIZED_REMOVAL)) {
	    return inference_worker_removal.generalized_removal_tactic_p(tactic);
	}
	if (type_spec.eql(inference_datastructures_problem.$GENERALIZED_REMOVAL_OR_REWRITE)) {
	    return makeBoolean((NIL != inference_worker_removal.generalized_removal_tactic_p(tactic)) || (NIL != inference_worker_rewrite.rewrite_tactic_p(tactic)));
	}
	if (type_spec.eql($CONNECTED_CONJUNCTION)) {
	    return inference_worker.connected_conjunction_tactic_p(tactic);
	}
	if (type_spec.eql($CONJUNCTIVE)) {
	    return inference_worker.conjunctive_tactic_p(tactic);
	}
	if (type_spec.eql($DISJUNCTIVE)) {
	    return inference_worker.disjunctive_tactic_p(tactic);
	}
	if (type_spec.eql($LOGICAL)) {
	    return inference_worker.logical_tactic_p(tactic);
	}
	if (type_spec.eql($LOGICAL_CONJUNCTIVE)) {
	    return inference_worker.logical_conjunctive_tactic_p(tactic);
	}
	if (type_spec.eql($STRUCTURAL_CONJUNCTIVE)) {
	    return inference_worker.logical_conjunctive_tactic_p(tactic);
	}
	if (type_spec.eql($META_STRUCTURAL)) {
	    return inference_worker.meta_structural_tactic_p(tactic);
	}
	if (type_spec.eql($CONTENT)) {
	    return inference_worker.content_tactic_p(tactic);
	}
	if (type_spec.eql($UNION)) {
	    return inference_worker_union.union_tactic_p(tactic);
	}
	if (type_spec.eql($SPLIT)) {
	    return inference_worker_split.split_tactic_p(tactic);
	}
	if (type_spec.eql($JOIN_ORDERED)) {
	    return inference_worker_join_ordered.join_ordered_tactic_p(tactic);
	}
	if (type_spec.eql($JOIN)) {
	    return inference_worker_join.join_tactic_p(tactic);
	}
	return eq(type_spec, inference_datastructures_tactic.tactic_type(tactic));
    }

    public static final SubLObject tactic_matches_type_specP_alt(SubLObject tactic, SubLObject type_spec) {
	if (NIL == type_spec) {
	    return T;
	} else {
	    SubLTrampolineFile.checkType(type_spec, inference_datastructures_problem.GENERALIZED_TACTIC_TYPE_P);
	    {
		SubLObject pcase_var = type_spec;
		if (pcase_var.eql($NON_TRANSFORMATION)) {
		    return makeBoolean(NIL == inference_worker_transformation.transformation_tactic_p(tactic));
		} else if (pcase_var.eql($GENERALIZED_REMOVAL)) {
		    return inference_worker_removal.generalized_removal_tactic_p(tactic);
		} else if (pcase_var.eql(inference_datastructures_problem.$GENERALIZED_REMOVAL_OR_REWRITE)) {
		    return makeBoolean((NIL != inference_worker_removal.generalized_removal_tactic_p(tactic)) || (NIL != inference_worker_rewrite.rewrite_tactic_p(tactic)));
		} else if (pcase_var.eql($CONNECTED_CONJUNCTION)) {
		    return inference_worker.connected_conjunction_tactic_p(tactic);
		} else if (pcase_var.eql($CONJUNCTIVE)) {
		    return inference_worker.conjunctive_tactic_p(tactic);
		} else if (pcase_var.eql($DISJUNCTIVE)) {
		    return inference_worker.disjunctive_tactic_p(tactic);
		} else if (pcase_var.eql($LOGICAL)) {
		    return inference_worker.logical_tactic_p(tactic);
		} else if (pcase_var.eql($LOGICAL_CONJUNCTIVE)) {
		    return inference_worker.logical_conjunctive_tactic_p(tactic);
		} else if (pcase_var.eql($STRUCTURAL_CONJUNCTIVE)) {
		    return inference_worker.logical_conjunctive_tactic_p(tactic);
		} else if (pcase_var.eql($META_STRUCTURAL)) {
		    return inference_worker.meta_structural_tactic_p(tactic);
		} else if (pcase_var.eql($CONTENT)) {
		    return inference_worker.content_tactic_p(tactic);
		} else if (pcase_var.eql($UNION)) {
		    return inference_worker_union.union_tactic_p(tactic);
		} else if (pcase_var.eql($SPLIT)) {
		    return inference_worker_split.split_tactic_p(tactic);
		} else if (pcase_var.eql($JOIN_ORDERED)) {
		    return inference_worker_join_ordered.join_ordered_tactic_p(tactic);
		} else if (pcase_var.eql($JOIN)) {
		    return inference_worker_join.join_tactic_p(tactic);
		} else {
		    return eq(type_spec, inference_datastructures_tactic.tactic_type(tactic));
		}

	    }
	}
    }

    public static final SubLObject tactical_problem_p(final SubLObject problem) {
	return makeBoolean(((NIL != inference_datastructures_problem.tactically_possible_problem_p(problem)) || (NIL != inference_datastructures_problem.tactically_pending_problem_p(problem))) || (NIL != inference_datastructures_problem.tactically_finished_problem_p(problem)));
    }

    public static final SubLObject tactical_problem_p_alt(SubLObject problem) {
	return makeBoolean(((NIL != inference_datastructures_problem.tactically_possible_problem_p(problem)) || (NIL != inference_datastructures_problem.tactically_pending_problem_p(problem))) || (NIL != inference_datastructures_problem.tactically_finished_problem_p(problem)));
    }

    public static final SubLObject tactically_examined_problem_p(final SubLObject problem) {
	return inference_datastructures_enumerated_types.examined_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_examined_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.examined_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_finished_problem_p(final SubLObject problem) {
	return inference_datastructures_enumerated_types.finished_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_finished_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.finished_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_good_problem_p(final SubLObject problem) {
	return inference_worker.problem_goodP(problem);
    }

    public static final SubLObject tactically_good_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.good_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_neutral_problem_p(final SubLObject problem) {
	return inference_datastructures_enumerated_types.neutral_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_neutral_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.neutral_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_new_problem_p(final SubLObject problem) {
	return eq($NEW, inference_datastructures_enumerated_types.tactical_status_from_problem_status(inference_datastructures_problem.problem_status(problem)));
    }

    public static final SubLObject tactically_new_problem_p_alt(SubLObject problem) {
	return eq($NEW, inference_datastructures_enumerated_types.tactical_status_from_problem_status(inference_datastructures_problem.problem_status(problem)));
    }

    public static final SubLObject tactically_no_good_problem_p(final SubLObject problem) {
	return inference_datastructures_enumerated_types.no_good_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_no_good_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.no_good_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_not_potentially_possible_problem_p(final SubLObject problem) {
	return makeBoolean(((NIL != inference_datastructures_problem.tactically_examined_problem_p(problem)) || (NIL != inference_datastructures_problem.tactically_pending_problem_p(problem))) || (NIL != inference_datastructures_problem.tactically_finished_problem_p(problem)));
    }

    public static final SubLObject tactically_not_potentially_possible_problem_p_alt(SubLObject problem) {
	return makeBoolean(((NIL != inference_datastructures_problem.tactically_examined_problem_p(problem)) || (NIL != inference_datastructures_problem.tactically_pending_problem_p(problem))) || (NIL != inference_datastructures_problem.tactically_finished_problem_p(problem)));
    }

    public static final SubLObject tactically_pending_problem_p(final SubLObject problem) {
	return inference_datastructures_enumerated_types.pending_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_pending_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.pending_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_possible_problem_p(final SubLObject problem) {
	return inference_datastructures_enumerated_types.possible_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_possible_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.possible_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_potentially_possible_problem_p(final SubLObject problem) {
	return makeBoolean((NIL != inference_datastructures_problem.tactically_possible_problem_p(problem)) || (NIL != inference_datastructures_problem.tactically_unexamined_problem_p(problem)));
    }

    public static final SubLObject tactically_potentially_possible_problem_p_alt(SubLObject problem) {
	return makeBoolean((NIL != inference_datastructures_problem.tactically_possible_problem_p(problem)) || (NIL != inference_datastructures_problem.tactically_unexamined_problem_p(problem)));
    }

    public static final SubLObject tactically_unexamined_problem_p(final SubLObject problem) {
	return inference_datastructures_enumerated_types.unexamined_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject tactically_unexamined_problem_p_alt(SubLObject problem) {
	return inference_datastructures_enumerated_types.unexamined_problem_status_p(inference_datastructures_problem.problem_status(problem));
    }

    public static final SubLObject valid_problem_p(final SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.problem_p(v_object)) && (NIL == inference_datastructures_problem.problem_invalid_p(v_object)));
    }

    public static final SubLObject valid_problem_p_alt(SubLObject v_object) {
	return makeBoolean((NIL != inference_datastructures_problem.problem_p(v_object)) && (NIL == inference_datastructures_problem.problem_invalid_p(v_object)));
    }

    public static final SubLObject visit_defstruct_object_problem_method(final SubLObject obj, final SubLObject visitor_fn) {
	return inference_datastructures_problem.visit_defstruct_problem(obj, visitor_fn);
    }

    public static final SubLObject visit_defstruct_problem(final SubLObject obj, final SubLObject visitor_fn) {
	funcall(visitor_fn, obj, $BEGIN, inference_datastructures_problem.MAKE_PROBLEM, TWELVE_INTEGER);
	funcall(visitor_fn, obj, $SLOT, $SUID, inference_datastructures_problem.prob_suid(obj));
	funcall(visitor_fn, obj, $SLOT, $STORE, inference_datastructures_problem.prob_store(obj));
	funcall(visitor_fn, obj, $SLOT, $QUERY, inference_datastructures_problem.prob_query(obj));
	funcall(visitor_fn, obj, $SLOT, inference_datastructures_problem.$FREE_HL_VARS, inference_datastructures_problem.prob_free_hl_vars(obj));
	funcall(visitor_fn, obj, $SLOT, $STATUS, inference_datastructures_problem.prob_status(obj));
	funcall(visitor_fn, obj, $SLOT, $DEPENDENT_LINKS, inference_datastructures_problem.prob_dependent_links(obj));
	funcall(visitor_fn, obj, $SLOT, $ARGUMENT_LINKS, inference_datastructures_problem.prob_argument_links(obj));
	funcall(visitor_fn, obj, $SLOT, $TACTICS, inference_datastructures_problem.prob_tactics(obj));
	funcall(visitor_fn, obj, $SLOT, inference_datastructures_problem.$PROOF_BINDINGS_INDEX, inference_datastructures_problem.prob_proof_bindings_index(obj));
	funcall(visitor_fn, obj, $SLOT, inference_datastructures_problem.$ARGUMENT_LINK_BINDINGS_INDEX, inference_datastructures_problem.prob_argument_link_bindings_index(obj));
	funcall(visitor_fn, obj, $SLOT, $BACKCHAIN_REQUIRED, inference_datastructures_problem.prob_backchain_required(obj));
	funcall(visitor_fn, obj, $SLOT, $MEMOIZATION_STATE, inference_datastructures_problem.prob_memoization_state(obj));
	funcall(visitor_fn, obj, $END, inference_datastructures_problem.MAKE_PROBLEM, TWELVE_INTEGER);
	return obj;
    }

    public static final SubLObject with_problem_memoization_state(final SubLObject macroform, final SubLObject environment) {
	SubLObject current;
	final SubLObject datum = current = macroform.rest();
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list67);
	final SubLObject temp = current.rest();
	current = current.first();
	SubLObject problem = NIL;
	destructuring_bind_must_consp(current, datum, inference_datastructures_problem.$list67);
	problem = current.first();
	current = current.rest();
	if (NIL == current) {
	    final SubLObject body;
	    current = body = temp;
	    return listS(WITH_MEMOIZATION_STATE, list(list(inference_datastructures_problem.PROBLEM_MEMOIZATION_STATE, problem)), append(body, NIL));
	}
	cdestructuring_bind_error(datum, inference_datastructures_problem.$list67);
	return NIL;
    }

    @Override
    public void declareFunctions() {
	inference_datastructures_problem.declare_inference_datastructures_problem_file();
    }

    @Override
    public void initializeVariables() {
	inference_datastructures_problem.init_inference_datastructures_problem_file();
    }

    @Override
    public void runTopLevelForms() {
	inference_datastructures_problem.setup_inference_datastructures_problem_file();
    }

    static {
    }

    public static final class $problem_native extends SubLStructNative {
	private static final SubLStructDeclNative structDecl;

	public SubLObject $argument_link_bindings_index;

	public SubLObject $argument_links;

	public SubLObject $backchain_required;

	public SubLObject $dependent_links;

	public SubLObject $free_hl_vars;

	public SubLObject $memoization_state;

	public SubLObject $proof_bindings_index;

	public SubLObject $query;

	public SubLObject $status;

	public SubLObject $store;

	public SubLObject $suid;

	public SubLObject $tactics;

	public $problem_native() {
	    this.$suid = Lisp.NIL;
	    this.$store = Lisp.NIL;
	    this.$query = Lisp.NIL;
	    this.$free_hl_vars = Lisp.NIL;
	    this.$status = Lisp.NIL;
	    this.$dependent_links = Lisp.NIL;
	    this.$argument_links = Lisp.NIL;
	    this.$tactics = Lisp.NIL;
	    this.$proof_bindings_index = Lisp.NIL;
	    this.$argument_link_bindings_index = Lisp.NIL;
	    this.$backchain_required = Lisp.NIL;
	    this.$memoization_state = Lisp.NIL;
	}

	@Override
	public SubLObject getField10() {
	    return this.$proof_bindings_index;
	}

	@Override
	public SubLObject getField11() {
	    return this.$argument_link_bindings_index;
	}

	@Override
	public SubLObject getField12() {
	    return this.$backchain_required;
	}

	@Override
	public SubLObject getField13() {
	    return this.$memoization_state;
	}

	@Override
	public SubLObject getField2() {
	    return this.$suid;
	}

	@Override
	public SubLObject getField3() {
	    return this.$store;
	}

	@Override
	public SubLObject getField4() {
	    return this.$query;
	}

	@Override
	public SubLObject getField5() {
	    return this.$free_hl_vars;
	}

	@Override
	public SubLObject getField6() {
	    return this.$status;
	}

	@Override
	public SubLObject getField7() {
	    return this.$dependent_links;
	}

	@Override
	public SubLObject getField8() {
	    return this.$argument_links;
	}

	@Override
	public SubLObject getField9() {
	    return this.$tactics;
	}

	@Override
	public SubLStructDecl getStructDecl() {
	    return structDecl;
	}

	@Override
	public SubLObject setField10(final SubLObject value) {
	    return this.$proof_bindings_index = value;
	}

	@Override
	public SubLObject setField11(final SubLObject value) {
	    return this.$argument_link_bindings_index = value;
	}

	@Override
	public SubLObject setField12(final SubLObject value) {
	    return this.$backchain_required = value;
	}

	@Override
	public SubLObject setField13(final SubLObject value) {
	    return this.$memoization_state = value;
	}

	@Override
	public SubLObject setField2(final SubLObject value) {
	    return this.$suid = value;
	}

	@Override
	public SubLObject setField3(final SubLObject value) {
	    return this.$store = value;
	}

	@Override
	public SubLObject setField4(final SubLObject value) {
	    return this.$query = value;
	}

	@Override
	public SubLObject setField5(final SubLObject value) {
	    return this.$free_hl_vars = value;
	}

	@Override
	public SubLObject setField6(final SubLObject value) {
	    return this.$status = value;
	}

	@Override
	public SubLObject setField7(final SubLObject value) {
	    return this.$dependent_links = value;
	}

	@Override
	public SubLObject setField8(final SubLObject value) {
	    return this.$argument_links = value;
	}

	@Override
	public SubLObject setField9(final SubLObject value) {
	    return this.$tactics = value;
	}

	static {
	    structDecl = makeStructDeclNative(inference_datastructures_problem.$problem_native.class, PROBLEM, PROBLEM_P, inference_datastructures_problem.$list3, inference_datastructures_problem.$list4,
		    new String[] { "$suid", "$store", "$query", "$free_hl_vars", "$status", "$dependent_links", "$argument_links", "$tactics", "$proof_bindings_index", "$argument_link_bindings_index", "$backchain_required", "$memoization_state" }, inference_datastructures_problem.$list5,
		    inference_datastructures_problem.$list6, inference_datastructures_problem.PRINT_PROBLEM);
	}
    }

    public static final class $problem_p$UnaryFunction extends UnaryFunction {
	public $problem_p$UnaryFunction() {
	    super(extractFunctionNamed("PROBLEM-P"));
	}

	@Override
	public SubLObject processItem(final SubLObject arg1) {
	    return inference_datastructures_problem.problem_p(arg1);
	}
    }

    // public static final class $problem_p$UnaryFunction extends UnaryFunction {
    // public $problem_p$UnaryFunction() {
    // super(extractFunctionNamed("PROBLEM-P"));
    // }
    // 
    // public SubLObject processItem_alt(SubLObject arg1) {
    // return problem_p(arg1);
    // }
    // }
    @LispMethod(comment = "public static final class $problem_p$UnaryFunction extends UnaryFunction {\npublic $problem_p$UnaryFunction() {\nsuper(extractFunctionNamed(\"PROBLEM-P\"));\n}\npublic SubLObject processItem_alt(SubLObject arg1) {\nreturn problem_p(arg1);")
    public static final class $sxhash_problem_method$UnaryFunction extends UnaryFunction {
	public $sxhash_problem_method$UnaryFunction() {
	    super(extractFunctionNamed("SXHASH-PROBLEM-METHOD"));
	}

	@Override
	public SubLObject processItem(final SubLObject arg1) {
	    return inference_datastructures_problem.sxhash_problem_method(arg1);
	}
    }
}

/**
 * Total time: 1074 ms synthetic
 */
