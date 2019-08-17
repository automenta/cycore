/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.defmacro_obsolete_warning;
import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.access_macros.register_macro_helper;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.make_symbol;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.put;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.current_process;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.kill_process;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.process_name;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.sleep;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.valid_process_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.ensure_current_process_timeout_is_cancelled;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.schedule_current_process_for_timeout;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.listp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
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
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Semaphores;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class subl_macro_promotions extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new subl_macro_promotions();

 public static final String myName = "com.cyc.cycjava.cycl.subl_macro_promotions";


    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $within_with_timeout$ = makeSymbol("*WITHIN-WITH-TIMEOUT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $with_timeout_nesting_depth$ = makeSymbol("*WITH-TIMEOUT-NESTING-DEPTH*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list0 = list(makeSymbol("VARIABLE"), makeSymbol("&OPTIONAL"), makeSymbol("INITIALIZATION"), makeSymbol("DOCUMENTATION"));

    private static final SubLSymbol DECLARE_DEFGLOBAL = makeSymbol("DECLARE-DEFGLOBAL");

    public static final SubLSymbol $catch_error_message_target$ = makeSymbol("*CATCH-ERROR-MESSAGE-TARGET*");

    static private final SubLString $$$ERROR = makeString("ERROR");

    static private final SubLList $list11 = list(list(makeSymbol("VAR")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list14 = list(QUOTE, makeSymbol("CATCH-ERROR-MESSAGE-HANDLER"));

    static private final SubLList $list15 = list(list(makeSymbol("TIME"), makeSymbol("TIMED-OUT-VAR")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol WITH_TIMEOUT_VIA_INTERRUPT = makeSymbol("WITH-TIMEOUT-VIA-INTERRUPT");

    private static final SubLSymbol $sym17$TAG = makeUninternedSymbol("TAG");

    private static final SubLSymbol $sym18$TIMER = makeUninternedSymbol("TIMER");

    static private final SubLList $list20 = list(list(makeSymbol("WITH-TIMEOUT-MAKE-TAG")));

    private static final SubLList $list21 = list(makeSymbol("*WITHIN-WITH-TIMEOUT*"), T);

    private static final SubLList $list23 = list(list(makeSymbol("*WITH-TIMEOUT-NESTING-DEPTH*"), list(makeSymbol("+"), ONE_INTEGER, makeSymbol("*WITH-TIMEOUT-NESTING-DEPTH*"))));

    private static final SubLSymbol WITH_TIMEOUT_START_TIMER = makeSymbol("WITH-TIMEOUT-START-TIMER");

    private static final SubLSymbol WITH_TIMEOUT_STOP_TIMER = makeSymbol("WITH-TIMEOUT-STOP-TIMER");

    private static final SubLSymbol WITH_TIMEOUT_MAKE_TAG = makeSymbol("WITH-TIMEOUT-MAKE-TAG");

    private static final SubLSymbol WITH_TIMEOUT_THROW = makeSymbol("WITH-TIMEOUT-THROW");

    private static final SubLList $list30 = list(list(makeSymbol("BI-STREAM"), makeSymbol("HOST"), makeSymbol("PORT"), makeSymbol("&KEY"), makeSymbol("TIMEOUT"), list(makeSymbol("ACCESS-MODE"), makeKeyword("PUBLIC"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list31 = list(makeKeyword("TIMEOUT"), makeKeyword("ACCESS-MODE"));

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    private static final SubLSymbol OPEN_TCP_STREAM_WITH_TIMEOUT = makeSymbol("OPEN-TCP-STREAM-WITH-TIMEOUT");

    private static final SubLSymbol VALIDATE_TCP_CONNECTION = makeSymbol("VALIDATE-TCP-CONNECTION");

    private static final SubLSymbol WITH_TCP_CONNECTION_WITH_TIMEOUT = makeSymbol("WITH-TCP-CONNECTION-WITH-TIMEOUT");

    private static final SubLList $list41 = list(makeSymbol("WITH-TCP-CONNECTION"));

    private static final SubLString $str43$Connection_to__A__A_timed_out_ = makeString("Connection to ~A:~A timed out.");

    static private final SubLList $list44 = list(makeSymbol("VARIABLES"), makeSymbol("EXPRESSION"));

    private static final SubLList $list48 = list(makeSymbol("VARIABLES"), makeSymbol("EXPRESSION"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol WITH_SPACE_PROFILING = makeSymbol("WITH-SPACE-PROFILING");

    static private final SubLList $list50 = list(list(makeSymbol("&KEY"), list(makeSymbol("STREAM"), list(QUOTE, makeSymbol("*STANDARD-OUTPUT*")))), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list51 = list(makeKeyword("STREAM"));

    private static final SubLSymbol $standard_output$ = makeSymbol("*STANDARD-OUTPUT*");

    private static final SubLSymbol WITH_SPACE_PROFILING_SL2C = makeSymbol("WITH-SPACE-PROFILING-SL2C");

    private static final SubLSymbol $sym55$SPACE_INFO = makeUninternedSymbol("SPACE-INFO");

    private static final SubLList $list56 = list(list(makeSymbol("ADD-SPACE-PROBE", "SUBLISP")));

    private static final SubLSymbol REMOVE_SPACE_PROBE = makeSymbol("REMOVE-SPACE-PROBE", "SUBLISP");

    private static final SubLSymbol $sym58$_INTERPRET_CSPACE_RESULTS = makeSymbol("%INTERPRET-CSPACE-RESULTS", "SUBLISP");

    private static final SubLList $list59 = list(makeSymbol("COMMENT-STRING"));

    static private final SubLList $list61 = list(makeSymbol("PROGN"));

    // Definitions
    /**
     * declare VARIABLE as a lexical variable that is initialized from the world like defvar.
     */
    @LispMethod(comment = "declare VARIABLE as a lexical variable that is initialized from the world like defvar.")
    public static final SubLObject defglobal_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject variable = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt0);
            variable = current.first();
            current = current.rest();
            {
                SubLObject initialization = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                destructuring_bind_must_listp(current, datum, $list_alt0);
                current = current.rest();
                {
                    SubLObject documentation = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                    destructuring_bind_must_listp(current, datum, $list_alt0);
                    current = current.rest();
                    if (NIL == current) {
                        return list(PROGN, listS(DEFLEXICAL, variable, list(FIF, list(BOUNDP, list(QUOTE, variable)), variable, initialization), append(NIL != documentation ? ((SubLObject) (list(documentation))) : NIL, NIL)), list(DECLARE_DEFGLOBAL, list(QUOTE, variable)));
                    } else {
                        cdestructuring_bind_error(datum, $list_alt0);
                    }
                }
            }
        }
        return NIL;
    }

    // Definitions
    /**
     * declare VARIABLE as a lexical variable that is initialized from the world like defvar.
     */
    @LispMethod(comment = "declare VARIABLE as a lexical variable that is initialized from the world like defvar.")
    public static SubLObject defglobal(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject variable = NIL;
        destructuring_bind_must_consp(current, datum, $list0);
        variable = current.first();
        current = current.rest();
        final SubLObject initialization = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list0);
        current = current.rest();
        final SubLObject documentation = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list0);
        current = current.rest();
        if (NIL == current) {
            return list(PROGN, listS(DEFLEXICAL, variable, list(FIF, list(BOUNDP, list(QUOTE, variable)), variable, initialization), append(NIL != documentation ? list(documentation) : NIL, NIL)), list(DECLARE_DEFGLOBAL, list(QUOTE, variable)));
        }
        cdestructuring_bind_error(datum, $list0);
        return NIL;
    }

    /**
     *
     *
     * @unknown we would love for this to be a macro-helper, but define-macro-helper
    isn't available until ACCESS-MACROS
     */
    @LispMethod(comment = "@unknown we would love for this to be a macro-helper, but define-macro-helper\r\nisn\'t available until ACCESS-MACROS")
    public static final SubLObject declare_defglobal_alt(SubLObject global) {
        put(global, $INITIALIZATION_TYPE, $WORLD);
        return global;
    }

    /**
     *
     *
     * @unknown we would love for this to be a macro-helper, but define-macro-helper
    isn't available until ACCESS-MACROS
     */
    @LispMethod(comment = "@unknown we would love for this to be a macro-helper, but define-macro-helper\r\nisn\'t available until ACCESS-MACROS")
    public static SubLObject declare_defglobal(final SubLObject global) {
        put(global, $INITIALIZATION_TYPE, $WORLD);
        return global;
    }

    /**
     * Execute BODY as an implicit progn.  If any error occurs, the body is aborted
     * and the variable VAR is set to a string representation of the error.
     */
    @LispMethod(comment = "Execute BODY as an implicit progn.  If any error occurs, the body is aborted\r\nand the variable VAR is set to a string representation of the error.\nExecute BODY as an implicit progn.  If any error occurs, the body is aborted\nand the variable VAR is set to a string representation of the error.")
    public static final SubLObject catch_error_message_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt11);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt11);
                    var = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return list(CCATCH, $catch_error_message_target$, var, listS(WITH_ERROR_HANDLER, $list_alt14, append(body, NIL)));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt11);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Execute BODY as an implicit progn.  If any error occurs, the body is aborted
     * and the variable VAR is set to a string representation of the error.
     */
    @LispMethod(comment = "Execute BODY as an implicit progn.  If any error occurs, the body is aborted\r\nand the variable VAR is set to a string representation of the error.\nExecute BODY as an implicit progn.  If any error occurs, the body is aborted\nand the variable VAR is set to a string representation of the error.")
    public static SubLObject catch_error_message(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list11);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject var = NIL;
        destructuring_bind_must_consp(current, datum, $list11);
        var = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return list(CCATCH, $catch_error_message_target$, var, listS(WITH_ERROR_HANDLER, $list14, append(body, NIL)));
        }
        cdestructuring_bind_error(datum, $list11);
        return NIL;
    }

    /**
     * Internal function for CATCH-ERROR-MESSAGE
     */
    @LispMethod(comment = "Internal function for CATCH-ERROR-MESSAGE")
    public static final SubLObject catch_error_message_handler_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return sublisp_throw($catch_error_message_target$.getGlobalValue(), Errors.$error_message$.getDynamicValue(thread));
        }
    }

    /**
     * Internal function for CATCH-ERROR-MESSAGE
     */
    @LispMethod(comment = "Internal function for CATCH-ERROR-MESSAGE")
    public static SubLObject catch_error_message_handler() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return sublisp_throw($catch_error_message_target$.getGlobalValue(), Errors.$error_message$.getDynamicValue(thread));
    }

    /**
     * Attempt to evaluate BODY.
     * When TIME is non-NIL, if the evaluation does not complete after TIME seconds,
     * abort the evaluation and set TIMED-OUT-VAR to T.
     *
     * @param TIME
     * 		nil or numberp
     */
    @LispMethod(comment = "Attempt to evaluate BODY.\r\nWhen TIME is non-NIL, if the evaluation does not complete after TIME seconds,\r\nabort the evaluation and set TIMED-OUT-VAR to T.\r\n\r\n@param TIME\r\n\t\tnil or numberp\nAttempt to evaluate BODY.\nWhen TIME is non-NIL, if the evaluation does not complete after TIME seconds,\nabort the evaluation and set TIMED-OUT-VAR to T.")
    public static final SubLObject with_timeout_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt15);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject time = NIL;
                    SubLObject timed_out_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt15);
                    time = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt15);
                    timed_out_var = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return with_timeout_internal(time, timed_out_var, body);
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt15);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Attempt to evaluate BODY.
     * When TIME is non-NIL, if the evaluation does not complete after TIME seconds,
     * abort the evaluation and set TIMED-OUT-VAR to T.
     *
     * @param TIME
     * 		nil or numberp
     */
    @LispMethod(comment = "Attempt to evaluate BODY.\r\nWhen TIME is non-NIL, if the evaluation does not complete after TIME seconds,\r\nabort the evaluation and set TIMED-OUT-VAR to T.\r\n\r\n@param TIME\r\n\t\tnil or numberp\nAttempt to evaluate BODY.\nWhen TIME is non-NIL, if the evaluation does not complete after TIME seconds,\nabort the evaluation and set TIMED-OUT-VAR to T.")
    public static SubLObject with_timeout(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list15);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject time = NIL;
        SubLObject timed_out_var = NIL;
        destructuring_bind_must_consp(current, datum, $list15);
        time = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list15);
        timed_out_var = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return listS(WITH_TIMEOUT_VIA_INTERRUPT, list(time, timed_out_var), append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list15);
        return NIL;
    }

    public static SubLObject with_timeout_via_interrupt(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list15);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject time = NIL;
        SubLObject timed_out_var = NIL;
        destructuring_bind_must_consp(current, datum, $list15);
        time = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list15);
        timed_out_var = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return with_timeout_internal(time, timed_out_var, body);
        }
        cdestructuring_bind_error(datum, $list15);
        return NIL;
    }

    public static final SubLObject with_timeout_internal_alt(SubLObject time, SubLObject timed_out_var, SubLObject body) {
        {
            SubLObject tag = $sym16$TAG;
            SubLObject timer = $sym17$TIMER;
            return list(CLET, list(bq_cons(tag, $list_alt19)), list(CCATCH, tag, timed_out_var, list(CLET, list($list_alt20, timer), list(CUNWIND_PROTECT, listS(CLET, $list_alt22, list(CSETQ, timer, list(WITH_TIMEOUT_START_TIMER, time, tag)), append(body, NIL)), list(WITH_TIMEOUT_STOP_TIMER, timer)))));
        }
    }

    public static SubLObject with_timeout_internal(final SubLObject time, final SubLObject timed_out_var, final SubLObject body) {
        final SubLObject tag = $sym17$TAG;
        final SubLObject timer = $sym18$TIMER;
        return list(CLET, list(bq_cons(tag, $list20)), list(CCATCH, tag, timed_out_var, list(CLET, list($list21, timer), list(CUNWIND_PROTECT, listS(CLET, $list23, list(CSETQ, timer, list(WITH_TIMEOUT_START_TIMER, time, tag)), append(body, NIL)), list(WITH_TIMEOUT_STOP_TIMER, timer)))));
    }

    public static final SubLObject with_timeout_make_tag_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return cons(WITH_TIMEOUT, $with_timeout_nesting_depth$.getDynamicValue(thread));
        }
    }

    public static SubLObject with_timeout_make_tag() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return cons(WITH_TIMEOUT, $with_timeout_nesting_depth$.getDynamicValue(thread));
    }

    /**
     * Start a new timer process that will interrupt the current process by
     * throwing to TAG after TIME seconds.
     */
    @LispMethod(comment = "Start a new timer process that will interrupt the current process by\r\nthrowing to TAG after TIME seconds.\nStart a new timer process that will interrupt the current process by\nthrowing to TAG after TIME seconds.")
    public static final SubLObject with_timeout_start_timer_alt(SubLObject time, SubLObject tag) {
        if (NIL == subl_promotions.non_negative_integer_p(time)) {
            return NIL;
        }
        {
            SubLObject timeout_name = format(NIL, $str_alt28$WITH_TIMEOUT__A, timeout_tag_nesting_depth(tag));
            SubLObject client_semaphore = Semaphores.new_semaphore(cconcatenate(timeout_name, $str_alt29$_client), UNPROVIDED);
            SubLObject timer_semaphore = Semaphores.new_semaphore(cconcatenate(timeout_name, $str_alt30$_timer), UNPROVIDED);
            SubLObject timer = subl_promotions.make_process_with_args(cconcatenate(timeout_name, new SubLObject[]{ $str_alt31$_Timer_for_, process_name(current_process()) }), WITH_TIMEOUT_TIMER_THREAD, list(current_process(), time, tag, client_semaphore, timer_semaphore));
            Semaphores.semaphore_signal(client_semaphore);
            Semaphores.semaphore_wait(timer_semaphore);
            return timer;
        }
    }

    /**
     * Start a new timer process that will interrupt the current process by
     * throwing to TAG after TIME seconds.
     */
    @LispMethod(comment = "Start a new timer process that will interrupt the current process by\r\nthrowing to TAG after TIME seconds.\nStart a new timer process that will interrupt the current process by\nthrowing to TAG after TIME seconds.")
    public static SubLObject with_timeout_start_timer(final SubLObject time, final SubLObject tag) {
        schedule_current_process_for_timeout(tag, time);
        return tag;
    }

    public static final SubLObject timeout_tag_nesting_depth_alt(SubLObject tag) {
        return tag.rest();
    }

    public static SubLObject timeout_tag_nesting_depth(final SubLObject tag) {
        return tag.rest();
    }

    /**
     * Stop the TIMER process, which is no longer needed.
     */
    @LispMethod(comment = "Stop the TIMER process, which is no longer needed.")
    public static final SubLObject with_timeout_stop_timer_alt(SubLObject timer) {
        if (NIL != valid_process_p(timer)) {
            return kill_process(timer);
        }
        return NIL;
    }

    /**
     * Stop the TIMER process, which is no longer needed.
     */
    @LispMethod(comment = "Stop the TIMER process, which is no longer needed.")
    public static SubLObject with_timeout_stop_timer(final SubLObject timer) {
        ensure_current_process_timeout_is_cancelled();
        return timer;
    }

    /**
     * Signal a timeout to TAG in CLIENT process after TIME seconds.
     */
    @LispMethod(comment = "Signal a timeout to TAG in CLIENT process after TIME seconds.")
    public static final SubLObject with_timeout_timer_thread_alt(SubLObject client, SubLObject time, SubLObject tag, SubLObject client_semaphore, SubLObject timer_semaphore) {
        Semaphores.semaphore_signal(timer_semaphore);
        Semaphores.semaphore_wait(client_semaphore);
        sleep(time);
        with_timeout_signal_timeout(client, tag);
        return NIL;
    }

    /**
     * Signal a timeout to TAG in CLIENT process after TIME seconds.
     */
    @LispMethod(comment = "Signal a timeout to TAG in CLIENT process after TIME seconds.")
    public static SubLObject with_timeout_timer_thread(final SubLObject client, final SubLObject time, final SubLObject tag, final SubLObject client_semaphore, final SubLObject timer_semaphore) {
        Semaphores.semaphore_signal(timer_semaphore);
        Semaphores.semaphore_wait(client_semaphore);
        sleep(time);
        with_timeout_signal_timeout(client, tag);
        return NIL;
    }

    public static final SubLObject with_timeout_signal_timeout_alt(SubLObject client, SubLObject tag) {
        if (NIL != valid_process_p(client)) {
            return subl_promotions.interrupt_process_with_args(client, WITH_TIMEOUT_THROW, list(tag));
        }
        return NIL;
    }

    public static SubLObject with_timeout_signal_timeout(final SubLObject client, final SubLObject tag) {
        if (NIL != valid_process_p(client)) {
            return subl_promotions.interrupt_process_with_args(client, WITH_TIMEOUT_THROW, list(tag));
        }
        return NIL;
    }

    public static final SubLObject with_timeout_throw_alt(SubLObject tag) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != $within_with_timeout$.getDynamicValue(thread)) {
                sublisp_throw(tag, T);
            }
            return NIL;
        }
    }

    public static SubLObject with_timeout_throw(final SubLObject tag) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $within_with_timeout$.getDynamicValue(thread)) {
            sublisp_throw(tag, T);
        }
        return NIL;
    }

    /**
     * Evaluate BODY with BI-STREAM open to a TCP connection to PORT on HOST.
     * TIMEOUT, if non-nil, is an integer connection timeout in seconds.
     * If ACCESS-MODE is :PRIVATE, then the stream is assumed to not be shared with anyone.
     */
    @LispMethod(comment = "Evaluate BODY with BI-STREAM open to a TCP connection to PORT on HOST.\r\nTIMEOUT, if non-nil, is an integer connection timeout in seconds.\r\nIf ACCESS-MODE is :PRIVATE, then the stream is assumed to not be shared with anyone.\nEvaluate BODY with BI-STREAM open to a TCP connection to PORT on HOST.\nTIMEOUT, if non-nil, is an integer connection timeout in seconds.\nIf ACCESS-MODE is :PRIVATE, then the stream is assumed to not be shared with anyone.")
    public static final SubLObject with_tcp_connection_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt34);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject bi_stream = NIL;
                    SubLObject host = NIL;
                    SubLObject port = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    bi_stream = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    host = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    port = current.first();
                    current = current.rest();
                    {
                        SubLObject allow_other_keys_p = NIL;
                        SubLObject rest = current;
                        SubLObject bad = NIL;
                        SubLObject current_1 = NIL;
                        for (; NIL != rest;) {
                            destructuring_bind_must_consp(rest, datum, $list_alt34);
                            current_1 = rest.first();
                            rest = rest.rest();
                            destructuring_bind_must_consp(rest, datum, $list_alt34);
                            if (NIL == member(current_1, $list_alt35, UNPROVIDED, UNPROVIDED)) {
                                bad = T;
                            }
                            if (current_1 == $ALLOW_OTHER_KEYS) {
                                allow_other_keys_p = rest.first();
                            }
                            rest = rest.rest();
                        }
                        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                            cdestructuring_bind_error(datum, $list_alt34);
                        }
                        {
                            SubLObject timeout_tail = property_list_member($TIMEOUT, current);
                            SubLObject timeout = (NIL != timeout_tail) ? ((SubLObject) (cadr(timeout_tail))) : NIL;
                            SubLObject access_mode_tail = property_list_member($ACCESS_MODE, current);
                            SubLObject access_mode = (NIL != access_mode_tail) ? ((SubLObject) (cadr(access_mode_tail))) : $PUBLIC;
                            current = temp;
                            {
                                SubLObject body = current;
                                return list(CLET, list(bi_stream), list(CUNWIND_PROTECT, list(PROGN, list(CSETQ, bi_stream, list(OPEN_TCP_STREAM_WITH_TIMEOUT, host, port, timeout, access_mode)), listS(PWHEN, bi_stream, append(body, NIL))), list(PWHEN, bi_stream, list(CLOSE, bi_stream))));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Evaluate BODY with BI-STREAM open to a TCP connection to PORT on HOST.
     * TIMEOUT, if non-nil, is an integer connection timeout in seconds.
     * If ACCESS-MODE is :PRIVATE, then the stream is assumed to not be shared with anyone.
     */
    @LispMethod(comment = "Evaluate BODY with BI-STREAM open to a TCP connection to PORT on HOST.\r\nTIMEOUT, if non-nil, is an integer connection timeout in seconds.\r\nIf ACCESS-MODE is :PRIVATE, then the stream is assumed to not be shared with anyone.\nEvaluate BODY with BI-STREAM open to a TCP connection to PORT on HOST.\nTIMEOUT, if non-nil, is an integer connection timeout in seconds.\nIf ACCESS-MODE is :PRIVATE, then the stream is assumed to not be shared with anyone.")
    public static SubLObject with_tcp_connection(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject bi_stream = NIL;
        SubLObject host = NIL;
        SubLObject port = NIL;
        destructuring_bind_must_consp(current, datum, $list30);
        bi_stream = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        host = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        port = current.first();
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$1 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list30);
            current_$1 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list30);
            if (NIL == member(current_$1, $list31, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$1 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list30);
        }
        final SubLObject timeout_tail = property_list_member($TIMEOUT, current);
        final SubLObject timeout = (NIL != timeout_tail) ? cadr(timeout_tail) : NIL;
        final SubLObject access_mode_tail = property_list_member($ACCESS_MODE, current);
        final SubLObject access_mode = (NIL != access_mode_tail) ? cadr(access_mode_tail) : $PUBLIC;
        final SubLObject body;
        current = body = temp;
        return list(CLET, list(bi_stream), list(CUNWIND_PROTECT, list(PROGN, list(CSETQ, bi_stream, list(OPEN_TCP_STREAM_WITH_TIMEOUT, host, port, timeout, access_mode)), listS(PWHEN, list(VALIDATE_TCP_CONNECTION, bi_stream, host, port), append(body, NIL))), list(PWHEN, bi_stream, list(CLOSE, bi_stream))));
    }

    public static final SubLObject with_tcp_connection_with_timeout_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt34);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject bi_stream = NIL;
                    SubLObject host = NIL;
                    SubLObject port = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    bi_stream = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    host = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt34);
                    port = current.first();
                    current = current.rest();
                    {
                        SubLObject allow_other_keys_p = NIL;
                        SubLObject rest = current;
                        SubLObject bad = NIL;
                        SubLObject current_2 = NIL;
                        for (; NIL != rest;) {
                            destructuring_bind_must_consp(rest, datum, $list_alt34);
                            current_2 = rest.first();
                            rest = rest.rest();
                            destructuring_bind_must_consp(rest, datum, $list_alt34);
                            if (NIL == member(current_2, $list_alt35, UNPROVIDED, UNPROVIDED)) {
                                bad = T;
                            }
                            if (current_2 == $ALLOW_OTHER_KEYS) {
                                allow_other_keys_p = rest.first();
                            }
                            rest = rest.rest();
                        }
                        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                            cdestructuring_bind_error(datum, $list_alt34);
                        }
                        {
                            SubLObject timeout_tail = property_list_member($TIMEOUT, current);
                            SubLObject timeout = (NIL != timeout_tail) ? ((SubLObject) (cadr(timeout_tail))) : NIL;
                            SubLObject access_mode_tail = property_list_member($ACCESS_MODE, current);
                            SubLObject access_mode = (NIL != access_mode_tail) ? ((SubLObject) (cadr(access_mode_tail))) : $PUBLIC;
                            current = temp;
                            {
                                SubLObject body = current;
                                defmacro_obsolete_warning(WITH_TCP_CONNECTION_WITH_TIMEOUT, $list_alt44);
                                return listS(WITH_TCP_CONNECTION, list(bi_stream, host, port, $TIMEOUT, timeout, $ACCESS_MODE, access_mode), append(body, NIL));
                            }
                        }
                    }
                }
            }
        }
    }

    public static SubLObject with_tcp_connection_with_timeout(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject bi_stream = NIL;
        SubLObject host = NIL;
        SubLObject port = NIL;
        destructuring_bind_must_consp(current, datum, $list30);
        bi_stream = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        host = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list30);
        port = current.first();
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$2 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list30);
            current_$2 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list30);
            if (NIL == member(current_$2, $list31, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$2 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list30);
        }
        final SubLObject timeout_tail = property_list_member($TIMEOUT, current);
        final SubLObject timeout = (NIL != timeout_tail) ? cadr(timeout_tail) : NIL;
        final SubLObject access_mode_tail = property_list_member($ACCESS_MODE, current);
        final SubLObject access_mode = (NIL != access_mode_tail) ? cadr(access_mode_tail) : $PUBLIC;
        final SubLObject body;
        current = body = temp;
        defmacro_obsolete_warning(WITH_TCP_CONNECTION_WITH_TIMEOUT, $list41);
        return listS(WITH_TCP_CONNECTION, list(bi_stream, host, port, $TIMEOUT, timeout, $ACCESS_MODE, access_mode), append(body, NIL));
    }

    public static SubLObject validate_tcp_connection(final SubLObject channel, final SubLObject host, final SubLObject port) {
        if (NIL == channel) {
            return Errors.error($str43$Connection_to__A__A_timed_out_, host, port);
        }
        return T;
    }

    /**
     * Evaluates EXPRESSION and set VARIABLES to the corresponding values returned.
     * If there are fewer variables than values, the additional values are discarded.
     * If there are more variables than values, the additional variables are set to NIL.
     */
    @LispMethod(comment = "Evaluates EXPRESSION and set VARIABLES to the corresponding values returned.\r\nIf there are fewer variables than values, the additional values are discarded.\r\nIf there are more variables than values, the additional variables are set to NIL.\nEvaluates EXPRESSION and set VARIABLES to the corresponding values returned.\nIf there are fewer variables than values, the additional values are discarded.\nIf there are more variables than values, the additional variables are set to NIL.")
    public static final SubLObject cmultiple_value_setq_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject v_variables = NIL;
            SubLObject expression = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt46);
            v_variables = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt46);
            expression = current.first();
            current = current.rest();
            if (NIL == current) {
                SubLTrampolineFile.checkType(v_variables, LISTP);
                if (NIL == v_variables) {
                    return list(PROGN, expression);
                }
                {
                    SubLObject parallel_variables = NIL;
                    SubLObject variable_assignments = NIL;
                    SubLObject cdolist_list_var = v_variables;
                    SubLObject variable = NIL;
                    for (variable = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , variable = cdolist_list_var.first()) {
                        variable_assignments = cons(variable, variable_assignments);
                        {
                            SubLObject parallel_variable = meta_macros.make_temp_var(variable);
                            parallel_variables = cons(parallel_variable, parallel_variables);
                            variable_assignments = cons(parallel_variable, variable_assignments);
                        }
                    }
                    return list(CMULTIPLE_VALUE_BIND, nreverse(parallel_variables), expression, bq_cons(CSETQ, append(nreverse(variable_assignments), NIL)));
                }
            } else {
                cdestructuring_bind_error(datum, $list_alt46);
            }
        }
        return NIL;
    }

    /**
     * Evaluates EXPRESSION and set VARIABLES to the corresponding values returned.
     * If there are fewer variables than values, the additional values are discarded.
     * If there are more variables than values, the additional variables are set to NIL.
     */
    @LispMethod(comment = "Evaluates EXPRESSION and set VARIABLES to the corresponding values returned.\r\nIf there are fewer variables than values, the additional values are discarded.\r\nIf there are more variables than values, the additional variables are set to NIL.\nEvaluates EXPRESSION and set VARIABLES to the corresponding values returned.\nIf there are fewer variables than values, the additional values are discarded.\nIf there are more variables than values, the additional variables are set to NIL.")
    public static SubLObject cmultiple_value_setq(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject v_variables = NIL;
        SubLObject expression = NIL;
        destructuring_bind_must_consp(current, datum, $list44);
        v_variables = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list44);
        expression = current.first();
        current = current.rest();
        if (NIL != current) {
            cdestructuring_bind_error(datum, $list44);
            return NIL;
        }
        assert NIL != listp(v_variables) : "! listp(v_variables) " + ("Types.listp(v_variables) " + "CommonSymbols.NIL != Types.listp(v_variables) ") + v_variables;
        if (NIL == v_variables) {
            return list(PROGN, expression);
        }
        SubLObject parallel_variables = NIL;
        SubLObject variable_assignments = NIL;
        SubLObject cdolist_list_var = v_variables;
        SubLObject variable = NIL;
        variable = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            variable_assignments = cons(variable, variable_assignments);
            final SubLObject parallel_variable = meta_macros.make_temp_var(variable);
            parallel_variables = cons(parallel_variable, parallel_variables);
            variable_assignments = cons(parallel_variable, variable_assignments);
            cdolist_list_var = cdolist_list_var.rest();
            variable = cdolist_list_var.first();
        } 
        return list(CMULTIPLE_VALUE_BIND, nreverse(parallel_variables), expression, bq_cons(CSETQ, append(nreverse(variable_assignments), NIL)));
    }

    public static final SubLObject with_space_profiling_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt52);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject allow_other_keys_p = NIL;
                    SubLObject rest = current;
                    SubLObject bad = NIL;
                    SubLObject current_3 = NIL;
                    for (; NIL != rest;) {
                        destructuring_bind_must_consp(rest, datum, $list_alt52);
                        current_3 = rest.first();
                        rest = rest.rest();
                        destructuring_bind_must_consp(rest, datum, $list_alt52);
                        if (NIL == member(current_3, $list_alt53, UNPROVIDED, UNPROVIDED)) {
                            bad = T;
                        }
                        if (current_3 == $ALLOW_OTHER_KEYS) {
                            allow_other_keys_p = rest.first();
                        }
                        rest = rest.rest();
                    }
                    if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                        cdestructuring_bind_error(datum, $list_alt52);
                    }
                    {
                        SubLObject stream_tail = property_list_member($STREAM, current);
                        SubLObject stream = (NIL != stream_tail) ? ((SubLObject) (cadr(stream_tail))) : $standard_output$;
                        current = temp;
                        {
                            SubLObject body = current;
                            return with_space_profiling_sl2c(stream, body);
                        }
                    }
                }
            }
        }
    }

    public static SubLObject with_space_profiling(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list50);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$3 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list50);
            current_$3 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list50);
            if (NIL == member(current_$3, $list51, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$3 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list50);
        }
        final SubLObject stream_tail = property_list_member($STREAM, current);
        final SubLObject stream = (NIL != stream_tail) ? cadr(stream_tail) : $standard_output$;
        final SubLObject body;
        current = body = temp;
        return with_space_profiling_sl2c(stream, body);
    }

    public static final SubLObject with_space_profiling_sl2c_alt(SubLObject stream, SubLObject body) {
        {
            SubLObject space_info = $sym57$SPACE_INFO;
            return list(CLET, list(space_info), list(CUNWIND_PROTECT, list(PROGN, listS(CSETQ, space_info, $list_alt58), bq_cons(PROGN, append(body, NIL))), list(PWHEN, space_info, list(REMOVE_SPACE_PROBE, space_info), list($sym60$_INTERPRET_CSPACE_RESULTS, space_info, stream))));
        }
    }

    public static SubLObject with_space_profiling_sl2c(final SubLObject stream, final SubLObject body) {
        final SubLObject space_info = $sym55$SPACE_INFO;
        return list(CLET, list(space_info), list(CUNWIND_PROTECT, list(PROGN, listS(CSETQ, space_info, $list56), bq_cons(PROGN, append(body, NIL))), list(PWHEN, space_info, list(REMOVE_SPACE_PROBE, space_info), list($sym58$_INTERPRET_CSPACE_RESULTS, space_info, stream))));
    }

    /**
     * Express the comment COMMENT-STRING as a form rather than via the ; or #| |# reader macros.
     */
    @LispMethod(comment = "Express the comment COMMENT-STRING as a form rather than via the ; or #| |# reader macros.")
    public static final SubLObject code_comment_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject comment_string = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt61);
            comment_string = current.first();
            current = current.rest();
            if (NIL == current) {
                SubLTrampolineFile.checkType(comment_string, STRINGP);
                return $list_alt63;
            } else {
                cdestructuring_bind_error(datum, $list_alt61);
            }
        }
        return NIL;
    }

    /**
     * Express the comment COMMENT-STRING as a form rather than via the ; or #| |# reader macros.
     */
    @LispMethod(comment = "Express the comment COMMENT-STRING as a form rather than via the ; or #| |# reader macros.")
    public static SubLObject code_comment(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject comment_string = NIL;
        destructuring_bind_must_consp(current, datum, $list59);
        comment_string = current.first();
        current = current.rest();
        if (NIL != current) {
            cdestructuring_bind_error(datum, $list59);
            return NIL;
        }
        assert NIL != stringp(comment_string) : "! stringp(comment_string) " + ("Types.stringp(comment_string) " + "CommonSymbols.NIL != Types.stringp(comment_string) ") + comment_string;
        return $list61;
    }

    public static SubLObject declare_subl_macro_promotions_file() {
        declareMacro("defglobal", "DEFGLOBAL");
        declareFunction("declare_defglobal", "DECLARE-DEFGLOBAL", 1, 0, false);
        declareMacro("catch_error_message", "CATCH-ERROR-MESSAGE");
        declareFunction("catch_error_message_handler", "CATCH-ERROR-MESSAGE-HANDLER", 0, 0, false);
        declareMacro("with_timeout", "WITH-TIMEOUT");
        declareMacro("with_timeout_via_interrupt", "WITH-TIMEOUT-VIA-INTERRUPT");
        declareFunction("with_timeout_internal", "WITH-TIMEOUT-INTERNAL", 3, 0, false);
        declareFunction("with_timeout_make_tag", "WITH-TIMEOUT-MAKE-TAG", 0, 0, false);
        declareFunction("with_timeout_start_timer", "WITH-TIMEOUT-START-TIMER", 2, 0, false);
        declareFunction("timeout_tag_nesting_depth", "TIMEOUT-TAG-NESTING-DEPTH", 1, 0, false);
        declareFunction("with_timeout_stop_timer", "WITH-TIMEOUT-STOP-TIMER", 1, 0, false);
        declareFunction("with_timeout_timer_thread", "WITH-TIMEOUT-TIMER-THREAD", 5, 0, false);
        declareFunction("with_timeout_signal_timeout", "WITH-TIMEOUT-SIGNAL-TIMEOUT", 2, 0, false);
        declareFunction("with_timeout_throw", "WITH-TIMEOUT-THROW", 1, 0, false);
        declareMacro("with_tcp_connection", "WITH-TCP-CONNECTION");
        declareMacro("with_tcp_connection_with_timeout", "WITH-TCP-CONNECTION-WITH-TIMEOUT");
        declareFunction("validate_tcp_connection", "VALIDATE-TCP-CONNECTION", 3, 0, false);
        declareMacro("cmultiple_value_setq", "CMULTIPLE-VALUE-SETQ");
        declareMacro("with_space_profiling", "WITH-SPACE-PROFILING");
        declareFunction("with_space_profiling_sl2c", "WITH-SPACE-PROFILING-SL2C", 2, 0, false);
        declareMacro("code_comment", "CODE-COMMENT");
        return NIL;
    }

    public static final SubLObject init_subl_macro_promotions_file_alt() {
        deflexical("*CATCH-ERROR-MESSAGE-TARGET*", NIL != boundp($catch_error_message_target$) ? ((SubLObject) ($catch_error_message_target$.getGlobalValue())) : make_symbol($$$ERROR));
        defparameter("*WITHIN-WITH-TIMEOUT*", NIL);
        defparameter("*WITH-TIMEOUT-NESTING-DEPTH*", ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject init_subl_macro_promotions_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*CATCH-ERROR-MESSAGE-TARGET*", SubLTrampolineFile.maybeDefault($catch_error_message_target$, $catch_error_message_target$, () -> make_symbol($$$ERROR)));
            defparameter("*WITHIN-WITH-TIMEOUT*", NIL);
            defparameter("*WITH-TIMEOUT-NESTING-DEPTH*", ZERO_INTEGER);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*CATCH-ERROR-MESSAGE-TARGET*", NIL != boundp($catch_error_message_target$) ? ((SubLObject) ($catch_error_message_target$.getGlobalValue())) : make_symbol($$$ERROR));
        }
        return NIL;
    }

    public static SubLObject init_subl_macro_promotions_file_Previous() {
        deflexical("*CATCH-ERROR-MESSAGE-TARGET*", SubLTrampolineFile.maybeDefault($catch_error_message_target$, $catch_error_message_target$, () -> make_symbol($$$ERROR)));
        defparameter("*WITHIN-WITH-TIMEOUT*", NIL);
        defparameter("*WITH-TIMEOUT-NESTING-DEPTH*", ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject setup_subl_macro_promotions_file_alt() {
        declare_defglobal($catch_error_message_target$);
        register_macro_helper(WITH_TIMEOUT_MAKE_TAG, WITH_TIMEOUT);
        register_macro_helper(WITH_TIMEOUT_START_TIMER, WITH_TIMEOUT);
        register_macro_helper(WITH_TIMEOUT_STOP_TIMER, WITH_TIMEOUT);
        meta_macros.declare_indention_pattern(CMULTIPLE_VALUE_SETQ, $list_alt50);
        register_external_symbol(WITH_SPACE_PROFILING);
        register_macro_helper(WITH_SPACE_PROFILING_SL2C, WITH_SPACE_PROFILING);
        return NIL;
    }

    public static SubLObject setup_subl_macro_promotions_file() {
        if (SubLFiles.USE_V1) {
            declare_defglobal($catch_error_message_target$);
            register_macro_helper(WITH_TIMEOUT_MAKE_TAG, WITH_TIMEOUT);
            register_macro_helper(WITH_TIMEOUT_START_TIMER, WITH_TIMEOUT);
            register_macro_helper(WITH_TIMEOUT_STOP_TIMER, WITH_TIMEOUT);
            register_macro_helper(VALIDATE_TCP_CONNECTION, WITH_TCP_CONNECTION);
            meta_macros.declare_indention_pattern(CMULTIPLE_VALUE_SETQ, $list48);
            register_external_symbol(WITH_SPACE_PROFILING);
            register_macro_helper(WITH_SPACE_PROFILING_SL2C, WITH_SPACE_PROFILING);
        }
        if (SubLFiles.USE_V2) {
            meta_macros.declare_indention_pattern(CMULTIPLE_VALUE_SETQ, $list_alt50);
        }
        return NIL;
    }

    public static SubLObject setup_subl_macro_promotions_file_Previous() {
        declare_defglobal($catch_error_message_target$);
        register_macro_helper(WITH_TIMEOUT_MAKE_TAG, WITH_TIMEOUT);
        register_macro_helper(WITH_TIMEOUT_START_TIMER, WITH_TIMEOUT);
        register_macro_helper(WITH_TIMEOUT_STOP_TIMER, WITH_TIMEOUT);
        register_macro_helper(VALIDATE_TCP_CONNECTION, WITH_TCP_CONNECTION);
        meta_macros.declare_indention_pattern(CMULTIPLE_VALUE_SETQ, $list48);
        register_external_symbol(WITH_SPACE_PROFILING);
        register_macro_helper(WITH_SPACE_PROFILING_SL2C, WITH_SPACE_PROFILING);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_subl_macro_promotions_file();
    }

    @Override
    public void initializeVariables() {
        init_subl_macro_promotions_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_subl_macro_promotions_file();
    }

    static {
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list_alt0 = list(makeSymbol("VARIABLE"), makeSymbol("&OPTIONAL"), makeSymbol("INITIALIZATION"), makeSymbol("DOCUMENTATION"));

    static private final SubLList $list_alt11 = list(list(makeSymbol("VAR")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt14 = list(QUOTE, makeSymbol("CATCH-ERROR-MESSAGE-HANDLER"));

    static private final SubLList $list_alt15 = list(list(makeSymbol("TIME"), makeSymbol("TIMED-OUT-VAR")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym16$TAG = makeUninternedSymbol("TAG");

    static private final SubLSymbol $sym17$TIMER = makeUninternedSymbol("TIMER");

    static private final SubLList $list_alt19 = list(list(makeSymbol("WITH-TIMEOUT-MAKE-TAG")));

    static private final SubLList $list_alt20 = list(makeSymbol("*WITHIN-WITH-TIMEOUT*"), T);

    static private final SubLList $list_alt22 = list(list(makeSymbol("*WITH-TIMEOUT-NESTING-DEPTH*"), list(makeSymbol("+"), ONE_INTEGER, makeSymbol("*WITH-TIMEOUT-NESTING-DEPTH*"))));

    static private final SubLString $str_alt28$WITH_TIMEOUT__A = makeString("WITH-TIMEOUT ~A");

    static private final SubLString $str_alt29$_client = makeString(" client");

    static private final SubLString $str_alt30$_timer = makeString(" timer");

    static private final SubLString $str_alt31$_Timer_for_ = makeString(" Timer for ");

    private static final SubLSymbol WITH_TIMEOUT_TIMER_THREAD = makeSymbol("WITH-TIMEOUT-TIMER-THREAD");

    static private final SubLList $list_alt34 = list(list(makeSymbol("BI-STREAM"), makeSymbol("HOST"), makeSymbol("PORT"), makeSymbol("&KEY"), makeSymbol("TIMEOUT"), list(makeSymbol("ACCESS-MODE"), makeKeyword("PUBLIC"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt35 = list(makeKeyword("TIMEOUT"), makeKeyword("ACCESS-MODE"));

    static private final SubLList $list_alt44 = list(makeSymbol("WITH-TCP-CONNECTION"));

    static private final SubLList $list_alt46 = list(makeSymbol("VARIABLES"), makeSymbol("EXPRESSION"));

    static private final SubLList $list_alt50 = list(makeSymbol("VARIABLES"), makeSymbol("EXPRESSION"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt52 = list(list(makeSymbol("&KEY"), list(makeSymbol("STREAM"), list(QUOTE, makeSymbol("*STANDARD-OUTPUT*")))), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt53 = list(makeKeyword("STREAM"));

    static private final SubLSymbol $sym57$SPACE_INFO = makeUninternedSymbol("SPACE-INFO");

    static private final SubLList $list_alt58 = list(list(makeSymbol("ADD-SPACE-PROBE", "SUBLISP")));

    static private final SubLSymbol $sym60$_INTERPRET_CSPACE_RESULTS = makeSymbol("%INTERPRET-CSPACE-RESULTS", "SUBLISP");

    static private final SubLList $list_alt61 = list(makeSymbol("COMMENT-STRING"));

    static private final SubLList $list_alt63 = list(makeSymbol("PROGN"));
}

/**
 * Total time: 255 ms
 */
