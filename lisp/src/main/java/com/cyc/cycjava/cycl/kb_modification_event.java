/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.control_vars.cyc_image_id;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eql;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.make_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.release_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.seize_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.getf;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      KB-MODIFICATION-EVENT
 * source file: /cyc/top/cycl/kb-modification-event.lisp
 * created:     2019/07/03 17:37:21
 */
public final class kb_modification_event extends SubLTranslatedFile implements V12 {
    public static final SubLObject subloop_reserved_initialize_event_class_categories_match_instance(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, RESULT, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, TEST_METHOD, NIL);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_event_class_categories_match_class(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, MODULE, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, CATEGORIES, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, SUITES, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, TEST_METHODS, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, ENABLED, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, LOCK, NIL);
        return NIL;
    }

    public static final SubLObject event_class_categories_match_p(SubLObject event_class_categories_match) {
        return classes.subloop_instanceof_class(event_class_categories_match, EVENT_CLASS_CATEGORIES_MATCH);
    }

    public static final SubLFile me = new kb_modification_event();

 public static final String myName = "com.cyc.cycjava.cycl.kb_modification_event";


    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $kb_modifications_event_dispatcher_lock$ = makeSymbol("*KB-MODIFICATIONS-EVENT-DISPATCHER-LOCK*");

    @LispMethod(comment = "Internal Constants")
    // Internal Constants
    private static final SubLSymbol $KB_MODIFICATION_EVENT = makeKeyword("KB-MODIFICATION-EVENT");

    private static final SubLSymbol $KB_STORE_EVENT = makeKeyword("KB-STORE-EVENT");

    private static final SubLSymbol $KB_MODIFY_CREATE_CONSTANT = makeKeyword("KB-MODIFY-CREATE-CONSTANT");

    static private final SubLList $list3 = list(makeKeyword("EXISTENCE"), makeKeyword("CREATION"), $FORT, makeKeyword("CONSTANT"));

    private static final SubLSymbol $KB_MODIFY_REMOVE_CONSTANT = makeKeyword("KB-MODIFY-REMOVE-CONSTANT");

    static private final SubLList $list5 = list(makeKeyword("EXISTENCE"), makeKeyword("REMOVAL"), $FORT, makeKeyword("CONSTANT"));

    private static final SubLSymbol $KB_MODIFY_RENAME_CONSTANT = makeKeyword("KB-MODIFY-RENAME-CONSTANT");

    static private final SubLList $list7 = list(makeKeyword("PROPERTY-CHANGE"), $FORT, makeKeyword("CONSTANT"));

    private static final SubLSymbol $KB_MODIFY_CREATE_NART = makeKeyword("KB-MODIFY-CREATE-NART");

    static private final SubLList $list9 = list(makeKeyword("EXISTENCE"), makeKeyword("CREATION"), $FORT, $NART);

    private static final SubLSymbol $KB_MODIFY_REMOVE_NART = makeKeyword("KB-MODIFY-REMOVE-NART");

    static private final SubLList $list11 = list(makeKeyword("EXISTENCE"), makeKeyword("REMOVAL"), $FORT, $NART);

    private static final SubLSymbol $KB_MODIFY_CREATE_ASSERTION = makeKeyword("KB-MODIFY-CREATE-ASSERTION");

    static private final SubLList $list13 = list(makeKeyword("EXISTENCE"), makeKeyword("CREATION"), makeKeyword("ASSERTION"));

    private static final SubLSymbol $KB_MODIFY_REMOVE_ASSERTION = makeKeyword("KB-MODIFY-REMOVE-ASSERTION");

    static private final SubLList $list15 = list(makeKeyword("EXISTENCE"), makeKeyword("REMOVAL"), makeKeyword("ASSERTION"));

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_DIRECTION = makeKeyword("KB-MODIFY-SET-ASSERTION-DIRECTION");

    static private final SubLList $list17 = list(makeKeyword("ASSERTION"), makeKeyword("PROPERTY-CHANGE"));

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_STRENGTH = makeKeyword("KB-MODIFY-SET-ASSERTION-STRENGTH");

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_TRUTH = makeKeyword("KB-MODIFY-SET-ASSERTION-TRUTH");

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES = makeKeyword("KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES");

    static private final SubLList $list21 = list(makeKeyword("ASSERTION"), makeKeyword("META-PROPERTY-CHANGE"));

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_ASSERTED_BY = makeKeyword("KB-MODIFY-SET-ASSERTION-ASSERTED-BY");

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY = makeKeyword("KB-MODIFY-SET-ASSERTION-ASSERTED-WHY");

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN = makeKeyword("KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN");

    private static final SubLSymbol $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND = makeKeyword("KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND");

    private static final SubLSymbol $KB_MODIFY_CREATE_ASSERTED_ARGUMENT = makeKeyword("KB-MODIFY-CREATE-ASSERTED-ARGUMENT");

    static private final SubLList $list27 = list(makeKeyword("ASSERTED-ARGUMENT"), makeKeyword("CREATION"), makeKeyword("EXISTENCE"));

    private static final SubLSymbol $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT = makeKeyword("KB-MODIFY-REMOVE-ASSERTED-ARGUMENT");

    static private final SubLList $list29 = list(makeKeyword("ASSERTED-ARGUMENT"), makeKeyword("REMOVAL"), makeKeyword("EXISTENCE"));

    private static final SubLSymbol $KB_MODIFY_CREATE_NEW_DEDUCTION = makeKeyword("KB-MODIFY-CREATE-NEW-DEDUCTION");

    static private final SubLList $list31 = list(makeKeyword("DEDUCTION"), makeKeyword("CREATION"), makeKeyword("EXISTENCE"));

    private static final SubLSymbol $KB_MODIFY_REMOVE_DEDUCTION = makeKeyword("KB-MODIFY-REMOVE-DEDUCTION");

    static private final SubLList $list33 = list(makeKeyword("DEDUCTION"), makeKeyword("REMOVAL"), makeKeyword("EXISTENCE"));

    private static final SubLSymbol $KB_MODIFY_SET_DEDUCTION_STRENGTH = makeKeyword("KB-MODIFY-SET-DEDUCTION-STRENGTH");

    static private final SubLList $list35 = list(makeKeyword("DEDUCTION"), makeKeyword("PROPERTY-CHANGE"));

    private static final SubLSymbol $KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT = makeKeyword("KB-MODIFY-CREATE-NEW-KB-HL-SUPPORT");

    static private final SubLList $list37 = list(makeKeyword("KB-HL-SUPPORT"), makeKeyword("CREATION"), makeKeyword("EXISTENCE"));

    private static final SubLSymbol $KB_MODIFY_REMOVE_KB_HL_SUPPORT = makeKeyword("KB-MODIFY-REMOVE-KB-HL-SUPPORT");

    private static final SubLList $list39 = list(makeKeyword("KB-HL-SUPPORT"), makeKeyword("REMOVAL"), makeKeyword("EXISTENCE"));

    private static final SubLSymbol $KB_MODIFY_IRRELEVANCE = makeKeyword("KB-MODIFY-IRRELEVANCE");

    private static final SubLList $list41 = list($TERM, makeKeyword("ASSERTION"));

    private static final SubLSymbol EVENT_P = makeSymbol("EVENT-P");

    private static final SubLString $str45$Event__S_is_of_type__S__not__S_ = makeString("Event ~S is of type ~S, not ~S.");

    private static final SubLSymbol $OLD_CONSTANT_NAME = makeKeyword("OLD-CONSTANT-NAME");

    private static final SubLSymbol $NEW_CONSTANT_NAME = makeKeyword("NEW-CONSTANT-NAME");

    private static final SubLSymbol $NART_HL_FORMULA = makeKeyword("NART-HL-FORMULA");



    private static final SubLSymbol $OLD_VAR_NAMES = makeKeyword("OLD-VAR-NAMES");

    private static final SubLSymbol $NEW_VAR_NAMES = makeKeyword("NEW-VAR-NAMES");

    private static final SubLSymbol $OLD_ASSERTED_BY = makeKeyword("OLD-ASSERTED-BY");

    private static final SubLSymbol $NEW_ASSERTED_BY = makeKeyword("NEW-ASSERTED-BY");

    private static final SubLSymbol $OLD_ASSERTED_WHEN = makeKeyword("OLD-ASSERTED-WHEN");

    private static final SubLSymbol $NEW_ASSERTED_WHEN = makeKeyword("NEW-ASSERTED-WHEN");

    private static final SubLSymbol $OLD_ASSERTED_WHY = makeKeyword("OLD-ASSERTED-WHY");

    private static final SubLSymbol $NEW_ASSERTED_WHY = makeKeyword("NEW-ASSERTED-WHY");

    private static final SubLSymbol $OLD_ASSERTED_SECOND = makeKeyword("OLD-ASSERTED-SECOND");

    private static final SubLSymbol $NEW_ASSERTED_SECOND = makeKeyword("NEW-ASSERTED-SECOND");

    private static final SubLSymbol $PRAGMATIC_SUPPORT_MTS = makeKeyword("PRAGMATIC-SUPPORT-MTS");

    private static final SubLSymbol $KB_HL_SUPPORT = makeKeyword("KB-HL-SUPPORT");

    private static final SubLString $str84$KB_MODIFICATIONS_EVENT_DISPATCHER = makeString("KB-MODIFICATIONS-EVENT-DISPATCHER Lock");

    private static final SubLSymbol $kb_modifications_event_dispatcher_listeners$ = makeSymbol("*KB-MODIFICATIONS-EVENT-DISPATCHER-LISTENERS*");

    private static final SubLString $$$KB_Modification_Event = makeString("KB Modification Event");

    private static final SubLString $$$KB_Modification_Event_Test_Suite = makeString("KB Modification Event Test Suite");

    // Definitions
    public static final SubLObject post_kb_modify_create_constant_event_alt(SubLObject name, SubLObject external_id) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_CONSTANT)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_CREATE_CONSTANT, list($NAME, kb_modification_event_support.get_stable_description(name), $EXTERNAL_ID, kb_modification_event_support.get_stable_description(external_id)));
        }
        return NIL;
    }

    // Definitions
    public static SubLObject post_kb_modify_create_constant_event(final SubLObject name, final SubLObject external_id) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_CONSTANT)) {
            return post_new_kb_modify_event($KB_MODIFY_CREATE_CONSTANT, list($NAME, kb_modification_event_support.get_stable_description(name), $EXTERNAL_ID, kb_modification_event_support.get_stable_description(external_id)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_create_constant_retrieve_external_id_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_CONSTANT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_CONSTANT);
            }
        }
        return getf(event_model.event_message(event), $EXTERNAL_ID, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_constant_retrieve_external_id(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_CONSTANT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_CONSTANT);
        }
        return getf(event_model.event_message(event), $EXTERNAL_ID, UNPROVIDED);
    }

    public static final SubLObject kb_modify_create_constant_retrieve_name_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_CONSTANT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_CONSTANT);
            }
        }
        return getf(event_model.event_message(event), $NAME, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_constant_retrieve_name(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_CONSTANT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_CONSTANT);
        }
        return getf(event_model.event_message(event), $NAME, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_rename_constant_event_alt(SubLObject constant, SubLObject old_name, SubLObject new_name) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_RENAME_CONSTANT)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_RENAME_CONSTANT, list($CONSTANT, kb_modification_event_support.get_stable_description(constant), $OLD_CONSTANT_NAME, kb_modification_event_support.get_stable_description(old_name), $NEW_CONSTANT_NAME, kb_modification_event_support.get_stable_description(new_name)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_rename_constant_event(final SubLObject constant, final SubLObject old_name, final SubLObject new_name) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_RENAME_CONSTANT)) {
            return post_new_kb_modify_event($KB_MODIFY_RENAME_CONSTANT, list($CONSTANT, kb_modification_event_support.get_stable_description(constant), $OLD_CONSTANT_NAME, kb_modification_event_support.get_stable_description(old_name), $NEW_CONSTANT_NAME, kb_modification_event_support.get_stable_description(new_name)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_rename_constant_retrieve_new_constant_name_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_RENAME_CONSTANT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_RENAME_CONSTANT);
            }
        }
        return getf(event_model.event_message(event), $NEW_CONSTANT_NAME, UNPROVIDED);
    }

    public static SubLObject kb_modify_rename_constant_retrieve_new_constant_name(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_RENAME_CONSTANT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_RENAME_CONSTANT);
        }
        return getf(event_model.event_message(event), $NEW_CONSTANT_NAME, UNPROVIDED);
    }

    public static final SubLObject kb_modify_rename_constant_retrieve_old_constant_name_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_RENAME_CONSTANT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_RENAME_CONSTANT);
            }
        }
        return getf(event_model.event_message(event), $OLD_CONSTANT_NAME, UNPROVIDED);
    }

    public static SubLObject kb_modify_rename_constant_retrieve_old_constant_name(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_RENAME_CONSTANT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_RENAME_CONSTANT);
        }
        return getf(event_model.event_message(event), $OLD_CONSTANT_NAME, UNPROVIDED);
    }

    public static final SubLObject kb_modify_rename_constant_retrieve_constant_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_RENAME_CONSTANT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_RENAME_CONSTANT);
            }
        }
        return getf(event_model.event_message(event), $CONSTANT, UNPROVIDED);
    }

    public static SubLObject kb_modify_rename_constant_retrieve_constant(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_RENAME_CONSTANT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_RENAME_CONSTANT);
        }
        return getf(event_model.event_message(event), $CONSTANT, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_remove_constant_event_alt(SubLObject constant) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_CONSTANT)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_REMOVE_CONSTANT, list($CONSTANT, kb_modification_event_support.get_stable_description(constant), $EXTERNAL_ID, kb_modification_event_support.get_stable_description(constants_high.constant_external_id(constant))));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_remove_constant_event(final SubLObject constant) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_CONSTANT)) {
            return post_new_kb_modify_event($KB_MODIFY_REMOVE_CONSTANT, list($CONSTANT, kb_modification_event_support.get_stable_description(constant), $EXTERNAL_ID, kb_modification_event_support.get_stable_description(constants_high.constant_external_id(constant))));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_remove_constant_retrieve_constant_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_CONSTANT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_CONSTANT);
            }
        }
        return getf(event_model.event_message(event), $CONSTANT, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_constant_retrieve_constant(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_CONSTANT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_CONSTANT);
        }
        return getf(event_model.event_message(event), $CONSTANT, UNPROVIDED);
    }

    public static final SubLObject kb_modify_remove_constant_retrieve_external_id_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_CONSTANT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_CONSTANT);
            }
        }
        return getf(event_model.event_message(event), $EXTERNAL_ID, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_constant_retrieve_external_id(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_CONSTANT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_CONSTANT);
        }
        return getf(event_model.event_message(event), $EXTERNAL_ID, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_create_nart_event_alt(SubLObject nart_hl_formula) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_NART)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_CREATE_NART, list($NART_HL_FORMULA, kb_modification_event_support.get_stable_description(nart_hl_formula)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_create_nart_event(final SubLObject nart_hl_formula) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_NART)) {
            return post_new_kb_modify_event($KB_MODIFY_CREATE_NART, list($NART_HL_FORMULA, kb_modification_event_support.get_stable_description(nart_hl_formula)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_create_nart_retrieve_nart_hl_formula_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_NART) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NART);
            }
        }
        return getf(event_model.event_message(event), $NART_HL_FORMULA, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_nart_retrieve_nart_hl_formula(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_NART) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NART);
        }
        return getf(event_model.event_message(event), $NART_HL_FORMULA, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_remove_nart_event_alt(SubLObject nart) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_NART)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_REMOVE_NART, list($NART_HL_FORMULA, kb_modification_event_support.get_stable_description(narts_high.nart_hl_formula(nart))));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_remove_nart_event(final SubLObject nart) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_NART)) {
            return post_new_kb_modify_event($KB_MODIFY_REMOVE_NART, list($NART_HL_FORMULA, kb_modification_event_support.get_stable_description(narts_high.nart_hl_formula(nart))));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_remove_nart_retrieve_nart_hl_formula_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_NART) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_NART);
            }
        }
        return getf(event_model.event_message(event), $NART_HL_FORMULA, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_nart_retrieve_nart_hl_formula(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_NART) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_NART);
        }
        return getf(event_model.event_message(event), $NART_HL_FORMULA, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_create_assertion_event_alt(SubLObject cnf, SubLObject mt) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_ASSERTION)) {
            if (NIL == com.cyc.cycjava.cycl.kb_modification_event.about_to_post_strictly_implementation_assertionP(cnf)) {
                return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_CREATE_ASSERTION, list($CNF, kb_modification_event_support.get_stable_description(cnf), $MT, kb_modification_event_support.get_stable_description(mt)));
            }
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_create_assertion_event(final SubLObject cnf, final SubLObject mt) {
        if ((NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_ASSERTION)) && (NIL == about_to_post_strictly_implementation_assertionP(cnf))) {
            return post_new_kb_modify_event($KB_MODIFY_CREATE_ASSERTION, list($CNF, kb_modification_event_support.get_stable_description(cnf), $MT, kb_modification_event_support.get_stable_description(mt)));
        }
        return NIL;
    }

    public static final SubLObject about_to_post_strictly_implementation_assertionP_alt(SubLObject cnf) {
        return com.cyc.cycjava.cycl.kb_modification_event.about_to_post_term_of_unit_assertionP(cnf);
    }

    public static SubLObject about_to_post_strictly_implementation_assertionP(final SubLObject cnf) {
        return about_to_post_term_of_unit_assertionP(cnf);
    }

    public static final SubLObject about_to_post_term_of_unit_assertionP_alt(SubLObject cnf) {
        if (NIL != clause_utilities.pos_atomic_cnf_p(cnf)) {
            {
                SubLObject pos_lit = clauses.pos_lits(cnf).first();
                SubLObject predicate = cycl_utilities.formula_arg0(pos_lit);
                return eq(predicate, $$termOfUnit);
            }
        }
        return NIL;
    }

    public static SubLObject about_to_post_term_of_unit_assertionP(final SubLObject cnf) {
        if (NIL != clause_utilities.pos_atomic_cnf_p(cnf)) {
            final SubLObject pos_lit = clauses.pos_lits(cnf).first();
            final SubLObject predicate = cycl_utilities.formula_arg0(pos_lit);
            return eql(predicate, $$termOfUnit);
        }
        return NIL;
    }

    public static final SubLObject kb_modify_create_assertion_retrieve_mt_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTION);
            }
        }
        return getf(event_model.event_message(event), $MT, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_assertion_retrieve_mt(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTION);
        }
        return getf(event_model.event_message(event), $MT, UNPROVIDED);
    }

    public static final SubLObject kb_modify_create_assertion_retrieve_cnf_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTION);
            }
        }
        return getf(event_model.event_message(event), $CNF, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_assertion_retrieve_cnf(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTION);
        }
        return getf(event_model.event_message(event), $CNF, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_remove_assertion_event(SubLObject assertion) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_ASSERTION)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_REMOVE_ASSERTION, list($SENTENCE, encapsulation.encapsulate(uncanonicalizer.assertion_el_formula(assertion)), $MT, kb_modification_event_support.get_stable_description(assertions_high.assertion_mt(assertion)), $GAF, kb_modification_event_support.get_stable_description(assertions_high.gaf_assertionP(assertion))));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_remove_assertion_event(final SubLObject assertion, SubLObject gaf_truth) {
        if (gaf_truth == UNPROVIDED) {
            gaf_truth = $TRUE;
        }
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_ASSERTION)) {
            return post_new_kb_modify_event($KB_MODIFY_REMOVE_ASSERTION, list($SENTENCE, encapsulation.encapsulate(cycl_utilities.possibly_negate(uncanonicalizer.assertion_el_formula(assertion), gaf_truth)), $MT, kb_modification_event_support.get_stable_description(assertions_high.assertion_mt(assertion)), $GAF, kb_modification_event_support.get_stable_description(assertions_high.gaf_assertionP(assertion))));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_remove_assertion_retrieve_gaf_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTION);
            }
        }
        return getf(event_model.event_message(event), $GAF, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_assertion_retrieve_gaf(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTION);
        }
        return getf(event_model.event_message(event), $GAF, UNPROVIDED);
    }

    public static final SubLObject kb_modify_remove_assertion_retrieve_mt_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTION);
            }
        }
        return getf(event_model.event_message(event), $MT, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_assertion_retrieve_mt(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTION);
        }
        return getf(event_model.event_message(event), $MT, UNPROVIDED);
    }

    public static final SubLObject kb_modify_remove_assertion_retrieve_sentence_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTION);
            }
        }
        return getf(event_model.event_message(event), $SENTENCE, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_assertion_retrieve_sentence(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTION);
        }
        return getf(event_model.event_message(event), $SENTENCE, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_direction_event_alt(SubLObject assertion, SubLObject old_direction, SubLObject new_direction) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_DIRECTION)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_DIRECTION, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_DIRECTION, kb_modification_event_support.get_stable_description(old_direction), $NEW_DIRECTION, kb_modification_event_support.get_stable_description(new_direction)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_direction_event(final SubLObject assertion, final SubLObject old_direction, final SubLObject new_direction) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_DIRECTION)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_DIRECTION, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_DIRECTION, kb_modification_event_support.get_stable_description(old_direction), $NEW_DIRECTION, kb_modification_event_support.get_stable_description(new_direction)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_direction_retrieve_new_direction_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_DIRECTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_DIRECTION);
            }
        }
        return getf(event_model.event_message(event), $NEW_DIRECTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_direction_retrieve_new_direction(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_DIRECTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_DIRECTION);
        }
        return getf(event_model.event_message(event), $NEW_DIRECTION, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_direction_retrieve_old_direction_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_DIRECTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_DIRECTION);
            }
        }
        return getf(event_model.event_message(event), $OLD_DIRECTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_direction_retrieve_old_direction(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_DIRECTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_DIRECTION);
        }
        return getf(event_model.event_message(event), $OLD_DIRECTION, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_direction_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_DIRECTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_DIRECTION);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_direction_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_DIRECTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_DIRECTION);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_truth_event_alt(SubLObject assertion, SubLObject old_truth, SubLObject new_truth) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_TRUTH)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_TRUTH, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_TRUTH, kb_modification_event_support.get_stable_description(old_truth), $NEW_TRUTH, kb_modification_event_support.get_stable_description(new_truth)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_truth_event(final SubLObject assertion, final SubLObject old_truth, final SubLObject new_truth) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_TRUTH)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_TRUTH, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_TRUTH, kb_modification_event_support.get_stable_description(old_truth), $NEW_TRUTH, kb_modification_event_support.get_stable_description(new_truth)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_truth_retrieve_new_truth_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_TRUTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_TRUTH);
            }
        }
        return getf(event_model.event_message(event), $NEW_TRUTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_truth_retrieve_new_truth(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_TRUTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_TRUTH);
        }
        return getf(event_model.event_message(event), $NEW_TRUTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_truth_retrieve_old_truth_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_TRUTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_TRUTH);
            }
        }
        return getf(event_model.event_message(event), $OLD_TRUTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_truth_retrieve_old_truth(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_TRUTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_TRUTH);
        }
        return getf(event_model.event_message(event), $OLD_TRUTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_truth_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_TRUTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_TRUTH);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_truth_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_TRUTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_TRUTH);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_strength_event_alt(SubLObject assertion, SubLObject old_strength, SubLObject new_strength) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_STRENGTH)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_STRENGTH, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_STRENGTH, kb_modification_event_support.get_stable_description(old_strength), $NEW_STRENGTH, kb_modification_event_support.get_stable_description(new_strength)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_strength_event(final SubLObject assertion, final SubLObject old_strength, final SubLObject new_strength) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_STRENGTH)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_STRENGTH, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_STRENGTH, kb_modification_event_support.get_stable_description(old_strength), $NEW_STRENGTH, kb_modification_event_support.get_stable_description(new_strength)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_strength_retrieve_new_strength_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_STRENGTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_STRENGTH);
            }
        }
        return getf(event_model.event_message(event), $NEW_STRENGTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_strength_retrieve_new_strength(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_STRENGTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_STRENGTH);
        }
        return getf(event_model.event_message(event), $NEW_STRENGTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_strength_retrieve_old_strength_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_STRENGTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_STRENGTH);
            }
        }
        return getf(event_model.event_message(event), $OLD_STRENGTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_strength_retrieve_old_strength(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_STRENGTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_STRENGTH);
        }
        return getf(event_model.event_message(event), $OLD_STRENGTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_strength_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_STRENGTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_STRENGTH);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_strength_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_STRENGTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_STRENGTH);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_variable_names_event_alt(SubLObject assertion, SubLObject old_var_names, SubLObject new_var_names) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_VAR_NAMES, kb_modification_event_support.get_stable_description(old_var_names), $NEW_VAR_NAMES, kb_modification_event_support.get_stable_description(new_var_names)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_variable_names_event(final SubLObject assertion, final SubLObject old_var_names, final SubLObject new_var_names) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_VAR_NAMES, kb_modification_event_support.get_stable_description(old_var_names), $NEW_VAR_NAMES, kb_modification_event_support.get_stable_description(new_var_names)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_variable_names_retrieve_new_var_names_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES);
            }
        }
        return getf(event_model.event_message(event), $NEW_VAR_NAMES, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_variable_names_retrieve_new_var_names(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES);
        }
        return getf(event_model.event_message(event), $NEW_VAR_NAMES, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_variable_names_retrieve_old_var_names_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES);
            }
        }
        return getf(event_model.event_message(event), $OLD_VAR_NAMES, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_variable_names_retrieve_old_var_names(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES);
        }
        return getf(event_model.event_message(event), $OLD_VAR_NAMES, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_variable_names_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_variable_names_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_asserted_by_event_alt(SubLObject assertion, SubLObject old_asserted_by, SubLObject new_asserted_by) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_BY)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_BY, kb_modification_event_support.get_stable_description(old_asserted_by), $NEW_ASSERTED_BY, kb_modification_event_support.get_stable_description(new_asserted_by)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_asserted_by_event(final SubLObject assertion, final SubLObject old_asserted_by, final SubLObject new_asserted_by) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_BY)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_BY, kb_modification_event_support.get_stable_description(old_asserted_by), $NEW_ASSERTED_BY, kb_modification_event_support.get_stable_description(new_asserted_by)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_asserted_by_retrieve_new_asserted_by_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_BY) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_BY);
            }
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_BY, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_by_retrieve_new_asserted_by(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_BY) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_BY);
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_BY, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_by_retrieve_old_asserted_by_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_BY) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_BY);
            }
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_BY, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_by_retrieve_old_asserted_by(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_BY) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_BY);
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_BY, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_by_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_BY) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_BY);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_by_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_BY) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_BY);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_asserted_when_event_alt(SubLObject assertion, SubLObject old_asserted_when, SubLObject new_asserted_when) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_WHEN, kb_modification_event_support.get_stable_description(old_asserted_when), $NEW_ASSERTED_WHEN, kb_modification_event_support.get_stable_description(new_asserted_when)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_asserted_when_event(final SubLObject assertion, final SubLObject old_asserted_when, final SubLObject new_asserted_when) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_WHEN, kb_modification_event_support.get_stable_description(old_asserted_when), $NEW_ASSERTED_WHEN, kb_modification_event_support.get_stable_description(new_asserted_when)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_asserted_when_retrieve_new_asserted_when_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN);
            }
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_WHEN, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_when_retrieve_new_asserted_when(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN);
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_WHEN, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_when_retrieve_old_asserted_when_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN);
            }
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_WHEN, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_when_retrieve_old_asserted_when(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN);
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_WHEN, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_when_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_when_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_asserted_why_event_alt(SubLObject assertion, SubLObject old_asserted_why, SubLObject new_asserted_why) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_WHY, kb_modification_event_support.get_stable_description(old_asserted_why), $NEW_ASSERTED_WHY, kb_modification_event_support.get_stable_description(new_asserted_why)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_asserted_why_event(final SubLObject assertion, final SubLObject old_asserted_why, final SubLObject new_asserted_why) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_WHY, kb_modification_event_support.get_stable_description(old_asserted_why), $NEW_ASSERTED_WHY, kb_modification_event_support.get_stable_description(new_asserted_why)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_asserted_why_retrieve_new_asserted_why_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY);
            }
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_WHY, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_why_retrieve_new_asserted_why(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY);
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_WHY, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_why_retrieve_old_asserted_why_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY);
            }
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_WHY, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_why_retrieve_old_asserted_why(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY);
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_WHY, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_why_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_why_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_WHY);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_assertion_asserted_second_event_alt(SubLObject assertion, SubLObject old_asserted_second, SubLObject new_asserted_second) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_SECOND, kb_modification_event_support.get_stable_description(old_asserted_second), $NEW_ASSERTED_SECOND, kb_modification_event_support.get_stable_description(new_asserted_second)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_assertion_asserted_second_event(final SubLObject assertion, final SubLObject old_asserted_second, final SubLObject new_asserted_second) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $OLD_ASSERTED_SECOND, kb_modification_event_support.get_stable_description(old_asserted_second), $NEW_ASSERTED_SECOND, kb_modification_event_support.get_stable_description(new_asserted_second)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_assertion_asserted_second_retrieve_new_asserted_second_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND);
            }
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_SECOND, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_second_retrieve_new_asserted_second(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND);
        }
        return getf(event_model.event_message(event), $NEW_ASSERTED_SECOND, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_second_retrieve_old_asserted_second_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND);
            }
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_SECOND, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_second_retrieve_old_asserted_second(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND);
        }
        return getf(event_model.event_message(event), $OLD_ASSERTED_SECOND, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_assertion_asserted_second_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_assertion_asserted_second_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_create_asserted_argument_alt(SubLObject assertion, SubLObject truth, SubLObject strength) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_ASSERTED_ARGUMENT)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $TRUTH, kb_modification_event_support.get_stable_description(truth), $STRENGTH, kb_modification_event_support.get_stable_description(strength)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_create_asserted_argument(final SubLObject assertion, final SubLObject truth, final SubLObject strength) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_ASSERTED_ARGUMENT)) {
            return post_new_kb_modify_event($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $TRUTH, kb_modification_event_support.get_stable_description(truth), $STRENGTH, kb_modification_event_support.get_stable_description(strength)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_create_asserted_argument_retrieve_strength_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTED_ARGUMENT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTED_ARGUMENT);
            }
        }
        return getf(event_model.event_message(event), $STRENGTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_asserted_argument_retrieve_strength(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTED_ARGUMENT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTED_ARGUMENT);
        }
        return getf(event_model.event_message(event), $STRENGTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_create_asserted_argument_retrieve_truth_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTED_ARGUMENT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTED_ARGUMENT);
            }
        }
        return getf(event_model.event_message(event), $TRUTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_asserted_argument_retrieve_truth(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTED_ARGUMENT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTED_ARGUMENT);
        }
        return getf(event_model.event_message(event), $TRUTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_create_asserted_argument_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTED_ARGUMENT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTED_ARGUMENT);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_asserted_argument_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_ASSERTED_ARGUMENT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_ASSERTED_ARGUMENT);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_remove_asserted_argument_alt(SubLObject assertion, SubLObject asserted_argument) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $ASSERTED_ARGUMENT, kb_modification_event_support.get_stable_description(asserted_argument)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_remove_asserted_argument(final SubLObject assertion, final SubLObject asserted_argument) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT)) {
            return post_new_kb_modify_event($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $ASSERTED_ARGUMENT, kb_modification_event_support.get_stable_description(asserted_argument)));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_remove_asserted_argument_retrieve_asserted_argument_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT);
            }
        }
        return getf(event_model.event_message(event), $ASSERTED_ARGUMENT, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_asserted_argument_retrieve_asserted_argument(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT);
        }
        return getf(event_model.event_message(event), $ASSERTED_ARGUMENT, UNPROVIDED);
    }

    public static final SubLObject kb_modify_remove_asserted_argument_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_asserted_argument_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_ASSERTED_ARGUMENT);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_create_new_deduction_event(SubLObject assertion, SubLObject supports, SubLObject truth) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_NEW_DEDUCTION)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_CREATE_NEW_DEDUCTION, list($ASSERTION, kb_modification_event_support.get_stable_description(assertion), $SUPPORTS, kb_modification_event_support.get_stable_description(supports), $TRUTH, kb_modification_event_support.get_stable_description(truth)));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_create_new_deduction_event(final SubLObject assertion, final SubLObject supports, final SubLObject truth, final SubLObject strength, SubLObject v_bindings, SubLObject pragmatic_support_mts) {
        if (v_bindings == UNPROVIDED) {
            v_bindings = NIL;
        }
        if (pragmatic_support_mts == UNPROVIDED) {
            pragmatic_support_mts = NIL;
        }
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_NEW_DEDUCTION)) {
            return post_new_kb_modify_event($KB_MODIFY_CREATE_NEW_DEDUCTION, list(new SubLObject[]{ $ASSERTION, kb_modification_event_support.get_stable_description(assertion), $SUPPORTS, kb_modification_event_support.get_stable_description(supports), $TRUTH, kb_modification_event_support.get_stable_description(truth), $STRENGTH, kb_modification_event_support.get_stable_description(strength), $BINDINGS, kb_modification_event_support.get_stable_description(v_bindings), $PRAGMATIC_SUPPORT_MTS, kb_modification_event_support.get_stable_description(pragmatic_support_mts) }));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_create_new_deduction_retrieve_truth_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_DEDUCTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_DEDUCTION);
            }
        }
        return getf(event_model.event_message(event), $TRUTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_new_deduction_retrieve_truth(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_DEDUCTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_DEDUCTION);
        }
        return getf(event_model.event_message(event), $TRUTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_create_new_deduction_retrieve_supports_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_DEDUCTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_DEDUCTION);
            }
        }
        return getf(event_model.event_message(event), $SUPPORTS, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_new_deduction_retrieve_supports(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_DEDUCTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_DEDUCTION);
        }
        return getf(event_model.event_message(event), $SUPPORTS, UNPROVIDED);
    }

    public static final SubLObject kb_modify_create_new_deduction_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_DEDUCTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_DEDUCTION);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_new_deduction_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_DEDUCTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_DEDUCTION);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_remove_deduction_event_alt(SubLObject deduction) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_DEDUCTION)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_REMOVE_DEDUCTION, list($DEDUCTION, deduction));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_remove_deduction_event(final SubLObject deduction) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_DEDUCTION)) {
            return post_new_kb_modify_event($KB_MODIFY_REMOVE_DEDUCTION, list($DEDUCTION, deduction));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_remove_deduction_retrieve_deduction_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_REMOVE_DEDUCTION) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_DEDUCTION);
            }
        }
        return getf(event_model.event_message(event), $DEDUCTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_remove_deduction_retrieve_deduction(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_DEDUCTION) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_DEDUCTION);
        }
        return getf(event_model.event_message(event), $DEDUCTION, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_set_deduction_strength_event_alt(SubLObject deduction, SubLObject old_strength, SubLObject new_strength) {
        if (NIL != com.cyc.cycjava.cycl.kb_modification_event.anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_DEDUCTION_STRENGTH)) {
            return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_SET_DEDUCTION_STRENGTH, list($DEDUCTION, deduction, $OLD_STRENGTH, old_strength, $NEW_STRENGTH, new_strength));
        }
        return NIL;
    }

    public static SubLObject post_kb_modify_set_deduction_strength_event(final SubLObject deduction, final SubLObject old_strength, final SubLObject new_strength) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_SET_DEDUCTION_STRENGTH)) {
            return post_new_kb_modify_event($KB_MODIFY_SET_DEDUCTION_STRENGTH, list($DEDUCTION, deduction, $OLD_STRENGTH, old_strength, $NEW_STRENGTH, new_strength));
        }
        return NIL;
    }

    public static final SubLObject kb_modify_set_deduction_strength_retrieve_new_strength_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_DEDUCTION_STRENGTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_DEDUCTION_STRENGTH);
            }
        }
        return getf(event_model.event_message(event), $NEW_STRENGTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_deduction_strength_retrieve_new_strength(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_DEDUCTION_STRENGTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_DEDUCTION_STRENGTH);
        }
        return getf(event_model.event_message(event), $NEW_STRENGTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_deduction_strength_retrieve_old_strength_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_DEDUCTION_STRENGTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_DEDUCTION_STRENGTH);
            }
        }
        return getf(event_model.event_message(event), $OLD_STRENGTH, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_deduction_strength_retrieve_old_strength(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_DEDUCTION_STRENGTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_DEDUCTION_STRENGTH);
        }
        return getf(event_model.event_message(event), $OLD_STRENGTH, UNPROVIDED);
    }

    public static final SubLObject kb_modify_set_deduction_strength_retrieve_deduction_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_SET_DEDUCTION_STRENGTH) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_DEDUCTION_STRENGTH);
            }
        }
        return getf(event_model.event_message(event), $DEDUCTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_set_deduction_strength_retrieve_deduction(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_SET_DEDUCTION_STRENGTH) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_SET_DEDUCTION_STRENGTH);
        }
        return getf(event_model.event_message(event), $DEDUCTION, UNPROVIDED);
    }

    public static SubLObject post_kb_modify_create_new_kb_hl_support_event(final SubLObject hl_support, final SubLObject justification) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT)) {
            return post_new_kb_modify_event($KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT, list($HL_SUPPORT, kb_modification_event_support.get_stable_description(hl_support), $JUSTIFICATION, kb_modification_event_support.get_stable_description(justification)));
        }
        return NIL;
    }

    public static SubLObject kb_modify_create_new_kb_hl_support_retrieve_justification(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT);
        }
        return getf(event_model.event_message(event), $JUSTIFICATION, UNPROVIDED);
    }

    public static SubLObject kb_modify_create_new_kb_hl_support_retrieve_hl_support(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT);
        }
        return getf(event_model.event_message(event), $HL_SUPPORT, UNPROVIDED);
    }

    public static SubLObject post_kb_modify_remove_kb_hl_support_event(final SubLObject kb_hl_support) {
        if (NIL != anyone_interested_in_kb_modification_eventP($KB_MODIFY_REMOVE_KB_HL_SUPPORT)) {
            return post_new_kb_modify_event($KB_MODIFY_REMOVE_KB_HL_SUPPORT, list($KB_HL_SUPPORT, kb_modification_event_support.get_stable_description(kb_hl_support)));
        }
        return NIL;
    }

    public static SubLObject kb_modify_remove_kb_hl_support_retrieve_kb_hl_support(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_REMOVE_KB_HL_SUPPORT) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_REMOVE_KB_HL_SUPPORT);
        }
        return getf(event_model.event_message(event), $KB_HL_SUPPORT, UNPROVIDED);
    }

    public static final SubLObject post_kb_modify_irrelevance_event_alt(SubLObject v_term, SubLObject assertion) {
        return com.cyc.cycjava.cycl.kb_modification_event.post_new_kb_modify_event($KB_MODIFY_IRRELEVANCE, list($TERM, kb_modification_event_support.get_stable_description(v_term), $ASSERTION, kb_modification_event_support.get_stable_description(assertion)));
    }

    public static SubLObject post_kb_modify_irrelevance_event(final SubLObject v_term, final SubLObject assertion) {
        return post_new_kb_modify_event($KB_MODIFY_IRRELEVANCE, list($TERM, kb_modification_event_support.get_stable_description(v_term), $ASSERTION, kb_modification_event_support.get_stable_description(assertion)));
    }

    public static final SubLObject kb_modify_irrelevance_retrieve_assertion_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_IRRELEVANCE) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_IRRELEVANCE);
            }
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static SubLObject kb_modify_irrelevance_retrieve_assertion(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_IRRELEVANCE) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_IRRELEVANCE);
        }
        return getf(event_model.event_message(event), $ASSERTION, UNPROVIDED);
    }

    public static final SubLObject kb_modify_irrelevance_retrieve_term_alt(SubLObject event) {
        SubLTrampolineFile.checkType(event, EVENT_P);
        if (event_model.event_class(event) != $KB_MODIFY_IRRELEVANCE) {
            if (NIL == event_utilities.event_is_handler_registration_eventP(event)) {
                Errors.error($str_alt41$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_IRRELEVANCE);
            }
        }
        return getf(event_model.event_message(event), $TERM, UNPROVIDED);
    }

    public static SubLObject kb_modify_irrelevance_retrieve_term(final SubLObject event) {
        assert NIL != event_model.event_p(event) : "! event_model.event_p(event) " + ("event_model.event_p(event) " + "CommonSymbols.NIL != event_model.event_p(event) ") + event;
        if ((event_model.event_class(event) != $KB_MODIFY_IRRELEVANCE) && (NIL == event_utilities.event_is_handler_registration_eventP(event))) {
            Errors.error($str45$Event__S_is_of_type__S__not__S_, event, event_model.event_class(event), $KB_MODIFY_IRRELEVANCE);
        }
        return getf(event_model.event_message(event), $TERM, UNPROVIDED);
    }

    public static final SubLObject post_new_kb_modify_event_alt(SubLObject event_class, SubLObject v_arguments) {
        return com.cyc.cycjava.cycl.kb_modification_event.post_kb_modify_event(com.cyc.cycjava.cycl.kb_modification_event.new_kb_modify_event(event_class, v_arguments));
    }

    public static SubLObject post_new_kb_modify_event(final SubLObject event_class, final SubLObject v_arguments) {
        return post_kb_modify_event(new_kb_modify_event(event_class, v_arguments));
    }

    public static final SubLObject new_kb_modify_event_alt(SubLObject event_class, SubLObject v_arguments) {
        return event_model.new_event(event_class, v_arguments, cyc_image_id());
    }

    public static SubLObject new_kb_modify_event(final SubLObject event_class, final SubLObject v_arguments) {
        return event_model.new_event(event_class, v_arguments, cyc_image_id());
    }

    public static final SubLObject post_kb_modify_event_alt(SubLObject event) {
        return event_broker.post_event(event, com.cyc.cycjava.cycl.kb_modification_event.current_kb_modification_event_broker());
    }

    public static SubLObject post_kb_modify_event(final SubLObject event) {
        return event_broker.post_event(event, current_kb_modification_event_broker());
    }

    public static final SubLObject current_kb_modification_event_broker_alt() {
        return event_broker.core_event_broker();
    }

    public static SubLObject current_kb_modification_event_broker() {
        return event_broker.core_event_broker();
    }

    public static final SubLObject anyone_interested_in_kb_modification_eventP_alt(SubLObject event_class) {
        return event_broker.fast_has_event_class_any_transitive_listenersP(event_class, com.cyc.cycjava.cycl.kb_modification_event.current_kb_modification_event_broker());
    }

    public static SubLObject anyone_interested_in_kb_modification_eventP(final SubLObject event_class) {
        return event_broker.fast_has_event_class_any_transitive_listenersP(event_class, current_kb_modification_event_broker());
    }

    public static final SubLObject kb_modifications_event_dispatcher_runningP_alt() {
        return event_utilities.generic_event_dispatcher_process_running_for_application_p($kb_modifications_event_dispatcher_listeners$);
    }

    public static SubLObject kb_modifications_event_dispatcher_runningP() {
        return event_utilities.generic_event_dispatcher_process_running_for_application_p($kb_modifications_event_dispatcher_listeners$);
    }

    public static final SubLObject ensure_kb_modifications_event_dispatcher_running_alt() {
        if (NIL == com.cyc.cycjava.cycl.kb_modification_event.kb_modifications_event_dispatcher_runningP()) {
            com.cyc.cycjava.cycl.kb_modification_event.start_kb_modifications_event_dispatcher();
        }
        return event_utilities.generic_event_dispatcher_process();
    }

    public static SubLObject ensure_kb_modifications_event_dispatcher_running() {
        if (NIL == kb_modifications_event_dispatcher_runningP()) {
            start_kb_modifications_event_dispatcher();
        }
        return event_utilities.generic_event_dispatcher_process();
    }

    public static final SubLObject register_listener_with_kb_modifications_event_dispatcher_alt(SubLObject listener_var) {
        {
            SubLObject lock = $kb_modifications_event_dispatcher_lock$.getGlobalValue();
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                if (NIL == find(listener_var, $kb_modifications_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                    $kb_modifications_event_dispatcher_listeners$.setGlobalValue(cons(listener_var, $kb_modifications_event_dispatcher_listeners$.getGlobalValue()));
                    if (NIL != com.cyc.cycjava.cycl.kb_modification_event.kb_modifications_event_dispatcher_runningP()) {
                        event_broker.register_event_listener(listener_var, NIL);
                    }
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return listener_var;
    }

    public static SubLObject register_listener_with_kb_modifications_event_dispatcher(final SubLObject listener_var) {
        SubLObject release = NIL;
        try {
            release = seize_lock($kb_modifications_event_dispatcher_lock$.getGlobalValue());
            if (NIL == find(listener_var, $kb_modifications_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED)) {
                $kb_modifications_event_dispatcher_listeners$.setGlobalValue(cons(listener_var, $kb_modifications_event_dispatcher_listeners$.getGlobalValue()));
                if (NIL != kb_modifications_event_dispatcher_runningP()) {
                    event_broker.register_event_listener(listener_var, NIL);
                }
            }
        } finally {
            if (NIL != release) {
                release_lock($kb_modifications_event_dispatcher_lock$.getGlobalValue());
            }
        }
        return listener_var;
    }

    public static final SubLObject deregister_listener_with_kb_modifications_event_dispatcher_alt(SubLObject listener_var) {
        {
            SubLObject lock = $kb_modifications_event_dispatcher_lock$.getGlobalValue();
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                $kb_modifications_event_dispatcher_listeners$.setGlobalValue(remove(listener_var, $kb_modifications_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                if (NIL != com.cyc.cycjava.cycl.kb_modification_event.kb_modifications_event_dispatcher_runningP()) {
                    event_broker.deregister_event_listener(listener_var, NIL);
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return listener_var;
    }

    public static SubLObject deregister_listener_with_kb_modifications_event_dispatcher(final SubLObject listener_var) {
        SubLObject release = NIL;
        try {
            release = seize_lock($kb_modifications_event_dispatcher_lock$.getGlobalValue());
            $kb_modifications_event_dispatcher_listeners$.setGlobalValue(remove(listener_var, $kb_modifications_event_dispatcher_listeners$.getGlobalValue(), EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            if (NIL != kb_modifications_event_dispatcher_runningP()) {
                event_broker.deregister_event_listener(listener_var, NIL);
            }
        } finally {
            if (NIL != release) {
                release_lock($kb_modifications_event_dispatcher_lock$.getGlobalValue());
            }
        }
        return listener_var;
    }

    public static final SubLObject stop_kb_modifications_event_dispatcher_alt() {
        return event_utilities.stop_generic_event_dispatcher_process_for_application($kb_modifications_event_dispatcher_listeners$);
    }

    public static SubLObject stop_kb_modifications_event_dispatcher() {
        return event_utilities.stop_generic_event_dispatcher_process_for_application($kb_modifications_event_dispatcher_listeners$);
    }

    public static final SubLObject start_kb_modifications_event_dispatcher_alt() {
        return event_utilities.start_generic_event_dispatcher_process_for_application($kb_modifications_event_dispatcher_listeners$);
    }

    public static SubLObject start_kb_modifications_event_dispatcher() {
        return event_utilities.start_generic_event_dispatcher_process_for_application($kb_modifications_event_dispatcher_listeners$);
    }

    public static final SubLObject declare_kb_modification_event_file_alt() {
        declareFunction("post_kb_modify_create_constant_event", "POST-KB-MODIFY-CREATE-CONSTANT-EVENT", 2, 0, false);
        declareFunction("kb_modify_create_constant_retrieve_external_id", "KB-MODIFY-CREATE-CONSTANT-RETRIEVE-EXTERNAL-ID", 1, 0, false);
        declareFunction("kb_modify_create_constant_retrieve_name", "KB-MODIFY-CREATE-CONSTANT-RETRIEVE-NAME", 1, 0, false);
        declareFunction("post_kb_modify_rename_constant_event", "POST-KB-MODIFY-RENAME-CONSTANT-EVENT", 3, 0, false);
        declareFunction("kb_modify_rename_constant_retrieve_new_constant_name", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-NEW-CONSTANT-NAME", 1, 0, false);
        declareFunction("kb_modify_rename_constant_retrieve_old_constant_name", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-OLD-CONSTANT-NAME", 1, 0, false);
        declareFunction("kb_modify_rename_constant_retrieve_constant", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-CONSTANT", 1, 0, false);
        declareFunction("post_kb_modify_remove_constant_event", "POST-KB-MODIFY-REMOVE-CONSTANT-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_constant_retrieve_constant", "KB-MODIFY-REMOVE-CONSTANT-RETRIEVE-CONSTANT", 1, 0, false);
        declareFunction("kb_modify_remove_constant_retrieve_external_id", "KB-MODIFY-REMOVE-CONSTANT-RETRIEVE-EXTERNAL-ID", 1, 0, false);
        declareFunction("post_kb_modify_create_nart_event", "POST-KB-MODIFY-CREATE-NART-EVENT", 1, 0, false);
        declareFunction("kb_modify_create_nart_retrieve_nart_hl_formula", "KB-MODIFY-CREATE-NART-RETRIEVE-NART-HL-FORMULA", 1, 0, false);
        declareFunction("post_kb_modify_remove_nart_event", "POST-KB-MODIFY-REMOVE-NART-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_nart_retrieve_nart_hl_formula", "KB-MODIFY-REMOVE-NART-RETRIEVE-NART-HL-FORMULA", 1, 0, false);
        declareFunction("post_kb_modify_create_assertion_event", "POST-KB-MODIFY-CREATE-ASSERTION-EVENT", 2, 0, false);
        declareFunction("about_to_post_strictly_implementation_assertionP", "ABOUT-TO-POST-STRICTLY-IMPLEMENTATION-ASSERTION?", 1, 0, false);
        declareFunction("about_to_post_term_of_unit_assertionP", "ABOUT-TO-POST-TERM-OF-UNIT-ASSERTION?", 1, 0, false);
        declareFunction("kb_modify_create_assertion_retrieve_mt", "KB-MODIFY-CREATE-ASSERTION-RETRIEVE-MT", 1, 0, false);
        declareFunction("kb_modify_create_assertion_retrieve_cnf", "KB-MODIFY-CREATE-ASSERTION-RETRIEVE-CNF", 1, 0, false);
        declareFunction("post_kb_modify_remove_assertion_event", "POST-KB-MODIFY-REMOVE-ASSERTION-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_assertion_retrieve_gaf", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-GAF", 1, 0, false);
        declareFunction("kb_modify_remove_assertion_retrieve_mt", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-MT", 1, 0, false);
        declareFunction("kb_modify_remove_assertion_retrieve_sentence", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-SENTENCE", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_direction_event", "POST-KB-MODIFY-SET-ASSERTION-DIRECTION-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_direction_retrieve_new_direction", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-NEW-DIRECTION", 1, 0, false);
        declareFunction("kb_modify_set_assertion_direction_retrieve_old_direction", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-OLD-DIRECTION", 1, 0, false);
        declareFunction("kb_modify_set_assertion_direction_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_truth_event", "POST-KB-MODIFY-SET-ASSERTION-TRUTH-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_truth_retrieve_new_truth", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-NEW-TRUTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_truth_retrieve_old_truth", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-OLD-TRUTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_truth_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_strength_event", "POST-KB-MODIFY-SET-ASSERTION-STRENGTH-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_strength_retrieve_new_strength", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-NEW-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_strength_retrieve_old_strength", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-OLD-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_strength_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_variable_names_event", "POST-KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_variable_names_retrieve_new_var_names", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-NEW-VAR-NAMES", 1, 0, false);
        declareFunction("kb_modify_set_assertion_variable_names_retrieve_old_var_names", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-OLD-VAR-NAMES", 1, 0, false);
        declareFunction("kb_modify_set_assertion_variable_names_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_by_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-BY-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_by_retrieve_new_asserted_by", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-NEW-ASSERTED-BY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_by_retrieve_old_asserted_by", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-OLD-ASSERTED-BY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_by_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_when_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_when_retrieve_new_asserted_when", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-NEW-ASSERTED-WHEN", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_when_retrieve_old_asserted_when", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-OLD-ASSERTED-WHEN", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_when_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_why_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_why_retrieve_new_asserted_why", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-NEW-ASSERTED-WHY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_why_retrieve_old_asserted_why", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-OLD-ASSERTED-WHY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_why_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_second_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_second_retrieve_new_asserted_second", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-NEW-ASSERTED-SECOND", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_second_retrieve_old_asserted_second", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-OLD-ASSERTED-SECOND", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_second_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_create_asserted_argument", "POST-KB-MODIFY-CREATE-ASSERTED-ARGUMENT", 3, 0, false);
        declareFunction("kb_modify_create_asserted_argument_retrieve_strength", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_create_asserted_argument_retrieve_truth", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-TRUTH", 1, 0, false);
        declareFunction("kb_modify_create_asserted_argument_retrieve_assertion", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_remove_asserted_argument", "POST-KB-MODIFY-REMOVE-ASSERTED-ARGUMENT", 2, 0, false);
        declareFunction("kb_modify_remove_asserted_argument_retrieve_asserted_argument", "KB-MODIFY-REMOVE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTED-ARGUMENT", 1, 0, false);
        declareFunction("kb_modify_remove_asserted_argument_retrieve_assertion", "KB-MODIFY-REMOVE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_create_new_deduction_event", "POST-KB-MODIFY-CREATE-NEW-DEDUCTION-EVENT", 3, 0, false);
        declareFunction("kb_modify_create_new_deduction_retrieve_truth", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-TRUTH", 1, 0, false);
        declareFunction("kb_modify_create_new_deduction_retrieve_supports", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-SUPPORTS", 1, 0, false);
        declareFunction("kb_modify_create_new_deduction_retrieve_assertion", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_remove_deduction_event", "POST-KB-MODIFY-REMOVE-DEDUCTION-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_deduction_retrieve_deduction", "KB-MODIFY-REMOVE-DEDUCTION-RETRIEVE-DEDUCTION", 1, 0, false);
        declareFunction("post_kb_modify_set_deduction_strength_event", "POST-KB-MODIFY-SET-DEDUCTION-STRENGTH-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_deduction_strength_retrieve_new_strength", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-NEW-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_deduction_strength_retrieve_old_strength", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-OLD-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_deduction_strength_retrieve_deduction", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-DEDUCTION", 1, 0, false);
        declareFunction("post_kb_modify_irrelevance_event", "POST-KB-MODIFY-IRRELEVANCE-EVENT", 2, 0, false);
        declareFunction("kb_modify_irrelevance_retrieve_assertion", "KB-MODIFY-IRRELEVANCE-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("kb_modify_irrelevance_retrieve_term", "KB-MODIFY-IRRELEVANCE-RETRIEVE-TERM", 1, 0, false);
        declareFunction("post_new_kb_modify_event", "POST-NEW-KB-MODIFY-EVENT", 2, 0, false);
        declareFunction("new_kb_modify_event", "NEW-KB-MODIFY-EVENT", 2, 0, false);
        declareFunction("post_kb_modify_event", "POST-KB-MODIFY-EVENT", 1, 0, false);
        declareFunction("current_kb_modification_event_broker", "CURRENT-KB-MODIFICATION-EVENT-BROKER", 0, 0, false);
        declareFunction("anyone_interested_in_kb_modification_eventP", "ANYONE-INTERESTED-IN-KB-MODIFICATION-EVENT?", 1, 0, false);
        declareFunction("kb_modifications_event_dispatcher_runningP", "KB-MODIFICATIONS-EVENT-DISPATCHER-RUNNING?", 0, 0, false);
        declareFunction("ensure_kb_modifications_event_dispatcher_running", "ENSURE-KB-MODIFICATIONS-EVENT-DISPATCHER-RUNNING", 0, 0, false);
        declareFunction("register_listener_with_kb_modifications_event_dispatcher", "REGISTER-LISTENER-WITH-KB-MODIFICATIONS-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("deregister_listener_with_kb_modifications_event_dispatcher", "DEREGISTER-LISTENER-WITH-KB-MODIFICATIONS-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("stop_kb_modifications_event_dispatcher", "STOP-KB-MODIFICATIONS-EVENT-DISPATCHER", 0, 0, false);
        declareFunction("start_kb_modifications_event_dispatcher", "START-KB-MODIFICATIONS-EVENT-DISPATCHER", 0, 0, false);
        declareFunction("subloop_reserved_initialize_event_class_categories_match_class", "SUBLOOP-RESERVED-INITIALIZE-EVENT-CLASS-CATEGORIES-MATCH-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_event_class_categories_match_instance", "SUBLOOP-RESERVED-INITIALIZE-EVENT-CLASS-CATEGORIES-MATCH-INSTANCE", 1, 0, false);
        declareFunction("event_class_categories_match_p", "EVENT-CLASS-CATEGORIES-MATCH-P", 1, 0, false);
        return NIL;
    }

    public static SubLObject declare_kb_modification_event_file() {
        if (SubLFiles.USE_V1) {
            declareFunction("post_kb_modify_create_constant_event", "POST-KB-MODIFY-CREATE-CONSTANT-EVENT", 2, 0, false);
            declareFunction("kb_modify_create_constant_retrieve_external_id", "KB-MODIFY-CREATE-CONSTANT-RETRIEVE-EXTERNAL-ID", 1, 0, false);
            declareFunction("kb_modify_create_constant_retrieve_name", "KB-MODIFY-CREATE-CONSTANT-RETRIEVE-NAME", 1, 0, false);
            declareFunction("post_kb_modify_rename_constant_event", "POST-KB-MODIFY-RENAME-CONSTANT-EVENT", 3, 0, false);
            declareFunction("kb_modify_rename_constant_retrieve_new_constant_name", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-NEW-CONSTANT-NAME", 1, 0, false);
            declareFunction("kb_modify_rename_constant_retrieve_old_constant_name", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-OLD-CONSTANT-NAME", 1, 0, false);
            declareFunction("kb_modify_rename_constant_retrieve_constant", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-CONSTANT", 1, 0, false);
            declareFunction("post_kb_modify_remove_constant_event", "POST-KB-MODIFY-REMOVE-CONSTANT-EVENT", 1, 0, false);
            declareFunction("kb_modify_remove_constant_retrieve_constant", "KB-MODIFY-REMOVE-CONSTANT-RETRIEVE-CONSTANT", 1, 0, false);
            declareFunction("kb_modify_remove_constant_retrieve_external_id", "KB-MODIFY-REMOVE-CONSTANT-RETRIEVE-EXTERNAL-ID", 1, 0, false);
            declareFunction("post_kb_modify_create_nart_event", "POST-KB-MODIFY-CREATE-NART-EVENT", 1, 0, false);
            declareFunction("kb_modify_create_nart_retrieve_nart_hl_formula", "KB-MODIFY-CREATE-NART-RETRIEVE-NART-HL-FORMULA", 1, 0, false);
            declareFunction("post_kb_modify_remove_nart_event", "POST-KB-MODIFY-REMOVE-NART-EVENT", 1, 0, false);
            declareFunction("kb_modify_remove_nart_retrieve_nart_hl_formula", "KB-MODIFY-REMOVE-NART-RETRIEVE-NART-HL-FORMULA", 1, 0, false);
            declareFunction("post_kb_modify_create_assertion_event", "POST-KB-MODIFY-CREATE-ASSERTION-EVENT", 2, 0, false);
            declareFunction("about_to_post_strictly_implementation_assertionP", "ABOUT-TO-POST-STRICTLY-IMPLEMENTATION-ASSERTION?", 1, 0, false);
            declareFunction("about_to_post_term_of_unit_assertionP", "ABOUT-TO-POST-TERM-OF-UNIT-ASSERTION?", 1, 0, false);
            declareFunction("kb_modify_create_assertion_retrieve_mt", "KB-MODIFY-CREATE-ASSERTION-RETRIEVE-MT", 1, 0, false);
            declareFunction("kb_modify_create_assertion_retrieve_cnf", "KB-MODIFY-CREATE-ASSERTION-RETRIEVE-CNF", 1, 0, false);
            declareFunction("post_kb_modify_remove_assertion_event", "POST-KB-MODIFY-REMOVE-ASSERTION-EVENT", 1, 1, false);
            declareFunction("kb_modify_remove_assertion_retrieve_gaf", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-GAF", 1, 0, false);
            declareFunction("kb_modify_remove_assertion_retrieve_mt", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-MT", 1, 0, false);
            declareFunction("kb_modify_remove_assertion_retrieve_sentence", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-SENTENCE", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_direction_event", "POST-KB-MODIFY-SET-ASSERTION-DIRECTION-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_direction_retrieve_new_direction", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-NEW-DIRECTION", 1, 0, false);
            declareFunction("kb_modify_set_assertion_direction_retrieve_old_direction", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-OLD-DIRECTION", 1, 0, false);
            declareFunction("kb_modify_set_assertion_direction_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_truth_event", "POST-KB-MODIFY-SET-ASSERTION-TRUTH-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_truth_retrieve_new_truth", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-NEW-TRUTH", 1, 0, false);
            declareFunction("kb_modify_set_assertion_truth_retrieve_old_truth", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-OLD-TRUTH", 1, 0, false);
            declareFunction("kb_modify_set_assertion_truth_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_strength_event", "POST-KB-MODIFY-SET-ASSERTION-STRENGTH-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_strength_retrieve_new_strength", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-NEW-STRENGTH", 1, 0, false);
            declareFunction("kb_modify_set_assertion_strength_retrieve_old_strength", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-OLD-STRENGTH", 1, 0, false);
            declareFunction("kb_modify_set_assertion_strength_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_variable_names_event", "POST-KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_variable_names_retrieve_new_var_names", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-NEW-VAR-NAMES", 1, 0, false);
            declareFunction("kb_modify_set_assertion_variable_names_retrieve_old_var_names", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-OLD-VAR-NAMES", 1, 0, false);
            declareFunction("kb_modify_set_assertion_variable_names_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_asserted_by_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-BY-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_by_retrieve_new_asserted_by", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-NEW-ASSERTED-BY", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_by_retrieve_old_asserted_by", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-OLD-ASSERTED-BY", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_by_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_asserted_when_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_when_retrieve_new_asserted_when", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-NEW-ASSERTED-WHEN", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_when_retrieve_old_asserted_when", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-OLD-ASSERTED-WHEN", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_when_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_asserted_why_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_why_retrieve_new_asserted_why", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-NEW-ASSERTED-WHY", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_why_retrieve_old_asserted_why", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-OLD-ASSERTED-WHY", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_why_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_set_assertion_asserted_second_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_second_retrieve_new_asserted_second", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-NEW-ASSERTED-SECOND", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_second_retrieve_old_asserted_second", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-OLD-ASSERTED-SECOND", 1, 0, false);
            declareFunction("kb_modify_set_assertion_asserted_second_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_create_asserted_argument", "POST-KB-MODIFY-CREATE-ASSERTED-ARGUMENT", 3, 0, false);
            declareFunction("kb_modify_create_asserted_argument_retrieve_strength", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-STRENGTH", 1, 0, false);
            declareFunction("kb_modify_create_asserted_argument_retrieve_truth", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-TRUTH", 1, 0, false);
            declareFunction("kb_modify_create_asserted_argument_retrieve_assertion", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_remove_asserted_argument", "POST-KB-MODIFY-REMOVE-ASSERTED-ARGUMENT", 2, 0, false);
            declareFunction("kb_modify_remove_asserted_argument_retrieve_asserted_argument", "KB-MODIFY-REMOVE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTED-ARGUMENT", 1, 0, false);
            declareFunction("kb_modify_remove_asserted_argument_retrieve_assertion", "KB-MODIFY-REMOVE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_create_new_deduction_event", "POST-KB-MODIFY-CREATE-NEW-DEDUCTION-EVENT", 4, 2, false);
            declareFunction("kb_modify_create_new_deduction_retrieve_truth", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-TRUTH", 1, 0, false);
            declareFunction("kb_modify_create_new_deduction_retrieve_supports", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-SUPPORTS", 1, 0, false);
            declareFunction("kb_modify_create_new_deduction_retrieve_assertion", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("post_kb_modify_remove_deduction_event", "POST-KB-MODIFY-REMOVE-DEDUCTION-EVENT", 1, 0, false);
            declareFunction("kb_modify_remove_deduction_retrieve_deduction", "KB-MODIFY-REMOVE-DEDUCTION-RETRIEVE-DEDUCTION", 1, 0, false);
            declareFunction("post_kb_modify_set_deduction_strength_event", "POST-KB-MODIFY-SET-DEDUCTION-STRENGTH-EVENT", 3, 0, false);
            declareFunction("kb_modify_set_deduction_strength_retrieve_new_strength", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-NEW-STRENGTH", 1, 0, false);
            declareFunction("kb_modify_set_deduction_strength_retrieve_old_strength", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-OLD-STRENGTH", 1, 0, false);
            declareFunction("kb_modify_set_deduction_strength_retrieve_deduction", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-DEDUCTION", 1, 0, false);
            declareFunction("post_kb_modify_create_new_kb_hl_support_event", "POST-KB-MODIFY-CREATE-NEW-KB-HL-SUPPORT-EVENT", 2, 0, false);
            declareFunction("kb_modify_create_new_kb_hl_support_retrieve_justification", "KB-MODIFY-CREATE-NEW-KB-HL-SUPPORT-RETRIEVE-JUSTIFICATION", 1, 0, false);
            declareFunction("kb_modify_create_new_kb_hl_support_retrieve_hl_support", "KB-MODIFY-CREATE-NEW-KB-HL-SUPPORT-RETRIEVE-HL-SUPPORT", 1, 0, false);
            declareFunction("post_kb_modify_remove_kb_hl_support_event", "POST-KB-MODIFY-REMOVE-KB-HL-SUPPORT-EVENT", 1, 0, false);
            declareFunction("kb_modify_remove_kb_hl_support_retrieve_kb_hl_support", "KB-MODIFY-REMOVE-KB-HL-SUPPORT-RETRIEVE-KB-HL-SUPPORT", 1, 0, false);
            declareFunction("post_kb_modify_irrelevance_event", "POST-KB-MODIFY-IRRELEVANCE-EVENT", 2, 0, false);
            declareFunction("kb_modify_irrelevance_retrieve_assertion", "KB-MODIFY-IRRELEVANCE-RETRIEVE-ASSERTION", 1, 0, false);
            declareFunction("kb_modify_irrelevance_retrieve_term", "KB-MODIFY-IRRELEVANCE-RETRIEVE-TERM", 1, 0, false);
            declareFunction("post_new_kb_modify_event", "POST-NEW-KB-MODIFY-EVENT", 2, 0, false);
            declareFunction("new_kb_modify_event", "NEW-KB-MODIFY-EVENT", 2, 0, false);
            declareFunction("post_kb_modify_event", "POST-KB-MODIFY-EVENT", 1, 0, false);
            declareFunction("current_kb_modification_event_broker", "CURRENT-KB-MODIFICATION-EVENT-BROKER", 0, 0, false);
            declareFunction("anyone_interested_in_kb_modification_eventP", "ANYONE-INTERESTED-IN-KB-MODIFICATION-EVENT?", 1, 0, false);
            declareFunction("kb_modifications_event_dispatcher_runningP", "KB-MODIFICATIONS-EVENT-DISPATCHER-RUNNING?", 0, 0, false);
            declareFunction("ensure_kb_modifications_event_dispatcher_running", "ENSURE-KB-MODIFICATIONS-EVENT-DISPATCHER-RUNNING", 0, 0, false);
            declareFunction("register_listener_with_kb_modifications_event_dispatcher", "REGISTER-LISTENER-WITH-KB-MODIFICATIONS-EVENT-DISPATCHER", 1, 0, false);
            declareFunction("deregister_listener_with_kb_modifications_event_dispatcher", "DEREGISTER-LISTENER-WITH-KB-MODIFICATIONS-EVENT-DISPATCHER", 1, 0, false);
            declareFunction("stop_kb_modifications_event_dispatcher", "STOP-KB-MODIFICATIONS-EVENT-DISPATCHER", 0, 0, false);
            declareFunction("start_kb_modifications_event_dispatcher", "START-KB-MODIFICATIONS-EVENT-DISPATCHER", 0, 0, false);
        }
        if (SubLFiles.USE_V2) {
            declareFunction("post_kb_modify_remove_assertion_event", "POST-KB-MODIFY-REMOVE-ASSERTION-EVENT", 1, 0, false);
            declareFunction("post_kb_modify_create_new_deduction_event", "POST-KB-MODIFY-CREATE-NEW-DEDUCTION-EVENT", 3, 0, false);
            declareFunction("subloop_reserved_initialize_event_class_categories_match_class", "SUBLOOP-RESERVED-INITIALIZE-EVENT-CLASS-CATEGORIES-MATCH-CLASS", 1, 0, false);
            declareFunction("subloop_reserved_initialize_event_class_categories_match_instance", "SUBLOOP-RESERVED-INITIALIZE-EVENT-CLASS-CATEGORIES-MATCH-INSTANCE", 1, 0, false);
            declareFunction("event_class_categories_match_p", "EVENT-CLASS-CATEGORIES-MATCH-P", 1, 0, false);
        }
        return NIL;
    }

    public static SubLObject declare_kb_modification_event_file_Previous() {
        declareFunction("post_kb_modify_create_constant_event", "POST-KB-MODIFY-CREATE-CONSTANT-EVENT", 2, 0, false);
        declareFunction("kb_modify_create_constant_retrieve_external_id", "KB-MODIFY-CREATE-CONSTANT-RETRIEVE-EXTERNAL-ID", 1, 0, false);
        declareFunction("kb_modify_create_constant_retrieve_name", "KB-MODIFY-CREATE-CONSTANT-RETRIEVE-NAME", 1, 0, false);
        declareFunction("post_kb_modify_rename_constant_event", "POST-KB-MODIFY-RENAME-CONSTANT-EVENT", 3, 0, false);
        declareFunction("kb_modify_rename_constant_retrieve_new_constant_name", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-NEW-CONSTANT-NAME", 1, 0, false);
        declareFunction("kb_modify_rename_constant_retrieve_old_constant_name", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-OLD-CONSTANT-NAME", 1, 0, false);
        declareFunction("kb_modify_rename_constant_retrieve_constant", "KB-MODIFY-RENAME-CONSTANT-RETRIEVE-CONSTANT", 1, 0, false);
        declareFunction("post_kb_modify_remove_constant_event", "POST-KB-MODIFY-REMOVE-CONSTANT-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_constant_retrieve_constant", "KB-MODIFY-REMOVE-CONSTANT-RETRIEVE-CONSTANT", 1, 0, false);
        declareFunction("kb_modify_remove_constant_retrieve_external_id", "KB-MODIFY-REMOVE-CONSTANT-RETRIEVE-EXTERNAL-ID", 1, 0, false);
        declareFunction("post_kb_modify_create_nart_event", "POST-KB-MODIFY-CREATE-NART-EVENT", 1, 0, false);
        declareFunction("kb_modify_create_nart_retrieve_nart_hl_formula", "KB-MODIFY-CREATE-NART-RETRIEVE-NART-HL-FORMULA", 1, 0, false);
        declareFunction("post_kb_modify_remove_nart_event", "POST-KB-MODIFY-REMOVE-NART-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_nart_retrieve_nart_hl_formula", "KB-MODIFY-REMOVE-NART-RETRIEVE-NART-HL-FORMULA", 1, 0, false);
        declareFunction("post_kb_modify_create_assertion_event", "POST-KB-MODIFY-CREATE-ASSERTION-EVENT", 2, 0, false);
        declareFunction("about_to_post_strictly_implementation_assertionP", "ABOUT-TO-POST-STRICTLY-IMPLEMENTATION-ASSERTION?", 1, 0, false);
        declareFunction("about_to_post_term_of_unit_assertionP", "ABOUT-TO-POST-TERM-OF-UNIT-ASSERTION?", 1, 0, false);
        declareFunction("kb_modify_create_assertion_retrieve_mt", "KB-MODIFY-CREATE-ASSERTION-RETRIEVE-MT", 1, 0, false);
        declareFunction("kb_modify_create_assertion_retrieve_cnf", "KB-MODIFY-CREATE-ASSERTION-RETRIEVE-CNF", 1, 0, false);
        declareFunction("post_kb_modify_remove_assertion_event", "POST-KB-MODIFY-REMOVE-ASSERTION-EVENT", 1, 1, false);
        declareFunction("kb_modify_remove_assertion_retrieve_gaf", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-GAF", 1, 0, false);
        declareFunction("kb_modify_remove_assertion_retrieve_mt", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-MT", 1, 0, false);
        declareFunction("kb_modify_remove_assertion_retrieve_sentence", "KB-MODIFY-REMOVE-ASSERTION-RETRIEVE-SENTENCE", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_direction_event", "POST-KB-MODIFY-SET-ASSERTION-DIRECTION-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_direction_retrieve_new_direction", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-NEW-DIRECTION", 1, 0, false);
        declareFunction("kb_modify_set_assertion_direction_retrieve_old_direction", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-OLD-DIRECTION", 1, 0, false);
        declareFunction("kb_modify_set_assertion_direction_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-DIRECTION-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_truth_event", "POST-KB-MODIFY-SET-ASSERTION-TRUTH-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_truth_retrieve_new_truth", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-NEW-TRUTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_truth_retrieve_old_truth", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-OLD-TRUTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_truth_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-TRUTH-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_strength_event", "POST-KB-MODIFY-SET-ASSERTION-STRENGTH-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_strength_retrieve_new_strength", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-NEW-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_strength_retrieve_old_strength", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-OLD-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_assertion_strength_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-STRENGTH-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_variable_names_event", "POST-KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_variable_names_retrieve_new_var_names", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-NEW-VAR-NAMES", 1, 0, false);
        declareFunction("kb_modify_set_assertion_variable_names_retrieve_old_var_names", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-OLD-VAR-NAMES", 1, 0, false);
        declareFunction("kb_modify_set_assertion_variable_names_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-VARIABLE-NAMES-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_by_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-BY-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_by_retrieve_new_asserted_by", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-NEW-ASSERTED-BY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_by_retrieve_old_asserted_by", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-OLD-ASSERTED-BY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_by_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-BY-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_when_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_when_retrieve_new_asserted_when", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-NEW-ASSERTED-WHEN", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_when_retrieve_old_asserted_when", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-OLD-ASSERTED-WHEN", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_when_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHEN-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_why_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_why_retrieve_new_asserted_why", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-NEW-ASSERTED-WHY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_why_retrieve_old_asserted_why", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-OLD-ASSERTED-WHY", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_why_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-WHY-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_set_assertion_asserted_second_event", "POST-KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_second_retrieve_new_asserted_second", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-NEW-ASSERTED-SECOND", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_second_retrieve_old_asserted_second", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-OLD-ASSERTED-SECOND", 1, 0, false);
        declareFunction("kb_modify_set_assertion_asserted_second_retrieve_assertion", "KB-MODIFY-SET-ASSERTION-ASSERTED-SECOND-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_create_asserted_argument", "POST-KB-MODIFY-CREATE-ASSERTED-ARGUMENT", 3, 0, false);
        declareFunction("kb_modify_create_asserted_argument_retrieve_strength", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_create_asserted_argument_retrieve_truth", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-TRUTH", 1, 0, false);
        declareFunction("kb_modify_create_asserted_argument_retrieve_assertion", "KB-MODIFY-CREATE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_remove_asserted_argument", "POST-KB-MODIFY-REMOVE-ASSERTED-ARGUMENT", 2, 0, false);
        declareFunction("kb_modify_remove_asserted_argument_retrieve_asserted_argument", "KB-MODIFY-REMOVE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTED-ARGUMENT", 1, 0, false);
        declareFunction("kb_modify_remove_asserted_argument_retrieve_assertion", "KB-MODIFY-REMOVE-ASSERTED-ARGUMENT-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_create_new_deduction_event", "POST-KB-MODIFY-CREATE-NEW-DEDUCTION-EVENT", 4, 2, false);
        declareFunction("kb_modify_create_new_deduction_retrieve_truth", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-TRUTH", 1, 0, false);
        declareFunction("kb_modify_create_new_deduction_retrieve_supports", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-SUPPORTS", 1, 0, false);
        declareFunction("kb_modify_create_new_deduction_retrieve_assertion", "KB-MODIFY-CREATE-NEW-DEDUCTION-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("post_kb_modify_remove_deduction_event", "POST-KB-MODIFY-REMOVE-DEDUCTION-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_deduction_retrieve_deduction", "KB-MODIFY-REMOVE-DEDUCTION-RETRIEVE-DEDUCTION", 1, 0, false);
        declareFunction("post_kb_modify_set_deduction_strength_event", "POST-KB-MODIFY-SET-DEDUCTION-STRENGTH-EVENT", 3, 0, false);
        declareFunction("kb_modify_set_deduction_strength_retrieve_new_strength", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-NEW-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_deduction_strength_retrieve_old_strength", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-OLD-STRENGTH", 1, 0, false);
        declareFunction("kb_modify_set_deduction_strength_retrieve_deduction", "KB-MODIFY-SET-DEDUCTION-STRENGTH-RETRIEVE-DEDUCTION", 1, 0, false);
        declareFunction("post_kb_modify_create_new_kb_hl_support_event", "POST-KB-MODIFY-CREATE-NEW-KB-HL-SUPPORT-EVENT", 2, 0, false);
        declareFunction("kb_modify_create_new_kb_hl_support_retrieve_justification", "KB-MODIFY-CREATE-NEW-KB-HL-SUPPORT-RETRIEVE-JUSTIFICATION", 1, 0, false);
        declareFunction("kb_modify_create_new_kb_hl_support_retrieve_hl_support", "KB-MODIFY-CREATE-NEW-KB-HL-SUPPORT-RETRIEVE-HL-SUPPORT", 1, 0, false);
        declareFunction("post_kb_modify_remove_kb_hl_support_event", "POST-KB-MODIFY-REMOVE-KB-HL-SUPPORT-EVENT", 1, 0, false);
        declareFunction("kb_modify_remove_kb_hl_support_retrieve_kb_hl_support", "KB-MODIFY-REMOVE-KB-HL-SUPPORT-RETRIEVE-KB-HL-SUPPORT", 1, 0, false);
        declareFunction("post_kb_modify_irrelevance_event", "POST-KB-MODIFY-IRRELEVANCE-EVENT", 2, 0, false);
        declareFunction("kb_modify_irrelevance_retrieve_assertion", "KB-MODIFY-IRRELEVANCE-RETRIEVE-ASSERTION", 1, 0, false);
        declareFunction("kb_modify_irrelevance_retrieve_term", "KB-MODIFY-IRRELEVANCE-RETRIEVE-TERM", 1, 0, false);
        declareFunction("post_new_kb_modify_event", "POST-NEW-KB-MODIFY-EVENT", 2, 0, false);
        declareFunction("new_kb_modify_event", "NEW-KB-MODIFY-EVENT", 2, 0, false);
        declareFunction("post_kb_modify_event", "POST-KB-MODIFY-EVENT", 1, 0, false);
        declareFunction("current_kb_modification_event_broker", "CURRENT-KB-MODIFICATION-EVENT-BROKER", 0, 0, false);
        declareFunction("anyone_interested_in_kb_modification_eventP", "ANYONE-INTERESTED-IN-KB-MODIFICATION-EVENT?", 1, 0, false);
        declareFunction("kb_modifications_event_dispatcher_runningP", "KB-MODIFICATIONS-EVENT-DISPATCHER-RUNNING?", 0, 0, false);
        declareFunction("ensure_kb_modifications_event_dispatcher_running", "ENSURE-KB-MODIFICATIONS-EVENT-DISPATCHER-RUNNING", 0, 0, false);
        declareFunction("register_listener_with_kb_modifications_event_dispatcher", "REGISTER-LISTENER-WITH-KB-MODIFICATIONS-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("deregister_listener_with_kb_modifications_event_dispatcher", "DEREGISTER-LISTENER-WITH-KB-MODIFICATIONS-EVENT-DISPATCHER", 1, 0, false);
        declareFunction("stop_kb_modifications_event_dispatcher", "STOP-KB-MODIFICATIONS-EVENT-DISPATCHER", 0, 0, false);
        declareFunction("start_kb_modifications_event_dispatcher", "START-KB-MODIFICATIONS-EVENT-DISPATCHER", 0, 0, false);
        return NIL;
    }

    static private final SubLList $list_alt3 = list(makeKeyword("EXISTENCE"), makeKeyword("CREATION"), $FORT, makeKeyword("CONSTANT"));

    static private final SubLList $list_alt5 = list(makeKeyword("EXISTENCE"), makeKeyword("REMOVAL"), $FORT, makeKeyword("CONSTANT"));

    static private final SubLList $list_alt7 = list(makeKeyword("PROPERTY-CHANGE"), $FORT, makeKeyword("CONSTANT"));

    static private final SubLList $list_alt9 = list(makeKeyword("EXISTENCE"), makeKeyword("CREATION"), $FORT, $NART);

    static private final SubLList $list_alt11 = list(makeKeyword("EXISTENCE"), makeKeyword("REMOVAL"), $FORT, $NART);

    static private final SubLList $list_alt13 = list(makeKeyword("EXISTENCE"), makeKeyword("CREATION"), makeKeyword("ASSERTION"));

    static private final SubLList $list_alt15 = list(makeKeyword("EXISTENCE"), makeKeyword("REMOVAL"), makeKeyword("ASSERTION"));

    static private final SubLList $list_alt17 = list(makeKeyword("ASSERTION"), makeKeyword("PROPERTY-CHANGE"));

    static private final SubLList $list_alt21 = list(makeKeyword("ASSERTION"), makeKeyword("META-PROPERTY-CHANGE"));

    static private final SubLList $list_alt27 = list(makeKeyword("ASSERTED-ARGUMENT"), makeKeyword("CREATION"), makeKeyword("EXISTENCE"));

    static private final SubLList $list_alt29 = list(makeKeyword("ASSERTED-ARGUMENT"), makeKeyword("REMOVAL"), makeKeyword("EXISTENCE"));

    static private final SubLList $list_alt31 = list(makeKeyword("DEDUCTION"), makeKeyword("CREATION"), makeKeyword("EXISTENCE"));

    static private final SubLList $list_alt33 = list(makeKeyword("DEDUCTION"), makeKeyword("REMOVAL"), makeKeyword("EXISTENCE"));

    static private final SubLList $list_alt35 = list(makeKeyword("DEDUCTION"), makeKeyword("PROPERTY-CHANGE"));

    static private final SubLList $list_alt37 = list($TERM, makeKeyword("ASSERTION"));

    static private final SubLString $str_alt41$Event__S_is_of_type__S__not__S_ = makeString("Event ~S is of type ~S, not ~S.");

    public static final SubLObject init_kb_modification_event_file_alt() {
        deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LOCK*", make_lock($str_alt74$KB_MODIFICATIONS_EVENT_DISPATCHER));
        deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LISTENERS*", NIL != boundp($kb_modifications_event_dispatcher_listeners$) ? ((SubLObject) ($kb_modifications_event_dispatcher_listeners$.getGlobalValue())) : NIL);
        return NIL;
    }

    public static SubLObject init_kb_modification_event_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LOCK*", make_lock($str84$KB_MODIFICATIONS_EVENT_DISPATCHER));
            deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LISTENERS*", SubLTrampolineFile.maybeDefault($kb_modifications_event_dispatcher_listeners$, $kb_modifications_event_dispatcher_listeners$, NIL));
        }
        if (SubLFiles.USE_V2) {
            deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LOCK*", make_lock($str_alt74$KB_MODIFICATIONS_EVENT_DISPATCHER));
            deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LISTENERS*", NIL != boundp($kb_modifications_event_dispatcher_listeners$) ? ((SubLObject) ($kb_modifications_event_dispatcher_listeners$.getGlobalValue())) : NIL);
        }
        return NIL;
    }

    public static SubLObject init_kb_modification_event_file_Previous() {
        deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LOCK*", make_lock($str84$KB_MODIFICATIONS_EVENT_DISPATCHER));
        deflexical("*KB-MODIFICATIONS-EVENT-DISPATCHER-LISTENERS*", SubLTrampolineFile.maybeDefault($kb_modifications_event_dispatcher_listeners$, $kb_modifications_event_dispatcher_listeners$, NIL));
        return NIL;
    }

    public static final SubLObject setup_kb_modification_event_file_alt() {
        event_model.register_event_class($KB_MODIFICATION_EVENT, $KB_STORE_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFICATION_EVENT, NIL);
        event_model.register_event_class($KB_MODIFY_CREATE_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_CONSTANT, $list_alt3);
        event_model.register_event_class($KB_MODIFY_REMOVE_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_CONSTANT, $list_alt5);
        event_model.register_event_class($KB_MODIFY_RENAME_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_RENAME_CONSTANT, $list_alt7);
        event_model.register_event_class($KB_MODIFY_CREATE_NART, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NART, $list_alt9);
        event_model.register_event_class($KB_MODIFY_REMOVE_NART, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_NART, $list_alt11);
        event_model.register_event_class($KB_MODIFY_CREATE_ASSERTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_ASSERTION, $list_alt13);
        event_model.register_event_class($KB_MODIFY_REMOVE_ASSERTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_ASSERTION, $list_alt15);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_DIRECTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_DIRECTION, $list_alt17);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_STRENGTH, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_STRENGTH, $list_alt17);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_TRUTH, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_TRUTH, $list_alt17);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, $list_alt21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, $list_alt21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, $list_alt21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, $list_alt21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, $list_alt21);
        event_model.register_event_class($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, $list_alt27);
        event_model.register_event_class($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, $list_alt29);
        event_model.register_event_class($KB_MODIFY_CREATE_NEW_DEDUCTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NEW_DEDUCTION, $list_alt31);
        event_model.register_event_class($KB_MODIFY_REMOVE_DEDUCTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_DEDUCTION, $list_alt33);
        event_model.register_event_class($KB_MODIFY_SET_DEDUCTION_STRENGTH, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_DEDUCTION_STRENGTH, $list_alt35);
        event_model.register_event_class($KB_MODIFY_IRRELEVANCE, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_IRRELEVANCE, $list_alt37);
        declare_defglobal($kb_modifications_event_dispatcher_listeners$);
        sunit_external.define_test_category($$$KB_Modification_Event, UNPROVIDED);
        sunit_macros.$current_module_sunit_tests$.setDynamicValue(cons(EVENT_CLASS_CATEGORIES_MATCH, sunit_macros.$current_module_sunit_tests$.getDynamicValue()));
        sunit_macros.define_test_case_preamble(EVENT_CLASS_CATEGORIES_MATCH);
        classes.subloop_new_class(EVENT_CLASS_CATEGORIES_MATCH, TEST_CASE, NIL, NIL, $list_alt79);
        classes.class_set_implements_slot_listeners(EVENT_CLASS_CATEGORIES_MATCH, NIL);
        classes.subloop_note_class_initialization_function(EVENT_CLASS_CATEGORIES_MATCH, SUBLOOP_RESERVED_INITIALIZE_EVENT_CLASS_CATEGORIES_MATCH_CLASS);
        classes.subloop_note_instance_initialization_function(EVENT_CLASS_CATEGORIES_MATCH, SUBLOOP_RESERVED_INITIALIZE_EVENT_CLASS_CATEGORIES_MATCH_INSTANCE);
        com.cyc.cycjava.cycl.kb_modification_event.subloop_reserved_initialize_event_class_categories_match_class(EVENT_CLASS_CATEGORIES_MATCH);
        sunit_macros.define_test_case_postamble(EVENT_CLASS_CATEGORIES_MATCH, $str_alt94$kb_modification_event, $$$cycl, $list_alt96);
        sunit_macros.def_test_method_register(EVENT_CLASS_CATEGORIES_MATCH, FIND_ALL);
        sunit_external.define_test_suite($$$KB_Modification_Event_Test_Suite, list($$$KB_Modification_Event), UNPROVIDED, UNPROVIDED);
        return NIL;
    }

    public static SubLObject setup_kb_modification_event_file() {
        if (SubLFiles.USE_V1) {
            event_model.register_event_class($KB_MODIFICATION_EVENT, $KB_STORE_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFICATION_EVENT, NIL);
            event_model.register_event_class($KB_MODIFY_CREATE_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_CONSTANT, $list3);
            event_model.register_event_class($KB_MODIFY_REMOVE_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_CONSTANT, $list5);
            event_model.register_event_class($KB_MODIFY_RENAME_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_RENAME_CONSTANT, $list7);
            event_model.register_event_class($KB_MODIFY_CREATE_NART, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NART, $list9);
            event_model.register_event_class($KB_MODIFY_REMOVE_NART, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_NART, $list11);
            event_model.register_event_class($KB_MODIFY_CREATE_ASSERTION, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_ASSERTION, $list13);
            event_model.register_event_class($KB_MODIFY_REMOVE_ASSERTION, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_ASSERTION, $list15);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_DIRECTION, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_DIRECTION, $list17);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_STRENGTH, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_STRENGTH, $list17);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_TRUTH, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_TRUTH, $list17);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, $list21);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, $list21);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, $list21);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, $list21);
            event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, $list21);
            event_model.register_event_class($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, $list27);
            event_model.register_event_class($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, $list29);
            event_model.register_event_class($KB_MODIFY_CREATE_NEW_DEDUCTION, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NEW_DEDUCTION, $list31);
            event_model.register_event_class($KB_MODIFY_REMOVE_DEDUCTION, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_DEDUCTION, $list33);
            event_model.register_event_class($KB_MODIFY_SET_DEDUCTION_STRENGTH, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_DEDUCTION_STRENGTH, $list35);
            event_model.register_event_class($KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT, $list37);
            event_model.register_event_class($KB_MODIFY_REMOVE_KB_HL_SUPPORT, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_KB_HL_SUPPORT, $list39);
            event_model.register_event_class($KB_MODIFY_IRRELEVANCE, $KB_MODIFICATION_EVENT, NIL);
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_IRRELEVANCE, $list41);
            declare_defglobal($kb_modifications_event_dispatcher_listeners$);
            sunit_external.define_test_category($$$KB_Modification_Event, UNPROVIDED);
            sunit_external.define_test_suite($$$KB_Modification_Event_Test_Suite, list($$$KB_Modification_Event), UNPROVIDED, UNPROVIDED);
        }
        if (SubLFiles.USE_V2) {
            kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_IRRELEVANCE, $list_alt37);
            sunit_macros.$current_module_sunit_tests$.setDynamicValue(cons(EVENT_CLASS_CATEGORIES_MATCH, sunit_macros.$current_module_sunit_tests$.getDynamicValue()));
            sunit_macros.define_test_case_preamble(EVENT_CLASS_CATEGORIES_MATCH);
            classes.subloop_new_class(EVENT_CLASS_CATEGORIES_MATCH, TEST_CASE, NIL, NIL, $list_alt79);
            classes.class_set_implements_slot_listeners(EVENT_CLASS_CATEGORIES_MATCH, NIL);
            classes.subloop_note_class_initialization_function(EVENT_CLASS_CATEGORIES_MATCH, SUBLOOP_RESERVED_INITIALIZE_EVENT_CLASS_CATEGORIES_MATCH_CLASS);
            classes.subloop_note_instance_initialization_function(EVENT_CLASS_CATEGORIES_MATCH, SUBLOOP_RESERVED_INITIALIZE_EVENT_CLASS_CATEGORIES_MATCH_INSTANCE);
            com.cyc.cycjava.cycl.kb_modification_event.subloop_reserved_initialize_event_class_categories_match_class(EVENT_CLASS_CATEGORIES_MATCH);
            sunit_macros.define_test_case_postamble(EVENT_CLASS_CATEGORIES_MATCH, $str_alt94$kb_modification_event, $$$cycl, $list_alt96);
            sunit_macros.def_test_method_register(EVENT_CLASS_CATEGORIES_MATCH, FIND_ALL);
        }
        return NIL;
    }

    public static SubLObject setup_kb_modification_event_file_Previous() {
        event_model.register_event_class($KB_MODIFICATION_EVENT, $KB_STORE_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFICATION_EVENT, NIL);
        event_model.register_event_class($KB_MODIFY_CREATE_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_CONSTANT, $list3);
        event_model.register_event_class($KB_MODIFY_REMOVE_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_CONSTANT, $list5);
        event_model.register_event_class($KB_MODIFY_RENAME_CONSTANT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_RENAME_CONSTANT, $list7);
        event_model.register_event_class($KB_MODIFY_CREATE_NART, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NART, $list9);
        event_model.register_event_class($KB_MODIFY_REMOVE_NART, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_NART, $list11);
        event_model.register_event_class($KB_MODIFY_CREATE_ASSERTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_ASSERTION, $list13);
        event_model.register_event_class($KB_MODIFY_REMOVE_ASSERTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_ASSERTION, $list15);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_DIRECTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_DIRECTION, $list17);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_STRENGTH, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_STRENGTH, $list17);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_TRUTH, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_TRUTH, $list17);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_VARIABLE_NAMES, $list21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_BY, $list21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_WHY, $list21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_WHEN, $list21);
        event_model.register_event_class($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_ASSERTION_ASSERTED_SECOND, $list21);
        event_model.register_event_class($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_ASSERTED_ARGUMENT, $list27);
        event_model.register_event_class($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_ASSERTED_ARGUMENT, $list29);
        event_model.register_event_class($KB_MODIFY_CREATE_NEW_DEDUCTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NEW_DEDUCTION, $list31);
        event_model.register_event_class($KB_MODIFY_REMOVE_DEDUCTION, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_DEDUCTION, $list33);
        event_model.register_event_class($KB_MODIFY_SET_DEDUCTION_STRENGTH, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_SET_DEDUCTION_STRENGTH, $list35);
        event_model.register_event_class($KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_CREATE_NEW_KB_HL_SUPPORT, $list37);
        event_model.register_event_class($KB_MODIFY_REMOVE_KB_HL_SUPPORT, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_REMOVE_KB_HL_SUPPORT, $list39);
        event_model.register_event_class($KB_MODIFY_IRRELEVANCE, $KB_MODIFICATION_EVENT, NIL);
        kb_modification_event_support.note_kb_modification_event_class_properties($KB_MODIFY_IRRELEVANCE, $list41);
        declare_defglobal($kb_modifications_event_dispatcher_listeners$);
        sunit_external.define_test_category($$$KB_Modification_Event, UNPROVIDED);
        sunit_external.define_test_suite($$$KB_Modification_Event_Test_Suite, list($$$KB_Modification_Event), UNPROVIDED, UNPROVIDED);
        return NIL;
    }

    static private final SubLString $str_alt74$KB_MODIFICATIONS_EVENT_DISPATCHER = makeString("KB-MODIFICATIONS-EVENT-DISPATCHER Lock");

    private static final SubLSymbol EVENT_CLASS_CATEGORIES_MATCH = makeSymbol("EVENT-CLASS-CATEGORIES-MATCH");

    static private final SubLList $list_alt79 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SETUP"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CLEANUP"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FIND-ALL"), NIL, makeKeyword("PROTECTED")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_EVENT_CLASS_CATEGORIES_MATCH_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-EVENT-CLASS-CATEGORIES-MATCH-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_EVENT_CLASS_CATEGORIES_MATCH_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-EVENT-CLASS-CATEGORIES-MATCH-INSTANCE");

    static private final SubLString $str_alt94$kb_modification_event = makeString("kb-modification-event");

    static private final SubLString $$$cycl = makeString("cycl");

    static private final SubLList $list_alt96 = list(makeString("KB Modification Event"));

    private static final SubLSymbol FIND_ALL = makeSymbol("FIND-ALL");

    @Override
    public void declareFunctions() {
        declare_kb_modification_event_file();
    }

    @Override
    public void initializeVariables() {
        init_kb_modification_event_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_kb_modification_event_file();
    }

    static {
    }
}

/**
 * Total time: 322 ms
 */
