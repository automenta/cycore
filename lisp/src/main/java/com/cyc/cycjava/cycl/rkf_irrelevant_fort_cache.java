/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.constant_handles.constant_p;
import static com.cyc.cycjava.cycl.control_vars.kb_loaded;
import static com.cyc.cycjava.cycl.id_index.do_id_index_empty_p;
import static com.cyc.cycjava.cycl.id_index.do_id_index_id_and_object_validP;
import static com.cyc.cycjava.cycl.id_index.do_id_index_next_id;
import static com.cyc.cycjava.cycl.id_index.do_id_index_next_state;
import static com.cyc.cycjava.cycl.id_index.do_id_index_state_object;
import static com.cyc.cycjava.cycl.id_index.id_index_dense_objects;
import static com.cyc.cycjava.cycl.id_index.id_index_dense_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_next_id;
import static com.cyc.cycjava.cycl.id_index.id_index_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_skip_tombstones_p;
import static com.cyc.cycjava.cycl.id_index.id_index_sparse_id_threshold;
import static com.cyc.cycjava.cycl.id_index.id_index_sparse_objects;
import static com.cyc.cycjava.cycl.id_index.id_index_sparse_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_tombstone_p;
import static com.cyc.cycjava.cycl.subl_macro_promotions.$catch_error_message_target$;
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
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eql;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.gethash_without_values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.make_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.release_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.seize_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_value;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.get_universal_time;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Filesys;
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


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      RKF-IRRELEVANT-FORT-CACHE
 * source file: /cyc/top/cycl/rkf-irrelevant-fort-cache.lisp
 * created:     2019/07/03 17:37:58
 */
public final class rkf_irrelevant_fort_cache extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new rkf_irrelevant_fort_cache();

 public static final String myName = "com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache";


    // deflexical
    // Definitions
    /**
     * File backed cache of fort -> IRRELEVANT-VAL of
     * all forts in the KB when the file was last generated (which should be every world build).
     * IRRELEVANT-VAL is an integer 0 = irrelevant nowhere, 1 = irrelevant
     * everywhere, 2 = irrelevant somewhere
     */
    @LispMethod(comment = "File backed cache of fort -> IRRELEVANT-VAL of\r\nall forts in the KB when the file was last generated (which should be every world build).\r\nIRRELEVANT-VAL is an integer 0 = irrelevant nowhere, 1 = irrelevant\r\neverywhere, 2 = irrelevant somewhere\ndeflexical\nFile backed cache of fort -> IRRELEVANT-VAL of\nall forts in the KB when the file was last generated (which should be every world build).\nIRRELEVANT-VAL is an integer 0 = irrelevant nowhere, 1 = irrelevant\neverywhere, 2 = irrelevant somewhere")
    private static final SubLSymbol $rkf_irrelevant_fort_cache$ = makeSymbol("*RKF-IRRELEVANT-FORT-CACHE*");

    // defparameter
    // Whether we want to do cache lookups of rkf irrelevance values of forts.
    /**
     * Whether we want to do cache lookups of rkf irrelevance values of forts.
     */
    @LispMethod(comment = "Whether we want to do cache lookups of rkf irrelevance values of forts.\ndefparameter")
    public static final SubLSymbol $use_rkf_irrelevant_fort_cacheP$ = makeSymbol("*USE-RKF-IRRELEVANT-FORT-CACHE?*");

    // deflexical
    /**
     * Lock used for opening the file hashtable to prevent multiple version from
     * being opened
     */
    @LispMethod(comment = "Lock used for opening the file hashtable to prevent multiple version from\r\nbeing opened\ndeflexical\nLock used for opening the file hashtable to prevent multiple version from\nbeing opened")
    private static final SubLSymbol $rkf_irrelevant_fht_lock$ = makeSymbol("*RKF-IRRELEVANT-FHT-LOCK*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $max_kbs_backward_to_look_for_irrelevancy_cache$ = makeSymbol("*MAX-KBS-BACKWARD-TO-LOOK-FOR-IRRELEVANCY-CACHE*");

    // deflexical
    // Set to T if this becomes fast enough.
    /**
     * Set to T if this becomes fast enough.
     */
    @LispMethod(comment = "Set to T if this becomes fast enough.\ndeflexical")
    private static final SubLSymbol $preload_rkf_irrelevant_fort_cacheP$ = makeSymbol("*PRELOAD-RKF-IRRELEVANT-FORT-CACHE?*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLString $str0$rkf_irrelevant_fht_lock = makeString("rkf-irrelevant-fht-lock");

    static private final SubLList $list1 = list(makeString("application.file-backed-cache.rkf-irrelevant-fort-cache-file-name"));

    public static final SubLSymbol $rkf_irrelevant_cache_base_file_name$ = makeSymbol("*RKF-IRRELEVANT-CACHE-BASE-FILE-NAME*");

    static private final SubLString $str3$rkf_irrelevant_fort_cache_fht = makeString("rkf-irrelevant-fort-cache.fht");

    static private final SubLList $list5 = list(list(makeSymbol("FILENAME")), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final SubLSymbol $rkf_irrelevant_fht_name$ = makeSymbol("*RKF-IRRELEVANT-FHT-NAME*");

    private static final SubLString $str8$_ = makeString("/");

    private static final SubLSymbol $HL_EXTERNALIZED_KEYS = makeKeyword("HL-EXTERNALIZED-KEYS");

    private static final SubLString $str11$tmp_ = makeString("tmp/");

    private static final SubLString $str12$Generating_rkf_irrelevant_cache_t = makeString("Generating rkf irrelevant cache to file: ~A");

    private static final SubLString $str13$Generating_rkf_irrelevant_values_ = makeString("Generating rkf irrelevant values for all forts ....");

    private static final SubLList $list15 = list(makeUninternedSymbol("START"), makeUninternedSymbol("END"), makeUninternedSymbol("DELTA"));

    private static final SubLSymbol FILE_BACKED_CACHE_P = makeSymbol("FILE-BACKED-CACHE-P");

    static private final SubLString $str18$Looking_up_rkf_irrelevance_status = makeString("Looking up rkf-irrelevance status for all forts ....");



    static private final SubLString $str25$Found_an_invalid_value_in_rkf_irr = makeString("Found an invalid value in rkf-irrelevant-fort-cache value: ~A for key ~A.");

    static private final SubLList $list26 = list(list(makeSymbol("FORT"), makeSymbol("&KEY"), makeSymbol("MESSAGE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list27 = list(makeKeyword("MESSAGE"));

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    static private final SubLSymbol $sym30$VALUE = makeUninternedSymbol("VALUE");

    private static final SubLSymbol DO_FILE_HASH_TABLE = makeSymbol("DO-FILE-HASH-TABLE");

    static private final SubLList $list32 = list(makeSymbol("FBC-FILE-HASH-TABLE-CACHE"), makeSymbol("*RKF-IRRELEVANT-FORT-CACHE*"));

    private static final SubLString $str37$Trying_to_insert_invalid_value_in = makeString("Trying to insert invalid value into rkf-irrelevant-fort-cache value: ~A for key: ~A.");

    private static final SubLString $str39$_A_can_not_be_converted_to_an_irr = makeString("~A can not be converted to an irrelevance keyword");

    private static final SubLSymbol RKF_IRRELEVANT_ASSERTION_CACHE_CLEAR = makeSymbol("RKF-IRRELEVANT-ASSERTION-CACHE-CLEAR");

    public static final SubLObject with_default_rkf_irrelevant_cache_file_path_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt5);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject filename = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt5);
                    filename = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return listS(CLET, list(list($rkf_irrelevant_fht_name$, filename)), append(body, NIL));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt5);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject with_default_rkf_irrelevant_cache_file_path(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list5);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject filename = NIL;
        destructuring_bind_must_consp(current, datum, $list5);
        filename = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return listS(CLET, list(list($rkf_irrelevant_fht_name$, filename)), append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list5);
        return NIL;
    }

    /**
     * Returns the default file path name for the file hash table for this cache.
     */
    @LispMethod(comment = "Returns the default file path name for the file hash table for this cache.")
    public static final SubLObject get_default_rkf_irrelevant_cache_file_path_alt(SubLObject kb) {
        if (kb == UNPROVIDED) {
            kb = kb_loaded();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != subl_promotions.positive_integer_p(kb)) {
                if (!($rkf_irrelevant_fht_name$.getDynamicValue(thread).isString() && (NIL != Filesys.probe_file($rkf_irrelevant_fht_name$.getDynamicValue(thread))))) {
                    $rkf_irrelevant_fht_name$.setDynamicValue(com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.get_rkf_irrelevant_cache_file_path_internal(kb), thread);
                }
            }
            return $rkf_irrelevant_fht_name$.getDynamicValue(thread);
        }
    }

    /**
     * Returns the default file path name for the file hash table for this cache.
     */
    @LispMethod(comment = "Returns the default file path name for the file hash table for this cache.")
    public static SubLObject get_default_rkf_irrelevant_cache_file_path(SubLObject kb) {
        if (kb == UNPROVIDED) {
            kb = kb_loaded();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL != subl_promotions.positive_integer_p(kb)) && ((!$rkf_irrelevant_fht_name$.getDynamicValue(thread).isString()) || (NIL == Filesys.probe_file($rkf_irrelevant_fht_name$.getDynamicValue(thread))))) {
            $rkf_irrelevant_fht_name$.setDynamicValue(get_rkf_irrelevant_cache_file_path_internal(kb), thread);
        }
        return $rkf_irrelevant_fht_name$.getDynamicValue(thread);
    }

    /**
     * Returns the file path name for the latest RKF irrelevance file hash table.
     */
    @LispMethod(comment = "Returns the file path name for the latest RKF irrelevance file hash table.")
    public static final SubLObject get_latest_rkf_irrelevant_cache_file_path_alt() {
        {
            SubLObject kb = kb_loaded();
            SubLObject path = NIL;
            while ((NIL != subl_promotions.positive_integer_p(kb)) && (!(path.isString() && (NIL != Filesys.probe_file(path))))) {
                path = com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.get_rkf_irrelevant_cache_file_path_internal(kb);
                kb = subtract(kb, ONE_INTEGER);
            } 
            return path;
        }
    }

    /**
     * Returns the file path name for the latest RKF irrelevance file hash table.
     */
    @LispMethod(comment = "Returns the file path name for the latest RKF irrelevance file hash table.")
    public static SubLObject get_latest_rkf_irrelevant_cache_file_path() {
        SubLObject kb;
        SubLObject path;
        for (kb = kb_loaded(), path = NIL; ((NIL != subl_promotions.positive_integer_p(kb)) && subtract(kb_loaded(), kb).numLE($max_kbs_backward_to_look_for_irrelevancy_cache$.getGlobalValue())) && ((!path.isString()) || (NIL == Filesys.probe_file(path))); path = get_rkf_irrelevant_cache_file_path_internal(kb) , kb = subtract(kb, ONE_INTEGER)) {
        }
        return path;
    }

    /**
     * Returns the file path name for the RKF irrelevance file hash table for KB.
     */
    @LispMethod(comment = "Returns the file path name for the RKF irrelevance file hash table for KB.")
    public static final SubLObject get_rkf_irrelevant_cache_file_path_internal_alt(SubLObject kb) {
        {
            SubLObject path = cconcatenate(file_backed_cache.$file_backed_cache_base_path$.getGlobalValue(), new SubLObject[]{ format(NIL, $str_alt8$_4__0d, kb), $str_alt9$_, $rkf_irrelevant_cache_base_file_name$.getGlobalValue() });
            return path;
        }
    }

    /**
     * Returns the file path name for the RKF irrelevance file hash table for KB.
     */
    @LispMethod(comment = "Returns the file path name for the RKF irrelevance file hash table for KB.")
    public static SubLObject get_rkf_irrelevant_cache_file_path_internal(final SubLObject kb) {
        final SubLObject path = cconcatenate(file_backed_cache.$file_backed_cache_base_path$.getGlobalValue(), new SubLObject[]{ operation_communication.kb_number_string(kb), $str8$_, $rkf_irrelevant_cache_base_file_name$.getGlobalValue() });
        return path;
    }

    /**
     * Initializes the file hashtables and internal caches used to cache
     * rkf irrelevant values for FORTs. Also prefetches all the items in
     * the file hashtable cache.
     */
    @LispMethod(comment = "Initializes the file hashtables and internal caches used to cache\r\nrkf irrelevant values for FORTs. Also prefetches all the items in\r\nthe file hashtable cache.\nInitializes the file hashtables and internal caches used to cache\nrkf irrelevant values for FORTs. Also prefetches all the items in\nthe file hashtable cache.")
    public static final SubLObject initialize_rkf_irrelevant_fort_cache_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == kb_loaded()) {
                return NIL;
            }
            if (NIL == $rkf_irrelevant_fort_cache$.getGlobalValue()) {
                {
                    SubLObject lock = $rkf_irrelevant_fht_lock$.getGlobalValue();
                    SubLObject release = NIL;
                    try {
                        release = seize_lock(lock);
                        if (NIL == $rkf_irrelevant_fort_cache$.getGlobalValue()) {
                            {
                                SubLObject _prev_bind_0 = file_hash_table.$fht_cache_percentage$.currentBinding(thread);
                                try {
                                    file_hash_table.$fht_cache_percentage$.bind($int$100, thread);
                                    $rkf_irrelevant_fort_cache$.setGlobalValue(file_backed_cache.file_backed_cache_create(com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.get_latest_rkf_irrelevant_cache_file_path(), $preload_rkf_irrelevant_fort_cacheP$.getGlobalValue(), T, file_hash_table.$fht_cache_percentage$.getDynamicValue(thread), symbol_function(EQUAL), $HL_EXTERNALIZED_KEYS));
                                } finally {
                                    file_hash_table.$fht_cache_percentage$.rebind(_prev_bind_0, thread);
                                }
                            }
                        }
                    } finally {
                        if (NIL != release) {
                            release_lock(lock);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    /**
     * Initializes the file hashtables and internal caches used to cache
     * rkf irrelevant values for FORTs. Also prefetches all the items in
     * the file hashtable cache.
     */
    @LispMethod(comment = "Initializes the file hashtables and internal caches used to cache\r\nrkf irrelevant values for FORTs. Also prefetches all the items in\r\nthe file hashtable cache.\nInitializes the file hashtables and internal caches used to cache\nrkf irrelevant values for FORTs. Also prefetches all the items in\nthe file hashtable cache.")
    public static SubLObject initialize_rkf_irrelevant_fort_cache() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == kb_loaded()) {
            return NIL;
        }
        if (NIL == $rkf_irrelevant_fort_cache$.getGlobalValue()) {
            SubLObject release = NIL;
            try {
                release = seize_lock($rkf_irrelevant_fht_lock$.getGlobalValue());
                if (NIL == $rkf_irrelevant_fort_cache$.getGlobalValue()) {
                    final SubLObject _prev_bind_0 = file_hash_table.$fht_cache_percentage$.currentBinding(thread);
                    try {
                        file_hash_table.$fht_cache_percentage$.bind($int$100, thread);
                        final SubLObject path = get_latest_rkf_irrelevant_cache_file_path();
                        $rkf_irrelevant_fort_cache$.setGlobalValue(NIL != Filesys.probe_file(path) ? file_backed_cache.file_backed_cache_create(path, $preload_rkf_irrelevant_fort_cacheP$.getGlobalValue(), T, file_hash_table.$fht_cache_percentage$.getDynamicValue(thread), symbol_function(EQUAL), $HL_EXTERNALIZED_KEYS) : NIL);
                    } finally {
                        file_hash_table.$fht_cache_percentage$.rebind(_prev_bind_0, thread);
                    }
                }
            } finally {
                if (NIL != release) {
                    release_lock($rkf_irrelevant_fht_lock$.getGlobalValue());
                }
            }
        }
        return NIL;
    }

    /**
     * Closes all file hashtables and reinitializes the rkf irrelevant caches.
     * Any dynamic updating of the live cache is lost.
     */
    @LispMethod(comment = "Closes all file hashtables and reinitializes the rkf irrelevant caches.\r\nAny dynamic updating of the live cache is lost.\nCloses all file hashtables and reinitializes the rkf irrelevant caches.\nAny dynamic updating of the live cache is lost.")
    public static final SubLObject reset_rkf_irrelevant_fort_cache_alt() {
        {
            SubLObject lock = $rkf_irrelevant_fht_lock$.getGlobalValue();
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                if (NIL != $rkf_irrelevant_fort_cache$.getGlobalValue()) {
                    $rkf_irrelevant_fht_name$.setDynamicValue(NIL);
                    file_backed_cache.file_backed_cache_reset($rkf_irrelevant_fort_cache$.getGlobalValue(), com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.get_default_rkf_irrelevant_cache_file_path(UNPROVIDED));
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return NIL;
    }

    /**
     * Closes all file hashtables and reinitializes the rkf irrelevant caches.
     * Any dynamic updating of the live cache is lost.
     */
    @LispMethod(comment = "Closes all file hashtables and reinitializes the rkf irrelevant caches.\r\nAny dynamic updating of the live cache is lost.\nCloses all file hashtables and reinitializes the rkf irrelevant caches.\nAny dynamic updating of the live cache is lost.")
    public static SubLObject reset_rkf_irrelevant_fort_cache() {
        SubLObject release = NIL;
        try {
            release = seize_lock($rkf_irrelevant_fht_lock$.getGlobalValue());
            if (NIL != $rkf_irrelevant_fort_cache$.getGlobalValue()) {
                $rkf_irrelevant_fht_name$.setDynamicValue(NIL);
                file_backed_cache.file_backed_cache_reset($rkf_irrelevant_fort_cache$.getGlobalValue(), get_default_rkf_irrelevant_cache_file_path(UNPROVIDED));
            }
        } finally {
            if (NIL != release) {
                release_lock($rkf_irrelevant_fht_lock$.getGlobalValue());
            }
        }
        return NIL;
    }

    /**
     * Creates a cache of all the forts in the current KB where the key is the fort in
     * question and  the value is an IRRELEVANT-VAL where an IRRELEVANT-VAL
     * is an integer 0 = irrelevant nowhere, 1 = irrelevant
     * everywhere, 2 = irrelevant somewhere, 3 = irrelevant nowhere and THCL.
     *
     * @param FILENAME
     * 		the file name of the file hashtable
     * 		Defaults to: *rkf-irrelevant-fht-name*
     */
    @LispMethod(comment = "Creates a cache of all the forts in the current KB where the key is the fort in\r\nquestion and  the value is an IRRELEVANT-VAL where an IRRELEVANT-VAL\r\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\r\neverywhere, 2 = irrelevant somewhere, 3 = irrelevant nowhere and THCL.\r\n\r\n@param FILENAME\r\n\t\tthe file name of the file hashtable\r\n\t\tDefaults to: *rkf-irrelevant-fht-name*\nCreates a cache of all the forts in the current KB where the key is the fort in\nquestion and  the value is an IRRELEVANT-VAL where an IRRELEVANT-VAL\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\neverywhere, 2 = irrelevant somewhere, 3 = irrelevant nowhere and THCL.")
    public static final SubLObject create_rkf_irrelevant_fort_cache_alt(SubLObject filename, SubLObject tmp_dir) {
        if (filename == UNPROVIDED) {
            filename = com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.get_default_rkf_irrelevant_cache_file_path(UNPROVIDED);
        }
        if (tmp_dir == UNPROVIDED) {
            tmp_dir = $str_alt12$tmp_;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            format(T, $str_alt13$Generating_rkf_irrelevant_cache_t, filename);
            {
                SubLObject i = ZERO_INTEGER;
                SubLObject doneP = NIL;
                SubLObject fht = file_hash_table.fast_create_file_hash_table(filename, tmp_dir, symbol_function(EQUAL), $HL_EXTERNALIZED_KEYS);
                kb_cleanup.kill_duplicate_narts();
                {
                    SubLObject message = $str_alt14$Generating_rkf_irrelevant_values_;
                    SubLObject total = forts.fort_count();
                    SubLObject sofar = ZERO_INTEGER;
                    {
                        SubLObject _prev_bind_0 = $last_percent_progress_index$.currentBinding(thread);
                        SubLObject _prev_bind_1 = $last_percent_progress_prediction$.currentBinding(thread);
                        SubLObject _prev_bind_2 = $within_noting_percent_progress$.currentBinding(thread);
                        SubLObject _prev_bind_3 = $percent_progress_start_time$.currentBinding(thread);
                        try {
                            $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
                            $last_percent_progress_prediction$.bind(NIL, thread);
                            $within_noting_percent_progress$.bind(T, thread);
                            $percent_progress_start_time$.bind(get_universal_time(), thread);
                            noting_percent_progress_preamble(message);
                            {
                                SubLObject rest = NIL;
                                for (rest = forts.do_forts_tables(); !((NIL != doneP) || (NIL == rest)); rest = rest.rest()) {
                                    {
                                        SubLObject table_var = rest.first();
                                        if (NIL == do_id_index_empty_p(table_var, $SKIP)) {
                                            {
                                                SubLObject id = do_id_index_next_id(table_var, T, NIL, NIL);
                                                SubLObject state_var = do_id_index_next_state(table_var, T, id, NIL);
                                                SubLObject fort = NIL;
                                                while ((NIL != id) && (NIL == doneP)) {
                                                    fort = do_id_index_state_object(table_var, $SKIP, id, state_var);
                                                    if (NIL != do_id_index_id_and_object_validP(id, fort, $SKIP)) {
                                                        sofar = add(sofar, ONE_INTEGER);
                                                        note_percent_progress(sofar, total);
                                                        i = add(i, ONE_INTEGER);
                                                        {
                                                            SubLObject val = com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.compute_rkf_irrelevant_fort_cache_value(fort);
                                                            if (val.isNumber()) {
                                                                file_hash_table.fast_put_file_hash_table(fort, fht, val);
                                                            }
                                                        }
                                                    }
                                                    id = do_id_index_next_id(table_var, T, id, state_var);
                                                    state_var = do_id_index_next_state(table_var, T, id, state_var);
                                                } 
                                            }
                                        }
                                    }
                                }
                            }
                            noting_percent_progress_postamble();
                        } finally {
                            $percent_progress_start_time$.rebind(_prev_bind_3, thread);
                            $within_noting_percent_progress$.rebind(_prev_bind_2, thread);
                            $last_percent_progress_prediction$.rebind(_prev_bind_1, thread);
                            $last_percent_progress_index$.rebind(_prev_bind_0, thread);
                        }
                    }
                }
                file_hash_table.fast_put_file_hash_table($THCL_SUPPORT, fht, T);
                file_hash_table.finalize_fast_create_file_hash_table(fht, UNPROVIDED, UNPROVIDED);
            }
            return filename;
        }
    }

    /**
     * Creates a cache of all the forts in the current KB where the key is the fort in
     * question and  the value is an IRRELEVANT-VAL where an IRRELEVANT-VAL
     * is an integer 0 = irrelevant nowhere, 1 = irrelevant
     * everywhere, 2 = irrelevant somewhere, 3 = irrelevant nowhere and THCL.
     *
     * @param FILENAME
     * 		the file name of the file hashtable
     * 		Defaults to: *rkf-irrelevant-fht-name*
     */
    @LispMethod(comment = "Creates a cache of all the forts in the current KB where the key is the fort in\r\nquestion and  the value is an IRRELEVANT-VAL where an IRRELEVANT-VAL\r\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\r\neverywhere, 2 = irrelevant somewhere, 3 = irrelevant nowhere and THCL.\r\n\r\n@param FILENAME\r\n\t\tthe file name of the file hashtable\r\n\t\tDefaults to: *rkf-irrelevant-fht-name*\nCreates a cache of all the forts in the current KB where the key is the fort in\nquestion and  the value is an IRRELEVANT-VAL where an IRRELEVANT-VAL\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\neverywhere, 2 = irrelevant somewhere, 3 = irrelevant nowhere and THCL.")
    public static SubLObject create_rkf_irrelevant_fort_cache(SubLObject filename, SubLObject tmp_dir) {
        if (filename == UNPROVIDED) {
            filename = get_default_rkf_irrelevant_cache_file_path(UNPROVIDED);
        }
        if (tmp_dir == UNPROVIDED) {
            tmp_dir = $str11$tmp_;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        format(T, $str12$Generating_rkf_irrelevant_cache_t, filename);
        SubLObject i = ZERO_INTEGER;
        final SubLObject doneP = NIL;
        final SubLObject fht = file_hash_table.fast_create_file_hash_table(filename, tmp_dir, symbol_function(EQUAL), $HL_EXTERNALIZED_KEYS);
        kb_cleanup.kill_duplicate_narts();
        final SubLObject message = $str13$Generating_rkf_irrelevant_values_;
        final SubLObject total = forts.fort_count();
        SubLObject sofar = ZERO_INTEGER;
        final SubLObject _prev_bind_0 = $last_percent_progress_index$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $last_percent_progress_prediction$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $within_noting_percent_progress$.currentBinding(thread);
        final SubLObject _prev_bind_4 = $percent_progress_start_time$.currentBinding(thread);
        try {
            $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
            $last_percent_progress_prediction$.bind(NIL, thread);
            $within_noting_percent_progress$.bind(T, thread);
            $percent_progress_start_time$.bind(get_universal_time(), thread);
            try {
                noting_percent_progress_preamble(message);
                SubLObject rest;
                SubLObject idx;
                SubLObject table_var;
                SubLObject idx_$1;
                SubLObject vector_var;
                SubLObject backwardP_var;
                SubLObject length;
                SubLObject current;
                SubLObject datum;
                SubLObject start;
                SubLObject end;
                SubLObject delta;
                SubLObject end_var;
                SubLObject id;
                SubLObject fort;
                SubLObject val;
                SubLObject idx_$2;
                SubLObject sparse;
                SubLObject id2;
                SubLObject end_id;
                SubLObject v_default;
                SubLObject fort2;
                SubLObject val2;
                for (rest = NIL, rest = forts.do_forts_tables(); (NIL == doneP) && (NIL != rest); rest = rest.rest()) {
                    table_var = idx = rest.first();
                    if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
                        idx_$1 = idx;
                        if (NIL == id_index_dense_objects_empty_p(idx_$1, $SKIP)) {
                            vector_var = id_index_dense_objects(idx_$1);
                            backwardP_var = NIL;
                            length = length(vector_var);
                            datum = current = (NIL != backwardP_var) ? list(subtract(length, ONE_INTEGER), MINUS_ONE_INTEGER, MINUS_ONE_INTEGER) : list(ZERO_INTEGER, length, ONE_INTEGER);
                            start = NIL;
                            end = NIL;
                            delta = NIL;
                            destructuring_bind_must_consp(current, datum, $list15);
                            start = current.first();
                            current = current.rest();
                            destructuring_bind_must_consp(current, datum, $list15);
                            end = current.first();
                            current = current.rest();
                            destructuring_bind_must_consp(current, datum, $list15);
                            delta = current.first();
                            current = current.rest();
                            if (NIL == current) {
                                if (NIL == doneP) {
                                    for (end_var = end, id = NIL, id = start; (NIL == doneP) && (NIL == subl_macros.do_numbers_endtest(id, delta, end_var)); id = add(id, delta)) {
                                        fort = aref(vector_var, id);
                                        if ((NIL == id_index_tombstone_p(fort)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                                            if (NIL != id_index_tombstone_p(fort)) {
                                                fort = $SKIP;
                                            }
                                            sofar = add(sofar, ONE_INTEGER);
                                            note_percent_progress(sofar, total);
                                            i = add(i, ONE_INTEGER);
                                            val = compute_rkf_irrelevant_fort_cache_value(fort);
                                            if (val.isNumber()) {
                                                file_hash_table.fast_put_file_hash_table(fort, fht, val);
                                            }
                                        }
                                    }
                                }
                            } else {
                                cdestructuring_bind_error(datum, $list15);
                            }
                        }
                        idx_$2 = idx;
                        if ((NIL == id_index_sparse_objects_empty_p(idx_$2)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                            sparse = id_index_sparse_objects(idx_$2);
                            id2 = id_index_sparse_id_threshold(idx_$2);
                            end_id = id_index_next_id(idx_$2);
                            v_default = (NIL != id_index_skip_tombstones_p($SKIP)) ? NIL : $SKIP;
                            while (id2.numL(end_id) && (NIL == doneP)) {
                                fort2 = gethash_without_values(id2, sparse, v_default);
                                if ((NIL == id_index_skip_tombstones_p($SKIP)) || (NIL == id_index_tombstone_p(fort2))) {
                                    sofar = add(sofar, ONE_INTEGER);
                                    note_percent_progress(sofar, total);
                                    i = add(i, ONE_INTEGER);
                                    val2 = compute_rkf_irrelevant_fort_cache_value(fort2);
                                    if (val2.isNumber()) {
                                        file_hash_table.fast_put_file_hash_table(fort2, fht, val2);
                                    }
                                }
                                id2 = add(id2, ONE_INTEGER);
                            } 
                        }
                    }
                }
            } finally {
                final SubLObject _prev_bind_0_$3 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    noting_percent_progress_postamble();
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$3, thread);
                }
            }
        } finally {
            $percent_progress_start_time$.rebind(_prev_bind_4, thread);
            $within_noting_percent_progress$.rebind(_prev_bind_3, thread);
            $last_percent_progress_prediction$.rebind(_prev_bind_2, thread);
            $last_percent_progress_index$.rebind(_prev_bind_0, thread);
        }
        file_hash_table.fast_put_file_hash_table($THCL_SUPPORT, fht, T);
        file_hash_table.finalize_fast_create_file_hash_table(fht, UNPROVIDED, UNPROVIDED);
        return filename;
    }

    public static final SubLObject compute_rkf_irrelevant_fort_cache_value_alt(SubLObject fort) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject val = NIL;
                {
                    SubLObject _prev_bind_0 = $use_rkf_irrelevant_fort_cacheP$.currentBinding(thread);
                    try {
                        $use_rkf_irrelevant_fort_cacheP$.bind(NIL, thread);
                        if (NIL == com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.dont_want_to_test_this_oneP(fort)) {
                            {
                                SubLObject store = NIL;
                                try {
                                    store = inference_datastructures_problem_store.new_problem_store(NIL);
                                    {
                                        SubLObject irrelevant_everywhereP = rkf_relevance_utilities.compute_fort_irrelevant_everywhere_status(fort, store);
                                        SubLObject irrelevant_nowhereP = (NIL != irrelevant_everywhereP) ? ((SubLObject) (NIL)) : rkf_relevance_utilities.compute_fort_irrelevant_nowhere_status(fort, store);
                                        SubLObject irrelevant_somewhereP = makeBoolean(NIL == irrelevant_nowhereP);
                                        SubLObject thclP = (NIL != irrelevant_nowhereP) ? ((SubLObject) (thcl.compute_thcl_status_of_fort(fort, NIL, T))) : NIL;
                                        val = (NIL != irrelevant_everywhereP) ? ((SubLObject) (ONE_INTEGER)) : NIL != irrelevant_somewhereP ? ((SubLObject) (TWO_INTEGER)) : NIL != thclP ? ((SubLObject) (THREE_INTEGER)) : ZERO_INTEGER;
                                    }
                                } finally {
                                    {
                                        SubLObject _prev_bind_0_1 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                        try {
                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                            inference_datastructures_problem_store.destroy_problem_store(store);
                                        } finally {
                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0_1, thread);
                                        }
                                    }
                                }
                            }
                        }
                    } finally {
                        $use_rkf_irrelevant_fort_cacheP$.rebind(_prev_bind_0, thread);
                    }
                }
                return val;
            }
        }
    }

    public static SubLObject compute_rkf_irrelevant_fort_cache_value(final SubLObject fort) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject val = NIL;
        final SubLObject _prev_bind_0 = $use_rkf_irrelevant_fort_cacheP$.currentBinding(thread);
        try {
            $use_rkf_irrelevant_fort_cacheP$.bind(NIL, thread);
            if (NIL == dont_want_to_test_this_oneP(fort)) {
                SubLObject store = NIL;
                try {
                    store = inference_datastructures_problem_store.new_problem_store(NIL);
                    final SubLObject irrelevant_everywhereP = rkf_relevance_utilities.compute_fort_irrelevant_everywhere_status(fort, store);
                    final SubLObject irrelevant_nowhereP = (NIL != irrelevant_everywhereP) ? NIL : rkf_relevance_utilities.compute_fort_irrelevant_nowhere_status(fort, store);
                    final SubLObject irrelevant_somewhereP = makeBoolean(NIL == irrelevant_nowhereP);
                    final SubLObject thclP = (NIL != irrelevant_nowhereP) ? thcl.compute_thcl_status_of_fort(fort, NIL, T, UNPROVIDED) : NIL;
                    val = (NIL != irrelevant_everywhereP) ? ONE_INTEGER : NIL != irrelevant_somewhereP ? TWO_INTEGER : NIL != thclP ? THREE_INTEGER : ZERO_INTEGER;
                } finally {
                    final SubLObject _prev_bind_0_$4 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values = getValuesAsVector();
                        inference_datastructures_problem_store.destroy_problem_store(store);
                        restoreValuesFromVector(_values);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$4, thread);
                    }
                }
            }
        } finally {
            $use_rkf_irrelevant_fort_cacheP$.rebind(_prev_bind_0, thread);
        }
        return val;
    }

    /**
     * Returns a list of all the forts in the current KB that don't have values in the
     * rkf irrelevant fort cache.
     *
     * @param FBC
     * 		The file backed cache of irrelevance values to verify. The file backed cache
     * 		should be of the form Key:fort -> Value:int
     * @return list of all forts that we don't have a cached rkf relevance for
     */
    @LispMethod(comment = "Returns a list of all the forts in the current KB that don\'t have values in the\r\nrkf irrelevant fort cache.\r\n\r\n@param FBC\r\n\t\tThe file backed cache of irrelevance values to verify. The file backed cache\r\n\t\tshould be of the form Key:fort -> Value:int\r\n@return list of all forts that we don\'t have a cached rkf relevance for\nReturns a list of all the forts in the current KB that don\'t have values in the\nrkf irrelevant fort cache.")
    public static final SubLObject verify_rkf_irrelevant_fort_cache_alt(SubLObject fbc) {
        if (fbc == UNPROVIDED) {
            fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(fbc, FILE_BACKED_CACHE_P);
            {
                SubLObject bad_forts = NIL;
                SubLObject message = $str_alt18$Looking_up_rkf_irrelevance_status;
                SubLObject total = forts.fort_count();
                SubLObject sofar = ZERO_INTEGER;
                {
                    SubLObject _prev_bind_0 = $last_percent_progress_index$.currentBinding(thread);
                    SubLObject _prev_bind_1 = $last_percent_progress_prediction$.currentBinding(thread);
                    SubLObject _prev_bind_2 = $within_noting_percent_progress$.currentBinding(thread);
                    SubLObject _prev_bind_3 = $percent_progress_start_time$.currentBinding(thread);
                    try {
                        $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
                        $last_percent_progress_prediction$.bind(NIL, thread);
                        $within_noting_percent_progress$.bind(T, thread);
                        $percent_progress_start_time$.bind(get_universal_time(), thread);
                        noting_percent_progress_preamble(message);
                        {
                            SubLObject cdolist_list_var = forts.do_forts_tables();
                            SubLObject table_var = NIL;
                            for (table_var = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , table_var = cdolist_list_var.first()) {
                                if (NIL == do_id_index_empty_p(table_var, $SKIP)) {
                                    {
                                        SubLObject id = do_id_index_next_id(table_var, T, NIL, NIL);
                                        SubLObject state_var = do_id_index_next_state(table_var, T, id, NIL);
                                        SubLObject fort = NIL;
                                        while (NIL != id) {
                                            fort = do_id_index_state_object(table_var, $SKIP, id, state_var);
                                            if (NIL != do_id_index_id_and_object_validP(id, fort, $SKIP)) {
                                                sofar = add(sofar, ONE_INTEGER);
                                                note_percent_progress(sofar, total);
                                                if (NIL == com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.dont_want_to_test_this_oneP(fort)) {
                                                    {
                                                        SubLObject val = file_backed_cache.file_backed_cache_lookup(fort, fbc, $NOT_FOUND, UNPROVIDED);
                                                        if (val == $NOT_FOUND) {
                                                            fort = cons(bad_forts, fort);
                                                        }
                                                    }
                                                }
                                            }
                                            id = do_id_index_next_id(table_var, T, id, state_var);
                                            state_var = do_id_index_next_state(table_var, T, id, state_var);
                                        } 
                                    }
                                }
                            }
                        }
                        noting_percent_progress_postamble();
                    } finally {
                        $percent_progress_start_time$.rebind(_prev_bind_3, thread);
                        $within_noting_percent_progress$.rebind(_prev_bind_2, thread);
                        $last_percent_progress_prediction$.rebind(_prev_bind_1, thread);
                        $last_percent_progress_index$.rebind(_prev_bind_0, thread);
                    }
                }
                return bad_forts;
            }
        }
    }

    /**
     * Returns a list of all the forts in the current KB that don't have values in the
     * rkf irrelevant fort cache.
     *
     * @param FBC
     * 		The file backed cache of irrelevance values to verify. The file backed cache
     * 		should be of the form Key:fort -> Value:int
     * @return list of all forts that we don't have a cached rkf relevance for
     */
    @LispMethod(comment = "Returns a list of all the forts in the current KB that don\'t have values in the\r\nrkf irrelevant fort cache.\r\n\r\n@param FBC\r\n\t\tThe file backed cache of irrelevance values to verify. The file backed cache\r\n\t\tshould be of the form Key:fort -> Value:int\r\n@return list of all forts that we don\'t have a cached rkf relevance for\nReturns a list of all the forts in the current KB that don\'t have values in the\nrkf irrelevant fort cache.")
    public static SubLObject verify_rkf_irrelevant_fort_cache(SubLObject fbc) {
        if (fbc == UNPROVIDED) {
            fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != file_backed_cache.file_backed_cache_p(fbc) : "! file_backed_cache.file_backed_cache_p(fbc) " + ("file_backed_cache.file_backed_cache_p(fbc) " + "CommonSymbols.NIL != file_backed_cache.file_backed_cache_p(fbc) ") + fbc;
        final SubLObject bad_forts = NIL;
        final SubLObject message = $str18$Looking_up_rkf_irrelevance_status;
        final SubLObject total = forts.fort_count();
        SubLObject sofar = ZERO_INTEGER;
        final SubLObject _prev_bind_0 = $last_percent_progress_index$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $last_percent_progress_prediction$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $within_noting_percent_progress$.currentBinding(thread);
        final SubLObject _prev_bind_4 = $percent_progress_start_time$.currentBinding(thread);
        try {
            $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
            $last_percent_progress_prediction$.bind(NIL, thread);
            $within_noting_percent_progress$.bind(T, thread);
            $percent_progress_start_time$.bind(get_universal_time(), thread);
            try {
                noting_percent_progress_preamble(message);
                SubLObject cdolist_list_var = forts.do_forts_tables();
                SubLObject table_var = NIL;
                table_var = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject idx = table_var;
                    if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
                        final SubLObject idx_$5 = idx;
                        if (NIL == id_index_dense_objects_empty_p(idx_$5, $SKIP)) {
                            final SubLObject vector_var = id_index_dense_objects(idx_$5);
                            final SubLObject backwardP_var = NIL;
                            SubLObject length;
                            SubLObject v_iteration;
                            SubLObject id;
                            SubLObject fort;
                            SubLObject val;
                            for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                                fort = aref(vector_var, id);
                                if ((NIL == id_index_tombstone_p(fort)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                                    if (NIL != id_index_tombstone_p(fort)) {
                                        fort = $SKIP;
                                    }
                                    sofar = add(sofar, ONE_INTEGER);
                                    note_percent_progress(sofar, total);
                                    if (NIL == dont_want_to_test_this_oneP(fort)) {
                                        val = file_backed_cache.file_backed_cache_lookup(fort, fbc, $NOT_FOUND, UNPROVIDED);
                                        if (val == $NOT_FOUND) {
                                            fort = cons(bad_forts, fort);
                                        }
                                    }
                                }
                            }
                        }
                        final SubLObject idx_$6 = idx;
                        if ((NIL == id_index_sparse_objects_empty_p(idx_$6)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                            final SubLObject sparse = id_index_sparse_objects(idx_$6);
                            SubLObject id2 = id_index_sparse_id_threshold(idx_$6);
                            final SubLObject end_id = id_index_next_id(idx_$6);
                            final SubLObject v_default = (NIL != id_index_skip_tombstones_p($SKIP)) ? NIL : $SKIP;
                            while (id2.numL(end_id)) {
                                SubLObject fort2 = gethash_without_values(id2, sparse, v_default);
                                if ((NIL == id_index_skip_tombstones_p($SKIP)) || (NIL == id_index_tombstone_p(fort2))) {
                                    sofar = add(sofar, ONE_INTEGER);
                                    note_percent_progress(sofar, total);
                                    if (NIL == dont_want_to_test_this_oneP(fort2)) {
                                        final SubLObject val2 = file_backed_cache.file_backed_cache_lookup(fort2, fbc, $NOT_FOUND, UNPROVIDED);
                                        if (val2 == $NOT_FOUND) {
                                            fort2 = cons(bad_forts, fort2);
                                        }
                                    }
                                }
                                id2 = add(id2, ONE_INTEGER);
                            } 
                        }
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    table_var = cdolist_list_var.first();
                } 
            } finally {
                final SubLObject _prev_bind_0_$7 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    noting_percent_progress_postamble();
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$7, thread);
                }
            }
        } finally {
            $percent_progress_start_time$.rebind(_prev_bind_4, thread);
            $within_noting_percent_progress$.rebind(_prev_bind_3, thread);
            $last_percent_progress_prediction$.rebind(_prev_bind_2, thread);
            $last_percent_progress_index$.rebind(_prev_bind_0, thread);
        }
        return bad_forts;
    }

    /**
     * Return wether the given fort is not worth performing a test on.
     * Currently we don't bother performing relevance tests on skolems.
     *
     * @param FORT
     * 		the fort to check if it's not worth perfoming a relevance test on
     * @return true if we shouldn't perform relevance tests on this FORT
     */
    @LispMethod(comment = "Return wether the given fort is not worth performing a test on.\r\nCurrently we don\'t bother performing relevance tests on skolems.\r\n\r\n@param FORT\r\n\t\tthe fort to check if it\'s not worth perfoming a relevance test on\r\n@return true if we shouldn\'t perform relevance tests on this FORT\nReturn wether the given fort is not worth performing a test on.\nCurrently we don\'t bother performing relevance tests on skolems.")
    public static final SubLObject dont_want_to_test_this_oneP_alt(SubLObject fort) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject is_wff = NIL;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
                        mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
                        is_wff = (NIL != constant_p(fort)) ? ((SubLObject) (T)) : wff.el_wft_fastP(narts_high.nart_el_formula(fort), UNPROVIDED);
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return makeBoolean(((NIL != term.skolem_nartP(fort)) || (NIL != term.reified_skolem_fnP(fort))) || (NIL == is_wff));
            }
        }
    }

    /**
     * Return wether the given fort is not worth performing a test on.
     * Currently we don't bother performing relevance tests on skolems.
     *
     * @param FORT
     * 		the fort to check if it's not worth perfoming a relevance test on
     * @return true if we shouldn't perform relevance tests on this FORT
     */
    @LispMethod(comment = "Return wether the given fort is not worth performing a test on.\r\nCurrently we don\'t bother performing relevance tests on skolems.\r\n\r\n@param FORT\r\n\t\tthe fort to check if it\'s not worth perfoming a relevance test on\r\n@return true if we shouldn\'t perform relevance tests on this FORT\nReturn wether the given fort is not worth performing a test on.\nCurrently we don\'t bother performing relevance tests on skolems.")
    public static SubLObject dont_want_to_test_this_oneP(final SubLObject fort) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject is_wff = NIL;
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_EVERYTHING, thread);
            mt_relevance_macros.$mt$.bind($$EverythingPSC, thread);
            is_wff = (NIL != constant_p(fort)) ? T : wff.el_wft_fastP(narts_high.nart_el_formula(fort), UNPROVIDED);
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return makeBoolean(((NIL != term.skolem_nartP(fort)) || (NIL != term.reified_skolem_fnP(fort))) || (NIL == is_wff));
    }

    /**
     * Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,
     * :irrelevant-nowhere, :not-found
     *
     * @param KEY
     * 		The term we want to look up whether it's rkf-irrelevant, currently only
     * 		forts are cached, other types will cause DEFAULT-VALUE to be returned. Assumes
     * 		canonicalized values.
     * @return Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,
    :irrelevant-nowhere, :not-found
     */
    @LispMethod(comment = "Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,\r\n:irrelevant-nowhere, :not-found\r\n\r\n@param KEY\r\n\t\tThe term we want to look up whether it\'s rkf-irrelevant, currently only\r\n\t\tforts are cached, other types will cause DEFAULT-VALUE to be returned. Assumes\r\n\t\tcanonicalized values.\r\n@return Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,\r\n:irrelevant-nowhere, :not-found\nReturns one of: :irrelevant-everywhere, :irrelevant-somewhere,\n:irrelevant-nowhere, :not-found")
    public static final SubLObject rkf_irrelevant_cache_lookup_alt(SubLObject key) {
        {
            SubLObject default_value = $NOT_FOUND;
            SubLObject val = com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.rkf_irrelevant_cache_value(key, default_value);
            SubLObject result = (val == ZERO_INTEGER) ? ((SubLObject) ($IRRELEVANT_NOWHERE)) : val == ONE_INTEGER ? ((SubLObject) ($IRRELEVANT_EVERYWHERE)) : val == TWO_INTEGER ? ((SubLObject) ($IRRELEVANT_SOMEWHERE)) : val == THREE_INTEGER ? ((SubLObject) ($IRRELEVANT_NOWHERE)) : val == default_value ? ((SubLObject) (default_value)) : Errors.error($str_alt25$Found_an_invalid_value_in_rkf_irr, val, key);
            return result;
        }
    }

    /**
     * Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,
     * :irrelevant-nowhere, :not-found
     *
     * @param KEY
     * 		The term we want to look up whether it's rkf-irrelevant, currently only
     * 		forts are cached, other types will cause DEFAULT-VALUE to be returned. Assumes
     * 		canonicalized values.
     * @return Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,
    :irrelevant-nowhere, :not-found
     */
    @LispMethod(comment = "Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,\r\n:irrelevant-nowhere, :not-found\r\n\r\n@param KEY\r\n\t\tThe term we want to look up whether it\'s rkf-irrelevant, currently only\r\n\t\tforts are cached, other types will cause DEFAULT-VALUE to be returned. Assumes\r\n\t\tcanonicalized values.\r\n@return Returns one of: :irrelevant-everywhere, :irrelevant-somewhere,\r\n:irrelevant-nowhere, :not-found\nReturns one of: :irrelevant-everywhere, :irrelevant-somewhere,\n:irrelevant-nowhere, :not-found")
    public static SubLObject rkf_irrelevant_cache_lookup(final SubLObject key) {
        final SubLObject default_value = $NOT_FOUND;
        final SubLObject val = rkf_irrelevant_cache_value(key, default_value);
        SubLObject result = default_value;
        if (val.eql(ZERO_INTEGER)) {
            result = $IRRELEVANT_NOWHERE;
        } else
            if (val.eql(ONE_INTEGER)) {
                result = $IRRELEVANT_EVERYWHERE;
            } else
                if (val.eql(TWO_INTEGER)) {
                    result = $IRRELEVANT_SOMEWHERE;
                } else
                    if (val.eql(THREE_INTEGER)) {
                        result = $IRRELEVANT_NOWHERE;
                    } else
                        if (val.eql(FOUR_INTEGER)) {
                            reset_rkf_irrelevant_cache_for_fort(key);
                            if (!FOUR_INTEGER.eql(rkf_irrelevant_cache_value(key, UNPROVIDED))) {
                                result = rkf_irrelevant_cache_lookup(key);
                            }
                        } else
                            if (!val.eql(default_value)) {
                                Errors.error($str25$Found_an_invalid_value_in_rkf_irr, val, key);
                            }





        return result;
    }

    public static final SubLObject rkf_irrelevant_cache_supports_thclP_alt() {
        return com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.rkf_irrelevant_cache_value($THCL_SUPPORT, NIL);
    }

    public static SubLObject rkf_irrelevant_cache_supports_thclP() {
        return rkf_irrelevant_cache_value($THCL_SUPPORT, NIL);
    }

    /**
     *
     *
     * @return BOOLEANP; Does the RKF irrelevance cache say FORT is THCL?
     */
    @LispMethod(comment = "@return BOOLEANP; Does the RKF irrelevance cache say FORT is THCL?")
    public static final SubLObject rkf_irrelevant_cache_thclP_alt(SubLObject fort) {
        return eql(THREE_INTEGER, com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.rkf_irrelevant_cache_value(fort, $NOT_FOUND));
    }

    /**
     *
     *
     * @return BOOLEANP; Does the RKF irrelevance cache say FORT is THCL?
     */
    @LispMethod(comment = "@return BOOLEANP; Does the RKF irrelevance cache say FORT is THCL?")
    public static SubLObject rkf_irrelevant_cache_thclP(final SubLObject fort) {
        return eql(THREE_INTEGER, rkf_irrelevant_cache_value(fort, $NOT_FOUND));
    }

    /**
     * Iterate over the RKF irrelevance FORT cache, executing BODY once with FORT bound
     * to each THCL FORT found therein.
     */
    @LispMethod(comment = "Iterate over the RKF irrelevance FORT cache, executing BODY once with FORT bound\r\nto each THCL FORT found therein.\nIterate over the RKF irrelevance FORT cache, executing BODY once with FORT bound\nto each THCL FORT found therein.")
    public static final SubLObject do_thcl_forts_from_rkf_irrelevance_cache_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt26);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject fort = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt26);
                    fort = current.first();
                    current = current.rest();
                    {
                        SubLObject allow_other_keys_p = NIL;
                        SubLObject rest = current;
                        SubLObject bad = NIL;
                        SubLObject current_2 = NIL;
                        for (; NIL != rest;) {
                            destructuring_bind_must_consp(rest, datum, $list_alt26);
                            current_2 = rest.first();
                            rest = rest.rest();
                            destructuring_bind_must_consp(rest, datum, $list_alt26);
                            if (NIL == member(current_2, $list_alt27, UNPROVIDED, UNPROVIDED)) {
                                bad = T;
                            }
                            if (current_2 == $ALLOW_OTHER_KEYS) {
                                allow_other_keys_p = rest.first();
                            }
                            rest = rest.rest();
                        }
                        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                            cdestructuring_bind_error(datum, $list_alt26);
                        }
                        {
                            SubLObject message_tail = property_list_member($MESSAGE, current);
                            SubLObject message = (NIL != message_tail) ? ((SubLObject) (cadr(message_tail))) : NIL;
                            current = temp;
                            {
                                SubLObject body = current;
                                SubLObject value = $sym30$VALUE;
                                return list(DO_FILE_HASH_TABLE, list(fort, value, $list_alt32, $MESSAGE, message), listS(PWHEN, list(EQL, THREE_INTEGER, value), append(body, NIL)));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Iterate over the RKF irrelevance FORT cache, executing BODY once with FORT bound
     * to each THCL FORT found therein.
     */
    @LispMethod(comment = "Iterate over the RKF irrelevance FORT cache, executing BODY once with FORT bound\r\nto each THCL FORT found therein.\nIterate over the RKF irrelevance FORT cache, executing BODY once with FORT bound\nto each THCL FORT found therein.")
    public static SubLObject do_thcl_forts_from_rkf_irrelevance_cache(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list26);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject fort = NIL;
        destructuring_bind_must_consp(current, datum, $list26);
        fort = current.first();
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$8 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list26);
            current_$8 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list26);
            if (NIL == member(current_$8, $list27, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$8 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list26);
        }
        final SubLObject message_tail = property_list_member($MESSAGE, current);
        final SubLObject message = (NIL != message_tail) ? cadr(message_tail) : NIL;
        final SubLObject body;
        current = body = temp;
        final SubLObject value = $sym30$VALUE;
        return list(DO_FILE_HASH_TABLE, list(fort, value, $list32, $MESSAGE, message), listS(PWHEN, list(EQL, THREE_INTEGER, value), append(body, NIL)));
    }

    /**
     * Returns whatever is in the cache for KEY, or DEFAULT-VALUE if not found.
     */
    @LispMethod(comment = "Returns whatever is in the cache for KEY, or DEFAULT-VALUE if not found.")
    public static final SubLObject rkf_irrelevant_cache_value_alt(SubLObject key, SubLObject default_value) {
        if (default_value == UNPROVIDED) {
            default_value = $NOT_FOUND;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
                SubLObject should_warn = NIL;
                SubLObject msg = NIL;
                SubLObject result = default_value;
                if (NIL == $use_rkf_irrelevant_fort_cacheP$.getDynamicValue(thread)) {
                    return default_value;
                }
                if (NIL == forts.fort_p(key)) {
                    return default_value;
                }
                try {
                    {
                        SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                        try {
                            Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                            try {
                                if (NIL == fbc) {
                                    {
                                        SubLObject msg_3 = NIL;
                                        try {
                                            {
                                                SubLObject _prev_bind_0_4 = Errors.$error_handler$.currentBinding(thread);
                                                try {
                                                    Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                    try {
                                                        com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.initialize_rkf_irrelevant_fort_cache();
                                                        fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
                                                    } catch (Throwable catch_var) {
                                                        Errors.handleThrowable(catch_var, NIL);
                                                    }
                                                } finally {
                                                    Errors.$error_handler$.rebind(_prev_bind_0_4, thread);
                                                }
                                            }
                                        } catch (Throwable ccatch_env_var) {
                                            msg_3 = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                        }
                                        if (NIL != msg_3) {
                                            if (NIL != should_warn) {
                                                Errors.warn(msg_3);
                                            }
                                        }
                                    }
                                }
                                SubLTrampolineFile.checkType(fbc, FILE_BACKED_CACHE_P);
                                result = file_backed_cache.file_backed_cache_lookup(key, fbc, default_value, UNPROVIDED);
                            } catch (Throwable catch_var) {
                                Errors.handleThrowable(catch_var, NIL);
                            }
                        } finally {
                            Errors.$error_handler$.rebind(_prev_bind_0, thread);
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    msg = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                }
                if (NIL != msg) {
                    if (NIL != should_warn) {
                        Errors.warn(msg);
                    }
                }
                return result;
            }
        }
    }

    /**
     * Returns whatever is in the cache for KEY, or DEFAULT-VALUE if not found.
     */
    @LispMethod(comment = "Returns whatever is in the cache for KEY, or DEFAULT-VALUE if not found.")
    public static SubLObject rkf_irrelevant_cache_value(final SubLObject key, SubLObject default_value) {
        if (default_value == UNPROVIDED) {
            default_value = $NOT_FOUND;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
        final SubLObject should_warn = NIL;
        SubLObject msg = NIL;
        SubLObject result = default_value;
        if (NIL == $use_rkf_irrelevant_fort_cacheP$.getDynamicValue(thread)) {
            return default_value;
        }
        if (NIL == forts.fort_p(key)) {
            return default_value;
        }
        try {
            thread.throwStack.push($catch_error_message_target$.getGlobalValue());
            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
            try {
                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                try {
                    if (NIL == fbc) {
                        SubLObject msg_$9 = NIL;
                        try {
                            thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                            final SubLObject _prev_bind_0_$10 = Errors.$error_handler$.currentBinding(thread);
                            try {
                                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                try {
                                    initialize_rkf_irrelevant_fort_cache();
                                    fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
                                } catch (final Throwable catch_var) {
                                    Errors.handleThrowable(catch_var, NIL);
                                }
                            } finally {
                                Errors.$error_handler$.rebind(_prev_bind_0_$10, thread);
                            }
                        } catch (final Throwable ccatch_env_var) {
                            msg_$9 = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                        } finally {
                            thread.throwStack.pop();
                        }
                        if ((NIL != msg_$9) && (NIL != should_warn)) {
                            Errors.warn(msg_$9);
                        }
                    }
                    assert NIL != file_backed_cache.file_backed_cache_p(fbc) : "! file_backed_cache.file_backed_cache_p(fbc) " + ("file_backed_cache.file_backed_cache_p(fbc) " + "CommonSymbols.NIL != file_backed_cache.file_backed_cache_p(fbc) ") + fbc;
                    result = file_backed_cache.file_backed_cache_lookup(key, fbc, default_value, UNPROVIDED);
                } catch (final Throwable catch_var2) {
                    Errors.handleThrowable(catch_var2, NIL);
                }
            } finally {
                Errors.$error_handler$.rebind(_prev_bind_0, thread);
            }
        } catch (final Throwable ccatch_env_var2) {
            msg = Errors.handleThrowable(ccatch_env_var2, $catch_error_message_target$.getGlobalValue());
        } finally {
            thread.throwStack.pop();
        }
        if ((NIL != msg) && (NIL != should_warn)) {
            Errors.warn(msg);
        }
        return result;
    }

    /**
     * Allows one to enter a cached rkf irrelevance value for a new fort, or to override a
     * cached value in the given file backed cache, FBC.
     * Stores Key:FORT -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL
     * is an integer 0 = irrelevant nowhere, 1 = irrelevant
     * everywhere, 2 = irrelevant somewhere. Any new cache entries
     * are memory-based and ephemeral. If you want to permanently save the value,
     * you'll have to regenerate the file-based cache.
     *
     * @param FORT
     * 		The fort we want to enter a new value for (assumes it's already
     * 		canonoicalized)
     * @param VAL
     * 		The value that should be associated with key. Should be one of
     * 		:irrelevant-nowhere, :irrelevant-everywhere, or :irrelevant-somewhere, or :thcl.
     * @param FBC
     * 		The file backed cache to add the value to. The file backed cache
     * 		should be of the form Key:fort -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL
     * 		is an integer 0 = irrelevant nowhere, 1 = irrelevant
     * 		everywhere, 2 = irrelevant somewhere.
     * @return integer value stored in the hash
     */
    @LispMethod(comment = "Allows one to enter a cached rkf irrelevance value for a new fort, or to override a\r\ncached value in the given file backed cache, FBC.\r\nStores Key:FORT -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL\r\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\r\neverywhere, 2 = irrelevant somewhere. Any new cache entries\r\nare memory-based and ephemeral. If you want to permanently save the value,\r\nyou\'ll have to regenerate the file-based cache.\r\n\r\n@param FORT\r\n\t\tThe fort we want to enter a new value for (assumes it\'s already\r\n\t\tcanonoicalized)\r\n@param VAL\r\n\t\tThe value that should be associated with key. Should be one of\r\n\t\t:irrelevant-nowhere, :irrelevant-everywhere, or :irrelevant-somewhere, or :thcl.\r\n@param FBC\r\n\t\tThe file backed cache to add the value to. The file backed cache\r\n\t\tshould be of the form Key:fort -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL\r\n\t\tis an integer 0 = irrelevant nowhere, 1 = irrelevant\r\n\t\teverywhere, 2 = irrelevant somewhere.\r\n@return integer value stored in the hash\nAllows one to enter a cached rkf irrelevance value for a new fort, or to override a\ncached value in the given file backed cache, FBC.\nStores Key:FORT -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\neverywhere, 2 = irrelevant somewhere. Any new cache entries\nare memory-based and ephemeral. If you want to permanently save the value,\nyou\'ll have to regenerate the file-based cache.")
    public static final SubLObject rkf_irrelevant_cache_enter_alt(SubLObject key, SubLObject val, SubLObject fbc) {
        if (fbc == UNPROVIDED) {
            fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == $use_rkf_irrelevant_fort_cacheP$.getDynamicValue(thread)) {
                return NIL;
            }
            SubLTrampolineFile.checkType(fbc, FILE_BACKED_CACHE_P);
            if (NIL == $rkf_irrelevant_fort_cache$.getGlobalValue()) {
                {
                    SubLObject msg = NIL;
                    try {
                        {
                            SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                            try {
                                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                try {
                                    com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.initialize_rkf_irrelevant_fort_cache();
                                    fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
                                } catch (Throwable catch_var) {
                                    Errors.handleThrowable(catch_var, NIL);
                                }
                            } finally {
                                Errors.$error_handler$.rebind(_prev_bind_0, thread);
                            }
                        }
                    } catch (Throwable ccatch_env_var) {
                        msg = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                    }
                    if (NIL != msg) {
                        Errors.warn(msg);
                        return NIL;
                    }
                }
            }
            {
                SubLObject value = (val == $IRRELEVANT_NOWHERE) ? ((SubLObject) (ZERO_INTEGER)) : val == $IRRELEVANT_EVERYWHERE ? ((SubLObject) (ONE_INTEGER)) : val == $IRRELEVANT_SOMEWHERE ? ((SubLObject) (TWO_INTEGER)) : val == $THCL ? ((SubLObject) (THREE_INTEGER)) : Errors.error($str_alt36$Trying_to_insert_invalid_value_in, val, key);
                return file_backed_cache.file_backed_cache_enter(key, value, fbc);
            }
        }
    }

    /**
     * Allows one to enter a cached rkf irrelevance value for a new fort, or to override a
     * cached value in the given file backed cache, FBC.
     * Stores Key:FORT -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL
     * is an integer 0 = irrelevant nowhere, 1 = irrelevant
     * everywhere, 2 = irrelevant somewhere. Any new cache entries
     * are memory-based and ephemeral. If you want to permanently save the value,
     * you'll have to regenerate the file-based cache.
     *
     * @param FORT
     * 		The fort we want to enter a new value for (assumes it's already
     * 		canonoicalized)
     * @param VAL
     * 		The value that should be associated with key. Should be one of
     * 		:irrelevant-nowhere, :irrelevant-everywhere, or :irrelevant-somewhere, or :thcl.
     * @param FBC
     * 		The file backed cache to add the value to. The file backed cache
     * 		should be of the form Key:fort -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL
     * 		is an integer 0 = irrelevant nowhere, 1 = irrelevant
     * 		everywhere, 2 = irrelevant somewhere.
     * @return integer value stored in the hash
     */
    @LispMethod(comment = "Allows one to enter a cached rkf irrelevance value for a new fort, or to override a\r\ncached value in the given file backed cache, FBC.\r\nStores Key:FORT -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL\r\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\r\neverywhere, 2 = irrelevant somewhere. Any new cache entries\r\nare memory-based and ephemeral. If you want to permanently save the value,\r\nyou\'ll have to regenerate the file-based cache.\r\n\r\n@param FORT\r\n\t\tThe fort we want to enter a new value for (assumes it\'s already\r\n\t\tcanonoicalized)\r\n@param VAL\r\n\t\tThe value that should be associated with key. Should be one of\r\n\t\t:irrelevant-nowhere, :irrelevant-everywhere, or :irrelevant-somewhere, or :thcl.\r\n@param FBC\r\n\t\tThe file backed cache to add the value to. The file backed cache\r\n\t\tshould be of the form Key:fort -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL\r\n\t\tis an integer 0 = irrelevant nowhere, 1 = irrelevant\r\n\t\teverywhere, 2 = irrelevant somewhere.\r\n@return integer value stored in the hash\nAllows one to enter a cached rkf irrelevance value for a new fort, or to override a\ncached value in the given file backed cache, FBC.\nStores Key:FORT -> Value:IRRELEVANT-VAL where an IRRELEVANT-VAL\nis an integer 0 = irrelevant nowhere, 1 = irrelevant\neverywhere, 2 = irrelevant somewhere. Any new cache entries\nare memory-based and ephemeral. If you want to permanently save the value,\nyou\'ll have to regenerate the file-based cache.")
    public static SubLObject rkf_irrelevant_cache_enter(final SubLObject key, final SubLObject val, SubLObject fbc) {
        if (fbc == UNPROVIDED) {
            fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == $use_rkf_irrelevant_fort_cacheP$.getDynamicValue(thread)) {
            return NIL;
        }
        assert NIL != file_backed_cache.file_backed_cache_p(fbc) : "! file_backed_cache.file_backed_cache_p(fbc) " + ("file_backed_cache.file_backed_cache_p(fbc) " + "CommonSymbols.NIL != file_backed_cache.file_backed_cache_p(fbc) ") + fbc;
        if (NIL == $rkf_irrelevant_fort_cache$.getGlobalValue()) {
            SubLObject msg = NIL;
            try {
                thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                try {
                    Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                    try {
                        initialize_rkf_irrelevant_fort_cache();
                        fbc = $rkf_irrelevant_fort_cache$.getGlobalValue();
                    } catch (final Throwable catch_var) {
                        Errors.handleThrowable(catch_var, NIL);
                    }
                } finally {
                    Errors.$error_handler$.rebind(_prev_bind_0, thread);
                }
            } catch (final Throwable ccatch_env_var) {
                msg = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
            } finally {
                thread.throwStack.pop();
            }
            if (NIL != msg) {
                Errors.warn(msg);
                return NIL;
            }
        }
        final SubLObject value = (val == $IRRELEVANT_NOWHERE) ? ZERO_INTEGER : val == $IRRELEVANT_EVERYWHERE ? ONE_INTEGER : val == $IRRELEVANT_SOMEWHERE ? TWO_INTEGER : val == $THCL ? THREE_INTEGER : val == $RESET ? FOUR_INTEGER : Errors.error($str37$Trying_to_insert_invalid_value_in, val, key);
        return file_backed_cache.file_backed_cache_enter(key, value, fbc);
    }

    /**
     * Returns the the rkf irrelevant fort cache as a file backed cache.
     */
    @LispMethod(comment = "Returns the the rkf irrelevant fort cache as a file backed cache.")
    public static final SubLObject get_rkf_irrelevant_fort_cache_alt() {
        return $rkf_irrelevant_fort_cache$.getGlobalValue();
    }

    /**
     * Returns the the rkf irrelevant fort cache as a file backed cache.
     */
    @LispMethod(comment = "Returns the the rkf irrelevant fort cache as a file backed cache.")
    public static SubLObject get_rkf_irrelevant_fort_cache() {
        return $rkf_irrelevant_fort_cache$.getGlobalValue();
    }

    public static final SubLObject reset_rkf_irrelevant_cache_for_fort_alt(SubLObject fort) {
        SubLTrampolineFile.checkType(fort, FORT_P);
        {
            SubLObject integer_value = com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.compute_rkf_irrelevant_fort_cache_value(fort);
            SubLObject keyword_value = com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.convert_irrelevance_number_to_keyword(integer_value);
            if (NIL == file_backed_cache.file_backed_cache_p(com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.get_rkf_irrelevant_fort_cache())) {
                com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.initialize_rkf_irrelevant_fort_cache();
            }
            return com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.rkf_irrelevant_cache_enter(fort, keyword_value, UNPROVIDED);
        }
    }

    public static SubLObject reset_rkf_irrelevant_cache_for_fort(final SubLObject fort) {
        assert NIL != forts.fort_p(fort) : "! forts.fort_p(fort) " + ("forts.fort_p(fort) " + "CommonSymbols.NIL != forts.fort_p(fort) ") + fort;
        final SubLObject integer_value = compute_rkf_irrelevant_fort_cache_value(fort);
        final SubLObject keyword_value = convert_irrelevance_number_to_keyword(integer_value);
        if (NIL == file_backed_cache.file_backed_cache_p(get_rkf_irrelevant_fort_cache())) {
            initialize_rkf_irrelevant_fort_cache();
        }
        return rkf_irrelevant_cache_enter(fort, keyword_value, UNPROVIDED);
    }

    public static final SubLObject convert_irrelevance_number_to_keyword_alt(SubLObject integer) {
        {
            SubLObject pcase_var = integer;
            if (pcase_var.eql(ZERO_INTEGER)) {
                return $IRRELEVANT_NOWHERE;
            } else {
                if (pcase_var.eql(ONE_INTEGER)) {
                    return $IRRELEVANT_EVERYWHERE;
                } else {
                    if (pcase_var.eql(TWO_INTEGER)) {
                        return $IRRELEVANT_SOMEWHERE;
                    } else {
                        if (pcase_var.eql(THREE_INTEGER)) {
                            return $THCL;
                        } else {
                            Errors.error($str_alt38$_A_can_not_be_converted_to_an_irr, integer);
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject convert_irrelevance_number_to_keyword(final SubLObject integer) {
        if (integer.eql(ZERO_INTEGER)) {
            return $IRRELEVANT_NOWHERE;
        }
        if (integer.eql(ONE_INTEGER)) {
            return $IRRELEVANT_EVERYWHERE;
        }
        if (integer.eql(TWO_INTEGER)) {
            return $IRRELEVANT_SOMEWHERE;
        }
        if (integer.eql(THREE_INTEGER)) {
            return $THCL;
        }
        if (integer.eql(FOUR_INTEGER)) {
            return $RESET;
        }
        Errors.error($str39$_A_can_not_be_converted_to_an_irr, integer);
        return NIL;
    }

    public static final SubLObject rkf_irrelevant_assertion_cache_clear_alt(SubLObject source, SubLObject assertion) {
        {
            SubLObject v_term = cycl_utilities.formula_arg1(assertion, UNPROVIDED);
            if (NIL != forts.fort_p(v_term)) {
                com.cyc.cycjava.cycl.rkf_irrelevant_fort_cache.reset_rkf_irrelevant_cache_for_fort(v_term);
                {
                    SubLObject event = kb_modification_event.post_kb_modify_irrelevance_event(v_term, assertion);
                    if (NIL != event) {
                        cure_api.clear_term_learner_irrelevancies_for_term(event, UNPROVIDED);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject rkf_irrelevant_assertion_cache_clear(SubLObject source, final SubLObject assertion) {
        final SubLObject v_term = assertions_high.gaf_arg1(assertion);
        if (NIL != forts.fort_p(v_term)) {
            rkf_irrelevant_cache_enter(v_term, $RESET, UNPROVIDED);
            final SubLObject event = kb_modification_event.post_kb_modify_irrelevance_event(v_term, assertion);
            if (NIL != event) {
                cure_api.clear_term_learner_irrelevancies_for_term(event, UNPROVIDED);
            }
        }
        return NIL;
    }

    public static SubLObject declare_rkf_irrelevant_fort_cache_file() {
        declareMacro("with_default_rkf_irrelevant_cache_file_path", "WITH-DEFAULT-RKF-IRRELEVANT-CACHE-FILE-PATH");
        declareFunction("get_default_rkf_irrelevant_cache_file_path", "GET-DEFAULT-RKF-IRRELEVANT-CACHE-FILE-PATH", 0, 1, false);
        declareFunction("get_latest_rkf_irrelevant_cache_file_path", "GET-LATEST-RKF-IRRELEVANT-CACHE-FILE-PATH", 0, 0, false);
        declareFunction("get_rkf_irrelevant_cache_file_path_internal", "GET-RKF-IRRELEVANT-CACHE-FILE-PATH-INTERNAL", 1, 0, false);
        declareFunction("initialize_rkf_irrelevant_fort_cache", "INITIALIZE-RKF-IRRELEVANT-FORT-CACHE", 0, 0, false);
        declareFunction("reset_rkf_irrelevant_fort_cache", "RESET-RKF-IRRELEVANT-FORT-CACHE", 0, 0, false);
        declareFunction("create_rkf_irrelevant_fort_cache", "CREATE-RKF-IRRELEVANT-FORT-CACHE", 0, 2, false);
        declareFunction("compute_rkf_irrelevant_fort_cache_value", "COMPUTE-RKF-IRRELEVANT-FORT-CACHE-VALUE", 1, 0, false);
        declareFunction("verify_rkf_irrelevant_fort_cache", "VERIFY-RKF-IRRELEVANT-FORT-CACHE", 0, 1, false);
        declareFunction("dont_want_to_test_this_oneP", "DONT-WANT-TO-TEST-THIS-ONE?", 1, 0, false);
        declareFunction("rkf_irrelevant_cache_lookup", "RKF-IRRELEVANT-CACHE-LOOKUP", 1, 0, false);
        declareFunction("rkf_irrelevant_cache_supports_thclP", "RKF-IRRELEVANT-CACHE-SUPPORTS-THCL?", 0, 0, false);
        declareFunction("rkf_irrelevant_cache_thclP", "RKF-IRRELEVANT-CACHE-THCL?", 1, 0, false);
        declareMacro("do_thcl_forts_from_rkf_irrelevance_cache", "DO-THCL-FORTS-FROM-RKF-IRRELEVANCE-CACHE");
        declareFunction("rkf_irrelevant_cache_value", "RKF-IRRELEVANT-CACHE-VALUE", 1, 1, false);
        declareFunction("rkf_irrelevant_cache_enter", "RKF-IRRELEVANT-CACHE-ENTER", 2, 1, false);
        declareFunction("get_rkf_irrelevant_fort_cache", "GET-RKF-IRRELEVANT-FORT-CACHE", 0, 0, false);
        declareFunction("reset_rkf_irrelevant_cache_for_fort", "RESET-RKF-IRRELEVANT-CACHE-FOR-FORT", 1, 0, false);
        declareFunction("convert_irrelevance_number_to_keyword", "CONVERT-IRRELEVANCE-NUMBER-TO-KEYWORD", 1, 0, false);
        declareFunction("rkf_irrelevant_assertion_cache_clear", "RKF-IRRELEVANT-ASSERTION-CACHE-CLEAR", 2, 0, false);
        return NIL;
    }

    public static final SubLObject init_rkf_irrelevant_fort_cache_file_alt() {
        deflexical("*RKF-IRRELEVANT-FORT-CACHE*", NIL);
        defparameter("*RKF-IRRELEVANT-FHT-NAME*", NIL);
        defparameter("*USE-RKF-IRRELEVANT-FORT-CACHE?*", T);
        deflexical("*RKF-IRRELEVANT-FHT-LOCK*", make_lock($str_alt0$rkf_irrelevant_fht_lock));
        deflexical("*RKF-IRRELEVANT-CACHE-BASE-FILE-NAME*", red_infrastructure_macros.red_def_helper($list_alt1.isSymbol() ? ((SubLObject) (symbol_value($list_alt1))) : $list_alt1, $rkf_irrelevant_cache_base_file_name$, $str_alt3$rkf_irrelevant_fort_cache_fht.isSymbol() ? ((SubLObject) (symbol_value($str_alt3$rkf_irrelevant_fort_cache_fht))) : $str_alt3$rkf_irrelevant_fort_cache_fht, $LEXICAL, UNPROVIDED));
        deflexical("*PRELOAD-RKF-IRRELEVANT-FORT-CACHE?*", NIL);
        return NIL;
    }

    public static SubLObject init_rkf_irrelevant_fort_cache_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*RKF-IRRELEVANT-FORT-CACHE*", NIL);
            defparameter("*RKF-IRRELEVANT-FHT-NAME*", NIL);
            defparameter("*USE-RKF-IRRELEVANT-FORT-CACHE?*", T);
            deflexical("*RKF-IRRELEVANT-FHT-LOCK*", make_lock($str0$rkf_irrelevant_fht_lock));
            deflexical("*RKF-IRRELEVANT-CACHE-BASE-FILE-NAME*", red_infrastructure_macros.red_def_helper($list1.isSymbol() ? symbol_value($list1) : $list1, $rkf_irrelevant_cache_base_file_name$, $str3$rkf_irrelevant_fort_cache_fht.isSymbol() ? symbol_value($str3$rkf_irrelevant_fort_cache_fht) : $str3$rkf_irrelevant_fort_cache_fht, $LEXICAL, UNPROVIDED));
            deflexical("*MAX-KBS-BACKWARD-TO-LOOK-FOR-IRRELEVANCY-CACHE*", FIVE_INTEGER);
            deflexical("*PRELOAD-RKF-IRRELEVANT-FORT-CACHE?*", NIL);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*RKF-IRRELEVANT-CACHE-BASE-FILE-NAME*", red_infrastructure_macros.red_def_helper($list_alt1.isSymbol() ? ((SubLObject) (symbol_value($list_alt1))) : $list_alt1, $rkf_irrelevant_cache_base_file_name$, $str_alt3$rkf_irrelevant_fort_cache_fht.isSymbol() ? ((SubLObject) (symbol_value($str_alt3$rkf_irrelevant_fort_cache_fht))) : $str_alt3$rkf_irrelevant_fort_cache_fht, $LEXICAL, UNPROVIDED));
        }
        return NIL;
    }

    public static SubLObject init_rkf_irrelevant_fort_cache_file_Previous() {
        deflexical("*RKF-IRRELEVANT-FORT-CACHE*", NIL);
        defparameter("*RKF-IRRELEVANT-FHT-NAME*", NIL);
        defparameter("*USE-RKF-IRRELEVANT-FORT-CACHE?*", T);
        deflexical("*RKF-IRRELEVANT-FHT-LOCK*", make_lock($str0$rkf_irrelevant_fht_lock));
        deflexical("*RKF-IRRELEVANT-CACHE-BASE-FILE-NAME*", red_infrastructure_macros.red_def_helper($list1.isSymbol() ? symbol_value($list1) : $list1, $rkf_irrelevant_cache_base_file_name$, $str3$rkf_irrelevant_fort_cache_fht.isSymbol() ? symbol_value($str3$rkf_irrelevant_fort_cache_fht) : $str3$rkf_irrelevant_fort_cache_fht, $LEXICAL, UNPROVIDED));
        deflexical("*MAX-KBS-BACKWARD-TO-LOOK-FOR-IRRELEVANCY-CACHE*", FIVE_INTEGER);
        deflexical("*PRELOAD-RKF-IRRELEVANT-FORT-CACHE?*", NIL);
        return NIL;
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLString $str_alt0$rkf_irrelevant_fht_lock = makeString("rkf-irrelevant-fht-lock");

    static private final SubLList $list_alt1 = list(makeString("application.file-backed-cache.rkf-irrelevant-fort-cache-file-name"));

    static private final SubLString $str_alt3$rkf_irrelevant_fort_cache_fht = makeString("rkf-irrelevant-fort-cache.fht");

    static private final SubLList $list_alt5 = list(list(makeSymbol("FILENAME")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLString $str_alt8$_4__0d = makeString("~4,'0d");

    public static SubLObject setup_rkf_irrelevant_fort_cache_file() {
        register_kb_function(RKF_IRRELEVANT_ASSERTION_CACHE_CLEAR);
        return NIL;
    }

    static private final SubLString $str_alt9$_ = makeString("/");

    @Override
    public void declareFunctions() {
        declare_rkf_irrelevant_fort_cache_file();
    }

    static private final SubLString $str_alt12$tmp_ = makeString("tmp/");

    @Override
    public void initializeVariables() {
        init_rkf_irrelevant_fort_cache_file();
    }

    static private final SubLString $str_alt13$Generating_rkf_irrelevant_cache_t = makeString("Generating rkf irrelevant cache to file: ~A");

    @Override
    public void runTopLevelForms() {
        setup_rkf_irrelevant_fort_cache_file();
    }

    static private final SubLString $str_alt14$Generating_rkf_irrelevant_values_ = makeString("Generating rkf irrelevant values for all forts ....");

    static {
    }

    static private final SubLString $str_alt18$Looking_up_rkf_irrelevance_status = makeString("Looking up rkf-irrelevance status for all forts ....");

    static private final SubLString $str_alt25$Found_an_invalid_value_in_rkf_irr = makeString("Found an invalid value in rkf-irrelevant-fort-cache value: ~A for key ~A.");

    static private final SubLList $list_alt26 = list(list(makeSymbol("FORT"), makeSymbol("&KEY"), makeSymbol("MESSAGE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt27 = list(makeKeyword("MESSAGE"));

    static private final SubLList $list_alt32 = list(makeSymbol("FBC-FILE-HASH-TABLE-CACHE"), makeSymbol("*RKF-IRRELEVANT-FORT-CACHE*"));

    static private final SubLString $str_alt36$Trying_to_insert_invalid_value_in = makeString("Trying to insert invalid value into rkf-irrelevant-fort-cache value: ~A for key: ~A.");

    static private final SubLString $str_alt38$_A_can_not_be_converted_to_an_irr = makeString("~A can not be converted to an irrelevance keyword");
}

/**
 * Total time: 252 ms
 */
