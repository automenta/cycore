package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.id_index.id_index_lookup;
import static com.cyc.cycjava.cycl.id_index.id_index_remove;
import static com.cyc.cycjava.cycl.id_index.id_index_reserve;
import static com.cyc.cycjava.cycl.id_index.new_id_index;
import static subl.Characters.CHAR_newline;
import static subl.ConsesLow.cons;
import static subl.ConsesLow.list;
import static subl.PrintLow.format;
import static subl.Sequences.cconcatenate;
import static subl.Sequences.delete;
import static subl.Sequences.length;
import static subl.Sequences.nreverse;
import static subl.Sequences.subseq;
import static subl.Symbols.symbol_function;
import static subl.Threads.$is_thread_performing_cleanupP$;
import static subl.Values.getValuesAsVector;
import static subl.Values.restoreValuesFromVector;
import static subl.Values.values;
import static subl.type.core.SubLObjectFactory.makeInteger;
import static subl.type.core.SubLObjectFactory.makeString;
import static subl.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.close;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.get_output_stream_string;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.make_private_string_output_stream;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import subl.SubLThread;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.core.SubLProcess;
import subl.type.core.SubLString;
import subl.type.number.SubLInteger;
import subl.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class pph_error_message_truncator extends SubLTranslatedFile implements V10 {
    public static final SubLFile me = new pph_error_message_truncator();

    public static final String myName = "com.cyc.cycjava_2.cycl.pph_error_message_truncator";


    // deflexical
    private static final SubLSymbol $pph_error_msg_truncator_id_index$ = makeSymbol("*PPH-ERROR-MSG-TRUNCATOR-ID-INDEX*");

    // deflexical
    private static final SubLSymbol $pph_error_msg_truncator_ids$ = makeSymbol("*PPH-ERROR-MSG-TRUNCATOR-IDS*");

    // deflexical
    private static final SubLSymbol $active_pph_error_msg_ids$ = makeSymbol("*ACTIVE-PPH-ERROR-MSG-IDS*");

    // deflexical
    private static final SubLSymbol $max_active_pph_error_msg_ids$ = makeSymbol("*MAX-ACTIVE-PPH-ERROR-MSG-IDS*");

    private static final SubLString $str0$___See_PPH_Error__ = makeString("...See PPH Error [");

    private static final SubLString $str1$__for_remaining_lines_of_error_me = makeString("] for remaining lines of error message.");

    private static final SubLString $str2$PPH_Error___D____ = makeString("PPH Error [~D]:~%");

    private static final SubLString $str3$_A__ = makeString("~A~%");

    private static final SubLInteger $int$500 = makeInteger(500);

    private static final SubLList $list5 = list(CHAR_newline);

    public static SubLObject truncate_pph_error_message(final SubLObject error_message) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject matches = match_pph_error_msg(error_message);
        final SubLObject matched_line_count = thread.secondMultipleValue();
        final SubLObject lines = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        SubLObject new_message_lines = copy_list(lines);
        SubLObject new_error_message = NIL;
        if ((NIL != matches) && (NIL != list_utilities.lengthG(lines, matched_line_count, UNPROVIDED))) {
            final SubLObject match = matches.first().rest().first();
            final SubLObject new_line = cconcatenate($str0$___See_PPH_Error__, new SubLObject[]{ format_nil.format_nil_d_no_copy(match), $str1$__for_remaining_lines_of_error_me });
            new_message_lines = cons(new_line, subseq(new_message_lines, number_utilities.f_1_(matched_line_count), UNPROVIDED));
        }
        final SubLObject id = get_new_pph_error_msg_id();
        SubLObject stream = NIL;
        try {
            stream = make_private_string_output_stream();
            format(stream, $str2$PPH_Error___D____, id);
            SubLObject cdolist_list_var = nreverse(new_message_lines);
            SubLObject line = NIL;
            line = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                format(stream, $str3$_A__, line);
                cdolist_list_var = cdolist_list_var.rest();
                line = cdolist_list_var.first();
            } 
            new_error_message = get_output_stream_string(stream);
        } finally {
            final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values = getValuesAsVector();
                close(stream, UNPROVIDED);
                restoreValuesFromVector(_values);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
            }
        }
        if (NIL == list_utilities.lengthE(lines, matched_line_count, UNPROVIDED)) {
            index_pph_error_msg_lines(lines, id);
        }
        note_pph_error_message_referenced(id);
        SubLObject cdolist_list_var = matches;
        SubLObject match2 = NIL;
        match2 = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$1 = match2.rest();
            SubLObject matched_id = NIL;
            matched_id = cdolist_list_var_$1.first();
            while (NIL != cdolist_list_var_$1) {
                note_pph_error_message_referenced(matched_id);
                cdolist_list_var_$1 = cdolist_list_var_$1.rest();
                matched_id = cdolist_list_var_$1.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            match2 = cdolist_list_var.first();
        } 
        return new_error_message;
    }

    public static SubLObject tokenize_pph_error_msg(final SubLObject error_msg) {
        return nreverse(string_utilities.string_tokenize(error_msg, $list5, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject index_pph_error_msg(final SubLObject error_msg, final SubLObject id) {
        final SubLObject lines = tokenize_pph_error_msg(error_msg);
        return index_pph_error_msg_lines(lines, id);
    }

    public static SubLObject index_pph_error_msg_lines(final SubLObject lines, final SubLObject id) {
        strie.strie_replace($pph_error_msg_truncator_ids$.getGlobalValue(), lines, id);
        return id;
    }

    public static SubLObject get_new_pph_error_msg_id() {
        return id_index_reserve($pph_error_msg_truncator_id_index$.getGlobalValue());
    }

    public static SubLObject unindex_pph_error_msg(final SubLObject id) {
        strie.strie_remove($pph_error_msg_truncator_ids$.getGlobalValue(), id_index_lookup($pph_error_msg_truncator_id_index$.getGlobalValue(), id, UNPROVIDED));
        id_index_remove($pph_error_msg_truncator_id_index$.getGlobalValue(), id);
        return id;
    }

    public static SubLObject note_pph_error_message_referenced(final SubLObject id) {
        $active_pph_error_msg_ids$.setGlobalValue(list_utilities.add_to_end(id, delete(id, copy_list($active_pph_error_msg_ids$.getGlobalValue()), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED)));
        if (NIL != list_utilities.lengthG($active_pph_error_msg_ids$.getGlobalValue(), $max_active_pph_error_msg_ids$.getGlobalValue(), UNPROVIDED)) {
            final SubLObject doomed_id = $active_pph_error_msg_ids$.getGlobalValue().first();
            $active_pph_error_msg_ids$.setGlobalValue($active_pph_error_msg_ids$.getGlobalValue().rest());
            unindex_pph_error_msg(doomed_id);
        }
        return id;
    }

    public static SubLObject match_pph_error_msg(final SubLObject error_msg) {
        final SubLObject lines = tokenize_pph_error_msg(error_msg);
        SubLObject completions = NIL;
        SubLObject key_size = ZERO_INTEGER;
        SubLObject doneP = NIL;
        if (NIL == doneP) {
            SubLObject end_var;
            SubLObject i;
            SubLObject new_completions;
            for (end_var = number_utilities.f_1X(length(lines)), i = NIL, i = ONE_INTEGER; (NIL == doneP) && (!i.numGE(end_var)); i = number_utilities.f_1X(i)) {
                new_completions = strie.strie_completions($pph_error_msg_truncator_ids$.getGlobalValue(), subseq(lines, ZERO_INTEGER, i));
                if (NIL != list_utilities.empty_list_p(new_completions)) {
                    doneP = T;
                } else {
                    completions = new_completions;
                    key_size = i;
                }
            }
        }
        return values(completions, key_size, lines);
    }

    public static SubLObject declare_pph_error_message_truncator_file() {
        declareFunction("truncate_pph_error_message", "TRUNCATE-PPH-ERROR-MESSAGE", 1, 0, false);
        declareFunction("tokenize_pph_error_msg", "TOKENIZE-PPH-ERROR-MSG", 1, 0, false);
        declareFunction("index_pph_error_msg", "INDEX-PPH-ERROR-MSG", 2, 0, false);
        declareFunction("index_pph_error_msg_lines", "INDEX-PPH-ERROR-MSG-LINES", 2, 0, false);
        declareFunction("get_new_pph_error_msg_id", "GET-NEW-PPH-ERROR-MSG-ID", 0, 0, false);
        declareFunction("unindex_pph_error_msg", "UNINDEX-PPH-ERROR-MSG", 1, 0, false);
        declareFunction("note_pph_error_message_referenced", "NOTE-PPH-ERROR-MESSAGE-REFERENCED", 1, 0, false);
        declareFunction("match_pph_error_msg", "MATCH-PPH-ERROR-MSG", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_pph_error_message_truncator_file() {
        deflexical("*PPH-ERROR-MSG-TRUNCATOR-ID-INDEX*", new_id_index(UNPROVIDED, UNPROVIDED));
        deflexical("*PPH-ERROR-MSG-TRUNCATOR-IDS*", strie.new_strie(symbol_function(EQUAL)));
        deflexical("*ACTIVE-PPH-ERROR-MSG-IDS*", NIL);
        deflexical("*MAX-ACTIVE-PPH-ERROR-MSG-IDS*", $int$500);
        return NIL;
    }

    public static SubLObject setup_pph_error_message_truncator_file() {
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_pph_error_message_truncator_file();
    }

    @Override
    public void initializeVariables() {
        init_pph_error_message_truncator_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_pph_error_message_truncator_file();
    }

    static {











    }
}

/**
 * Total time: 89 ms
 */
