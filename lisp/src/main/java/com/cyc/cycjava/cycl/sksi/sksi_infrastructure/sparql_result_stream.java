/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.sksi.sksi_infrastructure;


import static com.cyc.cycjava.cycl.iteration.iteration_done;
import static com.cyc.cycjava.cycl.iteration.iteration_finalize;
import static com.cyc.cycjava.cycl.iteration.iteration_next;
import static com.cyc.cycjava.cycl.iteration.iteration_next_without_values;
import static com.cyc.cycjava.cycl.iteration.new_iterator;
import static com.cyc.cycjava.cycl.iteration.new_null_iterator;
import static com.cyc.cycjava.cycl.number_utilities.f_1X;
import static com.cyc.cycjava.cycl.number_utilities.f_1_;
import static com.cyc.cycjava.cycl.utilities_macros.note_funcall_helper_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_colon;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_greater;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_less;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_slash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_underbar;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.make_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.rplaca;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.set_nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.replace;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.search;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.print;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.close;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.force_output;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_string;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.defvar;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.format_nil;
import com.cyc.cycjava.cycl.iteration;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.number_utilities;
import com.cyc.cycjava.cycl.stream_buffer;
import com.cyc.cycjava.cycl.string_utilities;
import com.cyc.cycjava.cycl.web_utilities;
import com.cyc.cycjava.cycl.xml_parsing_utilities;
import com.cyc.cycjava.cycl.rdf.rdf_literal;
import com.cyc.cycjava.cycl.rdf.sparql_utilities;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Strings;
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


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      SPARQL-RESULT-STREAM
 * source file: /cyc/top/cycl/sksi/sksi-infrastructure/sparql-result-stream.lisp
 * created:     2019/07/03 17:37:54
 */
public final class sparql_result_stream extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new sparql_result_stream();

 public static final String myName = "com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream";


    // defvar
    @LispMethod(comment = "defvar")
    private static final SubLSymbol $sparql_result_trace_stream$ = makeSymbol("*SPARQL-RESULT-TRACE-STREAM*");

    private static final SubLString $str0$___A = makeString("~%~A");

    @LispMethod(comment = "Internal Constants")
    // Internal Constants
    private static final SubLInteger $int$1024 = makeInteger(1024);

    private static final SubLString $str2$Empty_SPARQL_result_stream_iterat = makeString("Empty SPARQL result stream iterator on ~a with ~a ~a");

    private static final SubLString $$$Error = makeString("Error");

    private static final SubLString $$$No_Content = makeString("No Content");

    private static final SubLString $str5$Server_Error____A = makeString("Server Error : ~A");

    private static final SubLString $str6$_results = makeString("<results");

    private static final SubLString $str7$Unexpected_SPARQL_result_stream_o = makeString("Unexpected SPARQL result stream on ~a with ~a ~a~%~a~%~a~%~a");

    private static final SubLString $str8$__ = makeString("/>");

    private static final SubLSymbol $sym9$SPARQL_RESULT_STREAM_ITERATOR_DONE_ = makeSymbol("SPARQL-RESULT-STREAM-ITERATOR-DONE?");

    private static final SubLSymbol SPARQL_RESULT_STREAM_ITERATOR_NEXT = makeSymbol("SPARQL-RESULT-STREAM-ITERATOR-NEXT");

    private static final SubLSymbol SPARQL_RESULT_STREAM_ITERATOR_FINALIZE = makeSymbol("SPARQL-RESULT-STREAM-ITERATOR-FINALIZE");

    private static final SubLString $$$true = makeString("true");

    private static final SubLList $list13 = list(NIL);

    private static final SubLList $list14 = list(makeSymbol("LINE-BUFFER"), makeSymbol("&REST"), makeSymbol("REST"));

    private static final SubLList $list15 = list(makeSymbol("LINE-BUFFER"), makeSymbol("LINE-ITERATOR"), makeSymbol("VARIABLE-NAMES"), makeSymbol("PREFIX-MAP"), makeSymbol("EXTRACT-RDF-LITERALS?"));

    private static final SubLString $str16$_result = makeString("<result");

    private static final SubLList $list17 = list(makeSymbol("LINE-BUFFER"), makeSymbol("LINE-ITERATOR"), makeSymbol("&REST"), makeSymbol("REST"));

    private static final SubLString $str18$_binding = makeString("<binding");

    private static final SubLString $str19$__binding_ = makeString("</binding>");

    private static final SubLString $str20$_uri = makeString("<uri");

    private static final SubLString $str21$_literal = makeString("<literal");

    private static final SubLString $str22$_bnode = makeString("<bnode");

    private static final SubLString $str23$Unexpected_line__S = makeString("Unexpected line ~S");

    private static final SubLString $str24$_binding_name_ = makeString("<binding name=");

    private static final SubLString $str25$_ = makeString(">");

    private static final SubLString $str26$__literal_ = makeString("</literal>");

    private static final SubLString $str27$__uri_ = makeString("</uri>");

    private static final SubLList $list28 = cons(makeSymbol("ABBREV"), makeSymbol("PREFIX"));

    private static final SubLString $str29$__bnode_ = makeString("</bnode>");

    private static final SubLString $str31$Unable_to_open__S = makeString("Unable to open ~S");

    private static final SubLString $str33$__result = makeString("</result");

    // Definitions
    public static final SubLObject string_buffer_current_string_alt(SubLObject string_buffer) {
        return string_utilities.substring(stream_buffer.string_buffer_string(string_buffer), ZERO_INTEGER, stream_buffer.string_buffer_position(string_buffer));
    }

    // Definitions
    public static SubLObject string_buffer_current_string(final SubLObject string_buffer) {
        return string_utilities.substring(stream_buffer.string_buffer_string(string_buffer), ZERO_INTEGER, stream_buffer.string_buffer_position(string_buffer));
    }

    public static SubLObject sparql_line_iterator_next(final SubLObject line_iterator) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject line_buffer = iteration.iteration_next_without_values(line_iterator, UNPROVIDED);
        if ((NIL != line_buffer) && (NIL != $sparql_result_trace_stream$.getDynamicValue(thread))) {
            format_nil.force_format($sparql_result_trace_stream$.getDynamicValue(thread), $str0$___A, string_buffer_current_string(line_buffer), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        return line_buffer;
    }

    public static final SubLObject new_sparql_result_stream_iterator(SubLObject stream, SubLObject variable_names, SubLObject prefix_map) {
        {
            SubLObject line_iterator = stream_buffer.new_stream_line_iterator(stream, $int$1024);
            SubLObject line_buffer = iteration_next_without_values(line_iterator, UNPROVIDED);
            while (NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_containsP(line_buffer, $str_alt1$_results)) {
                line_buffer = iteration_next_without_values(line_iterator, UNPROVIDED);
            } 
            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_containsP(line_buffer, $str_alt2$__)) {
                iteration_finalize(line_iterator);
                return new_null_iterator();
            }
            {
                SubLObject state = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.make_sparql_result_stream_iterator_state(line_buffer, line_iterator, variable_names, prefix_map);
                return new_iterator(state, $sym3$SPARQL_RESULT_STREAM_ITERATOR_DONE_, SPARQL_RESULT_STREAM_ITERATOR_NEXT, SPARQL_RESULT_STREAM_ITERATOR_FINALIZE);
            }
        }
    }

    public static SubLObject new_sparql_result_stream_iterator(final SubLObject stream, final SubLObject variable_names, final SubLObject prefix_map, SubLObject extract_rdf_literalsP) {
        if (extract_rdf_literalsP == UNPROVIDED) {
            extract_rdf_literalsP = NIL;
        }
        final SubLObject line_iterator = stream_buffer.new_stream_line_iterator(stream, $int$1024);
        SubLObject line_buffer = sparql_line_iterator_next(line_iterator);
        SubLObject line_buffer_old = NIL;
        SubLObject line_buffer_older = NIL;
        SubLObject line_buffer_oldest = NIL;
        if (NIL == line_buffer) {
            Errors.error($str2$Empty_SPARQL_result_stream_iterat, stream, variable_names, prefix_map);
        }
        if ((NIL != string_buffer_containsP(line_buffer, $$$Error)) || (NIL != string_buffer_containsP(line_buffer, $$$No_Content))) {
            Errors.error($str5$Server_Error____A, string_buffer_current_string(line_buffer));
        }
        while (NIL == string_buffer_containsP(line_buffer, $str6$_results)) {
            line_buffer_oldest = line_buffer_older;
            line_buffer_older = line_buffer_old;
            line_buffer_old = line_buffer;
            line_buffer = sparql_line_iterator_next(line_iterator);
            if (NIL == line_buffer) {
                Errors.error($str7$Unexpected_SPARQL_result_stream_o, new SubLObject[]{ stream, variable_names, prefix_map, line_buffer_oldest, line_buffer_older, line_buffer_old });
            }
        } 
        if (NIL != string_buffer_containsP(line_buffer, $str8$__)) {
            iteration.iteration_finalize(line_iterator);
            return iteration.new_null_iterator();
        }
        final SubLObject state = make_sparql_result_stream_iterator_state(line_buffer, line_iterator, variable_names, prefix_map, extract_rdf_literalsP);
        return iteration.new_iterator(state, $sym9$SPARQL_RESULT_STREAM_ITERATOR_DONE_, SPARQL_RESULT_STREAM_ITERATOR_NEXT, SPARQL_RESULT_STREAM_ITERATOR_FINALIZE);
    }

    public static final SubLObject batch_process_sparql_result_stream(SubLObject stream, SubLObject variable_names, SubLObject prefix_map) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result_set = NIL;
                SubLObject result_iterator = NIL;
                try {
                    result_iterator = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.new_sparql_result_stream_iterator(stream, variable_names, prefix_map);
                    {
                        SubLObject done_var = NIL;
                        while (NIL == done_var) {
                            thread.resetMultipleValues();
                            {
                                SubLObject result = iteration_next(result_iterator);
                                SubLObject valid = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                if (NIL != valid) {
                                    result_set = cons(result, result_set);
                                }
                                done_var = makeBoolean(NIL == valid);
                            }
                        } 
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                        try {
                            $is_thread_performing_cleanupP$.bind(T, thread);
                            if (NIL != result_iterator) {
                                iteration_finalize(result_iterator);
                            }
                        } finally {
                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
                return nreverse(result_set);
            }
        }
    }

    public static SubLObject batch_process_sparql_result_stream(final SubLObject stream, final SubLObject variable_names, final SubLObject prefix_map, final SubLObject booleanP, SubLObject extract_rdf_literalsP) {
        if (extract_rdf_literalsP == UNPROVIDED) {
            extract_rdf_literalsP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result_set = NIL;
        if (NIL != booleanP) {
            result_set = batch_process_boolean_sparql_result_stream(stream);
        } else {
            SubLObject result_iterator = NIL;
            try {
                result_iterator = new_sparql_result_stream_iterator(stream, variable_names, prefix_map, extract_rdf_literalsP);
                SubLObject valid;
                for (SubLObject done_var = NIL; NIL == done_var; done_var = makeBoolean(NIL == valid)) {
                    thread.resetMultipleValues();
                    final SubLObject result = iteration.iteration_next(result_iterator);
                    valid = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL != valid) {
                        result_set = cons(result, result_set);
                    }
                }
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    if (NIL != result_iterator) {
                        iteration.iteration_finalize(result_iterator);
                    }
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        }
        return nreverse(result_set);
    }

    public static SubLObject batch_process_boolean_sparql_result_stream(final SubLObject stream) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject tokens = xml_parsing_utilities.xml_tokenize(stream, NIL, UNPROVIDED, UNPROVIDED);
        if (NIL != $sparql_result_trace_stream$.getDynamicValue(thread)) {
            SubLObject cdolist_list_var = tokens;
            SubLObject token = NIL;
            token = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                write_string(token, $sparql_result_trace_stream$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED);
                cdolist_list_var = cdolist_list_var.rest();
                token = cdolist_list_var.first();
            } 
            force_output($sparql_result_trace_stream$.getDynamicValue(thread));
        }
        if (NIL != list_utilities.member_equalP($$$true, tokens)) {
            return $list13;
        }
        return NIL;
    }

    public static final SubLObject make_sparql_result_stream_iterator_state(SubLObject line_buffer, SubLObject line_iterator, SubLObject variable_names, SubLObject prefix_map) {
        line_buffer = iteration_next_without_values(line_iterator, UNPROVIDED);
        return list(line_buffer, line_iterator, variable_names, prefix_map);
    }

    public static SubLObject make_sparql_result_stream_iterator_state(SubLObject line_buffer, final SubLObject line_iterator, final SubLObject variable_names, final SubLObject prefix_map, final SubLObject extract_rdf_literalsP) {
        line_buffer = sparql_line_iterator_next(line_iterator);
        return list(line_buffer, line_iterator, variable_names, prefix_map, extract_rdf_literalsP);
    }

    public static final SubLObject sparql_result_stream_iterator_doneP_alt(SubLObject state) {
        {
            SubLObject datum = state;
            SubLObject current = datum;
            SubLObject line_buffer = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt6);
            line_buffer = current.first();
            current = current.rest();
            {
                SubLObject rest = current;
                return sublisp_null(line_buffer);
            }
        }
    }

    public static SubLObject sparql_result_stream_iterator_doneP(final SubLObject state) {
        SubLObject line_buffer = NIL;
        destructuring_bind_must_consp(state, state, $list14);
        line_buffer = state.first();
        final SubLObject rest;
        final SubLObject current = rest = state.rest();
        return sublisp_null(line_buffer);
    }

    public static final SubLObject sparql_result_stream_iterator_next_alt(SubLObject state) {
        {
            SubLObject datum = state;
            SubLObject current = datum;
            SubLObject line_buffer = NIL;
            SubLObject line_iterator = NIL;
            SubLObject variable_names = NIL;
            SubLObject prefix_map = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt7);
            line_buffer = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt7);
            line_iterator = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt7);
            variable_names = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt7);
            prefix_map = current.first();
            current = current.rest();
            if (NIL == current) {
                while ((NIL == iteration_done(line_iterator)) && (NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_containsP(line_buffer, $str_alt8$_result))) {
                    line_buffer = iteration_next_without_values(line_iterator, UNPROVIDED);
                } 
                if (NIL != iteration_done(line_iterator)) {
                    line_buffer = NIL;
                    rplaca(state, line_buffer);
                    return values(NIL, state, T);
                }
                {
                    SubLObject result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.process_sparql_file_line_buffer_result(line_iterator, line_buffer, variable_names, prefix_map);
                    return values(result, state, NIL);
                }
            } else {
                cdestructuring_bind_error(datum, $list_alt7);
            }
        }
        return NIL;
    }

    public static SubLObject sparql_result_stream_iterator_next(final SubLObject state) {
        SubLObject line_buffer = NIL;
        SubLObject line_iterator = NIL;
        SubLObject variable_names = NIL;
        SubLObject prefix_map = NIL;
        SubLObject extract_rdf_literalsP = NIL;
        destructuring_bind_must_consp(state, state, $list15);
        line_buffer = state.first();
        SubLObject current = state.rest();
        destructuring_bind_must_consp(current, state, $list15);
        line_iterator = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, state, $list15);
        variable_names = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, state, $list15);
        prefix_map = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, state, $list15);
        extract_rdf_literalsP = current.first();
        current = current.rest();
        if (NIL != current) {
            cdestructuring_bind_error(state, $list15);
            return NIL;
        }
        while ((NIL == iteration.iteration_done(line_iterator)) && (NIL == string_buffer_containsP(line_buffer, $str16$_result))) {
            line_buffer = sparql_line_iterator_next(line_iterator);
        } 
        if (NIL != iteration.iteration_done(line_iterator)) {
            line_buffer = NIL;
            rplaca(state, line_buffer);
            return values(NIL, state, T);
        }
        final SubLObject result = process_sparql_file_line_buffer_result(line_iterator, line_buffer, variable_names, prefix_map, extract_rdf_literalsP);
        return values(result, state, NIL);
    }

    public static final SubLObject sparql_result_stream_iterator_finalize_alt(SubLObject state) {
        {
            SubLObject datum = state;
            SubLObject current = datum;
            SubLObject line_buffer = NIL;
            SubLObject line_iterator = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt9);
            line_buffer = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt9);
            line_iterator = current.first();
            current = current.rest();
            {
                SubLObject rest = current;
                return iteration_finalize(line_iterator);
            }
        }
    }

    public static SubLObject sparql_result_stream_iterator_finalize(final SubLObject state) {
        SubLObject line_buffer = NIL;
        SubLObject line_iterator = NIL;
        destructuring_bind_must_consp(state, state, $list17);
        line_buffer = state.first();
        SubLObject current = state.rest();
        destructuring_bind_must_consp(current, state, $list17);
        line_iterator = current.first();
        final SubLObject rest;
        current = rest = current.rest();
        return iteration.iteration_finalize(line_iterator);
    }

    public static final SubLObject string_buffer_containsP_alt(SubLObject string_buffer, SubLObject string) {
        return search(string, stream_buffer.string_buffer_string(string_buffer), symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, stream_buffer.string_buffer_position(string_buffer));
    }

    public static SubLObject string_buffer_containsP(final SubLObject string_buffer, final SubLObject string) {
        return search(string, stream_buffer.string_buffer_string(string_buffer), symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, stream_buffer.string_buffer_position(string_buffer));
    }

    public static final SubLObject process_sparql_file_line_buffer_result(SubLObject iterator, SubLObject line_buffer, SubLObject variable_names, SubLObject prefix_map) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = make_list(length(variable_names), NIL);
                line_buffer = iteration_next_without_values(iterator, UNPROVIDED);
                while (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_containsP(line_buffer, $str_alt10$_binding)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject binding_index = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.sparql_extract_binding_value_from_line_buffer(iterator, line_buffer, variable_names, prefix_map);
                        SubLObject binding_value = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (binding_index.isInteger()) {
                            set_nth(binding_index, result, binding_value);
                        }
                    }
                } 
                return result;
            }
        }
    }

    public static SubLObject process_sparql_file_line_buffer_result(final SubLObject iterator, SubLObject line_buffer, final SubLObject variable_names, final SubLObject prefix_map, SubLObject extract_rdf_literalsP) {
        if (extract_rdf_literalsP == UNPROVIDED) {
            extract_rdf_literalsP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject result = make_list(length(variable_names), NIL);
        line_buffer = sparql_line_iterator_next(iterator);
        while (NIL != string_buffer_containsP(line_buffer, $str18$_binding)) {
            thread.resetMultipleValues();
            final SubLObject binding_index = sparql_extract_binding_value_from_line_buffer(iterator, line_buffer, variable_names, prefix_map, extract_rdf_literalsP);
            final SubLObject binding_value = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (binding_index.isInteger()) {
                set_nth(binding_index, result, binding_value);
            }
        } 
        return result;
    }

    public static final SubLObject sparql_extract_binding_value_from_line_buffer(SubLObject iterator, SubLObject line_buffer, SubLObject variable_names, SubLObject prefix_map) {
        {
            SubLObject index = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.sparql_line_buffer_variable_index(line_buffer, variable_names);
            SubLObject value = NIL;
            line_buffer = iteration_next_without_values(iterator, UNPROVIDED);
            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_containsP(line_buffer, $str_alt11$_uri)) {
                value = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.sparql_extract_uri_from_line_buffer(line_buffer, prefix_map);
            } else {
                if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_containsP(line_buffer, $str_alt12$_literal)) {
                    value = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.sparql_extract_literal_from_line_buffer(line_buffer);
                } else {
                    if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_containsP(line_buffer, $str_alt13$_bnode)) {
                        value = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.sparql_extract_bnode_from_line_buffer(line_buffer);
                    } else {
                        return Errors.error($str_alt14$Unexpected_line__S, com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.string_buffer_current_string(line_buffer));
                    }
                }
            }
            line_buffer = iteration_next_without_values(iterator, UNPROVIDED);
            line_buffer = iteration_next_without_values(iterator, UNPROVIDED);
            return values(index, value);
        }
    }

    public static SubLObject sparql_extract_binding_value_from_line_buffer(final SubLObject iterator, SubLObject line_buffer, final SubLObject variable_names, final SubLObject prefix_map, SubLObject extract_rdf_literalsP) {
        if (extract_rdf_literalsP == UNPROVIDED) {
            extract_rdf_literalsP = NIL;
        }
        final SubLObject index = sparql_line_buffer_variable_index(line_buffer, variable_names);
        final SubLObject single_lineP = string_buffer_containsP(line_buffer, $str19$__binding_);
        SubLObject value = NIL;
        if (NIL == single_lineP) {
            line_buffer = sparql_line_iterator_next(iterator);
        }
        if (NIL != string_buffer_containsP(line_buffer, $str20$_uri)) {
            value = sparql_extract_uri_from_line_buffer(line_buffer, prefix_map);
        } else
            if (NIL != string_buffer_containsP(line_buffer, $str21$_literal)) {
                value = sparql_extract_literal_from_line_buffer(line_buffer, extract_rdf_literalsP);
            } else {
                if (NIL == string_buffer_containsP(line_buffer, $str22$_bnode)) {
                    return Errors.error($str23$Unexpected_line__S, string_buffer_current_string(line_buffer));
                }
                value = sparql_extract_bnode_from_line_buffer(line_buffer);
            }

        if (NIL == single_lineP) {
            line_buffer = sparql_line_iterator_next(iterator);
        }
        line_buffer = sparql_line_iterator_next(iterator);
        return values(index, value);
    }

    public static final SubLObject sparql_line_buffer_variable_index_alt(SubLObject line_buffer, SubLObject variable_names) {
        {
            SubLObject string = stream_buffer.string_buffer_string(line_buffer);
            SubLObject end = stream_buffer.string_buffer_position(line_buffer);
            SubLObject binding_start = search($str_alt15$_binding_name_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
            if (NIL != binding_start) {
                binding_start = add(binding_start, ONE_INTEGER);
                {
                    SubLObject variable_name_start = add(binding_start, ONE_INTEGER, FOURTEEN_INTEGER);
                    SubLObject variable_name_end = search($str_alt16$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, variable_name_start, UNPROVIDED);
                    SubLObject variable_length = subtract(variable_name_end, variable_name_start, ONE_INTEGER);
                    SubLObject list_var = NIL;
                    SubLObject variable_name = NIL;
                    SubLObject index = NIL;
                    for (list_var = variable_names, variable_name = list_var.first(), index = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , variable_name = list_var.first() , index = add(ONE_INTEGER, index)) {
                        if (length(variable_name).numE(variable_length) && (NIL != search(variable_name, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, variable_name_start, variable_name_end))) {
                            return index;
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sparql_line_buffer_variable_index(final SubLObject line_buffer, final SubLObject variable_names) {
        final SubLObject string = stream_buffer.string_buffer_string(line_buffer);
        final SubLObject end = stream_buffer.string_buffer_position(line_buffer);
        final SubLObject binding_start = search($str24$_binding_name_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
        if (NIL != binding_start) {
            final SubLObject variable_name_start = add(binding_start, FIFTEEN_INTEGER);
            final SubLObject variable_name_end = number_utilities.f_1_(search($str25$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, variable_name_start, UNPROVIDED));
            final SubLObject variable_length = subtract(variable_name_end, variable_name_start);
            SubLObject list_var = NIL;
            SubLObject variable_name = NIL;
            SubLObject index = NIL;
            list_var = variable_names;
            variable_name = list_var.first();
            for (index = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , variable_name = list_var.first() , index = add(ONE_INTEGER, index)) {
                if (length(variable_name).numE(variable_length) && (NIL != search(variable_name, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, variable_name_start, variable_name_end))) {
                    return index;
                }
            }
        }
        return NIL;
    }

    public static final SubLObject sparql_extract_literal_from_line_buffer(SubLObject line_buffer) {
        {
            SubLObject string = stream_buffer.string_buffer_string(line_buffer);
            SubLObject end = stream_buffer.string_buffer_position(line_buffer);
            SubLObject literal_tag_start = search($str_alt12$_literal, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
            if (NIL != literal_tag_start) {
                {
                    SubLObject literal_tag_end = search($str_alt16$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, literal_tag_start, end);
                    SubLObject literal_start = f_1X(literal_tag_end);
                    SubLObject literal_end = search($str_alt17$__literal_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, literal_start, end);
                    SubLObject literal = string_utilities.substring(string, literal_start, literal_end);
                    return literal;
                }
            }
        }
        return NIL;
    }

    public static SubLObject sparql_extract_literal_from_line_buffer(final SubLObject line_buffer, final SubLObject extract_rdf_literalsP) {
        if (NIL != extract_rdf_literalsP) {
            return sparql_extract_rdf_literal_from_line_buffer(line_buffer);
        }
        return sparql_extract_literal_from_line_buffer_int(line_buffer);
    }

    public static SubLObject sparql_extract_literal_from_line_buffer_int(final SubLObject line_buffer) {
        final SubLObject string = stream_buffer.string_buffer_string(line_buffer);
        final SubLObject end = stream_buffer.string_buffer_position(line_buffer);
        final SubLObject literal_tag_start = search($str21$_literal, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
        if (NIL != literal_tag_start) {
            final SubLObject literal_tag_end = search($str25$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, literal_tag_start, end);
            final SubLObject literal_start = number_utilities.f_1X(literal_tag_end);
            final SubLObject literal_end = search($str26$__literal_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, literal_start, end);
            final SubLObject literal = string_utilities.substring(string, literal_start, literal_end);
            return literal;
        }
        return NIL;
    }

    public static SubLObject sparql_extract_rdf_literal_from_line_buffer(final SubLObject line_buffer) {
        final SubLObject string = stream_buffer.string_buffer_string(line_buffer);
        final SubLObject end = stream_buffer.string_buffer_position(line_buffer);
        final SubLObject literal_tag_start = search($str21$_literal, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
        if (NIL != literal_tag_start) {
            final SubLObject literal_tag_end = search($str25$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, literal_tag_start, end);
            final SubLObject literal_start = number_utilities.f_1X(literal_tag_end);
            final SubLObject literal_end = search($str26$__literal_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, literal_start, end);
            final SubLObject literal_element = string_utilities.substring(string, literal_tag_start, add(TEN_INTEGER, literal_end));
            return rdf_literal.new_rdf_literal_from_sparql_results_xml(literal_element);
        }
        return NIL;
    }

    public static final SubLObject sparql_extract_uri_from_line_buffer_alt(SubLObject line_buffer, SubLObject prefix_map) {
        {
            SubLObject string = stream_buffer.string_buffer_string(line_buffer);
            SubLObject end = stream_buffer.string_buffer_position(line_buffer);
            SubLObject uri_tag_start = search($str_alt11$_uri, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
            if (NIL != uri_tag_start) {
                {
                    SubLObject uri_tag_end = search($str_alt16$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, uri_tag_start, end);
                    SubLObject uri_start = f_1X(uri_tag_end);
                    SubLObject uri_end = search($str_alt18$__uri_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, uri_start, end);
                    SubLObject cdolist_list_var = prefix_map;
                    SubLObject cons = NIL;
                    for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                        {
                            SubLObject datum = cons;
                            SubLObject current = datum;
                            SubLObject abbrev = NIL;
                            SubLObject prefix = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt19);
                            abbrev = current.first();
                            current = current.rest();
                            prefix = current;
                            {
                                SubLObject match = search(prefix, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, uri_start, uri_end);
                                if (NIL != match) {
                                    {
                                        SubLObject abbrev_length = length(abbrev);
                                        SubLObject prefix_length = length(prefix);
                                        SubLObject postfix_start = add(uri_start, prefix_length);
                                        SubLObject postfix_end = uri_end;
                                        SubLObject postfix_length = subtract(postfix_end, postfix_start);
                                        SubLObject uri_length = add(abbrev_length, ONE_INTEGER, postfix_length);
                                        SubLObject uri = Strings.make_string(uri_length, UNPROVIDED);
                                        replace(uri, abbrev, ZERO_INTEGER, abbrev_length, ZERO_INTEGER, abbrev_length);
                                        Strings.set_char(uri, abbrev_length, CHAR_colon);
                                        replace(uri, string, f_1X(abbrev_length), length(uri), postfix_start, postfix_end);
                                        return uri;
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject uri = Strings.make_string(add(TWO_INTEGER, subtract(uri_end, uri_start)), UNPROVIDED);
                        Strings.set_char(uri, ZERO_INTEGER, CHAR_less);
                        Strings.set_char(uri, f_1_(length(uri)), CHAR_greater);
                        replace(uri, string, ONE_INTEGER, f_1_(length(uri)), uri_start, uri_end);
                        return uri;
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sparql_extract_uri_from_line_buffer(final SubLObject line_buffer, final SubLObject prefix_map) {
        final SubLObject string = stream_buffer.string_buffer_string(line_buffer);
        final SubLObject end = stream_buffer.string_buffer_position(line_buffer);
        return sparql_extract_uri_from_string(string, prefix_map, end);
    }

    public static SubLObject sparql_extract_uri_from_string(final SubLObject string, final SubLObject prefix_map, SubLObject end) {
        if (end == UNPROVIDED) {
            end = NIL;
        }
        final SubLObject uri_tag_start = search($str20$_uri, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
        if (NIL != uri_tag_start) {
            final SubLObject uri_tag_end = search($str25$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, uri_tag_start, end);
            final SubLObject uri_start = number_utilities.f_1X(uri_tag_end);
            final SubLObject uri_end = search($str27$__uri_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, uri_start, end);
            SubLObject cdolist_list_var = prefix_map;
            SubLObject cons = NIL;
            cons = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject current;
                final SubLObject datum = current = cons;
                SubLObject abbrev = NIL;
                SubLObject prefix = NIL;
                destructuring_bind_must_consp(current, datum, $list28);
                abbrev = current.first();
                current = prefix = current.rest();
                final SubLObject match = search(prefix, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, uri_start, uri_end);
                if (NIL != match) {
                    final SubLObject abbrev_length = length(abbrev);
                    final SubLObject prefix_length = length(prefix);
                    final SubLObject postfix_start = add(uri_start, prefix_length);
                    final SubLObject postfix_end = uri_end;
                    final SubLObject postfix_length = subtract(postfix_end, postfix_start);
                    final SubLObject uri_length = add(abbrev_length, ONE_INTEGER, postfix_length);
                    final SubLObject uri = Strings.make_string(uri_length, UNPROVIDED);
                    if (NIL == find(CHAR_slash, string, symbol_function(EQ), symbol_function(IDENTITY), postfix_start, postfix_end)) {
                        replace(uri, abbrev, ZERO_INTEGER, abbrev_length, ZERO_INTEGER, abbrev_length);
                        Strings.set_char(uri, abbrev_length, CHAR_colon);
                        replace(uri, string, number_utilities.f_1X(abbrev_length), length(uri), postfix_start, postfix_end);
                        return uri;
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                cons = cdolist_list_var.first();
            } 
            final SubLObject uri2 = Strings.make_string(add(TWO_INTEGER, subtract(uri_end, uri_start)), UNPROVIDED);
            Strings.set_char(uri2, ZERO_INTEGER, CHAR_less);
            Strings.set_char(uri2, number_utilities.f_1_(length(uri2)), CHAR_greater);
            replace(uri2, string, ONE_INTEGER, number_utilities.f_1_(length(uri2)), uri_start, uri_end);
            return uri2;
        }
        return NIL;
    }

    public static final SubLObject sparql_extract_bnode_from_line_buffer_alt(SubLObject line_buffer) {
        {
            SubLObject string = stream_buffer.string_buffer_string(line_buffer);
            SubLObject end = stream_buffer.string_buffer_position(line_buffer);
            SubLObject bnode_tag_start = search($str_alt13$_bnode, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
            if (NIL != bnode_tag_start) {
                {
                    SubLObject bnode_tag_end = search($str_alt16$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, bnode_tag_start, end);
                    SubLObject bnode_start = f_1X(bnode_tag_end);
                    SubLObject bnode_end = search($str_alt20$__bnode_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, bnode_start, end);
                    SubLObject bnode = Strings.make_string(add(TWO_INTEGER, subtract(bnode_end, bnode_start)), UNPROVIDED);
                    Strings.set_char(bnode, ZERO_INTEGER, CHAR_underbar);
                    Strings.set_char(bnode, ONE_INTEGER, CHAR_colon);
                    replace(bnode, string, TWO_INTEGER, length(bnode), bnode_start, bnode_end);
                    return bnode;
                }
            }
        }
        return NIL;
    }

    public static SubLObject sparql_extract_bnode_from_line_buffer(final SubLObject line_buffer) {
        final SubLObject string = stream_buffer.string_buffer_string(line_buffer);
        final SubLObject end = stream_buffer.string_buffer_position(line_buffer);
        final SubLObject bnode_tag_start = search($str22$_bnode, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, ZERO_INTEGER, end);
        if (NIL != bnode_tag_start) {
            final SubLObject bnode_tag_end = search($str25$_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, bnode_tag_start, end);
            final SubLObject bnode_start = number_utilities.f_1X(bnode_tag_end);
            final SubLObject bnode_end = search($str29$__bnode_, string, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, bnode_start, end);
            final SubLObject bnode = Strings.make_string(add(TWO_INTEGER, subtract(bnode_end, bnode_start)), UNPROVIDED);
            Strings.set_char(bnode, ZERO_INTEGER, CHAR_underbar);
            Strings.set_char(bnode, ONE_INTEGER, CHAR_colon);
            replace(bnode, string, TWO_INTEGER, length(bnode), bnode_start, bnode_end);
            return bnode;
        }
        return NIL;
    }

    public static final SubLObject process_sparql_file_batch_tokenize_alt(SubLObject filename, SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = sksi_sks_interaction.$access_path$.getGlobalValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject variable_names = sksi_sks_interaction.sparql_extract_variable_names(sparql);
                SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
                SubLObject xml_tokens = NIL;
                SubLObject stream = NIL;
                try {
                    {
                        SubLObject _prev_bind_0 = stream_macros.$stream_requires_locking$.currentBinding(thread);
                        try {
                            stream_macros.$stream_requires_locking$.bind(NIL, thread);
                            stream = compatibility.open_text(filename, $INPUT, NIL);
                        } finally {
                            stream_macros.$stream_requires_locking$.rebind(_prev_bind_0, thread);
                        }
                    }
                    if (!stream.isStream()) {
                        Errors.error($str_alt22$Unable_to_open__S, filename);
                    }
                    {
                        SubLObject s = stream;
                        xml_tokens = web_utilities.xml_tokenize(s, UNPROVIDED, UNPROVIDED, UNPROVIDED);
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
                {
                    SubLObject result_set = sksi_sks_interaction.sksi_sparql_xml_tokens_to_result_set(xml_tokens, variable_names, prefix_map);
                    return result_set;
                }
            }
        }
    }

    public static SubLObject process_sparql_file_batch_tokenize(final SubLObject filename, final SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = sksi_sks_interaction.$access_path$.getGlobalValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject variable_names = sksi_sks_interaction.sparql_extract_variable_names(sparql);
        final SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
        SubLObject xml_tokens = NIL;
        SubLObject stream = NIL;
        try {
            final SubLObject _prev_bind_0 = stream_macros.$stream_requires_locking$.currentBinding(thread);
            try {
                stream_macros.$stream_requires_locking$.bind(NIL, thread);
                stream = compatibility.open_text(filename, $INPUT);
            } finally {
                stream_macros.$stream_requires_locking$.rebind(_prev_bind_0, thread);
            }
            if (!stream.isStream()) {
                Errors.error($str31$Unable_to_open__S, filename);
            }
            final SubLObject s = stream;
            xml_tokens = xml_parsing_utilities.xml_tokenize(s, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        } finally {
            final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values = getValuesAsVector();
                if (stream.isStream()) {
                    close(stream, UNPROVIDED);
                }
                restoreValuesFromVector(_values);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
            }
        }
        final SubLObject result_set = sksi_sks_interaction.sksi_sparql_xml_tokens_to_result_set(xml_tokens, variable_names, prefix_map);
        return result_set;
    }

    public static final SubLObject process_sparql_file_iterative_tokenize_alt(SubLObject filename, SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = sksi_sks_interaction.$access_path$.getGlobalValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject variable_names = sksi_sks_interaction.sparql_extract_variable_names(sparql);
                SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
                SubLObject result_set = NIL;
                SubLObject stream = NIL;
                try {
                    {
                        SubLObject _prev_bind_0 = stream_macros.$stream_requires_locking$.currentBinding(thread);
                        try {
                            stream_macros.$stream_requires_locking$.bind(NIL, thread);
                            stream = compatibility.open_text(filename, $INPUT, NIL);
                        } finally {
                            stream_macros.$stream_requires_locking$.rebind(_prev_bind_0, thread);
                        }
                    }
                    if (!stream.isStream()) {
                        Errors.error($str_alt22$Unable_to_open__S, filename);
                    }
                    {
                        SubLObject s = stream;
                        SubLObject iterator = web_utilities.new_xml_token_iterator(s, NIL, UNPROVIDED, UNPROVIDED);
                        SubLObject token = iteration_next_without_values(iterator, UNPROVIDED);
                        while (NIL == web_utilities.xml_token_starts_with(token, $str_alt1$_results)) {
                            token = iteration_next_without_values(iterator, UNPROVIDED);
                        } 
                        token = iteration_next_without_values(iterator, UNPROVIDED);
                        {
                            SubLObject catch_var = NIL;
                            try {
                                while (true) {
                                    while ((NIL == iteration_done(iterator)) && (NIL == web_utilities.xml_token_starts_with(token, $str_alt8$_result))) {
                                        token = iteration_next_without_values(iterator, UNPROVIDED);
                                    } 
                                    if (NIL != iteration_done(iterator)) {
                                        sublisp_throw($PARSING_DONE, T);
                                    }
                                    {
                                        SubLObject result_tokens = NIL;
                                        while (NIL == web_utilities.xml_token_starts_with(token, $str_alt24$__result)) {
                                            result_tokens = cons(token, result_tokens);
                                            token = iteration_next_without_values(iterator, UNPROVIDED);
                                        } 
                                        result_tokens = cons(token, result_tokens);
                                        result_tokens = nreverse(result_tokens);
                                        {
                                            SubLObject result = sparql_utilities.sparql_extract_result(variable_names, result_tokens, prefix_map);
                                            print(result, UNPROVIDED);
                                            result_set = cons(result, result_set);
                                        }
                                    }
                                } 
                            } catch (Throwable ccatch_env_var) {
                                catch_var = Errors.handleThrowable(ccatch_env_var, $PARSING_DONE);
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
                return nreverse(result_set);
            }
        }
    }

    public static SubLObject process_sparql_file_iterative_tokenize(final SubLObject filename, final SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = sksi_sks_interaction.$access_path$.getGlobalValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject variable_names = sksi_sks_interaction.sparql_extract_variable_names(sparql);
        final SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
        SubLObject result_set = NIL;
        SubLObject stream = NIL;
        try {
            final SubLObject _prev_bind_0 = stream_macros.$stream_requires_locking$.currentBinding(thread);
            try {
                stream_macros.$stream_requires_locking$.bind(NIL, thread);
                stream = compatibility.open_text(filename, $INPUT);
            } finally {
                stream_macros.$stream_requires_locking$.rebind(_prev_bind_0, thread);
            }
            if (!stream.isStream()) {
                Errors.error($str31$Unable_to_open__S, filename);
            }
            final SubLObject s = stream;
            final SubLObject iterator = xml_parsing_utilities.new_xml_token_iterator(s, NIL, UNPROVIDED, UNPROVIDED);
            for (SubLObject token = iteration.iteration_next_without_values(iterator, UNPROVIDED); NIL == xml_parsing_utilities.xml_token_starts_with(token, $str6$_results); token = iteration.iteration_next_without_values(iterator, UNPROVIDED)) {
            }
            SubLObject token = iteration.iteration_next_without_values(iterator, UNPROVIDED);
            SubLObject catch_var = NIL;
            try {
                thread.throwStack.push($PARSING_DONE);
                while (true) {
                    if ((NIL == iteration.iteration_done(iterator)) && (NIL == xml_parsing_utilities.xml_token_starts_with(token, $str16$_result))) {
                        token = iteration.iteration_next_without_values(iterator, UNPROVIDED);
                    } else {
                        if (NIL != iteration.iteration_done(iterator)) {
                            sublisp_throw($PARSING_DONE, T);
                        }
                        SubLObject result_tokens = NIL;
                        while (NIL == xml_parsing_utilities.xml_token_starts_with(token, $str33$__result)) {
                            result_tokens = cons(token, result_tokens);
                            token = iteration.iteration_next_without_values(iterator, UNPROVIDED);
                        } 
                        result_tokens = cons(token, result_tokens);
                        result_tokens = nreverse(result_tokens);
                        final SubLObject result = sparql_utilities.sparql_extract_result(variable_names, result_tokens, prefix_map);
                        print(result, UNPROVIDED);
                        result_set = cons(result, result_set);
                    }
                } 
            } catch (final Throwable ccatch_env_var) {
                catch_var = Errors.handleThrowable(ccatch_env_var, $PARSING_DONE);
                thread.throwStack.pop();
            }
        } finally {
            final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values = getValuesAsVector();
                if (stream.isStream()) {
                    close(stream, UNPROVIDED);
                }
                restoreValuesFromVector(_values);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
            }
        }
        return nreverse(result_set);
    }

    static private final SubLString $str_alt1$_results = makeString("<results");

    static private final SubLString $str_alt2$__ = makeString("/>");

    static private final SubLSymbol $sym3$SPARQL_RESULT_STREAM_ITERATOR_DONE_ = makeSymbol("SPARQL-RESULT-STREAM-ITERATOR-DONE?");

    public static final SubLObject process_sparql_file_line_buffer_alt(SubLObject filename, SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = sksi_sks_interaction.$access_path$.getGlobalValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject variable_names = sksi_sks_interaction.sparql_extract_variable_names(sparql);
                SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
                SubLObject result_set = NIL;
                SubLObject stream = NIL;
                try {
                    {
                        SubLObject _prev_bind_0 = stream_macros.$stream_requires_locking$.currentBinding(thread);
                        try {
                            stream_macros.$stream_requires_locking$.bind(NIL, thread);
                            stream = compatibility.open_text(filename, $INPUT, NIL);
                        } finally {
                            stream_macros.$stream_requires_locking$.rebind(_prev_bind_0, thread);
                        }
                    }
                    if (!stream.isStream()) {
                        Errors.error($str_alt22$Unable_to_open__S, filename);
                    }
                    {
                        SubLObject stream_1 = stream;
                        result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sparql_result_stream.batch_process_sparql_result_stream(stream_1, variable_names, prefix_map);
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
                return result_set;
            }
        }
    }

    public static SubLObject process_sparql_file_line_buffer(final SubLObject filename, final SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = sksi_sks_interaction.$access_path$.getGlobalValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject variable_names = sksi_sks_interaction.sparql_extract_variable_names(sparql);
        final SubLObject booleanP = sksi_sks_interaction.sparql_extract_booleanP(sparql);
        final SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
        SubLObject result_set = NIL;
        SubLObject stream = NIL;
        try {
            final SubLObject _prev_bind_0 = stream_macros.$stream_requires_locking$.currentBinding(thread);
            try {
                stream_macros.$stream_requires_locking$.bind(NIL, thread);
                stream = compatibility.open_text(filename, $INPUT);
            } finally {
                stream_macros.$stream_requires_locking$.rebind(_prev_bind_0, thread);
            }
            if (!stream.isStream()) {
                Errors.error($str31$Unable_to_open__S, filename);
            }
            final SubLObject stream_$1 = stream;
            result_set = batch_process_sparql_result_stream(stream_$1, variable_names, prefix_map, booleanP, UNPROVIDED);
        } finally {
            final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values = getValuesAsVector();
                if (stream.isStream()) {
                    close(stream, UNPROVIDED);
                }
                restoreValuesFromVector(_values);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
            }
        }
        return result_set;
    }

    static private final SubLList $list_alt6 = list(makeSymbol("LINE-BUFFER"), makeSymbol("&REST"), makeSymbol("REST"));

    static private final SubLList $list_alt7 = list(makeSymbol("LINE-BUFFER"), makeSymbol("LINE-ITERATOR"), makeSymbol("VARIABLE-NAMES"), makeSymbol("PREFIX-MAP"));

    static private final SubLString $str_alt8$_result = makeString("<result");

    static private final SubLList $list_alt9 = list(makeSymbol("LINE-BUFFER"), makeSymbol("LINE-ITERATOR"), makeSymbol("&REST"), makeSymbol("REST"));

    static private final SubLString $str_alt10$_binding = makeString("<binding");

    static private final SubLString $str_alt11$_uri = makeString("<uri");

    static private final SubLString $str_alt12$_literal = makeString("<literal");

    static private final SubLString $str_alt13$_bnode = makeString("<bnode");

    static private final SubLString $str_alt14$Unexpected_line__S = makeString("Unexpected line ~S");

    static private final SubLString $str_alt15$_binding_name_ = makeString("<binding name=");

    static private final SubLString $str_alt16$_ = makeString(">");

    static private final SubLString $str_alt17$__literal_ = makeString("</literal>");

    static private final SubLString $str_alt18$__uri_ = makeString("</uri>");

    static private final SubLList $list_alt19 = cons(makeSymbol("ABBREV"), makeSymbol("PREFIX"));

    static private final SubLString $str_alt20$__bnode_ = makeString("</bnode>");

    static private final SubLString $str_alt22$Unable_to_open__S = makeString("Unable to open ~S");

    static private final SubLString $str_alt24$__result = makeString("</result");

    public static final SubLObject declare_sparql_result_stream_file_alt() {
        declareFunction("string_buffer_current_string", "STRING-BUFFER-CURRENT-STRING", 1, 0, false);
        declareFunction("new_sparql_result_stream_iterator", "NEW-SPARQL-RESULT-STREAM-ITERATOR", 3, 0, false);
        declareFunction("batch_process_sparql_result_stream", "BATCH-PROCESS-SPARQL-RESULT-STREAM", 3, 0, false);
        declareFunction("make_sparql_result_stream_iterator_state", "MAKE-SPARQL-RESULT-STREAM-ITERATOR-STATE", 4, 0, false);
        declareFunction("sparql_result_stream_iterator_doneP", "SPARQL-RESULT-STREAM-ITERATOR-DONE?", 1, 0, false);
        declareFunction("sparql_result_stream_iterator_next", "SPARQL-RESULT-STREAM-ITERATOR-NEXT", 1, 0, false);
        declareFunction("sparql_result_stream_iterator_finalize", "SPARQL-RESULT-STREAM-ITERATOR-FINALIZE", 1, 0, false);
        declareFunction("string_buffer_containsP", "STRING-BUFFER-CONTAINS?", 2, 0, false);
        declareFunction("process_sparql_file_line_buffer_result", "PROCESS-SPARQL-FILE-LINE-BUFFER-RESULT", 4, 0, false);
        declareFunction("sparql_extract_binding_value_from_line_buffer", "SPARQL-EXTRACT-BINDING-VALUE-FROM-LINE-BUFFER", 4, 0, false);
        declareFunction("sparql_line_buffer_variable_index", "SPARQL-LINE-BUFFER-VARIABLE-INDEX", 2, 0, false);
        declareFunction("sparql_extract_literal_from_line_buffer", "SPARQL-EXTRACT-LITERAL-FROM-LINE-BUFFER", 1, 0, false);
        declareFunction("sparql_extract_uri_from_line_buffer", "SPARQL-EXTRACT-URI-FROM-LINE-BUFFER", 2, 0, false);
        declareFunction("sparql_extract_bnode_from_line_buffer", "SPARQL-EXTRACT-BNODE-FROM-LINE-BUFFER", 1, 0, false);
        declareFunction("process_sparql_file_batch_tokenize", "PROCESS-SPARQL-FILE-BATCH-TOKENIZE", 2, 1, false);
        declareFunction("process_sparql_file_iterative_tokenize", "PROCESS-SPARQL-FILE-ITERATIVE-TOKENIZE", 2, 1, false);
        declareFunction("process_sparql_file_line_buffer", "PROCESS-SPARQL-FILE-LINE-BUFFER", 2, 1, false);
        return NIL;
    }

    public static SubLObject declare_sparql_result_stream_file() {
        if (SubLFiles.USE_V1) {
            declareFunction("string_buffer_current_string", "STRING-BUFFER-CURRENT-STRING", 1, 0, false);
            declareFunction("sparql_line_iterator_next", "SPARQL-LINE-ITERATOR-NEXT", 1, 0, false);
            declareFunction("new_sparql_result_stream_iterator", "NEW-SPARQL-RESULT-STREAM-ITERATOR", 3, 1, false);
            declareFunction("batch_process_sparql_result_stream", "BATCH-PROCESS-SPARQL-RESULT-STREAM", 4, 1, false);
            declareFunction("batch_process_boolean_sparql_result_stream", "BATCH-PROCESS-BOOLEAN-SPARQL-RESULT-STREAM", 1, 0, false);
            declareFunction("make_sparql_result_stream_iterator_state", "MAKE-SPARQL-RESULT-STREAM-ITERATOR-STATE", 5, 0, false);
            declareFunction("sparql_result_stream_iterator_doneP", "SPARQL-RESULT-STREAM-ITERATOR-DONE?", 1, 0, false);
            declareFunction("sparql_result_stream_iterator_next", "SPARQL-RESULT-STREAM-ITERATOR-NEXT", 1, 0, false);
            declareFunction("sparql_result_stream_iterator_finalize", "SPARQL-RESULT-STREAM-ITERATOR-FINALIZE", 1, 0, false);
            declareFunction("string_buffer_containsP", "STRING-BUFFER-CONTAINS?", 2, 0, false);
            declareFunction("process_sparql_file_line_buffer_result", "PROCESS-SPARQL-FILE-LINE-BUFFER-RESULT", 4, 1, false);
            declareFunction("sparql_extract_binding_value_from_line_buffer", "SPARQL-EXTRACT-BINDING-VALUE-FROM-LINE-BUFFER", 4, 1, false);
            declareFunction("sparql_line_buffer_variable_index", "SPARQL-LINE-BUFFER-VARIABLE-INDEX", 2, 0, false);
            declareFunction("sparql_extract_literal_from_line_buffer", "SPARQL-EXTRACT-LITERAL-FROM-LINE-BUFFER", 2, 0, false);
            declareFunction("sparql_extract_literal_from_line_buffer_int", "SPARQL-EXTRACT-LITERAL-FROM-LINE-BUFFER-INT", 1, 0, false);
            declareFunction("sparql_extract_rdf_literal_from_line_buffer", "SPARQL-EXTRACT-RDF-LITERAL-FROM-LINE-BUFFER", 1, 0, false);
            declareFunction("sparql_extract_uri_from_line_buffer", "SPARQL-EXTRACT-URI-FROM-LINE-BUFFER", 2, 0, false);
            declareFunction("sparql_extract_uri_from_string", "SPARQL-EXTRACT-URI-FROM-STRING", 2, 1, false);
            declareFunction("sparql_extract_bnode_from_line_buffer", "SPARQL-EXTRACT-BNODE-FROM-LINE-BUFFER", 1, 0, false);
            declareFunction("process_sparql_file_batch_tokenize", "PROCESS-SPARQL-FILE-BATCH-TOKENIZE", 2, 1, false);
            declareFunction("process_sparql_file_iterative_tokenize", "PROCESS-SPARQL-FILE-ITERATIVE-TOKENIZE", 2, 1, false);
            declareFunction("process_sparql_file_line_buffer", "PROCESS-SPARQL-FILE-LINE-BUFFER", 2, 1, false);
        }
        if (SubLFiles.USE_V2) {
            declareFunction("new_sparql_result_stream_iterator", "NEW-SPARQL-RESULT-STREAM-ITERATOR", 3, 0, false);
            declareFunction("batch_process_sparql_result_stream", "BATCH-PROCESS-SPARQL-RESULT-STREAM", 3, 0, false);
            declareFunction("make_sparql_result_stream_iterator_state", "MAKE-SPARQL-RESULT-STREAM-ITERATOR-STATE", 4, 0, false);
            declareFunction("process_sparql_file_line_buffer_result", "PROCESS-SPARQL-FILE-LINE-BUFFER-RESULT", 4, 0, false);
            declareFunction("sparql_extract_binding_value_from_line_buffer", "SPARQL-EXTRACT-BINDING-VALUE-FROM-LINE-BUFFER", 4, 0, false);
            declareFunction("sparql_extract_literal_from_line_buffer", "SPARQL-EXTRACT-LITERAL-FROM-LINE-BUFFER", 1, 0, false);
        }
        return NIL;
    }

    public static SubLObject declare_sparql_result_stream_file_Previous() {
        declareFunction("string_buffer_current_string", "STRING-BUFFER-CURRENT-STRING", 1, 0, false);
        declareFunction("sparql_line_iterator_next", "SPARQL-LINE-ITERATOR-NEXT", 1, 0, false);
        declareFunction("new_sparql_result_stream_iterator", "NEW-SPARQL-RESULT-STREAM-ITERATOR", 3, 1, false);
        declareFunction("batch_process_sparql_result_stream", "BATCH-PROCESS-SPARQL-RESULT-STREAM", 4, 1, false);
        declareFunction("batch_process_boolean_sparql_result_stream", "BATCH-PROCESS-BOOLEAN-SPARQL-RESULT-STREAM", 1, 0, false);
        declareFunction("make_sparql_result_stream_iterator_state", "MAKE-SPARQL-RESULT-STREAM-ITERATOR-STATE", 5, 0, false);
        declareFunction("sparql_result_stream_iterator_doneP", "SPARQL-RESULT-STREAM-ITERATOR-DONE?", 1, 0, false);
        declareFunction("sparql_result_stream_iterator_next", "SPARQL-RESULT-STREAM-ITERATOR-NEXT", 1, 0, false);
        declareFunction("sparql_result_stream_iterator_finalize", "SPARQL-RESULT-STREAM-ITERATOR-FINALIZE", 1, 0, false);
        declareFunction("string_buffer_containsP", "STRING-BUFFER-CONTAINS?", 2, 0, false);
        declareFunction("process_sparql_file_line_buffer_result", "PROCESS-SPARQL-FILE-LINE-BUFFER-RESULT", 4, 1, false);
        declareFunction("sparql_extract_binding_value_from_line_buffer", "SPARQL-EXTRACT-BINDING-VALUE-FROM-LINE-BUFFER", 4, 1, false);
        declareFunction("sparql_line_buffer_variable_index", "SPARQL-LINE-BUFFER-VARIABLE-INDEX", 2, 0, false);
        declareFunction("sparql_extract_literal_from_line_buffer", "SPARQL-EXTRACT-LITERAL-FROM-LINE-BUFFER", 2, 0, false);
        declareFunction("sparql_extract_literal_from_line_buffer_int", "SPARQL-EXTRACT-LITERAL-FROM-LINE-BUFFER-INT", 1, 0, false);
        declareFunction("sparql_extract_rdf_literal_from_line_buffer", "SPARQL-EXTRACT-RDF-LITERAL-FROM-LINE-BUFFER", 1, 0, false);
        declareFunction("sparql_extract_uri_from_line_buffer", "SPARQL-EXTRACT-URI-FROM-LINE-BUFFER", 2, 0, false);
        declareFunction("sparql_extract_uri_from_string", "SPARQL-EXTRACT-URI-FROM-STRING", 2, 1, false);
        declareFunction("sparql_extract_bnode_from_line_buffer", "SPARQL-EXTRACT-BNODE-FROM-LINE-BUFFER", 1, 0, false);
        declareFunction("process_sparql_file_batch_tokenize", "PROCESS-SPARQL-FILE-BATCH-TOKENIZE", 2, 1, false);
        declareFunction("process_sparql_file_iterative_tokenize", "PROCESS-SPARQL-FILE-ITERATIVE-TOKENIZE", 2, 1, false);
        declareFunction("process_sparql_file_line_buffer", "PROCESS-SPARQL-FILE-LINE-BUFFER", 2, 1, false);
        return NIL;
    }

    public static SubLObject init_sparql_result_stream_file() {
        defvar("*SPARQL-RESULT-TRACE-STREAM*", NIL);
        return NIL;
    }

    public static final SubLObject setup_sparql_result_stream_file_alt() {
        note_funcall_helper_function($sym3$SPARQL_RESULT_STREAM_ITERATOR_DONE_);
        note_funcall_helper_function(SPARQL_RESULT_STREAM_ITERATOR_NEXT);
        note_funcall_helper_function(SPARQL_RESULT_STREAM_ITERATOR_FINALIZE);
        return NIL;
    }

    public static SubLObject setup_sparql_result_stream_file() {
        if (SubLFiles.USE_V1) {
            note_funcall_helper_function($sym9$SPARQL_RESULT_STREAM_ITERATOR_DONE_);
            note_funcall_helper_function(SPARQL_RESULT_STREAM_ITERATOR_NEXT);
            note_funcall_helper_function(SPARQL_RESULT_STREAM_ITERATOR_FINALIZE);
        }
        if (SubLFiles.USE_V2) {
            note_funcall_helper_function($sym3$SPARQL_RESULT_STREAM_ITERATOR_DONE_);
        }
        return NIL;
    }

    public static SubLObject setup_sparql_result_stream_file_Previous() {
        note_funcall_helper_function($sym9$SPARQL_RESULT_STREAM_ITERATOR_DONE_);
        note_funcall_helper_function(SPARQL_RESULT_STREAM_ITERATOR_NEXT);
        note_funcall_helper_function(SPARQL_RESULT_STREAM_ITERATOR_FINALIZE);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_sparql_result_stream_file();
    }

    @Override
    public void initializeVariables() {
        init_sparql_result_stream_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_sparql_result_stream_file();
    }

    static {
    }
}

/**
 * Total time: 140 ms
 */
