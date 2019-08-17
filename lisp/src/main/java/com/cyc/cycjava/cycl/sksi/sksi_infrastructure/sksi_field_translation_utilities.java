/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.sksi.sksi_infrastructure;


import static com.cyc.cycjava.cycl.el_utilities.copy_expression;
import static com.cyc.cycjava.cycl.el_utilities.el_set_items;
import static com.cyc.cycjava.cycl.el_utilities.el_set_p;
import static com.cyc.cycjava.cycl.el_utilities.make_binary_formula;
import static com.cyc.cycjava.cycl.el_utilities.make_el_set;
import static com.cyc.cycjava.cycl.el_utilities.unmake_binary_formula;
import static com.cyc.cycjava.cycl.utilities_macros.$is_noting_progressP$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_count$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_elapsed_seconds_for_notification$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_last_pacification_time$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_notification_count$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_pacifications_since_last_nl$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_start_time$;
import static com.cyc.cycjava.cycl.utilities_macros.$silent_progressP$;
import static com.cyc.cycjava.cycl.utilities_macros.$suppress_all_progress_faster_than_seconds$;
import static com.cyc.cycjava.cycl.utilities_macros.note_progress;
import static com.cyc.cycjava.cycl.utilities_macros.noting_progress_postamble;
import static com.cyc.cycjava.cycl.utilities_macros.noting_progress_preamble;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.equal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.position;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.get_universal_time;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.sublis;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.force_output;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.assertions_high;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.iteration;
import com.cyc.cycjava.cycl.kb_mapping_macros;
import com.cyc.cycjava.cycl.kb_utilities;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.memoization_state;
import com.cyc.cycjava.cycl.mt_relevance_macros;
import com.cyc.cycjava.cycl.virtual_indexing;
import com.cyc.cycjava.cycl.inference.ask_utilities;
import com.cyc.cycjava.cycl.sksi.data_warehousing.sksi_incremental_edit;
import com.cyc.cycjava.cycl.sksi.query_sks.sksi_query_utilities;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.UnaryFunction;
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
 * module:      SKSI-FIELD-TRANSLATION-UTILITIES
 * source file: /cyc/top/cycl/sksi/sksi-infrastructure/sksi-field-translation-utilities.lisp
 * created:     2019/07/03 17:37:53
 */
public final class sksi_field_translation_utilities extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new sksi_field_translation_utilities();

 public static final String myName = "com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities";


    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list0 = list(list(makeSymbol("TO-SCHEMA"), makeSymbol("TO-FIELD"), makeSymbol("FROM-SCHEMA"), makeSymbol("FIELD-TRANSLATION")), makeSymbol("FIELD-TRANSLATION-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol GAF_ARGS = makeSymbol("GAF-ARGS");

    private static final SubLSymbol PHYSICAL_FIELD_INDEXICAL_P = makeSymbol("PHYSICAL-FIELD-INDEXICAL-P");

    private static final SubLSymbol LOGICAL_FIELD_INDEXICAL_P = makeSymbol("LOGICAL-FIELD-INDEXICAL-P");

    static private final SubLList $list5 = list(list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD-DECODING")), makeSymbol("LOGICAL-FIELD-DECODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol DESTRUCTURE_FIELD_TRANSLATION_GAF = makeSymbol("DESTRUCTURE-FIELD-TRANSLATION-GAF");



    static private final SubLList $list10 = list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("TO-LOGICAL-FIELD-INDEXICAL"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("DECODING"));

    static private final SubLList $list11 = list(list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD-ENCODING")), makeSymbol("LOGICAL-FIELD-ENCODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));



    static private final SubLList $list13 = list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("TO-LOGICAL-FIELD-INDEXICAL"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("ENCODING"));

    static private final SubLList $list14 = list(list(makeSymbol("LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("FIELD-DECODING")), makeSymbol("FIELD-DECODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol RELEVANT_FIELD_DECODINGS = makeSymbol("RELEVANT-FIELD-DECODINGS");

    private static final SubLSymbol RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL = makeSymbol("RELEVANT-FIELD-DECODINGS-FOR-INDEXICAL");



    private static final SubLList $list18 = list(makeSymbol("LOGICAL-SCHEMA"), makeSymbol("TO-LOGICAL-FIELD-INDEXICAL"), makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("DECODING"));

    private static final SubLSymbol GET_FIELD_DECODING_FROM_GAF = makeSymbol("GET-FIELD-DECODING-FROM-GAF");

    private static final SubLList $list20 = list(makeSymbol("LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("DECODING"));

    static private final SubLList $list21 = list(list(makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("LOGICAL-SCHEMA"), makeSymbol("FIELD-ENCODING")), makeSymbol("FIELD-ENCODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol RELEVANT_FIELD_ENCODINGS = makeSymbol("RELEVANT-FIELD-ENCODINGS");

    private static final SubLSymbol GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD = makeSymbol("GET-FIELD-ENCODINGS-FOR-PHYSICAL-FIELD");

    private static final SubLSymbol GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL = makeSymbol("GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL");



    private static final SubLList $list26 = list(makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("TO-PHYSICAL-FIELD-INDEXICAL"), makeSymbol("LOGICAL-SCHEMA"), makeSymbol("ENCODING"));

    private static final SubLSymbol GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL = makeSymbol("GET-FIELD-ENCODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL");

    private static final SubLList $list29 = list(makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("LOGICAL-SCHEMA"), makeSymbol("ENCODING"));

    static private final SubLList $list30 = list(list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-DECODING")), makeSymbol("PHYSICAL-FIELD-DECODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));



    private static final SubLList $list32 = list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("TO-PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("DECODING"));

    static private final SubLList $list33 = list(list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-ENCODING")), makeSymbol("PHYSICAL-FIELD-ENCODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));



    private static final SubLList $list35 = list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("TO-PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("ENCODING"));

    private static final SubLSymbol LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P = makeSymbol("LOGICAL-FIELD-INDEXICAL-OR-REIFIED-MAPPING-EL-FORMULA-P");

    private static final SubLSymbol PHYSICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P = makeSymbol("PHYSICAL-FIELD-INDEXICAL-OR-REIFIED-MAPPING-EL-FORMULA-P");

    private static final SubLSymbol PHYSICAL_FIELD_INDEXICAL_EL_FORMULA_P = makeSymbol("PHYSICAL-FIELD-INDEXICAL-EL-FORMULA-P");

    private static final SubLSymbol $SKSI_MISSING_DECODINGS = makeKeyword("SKSI-MISSING-DECODINGS");

    private static final SubLSymbol $SKSI_MISSING_ENCODINGS = makeKeyword("SKSI-MISSING-ENCODINGS");

    private static final SubLString $str41$__SKSI__A_ = makeString("~&SKSI-~A ");

    private static final SubLString $str42$Decoding_loop___a_ = makeString("Decoding loop: ~a.");

    private static final SubLSymbol GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL = makeSymbol("GENERATE-ENCODING-COMPOSITIONS-FOR-PHYSICAL-FIELD-INDEXICAL");

    private static final SubLString $str44$Encoding_loop___a_ = makeString("Encoding loop: ~a.");

    private static final SubLSymbol GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL = makeSymbol("GENERATE-ENCODING-COMPOSITIONS-FROM-LOGICAL-FIELD-INDEXICAL");

    private static final SubLSymbol TRANSLATIONS_EQUIVALENT_MEMOIZED = makeSymbol("TRANSLATIONS-EQUIVALENT-MEMOIZED");

    private static final SubLSymbol LOGICAL_FIELD_INDEXICAL_EL_FORMULA_P = makeSymbol("LOGICAL-FIELD-INDEXICAL-EL-FORMULA-P");

    private static final SubLList $list50 = cons(makeSymbol("LF"), makeSymbol("VALUE"));



    private static final SubLString $str54$No_meaning_sentence_for__S = makeString("No meaning sentence for ~S");

    private static final SubLString $str57$failure_on_name_for__S = makeString("failure on name for ~S");

    private static final SubLString $str59$failure_on_value_for__S = makeString("failure on value for ~S");

    private static final SubLString $str60$No_physical_field_for__S = makeString("No physical field for ~S");

    private static final SubLSymbol INDEXICAL_FOR_LOGICAL_FIELD = makeSymbol("INDEXICAL-FOR-LOGICAL-FIELD");

    private static final SubLSymbol $sym62$_PRED = makeSymbol("?PRED");

    private static final SubLString $str63$More_than_one_meaning_sentence_pr = makeString("More than one meaning sentence pred found for ~S (from ~S)");

    private static final SubLString $str64$Meaning_sentence_not_found_for__S = makeString("Meaning sentence not found for ~S (from ~S)");

    // Definitions
    public static final SubLObject get_field_translation_from_gaf_alt(SubLObject field_translation_gaf) {
        return assertions_high.gaf_arg4(field_translation_gaf);
    }

    // Definitions
    public static SubLObject get_field_translation_from_gaf(final SubLObject field_translation_gaf) {
        return assertions_high.gaf_arg4(field_translation_gaf);
    }

    public static final SubLObject destructure_field_translation_gaf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt0);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject to_schema = NIL;
                    SubLObject to_field = NIL;
                    SubLObject from_schema = NIL;
                    SubLObject field_translation = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt0);
                    to_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt0);
                    to_field = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt0);
                    from_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt0);
                    field_translation = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject field_translation_gaf = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt0);
                            field_translation_gaf = current.first();
                            current = current.rest();
                            {
                                SubLObject body = current;
                                return listS(CDESTRUCTURING_BIND, list(to_schema, to_field, from_schema, field_translation), list(GAF_ARGS, field_translation_gaf), append(body, NIL));
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt0);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject destructure_field_translation_gaf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list0);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject to_schema = NIL;
        SubLObject to_field = NIL;
        SubLObject from_schema = NIL;
        SubLObject field_translation = NIL;
        destructuring_bind_must_consp(current, datum, $list0);
        to_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list0);
        to_field = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list0);
        from_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list0);
        field_translation = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject field_translation_gaf = NIL;
            destructuring_bind_must_consp(current, datum, $list0);
            field_translation_gaf = current.first();
            final SubLObject body;
            current = body = current.rest();
            return listS(CDESTRUCTURING_BIND, list(to_schema, to_field, from_schema, field_translation), list(GAF_ARGS, field_translation_gaf), append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list0);
        return NIL;
    }

    /**
     * Gathers the fields picked out by TEST from FIELD-TRANSLATION.
     */
    @LispMethod(comment = "Gathers the fields picked out by TEST from FIELD-TRANSLATION.")
    public static final SubLObject field_translation_fields_alt(SubLObject field_translation, SubLObject test) {
        return cycl_utilities.expression_gather(field_translation, test, T, symbol_function(EQ), symbol_function(IDENTITY), NIL);
    }

    /**
     * Gathers the fields picked out by TEST from FIELD-TRANSLATION.
     */
    @LispMethod(comment = "Gathers the fields picked out by TEST from FIELD-TRANSLATION.")
    public static SubLObject field_translation_fields(final SubLObject field_translation, final SubLObject test) {
        return cycl_utilities.expression_gather(field_translation, test, T, symbol_function(EQ), symbol_function(IDENTITY), NIL);
    }

    /**
     * Gathers the physical field indexicals from DECODING.
     */
    @LispMethod(comment = "Gathers the physical field indexicals from DECODING.")
    public static final SubLObject decoding_physical_field_indexicals_alt(SubLObject decoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(decoding, PHYSICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gathers the physical field indexicals from DECODING.
     */
    @LispMethod(comment = "Gathers the physical field indexicals from DECODING.")
    public static SubLObject decoding_physical_field_indexicals(final SubLObject decoding) {
        return field_translation_fields(decoding, PHYSICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gathers the physical field indexicals in the order in which
     * they appear in DECODING.
     */
    @LispMethod(comment = "Gathers the physical field indexicals in the order in which\r\nthey appear in DECODING.\nGathers the physical field indexicals in the order in which\nthey appear in DECODING.")
    public static final SubLObject decoding_physical_field_indexicals_ordered_alt(SubLObject decoding) {
        {
            SubLObject reconstructed_decoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_decoding(decoding, UNPROVIDED);
            return list_utilities.tree_gather_ordered(reconstructed_decoding, PHYSICAL_FIELD_INDEXICAL_P, symbol_function(IDENTITY), NIL);
        }
    }

    /**
     * Gathers the physical field indexicals in the order in which
     * they appear in DECODING.
     */
    @LispMethod(comment = "Gathers the physical field indexicals in the order in which\r\nthey appear in DECODING.\nGathers the physical field indexicals in the order in which\nthey appear in DECODING.")
    public static SubLObject decoding_physical_field_indexicals_ordered(final SubLObject decoding) {
        final SubLObject reconstructed_decoding = sksi_reconstruct_decoding(decoding, UNPROVIDED);
        return list_utilities.tree_gather_ordered(reconstructed_decoding, PHYSICAL_FIELD_INDEXICAL_P, symbol_function(IDENTITY), NIL);
    }

    /**
     * Gathers the logical fields from ENCODING.
     */
    @LispMethod(comment = "Gathers the logical fields from ENCODING.")
    public static final SubLObject encoding_logical_fields_alt(SubLObject encoding) {
        return sksi_kb_accessors.logical_fields_for_indexicals(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.encoding_logical_field_indexicals(encoding));
    }

    /**
     * Gathers the logical fields from ENCODING.
     */
    @LispMethod(comment = "Gathers the logical fields from ENCODING.")
    public static SubLObject encoding_logical_fields(final SubLObject encoding) {
        return sksi_kb_accessors.logical_fields_for_indexicals(encoding_logical_field_indexicals(encoding));
    }

    /**
     * Gathers the logical field indexicals from ENCODING.
     */
    @LispMethod(comment = "Gathers the logical field indexicals from ENCODING.")
    public static final SubLObject encoding_logical_field_indexicals_alt(SubLObject encoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(encoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gathers the logical field indexicals from ENCODING.
     */
    @LispMethod(comment = "Gathers the logical field indexicals from ENCODING.")
    public static SubLObject encoding_logical_field_indexicals(final SubLObject encoding) {
        return field_translation_fields(encoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gathers the logical fields in the order in which they
     * appear in ENCODING.
     */
    @LispMethod(comment = "Gathers the logical fields in the order in which they\r\nappear in ENCODING.\nGathers the logical fields in the order in which they\nappear in ENCODING.")
    public static final SubLObject encoding_logical_fields_ordered_alt(SubLObject encoding) {
        {
            SubLObject reconstructed_encoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_encoding(encoding, UNPROVIDED);
            SubLObject logical_field_indexicals = list_utilities.tree_gather_ordered(reconstructed_encoding, LOGICAL_FIELD_INDEXICAL_P, symbol_function(IDENTITY), NIL);
            return sksi_kb_accessors.logical_fields_for_indexicals(logical_field_indexicals);
        }
    }

    /**
     * Gathers the logical fields in the order in which they
     * appear in ENCODING.
     */
    @LispMethod(comment = "Gathers the logical fields in the order in which they\r\nappear in ENCODING.\nGathers the logical fields in the order in which they\nappear in ENCODING.")
    public static SubLObject encoding_logical_fields_ordered(final SubLObject encoding) {
        final SubLObject reconstructed_encoding = sksi_reconstruct_encoding(encoding, UNPROVIDED);
        final SubLObject logical_field_indexicals = list_utilities.tree_gather_ordered(reconstructed_encoding, LOGICAL_FIELD_INDEXICAL_P, symbol_function(IDENTITY), NIL);
        return sksi_kb_accessors.logical_fields_for_indexicals(logical_field_indexicals);
    }

    public static final SubLObject get_logical_field_decoding_from_gaf_alt(SubLObject logical_field_decoding_gaf) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_translation_from_gaf(logical_field_decoding_gaf);
    }

    public static SubLObject get_logical_field_decoding_from_gaf(final SubLObject logical_field_decoding_gaf) {
        return get_field_translation_from_gaf(logical_field_decoding_gaf);
    }

    public static final SubLObject destructure_logical_field_decoding_gaf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt5);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject to_logical_schema = NIL;
                    SubLObject logical_field = NIL;
                    SubLObject from_logical_schema = NIL;
                    SubLObject logical_field_decoding = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt5);
                    to_logical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt5);
                    logical_field = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt5);
                    from_logical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt5);
                    logical_field_decoding = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject logical_field_decoding_gaf = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt5);
                            logical_field_decoding_gaf = current.first();
                            current = current.rest();
                            {
                                SubLObject body = current;
                                return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_logical_schema, logical_field, from_logical_schema, logical_field_decoding), logical_field_decoding_gaf, append(body, NIL));
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt5);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject destructure_logical_field_decoding_gaf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list5);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject to_logical_schema = NIL;
        SubLObject logical_field = NIL;
        SubLObject from_logical_schema = NIL;
        SubLObject logical_field_decoding = NIL;
        destructuring_bind_must_consp(current, datum, $list5);
        to_logical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list5);
        logical_field = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list5);
        from_logical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list5);
        logical_field_decoding = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject logical_field_decoding_gaf = NIL;
            destructuring_bind_must_consp(current, datum, $list5);
            logical_field_decoding_gaf = current.first();
            final SubLObject body;
            current = body = current.rest();
            return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_logical_schema, logical_field, from_logical_schema, logical_field_decoding), logical_field_decoding_gaf, append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list5);
        return NIL;
    }

    /**
     * Gets the logical field indexicals s from LOGICAL-FIELD-DECODING.
     */
    @LispMethod(comment = "Gets the logical field indexicals s from LOGICAL-FIELD-DECODING.")
    public static final SubLObject logical_field_decoding_logical_field_indexicals_alt(SubLObject logical_field_decoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(logical_field_decoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gets the logical field indexicals s from LOGICAL-FIELD-DECODING.
     */
    @LispMethod(comment = "Gets the logical field indexicals s from LOGICAL-FIELD-DECODING.")
    public static SubLObject logical_field_decoding_logical_field_indexicals(final SubLObject logical_field_decoding) {
        return field_translation_fields(logical_field_decoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Returns all logical field decoding gafs for LOGICAL-FIELD.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all logical field decoding gafs for LOGICAL-FIELD.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.\nReturns all logical field decoding gafs for LOGICAL-FIELD.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_logical_field_decoding_gafs_for_logical_field_alt(SubLObject logical_field, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        {
            SubLObject logical_field_indexical = sksi_kb_accessors.indexical_for_logical_field(logical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata);
        }
    }

    /**
     * Returns all logical field decoding gafs for LOGICAL-FIELD.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all logical field decoding gafs for LOGICAL-FIELD.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.\nReturns all logical field decoding gafs for LOGICAL-FIELD.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_logical_field_decoding_gafs_for_logical_field(final SubLObject logical_field, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        final SubLObject logical_field_indexical = sksi_kb_accessors.indexical_for_logical_field(logical_field);
        return get_logical_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata);
    }

    /**
     * Returns all logical field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all logical field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.\nReturns all logical field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_logical_field_decoding_gafs_for_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        {
            SubLObject logical_field_decoding_gafs = NIL;
            if (NIL != logical_schemata) {
                {
                    SubLObject pred_var = $$logicalFieldDecoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_1 = NIL;
                                                    SubLObject token_var_2 = NIL;
                                                    while (NIL == done_var_1) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_2);
                                                            SubLObject valid_3 = makeBoolean(token_var_2 != assertion);
                                                            if (NIL != valid_3) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_logical_schema = NIL;
                                                                    SubLObject to_logical_field_indexical = NIL;
                                                                    SubLObject from_logical_schema = NIL;
                                                                    SubLObject decoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    to_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    to_logical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    from_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    decoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (((to_logical_field_indexical == logical_field_indexical) && (NIL != list_utilities.member_eqP(to_logical_schema, logical_schemata))) && (NIL != list_utilities.member_eqP(from_logical_schema, logical_schemata))) {
                                                                            logical_field_decoding_gafs = cons(assertion, logical_field_decoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt10);
                                                                    }
                                                                }
                                                            }
                                                            done_var_1 = makeBoolean(NIL == valid_3);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                {
                    SubLObject pred_var = $$logicalFieldDecoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_4 = NIL;
                                                    SubLObject token_var_5 = NIL;
                                                    while (NIL == done_var_4) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_5);
                                                            SubLObject valid_6 = makeBoolean(token_var_5 != assertion);
                                                            if (NIL != valid_6) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_logical_schema = NIL;
                                                                    SubLObject to_logical_field_indexical = NIL;
                                                                    SubLObject from_logical_schema = NIL;
                                                                    SubLObject decoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    to_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    to_logical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    from_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt10);
                                                                    decoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (to_logical_field_indexical == logical_field_indexical) {
                                                                            logical_field_decoding_gafs = cons(assertion, logical_field_decoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt10);
                                                                    }
                                                                }
                                                            }
                                                            done_var_4 = makeBoolean(NIL == valid_6);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return logical_field_decoding_gafs;
        }
    }

    /**
     * Returns all logical field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all logical field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.\nReturns all logical field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_logical_field_decoding_gafs_for_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject logical_field_decoding_gafs = NIL;
        if (NIL != logical_schemata) {
            final SubLObject pred_var = $$logicalFieldDecoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$1 = NIL;
                                final SubLObject token_var_$2 = NIL;
                                while (NIL == done_var_$1) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$2);
                                    final SubLObject valid_$3 = makeBoolean(!token_var_$2.eql(assertion));
                                    if (NIL != valid_$3) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject from_logical_schema = NIL;
                                        SubLObject decoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        to_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        from_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        decoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if ((to_logical_field_indexical.eql(logical_field_indexical) && (NIL != list_utilities.member_eqP(to_logical_schema, logical_schemata))) && (NIL != list_utilities.member_eqP(from_logical_schema, logical_schemata))) {
                                                logical_field_decoding_gafs = cons(assertion, logical_field_decoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list10);
                                        }
                                    }
                                    done_var_$1 = makeBoolean(NIL == valid_$3);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$4 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$4, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        } else {
            final SubLObject pred_var = $$logicalFieldDecoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$2 = NIL;
                                final SubLObject token_var_$3 = NIL;
                                while (NIL == done_var_$2) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$3);
                                    final SubLObject valid_$4 = makeBoolean(!token_var_$3.eql(assertion));
                                    if (NIL != valid_$4) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject from_logical_schema = NIL;
                                        SubLObject decoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        to_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        from_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list10);
                                        decoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (to_logical_field_indexical.eql(logical_field_indexical)) {
                                                logical_field_decoding_gafs = cons(assertion, logical_field_decoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list10);
                                        }
                                    }
                                    done_var_$2 = makeBoolean(NIL == valid_$4);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$5 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values2 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values2);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$5, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        }
        return logical_field_decoding_gafs;
    }

    /**
     * This behaves just like GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD except that it returns just
     * the corresponding logical field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD except that it returns just\r\nthe corresponding logical field decodings (and not the gafs).\nThis behaves just like GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD except that it returns just\nthe corresponding logical field decodings (and not the gafs).")
    public static final SubLObject get_logical_field_decodings_for_logical_field_alt(SubLObject logical_field, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        {
            SubLObject logical_field_decoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_decoding_gafs_for_logical_field(logical_field, logical_schemata);
            SubLObject logical_field_decodings = NIL;
            SubLObject cdolist_list_var = logical_field_decoding_gafs;
            SubLObject logical_field_decoding_gaf = NIL;
            for (logical_field_decoding_gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , logical_field_decoding_gaf = cdolist_list_var.first()) {
                logical_field_decodings = cons(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_decoding_from_gaf(logical_field_decoding_gaf), logical_field_decodings);
            }
            return nreverse(list_utilities.fast_delete_duplicates(logical_field_decodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        }
    }

    /**
     * This behaves just like GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD except that it returns just
     * the corresponding logical field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD except that it returns just\r\nthe corresponding logical field decodings (and not the gafs).\nThis behaves just like GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD except that it returns just\nthe corresponding logical field decodings (and not the gafs).")
    public static SubLObject get_logical_field_decodings_for_logical_field(final SubLObject logical_field, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        final SubLObject logical_field_decoding_gafs = get_logical_field_decoding_gafs_for_logical_field(logical_field, logical_schemata);
        SubLObject logical_field_decodings = NIL;
        SubLObject cdolist_list_var = logical_field_decoding_gafs;
        SubLObject logical_field_decoding_gaf = NIL;
        logical_field_decoding_gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            logical_field_decodings = cons(get_logical_field_decoding_from_gaf(logical_field_decoding_gaf), logical_field_decodings);
            cdolist_list_var = cdolist_list_var.rest();
            logical_field_decoding_gaf = cdolist_list_var.first();
        } 
        return nreverse(list_utilities.fast_delete_duplicates(logical_field_decodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject get_logical_field_encoding_from_gaf_alt(SubLObject logical_field_encoding_gaf) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_translation_from_gaf(logical_field_encoding_gaf);
    }

    public static SubLObject get_logical_field_encoding_from_gaf(final SubLObject logical_field_encoding_gaf) {
        return get_field_translation_from_gaf(logical_field_encoding_gaf);
    }

    public static final SubLObject destructure_logical_field_encoding_gaf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt11);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject to_logical_schema = NIL;
                    SubLObject logical_field = NIL;
                    SubLObject from_logical_schema = NIL;
                    SubLObject logical_field_encoding = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt11);
                    to_logical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt11);
                    logical_field = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt11);
                    from_logical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt11);
                    logical_field_encoding = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject logical_field_encoding_gaf = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt11);
                            logical_field_encoding_gaf = current.first();
                            current = current.rest();
                            {
                                SubLObject body = current;
                                return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_logical_schema, logical_field, from_logical_schema, logical_field_encoding), logical_field_encoding_gaf, append(body, NIL));
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt11);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject destructure_logical_field_encoding_gaf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list11);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject to_logical_schema = NIL;
        SubLObject logical_field = NIL;
        SubLObject from_logical_schema = NIL;
        SubLObject logical_field_encoding = NIL;
        destructuring_bind_must_consp(current, datum, $list11);
        to_logical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list11);
        logical_field = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list11);
        from_logical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list11);
        logical_field_encoding = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject logical_field_encoding_gaf = NIL;
            destructuring_bind_must_consp(current, datum, $list11);
            logical_field_encoding_gaf = current.first();
            final SubLObject body;
            current = body = current.rest();
            return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_logical_schema, logical_field, from_logical_schema, logical_field_encoding), logical_field_encoding_gaf, append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list11);
        return NIL;
    }

    /**
     * Gets the logical fields from LOGICAL-FIELD-ENCODING.
     */
    @LispMethod(comment = "Gets the logical fields from LOGICAL-FIELD-ENCODING.")
    public static final SubLObject logical_field_encoding_logical_field_indexicals_alt(SubLObject logical_field_encoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(logical_field_encoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gets the logical fields from LOGICAL-FIELD-ENCODING.
     */
    @LispMethod(comment = "Gets the logical fields from LOGICAL-FIELD-ENCODING.")
    public static SubLObject logical_field_encoding_logical_field_indexicals(final SubLObject logical_field_encoding) {
        return field_translation_fields(logical_field_encoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    public static final SubLObject logical_field_encoding_contains_logical_field_indexicalP_alt(SubLObject logical_field_encoding, SubLObject logical_field_indexical) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(logical_field_indexical, logical_field_encoding, T, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject logical_field_encoding_contains_logical_field_indexicalP(final SubLObject logical_field_encoding, final SubLObject logical_field_indexical) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(logical_field_indexical, logical_field_encoding, T, UNPROVIDED, UNPROVIDED));
    }

    /**
     * Returns all logical field encoding gafs to LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all logical field encoding gafs to LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.\nReturns all logical field encoding gafs to LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_logical_field_encoding_gafs_for_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        {
            SubLObject logical_field_encoding_gafs = NIL;
            if (NIL != logical_schemata) {
                {
                    SubLObject pred_var = $$logicalFieldEncoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_7 = NIL;
                                                    SubLObject token_var_8 = NIL;
                                                    while (NIL == done_var_7) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_8);
                                                            SubLObject valid_9 = makeBoolean(token_var_8 != assertion);
                                                            if (NIL != valid_9) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_logical_schema = NIL;
                                                                    SubLObject to_logical_field_indexical = NIL;
                                                                    SubLObject from_logical_schema = NIL;
                                                                    SubLObject encoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    from_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    encoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (((to_logical_field_indexical == logical_field_indexical) && (NIL != list_utilities.member_eqP(to_logical_schema, logical_schemata))) && (NIL != list_utilities.member_eqP(from_logical_schema, logical_schemata))) {
                                                                            logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt13);
                                                                    }
                                                                }
                                                            }
                                                            done_var_7 = makeBoolean(NIL == valid_9);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                {
                    SubLObject pred_var = $$logicalFieldEncoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_10 = NIL;
                                                    SubLObject token_var_11 = NIL;
                                                    while (NIL == done_var_10) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_11);
                                                            SubLObject valid_12 = makeBoolean(token_var_11 != assertion);
                                                            if (NIL != valid_12) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_logical_schema = NIL;
                                                                    SubLObject to_logical_field_indexical = NIL;
                                                                    SubLObject from_logical_schema = NIL;
                                                                    SubLObject encoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    from_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    encoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (to_logical_field_indexical == logical_field_indexical) {
                                                                            logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt13);
                                                                    }
                                                                }
                                                            }
                                                            done_var_10 = makeBoolean(NIL == valid_12);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return logical_field_encoding_gafs;
        }
    }

    /**
     * Returns all logical field encoding gafs to LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all logical field encoding gafs to LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.\nReturns all logical field encoding gafs to LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_logical_field_encoding_gafs_for_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject logical_field_encoding_gafs = NIL;
        if (NIL != logical_schemata) {
            final SubLObject pred_var = $$logicalFieldEncoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$9 = NIL;
                                final SubLObject token_var_$10 = NIL;
                                while (NIL == done_var_$9) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$10);
                                    final SubLObject valid_$11 = makeBoolean(!token_var_$10.eql(assertion));
                                    if (NIL != valid_$11) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject from_logical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        from_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if ((to_logical_field_indexical.eql(logical_field_indexical) && (NIL != list_utilities.member_eqP(to_logical_schema, logical_schemata))) && (NIL != list_utilities.member_eqP(from_logical_schema, logical_schemata))) {
                                                logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list13);
                                        }
                                    }
                                    done_var_$9 = makeBoolean(NIL == valid_$11);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$12 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$12, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        } else {
            final SubLObject pred_var = $$logicalFieldEncoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$10 = NIL;
                                final SubLObject token_var_$11 = NIL;
                                while (NIL == done_var_$10) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$11);
                                    final SubLObject valid_$12 = makeBoolean(!token_var_$11.eql(assertion));
                                    if (NIL != valid_$12) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject from_logical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        from_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (to_logical_field_indexical.eql(logical_field_indexical)) {
                                                logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list13);
                                        }
                                    }
                                    done_var_$10 = makeBoolean(NIL == valid_$12);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$13 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values2 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values2);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$13, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        }
        return logical_field_encoding_gafs;
    }

    /**
     * Returns all logical field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are c-indexicalonsidered.
     */
    @LispMethod(comment = "Returns all logical field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are c-indexicalonsidered.\nReturns all logical field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are c-indexicalonsidered.")
    public static final SubLObject get_logical_field_encoding_gafs_from_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        {
            SubLObject logical_field_encoding_gafs = NIL;
            if (NIL != logical_schemata) {
                {
                    SubLObject pred_var = $$logicalFieldEncoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_13 = NIL;
                                                    SubLObject token_var_14 = NIL;
                                                    while (NIL == done_var_13) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_14);
                                                            SubLObject valid_15 = makeBoolean(token_var_14 != assertion);
                                                            if (NIL != valid_15) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_logical_schema = NIL;
                                                                    SubLObject to_logical_field_indexical = NIL;
                                                                    SubLObject from_logical_schema = NIL;
                                                                    SubLObject encoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    from_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    encoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (((NIL != list_utilities.member_eqP(to_logical_schema, logical_schemata)) && (NIL != list_utilities.member_eqP(from_logical_schema, logical_schemata))) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.logical_field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                                            logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt13);
                                                                    }
                                                                }
                                                            }
                                                            done_var_13 = makeBoolean(NIL == valid_15);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                {
                    SubLObject pred_var = $$logicalFieldEncoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_16 = NIL;
                                                    SubLObject token_var_17 = NIL;
                                                    while (NIL == done_var_16) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_17);
                                                            SubLObject valid_18 = makeBoolean(token_var_17 != assertion);
                                                            if (NIL != valid_18) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_logical_schema = NIL;
                                                                    SubLObject to_logical_field_indexical = NIL;
                                                                    SubLObject from_logical_schema = NIL;
                                                                    SubLObject encoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    to_logical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    from_logical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt13);
                                                                    encoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.logical_field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical)) {
                                                                            logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt13);
                                                                    }
                                                                }
                                                            }
                                                            done_var_16 = makeBoolean(NIL == valid_18);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return logical_field_encoding_gafs;
        }
    }

    /**
     * Returns all logical field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then
     * only logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are c-indexicalonsidered.
     */
    @LispMethod(comment = "Returns all logical field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\r\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are c-indexicalonsidered.\nReturns all logical field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA is non-null, then\nonly logical field decoding gafs both logical schemata of which are in LOGICAL-SCHEMATA are c-indexicalonsidered.")
    public static SubLObject get_logical_field_encoding_gafs_from_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject logical_field_encoding_gafs = NIL;
        if (NIL != logical_schemata) {
            final SubLObject pred_var = $$logicalFieldEncoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$17 = NIL;
                                final SubLObject token_var_$18 = NIL;
                                while (NIL == done_var_$17) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$18);
                                    final SubLObject valid_$19 = makeBoolean(!token_var_$18.eql(assertion));
                                    if (NIL != valid_$19) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject from_logical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        from_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (((NIL != list_utilities.member_eqP(to_logical_schema, logical_schemata)) && (NIL != list_utilities.member_eqP(from_logical_schema, logical_schemata))) && (NIL != logical_field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list13);
                                        }
                                    }
                                    done_var_$17 = makeBoolean(NIL == valid_$19);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$20 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$20, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        } else {
            final SubLObject pred_var = $$logicalFieldEncoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$18 = NIL;
                                final SubLObject token_var_$19 = NIL;
                                while (NIL == done_var_$18) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$19);
                                    final SubLObject valid_$20 = makeBoolean(!token_var_$19.eql(assertion));
                                    if (NIL != valid_$20) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject from_logical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        from_logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list13);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (NIL != logical_field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical)) {
                                                logical_field_encoding_gafs = cons(assertion, logical_field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list13);
                                        }
                                    }
                                    done_var_$18 = makeBoolean(NIL == valid_$20);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$21 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values2 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values2);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$21, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        }
        return logical_field_encoding_gafs;
    }

    public static final SubLObject get_field_decoding_from_gaf_alt(SubLObject field_decoding_gaf) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_decoding(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_translation_from_gaf(field_decoding_gaf), UNPROVIDED);
    }

    public static SubLObject get_field_decoding_from_gaf(final SubLObject field_decoding_gaf) {
        return sksi_reconstruct_decoding(get_field_translation_from_gaf(field_decoding_gaf), UNPROVIDED);
    }

    public static final SubLObject destructure_field_decoding_gaf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt14);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject logical_schema = NIL;
                    SubLObject logical_field = NIL;
                    SubLObject physical_schema = NIL;
                    SubLObject field_decoding = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt14);
                    logical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt14);
                    logical_field = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt14);
                    physical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt14);
                    field_decoding = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject field_decoding_gaf = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt14);
                            field_decoding_gaf = current.first();
                            current = current.rest();
                            {
                                SubLObject body = current;
                                return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(logical_schema, logical_field, physical_schema, field_decoding), field_decoding_gaf, append(body, NIL));
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt14);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject destructure_field_decoding_gaf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list14);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject logical_schema = NIL;
        SubLObject logical_field = NIL;
        SubLObject physical_schema = NIL;
        SubLObject field_decoding = NIL;
        destructuring_bind_must_consp(current, datum, $list14);
        logical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list14);
        logical_field = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list14);
        physical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list14);
        field_decoding = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject field_decoding_gaf = NIL;
            destructuring_bind_must_consp(current, datum, $list14);
            field_decoding_gaf = current.first();
            final SubLObject body;
            current = body = current.rest();
            return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(logical_schema, logical_field, physical_schema, field_decoding), field_decoding_gaf, append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list14);
        return NIL;
    }

    /**
     * Gets the physical field indexicals from FIELD-DECODING.
     */
    @LispMethod(comment = "Gets the physical field indexicals from FIELD-DECODING.")
    public static final SubLObject field_decoding_physical_field_indexicals_alt(SubLObject field_decoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(field_decoding, PHYSICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gets the physical field indexicals from FIELD-DECODING.
     */
    @LispMethod(comment = "Gets the physical field indexicals from FIELD-DECODING.")
    public static SubLObject field_decoding_physical_field_indexicals(final SubLObject field_decoding) {
        return field_translation_fields(field_decoding, PHYSICAL_FIELD_INDEXICAL_P);
    }

    public static final SubLObject field_decoding_contains_physical_field_indexicalP_alt(SubLObject decoding, SubLObject physical_field) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(physical_field, decoding, T, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject field_decoding_contains_physical_field_indexicalP(final SubLObject decoding, final SubLObject physical_field) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(physical_field, decoding, T, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject field_decoding_contains_physical_field_indexicalsP_alt(SubLObject decoding, SubLObject physical_fields) {
        {
            SubLObject missingP = NIL;
            if (NIL == missingP) {
                {
                    SubLObject csome_list_var = physical_fields;
                    SubLObject physical_field = NIL;
                    for (physical_field = csome_list_var.first(); !((NIL != missingP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , physical_field = csome_list_var.first()) {
                        if (NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_decoding_contains_physical_field_indexicalP(decoding, physical_field)) {
                            missingP = T;
                        }
                    }
                }
            }
            return makeBoolean(NIL == missingP);
        }
    }

    public static SubLObject field_decoding_contains_physical_field_indexicalsP(final SubLObject decoding, final SubLObject physical_fields) {
        SubLObject missingP = NIL;
        if (NIL == missingP) {
            SubLObject csome_list_var = physical_fields;
            SubLObject physical_field = NIL;
            physical_field = csome_list_var.first();
            while ((NIL == missingP) && (NIL != csome_list_var)) {
                if (NIL == field_decoding_contains_physical_field_indexicalP(decoding, physical_field)) {
                    missingP = T;
                }
                csome_list_var = csome_list_var.rest();
                physical_field = csome_list_var.first();
            } 
        }
        return makeBoolean(NIL == missingP);
    }

    public static final SubLObject relevant_field_decodings_internal_alt(SubLObject logical_field, SubLObject logical_schema, SubLObject physical_schema) {
        {
            SubLObject logical_field_indexical = sksi_kb_accessors.indexical_for_logical_field(logical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.relevant_field_decodings_for_indexical(logical_field_indexical, list(logical_schema), list(physical_schema));
        }
    }

    public static SubLObject relevant_field_decodings_internal(final SubLObject logical_field, final SubLObject logical_schema, final SubLObject physical_schema) {
        final SubLObject logical_field_indexical = sksi_kb_accessors.indexical_for_logical_field(logical_field);
        return relevant_field_decodings_for_indexical(logical_field_indexical, list(logical_schema), list(physical_schema));
    }

    public static final SubLObject relevant_field_decodings_alt(SubLObject logical_field, SubLObject logical_schema, SubLObject physical_schema) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.relevant_field_decodings_internal(logical_field, logical_schema, physical_schema);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, RELEVANT_FIELD_DECODINGS, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), RELEVANT_FIELD_DECODINGS, THREE_INTEGER, NIL, EQ, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, RELEVANT_FIELD_DECODINGS, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_3(logical_field, logical_schema, physical_schema);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (logical_field == cached_args.first()) {
                                        cached_args = cached_args.rest();
                                        if (logical_schema == cached_args.first()) {
                                            cached_args = cached_args.rest();
                                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && (physical_schema == cached_args.first())) {
                                                return memoization_state.caching_results(results2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.relevant_field_decodings_internal(logical_field, logical_schema, physical_schema)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(logical_field, logical_schema, physical_schema));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject relevant_field_decodings(final SubLObject logical_field, final SubLObject logical_schema, final SubLObject physical_schema) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return relevant_field_decodings_internal(logical_field, logical_schema, physical_schema);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, RELEVANT_FIELD_DECODINGS, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), RELEVANT_FIELD_DECODINGS, THREE_INTEGER, NIL, EQ, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, RELEVANT_FIELD_DECODINGS, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_3(logical_field, logical_schema, physical_schema);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (logical_field.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (logical_schema.eql(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && physical_schema.eql(cached_args.first())) {
                            return memoization_state.caching_results(results2);
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(relevant_field_decodings_internal(logical_field, logical_schema, physical_schema)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(logical_field, logical_schema, physical_schema));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject relevant_field_decodings_for_indexical_internal_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_decodings_for_logical_field_indexical(logical_field_indexical, logical_schemata, physical_schemata);
    }

    public static SubLObject relevant_field_decodings_for_indexical_internal(final SubLObject logical_field_indexical, final SubLObject logical_schemata, final SubLObject physical_schemata) {
        return get_field_decodings_for_logical_field_indexical(logical_field_indexical, logical_schemata, physical_schemata);
    }

    public static final SubLObject relevant_field_decodings_for_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.relevant_field_decodings_for_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL, THREE_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_3(logical_field_indexical, logical_schemata, physical_schemata);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (logical_field_indexical.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (logical_schemata.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && physical_schemata.equal(cached_args.first())) {
                                                return memoization_state.caching_results(results2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.relevant_field_decodings_for_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(logical_field_indexical, logical_schemata, physical_schemata));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject relevant_field_decodings_for_indexical(final SubLObject logical_field_indexical, final SubLObject logical_schemata, final SubLObject physical_schemata) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return relevant_field_decodings_for_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL, THREE_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_3(logical_field_indexical, logical_schemata, physical_schemata);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (logical_field_indexical.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (logical_schemata.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && physical_schemata.equal(cached_args.first())) {
                            return memoization_state.caching_results(results2);
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(relevant_field_decodings_for_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(logical_field_indexical, logical_schemata, physical_schemata));
        return memoization_state.caching_results(results3);
    }

    /**
     * Returns all field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_field_decoding_gafs_for_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject field_decoding_gafs = NIL;
            if (NIL != logical_schemata) {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
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
                                                        SubLObject done_var_19 = NIL;
                                                        SubLObject token_var_20 = NIL;
                                                        while (NIL == done_var_19) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_20);
                                                                SubLObject valid_21 = makeBoolean(token_var_20 != assertion);
                                                                if (NIL != valid_21) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject to_logical_field_indexical = NIL;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject decoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        to_logical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        decoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if ((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != list_utilities.member_eqP(physical_schema, physical_schemata))) {
                                                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt19);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_19 = makeBoolean(NIL == valid_21);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
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
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_23);
                                                                SubLObject valid_24 = makeBoolean(token_var_23 != assertion);
                                                                if (NIL != valid_24) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject to_logical_field_indexical = NIL;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject decoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        to_logical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        decoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) {
                                                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt19);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_22 = makeBoolean(NIL == valid_24);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
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
                                                        SubLObject done_var_25 = NIL;
                                                        SubLObject token_var_26 = NIL;
                                                        while (NIL == done_var_25) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_26);
                                                                SubLObject valid_27 = makeBoolean(token_var_26 != assertion);
                                                                if (NIL != valid_27) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject to_logical_field_indexical = NIL;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject decoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        to_logical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt19);
                                                                        decoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) {
                                                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt19);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_25 = makeBoolean(NIL == valid_27);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
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
                                                        SubLObject done_var_28 = NIL;
                                                        SubLObject token_var_29 = NIL;
                                                        while (NIL == done_var_28) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_29);
                                                                SubLObject valid_30 = makeBoolean(token_var_29 != assertion);
                                                                if (NIL != valid_30) {
                                                                    field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                }
                                                                done_var_28 = makeBoolean(NIL == valid_30);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return field_decoding_gafs;
        }
    }

    /**
     * Returns all field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field decoding gafs for LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_field_decoding_gafs_for_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        SubLObject field_decoding_gafs = NIL;
        if (NIL != logical_schemata) {
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$25 = NIL;
                                final SubLObject token_var_$26 = NIL;
                                while (NIL == done_var_$25) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$26);
                                    final SubLObject valid_$27 = makeBoolean(!token_var_$26.eql(assertion));
                                    if (NIL != valid_$27) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject physical_schema = NIL;
                                        SubLObject decoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        decoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if ((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != list_utilities.member_eqP(physical_schema, physical_schemata))) {
                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list18);
                                        }
                                    }
                                    done_var_$25 = makeBoolean(NIL == valid_$27);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            } else {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$26 = NIL;
                                final SubLObject token_var_$27 = NIL;
                                while (NIL == done_var_$26) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$27);
                                    final SubLObject valid_$28 = makeBoolean(!token_var_$27.eql(assertion));
                                    if (NIL != valid_$28) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject physical_schema = NIL;
                                        SubLObject decoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        decoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) {
                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list18);
                                        }
                                    }
                                    done_var_$26 = makeBoolean(NIL == valid_$28);
                                } 
                            } finally {
                                final SubLObject _prev_bind_2 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values2 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values2);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_2);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            }
        } else
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$27 = NIL;
                                final SubLObject token_var_$28 = NIL;
                                while (NIL == done_var_$27) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$28);
                                    final SubLObject valid_$29 = makeBoolean(!token_var_$28.eql(assertion));
                                    if (NIL != valid_$29) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject logical_schema = NIL;
                                        SubLObject to_logical_field_indexical = NIL;
                                        SubLObject physical_schema = NIL;
                                        SubLObject decoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        to_logical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list18);
                                        decoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) {
                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list18);
                                        }
                                    }
                                    done_var_$27 = makeBoolean(NIL == valid_$29);
                                } 
                            } finally {
                                final SubLObject _prev_bind_3 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values3 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values3);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_3);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            } else {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(logical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(logical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$28 = NIL;
                                final SubLObject token_var_$29 = NIL;
                                while (NIL == done_var_$28) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$29);
                                    final SubLObject valid_$30 = makeBoolean(!token_var_$29.eql(assertion));
                                    if (NIL != valid_$30) {
                                        field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                    }
                                    done_var_$28 = makeBoolean(NIL == valid_$30);
                                } 
                            } finally {
                                final SubLObject _prev_bind_4 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values4 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values4);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_4);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            }

        return field_decoding_gafs;
    }

    /**
     * This behaves just like GET-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL except that it returns
     * just the corresponding field decodings (and not the GAFs).
     */
    @LispMethod(comment = "This behaves just like GET-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL except that it returns\r\njust the corresponding field decodings (and not the GAFs).\nThis behaves just like GET-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL except that it returns\njust the corresponding field decodings (and not the GAFs).")
    public static final SubLObject get_field_decodings_for_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject logical_field_indexical_fort = sksi_kb_accessors.lfi_fort_for_lfi(logical_field_indexical);
            SubLObject field_decoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical_fort, logical_schemata, physical_schemata);
            return list_utilities.fast_delete_duplicates(Mapping.mapcar(GET_FIELD_DECODING_FROM_GAF, field_decoding_gafs), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
    }

    /**
     * This behaves just like GET-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL except that it returns
     * just the corresponding field decodings (and not the GAFs).
     */
    @LispMethod(comment = "This behaves just like GET-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL except that it returns\r\njust the corresponding field decodings (and not the GAFs).\nThis behaves just like GET-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL except that it returns\njust the corresponding field decodings (and not the GAFs).")
    public static SubLObject get_field_decodings_for_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject logical_field_indexical_fort = sksi_kb_accessors.lfi_fort_for_lfi(logical_field_indexical);
        final SubLObject field_decoding_gafs = get_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical_fort, logical_schemata, physical_schemata);
        return list_utilities.fast_delete_duplicates(Mapping.mapcar(GET_FIELD_DECODING_FROM_GAF, field_decoding_gafs), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    /**
     * Returns all field decoding gafs from PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field decoding gafs from PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field decoding gafs from PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_field_decoding_gafs_from_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject field_decoding_gafs = NIL;
            if (NIL != logical_schemata) {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_31 = NIL;
                                                        SubLObject token_var_32 = NIL;
                                                        while (NIL == done_var_31) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_32);
                                                                SubLObject valid_33 = makeBoolean(token_var_32 != assertion);
                                                                if (NIL != valid_33) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject logical_field = NIL;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject decoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_field = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        decoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != list_utilities.member_eqP(physical_schema, physical_schemata))) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical))) {
                                                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt21);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_31 = makeBoolean(NIL == valid_33);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_34 = NIL;
                                                        SubLObject token_var_35 = NIL;
                                                        while (NIL == done_var_34) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_35);
                                                                SubLObject valid_36 = makeBoolean(token_var_35 != assertion);
                                                                if (NIL != valid_36) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject logical_field = NIL;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject decoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_field = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        decoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if ((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical))) {
                                                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt21);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_34 = makeBoolean(NIL == valid_36);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_37 = NIL;
                                                        SubLObject token_var_38 = NIL;
                                                        while (NIL == done_var_37) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_38);
                                                                SubLObject valid_39 = makeBoolean(token_var_38 != assertion);
                                                                if (NIL != valid_39) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject logical_field = NIL;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject decoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_field = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        decoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if ((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical))) {
                                                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt21);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_37 = makeBoolean(NIL == valid_39);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldDecoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_40 = NIL;
                                                        SubLObject token_var_41 = NIL;
                                                        while (NIL == done_var_40) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_41);
                                                                SubLObject valid_42 = makeBoolean(token_var_41 != assertion);
                                                                if (NIL != valid_42) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject logical_field = NIL;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject decoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        logical_field = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt21);
                                                                        decoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical)) {
                                                                                field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt21);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_40 = makeBoolean(NIL == valid_42);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return field_decoding_gafs;
        }
    }

    /**
     * Returns all field decoding gafs from PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field decoding gafs from PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field decoding gafs from PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_field_decoding_gafs_from_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject field_decoding_gafs = NIL;
        if (NIL != logical_schemata) {
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$37 = NIL;
                                    final SubLObject token_var_$38 = NIL;
                                    while (NIL == done_var_$37) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$38);
                                        final SubLObject valid_$39 = makeBoolean(!token_var_$38.eql(assertion));
                                        if (NIL != valid_$39) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject logical_schema = NIL;
                                            SubLObject logical_field = NIL;
                                            SubLObject physical_schema = NIL;
                                            SubLObject decoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_field = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            decoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if (((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != list_utilities.member_eqP(physical_schema, physical_schemata))) && (NIL != field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical))) {
                                                    field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list20);
                                            }
                                        }
                                        done_var_$37 = makeBoolean(NIL == valid_$39);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$40 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$40, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            } else {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$38 = NIL;
                                    final SubLObject token_var_$39 = NIL;
                                    while (NIL == done_var_$38) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$39);
                                        final SubLObject valid_$40 = makeBoolean(!token_var_$39.eql(assertion));
                                        if (NIL != valid_$40) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject logical_schema = NIL;
                                            SubLObject logical_field = NIL;
                                            SubLObject physical_schema = NIL;
                                            SubLObject decoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_field = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            decoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if ((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical))) {
                                                    field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list20);
                                            }
                                        }
                                        done_var_$38 = makeBoolean(NIL == valid_$40);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$41 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values2 = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values2);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$41, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            }
        } else
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$39 = NIL;
                                    final SubLObject token_var_$40 = NIL;
                                    while (NIL == done_var_$39) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$40);
                                        final SubLObject valid_$41 = makeBoolean(!token_var_$40.eql(assertion));
                                        if (NIL != valid_$41) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject logical_schema = NIL;
                                            SubLObject logical_field = NIL;
                                            SubLObject physical_schema = NIL;
                                            SubLObject decoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_field = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            decoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if ((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical))) {
                                                    field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list20);
                                            }
                                        }
                                        done_var_$39 = makeBoolean(NIL == valid_$41);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$42 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values3 = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values3);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$42, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            } else {
                final SubLObject pred_var = $$fieldDecoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$40 = NIL;
                                    final SubLObject token_var_$41 = NIL;
                                    while (NIL == done_var_$40) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$41);
                                        final SubLObject valid_$42 = makeBoolean(!token_var_$41.eql(assertion));
                                        if (NIL != valid_$42) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject logical_schema = NIL;
                                            SubLObject logical_field = NIL;
                                            SubLObject physical_schema = NIL;
                                            SubLObject decoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            logical_field = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list20);
                                            decoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if (NIL != field_decoding_contains_physical_field_indexicalP(decoding, physical_field_indexical)) {
                                                    field_decoding_gafs = cons(assertion, field_decoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list20);
                                            }
                                        }
                                        done_var_$40 = makeBoolean(NIL == valid_$42);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$43 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values4 = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values4);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$43, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            }

        return field_decoding_gafs;
    }

    /**
     * This behaves just like GET-FIELD-DECODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-FIELD-DECODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding field decodings (and not the gafs).\nThis behaves just like GET-FIELD-DECODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding field decodings (and not the gafs).")
    public static final SubLObject get_field_decodings_from_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject field_decoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_decoding_gafs_from_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
            return list_utilities.fast_delete_duplicates(Mapping.mapcar(GET_FIELD_DECODING_FROM_GAF, field_decoding_gafs), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
    }

    /**
     * This behaves just like GET-FIELD-DECODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-FIELD-DECODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding field decodings (and not the gafs).\nThis behaves just like GET-FIELD-DECODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding field decodings (and not the gafs).")
    public static SubLObject get_field_decodings_from_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject field_decoding_gafs = get_field_decoding_gafs_from_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
        return list_utilities.fast_delete_duplicates(Mapping.mapcar(GET_FIELD_DECODING_FROM_GAF, field_decoding_gafs), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject get_field_encoding_from_gaf_alt(SubLObject field_encoding_gaf) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_encoding(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_translation_from_gaf(field_encoding_gaf), UNPROVIDED);
    }

    public static SubLObject get_field_encoding_from_gaf(final SubLObject field_encoding_gaf) {
        return sksi_reconstruct_encoding(get_field_translation_from_gaf(field_encoding_gaf), UNPROVIDED);
    }

    public static final SubLObject destructure_field_encoding_gaf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt22);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject physical_schema = NIL;
                    SubLObject physical_field_indexical = NIL;
                    SubLObject logical_schema = NIL;
                    SubLObject field_encoding = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt22);
                    physical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt22);
                    physical_field_indexical = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt22);
                    logical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt22);
                    field_encoding = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject field_encoding_gaf = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt22);
                            field_encoding_gaf = current.first();
                            current = current.rest();
                            {
                                SubLObject body = current;
                                return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(physical_schema, physical_field_indexical, logical_schema, field_encoding), field_encoding_gaf, append(body, NIL));
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt22);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject destructure_field_encoding_gaf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list21);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject physical_schema = NIL;
        SubLObject physical_field_indexical = NIL;
        SubLObject logical_schema = NIL;
        SubLObject field_encoding = NIL;
        destructuring_bind_must_consp(current, datum, $list21);
        physical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list21);
        physical_field_indexical = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list21);
        logical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list21);
        field_encoding = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject field_encoding_gaf = NIL;
            destructuring_bind_must_consp(current, datum, $list21);
            field_encoding_gaf = current.first();
            final SubLObject body;
            current = body = current.rest();
            return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(physical_schema, physical_field_indexical, logical_schema, field_encoding), field_encoding_gaf, append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list21);
        return NIL;
    }

    /**
     * Gets the logical field indexicals from FIELD-ENCODING.
     */
    @LispMethod(comment = "Gets the logical field indexicals from FIELD-ENCODING.")
    public static final SubLObject field_encoding_logical_field_indexicals_alt(SubLObject field_encoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(field_encoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gets the logical field indexicals from FIELD-ENCODING.
     */
    @LispMethod(comment = "Gets the logical field indexicals from FIELD-ENCODING.")
    public static SubLObject field_encoding_logical_field_indexicals(final SubLObject field_encoding) {
        return field_translation_fields(field_encoding, LOGICAL_FIELD_INDEXICAL_P);
    }

    public static final SubLObject isomorphic_field_encoding_p_alt(SubLObject field_encoding) {
        return makeBoolean((NIL != sksi_kb_accessors.schema_object_id_fn_expression_p(field_encoding)) || (NIL != sksi_kb_accessors.source_schema_object_id_fn_expression_p(field_encoding)));
    }

    public static SubLObject isomorphic_field_encoding_p(final SubLObject field_encoding) {
        return makeBoolean((NIL != sksi_kb_accessors.schema_object_id_fn_expression_p(field_encoding)) || (NIL != sksi_kb_accessors.source_schema_object_id_fn_expression_p(field_encoding)));
    }

    public static final SubLObject field_encoding_contains_logical_field_indexicalP_alt(SubLObject encoding, SubLObject logical_field_indexical) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(logical_field_indexical, encoding, T, symbol_function(EQ), UNPROVIDED));
    }

    public static SubLObject field_encoding_contains_logical_field_indexicalP(final SubLObject encoding, final SubLObject logical_field_indexical) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(logical_field_indexical, encoding, T, symbol_function(EQ), UNPROVIDED));
    }

    public static final SubLObject relevant_field_encodings_internal_alt(SubLObject physical_field, SubLObject physical_schema, SubLObject logical_schema) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encodings_for_physical_field(physical_field, list(logical_schema), list(physical_schema));
    }

    public static SubLObject relevant_field_encodings_internal(final SubLObject physical_field, final SubLObject physical_schema, final SubLObject logical_schema) {
        return get_field_encodings_for_physical_field(physical_field, list(logical_schema), list(physical_schema));
    }

    public static final SubLObject relevant_field_encodings_alt(SubLObject physical_field, SubLObject physical_schema, SubLObject logical_schema) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.relevant_field_encodings_internal(physical_field, physical_schema, logical_schema);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, RELEVANT_FIELD_ENCODINGS, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), RELEVANT_FIELD_ENCODINGS, THREE_INTEGER, NIL, EQ, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, RELEVANT_FIELD_ENCODINGS, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_3(physical_field, physical_schema, logical_schema);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (physical_field == cached_args.first()) {
                                        cached_args = cached_args.rest();
                                        if (physical_schema == cached_args.first()) {
                                            cached_args = cached_args.rest();
                                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && (logical_schema == cached_args.first())) {
                                                return memoization_state.caching_results(results2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.relevant_field_encodings_internal(physical_field, physical_schema, logical_schema)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(physical_field, physical_schema, logical_schema));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject relevant_field_encodings(final SubLObject physical_field, final SubLObject physical_schema, final SubLObject logical_schema) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return relevant_field_encodings_internal(physical_field, physical_schema, logical_schema);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, RELEVANT_FIELD_ENCODINGS, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), RELEVANT_FIELD_ENCODINGS, THREE_INTEGER, NIL, EQ, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, RELEVANT_FIELD_ENCODINGS, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_3(physical_field, physical_schema, logical_schema);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (physical_field.eql(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (physical_schema.eql(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && logical_schema.eql(cached_args.first())) {
                            return memoization_state.caching_results(results2);
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(relevant_field_encodings_internal(physical_field, physical_schema, logical_schema)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(physical_field, physical_schema, logical_schema));
        return memoization_state.caching_results(results3);
    }

    /**
     * Returns all field encoding gafs for PHYSICAL-FIELD.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field encoding gafs for PHYSICAL-FIELD.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field encoding gafs for PHYSICAL-FIELD.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_field_encoding_gafs_for_physical_field_alt(SubLObject physical_field, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
        }
    }

    /**
     * Returns all field encoding gafs for PHYSICAL-FIELD.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field encoding gafs for PHYSICAL-FIELD.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field encoding gafs for PHYSICAL-FIELD.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_field_encoding_gafs_for_physical_field(final SubLObject physical_field, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
        return get_field_encoding_gafs_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
    }

    /**
     * This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just
     * the corresponding field encodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\r\nthe corresponding field encodings (and not the gafs).\nThis behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\nthe corresponding field encodings (and not the gafs).")
    public static final SubLObject get_field_encodings_for_physical_field_internal_alt(SubLObject physical_field, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encodings_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
        }
    }

    /**
     * This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just
     * the corresponding field encodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\r\nthe corresponding field encodings (and not the gafs).\nThis behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\nthe corresponding field encodings (and not the gafs).")
    public static SubLObject get_field_encodings_for_physical_field_internal(final SubLObject physical_field, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
        return get_field_encodings_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
    }

    public static final SubLObject get_field_encodings_for_physical_field_alt(SubLObject physical_field, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encodings_for_physical_field_internal(physical_field, logical_schemata, physical_schemata);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD, THREE_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_3(physical_field, logical_schemata, physical_schemata);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (physical_field.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (logical_schemata.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && physical_schemata.equal(cached_args.first())) {
                                                return memoization_state.caching_results(results2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encodings_for_physical_field_internal(physical_field, logical_schemata, physical_schemata)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(physical_field, logical_schemata, physical_schemata));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject get_field_encodings_for_physical_field(final SubLObject physical_field, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return get_field_encodings_for_physical_field_internal(physical_field, logical_schemata, physical_schemata);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD, THREE_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_3(physical_field, logical_schemata, physical_schemata);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (physical_field.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (logical_schemata.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && physical_schemata.equal(cached_args.first())) {
                            return memoization_state.caching_results(results2);
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(get_field_encodings_for_physical_field_internal(physical_field, logical_schemata, physical_schemata)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(physical_field, logical_schemata, physical_schemata));
        return memoization_state.caching_results(results3);
    }

    /**
     * Returns all field encoding gafs for PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field encoding gafs for PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field encoding gafs for PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_field_encoding_gafs_for_physical_field_indexical_internal_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject field_encoding_gafs = NIL;
            if (NIL != logical_schemata) {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
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
                                                        SubLObject done_var_43 = NIL;
                                                        SubLObject token_var_44 = NIL;
                                                        while (NIL == done_var_43) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_44);
                                                                SubLObject valid_45 = makeBoolean(token_var_44 != assertion);
                                                                if (NIL != valid_45) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject to_physical_field_indexical = NIL;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject encoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        to_physical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        encoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if ((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != list_utilities.member_eqP(logical_schema, logical_schemata))) {
                                                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt27);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_43 = makeBoolean(NIL == valid_45);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
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
                                                        SubLObject done_var_46 = NIL;
                                                        SubLObject token_var_47 = NIL;
                                                        while (NIL == done_var_46) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_47);
                                                                SubLObject valid_48 = makeBoolean(token_var_47 != assertion);
                                                                if (NIL != valid_48) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject to_physical_field_indexical = NIL;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject encoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        to_physical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        encoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) {
                                                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt27);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_46 = makeBoolean(NIL == valid_48);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
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
                                                        SubLObject done_var_49 = NIL;
                                                        SubLObject token_var_50 = NIL;
                                                        while (NIL == done_var_49) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_50);
                                                                SubLObject valid_51 = makeBoolean(token_var_50 != assertion);
                                                                if (NIL != valid_51) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject to_physical_field_indexical = NIL;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject encoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        to_physical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                                                        encoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) {
                                                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt27);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_49 = makeBoolean(NIL == valid_51);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
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
                                                        SubLObject done_var_52 = NIL;
                                                        SubLObject token_var_53 = NIL;
                                                        while (NIL == done_var_52) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_53);
                                                                SubLObject valid_54 = makeBoolean(token_var_53 != assertion);
                                                                if (NIL != valid_54) {
                                                                    field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                }
                                                                done_var_52 = makeBoolean(NIL == valid_54);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return field_encoding_gafs;
        }
    }

    /**
     * Returns all field encoding gafs for PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field encoding gafs for PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field encoding gafs for PHYSICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_field_encoding_gafs_for_physical_field_indexical_internal(final SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        SubLObject field_encoding_gafs = NIL;
        if (NIL != logical_schemata) {
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$53 = NIL;
                                final SubLObject token_var_$54 = NIL;
                                while (NIL == done_var_$53) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$54);
                                    final SubLObject valid_$55 = makeBoolean(!token_var_$54.eql(assertion));
                                    if (NIL != valid_$55) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject physical_schema = NIL;
                                        SubLObject to_physical_field_indexical = NIL;
                                        SubLObject logical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        to_physical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if ((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != list_utilities.member_eqP(logical_schema, logical_schemata))) {
                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list26);
                                        }
                                    }
                                    done_var_$53 = makeBoolean(NIL == valid_$55);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            } else {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$54 = NIL;
                                final SubLObject token_var_$55 = NIL;
                                while (NIL == done_var_$54) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$55);
                                    final SubLObject valid_$56 = makeBoolean(!token_var_$55.eql(assertion));
                                    if (NIL != valid_$56) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject physical_schema = NIL;
                                        SubLObject to_physical_field_indexical = NIL;
                                        SubLObject logical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        to_physical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) {
                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list26);
                                        }
                                    }
                                    done_var_$54 = makeBoolean(NIL == valid_$56);
                                } 
                            } finally {
                                final SubLObject _prev_bind_2 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values2 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values2);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_2);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            }
        } else
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$55 = NIL;
                                final SubLObject token_var_$56 = NIL;
                                while (NIL == done_var_$55) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$56);
                                    final SubLObject valid_$57 = makeBoolean(!token_var_$56.eql(assertion));
                                    if (NIL != valid_$57) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject physical_schema = NIL;
                                        SubLObject to_physical_field_indexical = NIL;
                                        SubLObject logical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        to_physical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        logical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list26);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) {
                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list26);
                                        }
                                    }
                                    done_var_$55 = makeBoolean(NIL == valid_$57);
                                } 
                            } finally {
                                final SubLObject _prev_bind_3 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values3 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values3);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_3);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            } else {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(physical_field_indexical, TWO_INTEGER, pred_var)) {
                    final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(physical_field_indexical, TWO_INTEGER, pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$56 = NIL;
                                final SubLObject token_var_$57 = NIL;
                                while (NIL == done_var_$56) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$57);
                                    final SubLObject valid_$58 = makeBoolean(!token_var_$57.eql(assertion));
                                    if (NIL != valid_$58) {
                                        field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                    }
                                    done_var_$56 = makeBoolean(NIL == valid_$58);
                                } 
                            } finally {
                                final SubLObject _prev_bind_4 = currentBinding($is_thread_performing_cleanupP$);
                                try {
                                    bind($is_thread_performing_cleanupP$, T);
                                    final SubLObject _values4 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values4);
                                } finally {
                                    rebind($is_thread_performing_cleanupP$, _prev_bind_4);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                }
            }

        return field_encoding_gafs;
    }

    public static final SubLObject get_field_encoding_gafs_for_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL, THREE_INTEGER, $int$100, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_3(physical_field_indexical, logical_schemata, physical_schemata);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (physical_field_indexical.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (logical_schemata.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && physical_schemata.equal(cached_args.first())) {
                                                return memoization_state.caching_results(results2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(physical_field_indexical, logical_schemata, physical_schemata));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject get_field_encoding_gafs_for_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return get_field_encoding_gafs_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL, THREE_INTEGER, $int$100, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_3(physical_field_indexical, logical_schemata, physical_schemata);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (physical_field_indexical.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (logical_schemata.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && physical_schemata.equal(cached_args.first())) {
                            return memoization_state.caching_results(results2);
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(get_field_encoding_gafs_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(physical_field_indexical, logical_schemata, physical_schemata));
        return memoization_state.caching_results(results3);
    }

    /**
     * This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding field encodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding field encodings (and not the gafs).\nThis behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding field encodings (and not the gafs).")
    public static final SubLObject get_field_encodings_for_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_indexical_fort = sksi_kb_accessors.pfi_fort_for_pfi(physical_field_indexical);
            SubLObject field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_physical_field_indexical(physical_field_indexical_fort, logical_schemata, physical_schemata);
            SubLObject field_encodings = NIL;
            SubLObject cdolist_list_var = field_encoding_gafs;
            SubLObject field_encoding_gaf = NIL;
            for (field_encoding_gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , field_encoding_gaf = cdolist_list_var.first()) {
                field_encodings = cons(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_from_gaf(field_encoding_gaf), field_encodings);
            }
            return nreverse(list_utilities.fast_delete_duplicates(field_encodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        }
    }

    @LispMethod(comment = "This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding field encodings (and not the gafs).\nThis behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding field encodings (and not the gafs).")
    public static SubLObject get_field_encodings_for_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_indexical_fort = sksi_kb_accessors.pfi_fort_for_pfi(physical_field_indexical);
        final SubLObject field_encoding_gafs = get_field_encoding_gafs_for_physical_field_indexical(physical_field_indexical_fort, logical_schemata, physical_schemata);
        SubLObject field_encodings = NIL;
        SubLObject cdolist_list_var = field_encoding_gafs;
        SubLObject field_encoding_gaf = NIL;
        field_encoding_gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            field_encodings = cons(get_field_encoding_from_gaf(field_encoding_gaf), field_encodings);
            cdolist_list_var = cdolist_list_var.rest();
            field_encoding_gaf = cdolist_list_var.first();
        } 
        return nreverse(list_utilities.fast_delete_duplicates(field_encodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }/**
     * This behaves just like GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding field encodings (and not the gafs).
     */


    /**
     * Returns all field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static final SubLObject get_field_encoding_gafs_for_logical_field_indexical_internal_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        {
            SubLObject field_encoding_gafs = NIL;
            if (NIL != logical_schemata) {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_55 = NIL;
                                                        SubLObject token_var_56 = NIL;
                                                        while (NIL == done_var_55) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_56);
                                                                SubLObject valid_57 = makeBoolean(token_var_56 != assertion);
                                                                if (NIL != valid_57) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject physical_field_indexical = NIL;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject encoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        encoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != list_utilities.member_eqP(logical_schema, logical_schemata))) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt30);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_55 = makeBoolean(NIL == valid_57);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_58 = NIL;
                                                        SubLObject token_var_59 = NIL;
                                                        while (NIL == done_var_58) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_59);
                                                                SubLObject valid_60 = makeBoolean(token_var_59 != assertion);
                                                                if (NIL != valid_60) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject physical_field_indexical = NIL;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject encoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        encoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if ((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt30);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_58 = makeBoolean(NIL == valid_60);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                if (NIL != physical_schemata) {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_61 = NIL;
                                                        SubLObject token_var_62 = NIL;
                                                        while (NIL == done_var_61) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_62);
                                                                SubLObject valid_63 = makeBoolean(token_var_62 != assertion);
                                                                if (NIL != valid_63) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject physical_field_indexical = NIL;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject encoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        encoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if ((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt30);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_61 = makeBoolean(NIL == valid_63);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
                } else {
                    {
                        SubLObject pred_var = $$fieldEncoding;
                        if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                            {
                                SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                        SubLObject done_var_64 = NIL;
                                                        SubLObject token_var_65 = NIL;
                                                        while (NIL == done_var_64) {
                                                            {
                                                                SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_65);
                                                                SubLObject valid_66 = makeBoolean(token_var_65 != assertion);
                                                                if (NIL != valid_66) {
                                                                    {
                                                                        SubLObject datum = assertions_high.gaf_args(assertion);
                                                                        SubLObject current = datum;
                                                                        SubLObject physical_schema = NIL;
                                                                        SubLObject physical_field_indexical = NIL;
                                                                        SubLObject logical_schema = NIL;
                                                                        SubLObject encoding = NIL;
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        physical_field_indexical = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        logical_schema = current.first();
                                                                        current = current.rest();
                                                                        destructuring_bind_must_consp(current, datum, $list_alt30);
                                                                        encoding = current.first();
                                                                        current = current.rest();
                                                                        if (NIL == current) {
                                                                            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical)) {
                                                                                field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                                            }
                                                                        } else {
                                                                            cdestructuring_bind_error(datum, $list_alt30);
                                                                        }
                                                                    }
                                                                }
                                                                done_var_64 = makeBoolean(NIL == valid_66);
                                                            }
                                                        } 
                                                    }
                                                } finally {
                                                    {
                                                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                        try {
                                                            bind($is_thread_performing_cleanupP$, T);
                                                            if (NIL != final_index_iterator) {
                                                                kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                            }
                                                        } finally {
                                                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return field_encoding_gafs;
        }
    }

    /**
     * Returns all field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is
     * non-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical
     * schema in LOGICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\r\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\r\nschema in LOGICAL-SCHEMATA are considered.\nReturns all field encoding gafs from LOGICAL-FIELD-INDEXICAL.  If LOGICAL-SCHEMATA or PHYSICAL-SCHEMATA is\nnon-null, then only physical field decoding gafs with physical schema in PHYSICAL-SCHEMATA or logical\nschema in LOGICAL-SCHEMATA are considered.")
    public static SubLObject get_field_encoding_gafs_for_logical_field_indexical_internal(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject field_encoding_gafs = NIL;
        if (NIL != logical_schemata) {
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$65 = NIL;
                                    final SubLObject token_var_$66 = NIL;
                                    while (NIL == done_var_$65) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$66);
                                        final SubLObject valid_$67 = makeBoolean(!token_var_$66.eql(assertion));
                                        if (NIL != valid_$67) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject physical_schema = NIL;
                                            SubLObject physical_field_indexical = NIL;
                                            SubLObject logical_schema = NIL;
                                            SubLObject encoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_field_indexical = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            encoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if (((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != list_utilities.member_eqP(logical_schema, logical_schemata))) && (NIL != field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                    field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list29);
                                            }
                                        }
                                        done_var_$65 = makeBoolean(NIL == valid_$67);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$68 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$68, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            } else {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$66 = NIL;
                                    final SubLObject token_var_$67 = NIL;
                                    while (NIL == done_var_$66) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$67);
                                        final SubLObject valid_$68 = makeBoolean(!token_var_$67.eql(assertion));
                                        if (NIL != valid_$68) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject physical_schema = NIL;
                                            SubLObject physical_field_indexical = NIL;
                                            SubLObject logical_schema = NIL;
                                            SubLObject encoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_field_indexical = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            encoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if ((NIL != list_utilities.member_eqP(logical_schema, logical_schemata)) && (NIL != field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                    field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list29);
                                            }
                                        }
                                        done_var_$66 = makeBoolean(NIL == valid_$68);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$69 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values2 = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values2);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$69, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            }
        } else
            if (NIL != physical_schemata) {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$67 = NIL;
                                    final SubLObject token_var_$68 = NIL;
                                    while (NIL == done_var_$67) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$68);
                                        final SubLObject valid_$69 = makeBoolean(!token_var_$68.eql(assertion));
                                        if (NIL != valid_$69) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject physical_schema = NIL;
                                            SubLObject physical_field_indexical = NIL;
                                            SubLObject logical_schema = NIL;
                                            SubLObject encoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_field_indexical = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            encoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if ((NIL != list_utilities.member_eqP(physical_schema, physical_schemata)) && (NIL != field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical))) {
                                                    field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list29);
                                            }
                                        }
                                        done_var_$67 = makeBoolean(NIL == valid_$69);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$70 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values3 = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values3);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$70, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            } else {
                final SubLObject pred_var = $$fieldEncoding;
                if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                    final SubLObject str = NIL;
                    final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                    final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                    final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                    final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                    final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                    final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                    try {
                        $progress_start_time$.bind(get_universal_time(), thread);
                        $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                        $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                        $progress_notification_count$.bind(ZERO_INTEGER, thread);
                        $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                        $progress_count$.bind(ZERO_INTEGER, thread);
                        $is_noting_progressP$.bind(T, thread);
                        $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                        noting_progress_preamble(str);
                        final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                        SubLObject done_var = NIL;
                        final SubLObject token_var = NIL;
                        while (NIL == done_var) {
                            final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                            final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                            if (NIL != valid) {
                                note_progress();
                                SubLObject final_index_iterator = NIL;
                                try {
                                    final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                    SubLObject done_var_$68 = NIL;
                                    final SubLObject token_var_$69 = NIL;
                                    while (NIL == done_var_$68) {
                                        final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$69);
                                        final SubLObject valid_$70 = makeBoolean(!token_var_$69.eql(assertion));
                                        if (NIL != valid_$70) {
                                            SubLObject current;
                                            final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                            SubLObject physical_schema = NIL;
                                            SubLObject physical_field_indexical = NIL;
                                            SubLObject logical_schema = NIL;
                                            SubLObject encoding = NIL;
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            physical_field_indexical = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            logical_schema = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list29);
                                            encoding = current.first();
                                            current = current.rest();
                                            if (NIL == current) {
                                                if (NIL != field_encoding_contains_logical_field_indexicalP(encoding, logical_field_indexical)) {
                                                    field_encoding_gafs = cons(assertion, field_encoding_gafs);
                                                }
                                            } else {
                                                cdestructuring_bind_error(datum, $list29);
                                            }
                                        }
                                        done_var_$68 = makeBoolean(NIL == valid_$70);
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$71 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values4 = getValuesAsVector();
                                        if (NIL != final_index_iterator) {
                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                        }
                                        restoreValuesFromVector(_values4);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$71, thread);
                                    }
                                }
                            }
                            done_var = makeBoolean(NIL == valid);
                        } 
                        noting_progress_postamble();
                    } finally {
                        $silent_progressP$.rebind(_prev_bind_8, thread);
                        $is_noting_progressP$.rebind(_prev_bind_7, thread);
                        $progress_count$.rebind(_prev_bind_6, thread);
                        $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                        $progress_notification_count$.rebind(_prev_bind_4, thread);
                        $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                        $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                        $progress_start_time$.rebind(_prev_bind_0, thread);
                    }
                }
            }

        return field_encoding_gafs;
    }

    public static final SubLObject get_field_encoding_gafs_for_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL, FOUR_INTEGER, $int$100, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_4(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (logical_field_indexical.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (logical_schemata.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (physical_schemata.equal(cached_args.first())) {
                                                cached_args = cached_args.rest();
                                                if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt_info.equal(cached_args.first())) {
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
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(logical_field_indexical, logical_schemata, physical_schemata, mt_info));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject get_field_encoding_gafs_for_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return get_field_encoding_gafs_for_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL, FOUR_INTEGER, $int$100, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_4(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (logical_field_indexical.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (logical_schemata.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (physical_schemata.equal(cached_args.first())) {
                            cached_args = cached_args.rest();
                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt_info.equal(cached_args.first())) {
                                return memoization_state.caching_results(results2);
                            }
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(get_field_encoding_gafs_for_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(logical_field_indexical, logical_schemata, physical_schemata, mt_info));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject get_physical_field_decoding_from_gaf_alt(SubLObject physical_field_decoding_gaf) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_translation_from_gaf(physical_field_decoding_gaf);
    }

    public static SubLObject get_physical_field_decoding_from_gaf(final SubLObject physical_field_decoding_gaf) {
        return get_field_translation_from_gaf(physical_field_decoding_gaf);
    }

    public static final SubLObject destructure_physical_field_decoding_gaf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt31);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject to_physical_schema = NIL;
                    SubLObject physical_field_indexical = NIL;
                    SubLObject from_physical_schema = NIL;
                    SubLObject physical_field_decoding = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt31);
                    to_physical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt31);
                    physical_field_indexical = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt31);
                    from_physical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt31);
                    physical_field_decoding = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject physical_field_decoding_gaf = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt31);
                            physical_field_decoding_gaf = current.first();
                            current = current.rest();
                            {
                                SubLObject body = current;
                                return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_physical_schema, physical_field_indexical, from_physical_schema, physical_field_decoding), physical_field_decoding_gaf, append(body, NIL));
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt31);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject destructure_physical_field_decoding_gaf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject to_physical_schema = NIL;
        SubLObject physical_field_indexical = NIL;
        SubLObject from_physical_schema = NIL;
        SubLObject physical_field_decoding = NIL;
        destructuring_bind_must_consp(current, datum, $list30);
        to_physical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        physical_field_indexical = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        from_physical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        physical_field_decoding = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject physical_field_decoding_gaf = NIL;
            destructuring_bind_must_consp(current, datum, $list30);
            physical_field_decoding_gaf = current.first();
            final SubLObject body;
            current = body = current.rest();
            return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_physical_schema, physical_field_indexical, from_physical_schema, physical_field_decoding), physical_field_decoding_gaf, append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list30);
        return NIL;
    }

    /**
     * Gets the physical field indexicals from PHYSICAL-FIELD-DECODING.
     */
    @LispMethod(comment = "Gets the physical field indexicals from PHYSICAL-FIELD-DECODING.")
    public static final SubLObject physical_field_decoding_physical_field_indexicals_alt(SubLObject physical_field_decoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(physical_field_decoding, PHYSICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gets the physical field indexicals from PHYSICAL-FIELD-DECODING.
     */
    @LispMethod(comment = "Gets the physical field indexicals from PHYSICAL-FIELD-DECODING.")
    public static SubLObject physical_field_decoding_physical_field_indexicals(final SubLObject physical_field_decoding) {
        return field_translation_fields(physical_field_decoding, PHYSICAL_FIELD_INDEXICAL_P);
    }/**
     * Gets the physical field indexicals from PHYSICAL-FIELD-DECODING.
     */


    /**
     * Returns all physical field decoding gafs for PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field decoding gafs for PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field decoding gafs for PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static final SubLObject get_physical_field_decoding_gafs_for_physical_field_alt(SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_decoding_gafs_for_physical_field_indexical(physical_field_indexical, physical_schemata);
        }
    }

    /**
     * Returns all physical field decoding gafs for PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field decoding gafs for PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field decoding gafs for PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static SubLObject get_physical_field_decoding_gafs_for_physical_field(final SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
        return get_physical_field_decoding_gafs_for_physical_field_indexical(physical_field_indexical, physical_schemata);
    }/**
     * Returns all physical field decoding gafs for PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */


    /**
     * This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just
     * the corresponding physical field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\r\nthe corresponding physical field decodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\nthe corresponding physical field decodings (and not the gafs).")
    public static final SubLObject get_physical_field_decodings_for_physical_field_alt(SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_decodings_for_physical_field_indexical(physical_field_indexical, physical_schemata);
        }
    }

    /**
     * This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just
     * the corresponding physical field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\r\nthe corresponding physical field decodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just\nthe corresponding physical field decodings (and not the gafs).")
    public static SubLObject get_physical_field_decodings_for_physical_field(final SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
        return get_physical_field_decodings_for_physical_field_indexical(physical_field_indexical, physical_schemata);
    }/**
     * This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD except that it returns just
     * the corresponding physical field decodings (and not the gafs).
     */


    /**
     * Returns all physical field decoding gafs for PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field decoding gafs for PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field decoding gafs for PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static final SubLObject get_physical_field_decoding_gafs_for_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_decoding_gafs = NIL;
            if (NIL != physical_schemata) {
                {
                    SubLObject pred_var = $$physicalFieldDecoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_67 = NIL;
                                                    SubLObject token_var_68 = NIL;
                                                    while (NIL == done_var_67) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_68);
                                                            SubLObject valid_69 = makeBoolean(token_var_68 != assertion);
                                                            if (NIL != valid_69) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_physical_schema = NIL;
                                                                    SubLObject to_physical_field_indexical = NIL;
                                                                    SubLObject from_physical_schema = NIL;
                                                                    SubLObject decoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    to_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    to_physical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    from_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    decoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (((to_physical_field_indexical == physical_field_indexical) && (NIL != list_utilities.member_eqP(to_physical_schema, physical_schemata))) && (NIL != list_utilities.member_eqP(from_physical_schema, physical_schemata))) {
                                                                            physical_field_decoding_gafs = cons(assertion, physical_field_decoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt33);
                                                                    }
                                                                }
                                                            }
                                                            done_var_67 = makeBoolean(NIL == valid_69);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                {
                    SubLObject pred_var = $$physicalFieldDecoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_70 = NIL;
                                                    SubLObject token_var_71 = NIL;
                                                    while (NIL == done_var_70) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_71);
                                                            SubLObject valid_72 = makeBoolean(token_var_71 != assertion);
                                                            if (NIL != valid_72) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_physical_schema = NIL;
                                                                    SubLObject to_physical_field_indexical = NIL;
                                                                    SubLObject from_physical_schema = NIL;
                                                                    SubLObject decoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    to_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    to_physical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    from_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt33);
                                                                    decoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (to_physical_field_indexical == physical_field_indexical) {
                                                                            physical_field_decoding_gafs = cons(assertion, physical_field_decoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt33);
                                                                    }
                                                                }
                                                            }
                                                            done_var_70 = makeBoolean(NIL == valid_72);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return physical_field_decoding_gafs;
        }
    }

    /**
     * Returns all physical field decoding gafs for PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field decoding gafs for PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field decoding gafs for PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field decoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static SubLObject get_physical_field_decoding_gafs_for_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject physical_field_decoding_gafs = NIL;
        if (NIL != physical_schemata) {
            final SubLObject pred_var = $$physicalFieldDecoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$81 = NIL;
                                final SubLObject token_var_$82 = NIL;
                                while (NIL == done_var_$81) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$82);
                                    final SubLObject valid_$83 = makeBoolean(!token_var_$82.eql(assertion));
                                    if (NIL != valid_$83) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_physical_schema = NIL;
                                        SubLObject to_physical_field_indexical = NIL;
                                        SubLObject from_physical_schema = NIL;
                                        SubLObject decoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        to_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        to_physical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        from_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        decoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if ((to_physical_field_indexical.eql(physical_field_indexical) && (NIL != list_utilities.member_eqP(to_physical_schema, physical_schemata))) && (NIL != list_utilities.member_eqP(from_physical_schema, physical_schemata))) {
                                                physical_field_decoding_gafs = cons(assertion, physical_field_decoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list32);
                                        }
                                    }
                                    done_var_$81 = makeBoolean(NIL == valid_$83);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$84 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$84, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        } else {
            final SubLObject pred_var = $$physicalFieldDecoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$82 = NIL;
                                final SubLObject token_var_$83 = NIL;
                                while (NIL == done_var_$82) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$83);
                                    final SubLObject valid_$84 = makeBoolean(!token_var_$83.eql(assertion));
                                    if (NIL != valid_$84) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_physical_schema = NIL;
                                        SubLObject to_physical_field_indexical = NIL;
                                        SubLObject from_physical_schema = NIL;
                                        SubLObject decoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        to_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        to_physical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        from_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list32);
                                        decoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (to_physical_field_indexical.eql(physical_field_indexical)) {
                                                physical_field_decoding_gafs = cons(assertion, physical_field_decoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list32);
                                        }
                                    }
                                    done_var_$82 = makeBoolean(NIL == valid_$84);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$85 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values2 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values2);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$85, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        }
        return physical_field_decoding_gafs;
    }

    /**
     * This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding physical field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding physical field decodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding physical field decodings (and not the gafs).")
    public static final SubLObject get_physical_field_decodings_for_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_decoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_decoding_gafs_for_physical_field_indexical(physical_field_indexical, physical_schemata);
            SubLObject physical_field_decodings = NIL;
            SubLObject cdolist_list_var = physical_field_decoding_gafs;
            SubLObject physical_field_decoding_gaf = NIL;
            for (physical_field_decoding_gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , physical_field_decoding_gaf = cdolist_list_var.first()) {
                physical_field_decodings = cons(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_decoding_from_gaf(physical_field_decoding_gaf), physical_field_decodings);
            }
            return nreverse(list_utilities.fast_delete_duplicates(physical_field_decodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        }
    }

    /**
     * This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding physical field decodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding physical field decodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding physical field decodings (and not the gafs).")
    public static SubLObject get_physical_field_decodings_for_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_decoding_gafs = get_physical_field_decoding_gafs_for_physical_field_indexical(physical_field_indexical, physical_schemata);
        SubLObject physical_field_decodings = NIL;
        SubLObject cdolist_list_var = physical_field_decoding_gafs;
        SubLObject physical_field_decoding_gaf = NIL;
        physical_field_decoding_gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            physical_field_decodings = cons(get_physical_field_decoding_from_gaf(physical_field_decoding_gaf), physical_field_decodings);
            cdolist_list_var = cdolist_list_var.rest();
            physical_field_decoding_gaf = cdolist_list_var.first();
        } 
        return nreverse(list_utilities.fast_delete_duplicates(physical_field_decodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }/**
     * This behaves just like GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding physical field decodings (and not the gafs).
     */


    public static final SubLObject get_physical_field_encoding_from_gaf_alt(SubLObject physical_field_encoding_gaf) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_translation_from_gaf(physical_field_encoding_gaf);
    }

    public static SubLObject get_physical_field_encoding_from_gaf(final SubLObject physical_field_encoding_gaf) {
        return get_field_translation_from_gaf(physical_field_encoding_gaf);
    }

    public static final SubLObject destructure_physical_field_encoding_gaf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt34);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject to_physical_schema = NIL;
                    SubLObject physical_field_indexical = NIL;
                    SubLObject from_physical_schema = NIL;
                    SubLObject physical_field_encoding = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    to_physical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    physical_field_indexical = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    from_physical_schema = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    physical_field_encoding = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject physical_field_encoding_gaf = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt34);
                            physical_field_encoding_gaf = current.first();
                            current = current.rest();
                            {
                                SubLObject body = current;
                                return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_physical_schema, physical_field_indexical, from_physical_schema, physical_field_encoding), physical_field_encoding_gaf, append(body, NIL));
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt34);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject destructure_physical_field_encoding_gaf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list33);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject to_physical_schema = NIL;
        SubLObject physical_field_indexical = NIL;
        SubLObject from_physical_schema = NIL;
        SubLObject physical_field_encoding = NIL;
        destructuring_bind_must_consp(current, datum, $list33);
        to_physical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list33);
        physical_field_indexical = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list33);
        from_physical_schema = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list33);
        physical_field_encoding = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject physical_field_encoding_gaf = NIL;
            destructuring_bind_must_consp(current, datum, $list33);
            physical_field_encoding_gaf = current.first();
            final SubLObject body;
            current = body = current.rest();
            return listS(DESTRUCTURE_FIELD_TRANSLATION_GAF, list(to_physical_schema, physical_field_indexical, from_physical_schema, physical_field_encoding), physical_field_encoding_gaf, append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list33);
        return NIL;
    }

    /**
     * Gets the physical field indexicals from PHYSICAL-FIELD-ENCODING.
     */
    @LispMethod(comment = "Gets the physical field indexicals from PHYSICAL-FIELD-ENCODING.")
    public static final SubLObject physical_field_encoding_physical_field_indexicals_alt(SubLObject physical_field_encoding) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(physical_field_encoding, PHYSICAL_FIELD_INDEXICAL_P);
    }

    /**
     * Gets the physical field indexicals from PHYSICAL-FIELD-ENCODING.
     */
    @LispMethod(comment = "Gets the physical field indexicals from PHYSICAL-FIELD-ENCODING.")
    public static SubLObject physical_field_encoding_physical_field_indexicals(final SubLObject physical_field_encoding) {
        return field_translation_fields(physical_field_encoding, PHYSICAL_FIELD_INDEXICAL_P);
    }/**
     * Gets the physical field indexicals from PHYSICAL-FIELD-ENCODING.
     */


    public static final SubLObject physical_field_encoding_contains_physical_field_indexicalP_alt(SubLObject physical_field_encoding, SubLObject physical_field_indexical) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(physical_field_indexical, physical_field_encoding, T, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject physical_field_encoding_contains_physical_field_indexicalP(final SubLObject physical_field_encoding, final SubLObject physical_field_indexical) {
        return list_utilities.sublisp_boolean(cycl_utilities.expression_find(physical_field_indexical, physical_field_encoding, T, UNPROVIDED, UNPROVIDED));
    }

    /**
     * Returns all physical field encoding gafs from PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field encoding gafs from PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field encoding gafs from PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static final SubLObject get_physical_field_encoding_gafs_from_physical_field_alt(SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
        }
    }

    /**
     * Returns all physical field encoding gafs from PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field encoding gafs from PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field encoding gafs from PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static SubLObject get_physical_field_encoding_gafs_from_physical_field(final SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
        return get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
    }/**
     * Returns all physical field encoding gafs from PHYSICAL-FIELD.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */


    /**
     * This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD except that it returns just
     * the corresponding physical field encodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD except that it returns just\r\nthe corresponding physical field encodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD except that it returns just\nthe corresponding physical field encodings (and not the gafs).")
    public static final SubLObject get_physical_field_encodings_from_physical_field_alt(SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_encodings_from_physical_field_indexical(physical_field_indexical, physical_schemata);
        }
    }

    /**
     * This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD except that it returns just
     * the corresponding physical field encodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD except that it returns just\r\nthe corresponding physical field encodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD except that it returns just\nthe corresponding physical field encodings (and not the gafs).")
    public static SubLObject get_physical_field_encodings_from_physical_field(final SubLObject physical_field, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
        return get_physical_field_encodings_from_physical_field_indexical(physical_field_indexical, physical_schemata);
    }/**
     * This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD except that it returns just
     * the corresponding physical field encodings (and not the gafs).
     */


    /**
     * Returns all physical field encoding gafs from PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field encoding gafs from PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field encoding gafs from PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static final SubLObject get_physical_field_encoding_gafs_from_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_encoding_gafs = NIL;
            if (NIL != physical_schemata) {
                {
                    SubLObject pred_var = $$physicalFieldEncoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_73 = NIL;
                                                    SubLObject token_var_74 = NIL;
                                                    while (NIL == done_var_73) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_74);
                                                            SubLObject valid_75 = makeBoolean(token_var_74 != assertion);
                                                            if (NIL != valid_75) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_physical_schema = NIL;
                                                                    SubLObject to_physical_field_indexical = NIL;
                                                                    SubLObject from_physical_schema = NIL;
                                                                    SubLObject encoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    to_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    to_physical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    from_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    encoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (((NIL != list_utilities.member_eqP(to_physical_schema, physical_schemata)) && (NIL != list_utilities.member_eqP(from_physical_schema, physical_schemata))) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.physical_field_encoding_contains_physical_field_indexicalP(encoding, physical_field_indexical))) {
                                                                            physical_field_encoding_gafs = cons(assertion, physical_field_encoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt36);
                                                                    }
                                                                }
                                                            }
                                                            done_var_73 = makeBoolean(NIL == valid_75);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            } else {
                {
                    SubLObject pred_var = $$physicalFieldEncoding;
                    if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                        {
                            SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
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
                                                    SubLObject done_var_76 = NIL;
                                                    SubLObject token_var_77 = NIL;
                                                    while (NIL == done_var_76) {
                                                        {
                                                            SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_77);
                                                            SubLObject valid_78 = makeBoolean(token_var_77 != assertion);
                                                            if (NIL != valid_78) {
                                                                {
                                                                    SubLObject datum = assertions_high.gaf_args(assertion);
                                                                    SubLObject current = datum;
                                                                    SubLObject to_physical_schema = NIL;
                                                                    SubLObject to_physical_field_indexical = NIL;
                                                                    SubLObject from_physical_schema = NIL;
                                                                    SubLObject encoding = NIL;
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    to_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    to_physical_field_indexical = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    from_physical_schema = current.first();
                                                                    current = current.rest();
                                                                    destructuring_bind_must_consp(current, datum, $list_alt36);
                                                                    encoding = current.first();
                                                                    current = current.rest();
                                                                    if (NIL == current) {
                                                                        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.physical_field_encoding_contains_physical_field_indexicalP(encoding, physical_field_indexical)) {
                                                                            physical_field_encoding_gafs = cons(assertion, physical_field_encoding_gafs);
                                                                        }
                                                                    } else {
                                                                        cdestructuring_bind_error(datum, $list_alt36);
                                                                    }
                                                                }
                                                            }
                                                            done_var_76 = makeBoolean(NIL == valid_78);
                                                        }
                                                    } 
                                                }
                                            } finally {
                                                {
                                                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                                                    try {
                                                        bind($is_thread_performing_cleanupP$, T);
                                                        if (NIL != final_index_iterator) {
                                                            kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                        }
                                                    } finally {
                                                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
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
            return physical_field_encoding_gafs;
        }
    }

    /**
     * Returns all physical field encoding gafs from PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then
     * only physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.
     */
    @LispMethod(comment = "Returns all physical field encoding gafs from PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\r\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.\nReturns all physical field encoding gafs from PHYSICAL-FIELD-INDEXICAL.  If PHYSICAL-SCHEMATA is non-null, then\nonly physical field encoding gafs both physical schemata of which are in PHYSICAL-SCHEMATA are considered.")
    public static SubLObject get_physical_field_encoding_gafs_from_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject physical_field_encoding_gafs = NIL;
        if (NIL != physical_schemata) {
            final SubLObject pred_var = $$physicalFieldEncoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$89 = NIL;
                                final SubLObject token_var_$90 = NIL;
                                while (NIL == done_var_$89) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$90);
                                    final SubLObject valid_$91 = makeBoolean(!token_var_$90.eql(assertion));
                                    if (NIL != valid_$91) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_physical_schema = NIL;
                                        SubLObject to_physical_field_indexical = NIL;
                                        SubLObject from_physical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        to_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        to_physical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        from_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (((NIL != list_utilities.member_eqP(to_physical_schema, physical_schemata)) && (NIL != list_utilities.member_eqP(from_physical_schema, physical_schemata))) && (NIL != physical_field_encoding_contains_physical_field_indexicalP(encoding, physical_field_indexical))) {
                                                physical_field_encoding_gafs = cons(assertion, physical_field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list35);
                                        }
                                    }
                                    done_var_$89 = makeBoolean(NIL == valid_$91);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$92 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$92, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        } else {
            final SubLObject pred_var = $$physicalFieldEncoding;
            if (NIL != kb_mapping_macros.do_predicate_extent_index_key_validator(pred_var)) {
                final SubLObject str = NIL;
                final SubLObject _prev_bind_0 = $progress_start_time$.currentBinding(thread);
                final SubLObject _prev_bind_2 = $progress_last_pacification_time$.currentBinding(thread);
                final SubLObject _prev_bind_3 = $progress_elapsed_seconds_for_notification$.currentBinding(thread);
                final SubLObject _prev_bind_4 = $progress_notification_count$.currentBinding(thread);
                final SubLObject _prev_bind_5 = $progress_pacifications_since_last_nl$.currentBinding(thread);
                final SubLObject _prev_bind_6 = $progress_count$.currentBinding(thread);
                final SubLObject _prev_bind_7 = $is_noting_progressP$.currentBinding(thread);
                final SubLObject _prev_bind_8 = $silent_progressP$.currentBinding(thread);
                try {
                    $progress_start_time$.bind(get_universal_time(), thread);
                    $progress_last_pacification_time$.bind($progress_start_time$.getDynamicValue(thread), thread);
                    $progress_elapsed_seconds_for_notification$.bind($suppress_all_progress_faster_than_seconds$.getDynamicValue(thread), thread);
                    $progress_notification_count$.bind(ZERO_INTEGER, thread);
                    $progress_pacifications_since_last_nl$.bind(ZERO_INTEGER, thread);
                    $progress_count$.bind(ZERO_INTEGER, thread);
                    $is_noting_progressP$.bind(T, thread);
                    $silent_progressP$.bind(NIL != str ? $silent_progressP$.getDynamicValue(thread) : T, thread);
                    noting_progress_preamble(str);
                    final SubLObject iterator_var = kb_mapping_macros.new_predicate_extent_final_index_spec_iterator(pred_var);
                    SubLObject done_var = NIL;
                    final SubLObject token_var = NIL;
                    while (NIL == done_var) {
                        final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                        final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                        if (NIL != valid) {
                            note_progress();
                            SubLObject final_index_iterator = NIL;
                            try {
                                final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, $TRUE, NIL);
                                SubLObject done_var_$90 = NIL;
                                final SubLObject token_var_$91 = NIL;
                                while (NIL == done_var_$90) {
                                    final SubLObject assertion = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$91);
                                    final SubLObject valid_$92 = makeBoolean(!token_var_$91.eql(assertion));
                                    if (NIL != valid_$92) {
                                        SubLObject current;
                                        final SubLObject datum = current = assertions_high.gaf_args(assertion);
                                        SubLObject to_physical_schema = NIL;
                                        SubLObject to_physical_field_indexical = NIL;
                                        SubLObject from_physical_schema = NIL;
                                        SubLObject encoding = NIL;
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        to_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        to_physical_field_indexical = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        from_physical_schema = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list35);
                                        encoding = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            if (NIL != physical_field_encoding_contains_physical_field_indexicalP(encoding, physical_field_indexical)) {
                                                physical_field_encoding_gafs = cons(assertion, physical_field_encoding_gafs);
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list35);
                                        }
                                    }
                                    done_var_$90 = makeBoolean(NIL == valid_$92);
                                } 
                            } finally {
                                final SubLObject _prev_bind_0_$93 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    final SubLObject _values2 = getValuesAsVector();
                                    if (NIL != final_index_iterator) {
                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                    }
                                    restoreValuesFromVector(_values2);
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$93, thread);
                                }
                            }
                        }
                        done_var = makeBoolean(NIL == valid);
                    } 
                    noting_progress_postamble();
                } finally {
                    $silent_progressP$.rebind(_prev_bind_8, thread);
                    $is_noting_progressP$.rebind(_prev_bind_7, thread);
                    $progress_count$.rebind(_prev_bind_6, thread);
                    $progress_pacifications_since_last_nl$.rebind(_prev_bind_5, thread);
                    $progress_notification_count$.rebind(_prev_bind_4, thread);
                    $progress_elapsed_seconds_for_notification$.rebind(_prev_bind_3, thread);
                    $progress_last_pacification_time$.rebind(_prev_bind_2, thread);
                    $progress_start_time$.rebind(_prev_bind_0, thread);
                }
            }
        }
        return physical_field_encoding_gafs;
    }

    /**
     * This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding physical field encodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding physical field encodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding physical field encodings (and not the gafs).")
    public static final SubLObject get_physical_field_encodings_from_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            SubLObject physical_field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
            SubLObject physical_field_encodings = NIL;
            SubLObject cdolist_list_var = physical_field_encoding_gafs;
            SubLObject physical_field_encoding_gaf = NIL;
            for (physical_field_encoding_gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , physical_field_encoding_gaf = cdolist_list_var.first()) {
                physical_field_encodings = cons(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_encoding_from_gaf(physical_field_encoding_gaf), physical_field_encodings);
            }
            return nreverse(list_utilities.fast_delete_duplicates(physical_field_encodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        }
    }

    /**
     * This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding physical field encodings (and not the gafs).
     */
    @LispMethod(comment = "This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\r\nthe corresponding physical field encodings (and not the gafs).\nThis behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just\nthe corresponding physical field encodings (and not the gafs).")
    public static SubLObject get_physical_field_encodings_from_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject physical_schemata) {
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLObject physical_field_encoding_gafs = get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
        SubLObject physical_field_encodings = NIL;
        SubLObject cdolist_list_var = physical_field_encoding_gafs;
        SubLObject physical_field_encoding_gaf = NIL;
        physical_field_encoding_gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            physical_field_encodings = cons(get_physical_field_encoding_from_gaf(physical_field_encoding_gaf), physical_field_encodings);
            cdolist_list_var = cdolist_list_var.rest();
            physical_field_encoding_gaf = cdolist_list_var.first();
        } 
        return nreverse(list_utilities.fast_delete_duplicates(physical_field_encodings, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }/**
     * This behaves just like GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL except that it returns just
     * the corresponding physical field encodings (and not the gafs).
     */


    /**
     * This breaks down any NARTs in ENCODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in ENCODING except for the logical fields.")
    public static final SubLObject sksi_reconstruct_encoding_alt(SubLObject encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(encoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in ENCODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in ENCODING except for the logical fields.")
    public static SubLObject sksi_reconstruct_encoding(final SubLObject encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(encoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in ENCODING except for the logical fields.
     */


    /**
     * This breaks down any NARTs in DECODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in DECODING except for the physical field indexicals.")
    public static final SubLObject sksi_reconstruct_decoding_alt(SubLObject decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(decoding, PHYSICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in DECODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in DECODING except for the physical field indexicals.")
    public static SubLObject sksi_reconstruct_decoding(final SubLObject decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(decoding, PHYSICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in DECODING except for the physical field indexicals.
     */


    /**
     * This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the logical fields.")
    public static final SubLObject sksi_reconstruct_logical_field_encoding_alt(SubLObject logical_field_encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(logical_field_encoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the logical fields.")
    public static SubLObject sksi_reconstruct_logical_field_encoding(final SubLObject logical_field_encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(logical_field_encoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the logical fields.
     */


    /**
     * This breaks down any NARTs in ENCODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in ENCODING except for the logical fields.")
    public static final SubLObject sksi_reconstruct_field_encoding_alt(SubLObject encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(encoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in ENCODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in ENCODING except for the logical fields.")
    public static SubLObject sksi_reconstruct_field_encoding(final SubLObject encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(encoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in ENCODING except for the logical fields.
     */


    /**
     * This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the physical field indexicals.")
    public static final SubLObject sksi_reconstruct_physical_field_encoding_alt(SubLObject physical_field_encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(physical_field_encoding, PHYSICAL_FIELD_INDEXICAL_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the physical field indexicals.")
    public static SubLObject sksi_reconstruct_physical_field_encoding(final SubLObject physical_field_encoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(physical_field_encoding, PHYSICAL_FIELD_INDEXICAL_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in LOGICAL-FIELD-ENCODING except for the physical field indexicals.
     */


    /**
     * This breaks down any NARTs in LOGICAL-FIELD-DECODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in LOGICAL-FIELD-DECODING except for the logical fields.")
    public static final SubLObject sksi_reconstruct_logical_field_decoding_alt(SubLObject logical_field_decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(logical_field_decoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in LOGICAL-FIELD-DECODING except for the logical fields.
     */
    @LispMethod(comment = "This breaks down any NARTs in LOGICAL-FIELD-DECODING except for the logical fields.")
    public static SubLObject sksi_reconstruct_logical_field_decoding(final SubLObject logical_field_decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(logical_field_decoding, LOGICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in LOGICAL-FIELD-DECODING except for the logical fields.
     */


    /**
     * This breaks down any NARTs in DECODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in DECODING except for the physical field indexicals.")
    public static final SubLObject sksi_reconstruct_field_decoding_alt(SubLObject decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(decoding, PHYSICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in DECODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in DECODING except for the physical field indexicals.")
    public static SubLObject sksi_reconstruct_field_decoding(final SubLObject decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(decoding, PHYSICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in DECODING except for the physical field indexicals.
     */


    /**
     * This breaks down any NARTs in PHYSICAL-FIELD-DECODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in PHYSICAL-FIELD-DECODING except for the physical field indexicals.")
    public static final SubLObject sksi_reconstruct_physical_field_decoding_alt(SubLObject physical_field_decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(physical_field_decoding, PHYSICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }

    /**
     * This breaks down any NARTs in PHYSICAL-FIELD-DECODING except for the physical field indexicals.
     */
    @LispMethod(comment = "This breaks down any NARTs in PHYSICAL-FIELD-DECODING except for the physical field indexicals.")
    public static SubLObject sksi_reconstruct_physical_field_decoding(final SubLObject physical_field_decoding, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_reconstruct_translation(physical_field_decoding, PHYSICAL_FIELD_INDEXICAL_OR_REIFIED_MAPPING_EL_FORMULA_P, memoizeP);
    }/**
     * This breaks down any NARTs in PHYSICAL-FIELD-DECODING except for the physical field indexicals.
     */


    /**
     * This breaks down any NARTs in TRANSLATION except those picked out by TEST.
     */
    @LispMethod(comment = "This breaks down any NARTs in TRANSLATION except those picked out by TEST.")
    public static final SubLObject sksi_reconstruct_translation_alt(SubLObject translation, SubLObject test, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return sksi_infrastructure_utilities.sksi_reconstruct_expression(translation, test, memoizeP);
    }

    /**
     * This breaks down any NARTs in TRANSLATION except those picked out by TEST.
     */
    @LispMethod(comment = "This breaks down any NARTs in TRANSLATION except those picked out by TEST.")
    public static SubLObject sksi_reconstruct_translation(final SubLObject translation, final SubLObject test, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_kb_accessors.schema_object_fn_expression_p(translation)) {
            thread.resetMultipleValues();
            final SubLObject schema_object_fn = unmake_binary_formula(translation);
            final SubLObject source = thread.secondMultipleValue();
            final SubLObject descriptor = thread.thirdMultipleValue();
            thread.resetMultipleValues();
            final SubLObject translated_descriptor = sksi_infrastructure_utilities.sksi_reconstruct_expression(descriptor, test, memoizeP);
            return make_binary_formula(schema_object_fn, source, translated_descriptor);
        }
        return sksi_infrastructure_utilities.sksi_reconstruct_expression(translation, test, memoizeP);
    }/**
     * This breaks down any NARTs in TRANSLATION except those picked out by TEST.
     */


    /**
     * This generates compositions of decodings from a logical field indexical all the way down.
     */
    @LispMethod(comment = "This generates compositions of decodings from a logical field indexical all the way down.")
    public static final SubLObject generate_decoding_compositions_for_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject logical_field_indexical_fort = sksi_kb_accessors.lfi_fort_for_lfi(logical_field_indexical);
                SubLObject translations = NIL;
                SubLObject result = NIL;
                SubLObject error_message = NIL;
                SubLObject result_79 = NIL;
                try {
                    {
                        SubLObject result_80 = NIL;
                        try {
                            translations = list_utilities.fast_delete_duplicates(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_decoding_compositions_for_logical_field_indexical_recursive(logical_field_indexical_fort, logical_schemata, physical_schemata, NIL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        } catch (Throwable ccatch_env_var) {
                            result_80 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
                        }
                        if (NIL != result_80) {
                            error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_80);
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    result_79 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_DECODINGS);
                }
                if (NIL != result_79) {
                    error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result_79);
                }
                if (NIL != error_message) {
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(THREE_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt42$SKSI_, new SubLObject[]{ error_message, $str_alt43$__ }));
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                }
                if (NIL != sksi_kb_accessors.lfi_alias_fn_naut_p(logical_field_indexical)) {
                    {
                        SubLObject cdolist_list_var = translations;
                        SubLObject translation = NIL;
                        for (translation = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , translation = cdolist_list_var.first()) {
                            {
                                SubLObject translation_pfis = cycl_utilities.expression_gather(translation, PHYSICAL_FIELD_INDEXICAL_P, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                SubLObject translation_copy = copy_expression(translation);
                                SubLObject cdolist_list_var_81 = translation_pfis;
                                SubLObject pfi = NIL;
                                for (pfi = cdolist_list_var_81.first(); NIL != cdolist_list_var_81; cdolist_list_var_81 = cdolist_list_var_81.rest() , pfi = cdolist_list_var_81.first()) {
                                    translation_copy = cycl_utilities.expression_nsubst(sksi_kb_accessors.pfi_for_lfi(pfi, logical_field_indexical), pfi, translation_copy, UNPROVIDED, UNPROVIDED);
                                }
                                result = cons(translation_copy, result);
                            }
                        }
                        result = nreverse(result);
                    }
                } else {
                    result = translations;
                }
                return result;
            }
        }
    }

    /**
     * This generates compositions of decodings from a logical field indexical all the way down.
     */
    @LispMethod(comment = "This generates compositions of decodings from a logical field indexical all the way down.")
    public static SubLObject generate_decoding_compositions_for_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject logical_field_indexical_fort = sksi_kb_accessors.lfi_fort_for_lfi(logical_field_indexical);
        SubLObject translations = NIL;
        SubLObject result = NIL;
        SubLObject error_message = NIL;
        SubLObject result_$97 = NIL;
        try {
            thread.throwStack.push($SKSI_MISSING_DECODINGS);
            SubLObject result_$98 = NIL;
            try {
                thread.throwStack.push($SKSI_MISSING_ENCODINGS);
                translations = list_utilities.fast_delete_duplicates(generate_decoding_compositions_for_logical_field_indexical_recursive(logical_field_indexical_fort, logical_schemata, physical_schemata, NIL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            } catch (final Throwable ccatch_env_var) {
                result_$98 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
            } finally {
                thread.throwStack.pop();
            }
            if (NIL != result_$98) {
                error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_$98);
            }
        } catch (final Throwable ccatch_env_var2) {
            result_$97 = Errors.handleThrowable(ccatch_env_var2, $SKSI_MISSING_DECODINGS);
        } finally {
            thread.throwStack.pop();
        }
        if (NIL != result_$97) {
            error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result_$97);
        }
        if ((NIL != error_message) && (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(THREE_INTEGER))) {
            format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str41$__SKSI__A_, error_message), THREE_INTEGER);
            force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
        }
        if (NIL != sksi_kb_accessors.lfi_alias_fn_naut_p(logical_field_indexical)) {
            SubLObject cdolist_list_var = translations;
            SubLObject translation = NIL;
            translation = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject translation_pfis = cycl_utilities.expression_gather(translation, PHYSICAL_FIELD_INDEXICAL_P, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                SubLObject translation_copy = copy_expression(translation);
                SubLObject cdolist_list_var_$99 = translation_pfis;
                SubLObject pfi = NIL;
                pfi = cdolist_list_var_$99.first();
                while (NIL != cdolist_list_var_$99) {
                    translation_copy = cycl_utilities.expression_nsubst(sksi_kb_accessors.pfi_for_lfi(pfi, logical_field_indexical), pfi, translation_copy, UNPROVIDED, UNPROVIDED);
                    cdolist_list_var_$99 = cdolist_list_var_$99.rest();
                    pfi = cdolist_list_var_$99.first();
                } 
                result = cons(translation_copy, result);
                cdolist_list_var = cdolist_list_var.rest();
                translation = cdolist_list_var.first();
            } 
            result = nreverse(result);
        } else {
            result = translations;
        }
        return result;
    }/**
     * This generates compositions of decodings from a logical field indexical all the way down.
     */


    public static final SubLObject generate_decoding_compositions_for_logical_field_indexical_recursive_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject used_gafs) {
        {
            SubLObject logical_field_decoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata);
            SubLObject field_decoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata, physical_schemata);
            SubLObject translations = NIL;
            {
                SubLObject cdolist_list_var = logical_field_decoding_gafs;
                SubLObject gaf = NIL;
                for (gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , gaf = cdolist_list_var.first()) {
                    if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                        Errors.warn($str_alt44$Decoding_loop___a_, used_gafs);
                    } else {
                        {
                            SubLObject logical_field_decoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_decoding_from_gaf(gaf);
                            SubLObject decoding_logical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.logical_field_decoding_logical_field_indexicals(logical_field_decoding);
                            SubLObject decoding_list = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_logical_field_decoding(logical_field_decoding, UNPROVIDED);
                            SubLObject new_used_gafs = cons(gaf, used_gafs);
                            SubLObject multi_alist = NIL;
                            SubLObject error_message = NIL;
                            SubLObject result = NIL;
                            try {
                                {
                                    SubLObject result_82 = NIL;
                                    try {
                                        {
                                            SubLObject cdolist_list_var_83 = decoding_logical_field_indexicals;
                                            SubLObject decoding_logical_field_indexical = NIL;
                                            for (decoding_logical_field_indexical = cdolist_list_var_83.first(); NIL != cdolist_list_var_83; cdolist_list_var_83 = cdolist_list_var_83.rest() , decoding_logical_field_indexical = cdolist_list_var_83.first()) {
                                                {
                                                    SubLObject sub_translations = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_decoding_compositions_for_logical_field_indexical_recursive(decoding_logical_field_indexical, logical_schemata, physical_schemata, new_used_gafs);
                                                    if (NIL != sub_translations) {
                                                        multi_alist = cons(cons(decoding_logical_field_indexical, sub_translations), multi_alist);
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Throwable ccatch_env_var) {
                                        result_82 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
                                    }
                                    if (NIL != result_82) {
                                        error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_82);
                                    }
                                }
                            } catch (Throwable ccatch_env_var) {
                                result = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_DECODINGS);
                            }
                            if (NIL != result) {
                                error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result);
                            }
                            if (NIL == error_message) {
                                translations = append(list_utilities.multi_sublis(multi_alist, decoding_list, symbol_function(EQ), UNPROVIDED), translations);
                            }
                        }
                    }
                }
            }
            {
                SubLObject cdolist_list_var = field_decoding_gafs;
                SubLObject gaf = NIL;
                for (gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , gaf = cdolist_list_var.first()) {
                    if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                        Errors.warn($str_alt44$Decoding_loop___a_, used_gafs);
                    } else {
                        {
                            SubLObject field_decoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_decoding_from_gaf(gaf);
                            SubLObject decoding_physical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_decoding_physical_field_indexicals(field_decoding);
                            SubLObject decoding_list = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_field_decoding(field_decoding, UNPROVIDED);
                            SubLObject new_used_gafs = cons(gaf, used_gafs);
                            SubLObject multi_alist = NIL;
                            SubLObject cdolist_list_var_84 = decoding_physical_field_indexicals;
                            SubLObject decoding_physical_field_indexical = NIL;
                            for (decoding_physical_field_indexical = cdolist_list_var_84.first(); NIL != cdolist_list_var_84; cdolist_list_var_84 = cdolist_list_var_84.rest() , decoding_physical_field_indexical = cdolist_list_var_84.first()) {
                                {
                                    SubLObject sub_translations = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_decoding_compositions_for_physical_field_indexical_recursive(decoding_physical_field_indexical, physical_schemata, new_used_gafs);
                                    if (NIL != sub_translations) {
                                        multi_alist = cons(cons(decoding_physical_field_indexical, sub_translations), multi_alist);
                                    }
                                }
                            }
                            translations = append(list_utilities.multi_sublis(multi_alist, decoding_list, symbol_function(EQ), UNPROVIDED), translations);
                        }
                    }
                }
            }
            if (NIL == translations) {
                sublisp_throw($SKSI_MISSING_DECODINGS, logical_field_indexical);
            }
            return translations;
        }
    }

    public static SubLObject generate_decoding_compositions_for_logical_field_indexical_recursive(final SubLObject logical_field_indexical, final SubLObject logical_schemata, final SubLObject physical_schemata, final SubLObject used_gafs) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject logical_field_decoding_gafs = get_logical_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata);
        final SubLObject field_decoding_gafs = get_field_decoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata, physical_schemata);
        SubLObject translations = NIL;
        SubLObject cdolist_list_var = logical_field_decoding_gafs;
        SubLObject gaf = NIL;
        gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                Errors.warn($str42$Decoding_loop___a_, used_gafs);
            } else {
                final SubLObject logical_field_decoding = get_logical_field_decoding_from_gaf(gaf);
                final SubLObject decoding_logical_field_indexicals = logical_field_decoding_logical_field_indexicals(logical_field_decoding);
                final SubLObject decoding_list = sksi_reconstruct_logical_field_decoding(logical_field_decoding, UNPROVIDED);
                final SubLObject new_used_gafs = cons(gaf, used_gafs);
                SubLObject multi_alist = NIL;
                SubLObject error_message = NIL;
                SubLObject result = NIL;
                try {
                    thread.throwStack.push($SKSI_MISSING_DECODINGS);
                    SubLObject result_$100 = NIL;
                    try {
                        thread.throwStack.push($SKSI_MISSING_ENCODINGS);
                        SubLObject cdolist_list_var_$101 = decoding_logical_field_indexicals;
                        SubLObject decoding_logical_field_indexical = NIL;
                        decoding_logical_field_indexical = cdolist_list_var_$101.first();
                        while (NIL != cdolist_list_var_$101) {
                            final SubLObject sub_translations = generate_decoding_compositions_for_logical_field_indexical_recursive(decoding_logical_field_indexical, logical_schemata, physical_schemata, new_used_gafs);
                            if (NIL != sub_translations) {
                                multi_alist = cons(cons(decoding_logical_field_indexical, sub_translations), multi_alist);
                            }
                            cdolist_list_var_$101 = cdolist_list_var_$101.rest();
                            decoding_logical_field_indexical = cdolist_list_var_$101.first();
                        } 
                    } catch (final Throwable ccatch_env_var) {
                        result_$100 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
                    } finally {
                        thread.throwStack.pop();
                    }
                    if (NIL != result_$100) {
                        error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_$100);
                    }
                } catch (final Throwable ccatch_env_var2) {
                    result = Errors.handleThrowable(ccatch_env_var2, $SKSI_MISSING_DECODINGS);
                } finally {
                    thread.throwStack.pop();
                }
                if (NIL != result) {
                    error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result);
                }
                if (NIL == error_message) {
                    translations = append(list_utilities.multi_sublis(multi_alist, decoding_list, symbol_function(EQ), UNPROVIDED), translations);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            gaf = cdolist_list_var.first();
        } 
        cdolist_list_var = field_decoding_gafs;
        gaf = NIL;
        gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                Errors.warn($str42$Decoding_loop___a_, used_gafs);
            } else {
                final SubLObject field_decoding = get_field_decoding_from_gaf(gaf);
                final SubLObject decoding_physical_field_indexicals = field_decoding_physical_field_indexicals(field_decoding);
                final SubLObject decoding_list = sksi_reconstruct_field_decoding(field_decoding, UNPROVIDED);
                final SubLObject new_used_gafs = cons(gaf, used_gafs);
                SubLObject multi_alist = NIL;
                SubLObject cdolist_list_var_$102 = decoding_physical_field_indexicals;
                SubLObject decoding_physical_field_indexical = NIL;
                decoding_physical_field_indexical = cdolist_list_var_$102.first();
                while (NIL != cdolist_list_var_$102) {
                    final SubLObject sub_translations2 = generate_decoding_compositions_for_physical_field_indexical_recursive(decoding_physical_field_indexical, physical_schemata, new_used_gafs);
                    if (NIL != sub_translations2) {
                        multi_alist = cons(cons(decoding_physical_field_indexical, sub_translations2), multi_alist);
                    }
                    cdolist_list_var_$102 = cdolist_list_var_$102.rest();
                    decoding_physical_field_indexical = cdolist_list_var_$102.first();
                } 
                translations = append(list_utilities.multi_sublis(multi_alist, decoding_list, symbol_function(EQ), UNPROVIDED), translations);
            }
            cdolist_list_var = cdolist_list_var.rest();
            gaf = cdolist_list_var.first();
        } 
        if (NIL == translations) {
            sublisp_throw($SKSI_MISSING_DECODINGS, logical_field_indexical);
        }
        return translations;
    }

    public static final SubLObject generate_decoding_compositions_for_physical_field_indexical_recursive_alt(SubLObject physical_field_indexical, SubLObject physical_schemata, SubLObject used_gafs) {
        {
            SubLObject physical_field_decoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_decoding_gafs_for_physical_field_indexical(physical_field_indexical, physical_schemata);
            SubLObject translations = NIL;
            SubLObject cdolist_list_var = physical_field_decoding_gafs;
            SubLObject gaf = NIL;
            for (gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , gaf = cdolist_list_var.first()) {
                if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                    Errors.warn($str_alt44$Decoding_loop___a_, used_gafs);
                } else {
                    {
                        SubLObject physical_field_decoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_decoding_from_gaf(gaf);
                        SubLObject decoding_physical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.physical_field_decoding_physical_field_indexicals(physical_field_decoding);
                        SubLObject decoding_list = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_physical_field_decoding(physical_field_decoding, UNPROVIDED);
                        SubLObject new_used_gafs = cons(gaf, used_gafs);
                        SubLObject multi_alist = NIL;
                        SubLObject cdolist_list_var_85 = decoding_physical_field_indexicals;
                        SubLObject decoding_physical_field_indexical = NIL;
                        for (decoding_physical_field_indexical = cdolist_list_var_85.first(); NIL != cdolist_list_var_85; cdolist_list_var_85 = cdolist_list_var_85.rest() , decoding_physical_field_indexical = cdolist_list_var_85.first()) {
                            {
                                SubLObject sub_translations = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_decoding_compositions_for_physical_field_indexical_recursive(decoding_physical_field_indexical, physical_schemata, new_used_gafs);
                                if (NIL != sub_translations) {
                                    multi_alist = cons(cons(decoding_physical_field_indexical, sub_translations), multi_alist);
                                }
                            }
                        }
                        translations = append(list_utilities.multi_sublis(multi_alist, decoding_list, symbol_function(EQ), UNPROVIDED), translations);
                    }
                }
            }
            return translations;
        }
    }

    public static SubLObject generate_decoding_compositions_for_physical_field_indexical_recursive(final SubLObject physical_field_indexical, final SubLObject physical_schemata, final SubLObject used_gafs) {
        final SubLObject physical_field_decoding_gafs = get_physical_field_decoding_gafs_for_physical_field_indexical(physical_field_indexical, physical_schemata);
        SubLObject translations = NIL;
        SubLObject cdolist_list_var = physical_field_decoding_gafs;
        SubLObject gaf = NIL;
        gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                Errors.warn($str42$Decoding_loop___a_, used_gafs);
            } else {
                final SubLObject physical_field_decoding = get_physical_field_decoding_from_gaf(gaf);
                final SubLObject decoding_physical_field_indexicals = physical_field_decoding_physical_field_indexicals(physical_field_decoding);
                final SubLObject decoding_list = sksi_reconstruct_physical_field_decoding(physical_field_decoding, UNPROVIDED);
                final SubLObject new_used_gafs = cons(gaf, used_gafs);
                SubLObject multi_alist = NIL;
                SubLObject cdolist_list_var_$103 = decoding_physical_field_indexicals;
                SubLObject decoding_physical_field_indexical = NIL;
                decoding_physical_field_indexical = cdolist_list_var_$103.first();
                while (NIL != cdolist_list_var_$103) {
                    final SubLObject sub_translations = generate_decoding_compositions_for_physical_field_indexical_recursive(decoding_physical_field_indexical, physical_schemata, new_used_gafs);
                    if (NIL != sub_translations) {
                        multi_alist = cons(cons(decoding_physical_field_indexical, sub_translations), multi_alist);
                    }
                    cdolist_list_var_$103 = cdolist_list_var_$103.rest();
                    decoding_physical_field_indexical = cdolist_list_var_$103.first();
                } 
                translations = append(list_utilities.multi_sublis(multi_alist, decoding_list, symbol_function(EQ), UNPROVIDED), translations);
            }
            cdolist_list_var = cdolist_list_var.rest();
            gaf = cdolist_list_var.first();
        } 
        return translations;
    }

    /**
     * This generates compositions of decodings from a physical field indexical all the way up.
     */
    @LispMethod(comment = "This generates compositions of decodings from a physical field indexical all the way up.")
    public static final SubLObject generate_encoding_compositions_for_physical_field_indexical_internal_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject translations = NIL;
                SubLObject error_message = NIL;
                SubLObject result = NIL;
                try {
                    {
                        SubLObject result_86 = NIL;
                        try {
                            translations = list_utilities.fast_delete_duplicates(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_for_physical_field_indexical_recursive(physical_field_indexical, logical_schemata, physical_schemata, NIL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        } catch (Throwable ccatch_env_var) {
                            result_86 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
                        }
                        if (NIL != result_86) {
                            error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_86);
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    result = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_DECODINGS);
                }
                if (NIL != result) {
                    error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result);
                }
                if (NIL != error_message) {
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(THREE_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt42$SKSI_, new SubLObject[]{ error_message, $str_alt43$__ }));
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                }
                return translations;
            }
        }
    }

    /**
     * This generates compositions of decodings from a physical field indexical all the way up.
     */
    @LispMethod(comment = "This generates compositions of decodings from a physical field indexical all the way up.")
    public static SubLObject generate_encoding_compositions_for_physical_field_indexical_internal(final SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject translations = NIL;
        SubLObject error_message = NIL;
        SubLObject result = NIL;
        try {
            thread.throwStack.push($SKSI_MISSING_DECODINGS);
            SubLObject result_$104 = NIL;
            try {
                thread.throwStack.push($SKSI_MISSING_ENCODINGS);
                translations = list_utilities.fast_delete_duplicates(generate_encoding_compositions_for_physical_field_indexical_recursive(physical_field_indexical, logical_schemata, physical_schemata, NIL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            } catch (final Throwable ccatch_env_var) {
                result_$104 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
            } finally {
                thread.throwStack.pop();
            }
            if (NIL != result_$104) {
                error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_$104);
            }
        } catch (final Throwable ccatch_env_var2) {
            result = Errors.handleThrowable(ccatch_env_var2, $SKSI_MISSING_DECODINGS);
        } finally {
            thread.throwStack.pop();
        }
        if (NIL != result) {
            error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result);
        }
        if ((NIL != error_message) && (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(THREE_INTEGER))) {
            format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str41$__SKSI__A_, error_message), THREE_INTEGER);
            force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
        }
        return translations;
    }/**
     * This generates compositions of decodings from a physical field indexical all the way up.
     */


    public static final SubLObject generate_encoding_compositions_for_physical_field_indexical_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata, mt_info);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_4(physical_field_indexical, logical_schemata, physical_schemata, mt_info);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (physical_field_indexical.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (logical_schemata.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (physical_schemata.equal(cached_args.first())) {
                                                cached_args = cached_args.rest();
                                                if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt_info.equal(cached_args.first())) {
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
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata, mt_info)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(physical_field_indexical, logical_schemata, physical_schemata, mt_info));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject generate_encoding_compositions_for_physical_field_indexical(final SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return generate_encoding_compositions_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata, mt_info);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_4(physical_field_indexical, logical_schemata, physical_schemata, mt_info);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (physical_field_indexical.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (logical_schemata.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (physical_schemata.equal(cached_args.first())) {
                            cached_args = cached_args.rest();
                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt_info.equal(cached_args.first())) {
                                return memoization_state.caching_results(results2);
                            }
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(generate_encoding_compositions_for_physical_field_indexical_internal(physical_field_indexical, logical_schemata, physical_schemata, mt_info)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(physical_field_indexical, logical_schemata, physical_schemata, mt_info));
        return memoization_state.caching_results(results3);
    }

    public static final SubLObject generate_encoding_compositions_for_physical_field_indexical_recursive_alt(SubLObject physical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject used_gafs) {
        {
            SubLObject physical_field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
            SubLObject field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
            SubLObject translations = NIL;
            {
                SubLObject cdolist_list_var = physical_field_encoding_gafs;
                SubLObject gaf = NIL;
                for (gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , gaf = cdolist_list_var.first()) {
                    if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                        Errors.warn($str_alt46$Encoding_loop___a_, used_gafs);
                    } else {
                        {
                            SubLObject physical_field_encoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_encoding_from_gaf(gaf);
                            SubLObject encoding_physical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.physical_field_encoding_physical_field_indexicals(physical_field_encoding);
                            SubLObject encoding_list = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_physical_field_encoding(physical_field_encoding, UNPROVIDED);
                            SubLObject new_used_gafs = cons(gaf, used_gafs);
                            SubLObject multi_alist = NIL;
                            SubLObject error_message = NIL;
                            SubLObject result = NIL;
                            try {
                                {
                                    SubLObject result_87 = NIL;
                                    try {
                                        {
                                            SubLObject cdolist_list_var_88 = encoding_physical_field_indexicals;
                                            SubLObject encoding_physical_field_indexical = NIL;
                                            for (encoding_physical_field_indexical = cdolist_list_var_88.first(); NIL != cdolist_list_var_88; cdolist_list_var_88 = cdolist_list_var_88.rest() , encoding_physical_field_indexical = cdolist_list_var_88.first()) {
                                                {
                                                    SubLObject sub_translations = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_for_physical_field_indexical_recursive(encoding_physical_field_indexical, logical_schemata, physical_schemata, new_used_gafs);
                                                    if (NIL != sub_translations) {
                                                        multi_alist = cons(cons(encoding_physical_field_indexical, sub_translations), multi_alist);
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Throwable ccatch_env_var) {
                                        result_87 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
                                    }
                                    if (NIL != result_87) {
                                        error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_87);
                                    }
                                }
                            } catch (Throwable ccatch_env_var) {
                                result = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_DECODINGS);
                            }
                            if (NIL != result) {
                                error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result);
                            }
                            if (NIL == error_message) {
                                translations = append(list_utilities.multi_sublis(multi_alist, encoding_list, symbol_function(EQ), UNPROVIDED), translations);
                            }
                        }
                    }
                }
            }
            {
                SubLObject cdolist_list_var = field_encoding_gafs;
                SubLObject gaf = NIL;
                for (gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , gaf = cdolist_list_var.first()) {
                    if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                        Errors.warn($str_alt46$Encoding_loop___a_, used_gafs);
                    } else {
                        {
                            SubLObject field_encoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_from_gaf(gaf);
                            SubLObject logical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_encoding_logical_field_indexicals(field_encoding);
                            SubLObject encoding_list = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_field_encoding(field_encoding, UNPROVIDED);
                            SubLObject new_used_gafs = cons(gaf, used_gafs);
                            SubLObject multi_alist = NIL;
                            SubLObject cdolist_list_var_89 = logical_field_indexicals;
                            SubLObject logical_field_indexical = NIL;
                            for (logical_field_indexical = cdolist_list_var_89.first(); NIL != cdolist_list_var_89; cdolist_list_var_89 = cdolist_list_var_89.rest() , logical_field_indexical = cdolist_list_var_89.first()) {
                                {
                                    SubLObject sub_translations = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_for_logical_field_indexical_recursive(logical_field_indexical, logical_schemata, new_used_gafs);
                                    if (NIL != sub_translations) {
                                        multi_alist = cons(cons(logical_field_indexical, sub_translations), multi_alist);
                                    }
                                }
                            }
                            translations = append(list_utilities.multi_sublis(multi_alist, encoding_list, symbol_function(EQ), UNPROVIDED), translations);
                        }
                    }
                }
            }
            if (NIL == translations) {
                sublisp_throw($SKSI_MISSING_ENCODINGS, physical_field_indexical);
            }
            return translations;
        }
    }

    public static SubLObject generate_encoding_compositions_for_physical_field_indexical_recursive(final SubLObject physical_field_indexical, final SubLObject logical_schemata, final SubLObject physical_schemata, final SubLObject used_gafs) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject physical_field_encoding_gafs = get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
        final SubLObject field_encoding_gafs = get_field_encoding_gafs_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
        SubLObject translations = NIL;
        SubLObject cdolist_list_var = physical_field_encoding_gafs;
        SubLObject gaf = NIL;
        gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                Errors.warn($str44$Encoding_loop___a_, used_gafs);
            } else {
                final SubLObject physical_field_encoding = get_physical_field_encoding_from_gaf(gaf);
                final SubLObject encoding_physical_field_indexicals = physical_field_encoding_physical_field_indexicals(physical_field_encoding);
                final SubLObject encoding_list = sksi_reconstruct_physical_field_encoding(physical_field_encoding, UNPROVIDED);
                final SubLObject new_used_gafs = cons(gaf, used_gafs);
                SubLObject multi_alist = NIL;
                SubLObject error_message = NIL;
                SubLObject result = NIL;
                try {
                    thread.throwStack.push($SKSI_MISSING_DECODINGS);
                    SubLObject result_$105 = NIL;
                    try {
                        thread.throwStack.push($SKSI_MISSING_ENCODINGS);
                        SubLObject cdolist_list_var_$106 = encoding_physical_field_indexicals;
                        SubLObject encoding_physical_field_indexical = NIL;
                        encoding_physical_field_indexical = cdolist_list_var_$106.first();
                        while (NIL != cdolist_list_var_$106) {
                            final SubLObject sub_translations = generate_encoding_compositions_for_physical_field_indexical_recursive(encoding_physical_field_indexical, logical_schemata, physical_schemata, new_used_gafs);
                            if (NIL != sub_translations) {
                                multi_alist = cons(cons(encoding_physical_field_indexical, sub_translations), multi_alist);
                            }
                            cdolist_list_var_$106 = cdolist_list_var_$106.rest();
                            encoding_physical_field_indexical = cdolist_list_var_$106.first();
                        } 
                    } catch (final Throwable ccatch_env_var) {
                        result_$105 = Errors.handleThrowable(ccatch_env_var, $SKSI_MISSING_ENCODINGS);
                    } finally {
                        thread.throwStack.pop();
                    }
                    if (NIL != result_$105) {
                        error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_ENCODINGS, result_$105);
                    }
                } catch (final Throwable ccatch_env_var2) {
                    result = Errors.handleThrowable(ccatch_env_var2, $SKSI_MISSING_DECODINGS);
                } finally {
                    thread.throwStack.pop();
                }
                if (NIL != result) {
                    error_message = sksi_debugging.sksi_violation_message($SKSI_MISSING_DECODINGS, result);
                }
                if (NIL == error_message) {
                    translations = append(list_utilities.multi_sublis(multi_alist, encoding_list, symbol_function(EQ), UNPROVIDED), translations);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            gaf = cdolist_list_var.first();
        } 
        cdolist_list_var = field_encoding_gafs;
        gaf = NIL;
        gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                Errors.warn($str44$Encoding_loop___a_, used_gafs);
            } else {
                final SubLObject field_encoding = get_field_encoding_from_gaf(gaf);
                final SubLObject logical_field_indexicals = field_encoding_logical_field_indexicals(field_encoding);
                final SubLObject encoding_list = sksi_reconstruct_field_encoding(field_encoding, UNPROVIDED);
                final SubLObject new_used_gafs = cons(gaf, used_gafs);
                SubLObject multi_alist = NIL;
                SubLObject cdolist_list_var_$107 = logical_field_indexicals;
                SubLObject logical_field_indexical = NIL;
                logical_field_indexical = cdolist_list_var_$107.first();
                while (NIL != cdolist_list_var_$107) {
                    final SubLObject sub_translations2 = generate_encoding_compositions_for_logical_field_indexical_recursive(logical_field_indexical, logical_schemata, new_used_gafs);
                    if (NIL != sub_translations2) {
                        multi_alist = cons(cons(logical_field_indexical, sub_translations2), multi_alist);
                    }
                    cdolist_list_var_$107 = cdolist_list_var_$107.rest();
                    logical_field_indexical = cdolist_list_var_$107.first();
                } 
                translations = append(list_utilities.multi_sublis(multi_alist, encoding_list, symbol_function(EQ), UNPROVIDED), translations);
            }
            cdolist_list_var = cdolist_list_var.rest();
            gaf = cdolist_list_var.first();
        } 
        if (NIL == translations) {
            sublisp_throw($SKSI_MISSING_ENCODINGS, physical_field_indexical);
        }
        return translations;
    }

    public static final SubLObject generate_encoding_compositions_for_logical_field_indexical_recursive_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject used_gafs) {
        {
            SubLObject logical_field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_encoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata);
            SubLObject translations = NIL;
            SubLObject cdolist_list_var = logical_field_encoding_gafs;
            SubLObject gaf = NIL;
            for (gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , gaf = cdolist_list_var.first()) {
                if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                    Errors.warn($str_alt46$Encoding_loop___a_, used_gafs);
                } else {
                    {
                        SubLObject logical_field_encoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_encoding_from_gaf(gaf);
                        SubLObject encoding_logical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.logical_field_encoding_logical_field_indexicals(logical_field_encoding);
                        SubLObject encoding_list = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_logical_field_encoding(logical_field_encoding, UNPROVIDED);
                        SubLObject new_used_gafs = cons(gaf, used_gafs);
                        SubLObject multi_alist = NIL;
                        SubLObject cdolist_list_var_90 = encoding_logical_field_indexicals;
                        SubLObject encoding_logical_field_indexical = NIL;
                        for (encoding_logical_field_indexical = cdolist_list_var_90.first(); NIL != cdolist_list_var_90; cdolist_list_var_90 = cdolist_list_var_90.rest() , encoding_logical_field_indexical = cdolist_list_var_90.first()) {
                            {
                                SubLObject sub_translations = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_for_logical_field_indexical_recursive(encoding_logical_field_indexical, logical_schemata, new_used_gafs);
                                if (NIL != sub_translations) {
                                    multi_alist = cons(cons(encoding_logical_field_indexical, sub_translations), multi_alist);
                                }
                            }
                        }
                        translations = append(list_utilities.multi_sublis(multi_alist, encoding_list, symbol_function(EQ), UNPROVIDED), translations);
                    }
                }
            }
            return translations;
        }
    }

    public static SubLObject generate_encoding_compositions_for_logical_field_indexical_recursive(final SubLObject logical_field_indexical, final SubLObject logical_schemata, final SubLObject used_gafs) {
        final SubLObject logical_field_encoding_gafs = get_logical_field_encoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata);
        SubLObject translations = NIL;
        SubLObject cdolist_list_var = logical_field_encoding_gafs;
        SubLObject gaf = NIL;
        gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(gaf, used_gafs)) {
                Errors.warn($str44$Encoding_loop___a_, used_gafs);
            } else {
                final SubLObject logical_field_encoding = get_logical_field_encoding_from_gaf(gaf);
                final SubLObject encoding_logical_field_indexicals = logical_field_encoding_logical_field_indexicals(logical_field_encoding);
                final SubLObject encoding_list = sksi_reconstruct_logical_field_encoding(logical_field_encoding, UNPROVIDED);
                final SubLObject new_used_gafs = cons(gaf, used_gafs);
                SubLObject multi_alist = NIL;
                SubLObject cdolist_list_var_$108 = encoding_logical_field_indexicals;
                SubLObject encoding_logical_field_indexical = NIL;
                encoding_logical_field_indexical = cdolist_list_var_$108.first();
                while (NIL != cdolist_list_var_$108) {
                    final SubLObject sub_translations = generate_encoding_compositions_for_logical_field_indexical_recursive(encoding_logical_field_indexical, logical_schemata, new_used_gafs);
                    if (NIL != sub_translations) {
                        multi_alist = cons(cons(encoding_logical_field_indexical, sub_translations), multi_alist);
                    }
                    cdolist_list_var_$108 = cdolist_list_var_$108.rest();
                    encoding_logical_field_indexical = cdolist_list_var_$108.first();
                } 
                translations = append(list_utilities.multi_sublis(multi_alist, encoding_list, symbol_function(EQ), UNPROVIDED), translations);
            }
            cdolist_list_var = cdolist_list_var.rest();
            gaf = cdolist_list_var.first();
        } 
        return translations;
    }

    /**
     * This generates compositions of encodings from LOGICAL-FIELD-INDEXICAL (and perhaps other logical field indexicals) to
     * any physical field.  What is returned is a list of pairs of compositions with the physical fields
     * to which they encode.
     */
    @LispMethod(comment = "This generates compositions of encodings from LOGICAL-FIELD-INDEXICAL (and perhaps other logical field indexicals) to\r\nany physical field.  What is returned is a list of pairs of compositions with the physical fields\r\nto which they encode.\nThis generates compositions of encodings from LOGICAL-FIELD-INDEXICAL (and perhaps other logical field indexicals) to\nany physical field.  What is returned is a list of pairs of compositions with the physical fields\nto which they encode.")
    public static final SubLObject generate_encoding_compositions_from_logical_field_indexical_internal_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        {
            SubLObject logical_field_indexical_fort = sksi_kb_accessors.lfi_fort_for_lfi(logical_field_indexical);
            SubLObject physical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.gather_lowest_physical_field_indexicals_for_logical_field_indexical(logical_field_indexical_fort, logical_schemata, physical_schemata);
            SubLObject translations = NIL;
            SubLObject cdolist_list_var = physical_field_indexicals;
            SubLObject physical_field_indexical = NIL;
            for (physical_field_indexical = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , physical_field_indexical = cdolist_list_var.first()) {
                {
                    SubLObject pfi_translations = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata, UNPROVIDED);
                    SubLObject alist = list(bq_cons(logical_field_indexical_fort, logical_field_indexical));
                    SubLObject cdolist_list_var_91 = pfi_translations;
                    SubLObject pfi_translation = NIL;
                    for (pfi_translation = cdolist_list_var_91.first(); NIL != cdolist_list_var_91; cdolist_list_var_91 = cdolist_list_var_91.rest() , pfi_translation = cdolist_list_var_91.first()) {
                        if (NIL != sksi_query_utilities.expression_contains_logical_field_indexicalP(logical_field_indexical_fort, pfi_translation)) {
                            translations = cons(list(sksi_kb_accessors.pfi_for_lfi(physical_field_indexical, logical_field_indexical), sublis(alist, pfi_translation, UNPROVIDED, UNPROVIDED)), translations);
                        }
                    }
                }
            }
            return translations;
        }
    }

    @LispMethod(comment = "This generates compositions of encodings from LOGICAL-FIELD-INDEXICAL (and perhaps other logical field indexicals) to\r\nany physical field.  What is returned is a list of pairs of compositions with the physical fields\r\nto which they encode.\nThis generates compositions of encodings from LOGICAL-FIELD-INDEXICAL (and perhaps other logical field indexicals) to\nany physical field.  What is returned is a list of pairs of compositions with the physical fields\nto which they encode.")
    public static SubLObject generate_encoding_compositions_from_logical_field_indexical_internal(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        final SubLObject logical_field_indexical_fort = sksi_kb_accessors.lfi_fort_for_lfi(logical_field_indexical);
        final SubLObject physical_field_indexicals = gather_lowest_physical_field_indexicals_for_logical_field_indexical(logical_field_indexical_fort, logical_schemata, physical_schemata);
        SubLObject translations = NIL;
        SubLObject cdolist_list_var = physical_field_indexicals;
        SubLObject physical_field_indexical = NIL;
        physical_field_indexical = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject pfi_translations = generate_encoding_compositions_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata, UNPROVIDED);
            final SubLObject alist = list(bq_cons(logical_field_indexical_fort, logical_field_indexical));
            SubLObject cdolist_list_var_$109 = pfi_translations;
            SubLObject pfi_translation = NIL;
            pfi_translation = cdolist_list_var_$109.first();
            while (NIL != cdolist_list_var_$109) {
                if (NIL != sksi_query_utilities.expression_contains_logical_field_indexicalP(logical_field_indexical_fort, pfi_translation)) {
                    translations = cons(list(sksi_kb_accessors.pfi_for_lfi(physical_field_indexical, logical_field_indexical), sublis(alist, pfi_translation, UNPROVIDED, UNPROVIDED)), translations);
                }
                cdolist_list_var_$109 = cdolist_list_var_$109.rest();
                pfi_translation = cdolist_list_var_$109.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            physical_field_indexical = cdolist_list_var.first();
        } 
        return translations;
    }/**
     * This generates compositions of encodings from LOGICAL-FIELD-INDEXICAL (and perhaps other logical field indexicals) to
     * any physical field.  What is returned is a list of pairs of compositions with the physical fields
     * to which they encode.
     */


    public static final SubLObject generate_encoding_compositions_from_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_from_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_4(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (logical_field_indexical.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (logical_schemata.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (physical_schemata.equal(cached_args.first())) {
                                                cached_args = cached_args.rest();
                                                if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt_info.equal(cached_args.first())) {
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
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.generate_encoding_compositions_from_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(logical_field_indexical, logical_schemata, physical_schemata, mt_info));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject generate_encoding_compositions_from_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject mt_info) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        if (mt_info == UNPROVIDED) {
            mt_info = mt_relevance_macros.mt_info(UNPROVIDED);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return generate_encoding_compositions_from_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_4(logical_field_indexical, logical_schemata, physical_schemata, mt_info);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (logical_field_indexical.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (logical_schemata.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (physical_schemata.equal(cached_args.first())) {
                            cached_args = cached_args.rest();
                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && mt_info.equal(cached_args.first())) {
                                return memoization_state.caching_results(results2);
                            }
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(generate_encoding_compositions_from_logical_field_indexical_internal(logical_field_indexical, logical_schemata, physical_schemata, mt_info)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(logical_field_indexical, logical_schemata, physical_schemata, mt_info));
        return memoization_state.caching_results(results3);
    }

    /**
     * This traces down through encodings to find the physical field indexicals to which LOGICAL-FIELD-INDEXICAL
     * corresponds.
     */
    @LispMethod(comment = "This traces down through encodings to find the physical field indexicals to which LOGICAL-FIELD-INDEXICAL\r\ncorresponds.\nThis traces down through encodings to find the physical field indexicals to which LOGICAL-FIELD-INDEXICAL\ncorresponds.")
    public static final SubLObject gather_lowest_physical_field_indexicals_for_logical_field_indexical_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        return list_utilities.fast_delete_duplicates(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.gather_lowest_physical_field_indexicals_for_logical_field_indexical_recursive(logical_field_indexical, logical_schemata, physical_schemata, NIL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    @LispMethod(comment = "This traces down through encodings to find the physical field indexicals to which LOGICAL-FIELD-INDEXICAL\r\ncorresponds.\nThis traces down through encodings to find the physical field indexicals to which LOGICAL-FIELD-INDEXICAL\ncorresponds.")
    public static SubLObject gather_lowest_physical_field_indexicals_for_logical_field_indexical(final SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata) {
        if (logical_schemata == UNPROVIDED) {
            logical_schemata = NIL;
        }
        if (physical_schemata == UNPROVIDED) {
            physical_schemata = NIL;
        }
        return list_utilities.fast_delete_duplicates(gather_lowest_physical_field_indexicals_for_logical_field_indexical_recursive(logical_field_indexical, logical_schemata, physical_schemata, NIL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }/**
     * This traces down through encodings to find the physical field indexicals to which LOGICAL-FIELD-INDEXICAL
     * corresponds.
     */


    public static final SubLObject gather_lowest_physical_field_indexicals_for_logical_field_indexical_recursive_alt(SubLObject logical_field_indexical, SubLObject logical_schemata, SubLObject physical_schemata, SubLObject used_gafs) {
        {
            SubLObject logical_field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_logical_field_encoding_gafs_from_logical_field_indexical(logical_field_indexical, logical_schemata);
            SubLObject field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_field_encoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata, physical_schemata, UNPROVIDED);
            SubLObject physical_field_indexicals = NIL;
            {
                SubLObject cdolist_list_var = logical_field_encoding_gafs;
                SubLObject logical_field_encoding_gaf = NIL;
                for (logical_field_encoding_gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , logical_field_encoding_gaf = cdolist_list_var.first()) {
                    if (NIL != list_utilities.member_eqP(logical_field_encoding_gaf, used_gafs)) {
                        Errors.warn($str_alt46$Encoding_loop___a_, used_gafs);
                    } else {
                        {
                            SubLObject new_logical_field_indexical = assertions_high.gaf_arg2(logical_field_encoding_gaf);
                            SubLObject new_used_gafs = cons(logical_field_encoding_gaf, used_gafs);
                            SubLObject new_pf_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.gather_lowest_physical_field_indexicals_for_logical_field_indexical_recursive(new_logical_field_indexical, logical_schemata, physical_schemata, new_used_gafs);
                            physical_field_indexicals = append(new_pf_indexicals, physical_field_indexicals);
                        }
                    }
                }
            }
            {
                SubLObject cdolist_list_var = field_encoding_gafs;
                SubLObject field_encoding_gaf = NIL;
                for (field_encoding_gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , field_encoding_gaf = cdolist_list_var.first()) {
                    if (NIL != list_utilities.member_eqP(field_encoding_gaf, used_gafs)) {
                        Errors.warn($str_alt46$Encoding_loop___a_, used_gafs);
                    } else {
                        {
                            SubLObject pf_indexical = assertions_high.gaf_arg2(field_encoding_gaf);
                            SubLObject new_used_gafs = cons(field_encoding_gaf, used_gafs);
                            SubLObject new_pf_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.gather_lowest_physical_field_indexicals_for_physical_field_indexical_recursive(pf_indexical, physical_schemata, new_used_gafs);
                            physical_field_indexicals = append(new_pf_indexicals, physical_field_indexicals);
                        }
                    }
                }
            }
            return physical_field_indexicals;
        }
    }

    public static SubLObject gather_lowest_physical_field_indexicals_for_logical_field_indexical_recursive(final SubLObject logical_field_indexical, final SubLObject logical_schemata, final SubLObject physical_schemata, final SubLObject used_gafs) {
        final SubLObject logical_field_encoding_gafs = get_logical_field_encoding_gafs_from_logical_field_indexical(logical_field_indexical, logical_schemata);
        final SubLObject field_encoding_gafs = get_field_encoding_gafs_for_logical_field_indexical(logical_field_indexical, logical_schemata, physical_schemata, UNPROVIDED);
        SubLObject physical_field_indexicals = NIL;
        SubLObject cdolist_list_var = logical_field_encoding_gafs;
        SubLObject logical_field_encoding_gaf = NIL;
        logical_field_encoding_gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(logical_field_encoding_gaf, used_gafs)) {
                Errors.warn($str44$Encoding_loop___a_, used_gafs);
            } else {
                final SubLObject new_logical_field_indexical = assertions_high.gaf_arg2(logical_field_encoding_gaf);
                final SubLObject new_used_gafs = cons(logical_field_encoding_gaf, used_gafs);
                final SubLObject new_pf_indexicals = gather_lowest_physical_field_indexicals_for_logical_field_indexical_recursive(new_logical_field_indexical, logical_schemata, physical_schemata, new_used_gafs);
                physical_field_indexicals = append(new_pf_indexicals, physical_field_indexicals);
            }
            cdolist_list_var = cdolist_list_var.rest();
            logical_field_encoding_gaf = cdolist_list_var.first();
        } 
        cdolist_list_var = field_encoding_gafs;
        SubLObject field_encoding_gaf = NIL;
        field_encoding_gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(field_encoding_gaf, used_gafs)) {
                Errors.warn($str44$Encoding_loop___a_, used_gafs);
            } else {
                final SubLObject pf_indexical = assertions_high.gaf_arg2(field_encoding_gaf);
                final SubLObject new_used_gafs = cons(field_encoding_gaf, used_gafs);
                final SubLObject new_pf_indexicals = gather_lowest_physical_field_indexicals_for_physical_field_indexical_recursive(pf_indexical, physical_schemata, new_used_gafs);
                physical_field_indexicals = append(new_pf_indexicals, physical_field_indexicals);
            }
            cdolist_list_var = cdolist_list_var.rest();
            field_encoding_gaf = cdolist_list_var.first();
        } 
        return physical_field_indexicals;
    }

    public static final SubLObject gather_lowest_physical_field_indexicals_for_physical_field_indexical_recursive_alt(SubLObject physical_field_indexical, SubLObject physical_schemata, SubLObject used_gafs) {
        {
            SubLObject physical_field_encoding_gafs = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
            SubLObject physical_field_indexicals = (NIL != physical_field_encoding_gafs) ? ((SubLObject) (NIL)) : list(physical_field_indexical);
            SubLObject cdolist_list_var = physical_field_encoding_gafs;
            SubLObject physical_field_encoding_gaf = NIL;
            for (physical_field_encoding_gaf = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , physical_field_encoding_gaf = cdolist_list_var.first()) {
                if (NIL != list_utilities.member_eqP(physical_field_encoding_gaf, used_gafs)) {
                    Errors.warn($str_alt46$Encoding_loop___a_, used_gafs);
                } else {
                    {
                        SubLObject pf_indexical = assertions_high.gaf_arg2(physical_field_encoding_gaf);
                        SubLObject new_used_gafs = cons(physical_field_encoding_gaf, used_gafs);
                        SubLObject new_pf_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.gather_lowest_physical_field_indexicals_for_physical_field_indexical_recursive(pf_indexical, physical_schemata, new_used_gafs);
                        physical_field_indexicals = append(new_pf_indexicals, physical_field_indexicals);
                    }
                }
            }
            return physical_field_indexicals;
        }
    }

    public static SubLObject gather_lowest_physical_field_indexicals_for_physical_field_indexical_recursive(final SubLObject physical_field_indexical, final SubLObject physical_schemata, final SubLObject used_gafs) {
        final SubLObject physical_field_encoding_gafs = get_physical_field_encoding_gafs_from_physical_field_indexical(physical_field_indexical, physical_schemata);
        SubLObject physical_field_indexicals = (NIL != physical_field_encoding_gafs) ? NIL : list(physical_field_indexical);
        SubLObject cdolist_list_var = physical_field_encoding_gafs;
        SubLObject physical_field_encoding_gaf = NIL;
        physical_field_encoding_gaf = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.member_eqP(physical_field_encoding_gaf, used_gafs)) {
                Errors.warn($str44$Encoding_loop___a_, used_gafs);
            } else {
                final SubLObject pf_indexical = assertions_high.gaf_arg2(physical_field_encoding_gaf);
                final SubLObject new_used_gafs = cons(physical_field_encoding_gaf, used_gafs);
                final SubLObject new_pf_indexicals = gather_lowest_physical_field_indexicals_for_physical_field_indexical_recursive(pf_indexical, physical_schemata, new_used_gafs);
                physical_field_indexicals = append(new_pf_indexicals, physical_field_indexicals);
            }
            cdolist_list_var = cdolist_list_var.rest();
            physical_field_encoding_gaf = cdolist_list_var.first();
        } 
        return physical_field_indexicals;
    }

    public static final SubLObject translations_equivalentP_alt(SubLObject translation0, SubLObject translation1, SubLObject hl_test, SubLObject el_test, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = NIL;
        }
        if (NIL != memoizeP) {
            if (NIL != kb_utilities.term_L(translation1, translation0, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalent_memoized(translation0, translation1, hl_test, el_test);
            } else {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalent_memoized(translation0, translation1, hl_test, el_test);
            }
        } else {
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalent_int(translation0, translation1, hl_test, el_test);
        }
    }

    public static SubLObject translations_equivalentP(final SubLObject translation0, final SubLObject translation1, final SubLObject hl_test, final SubLObject el_test, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = NIL;
        }
        if (NIL == memoizeP) {
            return translations_equivalent_int(translation0, translation1, hl_test, el_test);
        }
        if (NIL != kb_utilities.term_L(translation1, translation0, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
            return translations_equivalent_memoized(translation0, translation1, hl_test, el_test);
        }
        return translations_equivalent_memoized(translation0, translation1, hl_test, el_test);
    }

    public static final SubLObject translations_equivalent_memoized_internal_alt(SubLObject translation0, SubLObject translation1, SubLObject hl_test, SubLObject el_test) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalent_int(translation0, translation1, hl_test, el_test);
    }

    public static SubLObject translations_equivalent_memoized_internal(final SubLObject translation0, final SubLObject translation1, final SubLObject hl_test, final SubLObject el_test) {
        return translations_equivalent_int(translation0, translation1, hl_test, el_test);
    }

    public static final SubLObject translations_equivalent_memoized_alt(SubLObject translation0, SubLObject translation1, SubLObject hl_test, SubLObject el_test) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalent_memoized_internal(translation0, translation1, hl_test, el_test);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, TRANSLATIONS_EQUIVALENT_MEMOIZED, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), TRANSLATIONS_EQUIVALENT_MEMOIZED, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, TRANSLATIONS_EQUIVALENT_MEMOIZED, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_4(translation0, translation1, hl_test, el_test);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw16$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (translation0.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (translation1.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (hl_test.equal(cached_args.first())) {
                                                cached_args = cached_args.rest();
                                                if (((NIL != cached_args) && (NIL == cached_args.rest())) && el_test.equal(cached_args.first())) {
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
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalent_memoized_internal(translation0, translation1, hl_test, el_test)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(translation0, translation1, hl_test, el_test));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject translations_equivalent_memoized(final SubLObject translation0, final SubLObject translation1, final SubLObject hl_test, final SubLObject el_test) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return translations_equivalent_memoized_internal(translation0, translation1, hl_test, el_test);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, TRANSLATIONS_EQUIVALENT_MEMOIZED, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), TRANSLATIONS_EQUIVALENT_MEMOIZED, FOUR_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, TRANSLATIONS_EQUIVALENT_MEMOIZED, caching_state);
        }
        final SubLObject sxhash = memoization_state.sxhash_calc_4(translation0, translation1, hl_test, el_test);
        final SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (translation0.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (translation1.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (hl_test.equal(cached_args.first())) {
                            cached_args = cached_args.rest();
                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && el_test.equal(cached_args.first())) {
                                return memoization_state.caching_results(results2);
                            }
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(translations_equivalent_memoized_internal(translation0, translation1, hl_test, el_test)));
        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(translation0, translation1, hl_test, el_test));
        return memoization_state.caching_results(results3);
    }

    /**
     * Here we consider translations as functions taking the values of
     * fields (picked out by TEST) as arguments and returning the value
     * for another field.  So, two translations are equivalent just in
     * case they return the same value given the same arguments.  This
     * function approximates that notion.  NB: the result is undefined
     * when TRANSLATION0 or TRANSLATION1 is not a translation.
     */
    @LispMethod(comment = "Here we consider translations as functions taking the values of\r\nfields (picked out by TEST) as arguments and returning the value\r\nfor another field.  So, two translations are equivalent just in\r\ncase they return the same value given the same arguments.  This\r\nfunction approximates that notion.  NB: the result is undefined\r\nwhen TRANSLATION0 or TRANSLATION1 is not a translation.\nHere we consider translations as functions taking the values of\nfields (picked out by TEST) as arguments and returning the value\nfor another field.  So, two translations are equivalent just in\ncase they return the same value given the same arguments.  This\nfunction approximates that notion.  NB: the result is undefined\nwhen TRANSLATION0 or TRANSLATION1 is not a translation.")
    public static final SubLObject translations_equivalent_int_alt(SubLObject translation0, SubLObject translation1, SubLObject hl_test, SubLObject el_test) {
        if (translation0.equal(translation1)) {
            return T;
        }
        if (!((NIL != translation0) && (NIL != translation1))) {
            return NIL;
        }
        {
            SubLObject modified_translation0 = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(translation0, el_test, UNPROVIDED);
            SubLObject modified_translation1 = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_translation(translation1, el_test, UNPROVIDED);
            SubLObject possibly_equivalentP = T;
            SubLObject doneP = NIL;
            SubLObject arg_count = NIL;
            for (arg_count = ZERO_INTEGER; !((NIL == possibly_equivalentP) || (NIL != doneP)); arg_count = add(arg_count, ONE_INTEGER)) {
                {
                    SubLObject field0 = cycl_utilities.expression_find_if(hl_test, modified_translation0, T, UNPROVIDED);
                    SubLObject field1 = cycl_utilities.expression_find_if(hl_test, modified_translation1, T, UNPROVIDED);
                    if ((NIL != field0) && (NIL != field1)) {
                        modified_translation0 = cycl_utilities.expression_subst(arg_count, field0, modified_translation0, UNPROVIDED, UNPROVIDED);
                        modified_translation1 = cycl_utilities.expression_subst(arg_count, field1, modified_translation1, UNPROVIDED, UNPROVIDED);
                    } else {
                        if ((NIL != field0) || (NIL != field1)) {
                            possibly_equivalentP = NIL;
                        } else {
                            doneP = T;
                        }
                    }
                }
            }
            if (NIL != possibly_equivalentP) {
                return equal(modified_translation0, modified_translation1);
            } else {
                return NIL;
            }
        }
    }

    @LispMethod(comment = "Here we consider translations as functions taking the values of\r\nfields (picked out by TEST) as arguments and returning the value\r\nfor another field.  So, two translations are equivalent just in\r\ncase they return the same value given the same arguments.  This\r\nfunction approximates that notion.  NB: the result is undefined\r\nwhen TRANSLATION0 or TRANSLATION1 is not a translation.\nHere we consider translations as functions taking the values of\nfields (picked out by TEST) as arguments and returning the value\nfor another field.  So, two translations are equivalent just in\ncase they return the same value given the same arguments.  This\nfunction approximates that notion.  NB: the result is undefined\nwhen TRANSLATION0 or TRANSLATION1 is not a translation.")
    public static SubLObject translations_equivalent_int(final SubLObject translation0, final SubLObject translation1, final SubLObject hl_test, final SubLObject el_test) {
        if (translation0.equal(translation1)) {
            return T;
        }
        if ((NIL == translation0) || (NIL == translation1)) {
            return NIL;
        }
        SubLObject modified_translation0 = sksi_reconstruct_translation(translation0, el_test, UNPROVIDED);
        SubLObject modified_translation2 = sksi_reconstruct_translation(translation1, el_test, UNPROVIDED);
        SubLObject possibly_equivalentP = T;
        SubLObject doneP = NIL;
        SubLObject arg_count = NIL;
        arg_count = ZERO_INTEGER;
        while ((NIL != possibly_equivalentP) && (NIL == doneP)) {
            final SubLObject field0 = cycl_utilities.expression_find_if(hl_test, modified_translation0, T, UNPROVIDED);
            final SubLObject field2 = cycl_utilities.expression_find_if(hl_test, modified_translation2, T, UNPROVIDED);
            if ((NIL != field0) && (NIL != field2)) {
                modified_translation0 = cycl_utilities.expression_subst(arg_count, field0, modified_translation0, UNPROVIDED, UNPROVIDED);
                modified_translation2 = cycl_utilities.expression_subst(arg_count, field2, modified_translation2, UNPROVIDED, UNPROVIDED);
            } else
                if ((NIL != field0) || (NIL != field2)) {
                    possibly_equivalentP = NIL;
                } else {
                    doneP = T;
                }

            arg_count = add(arg_count, ONE_INTEGER);
        } 
        if (NIL != possibly_equivalentP) {
            return equal(modified_translation0, modified_translation2);
        }
        return NIL;
    }/**
     * Here we consider translations as functions taking the values of
     * fields (picked out by TEST) as arguments and returning the value
     * for another field.  So, two translations are equivalent just in
     * case they return the same value given the same arguments.  This
     * function approximates that notion.  NB: the result is undefined
     * when TRANSLATION0 or TRANSLATION1 is not a translation.
     */


    public static final SubLObject encodings_equivalentP_alt(SubLObject encoding0, SubLObject encoding1, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalentP(encoding0, encoding1, LOGICAL_FIELD_INDEXICAL_P, LOGICAL_FIELD_INDEXICAL_EL_FORMULA_P, memoizeP);
    }

    public static SubLObject encodings_equivalentP(final SubLObject encoding0, final SubLObject encoding1, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return translations_equivalentP(encoding0, encoding1, LOGICAL_FIELD_INDEXICAL_P, LOGICAL_FIELD_INDEXICAL_EL_FORMULA_P, memoizeP);
    }

    public static final SubLObject decodings_equivalentP_alt(SubLObject decoding0, SubLObject decoding1, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.translations_equivalentP(decoding0, decoding1, PHYSICAL_FIELD_INDEXICAL_P, PHYSICAL_FIELD_INDEXICAL_EL_FORMULA_P, memoizeP);
    }

    public static SubLObject decodings_equivalentP(final SubLObject decoding0, final SubLObject decoding1, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = T;
        }
        return translations_equivalentP(decoding0, decoding1, PHYSICAL_FIELD_INDEXICAL_P, PHYSICAL_FIELD_INDEXICAL_EL_FORMULA_P, memoizeP);
    }

    /**
     * Is some encoding of ENCODINGS0 equivalent to some encoding of
     * ENCODINGS1?
     */
    @LispMethod(comment = "Is some encoding of ENCODINGS0 equivalent to some encoding of\r\nENCODINGS1?\nIs some encoding of ENCODINGS0 equivalent to some encoding of\nENCODINGS1?")
    public static final SubLObject some_encodings_equivalentP_alt(SubLObject encodings0, SubLObject encodings1, SubLObject memoizedP) {
        if (memoizedP == UNPROVIDED) {
            memoizedP = NIL;
        }
        {
            SubLObject some_equivalentP = NIL;
            if (NIL == some_equivalentP) {
                {
                    SubLObject csome_list_var = encodings0;
                    SubLObject encoding0 = NIL;
                    for (encoding0 = csome_list_var.first(); !((NIL != some_equivalentP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , encoding0 = csome_list_var.first()) {
                        if (NIL == some_equivalentP) {
                            {
                                SubLObject csome_list_var_92 = encodings1;
                                SubLObject encoding1 = NIL;
                                for (encoding1 = csome_list_var_92.first(); !((NIL != some_equivalentP) || (NIL == csome_list_var_92)); csome_list_var_92 = csome_list_var_92.rest() , encoding1 = csome_list_var_92.first()) {
                                    some_equivalentP = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.encodings_equivalentP(encoding0, encoding1, memoizedP);
                                }
                            }
                        }
                    }
                }
            }
            return some_equivalentP;
        }
    }

    @LispMethod(comment = "Is some encoding of ENCODINGS0 equivalent to some encoding of\r\nENCODINGS1?\nIs some encoding of ENCODINGS0 equivalent to some encoding of\nENCODINGS1?")
    public static SubLObject some_encodings_equivalentP(final SubLObject encodings0, final SubLObject encodings1, SubLObject memoizedP) {
        if (memoizedP == UNPROVIDED) {
            memoizedP = NIL;
        }
        SubLObject some_equivalentP = NIL;
        if (NIL == some_equivalentP) {
            SubLObject csome_list_var = encodings0;
            SubLObject encoding0 = NIL;
            encoding0 = csome_list_var.first();
            while ((NIL == some_equivalentP) && (NIL != csome_list_var)) {
                if (NIL == some_equivalentP) {
                    SubLObject csome_list_var_$110;
                    SubLObject encoding2;
                    for (csome_list_var_$110 = encodings1, encoding2 = NIL, encoding2 = csome_list_var_$110.first(); (NIL == some_equivalentP) && (NIL != csome_list_var_$110); some_equivalentP = encodings_equivalentP(encoding0, encoding2, memoizedP) , csome_list_var_$110 = csome_list_var_$110.rest() , encoding2 = csome_list_var_$110.first()) {
                    }
                }
                csome_list_var = csome_list_var.rest();
                encoding0 = csome_list_var.first();
            } 
        }
        return some_equivalentP;
    }/**
     * Is some encoding of ENCODINGS0 equivalent to some encoding of
     * ENCODINGS1?
     */


    /**
     * Is some decoding of DECODINGS0 equivalent to some decoding of
     * DECODINGS1?
     */
    @LispMethod(comment = "Is some decoding of DECODINGS0 equivalent to some decoding of\r\nDECODINGS1?\nIs some decoding of DECODINGS0 equivalent to some decoding of\nDECODINGS1?")
    public static final SubLObject some_decodings_equivalentP_alt(SubLObject decodings0, SubLObject decodings1, SubLObject memoizedP) {
        if (memoizedP == UNPROVIDED) {
            memoizedP = NIL;
        }
        {
            SubLObject some_equivalentP = NIL;
            if (NIL == some_equivalentP) {
                {
                    SubLObject csome_list_var = decodings0;
                    SubLObject decoding0 = NIL;
                    for (decoding0 = csome_list_var.first(); !((NIL != some_equivalentP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , decoding0 = csome_list_var.first()) {
                        if (NIL == some_equivalentP) {
                            {
                                SubLObject csome_list_var_93 = decodings1;
                                SubLObject decoding1 = NIL;
                                for (decoding1 = csome_list_var_93.first(); !((NIL != some_equivalentP) || (NIL == csome_list_var_93)); csome_list_var_93 = csome_list_var_93.rest() , decoding1 = csome_list_var_93.first()) {
                                    some_equivalentP = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.decodings_equivalentP(decoding0, decoding1, memoizedP);
                                }
                            }
                        }
                    }
                }
            }
            return some_equivalentP;
        }
    }

    @LispMethod(comment = "Is some decoding of DECODINGS0 equivalent to some decoding of\r\nDECODINGS1?\nIs some decoding of DECODINGS0 equivalent to some decoding of\nDECODINGS1?")
    public static SubLObject some_decodings_equivalentP(final SubLObject decodings0, final SubLObject decodings1, SubLObject memoizedP) {
        if (memoizedP == UNPROVIDED) {
            memoizedP = NIL;
        }
        SubLObject some_equivalentP = NIL;
        if (NIL == some_equivalentP) {
            SubLObject csome_list_var = decodings0;
            SubLObject decoding0 = NIL;
            decoding0 = csome_list_var.first();
            while ((NIL == some_equivalentP) && (NIL != csome_list_var)) {
                if (NIL == some_equivalentP) {
                    SubLObject csome_list_var_$111;
                    SubLObject decoding2;
                    for (csome_list_var_$111 = decodings1, decoding2 = NIL, decoding2 = csome_list_var_$111.first(); (NIL == some_equivalentP) && (NIL != csome_list_var_$111); some_equivalentP = decodings_equivalentP(decoding0, decoding2, memoizedP) , csome_list_var_$111 = csome_list_var_$111.rest() , decoding2 = csome_list_var_$111.first()) {
                    }
                }
                csome_list_var = csome_list_var.rest();
                decoding0 = csome_list_var.first();
            } 
        }
        return some_equivalentP;
    }/**
     * Is some decoding of DECODINGS0 equivalent to some decoding of
     * DECODINGS1?
     */


    /**
     * ALIST should be an association list between logical fields and values
     * ENCODING should be a field encoding
     * SKS should be the relevant SKS
     * MT should be the relevant MT
     */
    @LispMethod(comment = "ALIST should be an association list between logical fields and values\r\nENCODING should be a field encoding\r\nSKS should be the relevant SKS\r\nMT should be the relevant MT\nALIST should be an association list between logical fields and values\nENCODING should be a field encoding\nSKS should be the relevant SKS\nMT should be the relevant MT")
    public static final SubLObject sksi_apply_encoding_and_reformulate_alt(SubLObject alist, SubLObject encoding, SubLObject sks, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            SubLObject reconstructed_encoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_encoding(encoding, UNPROVIDED);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_apply_translation_and_reformulate(alist, reconstructed_encoding, $ENCODE, sks, mt);
        }
    }

    @LispMethod(comment = "ALIST should be an association list between logical fields and values\r\nENCODING should be a field encoding\r\nSKS should be the relevant SKS\r\nMT should be the relevant MT\nALIST should be an association list between logical fields and values\nENCODING should be a field encoding\nSKS should be the relevant SKS\nMT should be the relevant MT")
    public static SubLObject sksi_apply_encoding_and_reformulate(final SubLObject alist, final SubLObject encoding, final SubLObject sks, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLObject reconstructed_encoding = sksi_reconstruct_encoding(encoding, UNPROVIDED);
        return sksi_apply_translation_and_reformulate(alist, reconstructed_encoding, $ENCODE, sks, mt);
    }/**
     * ALIST should be an association list between logical fields and values
     * ENCODING should be a field encoding
     * SKS should be the relevant SKS
     * MT should be the relevant MT
     */


    /**
     * ALIST should be an association list between physical field indexicals and values
     * DECODING should be a field decoding
     * SKS should be the relevant SKS
     * MT should be the relevant MT
     */
    @LispMethod(comment = "ALIST should be an association list between physical field indexicals and values\r\nDECODING should be a field decoding\r\nSKS should be the relevant SKS\r\nMT should be the relevant MT\nALIST should be an association list between physical field indexicals and values\nDECODING should be a field decoding\nSKS should be the relevant SKS\nMT should be the relevant MT")
    public static final SubLObject sksi_apply_decoding_and_reformulate_alt(SubLObject alist, SubLObject decoding, SubLObject sks, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            SubLObject reconstructed_decoding = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_reconstruct_decoding(decoding, UNPROVIDED);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.sksi_apply_translation_and_reformulate(alist, reconstructed_decoding, $DECODE, sks, mt);
        }
    }

    @LispMethod(comment = "ALIST should be an association list between physical field indexicals and values\r\nDECODING should be a field decoding\r\nSKS should be the relevant SKS\r\nMT should be the relevant MT\nALIST should be an association list between physical field indexicals and values\nDECODING should be a field decoding\nSKS should be the relevant SKS\nMT should be the relevant MT")
    public static SubLObject sksi_apply_decoding_and_reformulate(final SubLObject alist, final SubLObject decoding, final SubLObject sks, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLObject reconstructed_decoding = sksi_reconstruct_decoding(decoding, UNPROVIDED);
        return sksi_apply_translation_and_reformulate(alist, reconstructed_decoding, $DECODE, sks, mt);
    }/**
     * ALIST should be an association list between physical field indexicals and values
     * DECODING should be a field decoding
     * SKS should be the relevant SKS
     * MT should be the relevant MT
     */


    /**
     * NB: TRANSLATION should be reconstructed as per sksi-reconstruct-translation.
     */
    @LispMethod(comment = "NB: TRANSLATION should be reconstructed as per sksi-reconstruct-translation.")
    public static final SubLObject sksi_apply_translation_and_reformulate_alt(SubLObject alist, SubLObject translation, SubLObject direction, SubLObject sks, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject applied_translation = cycl_utilities.expression_sublis(alist, translation, symbol_function(EQUAL), UNPROVIDED);
                SubLObject result = NIL;
                SubLObject mt_var = mt;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
                        result = sksi_reformulate.sksi_reformulate(applied_translation, direction, sks);
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return result;
            }
        }
    }

    @LispMethod(comment = "NB: TRANSLATION should be reconstructed as per sksi-reconstruct-translation.")
    public static SubLObject sksi_apply_translation_and_reformulate(final SubLObject alist, final SubLObject translation, final SubLObject direction, final SubLObject sks, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject recursedP = NIL;
        SubLObject result = NIL;
        SubLObject rest;
        SubLObject cons;
        SubLObject current;
        SubLObject datum;
        SubLObject lf;
        SubLObject value;
        SubLObject result_items;
        SubLObject cdolist_list_var;
        SubLObject item;
        SubLObject new_alist;
        for (rest = NIL, rest = alist; (NIL == recursedP) && (NIL != rest); rest = rest.rest()) {
            cons = rest.first();
            datum = current = cons;
            lf = NIL;
            value = NIL;
            destructuring_bind_must_consp(current, datum, $list50);
            lf = current.first();
            current = value = current.rest();
            if (NIL != el_set_p(value)) {
                result_items = NIL;
                cdolist_list_var = el_set_items(value);
                item = NIL;
                item = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    new_alist = list_utilities.alist_enter(copy_expression(alist), lf, item, UNPROVIDED);
                    result_items = cons(sksi_apply_translation_and_reformulate(new_alist, translation, direction, sks, mt), result_items);
                    cdolist_list_var = cdolist_list_var.rest();
                    item = cdolist_list_var.first();
                } 
                result = make_el_set(result_items, UNPROVIDED);
                recursedP = T;
            }
        }
        if (NIL == recursedP) {
            final SubLObject applied_translation = cycl_utilities.expression_sublis(alist, translation, symbol_function(EQUAL), UNPROVIDED);
            final SubLObject mt_var = mt;
            final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
            final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
            try {
                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
                mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
                result = sksi_reformulate.sksi_reformulate(applied_translation, direction, sks);
            } finally {
                mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
            }
        }
        return result;
    }/**
     * NB: TRANSLATION should be reconstructed as per sksi-reconstruct-translation.
     */


    public static final SubLObject filter_decodings_alt(SubLObject decodings, SubLObject physical_schemata) {
        {
            SubLObject new_decodings = NIL;
            SubLObject cdolist_list_var = decodings;
            SubLObject decoding = NIL;
            for (decoding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , decoding = cdolist_list_var.first()) {
                if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.filter_decoding(decoding, physical_schemata)) {
                    new_decodings = cons(decoding, new_decodings);
                }
            }
            return nreverse(new_decodings);
        }
    }

    public static SubLObject filter_decodings(final SubLObject decodings, final SubLObject physical_schemata) {
        SubLObject new_decodings = NIL;
        SubLObject cdolist_list_var = decodings;
        SubLObject decoding = NIL;
        decoding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != filter_decoding(decoding, physical_schemata)) {
                new_decodings = cons(decoding, new_decodings);
            }
            cdolist_list_var = cdolist_list_var.rest();
            decoding = cdolist_list_var.first();
        } 
        return nreverse(new_decodings);
    }

    public static final SubLObject filter_decoding_alt(SubLObject decoding, SubLObject physical_schemata) {
        {
            SubLObject physical_field_indexicals = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_field_translation_utilities.field_translation_fields(decoding, PHYSICAL_FIELD_INDEXICAL_P);
            SubLObject filterP = NIL;
            if (NIL == filterP) {
                {
                    SubLObject csome_list_var = physical_field_indexicals;
                    SubLObject physical_field_indexical = NIL;
                    for (physical_field_indexical = csome_list_var.first(); !((NIL != filterP) || (NIL == csome_list_var)); csome_list_var = csome_list_var.rest() , physical_field_indexical = csome_list_var.first()) {
                        if (NIL == list_utilities.member_eqP(sksi_kb_accessors.physical_field_indexical_schema(physical_field_indexical), physical_schemata)) {
                            filterP = T;
                        }
                    }
                }
            }
            return NIL != filterP ? ((SubLObject) (NIL)) : decoding;
        }
    }

    public static SubLObject filter_decoding(final SubLObject decoding, final SubLObject physical_schemata) {
        final SubLObject physical_field_indexicals = field_translation_fields(decoding, PHYSICAL_FIELD_INDEXICAL_P);
        SubLObject filterP = NIL;
        if (NIL == filterP) {
            SubLObject csome_list_var = physical_field_indexicals;
            SubLObject physical_field_indexical = NIL;
            physical_field_indexical = csome_list_var.first();
            while ((NIL == filterP) && (NIL != csome_list_var)) {
                if (NIL == list_utilities.member_eqP(sksi_kb_accessors.physical_field_indexical_schema(physical_field_indexical), physical_schemata)) {
                    filterP = T;
                }
                csome_list_var = csome_list_var.rest();
                physical_field_indexical = csome_list_var.first();
            } 
        }
        return NIL != filterP ? NIL : decoding;
    }

    public static SubLObject sksi_translate_logical_sentences_to_physical_sentences(final SubLObject sentences, final SubLObject sks, SubLObject memoizeP) {
        if (memoizeP == UNPROVIDED) {
            memoizeP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject mapping_mt = sksi_kb_accessors.sk_source_mapping_mt(sks);
        final SubLObject content_mt = sksi_kb_accessors.sk_source_content_mt(sks);
        SubLObject result = NIL;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_GENL_MT, thread);
            mt_relevance_macros.$mt$.bind(mapping_mt, thread);
            final SubLObject logical_schemata = (NIL != sksi_sks_accessors.sks_complex_p(sks)) ? sksi_kb_accessors.sk_source_complex_logical_schemata(sks) : sksi_kb_accessors.sk_source_logical_schemata(sks);
            SubLObject cdolist_list_var = sentences;
            SubLObject sentence = NIL;
            sentence = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject pred = cycl_utilities.sentence_arg0(sentence);
                SubLObject meaning_sentence_gafs = sksi_meaning_sentence_utilities.relevant_logical_schemata_meaning_sentence_gafs(logical_schemata, pred, RELEVANT_PRED_IS_EQ, UNPROVIDED, UNPROVIDED);
                if (NIL != content_mt) {
                    SubLObject matching_meaning_sentence_gafs = NIL;
                    SubLObject cdolist_list_var_$112 = meaning_sentence_gafs;
                    SubLObject meaning_sentence_gaf = NIL;
                    meaning_sentence_gaf = cdolist_list_var_$112.first();
                    while (NIL != cdolist_list_var_$112) {
                        final SubLObject mt_var = content_mt;
                        final SubLObject _prev_bind_0_$113 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                        final SubLObject _prev_bind_1_$114 = mt_relevance_macros.$mt$.currentBinding(thread);
                        try {
                            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
                            mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
                            if (NIL != sksi_meaning_sentence_utilities.meaning_sentence_type_check(sentence, assertions_high.gaf_arg2(meaning_sentence_gaf), memoizeP, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                                matching_meaning_sentence_gafs = cons(meaning_sentence_gaf, matching_meaning_sentence_gafs);
                            }
                        } finally {
                            mt_relevance_macros.$mt$.rebind(_prev_bind_1_$114, thread);
                            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0_$113, thread);
                        }
                        cdolist_list_var_$112 = cdolist_list_var_$112.rest();
                        meaning_sentence_gaf = cdolist_list_var_$112.first();
                    } 
                    meaning_sentence_gafs = matching_meaning_sentence_gafs;
                }
                if (NIL == meaning_sentence_gafs) {
                    final SubLObject _prev_bind_0_$114 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    final SubLObject _prev_bind_1_$115 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_GENL_MT, thread);
                        mt_relevance_macros.$mt$.bind(mapping_mt, thread);
                        if (NIL != virtual_indexing.assertions_mentioning_terms(list(pred, $$meaningSentenceOfSchema), UNPROVIDED)) {
                            Errors.warn($str54$No_meaning_sentence_for__S, sentence);
                        }
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1_$115, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0_$114, thread);
                    }
                } else {
                    SubLObject sentence_translation = NIL;
                    SubLObject successP = NIL;
                    if (NIL == successP) {
                        SubLObject csome_list_var = meaning_sentence_gafs;
                        SubLObject meaning_sentence_gaf2 = NIL;
                        meaning_sentence_gaf2 = csome_list_var.first();
                        while ((NIL == successP) && (NIL != csome_list_var)) {
                            final SubLObject meaning_sentence = assertions_high.gaf_arg2(meaning_sentence_gaf2);
                            final SubLObject logical_schema = assertions_high.gaf_arg1(meaning_sentence_gaf2);
                            final SubLObject physical_schemata = sksi_kb_accessors.logical_schema_physical_schemata(logical_schema);
                            thread.resetMultipleValues();
                            final SubLObject raw_tuples = sksi_incremental_edit.sksi_translate_logical_sentence_to_raw_tuples(sentence, logical_schema, physical_schemata.first(), mapping_mt, sks, UNPROVIDED);
                            final SubLObject physical_fields = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if (NIL != raw_tuples) {
                                successP = T;
                                sentence_translation = cons($PRED, sentence_translation);
                                sentence_translation = cons(cycl_utilities.sentence_arg0(meaning_sentence), sentence_translation);
                                SubLObject cdolist_list_var_$113 = cycl_utilities.sentence_args(meaning_sentence, UNPROVIDED);
                                SubLObject arg = NIL;
                                arg = cdolist_list_var_$113.first();
                                while (NIL != cdolist_list_var_$113) {
                                    SubLObject name = NIL;
                                    SubLObject value = NIL;
                                    if (NIL != sksi_kb_accessors.logical_field_indexical_p(arg)) {
                                        final SubLObject relevant_physical_indexicals = sksi_infrastructure_utilities.sksi_determine_relevant_physical_field_indexicals_for_logical_field_indexical(arg, logical_schemata, physical_schemata);
                                        if (NIL == value) {
                                            SubLObject csome_list_var_$118 = relevant_physical_indexicals;
                                            SubLObject relevant_physical_indexical = NIL;
                                            relevant_physical_indexical = csome_list_var_$118.first();
                                            while ((NIL == value) && (NIL != csome_list_var_$118)) {
                                                final SubLObject relevant_physical_field = sksi_kb_accessors.physical_field_for_indexical(relevant_physical_indexical);
                                                final SubLObject position = position(relevant_physical_field, physical_fields, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                if (NIL != position) {
                                                    name = sksi_kb_accessors.physical_field_name(relevant_physical_field);
                                                    value = nth(position, raw_tuples.first().first());
                                                }
                                                csome_list_var_$118 = csome_list_var_$118.rest();
                                                relevant_physical_indexical = csome_list_var_$118.first();
                                            } 
                                        }
                                    } else {
                                        value = arg;
                                    }
                                    if (NIL == name) {
                                        value = $NO_NAME;
                                        successP = NIL;
                                        Errors.warn($str57$failure_on_name_for__S, arg);
                                    }
                                    if (NIL == value) {
                                        value = $NO_VALUE;
                                        successP = NIL;
                                        Errors.warn($str59$failure_on_value_for__S, arg);
                                    }
                                    sentence_translation = cons(name, sentence_translation);
                                    sentence_translation = cons(value, sentence_translation);
                                    cdolist_list_var_$113 = cdolist_list_var_$113.rest();
                                    arg = cdolist_list_var_$113.first();
                                } 
                                sentence_translation = nreverse(sentence_translation);
                                if ((NIL != successP) && (NIL == find($NO_NAME, sentence_translation, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED))) {
                                    result = cons(sentence_translation, result);
                                }
                                if (NIL != find($NO_NAME, sentence_translation, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                                    successP = NIL;
                                }
                            }
                            csome_list_var = csome_list_var.rest();
                            meaning_sentence_gaf2 = csome_list_var.first();
                        } 
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                sentence = cdolist_list_var.first();
            } 
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static SubLObject sksi_translate_physical_sentence_plists_to_logical_sentences(final SubLObject raw_sentence_plists, final SubLObject sks) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject mapping_mt = sksi_kb_accessors.sk_source_mapping_mt(sks);
        SubLObject result = NIL;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_GENL_MT, thread);
            mt_relevance_macros.$mt$.bind(mapping_mt, thread);
            final SubLObject physical_schemata = (NIL != sksi_sks_accessors.sks_complex_p(sks)) ? sksi_kb_accessors.sk_source_complex_physical_schemata(sks) : sksi_kb_accessors.sk_source_physical_schemata(sks);
            SubLObject cdolist_list_var = raw_sentence_plists;
            SubLObject raw_sentence_plist = NIL;
            raw_sentence_plist = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject logical_values = NIL;
                SubLObject logical_fields = NIL;
                SubLObject logical_schemata = NIL;
                SubLObject remainder;
                SubLObject pf_name;
                SubLObject raw_value;
                SubLObject physical_field;
                SubLObject physical_schema;
                SubLObject csome_list_var;
                SubLObject this_physical_schema;
                SubLObject possible_physical_field;
                SubLObject physical_field_indexical;
                SubLObject logical_field_indexicals;
                SubLObject found_logical_valueP;
                SubLObject cdolist_list_var_$119;
                SubLObject logical_field_indexical;
                SubLObject recipes;
                SubLObject csome_list_var2;
                SubLObject recipe;
                SubLObject reformulatable_expression;
                SubLObject logical_value;
                for (remainder = NIL, remainder = raw_sentence_plist; NIL != remainder; remainder = cddr(remainder)) {
                    pf_name = remainder.first();
                    raw_value = cadr(remainder);
                    physical_field = NIL;
                    physical_schema = NIL;
                    if (NIL == physical_field) {
                        csome_list_var = physical_schemata;
                        this_physical_schema = NIL;
                        this_physical_schema = csome_list_var.first();
                        while ((NIL == physical_field) && (NIL != csome_list_var)) {
                            possible_physical_field = sksi_kb_accessors.physical_field_for_schema_and_name(this_physical_schema, pf_name);
                            if (NIL != possible_physical_field) {
                                physical_field = possible_physical_field;
                                physical_schema = this_physical_schema;
                            }
                            csome_list_var = csome_list_var.rest();
                            this_physical_schema = csome_list_var.first();
                        } 
                    }
                    if (NIL == physical_field) {
                        Errors.warn($str60$No_physical_field_for__S, raw_value);
                    } else {
                        logical_schemata = sksi_kb_accessors.physical_schema_logical_schemata(physical_schema);
                        physical_field_indexical = sksi_kb_accessors.indexical_for_physical_field(physical_field);
                        if (NIL != physical_field_indexical) {
                            logical_field_indexicals = sksi_infrastructure_utilities.sksi_determine_relevant_logical_field_indexicals_for_physical_field_indexical(physical_field_indexical, logical_schemata, physical_schemata);
                            found_logical_valueP = NIL;
                            cdolist_list_var_$119 = logical_field_indexicals;
                            logical_field_indexical = NIL;
                            logical_field_indexical = cdolist_list_var_$119.first();
                            while (NIL != cdolist_list_var_$119) {
                                recipes = relevant_field_decodings_for_indexical(logical_field_indexical, logical_schemata, physical_schemata);
                                if (NIL == found_logical_valueP) {
                                    csome_list_var2 = recipes;
                                    recipe = NIL;
                                    recipe = csome_list_var2.first();
                                    while ((NIL == found_logical_valueP) && (NIL != csome_list_var2)) {
                                        reformulatable_expression = cycl_utilities.expression_subst(raw_value, cycl_utilities.naut_substitute(physical_field_indexical), cycl_utilities.naut_substitute(recipe), symbol_function(EQUAL), UNPROVIDED);
                                        logical_value = sksi_reformulate.sksi_reformulate(reformulatable_expression, $DECODE, sks);
                                        if (NIL != logical_value) {
                                            found_logical_valueP = T;
                                            logical_values = cons(logical_value, logical_values);
                                            logical_fields = cons(sksi_kb_accessors.logical_field_for_indexical(logical_field_indexical), logical_fields);
                                        }
                                        csome_list_var2 = csome_list_var2.rest();
                                        recipe = csome_list_var2.first();
                                    } 
                                }
                                cdolist_list_var_$119 = cdolist_list_var_$119.rest();
                                logical_field_indexical = cdolist_list_var_$119.first();
                            } 
                        }
                    }
                }
                logical_values = nreverse(logical_values);
                logical_fields = nreverse(logical_fields);
                final SubLObject logical_field_indexicals2 = Mapping.mapcar(INDEXICAL_FOR_LOGICAL_FIELD, logical_fields);
                SubLObject pred = NIL;
                if (NIL == pred) {
                    SubLObject csome_list_var3 = logical_schemata;
                    SubLObject logical_schema = NIL;
                    logical_schema = csome_list_var3.first();
                    while ((NIL == pred) && (NIL != csome_list_var3)) {
                        final SubLObject preds = ask_utilities.query_variable($sym62$_PRED, list($$meaningSentenceOfSchema, logical_schema, bq_cons($sym62$_PRED, logical_field_indexicals2)), mapping_mt, UNPROVIDED);
                        if (NIL != list_utilities.lengthGE(preds, TWO_INTEGER, UNPROVIDED)) {
                            Errors.warn($str63$More_than_one_meaning_sentence_pr, logical_field_indexicals2, raw_sentence_plist);
                        }
                        if (NIL != preds) {
                            pred = preds.first();
                        }
                        csome_list_var3 = csome_list_var3.rest();
                        logical_schema = csome_list_var3.first();
                    } 
                }
                if (NIL == pred) {
                    Errors.warn($str64$Meaning_sentence_not_found_for__S, logical_field_indexicals2, raw_sentence_plist);
                } else {
                    result = cons(bq_cons(pred, logical_values), result);
                }
                cdolist_list_var = cdolist_list_var.rest();
                raw_sentence_plist = cdolist_list_var.first();
            } 
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static SubLObject declare_sksi_field_translation_utilities_file() {
        declareFunction("get_field_translation_from_gaf", "GET-FIELD-TRANSLATION-FROM-GAF", 1, 0, false);
        declareMacro("destructure_field_translation_gaf", "DESTRUCTURE-FIELD-TRANSLATION-GAF");
        declareFunction("field_translation_fields", "FIELD-TRANSLATION-FIELDS", 2, 0, false);
        declareFunction("decoding_physical_field_indexicals", "DECODING-PHYSICAL-FIELD-INDEXICALS", 1, 0, false);
        new sksi_field_translation_utilities.$decoding_physical_field_indexicals$UnaryFunction();
        declareFunction("decoding_physical_field_indexicals_ordered", "DECODING-PHYSICAL-FIELD-INDEXICALS-ORDERED", 1, 0, false);
        declareFunction("encoding_logical_fields", "ENCODING-LOGICAL-FIELDS", 1, 0, false);
        declareFunction("encoding_logical_field_indexicals", "ENCODING-LOGICAL-FIELD-INDEXICALS", 1, 0, false);
        declareFunction("encoding_logical_fields_ordered", "ENCODING-LOGICAL-FIELDS-ORDERED", 1, 0, false);
        declareFunction("get_logical_field_decoding_from_gaf", "GET-LOGICAL-FIELD-DECODING-FROM-GAF", 1, 0, false);
        declareMacro("destructure_logical_field_decoding_gaf", "DESTRUCTURE-LOGICAL-FIELD-DECODING-GAF");
        declareFunction("logical_field_decoding_logical_field_indexicals", "LOGICAL-FIELD-DECODING-LOGICAL-FIELD-INDEXICALS", 1, 0, false);
        declareFunction("get_logical_field_decoding_gafs_for_logical_field", "GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD", 1, 1, false);
        declareFunction("get_logical_field_decoding_gafs_for_logical_field_indexical", "GET-LOGICAL-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL", 1, 1, false);
        declareFunction("get_logical_field_decodings_for_logical_field", "GET-LOGICAL-FIELD-DECODINGS-FOR-LOGICAL-FIELD", 1, 1, false);
        declareFunction("get_logical_field_encoding_from_gaf", "GET-LOGICAL-FIELD-ENCODING-FROM-GAF", 1, 0, false);
        declareMacro("destructure_logical_field_encoding_gaf", "DESTRUCTURE-LOGICAL-FIELD-ENCODING-GAF");
        declareFunction("logical_field_encoding_logical_field_indexicals", "LOGICAL-FIELD-ENCODING-LOGICAL-FIELD-INDEXICALS", 1, 0, false);
        declareFunction("logical_field_encoding_contains_logical_field_indexicalP", "LOGICAL-FIELD-ENCODING-CONTAINS-LOGICAL-FIELD-INDEXICAL?", 2, 0, false);
        declareFunction("get_logical_field_encoding_gafs_for_logical_field_indexical", "GET-LOGICAL-FIELD-ENCODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL", 1, 1, false);
        declareFunction("get_logical_field_encoding_gafs_from_logical_field_indexical", "GET-LOGICAL-FIELD-ENCODING-GAFS-FROM-LOGICAL-FIELD-INDEXICAL", 1, 1, false);
        declareFunction("get_field_decoding_from_gaf", "GET-FIELD-DECODING-FROM-GAF", 1, 0, false);
        declareMacro("destructure_field_decoding_gaf", "DESTRUCTURE-FIELD-DECODING-GAF");
        declareFunction("field_decoding_physical_field_indexicals", "FIELD-DECODING-PHYSICAL-FIELD-INDEXICALS", 1, 0, false);
        declareFunction("field_decoding_contains_physical_field_indexicalP", "FIELD-DECODING-CONTAINS-PHYSICAL-FIELD-INDEXICAL?", 2, 0, false);
        declareFunction("field_decoding_contains_physical_field_indexicalsP", "FIELD-DECODING-CONTAINS-PHYSICAL-FIELD-INDEXICALS?", 2, 0, false);
        declareFunction("relevant_field_decodings_internal", "RELEVANT-FIELD-DECODINGS-INTERNAL", 3, 0, false);
        declareFunction("relevant_field_decodings", "RELEVANT-FIELD-DECODINGS", 3, 0, false);
        declareFunction("relevant_field_decodings_for_indexical_internal", "RELEVANT-FIELD-DECODINGS-FOR-INDEXICAL-INTERNAL", 3, 0, false);
        declareFunction("relevant_field_decodings_for_indexical", "RELEVANT-FIELD-DECODINGS-FOR-INDEXICAL", 3, 0, false);
        declareFunction("get_field_decoding_gafs_for_logical_field_indexical", "GET-FIELD-DECODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("get_field_decodings_for_logical_field_indexical", "GET-FIELD-DECODINGS-FOR-LOGICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("get_field_decoding_gafs_from_physical_field_indexical", "GET-FIELD-DECODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("get_field_decodings_from_physical_field_indexical", "GET-FIELD-DECODINGS-FROM-PHYSICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("get_field_encoding_from_gaf", "GET-FIELD-ENCODING-FROM-GAF", 1, 0, false);
        declareMacro("destructure_field_encoding_gaf", "DESTRUCTURE-FIELD-ENCODING-GAF");
        declareFunction("field_encoding_logical_field_indexicals", "FIELD-ENCODING-LOGICAL-FIELD-INDEXICALS", 1, 0, false);
        declareFunction("isomorphic_field_encoding_p", "ISOMORPHIC-FIELD-ENCODING-P", 1, 0, false);
        declareFunction("field_encoding_contains_logical_field_indexicalP", "FIELD-ENCODING-CONTAINS-LOGICAL-FIELD-INDEXICAL?", 2, 0, false);
        declareFunction("relevant_field_encodings_internal", "RELEVANT-FIELD-ENCODINGS-INTERNAL", 3, 0, false);
        declareFunction("relevant_field_encodings", "RELEVANT-FIELD-ENCODINGS", 3, 0, false);
        declareFunction("get_field_encoding_gafs_for_physical_field", "GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD", 1, 2, false);
        declareFunction("get_field_encodings_for_physical_field_internal", "GET-FIELD-ENCODINGS-FOR-PHYSICAL-FIELD-INTERNAL", 1, 2, false);
        declareFunction("get_field_encodings_for_physical_field", "GET-FIELD-ENCODINGS-FOR-PHYSICAL-FIELD", 1, 2, false);
        declareFunction("get_field_encoding_gafs_for_physical_field_indexical_internal", "GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL-INTERNAL", 1, 2, false);
        declareFunction("get_field_encoding_gafs_for_physical_field_indexical", "GET-FIELD-ENCODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("get_field_encodings_for_physical_field_indexical", "GET-FIELD-ENCODINGS-FOR-PHYSICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("get_field_encoding_gafs_for_logical_field_indexical_internal", "GET-FIELD-ENCODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL-INTERNAL", 1, 3, false);
        declareFunction("get_field_encoding_gafs_for_logical_field_indexical", "GET-FIELD-ENCODING-GAFS-FOR-LOGICAL-FIELD-INDEXICAL", 1, 3, false);
        declareFunction("get_physical_field_decoding_from_gaf", "GET-PHYSICAL-FIELD-DECODING-FROM-GAF", 1, 0, false);
        declareMacro("destructure_physical_field_decoding_gaf", "DESTRUCTURE-PHYSICAL-FIELD-DECODING-GAF");
        declareFunction("physical_field_decoding_physical_field_indexicals", "PHYSICAL-FIELD-DECODING-PHYSICAL-FIELD-INDEXICALS", 1, 0, false);
        declareFunction("get_physical_field_decoding_gafs_for_physical_field", "GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD", 1, 1, false);
        declareFunction("get_physical_field_decodings_for_physical_field", "GET-PHYSICAL-FIELD-DECODINGS-FOR-PHYSICAL-FIELD", 1, 1, false);
        declareFunction("get_physical_field_decoding_gafs_for_physical_field_indexical", "GET-PHYSICAL-FIELD-DECODING-GAFS-FOR-PHYSICAL-FIELD-INDEXICAL", 1, 1, false);
        declareFunction("get_physical_field_decodings_for_physical_field_indexical", "GET-PHYSICAL-FIELD-DECODINGS-FOR-PHYSICAL-FIELD-INDEXICAL", 1, 1, false);
        declareFunction("get_physical_field_encoding_from_gaf", "GET-PHYSICAL-FIELD-ENCODING-FROM-GAF", 1, 0, false);
        declareMacro("destructure_physical_field_encoding_gaf", "DESTRUCTURE-PHYSICAL-FIELD-ENCODING-GAF");
        declareFunction("physical_field_encoding_physical_field_indexicals", "PHYSICAL-FIELD-ENCODING-PHYSICAL-FIELD-INDEXICALS", 1, 0, false);
        declareFunction("physical_field_encoding_contains_physical_field_indexicalP", "PHYSICAL-FIELD-ENCODING-CONTAINS-PHYSICAL-FIELD-INDEXICAL?", 2, 0, false);
        declareFunction("get_physical_field_encoding_gafs_from_physical_field", "GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD", 1, 1, false);
        declareFunction("get_physical_field_encodings_from_physical_field", "GET-PHYSICAL-FIELD-ENCODINGS-FROM-PHYSICAL-FIELD", 1, 1, false);
        declareFunction("get_physical_field_encoding_gafs_from_physical_field_indexical", "GET-PHYSICAL-FIELD-ENCODING-GAFS-FROM-PHYSICAL-FIELD-INDEXICAL", 1, 1, false);
        declareFunction("get_physical_field_encodings_from_physical_field_indexical", "GET-PHYSICAL-FIELD-ENCODINGS-FROM-PHYSICAL-FIELD-INDEXICAL", 1, 1, false);
        declareFunction("sksi_reconstruct_encoding", "SKSI-RECONSTRUCT-ENCODING", 1, 1, false);
        declareFunction("sksi_reconstruct_decoding", "SKSI-RECONSTRUCT-DECODING", 1, 1, false);
        declareFunction("sksi_reconstruct_logical_field_encoding", "SKSI-RECONSTRUCT-LOGICAL-FIELD-ENCODING", 1, 1, false);
        declareFunction("sksi_reconstruct_field_encoding", "SKSI-RECONSTRUCT-FIELD-ENCODING", 1, 1, false);
        declareFunction("sksi_reconstruct_physical_field_encoding", "SKSI-RECONSTRUCT-PHYSICAL-FIELD-ENCODING", 1, 1, false);
        declareFunction("sksi_reconstruct_logical_field_decoding", "SKSI-RECONSTRUCT-LOGICAL-FIELD-DECODING", 1, 1, false);
        declareFunction("sksi_reconstruct_field_decoding", "SKSI-RECONSTRUCT-FIELD-DECODING", 1, 1, false);
        declareFunction("sksi_reconstruct_physical_field_decoding", "SKSI-RECONSTRUCT-PHYSICAL-FIELD-DECODING", 1, 1, false);
        declareFunction("sksi_reconstruct_translation", "SKSI-RECONSTRUCT-TRANSLATION", 2, 1, false);
        declareFunction("generate_decoding_compositions_for_logical_field_indexical", "GENERATE-DECODING-COMPOSITIONS-FOR-LOGICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("generate_decoding_compositions_for_logical_field_indexical_recursive", "GENERATE-DECODING-COMPOSITIONS-FOR-LOGICAL-FIELD-INDEXICAL-RECURSIVE", 4, 0, false);
        declareFunction("generate_decoding_compositions_for_physical_field_indexical_recursive", "GENERATE-DECODING-COMPOSITIONS-FOR-PHYSICAL-FIELD-INDEXICAL-RECURSIVE", 3, 0, false);
        declareFunction("generate_encoding_compositions_for_physical_field_indexical_internal", "GENERATE-ENCODING-COMPOSITIONS-FOR-PHYSICAL-FIELD-INDEXICAL-INTERNAL", 1, 3, false);
        declareFunction("generate_encoding_compositions_for_physical_field_indexical", "GENERATE-ENCODING-COMPOSITIONS-FOR-PHYSICAL-FIELD-INDEXICAL", 1, 3, false);
        declareFunction("generate_encoding_compositions_for_physical_field_indexical_recursive", "GENERATE-ENCODING-COMPOSITIONS-FOR-PHYSICAL-FIELD-INDEXICAL-RECURSIVE", 4, 0, false);
        declareFunction("generate_encoding_compositions_for_logical_field_indexical_recursive", "GENERATE-ENCODING-COMPOSITIONS-FOR-LOGICAL-FIELD-INDEXICAL-RECURSIVE", 3, 0, false);
        declareFunction("generate_encoding_compositions_from_logical_field_indexical_internal", "GENERATE-ENCODING-COMPOSITIONS-FROM-LOGICAL-FIELD-INDEXICAL-INTERNAL", 1, 3, false);
        declareFunction("generate_encoding_compositions_from_logical_field_indexical", "GENERATE-ENCODING-COMPOSITIONS-FROM-LOGICAL-FIELD-INDEXICAL", 1, 3, false);
        declareFunction("gather_lowest_physical_field_indexicals_for_logical_field_indexical", "GATHER-LOWEST-PHYSICAL-FIELD-INDEXICALS-FOR-LOGICAL-FIELD-INDEXICAL", 1, 2, false);
        declareFunction("gather_lowest_physical_field_indexicals_for_logical_field_indexical_recursive", "GATHER-LOWEST-PHYSICAL-FIELD-INDEXICALS-FOR-LOGICAL-FIELD-INDEXICAL-RECURSIVE", 4, 0, false);
        declareFunction("gather_lowest_physical_field_indexicals_for_physical_field_indexical_recursive", "GATHER-LOWEST-PHYSICAL-FIELD-INDEXICALS-FOR-PHYSICAL-FIELD-INDEXICAL-RECURSIVE", 3, 0, false);
        declareFunction("translations_equivalentP", "TRANSLATIONS-EQUIVALENT?", 4, 1, false);
        declareFunction("translations_equivalent_memoized_internal", "TRANSLATIONS-EQUIVALENT-MEMOIZED-INTERNAL", 4, 0, false);
        declareFunction("translations_equivalent_memoized", "TRANSLATIONS-EQUIVALENT-MEMOIZED", 4, 0, false);
        declareFunction("translations_equivalent_int", "TRANSLATIONS-EQUIVALENT-INT", 4, 0, false);
        declareFunction("encodings_equivalentP", "ENCODINGS-EQUIVALENT?", 2, 1, false);
        declareFunction("decodings_equivalentP", "DECODINGS-EQUIVALENT?", 2, 1, false);
        declareFunction("some_encodings_equivalentP", "SOME-ENCODINGS-EQUIVALENT?", 2, 1, false);
        declareFunction("some_decodings_equivalentP", "SOME-DECODINGS-EQUIVALENT?", 2, 1, false);
        declareFunction("sksi_apply_encoding_and_reformulate", "SKSI-APPLY-ENCODING-AND-REFORMULATE", 3, 1, false);
        declareFunction("sksi_apply_decoding_and_reformulate", "SKSI-APPLY-DECODING-AND-REFORMULATE", 3, 1, false);
        declareFunction("sksi_apply_translation_and_reformulate", "SKSI-APPLY-TRANSLATION-AND-REFORMULATE", 4, 1, false);
        declareFunction("filter_decodings", "FILTER-DECODINGS", 2, 0, false);
        declareFunction("filter_decoding", "FILTER-DECODING", 2, 0, false);
        declareFunction("sksi_translate_logical_sentences_to_physical_sentences", "SKSI-TRANSLATE-LOGICAL-SENTENCES-TO-PHYSICAL-SENTENCES", 2, 1, false);
        declareFunction("sksi_translate_physical_sentence_plists_to_logical_sentences", "SKSI-TRANSLATE-PHYSICAL-SENTENCE-PLISTS-TO-LOGICAL-SENTENCES", 2, 0, false);
        return NIL;
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list_alt0 = list(list(makeSymbol("TO-SCHEMA"), makeSymbol("TO-FIELD"), makeSymbol("FROM-SCHEMA"), makeSymbol("FIELD-TRANSLATION")), makeSymbol("FIELD-TRANSLATION-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt5 = list(list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD-DECODING")), makeSymbol("LOGICAL-FIELD-DECODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt10 = list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("TO-LOGICAL-FIELD-INDEXICAL"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("DECODING"));

    static private final SubLList $list_alt11 = list(list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD-ENCODING")), makeSymbol("LOGICAL-FIELD-ENCODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt13 = list(makeSymbol("TO-LOGICAL-SCHEMA"), makeSymbol("TO-LOGICAL-FIELD-INDEXICAL"), makeSymbol("FROM-LOGICAL-SCHEMA"), makeSymbol("ENCODING"));

    static private final SubLList $list_alt14 = list(list(makeSymbol("LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("FIELD-DECODING")), makeSymbol("FIELD-DECODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final SubLSymbol $kw16$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLList $list_alt19 = list(makeSymbol("LOGICAL-SCHEMA"), makeSymbol("TO-LOGICAL-FIELD-INDEXICAL"), makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("DECODING"));

    static private final SubLList $list_alt21 = list(makeSymbol("LOGICAL-SCHEMA"), makeSymbol("LOGICAL-FIELD"), makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("DECODING"));

    static private final SubLList $list_alt22 = list(list(makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("LOGICAL-SCHEMA"), makeSymbol("FIELD-ENCODING")), makeSymbol("FIELD-ENCODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt27 = list(makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("TO-PHYSICAL-FIELD-INDEXICAL"), makeSymbol("LOGICAL-SCHEMA"), makeSymbol("ENCODING"));

    static private final SubLList $list_alt30 = list(makeSymbol("PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("LOGICAL-SCHEMA"), makeSymbol("ENCODING"));

    static private final SubLList $list_alt31 = list(list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-DECODING")), makeSymbol("PHYSICAL-FIELD-DECODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static SubLObject init_sksi_field_translation_utilities_file() {
        return NIL;
    }

    public static SubLObject setup_sksi_field_translation_utilities_file() {
        memoization_state.note_memoized_function(RELEVANT_FIELD_DECODINGS);
        memoization_state.note_memoized_function(RELEVANT_FIELD_DECODINGS_FOR_INDEXICAL);
        memoization_state.note_memoized_function(RELEVANT_FIELD_ENCODINGS);
        memoization_state.note_memoized_function(GET_FIELD_ENCODINGS_FOR_PHYSICAL_FIELD);
        memoization_state.note_memoized_function(GET_FIELD_ENCODING_GAFS_FOR_PHYSICAL_FIELD_INDEXICAL);
        memoization_state.note_memoized_function(GET_FIELD_ENCODING_GAFS_FOR_LOGICAL_FIELD_INDEXICAL);
        memoization_state.note_memoized_function(GENERATE_ENCODING_COMPOSITIONS_FOR_PHYSICAL_FIELD_INDEXICAL);
        memoization_state.note_memoized_function(GENERATE_ENCODING_COMPOSITIONS_FROM_LOGICAL_FIELD_INDEXICAL);
        memoization_state.note_memoized_function(TRANSLATIONS_EQUIVALENT_MEMOIZED);
        return NIL;
    }

    static private final SubLList $list_alt33 = list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("TO-PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("DECODING"));

    static private final SubLList $list_alt34 = list(list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("PHYSICAL-FIELD-ENCODING")), makeSymbol("PHYSICAL-FIELD-ENCODING-GAF"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt36 = list(makeSymbol("TO-PHYSICAL-SCHEMA"), makeSymbol("TO-PHYSICAL-FIELD-INDEXICAL"), makeSymbol("FROM-PHYSICAL-SCHEMA"), makeSymbol("ENCODING"));

    @Override
    public void declareFunctions() {
        declare_sksi_field_translation_utilities_file();
    }

    @Override
    public void initializeVariables() {
        init_sksi_field_translation_utilities_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_sksi_field_translation_utilities_file();
    }

    static {
    }

    public static final class $decoding_physical_field_indexicals$UnaryFunction extends UnaryFunction {
        public $decoding_physical_field_indexicals$UnaryFunction() {
            super(extractFunctionNamed("DECODING-PHYSICAL-FIELD-INDEXICALS"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return decoding_physical_field_indexicals(arg1);
        }
    }

    static private final SubLString $str_alt42$SKSI_ = makeString("SKSI ");

    static private final SubLString $str_alt43$__ = makeString("~%");

    static private final SubLString $str_alt44$Decoding_loop___a_ = makeString("Decoding loop: ~a.");

    static private final SubLString $str_alt46$Encoding_loop___a_ = makeString("Encoding loop: ~a.");
}

/**
 * Total time: 2521 ms
 */
