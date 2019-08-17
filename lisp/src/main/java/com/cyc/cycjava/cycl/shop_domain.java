/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.cb_utilities.cb_form;
import static com.cyc.cycjava.cycl.constant_handles.constant_p;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.html_utilities.html_hr;
import static com.cyc.cycjava.cycl.html_utilities.html_newline;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryKey;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryValue;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.gethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorNextEntry;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.make_hash_table;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.releaseEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.sethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove_if;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.function_spec_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.hash_table_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.listp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.third;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import java.util.Iterator;
import java.util.Map;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class shop_domain extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new shop_domain();

 public static final String myName = "com.cyc.cycjava.cycl.shop_domain";


    // deflexical
    /**
     * Associaties for each kind of planner rule the appropriate slot on an instance
     * of SHOP-BASIC-DOMAIN-P that indexes the planner rules of that type for the
     * represented domain.
     */
    @LispMethod(comment = "Associaties for each kind of planner rule the appropriate slot on an instance\r\nof SHOP-BASIC-DOMAIN-P that indexes the planner rules of that type for the\r\nrepresented domain.\ndeflexical\nAssociaties for each kind of planner rule the appropriate slot on an instance\nof SHOP-BASIC-DOMAIN-P that indexes the planner rules of that type for the\nrepresented domain.")
    private static final SubLSymbol $shop_domain_planner_rule_slot_map$ = makeSymbol("*SHOP-DOMAIN-PLANNER-RULE-SLOT-MAP*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol SHOP_DOMAIN = makeSymbol("SHOP-DOMAIN");

    static private final SubLList $list1 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NAME"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SET-NAME"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-METHOD"), list(makeSymbol("METHOD")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-OPERATOR"), list(makeSymbol("OPERATOR")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PLANNER-RULES"), list(makeSymbol("TASK"), makeSymbol("TYPE")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-METHODS"), list(makeSymbol("TASK")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OPERATORS"), list(makeSymbol("TASK")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CONDITIONAL-OPERATORS"), list(makeSymbol("TASK")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT-ALL-METHODS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT-ALL-OPERATORS"), list(makeSymbol("STREAM")), makeKeyword("PUBLIC")) });

    private static final SubLSymbol SHOP_BASIC_DOMAIN = makeSymbol("SHOP-BASIC-DOMAIN");

    static private final SubLList $list4 = list(makeSymbol("SHOP-DOMAIN"));

    static private final SubLList $list5 = list(new SubLObject[]{ list(makeSymbol("METHODS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("PRECONDITIONS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("OPERATORS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("COND-OPERATORS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("SUFFICIENCIES"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("MT"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("SIMPLE-SITUATION-PREDS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZATION-SANITY-CHECK"), NIL, makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FLUENT-PREDICATE?"), list(makeSymbol("PRED")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADDABLE-PREDICATE?"), list(makeSymbol("PRED")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETEABLE-PREDICATE?"), list(makeSymbol("PRED")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RULE-TABLE-FOR-TYPE"), list(makeSymbol("TYPE")), makeKeyword("PRIVATE")) });

    private static final SubLSymbol SIMPLE_SITUATION_PREDS = makeSymbol("SIMPLE-SITUATION-PREDS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_DOMAIN_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-DOMAIN-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_DOMAIN_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-DOMAIN-INSTANCE");

    static private final SubLList $list18 = list(cons(makeSymbol("SHOP-METHOD-P"), makeSymbol("METHODS")), cons(makeSymbol("SHOP-PRECONDITION-P"), makeSymbol("PRECONDITIONS")), cons(makeSymbol("SHOP-OPERATOR-P"), makeSymbol("OPERATORS")), cons(makeSymbol("SHOP-SUFFICIENCY-CONDITION-P"), makeSymbol("SUFFICIENCIES")), cons(makeSymbol("SHOP-CONDITIONAL-EFFECT-P"), makeSymbol("COND-OPERATORS")));

    static private final SubLList $list20 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list21 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(makeSymbol("CSETQ"), makeSymbol("MT"), NIL), list(makeSymbol("CSETQ"), makeSymbol("METHODS"), list(makeSymbol("MAKE-HASH-TABLE"), makeInteger(100))), list(makeSymbol("CSETQ"), makeSymbol("OPERATORS"), list(makeSymbol("MAKE-HASH-TABLE"), makeInteger(100))), list(makeSymbol("CSETQ"), makeSymbol("PRECONDITIONS"), list(makeSymbol("MAKE-HASH-TABLE"), makeInteger(100))), list(makeSymbol("CSETQ"), makeSymbol("SUFFICIENCIES"), list(makeSymbol("MAKE-HASH-TABLE"), TEN_INTEGER)), list(makeSymbol("CSETQ"), makeSymbol("COND-OPERATORS"), list(makeSymbol("MAKE-HASH-TABLE"), TEN_INTEGER)), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym22$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_INITIALIZE_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-INITIALIZE-METHOD");

    static private final SubLList $list26 = list(makeSymbol("STREAM"), makeSymbol("DEPTH"));

    static private final SubLList $list27 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("PIF"), list(makeSymbol("INSTANCE-P"), makeSymbol("SELF")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<DOMAIN-~S:~S>"), makeSymbol("INSTANCE-NUMBER"), makeSymbol("MT")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<Malformed Instance>"))), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym28$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLString $str29$__DOMAIN__S__S_ = makeString("#<DOMAIN-~S:~S>");

    static private final SubLString $str30$__Malformed_Instance_ = makeString("#<Malformed Instance>");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_PRINT_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-PRINT-METHOD");

    static private final SubLList $list33 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list34 = list(list(RET, makeSymbol("MT")));

    static private final SubLSymbol $sym35$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_GET_NAME_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-GET-NAME-METHOD");

    static private final SubLString $str37$var_A = makeString("var~A");

    static private final SubLSymbol $sym38$HL_VAR_ = makeSymbol("HL-VAR?");

    private static final SubLSymbol RULE_TABLE_FOR_TYPE = makeSymbol("RULE-TABLE-FOR-TYPE");

    static private final SubLList $list40 = list(makeKeyword("PRIVATE"));

    static private final SubLList $list41 = list(makeSymbol("TYPE"));

    static private final SubLList $list42 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SLOTNAME"), list(makeSymbol("SHOP-DOMAIN-RULE-TABLE-FOR-TYPE"), makeSymbol("TYPE")))), list(RET, list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), makeSymbol("SLOTNAME")))));

    private static final SubLSymbol SHOP_BASIC_DOMAIN_RULE_TABLE_FOR_TYPE_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-RULE-TABLE-FOR-TYPE-METHOD");

    private static final SubLSymbol GET_PLANNER_RULES = makeSymbol("GET-PLANNER-RULES");

    static private final SubLList $list45 = list(makeSymbol("TASK"), makeSymbol("TYPE"));

    static private final SubLList $list46 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CHECK-TYPE"), makeSymbol("TYPE"), makeSymbol("FUNCTION-SPEC-P")), list(makeSymbol("CLET"), list(list(makeSymbol("RULE-TABLE"), list(makeSymbol("RULE-TABLE-FOR-TYPE"), makeSymbol("SELF"), makeSymbol("TYPE")))), list(makeSymbol("CHECK-TYPE"), makeSymbol("RULE-TABLE"), makeSymbol("HASH-TABLE-P")), list(makeSymbol("CLET"), list(list(makeSymbol("RULE-LIST"), list(makeSymbol("SHOP-FIND-ALL-MATCHING-RULES"), makeSymbol("TASK"), makeSymbol("RULE-TABLE"))), list(makeSymbol("UNIFIED-RULES"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-RULE"), makeSymbol("RULE-LIST")), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-PLANNER-RULE")), makeSymbol("CUR-RULE")), makeSymbol("TASK"))), list(makeSymbol("CPUSH"), makeSymbol("CUR-RULE"), makeSymbol("UNIFIED-RULES")))), list(RET, makeSymbol("UNIFIED-RULES")))));

    private static final SubLSymbol SHOP_BASIC_DOMAIN_GET_PLANNER_RULES_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-GET-PLANNER-RULES-METHOD");

    private static final SubLSymbol HTML_DISPLAY_ALL_RULES_OF_TYPE = makeSymbol("HTML-DISPLAY-ALL-RULES-OF-TYPE");

    static private final SubLList $list52 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("TYPE"), makeSymbol("FUNCTION-SPEC-P")), list(makeSymbol("CLET"), list(list(makeSymbol("RULE-TABLE"), list(makeSymbol("RULE-TABLE-FOR-TYPE"), makeSymbol("SELF"), makeSymbol("TYPE")))), list(makeSymbol("CHECK-TYPE"), makeSymbol("RULE-TABLE"), makeSymbol("HASH-TABLE-P")), list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("RULE-TABLE")))), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-RULES"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("CUR-NAME")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-RULE"), makeSymbol("CUR-RULES")), list(makeSymbol("FIM"), makeSymbol("CUR-RULE"), list(QUOTE, makeSymbol("HTML-DISPLAY")))), list(makeSymbol("HTML-HR")))), list(RET, NIL)));

    static private final SubLSymbol $sym53$STRING__ = makeSymbol("STRING<=");

    private static final SubLSymbol SHOP_TASK_PRED_STRING = makeSymbol("SHOP-TASK-PRED-STRING");

    static private final SubLList $list55 = cons(makeSymbol("CUR-NAME"), makeSymbol("CUR-RULES"));

    private static final SubLSymbol SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_RULES_OF_TYPE_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-RULES-OF-TYPE-METHOD");

    private static final SubLSymbol ADD_SUFFICIENT_CONDS = makeSymbol("ADD-SUFFICIENT-CONDS");

    static private final SubLList $list59 = list(makeSymbol("COND"));

    static private final SubLList $list60 = list(list(makeSymbol("MUST"), list(makeSymbol("SHOP-PLANNER-RULE-P"), makeSymbol("COND")), makeString("(ADD-SUFFICIENCY-COND ~S): ~S is not a valid SHOP-PLANNER-RULE-P object."), makeSymbol("SELF"), makeSymbol("COND")), list(makeSymbol("CPUSH"), list(makeSymbol("GETHASH"), list(makeSymbol("FIM"), makeSymbol("COND"), list(QUOTE, makeSymbol("PRED"))), makeSymbol("SUFFICIENCIES")), makeSymbol("COND")), list(RET, NIL));

    static private final SubLSymbol $sym61$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLString $str62$_ADD_SUFFICIENCY_COND__S____S_is_ = makeString("(ADD-SUFFICIENCY-COND ~S): ~S is not a valid SHOP-PLANNER-RULE-P object.");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_ADD_SUFFICIENT_CONDS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-ADD-SUFFICIENT-CONDS-METHOD");

    private static final SubLSymbol GET_SUFFICIENT_CONDS = makeSymbol("GET-SUFFICIENT-CONDS");

    static private final SubLList $list66 = list(makeSymbol("TASK"));

    static private final SubLList $list67 = list(makeString("@param TASK hl-formula-p"), list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("TASK-OP"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("TASK"))), list(makeSymbol("SCOND-LIST"), list(makeSymbol("GETHASH"), makeSymbol("TASK-OP"), makeSymbol("SUFFICIENCIES"))), list(makeSymbol("UNIFIED-SCONDS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-SCOND"), makeSymbol("SCOND-LIST")), list(makeSymbol("PUNLESS"), list(EQ, $FAIL, list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-SUFFICIENCY-COND")), makeSymbol("CUR-SCOND")), makeSymbol("TASK"))), list(makeSymbol("CPUSH"), makeSymbol("CUR-SCOND"), makeSymbol("UNIFIED-SCONDS")))), list(RET, makeSymbol("UNIFIED-SCONDS"))));

    static private final SubLSymbol $sym68$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_GET_SUFFICIENT_CONDS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-GET-SUFFICIENT-CONDS-METHOD");

    private static final SubLSymbol PRINT_ALL_SCONDS = makeSymbol("PRINT-ALL-SCONDS");

    static private final SubLList $list72 = list(list(makeSymbol("CDOHASH"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-SCONDS"), makeSymbol("SUFFICIENCIES")), list(makeSymbol("FORMAT"), T, makeString("~%~s"), makeSymbol("CUR-NAME")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-SCOND"), makeSymbol("CUR-SCONDS")), list(makeSymbol("DESCRIBE-INSTANCE"), makeSymbol("CUR-SCOND")))), list(RET, NIL));

    static private final SubLSymbol $sym73$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLString $str74$___s = makeString("~%~s");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_PRINT_ALL_SCONDS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-PRINT-ALL-SCONDS-METHOD");

    private static final SubLSymbol HTML_DISPLAY_ALL_SCONDS = makeSymbol("HTML-DISPLAY-ALL-SCONDS");

    static private final SubLList $list77 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("SUFFICIENCIES")))), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-SCONDS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("CUR-NAME")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-SCOND"), makeSymbol("CUR-SCONDS")), list(makeSymbol("FIM"), makeSymbol("CUR-SCOND"), list(QUOTE, makeSymbol("HTML-DISPLAY")))), list(makeSymbol("HTML-HR")))), list(RET, NIL));

    static private final SubLSymbol $sym78$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLList $list79 = cons(makeSymbol("CUR-NAME"), makeSymbol("CUR-SCONDS"));

    private static final SubLSymbol SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_SCONDS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-SCONDS-METHOD");

    private static final SubLSymbol ADD_METHOD = makeSymbol("ADD-METHOD");

    static private final SubLList $list82 = list(makeSymbol("METHOD"));

    static private final SubLList $list83 = list(list(makeSymbol("MUST"), list(makeSymbol("SHOP-METHOD-P"), makeSymbol("METHOD")), makeString("(ADD-METHOD ~S): ~S is not a valid SHOP-METHOD-P object."), makeSymbol("SELF"), makeSymbol("METHOD")), list(makeSymbol("CPUSH"), makeSymbol("METHOD"), list(makeSymbol("GETHASH"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("PRED"), makeSymbol("SHOP-BASIC-METHOD")), makeSymbol("METHOD")), makeSymbol("METHODS"))), list(RET, NIL));

    static private final SubLSymbol $sym84$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLString $str85$_ADD_METHOD__S____S_is_not_a_vali = makeString("(ADD-METHOD ~S): ~S is not a valid SHOP-METHOD-P object.");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_ADD_METHOD_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-ADD-METHOD-METHOD");

    private static final SubLSymbol GET_METHODS = makeSymbol("GET-METHODS");

    static private final SubLList $list88 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("METHOD-LIST"), list(makeSymbol("SHOP-FIND-ALL-MATCHING-RULES"), makeSymbol("TASK"), makeSymbol("METHODS"))), list(makeSymbol("UNIFIED-METHODS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-METHOD"), makeSymbol("METHOD-LIST")), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-METHOD")), makeSymbol("CUR-METHOD")), makeSymbol("TASK"))), list(makeSymbol("CPUSH"), makeSymbol("CUR-METHOD"), makeSymbol("UNIFIED-METHODS")))), list(RET, makeSymbol("UNIFIED-METHODS"))));

    static private final SubLSymbol $sym89$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_GET_METHODS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-GET-METHODS-METHOD");

    private static final SubLSymbol PRINT_ALL_METHODS = makeSymbol("PRINT-ALL-METHODS");

    static private final SubLList $list92 = list(list(makeSymbol("CDOHASH"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-METHODS"), makeSymbol("METHODS")), list(makeSymbol("FORMAT"), T, makeString("~%~s"), makeSymbol("CUR-NAME")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-METHOD"), makeSymbol("CUR-METHODS")), list(makeSymbol("DESCRIBE-INSTANCE"), makeSymbol("CUR-METHOD")))), list(RET, NIL));

    static private final SubLSymbol $sym93$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_PRINT_ALL_METHODS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-PRINT-ALL-METHODS-METHOD");

    private static final SubLSymbol HTML_DISPLAY_ALL_METHODS = makeSymbol("HTML-DISPLAY-ALL-METHODS");

    static private final SubLList $list96 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("METHODS")))), list(makeSymbol("PUNLESS"), makeSymbol("ALIST"), list(RET, NIL)), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-METHODS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("CUR-NAME")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-METHOD"), makeSymbol("CUR-METHODS")), list(makeSymbol("FIM"), makeSymbol("CUR-METHOD"), list(QUOTE, makeSymbol("HTML-DISPLAY"))), list(makeSymbol("HTML-NEWLINE"))), list(makeSymbol("HTML-HR"))), list(RET, NIL)));

    static private final SubLSymbol $sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLList $list98 = cons(makeSymbol("CUR-NAME"), makeSymbol("CUR-METHODS"));

    private static final SubLSymbol SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_METHODS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-METHODS-METHOD");

    private static final SubLSymbol ADD_OPERATOR = makeSymbol("ADD-OPERATOR");

    static private final SubLList $list101 = list(makeSymbol("OPERATOR"));

    static private final SubLList $list102 = list(list(makeSymbol("MUST"), list(makeSymbol("SHOP-OPERATOR-P"), makeSymbol("OPERATOR")), makeString("(ADD-OPERATOR ~S): ~S is not a valid SHOP-OPERATOR-P object."), makeSymbol("SELF"), makeSymbol("OPERATOR")), list(makeSymbol("CPUSH"), makeSymbol("OPERATOR"), list(makeSymbol("GETHASH"), list(makeSymbol("FIM"), makeSymbol("OPERATOR"), list(QUOTE, makeSymbol("NAME"))), makeSymbol("OPERATORS"))), list(RET, NIL));

    static private final SubLSymbol $sym103$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLString $str104$_ADD_OPERATOR__S____S_is_not_a_va = makeString("(ADD-OPERATOR ~S): ~S is not a valid SHOP-OPERATOR-P object.");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_ADD_OPERATOR_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-ADD-OPERATOR-METHOD");

    private static final SubLSymbol GET_OPERATORS = makeSymbol("GET-OPERATORS");

    static private final SubLList $list108 = list(makeString("@param TASK hl-formula-p\n   @return listp ;; of shop-operator-p"), list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("TASK"))), list(makeSymbol("OPERATOR-LIST"), list(makeSymbol("GETHASH"), makeSymbol("PRED"), makeSymbol("OPERATORS"))), list(makeSymbol("RESULTS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("OP"), makeSymbol("OPERATOR-LIST")), list(makeSymbol("CLET"), list(list(makeSymbol("CUR-HEAD"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-OPERATOR")), makeSymbol("OP")))), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("REST"), makeSymbol("CUR-HEAD")), list(makeSymbol("REST"), makeSymbol("TASK")))), list(makeSymbol("CPUSH"), makeSymbol("OP"), makeSymbol("RESULTS"))))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("RESULTS")))));

    static private final SubLSymbol $sym109$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_GET_OPERATORS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-GET-OPERATORS-METHOD");

    private static final SubLSymbol PRINT_ALL_OPERATORS = makeSymbol("PRINT-ALL-OPERATORS");

    static private final SubLList $list112 = list(makeSymbol("STREAM"));

    static private final SubLList $list113 = list(list(makeSymbol("CDOHASH"), list(makeSymbol("X"), makeSymbol("Y"), makeSymbol("OPERATORS")), list(new SubLObject[]{ makeSymbol("CLET"), list(list(makeSymbol("TEMP"), list(makeSymbol("FIRST"), makeSymbol("Y"))), list(makeSymbol("HEAD"), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("HEAD")))), list(makeSymbol("ADD-LITERALS"), list(makeSymbol("GET-SLOT"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("ADDS")))), list(makeSymbol("DELETE-LITERALS"), list(makeSymbol("GET-SLOT"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("DELETES")))), list(makeSymbol("CONDITIONAL-EFFECTS"), list(makeSymbol("GET-SLOT"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("CONDITIONAL-EFFECTS")))), list(makeSymbol("COST"), list(makeSymbol("GET-SHOP-BASIC-OPERATOR-COST"), makeSymbol("TEMP")))), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%~s"), makeSymbol("X")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Head: ~S"), makeSymbol("HEAD")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Add-literals:     ~S"), makeSymbol("ADD-LITERALS")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Delete-literals: ~S"), makeSymbol("DELETE-LITERALS")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Conditional Effects: ~s"), makeSymbol("CONDITIONAL-EFFECTS")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Cost: ~S"), makeSymbol("COST")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%")) })), list(RET, NIL));

    static private final SubLSymbol $sym114$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLString $str119$____Head___S = makeString("~%  Head: ~S");

    static private final SubLString $str120$____Add_literals_______S = makeString("~%  Add-literals:     ~S");

    static private final SubLString $str121$____Delete_literals___S = makeString("~%  Delete-literals: ~S");

    static private final SubLString $str122$____Conditional_Effects___s = makeString("~%  Conditional Effects: ~s");

    static private final SubLString $str123$____Cost___S = makeString("~%  Cost: ~S");

    static private final SubLString $str124$__ = makeString("~%");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_PRINT_ALL_OPERATORS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-PRINT-ALL-OPERATORS-METHOD");

    private static final SubLSymbol HTML_DISPLAY_ALL_OPERATORS = makeSymbol("HTML-DISPLAY-ALL-OPERATORS");

    static private final SubLList $list127 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("OPERATORS")))), list(makeSymbol("PUNLESS"), makeSymbol("ALIST"), list(RET, NIL)), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("PRED"), makeSymbol("OPS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("PRED")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-OP"), makeSymbol("OPS")), list(makeSymbol("FIM"), makeSymbol("CUR-OP"), list(QUOTE, makeSymbol("HTML-DISPLAY"))), list(makeSymbol("HTML-NEWLINE")))), list(RET, NIL)));

    static private final SubLSymbol $sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLList $list129 = cons(makeSymbol("PRED"), makeSymbol("OPS"));

    private static final SubLSymbol SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_OPERATORS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-OPERATORS-METHOD");

    private static final SubLSymbol GET_CONDITIONAL_OPERATORS = makeSymbol("GET-CONDITIONAL-OPERATORS");

    static private final SubLList $list132 = list(makeString("@param TASK hl-formula-p\n   @return listp ;; of shop-operator-p"), list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("TASK"))), list(makeSymbol("RESULTS"), NIL)), list(makeSymbol("MUST"), list(makeSymbol("FORT-P"), makeSymbol("PRED")), makeString("(get-conditional-operators shop-basic-domain) Error with ~a, ~a is not a fort-p"), makeSymbol("TASK"), makeSymbol("PRED")), list(makeSymbol("CDOLIST"), list(makeSymbol("OP"), list(makeSymbol("SHOP-FIND-ALL-MATCHING-RULES"), makeSymbol("TASK"), makeSymbol("COND-OPERATORS"))), list(makeSymbol("CLET"), list(list(makeSymbol("CUR-HEAD"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-PLANNER-RULE")), makeSymbol("OP")))), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("REST"), makeSymbol("CUR-HEAD")), list(makeSymbol("REST"), makeSymbol("TASK")))), list(makeSymbol("CPUSH"), makeSymbol("OP"), makeSymbol("RESULTS"))))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("RESULTS")))));

    static private final SubLSymbol $sym133$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    static private final SubLString $str134$_get_conditional_operators_shop_b = makeString("(get-conditional-operators shop-basic-domain) Error with ~a, ~a is not a fort-p");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_GET_CONDITIONAL_OPERATORS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-GET-CONDITIONAL-OPERATORS-METHOD");

    private static final SubLSymbol HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS = makeSymbol("HTML-DISPLAY-ALL-CONDITIONAL-OPERATORS");

    static private final SubLList $list137 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("COND-OPERATORS")))), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING"))), list(makeSymbol("DO-ALIST"), list(makeSymbol("PRED"), makeSymbol("OPS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("PRED")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-OP"), makeSymbol("OPS")), list(makeSymbol("FIM"), makeSymbol("CUR-OP"), list(QUOTE, makeSymbol("HTML-DISPLAY"))), list(makeSymbol("HTML-NEWLINE")))), list(RET, NIL)));

    static private final SubLSymbol $sym138$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-BASIC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS_METHOD = makeSymbol("SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-CONDITIONAL-OPERATORS-METHOD");

    static private final SubLList $list141 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-METHOD"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-AXIOM"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-PRECONDITION"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-EFFECT"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-CONDITIONAL-EFFECT"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-SUFFICIENT-COND"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-PLAN-ASSERTIONS-LIST"), list(makeSymbol("ASSERTIONS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-PLAN-ASSERTIONS"), list(makeSymbol("MT")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INIT-DOMAIN-WITH-RULES"), list(makeSymbol("RULES"), makeSymbol("MT")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POPULATE-DOMAIN"), list(makeSymbol("DOMAIN")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INIT-SIMPLE-SITUATION-PREDS"), list(makeSymbol("MT")), makeKeyword("PRIVATE")) });

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SHOP_CYC_DOMAIN_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SHOP-CYC-DOMAIN-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SHOP_CYC_DOMAIN_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SHOP-CYC-DOMAIN-INSTANCE");

    static private final SubLList $list144 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol SHOP_CYC_DOMAIN_INITIALIZE_METHOD = makeSymbol("SHOP-CYC-DOMAIN-INITIALIZE-METHOD");

    static private final SubLList $list146 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("PIF"), list(makeSymbol("INSTANCE-P"), makeSymbol("SELF")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<SHOP-CYC-DOMAIN-~S:~S>"), makeSymbol("INSTANCE-NUMBER"), makeSymbol("MT")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<Malformed Instance>"))), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym147$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    static private final SubLString $str148$__SHOP_CYC_DOMAIN__S__S_ = makeString("#<SHOP-CYC-DOMAIN-~S:~S>");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PRINT_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PRINT-METHOD");

    private static final SubLSymbol PROCESS_PLAN_ASSERTIONS_LIST = makeSymbol("PROCESS-PLAN-ASSERTIONS-LIST");

    static private final SubLList $list151 = list(makeSymbol("ASSERTIONS"));

    static private final SubLList $list152 = list(makeString("@param ASSERTIONS listp ;; of assertion-p\n   process a list of ASSERTIONS, so that they can be entered into the domain\n   description. ASSERTIONS is a list of elements satisfying ASSERTION-P. \n   Returns T."), list(new SubLObject[]{ makeSymbol("CLET"), list(list(makeSymbol("PRECONDS"), NIL), list(makeSymbol("EFFECTS"), NIL), list(makeSymbol("PRE-METHODS"), NIL), list(makeSymbol("METHODS"), NIL), list(makeSymbol("AXIOMS"), NIL), list(makeSymbol("SUFFICIENT-CONDS"), NIL), list(makeSymbol("COND-EFFECTS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("ASSERTION"), makeSymbol("ASSERTIONS")), list(makeSymbol("PCOND"), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("preconditionForMethod")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("PRE-METHODS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("methodForAction")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("METHODS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("preconditionFor-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("PRECONDS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("effectOfAction-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("EFFECTS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("sufficientFor-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("SUFFICIENT-CONDS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("effectOfActionIf-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("COND-EFFECTS"))), list(T, list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("AXIOMS"))))), list(makeSymbol("PWHEN"), makeSymbol("*SHOP-USE-PRECONDITION-FOR-METHOD?*"), list(makeSymbol("PROGN"), list(makeSymbol("FORMAT"), T, makeString("******************* --> using preconditionForMethod~%~%")), list(makeSymbol("CSETQ"), makeSymbol("METHODS"), makeSymbol("PRE-METHODS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("PRECOND"), makeSymbol("PRECONDS")), list(makeSymbol("PROCESS-PRECONDITION"), makeSymbol("SELF"), makeSymbol("PRECOND"))), list(makeSymbol("CDOLIST"), list(makeSymbol("EFFECT"), makeSymbol("EFFECTS")), list(makeSymbol("PROCESS-EFFECT"), makeSymbol("SELF"), makeSymbol("EFFECT"))), list(makeSymbol("CDOLIST"), list(makeSymbol("COND-EFFECT"), makeSymbol("COND-EFFECTS")), list(makeSymbol("PROCESS-CONDITIONAL-EFFECT"), makeSymbol("SELF"), makeSymbol("COND-EFFECT"))), list(makeSymbol("CDOLIST"), list(makeSymbol("METHOD"), makeSymbol("METHODS")), list(makeSymbol("PROCESS-METHOD"), makeSymbol("SELF"), makeSymbol("METHOD"))), list(makeSymbol("CDOLIST"), list(makeSymbol("SCOND"), makeSymbol("SUFFICIENT-CONDS")), list(makeSymbol("PROCESS-SUFFICIENT-COND"), makeSymbol("SELF"), makeSymbol("SCOND"))) }), list(RET, T));

    static private final SubLSymbol $sym153$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");





    private static final SubLObject $$preconditionFor_Props = reader_make_constant_shell("preconditionFor-Props");

    private static final SubLObject $$effectOfAction_Props = reader_make_constant_shell("effectOfAction-Props");

    private static final SubLObject $$sufficientFor_Props = reader_make_constant_shell("sufficientFor-Props");

    private static final SubLObject $$effectOfActionIf_Props = reader_make_constant_shell("effectOfActionIf-Props");

    static private final SubLString $str160$________________________using_pre = makeString("******************* --> using preconditionForMethod~%~%");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PROCESS_PLAN_ASSERTIONS_LIST_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PROCESS-PLAN-ASSERTIONS-LIST-METHOD");

    private static final SubLSymbol GATHER_PLANNING_ASSERTIONS = makeSymbol("GATHER-PLANNING-ASSERTIONS");

    static private final SubLList $list163 = list(makeSymbol("DOMAIN-MT"));

    static private final SubLList $list164 = list(makeString("@param DOMAIN-MT hlmt-p\n   Returns the el form of assertions in MT which are likely planning assertions."), list(makeSymbol("CLET"), list(list(makeSymbol("ASS-SET"), list(makeSymbol("NEW-SET")))), list(makeSymbol("WITH-MT"), makeSymbol("DOMAIN-MT"), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-PRED"), makeSymbol("*SHOP-DOMAIN-DEFINITION-PREDICATES*")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-ASS"), list(makeSymbol("GATHER-PREDICATE-RULE-INDEX"), makeSymbol("CUR-PRED"), makeKeyword("POS"))), list(makeSymbol("SET-ADD"), makeSymbol("CUR-ASS"), makeSymbol("ASS-SET")))), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-ASS"), list(makeSymbol("GATHER-PREDICATE-EXTENT-INDEX"), reader_make_constant_shell("preconditionForMethod"))), list(makeSymbol("SET-ADD"), makeSymbol("CUR-ASS"), makeSymbol("ASS-SET")))), list(RET, list(makeSymbol("SET-ELEMENT-LIST"), makeSymbol("ASS-SET")))));

    private static final SubLSymbol SHOP_CYC_DOMAIN_GATHER_PLANNING_ASSERTIONS_METHOD = makeSymbol("SHOP-CYC-DOMAIN-GATHER-PLANNING-ASSERTIONS-METHOD");

    private static final SubLSymbol INIT_SIMPLE_SITUATION_PREDS = makeSymbol("INIT-SIMPLE-SITUATION-PREDS");

    static private final SubLList $list169 = list(list(makeSymbol("MUST"), list(makeSymbol("HLMT-P"), makeSymbol("DOMAIN-MT")), makeString("(init-simple-situation-preds shop-cyc-domain): ~s is not hlmt-p")), list(makeSymbol("CLET"), list(list(makeSymbol("SIMPLE-PREDS"), list(makeSymbol("ALL-FORT-INSTANCES"), reader_make_constant_shell("SimpleSituationPredicate"), makeSymbol("DOMAIN-MT")))), list(makeSymbol("CSETQ"), makeSymbol("SIMPLE-SITUATION-PREDS"), list(makeSymbol("CONSTRUCT-SET-FROM-LIST"), makeSymbol("SIMPLE-PREDS"))), list(RET, NIL)));

    static private final SubLSymbol $sym170$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    static private final SubLString $str171$_init_simple_situation_preds_shop = makeString("(init-simple-situation-preds shop-cyc-domain): ~s is not hlmt-p");



    private static final SubLSymbol SHOP_CYC_DOMAIN_INIT_SIMPLE_SITUATION_PREDS_METHOD = makeSymbol("SHOP-CYC-DOMAIN-INIT-SIMPLE-SITUATION-PREDS-METHOD");

    private static final SubLSymbol INIT_DOMAIN_WITH_RULES = makeSymbol("INIT-DOMAIN-WITH-RULES");

    static private final SubLList $list175 = list(makeSymbol("RULES"), makeSymbol("DOMAIN-MT"));

    static private final SubLList $list176 = list(makeString("This function is used primarily for creating domains from the proof checker."), list(makeSymbol("CSETQ"), makeSymbol("MT"), makeSymbol("DOMAIN-MT")), list(makeSymbol("PROCESS-PLAN-ASSERTIONS-LIST"), makeSymbol("SELF"), makeSymbol("RULES")), list(makeSymbol("INIT-SIMPLE-SITUATION-PREDS"), makeSymbol("SELF"), makeSymbol("DOMAIN-MT")), list(RET, T));

    static private final SubLSymbol $sym177$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_CYC_DOMAIN_INIT_DOMAIN_WITH_RULES_METHOD = makeSymbol("SHOP-CYC-DOMAIN-INIT-DOMAIN-WITH-RULES-METHOD");

    private static final SubLSymbol PROCESS_PLAN_ASSERTIONS = makeSymbol("PROCESS-PLAN-ASSERTIONS");

    static private final SubLList $list180 = list(makeString("Reads planning domain assertions in microtheory mt and\n   translates them into a planning domain data structure\n   for use by the planner."), list(makeSymbol("PWHEN"), makeSymbol("MT"), list(makeSymbol("INITIALIZE"), makeSymbol("SELF"))), list(makeSymbol("CSETQ"), makeSymbol("MT"), makeSymbol("DOMAIN-MT")), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTIONS"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("GATHER-PLANNING-ASSERTIONS"), makeSymbol("SHOP-CYC-DOMAIN")), makeSymbol("SELF"), makeSymbol("DOMAIN-MT")))), list(makeSymbol("PROCESS-PLAN-ASSERTIONS-LIST"), makeSymbol("SELF"), makeSymbol("ASSERTIONS")), list(makeSymbol("INIT-SIMPLE-SITUATION-PREDS"), makeSymbol("SELF"), makeSymbol("DOMAIN-MT"))), list(RET, T));

    static private final SubLSymbol $sym181$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PROCESS_PLAN_ASSERTIONS_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PROCESS-PLAN-ASSERTIONS-METHOD");

    private static final SubLSymbol PROCESS_METHOD = makeSymbol("PROCESS-METHOD");

    static private final SubLList $list184 = list(makeSymbol("ASSERTION"));

    static private final SubLList $list185 = list(makeString("@param ASSERION assertion-p\n   turns #$methodForAction formula into a planner method datastructure."), list(makeSymbol("CMULTIPLE-VALUE-BIND"), list(makeSymbol("METHOD-HEAD"), makeSymbol("PRECONDITIONS"), makeSymbol("DECOMPOSITION")), list(makeSymbol("SHOP-EXTRACT-METHOD-FROM-ASSERTION"), makeSymbol("ASSERTION")), list(makeSymbol("CLET"), list(list(makeSymbol("METHOD-NAME"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("METHOD-HEAD")))), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-METHOD"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-METHOD")), list(QUOTE, makeSymbol("SHOP-BASIC-METHOD"))))), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-METHOD")), makeSymbol("NEW-METHOD"), makeSymbol("ASSERTION"), makeSymbol("METHOD-HEAD"), makeSymbol("PRECONDITIONS"), makeSymbol("DECOMPOSITION")), list(makeSymbol("CPUSH"), makeSymbol("NEW-METHOD"), list(makeSymbol("GETHASH"), makeSymbol("METHOD-NAME"), makeSymbol("METHODS")))), list(RET, T))));

    static private final SubLSymbol $sym186$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_METHOD = makeSymbol("SHOP-BASIC-METHOD");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PROCESS_METHOD_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PROCESS-METHOD-METHOD");

    private static final SubLSymbol PROCESS_PRECONDITION = makeSymbol("PROCESS-PRECONDITION");

    static private final SubLList $list190 = list(makeString("@param ASSERTION assertion-p\n   Turn el precondition formulas into  preconditions for action operators."), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTION-FORM"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("ACTION-HEAD"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("DECOMPOSITION"), list(makeSymbol("LIST"), list(makeSymbol("LIST"), makeKeyword("PRIMITIVE"), makeSymbol("ACTION-HEAD")))), list(makeSymbol("CONDITIONS"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("NEW-PRECONDITION"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-PRECONDITION")), list(QUOTE, makeSymbol("SHOP-BASIC-PRECONDITION"))))), list(makeSymbol("CSETQ"), makeSymbol("CONDITIONS"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("CONDITIONS"))), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-PRECONDITION")), makeSymbol("NEW-PRECONDITION"), makeSymbol("ASSERTION"), makeSymbol("ACTION-HEAD"), makeSymbol("CONDITIONS"), makeSymbol("DECOMPOSITION")), list(makeSymbol("CPUSH"), makeSymbol("NEW-PRECONDITION"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-HEAD")), makeSymbol("PRECONDITIONS"))), list(RET, T)));

    static private final SubLSymbol $sym191$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_PRECONDITION = makeSymbol("SHOP-BASIC-PRECONDITION");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PROCESS_PRECONDITION_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PROCESS-PRECONDITION-METHOD");

    private static final SubLSymbol PROCESS_EFFECT = makeSymbol("PROCESS-EFFECT");

    static private final SubLList $list196 = list(makeString("@param ASSERTION assertion-p \n   Turn el effect statements into add and delete lists for action operators."), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTION-FORM"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("ACTION-HEAD"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("EFFECTS"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("NEW-OPERATOR"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-OPERATOR")), list(QUOTE, makeSymbol("SHOP-BASIC-OPERATOR"))))), list(makeSymbol("CSETQ"), makeSymbol("EFFECTS"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("EFFECTS"))), list(makeSymbol("CMULTIPLE-VALUE-BIND"), list(makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST")), list(makeSymbol("SHOP-SEPARATE-EFFECT-LIST"), makeSymbol("EFFECTS")), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-OPERATOR")), makeSymbol("NEW-OPERATOR"), makeSymbol("ASSERTION"), makeSymbol("ACTION-HEAD"), makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST")), list(makeSymbol("CPUSH"), makeSymbol("NEW-OPERATOR"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-HEAD")), makeSymbol("OPERATORS"))))), list(RET, T));

    static private final SubLSymbol $sym197$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PROCESS_EFFECT_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PROCESS-EFFECT-METHOD");

    private static final SubLSymbol PROCESS_SUFFICIENT_COND = makeSymbol("PROCESS-SUFFICIENT-COND");

    static private final SubLList $list201 = list(makeString("@param ASSERTION assertion-p"), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTION-FORM"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("PRECONDITION"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("ACTION-FORM"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("NEW-SCOND"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-SUFFICIENCY-COND")), list(QUOTE, makeSymbol("SHOP-BASIC-SUFFICIENCY-COND"))))), list(makeSymbol("CSETQ"), makeSymbol("PRECONDITION"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("PRECONDITION"))), list(makeSymbol("FIM"), makeSymbol("NEW-SCOND"), list(QUOTE, makeSymbol("SET-HEAD")), makeSymbol("ACTION-FORM")), list(makeSymbol("FIM"), makeSymbol("NEW-SCOND"), list(QUOTE, makeSymbol("SET-COND-FORMULA")), makeSymbol("PRECONDITION")), list(makeSymbol("FIM"), makeSymbol("NEW-SCOND"), list(QUOTE, makeSymbol("SET-ASSERTIONS")), list(makeSymbol("LIST"), makeSymbol("ASSERTION"))), list(makeSymbol("CPUSH"), makeSymbol("NEW-SCOND"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-FORM")), makeSymbol("SUFFICIENCIES"))), list(RET, NIL)));

    static private final SubLSymbol $sym202$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    private static final SubLSymbol SHOP_BASIC_SUFFICIENCY_COND = makeSymbol("SHOP-BASIC-SUFFICIENCY-COND");

    private static final SubLSymbol SET_HEAD = makeSymbol("SET-HEAD");

    private static final SubLSymbol SET_COND_FORMULA = makeSymbol("SET-COND-FORMULA");

    private static final SubLSymbol SET_ASSERTIONS = makeSymbol("SET-ASSERTIONS");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PROCESS_SUFFICIENT_COND_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PROCESS-SUFFICIENT-COND-METHOD");

    private static final SubLSymbol PROCESS_CONDITIONAL_EFFECT = makeSymbol("PROCESS-CONDITIONAL-EFFECT");

    static private final SubLList $list209 = list(makeString("@param ASSERTION assertion-p"), list(makeSymbol("CLET"), list(list(makeSymbol("FORMULA"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("PRED"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("FORMULA"))), list(makeSymbol("ACTION-FORMULA"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("FORMULA"))), list(makeSymbol("ACTION-HEAD"), makeSymbol("ACTION-FORMULA")), list(makeSymbol("CONDITION"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("FORMULA"))), list(makeSymbol("EFFECTS"), list(makeSymbol("FORMULA-ARG3"), makeSymbol("FORMULA"))), list(makeSymbol("COND-EFFECT-OPERATOR"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-CONDITIONAL-EFFECT")), list(QUOTE, makeSymbol("SHOP-BASIC-CONDITIONAL-EFFECT"))))), list(makeSymbol("MUST"), list(EQ, makeSymbol("PRED"), reader_make_constant_shell("effectOfActionIf-Props")), makeString("process-conditional-effect:~a is not at conditional effect assertion."), makeSymbol("ASSERTION")), list(makeSymbol("CSETQ"), makeSymbol("EFFECTS"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("EFFECTS"))), list(makeSymbol("CSETQ"), makeSymbol("CONDITION"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("CONDITION"))), list(makeSymbol("CMULTIPLE-VALUE-BIND"), list(makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST")), list(makeSymbol("SHOP-SEPARATE-EFFECT-LIST"), makeSymbol("EFFECTS")), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-CONDITIONAL-EFFECT")), makeSymbol("COND-EFFECT-OPERATOR"), makeSymbol("ACTION-HEAD"), makeSymbol("CONDITION"), makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST"), makeSymbol("ASSERTION")), list(makeSymbol("CPUSH"), makeSymbol("COND-EFFECT-OPERATOR"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-HEAD")), makeSymbol("COND-OPERATORS"))))), list(RET, NIL));

    static private final SubLSymbol $sym210$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SHOP-CYC-DOMAIN-METHOD");

    static private final SubLString $str212$process_conditional_effect__a_is_ = makeString("process-conditional-effect:~a is not at conditional effect assertion.");

    private static final SubLSymbol SHOP_CYC_DOMAIN_PROCESS_CONDITIONAL_EFFECT_METHOD = makeSymbol("SHOP-CYC-DOMAIN-PROCESS-CONDITIONAL-EFFECT-METHOD");



    private static final SubLSymbol SHOP_METHOD_FORMULA_P = makeSymbol("SHOP-METHOD-FORMULA-P");

    private static final SubLSymbol NEGATE = makeSymbol("NEGATE");







    // Definitions
    public static final SubLObject shop_domain_p_alt(SubLObject v_shop_domain) {
        return interfaces.subloop_instanceof_interface(v_shop_domain, SHOP_DOMAIN);
    }

    // Definitions
    public static SubLObject shop_domain_p(final SubLObject v_shop_domain) {
        return interfaces.subloop_instanceof_interface(v_shop_domain, SHOP_DOMAIN);
    }

    public static final SubLObject get_shop_basic_domain_simple_situation_preds_alt(SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, SEVEN_INTEGER, SIMPLE_SITUATION_PREDS);
    }

    public static SubLObject get_shop_basic_domain_simple_situation_preds(final SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, SEVEN_INTEGER, SIMPLE_SITUATION_PREDS);
    }

    public static final SubLObject set_shop_basic_domain_simple_situation_preds_alt(SubLObject shop_basic_domain, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, SEVEN_INTEGER, SIMPLE_SITUATION_PREDS);
    }

    public static SubLObject set_shop_basic_domain_simple_situation_preds(final SubLObject shop_basic_domain, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, SEVEN_INTEGER, SIMPLE_SITUATION_PREDS);
    }

    public static final SubLObject get_shop_basic_domain_mt_alt(SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, SIX_INTEGER, MT);
    }

    public static SubLObject get_shop_basic_domain_mt(final SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, SIX_INTEGER, MT);
    }

    public static final SubLObject set_shop_basic_domain_mt_alt(SubLObject shop_basic_domain, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, SIX_INTEGER, MT);
    }

    public static SubLObject set_shop_basic_domain_mt(final SubLObject shop_basic_domain, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, SIX_INTEGER, MT);
    }

    public static final SubLObject get_shop_basic_domain_sufficiencies_alt(SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, FIVE_INTEGER, SUFFICIENCIES);
    }

    public static SubLObject get_shop_basic_domain_sufficiencies(final SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, FIVE_INTEGER, SUFFICIENCIES);
    }

    public static final SubLObject set_shop_basic_domain_sufficiencies_alt(SubLObject shop_basic_domain, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, FIVE_INTEGER, SUFFICIENCIES);
    }

    public static SubLObject set_shop_basic_domain_sufficiencies(final SubLObject shop_basic_domain, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, FIVE_INTEGER, SUFFICIENCIES);
    }

    public static final SubLObject get_shop_basic_domain_cond_operators_alt(SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, FOUR_INTEGER, COND_OPERATORS);
    }

    public static SubLObject get_shop_basic_domain_cond_operators(final SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, FOUR_INTEGER, COND_OPERATORS);
    }

    public static final SubLObject set_shop_basic_domain_cond_operators_alt(SubLObject shop_basic_domain, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, FOUR_INTEGER, COND_OPERATORS);
    }

    public static SubLObject set_shop_basic_domain_cond_operators(final SubLObject shop_basic_domain, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, FOUR_INTEGER, COND_OPERATORS);
    }

    public static final SubLObject get_shop_basic_domain_operators_alt(SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, THREE_INTEGER, OPERATORS);
    }

    public static SubLObject get_shop_basic_domain_operators(final SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, THREE_INTEGER, OPERATORS);
    }

    public static final SubLObject set_shop_basic_domain_operators_alt(SubLObject shop_basic_domain, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, THREE_INTEGER, OPERATORS);
    }

    public static SubLObject set_shop_basic_domain_operators(final SubLObject shop_basic_domain, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, THREE_INTEGER, OPERATORS);
    }

    public static final SubLObject get_shop_basic_domain_preconditions_alt(SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, TWO_INTEGER, PRECONDITIONS);
    }

    public static SubLObject get_shop_basic_domain_preconditions(final SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, TWO_INTEGER, PRECONDITIONS);
    }

    public static final SubLObject set_shop_basic_domain_preconditions_alt(SubLObject shop_basic_domain, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, TWO_INTEGER, PRECONDITIONS);
    }

    public static SubLObject set_shop_basic_domain_preconditions(final SubLObject shop_basic_domain, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, TWO_INTEGER, PRECONDITIONS);
    }

    public static final SubLObject get_shop_basic_domain_methods_alt(SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, ONE_INTEGER, METHODS);
    }

    public static SubLObject get_shop_basic_domain_methods(final SubLObject shop_basic_domain) {
        return classes.subloop_get_access_protected_instance_slot(shop_basic_domain, ONE_INTEGER, METHODS);
    }

    public static final SubLObject set_shop_basic_domain_methods_alt(SubLObject shop_basic_domain, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, ONE_INTEGER, METHODS);
    }

    public static SubLObject set_shop_basic_domain_methods(final SubLObject shop_basic_domain, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(shop_basic_domain, value, ONE_INTEGER, METHODS);
    }

    public static final SubLObject subloop_reserved_initialize_shop_basic_domain_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_shop_basic_domain_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_shop_basic_domain_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, METHODS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, PRECONDITIONS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, COND_OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SUFFICIENCIES, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, MT, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SIMPLE_SITUATION_PREDS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_shop_basic_domain_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, METHODS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, PRECONDITIONS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, COND_OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SUFFICIENCIES, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, MT, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SIMPLE_SITUATION_PREDS, NIL);
        return NIL;
    }

    public static final SubLObject shop_basic_domain_p_alt(SubLObject shop_basic_domain) {
        return classes.subloop_instanceof_class(shop_basic_domain, SHOP_BASIC_DOMAIN);
    }

    public static SubLObject shop_basic_domain_p(final SubLObject shop_basic_domain) {
        return classes.subloop_instanceof_class(shop_basic_domain, SHOP_BASIC_DOMAIN);
    }

    public static final SubLObject shop_domain_rule_table_for_type_alt(SubLObject type) {
        return list_utilities.alist_lookup($shop_domain_planner_rule_slot_map$.getGlobalValue(), type, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject shop_domain_rule_table_for_type(final SubLObject type) {
        return list_utilities.alist_lookup($shop_domain_planner_rule_slot_map$.getGlobalValue(), type, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject shop_basic_domain_initialize_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject mt = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_mt(self);
            SubLObject sufficiencies = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_sufficiencies(self);
            SubLObject cond_operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_cond_operators(self);
            SubLObject operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_operators(self);
            SubLObject preconditions = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_preconditions(self);
            SubLObject v_methods = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_methods(self);
            try {
                try {
                    object.object_initialize_method(self);
                    mt = NIL;
                    v_methods = make_hash_table($int$100, UNPROVIDED, UNPROVIDED);
                    operators = make_hash_table($int$100, UNPROVIDED, UNPROVIDED);
                    preconditions = make_hash_table($int$100, UNPROVIDED, UNPROVIDED);
                    sufficiencies = make_hash_table(TEN_INTEGER, UNPROVIDED, UNPROVIDED);
                    cond_operators = make_hash_table(TEN_INTEGER, UNPROVIDED, UNPROVIDED);
                    sublisp_throw($sym22$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_mt(self, mt);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_sufficiencies(self, sufficiencies);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_cond_operators(self, cond_operators);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_operators(self, operators);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_preconditions(self, preconditions);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_methods(self, v_methods);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym22$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_initialize_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        SubLObject mt = get_shop_basic_domain_mt(self);
        SubLObject sufficiencies = get_shop_basic_domain_sufficiencies(self);
        SubLObject cond_operators = get_shop_basic_domain_cond_operators(self);
        SubLObject operators = get_shop_basic_domain_operators(self);
        SubLObject preconditions = get_shop_basic_domain_preconditions(self);
        SubLObject v_methods = get_shop_basic_domain_methods(self);
        try {
            thread.throwStack.push($sym22$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                object.object_initialize_method(self);
                mt = NIL;
                v_methods = make_hash_table($int$100, UNPROVIDED, UNPROVIDED);
                operators = make_hash_table($int$100, UNPROVIDED, UNPROVIDED);
                preconditions = make_hash_table($int$100, UNPROVIDED, UNPROVIDED);
                sufficiencies = make_hash_table(TEN_INTEGER, UNPROVIDED, UNPROVIDED);
                cond_operators = make_hash_table(TEN_INTEGER, UNPROVIDED, UNPROVIDED);
                sublisp_throw($sym22$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_mt(self, mt);
                    set_shop_basic_domain_sufficiencies(self, sufficiencies);
                    set_shop_basic_domain_cond_operators(self, cond_operators);
                    set_shop_basic_domain_operators(self, operators);
                    set_shop_basic_domain_preconditions(self, preconditions);
                    set_shop_basic_domain_methods(self, v_methods);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym22$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_print_method_alt(SubLObject self, SubLObject stream, SubLObject depth) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject mt = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_mt(self);
            SubLObject instance_number = object.get_object_instance_number(self);
            try {
                try {
                    if (NIL != subloop_structures.instance_p(self)) {
                        format(stream, $str_alt29$__DOMAIN__S__S_, instance_number, mt);
                    } else {
                        format(stream, $str_alt30$__Malformed_Instance_);
                    }
                    sublisp_throw($sym28$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_mt(self, mt);
                            object.set_object_instance_number(self, instance_number);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym28$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_print_method(final SubLObject self, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject mt = get_shop_basic_domain_mt(self);
        final SubLObject instance_number = object.get_object_instance_number(self);
        try {
            thread.throwStack.push($sym28$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                if (NIL != subloop_structures.instance_p(self)) {
                    format(stream, $str29$__DOMAIN__S__S_, instance_number, mt);
                } else {
                    format(stream, $str30$__Malformed_Instance_);
                }
                sublisp_throw($sym28$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_mt(self, mt);
                    object.set_object_instance_number(self, instance_number);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym28$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_get_name_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject mt = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_mt(self);
            try {
                try {
                    sublisp_throw($sym35$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, mt);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_mt(self, mt);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym35$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_get_name_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject mt = get_shop_basic_domain_mt(self);
        try {
            thread.throwStack.push($sym35$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                sublisp_throw($sym35$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, mt);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_mt(self, mt);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym35$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    /**
     * Returns a string associated with ENTRY so that
     * it can be sorted with its fellow entries alphabetically.
     *
     * @return stringp
     */
    @LispMethod(comment = "Returns a string associated with ENTRY so that\r\nit can be sorted with its fellow entries alphabetically.\r\n\r\n@return stringp\nReturns a string associated with ENTRY so that\nit can be sorted with its fellow entries alphabetically.")
    public static final SubLObject shop_task_pred_string_alt(SubLObject entry) {
        if (entry.isList()) {
            {
                SubLObject item = entry.first();
                if (NIL != constant_p(item)) {
                    return constants_high.constant_name(item);
                } else {
                    if (NIL != variables.variable_p(item)) {
                        return format(NIL, $str_alt37$var_A, variables.variable_id(item));
                    }
                }
            }
        }
        return string_utilities.$empty_string$.getGlobalValue();
    }

    /**
     * Returns a string associated with ENTRY so that
     * it can be sorted with its fellow entries alphabetically.
     *
     * @return stringp
     */
    @LispMethod(comment = "Returns a string associated with ENTRY so that\r\nit can be sorted with its fellow entries alphabetically.\r\n\r\n@return stringp\nReturns a string associated with ENTRY so that\nit can be sorted with its fellow entries alphabetically.")
    public static SubLObject shop_task_pred_string(final SubLObject entry) {
        if (entry.isList()) {
            final SubLObject item = entry.first();
            if (NIL != constant_p(item)) {
                return constants_high.constant_name(item);
            }
            if (NIL != variables.variable_p(item)) {
                return format(NIL, $str37$var_A, variables.variable_id(item));
            }
        }
        return string_utilities.$empty_string$.getGlobalValue();
    }

    public static final SubLObject shop_find_all_matching_rules_alt(SubLObject task, SubLObject hash_table) {
        {
            SubLObject keys = hash_table_utilities.hash_table_keys(hash_table);
            SubLObject var_keys = list_utilities.remove_if_not($sym38$HL_VAR_, keys, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject values = NIL;
            SubLObject cdolist_list_var = var_keys;
            SubLObject item = NIL;
            for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                values = append(gethash(item, hash_table, UNPROVIDED), values);
            }
            return append(gethash(cycl_utilities.formula_operator(task), hash_table, UNPROVIDED), values);
        }
    }

    public static SubLObject shop_find_all_matching_rules(final SubLObject task, final SubLObject hash_table) {
        final SubLObject keys = hash_table_utilities.hash_table_keys(hash_table);
        final SubLObject var_keys = list_utilities.remove_if_not($sym38$HL_VAR_, keys, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        SubLObject values = NIL;
        SubLObject cdolist_list_var = var_keys;
        SubLObject item = NIL;
        item = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            values = append(gethash(item, hash_table, UNPROVIDED), values);
            cdolist_list_var = cdolist_list_var.rest();
            item = cdolist_list_var.first();
        } 
        return append(gethash(cycl_utilities.formula_operator(task), hash_table, UNPROVIDED), values);
    }

    public static final SubLObject shop_basic_domain_rule_table_for_type_method_alt(SubLObject self, SubLObject type) {
        {
            SubLObject slotname = com.cyc.cycjava.cycl.shop_domain.shop_domain_rule_table_for_type(type);
            return instances.get_slot(self, slotname);
        }
    }

    public static SubLObject shop_basic_domain_rule_table_for_type_method(final SubLObject self, final SubLObject type) {
        final SubLObject slotname = shop_domain_rule_table_for_type(type);
        return instances.get_slot(self, slotname);
    }

    public static final SubLObject shop_basic_domain_get_planner_rules_method_alt(SubLObject self, SubLObject task, SubLObject type) {
        SubLTrampolineFile.checkType(task, LISTP);
        SubLTrampolineFile.checkType(type, FUNCTION_SPEC_P);
        {
            SubLObject rule_table = com.cyc.cycjava.cycl.shop_domain.shop_basic_domain_rule_table_for_type_method(self, type);
            SubLTrampolineFile.checkType(rule_table, HASH_TABLE_P);
            {
                SubLObject rule_list = com.cyc.cycjava.cycl.shop_domain.shop_find_all_matching_rules(task, rule_table);
                SubLObject unified_rules = NIL;
                SubLObject cdolist_list_var = rule_list;
                SubLObject cur_rule = NIL;
                for (cur_rule = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_rule = cdolist_list_var.first()) {
                    if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(shop_datastructures.shop_basic_planner_rule_head_method(cur_rule), task))) {
                        unified_rules = cons(cur_rule, unified_rules);
                    }
                }
                return unified_rules;
            }
        }
    }

    public static SubLObject shop_basic_domain_get_planner_rules_method(final SubLObject self, final SubLObject task, final SubLObject type) {
        assert NIL != listp(task) : "! listp(task) " + ("Types.listp(task) " + "CommonSymbols.NIL != Types.listp(task) ") + task;
        assert NIL != function_spec_p(type) : "! function_spec_p(type) " + ("Types.function_spec_p(type) " + "CommonSymbols.NIL != Types.function_spec_p(type) ") + type;
        final SubLObject rule_table = shop_basic_domain_rule_table_for_type_method(self, type);
        assert NIL != hash_table_p(rule_table) : "! hash_table_p(rule_table) " + ("Types.hash_table_p(rule_table) " + "CommonSymbols.NIL != Types.hash_table_p(rule_table) ") + rule_table;
        final SubLObject rule_list = shop_find_all_matching_rules(task, rule_table);
        SubLObject unified_rules = NIL;
        SubLObject cdolist_list_var = rule_list;
        SubLObject cur_rule = NIL;
        cur_rule = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(shop_datastructures.shop_basic_planner_rule_head_method(cur_rule), task))) {
                unified_rules = cons(cur_rule, unified_rules);
            }
            cdolist_list_var = cdolist_list_var.rest();
            cur_rule = cdolist_list_var.first();
        } 
        return unified_rules;
    }

    public static final SubLObject shop_basic_domain_html_display_all_rules_of_type_method_alt(SubLObject self, SubLObject type) {
        SubLTrampolineFile.checkType(type, FUNCTION_SPEC_P);
        {
            SubLObject rule_table = com.cyc.cycjava.cycl.shop_domain.shop_basic_domain_rule_table_for_type_method(self, type);
            SubLTrampolineFile.checkType(rule_table, HASH_TABLE_P);
            {
                SubLObject alist = hash_table_utilities.hash_table_to_alist(rule_table);
                alist = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                {
                    SubLObject cdolist_list_var = alist;
                    SubLObject cons = NIL;
                    for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                        {
                            SubLObject datum = cons;
                            SubLObject current = datum;
                            SubLObject cur_name = NIL;
                            SubLObject cur_rules = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt55);
                            cur_name = current.first();
                            current = current.rest();
                            cur_rules = current;
                            cb_form(cur_name, UNPROVIDED, UNPROVIDED);
                            html_newline(UNPROVIDED);
                            {
                                SubLObject cdolist_list_var_1 = cur_rules;
                                SubLObject cur_rule = NIL;
                                for (cur_rule = cdolist_list_var_1.first(); NIL != cdolist_list_var_1; cdolist_list_var_1 = cdolist_list_var_1.rest() , cur_rule = cdolist_list_var_1.first()) {
                                    methods.funcall_instance_method_with_0_args(cur_rule, HTML_DISPLAY);
                                }
                            }
                            html_hr(UNPROVIDED, UNPROVIDED);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject shop_basic_domain_html_display_all_rules_of_type_method(final SubLObject self, final SubLObject type) {
        assert NIL != function_spec_p(type) : "! function_spec_p(type) " + ("Types.function_spec_p(type) " + "CommonSymbols.NIL != Types.function_spec_p(type) ") + type;
        final SubLObject rule_table = shop_basic_domain_rule_table_for_type_method(self, type);
        assert NIL != hash_table_p(rule_table) : "! hash_table_p(rule_table) " + ("Types.hash_table_p(rule_table) " + "CommonSymbols.NIL != Types.hash_table_p(rule_table) ") + rule_table;
        SubLObject alist = hash_table_utilities.hash_table_to_alist(rule_table);
        SubLObject cdolist_list_var;
        alist = cdolist_list_var = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
        SubLObject cons = NIL;
        cons = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = cons;
            SubLObject cur_name = NIL;
            SubLObject cur_rules = NIL;
            destructuring_bind_must_consp(current, datum, $list55);
            cur_name = current.first();
            current = cur_rules = current.rest();
            cb_form(cur_name, UNPROVIDED, UNPROVIDED);
            html_newline(UNPROVIDED);
            SubLObject cdolist_list_var_$1 = cur_rules;
            SubLObject cur_rule = NIL;
            cur_rule = cdolist_list_var_$1.first();
            while (NIL != cdolist_list_var_$1) {
                methods.funcall_instance_method_with_0_args(cur_rule, HTML_DISPLAY);
                cdolist_list_var_$1 = cdolist_list_var_$1.rest();
                cur_rule = cdolist_list_var_$1.first();
            } 
            html_hr(UNPROVIDED, UNPROVIDED);
            cdolist_list_var = cdolist_list_var.rest();
            cons = cdolist_list_var.first();
        } 
        return NIL;
    }

    public static final SubLObject shop_basic_domain_add_sufficient_conds_method_alt(SubLObject self, SubLObject cond) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_domain_method = NIL;
                SubLObject sufficiencies = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_sufficiencies(self);
                try {
                    try {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if (NIL == shop_datastructures.shop_planner_rule_p(cond)) {
                                Errors.error($str_alt62$_ADD_SUFFICIENCY_COND__S____S_is_, self, cond);
                            }
                        }
                        cond = cons(gethash(methods.funcall_instance_method_with_0_args(cond, PRED), sufficiencies, UNPROVIDED), cond);
                        sublisp_throw($sym61$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_sufficiencies(self, sufficiencies);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym61$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_basic_domain_method;
            }
        }
    }

    public static SubLObject shop_basic_domain_add_sufficient_conds_method(final SubLObject self, SubLObject cond) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject sufficiencies = get_shop_basic_domain_sufficiencies(self);
        try {
            thread.throwStack.push($sym61$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == shop_datastructures.shop_planner_rule_p(cond))) {
                    Errors.error($str62$_ADD_SUFFICIENCY_COND__S____S_is_, self, cond);
                }
                cond = cons(gethash(methods.funcall_instance_method_with_0_args(cond, PRED), sufficiencies, UNPROVIDED), cond);
                sublisp_throw($sym61$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_sufficiencies(self, sufficiencies);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym61$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    /**
     *
     *
     * @param TASK
    hl-formula-p
     * 		
     */
    @LispMethod(comment = "@param TASK\nhl-formula-p")
    public static final SubLObject shop_basic_domain_get_sufficient_conds_method_alt(SubLObject self, SubLObject task) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject sufficiencies = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_sufficiencies(self);
            try {
                try {
                    SubLTrampolineFile.checkType(task, LISTP);
                    {
                        SubLObject task_op = cycl_utilities.formula_operator(task);
                        SubLObject scond_list = gethash(task_op, sufficiencies, UNPROVIDED);
                        SubLObject unified_sconds = NIL;
                        SubLObject cdolist_list_var = scond_list;
                        SubLObject cur_scond = NIL;
                        for (cur_scond = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_scond = cdolist_list_var.first()) {
                            if ($FAIL != shop_datastructures.shop_unify(shop_datastructures.shop_basic_planner_rule_head_method(cur_scond), task)) {
                                unified_sconds = cons(cur_scond, unified_sconds);
                            }
                        }
                        sublisp_throw($sym68$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, unified_sconds);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_sufficiencies(self, sufficiencies);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym68$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    /**
     *
     *
     * @param TASK
    hl-formula-p
     * 		
     */
    @LispMethod(comment = "@param TASK\nhl-formula-p")
    public static SubLObject shop_basic_domain_get_sufficient_conds_method(final SubLObject self, final SubLObject task) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject sufficiencies = get_shop_basic_domain_sufficiencies(self);
        try {
            thread.throwStack.push($sym68$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                assert NIL != listp(task) : "! listp(task) " + ("Types.listp(task) " + "CommonSymbols.NIL != Types.listp(task) ") + task;
                final SubLObject task_op = cycl_utilities.formula_operator(task);
                final SubLObject scond_list = gethash(task_op, sufficiencies, UNPROVIDED);
                SubLObject unified_sconds = NIL;
                SubLObject cdolist_list_var = scond_list;
                SubLObject cur_scond = NIL;
                cur_scond = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if ($FAIL != shop_datastructures.shop_unify(shop_datastructures.shop_basic_planner_rule_head_method(cur_scond), task)) {
                        unified_sconds = cons(cur_scond, unified_sconds);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    cur_scond = cdolist_list_var.first();
                } 
                sublisp_throw($sym68$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, unified_sconds);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_sufficiencies(self, sufficiencies);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym68$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_print_all_sconds_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject sufficiencies = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_sufficiencies(self);
            try {
                try {
                    {
                        SubLObject cur_name = NIL;
                        SubLObject cur_sconds = NIL;
                        {
                            final Iterator cdohash_iterator = getEntrySetIterator(sufficiencies);
                            try {
                                while (iteratorHasNext(cdohash_iterator)) {
                                    final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                                    cur_name = getEntryKey(cdohash_entry);
                                    cur_sconds = getEntryValue(cdohash_entry);
                                    format(T, $str_alt74$___s, cur_name);
                                    {
                                        SubLObject cdolist_list_var = cur_sconds;
                                        SubLObject cur_scond = NIL;
                                        for (cur_scond = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_scond = cdolist_list_var.first()) {
                                            object.describe_instance(cur_scond);
                                        }
                                    }
                                } 
                            } finally {
                                releaseEntrySetIterator(cdohash_iterator);
                            }
                        }
                        sublisp_throw($sym73$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_sufficiencies(self, sufficiencies);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym73$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_print_all_sconds_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject sufficiencies = get_shop_basic_domain_sufficiencies(self);
        try {
            thread.throwStack.push($sym73$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                SubLObject cur_name = NIL;
                SubLObject cur_sconds = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(sufficiencies);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        cur_name = getEntryKey(cdohash_entry);
                        cur_sconds = getEntryValue(cdohash_entry);
                        format(T, $str74$___s, cur_name);
                        SubLObject cdolist_list_var = cur_sconds;
                        SubLObject cur_scond = NIL;
                        cur_scond = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            object.describe_instance(cur_scond);
                            cdolist_list_var = cdolist_list_var.rest();
                            cur_scond = cdolist_list_var.first();
                        } 
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
                sublisp_throw($sym73$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_sufficiencies(self, sufficiencies);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym73$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_html_display_all_sconds_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject sufficiencies = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_sufficiencies(self);
            try {
                try {
                    {
                        SubLObject alist = hash_table_utilities.hash_table_to_alist(sufficiencies);
                        alist = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                        {
                            SubLObject cdolist_list_var = alist;
                            SubLObject cons = NIL;
                            for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                                {
                                    SubLObject datum = cons;
                                    SubLObject current = datum;
                                    SubLObject cur_name = NIL;
                                    SubLObject cur_sconds = NIL;
                                    destructuring_bind_must_consp(current, datum, $list_alt79);
                                    cur_name = current.first();
                                    current = current.rest();
                                    cur_sconds = current;
                                    cb_form(cur_name, UNPROVIDED, UNPROVIDED);
                                    html_newline(UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_2 = cur_sconds;
                                        SubLObject cur_scond = NIL;
                                        for (cur_scond = cdolist_list_var_2.first(); NIL != cdolist_list_var_2; cdolist_list_var_2 = cdolist_list_var_2.rest() , cur_scond = cdolist_list_var_2.first()) {
                                            methods.funcall_instance_method_with_0_args(cur_scond, HTML_DISPLAY);
                                        }
                                    }
                                    html_hr(UNPROVIDED, UNPROVIDED);
                                }
                            }
                        }
                        sublisp_throw($sym78$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_sufficiencies(self, sufficiencies);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym78$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_html_display_all_sconds_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject sufficiencies = get_shop_basic_domain_sufficiencies(self);
        try {
            thread.throwStack.push($sym78$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                SubLObject alist = hash_table_utilities.hash_table_to_alist(sufficiencies);
                SubLObject cdolist_list_var;
                alist = cdolist_list_var = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                SubLObject cons = NIL;
                cons = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject current;
                    final SubLObject datum = current = cons;
                    SubLObject cur_name = NIL;
                    SubLObject cur_sconds = NIL;
                    destructuring_bind_must_consp(current, datum, $list79);
                    cur_name = current.first();
                    current = cur_sconds = current.rest();
                    cb_form(cur_name, UNPROVIDED, UNPROVIDED);
                    html_newline(UNPROVIDED);
                    SubLObject cdolist_list_var_$2 = cur_sconds;
                    SubLObject cur_scond = NIL;
                    cur_scond = cdolist_list_var_$2.first();
                    while (NIL != cdolist_list_var_$2) {
                        methods.funcall_instance_method_with_0_args(cur_scond, HTML_DISPLAY);
                        cdolist_list_var_$2 = cdolist_list_var_$2.rest();
                        cur_scond = cdolist_list_var_$2.first();
                    } 
                    html_hr(UNPROVIDED, UNPROVIDED);
                    cdolist_list_var = cdolist_list_var.rest();
                    cons = cdolist_list_var.first();
                } 
                sublisp_throw($sym78$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_sufficiencies(self, sufficiencies);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym78$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_add_method_method_alt(SubLObject self, SubLObject method) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_domain_method = NIL;
                SubLObject v_methods = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_methods(self);
                try {
                    try {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if (NIL == shop_datastructures.shop_method_p(method)) {
                                Errors.error($str_alt85$_ADD_METHOD__S____S_is_not_a_vali, self, method);
                            }
                        }
                        sethash(shop_datastructures.shop_basic_planner_rule_pred_method(method), v_methods, cons(method, gethash(shop_datastructures.shop_basic_planner_rule_pred_method(method), v_methods, UNPROVIDED)));
                        sublisp_throw($sym84$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_methods(self, v_methods);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym84$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_basic_domain_method;
            }
        }
    }

    public static SubLObject shop_basic_domain_add_method_method(final SubLObject self, final SubLObject method) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject v_methods = get_shop_basic_domain_methods(self);
        try {
            thread.throwStack.push($sym84$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == shop_datastructures.shop_method_p(method))) {
                    Errors.error($str85$_ADD_METHOD__S____S_is_not_a_vali, self, method);
                }
                sethash(shop_datastructures.shop_basic_planner_rule_pred_method(method), v_methods, cons(method, gethash(shop_datastructures.shop_basic_planner_rule_pred_method(method), v_methods, UNPROVIDED)));
                sublisp_throw($sym84$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_methods(self, v_methods);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym84$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_get_methods_method_alt(SubLObject self, SubLObject task) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject v_methods = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_methods(self);
            try {
                try {
                    SubLTrampolineFile.checkType(task, LISTP);
                    {
                        SubLObject method_list = com.cyc.cycjava.cycl.shop_domain.shop_find_all_matching_rules(task, v_methods);
                        SubLObject unified_methods = NIL;
                        SubLObject cdolist_list_var = method_list;
                        SubLObject cur_method = NIL;
                        for (cur_method = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_method = cdolist_list_var.first()) {
                            if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(shop_datastructures.shop_basic_planner_rule_head_method(cur_method), task))) {
                                unified_methods = cons(cur_method, unified_methods);
                            }
                        }
                        sublisp_throw($sym89$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, unified_methods);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_methods(self, v_methods);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym89$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_get_methods_method(final SubLObject self, final SubLObject task) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject v_methods = get_shop_basic_domain_methods(self);
        try {
            thread.throwStack.push($sym89$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                assert NIL != listp(task) : "! listp(task) " + ("Types.listp(task) " + "CommonSymbols.NIL != Types.listp(task) ") + task;
                final SubLObject method_list = shop_find_all_matching_rules(task, v_methods);
                SubLObject unified_methods = NIL;
                SubLObject cdolist_list_var = method_list;
                SubLObject cur_method = NIL;
                cur_method = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(shop_datastructures.shop_basic_planner_rule_head_method(cur_method), task))) {
                        unified_methods = cons(cur_method, unified_methods);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    cur_method = cdolist_list_var.first();
                } 
                sublisp_throw($sym89$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, unified_methods);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_methods(self, v_methods);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym89$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_print_all_methods_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject v_methods = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_methods(self);
            try {
                try {
                    {
                        SubLObject cur_name = NIL;
                        SubLObject cur_methods = NIL;
                        {
                            final Iterator cdohash_iterator = getEntrySetIterator(v_methods);
                            try {
                                while (iteratorHasNext(cdohash_iterator)) {
                                    final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                                    cur_name = getEntryKey(cdohash_entry);
                                    cur_methods = getEntryValue(cdohash_entry);
                                    format(T, $str_alt74$___s, cur_name);
                                    {
                                        SubLObject cdolist_list_var = cur_methods;
                                        SubLObject cur_method = NIL;
                                        for (cur_method = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_method = cdolist_list_var.first()) {
                                            object.describe_instance(cur_method);
                                        }
                                    }
                                } 
                            } finally {
                                releaseEntrySetIterator(cdohash_iterator);
                            }
                        }
                        sublisp_throw($sym93$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_methods(self, v_methods);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym93$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_print_all_methods_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject v_methods = get_shop_basic_domain_methods(self);
        try {
            thread.throwStack.push($sym93$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                SubLObject cur_name = NIL;
                SubLObject cur_methods = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(v_methods);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        cur_name = getEntryKey(cdohash_entry);
                        cur_methods = getEntryValue(cdohash_entry);
                        format(T, $str74$___s, cur_name);
                        SubLObject cdolist_list_var = cur_methods;
                        SubLObject cur_method = NIL;
                        cur_method = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            object.describe_instance(cur_method);
                            cdolist_list_var = cdolist_list_var.rest();
                            cur_method = cdolist_list_var.first();
                        } 
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
                sublisp_throw($sym93$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_methods(self, v_methods);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym93$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_html_display_all_methods_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject v_methods = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_methods(self);
            try {
                try {
                    {
                        SubLObject alist = hash_table_utilities.hash_table_to_alist(v_methods);
                        if (NIL == alist) {
                            sublisp_throw($sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                        }
                        alist = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                        {
                            SubLObject cdolist_list_var = alist;
                            SubLObject cons = NIL;
                            for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                                {
                                    SubLObject datum = cons;
                                    SubLObject current = datum;
                                    SubLObject cur_name = NIL;
                                    SubLObject cur_methods = NIL;
                                    destructuring_bind_must_consp(current, datum, $list_alt98);
                                    cur_name = current.first();
                                    current = current.rest();
                                    cur_methods = current;
                                    cb_form(cur_name, UNPROVIDED, UNPROVIDED);
                                    html_newline(UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_3 = cur_methods;
                                        SubLObject cur_method = NIL;
                                        for (cur_method = cdolist_list_var_3.first(); NIL != cdolist_list_var_3; cdolist_list_var_3 = cdolist_list_var_3.rest() , cur_method = cdolist_list_var_3.first()) {
                                            methods.funcall_instance_method_with_0_args(cur_method, HTML_DISPLAY);
                                            html_newline(UNPROVIDED);
                                        }
                                    }
                                    html_hr(UNPROVIDED, UNPROVIDED);
                                }
                            }
                        }
                        sublisp_throw($sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_methods(self, v_methods);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_html_display_all_methods_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject v_methods = get_shop_basic_domain_methods(self);
        try {
            thread.throwStack.push($sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                SubLObject alist = hash_table_utilities.hash_table_to_alist(v_methods);
                if (NIL == alist) {
                    sublisp_throw($sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                }
                SubLObject cdolist_list_var;
                alist = cdolist_list_var = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                SubLObject cons = NIL;
                cons = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject current;
                    final SubLObject datum = current = cons;
                    SubLObject cur_name = NIL;
                    SubLObject cur_methods = NIL;
                    destructuring_bind_must_consp(current, datum, $list98);
                    cur_name = current.first();
                    current = cur_methods = current.rest();
                    cb_form(cur_name, UNPROVIDED, UNPROVIDED);
                    html_newline(UNPROVIDED);
                    SubLObject cdolist_list_var_$3 = cur_methods;
                    SubLObject cur_method = NIL;
                    cur_method = cdolist_list_var_$3.first();
                    while (NIL != cdolist_list_var_$3) {
                        methods.funcall_instance_method_with_0_args(cur_method, HTML_DISPLAY);
                        html_newline(UNPROVIDED);
                        cdolist_list_var_$3 = cdolist_list_var_$3.rest();
                        cur_method = cdolist_list_var_$3.first();
                    } 
                    html_hr(UNPROVIDED, UNPROVIDED);
                    cdolist_list_var = cdolist_list_var.rest();
                    cons = cdolist_list_var.first();
                } 
                sublisp_throw($sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_methods(self, v_methods);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym97$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_add_operator_method_alt(SubLObject self, SubLObject operator) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_domain_method = NIL;
                SubLObject operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_operators(self);
                try {
                    try {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if (NIL == shop_datastructures.shop_operator_p(operator)) {
                                Errors.error($str_alt104$_ADD_OPERATOR__S____S_is_not_a_va, self, operator);
                            }
                        }
                        sethash(methods.funcall_instance_method_with_0_args(operator, NAME), operators, cons(operator, gethash(methods.funcall_instance_method_with_0_args(operator, NAME), operators, UNPROVIDED)));
                        sublisp_throw($sym103$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_operators(self, operators);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym103$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_basic_domain_method;
            }
        }
    }

    public static SubLObject shop_basic_domain_add_operator_method(final SubLObject self, final SubLObject operator) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject operators = get_shop_basic_domain_operators(self);
        try {
            thread.throwStack.push($sym103$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == shop_datastructures.shop_operator_p(operator))) {
                    Errors.error($str104$_ADD_OPERATOR__S____S_is_not_a_va, self, operator);
                }
                sethash(methods.funcall_instance_method_with_0_args(operator, NAME), operators, cons(operator, gethash(methods.funcall_instance_method_with_0_args(operator, NAME), operators, UNPROVIDED)));
                sublisp_throw($sym103$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_operators(self, operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym103$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    /**
     *
     *
     * @param TASK
    hl-formula-p
     * 		
     * @return listp ;; of shop-operator-p
     */
    @LispMethod(comment = "@param TASK\nhl-formula-p\r\n\t\t\r\n@return listp ;; of shop-operator-p")
    public static final SubLObject shop_basic_domain_get_operators_method_alt(SubLObject self, SubLObject task) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_operators(self);
            try {
                try {
                    SubLTrampolineFile.checkType(task, LISTP);
                    {
                        SubLObject pred = cycl_utilities.formula_operator(task);
                        SubLObject operator_list = gethash(pred, operators, UNPROVIDED);
                        SubLObject results = NIL;
                        SubLObject cdolist_list_var = operator_list;
                        SubLObject op = NIL;
                        for (op = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , op = cdolist_list_var.first()) {
                            {
                                SubLObject cur_head = shop_datastructures.shop_basic_planner_rule_head_method(op);
                                if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(cur_head.rest(), task.rest()))) {
                                    results = cons(op, results);
                                }
                            }
                        }
                        sublisp_throw($sym109$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, nreverse(results));
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_operators(self, operators);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym109$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    /**
     *
     *
     * @param TASK
    hl-formula-p
     * 		
     * @return listp ;; of shop-operator-p
     */
    @LispMethod(comment = "@param TASK\nhl-formula-p\r\n\t\t\r\n@return listp ;; of shop-operator-p")
    public static SubLObject shop_basic_domain_get_operators_method(final SubLObject self, final SubLObject task) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject operators = get_shop_basic_domain_operators(self);
        try {
            thread.throwStack.push($sym109$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                assert NIL != listp(task) : "! listp(task) " + ("Types.listp(task) " + "CommonSymbols.NIL != Types.listp(task) ") + task;
                final SubLObject pred = cycl_utilities.formula_operator(task);
                final SubLObject operator_list = gethash(pred, operators, UNPROVIDED);
                SubLObject results = NIL;
                SubLObject cdolist_list_var = operator_list;
                SubLObject op = NIL;
                op = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject cur_head = shop_datastructures.shop_basic_planner_rule_head_method(op);
                    if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(cur_head.rest(), task.rest()))) {
                        results = cons(op, results);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    op = cdolist_list_var.first();
                } 
                sublisp_throw($sym109$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, nreverse(results));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_operators(self, operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym109$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_print_all_operators_method_alt(SubLObject self, SubLObject stream) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_operators(self);
            try {
                try {
                    {
                        SubLObject x = NIL;
                        SubLObject y = NIL;
                        {
                            final Iterator cdohash_iterator = getEntrySetIterator(operators);
                            try {
                                while (iteratorHasNext(cdohash_iterator)) {
                                    final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                                    x = getEntryKey(cdohash_entry);
                                    y = getEntryValue(cdohash_entry);
                                    {
                                        SubLObject temp = y.first();
                                        SubLObject head = methods.funcall_instance_method_with_0_args(temp, HEAD);
                                        SubLObject add_literals = instances.get_slot(temp, ADDS);
                                        SubLObject delete_literals = instances.get_slot(temp, DELETES);
                                        SubLObject conditional_effects = instances.get_slot(temp, CONDITIONAL_EFFECTS);
                                        SubLObject cost = shop_datastructures.get_shop_basic_operator_cost(temp);
                                        format(stream, $str_alt74$___s, x);
                                        format(stream, $str_alt119$____Head___S, head);
                                        format(stream, $str_alt120$____Add_literals_______S, add_literals);
                                        format(stream, $str_alt121$____Delete_literals___S, delete_literals);
                                        format(stream, $str_alt122$____Conditional_Effects___s, conditional_effects);
                                        format(stream, $str_alt123$____Cost___S, cost);
                                        format(stream, $str_alt124$__);
                                    }
                                } 
                            } finally {
                                releaseEntrySetIterator(cdohash_iterator);
                            }
                        }
                        sublisp_throw($sym114$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_operators(self, operators);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym114$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_print_all_operators_method(final SubLObject self, final SubLObject stream) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject operators = get_shop_basic_domain_operators(self);
        try {
            thread.throwStack.push($sym114$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                SubLObject x = NIL;
                SubLObject y = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(operators);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        x = getEntryKey(cdohash_entry);
                        y = getEntryValue(cdohash_entry);
                        final SubLObject temp = y.first();
                        final SubLObject head = methods.funcall_instance_method_with_0_args(temp, HEAD);
                        final SubLObject add_literals = instances.get_slot(temp, ADDS);
                        final SubLObject delete_literals = instances.get_slot(temp, DELETES);
                        final SubLObject conditional_effects = instances.get_slot(temp, CONDITIONAL_EFFECTS);
                        final SubLObject cost = shop_datastructures.get_shop_basic_operator_cost(temp);
                        format(stream, $str74$___s, x);
                        format(stream, $str119$____Head___S, head);
                        format(stream, $str120$____Add_literals_______S, add_literals);
                        format(stream, $str121$____Delete_literals___S, delete_literals);
                        format(stream, $str122$____Conditional_Effects___s, conditional_effects);
                        format(stream, $str123$____Cost___S, cost);
                        format(stream, $str124$__);
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
                sublisp_throw($sym114$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_operators(self, operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym114$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_html_display_all_operators_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_operators(self);
            try {
                try {
                    {
                        SubLObject alist = hash_table_utilities.hash_table_to_alist(operators);
                        if (NIL == alist) {
                            sublisp_throw($sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                        }
                        alist = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                        {
                            SubLObject cdolist_list_var = alist;
                            SubLObject cons = NIL;
                            for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                                {
                                    SubLObject datum = cons;
                                    SubLObject current = datum;
                                    SubLObject pred = NIL;
                                    SubLObject ops = NIL;
                                    destructuring_bind_must_consp(current, datum, $list_alt129);
                                    pred = current.first();
                                    current = current.rest();
                                    ops = current;
                                    cb_form(pred, UNPROVIDED, UNPROVIDED);
                                    html_newline(UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_4 = ops;
                                        SubLObject cur_op = NIL;
                                        for (cur_op = cdolist_list_var_4.first(); NIL != cdolist_list_var_4; cdolist_list_var_4 = cdolist_list_var_4.rest() , cur_op = cdolist_list_var_4.first()) {
                                            methods.funcall_instance_method_with_0_args(cur_op, HTML_DISPLAY);
                                            html_newline(UNPROVIDED);
                                        }
                                    }
                                }
                            }
                        }
                        sublisp_throw($sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_operators(self, operators);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_html_display_all_operators_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject operators = get_shop_basic_domain_operators(self);
        try {
            thread.throwStack.push($sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                SubLObject alist = hash_table_utilities.hash_table_to_alist(operators);
                if (NIL == alist) {
                    sublisp_throw($sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                }
                SubLObject cdolist_list_var;
                alist = cdolist_list_var = Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                SubLObject cons = NIL;
                cons = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject current;
                    final SubLObject datum = current = cons;
                    SubLObject pred = NIL;
                    SubLObject ops = NIL;
                    destructuring_bind_must_consp(current, datum, $list129);
                    pred = current.first();
                    current = ops = current.rest();
                    cb_form(pred, UNPROVIDED, UNPROVIDED);
                    html_newline(UNPROVIDED);
                    SubLObject cdolist_list_var_$4 = ops;
                    SubLObject cur_op = NIL;
                    cur_op = cdolist_list_var_$4.first();
                    while (NIL != cdolist_list_var_$4) {
                        methods.funcall_instance_method_with_0_args(cur_op, HTML_DISPLAY);
                        html_newline(UNPROVIDED);
                        cdolist_list_var_$4 = cdolist_list_var_$4.rest();
                        cur_op = cdolist_list_var_$4.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    cons = cdolist_list_var.first();
                } 
                sublisp_throw($sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_operators(self, operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym128$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    /**
     *
     *
     * @param TASK
    hl-formula-p
     * 		
     * @return listp ;; of shop-operator-p
     */
    @LispMethod(comment = "@param TASK\nhl-formula-p\r\n\t\t\r\n@return listp ;; of shop-operator-p")
    public static final SubLObject shop_basic_domain_get_conditional_operators_method_alt(SubLObject self, SubLObject task) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_basic_domain_method = NIL;
                SubLObject cond_operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_cond_operators(self);
                try {
                    try {
                        SubLTrampolineFile.checkType(task, LISTP);
                        {
                            SubLObject pred = cycl_utilities.formula_operator(task);
                            SubLObject results = NIL;
                            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                if (NIL == forts.fort_p(pred)) {
                                    Errors.error($str_alt134$_get_conditional_operators_shop_b, task, pred);
                                }
                            }
                            {
                                SubLObject cdolist_list_var = com.cyc.cycjava.cycl.shop_domain.shop_find_all_matching_rules(task, cond_operators);
                                SubLObject op = NIL;
                                for (op = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , op = cdolist_list_var.first()) {
                                    {
                                        SubLObject cur_head = shop_datastructures.shop_basic_planner_rule_head_method(op);
                                        if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(cur_head.rest(), task.rest()))) {
                                            results = cons(op, results);
                                        }
                                    }
                                }
                            }
                            sublisp_throw($sym133$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, nreverse(results));
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_cond_operators(self, cond_operators);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym133$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_basic_domain_method;
            }
        }
    }

    /**
     *
     *
     * @param TASK
    hl-formula-p
     * 		
     * @return listp ;; of shop-operator-p
     */
    @LispMethod(comment = "@param TASK\nhl-formula-p\r\n\t\t\r\n@return listp ;; of shop-operator-p")
    public static SubLObject shop_basic_domain_get_conditional_operators_method(final SubLObject self, final SubLObject task) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject cond_operators = get_shop_basic_domain_cond_operators(self);
        try {
            thread.throwStack.push($sym133$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                assert NIL != listp(task) : "! listp(task) " + ("Types.listp(task) " + "CommonSymbols.NIL != Types.listp(task) ") + task;
                final SubLObject pred = cycl_utilities.formula_operator(task);
                SubLObject results = NIL;
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == forts.fort_p(pred))) {
                    Errors.error($str134$_get_conditional_operators_shop_b, task, pred);
                }
                SubLObject cdolist_list_var = shop_find_all_matching_rules(task, cond_operators);
                SubLObject op = NIL;
                op = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject cur_head = shop_datastructures.shop_basic_planner_rule_head_method(op);
                    if (NIL == shop_datastructures.shop_unify_failedP(shop_datastructures.shop_unify(cur_head.rest(), task.rest()))) {
                        results = cons(op, results);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    op = cdolist_list_var.first();
                } 
                sublisp_throw($sym133$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, nreverse(results));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_cond_operators(self, cond_operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym133$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject shop_basic_domain_html_display_all_conditional_operators_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_shop_basic_domain_method = NIL;
            SubLObject cond_operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_cond_operators(self);
            try {
                try {
                    {
                        SubLObject alist = hash_table_utilities.hash_table_to_alist(cond_operators);
                        Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                        {
                            SubLObject cdolist_list_var = alist;
                            SubLObject cons = NIL;
                            for (cons = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cons = cdolist_list_var.first()) {
                                {
                                    SubLObject datum = cons;
                                    SubLObject current = datum;
                                    SubLObject pred = NIL;
                                    SubLObject ops = NIL;
                                    destructuring_bind_must_consp(current, datum, $list_alt129);
                                    pred = current.first();
                                    current = current.rest();
                                    ops = current;
                                    cb_form(pred, UNPROVIDED, UNPROVIDED);
                                    html_newline(UNPROVIDED);
                                    {
                                        SubLObject cdolist_list_var_5 = ops;
                                        SubLObject cur_op = NIL;
                                        for (cur_op = cdolist_list_var_5.first(); NIL != cdolist_list_var_5; cdolist_list_var_5 = cdolist_list_var_5.rest() , cur_op = cdolist_list_var_5.first()) {
                                            methods.funcall_instance_method_with_0_args(cur_op, HTML_DISPLAY);
                                            html_newline(UNPROVIDED);
                                        }
                                    }
                                }
                            }
                        }
                        sublisp_throw($sym138$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_cond_operators(self, cond_operators);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym138$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_basic_domain_method;
        }
    }

    public static SubLObject shop_basic_domain_html_display_all_conditional_operators_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_basic_domain_method = NIL;
        final SubLObject cond_operators = get_shop_basic_domain_cond_operators(self);
        try {
            thread.throwStack.push($sym138$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
            try {
                final SubLObject alist = hash_table_utilities.hash_table_to_alist(cond_operators);
                Sort.sort(alist, symbol_function($sym53$STRING__), SHOP_TASK_PRED_STRING);
                SubLObject cdolist_list_var = alist;
                SubLObject cons = NIL;
                cons = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject current;
                    final SubLObject datum = current = cons;
                    SubLObject pred = NIL;
                    SubLObject ops = NIL;
                    destructuring_bind_must_consp(current, datum, $list129);
                    pred = current.first();
                    current = ops = current.rest();
                    cb_form(pred, UNPROVIDED, UNPROVIDED);
                    html_newline(UNPROVIDED);
                    SubLObject cdolist_list_var_$5 = ops;
                    SubLObject cur_op = NIL;
                    cur_op = cdolist_list_var_$5.first();
                    while (NIL != cdolist_list_var_$5) {
                        methods.funcall_instance_method_with_0_args(cur_op, HTML_DISPLAY);
                        html_newline(UNPROVIDED);
                        cdolist_list_var_$5 = cdolist_list_var_$5.rest();
                        cur_op = cdolist_list_var_$5.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    cons = cdolist_list_var.first();
                } 
                sublisp_throw($sym138$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_cond_operators(self, cond_operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_basic_domain_method = Errors.handleThrowable(ccatch_env_var, $sym138$OUTER_CATCH_FOR_SHOP_BASIC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_basic_domain_method;
    }

    public static final SubLObject subloop_reserved_initialize_shop_cyc_domain_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_shop_cyc_domain_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_shop_cyc_domain_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, METHODS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, PRECONDITIONS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, COND_OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SUFFICIENCIES, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, MT, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SIMPLE_SITUATION_PREDS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_shop_cyc_domain_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, METHODS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, PRECONDITIONS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, COND_OPERATORS, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SUFFICIENCIES, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, MT, NIL);
        classes.subloop_initialize_slot(new_instance, SHOP_BASIC_DOMAIN, SIMPLE_SITUATION_PREDS, NIL);
        return NIL;
    }

    public static final SubLObject shop_cyc_domain_p_alt(SubLObject shop_cyc_domain) {
        return classes.subloop_instanceof_class(shop_cyc_domain, SHOP_CYC_DOMAIN);
    }

    public static SubLObject shop_cyc_domain_p(final SubLObject shop_cyc_domain) {
        return classes.subloop_instanceof_class(shop_cyc_domain, SHOP_CYC_DOMAIN);
    }

    public static final SubLObject shop_cyc_domain_initialize_method_alt(SubLObject self) {
        com.cyc.cycjava.cycl.shop_domain.shop_basic_domain_initialize_method(self);
        return self;
    }

    public static SubLObject shop_cyc_domain_initialize_method(final SubLObject self) {
        shop_basic_domain_initialize_method(self);
        return self;
    }

    public static final SubLObject shop_cyc_domain_print_method_alt(SubLObject self, SubLObject stream, SubLObject depth) {
        {
            SubLObject catch_var_for_shop_cyc_domain_method = NIL;
            SubLObject mt = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_mt(self);
            SubLObject instance_number = object.get_object_instance_number(self);
            try {
                try {
                    if (NIL != subloop_structures.instance_p(self)) {
                        format(stream, $str_alt148$__SHOP_CYC_DOMAIN__S__S_, instance_number, mt);
                    } else {
                        format(stream, $str_alt30$__Malformed_Instance_);
                    }
                    sublisp_throw($sym147$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_mt(self, mt);
                            object.set_object_instance_number(self, instance_number);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym147$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_cyc_domain_method;
        }
    }

    public static SubLObject shop_cyc_domain_print_method(final SubLObject self, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        final SubLObject mt = get_shop_basic_domain_mt(self);
        final SubLObject instance_number = object.get_object_instance_number(self);
        try {
            thread.throwStack.push($sym147$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                if (NIL != subloop_structures.instance_p(self)) {
                    format(stream, $str148$__SHOP_CYC_DOMAIN__S__S_, instance_number, mt);
                } else {
                    format(stream, $str30$__Malformed_Instance_);
                }
                sublisp_throw($sym147$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_mt(self, mt);
                    object.set_object_instance_number(self, instance_number);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym147$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    /**
     *
     *
     * @param ASSERTIONS
     * 		listp ;; of assertion-p
     * 		process a list of ASSERTIONS, so that they can be entered into the domain
     * 		description. ASSERTIONS is a list of elements satisfying ASSERTION-P.
     * 		Returns T.
     */
    @LispMethod(comment = "@param ASSERTIONS\r\n\t\tlistp ;; of assertion-p\r\n\t\tprocess a list of ASSERTIONS, so that they can be entered into the domain\r\n\t\tdescription. ASSERTIONS is a list of elements satisfying ASSERTION-P.\r\n\t\tReturns T.")
    public static final SubLObject shop_cyc_domain_process_plan_assertions_list_method_alt(SubLObject self, SubLObject assertions) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_cyc_domain_method = NIL;
                SubLObject v_methods = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_methods(self);
                try {
                    try {
                        {
                            SubLObject preconds = NIL;
                            SubLObject effects = NIL;
                            SubLObject pre_methods = NIL;
                            SubLObject v_methods_6 = NIL;
                            SubLObject axioms = NIL;
                            SubLObject sufficient_conds = NIL;
                            SubLObject cond_effects = NIL;
                            {
                                SubLObject cdolist_list_var = assertions;
                                SubLObject assertion = NIL;
                                for (assertion = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , assertion = cdolist_list_var.first()) {
                                    if (NIL != assertions_high.assertion_mentions_term(assertion, $$preconditionForMethod)) {
                                        pre_methods = cons(assertion, pre_methods);
                                    } else {
                                        if (NIL != assertions_high.assertion_mentions_term(assertion, $$methodForAction)) {
                                            v_methods_6 = cons(assertion, v_methods_6);
                                        } else {
                                            if (NIL != assertions_high.assertion_mentions_term(assertion, $$preconditionFor_Props)) {
                                                preconds = cons(assertion, preconds);
                                            } else {
                                                if (NIL != assertions_high.assertion_mentions_term(assertion, $$effectOfAction_Props)) {
                                                    effects = cons(assertion, effects);
                                                } else {
                                                    if (NIL != assertions_high.assertion_mentions_term(assertion, $$sufficientFor_Props)) {
                                                        sufficient_conds = cons(assertion, sufficient_conds);
                                                    } else {
                                                        if (NIL != assertions_high.assertion_mentions_term(assertion, $$effectOfActionIf_Props)) {
                                                            cond_effects = cons(assertion, cond_effects);
                                                        } else {
                                                            axioms = cons(assertion, axioms);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (NIL != shop_parameters.$shop_use_precondition_for_methodP$.getDynamicValue(thread)) {
                                format(T, $str_alt160$________________________using_pre);
                                v_methods_6 = pre_methods;
                            }
                            {
                                SubLObject cdolist_list_var = preconds;
                                SubLObject precond = NIL;
                                for (precond = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , precond = cdolist_list_var.first()) {
                                    com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_process_precondition_method(self, precond);
                                }
                            }
                            {
                                SubLObject cdolist_list_var = effects;
                                SubLObject effect = NIL;
                                for (effect = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , effect = cdolist_list_var.first()) {
                                    com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_process_effect_method(self, effect);
                                }
                            }
                            {
                                SubLObject cdolist_list_var = cond_effects;
                                SubLObject cond_effect = NIL;
                                for (cond_effect = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cond_effect = cdolist_list_var.first()) {
                                    com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_process_conditional_effect_method(self, cond_effect);
                                }
                            }
                            {
                                SubLObject cdolist_list_var = v_methods_6;
                                SubLObject method = NIL;
                                for (method = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , method = cdolist_list_var.first()) {
                                    com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_process_method_method(self, method);
                                }
                            }
                            {
                                SubLObject cdolist_list_var = sufficient_conds;
                                SubLObject scond = NIL;
                                for (scond = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , scond = cdolist_list_var.first()) {
                                    com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_process_sufficient_cond_method(self, scond);
                                }
                            }
                            sublisp_throw($sym153$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_methods(self, v_methods);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym153$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_cyc_domain_method;
            }
        }
    }

    /**
     *
     *
     * @param ASSERTIONS
     * 		listp ;; of assertion-p
     * 		process a list of ASSERTIONS, so that they can be entered into the domain
     * 		description. ASSERTIONS is a list of elements satisfying ASSERTION-P.
     * 		Returns T.
     */
    @LispMethod(comment = "@param ASSERTIONS\r\n\t\tlistp ;; of assertion-p\r\n\t\tprocess a list of ASSERTIONS, so that they can be entered into the domain\r\n\t\tdescription. ASSERTIONS is a list of elements satisfying ASSERTION-P.\r\n\t\tReturns T.")
    public static SubLObject shop_cyc_domain_process_plan_assertions_list_method(final SubLObject self, final SubLObject assertions) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        final SubLObject v_methods = get_shop_basic_domain_methods(self);
        try {
            thread.throwStack.push($sym153$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                SubLObject preconds = NIL;
                SubLObject effects = NIL;
                SubLObject pre_methods = NIL;
                SubLObject v_methods_$6 = NIL;
                SubLObject axioms = NIL;
                SubLObject sufficient_conds = NIL;
                SubLObject cond_effects = NIL;
                SubLObject cdolist_list_var = assertions;
                SubLObject assertion = NIL;
                assertion = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if (NIL != assertions_high.assertion_mentions_term(assertion, $$preconditionForMethod)) {
                        pre_methods = cons(assertion, pre_methods);
                    } else
                        if (NIL != assertions_high.assertion_mentions_term(assertion, $$methodForAction)) {
                            v_methods_$6 = cons(assertion, v_methods_$6);
                        } else
                            if (NIL != assertions_high.assertion_mentions_term(assertion, $$preconditionFor_Props)) {
                                preconds = cons(assertion, preconds);
                            } else
                                if (NIL != assertions_high.assertion_mentions_term(assertion, $$effectOfAction_Props)) {
                                    effects = cons(assertion, effects);
                                } else
                                    if (NIL != assertions_high.assertion_mentions_term(assertion, $$sufficientFor_Props)) {
                                        sufficient_conds = cons(assertion, sufficient_conds);
                                    } else
                                        if (NIL != assertions_high.assertion_mentions_term(assertion, $$effectOfActionIf_Props)) {
                                            cond_effects = cons(assertion, cond_effects);
                                        } else {
                                            axioms = cons(assertion, axioms);
                                        }





                    cdolist_list_var = cdolist_list_var.rest();
                    assertion = cdolist_list_var.first();
                } 
                if (NIL != shop_parameters.$shop_use_precondition_for_methodP$.getDynamicValue(thread)) {
                    format(T, $str160$________________________using_pre);
                    v_methods_$6 = pre_methods;
                }
                cdolist_list_var = preconds;
                SubLObject precond = NIL;
                precond = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    shop_cyc_domain_process_precondition_method(self, precond);
                    cdolist_list_var = cdolist_list_var.rest();
                    precond = cdolist_list_var.first();
                } 
                cdolist_list_var = effects;
                SubLObject effect = NIL;
                effect = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    shop_cyc_domain_process_effect_method(self, effect);
                    cdolist_list_var = cdolist_list_var.rest();
                    effect = cdolist_list_var.first();
                } 
                cdolist_list_var = cond_effects;
                SubLObject cond_effect = NIL;
                cond_effect = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    shop_cyc_domain_process_conditional_effect_method(self, cond_effect);
                    cdolist_list_var = cdolist_list_var.rest();
                    cond_effect = cdolist_list_var.first();
                } 
                cdolist_list_var = v_methods_$6;
                SubLObject method = NIL;
                method = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    shop_cyc_domain_process_method_method(self, method);
                    cdolist_list_var = cdolist_list_var.rest();
                    method = cdolist_list_var.first();
                } 
                cdolist_list_var = sufficient_conds;
                SubLObject scond = NIL;
                scond = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    shop_cyc_domain_process_sufficient_cond_method(self, scond);
                    cdolist_list_var = cdolist_list_var.rest();
                    scond = cdolist_list_var.first();
                } 
                sublisp_throw($sym153$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_methods(self, v_methods);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym153$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt1 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NAME"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SET-NAME"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-METHOD"), list(makeSymbol("METHOD")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADD-OPERATOR"), list(makeSymbol("OPERATOR")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PLANNER-RULES"), list(makeSymbol("TASK"), makeSymbol("TYPE")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-METHODS"), list(makeSymbol("TASK")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OPERATORS"), list(makeSymbol("TASK")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CONDITIONAL-OPERATORS"), list(makeSymbol("TASK")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT-ALL-METHODS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT-ALL-OPERATORS"), list(makeSymbol("STREAM")), makeKeyword("PUBLIC")) });

    static private final SubLList $list_alt4 = list(makeSymbol("SHOP-DOMAIN"));

    static private final SubLList $list_alt5 = list(new SubLObject[]{ list(makeSymbol("METHODS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("PRECONDITIONS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("OPERATORS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("COND-OPERATORS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("SUFFICIENCIES"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("MT"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("SIMPLE-SITUATION-PREDS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZATION-SANITY-CHECK"), NIL, makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FLUENT-PREDICATE?"), list(makeSymbol("PRED")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ADDABLE-PREDICATE?"), list(makeSymbol("PRED")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DELETEABLE-PREDICATE?"), list(makeSymbol("PRED")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RULE-TABLE-FOR-TYPE"), list(makeSymbol("TYPE")), makeKeyword("PRIVATE")) });

    static private final SubLList $list_alt18 = list(cons(makeSymbol("SHOP-METHOD-P"), makeSymbol("METHODS")), cons(makeSymbol("SHOP-PRECONDITION-P"), makeSymbol("PRECONDITIONS")), cons(makeSymbol("SHOP-OPERATOR-P"), makeSymbol("OPERATORS")), cons(makeSymbol("SHOP-SUFFICIENCY-CONDITION-P"), makeSymbol("SUFFICIENCIES")), cons(makeSymbol("SHOP-CONDITIONAL-EFFECT-P"), makeSymbol("COND-OPERATORS")));

    static private final SubLList $list_alt20 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list_alt21 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(makeSymbol("CSETQ"), makeSymbol("MT"), NIL), list(makeSymbol("CSETQ"), makeSymbol("METHODS"), list(makeSymbol("MAKE-HASH-TABLE"), makeInteger(100))), list(makeSymbol("CSETQ"), makeSymbol("OPERATORS"), list(makeSymbol("MAKE-HASH-TABLE"), makeInteger(100))), list(makeSymbol("CSETQ"), makeSymbol("PRECONDITIONS"), list(makeSymbol("MAKE-HASH-TABLE"), makeInteger(100))), list(makeSymbol("CSETQ"), makeSymbol("SUFFICIENCIES"), list(makeSymbol("MAKE-HASH-TABLE"), TEN_INTEGER)), list(makeSymbol("CSETQ"), makeSymbol("COND-OPERATORS"), list(makeSymbol("MAKE-HASH-TABLE"), TEN_INTEGER)), list(RET, makeSymbol("SELF")));

    /**
     *
     *
     * @param DOMAIN-MT
     * 		hlmt-p
     * 		Returns the el form of assertions in MT which are likely planning assertions.
     */
    @LispMethod(comment = "@param DOMAIN-MT\r\n\t\thlmt-p\r\n\t\tReturns the el form of assertions in MT which are likely planning assertions.")
    public static final SubLObject shop_cyc_domain_gather_planning_assertions_method_alt(SubLObject self, SubLObject domain_mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject ass_set = set.new_set(UNPROVIDED, UNPROVIDED);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_GENL_MT, thread);
                        mt_relevance_macros.$mt$.bind(domain_mt, thread);
                        {
                            SubLObject cdolist_list_var = shop_main.$shop_domain_definition_predicates$.getGlobalValue();
                            SubLObject cur_pred = NIL;
                            for (cur_pred = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_pred = cdolist_list_var.first()) {
                                {
                                    SubLObject cdolist_list_var_7 = kb_mapping.gather_predicate_rule_index(cur_pred, $POS, UNPROVIDED, UNPROVIDED);
                                    SubLObject cur_ass = NIL;
                                    for (cur_ass = cdolist_list_var_7.first(); NIL != cdolist_list_var_7; cdolist_list_var_7 = cdolist_list_var_7.rest() , cur_ass = cdolist_list_var_7.first()) {
                                        set.set_add(cur_ass, ass_set);
                                    }
                                }
                            }
                        }
                        {
                            SubLObject cdolist_list_var = kb_mapping.gather_predicate_extent_index($$preconditionForMethod, UNPROVIDED, UNPROVIDED);
                            SubLObject cur_ass = NIL;
                            for (cur_ass = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cur_ass = cdolist_list_var.first()) {
                                set.set_add(cur_ass, ass_set);
                            }
                        }
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return set.set_element_list(ass_set);
            }
        }
    }

    /**
     *
     *
     * @param DOMAIN-MT
     * 		hlmt-p
     * 		Returns the el form of assertions in MT which are likely planning assertions.
     */
    @LispMethod(comment = "@param DOMAIN-MT\r\n\t\thlmt-p\r\n\t\tReturns the el form of assertions in MT which are likely planning assertions.")
    public static SubLObject shop_cyc_domain_gather_planning_assertions_method(final SubLObject self, final SubLObject domain_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject ass_set = set.new_set(UNPROVIDED, UNPROVIDED);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$mt$.currentBinding(thread);
        try {
            mt_relevance_macros.$relevant_mt_function$.bind(RELEVANT_MT_IS_GENL_MT, thread);
            mt_relevance_macros.$mt$.bind(domain_mt, thread);
            SubLObject cdolist_list_var = shop_main.$shop_domain_definition_predicates$.getGlobalValue();
            SubLObject cur_pred = NIL;
            cur_pred = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cdolist_list_var_$7 = kb_mapping.gather_predicate_rule_index(cur_pred, $POS, UNPROVIDED, UNPROVIDED);
                SubLObject cur_ass = NIL;
                cur_ass = cdolist_list_var_$7.first();
                while (NIL != cdolist_list_var_$7) {
                    set.set_add(cur_ass, ass_set);
                    cdolist_list_var_$7 = cdolist_list_var_$7.rest();
                    cur_ass = cdolist_list_var_$7.first();
                } 
                cdolist_list_var = cdolist_list_var.rest();
                cur_pred = cdolist_list_var.first();
            } 
            cdolist_list_var = kb_mapping.gather_predicate_extent_index($$preconditionForMethod, UNPROVIDED, UNPROVIDED);
            SubLObject cur_ass2 = NIL;
            cur_ass2 = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                set.set_add(cur_ass2, ass_set);
                cdolist_list_var = cdolist_list_var.rest();
                cur_ass2 = cdolist_list_var.first();
            } 
        } finally {
            mt_relevance_macros.$mt$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return set.set_element_list(ass_set);
    }

    static private final SubLList $list_alt26 = list(makeSymbol("STREAM"), makeSymbol("DEPTH"));

    static private final SubLList $list_alt27 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("PIF"), list(makeSymbol("INSTANCE-P"), makeSymbol("SELF")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<DOMAIN-~S:~S>"), makeSymbol("INSTANCE-NUMBER"), makeSymbol("MT")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<Malformed Instance>"))), list(RET, makeSymbol("SELF")));

    static private final SubLString $str_alt29$__DOMAIN__S__S_ = makeString("#<DOMAIN-~S:~S>");

    static private final SubLString $str_alt30$__Malformed_Instance_ = makeString("#<Malformed Instance>");

    static private final SubLList $list_alt33 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list_alt34 = list(list(RET, makeSymbol("MT")));

    static private final SubLString $str_alt37$var_A = makeString("var~A");

    public static final SubLObject shop_cyc_domain_init_simple_situation_preds_method_alt(SubLObject self, SubLObject domain_mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_cyc_domain_method = NIL;
                SubLObject simple_situation_preds = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_simple_situation_preds(self);
                try {
                    try {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if (NIL == hlmt.hlmt_p(domain_mt)) {
                                Errors.error($str_alt171$_init_simple_situation_preds_shop);
                            }
                        }
                        {
                            SubLObject simple_preds = isa.all_fort_instances($$SimpleSituationPredicate, domain_mt, UNPROVIDED);
                            simple_situation_preds = set_utilities.construct_set_from_list(simple_preds, UNPROVIDED, UNPROVIDED);
                            sublisp_throw($sym170$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, NIL);
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_simple_situation_preds(self, simple_situation_preds);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym170$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_cyc_domain_method;
            }
        }
    }

    public static SubLObject shop_cyc_domain_init_simple_situation_preds_method(final SubLObject self, final SubLObject domain_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        SubLObject simple_situation_preds = get_shop_basic_domain_simple_situation_preds(self);
        try {
            thread.throwStack.push($sym170$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == hlmt.hlmt_p(domain_mt))) {
                    Errors.error($str171$_init_simple_situation_preds_shop);
                }
                final SubLObject simple_preds = isa.all_fort_instances($$SimpleSituationPredicate, domain_mt, UNPROVIDED);
                simple_situation_preds = set_utilities.construct_set_from_list(simple_preds, UNPROVIDED, UNPROVIDED);
                sublisp_throw($sym170$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_simple_situation_preds(self, simple_situation_preds);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym170$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt40 = list(makeKeyword("PRIVATE"));

    static private final SubLList $list_alt41 = list(makeSymbol("TYPE"));

    static private final SubLList $list_alt42 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SLOTNAME"), list(makeSymbol("SHOP-DOMAIN-RULE-TABLE-FOR-TYPE"), makeSymbol("TYPE")))), list(RET, list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), makeSymbol("SLOTNAME")))));

    static private final SubLList $list_alt45 = list(makeSymbol("TASK"), makeSymbol("TYPE"));

    static private final SubLList $list_alt46 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CHECK-TYPE"), makeSymbol("TYPE"), makeSymbol("FUNCTION-SPEC-P")), list(makeSymbol("CLET"), list(list(makeSymbol("RULE-TABLE"), list(makeSymbol("RULE-TABLE-FOR-TYPE"), makeSymbol("SELF"), makeSymbol("TYPE")))), list(makeSymbol("CHECK-TYPE"), makeSymbol("RULE-TABLE"), makeSymbol("HASH-TABLE-P")), list(makeSymbol("CLET"), list(list(makeSymbol("RULE-LIST"), list(makeSymbol("SHOP-FIND-ALL-MATCHING-RULES"), makeSymbol("TASK"), makeSymbol("RULE-TABLE"))), list(makeSymbol("UNIFIED-RULES"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-RULE"), makeSymbol("RULE-LIST")), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-PLANNER-RULE")), makeSymbol("CUR-RULE")), makeSymbol("TASK"))), list(makeSymbol("CPUSH"), makeSymbol("CUR-RULE"), makeSymbol("UNIFIED-RULES")))), list(RET, makeSymbol("UNIFIED-RULES")))));

    /**
     * This function is used primarily for creating domains from the proof checker.
     */
    @LispMethod(comment = "This function is used primarily for creating domains from the proof checker.")
    public static final SubLObject shop_cyc_domain_init_domain_with_rules_method_alt(SubLObject self, SubLObject rules, SubLObject domain_mt) {
        {
            SubLObject catch_var_for_shop_cyc_domain_method = NIL;
            SubLObject mt = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_mt(self);
            try {
                try {
                    mt = domain_mt;
                    com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_process_plan_assertions_list_method(self, rules);
                    com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_init_simple_situation_preds_method(self, domain_mt);
                    sublisp_throw($sym177$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_mt(self, mt);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym177$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_cyc_domain_method;
        }
    }

    /**
     * This function is used primarily for creating domains from the proof checker.
     */
    @LispMethod(comment = "This function is used primarily for creating domains from the proof checker.")
    public static SubLObject shop_cyc_domain_init_domain_with_rules_method(final SubLObject self, final SubLObject rules, final SubLObject domain_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        SubLObject mt = get_shop_basic_domain_mt(self);
        try {
            thread.throwStack.push($sym177$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                mt = domain_mt;
                shop_cyc_domain_process_plan_assertions_list_method(self, rules);
                shop_cyc_domain_init_simple_situation_preds_method(self, domain_mt);
                sublisp_throw($sym177$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_mt(self, mt);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym177$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt52 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("TYPE"), makeSymbol("FUNCTION-SPEC-P")), list(makeSymbol("CLET"), list(list(makeSymbol("RULE-TABLE"), list(makeSymbol("RULE-TABLE-FOR-TYPE"), makeSymbol("SELF"), makeSymbol("TYPE")))), list(makeSymbol("CHECK-TYPE"), makeSymbol("RULE-TABLE"), makeSymbol("HASH-TABLE-P")), list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("RULE-TABLE")))), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-RULES"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("CUR-NAME")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-RULE"), makeSymbol("CUR-RULES")), list(makeSymbol("FIM"), makeSymbol("CUR-RULE"), list(QUOTE, makeSymbol("HTML-DISPLAY")))), list(makeSymbol("HTML-HR")))), list(RET, NIL)));

    static private final SubLList $list_alt55 = cons(makeSymbol("CUR-NAME"), makeSymbol("CUR-RULES"));

    /**
     * Reads planning domain assertions in microtheory mt and
     * translates them into a planning domain data structure
     * for use by the planner.
     */
    @LispMethod(comment = "Reads planning domain assertions in microtheory mt and\r\ntranslates them into a planning domain data structure\r\nfor use by the planner.\nReads planning domain assertions in microtheory mt and\ntranslates them into a planning domain data structure\nfor use by the planner.")
    public static final SubLObject shop_cyc_domain_process_plan_assertions_method_alt(SubLObject self, SubLObject domain_mt) {
        {
            SubLObject catch_var_for_shop_cyc_domain_method = NIL;
            SubLObject mt = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_mt(self);
            try {
                try {
                    if (NIL != mt) {
                        com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_initialize_method(self);
                    }
                    mt = domain_mt;
                    {
                        SubLObject assertions = com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_gather_planning_assertions_method(self, domain_mt);
                        com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_process_plan_assertions_list_method(self, assertions);
                        com.cyc.cycjava.cycl.shop_domain.shop_cyc_domain_init_simple_situation_preds_method(self, domain_mt);
                    }
                    sublisp_throw($sym181$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_mt(self, mt);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym181$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_cyc_domain_method;
        }
    }

    /**
     * Reads planning domain assertions in microtheory mt and
     * translates them into a planning domain data structure
     * for use by the planner.
     */
    @LispMethod(comment = "Reads planning domain assertions in microtheory mt and\r\ntranslates them into a planning domain data structure\r\nfor use by the planner.\nReads planning domain assertions in microtheory mt and\ntranslates them into a planning domain data structure\nfor use by the planner.")
    public static SubLObject shop_cyc_domain_process_plan_assertions_method(final SubLObject self, final SubLObject domain_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        SubLObject mt = get_shop_basic_domain_mt(self);
        try {
            thread.throwStack.push($sym181$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                if (NIL != mt) {
                    shop_cyc_domain_initialize_method(self);
                }
                mt = domain_mt;
                final SubLObject assertions = shop_cyc_domain_gather_planning_assertions_method(self, domain_mt);
                shop_cyc_domain_process_plan_assertions_list_method(self, assertions);
                shop_cyc_domain_init_simple_situation_preds_method(self, domain_mt);
                sublisp_throw($sym181$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_mt(self, mt);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym181$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt59 = list(makeSymbol("COND"));

    static private final SubLList $list_alt60 = list(list(makeSymbol("MUST"), list(makeSymbol("SHOP-PLANNER-RULE-P"), makeSymbol("COND")), makeString("(ADD-SUFFICIENCY-COND ~S): ~S is not a valid SHOP-PLANNER-RULE-P object."), makeSymbol("SELF"), makeSymbol("COND")), list(makeSymbol("CPUSH"), list(makeSymbol("GETHASH"), list(makeSymbol("FIM"), makeSymbol("COND"), list(QUOTE, makeSymbol("PRED"))), makeSymbol("SUFFICIENCIES")), makeSymbol("COND")), list(RET, NIL));

    static private final SubLString $str_alt62$_ADD_SUFFICIENCY_COND__S____S_is_ = makeString("(ADD-SUFFICIENCY-COND ~S): ~S is not a valid SHOP-PLANNER-RULE-P object.");

    static private final SubLList $list_alt66 = list(makeSymbol("TASK"));

    static private final SubLList $list_alt67 = list(makeString("@param TASK hl-formula-p"), list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("TASK-OP"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("TASK"))), list(makeSymbol("SCOND-LIST"), list(makeSymbol("GETHASH"), makeSymbol("TASK-OP"), makeSymbol("SUFFICIENCIES"))), list(makeSymbol("UNIFIED-SCONDS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-SCOND"), makeSymbol("SCOND-LIST")), list(makeSymbol("PUNLESS"), list(EQ, $FAIL, list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-SUFFICIENCY-COND")), makeSymbol("CUR-SCOND")), makeSymbol("TASK"))), list(makeSymbol("CPUSH"), makeSymbol("CUR-SCOND"), makeSymbol("UNIFIED-SCONDS")))), list(RET, makeSymbol("UNIFIED-SCONDS"))));

    /**
     *
     *
     * @param ASSERION
     * 		assertion-p
     * 		turns #$methodForAction formula into a planner method datastructure.
     */
    @LispMethod(comment = "@param ASSERION\r\n\t\tassertion-p\r\n\t\tturns #$methodForAction formula into a planner method datastructure.")
    public static final SubLObject shop_cyc_domain_process_method_method_alt(SubLObject self, SubLObject assertion) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_cyc_domain_method = NIL;
                SubLObject preconditions = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_preconditions(self);
                SubLObject v_methods = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_methods(self);
                try {
                    try {
                        thread.resetMultipleValues();
                        {
                            SubLObject method_head = com.cyc.cycjava.cycl.shop_domain.shop_extract_method_from_assertion(assertion);
                            SubLObject preconditions_8 = thread.secondMultipleValue();
                            SubLObject decomposition = thread.thirdMultipleValue();
                            thread.resetMultipleValues();
                            {
                                SubLObject method_name = cycl_utilities.formula_operator(method_head);
                                SubLObject new_method = object.object_new_method(SHOP_BASIC_METHOD);
                                shop_datastructures.shop_basic_method_init_method(new_method, assertion, method_head, preconditions_8, decomposition);
                                sethash(method_name, v_methods, cons(new_method, gethash(method_name, v_methods, UNPROVIDED)));
                                sublisp_throw($sym186$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
                            }
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_preconditions(self, preconditions);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_methods(self, v_methods);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym186$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_cyc_domain_method;
            }
        }
    }

    /**
     *
     *
     * @param ASSERION
     * 		assertion-p
     * 		turns #$methodForAction formula into a planner method datastructure.
     */
    @LispMethod(comment = "@param ASSERION\r\n\t\tassertion-p\r\n\t\tturns #$methodForAction formula into a planner method datastructure.")
    public static SubLObject shop_cyc_domain_process_method_method(final SubLObject self, final SubLObject assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        final SubLObject preconditions = get_shop_basic_domain_preconditions(self);
        final SubLObject v_methods = get_shop_basic_domain_methods(self);
        try {
            thread.throwStack.push($sym186$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                thread.resetMultipleValues();
                final SubLObject method_head = shop_extract_method_from_assertion(assertion);
                final SubLObject preconditions_$8 = thread.secondMultipleValue();
                final SubLObject decomposition = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                final SubLObject method_name = cycl_utilities.formula_operator(method_head);
                final SubLObject new_method = object.object_new_method(SHOP_BASIC_METHOD);
                shop_datastructures.shop_basic_method_init_method(new_method, assertion, method_head, preconditions_$8, decomposition);
                sethash(method_name, v_methods, cons(new_method, gethash(method_name, v_methods, UNPROVIDED)));
                sublisp_throw($sym186$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_preconditions(self, preconditions);
                    set_shop_basic_domain_methods(self, v_methods);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym186$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt72 = list(list(makeSymbol("CDOHASH"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-SCONDS"), makeSymbol("SUFFICIENCIES")), list(makeSymbol("FORMAT"), T, makeString("~%~s"), makeSymbol("CUR-NAME")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-SCOND"), makeSymbol("CUR-SCONDS")), list(makeSymbol("DESCRIBE-INSTANCE"), makeSymbol("CUR-SCOND")))), list(RET, NIL));

    static private final SubLString $str_alt74$___s = makeString("~%~s");

    static private final SubLList $list_alt77 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("SUFFICIENCIES")))), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-SCONDS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("CUR-NAME")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-SCOND"), makeSymbol("CUR-SCONDS")), list(makeSymbol("FIM"), makeSymbol("CUR-SCOND"), list(QUOTE, makeSymbol("HTML-DISPLAY")))), list(makeSymbol("HTML-HR")))), list(RET, NIL));

    /**
     *
     *
     * @param ASSERTION
     * 		assertion-p
     * 		Turn el precondition formulas into  preconditions for action operators.
     */
    @LispMethod(comment = "@param ASSERTION\r\n\t\tassertion-p\r\n\t\tTurn el precondition formulas into  preconditions for action operators.")
    public static final SubLObject shop_cyc_domain_process_precondition_method_alt(SubLObject self, SubLObject assertion) {
        {
            SubLObject catch_var_for_shop_cyc_domain_method = NIL;
            SubLObject preconditions = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_preconditions(self);
            try {
                try {
                    {
                        SubLObject assertion_form = assertions_high.assertion_formula(assertion);
                        SubLObject action_head = cycl_utilities.formula_arg2(assertion_form, UNPROVIDED);
                        SubLObject decomposition = list(list($PRIMITIVE, action_head));
                        SubLObject conditions = cycl_utilities.formula_arg1(assertion_form, UNPROVIDED);
                        SubLObject new_precondition = object.object_new_method(SHOP_BASIC_PRECONDITION);
                        conditions = com.cyc.cycjava.cycl.shop_domain.possibly_conjunctive_formula_to_literals(conditions);
                        shop_datastructures.shop_basic_method_init_method(new_precondition, assertion, action_head, conditions, decomposition);
                        sethash(cycl_utilities.formula_operator(action_head), preconditions, cons(new_precondition, gethash(cycl_utilities.formula_operator(action_head), preconditions, UNPROVIDED)));
                        sublisp_throw($sym191$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_preconditions(self, preconditions);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym191$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_cyc_domain_method;
        }
    }

    /**
     *
     *
     * @param ASSERTION
     * 		assertion-p
     * 		Turn el precondition formulas into  preconditions for action operators.
     */
    @LispMethod(comment = "@param ASSERTION\r\n\t\tassertion-p\r\n\t\tTurn el precondition formulas into  preconditions for action operators.")
    public static SubLObject shop_cyc_domain_process_precondition_method(final SubLObject self, final SubLObject assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        final SubLObject preconditions = get_shop_basic_domain_preconditions(self);
        try {
            thread.throwStack.push($sym191$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                final SubLObject assertion_form = assertions_high.assertion_formula(assertion);
                final SubLObject action_head = cycl_utilities.formula_arg2(assertion_form, UNPROVIDED);
                final SubLObject decomposition = list(list($PRIMITIVE, action_head));
                SubLObject conditions = cycl_utilities.formula_arg1(assertion_form, UNPROVIDED);
                final SubLObject new_precondition = object.object_new_method(SHOP_BASIC_PRECONDITION);
                conditions = possibly_conjunctive_formula_to_literals(conditions);
                shop_datastructures.shop_basic_method_init_method(new_precondition, assertion, action_head, conditions, decomposition);
                sethash(cycl_utilities.formula_operator(action_head), preconditions, cons(new_precondition, gethash(cycl_utilities.formula_operator(action_head), preconditions, UNPROVIDED)));
                sublisp_throw($sym191$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_preconditions(self, preconditions);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym191$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt79 = cons(makeSymbol("CUR-NAME"), makeSymbol("CUR-SCONDS"));

    static private final SubLList $list_alt82 = list(makeSymbol("METHOD"));

    static private final SubLList $list_alt83 = list(list(makeSymbol("MUST"), list(makeSymbol("SHOP-METHOD-P"), makeSymbol("METHOD")), makeString("(ADD-METHOD ~S): ~S is not a valid SHOP-METHOD-P object."), makeSymbol("SELF"), makeSymbol("METHOD")), list(makeSymbol("CPUSH"), makeSymbol("METHOD"), list(makeSymbol("GETHASH"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("PRED"), makeSymbol("SHOP-BASIC-METHOD")), makeSymbol("METHOD")), makeSymbol("METHODS"))), list(RET, NIL));

    static private final SubLString $str_alt85$_ADD_METHOD__S____S_is_not_a_vali = makeString("(ADD-METHOD ~S): ~S is not a valid SHOP-METHOD-P object.");

    static private final SubLList $list_alt88 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("METHOD-LIST"), list(makeSymbol("SHOP-FIND-ALL-MATCHING-RULES"), makeSymbol("TASK"), makeSymbol("METHODS"))), list(makeSymbol("UNIFIED-METHODS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-METHOD"), makeSymbol("METHOD-LIST")), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-METHOD")), makeSymbol("CUR-METHOD")), makeSymbol("TASK"))), list(makeSymbol("CPUSH"), makeSymbol("CUR-METHOD"), makeSymbol("UNIFIED-METHODS")))), list(RET, makeSymbol("UNIFIED-METHODS"))));

    /**
     *
     *
     * @param ASSERTION
     * 		assertion-p
     * 		Turn el effect statements into add and delete lists for action operators.
     */
    @LispMethod(comment = "@param ASSERTION\r\n\t\tassertion-p\r\n\t\tTurn el effect statements into add and delete lists for action operators.")
    public static final SubLObject shop_cyc_domain_process_effect_method_alt(SubLObject self, SubLObject assertion) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_cyc_domain_method = NIL;
                SubLObject operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_operators(self);
                try {
                    try {
                        {
                            SubLObject assertion_form = assertions_high.assertion_formula(assertion);
                            SubLObject action_head = cycl_utilities.formula_arg1(assertion_form, UNPROVIDED);
                            SubLObject effects = cycl_utilities.formula_arg2(assertion_form, UNPROVIDED);
                            SubLObject new_operator = object.object_new_method(SHOP_BASIC_OPERATOR);
                            effects = com.cyc.cycjava.cycl.shop_domain.possibly_conjunctive_formula_to_literals(effects);
                            thread.resetMultipleValues();
                            {
                                SubLObject add_list = com.cyc.cycjava.cycl.shop_domain.shop_separate_effect_list(effects);
                                SubLObject delete_list = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                shop_datastructures.shop_basic_operator_init_method(new_operator, assertion, action_head, add_list, delete_list);
                                sethash(cycl_utilities.formula_operator(action_head), operators, cons(new_operator, gethash(cycl_utilities.formula_operator(action_head), operators, UNPROVIDED)));
                            }
                            sublisp_throw($sym197$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_operators(self, operators);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym197$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_cyc_domain_method;
            }
        }
    }

    /**
     *
     *
     * @param ASSERTION
     * 		assertion-p
     * 		Turn el effect statements into add and delete lists for action operators.
     */
    @LispMethod(comment = "@param ASSERTION\r\n\t\tassertion-p\r\n\t\tTurn el effect statements into add and delete lists for action operators.")
    public static SubLObject shop_cyc_domain_process_effect_method(final SubLObject self, final SubLObject assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        final SubLObject operators = get_shop_basic_domain_operators(self);
        try {
            thread.throwStack.push($sym197$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                final SubLObject assertion_form = assertions_high.assertion_formula(assertion);
                final SubLObject action_head = cycl_utilities.formula_arg1(assertion_form, UNPROVIDED);
                SubLObject effects = cycl_utilities.formula_arg2(assertion_form, UNPROVIDED);
                final SubLObject new_operator = object.object_new_method(SHOP_BASIC_OPERATOR);
                effects = possibly_conjunctive_formula_to_literals(effects);
                thread.resetMultipleValues();
                final SubLObject add_list = shop_separate_effect_list(effects);
                final SubLObject delete_list = thread.secondMultipleValue();
                thread.resetMultipleValues();
                shop_datastructures.shop_basic_operator_init_method(new_operator, assertion, action_head, add_list, delete_list);
                sethash(cycl_utilities.formula_operator(action_head), operators, cons(new_operator, gethash(cycl_utilities.formula_operator(action_head), operators, UNPROVIDED)));
                sublisp_throw($sym197$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, T);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_operators(self, operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym197$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt92 = list(list(makeSymbol("CDOHASH"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-METHODS"), makeSymbol("METHODS")), list(makeSymbol("FORMAT"), T, makeString("~%~s"), makeSymbol("CUR-NAME")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-METHOD"), makeSymbol("CUR-METHODS")), list(makeSymbol("DESCRIBE-INSTANCE"), makeSymbol("CUR-METHOD")))), list(RET, NIL));

    static private final SubLList $list_alt96 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("METHODS")))), list(makeSymbol("PUNLESS"), makeSymbol("ALIST"), list(RET, NIL)), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("CUR-NAME"), makeSymbol("CUR-METHODS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("CUR-NAME")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-METHOD"), makeSymbol("CUR-METHODS")), list(makeSymbol("FIM"), makeSymbol("CUR-METHOD"), list(QUOTE, makeSymbol("HTML-DISPLAY"))), list(makeSymbol("HTML-NEWLINE"))), list(makeSymbol("HTML-HR"))), list(RET, NIL)));

    /**
     *
     *
     * @param ASSERTION
    assertion-p
     * 		
     */
    @LispMethod(comment = "@param ASSERTION\nassertion-p")
    public static final SubLObject shop_cyc_domain_process_sufficient_cond_method_alt(SubLObject self, SubLObject assertion) {
        {
            SubLObject catch_var_for_shop_cyc_domain_method = NIL;
            SubLObject sufficiencies = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_sufficiencies(self);
            try {
                try {
                    {
                        SubLObject assertion_form = assertions_high.assertion_formula(assertion);
                        SubLObject precondition = cycl_utilities.formula_arg1(assertion_form, UNPROVIDED);
                        SubLObject action_form = cycl_utilities.formula_arg2(assertion_form, UNPROVIDED);
                        SubLObject new_scond = object.object_new_method(SHOP_BASIC_SUFFICIENCY_COND);
                        precondition = com.cyc.cycjava.cycl.shop_domain.possibly_conjunctive_formula_to_literals(precondition);
                        methods.funcall_instance_method_with_1_args(new_scond, SET_HEAD, action_form);
                        methods.funcall_instance_method_with_1_args(new_scond, SET_COND_FORMULA, precondition);
                        methods.funcall_instance_method_with_1_args(new_scond, SET_ASSERTIONS, list(assertion));
                        sethash(cycl_utilities.formula_operator(action_form), sufficiencies, cons(new_scond, gethash(cycl_utilities.formula_operator(action_form), sufficiencies, UNPROVIDED)));
                        sublisp_throw($sym202$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_sufficiencies(self, sufficiencies);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym202$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            }
            return catch_var_for_shop_cyc_domain_method;
        }
    }

    /**
     *
     *
     * @param ASSERTION
    assertion-p
     * 		
     */
    @LispMethod(comment = "@param ASSERTION\nassertion-p")
    public static SubLObject shop_cyc_domain_process_sufficient_cond_method(final SubLObject self, final SubLObject assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        final SubLObject sufficiencies = get_shop_basic_domain_sufficiencies(self);
        try {
            thread.throwStack.push($sym202$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                final SubLObject assertion_form = assertions_high.assertion_formula(assertion);
                SubLObject precondition = cycl_utilities.formula_arg1(assertion_form, UNPROVIDED);
                final SubLObject action_form = cycl_utilities.formula_arg2(assertion_form, UNPROVIDED);
                final SubLObject new_scond = object.object_new_method(SHOP_BASIC_SUFFICIENCY_COND);
                precondition = possibly_conjunctive_formula_to_literals(precondition);
                methods.funcall_instance_method_with_1_args(new_scond, SET_HEAD, action_form);
                methods.funcall_instance_method_with_1_args(new_scond, SET_COND_FORMULA, precondition);
                methods.funcall_instance_method_with_1_args(new_scond, SET_ASSERTIONS, list(assertion));
                sethash(cycl_utilities.formula_operator(action_form), sufficiencies, cons(new_scond, gethash(cycl_utilities.formula_operator(action_form), sufficiencies, UNPROVIDED)));
                sublisp_throw($sym202$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_sufficiencies(self, sufficiencies);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym202$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt98 = cons(makeSymbol("CUR-NAME"), makeSymbol("CUR-METHODS"));

    static private final SubLList $list_alt101 = list(makeSymbol("OPERATOR"));

    static private final SubLList $list_alt102 = list(list(makeSymbol("MUST"), list(makeSymbol("SHOP-OPERATOR-P"), makeSymbol("OPERATOR")), makeString("(ADD-OPERATOR ~S): ~S is not a valid SHOP-OPERATOR-P object."), makeSymbol("SELF"), makeSymbol("OPERATOR")), list(makeSymbol("CPUSH"), makeSymbol("OPERATOR"), list(makeSymbol("GETHASH"), list(makeSymbol("FIM"), makeSymbol("OPERATOR"), list(QUOTE, makeSymbol("NAME"))), makeSymbol("OPERATORS"))), list(RET, NIL));

    static private final SubLString $str_alt104$_ADD_OPERATOR__S____S_is_not_a_va = makeString("(ADD-OPERATOR ~S): ~S is not a valid SHOP-OPERATOR-P object.");

    static private final SubLList $list_alt108 = list(makeString("@param TASK hl-formula-p\n   @return listp ;; of shop-operator-p"), list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("TASK"))), list(makeSymbol("OPERATOR-LIST"), list(makeSymbol("GETHASH"), makeSymbol("PRED"), makeSymbol("OPERATORS"))), list(makeSymbol("RESULTS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("OP"), makeSymbol("OPERATOR-LIST")), list(makeSymbol("CLET"), list(list(makeSymbol("CUR-HEAD"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-OPERATOR")), makeSymbol("OP")))), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("REST"), makeSymbol("CUR-HEAD")), list(makeSymbol("REST"), makeSymbol("TASK")))), list(makeSymbol("CPUSH"), makeSymbol("OP"), makeSymbol("RESULTS"))))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("RESULTS")))));

    /**
     *
     *
     * @param ASSERTION
    assertion-p
     * 		
     */
    @LispMethod(comment = "@param ASSERTION\nassertion-p")
    public static final SubLObject shop_cyc_domain_process_conditional_effect_method_alt(SubLObject self, SubLObject assertion) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_shop_cyc_domain_method = NIL;
                SubLObject cond_operators = com.cyc.cycjava.cycl.shop_domain.get_shop_basic_domain_cond_operators(self);
                try {
                    try {
                        {
                            SubLObject formula = assertions_high.assertion_formula(assertion);
                            SubLObject pred = cycl_utilities.formula_operator(formula);
                            SubLObject action_formula = cycl_utilities.formula_arg1(formula, UNPROVIDED);
                            SubLObject action_head = action_formula;
                            SubLObject condition = cycl_utilities.formula_arg2(formula, UNPROVIDED);
                            SubLObject effects = cycl_utilities.formula_arg3(formula, UNPROVIDED);
                            SubLObject cond_effect_operator = object.object_new_method(SHOP_BASIC_CONDITIONAL_EFFECT);
                            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                if (pred != $$effectOfActionIf_Props) {
                                    Errors.error($str_alt212$process_conditional_effect__a_is_, assertion);
                                }
                            }
                            effects = com.cyc.cycjava.cycl.shop_domain.possibly_conjunctive_formula_to_literals(effects);
                            condition = com.cyc.cycjava.cycl.shop_domain.possibly_conjunctive_formula_to_literals(condition);
                            thread.resetMultipleValues();
                            {
                                SubLObject add_list = com.cyc.cycjava.cycl.shop_domain.shop_separate_effect_list(effects);
                                SubLObject delete_list = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                shop_datastructures.shop_basic_conditional_effect_init_method(cond_effect_operator, action_head, condition, add_list, delete_list, assertion);
                                sethash(cycl_utilities.formula_operator(action_head), cond_operators, cons(cond_effect_operator, gethash(cycl_utilities.formula_operator(action_head), cond_operators, UNPROVIDED)));
                            }
                            sublisp_throw($sym210$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, NIL);
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.shop_domain.set_shop_basic_domain_cond_operators(self, cond_operators);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym210$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
                }
                return catch_var_for_shop_cyc_domain_method;
            }
        }
    }

    /**
     *
     *
     * @param ASSERTION
    assertion-p
     * 		
     */
    @LispMethod(comment = "@param ASSERTION\nassertion-p")
    public static SubLObject shop_cyc_domain_process_conditional_effect_method(final SubLObject self, final SubLObject assertion) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_shop_cyc_domain_method = NIL;
        final SubLObject cond_operators = get_shop_basic_domain_cond_operators(self);
        try {
            thread.throwStack.push($sym210$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
            try {
                final SubLObject formula = assertions_high.assertion_formula(assertion);
                final SubLObject pred = cycl_utilities.formula_operator(formula);
                final SubLObject action_head;
                final SubLObject action_formula = action_head = cycl_utilities.formula_arg1(formula, UNPROVIDED);
                SubLObject condition = cycl_utilities.formula_arg2(formula, UNPROVIDED);
                SubLObject effects = cycl_utilities.formula_arg3(formula, UNPROVIDED);
                final SubLObject cond_effect_operator = object.object_new_method(SHOP_BASIC_CONDITIONAL_EFFECT);
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (!pred.eql($$effectOfActionIf_Props))) {
                    Errors.error($str212$process_conditional_effect__a_is_, assertion);
                }
                effects = possibly_conjunctive_formula_to_literals(effects);
                condition = possibly_conjunctive_formula_to_literals(condition);
                thread.resetMultipleValues();
                final SubLObject add_list = shop_separate_effect_list(effects);
                final SubLObject delete_list = thread.secondMultipleValue();
                thread.resetMultipleValues();
                shop_datastructures.shop_basic_conditional_effect_init_method(cond_effect_operator, action_head, condition, add_list, delete_list, assertion);
                sethash(cycl_utilities.formula_operator(action_head), cond_operators, cons(cond_effect_operator, gethash(cycl_utilities.formula_operator(action_head), cond_operators, UNPROVIDED)));
                sublisp_throw($sym210$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_shop_basic_domain_cond_operators(self, cond_operators);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_shop_cyc_domain_method = Errors.handleThrowable(ccatch_env_var, $sym210$OUTER_CATCH_FOR_SHOP_CYC_DOMAIN_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_shop_cyc_domain_method;
    }

    static private final SubLList $list_alt112 = list(makeSymbol("STREAM"));

    static private final SubLList $list_alt113 = list(list(makeSymbol("CDOHASH"), list(makeSymbol("X"), makeSymbol("Y"), makeSymbol("OPERATORS")), list(new SubLObject[]{ makeSymbol("CLET"), list(list(makeSymbol("TEMP"), list(makeSymbol("FIRST"), makeSymbol("Y"))), list(makeSymbol("HEAD"), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("HEAD")))), list(makeSymbol("ADD-LITERALS"), list(makeSymbol("GET-SLOT"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("ADDS")))), list(makeSymbol("DELETE-LITERALS"), list(makeSymbol("GET-SLOT"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("DELETES")))), list(makeSymbol("CONDITIONAL-EFFECTS"), list(makeSymbol("GET-SLOT"), makeSymbol("TEMP"), list(QUOTE, makeSymbol("CONDITIONAL-EFFECTS")))), list(makeSymbol("COST"), list(makeSymbol("GET-SHOP-BASIC-OPERATOR-COST"), makeSymbol("TEMP")))), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%~s"), makeSymbol("X")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Head: ~S"), makeSymbol("HEAD")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Add-literals:     ~S"), makeSymbol("ADD-LITERALS")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Delete-literals: ~S"), makeSymbol("DELETE-LITERALS")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Conditional Effects: ~s"), makeSymbol("CONDITIONAL-EFFECTS")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%  Cost: ~S"), makeSymbol("COST")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("~%")) })), list(RET, NIL));

    static private final SubLString $str_alt119$____Head___S = makeString("~%  Head: ~S");

    static private final SubLString $str_alt120$____Add_literals_______S = makeString("~%  Add-literals:     ~S");

    static private final SubLString $str_alt121$____Delete_literals___S = makeString("~%  Delete-literals: ~S");

    static private final SubLString $str_alt122$____Conditional_Effects___s = makeString("~%  Conditional Effects: ~s");

    /**
     * Is FORMULA a #$methodForAction formula?
     */
    @LispMethod(comment = "Is FORMULA a #$methodForAction formula?")
    public static final SubLObject shop_method_formula_p_alt(SubLObject formula) {
        if (!formula.isCons()) {
            return NIL;
        }
        return eq(cycl_utilities.formula_operator(formula), $$methodForAction);
    }

    /**
     * Is FORMULA a #$methodForAction formula?
     */
    @LispMethod(comment = "Is FORMULA a #$methodForAction formula?")
    public static SubLObject shop_method_formula_p(final SubLObject formula) {
        if (!formula.isCons()) {
            return NIL;
        }
        return eq(cycl_utilities.formula_operator(formula), $$methodForAction);
    }

    static private final SubLString $str_alt123$____Cost___S = makeString("~%  Cost: ~S");

    static private final SubLString $str_alt124$__ = makeString("~%");

    /**
     *
     *
     * @param ASSERTION
    assertion-p
     * 		
     * @return hl-formula-p listp listp ;; head preconditions decomposition
     */
    @LispMethod(comment = "@param ASSERTION\nassertion-p\r\n\t\t\r\n@return hl-formula-p listp listp ;; head preconditions decomposition")
    public static final SubLObject shop_extract_method_from_assertion_alt(SubLObject assertion) {
        {
            SubLObject head = NIL;
            SubLObject preconditions = NIL;
            SubLObject decomposition = NIL;
            SubLObject assertion_form = assertions_high.assertion_formula(assertion);
            SubLObject methodstatement = NIL;
            SubLObject legal_form = T;
            SubLObject negatedatoms = NIL;
            SubLObject consequent = NIL;
            SubLObject pcase_var = assertion_form.first();
            if (pcase_var.eql($$implies) || pcase_var.eql($$preconditionForMethod)) {
                preconditions = second(assertion_form);
                consequent = third(assertion_form);
                if (NIL != com.cyc.cycjava.cycl.shop_domain.shop_method_formula_p(consequent)) {
                    methodstatement = consequent;
                } else {
                    methodstatement = cycl_utilities.formula_find_if(SHOP_METHOD_FORMULA_P, consequent, UNPROVIDED, UNPROVIDED);
                    negatedatoms = remove_if(SHOP_METHOD_FORMULA_P, cycl_utilities.formula_args(consequent, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                }
                if (NIL == methodstatement) {
                    return NIL;
                }
                if (NIL != negatedatoms) {
                    negatedatoms = Mapping.mapcar(symbol_function(NEGATE), negatedatoms);
                }
                preconditions = com.cyc.cycjava.cycl.shop_domain.possibly_conjunctive_formula_to_literals(preconditions);
                preconditions = append(preconditions, negatedatoms);
            } else {
                if (pcase_var.eql($$methodForAction)) {
                    preconditions = NIL;
                    methodstatement = assertion_form;
                } else {
                    legal_form = NIL;
                }
            }
            if (NIL != legal_form) {
                head = cycl_utilities.formula_arg1(methodstatement, UNPROVIDED);
                {
                    SubLObject action_sequence = cycl_utilities.formula_arg1(cycl_utilities.formula_arg2(methodstatement, UNPROVIDED), UNPROVIDED);
                    if (action_sequence.isList()) {
                        decomposition = action_sequence.rest();
                    } else {
                        if (NIL != variables.variable_p(action_sequence)) {
                            {
                                SubLObject hl_vars = assertions_high.assertion_hl_variables(assertion);
                                SubLObject actions_var = variables.get_variable(length(hl_vars));
                                preconditions = cons(list($$equals, action_sequence, bq_cons($$TheList, actions_var)), preconditions);
                                decomposition = actions_var;
                            }
                        }
                    }
                }
            }
            return values(head, preconditions, decomposition);
        }
    }

    /**
     *
     *
     * @param ASSERTION
    assertion-p
     * 		
     * @return hl-formula-p listp listp ;; head preconditions decomposition
     */
    @LispMethod(comment = "@param ASSERTION\nassertion-p\r\n\t\t\r\n@return hl-formula-p listp listp ;; head preconditions decomposition")
    public static SubLObject shop_extract_method_from_assertion(final SubLObject assertion) {
        SubLObject head = NIL;
        SubLObject preconditions = NIL;
        SubLObject decomposition = NIL;
        final SubLObject assertion_form = assertions_high.assertion_formula(assertion);
        SubLObject methodstatement = NIL;
        SubLObject legal_form = T;
        SubLObject negatedatoms = NIL;
        SubLObject consequent = NIL;
        final SubLObject pcase_var = assertion_form.first();
        if (pcase_var.eql($$implies) || pcase_var.eql($$preconditionForMethod)) {
            preconditions = second(assertion_form);
            consequent = third(assertion_form);
            if (NIL != shop_method_formula_p(consequent)) {
                methodstatement = consequent;
            } else {
                methodstatement = cycl_utilities.formula_find_if(SHOP_METHOD_FORMULA_P, consequent, UNPROVIDED, UNPROVIDED);
                negatedatoms = remove_if(SHOP_METHOD_FORMULA_P, cycl_utilities.formula_args(consequent, UNPROVIDED), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            }
            if (NIL == methodstatement) {
                return NIL;
            }
            if (NIL != negatedatoms) {
                negatedatoms = Mapping.mapcar(symbol_function(NEGATE), negatedatoms);
            }
            preconditions = possibly_conjunctive_formula_to_literals(preconditions);
            preconditions = append(preconditions, negatedatoms);
        } else
            if (pcase_var.eql($$methodForAction)) {
                preconditions = NIL;
                methodstatement = assertion_form;
            } else {
                legal_form = NIL;
            }

        if (NIL != legal_form) {
            head = cycl_utilities.formula_arg1(methodstatement, UNPROVIDED);
            final SubLObject action_sequence = cycl_utilities.formula_arg1(cycl_utilities.formula_arg2(methodstatement, UNPROVIDED), UNPROVIDED);
            if (action_sequence.isList()) {
                decomposition = action_sequence.rest();
            } else
                if (NIL != variables.variable_p(action_sequence)) {
                    final SubLObject hl_vars = assertions_high.assertion_hl_variables(assertion);
                    final SubLObject actions_var = variables.get_variable(length(hl_vars));
                    preconditions = cons(list($$equals, action_sequence, bq_cons($$TheList, actions_var)), preconditions);
                    decomposition = actions_var;
                }

        }
        return values(head, preconditions, decomposition);
    }

    static private final SubLList $list_alt127 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("OPERATORS")))), list(makeSymbol("PUNLESS"), makeSymbol("ALIST"), list(RET, NIL)), list(makeSymbol("CSETQ"), makeSymbol("ALIST"), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING")))), list(makeSymbol("DO-ALIST"), list(makeSymbol("PRED"), makeSymbol("OPS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("PRED")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-OP"), makeSymbol("OPS")), list(makeSymbol("FIM"), makeSymbol("CUR-OP"), list(QUOTE, makeSymbol("HTML-DISPLAY"))), list(makeSymbol("HTML-NEWLINE")))), list(RET, NIL)));

    static private final SubLList $list_alt129 = cons(makeSymbol("PRED"), makeSymbol("OPS"));

    static private final SubLList $list_alt132 = list(makeString("@param TASK hl-formula-p\n   @return listp ;; of shop-operator-p"), list(makeSymbol("CHECK-TYPE"), makeSymbol("TASK"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("PRED"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("TASK"))), list(makeSymbol("RESULTS"), NIL)), list(makeSymbol("MUST"), list(makeSymbol("FORT-P"), makeSymbol("PRED")), makeString("(get-conditional-operators shop-basic-domain) Error with ~a, ~a is not a fort-p"), makeSymbol("TASK"), makeSymbol("PRED")), list(makeSymbol("CDOLIST"), list(makeSymbol("OP"), list(makeSymbol("SHOP-FIND-ALL-MATCHING-RULES"), makeSymbol("TASK"), makeSymbol("COND-OPERATORS"))), list(makeSymbol("CLET"), list(list(makeSymbol("CUR-HEAD"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("HEAD"), makeSymbol("SHOP-BASIC-PLANNER-RULE")), makeSymbol("OP")))), list(makeSymbol("PUNLESS"), list(makeSymbol("SHOP-UNIFY-FAILED?"), list(makeSymbol("SHOP-UNIFY"), list(makeSymbol("REST"), makeSymbol("CUR-HEAD")), list(makeSymbol("REST"), makeSymbol("TASK")))), list(makeSymbol("CPUSH"), makeSymbol("OP"), makeSymbol("RESULTS"))))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("RESULTS")))));

    /**
     *
     *
     * @param FORM
    hl-formula-p
     * 		
     * @return listp ;; of literals in FORM
     */
    @LispMethod(comment = "@param FORM\nhl-formula-p\r\n\t\t\r\n@return listp ;; of literals in FORM")
    public static final SubLObject possibly_conjunctive_formula_to_literals_alt(SubLObject form) {
        if (cycl_utilities.formula_operator(form) == $$and) {
            return cycl_utilities.formula_args(form, UNPROVIDED);
        } else {
            return list(form);
        }
    }

    /**
     *
     *
     * @param FORM
    hl-formula-p
     * 		
     * @return listp ;; of literals in FORM
     */
    @LispMethod(comment = "@param FORM\nhl-formula-p\r\n\t\t\r\n@return listp ;; of literals in FORM")
    public static SubLObject possibly_conjunctive_formula_to_literals(final SubLObject form) {
        if (cycl_utilities.formula_operator(form).eql($$and)) {
            return cycl_utilities.formula_args(form, UNPROVIDED);
        }
        return list(form);
    }

    /**
     *
     *
     * @param EFFECTS
    listp
     * 		
     * @return 0 listp ;; adds
     * @unknown 1 listp ;; deletes
    separate adds from deletes
     */
    @LispMethod(comment = "@param EFFECTS\nlistp\r\n\t\t\r\n@return 0 listp ;; adds\r\n@unknown 1 listp ;; deletes\r\nseparate adds from deletes")
    public static final SubLObject shop_separate_effect_list_alt(SubLObject effects) {
        {
            SubLObject add_list = NIL;
            SubLObject delete_list = NIL;
            SubLObject cdolist_list_var = effects;
            SubLObject e_formula = NIL;
            for (e_formula = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , e_formula = cdolist_list_var.first()) {
                if (NIL != cycl_utilities.negatedP(e_formula)) {
                    delete_list = cons(cycl_utilities.formula_arg1(e_formula, UNPROVIDED), delete_list);
                } else {
                    add_list = cons(e_formula, add_list);
                }
            }
            return values(add_list, delete_list);
        }
    }

    /**
     *
     *
     * @param EFFECTS
    listp
     * 		
     * @return 0 listp ;; adds
     * @unknown 1 listp ;; deletes
    separate adds from deletes
     */
    @LispMethod(comment = "@param EFFECTS\nlistp\r\n\t\t\r\n@return 0 listp ;; adds\r\n@unknown 1 listp ;; deletes\r\nseparate adds from deletes")
    public static SubLObject shop_separate_effect_list(final SubLObject effects) {
        SubLObject add_list = NIL;
        SubLObject delete_list = NIL;
        SubLObject cdolist_list_var = effects;
        SubLObject e_formula = NIL;
        e_formula = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != cycl_utilities.negatedP(e_formula)) {
                delete_list = cons(cycl_utilities.formula_arg1(e_formula, UNPROVIDED), delete_list);
            } else {
                add_list = cons(e_formula, add_list);
            }
            cdolist_list_var = cdolist_list_var.rest();
            e_formula = cdolist_list_var.first();
        } 
        return values(add_list, delete_list);
    }

    static private final SubLString $str_alt134$_get_conditional_operators_shop_b = makeString("(get-conditional-operators shop-basic-domain) Error with ~a, ~a is not a fort-p");

    static private final SubLList $list_alt137 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ALIST"), list(makeSymbol("HASH-TABLE-TO-ALIST"), makeSymbol("COND-OPERATORS")))), list(makeSymbol("SORT"), makeSymbol("ALIST"), list(makeSymbol("FUNCTION"), makeSymbol("STRING<=")), list(QUOTE, makeSymbol("SHOP-TASK-PRED-STRING"))), list(makeSymbol("DO-ALIST"), list(makeSymbol("PRED"), makeSymbol("OPS"), makeSymbol("ALIST")), list(makeSymbol("CB-FORM"), makeSymbol("PRED")), list(makeSymbol("HTML-NEWLINE")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-OP"), makeSymbol("OPS")), list(makeSymbol("FIM"), makeSymbol("CUR-OP"), list(QUOTE, makeSymbol("HTML-DISPLAY"))), list(makeSymbol("HTML-NEWLINE")))), list(RET, NIL)));

    public static SubLObject declare_shop_domain_file() {
        declareFunction("shop_domain_p", "SHOP-DOMAIN-P", 1, 0, false);
        declareFunction("get_shop_basic_domain_simple_situation_preds", "GET-SHOP-BASIC-DOMAIN-SIMPLE-SITUATION-PREDS", 1, 0, false);
        declareFunction("set_shop_basic_domain_simple_situation_preds", "SET-SHOP-BASIC-DOMAIN-SIMPLE-SITUATION-PREDS", 2, 0, false);
        declareFunction("get_shop_basic_domain_mt", "GET-SHOP-BASIC-DOMAIN-MT", 1, 0, false);
        declareFunction("set_shop_basic_domain_mt", "SET-SHOP-BASIC-DOMAIN-MT", 2, 0, false);
        declareFunction("get_shop_basic_domain_sufficiencies", "GET-SHOP-BASIC-DOMAIN-SUFFICIENCIES", 1, 0, false);
        declareFunction("set_shop_basic_domain_sufficiencies", "SET-SHOP-BASIC-DOMAIN-SUFFICIENCIES", 2, 0, false);
        declareFunction("get_shop_basic_domain_cond_operators", "GET-SHOP-BASIC-DOMAIN-COND-OPERATORS", 1, 0, false);
        declareFunction("set_shop_basic_domain_cond_operators", "SET-SHOP-BASIC-DOMAIN-COND-OPERATORS", 2, 0, false);
        declareFunction("get_shop_basic_domain_operators", "GET-SHOP-BASIC-DOMAIN-OPERATORS", 1, 0, false);
        declareFunction("set_shop_basic_domain_operators", "SET-SHOP-BASIC-DOMAIN-OPERATORS", 2, 0, false);
        declareFunction("get_shop_basic_domain_preconditions", "GET-SHOP-BASIC-DOMAIN-PRECONDITIONS", 1, 0, false);
        declareFunction("set_shop_basic_domain_preconditions", "SET-SHOP-BASIC-DOMAIN-PRECONDITIONS", 2, 0, false);
        declareFunction("get_shop_basic_domain_methods", "GET-SHOP-BASIC-DOMAIN-METHODS", 1, 0, false);
        declareFunction("set_shop_basic_domain_methods", "SET-SHOP-BASIC-DOMAIN-METHODS", 2, 0, false);
        declareFunction("subloop_reserved_initialize_shop_basic_domain_class", "SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-DOMAIN-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_shop_basic_domain_instance", "SUBLOOP-RESERVED-INITIALIZE-SHOP-BASIC-DOMAIN-INSTANCE", 1, 0, false);
        declareFunction("shop_basic_domain_p", "SHOP-BASIC-DOMAIN-P", 1, 0, false);
        declareFunction("shop_domain_rule_table_for_type", "SHOP-DOMAIN-RULE-TABLE-FOR-TYPE", 1, 0, false);
        declareFunction("shop_basic_domain_initialize_method", "SHOP-BASIC-DOMAIN-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("shop_basic_domain_print_method", "SHOP-BASIC-DOMAIN-PRINT-METHOD", 3, 0, false);
        declareFunction("shop_basic_domain_get_name_method", "SHOP-BASIC-DOMAIN-GET-NAME-METHOD", 1, 0, false);
        declareFunction("shop_task_pred_string", "SHOP-TASK-PRED-STRING", 1, 0, false);
        declareFunction("shop_find_all_matching_rules", "SHOP-FIND-ALL-MATCHING-RULES", 2, 0, false);
        declareFunction("shop_basic_domain_rule_table_for_type_method", "SHOP-BASIC-DOMAIN-RULE-TABLE-FOR-TYPE-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_get_planner_rules_method", "SHOP-BASIC-DOMAIN-GET-PLANNER-RULES-METHOD", 3, 0, false);
        declareFunction("shop_basic_domain_html_display_all_rules_of_type_method", "SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-RULES-OF-TYPE-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_add_sufficient_conds_method", "SHOP-BASIC-DOMAIN-ADD-SUFFICIENT-CONDS-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_get_sufficient_conds_method", "SHOP-BASIC-DOMAIN-GET-SUFFICIENT-CONDS-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_print_all_sconds_method", "SHOP-BASIC-DOMAIN-PRINT-ALL-SCONDS-METHOD", 1, 0, false);
        declareFunction("shop_basic_domain_html_display_all_sconds_method", "SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-SCONDS-METHOD", 1, 0, false);
        declareFunction("shop_basic_domain_add_method_method", "SHOP-BASIC-DOMAIN-ADD-METHOD-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_get_methods_method", "SHOP-BASIC-DOMAIN-GET-METHODS-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_print_all_methods_method", "SHOP-BASIC-DOMAIN-PRINT-ALL-METHODS-METHOD", 1, 0, false);
        declareFunction("shop_basic_domain_html_display_all_methods_method", "SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-METHODS-METHOD", 1, 0, false);
        declareFunction("shop_basic_domain_add_operator_method", "SHOP-BASIC-DOMAIN-ADD-OPERATOR-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_get_operators_method", "SHOP-BASIC-DOMAIN-GET-OPERATORS-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_print_all_operators_method", "SHOP-BASIC-DOMAIN-PRINT-ALL-OPERATORS-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_html_display_all_operators_method", "SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-OPERATORS-METHOD", 1, 0, false);
        declareFunction("shop_basic_domain_get_conditional_operators_method", "SHOP-BASIC-DOMAIN-GET-CONDITIONAL-OPERATORS-METHOD", 2, 0, false);
        declareFunction("shop_basic_domain_html_display_all_conditional_operators_method", "SHOP-BASIC-DOMAIN-HTML-DISPLAY-ALL-CONDITIONAL-OPERATORS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_shop_cyc_domain_class", "SUBLOOP-RESERVED-INITIALIZE-SHOP-CYC-DOMAIN-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_shop_cyc_domain_instance", "SUBLOOP-RESERVED-INITIALIZE-SHOP-CYC-DOMAIN-INSTANCE", 1, 0, false);
        declareFunction("shop_cyc_domain_p", "SHOP-CYC-DOMAIN-P", 1, 0, false);
        declareFunction("shop_cyc_domain_initialize_method", "SHOP-CYC-DOMAIN-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("shop_cyc_domain_print_method", "SHOP-CYC-DOMAIN-PRINT-METHOD", 3, 0, false);
        declareFunction("shop_cyc_domain_process_plan_assertions_list_method", "SHOP-CYC-DOMAIN-PROCESS-PLAN-ASSERTIONS-LIST-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_gather_planning_assertions_method", "SHOP-CYC-DOMAIN-GATHER-PLANNING-ASSERTIONS-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_init_simple_situation_preds_method", "SHOP-CYC-DOMAIN-INIT-SIMPLE-SITUATION-PREDS-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_init_domain_with_rules_method", "SHOP-CYC-DOMAIN-INIT-DOMAIN-WITH-RULES-METHOD", 3, 0, false);
        declareFunction("shop_cyc_domain_process_plan_assertions_method", "SHOP-CYC-DOMAIN-PROCESS-PLAN-ASSERTIONS-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_process_method_method", "SHOP-CYC-DOMAIN-PROCESS-METHOD-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_process_precondition_method", "SHOP-CYC-DOMAIN-PROCESS-PRECONDITION-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_process_effect_method", "SHOP-CYC-DOMAIN-PROCESS-EFFECT-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_process_sufficient_cond_method", "SHOP-CYC-DOMAIN-PROCESS-SUFFICIENT-COND-METHOD", 2, 0, false);
        declareFunction("shop_cyc_domain_process_conditional_effect_method", "SHOP-CYC-DOMAIN-PROCESS-CONDITIONAL-EFFECT-METHOD", 2, 0, false);
        declareFunction("shop_method_formula_p", "SHOP-METHOD-FORMULA-P", 1, 0, false);
        declareFunction("shop_extract_method_from_assertion", "SHOP-EXTRACT-METHOD-FROM-ASSERTION", 1, 0, false);
        declareFunction("possibly_conjunctive_formula_to_literals", "POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS", 1, 0, false);
        declareFunction("shop_separate_effect_list", "SHOP-SEPARATE-EFFECT-LIST", 1, 0, false);
        return NIL;
    }

    static private final SubLList $list_alt141 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-METHOD"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-AXIOM"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-PRECONDITION"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-EFFECT"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-CONDITIONAL-EFFECT"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-SUFFICIENT-COND"), list(makeSymbol("ASSERTION")), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-PLAN-ASSERTIONS-LIST"), list(makeSymbol("ASSERTIONS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PROCESS-PLAN-ASSERTIONS"), list(makeSymbol("MT")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INIT-DOMAIN-WITH-RULES"), list(makeSymbol("RULES"), makeSymbol("MT")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POPULATE-DOMAIN"), list(makeSymbol("DOMAIN")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INIT-SIMPLE-SITUATION-PREDS"), list(makeSymbol("MT")), makeKeyword("PRIVATE")) });

    static private final SubLList $list_alt144 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt146 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("PIF"), list(makeSymbol("INSTANCE-P"), makeSymbol("SELF")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<SHOP-CYC-DOMAIN-~S:~S>"), makeSymbol("INSTANCE-NUMBER"), makeSymbol("MT")), list(makeSymbol("FORMAT"), makeSymbol("STREAM"), makeString("#<Malformed Instance>"))), list(RET, makeSymbol("SELF")));

    static private final SubLString $str_alt148$__SHOP_CYC_DOMAIN__S__S_ = makeString("#<SHOP-CYC-DOMAIN-~S:~S>");

    static private final SubLList $list_alt151 = list(makeSymbol("ASSERTIONS"));

    static private final SubLList $list_alt152 = list(makeString("@param ASSERTIONS listp ;; of assertion-p\n   process a list of ASSERTIONS, so that they can be entered into the domain\n   description. ASSERTIONS is a list of elements satisfying ASSERTION-P. \n   Returns T."), list(new SubLObject[]{ makeSymbol("CLET"), list(list(makeSymbol("PRECONDS"), NIL), list(makeSymbol("EFFECTS"), NIL), list(makeSymbol("PRE-METHODS"), NIL), list(makeSymbol("METHODS"), NIL), list(makeSymbol("AXIOMS"), NIL), list(makeSymbol("SUFFICIENT-CONDS"), NIL), list(makeSymbol("COND-EFFECTS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("ASSERTION"), makeSymbol("ASSERTIONS")), list(makeSymbol("PCOND"), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("preconditionForMethod")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("PRE-METHODS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("methodForAction")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("METHODS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("preconditionFor-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("PRECONDS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("effectOfAction-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("EFFECTS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("sufficientFor-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("SUFFICIENT-CONDS"))), list(list(makeSymbol("ASSERTION-MENTIONS-TERM"), makeSymbol("ASSERTION"), reader_make_constant_shell("effectOfActionIf-Props")), list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("COND-EFFECTS"))), list(T, list(makeSymbol("CPUSH"), makeSymbol("ASSERTION"), makeSymbol("AXIOMS"))))), list(makeSymbol("PWHEN"), makeSymbol("*SHOP-USE-PRECONDITION-FOR-METHOD?*"), list(makeSymbol("PROGN"), list(makeSymbol("FORMAT"), T, makeString("******************* --> using preconditionForMethod~%~%")), list(makeSymbol("CSETQ"), makeSymbol("METHODS"), makeSymbol("PRE-METHODS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("PRECOND"), makeSymbol("PRECONDS")), list(makeSymbol("PROCESS-PRECONDITION"), makeSymbol("SELF"), makeSymbol("PRECOND"))), list(makeSymbol("CDOLIST"), list(makeSymbol("EFFECT"), makeSymbol("EFFECTS")), list(makeSymbol("PROCESS-EFFECT"), makeSymbol("SELF"), makeSymbol("EFFECT"))), list(makeSymbol("CDOLIST"), list(makeSymbol("COND-EFFECT"), makeSymbol("COND-EFFECTS")), list(makeSymbol("PROCESS-CONDITIONAL-EFFECT"), makeSymbol("SELF"), makeSymbol("COND-EFFECT"))), list(makeSymbol("CDOLIST"), list(makeSymbol("METHOD"), makeSymbol("METHODS")), list(makeSymbol("PROCESS-METHOD"), makeSymbol("SELF"), makeSymbol("METHOD"))), list(makeSymbol("CDOLIST"), list(makeSymbol("SCOND"), makeSymbol("SUFFICIENT-CONDS")), list(makeSymbol("PROCESS-SUFFICIENT-COND"), makeSymbol("SELF"), makeSymbol("SCOND"))) }), list(RET, T));

    public static SubLObject init_shop_domain_file() {
        deflexical("*SHOP-DOMAIN-PLANNER-RULE-SLOT-MAP*", $list18);
        return NIL;
    }

    public static SubLObject setup_shop_domain_file() {
        interfaces.new_interface(SHOP_DOMAIN, NIL, NIL, $list1);
        classes.subloop_new_class(SHOP_BASIC_DOMAIN, OBJECT, $list4, NIL, $list5);
        classes.class_set_implements_slot_listeners(SHOP_BASIC_DOMAIN, NIL);
        classes.subloop_note_class_initialization_function(SHOP_BASIC_DOMAIN, SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_DOMAIN_CLASS);
        classes.subloop_note_instance_initialization_function(SHOP_BASIC_DOMAIN, SUBLOOP_RESERVED_INITIALIZE_SHOP_BASIC_DOMAIN_INSTANCE);
        subloop_reserved_initialize_shop_basic_domain_class(SHOP_BASIC_DOMAIN);
        methods.methods_incorporate_instance_method(INITIALIZE, SHOP_BASIC_DOMAIN, $list20, NIL, $list21);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, INITIALIZE, SHOP_BASIC_DOMAIN_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(PRINT, SHOP_BASIC_DOMAIN, NIL, $list26, $list27);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, PRINT, SHOP_BASIC_DOMAIN_PRINT_METHOD);
        methods.methods_incorporate_instance_method(GET_NAME, SHOP_BASIC_DOMAIN, $list33, NIL, $list34);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, GET_NAME, SHOP_BASIC_DOMAIN_GET_NAME_METHOD);
        methods.methods_incorporate_instance_method(RULE_TABLE_FOR_TYPE, SHOP_BASIC_DOMAIN, $list40, $list41, $list42);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, RULE_TABLE_FOR_TYPE, SHOP_BASIC_DOMAIN_RULE_TABLE_FOR_TYPE_METHOD);
        methods.methods_incorporate_instance_method(GET_PLANNER_RULES, SHOP_BASIC_DOMAIN, $list33, $list45, $list46);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, GET_PLANNER_RULES, SHOP_BASIC_DOMAIN_GET_PLANNER_RULES_METHOD);
        methods.methods_incorporate_instance_method(HTML_DISPLAY_ALL_RULES_OF_TYPE, SHOP_BASIC_DOMAIN, $list33, $list41, $list52);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, HTML_DISPLAY_ALL_RULES_OF_TYPE, SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_RULES_OF_TYPE_METHOD);
        methods.methods_incorporate_instance_method(ADD_SUFFICIENT_CONDS, SHOP_BASIC_DOMAIN, $list33, $list59, $list60);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, ADD_SUFFICIENT_CONDS, SHOP_BASIC_DOMAIN_ADD_SUFFICIENT_CONDS_METHOD);
        methods.methods_incorporate_instance_method(GET_SUFFICIENT_CONDS, SHOP_BASIC_DOMAIN, $list33, $list66, $list67);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, GET_SUFFICIENT_CONDS, SHOP_BASIC_DOMAIN_GET_SUFFICIENT_CONDS_METHOD);
        methods.methods_incorporate_instance_method(PRINT_ALL_SCONDS, SHOP_BASIC_DOMAIN, $list33, NIL, $list72);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, PRINT_ALL_SCONDS, SHOP_BASIC_DOMAIN_PRINT_ALL_SCONDS_METHOD);
        methods.methods_incorporate_instance_method(HTML_DISPLAY_ALL_SCONDS, SHOP_BASIC_DOMAIN, $list33, NIL, $list77);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, HTML_DISPLAY_ALL_SCONDS, SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_SCONDS_METHOD);
        methods.methods_incorporate_instance_method(ADD_METHOD, SHOP_BASIC_DOMAIN, $list33, $list82, $list83);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, ADD_METHOD, SHOP_BASIC_DOMAIN_ADD_METHOD_METHOD);
        methods.methods_incorporate_instance_method(GET_METHODS, SHOP_BASIC_DOMAIN, $list33, $list66, $list88);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, GET_METHODS, SHOP_BASIC_DOMAIN_GET_METHODS_METHOD);
        methods.methods_incorporate_instance_method(PRINT_ALL_METHODS, SHOP_BASIC_DOMAIN, $list33, NIL, $list92);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, PRINT_ALL_METHODS, SHOP_BASIC_DOMAIN_PRINT_ALL_METHODS_METHOD);
        methods.methods_incorporate_instance_method(HTML_DISPLAY_ALL_METHODS, SHOP_BASIC_DOMAIN, $list33, NIL, $list96);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, HTML_DISPLAY_ALL_METHODS, SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_METHODS_METHOD);
        methods.methods_incorporate_instance_method(ADD_OPERATOR, SHOP_BASIC_DOMAIN, $list33, $list101, $list102);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, ADD_OPERATOR, SHOP_BASIC_DOMAIN_ADD_OPERATOR_METHOD);
        methods.methods_incorporate_instance_method(GET_OPERATORS, SHOP_BASIC_DOMAIN, $list33, $list66, $list108);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, GET_OPERATORS, SHOP_BASIC_DOMAIN_GET_OPERATORS_METHOD);
        methods.methods_incorporate_instance_method(PRINT_ALL_OPERATORS, SHOP_BASIC_DOMAIN, $list33, $list112, $list113);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, PRINT_ALL_OPERATORS, SHOP_BASIC_DOMAIN_PRINT_ALL_OPERATORS_METHOD);
        methods.methods_incorporate_instance_method(HTML_DISPLAY_ALL_OPERATORS, SHOP_BASIC_DOMAIN, $list33, NIL, $list127);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, HTML_DISPLAY_ALL_OPERATORS, SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_OPERATORS_METHOD);
        methods.methods_incorporate_instance_method(GET_CONDITIONAL_OPERATORS, SHOP_BASIC_DOMAIN, $list33, $list66, $list132);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, GET_CONDITIONAL_OPERATORS, SHOP_BASIC_DOMAIN_GET_CONDITIONAL_OPERATORS_METHOD);
        methods.methods_incorporate_instance_method(HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS, SHOP_BASIC_DOMAIN, $list33, NIL, $list137);
        methods.subloop_register_instance_method(SHOP_BASIC_DOMAIN, HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS, SHOP_BASIC_DOMAIN_HTML_DISPLAY_ALL_CONDITIONAL_OPERATORS_METHOD);
        classes.subloop_new_class(SHOP_CYC_DOMAIN, SHOP_BASIC_DOMAIN, NIL, NIL, $list141);
        classes.class_set_implements_slot_listeners(SHOP_CYC_DOMAIN, NIL);
        classes.subloop_note_class_initialization_function(SHOP_CYC_DOMAIN, SUBLOOP_RESERVED_INITIALIZE_SHOP_CYC_DOMAIN_CLASS);
        classes.subloop_note_instance_initialization_function(SHOP_CYC_DOMAIN, SUBLOOP_RESERVED_INITIALIZE_SHOP_CYC_DOMAIN_INSTANCE);
        subloop_reserved_initialize_shop_cyc_domain_class(SHOP_CYC_DOMAIN);
        methods.methods_incorporate_instance_method(INITIALIZE, SHOP_CYC_DOMAIN, $list33, NIL, $list144);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, INITIALIZE, SHOP_CYC_DOMAIN_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(PRINT, SHOP_CYC_DOMAIN, NIL, $list26, $list146);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PRINT, SHOP_CYC_DOMAIN_PRINT_METHOD);
        methods.methods_incorporate_instance_method(PROCESS_PLAN_ASSERTIONS_LIST, SHOP_CYC_DOMAIN, $list40, $list151, $list152);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PROCESS_PLAN_ASSERTIONS_LIST, SHOP_CYC_DOMAIN_PROCESS_PLAN_ASSERTIONS_LIST_METHOD);
        methods.methods_incorporate_class_method(GATHER_PLANNING_ASSERTIONS, SHOP_CYC_DOMAIN, $list33, $list163, $list164);
        methods.subloop_register_class_method(SHOP_CYC_DOMAIN, GATHER_PLANNING_ASSERTIONS, SHOP_CYC_DOMAIN_GATHER_PLANNING_ASSERTIONS_METHOD);
        methods.methods_incorporate_instance_method(INIT_SIMPLE_SITUATION_PREDS, SHOP_CYC_DOMAIN, $list40, $list163, $list169);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, INIT_SIMPLE_SITUATION_PREDS, SHOP_CYC_DOMAIN_INIT_SIMPLE_SITUATION_PREDS_METHOD);
        methods.methods_incorporate_instance_method(INIT_DOMAIN_WITH_RULES, SHOP_CYC_DOMAIN, $list33, $list175, $list176);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, INIT_DOMAIN_WITH_RULES, SHOP_CYC_DOMAIN_INIT_DOMAIN_WITH_RULES_METHOD);
        methods.methods_incorporate_instance_method(PROCESS_PLAN_ASSERTIONS, SHOP_CYC_DOMAIN, $list33, $list163, $list180);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PROCESS_PLAN_ASSERTIONS, SHOP_CYC_DOMAIN_PROCESS_PLAN_ASSERTIONS_METHOD);
        methods.methods_incorporate_instance_method(PROCESS_METHOD, SHOP_CYC_DOMAIN, $list40, $list184, $list185);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PROCESS_METHOD, SHOP_CYC_DOMAIN_PROCESS_METHOD_METHOD);
        methods.methods_incorporate_instance_method(PROCESS_PRECONDITION, SHOP_CYC_DOMAIN, $list40, $list184, $list190);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PROCESS_PRECONDITION, SHOP_CYC_DOMAIN_PROCESS_PRECONDITION_METHOD);
        methods.methods_incorporate_instance_method(PROCESS_EFFECT, SHOP_CYC_DOMAIN, $list40, $list184, $list196);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PROCESS_EFFECT, SHOP_CYC_DOMAIN_PROCESS_EFFECT_METHOD);
        methods.methods_incorporate_instance_method(PROCESS_SUFFICIENT_COND, SHOP_CYC_DOMAIN, $list40, $list184, $list201);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PROCESS_SUFFICIENT_COND, SHOP_CYC_DOMAIN_PROCESS_SUFFICIENT_COND_METHOD);
        methods.methods_incorporate_instance_method(PROCESS_CONDITIONAL_EFFECT, SHOP_CYC_DOMAIN, $list40, $list184, $list209);
        methods.subloop_register_instance_method(SHOP_CYC_DOMAIN, PROCESS_CONDITIONAL_EFFECT, SHOP_CYC_DOMAIN_PROCESS_CONDITIONAL_EFFECT_METHOD);
        return NIL;
    }

    static private final SubLString $str_alt160$________________________using_pre = makeString("******************* --> using preconditionForMethod~%~%");

    static private final SubLList $list_alt163 = list(makeSymbol("DOMAIN-MT"));

    static private final SubLList $list_alt164 = list(makeString("@param DOMAIN-MT hlmt-p\n   Returns the el form of assertions in MT which are likely planning assertions."), list(makeSymbol("CLET"), list(list(makeSymbol("ASS-SET"), list(makeSymbol("NEW-SET")))), list(makeSymbol("WITH-MT"), makeSymbol("DOMAIN-MT"), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-PRED"), makeSymbol("*SHOP-DOMAIN-DEFINITION-PREDICATES*")), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-ASS"), list(makeSymbol("GATHER-PREDICATE-RULE-INDEX"), makeSymbol("CUR-PRED"), makeKeyword("POS"))), list(makeSymbol("SET-ADD"), makeSymbol("CUR-ASS"), makeSymbol("ASS-SET")))), list(makeSymbol("CDOLIST"), list(makeSymbol("CUR-ASS"), list(makeSymbol("GATHER-PREDICATE-EXTENT-INDEX"), reader_make_constant_shell("preconditionForMethod"))), list(makeSymbol("SET-ADD"), makeSymbol("CUR-ASS"), makeSymbol("ASS-SET")))), list(RET, list(makeSymbol("SET-ELEMENT-LIST"), makeSymbol("ASS-SET")))));

    static private final SubLList $list_alt169 = list(list(makeSymbol("MUST"), list(makeSymbol("HLMT-P"), makeSymbol("DOMAIN-MT")), makeString("(init-simple-situation-preds shop-cyc-domain): ~s is not hlmt-p")), list(makeSymbol("CLET"), list(list(makeSymbol("SIMPLE-PREDS"), list(makeSymbol("ALL-FORT-INSTANCES"), reader_make_constant_shell("SimpleSituationPredicate"), makeSymbol("DOMAIN-MT")))), list(makeSymbol("CSETQ"), makeSymbol("SIMPLE-SITUATION-PREDS"), list(makeSymbol("CONSTRUCT-SET-FROM-LIST"), makeSymbol("SIMPLE-PREDS"))), list(RET, NIL)));

    static private final SubLString $str_alt171$_init_simple_situation_preds_shop = makeString("(init-simple-situation-preds shop-cyc-domain): ~s is not hlmt-p");

    static private final SubLList $list_alt175 = list(makeSymbol("RULES"), makeSymbol("DOMAIN-MT"));

    static private final SubLList $list_alt176 = list(makeString("This function is used primarily for creating domains from the proof checker."), list(makeSymbol("CSETQ"), makeSymbol("MT"), makeSymbol("DOMAIN-MT")), list(makeSymbol("PROCESS-PLAN-ASSERTIONS-LIST"), makeSymbol("SELF"), makeSymbol("RULES")), list(makeSymbol("INIT-SIMPLE-SITUATION-PREDS"), makeSymbol("SELF"), makeSymbol("DOMAIN-MT")), list(RET, T));

    static private final SubLList $list_alt180 = list(makeString("Reads planning domain assertions in microtheory mt and\n   translates them into a planning domain data structure\n   for use by the planner."), list(makeSymbol("PWHEN"), makeSymbol("MT"), list(makeSymbol("INITIALIZE"), makeSymbol("SELF"))), list(makeSymbol("CSETQ"), makeSymbol("MT"), makeSymbol("DOMAIN-MT")), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTIONS"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("GATHER-PLANNING-ASSERTIONS"), makeSymbol("SHOP-CYC-DOMAIN")), makeSymbol("SELF"), makeSymbol("DOMAIN-MT")))), list(makeSymbol("PROCESS-PLAN-ASSERTIONS-LIST"), makeSymbol("SELF"), makeSymbol("ASSERTIONS")), list(makeSymbol("INIT-SIMPLE-SITUATION-PREDS"), makeSymbol("SELF"), makeSymbol("DOMAIN-MT"))), list(RET, T));

    static private final SubLList $list_alt184 = list(makeSymbol("ASSERTION"));

    static private final SubLList $list_alt185 = list(makeString("@param ASSERION assertion-p\n   turns #$methodForAction formula into a planner method datastructure."), list(makeSymbol("CMULTIPLE-VALUE-BIND"), list(makeSymbol("METHOD-HEAD"), makeSymbol("PRECONDITIONS"), makeSymbol("DECOMPOSITION")), list(makeSymbol("SHOP-EXTRACT-METHOD-FROM-ASSERTION"), makeSymbol("ASSERTION")), list(makeSymbol("CLET"), list(list(makeSymbol("METHOD-NAME"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("METHOD-HEAD")))), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-METHOD"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-METHOD")), list(QUOTE, makeSymbol("SHOP-BASIC-METHOD"))))), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-METHOD")), makeSymbol("NEW-METHOD"), makeSymbol("ASSERTION"), makeSymbol("METHOD-HEAD"), makeSymbol("PRECONDITIONS"), makeSymbol("DECOMPOSITION")), list(makeSymbol("CPUSH"), makeSymbol("NEW-METHOD"), list(makeSymbol("GETHASH"), makeSymbol("METHOD-NAME"), makeSymbol("METHODS")))), list(RET, T))));

    static private final SubLList $list_alt190 = list(makeString("@param ASSERTION assertion-p\n   Turn el precondition formulas into  preconditions for action operators."), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTION-FORM"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("ACTION-HEAD"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("DECOMPOSITION"), list(makeSymbol("LIST"), list(makeSymbol("LIST"), makeKeyword("PRIMITIVE"), makeSymbol("ACTION-HEAD")))), list(makeSymbol("CONDITIONS"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("NEW-PRECONDITION"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-PRECONDITION")), list(QUOTE, makeSymbol("SHOP-BASIC-PRECONDITION"))))), list(makeSymbol("CSETQ"), makeSymbol("CONDITIONS"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("CONDITIONS"))), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-PRECONDITION")), makeSymbol("NEW-PRECONDITION"), makeSymbol("ASSERTION"), makeSymbol("ACTION-HEAD"), makeSymbol("CONDITIONS"), makeSymbol("DECOMPOSITION")), list(makeSymbol("CPUSH"), makeSymbol("NEW-PRECONDITION"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-HEAD")), makeSymbol("PRECONDITIONS"))), list(RET, T)));

    @Override
    public void declareFunctions() {
        declare_shop_domain_file();
    }

    @Override
    public void initializeVariables() {
        init_shop_domain_file();
    }

    static private final SubLList $list_alt196 = list(makeString("@param ASSERTION assertion-p \n   Turn el effect statements into add and delete lists for action operators."), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTION-FORM"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("ACTION-HEAD"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("EFFECTS"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("NEW-OPERATOR"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-OPERATOR")), list(QUOTE, makeSymbol("SHOP-BASIC-OPERATOR"))))), list(makeSymbol("CSETQ"), makeSymbol("EFFECTS"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("EFFECTS"))), list(makeSymbol("CMULTIPLE-VALUE-BIND"), list(makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST")), list(makeSymbol("SHOP-SEPARATE-EFFECT-LIST"), makeSymbol("EFFECTS")), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-OPERATOR")), makeSymbol("NEW-OPERATOR"), makeSymbol("ASSERTION"), makeSymbol("ACTION-HEAD"), makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST")), list(makeSymbol("CPUSH"), makeSymbol("NEW-OPERATOR"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-HEAD")), makeSymbol("OPERATORS"))))), list(RET, T));

    @Override
    public void runTopLevelForms() {
        setup_shop_domain_file();
    }

    static {
    }

    static private final SubLList $list_alt201 = list(makeString("@param ASSERTION assertion-p"), list(makeSymbol("CLET"), list(list(makeSymbol("ASSERTION-FORM"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("PRECONDITION"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("ACTION-FORM"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("ASSERTION-FORM"))), list(makeSymbol("NEW-SCOND"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-SUFFICIENCY-COND")), list(QUOTE, makeSymbol("SHOP-BASIC-SUFFICIENCY-COND"))))), list(makeSymbol("CSETQ"), makeSymbol("PRECONDITION"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("PRECONDITION"))), list(makeSymbol("FIM"), makeSymbol("NEW-SCOND"), list(QUOTE, makeSymbol("SET-HEAD")), makeSymbol("ACTION-FORM")), list(makeSymbol("FIM"), makeSymbol("NEW-SCOND"), list(QUOTE, makeSymbol("SET-COND-FORMULA")), makeSymbol("PRECONDITION")), list(makeSymbol("FIM"), makeSymbol("NEW-SCOND"), list(QUOTE, makeSymbol("SET-ASSERTIONS")), list(makeSymbol("LIST"), makeSymbol("ASSERTION"))), list(makeSymbol("CPUSH"), makeSymbol("NEW-SCOND"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-FORM")), makeSymbol("SUFFICIENCIES"))), list(RET, NIL)));

    static private final SubLList $list_alt209 = list(makeString("@param ASSERTION assertion-p"), list(makeSymbol("CLET"), list(list(makeSymbol("FORMULA"), list(makeSymbol("ASSERTION-FORMULA"), makeSymbol("ASSERTION"))), list(makeSymbol("PRED"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("FORMULA"))), list(makeSymbol("ACTION-FORMULA"), list(makeSymbol("FORMULA-ARG1"), makeSymbol("FORMULA"))), list(makeSymbol("ACTION-HEAD"), makeSymbol("ACTION-FORMULA")), list(makeSymbol("CONDITION"), list(makeSymbol("FORMULA-ARG2"), makeSymbol("FORMULA"))), list(makeSymbol("EFFECTS"), list(makeSymbol("FORMULA-ARG3"), makeSymbol("FORMULA"))), list(makeSymbol("COND-EFFECT-OPERATOR"), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("NEW"), makeSymbol("SHOP-BASIC-CONDITIONAL-EFFECT")), list(QUOTE, makeSymbol("SHOP-BASIC-CONDITIONAL-EFFECT"))))), list(makeSymbol("MUST"), list(EQ, makeSymbol("PRED"), reader_make_constant_shell("effectOfActionIf-Props")), makeString("process-conditional-effect:~a is not at conditional effect assertion."), makeSymbol("ASSERTION")), list(makeSymbol("CSETQ"), makeSymbol("EFFECTS"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("EFFECTS"))), list(makeSymbol("CSETQ"), makeSymbol("CONDITION"), list(makeSymbol("POSSIBLY-CONJUNCTIVE-FORMULA-TO-LITERALS"), makeSymbol("CONDITION"))), list(makeSymbol("CMULTIPLE-VALUE-BIND"), list(makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST")), list(makeSymbol("SHOP-SEPARATE-EFFECT-LIST"), makeSymbol("EFFECTS")), list(makeSymbol("INLINE-METHOD"), list(makeSymbol("INIT"), makeSymbol("SHOP-BASIC-CONDITIONAL-EFFECT")), makeSymbol("COND-EFFECT-OPERATOR"), makeSymbol("ACTION-HEAD"), makeSymbol("CONDITION"), makeSymbol("ADD-LIST"), makeSymbol("DELETE-LIST"), makeSymbol("ASSERTION")), list(makeSymbol("CPUSH"), makeSymbol("COND-EFFECT-OPERATOR"), list(makeSymbol("GETHASH"), list(makeSymbol("FORMULA-OPERATOR"), makeSymbol("ACTION-HEAD")), makeSymbol("COND-OPERATORS"))))), list(RET, NIL));

    static private final SubLString $str_alt212$process_conditional_effect__a_is_ = makeString("process-conditional-effect:~a is not at conditional effect assertion.");
}

/**
 * Total time: 1000 ms
 */
