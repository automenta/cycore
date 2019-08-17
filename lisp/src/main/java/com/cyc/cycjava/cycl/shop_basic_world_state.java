/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.el_utilities.groundP;
import static com.cyc.cycjava.cycl.el_utilities.literal_predicate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.apply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.subsetp;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class shop_basic_world_state extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new shop_basic_world_state();

 public static final String myName = "com.cyc.cycjava.cycl.shop_basic_world_state";


    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol SHOP_WORLD_STATE = makeSymbol("SHOP-WORLD-STATE");

    static private final SubLList $list1 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETE-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-LITERALS"), list(makeSymbol("LITERALS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETE-LITERALS"), list(makeSymbol("LITERALS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-ATOM"), list(makeSymbol("ATOM")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETE-ATOM"), list(makeSymbol("ATOM")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COPY-STATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NEXT-STATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-CLASS-METHOD"), makeSymbol("SAME-STATE-P"), list(makeSymbol("STATE1"), makeSymbol("STATE2")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")) });

    static private final SubLList $list4 = list(makeSymbol("SHOP-WORLD-STATE"));

    static private final SubLList $list5 = list(list(makeSymbol("POSITIVE-LITERALS"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("NEGATIVE-LITERALS"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("VAR-MANAGER"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK-GROUND-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK-IF-NOT-GROUND-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POSITIVE-MATCHES-TO-PREDICATE"), list(makeSymbol("PRED")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NEGATIVE-MATCHES-TO-PREDICATE"), list(makeSymbol("PRED")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK-WITH-MULTIBINDING"), list(makeSymbol("LITERAL"), makeSymbol("MBINDING")), makeKeyword("PUBLIC")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_WORLD_STATE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-WORLD-STATE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_WORLD_STATE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-WORLD-STATE-INSTANCE");

    static private final SubLList $list15 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list16 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(makeSymbol("CSETQ"), makeSymbol("POSITIVE-LITERALS"), list(makeSymbol("NEW-DICTIONARY"), list(makeSymbol("FUNCTION"), EQ), TEN_INTEGER)), list(makeSymbol("CSETQ"), makeSymbol("NEGATIVE-LITERALS"), list(makeSymbol("NEW-DICTIONARY"), list(makeSymbol("FUNCTION"), EQ), TEN_INTEGER)), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym17$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-WORLD-STATE-METHOD");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_INITIALIZE_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-INITIALIZE-METHOD");

    private static final SubLSymbol SAME_STATE_P = makeSymbol("SAME-STATE-P");

    static private final SubLList $list20 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list21 = list(makeSymbol("STATE1"), makeSymbol("STATE2"));

    static private final SubLList $list22 = list(list(makeSymbol("CLET"), list(list(makeSymbol("POS-LITS2"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE2"), list(QUOTE, makeSymbol("POSITIVE-LITERALS")))), list(makeSymbol("NEG-LITS2"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE2"), list(QUOTE, makeSymbol("NEGATIVE-LITERALS")))), list(makeSymbol("ANSWER"), T), list(makeSymbol("DONE?"), NIL)), list(makeSymbol("DO-DICTIONARY"), list(makeSymbol("KEY"), makeSymbol("VALUE1"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE1"), list(QUOTE, makeSymbol("POSITIVE-LITERALS"))), makeSymbol("DONE?")), list(makeSymbol("CLET"), list(list(makeSymbol("VALUE2"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("KEY"), makeSymbol("POS-LITS2")))), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("SUBSETP"), makeSymbol("VALUE1"), makeSymbol("VALUE2")), list(makeSymbol("SUBSETP"), makeSymbol("VALUE2"), makeSymbol("VALUE1"))), list(makeSymbol("CSETQ"), makeSymbol("DONE?"), T), list(makeSymbol("CSETQ"), makeSymbol("ANSWER"), NIL)))), list(makeSymbol("DO-DICTIONARY"), list(makeSymbol("KEY"), makeSymbol("VALUE1"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE1"), list(QUOTE, makeSymbol("NEGATIVE-LITERALS"))), makeSymbol("DONE?")), list(makeSymbol("CLET"), list(list(makeSymbol("VALUE2"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("KEY"), makeSymbol("NEG-LITS2")))), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("SUBSETP"), makeSymbol("VALUE1"), makeSymbol("VALUE2")), list(makeSymbol("SUBSETP"), makeSymbol("VALUE2"), makeSymbol("VALUE1"))), list(makeSymbol("CSETQ"), makeSymbol("DONE?"), T), list(makeSymbol("CSETQ"), makeSymbol("ANSWER"), NIL)))), list(RET, makeSymbol("ANSWER"))));

    static private final SubLSymbol $sym23$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-WORLD-STATE-METHOD");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_SAME_STATE_P_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-SAME-STATE-P-METHOD");

    private static final SubLSymbol ADD_ATOM = makeSymbol("ADD-ATOM");

    static private final SubLList $list26 = list(makeSymbol("LITERAL"));

    static private final SubLList $list27 = list(makeString("@param LITERAL consp\n   @return booleanp"), list(makeSymbol("MUST"), list(makeSymbol("CNOT"), list(makeSymbol("NEGATED?"), makeSymbol("LITERAL"))), makeString("ADD-ATOM: ~s is negated.~%"), makeSymbol("LITERAL")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("NEGATED-LITS"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED")))), list(makeSymbol("DICTIONARY-PUSHNEW"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED"), makeSymbol("LITERAL"), list(makeSymbol("FUNCTION"), EQUAL)), list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED"), list(makeSymbol("REMOVE"), makeSymbol("LITERAL"), makeSymbol("NEGATED-LITS"), list(makeSymbol("FUNCTION"), EQUAL)))), list(RET, T));

    static private final SubLSymbol $sym28$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-WORLD-STATE-METHOD");

    static private final SubLString $str29$ADD_ATOM___s_is_negated___ = makeString("ADD-ATOM: ~s is negated.~%");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_ADD_ATOM_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-ADD-ATOM-METHOD");

    private static final SubLSymbol DELETE_ATOM = makeSymbol("DELETE-ATOM");

    static private final SubLList $list32 = list(makeString("@param LITERAL consp\n   @return booleanp"), list(makeSymbol("MUST"), list(makeSymbol("CNOT"), list(makeSymbol("NEGATED?"), makeSymbol("LITERAL"))), makeString("DELETE-ATOM: ~s is negated.~%"), makeSymbol("LITERAL")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("POSITIVE-LITS"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED")))), list(makeSymbol("DICTIONARY-PUSHNEW"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED"), makeSymbol("LITERAL"), list(makeSymbol("FUNCTION"), EQUAL)), list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED"), list(makeSymbol("REMOVE"), makeSymbol("LITERAL"), makeSymbol("POSITIVE-LITS"), list(makeSymbol("FUNCTION"), EQUAL)))), list(RET, T));

    static private final SubLSymbol $sym33$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-WORLD-STATE-METHOD");

    static private final SubLString $str34$DELETE_ATOM___s_is_negated___ = makeString("DELETE-ATOM: ~s is negated.~%");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_DELETE_ATOM_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-DELETE-ATOM-METHOD");

    private static final SubLSymbol ADD_LITERAL = makeSymbol("ADD-LITERAL");

    static private final SubLList $list37 = list(makeString("@param LITERAL listp"), list(RET, list(makeSymbol("ADD-ATOM"), makeSymbol("SELF"), makeSymbol("LITERAL"))));

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_ADD_LITERAL_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-ADD-LITERAL-METHOD");

    private static final SubLSymbol DELETE_LITERAL = makeSymbol("DELETE-LITERAL");

    static private final SubLList $list40 = list(makeString("@param LITERAL listp"), list(RET, list(makeSymbol("DELETE-ATOM"), makeSymbol("SELF"), makeSymbol("LITERAL"))));

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_DELETE_LITERAL_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-DELETE-LITERAL-METHOD");

    static private final SubLList $list43 = list(makeSymbol("LITERALS"));

    static private final SubLList $list44 = list(makeString("@param LITERALS listp"), list(makeSymbol("CDOLIST"), list(makeSymbol("LIT"), makeSymbol("LITERALS")), list(makeSymbol("ADD-ATOM"), makeSymbol("SELF"), makeSymbol("LIT"))), list(RET, NIL));

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_ADD_LITERALS_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-ADD-LITERALS-METHOD");

    static private final SubLList $list47 = list(makeString("@param LITERALS listp"), list(makeSymbol("CDOLIST"), list(makeSymbol("LIT"), makeSymbol("LITERALS")), list(makeSymbol("DELETE-ATOM"), makeSymbol("SELF"), makeSymbol("LIT"))), list(RET, NIL));

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_DELETE_LITERALS_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-DELETE-LITERALS-METHOD");

    private static final SubLSymbol COPY_STATE = makeSymbol("COPY-STATE");

    static private final SubLList $list50 = list(makeString("@return shop-basic-world-state-p"), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-STATE"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-WORLD-STATE")), list(QUOTE, makeSymbol("SHOP-BASIC-WORLD-STATE"))))), list(makeSymbol("SET-SLOT"), makeSymbol("NEW-STATE"), list(QUOTE, makeSymbol("POSITIVE-LITERALS")), list(makeSymbol("COPY-DICTIONARY"), makeSymbol("POSITIVE-LITERALS"))), list(makeSymbol("SET-SLOT"), makeSymbol("NEW-STATE"), list(QUOTE, makeSymbol("NEGATIVE-LITERALS")), list(makeSymbol("COPY-DICTIONARY"), makeSymbol("NEGATIVE-LITERALS"))), list(RET, makeSymbol("NEW-STATE"))));

    static private final SubLSymbol $sym51$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-WORLD-STATE-METHOD");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_COPY_STATE_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-COPY-STATE-METHOD");

    static private final SubLList $list54 = list(makeString("@return shop-basic-world-state-p"), list(RET, list(makeSymbol("INLINE-METHOD"), list(makeSymbol("COPY-STATE"), makeSymbol("SHOP-BASIC-WORLD-STATE")), makeSymbol("SELF"))));

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_NEXT_STATE_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-NEXT-STATE-METHOD");

    private static final SubLSymbol POSITIVE_MATCHES_TO_PREDICATE = makeSymbol("POSITIVE-MATCHES-TO-PREDICATE");

    static private final SubLList $list57 = list(makeSymbol("PRED"));

    static private final SubLList $list58 = list(list(RET, list(makeSymbol("APPLY"), list(makeSymbol("FUNCTION"), makeSymbol("CCONCATENATE")), list(makeSymbol("APPLY-MB"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED")), makeSymbol("*SHOP-ASK-MULTIBINDING*")))));

    static private final SubLSymbol $sym59$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-WORLD-STATE-METHOD");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_POSITIVE_MATCHES_TO_PREDICATE_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-POSITIVE-MATCHES-TO-PREDICATE-METHOD");

    private static final SubLSymbol NEGATIVE_MATCHES_TO_PREDICATE = makeSymbol("NEGATIVE-MATCHES-TO-PREDICATE");

    static private final SubLList $list63 = list(list(RET, list(makeSymbol("APPLY"), list(makeSymbol("FUNCTION"), makeSymbol("CCONCATENATE")), list(makeSymbol("APPLY-MB"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED")), makeSymbol("*SHOP-ASK-MULTIBINDING*")))));

    static private final SubLSymbol $sym64$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-WORLD-STATE-METHOD");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_NEGATIVE_MATCHES_TO_PREDICATE_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-NEGATIVE-MATCHES-TO-PREDICATE-METHOD");

    private static final SubLSymbol ASK_GROUND_LITERAL = makeSymbol("ASK-GROUND-LITERAL");

    static private final SubLList $list67 = list(list(makeSymbol("MUST"), list(makeSymbol("GROUND?"), makeSymbol("LITERAL")), makeString("ask-ground-literal: ~s is not ground.~%"), makeSymbol("LITERAL")), list(RET, list(makeSymbol("MEMBER"), makeSymbol("LITERAL"), list(makeSymbol("POSITIVE-MATCHES-TO-PREDICATE"), makeSymbol("SELF"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("FUNCTION"), EQUAL))));

    static private final SubLString $str68$ask_ground_literal___s_is_not_gro = makeString("ask-ground-literal: ~s is not ground.~%");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_ASK_GROUND_LITERAL_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-ASK-GROUND-LITERAL-METHOD");

    private static final SubLSymbol ASK_IF_NOT_GROUND_LITERAL = makeSymbol("ASK-IF-NOT-GROUND-LITERAL");

    static private final SubLList $list71 = list(list(makeSymbol("MUST"), list(makeSymbol("GROUND?"), makeSymbol("LITERAL")), makeString("ask-if-not-ground-literal: ~s is not ground.~%"), makeSymbol("LITERAL")), list(RET, list(makeSymbol("MEMBER"), makeSymbol("LITERAL"), list(makeSymbol("NEGATIVE-MATCHES-TO-PREDICATE"), makeSymbol("SELF"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("FUNCTION"), EQUAL))));

    static private final SubLString $str72$ask_if_not_ground_literal___s_is_ = makeString("ask-if-not-ground-literal: ~s is not ground.~%");

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_ASK_IF_NOT_GROUND_LITERAL_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-ASK-IF-NOT-GROUND-LITERAL-METHOD");

    static private final SubLList $list75 = list(makeString("@param LITERAL consp\n   @param MBINDING multibinding-p\n   @return binding-list-p"), list(makeSymbol("PWHEN"), list(makeSymbol("GROUND?"), makeSymbol("LITERAL")), list(makeSymbol("PIF"), list(makeSymbol("ASK-GROUND-LITERAL"), makeSymbol("SELF"), makeSymbol("LITERAL")), list(RET, list(QUOTE, list(list(cons(T, T))))), list(RET, NIL))), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("POSITIVE-MATCHES"), list(makeSymbol("POSITIVE-MATCHES-TO-PREDICATE"), makeSymbol("SELF"), makeSymbol("PRED"))), list(makeSymbol("BINDINGS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-LIT"), makeSymbol("POSITIVE-MATCHES")), list(makeSymbol("CLET"), list(list(makeSymbol("MGU"), list(makeSymbol("TERM-UNIFY"), makeSymbol("LITERAL"), makeSymbol("CUR-LIT")))), list(makeSymbol("PWHEN"), makeSymbol("MGU"), list(makeSymbol("CPUSH"), makeSymbol("MGU"), makeSymbol("BINDINGS"))))), list(RET, makeSymbol("BINDINGS"))));

    static private final SubLList $list76 = list(list(cons(T, T)));

    private static final SubLSymbol SHOP_BASIC_WORLD_STATE_ASK_METHOD = makeSymbol("SHOP-BASIC-WORLD-STATE-ASK-METHOD");

    // Definitions
    public static final SubLObject shop_world_state_p_alt(SubLObject shop_world_state) {
        return interfaces.subloop_instanceof_interface(shop_world_state, SHOP_WORLD_STATE);
    }

    // Definitions
    public static SubLObject shop_world_state_p(final SubLObject shop_world_state) {
        return interfaces.subloop_instanceof_interface(shop_world_state, SHOP_WORLD_STATE);
    }

    public static final SubLObject get_shop_basic_world_state_var_manager_alt(SubLObject v_shop_basic_world_state) {
        return classes.subloop_get_access_protected_instance_slot(v_shop_basic_world_state, THREE_INTEGER, VAR_MANAGER);
    }

    public static SubLObject get_shop_basic_world_state_var_manager(final SubLObject v_shop_basic_world_state) {
        return classes.subloop_get_access_protected_instance_slot(v_shop_basic_world_state, THREE_INTEGER, VAR_MANAGER);
    }

    public static final SubLObject set_shop_basic_world_state_var_manager_alt(SubLObject v_shop_basic_world_state, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_shop_basic_world_state, value, THREE_INTEGER, VAR_MANAGER);
    }

    public static SubLObject set_shop_basic_world_state_var_manager(final SubLObject v_shop_basic_world_state, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_shop_basic_world_state, value, THREE_INTEGER, VAR_MANAGER);
    }

    public static final SubLObject get_shop_basic_world_state_negative_literals_alt(SubLObject v_shop_basic_world_state) {
        return classes.subloop_get_access_protected_instance_slot(v_shop_basic_world_state, TWO_INTEGER, NEGATIVE_LITERALS);
    }

    public static SubLObject get_shop_basic_world_state_negative_literals(final SubLObject v_shop_basic_world_state) {
        return classes.subloop_get_access_protected_instance_slot(v_shop_basic_world_state, TWO_INTEGER, NEGATIVE_LITERALS);
    }

    public static final SubLObject set_shop_basic_world_state_negative_literals_alt(SubLObject v_shop_basic_world_state, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_shop_basic_world_state, value, TWO_INTEGER, NEGATIVE_LITERALS);
    }

    public static SubLObject set_shop_basic_world_state_negative_literals(final SubLObject v_shop_basic_world_state, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_shop_basic_world_state, value, TWO_INTEGER, NEGATIVE_LITERALS);
    }

    public static final SubLObject get_shop_basic_world_state_positive_literals_alt(SubLObject v_shop_basic_world_state) {
        return classes.subloop_get_access_protected_instance_slot(v_shop_basic_world_state, ONE_INTEGER, POSITIVE_LITERALS);
    }

    public static SubLObject get_shop_basic_world_state_positive_literals(final SubLObject v_shop_basic_world_state) {
        return classes.subloop_get_access_protected_instance_slot(v_shop_basic_world_state, ONE_INTEGER, POSITIVE_LITERALS);
    }

    public static final SubLObject set_shop_basic_world_state_positive_literals_alt(SubLObject v_shop_basic_world_state, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_shop_basic_world_state, value, ONE_INTEGER, POSITIVE_LITERALS);
    }

    public static SubLObject set_shop_basic_world_state_positive_literals(final SubLObject v_shop_basic_world_state, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_shop_basic_world_state, value, ONE_INTEGER, POSITIVE_LITERALS);
    }

    public static final SubLObject subloop_reserved_initialize_shop_basic_world_state_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_shop_basic_world_state_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_shop_basic_world_state_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_WORLD_STATE, POSITIVE_LITERALS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_WORLD_STATE, NEGATIVE_LITERALS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_WORLD_STATE, VAR_MANAGER, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_shop_basic_world_state_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_WORLD_STATE, POSITIVE_LITERALS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_WORLD_STATE, NEGATIVE_LITERALS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_WORLD_STATE, VAR_MANAGER, NIL);
        return NIL;
    }

    public static final SubLObject shop_basic_world_state_p_alt(SubLObject v_shop_basic_world_state) {
        return classes.subloop_instanceof_class(v_shop_basic_world_state, SHOP_BASIC_WORLD_STATE);
    }

    public static SubLObject shop_basic_world_state_p(final SubLObject v_shop_basic_world_state) {
        return classes.subloop_instanceof_class(v_shop_basic_world_state, SHOP_BASIC_WORLD_STATE);
    }

    public static final SubLObject shop_basic_world_state_initialize_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_world_state_method = NIL;
            SubLObject negative_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_negative_literals(self);
            SubLObject positive_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_positive_literals(self);
            try {
                try {
                    object.object_initialize_method(self);
                    positive_literals = dictionary.new_dictionary(symbol_function(EQ), TEN_INTEGER);
                    negative_literals = dictionary.new_dictionary(symbol_function(EQ), TEN_INTEGER);
                    sublisp_throw($sym17$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_negative_literals(self, negative_literals);
                            com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_positive_literals(self, positive_literals);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym17$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            }
            return catch_var_for_shop_basic_world_state_method;
        }
    }

    public static SubLObject shop_basic_world_state_initialize_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_world_state_method = NIL;
        SubLObject negative_literals = get_shop_basic_world_state_negative_literals(self);
        SubLObject positive_literals = get_shop_basic_world_state_positive_literals(self);
        try {
            thread.throwStack.push($sym17$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            try {
                object.object_initialize_method(self);
                positive_literals = dictionary.new_dictionary(symbol_function(EQ), TEN_INTEGER);
                negative_literals = dictionary.new_dictionary(symbol_function(EQ), TEN_INTEGER);
                sublisp_throw($sym17$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_world_state_negative_literals(self, negative_literals);
                    set_shop_basic_world_state_positive_literals(self, positive_literals);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym17$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_world_state_method;
    }

    public static final SubLObject shop_basic_world_state_same_state_p_method_alt(SubLObject self, SubLObject state1, SubLObject state2) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_world_state_method = NIL;
                SubLObject negative_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_negative_literals(self);
                SubLObject positive_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_positive_literals(self);
                try {
                    try {
                        {
                            SubLObject pos_lits2 = instances.get_slot(state2, POSITIVE_LITERALS);
                            SubLObject neg_lits2 = instances.get_slot(state2, NEGATIVE_LITERALS);
                            SubLObject v_answer = T;
                            SubLObject doneP = NIL;
                            {
                                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(instances.get_slot(state1, POSITIVE_LITERALS)));
                                while (!((NIL != doneP) || (NIL != dictionary_contents.do_dictionary_contents_doneP(iteration_state)))) {
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject key = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                        SubLObject value1 = thread.secondMultipleValue();
                                        thread.resetMultipleValues();
                                        {
                                            SubLObject value2 = dictionary.dictionary_lookup(key, pos_lits2, UNPROVIDED);
                                            if ((NIL != subsetp(value1, value2, UNPROVIDED, UNPROVIDED)) && (NIL != subsetp(value2, value1, UNPROVIDED, UNPROVIDED))) {
                                                doneP = T;
                                                v_answer = NIL;
                                            }
                                        }
                                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                    }
                                } 
                                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                            }
                            {
                                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(instances.get_slot(state1, NEGATIVE_LITERALS)));
                                while (!((NIL != doneP) || (NIL != dictionary_contents.do_dictionary_contents_doneP(iteration_state)))) {
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject key = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                        SubLObject value1 = thread.secondMultipleValue();
                                        thread.resetMultipleValues();
                                        {
                                            SubLObject value2 = dictionary.dictionary_lookup(key, neg_lits2, UNPROVIDED);
                                            if ((NIL != subsetp(value1, value2, UNPROVIDED, UNPROVIDED)) && (NIL != subsetp(value2, value1, UNPROVIDED, UNPROVIDED))) {
                                                doneP = T;
                                                v_answer = NIL;
                                            }
                                        }
                                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                    }
                                } 
                                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                            }
                            sublisp_throw($sym23$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, v_answer);
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_negative_literals(self, negative_literals);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_positive_literals(self, positive_literals);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym23$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
                }
                return catch_var_for_shop_basic_world_state_method;
            }
        }
    }

    public static SubLObject shop_basic_world_state_same_state_p_method(final SubLObject self, final SubLObject state1, final SubLObject state2) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_world_state_method = NIL;
        final SubLObject negative_literals = get_shop_basic_world_state_negative_literals(self);
        final SubLObject positive_literals = get_shop_basic_world_state_positive_literals(self);
        try {
            thread.throwStack.push($sym23$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            try {
                final SubLObject pos_lits2 = instances.get_slot(state2, POSITIVE_LITERALS);
                final SubLObject neg_lits2 = instances.get_slot(state2, NEGATIVE_LITERALS);
                SubLObject v_answer = T;
                SubLObject doneP;
                SubLObject iteration_state;
                for (doneP = NIL, iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(instances.get_slot(state1, POSITIVE_LITERALS))); (NIL == doneP) && (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                    thread.resetMultipleValues();
                    final SubLObject key = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                    final SubLObject value1 = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    final SubLObject value2 = dictionary.dictionary_lookup(key, pos_lits2, UNPROVIDED);
                    if ((NIL != subsetp(value1, value2, UNPROVIDED, UNPROVIDED)) && (NIL != subsetp(value2, value1, UNPROVIDED, UNPROVIDED))) {
                        doneP = T;
                        v_answer = NIL;
                    }
                }
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(instances.get_slot(state1, NEGATIVE_LITERALS))); (NIL == doneP) && (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                    thread.resetMultipleValues();
                    final SubLObject key = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                    final SubLObject value1 = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    final SubLObject value2 = dictionary.dictionary_lookup(key, neg_lits2, UNPROVIDED);
                    if ((NIL != subsetp(value1, value2, UNPROVIDED, UNPROVIDED)) && (NIL != subsetp(value2, value1, UNPROVIDED, UNPROVIDED))) {
                        doneP = T;
                        v_answer = NIL;
                    }
                }
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                sublisp_throw($sym23$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, v_answer);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_world_state_negative_literals(self, negative_literals);
                    set_shop_basic_world_state_positive_literals(self, positive_literals);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym23$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_world_state_method;
    }

    /**
     *
     *
     * @param LITERAL
    consp
     * 		
     * @return booleanp
     */
    @LispMethod(comment = "@param LITERAL\nconsp\r\n\t\t\r\n@return booleanp")
    public static final SubLObject shop_basic_world_state_add_atom_method_alt(SubLObject self, SubLObject literal) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_world_state_method = NIL;
                SubLObject negative_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_negative_literals(self);
                SubLObject positive_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_positive_literals(self);
                try {
                    try {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if (NIL != cycl_utilities.negatedP(literal)) {
                                Errors.error($str_alt29$ADD_ATOM___s_is_negated___, literal);
                            }
                        }
                        {
                            SubLObject pred = literal_predicate(literal, UNPROVIDED);
                            SubLObject negated_lits = dictionary.dictionary_lookup(negative_literals, pred, UNPROVIDED);
                            dictionary_utilities.dictionary_pushnew(positive_literals, pred, literal, symbol_function(EQUAL), UNPROVIDED);
                            dictionary.dictionary_enter(negative_literals, pred, remove(literal, negated_lits, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        }
                        sublisp_throw($sym28$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, T);
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_negative_literals(self, negative_literals);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_positive_literals(self, positive_literals);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym28$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
                }
                return catch_var_for_shop_basic_world_state_method;
            }
        }
    }

    /**
     *
     *
     * @param LITERAL
    consp
     * 		
     * @return booleanp
     */
    @LispMethod(comment = "@param LITERAL\nconsp\r\n\t\t\r\n@return booleanp")
    public static SubLObject shop_basic_world_state_add_atom_method(final SubLObject self, final SubLObject literal) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_world_state_method = NIL;
        final SubLObject negative_literals = get_shop_basic_world_state_negative_literals(self);
        final SubLObject positive_literals = get_shop_basic_world_state_positive_literals(self);
        try {
            thread.throwStack.push($sym28$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            try {
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL != cycl_utilities.negatedP(literal))) {
                    Errors.error($str29$ADD_ATOM___s_is_negated___, literal);
                }
                final SubLObject pred = literal_predicate(literal, UNPROVIDED);
                final SubLObject negated_lits = dictionary.dictionary_lookup(negative_literals, pred, UNPROVIDED);
                dictionary_utilities.dictionary_pushnew(positive_literals, pred, literal, symbol_function(EQUAL), UNPROVIDED);
                dictionary.dictionary_enter(negative_literals, pred, remove(literal, negated_lits, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                sublisp_throw($sym28$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_world_state_negative_literals(self, negative_literals);
                    set_shop_basic_world_state_positive_literals(self, positive_literals);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym28$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_world_state_method;
    }

    /**
     *
     *
     * @param LITERAL
    consp
     * 		
     * @return booleanp
     */
    @LispMethod(comment = "@param LITERAL\nconsp\r\n\t\t\r\n@return booleanp")
    public static final SubLObject shop_basic_world_state_delete_atom_method_alt(SubLObject self, SubLObject literal) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_world_state_method = NIL;
                SubLObject negative_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_negative_literals(self);
                SubLObject positive_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_positive_literals(self);
                try {
                    try {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if (NIL != cycl_utilities.negatedP(literal)) {
                                Errors.error($str_alt34$DELETE_ATOM___s_is_negated___, literal);
                            }
                        }
                        {
                            SubLObject pred = literal_predicate(literal, UNPROVIDED);
                            SubLObject positive_lits = dictionary.dictionary_lookup(positive_literals, pred, UNPROVIDED);
                            dictionary_utilities.dictionary_pushnew(negative_literals, pred, literal, symbol_function(EQUAL), UNPROVIDED);
                            dictionary.dictionary_enter(positive_literals, pred, remove(literal, positive_lits, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                        }
                        sublisp_throw($sym33$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, T);
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_negative_literals(self, negative_literals);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_positive_literals(self, positive_literals);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym33$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
                }
                return catch_var_for_shop_basic_world_state_method;
            }
        }
    }

    /**
     *
     *
     * @param LITERAL
    consp
     * 		
     * @return booleanp
     */
    @LispMethod(comment = "@param LITERAL\nconsp\r\n\t\t\r\n@return booleanp")
    public static SubLObject shop_basic_world_state_delete_atom_method(final SubLObject self, final SubLObject literal) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_world_state_method = NIL;
        final SubLObject negative_literals = get_shop_basic_world_state_negative_literals(self);
        final SubLObject positive_literals = get_shop_basic_world_state_positive_literals(self);
        try {
            thread.throwStack.push($sym33$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            try {
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL != cycl_utilities.negatedP(literal))) {
                    Errors.error($str34$DELETE_ATOM___s_is_negated___, literal);
                }
                final SubLObject pred = literal_predicate(literal, UNPROVIDED);
                final SubLObject positive_lits = dictionary.dictionary_lookup(positive_literals, pred, UNPROVIDED);
                dictionary_utilities.dictionary_pushnew(negative_literals, pred, literal, symbol_function(EQUAL), UNPROVIDED);
                dictionary.dictionary_enter(positive_literals, pred, remove(literal, positive_lits, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                sublisp_throw($sym33$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_world_state_negative_literals(self, negative_literals);
                    set_shop_basic_world_state_positive_literals(self, positive_literals);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym33$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_world_state_method;
    }

    /**
     *
     *
     * @param LITERAL
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERAL\nlistp")
    public static final SubLObject shop_basic_world_state_add_literal_method_alt(SubLObject self, SubLObject literal) {
        return com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_add_atom_method(self, literal);
    }

    /**
     *
     *
     * @param LITERAL
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERAL\nlistp")
    public static SubLObject shop_basic_world_state_add_literal_method(final SubLObject self, final SubLObject literal) {
        return shop_basic_world_state_add_atom_method(self, literal);
    }

    /**
     *
     *
     * @param LITERAL
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERAL\nlistp")
    public static final SubLObject shop_basic_world_state_delete_literal_method_alt(SubLObject self, SubLObject literal) {
        return com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_delete_atom_method(self, literal);
    }

    /**
     *
     *
     * @param LITERAL
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERAL\nlistp")
    public static SubLObject shop_basic_world_state_delete_literal_method(final SubLObject self, final SubLObject literal) {
        return shop_basic_world_state_delete_atom_method(self, literal);
    }

    /**
     *
     *
     * @param LITERALS
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERALS\nlistp")
    public static final SubLObject shop_basic_world_state_add_literals_method_alt(SubLObject self, SubLObject literals) {
        {
            SubLObject cdolist_list_var = literals;
            SubLObject lit = NIL;
            for (lit = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , lit = cdolist_list_var.first()) {
                com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_add_atom_method(self, lit);
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param LITERALS
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERALS\nlistp")
    public static SubLObject shop_basic_world_state_add_literals_method(final SubLObject self, final SubLObject literals) {
        SubLObject cdolist_list_var = literals;
        SubLObject lit = NIL;
        lit = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            shop_basic_world_state_add_atom_method(self, lit);
            cdolist_list_var = cdolist_list_var.rest();
            lit = cdolist_list_var.first();
        } 
        return NIL;
    }

    /**
     *
     *
     * @param LITERALS
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERALS\nlistp")
    public static final SubLObject shop_basic_world_state_delete_literals_method_alt(SubLObject self, SubLObject literals) {
        {
            SubLObject cdolist_list_var = literals;
            SubLObject lit = NIL;
            for (lit = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , lit = cdolist_list_var.first()) {
                com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_delete_atom_method(self, lit);
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param LITERALS
    listp
     * 		
     */
    @LispMethod(comment = "@param LITERALS\nlistp")
    public static SubLObject shop_basic_world_state_delete_literals_method(final SubLObject self, final SubLObject literals) {
        SubLObject cdolist_list_var = literals;
        SubLObject lit = NIL;
        lit = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            shop_basic_world_state_delete_atom_method(self, lit);
            cdolist_list_var = cdolist_list_var.rest();
            lit = cdolist_list_var.first();
        } 
        return NIL;
    }

    /**
     *
     *
     * @return shop-basic-world-state-p
     */
    @LispMethod(comment = "@return shop-basic-world-state-p")
    public static final SubLObject shop_basic_world_state_copy_state_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_world_state_method = NIL;
            SubLObject negative_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_negative_literals(self);
            SubLObject positive_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_positive_literals(self);
            try {
                try {
                    {
                        SubLObject new_state = object.object_new_method(SHOP_BASIC_WORLD_STATE);
                        instances.set_slot(new_state, POSITIVE_LITERALS, dictionary_utilities.copy_dictionary(positive_literals));
                        instances.set_slot(new_state, NEGATIVE_LITERALS, dictionary_utilities.copy_dictionary(negative_literals));
                        sublisp_throw($sym51$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, new_state);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_negative_literals(self, negative_literals);
                            com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_positive_literals(self, positive_literals);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym51$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            }
            return catch_var_for_shop_basic_world_state_method;
        }
    }

    /**
     *
     *
     * @return shop-basic-world-state-p
     */
    @LispMethod(comment = "@return shop-basic-world-state-p")
    public static SubLObject shop_basic_world_state_copy_state_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_world_state_method = NIL;
        final SubLObject negative_literals = get_shop_basic_world_state_negative_literals(self);
        final SubLObject positive_literals = get_shop_basic_world_state_positive_literals(self);
        try {
            thread.throwStack.push($sym51$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            try {
                final SubLObject new_state = object.object_new_method(SHOP_BASIC_WORLD_STATE);
                instances.set_slot(new_state, POSITIVE_LITERALS, dictionary_utilities.copy_dictionary(positive_literals));
                instances.set_slot(new_state, NEGATIVE_LITERALS, dictionary_utilities.copy_dictionary(negative_literals));
                sublisp_throw($sym51$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, new_state);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_world_state_negative_literals(self, negative_literals);
                    set_shop_basic_world_state_positive_literals(self, positive_literals);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym51$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_world_state_method;
    }

    /**
     *
     *
     * @return shop-basic-world-state-p
     */
    @LispMethod(comment = "@return shop-basic-world-state-p")
    public static final SubLObject shop_basic_world_state_next_state_method_alt(SubLObject self) {
        return com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_copy_state_method(self);
    }

    /**
     *
     *
     * @return shop-basic-world-state-p
     */
    @LispMethod(comment = "@return shop-basic-world-state-p")
    public static SubLObject shop_basic_world_state_next_state_method(final SubLObject self) {
        return shop_basic_world_state_copy_state_method(self);
    }

    public static final SubLObject shop_basic_world_state_positive_matches_to_predicate_method_alt(SubLObject self, SubLObject pred) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_world_state_method = NIL;
                SubLObject positive_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_positive_literals(self);
                try {
                    try {
                        sublisp_throw($sym59$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, apply(symbol_function(CCONCATENATE), multibindings.apply_mb(dictionary.dictionary_lookup(positive_literals, pred, UNPROVIDED), shop_parameters.$shop_ask_multibinding$.getDynamicValue(thread))));
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_positive_literals(self, positive_literals);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym59$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
                }
                return catch_var_for_shop_basic_world_state_method;
            }
        }
    }

    public static SubLObject shop_basic_world_state_positive_matches_to_predicate_method(final SubLObject self, final SubLObject pred) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_world_state_method = NIL;
        final SubLObject positive_literals = get_shop_basic_world_state_positive_literals(self);
        try {
            thread.throwStack.push($sym59$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            try {
                sublisp_throw($sym59$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, apply(symbol_function(CCONCATENATE), multibindings.apply_mb(dictionary.dictionary_lookup(positive_literals, pred, UNPROVIDED), shop_parameters.$shop_ask_multibinding$.getDynamicValue(thread))));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_world_state_positive_literals(self, positive_literals);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym59$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_world_state_method;
    }

    public static final SubLObject shop_basic_world_state_negative_matches_to_predicate_method_alt(SubLObject self, SubLObject pred) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_world_state_method = NIL;
                SubLObject negative_literals = com.cyc.cycjava.cycl.shop_basic_world_state.get_shop_basic_world_state_negative_literals(self);
                try {
                    try {
                        sublisp_throw($sym64$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, apply(symbol_function(CCONCATENATE), multibindings.apply_mb(dictionary.dictionary_lookup(negative_literals, pred, UNPROVIDED), shop_parameters.$shop_ask_multibinding$.getDynamicValue(thread))));
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_basic_world_state.set_shop_basic_world_state_negative_literals(self, negative_literals);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym64$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
                }
                return catch_var_for_shop_basic_world_state_method;
            }
        }
    }

    public static SubLObject shop_basic_world_state_negative_matches_to_predicate_method(final SubLObject self, final SubLObject pred) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_world_state_method = NIL;
        final SubLObject negative_literals = get_shop_basic_world_state_negative_literals(self);
        try {
            thread.throwStack.push($sym64$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
            try {
                sublisp_throw($sym64$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD, apply(symbol_function(CCONCATENATE), multibindings.apply_mb(dictionary.dictionary_lookup(negative_literals, pred, UNPROVIDED), shop_parameters.$shop_ask_multibinding$.getDynamicValue(thread))));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_world_state_negative_literals(self, negative_literals);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_world_state_method = Errors.handleThrowable(ccatch_env_var, $sym64$OUTER_CATCH_FOR_SHOP_BASIC_WORLD_STATE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_world_state_method;
    }

    static private final SubLList $list_alt1 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETE-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-LITERALS"), list(makeSymbol("LITERALS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETE-LITERALS"), list(makeSymbol("LITERALS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-ATOM"), list(makeSymbol("ATOM")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETE-ATOM"), list(makeSymbol("ATOM")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COPY-STATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NEXT-STATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-CLASS-METHOD"), makeSymbol("SAME-STATE-P"), list(makeSymbol("STATE1"), makeSymbol("STATE2")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")) });

    public static final SubLObject shop_basic_world_state_ask_ground_literal_method_alt(SubLObject self, SubLObject literal) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == groundP(literal, UNPROVIDED)) {
                    Errors.error($str_alt68$ask_ground_literal___s_is_not_gro, literal);
                }
            }
            return member(literal, com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_positive_matches_to_predicate_method(self, literal_predicate(literal, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED);
        }
    }

    public static SubLObject shop_basic_world_state_ask_ground_literal_method(final SubLObject self, final SubLObject literal) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == groundP(literal, UNPROVIDED))) {
            Errors.error($str68$ask_ground_literal___s_is_not_gro, literal);
        }
        return member(literal, shop_basic_world_state_positive_matches_to_predicate_method(self, literal_predicate(literal, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED);
    }

    public static final SubLObject shop_basic_world_state_ask_if_not_ground_literal_method_alt(SubLObject self, SubLObject literal) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == groundP(literal, UNPROVIDED)) {
                    Errors.error($str_alt72$ask_if_not_ground_literal___s_is_, literal);
                }
            }
            return member(literal, com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_negative_matches_to_predicate_method(self, literal_predicate(literal, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED);
        }
    }

    public static SubLObject shop_basic_world_state_ask_if_not_ground_literal_method(final SubLObject self, final SubLObject literal) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == groundP(literal, UNPROVIDED))) {
            Errors.error($str72$ask_if_not_ground_literal___s_is_, literal);
        }
        return member(literal, shop_basic_world_state_negative_matches_to_predicate_method(self, literal_predicate(literal, UNPROVIDED)), symbol_function(EQUAL), UNPROVIDED);
    }

    static private final SubLList $list_alt4 = list(makeSymbol("SHOP-WORLD-STATE"));

    static private final SubLList $list_alt5 = list(list(makeSymbol("POSITIVE-LITERALS"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("NEGATIVE-LITERALS"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("VAR-MANAGER"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK-GROUND-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK-IF-NOT-GROUND-LITERAL"), list(makeSymbol("LITERAL")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POSITIVE-MATCHES-TO-PREDICATE"), list(makeSymbol("PRED")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NEGATIVE-MATCHES-TO-PREDICATE"), list(makeSymbol("PRED")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ASK-WITH-MULTIBINDING"), list(makeSymbol("LITERAL"), makeSymbol("MBINDING")), makeKeyword("PUBLIC")));

    /**
     *
     *
     * @param LITERAL
    consp
     * 		
     * @param MBINDING
    multibinding-p
     * 		
     * @return binding-list-p
     */
    @LispMethod(comment = "@param LITERAL\nconsp\r\n\t\t\r\n@param MBINDING\nmultibinding-p\r\n\t\t\r\n@return binding-list-p")
    public static final SubLObject shop_basic_world_state_ask_method_alt(SubLObject self, SubLObject literal) {
        if (NIL != groundP(literal, UNPROVIDED)) {
            if (NIL != com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_ask_ground_literal_method(self, literal)) {
                return $list_alt76;
            } else {
                return NIL;
            }
        }
        {
            SubLObject pred = literal_predicate(literal, UNPROVIDED);
            SubLObject positive_matches = com.cyc.cycjava.cycl.shop_basic_world_state.shop_basic_world_state_positive_matches_to_predicate_method(self, pred);
            SubLObject v_bindings = NIL;
            SubLObject cdolist_list_var = positive_matches;
            SubLObject cur_lit = NIL;
            for (cur_lit = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_lit = cdolist_list_var.first()) {
                {
                    SubLObject mgu = unification_utilities.term_unify(literal, cur_lit, UNPROVIDED, UNPROVIDED);
                    if (NIL != mgu) {
                        v_bindings = cons(mgu, v_bindings);
                    }
                }
            }
            return v_bindings;
        }
    }

    /**
     *
     *
     * @param LITERAL
    consp
     * 		
     * @param MBINDING
    multibinding-p
     * 		
     * @return binding-list-p
     */
    @LispMethod(comment = "@param LITERAL\nconsp\r\n\t\t\r\n@param MBINDING\nmultibinding-p\r\n\t\t\r\n@return binding-list-p")
    public static SubLObject shop_basic_world_state_ask_method(final SubLObject self, final SubLObject literal) {
        if (NIL == groundP(literal, UNPROVIDED)) {
            final SubLObject pred = literal_predicate(literal, UNPROVIDED);
            final SubLObject positive_matches = shop_basic_world_state_positive_matches_to_predicate_method(self, pred);
            SubLObject v_bindings = NIL;
            SubLObject cdolist_list_var = positive_matches;
            SubLObject cur_lit = NIL;
            cur_lit = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject mgu = unification_utilities.term_unify(literal, cur_lit, UNPROVIDED, UNPROVIDED);
                if (NIL != mgu) {
                    v_bindings = cons(mgu, v_bindings);
                }
                cdolist_list_var = cdolist_list_var.rest();
                cur_lit = cdolist_list_var.first();
            } 
            return v_bindings;
        }
        if (NIL != shop_basic_world_state_ask_ground_literal_method(self, literal)) {
            return $list76;
        }
        return NIL;
    }

    static private final SubLList $list_alt15 = list(makeKeyword("PROTECTED"));

    /**
     *
     *
     * @param BINDINGS
    binding-list-p
     * 		
     * @param WORLD-STATE
    shop-basic-world-state-p
     * 		
     * @return binding-list-p ;; those bindings in BINDINGS
    that don't contradict literals in WORLD-STATE.
     */
    @LispMethod(comment = "@param BINDINGS\nbinding-list-p\r\n\t\t\r\n@param WORLD-STATE\nshop-basic-world-state-p\r\n\t\t\r\n@return binding-list-p ;; those bindings in BINDINGS\r\nthat don\'t contradict literals in WORLD-STATE.")
    public static final SubLObject confirm_bindings_against_world_state_alt(SubLObject world_state, SubLObject goals, SubLObject v_bindings) {
        if (v_bindings.equal($list_alt76)) {
            {
                SubLObject confirmedP = T;
                SubLObject cdolist_list_var = goals;
                SubLObject cur_lit = NIL;
                for (cur_lit = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_lit = cdolist_list_var.first()) {
                    {
                        SubLObject pred = literal_predicate(cur_lit, UNPROVIDED);
                        SubLObject neg_matches = methods.funcall_instance_method_with_1_args(world_state, NEGATIVE_MATCHES_TO_PREDICATE, pred);
                        if (NIL != subl_promotions.memberP(cur_lit, neg_matches, symbol_function(EQUAL), UNPROVIDED)) {
                            confirmedP = NIL;
                        }
                    }
                }
                return NIL != confirmedP ? ((SubLObject) (v_bindings)) : NIL;
            }
        } else {
            {
                SubLObject new_bindings = NIL;
                SubLObject cdolist_list_var = v_bindings;
                SubLObject cur_binding = NIL;
                for (cur_binding = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_binding = cdolist_list_var.first()) {
                    {
                        SubLObject confirmedP = T;
                        SubLObject cdolist_list_var_1 = goals;
                        SubLObject cur_lit = NIL;
                        for (cur_lit = cdolist_list_var_1.first(); NIL != cdolist_list_var_1; cdolist_list_var_1 = cdolist_list_var_1.rest() , cur_lit = cdolist_list_var_1.first()) {
                            if (NIL == cycl_utilities.negatedP(cur_lit)) {
                                {
                                    SubLObject pred = literal_predicate(cur_lit, UNPROVIDED);
                                    SubLObject neg_matches = methods.funcall_instance_method_with_1_args(world_state, NEGATIVE_MATCHES_TO_PREDICATE, pred);
                                    if (NIL != subl_promotions.memberP(shop_datastructures.shop_apply_substitution(cur_lit, cur_binding), neg_matches, symbol_function(EQUAL), UNPROVIDED)) {
                                        confirmedP = NIL;
                                    }
                                }
                            }
                        }
                        if (NIL != confirmedP) {
                            new_bindings = cons(cur_binding, new_bindings);
                        }
                    }
                }
                return new_bindings;
            }
        }
    }

    /**
     *
     *
     * @param BINDINGS
    binding-list-p
     * 		
     * @param WORLD-STATE
    shop-basic-world-state-p
     * 		
     * @return binding-list-p ;; those bindings in BINDINGS
    that don't contradict literals in WORLD-STATE.
     */
    @LispMethod(comment = "@param BINDINGS\nbinding-list-p\r\n\t\t\r\n@param WORLD-STATE\nshop-basic-world-state-p\r\n\t\t\r\n@return binding-list-p ;; those bindings in BINDINGS\r\nthat don\'t contradict literals in WORLD-STATE.")
    public static SubLObject confirm_bindings_against_world_state(final SubLObject world_state, final SubLObject goals, final SubLObject v_bindings) {
        if (v_bindings.equal($list76)) {
            SubLObject confirmedP = T;
            SubLObject cdolist_list_var = goals;
            SubLObject cur_lit = NIL;
            cur_lit = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject pred = literal_predicate(cur_lit, UNPROVIDED);
                final SubLObject neg_matches = methods.funcall_instance_method_with_1_args(world_state, NEGATIVE_MATCHES_TO_PREDICATE, pred);
                if (NIL != subl_promotions.memberP(cur_lit, neg_matches, symbol_function(EQUAL), UNPROVIDED)) {
                    confirmedP = NIL;
                }
                cdolist_list_var = cdolist_list_var.rest();
                cur_lit = cdolist_list_var.first();
            } 
            return NIL != confirmedP ? v_bindings : NIL;
        }
        SubLObject new_bindings = NIL;
        SubLObject cdolist_list_var = v_bindings;
        SubLObject cur_binding = NIL;
        cur_binding = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject confirmedP2 = T;
            SubLObject cdolist_list_var_$1 = goals;
            SubLObject cur_lit2 = NIL;
            cur_lit2 = cdolist_list_var_$1.first();
            while (NIL != cdolist_list_var_$1) {
                if (NIL == cycl_utilities.negatedP(cur_lit2)) {
                    final SubLObject pred2 = literal_predicate(cur_lit2, UNPROVIDED);
                    final SubLObject neg_matches2 = methods.funcall_instance_method_with_1_args(world_state, NEGATIVE_MATCHES_TO_PREDICATE, pred2);
                    if (NIL != subl_promotions.memberP(shop_datastructures.shop_apply_substitution(cur_lit2, cur_binding), neg_matches2, symbol_function(EQUAL), UNPROVIDED)) {
                        confirmedP2 = NIL;
                    }
                }
                cdolist_list_var_$1 = cdolist_list_var_$1.rest();
                cur_lit2 = cdolist_list_var_$1.first();
            } 
            if (NIL != confirmedP2) {
                new_bindings = cons(cur_binding, new_bindings);
            }
            cdolist_list_var = cdolist_list_var.rest();
            cur_binding = cdolist_list_var.first();
        } 
        return new_bindings;
    }

    static private final SubLList $list_alt16 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(makeSymbol("CSETQ"), makeSymbol("POSITIVE-LITERALS"), list(makeSymbol("NEW-DICTIONARY"), list(makeSymbol("FUNCTION"), EQ), TEN_INTEGER)), list(makeSymbol("CSETQ"), makeSymbol("NEGATIVE-LITERALS"), list(makeSymbol("NEW-DICTIONARY"), list(makeSymbol("FUNCTION"), EQ), TEN_INTEGER)), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt20 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list_alt21 = list(makeSymbol("STATE1"), makeSymbol("STATE2"));

    static private final SubLList $list_alt22 = list(list(makeSymbol("CLET"), list(list(makeSymbol("POS-LITS2"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE2"), list(QUOTE, makeSymbol("POSITIVE-LITERALS")))), list(makeSymbol("NEG-LITS2"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE2"), list(QUOTE, makeSymbol("NEGATIVE-LITERALS")))), list(makeSymbol("ANSWER"), T), list(makeSymbol("DONE?"), NIL)), list(makeSymbol("DO-DICTIONARY"), list(makeSymbol("KEY"), makeSymbol("VALUE1"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE1"), list(QUOTE, makeSymbol("POSITIVE-LITERALS"))), makeSymbol("DONE?")), list(makeSymbol("CLET"), list(list(makeSymbol("VALUE2"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("KEY"), makeSymbol("POS-LITS2")))), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("SUBSETP"), makeSymbol("VALUE1"), makeSymbol("VALUE2")), list(makeSymbol("SUBSETP"), makeSymbol("VALUE2"), makeSymbol("VALUE1"))), list(makeSymbol("CSETQ"), makeSymbol("DONE?"), T), list(makeSymbol("CSETQ"), makeSymbol("ANSWER"), NIL)))), list(makeSymbol("DO-DICTIONARY"), list(makeSymbol("KEY"), makeSymbol("VALUE1"), list(makeSymbol("GET-SLOT"), makeSymbol("STATE1"), list(QUOTE, makeSymbol("NEGATIVE-LITERALS"))), makeSymbol("DONE?")), list(makeSymbol("CLET"), list(list(makeSymbol("VALUE2"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("KEY"), makeSymbol("NEG-LITS2")))), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("SUBSETP"), makeSymbol("VALUE1"), makeSymbol("VALUE2")), list(makeSymbol("SUBSETP"), makeSymbol("VALUE2"), makeSymbol("VALUE1"))), list(makeSymbol("CSETQ"), makeSymbol("DONE?"), T), list(makeSymbol("CSETQ"), makeSymbol("ANSWER"), NIL)))), list(RET, makeSymbol("ANSWER"))));

    public static SubLObject declare_shop_basic_world_state_file() {
        declareFunction("shop_world_state_p", "SHOP-WORLD-STATE-P", 1, 0, false);
        declareFunction("get_shop_basic_world_state_var_manager", "GET-SHOP-BASIC-WORLD-STATE-VAR-MANAGER", 1, 0, false);
        declareFunction("set_shop_basic_world_state_var_manager", "SET-SHOP-BASIC-WORLD-STATE-VAR-MANAGER", 2, 0, false);
        declareFunction("get_shop_basic_world_state_negative_literals", "GET-SHOP-BASIC-WORLD-STATE-NEGATIVE-LITERALS", 1, 0, false);
        declareFunction("set_shop_basic_world_state_negative_literals", "SET-SHOP-BASIC-WORLD-STATE-NEGATIVE-LITERALS", 2, 0, false);
        declareFunction("get_shop_basic_world_state_positive_literals", "GET-SHOP-BASIC-WORLD-STATE-POSITIVE-LITERALS", 1, 0, false);
        declareFunction("set_shop_basic_world_state_positive_literals", "SET-SHOP-BASIC-WORLD-STATE-POSITIVE-LITERALS", 2, 0, false);
        declareFunction("subloop_reserved_initialize_shop_basic_world_state_class", "SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-WORLD-STATE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_shop_basic_world_state_instance", "SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-WORLD-STATE-INSTANCE", 1, 0, false);
        declareFunction("shop_basic_world_state_p", "SHOP-BASIC-WORLD-STATE-P", 1, 0, false);
        declareFunction("shop_basic_world_state_initialize_method", "SHOP-BASIC-WORLD-STATE-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("shop_basic_world_state_same_state_p_method", "SHOP-BASIC-WORLD-STATE-SAME-STATE-P-METHOD", 3, 0, false);
        declareFunction("shop_basic_world_state_add_atom_method", "SHOP-BASIC-WORLD-STATE-ADD-ATOM-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_delete_atom_method", "SHOP-BASIC-WORLD-STATE-DELETE-ATOM-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_add_literal_method", "SHOP-BASIC-WORLD-STATE-ADD-LITERAL-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_delete_literal_method", "SHOP-BASIC-WORLD-STATE-DELETE-LITERAL-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_add_literals_method", "SHOP-BASIC-WORLD-STATE-ADD-LITERALS-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_delete_literals_method", "SHOP-BASIC-WORLD-STATE-DELETE-LITERALS-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_copy_state_method", "SHOP-BASIC-WORLD-STATE-COPY-STATE-METHOD", 1, 0, false);
        declareFunction("shop_basic_world_state_next_state_method", "SHOP-BASIC-WORLD-STATE-NEXT-STATE-METHOD", 1, 0, false);
        declareFunction("shop_basic_world_state_positive_matches_to_predicate_method", "SHOP-BASIC-WORLD-STATE-POSITIVE-MATCHES-TO-PREDICATE-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_negative_matches_to_predicate_method", "SHOP-BASIC-WORLD-STATE-NEGATIVE-MATCHES-TO-PREDICATE-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_ask_ground_literal_method", "SHOP-BASIC-WORLD-STATE-ASK-GROUND-LITERAL-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_ask_if_not_ground_literal_method", "SHOP-BASIC-WORLD-STATE-ASK-IF-NOT-GROUND-LITERAL-METHOD", 2, 0, false);
        declareFunction("shop_basic_world_state_ask_method", "SHOP-BASIC-WORLD-STATE-ASK-METHOD", 2, 0, false);
        declareFunction("confirm_bindings_against_world_state", "CONFIRM-BINDINGS-AGAINST-WORLD-STATE", 3, 0, false);
        return NIL;
    }

    static private final SubLList $list_alt26 = list(makeSymbol("LITERAL"));

    static private final SubLList $list_alt27 = list(makeString("@param LITERAL consp\n   @return booleanp"), list(makeSymbol("MUST"), list(makeSymbol("CNOT"), list(makeSymbol("NEGATED?"), makeSymbol("LITERAL"))), makeString("ADD-ATOM: ~s is negated.~%"), makeSymbol("LITERAL")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("NEGATED-LITS"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED")))), list(makeSymbol("DICTIONARY-PUSHNEW"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED"), makeSymbol("LITERAL"), list(makeSymbol("FUNCTION"), EQUAL)), list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED"), list(makeSymbol("REMOVE"), makeSymbol("LITERAL"), makeSymbol("NEGATED-LITS"), list(makeSymbol("FUNCTION"), EQUAL)))), list(RET, T));

    static private final SubLString $str_alt29$ADD_ATOM___s_is_negated___ = makeString("ADD-ATOM: ~s is negated.~%");

    static private final SubLList $list_alt32 = list(makeString("@param LITERAL consp\n   @return booleanp"), list(makeSymbol("MUST"), list(makeSymbol("CNOT"), list(makeSymbol("NEGATED?"), makeSymbol("LITERAL"))), makeString("DELETE-ATOM: ~s is negated.~%"), makeSymbol("LITERAL")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("POSITIVE-LITS"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED")))), list(makeSymbol("DICTIONARY-PUSHNEW"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED"), makeSymbol("LITERAL"), list(makeSymbol("FUNCTION"), EQUAL)), list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED"), list(makeSymbol("REMOVE"), makeSymbol("LITERAL"), makeSymbol("POSITIVE-LITS"), list(makeSymbol("FUNCTION"), EQUAL)))), list(RET, T));

    static private final SubLString $str_alt34$DELETE_ATOM___s_is_negated___ = makeString("DELETE-ATOM: ~s is negated.~%");

    public static SubLObject init_shop_basic_world_state_file() {
        return NIL;
    }

    public static SubLObject setup_shop_basic_world_state_file() {
        interfaces.new_interface(SHOP_WORLD_STATE, NIL, NIL, $list1);
        classes.subloop_new_class(SHOP_BASIC_WORLD_STATE, OBJECT, $list4, NIL, $list5);
        classes.class_set_implements_slot_listeners(SHOP_BASIC_WORLD_STATE, NIL);
        classes.subloop_note_class_initialization_function(SHOP_BASIC_WORLD_STATE, SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_WORLD_STATE_CLASS);
        classes.subloop_note_instance_initialization_function(SHOP_BASIC_WORLD_STATE, SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_WORLD_STATE_INSTANCE);
        subloop_reserved_initialize_shop_basic_world_state_class(SHOP_BASIC_WORLD_STATE);
        methods.methods_incorporate_instance_method(INITIALIZE, SHOP_BASIC_WORLD_STATE, $list15, NIL, $list16);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, INITIALIZE, SHOP_BASIC_WORLD_STATE_INITIALIZE_METHOD);
        methods.methods_incorporate_class_method(SAME_STATE_P, SHOP_BASIC_WORLD_STATE, $list20, $list21, $list22);
        methods.subloop_register_class_method(SHOP_BASIC_WORLD_STATE, SAME_STATE_P, SHOP_BASIC_WORLD_STATE_SAME_STATE_P_METHOD);
        methods.methods_incorporate_instance_method(ADD_ATOM, SHOP_BASIC_WORLD_STATE, $list20, $list26, $list27);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, ADD_ATOM, SHOP_BASIC_WORLD_STATE_ADD_ATOM_METHOD);
        methods.methods_incorporate_instance_method(DELETE_ATOM, SHOP_BASIC_WORLD_STATE, $list20, $list26, $list32);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, DELETE_ATOM, SHOP_BASIC_WORLD_STATE_DELETE_ATOM_METHOD);
        methods.methods_incorporate_instance_method(ADD_LITERAL, SHOP_BASIC_WORLD_STATE, $list20, $list26, $list37);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, ADD_LITERAL, SHOP_BASIC_WORLD_STATE_ADD_LITERAL_METHOD);
        methods.methods_incorporate_instance_method(DELETE_LITERAL, SHOP_BASIC_WORLD_STATE, $list20, $list26, $list40);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, DELETE_LITERAL, SHOP_BASIC_WORLD_STATE_DELETE_LITERAL_METHOD);
        methods.methods_incorporate_instance_method(ADD_LITERALS, SHOP_BASIC_WORLD_STATE, $list20, $list43, $list44);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, ADD_LITERALS, SHOP_BASIC_WORLD_STATE_ADD_LITERALS_METHOD);
        methods.methods_incorporate_instance_method(DELETE_LITERALS, SHOP_BASIC_WORLD_STATE, $list20, $list43, $list47);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, DELETE_LITERALS, SHOP_BASIC_WORLD_STATE_DELETE_LITERALS_METHOD);
        methods.methods_incorporate_instance_method(COPY_STATE, SHOP_BASIC_WORLD_STATE, $list20, NIL, $list50);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, COPY_STATE, SHOP_BASIC_WORLD_STATE_COPY_STATE_METHOD);
        methods.methods_incorporate_instance_method(NEXT_STATE, SHOP_BASIC_WORLD_STATE, $list20, NIL, $list54);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, NEXT_STATE, SHOP_BASIC_WORLD_STATE_NEXT_STATE_METHOD);
        methods.methods_incorporate_instance_method(POSITIVE_MATCHES_TO_PREDICATE, SHOP_BASIC_WORLD_STATE, $list15, $list57, $list58);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, POSITIVE_MATCHES_TO_PREDICATE, SHOP_BASIC_WORLD_STATE_POSITIVE_MATCHES_TO_PREDICATE_METHOD);
        methods.methods_incorporate_instance_method(NEGATIVE_MATCHES_TO_PREDICATE, SHOP_BASIC_WORLD_STATE, $list15, $list57, $list63);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, NEGATIVE_MATCHES_TO_PREDICATE, SHOP_BASIC_WORLD_STATE_NEGATIVE_MATCHES_TO_PREDICATE_METHOD);
        methods.methods_incorporate_instance_method(ASK_GROUND_LITERAL, SHOP_BASIC_WORLD_STATE, $list20, $list26, $list67);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, ASK_GROUND_LITERAL, SHOP_BASIC_WORLD_STATE_ASK_GROUND_LITERAL_METHOD);
        methods.methods_incorporate_instance_method(ASK_IF_NOT_GROUND_LITERAL, SHOP_BASIC_WORLD_STATE, $list20, $list26, $list71);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, ASK_IF_NOT_GROUND_LITERAL, SHOP_BASIC_WORLD_STATE_ASK_IF_NOT_GROUND_LITERAL_METHOD);
        methods.methods_incorporate_instance_method(ASK, SHOP_BASIC_WORLD_STATE, $list20, $list26, $list75);
        methods.subloop_register_instance_method(SHOP_BASIC_WORLD_STATE, ASK, SHOP_BASIC_WORLD_STATE_ASK_METHOD);
        return NIL;
    }

    static private final SubLList $list_alt37 = list(makeString("@param LITERAL listp"), list(RET, list(makeSymbol("ADD-ATOM"), makeSymbol("SELF"), makeSymbol("LITERAL"))));

    static private final SubLList $list_alt40 = list(makeString("@param LITERAL listp"), list(RET, list(makeSymbol("DELETE-ATOM"), makeSymbol("SELF"), makeSymbol("LITERAL"))));

    static private final SubLList $list_alt43 = list(makeSymbol("LITERALS"));

    static private final SubLList $list_alt44 = list(makeString("@param LITERALS listp"), list(makeSymbol("CDOLIST"), list(makeSymbol("LIT"), makeSymbol("LITERALS")), list(makeSymbol("ADD-ATOM"), makeSymbol("SELF"), makeSymbol("LIT"))), list(RET, NIL));

    static private final SubLList $list_alt47 = list(makeString("@param LITERALS listp"), list(makeSymbol("CDOLIST"), list(makeSymbol("LIT"), makeSymbol("LITERALS")), list(makeSymbol("DELETE-ATOM"), makeSymbol("SELF"), makeSymbol("LIT"))), list(RET, NIL));

    static private final SubLList $list_alt50 = list(makeString("@return shop-basic-world-state-p"), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-STATE"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-WORLD-STATE")), list(QUOTE, makeSymbol("SHOP-BASIC-WORLD-STATE"))))), list(makeSymbol("SET-SLOT"), makeSymbol("NEW-STATE"), list(QUOTE, makeSymbol("POSITIVE-LITERALS")), list(makeSymbol("COPY-DICTIONARY"), makeSymbol("POSITIVE-LITERALS"))), list(makeSymbol("SET-SLOT"), makeSymbol("NEW-STATE"), list(QUOTE, makeSymbol("NEGATIVE-LITERALS")), list(makeSymbol("COPY-DICTIONARY"), makeSymbol("NEGATIVE-LITERALS"))), list(RET, makeSymbol("NEW-STATE"))));

    static private final SubLList $list_alt54 = list(makeString("@return shop-basic-world-state-p"), list(RET, list(makeSymbol("INLINE-METHOD"), list(makeSymbol("COPY-STATE"), makeSymbol("SHOP-BASIC-WORLD-STATE")), makeSymbol("SELF"))));

    static private final SubLList $list_alt57 = list(makeSymbol("PRED"));

    static private final SubLList $list_alt58 = list(list(RET, list(makeSymbol("APPLY"), list(makeSymbol("FUNCTION"), makeSymbol("CCONCATENATE")), list(makeSymbol("APPLY-MB"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("POSITIVE-LITERALS"), makeSymbol("PRED")), makeSymbol("*SHOP-ASK-MULTIBINDING*")))));

    static private final SubLList $list_alt63 = list(list(RET, list(makeSymbol("APPLY"), list(makeSymbol("FUNCTION"), makeSymbol("CCONCATENATE")), list(makeSymbol("APPLY-MB"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("NEGATIVE-LITERALS"), makeSymbol("PRED")), makeSymbol("*SHOP-ASK-MULTIBINDING*")))));

    @Override
    public void declareFunctions() {
        declare_shop_basic_world_state_file();
    }

    @Override
    public void initializeVariables() {
        init_shop_basic_world_state_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_shop_basic_world_state_file();
    }

    static {
    }

    static private final SubLList $list_alt67 = list(list(makeSymbol("MUST"), list(makeSymbol("GROUND?"), makeSymbol("LITERAL")), makeString("ask-ground-literal: ~s is not ground.~%"), makeSymbol("LITERAL")), list(RET, list(makeSymbol("MEMBER"), makeSymbol("LITERAL"), list(makeSymbol("POSITIVE-MATCHES-TO-PREDICATE"), makeSymbol("SELF"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("FUNCTION"), EQUAL))));

    static private final SubLString $str_alt68$ask_ground_literal___s_is_not_gro = makeString("ask-ground-literal: ~s is not ground.~%");

    static private final SubLList $list_alt71 = list(list(makeSymbol("MUST"), list(makeSymbol("GROUND?"), makeSymbol("LITERAL")), makeString("ask-if-not-ground-literal: ~s is not ground.~%"), makeSymbol("LITERAL")), list(RET, list(makeSymbol("MEMBER"), makeSymbol("LITERAL"), list(makeSymbol("NEGATIVE-MATCHES-TO-PREDICATE"), makeSymbol("SELF"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("FUNCTION"), EQUAL))));

    static private final SubLString $str_alt72$ask_if_not_ground_literal___s_is_ = makeString("ask-if-not-ground-literal: ~s is not ground.~%");

    static private final SubLList $list_alt75 = list(makeString("@param LITERAL consp\n   @param MBINDING multibinding-p\n   @return binding-list-p"), list(makeSymbol("PWHEN"), list(makeSymbol("GROUND?"), makeSymbol("LITERAL")), list(makeSymbol("PIF"), list(makeSymbol("ASK-GROUND-LITERAL"), makeSymbol("SELF"), makeSymbol("LITERAL")), list(RET, list(QUOTE, list(list(cons(T, T))))), list(RET, NIL))), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("LITERAL-PREDICATE"), makeSymbol("LITERAL"))), list(makeSymbol("POSITIVE-MATCHES"), list(makeSymbol("POSITIVE-MATCHES-TO-PREDICATE"), makeSymbol("SELF"), makeSymbol("PRED"))), list(makeSymbol("BINDINGS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-LIT"), makeSymbol("POSITIVE-MATCHES")), list(makeSymbol("CLET"), list(list(makeSymbol("MGU"), list(makeSymbol("TERM-UNIFY"), makeSymbol("LITERAL"), makeSymbol("CUR-LIT")))), list(makeSymbol("PWHEN"), makeSymbol("MGU"), list(makeSymbol("CPUSH"), makeSymbol("MGU"), makeSymbol("BINDINGS"))))), list(RET, makeSymbol("BINDINGS"))));

    static private final SubLList $list_alt76 = list(list(cons(T, T)));
}

/**
 * Total time: 315 ms
 */
