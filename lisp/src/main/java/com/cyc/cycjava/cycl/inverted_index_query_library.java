/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.subl_macro_promotions.$catch_error_message_target$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eql;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.divide;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.multiply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numL;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.remove_if;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_value;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.current_process;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.booleanp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeDouble;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.getf;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.inference.ask_utilities;
import com.cyc.cycjava.cycl.inference.kb_query;
import com.cyc.cycjava.cycl.inference.harness.inference_kernel;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Environment;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLFloat;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      INVERTED-INDEX-QUERY-LIBRARY
 * source file: /cyc/top/cycl/inverted-index-query-library.lisp
 * created:     2019/07/03 17:38:26
 */
public final class inverted_index_query_library extends SubLTranslatedFile implements V12 {
    public static final SubLObject subloop_reserved_initialize_inverted_index_query_template_insert_delete_instance(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, RESULT, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, TEST_METHOD, NIL);
        classes.subloop_initialize_slot(new_instance, CYCL_TEST_CASE, CONSTANTS, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, INDEX, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, LIBRARY_ROOT_NODE, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, LIBRARY_MT, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, GOOD_ITEMS, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, BAD_ITEMS, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, SPECIAL_ITEMS, NIL);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_inverted_index_query_template_insert_delete_class(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, MODULE, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, CATEGORIES, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, SUITES, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, TEST_METHODS, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, ENABLED, NIL);
        classes.subloop_initialize_slot(new_instance, TEST_CASE, LOCK, NIL);
        return NIL;
    }

    public static final SubLObject set_inverted_index_query_template_insert_delete_special_items(SubLObject inverted_index_query_template_insert_delete, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(inverted_index_query_template_insert_delete, value, NINE_INTEGER, SPECIAL_ITEMS);
    }

    public static final SubLObject set_inverted_index_query_template_insert_delete_library_root_node(SubLObject inverted_index_query_template_insert_delete, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(inverted_index_query_template_insert_delete, value, FIVE_INTEGER, LIBRARY_ROOT_NODE);
    }

    public static final SubLObject set_inverted_index_query_template_insert_delete_library_mt(SubLObject inverted_index_query_template_insert_delete, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(inverted_index_query_template_insert_delete, value, SIX_INTEGER, LIBRARY_MT);
    }

    public static final SubLObject set_inverted_index_query_template_insert_delete_index(SubLObject inverted_index_query_template_insert_delete, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(inverted_index_query_template_insert_delete, value, FOUR_INTEGER, INDEX);
    }

    public static final SubLObject set_inverted_index_query_template_insert_delete_good_items(SubLObject inverted_index_query_template_insert_delete, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(inverted_index_query_template_insert_delete, value, SEVEN_INTEGER, GOOD_ITEMS);
    }

    public static final SubLObject set_inverted_index_query_template_insert_delete_bad_items(SubLObject inverted_index_query_template_insert_delete, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(inverted_index_query_template_insert_delete, value, EIGHT_INTEGER, BAD_ITEMS);
    }

    public static final SubLObject inverted_index_query_template_insert_delete_p(SubLObject inverted_index_query_template_insert_delete) {
        return classes.subloop_instanceof_class(inverted_index_query_template_insert_delete, INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE);
    }

    public static final SubLObject get_inverted_index_query_template_insert_delete_special_items(SubLObject inverted_index_query_template_insert_delete) {
        return classes.subloop_get_access_protected_instance_slot(inverted_index_query_template_insert_delete, NINE_INTEGER, SPECIAL_ITEMS);
    }

    public static final SubLObject get_inverted_index_query_template_insert_delete_library_root_node(SubLObject inverted_index_query_template_insert_delete) {
        return classes.subloop_get_access_protected_instance_slot(inverted_index_query_template_insert_delete, FIVE_INTEGER, LIBRARY_ROOT_NODE);
    }

    public static final SubLObject get_inverted_index_query_template_insert_delete_library_mt(SubLObject inverted_index_query_template_insert_delete) {
        return classes.subloop_get_access_protected_instance_slot(inverted_index_query_template_insert_delete, SIX_INTEGER, LIBRARY_MT);
    }

    public static final SubLObject get_inverted_index_query_template_insert_delete_index(SubLObject inverted_index_query_template_insert_delete) {
        return classes.subloop_get_access_protected_instance_slot(inverted_index_query_template_insert_delete, FOUR_INTEGER, INDEX);
    }

    public static final SubLObject get_inverted_index_query_template_insert_delete_good_items(SubLObject inverted_index_query_template_insert_delete) {
        return classes.subloop_get_access_protected_instance_slot(inverted_index_query_template_insert_delete, SEVEN_INTEGER, GOOD_ITEMS);
    }

    public static final SubLObject get_inverted_index_query_template_insert_delete_bad_items(SubLObject inverted_index_query_template_insert_delete) {
        return classes.subloop_get_access_protected_instance_slot(inverted_index_query_template_insert_delete, EIGHT_INTEGER, BAD_ITEMS);
    }

    static private final SubLString $str_alt84$ = makeString("");

    public static final SubLFile me = new inverted_index_query_library();

 public static final String myName = "com.cyc.cycjava.cycl.inverted_index_query_library";


    // defparameter
    @LispMethod(comment = "should we generate from the CycL when indexing queries @hack should really be a settable property of the object\ndefparameter")
    // Definitions
    /**
     * should we generate from the CycL when indexing queries @hack should really be a settable property of the object
     */
    private static final SubLSymbol $query_index_index_generationP$ = makeSymbol("*QUERY-INDEX-INDEX-GENERATION?*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $ql_search_weight_factor$ = makeSymbol("*QL-SEARCH-WEIGHT-FACTOR*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $min_query_weight$ = makeSymbol("*MIN-QUERY-WEIGHT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $query_template_index$ = makeSymbol("*QUERY-TEMPLATE-INDEX*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $ql_ir_search_disabledP$ = makeSymbol("*QL-IR-SEARCH-DISABLED?*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX");

    static private final SubLList $list2 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-CLASS");

    static private final SubLSymbol $sym12$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_INSTANC = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-INSTANCE");

    static private final SubLList $list14 = list(makeKeyword("PROTECTED"), makeKeyword("NO-MEMBER-VARIABLES"), T);

    static private final SubLList $list15 = list(list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("VALUE-TEST")), list(makeSymbol("FUNCTION"), EQUALP)), list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEXED-ITEMS")), list(makeSymbol("NEW-INDEXED-ITEMS"))), list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-ENTRY-COMPUTER")), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER")))), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-ENTRY-COMPUTER"))), list(QUOTE, makeSymbol("SET-INDEX")), makeSymbol("SELF")), list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PHYSICAL-INDEX")), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("IN-MEMORY-EQUALP-INDEX")))), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER");

    private static final SubLSymbol SET_INDEX = makeSymbol("SET-INDEX");

    private static final SubLSymbol IN_MEMORY_EQUALP_INDEX = makeSymbol("IN-MEMORY-EQUALP-INDEX");

    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX_INITIALIZE_METHOD = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-INITIALIZE-METHOD");

    static private final SubLList $list21 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list22 = list(makeSymbol("KEY"));

    static private final SubLList $list23 = list(makeString("@param KEY stringp;\n       @return list; a list of (query weight) lists that are indexed on at least one word in KEY.  In the case of multiple matches,\n                     return queries with more words matching the key first.  Also accepts CycL as the key, in which case it\n                     must be one (not more than one) of the terms in the template\'s cycl."), list(makeSymbol("CLET"), list(list(makeSymbol("LOOKUP-ITEMS"), list(makeSymbol("FIF"), list(makeSymbol("STRINGP"), makeSymbol("KEY")), list(makeSymbol("STRING-TOKENIZE-VIA-WORDIFY"), makeSymbol("KEY")), list(makeSymbol("LIST"), makeSymbol("KEY")))), list(makeSymbol("LOOKUP-ITEM-COUNT"), list(makeSymbol("LENGTH"), list(makeSymbol("REMOVE-IF"), list(QUOTE, makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-STOP-WORD?")), makeSymbol("LOOKUP-ITEMS")))), list(makeSymbol("DICT"), list(makeSymbol("NEW-DICTIONARY"))), makeSymbol("SCORED")), list(makeSymbol("CDOLIST"), list(makeSymbol("KEY"), makeSymbol("LOOKUP-ITEMS")), list(makeSymbol("CDOLIST"), list(makeSymbol("SUID"), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("PHYSICAL-INDEX"), list(QUOTE, makeSymbol("LOOKUP")), makeSymbol("KEY"))), list(makeSymbol("CLET"), list(list(makeSymbol("EXISTING-COUNT"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("DICT"), makeSymbol("SUID"), ZERO_INTEGER))), list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("DICT"), makeSymbol("SUID"), list(makeSymbol("1+"), makeSymbol("EXISTING-COUNT")))))), list(makeSymbol("DO-DICTIONARY"), list(makeSymbol("SUID"), makeSymbol("SCORE"), makeSymbol("DICT")), list(makeSymbol("CPUSH"), list(makeSymbol("LIST"), list(makeSymbol("GET-ITEM-FROM-INDEX-SUID"), makeSymbol("INDEXED-ITEMS"), makeSymbol("SUID")), makeSymbol("SCORE")), makeSymbol("SCORED"))), list(makeSymbol("CLET"), list(list(makeSymbol("NORMALIZED")), list(makeSymbol("HIGHEST-WEIGHT"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("ITEM"), makeSymbol("SCORED")), list(makeSymbol("CLET"), list(list(makeSymbol("RAW-WEIGHT"), list(makeSymbol("SECOND"), makeSymbol("ITEM"))), list(makeSymbol("NORMALIZED-WEIGHT"), list(makeSymbol("/"), makeSymbol("RAW-WEIGHT"), makeSymbol("LOOKUP-ITEM-COUNT")))), list(makeSymbol("PWHEN"), list(makeSymbol(">"), makeSymbol("RAW-WEIGHT"), makeSymbol("HIGHEST-WEIGHT")), list(makeSymbol("CSETQ"), makeSymbol("HIGHEST-WEIGHT"), makeSymbol("RAW-WEIGHT"))), list(makeSymbol("CPUSH"), list(makeSymbol("LIST"), list(makeSymbol("FIRST"), makeSymbol("ITEM")), makeSymbol("NORMALIZED-WEIGHT")), makeSymbol("NORMALIZED")))), list(makeSymbol("CSETQ"), makeSymbol("NORMALIZED"), list(makeSymbol("REMOVE-LOW-WEIGHTED-ITEMS"), makeSymbol("NORMALIZED"), makeSymbol("HIGHEST-WEIGHT"), makeSymbol("LOOKUP-ITEM-COUNT"))), list(makeSymbol("CSETQ"), makeSymbol("NORMALIZED"), list(makeSymbol("SORT"), makeSymbol("NORMALIZED"), list(QUOTE, makeSymbol(">")), list(QUOTE, makeSymbol("SECOND")))), list(RET, makeSymbol("NORMALIZED")))));

    static private final SubLSymbol $sym24$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-METHOD");

    static private final SubLSymbol $sym25$QUERY_TEMPLATE_INVERTED_INDEX_STOP_WORD_ = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-STOP-WORD?");

    static private final SubLSymbol $sym26$_ = makeSymbol(">");

    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX_LOOKUP_METHOD = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-LOOKUP-METHOD");

    private static final SubLFloat $float$0_5 = makeDouble(0.5);

    static private final SubLSymbol $sym30$NORMALIZED_WEIGHT_TOO_LOW_ = makeSymbol("NORMALIZED-WEIGHT-TOO-LOW?");

    private static final SubLSymbol REMOVE_KEY = makeSymbol("REMOVE-KEY");

    static private final SubLList $list32 = list(makeSymbol("INDEX-KEY"));

    static private final SubLList $list33 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ENTRIES"), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("INDEX-ENTRY-COMPUTER"), list(QUOTE, makeSymbol("COMPUTE-INDEX-ENTRIES")), makeSymbol("INDEX-KEY")))), list(makeSymbol("CDOLIST"), list(makeSymbol("ENTRY"), makeSymbol("ENTRIES")), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("SELF"), list(QUOTE, makeSymbol("REMOVE-VALUE")), makeSymbol("ENTRY")))), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym34$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-METHOD");

    private static final SubLSymbol REMOVE_VALUE = makeSymbol("REMOVE-VALUE");

    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_KEY_METHOD = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-KEY-METHOD");

    static private final SubLList $list38 = list(makeSymbol("INDEX-ENTRY"));

    static private final SubLList $list39 = list(list(makeSymbol("FIM"), makeSymbol("PHYSICAL-INDEX"), list(QUOTE, makeSymbol("REMOVE")), makeSymbol("INDEX-ENTRY")), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym40$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-METHOD");

    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_VALUE_METHOD = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-VALUE-METHOD");

    private static final SubLSymbol INDEX_ALL_QUERIES_IN_FOLDER = makeSymbol("INDEX-ALL-QUERIES-IN-FOLDER");

    static private final SubLList $list44 = list(makeSymbol("ROOT-NODE"));

    static private final SubLList $list45 = list(makeString("index all queries in ROOT-NODE folder and its subfolders"), list(makeSymbol("CLET"), list(list(makeSymbol("TEMPLATES"), list(makeSymbol("TEMPLATES-IN-FOLDER"), makeSymbol("ROOT-NODE"), reader_make_constant_shell("InferencePSC"), T)), list(makeSymbol("ERROR"))), list(makeSymbol("WITH-PPH-MEMOIZATION"), list(makeSymbol("CDOLIST"), list(makeSymbol("TEMPLATE"), makeSymbol("TEMPLATES")), list(makeSymbol("CATCH-ERROR-MESSAGE"), list(makeSymbol("ERROR")), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-AN-ITEM")), makeSymbol("TEMPLATE"))), list(makeSymbol("PWHEN"), makeSymbol("ERROR"), list(makeSymbol("REPORT-QL-INDEX-INITIALIZATION-ERROR"), makeSymbol("TEMPLATE"), makeSymbol("ERROR")), list(makeSymbol("CSETQ"), makeSymbol("ERROR"), NIL))))), list(RET, makeSymbol("SELF")));



    private static final SubLSymbol INDEX_AN_ITEM = makeSymbol("INDEX-AN-ITEM");

    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX_INDEX_ALL_QUERIES_IN_FOLDER_METHOD = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-INDEX-ALL-QUERIES-IN-FOLDER-METHOD");

    private static final SubLSymbol INDEX_QUERY_WITH_FORMULA_AND_GLOSS = makeSymbol("INDEX-QUERY-WITH-FORMULA-AND-GLOSS");

    private static final SubLList $list52 = list(makeSymbol("TEMPLATE"), makeSymbol("FORMULA"), makeSymbol("GLOSS"));

    static private final SubLList $list53 = list(makeString("index a particular QUERY-TEMPLATE using FORMULA and GLOSS"), list(makeSymbol("CLET"), list(list(makeSymbol("QUERY-FORMULA-ENCAPSULATED"), list(makeSymbol("LIST"), makeKeyword("TEMPLATE"), makeSymbol("TEMPLATE"), makeKeyword("QUERY-GLOSS"), makeSymbol("GLOSS"), makeKeyword("FORMULA"), makeSymbol("FORMULA")))), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-AN-ITEM")), makeSymbol("QUERY-FORMULA-ENCAPSULATED"))), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol $sym57$QUERY_TEMPLATE_INVERTED_INDEX_INDEX_QUERY_WITH_FORMULA_AND_GLOSS_ = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-INDEX-QUERY-WITH-FORMULA-AND-GLOSS-METHOD");

    private static final SubLList $list58 = list(list(makeSymbol("GENERATION-MT"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("GENERATION-DOMAIN-MT"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("PARENT-INDEX"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COMPUTE-INDEX-ENTRIES"), list(makeSymbol("ITEM-TO-INDEX")), makeKeyword("PUBLIC")));

    private static final SubLSymbol GENERATION_DOMAIN_MT = makeSymbol("GENERATION-DOMAIN-MT");

    private static final SubLSymbol $sym62$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-CLASS");

    static private final SubLSymbol $sym63$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-INSTANCE");

    private static final SubLList $list64 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list65 = list(makeSymbol("INVERTED-INDEX"));

    static private final SubLList $list66 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("INVERTED-INDEX"), makeSymbol("INVERTED-INDEX-P")), list(makeSymbol("CSETQ"), makeSymbol("PARENT-INDEX"), makeSymbol("INVERTED-INDEX")), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol $sym67$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    private static final SubLSymbol INVERTED_INDEX_P = makeSymbol("INVERTED-INDEX-P");

    private static final SubLSymbol QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_INDEX_METHOD = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-INDEX-METHOD");

    private static final SubLSymbol SET_GENERATION_MT = makeSymbol("SET-GENERATION-MT");

    private static final SubLList $list71 = list(makeSymbol("MT"));

    static private final SubLList $list72 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("MT"), makeSymbol("HLMT-P")), list(makeSymbol("CSETQ"), makeSymbol("GENERATION-MT"), makeSymbol("MT")), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol $sym73$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    private static final SubLSymbol $sym75$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_MT_ME = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-MT-METHOD");

    private static final SubLSymbol SET_GENERATION_DOMAIN_MT = makeSymbol("SET-GENERATION-DOMAIN-MT");

    private static final SubLList $list77 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("MT"), makeSymbol("HLMT-P")), list(makeSymbol("CSETQ"), makeSymbol("GENERATION-DOMAIN-MT"), makeSymbol("MT")), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol $sym78$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    private static final SubLSymbol $sym79$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_DOMAI = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-DOMAIN-MT-METHOD");

    private static final SubLList $list80 = list(makeSymbol("QUERY-TEMPLATE"));

    static private final SubLList $list81 = list(list(makeSymbol("CLET"), list(list(makeSymbol("INDEXED-ITEMS"), list(makeSymbol("GET-SLOT"), makeSymbol("PARENT-INDEX"), list(QUOTE, makeSymbol("INDEXED-ITEMS")))), makeSymbol("QUERY-TEMPLATE-QUERY"), makeSymbol("COMMENT"), makeSymbol("RESULT")), list(makeSymbol("PCOND"), list(list(makeSymbol("QUERY-TEMPLATE-ENCAPSULATED-P"), makeSymbol("QUERY-TEMPLATE")), list(makeSymbol("CSETQ"), makeSymbol("QUERY-TEMPLATE-QUERY"), list(makeSymbol("GETF"), makeSymbol("QUERY-TEMPLATE"), makeKeyword("FORMULA"))), list(makeSymbol("CSETQ"), makeSymbol("COMMENT"), list(makeSymbol("GETF"), makeSymbol("QUERY-TEMPLATE"), makeKeyword("QUERY-GLOSS"), makeString(""))), list(makeSymbol("CSETQ"), makeSymbol("QUERY-TEMPLATE"), list(makeSymbol("GETF"), makeSymbol("QUERY-TEMPLATE"), makeKeyword("TEMPLATE")))), list(T, list(makeSymbol("CMULTIPLE-VALUE-SETQ"), list(makeSymbol("QUERY-TEMPLATE-QUERY"), makeSymbol("COMMENT")), list(makeSymbol("QUERY-AND-GLOSS-FOR-FORMULA-TEMPLATE"), makeSymbol("QUERY-TEMPLATE"))))), list(makeSymbol("CLET"), list(list(makeSymbol("MY-INDEX"), list(makeSymbol("MAYBE-ADD-NEW-INDEXED-ITEM"), makeSymbol("INDEXED-ITEMS"), makeSymbol("QUERY-TEMPLATE")))), list(makeSymbol("CDOLIST"), list(makeSymbol("FORT"), list(makeSymbol("EXPRESSION-GATHER"), makeSymbol("QUERY-TEMPLATE-QUERY"), list(QUOTE, makeSymbol("FORT-P")), T)), list(makeSymbol("CPUSH"), list(makeSymbol("NEW-INVERTED-INDEX-ENTRY"), makeSymbol("FORT"), makeSymbol("MY-INDEX")), makeSymbol("RESULT"))), list(makeSymbol("PWHEN"), makeSymbol("*QUERY-INDEX-INDEX-GENERATION?*"), list(makeSymbol("CDOLIST"), list(makeSymbol("ENTRY"), list(makeSymbol("COMPUTE-INVERTED-INDEX-ENTRIES-FOR-STRING"), list(makeSymbol("GENERATE-QUESTION"), makeSymbol("QUERY-TEMPLATE-QUERY")), makeSymbol("MY-INDEX"))), list(makeSymbol("CPUSH"), makeSymbol("ENTRY"), makeSymbol("RESULT")))), list(makeSymbol("PWHEN"), makeSymbol("COMMENT"), list(makeSymbol("CDOLIST"), list(makeSymbol("ENTRY"), list(makeSymbol("COMPUTE-INVERTED-INDEX-ENTRIES-FOR-STRING"), makeSymbol("COMMENT"), makeSymbol("MY-INDEX"))), list(makeSymbol("CPUSH"), makeSymbol("ENTRY"), makeSymbol("RESULT")))), list(RET, makeSymbol("RESULT")))));

    private static final SubLSymbol $sym82$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    private static final SubLString $str83$ = makeString("");

    private static final SubLSymbol $sym85$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_COMPUTE_INDEX_ENTRIE = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-COMPUTE-INDEX-ENTRIES-METHOD");

    private static final SubLList $list86 = list(makeString("application.cyc-ir.lucene.ql-index.in-memory"));

    private static final SubLSymbol $ql_build_index_in_memory$ = makeSymbol("*QL-BUILD-INDEX-IN-MEMORY*");

    private static final SubLList $list93 = list(makeSymbol("CYCL"), makeSymbol("WEIGHT"));

    private static final SubLSymbol DISABLE_QL_IR_SEARCH = makeSymbol("DISABLE-QL-IR-SEARCH");

    private static final SubLSymbol ENABLE_QL_IR_SEARCH = makeSymbol("ENABLE-QL-IR-SEARCH");



    private static final SubLObject $const97$querySpecificationForFormulaTempl = reader_make_constant_shell("querySpecificationForFormulaTemplate");

    private static final SubLSymbol $REMOVAL_LOOKUP_POS = makeKeyword("REMOVAL-LOOKUP-POS");

    private static final SubLList $list104 = list(makeKeyword("MAX-NUMBER"), ONE_INTEGER);





    private static final SubLList $list108 = list(makeKeyword("X"));

    private static final SubLList $list109 = list(makeKeyword("MAX-NUMBER"), ONE_INTEGER, makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"), makeKeyword("MAX-TRANSFORMATION-DEPTH"), ZERO_INTEGER, makeKeyword("ALLOWED-MODULES"), makeKeyword("REMOVAL-ASSERTED-SENTENCE-LOOKUP-POS"));





    public static final SubLObject subloop_reserved_initialize_query_template_inverted_index_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_query_template_inverted_index_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_query_template_inverted_index_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, PHYSICAL_INDEX, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, INDEX_ENTRY_COMPUTER, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, VALUE_TEST, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, INDEXED_ITEMS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_query_template_inverted_index_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, PHYSICAL_INDEX, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, INDEX_ENTRY_COMPUTER, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, VALUE_TEST, NIL);
        classes.subloop_initialize_slot(new_instance, INVERTED_INDEX, INDEXED_ITEMS, NIL);
        return NIL;
    }

    public static final SubLObject query_template_inverted_index_p_alt(SubLObject query_template_inverted_index) {
        return classes.subloop_instanceof_class(query_template_inverted_index, QUERY_TEMPLATE_INVERTED_INDEX);
    }

    public static SubLObject query_template_inverted_index_p(final SubLObject query_template_inverted_index) {
        return classes.subloop_instanceof_class(query_template_inverted_index, QUERY_TEMPLATE_INVERTED_INDEX);
    }

    public static final SubLObject query_template_inverted_index_initialize_method_alt(SubLObject self) {
        instances.set_slot(self, VALUE_TEST, symbol_function(EQUALP));
        instances.set_slot(self, INDEXED_ITEMS, inverted_index.new_indexed_items());
        instances.set_slot(self, INDEX_ENTRY_COMPUTER, object.new_class_instance(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER));
        methods.funcall_instance_method_with_1_args(instances.get_slot(self, INDEX_ENTRY_COMPUTER), SET_INDEX, self);
        instances.set_slot(self, PHYSICAL_INDEX, object.new_class_instance(IN_MEMORY_EQUALP_INDEX));
        return self;
    }

    public static SubLObject query_template_inverted_index_initialize_method(final SubLObject self) {
        instances.set_slot(self, VALUE_TEST, symbol_function(EQUALP));
        instances.set_slot(self, INDEXED_ITEMS, inverted_index.new_indexed_items());
        instances.set_slot(self, INDEX_ENTRY_COMPUTER, object.new_class_instance(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER));
        methods.funcall_instance_method_with_1_args(instances.get_slot(self, INDEX_ENTRY_COMPUTER), SET_INDEX, self);
        instances.set_slot(self, PHYSICAL_INDEX, object.new_class_instance(IN_MEMORY_EQUALP_INDEX));
        return self;
    }

    /**
     *
     *
     * @param KEY
    stringp;
     * 		
     * @return list; a list of (query weight) lists that are indexed on at least one word in KEY.  In the case of multiple matches,
    return queries with more words matching the key first.  Also accepts CycL as the key, in which case it
    must be one (not more than one) of the terms in the template's cycl.
     */
    @LispMethod(comment = "@param KEY\nstringp;\r\n\t\t\r\n@return list; a list of (query weight) lists that are indexed on at least one word in KEY.  In the case of multiple matches,\r\nreturn queries with more words matching the key first.  Also accepts CycL as the key, in which case it\r\nmust be one (not more than one) of the terms in the template\'s cycl.")
    public static final SubLObject query_template_inverted_index_lookup_method_alt(SubLObject self, SubLObject key) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_query_template_inverted_index_method = NIL;
                SubLObject indexed_items = inverted_index.get_inverted_index_indexed_items(self);
                SubLObject physical_index = inverted_index.get_inverted_index_physical_index(self);
                try {
                    try {
                        {
                            SubLObject lookup_items = (key.isString()) ? ((SubLObject) (document.string_tokenize_via_wordify(key))) : list(key);
                            SubLObject lookup_item_count = length(remove_if($sym25$QUERY_TEMPLATE_INVERTED_INDEX_STOP_WORD_, lookup_items, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                            SubLObject dict = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
                            SubLObject scored = NIL;
                            {
                                SubLObject cdolist_list_var = lookup_items;
                                SubLObject key_1 = NIL;
                                for (key_1 = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , key_1 = cdolist_list_var.first()) {
                                    {
                                        SubLObject cdolist_list_var_2 = methods.funcall_instance_method_with_1_args(physical_index, LOOKUP, key_1);
                                        SubLObject suid = NIL;
                                        for (suid = cdolist_list_var_2.first(); NIL != cdolist_list_var_2; cdolist_list_var_2 = cdolist_list_var_2.rest() , suid = cdolist_list_var_2.first()) {
                                            {
                                                SubLObject existing_count = dictionary.dictionary_lookup(dict, suid, ZERO_INTEGER);
                                                dictionary.dictionary_enter(dict, suid, number_utilities.f_1X(existing_count));
                                            }
                                        }
                                    }
                                }
                            }
                            {
                                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(dict));
                                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject suid = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                        SubLObject score = thread.secondMultipleValue();
                                        thread.resetMultipleValues();
                                        scored = cons(list(inverted_index.get_item_from_index_suid(indexed_items, suid), score), scored);
                                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                    }
                                } 
                                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                            }
                            {
                                SubLObject normalized = NIL;
                                SubLObject highest_weight = ZERO_INTEGER;
                                SubLObject cdolist_list_var = scored;
                                SubLObject item = NIL;
                                for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                                    {
                                        SubLObject raw_weight = second(item);
                                        SubLObject normalized_weight = divide(raw_weight, lookup_item_count);
                                        if (raw_weight.numG(highest_weight)) {
                                            highest_weight = raw_weight;
                                        }
                                        normalized = cons(list(item.first(), normalized_weight), normalized);
                                    }
                                }
                                normalized = com.cyc.cycjava.cycl.inverted_index_query_library.remove_low_weighted_items(normalized, highest_weight, lookup_item_count);
                                normalized = Sort.sort(normalized, $sym26$_, SECOND);
                                sublisp_throw($sym24$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD, normalized);
                            }
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                inverted_index.set_inverted_index_indexed_items(self, indexed_items);
                                inverted_index.set_inverted_index_physical_index(self, physical_index);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_query_template_inverted_index_method = Errors.handleThrowable(ccatch_env_var, $sym24$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
                }
                return catch_var_for_query_template_inverted_index_method;
            }
        }
    }

    /**
     *
     *
     * @param KEY
    stringp;
     * 		
     * @return list; a list of (query weight) lists that are indexed on at least one word in KEY.  In the case of multiple matches,
    return queries with more words matching the key first.  Also accepts CycL as the key, in which case it
    must be one (not more than one) of the terms in the template's cycl.
     */
    @LispMethod(comment = "@param KEY\nstringp;\r\n\t\t\r\n@return list; a list of (query weight) lists that are indexed on at least one word in KEY.  In the case of multiple matches,\r\nreturn queries with more words matching the key first.  Also accepts CycL as the key, in which case it\r\nmust be one (not more than one) of the terms in the template\'s cycl.")
    public static SubLObject query_template_inverted_index_lookup_method(final SubLObject self, final SubLObject key) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_query_template_inverted_index_method = NIL;
        final SubLObject indexed_items = inverted_index.get_inverted_index_indexed_items(self);
        final SubLObject physical_index = inverted_index.get_inverted_index_physical_index(self);
        try {
            thread.throwStack.push($sym24$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
            try {
                final SubLObject lookup_items = (key.isString()) ? document.string_tokenize_via_wordify(key) : list(key);
                final SubLObject lookup_item_count = length(remove_if($sym25$QUERY_TEMPLATE_INVERTED_INDEX_STOP_WORD_, lookup_items, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                final SubLObject dict = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
                SubLObject scored = NIL;
                SubLObject cdolist_list_var = lookup_items;
                SubLObject key_$1 = NIL;
                key_$1 = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject cdolist_list_var_$2 = methods.funcall_instance_method_with_1_args(physical_index, LOOKUP, key_$1);
                    SubLObject suid = NIL;
                    suid = cdolist_list_var_$2.first();
                    while (NIL != cdolist_list_var_$2) {
                        final SubLObject existing_count = dictionary.dictionary_lookup(dict, suid, ZERO_INTEGER);
                        dictionary.dictionary_enter(dict, suid, number_utilities.f_1X(existing_count));
                        cdolist_list_var_$2 = cdolist_list_var_$2.rest();
                        suid = cdolist_list_var_$2.first();
                    } 
                    cdolist_list_var = cdolist_list_var.rest();
                    key_$1 = cdolist_list_var.first();
                } 
                SubLObject iteration_state;
                for (iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(dict)); NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state); iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state)) {
                    thread.resetMultipleValues();
                    final SubLObject suid2 = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                    final SubLObject score = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    scored = cons(list(inverted_index.get_item_from_index_suid(indexed_items, suid2), score), scored);
                }
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                SubLObject normalized = NIL;
                SubLObject highest_weight = ZERO_INTEGER;
                SubLObject cdolist_list_var2 = scored;
                SubLObject item = NIL;
                item = cdolist_list_var2.first();
                while (NIL != cdolist_list_var2) {
                    final SubLObject raw_weight = second(item);
                    final SubLObject normalized_weight = divide(raw_weight, lookup_item_count);
                    if (raw_weight.numG(highest_weight)) {
                        highest_weight = raw_weight;
                    }
                    normalized = cons(list(item.first(), normalized_weight), normalized);
                    cdolist_list_var2 = cdolist_list_var2.rest();
                    item = cdolist_list_var2.first();
                } 
                normalized = remove_low_weighted_items(normalized, highest_weight, lookup_item_count);
                normalized = Sort.sort(normalized, $sym26$_, SECOND);
                sublisp_throw($sym24$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD, normalized);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    inverted_index.set_inverted_index_indexed_items(self, indexed_items);
                    inverted_index.set_inverted_index_physical_index(self, physical_index);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_query_template_inverted_index_method = Errors.handleThrowable(ccatch_env_var, $sym24$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_query_template_inverted_index_method;
    }

    public static final SubLObject remove_low_weighted_items_alt(SubLObject items, SubLObject max_weight, SubLObject max_weight_possible) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                {
                    SubLObject _prev_bind_0 = $min_query_weight$.currentBinding(thread);
                    try {
                        $min_query_weight$.bind(divide(multiply(max_weight, $ql_search_weight_factor$.getDynamicValue(thread)), max_weight_possible), thread);
                        result = remove_if($sym30$NORMALIZED_WEIGHT_TOO_LOW_, items, SECOND, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                    } finally {
                        $min_query_weight$.rebind(_prev_bind_0, thread);
                    }
                }
                return result;
            }
        }
    }

    public static SubLObject remove_low_weighted_items(final SubLObject items, final SubLObject max_weight, final SubLObject max_weight_possible) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        final SubLObject _prev_bind_0 = $min_query_weight$.currentBinding(thread);
        try {
            $min_query_weight$.bind(divide(multiply(max_weight, $ql_search_weight_factor$.getDynamicValue(thread)), max_weight_possible), thread);
            result = remove_if($sym30$NORMALIZED_WEIGHT_TOO_LOW_, items, SECOND, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        } finally {
            $min_query_weight$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static final SubLObject normalized_weight_too_lowP_alt(SubLObject weight) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return numL(weight, $min_query_weight$.getDynamicValue(thread));
        }
    }

    public static SubLObject normalized_weight_too_lowP(final SubLObject weight) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return numL(weight, $min_query_weight$.getDynamicValue(thread));
    }

    public static final SubLObject query_template_inverted_index_remove_key_method_alt(SubLObject self, SubLObject index_key) {
        {
            SubLObject catch_var_for_query_template_inverted_index_method = NIL;
            SubLObject index_entry_computer = inverted_index.get_inverted_index_index_entry_computer(self);
            try {
                try {
                    {
                        SubLObject entries = methods.funcall_instance_method_with_1_args(index_entry_computer, COMPUTE_INDEX_ENTRIES, index_key);
                        SubLObject cdolist_list_var = entries;
                        SubLObject entry = NIL;
                        for (entry = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , entry = cdolist_list_var.first()) {
                            methods.funcall_instance_method_with_1_args(self, REMOVE_VALUE, entry);
                        }
                        sublisp_throw($sym34$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD, self);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            inverted_index.set_inverted_index_index_entry_computer(self, index_entry_computer);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_query_template_inverted_index_method = Errors.handleThrowable(ccatch_env_var, $sym34$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
            }
            return catch_var_for_query_template_inverted_index_method;
        }
    }

    public static SubLObject query_template_inverted_index_remove_key_method(final SubLObject self, final SubLObject index_key) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_query_template_inverted_index_method = NIL;
        final SubLObject index_entry_computer = inverted_index.get_inverted_index_index_entry_computer(self);
        try {
            thread.throwStack.push($sym34$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
            try {
                SubLObject cdolist_list_var;
                final SubLObject entries = cdolist_list_var = methods.funcall_instance_method_with_1_args(index_entry_computer, COMPUTE_INDEX_ENTRIES, index_key);
                SubLObject entry = NIL;
                entry = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    methods.funcall_instance_method_with_1_args(self, REMOVE_VALUE, entry);
                    cdolist_list_var = cdolist_list_var.rest();
                    entry = cdolist_list_var.first();
                } 
                sublisp_throw($sym34$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    inverted_index.set_inverted_index_index_entry_computer(self, index_entry_computer);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_query_template_inverted_index_method = Errors.handleThrowable(ccatch_env_var, $sym34$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_query_template_inverted_index_method;
    }

    public static final SubLObject query_template_inverted_index_remove_value_method_alt(SubLObject self, SubLObject index_entry) {
        {
            SubLObject catch_var_for_query_template_inverted_index_method = NIL;
            SubLObject physical_index = inverted_index.get_inverted_index_physical_index(self);
            try {
                try {
                    methods.funcall_instance_method_with_1_args(physical_index, REMOVE, index_entry);
                    sublisp_throw($sym40$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            inverted_index.set_inverted_index_physical_index(self, physical_index);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_query_template_inverted_index_method = Errors.handleThrowable(ccatch_env_var, $sym40$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
            }
            return catch_var_for_query_template_inverted_index_method;
        }
    }

    public static SubLObject query_template_inverted_index_remove_value_method(final SubLObject self, final SubLObject index_entry) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_query_template_inverted_index_method = NIL;
        final SubLObject physical_index = inverted_index.get_inverted_index_physical_index(self);
        try {
            thread.throwStack.push($sym40$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
            try {
                methods.funcall_instance_method_with_1_args(physical_index, REMOVE, index_entry);
                sublisp_throw($sym40$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    inverted_index.set_inverted_index_physical_index(self, physical_index);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_query_template_inverted_index_method = Errors.handleThrowable(ccatch_env_var, $sym40$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_query_template_inverted_index_method;
    }

    /**
     * index all queries in ROOT-NODE folder and its subfolders
     */
    @LispMethod(comment = "index all queries in ROOT-NODE folder and its subfolders")
    public static final SubLObject query_template_inverted_index_index_all_queries_in_folder_method_alt(SubLObject self, SubLObject root_node) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject templates = ke_interaction_folder.templates_in_folder(root_node, $$InferencePSC, T);
                SubLObject error = NIL;
                thread.resetMultipleValues();
                {
                    SubLObject _prev_bind_0 = pph_macros.$pph_problem_store_pointer$.currentBinding(thread);
                    try {
                        pph_macros.$pph_problem_store_pointer$.bind(pph_macros.find_or_create_pph_problem_store_pointer(), thread);
                        {
                            SubLObject reuseP = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            try {
                                thread.resetMultipleValues();
                                {
                                    SubLObject _prev_bind_0_3 = pph_macros.$pph_memoization_state$.currentBinding(thread);
                                    try {
                                        pph_macros.$pph_memoization_state$.bind(pph_macros.find_or_create_pph_memoization_state(), thread);
                                        {
                                            SubLObject new_or_reused = thread.secondMultipleValue();
                                            thread.resetMultipleValues();
                                            {
                                                SubLObject _prev_bind_0_4 = pph_macros.$pph_external_memoization_state$.currentBinding(thread);
                                                try {
                                                    pph_macros.$pph_external_memoization_state$.bind(pph_macros.find_or_create_pph_external_memoization_state(), thread);
                                                    {
                                                        SubLObject local_state = pph_macros.$pph_memoization_state$.getDynamicValue(thread);
                                                        {
                                                            SubLObject _prev_bind_0_5 = memoization_state.$memoization_state$.currentBinding(thread);
                                                            try {
                                                                memoization_state.$memoization_state$.bind(local_state, thread);
                                                                {
                                                                    SubLObject original_memoization_process = NIL;
                                                                    if ((NIL != local_state) && (NIL == memoization_state.memoization_state_lock(local_state))) {
                                                                        original_memoization_process = memoization_state.memoization_state_get_current_process_internal(local_state);
                                                                        {
                                                                            SubLObject current_proc = current_process();
                                                                            if (NIL == original_memoization_process) {
                                                                                memoization_state.memoization_state_set_current_process_internal(local_state, current_proc);
                                                                            } else {
                                                                                if (original_memoization_process != current_proc) {
                                                                                    Errors.error($str_alt47$Invalid_attempt_to_reuse_memoizat);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    try {
                                                                        {
                                                                            SubLObject cdolist_list_var = templates;
                                                                            SubLObject template = NIL;
                                                                            for (template = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , template = cdolist_list_var.first()) {
                                                                                try {
                                                                                    {
                                                                                        SubLObject _prev_bind_0_6 = Errors.$error_handler$.currentBinding(thread);
                                                                                        try {
                                                                                            Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                                                            try {
                                                                                                methods.funcall_instance_method_with_1_args(self, INDEX_AN_ITEM, template);
                                                                                            } catch (Throwable catch_var) {
                                                                                                Errors.handleThrowable(catch_var, NIL);
                                                                                            }
                                                                                        } finally {
                                                                                            Errors.$error_handler$.rebind(_prev_bind_0_6, thread);
                                                                                        }
                                                                                    }
                                                                                } catch (Throwable ccatch_env_var) {
                                                                                    error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                                                                }
                                                                                if (NIL != error) {
                                                                                    com.cyc.cycjava.cycl.inverted_index_query_library.report_ql_index_initialization_error(template, error);
                                                                                    error = NIL;
                                                                                }
                                                                            }
                                                                        }
                                                                    } finally {
                                                                        {
                                                                            SubLObject _prev_bind_0_7 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                                            try {
                                                                                $is_thread_performing_cleanupP$.bind(T, thread);
                                                                                if ((NIL != local_state) && (NIL == original_memoization_process)) {
                                                                                    memoization_state.memoization_state_set_current_process_internal(local_state, NIL);
                                                                                }
                                                                            } finally {
                                                                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0_7, thread);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } finally {
                                                                memoization_state.$memoization_state$.rebind(_prev_bind_0_5, thread);
                                                            }
                                                        }
                                                    }
                                                } finally {
                                                    pph_macros.$pph_external_memoization_state$.rebind(_prev_bind_0_4, thread);
                                                }
                                            }
                                            if ((new_or_reused == $NEW) && (NIL != memoization_state.memoization_state_p(pph_macros.$pph_memoization_state$.getDynamicValue(thread)))) {
                                                memoization_state.clear_all_memoization(pph_macros.$pph_memoization_state$.getDynamicValue(thread));
                                            }
                                        }
                                    } finally {
                                        pph_macros.$pph_memoization_state$.rebind(_prev_bind_0_3, thread);
                                    }
                                }
                            } finally {
                                {
                                    SubLObject _prev_bind_0_8 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        if (NIL == reuseP) {
                                            pph_macros.free_pph_problem_store_pointer(pph_macros.$pph_problem_store_pointer$.getDynamicValue(thread));
                                        }
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_8, thread);
                                    }
                                }
                            }
                        }
                    } finally {
                        pph_macros.$pph_problem_store_pointer$.rebind(_prev_bind_0, thread);
                    }
                }
                return self;
            }
        }
    }

    /**
     * index all queries in ROOT-NODE folder and its subfolders
     */
    @LispMethod(comment = "index all queries in ROOT-NODE folder and its subfolders")
    public static SubLObject query_template_inverted_index_index_all_queries_in_folder_method(final SubLObject self, final SubLObject root_node) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject templates = ke_interaction_folder.templates_in_folder(root_node, $$InferencePSC, T);
        SubLObject error = NIL;
        final SubLObject _prev_bind_0 = pph_macros.$free_pph_problem_store_pointers$.currentBinding(thread);
        try {
            pph_macros.$free_pph_problem_store_pointers$.bind(NIL, thread);
            thread.resetMultipleValues();
            final SubLObject _prev_bind_0_$3 = pph_macros.$pph_problem_store_pointer$.currentBinding(thread);
            try {
                pph_macros.$pph_problem_store_pointer$.bind(pph_macros.find_or_create_pph_problem_store_pointer(), thread);
                final SubLObject reuseP = thread.secondMultipleValue();
                thread.resetMultipleValues();
                try {
                    thread.resetMultipleValues();
                    final SubLObject _prev_bind_0_$4 = pph_macros.$pph_memoization_state$.currentBinding(thread);
                    try {
                        pph_macros.$pph_memoization_state$.bind(pph_macros.find_or_create_pph_memoization_state(), thread);
                        final SubLObject new_or_reused = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        final SubLObject _prev_bind_0_$5 = pph_macros.$pph_external_memoization_state$.currentBinding(thread);
                        try {
                            pph_macros.$pph_external_memoization_state$.bind(pph_macros.find_or_create_pph_external_memoization_state(), thread);
                            final SubLObject local_state = pph_macros.$pph_memoization_state$.getDynamicValue(thread);
                            final SubLObject _prev_bind_0_$6 = memoization_state.$memoization_state$.currentBinding(thread);
                            try {
                                memoization_state.$memoization_state$.bind(local_state, thread);
                                final SubLObject original_memoization_process = memoization_state.memoization_state_original_process(local_state);
                                try {
                                    SubLObject cdolist_list_var = templates;
                                    SubLObject template = NIL;
                                    template = cdolist_list_var.first();
                                    while (NIL != cdolist_list_var) {
                                        try {
                                            thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                                            final SubLObject _prev_bind_0_$7 = Errors.$error_handler$.currentBinding(thread);
                                            try {
                                                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                try {
                                                    methods.funcall_instance_method_with_1_args(self, INDEX_AN_ITEM, template);
                                                } catch (final Throwable catch_var) {
                                                    Errors.handleThrowable(catch_var, NIL);
                                                }
                                            } finally {
                                                Errors.$error_handler$.rebind(_prev_bind_0_$7, thread);
                                            }
                                        } catch (final Throwable ccatch_env_var) {
                                            error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                        } finally {
                                            thread.throwStack.pop();
                                        }
                                        if (NIL != error) {
                                            report_ql_index_initialization_error(template, error);
                                            error = NIL;
                                        }
                                        cdolist_list_var = cdolist_list_var.rest();
                                        template = cdolist_list_var.first();
                                    } 
                                } finally {
                                    final SubLObject _prev_bind_0_$8 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                    try {
                                        $is_thread_performing_cleanupP$.bind(T, thread);
                                        final SubLObject _values = getValuesAsVector();
                                        memoization_state.memoization_state_possibly_clear_original_process(local_state, original_memoization_process);
                                        restoreValuesFromVector(_values);
                                    } finally {
                                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$8, thread);
                                    }
                                }
                            } finally {
                                memoization_state.$memoization_state$.rebind(_prev_bind_0_$6, thread);
                            }
                        } finally {
                            pph_macros.$pph_external_memoization_state$.rebind(_prev_bind_0_$5, thread);
                        }
                        if ((new_or_reused == $NEW) && (NIL != memoization_state.memoization_state_p(pph_macros.$pph_memoization_state$.getDynamicValue(thread)))) {
                            memoization_state.clear_all_memoization(pph_macros.$pph_memoization_state$.getDynamicValue(thread));
                        }
                    } finally {
                        pph_macros.$pph_memoization_state$.rebind(_prev_bind_0_$4, thread);
                    }
                } finally {
                    final SubLObject _prev_bind_0_$9 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values2 = getValuesAsVector();
                        if (NIL == reuseP) {
                            pph_macros.free_pph_problem_store_pointer(pph_macros.$pph_problem_store_pointer$.getDynamicValue(thread));
                        }
                        restoreValuesFromVector(_values2);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$9, thread);
                    }
                }
            } finally {
                pph_macros.$pph_problem_store_pointer$.rebind(_prev_bind_0_$3, thread);
            }
        } finally {
            pph_macros.$free_pph_problem_store_pointers$.rebind(_prev_bind_0, thread);
        }
        return self;
    }

    /**
     * index a particular QUERY-TEMPLATE using FORMULA and GLOSS
     */
    @LispMethod(comment = "index a particular QUERY-TEMPLATE using FORMULA and GLOSS")
    public static final SubLObject query_template_inverted_index_index_query_with_formula_and_gloss_method_alt(SubLObject self, SubLObject template, SubLObject formula, SubLObject gloss) {
        {
            SubLObject query_formula_encapsulated = list($TEMPLATE, template, $QUERY_GLOSS, gloss, $FORMULA, formula);
            methods.funcall_instance_method_with_1_args(self, INDEX_AN_ITEM, query_formula_encapsulated);
            return self;
        }
    }

    /**
     * index a particular QUERY-TEMPLATE using FORMULA and GLOSS
     */
    @LispMethod(comment = "index a particular QUERY-TEMPLATE using FORMULA and GLOSS")
    public static SubLObject query_template_inverted_index_index_query_with_formula_and_gloss_method(final SubLObject self, final SubLObject template, final SubLObject formula, final SubLObject gloss) {
        final SubLObject query_formula_encapsulated = list($TEMPLATE, template, $QUERY_GLOSS, gloss, $FORMULA, formula);
        methods.funcall_instance_method_with_1_args(self, INDEX_AN_ITEM, query_formula_encapsulated);
        return self;
    }

    public static final SubLObject get_query_template_inverted_index_entry_computer_parent_index_alt(SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_get_access_protected_instance_slot(query_template_inverted_index_entry_computer, THREE_INTEGER, PARENT_INDEX);
    }

    public static SubLObject get_query_template_inverted_index_entry_computer_parent_index(final SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_get_access_protected_instance_slot(query_template_inverted_index_entry_computer, THREE_INTEGER, PARENT_INDEX);
    }

    public static final SubLObject set_query_template_inverted_index_entry_computer_parent_index_alt(SubLObject query_template_inverted_index_entry_computer, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(query_template_inverted_index_entry_computer, value, THREE_INTEGER, PARENT_INDEX);
    }

    public static SubLObject set_query_template_inverted_index_entry_computer_parent_index(final SubLObject query_template_inverted_index_entry_computer, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(query_template_inverted_index_entry_computer, value, THREE_INTEGER, PARENT_INDEX);
    }

    public static final SubLObject get_query_template_inverted_index_entry_computer_generation_domain_mt_alt(SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_get_access_protected_instance_slot(query_template_inverted_index_entry_computer, TWO_INTEGER, GENERATION_DOMAIN_MT);
    }

    public static SubLObject get_query_template_inverted_index_entry_computer_generation_domain_mt(final SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_get_access_protected_instance_slot(query_template_inverted_index_entry_computer, TWO_INTEGER, GENERATION_DOMAIN_MT);
    }

    public static final SubLObject set_query_template_inverted_index_entry_computer_generation_domain_mt_alt(SubLObject query_template_inverted_index_entry_computer, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(query_template_inverted_index_entry_computer, value, TWO_INTEGER, GENERATION_DOMAIN_MT);
    }

    public static SubLObject set_query_template_inverted_index_entry_computer_generation_domain_mt(final SubLObject query_template_inverted_index_entry_computer, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(query_template_inverted_index_entry_computer, value, TWO_INTEGER, GENERATION_DOMAIN_MT);
    }

    public static final SubLObject get_query_template_inverted_index_entry_computer_generation_mt_alt(SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_get_access_protected_instance_slot(query_template_inverted_index_entry_computer, ONE_INTEGER, GENERATION_MT);
    }

    public static SubLObject get_query_template_inverted_index_entry_computer_generation_mt(final SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_get_access_protected_instance_slot(query_template_inverted_index_entry_computer, ONE_INTEGER, GENERATION_MT);
    }

    public static final SubLObject set_query_template_inverted_index_entry_computer_generation_mt_alt(SubLObject query_template_inverted_index_entry_computer, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(query_template_inverted_index_entry_computer, value, ONE_INTEGER, GENERATION_MT);
    }

    public static SubLObject set_query_template_inverted_index_entry_computer_generation_mt(final SubLObject query_template_inverted_index_entry_computer, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(query_template_inverted_index_entry_computer, value, ONE_INTEGER, GENERATION_MT);
    }

    public static final SubLObject subloop_reserved_initialize_query_template_inverted_index_entry_computer_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_query_template_inverted_index_entry_computer_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_query_template_inverted_index_entry_computer_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, GENERATION_MT, NIL);
        classes.subloop_initialize_slot(new_instance, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, GENERATION_DOMAIN_MT, NIL);
        classes.subloop_initialize_slot(new_instance, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, PARENT_INDEX, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_query_template_inverted_index_entry_computer_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, GENERATION_MT, NIL);
        classes.subloop_initialize_slot(new_instance, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, GENERATION_DOMAIN_MT, NIL);
        classes.subloop_initialize_slot(new_instance, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, PARENT_INDEX, NIL);
        return NIL;
    }

    public static final SubLObject query_template_inverted_index_entry_computer_p_alt(SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_instanceof_class(query_template_inverted_index_entry_computer, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER);
    }

    public static SubLObject query_template_inverted_index_entry_computer_p(final SubLObject query_template_inverted_index_entry_computer) {
        return classes.subloop_instanceof_class(query_template_inverted_index_entry_computer, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER);
    }

    public static final SubLObject query_template_inverted_index_entry_computer_set_index_method_alt(SubLObject self, SubLObject v_inverted_index) {
        {
            SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
            SubLObject parent_index = com.cyc.cycjava.cycl.inverted_index_query_library.get_query_template_inverted_index_entry_computer_parent_index(self);
            try {
                try {
                    SubLTrampolineFile.checkType(v_inverted_index, INVERTED_INDEX_P);
                    parent_index = v_inverted_index;
                    sublisp_throw($sym68$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.inverted_index_query_library.set_query_template_inverted_index_entry_computer_parent_index(self, parent_index);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym68$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
            }
            return catch_var_for_query_template_inverted_index_entry_computer_method;
        }
    }

    public static SubLObject query_template_inverted_index_entry_computer_set_index_method(final SubLObject self, final SubLObject v_inverted_index) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
        SubLObject parent_index = get_query_template_inverted_index_entry_computer_parent_index(self);
        try {
            thread.throwStack.push($sym67$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
            try {
                assert NIL != inverted_index.inverted_index_p(v_inverted_index) : "! inverted_index.inverted_index_p(v_inverted_index) " + ("inverted_index.inverted_index_p(v_inverted_index) " + "CommonSymbols.NIL != inverted_index.inverted_index_p(v_inverted_index) ") + v_inverted_index;
                parent_index = v_inverted_index;
                sublisp_throw($sym67$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_query_template_inverted_index_entry_computer_parent_index(self, parent_index);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym67$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_query_template_inverted_index_entry_computer_method;
    }

    public static final SubLObject query_template_inverted_index_entry_computer_set_generation_mt_method_alt(SubLObject self, SubLObject mt) {
        {
            SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
            SubLObject generation_mt = com.cyc.cycjava.cycl.inverted_index_query_library.get_query_template_inverted_index_entry_computer_generation_mt(self);
            try {
                try {
                    SubLTrampolineFile.checkType(mt, HLMT_P);
                    generation_mt = mt;
                    sublisp_throw($sym74$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.inverted_index_query_library.set_query_template_inverted_index_entry_computer_generation_mt(self, generation_mt);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym74$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
            }
            return catch_var_for_query_template_inverted_index_entry_computer_method;
        }
    }

    public static SubLObject query_template_inverted_index_entry_computer_set_generation_mt_method(final SubLObject self, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
        SubLObject generation_mt = get_query_template_inverted_index_entry_computer_generation_mt(self);
        try {
            thread.throwStack.push($sym73$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
            try {
                assert NIL != hlmt.hlmt_p(mt) : "! hlmt.hlmt_p(mt) " + ("hlmt.hlmt_p(mt) " + "CommonSymbols.NIL != hlmt.hlmt_p(mt) ") + mt;
                generation_mt = mt;
                sublisp_throw($sym73$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_query_template_inverted_index_entry_computer_generation_mt(self, generation_mt);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym73$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_query_template_inverted_index_entry_computer_method;
    }

    public static final SubLObject query_template_inverted_index_entry_computer_set_generation_domain_mt_method_alt(SubLObject self, SubLObject mt) {
        {
            SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
            SubLObject generation_domain_mt = com.cyc.cycjava.cycl.inverted_index_query_library.get_query_template_inverted_index_entry_computer_generation_domain_mt(self);
            try {
                try {
                    SubLTrampolineFile.checkType(mt, HLMT_P);
                    generation_domain_mt = mt;
                    sublisp_throw($sym79$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.inverted_index_query_library.set_query_template_inverted_index_entry_computer_generation_domain_mt(self, generation_domain_mt);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym79$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
            }
            return catch_var_for_query_template_inverted_index_entry_computer_method;
        }
    }

    public static SubLObject query_template_inverted_index_entry_computer_set_generation_domain_mt_method(final SubLObject self, final SubLObject mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
        SubLObject generation_domain_mt = get_query_template_inverted_index_entry_computer_generation_domain_mt(self);
        try {
            thread.throwStack.push($sym78$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
            try {
                assert NIL != hlmt.hlmt_p(mt) : "! hlmt.hlmt_p(mt) " + ("hlmt.hlmt_p(mt) " + "CommonSymbols.NIL != hlmt.hlmt_p(mt) ") + mt;
                generation_domain_mt = mt;
                sublisp_throw($sym78$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_query_template_inverted_index_entry_computer_generation_domain_mt(self, generation_domain_mt);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym78$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_query_template_inverted_index_entry_computer_method;
    }

    public static final SubLObject query_template_inverted_index_entry_computer_compute_index_entries_method_alt(SubLObject self, SubLObject query_template) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
                SubLObject parent_index = com.cyc.cycjava.cycl.inverted_index_query_library.get_query_template_inverted_index_entry_computer_parent_index(self);
                try {
                    try {
                        {
                            SubLObject indexed_items = instances.get_slot(parent_index, INDEXED_ITEMS);
                            SubLObject query_template_query = NIL;
                            SubLObject comment = NIL;
                            SubLObject result = NIL;
                            if (NIL != com.cyc.cycjava.cycl.inverted_index_query_library.query_template_encapsulated_p(query_template)) {
                                query_template_query = getf(query_template, $FORMULA, UNPROVIDED);
                                comment = getf(query_template, $QUERY_GLOSS, $str_alt84$);
                                query_template = getf(query_template, $TEMPLATE, UNPROVIDED);
                            } else {
                                thread.resetMultipleValues();
                                {
                                    SubLObject query_template_query_9 = com.cyc.cycjava.cycl.inverted_index_query_library.query_and_gloss_for_formula_template(query_template, UNPROVIDED);
                                    SubLObject comment_10 = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    query_template_query = query_template_query_9;
                                    comment = comment_10;
                                }
                            }
                            {
                                SubLObject my_index = inverted_index.maybe_add_new_indexed_item(indexed_items, query_template);
                                {
                                    SubLObject cdolist_list_var = cycl_utilities.expression_gather(query_template_query, FORT_P, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                    SubLObject fort = NIL;
                                    for (fort = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , fort = cdolist_list_var.first()) {
                                        result = cons(inverted_index.new_inverted_index_entry(fort, my_index), result);
                                    }
                                }
                                if (NIL != $query_index_index_generationP$.getDynamicValue(thread)) {
                                    {
                                        SubLObject cdolist_list_var = com.cyc.cycjava.cycl.inverted_index_query_library.compute_inverted_index_entries_for_string(pph_question.generate_question(query_template_query, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), my_index);
                                        SubLObject entry = NIL;
                                        for (entry = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , entry = cdolist_list_var.first()) {
                                            result = cons(entry, result);
                                        }
                                    }
                                }
                                if (NIL != comment) {
                                    {
                                        SubLObject cdolist_list_var = com.cyc.cycjava.cycl.inverted_index_query_library.compute_inverted_index_entries_for_string(comment, my_index);
                                        SubLObject entry = NIL;
                                        for (entry = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , entry = cdolist_list_var.first()) {
                                            result = cons(entry, result);
                                        }
                                    }
                                }
                                sublisp_throw($sym83$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, result);
                            }
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.inverted_index_query_library.set_query_template_inverted_index_entry_computer_parent_index(self, parent_index);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym83$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
                }
                return catch_var_for_query_template_inverted_index_entry_computer_method;
            }
        }
    }

    public static SubLObject query_template_inverted_index_entry_computer_compute_index_entries_method(final SubLObject self, SubLObject query_template) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_query_template_inverted_index_entry_computer_method = NIL;
        final SubLObject parent_index = get_query_template_inverted_index_entry_computer_parent_index(self);
        try {
            thread.throwStack.push($sym82$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
            try {
                final SubLObject indexed_items = instances.get_slot(parent_index, INDEXED_ITEMS);
                SubLObject query_template_query = NIL;
                SubLObject comment = NIL;
                SubLObject result = NIL;
                if (NIL != query_template_encapsulated_p(query_template)) {
                    query_template_query = getf(query_template, $FORMULA, UNPROVIDED);
                    comment = getf(query_template, $QUERY_GLOSS, $str83$);
                    query_template = getf(query_template, $TEMPLATE, UNPROVIDED);
                } else {
                    thread.resetMultipleValues();
                    final SubLObject query_template_query_$10 = query_and_gloss_for_formula_template(query_template, UNPROVIDED);
                    final SubLObject comment_$11 = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    query_template_query = query_template_query_$10;
                    comment = comment_$11;
                }
                final SubLObject my_index = inverted_index.maybe_add_new_indexed_item(indexed_items, query_template);
                SubLObject cdolist_list_var = cycl_utilities.expression_gather(query_template_query, FORT_P, T, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                SubLObject fort = NIL;
                fort = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    result = cons(inverted_index.new_inverted_index_entry(fort, my_index), result);
                    cdolist_list_var = cdolist_list_var.rest();
                    fort = cdolist_list_var.first();
                } 
                if (NIL != $query_index_index_generationP$.getDynamicValue(thread)) {
                    cdolist_list_var = compute_inverted_index_entries_for_string(pph_question.generate_question(query_template_query, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), my_index);
                    SubLObject entry = NIL;
                    entry = cdolist_list_var.first();
                    while (NIL != cdolist_list_var) {
                        result = cons(entry, result);
                        cdolist_list_var = cdolist_list_var.rest();
                        entry = cdolist_list_var.first();
                    } 
                }
                if (NIL != comment) {
                    cdolist_list_var = compute_inverted_index_entries_for_string(comment, my_index);
                    SubLObject entry = NIL;
                    entry = cdolist_list_var.first();
                    while (NIL != cdolist_list_var) {
                        result = cons(entry, result);
                        cdolist_list_var = cdolist_list_var.rest();
                        entry = cdolist_list_var.first();
                    } 
                }
                sublisp_throw($sym82$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH, result);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_query_template_inverted_index_entry_computer_parent_index(self, parent_index);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_query_template_inverted_index_entry_computer_method = Errors.handleThrowable(ccatch_env_var, $sym82$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_query_template_inverted_index_entry_computer_method;
    }

    public static final SubLObject report_ql_index_initialization_error_alt(SubLObject template, SubLObject error) {
        {
            SubLObject from = $str_alt87$ql_qa_cyc_com;
            SubLObject message_text = cconcatenate($str_alt88$while_initializing_QL_search__hit, new SubLObject[]{ format_nil.format_nil_s_no_copy(template), format_nil.$format_nil_percent$.getGlobalValue(), $str_alt89$Error_Message__, format_nil.format_nil_s_no_copy(error), format_nil.$format_nil_percent$.getGlobalValue(), $str_alt90$Host__, format_nil.format_nil_a_no_copy(Environment.get_machine_name(UNPROVIDED)), format_nil.$format_nil_percent$.getGlobalValue(), $str_alt91$KB__, format_nil.format_nil_a_no_copy(operation_communication.kb_version_string()), format_nil.$format_nil_percent$.getGlobalValue(), $str_alt92$System__, format_nil.format_nil_a_no_copy(system_info.cyc_revision_string()), format_nil.$format_nil_percent$.getGlobalValue(), $str_alt93$User__, format_nil.format_nil_a_no_copy(operation_communication.the_cyclist()), format_nil.$format_nil_percent$.getGlobalValue() });
            SubLObject subject = cconcatenate($str_alt94$Error_loading_, new SubLObject[]{ format_nil.format_nil_s_no_copy(template), format_nil.$format_nil_percent$.getGlobalValue() });
            mail_utilities.mail_message($str_alt95$cyc_cyc_com, from, message_text, subject, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            return NIL;
        }
    }

    public static SubLObject report_ql_index_initialization_error(final SubLObject template, final SubLObject error) {
        return NIL;
    }

    public static final SubLObject compute_inverted_index_entries_for_string_alt(SubLObject string, SubLObject my_index) {
        {
            SubLObject result = NIL;
            SubLObject cdolist_list_var = document.string_tokenize_via_wordify(string);
            SubLObject word = NIL;
            for (word = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , word = cdolist_list_var.first()) {
                if (NIL == com.cyc.cycjava.cycl.inverted_index_query_library.query_template_inverted_index_stop_wordP(word)) {
                    result = cons(inverted_index.new_inverted_index_entry(word, my_index), result);
                    {
                        SubLObject stem = morphology.find_stem_memoized(word, UNPROVIDED);
                        if (!stem.equal(word)) {
                            result = cons(inverted_index.new_inverted_index_entry(stem, my_index), result);
                        }
                    }
                }
            }
            return result;
        }
    }

    public static SubLObject compute_inverted_index_entries_for_string(final SubLObject string, final SubLObject my_index) {
        SubLObject result = NIL;
        SubLObject cdolist_list_var = document.string_tokenize_via_wordify(string);
        SubLObject word = NIL;
        word = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == query_template_inverted_index_stop_wordP(word)) {
                result = cons(inverted_index.new_inverted_index_entry(word, my_index), result);
                final SubLObject stem = morphology.find_stem_memoized(word, UNPROVIDED);
                if (!stem.equal(word)) {
                    result = cons(inverted_index.new_inverted_index_entry(stem, my_index), result);
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            word = cdolist_list_var.first();
        } 
        return result;
    }

    public static final SubLObject query_template_inverted_index_stop_wordP_alt(SubLObject string) {
        return string.isString() ? ((SubLObject) (lexicon_utilities.denots_of_string_stop_word(string))) : NIL;
    }

    public static SubLObject query_template_inverted_index_stop_wordP(final SubLObject string) {
        return string.isString() ? lexicon_utilities.denots_of_string_stop_word(string) : NIL;
    }

    public static final SubLObject set_ql_build_index_in_memory_alt(SubLObject t_or_nil) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(t_or_nil, BOOLEANP);
            if (NIL != t_or_nil) {
                $ql_build_index_in_memory$.setDynamicValue(ONE_INTEGER, thread);
            } else {
                $ql_build_index_in_memory$.setDynamicValue(ZERO_INTEGER, thread);
            }
            return eql($ql_build_index_in_memory$.getDynamicValue(thread), ONE_INTEGER);
        }
    }

    public static SubLObject set_ql_build_index_in_memory(final SubLObject t_or_nil) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != booleanp(t_or_nil) : "! booleanp(t_or_nil) " + ("Types.booleanp(t_or_nil) " + "CommonSymbols.NIL != Types.booleanp(t_or_nil) ") + t_or_nil;
        if (NIL != t_or_nil) {
            $ql_build_index_in_memory$.setDynamicValue(ONE_INTEGER, thread);
        } else {
            $ql_build_index_in_memory$.setDynamicValue(ZERO_INTEGER, thread);
        }
        return eql($ql_build_index_in_memory$.getDynamicValue(thread), ONE_INTEGER);
    }

    public static final SubLObject get_ql_build_index_in_memory_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return eql($ql_build_index_in_memory$.getDynamicValue(thread), ONE_INTEGER);
        }
    }

    public static SubLObject get_ql_build_index_in_memory() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return eql($ql_build_index_in_memory$.getDynamicValue(thread), ONE_INTEGER);
    }

    public static final SubLObject clear_inverted_index_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != $query_template_index$.getDynamicValue(thread)) {
                methods.funcall_instance_method_with_0_args($query_template_index$.getDynamicValue(thread), INITIALIZE);
            }
            return NIL;
        }
    }

    public static SubLObject clear_inverted_index() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $query_template_index$.getDynamicValue(thread)) {
            methods.funcall_instance_method_with_0_args($query_template_index$.getDynamicValue(thread), INITIALIZE);
        }
        return NIL;
    }

    public static final SubLObject index_queries_from_node_in_memory_alt(SubLObject root_node) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == $query_template_index$.getDynamicValue(thread)) {
                $query_template_index$.setDynamicValue(object.new_class_instance(QUERY_TEMPLATE_INVERTED_INDEX), thread);
            }
            methods.funcall_instance_method_with_1_args($query_template_index$.getDynamicValue(thread), INDEX_ALL_QUERIES_IN_FOLDER, root_node);
            return $INDEXED;
        }
    }

    public static SubLObject index_queries_from_node_in_memory(final SubLObject root_node) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == $query_template_index$.getDynamicValue(thread)) {
            $query_template_index$.setDynamicValue(object.new_class_instance(QUERY_TEMPLATE_INVERTED_INDEX), thread);
        }
        methods.funcall_instance_method_with_1_args($query_template_index$.getDynamicValue(thread), INDEX_ALL_QUERIES_IN_FOLDER, root_node);
        return $INDEXED;
    }

    public static final SubLObject add_template_with_formula_and_gloss_in_memory_alt(SubLObject template, SubLObject formula, SubLObject gloss) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != com.cyc.cycjava.cycl.inverted_index_query_library.query_template_inverted_index_p($query_template_index$.getDynamicValue(thread))) {
                methods.funcall_instance_method_with_3_args($query_template_index$.getDynamicValue(thread), INDEX_QUERY_WITH_FORMULA_AND_GLOSS, template, formula, gloss);
                return $INDEXED;
            }
            return $INDEX_UNINITIALIZED;
        }
    }

    public static SubLObject add_template_with_formula_and_gloss_in_memory(final SubLObject template, final SubLObject formula, final SubLObject gloss) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != query_template_inverted_index_p($query_template_index$.getDynamicValue(thread))) {
            methods.funcall_instance_method_with_3_args($query_template_index$.getDynamicValue(thread), INDEX_QUERY_WITH_FORMULA_AND_GLOSS, template, formula, gloss);
            return $INDEXED;
        }
        return $INDEX_UNINITIALIZED;
    }

    public static final SubLObject indexed_queries_from_string_in_memory_alt(SubLObject string) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == com.cyc.cycjava.cycl.inverted_index_query_library.ql_ir_search_allowedP()) {
                return NIL;
            }
            return Mapping.mapcar(FIRST, methods.funcall_instance_method_with_1_args($query_template_index$.getDynamicValue(thread), LOOKUP, string));
        }
    }

    public static SubLObject indexed_queries_from_string_in_memory(final SubLObject string) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == ql_ir_search_allowedP()) {
            return NIL;
        }
        return Mapping.mapcar(FIRST, methods.funcall_instance_method_with_1_args($query_template_index$.getDynamicValue(thread), LOOKUP, string));
    }

    /**
     *
     *
     * @return listp; a list of ql-parse items.  The weights can range between 0 and 1
     */
    @LispMethod(comment = "@return listp; a list of ql-parse items.  The weights can range between 0 and 1")
    public static final SubLObject indexed_queries_from_string_with_confidences_in_memory_alt(SubLObject string) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == com.cyc.cycjava.cycl.inverted_index_query_library.ql_ir_search_allowedP()) {
                return NIL;
            }
            {
                SubLObject result = NIL;
                SubLObject cdolist_list_var = methods.funcall_instance_method_with_1_args($query_template_index$.getDynamicValue(thread), LOOKUP, string);
                SubLObject pair = NIL;
                for (pair = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , pair = cdolist_list_var.first()) {
                    {
                        SubLObject datum = pair;
                        SubLObject current = datum;
                        SubLObject cycl = NIL;
                        SubLObject weight = NIL;
                        destructuring_bind_must_consp(current, datum, $list_alt103);
                        cycl = current.first();
                        current = current.rest();
                        destructuring_bind_must_consp(current, datum, $list_alt103);
                        weight = current.first();
                        current = current.rest();
                        if (NIL == current) {
                            result = cons(query_library_utils.new_ql_parse(cycl, weight, UNPROVIDED), result);
                        } else {
                            cdestructuring_bind_error(datum, $list_alt103);
                        }
                    }
                }
                return result;
            }
        }
    }

    /**
     *
     *
     * @return listp; a list of ql-parse items.  The weights can range between 0 and 1
     */
    @LispMethod(comment = "@return listp; a list of ql-parse items.  The weights can range between 0 and 1")
    public static SubLObject indexed_queries_from_string_with_confidences_in_memory(final SubLObject string) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == ql_ir_search_allowedP()) {
            return NIL;
        }
        SubLObject result = NIL;
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_1_args($query_template_index$.getDynamicValue(thread), LOOKUP, string);
        SubLObject pair = NIL;
        pair = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = pair;
            SubLObject cycl = NIL;
            SubLObject weight = NIL;
            destructuring_bind_must_consp(current, datum, $list93);
            cycl = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list93);
            weight = current.first();
            current = current.rest();
            if (NIL == current) {
                result = cons(query_library_utils.new_ql_parse(cycl, weight, UNPROVIDED), result);
            } else {
                cdestructuring_bind_error(datum, $list93);
            }
            cdolist_list_var = cdolist_list_var.rest();
            pair = cdolist_list_var.first();
        } 
        return result;
    }

    /**
     *
     *
     * @unknown A blatant hack to disable all Query Library IR-style search through the find box.  This should really be done on the Java
    client side
     */
    @LispMethod(comment = "@unknown A blatant hack to disable all Query Library IR-style search through the find box.  This should really be done on the Java\r\nclient side")
    public static final SubLObject disable_ql_ir_search_alt() {
        $ql_ir_search_disabledP$.setDynamicValue(T);
        return NIL;
    }

    /**
     *
     *
     * @unknown A blatant hack to disable all Query Library IR-style search through the find box.  This should really be done on the Java
    client side
     */
    @LispMethod(comment = "@unknown A blatant hack to disable all Query Library IR-style search through the find box.  This should really be done on the Java\r\nclient side")
    public static SubLObject disable_ql_ir_search() {
        $ql_ir_search_disabledP$.setDynamicValue(T);
        return NIL;
    }

    /**
     *
     *
     * @unknown A blatant hack to enable Query Library IR-style search through the find box.  This should really be done on the Java
    client side
     */
    @LispMethod(comment = "@unknown A blatant hack to enable Query Library IR-style search through the find box.  This should really be done on the Java\r\nclient side")
    public static final SubLObject enable_ql_ir_search_alt() {
        $ql_ir_search_disabledP$.setDynamicValue(NIL);
        return NIL;
    }

    /**
     *
     *
     * @unknown A blatant hack to enable Query Library IR-style search through the find box.  This should really be done on the Java
    client side
     */
    @LispMethod(comment = "@unknown A blatant hack to enable Query Library IR-style search through the find box.  This should really be done on the Java\r\nclient side")
    public static SubLObject enable_ql_ir_search() {
        $ql_ir_search_disabledP$.setDynamicValue(NIL);
        return NIL;
    }

    public static final SubLObject ql_ir_search_allowedP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != com.cyc.cycjava.cycl.inverted_index_query_library.ql_ir_search_disabledP()) {
                return NIL;
            }
            if (((NIL != com.cyc.cycjava.cycl.inverted_index_query_library.get_ql_build_index_in_memory()) && (NIL != inverted_index.inverted_index_p($query_template_index$.getDynamicValue(thread)))) || (NIL == com.cyc.cycjava.cycl.inverted_index_query_library.get_ql_build_index_in_memory())) {
                return T;
            }
            return NIL;
        }
    }

    public static SubLObject ql_ir_search_allowedP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != ql_ir_search_disabledP()) {
            return NIL;
        }
        if (((NIL != get_ql_build_index_in_memory()) && (NIL != inverted_index.inverted_index_p($query_template_index$.getDynamicValue(thread)))) || (NIL == get_ql_build_index_in_memory())) {
            return T;
        }
        return NIL;
    }

    public static final SubLObject ql_ir_search_disabledP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return $ql_ir_search_disabledP$.getDynamicValue(thread);
        }
    }

    public static SubLObject ql_ir_search_disabledP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return $ql_ir_search_disabledP$.getDynamicValue(thread);
    }

    public static final SubLObject test_query_for_template_alt(SubLObject template, SubLObject mt) {
        if ((NIL != nart_handles.nart_p(template)) && cycl_utilities.nat_functor(template).eql($$TemplateFromTestQueryFn)) {
            return cycl_utilities.nat_arg1(template, UNPROVIDED);
        }
        {
            SubLObject var = variables.get_variable(ZERO_INTEGER);
            return backward.removal_ask_variable(var, list($const107$querySpecificationForFormulaTempl, template, var), mt, UNPROVIDED, UNPROVIDED).first();
        }
    }

    public static SubLObject test_query_for_template(final SubLObject template, final SubLObject mt) {
        if ((NIL != nart_handles.nart_p(template)) && cycl_utilities.nat_functor(template).eql($$TemplateFromTestQueryFn)) {
            return cycl_utilities.nat_arg1(template, UNPROVIDED);
        }
        final SubLObject var = variables.get_variable(ZERO_INTEGER);
        return backward.removal_ask_variable(var, list($const97$querySpecificationForFormulaTempl, template, var), mt, UNPROVIDED, UNPROVIDED).first();
    }

    public static final SubLObject cycl_for_test_query_new_alt(SubLObject test_query, SubLObject mt) {
        {
            SubLObject var = variables.get_variable(ZERO_INTEGER);
            SubLObject v_properties = list($ALLOWED_MODULES, $REMOVAL_LOOKUP_POS, $ANSWER_LANGUAGE, $HL, $RETURN, list($TEMPLATE, var));
            SubLObject dnf = list(NIL, list(kb_query.kbq_askable_query_spec_sentence(var, test_query, T)));
            return inference_kernel.new_cyc_query_from_dnf(dnf, mt, NIL, v_properties).first();
        }
    }

    public static SubLObject cycl_for_test_query_new(final SubLObject test_query, final SubLObject mt) {
        final SubLObject var = variables.get_variable(ZERO_INTEGER);
        final SubLObject v_properties = list($ALLOWED_MODULES, $REMOVAL_LOOKUP_POS, $ANSWER_LANGUAGE, $HL, $RETURN, list($TEMPLATE, var));
        final SubLObject dnf = list(NIL, list(kb_query.kbq_askable_query_spec_sentence(var, test_query, T)));
        return inference_kernel.new_cyc_query_from_dnf(dnf, mt, NIL, v_properties).first();
    }

    public static final SubLObject cycl_for_test_query_alt(SubLObject test_query, SubLObject mt) {
        {
            SubLObject var = variables.get_variable(ZERO_INTEGER);
            return backward.removal_ask_variable(var, kb_query.kbq_askable_query_spec_sentence(var, test_query, T), mt, $TRUE, $list_alt114).first();
        }
    }

    public static SubLObject cycl_for_test_query(final SubLObject test_query, final SubLObject mt) {
        final SubLObject var = variables.get_variable(ZERO_INTEGER);
        return backward.removal_ask_variable(var, kb_query.kbq_askable_query_spec_sentence(var, test_query, T), mt, $TRUE, $list104).first();
    }

    public static final SubLObject comment_for_test_query_alt(SubLObject test_query, SubLObject mt) {
        return ask_utilities.query_variable($X, list($$assertedSentence, listS($$comment, test_query, $list_alt118)), mt, $list_alt119).first();
    }

    public static SubLObject comment_for_test_query(final SubLObject test_query, final SubLObject mt) {
        return ask_utilities.query_variable($X, list($$assertedSentence, listS($$comment, test_query, $list108)), mt, $list109).first();
    }

    public static final SubLObject query_and_gloss_for_formula_template_alt(SubLObject formula_template, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$EverythingPSC;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject nartified_template = czer_main.canonicalize_term(formula_template, UNPROVIDED);
                SubLObject test_query = com.cyc.cycjava.cycl.inverted_index_query_library.test_query_for_template(nartified_template, mt);
                SubLObject comment = NIL;
                SubLObject query = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        comment = kb_mapping_utilities.fpred_value(nartified_template, $$formulaTemplateGloss, ONE_INTEGER, TWO_INTEGER, $TRUE);
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                if (NIL != test_query) {
                    query = com.cyc.cycjava.cycl.inverted_index_query_library.cycl_for_test_query(test_query, mt);
                }
                return values(query, comment);
            }
        }
    }

    public static SubLObject query_and_gloss_for_formula_template(final SubLObject formula_template, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $$EverythingPSC;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject nartified_template = czer_main.canonicalize_term(formula_template, UNPROVIDED);
        final SubLObject test_query = test_query_for_template(nartified_template, mt);
        SubLObject comment = NIL;
        SubLObject query = NIL;
        final SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mt);
        final SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
        try {
            mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
            mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
            mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
            comment = kb_mapping_utilities.fpred_value(nartified_template, $$formulaTemplateGloss, ONE_INTEGER, TWO_INTEGER, $TRUE);
        } finally {
            mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_3, thread);
            mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_2, thread);
            mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
        }
        if (NIL != test_query) {
            query = cycl_for_test_query(test_query, mt);
        }
        return values(query, comment);
    }

    public static final SubLObject query_template_encapsulated_p_alt(SubLObject obj) {
        return makeBoolean(((NIL != list_utilities.property_list_p(obj)) && (NIL != getf(obj, $FORMULA, UNPROVIDED))) && (NIL != getf(obj, $TEMPLATE, UNPROVIDED)));
    }

    public static SubLObject query_template_encapsulated_p(final SubLObject obj) {
        return makeBoolean(((NIL != list_utilities.property_list_p(obj)) && (NIL != getf(obj, $FORMULA, UNPROVIDED))) && (NIL != getf(obj, $TEMPLATE, UNPROVIDED)));
    }

    public static final SubLObject declare_inverted_index_query_library_file_alt() {
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_class", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_instance", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-INSTANCE", 1, 0, false);
        declareFunction("query_template_inverted_index_p", "QUERY-TEMPLATE-INVERTED-INDEX-P", 1, 0, false);
        declareFunction("query_template_inverted_index_initialize_method", "QUERY-TEMPLATE-INVERTED-INDEX-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("query_template_inverted_index_lookup_method", "QUERY-TEMPLATE-INVERTED-INDEX-LOOKUP-METHOD", 2, 0, false);
        declareFunction("remove_low_weighted_items", "REMOVE-LOW-WEIGHTED-ITEMS", 3, 0, false);
        declareFunction("normalized_weight_too_lowP", "NORMALIZED-WEIGHT-TOO-LOW?", 1, 0, false);
        declareFunction("query_template_inverted_index_remove_key_method", "QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-KEY-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_remove_value_method", "QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-VALUE-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_index_all_queries_in_folder_method", "QUERY-TEMPLATE-INVERTED-INDEX-INDEX-ALL-QUERIES-IN-FOLDER-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_index_query_with_formula_and_gloss_method", "QUERY-TEMPLATE-INVERTED-INDEX-INDEX-QUERY-WITH-FORMULA-AND-GLOSS-METHOD", 4, 0, false);
        declareFunction("get_query_template_inverted_index_entry_computer_parent_index", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-PARENT-INDEX", 1, 0, false);
        declareFunction("set_query_template_inverted_index_entry_computer_parent_index", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-PARENT-INDEX", 2, 0, false);
        declareFunction("get_query_template_inverted_index_entry_computer_generation_domain_mt", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-DOMAIN-MT", 1, 0, false);
        declareFunction("set_query_template_inverted_index_entry_computer_generation_domain_mt", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-DOMAIN-MT", 2, 0, false);
        declareFunction("get_query_template_inverted_index_entry_computer_generation_mt", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-MT", 1, 0, false);
        declareFunction("set_query_template_inverted_index_entry_computer_generation_mt", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-MT", 2, 0, false);
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_entry_computer_class", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_entry_computer_instance", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-INSTANCE", 1, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_p", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-P", 1, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_set_index_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-INDEX-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_set_generation_mt_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-MT-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_set_generation_domain_mt_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-DOMAIN-MT-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_compute_index_entries_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-COMPUTE-INDEX-ENTRIES-METHOD", 2, 0, false);
        declareFunction("report_ql_index_initialization_error", "REPORT-QL-INDEX-INITIALIZATION-ERROR", 2, 0, false);
        declareFunction("compute_inverted_index_entries_for_string", "COMPUTE-INVERTED-INDEX-ENTRIES-FOR-STRING", 2, 0, false);
        declareFunction("query_template_inverted_index_stop_wordP", "QUERY-TEMPLATE-INVERTED-INDEX-STOP-WORD?", 1, 0, false);
        declareFunction("set_ql_build_index_in_memory", "SET-QL-BUILD-INDEX-IN-MEMORY", 1, 0, false);
        declareFunction("get_ql_build_index_in_memory", "GET-QL-BUILD-INDEX-IN-MEMORY", 0, 0, false);
        declareFunction("clear_inverted_index", "CLEAR-INVERTED-INDEX", 0, 0, false);
        declareFunction("index_queries_from_node_in_memory", "INDEX-QUERIES-FROM-NODE-IN-MEMORY", 1, 0, false);
        declareFunction("add_template_with_formula_and_gloss_in_memory", "ADD-TEMPLATE-WITH-FORMULA-AND-GLOSS-IN-MEMORY", 3, 0, false);
        declareFunction("indexed_queries_from_string_in_memory", "INDEXED-QUERIES-FROM-STRING-IN-MEMORY", 1, 0, false);
        declareFunction("indexed_queries_from_string_with_confidences_in_memory", "INDEXED-QUERIES-FROM-STRING-WITH-CONFIDENCES-IN-MEMORY", 1, 0, false);
        declareFunction("disable_ql_ir_search", "DISABLE-QL-IR-SEARCH", 0, 0, false);
        declareFunction("enable_ql_ir_search", "ENABLE-QL-IR-SEARCH", 0, 0, false);
        declareFunction("ql_ir_search_allowedP", "QL-IR-SEARCH-ALLOWED?", 0, 0, false);
        declareFunction("ql_ir_search_disabledP", "QL-IR-SEARCH-DISABLED?", 0, 0, false);
        declareFunction("test_query_for_template", "TEST-QUERY-FOR-TEMPLATE", 2, 0, false);
        declareFunction("cycl_for_test_query_new", "CYCL-FOR-TEST-QUERY-NEW", 2, 0, false);
        declareFunction("cycl_for_test_query", "CYCL-FOR-TEST-QUERY", 2, 0, false);
        declareFunction("comment_for_test_query", "COMMENT-FOR-TEST-QUERY", 2, 0, false);
        declareFunction("query_and_gloss_for_formula_template", "QUERY-AND-GLOSS-FOR-FORMULA-TEMPLATE", 1, 1, false);
        declareFunction("query_template_encapsulated_p", "QUERY-TEMPLATE-ENCAPSULATED-P", 1, 0, false);
        declareFunction("get_inverted_index_query_template_insert_delete_special_items", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-SPECIAL-ITEMS", 1, 0, false);
        declareFunction("set_inverted_index_query_template_insert_delete_special_items", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-SPECIAL-ITEMS", 2, 0, false);
        declareFunction("get_inverted_index_query_template_insert_delete_bad_items", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-BAD-ITEMS", 1, 0, false);
        declareFunction("set_inverted_index_query_template_insert_delete_bad_items", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-BAD-ITEMS", 2, 0, false);
        declareFunction("get_inverted_index_query_template_insert_delete_good_items", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-GOOD-ITEMS", 1, 0, false);
        declareFunction("set_inverted_index_query_template_insert_delete_good_items", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-GOOD-ITEMS", 2, 0, false);
        declareFunction("get_inverted_index_query_template_insert_delete_library_mt", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-MT", 1, 0, false);
        declareFunction("set_inverted_index_query_template_insert_delete_library_mt", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-MT", 2, 0, false);
        declareFunction("get_inverted_index_query_template_insert_delete_library_root_node", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-ROOT-NODE", 1, 0, false);
        declareFunction("set_inverted_index_query_template_insert_delete_library_root_node", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-ROOT-NODE", 2, 0, false);
        declareFunction("get_inverted_index_query_template_insert_delete_index", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-INDEX", 1, 0, false);
        declareFunction("set_inverted_index_query_template_insert_delete_index", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-INDEX", 2, 0, false);
        declareFunction("subloop_reserved_initialize_inverted_index_query_template_insert_delete_class", "SUBLOOP-RESERVED-INITIALIZE-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_inverted_index_query_template_insert_delete_instance", "SUBLOOP-RESERVED-INITIALIZE-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-INSTANCE", 1, 0, false);
        declareFunction("inverted_index_query_template_insert_delete_p", "INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-P", 1, 0, false);
        return NIL;
    }

    public static SubLObject declare_inverted_index_query_library_file() {
        if (SubLFiles.USE_V1) {
            declareFunction("subloop_reserved_initialize_query_template_inverted_index_class", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-CLASS", 1, 0, false);
            declareFunction("subloop_reserved_initialize_query_template_inverted_index_instance", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-INSTANCE", 1, 0, false);
            declareFunction("query_template_inverted_index_p", "QUERY-TEMPLATE-INVERTED-INDEX-P", 1, 0, false);
            declareFunction("query_template_inverted_index_initialize_method", "QUERY-TEMPLATE-INVERTED-INDEX-INITIALIZE-METHOD", 1, 0, false);
            declareFunction("query_template_inverted_index_lookup_method", "QUERY-TEMPLATE-INVERTED-INDEX-LOOKUP-METHOD", 2, 0, false);
            declareFunction("remove_low_weighted_items", "REMOVE-LOW-WEIGHTED-ITEMS", 3, 0, false);
            declareFunction("normalized_weight_too_lowP", "NORMALIZED-WEIGHT-TOO-LOW?", 1, 0, false);
            declareFunction("query_template_inverted_index_remove_key_method", "QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-KEY-METHOD", 2, 0, false);
            declareFunction("query_template_inverted_index_remove_value_method", "QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-VALUE-METHOD", 2, 0, false);
            declareFunction("query_template_inverted_index_index_all_queries_in_folder_method", "QUERY-TEMPLATE-INVERTED-INDEX-INDEX-ALL-QUERIES-IN-FOLDER-METHOD", 2, 0, false);
            declareFunction("query_template_inverted_index_index_query_with_formula_and_gloss_method", "QUERY-TEMPLATE-INVERTED-INDEX-INDEX-QUERY-WITH-FORMULA-AND-GLOSS-METHOD", 4, 0, false);
            declareFunction("get_query_template_inverted_index_entry_computer_parent_index", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-PARENT-INDEX", 1, 0, false);
            declareFunction("set_query_template_inverted_index_entry_computer_parent_index", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-PARENT-INDEX", 2, 0, false);
            declareFunction("get_query_template_inverted_index_entry_computer_generation_domain_mt", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-DOMAIN-MT", 1, 0, false);
            declareFunction("set_query_template_inverted_index_entry_computer_generation_domain_mt", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-DOMAIN-MT", 2, 0, false);
            declareFunction("get_query_template_inverted_index_entry_computer_generation_mt", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-MT", 1, 0, false);
            declareFunction("set_query_template_inverted_index_entry_computer_generation_mt", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-MT", 2, 0, false);
            declareFunction("subloop_reserved_initialize_query_template_inverted_index_entry_computer_class", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-CLASS", 1, 0, false);
            declareFunction("subloop_reserved_initialize_query_template_inverted_index_entry_computer_instance", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-INSTANCE", 1, 0, false);
            declareFunction("query_template_inverted_index_entry_computer_p", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-P", 1, 0, false);
            declareFunction("query_template_inverted_index_entry_computer_set_index_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-INDEX-METHOD", 2, 0, false);
            declareFunction("query_template_inverted_index_entry_computer_set_generation_mt_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-MT-METHOD", 2, 0, false);
            declareFunction("query_template_inverted_index_entry_computer_set_generation_domain_mt_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-DOMAIN-MT-METHOD", 2, 0, false);
            declareFunction("query_template_inverted_index_entry_computer_compute_index_entries_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-COMPUTE-INDEX-ENTRIES-METHOD", 2, 0, false);
            declareFunction("report_ql_index_initialization_error", "REPORT-QL-INDEX-INITIALIZATION-ERROR", 2, 0, false);
            declareFunction("compute_inverted_index_entries_for_string", "COMPUTE-INVERTED-INDEX-ENTRIES-FOR-STRING", 2, 0, false);
            declareFunction("query_template_inverted_index_stop_wordP", "QUERY-TEMPLATE-INVERTED-INDEX-STOP-WORD?", 1, 0, false);
            declareFunction("set_ql_build_index_in_memory", "SET-QL-BUILD-INDEX-IN-MEMORY", 1, 0, false);
            declareFunction("get_ql_build_index_in_memory", "GET-QL-BUILD-INDEX-IN-MEMORY", 0, 0, false);
            declareFunction("clear_inverted_index", "CLEAR-INVERTED-INDEX", 0, 0, false);
            declareFunction("index_queries_from_node_in_memory", "INDEX-QUERIES-FROM-NODE-IN-MEMORY", 1, 0, false);
            declareFunction("add_template_with_formula_and_gloss_in_memory", "ADD-TEMPLATE-WITH-FORMULA-AND-GLOSS-IN-MEMORY", 3, 0, false);
            declareFunction("indexed_queries_from_string_in_memory", "INDEXED-QUERIES-FROM-STRING-IN-MEMORY", 1, 0, false);
            declareFunction("indexed_queries_from_string_with_confidences_in_memory", "INDEXED-QUERIES-FROM-STRING-WITH-CONFIDENCES-IN-MEMORY", 1, 0, false);
            declareFunction("disable_ql_ir_search", "DISABLE-QL-IR-SEARCH", 0, 0, false);
            declareFunction("enable_ql_ir_search", "ENABLE-QL-IR-SEARCH", 0, 0, false);
            declareFunction("ql_ir_search_allowedP", "QL-IR-SEARCH-ALLOWED?", 0, 0, false);
            declareFunction("ql_ir_search_disabledP", "QL-IR-SEARCH-DISABLED?", 0, 0, false);
            declareFunction("test_query_for_template", "TEST-QUERY-FOR-TEMPLATE", 2, 0, false);
            declareFunction("cycl_for_test_query_new", "CYCL-FOR-TEST-QUERY-NEW", 2, 0, false);
            declareFunction("cycl_for_test_query", "CYCL-FOR-TEST-QUERY", 2, 0, false);
            declareFunction("comment_for_test_query", "COMMENT-FOR-TEST-QUERY", 2, 0, false);
            declareFunction("query_and_gloss_for_formula_template", "QUERY-AND-GLOSS-FOR-FORMULA-TEMPLATE", 1, 1, false);
            declareFunction("query_template_encapsulated_p", "QUERY-TEMPLATE-ENCAPSULATED-P", 1, 0, false);
        }
        if (SubLFiles.USE_V2) {
            declareFunction("get_inverted_index_query_template_insert_delete_special_items", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-SPECIAL-ITEMS", 1, 0, false);
            declareFunction("set_inverted_index_query_template_insert_delete_special_items", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-SPECIAL-ITEMS", 2, 0, false);
            declareFunction("get_inverted_index_query_template_insert_delete_bad_items", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-BAD-ITEMS", 1, 0, false);
            declareFunction("set_inverted_index_query_template_insert_delete_bad_items", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-BAD-ITEMS", 2, 0, false);
            declareFunction("get_inverted_index_query_template_insert_delete_good_items", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-GOOD-ITEMS", 1, 0, false);
            declareFunction("set_inverted_index_query_template_insert_delete_good_items", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-GOOD-ITEMS", 2, 0, false);
            declareFunction("get_inverted_index_query_template_insert_delete_library_mt", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-MT", 1, 0, false);
            declareFunction("set_inverted_index_query_template_insert_delete_library_mt", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-MT", 2, 0, false);
            declareFunction("get_inverted_index_query_template_insert_delete_library_root_node", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-ROOT-NODE", 1, 0, false);
            declareFunction("set_inverted_index_query_template_insert_delete_library_root_node", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-LIBRARY-ROOT-NODE", 2, 0, false);
            declareFunction("get_inverted_index_query_template_insert_delete_index", "GET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-INDEX", 1, 0, false);
            declareFunction("set_inverted_index_query_template_insert_delete_index", "SET-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-INDEX", 2, 0, false);
            declareFunction("subloop_reserved_initialize_inverted_index_query_template_insert_delete_class", "SUBLOOP-RESERVED-INITIALIZE-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-CLASS", 1, 0, false);
            declareFunction("subloop_reserved_initialize_inverted_index_query_template_insert_delete_instance", "SUBLOOP-RESERVED-INITIALIZE-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-INSTANCE", 1, 0, false);
            declareFunction("inverted_index_query_template_insert_delete_p", "INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-P", 1, 0, false);
        }
        return NIL;
    }

    public static SubLObject declare_inverted_index_query_library_file_Previous() {
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_class", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_instance", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-INSTANCE", 1, 0, false);
        declareFunction("query_template_inverted_index_p", "QUERY-TEMPLATE-INVERTED-INDEX-P", 1, 0, false);
        declareFunction("query_template_inverted_index_initialize_method", "QUERY-TEMPLATE-INVERTED-INDEX-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("query_template_inverted_index_lookup_method", "QUERY-TEMPLATE-INVERTED-INDEX-LOOKUP-METHOD", 2, 0, false);
        declareFunction("remove_low_weighted_items", "REMOVE-LOW-WEIGHTED-ITEMS", 3, 0, false);
        declareFunction("normalized_weight_too_lowP", "NORMALIZED-WEIGHT-TOO-LOW?", 1, 0, false);
        declareFunction("query_template_inverted_index_remove_key_method", "QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-KEY-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_remove_value_method", "QUERY-TEMPLATE-INVERTED-INDEX-REMOVE-VALUE-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_index_all_queries_in_folder_method", "QUERY-TEMPLATE-INVERTED-INDEX-INDEX-ALL-QUERIES-IN-FOLDER-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_index_query_with_formula_and_gloss_method", "QUERY-TEMPLATE-INVERTED-INDEX-INDEX-QUERY-WITH-FORMULA-AND-GLOSS-METHOD", 4, 0, false);
        declareFunction("get_query_template_inverted_index_entry_computer_parent_index", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-PARENT-INDEX", 1, 0, false);
        declareFunction("set_query_template_inverted_index_entry_computer_parent_index", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-PARENT-INDEX", 2, 0, false);
        declareFunction("get_query_template_inverted_index_entry_computer_generation_domain_mt", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-DOMAIN-MT", 1, 0, false);
        declareFunction("set_query_template_inverted_index_entry_computer_generation_domain_mt", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-DOMAIN-MT", 2, 0, false);
        declareFunction("get_query_template_inverted_index_entry_computer_generation_mt", "GET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-MT", 1, 0, false);
        declareFunction("set_query_template_inverted_index_entry_computer_generation_mt", "SET-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-GENERATION-MT", 2, 0, false);
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_entry_computer_class", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_query_template_inverted_index_entry_computer_instance", "SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-INSTANCE", 1, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_p", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-P", 1, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_set_index_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-INDEX-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_set_generation_mt_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-MT-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_set_generation_domain_mt_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-DOMAIN-MT-METHOD", 2, 0, false);
        declareFunction("query_template_inverted_index_entry_computer_compute_index_entries_method", "QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-COMPUTE-INDEX-ENTRIES-METHOD", 2, 0, false);
        declareFunction("report_ql_index_initialization_error", "REPORT-QL-INDEX-INITIALIZATION-ERROR", 2, 0, false);
        declareFunction("compute_inverted_index_entries_for_string", "COMPUTE-INVERTED-INDEX-ENTRIES-FOR-STRING", 2, 0, false);
        declareFunction("query_template_inverted_index_stop_wordP", "QUERY-TEMPLATE-INVERTED-INDEX-STOP-WORD?", 1, 0, false);
        declareFunction("set_ql_build_index_in_memory", "SET-QL-BUILD-INDEX-IN-MEMORY", 1, 0, false);
        declareFunction("get_ql_build_index_in_memory", "GET-QL-BUILD-INDEX-IN-MEMORY", 0, 0, false);
        declareFunction("clear_inverted_index", "CLEAR-INVERTED-INDEX", 0, 0, false);
        declareFunction("index_queries_from_node_in_memory", "INDEX-QUERIES-FROM-NODE-IN-MEMORY", 1, 0, false);
        declareFunction("add_template_with_formula_and_gloss_in_memory", "ADD-TEMPLATE-WITH-FORMULA-AND-GLOSS-IN-MEMORY", 3, 0, false);
        declareFunction("indexed_queries_from_string_in_memory", "INDEXED-QUERIES-FROM-STRING-IN-MEMORY", 1, 0, false);
        declareFunction("indexed_queries_from_string_with_confidences_in_memory", "INDEXED-QUERIES-FROM-STRING-WITH-CONFIDENCES-IN-MEMORY", 1, 0, false);
        declareFunction("disable_ql_ir_search", "DISABLE-QL-IR-SEARCH", 0, 0, false);
        declareFunction("enable_ql_ir_search", "ENABLE-QL-IR-SEARCH", 0, 0, false);
        declareFunction("ql_ir_search_allowedP", "QL-IR-SEARCH-ALLOWED?", 0, 0, false);
        declareFunction("ql_ir_search_disabledP", "QL-IR-SEARCH-DISABLED?", 0, 0, false);
        declareFunction("test_query_for_template", "TEST-QUERY-FOR-TEMPLATE", 2, 0, false);
        declareFunction("cycl_for_test_query_new", "CYCL-FOR-TEST-QUERY-NEW", 2, 0, false);
        declareFunction("cycl_for_test_query", "CYCL-FOR-TEST-QUERY", 2, 0, false);
        declareFunction("comment_for_test_query", "COMMENT-FOR-TEST-QUERY", 2, 0, false);
        declareFunction("query_and_gloss_for_formula_template", "QUERY-AND-GLOSS-FOR-FORMULA-TEMPLATE", 1, 1, false);
        declareFunction("query_template_encapsulated_p", "QUERY-TEMPLATE-ENCAPSULATED-P", 1, 0, false);
        return NIL;
    }

    public static final SubLObject init_inverted_index_query_library_file_alt() {
        defparameter("*QUERY-INDEX-INDEX-GENERATION?*", NIL);
        defparameter("*QL-SEARCH-WEIGHT-FACTOR*", $float$0_5);
        defparameter("*MIN-QUERY-WEIGHT*", ZERO_INTEGER);
        defparameter("*QUERY-TEMPLATE-INDEX*", NIL);
        defparameter("*QL-IR-SEARCH-DISABLED?*", NIL);
        defparameter("*QL-BUILD-INDEX-IN-MEMORY*", red_infrastructure_macros.red_def_helper($list_alt96.isSymbol() ? ((SubLObject) (symbol_value($list_alt96))) : $list_alt96, $ql_build_index_in_memory$, ZERO_INTEGER.isSymbol() ? ((SubLObject) (symbol_value(ZERO_INTEGER))) : ZERO_INTEGER, $PARAMETER, UNPROVIDED));
        return NIL;
    }

    public static SubLObject init_inverted_index_query_library_file() {
        if (SubLFiles.USE_V1) {
            defparameter("*QUERY-INDEX-INDEX-GENERATION?*", NIL);
            defparameter("*QL-SEARCH-WEIGHT-FACTOR*", $float$0_5);
            defparameter("*MIN-QUERY-WEIGHT*", ZERO_INTEGER);
            defparameter("*QUERY-TEMPLATE-INDEX*", NIL);
            defparameter("*QL-IR-SEARCH-DISABLED?*", NIL);
            defparameter("*QL-BUILD-INDEX-IN-MEMORY*", red_infrastructure_macros.red_def_helper($list86.isSymbol() ? symbol_value($list86) : $list86, $ql_build_index_in_memory$, ZERO_INTEGER.isSymbol() ? symbol_value(ZERO_INTEGER) : ZERO_INTEGER, $PARAMETER, UNPROVIDED));
        }
        if (SubLFiles.USE_V2) {
            defparameter("*QL-BUILD-INDEX-IN-MEMORY*", red_infrastructure_macros.red_def_helper($list_alt96.isSymbol() ? ((SubLObject) (symbol_value($list_alt96))) : $list_alt96, $ql_build_index_in_memory$, ZERO_INTEGER.isSymbol() ? ((SubLObject) (symbol_value(ZERO_INTEGER))) : ZERO_INTEGER, $PARAMETER, UNPROVIDED));
        }
        return NIL;
    }

    public static SubLObject init_inverted_index_query_library_file_Previous() {
        defparameter("*QUERY-INDEX-INDEX-GENERATION?*", NIL);
        defparameter("*QL-SEARCH-WEIGHT-FACTOR*", $float$0_5);
        defparameter("*MIN-QUERY-WEIGHT*", ZERO_INTEGER);
        defparameter("*QUERY-TEMPLATE-INDEX*", NIL);
        defparameter("*QL-IR-SEARCH-DISABLED?*", NIL);
        defparameter("*QL-BUILD-INDEX-IN-MEMORY*", red_infrastructure_macros.red_def_helper($list86.isSymbol() ? symbol_value($list86) : $list86, $ql_build_index_in_memory$, ZERO_INTEGER.isSymbol() ? symbol_value(ZERO_INTEGER) : ZERO_INTEGER, $PARAMETER, UNPROVIDED));
        return NIL;
    }

    public static final SubLObject setup_inverted_index_query_library_file_alt() {
        classes.subloop_new_class(QUERY_TEMPLATE_INVERTED_INDEX, INVERTED_INDEX, NIL, NIL, $list_alt2);
        classes.class_set_implements_slot_listeners(QUERY_TEMPLATE_INVERTED_INDEX, NIL);
        classes.subloop_note_class_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX, SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_CLASS);
        classes.subloop_note_instance_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX, $sym12$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_INSTANC);
        com.cyc.cycjava.cycl.inverted_index_query_library.subloop_reserved_initialize_query_template_inverted_index_class(QUERY_TEMPLATE_INVERTED_INDEX);
        methods.methods_incorporate_instance_method(INITIALIZE, QUERY_TEMPLATE_INVERTED_INDEX, $list_alt14, NIL, $list_alt15);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INITIALIZE, QUERY_TEMPLATE_INVERTED_INDEX_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(LOOKUP, QUERY_TEMPLATE_INVERTED_INDEX, $list_alt21, $list_alt22, $list_alt23);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, LOOKUP, QUERY_TEMPLATE_INVERTED_INDEX_LOOKUP_METHOD);
        methods.methods_incorporate_instance_method(REMOVE_KEY, QUERY_TEMPLATE_INVERTED_INDEX, $list_alt21, $list_alt32, $list_alt33);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, REMOVE_KEY, QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_KEY_METHOD);
        methods.methods_incorporate_instance_method(REMOVE_VALUE, QUERY_TEMPLATE_INVERTED_INDEX, $list_alt21, $list_alt38, $list_alt39);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, REMOVE_VALUE, QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_VALUE_METHOD);
        methods.methods_incorporate_instance_method(INDEX_ALL_QUERIES_IN_FOLDER, QUERY_TEMPLATE_INVERTED_INDEX, $list_alt21, $list_alt44, $list_alt45);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INDEX_ALL_QUERIES_IN_FOLDER, QUERY_TEMPLATE_INVERTED_INDEX_INDEX_ALL_QUERIES_IN_FOLDER_METHOD);
        methods.methods_incorporate_instance_method(INDEX_QUERY_WITH_FORMULA_AND_GLOSS, QUERY_TEMPLATE_INVERTED_INDEX, $list_alt21, $list_alt53, $list_alt54);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INDEX_QUERY_WITH_FORMULA_AND_GLOSS, $sym58$QUERY_TEMPLATE_INVERTED_INDEX_INDEX_QUERY_WITH_FORMULA_AND_GLOSS_);
        classes.subloop_new_class(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, INDEX_ENTRY_COMPUTER, NIL, NIL, $list_alt59);
        classes.class_set_implements_slot_listeners(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, NIL);
        classes.subloop_note_class_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym63$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
        classes.subloop_note_instance_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym64$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
        com.cyc.cycjava.cycl.inverted_index_query_library.subloop_reserved_initialize_query_template_inverted_index_entry_computer_class(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER);
        methods.methods_incorporate_instance_method(SET_INDEX, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt66, $list_alt67);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_INDEX, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_INDEX_METHOD);
        methods.methods_incorporate_instance_method(SET_GENERATION_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt72, $list_alt73);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_MT, $sym76$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_MT_ME);
        methods.methods_incorporate_instance_method(SET_GENERATION_DOMAIN_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt72, $list_alt78);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_DOMAIN_MT, $sym80$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_DOMAI);
        methods.methods_incorporate_instance_method(COMPUTE_INDEX_ENTRIES, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt81, $list_alt82);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, COMPUTE_INDEX_ENTRIES, $sym86$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_COMPUTE_INDEX_ENTRIE);
        register_external_symbol(DISABLE_QL_IR_SEARCH);
        register_external_symbol(ENABLE_QL_IR_SEARCH);
        sunit_macros.$current_module_sunit_tests$.setDynamicValue(cons(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, sunit_macros.$current_module_sunit_tests$.getDynamicValue()));
        sunit_macros.define_test_case_preamble(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE);
        classes.subloop_new_class(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, CYCL_TEST_CASE, NIL, NIL, $list_alt124);
        classes.class_set_implements_slot_listeners(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, NIL);
        classes.subloop_note_class_initialization_function(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, $sym138$SUBLOOP_RESERVED_INITIALIZE_INVERTED_INDEX_QUERY_TEMPLATE_INSERT_);
        classes.subloop_note_instance_initialization_function(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, $sym142$SUBLOOP_RESERVED_INITIALIZE_INVERTED_INDEX_QUERY_TEMPLATE_INSERT_);
        com.cyc.cycjava.cycl.inverted_index_query_library.subloop_reserved_initialize_inverted_index_query_template_insert_delete_class(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE);
        sunit_macros.define_test_case_postamble(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, $str_alt143$inverted_indexes_concrete, $$$cycl, NIL);
        sunit_macros.def_test_method_register(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, TEST_INVERTED_INDEX_QUERY_TEMPLATE_ENTRY_DELETE);
        return NIL;
    }

    public static SubLObject setup_inverted_index_query_library_file() {
        if (SubLFiles.USE_V1) {
            classes.subloop_new_class(QUERY_TEMPLATE_INVERTED_INDEX, INVERTED_INDEX, NIL, NIL, $list2);
            classes.class_set_implements_slot_listeners(QUERY_TEMPLATE_INVERTED_INDEX, NIL);
            classes.subloop_note_class_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX, SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_CLASS);
            classes.subloop_note_instance_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX, $sym12$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_INSTANC);
            subloop_reserved_initialize_query_template_inverted_index_class(QUERY_TEMPLATE_INVERTED_INDEX);
            methods.methods_incorporate_instance_method(INITIALIZE, QUERY_TEMPLATE_INVERTED_INDEX, $list14, NIL, $list15);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INITIALIZE, QUERY_TEMPLATE_INVERTED_INDEX_INITIALIZE_METHOD);
            methods.methods_incorporate_instance_method(LOOKUP, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list22, $list23);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, LOOKUP, QUERY_TEMPLATE_INVERTED_INDEX_LOOKUP_METHOD);
            methods.methods_incorporate_instance_method(REMOVE_KEY, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list32, $list33);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, REMOVE_KEY, QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_KEY_METHOD);
            methods.methods_incorporate_instance_method(REMOVE_VALUE, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list38, $list39);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, REMOVE_VALUE, QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_VALUE_METHOD);
            methods.methods_incorporate_instance_method(INDEX_ALL_QUERIES_IN_FOLDER, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list44, $list45);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INDEX_ALL_QUERIES_IN_FOLDER, QUERY_TEMPLATE_INVERTED_INDEX_INDEX_ALL_QUERIES_IN_FOLDER_METHOD);
            methods.methods_incorporate_instance_method(INDEX_QUERY_WITH_FORMULA_AND_GLOSS, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list52, $list53);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INDEX_QUERY_WITH_FORMULA_AND_GLOSS, $sym57$QUERY_TEMPLATE_INVERTED_INDEX_INDEX_QUERY_WITH_FORMULA_AND_GLOSS_);
            classes.subloop_new_class(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, INDEX_ENTRY_COMPUTER, NIL, NIL, $list58);
            classes.class_set_implements_slot_listeners(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, NIL);
            classes.subloop_note_class_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym62$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
            classes.subloop_note_instance_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym63$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
            subloop_reserved_initialize_query_template_inverted_index_entry_computer_class(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER);
            methods.methods_incorporate_instance_method(SET_INDEX, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list65, $list66);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_INDEX, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_INDEX_METHOD);
            methods.methods_incorporate_instance_method(SET_GENERATION_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list71, $list72);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_MT, $sym75$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_MT_ME);
            methods.methods_incorporate_instance_method(SET_GENERATION_DOMAIN_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list71, $list77);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_DOMAIN_MT, $sym79$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_DOMAI);
            methods.methods_incorporate_instance_method(COMPUTE_INDEX_ENTRIES, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list80, $list81);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, COMPUTE_INDEX_ENTRIES, $sym85$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_COMPUTE_INDEX_ENTRIE);
            register_external_symbol(DISABLE_QL_IR_SEARCH);
            register_external_symbol(ENABLE_QL_IR_SEARCH);
        }
        if (SubLFiles.USE_V2) {
            methods.methods_incorporate_instance_method(INDEX_QUERY_WITH_FORMULA_AND_GLOSS, QUERY_TEMPLATE_INVERTED_INDEX, $list_alt21, $list_alt53, $list_alt54);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INDEX_QUERY_WITH_FORMULA_AND_GLOSS, $sym58$QUERY_TEMPLATE_INVERTED_INDEX_INDEX_QUERY_WITH_FORMULA_AND_GLOSS_);
            classes.subloop_new_class(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, INDEX_ENTRY_COMPUTER, NIL, NIL, $list_alt59);
            classes.subloop_note_class_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym63$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
            classes.subloop_note_instance_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym64$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
            methods.methods_incorporate_instance_method(SET_INDEX, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt66, $list_alt67);
            methods.methods_incorporate_instance_method(SET_GENERATION_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt72, $list_alt73);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_MT, $sym76$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_MT_ME);
            methods.methods_incorporate_instance_method(SET_GENERATION_DOMAIN_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt72, $list_alt78);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_DOMAIN_MT, $sym80$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_DOMAI);
            methods.methods_incorporate_instance_method(COMPUTE_INDEX_ENTRIES, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list_alt65, $list_alt81, $list_alt82);
            methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, COMPUTE_INDEX_ENTRIES, $sym86$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_COMPUTE_INDEX_ENTRIE);
            sunit_macros.$current_module_sunit_tests$.setDynamicValue(cons(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, sunit_macros.$current_module_sunit_tests$.getDynamicValue()));
            sunit_macros.define_test_case_preamble(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE);
            classes.subloop_new_class(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, CYCL_TEST_CASE, NIL, NIL, $list_alt124);
            classes.class_set_implements_slot_listeners(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, NIL);
            classes.subloop_note_class_initialization_function(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, $sym138$SUBLOOP_RESERVED_INITIALIZE_INVERTED_INDEX_QUERY_TEMPLATE_INSERT_);
            classes.subloop_note_instance_initialization_function(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, $sym142$SUBLOOP_RESERVED_INITIALIZE_INVERTED_INDEX_QUERY_TEMPLATE_INSERT_);
            com.cyc.cycjava.cycl.inverted_index_query_library.subloop_reserved_initialize_inverted_index_query_template_insert_delete_class(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE);
            sunit_macros.define_test_case_postamble(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, $str_alt143$inverted_indexes_concrete, $$$cycl, NIL);
            sunit_macros.def_test_method_register(INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE, TEST_INVERTED_INDEX_QUERY_TEMPLATE_ENTRY_DELETE);
        }
        return NIL;
    }

    public static SubLObject setup_inverted_index_query_library_file_Previous() {
        classes.subloop_new_class(QUERY_TEMPLATE_INVERTED_INDEX, INVERTED_INDEX, NIL, NIL, $list2);
        classes.class_set_implements_slot_listeners(QUERY_TEMPLATE_INVERTED_INDEX, NIL);
        classes.subloop_note_class_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX, SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_CLASS);
        classes.subloop_note_instance_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX, $sym12$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_INSTANC);
        subloop_reserved_initialize_query_template_inverted_index_class(QUERY_TEMPLATE_INVERTED_INDEX);
        methods.methods_incorporate_instance_method(INITIALIZE, QUERY_TEMPLATE_INVERTED_INDEX, $list14, NIL, $list15);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INITIALIZE, QUERY_TEMPLATE_INVERTED_INDEX_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(LOOKUP, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list22, $list23);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, LOOKUP, QUERY_TEMPLATE_INVERTED_INDEX_LOOKUP_METHOD);
        methods.methods_incorporate_instance_method(REMOVE_KEY, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list32, $list33);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, REMOVE_KEY, QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_KEY_METHOD);
        methods.methods_incorporate_instance_method(REMOVE_VALUE, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list38, $list39);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, REMOVE_VALUE, QUERY_TEMPLATE_INVERTED_INDEX_REMOVE_VALUE_METHOD);
        methods.methods_incorporate_instance_method(INDEX_ALL_QUERIES_IN_FOLDER, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list44, $list45);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INDEX_ALL_QUERIES_IN_FOLDER, QUERY_TEMPLATE_INVERTED_INDEX_INDEX_ALL_QUERIES_IN_FOLDER_METHOD);
        methods.methods_incorporate_instance_method(INDEX_QUERY_WITH_FORMULA_AND_GLOSS, QUERY_TEMPLATE_INVERTED_INDEX, $list21, $list52, $list53);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX, INDEX_QUERY_WITH_FORMULA_AND_GLOSS, $sym57$QUERY_TEMPLATE_INVERTED_INDEX_INDEX_QUERY_WITH_FORMULA_AND_GLOSS_);
        classes.subloop_new_class(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, INDEX_ENTRY_COMPUTER, NIL, NIL, $list58);
        classes.class_set_implements_slot_listeners(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, NIL);
        classes.subloop_note_class_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym62$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
        classes.subloop_note_instance_initialization_function(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $sym63$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C);
        subloop_reserved_initialize_query_template_inverted_index_entry_computer_class(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER);
        methods.methods_incorporate_instance_method(SET_INDEX, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list65, $list66);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_INDEX, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_INDEX_METHOD);
        methods.methods_incorporate_instance_method(SET_GENERATION_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list71, $list72);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_MT, $sym75$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_MT_ME);
        methods.methods_incorporate_instance_method(SET_GENERATION_DOMAIN_MT, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list71, $list77);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, SET_GENERATION_DOMAIN_MT, $sym79$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_DOMAI);
        methods.methods_incorporate_instance_method(COMPUTE_INDEX_ENTRIES, QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, $list64, $list80, $list81);
        methods.subloop_register_instance_method(QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER, COMPUTE_INDEX_ENTRIES, $sym85$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_COMPUTE_INDEX_ENTRIE);
        register_external_symbol(DISABLE_QL_IR_SEARCH);
        register_external_symbol(ENABLE_QL_IR_SEARCH);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_inverted_index_query_library_file();
    }

    @Override
    public void initializeVariables() {
        init_inverted_index_query_library_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_inverted_index_query_library_file();
    }

    static {
    }

    static private final SubLList $list_alt2 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")));

    static private final SubLList $list_alt14 = list(makeKeyword("PROTECTED"), makeKeyword("NO-MEMBER-VARIABLES"), T);

    static private final SubLList $list_alt15 = list(list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("VALUE-TEST")), list(makeSymbol("FUNCTION"), EQUALP)), list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEXED-ITEMS")), list(makeSymbol("NEW-INDEXED-ITEMS"))), list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-ENTRY-COMPUTER")), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER")))), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-ENTRY-COMPUTER"))), list(QUOTE, makeSymbol("SET-INDEX")), makeSymbol("SELF")), list(makeSymbol("SET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PHYSICAL-INDEX")), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("IN-MEMORY-EQUALP-INDEX")))), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt21 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list_alt22 = list(makeSymbol("KEY"));

    static private final SubLList $list_alt23 = list(makeString("@param KEY stringp;\n       @return list; a list of (query weight) lists that are indexed on at least one word in KEY.  In the case of multiple matches,\n                     return queries with more words matching the key first.  Also accepts CycL as the key, in which case it\n                     must be one (not more than one) of the terms in the template\'s cycl."), list(makeSymbol("CLET"), list(list(makeSymbol("LOOKUP-ITEMS"), list(makeSymbol("FIF"), list(makeSymbol("STRINGP"), makeSymbol("KEY")), list(makeSymbol("STRING-TOKENIZE-VIA-WORDIFY"), makeSymbol("KEY")), list(makeSymbol("LIST"), makeSymbol("KEY")))), list(makeSymbol("LOOKUP-ITEM-COUNT"), list(makeSymbol("LENGTH"), list(makeSymbol("REMOVE-IF"), list(QUOTE, makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-STOP-WORD?")), makeSymbol("LOOKUP-ITEMS")))), list(makeSymbol("DICT"), list(makeSymbol("NEW-DICTIONARY"))), makeSymbol("SCORED")), list(makeSymbol("CDOLIST"), list(makeSymbol("KEY"), makeSymbol("LOOKUP-ITEMS")), list(makeSymbol("CDOLIST"), list(makeSymbol("SUID"), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("PHYSICAL-INDEX"), list(QUOTE, makeSymbol("LOOKUP")), makeSymbol("KEY"))), list(makeSymbol("CLET"), list(list(makeSymbol("EXISTING-COUNT"), list(makeSymbol("DICTIONARY-LOOKUP"), makeSymbol("DICT"), makeSymbol("SUID"), ZERO_INTEGER))), list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("DICT"), makeSymbol("SUID"), list(makeSymbol("1+"), makeSymbol("EXISTING-COUNT")))))), list(makeSymbol("DO-DICTIONARY"), list(makeSymbol("SUID"), makeSymbol("SCORE"), makeSymbol("DICT")), list(makeSymbol("CPUSH"), list(makeSymbol("LIST"), list(makeSymbol("GET-ITEM-FROM-INDEX-SUID"), makeSymbol("INDEXED-ITEMS"), makeSymbol("SUID")), makeSymbol("SCORE")), makeSymbol("SCORED"))), list(makeSymbol("CLET"), list(list(makeSymbol("NORMALIZED")), list(makeSymbol("HIGHEST-WEIGHT"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("ITEM"), makeSymbol("SCORED")), list(makeSymbol("CLET"), list(list(makeSymbol("RAW-WEIGHT"), list(makeSymbol("SECOND"), makeSymbol("ITEM"))), list(makeSymbol("NORMALIZED-WEIGHT"), list(makeSymbol("/"), makeSymbol("RAW-WEIGHT"), makeSymbol("LOOKUP-ITEM-COUNT")))), list(makeSymbol("PWHEN"), list(makeSymbol(">"), makeSymbol("RAW-WEIGHT"), makeSymbol("HIGHEST-WEIGHT")), list(makeSymbol("CSETQ"), makeSymbol("HIGHEST-WEIGHT"), makeSymbol("RAW-WEIGHT"))), list(makeSymbol("CPUSH"), list(makeSymbol("LIST"), list(makeSymbol("FIRST"), makeSymbol("ITEM")), makeSymbol("NORMALIZED-WEIGHT")), makeSymbol("NORMALIZED")))), list(makeSymbol("CSETQ"), makeSymbol("NORMALIZED"), list(makeSymbol("REMOVE-LOW-WEIGHTED-ITEMS"), makeSymbol("NORMALIZED"), makeSymbol("HIGHEST-WEIGHT"), makeSymbol("LOOKUP-ITEM-COUNT"))), list(makeSymbol("CSETQ"), makeSymbol("NORMALIZED"), list(makeSymbol("SORT"), makeSymbol("NORMALIZED"), list(QUOTE, makeSymbol(">")), list(QUOTE, makeSymbol("SECOND")))), list(RET, makeSymbol("NORMALIZED")))));

    static private final SubLList $list_alt32 = list(makeSymbol("INDEX-KEY"));

    static private final SubLList $list_alt33 = list(list(makeSymbol("CLET"), list(list(makeSymbol("ENTRIES"), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("INDEX-ENTRY-COMPUTER"), list(QUOTE, makeSymbol("COMPUTE-INDEX-ENTRIES")), makeSymbol("INDEX-KEY")))), list(makeSymbol("CDOLIST"), list(makeSymbol("ENTRY"), makeSymbol("ENTRIES")), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("SELF"), list(QUOTE, makeSymbol("REMOVE-VALUE")), makeSymbol("ENTRY")))), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt38 = list(makeSymbol("INDEX-ENTRY"));

    static private final SubLList $list_alt39 = list(list(makeSymbol("FIM"), makeSymbol("PHYSICAL-INDEX"), list(QUOTE, makeSymbol("REMOVE")), makeSymbol("INDEX-ENTRY")), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt44 = list(makeSymbol("ROOT-NODE"));

    static private final SubLList $list_alt45 = list(makeString("index all queries in ROOT-NODE folder and its subfolders"), list(makeSymbol("CLET"), list(list(makeSymbol("TEMPLATES"), list(makeSymbol("TEMPLATES-IN-FOLDER"), makeSymbol("ROOT-NODE"), reader_make_constant_shell("InferencePSC"), T)), list(makeSymbol("ERROR"))), list(makeSymbol("WITH-PPH-MEMOIZATION"), list(makeSymbol("CDOLIST"), list(makeSymbol("TEMPLATE"), makeSymbol("TEMPLATES")), list(makeSymbol("CATCH-ERROR-MESSAGE"), list(makeSymbol("ERROR")), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-AN-ITEM")), makeSymbol("TEMPLATE"))), list(makeSymbol("PWHEN"), makeSymbol("ERROR"), list(makeSymbol("REPORT-QL-INDEX-INITIALIZATION-ERROR"), makeSymbol("TEMPLATE"), makeSymbol("ERROR")), list(makeSymbol("CSETQ"), makeSymbol("ERROR"), NIL))))), list(RET, makeSymbol("SELF")));

    static private final SubLString $str_alt47$Invalid_attempt_to_reuse_memoizat = makeString("Invalid attempt to reuse memoization state in multiple threads simultaneously.");

    static private final SubLList $list_alt53 = list(makeSymbol("TEMPLATE"), makeSymbol("FORMULA"), makeSymbol("GLOSS"));

    static private final SubLList $list_alt54 = list(makeString("index a particular QUERY-TEMPLATE using FORMULA and GLOSS"), list(makeSymbol("CLET"), list(list(makeSymbol("QUERY-FORMULA-ENCAPSULATED"), list(makeSymbol("LIST"), makeKeyword("TEMPLATE"), makeSymbol("TEMPLATE"), makeKeyword("QUERY-GLOSS"), makeSymbol("GLOSS"), makeKeyword("FORMULA"), makeSymbol("FORMULA")))), list(makeSymbol("FUNCALL-INSTANCE-METHOD"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INDEX-AN-ITEM")), makeSymbol("QUERY-FORMULA-ENCAPSULATED"))), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym58$QUERY_TEMPLATE_INVERTED_INDEX_INDEX_QUERY_WITH_FORMULA_AND_GLOSS_ = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-INDEX-QUERY-WITH-FORMULA-AND-GLOSS-METHOD");

    static private final SubLList $list_alt59 = list(list(makeSymbol("GENERATION-MT"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("GENERATION-DOMAIN-MT"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("PARENT-INDEX"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COMPUTE-INDEX-ENTRIES"), list(makeSymbol("ITEM-TO-INDEX")), makeKeyword("PUBLIC")));

    static private final SubLSymbol $sym64$SUBLOOP_RESERVED_INITIALIZE_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_C = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-INSTANCE");

    static private final SubLList $list_alt65 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list_alt66 = list(makeSymbol("INVERTED-INDEX"));

    static private final SubLList $list_alt67 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("INVERTED-INDEX"), makeSymbol("INVERTED-INDEX-P")), list(makeSymbol("CSETQ"), makeSymbol("PARENT-INDEX"), makeSymbol("INVERTED-INDEX")), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym68$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    static private final SubLList $list_alt72 = list(makeSymbol("MT"));

    static private final SubLList $list_alt73 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("MT"), makeSymbol("HLMT-P")), list(makeSymbol("CSETQ"), makeSymbol("GENERATION-MT"), makeSymbol("MT")), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym74$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    static private final SubLSymbol $sym76$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_MT_ME = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-MT-METHOD");

    static private final SubLList $list_alt78 = list(list(makeSymbol("CHECK-TYPE"), makeSymbol("MT"), makeSymbol("HLMT-P")), list(makeSymbol("CSETQ"), makeSymbol("GENERATION-DOMAIN-MT"), makeSymbol("MT")), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym79$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    static private final SubLSymbol $sym80$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_SET_GENERATION_DOMAI = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-SET-GENERATION-DOMAIN-MT-METHOD");

    static private final SubLList $list_alt81 = list(makeSymbol("QUERY-TEMPLATE"));

    static private final SubLList $list_alt82 = list(list(makeSymbol("CLET"), list(list(makeSymbol("INDEXED-ITEMS"), list(makeSymbol("GET-SLOT"), makeSymbol("PARENT-INDEX"), list(QUOTE, makeSymbol("INDEXED-ITEMS")))), makeSymbol("QUERY-TEMPLATE-QUERY"), makeSymbol("COMMENT"), makeSymbol("RESULT")), list(makeSymbol("PCOND"), list(list(makeSymbol("QUERY-TEMPLATE-ENCAPSULATED-P"), makeSymbol("QUERY-TEMPLATE")), list(makeSymbol("CSETQ"), makeSymbol("QUERY-TEMPLATE-QUERY"), list(makeSymbol("GETF"), makeSymbol("QUERY-TEMPLATE"), makeKeyword("FORMULA"))), list(makeSymbol("CSETQ"), makeSymbol("COMMENT"), list(makeSymbol("GETF"), makeSymbol("QUERY-TEMPLATE"), makeKeyword("QUERY-GLOSS"), makeString(""))), list(makeSymbol("CSETQ"), makeSymbol("QUERY-TEMPLATE"), list(makeSymbol("GETF"), makeSymbol("QUERY-TEMPLATE"), makeKeyword("TEMPLATE")))), list(T, list(makeSymbol("CMULTIPLE-VALUE-SETQ"), list(makeSymbol("QUERY-TEMPLATE-QUERY"), makeSymbol("COMMENT")), list(makeSymbol("QUERY-AND-GLOSS-FOR-FORMULA-TEMPLATE"), makeSymbol("QUERY-TEMPLATE"))))), list(makeSymbol("CLET"), list(list(makeSymbol("MY-INDEX"), list(makeSymbol("MAYBE-ADD-NEW-INDEXED-ITEM"), makeSymbol("INDEXED-ITEMS"), makeSymbol("QUERY-TEMPLATE")))), list(makeSymbol("CDOLIST"), list(makeSymbol("FORT"), list(makeSymbol("EXPRESSION-GATHER"), makeSymbol("QUERY-TEMPLATE-QUERY"), list(QUOTE, makeSymbol("FORT-P")), T)), list(makeSymbol("CPUSH"), list(makeSymbol("NEW-INVERTED-INDEX-ENTRY"), makeSymbol("FORT"), makeSymbol("MY-INDEX")), makeSymbol("RESULT"))), list(makeSymbol("PWHEN"), makeSymbol("*QUERY-INDEX-INDEX-GENERATION?*"), list(makeSymbol("CDOLIST"), list(makeSymbol("ENTRY"), list(makeSymbol("COMPUTE-INVERTED-INDEX-ENTRIES-FOR-STRING"), list(makeSymbol("GENERATE-QUESTION"), makeSymbol("QUERY-TEMPLATE-QUERY")), makeSymbol("MY-INDEX"))), list(makeSymbol("CPUSH"), makeSymbol("ENTRY"), makeSymbol("RESULT")))), list(makeSymbol("PWHEN"), makeSymbol("COMMENT"), list(makeSymbol("CDOLIST"), list(makeSymbol("ENTRY"), list(makeSymbol("COMPUTE-INVERTED-INDEX-ENTRIES-FOR-STRING"), makeSymbol("COMMENT"), makeSymbol("MY-INDEX"))), list(makeSymbol("CPUSH"), makeSymbol("ENTRY"), makeSymbol("RESULT")))), list(RET, makeSymbol("RESULT")))));

    static private final SubLSymbol $sym83$OUTER_CATCH_FOR_QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_METH = makeUninternedSymbol("OUTER-CATCH-FOR-QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-METHOD");

    static private final SubLSymbol $sym86$QUERY_TEMPLATE_INVERTED_INDEX_ENTRY_COMPUTER_COMPUTE_INDEX_ENTRIE = makeSymbol("QUERY-TEMPLATE-INVERTED-INDEX-ENTRY-COMPUTER-COMPUTE-INDEX-ENTRIES-METHOD");

    static private final SubLString $str_alt87$ql_qa_cyc_com = makeString("ql-qa@cyc.com");

    static private final SubLString $str_alt88$while_initializing_QL_search__hit = makeString("while initializing QL search, hit an error on ");

    static private final SubLString $str_alt89$Error_Message__ = makeString("Error Message: ");

    static private final SubLString $str_alt90$Host__ = makeString("Host: ");

    static private final SubLString $str_alt91$KB__ = makeString("KB: ");

    static private final SubLString $str_alt92$System__ = makeString("System: ");

    static private final SubLString $str_alt93$User__ = makeString("User: ");

    static private final SubLString $str_alt94$Error_loading_ = makeString("Error loading ");

    static private final SubLString $str_alt95$cyc_cyc_com = makeString("cyc@cyc.com");

    static private final SubLList $list_alt96 = list(makeString("application.cyc-ir.lucene.ql-index.in-memory"));

    static private final SubLList $list_alt103 = list(makeSymbol("CYCL"), makeSymbol("WEIGHT"));

    public static final SubLObject $const107$querySpecificationForFormulaTempl = reader_make_constant_shell("querySpecificationForFormulaTemplate");

    static private final SubLList $list_alt114 = list(makeKeyword("MAX-NUMBER"), ONE_INTEGER);

    static private final SubLList $list_alt118 = list(makeKeyword("X"));

    static private final SubLList $list_alt119 = list(makeKeyword("MAX-NUMBER"), ONE_INTEGER, makeKeyword("ANSWER-LANGUAGE"), makeKeyword("HL"), makeKeyword("MAX-TRANSFORMATION-DEPTH"), ZERO_INTEGER, makeKeyword("ALLOWED-MODULES"), makeKeyword("REMOVAL-ASSERTED-SENTENCE-LOOKUP-POS"));

    private static final SubLSymbol INVERTED_INDEX_QUERY_TEMPLATE_INSERT_DELETE = makeSymbol("INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE");

    private static final SubLSymbol CYCL_TEST_CASE = makeSymbol("CYCL-TEST-CASE");

    static private final SubLList $list_alt124 = list(new SubLObject[]{ list(makeSymbol("INDEX"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("LIBRARY-ROOT-NODE"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("LIBRARY-MT"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("GOOD-ITEMS"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("BAD-ITEMS"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("SPECIAL-ITEMS"), makeKeyword("INSTANCE"), makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SETUP"), NIL, makeKeyword("PRIVATE")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CLEANUP"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("TEST-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("TEST-INVERTED-INDEX-QUERY-TEMPLATE-ENTRY-DELETE"), NIL, makeKeyword("PROTECTED")) });

    private static final SubLSymbol SPECIAL_ITEMS = makeSymbol("SPECIAL-ITEMS");

    private static final SubLSymbol BAD_ITEMS = makeSymbol("BAD-ITEMS");

    private static final SubLSymbol GOOD_ITEMS = makeSymbol("GOOD-ITEMS");

    private static final SubLSymbol LIBRARY_MT = makeSymbol("LIBRARY-MT");

    private static final SubLSymbol LIBRARY_ROOT_NODE = makeSymbol("LIBRARY-ROOT-NODE");

    static private final SubLSymbol $sym138$SUBLOOP_RESERVED_INITIALIZE_INVERTED_INDEX_QUERY_TEMPLATE_INSERT_ = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-CLASS");

    static private final SubLSymbol $sym142$SUBLOOP_RESERVED_INITIALIZE_INVERTED_INDEX_QUERY_TEMPLATE_INSERT_ = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-INVERTED-INDEX-QUERY-TEMPLATE-INSERT-DELETE-INSTANCE");

    static private final SubLString $str_alt143$inverted_indexes_concrete = makeString("inverted-indexes-concrete");

    static private final SubLString $$$cycl = makeString("cycl");

    private static final SubLSymbol TEST_INVERTED_INDEX_QUERY_TEMPLATE_ENTRY_DELETE = makeSymbol("TEST-INVERTED-INDEX-QUERY-TEMPLATE-ENTRY-DELETE");
}

/**
 * Total time: 541 ms
 */
