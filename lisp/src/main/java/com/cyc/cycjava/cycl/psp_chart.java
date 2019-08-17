/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.register_macro_helper;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.constant_handles.valid_constantP;
import static com.cyc.cycjava.cycl.cyc_testing.generic_testing.define_test_case_table_int;
import static com.cyc.cycjava.cycl.el_utilities.copy_sentence;
import static com.cyc.cycjava.cycl.id_index.clear_id_index;
import static com.cyc.cycjava.cycl.id_index.id_index_count;
import static com.cyc.cycjava.cycl.id_index.id_index_enter;
import static com.cyc.cycjava.cycl.id_index.id_index_lookup;
import static com.cyc.cycjava.cycl.id_index.id_index_p;
import static com.cyc.cycjava.cycl.id_index.id_index_reserve;
import static com.cyc.cycjava.cycl.id_index.new_id_index;
import static com.cyc.cycjava.cycl.kb_indexing_datastructures.indexed_term_p;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.cycjava.cycl.utilities_macros.$last_percent_progress_index$;
import static com.cyc.cycjava.cycl.utilities_macros.$last_percent_progress_prediction$;
import static com.cyc.cycjava.cycl.utilities_macros.$percent_progress_start_time$;
import static com.cyc.cycjava.cycl.utilities_macros.$within_noting_percent_progress$;
import static com.cyc.cycjava.cycl.utilities_macros.note_percent_progress;
import static com.cyc.cycjava.cycl.utilities_macros.noting_percent_progress_postamble;
import static com.cyc.cycjava.cycl.utilities_macros.noting_percent_progress_preamble;
import static com.cyc.cycjava.cycl.utilities_macros.register_kb_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nconc;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.rplaca;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eql;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.apply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.divide;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.integerDivide;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.minus;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.multiply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numG;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numL;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove_if;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.search;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.subseq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.current_process;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.get_universal_time;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.floatp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.integerp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.resetMultipleValues;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.make_vector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.set_aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeDouble;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.nthcdr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.third;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.union;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import org.armedbear.lisp.Lisp;

import com.cyc.cycjava.cycl.inference.ask_utilities;
import com.cyc.cycjava.cycl.inference.harness.inference_kernel;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Strings;
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
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLFloat;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      PSP-CHART
 * source file: /cyc/top/cycl/psp-chart.lisp
 * created:     2019/07/03 17:38:25
 */
public final class psp_chart extends SubLTranslatedFile implements V12 {
    static private final SubLString $str_alt182$ = makeString("");

    public static final class $phrase_structure_parser_phrasal_edge_native extends SubLStructNative {
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        public SubLObject getField2() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$weight;
        }

        public SubLObject getField3() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$string;
        }

        public SubLObject getField4() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$span;
        }

        public SubLObject getField5() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$category;
        }

        public SubLObject getField6() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$pos_pred;
        }

        public SubLObject getField7() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$cycls;
        }

        public SubLObject getField8() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$rule;
        }

        public SubLObject getField9() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$new_dtr_constraints;
        }

        public SubLObject getField10() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$head_dtr;
        }

        public SubLObject getField11() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$non_head_dtrs;
        }

        public SubLObject getField12() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$id;
        }

        public SubLObject getField13() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$chart;
        }

        public SubLObject setField2(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$weight = value;
        }

        public SubLObject setField3(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$string = value;
        }

        public SubLObject setField4(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$span = value;
        }

        public SubLObject setField5(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$category = value;
        }

        public SubLObject setField6(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$pos_pred = value;
        }

        public SubLObject setField7(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$cycls = value;
        }

        public SubLObject setField8(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$rule = value;
        }

        public SubLObject setField9(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$new_dtr_constraints = value;
        }

        public SubLObject setField10(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$head_dtr = value;
        }

        public SubLObject setField11(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$non_head_dtrs = value;
        }

        public SubLObject setField12(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$id = value;
        }

        public SubLObject setField13(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.this.$chart = value;
        }

        public SubLObject $weight = Lisp.NIL;

        public SubLObject $string = Lisp.NIL;

        public SubLObject $span = Lisp.NIL;

        public SubLObject $category = Lisp.NIL;

        public SubLObject $pos_pred = Lisp.NIL;

        public SubLObject $cycls = Lisp.NIL;

        public SubLObject $rule = Lisp.NIL;

        public SubLObject $new_dtr_constraints = Lisp.NIL;

        public SubLObject $head_dtr = Lisp.NIL;

        public SubLObject $non_head_dtrs = Lisp.NIL;

        public SubLObject $id = Lisp.NIL;

        public SubLObject $chart = Lisp.NIL;

        private static final SubLStructDeclNative structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.class, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P, $list_alt57, $list_alt58, new String[]{ "$weight", "$string", "$span", "$category", "$pos_pred", "$cycls", "$rule", "$new_dtr_constraints", "$head_dtr", "$non_head_dtrs", "$id", "$chart" }, $list_alt59, $list_alt60, PPRINT_PSP_PHRASAL_EDGE);
    }

    public static final class $phrase_structure_parser_chart_native extends SubLStructNative {
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        public SubLObject getField2() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$id;
        }

        public SubLObject getField3() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$input_string;
        }

        public SubLObject getField4() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$length;
        }

        public SubLObject getField5() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$spans_to_edges;
        }

        public SubLObject getField6() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$gap_type;
        }

        public SubLObject getField7() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$memoization_state;
        }

        public SubLObject getField8() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$edge_id_index;
        }

        public SubLObject getField9() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$edges_to_nodes;
        }

        public SubLObject getField10() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$max_edges_per_span;
        }

        public SubLObject getField11() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$max_edges;
        }

        public SubLObject getField12() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$maxed_out_spans;
        }

        public SubLObject getField13() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$maxed_outP;
        }

        public SubLObject getField14() {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$parsed_lexical_spans;
        }

        public SubLObject setField2(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$id = value;
        }

        public SubLObject setField3(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$input_string = value;
        }

        public SubLObject setField4(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$length = value;
        }

        public SubLObject setField5(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$spans_to_edges = value;
        }

        public SubLObject setField6(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$gap_type = value;
        }

        public SubLObject setField7(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$memoization_state = value;
        }

        public SubLObject setField8(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$edge_id_index = value;
        }

        public SubLObject setField9(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$edges_to_nodes = value;
        }

        public SubLObject setField10(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$max_edges_per_span = value;
        }

        public SubLObject setField11(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$max_edges = value;
        }

        public SubLObject setField12(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$maxed_out_spans = value;
        }

        public SubLObject setField13(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$maxed_outP = value;
        }

        public SubLObject setField14(SubLObject value) {
            return com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.this.$parsed_lexical_spans = value;
        }

        public SubLObject $id = Lisp.NIL;

        public SubLObject $input_string = Lisp.NIL;

        public SubLObject $length = Lisp.NIL;

        public SubLObject $spans_to_edges = Lisp.NIL;

        public SubLObject $gap_type = Lisp.NIL;

        public SubLObject $memoization_state = Lisp.NIL;

        public SubLObject $edge_id_index = Lisp.NIL;

        public SubLObject $edges_to_nodes = Lisp.NIL;

        public SubLObject $max_edges_per_span = Lisp.NIL;

        public SubLObject $max_edges = Lisp.NIL;

        public SubLObject $maxed_out_spans = Lisp.NIL;

        public SubLObject $maxed_outP = Lisp.NIL;

        public SubLObject $parsed_lexical_spans = Lisp.NIL;

        private static final SubLStructDeclNative structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.class, PHRASE_STRUCTURE_PARSER_CHART, PHRASE_STRUCTURE_PARSER_CHART_P, $list_alt2, $list_alt3, new String[]{ "$id", "$input_string", "$length", "$spans_to_edges", "$gap_type", "$memoization_state", "$edge_id_index", "$edges_to_nodes", "$max_edges_per_span", "$max_edges", "$maxed_out_spans", "$maxed_outP", "$parsed_lexical_spans" }, $list_alt4, $list_alt5, PPRINT_PSP_CHART);
    }

    public static final SubLFile me = new psp_chart();

 public static final String myName = "com.cyc.cycjava.cycl.psp_chart";


    // deflexical
    // Definitions
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $psp_parse_wh_wordsP$ = makeSymbol("*PSP-PARSE-WH-WORDS?*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_phrase_structure_parser_chart$ = makeSymbol("*DTP-PHRASE-STRUCTURE-PARSER-CHART*");

    // deflexical
    // Do we keep track of edges for a chart by ID?
    /**
     * Do we keep track of edges for a chart by ID?
     */
    @LispMethod(comment = "Do we keep track of edges for a chart by ID?\ndeflexical")
    private static final SubLSymbol $psp_chart_index_edgesP$ = makeSymbol("*PSP-CHART-INDEX-EDGES?*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_phrase_structure_parser_phrasal_edge$ = makeSymbol("*DTP-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_phrase_structure_parser_lexical_edge$ = makeSymbol("*DTP-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $psp_unknown_weight$ = makeSymbol("*PSP-UNKNOWN-WEIGHT*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $psp_edge_default_weight$ = makeSymbol("*PSP-EDGE-DEFAULT-WEIGHT*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $psp_edge_greedy_weight$ = makeSymbol("*PSP-EDGE-GREEDY-WEIGHT*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $psp_edge_dispreferred_weight$ = makeSymbol("*PSP-EDGE-DISPREFERRED-WEIGHT*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $psp_gap_edge_weight$ = makeSymbol("*PSP-GAP-EDGE-WEIGHT*");

    // defconstant
    // List of tokenization modes, in order of preference.
    /**
     * List of tokenization modes, in order of preference.
     */
    @LispMethod(comment = "List of tokenization modes, in order of preference.\ndefconstant")
    private static final SubLSymbol $psp_tokenization_modes$ = makeSymbol("*PSP-TOKENIZATION-MODES*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $psp_use_term_lexiconP$ = makeSymbol("*PSP-USE-TERM-LEXICON?*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $fscp_multi_parse_categories$ = makeSymbol("*FSCP-MULTI-PARSE-CATEGORIES*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $psp_predicative_frameP_caching_state$ = makeSymbol("*PSP-PREDICATIVE-FRAME?-CACHING-STATE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $psp_constrained_frameP_caching_state$ = makeSymbol("*PSP-CONSTRAINED-FRAME?-CACHING-STATE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $transitive_np_comp_frameP_caching_state$ = makeSymbol("*TRANSITIVE-NP-COMP-FRAME?-CACHING-STATE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $intransitive_frameP_caching_state$ = makeSymbol("*INTRANSITIVE-FRAME?-CACHING-STATE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $psp_frame_has_typeP_caching_state$ = makeSymbol("*PSP-FRAME-HAS-TYPE?-CACHING-STATE*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $psp_passive_keys$ = makeSymbol("*PSP-PASSIVE-KEYS*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $titles_conventionally_quoted$ = makeSymbol("*TITLES-CONVENTIONALLY-QUOTED*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $psp_preferred_denot_preference_factor$ = makeSymbol("*PSP-PREFERRED-DENOT-PREFERENCE-FACTOR*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_CHART = makeSymbol("PHRASE-STRUCTURE-PARSER-CHART");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_CHART_P = makeSymbol("PHRASE-STRUCTURE-PARSER-CHART-P");

    static private final SubLList $list2 = list(new SubLObject[]{ makeSymbol("ID"), makeSymbol("INPUT-STRING"), makeSymbol("LENGTH"), makeSymbol("SPANS-TO-EDGES"), makeSymbol("GAP-TYPE"), makeSymbol("MEMOIZATION-STATE"), makeSymbol("EDGE-ID-INDEX"), makeSymbol("EDGES-TO-NODES"), makeSymbol("MAX-EDGES-PER-SPAN"), makeSymbol("MAX-EDGES"), makeSymbol("MAXED-OUT-SPANS"), makeSymbol("MAXED-OUT?"), makeSymbol("PARSED-LEXICAL-SPANS") });

    static private final SubLList $list3 = list(new SubLObject[]{ makeKeyword("ID"), makeKeyword("INPUT-STRING"), makeKeyword("LENGTH"), makeKeyword("SPANS-TO-EDGES"), makeKeyword("GAP-TYPE"), makeKeyword("MEMOIZATION-STATE"), makeKeyword("EDGE-ID-INDEX"), makeKeyword("EDGES-TO-NODES"), makeKeyword("MAX-EDGES-PER-SPAN"), makeKeyword("MAX-EDGES"), makeKeyword("MAXED-OUT-SPANS"), makeKeyword("MAXED-OUT?"), makeKeyword("PARSED-LEXICAL-SPANS") });

    static private final SubLList $list4 = list(new SubLObject[]{ makeSymbol("PSP-CHART-ID"), makeSymbol("PSP-CHART-INPUT-STRING"), makeSymbol("PSP-CHART-LENGTH"), makeSymbol("PSP-CHART-SPANS-TO-EDGES"), makeSymbol("PSP-CHART-GAP-TYPE"), makeSymbol("PSP-CHART-MEMOIZATION-STATE"), makeSymbol("PSP-CHART-EDGE-ID-INDEX"), makeSymbol("PSP-CHART-EDGES-TO-NODES"), makeSymbol("PSP-CHART-MAX-EDGES-PER-SPAN"), makeSymbol("PSP-CHART-MAX-EDGES"), makeSymbol("PSP-CHART-MAXED-OUT-SPANS"), makeSymbol("PSP-CHART-MAXED-OUT?"), makeSymbol("PSP-CHART-PARSED-LEXICAL-SPANS") });

    static private final SubLList $list5 = list(new SubLObject[]{ makeSymbol("_CSETF-PSP-CHART-ID"), makeSymbol("_CSETF-PSP-CHART-INPUT-STRING"), makeSymbol("_CSETF-PSP-CHART-LENGTH"), makeSymbol("_CSETF-PSP-CHART-SPANS-TO-EDGES"), makeSymbol("_CSETF-PSP-CHART-GAP-TYPE"), makeSymbol("_CSETF-PSP-CHART-MEMOIZATION-STATE"), makeSymbol("_CSETF-PSP-CHART-EDGE-ID-INDEX"), makeSymbol("_CSETF-PSP-CHART-EDGES-TO-NODES"), makeSymbol("_CSETF-PSP-CHART-MAX-EDGES-PER-SPAN"), makeSymbol("_CSETF-PSP-CHART-MAX-EDGES"), makeSymbol("_CSETF-PSP-CHART-MAXED-OUT-SPANS"), makeSymbol("_CSETF-PSP-CHART-MAXED-OUT?"), makeSymbol("_CSETF-PSP-CHART-PARSED-LEXICAL-SPANS") });

    private static final SubLSymbol PPRINT_PSP_CHART = makeSymbol("PPRINT-PSP-CHART");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_CHART_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("PHRASE-STRUCTURE-PARSER-CHART-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list8 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("PHRASE-STRUCTURE-PARSER-CHART-P"));

    private static final SubLSymbol PSP_CHART_ID = makeSymbol("PSP-CHART-ID");

    private static final SubLSymbol _CSETF_PSP_CHART_ID = makeSymbol("_CSETF-PSP-CHART-ID");

    private static final SubLSymbol PSP_CHART_INPUT_STRING = makeSymbol("PSP-CHART-INPUT-STRING");

    private static final SubLSymbol _CSETF_PSP_CHART_INPUT_STRING = makeSymbol("_CSETF-PSP-CHART-INPUT-STRING");

    private static final SubLSymbol PSP_CHART_LENGTH = makeSymbol("PSP-CHART-LENGTH");

    private static final SubLSymbol _CSETF_PSP_CHART_LENGTH = makeSymbol("_CSETF-PSP-CHART-LENGTH");

    private static final SubLSymbol PSP_CHART_SPANS_TO_EDGES = makeSymbol("PSP-CHART-SPANS-TO-EDGES");

    private static final SubLSymbol _CSETF_PSP_CHART_SPANS_TO_EDGES = makeSymbol("_CSETF-PSP-CHART-SPANS-TO-EDGES");

    private static final SubLSymbol PSP_CHART_GAP_TYPE = makeSymbol("PSP-CHART-GAP-TYPE");

    private static final SubLSymbol _CSETF_PSP_CHART_GAP_TYPE = makeSymbol("_CSETF-PSP-CHART-GAP-TYPE");

    private static final SubLSymbol PSP_CHART_MEMOIZATION_STATE = makeSymbol("PSP-CHART-MEMOIZATION-STATE");

    private static final SubLSymbol _CSETF_PSP_CHART_MEMOIZATION_STATE = makeSymbol("_CSETF-PSP-CHART-MEMOIZATION-STATE");

    private static final SubLSymbol PSP_CHART_EDGE_ID_INDEX = makeSymbol("PSP-CHART-EDGE-ID-INDEX");

    private static final SubLSymbol _CSETF_PSP_CHART_EDGE_ID_INDEX = makeSymbol("_CSETF-PSP-CHART-EDGE-ID-INDEX");

    private static final SubLSymbol PSP_CHART_EDGES_TO_NODES = makeSymbol("PSP-CHART-EDGES-TO-NODES");

    private static final SubLSymbol _CSETF_PSP_CHART_EDGES_TO_NODES = makeSymbol("_CSETF-PSP-CHART-EDGES-TO-NODES");

    private static final SubLSymbol PSP_CHART_MAX_EDGES_PER_SPAN = makeSymbol("PSP-CHART-MAX-EDGES-PER-SPAN");

    private static final SubLSymbol _CSETF_PSP_CHART_MAX_EDGES_PER_SPAN = makeSymbol("_CSETF-PSP-CHART-MAX-EDGES-PER-SPAN");

    private static final SubLSymbol PSP_CHART_MAX_EDGES = makeSymbol("PSP-CHART-MAX-EDGES");

    private static final SubLSymbol _CSETF_PSP_CHART_MAX_EDGES = makeSymbol("_CSETF-PSP-CHART-MAX-EDGES");

    private static final SubLSymbol PSP_CHART_MAXED_OUT_SPANS = makeSymbol("PSP-CHART-MAXED-OUT-SPANS");

    private static final SubLSymbol _CSETF_PSP_CHART_MAXED_OUT_SPANS = makeSymbol("_CSETF-PSP-CHART-MAXED-OUT-SPANS");

    private static final SubLSymbol $sym31$PSP_CHART_MAXED_OUT_ = makeSymbol("PSP-CHART-MAXED-OUT?");

    private static final SubLSymbol $sym32$_CSETF_PSP_CHART_MAXED_OUT_ = makeSymbol("_CSETF-PSP-CHART-MAXED-OUT?");

    private static final SubLSymbol PSP_CHART_PARSED_LEXICAL_SPANS = makeSymbol("PSP-CHART-PARSED-LEXICAL-SPANS");

    private static final SubLSymbol _CSETF_PSP_CHART_PARSED_LEXICAL_SPANS = makeSymbol("_CSETF-PSP-CHART-PARSED-LEXICAL-SPANS");

    private static final SubLSymbol $SPANS_TO_EDGES = makeKeyword("SPANS-TO-EDGES");

    private static final SubLSymbol $EDGE_ID_INDEX = makeKeyword("EDGE-ID-INDEX");

    private static final SubLSymbol $EDGES_TO_NODES = makeKeyword("EDGES-TO-NODES");

    private static final SubLSymbol $MAX_EDGES_PER_SPAN = makeKeyword("MAX-EDGES-PER-SPAN");

    private static final SubLSymbol $MAXED_OUT_SPANS = makeKeyword("MAXED-OUT-SPANS");

    private static final SubLSymbol $kw46$MAXED_OUT_ = makeKeyword("MAXED-OUT?");

    private static final SubLSymbol $PARSED_LEXICAL_SPANS = makeKeyword("PARSED-LEXICAL-SPANS");

    private static final SubLString $str48$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_PHRASE_STRUCTURE_PARSER_CHART = makeSymbol("MAKE-PHRASE-STRUCTURE-PARSER-CHART");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_CHART_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-PHRASE-STRUCTURE-PARSER-CHART-METHOD");

    private static final SubLSymbol $psp_chart_id_counter$ = makeSymbol("*PSP-CHART-ID-COUNTER*");

    private static final SubLString $str55$__PSP_CHART__S__S_ = makeString("#<PSP-CHART ~S ~S>");

    private static final SubLString $str59$Not_indexing_PSP_edges__Can_t_fin = makeString("Not indexing PSP edges. Can't find edges by IDs.");

    private static final SubLString $str60$_S_is_not_a_PSP_chart__Can_t_find = makeString("~S is not a PSP chart. Can't find edges by IDs.");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE = makeSymbol("PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P = makeSymbol("PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-P");

    private static final SubLList $list63 = list(new SubLObject[]{ makeSymbol("WEIGHT"), makeSymbol("STRING"), makeSymbol("SPAN"), makeSymbol("CATEGORY"), makeSymbol("POS-PRED"), makeSymbol("CYCLS"), makeSymbol("RULE"), makeSymbol("NEW-DTR-CONSTRAINTS"), makeSymbol("HEAD-DTR"), makeSymbol("NON-HEAD-DTRS"), makeSymbol("ID"), makeSymbol("CHART") });

    private static final SubLList $list64 = list(new SubLObject[]{ makeKeyword("WEIGHT"), makeKeyword("STRING"), $SPAN, makeKeyword("CATEGORY"), makeKeyword("POS-PRED"), makeKeyword("CYCLS"), $RULE, makeKeyword("NEW-DTR-CONSTRAINTS"), makeKeyword("HEAD-DTR"), makeKeyword("NON-HEAD-DTRS"), makeKeyword("ID"), makeKeyword("CHART") });

    private static final SubLList $list65 = list(new SubLObject[]{ makeSymbol("PSP-PHRASAL-EDGE-WEIGHT"), makeSymbol("PSP-PHRASAL-EDGE-STRING"), makeSymbol("PSP-PHRASAL-EDGE-SPAN"), makeSymbol("PSP-PHRASAL-EDGE-CATEGORY"), makeSymbol("PSP-PHRASAL-EDGE-POS-PRED"), makeSymbol("PSP-PHRASAL-EDGE-CYCLS"), makeSymbol("PSP-PHRASAL-EDGE-RULE"), makeSymbol("PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS"), makeSymbol("PSP-PHRASAL-EDGE-HEAD-DTR"), makeSymbol("PSP-PHRASAL-EDGE-NON-HEAD-DTRS"), makeSymbol("PSP-PHRASAL-EDGE-ID"), makeSymbol("PSP-PHRASAL-EDGE-CHART") });

    private static final SubLList $list66 = list(new SubLObject[]{ makeSymbol("_CSETF-PSP-PHRASAL-EDGE-WEIGHT"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-STRING"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-SPAN"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CATEGORY"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-POS-PRED"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CYCLS"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-RULE"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-HEAD-DTR"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-NON-HEAD-DTRS"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-ID"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CHART") });

    private static final SubLSymbol PPRINT_PSP_PHRASAL_EDGE = makeSymbol("PPRINT-PSP-PHRASAL-EDGE");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list69 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-P"));

    private static final SubLSymbol PSP_PHRASAL_EDGE_WEIGHT = makeSymbol("PSP-PHRASAL-EDGE-WEIGHT");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_WEIGHT = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-WEIGHT");

    private static final SubLSymbol PSP_PHRASAL_EDGE_STRING = makeSymbol("PSP-PHRASAL-EDGE-STRING");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_STRING = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-STRING");

    private static final SubLSymbol PSP_PHRASAL_EDGE_SPAN = makeSymbol("PSP-PHRASAL-EDGE-SPAN");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_SPAN = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-SPAN");

    private static final SubLSymbol PSP_PHRASAL_EDGE_CATEGORY = makeSymbol("PSP-PHRASAL-EDGE-CATEGORY");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_CATEGORY = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CATEGORY");

    private static final SubLSymbol PSP_PHRASAL_EDGE_POS_PRED = makeSymbol("PSP-PHRASAL-EDGE-POS-PRED");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_POS_PRED = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-POS-PRED");

    private static final SubLSymbol PSP_PHRASAL_EDGE_CYCLS = makeSymbol("PSP-PHRASAL-EDGE-CYCLS");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_CYCLS = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CYCLS");

    private static final SubLSymbol PSP_PHRASAL_EDGE_RULE = makeSymbol("PSP-PHRASAL-EDGE-RULE");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_RULE = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-RULE");

    private static final SubLSymbol PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS = makeSymbol("PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS");

    private static final SubLSymbol PSP_PHRASAL_EDGE_HEAD_DTR = makeSymbol("PSP-PHRASAL-EDGE-HEAD-DTR");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_HEAD_DTR = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-HEAD-DTR");

    private static final SubLSymbol PSP_PHRASAL_EDGE_NON_HEAD_DTRS = makeSymbol("PSP-PHRASAL-EDGE-NON-HEAD-DTRS");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_NON_HEAD_DTRS = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-NON-HEAD-DTRS");

    private static final SubLSymbol PSP_PHRASAL_EDGE_ID = makeSymbol("PSP-PHRASAL-EDGE-ID");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_ID = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-ID");

    private static final SubLSymbol PSP_PHRASAL_EDGE_CHART = makeSymbol("PSP-PHRASAL-EDGE-CHART");

    private static final SubLSymbol _CSETF_PSP_PHRASAL_EDGE_CHART = makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CHART");

    private static final SubLSymbol $NEW_DTR_CONSTRAINTS = makeKeyword("NEW-DTR-CONSTRAINTS");

    private static final SubLSymbol $NON_HEAD_DTRS = makeKeyword("NON-HEAD-DTRS");

    private static final SubLSymbol MAKE_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE = makeSymbol("MAKE-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE");

    private static final SubLSymbol $sym106$VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_METHO = makeSymbol("VISIT-DEFSTRUCT-OBJECT-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-METHOD");

    private static final SubLSymbol SXHASH_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_METHOD = makeSymbol("SXHASH-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-METHOD");

    private static final SubLString $str108$__PSP_PHRASAL_EDGE__S__S__S_ = makeString("#<PSP-PHRASAL-EDGE ~S ~S ~S>");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE = makeSymbol("PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P = makeSymbol("PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-P");

    private static final SubLList $list111 = list(new SubLObject[]{ makeSymbol("WEIGHT"), makeSymbol("STRING"), makeSymbol("SPAN"), makeSymbol("CATEGORY"), makeSymbol("POS-PRED"), makeSymbol("CYCLS"), makeSymbol("WU"), makeSymbol("FRAME"), makeSymbol("ID"), makeSymbol("CHART") });

    private static final SubLList $list112 = list(new SubLObject[]{ makeKeyword("WEIGHT"), makeKeyword("STRING"), $SPAN, makeKeyword("CATEGORY"), makeKeyword("POS-PRED"), makeKeyword("CYCLS"), makeKeyword("WU"), makeKeyword("FRAME"), makeKeyword("ID"), makeKeyword("CHART") });

    private static final SubLList $list113 = list(new SubLObject[]{ makeSymbol("PSP-LEXICAL-EDGE-WEIGHT"), makeSymbol("PSP-LEXICAL-EDGE-STRING"), makeSymbol("PSP-LEXICAL-EDGE-SPAN"), makeSymbol("PSP-LEXICAL-EDGE-CATEGORY"), makeSymbol("PSP-LEXICAL-EDGE-POS-PRED"), makeSymbol("PSP-LEXICAL-EDGE-CYCLS"), makeSymbol("PSP-LEXICAL-EDGE-WU"), makeSymbol("PSP-LEXICAL-EDGE-FRAME"), makeSymbol("PSP-LEXICAL-EDGE-ID"), makeSymbol("PSP-LEXICAL-EDGE-CHART") });

    private static final SubLList $list114 = list(new SubLObject[]{ makeSymbol("_CSETF-PSP-LEXICAL-EDGE-WEIGHT"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-STRING"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-SPAN"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CATEGORY"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-POS-PRED"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CYCLS"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-WU"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-FRAME"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-ID"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CHART") });

    private static final SubLSymbol PPRINT_PSP_LEXICAL_EDGE = makeSymbol("PPRINT-PSP-LEXICAL-EDGE");

    private static final SubLSymbol PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list117 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-P"));

    private static final SubLSymbol PSP_LEXICAL_EDGE_WEIGHT = makeSymbol("PSP-LEXICAL-EDGE-WEIGHT");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_WEIGHT = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-WEIGHT");

    private static final SubLSymbol PSP_LEXICAL_EDGE_STRING = makeSymbol("PSP-LEXICAL-EDGE-STRING");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_STRING = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-STRING");

    private static final SubLSymbol PSP_LEXICAL_EDGE_SPAN = makeSymbol("PSP-LEXICAL-EDGE-SPAN");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_SPAN = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-SPAN");

    private static final SubLSymbol PSP_LEXICAL_EDGE_CATEGORY = makeSymbol("PSP-LEXICAL-EDGE-CATEGORY");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_CATEGORY = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CATEGORY");

    private static final SubLSymbol PSP_LEXICAL_EDGE_POS_PRED = makeSymbol("PSP-LEXICAL-EDGE-POS-PRED");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_POS_PRED = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-POS-PRED");

    private static final SubLSymbol PSP_LEXICAL_EDGE_CYCLS = makeSymbol("PSP-LEXICAL-EDGE-CYCLS");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_CYCLS = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CYCLS");

    private static final SubLSymbol PSP_LEXICAL_EDGE_WU = makeSymbol("PSP-LEXICAL-EDGE-WU");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_WU = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-WU");

    private static final SubLSymbol PSP_LEXICAL_EDGE_FRAME = makeSymbol("PSP-LEXICAL-EDGE-FRAME");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_FRAME = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-FRAME");

    private static final SubLSymbol PSP_LEXICAL_EDGE_ID = makeSymbol("PSP-LEXICAL-EDGE-ID");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_ID = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-ID");

    private static final SubLSymbol PSP_LEXICAL_EDGE_CHART = makeSymbol("PSP-LEXICAL-EDGE-CHART");

    private static final SubLSymbol _CSETF_PSP_LEXICAL_EDGE_CHART = makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CHART");

    private static final SubLSymbol MAKE_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE = makeSymbol("MAKE-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE");

    private static final SubLSymbol $sym141$VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_METHO = makeSymbol("VISIT-DEFSTRUCT-OBJECT-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-METHOD");

    private static final SubLSymbol SXHASH_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_METHOD = makeSymbol("SXHASH-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-METHOD");

    private static final SubLString $str143$__PSP_LEXICAL_EDGE__S__S__S__S_W_ = makeString("#<PSP-LEXICAL-EDGE ~S ~S ~S ~S W=~S>");

    private static final SubLString $str144$___S___S______S__ = makeString("~&~S (~S):~% ~S~%");

    private static final SubLString $str145$_S_is_not_a_PSP_edge_ = makeString("~S is not a PSP edge.");

    private static final SubLString $str146$Headless_edge___S = makeString("Headless edge: ~S");

    private static final SubLList $list147 = list(list(makeSymbol("SPAN-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("DONE"), makeSymbol("CHART")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol $sym148$CHART_TO_USE = makeUninternedSymbol("CHART-TO-USE");

    private static final SubLList $list151 = list(makeSymbol("*PSP-CHART*"));

    private static final SubLList $list153 = list(makeSymbol("PHRASE-STRUCTURE-PARSER-CHART-P"));

    private static final SubLSymbol DO_DICTIONARY_KEYS = makeSymbol("DO-DICTIONARY-KEYS");

    private static final SubLSymbol PSP_DO_EDGES_FOR_SPAN = makeSymbol("PSP-DO-EDGES-FOR-SPAN");

    private static final SubLSymbol PSP_DO_EDGES = makeSymbol("PSP-DO-EDGES");

    private static final SubLList $list158 = list(list(makeSymbol("EDGE-VAR"), makeSymbol("CHART"), makeSymbol("CATEGORY"), makeSymbol("SPAN"), makeSymbol("COMPLETE-ONLY?"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol PSP_EDGES_WITH_SPAN = makeSymbol("PSP-EDGES-WITH-SPAN");

    private static final SubLSymbol $sym162$PSP_DO_EDGE_OK_ = makeSymbol("PSP-DO-EDGE-OK?");

    private static final SubLSymbol PSP_DO_LEXICAL_EDGES_FOR_SPAN = makeSymbol("PSP-DO-LEXICAL-EDGES-FOR-SPAN");

    private static final SubLSymbol PSP_LEXICAL_EDGES_WITH_SPAN = makeSymbol("PSP-LEXICAL-EDGES-WITH-SPAN");

    private static final SubLList $list166 = list(list(makeSymbol("EDGE-VAR"), list(makeSymbol("&KEY"), list(makeSymbol("CHART"), NIL), list(makeSymbol("CATEGORY"), makeKeyword("ANY")), list(makeSymbol("SPAN"), makeKeyword("ANY")), list(makeSymbol("COMPLETE-ONLY?"), T), list(makeSymbol("TYPE"), makeKeyword("ANY")), list(makeSymbol("DONE"), NIL))), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list167 = list(makeKeyword("CHART"), makeKeyword("CATEGORY"), $SPAN, makeKeyword("COMPLETE-ONLY?"), $TYPE, $DONE);

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    private static final SubLSymbol $kw169$COMPLETE_ONLY_ = makeKeyword("COMPLETE-ONLY?");

    private static final SubLSymbol $sym171$SPAN_TO_DO = makeUninternedSymbol("SPAN-TO-DO");

    private static final SubLSymbol $sym172$CHART_TO_USE = makeUninternedSymbol("CHART-TO-USE");

    private static final SubLList $list174 = list(makeKeyword("ANY"));

    private static final SubLSymbol PSP_DO_SPANS = makeSymbol("PSP-DO-SPANS");

    private static final SubLSymbol INTERVAL_SPAN_P = makeSymbol("INTERVAL-SPAN-P");

    private static final SubLFloat $float$0_0 = makeDouble(0.0);

    private static final SubLSymbol PSP_WEIGHT_P = makeSymbol("PSP-WEIGHT-P");

    private static final SubLFloat $float$0_85 = makeDouble(0.85);

    private static final SubLFloat $float$1_0 = makeDouble(1.0);

    private static final SubLFloat $float$0_75 = makeDouble(0.75);

    private static final SubLFloat $float$0_9 = makeDouble(0.9);

    private static final SubLSymbol PSP_NP_GAP_SEMX = makeSymbol("PSP-NP-GAP-SEMX");

    private static final SubLSymbol $psp_np_gap_semx_caching_state$ = makeSymbol("*PSP-NP-GAP-SEMX-CACHING-STATE*");



    private static final SubLSymbol NEW_PSP_PHRASAL_EDGE = makeSymbol("NEW-PSP-PHRASAL-EDGE");

    private static final SubLSymbol NEW_PSP_LEXICAL_EDGE = makeSymbol("NEW-PSP-LEXICAL-EDGE");

    private static final SubLString $str193$ = makeString("");



    private static final SubLList $list196 = list(makeKeyword("NP"), $NONE);



    private static final SubLList $list205 = list(makeKeyword("GREEDY"), makeKeyword("DILIGENT"));

    private static final SubLSymbol PSP_LEXICON_P = makeSymbol("PSP-LEXICON-P");

    private static final SubLString $str210$_ = makeString("'");

    private static final SubLString $str211$___ = makeString(" ' ");



    private static final SubLSymbol PSP_TOKENIZATION_MODE_P = makeSymbol("PSP-TOKENIZATION-MODE-P");

    private static final SubLString $str219$Failed_to_get_bigger_than__S__Lon = makeString("Failed to get bigger than ~S. Longest edge:~% ~S (span: ~S)~%");

    private static final SubLList $list221 = list(makeKeyword("QUANTITY"), makeKeyword("UNIT-OF-MEASURE"));

    private static final SubLString $$$a = makeString("a");

    private static final SubLSymbol WORD_P = makeSymbol("WORD-P");

    private static final SubLObject $$Number_SP = reader_make_constant_shell("Number-SP");



    private static final SubLSymbol $sym226$PSP_SCALAR_INTERVAL_ = makeSymbol("PSP-SCALAR-INTERVAL?");

    private static final SubLSymbol $sym227$IBQE_ = makeSymbol("IBQE?");



    private static final SubLSymbol $sym230$_ = makeSymbol("=");

    private static final SubLSymbol INTERVAL_SPAN_START = makeSymbol("INTERVAL-SPAN-START");

    private static final SubLList $list232 = cons(makeSymbol("INDEXICAL-STRING"), makeSymbol("DENOT"));













    private static final SubLSymbol $PASSIVE_WITH_BY = makeKeyword("PASSIVE-WITH-BY");





    private static final SubLList $list243 = list(reader_make_constant_shell("PPCompFrameFn"), reader_make_constant_shell("TransitivePPFrameType"), reader_make_constant_shell("Of-TheWord"));







    private static final SubLString $$$the_ = makeString("the ");

    private static final SubLString $str249$__Adding_new_edge_____S__Looking_ = makeString("~&Adding new edge:~% ~S~%Looking for dtrs:~% ~S~%");

    private static final SubLSymbol $sym251$PSP_LEXICAL_EDGE_ = makeSymbol("PSP-LEXICAL-EDGE?");

    private static final SubLList $list252 = list(reader_make_constant_shell("PhraseFn"), reader_make_constant_shell("Determiner"));

    private static final SubLSymbol $sym253$PSP_INCOMPLETE_EDGE_ = makeSymbol("PSP-INCOMPLETE-EDGE?");

    private static final SubLSymbol $sym254$PSP_NP_EDGE_ = makeSymbol("PSP-NP-EDGE?");



    private static final SubLString $str256$Initializing_Subcategorization_Fr = makeString("Initializing Subcategorization Frames...");

    private static final SubLSymbol ADD_SUBCAT_FRAME_INFO = makeSymbol("ADD-SUBCAT-FRAME-INFO");

    private static final SubLSymbol REMOVE_SUBCAT_FRAME_INFO = makeSymbol("REMOVE-SUBCAT-FRAME-INFO");

    private static final SubLSymbol PSP_BINDINGS_FROM_FRAME_INT = makeSymbol("PSP-BINDINGS-FROM-FRAME-INT");

    private static final SubLList $list262 = list(makeSymbol("?NUM"), makeSymbol("?KEY"));



    private static final SubLSymbol $psp_bindings_from_frame_int_caching_state$ = makeSymbol("*PSP-BINDINGS-FROM-FRAME-INT-CACHING-STATE*");

    private static final SubLInteger $int$128 = makeInteger(128);

    private static final SubLSymbol $sym266$PSP_PREDICATIVE_FRAME_ = makeSymbol("PSP-PREDICATIVE-FRAME?");



    private static final SubLSymbol $sym268$_PSP_PREDICATIVE_FRAME__CACHING_STATE_ = makeSymbol("*PSP-PREDICATIVE-FRAME?-CACHING-STATE*");

    private static final SubLSymbol PSP_KEYWORDS_FOR_FRAME = makeSymbol("PSP-KEYWORDS-FOR-FRAME");

    private static final SubLSymbol $sym270$_KEYWORD = makeSymbol("?KEYWORD");



    private static final SubLList $list272 = list(makeSymbol("?KEYWORD"));



    private static final SubLSymbol $psp_keywords_for_frame_caching_state$ = makeSymbol("*PSP-KEYWORDS-FOR-FRAME-CACHING-STATE*");

    private static final SubLSymbol $sym275$PSP_CONSTRAINED_FRAME_ = makeSymbol("PSP-CONSTRAINED-FRAME?");



    private static final SubLSymbol $sym278$_PSP_CONSTRAINED_FRAME__CACHING_STATE_ = makeSymbol("*PSP-CONSTRAINED-FRAME?-CACHING-STATE*");

    private static final SubLSymbol PSP_DEVISABLE_VERB_FRAMES = makeSymbol("PSP-DEVISABLE-VERB-FRAMES");

    private static final SubLSymbol $sym280$DEVISABLE_VERB_FRAME_ = makeSymbol("DEVISABLE-VERB-FRAME?");

    private static final SubLSymbol $psp_devisable_verb_frames_caching_state$ = makeSymbol("*PSP-DEVISABLE-VERB-FRAMES-CACHING-STATE*");

    private static final SubLSymbol $sym282$TRANSITIVE_NP_COMP_FRAME_ = makeSymbol("TRANSITIVE-NP-COMP-FRAME?");



    private static final SubLSymbol $sym284$_TRANSITIVE_NP_COMP_FRAME__CACHING_STATE_ = makeSymbol("*TRANSITIVE-NP-COMP-FRAME?-CACHING-STATE*");

    private static final SubLSymbol $sym285$INTRANSITIVE_FRAME_ = makeSymbol("INTRANSITIVE-FRAME?");



    static private final SubLList $list287 = list(ZERO_INTEGER);

    private static final SubLSymbol $sym288$_INTRANSITIVE_FRAME__CACHING_STATE_ = makeSymbol("*INTRANSITIVE-FRAME?-CACHING-STATE*");

    private static final SubLSymbol $sym289$PSP_FRAME_HAS_TYPE_ = makeSymbol("PSP-FRAME-HAS-TYPE?");

    private static final SubLSymbol $sym290$_PSP_FRAME_HAS_TYPE__CACHING_STATE_ = makeSymbol("*PSP-FRAME-HAS-TYPE?-CACHING-STATE*");

    private static final SubLSymbol PSP_PASSIVIZED_DTR_NUM = makeSymbol("PSP-PASSIVIZED-DTR-NUM");

    private static final SubLSymbol $sym292$_N = makeSymbol("?N");





    static private final SubLList $list295 = list(reader_make_constant_shell("FrameForVerbs"));

    private static final SubLList $list296 = list(makeSymbol("?N"), list(reader_make_constant_shell("PhraseFn"), reader_make_constant_shell("Noun")));

    private static final SubLSymbol $psp_passivized_dtr_num_caching_state$ = makeSymbol("*PSP-PASSIVIZED-DTR-NUM-CACHING-STATE*");

    private static final SubLList $list300 = list(makeKeyword("PASSIVE"), makeKeyword("PASSIVE-WITH-BY"));

    private static final SubLString $str301$Don_t_know_how_to_passivize__S___ = makeString("Don't know how to passivize ~S.~%");

    private static final SubLList $list302 = list(makeSymbol("TYPE"), makeSymbol("N"), makeSymbol("KEYWORD"));

    private static final SubLList $list303 = list(makeKeyword("BY-SUBJECT"));

    private static final SubLSymbol $sym305$PSP_CYCL__ = makeSymbol("PSP-CYCL-=");





    private static final SubLString $$$titlesConventionallyQuoted = makeString("titlesConventionallyQuoted");



    private static final SubLSymbol $sym317$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_ = makeSymbol("PSP-TERM-HAS-PREFERRED-LEXIFICATIONS?");

    private static final SubLList $list318 = list(makeSymbol("CYCL"), makeSymbol("POS"), makeSymbol("POS-PRED"), makeSymbol("WU"));





    private static final SubLSymbol $sym321$PSP_EDGE_SUBSUMES_ = makeSymbol("PSP-EDGE-SUBSUMES?");

    private static final SubLString $str322$__Removing_edge_____S__is_subsume = makeString("~&Removing edge:~% ~S~%is subsumed by~% ~S~%~%");

    private static final SubLSymbol PSP_FIND_FRAMES = makeSymbol("PSP-FIND-FRAMES");







    private static final SubLList $list327 = list(NIL);

    private static final SubLSymbol PSP_EDGE_TO_PPH_PHRASE = makeSymbol("PSP-EDGE-TO-PPH-PHRASE");

    private static final SubLSymbol TEST_SENTENCE_SET_WORDS_FROM_STRING_LIST = makeSymbol("TEST-SENTENCE-SET-WORDS-FROM-STRING-LIST");

    private static final SubLSymbol WORD_OFFSETS_EQUAL = makeSymbol("WORD-OFFSETS-EQUAL");

    private static final SubLList $list338 = list(list(list(list(makeString("6"), makeString("degrees"), makeString("per"), makeString("second"), makeString("per"), makeString("second")), makeString("6 degrees per second per  second")), list(ZERO_INTEGER, TWO_INTEGER, TEN_INTEGER, FOURTEEN_INTEGER, makeInteger(21), makeInteger(26))), list(list(list(makeString("6"), makeString("degrees"), makeString("per"), makeString("second"), makeString("per"), makeString("second")), makeString("6 degrees per second per second")), list(ZERO_INTEGER, TWO_INTEGER, TEN_INTEGER, FOURTEEN_INTEGER, makeInteger(21), makeInteger(25))));

    public static final SubLObject phrase_structure_parser_chart_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        pprint_psp_chart(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject phrase_structure_parser_chart_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        pprint_psp_chart(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject phrase_structure_parser_chart_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject phrase_structure_parser_chart_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native.class ? T : NIL;
    }

    public static final SubLObject psp_chart_id_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField2();
    }

    public static SubLObject psp_chart_id(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject psp_chart_input_string_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField3();
    }

    public static SubLObject psp_chart_input_string(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject psp_chart_length_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField4();
    }

    public static SubLObject psp_chart_length(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject psp_chart_spans_to_edges_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField5();
    }

    public static SubLObject psp_chart_spans_to_edges(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject psp_chart_gap_type_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField6();
    }

    public static SubLObject psp_chart_gap_type(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject psp_chart_memoization_state_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField7();
    }

    public static SubLObject psp_chart_memoization_state(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject psp_chart_edge_id_index_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField8();
    }

    public static SubLObject psp_chart_edge_id_index(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField8();
    }

    public static final SubLObject psp_chart_edges_to_nodes_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField9();
    }

    public static SubLObject psp_chart_edges_to_nodes(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField9();
    }

    public static final SubLObject psp_chart_max_edges_per_span_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField10();
    }

    public static SubLObject psp_chart_max_edges_per_span(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField10();
    }

    public static final SubLObject psp_chart_max_edges_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField11();
    }

    public static SubLObject psp_chart_max_edges(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField11();
    }

    public static final SubLObject psp_chart_maxed_out_spans_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField12();
    }

    public static SubLObject psp_chart_maxed_out_spans(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField12();
    }

    public static final SubLObject psp_chart_maxed_outP_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField13();
    }

    public static SubLObject psp_chart_maxed_outP(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField13();
    }

    public static final SubLObject psp_chart_parsed_lexical_spans_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.getField14();
    }

    public static SubLObject psp_chart_parsed_lexical_spans(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.getField14();
    }

    public static final SubLObject _csetf_psp_chart_id_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_psp_chart_id(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_psp_chart_input_string_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_psp_chart_input_string(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_psp_chart_length_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_psp_chart_length(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_psp_chart_spans_to_edges_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_psp_chart_spans_to_edges(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_psp_chart_gap_type_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_psp_chart_gap_type(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_psp_chart_memoization_state_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_psp_chart_memoization_state(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject _csetf_psp_chart_edge_id_index_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField8(value);
    }

    public static SubLObject _csetf_psp_chart_edge_id_index(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField8(value);
    }

    public static final SubLObject _csetf_psp_chart_edges_to_nodes_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField9(value);
    }

    public static SubLObject _csetf_psp_chart_edges_to_nodes(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField9(value);
    }

    public static final SubLObject _csetf_psp_chart_max_edges_per_span_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField10(value);
    }

    public static SubLObject _csetf_psp_chart_max_edges_per_span(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField10(value);
    }

    public static final SubLObject _csetf_psp_chart_max_edges_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField11(value);
    }

    public static SubLObject _csetf_psp_chart_max_edges(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField11(value);
    }

    public static final SubLObject _csetf_psp_chart_maxed_out_spans_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField12(value);
    }

    public static SubLObject _csetf_psp_chart_maxed_out_spans(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField12(value);
    }

    public static final SubLObject _csetf_psp_chart_maxed_outP_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField13(value);
    }

    public static SubLObject _csetf_psp_chart_maxed_outP(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField13(value);
    }

    public static final SubLObject _csetf_psp_chart_parsed_lexical_spans_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_CHART_P);
        return v_object.setField14(value);
    }

    public static SubLObject _csetf_psp_chart_parsed_lexical_spans(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_chart_p(v_object) : "! psp_chart.phrase_structure_parser_chart_p(v_object) " + "psp_chart.phrase_structure_parser_chart_p error :" + v_object;
        return v_object.setField14(value);
    }

    public static final SubLObject make_phrase_structure_parser_chart_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($ID)) {
                        _csetf_psp_chart_id(v_new, current_value);
                    } else {
                        if (pcase_var.eql($INPUT_STRING)) {
                            _csetf_psp_chart_input_string(v_new, current_value);
                        } else {
                            if (pcase_var.eql($LENGTH)) {
                                _csetf_psp_chart_length(v_new, current_value);
                            } else {
                                if (pcase_var.eql($SPANS_TO_EDGES)) {
                                    _csetf_psp_chart_spans_to_edges(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($GAP_TYPE)) {
                                        _csetf_psp_chart_gap_type(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($MEMOIZATION_STATE)) {
                                            _csetf_psp_chart_memoization_state(v_new, current_value);
                                        } else {
                                            if (pcase_var.eql($EDGE_ID_INDEX)) {
                                                _csetf_psp_chart_edge_id_index(v_new, current_value);
                                            } else {
                                                if (pcase_var.eql($EDGES_TO_NODES)) {
                                                    _csetf_psp_chart_edges_to_nodes(v_new, current_value);
                                                } else {
                                                    if (pcase_var.eql($MAX_EDGES_PER_SPAN)) {
                                                        _csetf_psp_chart_max_edges_per_span(v_new, current_value);
                                                    } else {
                                                        if (pcase_var.eql($MAX_EDGES)) {
                                                            _csetf_psp_chart_max_edges(v_new, current_value);
                                                        } else {
                                                            if (pcase_var.eql($MAXED_OUT_SPANS)) {
                                                                _csetf_psp_chart_maxed_out_spans(v_new, current_value);
                                                            } else {
                                                                if (pcase_var.eql($kw45$MAXED_OUT_)) {
                                                                    _csetf_psp_chart_maxed_outP(v_new, current_value);
                                                                } else {
                                                                    if (pcase_var.eql($PARSED_LEXICAL_SPANS)) {
                                                                        _csetf_psp_chart_parsed_lexical_spans(v_new, current_value);
                                                                    } else {
                                                                        Errors.error($str_alt47$Invalid_slot__S_for_construction_, current_arg);
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
            }
            return v_new;
        }
    }

    public static SubLObject make_phrase_structure_parser_chart(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_chart_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($ID)) {
                _csetf_psp_chart_id(v_new, current_value);
            } else
                if (pcase_var.eql($INPUT_STRING)) {
                    _csetf_psp_chart_input_string(v_new, current_value);
                } else
                    if (pcase_var.eql($LENGTH)) {
                        _csetf_psp_chart_length(v_new, current_value);
                    } else
                        if (pcase_var.eql($SPANS_TO_EDGES)) {
                            _csetf_psp_chart_spans_to_edges(v_new, current_value);
                        } else
                            if (pcase_var.eql($GAP_TYPE)) {
                                _csetf_psp_chart_gap_type(v_new, current_value);
                            } else
                                if (pcase_var.eql($MEMOIZATION_STATE)) {
                                    _csetf_psp_chart_memoization_state(v_new, current_value);
                                } else
                                    if (pcase_var.eql($EDGE_ID_INDEX)) {
                                        _csetf_psp_chart_edge_id_index(v_new, current_value);
                                    } else
                                        if (pcase_var.eql($EDGES_TO_NODES)) {
                                            _csetf_psp_chart_edges_to_nodes(v_new, current_value);
                                        } else
                                            if (pcase_var.eql($MAX_EDGES_PER_SPAN)) {
                                                _csetf_psp_chart_max_edges_per_span(v_new, current_value);
                                            } else
                                                if (pcase_var.eql($MAX_EDGES)) {
                                                    _csetf_psp_chart_max_edges(v_new, current_value);
                                                } else
                                                    if (pcase_var.eql($MAXED_OUT_SPANS)) {
                                                        _csetf_psp_chart_maxed_out_spans(v_new, current_value);
                                                    } else
                                                        if (pcase_var.eql($kw46$MAXED_OUT_)) {
                                                            _csetf_psp_chart_maxed_outP(v_new, current_value);
                                                        } else
                                                            if (pcase_var.eql($PARSED_LEXICAL_SPANS)) {
                                                                _csetf_psp_chart_parsed_lexical_spans(v_new, current_value);
                                                            } else {
                                                                Errors.error($str48$Invalid_slot__S_for_construction_, current_arg);
                                                            }












        }
        return v_new;
    }

    public static SubLObject visit_defstruct_phrase_structure_parser_chart(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_PHRASE_STRUCTURE_PARSER_CHART, THIRTEEN_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $ID, psp_chart_id(obj));
        funcall(visitor_fn, obj, $SLOT, $INPUT_STRING, psp_chart_input_string(obj));
        funcall(visitor_fn, obj, $SLOT, $LENGTH, psp_chart_length(obj));
        funcall(visitor_fn, obj, $SLOT, $SPANS_TO_EDGES, psp_chart_spans_to_edges(obj));
        funcall(visitor_fn, obj, $SLOT, $GAP_TYPE, psp_chart_gap_type(obj));
        funcall(visitor_fn, obj, $SLOT, $MEMOIZATION_STATE, psp_chart_memoization_state(obj));
        funcall(visitor_fn, obj, $SLOT, $EDGE_ID_INDEX, psp_chart_edge_id_index(obj));
        funcall(visitor_fn, obj, $SLOT, $EDGES_TO_NODES, psp_chart_edges_to_nodes(obj));
        funcall(visitor_fn, obj, $SLOT, $MAX_EDGES_PER_SPAN, psp_chart_max_edges_per_span(obj));
        funcall(visitor_fn, obj, $SLOT, $MAX_EDGES, psp_chart_max_edges(obj));
        funcall(visitor_fn, obj, $SLOT, $MAXED_OUT_SPANS, psp_chart_maxed_out_spans(obj));
        funcall(visitor_fn, obj, $SLOT, $kw46$MAXED_OUT_, psp_chart_maxed_outP(obj));
        funcall(visitor_fn, obj, $SLOT, $PARSED_LEXICAL_SPANS, psp_chart_parsed_lexical_spans(obj));
        funcall(visitor_fn, obj, $END, MAKE_PHRASE_STRUCTURE_PARSER_CHART, THIRTEEN_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_phrase_structure_parser_chart_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_phrase_structure_parser_chart(obj, visitor_fn);
    }

    public static final SubLObject pprint_psp_chart_alt(SubLObject v_psp_chart, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        format(stream, $str_alt49$__PSP_CHART__S__S_, psp_chart_id(v_psp_chart), psp_chart_input_string(v_psp_chart));
        return v_psp_chart;
    }

    public static SubLObject pprint_psp_chart(final SubLObject v_psp_chart, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        format(stream, $str55$__PSP_CHART__S__S_, psp_chart_id(v_psp_chart), psp_chart_input_string(v_psp_chart));
        return v_psp_chart;
    }

    public static final SubLObject new_psp_chart_alt(SubLObject input_string, SubLObject length) {
        if (input_string == UNPROVIDED) {
            input_string = NIL;
        }
        if (length == UNPROVIDED) {
            length = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject chart = make_phrase_structure_parser_chart(UNPROVIDED);
                _csetf_psp_chart_id(chart, $psp_chart_id_counter$.getGlobalValue());
                $psp_chart_id_counter$.setGlobalValue(add($psp_chart_id_counter$.getGlobalValue(), ONE_INTEGER));
                psp_chart_set_string(chart, input_string);
                _csetf_psp_chart_length(chart, length);
                _csetf_psp_chart_spans_to_edges(chart, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
                _csetf_psp_chart_memoization_state(chart, memoization_state.new_memoization_state(UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                if (NIL != psp_chart_index_edgesP()) {
                    _csetf_psp_chart_edge_id_index(chart, new_id_index(UNPROVIDED, UNPROVIDED));
                } else {
                    _csetf_psp_chart_edge_id_index(chart, ZERO_INTEGER);
                }
                _csetf_psp_chart_edges_to_nodes(chart, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
                _csetf_psp_chart_max_edges(chart, parsing_vars.$psp_max_edges_per_chart$.getDynamicValue(thread));
                _csetf_psp_chart_max_edges_per_span(chart, parsing_vars.$psp_max_edges_per_span$.getDynamicValue(thread));
                _csetf_psp_chart_maxed_out_spans(chart, set.new_set(symbol_function(EQ), UNPROVIDED));
                _csetf_psp_chart_maxed_outP(chart, NIL);
                _csetf_psp_chart_parsed_lexical_spans(chart, set.new_set(symbol_function(EQ), UNPROVIDED));
                return chart;
            }
        }
    }

    public static SubLObject new_psp_chart(SubLObject input_string, SubLObject length) {
        if (input_string == UNPROVIDED) {
            input_string = NIL;
        }
        if (length == UNPROVIDED) {
            length = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject chart = make_phrase_structure_parser_chart(UNPROVIDED);
        _csetf_psp_chart_id(chart, $psp_chart_id_counter$.getGlobalValue());
        $psp_chart_id_counter$.setGlobalValue(add($psp_chart_id_counter$.getGlobalValue(), ONE_INTEGER));
        psp_chart_set_string(chart, input_string);
        _csetf_psp_chart_length(chart, length);
        _csetf_psp_chart_spans_to_edges(chart, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        _csetf_psp_chart_memoization_state(chart, memoization_state.new_memoization_state(UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        if (NIL != psp_chart_index_edgesP()) {
            _csetf_psp_chart_edge_id_index(chart, new_id_index(UNPROVIDED, UNPROVIDED));
        } else {
            _csetf_psp_chart_edge_id_index(chart, ZERO_INTEGER);
        }
        _csetf_psp_chart_edges_to_nodes(chart, dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        _csetf_psp_chart_max_edges(chart, parsing_vars.$psp_max_edges_per_chart$.getDynamicValue(thread));
        _csetf_psp_chart_max_edges_per_span(chart, parsing_vars.$psp_max_edges_per_span$.getDynamicValue(thread));
        _csetf_psp_chart_maxed_out_spans(chart, set.new_set(symbol_function(EQ), UNPROVIDED));
        _csetf_psp_chart_maxed_outP(chart, NIL);
        _csetf_psp_chart_parsed_lexical_spans(chart, set.new_set(symbol_function(EQ), UNPROVIDED));
        return chart;
    }

    public static final SubLObject destroy_psp_chart_alt(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        psp_chart_clear(chart);
        return $DESTROYED;
    }

    public static SubLObject destroy_psp_chart(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        psp_chart_clear(chart);
        return $DESTROYED;
    }

    public static final SubLObject psp_chart_clear_alt(SubLObject chart) {
        _csetf_psp_chart_input_string(chart, NIL);
        _csetf_psp_chart_length(chart, NIL);
        dictionary.clear_dictionary(psp_chart_spans_to_edges(chart));
        dictionary.clear_dictionary(psp_chart_edges_to_nodes(chart));
        set.clear_set(psp_chart_maxed_out_spans(chart));
        set.clear_set(psp_chart_parsed_lexical_spans(chart));
        memoization_state.clear_all_memoization(psp_chart_memoization_state(chart));
        {
            SubLObject v_id_index = psp_chart_edge_id_index(chart);
            if (NIL != id_index_p(v_id_index)) {
                clear_id_index(v_id_index);
            }
        }
        return $CLEARED;
    }

    public static SubLObject psp_chart_clear(final SubLObject chart) {
        _csetf_psp_chart_input_string(chart, NIL);
        _csetf_psp_chart_length(chart, NIL);
        dictionary.clear_dictionary(psp_chart_spans_to_edges(chart));
        dictionary.clear_dictionary(psp_chart_edges_to_nodes(chart));
        set.clear_set(psp_chart_maxed_out_spans(chart));
        set.clear_set(psp_chart_parsed_lexical_spans(chart));
        memoization_state.clear_all_memoization(psp_chart_memoization_state(chart));
        final SubLObject v_id_index = psp_chart_edge_id_index(chart);
        if (NIL != id_index_p(v_id_index)) {
            clear_id_index(v_id_index);
        }
        return $CLEARED;
    }

    public static final SubLObject psp_chart_set_string_alt(SubLObject chart, SubLObject string) {
        _csetf_psp_chart_input_string(chart, string);
        return $SET;
    }

    public static SubLObject psp_chart_set_string(final SubLObject chart, final SubLObject string) {
        _csetf_psp_chart_input_string(chart, string);
        return $SET;
    }

    public static final SubLObject psp_chart_new_edge_id_alt(SubLObject chart) {
        {
            SubLObject id_index_or_counter = psp_chart_edge_id_index(chart);
            if (NIL != id_index_p(id_index_or_counter)) {
                return id_index_reserve(id_index_or_counter);
            } else {
                if (id_index_or_counter.isInteger()) {
                    {
                        SubLObject id = id_index_or_counter;
                        _csetf_psp_chart_edge_id_index(chart, add(psp_chart_edge_id_index(chart), ONE_INTEGER));
                        return id;
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject psp_chart_new_edge_id(final SubLObject chart) {
        final SubLObject id_index_or_counter = psp_chart_edge_id_index(chart);
        if (NIL != id_index_p(id_index_or_counter)) {
            return id_index_reserve(id_index_or_counter);
        }
        if (id_index_or_counter.isInteger()) {
            final SubLObject id = id_index_or_counter;
            _csetf_psp_chart_edge_id_index(chart, add(psp_chart_edge_id_index(chart), ONE_INTEGER));
            return id;
        }
        return NIL;
    }

    public static final SubLObject psp_chart_index_edgesP_alt() {
        return $psp_chart_index_edgesP$.getGlobalValue();
    }

    public static SubLObject psp_chart_index_edgesP() {
        return $psp_chart_index_edgesP$.getGlobalValue();
    }

    public static final SubLObject psp_find_edge_by_id_alt(SubLObject suid, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (NIL == psp_chart_index_edgesP()) {
            Errors.warn($str_alt53$Not_indexing_PSP_edges__Can_t_fin);
            return NIL;
        } else {
            if (NIL == phrase_structure_parser_chart_p(chart)) {
                Errors.warn($str_alt54$_S_is_not_a_PSP_chart__Can_t_find);
                return NIL;
            } else {
                {
                    SubLObject v_id_index = psp_chart_edge_id_index(chart);
                    return id_index_lookup(v_id_index, suid, UNPROVIDED);
                }
            }
        }
    }

    public static SubLObject psp_find_edge_by_id(final SubLObject suid, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (NIL == psp_chart_index_edgesP()) {
            Errors.warn($str59$Not_indexing_PSP_edges__Can_t_fin);
            return NIL;
        }
        if (NIL == phrase_structure_parser_chart_p(chart)) {
            Errors.warn($str60$_S_is_not_a_PSP_chart__Can_t_find);
            return NIL;
        }
        final SubLObject v_id_index = psp_chart_edge_id_index(chart);
        return id_index_lookup(v_id_index, suid, UNPROVIDED);
    }

    public static final SubLObject psp_chart_set_gap_type_alt(SubLObject chart, SubLObject gap_type) {
        _csetf_psp_chart_gap_type(chart, gap_type);
        return $SET;
    }

    public static SubLObject psp_chart_set_gap_type(final SubLObject chart, final SubLObject gap_type) {
        _csetf_psp_chart_gap_type(chart, gap_type);
        return $SET;
    }

    public static final SubLObject phrase_structure_parser_phrasal_edge_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        pprint_psp_phrasal_edge(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject phrase_structure_parser_phrasal_edge_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        pprint_psp_phrasal_edge(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject phrase_structure_parser_phrasal_edge_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject phrase_structure_parser_phrasal_edge_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native.class ? T : NIL;
    }

    public static final SubLObject psp_phrasal_edge_weight_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField2();
    }

    public static SubLObject psp_phrasal_edge_weight(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject psp_phrasal_edge_string_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField3();
    }

    public static SubLObject psp_phrasal_edge_string(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject psp_phrasal_edge_span_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField4();
    }

    public static SubLObject psp_phrasal_edge_span(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject psp_phrasal_edge_category_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField5();
    }

    public static SubLObject psp_phrasal_edge_category(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject psp_phrasal_edge_pos_pred_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField6();
    }

    public static SubLObject psp_phrasal_edge_pos_pred(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject psp_phrasal_edge_cycls_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField7();
    }

    public static SubLObject psp_phrasal_edge_cycls(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject psp_phrasal_edge_rule_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField8();
    }

    public static SubLObject psp_phrasal_edge_rule(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField8();
    }

    public static final SubLObject psp_phrasal_edge_new_dtr_constraints_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField9();
    }

    public static SubLObject psp_phrasal_edge_new_dtr_constraints(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField9();
    }

    public static final SubLObject psp_phrasal_edge_head_dtr_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField10();
    }

    public static SubLObject psp_phrasal_edge_head_dtr(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField10();
    }

    public static final SubLObject psp_phrasal_edge_non_head_dtrs_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField11();
    }

    public static SubLObject psp_phrasal_edge_non_head_dtrs(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField11();
    }

    public static final SubLObject psp_phrasal_edge_id_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField12();
    }

    public static SubLObject psp_phrasal_edge_id(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField12();
    }

    public static final SubLObject psp_phrasal_edge_chart_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.getField13();
    }

    public static SubLObject psp_phrasal_edge_chart(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.getField13();
    }

    public static final SubLObject _csetf_psp_phrasal_edge_weight_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_weight(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_string_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_string(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_span_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_span(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_category_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_category(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_pos_pred_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_pos_pred(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_cycls_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_cycls(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_rule_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField8(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_rule(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField8(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_new_dtr_constraints_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField9(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_new_dtr_constraints(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField9(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_head_dtr_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField10(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_head_dtr(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField10(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_non_head_dtrs_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField11(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_non_head_dtrs(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField11(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_id_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField12(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_id(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField12(value);
    }

    public static final SubLObject _csetf_psp_phrasal_edge_chart_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_P);
        return v_object.setField13(value);
    }

    public static SubLObject _csetf_psp_phrasal_edge_chart(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_phrasal_edge_p(v_object) : "! psp_chart.phrase_structure_parser_phrasal_edge_p(v_object) " + "psp_chart.phrase_structure_parser_phrasal_edge_p error :" + v_object;
        return v_object.setField13(value);
    }

    public static final SubLObject make_phrase_structure_parser_phrasal_edge_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($WEIGHT)) {
                        _csetf_psp_phrasal_edge_weight(v_new, current_value);
                    } else {
                        if (pcase_var.eql($STRING)) {
                            _csetf_psp_phrasal_edge_string(v_new, current_value);
                        } else {
                            if (pcase_var.eql($SPAN)) {
                                _csetf_psp_phrasal_edge_span(v_new, current_value);
                            } else {
                                if (pcase_var.eql($CATEGORY)) {
                                    _csetf_psp_phrasal_edge_category(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($POS_PRED)) {
                                        _csetf_psp_phrasal_edge_pos_pred(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($CYCLS)) {
                                            _csetf_psp_phrasal_edge_cycls(v_new, current_value);
                                        } else {
                                            if (pcase_var.eql($RULE)) {
                                                _csetf_psp_phrasal_edge_rule(v_new, current_value);
                                            } else {
                                                if (pcase_var.eql($NEW_DTR_CONSTRAINTS)) {
                                                    _csetf_psp_phrasal_edge_new_dtr_constraints(v_new, current_value);
                                                } else {
                                                    if (pcase_var.eql($HEAD_DTR)) {
                                                        _csetf_psp_phrasal_edge_head_dtr(v_new, current_value);
                                                    } else {
                                                        if (pcase_var.eql($NON_HEAD_DTRS)) {
                                                            _csetf_psp_phrasal_edge_non_head_dtrs(v_new, current_value);
                                                        } else {
                                                            if (pcase_var.eql($ID)) {
                                                                _csetf_psp_phrasal_edge_id(v_new, current_value);
                                                            } else {
                                                                if (pcase_var.eql($CHART)) {
                                                                    _csetf_psp_phrasal_edge_chart(v_new, current_value);
                                                                } else {
                                                                    Errors.error($str_alt47$Invalid_slot__S_for_construction_, current_arg);
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
            return v_new;
        }
    }

    public static SubLObject make_phrase_structure_parser_phrasal_edge(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_phrasal_edge_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($WEIGHT)) {
                _csetf_psp_phrasal_edge_weight(v_new, current_value);
            } else
                if (pcase_var.eql($STRING)) {
                    _csetf_psp_phrasal_edge_string(v_new, current_value);
                } else
                    if (pcase_var.eql($SPAN)) {
                        _csetf_psp_phrasal_edge_span(v_new, current_value);
                    } else
                        if (pcase_var.eql($CATEGORY)) {
                            _csetf_psp_phrasal_edge_category(v_new, current_value);
                        } else
                            if (pcase_var.eql($POS_PRED)) {
                                _csetf_psp_phrasal_edge_pos_pred(v_new, current_value);
                            } else
                                if (pcase_var.eql($CYCLS)) {
                                    _csetf_psp_phrasal_edge_cycls(v_new, current_value);
                                } else
                                    if (pcase_var.eql($RULE)) {
                                        _csetf_psp_phrasal_edge_rule(v_new, current_value);
                                    } else
                                        if (pcase_var.eql($NEW_DTR_CONSTRAINTS)) {
                                            _csetf_psp_phrasal_edge_new_dtr_constraints(v_new, current_value);
                                        } else
                                            if (pcase_var.eql($HEAD_DTR)) {
                                                _csetf_psp_phrasal_edge_head_dtr(v_new, current_value);
                                            } else
                                                if (pcase_var.eql($NON_HEAD_DTRS)) {
                                                    _csetf_psp_phrasal_edge_non_head_dtrs(v_new, current_value);
                                                } else
                                                    if (pcase_var.eql($ID)) {
                                                        _csetf_psp_phrasal_edge_id(v_new, current_value);
                                                    } else
                                                        if (pcase_var.eql($CHART)) {
                                                            _csetf_psp_phrasal_edge_chart(v_new, current_value);
                                                        } else {
                                                            Errors.error($str48$Invalid_slot__S_for_construction_, current_arg);
                                                        }











        }
        return v_new;
    }

    public static SubLObject visit_defstruct_phrase_structure_parser_phrasal_edge(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE, TWELVE_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $WEIGHT, psp_phrasal_edge_weight(obj));
        funcall(visitor_fn, obj, $SLOT, $STRING, psp_phrasal_edge_string(obj));
        funcall(visitor_fn, obj, $SLOT, $SPAN, psp_phrasal_edge_span(obj));
        funcall(visitor_fn, obj, $SLOT, $CATEGORY, psp_phrasal_edge_category(obj));
        funcall(visitor_fn, obj, $SLOT, $POS_PRED, psp_phrasal_edge_pos_pred(obj));
        funcall(visitor_fn, obj, $SLOT, $CYCLS, psp_phrasal_edge_cycls(obj));
        funcall(visitor_fn, obj, $SLOT, $RULE, psp_phrasal_edge_rule(obj));
        funcall(visitor_fn, obj, $SLOT, $NEW_DTR_CONSTRAINTS, psp_phrasal_edge_new_dtr_constraints(obj));
        funcall(visitor_fn, obj, $SLOT, $HEAD_DTR, psp_phrasal_edge_head_dtr(obj));
        funcall(visitor_fn, obj, $SLOT, $NON_HEAD_DTRS, psp_phrasal_edge_non_head_dtrs(obj));
        funcall(visitor_fn, obj, $SLOT, $ID, psp_phrasal_edge_id(obj));
        funcall(visitor_fn, obj, $SLOT, $CHART, psp_phrasal_edge_chart(obj));
        funcall(visitor_fn, obj, $END, MAKE_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE, TWELVE_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_phrase_structure_parser_phrasal_edge_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_phrase_structure_parser_phrasal_edge(obj, visitor_fn);
    }

    public static final SubLObject sxhash_phrase_structure_parser_phrasal_edge_method_alt(SubLObject v_object) {
        return sxhash_psp_edge(v_object);
    }

    public static SubLObject sxhash_phrase_structure_parser_phrasal_edge_method(final SubLObject v_object) {
        return sxhash_psp_edge(v_object);
    }

    public static final SubLObject pprint_psp_phrasal_edge_alt(SubLObject psp_phrasal_edge, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        format(stream, $str_alt99$__PSP_PHRASAL_EDGE__S__S_, psp_phrasal_edge_id(psp_phrasal_edge), psp_phrasal_edge_rule(psp_phrasal_edge));
        return psp_phrasal_edge;
    }

    public static SubLObject pprint_psp_phrasal_edge(final SubLObject psp_phrasal_edge, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        format(stream, $str108$__PSP_PHRASAL_EDGE__S__S__S_, new SubLObject[]{ psp_phrasal_edge_id(psp_phrasal_edge), psp_phrasal_edge_string(psp_phrasal_edge), psp_phrasal_edge_rule(psp_phrasal_edge) });
        return psp_phrasal_edge;
    }

    public static final SubLObject phrase_structure_parser_lexical_edge_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        pprint_psp_lexical_edge(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject phrase_structure_parser_lexical_edge_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        pprint_psp_lexical_edge(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject phrase_structure_parser_lexical_edge_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_lexical_edge_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject phrase_structure_parser_lexical_edge_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_lexical_edge_native.class ? T : NIL;
    }

    public static final SubLObject psp_lexical_edge_weight_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField2();
    }

    public static SubLObject psp_lexical_edge_weight(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject psp_lexical_edge_string_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField3();
    }

    public static SubLObject psp_lexical_edge_string(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject psp_lexical_edge_span_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField4();
    }

    public static SubLObject psp_lexical_edge_span(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject psp_lexical_edge_category_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField5();
    }

    public static SubLObject psp_lexical_edge_category(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject psp_lexical_edge_pos_pred_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField6();
    }

    public static SubLObject psp_lexical_edge_pos_pred(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject psp_lexical_edge_cycls_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField7();
    }

    public static SubLObject psp_lexical_edge_cycls(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject psp_lexical_edge_wu_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField8();
    }

    public static SubLObject psp_lexical_edge_wu(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField8();
    }

    public static final SubLObject psp_lexical_edge_frame_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField9();
    }

    public static SubLObject psp_lexical_edge_frame(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField9();
    }

    public static final SubLObject psp_lexical_edge_id_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField10();
    }

    public static SubLObject psp_lexical_edge_id(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField10();
    }

    public static final SubLObject psp_lexical_edge_chart_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.getField11();
    }

    public static SubLObject psp_lexical_edge_chart(final SubLObject v_object) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.getField11();
    }

    public static final SubLObject _csetf_psp_lexical_edge_weight_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_weight(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_string_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_string(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_span_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_span(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_category_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_category(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_pos_pred_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_pos_pred(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_cycls_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_cycls(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_wu_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField8(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_wu(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField8(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_frame_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField9(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_frame(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField9(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_id_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField10(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_id(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField10(value);
    }

    public static final SubLObject _csetf_psp_lexical_edge_chart_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P);
        return v_object.setField11(value);
    }

    public static SubLObject _csetf_psp_lexical_edge_chart(final SubLObject v_object, final SubLObject value) {
        assert NIL != phrase_structure_parser_lexical_edge_p(v_object) : "! psp_chart.phrase_structure_parser_lexical_edge_p(v_object) " + "psp_chart.phrase_structure_parser_lexical_edge_p error :" + v_object;
        return v_object.setField11(value);
    }

    public static final SubLObject make_phrase_structure_parser_lexical_edge_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_lexical_edge_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($WEIGHT)) {
                        _csetf_psp_lexical_edge_weight(v_new, current_value);
                    } else {
                        if (pcase_var.eql($STRING)) {
                            _csetf_psp_lexical_edge_string(v_new, current_value);
                        } else {
                            if (pcase_var.eql($SPAN)) {
                                _csetf_psp_lexical_edge_span(v_new, current_value);
                            } else {
                                if (pcase_var.eql($CATEGORY)) {
                                    _csetf_psp_lexical_edge_category(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($POS_PRED)) {
                                        _csetf_psp_lexical_edge_pos_pred(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($CYCLS)) {
                                            _csetf_psp_lexical_edge_cycls(v_new, current_value);
                                        } else {
                                            if (pcase_var.eql($WU)) {
                                                _csetf_psp_lexical_edge_wu(v_new, current_value);
                                            } else {
                                                if (pcase_var.eql($FRAME)) {
                                                    _csetf_psp_lexical_edge_frame(v_new, current_value);
                                                } else {
                                                    if (pcase_var.eql($ID)) {
                                                        _csetf_psp_lexical_edge_id(v_new, current_value);
                                                    } else {
                                                        if (pcase_var.eql($CHART)) {
                                                            _csetf_psp_lexical_edge_chart(v_new, current_value);
                                                        } else {
                                                            Errors.error($str_alt47$Invalid_slot__S_for_construction_, current_arg);
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
            return v_new;
        }
    }

    public static SubLObject make_phrase_structure_parser_lexical_edge(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_lexical_edge_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($WEIGHT)) {
                _csetf_psp_lexical_edge_weight(v_new, current_value);
            } else
                if (pcase_var.eql($STRING)) {
                    _csetf_psp_lexical_edge_string(v_new, current_value);
                } else
                    if (pcase_var.eql($SPAN)) {
                        _csetf_psp_lexical_edge_span(v_new, current_value);
                    } else
                        if (pcase_var.eql($CATEGORY)) {
                            _csetf_psp_lexical_edge_category(v_new, current_value);
                        } else
                            if (pcase_var.eql($POS_PRED)) {
                                _csetf_psp_lexical_edge_pos_pred(v_new, current_value);
                            } else
                                if (pcase_var.eql($CYCLS)) {
                                    _csetf_psp_lexical_edge_cycls(v_new, current_value);
                                } else
                                    if (pcase_var.eql($WU)) {
                                        _csetf_psp_lexical_edge_wu(v_new, current_value);
                                    } else
                                        if (pcase_var.eql($FRAME)) {
                                            _csetf_psp_lexical_edge_frame(v_new, current_value);
                                        } else
                                            if (pcase_var.eql($ID)) {
                                                _csetf_psp_lexical_edge_id(v_new, current_value);
                                            } else
                                                if (pcase_var.eql($CHART)) {
                                                    _csetf_psp_lexical_edge_chart(v_new, current_value);
                                                } else {
                                                    Errors.error($str48$Invalid_slot__S_for_construction_, current_arg);
                                                }









        }
        return v_new;
    }

    public static SubLObject visit_defstruct_phrase_structure_parser_lexical_edge(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE, TEN_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $WEIGHT, psp_lexical_edge_weight(obj));
        funcall(visitor_fn, obj, $SLOT, $STRING, psp_lexical_edge_string(obj));
        funcall(visitor_fn, obj, $SLOT, $SPAN, psp_lexical_edge_span(obj));
        funcall(visitor_fn, obj, $SLOT, $CATEGORY, psp_lexical_edge_category(obj));
        funcall(visitor_fn, obj, $SLOT, $POS_PRED, psp_lexical_edge_pos_pred(obj));
        funcall(visitor_fn, obj, $SLOT, $CYCLS, psp_lexical_edge_cycls(obj));
        funcall(visitor_fn, obj, $SLOT, $WU, psp_lexical_edge_wu(obj));
        funcall(visitor_fn, obj, $SLOT, $FRAME, psp_lexical_edge_frame(obj));
        funcall(visitor_fn, obj, $SLOT, $ID, psp_lexical_edge_id(obj));
        funcall(visitor_fn, obj, $SLOT, $CHART, psp_lexical_edge_chart(obj));
        funcall(visitor_fn, obj, $END, MAKE_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE, TEN_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_phrase_structure_parser_lexical_edge_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_phrase_structure_parser_lexical_edge(obj, visitor_fn);
    }

    public static final SubLObject sxhash_psp_edge_alt(SubLObject edge) {
        {
            SubLObject id = get_psp_edge_id(edge);
            if (id.isFixnum()) {
                return id;
            } else {
                return Sxhash.sxhash(get_psp_edge_string(edge));
            }
        }
    }

    public static SubLObject sxhash_psp_edge(final SubLObject edge) {
        final SubLObject id = get_psp_edge_id(edge);
        if (id.isFixnum()) {
            return id;
        }
        return Sxhash.sxhash(get_psp_edge_string(edge));
    }

    public static final SubLObject sxhash_phrase_structure_parser_lexical_edge_method_alt(SubLObject v_object) {
        return sxhash_psp_edge(v_object);
    }

    public static SubLObject sxhash_phrase_structure_parser_lexical_edge_method(final SubLObject v_object) {
        return sxhash_psp_edge(v_object);
    }

    /**
     *
     *
     * @return LISTP of all spans with edges in CHART, from START to END.
     */
    @LispMethod(comment = "@return LISTP of all spans with edges in CHART, from START to END.")
    public static final SubLObject psp_chart_spans_alt(SubLObject chart, SubLObject start, SubLObject end) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (start == UNPROVIDED) {
            start = parsing_vars.$psp_chart_start_index$.getDynamicValue();
        }
        if (end == UNPROVIDED) {
            end = parsing_vars.$psp_chart_end_index$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject spans = NIL;
                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(psp_chart_spans_to_edges(chart)));
                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject span = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                        SubLObject value = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (interval_span.interval_span_start(span).numGE(start) && ((NIL == end) || interval_span.interval_span_end(span).numLE(end))) {
                            spans = cons(span, spans);
                        }
                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                    }
                } 
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                return spans;
            }
        }
    }

    /**
     *
     *
     * @return LISTP of all spans with edges in CHART, from START to END.
     */
    @LispMethod(comment = "@return LISTP of all spans with edges in CHART, from START to END.")
    public static SubLObject psp_chart_spans(SubLObject chart, SubLObject start, SubLObject end) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (start == UNPROVIDED) {
            start = parsing_vars.$psp_chart_start_index$.getDynamicValue();
        }
        if (end == UNPROVIDED) {
            end = parsing_vars.$psp_chart_end_index$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject spans = NIL;
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(psp_chart_spans_to_edges(chart))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject span = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject value = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (interval_span.interval_span_start(span).numGE(start) && ((NIL == end) || interval_span.interval_span_end(span).numLE(end))) {
                spans = cons(span, spans);
            }
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return spans;
    }

    public static final SubLObject pprint_psp_lexical_edge_alt(SubLObject psp_lexical_edge, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        {
            SubLObject cycls = psp_lexical_edge_cycls(psp_lexical_edge);
            format(stream, $str_alt131$__PSP_LEXICAL_EDGE__S__S__S_W__S_, new SubLObject[]{ psp_lexical_edge_id(psp_lexical_edge), psp_lexical_edge_string(psp_lexical_edge), NIL != dictionary.dictionary_p(cycls) ? ((SubLObject) (dictionary.dictionary_values(cycls))) : string_utilities.$empty_string$.getGlobalValue(), psp_lexical_edge_weight(psp_lexical_edge) });
        }
        return psp_lexical_edge;
    }

    public static SubLObject pprint_psp_lexical_edge(final SubLObject psp_lexical_edge, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = NIL;
        }
        if (depth == UNPROVIDED) {
            depth = NIL;
        }
        final SubLObject cycls = psp_lexical_edge_cycls(psp_lexical_edge);
        format(stream, $str143$__PSP_LEXICAL_EDGE__S__S__S__S_W_, new SubLObject[]{ psp_lexical_edge_id(psp_lexical_edge), psp_lexical_edge_string(psp_lexical_edge), get_psp_edge_category(psp_lexical_edge), NIL != dictionary.dictionary_p(cycls) ? dictionary.dictionary_values(cycls) : string_utilities.$empty_string$.getGlobalValue(), psp_lexical_edge_weight(psp_lexical_edge) });
        return psp_lexical_edge;
    }

    /**
     * Print out the content of EDGE.
     */
    @LispMethod(comment = "Print out the content of EDGE.")
    public static final SubLObject psp_format_edge_alt(SubLObject edge, SubLObject stream) {
        if (stream == UNPROVIDED) {
            stream = T;
        }
        {
            SubLObject semx = psp_semantics.psp_semantics_for_edge(edge, UNPROVIDED, UNPROVIDED);
            if (NIL != semx) {
                format(stream, $str_alt132$___S___S______S__, new SubLObject[]{ get_psp_edge_category(edge), get_psp_edge_pos_pred(edge), semx });
            }
        }
        return NIL;
    }

    /**
     * Print out the content of EDGE.
     */
    @LispMethod(comment = "Print out the content of EDGE.")
    public static SubLObject psp_format_edge(final SubLObject edge, SubLObject stream) {
        if (stream == UNPROVIDED) {
            stream = T;
        }
        final SubLObject semx = psp_semantics.psp_semantics_for_edge(edge, UNPROVIDED, UNPROVIDED);
        if (NIL != semx) {
            format(stream, $str144$___S___S______S__, new SubLObject[]{ get_psp_edge_category(edge), get_psp_edge_pos_pred(edge), semx });
        }
        return NIL;
    }

    public static final SubLObject phrase_structure_parser_edge_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != phrase_structure_parser_phrasal_edge_p(v_object)) || (NIL != phrase_structure_parser_lexical_edge_p(v_object)));
    }

    public static SubLObject phrase_structure_parser_edge_p(final SubLObject v_object) {
        return makeBoolean((NIL != phrase_structure_parser_phrasal_edge_p(v_object)) || (NIL != phrase_structure_parser_lexical_edge_p(v_object)));
    }

    public static final SubLObject get_psp_edge_weight_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_weight(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return psp_lexical_edge_weight(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_weight(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_weight(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_weight(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject get_psp_edge_string_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            {
                SubLObject string = psp_phrasal_edge_string(edge);
                if (NIL == string) {
                    {
                        SubLObject dtr_strings = NIL;
                        SubLObject csome_list_var = psp_edge_dtrs(edge);
                        SubLObject dtr = NIL;
                        for (dtr = csome_list_var.first(); NIL != csome_list_var; csome_list_var = csome_list_var.rest() , dtr = csome_list_var.first()) {
                            dtr_strings = cons(get_psp_edge_string(dtr), dtr_strings);
                        }
                        string = string_utilities.bunge(nreverse(dtr_strings), UNPROVIDED);
                        _csetf_psp_phrasal_edge_string(edge, string);
                    }
                }
                return string;
            }
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return psp_lexical_edge_string(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_string(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            SubLObject string = psp_phrasal_edge_string(edge);
            if (NIL == string) {
                SubLObject dtr_strings = NIL;
                SubLObject csome_list_var = psp_edge_dtrs(edge);
                SubLObject dtr = NIL;
                dtr = csome_list_var.first();
                while (NIL != csome_list_var) {
                    dtr_strings = cons(get_psp_edge_string(dtr), dtr_strings);
                    csome_list_var = csome_list_var.rest();
                    dtr = csome_list_var.first();
                } 
                string = string_utilities.bunge(nreverse(dtr_strings), UNPROVIDED);
                _csetf_psp_phrasal_edge_string(edge, string);
            }
            return string;
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_string(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject get_psp_edge_span_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_span(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return psp_lexical_edge_span(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_span(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_span(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_span(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject get_psp_edge_category_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_category(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return psp_lexical_edge_category(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_category(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_category(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_category(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject get_psp_edge_pos_pred_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_pos_pred(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return psp_lexical_edge_pos_pred(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_pos_pred(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_pos_pred(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_pos_pred(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject get_psp_edge_cycls_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            {
                SubLObject cycls = psp_phrasal_edge_cycls(edge);
                if (NIL == cycls) {
                    cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
                    _csetf_psp_phrasal_edge_cycls(edge, cycls);
                }
                return cycls;
            }
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                {
                    SubLObject cycls = psp_lexical_edge_cycls(edge);
                    if (NIL == cycls) {
                        cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
                        _csetf_psp_lexical_edge_cycls(edge, cycls);
                    }
                    return cycls;
                }
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_cycls(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            SubLObject cycls = psp_phrasal_edge_cycls(edge);
            if (NIL == cycls) {
                cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
                _csetf_psp_phrasal_edge_cycls(edge, cycls);
            }
            return cycls;
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            SubLObject cycls = psp_lexical_edge_cycls(edge);
            if (NIL == cycls) {
                cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
                _csetf_psp_lexical_edge_cycls(edge, cycls);
            }
            return cycls;
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject get_psp_edge_rule_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_rule(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject get_psp_edge_rule(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_rule(edge);
        }
        return NIL;
    }

    public static final SubLObject get_psp_edge_new_dtr_constraints_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_new_dtr_constraints(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject get_psp_edge_new_dtr_constraints(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_new_dtr_constraints(edge);
        }
        return NIL;
    }

    public static final SubLObject get_psp_edge_head_dtr_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_head_dtr(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject get_psp_edge_head_dtr(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_head_dtr(edge);
        }
        return NIL;
    }

    public static final SubLObject get_psp_edge_non_head_dtrs_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_non_head_dtrs(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject get_psp_edge_non_head_dtrs(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_non_head_dtrs(edge);
        }
        return NIL;
    }

    public static final SubLObject get_psp_edge_wu_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_wu(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject get_psp_edge_wu(final SubLObject edge) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_wu(edge);
        }
        return NIL;
    }

    public static final SubLObject get_psp_edge_frame_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_frame(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject get_psp_edge_frame(final SubLObject edge) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_frame(edge);
        }
        return NIL;
    }

    public static final SubLObject get_psp_edge_id_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_id(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return psp_lexical_edge_id(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_id(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_id(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_id(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject get_psp_edge_chart_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_chart(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return psp_lexical_edge_chart(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject get_psp_edge_chart(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            return psp_phrasal_edge_chart(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            return psp_lexical_edge_chart(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    /**
     *
     *
     * @return phrase-structure-parser-lexical-edge-p
     */
    @LispMethod(comment = "@return phrase-structure-parser-lexical-edge-p")
    public static final SubLObject get_psp_edge_head_lexeme_alt(SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            {
                SubLObject head_dtr = psp_phrasal_edge_head_dtr(edge);
                if (NIL != head_dtr) {
                    return get_psp_edge_head_lexeme(head_dtr);
                }
                Errors.warn($str_alt134$Headless_edge___S, edge);
                return NIL;
            }
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return edge;
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return phrase-structure-parser-lexical-edge-p
     */
    @LispMethod(comment = "@return phrase-structure-parser-lexical-edge-p")
    public static SubLObject get_psp_edge_head_lexeme(final SubLObject edge) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            final SubLObject head_dtr = psp_phrasal_edge_head_dtr(edge);
            if (NIL != head_dtr) {
                return get_psp_edge_head_lexeme(head_dtr);
            }
            Errors.warn($str146$Headless_edge___S, edge);
            return NIL;
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                return edge;
            }
            Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
            return NIL;
        }
    }

    public static final SubLObject set_psp_edge_weight_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_weight(edge, value);
            return psp_phrasal_edge_weight(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_weight(edge, value);
                return psp_lexical_edge_weight(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_weight(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_weight(edge, value);
            return psp_phrasal_edge_weight(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_weight(edge, value);
            return psp_lexical_edge_weight(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject set_psp_edge_string_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_string(edge, value);
            return psp_phrasal_edge_string(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_string(edge, value);
                return psp_lexical_edge_string(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_string(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_string(edge, value);
            return psp_phrasal_edge_string(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_string(edge, value);
            return psp_lexical_edge_string(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject set_psp_edge_span_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_span(edge, value);
            return psp_phrasal_edge_span(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_span(edge, value);
                return psp_lexical_edge_span(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_span(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_span(edge, value);
            return psp_phrasal_edge_span(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_span(edge, value);
            return psp_lexical_edge_span(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject set_psp_edge_category_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_category(edge, value);
            return psp_phrasal_edge_category(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_category(edge, value);
                return psp_lexical_edge_category(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_category(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_category(edge, value);
            return psp_phrasal_edge_category(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_category(edge, value);
            return psp_lexical_edge_category(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject set_psp_edge_pos_pred_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_pos_pred(edge, value);
            return psp_phrasal_edge_pos_pred(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_pos_pred(edge, value);
                return psp_lexical_edge_pos_pred(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_pos_pred(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_pos_pred(edge, value);
            return psp_phrasal_edge_pos_pred(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_pos_pred(edge, value);
            return psp_lexical_edge_pos_pred(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject set_psp_edge_cycls_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_cycls(edge, value);
            return psp_phrasal_edge_cycls(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_cycls(edge, value);
                return psp_lexical_edge_cycls(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_cycls(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_cycls(edge, value);
            return psp_phrasal_edge_cycls(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_cycls(edge, value);
            return psp_lexical_edge_cycls(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject set_psp_edge_rule_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_rule(edge, value);
            return psp_phrasal_edge_rule(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject set_psp_edge_rule(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_rule(edge, value);
            return psp_phrasal_edge_rule(edge);
        }
        return NIL;
    }

    public static final SubLObject set_psp_edge_new_dtr_constraints_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_new_dtr_constraints(edge, value);
            return psp_phrasal_edge_new_dtr_constraints(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject set_psp_edge_new_dtr_constraints(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_new_dtr_constraints(edge, value);
            return psp_phrasal_edge_new_dtr_constraints(edge);
        }
        return NIL;
    }

    public static final SubLObject set_psp_edge_head_dtr_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_head_dtr(edge, value);
            return psp_phrasal_edge_head_dtr(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject set_psp_edge_head_dtr(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_head_dtr(edge, value);
            return psp_phrasal_edge_head_dtr(edge);
        }
        return NIL;
    }

    public static final SubLObject set_psp_edge_non_head_dtrs_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_non_head_dtrs(edge, value);
            return psp_phrasal_edge_non_head_dtrs(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject set_psp_edge_non_head_dtrs(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_non_head_dtrs(edge, value);
            return psp_phrasal_edge_non_head_dtrs(edge);
        }
        return NIL;
    }

    public static final SubLObject set_psp_edge_wu_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_wu(edge, value);
            return psp_lexical_edge_wu(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject set_psp_edge_wu(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_wu(edge, value);
            return psp_lexical_edge_wu(edge);
        }
        return NIL;
    }

    public static final SubLObject set_psp_edge_frame_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_frame(edge, value);
            return psp_lexical_edge_frame(edge);
        } else {
            return NIL;
        }
    }

    public static SubLObject set_psp_edge_frame(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_frame(edge, value);
            return psp_lexical_edge_frame(edge);
        }
        return NIL;
    }

    public static final SubLObject set_psp_edge_id_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_id(edge, value);
            return psp_phrasal_edge_id(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_id(edge, value);
                return psp_lexical_edge_id(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_id(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_id(edge, value);
            return psp_phrasal_edge_id(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_id(edge, value);
            return psp_lexical_edge_id(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    public static final SubLObject set_psp_edge_chart_alt(SubLObject edge, SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_chart(edge, value);
            return psp_phrasal_edge_chart(edge);
        } else {
            if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
                _csetf_psp_lexical_edge_chart(edge, value);
                return psp_lexical_edge_chart(edge);
            } else {
                Errors.error($str_alt133$_S_is_not_a_PSP_edge_, edge);
            }
        }
        return NIL;
    }

    public static SubLObject set_psp_edge_chart(final SubLObject edge, final SubLObject value) {
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            _csetf_psp_phrasal_edge_chart(edge, value);
            return psp_phrasal_edge_chart(edge);
        }
        if (NIL != phrase_structure_parser_lexical_edge_p(edge)) {
            _csetf_psp_lexical_edge_chart(edge, value);
            return psp_lexical_edge_chart(edge);
        }
        Errors.error($str145$_S_is_not_a_PSP_edge_, edge);
        return NIL;
    }

    /**
     * Execute BODY with SPAN-VAR bound iteratively to each span in CHART that has edges.
     */
    @LispMethod(comment = "Execute BODY with SPAN-VAR bound iteratively to each span in CHART that has edges.")
    public static final SubLObject psp_do_spans_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt135);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject span_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt135);
                    span_var = current.first();
                    current = current.rest();
                    {
                        SubLObject done = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt135);
                        current = current.rest();
                        {
                            SubLObject chart = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt135);
                            current = current.rest();
                            if (NIL == current) {
                                current = temp;
                                {
                                    SubLObject body = current;
                                    SubLObject chart_to_use = $sym136$CHART_TO_USE;
                                    return list(CLET, list(list(chart_to_use, listS(FIRST_OF, chart, $list_alt139))), listS(CHECK_TYPE, chart_to_use, $list_alt141), listS(DO_DICTIONARY_KEYS, list(span_var, list(PSP_CHART_SPANS_TO_EDGES, chart_to_use), $DONE, done), append(body, NIL)));
                                }
                            } else {
                                cdestructuring_bind_error(datum, $list_alt135);
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Execute BODY with SPAN-VAR bound iteratively to each span in CHART that has edges.
     */
    @LispMethod(comment = "Execute BODY with SPAN-VAR bound iteratively to each span in CHART that has edges.")
    public static SubLObject psp_do_spans(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list147);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject span_var = NIL;
        destructuring_bind_must_consp(current, datum, $list147);
        span_var = current.first();
        current = current.rest();
        final SubLObject done = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list147);
        current = current.rest();
        final SubLObject chart = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list147);
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            final SubLObject chart_to_use = $sym148$CHART_TO_USE;
            return list(CLET, list(list(chart_to_use, listS(FIRST_OF, chart, $list151))), listS(CHECK_TYPE, chart_to_use, $list153), listS(DO_DICTIONARY_KEYS, list(span_var, list(PSP_CHART_SPANS_TO_EDGES, chart_to_use), $DONE, done), append(body, NIL)));
        }
        cdestructuring_bind_error(datum, $list147);
        return NIL;
    }

    /**
     * Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching
     * CATEGORY, SPAN, and COMPLETE-ONLY?.
     */
    @LispMethod(comment = "Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\r\nCATEGORY, SPAN, and COMPLETE-ONLY?.\nExecute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\nCATEGORY, SPAN, and COMPLETE-ONLY?.")
    public static final SubLObject psp_do_edges_for_span_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt146);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject edge_var = NIL;
                    SubLObject chart = NIL;
                    SubLObject category = NIL;
                    SubLObject span = NIL;
                    SubLObject complete_onlyP = NIL;
                    SubLObject done = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    edge_var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    chart = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    category = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    span = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    complete_onlyP = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    done = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return list(CSOME, list(edge_var, list(PSP_EDGES_WITH_SPAN, span, chart), done), listS(PWHEN, list($sym150$PSP_DO_EDGE_OK_, edge_var, category, complete_onlyP), append(body, NIL)));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt146);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching
     * CATEGORY, SPAN, and COMPLETE-ONLY?.
     */
    @LispMethod(comment = "Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\r\nCATEGORY, SPAN, and COMPLETE-ONLY?.\nExecute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\nCATEGORY, SPAN, and COMPLETE-ONLY?.")
    public static SubLObject psp_do_edges_for_span(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject edge_var = NIL;
        SubLObject chart = NIL;
        SubLObject category = NIL;
        SubLObject span = NIL;
        SubLObject complete_onlyP = NIL;
        SubLObject done = NIL;
        destructuring_bind_must_consp(current, datum, $list158);
        edge_var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        chart = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        category = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        span = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        complete_onlyP = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        done = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return list(CSOME, list(edge_var, list(PSP_EDGES_WITH_SPAN, span, chart), done), listS(PWHEN, list($sym162$PSP_DO_EDGE_OK_, edge_var, category, complete_onlyP), append(body, NIL)));
        }
        cdestructuring_bind_error(datum, $list158);
        return NIL;
    }

    /**
     * Execute BODY with EDGE-VAR bound iteratively to each lexical edge in CHART matching
     * CATEGORY, SPAN, and COMPLETE-ONLY?.
     */
    @LispMethod(comment = "Execute BODY with EDGE-VAR bound iteratively to each lexical edge in CHART matching\r\nCATEGORY, SPAN, and COMPLETE-ONLY?.\nExecute BODY with EDGE-VAR bound iteratively to each lexical edge in CHART matching\nCATEGORY, SPAN, and COMPLETE-ONLY?.")
    public static final SubLObject psp_do_lexical_edges_for_span_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt146);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject edge_var = NIL;
                    SubLObject chart = NIL;
                    SubLObject category = NIL;
                    SubLObject span = NIL;
                    SubLObject complete_onlyP = NIL;
                    SubLObject done = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    edge_var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    chart = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    category = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    span = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    complete_onlyP = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt146);
                    done = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return list(CSOME, list(edge_var, list(PSP_LEXICAL_EDGES_WITH_SPAN, span, chart), done), listS(PWHEN, list($sym150$PSP_DO_EDGE_OK_, edge_var, category, complete_onlyP), append(body, NIL)));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt146);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Execute BODY with EDGE-VAR bound iteratively to each lexical edge in CHART matching
     * CATEGORY, SPAN, and COMPLETE-ONLY?.
     */
    @LispMethod(comment = "Execute BODY with EDGE-VAR bound iteratively to each lexical edge in CHART matching\r\nCATEGORY, SPAN, and COMPLETE-ONLY?.\nExecute BODY with EDGE-VAR bound iteratively to each lexical edge in CHART matching\nCATEGORY, SPAN, and COMPLETE-ONLY?.")
    public static SubLObject psp_do_lexical_edges_for_span(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject edge_var = NIL;
        SubLObject chart = NIL;
        SubLObject category = NIL;
        SubLObject span = NIL;
        SubLObject complete_onlyP = NIL;
        SubLObject done = NIL;
        destructuring_bind_must_consp(current, datum, $list158);
        edge_var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        chart = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        category = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        span = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        complete_onlyP = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list158);
        done = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return list(CSOME, list(edge_var, list(PSP_LEXICAL_EDGES_WITH_SPAN, span, chart), done), listS(PWHEN, list($sym162$PSP_DO_EDGE_OK_, edge_var, category, complete_onlyP), append(body, NIL)));
        }
        cdestructuring_bind_error(datum, $list158);
        return NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE meet the constraints CATEGORY and COMPLETE-ONLY?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE meet the constraints CATEGORY and COMPLETE-ONLY?")
    public static final SubLObject psp_do_edge_okP_alt(SubLObject edge, SubLObject category, SubLObject complete_onlyP) {
        return makeBoolean(((NIL == complete_onlyP) || (NIL == psp_incomplete_edgeP(edge))) && ((category == $ANY) || (NIL != psp_rules.psp_valid_constituent_typeP(get_psp_edge_category(edge), category))));
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE meet the constraints CATEGORY and COMPLETE-ONLY?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE meet the constraints CATEGORY and COMPLETE-ONLY?")
    public static SubLObject psp_do_edge_okP(final SubLObject edge, final SubLObject category, final SubLObject complete_onlyP) {
        return makeBoolean(((NIL == complete_onlyP) || (NIL == psp_incomplete_edgeP(edge))) && ((category == $ANY) || (NIL != psp_rules.psp_valid_constituent_typeP(get_psp_edge_category(edge), category))));
    }

    /**
     * Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching
     * CATEGORY, SPAN, and COMPLETE-ONLY?.
     */
    @LispMethod(comment = "Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\r\nCATEGORY, SPAN, and COMPLETE-ONLY?.\nExecute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\nCATEGORY, SPAN, and COMPLETE-ONLY?.")
    public static final SubLObject psp_do_edges_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt154);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject edge_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt154);
                    edge_var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt154);
                    {
                        SubLObject temp_1 = current.rest();
                        current = current.first();
                        {
                            SubLObject allow_other_keys_p = NIL;
                            SubLObject rest = current;
                            SubLObject bad = NIL;
                            SubLObject current_2 = NIL;
                            for (; NIL != rest;) {
                                destructuring_bind_must_consp(rest, datum, $list_alt154);
                                current_2 = rest.first();
                                rest = rest.rest();
                                destructuring_bind_must_consp(rest, datum, $list_alt154);
                                if (NIL == member(current_2, $list_alt155, UNPROVIDED, UNPROVIDED)) {
                                    bad = T;
                                }
                                if (current_2 == $ALLOW_OTHER_KEYS) {
                                    allow_other_keys_p = rest.first();
                                }
                                rest = rest.rest();
                            }
                            if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                                cdestructuring_bind_error(datum, $list_alt154);
                            }
                            {
                                SubLObject chart_tail = property_list_member($CHART, current);
                                SubLObject chart = (NIL != chart_tail) ? ((SubLObject) (cadr(chart_tail))) : NIL;
                                SubLObject category_tail = property_list_member($CATEGORY, current);
                                SubLObject category = (NIL != category_tail) ? ((SubLObject) (cadr(category_tail))) : $ANY;
                                SubLObject span_tail = property_list_member($SPAN, current);
                                SubLObject span = (NIL != span_tail) ? ((SubLObject) (cadr(span_tail))) : $ANY;
                                SubLObject complete_onlyP_tail = property_list_member($kw157$COMPLETE_ONLY_, current);
                                SubLObject complete_onlyP = (NIL != complete_onlyP_tail) ? ((SubLObject) (cadr(complete_onlyP_tail))) : T;
                                SubLObject type_tail = property_list_member($TYPE, current);
                                SubLObject type = (NIL != type_tail) ? ((SubLObject) (cadr(type_tail))) : $ANY;
                                SubLObject done_tail = property_list_member($DONE, current);
                                SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
                                current = temp_1;
                                if (NIL == current) {
                                    current = temp;
                                    {
                                        SubLObject body = current;
                                        SubLObject span_to_do = $sym159$SPAN_TO_DO;
                                        SubLObject chart_to_use = $sym160$CHART_TO_USE;
                                        return list(CLET, list(list(chart_to_use, listS(FIRST_OF, chart, $list_alt139))), listS(CHECK_TYPE, chart_to_use, $list_alt141), list(PCOND, list(listS(EQ, span, $list_alt162), list(PCASE, type, list($ANY, list(PSP_DO_SPANS, list(span_to_do, done, chart_to_use), listS(PSP_DO_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span_to_do, complete_onlyP, done), append(body, NIL)))), list($LEXICAL, list(PSP_DO_SPANS, list(span_to_do, done, chart_to_use), listS(PSP_DO_LEXICAL_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span_to_do, complete_onlyP, done), append(body, NIL)))))), list(list(INTERVAL_SPAN_P, span), list(PCASE, type, list($ANY, listS(PSP_DO_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span, complete_onlyP, done), append(body, NIL))), list($LEXICAL, listS(PSP_DO_LEXICAL_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span, complete_onlyP, done), append(body, NIL)))))));
                                    }
                                } else {
                                    cdestructuring_bind_error(datum, $list_alt154);
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
     * Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching
     * CATEGORY, SPAN, and COMPLETE-ONLY?.
     */
    @LispMethod(comment = "Execute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\r\nCATEGORY, SPAN, and COMPLETE-ONLY?.\nExecute BODY with EDGE-VAR bound iteratively to each edge in CHART matching\nCATEGORY, SPAN, and COMPLETE-ONLY?.")
    public static SubLObject psp_do_edges(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list166);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject edge_var = NIL;
        destructuring_bind_must_consp(current, datum, $list166);
        edge_var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list166);
        final SubLObject temp_$1 = current.rest();
        current = current.first();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$2 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list166);
            current_$2 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list166);
            if (NIL == member(current_$2, $list167, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$2 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list166);
        }
        final SubLObject chart_tail = property_list_member($CHART, current);
        final SubLObject chart = (NIL != chart_tail) ? cadr(chart_tail) : NIL;
        final SubLObject category_tail = property_list_member($CATEGORY, current);
        final SubLObject category = (NIL != category_tail) ? cadr(category_tail) : $ANY;
        final SubLObject span_tail = property_list_member($SPAN, current);
        final SubLObject span = (NIL != span_tail) ? cadr(span_tail) : $ANY;
        final SubLObject complete_onlyP_tail = property_list_member($kw169$COMPLETE_ONLY_, current);
        final SubLObject complete_onlyP = (NIL != complete_onlyP_tail) ? cadr(complete_onlyP_tail) : T;
        final SubLObject type_tail = property_list_member($TYPE, current);
        final SubLObject type = (NIL != type_tail) ? cadr(type_tail) : $ANY;
        final SubLObject done_tail = property_list_member($DONE, current);
        final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
        current = temp_$1;
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            final SubLObject span_to_do = $sym171$SPAN_TO_DO;
            final SubLObject chart_to_use = $sym172$CHART_TO_USE;
            return list(CLET, list(list(chart_to_use, listS(FIRST_OF, chart, $list151))), listS(CHECK_TYPE, chart_to_use, $list153), list(PCOND, list(listS(EQ, span, $list174), list(PCASE, type, list($ANY, list(PSP_DO_SPANS, list(span_to_do, done, chart_to_use), listS(PSP_DO_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span_to_do, complete_onlyP, done), append(body, NIL)))), list($LEXICAL, list(PSP_DO_SPANS, list(span_to_do, done, chart_to_use), listS(PSP_DO_LEXICAL_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span_to_do, complete_onlyP, done), append(body, NIL)))))), list(list(INTERVAL_SPAN_P, span), list(PCASE, type, list($ANY, listS(PSP_DO_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span, complete_onlyP, done), append(body, NIL))), list($LEXICAL, listS(PSP_DO_LEXICAL_EDGES_FOR_SPAN, list(edge_var, chart_to_use, category, span, complete_onlyP, done), append(body, NIL)))))));
        }
        cdestructuring_bind_error(datum, $list166);
        return NIL;
    }

    public static final SubLObject psp_weight_p_alt(SubLObject obj) {
        return makeBoolean((NIL != psp_unknown_weight_p(obj)) || (((NIL != possibly_psp_known_weight_p(obj)) && obj.numLE(parsing_vars.$psp_max_weight$.getGlobalValue())) && obj.numGE(parsing_vars.$psp_min_weight$.getGlobalValue())));
    }

    public static SubLObject psp_weight_p(final SubLObject obj) {
        return makeBoolean((NIL != psp_unknown_weight_p(obj)) || (((NIL != possibly_psp_known_weight_p(obj)) && obj.numLE(parsing_vars.$psp_max_weight$.getGlobalValue())) && obj.numGE(parsing_vars.$psp_min_weight$.getGlobalValue())));
    }

    public static final SubLObject psp_unknown_weight_p_alt(SubLObject obj) {
        return eq(obj, $psp_unknown_weight$.getGlobalValue());
    }

    public static SubLObject psp_unknown_weight_p(final SubLObject obj) {
        return eq(obj, $psp_unknown_weight$.getGlobalValue());
    }

    public static final SubLObject possibly_psp_known_weight_p_alt(SubLObject obj) {
        {
            SubLObject pcase_var = parsing_vars.$psp_weight_type$.getGlobalValue();
            if (pcase_var.eql($INTEGER)) {
                return integerp(obj);
            } else {
                if (pcase_var.eql($FLOAT)) {
                    return floatp(obj);
                } else {
                    return NIL;
                }
            }
        }
    }

    public static SubLObject possibly_psp_known_weight_p(final SubLObject obj) {
        final SubLObject pcase_var = parsing_vars.$psp_weight_type$.getGlobalValue();
        if (pcase_var.eql($INTEGER)) {
            return integerp(obj);
        }
        if (pcase_var.eql($FLOAT)) {
            return floatp(obj);
        }
        return NIL;
    }

    /**
     *
     *
     * @param WEIGHT1;
    pph-weight-p
     * 		
     * @param WEIGHT2;
    pph-weight-p
     * 		
     * @param MARGIN-OF-ERROR;
     * 		percentage expressed as float between 0 and 1
     * @return BOOLEAN; Does WEIGHT1 exceed WEIGHT2 by at least MARGIN-OF-ERROR?
     */
    @LispMethod(comment = "@param WEIGHT1;\npph-weight-p\r\n\t\t\r\n@param WEIGHT2;\npph-weight-p\r\n\t\t\r\n@param MARGIN-OF-ERROR;\r\n\t\tpercentage expressed as float between 0 and 1\r\n@return BOOLEAN; Does WEIGHT1 exceed WEIGHT2 by at least MARGIN-OF-ERROR?")
    public static final SubLObject psp_weight_G_alt(SubLObject weight1, SubLObject weight2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        SubLTrampolineFile.checkType(weight1, PSP_WEIGHT_P);
        SubLTrampolineFile.checkType(weight2, PSP_WEIGHT_P);
        if ((NIL != possibly_psp_known_weight_p(weight1)) && (NIL != possibly_psp_known_weight_p(weight2))) {
            return numG(weight1, multiply(weight2, number_utilities.f_1X(margin_of_error)));
        } else {
            return NIL;
        }
    }

    /**
     *
     *
     * @param WEIGHT1;
    pph-weight-p
     * 		
     * @param WEIGHT2;
    pph-weight-p
     * 		
     * @param MARGIN-OF-ERROR;
     * 		percentage expressed as float between 0 and 1
     * @return BOOLEAN; Does WEIGHT1 exceed WEIGHT2 by at least MARGIN-OF-ERROR?
     */
    @LispMethod(comment = "@param WEIGHT1;\npph-weight-p\r\n\t\t\r\n@param WEIGHT2;\npph-weight-p\r\n\t\t\r\n@param MARGIN-OF-ERROR;\r\n\t\tpercentage expressed as float between 0 and 1\r\n@return BOOLEAN; Does WEIGHT1 exceed WEIGHT2 by at least MARGIN-OF-ERROR?")
    public static SubLObject psp_weight_G(final SubLObject weight1, final SubLObject weight2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        assert NIL != psp_weight_p(weight1) : "! psp_chart.psp_weight_p(weight1) " + ("psp_chart.psp_weight_p(weight1) " + "CommonSymbols.NIL != psp_chart.psp_weight_p(weight1) ") + weight1;
        assert NIL != psp_weight_p(weight2) : "! psp_chart.psp_weight_p(weight2) " + ("psp_chart.psp_weight_p(weight2) " + "CommonSymbols.NIL != psp_chart.psp_weight_p(weight2) ") + weight2;
        if ((NIL != possibly_psp_known_weight_p(weight1)) && (NIL != possibly_psp_known_weight_p(weight2))) {
            return numG(weight1, multiply(weight2, number_utilities.f_1X(margin_of_error)));
        }
        return NIL;
    }

    /**
     *
     *
     * @param WEIGHT1;
    pph-weight-p
     * 		
     * @param WEIGHT2;
    pph-weight-p
     * 		
     * @param MARGIN-OF-ERROR;
     * 		percentage expressed as float between 0 and 1
     * @return BOOLEAN; Does WEIGHT2 exceed WEIGHT1 by at least MARGIN-OF-ERROR?
     */
    @LispMethod(comment = "@param WEIGHT1;\npph-weight-p\r\n\t\t\r\n@param WEIGHT2;\npph-weight-p\r\n\t\t\r\n@param MARGIN-OF-ERROR;\r\n\t\tpercentage expressed as float between 0 and 1\r\n@return BOOLEAN; Does WEIGHT2 exceed WEIGHT1 by at least MARGIN-OF-ERROR?")
    public static final SubLObject psp_weight_L_alt(SubLObject weight1, SubLObject weight2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        SubLTrampolineFile.checkType(weight1, PSP_WEIGHT_P);
        SubLTrampolineFile.checkType(weight2, PSP_WEIGHT_P);
        return numL(weight1, multiply(weight2, number_utilities.f_1X(margin_of_error)));
    }

    /**
     *
     *
     * @param WEIGHT1;
    pph-weight-p
     * 		
     * @param WEIGHT2;
    pph-weight-p
     * 		
     * @param MARGIN-OF-ERROR;
     * 		percentage expressed as float between 0 and 1
     * @return BOOLEAN; Does WEIGHT2 exceed WEIGHT1 by at least MARGIN-OF-ERROR?
     */
    @LispMethod(comment = "@param WEIGHT1;\npph-weight-p\r\n\t\t\r\n@param WEIGHT2;\npph-weight-p\r\n\t\t\r\n@param MARGIN-OF-ERROR;\r\n\t\tpercentage expressed as float between 0 and 1\r\n@return BOOLEAN; Does WEIGHT2 exceed WEIGHT1 by at least MARGIN-OF-ERROR?")
    public static SubLObject psp_weight_L(final SubLObject weight1, final SubLObject weight2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        assert NIL != psp_weight_p(weight1) : "! psp_chart.psp_weight_p(weight1) " + ("psp_chart.psp_weight_p(weight1) " + "CommonSymbols.NIL != psp_chart.psp_weight_p(weight1) ") + weight1;
        assert NIL != psp_weight_p(weight2) : "! psp_chart.psp_weight_p(weight2) " + ("psp_chart.psp_weight_p(weight2) " + "CommonSymbols.NIL != psp_chart.psp_weight_p(weight2) ") + weight2;
        return numL(weight1, multiply(weight2, number_utilities.f_1X(margin_of_error)));
    }

    /**
     * Compose all the known weights in WEIGHTS together to produce a new weight
     */
    @LispMethod(comment = "Compose all the known weights in WEIGHTS together to produce a new weight")
    public static final SubLObject psp_weight_compose_alt(SubLObject weights) {
        {
            SubLObject known_weightP = NIL;
            SubLObject current_weight = parsing_vars.$psp_max_weight$.getGlobalValue();
            SubLObject cdolist_list_var = weights;
            SubLObject weight = NIL;
            for (weight = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , weight = cdolist_list_var.first()) {
                if (NIL == psp_unknown_weight_p(weight)) {
                    known_weightP = T;
                    current_weight = psp_weight_compose_two(current_weight, weight);
                }
            }
            if (NIL != known_weightP) {
                return current_weight;
            } else {
                return $psp_unknown_weight$.getGlobalValue();
            }
        }
    }

    /**
     * Compose all the known weights in WEIGHTS together to produce a new weight
     */
    @LispMethod(comment = "Compose all the known weights in WEIGHTS together to produce a new weight")
    public static SubLObject psp_weight_compose(final SubLObject weights) {
        SubLObject known_weightP = NIL;
        SubLObject current_weight = parsing_vars.$psp_max_weight$.getGlobalValue();
        SubLObject cdolist_list_var = weights;
        SubLObject weight = NIL;
        weight = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == psp_unknown_weight_p(weight)) {
                known_weightP = T;
                current_weight = psp_weight_compose_two(current_weight, weight);
            }
            cdolist_list_var = cdolist_list_var.rest();
            weight = cdolist_list_var.first();
        } 
        if (NIL != known_weightP) {
            return current_weight;
        }
        return $psp_unknown_weight$.getGlobalValue();
    }

    public static final SubLObject psp_weight_compose_two_alt(SubLObject weight1, SubLObject weight2) {
        SubLTrampolineFile.checkType(weight1, PSP_WEIGHT_P);
        SubLTrampolineFile.checkType(weight2, PSP_WEIGHT_P);
        if (NIL != psp_unknown_weight_p(weight1)) {
            return weight2;
        } else {
            if (NIL != psp_unknown_weight_p(weight2)) {
                return weight1;
            } else {
                {
                    SubLObject weight_product = multiply(weight1, weight2);
                    SubLObject pcase_var = parsing_vars.$psp_weight_type$.getGlobalValue();
                    if (pcase_var.eql($INTEGER)) {
                        return integerDivide(weight_product, parsing_vars.$psp_max_weight$.getGlobalValue());
                    } else {
                        if (pcase_var.eql($FLOAT)) {
                            return divide(weight_product, parsing_vars.$psp_max_weight$.getGlobalValue());
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject psp_weight_compose_two(final SubLObject weight1, final SubLObject weight2) {
        assert NIL != psp_weight_p(weight1) : "! psp_chart.psp_weight_p(weight1) " + ("psp_chart.psp_weight_p(weight1) " + "CommonSymbols.NIL != psp_chart.psp_weight_p(weight1) ") + weight1;
        assert NIL != psp_weight_p(weight2) : "! psp_chart.psp_weight_p(weight2) " + ("psp_chart.psp_weight_p(weight2) " + "CommonSymbols.NIL != psp_chart.psp_weight_p(weight2) ") + weight2;
        if (NIL != psp_unknown_weight_p(weight1)) {
            return weight2;
        }
        if (NIL != psp_unknown_weight_p(weight2)) {
            return weight1;
        }
        final SubLObject weight_product = multiply(weight1, weight2);
        final SubLObject pcase_var = parsing_vars.$psp_weight_type$.getGlobalValue();
        if (pcase_var.eql($INTEGER)) {
            return integerDivide(weight_product, parsing_vars.$psp_max_weight$.getGlobalValue());
        }
        if (pcase_var.eql($FLOAT)) {
            return divide(weight_product, parsing_vars.$psp_max_weight$.getGlobalValue());
        }
        return NIL;
    }

    public static final SubLObject psp_edge_weight_unknown_p_alt(SubLObject edge) {
        return psp_unknown_weight_p(get_psp_edge_weight(edge));
    }

    public static SubLObject psp_edge_weight_unknown_p(final SubLObject edge) {
        return psp_unknown_weight_p(get_psp_edge_weight(edge));
    }

    /**
     *
     *
     * @return PSP-WEIGHT-P for an edge with HEAD-DTR, NON-HEAD-DTRS, and RULE
     */
    @LispMethod(comment = "@return PSP-WEIGHT-P for an edge with HEAD-DTR, NON-HEAD-DTRS, and RULE")
    public static final SubLObject psp_edge_weight_from_dtrs_alt(SubLObject head_dtr, SubLObject non_head_dtrs, SubLObject rule) {
        {
            SubLObject weight = psp_rules.psp_rule_weight(rule);
            if (NIL != head_dtr) {
                weight = psp_weight_compose_two(weight, get_psp_edge_weight(head_dtr));
            }
            {
                SubLObject cdolist_list_var = non_head_dtrs;
                SubLObject non_head_dtr = NIL;
                for (non_head_dtr = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , non_head_dtr = cdolist_list_var.first()) {
                    weight = psp_weight_compose_two(weight, get_psp_edge_weight(non_head_dtr));
                }
            }
            return weight;
        }
    }

    /**
     *
     *
     * @return PSP-WEIGHT-P for an edge with HEAD-DTR, NON-HEAD-DTRS, and RULE
     */
    @LispMethod(comment = "@return PSP-WEIGHT-P for an edge with HEAD-DTR, NON-HEAD-DTRS, and RULE")
    public static SubLObject psp_edge_weight_from_dtrs(final SubLObject head_dtr, final SubLObject non_head_dtrs, final SubLObject rule) {
        SubLObject weight = psp_rules.psp_rule_weight(rule);
        if (NIL != head_dtr) {
            weight = psp_weight_compose_two(weight, get_psp_edge_weight(head_dtr));
        }
        SubLObject cdolist_list_var = non_head_dtrs;
        SubLObject non_head_dtr = NIL;
        non_head_dtr = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            weight = psp_weight_compose_two(weight, get_psp_edge_weight(non_head_dtr));
            cdolist_list_var = cdolist_list_var.rest();
            non_head_dtr = cdolist_list_var.first();
        } 
        return weight;
    }

    public static final SubLObject clear_psp_np_gap_semx_alt() {
        {
            SubLObject cs = $psp_np_gap_semx_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_np_gap_semx() {
        final SubLObject cs = $psp_np_gap_semx_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_np_gap_semx_alt() {
        return memoization_state.caching_state_remove_function_results_with_args($psp_np_gap_semx_caching_state$.getGlobalValue(), list(EMPTY_SUBL_OBJECT_ARRAY), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_np_gap_semx() {
        return memoization_state.caching_state_remove_function_results_with_args($psp_np_gap_semx_caching_state$.getGlobalValue(), list(EMPTY_SUBL_OBJECT_ARRAY), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject psp_np_gap_semx_internal_alt() {
        {
            SubLObject cycl = psp_np_gap_referent();
            SubLObject cycls = psp_new_cycls(cycl, UNPROVIDED);
            return cycls;
        }
    }

    public static SubLObject psp_np_gap_semx_internal() {
        final SubLObject cycl = psp_np_gap_referent();
        final SubLObject cycls = psp_new_cycls(cycl, UNPROVIDED);
        return cycls;
    }

    public static final SubLObject psp_np_gap_semx_alt() {
        {
            SubLObject caching_state = $psp_np_gap_semx_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(PSP_NP_GAP_SEMX, $psp_np_gap_semx_caching_state$, NIL, EQL, ZERO_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject results = memoization_state.caching_state_get_zero_arg_results(caching_state, UNPROVIDED);
                if (results == $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(psp_np_gap_semx_internal()));
                    memoization_state.caching_state_set_zero_arg_results(caching_state, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject psp_np_gap_semx() {
        SubLObject caching_state = $psp_np_gap_semx_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(PSP_NP_GAP_SEMX, $psp_np_gap_semx_caching_state$, NIL, EQL, ZERO_INTEGER, ZERO_INTEGER);
        }
        SubLObject results = memoization_state.caching_state_get_zero_arg_results(caching_state, UNPROVIDED);
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(psp_np_gap_semx_internal()));
            memoization_state.caching_state_set_zero_arg_results(caching_state, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    /**
     * What an NP gap refers to - an indexical term
     */
    @LispMethod(comment = "What an NP gap refers to - an indexical term")
    public static final SubLObject psp_np_gap_referent_alt() {
        return $$TheGappedTerm;
    }

    /**
     * What an NP gap refers to - an indexical term
     */
    @LispMethod(comment = "What an NP gap refers to - an indexical term")
    public static SubLObject psp_np_gap_referent() {
        return $$TheGappedTerm;
    }

    /**
     *
     *
     * @return PHRASE-STRUCTURE-PARSER-EDGE-P; a gap edge with category CATEGORY at INDEX
     */
    @LispMethod(comment = "@return PHRASE-STRUCTURE-PARSER-EDGE-P; a gap edge with category CATEGORY at INDEX")
    public static final SubLObject new_psp_gap_edge_alt(SubLObject index, SubLObject category) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject phrasalP = numG(lexicon_utilities.bar_level(category, UNPROVIDED), ONE_INTEGER);
                SubLObject constructor = (NIL != phrasalP) ? ((SubLObject) (NEW_PSP_PHRASAL_EDGE)) : NEW_PSP_LEXICAL_EDGE;
                SubLObject new_edge = funcall(constructor, new SubLObject[]{ parsing_vars.$psp_chart$.getDynamicValue(thread), interval_span.get_interval_span(index, index), $str_alt182$, category, NIL, $NEW, $psp_gap_edge_weight$.getGlobalValue() });
                if ((category == $$Preposition) && (NIL != psp_pp_gap_string())) {
                    set_psp_edge_wu(new_edge, lexicon_accessors.words_of_stringXspeech_part(psp_pp_gap_string(), $$Preposition, UNPROVIDED, UNPROVIDED, UNPROVIDED).first());
                } else {
                    if (NIL != lexicon_accessors.npP(category)) {
                        set_psp_edge_cycls(new_edge, psp_np_gap_semx());
                    }
                }
                return new_edge;
            }
        }
    }

    /**
     *
     *
     * @return PHRASE-STRUCTURE-PARSER-EDGE-P; a gap edge with category CATEGORY at INDEX
     */
    @LispMethod(comment = "@return PHRASE-STRUCTURE-PARSER-EDGE-P; a gap edge with category CATEGORY at INDEX")
    public static SubLObject new_psp_gap_edge(final SubLObject index, final SubLObject category) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject phrasalP = numG(lexicon_utilities.bar_level(category, UNPROVIDED), ONE_INTEGER);
        final SubLObject constructor = (NIL != phrasalP) ? NEW_PSP_PHRASAL_EDGE : NEW_PSP_LEXICAL_EDGE;
        final SubLObject new_edge = funcall(constructor, new SubLObject[]{ parsing_vars.$psp_chart$.getDynamicValue(thread), interval_span.get_interval_span(index, index), $str193$, category, NIL, $NEW, $psp_gap_edge_weight$.getGlobalValue() });
        if (category.eql($$Preposition) && (NIL != psp_pp_gap_string())) {
            set_psp_edge_wu(new_edge, lexicon_accessors.words_of_stringXspeech_part(psp_pp_gap_string(), $$Preposition, UNPROVIDED, UNPROVIDED, UNPROVIDED).first());
        } else
            if (NIL != lexicon_accessors.npP(category)) {
                set_psp_edge_cycls(new_edge, psp_np_gap_semx());
            }

        return new_edge;
    }

    public static final SubLObject psp_gap_edge_p_alt(SubLObject edge) {
        return eql(psp_edge_start_index(edge), psp_edge_end_index(edge));
    }

    public static SubLObject psp_gap_edge_p(final SubLObject edge) {
        return eql(psp_edge_start_index(edge), psp_edge_end_index(edge));
    }

    public static final SubLObject psp_np_gap_edge_p_alt(SubLObject edge) {
        return makeBoolean((NIL != psp_gap_edge_p(edge)) && (NIL != lexicon_accessors.npP(get_psp_edge_category(edge))));
    }

    public static SubLObject psp_np_gap_edge_p(final SubLObject edge) {
        return makeBoolean((NIL != psp_gap_edge_p(edge)) && (NIL != lexicon_accessors.npP(get_psp_edge_category(edge))));
    }

    public static final SubLObject psp_preposition_gap_edge_p_alt(SubLObject edge) {
        return makeBoolean((NIL != psp_gap_edge_p(edge)) && get_psp_edge_category(edge).eql($$Preposition));
    }

    public static SubLObject psp_preposition_gap_edge_p(final SubLObject edge) {
        return makeBoolean((NIL != psp_gap_edge_p(edge)) && get_psp_edge_category(edge).eql($$Preposition));
    }

    public static final SubLObject psp_pp_gap_edge_p_alt(SubLObject edge) {
        return makeBoolean((NIL != psp_gap_edge_p(edge)) && (NIL != lexicon_accessors.ppP(get_psp_edge_category(edge))));
    }

    public static SubLObject psp_pp_gap_edge_p(final SubLObject edge) {
        return makeBoolean((NIL != psp_gap_edge_p(edge)) && (NIL != lexicon_accessors.ppP(get_psp_edge_category(edge))));
    }

    public static final SubLObject psp_gap_constraint_p_alt(SubLObject obj) {
        return makeBoolean((NIL != member(obj, $list_alt185, UNPROVIDED, UNPROVIDED)) || (NIL != psp_pp_gap_constraint_p(obj)));
    }

    public static SubLObject psp_gap_constraint_p(final SubLObject obj) {
        return makeBoolean((NIL != member(obj, $list196, UNPROVIDED, UNPROVIDED)) || (NIL != psp_pp_gap_constraint_p(obj)));
    }

    public static final SubLObject psp_allowed_gap_category_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread) == $NP) {
                return $$NounPhrase;
            } else {
                if (NIL != psp_pp_gap_allowedP()) {
                    return $$Preposition;
                } else {
                    return $NONE;
                }
            }
        }
    }

    public static SubLObject psp_allowed_gap_category() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread) == $NP) {
            return $$NounPhrase;
        }
        if (NIL != psp_pp_gap_allowedP()) {
            return $$Preposition;
        }
        return $NONE;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE already have a gapped daughter?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE already have a gapped daughter?")
    public static final SubLObject psp_edge_already_has_gapP_alt(SubLObject edge) {
        {
            SubLObject csome_list_var = psp_edge_dtrs(edge);
            SubLObject dtr = NIL;
            for (dtr = csome_list_var.first(); NIL != csome_list_var; csome_list_var = csome_list_var.rest() , dtr = csome_list_var.first()) {
                if ((NIL != psp_gap_edge_p(dtr)) || (NIL != psp_edge_already_has_gapP(dtr))) {
                    return T;
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE already have a gapped daughter?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE already have a gapped daughter?")
    public static SubLObject psp_edge_already_has_gapP(final SubLObject edge) {
        SubLObject csome_list_var = psp_edge_dtrs(edge);
        SubLObject dtr = NIL;
        dtr = csome_list_var.first();
        while (NIL != csome_list_var) {
            if ((NIL != psp_gap_edge_p(dtr)) || (NIL != psp_edge_already_has_gapP(dtr))) {
                return T;
            }
            csome_list_var = csome_list_var.rest();
            dtr = csome_list_var.first();
        } 
        return NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have a gapped NP daughter?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have a gapped NP daughter?")
    public static final SubLObject psp_edge_has_np_gapP_alt(SubLObject edge) {
        {
            SubLObject csome_list_var = psp_edge_dtrs(edge);
            SubLObject dtr = NIL;
            for (dtr = csome_list_var.first(); NIL != csome_list_var; csome_list_var = csome_list_var.rest() , dtr = csome_list_var.first()) {
                if ((NIL != psp_np_gap_edge_p(dtr)) || (NIL != psp_edge_has_np_gapP(dtr))) {
                    return T;
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have a gapped NP daughter?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have a gapped NP daughter?")
    public static SubLObject psp_edge_has_np_gapP(final SubLObject edge) {
        SubLObject csome_list_var = psp_edge_dtrs(edge);
        SubLObject dtr = NIL;
        dtr = csome_list_var.first();
        while (NIL != csome_list_var) {
            if ((NIL != psp_np_gap_edge_p(dtr)) || (NIL != psp_edge_has_np_gapP(dtr))) {
                return T;
            }
            csome_list_var = csome_list_var.rest();
            dtr = csome_list_var.first();
        } 
        return NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have a gapped PP daughter?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have a gapped PP daughter?")
    public static final SubLObject psp_edge_has_pp_gapP_alt(SubLObject edge) {
        {
            SubLObject dtrs = psp_edge_dtrs(edge);
            SubLObject ans = NIL;
            SubLObject list_var = NIL;
            SubLObject dtr = NIL;
            SubLObject i = NIL;
            for (list_var = psp_edge_dtrs(edge), dtr = list_var.first(), i = ZERO_INTEGER; !((NIL != ans) || (NIL == list_var)); list_var = list_var.rest() , dtr = list_var.first() , i = add(ONE_INTEGER, i)) {
                {
                    SubLObject n = number_utilities.f_1X(i);
                    if (NIL != psp_pp_gap_edge_p(dtr)) {
                        return T;
                    } else {
                        if (NIL != psp_preposition_gap_edge_p(dtr)) {
                            {
                                SubLObject next_dtr = nth(n, dtrs);
                                ans = makeBoolean((NIL != next_dtr) && (NIL != psp_np_gap_edge_p(next_dtr)));
                            }
                        } else {
                            if (NIL != psp_edge_has_pp_gapP(dtr)) {
                                return T;
                            }
                        }
                    }
                }
            }
            return ans;
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have a gapped PP daughter?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have a gapped PP daughter?")
    public static SubLObject psp_edge_has_pp_gapP(final SubLObject edge) {
        final SubLObject dtrs = psp_edge_dtrs(edge);
        SubLObject ans = NIL;
        SubLObject list_var = NIL;
        SubLObject dtr = NIL;
        SubLObject i = NIL;
        list_var = psp_edge_dtrs(edge);
        dtr = list_var.first();
        for (i = ZERO_INTEGER; (NIL == ans) && (NIL != list_var); list_var = list_var.rest() , dtr = list_var.first() , i = add(ONE_INTEGER, i)) {
            final SubLObject n = number_utilities.f_1X(i);
            if (NIL != psp_pp_gap_edge_p(dtr)) {
                return T;
            }
            if (NIL != psp_preposition_gap_edge_p(dtr)) {
                final SubLObject next_dtr = nth(n, dtrs);
                ans = makeBoolean((NIL != next_dtr) && (NIL != psp_np_gap_edge_p(next_dtr)));
            } else
                if (NIL != psp_edge_has_pp_gapP(dtr)) {
                    return T;
                }

        }
        return ans;
    }

    public static final SubLObject psp_gap_allowedP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return makeBoolean(parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread) != $NONE);
        }
    }

    public static SubLObject psp_gap_allowedP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return makeBoolean(parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread) != $NONE);
    }

    public static final SubLObject psp_pp_gap_allowedP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return psp_pp_gap_constraint_p(parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread));
        }
    }

    public static SubLObject psp_pp_gap_allowedP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return psp_pp_gap_constraint_p(parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread));
    }

    public static final SubLObject psp_pp_gap_constraint_p_alt(SubLObject obj) {
        return makeBoolean((obj.isCons() && (obj.first() == $PP)) && obj.rest().isString());
    }

    public static SubLObject psp_pp_gap_constraint_p(final SubLObject obj) {
        return makeBoolean((obj.isCons() && (obj.first() == $PP)) && obj.rest().isString());
    }

    public static final SubLObject psp_pp_gap_string_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return NIL != psp_pp_gap_allowedP() ? ((SubLObject) (parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread).rest())) : NIL;
        }
    }

    public static SubLObject psp_pp_gap_string() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return NIL != psp_pp_gap_allowedP() ? parsing_vars.$psp_gap_type_allowed$.getDynamicValue(thread).rest() : NIL;
    }

    public static final SubLObject new_psp_phrasal_edge_alt(SubLObject chart, SubLObject span, SubLObject string, SubLObject category, SubLObject pos_pred, SubLObject cycls, SubLObject weight, SubLObject rule, SubLObject new_dtr_constraints, SubLObject head_dtr, SubLObject non_head_dtrs) {
        if (category == UNPROVIDED) {
            category = NIL;
        }
        if (pos_pred == UNPROVIDED) {
            pos_pred = NIL;
        }
        if (cycls == UNPROVIDED) {
            cycls = $NEW;
        }
        if (weight == UNPROVIDED) {
            weight = NIL;
        }
        if (rule == UNPROVIDED) {
            rule = NIL;
        }
        if (new_dtr_constraints == UNPROVIDED) {
            new_dtr_constraints = NIL;
        }
        if (head_dtr == UNPROVIDED) {
            head_dtr = NIL;
        }
        if (non_head_dtrs == UNPROVIDED) {
            non_head_dtrs = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != string) {
                SubLTrampolineFile.checkType(string, STRINGP);
            }
            SubLTrampolineFile.checkType(span, INTERVAL_SPAN_P);
            if (cycls == $NEW) {
                cycls = NIL;
            }
            if (NIL != cycls) {
                SubLTrampolineFile.checkType(cycls, DICTIONARY_P);
            }
            if (NIL != parsing_vars.$psp_chart_consolidate_edgesP$.getDynamicValue(thread)) {
                thread.resetMultipleValues();
                {
                    SubLObject head_dtr_3 = psp_maybe_setify_dtrs(head_dtr, non_head_dtrs);
                    SubLObject non_head_dtrs_4 = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    head_dtr = head_dtr_3;
                    non_head_dtrs = non_head_dtrs_4;
                }
            }
            {
                SubLObject edge = make_phrase_structure_parser_phrasal_edge(UNPROVIDED);
                _csetf_psp_phrasal_edge_chart(edge, chart);
                _csetf_psp_phrasal_edge_span(edge, span);
                _csetf_psp_phrasal_edge_string(edge, string);
                _csetf_psp_phrasal_edge_category(edge, category);
                _csetf_psp_phrasal_edge_pos_pred(edge, pos_pred);
                _csetf_psp_phrasal_edge_cycls(edge, cycls);
                _csetf_psp_phrasal_edge_rule(edge, rule);
                _csetf_psp_phrasal_edge_new_dtr_constraints(edge, new_dtr_constraints);
                _csetf_psp_phrasal_edge_head_dtr(edge, head_dtr);
                _csetf_psp_phrasal_edge_non_head_dtrs(edge, non_head_dtrs);
                _csetf_psp_phrasal_edge_id(edge, psp_chart_new_edge_id(chart));
                _csetf_psp_phrasal_edge_weight(edge, NIL != weight ? ((SubLObject) (weight)) : (NIL != head_dtr) || (NIL != non_head_dtrs) ? ((SubLObject) (psp_edge_weight_from_dtrs(head_dtr, non_head_dtrs, rule))) : $psp_unknown_weight$.getGlobalValue());
                return edge;
            }
        }
    }

    public static SubLObject new_psp_phrasal_edge(final SubLObject chart, final SubLObject span, final SubLObject string, SubLObject category, SubLObject pos_pred, SubLObject cycls, SubLObject weight, SubLObject rule, SubLObject new_dtr_constraints, SubLObject head_dtr, SubLObject non_head_dtrs) {
        if (category == UNPROVIDED) {
            category = NIL;
        }
        if (pos_pred == UNPROVIDED) {
            pos_pred = NIL;
        }
        if (cycls == UNPROVIDED) {
            cycls = $NEW;
        }
        if (weight == UNPROVIDED) {
            weight = NIL;
        }
        if (rule == UNPROVIDED) {
            rule = NIL;
        }
        if (new_dtr_constraints == UNPROVIDED) {
            new_dtr_constraints = NIL;
        }
        if (head_dtr == UNPROVIDED) {
            head_dtr = NIL;
        }
        if (non_head_dtrs == UNPROVIDED) {
            non_head_dtrs = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (((NIL != string) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == stringp(string))) {
            throw new AssertionError(string);
        }
        assert NIL != interval_span.interval_span_p(span) : "! interval_span.interval_span_p(span) " + ("interval_span.interval_span_p(span) " + "CommonSymbols.NIL != interval_span.interval_span_p(span) ") + span;
        if (cycls == $NEW) {
            cycls = NIL;
        }
        if (((NIL != cycls) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == dictionary.dictionary_p(cycls))) {
            throw new AssertionError(cycls);
        }
        if (NIL != parsing_vars.$psp_chart_consolidate_edgesP$.getDynamicValue(thread)) {
            thread.resetMultipleValues();
            final SubLObject head_dtr_$3 = psp_maybe_setify_dtrs(head_dtr, non_head_dtrs);
            final SubLObject non_head_dtrs_$4 = thread.secondMultipleValue();
            thread.resetMultipleValues();
            head_dtr = head_dtr_$3;
            non_head_dtrs = non_head_dtrs_$4;
        }
        final SubLObject edge = make_phrase_structure_parser_phrasal_edge(UNPROVIDED);
        _csetf_psp_phrasal_edge_chart(edge, chart);
        _csetf_psp_phrasal_edge_span(edge, span);
        _csetf_psp_phrasal_edge_string(edge, string);
        _csetf_psp_phrasal_edge_category(edge, category);
        _csetf_psp_phrasal_edge_pos_pred(edge, pos_pred);
        _csetf_psp_phrasal_edge_cycls(edge, cycls);
        _csetf_psp_phrasal_edge_rule(edge, rule);
        _csetf_psp_phrasal_edge_new_dtr_constraints(edge, new_dtr_constraints);
        _csetf_psp_phrasal_edge_head_dtr(edge, head_dtr);
        _csetf_psp_phrasal_edge_non_head_dtrs(edge, non_head_dtrs);
        _csetf_psp_phrasal_edge_id(edge, psp_chart_new_edge_id(chart));
        _csetf_psp_phrasal_edge_weight(edge, NIL != weight ? weight : (NIL != head_dtr) || (NIL != non_head_dtrs) ? psp_edge_weight_from_dtrs(head_dtr, non_head_dtrs, rule) : $psp_unknown_weight$.getGlobalValue());
        return edge;
    }

    public static final SubLObject psp_maybe_setify_dtrs_alt(SubLObject head_dtr, SubLObject non_head_dtrs) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != parsing_vars.$psp_chart_consolidate_edgesP$.getDynamicValue(thread)) {
                return values(list(head_dtr), Mapping.mapcar(LIST, non_head_dtrs));
            } else {
                return values(head_dtr, non_head_dtrs);
            }
        }
    }

    public static SubLObject psp_maybe_setify_dtrs(final SubLObject head_dtr, final SubLObject non_head_dtrs) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != parsing_vars.$psp_chart_consolidate_edgesP$.getDynamicValue(thread)) {
            return values(list(head_dtr), Mapping.mapcar(LIST, non_head_dtrs));
        }
        return values(head_dtr, non_head_dtrs);
    }

    public static final SubLObject new_psp_lexical_edge_alt(SubLObject chart, SubLObject span, SubLObject string, SubLObject category, SubLObject pos_pred, SubLObject cycls, SubLObject weight, SubLObject wu, SubLObject frame) {
        if (category == UNPROVIDED) {
            category = NIL;
        }
        if (pos_pred == UNPROVIDED) {
            pos_pred = NIL;
        }
        if (cycls == UNPROVIDED) {
            cycls = $NEW;
        }
        if (weight == UNPROVIDED) {
            weight = NIL;
        }
        if (wu == UNPROVIDED) {
            wu = NIL;
        }
        if (frame == UNPROVIDED) {
            frame = NIL;
        }
        SubLTrampolineFile.checkType(string, STRINGP);
        SubLTrampolineFile.checkType(span, INTERVAL_SPAN_P);
        if ((NIL != category) && (NIL != string_utilities.non_empty_string_p(string))) {
            SubLTrampolineFile.checkType(pos_pred, PREDICATE_P);
        }
        if (cycls == $NEW) {
            cycls = NIL;
        }
        if (NIL != cycls) {
            SubLTrampolineFile.checkType(cycls, DICTIONARY_P);
        }
        {
            SubLObject id = psp_chart_new_edge_id(chart);
            SubLObject psp_weight = (NIL != weight) ? ((SubLObject) (weight)) : $psp_unknown_weight$.getGlobalValue();
            SubLObject edge = make_phrase_structure_parser_lexical_edge(UNPROVIDED);
            _csetf_psp_lexical_edge_chart(edge, chart);
            _csetf_psp_lexical_edge_span(edge, span);
            _csetf_psp_lexical_edge_string(edge, string);
            _csetf_psp_lexical_edge_category(edge, category);
            _csetf_psp_lexical_edge_pos_pred(edge, pos_pred);
            _csetf_psp_lexical_edge_cycls(edge, cycls);
            _csetf_psp_lexical_edge_wu(edge, wu);
            _csetf_psp_lexical_edge_frame(edge, frame);
            _csetf_psp_lexical_edge_id(edge, id);
            _csetf_psp_lexical_edge_weight(edge, psp_weight);
            return edge;
        }
    }

    public static SubLObject new_psp_lexical_edge(final SubLObject chart, final SubLObject span, final SubLObject string, SubLObject category, SubLObject pos_pred, SubLObject cycls, SubLObject weight, SubLObject wu, SubLObject frame) {
        if (category == UNPROVIDED) {
            category = NIL;
        }
        if (pos_pred == UNPROVIDED) {
            pos_pred = NIL;
        }
        if (cycls == UNPROVIDED) {
            cycls = $NEW;
        }
        if (weight == UNPROVIDED) {
            weight = NIL;
        }
        if (wu == UNPROVIDED) {
            wu = NIL;
        }
        if (frame == UNPROVIDED) {
            frame = NIL;
        }
        assert NIL != stringp(string) : "! stringp(string) " + ("Types.stringp(string) " + "CommonSymbols.NIL != Types.stringp(string) ") + string;
        assert NIL != interval_span.interval_span_p(span) : "! interval_span.interval_span_p(span) " + ("interval_span.interval_span_p(span) " + "CommonSymbols.NIL != interval_span.interval_span_p(span) ") + span;
        if ((((NIL != category) && (NIL != string_utilities.non_empty_string_p(string))) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == fort_types_interface.predicate_p(pos_pred))) {
            throw new AssertionError(pos_pred);
        }
        if (cycls == $NEW) {
            cycls = NIL;
        }
        if (((NIL != cycls) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == dictionary.dictionary_p(cycls))) {
            throw new AssertionError(cycls);
        }
        final SubLObject id = psp_chart_new_edge_id(chart);
        final SubLObject psp_weight = (NIL != weight) ? weight : $psp_unknown_weight$.getGlobalValue();
        final SubLObject edge = make_phrase_structure_parser_lexical_edge(UNPROVIDED);
        _csetf_psp_lexical_edge_chart(edge, chart);
        _csetf_psp_lexical_edge_span(edge, span);
        _csetf_psp_lexical_edge_string(edge, string);
        _csetf_psp_lexical_edge_category(edge, category);
        _csetf_psp_lexical_edge_pos_pred(edge, pos_pred);
        _csetf_psp_lexical_edge_cycls(edge, cycls);
        _csetf_psp_lexical_edge_wu(edge, wu);
        _csetf_psp_lexical_edge_frame(edge, frame);
        _csetf_psp_lexical_edge_id(edge, id);
        _csetf_psp_lexical_edge_weight(edge, psp_weight);
        return edge;
    }

    public static final SubLObject psp_tokenization_mode_p_alt(SubLObject obj) {
        return subl_promotions.memberP(obj, $psp_tokenization_modes$.getGlobalValue(), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject psp_tokenization_mode_p(final SubLObject obj) {
        return subl_promotions.memberP(obj, $psp_tokenization_modes$.getGlobalValue(), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @param SENTENCE;
    sign-p.
     * 		
     * @param STRINGS;
     * 		listp of strings.
     * 		Set the words of SENTENCE to be a vector consisting of word structures
     * 		for the strings in STRINGS.
     */
    @LispMethod(comment = "@param SENTENCE;\nsign-p.\r\n\t\t\r\n@param STRINGS;\r\n\t\tlistp of strings.\r\n\t\tSet the words of SENTENCE to be a vector consisting of word structures\r\n\t\tfor the strings in STRINGS.")
    public static final SubLObject sentence_set_words_from_string_list_alt(SubLObject sentence, SubLObject strings, SubLObject sentence_string) {
        {
            SubLObject words = NIL;
            SubLObject offset = ZERO_INTEGER;
            SubLObject cdolist_list_var = strings;
            SubLObject string = NIL;
            for (string = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , string = cdolist_list_var.first()) {
                offset = search(string, sentence_string, symbol_function(EQUAL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, offset, UNPROVIDED);
                if ((NIL == offset) && (NIL == list_utilities.empty_list_p(words))) {
                    offset = add(ONE_INTEGER, document.word_offset(words.first()), document.word_length(words.first()));
                } else {
                    if (NIL == offset) {
                        offset = ZERO_INTEGER;
                    }
                }
                words = cons(document.new_word(list($STRING, string, $OFFSET, offset)), words);
            }
            words = apply(VECTOR, nreverse(words));
            document.sign_update(sentence, list($CONSTITUENTS, words));
        }
        return sentence;
    }

    /**
     *
     *
     * @param SENTENCE;
    sign-p.
     * 		
     * @param STRINGS;
     * 		listp of strings.
     * 		Set the words of SENTENCE to be a vector consisting of word structures
     * 		for the strings in STRINGS.
     */
    @LispMethod(comment = "@param SENTENCE;\nsign-p.\r\n\t\t\r\n@param STRINGS;\r\n\t\tlistp of strings.\r\n\t\tSet the words of SENTENCE to be a vector consisting of word structures\r\n\t\tfor the strings in STRINGS.")
    public static SubLObject sentence_set_words_from_string_list(final SubLObject sentence, final SubLObject strings, final SubLObject sentence_string) {
        SubLObject words = NIL;
        SubLObject offset = ZERO_INTEGER;
        SubLObject cdolist_list_var = strings;
        SubLObject string = NIL;
        string = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            offset = search(string, sentence_string, symbol_function(EQUAL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, offset, UNPROVIDED);
            if ((NIL == offset) && (NIL == list_utilities.empty_list_p(words))) {
                offset = add(ONE_INTEGER, document.word_offset(words.first()), document.word_length(words.first()));
            } else
                if (NIL == offset) {
                    offset = ZERO_INTEGER;
                }

            words = cons(document.new_word(list($STRING, string, $OFFSET, offset)), words);
            cdolist_list_var = cdolist_list_var.rest();
            string = cdolist_list_var.first();
        } 
        words = apply(VECTOR, nreverse(words));
        document.sign_update(sentence, list($CONSTITUENTS, words));
        return sentence;
    }

    /**
     * Initialize CHART from INPUT-STRING, only considering lexical entries from LEXICON.
     */
    @LispMethod(comment = "Initialize CHART from INPUT-STRING, only considering lexical entries from LEXICON.")
    public static final SubLObject initialize_psp_chart_alt(SubLObject input_string, SubLObject chart, SubLObject lexicon, SubLObject gap_type) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (lexicon == UNPROVIDED) {
            lexicon = psp_lexicon.get_default_psp_lexicon(UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        if (gap_type == UNPROVIDED) {
            gap_type = $NONE;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(lexicon, PSP_LEXICON_P);
            input_string = string_utilities.string_substitute($str_alt199$_, $str_alt200$___, input_string, UNPROVIDED);
            psp_chart_clear(chart);
            psp_chart_set_string(chart, input_string);
            psp_chart_set_gap_type(chart, gap_type);
            {
                SubLObject sentence = document.new_sentence(UNPROVIDED, UNPROVIDED);
                SubLObject state = memoization_state.new_memoization_state(UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                SubLObject local_state = state;
                {
                    SubLObject _prev_bind_0 = memoization_state.$memoization_state$.currentBinding(thread);
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
                                            Errors.error($str_alt201$Invalid_attempt_to_reuse_memoizat);
                                        }
                                    }
                                }
                            }
                            try {
                                document.sign_update(sentence, list($STRING, input_string));
                            } finally {
                                {
                                    SubLObject _prev_bind_0_5 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        sentence_set_words_from_string_list(sentence, psp_string_tokenize(input_string), input_string);
                                        _csetf_psp_chart_length(chart, document.sentence_length(sentence));
                                        {
                                            SubLObject cdolist_list_var = $psp_tokenization_modes$.getGlobalValue();
                                            SubLObject mode = NIL;
                                            for (mode = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , mode = cdolist_list_var.first()) {
                                                psp_add_edges_from_sentence(sentence, mode, lexicon, chart);
                                            }
                                        }
                                        if ((NIL != local_state) && (NIL == original_memoization_process)) {
                                            memoization_state.memoization_state_set_current_process_internal(local_state, NIL);
                                        }
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_5, thread);
                                    }
                                }
                            }
                        }
                    } finally {
                        memoization_state.$memoization_state$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return $INITIALIZED;
        }
    }

    /**
     * Initialize CHART from INPUT-STRING, only considering lexical entries from LEXICON.
     */
    @LispMethod(comment = "Initialize CHART from INPUT-STRING, only considering lexical entries from LEXICON.")
    public static SubLObject initialize_psp_chart(SubLObject input_string, SubLObject chart, SubLObject lexicon, SubLObject gap_type) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (lexicon == UNPROVIDED) {
            lexicon = psp_lexicon.get_default_psp_lexicon(UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        if (gap_type == UNPROVIDED) {
            gap_type = $NONE;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != psp_lexicon.psp_lexicon_p(lexicon) : "! psp_lexicon.psp_lexicon_p(lexicon) " + ("psp_lexicon.psp_lexicon_p(lexicon) " + "CommonSymbols.NIL != psp_lexicon.psp_lexicon_p(lexicon) ") + lexicon;
        input_string = string_utilities.string_substitute($str210$_, $str211$___, input_string, UNPROVIDED);
        psp_chart_clear(chart);
        psp_chart_set_string(chart, input_string);
        psp_chart_set_gap_type(chart, gap_type);
        final SubLObject sentence = document.new_sentence(UNPROVIDED, UNPROVIDED);
        final SubLObject local_state;
        final SubLObject state = local_state = memoization_state.new_memoization_state(UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject _prev_bind_0 = memoization_state.$memoization_state$.currentBinding(thread);
        try {
            memoization_state.$memoization_state$.bind(local_state, thread);
            final SubLObject original_memoization_process = memoization_state.memoization_state_original_process(local_state);
            try {
                document.sign_update(sentence, list($STRING, input_string));
                sentence_set_words_from_string_list(sentence, psp_string_tokenize(input_string), input_string);
                _csetf_psp_chart_length(chart, document.sentence_length(sentence));
                SubLObject cdolist_list_var = $psp_tokenization_modes$.getGlobalValue();
                SubLObject mode = NIL;
                mode = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    psp_add_edges_from_sentence(sentence, mode, lexicon, chart);
                    cdolist_list_var = cdolist_list_var.rest();
                    mode = cdolist_list_var.first();
                } 
            } finally {
                final SubLObject _prev_bind_0_$5 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    memoization_state.memoization_state_possibly_clear_original_process(local_state, original_memoization_process);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$5, thread);
                }
            }
        } finally {
            memoization_state.$memoization_state$.rebind(_prev_bind_0, thread);
        }
        return $INITIALIZED;
    }

    public static final SubLObject psp_add_edges_from_sentence_alt(SubLObject sentence, SubLObject mode, SubLObject lexicon, SubLObject chart) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(sentence, SENTENCE_P);
            {
                SubLObject _prev_bind_0 = parsing_vars.$psp_chart$.currentBinding(thread);
                try {
                    parsing_vars.$psp_chart$.bind(chart, thread);
                    {
                        SubLObject edges = psp_sentence_edgify(sentence, mode, lexicon);
                        SubLObject cdolist_list_var = edges;
                        SubLObject edge = NIL;
                        for (edge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , edge = cdolist_list_var.first()) {
                            psp_edge_adjust_weight_for_mode(edge, mode);
                            if (!((mode == $DILIGENT) && (NIL != psp_chart_has_better_edgeP(chart, edge)))) {
                                psp_chart_add_edge(edge, UNPROVIDED);
                            }
                        }
                    }
                } finally {
                    parsing_vars.$psp_chart$.rebind(_prev_bind_0, thread);
                }
            }
            return $ADDED;
        }
    }

    public static SubLObject psp_add_edges_from_sentence(final SubLObject sentence, final SubLObject mode, final SubLObject lexicon, final SubLObject chart) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != document.sentence_p(sentence) : "! document.sentence_p(sentence) " + ("document.sentence_p(sentence) " + "CommonSymbols.NIL != document.sentence_p(sentence) ") + sentence;
        final SubLObject _prev_bind_0 = parsing_vars.$psp_chart$.currentBinding(thread);
        try {
            parsing_vars.$psp_chart$.bind(chart, thread);
            SubLObject cdolist_list_var;
            final SubLObject edges = cdolist_list_var = psp_sentence_edgify(sentence, mode, lexicon);
            SubLObject edge = NIL;
            edge = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                psp_edge_adjust_weight_for_mode(edge, mode);
                if ((mode != $DILIGENT) || (NIL == psp_chart_has_better_edgeP(chart, edge))) {
                    psp_chart_add_edge(edge, UNPROVIDED);
                }
                cdolist_list_var = cdolist_list_var.rest();
                edge = cdolist_list_var.first();
            } 
        } finally {
            parsing_vars.$psp_chart$.rebind(_prev_bind_0, thread);
        }
        return $ADDED;
    }

    /**
     * Adjust EDGE's weight based on MODE
     */
    @LispMethod(comment = "Adjust EDGE\'s weight based on MODE")
    public static final SubLObject psp_edge_adjust_weight_for_mode_alt(SubLObject edge, SubLObject mode) {
        {
            SubLObject mode_weight = psp_default_weight_for_mode(mode);
            SubLObject edge_weight = get_psp_edge_weight(edge);
            set_psp_edge_weight(edge, psp_weight_compose_two(mode_weight, edge_weight));
        }
        return get_psp_edge_weight(edge);
    }

    /**
     * Adjust EDGE's weight based on MODE
     */
    @LispMethod(comment = "Adjust EDGE\'s weight based on MODE")
    public static SubLObject psp_edge_adjust_weight_for_mode(final SubLObject edge, final SubLObject mode) {
        final SubLObject mode_weight = psp_default_weight_for_mode(mode);
        final SubLObject edge_weight = get_psp_edge_weight(edge);
        set_psp_edge_weight(edge, psp_weight_compose_two(mode_weight, edge_weight));
        return get_psp_edge_weight(edge);
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE represent a closed-class word?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE represent a closed-class word?")
    public static final SubLObject closed_lexical_class_edgeP_alt(SubLObject edge) {
        return lexicon_accessors.genl_posP(get_psp_edge_category(edge), $$ClosedClassWord, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE represent a closed-class word?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE represent a closed-class word?")
    public static SubLObject closed_lexical_class_edgeP(final SubLObject edge) {
        return lexicon_accessors.genl_posP(get_psp_edge_category(edge), $$ClosedClassWord, UNPROVIDED);
    }

    /**
     *
     *
     * @param MODE;
    psp-tokenization-mode-p
     * 		
     * @return PSP-WEIGHT-P; appropriate for edges found in MODE
     */
    @LispMethod(comment = "@param MODE;\npsp-tokenization-mode-p\r\n\t\t\r\n@return PSP-WEIGHT-P; appropriate for edges found in MODE")
    public static final SubLObject psp_default_weight_for_mode_alt(SubLObject mode) {
        SubLTrampolineFile.checkType(mode, PSP_TOKENIZATION_MODE_P);
        return mode == $GREEDY ? ((SubLObject) ($psp_edge_greedy_weight$.getGlobalValue())) : $psp_edge_dispreferred_weight$.getGlobalValue();
    }

    /**
     *
     *
     * @param MODE;
    psp-tokenization-mode-p
     * 		
     * @return PSP-WEIGHT-P; appropriate for edges found in MODE
     */
    @LispMethod(comment = "@param MODE;\npsp-tokenization-mode-p\r\n\t\t\r\n@return PSP-WEIGHT-P; appropriate for edges found in MODE")
    public static SubLObject psp_default_weight_for_mode(final SubLObject mode) {
        assert NIL != psp_tokenization_mode_p(mode) : "! psp_chart.psp_tokenization_mode_p(mode) " + ("psp_chart.psp_tokenization_mode_p(mode) " + "CommonSymbols.NIL != psp_chart.psp_tokenization_mode_p(mode) ") + mode;
        return mode == $GREEDY ? $psp_edge_greedy_weight$.getGlobalValue() : $psp_edge_dispreferred_weight$.getGlobalValue();
    }

    public static final SubLObject psp_chart_has_better_edgeP_alt(SubLObject chart, SubLObject edge) {
        {
            SubLObject existing_edges = psp_edges_with_span(get_psp_edge_span(edge), chart);
            SubLObject foundP = NIL;
            if (NIL == foundP) {
                {
                    SubLObject csome_list_var = existing_edges;
                    SubLObject existing_edge = NIL;
                    for (existing_edge = csome_list_var.first(); !((NIL != foundP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , existing_edge = csome_list_var.first()) {
                        if (NIL != psp_better_edgeP(existing_edge, edge)) {
                            foundP = T;
                        }
                    }
                }
            }
            return foundP;
        }
    }

    public static SubLObject psp_chart_has_better_edgeP(final SubLObject chart, final SubLObject edge) {
        final SubLObject existing_edges = psp_edges_with_span(get_psp_edge_span(edge), chart);
        SubLObject foundP = NIL;
        if (NIL == foundP) {
            SubLObject csome_list_var = existing_edges;
            SubLObject existing_edge = NIL;
            existing_edge = csome_list_var.first();
            while ((NIL == foundP) && (NIL != csome_list_var)) {
                if (NIL != psp_better_edgeP(existing_edge, edge)) {
                    foundP = T;
                }
                csome_list_var = csome_list_var.rest();
                existing_edge = csome_list_var.first();
            } 
        }
        return foundP;
    }

    /**
     * Is EDGE1 better than EDGE2?
     */
    @LispMethod(comment = "Is EDGE1 better than EDGE2?")
    public static final SubLObject psp_better_edgeP_alt(SubLObject edge1, SubLObject edge2) {
        return makeBoolean((NIL != psp_edge_weightierP(edge1, edge2, UNPROVIDED)) && (NIL != psp_edges_same_except_for_weightP(edge1, edge2)));
    }

    /**
     * Is EDGE1 better than EDGE2?
     */
    @LispMethod(comment = "Is EDGE1 better than EDGE2?")
    public static SubLObject psp_better_edgeP(final SubLObject edge1, final SubLObject edge2) {
        return makeBoolean((NIL != psp_edge_weightierP(edge1, edge2, UNPROVIDED)) && (NIL != psp_edges_same_except_for_weightP(edge1, edge2)));
    }

    public static final SubLObject psp_edges_same_except_for_weightP_alt(SubLObject edge1, SubLObject edge2) {
        if (!get_psp_edge_category(edge1).equal(get_psp_edge_category(edge2))) {
            return NIL;
        } else {
            if (!get_psp_edge_pos_pred(edge1).equal(get_psp_edge_pos_pred(edge2))) {
                return NIL;
            } else {
                if (!get_psp_edge_wu(edge1).equal(get_psp_edge_wu(edge2))) {
                    return NIL;
                } else {
                    if (!get_psp_edge_frame(edge1).equal(get_psp_edge_frame(edge2))) {
                        return NIL;
                    } else {
                        if (!get_psp_edge_cycls(edge1).equalp(get_psp_edge_cycls(edge2))) {
                            return NIL;
                        } else {
                            return T;
                        }
                    }
                }
            }
        }
    }

    public static SubLObject psp_edges_same_except_for_weightP(final SubLObject edge1, final SubLObject edge2) {
        if (!get_psp_edge_category(edge1).equal(get_psp_edge_category(edge2))) {
            return NIL;
        }
        if (!get_psp_edge_pos_pred(edge1).equal(get_psp_edge_pos_pred(edge2))) {
            return NIL;
        }
        if (!get_psp_edge_wu(edge1).equal(get_psp_edge_wu(edge2))) {
            return NIL;
        }
        if (!get_psp_edge_frame(edge1).equal(get_psp_edge_frame(edge2))) {
            return NIL;
        }
        if (!get_psp_edge_cycls(edge1).equalp(get_psp_edge_cycls(edge2))) {
            return NIL;
        }
        return T;
    }

    public static final SubLObject psp_edge_weightierP_alt(SubLObject edge1, SubLObject edge2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        {
            SubLObject ans = makeBoolean(get_psp_edge_span(edge1).eql(get_psp_edge_span(edge2)) && (NIL != psp_weight_G(get_psp_edge_weight(edge1), get_psp_edge_weight(edge2), margin_of_error)));
            return ans;
        }
    }

    public static SubLObject psp_edge_weightierP(final SubLObject edge1, final SubLObject edge2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        final SubLObject ans = makeBoolean(get_psp_edge_span(edge1).eql(get_psp_edge_span(edge2)) && (NIL != psp_weight_G(get_psp_edge_weight(edge1), get_psp_edge_weight(edge2), margin_of_error)));
        return ans;
    }

    public static final SubLObject psp_edge_weights_equalP_alt(SubLObject edge1, SubLObject edge2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        return makeBoolean((NIL == psp_edge_weightierP(edge1, edge2, margin_of_error)) && (NIL == psp_edge_weightierP(edge2, edge1, margin_of_error)));
    }

    public static SubLObject psp_edge_weights_equalP(final SubLObject edge1, final SubLObject edge2, SubLObject margin_of_error) {
        if (margin_of_error == UNPROVIDED) {
            margin_of_error = $float$0_0;
        }
        return makeBoolean((NIL == psp_edge_weightierP(edge1, edge2, margin_of_error)) && (NIL == psp_edge_weightierP(edge2, edge1, margin_of_error)));
    }

    /**
     *
     *
     * @unknown awaiting unified tokenizer for trie and parsers...
     */
    @LispMethod(comment = "@unknown awaiting unified tokenizer for trie and parsers...")
    public static final SubLObject psp_string_tokenize_alt(SubLObject string) {
        return nl_trie.nl_trie_string_tokenize(string);
    }

    /**
     *
     *
     * @unknown awaiting unified tokenizer for trie and parsers...
     */
    @LispMethod(comment = "@unknown awaiting unified tokenizer for trie and parsers...")
    public static SubLObject psp_string_tokenize(final SubLObject string) {
        return nl_trie.nl_trie_string_tokenize(string);
    }

    /**
     *
     *
     * @param SENTENCE
    sentence-p
     * 		
     * @param MODE
    psp-tokenization-mode-p
     * 		
     * @param LEXICON
    psp-lexicon-p
     * 		
     * @return list; a list of edge structures for SENTENCE, only considering entries from LEXICON.
     * @unknown this is a bad hack; we need to find a principled way to apply a variety of
    different tokenizers/parsers during lexical lookup; this needs to be rewritten
     */
    @LispMethod(comment = "@param SENTENCE\nsentence-p\r\n\t\t\r\n@param MODE\npsp-tokenization-mode-p\r\n\t\t\r\n@param LEXICON\npsp-lexicon-p\r\n\t\t\r\n@return list; a list of edge structures for SENTENCE, only considering entries from LEXICON.\r\n@unknown this is a bad hack; we need to find a principled way to apply a variety of\r\ndifferent tokenizers/parsers during lexical lookup; this needs to be rewritten")
    public static final SubLObject psp_sentence_edgify_alt(SubLObject sentence, SubLObject mode, SubLObject lexicon) {
        if (mode == UNPROVIDED) {
            mode = $GREEDY;
        }
        if (lexicon == UNPROVIDED) {
            lexicon = psp_lexicon.get_default_psp_lexicon(UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        SubLTrampolineFile.checkType(sentence, SENTENCE_P);
        SubLTrampolineFile.checkType(lexicon, PSP_LEXICON_P);
        SubLTrampolineFile.checkType(mode, PSP_TOKENIZATION_MODE_P);
        {
            SubLObject words = document.sentence_listify(sentence);
            SubLObject all_edges = make_vector(document.sentence_length(sentence), UNPROVIDED);
            SubLObject edges = NIL;
            SubLObject cdotimes_end_var = document.sentence_length(sentence);
            SubLObject i = NIL;
            for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                set_aref(all_edges, i, set.new_set(EQUAL, UNPROVIDED));
            }
            psp_add_indexical_edges(all_edges, words);
            psp_add_lexicon_edges(all_edges, sentence, lexicon);
            {
                SubLObject sentence_copy = psp_sentence_copy(sentence);
                psp_add_english_number_edges(all_edges, sentence_copy);
                psp_add_digit_edges(all_edges, sentence_copy);
                psp_add_quantity_edges(all_edges, sentence_copy);
            }
            psp_add_guessed_edges(all_edges, words);
            {
                SubLObject pcase_var = mode;
                if (pcase_var.eql($GREEDY)) {
                    {
                        SubLObject end_index = document.sentence_length(sentence);
                        SubLObject new_start = NIL;
                        SubLObject start = NIL;
                        for (start = ZERO_INTEGER; !start.eql(end_index); start = new_start) {
                            {
                                SubLObject new_edges = psp_longest_edges(aref(all_edges, start));
                                SubLObject longest_edge = new_edges.first();
                                edges = nconc(new_edges, edges);
                                new_start = (NIL != longest_edge) ? ((SubLObject) (interval_span.interval_span_end(get_psp_edge_span(longest_edge)))) : number_utilities.f_1X(start);
                                if (!new_start.numG(start)) {
                                    Errors.error($str_alt209$Failed_to_get_bigger_than__S__Lon, start, longest_edge, get_psp_edge_span(longest_edge));
                                }
                            }
                        }
                    }
                } else {
                    if (pcase_var.eql($DILIGENT)) {
                        {
                            SubLObject vector_var = all_edges;
                            SubLObject backwardP_var = NIL;
                            SubLObject length = length(vector_var);
                            SubLObject v_iteration = NIL;
                            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                {
                                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                    SubLObject index_edges = aref(vector_var, element_num);
                                    edges = nconc(set.set_element_list(index_edges), edges);
                                }
                            }
                        }
                    }
                }
            }
            return edges;
        }
    }

    /**
     *
     *
     * @param SENTENCE
    sentence-p
     * 		
     * @param MODE
    psp-tokenization-mode-p
     * 		
     * @param LEXICON
    psp-lexicon-p
     * 		
     * @return list; a list of edge structures for SENTENCE, only considering entries from LEXICON.
     * @unknown this is a bad hack; we need to find a principled way to apply a variety of
    different tokenizers/parsers during lexical lookup; this needs to be rewritten
     */
    @LispMethod(comment = "@param SENTENCE\nsentence-p\r\n\t\t\r\n@param MODE\npsp-tokenization-mode-p\r\n\t\t\r\n@param LEXICON\npsp-lexicon-p\r\n\t\t\r\n@return list; a list of edge structures for SENTENCE, only considering entries from LEXICON.\r\n@unknown this is a bad hack; we need to find a principled way to apply a variety of\r\ndifferent tokenizers/parsers during lexical lookup; this needs to be rewritten")
    public static SubLObject psp_sentence_edgify(final SubLObject sentence, SubLObject mode, SubLObject lexicon) {
        if (mode == UNPROVIDED) {
            mode = $GREEDY;
        }
        if (lexicon == UNPROVIDED) {
            lexicon = psp_lexicon.get_default_psp_lexicon(UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        assert NIL != document.sentence_p(sentence) : "! document.sentence_p(sentence) " + ("document.sentence_p(sentence) " + "CommonSymbols.NIL != document.sentence_p(sentence) ") + sentence;
        assert NIL != psp_lexicon.psp_lexicon_p(lexicon) : "! psp_lexicon.psp_lexicon_p(lexicon) " + ("psp_lexicon.psp_lexicon_p(lexicon) " + "CommonSymbols.NIL != psp_lexicon.psp_lexicon_p(lexicon) ") + lexicon;
        assert NIL != psp_tokenization_mode_p(mode) : "! psp_chart.psp_tokenization_mode_p(mode) " + ("psp_chart.psp_tokenization_mode_p(mode) " + "CommonSymbols.NIL != psp_chart.psp_tokenization_mode_p(mode) ") + mode;
        final SubLObject words = document.sentence_listify(sentence);
        final SubLObject all_edges = make_vector(document.sentence_length(sentence), UNPROVIDED);
        SubLObject edges = NIL;
        SubLObject cdotimes_end_var;
        SubLObject i;
        for (cdotimes_end_var = document.sentence_length(sentence), i = NIL, i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
            set_aref(all_edges, i, set.new_set(EQUAL, UNPROVIDED));
        }
        psp_add_indexical_edges(all_edges, words);
        psp_add_lexicon_edges(all_edges, sentence, lexicon);
        final SubLObject sentence_copy = psp_sentence_copy(sentence);
        psp_add_english_number_edges(all_edges, sentence_copy);
        psp_add_digit_edges(all_edges, sentence_copy);
        psp_add_quantity_edges(all_edges, sentence_copy);
        psp_add_guessed_edges(all_edges, words);
        final SubLObject pcase_var = mode;
        if (pcase_var.eql($GREEDY)) {
            SubLObject end_index;
            SubLObject new_start;
            SubLObject start;
            SubLObject new_edges;
            SubLObject longest_edge;
            for (end_index = document.sentence_length(sentence), new_start = NIL, start = NIL, start = ZERO_INTEGER; !start.eql(end_index); start = new_start) {
                new_edges = psp_longest_edges(aref(all_edges, start));
                longest_edge = new_edges.first();
                edges = nconc(new_edges, edges);
                new_start = (NIL != longest_edge) ? interval_span.interval_span_end(get_psp_edge_span(longest_edge)) : number_utilities.f_1X(start);
                if (!new_start.numG(start)) {
                    Errors.error($str219$Failed_to_get_bigger_than__S__Lon, start, longest_edge, get_psp_edge_span(longest_edge));
                }
            }
        } else
            if (pcase_var.eql($DILIGENT)) {
                final SubLObject vector_var = all_edges;
                final SubLObject backwardP_var = NIL;
                SubLObject length;
                SubLObject v_iteration;
                SubLObject element_num;
                SubLObject index_edges;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    index_edges = aref(vector_var, element_num);
                    edges = nconc(set.set_element_list(index_edges), edges);
                }
            }

        return edges;
    }

    /**
     * Add EDGE to RESULT-VECTOR.
     */
    @LispMethod(comment = "Add EDGE to RESULT-VECTOR.")
    public static final SubLObject psp_add_edge_to_result_vector_alt(SubLObject edge, SubLObject result_vector) {
        return set.set_add(edge, aref(result_vector, interval_span.interval_span_start(get_psp_edge_span(edge))));
    }

    /**
     * Add EDGE to RESULT-VECTOR.
     */
    @LispMethod(comment = "Add EDGE to RESULT-VECTOR.")
    public static SubLObject psp_add_edge_to_result_vector(final SubLObject edge, final SubLObject result_vector) {
        return set.set_add(edge, aref(result_vector, interval_span.interval_span_start(get_psp_edge_span(edge))));
    }

    public static final SubLObject psp_add_lexicon_edges_alt(SubLObject all_edges, SubLObject original_sentence, SubLObject lexicon) {
        if (lexicon == UNPROVIDED) {
            lexicon = psp_lexicon.get_default_psp_lexicon(UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        {
            SubLObject sentence = copy_sentence(original_sentence);
            SubLObject tokens = document.sentence_listify(sentence);
            SubLObject lexified_sentence = methods.funcall_instance_method_with_1_args(lexicon, LEXIFY_SENTENCE, sentence);
            SubLObject index = ZERO_INTEGER;
            SubLObject vector_var = document.sentence_yield(lexified_sentence);
            SubLObject backwardP_var = NIL;
            SubLObject length = length(vector_var);
            SubLObject v_iteration = NIL;
            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                {
                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                    SubLObject word = aref(vector_var, element_num);
                    SubLObject word_string = document.word_string(word);
                    SubLObject word_lex_entries = document.word_info(word);
                    SubLObject prefix_length = get_raw_word_count_from_word(word);
                    SubLObject start = index;
                    SubLObject end = add(index, prefix_length);
                    SubLObject prefix_words = subseq(tokens, start, end);
                    if (NIL != word_lex_entries) {
                        {
                            SubLObject cdolist_list_var = build_psp_edges_from_lex_entries(start, end, prefix_words, word_string, word_lex_entries);
                            SubLObject edge = NIL;
                            for (edge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , edge = cdolist_list_var.first()) {
                                psp_add_edge_to_result_vector(edge, all_edges);
                            }
                        }
                    }
                    index = add(index, prefix_length);
                }
            }
            return all_edges;
        }
    }

    public static SubLObject psp_add_lexicon_edges(final SubLObject all_edges, final SubLObject original_sentence, SubLObject lexicon) {
        if (lexicon == UNPROVIDED) {
            lexicon = psp_lexicon.get_default_psp_lexicon(UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        final SubLObject sentence = copy_sentence(original_sentence);
        final SubLObject tokens = document.sentence_listify(sentence);
        final SubLObject lexified_sentence = methods.funcall_instance_method_with_1_args(lexicon, LEXIFY_SENTENCE, sentence);
        SubLObject index = ZERO_INTEGER;
        SubLObject cdolist_list_var = document.sentence_yield_exhaustive(lexified_sentence);
        SubLObject word = NIL;
        word = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject word_string = document.word_string(word);
            final SubLObject word_lex_entries = document.word_info(word);
            final SubLObject prefix_length = get_raw_word_count_from_word(word);
            final SubLObject start = index;
            final SubLObject end = add(index, prefix_length);
            final SubLObject prefix_words = subseq(tokens, start, end);
            if (NIL != word_lex_entries) {
                SubLObject cdolist_list_var_$6 = build_psp_edges_from_lex_entries(start, end, prefix_words, word_string, word_lex_entries);
                SubLObject edge = NIL;
                edge = cdolist_list_var_$6.first();
                while (NIL != cdolist_list_var_$6) {
                    psp_add_edge_to_result_vector(edge, all_edges);
                    cdolist_list_var_$6 = cdolist_list_var_$6.rest();
                    edge = cdolist_list_var_$6.first();
                } 
            }
            index = add(index, prefix_length);
            cdolist_list_var = cdolist_list_var.rest();
            word = cdolist_list_var.first();
        } 
        return all_edges;
    }

    public static final SubLObject get_raw_word_count_from_word_alt(SubLObject word) {
        return length(psp_string_tokenize(document.word_string(word)));
    }

    public static SubLObject get_raw_word_count_from_word(final SubLObject word) {
        return length(psp_string_tokenize(document.word_string(word)));
    }

    /**
     *
     *
     * @return BOOLEANP; Do signs of CATEGORY have lists of CycL parses in their info?
     */
    @LispMethod(comment = "@return BOOLEANP; Do signs of CATEGORY have lists of CycL parses in their info?")
    public static final SubLObject fscp_multi_parse_category_p_alt(SubLObject category) {
        return makeBoolean(NIL != keyhash_utilities.fast_intersection(category, $fscp_multi_parse_categories$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    /**
     *
     *
     * @return BOOLEANP; Do signs of CATEGORY have lists of CycL parses in their info?
     */
    @LispMethod(comment = "@return BOOLEANP; Do signs of CATEGORY have lists of CycL parses in their info?")
    public static SubLObject fscp_multi_parse_category_p(final SubLObject category) {
        return makeBoolean(NIL != keyhash_utilities.fast_intersection(category, $fscp_multi_parse_categories$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject psp_add_fscp_edges_alt(SubLObject all_edges, SubLObject sentence, SubLObject v_parser, SubLObject pos, SubLObject pred, SubLObject filter_fn) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject fscp_constituents = NIL;
                SubLObject prefix_length = ZERO_INTEGER;
                SubLObject weight = $psp_edge_default_weight$.getGlobalValue();
                SubLObject parse_result = finite_state_cascade_parser.fscp_parse(v_parser, document.sentence_constituents(sentence));
                SubLObject index = NIL;
                SubLObject signs = NIL;
                SubLObject sign = NIL;
                for (index = ZERO_INTEGER, signs = (parse_result.isVector()) ? ((SubLObject) (vector_utilities.vector_elements(parse_result, UNPROVIDED))) : parse_result, sign = signs.first(); NIL != signs; index = add(index, prefix_length) , signs = signs.rest() , sign = signs.first()) {
                    if (NIL == document.sign_info(sign)) {
                        prefix_length = ONE_INTEGER;
                    } else {
                        fscp_constituents = document.sign_intervals(sign, index);
                        {
                            SubLObject cdolist_list_var = fscp_constituents;
                            SubLObject fscp_constituent = NIL;
                            for (fscp_constituent = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fscp_constituent = cdolist_list_var.first()) {
                                {
                                    SubLObject sign_6 = standard_tokenization.interval_token_value(fscp_constituent);
                                    SubLObject cdolist_list_var_7 = (NIL != fscp_multi_parse_category_p(document.sign_category(sign_6))) ? ((SubLObject) (document.sign_info(sign_6))) : list(document.sign_info(sign_6));
                                    SubLObject cycl = NIL;
                                    for (cycl = cdolist_list_var_7.first(); NIL != cdolist_list_var_7; cdolist_list_var_7 = cdolist_list_var_7.rest() , cycl = cdolist_list_var_7.first()) {
                                        if ((NIL != funcall(filter_fn, cycl)) && (NIL == Strings.stringE(document.sign_string(sign_6), $$$a, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                                            {
                                                SubLObject cycls = psp_new_cycls(cycl, UNPROVIDED);
                                                psp_add_edge_to_result_vector(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), interval_span.get_interval_span(standard_tokenization.interval_token_start(fscp_constituent), standard_tokenization.interval_token_end(fscp_constituent)), document.sign_string(sign_6), pos, pred, cycls, weight, UNPROVIDED, UNPROVIDED), all_edges);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        prefix_length = standard_tokenization.interval_token_length(fscp_constituents.first());
                    }
                }
            }
            return all_edges;
        }
    }

    public static SubLObject psp_add_fscp_edges(final SubLObject all_edges, final SubLObject sentence, final SubLObject v_parser, final SubLObject pos, final SubLObject pred, final SubLObject filter_fn) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject fscp_constituents = NIL;
        SubLObject prefix_length = ZERO_INTEGER;
        final SubLObject weight = $psp_edge_default_weight$.getGlobalValue();
        final SubLObject parse_result = finite_state_cascade_parser.fscp_parse(v_parser, document.sentence_constituents(sentence));
        SubLObject index = NIL;
        SubLObject signs = NIL;
        SubLObject sign = NIL;
        index = ZERO_INTEGER;
        signs = (parse_result.isVector()) ? vector_utilities.vector_elements(parse_result, UNPROVIDED) : parse_result;
        sign = signs.first();
        while (NIL != signs) {
            if (NIL == document.sign_info(sign)) {
                prefix_length = ONE_INTEGER;
            } else {
                SubLObject cdolist_list_var;
                fscp_constituents = cdolist_list_var = document.sign_intervals(sign, index);
                SubLObject fscp_constituent = NIL;
                fscp_constituent = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject sign_$7 = standard_tokenization.interval_token_value(fscp_constituent);
                    SubLObject cdolist_list_var_$8 = (NIL != fscp_multi_parse_category_p(document.sign_category(sign_$7))) ? document.sign_info(sign_$7) : list(document.sign_info(sign_$7));
                    SubLObject cycl = NIL;
                    cycl = cdolist_list_var_$8.first();
                    while (NIL != cdolist_list_var_$8) {
                        if ((NIL != funcall(filter_fn, cycl)) && (NIL == Strings.stringE(document.sign_string(sign_$7), $$$a, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                            final SubLObject cycls = psp_new_cycls(cycl, UNPROVIDED);
                            psp_add_edge_to_result_vector(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), interval_span.get_interval_span(standard_tokenization.interval_token_start(fscp_constituent), standard_tokenization.interval_token_end(fscp_constituent)), document.sign_string(sign_$7), pos, pred, cycls, weight, UNPROVIDED, UNPROVIDED), all_edges);
                        }
                        cdolist_list_var_$8 = cdolist_list_var_$8.rest();
                        cycl = cdolist_list_var_$8.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    fscp_constituent = cdolist_list_var.first();
                } 
                prefix_length = standard_tokenization.interval_token_length(fscp_constituents.first());
            }
            index = add(index, prefix_length);
            signs = signs.rest();
            sign = signs.first();
        } 
        return all_edges;
    }

    public static final SubLObject psp_sentence_copy_alt(SubLObject sentence) {
        SubLTrampolineFile.checkType(sentence, SENTENCE_P);
        {
            SubLObject new_sentence = document.new_sentence(UNPROVIDED, UNPROVIDED);
            document._csetf_sign_string(new_sentence, document.sentence_string(sentence));
            {
                SubLObject new_words = make_vector(length(document.sentence_constituents(sentence)), UNPROVIDED);
                SubLObject word_num = ZERO_INTEGER;
                SubLObject vector_var = document.sentence_yield(sentence);
                SubLObject backwardP_var = NIL;
                SubLObject length = length(vector_var);
                SubLObject v_iteration = NIL;
                for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    {
                        SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                        SubLObject word = aref(vector_var, element_num);
                        set_aref(new_words, word_num, psp_word_copy(word));
                        word_num = add(word_num, ONE_INTEGER);
                    }
                }
                document._csetf_sign_constituents(new_sentence, new_words);
            }
            document._csetf_sign_category(new_sentence, document.sentence_category(sentence));
            return new_sentence;
        }
    }

    public static SubLObject psp_sentence_copy(final SubLObject sentence) {
        assert NIL != document.sentence_p(sentence) : "! document.sentence_p(sentence) " + ("document.sentence_p(sentence) " + "CommonSymbols.NIL != document.sentence_p(sentence) ") + sentence;
        final SubLObject new_sentence = document.new_sentence(UNPROVIDED, UNPROVIDED);
        document._csetf_sign_string(new_sentence, document.sentence_string(sentence));
        final SubLObject new_words = make_vector(length(document.sentence_constituents(sentence)), UNPROVIDED);
        SubLObject word_num = ZERO_INTEGER;
        SubLObject cdolist_list_var = document.sentence_yield_exhaustive(sentence);
        SubLObject word = NIL;
        word = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            set_aref(new_words, word_num, psp_word_copy(word));
            word_num = add(word_num, ONE_INTEGER);
            cdolist_list_var = cdolist_list_var.rest();
            word = cdolist_list_var.first();
        } 
        document._csetf_sign_constituents(new_sentence, new_words);
        document._csetf_sign_category(new_sentence, document.sentence_category(sentence));
        return new_sentence;
    }

    public static final SubLObject psp_word_copy_alt(SubLObject word) {
        SubLTrampolineFile.checkType(word, WORD_P);
        {
            SubLObject new_word = document.new_word(UNPROVIDED);
            document._csetf_sign_string(new_word, document.word_string(word));
            document._csetf_sign_category(new_word, document.word_category(word));
            return new_word;
        }
    }

    public static SubLObject psp_word_copy(final SubLObject word) {
        assert NIL != document.word_p(word) : "! document.word_p(word) " + ("document.word_p(word) " + "CommonSymbols.NIL != document.word_p(word) ") + word;
        final SubLObject new_word = document.new_word(UNPROVIDED);
        document._csetf_sign_string(new_word, document.word_string(word));
        document._csetf_sign_category(new_word, document.word_category(word));
        return new_word;
    }

    public static final SubLObject psp_add_english_number_edges_alt(SubLObject all_edges, SubLObject sentence) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            psp_add_fscp_edges(all_edges, sentence, numeral_parser.$english_interval_parser$.getDynamicValue(thread), $$Number_SP, $$partOfSpeech, $sym216$PSP_SCALAR_INTERVAL_);
            return all_edges;
        }
    }

    public static SubLObject psp_add_english_number_edges(final SubLObject all_edges, final SubLObject sentence) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        psp_add_fscp_edges(all_edges, sentence, numeral_parser.$english_interval_parser$.getDynamicValue(thread), $$Number_SP, $$partOfSpeech, $sym226$PSP_SCALAR_INTERVAL_);
        return all_edges;
    }

    public static final SubLObject psp_add_digit_edges_alt(SubLObject all_edges, SubLObject sentence) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            psp_add_fscp_edges(all_edges, sentence, numeral_parser.$digit_interval_parser$.getDynamicValue(thread), $$Number_SP, $$partOfSpeech, $sym217$IBQE_);
            return all_edges;
        }
    }

    public static SubLObject psp_add_digit_edges(final SubLObject all_edges, final SubLObject sentence) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        psp_add_fscp_edges(all_edges, sentence, numeral_parser.$digit_interval_parser$.getDynamicValue(thread), $$Number_SP, $$partOfSpeech, $sym227$IBQE_);
        return all_edges;
    }

    public static final SubLObject psp_add_quantity_edges_alt(SubLObject all_edges, SubLObject sentence) {
        psp_add_fscp_edges(all_edges, sentence, english_quantity_parser.english_quantity_parser(), $$NounPhrase, $$simpleNounStrings, $sym216$PSP_SCALAR_INTERVAL_);
        return all_edges;
    }

    public static SubLObject psp_add_quantity_edges(final SubLObject all_edges, final SubLObject sentence) {
        psp_add_fscp_edges(all_edges, sentence, english_quantity_parser.english_quantity_parser(), $$NounPhrase, $$simpleNounStrings, $sym226$PSP_SCALAR_INTERVAL_);
        return all_edges;
    }

    /**
     * Add guessed edges for WORDS to ALL-EDGES.
     *
     * @unknown modifies ALL-EDGES.
     */
    @LispMethod(comment = "Add guessed edges for WORDS to ALL-EDGES.\r\n\r\n@unknown modifies ALL-EDGES.")
    public static final SubLObject psp_add_guessed_edges_alt(SubLObject all_edges, SubLObject words) {
        {
            SubLObject empty_starts = NIL;
            SubLObject cdotimes_end_var = length(words);
            SubLObject start = NIL;
            for (start = ZERO_INTEGER; start.numL(cdotimes_end_var); start = add(start, ONE_INTEGER)) {
                if (NIL == psp_edge_covering_startP(all_edges, start)) {
                    empty_starts = cons(start, empty_starts);
                    {
                        SubLObject guess_end = number_utilities.f_1X(start);
                        SubLObject guess_start = start;
                        while (NIL != member(guess_start, empty_starts, UNPROVIDED, UNPROVIDED)) {
                            {
                                SubLObject string = string_utilities.bunge(Mapping.mapcar(symbol_function(WORD_STRING), subseq(words, guess_start, guess_end)), UNPROVIDED);
                                psp_add_guessed_edge(guess_start, guess_end, all_edges, string);
                            }
                            guess_start = subtract(guess_start, ONE_INTEGER);
                        } 
                    }
                }
            }
        }
        return all_edges;
    }

    @LispMethod(comment = "Add guessed edges for WORDS to ALL-EDGES.\r\n\r\n@unknown modifies ALL-EDGES.")
    public static SubLObject psp_add_guessed_edges(final SubLObject all_edges, final SubLObject words) {
        SubLObject empty_starts = NIL;
        SubLObject cdotimes_end_var;
        SubLObject start;
        SubLObject guess_end;
        SubLObject guess_start;
        SubLObject string;
        for (cdotimes_end_var = length(words), start = NIL, start = ZERO_INTEGER; start.numL(cdotimes_end_var); start = add(start, ONE_INTEGER)) {
            if (NIL == psp_edge_covering_startP(all_edges, start)) {
                empty_starts = cons(start, empty_starts);
                guess_end = number_utilities.f_1X(start);
                for (guess_start = start; NIL != member(guess_start, empty_starts, UNPROVIDED, UNPROVIDED); guess_start = subtract(guess_start, ONE_INTEGER)) {
                    string = string_utilities.bunge(Mapping.mapcar(symbol_function(WORD_STRING), subseq(words, guess_start, guess_end)), UNPROVIDED);
                    psp_add_guessed_edge(guess_start, guess_end, all_edges, string);
                }
            }
        }
        return all_edges;
    }

    /**
     * Add a guessed edge with indices START and END to ALL-EDGES, with STRING.
     */
    @LispMethod(comment = "Add a guessed edge with indices START and END to ALL-EDGES, with STRING.")
    public static final SubLObject psp_add_guessed_edge_alt(SubLObject start, SubLObject end, SubLObject all_edges, SubLObject string) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject psp_guessed_word_weight = parsing_vars.$psp_max_weight$.getGlobalValue();
                thread.resetMultipleValues();
                {
                    SubLObject category = psp_maybe_guess_info_for_unknown_string(string, start, end);
                    SubLObject pos_pred = thread.secondMultipleValue();
                    SubLObject cycls = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    {
                        SubLObject guessed_edge = new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), interval_span.get_interval_span(start, end), string, category, pos_pred, cycls, psp_guessed_word_weight, UNPROVIDED, UNPROVIDED);
                        psp_add_edge_to_result_vector(guessed_edge, all_edges);
                    }
                }
            }
            return all_edges;
        }
    }

    @LispMethod(comment = "Add a guessed edge with indices START and END to ALL-EDGES, with STRING.")
    public static SubLObject psp_add_guessed_edge(final SubLObject start, final SubLObject end, final SubLObject all_edges, final SubLObject string) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject psp_guessed_word_weight = parsing_vars.$psp_max_weight$.getGlobalValue();
        thread.resetMultipleValues();
        final SubLObject category = psp_maybe_guess_info_for_unknown_string(string, start, end);
        final SubLObject pos_pred = thread.secondMultipleValue();
        final SubLObject cycls = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        final SubLObject guessed_edge = new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), interval_span.get_interval_span(start, end), string, category, pos_pred, cycls, psp_guessed_word_weight, UNPROVIDED, UNPROVIDED);
        psp_add_edge_to_result_vector(guessed_edge, all_edges);
        return all_edges;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGES contain an edge with a portion starting at START?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGES contain an edge with a portion starting at START?")
    public static final SubLObject psp_edge_covering_startP_alt(SubLObject edges, SubLObject start) {
        {
            SubLObject foundP = makeBoolean(NIL == set.set_emptyP(aref(edges, start)));
            if (NIL == foundP) {
                {
                    SubLObject csome_list_var = psp_spans_starting_at_start(start, UNPROVIDED);
                    SubLObject span = NIL;
                    for (span = csome_list_var.first(); !((NIL != foundP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , span = csome_list_var.first()) {
                        foundP = psp_span_has_edges_p(span, UNPROVIDED);
                    }
                }
            }
            {
                SubLObject lookup = NIL;
                for (lookup = number_utilities.f_1_(start); !((NIL != foundP) || lookup.numL(ZERO_INTEGER)); lookup = number_utilities.f_1_(lookup)) {
                    {
                        SubLObject set_contents_var = set.do_set_internal(aref(edges, lookup));
                        SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                        SubLObject state = NIL;
                        for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); !((NIL != foundP) || (NIL != set_contents.do_set_contents_doneP(basis_object, state))); state = set_contents.do_set_contents_update_state(state)) {
                            {
                                SubLObject edge = set_contents.do_set_contents_next(basis_object, state);
                                if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                                    foundP = numG(interval_span.interval_span_end(get_psp_edge_span(edge)), start);
                                }
                            }
                        }
                    }
                }
            }
            return foundP;
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGES contain an edge with a portion starting at START?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGES contain an edge with a portion starting at START?")
    public static SubLObject psp_edge_covering_startP(final SubLObject edges, final SubLObject start) {
        SubLObject foundP = makeBoolean(NIL == set.set_emptyP(aref(edges, start)));
        if (NIL == foundP) {
            SubLObject csome_list_var;
            SubLObject span;
            for (csome_list_var = psp_spans_starting_at_start(start, UNPROVIDED), span = NIL, span = csome_list_var.first(); (NIL == foundP) && (NIL != csome_list_var); foundP = psp_span_has_edges_p(span, UNPROVIDED) , csome_list_var = csome_list_var.rest() , span = csome_list_var.first()) {
            }
        }
        SubLObject lookup;
        SubLObject set_var;
        SubLObject set_contents_var;
        SubLObject basis_object;
        SubLObject state;
        SubLObject edge;
        for (lookup = NIL, lookup = number_utilities.f_1_(start); (NIL == foundP) && (!lookup.numL(ZERO_INTEGER)); lookup = number_utilities.f_1_(lookup)) {
            set_var = aref(edges, lookup);
            set_contents_var = set.do_set_internal(set_var);
            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); (NIL == foundP) && (NIL == set_contents.do_set_contents_doneP(basis_object, state)); state = set_contents.do_set_contents_update_state(state)) {
                edge = set_contents.do_set_contents_next(basis_object, state);
                if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                    foundP = numG(interval_span.interval_span_end(get_psp_edge_span(edge)), start);
                }
            }
        }
        return foundP;
    }

    /**
     *
     *
     * @return LISTP; of spans in CHART starting at START.
     */
    @LispMethod(comment = "@return LISTP; of spans in CHART starting at START.")
    public static final SubLObject psp_spans_starting_at_start_alt(SubLObject start, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return list_utilities.find_all(start, psp_chart_spans(chart, UNPROVIDED, UNPROVIDED), symbol_function($sym220$_), INTERVAL_SPAN_START);
    }

    /**
     *
     *
     * @return LISTP; of spans in CHART starting at START.
     */
    @LispMethod(comment = "@return LISTP; of spans in CHART starting at START.")
    public static SubLObject psp_spans_starting_at_start(final SubLObject start, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return list_utilities.find_all(start, psp_chart_spans(chart, UNPROVIDED, UNPROVIDED), symbol_function($sym230$_), INTERVAL_SPAN_START);
    }

    /**
     * Add indexical edges for WORDS to ALL-EDGES.
     *
     * @unknown modifies ALL-EDGES.
     */
    @LispMethod(comment = "Add indexical edges for WORDS to ALL-EDGES.\r\n\r\n@unknown modifies ALL-EDGES.")
    public static final SubLObject psp_add_indexical_edges_alt(SubLObject all_edges, SubLObject words) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject cdolist_list_var = parsing_vars.$psp_indexical_map$.getDynamicValue(thread);
                SubLObject cons = NIL;
                for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                    {
                        SubLObject datum = cons;
                        SubLObject current = datum;
                        SubLObject indexical_string = NIL;
                        SubLObject denot = NIL;
                        destructuring_bind_must_consp(current, datum, $list_alt222);
                        indexical_string = current.first();
                        current = current.rest();
                        denot = current;
                        {
                            SubLObject indexical_strings = psp_string_tokenize(indexical_string);
                            SubLObject start = NIL;
                            for (start = ZERO_INTEGER; NIL == list_utilities.lengthLE(words, start, UNPROVIDED); start = number_utilities.f_1X(start)) {
                                {
                                    SubLObject end = add(start, length(indexical_strings));
                                    if (Mapping.mapcar(symbol_function(WORD_STRING), subseq(words, start, end)).equalp(indexical_strings)) {
                                        {
                                            SubLObject span = interval_span.get_interval_span(start, end);
                                            SubLObject category = $$NounPhrase;
                                            SubLObject pos_pred = $$nameString;
                                            SubLObject cycls = psp_new_cycls(denot, UNPROVIDED);
                                            SubLObject indexical_edge = new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, indexical_string, category, pos_pred, cycls, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                            psp_add_edge_to_result_vector(indexical_edge, all_edges);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return all_edges;
        }
    }

    @LispMethod(comment = "Add indexical edges for WORDS to ALL-EDGES.\r\n\r\n@unknown modifies ALL-EDGES.")
    public static SubLObject psp_add_indexical_edges(final SubLObject all_edges, final SubLObject words) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject cdolist_list_var = parsing_vars.$psp_indexical_map$.getDynamicValue(thread);
        SubLObject cons = NIL;
        cons = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = cons;
            SubLObject indexical_string = NIL;
            SubLObject denot = NIL;
            destructuring_bind_must_consp(current, datum, $list232);
            indexical_string = current.first();
            current = denot = current.rest();
            final SubLObject indexical_strings = psp_string_tokenize(indexical_string);
            SubLObject start;
            SubLObject end;
            SubLObject span;
            SubLObject category;
            SubLObject pos_pred;
            SubLObject cycls;
            SubLObject indexical_edge;
            for (start = NIL, start = ZERO_INTEGER; NIL == list_utilities.lengthLE(words, start, UNPROVIDED); start = number_utilities.f_1X(start)) {
                end = add(start, length(indexical_strings));
                if (Mapping.mapcar(symbol_function(WORD_STRING), subseq(words, start, end)).equalp(indexical_strings)) {
                    span = interval_span.get_interval_span(start, end);
                    category = $$NounPhrase;
                    pos_pred = $$nameString;
                    cycls = psp_new_cycls(denot, UNPROVIDED);
                    indexical_edge = new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, indexical_string, category, pos_pred, cycls, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    psp_add_edge_to_result_vector(indexical_edge, all_edges);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            cons = cdolist_list_var.first();
        } 
        return all_edges;
    }

    /**
     * Guess syntactic and semantic info for the unknown string STRING
     */
    @LispMethod(comment = "Guess syntactic and semantic info for the unknown string STRING")
    public static final SubLObject psp_maybe_guess_info_for_unknown_string_alt(SubLObject string, SubLObject start, SubLObject end) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject category = NIL;
                SubLObject pos_pred = NIL;
                SubLObject cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
                if (NIL != parsing_vars.$guess_semantics_for_unknown_wordsP$.getDynamicValue(thread)) {
                    category = $$NounPhrase;
                    pos_pred = $$nameString;
                    {
                        SubLObject type = psp_type_for_unknown_string(string);
                        psp_add_cycl_to_cycls(list($$InstanceNamedFn, string, type), cycls, UNPROVIDED);
                    }
                }
                return values(category, pos_pred, cycls);
            }
        }
    }

    @LispMethod(comment = "Guess syntactic and semantic info for the unknown string STRING")
    public static SubLObject psp_maybe_guess_info_for_unknown_string(final SubLObject string, final SubLObject start, final SubLObject end) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject category = NIL;
        SubLObject pos_pred = NIL;
        final SubLObject cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
        if (NIL != parsing_vars.$guess_semantics_for_unknown_wordsP$.getDynamicValue(thread)) {
            category = $$NounPhrase;
            pos_pred = $$nameString;
            final SubLObject type = psp_type_for_unknown_string(string);
            psp_add_cycl_to_cycls(list($$InstanceNamedFn, string, type), cycls, UNPROVIDED);
        }
        return values(category, pos_pred, cycls);
    }

    public static final SubLObject psp_type_for_unknown_string_alt(SubLObject string) {
        return $$Thing;
    }

    public static SubLObject psp_type_for_unknown_string(final SubLObject string) {
        return $$Thing;
    }

    public static final SubLObject psp_scalar_intervalP_alt(SubLObject obj) {
        return makeBoolean(obj.isNumber() || (NIL != at_defns.quiet_has_typeP(obj, $$ScalarInterval, UNPROVIDED)));
    }

    public static SubLObject psp_scalar_intervalP(final SubLObject obj) {
        return makeBoolean(obj.isNumber() || (NIL != at_defns.quiet_has_typeP(obj, $$ScalarInterval, UNPROVIDED)));
    }

    /**
     * Returns a reduced version of PREFIXES, a list of lexical records, with only records
     * that are visible from MT
     */
    @LispMethod(comment = "Returns a reduced version of PREFIXES, a list of lexical records, with only records\r\nthat are visible from MT\nReturns a reduced version of PREFIXES, a list of lexical records, with only records\nthat are visible from MT")
    public static final SubLObject filter_prefixes_by_mt_alt(SubLObject prefixes, SubLObject mt) {
        {
            SubLObject filtered = NIL;
            SubLObject filtered_prefix = NIL;
            SubLObject cdolist_list_var = prefixes;
            SubLObject prefix = NIL;
            for (prefix = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , prefix = cdolist_list_var.first()) {
                filtered_prefix = nl_trie_accessors.nl_trie_record_filter_mt(prefix, mt);
                if (NIL != filtered_prefix) {
                    filtered = cons(filtered_prefix, filtered);
                }
            }
            return nreverse(filtered);
        }
    }

    @LispMethod(comment = "Returns a reduced version of PREFIXES, a list of lexical records, with only records\r\nthat are visible from MT\nReturns a reduced version of PREFIXES, a list of lexical records, with only records\nthat are visible from MT")
    public static SubLObject filter_prefixes_by_mt(final SubLObject prefixes, final SubLObject mt) {
        SubLObject filtered = NIL;
        SubLObject filtered_prefix = NIL;
        SubLObject cdolist_list_var = prefixes;
        SubLObject prefix = NIL;
        prefix = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            filtered_prefix = nl_trie_accessors.nl_trie_record_filter_mt(prefix, mt);
            if (NIL != filtered_prefix) {
                filtered = cons(filtered_prefix, filtered);
            }
            cdolist_list_var = cdolist_list_var.rest();
            prefix = cdolist_list_var.first();
        } 
        return nreverse(filtered);
    }

    public static final SubLObject psp_passive_marker_alt() {
        return $$passiveParticiple;
    }

    public static SubLObject psp_passive_marker() {
        return $$passiveParticiple;
    }

    /**
     *
     *
     * @return LISTP; of passive edges derivable from the input.
     */
    @LispMethod(comment = "@return LISTP; of passive edges derivable from the input.")
    public static final SubLObject psp_passive_edges_alt(SubLObject span, SubLObject token_string, SubLObject pos, SubLObject pos_pred, SubLObject cycls, SubLObject wu, SubLObject frame) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!((NIL != frame) && (pos_pred == $$perfect))) {
                return NIL;
            }
            {
                SubLObject new_edges = NIL;
                if (NIL != psp_frame_passivizableP(frame)) {
                    new_edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, token_string, pos, psp_passive_marker(), cycls, NIL, wu, list($PASSIVE, frame)), new_edges);
                    new_edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, token_string, pos, psp_passive_marker(), cycls, NIL, wu, list($PASSIVE_WITH_BY, frame)), new_edges);
                }
                return new_edges;
            }
        }
    }

    /**
     *
     *
     * @return LISTP; of passive edges derivable from the input.
     */
    @LispMethod(comment = "@return LISTP; of passive edges derivable from the input.")
    public static SubLObject psp_passive_edges(final SubLObject span, final SubLObject token_string, final SubLObject pos, final SubLObject pos_pred, final SubLObject cycls, final SubLObject wu, final SubLObject frame) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == frame) || (!pos_pred.eql($$perfect))) {
            return NIL;
        }
        SubLObject new_edges = NIL;
        if (NIL != psp_frame_passivizableP(frame)) {
            new_edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, token_string, pos, psp_passive_marker(), cycls, NIL, wu, list($PASSIVE, frame)), new_edges);
            new_edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, token_string, pos, psp_passive_marker(), cycls, NIL, wu, list($PASSIVE_WITH_BY, frame)), new_edges);
        }
        return new_edges;
    }

    /**
     *
     *
     * @return LISTP; of deverbal noun edges derivable from the input.
     */
    @LispMethod(comment = "@return LISTP; of deverbal noun edges derivable from the input.")
    public static final SubLObject psp_deverbal_edges_alt(SubLObject span, SubLObject token_string, SubLObject pos, SubLObject pos_pred, SubLObject cycls, SubLObject weight, SubLObject wu) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!(((NIL != wu) && (NIL != cycls)) && (NIL != deverbal_noun_posP(pos)))) {
                return NIL;
            }
            {
                SubLObject new_edges = NIL;
                if (NIL != find($$TransitiveNPFrame, lexicon_accessors.frames_of_wordXpos(wu, $$Verb), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                    new_edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, token_string, pos, pos_pred, cycls, weight, wu, narts_high.find_nart($list_alt233)), new_edges);
                }
                return new_edges;
            }
        }
    }

    /**
     *
     *
     * @return LISTP; of deverbal noun edges derivable from the input.
     */
    @LispMethod(comment = "@return LISTP; of deverbal noun edges derivable from the input.")
    public static SubLObject psp_deverbal_edges(final SubLObject span, final SubLObject token_string, final SubLObject pos, final SubLObject pos_pred, final SubLObject cycls, final SubLObject weight, final SubLObject wu) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (((NIL == wu) || (NIL == cycls)) || (NIL == deverbal_noun_posP(pos))) {
            return NIL;
        }
        SubLObject new_edges = NIL;
        if (NIL != find($$TransitiveNPFrame, lexicon_accessors.frames_of_wordXpos(wu, $$Verb), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
            new_edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, token_string, pos, pos_pred, cycls, weight, wu, narts_high.find_nart($list243)), new_edges);
        }
        return new_edges;
    }

    public static final SubLObject deverbal_noun_posP_alt(SubLObject pos) {
        return makeBoolean((NIL != psp_semantics.psp_genl_posP(pos, $$MassNoun, UNPROVIDED)) || (NIL != psp_semantics.psp_genl_posP(pos, $$GerundiveNoun, UNPROVIDED)));
    }

    public static SubLObject deverbal_noun_posP(final SubLObject pos) {
        return makeBoolean((NIL != psp_semantics.psp_genl_posP(pos, $$MassNoun, UNPROVIDED)) || (NIL != psp_semantics.psp_genl_posP(pos, $$GerundiveNoun, UNPROVIDED)));
    }

    /**
     *
     *
     * @return BOOLEAN; Is FRAME passivizable?
     */
    @LispMethod(comment = "@return BOOLEAN; Is FRAME passivizable?")
    public static final SubLObject psp_frame_passivizableP_alt(SubLObject frame) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject okP = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        okP = makeBoolean((NIL != forts.fort_p(frame)) && (NIL != isa.isaP(frame, $$PassivizableFrame, UNPROVIDED, UNPROVIDED)));
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return okP;
            }
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Is FRAME passivizable?
     */
    @LispMethod(comment = "@return BOOLEAN; Is FRAME passivizable?")
    public static SubLObject psp_frame_passivizableP(final SubLObject frame) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject okP = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            okP = makeBoolean((NIL != forts.fort_p(frame)) && (NIL != isa.isaP(frame, $$PassivizableFrame, UNPROVIDED, UNPROVIDED)));
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return okP;
    }

    /**
     *
     *
     * @return BOOLEAN; Is TOKEN-STRING a valid name form for CYCL?
     */
    @LispMethod(comment = "@return BOOLEAN; Is TOKEN-STRING a valid name form for CYCL?")
    public static final SubLObject psp_edge_cycl_passes_name_checkP_alt(SubLObject token_string, SubLObject cycl) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject exemptP = makeBoolean(NIL == indexed_term_p(cycl));
                SubLObject passP = exemptP;
                SubLObject eq_test = (NIL != lexicon_accessors.closed_lexical_class_stringP(token_string, UNPROVIDED)) ? ((SubLObject) (symbol_function(EQUAL))) : symbol_function(EQUALP);
                SubLObject unquoted_string = string_utilities.unquote_string(token_string);
                SubLObject precise_with_theP = NIL;
                thread.resetMultipleValues();
                {
                    SubLObject denots = lexicon_accessors.denots_of_name_string(token_string, lexicon_vars.$misspellingsP$.getDynamicValue(thread), parsing_utilities.psp_lexicon_root_mt(UNPROVIDED), precise_with_theP);
                    SubLObject pred_lists = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL == passP) {
                        {
                            SubLObject denot = NIL;
                            SubLObject denot_8 = NIL;
                            SubLObject pred_list = NIL;
                            SubLObject pred_list_9 = NIL;
                            for (denot = denots, denot_8 = denot.first(), pred_list = pred_lists, pred_list_9 = pred_list.first(); !((NIL != passP) || ((NIL == pred_list) && (NIL == denot))); denot = denot.rest() , denot_8 = denot.first() , pred_list = pred_list.rest() , pred_list_9 = pred_list.first()) {
                                if (denot_8.equalp(cycl)) {
                                    if (NIL == passP) {
                                        {
                                            SubLObject csome_list_var = pred_list_9;
                                            SubLObject pred = NIL;
                                            for (pred = csome_list_var.first(); !((NIL != passP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , pred = csome_list_var.first()) {
                                                {
                                                    SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
                                                    {
                                                        SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                                                        SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                                                        SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                                                        try {
                                                            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                                                            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                                                            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                                                            {
                                                                SubLObject asserted_strings = kb_mapping_utilities.pred_values(denot_8, pred, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                                if (NIL != member(unquoted_string, asserted_strings, eq_test, UNPROVIDED)) {
                                                                    passP = T;
                                                                } else {
                                                                    if (NIL != precise_with_theP) {
                                                                    } else {
                                                                        {
                                                                            SubLObject starts_with_theP = nl_trie_accessors.starts_with_theP(unquoted_string);
                                                                            SubLObject the_less_token_string = (NIL != starts_with_theP) ? ((SubLObject) (string_utilities.pre_remove(unquoted_string, $str_alt237$the_, symbol_function(EQUALP)))) : unquoted_string;
                                                                            if (NIL == passP) {
                                                                                {
                                                                                    SubLObject csome_list_var_10 = asserted_strings;
                                                                                    SubLObject asserted_string = NIL;
                                                                                    for (asserted_string = csome_list_var_10.first(); !((NIL != passP) || (NIL == csome_list_var_10)); csome_list_var_10 = csome_list_var_10.rest() , asserted_string = csome_list_var_10.first()) {
                                                                                        if (NIL != starts_with_theP) {
                                                                                            passP = funcall(eq_test, the_less_token_string, asserted_string);
                                                                                        } else {
                                                                                            passP = funcall(eq_test, unquoted_string, string_utilities.pre_remove(asserted_string, $str_alt237$the_, symbol_function(EQUALP)));
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
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return passP;
            }
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Is TOKEN-STRING a valid name form for CYCL?
     */
    @LispMethod(comment = "@return BOOLEAN; Is TOKEN-STRING a valid name form for CYCL?")
    public static SubLObject psp_edge_cycl_passes_name_checkP(final SubLObject token_string, final SubLObject cycl) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject passP;
        final SubLObject exemptP = passP = makeBoolean(NIL == indexed_term_p(cycl));
        final SubLObject eq_test = (NIL != lexicon_accessors.closed_lexical_class_stringP(token_string, UNPROVIDED)) ? symbol_function(EQUAL) : symbol_function(EQUALP);
        final SubLObject unquoted_string = string_utilities.unquote_string(token_string);
        final SubLObject precise_with_theP = NIL;
        thread.resetMultipleValues();
        final SubLObject denots = lexicon_accessors.denots_of_name_string(token_string, lexicon_vars.$misspellingsP$.getDynamicValue(thread), parsing_utilities.psp_lexicon_root_mt(UNPROVIDED), precise_with_theP);
        final SubLObject pred_lists = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL == passP) {
            SubLObject denot = NIL;
            SubLObject denot_$9 = NIL;
            SubLObject pred_list = NIL;
            SubLObject pred_list_$10 = NIL;
            denot = denots;
            denot_$9 = denot.first();
            pred_list = pred_lists;
            pred_list_$10 = pred_list.first();
            while ((NIL == passP) && ((NIL != pred_list) || (NIL != denot))) {
                if (denot_$9.equalp(cycl) && (NIL == passP)) {
                    SubLObject csome_list_var = pred_list_$10;
                    SubLObject pred = NIL;
                    pred = csome_list_var.first();
                    while ((NIL == passP) && (NIL != csome_list_var)) {
                        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
                        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                        try {
                            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                            final SubLObject asserted_strings = kb_mapping_utilities.pred_values(denot_$9, pred, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            if (NIL != member(unquoted_string, asserted_strings, eq_test, UNPROVIDED)) {
                                passP = T;
                            } else
                                if (NIL == precise_with_theP) {
                                    final SubLObject starts_with_theP = nl_trie_accessors.starts_with_theP(unquoted_string);
                                    final SubLObject the_less_token_string = (NIL != starts_with_theP) ? string_utilities.pre_remove(unquoted_string, $$$the_, symbol_function(EQUALP)) : unquoted_string;
                                    if (NIL == passP) {
                                        SubLObject csome_list_var_$11 = asserted_strings;
                                        SubLObject asserted_string = NIL;
                                        asserted_string = csome_list_var_$11.first();
                                        while ((NIL == passP) && (NIL != csome_list_var_$11)) {
                                            if (NIL != starts_with_theP) {
                                                passP = funcall(eq_test, the_less_token_string, asserted_string);
                                            } else {
                                                passP = funcall(eq_test, unquoted_string, string_utilities.pre_remove(asserted_string, $$$the_, symbol_function(EQUALP)));
                                            }
                                            csome_list_var_$11 = csome_list_var_$11.rest();
                                            asserted_string = csome_list_var_$11.first();
                                        } 
                                    }
                                }

                        } finally {
                            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
                            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
                            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                        }
                        csome_list_var = csome_list_var.rest();
                        pred = csome_list_var.first();
                    } 
                }
                denot = denot.rest();
                denot_$9 = denot.first();
                pred_list = pred_list.rest();
                pred_list_$10 = pred_list.first();
            } 
        }
        return passP;
    }

    public static final SubLObject string_has_non_name_denotsP_alt(SubLObject string) {
        return list_utilities.sublisp_boolean(lexicon_accessors.denots_of_string(string, NIL, $DENOT, NIL, T, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject string_has_non_name_denotsP(final SubLObject string) {
        return list_utilities.sublisp_boolean(lexicon_accessors.denots_of_string(string, NIL, $DENOT, NIL, T, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    /**
     * Add EDGE to CHART.
     */
    @LispMethod(comment = "Add EDGE to CHART.")
    public static final SubLObject psp_chart_add_edge_alt(SubLObject edge, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        {
            SubLObject span = get_psp_edge_span(edge);
            dictionary_utilities.dictionary_push(psp_chart_spans_to_edges(chart), span, edge);
        }
        if (NIL != psp_chart_index_edgesP()) {
            id_index_enter(psp_chart_edge_id_index(chart), get_psp_edge_id(edge), edge);
        }
        if (NIL != phrase_structure_parser_phrasal_edge_p(edge)) {
            if (NIL != psp_rules.psp_traced_ruleP(psp_edge_rule_fort(edge))) {
                format(T, $str_alt239$__Adding_new_edge_____S__Looking_, edge, get_psp_edge_new_dtr_constraints(edge));
            }
        }
        return $ADDED;
    }

    @LispMethod(comment = "Add EDGE to CHART.")
    public static SubLObject psp_chart_add_edge(final SubLObject edge, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        final SubLObject span = get_psp_edge_span(edge);
        dictionary_utilities.dictionary_push(psp_chart_spans_to_edges(chart), span, edge);
        if (NIL != psp_chart_index_edgesP()) {
            id_index_enter(psp_chart_edge_id_index(chart), get_psp_edge_id(edge), edge);
        }
        if ((NIL != phrase_structure_parser_phrasal_edge_p(edge)) && (NIL != psp_rules.psp_traced_ruleP(psp_edge_rule_fort(edge)))) {
            format(T, $str249$__Adding_new_edge_____S__Looking_, edge, get_psp_edge_new_dtr_constraints(edge));
        }
        return $ADDED;
    }

    /**
     * Find the #$SyntacticNode NODE that corresponds to EDGE-SET
     *
     * @return FORT-P or NOT-FOUND.
     */
    @LispMethod(comment = "Find the #$SyntacticNode NODE that corresponds to EDGE-SET\r\n\r\n@return FORT-P or NOT-FOUND.")
    public static final SubLObject psp_edge_set_lookup_node_alt(SubLObject edge_set, SubLObject not_found) {
        if (not_found == UNPROVIDED) {
            not_found = $NOT_FOUND;
        }
        {
            SubLObject node = NIL;
            SubLObject set_contents_var = set.do_set_internal(edge_set);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); !((NIL != node) || (NIL != set_contents.do_set_contents_doneP(basis_object, state))); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject edge = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                        node = psp_edge_lookup_node(edge, NIL);
                    }
                }
            }
            if (NIL != node) {
                psp_edge_set_note_node(edge_set, node);
            }
            return NIL != node ? ((SubLObject) (node)) : not_found;
        }
    }

    @LispMethod(comment = "Find the #$SyntacticNode NODE that corresponds to EDGE-SET\r\n\r\n@return FORT-P or NOT-FOUND.")
    public static SubLObject psp_edge_set_lookup_node(final SubLObject edge_set, SubLObject not_found) {
        if (not_found == UNPROVIDED) {
            not_found = $NOT_FOUND;
        }
        SubLObject node = NIL;
        final SubLObject set_contents_var = set.do_set_internal(edge_set);
        SubLObject basis_object;
        SubLObject state;
        SubLObject edge;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); (NIL == node) && (NIL == set_contents.do_set_contents_doneP(basis_object, state)); state = set_contents.do_set_contents_update_state(state)) {
            edge = set_contents.do_set_contents_next(basis_object, state);
            if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                node = psp_edge_lookup_node(edge, NIL);
            }
        }
        if (NIL != node) {
            psp_edge_set_note_node(edge_set, node);
        }
        return NIL != node ? node : not_found;
    }

    /**
     * Note that EDGE-SET corresponds to the #$SyntacticNode NODE.
     */
    @LispMethod(comment = "Note that EDGE-SET corresponds to the #$SyntacticNode NODE.")
    public static final SubLObject psp_edge_set_note_node_alt(SubLObject edge_set, SubLObject node) {
        {
            SubLObject set_contents_var = set.do_set_internal(edge_set);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject edge = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                        psp_edge_note_node(edge, node);
                    }
                }
            }
        }
        return node;
    }

    @LispMethod(comment = "Note that EDGE-SET corresponds to the #$SyntacticNode NODE.")
    public static SubLObject psp_edge_set_note_node(final SubLObject edge_set, final SubLObject node) {
        final SubLObject set_contents_var = set.do_set_internal(edge_set);
        SubLObject basis_object;
        SubLObject state;
        SubLObject edge;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            edge = set_contents.do_set_contents_next(basis_object, state);
            if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                psp_edge_note_node(edge, node);
            }
        }
        return node;
    }

    /**
     * Find the #$SyntacticNode NODE that corresponds to EDGE.
     *
     * @return FORT-P or NOT-FOUND.
     */
    @LispMethod(comment = "Find the #$SyntacticNode NODE that corresponds to EDGE.\r\n\r\n@return FORT-P or NOT-FOUND.")
    public static final SubLObject psp_edge_lookup_node_alt(SubLObject edge, SubLObject not_found) {
        if (not_found == UNPROVIDED) {
            not_found = $NOT_FOUND;
        }
        {
            SubLObject chart = get_psp_edge_chart(edge);
            SubLObject v_dictionary = psp_chart_edges_to_nodes(chart);
            return dictionary.dictionary_lookup(v_dictionary, edge, not_found);
        }
    }

    @LispMethod(comment = "Find the #$SyntacticNode NODE that corresponds to EDGE.\r\n\r\n@return FORT-P or NOT-FOUND.")
    public static SubLObject psp_edge_lookup_node(final SubLObject edge, SubLObject not_found) {
        if (not_found == UNPROVIDED) {
            not_found = $NOT_FOUND;
        }
        final SubLObject chart = get_psp_edge_chart(edge);
        final SubLObject v_dictionary = psp_chart_edges_to_nodes(chart);
        return dictionary.dictionary_lookup(v_dictionary, edge, not_found);
    }

    /**
     * Note that EDGE corresponds to the #$SyntacticNode NODE.
     */
    @LispMethod(comment = "Note that EDGE corresponds to the #$SyntacticNode NODE.")
    public static final SubLObject psp_edge_note_node_alt(SubLObject edge, SubLObject node) {
        {
            SubLObject chart = get_psp_edge_chart(edge);
            SubLObject v_dictionary = psp_chart_edges_to_nodes(chart);
            return dictionary.dictionary_enter(v_dictionary, edge, node);
        }
    }

    @LispMethod(comment = "Note that EDGE corresponds to the #$SyntacticNode NODE.")
    public static SubLObject psp_edge_note_node(final SubLObject edge, final SubLObject node) {
        final SubLObject chart = get_psp_edge_chart(edge);
        final SubLObject v_dictionary = psp_chart_edges_to_nodes(chart);
        return dictionary.dictionary_enter(v_dictionary, edge, node);
    }

    public static final SubLObject psp_edge_rule_struct_alt(SubLObject edge) {
        return get_psp_edge_rule(edge);
    }

    public static SubLObject psp_edge_rule_struct(final SubLObject edge) {
        return get_psp_edge_rule(edge);
    }

    public static final SubLObject psp_edge_rule_fort_alt(SubLObject edge) {
        {
            SubLObject rule = psp_edge_rule_struct(edge);
            return NIL != rule ? ((SubLObject) (psp_rules.psp_rule_fort(rule))) : NIL;
        }
    }

    public static SubLObject psp_edge_rule_fort(final SubLObject edge) {
        final SubLObject rule = psp_edge_rule_struct(edge);
        return NIL != rule ? psp_rules.psp_rule_fort(rule) : NIL;
    }

    /**
     *
     *
     * @param EDGES
     * 		set; a set of psp-edges
     * @return list; those edges in EDGES for which no longer edge in EDGES exists
     */
    @LispMethod(comment = "@param EDGES\r\n\t\tset; a set of psp-edges\r\n@return list; those edges in EDGES for which no longer edge in EDGES exists")
    public static final SubLObject psp_longest_edges_alt(SubLObject edges) {
        {
            SubLObject longest = NIL;
            SubLObject maxlength = ZERO_INTEGER;
            SubLObject length = NIL;
            SubLObject set_contents_var = set.do_set_internal(edges);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject edge = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                        length = interval_span.interval_span_length(get_psp_edge_span(edge));
                        if (length.eql(maxlength)) {
                            longest = cons(edge, longest);
                        } else {
                            if (length.numG(maxlength)) {
                                maxlength = length;
                                longest = list(edge);
                            }
                        }
                    }
                }
            }
            return longest;
        }
    }

    /**
     *
     *
     * @param EDGES
     * 		set; a set of psp-edges
     * @return list; those edges in EDGES for which no longer edge in EDGES exists
     */
    @LispMethod(comment = "@param EDGES\r\n\t\tset; a set of psp-edges\r\n@return list; those edges in EDGES for which no longer edge in EDGES exists")
    public static SubLObject psp_longest_edges(final SubLObject edges) {
        SubLObject longest = NIL;
        SubLObject maxlength = ZERO_INTEGER;
        SubLObject length = NIL;
        final SubLObject set_contents_var = set.do_set_internal(edges);
        SubLObject basis_object;
        SubLObject state;
        SubLObject edge;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            edge = set_contents.do_set_contents_next(basis_object, state);
            if (NIL != set_contents.do_set_contents_element_validP(state, edge)) {
                length = interval_span.interval_span_length(get_psp_edge_span(edge));
                if (length.eql(maxlength)) {
                    longest = cons(edge, longest);
                } else
                    if (length.numG(maxlength)) {
                        maxlength = length;
                        longest = list(edge);
                    }

            }
        }
        return longest;
    }

    /**
     *
     *
     * @unknown best is not always first, but it might be
     */
    @LispMethod(comment = "@unknown best is not always first, but it might be")
    public static final SubLObject best_cycl_for_edge_alt(SubLObject edge) {
        return psp_semantics.psp_cycl_cycl(psp_semantics.psp_semantics_for_edge(edge, UNPROVIDED, UNPROVIDED).first());
    }

    /**
     *
     *
     * @unknown best is not always first, but it might be
     */
    @LispMethod(comment = "@unknown best is not always first, but it might be")
    public static SubLObject best_cycl_for_edge(final SubLObject edge) {
        return psp_semantics.psp_cycl_cycl(psp_semantics.psp_semantics_for_edge(edge, UNPROVIDED, UNPROVIDED).first());
    }

    /**
     *
     *
     * @return LISTP of edges starting with SPAN
     */
    @LispMethod(comment = "@return LISTP of edges starting with SPAN")
    public static final SubLObject psp_edges_with_span_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return dictionary.dictionary_lookup_without_values(psp_chart_spans_to_edges(chart), span, UNPROVIDED);
    }

    /**
     *
     *
     * @return LISTP of edges starting with SPAN
     */
    @LispMethod(comment = "@return LISTP of edges starting with SPAN")
    public static SubLObject psp_edges_with_span(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return dictionary.dictionary_lookup_without_values(psp_chart_spans_to_edges(chart), span, UNPROVIDED);
    }

    /**
     *
     *
     * @return LISTP of lexical edges starting with SPAN
     */
    @LispMethod(comment = "@return LISTP of lexical edges starting with SPAN")
    public static final SubLObject psp_lexical_edges_with_span_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return list_utilities.remove_if_not($sym241$PSP_LEXICAL_EDGE_, psp_edges_with_span(span, chart), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return LISTP of lexical edges starting with SPAN
     */
    @LispMethod(comment = "@return LISTP of lexical edges starting with SPAN")
    public static SubLObject psp_lexical_edges_with_span(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return list_utilities.remove_if_not($sym251$PSP_LEXICAL_EDGE_, psp_edges_with_span(span, chart), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have all constituents required by its rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have all constituents required by its rule?")
    public static final SubLObject psp_completed_edgeP_alt(SubLObject edge) {
        if (NIL != psp_edge_might_have_dtrs_p(edge)) {
            return sublisp_null(get_psp_edge_new_dtr_constraints(edge));
        } else {
            return T;
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have all constituents required by its rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have all constituents required by its rule?")
    public static SubLObject psp_completed_edgeP(final SubLObject edge) {
        if (NIL != psp_edge_might_have_dtrs_p(edge)) {
            return sublisp_null(get_psp_edge_new_dtr_constraints(edge));
        }
        return T;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE not have all constituents required by its rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE not have all constituents required by its rule?")
    public static final SubLObject psp_incomplete_edgeP_alt(SubLObject edge) {
        return makeBoolean((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != get_psp_edge_new_dtr_constraints(edge)));
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE not have all constituents required by its rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE not have all constituents required by its rule?")
    public static SubLObject psp_incomplete_edgeP(final SubLObject edge) {
        return makeBoolean((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != get_psp_edge_new_dtr_constraints(edge)));
    }

    /**
     *
     *
     * @return BOOLEAN; Is EDGE derived from lexical lookup, as opposed to
    the product of a phrase-structure rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Is EDGE derived from lexical lookup, as opposed to\r\nthe product of a phrase-structure rule?")
    public static final SubLObject psp_lexical_edgeP_alt(SubLObject edge) {
        return phrase_structure_parser_lexical_edge_p(edge);
    }

    /**
     *
     *
     * @return BOOLEAN; Is EDGE derived from lexical lookup, as opposed to
    the product of a phrase-structure rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Is EDGE derived from lexical lookup, as opposed to\r\nthe product of a phrase-structure rule?")
    public static SubLObject psp_lexical_edgeP(final SubLObject edge) {
        return phrase_structure_parser_lexical_edge_p(edge);
    }

    public static final SubLObject psp_edge_might_have_dtrs_p_alt(SubLObject edge) {
        return phrase_structure_parser_phrasal_edge_p(edge);
    }

    public static SubLObject psp_edge_might_have_dtrs_p(final SubLObject edge) {
        return phrase_structure_parser_phrasal_edge_p(edge);
    }

    /**
     *
     *
     * @return BOOLEAN; Is EDGE the product of a phrase-structure rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Is EDGE the product of a phrase-structure rule?")
    public static final SubLObject psp_phrasal_edgeP_alt(SubLObject edge) {
        return phrase_structure_parser_phrasal_edge_p(edge);
    }

    /**
     *
     *
     * @return BOOLEAN; Is EDGE the product of a phrase-structure rule?
     */
    @LispMethod(comment = "@return BOOLEAN; Is EDGE the product of a phrase-structure rule?")
    public static SubLObject psp_phrasal_edgeP(final SubLObject edge) {
        return phrase_structure_parser_phrasal_edge_p(edge);
    }

    public static final SubLObject psp_adjp_edgeP_alt(SubLObject edge) {
        return makeBoolean(((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != psp_phrasal_edgeP(edge))) && (NIL != lexicon_accessors.adjpP(get_psp_edge_category(edge))));
    }

    public static SubLObject psp_adjp_edgeP(final SubLObject edge) {
        return makeBoolean(((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != psp_phrasal_edgeP(edge))) && (NIL != lexicon_accessors.adjpP(get_psp_edge_category(edge))));
    }

    public static final SubLObject psp_predicative_adjp_edgeP_alt(SubLObject edge) {
        if (NIL != psp_adjp_edgeP(edge)) {
            {
                SubLObject head_dtr = psp_edge_lexical_head_dtr(edge);
                SubLObject head_dtr_frame = (NIL != phrase_structure_parser_edge_p(head_dtr)) ? ((SubLObject) (get_psp_edge_frame(head_dtr))) : NIL;
                return psp_predicative_frameP(head_dtr_frame);
            }
        }
        return NIL;
    }

    public static SubLObject psp_predicative_adjp_edgeP(final SubLObject edge) {
        if (NIL != psp_adjp_edgeP(edge)) {
            final SubLObject head_dtr = psp_edge_lexical_head_dtr(edge);
            final SubLObject head_dtr_frame = (NIL != phrase_structure_parser_edge_p(head_dtr)) ? get_psp_edge_frame(head_dtr) : NIL;
            return psp_predicative_frameP(head_dtr_frame);
        }
        return NIL;
    }

    public static final SubLObject psp_vbar_edgeP_alt(SubLObject edge) {
        return makeBoolean((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != lexicon_accessors.vbarP(get_psp_edge_category(edge))));
    }

    public static SubLObject psp_vbar_edgeP(final SubLObject edge) {
        return makeBoolean((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != lexicon_accessors.vbarP(get_psp_edge_category(edge))));
    }

    public static final SubLObject psp_nbar_edgeP_alt(SubLObject edge) {
        return makeBoolean((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != lexicon_accessors.nbarP(get_psp_edge_category(edge))));
    }

    public static SubLObject psp_nbar_edgeP(final SubLObject edge) {
        return makeBoolean((NIL != psp_edge_might_have_dtrs_p(edge)) && (NIL != lexicon_accessors.nbarP(get_psp_edge_category(edge))));
    }

    public static final SubLObject psp_np_edgeP_alt(SubLObject edge) {
        return lexicon_accessors.npP(get_psp_edge_category(edge));
    }

    public static SubLObject psp_np_edgeP(final SubLObject edge) {
        return lexicon_accessors.npP(get_psp_edge_category(edge));
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE begin with a determiner phrase?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE begin with a determiner phrase?")
    public static final SubLObject psp_edge_starts_with_detpP_alt(SubLObject edge) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject ans = NIL;
                if ((NIL != psp_edge_might_have_dtrs_p(edge)) && psp_edge_dtr_count(edge).isPositive()) {
                    {
                        SubLObject left_dtr = psp_edge_nth_dtr(ONE_INTEGER, edge);
                        SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
                        {
                            SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                            SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                            SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                            try {
                                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                                ans = psp_semantics.psp_genl_posP(get_psp_edge_category(left_dtr), narts_high.find_nart($list_alt242), UNPROVIDED);
                            } finally {
                                mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                                mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
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
     * @return BOOLEAN; Does EDGE begin with a determiner phrase?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE begin with a determiner phrase?")
    public static SubLObject psp_edge_starts_with_detpP(final SubLObject edge) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject ans = NIL;
        if ((NIL != psp_edge_might_have_dtrs_p(edge)) && psp_edge_dtr_count(edge).isPositive()) {
            final SubLObject left_dtr = psp_edge_nth_dtr(ONE_INTEGER, edge);
            final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
            final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
            final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
            final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
            try {
                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                ans = psp_semantics.psp_genl_posP(get_psp_edge_category(left_dtr), narts_high.find_nart($list252), UNPROVIDED);
            } finally {
                mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
                mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
            }
        }
        return ans;
    }

    public static final SubLObject psp_pp_edgeP_alt(SubLObject edge) {
        return lexicon_accessors.ppP(get_psp_edge_category(edge));
    }

    public static SubLObject psp_pp_edgeP(final SubLObject edge) {
        return lexicon_accessors.ppP(get_psp_edge_category(edge));
    }

    public static final SubLObject psp_passive_edgeP_alt(SubLObject edge) {
        return eq(get_psp_edge_pos_pred(edge), psp_passive_marker());
    }

    public static SubLObject psp_passive_edgeP(final SubLObject edge) {
        return eq(get_psp_edge_pos_pred(edge), psp_passive_marker());
    }

    public static final SubLObject psp_edge_meets_pos_pred_constraintP_alt(SubLObject edge, SubLObject pos_pred) {
        {
            SubLObject actual_pred = get_psp_edge_pos_pred(edge);
            return makeBoolean((NIL == pos_pred) || (NIL != lexicon_accessors.genl_pos_predP(actual_pred, pos_pred, UNPROVIDED)));
        }
    }

    public static SubLObject psp_edge_meets_pos_pred_constraintP(final SubLObject edge, final SubLObject pos_pred) {
        final SubLObject actual_pred = get_psp_edge_pos_pred(edge);
        return makeBoolean((NIL == pos_pred) || (NIL != lexicon_accessors.genl_pos_predP(actual_pred, pos_pred, UNPROVIDED)));
    }

    public static final SubLObject first_edge_matching_pos_pred_alt(SubLObject edges, SubLObject pred) {
        {
            SubLObject ans = NIL;
            if (NIL == ans) {
                {
                    SubLObject csome_list_var = edges;
                    SubLObject edge = NIL;
                    for (edge = csome_list_var.first(); !((NIL != ans) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , edge = csome_list_var.first()) {
                        if (NIL != psp_edge_meets_pos_pred_constraintP(edge, pred)) {
                            ans = edge;
                        }
                    }
                }
            }
            return ans;
        }
    }

    public static SubLObject first_edge_matching_pos_pred(final SubLObject edges, final SubLObject pred) {
        SubLObject ans = NIL;
        if (NIL == ans) {
            SubLObject csome_list_var = edges;
            SubLObject edge = NIL;
            edge = csome_list_var.first();
            while ((NIL == ans) && (NIL != csome_list_var)) {
                if (NIL != psp_edge_meets_pos_pred_constraintP(edge, pred)) {
                    ans = edge;
                }
                csome_list_var = csome_list_var.rest();
                edge = csome_list_var.first();
            } 
        }
        return ans;
    }

    /**
     *
     *
     * @return LISTP; of constraints on the next daughter to be added to EDGE.
     */
    @LispMethod(comment = "@return LISTP; of constraints on the next daughter to be added to EDGE.")
    public static final SubLObject psp_edge_next_dtr_constraints_alt(SubLObject edge) {
        {
            SubLObject ndcs = get_psp_edge_new_dtr_constraints(edge);
            SubLObject pcase_var = ndcs;
            if (pcase_var.eql($UNKNOWN)) {
                return $UNKNOWN;
            } else {
                return ndcs.first();
            }
        }
    }

    /**
     *
     *
     * @return LISTP; of constraints on the next daughter to be added to EDGE.
     */
    @LispMethod(comment = "@return LISTP; of constraints on the next daughter to be added to EDGE.")
    public static SubLObject psp_edge_next_dtr_constraints(final SubLObject edge) {
        final SubLObject pcase_var;
        final SubLObject ndcs = pcase_var = get_psp_edge_new_dtr_constraints(edge);
        if (pcase_var.eql($UNKNOWN)) {
            return $UNKNOWN;
        }
        return ndcs.first();
    }

    /**
     *
     *
     * @return LISTP; of constraints on daughters after the next daughter to be added to EDGE.
     */
    @LispMethod(comment = "@return LISTP; of constraints on daughters after the next daughter to be added to EDGE.")
    public static final SubLObject psp_edge_later_dtr_constraints_alt(SubLObject edge) {
        {
            SubLObject ndcs = get_psp_edge_new_dtr_constraints(edge);
            SubLObject pcase_var = ndcs;
            if (pcase_var.eql($UNKNOWN)) {
                return $UNKNOWN;
            } else {
                return ndcs.rest();
            }
        }
    }

    /**
     *
     *
     * @return LISTP; of constraints on daughters after the next daughter to be added to EDGE.
     */
    @LispMethod(comment = "@return LISTP; of constraints on daughters after the next daughter to be added to EDGE.")
    public static SubLObject psp_edge_later_dtr_constraints(final SubLObject edge) {
        final SubLObject pcase_var;
        final SubLObject ndcs = pcase_var = get_psp_edge_new_dtr_constraints(edge);
        if (pcase_var.eql($UNKNOWN)) {
            return $UNKNOWN;
        }
        return ndcs.rest();
    }

    /**
     *
     *
     * @unknown the index of the head-dtr of EDGE, which is 1-indexed, not 0-indexed.
     */
    @LispMethod(comment = "@unknown the index of the head-dtr of EDGE, which is 1-indexed, not 0-indexed.")
    public static final SubLObject psp_edge_head_dtr_num_alt(SubLObject edge) {
        {
            SubLObject rule = psp_edge_rule_struct(edge);
            return NIL != rule ? ((SubLObject) (psp_rules.psp_rule_head(rule))) : NIL;
        }
    }

    /**
     *
     *
     * @unknown the index of the head-dtr of EDGE, which is 1-indexed, not 0-indexed.
     */
    @LispMethod(comment = "@unknown the index of the head-dtr of EDGE, which is 1-indexed, not 0-indexed.")
    public static SubLObject psp_edge_head_dtr_num(final SubLObject edge) {
        final SubLObject rule = psp_edge_rule_struct(edge);
        return NIL != rule ? psp_rules.psp_rule_head(rule) : NIL;
    }

    /**
     *
     *
     * @return INTEGERP; Number of immediate daughters of EDGE.
     */
    @LispMethod(comment = "@return INTEGERP; Number of immediate daughters of EDGE.")
    public static final SubLObject psp_edge_dtr_count_alt(SubLObject edge) {
        {
            SubLObject non_head_dtr_count = length(get_psp_edge_non_head_dtrs(edge));
            return NIL != get_psp_edge_head_dtr(edge) ? ((SubLObject) (number_utilities.f_1X(non_head_dtr_count))) : non_head_dtr_count;
        }
    }

    /**
     *
     *
     * @return INTEGERP; Number of immediate daughters of EDGE.
     */
    @LispMethod(comment = "@return INTEGERP; Number of immediate daughters of EDGE.")
    public static SubLObject psp_edge_dtr_count(final SubLObject edge) {
        final SubLObject non_head_dtr_count = length(get_psp_edge_non_head_dtrs(edge));
        return NIL != get_psp_edge_head_dtr(edge) ? number_utilities.f_1X(non_head_dtr_count) : non_head_dtr_count;
    }

    /**
     *
     *
     * @return LISTP; of PSP-EDGES, the daughters of EDGE.
     */
    @LispMethod(comment = "@return LISTP; of PSP-EDGES, the daughters of EDGE.")
    public static final SubLObject psp_edge_dtrs_alt(SubLObject edge) {
        {
            SubLObject head_dtr_num = psp_edge_head_dtr_num(edge);
            if (NIL == head_dtr_num) {
                return NIL;
            }
            {
                SubLObject head_dtr = get_psp_edge_head_dtr(edge);
                SubLObject non_head_dtrs = get_psp_edge_non_head_dtrs(edge);
                SubLObject all_dtrs = NIL;
                if (NIL != head_dtr) {
                    all_dtrs = append(list_utilities.first_n(number_utilities.f_1_(head_dtr_num), non_head_dtrs), cons(head_dtr, nthcdr(number_utilities.f_1_(head_dtr_num), non_head_dtrs)));
                } else {
                    all_dtrs = non_head_dtrs;
                }
                return all_dtrs;
            }
        }
    }

    /**
     *
     *
     * @return LISTP; of PSP-EDGES, the daughters of EDGE.
     */
    @LispMethod(comment = "@return LISTP; of PSP-EDGES, the daughters of EDGE.")
    public static SubLObject psp_edge_dtrs(final SubLObject edge) {
        final SubLObject head_dtr_num = psp_edge_head_dtr_num(edge);
        if (NIL == head_dtr_num) {
            return NIL;
        }
        final SubLObject head_dtr = get_psp_edge_head_dtr(edge);
        final SubLObject non_head_dtrs = get_psp_edge_non_head_dtrs(edge);
        SubLObject all_dtrs = NIL;
        if (NIL != head_dtr) {
            all_dtrs = append(list_utilities.first_n(number_utilities.f_1_(head_dtr_num), non_head_dtrs), cons(head_dtr, nthcdr(number_utilities.f_1_(head_dtr_num), non_head_dtrs)));
        } else {
            all_dtrs = non_head_dtrs;
        }
        return all_dtrs;
    }

    /**
     *
     *
     * @return BOOLEANP; Is there a non-branching path from MOTHER down to DTR?
     */
    @LispMethod(comment = "@return BOOLEANP; Is there a non-branching path from MOTHER down to DTR?")
    public static final SubLObject psp_edge_non_branching_descendentP_alt(SubLObject mother, SubLObject dtr) {
        if (!((NIL != phrase_structure_parser_edge_p(mother)) && (NIL != phrase_structure_parser_edge_p(dtr)))) {
            return NIL;
        }
        {
            SubLObject mothers_sole_dtr = psp_edge_sole_dtr(mother);
            if (NIL == mothers_sole_dtr) {
                return NIL;
            } else {
                if (dtr == mothers_sole_dtr) {
                    return T;
                } else {
                    if (NIL != psp_edge_non_branching_descendentP(mothers_sole_dtr, dtr)) {
                        return T;
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
     * @return BOOLEANP; Is there a non-branching path from MOTHER down to DTR?
     */
    @LispMethod(comment = "@return BOOLEANP; Is there a non-branching path from MOTHER down to DTR?")
    public static SubLObject psp_edge_non_branching_descendentP(final SubLObject mother, final SubLObject dtr) {
        if ((NIL == phrase_structure_parser_edge_p(mother)) || (NIL == phrase_structure_parser_edge_p(dtr))) {
            return NIL;
        }
        final SubLObject mothers_sole_dtr = psp_edge_sole_dtr(mother);
        if (NIL == mothers_sole_dtr) {
            return NIL;
        }
        if (dtr.eql(mothers_sole_dtr)) {
            return T;
        }
        if (NIL != psp_edge_non_branching_descendentP(mothers_sole_dtr, dtr)) {
            return T;
        }
        return NIL;
    }

    /**
     *
     *
     * @return PSP-EDGE or NIL; the semantic head dtr of EDGE.
     */
    @LispMethod(comment = "@return PSP-EDGE or NIL; the semantic head dtr of EDGE.")
    public static final SubLObject psp_edge_sem_head_dtr_alt(SubLObject edge) {
        {
            SubLObject rule_struct = psp_edge_rule_struct(edge);
            SubLObject rule_semx = psp_rules.psp_rule_semx(rule_struct);
            SubLObject semx_args = rule_semx.rest();
            SubLObject sem_head_dtr_num = semx_args.first();
            SubLObject sem_head_dtr = (sem_head_dtr_num.isInteger()) ? ((SubLObject) (psp_edge_nth_dtr(sem_head_dtr_num, edge))) : NIL;
            return sem_head_dtr;
        }
    }

    /**
     *
     *
     * @return PSP-EDGE or NIL; the semantic head dtr of EDGE.
     */
    @LispMethod(comment = "@return PSP-EDGE or NIL; the semantic head dtr of EDGE.")
    public static SubLObject psp_edge_sem_head_dtr(final SubLObject edge) {
        final SubLObject rule_struct = psp_edge_rule_struct(edge);
        final SubLObject rule_semx = psp_rules.psp_rule_semx(rule_struct);
        final SubLObject semx_args = rule_semx.rest();
        final SubLObject sem_head_dtr_num = semx_args.first();
        final SubLObject sem_head_dtr = (sem_head_dtr_num.isInteger()) ? psp_edge_nth_dtr(sem_head_dtr_num, edge) : NIL;
        return sem_head_dtr;
    }

    /**
     *
     *
     * @return PSP-EDGE; the lexical head daughter of EDGE
     */
    @LispMethod(comment = "@return PSP-EDGE; the lexical head daughter of EDGE")
    public static final SubLObject psp_edge_lexical_head_dtr_alt(SubLObject edge) {
        if ((NIL != psp_lexical_edgeP(edge)) || (NIL == get_psp_edge_head_dtr(edge))) {
            return edge;
        }
        {
            SubLObject head_dtr = get_psp_edge_head_dtr(edge);
            if (NIL == head_dtr) {
                return NIL;
            } else {
                return psp_edge_lexical_head_dtr(head_dtr);
            }
        }
    }

    /**
     *
     *
     * @return PSP-EDGE; the lexical head daughter of EDGE
     */
    @LispMethod(comment = "@return PSP-EDGE; the lexical head daughter of EDGE")
    public static SubLObject psp_edge_lexical_head_dtr(final SubLObject edge) {
        if ((NIL != psp_lexical_edgeP(edge)) || (NIL == get_psp_edge_head_dtr(edge))) {
            return edge;
        }
        final SubLObject head_dtr = get_psp_edge_head_dtr(edge);
        if (NIL == head_dtr) {
            return NIL;
        }
        return psp_edge_lexical_head_dtr(head_dtr);
    }

    /**
     *
     *
     * @return PSP-EDGE; the Nth daughter of EDGE.
     * @unknown 1-indexed.
     */
    @LispMethod(comment = "@return PSP-EDGE; the Nth daughter of EDGE.\r\n@unknown 1-indexed.")
    public static final SubLObject psp_edge_nth_dtr_alt(SubLObject n, SubLObject edge) {
        return nth(number_utilities.f_1_(n), psp_edge_dtrs(edge));
    }

    /**
     *
     *
     * @return PSP-EDGE; the Nth daughter of EDGE.
     * @unknown 1-indexed.
     */
    @LispMethod(comment = "@return PSP-EDGE; the Nth daughter of EDGE.\r\n@unknown 1-indexed.")
    public static SubLObject psp_edge_nth_dtr(final SubLObject n, final SubLObject edge) {
        return nth(number_utilities.f_1_(n), psp_edge_dtrs(edge));
    }

    public static final SubLObject psp_edge_sole_dtr_alt(SubLObject mother) {
        if (NIL == psp_edge_might_have_dtrs_p(mother)) {
            return NIL;
        }
        {
            SubLObject head_dtr = get_psp_edge_head_dtr(mother);
            return NIL != head_dtr ? ((SubLObject) (head_dtr)) : get_psp_edge_non_head_dtrs(mother).first();
        }
    }

    public static SubLObject psp_edge_sole_dtr(final SubLObject mother) {
        if (NIL == psp_edge_might_have_dtrs_p(mother)) {
            return NIL;
        }
        final SubLObject head_dtr = get_psp_edge_head_dtr(mother);
        return NIL != head_dtr ? head_dtr : get_psp_edge_non_head_dtrs(mother).first();
    }

    public static final SubLObject psp_edge_precedesP_alt(SubLObject edge1, SubLObject edge2) {
        return numL(interval_span.interval_span_start(get_psp_edge_span(edge1)), interval_span.interval_span_start(get_psp_edge_span(edge2)));
    }

    public static SubLObject psp_edge_precedesP(final SubLObject edge1, final SubLObject edge2) {
        return numL(interval_span.interval_span_start(get_psp_edge_span(edge1)), interval_span.interval_span_start(get_psp_edge_span(edge2)));
    }

    /**
     *
     *
     * @return BOOLEAN; Are EDGE1 and EDGE2 indistinguishable?
     */
    @LispMethod(comment = "@return BOOLEAN; Are EDGE1 and EDGE2 indistinguishable?")
    public static final SubLObject psp_edge_equalP_alt(SubLObject edge1, SubLObject edge2) {
        return makeBoolean(((get_psp_edge_span(edge1).equalp(get_psp_edge_span(edge2)) && get_psp_edge_category(edge1).equalp(get_psp_edge_category(edge2))) && get_psp_edge_pos_pred(edge1).equalp(get_psp_edge_pos_pred(edge2))) && get_psp_edge_cycls(edge1).equalp(get_psp_edge_cycls(edge2)));
    }

    /**
     *
     *
     * @return BOOLEAN; Are EDGE1 and EDGE2 indistinguishable?
     */
    @LispMethod(comment = "@return BOOLEAN; Are EDGE1 and EDGE2 indistinguishable?")
    public static SubLObject psp_edge_equalP(final SubLObject edge1, final SubLObject edge2) {
        return makeBoolean(((get_psp_edge_span(edge1).equalp(get_psp_edge_span(edge2)) && get_psp_edge_category(edge1).equalp(get_psp_edge_category(edge2))) && get_psp_edge_pos_pred(edge1).equalp(get_psp_edge_pos_pred(edge2))) && get_psp_edge_cycls(edge1).equalp(get_psp_edge_cycls(edge2)));
    }

    public static final SubLObject psp_edge_start_index_alt(SubLObject edge) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return subtract(interval_span.interval_span_start(get_psp_edge_span(edge)), parsing_vars.$psp_chart_start_index$.getDynamicValue(thread));
        }
    }

    public static SubLObject psp_edge_start_index(final SubLObject edge) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return subtract(interval_span.interval_span_start(get_psp_edge_span(edge)), parsing_vars.$psp_chart_start_index$.getDynamicValue(thread));
    }

    public static final SubLObject psp_edge_end_index_alt(SubLObject edge) {
        return interval_span.interval_span_end(get_psp_edge_span(edge));
    }

    public static SubLObject psp_edge_end_index(final SubLObject edge) {
        return interval_span.interval_span_end(get_psp_edge_span(edge));
    }

    public static final SubLObject psp_chart_max_span_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject start_index = parsing_vars.$psp_chart_start_index$.getDynamicValue(thread);
                SubLObject end_index = (NIL != parsing_vars.$psp_chart_end_index$.getDynamicValue(thread)) ? ((SubLObject) (parsing_vars.$psp_chart_end_index$.getDynamicValue(thread))) : psp_chart_length(parsing_vars.$psp_chart$.getDynamicValue(thread));
                return interval_span.get_interval_span(start_index, end_index);
            }
        }
    }

    public static SubLObject psp_chart_max_span() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject start_index = parsing_vars.$psp_chart_start_index$.getDynamicValue(thread);
        final SubLObject end_index = (NIL != parsing_vars.$psp_chart_end_index$.getDynamicValue(thread)) ? parsing_vars.$psp_chart_end_index$.getDynamicValue(thread) : psp_chart_length(parsing_vars.$psp_chart$.getDynamicValue(thread));
        return interval_span.get_interval_span(start_index, end_index);
    }

    public static final SubLObject psp_all_complete_edges_alt(SubLObject non_trivialP) {
        if (non_trivialP == UNPROVIDED) {
            non_trivialP = T;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject edges = NIL;
                SubLObject chart_to_use = (NIL != parsing_vars.$psp_chart$.getDynamicValue(thread)) ? ((SubLObject) (parsing_vars.$psp_chart$.getDynamicValue(thread))) : parsing_vars.$psp_chart$.getDynamicValue(thread);
                SubLTrampolineFile.checkType(chart_to_use, PHRASE_STRUCTURE_PARSER_CHART_P);
                {
                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(psp_chart_spans_to_edges(chart_to_use)));
                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                        thread.resetMultipleValues();
                        {
                            SubLObject span = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                            SubLObject value = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if ((NIL == non_trivialP) || interval_span.interval_span_length(span).numG(ONE_INTEGER)) {
                                edges = nconc(edges, psp_complete_edges_for_span(span));
                            }
                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                        }
                    } 
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                }
                return NIL != non_trivialP ? ((SubLObject) (remove_if($sym241$PSP_LEXICAL_EDGE_, edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) : edges;
            }
        }
    }

    public static SubLObject psp_all_complete_edges(SubLObject non_trivialP) {
        if (non_trivialP == UNPROVIDED) {
            non_trivialP = T;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject edges = NIL;
        final SubLObject chart_to_use = (NIL != parsing_vars.$psp_chart$.getDynamicValue(thread)) ? parsing_vars.$psp_chart$.getDynamicValue(thread) : parsing_vars.$psp_chart$.getDynamicValue(thread);
        assert NIL != phrase_structure_parser_chart_p(chart_to_use) : "! psp_chart.phrase_structure_parser_chart_p(chart_to_use) " + ("psp_chart.phrase_structure_parser_chart_p(chart_to_use) " + "CommonSymbols.NIL != psp_chart.phrase_structure_parser_chart_p(chart_to_use) ") + chart_to_use;
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(psp_chart_spans_to_edges(chart_to_use))); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject span = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject value = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if ((NIL == non_trivialP) || interval_span.interval_span_length(span).numG(ONE_INTEGER)) {
                edges = nconc(edges, psp_complete_edges_for_span(span));
            }
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return NIL != non_trivialP ? remove_if($sym251$PSP_LEXICAL_EDGE_, edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED) : edges;
    }

    /**
     *
     *
     * @return LISTP; of input-spanning edges in CHART
     */
    @LispMethod(comment = "@return LISTP; of input-spanning edges in CHART")
    public static final SubLObject psp_input_spanning_edges_alt(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject ans = NIL;
                {
                    SubLObject _prev_bind_0 = parsing_vars.$psp_chart$.currentBinding(thread);
                    try {
                        parsing_vars.$psp_chart$.bind(chart, thread);
                        ans = psp_complete_edges_for_span(psp_chart_max_span());
                    } finally {
                        parsing_vars.$psp_chart$.rebind(_prev_bind_0, thread);
                    }
                }
                return ans;
            }
        }
    }

    /**
     *
     *
     * @return LISTP; of input-spanning edges in CHART
     */
    @LispMethod(comment = "@return LISTP; of input-spanning edges in CHART")
    public static SubLObject psp_input_spanning_edges(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject ans = NIL;
        final SubLObject _prev_bind_0 = parsing_vars.$psp_chart$.currentBinding(thread);
        try {
            parsing_vars.$psp_chart$.bind(chart, thread);
            ans = psp_complete_edges_for_span(psp_chart_max_span());
        } finally {
            parsing_vars.$psp_chart$.rebind(_prev_bind_0, thread);
        }
        return ans;
    }

    public static final SubLObject psp_complete_edges_for_span_alt(SubLObject span) {
        {
            SubLObject all_edges = psp_edges_with_span(span, UNPROVIDED);
            SubLObject complete_edges = remove_if($sym243$PSP_INCOMPLETE_EDGE_, all_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            return complete_edges;
        }
    }

    public static SubLObject psp_complete_edges_for_span(final SubLObject span) {
        final SubLObject all_edges = psp_edges_with_span(span, UNPROVIDED);
        final SubLObject complete_edges = remove_if($sym253$PSP_INCOMPLETE_EDGE_, all_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        return complete_edges;
    }

    /**
     *
     *
     * @return INTEGERP; Count of all edges in CHART
     */
    @LispMethod(comment = "@return INTEGERP; Count of all edges in CHART")
    public static final SubLObject psp_edge_count_alt(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (NIL == phrase_structure_parser_chart_p(chart)) {
            return ZERO_INTEGER;
        } else {
            if (NIL != psp_chart_index_edgesP()) {
                return id_index_count(psp_chart_edge_id_index(chart));
            } else {
                return psp_chart_edge_id_index(chart);
            }
        }
    }

    /**
     *
     *
     * @return INTEGERP; Count of all edges in CHART
     */
    @LispMethod(comment = "@return INTEGERP; Count of all edges in CHART")
    public static SubLObject psp_edge_count(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        if (NIL == phrase_structure_parser_chart_p(chart)) {
            return ZERO_INTEGER;
        }
        if (NIL != psp_chart_index_edgesP()) {
            return id_index_count(psp_chart_edge_id_index(chart));
        }
        return psp_chart_edge_id_index(chart);
    }

    /**
     *
     *
     * @return BOOLEANP; Non-NIL iff CHART contains a span for which there is no subsuming
    span with semantics.
     */
    @LispMethod(comment = "@return BOOLEANP; Non-NIL iff CHART contains a span for which there is no subsuming\r\nspan with semantics.")
    public static final SubLObject psp_chart_has_unknown_stringsP_alt(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(chart, PHRASE_STRUCTURE_PARSER_CHART_P);
            {
                SubLObject unknownP = NIL;
                if (NIL == unknownP) {
                    {
                        SubLObject end_var = psp_chart_length(chart);
                        SubLObject i = NIL;
                        for (i = ZERO_INTEGER; !((NIL != unknownP) || i.numGE(end_var)); i = number_utilities.f_1X(i)) {
                            {
                                SubLObject ispan = interval_span.get_interval_span(i, number_utilities.f_1X(i));
                                SubLObject knownP = NIL;
                                SubLObject chart_to_use = (NIL != chart) ? ((SubLObject) (chart)) : parsing_vars.$psp_chart$.getDynamicValue(thread);
                                SubLTrampolineFile.checkType(chart_to_use, PHRASE_STRUCTURE_PARSER_CHART_P);
                                {
                                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(psp_chart_spans_to_edges(chart_to_use)));
                                    while (!((NIL != knownP) || (NIL != dictionary_contents.do_dictionary_contents_doneP(iteration_state)))) {
                                        thread.resetMultipleValues();
                                        {
                                            SubLObject span = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                            SubLObject value = thread.secondMultipleValue();
                                            thread.resetMultipleValues();
                                            if (NIL != interval_span.interval_span_subsumesP(span, ispan)) {
                                                if (NIL == knownP) {
                                                    {
                                                        SubLObject csome_list_var = psp_edges_with_span(span, chart);
                                                        SubLObject edge = NIL;
                                                        for (edge = csome_list_var.first(); !((NIL != knownP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , edge = csome_list_var.first()) {
                                                            if (NIL != psp_do_edge_okP(edge, $ANY, T)) {
                                                                if (NIL == dictionary.dictionary_empty_p(get_psp_edge_cycls(edge))) {
                                                                    knownP = T;
                                                                }
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
                                unknownP = makeBoolean(NIL == knownP);
                            }
                        }
                    }
                }
                return unknownP;
            }
        }
    }

    /**
     *
     *
     * @return BOOLEANP; Non-NIL iff CHART contains a span for which there is no subsuming
    span with semantics.
     */
    @LispMethod(comment = "@return BOOLEANP; Non-NIL iff CHART contains a span for which there is no subsuming\r\nspan with semantics.")
    public static SubLObject psp_chart_has_unknown_stringsP(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != phrase_structure_parser_chart_p(chart) : "! psp_chart.phrase_structure_parser_chart_p(chart) " + ("psp_chart.phrase_structure_parser_chart_p(chart) " + "CommonSymbols.NIL != psp_chart.phrase_structure_parser_chart_p(chart) ") + chart;
        SubLObject unknownP = NIL;
        if (NIL == unknownP) {
            SubLObject end_var;
            SubLObject i;
            SubLObject ispan;
            SubLObject knownP;
            SubLObject chart_to_use;
            SubLObject iteration_state;
            SubLObject span;
            SubLObject value;
            SubLObject csome_list_var;
            SubLObject edge;
            for (end_var = psp_chart_length(chart), i = NIL, i = ZERO_INTEGER; (NIL == unknownP) && (!i.numGE(end_var)); unknownP = makeBoolean(NIL == knownP) , i = number_utilities.f_1X(i)) {
                ispan = interval_span.get_interval_span(i, number_utilities.f_1X(i));
                knownP = NIL;
                chart_to_use = (NIL != chart) ? chart : parsing_vars.$psp_chart$.getDynamicValue(thread);
                assert NIL != phrase_structure_parser_chart_p(chart_to_use) : "! psp_chart.phrase_structure_parser_chart_p(chart_to_use) " + ("psp_chart.phrase_structure_parser_chart_p(chart_to_use) " + "CommonSymbols.NIL != psp_chart.phrase_structure_parser_chart_p(chart_to_use) ") + chart_to_use;
                for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(psp_chart_spans_to_edges(chart_to_use))); (NIL == knownP) && (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                    thread.resetMultipleValues();
                    span = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                    value = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if ((NIL != interval_span.interval_span_subsumesP(span, ispan)) && (NIL == knownP)) {
                        csome_list_var = psp_edges_with_span(span, chart);
                        edge = NIL;
                        edge = csome_list_var.first();
                        while ((NIL == knownP) && (NIL != csome_list_var)) {
                            if ((NIL != psp_do_edge_okP(edge, $ANY, T)) && (NIL == dictionary.dictionary_empty_p(get_psp_edge_cycls(edge)))) {
                                knownP = T;
                            }
                            csome_list_var = csome_list_var.rest();
                            edge = csome_list_var.first();
                        } 
                    }
                }
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
            }
        }
        return unknownP;
    }

    /**
     *
     *
     * @return INTEGERP; How many edges does CHART have for SPAN?
     */
    @LispMethod(comment = "@return INTEGERP; How many edges does CHART have for SPAN?")
    public static final SubLObject psp_edge_count_for_span_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return length(psp_edges_with_span(span, chart));
    }

    /**
     *
     *
     * @return INTEGERP; How many edges does CHART have for SPAN?
     */
    @LispMethod(comment = "@return INTEGERP; How many edges does CHART have for SPAN?")
    public static SubLObject psp_edge_count_for_span(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return length(psp_edges_with_span(span, chart));
    }

    /**
     *
     *
     * @return BOOLEANP; Does CHART contain any edges for SPAN?
     */
    @LispMethod(comment = "@return BOOLEANP; Does CHART contain any edges for SPAN?")
    public static final SubLObject psp_span_has_edges_p_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        {
            SubLObject edge_count = psp_edge_count_for_span(span, chart);
            return edge_count.isPositive() ? ((SubLObject) (edge_count)) : NIL;
        }
    }

    /**
     *
     *
     * @return BOOLEANP; Does CHART contain any edges for SPAN?
     */
    @LispMethod(comment = "@return BOOLEANP; Does CHART contain any edges for SPAN?")
    public static SubLObject psp_span_has_edges_p(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        final SubLObject edge_count = psp_edge_count_for_span(span, chart);
        return edge_count.isPositive() ? edge_count : NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Do we have too many edges for CHART?
     */
    @LispMethod(comment = "@return BOOLEAN; Do we have too many edges for CHART?")
    public static final SubLObject psp_chart_too_many_edgesP_alt(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        {
            SubLObject limit = psp_chart_max_edges(chart);
            SubLObject overP = makeBoolean((!limit.isInteger()) || psp_edge_count(chart).numG(limit));
            if (NIL != overP) {
                Errors.warn($str_alt244$__Too_many_edges_in_PSP_chart_for, psp_chart_input_string(chart), limit, psp_edge_count(chart));
            }
            return overP;
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Do we have too many edges for CHART?
     */
    @LispMethod(comment = "@return BOOLEAN; Do we have too many edges for CHART?")
    public static SubLObject psp_chart_too_many_edgesP(SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        final SubLObject limit = psp_chart_max_edges(chart);
        final SubLObject overP = makeBoolean((!limit.isInteger()) || psp_edge_count(chart).numG(limit));
        if (NIL != overP) {
        }
        return overP;
    }

    /**
     *
     *
     * @return BOOLEAN; Do we have too many edges for SPAN?
     */
    @LispMethod(comment = "@return BOOLEAN; Do we have too many edges for SPAN?")
    public static final SubLObject psp_span_too_many_edgesP_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return makeBoolean((NIL != set.set_memberP(span, psp_chart_maxed_out_spans(chart))) || (NIL != psp_span_too_many_edgesP_int(span, chart)));
    }

    /**
     *
     *
     * @return BOOLEAN; Do we have too many edges for SPAN?
     */
    @LispMethod(comment = "@return BOOLEAN; Do we have too many edges for SPAN?")
    public static SubLObject psp_span_too_many_edgesP(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return makeBoolean((NIL != set.set_memberP(span, psp_chart_maxed_out_spans(chart))) || (NIL != psp_span_too_many_edgesP_int(span, chart)));
    }

    /**
     *
     *
     * @return BOOLEAN; Do we have too many edges for SPAN?
     */
    @LispMethod(comment = "@return BOOLEAN; Do we have too many edges for SPAN?")
    public static final SubLObject psp_span_too_many_edgesP_int_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        {
            SubLObject limit = psp_chart_max_edges_per_span(chart);
            SubLObject overP = makeBoolean((!limit.isInteger()) || psp_edge_count_for_span(span, chart).numG(limit));
            if (NIL != overP) {
                set.set_add(span, psp_chart_maxed_out_spans(chart));
                Errors.warn($str_alt245$__Too_many_edges_for_span__S_in_P, new SubLObject[]{ span, psp_chart_input_string(chart), limit, psp_edge_count_for_span(span, chart) });
            }
            return overP;
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Do we have too many edges for SPAN?
     */
    @LispMethod(comment = "@return BOOLEAN; Do we have too many edges for SPAN?")
    public static SubLObject psp_span_too_many_edgesP_int(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        final SubLObject limit = psp_chart_max_edges_per_span(chart);
        final SubLObject overP = makeBoolean((!limit.isInteger()) || psp_edge_count_for_span(span, chart).numG(limit));
        if (NIL != overP) {
            set.set_add(span, psp_chart_maxed_out_spans(chart));
        }
        return overP;
    }

    public static final SubLObject psp_lexical_span_parsedP_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return set.set_memberP(span, psp_chart_parsed_lexical_spans(chart));
    }

    public static SubLObject psp_lexical_span_parsedP(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return set.set_memberP(span, psp_chart_parsed_lexical_spans(chart));
    }

    public static final SubLObject note_psp_lexical_span_parsed_alt(SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return set.set_add(span, psp_chart_parsed_lexical_spans(chart));
    }

    public static SubLObject note_psp_lexical_span_parsed(final SubLObject span, SubLObject chart) {
        if (chart == UNPROVIDED) {
            chart = parsing_vars.$psp_chart$.getDynamicValue();
        }
        return set.set_add(span, psp_chart_parsed_lexical_spans(chart));
    }

    /**
     *
     *
     * @return LISTP; of all NP edges in *PSP-CHART*
     */
    @LispMethod(comment = "@return LISTP; of all NP edges in *PSP-CHART*")
    public static final SubLObject psp_np_edges_alt() {
        {
            SubLObject edges = psp_input_spanning_edges(UNPROVIDED);
            SubLObject comp_edges = remove_if($sym243$PSP_INCOMPLETE_EDGE_, edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject np_edges = list_utilities.remove_if_not($sym246$PSP_NP_EDGE_, comp_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            return np_edges;
        }
    }

    /**
     *
     *
     * @return LISTP; of all NP edges in *PSP-CHART*
     */
    @LispMethod(comment = "@return LISTP; of all NP edges in *PSP-CHART*")
    public static SubLObject psp_np_edges() {
        final SubLObject edges = psp_input_spanning_edges(UNPROVIDED);
        final SubLObject comp_edges = remove_if($sym253$PSP_INCOMPLETE_EDGE_, edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject np_edges = list_utilities.remove_if_not($sym254$PSP_NP_EDGE_, comp_edges, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        return np_edges;
    }

    /**
     *
     *
     * @return INTEGERP; How many input-spanning edges are there in *PSP-CHART*?
     */
    @LispMethod(comment = "@return INTEGERP; How many input-spanning edges are there in *PSP-CHART*?")
    public static final SubLObject psp_input_spanning_edge_count_alt() {
        return psp_edge_count_for_span(psp_chart_max_span(), UNPROVIDED);
    }

    /**
     *
     *
     * @return INTEGERP; How many input-spanning edges are there in *PSP-CHART*?
     */
    @LispMethod(comment = "@return INTEGERP; How many input-spanning edges are there in *PSP-CHART*?")
    public static SubLObject psp_input_spanning_edge_count() {
        return psp_edge_count_for_span(psp_chart_max_span(), UNPROVIDED);
    }

    /**
     *
     *
     * @return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase headed by EDGE.
     */
    @LispMethod(comment = "@return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase headed by EDGE.")
    public static final SubLObject psp_bindings_from_head_alt(SubLObject edge) {
        {
            SubLObject frame = get_psp_edge_frame(edge);
            return NIL != frame ? ((SubLObject) (psp_bindings_from_frame(frame))) : NIL;
        }
    }

    /**
     *
     *
     * @return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase headed by EDGE.
     */
    @LispMethod(comment = "@return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase headed by EDGE.")
    public static SubLObject psp_bindings_from_head(final SubLObject edge) {
        final SubLObject frame = get_psp_edge_frame(edge);
        return NIL != frame ? psp_bindings_from_frame(frame) : NIL;
    }

    /**
     * Cache information about subcat frames from the KB.
     */
    @LispMethod(comment = "Cache information about subcat frames from the KB.")
    public static final SubLObject cache_psp_frame_info_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        {
                            SubLObject frames = isa.all_fort_instances($$SubcategorizationFrame, UNPROVIDED, UNPROVIDED);
                            SubLObject total = length(frames);
                            {
                                SubLObject _prev_bind_0_11 = $last_percent_progress_index$.currentBinding(thread);
                                SubLObject _prev_bind_1_12 = $last_percent_progress_prediction$.currentBinding(thread);
                                SubLObject _prev_bind_2_13 = $within_noting_percent_progress$.currentBinding(thread);
                                SubLObject _prev_bind_3 = $percent_progress_start_time$.currentBinding(thread);
                                try {
                                    $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
                                    $last_percent_progress_prediction$.bind(NIL, thread);
                                    $within_noting_percent_progress$.bind(T, thread);
                                    $percent_progress_start_time$.bind(get_universal_time(), thread);
                                    noting_percent_progress_preamble($str_alt248$Initializing_Subcategorization_Fr);
                                    {
                                        SubLObject list_var = NIL;
                                        SubLObject frame = NIL;
                                        SubLObject sofar = NIL;
                                        for (list_var = frames, frame = list_var.first(), sofar = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , frame = list_var.first() , sofar = add(ONE_INTEGER, sofar)) {
                                            if (NIL != psp_constrained_frameP(frame, UNPROVIDED)) {
                                                psp_bindings_from_frame_int(frame, UNPROVIDED);
                                                psp_keywords_for_frame(frame);
                                                psp_predicative_frameP(frame);
                                                psp_passivized_dtr_num(frame, UNPROVIDED);
                                            }
                                            note_percent_progress(sofar, total);
                                        }
                                    }
                                    noting_percent_progress_postamble();
                                } finally {
                                    $percent_progress_start_time$.rebind(_prev_bind_3, thread);
                                    $within_noting_percent_progress$.rebind(_prev_bind_2_13, thread);
                                    $last_percent_progress_prediction$.rebind(_prev_bind_1_12, thread);
                                    $last_percent_progress_index$.rebind(_prev_bind_0_11, thread);
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
            return $CACHED;
        }
    }

    @LispMethod(comment = "Cache information about subcat frames from the KB.")
    public static SubLObject cache_psp_frame_info() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(parsing_utilities.psp_lexicon_root_mt(UNPROVIDED));
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            final SubLObject frames = isa.all_fort_instances($$SubcategorizationFrame, UNPROVIDED, UNPROVIDED);
            final SubLObject total = length(frames);
            final SubLObject _prev_bind_0_$12 = $last_percent_progress_index$.currentBinding(thread);
            final SubLObject _prev_bind_1_$13 = $last_percent_progress_prediction$.currentBinding(thread);
            final SubLObject _prev_bind_2_$14 = $within_noting_percent_progress$.currentBinding(thread);
            final SubLObject _prev_bind_4 = $percent_progress_start_time$.currentBinding(thread);
            try {
                $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
                $last_percent_progress_prediction$.bind(NIL, thread);
                $within_noting_percent_progress$.bind(T, thread);
                $percent_progress_start_time$.bind(get_universal_time(), thread);
                try {
                    noting_percent_progress_preamble($str256$Initializing_Subcategorization_Fr);
                    SubLObject list_var = NIL;
                    SubLObject frame = NIL;
                    SubLObject sofar = NIL;
                    list_var = frames;
                    frame = list_var.first();
                    for (sofar = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , frame = list_var.first() , sofar = add(ONE_INTEGER, sofar)) {
                        if (NIL != psp_constrained_frameP(frame, UNPROVIDED)) {
                            psp_bindings_from_frame_int(frame, UNPROVIDED);
                            psp_keywords_for_frame(frame);
                            psp_predicative_frameP(frame);
                            psp_passivized_dtr_num(frame, UNPROVIDED);
                        }
                        note_percent_progress(sofar, total);
                    }
                } finally {
                    final SubLObject _prev_bind_0_$13 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values = getValuesAsVector();
                        noting_percent_progress_postamble();
                        restoreValuesFromVector(_values);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$13, thread);
                    }
                }
            } finally {
                $percent_progress_start_time$.rebind(_prev_bind_4, thread);
                $within_noting_percent_progress$.rebind(_prev_bind_2_$14, thread);
                $last_percent_progress_prediction$.rebind(_prev_bind_1_$13, thread);
                $last_percent_progress_index$.rebind(_prev_bind_0_$12, thread);
            }
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return $CACHED;
    }

    /**
     * Clear and then cache subcat frame info.
     */
    @LispMethod(comment = "Clear and then cache subcat frame info.")
    public static final SubLObject recache_psp_frame_info_alt() {
        clear_psp_frame_info();
        return cache_psp_frame_info();
    }

    @LispMethod(comment = "Clear and then cache subcat frame info.")
    public static SubLObject recache_psp_frame_info() {
        clear_psp_frame_info();
        return cache_psp_frame_info();
    }

    public static final SubLObject clear_psp_frame_info_alt() {
        clear_psp_bindings_from_frame_int();
        clear_psp_keywords_for_frame();
        clear_psp_constrained_frameP();
        clear_intransitive_frameP();
        clear_psp_devisable_verb_frames();
        clear_psp_passivized_dtr_num();
        return $CLEARED;
    }

    public static SubLObject clear_psp_frame_info() {
        clear_psp_bindings_from_frame_int();
        clear_psp_keywords_for_frame();
        clear_psp_constrained_frameP();
        clear_intransitive_frameP();
        clear_psp_devisable_verb_frames();
        clear_psp_passivized_dtr_num();
        return $CLEARED;
    }

    /**
     * #$afterAdding for subcat frame-related predicates.
     */
    @LispMethod(comment = "#$afterAdding for subcat frame-related predicates.")
    public static final SubLObject add_subcat_frame_info_alt(SubLObject argument, SubLObject assertion) {
        return clear_psp_frame_info();
    }

    @LispMethod(comment = "#$afterAdding for subcat frame-related predicates.")
    public static SubLObject add_subcat_frame_info(final SubLObject argument, final SubLObject assertion) {
        return clear_psp_frame_info();
    }

    /**
     * #$afterRemoving for subcat frame-related predicates.
     */
    @LispMethod(comment = "#$afterRemoving for subcat frame-related predicates.")
    public static final SubLObject remove_subcat_frame_info_alt(SubLObject argument, SubLObject assertion) {
        return clear_psp_frame_info();
    }

    @LispMethod(comment = "#$afterRemoving for subcat frame-related predicates.")
    public static SubLObject remove_subcat_frame_info(final SubLObject argument, final SubLObject assertion) {
        return clear_psp_frame_info();
    }

    /**
     *
     *
     * @param FRAME;
     * 		a possibly-passive subcat frame.
     * @return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase
    whose head has frame FRAME, or :UNKNOWN.
     */
    @LispMethod(comment = "@param FRAME;\r\n\t\ta possibly-passive subcat frame.\r\n@return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase\r\nwhose head has frame FRAME, or :UNKNOWN.")
    public static final SubLObject psp_bindings_from_frame_alt(SubLObject frame) {
        {
            SubLObject pcase_var = frame;
            if (pcase_var.eql($UNKNOWN)) {
                return $UNKNOWN;
            } else {
                {
                    SubLObject passiveP = psp_passive_frameP(frame);
                    SubLObject active_frame = (NIL != passiveP) ? ((SubLObject) (psp_active_frame(frame))) : frame;
                    SubLObject v_bindings = psp_bindings_from_frame_int(active_frame, UNPROVIDED);
                    return NIL != passiveP ? ((SubLObject) (psp_passivize_bindings(v_bindings, frame))) : v_bindings;
                }
            }
        }
    }

    /**
     *
     *
     * @param FRAME;
     * 		a possibly-passive subcat frame.
     * @return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase
    whose head has frame FRAME, or :UNKNOWN.
     */
    @LispMethod(comment = "@param FRAME;\r\n\t\ta possibly-passive subcat frame.\r\n@return LISTP of (<dtr-num> <keyword>) binding pairs for daughters of a phrase\r\nwhose head has frame FRAME, or :UNKNOWN.")
    public static SubLObject psp_bindings_from_frame(final SubLObject frame) {
        if (frame.eql($UNKNOWN)) {
            return $UNKNOWN;
        }
        final SubLObject passiveP = psp_passive_frameP(frame);
        final SubLObject active_frame = (NIL != passiveP) ? psp_active_frame(frame) : frame;
        final SubLObject v_bindings = psp_bindings_from_frame_int(active_frame, UNPROVIDED);
        return NIL != passiveP ? psp_passivize_bindings(v_bindings, frame) : v_bindings;
    }

    public static final SubLObject clear_psp_bindings_from_frame_int_alt() {
        {
            SubLObject cs = $psp_bindings_from_frame_int_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_bindings_from_frame_int() {
        final SubLObject cs = $psp_bindings_from_frame_int_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_bindings_from_frame_int_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_bindings_from_frame_int_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_bindings_from_frame_int(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_bindings_from_frame_int_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject psp_bindings_from_frame_int_internal_alt(SubLObject frame, SubLObject mt) {
        {
            SubLObject v_bindings = NIL;
            SubLObject cdolist_list_var = ask_utilities.ask_template($list_alt254, listS($$subcatFrameDependentKeyword, frame, $list_alt254), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject binding = NIL;
            for (binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , binding = cdolist_list_var.first()) {
                rplaca(binding, number_utilities.f_1X(binding.first()));
                v_bindings = cons(cons($INSTANCE, binding), v_bindings);
            }
            return v_bindings;
        }
    }

    public static SubLObject psp_bindings_from_frame_int_internal(final SubLObject frame, final SubLObject mt) {
        SubLObject v_bindings = NIL;
        SubLObject cdolist_list_var = ask_utilities.ask_template($list262, listS($$subcatFrameDependentKeyword, frame, $list262), mt, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        SubLObject binding = NIL;
        binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            rplaca(binding, number_utilities.f_1X(binding.first()));
            v_bindings = cons(cons($INSTANCE, binding), v_bindings);
            cdolist_list_var = cdolist_list_var.rest();
            binding = cdolist_list_var.first();
        } 
        return v_bindings;
    }

    public static final SubLObject psp_bindings_from_frame_int_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        {
            SubLObject caching_state = $psp_bindings_from_frame_int_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(PSP_BINDINGS_FROM_FRAME_INT, $psp_bindings_from_frame_int_caching_state$, NIL, EQL, TWO_INTEGER, $int$128);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (frame.eql(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(psp_bindings_from_frame_int_internal(frame, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(frame, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject psp_bindings_from_frame_int(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        SubLObject caching_state = $psp_bindings_from_frame_int_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(PSP_BINDINGS_FROM_FRAME_INT, $psp_bindings_from_frame_int_caching_state$, NIL, EQL, TWO_INTEGER, $int$128);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (frame.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(psp_bindings_from_frame_int_internal(frame, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(frame, mt));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject clear_psp_predicative_frameP_alt() {
        {
            SubLObject cs = $psp_predicative_frameP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_predicative_frameP() {
        final SubLObject cs = $psp_predicative_frameP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_predicative_frameP_alt(SubLObject frame) {
        return memoization_state.caching_state_remove_function_results_with_args($psp_predicative_frameP_caching_state$.getGlobalValue(), list(frame), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_predicative_frameP(final SubLObject frame) {
        return memoization_state.caching_state_remove_function_results_with_args($psp_predicative_frameP_caching_state$.getGlobalValue(), list(frame), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject psp_predicative_frameP_internal_alt(SubLObject frame) {
        return makeBoolean((NIL != forts.fort_p(frame)) && (NIL != isa.isa_in_any_mtP(frame, $$PredicativeFrame)));
    }

    public static SubLObject psp_predicative_frameP_internal(final SubLObject frame) {
        return makeBoolean((NIL != forts.fort_p(frame)) && (NIL != isa.isa_in_any_mtP(frame, $$PredicativeFrame)));
    }

    public static final SubLObject psp_predicative_frameP_alt(SubLObject frame) {
        {
            SubLObject caching_state = $psp_predicative_frameP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym258$PSP_PREDICATIVE_FRAME_, $sym260$_PSP_PREDICATIVE_FRAME__CACHING_STATE_, NIL, EQL, ONE_INTEGER, $int$128);
            }
            {
                SubLObject results = memoization_state.caching_state_lookup(caching_state, frame, $kw178$_MEMOIZED_ITEM_NOT_FOUND_);
                if (results == $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(psp_predicative_frameP_internal(frame)));
                    memoization_state.caching_state_put(caching_state, frame, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject psp_predicative_frameP(final SubLObject frame) {
        SubLObject caching_state = $psp_predicative_frameP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym266$PSP_PREDICATIVE_FRAME_, $sym268$_PSP_PREDICATIVE_FRAME__CACHING_STATE_, NIL, EQL, ONE_INTEGER, $int$128);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, frame, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(psp_predicative_frameP_internal(frame)));
            memoization_state.caching_state_put(caching_state, frame, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    public static final SubLObject clear_psp_keywords_for_frame_alt() {
        {
            SubLObject cs = $psp_keywords_for_frame_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_keywords_for_frame() {
        final SubLObject cs = $psp_keywords_for_frame_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_keywords_for_frame_alt(SubLObject frame) {
        return memoization_state.caching_state_remove_function_results_with_args($psp_keywords_for_frame_caching_state$.getGlobalValue(), list(frame), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_keywords_for_frame(final SubLObject frame) {
        return memoization_state.caching_state_remove_function_results_with_args($psp_keywords_for_frame_caching_state$.getGlobalValue(), list(frame), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return LISTP of keywords suitable for FRAME.
     */
    @LispMethod(comment = "@return LISTP of keywords suitable for FRAME.")
    public static final SubLObject psp_keywords_for_frame_internal_alt(SubLObject frame) {
        if (NIL != psp_passive_frameP(frame)) {
            return psp_keywords_for_frame(psp_active_frame(frame));
        } else {
            return ask_utilities.ask_variable($sym262$_KEYWORD, listS($$subcatFrameKeywords, frame, $list_alt264), $$InferencePSC, FIVE_INTEGER, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
    }

    /**
     *
     *
     * @return LISTP of keywords suitable for FRAME.
     */
    @LispMethod(comment = "@return LISTP of keywords suitable for FRAME.")
    public static SubLObject psp_keywords_for_frame_internal(final SubLObject frame) {
        if (NIL != psp_passive_frameP(frame)) {
            return psp_keywords_for_frame(psp_active_frame(frame));
        }
        return ask_utilities.ask_variable($sym270$_KEYWORD, listS($$subcatFrameKeywords, frame, $list272), $$InferencePSC, FIVE_INTEGER, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject psp_keywords_for_frame_alt(SubLObject frame) {
        {
            SubLObject caching_state = $psp_keywords_for_frame_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(PSP_KEYWORDS_FOR_FRAME, $psp_keywords_for_frame_caching_state$, NIL, EQL, ONE_INTEGER, $int$128);
            }
            {
                SubLObject results = memoization_state.caching_state_lookup(caching_state, frame, $kw178$_MEMOIZED_ITEM_NOT_FOUND_);
                if (results == $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(psp_keywords_for_frame_internal(frame)));
                    memoization_state.caching_state_put(caching_state, frame, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject psp_keywords_for_frame(final SubLObject frame) {
        SubLObject caching_state = $psp_keywords_for_frame_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(PSP_KEYWORDS_FOR_FRAME, $psp_keywords_for_frame_caching_state$, NIL, EQL, ONE_INTEGER, $int$128);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, frame, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(psp_keywords_for_frame_internal(frame)));
            memoization_state.caching_state_put(caching_state, frame, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    public static final SubLObject clear_psp_constrained_frameP_alt() {
        {
            SubLObject cs = $psp_constrained_frameP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_constrained_frameP() {
        final SubLObject cs = $psp_constrained_frameP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_constrained_frameP_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_constrained_frameP_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_constrained_frameP(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_constrained_frameP_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN; Does FRAME have any constraints visible from MT?
    If not, we won't use it.
     */
    @LispMethod(comment = "@return BOOLEAN; Does FRAME have any constraints visible from MT?\r\nIf not, we won\'t use it.")
    public static final SubLObject psp_constrained_frameP_internal_alt(SubLObject frame, SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject ans = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        ans = makeBoolean((NIL != intransitive_frameP(frame, UNPROVIDED)) || (NIL != kb_mapping_utilities.some_pred_value(frame, $$subcatFrameDependentConstraint, ONE_INTEGER, $TRUE)));
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
     * @return BOOLEAN; Does FRAME have any constraints visible from MT?
    If not, we won't use it.
     */
    @LispMethod(comment = "@return BOOLEAN; Does FRAME have any constraints visible from MT?\r\nIf not, we won\'t use it.")
    public static SubLObject psp_constrained_frameP_internal(final SubLObject frame, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject ans = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            ans = makeBoolean((NIL != intransitive_frameP(frame, UNPROVIDED)) || (NIL != kb_mapping_utilities.some_pred_value(frame, $$subcatFrameDependentConstraint, ONE_INTEGER, $TRUE)));
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return ans;
    }

    public static final SubLObject psp_constrained_frameP_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        {
            SubLObject caching_state = $psp_constrained_frameP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym267$PSP_CONSTRAINED_FRAME_, $sym270$_PSP_CONSTRAINED_FRAME__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (frame.eql(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(psp_constrained_frameP_internal(frame, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(frame, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject psp_constrained_frameP(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        SubLObject caching_state = $psp_constrained_frameP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym275$PSP_CONSTRAINED_FRAME_, $sym278$_PSP_CONSTRAINED_FRAME__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (frame.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(psp_constrained_frameP_internal(frame, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(frame, mt));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject clear_psp_devisable_verb_frames_alt() {
        {
            SubLObject cs = $psp_devisable_verb_frames_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_devisable_verb_frames() {
        final SubLObject cs = $psp_devisable_verb_frames_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_devisable_verb_frames_alt(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_devisable_verb_frames_caching_state$.getGlobalValue(), list(mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_devisable_verb_frames(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_devisable_verb_frames_caching_state$.getGlobalValue(), list(mt), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return LISTP; of verb frames that we can devise semTrans templates for.
    Only bother doing syntactic parsing with these for verb edges with no known templates.
     */
    @LispMethod(comment = "@return LISTP; of verb frames that we can devise semTrans templates for.\r\nOnly bother doing syntactic parsing with these for verb edges with no known templates.")
    public static final SubLObject psp_devisable_verb_frames_internal_alt(SubLObject mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject ans = NIL;
                {
                    SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
                    try {
                        lexicon_vars.$lexicon_lookup_mt$.bind(mt, thread);
                        ans = list_utilities.remove_if_not($sym272$DEVISABLE_VERB_FRAME_, lexicon_vars.verb_frames(lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread)), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    } finally {
                        lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return ans;
            }
        }
    }

    /**
     *
     *
     * @return LISTP; of verb frames that we can devise semTrans templates for.
    Only bother doing syntactic parsing with these for verb edges with no known templates.
     */
    @LispMethod(comment = "@return LISTP; of verb frames that we can devise semTrans templates for.\r\nOnly bother doing syntactic parsing with these for verb edges with no known templates.")
    public static SubLObject psp_devisable_verb_frames_internal(final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject ans = NIL;
        final SubLObject _prev_bind_0 = lexicon_vars.$lexicon_lookup_mt$.currentBinding(thread);
        try {
            lexicon_vars.$lexicon_lookup_mt$.bind(mt, thread);
            ans = list_utilities.remove_if_not($sym280$DEVISABLE_VERB_FRAME_, lexicon_vars.verb_frames(lexicon_vars.$lexicon_lookup_mt$.getDynamicValue(thread)), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        } finally {
            lexicon_vars.$lexicon_lookup_mt$.rebind(_prev_bind_0, thread);
        }
        return ans;
    }

    public static final SubLObject psp_devisable_verb_frames_alt(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        {
            SubLObject caching_state = $psp_devisable_verb_frames_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(PSP_DEVISABLE_VERB_FRAMES, $psp_devisable_verb_frames_caching_state$, NIL, EQL, ONE_INTEGER, SIXTEEN_INTEGER);
            }
            {
                SubLObject results = memoization_state.caching_state_lookup(caching_state, mt, $kw178$_MEMOIZED_ITEM_NOT_FOUND_);
                if (results == $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(psp_devisable_verb_frames_internal(mt)));
                    memoization_state.caching_state_put(caching_state, mt, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject psp_devisable_verb_frames(SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        SubLObject caching_state = $psp_devisable_verb_frames_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(PSP_DEVISABLE_VERB_FRAMES, $psp_devisable_verb_frames_caching_state$, NIL, EQL, ONE_INTEGER, SIXTEEN_INTEGER);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, mt, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(psp_devisable_verb_frames_internal(mt)));
            memoization_state.caching_state_put(caching_state, mt, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    public static final SubLObject clear_transitive_np_comp_frameP_alt() {
        {
            SubLObject cs = $transitive_np_comp_frameP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_transitive_np_comp_frameP() {
        final SubLObject cs = $transitive_np_comp_frameP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_transitive_np_comp_frameP_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($transitive_np_comp_frameP_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_transitive_np_comp_frameP(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($transitive_np_comp_frameP_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN
     */
    @LispMethod(comment = "@return BOOLEAN")
    public static final SubLObject transitive_np_comp_frameP_internal_alt(SubLObject frame, SubLObject mt) {
        return at_defns.quick_quiet_has_typeP(frame, $$TransitiveNPFrameType, mt);
    }

    /**
     *
     *
     * @return BOOLEAN
     */
    @LispMethod(comment = "@return BOOLEAN")
    public static SubLObject transitive_np_comp_frameP_internal(final SubLObject frame, final SubLObject mt) {
        return at_defns.quick_quiet_has_typeP(frame, $$TransitiveNPFrameType, mt);
    }

    public static final SubLObject transitive_np_comp_frameP_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        {
            SubLObject caching_state = $transitive_np_comp_frameP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym274$TRANSITIVE_NP_COMP_FRAME_, $sym276$_TRANSITIVE_NP_COMP_FRAME__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (frame.eql(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(transitive_np_comp_frameP_internal(frame, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(frame, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject transitive_np_comp_frameP(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        SubLObject caching_state = $transitive_np_comp_frameP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym282$TRANSITIVE_NP_COMP_FRAME_, $sym284$_TRANSITIVE_NP_COMP_FRAME__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (frame.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(transitive_np_comp_frameP_internal(frame, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(frame, mt));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject clear_intransitive_frameP_alt() {
        {
            SubLObject cs = $intransitive_frameP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_intransitive_frameP() {
        final SubLObject cs = $intransitive_frameP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_intransitive_frameP_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($intransitive_frameP_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_intransitive_frameP(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        return memoization_state.caching_state_remove_function_results_with_args($intransitive_frameP_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN
     */
    @LispMethod(comment = "@return BOOLEAN")
    public static final SubLObject intransitive_frameP_internal_alt(SubLObject frame, SubLObject mt) {
        return list_utilities.sublisp_boolean(backward.removal_ask(listS($$subcatFrameArity, frame, $list_alt279), mt, UNPROVIDED, UNPROVIDED));
    }

    /**
     *
     *
     * @return BOOLEAN
     */
    @LispMethod(comment = "@return BOOLEAN")
    public static SubLObject intransitive_frameP_internal(final SubLObject frame, final SubLObject mt) {
        return list_utilities.sublisp_boolean(backward.removal_ask(listS($$subcatFrameArity, frame, $list287), mt, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject intransitive_frameP_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        {
            SubLObject caching_state = $intransitive_frameP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym277$INTRANSITIVE_FRAME_, $sym280$_INTRANSITIVE_FRAME__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (frame.eql(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(intransitive_frameP_internal(frame, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(frame, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject intransitive_frameP(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        SubLObject caching_state = $intransitive_frameP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym285$INTRANSITIVE_FRAME_, $sym288$_INTRANSITIVE_FRAME__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (frame.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(intransitive_frameP_internal(frame, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(frame, mt));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject clear_psp_frame_has_typeP_alt() {
        {
            SubLObject cs = $psp_frame_has_typeP_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_frame_has_typeP() {
        final SubLObject cs = $psp_frame_has_typeP_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_frame_has_typeP_alt(SubLObject frame, SubLObject type) {
        return memoization_state.caching_state_remove_function_results_with_args($psp_frame_has_typeP_caching_state$.getGlobalValue(), list(frame, type), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_frame_has_typeP(final SubLObject frame, final SubLObject type) {
        return memoization_state.caching_state_remove_function_results_with_args($psp_frame_has_typeP_caching_state$.getGlobalValue(), list(frame, type), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN
     */
    @LispMethod(comment = "@return BOOLEAN")
    public static final SubLObject psp_frame_has_typeP_internal_alt(SubLObject frame, SubLObject type) {
        return isa.isa_in_any_mtP(frame, type);
    }

    /**
     *
     *
     * @return BOOLEAN
     */
    @LispMethod(comment = "@return BOOLEAN")
    public static SubLObject psp_frame_has_typeP_internal(final SubLObject frame, final SubLObject type) {
        return isa.isa_in_any_mtP(frame, type);
    }

    public static final SubLObject psp_frame_has_typeP_alt(SubLObject frame, SubLObject type) {
        {
            SubLObject caching_state = $psp_frame_has_typeP_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name($sym281$PSP_FRAME_HAS_TYPE_, $sym282$_PSP_FRAME_HAS_TYPE__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(frame, type);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (frame.eql(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && type.eql(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(psp_frame_has_typeP_internal(frame, type)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(frame, type));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject psp_frame_has_typeP(final SubLObject frame, final SubLObject type) {
        SubLObject caching_state = $psp_frame_has_typeP_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name($sym289$PSP_FRAME_HAS_TYPE_, $sym290$_PSP_FRAME_HAS_TYPE__CACHING_STATE_, NIL, EQL, TWO_INTEGER, $int$128);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(frame, type);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (frame.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && type.eql(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(psp_frame_has_typeP_internal(frame, type)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(frame, type));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject clear_psp_passivized_dtr_num_alt() {
        {
            SubLObject cs = $psp_passivized_dtr_num_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_psp_passivized_dtr_num() {
        final SubLObject cs = $psp_passivized_dtr_num_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_psp_passivized_dtr_num_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$psp_rule_lookup_mt$.getGlobalValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_passivized_dtr_num_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_psp_passivized_dtr_num(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$psp_rule_lookup_mt$.getGlobalValue();
        }
        return memoization_state.caching_state_remove_function_results_with_args($psp_passivized_dtr_num_caching_state$.getGlobalValue(), list(frame, mt), UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @param FRAME
     * 		fort-p; an active-voice subcat frame.
     * @unknown DTR-NUM is indexed from 1 and includes the head.
     * @return INTEGERP or NIL. The number of the dtr that can be passivized.
     */
    @LispMethod(comment = "@param FRAME\r\n\t\tfort-p; an active-voice subcat frame.\r\n@unknown DTR-NUM is indexed from 1 and includes the head.\r\n@return INTEGERP or NIL. The number of the dtr that can be passivized.")
    public static final SubLObject psp_passivized_dtr_num_internal_alt(SubLObject frame, SubLObject mt) {
        {
            SubLObject fort_frame = function_terms.naut_to_nart(frame);
            SubLObject ask_result = ask_utilities.ask_template($sym284$_N, list($$and, listS($$isa, fort_frame, $list_alt287), listS($$subcatFrameDependentConstraint, fort_frame, $list_alt288)), mt, ONE_INTEGER, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject nums = list_utilities.remove_if_not(INTEGERP, ask_result, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject raw_ans = (NIL != nums) ? ((SubLObject) (number_utilities.minimum(nums, UNPROVIDED))) : NIL;
            return raw_ans.isInteger() ? ((SubLObject) (number_utilities.f_1X(raw_ans))) : NIL;
        }
    }

    /**
     *
     *
     * @param FRAME
     * 		fort-p; an active-voice subcat frame.
     * @unknown DTR-NUM is indexed from 1 and includes the head.
     * @return INTEGERP or NIL. The number of the dtr that can be passivized.
     */
    @LispMethod(comment = "@param FRAME\r\n\t\tfort-p; an active-voice subcat frame.\r\n@unknown DTR-NUM is indexed from 1 and includes the head.\r\n@return INTEGERP or NIL. The number of the dtr that can be passivized.")
    public static SubLObject psp_passivized_dtr_num_internal(final SubLObject frame, final SubLObject mt) {
        final SubLObject fort_frame = function_terms.naut_to_nart(frame);
        final SubLObject ask_result = ask_utilities.ask_template($sym292$_N, list($$and, listS($$isa, fort_frame, $list295), listS($$subcatFrameDependentConstraint, fort_frame, $list296)), mt, ONE_INTEGER, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject nums = list_utilities.remove_if_not(INTEGERP, ask_result, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject raw_ans = (NIL != nums) ? number_utilities.minimum(nums, UNPROVIDED) : NIL;
        return raw_ans.isInteger() ? number_utilities.f_1X(raw_ans) : NIL;
    }

    public static final SubLObject psp_passivized_dtr_num_alt(SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$psp_rule_lookup_mt$.getGlobalValue();
        }
        {
            SubLObject caching_state = $psp_passivized_dtr_num_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(PSP_PASSIVIZED_DTR_NUM, $psp_passivized_dtr_num_caching_state$, NIL, EQL, TWO_INTEGER, $int$128);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (frame.eql(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(psp_passivized_dtr_num_internal(frame, mt)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(frame, mt));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject psp_passivized_dtr_num(final SubLObject frame, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_vars.$psp_rule_lookup_mt$.getGlobalValue();
        }
        SubLObject caching_state = $psp_passivized_dtr_num_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(PSP_PASSIVIZED_DTR_NUM, $psp_passivized_dtr_num_caching_state$, NIL, EQL, TWO_INTEGER, $int$128);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_2(frame, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (frame.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.eql(cached_args.first())) {
                        return memoization_state.caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(psp_passivized_dtr_num_internal(frame, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(frame, mt));
        return memoization_state.caching_results(results3);
    }

    /**
     *
     *
     * @param FRAME
     * 		fort-p; an active-voice subcat frame.
     */
    @LispMethod(comment = "@param FRAME\r\n\t\tfort-p; an active-voice subcat frame.")
    public static final SubLObject psp_passivized_dtr_keyword_alt(SubLObject frame) {
        {
            SubLObject p_dtr_num = psp_passivized_dtr_num(frame, UNPROVIDED);
            SubLObject v_bindings = psp_bindings_from_frame(frame);
            SubLObject binding_list = find(p_dtr_num, v_bindings, symbol_function(EQL), symbol_function(SECOND), UNPROVIDED, UNPROVIDED);
            SubLObject keyword = third(binding_list);
            return keyword;
        }
    }

    /**
     *
     *
     * @param FRAME
     * 		fort-p; an active-voice subcat frame.
     */
    @LispMethod(comment = "@param FRAME\r\n\t\tfort-p; an active-voice subcat frame.")
    public static SubLObject psp_passivized_dtr_keyword(final SubLObject frame) {
        final SubLObject p_dtr_num = psp_passivized_dtr_num(frame, UNPROVIDED);
        final SubLObject v_bindings = psp_bindings_from_frame(frame);
        final SubLObject binding_list = find(p_dtr_num, v_bindings, symbol_function(EQL), symbol_function(SECOND), UNPROVIDED, UNPROVIDED);
        final SubLObject keyword = third(binding_list);
        return keyword;
    }

    /**
     *
     *
     * @return BOOLEAN; Is FRAME a passive frame?
     */
    @LispMethod(comment = "@return BOOLEAN; Is FRAME a passive frame?")
    public static final SubLObject psp_passive_frameP_alt(SubLObject frame) {
        return makeBoolean(frame.isCons() && (NIL != member(frame.first(), $psp_passive_keys$.getGlobalValue(), UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @return BOOLEAN; Is FRAME a passive frame?
     */
    @LispMethod(comment = "@return BOOLEAN; Is FRAME a passive frame?")
    public static SubLObject psp_passive_frameP(final SubLObject frame) {
        return makeBoolean(frame.isCons() && (NIL != member(frame.first(), $psp_passive_keys$.getGlobalValue(), UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @return FORT-P; the active version of the passive frame FRAME.
     */
    @LispMethod(comment = "@return FORT-P; the active version of the passive frame FRAME.")
    public static final SubLObject psp_active_frame_alt(SubLObject frame) {
        return second(frame);
    }

    /**
     *
     *
     * @return FORT-P; the active version of the passive frame FRAME.
     */
    @LispMethod(comment = "@return FORT-P; the active version of the passive frame FRAME.")
    public static SubLObject psp_active_frame(final SubLObject frame) {
        return second(frame);
    }

    /**
     *
     *
     * @return KEYWORDP; The type of passive of FRAME.
     */
    @LispMethod(comment = "@return KEYWORDP; The type of passive of FRAME.")
    public static final SubLObject psp_passive_frame_type_alt(SubLObject frame) {
        return frame.first();
    }

    /**
     *
     *
     * @return KEYWORDP; The type of passive of FRAME.
     */
    @LispMethod(comment = "@return KEYWORDP; The type of passive of FRAME.")
    public static SubLObject psp_passive_frame_type(final SubLObject frame) {
        return frame.first();
    }

    /**
     *
     *
     * @param PASSIVE-FRAME;
     * 		a passive subcat frame.
     * @unknown This function could stand to be cached.
     */
    @LispMethod(comment = "@param PASSIVE-FRAME;\r\n\t\ta passive subcat frame.\r\n@unknown This function could stand to be cached.")
    public static final SubLObject psp_passivize_bindings_alt(SubLObject v_bindings, SubLObject passive_frame) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject new_bindings = NIL;
                SubLObject active_frame = psp_active_frame(passive_frame);
                SubLObject type = psp_passive_frame_type(passive_frame);
                SubLObject p_dtr_num = psp_passivized_dtr_num(active_frame, UNPROVIDED);
                if (!(p_dtr_num.isInteger() || (NIL == parsing_vars.$psp_verboseP$.getDynamicValue(thread)))) {
                    Errors.warn($str_alt293$Don_t_know_how_to_passivize__S___, active_frame);
                    return v_bindings;
                }
                {
                    SubLObject cdolist_list_var = v_bindings;
                    SubLObject binding = NIL;
                    for (binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , binding = cdolist_list_var.first()) {
                        {
                            SubLObject datum = binding;
                            SubLObject current = datum;
                            SubLObject type_14 = NIL;
                            SubLObject n = NIL;
                            SubLObject keyword = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt294);
                            type_14 = current.first();
                            current = current.rest();
                            destructuring_bind_must_consp(current, datum, $list_alt294);
                            n = current.first();
                            current = current.rest();
                            destructuring_bind_must_consp(current, datum, $list_alt294);
                            keyword = current.first();
                            current = current.rest();
                            if (NIL == current) {
                                if (n.numL(p_dtr_num)) {
                                    new_bindings = cons(binding, new_bindings);
                                } else {
                                    if (n.numG(p_dtr_num)) {
                                        new_bindings = cons(list(type_14, number_utilities.f_1_(n), keyword), new_bindings);
                                    }
                                }
                            } else {
                                cdestructuring_bind_error(datum, $list_alt294);
                            }
                        }
                    }
                }
                if (type == $PASSIVE_WITH_BY) {
                    new_bindings = cons(psp_by_subject_binding(active_frame), new_bindings);
                }
                return new_bindings;
            }
        }
    }

    /**
     *
     *
     * @param PASSIVE-FRAME;
     * 		a passive subcat frame.
     * @unknown This function could stand to be cached.
     */
    @LispMethod(comment = "@param PASSIVE-FRAME;\r\n\t\ta passive subcat frame.\r\n@unknown This function could stand to be cached.")
    public static SubLObject psp_passivize_bindings(final SubLObject v_bindings, final SubLObject passive_frame) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject new_bindings = NIL;
        final SubLObject active_frame = psp_active_frame(passive_frame);
        final SubLObject type = psp_passive_frame_type(passive_frame);
        final SubLObject p_dtr_num = psp_passivized_dtr_num(active_frame, UNPROVIDED);
        if ((!p_dtr_num.isInteger()) && (NIL != parsing_vars.$psp_verboseP$.getDynamicValue(thread))) {
            Errors.warn($str301$Don_t_know_how_to_passivize__S___, active_frame);
            return v_bindings;
        }
        SubLObject cdolist_list_var = v_bindings;
        SubLObject binding = NIL;
        binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = binding;
            SubLObject type_$16 = NIL;
            SubLObject n = NIL;
            SubLObject keyword = NIL;
            destructuring_bind_must_consp(current, datum, $list302);
            type_$16 = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list302);
            n = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list302);
            keyword = current.first();
            current = current.rest();
            if (NIL == current) {
                if (n.numL(p_dtr_num)) {
                    new_bindings = cons(binding, new_bindings);
                } else
                    if (n.numG(p_dtr_num)) {
                        new_bindings = cons(list(type_$16, number_utilities.f_1_(n), keyword), new_bindings);
                    }

            } else {
                cdestructuring_bind_error(datum, $list302);
            }
            cdolist_list_var = cdolist_list_var.rest();
            binding = cdolist_list_var.first();
        } 
        if (type == $PASSIVE_WITH_BY) {
            new_bindings = cons(psp_by_subject_binding(active_frame), new_bindings);
        }
        return new_bindings;
    }

    public static final SubLObject psp_by_subject_binding_alt(SubLObject active_frame) {
        {
            SubLObject frame_arity = lexicon_accessors.subcat_frame_arity(active_frame);
            SubLObject by_obj_num = add(TWO_INTEGER, frame_arity);
            return listS($INSTANCE, by_obj_num, $list_alt295);
        }
    }

    public static SubLObject psp_by_subject_binding(final SubLObject active_frame) {
        final SubLObject frame_arity = lexicon_accessors.subcat_frame_arity(active_frame);
        final SubLObject by_obj_num = add(TWO_INTEGER, frame_arity);
        return listS($INSTANCE, by_obj_num, $list303);
    }

    /**
     * Add CYCL to CYCLS. @note destructively modifies CYCLS.
     */
    @LispMethod(comment = "Add CYCL to CYCLS. @note destructively modifies CYCLS.")
    public static final SubLObject psp_add_cycl_to_cycls_alt(SubLObject cycl, SubLObject cycls, SubLObject type) {
        if (type == UNPROVIDED) {
            type = $DENOT_GROUND;
        }
        dictionary_utilities.dictionary_pushnew(cycls, type, cycl, $sym297$PSP_CYCL__, UNPROVIDED);
        return cycls;
    }

    @LispMethod(comment = "Add CYCL to CYCLS. @note destructively modifies CYCLS.")
    public static SubLObject psp_add_cycl_to_cycls(final SubLObject cycl, final SubLObject cycls, SubLObject type) {
        if (type == UNPROVIDED) {
            type = $DENOT_GROUND;
        }
        dictionary_utilities.dictionary_pushnew(cycls, type, cycl, $sym305$PSP_CYCL__, UNPROVIDED);
        return cycls;
    }/**
     * Add CYCL to CYCLS. @note destructively modifies CYCLS.
     */


    /**
     *
     *
     * @return a new structure for a CYCLS slot of a psp edge.
     */
    @LispMethod(comment = "@return a new structure for a CYCLS slot of a psp edge.")
    public static final SubLObject psp_new_cycls_alt(SubLObject initial_cycl, SubLObject type) {
        if (initial_cycl == UNPROVIDED) {
            initial_cycl = NIL;
        }
        if (type == UNPROVIDED) {
            type = $DENOT_GROUND;
        }
        {
            SubLObject cycls = dictionary.new_dictionary(symbol_function(EQ), ZERO_INTEGER);
            if (NIL != initial_cycl) {
                psp_add_cycl_to_cycls(initial_cycl, cycls, type);
            }
            return cycls;
        }
    }

    /**
     *
     *
     * @return a new structure for a CYCLS slot of a psp edge.
     */
    @LispMethod(comment = "@return a new structure for a CYCLS slot of a psp edge.")
    public static SubLObject psp_new_cycls(SubLObject initial_cycl, SubLObject type) {
        if (initial_cycl == UNPROVIDED) {
            initial_cycl = NIL;
        }
        if (type == UNPROVIDED) {
            type = $DENOT_GROUND;
        }
        final SubLObject cycls = dictionary.new_dictionary(symbol_function(EQ), ZERO_INTEGER);
        if (NIL != initial_cycl) {
            psp_add_cycl_to_cycls(initial_cycl, cycls, type);
        }
        return cycls;
    }

    /**
     *
     *
     * @param START;
     * 		integerp - the starting index.
     * @param END;
     * 		integerp - the ending index.
     * @param WORDS;
     * 		listp of WORD-P objects.
     * @param EDGE-STRING;
     * 		stringp - the part of the input string from START to END.
     * @param LEX-ENTRIES;
     * 		listp - the trie entries for WORDS.
     * @return LISTP; of psp-edges from the given parameters
     */
    @LispMethod(comment = "@param START;\r\n\t\tintegerp - the starting index.\r\n@param END;\r\n\t\tintegerp - the ending index.\r\n@param WORDS;\r\n\t\tlistp of WORD-P objects.\r\n@param EDGE-STRING;\r\n\t\tstringp - the part of the input string from START to END.\r\n@param LEX-ENTRIES;\r\n\t\tlistp - the trie entries for WORDS.\r\n@return LISTP; of psp-edges from the given parameters")
    public static final SubLObject build_psp_edges_from_lex_entries_alt(SubLObject start, SubLObject end, SubLObject words, SubLObject edge_string, SubLObject lex_entries) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject span = interval_span.get_interval_span(start, end);
                SubLObject edges = NIL;
                if ((NIL == $psp_parse_wh_wordsP$.getGlobalValue()) && (NIL != lexicon_accessors.string_is_posP(edge_string, $$WHWord, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                } else {
                    if (NIL != lex_entries) {
                        {
                            SubLObject cdolist_list_var = lex_entries;
                            SubLObject lex_entry = NIL;
                            for (lex_entry = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , lex_entry = cdolist_list_var.first()) {
                                {
                                    SubLObject cdolist_list_var_15 = build_psp_edges_from_lex_entry(lex_entry, span, edge_string);
                                    SubLObject edge = NIL;
                                    for (edge = cdolist_list_var_15.first(); NIL != cdolist_list_var_15; cdolist_list_var_15 = cdolist_list_var_15.rest() , edge = cdolist_list_var_15.first()) {
                                        {
                                            SubLObject item_var = edge;
                                            if (NIL == member(item_var, edges, symbol_function(EQL), symbol_function(IDENTITY))) {
                                                edges = cons(item_var, edges);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        edges = psp_adjust_edge_weights(edges);
                        edges = psp_consolidate_edges(edges);
                    } else {
                        if (NIL != list_utilities.singletonP(words)) {
                            {
                                SubLObject word = words.first();
                                edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, edge_string, psp_find_constant(document.word_category(word).first()), psp_find_constant(second(document.word_category(word))), numeral_parser.string_to_interval(document.word_string(word)), UNPROVIDED, UNPROVIDED, UNPROVIDED), edges);
                            }
                        }
                    }
                }
                return edges;
            }
        }
    }

    /**
     *
     *
     * @param START;
     * 		integerp - the starting index.
     * @param END;
     * 		integerp - the ending index.
     * @param WORDS;
     * 		listp of WORD-P objects.
     * @param EDGE-STRING;
     * 		stringp - the part of the input string from START to END.
     * @param LEX-ENTRIES;
     * 		listp - the trie entries for WORDS.
     * @return LISTP; of psp-edges from the given parameters
     */
    @LispMethod(comment = "@param START;\r\n\t\tintegerp - the starting index.\r\n@param END;\r\n\t\tintegerp - the ending index.\r\n@param WORDS;\r\n\t\tlistp of WORD-P objects.\r\n@param EDGE-STRING;\r\n\t\tstringp - the part of the input string from START to END.\r\n@param LEX-ENTRIES;\r\n\t\tlistp - the trie entries for WORDS.\r\n@return LISTP; of psp-edges from the given parameters")
    public static SubLObject build_psp_edges_from_lex_entries(final SubLObject start, final SubLObject end, final SubLObject words, final SubLObject edge_string, final SubLObject lex_entries) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject span = interval_span.get_interval_span(start, end);
        SubLObject edges = NIL;
        if ((NIL != $psp_parse_wh_wordsP$.getGlobalValue()) || (NIL == lexicon_accessors.string_is_posP(edge_string, $$WHWord, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
            if (NIL != lex_entries) {
                SubLObject cdolist_list_var = lex_entries;
                SubLObject lex_entry = NIL;
                lex_entry = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject cdolist_list_var_$17 = build_psp_edges_from_lex_entry(lex_entry, span, edge_string);
                    SubLObject edge = NIL;
                    edge = cdolist_list_var_$17.first();
                    while (NIL != cdolist_list_var_$17) {
                        final SubLObject item_var = edge;
                        if (NIL == member(item_var, edges, symbol_function(EQL), symbol_function(IDENTITY))) {
                            edges = cons(item_var, edges);
                        }
                        cdolist_list_var_$17 = cdolist_list_var_$17.rest();
                        edge = cdolist_list_var_$17.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    lex_entry = cdolist_list_var.first();
                } 
                edges = psp_adjust_edge_weights(edges);
                edges = psp_consolidate_edges(edges);
            } else
                if (NIL != list_utilities.singletonP(words)) {
                    final SubLObject word = words.first();
                    edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, edge_string, psp_find_constant(document.word_category(word).first()), psp_find_constant(second(document.word_category(word))), numeral_parser.string_to_interval(document.word_string(word)), UNPROVIDED, UNPROVIDED, UNPROVIDED), edges);
                }

        }
        return edges;
    }

    public static final SubLObject build_psp_edges_from_lex_entry_alt(SubLObject lex_entry, SubLObject span, SubLObject edge_string) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject edges = NIL;
                SubLObject entry_weight = psp_weight_for_lex_entry(lex_entry, edge_string);
                SubLObject cycl = methods.funcall_instance_method_with_1_args(lex_entry, GET, $DENOT);
                SubLObject preferred_denots = lex_entry_preferred_denots(lex_entry);
                SubLObject cat = methods.funcall_instance_method_with_1_args(lex_entry, GET, $CYC_CATEGORY);
                SubLObject wu = methods.funcall_instance_method_with_1_args(lex_entry, GET, $WORD_UNIT);
                SubLObject pos_preds = psp_pos_preds_from_lex_entry(lex_entry);
                SubLObject cdolist_list_var = pos_preds;
                SubLObject pos_pred = NIL;
                for (pos_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pos_pred = cdolist_list_var.first()) {
                    if (NIL != psp_term_passes_constraintsP(cycl, UNPROVIDED)) {
                        {
                            SubLObject frames = psp_find_frames(wu, cat, cycl, UNPROVIDED);
                            SubLObject cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
                            SubLObject disprefer_level = psp_dispreference_level(cycl, preferred_denots, pos_pred);
                            SubLObject weight = psp_disprefer_weight(entry_weight, disprefer_level);
                            if (cycl != $NO_DENOT) {
                                psp_add_cycl_to_cycls(cycl, cycls, UNPROVIDED);
                            }
                            if ((NIL != wu) && (NIL != subl_promotions.memberP($$RegularNounFrame, frames, UNPROVIDED, UNPROVIDED))) {
                                {
                                    SubLObject cdolist_list_var_16 = psp_semantics.psp_noun_sem_trans_cycls(edge_string, wu, cat);
                                    SubLObject noun_cycl = NIL;
                                    for (noun_cycl = cdolist_list_var_16.first(); NIL != cdolist_list_var_16; cdolist_list_var_16 = cdolist_list_var_16.rest() , noun_cycl = cdolist_list_var_16.first()) {
                                        psp_add_cycl_to_cycls(noun_cycl, cycls, UNPROVIDED);
                                    }
                                }
                            }
                            edges = append(edges, psp_deverbal_edges(span, edge_string, cat, pos_pred, cycls, weight, wu));
                            {
                                SubLObject cdolist_list_var_17 = frames;
                                SubLObject frame = NIL;
                                for (frame = cdolist_list_var_17.first(); NIL != cdolist_list_var_17; cdolist_list_var_17 = cdolist_list_var_17.rest() , frame = cdolist_list_var_17.first()) {
                                    edges = append(edges, psp_passive_edges(span, edge_string, cat, pos_pred, cycls, wu, frame));
                                    edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, edge_string, cat, pos_pred, cycls, weight, wu, frame), edges);
                                }
                            }
                        }
                    }
                }
                return edges;
            }
        }
    }

    public static SubLObject build_psp_edges_from_lex_entry(final SubLObject lex_entry, final SubLObject span, final SubLObject edge_string) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject edges = NIL;
        final SubLObject entry_weight = psp_weight_for_lex_entry(lex_entry, edge_string);
        final SubLObject cycl = abstract_lexicon.lex_entry_denot(lex_entry);
        final SubLObject preferred_denots = lex_entry_preferred_denots(lex_entry);
        final SubLObject cat = methods.funcall_instance_method_with_1_args(lex_entry, GET, $CYC_CATEGORY);
        final SubLObject wu = methods.funcall_instance_method_with_1_args(lex_entry, GET, $WORD_UNIT);
        SubLObject cdolist_list_var;
        final SubLObject pos_preds = cdolist_list_var = psp_pos_preds_from_lex_entry(lex_entry);
        SubLObject pos_pred = NIL;
        pos_pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != psp_term_passes_constraintsP(cycl, UNPROVIDED)) {
                final SubLObject frames = psp_find_frames(wu, cat, cycl, UNPROVIDED);
                final SubLObject cycls = psp_new_cycls(UNPROVIDED, UNPROVIDED);
                final SubLObject disprefer_level = psp_dispreference_level(cycl, preferred_denots, pos_pred);
                final SubLObject weight = psp_disprefer_weight(entry_weight, disprefer_level);
                if (cycl != $NO_DENOT) {
                    psp_add_cycl_to_cycls(cycl, cycls, UNPROVIDED);
                }
                if ((NIL != wu) && (NIL != subl_promotions.memberP($$RegularNounFrame, frames, UNPROVIDED, UNPROVIDED))) {
                    SubLObject cdolist_list_var_$18 = psp_semantics.psp_noun_sem_trans_cycls(edge_string, wu, cat);
                    SubLObject noun_cycl = NIL;
                    noun_cycl = cdolist_list_var_$18.first();
                    while (NIL != cdolist_list_var_$18) {
                        psp_add_cycl_to_cycls(noun_cycl, cycls, UNPROVIDED);
                        cdolist_list_var_$18 = cdolist_list_var_$18.rest();
                        noun_cycl = cdolist_list_var_$18.first();
                    } 
                }
                edges = append(edges, psp_deverbal_edges(span, edge_string, cat, pos_pred, cycls, weight, wu));
                SubLObject cdolist_list_var_$19 = frames;
                SubLObject frame = NIL;
                frame = cdolist_list_var_$19.first();
                while (NIL != cdolist_list_var_$19) {
                    edges = append(edges, psp_passive_edges(span, edge_string, cat, pos_pred, cycls, wu, frame));
                    edges = cons(new_psp_lexical_edge(parsing_vars.$psp_chart$.getDynamicValue(thread), span, edge_string, cat, pos_pred, cycls, weight, wu, frame), edges);
                    cdolist_list_var_$19 = cdolist_list_var_$19.rest();
                    frame = cdolist_list_var_$19.first();
                } 
            }
            cdolist_list_var = cdolist_list_var.rest();
            pos_pred = cdolist_list_var.first();
        } 
        return edges;
    }

    public static final SubLObject lex_entry_get_trie_entry_alt(SubLObject lex_entry) {
        return methods.funcall_instance_method_with_1_args(lex_entry, GET, $TRIE_ENTRY);
    }

    public static SubLObject lex_entry_get_trie_entry(final SubLObject lex_entry) {
        return methods.funcall_instance_method_with_1_args(lex_entry, GET, $TRIE_ENTRY);
    }

    public static final SubLObject lex_entry_preferred_denots_alt(SubLObject lex_entry) {
        {
            SubLObject nl_trie_entry = lex_entry_get_trie_entry(lex_entry);
            return NIL != nl_trie_entry ? ((SubLObject) (nl_trie.nl_trie_entry_preferred_denots(nl_trie_entry))) : list(methods.funcall_instance_method_with_1_args(lex_entry, GET, $DENOT));
        }
    }

    public static SubLObject lex_entry_preferred_denots(final SubLObject lex_entry) {
        final SubLObject nl_trie_entry = lex_entry_get_trie_entry(lex_entry);
        return NIL != nl_trie_entry ? nl_trie.nl_trie_entry_preferred_denots(nl_trie_entry) : list(methods.funcall_instance_method_with_1_args(lex_entry, GET, $DENOT));
    }

    public static final SubLObject psp_pos_preds_from_lex_entry_alt(SubLObject lex_entry) {
        return (NIL == methods.funcall_instance_method_with_1_args(lex_entry, GET, $WORD_UNIT)) && (NIL != methods.funcall_instance_method_with_1_args(lex_entry, GET, $PREDICATE)) ? ((SubLObject) (list(methods.funcall_instance_method_with_1_args(lex_entry, GET, $PREDICATE)))) : methods.funcall_instance_method_with_1_args(lex_entry, GET, $INFLECTIONS);
    }

    public static SubLObject psp_pos_preds_from_lex_entry(final SubLObject lex_entry) {
        return (NIL == methods.funcall_instance_method_with_1_args(lex_entry, GET, $WORD_UNIT)) && (NIL != methods.funcall_instance_method_with_1_args(lex_entry, GET, $PREDICATE)) ? list(methods.funcall_instance_method_with_1_args(lex_entry, GET, $PREDICATE)) : methods.funcall_instance_method_with_1_args(lex_entry, GET, $INFLECTIONS);
    }

    public static final SubLObject psp_term_passes_constraintsP_alt(SubLObject cycl, SubLObject filters) {
        if (filters == UNPROVIDED) {
            filters = parsing_vars.$psp_concept_filters$.getDynamicValue();
        }
        return makeBoolean((NIL != rbp_wff.rbp_wff_term_visibleP(cycl)) && (NIL != term_lexicon.denot_passes_filter_specsP(cycl, filters)));
    }

    public static SubLObject psp_term_passes_constraintsP(final SubLObject cycl, SubLObject filters) {
        if (filters == UNPROVIDED) {
            filters = parsing_vars.$psp_concept_filters$.getDynamicValue();
        }
        return makeBoolean((NIL != rbp_wff.rbp_wff_term_visibleP(cycl)) && (NIL != term_lexicon.denot_passes_filter_specsP(cycl, filters)));
    }

    public static final SubLObject titles_conventionally_quoted_alt() {
        if (NIL != $titles_conventionally_quoted$.getGlobalValue()) {
            return $titles_conventionally_quoted$.getGlobalValue();
        }
        {
            SubLObject pred = constants_high.find_constant($$$titlesConventionallyQuoted);
            if (NIL != valid_constantP(pred, UNPROVIDED)) {
                $titles_conventionally_quoted$.setGlobalValue(pred);
            }
            return $titles_conventionally_quoted$.getGlobalValue();
        }
    }

    public static SubLObject titles_conventionally_quoted() {
        if (NIL != $titles_conventionally_quoted$.getGlobalValue()) {
            return $titles_conventionally_quoted$.getGlobalValue();
        }
        final SubLObject pred = constants_high.find_constant($$$titlesConventionallyQuoted);
        if (NIL != valid_constantP(pred, UNPROVIDED)) {
            $titles_conventionally_quoted$.setGlobalValue(pred);
        }
        return $titles_conventionally_quoted$.getGlobalValue();
    }

    public static final SubLObject psp_dispreference_level_alt(SubLObject cycl, SubLObject preferred_denots, SubLObject pos_pred) {
        if (NIL != subl_promotions.memberP(cycl, preferred_denots, symbol_function(EQUAL), UNPROVIDED)) {
            return minus($psp_preferred_denot_preference_factor$.getGlobalValue());
        } else {
            if ((NIL != lexicon_accessors.genl_pos_predP(pos_pred, $$titleOfWork, UNPROVIDED)) && (NIL != cyc_kernel.closed_query_success_result_p(inference_kernel.new_cyc_query(list(titles_conventionally_quoted(), cycl), parsing_utilities.psp_lexicon_root_mt(UNPROVIDED), UNPROVIDED)))) {
                return TWO_INTEGER;
            } else {
                return ZERO_INTEGER;
            }
        }
    }

    public static SubLObject psp_dispreference_level(final SubLObject cycl, final SubLObject preferred_denots, final SubLObject pos_pred) {
        if (NIL != subl_promotions.memberP(cycl, preferred_denots, symbol_function(EQUAL), UNPROVIDED)) {
            return minus($psp_preferred_denot_preference_factor$.getGlobalValue());
        }
        if ((NIL != lexicon_accessors.genl_pos_predP(pos_pred, $$titleOfWork, UNPROVIDED)) && (NIL != cyc_kernel.closed_query_success_result_p(inference_kernel.new_cyc_query(list(titles_conventionally_quoted(), cycl), parsing_utilities.psp_lexicon_root_mt(UNPROVIDED), UNPROVIDED)))) {
            return TWO_INTEGER;
        }
        return ZERO_INTEGER;
    }

    public static final SubLObject psp_disprefer_weight_alt(SubLObject entry_weight, SubLObject disprefer_level) {
        if (disprefer_level == UNPROVIDED) {
            disprefer_level = ONE_INTEGER;
        }
        {
            SubLObject weight = entry_weight;
            if (NIL != subl_promotions.positive_integer_p(disprefer_level)) {
                {
                    SubLObject i = NIL;
                    for (i = ZERO_INTEGER; i.numL(disprefer_level); i = add(i, ONE_INTEGER)) {
                        weight = psp_weight_compose_two(weight, $psp_edge_dispreferred_weight$.getGlobalValue());
                    }
                }
            } else {
                if (NIL != subl_promotions.negative_integer_p(disprefer_level)) {
                    weight = psp_reprefer_weight(entry_weight, minus(disprefer_level));
                }
            }
            return weight;
        }
    }

    public static SubLObject psp_disprefer_weight(final SubLObject entry_weight, SubLObject disprefer_level) {
        if (disprefer_level == UNPROVIDED) {
            disprefer_level = ONE_INTEGER;
        }
        SubLObject weight = entry_weight;
        if (NIL != subl_promotions.positive_integer_p(disprefer_level)) {
            SubLObject i;
            for (i = NIL, i = ZERO_INTEGER; i.numL(disprefer_level); i = add(i, ONE_INTEGER)) {
                weight = psp_weight_compose_two(weight, $psp_edge_dispreferred_weight$.getGlobalValue());
            }
        } else
            if (NIL != subl_promotions.negative_integer_p(disprefer_level)) {
                weight = psp_reprefer_weight(entry_weight, minus(disprefer_level));
            }

        return weight;
    }

    public static final SubLObject psp_reprefer_weight_alt(SubLObject entry_weight, SubLObject reprefer_level) {
        if (reprefer_level == UNPROVIDED) {
            reprefer_level = ONE_INTEGER;
        }
        {
            SubLObject weight = entry_weight;
            SubLObject i = NIL;
            for (i = ZERO_INTEGER; i.numL(reprefer_level); i = add(i, ONE_INTEGER)) {
                {
                    SubLObject delta = (parsing_vars.$psp_weight_type$.getGlobalValue() == $INTEGER) ? ((SubLObject) (integerDivide(subtract(parsing_vars.$psp_max_weight$.getGlobalValue(), weight), FOUR_INTEGER))) : divide(subtract(parsing_vars.$psp_max_weight$.getGlobalValue(), weight), FOUR_INTEGER);
                    weight = add(weight, delta);
                }
            }
            return weight;
        }
    }

    public static SubLObject psp_reprefer_weight(final SubLObject entry_weight, SubLObject reprefer_level) {
        if (reprefer_level == UNPROVIDED) {
            reprefer_level = ONE_INTEGER;
        }
        SubLObject weight = entry_weight;
        SubLObject i;
        SubLObject delta;
        for (i = NIL, i = ZERO_INTEGER; i.numL(reprefer_level); i = add(i, ONE_INTEGER)) {
            delta = (parsing_vars.$psp_weight_type$.getGlobalValue() == $INTEGER) ? integerDivide(subtract(parsing_vars.$psp_max_weight$.getGlobalValue(), weight), FOUR_INTEGER) : divide(subtract(parsing_vars.$psp_max_weight$.getGlobalValue(), weight), FOUR_INTEGER);
            weight = add(weight, delta);
        }
        return weight;
    }

    /**
     *
     *
     * @return PSP-WEIGHT-P appropriate for an edge built from LEX-ENTRY and STRING.
     */
    @LispMethod(comment = "@return PSP-WEIGHT-P appropriate for an edge built from LEX-ENTRY and STRING.")
    public static final SubLObject psp_weight_for_lex_entry_alt(SubLObject lex_entry, SubLObject string) {
        {
            SubLObject nl_trie_entry = lex_entry_get_trie_entry(lex_entry);
            return NIL != nl_trie_entry ? ((SubLObject) (psp_weight_for_trie_entry(nl_trie_entry, string))) : $psp_edge_default_weight$.getGlobalValue();
        }
    }

    /**
     *
     *
     * @return PSP-WEIGHT-P appropriate for an edge built from LEX-ENTRY and STRING.
     */
    @LispMethod(comment = "@return PSP-WEIGHT-P appropriate for an edge built from LEX-ENTRY and STRING.")
    public static SubLObject psp_weight_for_lex_entry(final SubLObject lex_entry, final SubLObject string) {
        final SubLObject nl_trie_entry = lex_entry_get_trie_entry(lex_entry);
        return NIL != nl_trie_entry ? psp_weight_for_trie_entry(nl_trie_entry, string) : $psp_edge_default_weight$.getGlobalValue();
    }

    /**
     *
     *
     * @return PSP-WEIGHT-P appropriate for an edge built from TRIE-ENTRY and STRING.
     */
    @LispMethod(comment = "@return PSP-WEIGHT-P appropriate for an edge built from TRIE-ENTRY and STRING.")
    public static final SubLObject psp_weight_for_trie_entry_alt(SubLObject trie_entry, SubLObject string) {
        {
            SubLObject weight = $psp_edge_default_weight$.getGlobalValue();
            if (NIL != psp_disprefer_trie_entry_for_stringP(trie_entry, string)) {
                weight = psp_disprefer_weight(weight, UNPROVIDED);
            }
            return weight;
        }
    }

    /**
     *
     *
     * @return PSP-WEIGHT-P appropriate for an edge built from TRIE-ENTRY and STRING.
     */
    @LispMethod(comment = "@return PSP-WEIGHT-P appropriate for an edge built from TRIE-ENTRY and STRING.")
    public static SubLObject psp_weight_for_trie_entry(final SubLObject trie_entry, final SubLObject string) {
        SubLObject weight = $psp_edge_default_weight$.getGlobalValue();
        if (NIL != psp_disprefer_trie_entry_for_stringP(trie_entry, string)) {
            weight = psp_disprefer_weight(weight, UNPROVIDED);
        }
        return weight;
    }

    public static final SubLObject psp_disprefer_trie_entry_for_stringP_alt(SubLObject trie_entry, SubLObject string) {
        if ((NIL != lexicon_accessors.closed_lexical_class_stringP(string, UNPROVIDED)) && (NIL == nl_trie.nl_trie_closed_lexical_class_entryP(trie_entry))) {
            return T;
        } else {
            if ((NIL == nl_trie_accessors.nl_trie_access_case_sensitive_p()) && (NIL == string_utilities.upper_case_string_p(string))) {
                return NIL;
            } else {
                if (NIL != nl_trie.nl_trie_entry_case_matches_stringP(trie_entry, string)) {
                    return NIL;
                } else {
                    return T;
                }
            }
        }
    }

    public static SubLObject psp_disprefer_trie_entry_for_stringP(final SubLObject trie_entry, final SubLObject string) {
        if ((NIL != lexicon_accessors.closed_lexical_class_stringP(string, UNPROVIDED)) && (NIL == nl_trie.nl_trie_closed_lexical_class_entryP(trie_entry))) {
            return T;
        }
        if ((NIL == nl_trie_accessors.nl_trie_access_case_sensitive_p()) && (NIL == string_utilities.upper_case_string_p(string))) {
            return NIL;
        }
        if (NIL != nl_trie.nl_trie_entry_case_matches_stringP(trie_entry, string)) {
            return NIL;
        }
        return T;
    }

    public static final SubLObject psp_term_has_preferred_lexificationsP_internal_alt(SubLObject cycl) {
        return list_utilities.sublisp_boolean(lexicon_utilities.preferred_lexifications_for_term(cycl, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject psp_term_has_preferred_lexificationsP_internal(final SubLObject cycl) {
        return list_utilities.sublisp_boolean(lexicon_utilities.preferred_lexifications_for_term(cycl, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject psp_term_has_preferred_lexificationsP_alt(SubLObject cycl) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return psp_term_has_preferred_lexificationsP_internal(cycl);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, $sym309$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), $sym309$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_, ONE_INTEGER, NIL, EQL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, $sym309$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_, caching_state);
                }
                {
                    SubLObject results = memoization_state.caching_state_lookup(caching_state, cycl, $kw178$_MEMOIZED_ITEM_NOT_FOUND_);
                    if (results == $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                        results = arg2(thread.resetMultipleValues(), multiple_value_list(psp_term_has_preferred_lexificationsP_internal(cycl)));
                        memoization_state.caching_state_put(caching_state, cycl, results, UNPROVIDED);
                    }
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject psp_term_has_preferred_lexificationsP(final SubLObject cycl) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return psp_term_has_preferred_lexificationsP_internal(cycl);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, $sym317$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), $sym317$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_, ONE_INTEGER, NIL, EQL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, $sym317$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_, caching_state);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, cycl, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(thread.resetMultipleValues(), multiple_value_list(psp_term_has_preferred_lexificationsP_internal(cycl)));
            memoization_state.caching_state_put(caching_state, cycl, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    /**
     * Remove from QUADS any that we don't want to build edges out of.
     */
    @LispMethod(comment = "Remove from QUADS any that we don\'t want to build edges out of.")
    public static final SubLObject psp_filter_lexical_quads_alt(SubLObject quads, SubLObject quad_string) {
        {
            SubLObject good_quads = NIL;
            SubLObject cdolist_list_var = quads;
            SubLObject quad = NIL;
            for (quad = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , quad = cdolist_list_var.first()) {
                if (NIL != psp_good_lexical_quadP(quad, quad_string)) {
                    good_quads = cons(quad, good_quads);
                }
            }
            return good_quads;
        }
    }

    @LispMethod(comment = "Remove from QUADS any that we don\'t want to build edges out of.")
    public static SubLObject psp_filter_lexical_quads(final SubLObject quads, final SubLObject quad_string) {
        SubLObject good_quads = NIL;
        SubLObject cdolist_list_var = quads;
        SubLObject quad = NIL;
        quad = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != psp_good_lexical_quadP(quad, quad_string)) {
                good_quads = cons(quad, good_quads);
            }
            cdolist_list_var = cdolist_list_var.rest();
            quad = cdolist_list_var.first();
        } 
        return good_quads;
    }/**
     * Remove from QUADS any that we don't want to build edges out of.
     */


    /**
     *
     *
     * @return BOOLEAN; Should we even try to make any edges out of QUAD, corresponding
    to the input string QUAD-STRING?
     */
    @LispMethod(comment = "@return BOOLEAN; Should we even try to make any edges out of QUAD, corresponding\r\nto the input string QUAD-STRING?")
    public static final SubLObject psp_good_lexical_quadP_alt(SubLObject quad, SubLObject quad_string) {
        {
            SubLObject datum = quad;
            SubLObject current = datum;
            SubLObject cycl = NIL;
            SubLObject pos = NIL;
            SubLObject pos_pred = NIL;
            SubLObject wu = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt310);
            cycl = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt310);
            pos = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt310);
            pos_pred = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt310);
            wu = current.first();
            current = current.rest();
            if (NIL == current) {
                return makeBoolean((((NIL == parsing_utilities.blacklisted_cycl_for_parsersP(cycl)) && ((NIL == lexicon_vars.name_string_predP(pos_pred)) || (NIL != psp_edge_cycl_passes_name_checkP(quad_string, cycl)))) && ((NIL == psp_semantics.psp_genl_posP(pos, $$ClosedClassWord, UNPROVIDED)) || (NIL != lexicon_accessors.closed_lexical_class_stringP(quad_string, UNPROVIDED)))) && (NIL != psp_ok_pos_predP(pos_pred)));
            } else {
                cdestructuring_bind_error(datum, $list_alt310);
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Should we even try to make any edges out of QUAD, corresponding
    to the input string QUAD-STRING?
     */
    @LispMethod(comment = "@return BOOLEAN; Should we even try to make any edges out of QUAD, corresponding\r\nto the input string QUAD-STRING?")
    public static SubLObject psp_good_lexical_quadP(final SubLObject quad, final SubLObject quad_string) {
        SubLObject cycl = NIL;
        SubLObject pos = NIL;
        SubLObject pos_pred = NIL;
        SubLObject wu = NIL;
        destructuring_bind_must_consp(quad, quad, $list318);
        cycl = quad.first();
        SubLObject current = quad.rest();
        destructuring_bind_must_consp(current, quad, $list318);
        pos = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, quad, $list318);
        pos_pred = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, quad, $list318);
        wu = current.first();
        current = current.rest();
        if (NIL == current) {
            return makeBoolean((((NIL == parsing_utilities.blacklisted_cycl_for_parsersP(cycl)) && ((NIL == lexicon_vars.name_string_predP(pos_pred)) || (NIL != psp_edge_cycl_passes_name_checkP(quad_string, cycl)))) && ((NIL == psp_semantics.psp_genl_posP(pos, $$ClosedClassWord, UNPROVIDED)) || (NIL != lexicon_accessors.closed_lexical_class_stringP(quad_string, UNPROVIDED)))) && (NIL != psp_ok_pos_predP(pos_pred)));
        }
        cdestructuring_bind_error(quad, $list318);
        return NIL;
    }

    /**
     *
     *
     * @return BOOLEAN; Is POS-PRED ok for PSP use at all?
     */
    @LispMethod(comment = "@return BOOLEAN; Is POS-PRED ok for PSP use at all?")
    public static final SubLObject psp_ok_pos_predP_alt(SubLObject pos_pred) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return makeBoolean(!((NIL != parsing_vars.$psp_pos_pred_filterP$.getDynamicValue(thread)) && (NIL != member(pos_pred, parsing_vars.$psp_pos_pred_filter_preds$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED))));
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Is POS-PRED ok for PSP use at all?
     */
    @LispMethod(comment = "@return BOOLEAN; Is POS-PRED ok for PSP use at all?")
    public static SubLObject psp_ok_pos_predP(final SubLObject pos_pred) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return makeBoolean((NIL == parsing_vars.$psp_pos_pred_filterP$.getDynamicValue(thread)) || (NIL == member(pos_pred, parsing_vars.$psp_pos_pred_filter_preds$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)));
    }

    /**
     *
     *
     * @param EDGES
     * 		LISTP of PHRASE-STRUCTURE-PARSER-EDGE-P.
     * 		Adjust weights of EDGES based on the other edges in EDGES.
     * @unknown assumes all edges are for the same span.
     */
    @LispMethod(comment = "@param EDGES\r\n\t\tLISTP of PHRASE-STRUCTURE-PARSER-EDGE-P.\r\n\t\tAdjust weights of EDGES based on the other edges in EDGES.\r\n@unknown assumes all edges are for the same span.")
    public static final SubLObject psp_adjust_edge_weights_alt(SubLObject edges) {
        {
            SubLObject cdolist_list_var = edges;
            SubLObject edge = NIL;
            for (edge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , edge = cdolist_list_var.first()) {
                psp_adjust_edge_weight(edge, edges);
            }
        }
        return edges;
    }

    /**
     *
     *
     * @param EDGES
     * 		LISTP of PHRASE-STRUCTURE-PARSER-EDGE-P.
     * 		Adjust weights of EDGES based on the other edges in EDGES.
     * @unknown assumes all edges are for the same span.
     */
    @LispMethod(comment = "@param EDGES\r\n\t\tLISTP of PHRASE-STRUCTURE-PARSER-EDGE-P.\r\n\t\tAdjust weights of EDGES based on the other edges in EDGES.\r\n@unknown assumes all edges are for the same span.")
    public static SubLObject psp_adjust_edge_weights(final SubLObject edges) {
        SubLObject cdolist_list_var = edges;
        SubLObject edge = NIL;
        edge = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            psp_adjust_edge_weight(edge, edges);
            cdolist_list_var = cdolist_list_var.rest();
            edge = cdolist_list_var.first();
        } 
        return edges;
    }

    /**
     *
     *
     * @param EDGE;
     * 		PHRASE-STRUCTURE-PARSER-EDGE-P.
     * 		Adjust weight of EDGE based on the other edges in EDGES.
     * @unknown assumes all edges are for the same span.
     */
    @LispMethod(comment = "@param EDGE;\r\n\t\tPHRASE-STRUCTURE-PARSER-EDGE-P.\r\n\t\tAdjust weight of EDGE based on the other edges in EDGES.\r\n@unknown assumes all edges are for the same span.")
    public static final SubLObject psp_adjust_edge_weight_alt(SubLObject edge, SubLObject edges) {
        {
            SubLObject pos_pred = get_psp_edge_pos_pred(edge);
            if (NIL != lexicon_accessors.genl_pos_predP(pos_pred, $$titleOfWork, UNPROVIDED)) {
                {
                    SubLObject dispreference_level = NIL;
                    if (NIL == dispreference_level) {
                        {
                            SubLObject csome_list_var = edges;
                            SubLObject other_edge = NIL;
                            for (other_edge = csome_list_var.first(); !((NIL != dispreference_level) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , other_edge = csome_list_var.first()) {
                                if (!other_edge.eql(edge)) {
                                    if (NIL != lexicon_accessors.genl_pos_predP(get_psp_edge_pos_pred(other_edge), $$nameString, UNPROVIDED)) {
                                        if (NIL == dispreference_level) {
                                            {
                                                SubLObject csome_list_var_18 = psp_edge_lookup_semx(other_edge, $DENOT_GROUND);
                                                SubLObject cycl = NIL;
                                                for (cycl = csome_list_var_18.first(); !((NIL != dispreference_level) || (NIL == csome_list_var_18)); csome_list_var_18 = csome_list_var_18.rest() , cycl = csome_list_var_18.first()) {
                                                    if (!((NIL != psp_rules.psp_isaP(cycl, $$FictionalCharacter)) || (NIL != psp_rules.psp_isaP(cycl, $$ConceptualWork)))) {
                                                        dispreference_level = TWO_INTEGER;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (NIL != dispreference_level) {
                        set_psp_edge_weight(edge, psp_disprefer_weight(get_psp_edge_weight(edge), dispreference_level));
                    }
                }
            }
        }
        return edge;
    }

    /**
     *
     *
     * @param EDGE;
     * 		PHRASE-STRUCTURE-PARSER-EDGE-P.
     * 		Adjust weight of EDGE based on the other edges in EDGES.
     * @unknown assumes all edges are for the same span.
     */
    @LispMethod(comment = "@param EDGE;\r\n\t\tPHRASE-STRUCTURE-PARSER-EDGE-P.\r\n\t\tAdjust weight of EDGE based on the other edges in EDGES.\r\n@unknown assumes all edges are for the same span.")
    public static SubLObject psp_adjust_edge_weight(final SubLObject edge, final SubLObject edges) {
        final SubLObject pos_pred = get_psp_edge_pos_pred(edge);
        if (NIL != lexicon_accessors.genl_pos_predP(pos_pred, $$titleOfWork, UNPROVIDED)) {
            SubLObject dispreference_level = NIL;
            if (NIL == dispreference_level) {
                SubLObject csome_list_var = edges;
                SubLObject other_edge = NIL;
                other_edge = csome_list_var.first();
                while ((NIL == dispreference_level) && (NIL != csome_list_var)) {
                    if (((!other_edge.eql(edge)) && (NIL != lexicon_accessors.genl_pos_predP(get_psp_edge_pos_pred(other_edge), $$nameString, UNPROVIDED))) && (NIL == dispreference_level)) {
                        SubLObject csome_list_var_$20 = psp_edge_lookup_semx(other_edge, $DENOT_GROUND);
                        SubLObject cycl = NIL;
                        cycl = csome_list_var_$20.first();
                        while ((NIL == dispreference_level) && (NIL != csome_list_var_$20)) {
                            if ((NIL == psp_rules.psp_isaP(cycl, $$FictionalCharacter)) && (NIL == psp_rules.psp_isaP(cycl, $$ConceptualWork))) {
                                dispreference_level = TWO_INTEGER;
                            }
                            csome_list_var_$20 = csome_list_var_$20.rest();
                            cycl = csome_list_var_$20.first();
                        } 
                    }
                    csome_list_var = csome_list_var.rest();
                    other_edge = csome_list_var.first();
                } 
            }
            if (NIL != dispreference_level) {
                set_psp_edge_weight(edge, psp_disprefer_weight(get_psp_edge_weight(edge), dispreference_level));
            }
        }
        return edge;
    }

    /**
     * Try to consolidate EDGES into a smaller number of edges.
     *
     * @unknown assumes all edges are for the same span.
     */
    @LispMethod(comment = "Try to consolidate EDGES into a smaller number of edges.\r\n\r\n@unknown assumes all edges are for the same span.")
    public static final SubLObject psp_consolidate_edges_alt(SubLObject edges) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject output_edges = NIL;
                SubLObject cdolist_list_var = psp_delete_subsumed_edges(edges);
                SubLObject edge = NIL;
                for (edge = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , edge = cdolist_list_var.first()) {
                    {
                        SubLObject prior_edge = psp_find_matching_edge(edge, output_edges);
                        SubLObject edge_cycls = get_psp_edge_cycls(edge);
                        if ((NIL != prior_edge) && (NIL != dictionary.dictionary_empty_p(edge_cycls))) {
                        } else {
                            if (NIL != prior_edge) {
                                {
                                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(edge_cycls));
                                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                                        thread.resetMultipleValues();
                                        {
                                            SubLObject type = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                            SubLObject cycls = thread.secondMultipleValue();
                                            thread.resetMultipleValues();
                                            {
                                                SubLObject cdolist_list_var_19 = cycls;
                                                SubLObject cycl = NIL;
                                                for (cycl = cdolist_list_var_19.first(); NIL != cdolist_list_var_19; cdolist_list_var_19 = cdolist_list_var_19.rest() , cycl = cdolist_list_var_19.first()) {
                                                    psp_edge_add_cycl(prior_edge, cycl, type);
                                                }
                                            }
                                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                        }
                                    } 
                                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                                }
                            } else {
                                output_edges = cons(edge, output_edges);
                            }
                        }
                    }
                }
                return output_edges;
            }
        }
    }

    @LispMethod(comment = "Try to consolidate EDGES into a smaller number of edges.\r\n\r\n@unknown assumes all edges are for the same span.")
    public static SubLObject psp_consolidate_edges(final SubLObject edges) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject output_edges = NIL;
        SubLObject cdolist_list_var = psp_delete_subsumed_edges(edges);
        SubLObject edge = NIL;
        edge = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject prior_edge = psp_find_matching_edge(edge, output_edges);
            final SubLObject edge_cycls = get_psp_edge_cycls(edge);
            if ((NIL == prior_edge) || (NIL == dictionary.dictionary_empty_p(edge_cycls))) {
                if (NIL != prior_edge) {
                    SubLObject iteration_state;
                    for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(edge_cycls)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                        thread.resetMultipleValues();
                        final SubLObject type = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                        final SubLObject cycls = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        SubLObject cdolist_list_var_$21 = cycls;
                        SubLObject cycl = NIL;
                        cycl = cdolist_list_var_$21.first();
                        while (NIL != cdolist_list_var_$21) {
                            psp_edge_add_cycl(prior_edge, cycl, type);
                            cdolist_list_var_$21 = cdolist_list_var_$21.rest();
                            cycl = cdolist_list_var_$21.first();
                        } 
                    }
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                } else {
                    output_edges = cons(edge, output_edges);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            edge = cdolist_list_var.first();
        } 
        return output_edges;
    }/**
     * Try to consolidate EDGES into a smaller number of edges.
     *
     * @unknown assumes all edges are for the same span.
     */


    /**
     * Add CYCL to the TYPE semantics for EDGE.
     */
    @LispMethod(comment = "Add CYCL to the TYPE semantics for EDGE.")
    public static final SubLObject psp_edge_add_cycl_alt(SubLObject edge, SubLObject cycl, SubLObject type) {
        if (type == UNPROVIDED) {
            type = $DENOT_GROUND;
        }
        psp_add_cycl_to_cycls(cycl, get_psp_edge_cycls(edge), type);
        return edge;
    }

    @LispMethod(comment = "Add CYCL to the TYPE semantics for EDGE.")
    public static SubLObject psp_edge_add_cycl(final SubLObject edge, final SubLObject cycl, SubLObject type) {
        if (type == UNPROVIDED) {
            type = $DENOT_GROUND;
        }
        psp_add_cycl_to_cycls(cycl, get_psp_edge_cycls(edge), type);
        return edge;
    }/**
     * Add CYCL to the TYPE semantics for EDGE.
     */


    public static final SubLObject psp_delete_subsumed_edges_alt(SubLObject edges) {
        return list_utilities.delete_subsumed_items(edges, $sym313$PSP_EDGE_SUBSUMES_, UNPROVIDED);
    }

    public static SubLObject psp_delete_subsumed_edges(final SubLObject edges) {
        return list_utilities.delete_subsumed_items(edges, $sym321$PSP_EDGE_SUBSUMES_, UNPROVIDED);
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE subsume OTHER-EDGE?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE subsume OTHER-EDGE?")
    public static final SubLObject psp_edge_subsumesP_alt(SubLObject edge, SubLObject other_edge) {
        return psp_edge_subsumedP(other_edge, edge);
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE subsume OTHER-EDGE?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE subsume OTHER-EDGE?")
    public static SubLObject psp_edge_subsumesP(final SubLObject edge, final SubLObject other_edge) {
        return psp_edge_subsumedP(other_edge, edge);
    }

    /**
     *
     *
     * @return BOOLEAN; Is EDGE subsumed by OTHER-EDGE?
     */
    @LispMethod(comment = "@return BOOLEAN; Is EDGE subsumed by OTHER-EDGE?")
    public static final SubLObject psp_edge_subsumedP_alt(SubLObject edge, SubLObject other_edge) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject subsumedP = makeBoolean(((((NIL == psp_edge_weightierP(edge, other_edge, UNPROVIDED)) && (NIL != psp_edge_pos_pred_subsumedP(edge, other_edge))) && (NIL != psp_edge_category_subsumedP(edge, other_edge))) && (NIL != psp_edge_frame_subsumedP(edge, other_edge))) && (NIL != psp_edge_cycls_subsumedP(edge, other_edge)));
                if ((NIL != parsing_vars.$psp_verboseP$.getDynamicValue(thread)) && (NIL != subsumedP)) {
                    format(T, $str_alt314$__Removing_edge_____S__is_subsume, edge, other_edge);
                }
                return subsumedP;
            }
        }
    }

    /**
     *
     *
     * @return BOOLEAN; Is EDGE subsumed by OTHER-EDGE?
     */
    @LispMethod(comment = "@return BOOLEAN; Is EDGE subsumed by OTHER-EDGE?")
    public static SubLObject psp_edge_subsumedP(final SubLObject edge, final SubLObject other_edge) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject subsumedP = makeBoolean(((((NIL == psp_edge_weightierP(edge, other_edge, UNPROVIDED)) && (NIL != psp_edge_pos_pred_subsumedP(edge, other_edge))) && (NIL != psp_edge_category_subsumedP(edge, other_edge))) && (NIL != psp_edge_frame_subsumedP(edge, other_edge))) && (NIL != psp_edge_cycls_subsumedP(edge, other_edge)));
        if ((NIL != parsing_vars.$psp_verboseP$.getDynamicValue(thread)) && (NIL != subsumedP)) {
            format(T, $str322$__Removing_edge_____S__is_subsume, edge, other_edge);
        }
        return subsumedP;
    }

    public static final SubLObject psp_edge_pos_pred_subsumedP_alt(SubLObject edge, SubLObject other_edge) {
        return lexicon_accessors.genl_pos_predP(get_psp_edge_pos_pred(other_edge), get_psp_edge_pos_pred(edge), UNPROVIDED);
    }

    public static SubLObject psp_edge_pos_pred_subsumedP(final SubLObject edge, final SubLObject other_edge) {
        return lexicon_accessors.genl_pos_predP(get_psp_edge_pos_pred(other_edge), get_psp_edge_pos_pred(edge), UNPROVIDED);
    }

    public static final SubLObject psp_edge_category_subsumedP_alt(SubLObject edge, SubLObject other_edge) {
        return psp_semantics.psp_genl_posP(get_psp_edge_category(other_edge), get_psp_edge_category(edge), UNPROVIDED);
    }

    public static SubLObject psp_edge_category_subsumedP(final SubLObject edge, final SubLObject other_edge) {
        return psp_semantics.psp_genl_posP(get_psp_edge_category(other_edge), get_psp_edge_category(edge), UNPROVIDED);
    }

    public static final SubLObject psp_edge_frame_subsumedP_alt(SubLObject edge, SubLObject other_edge) {
        return psp_frame_subsumedP(get_psp_edge_frame(edge), get_psp_edge_frame(other_edge));
    }

    public static SubLObject psp_edge_frame_subsumedP(final SubLObject edge, final SubLObject other_edge) {
        return psp_frame_subsumedP(get_psp_edge_frame(edge), get_psp_edge_frame(other_edge));
    }

    public static final SubLObject psp_frame_subsumedP_alt(SubLObject frame, SubLObject other_frame) {
        return eq(frame, other_frame);
    }

    public static SubLObject psp_frame_subsumedP(final SubLObject frame, final SubLObject other_frame) {
        return eq(frame, other_frame);
    }

    public static final SubLObject psp_edge_cycls_subsumedP_alt(SubLObject edge, SubLObject other_edge) {
        return psp_cycls_subsumedP(get_psp_edge_cycls(edge), get_psp_edge_cycls(other_edge));
    }

    public static SubLObject psp_edge_cycls_subsumedP(final SubLObject edge, final SubLObject other_edge) {
        return psp_cycls_subsumedP(get_psp_edge_cycls(edge), get_psp_edge_cycls(other_edge));
    }

    public static final SubLObject psp_cycls_subsumedP_alt(SubLObject cycls, SubLObject other_cycls) {
        if (NIL != dictionary.dictionary_empty_p(cycls)) {
            return T;
        } else {
            {
                SubLObject differentP = NIL;
                if (NIL == differentP) {
                    {
                        SubLObject key = NIL;
                        SubLObject key_20 = NIL;
                        SubLObject other_key = NIL;
                        SubLObject other_key_21 = NIL;
                        for (key = dictionary.dictionary_keys(cycls), key_20 = key.first(), other_key = dictionary.dictionary_keys(other_cycls), other_key_21 = other_key.first(); !((NIL != differentP) || ((NIL == other_key) && (NIL == key))); key = key.rest() , key_20 = key.first() , other_key = other_key.rest() , other_key_21 = other_key.first()) {
                            differentP = makeBoolean(NIL == psp_cycls_subsumedP_int(dictionary.dictionary_lookup(cycls, key_20, UNPROVIDED), dictionary.dictionary_lookup(other_cycls, other_key_21, UNPROVIDED)));
                        }
                    }
                }
                return makeBoolean(NIL == differentP);
            }
        }
    }

    public static SubLObject psp_cycls_subsumedP(final SubLObject cycls, final SubLObject other_cycls) {
        if (NIL != dictionary.dictionary_empty_p(cycls)) {
            return T;
        }
        SubLObject differentP = NIL;
        if (NIL == differentP) {
            SubLObject key;
            SubLObject key_$22;
            SubLObject other_key;
            SubLObject other_key_$23;
            for (key = NIL, key_$22 = NIL, other_key = NIL, other_key_$23 = NIL, key = dictionary.dictionary_keys(cycls), key_$22 = key.first(), other_key = dictionary.dictionary_keys(other_cycls), other_key_$23 = other_key.first(); (NIL == differentP) && ((NIL != other_key) || (NIL != key)); differentP = makeBoolean(NIL == psp_cycls_subsumedP_int(dictionary.dictionary_lookup(cycls, key_$22, UNPROVIDED), dictionary.dictionary_lookup(other_cycls, other_key_$23, UNPROVIDED))) , key = key.rest() , key_$22 = key.first() , other_key = other_key.rest() , other_key_$23 = other_key.first()) {
            }
        }
        return makeBoolean(NIL == differentP);
    }

    public static final SubLObject psp_cycls_subsumedP_int_alt(SubLObject cycls, SubLObject other_cycls) {
        {
            SubLObject problemP = NIL;
            if (NIL == problemP) {
                {
                    SubLObject csome_list_var = cycls;
                    SubLObject cycl = NIL;
                    for (cycl = csome_list_var.first(); !((NIL != problemP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , cycl = csome_list_var.first()) {
                        if (NIL == subl_promotions.memberP(cycl, other_cycls, symbol_function(EQUAL), UNPROVIDED)) {
                            problemP = T;
                        }
                    }
                }
            }
            return makeBoolean(NIL == problemP);
        }
    }

    public static SubLObject psp_cycls_subsumedP_int(final SubLObject cycls, final SubLObject other_cycls) {
        SubLObject problemP = NIL;
        if (NIL == problemP) {
            SubLObject csome_list_var = cycls;
            SubLObject cycl = NIL;
            cycl = csome_list_var.first();
            while ((NIL == problemP) && (NIL != csome_list_var)) {
                if (NIL == subl_promotions.memberP(cycl, other_cycls, symbol_function(EQUAL), UNPROVIDED)) {
                    problemP = T;
                }
                csome_list_var = csome_list_var.rest();
                cycl = csome_list_var.first();
            } 
        }
        return makeBoolean(NIL == problemP);
    }

    public static final SubLObject psp_find_matching_edge_alt(SubLObject edge, SubLObject other_edges) {
        {
            SubLObject span = get_psp_edge_span(edge);
            SubLObject existing_edges = psp_edges_with_span(span, UNPROVIDED);
            SubLObject match = NIL;
            if (NIL == match) {
                {
                    SubLObject csome_list_var = append(other_edges, existing_edges);
                    SubLObject other_edge = NIL;
                    for (other_edge = csome_list_var.first(); !((NIL != match) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , other_edge = csome_list_var.first()) {
                        if (NIL != psp_edges_matchP(edge, other_edge)) {
                            match = other_edge;
                        }
                    }
                }
            }
            return match;
        }
    }

    public static SubLObject psp_find_matching_edge(final SubLObject edge, final SubLObject other_edges) {
        final SubLObject span = get_psp_edge_span(edge);
        final SubLObject existing_edges = psp_edges_with_span(span, UNPROVIDED);
        SubLObject match = NIL;
        if (NIL == match) {
            SubLObject csome_list_var = append(other_edges, existing_edges);
            SubLObject other_edge = NIL;
            other_edge = csome_list_var.first();
            while ((NIL == match) && (NIL != csome_list_var)) {
                if (NIL != psp_edges_matchP(edge, other_edge)) {
                    match = other_edge;
                }
                csome_list_var = csome_list_var.rest();
                other_edge = csome_list_var.first();
            } 
        }
        return match;
    }

    public static final SubLObject psp_edges_matchP_alt(SubLObject edge, SubLObject other_edge) {
        return makeBoolean((((get_psp_edge_pos_pred(edge) == get_psp_edge_pos_pred(other_edge)) && (get_psp_edge_category(edge) == get_psp_edge_category(other_edge))) && (get_psp_edge_frame(edge) == get_psp_edge_frame(other_edge))) && (NIL != psp_edge_weights_equalP(edge, other_edge, ZERO_INTEGER)));
    }

    public static SubLObject psp_edges_matchP(final SubLObject edge, final SubLObject other_edge) {
        return makeBoolean(((get_psp_edge_pos_pred(edge).eql(get_psp_edge_pos_pred(other_edge)) && get_psp_edge_category(edge).eql(get_psp_edge_category(other_edge))) && get_psp_edge_frame(edge).eql(get_psp_edge_frame(other_edge))) && (NIL != psp_edge_weights_equalP(edge, other_edge, ZERO_INTEGER)));
    }

    public static final SubLObject psp_find_frames_internal_alt(SubLObject wu, SubLObject pos, SubLObject denot, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        {
            SubLObject frames = NIL;
            if ((NIL != wu) && (NIL != pos)) {
                frames = lexicon_accessors.frames_of_wordXpos(wu, pos);
                if (denot != $NO_DENOT) {
                    frames = union(frames, lexicon_accessors.frames_of_word_posXdenot(wu, pos, denot), UNPROVIDED, UNPROVIDED);
                }
            }
            if (NIL != psp_semantics.psp_genl_posP(pos, $$Noun, UNPROVIDED)) {
                {
                    SubLObject item_var = $$RegularNounFrame;
                    if (NIL == member(item_var, frames, symbol_function(EQL), symbol_function(IDENTITY))) {
                        frames = cons(item_var, frames);
                    }
                }
            } else {
                if ((NIL == frames) && (NIL != psp_semantics.psp_genl_posP(pos, $$Adjective, UNPROVIDED))) {
                    {
                        SubLObject item_var = $$RegularAdjFrame;
                        if (NIL == member(item_var, frames, symbol_function(EQL), symbol_function(IDENTITY))) {
                            frames = cons(item_var, frames);
                        }
                    }
                } else {
                    if (((NIL == frames) && (denot != $NO_DENOT)) && (NIL != psp_semantics.psp_genl_posP(pos, $$Verb, UNPROVIDED))) {
                        frames = psp_devisable_verb_frames(mt);
                    }
                }
            }
            frames = list_utilities.remove_if_not($sym267$PSP_CONSTRAINED_FRAME_, frames, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            return NIL != frames ? ((SubLObject) (frames)) : $list_alt319;
        }
    }

    public static SubLObject psp_find_frames_internal(final SubLObject wu, final SubLObject pos, final SubLObject denot, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        SubLObject frames = NIL;
        if ((NIL != wu) && (NIL != pos)) {
            frames = lexicon_accessors.frames_of_wordXpos(wu, pos);
            if (denot != $NO_DENOT) {
                frames = union(frames, lexicon_accessors.frames_of_word_posXdenot(wu, pos, denot), UNPROVIDED, UNPROVIDED);
            }
        }
        if (NIL != psp_semantics.psp_genl_posP(pos, $$Noun, UNPROVIDED)) {
            final SubLObject item_var = $$RegularNounFrame;
            if (NIL == member(item_var, frames, symbol_function(EQL), symbol_function(IDENTITY))) {
                frames = cons(item_var, frames);
            }
        } else
            if ((NIL == frames) && (NIL != psp_semantics.psp_genl_posP(pos, $$Adjective, UNPROVIDED))) {
                final SubLObject item_var = $$RegularAdjFrame;
                if (NIL == member(item_var, frames, symbol_function(EQL), symbol_function(IDENTITY))) {
                    frames = cons(item_var, frames);
                }
            } else
                if (((NIL == frames) && (denot != $NO_DENOT)) && (NIL != psp_semantics.psp_genl_posP(pos, $$Verb, UNPROVIDED))) {
                    frames = psp_devisable_verb_frames(mt);
                }


        frames = list_utilities.remove_if_not($sym275$PSP_CONSTRAINED_FRAME_, frames, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        return NIL != frames ? frames : $list327;
    }

    public static final SubLObject psp_find_frames_alt(SubLObject wu, SubLObject pos, SubLObject denot, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return psp_find_frames_internal(wu, pos, denot, mt);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, PSP_FIND_FRAMES, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), PSP_FIND_FRAMES, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, PSP_FIND_FRAMES, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_4(wu, pos, denot, mt);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw178$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (wu.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (pos.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (denot.equal(cached_args.first())) {
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
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(psp_find_frames_internal(wu, pos, denot, mt)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(wu, pos, denot, mt));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject psp_find_frames(final SubLObject wu, final SubLObject pos, final SubLObject denot, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = parsing_utilities.psp_lexicon_root_mt(UNPROVIDED);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return psp_find_frames_internal(wu, pos, denot, mt);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, PSP_FIND_FRAMES, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), PSP_FIND_FRAMES, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, PSP_FIND_FRAMES, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_4(wu, pos, denot, mt);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (wu.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (pos.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (denot.equal(cached_args.first())) {
                            cached_args = cached_args.rest();
                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt.equal(cached_args.first())) {
                                return memoization_state.caching_results(results2);
                            }
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(psp_find_frames_internal(wu, pos, denot, mt)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(wu, pos, denot, mt));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject psp_find_constant_alt(SubLObject v_object) {
        if (v_object.isString()) {
            return constants_high.find_constant(v_object);
        } else {
            return v_object;
        }
    }

    public static SubLObject psp_find_constant(final SubLObject v_object) {
        if (v_object.isString()) {
            return constants_high.find_constant(v_object);
        }
        return v_object;
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have cached semantics of OUTPUT-TYPE?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have cached semantics of OUTPUT-TYPE?")
    public static final SubLObject psp_edge_semx_doneP_alt(SubLObject edge, SubLObject output_type) {
        if (output_type == $DENOT_TEMPLATE) {
            return NIL;
        }
        return list_utilities.sublisp_boolean(dictionary.dictionary_lookup_without_values(get_psp_edge_cycls(edge), output_type, UNPROVIDED));
    }

    /**
     *
     *
     * @return BOOLEAN; Does EDGE have cached semantics of OUTPUT-TYPE?
     */
    @LispMethod(comment = "@return BOOLEAN; Does EDGE have cached semantics of OUTPUT-TYPE?")
    public static SubLObject psp_edge_semx_doneP(final SubLObject edge, final SubLObject output_type) {
        if (output_type == $DENOT_TEMPLATE) {
            return NIL;
        }
        return list_utilities.sublisp_boolean(dictionary.dictionary_lookup_without_values(get_psp_edge_cycls(edge), output_type, UNPROVIDED));
    }

    /**
     *
     *
     * @return LISTP; of the cached semantics of OUTPUT-TYPE of EDGE.
     */
    @LispMethod(comment = "@return LISTP; of the cached semantics of OUTPUT-TYPE of EDGE.")
    public static final SubLObject psp_edge_lookup_semx_alt(SubLObject edge, SubLObject output_type) {
        {
            SubLObject semx = dictionary.dictionary_lookup(get_psp_edge_cycls(edge), output_type, UNPROVIDED);
            return semx;
        }
    }

    /**
     *
     *
     * @return LISTP; of the cached semantics of OUTPUT-TYPE of EDGE.
     */
    @LispMethod(comment = "@return LISTP; of the cached semantics of OUTPUT-TYPE of EDGE.")
    public static SubLObject psp_edge_lookup_semx(final SubLObject edge, final SubLObject output_type) {
        final SubLObject semx = dictionary.dictionary_lookup(get_psp_edge_cycls(edge), output_type, UNPROVIDED);
        return semx;
    }

    public static final SubLObject psp_edge_to_pph_phrase_alt(SubLObject edge) {
        {
            SubLObject ans = pph_data_structures.new_pph_phrase(get_psp_edge_string(edge), UNPROVIDED, UNPROVIDED);
            SubLObject dtr_list = psp_edge_dtrs(edge);
            pph_phrase.pph_phrase_set_agr(ans, pph_data_structures.new_pph_phrase_agr(pph_utilities.new_pph_agr_constraint($POS_PRED, get_psp_edge_pos_pred(edge)), UNPROVIDED), UNPROVIDED);
            pph_phrase.pph_phrase_set_category(ans, get_psp_edge_category(edge), UNPROVIDED);
            pph_phrase.pph_phrase_set_wu(ans, get_psp_edge_wu(edge));
            pph_phrase.set_pph_phrase_dtrs_from_list(ans, Mapping.mapcar(PSP_EDGE_TO_PPH_PHRASE, dtr_list), UNPROVIDED);
            pph_phrase.pph_phrase_set_cycl(ans, best_cycl_for_edge(edge));
            {
                SubLObject head_dtr_num = psp_edge_head_dtr_num(edge);
                if (NIL != head_dtr_num) {
                    pph_phrase.pph_phrase_set_head_dtr_num(ans, number_utilities.f_1_(head_dtr_num));
                }
            }
            return ans;
        }
    }

    public static SubLObject psp_edge_to_pph_phrase(final SubLObject edge) {
        final SubLObject ans = pph_data_structures.new_pph_phrase(get_psp_edge_string(edge), UNPROVIDED, UNPROVIDED);
        final SubLObject dtr_list = psp_edge_dtrs(edge);
        pph_phrase.pph_phrase_set_agr(ans, pph_data_structures.new_pph_phrase_agr(pph_utilities.new_pph_agr_constraint($POS_PRED, get_psp_edge_pos_pred(edge)), UNPROVIDED), UNPROVIDED);
        pph_phrase.pph_phrase_set_category(ans, get_psp_edge_category(edge), UNPROVIDED);
        pph_phrase.pph_phrase_set_wu(ans, get_psp_edge_wu(edge));
        pph_phrase.set_pph_phrase_dtrs_from_list(ans, Mapping.mapcar(PSP_EDGE_TO_PPH_PHRASE, dtr_list), UNPROVIDED);
        pph_phrase.pph_phrase_set_cycl(ans, best_cycl_for_edge(edge));
        final SubLObject head_dtr_num = psp_edge_head_dtr_num(edge);
        if (NIL != head_dtr_num) {
            pph_phrase.pph_phrase_set_head_dtr_num(ans, number_utilities.f_1_(head_dtr_num));
        }
        return ans;
    }

    public static final SubLObject test_sentence_set_words_from_string_list_alt(SubLObject strings, SubLObject sentence_string) {
        {
            SubLObject sentence = document.new_sentence(UNPROVIDED, UNPROVIDED);
            return sentence_set_words_from_string_list(sentence, strings, sentence_string);
        }
    }

    public static SubLObject test_sentence_set_words_from_string_list(final SubLObject strings, final SubLObject sentence_string) {
        final SubLObject sentence = document.new_sentence(UNPROVIDED, UNPROVIDED);
        return sentence_set_words_from_string_list(sentence, strings, sentence_string);
    }

    public static final SubLObject word_offsets_equal_alt(SubLObject sentence, SubLObject word_offsets) {
        {
            SubLObject word = NIL;
            SubLObject word_22 = NIL;
            SubLObject offset = NIL;
            SubLObject offset_23 = NIL;
            for (word = vector_utilities.vector_elements(document.sentence_yield(sentence), UNPROVIDED), word_22 = word.first(), offset = word_offsets, offset_23 = offset.first(); !((NIL == offset) && (NIL == word)); word = word.rest() , word_22 = word.first() , offset = offset.rest() , offset_23 = offset.first()) {
                if (document.word_offset(word_22) != offset_23) {
                    return NIL;
                }
            }
        }
        return T;
    }

    public static SubLObject word_offsets_equal(final SubLObject sentence, final SubLObject word_offsets) {
        SubLObject word = NIL;
        SubLObject word_$24 = NIL;
        SubLObject offset = NIL;
        SubLObject offset_$25 = NIL;
        word = vector_utilities.vector_elements(document.sentence_yield(sentence), UNPROVIDED);
        word_$24 = word.first();
        offset = word_offsets;
        offset_$25 = offset.first();
        while ((NIL != offset) || (NIL != word)) {
            if (!document.word_offset(word_$24).eql(offset_$25)) {
                return NIL;
            }
            word = word.rest();
            word_$24 = word.first();
            offset = offset.rest();
            offset_$25 = offset.first();
        } 
        return T;
    }

    public static SubLObject declare_psp_chart_file() {
        declareFunction("phrase_structure_parser_chart_print_function_trampoline", "PHRASE-STRUCTURE-PARSER-CHART-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("phrase_structure_parser_chart_p", "PHRASE-STRUCTURE-PARSER-CHART-P", 1, 0, false);
        new psp_chart.$phrase_structure_parser_chart_p$UnaryFunction();
        declareFunction("psp_chart_id", "PSP-CHART-ID", 1, 0, false);
        declareFunction("psp_chart_input_string", "PSP-CHART-INPUT-STRING", 1, 0, false);
        declareFunction("psp_chart_length", "PSP-CHART-LENGTH", 1, 0, false);
        declareFunction("psp_chart_spans_to_edges", "PSP-CHART-SPANS-TO-EDGES", 1, 0, false);
        declareFunction("psp_chart_gap_type", "PSP-CHART-GAP-TYPE", 1, 0, false);
        declareFunction("psp_chart_memoization_state", "PSP-CHART-MEMOIZATION-STATE", 1, 0, false);
        declareFunction("psp_chart_edge_id_index", "PSP-CHART-EDGE-ID-INDEX", 1, 0, false);
        declareFunction("psp_chart_edges_to_nodes", "PSP-CHART-EDGES-TO-NODES", 1, 0, false);
        declareFunction("psp_chart_max_edges_per_span", "PSP-CHART-MAX-EDGES-PER-SPAN", 1, 0, false);
        declareFunction("psp_chart_max_edges", "PSP-CHART-MAX-EDGES", 1, 0, false);
        declareFunction("psp_chart_maxed_out_spans", "PSP-CHART-MAXED-OUT-SPANS", 1, 0, false);
        declareFunction("psp_chart_maxed_outP", "PSP-CHART-MAXED-OUT?", 1, 0, false);
        declareFunction("psp_chart_parsed_lexical_spans", "PSP-CHART-PARSED-LEXICAL-SPANS", 1, 0, false);
        declareFunction("_csetf_psp_chart_id", "_CSETF-PSP-CHART-ID", 2, 0, false);
        declareFunction("_csetf_psp_chart_input_string", "_CSETF-PSP-CHART-INPUT-STRING", 2, 0, false);
        declareFunction("_csetf_psp_chart_length", "_CSETF-PSP-CHART-LENGTH", 2, 0, false);
        declareFunction("_csetf_psp_chart_spans_to_edges", "_CSETF-PSP-CHART-SPANS-TO-EDGES", 2, 0, false);
        declareFunction("_csetf_psp_chart_gap_type", "_CSETF-PSP-CHART-GAP-TYPE", 2, 0, false);
        declareFunction("_csetf_psp_chart_memoization_state", "_CSETF-PSP-CHART-MEMOIZATION-STATE", 2, 0, false);
        declareFunction("_csetf_psp_chart_edge_id_index", "_CSETF-PSP-CHART-EDGE-ID-INDEX", 2, 0, false);
        declareFunction("_csetf_psp_chart_edges_to_nodes", "_CSETF-PSP-CHART-EDGES-TO-NODES", 2, 0, false);
        declareFunction("_csetf_psp_chart_max_edges_per_span", "_CSETF-PSP-CHART-MAX-EDGES-PER-SPAN", 2, 0, false);
        declareFunction("_csetf_psp_chart_max_edges", "_CSETF-PSP-CHART-MAX-EDGES", 2, 0, false);
        declareFunction("_csetf_psp_chart_maxed_out_spans", "_CSETF-PSP-CHART-MAXED-OUT-SPANS", 2, 0, false);
        declareFunction("_csetf_psp_chart_maxed_outP", "_CSETF-PSP-CHART-MAXED-OUT?", 2, 0, false);
        declareFunction("_csetf_psp_chart_parsed_lexical_spans", "_CSETF-PSP-CHART-PARSED-LEXICAL-SPANS", 2, 0, false);
        declareFunction("make_phrase_structure_parser_chart", "MAKE-PHRASE-STRUCTURE-PARSER-CHART", 0, 1, false);
        declareFunction("visit_defstruct_phrase_structure_parser_chart", "VISIT-DEFSTRUCT-PHRASE-STRUCTURE-PARSER-CHART", 2, 0, false);
        declareFunction("visit_defstruct_object_phrase_structure_parser_chart_method", "VISIT-DEFSTRUCT-OBJECT-PHRASE-STRUCTURE-PARSER-CHART-METHOD", 2, 0, false);
        declareFunction("pprint_psp_chart", "PPRINT-PSP-CHART", 1, 2, false);
        declareFunction("new_psp_chart", "NEW-PSP-CHART", 0, 2, false);
        declareFunction("destroy_psp_chart", "DESTROY-PSP-CHART", 0, 1, false);
        declareFunction("psp_chart_clear", "PSP-CHART-CLEAR", 1, 0, false);
        declareFunction("psp_chart_set_string", "PSP-CHART-SET-STRING", 2, 0, false);
        declareFunction("psp_chart_new_edge_id", "PSP-CHART-NEW-EDGE-ID", 1, 0, false);
        declareFunction("psp_chart_index_edgesP", "PSP-CHART-INDEX-EDGES?", 0, 0, false);
        declareFunction("psp_find_edge_by_id", "PSP-FIND-EDGE-BY-ID", 1, 1, false);
        declareFunction("psp_chart_set_gap_type", "PSP-CHART-SET-GAP-TYPE", 2, 0, false);
        declareFunction("phrase_structure_parser_phrasal_edge_print_function_trampoline", "PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("phrase_structure_parser_phrasal_edge_p", "PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-P", 1, 0, false);
        new psp_chart.$phrase_structure_parser_phrasal_edge_p$UnaryFunction();
        declareFunction("psp_phrasal_edge_weight", "PSP-PHRASAL-EDGE-WEIGHT", 1, 0, false);
        declareFunction("psp_phrasal_edge_string", "PSP-PHRASAL-EDGE-STRING", 1, 0, false);
        declareFunction("psp_phrasal_edge_span", "PSP-PHRASAL-EDGE-SPAN", 1, 0, false);
        declareFunction("psp_phrasal_edge_category", "PSP-PHRASAL-EDGE-CATEGORY", 1, 0, false);
        declareFunction("psp_phrasal_edge_pos_pred", "PSP-PHRASAL-EDGE-POS-PRED", 1, 0, false);
        declareFunction("psp_phrasal_edge_cycls", "PSP-PHRASAL-EDGE-CYCLS", 1, 0, false);
        declareFunction("psp_phrasal_edge_rule", "PSP-PHRASAL-EDGE-RULE", 1, 0, false);
        declareFunction("psp_phrasal_edge_new_dtr_constraints", "PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS", 1, 0, false);
        declareFunction("psp_phrasal_edge_head_dtr", "PSP-PHRASAL-EDGE-HEAD-DTR", 1, 0, false);
        declareFunction("psp_phrasal_edge_non_head_dtrs", "PSP-PHRASAL-EDGE-NON-HEAD-DTRS", 1, 0, false);
        declareFunction("psp_phrasal_edge_id", "PSP-PHRASAL-EDGE-ID", 1, 0, false);
        declareFunction("psp_phrasal_edge_chart", "PSP-PHRASAL-EDGE-CHART", 1, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_weight", "_CSETF-PSP-PHRASAL-EDGE-WEIGHT", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_string", "_CSETF-PSP-PHRASAL-EDGE-STRING", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_span", "_CSETF-PSP-PHRASAL-EDGE-SPAN", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_category", "_CSETF-PSP-PHRASAL-EDGE-CATEGORY", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_pos_pred", "_CSETF-PSP-PHRASAL-EDGE-POS-PRED", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_cycls", "_CSETF-PSP-PHRASAL-EDGE-CYCLS", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_rule", "_CSETF-PSP-PHRASAL-EDGE-RULE", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_new_dtr_constraints", "_CSETF-PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_head_dtr", "_CSETF-PSP-PHRASAL-EDGE-HEAD-DTR", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_non_head_dtrs", "_CSETF-PSP-PHRASAL-EDGE-NON-HEAD-DTRS", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_id", "_CSETF-PSP-PHRASAL-EDGE-ID", 2, 0, false);
        declareFunction("_csetf_psp_phrasal_edge_chart", "_CSETF-PSP-PHRASAL-EDGE-CHART", 2, 0, false);
        declareFunction("make_phrase_structure_parser_phrasal_edge", "MAKE-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE", 0, 1, false);
        declareFunction("visit_defstruct_phrase_structure_parser_phrasal_edge", "VISIT-DEFSTRUCT-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE", 2, 0, false);
        declareFunction("visit_defstruct_object_phrase_structure_parser_phrasal_edge_method", "VISIT-DEFSTRUCT-OBJECT-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-METHOD", 2, 0, false);
        declareFunction("sxhash_phrase_structure_parser_phrasal_edge_method", "SXHASH-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-METHOD", 1, 0, false);
        declareFunction("pprint_psp_phrasal_edge", "PPRINT-PSP-PHRASAL-EDGE", 1, 2, false);
        declareFunction("phrase_structure_parser_lexical_edge_print_function_trampoline", "PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("phrase_structure_parser_lexical_edge_p", "PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-P", 1, 0, false);
        new psp_chart.$phrase_structure_parser_lexical_edge_p$UnaryFunction();
        declareFunction("psp_lexical_edge_weight", "PSP-LEXICAL-EDGE-WEIGHT", 1, 0, false);
        declareFunction("psp_lexical_edge_string", "PSP-LEXICAL-EDGE-STRING", 1, 0, false);
        declareFunction("psp_lexical_edge_span", "PSP-LEXICAL-EDGE-SPAN", 1, 0, false);
        declareFunction("psp_lexical_edge_category", "PSP-LEXICAL-EDGE-CATEGORY", 1, 0, false);
        declareFunction("psp_lexical_edge_pos_pred", "PSP-LEXICAL-EDGE-POS-PRED", 1, 0, false);
        declareFunction("psp_lexical_edge_cycls", "PSP-LEXICAL-EDGE-CYCLS", 1, 0, false);
        declareFunction("psp_lexical_edge_wu", "PSP-LEXICAL-EDGE-WU", 1, 0, false);
        declareFunction("psp_lexical_edge_frame", "PSP-LEXICAL-EDGE-FRAME", 1, 0, false);
        declareFunction("psp_lexical_edge_id", "PSP-LEXICAL-EDGE-ID", 1, 0, false);
        declareFunction("psp_lexical_edge_chart", "PSP-LEXICAL-EDGE-CHART", 1, 0, false);
        declareFunction("_csetf_psp_lexical_edge_weight", "_CSETF-PSP-LEXICAL-EDGE-WEIGHT", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_string", "_CSETF-PSP-LEXICAL-EDGE-STRING", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_span", "_CSETF-PSP-LEXICAL-EDGE-SPAN", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_category", "_CSETF-PSP-LEXICAL-EDGE-CATEGORY", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_pos_pred", "_CSETF-PSP-LEXICAL-EDGE-POS-PRED", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_cycls", "_CSETF-PSP-LEXICAL-EDGE-CYCLS", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_wu", "_CSETF-PSP-LEXICAL-EDGE-WU", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_frame", "_CSETF-PSP-LEXICAL-EDGE-FRAME", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_id", "_CSETF-PSP-LEXICAL-EDGE-ID", 2, 0, false);
        declareFunction("_csetf_psp_lexical_edge_chart", "_CSETF-PSP-LEXICAL-EDGE-CHART", 2, 0, false);
        declareFunction("make_phrase_structure_parser_lexical_edge", "MAKE-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE", 0, 1, false);
        declareFunction("visit_defstruct_phrase_structure_parser_lexical_edge", "VISIT-DEFSTRUCT-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE", 2, 0, false);
        declareFunction("visit_defstruct_object_phrase_structure_parser_lexical_edge_method", "VISIT-DEFSTRUCT-OBJECT-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-METHOD", 2, 0, false);
        declareFunction("sxhash_psp_edge", "SXHASH-PSP-EDGE", 1, 0, false);
        declareFunction("sxhash_phrase_structure_parser_lexical_edge_method", "SXHASH-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-METHOD", 1, 0, false);
        declareFunction("psp_chart_spans", "PSP-CHART-SPANS", 0, 3, false);
        declareFunction("pprint_psp_lexical_edge", "PPRINT-PSP-LEXICAL-EDGE", 1, 2, false);
        declareFunction("psp_format_edge", "PSP-FORMAT-EDGE", 1, 1, false);
        declareFunction("phrase_structure_parser_edge_p", "PHRASE-STRUCTURE-PARSER-EDGE-P", 1, 0, false);
        declareFunction("get_psp_edge_weight", "GET-PSP-EDGE-WEIGHT", 1, 0, false);
        declareFunction("get_psp_edge_string", "GET-PSP-EDGE-STRING", 1, 0, false);
        declareFunction("get_psp_edge_span", "GET-PSP-EDGE-SPAN", 1, 0, false);
        declareFunction("get_psp_edge_category", "GET-PSP-EDGE-CATEGORY", 1, 0, false);
        declareFunction("get_psp_edge_pos_pred", "GET-PSP-EDGE-POS-PRED", 1, 0, false);
        declareFunction("get_psp_edge_cycls", "GET-PSP-EDGE-CYCLS", 1, 0, false);
        declareFunction("get_psp_edge_rule", "GET-PSP-EDGE-RULE", 1, 0, false);
        declareFunction("get_psp_edge_new_dtr_constraints", "GET-PSP-EDGE-NEW-DTR-CONSTRAINTS", 1, 0, false);
        declareFunction("get_psp_edge_head_dtr", "GET-PSP-EDGE-HEAD-DTR", 1, 0, false);
        declareFunction("get_psp_edge_non_head_dtrs", "GET-PSP-EDGE-NON-HEAD-DTRS", 1, 0, false);
        declareFunction("get_psp_edge_wu", "GET-PSP-EDGE-WU", 1, 0, false);
        declareFunction("get_psp_edge_frame", "GET-PSP-EDGE-FRAME", 1, 0, false);
        declareFunction("get_psp_edge_id", "GET-PSP-EDGE-ID", 1, 0, false);
        declareFunction("get_psp_edge_chart", "GET-PSP-EDGE-CHART", 1, 0, false);
        declareFunction("get_psp_edge_head_lexeme", "GET-PSP-EDGE-HEAD-LEXEME", 1, 0, false);
        declareFunction("set_psp_edge_weight", "SET-PSP-EDGE-WEIGHT", 2, 0, false);
        declareFunction("set_psp_edge_string", "SET-PSP-EDGE-STRING", 2, 0, false);
        declareFunction("set_psp_edge_span", "SET-PSP-EDGE-SPAN", 2, 0, false);
        declareFunction("set_psp_edge_category", "SET-PSP-EDGE-CATEGORY", 2, 0, false);
        declareFunction("set_psp_edge_pos_pred", "SET-PSP-EDGE-POS-PRED", 2, 0, false);
        declareFunction("set_psp_edge_cycls", "SET-PSP-EDGE-CYCLS", 2, 0, false);
        declareFunction("set_psp_edge_rule", "SET-PSP-EDGE-RULE", 2, 0, false);
        declareFunction("set_psp_edge_new_dtr_constraints", "SET-PSP-EDGE-NEW-DTR-CONSTRAINTS", 2, 0, false);
        declareFunction("set_psp_edge_head_dtr", "SET-PSP-EDGE-HEAD-DTR", 2, 0, false);
        declareFunction("set_psp_edge_non_head_dtrs", "SET-PSP-EDGE-NON-HEAD-DTRS", 2, 0, false);
        declareFunction("set_psp_edge_wu", "SET-PSP-EDGE-WU", 2, 0, false);
        declareFunction("set_psp_edge_frame", "SET-PSP-EDGE-FRAME", 2, 0, false);
        declareFunction("set_psp_edge_id", "SET-PSP-EDGE-ID", 2, 0, false);
        declareFunction("set_psp_edge_chart", "SET-PSP-EDGE-CHART", 2, 0, false);
        declareMacro("psp_do_spans", "PSP-DO-SPANS");
        declareMacro("psp_do_edges_for_span", "PSP-DO-EDGES-FOR-SPAN");
        declareMacro("psp_do_lexical_edges_for_span", "PSP-DO-LEXICAL-EDGES-FOR-SPAN");
        declareFunction("psp_do_edge_okP", "PSP-DO-EDGE-OK?", 3, 0, false);
        declareMacro("psp_do_edges", "PSP-DO-EDGES");
        declareFunction("psp_weight_p", "PSP-WEIGHT-P", 1, 0, false);
        declareFunction("psp_unknown_weight_p", "PSP-UNKNOWN-WEIGHT-P", 1, 0, false);
        declareFunction("possibly_psp_known_weight_p", "POSSIBLY-PSP-KNOWN-WEIGHT-P", 1, 0, false);
        declareFunction("psp_weight_G", "PSP-WEIGHT->", 2, 1, false);
        declareFunction("psp_weight_L", "PSP-WEIGHT-<", 2, 1, false);
        declareFunction("psp_weight_compose", "PSP-WEIGHT-COMPOSE", 1, 0, false);
        declareFunction("psp_weight_compose_two", "PSP-WEIGHT-COMPOSE-TWO", 2, 0, false);
        declareFunction("psp_edge_weight_unknown_p", "PSP-EDGE-WEIGHT-UNKNOWN-P", 1, 0, false);
        declareFunction("psp_edge_weight_from_dtrs", "PSP-EDGE-WEIGHT-FROM-DTRS", 3, 0, false);
        declareFunction("clear_psp_np_gap_semx", "CLEAR-PSP-NP-GAP-SEMX", 0, 0, false);
        declareFunction("remove_psp_np_gap_semx", "REMOVE-PSP-NP-GAP-SEMX", 0, 0, false);
        declareFunction("psp_np_gap_semx_internal", "PSP-NP-GAP-SEMX-INTERNAL", 0, 0, false);
        declareFunction("psp_np_gap_semx", "PSP-NP-GAP-SEMX", 0, 0, false);
        declareFunction("psp_np_gap_referent", "PSP-NP-GAP-REFERENT", 0, 0, false);
        declareFunction("new_psp_gap_edge", "NEW-PSP-GAP-EDGE", 2, 0, false);
        declareFunction("psp_gap_edge_p", "PSP-GAP-EDGE-P", 1, 0, false);
        declareFunction("psp_np_gap_edge_p", "PSP-NP-GAP-EDGE-P", 1, 0, false);
        declareFunction("psp_preposition_gap_edge_p", "PSP-PREPOSITION-GAP-EDGE-P", 1, 0, false);
        declareFunction("psp_pp_gap_edge_p", "PSP-PP-GAP-EDGE-P", 1, 0, false);
        declareFunction("psp_gap_constraint_p", "PSP-GAP-CONSTRAINT-P", 1, 0, false);
        declareFunction("psp_allowed_gap_category", "PSP-ALLOWED-GAP-CATEGORY", 0, 0, false);
        declareFunction("psp_edge_already_has_gapP", "PSP-EDGE-ALREADY-HAS-GAP?", 1, 0, false);
        declareFunction("psp_edge_has_np_gapP", "PSP-EDGE-HAS-NP-GAP?", 1, 0, false);
        declareFunction("psp_edge_has_pp_gapP", "PSP-EDGE-HAS-PP-GAP?", 1, 0, false);
        declareFunction("psp_gap_allowedP", "PSP-GAP-ALLOWED?", 0, 0, false);
        declareFunction("psp_pp_gap_allowedP", "PSP-PP-GAP-ALLOWED?", 0, 0, false);
        declareFunction("psp_pp_gap_constraint_p", "PSP-PP-GAP-CONSTRAINT-P", 1, 0, false);
        declareFunction("psp_pp_gap_string", "PSP-PP-GAP-STRING", 0, 0, false);
        declareFunction("new_psp_phrasal_edge", "NEW-PSP-PHRASAL-EDGE", 3, 8, false);
        declareFunction("psp_maybe_setify_dtrs", "PSP-MAYBE-SETIFY-DTRS", 2, 0, false);
        declareFunction("new_psp_lexical_edge", "NEW-PSP-LEXICAL-EDGE", 3, 6, false);
        declareFunction("psp_tokenization_mode_p", "PSP-TOKENIZATION-MODE-P", 1, 0, false);
        declareFunction("sentence_set_words_from_string_list", "SENTENCE-SET-WORDS-FROM-STRING-LIST", 3, 0, false);
        declareFunction("initialize_psp_chart", "INITIALIZE-PSP-CHART", 1, 3, false);
        declareFunction("psp_add_edges_from_sentence", "PSP-ADD-EDGES-FROM-SENTENCE", 4, 0, false);
        declareFunction("psp_edge_adjust_weight_for_mode", "PSP-EDGE-ADJUST-WEIGHT-FOR-MODE", 2, 0, false);
        declareFunction("closed_lexical_class_edgeP", "CLOSED-LEXICAL-CLASS-EDGE?", 1, 0, false);
        declareFunction("psp_default_weight_for_mode", "PSP-DEFAULT-WEIGHT-FOR-MODE", 1, 0, false);
        declareFunction("psp_chart_has_better_edgeP", "PSP-CHART-HAS-BETTER-EDGE?", 2, 0, false);
        declareFunction("psp_better_edgeP", "PSP-BETTER-EDGE?", 2, 0, false);
        declareFunction("psp_edges_same_except_for_weightP", "PSP-EDGES-SAME-EXCEPT-FOR-WEIGHT?", 2, 0, false);
        declareFunction("psp_edge_weightierP", "PSP-EDGE-WEIGHTIER?", 2, 1, false);
        declareFunction("psp_edge_weights_equalP", "PSP-EDGE-WEIGHTS-EQUAL?", 2, 1, false);
        declareFunction("psp_string_tokenize", "PSP-STRING-TOKENIZE", 1, 0, false);
        declareFunction("psp_sentence_edgify", "PSP-SENTENCE-EDGIFY", 1, 2, false);
        declareFunction("psp_add_edge_to_result_vector", "PSP-ADD-EDGE-TO-RESULT-VECTOR", 2, 0, false);
        declareFunction("psp_add_lexicon_edges", "PSP-ADD-LEXICON-EDGES", 2, 1, false);
        declareFunction("get_raw_word_count_from_word", "GET-RAW-WORD-COUNT-FROM-WORD", 1, 0, false);
        declareFunction("fscp_multi_parse_category_p", "FSCP-MULTI-PARSE-CATEGORY-P", 1, 0, false);
        declareFunction("psp_add_fscp_edges", "PSP-ADD-FSCP-EDGES", 6, 0, false);
        declareFunction("psp_sentence_copy", "PSP-SENTENCE-COPY", 1, 0, false);
        declareFunction("psp_word_copy", "PSP-WORD-COPY", 1, 0, false);
        declareFunction("psp_add_english_number_edges", "PSP-ADD-ENGLISH-NUMBER-EDGES", 2, 0, false);
        declareFunction("psp_add_digit_edges", "PSP-ADD-DIGIT-EDGES", 2, 0, false);
        declareFunction("psp_add_quantity_edges", "PSP-ADD-QUANTITY-EDGES", 2, 0, false);
        declareFunction("psp_add_guessed_edges", "PSP-ADD-GUESSED-EDGES", 2, 0, false);
        declareFunction("psp_add_guessed_edge", "PSP-ADD-GUESSED-EDGE", 4, 0, false);
        declareFunction("psp_edge_covering_startP", "PSP-EDGE-COVERING-START?", 2, 0, false);
        declareFunction("psp_spans_starting_at_start", "PSP-SPANS-STARTING-AT-START", 1, 1, false);
        declareFunction("psp_add_indexical_edges", "PSP-ADD-INDEXICAL-EDGES", 2, 0, false);
        declareFunction("psp_maybe_guess_info_for_unknown_string", "PSP-MAYBE-GUESS-INFO-FOR-UNKNOWN-STRING", 3, 0, false);
        declareFunction("psp_type_for_unknown_string", "PSP-TYPE-FOR-UNKNOWN-STRING", 1, 0, false);
        declareFunction("psp_scalar_intervalP", "PSP-SCALAR-INTERVAL?", 1, 0, false);
        declareFunction("filter_prefixes_by_mt", "FILTER-PREFIXES-BY-MT", 2, 0, false);
        declareFunction("psp_passive_marker", "PSP-PASSIVE-MARKER", 0, 0, false);
        declareFunction("psp_passive_edges", "PSP-PASSIVE-EDGES", 7, 0, false);
        declareFunction("psp_deverbal_edges", "PSP-DEVERBAL-EDGES", 7, 0, false);
        declareFunction("deverbal_noun_posP", "DEVERBAL-NOUN-POS?", 1, 0, false);
        declareFunction("psp_frame_passivizableP", "PSP-FRAME-PASSIVIZABLE?", 1, 0, false);
        declareFunction("psp_edge_cycl_passes_name_checkP", "PSP-EDGE-CYCL-PASSES-NAME-CHECK?", 2, 0, false);
        declareFunction("string_has_non_name_denotsP", "STRING-HAS-NON-NAME-DENOTS?", 1, 0, false);
        declareFunction("psp_chart_add_edge", "PSP-CHART-ADD-EDGE", 1, 1, false);
        declareFunction("psp_edge_set_lookup_node", "PSP-EDGE-SET-LOOKUP-NODE", 1, 1, false);
        declareFunction("psp_edge_set_note_node", "PSP-EDGE-SET-NOTE-NODE", 2, 0, false);
        declareFunction("psp_edge_lookup_node", "PSP-EDGE-LOOKUP-NODE", 1, 1, false);
        declareFunction("psp_edge_note_node", "PSP-EDGE-NOTE-NODE", 2, 0, false);
        declareFunction("psp_edge_rule_struct", "PSP-EDGE-RULE-STRUCT", 1, 0, false);
        declareFunction("psp_edge_rule_fort", "PSP-EDGE-RULE-FORT", 1, 0, false);
        declareFunction("psp_longest_edges", "PSP-LONGEST-EDGES", 1, 0, false);
        declareFunction("best_cycl_for_edge", "BEST-CYCL-FOR-EDGE", 1, 0, false);
        declareFunction("psp_edges_with_span", "PSP-EDGES-WITH-SPAN", 1, 1, false);
        declareFunction("psp_lexical_edges_with_span", "PSP-LEXICAL-EDGES-WITH-SPAN", 1, 1, false);
        declareFunction("psp_completed_edgeP", "PSP-COMPLETED-EDGE?", 1, 0, false);
        declareFunction("psp_incomplete_edgeP", "PSP-INCOMPLETE-EDGE?", 1, 0, false);
        declareFunction("psp_lexical_edgeP", "PSP-LEXICAL-EDGE?", 1, 0, false);
        declareFunction("psp_edge_might_have_dtrs_p", "PSP-EDGE-MIGHT-HAVE-DTRS-P", 1, 0, false);
        declareFunction("psp_phrasal_edgeP", "PSP-PHRASAL-EDGE?", 1, 0, false);
        declareFunction("psp_adjp_edgeP", "PSP-ADJP-EDGE?", 1, 0, false);
        declareFunction("psp_predicative_adjp_edgeP", "PSP-PREDICATIVE-ADJP-EDGE?", 1, 0, false);
        declareFunction("psp_vbar_edgeP", "PSP-VBAR-EDGE?", 1, 0, false);
        declareFunction("psp_nbar_edgeP", "PSP-NBAR-EDGE?", 1, 0, false);
        declareFunction("psp_np_edgeP", "PSP-NP-EDGE?", 1, 0, false);
        declareFunction("psp_edge_starts_with_detpP", "PSP-EDGE-STARTS-WITH-DETP?", 1, 0, false);
        declareFunction("psp_pp_edgeP", "PSP-PP-EDGE?", 1, 0, false);
        declareFunction("psp_passive_edgeP", "PSP-PASSIVE-EDGE?", 1, 0, false);
        declareFunction("psp_edge_meets_pos_pred_constraintP", "PSP-EDGE-MEETS-POS-PRED-CONSTRAINT?", 2, 0, false);
        declareFunction("first_edge_matching_pos_pred", "FIRST-EDGE-MATCHING-POS-PRED", 2, 0, false);
        declareFunction("psp_edge_next_dtr_constraints", "PSP-EDGE-NEXT-DTR-CONSTRAINTS", 1, 0, false);
        declareFunction("psp_edge_later_dtr_constraints", "PSP-EDGE-LATER-DTR-CONSTRAINTS", 1, 0, false);
        declareFunction("psp_edge_head_dtr_num", "PSP-EDGE-HEAD-DTR-NUM", 1, 0, false);
        declareFunction("psp_edge_dtr_count", "PSP-EDGE-DTR-COUNT", 1, 0, false);
        declareFunction("psp_edge_dtrs", "PSP-EDGE-DTRS", 1, 0, false);
        declareFunction("psp_edge_non_branching_descendentP", "PSP-EDGE-NON-BRANCHING-DESCENDENT?", 2, 0, false);
        declareFunction("psp_edge_sem_head_dtr", "PSP-EDGE-SEM-HEAD-DTR", 1, 0, false);
        declareFunction("psp_edge_lexical_head_dtr", "PSP-EDGE-LEXICAL-HEAD-DTR", 1, 0, false);
        declareFunction("psp_edge_nth_dtr", "PSP-EDGE-NTH-DTR", 2, 0, false);
        declareFunction("psp_edge_sole_dtr", "PSP-EDGE-SOLE-DTR", 1, 0, false);
        declareFunction("psp_edge_precedesP", "PSP-EDGE-PRECEDES?", 2, 0, false);
        declareFunction("psp_edge_equalP", "PSP-EDGE-EQUAL?", 2, 0, false);
        declareFunction("psp_edge_start_index", "PSP-EDGE-START-INDEX", 1, 0, false);
        declareFunction("psp_edge_end_index", "PSP-EDGE-END-INDEX", 1, 0, false);
        declareFunction("psp_chart_max_span", "PSP-CHART-MAX-SPAN", 0, 0, false);
        declareFunction("psp_all_complete_edges", "PSP-ALL-COMPLETE-EDGES", 0, 1, false);
        declareFunction("psp_input_spanning_edges", "PSP-INPUT-SPANNING-EDGES", 0, 1, false);
        declareFunction("psp_complete_edges_for_span", "PSP-COMPLETE-EDGES-FOR-SPAN", 1, 0, false);
        declareFunction("psp_edge_count", "PSP-EDGE-COUNT", 0, 1, false);
        declareFunction("psp_chart_has_unknown_stringsP", "PSP-CHART-HAS-UNKNOWN-STRINGS?", 0, 1, false);
        declareFunction("psp_edge_count_for_span", "PSP-EDGE-COUNT-FOR-SPAN", 1, 1, false);
        declareFunction("psp_span_has_edges_p", "PSP-SPAN-HAS-EDGES-P", 1, 1, false);
        declareFunction("psp_chart_too_many_edgesP", "PSP-CHART-TOO-MANY-EDGES?", 0, 1, false);
        declareFunction("psp_span_too_many_edgesP", "PSP-SPAN-TOO-MANY-EDGES?", 1, 1, false);
        declareFunction("psp_span_too_many_edgesP_int", "PSP-SPAN-TOO-MANY-EDGES?-INT", 1, 1, false);
        declareFunction("psp_lexical_span_parsedP", "PSP-LEXICAL-SPAN-PARSED?", 1, 1, false);
        declareFunction("note_psp_lexical_span_parsed", "NOTE-PSP-LEXICAL-SPAN-PARSED", 1, 1, false);
        declareFunction("psp_np_edges", "PSP-NP-EDGES", 0, 0, false);
        declareFunction("psp_input_spanning_edge_count", "PSP-INPUT-SPANNING-EDGE-COUNT", 0, 0, false);
        declareFunction("psp_bindings_from_head", "PSP-BINDINGS-FROM-HEAD", 1, 0, false);
        declareFunction("cache_psp_frame_info", "CACHE-PSP-FRAME-INFO", 0, 0, false);
        declareFunction("recache_psp_frame_info", "RECACHE-PSP-FRAME-INFO", 0, 0, false);
        declareFunction("clear_psp_frame_info", "CLEAR-PSP-FRAME-INFO", 0, 0, false);
        declareFunction("add_subcat_frame_info", "ADD-SUBCAT-FRAME-INFO", 2, 0, false);
        declareFunction("remove_subcat_frame_info", "REMOVE-SUBCAT-FRAME-INFO", 2, 0, false);
        declareFunction("psp_bindings_from_frame", "PSP-BINDINGS-FROM-FRAME", 1, 0, false);
        declareFunction("clear_psp_bindings_from_frame_int", "CLEAR-PSP-BINDINGS-FROM-FRAME-INT", 0, 0, false);
        declareFunction("remove_psp_bindings_from_frame_int", "REMOVE-PSP-BINDINGS-FROM-FRAME-INT", 1, 1, false);
        declareFunction("psp_bindings_from_frame_int_internal", "PSP-BINDINGS-FROM-FRAME-INT-INTERNAL", 2, 0, false);
        declareFunction("psp_bindings_from_frame_int", "PSP-BINDINGS-FROM-FRAME-INT", 1, 1, false);
        declareFunction("clear_psp_predicative_frameP", "CLEAR-PSP-PREDICATIVE-FRAME?", 0, 0, false);
        declareFunction("remove_psp_predicative_frameP", "REMOVE-PSP-PREDICATIVE-FRAME?", 1, 0, false);
        declareFunction("psp_predicative_frameP_internal", "PSP-PREDICATIVE-FRAME?-INTERNAL", 1, 0, false);
        declareFunction("psp_predicative_frameP", "PSP-PREDICATIVE-FRAME?", 1, 0, false);
        declareFunction("clear_psp_keywords_for_frame", "CLEAR-PSP-KEYWORDS-FOR-FRAME", 0, 0, false);
        declareFunction("remove_psp_keywords_for_frame", "REMOVE-PSP-KEYWORDS-FOR-FRAME", 1, 0, false);
        declareFunction("psp_keywords_for_frame_internal", "PSP-KEYWORDS-FOR-FRAME-INTERNAL", 1, 0, false);
        declareFunction("psp_keywords_for_frame", "PSP-KEYWORDS-FOR-FRAME", 1, 0, false);
        declareFunction("clear_psp_constrained_frameP", "CLEAR-PSP-CONSTRAINED-FRAME?", 0, 0, false);
        declareFunction("remove_psp_constrained_frameP", "REMOVE-PSP-CONSTRAINED-FRAME?", 1, 1, false);
        declareFunction("psp_constrained_frameP_internal", "PSP-CONSTRAINED-FRAME?-INTERNAL", 2, 0, false);
        declareFunction("psp_constrained_frameP", "PSP-CONSTRAINED-FRAME?", 1, 1, false);
        declareFunction("clear_psp_devisable_verb_frames", "CLEAR-PSP-DEVISABLE-VERB-FRAMES", 0, 0, false);
        declareFunction("remove_psp_devisable_verb_frames", "REMOVE-PSP-DEVISABLE-VERB-FRAMES", 0, 1, false);
        declareFunction("psp_devisable_verb_frames_internal", "PSP-DEVISABLE-VERB-FRAMES-INTERNAL", 1, 0, false);
        declareFunction("psp_devisable_verb_frames", "PSP-DEVISABLE-VERB-FRAMES", 0, 1, false);
        declareFunction("clear_transitive_np_comp_frameP", "CLEAR-TRANSITIVE-NP-COMP-FRAME?", 0, 0, false);
        declareFunction("remove_transitive_np_comp_frameP", "REMOVE-TRANSITIVE-NP-COMP-FRAME?", 1, 1, false);
        declareFunction("transitive_np_comp_frameP_internal", "TRANSITIVE-NP-COMP-FRAME?-INTERNAL", 2, 0, false);
        declareFunction("transitive_np_comp_frameP", "TRANSITIVE-NP-COMP-FRAME?", 1, 1, false);
        declareFunction("clear_intransitive_frameP", "CLEAR-INTRANSITIVE-FRAME?", 0, 0, false);
        declareFunction("remove_intransitive_frameP", "REMOVE-INTRANSITIVE-FRAME?", 1, 1, false);
        declareFunction("intransitive_frameP_internal", "INTRANSITIVE-FRAME?-INTERNAL", 2, 0, false);
        declareFunction("intransitive_frameP", "INTRANSITIVE-FRAME?", 1, 1, false);
        declareFunction("clear_psp_frame_has_typeP", "CLEAR-PSP-FRAME-HAS-TYPE?", 0, 0, false);
        declareFunction("remove_psp_frame_has_typeP", "REMOVE-PSP-FRAME-HAS-TYPE?", 2, 0, false);
        declareFunction("psp_frame_has_typeP_internal", "PSP-FRAME-HAS-TYPE?-INTERNAL", 2, 0, false);
        declareFunction("psp_frame_has_typeP", "PSP-FRAME-HAS-TYPE?", 2, 0, false);
        declareFunction("clear_psp_passivized_dtr_num", "CLEAR-PSP-PASSIVIZED-DTR-NUM", 0, 0, false);
        declareFunction("remove_psp_passivized_dtr_num", "REMOVE-PSP-PASSIVIZED-DTR-NUM", 1, 1, false);
        declareFunction("psp_passivized_dtr_num_internal", "PSP-PASSIVIZED-DTR-NUM-INTERNAL", 2, 0, false);
        declareFunction("psp_passivized_dtr_num", "PSP-PASSIVIZED-DTR-NUM", 1, 1, false);
        declareFunction("psp_passivized_dtr_keyword", "PSP-PASSIVIZED-DTR-KEYWORD", 1, 0, false);
        declareFunction("psp_passive_frameP", "PSP-PASSIVE-FRAME?", 1, 0, false);
        declareFunction("psp_active_frame", "PSP-ACTIVE-FRAME", 1, 0, false);
        declareFunction("psp_passive_frame_type", "PSP-PASSIVE-FRAME-TYPE", 1, 0, false);
        declareFunction("psp_passivize_bindings", "PSP-PASSIVIZE-BINDINGS", 2, 0, false);
        declareFunction("psp_by_subject_binding", "PSP-BY-SUBJECT-BINDING", 1, 0, false);
        declareFunction("psp_add_cycl_to_cycls", "PSP-ADD-CYCL-TO-CYCLS", 2, 1, false);
        declareFunction("psp_new_cycls", "PSP-NEW-CYCLS", 0, 2, false);
        declareFunction("build_psp_edges_from_lex_entries", "BUILD-PSP-EDGES-FROM-LEX-ENTRIES", 5, 0, false);
        declareFunction("build_psp_edges_from_lex_entry", "BUILD-PSP-EDGES-FROM-LEX-ENTRY", 3, 0, false);
        declareFunction("lex_entry_get_trie_entry", "LEX-ENTRY-GET-TRIE-ENTRY", 1, 0, false);
        declareFunction("lex_entry_preferred_denots", "LEX-ENTRY-PREFERRED-DENOTS", 1, 0, false);
        declareFunction("psp_pos_preds_from_lex_entry", "PSP-POS-PREDS-FROM-LEX-ENTRY", 1, 0, false);
        declareFunction("psp_term_passes_constraintsP", "PSP-TERM-PASSES-CONSTRAINTS?", 1, 1, false);
        declareFunction("titles_conventionally_quoted", "TITLES-CONVENTIONALLY-QUOTED", 0, 0, false);
        declareFunction("psp_dispreference_level", "PSP-DISPREFERENCE-LEVEL", 3, 0, false);
        declareFunction("psp_disprefer_weight", "PSP-DISPREFER-WEIGHT", 1, 1, false);
        declareFunction("psp_reprefer_weight", "PSP-REPREFER-WEIGHT", 1, 1, false);
        declareFunction("psp_weight_for_lex_entry", "PSP-WEIGHT-FOR-LEX-ENTRY", 2, 0, false);
        declareFunction("psp_weight_for_trie_entry", "PSP-WEIGHT-FOR-TRIE-ENTRY", 2, 0, false);
        declareFunction("psp_disprefer_trie_entry_for_stringP", "PSP-DISPREFER-TRIE-ENTRY-FOR-STRING?", 2, 0, false);
        declareFunction("psp_term_has_preferred_lexificationsP_internal", "PSP-TERM-HAS-PREFERRED-LEXIFICATIONS?-INTERNAL", 1, 0, false);
        declareFunction("psp_term_has_preferred_lexificationsP", "PSP-TERM-HAS-PREFERRED-LEXIFICATIONS?", 1, 0, false);
        declareFunction("psp_filter_lexical_quads", "PSP-FILTER-LEXICAL-QUADS", 2, 0, false);
        declareFunction("psp_good_lexical_quadP", "PSP-GOOD-LEXICAL-QUAD?", 2, 0, false);
        declareFunction("psp_ok_pos_predP", "PSP-OK-POS-PRED?", 1, 0, false);
        declareFunction("psp_adjust_edge_weights", "PSP-ADJUST-EDGE-WEIGHTS", 1, 0, false);
        declareFunction("psp_adjust_edge_weight", "PSP-ADJUST-EDGE-WEIGHT", 2, 0, false);
        declareFunction("psp_consolidate_edges", "PSP-CONSOLIDATE-EDGES", 1, 0, false);
        declareFunction("psp_edge_add_cycl", "PSP-EDGE-ADD-CYCL", 2, 1, false);
        declareFunction("psp_delete_subsumed_edges", "PSP-DELETE-SUBSUMED-EDGES", 1, 0, false);
        declareFunction("psp_edge_subsumesP", "PSP-EDGE-SUBSUMES?", 2, 0, false);
        declareFunction("psp_edge_subsumedP", "PSP-EDGE-SUBSUMED?", 2, 0, false);
        declareFunction("psp_edge_pos_pred_subsumedP", "PSP-EDGE-POS-PRED-SUBSUMED?", 2, 0, false);
        declareFunction("psp_edge_category_subsumedP", "PSP-EDGE-CATEGORY-SUBSUMED?", 2, 0, false);
        declareFunction("psp_edge_frame_subsumedP", "PSP-EDGE-FRAME-SUBSUMED?", 2, 0, false);
        declareFunction("psp_frame_subsumedP", "PSP-FRAME-SUBSUMED?", 2, 0, false);
        declareFunction("psp_edge_cycls_subsumedP", "PSP-EDGE-CYCLS-SUBSUMED?", 2, 0, false);
        declareFunction("psp_cycls_subsumedP", "PSP-CYCLS-SUBSUMED?", 2, 0, false);
        declareFunction("psp_cycls_subsumedP_int", "PSP-CYCLS-SUBSUMED?-INT", 2, 0, false);
        declareFunction("psp_find_matching_edge", "PSP-FIND-MATCHING-EDGE", 2, 0, false);
        declareFunction("psp_edges_matchP", "PSP-EDGES-MATCH?", 2, 0, false);
        declareFunction("psp_find_frames_internal", "PSP-FIND-FRAMES-INTERNAL", 3, 1, false);
        declareFunction("psp_find_frames", "PSP-FIND-FRAMES", 3, 1, false);
        declareFunction("psp_find_constant", "PSP-FIND-CONSTANT", 1, 0, false);
        declareFunction("psp_edge_semx_doneP", "PSP-EDGE-SEMX-DONE?", 2, 0, false);
        declareFunction("psp_edge_lookup_semx", "PSP-EDGE-LOOKUP-SEMX", 2, 0, false);
        declareFunction("psp_edge_to_pph_phrase", "PSP-EDGE-TO-PPH-PHRASE", 1, 0, false);
        declareFunction("test_sentence_set_words_from_string_list", "TEST-SENTENCE-SET-WORDS-FROM-STRING-LIST", 2, 0, false);
        declareFunction("word_offsets_equal", "WORD-OFFSETS-EQUAL", 2, 0, false);
        return NIL;
    }

    public static final SubLObject init_psp_chart_file_alt() {
        deflexical("*PSP-PARSE-WH-WORDS?*", NIL);
        defconstant("*DTP-PHRASE-STRUCTURE-PARSER-CHART*", PHRASE_STRUCTURE_PARSER_CHART);
        deflexical("*PSP-CHART-ID-COUNTER*", NIL != boundp($psp_chart_id_counter$) ? ((SubLObject) ($psp_chart_id_counter$.getGlobalValue())) : ZERO_INTEGER);
        deflexical("*PSP-CHART-INDEX-EDGES?*", NIL);
        defconstant("*DTP-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE*", PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE);
        defconstant("*DTP-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE*", PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE);
        defconstant("*PSP-UNKNOWN-WEIGHT*", $UNKNOWN);
        defconstant("*PSP-EDGE-DEFAULT-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_85));
        defconstant("*PSP-EDGE-GREEDY-WEIGHT*", parsing_utilities.psp_weight_from_float($float$1_0));
        defconstant("*PSP-EDGE-DISPREFERRED-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_75));
        defconstant("*PSP-GAP-EDGE-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_9));
        deflexical("*PSP-NP-GAP-SEMX-CACHING-STATE*", NIL);
        defconstant("*PSP-TOKENIZATION-MODES*", $list_alt194);
        defparameter("*PSP-USE-TERM-LEXICON?*", NIL);
        deflexical("*FSCP-MULTI-PARSE-CATEGORIES*", $list_alt211);
        deflexical("*PSP-BINDINGS-FROM-FRAME-INT-CACHING-STATE*", NIL);
        deflexical("*PSP-PREDICATIVE-FRAME?-CACHING-STATE*", NIL);
        deflexical("*PSP-KEYWORDS-FOR-FRAME-CACHING-STATE*", NIL);
        deflexical("*PSP-CONSTRAINED-FRAME?-CACHING-STATE*", NIL);
        deflexical("*PSP-DEVISABLE-VERB-FRAMES-CACHING-STATE*", NIL);
        deflexical("*TRANSITIVE-NP-COMP-FRAME?-CACHING-STATE*", NIL);
        deflexical("*INTRANSITIVE-FRAME?-CACHING-STATE*", NIL);
        deflexical("*PSP-FRAME-HAS-TYPE?-CACHING-STATE*", NIL);
        deflexical("*PSP-PASSIVIZED-DTR-NUM-CACHING-STATE*", NIL);
        defconstant("*PSP-PASSIVE-KEYS*", $list_alt292);
        deflexical("*TITLES-CONVENTIONALLY-QUOTED*", NIL);
        deflexical("*PSP-PREFERRED-DENOT-PREFERENCE-FACTOR*", ONE_INTEGER);
        return NIL;
    }

    public static SubLObject init_psp_chart_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*PSP-PARSE-WH-WORDS?*", NIL);
            defconstant("*DTP-PHRASE-STRUCTURE-PARSER-CHART*", PHRASE_STRUCTURE_PARSER_CHART);
            deflexical("*PSP-CHART-ID-COUNTER*", SubLTrampolineFile.maybeDefault($psp_chart_id_counter$, $psp_chart_id_counter$, ZERO_INTEGER));
            deflexical("*PSP-CHART-INDEX-EDGES?*", NIL);
            defconstant("*DTP-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE*", PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE);
            defconstant("*DTP-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE*", PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE);
            defconstant("*PSP-UNKNOWN-WEIGHT*", $UNKNOWN);
            defconstant("*PSP-EDGE-DEFAULT-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_85));
            defconstant("*PSP-EDGE-GREEDY-WEIGHT*", parsing_utilities.psp_weight_from_float($float$1_0));
            defconstant("*PSP-EDGE-DISPREFERRED-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_75));
            defconstant("*PSP-GAP-EDGE-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_9));
            deflexical("*PSP-NP-GAP-SEMX-CACHING-STATE*", NIL);
            defconstant("*PSP-TOKENIZATION-MODES*", $list205);
            defparameter("*PSP-USE-TERM-LEXICON?*", NIL);
            deflexical("*FSCP-MULTI-PARSE-CATEGORIES*", $list221);
            deflexical("*PSP-BINDINGS-FROM-FRAME-INT-CACHING-STATE*", NIL);
            deflexical("*PSP-PREDICATIVE-FRAME?-CACHING-STATE*", NIL);
            deflexical("*PSP-KEYWORDS-FOR-FRAME-CACHING-STATE*", NIL);
            deflexical("*PSP-CONSTRAINED-FRAME?-CACHING-STATE*", NIL);
            deflexical("*PSP-DEVISABLE-VERB-FRAMES-CACHING-STATE*", NIL);
            deflexical("*TRANSITIVE-NP-COMP-FRAME?-CACHING-STATE*", NIL);
            deflexical("*INTRANSITIVE-FRAME?-CACHING-STATE*", NIL);
            deflexical("*PSP-FRAME-HAS-TYPE?-CACHING-STATE*", NIL);
            deflexical("*PSP-PASSIVIZED-DTR-NUM-CACHING-STATE*", NIL);
            defconstant("*PSP-PASSIVE-KEYS*", $list300);
            deflexical("*TITLES-CONVENTIONALLY-QUOTED*", NIL);
            deflexical("*PSP-PREFERRED-DENOT-PREFERENCE-FACTOR*", ONE_INTEGER);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*PSP-CHART-ID-COUNTER*", NIL != boundp($psp_chart_id_counter$) ? ((SubLObject) ($psp_chart_id_counter$.getGlobalValue())) : ZERO_INTEGER);
            defconstant("*PSP-TOKENIZATION-MODES*", $list_alt194);
            deflexical("*FSCP-MULTI-PARSE-CATEGORIES*", $list_alt211);
            defconstant("*PSP-PASSIVE-KEYS*", $list_alt292);
        }
        return NIL;
    }

    public static SubLObject init_psp_chart_file_Previous() {
        deflexical("*PSP-PARSE-WH-WORDS?*", NIL);
        defconstant("*DTP-PHRASE-STRUCTURE-PARSER-CHART*", PHRASE_STRUCTURE_PARSER_CHART);
        deflexical("*PSP-CHART-ID-COUNTER*", SubLTrampolineFile.maybeDefault($psp_chart_id_counter$, $psp_chart_id_counter$, ZERO_INTEGER));
        deflexical("*PSP-CHART-INDEX-EDGES?*", NIL);
        defconstant("*DTP-PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE*", PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE);
        defconstant("*DTP-PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE*", PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE);
        defconstant("*PSP-UNKNOWN-WEIGHT*", $UNKNOWN);
        defconstant("*PSP-EDGE-DEFAULT-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_85));
        defconstant("*PSP-EDGE-GREEDY-WEIGHT*", parsing_utilities.psp_weight_from_float($float$1_0));
        defconstant("*PSP-EDGE-DISPREFERRED-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_75));
        defconstant("*PSP-GAP-EDGE-WEIGHT*", parsing_utilities.psp_weight_from_float($float$0_9));
        deflexical("*PSP-NP-GAP-SEMX-CACHING-STATE*", NIL);
        defconstant("*PSP-TOKENIZATION-MODES*", $list205);
        defparameter("*PSP-USE-TERM-LEXICON?*", NIL);
        deflexical("*FSCP-MULTI-PARSE-CATEGORIES*", $list221);
        deflexical("*PSP-BINDINGS-FROM-FRAME-INT-CACHING-STATE*", NIL);
        deflexical("*PSP-PREDICATIVE-FRAME?-CACHING-STATE*", NIL);
        deflexical("*PSP-KEYWORDS-FOR-FRAME-CACHING-STATE*", NIL);
        deflexical("*PSP-CONSTRAINED-FRAME?-CACHING-STATE*", NIL);
        deflexical("*PSP-DEVISABLE-VERB-FRAMES-CACHING-STATE*", NIL);
        deflexical("*TRANSITIVE-NP-COMP-FRAME?-CACHING-STATE*", NIL);
        deflexical("*INTRANSITIVE-FRAME?-CACHING-STATE*", NIL);
        deflexical("*PSP-FRAME-HAS-TYPE?-CACHING-STATE*", NIL);
        deflexical("*PSP-PASSIVIZED-DTR-NUM-CACHING-STATE*", NIL);
        defconstant("*PSP-PASSIVE-KEYS*", $list300);
        deflexical("*TITLES-CONVENTIONALLY-QUOTED*", NIL);
        deflexical("*PSP-PREFERRED-DENOT-PREFERENCE-FACTOR*", ONE_INTEGER);
        return NIL;
    }

    public static final SubLObject setup_psp_chart_file_alt() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_chart$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_CHART_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(PSP_CHART_ID, _CSETF_PSP_CHART_ID);
        def_csetf(PSP_CHART_INPUT_STRING, _CSETF_PSP_CHART_INPUT_STRING);
        def_csetf(PSP_CHART_LENGTH, _CSETF_PSP_CHART_LENGTH);
        def_csetf(PSP_CHART_SPANS_TO_EDGES, _CSETF_PSP_CHART_SPANS_TO_EDGES);
        def_csetf(PSP_CHART_GAP_TYPE, _CSETF_PSP_CHART_GAP_TYPE);
        def_csetf(PSP_CHART_MEMOIZATION_STATE, _CSETF_PSP_CHART_MEMOIZATION_STATE);
        def_csetf(PSP_CHART_EDGE_ID_INDEX, _CSETF_PSP_CHART_EDGE_ID_INDEX);
        def_csetf(PSP_CHART_EDGES_TO_NODES, _CSETF_PSP_CHART_EDGES_TO_NODES);
        def_csetf(PSP_CHART_MAX_EDGES_PER_SPAN, _CSETF_PSP_CHART_MAX_EDGES_PER_SPAN);
        def_csetf(PSP_CHART_MAX_EDGES, _CSETF_PSP_CHART_MAX_EDGES);
        def_csetf(PSP_CHART_MAXED_OUT_SPANS, _CSETF_PSP_CHART_MAXED_OUT_SPANS);
        def_csetf($sym30$PSP_CHART_MAXED_OUT_, $sym31$_CSETF_PSP_CHART_MAXED_OUT_);
        def_csetf(PSP_CHART_PARSED_LEXICAL_SPANS, _CSETF_PSP_CHART_PARSED_LEXICAL_SPANS);
        identity(PHRASE_STRUCTURE_PARSER_CHART);
        declare_defglobal($psp_chart_id_counter$);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(PSP_PHRASAL_EDGE_WEIGHT, _CSETF_PSP_PHRASAL_EDGE_WEIGHT);
        def_csetf(PSP_PHRASAL_EDGE_STRING, _CSETF_PSP_PHRASAL_EDGE_STRING);
        def_csetf(PSP_PHRASAL_EDGE_SPAN, _CSETF_PSP_PHRASAL_EDGE_SPAN);
        def_csetf(PSP_PHRASAL_EDGE_CATEGORY, _CSETF_PSP_PHRASAL_EDGE_CATEGORY);
        def_csetf(PSP_PHRASAL_EDGE_POS_PRED, _CSETF_PSP_PHRASAL_EDGE_POS_PRED);
        def_csetf(PSP_PHRASAL_EDGE_CYCLS, _CSETF_PSP_PHRASAL_EDGE_CYCLS);
        def_csetf(PSP_PHRASAL_EDGE_RULE, _CSETF_PSP_PHRASAL_EDGE_RULE);
        def_csetf(PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS, _CSETF_PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS);
        def_csetf(PSP_PHRASAL_EDGE_HEAD_DTR, _CSETF_PSP_PHRASAL_EDGE_HEAD_DTR);
        def_csetf(PSP_PHRASAL_EDGE_NON_HEAD_DTRS, _CSETF_PSP_PHRASAL_EDGE_NON_HEAD_DTRS);
        def_csetf(PSP_PHRASAL_EDGE_ID, _CSETF_PSP_PHRASAL_EDGE_ID);
        def_csetf(PSP_PHRASAL_EDGE_CHART, _CSETF_PSP_PHRASAL_EDGE_CHART);
        identity(PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE);
        register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function(SXHASH_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_METHOD));
        register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(PSP_LEXICAL_EDGE_WEIGHT, _CSETF_PSP_LEXICAL_EDGE_WEIGHT);
        def_csetf(PSP_LEXICAL_EDGE_STRING, _CSETF_PSP_LEXICAL_EDGE_STRING);
        def_csetf(PSP_LEXICAL_EDGE_SPAN, _CSETF_PSP_LEXICAL_EDGE_SPAN);
        def_csetf(PSP_LEXICAL_EDGE_CATEGORY, _CSETF_PSP_LEXICAL_EDGE_CATEGORY);
        def_csetf(PSP_LEXICAL_EDGE_POS_PRED, _CSETF_PSP_LEXICAL_EDGE_POS_PRED);
        def_csetf(PSP_LEXICAL_EDGE_CYCLS, _CSETF_PSP_LEXICAL_EDGE_CYCLS);
        def_csetf(PSP_LEXICAL_EDGE_WU, _CSETF_PSP_LEXICAL_EDGE_WU);
        def_csetf(PSP_LEXICAL_EDGE_FRAME, _CSETF_PSP_LEXICAL_EDGE_FRAME);
        def_csetf(PSP_LEXICAL_EDGE_ID, _CSETF_PSP_LEXICAL_EDGE_ID);
        def_csetf(PSP_LEXICAL_EDGE_CHART, _CSETF_PSP_LEXICAL_EDGE_CHART);
        identity(PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE);
        register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function(SXHASH_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_METHOD));
        register_macro_helper(PSP_DO_EDGES_FOR_SPAN, PSP_DO_EDGES);
        register_macro_helper(PSP_DO_LEXICAL_EDGES_FOR_SPAN, PSP_DO_EDGES);
        register_macro_helper($sym150$PSP_DO_EDGE_OK_, PSP_DO_EDGES_FOR_SPAN);
        memoization_state.note_globally_cached_function(PSP_NP_GAP_SEMX);
        register_kb_function(ADD_SUBCAT_FRAME_INFO);
        register_kb_function(REMOVE_SUBCAT_FRAME_INFO);
        memoization_state.note_globally_cached_function(PSP_BINDINGS_FROM_FRAME_INT);
        memoization_state.note_globally_cached_function($sym258$PSP_PREDICATIVE_FRAME_);
        memoization_state.note_globally_cached_function(PSP_KEYWORDS_FOR_FRAME);
        memoization_state.note_globally_cached_function($sym267$PSP_CONSTRAINED_FRAME_);
        memoization_state.note_globally_cached_function(PSP_DEVISABLE_VERB_FRAMES);
        memoization_state.note_globally_cached_function($sym274$TRANSITIVE_NP_COMP_FRAME_);
        memoization_state.note_globally_cached_function($sym277$INTRANSITIVE_FRAME_);
        memoization_state.note_globally_cached_function($sym281$PSP_FRAME_HAS_TYPE_);
        memoization_state.note_globally_cached_function(PSP_PASSIVIZED_DTR_NUM);
        memoization_state.note_memoized_function($sym309$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_);
        memoization_state.note_memoized_function(PSP_FIND_FRAMES);
        define_test_case_table_int(TEST_SENTENCE_SET_WORDS_FROM_STRING_LIST, list(new SubLObject[]{ $TEST, WORD_OFFSETS_EQUAL, $OWNER, $$$daves, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list_alt331);
        return NIL;
    }

    public static SubLObject setup_psp_chart_file() {
        if (SubLFiles.USE_V1) {
            register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_chart$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_CHART_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list8);
            def_csetf(PSP_CHART_ID, _CSETF_PSP_CHART_ID);
            def_csetf(PSP_CHART_INPUT_STRING, _CSETF_PSP_CHART_INPUT_STRING);
            def_csetf(PSP_CHART_LENGTH, _CSETF_PSP_CHART_LENGTH);
            def_csetf(PSP_CHART_SPANS_TO_EDGES, _CSETF_PSP_CHART_SPANS_TO_EDGES);
            def_csetf(PSP_CHART_GAP_TYPE, _CSETF_PSP_CHART_GAP_TYPE);
            def_csetf(PSP_CHART_MEMOIZATION_STATE, _CSETF_PSP_CHART_MEMOIZATION_STATE);
            def_csetf(PSP_CHART_EDGE_ID_INDEX, _CSETF_PSP_CHART_EDGE_ID_INDEX);
            def_csetf(PSP_CHART_EDGES_TO_NODES, _CSETF_PSP_CHART_EDGES_TO_NODES);
            def_csetf(PSP_CHART_MAX_EDGES_PER_SPAN, _CSETF_PSP_CHART_MAX_EDGES_PER_SPAN);
            def_csetf(PSP_CHART_MAX_EDGES, _CSETF_PSP_CHART_MAX_EDGES);
            def_csetf(PSP_CHART_MAXED_OUT_SPANS, _CSETF_PSP_CHART_MAXED_OUT_SPANS);
            def_csetf($sym31$PSP_CHART_MAXED_OUT_, $sym32$_CSETF_PSP_CHART_MAXED_OUT_);
            def_csetf(PSP_CHART_PARSED_LEXICAL_SPANS, _CSETF_PSP_CHART_PARSED_LEXICAL_SPANS);
            identity(PHRASE_STRUCTURE_PARSER_CHART);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_chart$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_CHART_METHOD));
            declare_defglobal($psp_chart_id_counter$);
            register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list69);
            def_csetf(PSP_PHRASAL_EDGE_WEIGHT, _CSETF_PSP_PHRASAL_EDGE_WEIGHT);
            def_csetf(PSP_PHRASAL_EDGE_STRING, _CSETF_PSP_PHRASAL_EDGE_STRING);
            def_csetf(PSP_PHRASAL_EDGE_SPAN, _CSETF_PSP_PHRASAL_EDGE_SPAN);
            def_csetf(PSP_PHRASAL_EDGE_CATEGORY, _CSETF_PSP_PHRASAL_EDGE_CATEGORY);
            def_csetf(PSP_PHRASAL_EDGE_POS_PRED, _CSETF_PSP_PHRASAL_EDGE_POS_PRED);
            def_csetf(PSP_PHRASAL_EDGE_CYCLS, _CSETF_PSP_PHRASAL_EDGE_CYCLS);
            def_csetf(PSP_PHRASAL_EDGE_RULE, _CSETF_PSP_PHRASAL_EDGE_RULE);
            def_csetf(PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS, _CSETF_PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS);
            def_csetf(PSP_PHRASAL_EDGE_HEAD_DTR, _CSETF_PSP_PHRASAL_EDGE_HEAD_DTR);
            def_csetf(PSP_PHRASAL_EDGE_NON_HEAD_DTRS, _CSETF_PSP_PHRASAL_EDGE_NON_HEAD_DTRS);
            def_csetf(PSP_PHRASAL_EDGE_ID, _CSETF_PSP_PHRASAL_EDGE_ID);
            def_csetf(PSP_PHRASAL_EDGE_CHART, _CSETF_PSP_PHRASAL_EDGE_CHART);
            identity(PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function($sym106$VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_METHO));
            register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function(SXHASH_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_METHOD));
            register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list117);
            def_csetf(PSP_LEXICAL_EDGE_WEIGHT, _CSETF_PSP_LEXICAL_EDGE_WEIGHT);
            def_csetf(PSP_LEXICAL_EDGE_STRING, _CSETF_PSP_LEXICAL_EDGE_STRING);
            def_csetf(PSP_LEXICAL_EDGE_SPAN, _CSETF_PSP_LEXICAL_EDGE_SPAN);
            def_csetf(PSP_LEXICAL_EDGE_CATEGORY, _CSETF_PSP_LEXICAL_EDGE_CATEGORY);
            def_csetf(PSP_LEXICAL_EDGE_POS_PRED, _CSETF_PSP_LEXICAL_EDGE_POS_PRED);
            def_csetf(PSP_LEXICAL_EDGE_CYCLS, _CSETF_PSP_LEXICAL_EDGE_CYCLS);
            def_csetf(PSP_LEXICAL_EDGE_WU, _CSETF_PSP_LEXICAL_EDGE_WU);
            def_csetf(PSP_LEXICAL_EDGE_FRAME, _CSETF_PSP_LEXICAL_EDGE_FRAME);
            def_csetf(PSP_LEXICAL_EDGE_ID, _CSETF_PSP_LEXICAL_EDGE_ID);
            def_csetf(PSP_LEXICAL_EDGE_CHART, _CSETF_PSP_LEXICAL_EDGE_CHART);
            identity(PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function($sym141$VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_METHO));
            register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function(SXHASH_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_METHOD));
            register_macro_helper(PSP_DO_EDGES_FOR_SPAN, PSP_DO_EDGES);
            register_macro_helper(PSP_DO_LEXICAL_EDGES_FOR_SPAN, PSP_DO_EDGES);
            register_macro_helper($sym162$PSP_DO_EDGE_OK_, PSP_DO_EDGES_FOR_SPAN);
            memoization_state.note_globally_cached_function(PSP_NP_GAP_SEMX);
            register_kb_function(ADD_SUBCAT_FRAME_INFO);
            register_kb_function(REMOVE_SUBCAT_FRAME_INFO);
            memoization_state.note_globally_cached_function(PSP_BINDINGS_FROM_FRAME_INT);
            memoization_state.note_globally_cached_function($sym266$PSP_PREDICATIVE_FRAME_);
            memoization_state.note_globally_cached_function(PSP_KEYWORDS_FOR_FRAME);
            memoization_state.note_globally_cached_function($sym275$PSP_CONSTRAINED_FRAME_);
            memoization_state.note_globally_cached_function(PSP_DEVISABLE_VERB_FRAMES);
            memoization_state.note_globally_cached_function($sym282$TRANSITIVE_NP_COMP_FRAME_);
            memoization_state.note_globally_cached_function($sym285$INTRANSITIVE_FRAME_);
            memoization_state.note_globally_cached_function($sym289$PSP_FRAME_HAS_TYPE_);
            memoization_state.note_globally_cached_function(PSP_PASSIVIZED_DTR_NUM);
            memoization_state.note_memoized_function($sym317$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_);
            memoization_state.note_memoized_function(PSP_FIND_FRAMES);
            define_test_case_table_int(TEST_SENTENCE_SET_WORDS_FROM_STRING_LIST, list(new SubLObject[]{ $TEST, WORD_OFFSETS_EQUAL, $OWNER, NIL, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list338);
        }
        if (SubLFiles.USE_V2) {
            def_csetf($sym30$PSP_CHART_MAXED_OUT_, $sym31$_CSETF_PSP_CHART_MAXED_OUT_);
            register_macro_helper($sym150$PSP_DO_EDGE_OK_, PSP_DO_EDGES_FOR_SPAN);
            memoization_state.note_globally_cached_function($sym258$PSP_PREDICATIVE_FRAME_);
            memoization_state.note_globally_cached_function($sym267$PSP_CONSTRAINED_FRAME_);
            memoization_state.note_globally_cached_function($sym274$TRANSITIVE_NP_COMP_FRAME_);
            memoization_state.note_globally_cached_function($sym277$INTRANSITIVE_FRAME_);
            memoization_state.note_globally_cached_function($sym281$PSP_FRAME_HAS_TYPE_);
            memoization_state.note_memoized_function($sym309$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_);
            define_test_case_table_int(TEST_SENTENCE_SET_WORDS_FROM_STRING_LIST, list(new SubLObject[]{ $TEST, WORD_OFFSETS_EQUAL, $OWNER, $$$daves, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list_alt331);
        }
        return NIL;
    }

    public static SubLObject setup_psp_chart_file_Previous() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_chart$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_CHART_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list8);
        def_csetf(PSP_CHART_ID, _CSETF_PSP_CHART_ID);
        def_csetf(PSP_CHART_INPUT_STRING, _CSETF_PSP_CHART_INPUT_STRING);
        def_csetf(PSP_CHART_LENGTH, _CSETF_PSP_CHART_LENGTH);
        def_csetf(PSP_CHART_SPANS_TO_EDGES, _CSETF_PSP_CHART_SPANS_TO_EDGES);
        def_csetf(PSP_CHART_GAP_TYPE, _CSETF_PSP_CHART_GAP_TYPE);
        def_csetf(PSP_CHART_MEMOIZATION_STATE, _CSETF_PSP_CHART_MEMOIZATION_STATE);
        def_csetf(PSP_CHART_EDGE_ID_INDEX, _CSETF_PSP_CHART_EDGE_ID_INDEX);
        def_csetf(PSP_CHART_EDGES_TO_NODES, _CSETF_PSP_CHART_EDGES_TO_NODES);
        def_csetf(PSP_CHART_MAX_EDGES_PER_SPAN, _CSETF_PSP_CHART_MAX_EDGES_PER_SPAN);
        def_csetf(PSP_CHART_MAX_EDGES, _CSETF_PSP_CHART_MAX_EDGES);
        def_csetf(PSP_CHART_MAXED_OUT_SPANS, _CSETF_PSP_CHART_MAXED_OUT_SPANS);
        def_csetf($sym31$PSP_CHART_MAXED_OUT_, $sym32$_CSETF_PSP_CHART_MAXED_OUT_);
        def_csetf(PSP_CHART_PARSED_LEXICAL_SPANS, _CSETF_PSP_CHART_PARSED_LEXICAL_SPANS);
        identity(PHRASE_STRUCTURE_PARSER_CHART);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_chart$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_CHART_METHOD));
        declare_defglobal($psp_chart_id_counter$);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list69);
        def_csetf(PSP_PHRASAL_EDGE_WEIGHT, _CSETF_PSP_PHRASAL_EDGE_WEIGHT);
        def_csetf(PSP_PHRASAL_EDGE_STRING, _CSETF_PSP_PHRASAL_EDGE_STRING);
        def_csetf(PSP_PHRASAL_EDGE_SPAN, _CSETF_PSP_PHRASAL_EDGE_SPAN);
        def_csetf(PSP_PHRASAL_EDGE_CATEGORY, _CSETF_PSP_PHRASAL_EDGE_CATEGORY);
        def_csetf(PSP_PHRASAL_EDGE_POS_PRED, _CSETF_PSP_PHRASAL_EDGE_POS_PRED);
        def_csetf(PSP_PHRASAL_EDGE_CYCLS, _CSETF_PSP_PHRASAL_EDGE_CYCLS);
        def_csetf(PSP_PHRASAL_EDGE_RULE, _CSETF_PSP_PHRASAL_EDGE_RULE);
        def_csetf(PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS, _CSETF_PSP_PHRASAL_EDGE_NEW_DTR_CONSTRAINTS);
        def_csetf(PSP_PHRASAL_EDGE_HEAD_DTR, _CSETF_PSP_PHRASAL_EDGE_HEAD_DTR);
        def_csetf(PSP_PHRASAL_EDGE_NON_HEAD_DTRS, _CSETF_PSP_PHRASAL_EDGE_NON_HEAD_DTRS);
        def_csetf(PSP_PHRASAL_EDGE_ID, _CSETF_PSP_PHRASAL_EDGE_ID);
        def_csetf(PSP_PHRASAL_EDGE_CHART, _CSETF_PSP_PHRASAL_EDGE_CHART);
        identity(PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function($sym106$VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_METHO));
        register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_phrasal_edge$.getGlobalValue(), symbol_function(SXHASH_PHRASE_STRUCTURE_PARSER_PHRASAL_EDGE_METHOD));
        register_method($print_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function(PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list117);
        def_csetf(PSP_LEXICAL_EDGE_WEIGHT, _CSETF_PSP_LEXICAL_EDGE_WEIGHT);
        def_csetf(PSP_LEXICAL_EDGE_STRING, _CSETF_PSP_LEXICAL_EDGE_STRING);
        def_csetf(PSP_LEXICAL_EDGE_SPAN, _CSETF_PSP_LEXICAL_EDGE_SPAN);
        def_csetf(PSP_LEXICAL_EDGE_CATEGORY, _CSETF_PSP_LEXICAL_EDGE_CATEGORY);
        def_csetf(PSP_LEXICAL_EDGE_POS_PRED, _CSETF_PSP_LEXICAL_EDGE_POS_PRED);
        def_csetf(PSP_LEXICAL_EDGE_CYCLS, _CSETF_PSP_LEXICAL_EDGE_CYCLS);
        def_csetf(PSP_LEXICAL_EDGE_WU, _CSETF_PSP_LEXICAL_EDGE_WU);
        def_csetf(PSP_LEXICAL_EDGE_FRAME, _CSETF_PSP_LEXICAL_EDGE_FRAME);
        def_csetf(PSP_LEXICAL_EDGE_ID, _CSETF_PSP_LEXICAL_EDGE_ID);
        def_csetf(PSP_LEXICAL_EDGE_CHART, _CSETF_PSP_LEXICAL_EDGE_CHART);
        identity(PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function($sym141$VISIT_DEFSTRUCT_OBJECT_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_METHO));
        register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_phrase_structure_parser_lexical_edge$.getGlobalValue(), symbol_function(SXHASH_PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_METHOD));
        register_macro_helper(PSP_DO_EDGES_FOR_SPAN, PSP_DO_EDGES);
        register_macro_helper(PSP_DO_LEXICAL_EDGES_FOR_SPAN, PSP_DO_EDGES);
        register_macro_helper($sym162$PSP_DO_EDGE_OK_, PSP_DO_EDGES_FOR_SPAN);
        memoization_state.note_globally_cached_function(PSP_NP_GAP_SEMX);
        register_kb_function(ADD_SUBCAT_FRAME_INFO);
        register_kb_function(REMOVE_SUBCAT_FRAME_INFO);
        memoization_state.note_globally_cached_function(PSP_BINDINGS_FROM_FRAME_INT);
        memoization_state.note_globally_cached_function($sym266$PSP_PREDICATIVE_FRAME_);
        memoization_state.note_globally_cached_function(PSP_KEYWORDS_FOR_FRAME);
        memoization_state.note_globally_cached_function($sym275$PSP_CONSTRAINED_FRAME_);
        memoization_state.note_globally_cached_function(PSP_DEVISABLE_VERB_FRAMES);
        memoization_state.note_globally_cached_function($sym282$TRANSITIVE_NP_COMP_FRAME_);
        memoization_state.note_globally_cached_function($sym285$INTRANSITIVE_FRAME_);
        memoization_state.note_globally_cached_function($sym289$PSP_FRAME_HAS_TYPE_);
        memoization_state.note_globally_cached_function(PSP_PASSIVIZED_DTR_NUM);
        memoization_state.note_memoized_function($sym317$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_);
        memoization_state.note_memoized_function(PSP_FIND_FRAMES);
        define_test_case_table_int(TEST_SENTENCE_SET_WORDS_FROM_STRING_LIST, list(new SubLObject[]{ $TEST, WORD_OFFSETS_EQUAL, $OWNER, NIL, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list338);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_psp_chart_file();
    }

    @Override
    public void initializeVariables() {
        init_psp_chart_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_psp_chart_file();
    }

    static {
    }

    public static final class $phrase_structure_parser_chart_p$UnaryFunction extends UnaryFunction {
        public $phrase_structure_parser_chart_p$UnaryFunction() {
            super(extractFunctionNamed("PHRASE-STRUCTURE-PARSER-CHART-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return phrase_structure_parser_chart_p(arg1);
        }
    }

    static private final SubLList $list_alt2 = list(new SubLObject[]{ makeSymbol("ID"), makeSymbol("INPUT-STRING"), makeSymbol("LENGTH"), makeSymbol("SPANS-TO-EDGES"), makeSymbol("GAP-TYPE"), makeSymbol("MEMOIZATION-STATE"), makeSymbol("EDGE-ID-INDEX"), makeSymbol("EDGES-TO-NODES"), makeSymbol("MAX-EDGES-PER-SPAN"), makeSymbol("MAX-EDGES"), makeSymbol("MAXED-OUT-SPANS"), makeSymbol("MAXED-OUT?"), makeSymbol("PARSED-LEXICAL-SPANS") });

    static private final SubLList $list_alt3 = list(new SubLObject[]{ makeKeyword("ID"), makeKeyword("INPUT-STRING"), makeKeyword("LENGTH"), makeKeyword("SPANS-TO-EDGES"), makeKeyword("GAP-TYPE"), makeKeyword("MEMOIZATION-STATE"), makeKeyword("EDGE-ID-INDEX"), makeKeyword("EDGES-TO-NODES"), makeKeyword("MAX-EDGES-PER-SPAN"), makeKeyword("MAX-EDGES"), makeKeyword("MAXED-OUT-SPANS"), makeKeyword("MAXED-OUT?"), makeKeyword("PARSED-LEXICAL-SPANS") });

    static private final SubLList $list_alt4 = list(new SubLObject[]{ makeSymbol("PSP-CHART-ID"), makeSymbol("PSP-CHART-INPUT-STRING"), makeSymbol("PSP-CHART-LENGTH"), makeSymbol("PSP-CHART-SPANS-TO-EDGES"), makeSymbol("PSP-CHART-GAP-TYPE"), makeSymbol("PSP-CHART-MEMOIZATION-STATE"), makeSymbol("PSP-CHART-EDGE-ID-INDEX"), makeSymbol("PSP-CHART-EDGES-TO-NODES"), makeSymbol("PSP-CHART-MAX-EDGES-PER-SPAN"), makeSymbol("PSP-CHART-MAX-EDGES"), makeSymbol("PSP-CHART-MAXED-OUT-SPANS"), makeSymbol("PSP-CHART-MAXED-OUT?"), makeSymbol("PSP-CHART-PARSED-LEXICAL-SPANS") });

    static private final SubLList $list_alt5 = list(new SubLObject[]{ makeSymbol("_CSETF-PSP-CHART-ID"), makeSymbol("_CSETF-PSP-CHART-INPUT-STRING"), makeSymbol("_CSETF-PSP-CHART-LENGTH"), makeSymbol("_CSETF-PSP-CHART-SPANS-TO-EDGES"), makeSymbol("_CSETF-PSP-CHART-GAP-TYPE"), makeSymbol("_CSETF-PSP-CHART-MEMOIZATION-STATE"), makeSymbol("_CSETF-PSP-CHART-EDGE-ID-INDEX"), makeSymbol("_CSETF-PSP-CHART-EDGES-TO-NODES"), makeSymbol("_CSETF-PSP-CHART-MAX-EDGES-PER-SPAN"), makeSymbol("_CSETF-PSP-CHART-MAX-EDGES"), makeSymbol("_CSETF-PSP-CHART-MAXED-OUT-SPANS"), makeSymbol("_CSETF-PSP-CHART-MAXED-OUT?"), makeSymbol("_CSETF-PSP-CHART-PARSED-LEXICAL-SPANS") });

    public static final class $phrase_structure_parser_phrasal_edge_p$UnaryFunction extends UnaryFunction {
        public $phrase_structure_parser_phrasal_edge_p$UnaryFunction() {
            super(extractFunctionNamed("PHRASE-STRUCTURE-PARSER-PHRASAL-EDGE-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return phrase_structure_parser_phrasal_edge_p(arg1);
        }
    }

    static private final SubLSymbol $sym30$PSP_CHART_MAXED_OUT_ = makeSymbol("PSP-CHART-MAXED-OUT?");

    static private final SubLSymbol $sym31$_CSETF_PSP_CHART_MAXED_OUT_ = makeSymbol("_CSETF-PSP-CHART-MAXED-OUT?");

    public static final class $phrase_structure_parser_lexical_edge_native extends SubLStructNative {
        public SubLObject $weight;

        public SubLObject $string;

        public SubLObject $span;

        public SubLObject $category;

        public SubLObject $pos_pred;

        public SubLObject $cycls;

        public SubLObject $wu;

        public SubLObject $frame;

        public SubLObject $id;

        public SubLObject $chart;

        private static final SubLStructDeclNative structDecl;

        public $phrase_structure_parser_lexical_edge_native() {
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$weight = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$string = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$span = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$category = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$pos_pred = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$cycls = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$wu = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$frame = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$id = Lisp.NIL;
            psp_chart.$phrase_structure_parser_lexical_edge_native.this.$chart = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$weight;
        }

        @Override
        public SubLObject getField3() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$string;
        }

        @Override
        public SubLObject getField4() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$span;
        }

        @Override
        public SubLObject getField5() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$category;
        }

        @Override
        public SubLObject getField6() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$pos_pred;
        }

        @Override
        public SubLObject getField7() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$cycls;
        }

        @Override
        public SubLObject getField8() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$wu;
        }

        @Override
        public SubLObject getField9() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$frame;
        }

        @Override
        public SubLObject getField10() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$id;
        }

        @Override
        public SubLObject getField11() {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$chart;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$weight = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$string = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$span = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$category = value;
        }

        @Override
        public SubLObject setField6(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$pos_pred = value;
        }

        @Override
        public SubLObject setField7(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$cycls = value;
        }

        @Override
        public SubLObject setField8(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$wu = value;
        }

        @Override
        public SubLObject setField9(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$frame = value;
        }

        @Override
        public SubLObject setField10(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$id = value;
        }

        @Override
        public SubLObject setField11(final SubLObject value) {
            return psp_chart.$phrase_structure_parser_lexical_edge_native.this.$chart = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.psp_chart.$phrase_structure_parser_lexical_edge_native.class, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE, PHRASE_STRUCTURE_PARSER_LEXICAL_EDGE_P, $list111, $list112, new String[]{ "$weight", "$string", "$span", "$category", "$pos_pred", "$cycls", "$wu", "$frame", "$id", "$chart" }, $list113, $list114, PPRINT_PSP_LEXICAL_EDGE);
        }
    }

    public static final SubLSymbol $kw45$MAXED_OUT_ = makeKeyword("MAXED-OUT?");

    static private final SubLString $str_alt47$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLString $str_alt49$__PSP_CHART__S__S_ = makeString("#<PSP-CHART ~S ~S>");

    static private final SubLString $str_alt53$Not_indexing_PSP_edges__Can_t_fin = makeString("Not indexing PSP edges. Can't find edges by IDs.");

    static private final SubLString $str_alt54$_S_is_not_a_PSP_chart__Can_t_find = makeString("~S is not a PSP chart. Can't find edges by IDs.");

    static private final SubLList $list_alt57 = list(new SubLObject[]{ makeSymbol("WEIGHT"), makeSymbol("STRING"), makeSymbol("SPAN"), makeSymbol("CATEGORY"), makeSymbol("POS-PRED"), makeSymbol("CYCLS"), makeSymbol("RULE"), makeSymbol("NEW-DTR-CONSTRAINTS"), makeSymbol("HEAD-DTR"), makeSymbol("NON-HEAD-DTRS"), makeSymbol("ID"), makeSymbol("CHART") });

    static private final SubLList $list_alt58 = list(new SubLObject[]{ makeKeyword("WEIGHT"), makeKeyword("STRING"), $SPAN, makeKeyword("CATEGORY"), makeKeyword("POS-PRED"), makeKeyword("CYCLS"), $RULE, makeKeyword("NEW-DTR-CONSTRAINTS"), makeKeyword("HEAD-DTR"), makeKeyword("NON-HEAD-DTRS"), makeKeyword("ID"), makeKeyword("CHART") });

    static private final SubLList $list_alt59 = list(new SubLObject[]{ makeSymbol("PSP-PHRASAL-EDGE-WEIGHT"), makeSymbol("PSP-PHRASAL-EDGE-STRING"), makeSymbol("PSP-PHRASAL-EDGE-SPAN"), makeSymbol("PSP-PHRASAL-EDGE-CATEGORY"), makeSymbol("PSP-PHRASAL-EDGE-POS-PRED"), makeSymbol("PSP-PHRASAL-EDGE-CYCLS"), makeSymbol("PSP-PHRASAL-EDGE-RULE"), makeSymbol("PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS"), makeSymbol("PSP-PHRASAL-EDGE-HEAD-DTR"), makeSymbol("PSP-PHRASAL-EDGE-NON-HEAD-DTRS"), makeSymbol("PSP-PHRASAL-EDGE-ID"), makeSymbol("PSP-PHRASAL-EDGE-CHART") });

    static private final SubLList $list_alt60 = list(new SubLObject[]{ makeSymbol("_CSETF-PSP-PHRASAL-EDGE-WEIGHT"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-STRING"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-SPAN"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CATEGORY"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-POS-PRED"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CYCLS"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-RULE"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-NEW-DTR-CONSTRAINTS"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-HEAD-DTR"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-NON-HEAD-DTRS"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-ID"), makeSymbol("_CSETF-PSP-PHRASAL-EDGE-CHART") });

    public static final class $phrase_structure_parser_lexical_edge_p$UnaryFunction extends UnaryFunction {
        public $phrase_structure_parser_lexical_edge_p$UnaryFunction() {
            super(extractFunctionNamed("PHRASE-STRUCTURE-PARSER-LEXICAL-EDGE-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return phrase_structure_parser_lexical_edge_p(arg1);
        }
    }

    static private final SubLString $str_alt99$__PSP_PHRASAL_EDGE__S__S_ = makeString("#<PSP-PHRASAL-EDGE ~S ~S>");

    static private final SubLList $list_alt102 = list(new SubLObject[]{ makeSymbol("WEIGHT"), makeSymbol("STRING"), makeSymbol("SPAN"), makeSymbol("CATEGORY"), makeSymbol("POS-PRED"), makeSymbol("CYCLS"), makeSymbol("WU"), makeSymbol("FRAME"), makeSymbol("ID"), makeSymbol("CHART") });

    static private final SubLList $list_alt103 = list(new SubLObject[]{ makeKeyword("WEIGHT"), makeKeyword("STRING"), $SPAN, makeKeyword("CATEGORY"), makeKeyword("POS-PRED"), makeKeyword("CYCLS"), makeKeyword("WU"), makeKeyword("FRAME"), makeKeyword("ID"), makeKeyword("CHART") });

    static private final SubLList $list_alt104 = list(new SubLObject[]{ makeSymbol("PSP-LEXICAL-EDGE-WEIGHT"), makeSymbol("PSP-LEXICAL-EDGE-STRING"), makeSymbol("PSP-LEXICAL-EDGE-SPAN"), makeSymbol("PSP-LEXICAL-EDGE-CATEGORY"), makeSymbol("PSP-LEXICAL-EDGE-POS-PRED"), makeSymbol("PSP-LEXICAL-EDGE-CYCLS"), makeSymbol("PSP-LEXICAL-EDGE-WU"), makeSymbol("PSP-LEXICAL-EDGE-FRAME"), makeSymbol("PSP-LEXICAL-EDGE-ID"), makeSymbol("PSP-LEXICAL-EDGE-CHART") });

    static private final SubLList $list_alt105 = list(new SubLObject[]{ makeSymbol("_CSETF-PSP-LEXICAL-EDGE-WEIGHT"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-STRING"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-SPAN"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CATEGORY"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-POS-PRED"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CYCLS"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-WU"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-FRAME"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-ID"), makeSymbol("_CSETF-PSP-LEXICAL-EDGE-CHART") });

    static private final SubLString $str_alt131$__PSP_LEXICAL_EDGE__S__S__S_W__S_ = makeString("#<PSP-LEXICAL-EDGE ~S ~S ~S W=~S>");

    static private final SubLString $str_alt132$___S___S______S__ = makeString("~&~S (~S):~% ~S~%");

    static private final SubLString $str_alt133$_S_is_not_a_PSP_edge_ = makeString("~S is not a PSP edge.");

    static private final SubLString $str_alt134$Headless_edge___S = makeString("Headless edge: ~S");

    static private final SubLList $list_alt135 = list(list(makeSymbol("SPAN-VAR"), makeSymbol("&OPTIONAL"), makeSymbol("DONE"), makeSymbol("CHART")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym136$CHART_TO_USE = makeUninternedSymbol("CHART-TO-USE");

    static private final SubLList $list_alt139 = list(makeSymbol("*PSP-CHART*"));

    static private final SubLList $list_alt141 = list(makeSymbol("PHRASE-STRUCTURE-PARSER-CHART-P"));

    static private final SubLList $list_alt146 = list(list(makeSymbol("EDGE-VAR"), makeSymbol("CHART"), makeSymbol("CATEGORY"), makeSymbol("SPAN"), makeSymbol("COMPLETE-ONLY?"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym150$PSP_DO_EDGE_OK_ = makeSymbol("PSP-DO-EDGE-OK?");

    static private final SubLList $list_alt154 = list(list(makeSymbol("EDGE-VAR"), list(makeSymbol("&KEY"), list(makeSymbol("CHART"), NIL), list(makeSymbol("CATEGORY"), makeKeyword("ANY")), list(makeSymbol("SPAN"), makeKeyword("ANY")), list(makeSymbol("COMPLETE-ONLY?"), T), list(makeSymbol("TYPE"), makeKeyword("ANY")), list(makeSymbol("DONE"), NIL))), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt155 = list(makeKeyword("CHART"), makeKeyword("CATEGORY"), $SPAN, makeKeyword("COMPLETE-ONLY?"), $TYPE, $DONE);

    public static final SubLSymbol $kw157$COMPLETE_ONLY_ = makeKeyword("COMPLETE-ONLY?");

    static private final SubLSymbol $sym159$SPAN_TO_DO = makeUninternedSymbol("SPAN-TO-DO");

    static private final SubLSymbol $sym160$CHART_TO_USE = makeUninternedSymbol("CHART-TO-USE");

    static private final SubLList $list_alt162 = list(makeKeyword("ANY"));

    public static final SubLSymbol $kw178$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLList $list_alt185 = list(makeKeyword("NP"), $NONE);

    static private final SubLList $list_alt194 = list(makeKeyword("GREEDY"), makeKeyword("DILIGENT"));

    static private final SubLString $str_alt199$_ = makeString("'");

    static private final SubLString $str_alt200$___ = makeString(" ' ");

    static private final SubLString $str_alt201$Invalid_attempt_to_reuse_memoizat = makeString("Invalid attempt to reuse memoization state in multiple threads simultaneously.");

    static private final SubLString $str_alt209$Failed_to_get_bigger_than__S__Lon = makeString("Failed to get bigger than ~S. Longest edge:~% ~S (span: ~S)~%");

    static private final SubLList $list_alt211 = list(makeKeyword("QUANTITY"), makeKeyword("UNIT-OF-MEASURE"));

    static private final SubLSymbol $sym216$PSP_SCALAR_INTERVAL_ = makeSymbol("PSP-SCALAR-INTERVAL?");

    static private final SubLSymbol $sym217$IBQE_ = makeSymbol("IBQE?");

    static private final SubLSymbol $sym220$_ = makeSymbol("=");

    static private final SubLList $list_alt222 = cons(makeSymbol("INDEXICAL-STRING"), makeSymbol("DENOT"));

    static private final SubLList $list_alt233 = list(reader_make_constant_shell("PPCompFrameFn"), reader_make_constant_shell("TransitivePPFrameType"), reader_make_constant_shell("Of-TheWord"));

    static private final SubLString $str_alt237$the_ = makeString("the ");

    static private final SubLString $str_alt239$__Adding_new_edge_____S__Looking_ = makeString("~&Adding new edge:~% ~S~%Looking for dtrs:~% ~S~%");

    static private final SubLSymbol $sym241$PSP_LEXICAL_EDGE_ = makeSymbol("PSP-LEXICAL-EDGE?");

    static private final SubLList $list_alt242 = list(reader_make_constant_shell("PhraseFn"), reader_make_constant_shell("Determiner"));

    static private final SubLSymbol $sym243$PSP_INCOMPLETE_EDGE_ = makeSymbol("PSP-INCOMPLETE-EDGE?");

    static private final SubLString $str_alt244$__Too_many_edges_in_PSP_chart_for = makeString("~&Too many edges in PSP chart for ~S.~%Limit: ~S Current: ~S.~%");

    static private final SubLString $str_alt245$__Too_many_edges_for_span__S_in_P = makeString("~&Too many edges for span ~S in PSP chart for ~S.~%Limit: ~S Current: ~S.~%");

    static private final SubLSymbol $sym246$PSP_NP_EDGE_ = makeSymbol("PSP-NP-EDGE?");

    static private final SubLString $str_alt248$Initializing_Subcategorization_Fr = makeString("Initializing Subcategorization Frames...");

    static private final SubLList $list_alt254 = list(makeSymbol("?NUM"), makeSymbol("?KEY"));

    static private final SubLSymbol $sym258$PSP_PREDICATIVE_FRAME_ = makeSymbol("PSP-PREDICATIVE-FRAME?");

    static private final SubLSymbol $sym260$_PSP_PREDICATIVE_FRAME__CACHING_STATE_ = makeSymbol("*PSP-PREDICATIVE-FRAME?-CACHING-STATE*");

    static private final SubLSymbol $sym262$_KEYWORD = makeSymbol("?KEYWORD");

    static private final SubLList $list_alt264 = list(makeSymbol("?KEYWORD"));

    static private final SubLSymbol $sym267$PSP_CONSTRAINED_FRAME_ = makeSymbol("PSP-CONSTRAINED-FRAME?");

    static private final SubLSymbol $sym270$_PSP_CONSTRAINED_FRAME__CACHING_STATE_ = makeSymbol("*PSP-CONSTRAINED-FRAME?-CACHING-STATE*");

    static private final SubLSymbol $sym272$DEVISABLE_VERB_FRAME_ = makeSymbol("DEVISABLE-VERB-FRAME?");

    static private final SubLSymbol $sym274$TRANSITIVE_NP_COMP_FRAME_ = makeSymbol("TRANSITIVE-NP-COMP-FRAME?");

    static private final SubLSymbol $sym276$_TRANSITIVE_NP_COMP_FRAME__CACHING_STATE_ = makeSymbol("*TRANSITIVE-NP-COMP-FRAME?-CACHING-STATE*");

    static private final SubLSymbol $sym277$INTRANSITIVE_FRAME_ = makeSymbol("INTRANSITIVE-FRAME?");

    static private final SubLList $list_alt279 = list(ZERO_INTEGER);

    static private final SubLSymbol $sym280$_INTRANSITIVE_FRAME__CACHING_STATE_ = makeSymbol("*INTRANSITIVE-FRAME?-CACHING-STATE*");

    static private final SubLSymbol $sym281$PSP_FRAME_HAS_TYPE_ = makeSymbol("PSP-FRAME-HAS-TYPE?");

    static private final SubLSymbol $sym282$_PSP_FRAME_HAS_TYPE__CACHING_STATE_ = makeSymbol("*PSP-FRAME-HAS-TYPE?-CACHING-STATE*");

    static private final SubLSymbol $sym284$_N = makeSymbol("?N");

    static private final SubLList $list_alt287 = list(reader_make_constant_shell("FrameForVerbs"));

    static private final SubLList $list_alt288 = list(makeSymbol("?N"), list(reader_make_constant_shell("PhraseFn"), reader_make_constant_shell("Noun")));

    static private final SubLList $list_alt292 = list(makeKeyword("PASSIVE"), makeKeyword("PASSIVE-WITH-BY"));

    static private final SubLString $str_alt293$Don_t_know_how_to_passivize__S___ = makeString("Don't know how to passivize ~S.~%");

    static private final SubLList $list_alt294 = list(makeSymbol("TYPE"), makeSymbol("N"), makeSymbol("KEYWORD"));

    static private final SubLList $list_alt295 = list(makeKeyword("BY-SUBJECT"));

    static private final SubLSymbol $sym297$PSP_CYCL__ = makeSymbol("PSP-CYCL-=");

    static private final SubLSymbol $sym309$PSP_TERM_HAS_PREFERRED_LEXIFICATIONS_ = makeSymbol("PSP-TERM-HAS-PREFERRED-LEXIFICATIONS?");

    static private final SubLList $list_alt310 = list(makeSymbol("CYCL"), makeSymbol("POS"), makeSymbol("POS-PRED"), makeSymbol("WU"));

    static private final SubLSymbol $sym313$PSP_EDGE_SUBSUMES_ = makeSymbol("PSP-EDGE-SUBSUMES?");

    static private final SubLString $str_alt314$__Removing_edge_____S__is_subsume = makeString("~&Removing edge:~% ~S~%is subsumed by~% ~S~%~%");

    static private final SubLList $list_alt319 = list(NIL);

    static private final SubLString $$$daves = makeString("daves");

    static private final SubLList $list_alt331 = list(list(list(list(makeString("6"), makeString("degrees"), makeString("per"), makeString("second"), makeString("per"), makeString("second")), makeString("6 degrees per second per  second")), list(ZERO_INTEGER, TWO_INTEGER, TEN_INTEGER, FOURTEEN_INTEGER, makeInteger(21), makeInteger(26))), list(list(list(makeString("6"), makeString("degrees"), makeString("per"), makeString("second"), makeString("per"), makeString("second")), makeString("6 degrees per second per second")), list(ZERO_INTEGER, TWO_INTEGER, TEN_INTEGER, FOURTEEN_INTEGER, makeInteger(21), makeInteger(25))));
}

/**
 * Total time: 1641 ms synthetic
 */
