/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.el_utilities.make_formula;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.assoc;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_tree;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.fifth;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.fourth;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.putf;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.sixth;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.third;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.inference.ask_utilities;
import com.cyc.cycjava.cycl.inference.harness.inference_parameters;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
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
 * module:      SCRIPT-INSTANCE-EDITOR-API
 * source file: /cyc/top/cycl/script-instance-editor-api.lisp
 * created:     2019/07/03 17:38:03
 */
public final class script_instance_editor_api extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new script_instance_editor_api();

 public static final String myName = "com.cyc.cycjava.cycl.script_instance_editor_api";


    // deflexical
    /**
     * The default sub-situation relations that we are paying attention to.
     */
    @LispMethod(comment = "The default sub-situation relations that we are paying attention to.\ndeflexical")
    public static final SubLSymbol $default_sub_situation_instance_level_relations$ = makeSymbol("*DEFAULT-SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*");

    // deflexical
    // A set of commonly useful role player instance level relations.
    /**
     * A set of commonly useful role player instance level relations.
     */
    @LispMethod(comment = "A set of commonly useful role player instance level relations.\ndeflexical")
    public static final SubLSymbol $default_role_player_instance_level_relations$ = makeSymbol("*DEFAULT-ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*");

    // deflexical
    // A set of commonly useful role player type level relations.
    /**
     * A set of commonly useful role player type level relations.
     */
    @LispMethod(comment = "A set of commonly useful role player type level relations.\ndeflexical")
    public static final SubLSymbol $default_role_player_type_level_relations$ = makeSymbol("*DEFAULT-ROLE-PLAYER-TYPE-LEVEL-RELATIONS*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    public static final SubLSymbol $script_instance_editor_mt$ = makeSymbol("*SCRIPT-INSTANCE-EDITOR-MT*");



    static private final SubLList $list2 = list(reader_make_constant_shell("firstSubEvents"), reader_make_constant_shell("properSubEvents"), reader_make_constant_shell("lastSubEvents"));

    static private final SubLList $list3 = list(new SubLObject[]{ reader_make_constant_shell("perpetrator"), reader_make_constant_shell("hostages"), reader_make_constant_shell("dateOfEvent"), reader_make_constant_shell("losesControl"), reader_make_constant_shell("objectOfControlTransfer"), reader_make_constant_shell("agreeingAgents"), reader_make_constant_shell("ransomDemanded"), reader_make_constant_shell("senderOfInfo"), reader_make_constant_shell("agreementCreated"), reader_make_constant_shell("crimeVictim"), reader_make_constant_shell("eventOccursAt") });

    static private final SubLList $list4 = list(reader_make_constant_shell("equivRolePlayers"), reader_make_constant_shell("equivRolePlayersOfType"), reader_make_constant_shell("equivalentRolePlayers-SitTypeToSubSitType"), reader_make_constant_shell("equivalentRolePlayers-SitTypeToSubSitType-Unique"), reader_make_constant_shell("equivalentRolePlayers-SubSitTypeToSubSitType"), reader_make_constant_shell("equivalentRolePlayers-SubSitTypeToSubSitType-Unique"), reader_make_constant_shell("equivalentRolePlayersOfType-SubSitTypeToSubSitType"));

    private static final SubLSymbol WITH_ROLE_PLAYER_INSTANCE_LEVEL_RELATIONS = makeSymbol("WITH-ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS");

    static private final SubLList $list6 = list(list(makeSymbol("RELATIONS")), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final SubLSymbol $role_player_instance_level_relations$ = makeSymbol("*ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*");

    private static final SubLSymbol WITH_SUB_SITUATION_INSTANCE_LEVEL_RELATIONS = makeSymbol("WITH-SUB-SITUATION-INSTANCE-LEVEL-RELATIONS");

    public static final SubLSymbol $sub_situation_instance_level_relations$ = makeSymbol("*SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*");

    private static final SubLSymbol WITH_ROLE_PLAYER_TYPE_LEVEL_RELATIONS = makeSymbol("WITH-ROLE-PLAYER-TYPE-LEVEL-RELATIONS");

    public static final SubLSymbol $role_player_type_level_relations$ = makeSymbol("*ROLE-PLAYER-TYPE-LEVEL-RELATIONS*");

    private static final SubLSymbol GET_SCRIPT_GRAPH_INFO = makeSymbol("GET-SCRIPT-GRAPH-INFO");



    static private final SubLString $$$Argument_not_a_situation_type = makeString("Argument not a situation type");

    private static final SubLSymbol $SUB_EVT_TYPE = makeKeyword("SUB-EVT-TYPE");



    static private final SubLList $list18 = list(makeKeyword("SUB-EVT-TYPE"));

    private static final SubLSymbol $SUB_EVENT_TYPES = makeKeyword("SUB-EVENT-TYPES");

    private static final SubLSymbol $ROLE_PLAYER_EQUIVALENCES = makeKeyword("ROLE-PLAYER-EQUIVALENCES");

    private static final SubLSymbol GET_ROLE_PLAYER_EQUIVALENCES_FOR_PRED_AND_SCRIPT = makeSymbol("GET-ROLE-PLAYER-EQUIVALENCES-FOR-PRED-AND-SCRIPT");

    private static final SubLObject $const22$equivalentRolePlayers_SubSitTypeT = reader_make_constant_shell("equivalentRolePlayers-SubSitTypeToSubSitType");

    static private final SubLList $list23 = list(makeSymbol("?SUB-SIT-TYPE-1"), makeSymbol("?SIT-ROLE-1"), makeSymbol("?SUB-SIT-TYPE-2"), makeSymbol("?SUB-SIT-ROLE-2"));

    private static final SubLObject $const24$equivalentRolePlayers_SitTypeToSu = reader_make_constant_shell("equivalentRolePlayers-SitTypeToSubSitType");

    static private final SubLList $list25 = list(makeSymbol("?SIT-ROLE"), makeSymbol("?SUB-SIT-TYPE"), makeSymbol("?SUB-SIT-ROLE"));

    private static final SubLSymbol GET_GRAPH_FOR_SCRIPT_INSTANCE = makeSymbol("GET-GRAPH-FOR-SCRIPT-INSTANCE");

    static private final SubLString $str28$_A_is_not_an_individual_ = makeString("~A is not an individual.");

    private static final SubLSymbol GET_COMPLETE_GRAPH_FOR_SCRIPT_INSTANCE = makeSymbol("GET-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE");

    private static final SubLSymbol AUGMENT_COMPLETE_GRAPH_FOR_SCRIPT_INSTANCE = makeSymbol("AUGMENT-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE");

    private static final SubLSymbol GET_GRAPH_FOR_SCRIPT_ROLE_PLAYER_INSTANCE = makeSymbol("GET-GRAPH-FOR-SCRIPT-ROLE-PLAYER-INSTANCE");





    static private final SubLList $list44 = list(makeKeyword("EVT"));





    static private final SubLSymbol $sym47$_PRED = makeSymbol("?PRED");

    static private final SubLSymbol $sym48$_SUB_EVENT = makeSymbol("?SUB-EVENT");



    private static final SubLSymbol GET_ANCESTOR_NODES = makeSymbol("GET-ANCESTOR-NODES");

    private static final SubLSymbol $DISJUNCTION_FREE_EL_VARS_POLICY = makeKeyword("DISJUNCTION-FREE-EL-VARS-POLICY");

    static private final SubLList $list53 = list(makeSymbol("?SUB"), makeSymbol("?SUB1"), makeSymbol("?SUB2"), makeSymbol("?SUB-MT"), makeSymbol("?SUB2-MT"));

    static private final SubLSymbol $sym54$_SUB1 = makeSymbol("?SUB1");

    static private final SubLList $list55 = list(list(reader_make_constant_shell("join-left"), list(reader_make_constant_shell("ist-Asserted"), makeSymbol("?SUB-MT"), list(reader_make_constant_shell("properSubEvents"), makeSymbol("?SUB"), makeSymbol("?SUB1"))), list(reader_make_constant_shell("ist-Asserted"), makeSymbol("?SUB2-MT"), list(reader_make_constant_shell("properSubEvents"), makeSymbol("?SUB1"), makeSymbol("?SUB2")))));

    static private final SubLList $list56 = list(makeSymbol("ARG-1"), makeSymbol("ARG-2"), makeSymbol("ITEM"), makeSymbol("MT"), makeSymbol("MT2"));

    private static final SubLSymbol GET_ROLE_PLAYER_EQUIVALENCES = makeSymbol("GET-ROLE-PLAYER-EQUIVALENCES");

    /**
     * Override the default settings of *ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*
     * for purposes of script instance graph retrieval.
     */
    @LispMethod(comment = "Override the default settings of *ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*\r\nfor purposes of script instance graph retrieval.\nOverride the default settings of *ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*\nfor purposes of script instance graph retrieval.")
    public static final SubLObject with_role_player_instance_level_relations_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt6);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject relations = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt6);
                    relations = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return listS(CLET, list(list($role_player_instance_level_relations$, relations)), append(body, NIL));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt6);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Override the default settings of *ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*
     * for purposes of script instance graph retrieval.
     */
    @LispMethod(comment = "Override the default settings of *ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*\r\nfor purposes of script instance graph retrieval.\nOverride the default settings of *ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*\nfor purposes of script instance graph retrieval.")
    public static SubLObject with_role_player_instance_level_relations(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list6);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject relations = NIL;
        destructuring_bind_must_consp(current, datum, $list6);
        relations = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return listS(CLET, list(list($role_player_instance_level_relations$, relations)), append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list6);
        return NIL;
    }

    /**
     * Overrides the default setting of *SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*
     * parameter.
     */
    @LispMethod(comment = "Overrides the default setting of *SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*\r\nparameter.\nOverrides the default setting of *SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*\nparameter.")
    public static final SubLObject with_sub_situation_instance_level_relations_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt6);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject relations = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt6);
                    relations = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return listS(CLET, list(list($sub_situation_instance_level_relations$, relations)), append(body, NIL));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt6);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Overrides the default setting of *SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*
     * parameter.
     */
    @LispMethod(comment = "Overrides the default setting of *SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*\r\nparameter.\nOverrides the default setting of *SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*\nparameter.")
    public static SubLObject with_sub_situation_instance_level_relations(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list6);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject relations = NIL;
        destructuring_bind_must_consp(current, datum, $list6);
        relations = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return listS(CLET, list(list($sub_situation_instance_level_relations$, relations)), append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list6);
        return NIL;
    }

    /**
     * Override for the default settings of *ROLE-PLAYER-TYPE-LEVEL-RELATIONS*
     * for purposes of script instance graph retrieval.
     */
    @LispMethod(comment = "Override for the default settings of *ROLE-PLAYER-TYPE-LEVEL-RELATIONS*\r\nfor purposes of script instance graph retrieval.\nOverride for the default settings of *ROLE-PLAYER-TYPE-LEVEL-RELATIONS*\nfor purposes of script instance graph retrieval.")
    public static final SubLObject with_role_player_type_level_relations_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt6);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject relations = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt6);
                    relations = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return listS(CLET, list(list($role_player_type_level_relations$, relations)), append(body, NIL));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt6);
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Override for the default settings of *ROLE-PLAYER-TYPE-LEVEL-RELATIONS*
     * for purposes of script instance graph retrieval.
     */
    @LispMethod(comment = "Override for the default settings of *ROLE-PLAYER-TYPE-LEVEL-RELATIONS*\r\nfor purposes of script instance graph retrieval.\nOverride for the default settings of *ROLE-PLAYER-TYPE-LEVEL-RELATIONS*\nfor purposes of script instance graph retrieval.")
    public static SubLObject with_role_player_type_level_relations(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list6);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject relations = NIL;
        destructuring_bind_must_consp(current, datum, $list6);
        relations = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return listS(CLET, list(list($role_player_type_level_relations$, relations)), append(body, NIL));
        }
        cdestructuring_bind_error(datum, $list6);
        return NIL;
    }

    /**
     * Load the connectivity information for one script graph as visible from mt.
     * The information consists of sub-events and role player equivalences.
     *
     * @return ALIST of KEYWORD -> assertion pairs.
     */
    @LispMethod(comment = "Load the connectivity information for one script graph as visible from mt.\r\nThe information consists of sub-events and role player equivalences.\r\n\r\n@return ALIST of KEYWORD -> assertion pairs.\nLoad the connectivity information for one script graph as visible from mt.\nThe information consists of sub-events and role player equivalences.")
    public static final SubLObject get_script_graph_info_alt(SubLObject sit_type, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject graph_info = dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED);
                if (NIL != isa.not_isaP(sit_type, $$SituationType, UNPROVIDED, UNPROVIDED)) {
                    Errors.error($$$Argument_not_a_situation_type);
                } else {
                    {
                        SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(elmt);
                        {
                            SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                            SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                            SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                            try {
                                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                                {
                                    SubLObject sub_events = ask_utilities.query_variable($SUB_EVT_TYPE, listS($$properSubSituationTypes, sit_type, $list_alt18), UNPROVIDED, UNPROVIDED);
                                    dictionary_utilities.dictionary_push(graph_info, $SUB_EVENT_TYPES, sub_events);
                                    {
                                        SubLObject role_player_assertions = NIL;
                                        SubLObject query_results = NIL;
                                        SubLObject cdolist_list_var = $role_player_type_level_relations$.getDynamicValue(thread);
                                        SubLObject pred = NIL;
                                        for (pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pred = cdolist_list_var.first()) {
                                            query_results = com.cyc.cycjava.cycl.script_instance_editor_api.get_role_player_equivalences_for_pred_and_script(pred, sit_type, UNPROVIDED);
                                            {
                                                SubLObject cdolist_list_var_1 = query_results;
                                                SubLObject ass = NIL;
                                                for (ass = cdolist_list_var_1.first(); NIL != cdolist_list_var_1; cdolist_list_var_1 = cdolist_list_var_1.rest() , ass = cdolist_list_var_1.first()) {
                                                    role_player_assertions = cons(ass, role_player_assertions);
                                                }
                                            }
                                        }
                                        dictionary_utilities.dictionary_push(graph_info, $ROLE_PLAYER_EQUIVALENCES, role_player_assertions);
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
                return dictionary_utilities.dictionary_to_alist(graph_info);
            }
        }
    }

    /**
     * Load the connectivity information for one script graph as visible from mt.
     * The information consists of sub-events and role player equivalences.
     *
     * @return ALIST of KEYWORD -> assertion pairs.
     */
    @LispMethod(comment = "Load the connectivity information for one script graph as visible from mt.\r\nThe information consists of sub-events and role player equivalences.\r\n\r\n@return ALIST of KEYWORD -> assertion pairs.\nLoad the connectivity information for one script graph as visible from mt.\nThe information consists of sub-events and role player equivalences.")
    public static SubLObject get_script_graph_info(final SubLObject sit_type, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject graph_info = dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED);
        if (NIL != isa.not_isaP(sit_type, $$SituationType, UNPROVIDED, UNPROVIDED)) {
            Errors.error($$$Argument_not_a_situation_type);
        } else {
            final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(elmt);
            final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
            final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
            final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
            try {
                mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                final SubLObject sub_events = ask_utilities.query_variable($SUB_EVT_TYPE, listS($$properSubSituationTypes, sit_type, $list18), UNPROVIDED, UNPROVIDED);
                dictionary_utilities.dictionary_push(graph_info, $SUB_EVENT_TYPES, sub_events);
                SubLObject role_player_assertions = NIL;
                SubLObject query_results = NIL;
                SubLObject cdolist_list_var = $role_player_type_level_relations$.getDynamicValue(thread);
                SubLObject pred = NIL;
                pred = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject cdolist_list_var_$1;
                    query_results = cdolist_list_var_$1 = get_role_player_equivalences_for_pred_and_script(pred, sit_type, UNPROVIDED);
                    SubLObject ass = NIL;
                    ass = cdolist_list_var_$1.first();
                    while (NIL != cdolist_list_var_$1) {
                        role_player_assertions = cons(ass, role_player_assertions);
                        cdolist_list_var_$1 = cdolist_list_var_$1.rest();
                        ass = cdolist_list_var_$1.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    pred = cdolist_list_var.first();
                } 
                dictionary_utilities.dictionary_push(graph_info, $ROLE_PLAYER_EQUIVALENCES, role_player_assertions);
            } finally {
                mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
                mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
                mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
            }
        }
        return dictionary_utilities.dictionary_to_alist(graph_info);
    }

    /**
     * Get the role player equivalence sentences for one predicate and for the script.
     * Default to the *SCRIPT-INSTANCE-EDITOR-MT* if no MT is provided.
     *
     * @return LIST of role player equivalence sentences
     */
    @LispMethod(comment = "Get the role player equivalence sentences for one predicate and for the script.\r\nDefault to the *SCRIPT-INSTANCE-EDITOR-MT* if no MT is provided.\r\n\r\n@return LIST of role player equivalence sentences\nGet the role player equivalence sentences for one predicate and for the script.\nDefault to the *SCRIPT-INSTANCE-EDITOR-MT* if no MT is provided.")
    public static final SubLObject get_role_player_equivalences_for_pred_and_script_alt(SubLObject role_player_equiv_pred, SubLObject script, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        {
            SubLObject query_properties = inference_parameters.explicify_inference_engine_query_property_defaults(NIL);
            SubLObject query = NIL;
            SubLObject template = NIL;
            SubLObject results = NIL;
            if (role_player_equiv_pred.equal($const22$equivalentRolePlayers_SubSitTypeT)) {
                query = listS($const22$equivalentRolePlayers_SubSitTypeT, script, $list_alt23);
                template = listS($const22$equivalentRolePlayers_SubSitTypeT, script, $list_alt23);
            } else {
                if (role_player_equiv_pred.equalp($const24$equivalentRolePlayers_SitTypeToSu)) {
                    query = listS($const24$equivalentRolePlayers_SitTypeToSu, script, $list_alt25);
                    template = listS($const24$equivalentRolePlayers_SitTypeToSu, script, $list_alt25);
                } else {
                    return NIL;
                }
            }
            results = ask_utilities.query_template(template, query, elmt, query_properties);
            return Mapping.mapcar(symbol_function(CANONICALIZE_TERM), results);
        }
    }

    /**
     * Get the role player equivalence sentences for one predicate and for the script.
     * Default to the *SCRIPT-INSTANCE-EDITOR-MT* if no MT is provided.
     *
     * @return LIST of role player equivalence sentences
     */
    @LispMethod(comment = "Get the role player equivalence sentences for one predicate and for the script.\r\nDefault to the *SCRIPT-INSTANCE-EDITOR-MT* if no MT is provided.\r\n\r\n@return LIST of role player equivalence sentences\nGet the role player equivalence sentences for one predicate and for the script.\nDefault to the *SCRIPT-INSTANCE-EDITOR-MT* if no MT is provided.")
    public static SubLObject get_role_player_equivalences_for_pred_and_script(final SubLObject role_player_equiv_pred, final SubLObject script, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        final SubLObject query_properties = inference_parameters.explicify_inference_engine_query_property_defaults(NIL);
        SubLObject query = NIL;
        SubLObject template = NIL;
        SubLObject results = NIL;
        if (role_player_equiv_pred.equal($const22$equivalentRolePlayers_SubSitTypeT)) {
            query = listS($const22$equivalentRolePlayers_SubSitTypeT, script, $list23);
            template = listS($const22$equivalentRolePlayers_SubSitTypeT, script, $list23);
        } else {
            if (!role_player_equiv_pred.equalp($const24$equivalentRolePlayers_SitTypeToSu)) {
                return NIL;
            }
            query = listS($const24$equivalentRolePlayers_SitTypeToSu, script, $list25);
            template = listS($const24$equivalentRolePlayers_SitTypeToSu, script, $list25);
        }
        results = ask_utilities.query_template(template, query, elmt, query_properties);
        return Mapping.mapcar(symbol_function(CANONICALIZE_TERM), results);
    }

    /**
     * Get the graph, one level deep, of the sub-events and the role players for the
     * passed in situation type.
     *
     * @return ALIST of keyword -> list of assertions for either sub-events,
    role-players or parent events.
     */
    @LispMethod(comment = "Get the graph, one level deep, of the sub-events and the role players for the\r\npassed in situation type.\r\n\r\n@return ALIST of keyword -> list of assertions for either sub-events,\r\nrole-players or parent events.\nGet the graph, one level deep, of the sub-events and the role players for the\npassed in situation type.")
    public static final SubLObject get_graph_for_script_instance_alt(SubLObject sit_type_instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        return com.cyc.cycjava.cycl.script_instance_editor_api.get_graph_for_script_instance_impl(sit_type_instance, elmt);
    }

    /**
     * Get the graph, one level deep, of the sub-events and the role players for the
     * passed in situation type.
     *
     * @return ALIST of keyword -> list of assertions for either sub-events,
    role-players or parent events.
     */
    @LispMethod(comment = "Get the graph, one level deep, of the sub-events and the role players for the\r\npassed in situation type.\r\n\r\n@return ALIST of keyword -> list of assertions for either sub-events,\r\nrole-players or parent events.\nGet the graph, one level deep, of the sub-events and the role players for the\npassed in situation type.")
    public static SubLObject get_graph_for_script_instance(final SubLObject sit_type_instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        return get_graph_for_script_instance_impl(sit_type_instance, elmt);
    }

    public static final SubLObject get_graph_for_script_instance_impl_alt(SubLObject situation_type_instance, SubLObject elmt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject categorized_assertion_map = NIL;
                SubLObject genl_pred_map = NIL;
                categorized_assertion_map = dictionary.new_dictionary(symbol_function(EQUAL), ZERO_INTEGER);
                genl_pred_map = dictionary.new_dictionary(symbol_function(EQUAL), ZERO_INTEGER);
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == kb_accessors.individualP(situation_type_instance)) {
                        Errors.error($str_alt28$_A_is_not_an_individual_, situation_type_instance);
                    }
                }
                {
                    SubLObject cdolist_list_var = $sub_situation_instance_level_relations$.getDynamicValue(thread);
                    SubLObject pred = NIL;
                    for (pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pred = cdolist_list_var.first()) {
                        if (NIL != list_utilities.intersectP(genl_predicates.min_genl_predicates(pred, elmt, UNPROVIDED), $sub_situation_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                            dictionary_utilities.dictionary_push(genl_pred_map, pred, $GENLS);
                        }
                        if (NIL != list_utilities.intersectP(genl_predicates.max_spec_predicates(pred, elmt, UNPROVIDED), $sub_situation_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                            dictionary_utilities.dictionary_push(genl_pred_map, pred, $SPEC);
                        }
                    }
                }
                {
                    SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(elmt);
                    {
                        SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                        SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                        SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                        try {
                            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                            {
                                SubLObject pred_var = NIL;
                                if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(situation_type_instance, NIL, pred_var)) {
                                    {
                                        SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(situation_type_instance, NIL, pred_var);
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
                                                                SubLObject done_var_2 = NIL;
                                                                SubLObject token_var_3 = NIL;
                                                                while (NIL == done_var_2) {
                                                                    {
                                                                        SubLObject ass = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_3);
                                                                        SubLObject valid_4 = makeBoolean(token_var_3 != ass);
                                                                        if (NIL != valid_4) {
                                                                            {
                                                                                SubLObject pred = assertions_high.gaf_arg0(ass);
                                                                                SubLObject type = NIL;
                                                                                SubLObject event_type = NIL;
                                                                                SubLObject check_assertion_list = NIL;
                                                                                SubLObject spec_foundP = NIL;
                                                                                SubLObject assertions_to_remove = NIL;
                                                                                if (assertions_high.gaf_arg2(ass).equalp(situation_type_instance)) {
                                                                                    event_type = $PARENT_EVENTS;
                                                                                } else {
                                                                                    event_type = $SUB_EVENTS;
                                                                                }
                                                                                if (NIL != subl_promotions.memberP(pred, $sub_situation_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                                                                                    type = dictionary.dictionary_lookup(genl_pred_map, pred, UNPROVIDED);
                                                                                    if (type.equalp($GENLS)) {
                                                                                        check_assertion_list = dictionary_utilities.dictionary_pop(categorized_assertion_map, event_type);
                                                                                        {
                                                                                            SubLObject cdolist_list_var = check_assertion_list;
                                                                                            SubLObject each_item = NIL;
                                                                                            for (each_item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , each_item = cdolist_list_var.first()) {
                                                                                                if (assertions_high.gaf_arg2(each_item).equal(assertions_high.gaf_arg2(ass)) && (NIL != subl_promotions.memberP(assertions_high.gaf_arg0(each_item), genl_predicates.min_genl_predicates(pred, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED))) {
                                                                                                    assertions_to_remove = cons(each_item, assertions_to_remove);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        check_assertion_list = list_utilities.compare_lists(check_assertion_list, assertions_to_remove, UNPROVIDED, UNPROVIDED);
                                                                                        dictionary_utilities.dictionary_push(categorized_assertion_map, event_type, check_assertion_list);
                                                                                    } else {
                                                                                        spec_foundP = NIL;
                                                                                        check_assertion_list = dictionary.dictionary_lookup(categorized_assertion_map, event_type, UNPROVIDED);
                                                                                        {
                                                                                            SubLObject rest = NIL;
                                                                                            for (rest = check_assertion_list; !((NIL != spec_foundP) || (NIL == rest)); rest = rest.rest()) {
                                                                                                {
                                                                                                    SubLObject each_item = rest.first();
                                                                                                    if (assertions_high.gaf_arg2(each_item).equal(assertions_high.gaf_arg2(ass)) && (NIL != subl_promotions.memberP(assertions_high.gaf_arg0(each_item), genl_predicates.max_spec_predicates(pred, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED))) {
                                                                                                        spec_foundP = T;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        if (NIL == spec_foundP) {
                                                                                            dictionary_utilities.dictionary_push(categorized_assertion_map, event_type, ass);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                if (NIL != subl_promotions.memberP(pred, $role_player_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                                                                                    dictionary_utilities.dictionary_push(categorized_assertion_map, $ROLE_PLAYERS, ass);
                                                                                }
                                                                            }
                                                                        }
                                                                        done_var_2 = makeBoolean(NIL == valid_4);
                                                                    }
                                                                } 
                                                            }
                                                        } finally {
                                                            {
                                                                SubLObject _prev_bind_0_5 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                                try {
                                                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                                                    if (NIL != final_index_iterator) {
                                                                        kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                                                    }
                                                                } finally {
                                                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_5, thread);
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
                                {
                                    SubLObject cdolist_list_var = com.cyc.cycjava.cycl.script_instance_editor_api.get_ancestor_nodes(situation_type_instance, elmt);
                                    SubLObject each_ass = NIL;
                                    for (each_ass = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , each_ass = cdolist_list_var.first()) {
                                        dictionary_utilities.dictionary_push(categorized_assertion_map, $PARENT_EVENTS, each_ass);
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
                return dictionary_utilities.dictionary_to_alist(categorized_assertion_map);
            }
        }
    }

    public static SubLObject get_graph_for_script_instance_impl(final SubLObject situation_type_instance, final SubLObject elmt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject categorized_assertion_map = NIL;
        SubLObject genl_pred_map = NIL;
        categorized_assertion_map = dictionary.new_dictionary(symbol_function(EQUAL), ZERO_INTEGER);
        genl_pred_map = dictionary.new_dictionary(symbol_function(EQUAL), ZERO_INTEGER);
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == kb_accessors.individualP(situation_type_instance))) {
            Errors.error($str28$_A_is_not_an_individual_, situation_type_instance);
        }
        SubLObject cdolist_list_var = $sub_situation_instance_level_relations$.getDynamicValue(thread);
        SubLObject pred = NIL;
        pred = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != list_utilities.intersectP(genl_predicates.min_genl_predicates(pred, elmt, UNPROVIDED), $sub_situation_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                dictionary_utilities.dictionary_push(genl_pred_map, pred, $GENLS);
            }
            if (NIL != list_utilities.intersectP(genl_predicates.max_spec_predicates(pred, elmt, UNPROVIDED), $sub_situation_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                dictionary_utilities.dictionary_push(genl_pred_map, pred, $SPEC);
            }
            cdolist_list_var = cdolist_list_var.rest();
            pred = cdolist_list_var.first();
        } 
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(elmt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            final SubLObject pred_var = NIL;
            if (NIL != kb_mapping_macros.do_gaf_arg_index_key_validator(situation_type_instance, NIL, pred_var)) {
                final SubLObject iterator_var = kb_mapping_macros.new_gaf_arg_final_index_spec_iterator(situation_type_instance, NIL, pred_var);
                SubLObject done_var = NIL;
                final SubLObject token_var = NIL;
                while (NIL == done_var) {
                    final SubLObject final_index_spec = iteration.iteration_next_without_values_macro_helper(iterator_var, token_var);
                    final SubLObject valid = makeBoolean(!token_var.eql(final_index_spec));
                    if (NIL != valid) {
                        SubLObject final_index_iterator = NIL;
                        try {
                            final_index_iterator = kb_mapping_macros.new_final_index_iterator(final_index_spec, $GAF, NIL, NIL);
                            SubLObject done_var_$2 = NIL;
                            final SubLObject token_var_$3 = NIL;
                            while (NIL == done_var_$2) {
                                final SubLObject ass = iteration.iteration_next_without_values_macro_helper(final_index_iterator, token_var_$3);
                                final SubLObject valid_$4 = makeBoolean(!token_var_$3.eql(ass));
                                if (NIL != valid_$4) {
                                    final SubLObject pred2 = assertions_high.gaf_arg0(ass);
                                    SubLObject type = NIL;
                                    SubLObject event_type = NIL;
                                    SubLObject check_assertion_list = NIL;
                                    SubLObject spec_foundP = NIL;
                                    SubLObject assertions_to_remove = NIL;
                                    if (assertions_high.gaf_arg2(ass).equalp(situation_type_instance)) {
                                        event_type = $PARENT_EVENTS;
                                    } else {
                                        event_type = $SUB_EVENTS;
                                    }
                                    if (NIL != subl_promotions.memberP(pred2, $sub_situation_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                                        type = dictionary.dictionary_lookup(genl_pred_map, pred2, UNPROVIDED);
                                        if (type.equalp($GENLS)) {
                                            SubLObject cdolist_list_var2;
                                            check_assertion_list = cdolist_list_var2 = dictionary_utilities.dictionary_pop(categorized_assertion_map, event_type);
                                            SubLObject each_item = NIL;
                                            each_item = cdolist_list_var2.first();
                                            while (NIL != cdolist_list_var2) {
                                                if (assertions_high.gaf_arg2(each_item).equal(assertions_high.gaf_arg2(ass)) && (NIL != subl_promotions.memberP(assertions_high.gaf_arg0(each_item), genl_predicates.min_genl_predicates(pred2, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED))) {
                                                    assertions_to_remove = cons(each_item, assertions_to_remove);
                                                }
                                                cdolist_list_var2 = cdolist_list_var2.rest();
                                                each_item = cdolist_list_var2.first();
                                            } 
                                            check_assertion_list = list_utilities.compare_lists(check_assertion_list, assertions_to_remove, UNPROVIDED, UNPROVIDED);
                                            dictionary_utilities.dictionary_push(categorized_assertion_map, event_type, check_assertion_list);
                                        } else {
                                            spec_foundP = NIL;
                                            check_assertion_list = dictionary.dictionary_lookup(categorized_assertion_map, event_type, UNPROVIDED);
                                            SubLObject each_item;
                                            SubLObject rest;
                                            for (rest = NIL, rest = check_assertion_list; (NIL == spec_foundP) && (NIL != rest); rest = rest.rest()) {
                                                each_item = rest.first();
                                                if (assertions_high.gaf_arg2(each_item).equal(assertions_high.gaf_arg2(ass)) && (NIL != subl_promotions.memberP(assertions_high.gaf_arg0(each_item), genl_predicates.max_spec_predicates(pred2, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED))) {
                                                    spec_foundP = T;
                                                }
                                            }
                                            if (NIL == spec_foundP) {
                                                dictionary_utilities.dictionary_push(categorized_assertion_map, event_type, ass);
                                            }
                                        }
                                    }
                                    if (NIL != subl_promotions.memberP(pred2, $role_player_instance_level_relations$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED)) {
                                        dictionary_utilities.dictionary_push(categorized_assertion_map, $ROLE_PLAYERS, ass);
                                    }
                                }
                                done_var_$2 = makeBoolean(NIL == valid_$4);
                            } 
                        } finally {
                            final SubLObject _prev_bind_0_$5 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                final SubLObject _values = getValuesAsVector();
                                if (NIL != final_index_iterator) {
                                    kb_mapping_macros.destroy_final_index_iterator(final_index_iterator);
                                }
                                restoreValuesFromVector(_values);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$5, thread);
                            }
                        }
                    }
                    done_var = makeBoolean(NIL == valid);
                } 
            }
            SubLObject cdolist_list_var3 = get_ancestor_nodes(situation_type_instance, elmt);
            SubLObject each_ass = NIL;
            each_ass = cdolist_list_var3.first();
            while (NIL != cdolist_list_var3) {
                dictionary_utilities.dictionary_push(categorized_assertion_map, $PARENT_EVENTS, each_ass);
                cdolist_list_var3 = cdolist_list_var3.rest();
                each_ass = cdolist_list_var3.first();
            } 
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        return dictionary_utilities.dictionary_to_alist(categorized_assertion_map);
    }

    /**
     * Given a starting SitType instance, walk the graph and gather up all the subtypes.
     *
     * @return an ALIST of sittype-instances => result of the GET-GRAPH-FOR-SCRIPT-INSTANCE
    calls tha reflect the whole hierarchy.
     */
    @LispMethod(comment = "Given a starting SitType instance, walk the graph and gather up all the subtypes.\r\n\r\n@return an ALIST of sittype-instances => result of the GET-GRAPH-FOR-SCRIPT-INSTANCE\r\ncalls tha reflect the whole hierarchy.")
    public static final SubLObject get_complete_graph_for_script_instance_alt(SubLObject sittype_instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        {
            SubLObject to_check = queues.create_queue();
            SubLObject checked = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
            queues.enqueue(sittype_instance, to_check);
            while (NIL == queues.queue_empty_p(to_check)) {
                {
                    SubLObject current = queues.dequeue(to_check);
                    SubLObject instance_graph = com.cyc.cycjava.cycl.script_instance_editor_api.get_graph_for_script_instance(current, elmt);
                    dictionary.dictionary_enter(checked, current, instance_graph);
                    {
                        SubLObject sub_event_assertions = assoc($SUB_EVENTS, instance_graph, UNPROVIDED, UNPROVIDED).rest();
                        SubLObject cdolist_list_var = sub_event_assertions;
                        SubLObject sub_event_assertion = NIL;
                        for (sub_event_assertion = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sub_event_assertion = cdolist_list_var.first()) {
                            {
                                SubLObject sentence = uncanonicalizer.assertion_el_formula(sub_event_assertion);
                                SubLObject sub_event = cycl_utilities.formula_arg2(sentence, UNPROVIDED);
                                if ($NOT_FOUND == dictionary.dictionary_lookup(checked, sub_event, $NOT_FOUND)) {
                                    queues.enqueue(sub_event, to_check);
                                }
                            }
                        }
                    }
                }
            } 
            return dictionary_utilities.dictionary_to_alist(checked);
        }
    }

    /**
     * Given a starting SitType instance, walk the graph and gather up all the subtypes.
     *
     * @return an ALIST of sittype-instances => result of the GET-GRAPH-FOR-SCRIPT-INSTANCE
    calls tha reflect the whole hierarchy.
     */
    @LispMethod(comment = "Given a starting SitType instance, walk the graph and gather up all the subtypes.\r\n\r\n@return an ALIST of sittype-instances => result of the GET-GRAPH-FOR-SCRIPT-INSTANCE\r\ncalls tha reflect the whole hierarchy.")
    public static SubLObject get_complete_graph_for_script_instance(final SubLObject sittype_instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        final SubLObject to_check = queues.create_queue(UNPROVIDED);
        final SubLObject checked = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
        queues.enqueue(sittype_instance, to_check);
        while (NIL == queues.queue_empty_p(to_check)) {
            final SubLObject current = queues.dequeue(to_check);
            final SubLObject instance_graph = get_graph_for_script_instance(current, elmt);
            dictionary.dictionary_enter(checked, current, instance_graph);
            SubLObject cdolist_list_var;
            final SubLObject sub_event_assertions = cdolist_list_var = assoc($SUB_EVENTS, instance_graph, UNPROVIDED, UNPROVIDED).rest();
            SubLObject sub_event_assertion = NIL;
            sub_event_assertion = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject sentence = uncanonicalizer.assertion_el_formula(sub_event_assertion);
                final SubLObject sub_event = cycl_utilities.formula_arg2(sentence, UNPROVIDED);
                if ($NOT_FOUND == dictionary.dictionary_lookup(checked, sub_event, $NOT_FOUND)) {
                    queues.enqueue(sub_event, to_check);
                }
                cdolist_list_var = cdolist_list_var.rest();
                sub_event_assertion = cdolist_list_var.first();
            } 
        } 
        return dictionary_utilities.dictionary_to_alist(checked);
    }

    /**
     * Given a graph of the type returned by GET-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE
     * and dictionary that maps assertion objects to sentences, generate a new instance graph
     * that reflects the assertions replaced with the hypothetical sentences.
     * This method is useful for WHAT-IF type of situations where some of the information
     * is reified in the KB and some is not.
     *
     * @return return an ALIST of sittype-instances => result of the GET-GRAPH-FOR-SCRIPT-INSTANCE
    calls tha reflect the whole hierarchy, with a mix of ASSERTION-P and PROPERTY-LIST-P
    objects of the form (:SENTENCE <ASENT> :ELMT <ELMT>)
     */
    @LispMethod(comment = "Given a graph of the type returned by GET-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE\r\nand dictionary that maps assertion objects to sentences, generate a new instance graph\r\nthat reflects the assertions replaced with the hypothetical sentences.\r\nThis method is useful for WHAT-IF type of situations where some of the information\r\nis reified in the KB and some is not.\r\n\r\n@return return an ALIST of sittype-instances => result of the GET-GRAPH-FOR-SCRIPT-INSTANCE\r\ncalls tha reflect the whole hierarchy, with a mix of ASSERTION-P and PROPERTY-LIST-P\r\nobjects of the form (:SENTENCE <ASENT> :ELMT <ELMT>)\nGiven a graph of the type returned by GET-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE\nand dictionary that maps assertion objects to sentences, generate a new instance graph\nthat reflects the assertions replaced with the hypothetical sentences.\nThis method is useful for WHAT-IF type of situations where some of the information\nis reified in the KB and some is not.")
    public static final SubLObject augment_complete_graph_for_script_instance_alt(SubLObject instance_graph, SubLObject sentence_map, SubLObject term_map) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_graph = copy_tree(instance_graph);
                {
                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(sentence_map));
                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                        thread.resetMultipleValues();
                        {
                            SubLObject assertion = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                            SubLObject sentence = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            if (NIL != assertion_handles.assertion_p(assertion)) {
                                {
                                    SubLObject elmt = uncanonicalizer.assertion_elmt(assertion);
                                    SubLObject hypothetical = list($SENTENCE, sentence, $ELMT, elmt);
                                    v_graph = list_utilities.tree_substitute(v_graph, assertion, hypothetical);
                                }
                            } else {
                                v_graph = list_utilities.tree_substitute(v_graph, assertion, sentence);
                            }
                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                        }
                    } 
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                }
                {
                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(term_map));
                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                        thread.resetMultipleValues();
                        {
                            SubLObject old_term = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                            SubLObject new_term = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            v_graph = list_utilities.tree_substitute(v_graph, old_term, new_term);
                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                        }
                    } 
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                }
                return v_graph;
            }
        }
    }

    @LispMethod(comment = "Given a graph of the type returned by GET-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE\r\nand dictionary that maps assertion objects to sentences, generate a new instance graph\r\nthat reflects the assertions replaced with the hypothetical sentences.\r\nThis method is useful for WHAT-IF type of situations where some of the information\r\nis reified in the KB and some is not.\r\n\r\n@return return an ALIST of sittype-instances => result of the GET-GRAPH-FOR-SCRIPT-INSTANCE\r\ncalls tha reflect the whole hierarchy, with a mix of ASSERTION-P and PROPERTY-LIST-P\r\nobjects of the form (:SENTENCE <ASENT> :ELMT <ELMT>)\nGiven a graph of the type returned by GET-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE\nand dictionary that maps assertion objects to sentences, generate a new instance graph\nthat reflects the assertions replaced with the hypothetical sentences.\nThis method is useful for WHAT-IF type of situations where some of the information\nis reified in the KB and some is not.")
    public static SubLObject augment_complete_graph_for_script_instance(final SubLObject instance_graph, final SubLObject sentence_map, final SubLObject term_map) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject v_graph = copy_tree(instance_graph);
        SubLObject iteration_state;
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(sentence_map)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject assertion = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject sentence = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != assertion_handles.assertion_p(assertion)) {
                final SubLObject elmt = uncanonicalizer.assertion_elmt(assertion);
                final SubLObject hypothetical = list($SENTENCE, sentence, $ELMT, elmt);
                v_graph = list_utilities.tree_substitute(v_graph, assertion, hypothetical);
            } else {
                v_graph = list_utilities.tree_substitute(v_graph, assertion, sentence);
            }
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(term_map)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject old_term = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
            final SubLObject new_term = thread.secondMultipleValue();
            thread.resetMultipleValues();
            v_graph = list_utilities.tree_substitute(v_graph, old_term, new_term);
        }
        dictionary_contents.do_dictionary_contents_finalize(iteration_state);
        return v_graph;
    }

    /**
     * Get a graph of all the sub-events of the situation type instance
     * where a role player is participating and the role that they are playing
     * in these events.
     *
     * @return ALIST of keywords -> assertions for the information.
     */
    @LispMethod(comment = "Get a graph of all the sub-events of the situation type instance\r\nwhere a role player is participating and the role that they are playing\r\nin these events.\r\n\r\n@return ALIST of keywords -> assertions for the information.\nGet a graph of all the sub-events of the situation type instance\nwhere a role player is participating and the role that they are playing\nin these events.")
    public static final SubLObject get_graph_for_script_role_player_instance_alt(SubLObject role_player, SubLObject sit_type_instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject categorized_assertion_map = dictionary.new_dictionary(symbol_function(EQUAL), UNPROVIDED);
                SubLObject preds = make_formula($$TheSet, $role_player_instance_level_relations$.getDynamicValue(thread), UNPROVIDED);
                SubLObject sub_events = NIL;
                SubLObject query = NIL;
                SubLObject results = NIL;
                sub_events = ask_utilities.query_variable($EVT, listS($$properSubEvents, sit_type_instance, $list_alt44), elmt, UNPROVIDED);
                {
                    SubLObject item_var = sit_type_instance;
                    if (NIL == member(item_var, sub_events, symbol_function(EQL), symbol_function(IDENTITY))) {
                        sub_events = cons(item_var, sub_events);
                    }
                }
                sub_events = make_formula($$TheSet, sub_events, UNPROVIDED);
                query = list($$and, list($$assertedSentence, list($sym47$_PRED, $sym48$_SUB_EVENT, role_player)), list($$elementOf, $sym47$_PRED, preds), list($$elementOf, $sym48$_SUB_EVENT, sub_events));
                results = ask_utilities.query_template(list($sym47$_PRED, $sym48$_SUB_EVENT, role_player), query, elmt, UNPROVIDED);
                {
                    SubLObject cdolist_list_var = results;
                    SubLObject result = NIL;
                    for (result = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , result = cdolist_list_var.first()) {
                        {
                            SubLObject ass = czer_meta.find_visible_assertion_cycl(result, elmt);
                            dictionary_utilities.dictionary_push(categorized_assertion_map, $ROLE_PLAYERS, ass);
                        }
                    }
                }
                return dictionary_utilities.dictionary_to_alist(categorized_assertion_map);
            }
        }
    }

    @LispMethod(comment = "Get a graph of all the sub-events of the situation type instance\r\nwhere a role player is participating and the role that they are playing\r\nin these events.\r\n\r\n@return ALIST of keywords -> assertions for the information.\nGet a graph of all the sub-events of the situation type instance\nwhere a role player is participating and the role that they are playing\nin these events.")
    public static SubLObject get_graph_for_script_role_player_instance(final SubLObject role_player, final SubLObject sit_type_instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject categorized_assertion_map = dictionary.new_dictionary(symbol_function(EQUAL), UNPROVIDED);
        final SubLObject preds = make_formula($$TheSet, $role_player_instance_level_relations$.getDynamicValue(thread), UNPROVIDED);
        SubLObject sub_events = NIL;
        SubLObject query = NIL;
        SubLObject results = NIL;
        sub_events = ask_utilities.query_variable($EVT, listS($$properSubEvents, sit_type_instance, $list44), elmt, UNPROVIDED);
        if (NIL == member(sit_type_instance, sub_events, symbol_function(EQL), symbol_function(IDENTITY))) {
            sub_events = cons(sit_type_instance, sub_events);
        }
        sub_events = make_formula($$TheSet, sub_events, UNPROVIDED);
        query = list($$and, list($$assertedSentence, list($sym47$_PRED, $sym48$_SUB_EVENT, role_player)), list($$elementOf, $sym47$_PRED, preds), list($$elementOf, $sym48$_SUB_EVENT, sub_events));
        SubLObject cdolist_list_var;
        results = cdolist_list_var = ask_utilities.query_template(list($sym47$_PRED, $sym48$_SUB_EVENT, role_player), query, elmt, UNPROVIDED);
        SubLObject result = NIL;
        result = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject ass = czer_meta.find_visible_assertion_cycl(result, elmt);
            dictionary_utilities.dictionary_push(categorized_assertion_map, $ROLE_PLAYERS, ass);
            cdolist_list_var = cdolist_list_var.rest();
            result = cdolist_list_var.first();
        } 
        return dictionary_utilities.dictionary_to_alist(categorized_assertion_map);
    }/**
     * Get a graph of all the sub-events of the situation type instance
     * where a role player is participating and the role that they are playing
     * in these events.
     *
     * @return ALIST of keywords -> assertions for the information.
     */


    /**
     * Scans for ancestor nodes acroiss microtheories.
     *
     * @return LIST of Assertions that express proper sub event information.
     */
    @LispMethod(comment = "Scans for ancestor nodes acroiss microtheories.\r\n\r\n@return LIST of Assertions that express proper sub event information.")
    public static final SubLObject get_ancestor_nodes_alt(SubLObject instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        {
            SubLObject results = NIL;
            SubLObject query_properties = NIL;
            SubLObject assertions = NIL;
            query_properties = inference_parameters.explicify_inference_engine_query_property_defaults(NIL);
            putf(query_properties, $DISJUNCTION_FREE_EL_VARS_POLICY, $COMPUTE_UNION);
            results = ask_utilities.query_template($list_alt53, listS($$and, list($$properSubEvents, $sym54$_SUB1, instance), $list_alt55), $$InferencePSC, query_properties);
            {
                SubLObject cdolist_list_var = results;
                SubLObject res = NIL;
                for (res = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , res = cdolist_list_var.first()) {
                    {
                        SubLObject datum = res;
                        SubLObject current = datum;
                        SubLObject arg_1 = NIL;
                        SubLObject arg_2 = NIL;
                        SubLObject item = NIL;
                        SubLObject mt = NIL;
                        SubLObject mt2 = NIL;
                        destructuring_bind_must_consp(current, datum, $list_alt56);
                        arg_1 = current.first();
                        current = current.rest();
                        destructuring_bind_must_consp(current, datum, $list_alt56);
                        arg_2 = current.first();
                        current = current.rest();
                        destructuring_bind_must_consp(current, datum, $list_alt56);
                        item = current.first();
                        current = current.rest();
                        destructuring_bind_must_consp(current, datum, $list_alt56);
                        mt = current.first();
                        current = current.rest();
                        destructuring_bind_must_consp(current, datum, $list_alt56);
                        mt2 = current.first();
                        current = current.rest();
                        if (NIL == current) {
                            if (item.equal(instance)) {
                                {
                                    SubLObject ass_var = czer_meta.find_visible_assertion_cycl(list($$properSubEvents, arg_1, arg_2), mt);
                                    assertions = cons(ass_var, assertions);
                                }
                            }
                        } else {
                            cdestructuring_bind_error(datum, $list_alt56);
                        }
                    }
                }
            }
            return assertions;
        }
    }

    @LispMethod(comment = "Scans for ancestor nodes acroiss microtheories.\r\n\r\n@return LIST of Assertions that express proper sub event information.")
    public static SubLObject get_ancestor_nodes(final SubLObject instance, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        SubLObject results = NIL;
        SubLObject query_properties = NIL;
        SubLObject assertions = NIL;
        query_properties = inference_parameters.explicify_inference_engine_query_property_defaults(NIL);
        putf(query_properties, $DISJUNCTION_FREE_EL_VARS_POLICY, $COMPUTE_UNION);
        SubLObject cdolist_list_var;
        results = cdolist_list_var = ask_utilities.query_template($list53, listS($$and, list($$properSubEvents, $sym54$_SUB1, instance), $list55), $$InferencePSC, query_properties);
        SubLObject res = NIL;
        res = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = res;
            SubLObject arg_1 = NIL;
            SubLObject arg_2 = NIL;
            SubLObject item = NIL;
            SubLObject mt = NIL;
            SubLObject mt2 = NIL;
            destructuring_bind_must_consp(current, datum, $list56);
            arg_1 = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list56);
            arg_2 = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list56);
            item = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list56);
            mt = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list56);
            mt2 = current.first();
            current = current.rest();
            if (NIL == current) {
                if (item.equal(instance)) {
                    final SubLObject ass_var = czer_meta.find_visible_assertion_cycl(list($$properSubEvents, arg_1, arg_2), mt);
                    assertions = cons(ass_var, assertions);
                }
            } else {
                cdestructuring_bind_error(datum, $list56);
            }
            cdolist_list_var = cdolist_list_var.rest();
            res = cdolist_list_var.first();
        } 
        return assertions;
    }/**
     * Scans for ancestor nodes acroiss microtheories.
     *
     * @return LIST of Assertions that express proper sub event information.
     */


    /**
     * Use roleplayerequivalence predicates in SCRIPT to find equivalences in
     * script SCRIPT-instance about roles played by ROLE-PLAYER in SCRIPT-INSTANCE
     */
    @LispMethod(comment = "Use roleplayerequivalence predicates in SCRIPT to find equivalences in\r\nscript SCRIPT-instance about roles played by ROLE-PLAYER in SCRIPT-INSTANCE\nUse roleplayerequivalence predicates in SCRIPT to find equivalences in\nscript SCRIPT-instance about roles played by ROLE-PLAYER in SCRIPT-INSTANCE")
    public static final SubLObject get_role_player_equivalences_alt(SubLObject role_player, SubLObject script_instance, SubLObject script, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject known_facts = NIL;
                SubLObject roles_played_by = NIL;
                SubLObject equiv_sub_sit_list = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(elmt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        known_facts = event_learning.get_represented_facts_for_event(script_instance, elmt);
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                {
                    SubLObject cdolist_list_var = known_facts;
                    SubLObject fact = NIL;
                    for (fact = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fact = cdolist_list_var.first()) {
                        if (role_player.equal(third(fact))) {
                            {
                                SubLObject item_var = fact;
                                if (NIL == member(item_var, roles_played_by, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                    roles_played_by = cons(item_var, roles_played_by);
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject script_map = com.cyc.cycjava.cycl.script_instance_editor_api.get_script_graph_info(script, elmt);
                    SubLObject script_role_player_equivalences = list_utilities.alist_lookup(script_map, $ROLE_PLAYER_EQUIVALENCES, UNPROVIDED, UNPROVIDED);
                    SubLObject equivalence_predicate = NIL;
                    SubLObject cdolist_list_var = script_role_player_equivalences.first();
                    SubLObject role_player_relation = NIL;
                    for (role_player_relation = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , role_player_relation = cdolist_list_var.first()) {
                        equivalence_predicate = role_player_relation.first();
                        if (equivalence_predicate.equal($const22$equivalentRolePlayers_SubSitTypeT)) {
                            {
                                SubLObject sit_type = second(role_player_relation);
                                SubLObject role_1 = third(role_player_relation);
                                SubLObject sub_sit_type_1 = fourth(role_player_relation);
                                SubLObject role_2 = fifth(role_player_relation);
                                SubLObject sub_sit_type_2 = sixth(role_player_relation);
                                SubLObject roles_map = com.cyc.cycjava.cycl.script_instance_editor_api.all_roles_matching(roles_played_by, list(role_1, role_2));
                                SubLObject roles_by_role_1 = dictionary.dictionary_lookup(roles_map, role_1, UNPROVIDED);
                                SubLObject roles_by_role_2 = dictionary.dictionary_lookup(roles_map, role_2, UNPROVIDED);
                                if ((NIL != roles_by_role_1) && (NIL != roles_by_role_2)) {
                                    {
                                        SubLObject cdolist_list_var_6 = roles_by_role_1;
                                        SubLObject role_formula = NIL;
                                        for (role_formula = cdolist_list_var_6.first(); NIL != cdolist_list_var_6; cdolist_list_var_6 = cdolist_list_var_6.rest() , role_formula = cdolist_list_var_6.first()) {
                                            if (NIL != isa.isaP(second(role_formula), sub_sit_type_1, elmt, UNPROVIDED)) {
                                                {
                                                    SubLObject cdolist_list_var_7 = roles_by_role_2;
                                                    SubLObject role_formula2 = NIL;
                                                    for (role_formula2 = cdolist_list_var_7.first(); NIL != cdolist_list_var_7; cdolist_list_var_7 = cdolist_list_var_7.rest() , role_formula2 = cdolist_list_var_7.first()) {
                                                        if (NIL != isa.isaP(second(role_formula2), sub_sit_type_2, elmt, UNPROVIDED)) {
                                                            {
                                                                SubLObject item_var = list(second(role_formula), role_1);
                                                                if (NIL == member(item_var, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                                                    equiv_sub_sit_list = cons(item_var, equiv_sub_sit_list);
                                                                }
                                                            }
                                                            {
                                                                SubLObject item_var = list(second(role_formula2), role_2);
                                                                if (NIL == member(item_var, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                                                    equiv_sub_sit_list = cons(item_var, equiv_sub_sit_list);
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
                        } else {
                            if (equivalence_predicate.equal($const24$equivalentRolePlayers_SitTypeToSu)) {
                                {
                                    SubLObject sit_type = second(role_player_relation);
                                    SubLObject sit_role_1 = third(role_player_relation);
                                    SubLObject sub_sit_type_1 = fourth(role_player_relation);
                                    SubLObject sub_sit_role_2 = fifth(role_player_relation);
                                    SubLObject roles_map = com.cyc.cycjava.cycl.script_instance_editor_api.all_roles_matching(roles_played_by, list(sit_role_1, sub_sit_role_2));
                                    SubLObject roles_by_role_1 = dictionary.dictionary_lookup(roles_map, sit_role_1, UNPROVIDED);
                                    SubLObject roles_by_role_2 = dictionary.dictionary_lookup(roles_map, sub_sit_role_2, UNPROVIDED);
                                    if ((NIL != roles_by_role_1) && (NIL != roles_by_role_2)) {
                                        {
                                            SubLObject cdolist_list_var_8 = roles_by_role_1;
                                            SubLObject role_formula = NIL;
                                            for (role_formula = cdolist_list_var_8.first(); NIL != cdolist_list_var_8; cdolist_list_var_8 = cdolist_list_var_8.rest() , role_formula = cdolist_list_var_8.first()) {
                                                if (NIL != isa.isaP(second(role_formula), sit_type, elmt, UNPROVIDED)) {
                                                    {
                                                        SubLObject cdolist_list_var_9 = roles_by_role_2;
                                                        SubLObject role_formula2 = NIL;
                                                        for (role_formula2 = cdolist_list_var_9.first(); NIL != cdolist_list_var_9; cdolist_list_var_9 = cdolist_list_var_9.rest() , role_formula2 = cdolist_list_var_9.first()) {
                                                            if (NIL != isa.isaP(second(role_formula2), sub_sit_type_1, elmt, UNPROVIDED)) {
                                                                {
                                                                    SubLObject item_var = list(second(role_formula), sit_role_1);
                                                                    if (NIL == member(item_var, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                                                        equiv_sub_sit_list = cons(item_var, equiv_sub_sit_list);
                                                                    }
                                                                }
                                                                {
                                                                    SubLObject item_var = list(second(role_formula2), sub_sit_role_2);
                                                                    if (NIL == member(item_var, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                                                        equiv_sub_sit_list = cons(item_var, equiv_sub_sit_list);
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
                return equiv_sub_sit_list;
            }
        }
    }

    /**
     * Use roleplayerequivalence predicates in SCRIPT to find equivalences in
     * script SCRIPT-instance about roles played by ROLE-PLAYER in SCRIPT-INSTANCE
     */
    @LispMethod(comment = "Use roleplayerequivalence predicates in SCRIPT to find equivalences in\r\nscript SCRIPT-instance about roles played by ROLE-PLAYER in SCRIPT-INSTANCE\nUse roleplayerequivalence predicates in SCRIPT to find equivalences in\nscript SCRIPT-instance about roles played by ROLE-PLAYER in SCRIPT-INSTANCE")
    public static SubLObject get_role_player_equivalences(final SubLObject role_player, final SubLObject script_instance, final SubLObject script, SubLObject elmt) {
        if (elmt == UNPROVIDED) {
            elmt = $script_instance_editor_mt$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject known_facts = NIL;
        SubLObject roles_played_by = NIL;
        SubLObject equiv_sub_sit_list = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(elmt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            known_facts = event_learning.get_represented_facts_for_event(script_instance, elmt);
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        SubLObject cdolist_list_var = known_facts;
        SubLObject fact = NIL;
        fact = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (role_player.equal(third(fact))) {
                final SubLObject item_var = fact;
                if (NIL == member(item_var, roles_played_by, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                    roles_played_by = cons(item_var, roles_played_by);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            fact = cdolist_list_var.first();
        } 
        final SubLObject script_map = get_script_graph_info(script, elmt);
        final SubLObject script_role_player_equivalences = list_utilities.alist_lookup(script_map, $ROLE_PLAYER_EQUIVALENCES, UNPROVIDED, UNPROVIDED);
        SubLObject equivalence_predicate = NIL;
        SubLObject cdolist_list_var2 = script_role_player_equivalences.first();
        SubLObject role_player_relation = NIL;
        role_player_relation = cdolist_list_var2.first();
        while (NIL != cdolist_list_var2) {
            equivalence_predicate = role_player_relation.first();
            if (equivalence_predicate.equal($const22$equivalentRolePlayers_SubSitTypeT)) {
                final SubLObject sit_type = second(role_player_relation);
                final SubLObject role_1 = third(role_player_relation);
                final SubLObject sub_sit_type_1 = fourth(role_player_relation);
                final SubLObject role_2 = fifth(role_player_relation);
                final SubLObject sub_sit_type_2 = sixth(role_player_relation);
                final SubLObject roles_map = all_roles_matching(roles_played_by, list(role_1, role_2));
                final SubLObject roles_by_role_1 = dictionary.dictionary_lookup(roles_map, role_1, UNPROVIDED);
                final SubLObject roles_by_role_2 = dictionary.dictionary_lookup(roles_map, role_2, UNPROVIDED);
                if ((NIL != roles_by_role_1) && (NIL != roles_by_role_2)) {
                    SubLObject cdolist_list_var_$6 = roles_by_role_1;
                    SubLObject role_formula = NIL;
                    role_formula = cdolist_list_var_$6.first();
                    while (NIL != cdolist_list_var_$6) {
                        if (NIL != isa.isaP(second(role_formula), sub_sit_type_1, elmt, UNPROVIDED)) {
                            SubLObject cdolist_list_var_$7 = roles_by_role_2;
                            SubLObject role_formula2 = NIL;
                            role_formula2 = cdolist_list_var_$7.first();
                            while (NIL != cdolist_list_var_$7) {
                                if (NIL != isa.isaP(second(role_formula2), sub_sit_type_2, elmt, UNPROVIDED)) {
                                    SubLObject item_var2 = list(second(role_formula), role_1);
                                    if (NIL == member(item_var2, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                        equiv_sub_sit_list = cons(item_var2, equiv_sub_sit_list);
                                    }
                                    item_var2 = list(second(role_formula2), role_2);
                                    if (NIL == member(item_var2, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                        equiv_sub_sit_list = cons(item_var2, equiv_sub_sit_list);
                                    }
                                }
                                cdolist_list_var_$7 = cdolist_list_var_$7.rest();
                                role_formula2 = cdolist_list_var_$7.first();
                            } 
                        }
                        cdolist_list_var_$6 = cdolist_list_var_$6.rest();
                        role_formula = cdolist_list_var_$6.first();
                    } 
                }
            } else
                if (equivalence_predicate.equal($const24$equivalentRolePlayers_SitTypeToSu)) {
                    final SubLObject sit_type = second(role_player_relation);
                    final SubLObject sit_role_1 = third(role_player_relation);
                    final SubLObject sub_sit_type_1 = fourth(role_player_relation);
                    final SubLObject sub_sit_role_2 = fifth(role_player_relation);
                    final SubLObject roles_map2 = all_roles_matching(roles_played_by, list(sit_role_1, sub_sit_role_2));
                    final SubLObject roles_by_role_3 = dictionary.dictionary_lookup(roles_map2, sit_role_1, UNPROVIDED);
                    final SubLObject roles_by_role_4 = dictionary.dictionary_lookup(roles_map2, sub_sit_role_2, UNPROVIDED);
                    if ((NIL != roles_by_role_3) && (NIL != roles_by_role_4)) {
                        SubLObject cdolist_list_var_$8 = roles_by_role_3;
                        SubLObject role_formula3 = NIL;
                        role_formula3 = cdolist_list_var_$8.first();
                        while (NIL != cdolist_list_var_$8) {
                            if (NIL != isa.isaP(second(role_formula3), sit_type, elmt, UNPROVIDED)) {
                                SubLObject cdolist_list_var_$9 = roles_by_role_4;
                                SubLObject role_formula4 = NIL;
                                role_formula4 = cdolist_list_var_$9.first();
                                while (NIL != cdolist_list_var_$9) {
                                    if (NIL != isa.isaP(second(role_formula4), sub_sit_type_1, elmt, UNPROVIDED)) {
                                        SubLObject item_var3 = list(second(role_formula3), sit_role_1);
                                        if (NIL == member(item_var3, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                            equiv_sub_sit_list = cons(item_var3, equiv_sub_sit_list);
                                        }
                                        item_var3 = list(second(role_formula4), sub_sit_role_2);
                                        if (NIL == member(item_var3, equiv_sub_sit_list, symbol_function(EQUAL), symbol_function(IDENTITY))) {
                                            equiv_sub_sit_list = cons(item_var3, equiv_sub_sit_list);
                                        }
                                    }
                                    cdolist_list_var_$9 = cdolist_list_var_$9.rest();
                                    role_formula4 = cdolist_list_var_$9.first();
                                } 
                            }
                            cdolist_list_var_$8 = cdolist_list_var_$8.rest();
                            role_formula3 = cdolist_list_var_$8.first();
                        } 
                    }
                }

            cdolist_list_var2 = cdolist_list_var2.rest();
            role_player_relation = cdolist_list_var2.first();
        } 
        return equiv_sub_sit_list;
    }

    /**
     *
     *
     * @return a MAP with keys from LIST-OF-ROLES-TO-SEARCH and values as a list of elements
    from LIST-OF-ROLES-PLAYED where the role-player slot matches
     */
    @LispMethod(comment = "@return a MAP with keys from LIST-OF-ROLES-TO-SEARCH and values as a list of elements\r\nfrom LIST-OF-ROLES-PLAYED where the role-player slot matches")
    public static final SubLObject all_roles_matching_alt(SubLObject list_of_roles_played, SubLObject list_of_roles_to_search) {
        {
            SubLObject roles_map = dictionary.new_dictionary(symbol_function(EQUAL), UNPROVIDED);
            SubLObject cdolist_list_var = list_of_roles_to_search;
            SubLObject role_to_search = NIL;
            for (role_to_search = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , role_to_search = cdolist_list_var.first()) {
                {
                    SubLObject cdolist_list_var_10 = list_of_roles_played;
                    SubLObject role_played_formula = NIL;
                    for (role_played_formula = cdolist_list_var_10.first(); NIL != cdolist_list_var_10; cdolist_list_var_10 = cdolist_list_var_10.rest() , role_played_formula = cdolist_list_var_10.first()) {
                        if (role_played_formula.first().equalp(role_to_search)) {
                            dictionary_utilities.dictionary_push(roles_map, role_to_search, role_played_formula);
                        }
                    }
                }
            }
            return roles_map;
        }
    }

    /**
     *
     *
     * @return a MAP with keys from LIST-OF-ROLES-TO-SEARCH and values as a list of elements
    from LIST-OF-ROLES-PLAYED where the role-player slot matches
     */
    @LispMethod(comment = "@return a MAP with keys from LIST-OF-ROLES-TO-SEARCH and values as a list of elements\r\nfrom LIST-OF-ROLES-PLAYED where the role-player slot matches")
    public static SubLObject all_roles_matching(final SubLObject list_of_roles_played, final SubLObject list_of_roles_to_search) {
        final SubLObject roles_map = dictionary.new_dictionary(symbol_function(EQUAL), UNPROVIDED);
        SubLObject cdolist_list_var = list_of_roles_to_search;
        SubLObject role_to_search = NIL;
        role_to_search = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$10 = list_of_roles_played;
            SubLObject role_played_formula = NIL;
            role_played_formula = cdolist_list_var_$10.first();
            while (NIL != cdolist_list_var_$10) {
                if (role_played_formula.first().equalp(role_to_search)) {
                    dictionary_utilities.dictionary_push(roles_map, role_to_search, role_played_formula);
                }
                cdolist_list_var_$10 = cdolist_list_var_$10.rest();
                role_played_formula = cdolist_list_var_$10.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            role_to_search = cdolist_list_var.first();
        } 
        return roles_map;
    }

    public static SubLObject declare_script_instance_editor_api_file() {
        declareMacro("with_role_player_instance_level_relations", "WITH-ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS");
        declareMacro("with_sub_situation_instance_level_relations", "WITH-SUB-SITUATION-INSTANCE-LEVEL-RELATIONS");
        declareMacro("with_role_player_type_level_relations", "WITH-ROLE-PLAYER-TYPE-LEVEL-RELATIONS");
        declareFunction("get_script_graph_info", "GET-SCRIPT-GRAPH-INFO", 1, 1, false);
        declareFunction("get_role_player_equivalences_for_pred_and_script", "GET-ROLE-PLAYER-EQUIVALENCES-FOR-PRED-AND-SCRIPT", 2, 1, false);
        declareFunction("get_graph_for_script_instance", "GET-GRAPH-FOR-SCRIPT-INSTANCE", 1, 1, false);
        declareFunction("get_graph_for_script_instance_impl", "GET-GRAPH-FOR-SCRIPT-INSTANCE-IMPL", 2, 0, false);
        declareFunction("get_complete_graph_for_script_instance", "GET-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE", 1, 1, false);
        declareFunction("augment_complete_graph_for_script_instance", "AUGMENT-COMPLETE-GRAPH-FOR-SCRIPT-INSTANCE", 3, 0, false);
        declareFunction("get_graph_for_script_role_player_instance", "GET-GRAPH-FOR-SCRIPT-ROLE-PLAYER-INSTANCE", 2, 1, false);
        declareFunction("get_ancestor_nodes", "GET-ANCESTOR-NODES", 1, 1, false);
        declareFunction("get_role_player_equivalences", "GET-ROLE-PLAYER-EQUIVALENCES", 3, 1, false);
        declareFunction("all_roles_matching", "ALL-ROLES-MATCHING", 2, 0, false);
        return NIL;
    }

    public static SubLObject init_script_instance_editor_api_file() {
        defparameter("*SCRIPT-INSTANCE-EDITOR-MT*", $$InferencePSC);
        deflexical("*DEFAULT-SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*", $list2);
        defparameter("*SUB-SITUATION-INSTANCE-LEVEL-RELATIONS*", $default_sub_situation_instance_level_relations$.getGlobalValue());
        deflexical("*DEFAULT-ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*", $list3);
        defparameter("*ROLE-PLAYER-INSTANCE-LEVEL-RELATIONS*", $default_role_player_instance_level_relations$.getGlobalValue());
        deflexical("*DEFAULT-ROLE-PLAYER-TYPE-LEVEL-RELATIONS*", $list4);
        defparameter("*ROLE-PLAYER-TYPE-LEVEL-RELATIONS*", $default_role_player_type_level_relations$.getGlobalValue());
        return NIL;
    }

    public static SubLObject setup_script_instance_editor_api_file() {
        register_external_symbol($script_instance_editor_mt$);
        register_external_symbol(WITH_ROLE_PLAYER_INSTANCE_LEVEL_RELATIONS);
        register_external_symbol(WITH_SUB_SITUATION_INSTANCE_LEVEL_RELATIONS);
        register_external_symbol(WITH_ROLE_PLAYER_TYPE_LEVEL_RELATIONS);
        register_external_symbol(GET_SCRIPT_GRAPH_INFO);
        register_external_symbol(GET_ROLE_PLAYER_EQUIVALENCES_FOR_PRED_AND_SCRIPT);
        register_external_symbol(GET_GRAPH_FOR_SCRIPT_INSTANCE);
        register_external_symbol(GET_COMPLETE_GRAPH_FOR_SCRIPT_INSTANCE);
        register_external_symbol(AUGMENT_COMPLETE_GRAPH_FOR_SCRIPT_INSTANCE);
        register_external_symbol(GET_GRAPH_FOR_SCRIPT_ROLE_PLAYER_INSTANCE);
        register_external_symbol(GET_ANCESTOR_NODES);
        register_external_symbol(GET_ROLE_PLAYER_EQUIVALENCES);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_script_instance_editor_api_file();
    }

    @Override
    public void initializeVariables() {
        init_script_instance_editor_api_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_script_instance_editor_api_file();
    }

    static {
    }

    static private final SubLList $list_alt2 = list(reader_make_constant_shell("firstSubEvents"), reader_make_constant_shell("properSubEvents"), reader_make_constant_shell("lastSubEvents"));

    static private final SubLList $list_alt3 = list(new SubLObject[]{ reader_make_constant_shell("perpetrator"), reader_make_constant_shell("hostages"), reader_make_constant_shell("dateOfEvent"), reader_make_constant_shell("losesControl"), reader_make_constant_shell("objectOfControlTransfer"), reader_make_constant_shell("agreeingAgents"), reader_make_constant_shell("ransomDemanded"), reader_make_constant_shell("senderOfInfo"), reader_make_constant_shell("agreementCreated"), reader_make_constant_shell("crimeVictim"), reader_make_constant_shell("eventOccursAt") });

    static private final SubLList $list_alt4 = list(reader_make_constant_shell("equivRolePlayers"), reader_make_constant_shell("equivRolePlayersOfType"), reader_make_constant_shell("equivalentRolePlayers-SitTypeToSubSitType"), reader_make_constant_shell("equivalentRolePlayers-SitTypeToSubSitType-Unique"), reader_make_constant_shell("equivalentRolePlayers-SubSitTypeToSubSitType"), reader_make_constant_shell("equivalentRolePlayers-SubSitTypeToSubSitType-Unique"), reader_make_constant_shell("equivalentRolePlayersOfType-SubSitTypeToSubSitType"));

    static private final SubLList $list_alt6 = list(list(makeSymbol("RELATIONS")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt18 = list(makeKeyword("SUB-EVT-TYPE"));

    static private final SubLList $list_alt23 = list(makeSymbol("?SUB-SIT-TYPE-1"), makeSymbol("?SIT-ROLE-1"), makeSymbol("?SUB-SIT-TYPE-2"), makeSymbol("?SUB-SIT-ROLE-2"));

    static private final SubLList $list_alt25 = list(makeSymbol("?SIT-ROLE"), makeSymbol("?SUB-SIT-TYPE"), makeSymbol("?SUB-SIT-ROLE"));

    static private final SubLString $str_alt28$_A_is_not_an_individual_ = makeString("~A is not an individual.");

    static private final SubLList $list_alt44 = list(makeKeyword("EVT"));

    static private final SubLList $list_alt53 = list(makeSymbol("?SUB"), makeSymbol("?SUB1"), makeSymbol("?SUB2"), makeSymbol("?SUB-MT"), makeSymbol("?SUB2-MT"));

    static private final SubLList $list_alt55 = list(list(reader_make_constant_shell("join-left"), list(reader_make_constant_shell("ist-Asserted"), makeSymbol("?SUB-MT"), list(reader_make_constant_shell("properSubEvents"), makeSymbol("?SUB"), makeSymbol("?SUB1"))), list(reader_make_constant_shell("ist-Asserted"), makeSymbol("?SUB2-MT"), list(reader_make_constant_shell("properSubEvents"), makeSymbol("?SUB1"), makeSymbol("?SUB2")))));

    static private final SubLList $list_alt56 = list(makeSymbol("ARG-1"), makeSymbol("ARG-2"), makeSymbol("ITEM"), makeSymbol("MT"), makeSymbol("MT2"));
}

/**
 * Total time: 359 ms
 */
