/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.constant_handles.$dtp_constant$;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.cyc_testing.generic_testing.define_test_case_table_int;
import static com.cyc.cycjava.cycl.string_utilities.indent;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.multiply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.method_func;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.make_vector;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.prin1;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.princ;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.close;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.get_output_stream_string;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.make_private_string_output_stream;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.owl.owlification;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.StreamsLow;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
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
 * module:      FORMAT-CYCL-EXPRESSION
 * source file: /cyc/top/cycl/format-cycl-expression.lisp
 * created:     2019/07/03 17:37:21
 */
public final class format_cycl_expression extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new format_cycl_expression();

 public static final String myName = "com.cyc.cycjava.cycl.format_cycl_expression";


    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $format_cycl_expression_newline_method$ = makeSymbol("*FORMAT-CYCL-EXPRESSION-NEWLINE-METHOD*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $format_cycl_expression_indent_method$ = makeSymbol("*FORMAT-CYCL-EXPRESSION-INDENT-METHOD*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $format_cycl_expression_atom_method_table$ = makeSymbol("*FORMAT-CYCL-EXPRESSION-ATOM-METHOD-TABLE*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $format_cycl_expression_constant_method$ = makeSymbol("*FORMAT-CYCL-EXPRESSION-CONSTANT-METHOD*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $format_cycl_expression_nat_method$ = makeSymbol("*FORMAT-CYCL-EXPRESSION-NAT-METHOD*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $format_cycl_expression_assertion_method$ = makeSymbol("*FORMAT-CYCL-EXPRESSION-ASSERTION-METHOD*");

    private static final SubLSymbol GET_PRETTY_FORMATTED_STRING = makeSymbol("GET-PRETTY-FORMATTED-STRING");

    private static final SubLString $str1$_ = makeString("(");

    private static final SubLString $str2$_ = makeString(" ");

    private static final SubLString $str3$___ = makeString(" . ");

    private static final SubLString $str4$_ = makeString(")");

    private static final SubLSymbol FORMAT_CYCL_EXPRESSION_DEFAULT_INDENT = makeSymbol("FORMAT-CYCL-EXPRESSION-DEFAULT-INDENT");

    private static final SubLSymbol FORMAT_CYCL_EXPRESSION_ATOM_CONSTANT_METHOD = makeSymbol("FORMAT-CYCL-EXPRESSION-ATOM-CONSTANT-METHOD");

    private static final SubLSymbol FORMAT_CYCL_EXPRESSION_ATOM_NART_METHOD = makeSymbol("FORMAT-CYCL-EXPRESSION-ATOM-NART-METHOD");

    private static final SubLSymbol FORMAT_CYCL_EXPRESSION_ATOM_ASSERTION_METHOD = makeSymbol("FORMAT-CYCL-EXPRESSION-ATOM-ASSERTION-METHOD");

    private static final SubLList $list17 = list(list(list(list(reader_make_constant_shell("implies"), list(reader_make_constant_shell("isa"), makeSymbol("?X"), reader_make_constant_shell("Cat")), list(reader_make_constant_shell("likesAsFriend"), makeSymbol("?X"), reader_make_constant_shell("DaveS"))), T, T), makeString("(#$implies \n  (#$isa ?X #$Cat) \n  (#$likesAsFriend ?X #$DaveS))")), list(list(list(reader_make_constant_shell("implies"), list(reader_make_constant_shell("isa"), makeSymbol("?X"), reader_make_constant_shell("Cat")), list(reader_make_constant_shell("likesAsFriend"), makeSymbol("?X"), reader_make_constant_shell("DaveS"))), T, NIL), makeString("(#$implies (#$isa ?X #$Cat) (#$likesAsFriend ?X #$DaveS))")), list(list(list(reader_make_constant_shell("implies"), list(reader_make_constant_shell("isa"), makeSymbol("?X"), reader_make_constant_shell("Cat")), list(reader_make_constant_shell("likesAsFriend"), makeSymbol("?X"), reader_make_constant_shell("DaveS"))), NIL, T), makeString("(implies \n  (isa ?X Cat) \n  (likesAsFriend ?X DaveS))")), list(list(list(reader_make_constant_shell("implies"), list(reader_make_constant_shell("isa"), makeSymbol("?X"), reader_make_constant_shell("Cat")), list(reader_make_constant_shell("likesAsFriend"), makeSymbol("?X"), reader_make_constant_shell("DaveS"))), NIL, NIL), makeString("(implies (isa ?X Cat) (likesAsFriend ?X DaveS))")));

    public static SubLObject get_pretty_formatted_string(final SubLObject obj, SubLObject include_constant_reader_prefix, SubLObject wrap_lines) {
        if (include_constant_reader_prefix == UNPROVIDED) {
            include_constant_reader_prefix = NIL;
        }
        if (wrap_lines == UNPROVIDED) {
            wrap_lines = NIL;
        }
        final SubLObject depth = (NIL != wrap_lines) ? ZERO_INTEGER : NIL;
        SubLObject s = format_cycl_expression_to_string(obj, depth);
        if (NIL == include_constant_reader_prefix) {
            s = owlification.strip_hl_artifacts(s);
        }
        s = string_utilities.left_trim_whitespace(s);
        return s;
    }

    // Definitions
    /**
     * Output to STREAM a readable version of EXPRESSION, which is returned.
     * If DEPTH is NIL, then no wrapping is performed.
     */
    @LispMethod(comment = "Output to STREAM a readable version of EXPRESSION, which is returned.\r\nIf DEPTH is NIL, then no wrapping is performed.\nOutput to STREAM a readable version of EXPRESSION, which is returned.\nIf DEPTH is NIL, then no wrapping is performed.")
    public static final SubLObject format_cycl_expression_alt(SubLObject expression, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = StreamsLow.$standard_output$.getDynamicValue();
        }
        if (depth == UNPROVIDED) {
            depth = ZERO_INTEGER;
        }
        return com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_recursive(expression, stream, depth);
    }

    // Definitions
    /**
     * Output to STREAM a readable version of EXPRESSION, which is returned.
     * If DEPTH is NIL, then no wrapping is performed.
     */
    @LispMethod(comment = "Output to STREAM a readable version of EXPRESSION, which is returned.\r\nIf DEPTH is NIL, then no wrapping is performed.\nOutput to STREAM a readable version of EXPRESSION, which is returned.\nIf DEPTH is NIL, then no wrapping is performed.")
    public static SubLObject format_cycl_expression(final SubLObject expression, SubLObject stream, SubLObject depth) {
        if (stream == UNPROVIDED) {
            stream = StreamsLow.$standard_output$.getDynamicValue();
        }
        if (depth == UNPROVIDED) {
            depth = ZERO_INTEGER;
        }
        return format_cycl_expression_recursive(expression, stream, depth);
    }

    /**
     * Output to a string a readable version of EXPRESSION, which is returned.
     * If DEPTH is NIL, then no wrapping is performed.
     *
     * @unknown jantos
     * @unknown done
     */
    @LispMethod(comment = "Output to a string a readable version of EXPRESSION, which is returned.\r\nIf DEPTH is NIL, then no wrapping is performed.\r\n\r\n@unknown jantos\r\n@unknown done\nOutput to a string a readable version of EXPRESSION, which is returned.\nIf DEPTH is NIL, then no wrapping is performed.")
    public static final SubLObject format_cycl_expression_to_string_alt(SubLObject expression, SubLObject depth) {
        if (depth == UNPROVIDED) {
            depth = ZERO_INTEGER;
        }
        {
            SubLObject result = NIL;
            SubLObject stream = NIL;
            try {
                stream = make_private_string_output_stream();
                com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression(expression, stream, depth);
                result = get_output_stream_string(stream);
            } finally {
                {
                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                    try {
                        bind($is_thread_performing_cleanupP$, T);
                        close(stream, UNPROVIDED);
                    } finally {
                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                    }
                }
            }
            return result;
        }
    }

    /**
     * Output to a string a readable version of EXPRESSION, which is returned.
     * If DEPTH is NIL, then no wrapping is performed.
     *
     * @unknown jantos
     * @unknown done
     */
    @LispMethod(comment = "Output to a string a readable version of EXPRESSION, which is returned.\r\nIf DEPTH is NIL, then no wrapping is performed.\r\n\r\n@unknown jantos\r\n@unknown done\nOutput to a string a readable version of EXPRESSION, which is returned.\nIf DEPTH is NIL, then no wrapping is performed.")
    public static SubLObject format_cycl_expression_to_string(final SubLObject expression, SubLObject depth) {
        if (depth == UNPROVIDED) {
            depth = ZERO_INTEGER;
        }
        SubLObject result = NIL;
        SubLObject stream = NIL;
        try {
            stream = make_private_string_output_stream();
            format_cycl_expression(expression, stream, depth);
            result = get_output_stream_string(stream);
        } finally {
            final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
            try {
                bind($is_thread_performing_cleanupP$, T);
                final SubLObject _values = getValuesAsVector();
                close(stream, UNPROVIDED);
                restoreValuesFromVector(_values);
            } finally {
                rebind($is_thread_performing_cleanupP$, _prev_bind_0);
            }
        }
        return result;
    }

    public static final SubLObject format_cycl_expression_recursive_alt(SubLObject expression, SubLObject stream, SubLObject depth) {
        if (expression.isAtom()) {
            com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_atom(expression, stream, depth);
        } else {
            com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_indent(stream, depth);
            princ($str_alt0$_, stream);
            com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_recursive(expression.first(), stream, NIL);
            {
                SubLObject rest = NIL;
                for (rest = expression.rest(); rest.isCons(); rest = rest.rest()) {
                    princ($str_alt1$_, stream);
                    com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_recursive(rest.first(), stream, depth.isInteger() ? ((SubLObject) (add(depth, ONE_INTEGER))) : NIL);
                }
                if (NIL != rest) {
                    princ($str_alt2$___, stream);
                    com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_recursive(rest, stream, depth.isInteger() ? ((SubLObject) (add(depth, ONE_INTEGER))) : NIL);
                }
                princ($str_alt3$_, stream);
            }
        }
        return expression;
    }

    public static SubLObject format_cycl_expression_recursive(final SubLObject expression, final SubLObject stream, final SubLObject depth) {
        if (expression.isAtom()) {
            format_cycl_expression_atom(expression, stream, depth);
        } else {
            format_cycl_expression_indent(stream, depth);
            princ($str1$_, stream);
            format_cycl_expression_recursive(expression.first(), stream, NIL);
            SubLObject rest;
            for (rest = NIL, rest = expression.rest(); rest.isCons(); rest = rest.rest()) {
                princ($str2$_, stream);
                format_cycl_expression_recursive(rest.first(), stream, depth.isInteger() ? add(depth, ONE_INTEGER) : NIL);
            }
            if (NIL != rest) {
                princ($str3$___, stream);
                format_cycl_expression_recursive(rest, stream, depth.isInteger() ? add(depth, ONE_INTEGER) : NIL);
            }
            princ($str4$_, stream);
        }
        return expression;
    }

    public static final SubLObject format_cycl_expression_default_indent_alt(SubLObject stream, SubLObject depth) {
        return indent(stream, multiply(TWO_INTEGER, depth));
    }

    public static SubLObject format_cycl_expression_default_indent(final SubLObject stream, final SubLObject depth) {
        return string_utilities.indent(stream, multiply(TWO_INTEGER, depth));
    }

    public static final SubLObject format_cycl_expression_indent_alt(SubLObject stream, SubLObject depth) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (depth.isInteger()) {
                funcall($format_cycl_expression_newline_method$.getDynamicValue(thread), stream);
                if (depth.numG(ZERO_INTEGER)) {
                    funcall($format_cycl_expression_indent_method$.getDynamicValue(thread), stream, depth);
                }
            }
            return NIL;
        }
    }

    public static SubLObject format_cycl_expression_indent(final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (depth.isInteger()) {
            funcall($format_cycl_expression_newline_method$.getDynamicValue(thread), stream);
            if (depth.numG(ZERO_INTEGER)) {
                funcall($format_cycl_expression_indent_method$.getDynamicValue(thread), stream, depth);
            }
        }
        return NIL;
    }

    public static final SubLObject format_cycl_expression_atom_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        {
            SubLObject method_function = method_func(v_object, $format_cycl_expression_atom_method_table$.getGlobalValue());
            if (NIL != method_function) {
                return funcall(method_function, v_object, stream, depth);
            }
            return com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_default(v_object, stream, depth);
        }
    }

    public static SubLObject format_cycl_expression_atom(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        final SubLObject method_function = method_func(v_object, $format_cycl_expression_atom_method_table$.getGlobalValue());
        if (NIL != method_function) {
            return funcall(method_function, v_object, stream, depth);
        }
        return format_cycl_expression_default(v_object, stream, depth);
    }

    public static final SubLObject format_cycl_expression_default_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        return prin1(v_object, stream);
    }

    public static SubLObject format_cycl_expression_default(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        return prin1(v_object, stream);
    }

    public static final SubLObject format_cycl_expression_atom_constant_method_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != $format_cycl_expression_constant_method$.getDynamicValue(thread)) {
                funcall($format_cycl_expression_constant_method$.getDynamicValue(thread), v_object, stream, depth);
            } else {
                com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_default(v_object, stream, depth);
            }
            return v_object;
        }
    }

    public static SubLObject format_cycl_expression_atom_constant_method(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $format_cycl_expression_constant_method$.getDynamicValue(thread)) {
            funcall($format_cycl_expression_constant_method$.getDynamicValue(thread), v_object, stream, depth);
        } else {
            format_cycl_expression_default(v_object, stream, depth);
        }
        return v_object;
    }

    public static final SubLObject format_cycl_expression_atom_nart_method_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        return com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_nat(v_object, stream, depth);
    }

    public static SubLObject format_cycl_expression_atom_nart_method(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        return format_cycl_expression_nat(v_object, stream, depth);
    }

    public static final SubLObject format_cycl_expression_nat_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != $format_cycl_expression_nat_method$.getDynamicValue(thread)) {
                funcall($format_cycl_expression_nat_method$.getDynamicValue(thread), v_object, stream, depth);
            } else {
                return com.cyc.cycjava.cycl.format_cycl_expression.format_cycl_expression_default(v_object, stream, depth);
            }
            return NIL;
        }
    }

    public static SubLObject format_cycl_expression_nat(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $format_cycl_expression_nat_method$.getDynamicValue(thread)) {
            funcall($format_cycl_expression_nat_method$.getDynamicValue(thread), v_object, stream, depth);
            return NIL;
        }
        return format_cycl_expression_default(v_object, stream, depth);
    }

    public static SubLObject format_cycl_expression_atom_assertion_method(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        return format_cycl_expression_assertion(v_object, stream, depth);
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLString $str_alt0$_ = makeString("(");

    static private final SubLString $str_alt1$_ = makeString(" ");

    static private final SubLString $str_alt2$___ = makeString(" . ");

    public static SubLObject format_cycl_expression_assertion(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $format_cycl_expression_assertion_method$.getDynamicValue(thread)) {
            funcall($format_cycl_expression_assertion_method$.getDynamicValue(thread), v_object, stream, depth);
            return NIL;
        }
        return format_cycl_expression_default(v_object, stream, depth);
    }

    static private final SubLString $str_alt3$_ = makeString(")");

    public static SubLObject declare_format_cycl_expression_file() {
        declareFunction("get_pretty_formatted_string", "GET-PRETTY-FORMATTED-STRING", 1, 2, false);
        declareFunction("format_cycl_expression", "FORMAT-CYCL-EXPRESSION", 1, 2, false);
        declareFunction("format_cycl_expression_to_string", "FORMAT-CYCL-EXPRESSION-TO-STRING", 1, 1, false);
        declareFunction("format_cycl_expression_recursive", "FORMAT-CYCL-EXPRESSION-RECURSIVE", 3, 0, false);
        declareFunction("format_cycl_expression_default_indent", "FORMAT-CYCL-EXPRESSION-DEFAULT-INDENT", 2, 0, false);
        declareFunction("format_cycl_expression_indent", "FORMAT-CYCL-EXPRESSION-INDENT", 2, 0, false);
        declareFunction("format_cycl_expression_atom", "FORMAT-CYCL-EXPRESSION-ATOM", 3, 0, false);
        declareFunction("format_cycl_expression_default", "FORMAT-CYCL-EXPRESSION-DEFAULT", 3, 0, false);
        declareFunction("format_cycl_expression_atom_constant_method", "FORMAT-CYCL-EXPRESSION-ATOM-CONSTANT-METHOD", 3, 0, false);
        declareFunction("format_cycl_expression_atom_nart_method", "FORMAT-CYCL-EXPRESSION-ATOM-NART-METHOD", 3, 0, false);
        declareFunction("format_cycl_expression_nat", "FORMAT-CYCL-EXPRESSION-NAT", 3, 0, false);
        declareFunction("format_cycl_expression_atom_assertion_method", "FORMAT-CYCL-EXPRESSION-ATOM-ASSERTION-METHOD", 3, 0, false);
        declareFunction("format_cycl_expression_assertion", "FORMAT-CYCL-EXPRESSION-ASSERTION", 3, 0, false);
        return NIL;
    }

    public static SubLObject init_format_cycl_expression_file() {
        defparameter("*FORMAT-CYCL-EXPRESSION-NEWLINE-METHOD*", TERPRI);
        defparameter("*FORMAT-CYCL-EXPRESSION-INDENT-METHOD*", FORMAT_CYCL_EXPRESSION_DEFAULT_INDENT);
        deflexical("*FORMAT-CYCL-EXPRESSION-ATOM-METHOD-TABLE*", make_vector($int$256, NIL));
        defparameter("*FORMAT-CYCL-EXPRESSION-CONSTANT-METHOD*", NIL);
        defparameter("*FORMAT-CYCL-EXPRESSION-NAT-METHOD*", NIL);
        defparameter("*FORMAT-CYCL-EXPRESSION-ASSERTION-METHOD*", NIL);
        return NIL;
    }

    public static SubLObject setup_format_cycl_expression_file() {
        register_external_symbol(GET_PRETTY_FORMATTED_STRING);
        register_method($format_cycl_expression_atom_method_table$.getGlobalValue(), $dtp_constant$.getGlobalValue(), symbol_function(FORMAT_CYCL_EXPRESSION_ATOM_CONSTANT_METHOD));
        register_method($format_cycl_expression_atom_method_table$.getGlobalValue(), nart_handles.$dtp_nart$.getGlobalValue(), symbol_function(FORMAT_CYCL_EXPRESSION_ATOM_NART_METHOD));
        register_method($format_cycl_expression_atom_method_table$.getGlobalValue(), assertion_handles.$dtp_assertion$.getGlobalValue(), symbol_function(FORMAT_CYCL_EXPRESSION_ATOM_ASSERTION_METHOD));
        define_test_case_table_int(GET_PRETTY_FORMATTED_STRING, list(new SubLObject[]{ $TEST, NIL, $OWNER, NIL, $CLASSES, NIL, $KB, $FULL, $WORKING_, T }), $list17);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_format_cycl_expression_file();
    }

    @Override
    public void initializeVariables() {
        init_format_cycl_expression_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_format_cycl_expression_file();
    }

    static {
    }
}

/**
 * Total time: 134 ms
 */
