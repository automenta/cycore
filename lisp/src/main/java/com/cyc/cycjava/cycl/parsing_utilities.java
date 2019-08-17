/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.constant_handles.constant_p;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.control_vars.$within_assert$;
import static com.cyc.cycjava.cycl.el_utilities.copy_expression;
import static com.cyc.cycjava.cycl.el_utilities.el_binary_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_conjunction_p;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_with_operator_p;
import static com.cyc.cycjava.cycl.el_utilities.el_junction_p;
import static com.cyc.cycjava.cycl.el_utilities.el_ternary_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_unary_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.formula_arityE;
import static com.cyc.cycjava.cycl.el_utilities.formula_with_sequence_varP;
import static com.cyc.cycjava.cycl.el_utilities.make_binary_formula;
import static com.cyc.cycjava.cycl.el_utilities.make_conjunction;
import static com.cyc.cycjava.cycl.el_utilities.make_el_list;
import static com.cyc.cycjava.cycl.el_utilities.make_el_set;
import static com.cyc.cycjava.cycl.el_utilities.make_formula;
import static com.cyc.cycjava.cycl.el_utilities.make_ternary_formula;
import static com.cyc.cycjava.cycl.el_utilities.make_unary_formula;
import static com.cyc.cycjava.cycl.el_utilities.map_formula_terms;
import static com.cyc.cycjava.cycl.el_utilities.possibly_naut_p;
import static com.cyc.cycjava.cycl.el_utilities.replace_formula_arg;
import static com.cyc.cycjava.cycl.el_utilities.string_to_el_var_name;
import static com.cyc.cycjava.cycl.kb_indexing_datastructures.indexed_term_p;
import static com.cyc.cycjava.cycl.set.new_set;
import static com.cyc.cycjava.cycl.set.set_add;
import static com.cyc.cycjava.cycl.set.set_element_list;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.alpha_char_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.rplacd;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eql;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.clrhash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.gethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.hash_table_count;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.make_hash_table;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.sethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.divide;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.floor;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.max;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.multiply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numGE;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete_duplicates;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove_duplicates;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.reverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.search;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.make_keyword;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.current_process;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.floatp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.keywordp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.nth_value_step_1;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.nth_value_step_2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.resetMultipleValues;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.assoc;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.butlast;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.fourth;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.getf;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.last;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.third;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_pretty$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.print;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.close;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import org.armedbear.lisp.Lisp;

import com.cyc.cycjava.cycl.inference.ask_utilities;
import com.cyc.cycjava.cycl.inference.harness.forward;
import com.cyc.cycjava.cycl.inference.harness.inference_kernel;
import com.cyc.cycjava.cycl.inference.harness.prove;
import com.cyc.cycjava.cycl.rtp.rtp_iterators;
import com.cyc.cycjava.cycl.sbhl.sbhl_graphs;
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
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Strings;
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
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
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
 * module:      PARSING-UTILITIES
 * source file: /cyc/top/cycl/parsing-utilities.lisp
 * created:     2019/07/03 17:38:23
 */
public final class parsing_utilities extends SubLTranslatedFile implements V12 {
    public static final SubLObject subloop_reserved_initialize_parse_dates_and_numbers_test_case_instance(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, RESULT, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, TEST_METHOD, NIL);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_parse_dates_and_numbers_test_case_class(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, MODULE, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, CATEGORIES, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, SUITES, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, TEST_METHODS, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, ENABLED, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, LOCK, NIL);
        return NIL;
    }

    public static final SubLObject parse_dates_and_numbers_test_case_p(SubLObject parse_dates_and_numbers_test_case) {
        return classes.subloop_instanceof_class(parse_dates_and_numbers_test_case, PARSE_DATES_AND_NUMBERS_TEST_CASE);
    }

    static private final SubLString $str_alt199$ = makeString("");

    public static final class $parse_iterator_native extends SubLStructNative {
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        public SubLObject getField2() {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$sub_iterators;
        }

        public SubLObject getField3() {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$properties;
        }

        public SubLObject getField4() {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$current_iterator_index;
        }

        public SubLObject getField5() {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$string;
        }

        public SubLObject getField6() {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$previous_parses;
        }

        public SubLObject getField7() {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$next;
        }

        public SubLObject setField2(SubLObject value) {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$sub_iterators = value;
        }

        public SubLObject setField3(SubLObject value) {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$properties = value;
        }

        public SubLObject setField4(SubLObject value) {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$current_iterator_index = value;
        }

        public SubLObject setField5(SubLObject value) {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$string = value;
        }

        public SubLObject setField6(SubLObject value) {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$previous_parses = value;
        }

        public SubLObject setField7(SubLObject value) {
            return com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.this.$next = value;
        }

        public SubLObject $sub_iterators = Lisp.NIL;

        public SubLObject $properties = Lisp.NIL;

        public SubLObject $current_iterator_index = Lisp.NIL;

        public SubLObject $string = Lisp.NIL;

        public SubLObject $previous_parses = Lisp.NIL;

        public SubLObject $next = Lisp.NIL;

        private static final SubLStructDeclNative structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.class, PARSE_ITERATOR, PARSE_ITERATOR_P, $list_alt269, $list_alt270, new String[]{ "$sub_iterators", "$properties", "$current_iterator_index", "$string", "$previous_parses", "$next" }, $list_alt271, $list_alt272, PRINT_PARSE_ITERATOR);
    }

    public static final SubLFile me = new parsing_utilities();

 public static final String myName = "com.cyc.cycjava.cycl.parsing_utilities";


    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $parse_noun_compound_valid_non_alpha_chars$ = makeSymbol("*PARSE-NOUN-COMPOUND-VALID-NON-ALPHA-CHARS*");

    // defparameter
    // #$NLUtteranceAttributes constant -> mts
    /**
     * #$NLUtteranceAttributes constant -> mts
     */
    @LispMethod(comment = "#$NLUtteranceAttributes constant -> mts\ndefparameter")
    private static final SubLSymbol $nl_tags$ = makeSymbol("*NL-TAGS*");

    // defparameter
    // #$NLTagFn constant -> mts
    /**
     * #$NLTagFn constant -> mts
     */
    @LispMethod(comment = "#$NLTagFn constant -> mts\ndefparameter")
    private static final SubLSymbol $nl_tag_fns$ = makeSymbol("*NL-TAG-FNS*");

    // defparameter
    // <fn whose resultIsa is (a spec of) #$NLUtteranceAttribute> -> mts
    /**
     * <fn whose resultIsa is (a spec of) #$NLUtteranceAttribute> -> mts
     */
    @LispMethod(comment = "<fn whose resultIsa is (a spec of) #$NLUtteranceAttribute> -> mts\ndefparameter")
    private static final SubLSymbol $nl_tag_denoting_fns$ = makeSymbol("*NL-TAG-DENOTING-FNS*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $npp_name_preds_to_exclude$ = makeSymbol("*NPP-NAME-PREDS-TO-EXCLUDE*");

    // defparameter
    // LISTP of preds similar to #$relationAllExists
    /**
     * LISTP of preds similar to #$relationAllExists
     */
    @LispMethod(comment = "LISTP of preds similar to #$relationAllExists\ndefparameter")
    private static final SubLSymbol $ra1p_rae_preds$ = makeSymbol("*RA1P-RAE-PREDS*");

    // defparameter
    // LISTP of preds similar to #$requiredArg1Pred
    /**
     * LISTP of preds similar to #$requiredArg1Pred
     */
    @LispMethod(comment = "LISTP of preds similar to #$requiredArg1Pred\ndefparameter")
    private static final SubLSymbol $ra1p_ra1_preds$ = makeSymbol("*RA1P-RA1-PREDS*");

    // deflexical
    // BOOLEAN; Should REQUIRED-ARG1-PREDS print out its pairs?
    /**
     * BOOLEAN; Should REQUIRED-ARG1-PREDS print out its pairs?
     */
    @LispMethod(comment = "BOOLEAN; Should REQUIRED-ARG1-PREDS print out its pairs?\ndeflexical")
    private static final SubLSymbol $ra1p_show_pairsP$ = makeSymbol("*RA1P-SHOW-PAIRS?*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $recently_created_parse_tree_elements$ = makeSymbol("*RECENTLY-CREATED-PARSE-TREE-ELEMENTS*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $remember_new_parse_tree_elementsP$ = makeSymbol("*REMEMBER-NEW-PARSE-TREE-ELEMENTS?*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $parse_as_typeP_caching_state$ = makeSymbol("*PARSE-AS-TYPE?-CACHING-STATE*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_parse_iterator$ = makeSymbol("*DTP-PARSE-ITERATOR*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_cyclify_iterator$ = makeSymbol("*DTP-CYCLIFY-ITERATOR*");

    private static final SubLSymbol NPP_PARSE_CYCL = makeSymbol("NPP-PARSE-CYCL");

    private static final SubLObject $$Definite_NLAttr = reader_make_constant_shell("Definite-NLAttr");

    private static final SubLSymbol INSTANCES_OF_COLLECTION_PARSE = makeSymbol("INSTANCES-OF-COLLECTION-PARSE");

    private static final SubLSymbol $sym7$_X = makeSymbol("?X");



    private static final SubLSymbol $MAX_TRANSFORMATION_DEPTH = makeKeyword("MAX-TRANSFORMATION-DEPTH");

    private static final SubLSymbol PARSE_NOUN_COMPOUND_VALID_CHAR_P = makeSymbol("PARSE-NOUN-COMPOUND-VALID-CHAR-P");



    private static final SubLString $str14$___0123456789 = makeString(" -'0123456789");





    private static final SubLString $str23$_A_is_not_a__A = makeString("~A is not a ~A");

    private static final SubLString $$$continue_anyway = makeString("continue anyway");

    private static final SubLString $str28$_A_is_not_a_valid__sbhl_type_erro = makeString("~A is not a valid *sbhl-type-error-action* value");



    private static final SubLString $str32$_A_is_neither_SET_P_nor_LISTP_ = makeString("~A is neither SET-P nor LISTP.");

    private static final SubLString $str33$attempting_to_bind_direction_link = makeString("attempting to bind direction link variable, to NIL. macro body not executed.");

    private static final SubLString $str34$Node__a_does_not_pass_sbhl_type_t = makeString("Node ~a does not pass sbhl-type-test ~a~%");









    private static final SubLSymbol STRIP_NL_TAGS = makeSymbol("STRIP-NL-TAGS");

    private static final SubLObject $$NLDefinitenessFn_3 = reader_make_constant_shell("NLDefinitenessFn-3");

    private static final SubLObject $$NLQuantFn_3 = reader_make_constant_shell("NLQuantFn-3");

    private static final SubLString $str44$Can_t_find_ternary_NL_tag_fn_for_ = makeString("Can't find ternary NL tag fn for ~S");







    private static final SubLString $str49$Can_t_find_binary_NL_tag_fn_for__ = makeString("Can't find binary NL tag fn for ~S");

    private static final SubLSymbol MAKE_NL_TAG_TEMPLATE = makeSymbol("MAKE-NL-TAG-TEMPLATE");

    private static final SubLSymbol $make_nl_tag_template_caching_state$ = makeSymbol("*MAKE-NL-TAG-TEMPLATE-CACHING-STATE*");

    private static final SubLSymbol $sym52$NL_TAG_ = makeSymbol("NL-TAG?");

    private static final SubLString $str53$Couldn_t_determine_NL_number_for_ = makeString("Couldn't determine NL number for ~S");

    private static final SubLList $list54 = list(makeKeyword("ANYTHING"));

    private static final SubLSymbol $sym55$DETERMINER_NUMBER_DENOT_ = makeSymbol("DETERMINER-NUMBER-DENOT?");



    private static final SubLList $list57 = list(reader_make_constant_shell("nounStrings"));

    static private final SubLList $list58 = list(reader_make_constant_shell("plural-Generic"));

    static private final SubLList $list59 = list(reader_make_constant_shell("singular-Generic"));

    private static final SubLSymbol NL_MAX_FLOOR_PREDS = makeSymbol("NL-MAX-FLOOR-PREDS");

    private static final SubLSymbol $nl_max_floor_preds_caching_state$ = makeSymbol("*NL-MAX-FLOOR-PREDS-CACHING-STATE*");



    private static final SubLSymbol CYCL_PRONOUN_P = makeSymbol("CYCL-PRONOUN-P");

    private static final SubLObject $$UnmarkedNumber_NLAttr = reader_make_constant_shell("UnmarkedNumber-NLAttr");



    private static final SubLObject $$Ungendered_NLAttr = reader_make_constant_shell("Ungendered-NLAttr");







    private static final SubLSymbol KB_NL_NUMBER_ATTRIBUTE_P = makeSymbol("KB-NL-NUMBER-ATTRIBUTE-P");

    private static final SubLObject $$Generic_NLAttr = reader_make_constant_shell("Generic-NLAttr");

    private static final SubLObject $$Mass_NLAttr = reader_make_constant_shell("Mass-NLAttr");

    private static final SubLObject $$Plural_NLAttr = reader_make_constant_shell("Plural-NLAttr");

    private static final SubLSymbol KB_NL_GENDER_ATTRIBUTE_P = makeSymbol("KB-NL-GENDER-ATTRIBUTE-P");

    private static final SubLObject $$nonPlural_Generic = reader_make_constant_shell("nonPlural-Generic");

    private static final SubLObject $$Singular_NLAttr = reader_make_constant_shell("Singular-NLAttr");

    private static final SubLObject $$singular_Generic = reader_make_constant_shell("singular-Generic");

    private static final SubLObject $$plural_Generic = reader_make_constant_shell("plural-Generic");

    private static final SubLObject $$massNumber_Generic = reader_make_constant_shell("massNumber-Generic");

    private static final SubLSymbol $spps_to_nl_numbers$ = makeSymbol("*SPPS-TO-NL-NUMBERS*");

    private static final SubLInteger $int$128 = makeInteger(128);









    private static final SubLObject $const88$ProperNamePredicate_ExcludedFromN = reader_make_constant_shell("ProperNamePredicate-ExcludedFromNPParser");

    private static final SubLSymbol PSP_WEIGHT_P = makeSymbol("PSP-WEIGHT-P");







    private static final SubLSymbol $sym99$RESULT_OF_PARSING_FORMULA_ = makeSymbol("RESULT-OF-PARSING-FORMULA?");



    private static final SubLString $str101$_A_doesn_t_genl_to___ParsingTempl = makeString("~A doesn't genl to #$ParsingTemplateCategory");

    private static final SubLList $list102 = list(makeSymbol("SUBJ-ROLES"), makeSymbol("DIR-OBJ-ROLES"), makeSymbol("INDIR-OBJ-ROLES"));





    static private final SubLList $list106 = list(makeKeyword("ACTION"), makeKeyword("SUBJECT"));

    private static final SubLList $list107 = list(makeKeyword("ACTION"), makeKeyword("OBJECT"));



    private static final SubLObject $$patient_GenericDirect = reader_make_constant_shell("patient-GenericDirect");

    private static final SubLObject $$patient_GenericIndirect = reader_make_constant_shell("patient-GenericIndirect");

    static private final SubLList $list111 = list(reader_make_constant_shell("relationAllExists"), reader_make_constant_shell("relationAllExistsCount"), reader_make_constant_shell("relationAllExistsMany"), reader_make_constant_shell("relationAllExistsMin"));

    private static final SubLSymbol ALL_SPEC_PREDICATES = makeSymbol("ALL-SPEC-PREDICATES");



    private static final SubLSymbol $sym115$GENL_PRED_ = makeSymbol("GENL-PRED?");

    private static final SubLSymbol $sym116$GENL_ = makeSymbol("GENL?");

    private static final SubLSymbol $sym118$SPEC_PRED_ = makeSymbol("SPEC-PRED?");

    private static final SubLList $list119 = list(makeSymbol("EXISTING-VAR"), makeSymbol("TYPE"));



    private static final SubLString $$$0 = makeString("0");

    private static final SubLString $str122$_ = makeString("-");



    private static final SubLSymbol NART_TO_NAUT = makeSymbol("NART-TO-NAUT");













    private static final SubLList $list131 = list(makeSymbol("?DTR-NUM"), makeSymbol("?DTR"));

    private static final SubLList $list132 = list(makeKeyword("RETURN"), list(makeKeyword("TEMPLATE"), list(makeSymbol("?DTR"), makeSymbol("?DTR-NUM"))), makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"));

    private static final SubLSymbol $sym133$_ = makeSymbol("<");



    static private final SubLList $list135 = list(makeSymbol("?DTR"));

    static private final SubLList $list136 = list(makeKeyword("RETURN"), list(makeKeyword("TEMPLATE"), makeSymbol("?DTR")), makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"));

    private static final SubLSymbol $sym137$_MOTHER = makeSymbol("?MOTHER");

    private static final SubLList $list138 = list(makeKeyword("RETURN"), list(makeKeyword("TEMPLATE"), makeSymbol("?MOTHER")), makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"), makeKeyword("MAX-NUMBER"), ONE_INTEGER);





    private static final SubLSymbol $sym141$_MEANING = makeSymbol("?MEANING");

    static private final SubLList $list142 = list(makeSymbol("?MEANING"));

    private static final SubLList $list143 = list(makeSymbol("MEANINGS"), makeSymbol("NODE"), makeSymbol("&KEY"), list(makeSymbol("MT"), makeSymbol("*PARSE-TREE-MT*")), list(makeSymbol("PRED"), reader_make_constant_shell("proposedMeaning")));

    private static final SubLList $list144 = list(makeKeyword("MT"), $PRED);

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    private static final SubLSymbol $sym148$THIS_MEANING = makeUninternedSymbol("THIS-MEANING");

    private static final SubLSymbol SYNTACTIC_NODE_PROPOSED_MEANINGS = makeSymbol("SYNTACTIC-NODE-PROPOSED-MEANINGS");

    private static final SubLList $list152 = list(list(QUOTE, EQUAL));

    private static final SubLSymbol $sym153$THIS_MEANING = makeUninternedSymbol("THIS-MEANING");

    static private final SubLList $list156 = list(list(makeSymbol("*REMEMBER-NEW-PARSE-TREE-ELEMENTS?*"), T));

    private static final SubLString $$$ParseTreeElement = makeString("ParseTreeElement");

    private static final SubLSymbol $sym159$_INSTANCE = makeSymbol("?INSTANCE");

    private static final SubLString $str160$Can_t_hypothesize_instance_of__S_ = makeString("Can't hypothesize instance of ~S because~% ~S");





















    private static final SubLString $str171$Token_node_mismatch__Token___S_st = makeString("Token-node mismatch. Token: ~S string: ~S. Node string: ~S");

















    private static final SubLList $list181 = list(makeKeyword("TOKEN"));

    private static final SubLList $list182 = list($BIND, makeSymbol("FIRST-TOKEN-NUM"));

    private static final SubLList $list183 = list($BIND, makeSymbol("LAST-TOKEN-NUM"));

    private static final SubLSymbol LAST_TOKEN_NUM = makeSymbol("LAST-TOKEN-NUM");

    private static final SubLSymbol FIRST_TOKEN_NUM = makeSymbol("FIRST-TOKEN-NUM");

    private static final SubLString $str186$Couldn_t_get_interval_span_for__S = makeString("Couldn't get interval span for ~S in ~S");







    private static final SubLSymbol $sym190$_N = makeSymbol("?N");

    private static final SubLList $list191 = list(makeSymbol("?N"), makeSymbol("?NODE"));







    private static final SubLSymbol SYNTACTIC_NODE_TO_NESTED_LIST = makeSymbol("SYNTACTIC-NODE-TO-NESTED-LIST");

    private static final SubLString $str196$ = makeString("");

    private static final SubLString $str197$Couldn_t_find_string_for__S = makeString("Couldn't find string for ~S");

    private static final SubLString $$$_ = makeString(" ");

    private static final SubLString $str199$Bad_dtrs___S = makeString("Bad dtrs: ~S");

    private static final SubLSymbol INTERVAL_SPAN_P = makeSymbol("INTERVAL-SPAN-P");

    private static final SubLString $str201$Can_t_get_span_start_from__S = makeString("Can't get span start from ~S");

    private static final SubLString $str202$Can_t_get_span_end_from__S = makeString("Can't get span end from ~S");

    private static final SubLSymbol $penntag_phrase_categories$ = makeSymbol("*PENNTAG-PHRASE-CATEGORIES*");

    private static final SubLList $list204 = cons(makeSymbol("?TAG"), makeSymbol("?CATEGORY"));

    private static final SubLList $list205 = list(reader_make_constant_shell("syntacticCategoryTags"), makeSymbol("?CATEGORY"), makeSymbol("?TAG"));

    private static final SubLList $list206 = list(makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"));

    private static final SubLSymbol $penntag_forts$ = makeSymbol("*PENNTAG-FORTS*");

    static private final SubLList $list209 = cons(makeSymbol("?TAG-STRING"), makeSymbol("?TAG"));

    private static final SubLList $list210 = list(reader_make_constant_shell("pennTagString"), makeSymbol("?TAG"), makeSymbol("?TAG-STRING"));

    private static final SubLSymbol $sym212$_TOKEN = makeSymbol("?TOKEN");



    private static final SubLList $list214 = list(makeSymbol("?TOKEN"));



    private static final SubLString $str217$Failed_to_compute_start_offset_fo = makeString("Failed to compute start offset for ~S (~S) of ~S");









    private static final SubLSymbol CONSTRUCT_PARSE_EXPRESSION_FROM_SYNTACTIC_NODE = makeSymbol("CONSTRUCT-PARSE-EXPRESSION-FROM-SYNTACTIC-NODE");

    private static final SubLSymbol $sym223$PHRASAL_NODE_ = makeSymbol("PHRASAL-NODE?");

    private static final SubLString $str225$Couldn_t_find_dtr__S_of__S = makeString("Couldn't find dtr ~S of ~S");

    private static final SubLList $list226 = list(list(makeSymbol("GOOD"), makeSymbol("&OPTIONAL"), makeSymbol("BAD")), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final SubLSymbol $good_parsing_pile$ = makeSymbol("*GOOD-PARSING-PILE*");

    public static final SubLSymbol $bad_parsing_pile$ = makeSymbol("*BAD-PARSING-PILE*");

    static private final SubLList $list229 = list(makeSymbol("PARSE"));

    private static final SubLList $list231 = list(makeSymbol("*GOOD-PARSING-PILES*"));

    static private final SubLList $list232 = list(makeSymbol("PARSE"), makeSymbol("REASON"));

    private static final SubLList $list233 = list(makeSymbol("*BAD-PARSING-PILE*"));

    static private final SubLList $list234 = list(makeSymbol("FILTER"), makeSymbol("DENOT-ACCESSOR"), makeSymbol("DIAG-ACCESSOR"));

    private static final SubLSymbol $sym235$FILTER_RESPONSE = makeUninternedSymbol("FILTER-RESPONSE");

    private static final SubLSymbol $sym236$SOME_GOOD = makeUninternedSymbol("SOME-GOOD");

    private static final SubLSymbol $sym237$SOME_BAD = makeUninternedSymbol("SOME-BAD");

    private static final SubLSymbol $sym238$THIS_CANDIDATE = makeUninternedSymbol("THIS-CANDIDATE");

    private static final SubLList $list239 = list(NIL);

    private static final SubLList $list240 = list(makeSymbol("*GOOD-PARSING-PILE*"));

    private static final SubLList $list247 = list(makeSymbol("GOOD"), makeSymbol("BAD"));

    private static final SubLObject $const249$SubcollectionRelationFunction_Can = reader_make_constant_shell("SubcollectionRelationFunction-Canonical");

    private static final SubLObject $const250$SubcollectionRelationFunction_Typ = reader_make_constant_shell("SubcollectionRelationFunction-TypeLevel");

    private static final SubLObject $const251$SubcollectionRelationFunction_Inv = reader_make_constant_shell("SubcollectionRelationFunction-Inverse");

    private static final SubLList $list252 = list(makeSymbol("THIS-ARG1"), makeSymbol("THIS-PRED"), makeSymbol("THIS-ARG3"));

    private static final SubLList $list253 = list(makeSymbol("OTHER-ARG1"), makeSymbol("OTHER-PRED"), makeSymbol("OTHER-ARG3"));

    private static final SubLSymbol $sym254$PARSE_AS_TYPE_ = makeSymbol("PARSE-AS-TYPE?");





    private static final SubLList $list257 = list(makeKeyword("TRANSFORMATION-ALLOWED?"), NIL);

    private static final SubLSymbol $sym258$_PARSE_AS_TYPE__CACHING_STATE_ = makeSymbol("*PARSE-AS-TYPE?-CACHING-STATE*");







    private static final SubLString $str262$_ = makeString(",");

    private static final SubLList $list263 = list(reader_make_constant_shell("PhraseFn"), reader_make_constant_shell("Determiner"));

    private static final SubLSymbol PARSE_ITERATOR = makeSymbol("PARSE-ITERATOR");

    private static final SubLSymbol PARSE_ITERATOR_P = makeSymbol("PARSE-ITERATOR-P");

    static private final SubLList $list266 = list(makeSymbol("SUB-ITERATORS"), makeSymbol("PROPERTIES"), makeSymbol("CURRENT-ITERATOR-INDEX"), makeSymbol("STRING"), makeSymbol("PREVIOUS-PARSES"), makeSymbol("NEXT"));

    private static final SubLList $list267 = list(makeKeyword("SUB-ITERATORS"), makeKeyword("PROPERTIES"), makeKeyword("CURRENT-ITERATOR-INDEX"), makeKeyword("STRING"), makeKeyword("PREVIOUS-PARSES"), $NEXT);

    private static final SubLList $list268 = list(makeSymbol("PI-SUB-ITERATORS"), makeSymbol("PI-PROPERTIES"), makeSymbol("PI-CURRENT-ITERATOR-INDEX"), makeSymbol("PI-STRING"), makeSymbol("PI-PREVIOUS-PARSES"), makeSymbol("PI-NEXT"));

    static private final SubLList $list269 = list(makeSymbol("_CSETF-PI-SUB-ITERATORS"), makeSymbol("_CSETF-PI-PROPERTIES"), makeSymbol("_CSETF-PI-CURRENT-ITERATOR-INDEX"), makeSymbol("_CSETF-PI-STRING"), makeSymbol("_CSETF-PI-PREVIOUS-PARSES"), makeSymbol("_CSETF-PI-NEXT"));

    private static final SubLSymbol PRINT_PARSE_ITERATOR = makeSymbol("PRINT-PARSE-ITERATOR");

    private static final SubLSymbol PARSE_ITERATOR_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("PARSE-ITERATOR-PRINT-FUNCTION-TRAMPOLINE");

    static private final SubLList $list272 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("PARSE-ITERATOR-P"));

    private static final SubLSymbol PI_SUB_ITERATORS = makeSymbol("PI-SUB-ITERATORS");

    private static final SubLSymbol _CSETF_PI_SUB_ITERATORS = makeSymbol("_CSETF-PI-SUB-ITERATORS");

    private static final SubLSymbol PI_PROPERTIES = makeSymbol("PI-PROPERTIES");

    private static final SubLSymbol _CSETF_PI_PROPERTIES = makeSymbol("_CSETF-PI-PROPERTIES");

    private static final SubLSymbol PI_CURRENT_ITERATOR_INDEX = makeSymbol("PI-CURRENT-ITERATOR-INDEX");

    private static final SubLSymbol _CSETF_PI_CURRENT_ITERATOR_INDEX = makeSymbol("_CSETF-PI-CURRENT-ITERATOR-INDEX");

    private static final SubLSymbol PI_STRING = makeSymbol("PI-STRING");

    private static final SubLSymbol _CSETF_PI_STRING = makeSymbol("_CSETF-PI-STRING");

    private static final SubLSymbol PI_PREVIOUS_PARSES = makeSymbol("PI-PREVIOUS-PARSES");

    private static final SubLSymbol _CSETF_PI_PREVIOUS_PARSES = makeSymbol("_CSETF-PI-PREVIOUS-PARSES");

    private static final SubLSymbol PI_NEXT = makeSymbol("PI-NEXT");

    private static final SubLSymbol _CSETF_PI_NEXT = makeSymbol("_CSETF-PI-NEXT");

    private static final SubLSymbol $CURRENT_ITERATOR_INDEX = makeKeyword("CURRENT-ITERATOR-INDEX");

    private static final SubLString $str291$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_PARSE_ITERATOR = makeSymbol("MAKE-PARSE-ITERATOR");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_PARSE_ITERATOR_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-PARSE-ITERATOR-METHOD");

    private static final SubLString $str297$__parse_iterator__S_current_iter_ = makeString("#<parse-iterator ~S current-iter: ~A sub-iters: ~A props: ~A>");

    private static final SubLSymbol PARSE_ITERATOR_DONE = makeSymbol("PARSE-ITERATOR-DONE");

    private static final SubLSymbol PARSE_ITERATOR_NEXT = makeSymbol("PARSE-ITERATOR-NEXT");

    private static final SubLList $list301 = list(reader_make_constant_shell("CharniakParserCyclifier"), reader_make_constant_shell("CycRecursiveTemplateParser"));





    private static final SubLSymbol $sym304$EQUALS_EL_ = makeSymbol("EQUALS-EL?");

    private static final SubLSymbol CYCLIFY_ITERATOR = makeSymbol("CYCLIFY-ITERATOR");

    private static final SubLSymbol CYCLIFY_ITERATOR_P = makeSymbol("CYCLIFY-ITERATOR-P");

    static private final SubLList $list308 = list(makeSymbol("ITER-OBJ"));

    private static final SubLList $list309 = list(makeKeyword("ITER-OBJ"));

    private static final SubLList $list310 = list(makeSymbol("CYCLIFY-ITERATOR-ITER-OBJ"));

    private static final SubLList $list311 = list(makeSymbol("_CSETF-CYCLIFY-ITERATOR-ITER-OBJ"));

    private static final SubLSymbol CYCLIFY_ITERATOR_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("CYCLIFY-ITERATOR-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list314 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("CYCLIFY-ITERATOR-P"));

    private static final SubLSymbol CYCLIFY_ITERATOR_ITER_OBJ = makeSymbol("CYCLIFY-ITERATOR-ITER-OBJ");

    private static final SubLSymbol _CSETF_CYCLIFY_ITERATOR_ITER_OBJ = makeSymbol("_CSETF-CYCLIFY-ITERATOR-ITER-OBJ");

    private static final SubLSymbol MAKE_CYCLIFY_ITERATOR = makeSymbol("MAKE-CYCLIFY-ITERATOR");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_CYCLIFY_ITERATOR_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-CYCLIFY-ITERATOR-METHOD");

    private static final SubLSymbol CYCLIFY_ITERATOR_DONE = makeSymbol("CYCLIFY-ITERATOR-DONE");

    private static final SubLSymbol CYCLIFY_ITERATOR_NEXT = makeSymbol("CYCLIFY-ITERATOR-NEXT");

    private static final SubLSymbol $sym323$HAS_NEXT_ = makeSymbol("HAS-NEXT?");



    private static final SubLSymbol $sym327$_STRING = makeSymbol("?STRING");



    private static final SubLList $list329 = list(makeSymbol("?STRING"));

    private static final SubLObject $const330$TextualEntailmentGenericBackgroun = reader_make_constant_shell("TextualEntailmentGenericBackgroundMt");



    private static final SubLList $list333 = list(makeKeyword("MAX-NUMBER"), ONE_INTEGER);







    // Definitions
    public static final SubLObject parse_noun_compound_with_types_alt(SubLObject string, SubLObject good_list, SubLObject bad_list, SubLObject ignore_subcolfnsP, SubLObject instance_limit, SubLObject try_unknown_stringsP) {
        if (instance_limit == UNPROVIDED) {
            instance_limit = TEN_INTEGER;
        }
        if (try_unknown_stringsP == UNPROVIDED) {
            try_unknown_stringsP = T;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject parses = NIL;
                string = clean_string_for_parse_noun_compound(string);
                if (NIL != string_utilities.non_empty_stringP(string)) {
                    {
                        SubLObject _prev_bind_0 = parsing_vars.$npp_max_rules_per_compound$.currentBinding(thread);
                        try {
                            parsing_vars.$npp_max_rules_per_compound$.bind(TWO_INTEGER, thread);
                            {
                                SubLObject start_index = NIL;
                                SubLObject end_index = NIL;
                                SubLObject chart = NIL;
                                if ((NIL == parsing_vars.$psp_chart$.getDynamicValue(thread)) || (NIL != parsing_macros.psp_chart_matches_gap_type_p(parsing_vars.$psp_chart$.getDynamicValue(thread), $NONE))) {
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject start_index_1 = psp_main.psp_find_string_in_charts(string, parsing_vars.$psp_chart$.getDynamicValue(thread), parsing_vars.$psp_extra_charts$.getDynamicValue(thread));
                                        SubLObject end_index_2 = thread.secondMultipleValue();
                                        SubLObject chart_3 = thread.thirdMultipleValue();
                                        thread.resetMultipleValues();
                                        start_index = start_index_1;
                                        end_index = end_index_2;
                                        chart = chart_3;
                                    }
                                }
                                if (NIL != start_index) {
                                    {
                                        SubLObject _prev_bind_0_4 = parsing_vars.$psp_chart_start_index$.currentBinding(thread);
                                        SubLObject _prev_bind_1 = parsing_vars.$psp_chart_end_index$.currentBinding(thread);
                                        SubLObject _prev_bind_2 = parsing_vars.$psp_chart$.currentBinding(thread);
                                        try {
                                            parsing_vars.$psp_chart_start_index$.bind(start_index, thread);
                                            parsing_vars.$psp_chart_end_index$.bind(end_index, thread);
                                            parsing_vars.$psp_chart$.bind(chart, thread);
                                            psp_syntax.psp_chart_do_syntactic_parsing(chart, UNPROVIDED, UNPROVIDED);
                                            {
                                                SubLObject _prev_bind_0_5 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                                                try {
                                                    parsing_vars.$psp_return_mode$.bind($BEST_ONLY, thread);
                                                    parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                                                } finally {
                                                    parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_5, thread);
                                                }
                                            }
                                            if (NIL != parses) {
                                            } else {
                                                if ((NIL != try_unknown_stringsP) && (NIL != psp_chart.psp_chart_has_unknown_stringsP(UNPROVIDED))) {
                                                    parses = filter_noun_compound_parses_by_types(delete_duplicates(Mapping.mapcar(NPP_PARSE_CYCL, noun_compound_parser.parse_noun_compound(string, UNPROVIDED, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED), good_list, bad_list, ignore_subcolfnsP, UNPROVIDED);
                                                } else {
                                                    {
                                                        SubLObject _prev_bind_0_6 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                                                        try {
                                                            parsing_vars.$psp_return_mode$.bind($EVERYTHING, thread);
                                                            parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                                                        } finally {
                                                            parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_6, thread);
                                                        }
                                                    }
                                                }
                                            }
                                        } finally {
                                            parsing_vars.$psp_chart$.rebind(_prev_bind_2, thread);
                                            parsing_vars.$psp_chart_end_index$.rebind(_prev_bind_1, thread);
                                            parsing_vars.$psp_chart_start_index$.rebind(_prev_bind_0_4, thread);
                                        }
                                    }
                                } else {
                                    {
                                        SubLObject _prev_bind_0_7 = parsing_vars.$psp_chart_start_index$.currentBinding(thread);
                                        SubLObject _prev_bind_1 = parsing_vars.$psp_chart_end_index$.currentBinding(thread);
                                        try {
                                            parsing_vars.$psp_chart_start_index$.bind(ZERO_INTEGER, thread);
                                            parsing_vars.$psp_chart_end_index$.bind(NIL, thread);
                                            {
                                                SubLObject chart_8 = psp_syntax.psp_chart_for_string(string, parsing_macros.get_psp_lexicon(), $NONE, UNPROVIDED);
                                                SubLObject local_state = psp_chart.psp_chart_memoization_state(chart_8);
                                                {
                                                    SubLObject _prev_bind_0_9 = memoization_state.$memoization_state$.currentBinding(thread);
                                                    try {
                                                        memoization_state.$memoization_state$.bind(local_state, thread);
                                                        {
                                                            SubLObject original_memoization_process = NIL;
                                                            if ((NIL != local_state) && (NIL == memoization_state.memoization_state_lock(local_state))) {
                                                                original_memoization_process = memoization_state.memoization_state_get_current_process_internal(local_state);
                                                                {
                                                                    SubLObject current_proc = current_process();
                                                                    if (NIL == original_memoization_process) {
                                                                        memoization_state.memoization_state_set_current_process_internal(local_state, current_proc);
                                                                    } else {
                                                                        if (original_memoization_process != current_proc) {
                                                                            Errors.error($str_alt4$Invalid_attempt_to_reuse_memoizat);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            try {
                                                                {
                                                                    SubLObject _prev_bind_0_10 = parsing_vars.$psp_chart$.currentBinding(thread);
                                                                    try {
                                                                        parsing_vars.$psp_chart$.bind(chart_8, thread);
                                                                        {
                                                                            SubLObject _prev_bind_0_11 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                                                                            try {
                                                                                parsing_vars.$psp_return_mode$.bind($BEST_ONLY, thread);
                                                                                parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                                                                            } finally {
                                                                                parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_11, thread);
                                                                            }
                                                                        }
                                                                        if (NIL != parses) {
                                                                        } else {
                                                                            if ((NIL != try_unknown_stringsP) && (NIL != psp_chart.psp_chart_has_unknown_stringsP(UNPROVIDED))) {
                                                                                parses = filter_noun_compound_parses_by_types(delete_duplicates(Mapping.mapcar(NPP_PARSE_CYCL, noun_compound_parser.parse_noun_compound(string, UNPROVIDED, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED), good_list, bad_list, ignore_subcolfnsP, UNPROVIDED);
                                                                            } else {
                                                                                {
                                                                                    SubLObject _prev_bind_0_12 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                                                                                    try {
                                                                                        parsing_vars.$psp_return_mode$.bind($EVERYTHING, thread);
                                                                                        parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                                                                                    } finally {
                                                                                        parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_12, thread);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        if (NIL == parsing_vars.$psp_dont_destroy_chartP$.getDynamicValue(thread)) {
                                                                            psp_chart.destroy_psp_chart(UNPROVIDED);
                                                                        }
                                                                    } finally {
                                                                        parsing_vars.$psp_chart$.rebind(_prev_bind_0_10, thread);
                                                                    }
                                                                }
                                                            } finally {
                                                                {
                                                                    SubLObject _prev_bind_0_13 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                                    try {
                                                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                                                        if ((NIL != local_state) && (NIL == original_memoization_process)) {
                                                                            memoization_state.memoization_state_set_current_process_internal(local_state, NIL);
                                                                        }
                                                                    } finally {
                                                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_13, thread);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } finally {
                                                        memoization_state.$memoization_state$.rebind(_prev_bind_0_9, thread);
                                                    }
                                                }
                                            }
                                        } finally {
                                            parsing_vars.$psp_chart_end_index$.rebind(_prev_bind_1, thread);
                                            parsing_vars.$psp_chart_start_index$.rebind(_prev_bind_0_7, thread);
                                        }
                                    }
                                }
                            }
                        } finally {
                            parsing_vars.$npp_max_rules_per_compound$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
                return parses;
            }
        }
    }

    // Definitions
    public static SubLObject parse_noun_compound_with_types(SubLObject string, final SubLObject good_list, final SubLObject bad_list, final SubLObject ignore_subcolfnsP, SubLObject instance_limit, SubLObject try_unknown_stringsP) {
        if (instance_limit == UNPROVIDED) {
            instance_limit = TEN_INTEGER;
        }
        if (try_unknown_stringsP == UNPROVIDED) {
            try_unknown_stringsP = T;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject parses = NIL;
        string = clean_string_for_parse_noun_compound(string);
        if (NIL != string_utilities.non_empty_stringP(string)) {
            final SubLObject _prev_bind_0 = parsing_vars.$npp_max_rules_per_compound$.currentBinding(thread);
            try {
                parsing_vars.$npp_max_rules_per_compound$.bind(TWO_INTEGER, thread);
                SubLObject start_index = NIL;
                SubLObject end_index = NIL;
                SubLObject chart = NIL;
                if ((NIL == parsing_vars.$psp_chart$.getDynamicValue(thread)) || (NIL != parsing_macros.psp_chart_matches_gap_type_p(parsing_vars.$psp_chart$.getDynamicValue(thread), $NONE))) {
                    thread.resetMultipleValues();
                    final SubLObject start_index_$1 = psp_main.psp_find_string_in_charts(string, parsing_vars.$psp_chart$.getDynamicValue(thread), parsing_vars.$psp_extra_charts$.getDynamicValue(thread));
                    final SubLObject end_index_$2 = thread.secondMultipleValue();
                    final SubLObject chart_$3 = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    start_index = start_index_$1;
                    end_index = end_index_$2;
                    chart = chart_$3;
                }
                if (NIL != start_index) {
                    final SubLObject _prev_bind_0_$4 = parsing_vars.$psp_chart_start_index$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = parsing_vars.$psp_chart_end_index$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = parsing_vars.$psp_chart$.currentBinding(thread);
                    try {
                        parsing_vars.$psp_chart_start_index$.bind(start_index, thread);
                        parsing_vars.$psp_chart_end_index$.bind(end_index, thread);
                        parsing_vars.$psp_chart$.bind(chart, thread);
                        psp_syntax.psp_chart_do_syntactic_parsing(chart, UNPROVIDED, UNPROVIDED);
                        final SubLObject _prev_bind_0_$5 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                        try {
                            parsing_vars.$psp_return_mode$.bind($BEST_ONLY, thread);
                            parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                        } finally {
                            parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_$5, thread);
                        }
                        if (NIL == parses) {
                            if ((NIL != try_unknown_stringsP) && (NIL != psp_chart.psp_chart_has_unknown_stringsP(UNPROVIDED))) {
                                parses = filter_noun_compound_parses_by_types(delete_duplicates(Mapping.mapcar(NPP_PARSE_CYCL, noun_compound_parser.parse_noun_compound(string, UNPROVIDED, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED), good_list, bad_list, ignore_subcolfnsP, UNPROVIDED);
                            } else {
                                final SubLObject _prev_bind_0_$6 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                                try {
                                    parsing_vars.$psp_return_mode$.bind($EVERYTHING, thread);
                                    parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                                } finally {
                                    parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_$6, thread);
                                }
                            }
                        }
                    } finally {
                        parsing_vars.$psp_chart$.rebind(_prev_bind_3, thread);
                        parsing_vars.$psp_chart_end_index$.rebind(_prev_bind_2, thread);
                        parsing_vars.$psp_chart_start_index$.rebind(_prev_bind_0_$4, thread);
                    }
                } else {
                    final SubLObject _prev_bind_0_$7 = parsing_vars.$psp_chart_start_index$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = parsing_vars.$psp_chart_end_index$.currentBinding(thread);
                    try {
                        parsing_vars.$psp_chart_start_index$.bind(ZERO_INTEGER, thread);
                        parsing_vars.$psp_chart_end_index$.bind(NIL, thread);
                        final SubLObject chart_$4 = psp_syntax.psp_chart_for_string(string, parsing_macros.get_psp_lexicon(), $NONE, UNPROVIDED);
                        final SubLObject local_state = psp_chart.psp_chart_memoization_state(chart_$4);
                        final SubLObject _prev_bind_0_$8 = memoization_state.$memoization_state$.currentBinding(thread);
                        try {
                            memoization_state.$memoization_state$.bind(local_state, thread);
                            final SubLObject original_memoization_process = memoization_state.memoization_state_original_process(local_state);
                            try {
                                final SubLObject _prev_bind_0_$9 = parsing_vars.$psp_chart$.currentBinding(thread);
                                try {
                                    parsing_vars.$psp_chart$.bind(chart_$4, thread);
                                    final SubLObject _prev_bind_0_$10 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                                    try {
                                        parsing_vars.$psp_return_mode$.bind($BEST_ONLY, thread);
                                        parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                                    } finally {
                                        parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_$10, thread);
                                    }
                                    if (NIL == parses) {
                                        if ((NIL != try_unknown_stringsP) && (NIL != psp_chart.psp_chart_has_unknown_stringsP(UNPROVIDED))) {
                                            parses = filter_noun_compound_parses_by_types(delete_duplicates(Mapping.mapcar(NPP_PARSE_CYCL, noun_compound_parser.parse_noun_compound(string, UNPROVIDED, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED), good_list, bad_list, ignore_subcolfnsP, UNPROVIDED);
                                        } else {
                                            final SubLObject _prev_bind_0_$11 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                                            try {
                                                parsing_vars.$psp_return_mode$.bind($EVERYTHING, thread);
                                                parses = parse_noun_compound_with_types_int(string, good_list, bad_list, ignore_subcolfnsP, instance_limit);
                                            } finally {
                                                parsing_vars.$psp_return_mode$.rebind(_prev_bind_0_$11, thread);
                                            }
                                        }
                                    }
                                    if (NIL == parsing_vars.$psp_dont_destroy_chartP$.getDynamicValue(thread)) {
                                        psp_chart.destroy_psp_chart(UNPROVIDED);
                                    }
                                } finally {
                                    parsing_vars.$psp_chart$.rebind(_prev_bind_0_$9, thread);
                                }
                            } finally {
                                final SubLObject _prev_bind_0_$12 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    memoization_state.memoization_state_possibly_clear_original_process(local_state, original_memoization_process);
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$12, thread);
                                }
                            }
                        } finally {
                            memoization_state.$memoization_state$.rebind(_prev_bind_0_$8, thread);
                        }
                    } finally {
                        parsing_vars.$psp_chart_end_index$.rebind(_prev_bind_2, thread);
                        parsing_vars.$psp_chart_start_index$.rebind(_prev_bind_0_$7, thread);
                    }
                }
            } finally {
                parsing_vars.$npp_max_rules_per_compound$.rebind(_prev_bind_0, thread);
            }
        }
        return parses;
    }

    public static final SubLObject parse_noun_compound_with_types_int_alt(SubLObject string, SubLObject good_list, SubLObject bad_list, SubLObject ignore_subcolfnsP, SubLObject instance_limit) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject parses = NIL;
                {
                    SubLObject _prev_bind_0 = parsing_vars.$npp_use_nl_tagsP$.currentBinding(thread);
                    SubLObject _prev_bind_1 = parsing_vars.$use_ternary_np_tagsP$.currentBinding(thread);
                    try {
                        parsing_vars.$npp_use_nl_tagsP$.bind(T, thread);
                        parsing_vars.$use_ternary_np_tagsP$.bind(NIL, thread);
                        parses = psp_main.ps_get_cycls_for_phrase(string, $ANY, $ANY, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    } finally {
                        parsing_vars.$use_ternary_np_tagsP$.rebind(_prev_bind_1, thread);
                        parsing_vars.$npp_use_nl_tagsP$.rebind(_prev_bind_0, thread);
                    }
                }
                if (NIL != list_utilities.non_empty_list_p(parses)) {
                    if ((NIL != subl_promotions.positive_integer_p(instance_limit)) && (NIL == noun_compound_parses_mostly_definiteP(parses))) {
                        instance_limit = ZERO_INTEGER;
                    }
                    parses = strip_nl_tags(parses, UNPROVIDED);
                    parses = append(parses, instances_of_collection_parses(parses, instance_limit));
                    parses = filter_noun_compound_parses_by_types(parses, good_list, bad_list, ignore_subcolfnsP, UNPROVIDED);
                }
                return parses;
            }
        }
    }

    public static SubLObject parse_noun_compound_with_types_int(final SubLObject string, final SubLObject good_list, final SubLObject bad_list, final SubLObject ignore_subcolfnsP, SubLObject instance_limit) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject parses = NIL;
        final SubLObject _prev_bind_0 = parsing_vars.$npp_use_nl_tagsP$.currentBinding(thread);
        final SubLObject _prev_bind_2 = parsing_vars.$use_ternary_np_tagsP$.currentBinding(thread);
        try {
            parsing_vars.$npp_use_nl_tagsP$.bind(T, thread);
            parsing_vars.$use_ternary_np_tagsP$.bind(NIL, thread);
            parses = psp_main.ps_get_cycls_for_phrase(string, $ANY, $ANY, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        } finally {
            parsing_vars.$use_ternary_np_tagsP$.rebind(_prev_bind_2, thread);
            parsing_vars.$npp_use_nl_tagsP$.rebind(_prev_bind_0, thread);
        }
        if (NIL != list_utilities.non_empty_list_p(parses)) {
            if ((NIL != subl_promotions.positive_integer_p(instance_limit)) && (NIL == noun_compound_parses_mostly_definiteP(parses))) {
                instance_limit = ZERO_INTEGER;
            }
            parses = strip_nl_tags(parses, UNPROVIDED);
            parses = append(parses, instances_of_collection_parses(parses, instance_limit));
            parses = filter_noun_compound_parses_by_types(parses, good_list, bad_list, ignore_subcolfnsP, UNPROVIDED);
        }
        return parses;
    }

    public static final SubLObject noun_compound_parses_mostly_definiteP_alt(SubLObject parses) {
        {
            SubLObject threshold = divide(length(parses), TWO_INTEGER);
            SubLObject number_of_definite = ZERO_INTEGER;
            SubLObject rest = NIL;
            for (rest = parses; !(number_of_definite.numGE(threshold) || (NIL == rest)); rest = rest.rest()) {
                {
                    SubLObject cycl = rest.first();
                    if ($$Definite_NLAttr == nl_definiteness_attr_for_cycl(cycl)) {
                        number_of_definite = add(number_of_definite, ONE_INTEGER);
                    }
                }
            }
            return numGE(number_of_definite, threshold);
        }
    }

    public static SubLObject noun_compound_parses_mostly_definiteP(final SubLObject parses) {
        SubLObject threshold;
        SubLObject number_of_definite;
        SubLObject rest;
        SubLObject cycl;
        for (threshold = divide(length(parses), TWO_INTEGER), number_of_definite = ZERO_INTEGER, rest = NIL, rest = parses; (!number_of_definite.numGE(threshold)) && (NIL != rest); rest = rest.rest()) {
            cycl = rest.first();
            if ($$Definite_NLAttr.eql(nl_definiteness_attr_for_cycl(cycl))) {
                number_of_definite = add(number_of_definite, ONE_INTEGER);
            }
        }
        return numGE(number_of_definite, threshold);
    }

    /**
     * If an element of PARSES is a collection with no more than INSTANCE-LIMIT instances, find its instances;
     * return a list containing all instances of all such collections in PARSES.
     *
     * @return listp of cycl-term-p
     */
    @LispMethod(comment = "If an element of PARSES is a collection with no more than INSTANCE-LIMIT instances, find its instances;\r\nreturn a list containing all instances of all such collections in PARSES.\r\n\r\n@return listp of cycl-term-p\nIf an element of PARSES is a collection with no more than INSTANCE-LIMIT instances, find its instances;\nreturn a list containing all instances of all such collections in PARSES.")
    public static final SubLObject instances_of_collection_parses_alt(SubLObject parses, SubLObject instance_limit) {
        {
            SubLObject result = NIL;
            if (!instance_limit.isZero()) {
                {
                    SubLObject cdolist_list_var = parses;
                    SubLObject parse = NIL;
                    for (parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parse = cdolist_list_var.first()) {
                        if (NIL != fort_types_interface.isa_collectionP(parse, UNPROVIDED)) {
                            result = append(result, instances_of_collection_parse(parse, instance_limit));
                        }
                    }
                }
            }
            return list_utilities.fast_delete_duplicates(result, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
    }

    /**
     * If an element of PARSES is a collection with no more than INSTANCE-LIMIT instances, find its instances;
     * return a list containing all instances of all such collections in PARSES.
     *
     * @return listp of cycl-term-p
     */
    @LispMethod(comment = "If an element of PARSES is a collection with no more than INSTANCE-LIMIT instances, find its instances;\r\nreturn a list containing all instances of all such collections in PARSES.\r\n\r\n@return listp of cycl-term-p\nIf an element of PARSES is a collection with no more than INSTANCE-LIMIT instances, find its instances;\nreturn a list containing all instances of all such collections in PARSES.")
    public static SubLObject instances_of_collection_parses(final SubLObject parses, final SubLObject instance_limit) {
        SubLObject result = NIL;
        if (!instance_limit.isZero()) {
            SubLObject cdolist_list_var = parses;
            SubLObject parse = NIL;
            parse = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL != fort_types_interface.isa_collectionP(parse, UNPROVIDED)) {
                    result = append(result, instances_of_collection_parse(parse, instance_limit));
                }
                cdolist_list_var = cdolist_list_var.rest();
                parse = cdolist_list_var.first();
            } 
        }
        return list_utilities.fast_delete_duplicates(result, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject instances_of_collection_parse_internal_alt(SubLObject parse, SubLObject instance_limit) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if ((NIL != forts.fort_p(parse)) && cardinality_estimates.instance_cardinality(parse).numG(instance_limit)) {
                return NIL;
            }
            {
                SubLObject el_variable = $sym8$_X;
                SubLObject query_sentence = make_binary_formula($$isa, el_variable, parse);
                SubLObject query_mt = parsing_vars.$parsing_domain_mt$.getDynamicValue(thread);
                SubLObject query_max_number = number_utilities.f_1X(instance_limit);
                SubLObject query_properties = list($MAX_NUMBER, query_max_number, $MAX_TIME, TWELVE_INTEGER, $MAX_TRANSFORMATION_DEPTH, ZERO_INTEGER);
                SubLObject v_instances = ask_utilities.query_variable(el_variable, query_sentence, query_mt, query_properties);
                if (length(v_instances).numG(instance_limit)) {
                    return NIL;
                } else {
                    return v_instances;
                }
            }
        }
    }

    public static SubLObject instances_of_collection_parse_internal(final SubLObject parse, final SubLObject instance_limit) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL != forts.fort_p(parse)) && cardinality_estimates.instance_cardinality(parse).numG(instance_limit)) {
            return NIL;
        }
        final SubLObject el_variable = $sym7$_X;
        final SubLObject query_sentence = make_binary_formula($$isa, el_variable, parse);
        final SubLObject query_mt = parsing_vars.$parsing_domain_mt$.getDynamicValue(thread);
        final SubLObject query_max_number = number_utilities.f_1X(instance_limit);
        final SubLObject query_properties = list($MAX_NUMBER, query_max_number, $MAX_TIME, TWELVE_INTEGER, $MAX_TRANSFORMATION_DEPTH, ZERO_INTEGER);
        final SubLObject v_instances = ask_utilities.query_variable(el_variable, query_sentence, query_mt, query_properties);
        if (length(v_instances).numG(instance_limit)) {
            return NIL;
        }
        return v_instances;
    }

    public static final SubLObject instances_of_collection_parse_alt(SubLObject parse, SubLObject instance_limit) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return instances_of_collection_parse_internal(parse, instance_limit);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, INSTANCES_OF_COLLECTION_PARSE, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), INSTANCES_OF_COLLECTION_PARSE, TWO_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, INSTANCES_OF_COLLECTION_PARSE, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_2(parse, instance_limit);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw13$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (parse.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && instance_limit.equal(cached_args.first())) {
                                            return memoization_state.caching_results(results2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(instances_of_collection_parse_internal(parse, instance_limit)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(parse, instance_limit));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject instances_of_collection_parse(final SubLObject parse, final SubLObject instance_limit) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return instances_of_collection_parse_internal(parse, instance_limit);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, INSTANCES_OF_COLLECTION_PARSE, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), INSTANCES_OF_COLLECTION_PARSE, TWO_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, INSTANCES_OF_COLLECTION_PARSE, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(parse, instance_limit);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (parse.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && instance_limit.equal(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(instances_of_collection_parse_internal(parse, instance_limit)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(parse, instance_limit));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject clean_string_for_parse_noun_compound_alt(SubLObject string) {
        return list_utilities.remove_if_not(PARSE_NOUN_COMPOUND_VALID_CHAR_P, string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject clean_string_for_parse_noun_compound(final SubLObject string) {
        return list_utilities.remove_if_not(PARSE_NOUN_COMPOUND_VALID_CHAR_P, string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject filter_noun_compound_parses_by_types_alt(SubLObject parses, SubLObject good_list, SubLObject bad_list, SubLObject ignore_subcolfnsP, SubLObject mt) {
        if (ignore_subcolfnsP == UNPROVIDED) {
            ignore_subcolfnsP = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parsing_domain_mt$.getDynamicValue();
        }
        {
            SubLObject for_typing = NIL;
            if (NIL != ignore_subcolfnsP) {
                {
                    SubLObject cdolist_list_var = parses;
                    SubLObject parse = NIL;
                    for (parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parse = cdolist_list_var.first()) {
                        if (NIL == reformulator_module_quantifier_unifier_3.subcollection_function_nautP(parse, $$UniversalVocabularyMt)) {
                            for_typing = cons(parse, for_typing);
                        }
                    }
                }
            } else {
                for_typing = parses;
            }
            return restrict_parses_by_types(for_typing, good_list, bad_list, mt);
        }
    }

    public static SubLObject filter_noun_compound_parses_by_types(final SubLObject parses, final SubLObject good_list, final SubLObject bad_list, SubLObject ignore_subcolfnsP, SubLObject mt) {
        if (ignore_subcolfnsP == UNPROVIDED) {
            ignore_subcolfnsP = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parsing_domain_mt$.getDynamicValue();
        }
        SubLObject for_typing = NIL;
        if (NIL != ignore_subcolfnsP) {
            SubLObject cdolist_list_var = parses;
            SubLObject parse = NIL;
            parse = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL == reformulator_module_quantifier_unifier_3.subcollection_function_nautP(parse, $$UniversalVocabularyMt)) {
                    for_typing = cons(parse, for_typing);
                }
                cdolist_list_var = cdolist_list_var.rest();
                parse = cdolist_list_var.first();
            } 
        } else {
            for_typing = parses;
        }
        return restrict_parses_by_types(for_typing, good_list, bad_list, mt);
    }

    public static final SubLObject parse_noun_compound_valid_char_p_alt(SubLObject v_object) {
        if (!v_object.isChar()) {
            return NIL;
        } else {
            if (NIL != find(v_object, $parse_noun_compound_valid_non_alpha_chars$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                return T;
            } else {
                return alpha_char_p(v_object);
            }
        }
    }

    public static SubLObject parse_noun_compound_valid_char_p(final SubLObject v_object) {
        if (!v_object.isChar()) {
            return NIL;
        }
        if (NIL != find(v_object, $parse_noun_compound_valid_non_alpha_chars$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
            return T;
        }
        return alpha_char_p(v_object);
    }

    public static final SubLObject clear_nl_tags_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return dictionary.clear_dictionary($nl_tags$.getDynamicValue(thread));
        }
    }

    public static SubLObject clear_nl_tags() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return dictionary.clear_dictionary($nl_tags$.getDynamicValue(thread));
    }

    public static final SubLObject clear_nl_tag_fns_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return dictionary.clear_dictionary($nl_tag_fns$.getDynamicValue(thread));
        }
    }

    public static SubLObject clear_nl_tag_fns() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return dictionary.clear_dictionary($nl_tag_fns$.getDynamicValue(thread));
    }

    public static final SubLObject clear_nl_tag_denoting_fns_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return dictionary.clear_dictionary($nl_tag_denoting_fns$.getDynamicValue(thread));
        }
    }

    public static SubLObject clear_nl_tag_denoting_fns() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return dictionary.clear_dictionary($nl_tag_denoting_fns$.getDynamicValue(thread));
    }

    public static final SubLObject initialize_nl_tags_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            clear_nl_tags();
            {
                SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                try {
                    mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                    mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                    {
                        SubLObject node_var = $$NLUtteranceAttribute;
                        SubLObject deck_type = (false) ? ((SubLObject) ($QUEUE)) : $STACK;
                        SubLObject recur_deck = deck.create_deck(deck_type);
                        {
                            SubLObject _prev_bind_0_14 = sbhl_marking_vars.$sbhl_space$.currentBinding(thread);
                            try {
                                sbhl_marking_vars.$sbhl_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
                                {
                                    SubLObject tv_var = NIL;
                                    {
                                        SubLObject _prev_bind_0_15 = sbhl_search_vars.$sbhl_tv$.currentBinding(thread);
                                        SubLObject _prev_bind_1_16 = sbhl_search_vars.$relevant_sbhl_tv_function$.currentBinding(thread);
                                        try {
                                            sbhl_search_vars.$sbhl_tv$.bind(NIL != tv_var ? ((SubLObject) (tv_var)) : sbhl_search_vars.get_sbhl_true_tv(), thread);
                                            sbhl_search_vars.$relevant_sbhl_tv_function$.bind(NIL != tv_var ? ((SubLObject) (RELEVANT_SBHL_TV_IS_GENERAL_TV)) : sbhl_search_vars.$relevant_sbhl_tv_function$.getDynamicValue(thread), thread);
                                            if (NIL != tv_var) {
                                                if (NIL != sbhl_paranoia.sbhl_object_type_checking_p()) {
                                                    if (NIL == sbhl_search_vars.sbhl_true_tv_p(tv_var)) {
                                                        {
                                                            SubLObject pcase_var = sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread);
                                                            if (pcase_var.eql($ERROR)) {
                                                                sbhl_paranoia.sbhl_error(ONE_INTEGER, $str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                            } else {
                                                                if (pcase_var.eql($CERROR)) {
                                                                    sbhl_paranoia.sbhl_cerror(ONE_INTEGER, $$$continue_anyway, $str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                } else {
                                                                    if (pcase_var.eql($WARN)) {
                                                                        Errors.warn($str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                                                    } else {
                                                                        Errors.warn($str_alt30$_A_is_not_a_valid__sbhl_type_erro, sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread));
                                                                        Errors.cerror($$$continue_anyway, $str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            {
                                                SubLObject _prev_bind_0_17 = sbhl_search_vars.$sbhl_search_module$.currentBinding(thread);
                                                SubLObject _prev_bind_1_18 = sbhl_search_vars.$sbhl_search_module_type$.currentBinding(thread);
                                                SubLObject _prev_bind_2 = sbhl_search_vars.$sbhl_add_node_to_result_test$.currentBinding(thread);
                                                SubLObject _prev_bind_3 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                SubLObject _prev_bind_4 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                try {
                                                    sbhl_search_vars.$sbhl_search_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                                                    sbhl_search_vars.$sbhl_search_module_type$.bind(sbhl_module_utilities.get_sbhl_module_type(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.bind(sbhl_module_utilities.get_sbhl_add_node_to_result_test(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                                    sbhl_module_vars.$sbhl_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                                                    if ((NIL != sbhl_paranoia.suspend_sbhl_type_checkingP()) || (NIL != sbhl_module_utilities.apply_sbhl_module_type_test($$NLUtteranceAttribute, sbhl_module_vars.get_sbhl_module(UNPROVIDED)))) {
                                                        {
                                                            SubLObject _prev_bind_0_19 = sbhl_search_vars.$sbhl_search_direction$.currentBinding(thread);
                                                            SubLObject _prev_bind_1_20 = sbhl_link_vars.$sbhl_link_direction$.currentBinding(thread);
                                                            SubLObject _prev_bind_2_21 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                            try {
                                                                sbhl_search_vars.$sbhl_search_direction$.bind(sbhl_search_vars.get_sbhl_backward_search_direction(), thread);
                                                                sbhl_link_vars.$sbhl_link_direction$.bind(sbhl_module_utilities.sbhl_search_direction_to_link_direction(sbhl_search_vars.get_sbhl_backward_search_direction(), sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                                                sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                                                sbhl_marking_utilities.sbhl_mark_node_marked(node_var, UNPROVIDED);
                                                                while (NIL != node_var) {
                                                                    {
                                                                        SubLObject sp_type = node_var;
                                                                        SubLObject pred_var = $$isa;
                                                                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(sp_type, TWO_INTEGER, pred_var)) {
                                                                            {
                                                                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(sp_type, TWO_INTEGER, pred_var);
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
                                                                                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                                                                                    {
                                                                                                        SubLObject done_var_22 = NIL;
                                                                                                        SubLObject token_var_23 = NIL;
                                                                                                        while (NIL == done_var_22) {
                                                                                                            {
                                                                                                                SubLObject as = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_23);
                                                                                                                SubLObject valid_24 = makeBoolean(token_var_23 != as);
                                                                                                                if (NIL != valid_24) {
                                                                                                                    {
                                                                                                                        SubLObject sp = assertions_high.gaf_arg1(as);
                                                                                                                        SubLObject mt = assertions_high.assertion_mt(as);
                                                                                                                        dictionary_utilities.dictionary_pushnew($nl_tags$.getDynamicValue(thread), sp, mt, UNPROVIDED, UNPROVIDED);
                                                                                                                    }
                                                                                                                }
                                                                                                                done_var_22 = makeBoolean(NIL == valid_24);
                                                                                                            }
                                                                                                        } 
                                                                                                    }
                                                                                                } finally {
                                                                                                    {
                                                                                                        SubLObject _prev_bind_0_25 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                                                                        try {
                                                                                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                                                                                            if (NIL != final_index_iterator) {
                                                                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                                                                            }
                                                                                                        } finally {
                                                                                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_25, thread);
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
                                                                    }
                                                                    {
                                                                        SubLObject accessible_modules = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_vars.get_sbhl_module($$genls));
                                                                        SubLObject cdolist_list_var = accessible_modules;
                                                                        SubLObject module_var = NIL;
                                                                        for (module_var = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , module_var = cdolist_list_var.first()) {
                                                                            {
                                                                                SubLObject _prev_bind_0_26 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                                                SubLObject _prev_bind_1_27 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                                                try {
                                                                                    sbhl_module_vars.$sbhl_module$.bind(module_var, thread);
                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? ((SubLObject) (makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)))) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                                                    {
                                                                                        SubLObject node = function_terms.naut_to_nart(node_var);
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
                                                                                                                        SubLObject mt = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                                                                                                        SubLObject tv_links = thread.secondMultipleValue();
                                                                                                                        thread.resetMultipleValues();
                                                                                                                        if (NIL != mt_relevance_macros.relevant_mtP(mt)) {
                                                                                                                            {
                                                                                                                                SubLObject _prev_bind_0_28 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                                                                                try {
                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.bind(mt, thread);
                                                                                                                                    {
                                                                                                                                        SubLObject iteration_state_29 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links));
                                                                                                                                        while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_29)) {
                                                                                                                                            thread.resetMultipleValues();
                                                                                                                                            {
                                                                                                                                                SubLObject tv = dictionary_contents.do_dictionary_contents_key_value(iteration_state_29);
                                                                                                                                                SubLObject link_nodes = thread.secondMultipleValue();
                                                                                                                                                thread.resetMultipleValues();
                                                                                                                                                if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                                                                                    {
                                                                                                                                                        SubLObject _prev_bind_0_30 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                                                                                        try {
                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                                                                                            {
                                                                                                                                                                SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                                                                SubLObject cdolist_list_var_31 = new_list;
                                                                                                                                                                SubLObject node_vars_link_node = NIL;
                                                                                                                                                                for (node_vars_link_node = cdolist_list_var_31.first(); NIL != cdolist_list_var_31; cdolist_list_var_31 = cdolist_list_var_31.rest() , node_vars_link_node = cdolist_list_var_31.first()) {
                                                                                                                                                                    if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED)) {
                                                                                                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                                                                                        deck.deck_push(node_vars_link_node, recur_deck);
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        } finally {
                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_30, thread);
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                iteration_state_29 = dictionary_contents.do_dictionary_contents_next(iteration_state_29);
                                                                                                                                            }
                                                                                                                                        } 
                                                                                                                                        dictionary_contents.do_dictionary_contents_finalize(iteration_state_29);
                                                                                                                                    }
                                                                                                                                } finally {
                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_28, thread);
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
                                                                                                    sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str_alt34$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (NIL != obsolete.cnat_p(node, UNPROVIDED)) {
                                                                                                {
                                                                                                    SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                    SubLObject cdolist_list_var_32 = new_list;
                                                                                                    SubLObject generating_fn = NIL;
                                                                                                    for (generating_fn = cdolist_list_var_32.first(); NIL != cdolist_list_var_32; cdolist_list_var_32 = cdolist_list_var_32.rest() , generating_fn = cdolist_list_var_32.first()) {
                                                                                                        {
                                                                                                            SubLObject _prev_bind_0_33 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                                                                            try {
                                                                                                                sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn, thread);
                                                                                                                {
                                                                                                                    SubLObject link_nodes = funcall(generating_fn, node);
                                                                                                                    SubLObject new_list_34 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                    SubLObject cdolist_list_var_35 = new_list_34;
                                                                                                                    SubLObject node_vars_link_node = NIL;
                                                                                                                    for (node_vars_link_node = cdolist_list_var_35.first(); NIL != cdolist_list_var_35; cdolist_list_var_35 = cdolist_list_var_35.rest() , node_vars_link_node = cdolist_list_var_35.first()) {
                                                                                                                        if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED)) {
                                                                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                                            deck.deck_push(node_vars_link_node, recur_deck);
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            } finally {
                                                                                                                sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_33, thread);
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } finally {
                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_27, thread);
                                                                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_26, thread);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    node_var = deck.deck_pop(recur_deck);
                                                                } 
                                                            } finally {
                                                                sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_2_21, thread);
                                                                sbhl_link_vars.$sbhl_link_direction$.rebind(_prev_bind_1_20, thread);
                                                                sbhl_search_vars.$sbhl_search_direction$.rebind(_prev_bind_0_19, thread);
                                                            }
                                                        }
                                                    } else {
                                                        sbhl_paranoia.sbhl_warn(TWO_INTEGER, $str_alt35$Node__a_does_not_pass_sbhl_type_t, $$NLUtteranceAttribute, sbhl_module_utilities.get_sbhl_type_test(sbhl_module_vars.get_sbhl_module(UNPROVIDED)), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                    }
                                                } finally {
                                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_4, thread);
                                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_3, thread);
                                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.rebind(_prev_bind_2, thread);
                                                    sbhl_search_vars.$sbhl_search_module_type$.rebind(_prev_bind_1_18, thread);
                                                    sbhl_search_vars.$sbhl_search_module$.rebind(_prev_bind_0_17, thread);
                                                }
                                            }
                                        } finally {
                                            sbhl_search_vars.$relevant_sbhl_tv_function$.rebind(_prev_bind_1_16, thread);
                                            sbhl_search_vars.$sbhl_tv$.rebind(_prev_bind_0_15, thread);
                                        }
                                    }
                                    sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_space$.getDynamicValue(thread));
                                }
                            } finally {
                                sbhl_marking_vars.$sbhl_space$.rebind(_prev_bind_0_14, thread);
                            }
                        }
                    }
                } finally {
                    mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                    mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                }
            }
            return $nl_tags$.getDynamicValue(thread);
        }
    }

    public static SubLObject initialize_nl_tags() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        clear_nl_tags();
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
            mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
            SubLObject node_var = $$NLUtteranceAttribute;
            final SubLObject deck_type = $STACK;
            final SubLObject recur_deck = deck.create_deck(deck_type);
            final SubLObject _prev_bind_0_$14 = sbhl_marking_vars.$sbhl_space$.currentBinding(thread);
            try {
                sbhl_marking_vars.$sbhl_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
                try {
                    final SubLObject tv_var = NIL;
                    final SubLObject _prev_bind_0_$15 = sbhl_search_vars.$sbhl_tv$.currentBinding(thread);
                    final SubLObject _prev_bind_1_$16 = sbhl_search_vars.$relevant_sbhl_tv_function$.currentBinding(thread);
                    try {
                        sbhl_search_vars.$sbhl_tv$.bind(NIL != tv_var ? tv_var : sbhl_search_vars.get_sbhl_true_tv(), thread);
                        sbhl_search_vars.$relevant_sbhl_tv_function$.bind(NIL != tv_var ? RELEVANT_SBHL_TV_IS_GENERAL_TV : sbhl_search_vars.$relevant_sbhl_tv_function$.getDynamicValue(thread), thread);
                        if (((NIL != tv_var) && (NIL != sbhl_paranoia.sbhl_object_type_checking_p())) && (NIL == sbhl_search_vars.sbhl_true_tv_p(tv_var))) {
                            final SubLObject pcase_var = sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread);
                            if (pcase_var.eql($ERROR)) {
                                sbhl_paranoia.sbhl_error(ONE_INTEGER, $str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            } else
                                if (pcase_var.eql($CERROR)) {
                                    sbhl_paranoia.sbhl_cerror(ONE_INTEGER, $$$continue_anyway, $str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                } else
                                    if (pcase_var.eql($WARN)) {
                                        Errors.warn($str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                    } else {
                                        Errors.warn($str28$_A_is_not_a_valid__sbhl_type_erro, sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread));
                                        Errors.cerror($$$continue_anyway, $str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                    }


                        }
                        final SubLObject _prev_bind_0_$16 = sbhl_search_vars.$sbhl_search_module$.currentBinding(thread);
                        final SubLObject _prev_bind_1_$17 = sbhl_search_vars.$sbhl_search_module_type$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = sbhl_search_vars.$sbhl_add_node_to_result_test$.currentBinding(thread);
                        final SubLObject _prev_bind_4 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                        final SubLObject _prev_bind_5 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                        try {
                            sbhl_search_vars.$sbhl_search_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                            sbhl_search_vars.$sbhl_search_module_type$.bind(sbhl_module_utilities.get_sbhl_module_type(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                            sbhl_search_vars.$sbhl_add_node_to_result_test$.bind(sbhl_module_utilities.get_sbhl_add_node_to_result_test(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                            sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                            sbhl_module_vars.$sbhl_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                            if ((NIL != sbhl_paranoia.suspend_sbhl_type_checkingP()) || (NIL != sbhl_module_utilities.apply_sbhl_module_type_test($$NLUtteranceAttribute, sbhl_module_vars.get_sbhl_module(UNPROVIDED)))) {
                                final SubLObject _prev_bind_0_$17 = sbhl_search_vars.$sbhl_search_direction$.currentBinding(thread);
                                final SubLObject _prev_bind_1_$18 = sbhl_link_vars.$sbhl_link_direction$.currentBinding(thread);
                                final SubLObject _prev_bind_2_$21 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                try {
                                    sbhl_search_vars.$sbhl_search_direction$.bind(sbhl_search_vars.get_sbhl_backward_search_direction(), thread);
                                    sbhl_link_vars.$sbhl_link_direction$.bind(sbhl_module_utilities.sbhl_search_direction_to_link_direction(sbhl_search_vars.get_sbhl_backward_search_direction(), sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                    sbhl_marking_utilities.sbhl_mark_node_marked(node_var, UNPROVIDED);
                                    while (NIL != node_var) {
                                        final SubLObject sp_type = node_var;
                                        final SubLObject pred_var = $$isa;
                                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(sp_type, TWO_INTEGER, pred_var)) {
                                            final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(sp_type, TWO_INTEGER, pred_var);
                                            SubLObject done_var = NIL;
                                            final SubLObject token_var = NIL;
                                            while (NIL == done_var) {
                                                final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                                                final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                                                if (NIL != valid) {
                                                    SubLObject final_index_iterator = NIL;
                                                    try {
                                                        final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                                        SubLObject done_var_$22 = NIL;
                                                        final SubLObject token_var_$23 = NIL;
                                                        while (NIL == done_var_$22) {
                                                            final SubLObject as = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$23);
                                                            final SubLObject valid_$24 = makeBoolean(!token_var_$23.eql(as));
                                                            if (NIL != valid_$24) {
                                                                final SubLObject sp = assertions_high.gaf_arg1(as);
                                                                final SubLObject mt = assertions_high.assertion_mt(as);
                                                                dictionary_utilities.dictionary_pushnew($nl_tags$.getDynamicValue(thread), sp, mt, UNPROVIDED, UNPROVIDED);
                                                            }
                                                            done_var_$22 = makeBoolean(NIL == valid_$24);
                                                        } 
                                                    } finally {
                                                        final SubLObject _prev_bind_0_$18 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                        try {
                                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                                            final SubLObject _values = getValuesAsVector();
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                            restoreValuesFromVector(_values);
                                                        } finally {
                                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$18, thread);
                                                        }
                                                    }
                                                }
                                                done_var = makeBoolean(NIL == valid);
                                            } 
                                        }
                                        SubLObject cdolist_list_var;
                                        final SubLObject accessible_modules = cdolist_list_var = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_vars.get_sbhl_module($$genls));
                                        SubLObject module_var = NIL;
                                        module_var = cdolist_list_var.first();
                                        while (NIL != cdolist_list_var) {
                                            final SubLObject _prev_bind_0_$19 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                            final SubLObject _prev_bind_1_$19 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                            try {
                                                sbhl_module_vars.$sbhl_module$.bind(module_var, thread);
                                                sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                final SubLObject node = function_terms.naut_to_nart(node_var);
                                                if (NIL != sbhl_link_vars.sbhl_node_object_p(node)) {
                                                    final SubLObject d_link = sbhl_graphs.get_sbhl_graph_link(node, sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                    if (NIL != d_link) {
                                                        final SubLObject mt_links = sbhl_links.get_sbhl_mt_links(d_link, sbhl_link_vars.get_sbhl_link_direction(), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                        if (NIL != mt_links) {
                                                            SubLObject iteration_state;
                                                            for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(mt_links)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                                                                thread.resetMultipleValues();
                                                                final SubLObject mt2 = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                                                final SubLObject tv_links = thread.secondMultipleValue();
                                                                thread.resetMultipleValues();
                                                                if (NIL != mt_relevance_macros.relevant_mtP(mt2)) {
                                                                    final SubLObject _prev_bind_0_$20 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                    try {
                                                                        sbhl_link_vars.$sbhl_link_mt$.bind(mt2, thread);
                                                                        SubLObject iteration_state_$29;
                                                                        for (iteration_state_$29 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_$29); iteration_state_$29 = dictionary_contents.do_dictionary_contents_next(iteration_state_$29)) {
                                                                            thread.resetMultipleValues();
                                                                            final SubLObject tv = dictionary_contents.do_dictionary_contents_key_value(iteration_state_$29);
                                                                            final SubLObject link_nodes = thread.secondMultipleValue();
                                                                            thread.resetMultipleValues();
                                                                            if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                final SubLObject _prev_bind_0_$21 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                try {
                                                                                    sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                    final SubLObject sol = link_nodes;
                                                                                    if (NIL != set.set_p(sol)) {
                                                                                        final SubLObject set_contents_var = set.do_set_internal(sol);
                                                                                        SubLObject basis_object;
                                                                                        SubLObject state;
                                                                                        SubLObject node_vars_link_node;
                                                                                        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                                                                            node_vars_link_node = set_contents.do_set_contents_next(basis_object, state);
                                                                                            if ((NIL != set_contents.do_set_contents_element_validP(state, node_vars_link_node)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED))) {
                                                                                                sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                deck.deck_push(node_vars_link_node, recur_deck);
                                                                                            }
                                                                                        }
                                                                                    } else
                                                                                        if (sol.isList()) {
                                                                                            SubLObject csome_list_var = sol;
                                                                                            SubLObject node_vars_link_node2 = NIL;
                                                                                            node_vars_link_node2 = csome_list_var.first();
                                                                                            while (NIL != csome_list_var) {
                                                                                                if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node2, UNPROVIDED)) {
                                                                                                    sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node2, UNPROVIDED);
                                                                                                    deck.deck_push(node_vars_link_node2, recur_deck);
                                                                                                }
                                                                                                csome_list_var = csome_list_var.rest();
                                                                                                node_vars_link_node2 = csome_list_var.first();
                                                                                            } 
                                                                                        } else {
                                                                                            Errors.error($str32$_A_is_neither_SET_P_nor_LISTP_, sol);
                                                                                        }

                                                                                } finally {
                                                                                    sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_$21, thread);
                                                                                }
                                                                            }
                                                                        }
                                                                        dictionary_contents.do_dictionary_contents_finalize(iteration_state_$29);
                                                                    } finally {
                                                                        sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_$20, thread);
                                                                    }
                                                                }
                                                            }
                                                            dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                                                        }
                                                    } else {
                                                        sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str33$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                    }
                                                } else
                                                    if (NIL != obsolete.cnat_p(node, UNPROVIDED)) {
                                                        SubLObject cdolist_list_var_$31;
                                                        final SubLObject new_list = cdolist_list_var_$31 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                        SubLObject generating_fn = NIL;
                                                        generating_fn = cdolist_list_var_$31.first();
                                                        while (NIL != cdolist_list_var_$31) {
                                                            final SubLObject _prev_bind_0_$22 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                            try {
                                                                sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn, thread);
                                                                final SubLObject sol2;
                                                                final SubLObject link_nodes2 = sol2 = funcall(generating_fn, node);
                                                                if (NIL != set.set_p(sol2)) {
                                                                    final SubLObject set_contents_var2 = set.do_set_internal(sol2);
                                                                    SubLObject basis_object2;
                                                                    SubLObject state2;
                                                                    SubLObject node_vars_link_node3;
                                                                    for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2, state2); state2 = set_contents.do_set_contents_update_state(state2)) {
                                                                        node_vars_link_node3 = set_contents.do_set_contents_next(basis_object2, state2);
                                                                        if ((NIL != set_contents.do_set_contents_element_validP(state2, node_vars_link_node3)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node3, UNPROVIDED))) {
                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node3, UNPROVIDED);
                                                                            deck.deck_push(node_vars_link_node3, recur_deck);
                                                                        }
                                                                    }
                                                                } else
                                                                    if (sol2.isList()) {
                                                                        SubLObject csome_list_var2 = sol2;
                                                                        SubLObject node_vars_link_node4 = NIL;
                                                                        node_vars_link_node4 = csome_list_var2.first();
                                                                        while (NIL != csome_list_var2) {
                                                                            if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node4, UNPROVIDED)) {
                                                                                sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node4, UNPROVIDED);
                                                                                deck.deck_push(node_vars_link_node4, recur_deck);
                                                                            }
                                                                            csome_list_var2 = csome_list_var2.rest();
                                                                            node_vars_link_node4 = csome_list_var2.first();
                                                                        } 
                                                                    } else {
                                                                        Errors.error($str32$_A_is_neither_SET_P_nor_LISTP_, sol2);
                                                                    }

                                                            } finally {
                                                                sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_$22, thread);
                                                            }
                                                            cdolist_list_var_$31 = cdolist_list_var_$31.rest();
                                                            generating_fn = cdolist_list_var_$31.first();
                                                        } 
                                                    }

                                            } finally {
                                                sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_$19, thread);
                                                sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_$19, thread);
                                            }
                                            cdolist_list_var = cdolist_list_var.rest();
                                            module_var = cdolist_list_var.first();
                                        } 
                                        node_var = deck.deck_pop(recur_deck);
                                    } 
                                } finally {
                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_2_$21, thread);
                                    sbhl_link_vars.$sbhl_link_direction$.rebind(_prev_bind_1_$18, thread);
                                    sbhl_search_vars.$sbhl_search_direction$.rebind(_prev_bind_0_$17, thread);
                                }
                            } else {
                                sbhl_paranoia.sbhl_warn(TWO_INTEGER, $str34$Node__a_does_not_pass_sbhl_type_t, $$NLUtteranceAttribute, sbhl_module_utilities.get_sbhl_type_test(sbhl_module_vars.get_sbhl_module(UNPROVIDED)), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            }
                        } finally {
                            sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_5, thread);
                            sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_4, thread);
                            sbhl_search_vars.$sbhl_add_node_to_result_test$.rebind(_prev_bind_3, thread);
                            sbhl_search_vars.$sbhl_search_module_type$.rebind(_prev_bind_1_$17, thread);
                            sbhl_search_vars.$sbhl_search_module$.rebind(_prev_bind_0_$16, thread);
                        }
                    } finally {
                        sbhl_search_vars.$relevant_sbhl_tv_function$.rebind(_prev_bind_1_$16, thread);
                        sbhl_search_vars.$sbhl_tv$.rebind(_prev_bind_0_$15, thread);
                    }
                } finally {
                    final SubLObject _prev_bind_0_$23 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values2 = getValuesAsVector();
                        sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_space$.getDynamicValue(thread));
                        restoreValuesFromVector(_values2);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$23, thread);
                    }
                }
            } finally {
                sbhl_marking_vars.$sbhl_space$.rebind(_prev_bind_0_$14, thread);
            }
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return $nl_tags$.getDynamicValue(thread);
    }

    public static final SubLObject initialize_nl_tag_fns_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            clear_nl_tag_fns();
            {
                SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                try {
                    mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                    mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                    {
                        SubLObject cdolist_list_var = gt_methods.gt_all_inferiors($$genlFuncs, $$NLTagFn, UNPROVIDED);
                        SubLObject spec_fn = NIL;
                        for (spec_fn = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , spec_fn = cdolist_list_var.first()) {
                            {
                                SubLObject pred_var = $$isa;
                                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(spec_fn, ONE_INTEGER, pred_var)) {
                                    {
                                        SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(spec_fn, ONE_INTEGER, pred_var);
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
                                                            final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, NIL, NIL);
                                                            {
                                                                SubLObject done_var_36 = NIL;
                                                                SubLObject token_var_37 = NIL;
                                                                while (NIL == done_var_36) {
                                                                    {
                                                                        SubLObject as = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_37);
                                                                        SubLObject valid_38 = makeBoolean(token_var_37 != as);
                                                                        if (NIL != valid_38) {
                                                                            {
                                                                                SubLObject sp = assertions_high.gaf_arg1(as);
                                                                                SubLObject mt = assertions_high.assertion_mt(as);
                                                                                dictionary_utilities.dictionary_pushnew($nl_tag_fns$.getDynamicValue(thread), sp, mt, UNPROVIDED, UNPROVIDED);
                                                                            }
                                                                        }
                                                                        done_var_36 = makeBoolean(NIL == valid_38);
                                                                    }
                                                                } 
                                                            }
                                                        } finally {
                                                            {
                                                                SubLObject _prev_bind_0_39 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                                try {
                                                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                                                    if (NIL != final_index_iterator) {
                                                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                                    }
                                                                } finally {
                                                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_39, thread);
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
                            }
                        }
                    }
                } finally {
                    mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                    mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                }
            }
            return $nl_tag_fns$.getDynamicValue(thread);
        }
    }

    public static SubLObject initialize_nl_tag_fns() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        clear_nl_tag_fns();
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
            mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
            SubLObject cdolist_list_var = gt_methods.gt_all_inferiors($$genlFuncs, $$NLTagFn, UNPROVIDED);
            SubLObject spec_fn = NIL;
            spec_fn = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject pred_var = $$isa;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(spec_fn, ONE_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(spec_fn, ONE_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, NIL, NIL);
                                SubLObject done_var_$34 = NIL;
                                final SubLObject token_var_$35 = NIL;
                                while (NIL == done_var_$34) {
                                    final SubLObject as = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$35);
                                    final SubLObject valid_$36 = makeBoolean(!token_var_$35.eql(as));
                                    if (NIL != valid_$36) {
                                        final SubLObject sp = assertions_high.gaf_arg1(as);
                                        final SubLObject mt = assertions_high.assertion_mt(as);
                                        dictionary_utilities.dictionary_pushnew($nl_tag_fns$.getDynamicValue(thread), sp, mt, UNPROVIDED, UNPROVIDED);
                                    }
                                    done_var_$34 = makeBoolean(NIL == valid_$36);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$37 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$37, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
                cdolist_list_var = cdolist_list_var.rest();
                spec_fn = cdolist_list_var.first();
            } 
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return $nl_tag_fns$.getDynamicValue(thread);
    }

    public static final SubLObject initialize_nl_tag_denoting_fns_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            clear_nl_tag_denoting_fns();
            {
                SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                try {
                    mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                    mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                    {
                        SubLObject node_var = $$NLUtteranceAttribute;
                        SubLObject deck_type = (false) ? ((SubLObject) ($QUEUE)) : $STACK;
                        SubLObject recur_deck = deck.create_deck(deck_type);
                        {
                            SubLObject _prev_bind_0_40 = sbhl_marking_vars.$sbhl_space$.currentBinding(thread);
                            try {
                                sbhl_marking_vars.$sbhl_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
                                {
                                    SubLObject tv_var = NIL;
                                    {
                                        SubLObject _prev_bind_0_41 = sbhl_search_vars.$sbhl_tv$.currentBinding(thread);
                                        SubLObject _prev_bind_1_42 = sbhl_search_vars.$relevant_sbhl_tv_function$.currentBinding(thread);
                                        try {
                                            sbhl_search_vars.$sbhl_tv$.bind(NIL != tv_var ? ((SubLObject) (tv_var)) : sbhl_search_vars.get_sbhl_true_tv(), thread);
                                            sbhl_search_vars.$relevant_sbhl_tv_function$.bind(NIL != tv_var ? ((SubLObject) (RELEVANT_SBHL_TV_IS_GENERAL_TV)) : sbhl_search_vars.$relevant_sbhl_tv_function$.getDynamicValue(thread), thread);
                                            if (NIL != tv_var) {
                                                if (NIL != sbhl_paranoia.sbhl_object_type_checking_p()) {
                                                    if (NIL == sbhl_search_vars.sbhl_true_tv_p(tv_var)) {
                                                        {
                                                            SubLObject pcase_var = sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread);
                                                            if (pcase_var.eql($ERROR)) {
                                                                sbhl_paranoia.sbhl_error(ONE_INTEGER, $str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                            } else {
                                                                if (pcase_var.eql($CERROR)) {
                                                                    sbhl_paranoia.sbhl_cerror(ONE_INTEGER, $$$continue_anyway, $str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                } else {
                                                                    if (pcase_var.eql($WARN)) {
                                                                        Errors.warn($str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                                                    } else {
                                                                        Errors.warn($str_alt30$_A_is_not_a_valid__sbhl_type_erro, sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread));
                                                                        Errors.cerror($$$continue_anyway, $str_alt25$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            {
                                                SubLObject _prev_bind_0_43 = sbhl_search_vars.$sbhl_search_module$.currentBinding(thread);
                                                SubLObject _prev_bind_1_44 = sbhl_search_vars.$sbhl_search_module_type$.currentBinding(thread);
                                                SubLObject _prev_bind_2 = sbhl_search_vars.$sbhl_add_node_to_result_test$.currentBinding(thread);
                                                SubLObject _prev_bind_3 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                SubLObject _prev_bind_4 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                try {
                                                    sbhl_search_vars.$sbhl_search_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                                                    sbhl_search_vars.$sbhl_search_module_type$.bind(sbhl_module_utilities.get_sbhl_module_type(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.bind(sbhl_module_utilities.get_sbhl_add_node_to_result_test(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                                    sbhl_module_vars.$sbhl_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                                                    if ((NIL != sbhl_paranoia.suspend_sbhl_type_checkingP()) || (NIL != sbhl_module_utilities.apply_sbhl_module_type_test($$NLUtteranceAttribute, sbhl_module_vars.get_sbhl_module(UNPROVIDED)))) {
                                                        {
                                                            SubLObject _prev_bind_0_45 = sbhl_search_vars.$sbhl_search_direction$.currentBinding(thread);
                                                            SubLObject _prev_bind_1_46 = sbhl_link_vars.$sbhl_link_direction$.currentBinding(thread);
                                                            SubLObject _prev_bind_2_47 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                            try {
                                                                sbhl_search_vars.$sbhl_search_direction$.bind(sbhl_search_vars.get_sbhl_backward_search_direction(), thread);
                                                                sbhl_link_vars.$sbhl_link_direction$.bind(sbhl_module_utilities.sbhl_search_direction_to_link_direction(sbhl_search_vars.get_sbhl_backward_search_direction(), sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                                                sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                                                sbhl_marking_utilities.sbhl_mark_node_marked(node_var, UNPROVIDED);
                                                                while (NIL != node_var) {
                                                                    {
                                                                        SubLObject spec = node_var;
                                                                        SubLObject pred_var = $$resultIsa;
                                                                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(spec, TWO_INTEGER, pred_var)) {
                                                                            {
                                                                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(spec, TWO_INTEGER, pred_var);
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
                                                                                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, NIL, NIL);
                                                                                                    {
                                                                                                        SubLObject done_var_48 = NIL;
                                                                                                        SubLObject token_var_49 = NIL;
                                                                                                        while (NIL == done_var_48) {
                                                                                                            {
                                                                                                                SubLObject as = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_49);
                                                                                                                SubLObject valid_50 = makeBoolean(token_var_49 != as);
                                                                                                                if (NIL != valid_50) {
                                                                                                                    {
                                                                                                                        SubLObject fn = assertions_high.gaf_arg1(as);
                                                                                                                        SubLObject mt = assertions_high.assertion_mt(as);
                                                                                                                        dictionary_utilities.dictionary_pushnew($nl_tag_denoting_fns$.getDynamicValue(thread), fn, mt, UNPROVIDED, UNPROVIDED);
                                                                                                                    }
                                                                                                                }
                                                                                                                done_var_48 = makeBoolean(NIL == valid_50);
                                                                                                            }
                                                                                                        } 
                                                                                                    }
                                                                                                } finally {
                                                                                                    {
                                                                                                        SubLObject _prev_bind_0_51 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                                                                        try {
                                                                                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                                                                                            if (NIL != final_index_iterator) {
                                                                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                                                                            }
                                                                                                        } finally {
                                                                                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_51, thread);
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
                                                                    }
                                                                    {
                                                                        SubLObject accessible_modules = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_vars.get_sbhl_module($$genls));
                                                                        SubLObject cdolist_list_var = accessible_modules;
                                                                        SubLObject module_var = NIL;
                                                                        for (module_var = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , module_var = cdolist_list_var.first()) {
                                                                            {
                                                                                SubLObject _prev_bind_0_52 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                                                                SubLObject _prev_bind_1_53 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                                                                try {
                                                                                    sbhl_module_vars.$sbhl_module$.bind(module_var, thread);
                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? ((SubLObject) (makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)))) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                                                    {
                                                                                        SubLObject node = function_terms.naut_to_nart(node_var);
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
                                                                                                                        SubLObject mt = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                                                                                                        SubLObject tv_links = thread.secondMultipleValue();
                                                                                                                        thread.resetMultipleValues();
                                                                                                                        if (NIL != mt_relevance_macros.relevant_mtP(mt)) {
                                                                                                                            {
                                                                                                                                SubLObject _prev_bind_0_54 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                                                                                try {
                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.bind(mt, thread);
                                                                                                                                    {
                                                                                                                                        SubLObject iteration_state_55 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links));
                                                                                                                                        while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_55)) {
                                                                                                                                            thread.resetMultipleValues();
                                                                                                                                            {
                                                                                                                                                SubLObject tv = dictionary_contents.do_dictionary_contents_key_value(iteration_state_55);
                                                                                                                                                SubLObject link_nodes = thread.secondMultipleValue();
                                                                                                                                                thread.resetMultipleValues();
                                                                                                                                                if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                                                                                    {
                                                                                                                                                        SubLObject _prev_bind_0_56 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                                                                                        try {
                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                                                                                            {
                                                                                                                                                                SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                                                                SubLObject cdolist_list_var_57 = new_list;
                                                                                                                                                                SubLObject node_vars_link_node = NIL;
                                                                                                                                                                for (node_vars_link_node = cdolist_list_var_57.first(); NIL != cdolist_list_var_57; cdolist_list_var_57 = cdolist_list_var_57.rest() , node_vars_link_node = cdolist_list_var_57.first()) {
                                                                                                                                                                    if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED)) {
                                                                                                                                                                        sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                                                                                        deck.deck_push(node_vars_link_node, recur_deck);
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        } finally {
                                                                                                                                                            sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_56, thread);
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                iteration_state_55 = dictionary_contents.do_dictionary_contents_next(iteration_state_55);
                                                                                                                                            }
                                                                                                                                        } 
                                                                                                                                        dictionary_contents.do_dictionary_contents_finalize(iteration_state_55);
                                                                                                                                    }
                                                                                                                                } finally {
                                                                                                                                    sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_54, thread);
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
                                                                                                    sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str_alt34$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (NIL != obsolete.cnat_p(node, UNPROVIDED)) {
                                                                                                {
                                                                                                    SubLObject new_list = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                                                                    SubLObject cdolist_list_var_58 = new_list;
                                                                                                    SubLObject generating_fn = NIL;
                                                                                                    for (generating_fn = cdolist_list_var_58.first(); NIL != cdolist_list_var_58; cdolist_list_var_58 = cdolist_list_var_58.rest() , generating_fn = cdolist_list_var_58.first()) {
                                                                                                        {
                                                                                                            SubLObject _prev_bind_0_59 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                                                                            try {
                                                                                                                sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn, thread);
                                                                                                                {
                                                                                                                    SubLObject link_nodes = funcall(generating_fn, node);
                                                                                                                    SubLObject new_list_60 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? ((SubLObject) (list_utilities.randomize_list(link_nodes))) : link_nodes;
                                                                                                                    SubLObject cdolist_list_var_61 = new_list_60;
                                                                                                                    SubLObject node_vars_link_node = NIL;
                                                                                                                    for (node_vars_link_node = cdolist_list_var_61.first(); NIL != cdolist_list_var_61; cdolist_list_var_61 = cdolist_list_var_61.rest() , node_vars_link_node = cdolist_list_var_61.first()) {
                                                                                                                        if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED)) {
                                                                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                                            deck.deck_push(node_vars_link_node, recur_deck);
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            } finally {
                                                                                                                sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_59, thread);
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } finally {
                                                                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_53, thread);
                                                                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_52, thread);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    node_var = deck.deck_pop(recur_deck);
                                                                } 
                                                            } finally {
                                                                sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_2_47, thread);
                                                                sbhl_link_vars.$sbhl_link_direction$.rebind(_prev_bind_1_46, thread);
                                                                sbhl_search_vars.$sbhl_search_direction$.rebind(_prev_bind_0_45, thread);
                                                            }
                                                        }
                                                    } else {
                                                        sbhl_paranoia.sbhl_warn(TWO_INTEGER, $str_alt35$Node__a_does_not_pass_sbhl_type_t, $$NLUtteranceAttribute, sbhl_module_utilities.get_sbhl_type_test(sbhl_module_vars.get_sbhl_module(UNPROVIDED)), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                    }
                                                } finally {
                                                    sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_4, thread);
                                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_3, thread);
                                                    sbhl_search_vars.$sbhl_add_node_to_result_test$.rebind(_prev_bind_2, thread);
                                                    sbhl_search_vars.$sbhl_search_module_type$.rebind(_prev_bind_1_44, thread);
                                                    sbhl_search_vars.$sbhl_search_module$.rebind(_prev_bind_0_43, thread);
                                                }
                                            }
                                        } finally {
                                            sbhl_search_vars.$relevant_sbhl_tv_function$.rebind(_prev_bind_1_42, thread);
                                            sbhl_search_vars.$sbhl_tv$.rebind(_prev_bind_0_41, thread);
                                        }
                                    }
                                    sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_space$.getDynamicValue(thread));
                                }
                            } finally {
                                sbhl_marking_vars.$sbhl_space$.rebind(_prev_bind_0_40, thread);
                            }
                        }
                    }
                } finally {
                    mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                    mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                }
            }
            return $nl_tag_denoting_fns$.getDynamicValue(thread);
        }
    }

    public static SubLObject initialize_nl_tag_denoting_fns() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        clear_nl_tag_denoting_fns();
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
            mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
            SubLObject node_var = $$NLUtteranceAttribute;
            final SubLObject deck_type = $STACK;
            final SubLObject recur_deck = deck.create_deck(deck_type);
            final SubLObject _prev_bind_0_$38 = sbhl_marking_vars.$sbhl_space$.currentBinding(thread);
            try {
                sbhl_marking_vars.$sbhl_space$.bind(sbhl_marking_vars.get_sbhl_marking_space(), thread);
                try {
                    final SubLObject tv_var = NIL;
                    final SubLObject _prev_bind_0_$39 = sbhl_search_vars.$sbhl_tv$.currentBinding(thread);
                    final SubLObject _prev_bind_1_$40 = sbhl_search_vars.$relevant_sbhl_tv_function$.currentBinding(thread);
                    try {
                        sbhl_search_vars.$sbhl_tv$.bind(NIL != tv_var ? tv_var : sbhl_search_vars.get_sbhl_true_tv(), thread);
                        sbhl_search_vars.$relevant_sbhl_tv_function$.bind(NIL != tv_var ? RELEVANT_SBHL_TV_IS_GENERAL_TV : sbhl_search_vars.$relevant_sbhl_tv_function$.getDynamicValue(thread), thread);
                        if (((NIL != tv_var) && (NIL != sbhl_paranoia.sbhl_object_type_checking_p())) && (NIL == sbhl_search_vars.sbhl_true_tv_p(tv_var))) {
                            final SubLObject pcase_var = sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread);
                            if (pcase_var.eql($ERROR)) {
                                sbhl_paranoia.sbhl_error(ONE_INTEGER, $str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            } else
                                if (pcase_var.eql($CERROR)) {
                                    sbhl_paranoia.sbhl_cerror(ONE_INTEGER, $$$continue_anyway, $str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                } else
                                    if (pcase_var.eql($WARN)) {
                                        Errors.warn($str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                    } else {
                                        Errors.warn($str28$_A_is_not_a_valid__sbhl_type_erro, sbhl_paranoia.$sbhl_type_error_action$.getDynamicValue(thread));
                                        Errors.cerror($$$continue_anyway, $str23$_A_is_not_a__A, tv_var, SBHL_TRUE_TV_P);
                                    }


                        }
                        final SubLObject _prev_bind_0_$40 = sbhl_search_vars.$sbhl_search_module$.currentBinding(thread);
                        final SubLObject _prev_bind_1_$41 = sbhl_search_vars.$sbhl_search_module_type$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = sbhl_search_vars.$sbhl_add_node_to_result_test$.currentBinding(thread);
                        final SubLObject _prev_bind_4 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                        final SubLObject _prev_bind_5 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                        try {
                            sbhl_search_vars.$sbhl_search_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                            sbhl_search_vars.$sbhl_search_module_type$.bind(sbhl_module_utilities.get_sbhl_module_type(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                            sbhl_search_vars.$sbhl_add_node_to_result_test$.bind(sbhl_module_utilities.get_sbhl_add_node_to_result_test(sbhl_module_vars.get_sbhl_module($$genls)), thread);
                            sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                            sbhl_module_vars.$sbhl_module$.bind(sbhl_module_vars.get_sbhl_module($$genls), thread);
                            if ((NIL != sbhl_paranoia.suspend_sbhl_type_checkingP()) || (NIL != sbhl_module_utilities.apply_sbhl_module_type_test($$NLUtteranceAttribute, sbhl_module_vars.get_sbhl_module(UNPROVIDED)))) {
                                final SubLObject _prev_bind_0_$41 = sbhl_search_vars.$sbhl_search_direction$.currentBinding(thread);
                                final SubLObject _prev_bind_1_$42 = sbhl_link_vars.$sbhl_link_direction$.currentBinding(thread);
                                final SubLObject _prev_bind_2_$45 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                try {
                                    sbhl_search_vars.$sbhl_search_direction$.bind(sbhl_search_vars.get_sbhl_backward_search_direction(), thread);
                                    sbhl_link_vars.$sbhl_link_direction$.bind(sbhl_module_utilities.sbhl_search_direction_to_link_direction(sbhl_search_vars.get_sbhl_backward_search_direction(), sbhl_module_vars.get_sbhl_module($$genls)), thread);
                                    sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL, thread);
                                    sbhl_marking_utilities.sbhl_mark_node_marked(node_var, UNPROVIDED);
                                    while (NIL != node_var) {
                                        final SubLObject spec = node_var;
                                        final SubLObject pred_var = $$resultIsa;
                                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(spec, TWO_INTEGER, pred_var)) {
                                            final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(spec, TWO_INTEGER, pred_var);
                                            SubLObject done_var = NIL;
                                            final SubLObject token_var = NIL;
                                            while (NIL == done_var) {
                                                final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                                                final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                                                if (NIL != valid) {
                                                    SubLObject final_index_iterator = NIL;
                                                    try {
                                                        final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, NIL, NIL);
                                                        SubLObject done_var_$46 = NIL;
                                                        final SubLObject token_var_$47 = NIL;
                                                        while (NIL == done_var_$46) {
                                                            final SubLObject as = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$47);
                                                            final SubLObject valid_$48 = makeBoolean(!token_var_$47.eql(as));
                                                            if (NIL != valid_$48) {
                                                                final SubLObject fn = assertions_high.gaf_arg1(as);
                                                                final SubLObject mt = assertions_high.assertion_mt(as);
                                                                dictionary_utilities.dictionary_pushnew($nl_tag_denoting_fns$.getDynamicValue(thread), fn, mt, UNPROVIDED, UNPROVIDED);
                                                            }
                                                            done_var_$46 = makeBoolean(NIL == valid_$48);
                                                        } 
                                                    } finally {
                                                        final SubLObject _prev_bind_0_$42 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                        try {
                                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                                            final SubLObject _values = getValuesAsVector();
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                            restoreValuesFromVector(_values);
                                                        } finally {
                                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$42, thread);
                                                        }
                                                    }
                                                }
                                                done_var = makeBoolean(NIL == valid);
                                            } 
                                        }
                                        SubLObject cdolist_list_var;
                                        final SubLObject accessible_modules = cdolist_list_var = sbhl_macros.get_sbhl_accessible_modules(sbhl_module_vars.get_sbhl_module($$genls));
                                        SubLObject module_var = NIL;
                                        module_var = cdolist_list_var.first();
                                        while (NIL != cdolist_list_var) {
                                            final SubLObject _prev_bind_0_$43 = sbhl_module_vars.$sbhl_module$.currentBinding(thread);
                                            final SubLObject _prev_bind_1_$43 = sbhl_search_vars.$genl_inverse_mode_p$.currentBinding(thread);
                                            try {
                                                sbhl_module_vars.$sbhl_module$.bind(module_var, thread);
                                                sbhl_search_vars.$genl_inverse_mode_p$.bind(NIL != sbhl_search_vars.flip_genl_inverse_modeP(UNPROVIDED, UNPROVIDED) ? makeBoolean(NIL == sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread)) : sbhl_search_vars.$genl_inverse_mode_p$.getDynamicValue(thread), thread);
                                                final SubLObject node = function_terms.naut_to_nart(node_var);
                                                if (NIL != sbhl_link_vars.sbhl_node_object_p(node)) {
                                                    final SubLObject d_link = sbhl_graphs.get_sbhl_graph_link(node, sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                    if (NIL != d_link) {
                                                        final SubLObject mt_links = sbhl_links.get_sbhl_mt_links(d_link, sbhl_link_vars.get_sbhl_link_direction(), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                        if (NIL != mt_links) {
                                                            SubLObject iteration_state;
                                                            for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(mt_links)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                                                                thread.resetMultipleValues();
                                                                final SubLObject mt2 = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                                                final SubLObject tv_links = thread.secondMultipleValue();
                                                                thread.resetMultipleValues();
                                                                if (NIL != mt_relevance_macros.relevant_mtP(mt2)) {
                                                                    final SubLObject _prev_bind_0_$44 = sbhl_link_vars.$sbhl_link_mt$.currentBinding(thread);
                                                                    try {
                                                                        sbhl_link_vars.$sbhl_link_mt$.bind(mt2, thread);
                                                                        SubLObject iteration_state_$53;
                                                                        for (iteration_state_$53 = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(tv_links)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state_$53); iteration_state_$53 = dictionary_contents.do_dictionary_contents_next(iteration_state_$53)) {
                                                                            thread.resetMultipleValues();
                                                                            final SubLObject tv = dictionary_contents.do_dictionary_contents_key_value(iteration_state_$53);
                                                                            final SubLObject link_nodes = thread.secondMultipleValue();
                                                                            thread.resetMultipleValues();
                                                                            if (NIL != sbhl_search_vars.relevant_sbhl_tvP(tv)) {
                                                                                final SubLObject _prev_bind_0_$45 = sbhl_link_vars.$sbhl_link_tv$.currentBinding(thread);
                                                                                try {
                                                                                    sbhl_link_vars.$sbhl_link_tv$.bind(tv, thread);
                                                                                    final SubLObject sol = link_nodes;
                                                                                    if (NIL != set.set_p(sol)) {
                                                                                        final SubLObject set_contents_var = set.do_set_internal(sol);
                                                                                        SubLObject basis_object;
                                                                                        SubLObject state;
                                                                                        SubLObject node_vars_link_node;
                                                                                        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                                                                            node_vars_link_node = set_contents.do_set_contents_next(basis_object, state);
                                                                                            if ((NIL != set_contents.do_set_contents_element_validP(state, node_vars_link_node)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node, UNPROVIDED))) {
                                                                                                sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node, UNPROVIDED);
                                                                                                deck.deck_push(node_vars_link_node, recur_deck);
                                                                                            }
                                                                                        }
                                                                                    } else
                                                                                        if (sol.isList()) {
                                                                                            SubLObject csome_list_var = sol;
                                                                                            SubLObject node_vars_link_node2 = NIL;
                                                                                            node_vars_link_node2 = csome_list_var.first();
                                                                                            while (NIL != csome_list_var) {
                                                                                                if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node2, UNPROVIDED)) {
                                                                                                    sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node2, UNPROVIDED);
                                                                                                    deck.deck_push(node_vars_link_node2, recur_deck);
                                                                                                }
                                                                                                csome_list_var = csome_list_var.rest();
                                                                                                node_vars_link_node2 = csome_list_var.first();
                                                                                            } 
                                                                                        } else {
                                                                                            Errors.error($str32$_A_is_neither_SET_P_nor_LISTP_, sol);
                                                                                        }

                                                                                } finally {
                                                                                    sbhl_link_vars.$sbhl_link_tv$.rebind(_prev_bind_0_$45, thread);
                                                                                }
                                                                            }
                                                                        }
                                                                        dictionary_contents.do_dictionary_contents_finalize(iteration_state_$53);
                                                                    } finally {
                                                                        sbhl_link_vars.$sbhl_link_mt$.rebind(_prev_bind_0_$44, thread);
                                                                    }
                                                                }
                                                            }
                                                            dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                                                        }
                                                    } else {
                                                        sbhl_paranoia.sbhl_error(FIVE_INTEGER, $str33$attempting_to_bind_direction_link, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                    }
                                                } else
                                                    if (NIL != obsolete.cnat_p(node, UNPROVIDED)) {
                                                        SubLObject cdolist_list_var_$55;
                                                        final SubLObject new_list = cdolist_list_var_$55 = (NIL != sbhl_link_vars.sbhl_randomize_lists_p()) ? list_utilities.randomize_list(sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED))) : sbhl_module_utilities.get_sbhl_module_relevant_naut_link_generators(sbhl_link_vars.get_sbhl_link_direction(), sbhl_search_vars.$sbhl_tv$.getDynamicValue(thread), sbhl_module_vars.get_sbhl_module(UNPROVIDED));
                                                        SubLObject generating_fn = NIL;
                                                        generating_fn = cdolist_list_var_$55.first();
                                                        while (NIL != cdolist_list_var_$55) {
                                                            final SubLObject _prev_bind_0_$46 = sbhl_link_vars.$sbhl_link_generator$.currentBinding(thread);
                                                            try {
                                                                sbhl_link_vars.$sbhl_link_generator$.bind(generating_fn, thread);
                                                                final SubLObject sol2;
                                                                final SubLObject link_nodes2 = sol2 = funcall(generating_fn, node);
                                                                if (NIL != set.set_p(sol2)) {
                                                                    final SubLObject set_contents_var2 = set.do_set_internal(sol2);
                                                                    SubLObject basis_object2;
                                                                    SubLObject state2;
                                                                    SubLObject node_vars_link_node3;
                                                                    for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2, state2); state2 = set_contents.do_set_contents_update_state(state2)) {
                                                                        node_vars_link_node3 = set_contents.do_set_contents_next(basis_object2, state2);
                                                                        if ((NIL != set_contents.do_set_contents_element_validP(state2, node_vars_link_node3)) && (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node3, UNPROVIDED))) {
                                                                            sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node3, UNPROVIDED);
                                                                            deck.deck_push(node_vars_link_node3, recur_deck);
                                                                        }
                                                                    }
                                                                } else
                                                                    if (sol2.isList()) {
                                                                        SubLObject csome_list_var2 = sol2;
                                                                        SubLObject node_vars_link_node4 = NIL;
                                                                        node_vars_link_node4 = csome_list_var2.first();
                                                                        while (NIL != csome_list_var2) {
                                                                            if (NIL == sbhl_marking_utilities.sbhl_search_path_termination_p(node_vars_link_node4, UNPROVIDED)) {
                                                                                sbhl_marking_utilities.sbhl_mark_node_marked(node_vars_link_node4, UNPROVIDED);
                                                                                deck.deck_push(node_vars_link_node4, recur_deck);
                                                                            }
                                                                            csome_list_var2 = csome_list_var2.rest();
                                                                            node_vars_link_node4 = csome_list_var2.first();
                                                                        } 
                                                                    } else {
                                                                        Errors.error($str32$_A_is_neither_SET_P_nor_LISTP_, sol2);
                                                                    }

                                                            } finally {
                                                                sbhl_link_vars.$sbhl_link_generator$.rebind(_prev_bind_0_$46, thread);
                                                            }
                                                            cdolist_list_var_$55 = cdolist_list_var_$55.rest();
                                                            generating_fn = cdolist_list_var_$55.first();
                                                        } 
                                                    }

                                            } finally {
                                                sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_1_$43, thread);
                                                sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_0_$43, thread);
                                            }
                                            cdolist_list_var = cdolist_list_var.rest();
                                            module_var = cdolist_list_var.first();
                                        } 
                                        node_var = deck.deck_pop(recur_deck);
                                    } 
                                } finally {
                                    sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_2_$45, thread);
                                    sbhl_link_vars.$sbhl_link_direction$.rebind(_prev_bind_1_$42, thread);
                                    sbhl_search_vars.$sbhl_search_direction$.rebind(_prev_bind_0_$41, thread);
                                }
                            } else {
                                sbhl_paranoia.sbhl_warn(TWO_INTEGER, $str34$Node__a_does_not_pass_sbhl_type_t, $$NLUtteranceAttribute, sbhl_module_utilities.get_sbhl_type_test(sbhl_module_vars.get_sbhl_module(UNPROVIDED)), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            }
                        } finally {
                            sbhl_module_vars.$sbhl_module$.rebind(_prev_bind_5, thread);
                            sbhl_search_vars.$genl_inverse_mode_p$.rebind(_prev_bind_4, thread);
                            sbhl_search_vars.$sbhl_add_node_to_result_test$.rebind(_prev_bind_3, thread);
                            sbhl_search_vars.$sbhl_search_module_type$.rebind(_prev_bind_1_$41, thread);
                            sbhl_search_vars.$sbhl_search_module$.rebind(_prev_bind_0_$40, thread);
                        }
                    } finally {
                        sbhl_search_vars.$relevant_sbhl_tv_function$.rebind(_prev_bind_1_$40, thread);
                        sbhl_search_vars.$sbhl_tv$.rebind(_prev_bind_0_$39, thread);
                    }
                } finally {
                    final SubLObject _prev_bind_0_$47 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values2 = getValuesAsVector();
                        sbhl_marking_vars.free_sbhl_marking_space(sbhl_marking_vars.$sbhl_space$.getDynamicValue(thread));
                        restoreValuesFromVector(_values2);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$47, thread);
                    }
                }
            } finally {
                sbhl_marking_vars.$sbhl_space$.rebind(_prev_bind_0_$38, thread);
            }
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return $nl_tag_denoting_fns$.getDynamicValue(thread);
    }

    /**
     *
     *
     * @return BOOLEAN; Is OBJ a #$NLUtteranceAttribute?
     */
    @LispMethod(comment = "@return BOOLEAN; Is OBJ a #$NLUtteranceAttribute?")
    public static final SubLObject nl_tagP_alt(SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!dictionary.dictionary_length($nl_tags$.getDynamicValue(thread)).isPositive()) {
                initialize_nl_tags();
            }
            {
                SubLObject mts = dictionary.dictionary_lookup($nl_tags$.getDynamicValue(thread), obj, UNPROVIDED);
                SubLObject mt_okP = eq(mt, $$InferencePSC);
                SubLObject nl_tagP = NIL;
                if (NIL == mt_okP) {
                    {
                        SubLObject csome_list_var = mts;
                        SubLObject ok_mt = NIL;
                        for (ok_mt = csome_list_var.first(); !((NIL != mt_okP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , ok_mt = csome_list_var.first()) {
                            mt_okP = genl_mts.genl_mtP(mt, ok_mt, UNPROVIDED, UNPROVIDED);
                        }
                    }
                }
                nl_tagP = makeBoolean((NIL != mts) && (NIL != mt_okP));
                if (!((NIL != nl_tagP) || (NIL == possibly_naut_p(obj)))) {
                    nl_tagP = nl_tag_denoting_fnP(cycl_utilities.nat_functor(obj), mt);
                }
                return nl_tagP;
            }
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Is OBJ a #$NLUtteranceAttribute?
     */
    @LispMethod(comment = "@return BOOLEAN; Is OBJ a #$NLUtteranceAttribute?")
    public static SubLObject nl_tagP(final SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (!dictionary.dictionary_length($nl_tags$.getDynamicValue(thread)).isPositive()) {
            initialize_nl_tags();
        }
        final SubLObject mts = dictionary.dictionary_lookup($nl_tags$.getDynamicValue(thread), obj, UNPROVIDED);
        SubLObject mt_okP = eql(mt, $$InferencePSC);
        SubLObject nl_tagP = NIL;
        if (NIL == mt_okP) {
            SubLObject csome_list_var;
            SubLObject ok_mt;
            for (csome_list_var = mts, ok_mt = NIL, ok_mt = csome_list_var.first(); (NIL == mt_okP) && (NIL != csome_list_var); mt_okP = genl_mts.genl_mtP(mt, ok_mt, UNPROVIDED, UNPROVIDED) , csome_list_var = csome_list_var.rest() , ok_mt = csome_list_var.first()) {
            }
        }
        nl_tagP = makeBoolean((NIL != mts) && (NIL != mt_okP));
        if ((NIL == nl_tagP) && (NIL != possibly_naut_p(obj))) {
            nl_tagP = nl_tag_denoting_fnP(cycl_utilities.nat_functor(obj), mt);
        }
        return nl_tagP;
    }

    public static final SubLObject nl_tag_denoting_fnP_alt(SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!dictionary.dictionary_length($nl_tag_denoting_fns$.getDynamicValue(thread)).isPositive()) {
                initialize_nl_tag_denoting_fns();
            }
            {
                SubLObject mts = dictionary.dictionary_lookup($nl_tag_denoting_fns$.getDynamicValue(thread), obj, UNPROVIDED);
                SubLObject mt_okP = eq(mt, $$InferencePSC);
                if (NIL == mt_okP) {
                    {
                        SubLObject csome_list_var = mts;
                        SubLObject ok_mt = NIL;
                        for (ok_mt = csome_list_var.first(); !((NIL != mt_okP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , ok_mt = csome_list_var.first()) {
                            mt_okP = genl_mts.genl_mtP(mt, ok_mt, UNPROVIDED, UNPROVIDED);
                        }
                    }
                }
                return makeBoolean((NIL != mts) && (NIL != mt_okP));
            }
        }
    }

    public static SubLObject nl_tag_denoting_fnP(final SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (!dictionary.dictionary_length($nl_tag_denoting_fns$.getDynamicValue(thread)).isPositive()) {
            initialize_nl_tag_denoting_fns();
        }
        final SubLObject mts = dictionary.dictionary_lookup($nl_tag_denoting_fns$.getDynamicValue(thread), obj, UNPROVIDED);
        SubLObject mt_okP = eql(mt, $$InferencePSC);
        if (NIL == mt_okP) {
            SubLObject csome_list_var;
            SubLObject ok_mt;
            for (csome_list_var = mts, ok_mt = NIL, ok_mt = csome_list_var.first(); (NIL == mt_okP) && (NIL != csome_list_var); mt_okP = genl_mts.genl_mtP(mt, ok_mt, UNPROVIDED, UNPROVIDED) , csome_list_var = csome_list_var.rest() , ok_mt = csome_list_var.first()) {
            }
        }
        return makeBoolean((NIL != mts) && (NIL != mt_okP));
    }

    public static final SubLObject nl_tag_fnP_alt(SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!dictionary.dictionary_length($nl_tag_fns$.getDynamicValue(thread)).isPositive()) {
                initialize_nl_tag_fns();
            }
            {
                SubLObject mts = dictionary.dictionary_lookup($nl_tag_fns$.getDynamicValue(thread), obj, UNPROVIDED);
                SubLObject mt_okP = eq(mt, $$InferencePSC);
                if (NIL == mt_okP) {
                    {
                        SubLObject csome_list_var = mts;
                        SubLObject ok_mt = NIL;
                        for (ok_mt = csome_list_var.first(); !((NIL != mt_okP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , ok_mt = csome_list_var.first()) {
                            mt_okP = genl_mts.genl_mtP(mt, ok_mt, UNPROVIDED, UNPROVIDED);
                        }
                    }
                }
                return makeBoolean((NIL != mts) && (NIL != mt_okP));
            }
        }
    }

    public static SubLObject nl_tag_fnP(final SubLObject obj, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (!dictionary.dictionary_length($nl_tag_fns$.getDynamicValue(thread)).isPositive()) {
            initialize_nl_tag_fns();
        }
        final SubLObject mts = dictionary.dictionary_lookup($nl_tag_fns$.getDynamicValue(thread), obj, UNPROVIDED);
        SubLObject mt_okP = eql(mt, $$InferencePSC);
        if (NIL == mt_okP) {
            SubLObject csome_list_var;
            SubLObject ok_mt;
            for (csome_list_var = mts, ok_mt = NIL, ok_mt = csome_list_var.first(); (NIL == mt_okP) && (NIL != csome_list_var); mt_okP = genl_mts.genl_mtP(mt, ok_mt, UNPROVIDED, UNPROVIDED) , csome_list_var = csome_list_var.rest() , ok_mt = csome_list_var.first()) {
            }
        }
        return makeBoolean((NIL != mts) && (NIL != mt_okP));
    }

    /**
     *
     *
     * @return BOOLEAN; is TERM an nl-tagged term
     */
    @LispMethod(comment = "@return BOOLEAN; is TERM an nl-tagged term")
    public static final SubLObject nl_tagged_termP_alt(SubLObject v_term) {
        if (!(v_term.isCons() && ((NIL != formula_arityE(v_term, TWO_INTEGER, UNPROVIDED)) || (NIL != formula_arityE(v_term, THREE_INTEGER, UNPROVIDED))))) {
            return NIL;
        }
        return makeBoolean((NIL != nl_tag_fnP(cycl_utilities.formula_arg0(v_term), UNPROVIDED)) && (NIL != nl_tagP(cycl_utilities.formula_arg1(v_term, UNPROVIDED), UNPROVIDED)));
    }

    /**
     *
     *
     * @return BOOLEAN; is TERM an nl-tagged term
     */
    @LispMethod(comment = "@return BOOLEAN; is TERM an nl-tagged term")
    public static SubLObject nl_tagged_termP(final SubLObject v_term) {
        if ((!v_term.isCons()) || ((NIL == formula_arityE(v_term, TWO_INTEGER, UNPROVIDED)) && (NIL == formula_arityE(v_term, THREE_INTEGER, UNPROVIDED)))) {
            return NIL;
        }
        return makeBoolean((NIL != nl_tag_fnP(cycl_utilities.formula_arg0(v_term), UNPROVIDED)) && (NIL != nl_tagP(cycl_utilities.formula_arg1(v_term, UNPROVIDED), UNPROVIDED)));
    }

    public static final SubLObject ternary_nl_tagged_termP_alt(SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!(v_term.isCons() && (NIL != formula_arityE(v_term, THREE_INTEGER, UNPROVIDED)))) {
                return NIL;
            }
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
                        result = makeBoolean((NIL != ternary_nl_tag_fnP(cycl_utilities.formula_arg0(v_term))) && (NIL != nl_tagP(cycl_utilities.formula_arg1(v_term, UNPROVIDED), UNPROVIDED)));
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

    public static SubLObject ternary_nl_tagged_termP(final SubLObject v_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$InferencePSC;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((!v_term.isCons()) || (NIL == formula_arityE(v_term, THREE_INTEGER, UNPROVIDED))) {
            return NIL;
        }
        SubLObject result = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            result = makeBoolean((NIL != ternary_nl_tag_fnP(cycl_utilities.formula_arg0(v_term))) && (NIL != nl_tagP(cycl_utilities.formula_arg1(v_term, UNPROVIDED), UNPROVIDED)));
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static final SubLObject ternary_nl_tag_fnP_alt(SubLObject v_term) {
        return makeBoolean((NIL != nl_tag_fnP(v_term, UNPROVIDED)) && arity.arity(v_term).numE(THREE_INTEGER));
    }

    public static SubLObject ternary_nl_tag_fnP(final SubLObject v_term) {
        return makeBoolean((NIL != nl_tag_fnP(v_term, UNPROVIDED)) && arity.arity(v_term).numE(THREE_INTEGER));
    }

    /**
     * Remove any NL tags from the outer layer of OBJ. Non-destructive.
     * This won't remove any tags that are inside something that is not
     * an NL-TAGGED-FUNCTION?, but it will remove all nested tags from
     * the outermost level.
     */
    @LispMethod(comment = "Remove any NL tags from the outer layer of OBJ. Non-destructive.\r\nThis won\'t remove any tags that are inside something that is not\r\nan NL-TAGGED-FUNCTION?, but it will remove all nested tags from\r\nthe outermost level.\nRemove any NL tags from the outer layer of OBJ. Non-destructive.\nThis won\'t remove any tags that are inside something that is not\nan NL-TAGGED-FUNCTION?, but it will remove all nested tags from\nthe outermost level.")
    public static final SubLObject strip_nl_tags_top_level_alt(SubLObject obj) {
        return strip_nl_tags(obj, T);
    }

    /**
     * Remove any NL tags from the outer layer of OBJ. Non-destructive.
     * This won't remove any tags that are inside something that is not
     * an NL-TAGGED-FUNCTION?, but it will remove all nested tags from
     * the outermost level.
     */
    @LispMethod(comment = "Remove any NL tags from the outer layer of OBJ. Non-destructive.\r\nThis won\'t remove any tags that are inside something that is not\r\nan NL-TAGGED-FUNCTION?, but it will remove all nested tags from\r\nthe outermost level.\nRemove any NL tags from the outer layer of OBJ. Non-destructive.\nThis won\'t remove any tags that are inside something that is not\nan NL-TAGGED-FUNCTION?, but it will remove all nested tags from\nthe outermost level.")
    public static SubLObject strip_nl_tags_top_level(final SubLObject obj) {
        return strip_nl_tags(obj, T);
    }

    public static final SubLObject possibly_strip_nl_tags_from_list_alt(SubLObject term_list, SubLObject stripP) {
        if (NIL == stripP) {
            return term_list;
        }
        {
            SubLObject stripped_terms = NIL;
            SubLObject cdolist_list_var = term_list;
            SubLObject v_term = NIL;
            for (v_term = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , v_term = cdolist_list_var.first()) {
                {
                    SubLObject tag_free = strip_nl_tags(v_term, UNPROVIDED);
                    SubLObject item_var = tag_free;
                    if (NIL == member(item_var, stripped_terms, symbol_function(EQL), symbol_function(IDENTITY))) {
                        stripped_terms = cons(item_var, stripped_terms);
                    }
                }
            }
            return stripped_terms;
        }
    }

    public static SubLObject possibly_strip_nl_tags_from_list(final SubLObject term_list, final SubLObject stripP) {
        if (NIL == stripP) {
            return term_list;
        }
        SubLObject stripped_terms = NIL;
        SubLObject cdolist_list_var = term_list;
        SubLObject v_term = NIL;
        v_term = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject item_var;
            final SubLObject tag_free = item_var = strip_nl_tags(v_term, UNPROVIDED);
            if (NIL == member(item_var, stripped_terms, symbol_function(EQL), symbol_function(IDENTITY))) {
                stripped_terms = cons(item_var, stripped_terms);
            }
            cdolist_list_var = cdolist_list_var.rest();
            v_term = cdolist_list_var.first();
        } 
        return stripped_terms;
    }

    /**
     * Remove any NL tags from OBJ. Non-destructive.
     */
    @LispMethod(comment = "Remove any NL tags from OBJ. Non-destructive.")
    public static final SubLObject strip_nl_tags_alt(SubLObject obj, SubLObject top_level_onlyP) {
        if (top_level_onlyP == UNPROVIDED) {
            top_level_onlyP = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject nl_tagged_objectP = nl_tagged_termP(obj);
                SubLObject stripped = obj;
                SubLObject placeholder = parsing_vars.nl_tag_cycl_placeholder();
                SubLObject nl_tag_templates = NIL;
                if (obj.isAtom()) {
                } else {
                    if (NIL != nl_tagged_objectP) {
                        thread.resetMultipleValues();
                        {
                            SubLObject stripped_62 = strip_nl_tags(cycl_utilities.nat_arg2(obj, UNPROVIDED), top_level_onlyP);
                            SubLObject nl_tag_templates_63 = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            stripped = stripped_62;
                            nl_tag_templates = nl_tag_templates_63;
                        }
                        nl_tag_templates = list_utilities.nadd_to_end(replace_formula_arg(TWO_INTEGER, placeholder, obj), nl_tag_templates);
                    } else {
                        if ((NIL != el_formula_p(obj)) && (NIL == top_level_onlyP)) {
                            stripped = map_formula_terms(STRIP_NL_TAGS, obj, UNPROVIDED);
                        }
                    }
                }
                return values(stripped, nl_tag_templates);
            }
        }
    }

    /**
     * Remove any NL tags from OBJ. Non-destructive.
     */
    @LispMethod(comment = "Remove any NL tags from OBJ. Non-destructive.")
    public static SubLObject strip_nl_tags(final SubLObject obj, SubLObject top_level_onlyP) {
        if (top_level_onlyP == UNPROVIDED) {
            top_level_onlyP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject nl_tagged_objectP = nl_tagged_termP(obj);
        SubLObject stripped = obj;
        final SubLObject placeholder = parsing_vars.nl_tag_cycl_placeholder();
        SubLObject nl_tag_templates = NIL;
        if (!obj.isAtom()) {
            if (NIL != nl_tagged_objectP) {
                thread.resetMultipleValues();
                final SubLObject stripped_$58 = strip_nl_tags(cycl_utilities.nat_arg2(obj, UNPROVIDED), top_level_onlyP);
                final SubLObject nl_tag_templates_$59 = thread.secondMultipleValue();
                thread.resetMultipleValues();
                stripped = stripped_$58;
                nl_tag_templates = nl_tag_templates_$59;
                nl_tag_templates = list_utilities.nadd_to_end(replace_formula_arg(TWO_INTEGER, placeholder, obj), nl_tag_templates);
            } else
                if ((NIL != el_formula_p(obj)) && (NIL == top_level_onlyP)) {
                    stripped = map_formula_terms(STRIP_NL_TAGS, obj, UNPROVIDED);
                }

        }
        return values(stripped, nl_tag_templates);
    }

    public static final SubLObject get_nl_tag_fn_alt(SubLObject type, SubLObject ternaryP) {
        if (NIL != ternaryP) {
            return get_ternary_nl_tag_fn(type);
        } else {
            return get_binary_nl_tag_fn(type);
        }
    }

    public static SubLObject get_nl_tag_fn(final SubLObject type, final SubLObject ternaryP) {
        if (NIL != ternaryP) {
            return get_ternary_nl_tag_fn(type);
        }
        return get_binary_nl_tag_fn(type);
    }

    public static final SubLObject get_ternary_nl_tag_fn_alt(SubLObject type) {
        {
            SubLObject pcase_var = type;
            if (pcase_var.eql($DEFINITENESS)) {
                return $$NLDefinitenessFn_3;
            } else {
                if (pcase_var.eql($QUANT)) {
                    return $$NLQuantFn_3;
                } else {
                    Errors.sublisp_break($str_alt45$Can_t_find_ternary_NL_tag_fn_for_, new SubLObject[]{ type });
                }
            }
        }
        return NIL;
    }

    public static SubLObject get_ternary_nl_tag_fn(final SubLObject type) {
        if (type.eql($DEFINITENESS)) {
            return $$NLDefinitenessFn_3;
        }
        if (type.eql($QUANT)) {
            return $$NLQuantFn_3;
        }
        Errors.sublisp_break($str44$Can_t_find_ternary_NL_tag_fn_for_, new SubLObject[]{ type });
        return NIL;
    }

    public static final SubLObject get_binary_nl_tag_fn_alt(SubLObject type) {
        {
            SubLObject pcase_var = type;
            if (pcase_var.eql($DEFINITENESS)) {
                return $$NLDefinitenessFn;
            } else {
                if (pcase_var.eql($QUANT)) {
                    return $$NLQuantFn;
                } else {
                    if (pcase_var.eql($NUMBER)) {
                        return $$NLNumberFn;
                    } else {
                        Errors.sublisp_break($str_alt50$Can_t_find_binary_NL_tag_fn_for__, new SubLObject[]{ type });
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject get_binary_nl_tag_fn(final SubLObject type) {
        if (type.eql($DEFINITENESS)) {
            return $$NLDefinitenessFn;
        }
        if (type.eql($QUANT)) {
            return $$NLQuantFn;
        }
        if (type.eql($NUMBER)) {
            return $$NLNumberFn;
        }
        Errors.sublisp_break($str49$Can_t_find_binary_NL_tag_fn_for__, new SubLObject[]{ type });
        return NIL;
    }

    public static final SubLObject get_nl_tag_template_alt(SubLObject type, SubLObject attribute, SubLObject start_index) {
        if (start_index == UNPROVIDED) {
            start_index = ZERO_INTEGER;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject ternaryP = makeBoolean((NIL != parsing_vars.$use_ternary_np_tagsP$.getDynamicValue(thread)) && (type != $NUMBER));
                SubLObject tag_fn = get_nl_tag_fn(type, ternaryP);
                return NIL != ternaryP ? ((SubLObject) (make_ternary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder(), start_index))) : make_binary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder());
            }
        }
    }

    public static SubLObject get_nl_tag_template(final SubLObject type, final SubLObject attribute, SubLObject start_index) {
        if (start_index == UNPROVIDED) {
            start_index = ZERO_INTEGER;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject ternaryP = makeBoolean((NIL != parsing_vars.$use_ternary_np_tagsP$.getDynamicValue(thread)) && (type != $NUMBER));
        final SubLObject tag_fn = get_nl_tag_fn(type, ternaryP);
        return NIL != ternaryP ? make_ternary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder(), start_index) : make_binary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder());
    }

    public static final SubLObject clear_make_nl_tag_template_alt() {
        {
            SubLObject cs = $make_nl_tag_template_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_make_nl_tag_template() {
        final SubLObject cs = $make_nl_tag_template_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_make_nl_tag_template_alt(SubLObject type, SubLObject attribute, SubLObject start_index) {
        return memoization_state.caching_state_remove_function_results_with_args($make_nl_tag_template_caching_state$.getGlobalValue(), list(type, attribute, start_index), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_make_nl_tag_template(final SubLObject type, final SubLObject attribute, final SubLObject start_index) {
        return memoization_state.caching_state_remove_function_results_with_args($make_nl_tag_template_caching_state$.getGlobalValue(), list(type, attribute, start_index), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject make_nl_tag_template_internal_alt(SubLObject type, SubLObject attribute, SubLObject start_index) {
        {
            SubLObject ternaryP = makeBoolean((NIL != start_index) && (type != $NUMBER));
            SubLObject tag_fn = get_nl_tag_fn(type, ternaryP);
            return NIL != ternaryP ? ((SubLObject) (make_ternary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder(), start_index))) : make_binary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder());
        }
    }

    public static SubLObject make_nl_tag_template_internal(final SubLObject type, final SubLObject attribute, final SubLObject start_index) {
        final SubLObject ternaryP = makeBoolean((NIL != start_index) && (type != $NUMBER));
        final SubLObject tag_fn = get_nl_tag_fn(type, ternaryP);
        return NIL != ternaryP ? make_ternary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder(), start_index) : make_binary_formula(tag_fn, attribute, parsing_vars.nl_tag_cycl_placeholder());
    }

    public static final SubLObject make_nl_tag_template_alt(SubLObject type, SubLObject attribute, SubLObject start_index) {
        {
            SubLObject caching_state = $make_nl_tag_template_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(MAKE_NL_TAG_TEMPLATE, $make_nl_tag_template_caching_state$, NIL, EQL, THREE_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_3(type, attribute, start_index);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw13$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (type.eql(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (attribute.eql(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && start_index.eql(cached_args.first())) {
                                            return memoization_state.caching_results(results2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(make_nl_tag_template_internal(type, attribute, start_index)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(type, attribute, start_index));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject make_nl_tag_template(final SubLObject type, final SubLObject attribute, final SubLObject start_index) {
        SubLObject caching_state = $make_nl_tag_template_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(MAKE_NL_TAG_TEMPLATE, $make_nl_tag_template_caching_state$, NIL, EQL, THREE_INTEGER, ZERO_INTEGER);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_3(type, attribute, start_index);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (type.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (attribute.eql(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && start_index.eql(cached_args.first())) {
                            return memoization_state.caching_results(results2);
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(make_nl_tag_template_internal(type, attribute, start_index)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(type, attribute, start_index));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject add_nl_tag_template_alt(SubLObject cycl, SubLObject tag_template) {
        return replace_formula_arg(TWO_INTEGER, cycl, tag_template);
    }

    public static SubLObject add_nl_tag_template(final SubLObject cycl, final SubLObject tag_template) {
        return replace_formula_arg(TWO_INTEGER, cycl, tag_template);
    }

    /**
     *
     *
     * @return BOOLEAN; Does FORMULA contain an NL tag?
     */
    @LispMethod(comment = "@return BOOLEAN; Does FORMULA contain an NL tag?")
    public static final SubLObject contains_nl_tagP_alt(SubLObject formula) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find_if($sym53$NL_TAG_, formula, NIL, UNPROVIDED));
    }

    /**
     *
     *
     * @return BOOLEAN; Does FORMULA contain an NL tag?
     */
    @LispMethod(comment = "@return BOOLEAN; Does FORMULA contain an NL tag?")
    public static SubLObject contains_nl_tagP(final SubLObject formula) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find_if($sym52$NL_TAG_, formula, NIL, UNPROVIDED));
    }

    public static final SubLObject add_nl_number_wrapper_alt(SubLObject cycl, SubLObject pred) {
        {
            SubLObject number = spp_to_nl_number(pred);
            SubLObject ans = cycl;
            if (NIL == number) {
                Errors.warn($str_alt54$Couldn_t_determine_NL_number_for_, pred);
            } else {
                if (NIL != formula_pattern_match.formula_matches_pattern(cycl, listS($$NLNumberFn, number, $list_alt55))) {
                } else {
                    {
                        SubLObject nl_tag_template = get_nl_tag_template($NUMBER, number, UNPROVIDED);
                        ans = add_nl_tag_template(cycl, nl_tag_template);
                    }
                }
            }
            return ans;
        }
    }

    public static SubLObject add_nl_number_wrapper(final SubLObject cycl, final SubLObject pred) {
        final SubLObject number = spp_to_nl_number(pred);
        SubLObject ans = cycl;
        if (NIL == number) {
            Errors.warn($str53$Couldn_t_determine_NL_number_for_, pred);
        } else
            if (NIL == formula_pattern_match.formula_matches_pattern(cycl, listS($$NLNumberFn, number, $list54))) {
                final SubLObject nl_tag_template = get_nl_tag_template($NUMBER, number, UNPROVIDED);
                ans = add_nl_tag_template(cycl, nl_tag_template);
            }

        return ans;
    }

    /**
     *
     *
     * @return BOOLEAN; Is OBJ a number, for purposes of determiner agreement?
     */
    @LispMethod(comment = "@return BOOLEAN; Is OBJ a number, for purposes of determiner agreement?")
    public static final SubLObject determiner_number_denotP_internal_alt(SubLObject obj) {
        return at_defns.quick_quiet_has_typeP(obj, $$ScalarInterval, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN; Is OBJ a number, for purposes of determiner agreement?
     */
    @LispMethod(comment = "@return BOOLEAN; Is OBJ a number, for purposes of determiner agreement?")
    public static SubLObject determiner_number_denotP_internal(final SubLObject obj) {
        return at_defns.quick_quiet_has_typeP(obj, $$ScalarInterval, UNPROVIDED);
    }

    public static final SubLObject determiner_number_denotP_alt(SubLObject obj) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return determiner_number_denotP_internal(obj);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, $sym56$DETERMINER_NUMBER_DENOT_, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), $sym56$DETERMINER_NUMBER_DENOT_, ONE_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, $sym56$DETERMINER_NUMBER_DENOT_, caching_state);
                }
                {
                    SubLObject results = memoization_state.caching_state_lookup(caching_state, obj, $kw13$_MEMOIZED_ITEM_NOT_FOUND_);
                    if (results == $kw13$_MEMOIZED_ITEM_NOT_FOUND_) {
                        results = arg2(thread.resetMultipleValues(), multiple_value_list(determiner_number_denotP_internal(obj)));
                        memoization_state.caching_state_put(caching_state, obj, results, UNPROVIDED);
                    }
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject determiner_number_denotP(final SubLObject obj) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return determiner_number_denotP_internal(obj);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, $sym55$DETERMINER_NUMBER_DENOT_, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), $sym55$DETERMINER_NUMBER_DENOT_, ONE_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, $sym55$DETERMINER_NUMBER_DENOT_, caching_state);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, obj, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(thread.resetMultipleValues(), multiple_value_list(determiner_number_denotP_internal(obj)));
            memoization_state.caching_state_put(caching_state, obj, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    /**
     *
     *
     * @param NUMBER
     * 		
     * @return LISTP of speech-part preds
     */
    @LispMethod(comment = "@param NUMBER\r\n\t\t\r\n@return LISTP of speech-part preds")
    public static final SubLObject agr_preds_for_number_alt(SubLObject number) {
        if (number.isDouble()) {
            return number.numE(ONE_INTEGER) ? ((SubLObject) ($list_alt58)) : $list_alt59;
        } else {
            if (number.eql(ONE_INTEGER)) {
                return $list_alt60;
            } else {
                return $list_alt59;
            }
        }
    }

    /**
     *
     *
     * @param NUMBER
     * 		
     * @return LISTP of speech-part preds
     */
    @LispMethod(comment = "@param NUMBER\r\n\t\t\r\n@return LISTP of speech-part preds")
    public static SubLObject agr_preds_for_number(final SubLObject number) {
        if (number.isDouble()) {
            return number.numE(ONE_INTEGER) ? $list57 : $list58;
        }
        if (number.eql(ONE_INTEGER)) {
            return $list59;
        }
        return $list58;
    }

    /**
     * Add a wrapper function and tag to CYCL, based on DET-STRING and/or DET-CYCLS.
     *
     * @param DET-CYCLS
     * 		listp of CycL expressions, the denotata of the determiner.
     * @return LISTP of CycL expressions.
     */
    @LispMethod(comment = "Add a wrapper function and tag to CYCL, based on DET-STRING and/or DET-CYCLS.\r\n\r\n@param DET-CYCLS\r\n\t\tlistp of CycL expressions, the denotata of the determiner.\r\n@return LISTP of CycL expressions.")
    public static final SubLObject add_nl_quant_wrapper_alt(SubLObject cycl, SubLObject det_string, SubLObject det_cycls, SubLObject start_index) {
        if (det_cycls == UNPROVIDED) {
            det_cycls = NIL;
        }
        if (start_index == UNPROVIDED) {
            start_index = ZERO_INTEGER;
        }
        {
            SubLObject nlqas = lexicon_utilities.nl_quant_attributes_for_string(det_string, det_cycls);
            SubLObject ans = NIL;
            if (NIL == nlqas) {
                ans = cons(cycl, ans);
            } else {
                if ((NIL != el_formula_p(cycl)) && (NIL != contains_nl_tagP(cycl))) {
                    {
                        SubLObject agr_preds = lexicon_accessors.agr_of_det_string(det_string);
                        SubLObject cycl_num_attr = nl_number_attr_for_cycl(cycl);
                        SubLObject cycl_spp = nl_number_to_spp(cycl_num_attr);
                        SubLObject all_preds = cons(cycl_spp, agr_preds);
                        SubLObject floor_preds = nl_max_floor_preds(all_preds);
                        SubLObject new_cycl = NIL;
                        SubLObject pcase_var = length(floor_preds);
                        if (pcase_var.eql(ZERO_INTEGER)) {
                            Errors.warn($str_alt61$No_common_specialization_for__S__, all_preds);
                        } else {
                            if (pcase_var.eql(ONE_INTEGER)) {
                                {
                                    SubLObject floor_pred = floor_preds.first();
                                    SubLObject new_num_attr = spp_to_nl_number(floor_pred);
                                    if (NIL == new_num_attr) {
                                        Errors.warn($str_alt62$No_NL_Number_attribute_for__S___, floor_pred);
                                    } else {
                                        new_cycl = cycl_utilities.expression_subst(new_num_attr, cycl_num_attr, cycl, UNPROVIDED, UNPROVIDED);
                                    }
                                }
                            } else {
                                Errors.warn($str_alt63$Multiple_specializations_for__S__, all_preds);
                            }
                        }
                        if (NIL != new_cycl) {
                            {
                                SubLObject cdolist_list_var = nlqas;
                                SubLObject nlqa = NIL;
                                for (nlqa = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , nlqa = cdolist_list_var.first()) {
                                    {
                                        SubLObject nl_tag_template = get_nl_tag_template($QUANT, nlqa, start_index);
                                        SubLObject tagged_new_cycl = add_nl_tag_template(new_cycl, nl_tag_template);
                                        ans = cons(tagged_new_cycl, ans);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return ans;
        }
    }

    /**
     * Add a wrapper function and tag to CYCL, based on DET-STRING and/or DET-CYCLS.
     *
     * @param DET-CYCLS
     * 		listp of CycL expressions, the denotata of the determiner.
     * @return LISTP of CycL expressions.
     */
    @LispMethod(comment = "Add a wrapper function and tag to CYCL, based on DET-STRING and/or DET-CYCLS.\r\n\r\n@param DET-CYCLS\r\n\t\tlistp of CycL expressions, the denotata of the determiner.\r\n@return LISTP of CycL expressions.")
    public static SubLObject add_nl_quant_wrapper(final SubLObject cycl, final SubLObject det_string, SubLObject det_cycls, SubLObject start_index) {
        if (det_cycls == UNPROVIDED) {
            det_cycls = NIL;
        }
        if (start_index == UNPROVIDED) {
            start_index = ZERO_INTEGER;
        }
        final SubLObject nlqas = lexicon_utilities.nl_quant_attributes_for_string(det_string, det_cycls);
        SubLObject ans = NIL;
        if (NIL == nlqas) {
            ans = cons(cycl, ans);
        } else
            if ((NIL != el_formula_p(cycl)) && (NIL != contains_nl_tagP(cycl))) {
                final SubLObject agr_preds = lexicon_accessors.agr_of_det_string(det_string);
                final SubLObject cycl_num_attr = nl_number_attr_for_cycl(cycl);
                final SubLObject cycl_spp = nl_number_to_spp(cycl_num_attr);
                final SubLObject all_preds = cons(cycl_spp, agr_preds);
                final SubLObject floor_preds = nl_max_floor_preds(all_preds);
                SubLObject new_cycl = NIL;
                final SubLObject pcase_var = length(floor_preds);
                if (!pcase_var.eql(ZERO_INTEGER)) {
                    if (pcase_var.eql(ONE_INTEGER)) {
                        final SubLObject floor_pred = floor_preds.first();
                        final SubLObject new_num_attr = spp_to_nl_number(floor_pred);
                        if (NIL != new_num_attr) {
                            new_cycl = cycl_utilities.expression_subst(new_num_attr, cycl_num_attr, cycl, UNPROVIDED, UNPROVIDED);
                        }
                    }
                }
                if (NIL != new_cycl) {
                    SubLObject cdolist_list_var = nlqas;
                    SubLObject nlqa = NIL;
                    nlqa = cdolist_list_var.first();
                    while (NIL != cdolist_list_var) {
                        final SubLObject nl_tag_template = get_nl_tag_template($QUANT, nlqa, start_index);
                        final SubLObject tagged_new_cycl = add_nl_tag_template(new_cycl, nl_tag_template);
                        ans = cons(tagged_new_cycl, ans);
                        cdolist_list_var = cdolist_list_var.rest();
                        nlqa = cdolist_list_var.first();
                    } 
                }
            }

        return ans;
    }

    public static final SubLObject clear_nl_max_floor_preds_alt() {
        {
            SubLObject cs = $nl_max_floor_preds_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_nl_max_floor_preds() {
        final SubLObject cs = $nl_max_floor_preds_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_nl_max_floor_preds_alt(SubLObject preds) {
        return memoization_state.caching_state_remove_function_results_with_args($nl_max_floor_preds_caching_state$.getGlobalValue(), list(preds), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_nl_max_floor_preds(final SubLObject preds) {
        return memoization_state.caching_state_remove_function_results_with_args($nl_max_floor_preds_caching_state$.getGlobalValue(), list(preds), UNPROVIDED, UNPROVIDED);
    }

    /**
     * Cached version for use by parsers.
     *
     * @unknown Not currently cleared as an after-adding, so it is the responsibility
    of individual parsers to clear as appropriate.
     */
    @LispMethod(comment = "Cached version for use by parsers.\r\n\r\n@unknown Not currently cleared as an after-adding, so it is the responsibility\r\nof individual parsers to clear as appropriate.")
    public static final SubLObject nl_max_floor_preds_internal_alt(SubLObject preds) {
        return genl_predicates.max_floor_predicates(preds, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    /**
     * Cached version for use by parsers.
     *
     * @unknown Not currently cleared as an after-adding, so it is the responsibility
    of individual parsers to clear as appropriate.
     */
    @LispMethod(comment = "Cached version for use by parsers.\r\n\r\n@unknown Not currently cleared as an after-adding, so it is the responsibility\r\nof individual parsers to clear as appropriate.")
    public static SubLObject nl_max_floor_preds_internal(final SubLObject preds) {
        return genl_predicates.max_floor_predicates(preds, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject nl_max_floor_preds_alt(SubLObject preds) {
        {
            SubLObject caching_state = $nl_max_floor_preds_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(NL_MAX_FLOOR_PREDS, $nl_max_floor_preds_caching_state$, NIL, EQUAL, ONE_INTEGER, $int$256);
            }
            {
                SubLObject results = memoization_state.caching_state_lookup(caching_state, preds, $kw13$_MEMOIZED_ITEM_NOT_FOUND_);
                if (results == $kw13$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(nl_max_floor_preds_internal(preds)));
                    memoization_state.caching_state_put(caching_state, preds, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject nl_max_floor_preds(final SubLObject preds) {
        SubLObject caching_state = $nl_max_floor_preds_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(NL_MAX_FLOOR_PREDS, $nl_max_floor_preds_caching_state$, NIL, EQUAL, ONE_INTEGER, $int$256);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, preds, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(nl_max_floor_preds_internal(preds)));
            memoization_state.caching_state_put(caching_state, preds, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    public static final SubLObject cycl_has_definiteness_tagP_alt(SubLObject cycl) {
        return makeBoolean((NIL != cycl_utilities.expression_find($$NLDefinitenessFn, cycl, UNPROVIDED, UNPROVIDED, UNPROVIDED)) || (NIL != cycl_utilities.expression_find($$NLDefinitenessFn_3, cycl, UNPROVIDED, UNPROVIDED, UNPROVIDED)));
    }

    public static SubLObject cycl_has_definiteness_tagP(final SubLObject cycl) {
        return makeBoolean((NIL != cycl_utilities.expression_find($$NLDefinitenessFn, cycl, UNPROVIDED, UNPROVIDED, UNPROVIDED)) || (NIL != cycl_utilities.expression_find($$NLDefinitenessFn_3, cycl, UNPROVIDED, UNPROVIDED, UNPROVIDED)));
    }

    public static final SubLObject cycl_pronoun_p_alt(SubLObject v_object) {
        return narts_high.nart_with_functor_p($$PronounFn, v_object);
    }

    public static SubLObject cycl_pronoun_p(final SubLObject v_object) {
        return narts_high.nart_with_functor_p($$PronounFn, v_object);
    }

    public static final SubLObject nl_person_attr_for_cycl_pronoun_alt(SubLObject pronoun) {
        SubLTrampolineFile.checkType(pronoun, CYCL_PRONOUN_P);
        return cycl_utilities.nat_arg1(pronoun, UNPROVIDED);
    }

    public static SubLObject nl_person_attr_for_cycl_pronoun(final SubLObject pronoun) {
        assert NIL != cycl_pronoun_p(pronoun) : "! parsing_utilities.cycl_pronoun_p(pronoun) " + ("parsing_utilities.cycl_pronoun_p(pronoun) " + "CommonSymbols.NIL != parsing_utilities.cycl_pronoun_p(pronoun) ") + pronoun;
        return cycl_utilities.nat_arg1(pronoun, UNPROVIDED);
    }

    public static final SubLObject nl_number_attr_for_cycl_alt(SubLObject cycl) {
        {
            SubLObject attr = $$UnmarkedNumber_NLAttr;
            if (NIL != cycl_grammar.cycl_nat_p(cycl)) {
                {
                    SubLObject function = cycl_utilities.nat_function(cycl);
                    if (function == $$NLNumberFn) {
                        attr = cycl_utilities.nat_arg1(cycl, UNPROVIDED);
                    } else {
                        if (function == $$NLDefinitenessFn) {
                            attr = nl_number_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                        } else {
                            if (function == $$NLQuantFn) {
                                attr = nl_number_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                            } else {
                                if (function == $$PronounFn) {
                                    attr = cycl_utilities.nat_arg2(cycl, UNPROVIDED);
                                } else {
                                    if (NIL != isa.isaP(function, $$SubcollectionFunction, UNPROVIDED, UNPROVIDED)) {
                                        attr = nl_number_attr_for_cycl(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return attr;
        }
    }

    public static SubLObject nl_number_attr_for_cycl(final SubLObject cycl) {
        SubLObject attr = $$UnmarkedNumber_NLAttr;
        if (NIL != cycl_grammar.cycl_nat_p(cycl)) {
            final SubLObject function = cycl_utilities.nat_function(cycl);
            if (function.eql($$NLNumberFn)) {
                attr = cycl_utilities.nat_arg1(cycl, UNPROVIDED);
            } else
                if (function.eql($$NLDefinitenessFn)) {
                    attr = nl_number_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                } else
                    if (function.eql($$NLQuantFn)) {
                        attr = nl_number_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                    } else
                        if (function.eql($$PronounFn)) {
                            attr = cycl_utilities.nat_arg2(cycl, UNPROVIDED);
                        } else
                            if (NIL != isa.isaP(function, $$SubcollectionFunction, UNPROVIDED, UNPROVIDED)) {
                                attr = nl_number_attr_for_cycl(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                            }




        }
        return attr;
    }

    public static final SubLObject nl_definiteness_attr_for_cycl_alt(SubLObject cycl) {
        {
            SubLObject attr = NIL;
            if (NIL != cycl_grammar.cycl_nat_p(cycl)) {
                {
                    SubLObject function = cycl_utilities.nat_function(cycl);
                    if (function == $$NLDefinitenessFn) {
                        attr = cycl_utilities.nat_arg1(cycl, UNPROVIDED);
                    } else {
                        if (function == $$NLNumberFn) {
                            attr = nl_definiteness_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                        } else {
                            if (function == $$NLQuantFn) {
                                attr = nl_definiteness_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                            } else {
                                if (function == $$PronounFn) {
                                    {
                                        SubLObject arg4 = cycl_utilities.nat_arg4(cycl, UNPROVIDED);
                                        if (NIL != fort_types_interface.nl_definiteness_attributeP(arg4)) {
                                            attr = arg4;
                                        }
                                    }
                                } else {
                                    if (NIL != isa.isaP(function, $$SubcollectionFunction, UNPROVIDED, UNPROVIDED)) {
                                        attr = nl_definiteness_attr_for_cycl(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return attr;
        }
    }

    public static SubLObject nl_definiteness_attr_for_cycl(final SubLObject cycl) {
        SubLObject attr = NIL;
        if (NIL != cycl_grammar.cycl_nat_p(cycl)) {
            final SubLObject function = cycl_utilities.nat_function(cycl);
            if (function.eql($$NLDefinitenessFn)) {
                attr = cycl_utilities.nat_arg1(cycl, UNPROVIDED);
            } else
                if (function.eql($$NLNumberFn)) {
                    attr = nl_definiteness_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                } else
                    if (function.eql($$NLQuantFn)) {
                        attr = nl_definiteness_attr_for_cycl(cycl_utilities.nat_arg2(cycl, UNPROVIDED));
                    } else
                        if (function.eql($$PronounFn)) {
                            final SubLObject arg4 = cycl_utilities.nat_arg4(cycl, UNPROVIDED);
                            if (NIL != fort_types_interface.nl_definiteness_attributeP(arg4)) {
                                attr = arg4;
                            }
                        } else
                            if (NIL != isa.isaP(function, $$SubcollectionFunction, UNPROVIDED, UNPROVIDED)) {
                                attr = nl_definiteness_attr_for_cycl(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                            }




        }
        return attr;
    }

    public static final SubLObject nl_gender_attr_for_cycl_alt(SubLObject cycl) {
        {
            SubLObject attr = $$Ungendered_NLAttr;
            if (NIL != cycl_grammar.cycl_nat_p(cycl)) {
                {
                    SubLObject function = cycl_utilities.nat_function(cycl);
                    if (function == $$NLDefinitenessFn) {
                    } else {
                        if (function == $$NLNumberFn) {
                        } else {
                            if (function == $$NLQuantFn) {
                            } else {
                                if (function == $$PronounFn) {
                                    attr = cycl_utilities.nat_arg3(cycl, UNPROVIDED);
                                } else {
                                    if (NIL != isa.isaP(function, $$SubcollectionFunction, UNPROVIDED, UNPROVIDED)) {
                                        attr = nl_gender_attr_for_cycl(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return attr;
        }
    }

    public static SubLObject nl_gender_attr_for_cycl(final SubLObject cycl) {
        SubLObject attr = $$Ungendered_NLAttr;
        if (NIL != cycl_grammar.cycl_nat_p(cycl)) {
            final SubLObject function = cycl_utilities.nat_function(cycl);
            if (!function.eql($$NLDefinitenessFn)) {
                if (!function.eql($$NLNumberFn)) {
                    if (!function.eql($$NLQuantFn)) {
                        if (function.eql($$PronounFn)) {
                            attr = cycl_utilities.nat_arg3(cycl, UNPROVIDED);
                        } else
                            if (NIL != isa.isaP(function, $$SubcollectionFunction, UNPROVIDED, UNPROVIDED)) {
                                attr = nl_gender_attr_for_cycl(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                            }

                    }
                }
            }
        }
        return attr;
    }

    public static final SubLObject kb_nl_number_attribute_p_alt(SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$NLNumberAttribute);
    }

    public static SubLObject kb_nl_number_attribute_p(final SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$NLNumberAttribute);
    }

    public static final SubLObject kb_nl_definiteness_attribute_p_alt(SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$NLDefinitenessAttribute);
    }

    public static SubLObject kb_nl_definiteness_attribute_p(final SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$NLDefinitenessAttribute);
    }

    public static final SubLObject kb_nl_gender_attribute_p_alt(SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$NLGenderAttribute);
    }

    public static SubLObject kb_nl_gender_attribute_p(final SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$NLGenderAttribute);
    }

    public static final SubLObject nl_number_attributes_disagreeP_alt(SubLObject number1, SubLObject number2) {
        SubLTrampolineFile.checkType(number1, KB_NL_NUMBER_ATTRIBUTE_P);
        SubLTrampolineFile.checkType(number2, KB_NL_NUMBER_ATTRIBUTE_P);
        return makeBoolean(!(((((number1 == number2) || (number1 == $$UnmarkedNumber_NLAttr)) || (number2 == $$UnmarkedNumber_NLAttr)) || ((number1 == $$Generic_NLAttr) && ((number2 == $$Mass_NLAttr) || (number2 == $$Plural_NLAttr)))) || ((number2 == $$Generic_NLAttr) && ((number1 == $$Mass_NLAttr) || (number1 == $$Plural_NLAttr)))));
    }

    public static SubLObject nl_number_attributes_disagreeP(final SubLObject number1, final SubLObject number2) {
        assert NIL != kb_nl_number_attribute_p(number1) : "! parsing_utilities.kb_nl_number_attribute_p(number1) " + ("parsing_utilities.kb_nl_number_attribute_p(number1) " + "CommonSymbols.NIL != parsing_utilities.kb_nl_number_attribute_p(number1) ") + number1;
        assert NIL != kb_nl_number_attribute_p(number2) : "! parsing_utilities.kb_nl_number_attribute_p(number2) " + ("parsing_utilities.kb_nl_number_attribute_p(number2) " + "CommonSymbols.NIL != parsing_utilities.kb_nl_number_attribute_p(number2) ") + number2;
        return makeBoolean(((((!number1.eql(number2)) && (!number1.eql($$UnmarkedNumber_NLAttr))) && (!number2.eql($$UnmarkedNumber_NLAttr))) && ((!number1.eql($$Generic_NLAttr)) || ((!number2.eql($$Mass_NLAttr)) && (!number2.eql($$Plural_NLAttr))))) && ((!number2.eql($$Generic_NLAttr)) || ((!number1.eql($$Mass_NLAttr)) && (!number1.eql($$Plural_NLAttr)))));
    }

    public static final SubLObject nl_gender_attributes_disagreeP_alt(SubLObject gender1, SubLObject gender2) {
        SubLTrampolineFile.checkType(gender1, KB_NL_GENDER_ATTRIBUTE_P);
        SubLTrampolineFile.checkType(gender2, KB_NL_GENDER_ATTRIBUTE_P);
        return makeBoolean(!(((gender1 == gender2) || (gender1 == $$Ungendered_NLAttr)) || (gender2 == $$Ungendered_NLAttr)));
    }

    public static SubLObject nl_gender_attributes_disagreeP(final SubLObject gender1, final SubLObject gender2) {
        assert NIL != kb_nl_gender_attribute_p(gender1) : "! parsing_utilities.kb_nl_gender_attribute_p(gender1) " + ("parsing_utilities.kb_nl_gender_attribute_p(gender1) " + "CommonSymbols.NIL != parsing_utilities.kb_nl_gender_attribute_p(gender1) ") + gender1;
        assert NIL != kb_nl_gender_attribute_p(gender2) : "! parsing_utilities.kb_nl_gender_attribute_p(gender2) " + ("parsing_utilities.kb_nl_gender_attribute_p(gender2) " + "CommonSymbols.NIL != parsing_utilities.kb_nl_gender_attribute_p(gender2) ") + gender2;
        return makeBoolean(((!gender1.eql(gender2)) && (!gender1.eql($$Ungendered_NLAttr))) && (!gender2.eql($$Ungendered_NLAttr)));
    }

    public static final SubLObject cycls_disagree_in_nl_number_attributeP_alt(SubLObject cycl1, SubLObject cycl2) {
        {
            SubLObject number1 = nl_number_attr_for_cycl(cycl1);
            SubLObject number2 = nl_number_attr_for_cycl(cycl2);
            return nl_number_attributes_disagreeP(number1, number2);
        }
    }

    public static SubLObject cycls_disagree_in_nl_number_attributeP(final SubLObject cycl1, final SubLObject cycl2) {
        final SubLObject number1 = nl_number_attr_for_cycl(cycl1);
        final SubLObject number2 = nl_number_attr_for_cycl(cycl2);
        return nl_number_attributes_disagreeP(number1, number2);
    }

    public static final SubLObject cycls_disagree_in_nl_gender_attributeP_alt(SubLObject cycl1, SubLObject cycl2) {
        {
            SubLObject gender1 = nl_gender_attr_for_cycl(cycl1);
            SubLObject gender2 = nl_gender_attr_for_cycl(cycl2);
            return nl_gender_attributes_disagreeP(gender1, gender2);
        }
    }

    public static SubLObject cycls_disagree_in_nl_gender_attributeP(final SubLObject cycl1, final SubLObject cycl2) {
        final SubLObject gender1 = nl_gender_attr_for_cycl(cycl1);
        final SubLObject gender2 = nl_gender_attr_for_cycl(cycl2);
        return nl_gender_attributes_disagreeP(gender1, gender2);
    }

    /**
     *
     *
     * @return FORT-P or NIL; the #$SpeechPartPredicate for #$NLNumberAttribute ATTR,
    if one is known.
     */
    @LispMethod(comment = "@return FORT-P or NIL; the #$SpeechPartPredicate for #$NLNumberAttribute ATTR,\r\nif one is known.")
    public static final SubLObject nl_number_to_spp_alt(SubLObject attr) {
        {
            SubLObject pcase_var = attr;
            if (pcase_var.eql($$UnmarkedNumber_NLAttr)) {
                return $$nonPlural_Generic;
            } else {
                if (pcase_var.eql($$Singular_NLAttr)) {
                    return $$singular_Generic;
                } else {
                    if (pcase_var.eql($$Plural_NLAttr)) {
                        return $$plural_Generic;
                    } else {
                        if (pcase_var.eql($$Mass_NLAttr)) {
                            return $$massNumber_Generic;
                        } else {
                            return NIL;
                        }
                    }
                }
            }
        }
    }

    /**
     *
     *
     * @return FORT-P or NIL; the #$SpeechPartPredicate for #$NLNumberAttribute ATTR,
    if one is known.
     */
    @LispMethod(comment = "@return FORT-P or NIL; the #$SpeechPartPredicate for #$NLNumberAttribute ATTR,\r\nif one is known.")
    public static SubLObject nl_number_to_spp(final SubLObject attr) {
        if (attr.eql($$UnmarkedNumber_NLAttr)) {
            return $$nonPlural_Generic;
        }
        if (attr.eql($$Singular_NLAttr)) {
            return $$singular_Generic;
        }
        if (attr.eql($$Plural_NLAttr)) {
            return $$plural_Generic;
        }
        if (attr.eql($$Mass_NLAttr)) {
            return $$massNumber_Generic;
        }
        return NIL;
    }

    /**
     *
     *
     * @return FORT-P or NIL; the #$NLNumberAttribute for #$SpeechPartPredicate PRED,
    if one is known.
     */
    @LispMethod(comment = "@return FORT-P or NIL; the #$NLNumberAttribute for #$SpeechPartPredicate PRED,\r\nif one is known.")
    public static final SubLObject spp_to_nl_number_alt(SubLObject pred) {
        if (hash_table_count($spps_to_nl_numbers$.getGlobalValue()).isZero()) {
            init_spps_to_nl_numbers();
        }
        return gethash(pred, $spps_to_nl_numbers$.getGlobalValue(), UNPROVIDED);
    }

    /**
     *
     *
     * @return FORT-P or NIL; the #$NLNumberAttribute for #$SpeechPartPredicate PRED,
    if one is known.
     */
    @LispMethod(comment = "@return FORT-P or NIL; the #$NLNumberAttribute for #$SpeechPartPredicate PRED,\r\nif one is known.")
    public static SubLObject spp_to_nl_number(final SubLObject pred) {
        if (hash_table_count($spps_to_nl_numbers$.getGlobalValue()).isZero()) {
            init_spps_to_nl_numbers();
        }
        return gethash(pred, $spps_to_nl_numbers$.getGlobalValue(), UNPROVIDED);
    }

    public static final SubLObject init_spps_to_nl_numbers_alt() {
        clrhash($spps_to_nl_numbers$.getGlobalValue());
        {
            SubLObject cdolist_list_var = lexicon_accessors.all_speech_part_preds(UNPROVIDED);
            SubLObject pred = NIL;
            for (pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pred = cdolist_list_var.first()) {
                if ((NIL != lexicon_accessors.genl_pos_predP(pred, $$singular_Generic, UNPROVIDED)) && (NIL != lexicon_accessors.genl_pos_predP(pred, $$massNumber_Generic, UNPROVIDED))) {
                    sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$UnmarkedNumber_NLAttr);
                } else {
                    if (NIL != lexicon_accessors.genl_pos_predP(pred, $$plural_Generic, UNPROVIDED)) {
                        sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$Plural_NLAttr);
                    } else {
                        if (NIL != lexicon_accessors.genl_pos_predP(pred, $$singular_Generic, UNPROVIDED)) {
                            sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$Singular_NLAttr);
                        } else {
                            if (NIL != lexicon_accessors.genl_pos_predP(pred, $$massNumber_Generic, UNPROVIDED)) {
                                sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$Mass_NLAttr);
                            } else {
                                sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$UnmarkedNumber_NLAttr);
                            }
                        }
                    }
                }
            }
        }
        sethash($$partOfSpeech, $spps_to_nl_numbers$.getGlobalValue(), $$Singular_NLAttr);
        return $INITIALIZED;
    }

    public static SubLObject init_spps_to_nl_numbers() {
        clrhash($spps_to_nl_numbers$.getGlobalValue());
        SubLObject cdolist_list_var = lexicon_accessors.all_speech_part_preds(UNPROVIDED);
        SubLObject pred = NIL;
        pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if ((NIL != lexicon_accessors.genl_pos_predP(pred, $$singular_Generic, UNPROVIDED)) && (NIL != lexicon_accessors.genl_pos_predP(pred, $$massNumber_Generic, UNPROVIDED))) {
                sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$UnmarkedNumber_NLAttr);
            } else
                if (NIL != lexicon_accessors.genl_pos_predP(pred, $$plural_Generic, UNPROVIDED)) {
                    sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$Plural_NLAttr);
                } else
                    if (NIL != lexicon_accessors.genl_pos_predP(pred, $$singular_Generic, UNPROVIDED)) {
                        sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$Singular_NLAttr);
                    } else
                        if (NIL != lexicon_accessors.genl_pos_predP(pred, $$massNumber_Generic, UNPROVIDED)) {
                            sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$Mass_NLAttr);
                        } else {
                            sethash(pred, $spps_to_nl_numbers$.getGlobalValue(), $$UnmarkedNumber_NLAttr);
                        }



            cdolist_list_var = cdolist_list_var.rest();
            pred = cdolist_list_var.first();
        } 
        sethash($$partOfSpeech, $spps_to_nl_numbers$.getGlobalValue(), $$Singular_NLAttr);
        return $INITIALIZED;
    }

    /**
     * Add a wrapper function and tag to CYCL, based on DET-STRING and/or POS.
     *
     * @return LISTP of wrapped versions of CYCL.
     */
    @LispMethod(comment = "Add a wrapper function and tag to CYCL, based on DET-STRING and/or POS.\r\n\r\n@return LISTP of wrapped versions of CYCL.")
    public static final SubLObject add_nl_def_wrapper_alt(SubLObject cycl, SubLObject det_string, SubLObject pos, SubLObject denots, SubLObject start_index) {
        if (pos == UNPROVIDED) {
            pos = NIL;
        }
        if (denots == UNPROVIDED) {
            denots = NIL;
        }
        if (start_index == UNPROVIDED) {
            start_index = ZERO_INTEGER;
        }
        {
            SubLObject ans = NIL;
            SubLObject cdolist_list_var = (NIL != pos) ? ((SubLObject) (lexicon_utilities.nl_def_attributes_for_pos(pos, denots))) : lexicon_utilities.nl_def_attributes_for_string(det_string, denots);
            SubLObject nlda = NIL;
            for (nlda = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , nlda = cdolist_list_var.first()) {
                {
                    SubLObject nl_tag_template = get_nl_tag_template($DEFINITENESS, nlda, start_index);
                    SubLObject tagged_cycl = add_nl_tag_template(cycl, nl_tag_template);
                    ans = cons(tagged_cycl, ans);
                }
            }
            return NIL != ans ? ((SubLObject) (ans)) : list(cycl);
        }
    }

    /**
     * Add a wrapper function and tag to CYCL, based on DET-STRING and/or POS.
     *
     * @return LISTP of wrapped versions of CYCL.
     */
    @LispMethod(comment = "Add a wrapper function and tag to CYCL, based on DET-STRING and/or POS.\r\n\r\n@return LISTP of wrapped versions of CYCL.")
    public static SubLObject add_nl_def_wrapper(final SubLObject cycl, final SubLObject det_string, SubLObject pos, SubLObject denots, SubLObject start_index) {
        if (pos == UNPROVIDED) {
            pos = NIL;
        }
        if (denots == UNPROVIDED) {
            denots = NIL;
        }
        if (start_index == UNPROVIDED) {
            start_index = ZERO_INTEGER;
        }
        SubLObject ans = NIL;
        SubLObject cdolist_list_var = (NIL != pos) ? lexicon_utilities.nl_def_attributes_for_pos(pos, denots) : lexicon_utilities.nl_def_attributes_for_string(det_string, denots);
        SubLObject nlda = NIL;
        nlda = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject nl_tag_template = get_nl_tag_template($DEFINITENESS, nlda, start_index);
            final SubLObject tagged_cycl = add_nl_tag_template(cycl, nl_tag_template);
            ans = cons(tagged_cycl, ans);
            cdolist_list_var = cdolist_list_var.rest();
            nlda = cdolist_list_var.first();
        } 
        return NIL != ans ? ans : list(cycl);
    }

    /**
     * Parse a collection-denoting CycL expression.
     *
     * @return 0; CycL collection-denoting expression.
     * @return 1; EL variable representing an arbitrary instance of CYCL.
     * @return 2; LISTP of CycL clauses representing further restrictions on VAR.
     * @unknown baxter
     */
    @LispMethod(comment = "Parse a collection-denoting CycL expression.\r\n\r\n@return 0; CycL collection-denoting expression.\r\n@return 1; EL variable representing an arbitrary instance of CYCL.\r\n@return 2; LISTP of CycL clauses representing further restrictions on VAR.\r\n@unknown baxter")
    public static final SubLObject parse_collection_expression_alt(SubLObject cycl) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject coll = cycl;
                SubLObject var = NIL;
                SubLObject restrictions = NIL;
                if (NIL != term.nautP(cycl, UNPROVIDED)) {
                    if (cycl_utilities.nat_function(cycl) == $$CollectionSubsetFn) {
                        coll = cycl_utilities.nat_arg1(cycl, UNPROVIDED);
                        {
                            SubLObject set_expr = cycl_utilities.nat_arg2(cycl, UNPROVIDED);
                            if (cycl_utilities.nat_function(set_expr) == $$TheSetOf) {
                                var = cycl_utilities.nat_arg1(set_expr, UNPROVIDED);
                                {
                                    SubLObject restr = cycl_utilities.nat_arg2(set_expr, UNPROVIDED);
                                    SubLObject new_restrictions = (NIL != el_conjunction_p(restr)) ? ((SubLObject) (cycl_utilities.formula_args(restr, UNPROVIDED))) : list(restr);
                                    restrictions = new_restrictions;
                                }
                            }
                        }
                    } else {
                        if (NIL != isa.isa_in_any_mtP(cycl_utilities.nat_function(cycl), $$SubcollectionRelationFunction)) {
                            thread.resetMultipleValues();
                            {
                                SubLObject subcoll = parse_collection_expression(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                                SubLObject subvar = thread.secondMultipleValue();
                                SubLObject subrestrictions = thread.thirdMultipleValue();
                                thread.resetMultipleValues();
                                coll = make_formula(cycl_utilities.nat_function(cycl), cons(subcoll, cycl_utilities.nat_args(cycl, UNPROVIDED).rest()), UNPROVIDED);
                                var = subvar;
                                restrictions = subrestrictions;
                            }
                        }
                    }
                }
                return values(coll, var, restrictions);
            }
        }
    }

    /**
     * Parse a collection-denoting CycL expression.
     *
     * @return 0; CycL collection-denoting expression.
     * @return 1; EL variable representing an arbitrary instance of CYCL.
     * @return 2; LISTP of CycL clauses representing further restrictions on VAR.
     * @unknown baxter
     */
    @LispMethod(comment = "Parse a collection-denoting CycL expression.\r\n\r\n@return 0; CycL collection-denoting expression.\r\n@return 1; EL variable representing an arbitrary instance of CYCL.\r\n@return 2; LISTP of CycL clauses representing further restrictions on VAR.\r\n@unknown baxter")
    public static SubLObject parse_collection_expression(final SubLObject cycl) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject coll = cycl;
        SubLObject var = NIL;
        SubLObject restrictions = NIL;
        if (NIL != term.nautP(cycl, UNPROVIDED)) {
            if (cycl_utilities.nat_function(cycl).eql($$CollectionSubsetFn)) {
                coll = cycl_utilities.nat_arg1(cycl, UNPROVIDED);
                final SubLObject set_expr = cycl_utilities.nat_arg2(cycl, UNPROVIDED);
                if (cycl_utilities.nat_function(set_expr).eql($$TheSetOf)) {
                    var = cycl_utilities.nat_arg1(set_expr, UNPROVIDED);
                    final SubLObject restr = cycl_utilities.nat_arg2(set_expr, UNPROVIDED);
                    final SubLObject new_restrictions = restrictions = (NIL != el_conjunction_p(restr)) ? cycl_utilities.formula_args(restr, UNPROVIDED) : list(restr);
                }
            } else
                if (NIL != isa.isa_in_any_mtP(cycl_utilities.nat_function(cycl), $$SubcollectionRelationFunction)) {
                    thread.resetMultipleValues();
                    final SubLObject subcoll = parse_collection_expression(cycl_utilities.nat_arg1(cycl, UNPROVIDED));
                    final SubLObject subvar = thread.secondMultipleValue();
                    final SubLObject subrestrictions = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    coll = make_formula(cycl_utilities.nat_function(cycl), cons(subcoll, cycl_utilities.nat_args(cycl, UNPROVIDED).rest()), UNPROVIDED);
                    var = subvar;
                    restrictions = subrestrictions;
                }

        }
        return values(coll, var, restrictions);
    }

    /**
     * Parse a collection-denoting CycL expression.
     *
     * @param COLL;
     * 		CycL collection-denoting expression.
     * @param VAR;
     * 		EL variable representing an arbitrary instance of CYCL.
     * @param RESTRICTIONS;
     * 		LISTP of CycL clauses representing further restrictions on VAR.
     * @return a CycL expression incorporating COLL and RESTRICTIONS.
     * @unknown baxter
     */
    @LispMethod(comment = "Parse a collection-denoting CycL expression.\r\n\r\n@param COLL;\r\n\t\tCycL collection-denoting expression.\r\n@param VAR;\r\n\t\tEL variable representing an arbitrary instance of CYCL.\r\n@param RESTRICTIONS;\r\n\t\tLISTP of CycL clauses representing further restrictions on VAR.\r\n@return a CycL expression incorporating COLL and RESTRICTIONS.\r\n@unknown baxter")
    public static final SubLObject make_collection_expression_alt(SubLObject coll, SubLObject var, SubLObject restrictions) {
        if (NIL == restrictions) {
            return coll;
        } else {
            if (NIL != list_utilities.singletonP(restrictions)) {
                return list($$CollectionSubsetFn, coll, list($$TheSetOf, var, restrictions.first()));
            } else {
                return list($$CollectionSubsetFn, coll, list($$TheSetOf, var, make_conjunction(restrictions)));
            }
        }
    }

    /**
     * Parse a collection-denoting CycL expression.
     *
     * @param COLL;
     * 		CycL collection-denoting expression.
     * @param VAR;
     * 		EL variable representing an arbitrary instance of CYCL.
     * @param RESTRICTIONS;
     * 		LISTP of CycL clauses representing further restrictions on VAR.
     * @return a CycL expression incorporating COLL and RESTRICTIONS.
     * @unknown baxter
     */
    @LispMethod(comment = "Parse a collection-denoting CycL expression.\r\n\r\n@param COLL;\r\n\t\tCycL collection-denoting expression.\r\n@param VAR;\r\n\t\tEL variable representing an arbitrary instance of CYCL.\r\n@param RESTRICTIONS;\r\n\t\tLISTP of CycL clauses representing further restrictions on VAR.\r\n@return a CycL expression incorporating COLL and RESTRICTIONS.\r\n@unknown baxter")
    public static SubLObject make_collection_expression(final SubLObject coll, final SubLObject var, final SubLObject restrictions) {
        if (NIL == restrictions) {
            return coll;
        }
        if (NIL != list_utilities.singletonP(restrictions)) {
            return list($$CollectionSubsetFn, coll, list($$TheSetOf, var, restrictions.first()));
        }
        return list($$CollectionSubsetFn, coll, list($$TheSetOf, var, make_conjunction(restrictions)));
    }

    public static final SubLObject npp_init_excluded_name_preds_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                try {
                    mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                    mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                    {
                        SubLObject preds = isa.all_fort_instances($const92$ProperNamePredicate_ExcludedFromN, UNPROVIDED, UNPROVIDED);
                        $npp_name_preds_to_exclude$.setDynamicValue(NIL != preds ? ((SubLObject) (preds)) : $NONE, thread);
                    }
                } finally {
                    mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                    mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                }
            }
            return $npp_name_preds_to_exclude$.getDynamicValue(thread);
        }
    }

    public static SubLObject npp_init_excluded_name_preds() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
            mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
            final SubLObject preds = isa.all_fort_instances($const88$ProperNamePredicate_ExcludedFromN, UNPROVIDED, UNPROVIDED);
            $npp_name_preds_to_exclude$.setDynamicValue(NIL != preds ? preds : $NONE, thread);
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return $npp_name_preds_to_exclude$.getDynamicValue(thread);
    }

    public static final SubLObject npp_name_preds_to_exclude_iterator_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == $npp_name_preds_to_exclude$.getDynamicValue(thread)) {
                npp_init_excluded_name_preds();
            }
            return iteration.new_list_iterator($npp_name_preds_to_exclude$.getDynamicValue(thread));
        }
    }

    public static SubLObject npp_name_preds_to_exclude_iterator() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == $npp_name_preds_to_exclude$.getDynamicValue(thread)) {
            npp_init_excluded_name_preds();
        }
        return iteration.new_list_iterator($npp_name_preds_to_exclude$.getDynamicValue(thread));
    }

    public static final SubLObject npp_excluded_name_predP_alt(SubLObject v_object) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == $npp_name_preds_to_exclude$.getDynamicValue(thread)) {
                npp_init_excluded_name_preds();
            }
            return makeBoolean(($npp_name_preds_to_exclude$.getDynamicValue(thread) != $NONE) && (NIL != find(v_object, $npp_name_preds_to_exclude$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)));
        }
    }

    public static SubLObject npp_excluded_name_predP(final SubLObject v_object) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == $npp_name_preds_to_exclude$.getDynamicValue(thread)) {
            npp_init_excluded_name_preds();
        }
        return makeBoolean((!$npp_name_preds_to_exclude$.getDynamicValue(thread).eql($NONE)) && (NIL != find(v_object, $npp_name_preds_to_exclude$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)));
    }

    public static final SubLObject blacklisted_cycl_for_parsersP_alt(SubLObject cycl) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return subl_promotions.memberP(cycl, parsing_vars.$parser_cycl_blacklist$.getDynamicValue(thread), symbol_function(EQUAL), UNPROVIDED);
        }
    }

    public static SubLObject blacklisted_cycl_for_parsersP(final SubLObject cycl) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return subl_promotions.memberP(cycl, parsing_vars.$parser_cycl_blacklist$.getDynamicValue(thread), symbol_function(EQUAL), UNPROVIDED);
    }

    public static final SubLObject psp_lexicon_root_mt_alt(SubLObject v_psp_lexicon) {
        if (v_psp_lexicon == UNPROVIDED) {
            v_psp_lexicon = parsing_vars.$psp_lexicon$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject root_mt = $UNINITIALIZED;
                if (NIL != misc_utilities.initialized_p(v_psp_lexicon)) {
                    root_mt = methods.funcall_instance_method_with_0_args(v_psp_lexicon, ROOT_MT);
                }
                if (NIL == misc_utilities.initialized_p(root_mt)) {
                    root_mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread);
                }
                return root_mt;
            }
        }
    }

    public static SubLObject psp_lexicon_root_mt(SubLObject v_psp_lexicon) {
        if (v_psp_lexicon == UNPROVIDED) {
            v_psp_lexicon = parsing_vars.$psp_lexicon$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject root_mt = $UNINITIALIZED;
        if (NIL != misc_utilities.initialized_p(v_psp_lexicon)) {
            root_mt = methods.funcall_instance_method_with_0_args(v_psp_lexicon, ROOT_MT);
        }
        if (NIL == misc_utilities.initialized_p(root_mt)) {
            root_mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread);
        }
        return root_mt;
    }

    /**
     *
     *
     * @return FLOATP; representation of PSP-WEIGHT as a floating-point value
    between 0 and 1.
     */
    @LispMethod(comment = "@return FLOATP; representation of PSP-WEIGHT as a floating-point value\r\nbetween 0 and 1.")
    public static final SubLObject psp_weight_to_float_alt(SubLObject psp_weight) {
        SubLTrampolineFile.checkType(psp_weight, PSP_WEIGHT_P);
        return divide(psp_weight, parsing_vars.$psp_max_weight$.getGlobalValue());
    }

    /**
     *
     *
     * @return FLOATP; representation of PSP-WEIGHT as a floating-point value
    between 0 and 1.
     */
    @LispMethod(comment = "@return FLOATP; representation of PSP-WEIGHT as a floating-point value\r\nbetween 0 and 1.")
    public static SubLObject psp_weight_to_float(final SubLObject psp_weight) {
        assert NIL != psp_chart.psp_weight_p(psp_weight) : "! psp_chart.psp_weight_p(psp_weight) " + ("psp_chart.psp_weight_p(psp_weight) " + "CommonSymbols.NIL != psp_chart.psp_weight_p(psp_weight) ") + psp_weight;
        return divide(psp_weight, parsing_vars.$psp_max_weight$.getGlobalValue());
    }

    /**
     *
     *
     * @param FLOAT
     * 		floatp; a floating-point value between 0 and 1.
     * @return PSP-WEIGHT-P; corresponding to FLOAT.
     */
    @LispMethod(comment = "@param FLOAT\r\n\t\tfloatp; a floating-point value between 0 and 1.\r\n@return PSP-WEIGHT-P; corresponding to FLOAT.")
    public static final SubLObject psp_weight_from_float_alt(SubLObject v_float) {
        SubLTrampolineFile.checkType(v_float, FLOATP);
        {
            SubLObject raw_float_weight = multiply(v_float, parsing_vars.$psp_max_weight$.getGlobalValue());
            SubLObject pcase_var = parsing_vars.$psp_weight_type$.getGlobalValue();
            if (pcase_var.eql($INTEGER)) {
                return nth_value_step_2(nth_value_step_1(ZERO_INTEGER), floor(raw_float_weight, UNPROVIDED));
            } else {
                if (pcase_var.eql($FLOAT)) {
                    return raw_float_weight;
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @param FLOAT
     * 		floatp; a floating-point value between 0 and 1.
     * @return PSP-WEIGHT-P; corresponding to FLOAT.
     */
    @LispMethod(comment = "@param FLOAT\r\n\t\tfloatp; a floating-point value between 0 and 1.\r\n@return PSP-WEIGHT-P; corresponding to FLOAT.")
    public static SubLObject psp_weight_from_float(final SubLObject v_float) {
        assert NIL != floatp(v_float) : "! floatp(v_float) " + ("Types.floatp(v_float) " + "CommonSymbols.NIL != Types.floatp(v_float) ") + v_float;
        final SubLObject raw_float_weight = multiply(v_float, parsing_vars.$psp_max_weight$.getGlobalValue());
        final SubLObject pcase_var = parsing_vars.$psp_weight_type$.getGlobalValue();
        if (pcase_var.eql($INTEGER)) {
            return nth_value_step_2(nth_value_step_1(ZERO_INTEGER), floor(raw_float_weight, UNPROVIDED));
        }
        if (pcase_var.eql($FLOAT)) {
            return raw_float_weight;
        }
        return NIL;
    }

    public static final SubLObject remove_keyword_literals_alt(SubLObject cycl) {
        {
            SubLObject copy = copy_expression(cycl);
            while ((NIL != el_formula_p(copy)) && (NIL != cycl_utilities.expression_contains_keywordp(copy, UNPROVIDED))) {
                {
                    SubLObject arg_pos = cycl_utilities.arg_positions_if_bfs(KEYWORDP, copy, UNPROVIDED).first();
                    if (NIL != list_utilities.singletonP(arg_pos)) {
                        copy = NIL;
                    } else {
                        if (NIL != el_junction_p(list_utilities.get_nested_nth(copy, butlast(arg_pos, UNPROVIDED), UNPROVIDED))) {
                            list_utilities.delete_nested_nth(copy, arg_pos);
                        } else {
                            list_utilities.delete_nested_nth(copy, butlast(arg_pos, UNPROVIDED));
                        }
                    }
                }
            } 
            return copy;
        }
    }

    public static SubLObject remove_keyword_literals(final SubLObject cycl) {
        SubLObject copy = copy_expression(cycl);
        while ((NIL != el_formula_p(copy)) && (NIL != cycl_utilities.expression_contains_keywordp(copy, UNPROVIDED))) {
            final SubLObject arg_pos = cycl_utilities.arg_positions_if_bfs(KEYWORDP, copy, UNPROVIDED).first();
            if (NIL != list_utilities.singletonP(arg_pos)) {
                copy = NIL;
            } else
                if (NIL != el_junction_p(list_utilities.get_nested_nth(copy, butlast(arg_pos, UNPROVIDED), UNPROVIDED))) {
                    list_utilities.delete_nested_nth(copy, arg_pos);
                } else {
                    list_utilities.delete_nested_nth(copy, butlast(arg_pos, UNPROVIDED));
                }

        } 
        return copy;
    }

    public static final SubLObject vp_parse_from_pspP_alt(SubLObject formula) {
        return makeBoolean((NIL == formula_with_sequence_varP(formula)) && (((NIL != el_binary_formula_p(formula)) && (NIL != genls.genl_in_any_mtP(cycl_utilities.formula_arg0(formula), $$TheVPParse))) || ((NIL != el_unary_formula_p(formula)) && (NIL != genls.genl_in_any_mtP(cycl_utilities.formula_arg0(formula), $$SomeFn)))));
    }

    public static SubLObject vp_parse_from_pspP(final SubLObject formula) {
        return makeBoolean((NIL == formula_with_sequence_varP(formula)) && (((NIL != el_binary_formula_p(formula)) && (NIL != genls.genl_in_any_mtP(cycl_utilities.formula_arg0(formula), $$TheVPParse))) || ((NIL != el_unary_formula_p(formula)) && (NIL != genls.genl_in_any_mtP(cycl_utilities.formula_arg0(formula), $$SomeFn)))));
    }

    public static final SubLObject result_of_parsing_words_alt(SubLObject formula) {
        return second(formula);
    }

    public static SubLObject result_of_parsing_words(final SubLObject formula) {
        return second(formula);
    }

    public static final SubLObject result_of_parsing_span_alt(SubLObject formula) {
        return third(formula);
    }

    public static SubLObject result_of_parsing_span(final SubLObject formula) {
        return third(formula);
    }

    public static final SubLObject result_of_parsing_span_wXo_thelist_alt(SubLObject formula) {
        return third(formula).rest();
    }

    public static SubLObject result_of_parsing_span_wXo_thelist(final SubLObject formula) {
        return third(formula).rest();
    }

    public static final SubLObject result_of_parsing_span_length_alt(SubLObject cycl) {
        return length(result_of_parsing_span(cycl));
    }

    public static SubLObject result_of_parsing_span_length(final SubLObject cycl) {
        return length(result_of_parsing_span(cycl));
    }

    public static final SubLObject result_of_parsing_category_alt(SubLObject formula) {
        return fourth(formula);
    }

    public static SubLObject result_of_parsing_category(final SubLObject formula) {
        return fourth(formula);
    }

    /**
     * Returns the starting index of ROP
     */
    @LispMethod(comment = "Returns the starting index of ROP")
    public static final SubLObject result_of_parsing_start_alt(SubLObject rop) {
        return result_of_parsing_span_wXo_thelist(rop).first();
    }

    /**
     * Returns the starting index of ROP
     */
    @LispMethod(comment = "Returns the starting index of ROP")
    public static SubLObject result_of_parsing_start(final SubLObject rop) {
        return result_of_parsing_span_wXo_thelist(rop).first();
    }/**
     * Returns the starting index of ROP
     */


    /**
     * Returns the ending index of ROP
     */
    @LispMethod(comment = "Returns the ending index of ROP")
    public static final SubLObject result_of_parsing_end_alt(SubLObject rop) {
        return last(result_of_parsing_span(rop), UNPROVIDED).first();
    }

    /**
     * Returns the ending index of ROP
     */
    @LispMethod(comment = "Returns the ending index of ROP")
    public static SubLObject result_of_parsing_end(final SubLObject rop) {
        return last(result_of_parsing_span(rop), UNPROVIDED).first();
    }/**
     * Returns the ending index of ROP
     */


    public static final SubLObject result_of_parsing_formulaP_alt(SubLObject formula) {
        return makeBoolean(formula.isList() && ($$TheResultOfParsing == formula.first()));
    }

    public static SubLObject result_of_parsing_formulaP(final SubLObject formula) {
        return makeBoolean(formula.isList() && $$TheResultOfParsing.eql(formula.first()));
    }

    public static final SubLObject formula_contains_result_of_parsingP_alt(SubLObject formula) {
        return makeBoolean((NIL != list_utilities.tree_find($$TheResultOfParsing, formula, UNPROVIDED, UNPROVIDED)) && (NIL != cycl_utilities.expression_find_if($sym103$RESULT_OF_PARSING_FORMULA_, formula, UNPROVIDED, UNPROVIDED)));
    }

    public static SubLObject formula_contains_result_of_parsingP(final SubLObject formula) {
        return makeBoolean((NIL != list_utilities.tree_find($$TheResultOfParsing, formula, UNPROVIDED, UNPROVIDED)) && (NIL != cycl_utilities.expression_find_if($sym99$RESULT_OF_PARSING_FORMULA_, formula, UNPROVIDED, UNPROVIDED)));
    }

    public static final SubLObject formulas_containing_results_of_parsing_alt(SubLObject formula) {
        return list_utilities.tree_find_all_if($sym103$RESULT_OF_PARSING_FORMULA_, formula, UNPROVIDED);
    }

    public static SubLObject formulas_containing_results_of_parsing(final SubLObject formula) {
        return list_utilities.tree_find_all_if($sym99$RESULT_OF_PARSING_FORMULA_, formula, UNPROVIDED);
    }

    /**
     * Return a token-list of integers from START-INDEX inclusive to END-INDEX exclusive.
     */
    @LispMethod(comment = "Return a token-list of integers from START-INDEX inclusive to END-INDEX exclusive.")
    public static final SubLObject token_list_from_span_alt(SubLObject start_index, SubLObject end_index) {
        return list_utilities.new_num_list(subtract(end_index, start_index), start_index);
    }

    /**
     * Return a token-list of integers from START-INDEX inclusive to END-INDEX exclusive.
     */
    @LispMethod(comment = "Return a token-list of integers from START-INDEX inclusive to END-INDEX exclusive.")
    public static SubLObject token_list_from_span(final SubLObject start_index, final SubLObject end_index) {
        return list_utilities.new_num_list(subtract(end_index, start_index), start_index);
    }/**
     * Return a token-list of integers from START-INDEX inclusive to END-INDEX exclusive.
     */


    public static final SubLObject result_of_parsing_highest_category_alt(SubLObject formula, SubLObject cat) {
        if (cat == UNPROVIDED) {
            cat = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != cat) {
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == genls.genl_in_any_mtP(cat, $$ParsingTemplateCategory)) {
                        Errors.error($str_alt105$_A_doesn_t_genl_to___ParsingTempl, cat);
                    }
                }
            }
            {
                SubLObject v_answer = NIL;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                        mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                        v_answer = genls.max_cols(delete($$ParsingTemplateCategory, genls.all_genls_wrt(result_of_parsing_category(formula), NIL != cat ? ((SubLObject) (cat)) : $$ParsingTemplateCategory, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED).first();
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return v_answer;
            }
        }
    }

    public static SubLObject result_of_parsing_highest_category(final SubLObject formula, SubLObject cat) {
        if (cat == UNPROVIDED) {
            cat = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (((NIL != cat) && (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread))) && (NIL == genls.genl_in_any_mtP(cat, $$ParsingTemplateCategory))) {
            Errors.error($str101$_A_doesn_t_genl_to___ParsingTempl, cat);
        }
        SubLObject v_answer = NIL;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
            mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
            v_answer = genls.max_cols(delete($$ParsingTemplateCategory, genls.all_genls_wrt(result_of_parsing_category(formula), NIL != cat ? cat : $$ParsingTemplateCategory, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED).first();
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return v_answer;
    }

    /**
     *
     *
     * @return BOOLEAN; Is FRAME one that we can devise a semTrans template
    for given a denot?
     */
    @LispMethod(comment = "@return BOOLEAN; Is FRAME one that we can devise a semTrans template\r\nfor given a denot?")
    public static final SubLObject devisable_verb_frameP_alt(SubLObject frame) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return makeBoolean((NIL != psp_chart.intransitive_frameP(frame, lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread))) || (NIL != psp_chart.transitive_np_comp_frameP(frame, lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread))));
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Is FRAME one that we can devise a semTrans template
    for given a denot?
     */
    @LispMethod(comment = "@return BOOLEAN; Is FRAME one that we can devise a semTrans template\r\nfor given a denot?")
    public static SubLObject devisable_verb_frameP(final SubLObject frame) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return makeBoolean((NIL != psp_chart.intransitive_frameP(frame, lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread))) || (NIL != psp_chart.transitive_np_comp_frameP(frame, lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread))));
    }

    /**
     *
     *
     * @param DENOT;
     * 		A CycL expression denoted by some verb.
     * @param FRAME;
     * 		A subcategorization frame for the verb.
     * @return LISTP; of semtrans templates
     * @unknown baxter
     */
    @LispMethod(comment = "@param DENOT;\r\n\t\tA CycL expression denoted by some verb.\r\n@param FRAME;\r\n\t\tA subcategorization frame for the verb.\r\n@return LISTP; of semtrans templates\r\n@unknown baxter")
    public static final SubLObject verb_semtrans_from_denot_rolesXframe_alt(SubLObject denot, SubLObject roles, SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        {
            SubLObject templates = NIL;
            if (NIL != roles) {
                {
                    SubLObject datum = roles;
                    SubLObject current = datum;
                    SubLObject subj_roles = NIL;
                    SubLObject dir_obj_roles = NIL;
                    SubLObject indir_obj_roles = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt106);
                    subj_roles = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt106);
                    dir_obj_roles = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt106);
                    indir_obj_roles = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        if (frame == $$MiddleVoiceFrame) {
                            {
                                SubLObject cdolist_list_var = dir_obj_roles;
                                SubLObject subj_role = NIL;
                                for (subj_role = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , subj_role = cdolist_list_var.first()) {
                                    templates = cons(list($$and, list($$isa, $ACTION, denot), bq_cons(subj_role, $list_alt110)), templates);
                                }
                            }
                        } else {
                            if (NIL != psp_chart.intransitive_frameP(frame, mt)) {
                                {
                                    SubLObject cdolist_list_var = subj_roles;
                                    SubLObject subj_role = NIL;
                                    for (subj_role = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , subj_role = cdolist_list_var.first()) {
                                        templates = cons(list($$and, list($$isa, $ACTION, denot), bq_cons(subj_role, $list_alt110)), templates);
                                    }
                                }
                            } else {
                                if (NIL != psp_chart.transitive_np_comp_frameP(frame, mt)) {
                                    {
                                        SubLObject cdolist_list_var = subj_roles;
                                        SubLObject subj_role = NIL;
                                        for (subj_role = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , subj_role = cdolist_list_var.first()) {
                                            {
                                                SubLObject cdolist_list_var_64 = dir_obj_roles;
                                                SubLObject dir_obj_role = NIL;
                                                for (dir_obj_role = cdolist_list_var_64.first(); NIL != cdolist_list_var_64; cdolist_list_var_64 = cdolist_list_var_64.rest() , dir_obj_role = cdolist_list_var_64.first()) {
                                                    templates = cons(list($$and, list($$isa, $ACTION, denot), bq_cons(subj_role, $list_alt110), bq_cons(dir_obj_role, $list_alt111)), templates);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt106);
                    }
                }
            }
            return templates;
        }
    }

    /**
     *
     *
     * @param DENOT;
     * 		A CycL expression denoted by some verb.
     * @param FRAME;
     * 		A subcategorization frame for the verb.
     * @return LISTP; of semtrans templates
     * @unknown baxter
     */
    @LispMethod(comment = "@param DENOT;\r\n\t\tA CycL expression denoted by some verb.\r\n@param FRAME;\r\n\t\tA subcategorization frame for the verb.\r\n@return LISTP; of semtrans templates\r\n@unknown baxter")
    public static SubLObject verb_semtrans_from_denot_rolesXframe(final SubLObject denot, final SubLObject roles, final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        SubLObject templates = NIL;
        if (NIL != roles) {
            SubLObject subj_roles = NIL;
            SubLObject dir_obj_roles = NIL;
            SubLObject indir_obj_roles = NIL;
            destructuring_bind_must_consp(roles, roles, $list102);
            subj_roles = roles.first();
            SubLObject current = roles.rest();
            destructuring_bind_must_consp(current, roles, $list102);
            dir_obj_roles = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, roles, $list102);
            indir_obj_roles = current.first();
            current = current.rest();
            if (NIL == current) {
                if (frame.eql($$MiddleVoiceFrame)) {
                    SubLObject cdolist_list_var = dir_obj_roles;
                    SubLObject subj_role = NIL;
                    subj_role = cdolist_list_var.first();
                    while (NIL != cdolist_list_var) {
                        templates = cons(list($$and, list($$isa, $ACTION, denot), bq_cons(subj_role, $list106)), templates);
                        cdolist_list_var = cdolist_list_var.rest();
                        subj_role = cdolist_list_var.first();
                    } 
                } else
                    if (NIL != psp_chart.intransitive_frameP(frame, mt)) {
                        SubLObject cdolist_list_var = subj_roles;
                        SubLObject subj_role = NIL;
                        subj_role = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            templates = cons(list($$and, list($$isa, $ACTION, denot), bq_cons(subj_role, $list106)), templates);
                            cdolist_list_var = cdolist_list_var.rest();
                            subj_role = cdolist_list_var.first();
                        } 
                    } else
                        if (NIL != psp_chart.transitive_np_comp_frameP(frame, mt)) {
                            SubLObject cdolist_list_var = subj_roles;
                            SubLObject subj_role = NIL;
                            subj_role = cdolist_list_var.first();
                            while (NIL != cdolist_list_var) {
                                SubLObject cdolist_list_var_$60 = dir_obj_roles;
                                SubLObject dir_obj_role = NIL;
                                dir_obj_role = cdolist_list_var_$60.first();
                                while (NIL != cdolist_list_var_$60) {
                                    templates = cons(list($$and, list($$isa, $ACTION, denot), bq_cons(subj_role, $list106), bq_cons(dir_obj_role, $list107)), templates);
                                    cdolist_list_var_$60 = cdolist_list_var_$60.rest();
                                    dir_obj_role = cdolist_list_var_$60.first();
                                } 
                                cdolist_list_var = cdolist_list_var.rest();
                                subj_role = cdolist_list_var.first();
                            } 
                        }


            } else {
                cdestructuring_bind_error(roles, $list102);
            }
        }
        return templates;
    }

    /**
     * helper for VERB-SEMTRANS-FROM-DENOT
     */
    @LispMethod(comment = "helper for VERB-SEMTRANS-FROM-DENOT")
    public static final SubLObject known_roles_for_denot_alt(SubLObject denot, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject roles = NIL;
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
                            SubLObject doer_roles = known_subject_roles_for_denot(denot, mt, UNPROVIDED);
                            SubLObject direct_object_roles = known_direct_object_roles_for_denot(denot, mt, UNPROVIDED);
                            SubLObject indirect_object_roles = known_indirect_object_roles_for_denot(denot, mt, UNPROVIDED);
                            if (((NIL != doer_roles) || (NIL != direct_object_roles)) || (NIL != indirect_object_roles)) {
                                roles = list(doer_roles, direct_object_roles, indirect_object_roles);
                            }
                        }
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return roles;
            }
        }
    }

    /**
     * helper for VERB-SEMTRANS-FROM-DENOT
     */
    @LispMethod(comment = "helper for VERB-SEMTRANS-FROM-DENOT")
    public static SubLObject known_roles_for_denot(final SubLObject denot, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject roles = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            final SubLObject doer_roles = known_subject_roles_for_denot(denot, mt, UNPROVIDED);
            final SubLObject direct_object_roles = known_direct_object_roles_for_denot(denot, mt, UNPROVIDED);
            final SubLObject indirect_object_roles = known_indirect_object_roles_for_denot(denot, mt, UNPROVIDED);
            if (((NIL != doer_roles) || (NIL != direct_object_roles)) || (NIL != indirect_object_roles)) {
                roles = list(doer_roles, direct_object_roles, indirect_object_roles);
            }
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return roles;
    }/**
     * helper for VERB-SEMTRANS-FROM-DENOT
     */


    public static final SubLObject known_subject_roles_for_denot_alt(SubLObject denot, SubLObject mt, SubLObject best_onlyP) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        return required_arg1_preds(denot, $$doneBy, mt, best_onlyP);
    }

    public static SubLObject known_subject_roles_for_denot(final SubLObject denot, SubLObject mt, SubLObject best_onlyP) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        return required_arg1_preds(denot, $$doneBy, mt, best_onlyP);
    }

    public static final SubLObject known_direct_object_roles_for_denot_alt(SubLObject denot, SubLObject mt, SubLObject best_onlyP) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        return required_arg1_preds(denot, $$patient_GenericDirect, mt, best_onlyP);
    }

    public static SubLObject known_direct_object_roles_for_denot(final SubLObject denot, SubLObject mt, SubLObject best_onlyP) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        return required_arg1_preds(denot, $$patient_GenericDirect, mt, best_onlyP);
    }

    public static final SubLObject known_indirect_object_roles_for_denot_alt(SubLObject denot, SubLObject mt, SubLObject best_onlyP) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        return required_arg1_preds(denot, $$patient_GenericIndirect, mt, best_onlyP);
    }

    public static SubLObject known_indirect_object_roles_for_denot(final SubLObject denot, SubLObject mt, SubLObject best_onlyP) {
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        return required_arg1_preds(denot, $$patient_GenericIndirect, mt, best_onlyP);
    }

    /**
     *
     *
     * @param COL
     * 		fort-p; A #$Collection
     * @param GENL-PRED
     * 		fort-p; If this is non-nil, return only spec-preds of GENL-PRED
     * @param MT;
     * 		which mt to look from
     * @param BEST-ONLY?
     * 		boolean; return a list of all answers, or only the best one?
     * @unknown LISTP; of predicates that any instance of COL is expected to stand in to something
     */
    @LispMethod(comment = "@param COL\r\n\t\tfort-p; A #$Collection\r\n@param GENL-PRED\r\n\t\tfort-p; If this is non-nil, return only spec-preds of GENL-PRED\r\n@param MT;\r\n\t\twhich mt to look from\r\n@param BEST-ONLY?\r\n\t\tboolean; return a list of all answers, or only the best one?\r\n@unknown LISTP; of predicates that any instance of COL is expected to stand in to something")
    public static final SubLObject required_arg1_preds_alt(SubLObject col, SubLObject genl_pred, SubLObject mt, SubLObject best_onlyP) {
        if (genl_pred == UNPROVIDED) {
            genl_pred = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            col = function_terms.naut_to_nart(col);
            if (NIL == fort_types_interface.collectionP(col)) {
                return NIL;
            }
            {
                SubLObject ans = NIL;
                SubLObject mt_var = mt;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        {
                            SubLObject genl_pred_pairs = NIL;
                            SubLObject rae_preds = $list_alt115;
                            {
                                SubLObject _prev_bind_0_65 = $ra1p_rae_preds$.currentBinding(thread);
                                SubLObject _prev_bind_1_66 = $ra1p_ra1_preds$.currentBinding(thread);
                                try {
                                    $ra1p_rae_preds$.bind(list_utilities.flatten(Mapping.mapcar(ALL_SPEC_PREDICATES, rae_preds)), thread);
                                    $ra1p_ra1_preds$.bind(genl_predicates.all_spec_predicates($$requiredArg1Pred, UNPROVIDED, UNPROVIDED), thread);
                                    {
                                        SubLObject cdolist_list_var = cons(col, genls.all_genls(col, UNPROVIDED, UNPROVIDED));
                                        SubLObject genl = NIL;
                                        for (genl = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , genl = cdolist_list_var.first()) {
                                            {
                                                SubLObject cdolist_list_var_67 = ra1p_do_one_event_type(genl);
                                                SubLObject req_pred = NIL;
                                                for (req_pred = cdolist_list_var_67.first(); NIL != cdolist_list_var_67; cdolist_list_var_67 = cdolist_list_var_67.rest() , req_pred = cdolist_list_var_67.first()) {
                                                    genl_pred_pairs = cons(list(genl, req_pred), genl_pred_pairs);
                                                }
                                            }
                                        }
                                    }
                                    genl_pred_pairs = delete_duplicates(genl_pred_pairs, symbol_function(EQUALP), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    {
                                        SubLObject spec_preds = (NIL != genl_pred) ? ((SubLObject) (genl_predicates.all_spec_predicates_among(genl_pred, Mapping.mapcar(symbol_function(SECOND), genl_pred_pairs), UNPROVIDED, UNPROVIDED))) : NIL;
                                        SubLObject cdolist_list_var = genl_pred_pairs;
                                        SubLObject pair = NIL;
                                        for (pair = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pair = cdolist_list_var.first()) {
                                            if ((NIL == genl_pred) || (NIL != subl_promotions.memberP(second(pair), spec_preds, UNPROVIDED, UNPROVIDED))) {
                                                ans = cons(pair, ans);
                                            }
                                        }
                                    }
                                    if (NIL != $ra1p_show_pairsP$.getGlobalValue()) {
                                        print(ans, UNPROVIDED);
                                    }
                                    if (NIL != best_onlyP) {
                                        {
                                            SubLObject sorted0 = Sort.sort(ans, symbol_function($sym119$GENL_PRED_), symbol_function(SECOND));
                                            SubLObject sorted = Sort.sort(sorted0, symbol_function($sym120$GENL_), symbol_function(FIRST));
                                            ans = second(sorted.first());
                                        }
                                    } else {
                                        ans = remove_genl_preds(delete_duplicates(Mapping.mapcar(symbol_function(SECOND), ans), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED);
                                    }
                                } finally {
                                    $ra1p_ra1_preds$.rebind(_prev_bind_1_66, thread);
                                    $ra1p_rae_preds$.rebind(_prev_bind_0_65, thread);
                                }
                            }
                        }
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return ans;
            }
        }
    }

    /**
     *
     *
     * @param COL
     * 		fort-p; A #$Collection
     * @param GENL-PRED
     * 		fort-p; If this is non-nil, return only spec-preds of GENL-PRED
     * @param MT;
     * 		which mt to look from
     * @param BEST-ONLY?
     * 		boolean; return a list of all answers, or only the best one?
     * @unknown LISTP; of predicates that any instance of COL is expected to stand in to something
     */
    @LispMethod(comment = "@param COL\r\n\t\tfort-p; A #$Collection\r\n@param GENL-PRED\r\n\t\tfort-p; If this is non-nil, return only spec-preds of GENL-PRED\r\n@param MT;\r\n\t\twhich mt to look from\r\n@param BEST-ONLY?\r\n\t\tboolean; return a list of all answers, or only the best one?\r\n@unknown LISTP; of predicates that any instance of COL is expected to stand in to something")
    public static SubLObject required_arg1_preds(SubLObject col, SubLObject genl_pred, SubLObject mt, SubLObject best_onlyP) {
        if (genl_pred == UNPROVIDED) {
            genl_pred = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = lexicon_vars.$lexicon_lookup_mt$.getDynamicValue();
        }
        if (best_onlyP == UNPROVIDED) {
            best_onlyP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        col = function_terms.naut_to_nart(col);
        if (NIL == fort_types_interface.collectionP(col)) {
            return NIL;
        }
        SubLObject ans = NIL;
        final SubLObject mt_var = mt;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            SubLObject genl_pred_pairs = NIL;
            final SubLObject rae_preds = $list111;
            final SubLObject _prev_bind_0_$61 = $ra1p_rae_preds$.currentBinding(thread);
            final SubLObject _prev_bind_1_$62 = $ra1p_ra1_preds$.currentBinding(thread);
            try {
                $ra1p_rae_preds$.bind(list_utilities.flatten(Mapping.mapcar(ALL_SPEC_PREDICATES, rae_preds)), thread);
                $ra1p_ra1_preds$.bind(genl_predicates.all_spec_predicates($$requiredArg1Pred, UNPROVIDED, UNPROVIDED), thread);
                SubLObject cdolist_list_var = cons(col, genls.all_genls(col, UNPROVIDED, UNPROVIDED));
                SubLObject genl = NIL;
                genl = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject cdolist_list_var_$63 = ra1p_do_one_event_type(genl);
                    SubLObject req_pred = NIL;
                    req_pred = cdolist_list_var_$63.first();
                    while (NIL != cdolist_list_var_$63) {
                        genl_pred_pairs = cons(list(genl, req_pred), genl_pred_pairs);
                        cdolist_list_var_$63 = cdolist_list_var_$63.rest();
                        req_pred = cdolist_list_var_$63.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    genl = cdolist_list_var.first();
                } 
                genl_pred_pairs = delete_duplicates(genl_pred_pairs, symbol_function(EQUALP), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                final SubLObject spec_preds = (NIL != genl_pred) ? genl_predicates.all_spec_predicates_among(genl_pred, Mapping.mapcar(symbol_function(SECOND), genl_pred_pairs), UNPROVIDED, UNPROVIDED) : NIL;
                SubLObject cdolist_list_var2 = genl_pred_pairs;
                SubLObject pair = NIL;
                pair = cdolist_list_var2.first();
                while (NIL != cdolist_list_var2) {
                    if ((NIL == genl_pred) || (NIL != subl_promotions.memberP(second(pair), spec_preds, UNPROVIDED, UNPROVIDED))) {
                        ans = cons(pair, ans);
                    }
                    cdolist_list_var2 = cdolist_list_var2.rest();
                    pair = cdolist_list_var2.first();
                } 
                if (NIL != $ra1p_show_pairsP$.getGlobalValue()) {
                    print(ans, UNPROVIDED);
                }
                if (NIL != best_onlyP) {
                    final SubLObject sorted0 = Sort.sort(ans, symbol_function($sym115$GENL_PRED_), symbol_function(SECOND));
                    final SubLObject sorted2 = Sort.sort(sorted0, symbol_function($sym116$GENL_), symbol_function(FIRST));
                    ans = second(sorted2.first());
                } else {
                    ans = remove_genl_preds(delete_duplicates(Mapping.mapcar(symbol_function(SECOND), ans), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED);
                }
            } finally {
                $ra1p_ra1_preds$.rebind(_prev_bind_1_$62, thread);
                $ra1p_rae_preds$.rebind(_prev_bind_0_$61, thread);
            }
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return ans;
    }

    /**
     * Remove any items from PREDS that are #$genlPreds of anything else in PREDS
     */
    @LispMethod(comment = "Remove any items from PREDS that are #$genlPreds of anything else in PREDS")
    public static final SubLObject remove_genl_preds_alt(SubLObject preds, SubLObject key) {
        if (key == UNPROVIDED) {
            key = symbol_function(IDENTITY);
        }
        if (!preds.isList()) {
            return NIL;
        }
        {
            SubLObject ans = NIL;
            SubLObject cdolist_list_var = preds;
            SubLObject pred = NIL;
            for (pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pred = cdolist_list_var.first()) {
                {
                    SubLObject others = remove(pred, preds, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    if (NIL == subl_promotions.memberP(funcall(key, pred), others, $sym122$SPEC_PRED_, key)) {
                        ans = cons(pred, ans);
                    }
                }
            }
            return ans;
        }
    }

    /**
     * Remove any items from PREDS that are #$genlPreds of anything else in PREDS
     */
    @LispMethod(comment = "Remove any items from PREDS that are #$genlPreds of anything else in PREDS")
    public static SubLObject remove_genl_preds(final SubLObject preds, SubLObject key) {
        if (key == UNPROVIDED) {
            key = symbol_function(IDENTITY);
        }
        if (!preds.isList()) {
            return NIL;
        }
        SubLObject ans = NIL;
        SubLObject cdolist_list_var = preds;
        SubLObject pred = NIL;
        pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject others = remove(pred, preds, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            if (NIL == subl_promotions.memberP(funcall(key, pred), others, $sym118$SPEC_PRED_, key)) {
                ans = cons(pred, ans);
            }
            cdolist_list_var = cdolist_list_var.rest();
            pred = cdolist_list_var.first();
        } 
        return ans;
    }/**
     * Remove any items from PREDS that are #$genlPreds of anything else in PREDS
     */


    /**
     *
     *
     * @return LISTP of required roles
     */
    @LispMethod(comment = "@return LISTP of required roles")
    public static final SubLObject ra1p_do_one_event_type_alt(SubLObject event_type) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject ans = NIL;
                {
                    SubLObject cdolist_list_var = $ra1p_ra1_preds$.getDynamicValue(thread);
                    SubLObject ra1_pred = NIL;
                    for (ra1_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , ra1_pred = cdolist_list_var.first()) {
                        {
                            SubLObject cdolist_list_var_68 = ra1p_do_one_event_typeXpred(event_type, ra1_pred, NIL);
                            SubLObject req_pred = NIL;
                            for (req_pred = cdolist_list_var_68.first(); NIL != cdolist_list_var_68; cdolist_list_var_68 = cdolist_list_var_68.rest() , req_pred = cdolist_list_var_68.first()) {
                                ans = cons(req_pred, ans);
                            }
                        }
                    }
                }
                {
                    SubLObject cdolist_list_var = $ra1p_rae_preds$.getDynamicValue(thread);
                    SubLObject rae_pred = NIL;
                    for (rae_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , rae_pred = cdolist_list_var.first()) {
                        {
                            SubLObject cdolist_list_var_69 = ra1p_do_one_event_typeXpred(event_type, rae_pred, T);
                            SubLObject req_pred = NIL;
                            for (req_pred = cdolist_list_var_69.first(); NIL != cdolist_list_var_69; cdolist_list_var_69 = cdolist_list_var_69.rest() , req_pred = cdolist_list_var_69.first()) {
                                ans = cons(req_pred, ans);
                            }
                        }
                    }
                }
                return ans;
            }
        }
    }

    /**
     *
     *
     * @return LISTP of required roles
     */
    @LispMethod(comment = "@return LISTP of required roles")
    public static SubLObject ra1p_do_one_event_type(final SubLObject event_type) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject ans = NIL;
        SubLObject cdolist_list_var = $ra1p_ra1_preds$.getDynamicValue(thread);
        SubLObject ra1_pred = NIL;
        ra1_pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$64 = ra1p_do_one_event_typeXpred(event_type, ra1_pred, NIL);
            SubLObject req_pred = NIL;
            req_pred = cdolist_list_var_$64.first();
            while (NIL != cdolist_list_var_$64) {
                ans = cons(req_pred, ans);
                cdolist_list_var_$64 = cdolist_list_var_$64.rest();
                req_pred = cdolist_list_var_$64.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            ra1_pred = cdolist_list_var.first();
        } 
        cdolist_list_var = $ra1p_rae_preds$.getDynamicValue(thread);
        SubLObject rae_pred = NIL;
        rae_pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$65 = ra1p_do_one_event_typeXpred(event_type, rae_pred, T);
            SubLObject req_pred = NIL;
            req_pred = cdolist_list_var_$65.first();
            while (NIL != cdolist_list_var_$65) {
                ans = cons(req_pred, ans);
                cdolist_list_var_$65 = cdolist_list_var_$65.rest();
                req_pred = cdolist_list_var_$65.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            rae_pred = cdolist_list_var.first();
        } 
        return ans;
    }

    public static final SubLObject ra1p_do_one_event_typeXpred_alt(SubLObject event_type, SubLObject pred, SubLObject invertedP) {
        {
            SubLObject index_arg = (NIL != invertedP) ? ((SubLObject) (TWO_INTEGER)) : ONE_INTEGER;
            SubLObject gather_arg = (NIL != invertedP) ? ((SubLObject) (ONE_INTEGER)) : TWO_INTEGER;
            if ((NIL != somewhere_cache.somewhere_cached_pred_p(pred)) && (NIL == somewhere_cache.some_pred_assertion_somewhereP(pred, event_type, index_arg, UNPROVIDED))) {
                return NIL;
            }
            return kb_mapping_utilities.pred_values(event_type, pred, index_arg, gather_arg, UNPROVIDED);
        }
    }

    public static SubLObject ra1p_do_one_event_typeXpred(final SubLObject event_type, final SubLObject pred, final SubLObject invertedP) {
        final SubLObject index_arg = (NIL != invertedP) ? TWO_INTEGER : ONE_INTEGER;
        final SubLObject gather_arg = (NIL != invertedP) ? ONE_INTEGER : TWO_INTEGER;
        if ((NIL != somewhere_cache.somewhere_cached_pred_p(pred)) && (NIL == somewhere_cache.some_pred_assertion_somewhereP(pred, event_type, index_arg, UNPROVIDED))) {
            return NIL;
        }
        return kb_mapping_utilities.pred_values(event_type, pred, index_arg, gather_arg, UNPROVIDED);
    }

    /**
     * if INTERP is a VariableFn expression, ensure that the variable in its arg1 position is unique.
     */
    @LispMethod(comment = "if INTERP is a VariableFn expression, ensure that the variable in its arg1 position is unique.")
    public static final SubLObject possibly_uniquify_variablefn_alt(SubLObject interp) {
        if (NIL == variablefn_termP(interp)) {
            return interp;
        }
        {
            SubLObject existing_var = cycl_utilities.formula_arg1(interp, UNPROVIDED);
            SubLObject new_var = unique_el_var_for_variablefn(interp);
            SubLObject result = cycl_utilities.expression_subst(new_var, existing_var, interp, UNPROVIDED, UNPROVIDED);
            return result;
        }
    }

    /**
     * if INTERP is a VariableFn expression, ensure that the variable in its arg1 position is unique.
     */
    @LispMethod(comment = "if INTERP is a VariableFn expression, ensure that the variable in its arg1 position is unique.")
    public static SubLObject possibly_uniquify_variablefn(final SubLObject interp) {
        if (NIL == variablefn_termP(interp)) {
            return interp;
        }
        final SubLObject existing_var = cycl_utilities.formula_arg1(interp, UNPROVIDED);
        final SubLObject new_var = unique_el_var_for_variablefn(interp);
        final SubLObject result = cycl_utilities.expression_subst(new_var, existing_var, interp, UNPROVIDED, UNPROVIDED);
        return result;
    }/**
     * if INTERP is a VariableFn expression, ensure that the variable in its arg1 position is unique.
     */


    public static final SubLObject unique_el_var_for_variablefn_alt(SubLObject interp) {
        {
            SubLObject datum = cycl_utilities.nat_args(interp, UNPROVIDED);
            SubLObject current = datum;
            SubLObject existing_var = NIL;
            SubLObject type = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt123);
            existing_var = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt123);
            type = current.first();
            current = current.rest();
            if (NIL == current) {
                {
                    SubLObject type_string = (NIL != good_type_for_var_nameP(type)) ? ((SubLObject) (pph_main.generate_phrase(type, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) : cycl_variables.variable_name_without_prefix(existing_var);
                    SubLObject var_root = string_to_el_var_name(type_string);
                    SubLObject var_name = next_unique_var_name(var_root);
                    SubLObject var = cycl_variables.make_el_var(var_name);
                    return var;
                }
            } else {
                cdestructuring_bind_error(datum, $list_alt123);
            }
        }
        return NIL;
    }

    public static SubLObject unique_el_var_for_variablefn(final SubLObject interp) {
        SubLObject current;
        final SubLObject datum = current = cycl_utilities.nat_args(interp, UNPROVIDED);
        SubLObject existing_var = NIL;
        SubLObject type = NIL;
        destructuring_bind_must_consp(current, datum, $list119);
        existing_var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list119);
        type = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject type_string = (NIL != good_type_for_var_nameP(type)) ? pph_main.generate_phrase(type, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED) : cycl_variables.variable_name_without_prefix(existing_var);
            final SubLObject var_root = string_to_el_var_name(type_string);
            final SubLObject var_name = next_unique_var_name(var_root);
            final SubLObject var = cycl_variables.make_el_var(var_name);
            return var;
        }
        cdestructuring_bind_error(datum, $list119);
        return NIL;
    }

    public static final SubLObject good_type_for_var_nameP_alt(SubLObject type) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return makeBoolean((NIL != forts.fort_p(type)) && (NIL == rkf_relevance_utilities.rkf_irrelevant_term(type, parsing_vars.$parsing_domain_mt$.getDynamicValue(thread))));
        }
    }

    public static SubLObject good_type_for_var_nameP(final SubLObject type) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return makeBoolean((NIL != forts.fort_p(type)) && (NIL == rkf_relevance_utilities.rkf_irrelevant_term(type, parsing_vars.$parsing_domain_mt$.getDynamicValue(thread))));
    }

    public static final SubLObject variablefn_termP_alt(SubLObject obj) {
        return makeBoolean((obj.isCons() && (NIL != formula_arityE(obj, TWO_INTEGER, UNPROVIDED))) && (cycl_utilities.formula_arg0(obj) == $$VariableFn));
    }

    public static SubLObject variablefn_termP(final SubLObject obj) {
        return makeBoolean((obj.isCons() && (NIL != formula_arityE(obj, TWO_INTEGER, UNPROVIDED))) && cycl_utilities.formula_arg0(obj).eql($$VariableFn));
    }

    /**
     *
     *
     * @param VAR-NAME
    string
     * 		
     * @return string; a variable name that's unique wrt the currently-applicable uniquification store.  Variables
    are generally made unique by adding numbers to the end of them.  Note that this does not ensure completely unique
    variable names, since there may be variables that are not stored in the uniquification store
     * @unknown make sure that all variables (e.g. from elsewhere in the parse) get entered into the table, even if
    they're not uniquified
     */
    @LispMethod(comment = "@param VAR-NAME\nstring\r\n\t\t\r\n@return string; a variable name that\'s unique wrt the currently-applicable uniquification store.  Variables\r\nare generally made unique by adding numbers to the end of them.  Note that this does not ensure completely unique\r\nvariable names, since there may be variables that are not stored in the uniquification store\r\n@unknown make sure that all variables (e.g. from elsewhere in the parse) get entered into the table, even if\r\nthey\'re not uniquified")
    public static final SubLObject next_unique_var_name_alt(SubLObject var_name) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject var_num = dictionary.dictionary_lookup(parsing_vars.$variable_uniquification_store$.getDynamicValue(thread), var_name, $$$0);
                SubLObject var_suffix = (var_num.isNumber()) ? ((SubLObject) (cconcatenate($str_alt126$_, string_utilities.to_string(var_num)))) : var_num;
                SubLObject result = cconcatenate(var_name, var_suffix);
                if (var_num.isNumber()) {
                    dictionary.dictionary_enter(parsing_vars.$variable_uniquification_store$.getDynamicValue(thread), var_name, number_utilities.f_1X(var_num));
                } else {
                    dictionary.dictionary_enter(parsing_vars.$variable_uniquification_store$.getDynamicValue(thread), var_name, ONE_INTEGER);
                }
                return result;
            }
        }
    }

    /**
     *
     *
     * @param VAR-NAME
    string
     * 		
     * @return string; a variable name that's unique wrt the currently-applicable uniquification store.  Variables
    are generally made unique by adding numbers to the end of them.  Note that this does not ensure completely unique
    variable names, since there may be variables that are not stored in the uniquification store
     * @unknown make sure that all variables (e.g. from elsewhere in the parse) get entered into the table, even if
    they're not uniquified
     */
    @LispMethod(comment = "@param VAR-NAME\nstring\r\n\t\t\r\n@return string; a variable name that\'s unique wrt the currently-applicable uniquification store.  Variables\r\nare generally made unique by adding numbers to the end of them.  Note that this does not ensure completely unique\r\nvariable names, since there may be variables that are not stored in the uniquification store\r\n@unknown make sure that all variables (e.g. from elsewhere in the parse) get entered into the table, even if\r\nthey\'re not uniquified")
    public static SubLObject next_unique_var_name(final SubLObject var_name) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject var_num = dictionary.dictionary_lookup(parsing_vars.$variable_uniquification_store$.getDynamicValue(thread), var_name, $$$0);
        final SubLObject var_suffix = (var_num.isNumber()) ? cconcatenate($str122$_, string_utilities.to_string(var_num)) : var_num;
        final SubLObject result = cconcatenate(var_name, var_suffix);
        if (var_num.isNumber()) {
            dictionary.dictionary_enter(parsing_vars.$variable_uniquification_store$.getDynamicValue(thread), var_name, number_utilities.f_1X(var_num));
        } else {
            dictionary.dictionary_enter(parsing_vars.$variable_uniquification_store$.getDynamicValue(thread), var_name, ONE_INTEGER);
        }
        return result;
    }

    public static final SubLObject parsing_rewrite_of_inverseP_alt(SubLObject dispreferred_term, SubLObject preferred_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return parsing_rewrite_ofP(preferred_term, dispreferred_term, mt);
    }

    public static SubLObject parsing_rewrite_of_inverseP(final SubLObject dispreferred_term, final SubLObject preferred_term, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        return parsing_rewrite_ofP(preferred_term, dispreferred_term, mt);
    }

    /**
     *
     *
     * @return BOOLEANP; Is TERM1 a rewrite (i.e. a preferable semantic equivalent) of TERM2?
     * @unknown baxter
     */
    @LispMethod(comment = "@return BOOLEANP; Is TERM1 a rewrite (i.e. a preferable semantic equivalent) of TERM2?\r\n@unknown baxter")
    public static final SubLObject parsing_rewrite_ofP_alt(SubLObject term1, SubLObject term2, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        term1 = narts_high.nart_substitute(term1);
        term2 = narts_high.nart_substitute(term2);
        if ((NIL != forts.fort_p(term1)) && (NIL != forts.fort_p(term2))) {
            return makeBoolean((NIL != equals.direct_rewrite_ofP(term1, term2, mt)) || (NIL != subl_promotions.memberP(function_terms.nart_to_naut(term2), kb_mapping_utilities.pred_values_in_any_mt(term1, $$parsingConflateTo, UNPROVIDED, UNPROVIDED, UNPROVIDED), symbol_function(EQUALP), symbol_function(NART_TO_NAUT))));
        } else {
            if (NIL != indexed_term_p(term1)) {
                return subl_promotions.memberP(function_terms.nart_to_naut(term2), kb_mapping_utilities.pred_values_in_any_mt(term1, $$rewriteOf, UNPROVIDED, UNPROVIDED, UNPROVIDED), symbol_function(EQUALP), NART_TO_NAUT);
            } else {
                if (term1.equal(relation_evaluation.cyc_evaluate(term2))) {
                    return T;
                } else {
                    return NIL;
                }
            }
        }
    }

    /**
     *
     *
     * @return BOOLEANP; Is TERM1 a rewrite (i.e. a preferable semantic equivalent) of TERM2?
     * @unknown baxter
     */
    @LispMethod(comment = "@return BOOLEANP; Is TERM1 a rewrite (i.e. a preferable semantic equivalent) of TERM2?\r\n@unknown baxter")
    public static SubLObject parsing_rewrite_ofP(SubLObject term1, SubLObject term2, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        term1 = narts_high.nart_substitute(term1);
        term2 = narts_high.nart_substitute(term2);
        if ((NIL != forts.fort_p(term1)) && (NIL != forts.fort_p(term2))) {
            return makeBoolean((NIL != equals.direct_rewrite_ofP(term1, term2, mt)) || (NIL != subl_promotions.memberP(function_terms.nart_to_naut(term2), kb_mapping_utilities.pred_values_in_any_mt(term1, $$parsingConflateTo, UNPROVIDED, UNPROVIDED, UNPROVIDED), symbol_function(EQUALP), symbol_function(NART_TO_NAUT))));
        }
        if (NIL != indexed_term_p(term1)) {
            return subl_promotions.memberP(function_terms.nart_to_naut(term2), kb_mapping_utilities.pred_values_in_any_mt(term1, $$rewriteOf, UNPROVIDED, UNPROVIDED, UNPROVIDED), symbol_function(EQUALP), NART_TO_NAUT);
        }
        if (term1.equal(relation_evaluation.cyc_evaluate(term2))) {
            return T;
        }
        return NIL;
    }

    public static final SubLObject parse_tree_creation_rules_alt() {
        {
            SubLObject rules = forward.creation_template_allowable_rules($$ParseTreeReificationTemplate);
            return rules;
        }
    }

    public static SubLObject parse_tree_creation_rules() {
        final SubLObject rules = forward.creation_template_allowable_rules($$ParseTreeReificationTemplate);
        return rules;
    }

    public static final SubLObject syntactic_node_p_alt(SubLObject v_object, SubLObject strictP) {
        if (strictP == UNPROVIDED) {
            strictP = parsing_vars.$use_strict_syntactic_node_checkingP$.getDynamicValue();
        }
        return makeBoolean((NIL != forts.fort_p(v_object)) && ((NIL == strictP) || (NIL != isa.isa_in_any_mtP(v_object, $$SyntacticNode))));
    }

    public static SubLObject syntactic_node_p(final SubLObject v_object, SubLObject strictP) {
        if (strictP == UNPROVIDED) {
            strictP = parsing_vars.$use_strict_syntactic_node_checkingP$.getDynamicValue();
        }
        return makeBoolean((NIL != forts.fort_p(v_object)) && ((NIL == strictP) || (NIL != isa.isa_in_any_mtP(v_object, $$SyntacticNode))));
    }

    public static final SubLObject lexical_nodeP_alt(SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$LexicalNode);
    }

    public static SubLObject lexical_nodeP(final SubLObject v_object) {
        return isa.isa_in_any_mtP(v_object, $$LexicalNode);
    }

    public static final SubLObject phrasal_nodeP_alt(SubLObject v_object) {
        return makeBoolean((NIL != syntactic_node_p(v_object, T)) && (NIL != list_utilities.non_empty_list_p(syntactic_node_dtrs(v_object))));
    }

    public static SubLObject phrasal_nodeP(final SubLObject v_object) {
        return makeBoolean((NIL != syntactic_node_p(v_object, T)) && (NIL != list_utilities.non_empty_list_p(syntactic_node_dtrs(v_object))));
    }

    public static final SubLObject syntactic_choice_node_p_alt(SubLObject v_object, SubLObject strictP) {
        if (strictP == UNPROVIDED) {
            strictP = parsing_vars.$use_strict_syntactic_node_checkingP$.getDynamicValue();
        }
        return makeBoolean((NIL != forts.fort_p(v_object)) && (NIL != isa.isa_in_any_mtP(v_object, $$SyntacticChoiceNode)));
    }

    public static SubLObject syntactic_choice_node_p(final SubLObject v_object, SubLObject strictP) {
        if (strictP == UNPROVIDED) {
            strictP = parsing_vars.$use_strict_syntactic_node_checkingP$.getDynamicValue();
        }
        return makeBoolean((NIL != forts.fort_p(v_object)) && (NIL != isa.isa_in_any_mtP(v_object, $$SyntacticChoiceNode)));
    }

    /**
     *
     *
     * @param NODE;
    syntactic-node-p.
     * 		
     * @return LISTP; sorted list of the daughters of NODE.
     */
    @LispMethod(comment = "@param NODE;\nsyntactic-node-p.\r\n\t\t\r\n@return LISTP; sorted list of the daughters of NODE.")
    public static final SubLObject syntactic_node_dtrs_alt(SubLObject node) {
        {
            SubLObject pairs = inference_kernel.new_cyc_query(listS($$syntacticNodeNthDaughter, node, $list_alt135), $$EverythingPSC, $list_alt136);
            SubLObject sorted = Sort.sort(pairs, symbol_function($sym137$_), symbol_function(SECOND));
            if (NIL != sorted) {
                return Mapping.mapcar(symbol_function(FIRST), sorted);
            } else {
                return inference_kernel.new_cyc_query(listS($$syntacticDaughters, node, $list_alt139), $$EverythingPSC, $list_alt140);
            }
        }
    }

    /**
     *
     *
     * @param NODE;
    syntactic-node-p.
     * 		
     * @return LISTP; sorted list of the daughters of NODE.
     */
    @LispMethod(comment = "@param NODE;\nsyntactic-node-p.\r\n\t\t\r\n@return LISTP; sorted list of the daughters of NODE.")
    public static SubLObject syntactic_node_dtrs(final SubLObject node) {
        final SubLObject pairs = inference_kernel.new_cyc_query(listS($$syntacticNodeNthDaughter, node, $list131), $$EverythingPSC, $list132);
        final SubLObject sorted = Sort.sort(pairs, symbol_function($sym133$_), symbol_function(SECOND));
        if (NIL != sorted) {
            return Mapping.mapcar(symbol_function(FIRST), sorted);
        }
        return inference_kernel.new_cyc_query(listS($$syntacticDaughters, node, $list135), $$EverythingPSC, $list136);
    }

    /**
     *
     *
     * @param NODE;
    syntactic-node-p.
     * 		
     * @return syntactic-node-p or NIL; the mother of NODE.
     */
    @LispMethod(comment = "@param NODE;\nsyntactic-node-p.\r\n\t\t\r\n@return syntactic-node-p or NIL; the mother of NODE.")
    public static final SubLObject syntactic_node_mother_alt(SubLObject node) {
        return inference_kernel.new_cyc_query(list($$syntacticDaughters, $sym141$_MOTHER, node), $$EverythingPSC, $list_alt142).first();
    }

    /**
     *
     *
     * @param NODE;
    syntactic-node-p.
     * 		
     * @return syntactic-node-p or NIL; the mother of NODE.
     */
    @LispMethod(comment = "@param NODE;\nsyntactic-node-p.\r\n\t\t\r\n@return syntactic-node-p or NIL; the mother of NODE.")
    public static SubLObject syntactic_node_mother(final SubLObject node) {
        return inference_kernel.new_cyc_query(list($$syntacticDaughters, $sym137$_MOTHER, node), $$EverythingPSC, $list138).first();
    }

    /**
     *
     *
     * @param NODE;
    syntactic-node-p.
     * 		
     * @return syntactic-node-p or NIL; the root node of (the parse tree containing) NODE.
     */
    @LispMethod(comment = "@param NODE;\nsyntactic-node-p.\r\n\t\t\r\n@return syntactic-node-p or NIL; the root node of (the parse tree containing) NODE.")
    public static final SubLObject syntactic_node_root_node_alt(SubLObject node) {
        {
            SubLObject candidate = node;
            SubLObject mother = syntactic_node_mother(candidate);
            while (NIL != mother) {
                candidate = mother;
                mother = syntactic_node_mother(candidate);
            } 
            return candidate;
        }
    }

    /**
     *
     *
     * @param NODE;
    syntactic-node-p.
     * 		
     * @return syntactic-node-p or NIL; the root node of (the parse tree containing) NODE.
     */
    @LispMethod(comment = "@param NODE;\nsyntactic-node-p.\r\n\t\t\r\n@return syntactic-node-p or NIL; the root node of (the parse tree containing) NODE.")
    public static SubLObject syntactic_node_root_node(final SubLObject node) {
        SubLObject candidate = node;
        for (SubLObject mother = syntactic_node_mother(candidate); NIL != mother; mother = syntactic_node_mother(candidate)) {
            candidate = mother;
        }
        return candidate;
    }

    /**
     *
     *
     * @param NODE
    syntactic-node-p.
     * 		
     * @param N
     * 		non-negative-integer-p; zero-indexed daughter number.
     */
    @LispMethod(comment = "@param NODE\nsyntactic-node-p.\r\n\t\t\r\n@param N\r\n\t\tnon-negative-integer-p; zero-indexed daughter number.")
    public static final SubLObject syntactic_node_nth_dtr_alt(SubLObject node, SubLObject n) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject dtr_num = number_utilities.f_1X(n);
                SubLObject dtr = NIL;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                        mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                        {
                            SubLObject pred_var = $$syntacticNodeNthDaughter;
                            if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(node, ONE_INTEGER, pred_var)) {
                                {
                                    SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(node, ONE_INTEGER, pred_var);
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
                                                        final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, NIL, NIL);
                                                        {
                                                            SubLObject done_var_70 = NIL;
                                                            SubLObject token_var_71 = NIL;
                                                            while (NIL == done_var_70) {
                                                                {
                                                                    SubLObject gaf = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_71);
                                                                    SubLObject valid_72 = makeBoolean(token_var_71 != gaf);
                                                                    if (NIL != valid_72) {
                                                                        if (assertions_high.gaf_arg2(gaf).eql(dtr_num)) {
                                                                            dtr = assertions_high.gaf_arg3(gaf);
                                                                        }
                                                                    }
                                                                    done_var_70 = makeBoolean(NIL == valid_72);
                                                                }
                                                            } 
                                                        }
                                                    } finally {
                                                        {
                                                            SubLObject _prev_bind_0_73 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                            try {
                                                                $is_thread_performing_cleanupP$.bind(T, thread);
                                                                if (NIL != final_index_iterator) {
                                                                    kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                                }
                                                            } finally {
                                                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_73, thread);
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
                        }
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return dtr;
            }
        }
    }

    /**
     *
     *
     * @param NODE
    syntactic-node-p.
     * 		
     * @param N
     * 		non-negative-integer-p; zero-indexed daughter number.
     */
    @LispMethod(comment = "@param NODE\nsyntactic-node-p.\r\n\t\t\r\n@param N\r\n\t\tnon-negative-integer-p; zero-indexed daughter number.")
    public static SubLObject syntactic_node_nth_dtr(final SubLObject node, final SubLObject n) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject dtr_num = number_utilities.f_1X(n);
        SubLObject dtr = NIL;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
            mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
            final SubLObject pred_var = $$syntacticNodeNthDaughter;
            if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(node, ONE_INTEGER, pred_var)) {
                final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(node, ONE_INTEGER, pred_var);
                SubLObject done_var = NIL;
                final SubLObject token_var = NIL;
                while (NIL == done_var) {
                    final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                    final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                    if (NIL != valid) {
                        SubLObject final_index_iterator = NIL;
                        try {
                            final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, NIL, NIL);
                            SubLObject done_var_$66 = NIL;
                            final SubLObject token_var_$67 = NIL;
                            while (NIL == done_var_$66) {
                                final SubLObject gaf = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$67);
                                final SubLObject valid_$68 = makeBoolean(!token_var_$67.eql(gaf));
                                if ((NIL != valid_$68) && assertions_high.gaf_arg2(gaf).eql(dtr_num)) {
                                    dtr = assertions_high.gaf_arg3(gaf);
                                }
                                done_var_$66 = makeBoolean(NIL == valid_$68);
                            } 
                        } finally {
                            final SubLObject _prev_bind_0_$69 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                final SubLObject _values = getValuesAsVector();
                                if (NIL != final_index_iterator) {
                                    kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                }
                                restoreValuesFromVector(_values);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$69, thread);
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
        return dtr;
    }

    /**
     *
     *
     * @param NODE
    syntactic-node-p.
     * 		
     * @param PATH
     * 		listp of non-negative integers.
     */
    @LispMethod(comment = "@param NODE\nsyntactic-node-p.\r\n\t\t\r\n@param PATH\r\n\t\tlistp of non-negative integers.")
    public static final SubLObject syntactic_node_descendant_at_path_alt(SubLObject node, SubLObject path) {
        {
            SubLObject remaining_path = path;
            SubLObject node_at_path_so_far = node;
            while ((NIL != node_at_path_so_far) && (NIL != remaining_path)) {
                node_at_path_so_far = syntactic_node_nth_dtr(node_at_path_so_far, remaining_path.first());
                remaining_path = remaining_path.rest();
            } 
            return node_at_path_so_far;
        }
    }

    /**
     *
     *
     * @param NODE
    syntactic-node-p.
     * 		
     * @param PATH
     * 		listp of non-negative integers.
     */
    @LispMethod(comment = "@param NODE\nsyntactic-node-p.\r\n\t\t\r\n@param PATH\r\n\t\tlistp of non-negative integers.")
    public static SubLObject syntactic_node_descendant_at_path(final SubLObject node, final SubLObject path) {
        SubLObject remaining_path;
        SubLObject node_at_path_so_far;
        for (remaining_path = path, node_at_path_so_far = node; (NIL != node_at_path_so_far) && (NIL != remaining_path); node_at_path_so_far = syntactic_node_nth_dtr(node_at_path_so_far, remaining_path.first()) , remaining_path = remaining_path.rest()) {
        }
        return node_at_path_so_far;
    }

    /**
     * Assert a parse-tree-related SENTENCE into MT.
     */
    @LispMethod(comment = "Assert a parse-tree-related SENTENCE into MT.")
    public static final SubLObject assert_parse_tree_info_alt(SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject _prev_bind_0 = fi.$fi_error$.currentBinding(thread);
                SubLObject _prev_bind_1 = fi.$fi_warning$.currentBinding(thread);
                try {
                    fi.$fi_error$.bind(NIL, thread);
                    fi.$fi_warning$.bind(NIL, thread);
                    thread.resetMultipleValues();
                    {
                        SubLObject successP = ke.ke_assert_wff_now(sentence, mt, UNPROVIDED, UNPROVIDED);
                        SubLObject fi_error = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (NIL == successP) {
                            Errors.error(fi.fi_error_string(fi_error));
                        }
                    }
                } finally {
                    fi.$fi_warning$.rebind(_prev_bind_1, thread);
                    fi.$fi_error$.rebind(_prev_bind_0, thread);
                }
            }
            return sentence;
        }
    }

    /**
     * Assert a parse-tree-related SENTENCE into MT.
     */
    @LispMethod(comment = "Assert a parse-tree-related SENTENCE into MT.")
    public static SubLObject assert_parse_tree_info(final SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject _prev_bind_0 = fi.$fi_error$.currentBinding(thread);
        final SubLObject _prev_bind_2 = fi.$fi_warning$.currentBinding(thread);
        try {
            fi.$fi_error$.bind(NIL, thread);
            fi.$fi_warning$.bind(NIL, thread);
            thread.resetMultipleValues();
            final SubLObject successP = ke.ke_assert_wff_now(sentence, mt, UNPROVIDED, UNPROVIDED);
            final SubLObject fi_error = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL == successP) {
                Errors.error(fi.fi_error_string(fi_error));
            }
        } finally {
            fi.$fi_warning$.rebind(_prev_bind_2, thread);
            fi.$fi_error$.rebind(_prev_bind_0, thread);
        }
        return sentence;
    }/**
     * Assert a parse-tree-related SENTENCE into MT.
     */


    /**
     * Propose MEANING for NODE in MT.
     */
    @LispMethod(comment = "Propose MEANING for NODE in MT.")
    public static final SubLObject syntactic_node_propose_meaning_alt(SubLObject node, SubLObject meaning, SubLObject pred, SubLObject mt) {
        if (pred == UNPROVIDED) {
            pred = $$proposedMeaning;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list(pred, node, package_node_meaning(meaning)), mt);
        return node;
    }

    /**
     * Propose MEANING for NODE in MT.
     */
    @LispMethod(comment = "Propose MEANING for NODE in MT.")
    public static SubLObject syntactic_node_propose_meaning(final SubLObject node, final SubLObject meaning, SubLObject pred, SubLObject mt) {
        if (pred == UNPROVIDED) {
            pred = $$proposedMeaning;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list(pred, node, package_node_meaning(meaning)), mt);
        return node;
    }/**
     * Propose MEANING for NODE in MT.
     */


    /**
     * State dependency of MOTHER-MEANING on DTR-MEANING in MT.
     */
    @LispMethod(comment = "State dependency of MOTHER-MEANING on DTR-MEANING in MT.")
    public static final SubLObject syntactic_node_note_semantic_dependency_alt(SubLObject mother, SubLObject mother_meaning, SubLObject dtr, SubLObject dtr_meaning, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list($$dependentMeaning, mother, package_node_meaning(mother_meaning), dtr, package_node_meaning(dtr_meaning)), mt);
        return mother;
    }

    /**
     * State dependency of MOTHER-MEANING on DTR-MEANING in MT.
     */
    @LispMethod(comment = "State dependency of MOTHER-MEANING on DTR-MEANING in MT.")
    public static SubLObject syntactic_node_note_semantic_dependency(final SubLObject mother, final SubLObject mother_meaning, final SubLObject dtr, final SubLObject dtr_meaning, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list($$dependentMeaning, mother, package_node_meaning(mother_meaning), dtr, package_node_meaning(dtr_meaning)), mt);
        return mother;
    }/**
     * State dependency of MOTHER-MEANING on DTR-MEANING in MT.
     */


    /**
     * Gathering meanings for NODE in MT wrt #$proposedMeaning (or a spec thereof)
     */
    @LispMethod(comment = "Gathering meanings for NODE in MT wrt #$proposedMeaning (or a spec thereof)")
    public static final SubLObject syntactic_node_proposed_meanings_alt(SubLObject node, SubLObject mt, SubLObject pred) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (pred == UNPROVIDED) {
            pred = $$proposedMeaning;
        }
        if (NIL != genl_predicates.genl_predicate_in_any_mtP(pred, $$proposedMeaning)) {
            return ask_utilities.ask_variable($sym145$_MEANING, listS(pred, node, $list_alt146), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        return NIL;
    }

    /**
     * Gathering meanings for NODE in MT wrt #$proposedMeaning (or a spec thereof)
     */
    @LispMethod(comment = "Gathering meanings for NODE in MT wrt #$proposedMeaning (or a spec thereof)")
    public static SubLObject syntactic_node_proposed_meanings(final SubLObject node, SubLObject mt, SubLObject pred) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (pred == UNPROVIDED) {
            pred = $$proposedMeaning;
        }
        if (NIL != genl_predicates.genl_predicate_in_any_mtP(pred, $$proposedMeaning)) {
            return ask_utilities.ask_variable($sym141$_MEANING, listS(pred, node, $list142), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        return NIL;
    }/**
     * Gathering meanings for NODE in MT wrt #$proposedMeaning (or a spec thereof)
     */


    public static final SubLObject push_meanings_from_syntactic_node_alt(SubLObject macroform, SubLObject environment) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = macroform.rest();
                SubLObject current = datum;
                SubLObject meanings = NIL;
                SubLObject node = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt147);
                meanings = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt147);
                node = current.first();
                current = current.rest();
                {
                    SubLObject allow_other_keys_p = NIL;
                    SubLObject rest = current;
                    SubLObject bad = NIL;
                    SubLObject current_74 = NIL;
                    for (; NIL != rest;) {
                        destructuring_bind_must_consp(rest, datum, $list_alt147);
                        current_74 = rest.first();
                        rest = rest.rest();
                        destructuring_bind_must_consp(rest, datum, $list_alt147);
                        if (NIL == member(current_74, $list_alt148, UNPROVIDED, UNPROVIDED)) {
                            bad = T;
                        }
                        if (current_74 == $ALLOW_OTHER_KEYS) {
                            allow_other_keys_p = rest.first();
                        }
                        rest = rest.rest();
                    }
                    if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                        cdestructuring_bind_error(datum, $list_alt147);
                    }
                    {
                        SubLObject mt_tail = property_list_member($MT, current);
                        SubLObject mt = (NIL != mt_tail) ? ((SubLObject) (cadr(mt_tail))) : parsing_vars.$parse_tree_mt$.getDynamicValue(thread);
                        SubLObject pred_tail = property_list_member($PRED, current);
                        SubLObject pred = (NIL != pred_tail) ? ((SubLObject) (cadr(pred_tail))) : $$proposedMeaning;
                        SubLObject this_meaning = $sym152$THIS_MEANING;
                        return list(CDOLIST, list(this_meaning, list(SYNTACTIC_NODE_PROPOSED_MEANINGS, node, mt, pred)), listS(CPUSHNEW, this_meaning, meanings, $list_alt156));
                    }
                }
            }
        }
    }

    public static SubLObject push_meanings_from_syntactic_node(final SubLObject macroform, final SubLObject environment) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject meanings = NIL;
        SubLObject node = NIL;
        destructuring_bind_must_consp(current, datum, $list143);
        meanings = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list143);
        node = current.first();
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$70 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list143);
            current_$70 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list143);
            if (NIL == member(current_$70, $list144, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$70 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list143);
        }
        final SubLObject mt_tail = property_list_member($MT, current);
        final SubLObject mt = (NIL != mt_tail) ? cadr(mt_tail) : parsing_vars.$parse_tree_mt$.getDynamicValue(thread);
        final SubLObject pred_tail = property_list_member($PRED, current);
        final SubLObject pred = (NIL != pred_tail) ? cadr(pred_tail) : $$proposedMeaning;
        final SubLObject this_meaning = $sym148$THIS_MEANING;
        return list(CDOLIST, list(this_meaning, list(SYNTACTIC_NODE_PROPOSED_MEANINGS, node, mt, pred)), listS(CPUSHNEW, this_meaning, meanings, $list152));
    }

    public static final SubLObject push_node_meaning_pairs_from_syntactic_node_alt(SubLObject macroform, SubLObject environment) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = macroform.rest();
                SubLObject current = datum;
                SubLObject meanings = NIL;
                SubLObject node = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt147);
                meanings = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt147);
                node = current.first();
                current = current.rest();
                {
                    SubLObject allow_other_keys_p = NIL;
                    SubLObject rest = current;
                    SubLObject bad = NIL;
                    SubLObject current_75 = NIL;
                    for (; NIL != rest;) {
                        destructuring_bind_must_consp(rest, datum, $list_alt147);
                        current_75 = rest.first();
                        rest = rest.rest();
                        destructuring_bind_must_consp(rest, datum, $list_alt147);
                        if (NIL == member(current_75, $list_alt148, UNPROVIDED, UNPROVIDED)) {
                            bad = T;
                        }
                        if (current_75 == $ALLOW_OTHER_KEYS) {
                            allow_other_keys_p = rest.first();
                        }
                        rest = rest.rest();
                    }
                    if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                        cdestructuring_bind_error(datum, $list_alt147);
                    }
                    {
                        SubLObject mt_tail = property_list_member($MT, current);
                        SubLObject mt = (NIL != mt_tail) ? ((SubLObject) (cadr(mt_tail))) : parsing_vars.$parse_tree_mt$.getDynamicValue(thread);
                        SubLObject pred_tail = property_list_member($PRED, current);
                        SubLObject pred = (NIL != pred_tail) ? ((SubLObject) (cadr(pred_tail))) : $$proposedMeaning;
                        SubLObject this_meaning = $sym157$THIS_MEANING;
                        return list(CDOLIST, list(this_meaning, list(SYNTACTIC_NODE_PROPOSED_MEANINGS, node, mt, pred)), listS(CPUSHNEW, list(LIST, node, this_meaning), meanings, $list_alt156));
                    }
                }
            }
        }
    }

    public static SubLObject push_node_meaning_pairs_from_syntactic_node(final SubLObject macroform, final SubLObject environment) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject meanings = NIL;
        SubLObject node = NIL;
        destructuring_bind_must_consp(current, datum, $list143);
        meanings = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list143);
        node = current.first();
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$71 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list143);
            current_$71 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list143);
            if (NIL == member(current_$71, $list144, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$71 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list143);
        }
        final SubLObject mt_tail = property_list_member($MT, current);
        final SubLObject mt = (NIL != mt_tail) ? cadr(mt_tail) : parsing_vars.$parse_tree_mt$.getDynamicValue(thread);
        final SubLObject pred_tail = property_list_member($PRED, current);
        final SubLObject pred = (NIL != pred_tail) ? cadr(pred_tail) : $$proposedMeaning;
        final SubLObject this_meaning = $sym153$THIS_MEANING;
        return list(CDOLIST, list(this_meaning, list(SYNTACTIC_NODE_PROPOSED_MEANINGS, node, mt, pred)), listS(CPUSHNEW, list(LIST, node, this_meaning), meanings, $list152));
    }

    public static final SubLObject recently_created_parse_tree_elements_alt() {
        return $recently_created_parse_tree_elements$.getGlobalValue();
    }

    public static SubLObject recently_created_parse_tree_elements() {
        return $recently_created_parse_tree_elements$.getGlobalValue();
    }

    public static final SubLObject remembering_new_parse_tree_elements_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject body = current;
            return listS(CLET, $list_alt160, append(body, NIL));
        }
    }

    public static SubLObject remembering_new_parse_tree_elements(final SubLObject macroform, final SubLObject environment) {
        final SubLObject datum = macroform.rest();
        final SubLObject body;
        final SubLObject current = body = datum;
        return listS(CLET, $list156, append(body, NIL));
    }

    public static final SubLObject forget_recently_created_parse_tree_elements_alt() {
        $recently_created_parse_tree_elements$.setGlobalValue(NIL);
        return $recently_created_parse_tree_elements$.getGlobalValue();
    }

    public static SubLObject forget_recently_created_parse_tree_elements() {
        $recently_created_parse_tree_elements$.setGlobalValue(NIL);
        return $recently_created_parse_tree_elements$.getGlobalValue();
    }

    public static final SubLObject kill_recently_created_parse_tree_elements_alt() {
        while (NIL != $recently_created_parse_tree_elements$.getGlobalValue()) {
            ke.ke_kill($recently_created_parse_tree_elements$.getGlobalValue().first());
            $recently_created_parse_tree_elements$.setGlobalValue($recently_created_parse_tree_elements$.getGlobalValue().rest());
        } 
        return $recently_created_parse_tree_elements$.getGlobalValue();
    }

    public static SubLObject kill_recently_created_parse_tree_elements() {
        while (NIL != $recently_created_parse_tree_elements$.getGlobalValue()) {
            ke.ke_kill($recently_created_parse_tree_elements$.getGlobalValue().first());
            $recently_created_parse_tree_elements$.setGlobalValue($recently_created_parse_tree_elements$.getGlobalValue().rest());
        } 
        return $recently_created_parse_tree_elements$.getGlobalValue();
    }

    /**
     * Create and return a new instance of COL.
     */
    @LispMethod(comment = "Create and return a new instance of COL.")
    public static final SubLObject create_parse_tree_element_alt(SubLObject col, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject new_constant = rkf_term_utilities.create_new_constant(NIL != constant_p(col) ? ((SubLObject) (constants_high.constant_name(col))) : $$$ParseTreeElement, list(col), NIL, mt, name_prefix, NIL, T, T, T);
                if (NIL != $remember_new_parse_tree_elementsP$.getDynamicValue(thread)) {
                    $recently_created_parse_tree_elements$.setGlobalValue(cons(new_constant, $recently_created_parse_tree_elements$.getGlobalValue()));
                }
                return new_constant;
            }
        }
    }

    /**
     * Create and return a new instance of COL.
     */
    @LispMethod(comment = "Create and return a new instance of COL.")
    public static SubLObject create_parse_tree_element(final SubLObject col, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject new_constant = rkf_term_utilities.create_new_constant(NIL != constant_p(col) ? constants_high.constant_name(col) : $$$ParseTreeElement, list(col), NIL, mt, name_prefix, NIL, $UPCASE, T, T);
        if (NIL != $remember_new_parse_tree_elementsP$.getDynamicValue(thread)) {
            $recently_created_parse_tree_elements$.setGlobalValue(cons(new_constant, $recently_created_parse_tree_elements$.getGlobalValue()));
        }
        return new_constant;
    }/**
     * Create and return a new instance of COL.
     */


    /**
     * Hypothesize and return a new instance of COL.
     */
    @LispMethod(comment = "Hypothesize and return a new instance of COL.")
    public static final SubLObject hypothesize_parse_tree_element_alt(SubLObject col, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject var = $sym162$_INSTANCE;
                SubLObject instance = NIL;
                if (NIL != hlmt.hlmt_p(mt)) {
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
                        SubLObject _prev_bind_11 = fi.$fi_error$.currentBinding(thread);
                        SubLObject _prev_bind_12 = fi.$fi_warning$.currentBinding(thread);
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
                            fi.$fi_error$.bind(NIL, thread);
                            fi.$fi_warning$.bind(NIL, thread);
                            thread.resetMultipleValues();
                            {
                                SubLObject v_bindings = prove.fi_hypothesize_int(list($$isa, $sym162$_INSTANCE, col), mt, name_prefix, UNPROVIDED);
                                SubLObject failure_reasons = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                if (NIL != failure_reasons) {
                                    Errors.warn($str_alt163$Can_t_hypothesize_instance_of__S_, col, failure_reasons);
                                } else {
                                    instance = bindings.variable_lookup(var, v_bindings);
                                }
                            }
                        } finally {
                            fi.$fi_warning$.rebind(_prev_bind_12, thread);
                            fi.$fi_error$.rebind(_prev_bind_11, thread);
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
                }
                return instance;
            }
        }
    }

    /**
     * Hypothesize and return a new instance of COL.
     */
    @LispMethod(comment = "Hypothesize and return a new instance of COL.")
    public static SubLObject hypothesize_parse_tree_element(final SubLObject col, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject var = $sym159$_INSTANCE;
        SubLObject instance = NIL;
        if (NIL != hlmt.hlmt_p(mt)) {
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
            final SubLObject _prev_bind_12 = fi.$fi_error$.currentBinding(thread);
            final SubLObject _prev_bind_13 = fi.$fi_warning$.currentBinding(thread);
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
                fi.$fi_error$.bind(NIL, thread);
                fi.$fi_warning$.bind(NIL, thread);
                thread.resetMultipleValues();
                final SubLObject v_bindings = prove.fi_hypothesize_int(list($$isa, $sym159$_INSTANCE, col), mt, name_prefix, UNPROVIDED);
                final SubLObject failure_reasons = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL != failure_reasons) {
                    Errors.warn($str160$Can_t_hypothesize_instance_of__S_, col, failure_reasons);
                } else {
                    instance = bindings.variable_lookup(var, v_bindings);
                }
            } finally {
                fi.$fi_warning$.rebind(_prev_bind_13, thread);
                fi.$fi_error$.rebind(_prev_bind_12, thread);
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
        }
        return instance;
    }/**
     * Hypothesize and return a new instance of COL.
     */


    /**
     * Generate and return a new #$SyntacticChoiceNode
     */
    @LispMethod(comment = "Generate and return a new #$SyntacticChoiceNode")
    public static final SubLObject generate_choice_node_alt(SubLObject creator, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject node = NIL;
                {
                    SubLObject _prev_bind_0 = parsing_vars.$parse_tree_mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = parsing_vars.$parse_tree_prefix$.currentBinding(thread);
                    try {
                        parsing_vars.$parse_tree_mt$.bind(mt, thread);
                        parsing_vars.$parse_tree_prefix$.bind(name_prefix, thread);
                        node = create_parse_tree_element($$SyntacticChoiceNode, UNPROVIDED, UNPROVIDED);
                        assert_parse_tree_info(list($$syntacticNodeCreator, creator, node), UNPROVIDED);
                    } finally {
                        parsing_vars.$parse_tree_prefix$.rebind(_prev_bind_1, thread);
                        parsing_vars.$parse_tree_mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return node;
            }
        }
    }

    /**
     * Generate and return a new #$SyntacticChoiceNode
     */
    @LispMethod(comment = "Generate and return a new #$SyntacticChoiceNode")
    public static SubLObject generate_choice_node(final SubLObject creator, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject node = NIL;
        final SubLObject _prev_bind_0 = parsing_vars.$parse_tree_mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = parsing_vars.$parse_tree_prefix$.currentBinding(thread);
        try {
            parsing_vars.$parse_tree_mt$.bind(mt, thread);
            parsing_vars.$parse_tree_prefix$.bind(name_prefix, thread);
            node = create_parse_tree_element($$SyntacticChoiceNode, UNPROVIDED, UNPROVIDED);
            assert_parse_tree_info(list($$syntacticNodeCreator, creator, node), UNPROVIDED);
        } finally {
            parsing_vars.$parse_tree_prefix$.rebind(_prev_bind_2, thread);
            parsing_vars.$parse_tree_mt$.rebind(_prev_bind_0, thread);
        }
        return node;
    }/**
     * Generate and return a new #$SyntacticChoiceNode
     */


    /**
     * Add OPTION-NODE as a new option node for CHOICE-NODE.
     */
    @LispMethod(comment = "Add OPTION-NODE as a new option node for CHOICE-NODE.")
    public static final SubLObject choice_node_add_option_node_alt(SubLObject choice_node, SubLObject option_node, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list($$optionNodes, choice_node, option_node), mt);
        return choice_node;
    }

    /**
     * Add OPTION-NODE as a new option node for CHOICE-NODE.
     */
    @LispMethod(comment = "Add OPTION-NODE as a new option node for CHOICE-NODE.")
    public static SubLObject choice_node_add_option_node(final SubLObject choice_node, final SubLObject option_node, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list($$optionNodes, choice_node, option_node), mt);
        return choice_node;
    }/**
     * Add OPTION-NODE as a new option node for CHOICE-NODE.
     */


    public static final SubLObject create_parse_tree_alt(SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        {
            SubLObject tree = create_parse_tree_element($$CycParseTree, mt, name_prefix);
            return tree;
        }
    }

    public static SubLObject create_parse_tree(SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        final SubLObject tree = create_parse_tree_element($$CycParseTree, mt, name_prefix);
        return tree;
    }

    public static final SubLObject assert_parse_tree_root_node_alt(SubLObject tree, SubLObject root_node, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list($$syntacticRootOfParseTree, tree, root_node), mt);
        return tree;
    }

    public static SubLObject assert_parse_tree_root_node(final SubLObject tree, final SubLObject root_node, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        assert_parse_tree_info(list($$syntacticRootOfParseTree, tree, root_node), mt);
        return tree;
    }

    public static final SubLObject create_parse_tree_with_root_node_alt(SubLObject root_node, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        {
            SubLObject tree = create_parse_tree(mt, name_prefix);
            assert_parse_tree_root_node(tree, root_node, mt);
            return tree;
        }
    }

    public static SubLObject create_parse_tree_with_root_node(final SubLObject root_node, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        final SubLObject tree = create_parse_tree(mt, name_prefix);
        assert_parse_tree_root_node(tree, root_node, mt);
        return tree;
    }

    /**
     *
     *
     * @return FORT-P; The mt in which the structure of PARSE-TREE should be asserted.
     */
    @LispMethod(comment = "@return FORT-P; The mt in which the structure of PARSE-TREE should be asserted.")
    public static final SubLObject content_mt_of_parse_tree_alt(SubLObject v_parse_tree) {
        return czer_main.cyc_find_or_create_nart(list($$ContentMtOfParseTreeFn, v_parse_tree), UNPROVIDED);
    }

    /**
     *
     *
     * @return FORT-P; The mt in which the structure of PARSE-TREE should be asserted.
     */
    @LispMethod(comment = "@return FORT-P; The mt in which the structure of PARSE-TREE should be asserted.")
    public static SubLObject content_mt_of_parse_tree(final SubLObject v_parse_tree) {
        return czer_main.cyc_find_or_create_nart(list($$ContentMtOfParseTreeFn, v_parse_tree), UNPROVIDED);
    }

    /**
     *
     *
     * @param TOKENIZATION;
     * 		instance of #$CycTokenization.
     * @param CREATOR;
     * 		instance of #$SyntacticInterpretationAgent.
     * @param RULE;
     * 		instance of #$PhraseStructureRule or NIL.
     * @return SYNTACTIC-NODE-P; #$SyntacticNode.
     */
    @LispMethod(comment = "@param TOKENIZATION;\r\n\t\tinstance of #$CycTokenization.\r\n@param CREATOR;\r\n\t\tinstance of #$SyntacticInterpretationAgent.\r\n@param RULE;\r\n\t\tinstance of #$PhraseStructureRule or NIL.\r\n@return SYNTACTIC-NODE-P; #$SyntacticNode.")
    public static final SubLObject create_syntactic_node_alt(SubLObject tokenization, SubLObject creator, SubLObject category, SubLObject span, SubLObject lexical_nodeP, SubLObject string, SubLObject rule, SubLObject mt, SubLObject name_prefix) {
        if (rule == UNPROVIDED) {
            rule = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        if (NIL != lexical_nodeP) {
            return create_lexical_node(tokenization, creator, category, span, string, mt, name_prefix);
        } else {
            return create_phrasal_node(tokenization, creator, category, span, rule, string, mt, name_prefix);
        }
    }

    /**
     *
     *
     * @param TOKENIZATION;
     * 		instance of #$CycTokenization.
     * @param CREATOR;
     * 		instance of #$SyntacticInterpretationAgent.
     * @param RULE;
     * 		instance of #$PhraseStructureRule or NIL.
     * @return SYNTACTIC-NODE-P; #$SyntacticNode.
     */
    @LispMethod(comment = "@param TOKENIZATION;\r\n\t\tinstance of #$CycTokenization.\r\n@param CREATOR;\r\n\t\tinstance of #$SyntacticInterpretationAgent.\r\n@param RULE;\r\n\t\tinstance of #$PhraseStructureRule or NIL.\r\n@return SYNTACTIC-NODE-P; #$SyntacticNode.")
    public static SubLObject create_syntactic_node(final SubLObject tokenization, final SubLObject creator, final SubLObject category, final SubLObject span, final SubLObject lexical_nodeP, final SubLObject string, SubLObject rule, SubLObject mt, SubLObject name_prefix) {
        if (rule == UNPROVIDED) {
            rule = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        if (NIL != lexical_nodeP) {
            return create_lexical_node(tokenization, creator, category, span, string, mt, name_prefix);
        }
        return create_phrasal_node(tokenization, creator, category, span, rule, string, mt, name_prefix);
    }

    public static final SubLObject create_phrasal_node_alt(SubLObject tokenization, SubLObject creator, SubLObject category, SubLObject span, SubLObject rule, SubLObject string, SubLObject mt, SubLObject name_prefix) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject node = NIL;
                {
                    SubLObject _prev_bind_0 = parsing_vars.$parse_tree_mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = parsing_vars.$parse_tree_prefix$.currentBinding(thread);
                    try {
                        parsing_vars.$parse_tree_mt$.bind(mt, thread);
                        parsing_vars.$parse_tree_prefix$.bind(name_prefix, thread);
                        node = create_parse_tree_element($$SyntacticNode, UNPROVIDED, UNPROVIDED);
                        if (NIL != creator) {
                            assert_parse_tree_info(list($$syntacticNodeCreator, creator, node), UNPROVIDED);
                        }
                        if (NIL != category) {
                            assert_parse_tree_info(list($$syntacticNodeCategory, node, category), UNPROVIDED);
                        }
                        if (NIL != string) {
                            assert_parse_tree_info(list($$syntacticNodeString, node, string), UNPROVIDED);
                        }
                        if (NIL != forts.fort_p(rule)) {
                            assert_parse_tree_info(list($$syntacticNodePhraseStructureRule, node, rule), UNPROVIDED);
                        }
                        if (interval_span.interval_span_length(span).eql(ONE_INTEGER)) {
                            {
                                SubLObject token_num = interval_span.interval_span_start(span);
                                thread.resetMultipleValues();
                                {
                                    SubLObject token = find_or_create_parse_token(tokenization, token_num, UNPROVIDED, UNPROVIDED);
                                    SubLObject newP = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    if (NIL != newP) {
                                        assert_parse_tree_info(list($$tokenString, token, string), UNPROVIDED);
                                        {
                                            SubLObject start_offset = compute_parse_token_start_offset(tokenization, token_num, string);
                                            if (NIL != start_offset) {
                                                assert_parse_tree_info(list($$tokenStartCharacterOffset, token, start_offset), UNPROVIDED);
                                            }
                                        }
                                    } else {
                                        if (NIL != string) {
                                            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                                if (!token_string(token).equal(string)) {
                                                    Errors.error($str_alt174$Token_node_mismatch__Token___S_st, token, token_string(token), string);
                                                }
                                            }
                                        }
                                    }
                                    assert_parse_tree_info(list($$syntacticNodeCoversToken, node, token), UNPROVIDED);
                                }
                            }
                        } else {
                            {
                                SubLObject start = interval_span.interval_span_start(span);
                                SubLObject end = number_utilities.f_1_(interval_span.interval_span_end(span));
                                SubLObject token_set = list($$CycParsingTokenContiguousSetFn, start, end, tokenization);
                                assert_parse_tree_info(list($$syntacticNodeCoversTokenSet, node, token_set), UNPROVIDED);
                            }
                        }
                    } finally {
                        parsing_vars.$parse_tree_prefix$.rebind(_prev_bind_1, thread);
                        parsing_vars.$parse_tree_mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return node;
            }
        }
    }

    public static SubLObject create_phrasal_node(final SubLObject tokenization, final SubLObject creator, final SubLObject category, final SubLObject span, final SubLObject rule, final SubLObject string, final SubLObject mt, final SubLObject name_prefix) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject node = NIL;
        final SubLObject _prev_bind_0 = parsing_vars.$parse_tree_mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = parsing_vars.$parse_tree_prefix$.currentBinding(thread);
        try {
            parsing_vars.$parse_tree_mt$.bind(mt, thread);
            parsing_vars.$parse_tree_prefix$.bind(name_prefix, thread);
            node = create_parse_tree_element($$SyntacticNode, UNPROVIDED, UNPROVIDED);
            if (NIL != creator) {
                assert_parse_tree_info(list($$syntacticNodeCreator, creator, node), UNPROVIDED);
            }
            if (NIL != category) {
                assert_parse_tree_info(list($$syntacticNodeCategory, node, category), UNPROVIDED);
            }
            if (NIL != string) {
                assert_parse_tree_info(list($$syntacticNodeString, node, string), UNPROVIDED);
            }
            if (NIL != forts.fort_p(rule)) {
                assert_parse_tree_info(list($$syntacticNodePhraseStructureRule, node, rule), UNPROVIDED);
            }
            if (interval_span.interval_span_length(span).eql(ONE_INTEGER)) {
                final SubLObject token_num = interval_span.interval_span_start(span);
                thread.resetMultipleValues();
                final SubLObject token = find_or_create_parse_token(tokenization, token_num, UNPROVIDED, UNPROVIDED);
                final SubLObject newP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL != newP) {
                    assert_parse_tree_info(list($$tokenString, token, string), UNPROVIDED);
                    final SubLObject start_offset = compute_parse_token_start_offset(tokenization, token_num, string);
                    if (NIL != start_offset) {
                        assert_parse_tree_info(list($$tokenStartCharacterOffset, token, start_offset), UNPROVIDED);
                    }
                } else
                    if (((NIL != string) && (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread))) && (!token_string(token).equal(string))) {
                        Errors.error($str171$Token_node_mismatch__Token___S_st, token, token_string(token), string);
                    }

                assert_parse_tree_info(list($$syntacticNodeCoversToken, node, token), UNPROVIDED);
            } else {
                final SubLObject start = interval_span.interval_span_start(span);
                final SubLObject end = number_utilities.f_1_(interval_span.interval_span_end(span));
                final SubLObject token_set = list($$CycParsingTokenContiguousSetFn, start, end, tokenization);
                assert_parse_tree_info(list($$syntacticNodeCoversTokenSet, node, token_set), UNPROVIDED);
            }
        } finally {
            parsing_vars.$parse_tree_prefix$.rebind(_prev_bind_2, thread);
            parsing_vars.$parse_tree_mt$.rebind(_prev_bind_0, thread);
        }
        return node;
    }

    public static final SubLObject assert_start_offset_for_token_alt(SubLObject token, SubLObject tokenization, SubLObject token_num, SubLObject string) {
        if (tokenization == UNPROVIDED) {
            tokenization = token_tokenization(token);
        }
        if (token_num == UNPROVIDED) {
            token_num = token_number(token);
        }
        if (string == UNPROVIDED) {
            string = token_string(token);
        }
        {
            SubLObject start_offset = compute_parse_token_start_offset(tokenization, token_num, string);
            if (NIL != start_offset) {
                assert_parse_tree_info(list($$tokenStartCharacterOffset, token, start_offset), UNPROVIDED);
            }
        }
        return token;
    }

    public static SubLObject assert_start_offset_for_token(final SubLObject token, SubLObject tokenization, SubLObject token_num, SubLObject string) {
        if (tokenization == UNPROVIDED) {
            tokenization = token_tokenization(token);
        }
        if (token_num == UNPROVIDED) {
            token_num = token_number(token);
        }
        if (string == UNPROVIDED) {
            string = token_string(token);
        }
        final SubLObject start_offset = compute_parse_token_start_offset(tokenization, token_num, string);
        if (NIL != start_offset) {
            assert_parse_tree_info(list($$tokenStartCharacterOffset, token, start_offset), UNPROVIDED);
        }
        return token;
    }

    public static final SubLObject syntactic_node_string_alt(SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeString, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject syntactic_node_string(final SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeString, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject syntactic_node_start_offset_alt(SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeStartCharacterOffset, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject syntactic_node_start_offset(final SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeStartCharacterOffset, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject syntactic_node_category_alt(SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeCategory, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject syntactic_node_category(final SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeCategory, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject syntactic_node_agreement_pred_alt(SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeAgreementPredicate, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject syntactic_node_agreement_pred(final SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeAgreementPredicate, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject syntactic_node_tree_alt(SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$nodeInSystem, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject syntactic_node_tree(final SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$nodeInSystem, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject syntactic_node_penntag_alt(SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodePennTag, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject syntactic_node_penntag(final SubLObject syntactic_node) {
        return NIL != indexed_term_p(syntactic_node) ? kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodePennTag, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject parse_tree_content_mt_alt(SubLObject v_parse_tree) {
        return NIL != indexed_term_p(v_parse_tree) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(v_parse_tree, $$contentMtOfParseTree, TWO_INTEGER, ONE_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject parse_tree_content_mt(final SubLObject v_parse_tree) {
        return NIL != indexed_term_p(v_parse_tree) ? kb_mapping_utilities.fpred_value_in_any_mt(v_parse_tree, $$contentMtOfParseTree, TWO_INTEGER, ONE_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject syntactic_node_content_mt_alt(SubLObject syntactic_node) {
        {
            SubLObject tree = syntactic_node_tree(syntactic_node);
            return parse_tree_content_mt(tree);
        }
    }

    public static SubLObject syntactic_node_content_mt(final SubLObject syntactic_node) {
        final SubLObject tree = syntactic_node_tree(syntactic_node);
        return parse_tree_content_mt(tree);
    }

    public static final SubLObject syntactic_node_span_alt(SubLObject syntactic_node, SubLObject tokenization) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject token = backward.removal_ask_variable($TOKEN, listS($$syntacticNodeCoversToken, syntactic_node, $list_alt184), $$InferencePSC, UNPROVIDED, UNPROVIDED).first();
                SubLObject token_set = kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeCoversTokenSet, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                if ((NIL != token) && token_tokenization(token).eql(tokenization)) {
                    {
                        SubLObject token_num = token_number(token);
                        if (NIL != token_num) {
                            return interval_span.get_interval_span(token_num, number_utilities.f_1X(token_num));
                        }
                    }
                }
                if (NIL != token_set) {
                    {
                        SubLObject pattern = list($$CycParsingTokenContiguousSetFn, $list_alt185, $list_alt186, tokenization);
                        thread.resetMultipleValues();
                        {
                            SubLObject success = formula_pattern_match.formula_matches_pattern(token_set, pattern);
                            SubLObject v_bindings = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if (NIL != success) {
                                {
                                    SubLObject last_token_num = list_utilities.alist_lookup_without_values(v_bindings, LAST_TOKEN_NUM, UNPROVIDED, UNPROVIDED);
                                    SubLObject first_token_num = list_utilities.alist_lookup_without_values(v_bindings, FIRST_TOKEN_NUM, UNPROVIDED, UNPROVIDED);
                                    SubLObject start = first_token_num;
                                    SubLObject end = number_utilities.f_1X(last_token_num);
                                    return interval_span.get_interval_span(start, end);
                                }
                            }
                        }
                    }
                }
                Errors.sublisp_break($str_alt189$Couldn_t_get_interval_span_for__S, new SubLObject[]{ syntactic_node, tokenization });
            }
            return NIL;
        }
    }

    public static SubLObject syntactic_node_span(final SubLObject syntactic_node, final SubLObject tokenization) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject token = backward.removal_ask_variable($TOKEN, listS($$syntacticNodeCoversToken, syntactic_node, $list181), $$InferencePSC, UNPROVIDED, UNPROVIDED).first();
        final SubLObject token_set = kb_mapping_utilities.fpred_value_in_any_mt(syntactic_node, $$syntacticNodeCoversTokenSet, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        if ((NIL != token) && token_tokenization(token).eql(tokenization)) {
            final SubLObject token_num = token_number(token);
            if (NIL != token_num) {
                return interval_span.get_interval_span(token_num, number_utilities.f_1X(token_num));
            }
        }
        if (NIL != token_set) {
            final SubLObject pattern = list($$CycParsingTokenContiguousSetFn, $list182, $list183, tokenization);
            thread.resetMultipleValues();
            final SubLObject success = formula_pattern_match.formula_matches_pattern(token_set, pattern);
            final SubLObject v_bindings = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != success) {
                final SubLObject last_token_num = list_utilities.alist_lookup_without_values(v_bindings, LAST_TOKEN_NUM, UNPROVIDED, UNPROVIDED);
                final SubLObject start;
                final SubLObject first_token_num = start = list_utilities.alist_lookup_without_values(v_bindings, FIRST_TOKEN_NUM, UNPROVIDED, UNPROVIDED);
                final SubLObject end = number_utilities.f_1X(last_token_num);
                return interval_span.get_interval_span(start, end);
            }
        }
        Errors.sublisp_break($str186$Couldn_t_get_interval_span_for__S, new SubLObject[]{ syntactic_node, tokenization });
        return NIL;
    }

    public static final SubLObject create_lexical_node_alt(SubLObject tokenization, SubLObject creator, SubLObject category, SubLObject span, SubLObject string, SubLObject mt, SubLObject name_prefix) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject node = NIL;
                {
                    SubLObject _prev_bind_0 = parsing_vars.$parse_tree_mt$.currentBinding(thread);
                    try {
                        parsing_vars.$parse_tree_mt$.bind(mt, thread);
                        {
                            SubLObject _prev_bind_0_76 = parsing_vars.$parse_tree_prefix$.currentBinding(thread);
                            try {
                                parsing_vars.$parse_tree_prefix$.bind(name_prefix, thread);
                                node = create_parse_tree_element($$LexicalNode, UNPROVIDED, UNPROVIDED);
                            } finally {
                                parsing_vars.$parse_tree_prefix$.rebind(_prev_bind_0_76, thread);
                            }
                        }
                        if (NIL != creator) {
                            assert_parse_tree_info(list($$lexicalNodeCreator, creator, node), UNPROVIDED);
                        }
                        if (NIL != category) {
                            assert_parse_tree_info(list($$syntacticNodeCategory, node, category), UNPROVIDED);
                        }
                        if (NIL != string) {
                            assert_parse_tree_info(list($$syntacticNodeString, node, string), UNPROVIDED);
                        }
                        if (interval_span.interval_span_length(span).eql(ONE_INTEGER)) {
                            {
                                SubLObject token_num = interval_span.interval_span_start(span);
                                SubLObject token = find_or_create_parse_token(tokenization, token_num, UNPROVIDED, UNPROVIDED);
                                assert_parse_tree_info(list($$lexicalNodeCoversToken, node, token), UNPROVIDED);
                                if (!((NIL == string) || string.equal(token_string(token)))) {
                                    assert_parse_tree_info(list($$tokenString, token, string), UNPROVIDED);
                                    {
                                        SubLObject start_offset = compute_parse_token_start_offset(tokenization, token_num, string);
                                        if (NIL != start_offset) {
                                            assert_parse_tree_info(list($$tokenStartCharacterOffset, token, start_offset), UNPROVIDED);
                                        }
                                    }
                                }
                            }
                        } else {
                            {
                                SubLObject start = interval_span.interval_span_start(span);
                                SubLObject end = number_utilities.f_1_(interval_span.interval_span_end(span));
                                SubLObject token_set = list($$CycParsingTokenContiguousSetFn, start, end, tokenization);
                                assert_parse_tree_info(list($$lexicalNodeCoversTokenSet, node, token_set), UNPROVIDED);
                            }
                        }
                    } finally {
                        parsing_vars.$parse_tree_mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return node;
            }
        }
    }

    public static SubLObject create_lexical_node(final SubLObject tokenization, final SubLObject creator, final SubLObject category, final SubLObject span, final SubLObject string, final SubLObject mt, final SubLObject name_prefix) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject node = NIL;
        final SubLObject _prev_bind_0 = parsing_vars.$parse_tree_mt$.currentBinding(thread);
        try {
            parsing_vars.$parse_tree_mt$.bind(mt, thread);
            final SubLObject _prev_bind_0_$72 = parsing_vars.$parse_tree_prefix$.currentBinding(thread);
            try {
                parsing_vars.$parse_tree_prefix$.bind(name_prefix, thread);
                node = create_parse_tree_element($$LexicalNode, UNPROVIDED, UNPROVIDED);
            } finally {
                parsing_vars.$parse_tree_prefix$.rebind(_prev_bind_0_$72, thread);
            }
            if (NIL != creator) {
                assert_parse_tree_info(list($$lexicalNodeCreator, creator, node), UNPROVIDED);
            }
            if (NIL != category) {
                assert_parse_tree_info(list($$syntacticNodeCategory, node, category), UNPROVIDED);
            }
            if (NIL != string) {
                assert_parse_tree_info(list($$syntacticNodeString, node, string), UNPROVIDED);
            }
            if (interval_span.interval_span_length(span).eql(ONE_INTEGER)) {
                final SubLObject token_num = interval_span.interval_span_start(span);
                final SubLObject token = find_or_create_parse_token(tokenization, token_num, UNPROVIDED, UNPROVIDED);
                assert_parse_tree_info(list($$lexicalNodeCoversToken, node, token), UNPROVIDED);
                if ((NIL != string) && (!string.equal(token_string(token)))) {
                    assert_parse_tree_info(list($$tokenString, token, string), UNPROVIDED);
                    final SubLObject start_offset = compute_parse_token_start_offset(tokenization, token_num, string);
                    if (NIL != start_offset) {
                        assert_parse_tree_info(list($$tokenStartCharacterOffset, token, start_offset), UNPROVIDED);
                    }
                }
            } else {
                final SubLObject start = interval_span.interval_span_start(span);
                final SubLObject end = number_utilities.f_1_(interval_span.interval_span_end(span));
                final SubLObject token_set = list($$CycParsingTokenContiguousSetFn, start, end, tokenization);
                assert_parse_tree_info(list($$lexicalNodeCoversTokenSet, node, token_set), UNPROVIDED);
            }
        } finally {
            parsing_vars.$parse_tree_mt$.rebind(_prev_bind_0, thread);
        }
        return node;
    }

    /**
     * Add DTR-NODE as the DTR-NUMth daughter of NODE in MT. If DTR-NUM is nil, use highest known DTR-NUM plus 1.
     */
    @LispMethod(comment = "Add DTR-NODE as the DTR-NUMth daughter of NODE in MT. If DTR-NUM is nil, use highest known DTR-NUM plus 1.")
    public static final SubLObject syntactic_node_add_dtr_alt(SubLObject node, SubLObject dtr_node, SubLObject dtr_num, SubLObject mt) {
        if (dtr_num == UNPROVIDED) {
            dtr_num = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (NIL == dtr_num) {
            dtr_num = ZERO_INTEGER;
            {
                SubLObject cdolist_list_var = ask_utilities.ask_variable($sym193$_N, listS($$syntacticNodeNthDaughter, node, $list_alt194), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                SubLObject n = NIL;
                for (n = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , n = cdolist_list_var.first()) {
                    dtr_num = max(dtr_num, n);
                }
            }
            dtr_num = add(dtr_num, ONE_INTEGER);
        }
        assert_parse_tree_info(list($$syntacticNodeNthDaughter, node, dtr_num, dtr_node), mt);
        if (NIL == isa.isaP(dtr_node, $$SyntacticChoiceNode, mt, UNPROVIDED)) {
            assert_parse_tree_info(list($$syntacticNodeNthContributor, node, dtr_num, dtr_node), mt);
        }
        return node;
    }

    @LispMethod(comment = "Add DTR-NODE as the DTR-NUMth daughter of NODE in MT. If DTR-NUM is nil, use highest known DTR-NUM plus 1.")
    public static SubLObject syntactic_node_add_dtr(final SubLObject node, final SubLObject dtr_node, SubLObject dtr_num, SubLObject mt) {
        if (dtr_num == UNPROVIDED) {
            dtr_num = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        if (NIL == dtr_num) {
            dtr_num = ZERO_INTEGER;
            SubLObject cdolist_list_var = ask_utilities.ask_variable($sym190$_N, listS($$syntacticNodeNthDaughter, node, $list191), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject n = NIL;
            n = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                dtr_num = max(dtr_num, n);
                cdolist_list_var = cdolist_list_var.rest();
                n = cdolist_list_var.first();
            } 
            dtr_num = add(dtr_num, ONE_INTEGER);
        }
        assert_parse_tree_info(list($$syntacticNodeNthDaughter, node, dtr_num, dtr_node), mt);
        if (NIL == isa.isaP(dtr_node, $$SyntacticChoiceNode, mt, UNPROVIDED)) {
            assert_parse_tree_info(list($$syntacticNodeNthContributor, node, dtr_num, dtr_node), mt);
        }
        return node;
    }/**
     * Add DTR-NODE as the DTR-NUMth daughter of NODE in MT. If DTR-NUM is nil, use highest known DTR-NUM plus 1.
     */


    /**
     * Add DTR-NODE as the daughter of NODE. @see syntactic-node-add-dtr
     */
    @LispMethod(comment = "Add DTR-NODE as the daughter of NODE. @see syntactic-node-add-dtr")
    public static final SubLObject syntactic_node_add_head_dtr_alt(SubLObject node, SubLObject dtr_node, SubLObject dtr_num, SubLObject mt) {
        if (dtr_num == UNPROVIDED) {
            dtr_num = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        syntactic_node_add_dtr(node, dtr_node, dtr_num, mt);
        assert_parse_tree_info(list($$syntacticNodeHeadDaughter, node, dtr_node), mt);
        return node;
    }

    @LispMethod(comment = "Add DTR-NODE as the daughter of NODE. @see syntactic-node-add-dtr")
    public static SubLObject syntactic_node_add_head_dtr(final SubLObject node, final SubLObject dtr_node, SubLObject dtr_num, SubLObject mt) {
        if (dtr_num == UNPROVIDED) {
            dtr_num = NIL;
        }
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$parse_tree_mt$.getDynamicValue();
        }
        syntactic_node_add_dtr(node, dtr_node, dtr_num, mt);
        assert_parse_tree_info(list($$syntacticNodeHeadDaughter, node, dtr_node), mt);
        return node;
    }/**
     * Add DTR-NODE as the daughter of NODE. @see syntactic-node-add-dtr
     */


    /**
     * Convert MEANING into something suitable to be asserted as a proposed meaning for a #$SyntacticNode.
     */
    @LispMethod(comment = "Convert MEANING into something suitable to be asserted as a proposed meaning for a #$SyntacticNode.")
    public static final SubLObject package_node_meaning_alt(SubLObject meaning) {
        return make_unary_formula($$SubLQuoteFn, meaning);
    }

    @LispMethod(comment = "Convert MEANING into something suitable to be asserted as a proposed meaning for a #$SyntacticNode.")
    public static SubLObject package_node_meaning(final SubLObject meaning) {
        return make_unary_formula($$SubLQuoteFn, meaning);
    }/**
     * Convert MEANING into something suitable to be asserted as a proposed meaning for a #$SyntacticNode.
     */


    /**
     *
     *
     * @return LISTP; a nested list representing the tree structure of NODE and its dtrs, etc.
     */
    @LispMethod(comment = "@return LISTP; a nested list representing the tree structure of NODE and its dtrs, etc.")
    public static final SubLObject syntactic_node_to_nested_list_alt(SubLObject node) {
        {
            SubLObject dtrs = syntactic_node_dtrs(node);
            return NIL != dtrs ? ((SubLObject) (cons(node, Mapping.mapcar(SYNTACTIC_NODE_TO_NESTED_LIST, dtrs)))) : node;
        }
    }

    /**
     *
     *
     * @return LISTP; a nested list representing the tree structure of NODE and its dtrs, etc.
     */
    @LispMethod(comment = "@return LISTP; a nested list representing the tree structure of NODE and its dtrs, etc.")
    public static SubLObject syntactic_node_to_nested_list(final SubLObject node) {
        final SubLObject dtrs = syntactic_node_dtrs(node);
        return NIL != dtrs ? cons(node, Mapping.mapcar(SYNTACTIC_NODE_TO_NESTED_LIST, dtrs)) : node;
    }

    /**
     *
     *
     * @return STRINGP of mother of DTRS
     */
    @LispMethod(comment = "@return STRINGP of mother of DTRS")
    public static final SubLObject phrasal_node_string_from_dtrs_alt(SubLObject dtrs) {
        {
            SubLObject string = $str_alt199$;
            SubLObject cdolist_list_var = dtrs;
            SubLObject node = NIL;
            for (node = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , node = cdolist_list_var.first()) {
                {
                    SubLObject token_string = syntactic_node_string(node);
                    if (!token_string.isString()) {
                        Errors.error($str_alt200$Couldn_t_find_string_for__S, node);
                    }
                    string = (NIL != string_utilities.empty_string_p(string)) ? ((SubLObject) (token_string)) : cconcatenate(string, new SubLObject[]{ $str_alt201$_, token_string });
                }
            }
            return string;
        }
    }

    /**
     *
     *
     * @return STRINGP of mother of DTRS
     */
    @LispMethod(comment = "@return STRINGP of mother of DTRS")
    public static SubLObject phrasal_node_string_from_dtrs(final SubLObject dtrs) {
        SubLObject string = $str196$;
        SubLObject cdolist_list_var = dtrs;
        SubLObject node = NIL;
        node = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject token_string = syntactic_node_string(node);
            if (!token_string.isString()) {
                Errors.error($str197$Couldn_t_find_string_for__S, node);
            }
            string = (NIL != string_utilities.empty_string_p(string)) ? token_string : cconcatenate(string, new SubLObject[]{ $$$_, token_string });
            cdolist_list_var = cdolist_list_var.rest();
            node = cdolist_list_var.first();
        } 
        return string;
    }

    /**
     *
     *
     * @return INTERVAL-SPAN-P from start of first of DTRS to end of last.
     */
    @LispMethod(comment = "@return INTERVAL-SPAN-P from start of first of DTRS to end of last.")
    public static final SubLObject phrasal_node_span_from_dtrs_alt(SubLObject dtrs, SubLObject tokenization) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject start = syntactic_node_span_start(dtrs.first(), tokenization);
                SubLObject end = start;
                SubLObject cdolist_list_var = dtrs;
                SubLObject dtr = NIL;
                for (dtr = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , dtr = cdolist_list_var.first()) {
                    if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                        if (!end.eql(syntactic_node_span_start(dtr, tokenization))) {
                            Errors.error($str_alt202$Bad_dtrs___S, dtrs);
                        }
                    }
                    end = syntactic_node_span_end(dtr, tokenization);
                }
                return interval_span.get_interval_span(start, end);
            }
        }
    }

    /**
     *
     *
     * @return INTERVAL-SPAN-P from start of first of DTRS to end of last.
     */
    @LispMethod(comment = "@return INTERVAL-SPAN-P from start of first of DTRS to end of last.")
    public static SubLObject phrasal_node_span_from_dtrs(final SubLObject dtrs, final SubLObject tokenization) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject end;
        final SubLObject start = end = syntactic_node_span_start(dtrs.first(), tokenization);
        SubLObject cdolist_list_var = dtrs;
        SubLObject dtr = NIL;
        dtr = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (!end.eql(syntactic_node_span_start(dtr, tokenization)))) {
                Errors.error($str199$Bad_dtrs___S, dtrs);
            }
            end = syntactic_node_span_end(dtr, tokenization);
            cdolist_list_var = cdolist_list_var.rest();
            dtr = cdolist_list_var.first();
        } 
        return interval_span.get_interval_span(start, end);
    }

    public static final SubLObject syntactic_node_span_start_alt(SubLObject node, SubLObject tokenization) {
        {
            SubLObject span = syntactic_node_span(node, tokenization);
            SubLTrampolineFile.checkType(span, INTERVAL_SPAN_P);
            return interval_span.interval_span_start(span);
        }
    }

    public static SubLObject syntactic_node_span_start(final SubLObject node, final SubLObject tokenization) {
        final SubLObject span = syntactic_node_span(node, tokenization);
        assert NIL != interval_span.interval_span_p(span) : "! interval_span.interval_span_p(span) " + ("interval_span.interval_span_p(span) " + "CommonSymbols.NIL != interval_span.interval_span_p(span) ") + span;
        return interval_span.interval_span_start(span);
    }

    public static final SubLObject syntactic_node_span_end_alt(SubLObject node, SubLObject tokenization) {
        {
            SubLObject span = syntactic_node_span(node, tokenization);
            SubLTrampolineFile.checkType(span, INTERVAL_SPAN_P);
            return interval_span.interval_span_end(span);
        }
    }

    public static SubLObject syntactic_node_span_end(final SubLObject node, final SubLObject tokenization) {
        final SubLObject span = syntactic_node_span(node, tokenization);
        assert NIL != interval_span.interval_span_p(span) : "! interval_span.interval_span_p(span) " + ("interval_span.interval_span_p(span) " + "CommonSymbols.NIL != interval_span.interval_span_p(span) ") + span;
        return interval_span.interval_span_end(span);
    }

    public static final SubLObject initialize_penntag_phrase_categories_alt() {
        {
            SubLObject ans = ask_utilities.query_template($list_alt207, $list_alt208, $$InferencePSC, $list_alt209);
            $penntag_phrase_categories$.setGlobalValue(ans);
            return $penntag_phrase_categories$.getGlobalValue();
        }
    }

    public static SubLObject initialize_penntag_phrase_categories() {
        final SubLObject ans = ask_utilities.query_template($list204, $list205, $$InferencePSC, $list206);
        $penntag_phrase_categories$.setGlobalValue(ans);
        return $penntag_phrase_categories$.getGlobalValue();
    }

    public static final SubLObject penntag_phrase_categories_alt() {
        if (NIL == list_utilities.alist_p($penntag_phrase_categories$.getGlobalValue())) {
            initialize_penntag_phrase_categories();
        }
        return $penntag_phrase_categories$.getGlobalValue();
    }

    public static SubLObject penntag_phrase_categories() {
        if (NIL == list_utilities.alist_p($penntag_phrase_categories$.getGlobalValue())) {
            initialize_penntag_phrase_categories();
        }
        return $penntag_phrase_categories$.getGlobalValue();
    }

    public static final SubLObject cycl_penntag_p_alt(SubLObject v_object) {
        return list_utilities.alist_has_keyP(penntag_phrase_categories(), v_object, symbol_function(EQL));
    }

    public static SubLObject cycl_penntag_p(final SubLObject v_object) {
        return list_utilities.alist_has_keyP(penntag_phrase_categories(), v_object, symbol_function(EQL));
    }

    public static final SubLObject penntag_category_alt(SubLObject penntag) {
        SubLTrampolineFile.checkType(penntag, FORT_P);
        return list_utilities.alist_lookup_without_values(penntag_phrase_categories(), penntag, symbol_function(EQL), NIL);
    }

    public static SubLObject penntag_category(final SubLObject penntag) {
        assert NIL != forts.fort_p(penntag) : "! forts.fort_p(penntag) " + ("forts.fort_p(penntag) " + "CommonSymbols.NIL != forts.fort_p(penntag) ") + penntag;
        return list_utilities.alist_lookup_without_values(penntag_phrase_categories(), penntag, symbol_function(EQL), NIL);
    }

    public static final SubLObject denots_of_stringXpenntag_alt(SubLObject string, SubLObject penntag) {
        {
            SubLObject category = penntag_category(penntag);
            return NIL != lexicon_accessors.speech_partP(category, UNPROVIDED) ? ((SubLObject) (lexicon_accessors.denots_of_stringXspeech_part(string, category, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) : NIL;
        }
    }

    public static SubLObject denots_of_stringXpenntag(final SubLObject string, final SubLObject penntag) {
        final SubLObject category = penntag_category(penntag);
        return NIL != lexicon_accessors.speech_partP(category, UNPROVIDED) ? lexicon_accessors.denots_of_stringXspeech_part(string, category, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED) : NIL;
    }

    public static final SubLObject initialize_penntag_forts_alt() {
        {
            SubLObject ans = ask_utilities.query_template($list_alt212, $list_alt213, $$InferencePSC, $list_alt209);
            $penntag_forts$.setGlobalValue(ans);
            return $penntag_forts$.getGlobalValue();
        }
    }

    public static SubLObject initialize_penntag_forts() {
        final SubLObject ans = ask_utilities.query_template($list209, $list210, $$InferencePSC, $list206);
        $penntag_forts$.setGlobalValue(ans);
        return $penntag_forts$.getGlobalValue();
    }

    public static final SubLObject penntag_forts_alt() {
        if (NIL == list_utilities.alist_p($penntag_forts$.getGlobalValue())) {
            initialize_penntag_forts();
        }
        return $penntag_forts$.getGlobalValue();
    }

    public static SubLObject penntag_forts() {
        if (NIL == list_utilities.alist_p($penntag_forts$.getGlobalValue())) {
            initialize_penntag_forts();
        }
        return $penntag_forts$.getGlobalValue();
    }

    public static final SubLObject penntag_to_cycl_alt(SubLObject penntag) {
        SubLTrampolineFile.checkType(penntag, STRINGP);
        return list_utilities.alist_lookup_without_values(penntag_forts(), penntag, symbol_function(EQUALP), NIL);
    }

    public static SubLObject penntag_to_cycl(final SubLObject penntag) {
        assert NIL != stringp(penntag) : "! stringp(penntag) " + ("Types.stringp(penntag) " + "CommonSymbols.NIL != Types.stringp(penntag) ") + penntag;
        return list_utilities.alist_lookup_without_values(penntag_forts(), penntag, symbol_function(EQUALP), NIL);
    }

    public static final SubLObject cycl_penntag_to_keyword_alt(SubLObject cycl_penntag) {
        {
            SubLObject string = list_utilities.alist_reverse_lookup_without_values(penntag_forts(), cycl_penntag, UNPROVIDED, UNPROVIDED);
            return NIL != string ? ((SubLObject) (make_keyword(string))) : NIL;
        }
    }

    public static SubLObject cycl_penntag_to_keyword(final SubLObject cycl_penntag) {
        final SubLObject string = list_utilities.alist_reverse_lookup_without_values(penntag_forts(), cycl_penntag, UNPROVIDED, UNPROVIDED);
        return NIL != string ? make_keyword(string) : NIL;
    }

    /**
     *
     *
     * @return FORT-P; the CycL token that is the TOKEN-NUMth token of TOKENIZATION in MT.
    Uses NAME-PREFIX to create a new term if none exists yet.
     */
    @LispMethod(comment = "@return FORT-P; the CycL token that is the TOKEN-NUMth token of TOKENIZATION in MT.\r\nUses NAME-PREFIX to create a new term if none exists yet.")
    public static final SubLObject find_or_create_parse_token_alt(SubLObject tokenization, SubLObject token_num, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        {
            SubLObject token = find_parse_token(tokenization, token_num);
            SubLObject newP = sublisp_null(token);
            if (NIL == token) {
                token = create_parse_token(tokenization, token_num, mt, name_prefix);
            }
            return values(token, newP);
        }
    }

    /**
     *
     *
     * @return FORT-P; the CycL token that is the TOKEN-NUMth token of TOKENIZATION in MT.
    Uses NAME-PREFIX to create a new term if none exists yet.
     */
    @LispMethod(comment = "@return FORT-P; the CycL token that is the TOKEN-NUMth token of TOKENIZATION in MT.\r\nUses NAME-PREFIX to create a new term if none exists yet.")
    public static SubLObject find_or_create_parse_token(final SubLObject tokenization, final SubLObject token_num, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        SubLObject token = find_parse_token(tokenization, token_num);
        final SubLObject newP = sublisp_null(token);
        if (NIL == token) {
            token = create_parse_token(tokenization, token_num, mt, name_prefix);
        }
        return values(token, newP);
    }

    public static final SubLObject find_parse_token_alt(SubLObject tokenization, SubLObject token_num) {
        {
            SubLObject token = ask_utilities.ask_variable($sym215$_TOKEN, listS($$nthTokenInTokenization, tokenization, token_num, $list_alt217), $$EverythingPSC, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED).first();
            return token;
        }
    }

    public static SubLObject find_parse_token(final SubLObject tokenization, final SubLObject token_num) {
        final SubLObject token = ask_utilities.ask_variable($sym212$_TOKEN, listS($$nthTokenInTokenization, tokenization, token_num, $list214), $$EverythingPSC, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED).first();
        return token;
    }

    public static final SubLObject create_parse_token_alt(SubLObject tokenization, SubLObject token_num, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        SubLTrampolineFile.checkType(token_num, NON_NEGATIVE_INTEGER_P);
        {
            SubLObject token = create_parse_tree_element($$CycParsingToken, mt, name_prefix);
            assert_parse_tree_info(list($$nthTokenInTokenization, tokenization, token_num, token), mt);
            return token;
        }
    }

    public static SubLObject create_parse_token(final SubLObject tokenization, final SubLObject token_num, SubLObject mt, SubLObject name_prefix) {
        if (mt == UNPROVIDED) {
            mt = mt_vars.$decontextualized_collection_mt$.getGlobalValue();
        }
        if (name_prefix == UNPROVIDED) {
            name_prefix = parsing_vars.$parse_tree_prefix$.getDynamicValue();
        }
        assert NIL != subl_promotions.non_negative_integer_p(token_num) : "! subl_promotions.non_negative_integer_p(token_num) " + ("subl_promotions.non_negative_integer_p(token_num) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(token_num) ") + token_num;
        final SubLObject token = create_parse_tree_element($$CycParsingToken, mt, name_prefix);
        assert_parse_tree_info(list($$nthTokenInTokenization, tokenization, token_num, token), mt);
        return token;
    }

    /**
     *
     *
     * @return NON-NEGATIVE-INTEGERP or NIL.
     */
    @LispMethod(comment = "@return NON-NEGATIVE-INTEGERP or NIL.")
    public static final SubLObject compute_parse_token_start_offset_alt(SubLObject tokenization, SubLObject token_num, SubLObject token_string) {
        SubLTrampolineFile.checkType(token_num, NON_NEGATIVE_INTEGER_P);
        if (token_num.isZero()) {
            return ZERO_INTEGER;
        }
        {
            SubLObject preceding_token = find_parse_token(tokenization, number_utilities.f_1_(token_num));
            SubLObject preceding_offset = (NIL != preceding_token) ? ((SubLObject) (token_start_offset(preceding_token))) : NIL;
            if (NIL != preceding_offset) {
                {
                    SubLObject preceding_string = token_string(preceding_token);
                    SubLObject cycl_full_string = tokenization_input_string(tokenization);
                    SubLObject full_string = (NIL != unicode_nauts.unicode_naut_p(cycl_full_string)) ? ((SubLObject) (unicode_strings.display_to_subl_string(cycl_utilities.nat_arg1(cycl_full_string, UNPROVIDED), UNPROVIDED, UNPROVIDED))) : cycl_full_string;
                    SubLObject subl_token_string = (NIL != unicode_nauts.unicode_naut_p(token_string)) ? ((SubLObject) (unicode_strings.display_to_subl_string(cycl_utilities.nat_arg1(token_string, UNPROVIDED), UNPROVIDED, UNPROVIDED))) : token_string;
                    SubLObject truncate_length = add(preceding_offset, length(preceding_string));
                    SubLObject remaining_string = (NIL != full_string) ? ((SubLObject) (string_utilities.substring(full_string, truncate_length, UNPROVIDED))) : NIL;
                    SubLObject local_offset = (NIL != remaining_string) ? ((SubLObject) (search(subl_token_string, remaining_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) : NIL;
                    SubLObject overall_offset = (NIL != local_offset) ? ((SubLObject) (add(local_offset, truncate_length))) : NIL;
                    if (NIL != overall_offset) {
                        return overall_offset;
                    }
                }
            }
            Errors.warn($str_alt220$Failed_to_compute_start_offset_fo, token_num, token_string, tokenization);
            return NIL;
        }
    }

    /**
     *
     *
     * @return NON-NEGATIVE-INTEGERP or NIL.
     */
    @LispMethod(comment = "@return NON-NEGATIVE-INTEGERP or NIL.")
    public static SubLObject compute_parse_token_start_offset(final SubLObject tokenization, final SubLObject token_num, final SubLObject token_string) {
        assert NIL != subl_promotions.non_negative_integer_p(token_num) : "! subl_promotions.non_negative_integer_p(token_num) " + ("subl_promotions.non_negative_integer_p(token_num) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(token_num) ") + token_num;
        if (token_num.isZero()) {
            return ZERO_INTEGER;
        }
        final SubLObject preceding_token = find_parse_token(tokenization, number_utilities.f_1_(token_num));
        final SubLObject preceding_offset = (NIL != preceding_token) ? token_start_offset(preceding_token) : NIL;
        if (NIL != preceding_offset) {
            final SubLObject preceding_string = token_string(preceding_token);
            final SubLObject cycl_full_string = tokenization_input_string(tokenization);
            final SubLObject full_string = (NIL != unicode_nauts.unicode_naut_p(cycl_full_string, UNPROVIDED)) ? unicode_strings.display_to_subl_string(cycl_utilities.nat_arg1(cycl_full_string, UNPROVIDED), UNPROVIDED, UNPROVIDED) : cycl_full_string;
            final SubLObject subl_token_string = (NIL != unicode_nauts.unicode_naut_p(token_string, UNPROVIDED)) ? unicode_strings.display_to_subl_string(cycl_utilities.nat_arg1(token_string, UNPROVIDED), UNPROVIDED, UNPROVIDED) : token_string;
            final SubLObject truncate_length = add(preceding_offset, length(preceding_string));
            final SubLObject remaining_string = (NIL != full_string) ? string_utilities.substring(full_string, truncate_length, UNPROVIDED) : NIL;
            final SubLObject local_offset = (NIL != remaining_string) ? search(subl_token_string, remaining_string, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED) : NIL;
            final SubLObject overall_offset = (NIL != local_offset) ? add(local_offset, truncate_length) : NIL;
            if (NIL != overall_offset) {
                return overall_offset;
            }
        }
        Errors.warn($str217$Failed_to_compute_start_offset_fo, token_num, token_string, tokenization);
        return NIL;
    }

    public static final SubLObject token_start_offset_alt(SubLObject token) {
        return NIL != indexed_term_p(token) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(token, $$tokenStartCharacterOffset, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject token_start_offset(final SubLObject token) {
        return NIL != indexed_term_p(token) ? kb_mapping_utilities.fpred_value_in_any_mt(token, $$tokenStartCharacterOffset, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject token_number_alt(SubLObject token) {
        return NIL != indexed_term_p(token) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(token, $$nthTokenInTokenization, THREE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject token_number(final SubLObject token) {
        return NIL != indexed_term_p(token) ? kb_mapping_utilities.fpred_value_in_any_mt(token, $$nthTokenInTokenization, THREE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject token_string_alt(SubLObject token) {
        return NIL != indexed_term_p(token) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(token, $$tokenString, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject token_string(final SubLObject token) {
        return NIL != indexed_term_p(token) ? kb_mapping_utilities.fpred_value_in_any_mt(token, $$tokenString, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject token_tokenization_alt(SubLObject token) {
        return NIL != indexed_term_p(token) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(token, $$tokenTokenization, ONE_INTEGER, TWO_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject token_tokenization(final SubLObject token) {
        return NIL != indexed_term_p(token) ? kb_mapping_utilities.fpred_value_in_any_mt(token, $$tokenTokenization, ONE_INTEGER, TWO_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject tokenization_peg_alt(SubLObject tokenization) {
        return NIL != indexed_term_p(tokenization) ? ((SubLObject) (kb_mapping_utilities.fpred_value_in_any_mt(tokenization, $$pegTokenizations, TWO_INTEGER, ONE_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject tokenization_peg(final SubLObject tokenization) {
        return NIL != indexed_term_p(tokenization) ? kb_mapping_utilities.fpred_value_in_any_mt(tokenization, $$pegTokenizations, TWO_INTEGER, ONE_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject tokenization_tokens_alt(SubLObject tokenization) {
        return NIL != indexed_term_p(tokenization) ? ((SubLObject) (kb_mapping_utilities.pred_values_in_any_mt(tokenization, $$tokenTokenization, TWO_INTEGER, ONE_INTEGER, $TRUE))) : NIL;
    }

    public static SubLObject tokenization_tokens(final SubLObject tokenization) {
        return NIL != indexed_term_p(tokenization) ? kb_mapping_utilities.pred_values_in_any_mt(tokenization, $$tokenTokenization, TWO_INTEGER, ONE_INTEGER, $TRUE) : NIL;
    }

    public static final SubLObject tokenization_input_string_alt(SubLObject tokenization) {
        return kb_mapping_utilities.fpred_value_in_any_mt(tokenization, $$tokenizationInputString, ONE_INTEGER, TWO_INTEGER, $TRUE);
    }

    public static SubLObject tokenization_input_string(final SubLObject tokenization) {
        return kb_mapping_utilities.fpred_value_in_any_mt(tokenization, $$tokenizationInputString, ONE_INTEGER, TWO_INTEGER, $TRUE);
    }

    public static final SubLObject tokenization_token_count_alt(SubLObject tokenization) {
        {
            SubLObject asserted = kb_mapping_utilities.fpred_value_in_any_mt(tokenization, $$numberOfTokensInTokenization, ONE_INTEGER, TWO_INTEGER, $TRUE);
            return NIL != asserted ? ((SubLObject) (asserted)) : ZERO_INTEGER;
        }
    }

    public static SubLObject tokenization_token_count(final SubLObject tokenization) {
        final SubLObject asserted = kb_mapping_utilities.fpred_value_in_any_mt(tokenization, $$numberOfTokensInTokenization, ONE_INTEGER, TWO_INTEGER, $TRUE);
        return NIL != asserted ? asserted : ZERO_INTEGER;
    }

    /**
     * Align two tokenizations of the same input string.
     *
     * @param TOKENS1;
     * 		listp of tokens
     * @param TOKENS2;
     * 		listp of tokens
     * @param KEY1;
     * 		function-spec-p for retrieving strings from items on TOKENS1.
     * @param KEY2;
     * 		function-spec-p for retrieving strings from items on TOKENS2.
     * @return ALIST-P mapping tokens from TOKENS2 onto tokens from TOKENS1.
     */
    @LispMethod(comment = "Align two tokenizations of the same input string.\r\n\r\n@param TOKENS1;\r\n\t\tlistp of tokens\r\n@param TOKENS2;\r\n\t\tlistp of tokens\r\n@param KEY1;\r\n\t\tfunction-spec-p for retrieving strings from items on TOKENS1.\r\n@param KEY2;\r\n\t\tfunction-spec-p for retrieving strings from items on TOKENS2.\r\n@return ALIST-P mapping tokens from TOKENS2 onto tokens from TOKENS1.")
    public static final SubLObject create_token_mapping_alt(SubLObject tokens1, SubLObject tokens2, SubLObject key1, SubLObject key2) {
        if (key1 == UNPROVIDED) {
            key1 = symbol_function(IDENTITY);
        }
        if (key2 == UNPROVIDED) {
            key2 = symbol_function(IDENTITY);
        }
        {
            SubLObject cur_index = ZERO_INTEGER;
            SubLObject token_mapping = NIL;
            {
                SubLObject cdotimes_end_var = length(tokens2);
                SubLObject i = NIL;
                for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                    token_mapping = cons(cons(i, i), token_mapping);
                }
            }
            {
                SubLObject list_var = NIL;
                SubLObject word = NIL;
                SubLObject i = NIL;
                for (list_var = tokens2, word = list_var.first(), i = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , word = list_var.first() , i = add(ONE_INTEGER, i)) {
                    {
                        SubLObject cur_token = funcall(key1, nth(cur_index, tokens1));
                        SubLObject next_token = funcall(key1, nth(add(cur_index, ONE_INTEGER), tokens1));
                        SubLObject word_string = funcall(key2, word);
                        if (i.numE(ZERO_INTEGER)) {
                            rplacd(assoc(ZERO_INTEGER, token_mapping, UNPROVIDED, UNPROVIDED), ZERO_INTEGER);
                        } else {
                            if (i.numE(subtract(length(tokens2), ONE_INTEGER))) {
                                rplacd(assoc(i, token_mapping, UNPROVIDED, UNPROVIDED), subtract(length(tokens1), ONE_INTEGER));
                            } else {
                                if (NIL != Strings.string_equal(word_string, cur_token, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                                    update_token_mapping(token_mapping, i, cur_index);
                                } else {
                                    if (next_token.isString() && (NIL != Strings.string_equal(word_string, next_token, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                                        cur_index = add(cur_index, ONE_INTEGER);
                                        update_token_mapping(token_mapping, i, cur_index);
                                    } else {
                                        if (((NIL != string_utilities.substringP(word_string, cur_token, UNPROVIDED, UNPROVIDED, UNPROVIDED)) && next_token.isString()) && (NIL == string_utilities.substringP(word_string, next_token, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                                            rplacd(assoc(i, token_mapping, UNPROVIDED, UNPROVIDED), cur_index);
                                            if (NIL != string_utilities.ends_with(cur_token, word_string, UNPROVIDED)) {
                                                cur_index = add(cur_index, ONE_INTEGER);
                                            }
                                        } else {
                                            if (((NIL == string_utilities.substringP(word_string, cur_token, UNPROVIDED, UNPROVIDED, UNPROVIDED)) && next_token.isString()) && (NIL != string_utilities.substringP(word_string, next_token, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                                                cur_index = add(cur_index, ONE_INTEGER);
                                                rplacd(assoc(i, token_mapping, UNPROVIDED, UNPROVIDED), cur_index);
                                            } else {
                                                rplacd(assoc(i, token_mapping, UNPROVIDED, UNPROVIDED), cur_index);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return token_mapping;
        }
    }

    @LispMethod(comment = "Align two tokenizations of the same input string.\r\n\r\n@param TOKENS1;\r\n\t\tlistp of tokens\r\n@param TOKENS2;\r\n\t\tlistp of tokens\r\n@param KEY1;\r\n\t\tfunction-spec-p for retrieving strings from items on TOKENS1.\r\n@param KEY2;\r\n\t\tfunction-spec-p for retrieving strings from items on TOKENS2.\r\n@return ALIST-P mapping tokens from TOKENS2 onto tokens from TOKENS1.")
    public static SubLObject create_token_mapping(final SubLObject tokens1, final SubLObject tokens2, SubLObject key1, SubLObject key2) {
        if (key1 == UNPROVIDED) {
            key1 = symbol_function(IDENTITY);
        }
        if (key2 == UNPROVIDED) {
            key2 = symbol_function(IDENTITY);
        }
        SubLObject cur_index = ZERO_INTEGER;
        SubLObject token_mapping = NIL;
        SubLObject cdotimes_end_var;
        SubLObject i;
        for (cdotimes_end_var = length(tokens2), i = NIL, i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
            token_mapping = cons(cons(i, i), token_mapping);
        }
        SubLObject list_var = NIL;
        SubLObject word = NIL;
        SubLObject j = NIL;
        list_var = tokens2;
        word = list_var.first();
        for (j = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , word = list_var.first() , j = add(ONE_INTEGER, j)) {
            final SubLObject cur_token = funcall(key1, nth(cur_index, tokens1));
            final SubLObject next_token = funcall(key1, nth(add(cur_index, ONE_INTEGER), tokens1));
            final SubLObject word_string = funcall(key2, word);
            if (j.numE(ZERO_INTEGER)) {
                rplacd(assoc(ZERO_INTEGER, token_mapping, UNPROVIDED, UNPROVIDED), ZERO_INTEGER);
            } else
                if (j.numE(subtract(length(tokens2), ONE_INTEGER))) {
                    rplacd(assoc(j, token_mapping, UNPROVIDED, UNPROVIDED), subtract(length(tokens1), ONE_INTEGER));
                } else
                    if (NIL != Strings.string_equal(word_string, cur_token, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                        update_token_mapping(token_mapping, j, cur_index);
                    } else
                        if (next_token.isString() && (NIL != Strings.string_equal(word_string, next_token, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                            cur_index = add(cur_index, ONE_INTEGER);
                            update_token_mapping(token_mapping, j, cur_index);
                        } else
                            if (((NIL != string_utilities.substringP(word_string, cur_token, UNPROVIDED, UNPROVIDED, UNPROVIDED)) && next_token.isString()) && (NIL == string_utilities.substringP(word_string, next_token, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                                rplacd(assoc(j, token_mapping, UNPROVIDED, UNPROVIDED), cur_index);
                                if (NIL != string_utilities.ends_with(cur_token, word_string, UNPROVIDED)) {
                                    cur_index = add(cur_index, ONE_INTEGER);
                                }
                            } else
                                if (((NIL == string_utilities.substringP(word_string, cur_token, UNPROVIDED, UNPROVIDED, UNPROVIDED)) && next_token.isString()) && (NIL != string_utilities.substringP(word_string, next_token, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                                    cur_index = add(cur_index, ONE_INTEGER);
                                    rplacd(assoc(j, token_mapping, UNPROVIDED, UNPROVIDED), cur_index);
                                } else {
                                    rplacd(assoc(j, token_mapping, UNPROVIDED, UNPROVIDED), cur_index);
                                }





        }
        return token_mapping;
    }/**
     * Align two tokenizations of the same input string.
     *
     * @param TOKENS1;
     * 		listp of tokens
     * @param TOKENS2;
     * 		listp of tokens
     * @param KEY1;
     * 		function-spec-p for retrieving strings from items on TOKENS1.
     * @param KEY2;
     * 		function-spec-p for retrieving strings from items on TOKENS2.
     * @return ALIST-P mapping tokens from TOKENS2 onto tokens from TOKENS1.
     */


    public static final SubLObject update_token_mapping_alt(SubLObject token_mapping, SubLObject word_index, SubLObject token_index) {
        rplacd(assoc(word_index, token_mapping, UNPROVIDED, UNPROVIDED), token_index);
        if (word_index.numG(ZERO_INTEGER) && token_index.numG(ZERO_INTEGER)) {
            rplacd(assoc(subtract(word_index, ONE_INTEGER), token_mapping, UNPROVIDED, UNPROVIDED), subtract(token_index, ONE_INTEGER));
            token_index = add(token_index, ONE_INTEGER);
        }
        return token_index;
    }

    public static SubLObject update_token_mapping(final SubLObject token_mapping, final SubLObject word_index, SubLObject token_index) {
        rplacd(assoc(word_index, token_mapping, UNPROVIDED, UNPROVIDED), token_index);
        if (word_index.numG(ZERO_INTEGER) && token_index.numG(ZERO_INTEGER)) {
            rplacd(assoc(subtract(word_index, ONE_INTEGER), token_mapping, UNPROVIDED, UNPROVIDED), subtract(token_index, ONE_INTEGER));
            token_index = add(token_index, ONE_INTEGER);
        }
        return token_index;
    }

    public static final SubLObject construct_parse_expression_from_syntactic_node_alt(SubLObject root_node) {
        {
            SubLObject penntag = syntactic_node_penntag(root_node);
            SubLObject category = cycl_penntag_to_keyword(penntag);
            SubLObject lexicalP = lexical_nodeP(root_node);
            if (NIL != lexicalP) {
                return list(category, syntactic_node_string(root_node));
            } else {
                {
                    SubLObject subexpressions = Mapping.mapcar(CONSTRUCT_PARSE_EXPRESSION_FROM_SYNTACTIC_NODE, syntactic_node_dtrs(root_node));
                    return cons(category, subexpressions);
                }
            }
        }
    }

    public static SubLObject construct_parse_expression_from_syntactic_node(final SubLObject root_node) {
        final SubLObject penntag = syntactic_node_penntag(root_node);
        final SubLObject category = cycl_penntag_to_keyword(penntag);
        final SubLObject lexicalP = lexical_nodeP(root_node);
        if (NIL != lexicalP) {
            return list(category, syntactic_node_string(root_node));
        }
        final SubLObject subexpressions = Mapping.mapcar(CONSTRUCT_PARSE_EXPRESSION_FROM_SYNTACTIC_NODE, syntactic_node_dtrs(root_node));
        return cons(category, subexpressions);
    }

    /**
     *
     *
     * @param TREES-TO-NODES
     * 		dictionary-p or NIL; If present, mappings will be added from each subnode of ROOT-NODE to the corresponding subtree.
     */
    @LispMethod(comment = "@param TREES-TO-NODES\r\n\t\tdictionary-p or NIL; If present, mappings will be added from each subnode of ROOT-NODE to the corresponding subtree.")
    public static final SubLObject construct_subl_parse_tree_from_syntactic_node_alt(SubLObject root_node, SubLObject trees_to_nodes) {
        if (trees_to_nodes == UNPROVIDED) {
            trees_to_nodes = NIL;
        }
        SubLTrampolineFile.checkType(root_node, $sym226$PHRASAL_NODE_);
        {
            SubLObject v_parse_tree = parse_tree.new_parse_tree(construct_parse_expression_from_syntactic_node(root_node), UNPROVIDED, UNPROVIDED);
            if (NIL != dictionary.dictionary_p(trees_to_nodes)) {
                add_parse_tree_to_node_mappings(root_node, v_parse_tree, trees_to_nodes);
            }
            return v_parse_tree;
        }
    }

    /**
     *
     *
     * @param TREES-TO-NODES
     * 		dictionary-p or NIL; If present, mappings will be added from each subnode of ROOT-NODE to the corresponding subtree.
     */
    @LispMethod(comment = "@param TREES-TO-NODES\r\n\t\tdictionary-p or NIL; If present, mappings will be added from each subnode of ROOT-NODE to the corresponding subtree.")
    public static SubLObject construct_subl_parse_tree_from_syntactic_node(final SubLObject root_node, SubLObject trees_to_nodes) {
        if (trees_to_nodes == UNPROVIDED) {
            trees_to_nodes = NIL;
        }
        assert NIL != phrasal_nodeP(root_node) : "! parsing_utilities.phrasal_nodeP(root_node) " + ("parsing_utilities.phrasal_nodeP(root_node) " + "CommonSymbols.NIL != parsing_utilities.phrasal_nodeP(root_node) ") + root_node;
        final SubLObject v_parse_tree = parse_tree.new_parse_tree(construct_parse_expression_from_syntactic_node(root_node), UNPROVIDED, UNPROVIDED);
        if (NIL != dictionary.dictionary_p(trees_to_nodes)) {
            add_parse_tree_to_node_mappings(root_node, v_parse_tree, trees_to_nodes);
        }
        return v_parse_tree;
    }

    /**
     *
     *
     * @param TREES-TO-NODES
     * 		dictionary-p; Add a mapping from each subnode of ROOT-NODE to the corresponding subtree of PARSE-TREE.
     */
    @LispMethod(comment = "@param TREES-TO-NODES\r\n\t\tdictionary-p; Add a mapping from each subnode of ROOT-NODE to the corresponding subtree of PARSE-TREE.")
    public static final SubLObject add_parse_tree_to_node_mappings_alt(SubLObject root_node, SubLObject v_parse_tree, SubLObject trees_to_nodes) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            dictionary.dictionary_enter(trees_to_nodes, v_parse_tree, root_node);
            {
                SubLObject list_var = NIL;
                SubLObject subnode = NIL;
                SubLObject dtr_num = NIL;
                for (list_var = syntactic_node_dtrs(root_node), subnode = list_var.first(), dtr_num = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , subnode = list_var.first() , dtr_num = add(ONE_INTEGER, dtr_num)) {
                    {
                        SubLObject subtree = methods.funcall_instance_method_with_1_args(v_parse_tree, GET_DAUGHTER, dtr_num);
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if (NIL == parse_tree.parse_tree_p(subtree)) {
                                Errors.error($str_alt228$Couldn_t_find_dtr__S_of__S, dtr_num, v_parse_tree);
                            }
                        }
                        add_parse_tree_to_node_mappings(subnode, subtree, trees_to_nodes);
                    }
                }
            }
            return trees_to_nodes;
        }
    }

    /**
     *
     *
     * @param TREES-TO-NODES
     * 		dictionary-p; Add a mapping from each subnode of ROOT-NODE to the corresponding subtree of PARSE-TREE.
     */
    @LispMethod(comment = "@param TREES-TO-NODES\r\n\t\tdictionary-p; Add a mapping from each subnode of ROOT-NODE to the corresponding subtree of PARSE-TREE.")
    public static SubLObject add_parse_tree_to_node_mappings(final SubLObject root_node, final SubLObject v_parse_tree, final SubLObject trees_to_nodes) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        dictionary.dictionary_enter(trees_to_nodes, v_parse_tree, root_node);
        SubLObject list_var = NIL;
        SubLObject subnode = NIL;
        SubLObject dtr_num = NIL;
        list_var = syntactic_node_dtrs(root_node);
        subnode = list_var.first();
        for (dtr_num = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , subnode = list_var.first() , dtr_num = add(ONE_INTEGER, dtr_num)) {
            final SubLObject subtree = methods.funcall_instance_method_with_1_args(v_parse_tree, GET_DAUGHTER, dtr_num);
            if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == parse_tree.parse_tree_p(subtree))) {
                Errors.error($str225$Couldn_t_find_dtr__S_of__S, dtr_num, v_parse_tree);
            }
            add_parse_tree_to_node_mappings(subnode, subtree, trees_to_nodes);
        }
        return trees_to_nodes;
    }

    public static final SubLObject with_parsing_filter_piles_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt229);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject good = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt229);
                    good = current.first();
                    current = current.rest();
                    {
                        SubLObject bad = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt229);
                        current = current.rest();
                        if (NIL == current) {
                            current = temp;
                            {
                                SubLObject body = current;
                                return listS(CLET, list(list($good_parsing_pile$, good), list($bad_parsing_pile$, bad)), append(body, NIL));
                            }
                        } else {
                            cdestructuring_bind_error(datum, $list_alt229);
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject with_parsing_filter_piles(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list226);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject good = NIL;
        destructuring_bind_must_consp(current, datum, $list226);
        good = current.first();
        current = current.rest();
        final SubLObject bad = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list226);
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return listS(CLET, list(list($good_parsing_pile$, good), list($bad_parsing_pile$, bad)), append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list226);
        return NIL;
    }

    public static final SubLObject parsing_filter_accepts_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject parse = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt232);
            parse = current.first();
            current = current.rest();
            if (NIL == current) {
                return listS(CPUSH, parse, $list_alt234);
            } else {
                cdestructuring_bind_error(datum, $list_alt232);
            }
        }
        return NIL;
    }

    public static SubLObject parsing_filter_accepts(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject parse = NIL;
        destructuring_bind_must_consp(current, datum, $list229);
        parse = current.first();
        current = current.rest();
        if (NIL == current) {
            return listS(CPUSH, parse, $list231);
        }
        cdestructuring_bind_error(datum, $list229);
        return NIL;
    }

    public static final SubLObject parsing_filter_rejects_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject parse = NIL;
            SubLObject reason = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt235);
            parse = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt235);
            reason = current.first();
            current = current.rest();
            if (NIL == current) {
                return listS(CPUSH, list(LIST, parse, reason), $list_alt236);
            } else {
                cdestructuring_bind_error(datum, $list_alt235);
            }
        }
        return NIL;
    }

    public static SubLObject parsing_filter_rejects(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject parse = NIL;
        SubLObject reason = NIL;
        destructuring_bind_must_consp(current, datum, $list232);
        parse = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list232);
        reason = current.first();
        current = current.rest();
        if (NIL == current) {
            return listS(CPUSH, list(LIST, parse, reason), $list233);
        }
        cdestructuring_bind_error(datum, $list232);
        return NIL;
    }

    public static final SubLObject apply_elimination_parsing_filter_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject filter = NIL;
            SubLObject denot_accessor = NIL;
            SubLObject diag_accessor = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt237);
            filter = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt237);
            denot_accessor = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt237);
            diag_accessor = current.first();
            current = current.rest();
            if (NIL == current) {
                {
                    SubLObject filter_response = $sym238$FILTER_RESPONSE;
                    SubLObject some_good = $sym239$SOME_GOOD;
                    SubLObject some_bad = $sym240$SOME_BAD;
                    SubLObject this_candidate = $sym241$THIS_CANDIDATE;
                    return list(CLET, list(bq_cons(some_good, $list_alt242), bq_cons(some_bad, $list_alt236)), list(CDOLIST, bq_cons(this_candidate, $list_alt243), list(CLET, list(list(filter_response, list(FUNCALL, filter, list(FUNCALL, denot_accessor, this_candidate)))), list(PIF, filter_response, list(CPUSH, list(LIST, this_candidate, list(CONS, filter_response, list(FUNCALL, diag_accessor, this_candidate))), some_bad), list(CPUSH, this_candidate, some_good)))), list(PWHEN, list(PROPER_LIST_P, some_good), list(CSETQ, $good_parsing_pile$, some_good), list(CSETQ, $bad_parsing_pile$, some_bad)));
                }
            } else {
                cdestructuring_bind_error(datum, $list_alt237);
            }
        }
        return NIL;
    }

    public static SubLObject apply_elimination_parsing_filter(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject filter = NIL;
        SubLObject denot_accessor = NIL;
        SubLObject diag_accessor = NIL;
        destructuring_bind_must_consp(current, datum, $list234);
        filter = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list234);
        denot_accessor = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list234);
        diag_accessor = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject filter_response = $sym235$FILTER_RESPONSE;
            final SubLObject some_good = $sym236$SOME_GOOD;
            final SubLObject some_bad = $sym237$SOME_BAD;
            final SubLObject this_candidate = $sym238$THIS_CANDIDATE;
            return list(CLET, list(bq_cons(some_good, $list239), bq_cons(some_bad, $list233)), list(CDOLIST, bq_cons(this_candidate, $list240), list(CLET, list(list(filter_response, list(FUNCALL, filter, list(FUNCALL, denot_accessor, this_candidate)))), list(PIF, filter_response, list(CPUSH, list(LIST, this_candidate, list(CONS, filter_response, list(FUNCALL, diag_accessor, this_candidate))), some_bad), list(CPUSH, this_candidate, some_good)))), list(PWHEN, list(PROPER_LIST_P, some_good), list(CSETQ, $good_parsing_pile$, some_good), list(CSETQ, $bad_parsing_pile$, some_bad)));
        }
        cdestructuring_bind_error(datum, $list234);
        return NIL;
    }

    public static final SubLObject save_parsing_filter_piles_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject good = NIL;
            SubLObject bad = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt250);
            good = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt250);
            bad = current.first();
            current = current.rest();
            if (NIL == current) {
                return list(PROGN, listS(CSETQ, good, $list_alt243), listS(CSETQ, bad, $list_alt236));
            } else {
                cdestructuring_bind_error(datum, $list_alt250);
            }
        }
        return NIL;
    }

    public static SubLObject save_parsing_filter_piles(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject good = NIL;
        SubLObject bad = NIL;
        destructuring_bind_must_consp(current, datum, $list247);
        good = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list247);
        bad = current.first();
        current = current.rest();
        if (NIL == current) {
            return list(PROGN, listS(CSETQ, good, $list240), listS(CSETQ, bad, $list233));
        }
        cdestructuring_bind_error(datum, $list247);
        return NIL;
    }

    /**
     *
     *
     * @param LISTP
     * 		of parses being considered
     * @param FUNCTION
     * 		expected to be a booleanp 'filter' when it rejects a parse
     * @param ACCESSOR
     * 		the function to get the semx out of each parse, e.g., @see nc-block-denot
     * @param QUIET?
     * 		optional - if non-nil, report no failures if all of them fail
     * @return LISTP of (parse diagnostic) pairs, which diagnostic is non-nil for every parse which failed the filter
     */
    @LispMethod(comment = "@param LISTP\r\n\t\tof parses being considered\r\n@param FUNCTION\r\n\t\texpected to be a booleanp \'filter\' when it rejects a parse\r\n@param ACCESSOR\r\n\t\tthe function to get the semx out of each parse, e.g., @see nc-block-denot\r\n@param QUIET?\r\n\t\toptional - if non-nil, report no failures if all of them fail\r\n@return LISTP of (parse diagnostic) pairs, which diagnostic is non-nil for every parse which failed the filter")
    public static final SubLObject apply_boolean_parsing_filter_alt(SubLObject parses, SubLObject filter, SubLObject accessor, SubLObject quietP) {
        if (quietP == UNPROVIDED) {
            quietP = NIL;
        }
        {
            SubLObject if_some_bad = NIL;
            SubLObject if_all_bad = NIL;
            SubLObject all_badP = T;
            SubLObject cdolist_list_var = parses;
            SubLObject parse = NIL;
            for (parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parse = cdolist_list_var.first()) {
                {
                    SubLObject diagnostic = funcall(filter, funcall(accessor, parse));
                    if (NIL == diagnostic) {
                        all_badP = NIL;
                    }
                    if (NIL != quietP) {
                        if_all_bad = cons(list(parse, NIL), if_all_bad);
                    }
                    if_some_bad = cons(list(parse, diagnostic), if_some_bad);
                }
            }
            return (NIL != all_badP) && (NIL == quietP) ? ((SubLObject) (if_all_bad)) : if_some_bad;
        }
    }

    /**
     *
     *
     * @param LISTP
     * 		of parses being considered
     * @param FUNCTION
     * 		expected to be a booleanp 'filter' when it rejects a parse
     * @param ACCESSOR
     * 		the function to get the semx out of each parse, e.g., @see nc-block-denot
     * @param QUIET?
     * 		optional - if non-nil, report no failures if all of them fail
     * @return LISTP of (parse diagnostic) pairs, which diagnostic is non-nil for every parse which failed the filter
     */
    @LispMethod(comment = "@param LISTP\r\n\t\tof parses being considered\r\n@param FUNCTION\r\n\t\texpected to be a booleanp \'filter\' when it rejects a parse\r\n@param ACCESSOR\r\n\t\tthe function to get the semx out of each parse, e.g., @see nc-block-denot\r\n@param QUIET?\r\n\t\toptional - if non-nil, report no failures if all of them fail\r\n@return LISTP of (parse diagnostic) pairs, which diagnostic is non-nil for every parse which failed the filter")
    public static SubLObject apply_boolean_parsing_filter(final SubLObject parses, final SubLObject filter, final SubLObject accessor, SubLObject quietP) {
        if (quietP == UNPROVIDED) {
            quietP = NIL;
        }
        SubLObject if_some_bad = NIL;
        SubLObject if_all_bad = NIL;
        SubLObject all_badP = T;
        SubLObject cdolist_list_var = parses;
        SubLObject parse = NIL;
        parse = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject diagnostic = funcall(filter, funcall(accessor, parse));
            if (NIL == diagnostic) {
                all_badP = NIL;
            }
            if (NIL != quietP) {
                if_all_bad = cons(list(parse, NIL), if_all_bad);
            }
            if_some_bad = cons(list(parse, diagnostic), if_some_bad);
            cdolist_list_var = cdolist_list_var.rest();
            parse = cdolist_list_var.first();
        } 
        return (NIL != all_badP) && (NIL == quietP) ? if_all_bad : if_some_bad;
    }

    public static final SubLObject apply_macroscopic_parsing_filter_alt(SubLObject parses, SubLObject filter, SubLObject accessor, SubLObject quietP) {
        if (quietP == UNPROVIDED) {
            quietP = NIL;
        }
        {
            SubLObject if_some_bad = NIL;
            SubLObject if_all_bad = NIL;
            SubLObject all_badP = T;
            SubLObject cdolist_list_var = parses;
            SubLObject parse = NIL;
            for (parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parse = cdolist_list_var.first()) {
                {
                    SubLObject diagnostic = funcall(filter, funcall(accessor, parse), Mapping.mapcar(accessor, parses));
                    if (NIL == diagnostic) {
                        all_badP = NIL;
                    }
                    if (NIL != quietP) {
                        if_all_bad = cons(list(parse, NIL), if_all_bad);
                    }
                    if_some_bad = cons(list(parse, diagnostic), if_some_bad);
                }
            }
            return (NIL != all_badP) && (NIL == quietP) ? ((SubLObject) (if_all_bad)) : if_some_bad;
        }
    }

    public static SubLObject apply_macroscopic_parsing_filter(final SubLObject parses, final SubLObject filter, final SubLObject accessor, SubLObject quietP) {
        if (quietP == UNPROVIDED) {
            quietP = NIL;
        }
        SubLObject if_some_bad = NIL;
        SubLObject if_all_bad = NIL;
        SubLObject all_badP = T;
        SubLObject cdolist_list_var = parses;
        SubLObject parse = NIL;
        parse = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject diagnostic = funcall(filter, funcall(accessor, parse), Mapping.mapcar(accessor, parses));
            if (NIL == diagnostic) {
                all_badP = NIL;
            }
            if (NIL != quietP) {
                if_all_bad = cons(list(parse, NIL), if_all_bad);
            }
            if_some_bad = cons(list(parse, diagnostic), if_some_bad);
            cdolist_list_var = cdolist_list_var.rest();
            parse = cdolist_list_var.first();
        } 
        return (NIL != all_badP) && (NIL == quietP) ? if_all_bad : if_some_bad;
    }

    public static final SubLObject parsing_subcollection_function_nautP_alt(SubLObject v_object) {
        return reformulator_module_quantifier_unifier_3.subcollection_function_nautP(v_object, $$UniversalVocabularyMt);
    }

    public static SubLObject parsing_subcollection_function_nautP(final SubLObject v_object) {
        return reformulator_module_quantifier_unifier_3.subcollection_function_nautP(v_object, $$UniversalVocabularyMt);
    }

    public static final SubLObject ternary_parsing_subcollection_function_nautP_alt(SubLObject v_object) {
        return makeBoolean((NIL != el_ternary_formula_p(v_object)) && (NIL != isa.isaP(cycl_utilities.nat_functor(v_object), $$SubcollectionRelationFunction, UNPROVIDED, UNPROVIDED)));
    }

    public static SubLObject ternary_parsing_subcollection_function_nautP(final SubLObject v_object) {
        return makeBoolean((NIL != el_ternary_formula_p(v_object)) && (NIL != isa.isaP(cycl_utilities.nat_functor(v_object), $$SubcollectionRelationFunction, UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG2
     * @unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$Bartering
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG2\r\n@unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$Bartering")
    public static final SubLObject subcollection_function_naut_from_arg_alt(SubLObject subfn) {
        return NIL != isa.isaP(cycl_utilities.formula_arg0(subfn), $const252$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED) ? ((SubLObject) (cycl_utilities.formula_arg1(subfn, UNPROVIDED))) : cycl_utilities.formula_arg3(subfn, UNPROVIDED);
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG2
     * @unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$Bartering
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG2\r\n@unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$Bartering")
    public static SubLObject subcollection_function_naut_from_arg(final SubLObject subfn) {
        return NIL != isa.isaP(cycl_utilities.formula_arg0(subfn), $const249$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED) ? cycl_utilities.formula_arg1(subfn, UNPROVIDED) : cycl_utilities.formula_arg3(subfn, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in
    its 'from' argument.
     * @unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-FROM-ARG? #$SubcollectionOfWithRelationFromTypeFn).
     */
    @LispMethod(comment = "@return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in\r\nits \'from\' argument.\r\n@unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-FROM-ARG? #$SubcollectionOfWithRelationFromTypeFn).")
    public static final SubLObject subcollection_function_type_level_in_from_argP_alt(SubLObject subfn) {
        return makeBoolean((NIL != isa.isaP(subfn, $const252$SubcollectionRelationFunction_Can, UNPROVIDED, UNPROVIDED)) || (NIL != isa.isaP(subfn, $const253$SubcollectionRelationFunction_Typ, UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in
    its 'from' argument.
     * @unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-FROM-ARG? #$SubcollectionOfWithRelationFromTypeFn).
     */
    @LispMethod(comment = "@return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in\r\nits \'from\' argument.\r\n@unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-FROM-ARG? #$SubcollectionOfWithRelationFromTypeFn).")
    public static SubLObject subcollection_function_type_level_in_from_argP(final SubLObject subfn) {
        return makeBoolean((NIL != isa.isaP(subfn, $const249$SubcollectionRelationFunction_Can, UNPROVIDED, UNPROVIDED)) || (NIL != isa.isaP(subfn, $const250$SubcollectionRelationFunction_Typ, UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in
    its 'to' argument.
     * @unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-TO-ARG? #$SubcollectionOfWithRelationToTypeFn).
     */
    @LispMethod(comment = "@return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in\r\nits \'to\' argument.\r\n@unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-TO-ARG? #$SubcollectionOfWithRelationToTypeFn).")
    public static final SubLObject subcollection_function_type_level_in_to_argP_alt(SubLObject subfn) {
        return makeBoolean((NIL != isa.isaP(subfn, $const254$SubcollectionRelationFunction_Inv, UNPROVIDED, UNPROVIDED)) || (NIL != isa.isaP(subfn, $const253$SubcollectionRelationFunction_Typ, UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in
    its 'to' argument.
     * @unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-TO-ARG? #$SubcollectionOfWithRelationToTypeFn).
     */
    @LispMethod(comment = "@return BOOLEANP; Non-NIL iff SUBFN is a subcollection function that is type-level in\r\nits \'to\' argument.\r\n@unknown (SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-TO-ARG? #$SubcollectionOfWithRelationToTypeFn).")
    public static SubLObject subcollection_function_type_level_in_to_argP(final SubLObject subfn) {
        return makeBoolean((NIL != isa.isaP(subfn, $const251$SubcollectionRelationFunction_Inv, UNPROVIDED, UNPROVIDED)) || (NIL != isa.isaP(subfn, $const250$SubcollectionRelationFunction_Typ, UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG1
     * @unknown for (#$SubcollectionOfWithRelationFromTypeFn #$Livestock #$doneBy #$Bartering), return #$Livestock
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG1\r\n@unknown for (#$SubcollectionOfWithRelationFromTypeFn #$Livestock #$doneBy #$Bartering), return #$Livestock")
    public static final SubLObject subcollection_function_naut_to_arg_alt(SubLObject subfn) {
        return NIL != isa.isaP(cycl_utilities.formula_arg0(subfn), $const252$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED) ? ((SubLObject) (cycl_utilities.formula_arg3(subfn, UNPROVIDED))) : cycl_utilities.formula_arg1(subfn, UNPROVIDED);
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG1
     * @unknown for (#$SubcollectionOfWithRelationFromTypeFn #$Livestock #$doneBy #$Bartering), return #$Livestock
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return ARG1\r\n@unknown for (#$SubcollectionOfWithRelationFromTypeFn #$Livestock #$doneBy #$Bartering), return #$Livestock")
    public static SubLObject subcollection_function_naut_to_arg(final SubLObject subfn) {
        return NIL != isa.isaP(cycl_utilities.formula_arg0(subfn), $const249$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED) ? cycl_utilities.formula_arg3(subfn, UNPROVIDED) : cycl_utilities.formula_arg1(subfn, UNPROVIDED);
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return PRED
     * @unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$objectTypeUsed
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return PRED\r\n@unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$objectTypeUsed")
    public static final SubLObject subcollection_function_naut_pred_alt(SubLObject subfn) {
        return cycl_utilities.formula_arg2(subfn, UNPROVIDED);
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return PRED
     * @unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$objectTypeUsed
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), then return PRED\r\n@unknown for (#$SubcollectionOfWithRelationToFn #$Bartering #$objectTypeUsed #$Store-Generic), return #$objectTypeUsed")
    public static SubLObject subcollection_function_naut_pred(final SubLObject subfn) {
        return cycl_utilities.formula_arg2(subfn, UNPROVIDED);
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG1
    equivalent changed to COL
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG1\r\nequivalent changed to COL")
    public static final SubLObject subcollection_function_naut_with_new_from_arg_alt(SubLObject subfn, SubLObject col) {
        {
            SubLObject new_nat = list(cycl_utilities.formula_arg0(subfn));
            SubLObject canonicalP = isa.isaP(cycl_utilities.formula_arg0(subfn), $const252$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED);
            new_nat = cons(NIL != canonicalP ? ((SubLObject) (col)) : cycl_utilities.formula_arg1(subfn, UNPROVIDED), new_nat);
            new_nat = cons(cycl_utilities.formula_arg2(subfn, UNPROVIDED), new_nat);
            new_nat = cons(NIL != canonicalP ? ((SubLObject) (cycl_utilities.formula_arg3(subfn, UNPROVIDED))) : col, new_nat);
            return reverse(new_nat);
        }
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG1
    equivalent changed to COL
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG1\r\nequivalent changed to COL")
    public static SubLObject subcollection_function_naut_with_new_from_arg(final SubLObject subfn, final SubLObject col) {
        SubLObject new_nat = list(cycl_utilities.formula_arg0(subfn));
        final SubLObject canonicalP = isa.isaP(cycl_utilities.formula_arg0(subfn), $const249$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED);
        new_nat = cons(NIL != canonicalP ? col : cycl_utilities.formula_arg1(subfn, UNPROVIDED), new_nat);
        new_nat = cons(cycl_utilities.formula_arg2(subfn, UNPROVIDED), new_nat);
        new_nat = cons(NIL != canonicalP ? cycl_utilities.formula_arg3(subfn, UNPROVIDED) : col, new_nat);
        return reverse(new_nat);
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG2
    equivalent changed to COL
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG2\r\nequivalent changed to COL")
    public static final SubLObject subcollection_function_naut_with_new_to_arg_alt(SubLObject subfn, SubLObject col) {
        {
            SubLObject new_nat = list(cycl_utilities.formula_arg0(subfn));
            SubLObject canonicalP = isa.isaP(cycl_utilities.formula_arg0(subfn), $const252$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED);
            new_nat = cons(NIL != canonicalP ? ((SubLObject) (cycl_utilities.formula_arg1(subfn, UNPROVIDED))) : col, new_nat);
            new_nat = cons(cycl_utilities.formula_arg2(subfn, UNPROVIDED), new_nat);
            new_nat = cons(NIL != canonicalP ? ((SubLObject) (col)) : cycl_utilities.formula_arg3(subfn, UNPROVIDED), new_nat);
            return reverse(new_nat);
        }
    }

    /**
     *
     *
     * @return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG2
    equivalent changed to COL
     */
    @LispMethod(comment = "@return if the subcollection naut corresponds to (PRED ARG1 ARG2), return a version with the ARG2\r\nequivalent changed to COL")
    public static SubLObject subcollection_function_naut_with_new_to_arg(final SubLObject subfn, final SubLObject col) {
        SubLObject new_nat = list(cycl_utilities.formula_arg0(subfn));
        final SubLObject canonicalP = isa.isaP(cycl_utilities.formula_arg0(subfn), $const249$SubcollectionRelationFunction_Can, $$UniversalVocabularyMt, UNPROVIDED);
        new_nat = cons(NIL != canonicalP ? cycl_utilities.formula_arg1(subfn, UNPROVIDED) : col, new_nat);
        new_nat = cons(cycl_utilities.formula_arg2(subfn, UNPROVIDED), new_nat);
        new_nat = cons(NIL != canonicalP ? col : cycl_utilities.formula_arg3(subfn, UNPROVIDED), new_nat);
        return reverse(new_nat);
    }

    /**
     *
     *
     * @param LISTP
     * 		of (PARSE OBJECT) pairs, e.g., the results of @see parse-noun-compound
     * @return LISTP - said list, minus any subcollection nauts which are genls of other subcollection nauts (in that list)
     * @unknown reduces (SubcollectionOfWithRelationToTypeFn Smuggling {transportees,objectActedOn} Weapon), to just the transportees version
     */
    @LispMethod(comment = "@param LISTP\r\n\t\tof (PARSE OBJECT) pairs, e.g., the results of @see parse-noun-compound\r\n@return LISTP - said list, minus any subcollection nauts which are genls of other subcollection nauts (in that list)\r\n@unknown reduces (SubcollectionOfWithRelationToTypeFn Smuggling {transportees,objectActedOn} Weapon), to just the transportees version")
    public static final SubLObject eliminate_genl_subcollection_nauts_alt(SubLObject parses) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if ((NIL == parses) || (NIL != list_utilities.singletonP(parses))) {
                return parses;
            }
            {
                SubLObject nats_by_func = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
                SubLObject good_parses = new_set(EQUAL, UNPROVIDED);
                {
                    SubLObject cdolist_list_var = parses;
                    SubLObject this_parse = NIL;
                    for (this_parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , this_parse = cdolist_list_var.first()) {
                        if (NIL != ternary_parsing_subcollection_function_nautP(this_parse.first())) {
                            {
                                SubLObject this_func = cycl_utilities.formula_arg0(this_parse.first());
                                dictionary.dictionary_enter(nats_by_func, this_func, cons(this_parse, dictionary.dictionary_lookup(nats_by_func, this_func, UNPROVIDED)));
                            }
                        } else {
                            set_add(this_parse, good_parses);
                        }
                    }
                }
                {
                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(nats_by_func));
                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                        thread.resetMultipleValues();
                        {
                            SubLObject func = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                            SubLObject parses_77 = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if (NIL != list_utilities.singletonP(parses_77)) {
                                set_add(parses_77.first(), good_parses);
                            } else {
                                {
                                    SubLObject cdolist_list_var = parses_77;
                                    SubLObject this_parse = NIL;
                                    for (this_parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , this_parse = cdolist_list_var.first()) {
                                        {
                                            SubLObject found_generalityP = NIL;
                                            SubLObject checked_anyP = NIL;
                                            if (NIL == found_generalityP) {
                                                {
                                                    SubLObject csome_list_var = parses_77;
                                                    SubLObject other_parse = NIL;
                                                    for (other_parse = csome_list_var.first(); !((NIL != found_generalityP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , other_parse = csome_list_var.first()) {
                                                        if (!other_parse.first().equal(this_parse.first())) {
                                                            checked_anyP = T;
                                                            {
                                                                SubLObject datum = cycl_utilities.formula_args(this_parse.first(), UNPROVIDED);
                                                                SubLObject current = datum;
                                                                SubLObject this_arg1 = NIL;
                                                                SubLObject this_pred = NIL;
                                                                SubLObject this_arg3 = NIL;
                                                                destructuring_bind_must_consp(current, datum, $list_alt255);
                                                                this_arg1 = current.first();
                                                                current = current.rest();
                                                                destructuring_bind_must_consp(current, datum, $list_alt255);
                                                                this_pred = current.first();
                                                                current = current.rest();
                                                                destructuring_bind_must_consp(current, datum, $list_alt255);
                                                                this_arg3 = current.first();
                                                                current = current.rest();
                                                                if (NIL == current) {
                                                                    {
                                                                        SubLObject datum_78 = cycl_utilities.formula_args(other_parse.first(), UNPROVIDED);
                                                                        SubLObject current_79 = datum_78;
                                                                        SubLObject other_arg1 = NIL;
                                                                        SubLObject other_pred = NIL;
                                                                        SubLObject other_arg3 = NIL;
                                                                        destructuring_bind_must_consp(current_79, datum_78, $list_alt256);
                                                                        other_arg1 = current_79.first();
                                                                        current_79 = current_79.rest();
                                                                        destructuring_bind_must_consp(current_79, datum_78, $list_alt256);
                                                                        other_pred = current_79.first();
                                                                        current_79 = current_79.rest();
                                                                        destructuring_bind_must_consp(current_79, datum_78, $list_alt256);
                                                                        other_arg3 = current_79.first();
                                                                        current_79 = current_79.rest();
                                                                        if (NIL == current_79) {
                                                                            found_generalityP = makeBoolean(((NIL != genl_predicates.genl_predicate_in_any_mtP(this_pred, other_pred)) && (NIL != genls.genl_in_any_mtP(this_arg1, other_arg1))) && (NIL != genls.genl_in_any_mtP(this_arg3, other_arg3)));
                                                                        } else {
                                                                            cdestructuring_bind_error(datum_78, $list_alt256);
                                                                        }
                                                                    }
                                                                } else {
                                                                    cdestructuring_bind_error(datum, $list_alt255);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (NIL == found_generalityP) {
                                                set_add(this_parse, good_parses);
                                            }
                                            if (NIL == checked_anyP) {
                                                set_add(this_parse, good_parses);
                                            }
                                        }
                                    }
                                }
                            }
                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                        }
                    } 
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                }
                return set_element_list(good_parses);
            }
        }
    }

    /**
     *
     *
     * @param LISTP
     * 		of (PARSE OBJECT) pairs, e.g., the results of @see parse-noun-compound
     * @return LISTP - said list, minus any subcollection nauts which are genls of other subcollection nauts (in that list)
     * @unknown reduces (SubcollectionOfWithRelationToTypeFn Smuggling {transportees,objectActedOn} Weapon), to just the transportees version
     */
    @LispMethod(comment = "@param LISTP\r\n\t\tof (PARSE OBJECT) pairs, e.g., the results of @see parse-noun-compound\r\n@return LISTP - said list, minus any subcollection nauts which are genls of other subcollection nauts (in that list)\r\n@unknown reduces (SubcollectionOfWithRelationToTypeFn Smuggling {transportees,objectActedOn} Weapon), to just the transportees version")
    public static SubLObject eliminate_genl_subcollection_nauts(final SubLObject parses) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == parses) || (NIL != list_utilities.singletonP(parses))) {
            return parses;
        }
        final SubLObject nats_by_func = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
        final SubLObject good_parses = set.new_set(EQUAL, UNPROVIDED);
        SubLObject cdolist_list_var = parses;
        SubLObject this_parse = NIL;
        this_parse = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != ternary_parsing_subcollection_function_nautP(this_parse.first())) {
                final SubLObject this_func = cycl_utilities.formula_arg0(this_parse.first());
                dictionary.dictionary_enter(nats_by_func, this_func, cons(this_parse, dictionary.dictionary_lookup(nats_by_func, this_func, UNPROVIDED)));
            } else {
                set.set_add(this_parse, good_parses);
            }
            cdolist_list_var = cdolist_list_var.rest();
            this_parse = cdolist_list_var.first();
        } 
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(nats_by_func)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject func = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject parses_$73 = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != list_utilities.singletonP(parses_$73)) {
                set.set_add(parses_$73.first(), good_parses);
            } else {
                SubLObject cdolist_list_var2 = parses_$73;
                SubLObject this_parse2 = NIL;
                this_parse2 = cdolist_list_var2.first();
                while (NIL != cdolist_list_var2) {
                    SubLObject found_generalityP = NIL;
                    SubLObject checked_anyP = NIL;
                    if (NIL == found_generalityP) {
                        SubLObject csome_list_var = parses_$73;
                        SubLObject other_parse = NIL;
                        other_parse = csome_list_var.first();
                        while ((NIL == found_generalityP) && (NIL != csome_list_var)) {
                            if (!other_parse.first().equal(this_parse2.first())) {
                                checked_anyP = T;
                                SubLObject current;
                                final SubLObject datum = current = cycl_utilities.formula_args(this_parse2.first(), UNPROVIDED);
                                SubLObject this_arg1 = NIL;
                                SubLObject this_pred = NIL;
                                SubLObject this_arg2 = NIL;
                                destructuring_bind_must_consp(current, datum, $list252);
                                this_arg1 = current.first();
                                current = current.rest();
                                destructuring_bind_must_consp(current, datum, $list252);
                                this_pred = current.first();
                                current = current.rest();
                                destructuring_bind_must_consp(current, datum, $list252);
                                this_arg2 = current.first();
                                current = current.rest();
                                if (NIL == current) {
                                    SubLObject current_$75;
                                    final SubLObject datum_$74 = current_$75 = cycl_utilities.formula_args(other_parse.first(), UNPROVIDED);
                                    SubLObject other_arg1 = NIL;
                                    SubLObject other_pred = NIL;
                                    SubLObject other_arg2 = NIL;
                                    destructuring_bind_must_consp(current_$75, datum_$74, $list253);
                                    other_arg1 = current_$75.first();
                                    current_$75 = current_$75.rest();
                                    destructuring_bind_must_consp(current_$75, datum_$74, $list253);
                                    other_pred = current_$75.first();
                                    current_$75 = current_$75.rest();
                                    destructuring_bind_must_consp(current_$75, datum_$74, $list253);
                                    other_arg2 = current_$75.first();
                                    current_$75 = current_$75.rest();
                                    if (NIL == current_$75) {
                                        found_generalityP = makeBoolean(((NIL != genl_predicates.genl_predicate_in_any_mtP(this_pred, other_pred)) && (NIL != genls.genl_in_any_mtP(this_arg1, other_arg1))) && (NIL != genls.genl_in_any_mtP(this_arg2, other_arg2)));
                                    } else {
                                        cdestructuring_bind_error(datum_$74, $list253);
                                    }
                                } else {
                                    cdestructuring_bind_error(datum, $list252);
                                }
                            }
                            csome_list_var = csome_list_var.rest();
                            other_parse = csome_list_var.first();
                        } 
                    }
                    if (NIL == found_generalityP) {
                        set.set_add(this_parse2, good_parses);
                    }
                    if (NIL == checked_anyP) {
                        set.set_add(this_parse2, good_parses);
                    }
                    cdolist_list_var2 = cdolist_list_var2.rest();
                    this_parse2 = cdolist_list_var2.first();
                } 
            }
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return set.set_element_list(good_parses);
    }

    public static final SubLObject clear_parse_as_typeP_alt() {
        {
            SubLObject cs = $parse_as_typeP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_parse_as_typeP() {
        final SubLObject cs = $parse_as_typeP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_parse_as_typeP_alt(SubLObject type, SubLObject parse_type, SubLObject mt) {
        return memoization_state.caching_state_remove_function_results_with_args($parse_as_typeP_caching_state$.getGlobalValue(), list(type, parse_type, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_parse_as_typeP(final SubLObject type, final SubLObject parse_type, final SubLObject mt) {
        return memoization_state.caching_state_remove_function_results_with_args($parse_as_typeP_caching_state$.getGlobalValue(), list(type, parse_type, mt), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject parse_as_typeP_internal_alt(SubLObject type, SubLObject parse_type, SubLObject mt) {
        return makeBoolean((NIL == disjoint_with.disjoint_withP(type, parse_type, mt, UNPROVIDED)) && (NIL != cyc_kernel.closed_query_success_result_p(inference_kernel.new_cyc_query(list($$unknownSentence, list($$disjointWith, type, parse_type)), mt, $list_alt260))));
    }

    public static SubLObject parse_as_typeP_internal(final SubLObject type, final SubLObject parse_type, final SubLObject mt) {
        return makeBoolean((NIL == disjoint_with.disjoint_withP(type, parse_type, mt, UNPROVIDED)) && (NIL != cyc_kernel.closed_query_success_result_p(inference_kernel.new_cyc_query(list($$unknownSentence, list($$disjointWith, type, parse_type)), mt, $list257))));
    }

    public static final SubLObject parse_as_typeP_alt(SubLObject type, SubLObject parse_type, SubLObject mt) {
        {
            SubLObject caching_state = $parse_as_typeP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym257$PARSE_AS_TYPE_, $sym261$_PARSE_AS_TYPE__CACHING_STATE_, NIL, EQUAL, THREE_INTEGER, EIGHT_INTEGER);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_3(type, parse_type, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw13$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (type.equal(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (parse_type.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                                            return memoization_state.caching_results(results2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(parse_as_typeP_internal(type, parse_type, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(type, parse_type, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject parse_as_typeP(final SubLObject type, final SubLObject parse_type, final SubLObject mt) {
        SubLObject caching_state = $parse_as_typeP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym254$PARSE_AS_TYPE_, $sym258$_PARSE_AS_TYPE__CACHING_STATE_, NIL, EQUAL, THREE_INTEGER, EIGHT_INTEGER);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_3(type, parse_type, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (type.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (parse_type.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                            return memoization_state.caching_results(results2);
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(parse_as_typeP_internal(type, parse_type, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(type, parse_type, mt));
        return memoization_state.caching_results(results3);
    }

    /**
     * Restrict ALL-PARSES, returning a LISTP of parses all of which are instances of some element
     * in RESTRICTIONS and no element in EXCLUSIONS.
     */
    @LispMethod(comment = "Restrict ALL-PARSES, returning a LISTP of parses all of which are instances of some element\r\nin RESTRICTIONS and no element in EXCLUSIONS.\nRestrict ALL-PARSES, returning a LISTP of parses all of which are instances of some element\nin RESTRICTIONS and no element in EXCLUSIONS.")
    public static final SubLObject restrict_parses_by_types_alt(SubLObject all_parses, SubLObject restrictions, SubLObject exclusions, SubLObject semantic_mt) {
        if (semantic_mt == UNPROVIDED) {
            semantic_mt = $$InferencePSC;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(semantic_mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        {
                            SubLObject cdolist_list_var = all_parses;
                            SubLObject parse = NIL;
                            for (parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parse = cdolist_list_var.first()) {
                                {
                                    SubLObject badP = NIL;
                                    if (NIL == badP) {
                                        {
                                            SubLObject csome_list_var = exclusions;
                                            SubLObject type = NIL;
                                            for (type = csome_list_var.first(); !((NIL != badP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , type = csome_list_var.first()) {
                                                if (NIL != lexicon_accessors.denot_has_typeP(parse, type, UNPROVIDED)) {
                                                    badP = T;
                                                }
                                            }
                                        }
                                    }
                                    if (NIL == badP) {
                                        {
                                            SubLObject goodP = NIL;
                                            if (NIL == goodP) {
                                                {
                                                    SubLObject csome_list_var = restrictions;
                                                    SubLObject type = NIL;
                                                    for (type = csome_list_var.first(); !((NIL != goodP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , type = csome_list_var.first()) {
                                                        if (NIL != lexicon_accessors.denot_has_typeP(parse, type, UNPROVIDED)) {
                                                            goodP = T;
                                                        }
                                                    }
                                                }
                                            }
                                            if (NIL != goodP) {
                                                result = cons(parse, result);
                                            } else {
                                                {
                                                    SubLObject cdolist_list_var_80 = restrictions;
                                                    SubLObject type = NIL;
                                                    for (type = cdolist_list_var_80.first(); NIL != cdolist_list_var_80; cdolist_list_var_80 = cdolist_list_var_80.rest() , type = cdolist_list_var_80.first()) {
                                                        {
                                                            SubLObject restricted = restrict_parse_to_type(parse, type);
                                                            if (NIL != restricted) {
                                                                result = cons(restricted, result);
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

    @LispMethod(comment = "Restrict ALL-PARSES, returning a LISTP of parses all of which are instances of some element\r\nin RESTRICTIONS and no element in EXCLUSIONS.\nRestrict ALL-PARSES, returning a LISTP of parses all of which are instances of some element\nin RESTRICTIONS and no element in EXCLUSIONS.")
    public static SubLObject restrict_parses_by_types(final SubLObject all_parses, final SubLObject restrictions, final SubLObject exclusions, SubLObject semantic_mt) {
        if (semantic_mt == UNPROVIDED) {
            semantic_mt = $$InferencePSC;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(semantic_mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            SubLObject cdolist_list_var = all_parses;
            SubLObject parse = NIL;
            parse = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject badP = NIL;
                if (NIL == badP) {
                    SubLObject csome_list_var = exclusions;
                    SubLObject type = NIL;
                    type = csome_list_var.first();
                    while ((NIL == badP) && (NIL != csome_list_var)) {
                        if (NIL != lexicon_accessors.denot_has_typeP(parse, type, UNPROVIDED)) {
                            badP = T;
                        }
                        csome_list_var = csome_list_var.rest();
                        type = csome_list_var.first();
                    } 
                }
                if (NIL == badP) {
                    SubLObject goodP = NIL;
                    if (NIL == goodP) {
                        SubLObject csome_list_var2 = restrictions;
                        SubLObject type2 = NIL;
                        type2 = csome_list_var2.first();
                        while ((NIL == goodP) && (NIL != csome_list_var2)) {
                            if (NIL != lexicon_accessors.denot_has_typeP(parse, type2, UNPROVIDED)) {
                                goodP = T;
                            }
                            csome_list_var2 = csome_list_var2.rest();
                            type2 = csome_list_var2.first();
                        } 
                    }
                    if (NIL != goodP) {
                        result = cons(parse, result);
                    } else {
                        SubLObject cdolist_list_var_$76 = restrictions;
                        SubLObject type2 = NIL;
                        type2 = cdolist_list_var_$76.first();
                        while (NIL != cdolist_list_var_$76) {
                            final SubLObject restricted = restrict_parse_to_type(parse, type2);
                            if (NIL != restricted) {
                                result = cons(restricted, result);
                            }
                            cdolist_list_var_$76 = cdolist_list_var_$76.rest();
                            type2 = cdolist_list_var_$76.first();
                        } 
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                parse = cdolist_list_var.first();
            } 
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return result;
    }/**
     * Restrict ALL-PARSES, returning a LISTP of parses all of which are instances of some element
     * in RESTRICTIONS and no element in EXCLUSIONS.
     */


    /**
     * Try to return a restricted form of PARSE that satisfies TYPE.
     */
    @LispMethod(comment = "Try to return a restricted form of PARSE that satisfies TYPE.")
    public static final SubLObject restrict_parse_to_type_alt(SubLObject parse, SubLObject type) {
        if (NIL != el_formula_with_operator_p(parse, $$Unity)) {
            {
                SubLObject restricted = replace_formula_arg(ZERO_INTEGER, $$IntervalRangeFn, parse);
                if (NIL != lexicon_accessors.denot_has_typeP(restricted, type, UNPROVIDED)) {
                    return restricted;
                }
            }
        }
        return NIL;
    }

    @LispMethod(comment = "Try to return a restricted form of PARSE that satisfies TYPE.")
    public static SubLObject restrict_parse_to_type(final SubLObject parse, final SubLObject type) {
        if (NIL != el_formula_with_operator_p(parse, $$Unity)) {
            final SubLObject restricted = replace_formula_arg(ZERO_INTEGER, $$IntervalRangeFn, parse);
            if (NIL != lexicon_accessors.denot_has_typeP(restricted, type, UNPROVIDED)) {
                return restricted;
            }
        }
        return NIL;
    }/**
     * Try to return a restricted form of PARSE that satisfies TYPE.
     */


    /**
     *
     *
     * @param STRINGP
     * 		to be parsed
     * @param listp;
     * 		types which the parse can match (list treated as a disjunction)
     * @param listp;
     * 		types which the parse can't match (a disjunction)
     * @param MT
     * 		; optional, restrict the parsing to this mt
     * @return LISTP of valid parses, if any
     * @unknown This function presently groks only #$ScalarInterval #$Date, and their specs, hence the name
     * @unknown This function uses @see parse-as-type?, which is cached, for its type-checking
     */
    @LispMethod(comment = "@param STRINGP\r\n\t\tto be parsed\r\n@param listp;\r\n\t\ttypes which the parse can match (list treated as a disjunction)\r\n@param listp;\r\n\t\ttypes which the parse can\'t match (a disjunction)\r\n@param MT\r\n\t\t; optional, restrict the parsing to this mt\r\n@return LISTP of valid parses, if any\r\n@unknown This function presently groks only #$ScalarInterval #$Date, and their specs, hence the name\r\n@unknown This function uses @see parse-as-type?, which is cached, for its type-checking")
    public static final SubLObject parse_dates_and_numbers_alt(SubLObject string, SubLObject can_be, SubLObject cant_be, SubLObject semantic_mt, SubLObject parsing_mt) {
        if (semantic_mt == UNPROVIDED) {
            semantic_mt = NIL;
        }
        if (parsing_mt == UNPROVIDED) {
            parsing_mt = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject all_parses = NIL;
                SubLObject mt = (NIL != semantic_mt) ? ((SubLObject) (semantic_mt)) : $$InferencePSC;
                SubLObject date_parses = NIL;
                if (NIL == date_parses) {
                    {
                        SubLObject csome_list_var = can_be;
                        SubLObject type = NIL;
                        for (type = csome_list_var.first(); !((NIL != date_parses) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , type = csome_list_var.first()) {
                            if (NIL != parse_as_typeP(type, $$Date, mt)) {
                                date_parses = date_utilities.parse_date_from_string(string);
                            }
                        }
                    }
                }
                if (NIL != date_parses) {
                    all_parses = remove_duplicates(date_parses, EQUAL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                }
                {
                    SubLObject _prev_bind_0 = parsing_vars.$npp_use_nl_tagsP$.currentBinding(thread);
                    SubLObject _prev_bind_1 = parsing_vars.$psp_return_mode$.currentBinding(thread);
                    SubLObject _prev_bind_2 = parsing_vars.$psp_reformulateP$.currentBinding(thread);
                    try {
                        parsing_vars.$npp_use_nl_tagsP$.bind(NIL, thread);
                        parsing_vars.$psp_return_mode$.bind($BEST_ONLY, thread);
                        parsing_vars.$psp_reformulateP$.bind(NIL, thread);
                        {
                            SubLObject parse_as_numberP = NIL;
                            SubLObject number_collection = $$ScalarInterval;
                            SubLObject number_parses = NIL;
                            if (NIL == parse_as_numberP) {
                                {
                                    SubLObject csome_list_var = can_be;
                                    SubLObject type = NIL;
                                    for (type = csome_list_var.first(); !((NIL != parse_as_numberP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , type = csome_list_var.first()) {
                                        parse_as_numberP = parse_as_typeP(type, number_collection, mt);
                                    }
                                }
                            }
                            if (NIL != parse_as_numberP) {
                                {
                                    SubLObject standard_string = string_utilities.replace_substring(string, $str_alt265$_, $str_alt199$);
                                    number_parses = append(english_quantity_parser.string_to_quantities(standard_string), psp_main.ps_get_cycls_for_np(standard_string, UNPROVIDED, UNPROVIDED, UNPROVIDED), psp_main.ps_get_cycls_for_phrase(standard_string, $list_alt266, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                                }
                            }
                            number_parses = restrict_parses_by_types(number_parses, list(number_collection), NIL, mt);
                            {
                                SubLObject cdolist_list_var = number_parses;
                                SubLObject parse = NIL;
                                for (parse = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parse = cdolist_list_var.first()) {
                                    {
                                        SubLObject item_var = parse;
                                        if (NIL == member(item_var, all_parses, EQUAL, symbol_function(IDENTITY))) {
                                            all_parses = cons(item_var, all_parses);
                                        }
                                    }
                                }
                            }
                        }
                    } finally {
                        parsing_vars.$psp_reformulateP$.rebind(_prev_bind_2, thread);
                        parsing_vars.$psp_return_mode$.rebind(_prev_bind_1, thread);
                        parsing_vars.$npp_use_nl_tagsP$.rebind(_prev_bind_0, thread);
                    }
                }
                return restrict_parses_by_types(all_parses, can_be, cant_be, mt);
            }
        }
    }

    /**
     *
     *
     * @param STRINGP
     * 		to be parsed
     * @param listp;
     * 		types which the parse can match (list treated as a disjunction)
     * @param listp;
     * 		types which the parse can't match (a disjunction)
     * @param MT
     * 		; optional, restrict the parsing to this mt
     * @return LISTP of valid parses, if any
     * @unknown This function presently groks only #$ScalarInterval #$Date, and their specs, hence the name
     * @unknown This function uses @see parse-as-type?, which is cached, for its type-checking
     */
    @LispMethod(comment = "@param STRINGP\r\n\t\tto be parsed\r\n@param listp;\r\n\t\ttypes which the parse can match (list treated as a disjunction)\r\n@param listp;\r\n\t\ttypes which the parse can\'t match (a disjunction)\r\n@param MT\r\n\t\t; optional, restrict the parsing to this mt\r\n@return LISTP of valid parses, if any\r\n@unknown This function presently groks only #$ScalarInterval #$Date, and their specs, hence the name\r\n@unknown This function uses @see parse-as-type?, which is cached, for its type-checking")
    public static SubLObject parse_dates_and_numbers(final SubLObject string, final SubLObject can_be, final SubLObject cant_be, SubLObject semantic_mt, SubLObject parsing_mt) {
        if (semantic_mt == UNPROVIDED) {
            semantic_mt = NIL;
        }
        if (parsing_mt == UNPROVIDED) {
            parsing_mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject all_parses = NIL;
        final SubLObject mt = (NIL != semantic_mt) ? semantic_mt : $$InferencePSC;
        SubLObject date_parses = NIL;
        if (NIL == date_parses) {
            SubLObject csome_list_var = can_be;
            SubLObject type = NIL;
            type = csome_list_var.first();
            while ((NIL == date_parses) && (NIL != csome_list_var)) {
                if (NIL != parse_as_typeP(type, $$Date, mt)) {
                    date_parses = date_utilities.parse_date_from_string(string);
                }
                csome_list_var = csome_list_var.rest();
                type = csome_list_var.first();
            } 
        }
        if (NIL != date_parses) {
            all_parses = remove_duplicates(date_parses, EQUAL, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        final SubLObject _prev_bind_0 = parsing_vars.$npp_use_nl_tagsP$.currentBinding(thread);
        final SubLObject _prev_bind_2 = parsing_vars.$psp_return_mode$.currentBinding(thread);
        final SubLObject _prev_bind_3 = parsing_vars.$psp_reformulateP$.currentBinding(thread);
        try {
            parsing_vars.$npp_use_nl_tagsP$.bind(NIL, thread);
            parsing_vars.$psp_return_mode$.bind($BEST_ONLY, thread);
            parsing_vars.$psp_reformulateP$.bind(NIL, thread);
            SubLObject parse_as_numberP = NIL;
            final SubLObject number_collection = $$ScalarInterval;
            SubLObject number_parses = NIL;
            if (NIL == parse_as_numberP) {
                SubLObject csome_list_var2;
                SubLObject type2;
                for (csome_list_var2 = can_be, type2 = NIL, type2 = csome_list_var2.first(); (NIL == parse_as_numberP) && (NIL != csome_list_var2); parse_as_numberP = parse_as_typeP(type2, number_collection, mt) , csome_list_var2 = csome_list_var2.rest() , type2 = csome_list_var2.first()) {
                }
            }
            if (NIL != parse_as_numberP) {
                final SubLObject standard_string = string_utilities.replace_substring(string, $str262$_, $str196$);
                number_parses = append(english_quantity_parser.string_to_quantities(standard_string), psp_main.ps_get_cycls_for_np(standard_string, UNPROVIDED, UNPROVIDED, UNPROVIDED), psp_main.ps_get_cycls_for_phrase(standard_string, $list263, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            }
            SubLObject cdolist_list_var;
            number_parses = cdolist_list_var = restrict_parses_by_types(number_parses, list(number_collection), NIL, mt);
            SubLObject parse = NIL;
            parse = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject item_var = parse;
                if (NIL == member(item_var, all_parses, EQUAL, symbol_function(IDENTITY))) {
                    all_parses = cons(item_var, all_parses);
                }
                cdolist_list_var = cdolist_list_var.rest();
                parse = cdolist_list_var.first();
            } 
        } finally {
            parsing_vars.$psp_reformulateP$.rebind(_prev_bind_3, thread);
            parsing_vars.$psp_return_mode$.rebind(_prev_bind_2, thread);
            parsing_vars.$npp_use_nl_tagsP$.rebind(_prev_bind_0, thread);
        }
        return restrict_parses_by_types(all_parses, can_be, cant_be, mt);
    }

    public static final SubLObject parse_iterator_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        print_parse_iterator(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject parse_iterator_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        print_parse_iterator(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject parse_iterator_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject parse_iterator_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native.class ? T : NIL;
    }

    public static final SubLObject pi_sub_iterators_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.getField2();
    }

    public static SubLObject pi_sub_iterators(final SubLObject v_object) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject pi_properties_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.getField3();
    }

    public static SubLObject pi_properties(final SubLObject v_object) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject pi_current_iterator_index_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.getField4();
    }

    public static SubLObject pi_current_iterator_index(final SubLObject v_object) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject pi_string_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.getField5();
    }

    public static SubLObject pi_string(final SubLObject v_object) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject pi_previous_parses_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.getField6();
    }

    public static SubLObject pi_previous_parses(final SubLObject v_object) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject pi_next_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.getField7();
    }

    public static SubLObject pi_next(final SubLObject v_object) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject _csetf_pi_sub_iterators_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_pi_sub_iterators(final SubLObject v_object, final SubLObject value) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_pi_properties_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_pi_properties(final SubLObject v_object, final SubLObject value) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_pi_current_iterator_index_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_pi_current_iterator_index(final SubLObject v_object, final SubLObject value) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_pi_string_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_pi_string(final SubLObject v_object, final SubLObject value) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_pi_previous_parses_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_pi_previous_parses(final SubLObject v_object, final SubLObject value) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_pi_next_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PARSE_ITERATOR_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_pi_next(final SubLObject v_object, final SubLObject value) {
        assert NIL != parse_iterator_p(v_object) : "! parsing_utilities.parse_iterator_p(v_object) " + "parsing_utilities.parse_iterator_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject make_parse_iterator_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($SUB_ITERATORS)) {
                        _csetf_pi_sub_iterators(v_new, current_value);
                    } else {
                        if (pcase_var.eql($PROPERTIES)) {
                            _csetf_pi_properties(v_new, current_value);
                        } else {
                            if (pcase_var.eql($CURRENT_ITERATOR_INDEX)) {
                                _csetf_pi_current_iterator_index(v_new, current_value);
                            } else {
                                if (pcase_var.eql($STRING)) {
                                    _csetf_pi_string(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($PREVIOUS_PARSES)) {
                                        _csetf_pi_previous_parses(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($NEXT)) {
                                            _csetf_pi_next(v_new, current_value);
                                        } else {
                                            Errors.error($str_alt293$Invalid_slot__S_for_construction_, current_arg);
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

    public static SubLObject make_parse_iterator(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($SUB_ITERATORS)) {
                _csetf_pi_sub_iterators(v_new, current_value);
            } else
                if (pcase_var.eql($PROPERTIES)) {
                    _csetf_pi_properties(v_new, current_value);
                } else
                    if (pcase_var.eql($CURRENT_ITERATOR_INDEX)) {
                        _csetf_pi_current_iterator_index(v_new, current_value);
                    } else
                        if (pcase_var.eql($STRING)) {
                            _csetf_pi_string(v_new, current_value);
                        } else
                            if (pcase_var.eql($PREVIOUS_PARSES)) {
                                _csetf_pi_previous_parses(v_new, current_value);
                            } else
                                if (pcase_var.eql($NEXT)) {
                                    _csetf_pi_next(v_new, current_value);
                                } else {
                                    Errors.error($str291$Invalid_slot__S_for_construction_, current_arg);
                                }





        }
        return v_new;
    }

    public static SubLObject visit_defstruct_parse_iterator(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_PARSE_ITERATOR, SIX_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $SUB_ITERATORS, pi_sub_iterators(obj));
        funcall(visitor_fn, obj, $SLOT, $PROPERTIES, pi_properties(obj));
        funcall(visitor_fn, obj, $SLOT, $CURRENT_ITERATOR_INDEX, pi_current_iterator_index(obj));
        funcall(visitor_fn, obj, $SLOT, $STRING, pi_string(obj));
        funcall(visitor_fn, obj, $SLOT, $PREVIOUS_PARSES, pi_previous_parses(obj));
        funcall(visitor_fn, obj, $SLOT, $NEXT, pi_next(obj));
        funcall(visitor_fn, obj, $END, MAKE_PARSE_ITERATOR, SIX_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_parse_iterator_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_parse_iterator(obj, visitor_fn);
    }

    public static final SubLObject print_parse_iterator_alt(SubLObject iter, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        format(stream, $str_alt294$__parse_iterator__S_current_iter_, new SubLObject[]{ pi_string(iter), pi_current_iterator_index(iter), pi_sub_iterators(iter), pi_properties(iter) });
        return NIL;
    }

    public static SubLObject print_parse_iterator(final SubLObject iter, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        format(stream, $str297$__parse_iterator__S_current_iter_, new SubLObject[]{ pi_string(iter), pi_current_iterator_index(iter), pi_sub_iterators(iter), pi_properties(iter) });
        return NIL;
    }

    /**
     *
     *
     * @param STRING
    stringp;
     * 		
     * @param PROPERTIES
     * 		property-list-p;
     * 		create a high-level parse iterator for STRING, set with appropriate PROPERTIES
     */
    @LispMethod(comment = "@param STRING\nstringp;\r\n\t\t\r\n@param PROPERTIES\r\n\t\tproperty-list-p;\r\n\t\tcreate a high-level parse iterator for STRING, set with appropriate PROPERTIES")
    public static final SubLObject new_parse_iterator_alt(SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return iteration.new_iterator(new_parse_iterator_state(string, v_properties), PARSE_ITERATOR_DONE, PARSE_ITERATOR_NEXT, UNPROVIDED);
    }

    /**
     *
     *
     * @param STRING
    stringp;
     * 		
     * @param PROPERTIES
     * 		property-list-p;
     * 		create a high-level parse iterator for STRING, set with appropriate PROPERTIES
     */
    @LispMethod(comment = "@param STRING\nstringp;\r\n\t\t\r\n@param PROPERTIES\r\n\t\tproperty-list-p;\r\n\t\tcreate a high-level parse iterator for STRING, set with appropriate PROPERTIES")
    public static SubLObject new_parse_iterator(final SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return iteration.new_iterator(new_parse_iterator_state(string, v_properties), PARSE_ITERATOR_DONE, PARSE_ITERATOR_NEXT, UNPROVIDED);
    }

    public static final SubLObject new_parse_iterator_state_alt(SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        {
            SubLObject iter = make_parse_iterator(UNPROVIDED);
            _csetf_pi_properties(iter, v_properties);
            _csetf_pi_string(iter, string);
            _csetf_pi_current_iterator_index(iter, ZERO_INTEGER);
            _csetf_pi_sub_iterators(iter, parsing_subiterators(string, v_properties));
            _csetf_pi_next(iter, NIL);
            return iter;
        }
    }

    public static SubLObject new_parse_iterator_state(final SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        final SubLObject iter = make_parse_iterator(UNPROVIDED);
        _csetf_pi_properties(iter, v_properties);
        _csetf_pi_string(iter, string);
        _csetf_pi_current_iterator_index(iter, ZERO_INTEGER);
        _csetf_pi_sub_iterators(iter, parsing_subiterators(string, v_properties));
        _csetf_pi_next(iter, NIL);
        return iter;
    }

    /**
     *
     *
     * @return list; a list containing iterators for all the parsers specified in the :parsers property of PROPERTIES
     */
    @LispMethod(comment = "@return list; a list containing iterators for all the parsers specified in the :parsers property of PROPERTIES")
    public static final SubLObject parsing_subiterators_alt(SubLObject string, SubLObject v_properties) {
        {
            SubLObject iterators = NIL;
            SubLObject parsers = getf(v_properties, $PARSERS, $list_alt298);
            SubLObject cdolist_list_var = parsers;
            SubLObject parser_symbol = NIL;
            for (parser_symbol = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , parser_symbol = cdolist_list_var.first()) {
                {
                    SubLObject pcase_var = parser_symbol;
                    if (pcase_var.eql($$CycRecursiveTemplateParser)) {
                        iterators = cons(rtp_iterators.new_rtp_iterator(string, v_properties), iterators);
                    } else {
                        if (pcase_var.eql($$CharniakParserCyclifier)) {
                            {
                                SubLObject cyclify_iter = new_cyclify_iterator(string, v_properties);
                                if (NIL != cyclify_iterator_p(cyclify_iter)) {
                                    iterators = cons(cyclify_iter, iterators);
                                }
                            }
                        }
                    }
                }
            }
            return nreverse(iterators);
        }
    }

    /**
     *
     *
     * @return list; a list containing iterators for all the parsers specified in the :parsers property of PROPERTIES
     */
    @LispMethod(comment = "@return list; a list containing iterators for all the parsers specified in the :parsers property of PROPERTIES")
    public static SubLObject parsing_subiterators(final SubLObject string, final SubLObject v_properties) {
        SubLObject iterators = NIL;
        SubLObject cdolist_list_var;
        final SubLObject parsers = cdolist_list_var = getf(v_properties, $PARSERS, $list301);
        SubLObject parser_symbol = NIL;
        parser_symbol = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject pcase_var = parser_symbol;
            if (pcase_var.eql($$CycRecursiveTemplateParser)) {
                iterators = cons(rtp_iterators.new_rtp_iterator(string, v_properties), iterators);
            } else
                if (pcase_var.eql($$CharniakParserCyclifier)) {
                    final SubLObject cyclify_iter = new_cyclify_iterator(string, v_properties);
                    if (NIL != cyclify_iterator_p(cyclify_iter)) {
                        iterators = cons(cyclify_iter, iterators);
                    }
                }

            cdolist_list_var = cdolist_list_var.rest();
            parser_symbol = cdolist_list_var.first();
        } 
        return nreverse(iterators);
    }

    public static final SubLObject pi_current_iterator_alt(SubLObject iter) {
        return nth(pi_current_iterator_index(iter), pi_sub_iterators(iter));
    }

    public static SubLObject pi_current_iterator(final SubLObject iter) {
        return nth(pi_current_iterator_index(iter), pi_sub_iterators(iter));
    }

    public static final SubLObject parse_iterator_done_alt(SubLObject iter) {
        return makeBoolean(NIL == parse_iterator_has_next(iter));
    }

    public static SubLObject parse_iterator_done(final SubLObject iter) {
        return makeBoolean(NIL == parse_iterator_has_next(iter));
    }

    public static final SubLObject parse_iterator_has_next_alt(SubLObject iter) {
        {
            SubLObject current_iter = pi_current_iterator(iter);
            SubLObject failedP = NIL;
            while (!(((NIL != pi_next(iter)) || (NIL != failedP)) || (NIL == current_iter))) {
                {
                    SubLObject current_iter_done = iteration.iteration_done(current_iter);
                    if ((NIL != current_iter_done) && pi_current_iterator_index(iter).numG(length(pi_sub_iterators(iter)))) {
                        failedP = T;
                    } else {
                        if (NIL != current_iter_done) {
                            _csetf_pi_current_iterator_index(iter, number_utilities.f_1X(pi_current_iterator_index(iter)));
                            current_iter_done = NIL;
                            current_iter = pi_current_iterator(iter);
                        } else {
                            {
                                SubLObject next_val = iteration.iteration_next(current_iter);
                                if (NIL == subl_promotions.memberP(next_val, pi_previous_parses(iter), $sym301$EQUALS_EL_, UNPROVIDED)) {
                                    _csetf_pi_next(iter, cons(next_val, pi_next(iter)));
                                }
                            }
                        }
                    }
                }
            } 
        }
        return list_utilities.sublisp_boolean(pi_next(iter));
    }

    public static SubLObject parse_iterator_has_next(final SubLObject iter) {
        SubLObject current_iter = pi_current_iterator(iter);
        SubLObject failedP = NIL;
        while (((NIL == pi_next(iter)) && (NIL == failedP)) && (NIL != current_iter)) {
            SubLObject current_iter_done = iteration.iteration_done(current_iter);
            if ((NIL != current_iter_done) && pi_current_iterator_index(iter).numG(length(pi_sub_iterators(iter)))) {
                failedP = T;
            } else
                if (NIL != current_iter_done) {
                    _csetf_pi_current_iterator_index(iter, number_utilities.f_1X(pi_current_iterator_index(iter)));
                    current_iter_done = NIL;
                    current_iter = pi_current_iterator(iter);
                } else {
                    final SubLObject next_val = iteration.iteration_next(current_iter);
                    if (NIL != subl_promotions.memberP(next_val, pi_previous_parses(iter), $sym304$EQUALS_EL_, UNPROVIDED)) {
                        continue;
                    }
                    _csetf_pi_next(iter, cons(next_val, pi_next(iter)));
                }

        } 
        return list_utilities.sublisp_boolean(pi_next(iter));
    }

    public static final SubLObject parse_iterator_next_alt(SubLObject iter) {
        if (NIL == parse_iterator_has_next(iter)) {
            return $FAILURE;
        }
        {
            SubLObject next_val = pi_next(iter).first();
            _csetf_pi_previous_parses(iter, cons(next_val, pi_previous_parses(iter)));
            _csetf_pi_next(iter, pi_next(iter).rest());
            return values(next_val, iter, NIL);
        }
    }

    public static SubLObject parse_iterator_next(final SubLObject iter) {
        if (NIL == parse_iterator_has_next(iter)) {
            return $FAILURE;
        }
        final SubLObject next_val = pi_next(iter).first();
        _csetf_pi_previous_parses(iter, cons(next_val, pi_previous_parses(iter)));
        _csetf_pi_next(iter, pi_next(iter).rest());
        return values(next_val, iter, NIL);
    }

    public static final SubLObject cyclify_iterator_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject cyclify_iterator_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject cyclify_iterator_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.parsing_utilities.$cyclify_iterator_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject cyclify_iterator_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.parsing_utilities.$cyclify_iterator_native.class ? T : NIL;
    }

    public static final SubLObject cyclify_iterator_iter_obj_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, CYCLIFY_ITERATOR_P);
        return v_object.getField2();
    }

    public static SubLObject cyclify_iterator_iter_obj(final SubLObject v_object) {
        assert NIL != cyclify_iterator_p(v_object) : "! parsing_utilities.cyclify_iterator_p(v_object) " + "parsing_utilities.cyclify_iterator_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject _csetf_cyclify_iterator_iter_obj_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, CYCLIFY_ITERATOR_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_cyclify_iterator_iter_obj(final SubLObject v_object, final SubLObject value) {
        assert NIL != cyclify_iterator_p(v_object) : "! parsing_utilities.cyclify_iterator_p(v_object) " + "parsing_utilities.cyclify_iterator_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject make_cyclify_iterator_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.parsing_utilities.$cyclify_iterator_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($ITER_OBJ)) {
                        _csetf_cyclify_iterator_iter_obj(v_new, current_value);
                    } else {
                        Errors.error($str_alt293$Invalid_slot__S_for_construction_, current_arg);
                    }
                }
            }
            return v_new;
        }
    }

    public static SubLObject make_cyclify_iterator(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.parsing_utilities.$cyclify_iterator_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($ITER_OBJ)) {
                _csetf_cyclify_iterator_iter_obj(v_new, current_value);
            } else {
                Errors.error($str291$Invalid_slot__S_for_construction_, current_arg);
            }
        }
        return v_new;
    }

    public static SubLObject visit_defstruct_cyclify_iterator(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_CYCLIFY_ITERATOR, ONE_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $ITER_OBJ, cyclify_iterator_iter_obj(obj));
        funcall(visitor_fn, obj, $END, MAKE_CYCLIFY_ITERATOR, ONE_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_cyclify_iterator_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_cyclify_iterator(obj, visitor_fn);
    }

    public static final SubLObject new_cyclify_iterator_alt(SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return iteration.new_iterator(new_cyclify_iterator_state(string, v_properties), CYCLIFY_ITERATOR_DONE, CYCLIFY_ITERATOR_NEXT, UNPROVIDED);
    }

    public static SubLObject new_cyclify_iterator(final SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        return iteration.new_iterator(new_cyclify_iterator_state(string, v_properties), CYCLIFY_ITERATOR_DONE, CYCLIFY_ITERATOR_NEXT, UNPROVIDED);
    }

    public static final SubLObject new_cyclify_iterator_state_alt(SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        {
            SubLObject iter = make_cyclify_iterator(UNPROVIDED);
            SubLObject v_cyclifier = cyclifier.new_cyclifier(string, v_properties);
            if (NIL != cyclifier.cyclifier_p(v_cyclifier)) {
                _csetf_cyclify_iterator_iter_obj(iter, v_cyclifier);
                return iter;
            }
        }
        return $FAIL;
    }

    public static SubLObject new_cyclify_iterator_state(final SubLObject string, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        final SubLObject iter = make_cyclify_iterator(UNPROVIDED);
        final SubLObject v_cyclifier = cyclifier.new_cyclifier(string, v_properties);
        if (NIL != cyclifier.cyclifier_p(v_cyclifier)) {
            _csetf_cyclify_iterator_iter_obj(iter, v_cyclifier);
            return iter;
        }
        return $FAIL;
    }

    public static final SubLObject cyclify_iterator_done_alt(SubLObject iter) {
        if (NIL != cyclify_iterator_p(iter)) {
            {
                SubLObject internal_iter = cyclify_iterator_iter_obj(iter);
                return makeBoolean(NIL == methods.funcall_instance_method_with_0_args(internal_iter, $sym317$HAS_NEXT_));
            }
        }
        return T;
    }

    public static SubLObject cyclify_iterator_done(final SubLObject iter) {
        if (NIL != cyclify_iterator_p(iter)) {
            final SubLObject internal_iter = cyclify_iterator_iter_obj(iter);
            return makeBoolean(NIL == methods.funcall_instance_method_with_0_args(internal_iter, $sym323$HAS_NEXT_));
        }
        return T;
    }

    public static final SubLObject cyclify_iterator_next_alt(SubLObject iter) {
        if (NIL != cyclify_iterator_p(iter)) {
            {
                SubLObject internal_iter = cyclify_iterator_iter_obj(iter);
                if (NIL != internal_iter) {
                    return values(methods.funcall_instance_method_with_0_args(internal_iter, NEXT), iter, NIL);
                }
            }
        }
        return NIL;
    }

    public static SubLObject cyclify_iterator_next(final SubLObject iter) {
        if (NIL != cyclify_iterator_p(iter)) {
            final SubLObject internal_iter = cyclify_iterator_iter_obj(iter);
            if (NIL != internal_iter) {
                return values(methods.funcall_instance_method_with_0_args(internal_iter, NEXT), iter, NIL);
            }
        }
        return NIL;
    }

    /**
     * part of an evaluation-defn that is here because of macro-ordering issues
     */
    @LispMethod(comment = "part of an evaluation-defn that is here because of macro-ordering issues")
    public static final SubLObject complete_text_topic_structure_tagging_alt(SubLObject iter) {
        {
            SubLObject paragraph_result = NIL;
            while (NIL == iteration.iteration_done(iter)) {
                {
                    SubLObject sentence = iteration.iteration_next(iter);
                    SubLObject sentence_result = NIL;
                    SubLObject vector_var = document.sentence_yield(sentence);
                    SubLObject backwardP_var = NIL;
                    SubLObject length = length(vector_var);
                    SubLObject v_iteration = NIL;
                    for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                        {
                            SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                            SubLObject word = aref(vector_var, element_num);
                            sentence_result = cons(make_el_list(list(document.word_string(word), NIL != document.word_cyc_pos(word) ? ((SubLObject) (document.word_cyc_pos(word))) : $$NLWordForm, make_el_set(nl_api_datastructures.cycls_from_nl_interps(document.word_interps(word)), UNPROVIDED)), UNPROVIDED), sentence_result);
                        }
                    }
                    paragraph_result = cons(make_el_list(nreverse(sentence_result), UNPROVIDED), paragraph_result);
                }
            } 
            return make_el_list(nreverse(paragraph_result), UNPROVIDED);
        }
    }

    @LispMethod(comment = "part of an evaluation-defn that is here because of macro-ordering issues")
    public static SubLObject complete_text_topic_structure_tagging(final SubLObject iter) {
        SubLObject paragraph_result = NIL;
        while (NIL == iteration.iteration_done(iter)) {
            final SubLObject sentence = iteration.iteration_next(iter);
            SubLObject sentence_result = NIL;
            SubLObject cdolist_list_var = document.sentence_yield_exhaustive(sentence);
            SubLObject word = NIL;
            word = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                sentence_result = cons(make_el_list(list(document.word_string(word), NIL != document.word_cyc_pos(word) ? document.word_cyc_pos(word) : $$NLWordForm, make_el_set(nl_api_datastructures.cycls_from_nl_interps(document.word_interps(word)), UNPROVIDED)), UNPROVIDED), sentence_result);
                cdolist_list_var = cdolist_list_var.rest();
                word = cdolist_list_var.first();
            } 
            paragraph_result = cons(make_el_list(nreverse(sentence_result), UNPROVIDED), paragraph_result);
        } 
        return make_el_list(nreverse(paragraph_result), UNPROVIDED);
    }/**
     * part of an evaluation-defn that is here because of macro-ordering issues
     */


    public static final SubLObject speaker_referent_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return NIL != parsing_vars.$speaker_referent$.getDynamicValue(thread) ? ((SubLObject) (parsing_vars.$speaker_referent$.getDynamicValue(thread))) : api_control_vars.$the_cyclist$.getDynamicValue(thread);
        }
    }

    public static SubLObject speaker_referent() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return NIL != parsing_vars.$speaker_referent$.getDynamicValue(thread) ? parsing_vars.$speaker_referent$.getDynamicValue(thread) : api_control_vars.$the_cyclist$.getDynamicValue(thread);
    }

    public static final SubLObject get_cyclifier_interps_for_expression_peg_alt(SubLObject peg) {
        {
            SubLObject ep_string = peg_text(peg);
            return get_cyclifier_interps_for_string(ep_string, NIL, peg, NIL, $L2R);
        }
    }

    public static SubLObject get_cyclifier_interps_for_expression_peg(final SubLObject peg) {
        final SubLObject ep_string = peg_text(peg);
        return get_cyclifier_interps_for_string(ep_string, NIL, peg, NIL, $L2R);
    }

    public static final SubLObject peg_text_alt(SubLObject peg) {
        {
            SubLObject text = ask_utilities.query_variable($sym321$_STRING, listS($$originalPhrase, peg, $list_alt323), $$InferencePSC, UNPROVIDED).first();
            return text;
        }
    }

    public static SubLObject peg_text(final SubLObject peg) {
        final SubLObject text = ask_utilities.query_variable($sym327$_STRING, listS($$originalPhrase, peg, $list329), $$InferencePSC, UNPROVIDED).first();
        return text;
    }

    public static final SubLObject get_cyclifier_interps_for_string_alt(SubLObject sentence, SubLObject background_mt, SubLObject peg, SubLObject ephemeralP, SubLObject application) {
        if (background_mt == UNPROVIDED) {
            background_mt = $const324$TextualEntailmentGenericBackgroun;
        }
        if (peg == UNPROVIDED) {
            peg = NIL;
        }
        if (ephemeralP == UNPROVIDED) {
            ephemeralP = T;
        }
        if (application == UNPROVIDED) {
            application = $TEXTUAL_INFERENCE;
        }
        {
            SubLObject content_model_mt = NIL;
            if (NIL != peg) {
                content_model_mt = ask_utilities.query_variable($MT, list($$contentModelFocalPeg, $MT, peg), $$InferencePSC, $list_alt327).first();
            }
            if (NIL == content_model_mt) {
                content_model_mt = textual_entailments.create_cyclifier_content_model(sentence, background_mt, ephemeralP, application);
            }
            textual_entailments.update_interps_on_model(content_model_mt, sentence, peg, ephemeralP, application, UNPROVIDED);
            return content_model_mt;
        }
    }

    public static SubLObject get_cyclifier_interps_for_string(final SubLObject sentence, SubLObject background_mt, SubLObject peg, SubLObject ephemeralP, SubLObject application) {
        if (background_mt == UNPROVIDED) {
            background_mt = $const330$TextualEntailmentGenericBackgroun;
        }
        if (peg == UNPROVIDED) {
            peg = NIL;
        }
        if (ephemeralP == UNPROVIDED) {
            ephemeralP = T;
        }
        if (application == UNPROVIDED) {
            application = $TEXTUAL_INFERENCE;
        }
        SubLObject content_model_mt = NIL;
        if (NIL != peg) {
            content_model_mt = ask_utilities.query_variable($MT, list($$contentModelFocalPeg, $MT, peg), $$InferencePSC, $list333).first();
        }
        return content_model_mt;
    }

    /**
     * Records information related to an English-CycL pair.
     *
     * @param cycl
    cycl-sentence-p
     * 		
     * @param english
    stringp
     * 		
     * @param force
     * 		keywordp; :s -> sentence, :q -> question
     * @param english-type
     * 		keywordp; :common -> commonly-used, :precise -> Cyclish
     */
    @LispMethod(comment = "Records information related to an English-CycL pair.\r\n\r\n@param cycl\ncycl-sentence-p\r\n\t\t\r\n@param english\nstringp\r\n\t\t\r\n@param force\r\n\t\tkeywordp; :s -> sentence, :q -> question\r\n@param english-type\r\n\t\tkeywordp; :common -> commonly-used, :precise -> Cyclish")
    public static final SubLObject record_suggested_nl_for_assertion_alt(SubLObject cycl, SubLObject nl, SubLObject force, SubLObject english_type, SubLObject timestamp, SubLObject user) {
        if (timestamp == UNPROVIDED) {
            timestamp = numeric_date_utilities.universal_timestring(UNPROVIDED);
        }
        if (user == UNPROVIDED) {
            user = api_control_vars.$the_cyclist$.getDynamicValue();
        }
        SubLTrampolineFile.checkType(cycl, CYCL_SENTENCE_P);
        SubLTrampolineFile.checkType(nl, STRINGP);
        SubLTrampolineFile.checkType(force, KEYWORDP);
        SubLTrampolineFile.checkType(english_type, KEYWORDP);
        record_suggested_nl_to_file(cycl, nl, force, english_type, timestamp, user);
        record_suggested_nl_in_kb(cycl, nl);
        ebmt_template_parser.ebmt_create_and_add_templates(nl, cycl, ebmt_template_parser.new_ebmt_lexicon(UNPROVIDED), T, UNPROVIDED);
        return NIL;
    }

    @LispMethod(comment = "Records information related to an English-CycL pair.\r\n\r\n@param cycl\n\t\tcycl-sentence-p\r\n\t\t\r\n@param english\n\t\tstringp\r\n\t\t\r\n@param force\r\n\t\tkeywordp; :s -> sentence, :q -> question\r\n@param english-type\r\n\t\tkeywordp; :common -> commonly-used, :precise -> Cyclish")
    public static SubLObject record_suggested_nl_for_assertion(final SubLObject cycl, final SubLObject nl, final SubLObject force, final SubLObject english_type, SubLObject timestamp, SubLObject user) {
        if (timestamp == UNPROVIDED) {
            timestamp = numeric_date_utilities.universal_timestring(UNPROVIDED);
        }
        if (user == UNPROVIDED) {
            user = api_control_vars.$the_cyclist$.getDynamicValue();
        }
        assert NIL != cycl_grammar.cycl_sentence_p(cycl) : "! cycl_grammar.cycl_sentence_p(cycl) " + ("cycl_grammar.cycl_sentence_p(cycl) " + "CommonSymbols.NIL != cycl_grammar.cycl_sentence_p(cycl) ") + cycl;
        assert NIL != stringp(nl) : "! stringp(nl) " + ("Types.stringp(nl) " + "CommonSymbols.NIL != Types.stringp(nl) ") + nl;
        assert NIL != keywordp(force) : "! keywordp(force) " + ("Types.keywordp(force) " + "CommonSymbols.NIL != Types.keywordp(force) ") + force;
        assert NIL != keywordp(english_type) : "! keywordp(english_type) " + ("Types.keywordp(english_type) " + "CommonSymbols.NIL != Types.keywordp(english_type) ") + english_type;
        record_suggested_nl_to_file(cycl, nl, force, english_type, timestamp, user);
        record_suggested_nl_in_kb(cycl, nl);
        return NIL;
    }/**
     * Records information related to an English-CycL pair.
     *
     * @param cycl
    		cycl-sentence-p
     * 		
     * @param english
    		stringp
     * 		
     * @param force
     * 		keywordp; :s -> sentence, :q -> question
     * @param english-type
     * 		keywordp; :common -> commonly-used, :precise -> Cyclish
     */


    public static final SubLObject record_suggested_nl_in_kb_alt(SubLObject cycl, SubLObject nl) {
        return ke.ke_assert_now(list($$ebmtNLToCycLTrainingExample, nl, list($$Quote, cycl)), $$EBMTTrainingExamplesFromQLMt, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject record_suggested_nl_in_kb(final SubLObject cycl, final SubLObject nl) {
        return ke.ke_assert_now(list($$ebmtNLToCycLTrainingExample, nl, list($$Quote, cycl)), $$EBMTTrainingExamplesFromQLMt, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject record_suggested_nl_to_file_alt(SubLObject cycl, SubLObject nl, SubLObject force, SubLObject english_type, SubLObject timestamp, SubLObject user) {
        if (timestamp == UNPROVIDED) {
            timestamp = numeric_date_utilities.universal_timestring(UNPROVIDED);
        }
        if (user == UNPROVIDED) {
            user = api_control_vars.$the_cyclist$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject stream = NIL;
                try {
                    stream = compatibility.open_text($str_alt332$_cyc_departments_nl_corpora_cb_us, $APPEND, NIL);
                    if (!stream.isStream()) {
                        Errors.error($str_alt334$Unable_to_open__S, $str_alt332$_cyc_departments_nl_corpora_cb_us);
                    }
                    {
                        SubLObject stream_81 = stream;
                        {
                            SubLObject _prev_bind_0 = $print_pretty$.currentBinding(thread);
                            try {
                                $print_pretty$.bind(NIL, thread);
                                {
                                    SubLObject output_list = list(timestamp, user, cycl, nl, force, english_type);
                                    format(stream_81, $str_alt335$_S__, output_list);
                                }
                            } finally {
                                $print_pretty$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                        try {
                            $is_thread_performing_cleanupP$.bind(T, thread);
                            if (stream.isStream()) {
                                close(stream, UNPROVIDED);
                            }
                        } finally {
                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject record_suggested_nl_to_file(final SubLObject cycl, final SubLObject nl, final SubLObject force, final SubLObject english_type, SubLObject timestamp, SubLObject user) {
        if (timestamp == UNPROVIDED) {
            timestamp = numeric_date_utilities.universal_timestring(UNPROVIDED);
        }
        if (user == UNPROVIDED) {
            user = api_control_vars.$the_cyclist$.getDynamicValue();
        }
        return NIL;
    }

    public static final SubLObject declare_parsing_utilities_file_alt() {
        declareFunction("parse_noun_compound_with_types", "PARSE-NOUN-COMPOUND-WITH-TYPES", 4, 2, false);
        declareFunction("parse_noun_compound_with_types_int", "PARSE-NOUN-COMPOUND-WITH-TYPES-INT", 5, 0, false);
        declareFunction("noun_compound_parses_mostly_definiteP", "NOUN-COMPOUND-PARSES-MOSTLY-DEFINITE?", 1, 0, false);
        declareFunction("instances_of_collection_parses", "INSTANCES-OF-COLLECTION-PARSES", 2, 0, false);
        declareFunction("instances_of_collection_parse_internal", "INSTANCES-OF-COLLECTION-PARSE-INTERNAL", 2, 0, false);
        declareFunction("instances_of_collection_parse", "INSTANCES-OF-COLLECTION-PARSE", 2, 0, false);
        declareFunction("clean_string_for_parse_noun_compound", "CLEAN-STRING-FOR-PARSE-NOUN-COMPOUND", 1, 0, false);
        declareFunction("filter_noun_compound_parses_by_types", "FILTER-NOUN-COMPOUND-PARSES-BY-TYPES", 3, 2, false);
        declareFunction("parse_noun_compound_valid_char_p", "PARSE-NOUN-COMPOUND-VALID-CHAR-P", 1, 0, false);
        declareFunction("clear_nl_tags", "CLEAR-NL-TAGS", 0, 0, false);
        declareFunction("clear_nl_tag_fns", "CLEAR-NL-TAG-FNS", 0, 0, false);
        declareFunction("clear_nl_tag_denoting_fns", "CLEAR-NL-TAG-DENOTING-FNS", 0, 0, false);
        declareFunction("initialize_nl_tags", "INITIALIZE-NL-TAGS", 0, 0, false);
        declareFunction("initialize_nl_tag_fns", "INITIALIZE-NL-TAG-FNS", 0, 0, false);
        declareFunction("initialize_nl_tag_denoting_fns", "INITIALIZE-NL-TAG-DENOTING-FNS", 0, 0, false);
        declareFunction("nl_tagP", "NL-TAG?", 1, 1, false);
        declareFunction("nl_tag_denoting_fnP", "NL-TAG-DENOTING-FN?", 1, 1, false);
        declareFunction("nl_tag_fnP", "NL-TAG-FN?", 1, 1, false);
        declareFunction("nl_tagged_termP", "NL-TAGGED-TERM?", 1, 0, false);
        declareFunction("ternary_nl_tagged_termP", "TERNARY-NL-TAGGED-TERM?", 1, 1, false);
        declareFunction("ternary_nl_tag_fnP", "TERNARY-NL-TAG-FN?", 1, 0, false);
        declareFunction("strip_nl_tags_top_level", "STRIP-NL-TAGS-TOP-LEVEL", 1, 0, false);
        declareFunction("possibly_strip_nl_tags_from_list", "POSSIBLY-STRIP-NL-TAGS-FROM-LIST", 2, 0, false);
        declareFunction("strip_nl_tags", "STRIP-NL-TAGS", 1, 1, false);
        declareFunction("get_nl_tag_fn", "GET-NL-TAG-FN", 2, 0, false);
        declareFunction("get_ternary_nl_tag_fn", "GET-TERNARY-NL-TAG-FN", 1, 0, false);
        declareFunction("get_binary_nl_tag_fn", "GET-BINARY-NL-TAG-FN", 1, 0, false);
        declareFunction("get_nl_tag_template", "GET-NL-TAG-TEMPLATE", 2, 1, false);
        declareFunction("clear_make_nl_tag_template", "CLEAR-MAKE-NL-TAG-TEMPLATE", 0, 0, false);
        declareFunction("remove_make_nl_tag_template", "REMOVE-MAKE-NL-TAG-TEMPLATE", 3, 0, false);
        declareFunction("make_nl_tag_template_internal", "MAKE-NL-TAG-TEMPLATE-INTERNAL", 3, 0, false);
        declareFunction("make_nl_tag_template", "MAKE-NL-TAG-TEMPLATE", 3, 0, false);
        declareFunction("add_nl_tag_template", "ADD-NL-TAG-TEMPLATE", 2, 0, false);
        declareFunction("contains_nl_tagP", "CONTAINS-NL-TAG?", 1, 0, false);
        declareFunction("add_nl_number_wrapper", "ADD-NL-NUMBER-WRAPPER", 2, 0, false);
        declareFunction("determiner_number_denotP_internal", "DETERMINER-NUMBER-DENOT?-INTERNAL", 1, 0, false);
        declareFunction("determiner_number_denotP", "DETERMINER-NUMBER-DENOT?", 1, 0, false);
        declareFunction("agr_preds_for_number", "AGR-PREDS-FOR-NUMBER", 1, 0, false);
        declareFunction("add_nl_quant_wrapper", "ADD-NL-QUANT-WRAPPER", 2, 2, false);
        declareFunction("clear_nl_max_floor_preds", "CLEAR-NL-MAX-FLOOR-PREDS", 0, 0, false);
        declareFunction("remove_nl_max_floor_preds", "REMOVE-NL-MAX-FLOOR-PREDS", 1, 0, false);
        declareFunction("nl_max_floor_preds_internal", "NL-MAX-FLOOR-PREDS-INTERNAL", 1, 0, false);
        declareFunction("nl_max_floor_preds", "NL-MAX-FLOOR-PREDS", 1, 0, false);
        declareFunction("cycl_has_definiteness_tagP", "CYCL-HAS-DEFINITENESS-TAG?", 1, 0, false);
        declareFunction("cycl_pronoun_p", "CYCL-PRONOUN-P", 1, 0, false);
        declareFunction("nl_person_attr_for_cycl_pronoun", "NL-PERSON-ATTR-FOR-CYCL-PRONOUN", 1, 0, false);
        declareFunction("nl_number_attr_for_cycl", "NL-NUMBER-ATTR-FOR-CYCL", 1, 0, false);
        declareFunction("nl_definiteness_attr_for_cycl", "NL-DEFINITENESS-ATTR-FOR-CYCL", 1, 0, false);
        declareFunction("nl_gender_attr_for_cycl", "NL-GENDER-ATTR-FOR-CYCL", 1, 0, false);
        declareFunction("kb_nl_number_attribute_p", "KB-NL-NUMBER-ATTRIBUTE-P", 1, 0, false);
        declareFunction("kb_nl_definiteness_attribute_p", "KB-NL-DEFINITENESS-ATTRIBUTE-P", 1, 0, false);
        declareFunction("kb_nl_gender_attribute_p", "KB-NL-GENDER-ATTRIBUTE-P", 1, 0, false);
        declareFunction("nl_number_attributes_disagreeP", "NL-NUMBER-ATTRIBUTES-DISAGREE?", 2, 0, false);
        declareFunction("nl_gender_attributes_disagreeP", "NL-GENDER-ATTRIBUTES-DISAGREE?", 2, 0, false);
        declareFunction("cycls_disagree_in_nl_number_attributeP", "CYCLS-DISAGREE-IN-NL-NUMBER-ATTRIBUTE?", 2, 0, false);
        declareFunction("cycls_disagree_in_nl_gender_attributeP", "CYCLS-DISAGREE-IN-NL-GENDER-ATTRIBUTE?", 2, 0, false);
        declareFunction("nl_number_to_spp", "NL-NUMBER-TO-SPP", 1, 0, false);
        declareFunction("spp_to_nl_number", "SPP-TO-NL-NUMBER", 1, 0, false);
        declareFunction("init_spps_to_nl_numbers", "INIT-SPPS-TO-NL-NUMBERS", 0, 0, false);
        declareFunction("add_nl_def_wrapper", "ADD-NL-DEF-WRAPPER", 2, 3, false);
        declareFunction("parse_collection_expression", "PARSE-COLLECTION-EXPRESSION", 1, 0, false);
        declareFunction("make_collection_expression", "MAKE-COLLECTION-EXPRESSION", 3, 0, false);
        declareFunction("npp_init_excluded_name_preds", "NPP-INIT-EXCLUDED-NAME-PREDS", 0, 0, false);
        declareFunction("npp_name_preds_to_exclude_iterator", "NPP-NAME-PREDS-TO-EXCLUDE-ITERATOR", 0, 0, false);
        declareFunction("npp_excluded_name_predP", "NPP-EXCLUDED-NAME-PRED?", 1, 0, false);
        declareFunction("blacklisted_cycl_for_parsersP", "BLACKLISTED-CYCL-FOR-PARSERS?", 1, 0, false);
        declareFunction("psp_lexicon_root_mt", "PSP-LEXICON-ROOT-MT", 0, 1, false);
        declareFunction("psp_weight_to_float", "PSP-WEIGHT-TO-FLOAT", 1, 0, false);
        declareFunction("psp_weight_from_float", "PSP-WEIGHT-FROM-FLOAT", 1, 0, false);
        declareFunction("remove_keyword_literals", "REMOVE-KEYWORD-LITERALS", 1, 0, false);
        declareFunction("vp_parse_from_pspP", "VP-PARSE-FROM-PSP?", 1, 0, false);
        declareFunction("result_of_parsing_words", "RESULT-OF-PARSING-WORDS", 1, 0, false);
        declareFunction("result_of_parsing_span", "RESULT-OF-PARSING-SPAN", 1, 0, false);
        declareFunction("result_of_parsing_span_wXo_thelist", "RESULT-OF-PARSING-SPAN-W/O-THELIST", 1, 0, false);
        declareFunction("result_of_parsing_span_length", "RESULT-OF-PARSING-SPAN-LENGTH", 1, 0, false);
        declareFunction("result_of_parsing_category", "RESULT-OF-PARSING-CATEGORY", 1, 0, false);
        declareFunction("result_of_parsing_start", "RESULT-OF-PARSING-START", 1, 0, false);
        declareFunction("result_of_parsing_end", "RESULT-OF-PARSING-END", 1, 0, false);
        declareFunction("result_of_parsing_formulaP", "RESULT-OF-PARSING-FORMULA?", 1, 0, false);
        declareFunction("formula_contains_result_of_parsingP", "FORMULA-CONTAINS-RESULT-OF-PARSING?", 1, 0, false);
        declareFunction("formulas_containing_results_of_parsing", "FORMULAS-CONTAINING-RESULTS-OF-PARSING", 1, 0, false);
        declareFunction("token_list_from_span", "TOKEN-LIST-FROM-SPAN", 2, 0, false);
        declareFunction("result_of_parsing_highest_category", "RESULT-OF-PARSING-HIGHEST-CATEGORY", 1, 1, false);
        declareFunction("devisable_verb_frameP", "DEVISABLE-VERB-FRAME?", 1, 0, false);
        declareFunction("verb_semtrans_from_denot_rolesXframe", "VERB-SEMTRANS-FROM-DENOT-ROLES&FRAME", 3, 1, false);
        declareFunction("known_roles_for_denot", "KNOWN-ROLES-FOR-DENOT", 1, 1, false);
        declareFunction("known_subject_roles_for_denot", "KNOWN-SUBJECT-ROLES-FOR-DENOT", 1, 2, false);
        declareFunction("known_direct_object_roles_for_denot", "KNOWN-DIRECT-OBJECT-ROLES-FOR-DENOT", 1, 2, false);
        declareFunction("known_indirect_object_roles_for_denot", "KNOWN-INDIRECT-OBJECT-ROLES-FOR-DENOT", 1, 2, false);
        declareFunction("required_arg1_preds", "REQUIRED-ARG1-PREDS", 1, 3, false);
        declareFunction("remove_genl_preds", "REMOVE-GENL-PREDS", 1, 1, false);
        declareFunction("ra1p_do_one_event_type", "RA1P-DO-ONE-EVENT-TYPE", 1, 0, false);
        declareFunction("ra1p_do_one_event_typeXpred", "RA1P-DO-ONE-EVENT-TYPE&PRED", 3, 0, false);
        declareFunction("possibly_uniquify_variablefn", "POSSIBLY-UNIQUIFY-VARIABLEFN", 1, 0, false);
        declareFunction("unique_el_var_for_variablefn", "UNIQUE-EL-VAR-FOR-VARIABLEFN", 1, 0, false);
        declareFunction("good_type_for_var_nameP", "GOOD-TYPE-FOR-VAR-NAME?", 1, 0, false);
        declareFunction("variablefn_termP", "VARIABLEFN-TERM?", 1, 0, false);
        declareFunction("next_unique_var_name", "NEXT-UNIQUE-VAR-NAME", 1, 0, false);
        declareFunction("parsing_rewrite_of_inverseP", "PARSING-REWRITE-OF-INVERSE?", 2, 1, false);
        declareFunction("parsing_rewrite_ofP", "PARSING-REWRITE-OF?", 2, 1, false);
        declareFunction("parse_tree_creation_rules", "PARSE-TREE-CREATION-RULES", 0, 0, false);
        declareFunction("syntactic_node_p", "SYNTACTIC-NODE-P", 1, 1, false);
        declareFunction("lexical_nodeP", "LEXICAL-NODE?", 1, 0, false);
        declareFunction("phrasal_nodeP", "PHRASAL-NODE?", 1, 0, false);
        declareFunction("syntactic_choice_node_p", "SYNTACTIC-CHOICE-NODE-P", 1, 1, false);
        declareFunction("syntactic_node_dtrs", "SYNTACTIC-NODE-DTRS", 1, 0, false);
        declareFunction("syntactic_node_mother", "SYNTACTIC-NODE-MOTHER", 1, 0, false);
        declareFunction("syntactic_node_root_node", "SYNTACTIC-NODE-ROOT-NODE", 1, 0, false);
        declareFunction("syntactic_node_nth_dtr", "SYNTACTIC-NODE-NTH-DTR", 2, 0, false);
        declareFunction("syntactic_node_descendant_at_path", "SYNTACTIC-NODE-DESCENDANT-AT-PATH", 2, 0, false);
        declareFunction("assert_parse_tree_info", "ASSERT-PARSE-TREE-INFO", 1, 1, false);
        declareFunction("syntactic_node_propose_meaning", "SYNTACTIC-NODE-PROPOSE-MEANING", 2, 2, false);
        declareFunction("syntactic_node_note_semantic_dependency", "SYNTACTIC-NODE-NOTE-SEMANTIC-DEPENDENCY", 4, 1, false);
        declareFunction("syntactic_node_proposed_meanings", "SYNTACTIC-NODE-PROPOSED-MEANINGS", 1, 2, false);
        declareMacro("push_meanings_from_syntactic_node", "PUSH-MEANINGS-FROM-SYNTACTIC-NODE");
        declareMacro("push_node_meaning_pairs_from_syntactic_node", "PUSH-NODE-MEANING-PAIRS-FROM-SYNTACTIC-NODE");
        declareFunction("recently_created_parse_tree_elements", "RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
        declareMacro("remembering_new_parse_tree_elements", "REMEMBERING-NEW-PARSE-TREE-ELEMENTS");
        declareFunction("forget_recently_created_parse_tree_elements", "FORGET-RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
        declareFunction("kill_recently_created_parse_tree_elements", "KILL-RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
        declareFunction("create_parse_tree_element", "CREATE-PARSE-TREE-ELEMENT", 1, 2, false);
        declareFunction("hypothesize_parse_tree_element", "HYPOTHESIZE-PARSE-TREE-ELEMENT", 1, 2, false);
        declareFunction("generate_choice_node", "GENERATE-CHOICE-NODE", 1, 2, false);
        declareFunction("choice_node_add_option_node", "CHOICE-NODE-ADD-OPTION-NODE", 2, 1, false);
        declareFunction("create_parse_tree", "CREATE-PARSE-TREE", 0, 2, false);
        declareFunction("assert_parse_tree_root_node", "ASSERT-PARSE-TREE-ROOT-NODE", 2, 1, false);
        declareFunction("create_parse_tree_with_root_node", "CREATE-PARSE-TREE-WITH-ROOT-NODE", 1, 2, false);
        declareFunction("content_mt_of_parse_tree", "CONTENT-MT-OF-PARSE-TREE", 1, 0, false);
        declareFunction("create_syntactic_node", "CREATE-SYNTACTIC-NODE", 6, 3, false);
        declareFunction("create_phrasal_node", "CREATE-PHRASAL-NODE", 8, 0, false);
        declareFunction("assert_start_offset_for_token", "ASSERT-START-OFFSET-FOR-TOKEN", 1, 3, false);
        declareFunction("syntactic_node_string", "SYNTACTIC-NODE-STRING", 1, 0, false);
        declareFunction("syntactic_node_start_offset", "SYNTACTIC-NODE-START-OFFSET", 1, 0, false);
        declareFunction("syntactic_node_category", "SYNTACTIC-NODE-CATEGORY", 1, 0, false);
        declareFunction("syntactic_node_agreement_pred", "SYNTACTIC-NODE-AGREEMENT-PRED", 1, 0, false);
        declareFunction("syntactic_node_tree", "SYNTACTIC-NODE-TREE", 1, 0, false);
        declareFunction("syntactic_node_penntag", "SYNTACTIC-NODE-PENNTAG", 1, 0, false);
        declareFunction("parse_tree_content_mt", "PARSE-TREE-CONTENT-MT", 1, 0, false);
        declareFunction("syntactic_node_content_mt", "SYNTACTIC-NODE-CONTENT-MT", 1, 0, false);
        declareFunction("syntactic_node_span", "SYNTACTIC-NODE-SPAN", 2, 0, false);
        declareFunction("create_lexical_node", "CREATE-LEXICAL-NODE", 7, 0, false);
        declareFunction("syntactic_node_add_dtr", "SYNTACTIC-NODE-ADD-DTR", 2, 2, false);
        declareFunction("syntactic_node_add_head_dtr", "SYNTACTIC-NODE-ADD-HEAD-DTR", 2, 2, false);
        declareFunction("package_node_meaning", "PACKAGE-NODE-MEANING", 1, 0, false);
        declareFunction("syntactic_node_to_nested_list", "SYNTACTIC-NODE-TO-NESTED-LIST", 1, 0, false);
        declareFunction("phrasal_node_string_from_dtrs", "PHRASAL-NODE-STRING-FROM-DTRS", 1, 0, false);
        declareFunction("phrasal_node_span_from_dtrs", "PHRASAL-NODE-SPAN-FROM-DTRS", 2, 0, false);
        declareFunction("syntactic_node_span_start", "SYNTACTIC-NODE-SPAN-START", 2, 0, false);
        declareFunction("syntactic_node_span_end", "SYNTACTIC-NODE-SPAN-END", 2, 0, false);
        declareFunction("initialize_penntag_phrase_categories", "INITIALIZE-PENNTAG-PHRASE-CATEGORIES", 0, 0, false);
        declareFunction("penntag_phrase_categories", "PENNTAG-PHRASE-CATEGORIES", 0, 0, false);
        declareFunction("cycl_penntag_p", "CYCL-PENNTAG-P", 1, 0, false);
        declareFunction("penntag_category", "PENNTAG-CATEGORY", 1, 0, false);
        declareFunction("denots_of_stringXpenntag", "DENOTS-OF-STRING&PENNTAG", 2, 0, false);
        declareFunction("initialize_penntag_forts", "INITIALIZE-PENNTAG-FORTS", 0, 0, false);
        declareFunction("penntag_forts", "PENNTAG-FORTS", 0, 0, false);
        declareFunction("penntag_to_cycl", "PENNTAG-TO-CYCL", 1, 0, false);
        declareFunction("cycl_penntag_to_keyword", "CYCL-PENNTAG-TO-KEYWORD", 1, 0, false);
        declareFunction("find_or_create_parse_token", "FIND-OR-CREATE-PARSE-TOKEN", 2, 2, false);
        declareFunction("find_parse_token", "FIND-PARSE-TOKEN", 2, 0, false);
        declareFunction("create_parse_token", "CREATE-PARSE-TOKEN", 2, 2, false);
        declareFunction("compute_parse_token_start_offset", "COMPUTE-PARSE-TOKEN-START-OFFSET", 3, 0, false);
        declareFunction("token_start_offset", "TOKEN-START-OFFSET", 1, 0, false);
        declareFunction("token_number", "TOKEN-NUMBER", 1, 0, false);
        declareFunction("token_string", "TOKEN-STRING", 1, 0, false);
        declareFunction("token_tokenization", "TOKEN-TOKENIZATION", 1, 0, false);
        declareFunction("tokenization_peg", "TOKENIZATION-PEG", 1, 0, false);
        declareFunction("tokenization_tokens", "TOKENIZATION-TOKENS", 1, 0, false);
        declareFunction("tokenization_input_string", "TOKENIZATION-INPUT-STRING", 1, 0, false);
        declareFunction("tokenization_token_count", "TOKENIZATION-TOKEN-COUNT", 1, 0, false);
        declareFunction("create_token_mapping", "CREATE-TOKEN-MAPPING", 2, 2, false);
        declareFunction("update_token_mapping", "UPDATE-TOKEN-MAPPING", 3, 0, false);
        declareFunction("construct_parse_expression_from_syntactic_node", "CONSTRUCT-PARSE-EXPRESSION-FROM-SYNTACTIC-NODE", 1, 0, false);
        declareFunction("construct_subl_parse_tree_from_syntactic_node", "CONSTRUCT-SUBL-PARSE-TREE-FROM-SYNTACTIC-NODE", 1, 1, false);
        declareFunction("add_parse_tree_to_node_mappings", "ADD-PARSE-TREE-TO-NODE-MAPPINGS", 3, 0, false);
        declareMacro("with_parsing_filter_piles", "WITH-PARSING-FILTER-PILES");
        declareMacro("parsing_filter_accepts", "PARSING-FILTER-ACCEPTS");
        declareMacro("parsing_filter_rejects", "PARSING-FILTER-REJECTS");
        declareMacro("apply_elimination_parsing_filter", "APPLY-ELIMINATION-PARSING-FILTER");
        declareMacro("save_parsing_filter_piles", "SAVE-PARSING-FILTER-PILES");
        declareFunction("apply_boolean_parsing_filter", "APPLY-BOOLEAN-PARSING-FILTER", 3, 1, false);
        declareFunction("apply_macroscopic_parsing_filter", "APPLY-MACROSCOPIC-PARSING-FILTER", 3, 1, false);
        declareFunction("parsing_subcollection_function_nautP", "PARSING-SUBCOLLECTION-FUNCTION-NAUT?", 1, 0, false);
        declareFunction("ternary_parsing_subcollection_function_nautP", "TERNARY-PARSING-SUBCOLLECTION-FUNCTION-NAUT?", 1, 0, false);
        declareFunction("subcollection_function_naut_from_arg", "SUBCOLLECTION-FUNCTION-NAUT-FROM-ARG", 1, 0, false);
        declareFunction("subcollection_function_type_level_in_from_argP", "SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-FROM-ARG?", 1, 0, false);
        declareFunction("subcollection_function_type_level_in_to_argP", "SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-TO-ARG?", 1, 0, false);
        declareFunction("subcollection_function_naut_to_arg", "SUBCOLLECTION-FUNCTION-NAUT-TO-ARG", 1, 0, false);
        declareFunction("subcollection_function_naut_pred", "SUBCOLLECTION-FUNCTION-NAUT-PRED", 1, 0, false);
        declareFunction("subcollection_function_naut_with_new_from_arg", "SUBCOLLECTION-FUNCTION-NAUT-WITH-NEW-FROM-ARG", 2, 0, false);
        declareFunction("subcollection_function_naut_with_new_to_arg", "SUBCOLLECTION-FUNCTION-NAUT-WITH-NEW-TO-ARG", 2, 0, false);
        declareFunction("eliminate_genl_subcollection_nauts", "ELIMINATE-GENL-SUBCOLLECTION-NAUTS", 1, 0, false);
        declareFunction("clear_parse_as_typeP", "CLEAR-PARSE-AS-TYPE?", 0, 0, false);
        declareFunction("remove_parse_as_typeP", "REMOVE-PARSE-AS-TYPE?", 3, 0, false);
        declareFunction("parse_as_typeP_internal", "PARSE-AS-TYPE?-INTERNAL", 3, 0, false);
        declareFunction("parse_as_typeP", "PARSE-AS-TYPE?", 3, 0, false);
        declareFunction("restrict_parses_by_types", "RESTRICT-PARSES-BY-TYPES", 3, 1, false);
        declareFunction("restrict_parse_to_type", "RESTRICT-PARSE-TO-TYPE", 2, 0, false);
        declareFunction("parse_dates_and_numbers", "PARSE-DATES-AND-NUMBERS", 3, 2, false);
        declareFunction("parse_iterator_print_function_trampoline", "PARSE-ITERATOR-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("parse_iterator_p", "PARSE-ITERATOR-P", 1, 0, false);
        new com.cyc.cycjava.cycl.parsing_utilities.$parse_iterator_p$UnaryFunction();
        declareFunction("pi_sub_iterators", "PI-SUB-ITERATORS", 1, 0, false);
        declareFunction("pi_properties", "PI-PROPERTIES", 1, 0, false);
        declareFunction("pi_current_iterator_index", "PI-CURRENT-ITERATOR-INDEX", 1, 0, false);
        declareFunction("pi_string", "PI-STRING", 1, 0, false);
        declareFunction("pi_previous_parses", "PI-PREVIOUS-PARSES", 1, 0, false);
        declareFunction("pi_next", "PI-NEXT", 1, 0, false);
        declareFunction("_csetf_pi_sub_iterators", "_CSETF-PI-SUB-ITERATORS", 2, 0, false);
        declareFunction("_csetf_pi_properties", "_CSETF-PI-PROPERTIES", 2, 0, false);
        declareFunction("_csetf_pi_current_iterator_index", "_CSETF-PI-CURRENT-ITERATOR-INDEX", 2, 0, false);
        declareFunction("_csetf_pi_string", "_CSETF-PI-STRING", 2, 0, false);
        declareFunction("_csetf_pi_previous_parses", "_CSETF-PI-PREVIOUS-PARSES", 2, 0, false);
        declareFunction("_csetf_pi_next", "_CSETF-PI-NEXT", 2, 0, false);
        declareFunction("make_parse_iterator", "MAKE-PARSE-ITERATOR", 0, 1, false);
        declareFunction("print_parse_iterator", "PRINT-PARSE-ITERATOR", 1, 2, false);
        declareFunction("new_parse_iterator", "NEW-PARSE-ITERATOR", 1, 1, false);
        declareFunction("new_parse_iterator_state", "NEW-PARSE-ITERATOR-STATE", 1, 1, false);
        declareFunction("parsing_subiterators", "PARSING-SUBITERATORS", 2, 0, false);
        declareFunction("pi_current_iterator", "PI-CURRENT-ITERATOR", 1, 0, false);
        declareFunction("parse_iterator_done", "PARSE-ITERATOR-DONE", 1, 0, false);
        declareFunction("parse_iterator_has_next", "PARSE-ITERATOR-HAS-NEXT", 1, 0, false);
        declareFunction("parse_iterator_next", "PARSE-ITERATOR-NEXT", 1, 0, false);
        declareFunction("cyclify_iterator_print_function_trampoline", "CYCLIFY-ITERATOR-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("cyclify_iterator_p", "CYCLIFY-ITERATOR-P", 1, 0, false);
        new com.cyc.cycjava.cycl.parsing_utilities.$cyclify_iterator_p$UnaryFunction();
        declareFunction("cyclify_iterator_iter_obj", "CYCLIFY-ITERATOR-ITER-OBJ", 1, 0, false);
        declareFunction("_csetf_cyclify_iterator_iter_obj", "_CSETF-CYCLIFY-ITERATOR-ITER-OBJ", 2, 0, false);
        declareFunction("make_cyclify_iterator", "MAKE-CYCLIFY-ITERATOR", 0, 1, false);
        declareFunction("new_cyclify_iterator", "NEW-CYCLIFY-ITERATOR", 1, 1, false);
        declareFunction("new_cyclify_iterator_state", "NEW-CYCLIFY-ITERATOR-STATE", 1, 1, false);
        declareFunction("cyclify_iterator_done", "CYCLIFY-ITERATOR-DONE", 1, 0, false);
        declareFunction("cyclify_iterator_next", "CYCLIFY-ITERATOR-NEXT", 1, 0, false);
        declareFunction("complete_text_topic_structure_tagging", "COMPLETE-TEXT-TOPIC-STRUCTURE-TAGGING", 1, 0, false);
        declareFunction("speaker_referent", "SPEAKER-REFERENT", 0, 0, false);
        declareFunction("get_cyclifier_interps_for_expression_peg", "GET-CYCLIFIER-INTERPS-FOR-EXPRESSION-PEG", 1, 0, false);
        declareFunction("peg_text", "PEG-TEXT", 1, 0, false);
        declareFunction("get_cyclifier_interps_for_string", "GET-CYCLIFIER-INTERPS-FOR-STRING", 1, 4, false);
        declareFunction("record_suggested_nl_for_assertion", "RECORD-SUGGESTED-NL-FOR-ASSERTION", 4, 2, false);
        declareFunction("record_suggested_nl_in_kb", "RECORD-SUGGESTED-NL-IN-KB", 2, 0, false);
        declareFunction("record_suggested_nl_to_file", "RECORD-SUGGESTED-NL-TO-FILE", 4, 2, false);
        declareFunction("subloop_reserved_initialize_parse_dates_and_numbers_test_case_class", "SUBLOOP-RESERVED-INITIALIZE-PARSE-DATES-AND-NUMBERS-TEST-CASE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_parse_dates_and_numbers_test_case_instance", "SUBLOOP-RESERVED-INITIALIZE-PARSE-DATES-AND-NUMBERS-TEST-CASE-INSTANCE", 1, 0, false);
        declareFunction("parse_dates_and_numbers_test_case_p", "PARSE-DATES-AND-NUMBERS-TEST-CASE-P", 1, 0, false);
        return NIL;
    }

    public static SubLObject declare_parsing_utilities_file() {
        if (SubLFiles.USE_V1) {
            declareFunction("parse_noun_compound_with_types", "PARSE-NOUN-COMPOUND-WITH-TYPES", 4, 2, false);
            declareFunction("parse_noun_compound_with_types_int", "PARSE-NOUN-COMPOUND-WITH-TYPES-INT", 5, 0, false);
            declareFunction("noun_compound_parses_mostly_definiteP", "NOUN-COMPOUND-PARSES-MOSTLY-DEFINITE?", 1, 0, false);
            declareFunction("instances_of_collection_parses", "INSTANCES-OF-COLLECTION-PARSES", 2, 0, false);
            declareFunction("instances_of_collection_parse_internal", "INSTANCES-OF-COLLECTION-PARSE-INTERNAL", 2, 0, false);
            declareFunction("instances_of_collection_parse", "INSTANCES-OF-COLLECTION-PARSE", 2, 0, false);
            declareFunction("clean_string_for_parse_noun_compound", "CLEAN-STRING-FOR-PARSE-NOUN-COMPOUND", 1, 0, false);
            declareFunction("filter_noun_compound_parses_by_types", "FILTER-NOUN-COMPOUND-PARSES-BY-TYPES", 3, 2, false);
            declareFunction("parse_noun_compound_valid_char_p", "PARSE-NOUN-COMPOUND-VALID-CHAR-P", 1, 0, false);
            declareFunction("clear_nl_tags", "CLEAR-NL-TAGS", 0, 0, false);
            declareFunction("clear_nl_tag_fns", "CLEAR-NL-TAG-FNS", 0, 0, false);
            declareFunction("clear_nl_tag_denoting_fns", "CLEAR-NL-TAG-DENOTING-FNS", 0, 0, false);
            declareFunction("initialize_nl_tags", "INITIALIZE-NL-TAGS", 0, 0, false);
            declareFunction("initialize_nl_tag_fns", "INITIALIZE-NL-TAG-FNS", 0, 0, false);
            declareFunction("initialize_nl_tag_denoting_fns", "INITIALIZE-NL-TAG-DENOTING-FNS", 0, 0, false);
            declareFunction("nl_tagP", "NL-TAG?", 1, 1, false);
            declareFunction("nl_tag_denoting_fnP", "NL-TAG-DENOTING-FN?", 1, 1, false);
            declareFunction("nl_tag_fnP", "NL-TAG-FN?", 1, 1, false);
            declareFunction("nl_tagged_termP", "NL-TAGGED-TERM?", 1, 0, false);
            declareFunction("ternary_nl_tagged_termP", "TERNARY-NL-TAGGED-TERM?", 1, 1, false);
            declareFunction("ternary_nl_tag_fnP", "TERNARY-NL-TAG-FN?", 1, 0, false);
            declareFunction("strip_nl_tags_top_level", "STRIP-NL-TAGS-TOP-LEVEL", 1, 0, false);
            declareFunction("possibly_strip_nl_tags_from_list", "POSSIBLY-STRIP-NL-TAGS-FROM-LIST", 2, 0, false);
            declareFunction("strip_nl_tags", "STRIP-NL-TAGS", 1, 1, false);
            declareFunction("get_nl_tag_fn", "GET-NL-TAG-FN", 2, 0, false);
            declareFunction("get_ternary_nl_tag_fn", "GET-TERNARY-NL-TAG-FN", 1, 0, false);
            declareFunction("get_binary_nl_tag_fn", "GET-BINARY-NL-TAG-FN", 1, 0, false);
            declareFunction("get_nl_tag_template", "GET-NL-TAG-TEMPLATE", 2, 1, false);
            declareFunction("clear_make_nl_tag_template", "CLEAR-MAKE-NL-TAG-TEMPLATE", 0, 0, false);
            declareFunction("remove_make_nl_tag_template", "REMOVE-MAKE-NL-TAG-TEMPLATE", 3, 0, false);
            declareFunction("make_nl_tag_template_internal", "MAKE-NL-TAG-TEMPLATE-INTERNAL", 3, 0, false);
            declareFunction("make_nl_tag_template", "MAKE-NL-TAG-TEMPLATE", 3, 0, false);
            declareFunction("add_nl_tag_template", "ADD-NL-TAG-TEMPLATE", 2, 0, false);
            declareFunction("contains_nl_tagP", "CONTAINS-NL-TAG?", 1, 0, false);
            declareFunction("add_nl_number_wrapper", "ADD-NL-NUMBER-WRAPPER", 2, 0, false);
            declareFunction("determiner_number_denotP_internal", "DETERMINER-NUMBER-DENOT?-INTERNAL", 1, 0, false);
            declareFunction("determiner_number_denotP", "DETERMINER-NUMBER-DENOT?", 1, 0, false);
            declareFunction("agr_preds_for_number", "AGR-PREDS-FOR-NUMBER", 1, 0, false);
            declareFunction("add_nl_quant_wrapper", "ADD-NL-QUANT-WRAPPER", 2, 2, false);
            declareFunction("clear_nl_max_floor_preds", "CLEAR-NL-MAX-FLOOR-PREDS", 0, 0, false);
            declareFunction("remove_nl_max_floor_preds", "REMOVE-NL-MAX-FLOOR-PREDS", 1, 0, false);
            declareFunction("nl_max_floor_preds_internal", "NL-MAX-FLOOR-PREDS-INTERNAL", 1, 0, false);
            declareFunction("nl_max_floor_preds", "NL-MAX-FLOOR-PREDS", 1, 0, false);
            declareFunction("cycl_has_definiteness_tagP", "CYCL-HAS-DEFINITENESS-TAG?", 1, 0, false);
            declareFunction("cycl_pronoun_p", "CYCL-PRONOUN-P", 1, 0, false);
            declareFunction("nl_person_attr_for_cycl_pronoun", "NL-PERSON-ATTR-FOR-CYCL-PRONOUN", 1, 0, false);
            declareFunction("nl_number_attr_for_cycl", "NL-NUMBER-ATTR-FOR-CYCL", 1, 0, false);
            declareFunction("nl_definiteness_attr_for_cycl", "NL-DEFINITENESS-ATTR-FOR-CYCL", 1, 0, false);
            declareFunction("nl_gender_attr_for_cycl", "NL-GENDER-ATTR-FOR-CYCL", 1, 0, false);
            declareFunction("kb_nl_number_attribute_p", "KB-NL-NUMBER-ATTRIBUTE-P", 1, 0, false);
            declareFunction("kb_nl_definiteness_attribute_p", "KB-NL-DEFINITENESS-ATTRIBUTE-P", 1, 0, false);
            declareFunction("kb_nl_gender_attribute_p", "KB-NL-GENDER-ATTRIBUTE-P", 1, 0, false);
            declareFunction("nl_number_attributes_disagreeP", "NL-NUMBER-ATTRIBUTES-DISAGREE?", 2, 0, false);
            declareFunction("nl_gender_attributes_disagreeP", "NL-GENDER-ATTRIBUTES-DISAGREE?", 2, 0, false);
            declareFunction("cycls_disagree_in_nl_number_attributeP", "CYCLS-DISAGREE-IN-NL-NUMBER-ATTRIBUTE?", 2, 0, false);
            declareFunction("cycls_disagree_in_nl_gender_attributeP", "CYCLS-DISAGREE-IN-NL-GENDER-ATTRIBUTE?", 2, 0, false);
            declareFunction("nl_number_to_spp", "NL-NUMBER-TO-SPP", 1, 0, false);
            declareFunction("spp_to_nl_number", "SPP-TO-NL-NUMBER", 1, 0, false);
            declareFunction("init_spps_to_nl_numbers", "INIT-SPPS-TO-NL-NUMBERS", 0, 0, false);
            declareFunction("add_nl_def_wrapper", "ADD-NL-DEF-WRAPPER", 2, 3, false);
            declareFunction("parse_collection_expression", "PARSE-COLLECTION-EXPRESSION", 1, 0, false);
            declareFunction("make_collection_expression", "MAKE-COLLECTION-EXPRESSION", 3, 0, false);
            declareFunction("npp_init_excluded_name_preds", "NPP-INIT-EXCLUDED-NAME-PREDS", 0, 0, false);
            declareFunction("npp_name_preds_to_exclude_iterator", "NPP-NAME-PREDS-TO-EXCLUDE-ITERATOR", 0, 0, false);
            declareFunction("npp_excluded_name_predP", "NPP-EXCLUDED-NAME-PRED?", 1, 0, false);
            declareFunction("blacklisted_cycl_for_parsersP", "BLACKLISTED-CYCL-FOR-PARSERS?", 1, 0, false);
            declareFunction("psp_lexicon_root_mt", "PSP-LEXICON-ROOT-MT", 0, 1, false);
            declareFunction("psp_weight_to_float", "PSP-WEIGHT-TO-FLOAT", 1, 0, false);
            declareFunction("psp_weight_from_float", "PSP-WEIGHT-FROM-FLOAT", 1, 0, false);
            declareFunction("remove_keyword_literals", "REMOVE-KEYWORD-LITERALS", 1, 0, false);
            declareFunction("vp_parse_from_pspP", "VP-PARSE-FROM-PSP?", 1, 0, false);
            declareFunction("result_of_parsing_words", "RESULT-OF-PARSING-WORDS", 1, 0, false);
            declareFunction("result_of_parsing_span", "RESULT-OF-PARSING-SPAN", 1, 0, false);
            declareFunction("result_of_parsing_span_wXo_thelist", "RESULT-OF-PARSING-SPAN-W/O-THELIST", 1, 0, false);
            declareFunction("result_of_parsing_span_length", "RESULT-OF-PARSING-SPAN-LENGTH", 1, 0, false);
            declareFunction("result_of_parsing_category", "RESULT-OF-PARSING-CATEGORY", 1, 0, false);
            declareFunction("result_of_parsing_start", "RESULT-OF-PARSING-START", 1, 0, false);
            declareFunction("result_of_parsing_end", "RESULT-OF-PARSING-END", 1, 0, false);
            declareFunction("result_of_parsing_formulaP", "RESULT-OF-PARSING-FORMULA?", 1, 0, false);
            declareFunction("formula_contains_result_of_parsingP", "FORMULA-CONTAINS-RESULT-OF-PARSING?", 1, 0, false);
            declareFunction("formulas_containing_results_of_parsing", "FORMULAS-CONTAINING-RESULTS-OF-PARSING", 1, 0, false);
            declareFunction("token_list_from_span", "TOKEN-LIST-FROM-SPAN", 2, 0, false);
            declareFunction("result_of_parsing_highest_category", "RESULT-OF-PARSING-HIGHEST-CATEGORY", 1, 1, false);
            declareFunction("devisable_verb_frameP", "DEVISABLE-VERB-FRAME?", 1, 0, false);
            declareFunction("verb_semtrans_from_denot_rolesXframe", "VERB-SEMTRANS-FROM-DENOT-ROLES&FRAME", 3, 1, false);
            declareFunction("known_roles_for_denot", "KNOWN-ROLES-FOR-DENOT", 1, 1, false);
            declareFunction("known_subject_roles_for_denot", "KNOWN-SUBJECT-ROLES-FOR-DENOT", 1, 2, false);
            declareFunction("known_direct_object_roles_for_denot", "KNOWN-DIRECT-OBJECT-ROLES-FOR-DENOT", 1, 2, false);
            declareFunction("known_indirect_object_roles_for_denot", "KNOWN-INDIRECT-OBJECT-ROLES-FOR-DENOT", 1, 2, false);
            declareFunction("required_arg1_preds", "REQUIRED-ARG1-PREDS", 1, 3, false);
            declareFunction("remove_genl_preds", "REMOVE-GENL-PREDS", 1, 1, false);
            declareFunction("ra1p_do_one_event_type", "RA1P-DO-ONE-EVENT-TYPE", 1, 0, false);
            declareFunction("ra1p_do_one_event_typeXpred", "RA1P-DO-ONE-EVENT-TYPE&PRED", 3, 0, false);
            declareFunction("possibly_uniquify_variablefn", "POSSIBLY-UNIQUIFY-VARIABLEFN", 1, 0, false);
            declareFunction("unique_el_var_for_variablefn", "UNIQUE-EL-VAR-FOR-VARIABLEFN", 1, 0, false);
            declareFunction("good_type_for_var_nameP", "GOOD-TYPE-FOR-VAR-NAME?", 1, 0, false);
            declareFunction("variablefn_termP", "VARIABLEFN-TERM?", 1, 0, false);
            declareFunction("next_unique_var_name", "NEXT-UNIQUE-VAR-NAME", 1, 0, false);
            declareFunction("parsing_rewrite_of_inverseP", "PARSING-REWRITE-OF-INVERSE?", 2, 1, false);
            declareFunction("parsing_rewrite_ofP", "PARSING-REWRITE-OF?", 2, 1, false);
            declareFunction("parse_tree_creation_rules", "PARSE-TREE-CREATION-RULES", 0, 0, false);
            declareFunction("syntactic_node_p", "SYNTACTIC-NODE-P", 1, 1, false);
            declareFunction("lexical_nodeP", "LEXICAL-NODE?", 1, 0, false);
            declareFunction("phrasal_nodeP", "PHRASAL-NODE?", 1, 0, false);
            declareFunction("syntactic_choice_node_p", "SYNTACTIC-CHOICE-NODE-P", 1, 1, false);
            declareFunction("syntactic_node_dtrs", "SYNTACTIC-NODE-DTRS", 1, 0, false);
            declareFunction("syntactic_node_mother", "SYNTACTIC-NODE-MOTHER", 1, 0, false);
            declareFunction("syntactic_node_root_node", "SYNTACTIC-NODE-ROOT-NODE", 1, 0, false);
            declareFunction("syntactic_node_nth_dtr", "SYNTACTIC-NODE-NTH-DTR", 2, 0, false);
            declareFunction("syntactic_node_descendant_at_path", "SYNTACTIC-NODE-DESCENDANT-AT-PATH", 2, 0, false);
            declareFunction("assert_parse_tree_info", "ASSERT-PARSE-TREE-INFO", 1, 1, false);
            declareFunction("syntactic_node_propose_meaning", "SYNTACTIC-NODE-PROPOSE-MEANING", 2, 2, false);
            declareFunction("syntactic_node_note_semantic_dependency", "SYNTACTIC-NODE-NOTE-SEMANTIC-DEPENDENCY", 4, 1, false);
            declareFunction("syntactic_node_proposed_meanings", "SYNTACTIC-NODE-PROPOSED-MEANINGS", 1, 2, false);
            declareMacro("push_meanings_from_syntactic_node", "PUSH-MEANINGS-FROM-SYNTACTIC-NODE");
            declareMacro("push_node_meaning_pairs_from_syntactic_node", "PUSH-NODE-MEANING-PAIRS-FROM-SYNTACTIC-NODE");
            declareFunction("recently_created_parse_tree_elements", "RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
            declareMacro("remembering_new_parse_tree_elements", "REMEMBERING-NEW-PARSE-TREE-ELEMENTS");
            declareFunction("forget_recently_created_parse_tree_elements", "FORGET-RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
            declareFunction("kill_recently_created_parse_tree_elements", "KILL-RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
            declareFunction("create_parse_tree_element", "CREATE-PARSE-TREE-ELEMENT", 1, 2, false);
            declareFunction("hypothesize_parse_tree_element", "HYPOTHESIZE-PARSE-TREE-ELEMENT", 1, 2, false);
            declareFunction("generate_choice_node", "GENERATE-CHOICE-NODE", 1, 2, false);
            declareFunction("choice_node_add_option_node", "CHOICE-NODE-ADD-OPTION-NODE", 2, 1, false);
            declareFunction("create_parse_tree", "CREATE-PARSE-TREE", 0, 2, false);
            declareFunction("assert_parse_tree_root_node", "ASSERT-PARSE-TREE-ROOT-NODE", 2, 1, false);
            declareFunction("create_parse_tree_with_root_node", "CREATE-PARSE-TREE-WITH-ROOT-NODE", 1, 2, false);
            declareFunction("content_mt_of_parse_tree", "CONTENT-MT-OF-PARSE-TREE", 1, 0, false);
            declareFunction("create_syntactic_node", "CREATE-SYNTACTIC-NODE", 6, 3, false);
            declareFunction("create_phrasal_node", "CREATE-PHRASAL-NODE", 8, 0, false);
            declareFunction("assert_start_offset_for_token", "ASSERT-START-OFFSET-FOR-TOKEN", 1, 3, false);
            declareFunction("syntactic_node_string", "SYNTACTIC-NODE-STRING", 1, 0, false);
            declareFunction("syntactic_node_start_offset", "SYNTACTIC-NODE-START-OFFSET", 1, 0, false);
            declareFunction("syntactic_node_category", "SYNTACTIC-NODE-CATEGORY", 1, 0, false);
            declareFunction("syntactic_node_agreement_pred", "SYNTACTIC-NODE-AGREEMENT-PRED", 1, 0, false);
            declareFunction("syntactic_node_tree", "SYNTACTIC-NODE-TREE", 1, 0, false);
            declareFunction("syntactic_node_penntag", "SYNTACTIC-NODE-PENNTAG", 1, 0, false);
            declareFunction("parse_tree_content_mt", "PARSE-TREE-CONTENT-MT", 1, 0, false);
            declareFunction("syntactic_node_content_mt", "SYNTACTIC-NODE-CONTENT-MT", 1, 0, false);
            declareFunction("syntactic_node_span", "SYNTACTIC-NODE-SPAN", 2, 0, false);
            declareFunction("create_lexical_node", "CREATE-LEXICAL-NODE", 7, 0, false);
            declareFunction("syntactic_node_add_dtr", "SYNTACTIC-NODE-ADD-DTR", 2, 2, false);
            declareFunction("syntactic_node_add_head_dtr", "SYNTACTIC-NODE-ADD-HEAD-DTR", 2, 2, false);
            declareFunction("package_node_meaning", "PACKAGE-NODE-MEANING", 1, 0, false);
            declareFunction("syntactic_node_to_nested_list", "SYNTACTIC-NODE-TO-NESTED-LIST", 1, 0, false);
            declareFunction("phrasal_node_string_from_dtrs", "PHRASAL-NODE-STRING-FROM-DTRS", 1, 0, false);
            declareFunction("phrasal_node_span_from_dtrs", "PHRASAL-NODE-SPAN-FROM-DTRS", 2, 0, false);
            declareFunction("syntactic_node_span_start", "SYNTACTIC-NODE-SPAN-START", 2, 0, false);
            declareFunction("syntactic_node_span_end", "SYNTACTIC-NODE-SPAN-END", 2, 0, false);
            declareFunction("initialize_penntag_phrase_categories", "INITIALIZE-PENNTAG-PHRASE-CATEGORIES", 0, 0, false);
            declareFunction("penntag_phrase_categories", "PENNTAG-PHRASE-CATEGORIES", 0, 0, false);
            declareFunction("cycl_penntag_p", "CYCL-PENNTAG-P", 1, 0, false);
            declareFunction("penntag_category", "PENNTAG-CATEGORY", 1, 0, false);
            declareFunction("denots_of_stringXpenntag", "DENOTS-OF-STRING&PENNTAG", 2, 0, false);
            declareFunction("initialize_penntag_forts", "INITIALIZE-PENNTAG-FORTS", 0, 0, false);
            declareFunction("penntag_forts", "PENNTAG-FORTS", 0, 0, false);
            declareFunction("penntag_to_cycl", "PENNTAG-TO-CYCL", 1, 0, false);
            declareFunction("cycl_penntag_to_keyword", "CYCL-PENNTAG-TO-KEYWORD", 1, 0, false);
            declareFunction("find_or_create_parse_token", "FIND-OR-CREATE-PARSE-TOKEN", 2, 2, false);
            declareFunction("find_parse_token", "FIND-PARSE-TOKEN", 2, 0, false);
            declareFunction("create_parse_token", "CREATE-PARSE-TOKEN", 2, 2, false);
            declareFunction("compute_parse_token_start_offset", "COMPUTE-PARSE-TOKEN-START-OFFSET", 3, 0, false);
            declareFunction("token_start_offset", "TOKEN-START-OFFSET", 1, 0, false);
            declareFunction("token_number", "TOKEN-NUMBER", 1, 0, false);
            declareFunction("token_string", "TOKEN-STRING", 1, 0, false);
            declareFunction("token_tokenization", "TOKEN-TOKENIZATION", 1, 0, false);
            declareFunction("tokenization_peg", "TOKENIZATION-PEG", 1, 0, false);
            declareFunction("tokenization_tokens", "TOKENIZATION-TOKENS", 1, 0, false);
            declareFunction("tokenization_input_string", "TOKENIZATION-INPUT-STRING", 1, 0, false);
            declareFunction("tokenization_token_count", "TOKENIZATION-TOKEN-COUNT", 1, 0, false);
            declareFunction("create_token_mapping", "CREATE-TOKEN-MAPPING", 2, 2, false);
            declareFunction("update_token_mapping", "UPDATE-TOKEN-MAPPING", 3, 0, false);
            declareFunction("construct_parse_expression_from_syntactic_node", "CONSTRUCT-PARSE-EXPRESSION-FROM-SYNTACTIC-NODE", 1, 0, false);
            declareFunction("construct_subl_parse_tree_from_syntactic_node", "CONSTRUCT-SUBL-PARSE-TREE-FROM-SYNTACTIC-NODE", 1, 1, false);
            declareFunction("add_parse_tree_to_node_mappings", "ADD-PARSE-TREE-TO-NODE-MAPPINGS", 3, 0, false);
            declareMacro("with_parsing_filter_piles", "WITH-PARSING-FILTER-PILES");
            declareMacro("parsing_filter_accepts", "PARSING-FILTER-ACCEPTS");
            declareMacro("parsing_filter_rejects", "PARSING-FILTER-REJECTS");
            declareMacro("apply_elimination_parsing_filter", "APPLY-ELIMINATION-PARSING-FILTER");
            declareMacro("save_parsing_filter_piles", "SAVE-PARSING-FILTER-PILES");
            declareFunction("apply_boolean_parsing_filter", "APPLY-BOOLEAN-PARSING-FILTER", 3, 1, false);
            declareFunction("apply_macroscopic_parsing_filter", "APPLY-MACROSCOPIC-PARSING-FILTER", 3, 1, false);
            declareFunction("parsing_subcollection_function_nautP", "PARSING-SUBCOLLECTION-FUNCTION-NAUT?", 1, 0, false);
            declareFunction("ternary_parsing_subcollection_function_nautP", "TERNARY-PARSING-SUBCOLLECTION-FUNCTION-NAUT?", 1, 0, false);
            declareFunction("subcollection_function_naut_from_arg", "SUBCOLLECTION-FUNCTION-NAUT-FROM-ARG", 1, 0, false);
            declareFunction("subcollection_function_type_level_in_from_argP", "SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-FROM-ARG?", 1, 0, false);
            declareFunction("subcollection_function_type_level_in_to_argP", "SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-TO-ARG?", 1, 0, false);
            declareFunction("subcollection_function_naut_to_arg", "SUBCOLLECTION-FUNCTION-NAUT-TO-ARG", 1, 0, false);
            declareFunction("subcollection_function_naut_pred", "SUBCOLLECTION-FUNCTION-NAUT-PRED", 1, 0, false);
            declareFunction("subcollection_function_naut_with_new_from_arg", "SUBCOLLECTION-FUNCTION-NAUT-WITH-NEW-FROM-ARG", 2, 0, false);
            declareFunction("subcollection_function_naut_with_new_to_arg", "SUBCOLLECTION-FUNCTION-NAUT-WITH-NEW-TO-ARG", 2, 0, false);
            declareFunction("eliminate_genl_subcollection_nauts", "ELIMINATE-GENL-SUBCOLLECTION-NAUTS", 1, 0, false);
            declareFunction("clear_parse_as_typeP", "CLEAR-PARSE-AS-TYPE?", 0, 0, false);
            declareFunction("remove_parse_as_typeP", "REMOVE-PARSE-AS-TYPE?", 3, 0, false);
            declareFunction("parse_as_typeP_internal", "PARSE-AS-TYPE?-INTERNAL", 3, 0, false);
            declareFunction("parse_as_typeP", "PARSE-AS-TYPE?", 3, 0, false);
            declareFunction("restrict_parses_by_types", "RESTRICT-PARSES-BY-TYPES", 3, 1, false);
            declareFunction("restrict_parse_to_type", "RESTRICT-PARSE-TO-TYPE", 2, 0, false);
            declareFunction("parse_dates_and_numbers", "PARSE-DATES-AND-NUMBERS", 3, 2, false);
            declareFunction("parse_iterator_print_function_trampoline", "PARSE-ITERATOR-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
            declareFunction("parse_iterator_p", "PARSE-ITERATOR-P", 1, 0, false);
            new parsing_utilities.$parse_iterator_p$UnaryFunction();
            declareFunction("pi_sub_iterators", "PI-SUB-ITERATORS", 1, 0, false);
            declareFunction("pi_properties", "PI-PROPERTIES", 1, 0, false);
            declareFunction("pi_current_iterator_index", "PI-CURRENT-ITERATOR-INDEX", 1, 0, false);
            declareFunction("pi_string", "PI-STRING", 1, 0, false);
            declareFunction("pi_previous_parses", "PI-PREVIOUS-PARSES", 1, 0, false);
            declareFunction("pi_next", "PI-NEXT", 1, 0, false);
            declareFunction("_csetf_pi_sub_iterators", "_CSETF-PI-SUB-ITERATORS", 2, 0, false);
            declareFunction("_csetf_pi_properties", "_CSETF-PI-PROPERTIES", 2, 0, false);
            declareFunction("_csetf_pi_current_iterator_index", "_CSETF-PI-CURRENT-ITERATOR-INDEX", 2, 0, false);
            declareFunction("_csetf_pi_string", "_CSETF-PI-STRING", 2, 0, false);
            declareFunction("_csetf_pi_previous_parses", "_CSETF-PI-PREVIOUS-PARSES", 2, 0, false);
            declareFunction("_csetf_pi_next", "_CSETF-PI-NEXT", 2, 0, false);
            declareFunction("make_parse_iterator", "MAKE-PARSE-ITERATOR", 0, 1, false);
            declareFunction("visit_defstruct_parse_iterator", "VISIT-DEFSTRUCT-PARSE-ITERATOR", 2, 0, false);
            declareFunction("visit_defstruct_object_parse_iterator_method", "VISIT-DEFSTRUCT-OBJECT-PARSE-ITERATOR-METHOD", 2, 0, false);
            declareFunction("print_parse_iterator", "PRINT-PARSE-ITERATOR", 1, 2, false);
            declareFunction("new_parse_iterator", "NEW-PARSE-ITERATOR", 1, 1, false);
            declareFunction("new_parse_iterator_state", "NEW-PARSE-ITERATOR-STATE", 1, 1, false);
            declareFunction("parsing_subiterators", "PARSING-SUBITERATORS", 2, 0, false);
            declareFunction("pi_current_iterator", "PI-CURRENT-ITERATOR", 1, 0, false);
            declareFunction("parse_iterator_done", "PARSE-ITERATOR-DONE", 1, 0, false);
            declareFunction("parse_iterator_has_next", "PARSE-ITERATOR-HAS-NEXT", 1, 0, false);
            declareFunction("parse_iterator_next", "PARSE-ITERATOR-NEXT", 1, 0, false);
            declareFunction("cyclify_iterator_print_function_trampoline", "CYCLIFY-ITERATOR-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
            declareFunction("cyclify_iterator_p", "CYCLIFY-ITERATOR-P", 1, 0, false);
            new parsing_utilities.$cyclify_iterator_p$UnaryFunction();
            declareFunction("cyclify_iterator_iter_obj", "CYCLIFY-ITERATOR-ITER-OBJ", 1, 0, false);
            declareFunction("_csetf_cyclify_iterator_iter_obj", "_CSETF-CYCLIFY-ITERATOR-ITER-OBJ", 2, 0, false);
            declareFunction("make_cyclify_iterator", "MAKE-CYCLIFY-ITERATOR", 0, 1, false);
            declareFunction("visit_defstruct_cyclify_iterator", "VISIT-DEFSTRUCT-CYCLIFY-ITERATOR", 2, 0, false);
            declareFunction("visit_defstruct_object_cyclify_iterator_method", "VISIT-DEFSTRUCT-OBJECT-CYCLIFY-ITERATOR-METHOD", 2, 0, false);
            declareFunction("new_cyclify_iterator", "NEW-CYCLIFY-ITERATOR", 1, 1, false);
            declareFunction("new_cyclify_iterator_state", "NEW-CYCLIFY-ITERATOR-STATE", 1, 1, false);
            declareFunction("cyclify_iterator_done", "CYCLIFY-ITERATOR-DONE", 1, 0, false);
            declareFunction("cyclify_iterator_next", "CYCLIFY-ITERATOR-NEXT", 1, 0, false);
            declareFunction("complete_text_topic_structure_tagging", "COMPLETE-TEXT-TOPIC-STRUCTURE-TAGGING", 1, 0, false);
            declareFunction("speaker_referent", "SPEAKER-REFERENT", 0, 0, false);
            declareFunction("get_cyclifier_interps_for_expression_peg", "GET-CYCLIFIER-INTERPS-FOR-EXPRESSION-PEG", 1, 0, false);
            declareFunction("peg_text", "PEG-TEXT", 1, 0, false);
            declareFunction("get_cyclifier_interps_for_string", "GET-CYCLIFIER-INTERPS-FOR-STRING", 1, 4, false);
            declareFunction("record_suggested_nl_for_assertion", "RECORD-SUGGESTED-NL-FOR-ASSERTION", 4, 2, false);
            declareFunction("record_suggested_nl_in_kb", "RECORD-SUGGESTED-NL-IN-KB", 2, 0, false);
            declareFunction("record_suggested_nl_to_file", "RECORD-SUGGESTED-NL-TO-FILE", 4, 2, false);
        }
        if (SubLFiles.USE_V2) {
            declareFunction("subloop_reserved_initialize_parse_dates_and_numbers_test_case_class", "SUBLOOP-RESERVED-INITIALIZE-PARSE-DATES-AND-NUMBERS-TEST-CASE-CLASS", 1, 0, false);
            declareFunction("subloop_reserved_initialize_parse_dates_and_numbers_test_case_instance", "SUBLOOP-RESERVED-INITIALIZE-PARSE-DATES-AND-NUMBERS-TEST-CASE-INSTANCE", 1, 0, false);
            declareFunction("parse_dates_and_numbers_test_case_p", "PARSE-DATES-AND-NUMBERS-TEST-CASE-P", 1, 0, false);
        }
        return NIL;
    }

    public static SubLObject declare_parsing_utilities_file_Previous() {
        declareFunction("parse_noun_compound_with_types", "PARSE-NOUN-COMPOUND-WITH-TYPES", 4, 2, false);
        declareFunction("parse_noun_compound_with_types_int", "PARSE-NOUN-COMPOUND-WITH-TYPES-INT", 5, 0, false);
        declareFunction("noun_compound_parses_mostly_definiteP", "NOUN-COMPOUND-PARSES-MOSTLY-DEFINITE?", 1, 0, false);
        declareFunction("instances_of_collection_parses", "INSTANCES-OF-COLLECTION-PARSES", 2, 0, false);
        declareFunction("instances_of_collection_parse_internal", "INSTANCES-OF-COLLECTION-PARSE-INTERNAL", 2, 0, false);
        declareFunction("instances_of_collection_parse", "INSTANCES-OF-COLLECTION-PARSE", 2, 0, false);
        declareFunction("clean_string_for_parse_noun_compound", "CLEAN-STRING-FOR-PARSE-NOUN-COMPOUND", 1, 0, false);
        declareFunction("filter_noun_compound_parses_by_types", "FILTER-NOUN-COMPOUND-PARSES-BY-TYPES", 3, 2, false);
        declareFunction("parse_noun_compound_valid_char_p", "PARSE-NOUN-COMPOUND-VALID-CHAR-P", 1, 0, false);
        declareFunction("clear_nl_tags", "CLEAR-NL-TAGS", 0, 0, false);
        declareFunction("clear_nl_tag_fns", "CLEAR-NL-TAG-FNS", 0, 0, false);
        declareFunction("clear_nl_tag_denoting_fns", "CLEAR-NL-TAG-DENOTING-FNS", 0, 0, false);
        declareFunction("initialize_nl_tags", "INITIALIZE-NL-TAGS", 0, 0, false);
        declareFunction("initialize_nl_tag_fns", "INITIALIZE-NL-TAG-FNS", 0, 0, false);
        declareFunction("initialize_nl_tag_denoting_fns", "INITIALIZE-NL-TAG-DENOTING-FNS", 0, 0, false);
        declareFunction("nl_tagP", "NL-TAG?", 1, 1, false);
        declareFunction("nl_tag_denoting_fnP", "NL-TAG-DENOTING-FN?", 1, 1, false);
        declareFunction("nl_tag_fnP", "NL-TAG-FN?", 1, 1, false);
        declareFunction("nl_tagged_termP", "NL-TAGGED-TERM?", 1, 0, false);
        declareFunction("ternary_nl_tagged_termP", "TERNARY-NL-TAGGED-TERM?", 1, 1, false);
        declareFunction("ternary_nl_tag_fnP", "TERNARY-NL-TAG-FN?", 1, 0, false);
        declareFunction("strip_nl_tags_top_level", "STRIP-NL-TAGS-TOP-LEVEL", 1, 0, false);
        declareFunction("possibly_strip_nl_tags_from_list", "POSSIBLY-STRIP-NL-TAGS-FROM-LIST", 2, 0, false);
        declareFunction("strip_nl_tags", "STRIP-NL-TAGS", 1, 1, false);
        declareFunction("get_nl_tag_fn", "GET-NL-TAG-FN", 2, 0, false);
        declareFunction("get_ternary_nl_tag_fn", "GET-TERNARY-NL-TAG-FN", 1, 0, false);
        declareFunction("get_binary_nl_tag_fn", "GET-BINARY-NL-TAG-FN", 1, 0, false);
        declareFunction("get_nl_tag_template", "GET-NL-TAG-TEMPLATE", 2, 1, false);
        declareFunction("clear_make_nl_tag_template", "CLEAR-MAKE-NL-TAG-TEMPLATE", 0, 0, false);
        declareFunction("remove_make_nl_tag_template", "REMOVE-MAKE-NL-TAG-TEMPLATE", 3, 0, false);
        declareFunction("make_nl_tag_template_internal", "MAKE-NL-TAG-TEMPLATE-INTERNAL", 3, 0, false);
        declareFunction("make_nl_tag_template", "MAKE-NL-TAG-TEMPLATE", 3, 0, false);
        declareFunction("add_nl_tag_template", "ADD-NL-TAG-TEMPLATE", 2, 0, false);
        declareFunction("contains_nl_tagP", "CONTAINS-NL-TAG?", 1, 0, false);
        declareFunction("add_nl_number_wrapper", "ADD-NL-NUMBER-WRAPPER", 2, 0, false);
        declareFunction("determiner_number_denotP_internal", "DETERMINER-NUMBER-DENOT?-INTERNAL", 1, 0, false);
        declareFunction("determiner_number_denotP", "DETERMINER-NUMBER-DENOT?", 1, 0, false);
        declareFunction("agr_preds_for_number", "AGR-PREDS-FOR-NUMBER", 1, 0, false);
        declareFunction("add_nl_quant_wrapper", "ADD-NL-QUANT-WRAPPER", 2, 2, false);
        declareFunction("clear_nl_max_floor_preds", "CLEAR-NL-MAX-FLOOR-PREDS", 0, 0, false);
        declareFunction("remove_nl_max_floor_preds", "REMOVE-NL-MAX-FLOOR-PREDS", 1, 0, false);
        declareFunction("nl_max_floor_preds_internal", "NL-MAX-FLOOR-PREDS-INTERNAL", 1, 0, false);
        declareFunction("nl_max_floor_preds", "NL-MAX-FLOOR-PREDS", 1, 0, false);
        declareFunction("cycl_has_definiteness_tagP", "CYCL-HAS-DEFINITENESS-TAG?", 1, 0, false);
        declareFunction("cycl_pronoun_p", "CYCL-PRONOUN-P", 1, 0, false);
        declareFunction("nl_person_attr_for_cycl_pronoun", "NL-PERSON-ATTR-FOR-CYCL-PRONOUN", 1, 0, false);
        declareFunction("nl_number_attr_for_cycl", "NL-NUMBER-ATTR-FOR-CYCL", 1, 0, false);
        declareFunction("nl_definiteness_attr_for_cycl", "NL-DEFINITENESS-ATTR-FOR-CYCL", 1, 0, false);
        declareFunction("nl_gender_attr_for_cycl", "NL-GENDER-ATTR-FOR-CYCL", 1, 0, false);
        declareFunction("kb_nl_number_attribute_p", "KB-NL-NUMBER-ATTRIBUTE-P", 1, 0, false);
        declareFunction("kb_nl_definiteness_attribute_p", "KB-NL-DEFINITENESS-ATTRIBUTE-P", 1, 0, false);
        declareFunction("kb_nl_gender_attribute_p", "KB-NL-GENDER-ATTRIBUTE-P", 1, 0, false);
        declareFunction("nl_number_attributes_disagreeP", "NL-NUMBER-ATTRIBUTES-DISAGREE?", 2, 0, false);
        declareFunction("nl_gender_attributes_disagreeP", "NL-GENDER-ATTRIBUTES-DISAGREE?", 2, 0, false);
        declareFunction("cycls_disagree_in_nl_number_attributeP", "CYCLS-DISAGREE-IN-NL-NUMBER-ATTRIBUTE?", 2, 0, false);
        declareFunction("cycls_disagree_in_nl_gender_attributeP", "CYCLS-DISAGREE-IN-NL-GENDER-ATTRIBUTE?", 2, 0, false);
        declareFunction("nl_number_to_spp", "NL-NUMBER-TO-SPP", 1, 0, false);
        declareFunction("spp_to_nl_number", "SPP-TO-NL-NUMBER", 1, 0, false);
        declareFunction("init_spps_to_nl_numbers", "INIT-SPPS-TO-NL-NUMBERS", 0, 0, false);
        declareFunction("add_nl_def_wrapper", "ADD-NL-DEF-WRAPPER", 2, 3, false);
        declareFunction("parse_collection_expression", "PARSE-COLLECTION-EXPRESSION", 1, 0, false);
        declareFunction("make_collection_expression", "MAKE-COLLECTION-EXPRESSION", 3, 0, false);
        declareFunction("npp_init_excluded_name_preds", "NPP-INIT-EXCLUDED-NAME-PREDS", 0, 0, false);
        declareFunction("npp_name_preds_to_exclude_iterator", "NPP-NAME-PREDS-TO-EXCLUDE-ITERATOR", 0, 0, false);
        declareFunction("npp_excluded_name_predP", "NPP-EXCLUDED-NAME-PRED?", 1, 0, false);
        declareFunction("blacklisted_cycl_for_parsersP", "BLACKLISTED-CYCL-FOR-PARSERS?", 1, 0, false);
        declareFunction("psp_lexicon_root_mt", "PSP-LEXICON-ROOT-MT", 0, 1, false);
        declareFunction("psp_weight_to_float", "PSP-WEIGHT-TO-FLOAT", 1, 0, false);
        declareFunction("psp_weight_from_float", "PSP-WEIGHT-FROM-FLOAT", 1, 0, false);
        declareFunction("remove_keyword_literals", "REMOVE-KEYWORD-LITERALS", 1, 0, false);
        declareFunction("vp_parse_from_pspP", "VP-PARSE-FROM-PSP?", 1, 0, false);
        declareFunction("result_of_parsing_words", "RESULT-OF-PARSING-WORDS", 1, 0, false);
        declareFunction("result_of_parsing_span", "RESULT-OF-PARSING-SPAN", 1, 0, false);
        declareFunction("result_of_parsing_span_wXo_thelist", "RESULT-OF-PARSING-SPAN-W/O-THELIST", 1, 0, false);
        declareFunction("result_of_parsing_span_length", "RESULT-OF-PARSING-SPAN-LENGTH", 1, 0, false);
        declareFunction("result_of_parsing_category", "RESULT-OF-PARSING-CATEGORY", 1, 0, false);
        declareFunction("result_of_parsing_start", "RESULT-OF-PARSING-START", 1, 0, false);
        declareFunction("result_of_parsing_end", "RESULT-OF-PARSING-END", 1, 0, false);
        declareFunction("result_of_parsing_formulaP", "RESULT-OF-PARSING-FORMULA?", 1, 0, false);
        declareFunction("formula_contains_result_of_parsingP", "FORMULA-CONTAINS-RESULT-OF-PARSING?", 1, 0, false);
        declareFunction("formulas_containing_results_of_parsing", "FORMULAS-CONTAINING-RESULTS-OF-PARSING", 1, 0, false);
        declareFunction("token_list_from_span", "TOKEN-LIST-FROM-SPAN", 2, 0, false);
        declareFunction("result_of_parsing_highest_category", "RESULT-OF-PARSING-HIGHEST-CATEGORY", 1, 1, false);
        declareFunction("devisable_verb_frameP", "DEVISABLE-VERB-FRAME?", 1, 0, false);
        declareFunction("verb_semtrans_from_denot_rolesXframe", "VERB-SEMTRANS-FROM-DENOT-ROLES&FRAME", 3, 1, false);
        declareFunction("known_roles_for_denot", "KNOWN-ROLES-FOR-DENOT", 1, 1, false);
        declareFunction("known_subject_roles_for_denot", "KNOWN-SUBJECT-ROLES-FOR-DENOT", 1, 2, false);
        declareFunction("known_direct_object_roles_for_denot", "KNOWN-DIRECT-OBJECT-ROLES-FOR-DENOT", 1, 2, false);
        declareFunction("known_indirect_object_roles_for_denot", "KNOWN-INDIRECT-OBJECT-ROLES-FOR-DENOT", 1, 2, false);
        declareFunction("required_arg1_preds", "REQUIRED-ARG1-PREDS", 1, 3, false);
        declareFunction("remove_genl_preds", "REMOVE-GENL-PREDS", 1, 1, false);
        declareFunction("ra1p_do_one_event_type", "RA1P-DO-ONE-EVENT-TYPE", 1, 0, false);
        declareFunction("ra1p_do_one_event_typeXpred", "RA1P-DO-ONE-EVENT-TYPE&PRED", 3, 0, false);
        declareFunction("possibly_uniquify_variablefn", "POSSIBLY-UNIQUIFY-VARIABLEFN", 1, 0, false);
        declareFunction("unique_el_var_for_variablefn", "UNIQUE-EL-VAR-FOR-VARIABLEFN", 1, 0, false);
        declareFunction("good_type_for_var_nameP", "GOOD-TYPE-FOR-VAR-NAME?", 1, 0, false);
        declareFunction("variablefn_termP", "VARIABLEFN-TERM?", 1, 0, false);
        declareFunction("next_unique_var_name", "NEXT-UNIQUE-VAR-NAME", 1, 0, false);
        declareFunction("parsing_rewrite_of_inverseP", "PARSING-REWRITE-OF-INVERSE?", 2, 1, false);
        declareFunction("parsing_rewrite_ofP", "PARSING-REWRITE-OF?", 2, 1, false);
        declareFunction("parse_tree_creation_rules", "PARSE-TREE-CREATION-RULES", 0, 0, false);
        declareFunction("syntactic_node_p", "SYNTACTIC-NODE-P", 1, 1, false);
        declareFunction("lexical_nodeP", "LEXICAL-NODE?", 1, 0, false);
        declareFunction("phrasal_nodeP", "PHRASAL-NODE?", 1, 0, false);
        declareFunction("syntactic_choice_node_p", "SYNTACTIC-CHOICE-NODE-P", 1, 1, false);
        declareFunction("syntactic_node_dtrs", "SYNTACTIC-NODE-DTRS", 1, 0, false);
        declareFunction("syntactic_node_mother", "SYNTACTIC-NODE-MOTHER", 1, 0, false);
        declareFunction("syntactic_node_root_node", "SYNTACTIC-NODE-ROOT-NODE", 1, 0, false);
        declareFunction("syntactic_node_nth_dtr", "SYNTACTIC-NODE-NTH-DTR", 2, 0, false);
        declareFunction("syntactic_node_descendant_at_path", "SYNTACTIC-NODE-DESCENDANT-AT-PATH", 2, 0, false);
        declareFunction("assert_parse_tree_info", "ASSERT-PARSE-TREE-INFO", 1, 1, false);
        declareFunction("syntactic_node_propose_meaning", "SYNTACTIC-NODE-PROPOSE-MEANING", 2, 2, false);
        declareFunction("syntactic_node_note_semantic_dependency", "SYNTACTIC-NODE-NOTE-SEMANTIC-DEPENDENCY", 4, 1, false);
        declareFunction("syntactic_node_proposed_meanings", "SYNTACTIC-NODE-PROPOSED-MEANINGS", 1, 2, false);
        declareMacro("push_meanings_from_syntactic_node", "PUSH-MEANINGS-FROM-SYNTACTIC-NODE");
        declareMacro("push_node_meaning_pairs_from_syntactic_node", "PUSH-NODE-MEANING-PAIRS-FROM-SYNTACTIC-NODE");
        declareFunction("recently_created_parse_tree_elements", "RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
        declareMacro("remembering_new_parse_tree_elements", "REMEMBERING-NEW-PARSE-TREE-ELEMENTS");
        declareFunction("forget_recently_created_parse_tree_elements", "FORGET-RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
        declareFunction("kill_recently_created_parse_tree_elements", "KILL-RECENTLY-CREATED-PARSE-TREE-ELEMENTS", 0, 0, false);
        declareFunction("create_parse_tree_element", "CREATE-PARSE-TREE-ELEMENT", 1, 2, false);
        declareFunction("hypothesize_parse_tree_element", "HYPOTHESIZE-PARSE-TREE-ELEMENT", 1, 2, false);
        declareFunction("generate_choice_node", "GENERATE-CHOICE-NODE", 1, 2, false);
        declareFunction("choice_node_add_option_node", "CHOICE-NODE-ADD-OPTION-NODE", 2, 1, false);
        declareFunction("create_parse_tree", "CREATE-PARSE-TREE", 0, 2, false);
        declareFunction("assert_parse_tree_root_node", "ASSERT-PARSE-TREE-ROOT-NODE", 2, 1, false);
        declareFunction("create_parse_tree_with_root_node", "CREATE-PARSE-TREE-WITH-ROOT-NODE", 1, 2, false);
        declareFunction("content_mt_of_parse_tree", "CONTENT-MT-OF-PARSE-TREE", 1, 0, false);
        declareFunction("create_syntactic_node", "CREATE-SYNTACTIC-NODE", 6, 3, false);
        declareFunction("create_phrasal_node", "CREATE-PHRASAL-NODE", 8, 0, false);
        declareFunction("assert_start_offset_for_token", "ASSERT-START-OFFSET-FOR-TOKEN", 1, 3, false);
        declareFunction("syntactic_node_string", "SYNTACTIC-NODE-STRING", 1, 0, false);
        declareFunction("syntactic_node_start_offset", "SYNTACTIC-NODE-START-OFFSET", 1, 0, false);
        declareFunction("syntactic_node_category", "SYNTACTIC-NODE-CATEGORY", 1, 0, false);
        declareFunction("syntactic_node_agreement_pred", "SYNTACTIC-NODE-AGREEMENT-PRED", 1, 0, false);
        declareFunction("syntactic_node_tree", "SYNTACTIC-NODE-TREE", 1, 0, false);
        declareFunction("syntactic_node_penntag", "SYNTACTIC-NODE-PENNTAG", 1, 0, false);
        declareFunction("parse_tree_content_mt", "PARSE-TREE-CONTENT-MT", 1, 0, false);
        declareFunction("syntactic_node_content_mt", "SYNTACTIC-NODE-CONTENT-MT", 1, 0, false);
        declareFunction("syntactic_node_span", "SYNTACTIC-NODE-SPAN", 2, 0, false);
        declareFunction("create_lexical_node", "CREATE-LEXICAL-NODE", 7, 0, false);
        declareFunction("syntactic_node_add_dtr", "SYNTACTIC-NODE-ADD-DTR", 2, 2, false);
        declareFunction("syntactic_node_add_head_dtr", "SYNTACTIC-NODE-ADD-HEAD-DTR", 2, 2, false);
        declareFunction("package_node_meaning", "PACKAGE-NODE-MEANING", 1, 0, false);
        declareFunction("syntactic_node_to_nested_list", "SYNTACTIC-NODE-TO-NESTED-LIST", 1, 0, false);
        declareFunction("phrasal_node_string_from_dtrs", "PHRASAL-NODE-STRING-FROM-DTRS", 1, 0, false);
        declareFunction("phrasal_node_span_from_dtrs", "PHRASAL-NODE-SPAN-FROM-DTRS", 2, 0, false);
        declareFunction("syntactic_node_span_start", "SYNTACTIC-NODE-SPAN-START", 2, 0, false);
        declareFunction("syntactic_node_span_end", "SYNTACTIC-NODE-SPAN-END", 2, 0, false);
        declareFunction("initialize_penntag_phrase_categories", "INITIALIZE-PENNTAG-PHRASE-CATEGORIES", 0, 0, false);
        declareFunction("penntag_phrase_categories", "PENNTAG-PHRASE-CATEGORIES", 0, 0, false);
        declareFunction("cycl_penntag_p", "CYCL-PENNTAG-P", 1, 0, false);
        declareFunction("penntag_category", "PENNTAG-CATEGORY", 1, 0, false);
        declareFunction("denots_of_stringXpenntag", "DENOTS-OF-STRING&PENNTAG", 2, 0, false);
        declareFunction("initialize_penntag_forts", "INITIALIZE-PENNTAG-FORTS", 0, 0, false);
        declareFunction("penntag_forts", "PENNTAG-FORTS", 0, 0, false);
        declareFunction("penntag_to_cycl", "PENNTAG-TO-CYCL", 1, 0, false);
        declareFunction("cycl_penntag_to_keyword", "CYCL-PENNTAG-TO-KEYWORD", 1, 0, false);
        declareFunction("find_or_create_parse_token", "FIND-OR-CREATE-PARSE-TOKEN", 2, 2, false);
        declareFunction("find_parse_token", "FIND-PARSE-TOKEN", 2, 0, false);
        declareFunction("create_parse_token", "CREATE-PARSE-TOKEN", 2, 2, false);
        declareFunction("compute_parse_token_start_offset", "COMPUTE-PARSE-TOKEN-START-OFFSET", 3, 0, false);
        declareFunction("token_start_offset", "TOKEN-START-OFFSET", 1, 0, false);
        declareFunction("token_number", "TOKEN-NUMBER", 1, 0, false);
        declareFunction("token_string", "TOKEN-STRING", 1, 0, false);
        declareFunction("token_tokenization", "TOKEN-TOKENIZATION", 1, 0, false);
        declareFunction("tokenization_peg", "TOKENIZATION-PEG", 1, 0, false);
        declareFunction("tokenization_tokens", "TOKENIZATION-TOKENS", 1, 0, false);
        declareFunction("tokenization_input_string", "TOKENIZATION-INPUT-STRING", 1, 0, false);
        declareFunction("tokenization_token_count", "TOKENIZATION-TOKEN-COUNT", 1, 0, false);
        declareFunction("create_token_mapping", "CREATE-TOKEN-MAPPING", 2, 2, false);
        declareFunction("update_token_mapping", "UPDATE-TOKEN-MAPPING", 3, 0, false);
        declareFunction("construct_parse_expression_from_syntactic_node", "CONSTRUCT-PARSE-EXPRESSION-FROM-SYNTACTIC-NODE", 1, 0, false);
        declareFunction("construct_subl_parse_tree_from_syntactic_node", "CONSTRUCT-SUBL-PARSE-TREE-FROM-SYNTACTIC-NODE", 1, 1, false);
        declareFunction("add_parse_tree_to_node_mappings", "ADD-PARSE-TREE-TO-NODE-MAPPINGS", 3, 0, false);
        declareMacro("with_parsing_filter_piles", "WITH-PARSING-FILTER-PILES");
        declareMacro("parsing_filter_accepts", "PARSING-FILTER-ACCEPTS");
        declareMacro("parsing_filter_rejects", "PARSING-FILTER-REJECTS");
        declareMacro("apply_elimination_parsing_filter", "APPLY-ELIMINATION-PARSING-FILTER");
        declareMacro("save_parsing_filter_piles", "SAVE-PARSING-FILTER-PILES");
        declareFunction("apply_boolean_parsing_filter", "APPLY-BOOLEAN-PARSING-FILTER", 3, 1, false);
        declareFunction("apply_macroscopic_parsing_filter", "APPLY-MACROSCOPIC-PARSING-FILTER", 3, 1, false);
        declareFunction("parsing_subcollection_function_nautP", "PARSING-SUBCOLLECTION-FUNCTION-NAUT?", 1, 0, false);
        declareFunction("ternary_parsing_subcollection_function_nautP", "TERNARY-PARSING-SUBCOLLECTION-FUNCTION-NAUT?", 1, 0, false);
        declareFunction("subcollection_function_naut_from_arg", "SUBCOLLECTION-FUNCTION-NAUT-FROM-ARG", 1, 0, false);
        declareFunction("subcollection_function_type_level_in_from_argP", "SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-FROM-ARG?", 1, 0, false);
        declareFunction("subcollection_function_type_level_in_to_argP", "SUBCOLLECTION-FUNCTION-TYPE-LEVEL-IN-TO-ARG?", 1, 0, false);
        declareFunction("subcollection_function_naut_to_arg", "SUBCOLLECTION-FUNCTION-NAUT-TO-ARG", 1, 0, false);
        declareFunction("subcollection_function_naut_pred", "SUBCOLLECTION-FUNCTION-NAUT-PRED", 1, 0, false);
        declareFunction("subcollection_function_naut_with_new_from_arg", "SUBCOLLECTION-FUNCTION-NAUT-WITH-NEW-FROM-ARG", 2, 0, false);
        declareFunction("subcollection_function_naut_with_new_to_arg", "SUBCOLLECTION-FUNCTION-NAUT-WITH-NEW-TO-ARG", 2, 0, false);
        declareFunction("eliminate_genl_subcollection_nauts", "ELIMINATE-GENL-SUBCOLLECTION-NAUTS", 1, 0, false);
        declareFunction("clear_parse_as_typeP", "CLEAR-PARSE-AS-TYPE?", 0, 0, false);
        declareFunction("remove_parse_as_typeP", "REMOVE-PARSE-AS-TYPE?", 3, 0, false);
        declareFunction("parse_as_typeP_internal", "PARSE-AS-TYPE?-INTERNAL", 3, 0, false);
        declareFunction("parse_as_typeP", "PARSE-AS-TYPE?", 3, 0, false);
        declareFunction("restrict_parses_by_types", "RESTRICT-PARSES-BY-TYPES", 3, 1, false);
        declareFunction("restrict_parse_to_type", "RESTRICT-PARSE-TO-TYPE", 2, 0, false);
        declareFunction("parse_dates_and_numbers", "PARSE-DATES-AND-NUMBERS", 3, 2, false);
        declareFunction("parse_iterator_print_function_trampoline", "PARSE-ITERATOR-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("parse_iterator_p", "PARSE-ITERATOR-P", 1, 0, false);
        new parsing_utilities.$parse_iterator_p$UnaryFunction();
        declareFunction("pi_sub_iterators", "PI-SUB-ITERATORS", 1, 0, false);
        declareFunction("pi_properties", "PI-PROPERTIES", 1, 0, false);
        declareFunction("pi_current_iterator_index", "PI-CURRENT-ITERATOR-INDEX", 1, 0, false);
        declareFunction("pi_string", "PI-STRING", 1, 0, false);
        declareFunction("pi_previous_parses", "PI-PREVIOUS-PARSES", 1, 0, false);
        declareFunction("pi_next", "PI-NEXT", 1, 0, false);
        declareFunction("_csetf_pi_sub_iterators", "_CSETF-PI-SUB-ITERATORS", 2, 0, false);
        declareFunction("_csetf_pi_properties", "_CSETF-PI-PROPERTIES", 2, 0, false);
        declareFunction("_csetf_pi_current_iterator_index", "_CSETF-PI-CURRENT-ITERATOR-INDEX", 2, 0, false);
        declareFunction("_csetf_pi_string", "_CSETF-PI-STRING", 2, 0, false);
        declareFunction("_csetf_pi_previous_parses", "_CSETF-PI-PREVIOUS-PARSES", 2, 0, false);
        declareFunction("_csetf_pi_next", "_CSETF-PI-NEXT", 2, 0, false);
        declareFunction("make_parse_iterator", "MAKE-PARSE-ITERATOR", 0, 1, false);
        declareFunction("visit_defstruct_parse_iterator", "VISIT-DEFSTRUCT-PARSE-ITERATOR", 2, 0, false);
        declareFunction("visit_defstruct_object_parse_iterator_method", "VISIT-DEFSTRUCT-OBJECT-PARSE-ITERATOR-METHOD", 2, 0, false);
        declareFunction("print_parse_iterator", "PRINT-PARSE-ITERATOR", 1, 2, false);
        declareFunction("new_parse_iterator", "NEW-PARSE-ITERATOR", 1, 1, false);
        declareFunction("new_parse_iterator_state", "NEW-PARSE-ITERATOR-STATE", 1, 1, false);
        declareFunction("parsing_subiterators", "PARSING-SUBITERATORS", 2, 0, false);
        declareFunction("pi_current_iterator", "PI-CURRENT-ITERATOR", 1, 0, false);
        declareFunction("parse_iterator_done", "PARSE-ITERATOR-DONE", 1, 0, false);
        declareFunction("parse_iterator_has_next", "PARSE-ITERATOR-HAS-NEXT", 1, 0, false);
        declareFunction("parse_iterator_next", "PARSE-ITERATOR-NEXT", 1, 0, false);
        declareFunction("cyclify_iterator_print_function_trampoline", "CYCLIFY-ITERATOR-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("cyclify_iterator_p", "CYCLIFY-ITERATOR-P", 1, 0, false);
        new parsing_utilities.$cyclify_iterator_p$UnaryFunction();
        declareFunction("cyclify_iterator_iter_obj", "CYCLIFY-ITERATOR-ITER-OBJ", 1, 0, false);
        declareFunction("_csetf_cyclify_iterator_iter_obj", "_CSETF-CYCLIFY-ITERATOR-ITER-OBJ", 2, 0, false);
        declareFunction("make_cyclify_iterator", "MAKE-CYCLIFY-ITERATOR", 0, 1, false);
        declareFunction("visit_defstruct_cyclify_iterator", "VISIT-DEFSTRUCT-CYCLIFY-ITERATOR", 2, 0, false);
        declareFunction("visit_defstruct_object_cyclify_iterator_method", "VISIT-DEFSTRUCT-OBJECT-CYCLIFY-ITERATOR-METHOD", 2, 0, false);
        declareFunction("new_cyclify_iterator", "NEW-CYCLIFY-ITERATOR", 1, 1, false);
        declareFunction("new_cyclify_iterator_state", "NEW-CYCLIFY-ITERATOR-STATE", 1, 1, false);
        declareFunction("cyclify_iterator_done", "CYCLIFY-ITERATOR-DONE", 1, 0, false);
        declareFunction("cyclify_iterator_next", "CYCLIFY-ITERATOR-NEXT", 1, 0, false);
        declareFunction("complete_text_topic_structure_tagging", "COMPLETE-TEXT-TOPIC-STRUCTURE-TAGGING", 1, 0, false);
        declareFunction("speaker_referent", "SPEAKER-REFERENT", 0, 0, false);
        declareFunction("get_cyclifier_interps_for_expression_peg", "GET-CYCLIFIER-INTERPS-FOR-EXPRESSION-PEG", 1, 0, false);
        declareFunction("peg_text", "PEG-TEXT", 1, 0, false);
        declareFunction("get_cyclifier_interps_for_string", "GET-CYCLIFIER-INTERPS-FOR-STRING", 1, 4, false);
        declareFunction("record_suggested_nl_for_assertion", "RECORD-SUGGESTED-NL-FOR-ASSERTION", 4, 2, false);
        declareFunction("record_suggested_nl_in_kb", "RECORD-SUGGESTED-NL-IN-KB", 2, 0, false);
        declareFunction("record_suggested_nl_to_file", "RECORD-SUGGESTED-NL-TO-FILE", 4, 2, false);
        return NIL;
    }

    public static final SubLObject init_parsing_utilities_file_alt() {
        deflexical("*PARSE-NOUN-COMPOUND-VALID-NON-ALPHA-CHARS*", $str_alt16$___0123456789);
        defparameter("*NL-TAGS*", dictionary.new_dictionary(symbol_function(EQ), SIXTEEN_INTEGER));
        defparameter("*NL-TAG-FNS*", dictionary.new_dictionary(symbol_function(EQ), SIXTEEN_INTEGER));
        defparameter("*NL-TAG-DENOTING-FNS*", dictionary.new_dictionary(symbol_function(EQ), SIXTEEN_INTEGER));
        deflexical("*MAKE-NL-TAG-TEMPLATE-CACHING-STATE*", NIL);
        deflexical("*NL-MAX-FLOOR-PREDS-CACHING-STATE*", NIL);
        deflexical("*SPPS-TO-NL-NUMBERS*", NIL != boundp($spps_to_nl_numbers$) ? ((SubLObject) ($spps_to_nl_numbers$.getGlobalValue())) : make_hash_table($int$128, UNPROVIDED, UNPROVIDED));
        defparameter("*NPP-NAME-PREDS-TO-EXCLUDE*", NIL);
        defparameter("*RA1P-RAE-PREDS*", NIL);
        defparameter("*RA1P-RA1-PREDS*", NIL);
        deflexical("*RA1P-SHOW-PAIRS?*", NIL);
        deflexical("*RECENTLY-CREATED-PARSE-TREE-ELEMENTS*", NIL);
        defparameter("*REMEMBER-NEW-PARSE-TREE-ELEMENTS?*", NIL);
        deflexical("*PENNTAG-PHRASE-CATEGORIES*", NIL != boundp($penntag_phrase_categories$) ? ((SubLObject) ($penntag_phrase_categories$.getGlobalValue())) : $UNINITIALIZED);
        deflexical("*PENNTAG-FORTS*", NIL != boundp($penntag_forts$) ? ((SubLObject) ($penntag_forts$.getGlobalValue())) : $UNINITIALIZED);
        defparameter("*GOOD-PARSING-PILE*", NIL);
        defparameter("*BAD-PARSING-PILE*", NIL);
        deflexical("*PARSE-AS-TYPE?-CACHING-STATE*", NIL);
        defconstant("*DTP-PARSE-ITERATOR*", PARSE_ITERATOR);
        defconstant("*DTP-CYCLIFY-ITERATOR*", CYCLIFY_ITERATOR);
        return NIL;
    }

    public static SubLObject init_parsing_utilities_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*PARSE-NOUN-COMPOUND-VALID-NON-ALPHA-CHARS*", $str14$___0123456789);
            defparameter("*NL-TAGS*", dictionary.new_dictionary(symbol_function(EQL), SIXTEEN_INTEGER));
            defparameter("*NL-TAG-FNS*", dictionary.new_dictionary(symbol_function(EQL), SIXTEEN_INTEGER));
            defparameter("*NL-TAG-DENOTING-FNS*", dictionary.new_dictionary(symbol_function(EQL), SIXTEEN_INTEGER));
            deflexical("*MAKE-NL-TAG-TEMPLATE-CACHING-STATE*", NIL);
            deflexical("*NL-MAX-FLOOR-PREDS-CACHING-STATE*", NIL);
            deflexical("*SPPS-TO-NL-NUMBERS*", SubLTrampolineFile.maybeDefault($spps_to_nl_numbers$, $spps_to_nl_numbers$, () -> make_hash_table($int$128, UNPROVIDED, UNPROVIDED)));
            defparameter("*NPP-NAME-PREDS-TO-EXCLUDE*", NIL);
            defparameter("*RA1P-RAE-PREDS*", NIL);
            defparameter("*RA1P-RA1-PREDS*", NIL);
            deflexical("*RA1P-SHOW-PAIRS?*", NIL);
            deflexical("*RECENTLY-CREATED-PARSE-TREE-ELEMENTS*", NIL);
            defparameter("*REMEMBER-NEW-PARSE-TREE-ELEMENTS?*", NIL);
            deflexical("*PENNTAG-PHRASE-CATEGORIES*", SubLTrampolineFile.maybeDefault($penntag_phrase_categories$, $penntag_phrase_categories$, $UNINITIALIZED));
            deflexical("*PENNTAG-FORTS*", SubLTrampolineFile.maybeDefault($penntag_forts$, $penntag_forts$, $UNINITIALIZED));
            defparameter("*GOOD-PARSING-PILE*", NIL);
            defparameter("*BAD-PARSING-PILE*", NIL);
            deflexical("*PARSE-AS-TYPE?-CACHING-STATE*", NIL);
            defconstant("*DTP-PARSE-ITERATOR*", PARSE_ITERATOR);
            defconstant("*DTP-CYCLIFY-ITERATOR*", CYCLIFY_ITERATOR);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*PARSE-NOUN-COMPOUND-VALID-NON-ALPHA-CHARS*", $str_alt16$___0123456789);
            defparameter("*NL-TAGS*", dictionary.new_dictionary(symbol_function(EQ), SIXTEEN_INTEGER));
            defparameter("*NL-TAG-FNS*", dictionary.new_dictionary(symbol_function(EQ), SIXTEEN_INTEGER));
            defparameter("*NL-TAG-DENOTING-FNS*", dictionary.new_dictionary(symbol_function(EQ), SIXTEEN_INTEGER));
            deflexical("*SPPS-TO-NL-NUMBERS*", NIL != boundp($spps_to_nl_numbers$) ? ((SubLObject) ($spps_to_nl_numbers$.getGlobalValue())) : make_hash_table($int$128, UNPROVIDED, UNPROVIDED));
            deflexical("*PENNTAG-PHRASE-CATEGORIES*", NIL != boundp($penntag_phrase_categories$) ? ((SubLObject) ($penntag_phrase_categories$.getGlobalValue())) : $UNINITIALIZED);
            deflexical("*PENNTAG-FORTS*", NIL != boundp($penntag_forts$) ? ((SubLObject) ($penntag_forts$.getGlobalValue())) : $UNINITIALIZED);
        }
        return NIL;
    }

    public static SubLObject init_parsing_utilities_file_Previous() {
        deflexical("*PARSE-NOUN-COMPOUND-VALID-NON-ALPHA-CHARS*", $str14$___0123456789);
        defparameter("*NL-TAGS*", dictionary.new_dictionary(symbol_function(EQL), SIXTEEN_INTEGER));
        defparameter("*NL-TAG-FNS*", dictionary.new_dictionary(symbol_function(EQL), SIXTEEN_INTEGER));
        defparameter("*NL-TAG-DENOTING-FNS*", dictionary.new_dictionary(symbol_function(EQL), SIXTEEN_INTEGER));
        deflexical("*MAKE-NL-TAG-TEMPLATE-CACHING-STATE*", NIL);
        deflexical("*NL-MAX-FLOOR-PREDS-CACHING-STATE*", NIL);
        deflexical("*SPPS-TO-NL-NUMBERS*", SubLTrampolineFile.maybeDefault($spps_to_nl_numbers$, $spps_to_nl_numbers$, () -> make_hash_table($int$128, UNPROVIDED, UNPROVIDED)));
        defparameter("*NPP-NAME-PREDS-TO-EXCLUDE*", NIL);
        defparameter("*RA1P-RAE-PREDS*", NIL);
        defparameter("*RA1P-RA1-PREDS*", NIL);
        deflexical("*RA1P-SHOW-PAIRS?*", NIL);
        deflexical("*RECENTLY-CREATED-PARSE-TREE-ELEMENTS*", NIL);
        defparameter("*REMEMBER-NEW-PARSE-TREE-ELEMENTS?*", NIL);
        deflexical("*PENNTAG-PHRASE-CATEGORIES*", SubLTrampolineFile.maybeDefault($penntag_phrase_categories$, $penntag_phrase_categories$, $UNINITIALIZED));
        deflexical("*PENNTAG-FORTS*", SubLTrampolineFile.maybeDefault($penntag_forts$, $penntag_forts$, $UNINITIALIZED));
        defparameter("*GOOD-PARSING-PILE*", NIL);
        defparameter("*BAD-PARSING-PILE*", NIL);
        deflexical("*PARSE-AS-TYPE?-CACHING-STATE*", NIL);
        defconstant("*DTP-PARSE-ITERATOR*", PARSE_ITERATOR);
        defconstant("*DTP-CYCLIFY-ITERATOR*", CYCLIFY_ITERATOR);
        return NIL;
    }

    public static final SubLObject setup_parsing_utilities_file_alt() {
        memoization_state.note_memoized_function(INSTANCES_OF_COLLECTION_PARSE);
        memoization_state.note_globally_cached_function(MAKE_NL_TAG_TEMPLATE);
        memoization_state.note_memoized_function($sym56$DETERMINER_NUMBER_DENOT_);
        memoization_state.note_globally_cached_function(NL_MAX_FLOOR_PREDS);
        declare_defglobal($spps_to_nl_numbers$);
        declare_defglobal($penntag_phrase_categories$);
        declare_defglobal($penntag_forts$);
        memoization_state.note_globally_cached_function($sym257$PARSE_AS_TYPE_);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_parse_iterator$.getGlobalValue(), symbol_function(PARSE_ITERATOR_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(PI_SUB_ITERATORS, _CSETF_PI_SUB_ITERATORS);
        def_csetf(PI_PROPERTIES, _CSETF_PI_PROPERTIES);
        def_csetf(PI_CURRENT_ITERATOR_INDEX, _CSETF_PI_CURRENT_ITERATOR_INDEX);
        def_csetf(PI_STRING, _CSETF_PI_STRING);
        def_csetf(PI_PREVIOUS_PARSES, _CSETF_PI_PREVIOUS_PARSES);
        def_csetf(PI_NEXT, _CSETF_PI_NEXT);
        identity(PARSE_ITERATOR);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_cyclify_iterator$.getGlobalValue(), symbol_function(CYCLIFY_ITERATOR_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(CYCLIFY_ITERATOR_ITER_OBJ, _CSETF_CYCLIFY_ITERATOR_ITER_OBJ);
        identity(CYCLIFY_ITERATOR);
        sunit_macros.$current_module_sunit_tests$.setDynamicValue(cons(PARSE_DATES_AND_NUMBERS_TEST_CASE, sunit_macros.$current_module_sunit_tests$.getDynamicValue()));
        sunit_macros.define_test_case_preamble(PARSE_DATES_AND_NUMBERS_TEST_CASE);
        classes.subloop_new_class(PARSE_DATES_AND_NUMBERS_TEST_CASE, TEST_CASE, NIL, NIL, $list_alt338);
        classes.class_set_implements_slot_listeners(PARSE_DATES_AND_NUMBERS_TEST_CASE, NIL);
        classes.subloop_note_class_initialization_function(PARSE_DATES_AND_NUMBERS_TEST_CASE, $sym347$SUBLOOP_RESERVED_INITIALIZE_PARSE_DATES_AND_NUMBERS_TEST_CASE_CLA);
        classes.subloop_note_instance_initialization_function(PARSE_DATES_AND_NUMBERS_TEST_CASE, $sym352$SUBLOOP_RESERVED_INITIALIZE_PARSE_DATES_AND_NUMBERS_TEST_CASE_INS);
        subloop_reserved_initialize_parse_dates_and_numbers_test_case_class(PARSE_DATES_AND_NUMBERS_TEST_CASE);
        sunit_macros.define_test_case_postamble(PARSE_DATES_AND_NUMBERS_TEST_CASE, $str_alt353$parsing_utilities, $$$cycl, NIL);
        sunit_macros.def_test_method_register(PARSE_DATES_AND_NUMBERS_TEST_CASE, ENSURE_NO_NON_DATE_NON_NUMBER_PARSES);
        sunit_external.define_test_suite($$$Parsing_Utilities_Test_Suite, NIL, $list_alt357, UNPROVIDED);
        return NIL;
    }

    public static SubLObject setup_parsing_utilities_file() {
        if (SubLFiles.USE_V1) {
            memoization_state.note_memoized_function(INSTANCES_OF_COLLECTION_PARSE);
            memoization_state.note_globally_cached_function(MAKE_NL_TAG_TEMPLATE);
            memoization_state.note_memoized_function($sym55$DETERMINER_NUMBER_DENOT_);
            memoization_state.note_globally_cached_function(NL_MAX_FLOOR_PREDS);
            declare_defglobal($spps_to_nl_numbers$);
            declare_defglobal($penntag_phrase_categories$);
            declare_defglobal($penntag_forts$);
            memoization_state.note_globally_cached_function($sym254$PARSE_AS_TYPE_);
            register_method($print_object_method_table$.getGlobalValue(), $dtp_parse_iterator$.getGlobalValue(), symbol_function(PARSE_ITERATOR_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list272);
            def_csetf(PI_SUB_ITERATORS, _CSETF_PI_SUB_ITERATORS);
            def_csetf(PI_PROPERTIES, _CSETF_PI_PROPERTIES);
            def_csetf(PI_CURRENT_ITERATOR_INDEX, _CSETF_PI_CURRENT_ITERATOR_INDEX);
            def_csetf(PI_STRING, _CSETF_PI_STRING);
            def_csetf(PI_PREVIOUS_PARSES, _CSETF_PI_PREVIOUS_PARSES);
            def_csetf(PI_NEXT, _CSETF_PI_NEXT);
            identity(PARSE_ITERATOR);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_parse_iterator$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_PARSE_ITERATOR_METHOD));
            register_method($print_object_method_table$.getGlobalValue(), $dtp_cyclify_iterator$.getGlobalValue(), symbol_function(CYCLIFY_ITERATOR_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list314);
            def_csetf(CYCLIFY_ITERATOR_ITER_OBJ, _CSETF_CYCLIFY_ITERATOR_ITER_OBJ);
            identity(CYCLIFY_ITERATOR);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_cyclify_iterator$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_CYCLIFY_ITERATOR_METHOD));
        }
        if (SubLFiles.USE_V2) {
            memoization_state.note_memoized_function($sym56$DETERMINER_NUMBER_DENOT_);
            memoization_state.note_globally_cached_function($sym257$PARSE_AS_TYPE_);
            sunit_macros.$current_module_sunit_tests$.setDynamicValue(cons(PARSE_DATES_AND_NUMBERS_TEST_CASE, sunit_macros.$current_module_sunit_tests$.getDynamicValue()));
            sunit_macros.define_test_case_preamble(PARSE_DATES_AND_NUMBERS_TEST_CASE);
            classes.subloop_new_class(PARSE_DATES_AND_NUMBERS_TEST_CASE, TEST_CASE, NIL, NIL, $list_alt338);
            classes.class_set_implements_slot_listeners(PARSE_DATES_AND_NUMBERS_TEST_CASE, NIL);
            classes.subloop_note_class_initialization_function(PARSE_DATES_AND_NUMBERS_TEST_CASE, $sym347$SUBLOOP_RESERVED_INITIALIZE_PARSE_DATES_AND_NUMBERS_TEST_CASE_CLA);
            classes.subloop_note_instance_initialization_function(PARSE_DATES_AND_NUMBERS_TEST_CASE, $sym352$SUBLOOP_RESERVED_INITIALIZE_PARSE_DATES_AND_NUMBERS_TEST_CASE_INS);
            subloop_reserved_initialize_parse_dates_and_numbers_test_case_class(PARSE_DATES_AND_NUMBERS_TEST_CASE);
            sunit_macros.define_test_case_postamble(PARSE_DATES_AND_NUMBERS_TEST_CASE, $str_alt353$parsing_utilities, $$$cycl, NIL);
            sunit_macros.def_test_method_register(PARSE_DATES_AND_NUMBERS_TEST_CASE, ENSURE_NO_NON_DATE_NON_NUMBER_PARSES);
            sunit_external.define_test_suite($$$Parsing_Utilities_Test_Suite, NIL, $list_alt357, UNPROVIDED);
        }
        return NIL;
    }

    public static SubLObject setup_parsing_utilities_file_Previous() {
        memoization_state.note_memoized_function(INSTANCES_OF_COLLECTION_PARSE);
        memoization_state.note_globally_cached_function(MAKE_NL_TAG_TEMPLATE);
        memoization_state.note_memoized_function($sym55$DETERMINER_NUMBER_DENOT_);
        memoization_state.note_globally_cached_function(NL_MAX_FLOOR_PREDS);
        declare_defglobal($spps_to_nl_numbers$);
        declare_defglobal($penntag_phrase_categories$);
        declare_defglobal($penntag_forts$);
        memoization_state.note_globally_cached_function($sym254$PARSE_AS_TYPE_);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_parse_iterator$.getGlobalValue(), symbol_function(PARSE_ITERATOR_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list272);
        def_csetf(PI_SUB_ITERATORS, _CSETF_PI_SUB_ITERATORS);
        def_csetf(PI_PROPERTIES, _CSETF_PI_PROPERTIES);
        def_csetf(PI_CURRENT_ITERATOR_INDEX, _CSETF_PI_CURRENT_ITERATOR_INDEX);
        def_csetf(PI_STRING, _CSETF_PI_STRING);
        def_csetf(PI_PREVIOUS_PARSES, _CSETF_PI_PREVIOUS_PARSES);
        def_csetf(PI_NEXT, _CSETF_PI_NEXT);
        identity(PARSE_ITERATOR);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_parse_iterator$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_PARSE_ITERATOR_METHOD));
        register_method($print_object_method_table$.getGlobalValue(), $dtp_cyclify_iterator$.getGlobalValue(), symbol_function(CYCLIFY_ITERATOR_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list314);
        def_csetf(CYCLIFY_ITERATOR_ITER_OBJ, _CSETF_CYCLIFY_ITERATOR_ITER_OBJ);
        identity(CYCLIFY_ITERATOR);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_cyclify_iterator$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_CYCLIFY_ITERATOR_METHOD));
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_parsing_utilities_file();
    }

    @Override
    public void initializeVariables() {
        init_parsing_utilities_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_parsing_utilities_file();
    }

    static {
    }

    public static final class $parse_iterator_p$UnaryFunction extends UnaryFunction {
        public $parse_iterator_p$UnaryFunction() {
            super(extractFunctionNamed("PARSE-ITERATOR-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return parse_iterator_p(arg1);
        }
    }

    public static final class $cyclify_iterator_native extends SubLStructNative {
        public SubLObject $iter_obj;

        private static final SubLStructDeclNative structDecl;

        public $cyclify_iterator_native() {
            parsing_utilities.$cyclify_iterator_native.this.$iter_obj = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return parsing_utilities.$cyclify_iterator_native.this.$iter_obj;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return parsing_utilities.$cyclify_iterator_native.this.$iter_obj = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.parsing_utilities.$cyclify_iterator_native.class, CYCLIFY_ITERATOR, CYCLIFY_ITERATOR_P, $list308, $list309, new String[]{ "$iter_obj" }, $list310, $list311, DEFAULT_STRUCT_PRINT_FUNCTION);
        }
    }

    public static final class $cyclify_iterator_p$UnaryFunction extends UnaryFunction {
        public $cyclify_iterator_p$UnaryFunction() {
            super(extractFunctionNamed("CYCLIFY-ITERATOR-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return cyclify_iterator_p(arg1);
        }
    }

    static private final SubLString $str_alt4$Invalid_attempt_to_reuse_memoizat = makeString("Invalid attempt to reuse memoization state in multiple threads simultaneously.");

    static private final SubLSymbol $sym8$_X = makeSymbol("?X");

    public static final SubLSymbol $kw13$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLString $str_alt16$___0123456789 = makeString(" -'0123456789");

    static private final SubLString $str_alt25$_A_is_not_a__A = makeString("~A is not a ~A");

    static private final SubLString $str_alt30$_A_is_not_a_valid__sbhl_type_erro = makeString("~A is not a valid *sbhl-type-error-action* value");

    static private final SubLString $str_alt34$attempting_to_bind_direction_link = makeString("attempting to bind direction link variable, to NIL. macro body not executed.");

    static private final SubLString $str_alt35$Node__a_does_not_pass_sbhl_type_t = makeString("Node ~a does not pass sbhl-type-test ~a~%");

    static private final SubLString $str_alt45$Can_t_find_ternary_NL_tag_fn_for_ = makeString("Can't find ternary NL tag fn for ~S");

    static private final SubLString $str_alt50$Can_t_find_binary_NL_tag_fn_for__ = makeString("Can't find binary NL tag fn for ~S");

    static private final SubLSymbol $sym53$NL_TAG_ = makeSymbol("NL-TAG?");

    static private final SubLString $str_alt54$Couldn_t_determine_NL_number_for_ = makeString("Couldn't determine NL number for ~S");

    static private final SubLList $list_alt55 = list(makeKeyword("ANYTHING"));

    static private final SubLSymbol $sym56$DETERMINER_NUMBER_DENOT_ = makeSymbol("DETERMINER-NUMBER-DENOT?");

    static private final SubLList $list_alt58 = list(reader_make_constant_shell("nounStrings"));

    static private final SubLList $list_alt59 = list(reader_make_constant_shell("plural-Generic"));

    static private final SubLList $list_alt60 = list(reader_make_constant_shell("singular-Generic"));

    static private final SubLString $str_alt61$No_common_specialization_for__S__ = makeString("No common specialization for ~S.~%");

    static private final SubLString $str_alt62$No_NL_Number_attribute_for__S___ = makeString("No NL Number attribute for ~S.~%");

    static private final SubLString $str_alt63$Multiple_specializations_for__S__ = makeString("Multiple specializations for ~S.~%");

    public static final SubLObject $const92$ProperNamePredicate_ExcludedFromN = reader_make_constant_shell("ProperNamePredicate-ExcludedFromNPParser");

    static private final SubLSymbol $sym103$RESULT_OF_PARSING_FORMULA_ = makeSymbol("RESULT-OF-PARSING-FORMULA?");

    static private final SubLString $str_alt105$_A_doesn_t_genl_to___ParsingTempl = makeString("~A doesn't genl to #$ParsingTemplateCategory");

    static private final SubLList $list_alt106 = list(makeSymbol("SUBJ-ROLES"), makeSymbol("DIR-OBJ-ROLES"), makeSymbol("INDIR-OBJ-ROLES"));

    static private final SubLList $list_alt110 = list(makeKeyword("ACTION"), makeKeyword("SUBJECT"));

    static private final SubLList $list_alt111 = list(makeKeyword("ACTION"), makeKeyword("OBJECT"));

    static private final SubLList $list_alt115 = list(reader_make_constant_shell("relationAllExists"), reader_make_constant_shell("relationAllExistsCount"), reader_make_constant_shell("relationAllExistsMany"), reader_make_constant_shell("relationAllExistsMin"));

    static private final SubLSymbol $sym119$GENL_PRED_ = makeSymbol("GENL-PRED?");

    static private final SubLSymbol $sym120$GENL_ = makeSymbol("GENL?");

    static private final SubLSymbol $sym122$SPEC_PRED_ = makeSymbol("SPEC-PRED?");

    static private final SubLList $list_alt123 = list(makeSymbol("EXISTING-VAR"), makeSymbol("TYPE"));

    static private final SubLString $str_alt126$_ = makeString("-");

    static private final SubLList $list_alt135 = list(makeSymbol("?DTR-NUM"), makeSymbol("?DTR"));

    static private final SubLList $list_alt136 = list(makeKeyword("RETURN"), list(makeKeyword("TEMPLATE"), list(makeSymbol("?DTR"), makeSymbol("?DTR-NUM"))), makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"));

    static private final SubLSymbol $sym137$_ = makeSymbol("<");

    static private final SubLList $list_alt139 = list(makeSymbol("?DTR"));

    static private final SubLList $list_alt140 = list(makeKeyword("RETURN"), list(makeKeyword("TEMPLATE"), makeSymbol("?DTR")), makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"));

    static private final SubLSymbol $sym141$_MOTHER = makeSymbol("?MOTHER");

    static private final SubLList $list_alt142 = list(makeKeyword("RETURN"), list(makeKeyword("TEMPLATE"), makeSymbol("?MOTHER")), makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"), makeKeyword("MAX-NUMBER"), ONE_INTEGER);

    static private final SubLSymbol $sym145$_MEANING = makeSymbol("?MEANING");

    static private final SubLList $list_alt146 = list(makeSymbol("?MEANING"));

    static private final SubLList $list_alt147 = list(makeSymbol("MEANINGS"), makeSymbol("NODE"), makeSymbol("&KEY"), list(makeSymbol("MT"), makeSymbol("*PARSE-TREE-MT*")), list(makeSymbol("PRED"), reader_make_constant_shell("proposedMeaning")));

    static private final SubLList $list_alt148 = list(makeKeyword("MT"), $PRED);

    static private final SubLSymbol $sym152$THIS_MEANING = makeUninternedSymbol("THIS-MEANING");

    static private final SubLList $list_alt156 = list(list(QUOTE, EQUAL));

    static private final SubLSymbol $sym157$THIS_MEANING = makeUninternedSymbol("THIS-MEANING");

    static private final SubLList $list_alt160 = list(list(makeSymbol("*REMEMBER-NEW-PARSE-TREE-ELEMENTS?*"), T));

    static private final SubLSymbol $sym162$_INSTANCE = makeSymbol("?INSTANCE");

    static private final SubLString $str_alt163$Can_t_hypothesize_instance_of__S_ = makeString("Can't hypothesize instance of ~S because~% ~S");

    static private final SubLString $str_alt174$Token_node_mismatch__Token___S_st = makeString("Token-node mismatch. Token: ~S string: ~S. Node string: ~S");

    static private final SubLList $list_alt184 = list(makeKeyword("TOKEN"));

    static private final SubLList $list_alt185 = list($BIND, makeSymbol("FIRST-TOKEN-NUM"));

    static private final SubLList $list_alt186 = list($BIND, makeSymbol("LAST-TOKEN-NUM"));

    static private final SubLString $str_alt189$Couldn_t_get_interval_span_for__S = makeString("Couldn't get interval span for ~S in ~S");

    static private final SubLSymbol $sym193$_N = makeSymbol("?N");

    static private final SubLList $list_alt194 = list(makeSymbol("?N"), makeSymbol("?NODE"));

    static private final SubLString $str_alt200$Couldn_t_find_string_for__S = makeString("Couldn't find string for ~S");

    static private final SubLString $str_alt201$_ = makeString(" ");

    static private final SubLString $str_alt202$Bad_dtrs___S = makeString("Bad dtrs: ~S");

    static private final SubLString $str_alt204$Can_t_get_span_start_from__S = makeString("Can't get span start from ~S");

    static private final SubLString $str_alt205$Can_t_get_span_end_from__S = makeString("Can't get span end from ~S");

    static private final SubLList $list_alt207 = cons(makeSymbol("?TAG"), makeSymbol("?CATEGORY"));

    static private final SubLList $list_alt208 = list(reader_make_constant_shell("syntacticCategoryTags"), makeSymbol("?CATEGORY"), makeSymbol("?TAG"));

    static private final SubLList $list_alt209 = list(makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"));

    static private final SubLList $list_alt212 = cons(makeSymbol("?TAG-STRING"), makeSymbol("?TAG"));

    static private final SubLList $list_alt213 = list(reader_make_constant_shell("pennTagString"), makeSymbol("?TAG"), makeSymbol("?TAG-STRING"));

    static private final SubLSymbol $sym215$_TOKEN = makeSymbol("?TOKEN");

    static private final SubLList $list_alt217 = list(makeSymbol("?TOKEN"));

    static private final SubLString $str_alt220$Failed_to_compute_start_offset_fo = makeString("Failed to compute start offset for ~S (~S) of ~S");

    static private final SubLSymbol $sym226$PHRASAL_NODE_ = makeSymbol("PHRASAL-NODE?");

    static private final SubLString $str_alt228$Couldn_t_find_dtr__S_of__S = makeString("Couldn't find dtr ~S of ~S");

    static private final SubLList $list_alt229 = list(list(makeSymbol("GOOD"), makeSymbol("&OPTIONAL"), makeSymbol("BAD")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt232 = list(makeSymbol("PARSE"));

    static private final SubLList $list_alt234 = list(makeSymbol("*GOOD-PARSING-PILES*"));

    static private final SubLList $list_alt235 = list(makeSymbol("PARSE"), makeSymbol("REASON"));

    static private final SubLList $list_alt236 = list(makeSymbol("*BAD-PARSING-PILE*"));

    static private final SubLList $list_alt237 = list(makeSymbol("FILTER"), makeSymbol("DENOT-ACCESSOR"), makeSymbol("DIAG-ACCESSOR"));

    static private final SubLSymbol $sym238$FILTER_RESPONSE = makeUninternedSymbol("FILTER-RESPONSE");

    static private final SubLSymbol $sym239$SOME_GOOD = makeUninternedSymbol("SOME-GOOD");

    static private final SubLSymbol $sym240$SOME_BAD = makeUninternedSymbol("SOME-BAD");

    static private final SubLSymbol $sym241$THIS_CANDIDATE = makeUninternedSymbol("THIS-CANDIDATE");

    static private final SubLList $list_alt242 = list(NIL);

    static private final SubLList $list_alt243 = list(makeSymbol("*GOOD-PARSING-PILE*"));

    static private final SubLList $list_alt250 = list(makeSymbol("GOOD"), makeSymbol("BAD"));

    public static final SubLObject $const252$SubcollectionRelationFunction_Can = reader_make_constant_shell("SubcollectionRelationFunction-Canonical");

    public static final SubLObject $const253$SubcollectionRelationFunction_Typ = reader_make_constant_shell("SubcollectionRelationFunction-TypeLevel");

    public static final SubLObject $const254$SubcollectionRelationFunction_Inv = reader_make_constant_shell("SubcollectionRelationFunction-Inverse");

    static private final SubLList $list_alt255 = list(makeSymbol("THIS-ARG1"), makeSymbol("THIS-PRED"), makeSymbol("THIS-ARG3"));

    static private final SubLList $list_alt256 = list(makeSymbol("OTHER-ARG1"), makeSymbol("OTHER-PRED"), makeSymbol("OTHER-ARG3"));

    static private final SubLSymbol $sym257$PARSE_AS_TYPE_ = makeSymbol("PARSE-AS-TYPE?");

    static private final SubLList $list_alt260 = list(makeKeyword("TRANSFORMATION-ALLOWED?"), NIL);

    static private final SubLSymbol $sym261$_PARSE_AS_TYPE__CACHING_STATE_ = makeSymbol("*PARSE-AS-TYPE?-CACHING-STATE*");

    static private final SubLString $str_alt265$_ = makeString(",");

    static private final SubLList $list_alt266 = list(reader_make_constant_shell("PhraseFn"), reader_make_constant_shell("Determiner"));

    static private final SubLList $list_alt269 = list(makeSymbol("SUB-ITERATORS"), makeSymbol("PROPERTIES"), makeSymbol("CURRENT-ITERATOR-INDEX"), makeSymbol("STRING"), makeSymbol("PREVIOUS-PARSES"), makeSymbol("NEXT"));

    static private final SubLList $list_alt270 = list(makeKeyword("SUB-ITERATORS"), makeKeyword("PROPERTIES"), makeKeyword("CURRENT-ITERATOR-INDEX"), makeKeyword("STRING"), makeKeyword("PREVIOUS-PARSES"), $NEXT);

    static private final SubLList $list_alt271 = list(makeSymbol("PI-SUB-ITERATORS"), makeSymbol("PI-PROPERTIES"), makeSymbol("PI-CURRENT-ITERATOR-INDEX"), makeSymbol("PI-STRING"), makeSymbol("PI-PREVIOUS-PARSES"), makeSymbol("PI-NEXT"));

    static private final SubLList $list_alt272 = list(makeSymbol("_CSETF-PI-SUB-ITERATORS"), makeSymbol("_CSETF-PI-PROPERTIES"), makeSymbol("_CSETF-PI-CURRENT-ITERATOR-INDEX"), makeSymbol("_CSETF-PI-STRING"), makeSymbol("_CSETF-PI-PREVIOUS-PARSES"), makeSymbol("_CSETF-PI-NEXT"));

    static private final SubLString $str_alt293$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLString $str_alt294$__parse_iterator__S_current_iter_ = makeString("#<parse-iterator ~S current-iter: ~A sub-iters: ~A props: ~A>");

    static private final SubLList $list_alt298 = list(reader_make_constant_shell("CharniakParserCyclifier"), reader_make_constant_shell("CycRecursiveTemplateParser"));

    static private final SubLSymbol $sym301$EQUALS_EL_ = makeSymbol("EQUALS-EL?");

    static private final SubLList $list_alt305 = list(makeSymbol("ITER-OBJ"));

    static private final SubLList $list_alt306 = list(makeKeyword("ITER-OBJ"));

    static private final SubLList $list_alt307 = list(makeSymbol("CYCLIFY-ITERATOR-ITER-OBJ"));

    static private final SubLList $list_alt308 = list(makeSymbol("_CSETF-CYCLIFY-ITERATOR-ITER-OBJ"));

    static private final SubLSymbol $sym317$HAS_NEXT_ = makeSymbol("HAS-NEXT?");

    static private final SubLSymbol $sym321$_STRING = makeSymbol("?STRING");

    static private final SubLList $list_alt323 = list(makeSymbol("?STRING"));

    public static final SubLObject $const324$TextualEntailmentGenericBackgroun = reader_make_constant_shell("TextualEntailmentGenericBackgroundMt");

    static private final SubLList $list_alt327 = list(makeKeyword("MAX-NUMBER"), ONE_INTEGER);

    static private final SubLString $str_alt332$_cyc_departments_nl_corpora_cb_us = makeString("/cyc/departments/nl/corpora/cb-user-suggestions.txt");

    static private final SubLString $str_alt334$Unable_to_open__S = makeString("Unable to open ~S");

    static private final SubLString $str_alt335$_S__ = makeString("~S~%");

    private static final SubLSymbol PARSE_DATES_AND_NUMBERS_TEST_CASE = makeSymbol("PARSE-DATES-AND-NUMBERS-TEST-CASE");

    static private final SubLList $list_alt338 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SETUP"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CLEANUP"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ENSURE-NO-NON-DATE-NON-NUMBER-PARSES"), NIL, makeKeyword("PROTECTED")));

    static private final SubLSymbol $sym347$SUBLOOP_RESERVED_INITIALIZE_PARSE_DATES_AND_NUMBERS_TEST_CASE_CLA = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PARSE-DATES-AND-NUMBERS-TEST-CASE-CLASS");

    static private final SubLSymbol $sym352$SUBLOOP_RESERVED_INITIALIZE_PARSE_DATES_AND_NUMBERS_TEST_CASE_INS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PARSE-DATES-AND-NUMBERS-TEST-CASE-INSTANCE");

    static private final SubLString $str_alt353$parsing_utilities = makeString("parsing-utilities");

    static private final SubLString $$$cycl = makeString("cycl");

    private static final SubLSymbol ENSURE_NO_NON_DATE_NON_NUMBER_PARSES = makeSymbol("ENSURE-NO-NON-DATE-NON-NUMBER-PARSES");

    static private final SubLString $$$Parsing_Utilities_Test_Suite = makeString("Parsing Utilities Test Suite");

    static private final SubLList $list_alt357 = list(list(makeString("parsing-utilities"), makeString("cycl")));
}

/**
 * Total time: 2150 ms
 */
