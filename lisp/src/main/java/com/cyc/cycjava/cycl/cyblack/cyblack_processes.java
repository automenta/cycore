/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.cyblack;


import static subl.ConsesLow.cons;
import static subl.ConsesLow.list;
import static subl.ConsesLow.rplacd;
import static subl.Locks.make_lock;
import static subl.Locks.release_lock;
import static subl.Locks.seize_lock;
import static subl.Numbers.add;
import static subl.Sequences.cconcatenate;
import static subl.Sequences.delete;
import static subl.Symbols.symbol_function;
import static subl.Threads.all_processes;
import static subl.Threads.kill_process;
import static subl.Threads.make_process;
import static subl.Threads.process_name;
import static subl.type.core.SubLObjectFactory.makeString;
import static subl.type.core.SubLObjectFactory.makeSymbol;
import static subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static subl.jrtl.translatedCode.sublisp.conses_high.assoc;
import static subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static subl.util.SubLFiles.declareFunction;
import static subl.util.SubLFiles.deflexical;
import static subl.util.SubLFiles.defvar;

import com.cyc.cycjava.cycl.V02;
import com.cyc.cycjava.cycl.string_utilities;
import com.cyc.cycjava.cycl.subl_promotions;
import subl.SubLThread;
import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.core.SubLProcess;
import subl.type.core.SubLString;
import subl.type.symbol.SubLSymbol;
import subl.util.SubLFile;
import subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 *  module:      CYBLACK-PROCESSES
 *  source file: /cyc/top/cycl/cyblack/cyblack-processes.lisp
 *  created:     2019/07/03 17:38:42
 */
public final class cyblack_processes extends SubLTranslatedFile implements V02 {
    // // Constructor
    private cyblack_processes() {
    }

    public static final SubLFile me = new cyblack_processes();

    public static final String myName = "com.cyc.cycjava.cycl.cyblack.cyblack_processes";

    // // Definitions
    // deflexical
    private static final SubLSymbol $cyblack_application_process_map_lock$ = makeSymbol("*CYBLACK-APPLICATION-PROCESS-MAP-LOCK*");

    // defvar
    private static final SubLSymbol $cyblack_application_process_map$ = makeSymbol("*CYBLACK-APPLICATION-PROCESS-MAP*");

    // defvar
    private static final SubLSymbol $cyblack_application_id_counter$ = makeSymbol("*CYBLACK-APPLICATION-ID-COUNTER*");

    public static final SubLObject cyblack_allocate_application_id() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject new_id = NIL;
                SubLObject lock = $cyblack_application_process_map_lock$.getGlobalValue();
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    $cyblack_application_id_counter$.setDynamicValue(add($cyblack_application_id_counter$.getDynamicValue(thread), ONE_INTEGER), thread);
                    new_id = $cyblack_application_id_counter$.getDynamicValue(thread);
                    $cyblack_application_process_map$.setDynamicValue(cons(list($cyblack_application_id_counter$.getDynamicValue(thread)), $cyblack_application_process_map$.getDynamicValue(thread)), thread);
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
                return new_id;
            }
        }
    }

    public static final SubLObject cyblack_deallocate_application_id(SubLObject application_id) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject lock = $cyblack_application_process_map_lock$.getGlobalValue();
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    $cyblack_application_process_map$.setDynamicValue(delete(application_id, $cyblack_application_process_map$.getDynamicValue(thread), symbol_function(EQUAL), symbol_function(CAR), UNPROVIDED, UNPROVIDED, UNPROVIDED), thread);
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
            return application_id;
        }
    }

    public static final SubLObject cyblack_application_is_running_p(SubLObject application_id) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject cdolist_list_var = $cyblack_application_process_map$.getDynamicValue(thread);
                SubLObject cons = NIL;
                for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                    {
                        SubLObject datum = cons;
                        SubLObject current = datum;
                        SubLObject key = NIL;
                        SubLObject value = NIL;
                        destructuring_bind_must_consp(current, datum, $list_alt2);
                        key = current.first();
                        current = current.rest();
                        value = current;
                        if (key.equal(application_id)) {
                            return T;
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static final SubLObject cyblack_kill_application_processes(SubLObject application_id) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject lock = $cyblack_application_process_map_lock$.getGlobalValue();
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    {
                        SubLObject association = assoc(application_id, $cyblack_application_process_map$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED);
                        if (NIL != association) {
                            {
                                SubLObject process_names = association.rest();
                                SubLObject cdolist_list_var = copy_list(all_processes());
                                SubLObject process = NIL;
                                for (process = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , process = cdolist_list_var.first()) {
                                    if (NIL != subl_promotions.memberP(process_name(process), process_names, symbol_function(STRING_EQUAL), UNPROVIDED)) {
                                        kill_process(process);
                                    }
                                }
                            }
                        }
                    }
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
            return application_id;
        }
    }

    public static final SubLObject cyblack_kill_application_processes_and_deallocate_application_id(SubLObject application_id) {
        cyblack_kill_application_processes(application_id);
        cyblack_deallocate_application_id(application_id);
        return application_id;
    }

    public static final SubLObject cyblack_register_application_instance_process(SubLObject application_id, SubLObject process_name) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject lock = $cyblack_application_process_map_lock$.getGlobalValue();
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    {
                        SubLObject association = assoc(application_id, $cyblack_application_process_map$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED);
                        if (NIL != association) {
                            rplacd(association, cons(process_name, association.rest()));
                        }
                    }
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
            return process_name;
        }
    }

    public static final SubLObject cyblack_make_process(SubLObject application_id, SubLObject process_name, SubLObject function) {
        {
            SubLObject modified_process_name = cconcatenate(process_name, string_utilities.to_string(application_id));
            cyblack_register_application_instance_process(application_id, modified_process_name);
            return make_process(modified_process_name, function);
        }
    }

    public static final SubLObject declare_cyblack_processes_file() {
        declareFunction("cyblack_allocate_application_id", "CYBLACK-ALLOCATE-APPLICATION-ID", 0, 0, false);
        declareFunction("cyblack_deallocate_application_id", "CYBLACK-DEALLOCATE-APPLICATION-ID", 1, 0, false);
        declareFunction("cyblack_application_is_running_p", "CYBLACK-APPLICATION-IS-RUNNING-P", 1, 0, false);
        declareFunction("cyblack_kill_application_processes", "CYBLACK-KILL-APPLICATION-PROCESSES", 1, 0, false);
        declareFunction("cyblack_kill_application_processes_and_deallocate_application_id", "CYBLACK-KILL-APPLICATION-PROCESSES-AND-DEALLOCATE-APPLICATION-ID", 1, 0, false);
        declareFunction("cyblack_register_application_instance_process", "CYBLACK-REGISTER-APPLICATION-INSTANCE-PROCESS", 2, 0, false);
        declareFunction("cyblack_make_process", "CYBLACK-MAKE-PROCESS", 3, 0, false);
        return NIL;
    }

    public static final SubLObject init_cyblack_processes_file() {
        deflexical("*CYBLACK-APPLICATION-PROCESS-MAP-LOCK*", make_lock($str_alt0$CyBlack_Application_Process_Map_L));
        defvar("*CYBLACK-APPLICATION-PROCESS-MAP*", NIL);
        defvar("*CYBLACK-APPLICATION-ID-COUNTER*", ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject setup_cyblack_processes_file() {
                return NIL;
    }

    // // Internal Constants
    static private final SubLString $str_alt0$CyBlack_Application_Process_Map_L = makeString("CyBlack Application Process Map Lock");



    static private final SubLList $list_alt2 = cons(makeSymbol("KEY"), makeSymbol("VALUE"));



    // // Initializers
    public void declareFunctions() {
        declare_cyblack_processes_file();
    }

    public void initializeVariables() {
        init_cyblack_processes_file();
    }

    public void runTopLevelForms() {
        setup_cyblack_processes_file();
    }
}

