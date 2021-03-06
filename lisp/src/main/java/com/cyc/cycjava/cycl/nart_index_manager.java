/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static subl.ConsesLow.append;
import static subl.ConsesLow.list;
import static subl.ConsesLow.listS;
import static subl.Numbers.numE;
import static subl.Symbols.boundp;
import static subl.type.core.SubLObjectFactory.makeInteger;
import static subl.type.core.SubLObjectFactory.makeString;
import static subl.type.core.SubLObjectFactory.makeSymbol;
import static subl.util.SubLFiles.declareFunction;
import static subl.util.SubLFiles.declareMacro;
import static subl.util.SubLFiles.deflexical;

import subl.SubLThread;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.core.SubLProcess;
import subl.type.core.SubLString;
import subl.type.number.SubLInteger;
import subl.type.symbol.SubLSymbol;
import subl.util.SubLFile;
import subl.util.SubLFiles;
import subl.util.SubLFiles.LispMethod;
import subl.util.SubLTrampolineFile;
import subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      NART-INDEX-MANAGER
 * source file: /cyc/top/cycl/nart-index-manager.lisp
 * created:     2019/07/03 17:37:20
 */
public final class nart_index_manager extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new nart_index_manager();

 public static final String myName = "com.cyc.cycjava.cycl.nart_index_manager";


    // deflexical
    @LispMethod(comment = "Based on arete experiments, only 20% of all narts are touched during normal inference,\r\nso we\'ll make a conservative guess that every one of those touched the nart\'s index.\ndeflexical\nBased on arete experiments, only 20% of all narts are touched during normal inference,\nso we\'ll make a conservative guess that every one of those touched the nart\'s index.")
    private static final SubLSymbol $nart_index_lru_size_percentage$ = makeSymbol("*NART-INDEX-LRU-SIZE-PERCENTAGE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $nart_index_lru_size_max$ = makeSymbol("*NART-INDEX-LRU-SIZE-MAX*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    public static final SubLSymbol $nart_index_manager$ = makeSymbol("*NART-INDEX-MANAGER*");

    private static final SubLInteger $int$30000 = makeInteger(30000);

    private static final SubLString $str3$nart_index = makeString("nart-index");

    private static final SubLSymbol LOAD_NART_INDEX_FROM_CACHE = makeSymbol("LOAD-NART-INDEX-FROM-CACHE");

    private static final SubLSymbol $sym6$_EXIT = makeSymbol("%EXIT");

    private static final SubLString $str7$nat_indices = makeString("nat-indices");

    private static final SubLString $str8$nat_indices_index = makeString("nat-indices-index");

    private static final SubLString $str9$nat_complex_indices = makeString("nat-complex-indices");

    private static final SubLString $str10$nat_complex_indices_index = makeString("nat-complex-indices-index");

    private static final SubLString $$$nim = makeString("nim");

    private static final SubLString $$$cnim = makeString("cnim");

    private static final SubLSymbol WITH_KBOM_FULLY_LOADED = makeSymbol("WITH-KBOM-FULLY-LOADED");

    private static final SubLList $list15 = list(makeSymbol("*NART-INDEX-MANAGER*"));

    public static final SubLObject setup_nart_index_table_alt(SubLObject size, SubLObject exactP) {
        $nart_index_manager$.setGlobalValue(kb_object_manager.new_kb_object_manager($str_alt2$nart_index, size, $nart_index_lru_size_percentage$.getGlobalValue(), LOAD_NART_INDEX_FROM_CACHE, exactP));
        return T;
    }

    public static SubLObject setup_nart_index_table(final SubLObject size, final SubLObject exactP) {
        $nart_index_manager$.setGlobalValue(kb_object_manager.new_kb_object_manager($str3$nart_index, size, $nart_index_lru_size_percentage$.getGlobalValue(), $nart_index_lru_size_max$.getGlobalValue(), LOAD_NART_INDEX_FROM_CACHE, exactP));
        return T;
    }

    public static SubLObject resize_nart_index_kbom_lru(SubLObject percentage, SubLObject max) {
        if (percentage == UNPROVIDED) {
            percentage = NIL;
        }
        if (max == UNPROVIDED) {
            max = NIL;
        }
        if (NIL == percentage) {
            percentage = $nart_index_lru_size_percentage$.getGlobalValue();
        }
        if (NIL == max) {
            max = $nart_index_lru_size_max$.getGlobalValue();
        }
        return kb_object_manager.resize_kbom_lru_cache_from_percentage_and_max($nart_index_manager$.getGlobalValue(), percentage, max);
    }

    public static final SubLObject optimize_nart_index_table_alt(SubLObject new_nart_id_threshold) {
        return kb_object_manager.optimize_kb_object_content_table($nart_index_manager$.getGlobalValue(), new_nart_id_threshold);
    }

    public static SubLObject optimize_nart_index_table(final SubLObject new_nart_id_threshold) {
        return kb_object_manager.optimize_kb_object_content_table($nart_index_manager$.getGlobalValue(), new_nart_id_threshold);
    }

    public static final SubLObject clear_nart_index_table_alt() {
        return kb_object_manager.clear_kb_object_content_table($nart_index_manager$.getGlobalValue());
    }

    public static SubLObject clear_nart_index_table() {
        return kb_object_manager.clear_kb_object_content_table($nart_index_manager$.getGlobalValue());
    }

    /**
     * Enable the tracking of nart-index usage counts.
     */
    @LispMethod(comment = "Enable the tracking of nart-index usage counts.")
    public static final SubLObject maintain_nart_index_usage_counts_alt() {
        return kb_object_manager.maintain_kb_object_usage_counts($nart_index_manager$.getGlobalValue());
    }

    /**
     * Enable the tracking of nart-index usage counts.
     */
    @LispMethod(comment = "Enable the tracking of nart-index usage counts.")
    public static SubLObject maintain_nart_index_usage_counts() {
        return kb_object_manager.maintain_kb_object_usage_counts($nart_index_manager$.getGlobalValue());
    }

    /**
     * Disable the tracking of nart-index usage counts.
     */
    @LispMethod(comment = "Disable the tracking of nart-index usage counts.")
    public static final SubLObject dont_maintain_nart_index_usage_counts_alt() {
        return kb_object_manager.dont_maintain_kb_object_usage_counts($nart_index_manager$.getGlobalValue());
    }

    /**
     * Disable the tracking of nart-index usage counts.
     */
    @LispMethod(comment = "Disable the tracking of nart-index usage counts.")
    public static SubLObject dont_maintain_nart_index_usage_counts() {
        return kb_object_manager.dont_maintain_kb_object_usage_counts($nart_index_manager$.getGlobalValue());
    }

    /**
     * Test predicate whether nart-index usage counts are already enabled or not.
     */
    @LispMethod(comment = "Test predicate whether nart-index usage counts are already enabled or not.")
    public static final SubLObject nart_index_usage_counts_enabled_p_alt() {
        return kb_object_manager.kb_object_usage_counts_enabledP($nart_index_manager$.getGlobalValue());
    }

    /**
     * Test predicate whether nart-index usage counts are already enabled or not.
     */
    @LispMethod(comment = "Test predicate whether nart-index usage counts are already enabled or not.")
    public static SubLObject nart_index_usage_counts_enabled_p() {
        return kb_object_manager.kb_object_usage_counts_enabledP($nart_index_manager$.getGlobalValue());
    }

    /**
     * Return the number of nart-indices whose content is cached in memory.
     */
    @LispMethod(comment = "Return the number of nart-indices whose content is cached in memory.")
    public static final SubLObject cached_nart_index_count_alt() {
        return kb_object_manager.cached_kb_object_count($nart_index_manager$.getGlobalValue());
    }

    /**
     * Return the number of nart-indices whose content is cached in memory.
     */
    @LispMethod(comment = "Return the number of nart-indices whose content is cached in memory.")
    public static SubLObject cached_nart_index_count() {
        return kb_object_manager.cached_kb_object_count($nart_index_manager$.getGlobalValue());
    }

    public static final SubLObject nart_indices_completely_cachedP_alt() {
        return numE(nart_handles.nart_count(), com.cyc.cycjava.cycl.nart_index_manager.cached_nart_index_count());
    }

    public static SubLObject nart_indices_completely_cachedP() {
        return numE(nart_handles.nart_count(), cached_nart_index_count());
    }

    public static final SubLObject lookup_nart_index_alt(SubLObject id) {
        return kb_object_manager.lookup_kb_object_content($nart_index_manager$.getGlobalValue(), id);
    }

    public static SubLObject lookup_nart_index(final SubLObject id) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject _prev_bind_0 = kb_storage_logging.$kb_storage_client$.currentBinding(thread);
        try {
            kb_storage_logging.$kb_storage_client$.bind(kb_storage_logging.add_kb_storage_logging_add_tag($NIM), thread);
            return kb_object_manager.lookup_kb_object_content($nart_index_manager$.getGlobalValue(), id);
        } finally {
            kb_storage_logging.$kb_storage_client$.rebind(_prev_bind_0, thread);
        }
    }

    public static SubLObject nart_index_cachedP(final SubLObject id) {
        return kb_object_manager.kb_object_id_cachedP($nart_index_manager$.getGlobalValue(), id);
    }

    /**
     * Note that ID will be used as the id for NART-INDEX.
     */
    @LispMethod(comment = "Note that ID will be used as the id for NART-INDEX.")
    public static final SubLObject register_nart_index_alt(SubLObject id, SubLObject nart_index) {
        return kb_object_manager.register_kb_object_content($nart_index_manager$.getGlobalValue(), id, nart_index);
    }

    /**
     * Note that ID will be used as the id for NART-INDEX.
     */
    @LispMethod(comment = "Note that ID will be used as the id for NART-INDEX.")
    public static SubLObject register_nart_index(final SubLObject id, final SubLObject nart_index) {
        return kb_object_manager.register_kb_object_content($nart_index_manager$.getGlobalValue(), id, nart_index);
    }

    /**
     * Note that ID is not in use as an NART-INDEX id
     */
    @LispMethod(comment = "Note that ID is not in use as an NART-INDEX id")
    public static final SubLObject deregister_nart_index_alt(SubLObject id) {
        return kb_object_manager.deregister_kb_object_content($nart_index_manager$.getGlobalValue(), id);
    }

    /**
     * Note that ID is not in use as an NART-INDEX id
     */
    @LispMethod(comment = "Note that ID is not in use as an NART-INDEX id")
    public static SubLObject deregister_nart_index(final SubLObject id) {
        return kb_object_manager.deregister_kb_object_content($nart_index_manager$.getGlobalValue(), id);
    }

    public static final SubLObject mark_nart_index_as_muted_alt(SubLObject id) {
        return kb_object_manager.mark_kb_object_content_as_muted($nart_index_manager$.getGlobalValue(), id);
    }

    public static SubLObject mark_nart_index_as_muted(final SubLObject id) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject _prev_bind_0 = kb_storage_logging.$kb_storage_client$.currentBinding(thread);
        try {
            kb_storage_logging.$kb_storage_client$.bind(kb_storage_logging.add_kb_storage_logging_add_tag($NIM), thread);
            return kb_object_manager.mark_kb_object_content_as_muted($nart_index_manager$.getGlobalValue(), id);
        } finally {
            kb_storage_logging.$kb_storage_client$.rebind(_prev_bind_0, thread);
        }
    }

    /**
     * Turn metering of nart-index content swap time on.
     */
    @LispMethod(comment = "Turn metering of nart-index content swap time on.")
    public static final SubLObject meter_nart_index_swap_time_alt() {
        return kb_object_manager.meter_kb_object_content_swap_time($nart_index_manager$.getGlobalValue());
    }

    /**
     * Turn metering of nart-index content swap time on.
     */
    @LispMethod(comment = "Turn metering of nart-index content swap time on.")
    public static SubLObject meter_nart_index_swap_time() {
        return kb_object_manager.meter_kb_object_content_swap_time($nart_index_manager$.getGlobalValue());
    }

    /**
     * Turn metering of nart-index content swap time off.
     */
    @LispMethod(comment = "Turn metering of nart-index content swap time off.")
    public static final SubLObject dont_meter_nart_index_swap_time_alt() {
        return kb_object_manager.dont_meter_kb_object_content_swap_time($nart_index_manager$.getGlobalValue());
    }

    /**
     * Turn metering of nart-index content swap time off.
     */
    @LispMethod(comment = "Turn metering of nart-index content swap time off.")
    public static SubLObject dont_meter_nart_index_swap_time() {
        return kb_object_manager.dont_meter_kb_object_content_swap_time($nart_index_manager$.getGlobalValue());
    }

    /**
     * Reset nart-index content swap time to 0.
     */
    @LispMethod(comment = "Reset nart-index content swap time to 0.")
    public static final SubLObject clear_nart_index_swap_time_alt() {
        return kb_object_manager.clear_kb_object_content_swap_time($nart_index_manager$.getGlobalValue());
    }

    /**
     * Reset nart-index content swap time to 0.
     */
    @LispMethod(comment = "Reset nart-index content swap time to 0.")
    public static SubLObject clear_nart_index_swap_time() {
        return kb_object_manager.clear_kb_object_content_swap_time($nart_index_manager$.getGlobalValue());
    }

    /**
     *
     *
     * @return NON-NEGATIVE-NUMBER-P; How many seconds have been spent inside
    SWAP-IN-NART-INDEX with metering on since the last time it was cleared?
     */
    @LispMethod(comment = "@return NON-NEGATIVE-NUMBER-P; How many seconds have been spent inside\r\nSWAP-IN-NART-INDEX with metering on since the last time it was cleared?")
    public static final SubLObject current_nart_index_swap_time_alt() {
        return kb_object_manager.kb_object_manager_swap_time($nart_index_manager$.getGlobalValue());
    }

    /**
     *
     *
     * @return NON-NEGATIVE-NUMBER-P; How many seconds have been spent inside
    SWAP-IN-NART-INDEX with metering on since the last time it was cleared?
     */
    @LispMethod(comment = "@return NON-NEGATIVE-NUMBER-P; How many seconds have been spent inside\r\nSWAP-IN-NART-INDEX with metering on since the last time it was cleared?")
    public static SubLObject current_nart_index_swap_time() {
        return kb_object_manager.kb_object_manager_swap_time($nart_index_manager$.getGlobalValue());
    }

    public static final SubLObject swap_out_all_pristine_nart_indices_alt() {
        return kb_object_manager.swap_out_all_pristine_kb_objects_int($nart_index_manager$.getGlobalValue());
    }

    public static SubLObject swap_out_all_pristine_nart_indices() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject _prev_bind_0 = kb_storage_logging.$kb_storage_client$.currentBinding(thread);
        try {
            kb_storage_logging.$kb_storage_client$.bind(kb_storage_logging.add_kb_storage_logging_add_tag($NIM), thread);
            return kb_object_manager.safely_swap_out_all_pristine_kb_objects($nart_index_manager$.getGlobalValue());
        } finally {
            kb_storage_logging.$kb_storage_client$.rebind(_prev_bind_0, thread);
        }
    }

    public static final SubLObject initialize_nart_index_hl_store_cache_alt() {
        return kb_object_manager.initialize_kb_object_hl_store_cache($nart_index_manager$.getGlobalValue(), $str_alt4$nat_indices, $str_alt5$nat_indices_index);
    }

    public static SubLObject initialize_nart_index_hl_store_cache() {
        final SubLObject result = kb_object_manager.initialize_kb_object_hl_store_cache($nart_index_manager$.getGlobalValue(), $str7$nat_indices, $str8$nat_indices_index, $str9$nat_complex_indices, $str10$nat_complex_indices_index);
        kb_storage_logging.register_kb_storage_logging_tag(kb_object_manager.kb_object_manager_file_vector($nart_index_manager$.getGlobalValue()), $$$nim);
        final SubLObject sub_fvector = kb_object_manager.kb_object_manager_sub_file_vector($nart_index_manager$.getGlobalValue());
        if (NIL != file_vector.file_vector_p(sub_fvector)) {
            kb_storage_logging.register_kb_storage_logging_tag(sub_fvector, $$$cnim);
            file_vector_utilities.register_indexical_file_vector_reference($NART_INDEX, sub_fvector);
        }
        return result;
    }

    static private final SubLString $str_alt2$nart_index = makeString("nart-index");

    static private final SubLString $str_alt4$nat_indices = makeString("nat-indices");

    static private final SubLString $str_alt5$nat_indices_index = makeString("nat-indices-index");

    public static SubLObject wide_nart_index_hl_store_cacheP() {
        return kb_object_manager.wide_kb_object_managerP($nart_index_manager$.getGlobalValue());
    }

    public static SubLObject wide_nart_complex_index_hl_store_cacheP() {
        return kb_object_manager.wide_kb_object_sub_managerP($nart_index_manager$.getGlobalValue());
    }

    public static SubLObject new_muted_nart_index_iterator() {
        return kb_object_manager.new_muted_kb_object_iterator($nart_index_manager$.getGlobalValue());
    }

    public static SubLObject nart_index_change_stream_buffer_sizes(final SubLObject data_stream_buffer_size, final SubLObject index_stream_buffer_size) {
        return kb_object_manager.kb_object_manager_change_stream_buffer_sizes($nart_index_manager$.getGlobalValue(), data_stream_buffer_size, index_stream_buffer_size);
    }

    public static SubLObject nart_index_enable_memory_mapping() {
        return kb_object_manager.kb_object_manager_enable_memory_mapping($nart_index_manager$.getGlobalValue());
    }

    public static SubLObject nart_index_memory_mappedP() {
        return kb_object_manager.kb_object_manager_memory_mappedP($nart_index_manager$.getGlobalValue());
    }

    public static SubLObject with_nart_index_fully_loaded(final SubLObject macroform, final SubLObject environment) {
        final SubLObject datum = macroform.rest();
        final SubLObject body;
        final SubLObject current = body = datum;
        return listS(WITH_KBOM_FULLY_LOADED, $list15, append(body, NIL));
    }

    public static SubLObject declare_nart_index_manager_file() {
        declareFunction("setup_nart_index_table", "SETUP-NART-INDEX-TABLE", 2, 0, false);
        declareFunction("resize_nart_index_kbom_lru", "RESIZE-NART-INDEX-KBOM-LRU", 0, 2, false);
        declareFunction("optimize_nart_index_table", "OPTIMIZE-NART-INDEX-TABLE", 1, 0, false);
        declareFunction("clear_nart_index_table", "CLEAR-NART-INDEX-TABLE", 0, 0, false);
        declareFunction("maintain_nart_index_usage_counts", "MAINTAIN-NART-INDEX-USAGE-COUNTS", 0, 0, false);
        declareFunction("dont_maintain_nart_index_usage_counts", "DONT-MAINTAIN-NART-INDEX-USAGE-COUNTS", 0, 0, false);
        declareFunction("nart_index_usage_counts_enabled_p", "NART-INDEX-USAGE-COUNTS-ENABLED-P", 0, 0, false);
        declareFunction("cached_nart_index_count", "CACHED-NART-INDEX-COUNT", 0, 0, false);
        declareFunction("nart_indices_completely_cachedP", "NART-INDICES-COMPLETELY-CACHED?", 0, 0, false);
        declareFunction("lookup_nart_index", "LOOKUP-NART-INDEX", 1, 0, false);
        declareFunction("nart_index_cachedP", "NART-INDEX-CACHED?", 1, 0, false);
        declareFunction("register_nart_index", "REGISTER-NART-INDEX", 2, 0, false);
        declareFunction("deregister_nart_index", "DEREGISTER-NART-INDEX", 1, 0, false);
        declareFunction("mark_nart_index_as_muted", "MARK-NART-INDEX-AS-MUTED", 1, 0, false);
        declareFunction("meter_nart_index_swap_time", "METER-NART-INDEX-SWAP-TIME", 0, 0, false);
        declareFunction("dont_meter_nart_index_swap_time", "DONT-METER-NART-INDEX-SWAP-TIME", 0, 0, false);
        declareFunction("clear_nart_index_swap_time", "CLEAR-NART-INDEX-SWAP-TIME", 0, 0, false);
        declareFunction("current_nart_index_swap_time", "CURRENT-NART-INDEX-SWAP-TIME", 0, 0, false);
        declareFunction("swap_out_all_pristine_nart_indices", "SWAP-OUT-ALL-PRISTINE-NART-INDICES", 0, 0, false);
        declareFunction("initialize_nart_index_hl_store_cache", "INITIALIZE-NART-INDEX-HL-STORE-CACHE", 0, 0, false);
        declareFunction("wide_nart_index_hl_store_cacheP", "WIDE-NART-INDEX-HL-STORE-CACHE?", 0, 0, false);
        declareFunction("wide_nart_complex_index_hl_store_cacheP", "WIDE-NART-COMPLEX-INDEX-HL-STORE-CACHE?", 0, 0, false);
        declareFunction("new_muted_nart_index_iterator", "NEW-MUTED-NART-INDEX-ITERATOR", 0, 0, false);
        declareFunction("nart_index_change_stream_buffer_sizes", "NART-INDEX-CHANGE-STREAM-BUFFER-SIZES", 2, 0, false);
        declareFunction("nart_index_enable_memory_mapping", "NART-INDEX-ENABLE-MEMORY-MAPPING", 0, 0, false);
        declareFunction("nart_index_memory_mappedP", "NART-INDEX-MEMORY-MAPPED?", 0, 0, false);
        declareMacro("with_nart_index_fully_loaded", "WITH-NART-INDEX-FULLY-LOADED");
        return NIL;
    }

    public static final SubLObject init_nart_index_manager_file_alt() {
        deflexical("*NART-INDEX-MANAGER*", NIL != boundp($nart_index_manager$) ? ((SubLObject) ($nart_index_manager$.getGlobalValue())) : $UNINITIALIZED);
        deflexical("*NART-INDEX-LRU-SIZE-PERCENTAGE*", TWENTY_INTEGER);
        return NIL;
    }

    public static SubLObject init_nart_index_manager_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*NART-INDEX-MANAGER*", SubLTrampolineFile.maybeDefault($nart_index_manager$, $nart_index_manager$, $UNINITIALIZED));
            deflexical("*NART-INDEX-LRU-SIZE-PERCENTAGE*", TWENTY_INTEGER);
            deflexical("*NART-INDEX-LRU-SIZE-MAX*", $int$30000);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*NART-INDEX-MANAGER*", NIL != boundp($nart_index_manager$) ? ((SubLObject) ($nart_index_manager$.getGlobalValue())) : $UNINITIALIZED);
        }
        return NIL;
    }

    public static SubLObject init_nart_index_manager_file_Previous() {
        deflexical("*NART-INDEX-MANAGER*", SubLTrampolineFile.maybeDefault($nart_index_manager$, $nart_index_manager$, $UNINITIALIZED));
        deflexical("*NART-INDEX-LRU-SIZE-PERCENTAGE*", TWENTY_INTEGER);
        deflexical("*NART-INDEX-LRU-SIZE-MAX*", $int$30000);
        return NIL;
    }

    public static SubLObject setup_nart_index_manager_file() {
        declare_defglobal($nart_index_manager$);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_nart_index_manager_file();
    }

    @Override
    public void initializeVariables() {
        init_nart_index_manager_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_nart_index_manager_file();
    }

    static {
    }
}

/**
 * Total time: 48 ms
 */
