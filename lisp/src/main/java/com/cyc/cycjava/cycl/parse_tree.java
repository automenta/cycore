/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.sentence_free_variables;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.equal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.multiply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numG;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.booleanp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.function_spec_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.integerp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.keywordp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.listp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.make_vector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.set_aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.assoc;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.butlast;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.princ;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.terpri;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_string;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.sbhl.sbhl_marking_vars;
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
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class parse_tree extends SubLTranslatedFile implements V12 {
    static private final SubLString $str_alt250$ = makeString("");

    public static final SubLFile me = new parse_tree();

 public static final String myName = "com.cyc.cycjava.cycl.parse_tree";


    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $phrase_tree_allow_adjacent_daughters_to_merge$ = makeSymbol("*PHRASE-TREE-ALLOW-ADJACENT-DAUGHTERS-TO-MERGE*");

    // defparameter
    // a mapping from categories to types of phrase trees
    /**
     * a mapping from categories to types of phrase trees
     */
    @LispMethod(comment = "a mapping from categories to types of phrase trees\ndefparameter")
    private static final SubLSymbol $category_to_phrase$ = makeSymbol("*CATEGORY-TO-PHRASE*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol NOMINAL_TREE = makeSymbol("NOMINAL-TREE");

    static private final SubLList $list1 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NUMBER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-GENDER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PERSON"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SINGULAR-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PLURAL-P"), NIL, makeKeyword("PUBLIC")));

    private static final SubLSymbol VERBAL_TREE = makeSymbol("VERBAL-TREE");

    static private final SubLList $list3 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PREDICATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SUBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-SUBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PASSIVE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FINITE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-LOCAL-OBJECTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NONLOCAL-OBJECTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INVERTED-P"), NIL, makeKeyword("PROTECTED")) });

    private static final SubLSymbol ADVERBIAL_TREE = makeSymbol("ADVERBIAL-TREE");

    private static final SubLSymbol PREPOSITIONAL_TREE = makeSymbol("PREPOSITIONAL-TREE");

    private static final SubLSymbol PARTICLE_TREE = makeSymbol("PARTICLE-TREE");

    private static final SubLSymbol ADJECTIVAL_TREE = makeSymbol("ADJECTIVAL-TREE");

    private static final SubLSymbol QUESTION_TREE = makeSymbol("QUESTION-TREE");

    private static final SubLSymbol WH_TREE = makeSymbol("WH-TREE");

    private static final SubLSymbol NUMERICAL_TREE = makeSymbol("NUMERICAL-TREE");

    private static final SubLSymbol FINITE_VERBAL_WORD_TREE = makeSymbol("FINITE-VERBAL-WORD-TREE");

    private static final SubLSymbol QUANTIFIER_TREE = makeSymbol("QUANTIFIER-TREE");

    static private final SubLList $list13 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIED"), NIL, makeKeyword("PUBLIC")));

    private static final SubLSymbol PRONOUN_TREE = makeSymbol("PRONOUN-TREE");

    static private final SubLList $list17 = list(makeSymbol("CYCLIFIABLE"), makeSymbol("PARSE-TREE-INTERFACE"));

    static private final SubLList $list18 = list(new SubLObject[]{ list(makeSymbol("STRING"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("CATEGORY"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("MOTHER"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("IDX"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("MODIFIERS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("CONTEXT"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DEEP-COPY"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ROOT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-STRING"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CATEGORY"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-INDEX"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PATH"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MOTHER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SISTER"), list(makeSymbol("INDEX")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("YIELD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PARSE-EXPRESSION"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEAD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-COMPLEMENTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MODIFIERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MODIFIEDS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CONTEXT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("TEMPORAL-TREE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFIABLE-QUESTION-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ROOT-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY"), list(makeSymbol("LEXICON")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LOGICAL-FORM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY"), list(makeSymbol("PROPERTIES")), makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CYCLIFIER"), list(makeSymbol("PROPERTIES")), makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COVERS"), list(makeSymbol("LIST-OF-STRINGS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MINIMALLY-COVERING-SUBTREES"), list(makeSymbol("LIST-OF-STRINGS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), list(makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COVERS-MINIMALLY"), list(makeSymbol("LIST-OF-STRINGS")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTORS"), NIL, makeKeyword("PROTECTED")) });

    static private final SubLList $list26 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list27 = list(makeSymbol("&OPTIONAL"), makeSymbol("PROPERTIES"));

    static private final SubLList $list28 = list(list(new SubLObject[]{ makeSymbol("CDESTRUCTURING-BIND"), list(new SubLObject[]{ makeSymbol("&KEY"), list(makeSymbol("LEXICON"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("CYCLIFIER-LEXICON")))), list(makeSymbol("SCORE-FUNCTION"), list(QUOTE, makeSymbol("CYCLIFICATION-SCORE"))), list(makeSymbol("OUTPUT"), makeKeyword("BRIEF")), list(makeSymbol("WFF-CHECK?"), T), makeSymbol("CONTEXT"), list(makeSymbol("DISAMBIGUATOR"), NIL), list(makeSymbol("COREFERENCE-RESOLVER"), NIL), list(makeSymbol("ERROR-HANDLING"), makeKeyword("THROW")), list(makeSymbol("SUBCYCLIFIER-POOL"), list(makeSymbol("NEW-CYCLIFIER-POOL"), list(makeSymbol("LIST"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("DATE-CYCLIFIER")))))), makeSymbol("&ALLOW-OTHER-KEYS") }), makeSymbol("PROPERTIES"), list(makeSymbol("CHECK-TYPE"), makeSymbol("LEXICON"), makeSymbol("CYCLIFIER-LEXICON-P")), list(makeSymbol("CHECK-TYPE"), makeSymbol("SCORE-FUNCTION"), makeSymbol("FUNCTION-SPEC-P")), list(makeSymbol("CHECK-TYPE"), makeSymbol("OUTPUT"), makeSymbol("KEYWORDP")), list(makeSymbol("CHECK-TYPE-IF-PRESENT"), makeSymbol("CONTEXT"), makeSymbol("PARSE-TREE-CONTEXT-P")), list(makeSymbol("CHECK-TYPE"), makeSymbol("WFF-CHECK?"), makeSymbol("BOOLEANP")), list(makeSymbol("MUST"), list(makeSymbol("MEMBER"), makeSymbol("ERROR-HANDLING"), list(QUOTE, list(makeKeyword("THROW"), $WARN, makeKeyword("IGNORE")))), makeString("Invalid error-handling tag ~a"), makeSymbol("ERROR-HANDLING")), list(makeSymbol("CHECK-TYPE-IF-PRESENT"), makeSymbol("SUBCYCLIFIER-POOL"), makeSymbol("CYCLIFIER-POOL-P")), list(new SubLObject[]{ makeSymbol("CLET"), list(list(makeSymbol("CYCLIFIER"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("CYCLIFIER"))))), list(makeSymbol("SET-CYCLIFIER-SCORE-FUNCTION"), makeSymbol("CYCLIFIER"), makeSymbol("SCORE-FUNCTION")), list(makeSymbol("SET-CYCLIFIER-OUTPUT"), makeSymbol("CYCLIFIER"), makeSymbol("OUTPUT")), list(makeSymbol("SET-CYCLIFIER-ERROR-HANDLING"), makeSymbol("CYCLIFIER"), makeSymbol("ERROR-HANDLING")), list(makeSymbol("SET-CYCLIFIER-SUBCYCLIFIER-POOL"), makeSymbol("CYCLIFIER"), makeSymbol("SUBCYCLIFIER-POOL")), list(makeSymbol("PWHEN"), makeSymbol("LEXICON"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("LEXIFY")), makeSymbol("LEXICON"))), list(makeSymbol("PWHEN"), makeSymbol("DISAMBIGUATOR"), list(makeSymbol("PWHEN"), list(makeSymbol("NULL"), makeSymbol("CONTEXT")), list(makeSymbol("CSETQ"), makeSymbol("CONTEXT"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("PARSE-TREE-CONTEXT"))))), list(makeSymbol("FIM"), makeSymbol("DISAMBIGUATOR"), list(QUOTE, makeSymbol("DISAMBIGUATE")), makeSymbol("SELF"), makeSymbol("CONTEXT"))), list(makeSymbol("IGNORE"), makeSymbol("COREFERENCE-RESOLVER")), list(makeSymbol("SET-CYCLIFIER-TREE"), makeSymbol("CYCLIFIER"), makeSymbol("SELF")), list(makeSymbol("SET-CYCLIFIER-WFF-CHECK?"), makeSymbol("CYCLIFIER"), makeSymbol("WFF-CHECK?")), list(makeSymbol("PWHEN"), makeSymbol("WFF-CHECK?"), list(makeSymbol("SET-CYCLIFIER-MEMOIZATION-STATE"), makeSymbol("CYCLIFIER"), list(makeSymbol("NEW-MEMOIZATION-STATE"))), list(makeSymbol("SET-CYCLIFIER-SBHL-RESOURCE"), makeSymbol("CYCLIFIER"), list(makeSymbol("NEW-SBHL-MARKING-SPACE-RESOURCE"), TEN_INTEGER))), list(RET, makeSymbol("CYCLIFIER")) }) }));

    static private final SubLSymbol $sym29$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol CYCLIFICATION_SCORE = makeSymbol("CYCLIFICATION-SCORE");

    private static final SubLSymbol $kw36$WFF_CHECK_ = makeKeyword("WFF-CHECK?");

    private static final SubLSymbol DATE_CYCLIFIER = makeSymbol("DATE-CYCLIFIER");

    private static final SubLSymbol CYCLIFIER_LEXICON_P = makeSymbol("CYCLIFIER-LEXICON-P");

    private static final SubLSymbol PARSE_TREE_CONTEXT_P = makeSymbol("PARSE-TREE-CONTEXT-P");

    static private final SubLList $list49 = list(makeKeyword("THROW"), $WARN, makeKeyword("IGNORE"));

    static private final SubLString $str50$Invalid_error_handling_tag__a = makeString("Invalid error-handling tag ~a");

    private static final SubLSymbol CYCLIFIER_POOL_P = makeSymbol("CYCLIFIER-POOL-P");

    private static final SubLSymbol DISAMBIGUATE = makeSymbol("DISAMBIGUATE");

    private static final SubLSymbol PARSE_TREE_GET_CYCLIFIER_METHOD = makeSymbol("PARSE-TREE-GET-CYCLIFIER-METHOD");

    static private final SubLList $list58 = list(list(makeSymbol("CLET"), list(list(makeSymbol("CYCLIFIER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CYCLIFIER")), makeSymbol("PROPERTIES"))), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("WHILE"), list(makeSymbol("FIM"), makeSymbol("CYCLIFIER"), list(QUOTE, makeSymbol("HAS-NEXT?"))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("CYCLIFIER"), list(QUOTE, makeSymbol("NEXT"))), makeSymbol("CYCLS"))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("CYCLS")))));

    static private final SubLSymbol $sym59$HAS_NEXT_ = makeSymbol("HAS-NEXT?");

    private static final SubLSymbol PARSE_TREE_CYCLIFY_METHOD = makeSymbol("PARSE-TREE-CYCLIFY-METHOD");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PARSE_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PARSE-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PARSE_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PARSE-TREE-INSTANCE");

    private static final SubLSymbol DEEP_COPY = makeSymbol("DEEP-COPY");

    static private final SubLList $list68 = list(makeString("@return parse-tree-p; a deep copy of this parse tree.\n   @owner bertolo."), list(makeSymbol("CLET"), list(list(makeSymbol("CLASS-OF-SELF"), list(makeSymbol("INSTANCE-CLASS"), makeSymbol("SELF"))), list(makeSymbol("NEW"), list(makeSymbol("FUNCALL-CLASS-METHOD"), makeSymbol("CLASS-OF-SELF"), list(QUOTE, makeSymbol("NEW")))), list(makeSymbol("ALL-INSTANCE-SLOTS"), list(makeSymbol("INSTANCES-ALL-INSTANCE-SLOTS"), makeSymbol("SELF")))), list(makeSymbol("CDOLIST"), list(makeSymbol("INSTANCE-SLOT"), makeSymbol("ALL-INSTANCE-SLOTS")), list(makeSymbol("CLET"), list(list(makeSymbol("SLOT-VALUE"), list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), makeSymbol("INSTANCE-SLOT"))), list(makeSymbol("VALUE-COPY"), NIL)), list(makeSymbol("PCOND"), list(list(makeSymbol("OBJECT-P"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("FIM"), makeSymbol("SLOT-VALUE"), list(QUOTE, makeSymbol("DEEP-COPY"))))), list(list(makeSymbol("LISTP"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("COPY-LIST"), makeSymbol("SLOT-VALUE")))), list(list(makeSymbol("VECTORP"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("COPY-VECTOR"), makeSymbol("SLOT-VALUE")))), list(list(makeSymbol("HASH-TABLE-P"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("COPY-HASHTABLE"), makeSymbol("SLOT-VALUE")))), list(T, list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), makeSymbol("SLOT-VALUE")))), list(makeSymbol("SET-SLOT"), makeSymbol("NEW"), makeSymbol("INSTANCE-SLOT"), makeSymbol("VALUE-COPY")))), list(RET, makeSymbol("NEW"))));

    private static final SubLSymbol PARSE_TREE_DEEP_COPY_METHOD = makeSymbol("PARSE-TREE-DEEP-COPY-METHOD");

    private static final SubLSymbol GET_ROOT = makeSymbol("GET-ROOT");

    static private final SubLList $list72 = list(makeString("@return phrase-tree-p; the root of this tree"), list(makeSymbol("CLET"), list(list(makeSymbol("TREE"), makeSymbol("SELF"))), list(makeSymbol("UNTIL"), list(makeSymbol("FIM"), makeSymbol("TREE"), list(QUOTE, makeSymbol("ROOT-P"))), list(makeSymbol("CSETQ"), makeSymbol("TREE"), list(makeSymbol("FIM"), makeSymbol("TREE"), list(QUOTE, makeSymbol("GET-MOTHER"))))), list(RET, makeSymbol("TREE"))));

    private static final SubLSymbol PARSE_TREE_GET_ROOT_METHOD = makeSymbol("PARSE-TREE-GET-ROOT-METHOD");

    static private final SubLList $list77 = list(makeString("@return keywordp; the category of this parse-tree"), list(RET, makeSymbol("CATEGORY")));

    static private final SubLSymbol $sym78$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_GET_CATEGORY_METHOD = makeSymbol("PARSE-TREE-GET-CATEGORY-METHOD");

    static private final SubLList $list81 = list(makeString("@return non-negative-integer-p; the number of sisters left to this parse tree"), list(RET, makeSymbol("IDX")));

    static private final SubLSymbol $sym82$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_GET_INDEX_METHOD = makeSymbol("PARSE-TREE-GET-INDEX-METHOD");

    private static final SubLSymbol GET_PATH = makeSymbol("GET-PATH");

    static private final SubLList $list85 = list(makeString("@return listp; the path from the root to this parse tree"), list(makeSymbol("CLET"), list(list(makeSymbol("PATH"), list(makeSymbol("FWHEN"), makeSymbol("IDX"), list(makeSymbol("LIST"), makeSymbol("IDX"))))), list(makeSymbol("CDOLIST"), list(makeSymbol("ANCESTOR"), list(makeSymbol("BUTLAST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-ANCESTORS"))))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("ANCESTOR"), list(QUOTE, makeSymbol("GET-INDEX"))), makeSymbol("PATH"))), list(RET, makeSymbol("PATH"))));

    static private final SubLSymbol $sym86$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_GET_PATH_METHOD = makeSymbol("PARSE-TREE-GET-PATH-METHOD");

    static private final SubLList $list89 = list(makeString("@return parse-tree-p; the mother of this tree"), list(RET, makeSymbol("MOTHER")));

    static private final SubLSymbol $sym90$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_GET_MOTHER_METHOD = makeSymbol("PARSE-TREE-GET-MOTHER-METHOD");

    static private final SubLList $list93 = list(makeString("@return word-tree-p; the (first) semantic head of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS"))))));

    private static final SubLSymbol PARSE_TREE_GET_SEMANTIC_HEAD_METHOD = makeSymbol("PARSE-TREE-GET-SEMANTIC-HEAD-METHOD");

    static private final SubLList $list97 = list(makeString("@return listp; a list of all complements of this tree, in surface order"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-COMPLEMENTS")))))));

    private static final SubLSymbol PARSE_TREE_GET_COMPLEMENTS_METHOD = makeSymbol("PARSE-TREE-GET-COMPLEMENTS-METHOD");

    static private final SubLList $list101 = list(makeString("@return listp; a list of modifiers of this tree"), list(RET, NIL));

    private static final SubLSymbol PARSE_TREE_GET_MODIFIERS_METHOD = makeSymbol("PARSE-TREE-GET-MODIFIERS-METHOD");

    static private final SubLList $list104 = list(makeString("@return listp; the constituents this tree modifies"), list(RET, NIL));

    private static final SubLSymbol PARSE_TREE_GET_MODIFIEDS_METHOD = makeSymbol("PARSE-TREE-GET-MODIFIEDS-METHOD");

    static private final SubLList $list107 = list(makeString("@return parse-tree-context-p; other parse trees created in this tree's context"), list(RET, makeSymbol("CONTEXT")));

    static private final SubLSymbol $sym108$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_GET_CONTEXT_METHOD = makeSymbol("PARSE-TREE-GET-CONTEXT-METHOD");

    static private final SubLList $list111 = list(makeString("@return booleanp; t if this tree is temporal, nil otherwise\n   @note this is just a fallback; specific trees override this"), list(RET, NIL));

    private static final SubLSymbol PARSE_TREE_TEMPORAL_TREE_P_METHOD = makeSymbol("PARSE-TREE-TEMPORAL-TREE-P-METHOD");

    private static final SubLSymbol CYCLIFIABLE_QUESTION_P = makeSymbol("CYCLIFIABLE-QUESTION-P");

    static private final SubLList $list114 = list(makeString("@return boolean; t if this is a question, nil otherwise"), list(RET, list(makeSymbol("QUESTION-TREE-P"), makeSymbol("SELF"))));

    private static final SubLSymbol PARSE_TREE_CYCLIFIABLE_QUESTION_P_METHOD = makeSymbol("PARSE-TREE-CYCLIFIABLE-QUESTION-P-METHOD");

    static private final SubLList $list116 = list(makeString("@return booleanp; t if this tree is the root, nil otherwise"), list(RET, list(makeSymbol("CNOT"), makeSymbol("MOTHER"))));

    static private final SubLSymbol $sym117$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_ROOT_P_METHOD = makeSymbol("PARSE-TREE-ROOT-P-METHOD");

    static private final SubLList $list119 = list(makeSymbol("LEXICON"));

    static private final SubLList $list120 = list(makeString("@return parse-tree-p; this parse-tree with lexical entries added"), list(makeSymbol("CLET"), list(list(makeSymbol("*ALLOW-DUPLICATE-CYCLIFICATIONS?*"), T), list(makeSymbol("*CYCLIFICATION-COMPLETE*"), NIL)), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol PARSE_TREE_LEXIFY_METHOD = makeSymbol("PARSE-TREE-LEXIFY-METHOD");

    static private final SubLList $list123 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list124 = list(makeString("@return parse-tree-p; this parse-tree with lexical entries added"), list(makeSymbol("IGNORE"), makeSymbol("LEXICON")), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol PARSE_TREE_LEXIFY_INT_METHOD = makeSymbol("PARSE-TREE-LEXIFY-INT-METHOD");

    static private final SubLList $list127 = list(makeString("@return listp; the CycL representation of the meaning of this parse-tree"), list(RET, NIL));

    private static final SubLSymbol PARSE_TREE_CYCLIFY_INT_METHOD = makeSymbol("PARSE-TREE-CYCLIFY-INT-METHOD");

    static private final SubLList $list129 = list(makeString("@return listp; the list of ancestors of this parse tree"), list(RET, list(makeSymbol("FWHEN"), makeSymbol("MOTHER"), list(makeSymbol("CONS"), makeSymbol("MOTHER"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTORS")))))));

    static private final SubLSymbol $sym130$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_GET_ANCESTORS_METHOD = makeSymbol("PARSE-TREE-GET-ANCESTORS-METHOD");

    static private final SubLList $list133 = list(makeSymbol("INDEX"));

    static private final SubLList $list134 = list(makeString("@return parse-tree-p; the sister INDEX positions to the right of this\n   parse tree if INDEX is positive and to the left if INDEX is negative;\n   nil if there is no such sister"), list(makeSymbol("CHECK-TYPE"), makeSymbol("INDEX"), makeSymbol("INTEGERP")), list(makeSymbol("PWHEN"), makeSymbol("IDX"), list(makeSymbol("CLET"), list(list(makeSymbol("SISTER-INDEX"), list(makeSymbol("+"), makeSymbol("IDX"), makeSymbol("INDEX")))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("CAND"), makeSymbol("MOTHER"), list(makeSymbol("NON-NEGATIVE-INTEGER-P"), makeSymbol("SISTER-INDEX"))), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-DAUGHTER")), list(makeSymbol("+"), makeSymbol("IDX"), makeSymbol("INDEX"))))))));

    static private final SubLSymbol $sym135$OUTER_CATCH_FOR_PARSE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PARSE-TREE-METHOD");

    private static final SubLSymbol PARSE_TREE_GET_SISTER_METHOD = makeSymbol("PARSE-TREE-GET-SISTER-METHOD");

    static private final SubLList $list140 = list(makeSymbol("STREAM"), makeSymbol("DEPTH"));

    static private final SubLList $list141 = list(makeString("Pretty-prints this phrase-tree to STREAM, ignoring DEPTH"), list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(RET, list(makeSymbol("PPRINT-PARSE-TREE"), makeSymbol("SELF"), makeSymbol("STREAM"), ZERO_INTEGER)));

    private static final SubLSymbol PARSE_TREE_PRINT_METHOD = makeSymbol("PARSE-TREE-PRINT-METHOD");

    static private final SubLString $str143$__W_ = makeString("#<W(");

    static private final SubLString $str144$_ = makeString(")");

    static private final SubLString $str146$_ = makeString("*");

    static private final SubLString $str147$__ = makeString(": ");

    static private final SubLString $str149$_ = makeString(">");

    private static final SubLSymbol RETOKENIZE = makeSymbol("RETOKENIZE");

    static private final SubLList $list151 = list(list(makeSymbol("SUB-TREE"), makeSymbol("PARSE-TREE"), makeSymbol("&OPTIONAL"), makeSymbol("DONE?"), list(makeSymbol("CATEGORIES"), makeKeyword("ALL")), list(makeSymbol("ORDER"), makeKeyword("DFR2L"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym154$STACK = makeUninternedSymbol("STACK");

    static private final SubLSymbol $sym155$ORDER_VAR = makeUninternedSymbol("ORDER-VAR");

    static private final SubLSymbol $sym156$POSSIBLE_ORDERS = makeUninternedSymbol("POSSIBLE-ORDERS");

    static private final SubLList $list158 = list(list(makeSymbol("CREATE-STACK")));

    static private final SubLList $list159 = list(list(makeSymbol("LIST"), makeKeyword("DFL2R"), makeKeyword("DFR2L")));

    static private final SubLSymbol $sym161$MEMBER_ = makeSymbol("MEMBER?");

    static private final SubLList $list162 = list(list(makeSymbol("FUNCTION"), EQ));

    static private final SubLString $str163$_S_is_not_one_of__S = makeString("~S is not one of ~S");

    private static final SubLSymbol STACK_EMPTY_P = makeSymbol("STACK-EMPTY-P");

    static private final SubLList $list176 = list(makeKeyword("DFL2R"));

    static private final SubLSymbol $sym177$CATEGORIES_VAR = makeUninternedSymbol("CATEGORIES-VAR");

    private static final SubLSymbol DO_PARSE_TREE_SUBTREES = makeSymbol("DO-PARSE-TREE-SUBTREES");

    static private final SubLList $list179 = list(makeKeyword("ALL"));

    static private final SubLList $list181 = list(list(QUOTE, makeSymbol("GET-CATEGORY")));

    static private final SubLList $list182 = list(list(makeSymbol("WORD-TREE"), makeSymbol("PARSE-TREE"), makeSymbol("&OPTIONAL"), makeSymbol("DONE?"), list(makeSymbol("CATEGORIES"), makeKeyword("ALL"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol PHRASE_TREE = makeSymbol("PHRASE-TREE");

    static private final SubLList $list185 = list(new SubLObject[]{ list(makeSymbol("DAUGHTERS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-STRING"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DAUGHTER"), list(makeSymbol("INDEX")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DESCENDANT"), list(makeSymbol("PATH")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SEARCH"), list(makeSymbol("STRING")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-REFS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DAUGHTER-COUNT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("YIELD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PARSE-EXPRESSION"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEAD-DAUGHTER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MODIFIERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CONJUNCTION"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COORDINATE-PHRASE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), list(makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RETOKENIZE"), list(makeSymbol("WORDS"), makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RETOKENIZE-INT"), list(makeSymbol("WORDS"), makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LOGICAL-FORM-INT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FIND"), list(makeSymbol("PREDICATE")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FIND-FIRST"), list(makeSymbol("PREDICATE")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FIND-LAST"), list(makeSymbol("PREDICATE")), makeKeyword("PROTECTED")) });

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PHRASE_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PHRASE-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PHRASE_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PHRASE-TREE-INSTANCE");

    static private final SubLList $list190 = list(makeString("@return listp; the list of head daughers of this phrase"), list(RET, NIL));

    private static final SubLSymbol PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD");

    static private final SubLList $list193 = list(makeString("@return parse-tree-p; the (first) head daughter of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS"))))));

    private static final SubLSymbol PHRASE_TREE_GET_HEAD_DAUGHTER_METHOD = makeSymbol("PHRASE-TREE-GET-HEAD-DAUGHTER-METHOD");

    static private final SubLList $list195 = list(makeString("@return word-tree-p; the (first) head of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEADS"))))));

    private static final SubLSymbol PHRASE_TREE_GET_HEAD_METHOD = makeSymbol("PHRASE-TREE-GET-HEAD-METHOD");

    private static final SubLSymbol GET_SEMANTIC_HEAD_DAUGHTERS = makeSymbol("GET-SEMANTIC-HEAD-DAUGHTERS");

    static private final SubLList $list199 = list(makeString("@return listp; the list of head daughers of this phrase"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS")))));

    private static final SubLSymbol PHRASE_TREE_GET_SEMANTIC_HEAD_DAUGHTERS_METHOD = makeSymbol("PHRASE-TREE-GET-SEMANTIC-HEAD-DAUGHTERS-METHOD");

    private static final SubLSymbol GET_SEMANTIC_HEAD_DAUGHTER = makeSymbol("GET-SEMANTIC-HEAD-DAUGHTER");

    static private final SubLList $list202 = list(makeString("@return parse-tree-p; the (first) semantic head daughter of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD-DAUGHTERS"))))));

    private static final SubLSymbol PHRASE_TREE_GET_SEMANTIC_HEAD_DAUGHTER_METHOD = makeSymbol("PHRASE-TREE-GET-SEMANTIC-HEAD-DAUGHTER-METHOD");

    static private final SubLList $list205 = list(makeKeyword("READ-ONLY"), makeKeyword("PUBLIC"));

    static private final SubLList $list206 = list(makeString("@return listp; the unique referents of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS")))), list(makeSymbol("REFS"), NIL)), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("CNOT"), makeSymbol("HEADS")), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), ONE_INTEGER)), list(makeSymbol("CLET"), list(list(makeSymbol("DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER))), list(makeSymbol("PIF"), list(makeSymbol("CAND"), list(makeSymbol("QUANTIFIER-TREE-P"), makeSymbol("DAUGHTER")), list(makeSymbol("PHRASE-TREE-P"), makeSymbol("DAUGHTER")), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("$"))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), makeSymbol("HEADS")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("HEADS"))))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), makeSymbol("HEADS")), list(makeSymbol("CDOLIST"), list(makeSymbol("REF"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-REFS")))), list(makeSymbol("CPUSH"), makeSymbol("REF"), makeSymbol("REFS")))), list(RET, makeSymbol("REFS"))));

    private static final SubLSymbol $kw208$_ = makeKeyword("$");

    private static final SubLSymbol PHRASE_TREE_GET_REFS_METHOD = makeSymbol("PHRASE-TREE-GET-REFS-METHOD");

    static private final SubLList $list210 = list(makeString("@return listp; the list of all modifiers of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-MODIFIERS")))))));

    private static final SubLSymbol PHRASE_TREE_GET_MODIFIERS_METHOD = makeSymbol("PHRASE-TREE-GET-MODIFIERS-METHOD");

    static private final SubLList $list213 = list(makeString("@return object; the conjunction of this tree if there is one, nil otherwise"), list(RET, list(makeSymbol("FIND-FIRST"), makeSymbol("SELF"), list(QUOTE, makeSymbol("COORDINATE-WORD-TREE-P")))));

    private static final SubLSymbol COORDINATE_WORD_TREE_P = makeSymbol("COORDINATE-WORD-TREE-P");

    private static final SubLSymbol PHRASE_TREE_GET_CONJUNCTION_METHOD = makeSymbol("PHRASE-TREE-GET-CONJUNCTION-METHOD");

    static private final SubLList $list216 = list(makeKeyword("NO-MEMBER-VARIABLES"), makeKeyword("PROTECTED"));

    static private final SubLList $list217 = list(makeString("@return phrase-tree-p; this phrase-tree with lexical entries added"), list(makeSymbol("CLET"), list(makeSymbol("WORDS"), makeSymbol("ORIG-SPELLING"), makeSymbol("NORMALIZED-SPELLING"), makeSymbol("FIRST-WORD1"), makeSymbol("FIRST-WORD2"), makeSymbol("YIELD"), list(makeSymbol("DAUGHTERS"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF")))), list(new SubLObject[]{ makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ROOT-P"))), list(makeSymbol("CSETQ"), makeSymbol("YIELD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("YIELD")))), list(makeSymbol("CSETQ"), makeSymbol("WORDS"), list(makeSymbol("TREE-YIELD-TO-WORDS"), makeSymbol("YIELD"))), list(makeSymbol("CSETQ"), makeSymbol("ORIG-SPELLING"), list(makeSymbol("WORD-STRING"), list(makeSymbol("AREF"), makeSymbol("WORDS"), ZERO_INTEGER))), list(makeSymbol("TAGGER-NORMALIZE-INITIAL-CAPITALIZATION"), list(makeSymbol("GET-TAGGER")), makeSymbol("WORDS")), list(makeSymbol("CSETQ"), makeSymbol("NORMALIZED-SPELLING"), list(makeSymbol("WORD-STRING"), list(makeSymbol("AREF"), makeSymbol("WORDS"), ZERO_INTEGER))), list(makeSymbol("CSETQ"), makeSymbol("FIRST-WORD1"), list(makeSymbol("FIRST"), makeSymbol("YIELD"))), list(makeSymbol("SET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD1"), makeSymbol("NORMALIZED-SPELLING")) }), list(makeSymbol("CDOVECTOR"), list(makeSymbol("DAUGHTER"), makeSymbol("DAUGHTERS")), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ROOT-P"))), list(makeSymbol("CSETQ"), makeSymbol("FIRST-WORD2"), list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("YIELD"))))), list(makeSymbol("PIF"), list(EQUALP, list(makeSymbol("FIM"), makeSymbol("FIRST-WORD1"), list(QUOTE, makeSymbol("GET-STRING"))), list(makeSymbol("FIM"), makeSymbol("FIRST-WORD2"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("SET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD2"), makeSymbol("ORIG-SPELLING")), list(makeSymbol("CLET"), list(list(makeSymbol("TOKENS"), list(makeSymbol("SPLIT-STRING"), list(makeSymbol("GET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD2")))), list(makeSymbol("NEW-STRING"), list(makeSymbol("BUNGE"), list(makeSymbol("CONS"), makeSymbol("ORIG-SPELLING"), list(makeSymbol("CDR"), makeSymbol("TOKENS")))))), list(makeSymbol("SET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD2"), makeSymbol("NEW-STRING")))), list(makeSymbol("PROCESS-MODIFIERS"), list(makeSymbol("PROCESS-ABS-INDEX"), makeSymbol("SELF")))), list(RET, makeSymbol("SELF"))));

    private static final SubLSymbol PHRASE_TREE_LEXIFY_INT_METHOD = makeSymbol("PHRASE-TREE-LEXIFY-INT-METHOD");

    static private final SubLList $list220 = list(list(makeSymbol("CLET"), list(makeSymbol("RESULT")), list(makeSymbol("PCOND"), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PRETERMINAL-P"))), list(makeSymbol("CSETQ"), makeSymbol("RESULT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("RETOKENIZE-INT")), list(makeSymbol("VECTOR-ELEMENTS"), list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTERS")))), makeSymbol("LEXICON")))), list(list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("GET")), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CSETQ"), makeSymbol("RESULT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("RETOKENIZE-INT")), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("YIELD"))), makeSymbol("LEXICON")))), list(list(makeSymbol("CAND"), makeSymbol("*PHRASE-TREE-ALLOW-ADJACENT-DAUGHTERS-TO-MERGE*"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ADJACENT-DAUGHTERS-HAVE-LEXICAL-ENTRY")), makeSymbol("LEXICON"))), list(makeSymbol("CDESTRUCTURING-BIND"), list(makeSymbol("WORD-LISTS"), makeSymbol("CONSTIT-LISTS")), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ADJACENT-DAUGHTERS-HAVE-LEXICAL-ENTRY")), makeSymbol("LEXICON")), list(makeSymbol("CSETQ"), makeSymbol("RESULT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PARTIAL-RETOKENIZE-INT")), makeSymbol("WORD-LISTS"), makeSymbol("CONSTIT-LISTS"), makeSymbol("LEXICON"))))), list(T, list(makeSymbol("CSETQ"), makeSymbol("RESULT"), makeSymbol("SELF")))), list(RET, makeSymbol("RESULT"))));

    private static final SubLSymbol PRETERMINAL_P = makeSymbol("PRETERMINAL-P");

    private static final SubLSymbol RETOKENIZE_INT = makeSymbol("RETOKENIZE-INT");

    private static final SubLSymbol ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY = makeSymbol("ADJACENT-DAUGHTERS-HAVE-LEXICAL-ENTRY");

    static private final SubLList $list225 = list(makeSymbol("WORD-LISTS"), makeSymbol("CONSTIT-LISTS"));

    private static final SubLSymbol PARTIAL_RETOKENIZE_INT = makeSymbol("PARTIAL-RETOKENIZE-INT");

    private static final SubLSymbol PHRASE_TREE_RETOKENIZE_METHOD = makeSymbol("PHRASE-TREE-RETOKENIZE-METHOD");

    static private final SubLList $list228 = list(makeSymbol("WORDS"), makeSymbol("LEXICON"));

    static private final SubLList $list229 = list(list(makeSymbol("CLET"), list(list(makeSymbol("TOKENS"), list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("LEXIFY-WORDS")), makeSymbol("WORDS"))), list(makeSymbol("NEW-DAUGHTERS"), list(makeSymbol("MAKE-VECTOR"), list(makeSymbol("LENGTH"), makeSymbol("TOKENS")))), list(makeSymbol("I"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("TOKEN"), makeSymbol("TOKENS")), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-DAUGHTER"), list(makeSymbol("NEW-WORD-TREE"), list(makeSymbol("FIRST"), makeSymbol("TOKEN")), list(makeSymbol("SECOND"), makeSymbol("TOKEN")), makeSymbol("SELF"), makeSymbol("I"), NIL, makeSymbol("CONTEXT")))), list(makeSymbol("SET-AREF"), makeSymbol("NEW-DAUGHTERS"), makeSymbol("I"), makeSymbol("NEW-DAUGHTER")), list(makeSymbol("CINC"), makeSymbol("I")))), list(makeSymbol("SET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"), makeSymbol("NEW-DAUGHTERS"))), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym230$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol LEXIFY_WORDS = makeSymbol("LEXIFY-WORDS");

    private static final SubLSymbol PHRASE_TREE_RETOKENIZE_INT_METHOD = makeSymbol("PHRASE-TREE-RETOKENIZE-INT-METHOD");

    static private final SubLList $list233 = list(makeSymbol("WORD-LISTS"), makeSymbol("DAUGHTER-LISTS"), makeSymbol("LEXICON"));

    static private final SubLList $list234 = list(makeString("retokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE\n   that do not get retokenized. Those words will be re-attached to the phrase with no modification."), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-DAUGHTERS"))), list(makeSymbol("CDOLIST-MULTIPLE"), list(list(makeSymbol("WORDS"), makeSymbol("WORD-LISTS")), list(makeSymbol("DAUGHTERS"), makeSymbol("DAUGHTER-LISTS"))), list(makeSymbol("PCOND"), list(makeSymbol("WORDS"), list(makeSymbol("CLET"), list(list(makeSymbol("TOKENS"), list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("LEXIFY-WORDS")), makeSymbol("WORDS"))), list(makeSymbol("I"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("TOKEN"), makeSymbol("TOKENS")), list(makeSymbol("CPUSH"), list(makeSymbol("NEW-WORD-TREE"), list(makeSymbol("FIRST"), makeSymbol("TOKEN")), list(makeSymbol("SECOND"), makeSymbol("TOKEN")), makeSymbol("SELF"), makeSymbol("I"), NIL, list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CONTEXT")))), makeSymbol("NEW-DAUGHTERS"))))), list(T, list(makeSymbol("CPUSH-ALL"), makeSymbol("DAUGHTERS"), makeSymbol("NEW-DAUGHTERS"))))), list(makeSymbol("CLET"), list(list(makeSymbol("REPLACEMENT-DAUGHTERS"), list(makeSymbol("LIST2VECTOR"), list(makeSymbol("NREVERSE"), makeSymbol("NEW-DAUGHTERS"))))), list(makeSymbol("SET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"), makeSymbol("REPLACEMENT-DAUGHTERS")))), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol PHRASE_TREE_PARTIAL_RETOKENIZE_INT_METHOD = makeSymbol("PHRASE-TREE-PARTIAL-RETOKENIZE-INT-METHOD");

    static private final SubLList $list236 = list(list(makeSymbol("CLET"), list(list(makeSymbol("WORD-LISTS")), list(makeSymbol("DAUGHTER-LISTS")), list(makeSymbol("ALL-DAUGHTERS"), list(makeSymbol("VECTOR2LIST"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF")))), list(makeSymbol("DAUGHTER-COUNT"), list(makeSymbol("LENGTH"), makeSymbol("ALL-DAUGHTERS"))), list(makeSymbol("STRING"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CURRENT-START"), ZERO_INTEGER)), list(makeSymbol("WHILE"), list(makeSymbol("<"), makeSymbol("CURRENT-START"), makeSymbol("DAUGHTER-COUNT")), list(makeSymbol("CLET"), list(list(makeSymbol("NUM-DAUGHTERS-USED"), NIL), list(makeSymbol("START-FOR-ITERATION"), makeSymbol("CURRENT-START"))), list(makeSymbol("DO-NUMBERS"), list(new SubLObject[]{ makeSymbol("NUM"), makeKeyword("START"), makeSymbol("DAUGHTER-COUNT"), makeKeyword("END"), makeSymbol("START-FOR-ITERATION"), makeKeyword("DELTA"), MINUS_ONE_INTEGER, $DONE, makeSymbol("NUM-DAUGHTERS-USED") }), list(makeSymbol("CLET"), list(list(makeSymbol("DAUGHTERS"), list(makeSymbol("FIRST-N"), makeSymbol("NUM"), makeSymbol("ALL-DAUGHTERS"))), list(makeSymbol("YIELD"), list(makeSymbol("MAPCAN"), list(makeSymbol("FUNCTION"), makeSymbol("COPY-LIST")), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("PHRASE-TREE-YIELD")), makeSymbol("DAUGHTERS")))), list(makeSymbol("RAW-STRING"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("PHRASE-TREE-STRING")), makeSymbol("YIELD"))), list(makeSymbol("CURRENT-STRING"), list(makeSymbol("BUNGE-ACCORDING-TO-STRING"), makeSymbol("RAW-STRING"), makeSymbol("STRING")))), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("GET")), makeSymbol("CURRENT-STRING")), list(makeSymbol("CPUSH"), makeSymbol("YIELD"), makeSymbol("WORD-LISTS")), list(makeSymbol("CPUSH"), list(makeSymbol("FIRST-N"), makeSymbol("NUM"), makeSymbol("ALL-DAUGHTERS")), makeSymbol("DAUGHTER-LISTS")), list(makeSymbol("CSETQ"), makeSymbol("NUM-DAUGHTERS-USED"), makeSymbol("NUM")), list(makeSymbol("CINC"), makeSymbol("CURRENT-START"), makeSymbol("NUM-DAUGHTERS-USED"))))), list(makeSymbol("PUNLESS"), makeSymbol("NUM-DAUGHTERS-USED"), list(makeSymbol("CPUSH"), NIL, makeSymbol("WORD-LISTS")), list(makeSymbol("CPUSH"), list(makeSymbol("LIST"), list(makeSymbol("NTH"), makeSymbol("CURRENT-START"), makeSymbol("ALL-DAUGHTERS"))), makeSymbol("DAUGHTER-LISTS")), list(makeSymbol("CINC"), makeSymbol("CURRENT-START"))))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("NREVERSE"), makeSymbol("WORD-LISTS")), list(makeSymbol("NREVERSE"), makeSymbol("DAUGHTER-LISTS"))))));

    static private final SubLSymbol $sym237$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol PHRASE_TREE_YIELD = makeSymbol("PHRASE-TREE-YIELD");

    private static final SubLSymbol PHRASE_TREE_STRING = makeSymbol("PHRASE-TREE-STRING");

    private static final SubLSymbol PHRASE_TREE_ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY_METHOD = makeSymbol("PHRASE-TREE-ADJACENT-DAUGHTERS-HAVE-LEXICAL-ENTRY-METHOD");

    static private final SubLList $list242 = list(makeString("@return listp; the CycL representation of the meaning of this phrase-tree"), list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEADS")))), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), makeSymbol("HEADS")), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("CYCLIFY-INT"))), makeSymbol("CYCLS"))), list(RET, list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), makeSymbol("CYCLS"))))));

    private static final SubLSymbol PHRASE_TREE_CYCLIFY_INT_METHOD = makeSymbol("PHRASE-TREE-CYCLIFY-INT-METHOD");

    static private final SubLList $list245 = list(new SubLObject[]{ cons(makeKeyword("NP"), makeSymbol("NP-TREE")), cons(makeKeyword("NPP"), makeSymbol("NPP-TREE")), cons(makeKeyword("CNP"), makeSymbol("NOMINAL-PHRASE-TREE")), cons(makeKeyword("NX"), makeSymbol("NX-TREE")), cons(makeKeyword("PNP"), makeSymbol("PNP-TREE")), cons($WHNP, makeSymbol("WHNP-TREE")), cons(makeKeyword("PRT"), makeSymbol("PRT-TREE")), cons(makeKeyword("QP"), makeSymbol("QP-TREE")), cons(makeKeyword("SUB"), makeSymbol("NOMINAL-PHRASE-TREE")), cons(makeKeyword("OBJ"), makeSymbol("NOMINAL-PHRASE-TREE")), cons(makeKeyword("CONJP"), makeSymbol("COORDINATE-PHRASE-TREE")), cons(makeKeyword("UCP"), makeSymbol("UNLIKE-COORDINATE-PHRASE-TREE")), cons(makeKeyword("PRT"), makeSymbol("PARTICLE-PHRASE-TREE")), cons(makeKeyword("VP"), makeSymbol("VP-TREE")), cons(makeKeyword("VG"), makeSymbol("VG-TREE")), cons(makeKeyword("S1"), makeSymbol("S1-TREE")), cons(makeKeyword("S"), makeSymbol("S-TREE")), cons($SBAR, makeSymbol("SBAR-TREE")), cons(makeKeyword("SBARQ"), makeSymbol("SBARQ-TREE")), cons(makeKeyword("SQ"), makeSymbol("SQ-TREE")), cons($SINV, makeSymbol("SINV-TREE")), cons(makeKeyword("PP"), makeSymbol("PP-TREE")), cons($WHPP, makeSymbol("WHPP-TREE")), cons($ADVP, makeSymbol("ADVP-TREE")), cons(makeKeyword("WHADVP"), makeSymbol("WHADVP-TREE")), cons($ADJP, makeSymbol("ADJP-TREE")), cons(makeKeyword("WHADJP"), makeSymbol("WHADJP-TREE")), cons($FRAG, makeSymbol("FRAG-TREE")), cons(makeKeyword("X"), makeSymbol("PHRASE-TREE")) });

    static private final SubLString $str246$__P_ = makeString("#<P(");

    static private final SubLString $str247$__ = makeString("):");

    static private final SubLList $list248 = list(makeString("@return stringp; the string of this phrase-tree"), list(makeSymbol("PUNLESS"), list(makeSymbol("STRINGP"), makeSymbol("STRING")), list(makeSymbol("PIF"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), ZERO_INTEGER), list(makeSymbol("CLET"), list(list(makeSymbol("S"), list(makeSymbol("FIM"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-STRING")))), makeSymbol("DAUGHTER-STRING")), list(makeSymbol("CDOTIMES"), list(makeSymbol("I"), list(makeSymbol("-"), list(makeSymbol("DAUGHTER-COUNT"), makeSymbol("SELF")), ONE_INTEGER)), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER-STRING"), list(makeSymbol("FIM"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), list(makeSymbol("1+"), makeSymbol("I"))), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CSETQ"), makeSymbol("S"), list(makeSymbol("CCONCATENATE"), makeSymbol("S"), list(makeSymbol("FIF"), list(makeSymbol("CONTRACT-STRING?"), makeSymbol("DAUGHTER-STRING")), makeString(""), makeString(" ")), makeSymbol("DAUGHTER-STRING")))), list(makeSymbol("CSETQ"), makeSymbol("STRING"), makeSymbol("S"))), list(makeSymbol("CSETQ"), makeSymbol("STRING"), makeString("")))), list(RET, makeSymbol("STRING")));

    static private final SubLSymbol $sym249$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    static private final SubLString $str250$ = makeString("");

    static private final SubLString $$$_ = makeString(" ");

    private static final SubLSymbol PHRASE_TREE_GET_STRING_METHOD = makeSymbol("PHRASE-TREE-GET-STRING-METHOD");

    static private final SubLList $list253 = list(makeString("@return listp; the list of words of this phrase-tree"), list(makeSymbol("CLET"), list(list(makeSymbol("YIELD"), NIL)), list(makeSymbol("DO-PARSE-TREE-SUBTREES"), list(makeSymbol("DAUGHTER"), makeSymbol("SELF")), list(makeSymbol("PWHEN"), list(makeSymbol("WORD-TREE-P"), makeSymbol("DAUGHTER")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("YIELD")))), list(RET, makeSymbol("YIELD"))));

    private static final SubLSymbol PHRASE_TREE_YIELD_METHOD = makeSymbol("PHRASE-TREE-YIELD-METHOD");

    private static final SubLSymbol GET_PARSE_EXPRESSION = makeSymbol("GET-PARSE-EXPRESSION");

    static private final SubLList $list257 = list(makeString("@return listp; the parse-expression of this phrase-tree\n   @owner bertolo"), list(makeSymbol("CLET"), list(list(makeSymbol("EXPRESSION"), list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CATEGORY")))))), list(makeSymbol("DO-VECTOR"), list(makeSymbol("DAUGHTER"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("GET-PARSE-EXPRESSION"))), makeSymbol("EXPRESSION"))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("EXPRESSION")))));

    private static final SubLSymbol PHRASE_TREE_GET_PARSE_EXPRESSION_METHOD = makeSymbol("PHRASE-TREE-GET-PARSE-EXPRESSION-METHOD");

    static private final SubLList $list259 = list(makeString("@return parse-tree-p; the heads of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD-DAUGHTER"), list(QUOTE, makeSymbol("GET-HEADS")))), list(makeSymbol("CPUSH"), makeSymbol("HEAD"), makeSymbol("HEADS")))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("HEADS")))));

    private static final SubLSymbol PHRASE_TREE_GET_HEADS_METHOD = makeSymbol("PHRASE-TREE-GET-HEADS-METHOD");

    static private final SubLList $list261 = list(makeString("@return parse-tree-p; the semantic heads of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD-DAUGHTERS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD-DAUGHTER"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS")))), list(makeSymbol("CPUSH"), makeSymbol("HEAD"), makeSymbol("HEADS")))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("HEADS")))));

    private static final SubLSymbol PHRASE_TREE_GET_SEMANTIC_HEADS_METHOD = makeSymbol("PHRASE-TREE-GET-SEMANTIC-HEADS-METHOD");

    static private final SubLList $list263 = list(makeString("@param INDEX integerp\n   @return parse-tree-p; the INDEX\'th daughter of this parse tree, or nil if it doesn\'t exist"), list(makeSymbol("CHECK-TYPE"), makeSymbol("INDEX"), makeSymbol("NON-NEGATIVE-INTEGER-P")), list(makeSymbol("PWHEN"), list(makeSymbol(">="), makeSymbol("INDEX"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS"))), list(RET, NIL)), list(RET, list(makeSymbol("AREF"), makeSymbol("DAUGHTERS"), makeSymbol("INDEX"))));

    static private final SubLSymbol $sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol PHRASE_TREE_GET_DAUGHTER_METHOD = makeSymbol("PHRASE-TREE-GET-DAUGHTER-METHOD");

    static private final SubLList $list268 = list(makeSymbol("PATH"));

    static private final SubLList $list269 = list(makeString("@param PATH listp; a list of non-negative integers\n   @return parse-tree-p; the parse tree a location PATH, starting from the root\n   of this phrase-tree, or nil if there is no such parse tree"), list(makeSymbol("CHECK-TYPE"), makeSymbol("PATH"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("TREE"), makeSymbol("SELF")), makeSymbol("DTRS")), list(makeSymbol("CDOLIST"), list(makeSymbol("I"), makeSymbol("PATH")), list(makeSymbol("CSETQ"), makeSymbol("DTRS"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("TREE"))), list(makeSymbol("PWHEN"), list(makeSymbol(">="), makeSymbol("I"), list(makeSymbol("LENGTH"), makeSymbol("DTRS"))), list(RET, NIL)), list(makeSymbol("CSETQ"), makeSymbol("TREE"), list(makeSymbol("AREF"), makeSymbol("DTRS"), makeSymbol("I")))), list(RET, makeSymbol("TREE"))));

    private static final SubLSymbol PHRASE_TREE_GET_DESCENDANT_METHOD = makeSymbol("PHRASE-TREE-GET-DESCENDANT-METHOD");

    static private final SubLList $list274 = list(makeSymbol("SEARCH-STRING"), makeSymbol("&OPTIONAL"), list(makeSymbol("N"), ONE_INTEGER));

    static private final SubLList $list275 = list(makeString("@param STRING stringp; the string to search for \n   @param N positive-integer-p; the desired occurrence\n   @return parse-tree-p; the first tree whose string is equal to STRING"), list(makeSymbol("CHECK-TYPE"), makeSymbol("SEARCH-STRING"), makeSymbol("STRINGP")), list(makeSymbol("CHECK-TYPE"), makeSymbol("N"), makeSymbol("POSITIVE-INTEGER-P")), list(makeSymbol("CLET"), list(list(makeSymbol("M"), ZERO_INTEGER)), list(makeSymbol("DO-PARSE-TREE-WORD-TREES"), list(makeSymbol("WORD"), makeSymbol("SELF")), list(makeSymbol("PWHEN"), list(EQUALP, makeSymbol("SEARCH-STRING"), list(makeSymbol("FIM"), makeSymbol("WORD"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CINC"), makeSymbol("M")), list(makeSymbol("PWHEN"), list(makeSymbol("="), makeSymbol("M"), makeSymbol("N")), list(RET, makeSymbol("WORD")))))));

    private static final SubLSymbol PHRASE_TREE_SEARCH_METHOD = makeSymbol("PHRASE-TREE-SEARCH-METHOD");

    static private final SubLList $list279 = list(makeString("@return non-negative-integerp; the number of daughters of this parse tree"), list(RET, list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS"))));

    static private final SubLSymbol $sym280$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol PHRASE_TREE_DAUGHTER_COUNT_METHOD = makeSymbol("PHRASE-TREE-DAUGHTER-COUNT-METHOD");

    static private final SubLList $list282 = list(makeString("@return booleanp; t if this phrase tree has all words as daughters, nil otherwise"), list(RET, list(makeSymbol("CNOT"), list(makeSymbol("FIND-IF-NOT"), list(QUOTE, makeSymbol("WORD-TREE-P")), makeSymbol("DAUGHTERS")))));

    static private final SubLSymbol $sym283$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol PHRASE_TREE_PRETERMINAL_P_METHOD = makeSymbol("PHRASE-TREE-PRETERMINAL-P-METHOD");

    private static final SubLSymbol HYDRA_P = makeSymbol("HYDRA-P");

    static private final SubLList $list286 = list(makeString("@return booleanp; t if this phrase tree is multi-headed (a hydra), nil otherwise"), list(RET, list(makeSymbol(">"), list(makeSymbol("LENGTH"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS")))), ONE_INTEGER)));

    private static final SubLSymbol PHRASE_TREE_HYDRA_P_METHOD = makeSymbol("PHRASE-TREE-HYDRA-P-METHOD");

    static private final SubLList $list289 = list(makeSymbol("PREDICATE"), makeSymbol("&OPTIONAL"), list(makeSymbol("START"), ZERO_INTEGER), makeSymbol("END"));

    static private final SubLList $list290 = list(makeString("@param PREDICATE symbolp\n   @return listp; a list of all daughters of this phrase-tree which satisfies\n   PREDICATE, from left to right"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER"), list(makeSymbol("STOP"), list(makeSymbol("FIF"), makeSymbol("END"), makeSymbol("END"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("FOUND"), NIL)), list(makeSymbol("PWHEN"), list(makeSymbol(">"), makeSymbol("START"), makeSymbol("STOP")), list(makeSymbol("WARN"), makeString("start(~A) after stop(~A) when finding ~S in ~S~%"), makeSymbol("START"), makeSymbol("STOP"), makeSymbol("PREDICATE"), makeSymbol("SELF"))), list(makeSymbol("CDO"), list(list(makeSymbol("I"), makeSymbol("START"), list(makeSymbol("1+"), makeSymbol("I")))), list(list(makeSymbol(">="), makeSymbol("I"), makeSymbol("STOP"))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("FOUND")))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("FOUND")))));

    static private final SubLSymbol $sym291$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    static private final SubLString $str292$start__A__after_stop__A__when_fin = makeString("start(~A) after stop(~A) when finding ~S in ~S~%");

    private static final SubLSymbol PHRASE_TREE_FIND_METHOD = makeSymbol("PHRASE-TREE-FIND-METHOD");

    static private final SubLList $list295 = list(makeSymbol("PREDICATE"), makeSymbol("&OPTIONAL"), list(makeSymbol("START"), ZERO_INTEGER));

    static private final SubLList $list296 = list(makeString("@param PREDICATE symbolp\n   @return parse-tree-p; the first daughter of this phrase passing PREDICATE, starting\n   at daughter START"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER")), list(makeSymbol("CDO"), list(list(makeSymbol("I"), makeSymbol("START"), list(makeSymbol("1+"), makeSymbol("I")))), list(list(makeSymbol(">="), makeSymbol("I"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(RET, makeSymbol("DAUGHTER"), makeSymbol("I")))), list(RET, NIL)));

    static private final SubLSymbol $sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol PHRASE_TREE_FIND_FIRST_METHOD = makeSymbol("PHRASE-TREE-FIND-FIRST-METHOD");

    static private final SubLList $list300 = list(makeSymbol("PREDICATE"), makeSymbol("&OPTIONAL"), makeSymbol("END"));

    static private final SubLList $list301 = list(makeString("@param PREDICATE symbolp\n   @return parse-tree-p; the last daughter of this phrase passing PREDICATE, ending\n   at daughter END"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER")), list(makeSymbol("CDO"), list(list(makeSymbol("I"), list(makeSymbol("FIF"), makeSymbol("END"), makeSymbol("END"), list(makeSymbol("1-"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("1-"), makeSymbol("I")))), list(list(makeSymbol("MINUSP"), makeSymbol("I"))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(RET, makeSymbol("DAUGHTER")))), list(RET, NIL)));

    static private final SubLSymbol $sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol PHRASE_TREE_FIND_LAST_METHOD = makeSymbol("PHRASE-TREE-FIND-LAST-METHOD");

    private static final SubLSymbol FIND_ALL = makeSymbol("FIND-ALL");

    static private final SubLList $list305 = list(makeString("@param PREDICATE symbolp\n   @return listp; a list of all daughters of this phrase-tree whose category is\n   CATEGORY, from left to right, recursing down into daughters"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER"), list(makeSymbol("STOP"), list(makeSymbol("FIF"), makeSymbol("END"), makeSymbol("END"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("FOUND"), NIL)), list(makeSymbol("CDO"), list(list(makeSymbol("I"), makeSymbol("START"), list(makeSymbol("1+"), makeSymbol("I")))), list(list(makeSymbol("="), makeSymbol("I"), makeSymbol("STOP"))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("FOUND"))), list(makeSymbol("PWHEN"), list(makeSymbol("PHRASE-TREE-P"), makeSymbol("DAUGHTER")), list(makeSymbol("CDOLIST"), list(makeSymbol("THE-DESCENDANT"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("FIND-ALL")), makeSymbol("PREDICATE"))), list(makeSymbol("CPUSH"), makeSymbol("THE-DESCENDANT"), makeSymbol("FOUND"))))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("FOUND")))));

    static private final SubLSymbol $sym306$OUTER_CATCH_FOR_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-PHRASE-TREE-METHOD");

    private static final SubLSymbol PHRASE_TREE_FIND_ALL_METHOD = makeSymbol("PHRASE-TREE-FIND-ALL-METHOD");

    private static final SubLSymbol FIND_ALL_SUBTREES_OF_CATEGORIES = makeSymbol("FIND-ALL-SUBTREES-OF-CATEGORIES");

    static private final SubLList $list309 = list(makeSymbol("CATEGORY-LIST"), makeSymbol("&OPTIONAL"), list(makeSymbol("START"), ZERO_INTEGER), makeSymbol("END"));

    static private final SubLList $list310 = list(makeString("@param pos-list listp; a list of keyword syntactic categories\n   @return listp; a list of all subtrees of self that have any of the categories in CATEGORY-LIST"), list(makeSymbol("IGNORE"), makeSymbol("START"), makeSymbol("END")), list(makeSymbol("CLET"), list(list(makeSymbol("RESULT"))), list(makeSymbol("DO-PARSE-TREE-SUBTREES"), list(makeSymbol("SUBTREE"), makeSymbol("SELF")), list(makeSymbol("CDOLIST"), list(makeSymbol("CAT"), makeSymbol("CATEGORY-LIST")), list(makeSymbol("PWHEN"), list(EQ, list(makeSymbol("GET-PARSE-TREE-CATEGORY"), makeSymbol("SUBTREE")), makeSymbol("CAT")), list(makeSymbol("CPUSH"), makeSymbol("SUBTREE"), makeSymbol("RESULT"))))), list(RET, makeSymbol("RESULT"))));

    private static final SubLSymbol PHRASE_TREE_FIND_ALL_SUBTREES_OF_CATEGORIES_METHOD = makeSymbol("PHRASE-TREE-FIND-ALL-SUBTREES-OF-CATEGORIES-METHOD");



    static private final SubLList $list315 = list(makeString("'s"), makeString(","), makeString("."), makeString("!"), makeString("?"), makeString(";"));

    static private final SubLSymbol $sym316$STRING_ = makeSymbol("STRING=");



    static private final SubLList $list319 = list(makeString("who"), makeString("where"), makeString("when"), makeString("how"));

    static private final SubLList $list320 = list(makeString("which"), makeString("what"));

    static private final SubLString $$$what = makeString("what");

    private static final SubLSymbol NOMINAL_PHRASE_TREE = makeSymbol("NOMINAL-PHRASE-TREE");

    static private final SubLList $list323 = list(makeSymbol("NOMINAL-TREE"), makeSymbol("CYCLIFIABLE-NOUN-PHRASE"));

    static private final SubLList $list324 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DETERMINER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OVERT-QUANTIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-POSSESSOR"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SPECIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POSSESSIVE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("TEMPORAL-TREE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NAME-TREE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NUMBER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PERSON"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-GENDER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SINGULAR-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PLURAL-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DEFINITE-DESCRIPTION-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-EXISTENTIAL-NULL-DETERMINER"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), list(makeSymbol("LEXICON")), makeKeyword("PROTECTED")) });

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NOMINAL_PHRASE_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NOMINAL-PHRASE-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NOMINAL_PHRASE_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NOMINAL-PHRASE-TREE-INSTANCE");

    static private final SubLList $list328 = list(makeString("@return word-tree-p; the determiner of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-DETERMINER")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_DETERMINER_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-DETERMINER-METHOD");

    static private final SubLList $list331 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-OVERT-QUANTIFIER")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_OVERT_QUANTIFIER_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-OVERT-QUANTIFIER-METHOD");

    static private final SubLList $list334 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-QUANTIFIER")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_QUANTIFIER_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-QUANTIFIER-METHOD");

    static private final SubLList $list337 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-POSSESSOR")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_POSSESSOR_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-POSSESSOR-METHOD");

    static private final SubLList $list340 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SPECIFIER")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_SPECIFIER_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-SPECIFIER-METHOD");

    private static final SubLSymbol POSSESSIVE_P = makeSymbol("POSSESSIVE-P");

    static private final SubLList $list343 = list(makeString("@return boolean; t if this phrase is a possessive, nil otherwise"), list(RET, list(makeSymbol("POSSESSIVE-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), list(makeSymbol("1-"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_POSSESSIVE_P_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-POSSESSIVE-P-METHOD");

    static private final SubLList $list345 = list(makeString("@return booleanp; t if this tree is temporal, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(makeSymbol("PWHEN"), makeSymbol("HEAD"), list(RET, list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("TEMPORAL-TREE-P")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_TEMPORAL_TREE_P_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-TEMPORAL-TREE-P-METHOD");

    static private final SubLList $list348 = list(makeString("@return booleanp; t if this tree is a definite description, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("CAND"), makeSymbol("HEAD"), list(makeSymbol("NOMINAL-TREE-P"), makeSymbol("HEAD"))), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("DEFINITE-DESCRIPTION-P")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_DEFINITE_DESCRIPTION_P_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-DEFINITE-DESCRIPTION-P-METHOD");

    private static final SubLSymbol NAME_TREE_P = makeSymbol("NAME-TREE-P");

    static private final SubLList $list351 = list(makeString("@return booleanp; t if this tree is a name, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("CAND"), makeSymbol("HEAD"), list(makeSymbol("NAME-WORD-TREE-P"), makeSymbol("HEAD"))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_NAME_TREE_P_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-NAME-TREE-P-METHOD");

    static private final SubLList $list354 = list(makeString("@return listp; a list of genders of this np with possible gender values \n   :FEMININE, :MASCULINE and :NEUTER"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-GENDER")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_GENDER_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-GENDER-METHOD");

    static private final SubLList $list357 = list(makeString("@return listp; a list of numbers of this np with possible number values\n   :SINGULAR and :PLURAL"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-NUMBER")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_NUMBER_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-NUMBER-METHOD");

    static private final SubLList $list360 = list(makeString("@return numberp; the person of this noun; 1, 2, or 3"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-PERSON")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_PERSON_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-PERSON-METHOD");

    static private final SubLList $list363 = list(makeString("@return booleanp; t if this tree is singular, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("SINGULAR-P")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_SINGULAR_P_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-SINGULAR-P-METHOD");

    static private final SubLList $list366 = list(makeString("@return booleanp; t if this tree is plural, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("PLURAL-P")))))));

    private static final SubLSymbol NOMINAL_PHRASE_TREE_PLURAL_P_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-PLURAL-P-METHOD");

    static private final SubLList $list368 = list(makeString("@return parse-tree-p; a version of this np in which multi-word\n   lemmata have been conflated into single words and each word has\n   lexical entries added\n   @side-effects this tree\'s daughters will be destructively modified"), list(makeSymbol("CHECK-TYPE"), makeSymbol("LEXICON"), makeSymbol("ABSTRACT-LEXICON-P")), list(makeSymbol("CDOVECTOR"), list(makeSymbol("DAUGHTER"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"))), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DEFINITE-DESCRIPTION-P"))), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD")))), list(makeSymbol("AUGMENTED-LEXES"), NIL)), list(makeSymbol("PWHEN"), makeSymbol("HEAD"), list(makeSymbol("CDOLIST"), list(makeSymbol("RLE"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-RANKED-LEXES")))), list(makeSymbol("CLET"), list(list(makeSymbol("LEX"), list(makeSymbol("FWHEN"), makeSymbol("RLE"), list(makeSymbol("FIM"), list(makeSymbol("RLE-LEX"), makeSymbol("RLE")), list(QUOTE, makeSymbol("COPY")))))), list(makeSymbol("PUNLESS"), list(makeSymbol("INTRODUCES-TERM?"), makeSymbol("LEX")), list(makeSymbol("CLET"), list(list(makeSymbol("INSTANCE"), list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CONTEXT"))), list(QUOTE, makeSymbol("GET-INSTANCE")), makeSymbol("SELF"))), list(makeSymbol("SEMTRANS"), list(makeSymbol("FIM"), makeSymbol("LEX"), list(QUOTE, makeSymbol("GET")), makeKeyword("SEMTRANS")))), list(makeSymbol("FIM"), makeSymbol("LEX"), list(QUOTE, makeSymbol("SET")), makeKeyword("SEMTRANS"), list(makeSymbol("CONJOIN"), list(makeSymbol("LIST"), makeSymbol("SEMTRANS"), list(makeSymbol("BQ-LIST"), reader_make_constant_shell("equals"), $NOUN, makeSymbol("INSTANCE"))))), list(makeSymbol("RLE-SET-CONFIDENCE"), makeSymbol("RLE"), ONE_INTEGER))), list(makeSymbol("CPUSH"), makeSymbol("RLE"), makeSymbol("AUGMENTED-LEXES")))), list(makeSymbol("SET-SLOT"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("LEXES")), makeSymbol("AUGMENTED-LEXES"))))), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol GET_INSTANCE = makeSymbol("GET-INSTANCE");



    private static final SubLSymbol NOMINAL_PHRASE_TREE_LEXIFY_INT_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-LEXIFY-INT-METHOD");

    static private final SubLList $list378 = list(makeString("@return listp; a list of all head daughters of this phrase"), list(makeSymbol("PCOND"), list(list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), TWO_INTEGER), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("$")), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ONE_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("CD"))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))), list(list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), ONE_INTEGER), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("CD"))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("COORDINATE-PHRASE-P"))), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))))), list(list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), TWO_INTEGER), list(makeSymbol("VBG-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ONE_INTEGER)), list(makeSymbol("DETERMINER-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ONE_INTEGER)))), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PRETERMINAL-P"))), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-WORD-TREE-P"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER")))))), list(T, list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-WORD-TREE-P"))))), list(makeSymbol("PIF"), makeSymbol("HEAD-DAUGHTER"), list(RET, list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER"))), list(makeSymbol("PROGN"), list(makeSymbol("CSETQ"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("NOMINAL-PHRASE-TREE-P")))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER"))))))))));

    private static final SubLSymbol NOMINAL_WORD_TREE_P = makeSymbol("NOMINAL-WORD-TREE-P");

    private static final SubLSymbol NOMINAL_PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("NOMINAL-PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD");

    private static final SubLSymbol NP_TREE = makeSymbol("NP-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NP-TREE-INSTANCE");

    private static final SubLSymbol NPP_TREE = makeSymbol("NPP-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NPP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NPP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NPP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NPP-TREE-INSTANCE");

    private static final SubLSymbol NX_TREE = makeSymbol("NX-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NX_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NX-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_NX_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-NX-TREE-INSTANCE");

    private static final SubLSymbol PNP_TREE = makeSymbol("PNP-TREE");

    static private final SubLList $list395 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PNP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PNP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PNP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PNP-TREE-INSTANCE");

    static private final SubLList $list398 = list(makeString("@return listp; the head daughters of this pnp tree"), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))))));

    private static final SubLSymbol PNP_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("PNP-TREE-GET-HEAD-DAUGHTERS-METHOD");

    private static final SubLSymbol WHNP_TREE = makeSymbol("WHNP-TREE");

    static private final SubLList $list401 = list(makeSymbol("WH-TREE"));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHNP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHNP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHNP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHNP-TREE-INSTANCE");

    private static final SubLSymbol VERBAL_PHRASE_TREE = makeSymbol("VERBAL-PHRASE-TREE");

    static private final SubLList $list405 = list(makeSymbol("VERBAL-TREE"), makeSymbol("CYCLIFIABLE-VERB-PHRASE"));

    static private final SubLList $list406 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NUMBER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PERSON"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-TENSE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PREDICATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SUBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-VERBAL-COMPLEMENT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COPULA-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DUMMY-TO-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PASSIVE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FINITE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("VP-COORDINATE-PHRASE-P"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PARTITION-SEMANTIC-COMPLEMENTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT-VP"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT-VP-COORDINATION"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INVERTED-P"), NIL, makeKeyword("PROTECTED")) });

    static private final SubLList $list408 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT")))), list(makeSymbol("SUBJ-CYCLS"), list(makeSymbol("FWHEN"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SUBJECT"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))), list(makeSymbol("CONJUNCTION"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CONJUNCTION")))), list(makeSymbol("VPS"), list(makeSymbol("FIM"), makeSymbol("CONJUNCTION"), list(QUOTE, makeSymbol("GET-COMPLEMENTS")))), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("VP"), makeSymbol("VPS")), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("VP"), list(QUOTE, makeSymbol("CYCLIFY-INT-VP"))), makeSymbol("CYCLS"))), list(RET, list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), list(makeSymbol("CONS"), makeSymbol("SUBJ-CYCLS"), makeSymbol("CYCLS")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_CYCLIFY_INT_VP_COORDINATION_METHOD = makeSymbol("VERBAL-PHRASE-TREE-CYCLIFY-INT-VP-COORDINATION-METHOD");

    static private final SubLList $list412 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS")))), list(makeSymbol("ALL-CONJUNCTION-CYCLS"), NIL), list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT")))), list(makeSymbol("COMPLEMENTS"), list(makeSymbol("FWHEN"), makeSymbol("HEADS"), list(makeSymbol("FIM"), list(makeSymbol("FIRST"), makeSymbol("HEADS")), list(QUOTE, makeSymbol("GET-SEMANTIC-COMPLEMENTS"))))), list(makeSymbol("COMP-CYCLS"), NIL), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("CSETQ"), makeSymbol("COMPLEMENTS"), list(makeSymbol("DELETE-CYCLIFIABLE"), makeSymbol("SUBJECT"), makeSymbol("COMPLEMENTS"))), list(makeSymbol("WITHOUT-RECURSIVE-CYCLIFICATION"), list(makeSymbol("CLET"), list(list(makeSymbol("*CYCLIFICATION-IN-PROGRESS*"), list(makeSymbol("APPEND"), makeSymbol("HEADS"), makeSymbol("*CYCLIFICATION-IN-PROGRESS*")))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), makeSymbol("HEADS")), list(makeSymbol("CLET"), list(list(makeSymbol("ALL-HEAD-CYCLS"), NIL), list(makeSymbol("MOD-CYCLS"), list(makeSymbol("GET-MOD-CYCLS"), makeSymbol("HEAD"))), list(makeSymbol("RENAMINGS"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-KEYWORD-RENAMINGS")), list(makeSymbol("REQUIRED-KEYWORDS"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-RANKED-LEXES"))))))), list(makeSymbol("CDOLIST"), list(makeSymbol("CONJUNCTION"), makeSymbol("RENAMINGS")), list(makeSymbol("CLET"), list(list(makeSymbol("ALL-RLE-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("RLE"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-RANKED-LEXES")))), list(makeSymbol("CLET"), list(list(makeSymbol("COMPOSITE-VERBAL-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("RENAMING"), makeSymbol("CONJUNCTION")), list(makeSymbol("CLET"), list(list(makeSymbol("RENAMED0"), list(makeSymbol("APPLY-LEX-RENAMING"), makeSymbol("HEAD"), makeSymbol("RLE"), makeSymbol("RENAMING"))), list(makeSymbol("RENAMED"), list(makeSymbol("FWHEN"), makeSymbol("RENAMED0"), list(makeSymbol("LIST"), list(makeSymbol("QUANTIFY-IMPLICIT-SUBJECT"), makeSymbol("RENAMED0")))))), list(makeSymbol("ALIST-CPUSHNEW"), makeSymbol("COMPOSITE-VERBAL-CYCLS"), list(makeSymbol("RENAMING-KEY"), makeSymbol("RENAMING")), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), list(makeSymbol("CONS"), makeSymbol("RENAMED"), makeSymbol("MOD-CYCLS"))))))), list(makeSymbol("CLET"), list(list(makeSymbol("SENSE-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("VALUE"), list(makeSymbol("ALIST-VALUES"), makeSymbol("COMPOSITE-VERBAL-CYCLS"))), list(makeSymbol("CPUSH"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("VALUE"))), makeSymbol("SENSE-CYCLS"))), list(makeSymbol("CPUSH"), makeSymbol("SENSE-CYCLS"), makeSymbol("ALL-RLE-CYCLS"))))), list(makeSymbol("CPUSH"), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-RLE-CYCLS")), makeSymbol("ALL-HEAD-CYCLS")))), list(makeSymbol("CPUSH"), makeSymbol("ALL-HEAD-CYCLS"), makeSymbol("ALL-CONJUNCTION-CYCLS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("COMPLEMENT"), makeSymbol("COMPLEMENTS")), list(makeSymbol("CLET"), list(list(makeSymbol("COMP-CYCL"), list(makeSymbol("FIM"), makeSymbol("COMPLEMENT"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))), list(makeSymbol("PWHEN"), makeSymbol("COMP-CYCL"), list(makeSymbol("CPUSH"), makeSymbol("COMP-CYCL"), makeSymbol("COMP-CYCLS"))))), list(makeSymbol("CDOLIST"), list(makeSymbol("ALL-HEAD-CYCLS"), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-CONJUNCTION-CYCLS"))), list(makeSymbol("CLET"), list(list(makeSymbol("CYCLS0"), NIL), list(makeSymbol("ALL-SUBJECTS-WITH-OBJECTS-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("ITEM"), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-HEAD-CYCLS"))), list(makeSymbol("CLET"), list(list(makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("ITEM2"), list(makeSymbol("CROSS-PRODUCTS"), makeSymbol("ITEM"))), list(makeSymbol("CSETQ"), makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT"), list(makeSymbol("APPEND"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ITEM2"))), makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT")))), list(makeSymbol("CPUSH"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), list(makeSymbol("CONS"), makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT"), makeSymbol("COMP-CYCLS")))), makeSymbol("ALL-SUBJECTS-WITH-OBJECTS-CYCLS")))), list(makeSymbol("CSETQ"), makeSymbol("CYCLS0"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-SUBJECTS-WITH-OBJECTS-CYCLS")))), list(makeSymbol("CSETQ"), makeSymbol("CYCLS"), list(makeSymbol("APPEND"), makeSymbol("CYCLS0"), makeSymbol("CYCLS"))))))), list(RET, makeSymbol("CYCLS"))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_CYCLIFY_INT_VP_METHOD = makeSymbol("VERBAL-PHRASE-TREE-CYCLIFY-INT-VP-METHOD");

    static private final SubLList $list416 = list(list(makeSymbol("WITHOUT-DUPLICATE-CYCLIFICATION"), list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("PCOND"), list(list(makeSymbol("CYCLIFIABLE-SENTENTIAL-PHRASE-P"), makeSymbol("SELF")), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DTR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTER"))))), list(makeSymbol("PWHEN"), makeSymbol("HEAD-DTR"), list(RET, list(makeSymbol("FIM"), makeSymbol("HEAD-DTR"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))))), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("VP-COORDINATE-PHRASE-P"))), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CYCLIFY-INT-VP-COORDINATION"))))), list(T, list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT")))), list(makeSymbol("SUBJ-CYCLS"), list(makeSymbol("FWHEN"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SUBJECT"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))), list(makeSymbol("VP-CYCLS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CYCLIFY-INT-VP")))), list(makeSymbol("SUBJ-VP-CYCLS"), list(makeSymbol("FWHEN"), makeSymbol("SUBJ-CYCLS"), list(makeSymbol("LIST"), makeSymbol("SUBJ-CYCLS"))))), list(makeSymbol("PWHEN"), makeSymbol("VP-CYCLS"), list(makeSymbol("CPUSH"), makeSymbol("VP-CYCLS"), makeSymbol("SUBJ-VP-CYCLS"))), list(RET, list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), makeSymbol("SUBJ-VP-CYCLS")))))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_CYCLIFY_INT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-CYCLIFY-INT-METHOD");

    static private final SubLList $list419 = list(list(makeSymbol("CLET"), list(list(makeSymbol("CONJUNCTION"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CONJUNCTION"))))), list(RET, list(makeSymbol("CAND"), makeSymbol("CONJUNCTION"), list(makeSymbol("CNOT"), list(makeSymbol("MEMBER-IF-NOT"), list(QUOTE, makeSymbol("CYCLIFIABLE-VERB-PHRASE-P")), list(makeSymbol("FIM"), makeSymbol("CONJUNCTION"), list(QUOTE, makeSymbol("GET-COMPLEMENTS")))))))));

    private static final SubLSymbol CYCLIFIABLE_VERB_PHRASE_P = makeSymbol("CYCLIFIABLE-VERB-PHRASE-P");

    private static final SubLSymbol VERBAL_PHRASE_TREE_VP_COORDINATE_PHRASE_P_METHOD = makeSymbol("VERBAL-PHRASE-TREE-VP-COORDINATE-PHRASE-P-METHOD");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_VERBAL_PHRASE_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-VERBAL-PHRASE-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_VERBAL_PHRASE_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-VERBAL-PHRASE-TREE-INSTANCE");

    static private final SubLList $list425 = list(makeString("@return word-tree-p; the predicate of this sentence"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD")))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_PREDICATE_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-PREDICATE-METHOD");

    static private final SubLList $list427 = list(makeString("@return listp; a list of numbers of this vp with possible number values\n   :SINGULAR and :PLURAL"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-NUMBER")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_NUMBER_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-NUMBER-METHOD");

    static private final SubLList $list429 = list(makeString("@return numberp; the person of this vp; 1, 2, or 3"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-PERSON")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_PERSON_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-PERSON-METHOD");

    static private final SubLList $list432 = list(makeString("@return keywordp; the tense of this vp; either :PRESENT, :PAST, :FUTURE,\n   or :INFINITIVE"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-TENSE")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_TENSE_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-TENSE-METHOD");

    static private final SubLList $list434 = list(makeString("@return phrase-tree-p; the subject of this verbal tree"), list(makeSymbol("PWHEN"), makeSymbol("MOTHER"), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")), list(makeSymbol("1-"), makeSymbol("IDX"))))), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("CNOT"), makeSymbol("SUBJECT")), list(makeSymbol("VERBAL-TREE-P"), makeSymbol("MOTHER"))), list(makeSymbol("CSETQ"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-SUBJECT"))))), list(RET, makeSymbol("SUBJECT")))));

    static private final SubLSymbol $sym435$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-VERBAL-PHRASE-TREE-METHOD");

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_SUBJECT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-SUBJECT-METHOD");

    static private final SubLList $list438 = list(makeString("@return listp; a list of all objects of this tree, in surface order"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-OBJECTS")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_OBJECTS_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-OBJECTS-METHOD");

    static private final SubLList $list441 = list(makeString("@return phrase-tree-p; the direct object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-DIRECT-OBJECT")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_DIRECT_OBJECT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-DIRECT-OBJECT-METHOD");

    static private final SubLList $list444 = list(makeString("@return phrase-tree-p; the indirect object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-INDIRECT-OBJECT")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_INDIRECT_OBJECT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-INDIRECT-OBJECT-METHOD");

    static private final SubLList $list447 = list(makeString("@return phrase-tree-p; the semantic direct object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-DIRECT-OBJECT")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_SEMANTIC_DIRECT_OBJECT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-SEMANTIC-DIRECT-OBJECT-METHOD");

    static private final SubLList $list450 = list(makeString("@return phrase-tree-p; the semantic indirect object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-INDIRECT-OBJECT")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_SEMANTIC_INDIRECT_OBJECT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-SEMANTIC-INDIRECT-OBJECT-METHOD");

    static private final SubLList $list453 = list(makeString("@return phrase-tree-p; the semantic subject of this verbal tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-SUBJECT")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_SEMANTIC_SUBJECT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-SEMANTIC-SUBJECT-METHOD");

    static private final SubLList $list456 = list(makeString("@return listp; a list of the semantic subject of this verbal tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-OBJECTS")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_SEMANTIC_OBJECTS_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-SEMANTIC-OBJECTS-METHOD");

    static private final SubLList $list458 = list(makeString("@return listp; a list of all verbal complements of this verbal tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-VERBAL-COMPLEMENT")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_VERBAL_COMPLEMENT_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-VERBAL-COMPLEMENT-METHOD");

    static private final SubLList $list461 = list(makeString("@return booleanp; t if this phrase-tree is a copula construction, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("AUX-VERB-WORD-P"), makeSymbol("HEAD")), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("COPULA-P")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_COPULA_P_METHOD = makeSymbol("VERBAL-PHRASE-TREE-COPULA-P-METHOD");

    private static final SubLSymbol DUMMY_TO_P = makeSymbol("DUMMY-TO-P");

    static private final SubLList $list464 = list(makeString("@return booleanp; t if this phrase-tree is a dummy 'to' construction, nil otherwise"), list(RET, list(makeSymbol("CAND"), list(makeSymbol("VERBAL-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD")))), list(EQUAL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-STRING"))), makeString("to")))));

    static private final SubLString $$$to = makeString("to");

    private static final SubLSymbol VERBAL_PHRASE_TREE_DUMMY_TO_P_METHOD = makeSymbol("VERBAL-PHRASE-TREE-DUMMY-TO-P-METHOD");

    static private final SubLList $list468 = list(makeString("@return booleanp; t if this phrase-tree is a passive construction, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("PASSIVE-P")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_PASSIVE_P_METHOD = makeSymbol("VERBAL-PHRASE-TREE-PASSIVE-P-METHOD");

    private static final SubLSymbol FINITE_P = makeSymbol("FINITE-P");

    static private final SubLList $list471 = list(makeString("@return booleanp; t if this phrase tree is finite, nil otherwise"), list(RET, list(makeSymbol("FINITE-VERBAL-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_FINITE_P_METHOD = makeSymbol("VERBAL-PHRASE-TREE-FINITE-P-METHOD");

    static private final SubLList $list474 = list(list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("PARTITION-SEMANTIC-COMPLEMENTS")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_PARTITION_SEMANTIC_COMPLEMENTS_METHOD = makeSymbol("VERBAL-PHRASE-TREE-PARTITION-SEMANTIC-COMPLEMENTS-METHOD");

    static private final SubLList $list477 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(makeSymbol("PIF"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVERTED-P"))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("VERBAL-PHRASE-TREE-P"), makeSymbol("MOTHER")), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTOR-OBJECTS"))))), list(RET, list(makeSymbol("APPEND"), list(makeSymbol("FWHEN"), list(makeSymbol("VERBAL-PHRASE-TREE-P"), makeSymbol("MOTHER")), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTOR-OBJECTS")))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")))))));

    static private final SubLSymbol $sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-VERBAL-PHRASE-TREE-METHOD");

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_ANCESTOR_OBJECTS_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-ANCESTOR-OBJECTS-METHOD");

    static private final SubLList $list481 = list(makeString("@return listp; the head daughters of this verbal phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTERS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("VERBAL-WORD-TREE-P"))))), list(RET, list(makeSymbol("FIF"), makeSymbol("HEAD-DAUGHTERS"), makeSymbol("HEAD-DAUGHTERS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("VERBAL-PHRASE-TREE-P")))))));

    private static final SubLSymbol VERBAL_WORD_TREE_P = makeSymbol("VERBAL-WORD-TREE-P");

    private static final SubLSymbol VERBAL_PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("VERBAL-PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD");

    static private final SubLList $list485 = list(makeString("@return booleanp; t if this verbal tree is inverted, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("INVERTED-P")))))));

    private static final SubLSymbol VERBAL_PHRASE_TREE_INVERTED_P_METHOD = makeSymbol("VERBAL-PHRASE-TREE-INVERTED-P-METHOD");

    private static final SubLSymbol SENTENTIAL_TREE = makeSymbol("SENTENTIAL-TREE");

    static private final SubLList $list488 = list(makeSymbol("CYCLIFIABLE-SENTENTIAL-PHRASE"));

    static private final SubLList $list489 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SUBJECT"), NIL, makeKeyword("PUBLIC")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SENTENTIAL_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SENTENTIAL-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SENTENTIAL_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SENTENTIAL-TREE-INSTANCE");

    static private final SubLList $list492 = list(makeString("@return np-tree-p; the subject of this sentence"), list(makeSymbol("PIF"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVERTED-P"))), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD")))), list(makeSymbol("HEAD-MOTHER"), list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-MOTHER")))))), list(makeSymbol("PWHEN"), makeSymbol("HEAD-MOTHER"), list(RET, list(makeSymbol("FIM"), makeSymbol("HEAD-MOTHER"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")), list(makeSymbol("1+"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-INDEX")))))))), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))))), list(makeSymbol("PIF"), makeSymbol("SUBJECT"), list(RET, makeSymbol("SUBJECT")), list(makeSymbol("CDOLIST"), list(makeSymbol("ANCESTOR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-ANCESTORS")))), list(makeSymbol("PWHEN"), list(makeSymbol("VERBAL-TREE-P"), makeSymbol("ANCESTOR")), list(RET, list(makeSymbol("FIM"), makeSymbol("ANCESTOR"), list(QUOTE, makeSymbol("GET-SUBJECT"))))))))));

    private static final SubLSymbol SENTENTIAL_TREE_GET_SUBJECT_METHOD = makeSymbol("SENTENTIAL-TREE-GET-SUBJECT-METHOD");

    static private final SubLList $list494 = list(makeString("@return listp; the constituents this sentence modifies"), list(makeSymbol("PWHEN"), list(makeSymbol("RELATIVE-CLAUSE-P"), makeSymbol("SELF")), list(makeSymbol("CLET"), list(list(makeSymbol("FIRST-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)), list(makeSymbol("FIRST-HEAD"), list(makeSymbol("FWHEN"), list(makeSymbol("WORD-TREE-P"), makeSymbol("FIRST-DAUGHTER")), list(makeSymbol("FIM"), makeSymbol("FIRST-DAUGHTER"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(makeSymbol("MODIFIED"), list(makeSymbol("FWHEN"), list(makeSymbol("WP-TREE-P"), makeSymbol("FIRST-HEAD")), list(makeSymbol("FIM"), makeSymbol("FIRST-HEAD"), list(QUOTE, makeSymbol("GET-ANTECEDENT"))))), list(makeSymbol("MOD-HEAD"), list(makeSymbol("FWHEN"), list(makeSymbol("WORD-TREE-P"), makeSymbol("MODIFIED")), list(makeSymbol("FIM"), makeSymbol("MODIFIED"), list(QUOTE, makeSymbol("GET-HEAD")))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("MOD-HEAD"), list(makeSymbol("LIST"), makeSymbol("MOD-HEAD")))))));

    private static final SubLSymbol SENTENTIAL_TREE_GET_MODIFIEDS_METHOD = makeSymbol("SENTENTIAL-TREE-GET-MODIFIEDS-METHOD");

    private static final SubLSymbol VP_TREE = makeSymbol("VP-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_VP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-VP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_VP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-VP-TREE-INSTANCE");

    private static final SubLSymbol VG_TREE = makeSymbol("VG-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_VG_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-VG-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_VG_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-VG-TREE-INSTANCE");

    private static final SubLSymbol S1_TREE = makeSymbol("S1-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_S1_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-S1-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_S1_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-S1-TREE-INSTANCE");

    private static final SubLSymbol S_TREE = makeSymbol("S-TREE");

    static private final SubLList $list507 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PUBLIC")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_S_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-S-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_S_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-S-TREE-INSTANCE");

    static private final SubLList $list510 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT"))))), list(makeSymbol("PCOND"), list(list(makeSymbol("CAND"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVERTED-P")))), list(RET, list(makeSymbol("LIST"), makeSymbol("SUBJECT")))), list(list(makeSymbol("CAND"), makeSymbol("MOTHER"), list(makeSymbol("RELATIVE-CLAUSE-P"), makeSymbol("MOTHER"))), list(RET, list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-MODIFIEDS"))))))));

    static private final SubLSymbol $sym511$OUTER_CATCH_FOR_S_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-S-TREE-METHOD");

    private static final SubLSymbol S_TREE_GET_ANCESTOR_OBJECTS_METHOD = makeSymbol("S-TREE-GET-ANCESTOR-OBJECTS-METHOD");

    private static final SubLSymbol SBAR_TREE = makeSymbol("SBAR-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SBAR_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SBAR-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SBAR_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SBAR-TREE-INSTANCE");

    private static final SubLSymbol SBARQ_TREE = makeSymbol("SBARQ-TREE");

    static private final SubLList $list517 = list(makeSymbol("QUESTION-TREE"));

    static private final SubLList $list518 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PROTECTED")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SBARQ_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SBARQ-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SBARQ_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SBARQ-TREE-INSTANCE");

    static private final SubLList $list521 = list(makeString("@return listp; the head daughters of this sbarq tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("VERBAL-TREE-P"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER"))))));

    private static final SubLSymbol SBARQ_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("SBARQ-TREE-GET-HEAD-DAUGHTERS-METHOD");

    static private final SubLList $list524 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(RET, list(makeSymbol("DELETE"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT"))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))), list(QUOTE, EQ))));

    private static final SubLSymbol SBARQ_TREE_GET_ANCESTOR_OBJECTS_METHOD = makeSymbol("SBARQ-TREE-GET-ANCESTOR-OBJECTS-METHOD");

    private static final SubLSymbol SQ_TREE = makeSymbol("SQ-TREE");

    static private final SubLList $list527 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PROTECTED")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SQ_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SQ-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SQ_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SQ-TREE-INSTANCE");

    static private final SubLList $list530 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(RET, list(makeSymbol("APPEND"), list(makeSymbol("CDR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")))), list(makeSymbol("FWHEN"), list(makeSymbol("VERBAL-PHRASE-TREE-P"), makeSymbol("MOTHER")), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTOR-OBJECTS")))))));

    static private final SubLSymbol $sym531$OUTER_CATCH_FOR_SQ_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SQ-TREE-METHOD");

    private static final SubLSymbol SQ_TREE_GET_ANCESTOR_OBJECTS_METHOD = makeSymbol("SQ-TREE-GET-ANCESTOR-OBJECTS-METHOD");

    private static final SubLSymbol SINV_TREE = makeSymbol("SINV-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SINV_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SINV-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SINV_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SINV-TREE-INSTANCE");

    private static final SubLSymbol PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE = makeSymbol("PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE");

    static private final SubLList $list537 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OBLIQUE-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("STRANDED-P"), NIL, makeKeyword("PUBLIC")));

    static private final SubLSymbol $sym538$SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-CLASS");

    static private final SubLSymbol $sym539$SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-INSTANCE");

    static private final SubLList $list540 = list(makeString("@return listp; the head daughters of this prepositional-or-particle-phrase-tree"), list(makeSymbol("PWHEN"), list(makeSymbol("VBG-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))), list(RET, list(makeSymbol("APPEND"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("PREPOSITIONAL-TREE-P"))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("PARTICLE-TREE-P"))))));

    private static final SubLSymbol PREPOSITIONAL_TREE_P = makeSymbol("PREPOSITIONAL-TREE-P");

    private static final SubLSymbol PARTICLE_TREE_P = makeSymbol("PARTICLE-TREE-P");

    private static final SubLSymbol PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD");

    static private final SubLList $list545 = list(makeString("@return nominal-phrase-tree; the complement np of the prepositional head of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-OBLIQUE-OBJECT")))))));

    private static final SubLSymbol PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE_GET_OBLIQUE_OBJECT_METHOD = makeSymbol("PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-GET-OBLIQUE-OBJECT-METHOD");

    private static final SubLSymbol STRANDED_P = makeSymbol("STRANDED-P");

    static private final SubLList $list548 = list(makeString("@return booleanp; non-nil if the preposition or particle of this phrase is \n   stranded, nil otherwise"), list(RET, list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), ONE_INTEGER), list(makeSymbol("PREPOSITIONAL-OR-PARTICLE-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))));

    private static final SubLSymbol PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE_STRANDED_P_METHOD = makeSymbol("PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-STRANDED-P-METHOD");

    private static final SubLSymbol PREPOSITIONAL_PHRASE_TREE = makeSymbol("PREPOSITIONAL-PHRASE-TREE");

    static private final SubLList $list551 = list(makeSymbol("PREPOSITIONAL-TREE"));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_PHRASE_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-PHRASE-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_PHRASE_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-PHRASE-TREE-INSTANCE");

    private static final SubLSymbol PP_TREE = makeSymbol("PP-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PP-TREE-INSTANCE");

    private static final SubLSymbol WHPP_TREE = makeSymbol("WHPP-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHPP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHPP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHPP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHPP-TREE-INSTANCE");

    private static final SubLSymbol PRT_TREE = makeSymbol("PRT-TREE");

    static private final SubLList $list561 = list(makeSymbol("PARTICLE-TREE"));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PRT_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PRT-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_PRT_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-PRT-TREE-INSTANCE");

    static private final SubLList $list564 = list(makeString("@return listp; the head daughters of this prepositional-or-particle-phrase-tree"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("PARTICLE-TREE-P")))));

    private static final SubLSymbol PRT_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("PRT-TREE-GET-HEAD-DAUGHTERS-METHOD");

    private static final SubLSymbol ADJP_TREE = makeSymbol("ADJP-TREE");

    static private final SubLList $list567 = list(makeSymbol("ADJECTIVAL-TREE"));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_ADJP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-ADJP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_ADJP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-ADJP-TREE-INSTANCE");

    static private final SubLList $list570 = list(makeString("@return listp; the head daughters of this adjp-tree"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("ADJECTIVAL-TREE-P")))));

    private static final SubLSymbol ADJECTIVAL_TREE_P = makeSymbol("ADJECTIVAL-TREE-P");

    private static final SubLSymbol ADJP_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("ADJP-TREE-GET-HEAD-DAUGHTERS-METHOD");

    static private final SubLList $list573 = list(makeString("@return listp; the complement of this adjective"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-MODIFIEDS")))))));

    private static final SubLSymbol ADJP_TREE_GET_MODIFIEDS_METHOD = makeSymbol("ADJP-TREE-GET-MODIFIEDS-METHOD");

    private static final SubLSymbol WHADJP_TREE = makeSymbol("WHADJP-TREE");

    static private final SubLList $list576 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), NIL, makeKeyword("PROTECTED")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHADJP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHADJP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHADJP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHADJP-TREE-INSTANCE");

    static private final SubLList $list579 = list(makeString("@return parse-tree-p; a version of this np in which multi-word\n   lemmata have been conflated into single words and each word has\n   lexical entries added\n   @side-effects this tree\'s daughters will be destructively modified"), list(makeSymbol("CHECK-TYPE"), makeSymbol("LEXICON"), makeSymbol("ABSTRACT-LEXICON-P")), list(makeSymbol("CDOVECTOR"), list(makeSymbol("DAUGHTER"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"))), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol WHADJP_TREE_LEXIFY_INT_METHOD = makeSymbol("WHADJP-TREE-LEXIFY-INT-METHOD");

    private static final SubLSymbol ADVP_TREE = makeSymbol("ADVP-TREE");

    static private final SubLList $list582 = list(makeSymbol("ADVERBIAL-TREE"));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_ADVP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-ADVP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_ADVP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-ADVP-TREE-INSTANCE");

    static private final SubLList $list585 = list(makeString("@return listp; a list of head daughters of this adverbial tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DTR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("ADVERBIAL-TREE-P"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DTR"), list(makeSymbol("LIST"), makeSymbol("HEAD-DTR"))))));

    private static final SubLSymbol ADVERBIAL_TREE_P = makeSymbol("ADVERBIAL-TREE-P");

    private static final SubLSymbol ADVP_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("ADVP-TREE-GET-HEAD-DAUGHTERS-METHOD");

    private static final SubLSymbol WHADVP_TREE = makeSymbol("WHADVP-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHADVP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHADVP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WHADVP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WHADVP-TREE-INSTANCE");

    private static final SubLSymbol FRAG_TREE = makeSymbol("FRAG-TREE");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_FRAG_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-FRAG-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_FRAG_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-FRAG-TREE-INSTANCE");

    private static final SubLSymbol QP_TREE = makeSymbol("QP-TREE");

    static private final SubLList $list595 = list(makeSymbol("NUMERICAL-TREE"), makeSymbol("QUANTIFIER-TREE"));

    static private final SubLList $list596 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIED"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("PROTECTED")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_QP_TREE_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-QP-TREE-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_QP_TREE_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-QP-TREE-INSTANCE");

    static private final SubLList $list599 = list(makeString("@return listp; a list of all head daughters of this phrase"), list(RET, list(makeSymbol("FIND"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CARDINAL-WORD-TREE-P")))));

    private static final SubLSymbol CARDINAL_WORD_TREE_P = makeSymbol("CARDINAL-WORD-TREE-P");

    private static final SubLSymbol QP_TREE_GET_HEAD_DAUGHTERS_METHOD = makeSymbol("QP-TREE-GET-HEAD-DAUGHTERS-METHOD");

    static private final SubLList $list603 = list(makeString("@return word-tree; the word that restricts the variable that this determiner quantifies"), list(RET, list(makeSymbol("FWHEN"), makeSymbol("MOTHER"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-HEAD"))))));

    static private final SubLSymbol $sym604$OUTER_CATCH_FOR_QP_TREE_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-QP-TREE-METHOD");

    private static final SubLSymbol QP_TREE_GET_QUANTIFIED_METHOD = makeSymbol("QP-TREE-GET-QUANTIFIED-METHOD");

    static private final SubLList $list606 = list(makeString("@return listp; the CycL representation of the meaning of this quantifier phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("CYCLS"), NIL), list(makeSymbol("NUMBER"), list(makeSymbol("STRING-TO-INTERVAL"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-STRING"))))), list(makeSymbol("QUANTIFIED"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-QUANTIFIED")))), list(makeSymbol("RESTRICTIONS"), list(makeSymbol("FWHEN"), makeSymbol("QUANTIFIED"), list(makeSymbol("FIM"), makeSymbol("QUANTIFIED"), list(QUOTE, makeSymbol("CYCLIFY-NUCLEUS"))))), list(makeSymbol("VARIABLE"), list(makeSymbol("FWHEN"), makeSymbol("QUANTIFIED"), list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("QUANTIFIED"), list(QUOTE, makeSymbol("GET-REFS"))))))), list(makeSymbol("PWHEN"), makeSymbol("NUMBER"), list(makeSymbol("CDOLIST"), list(makeSymbol("RESTRICTION"), makeSymbol("RESTRICTIONS")), list(makeSymbol("CPUSH"), list(makeSymbol("NEW-CYCLIFICATION"), list(makeSymbol("BQ-LIST"), reader_make_constant_shell("thereExistAtLeast"), makeSymbol("NUMBER"), makeSymbol("VARIABLE"), list(makeSymbol("BQ-LIST*"), reader_make_constant_shell("and"), makeSymbol("RESTRICTION"), list(QUOTE, list(makeKeyword("SCOPE"))))), makeSymbol("SELF"), ONE_INTEGER), makeSymbol("CYCLS")))), list(RET, makeSymbol("CYCLS"))));





    static private final SubLList $list610 = list(makeKeyword("SCOPE"));

    private static final SubLSymbol QP_TREE_CYCLIFY_INT_METHOD = makeSymbol("QP-TREE-CYCLIFY-INT-METHOD");

    // Definitions
    public static final SubLObject nominal_tree_p_alt(SubLObject nominal_tree) {
        return interfaces.subloop_instanceof_interface(nominal_tree, NOMINAL_TREE);
    }

    // Definitions
    public static SubLObject nominal_tree_p(final SubLObject nominal_tree) {
        return interfaces.subloop_instanceof_interface(nominal_tree, NOMINAL_TREE);
    }

    public static final SubLObject verbal_tree_p_alt(SubLObject verbal_tree) {
        return interfaces.subloop_instanceof_interface(verbal_tree, VERBAL_TREE);
    }

    public static SubLObject verbal_tree_p(final SubLObject verbal_tree) {
        return interfaces.subloop_instanceof_interface(verbal_tree, VERBAL_TREE);
    }

    public static final SubLObject adverbial_tree_p_alt(SubLObject adverbial_tree) {
        return interfaces.subloop_instanceof_interface(adverbial_tree, ADVERBIAL_TREE);
    }

    public static SubLObject adverbial_tree_p(final SubLObject adverbial_tree) {
        return interfaces.subloop_instanceof_interface(adverbial_tree, ADVERBIAL_TREE);
    }

    public static final SubLObject prepositional_tree_p_alt(SubLObject prepositional_tree) {
        return interfaces.subloop_instanceof_interface(prepositional_tree, PREPOSITIONAL_TREE);
    }

    public static SubLObject prepositional_tree_p(final SubLObject prepositional_tree) {
        return interfaces.subloop_instanceof_interface(prepositional_tree, PREPOSITIONAL_TREE);
    }

    public static final SubLObject particle_tree_p_alt(SubLObject particle_tree) {
        return interfaces.subloop_instanceof_interface(particle_tree, PARTICLE_TREE);
    }

    public static SubLObject particle_tree_p(final SubLObject particle_tree) {
        return interfaces.subloop_instanceof_interface(particle_tree, PARTICLE_TREE);
    }

    public static final SubLObject adjectival_tree_p_alt(SubLObject adjectival_tree) {
        return interfaces.subloop_instanceof_interface(adjectival_tree, ADJECTIVAL_TREE);
    }

    public static SubLObject adjectival_tree_p(final SubLObject adjectival_tree) {
        return interfaces.subloop_instanceof_interface(adjectival_tree, ADJECTIVAL_TREE);
    }

    public static final SubLObject question_tree_p_alt(SubLObject question_tree) {
        return interfaces.subloop_instanceof_interface(question_tree, QUESTION_TREE);
    }

    public static SubLObject question_tree_p(final SubLObject question_tree) {
        return interfaces.subloop_instanceof_interface(question_tree, QUESTION_TREE);
    }

    public static final SubLObject wh_tree_p_alt(SubLObject wh_tree) {
        return interfaces.subloop_instanceof_interface(wh_tree, WH_TREE);
    }

    public static SubLObject wh_tree_p(final SubLObject wh_tree) {
        return interfaces.subloop_instanceof_interface(wh_tree, WH_TREE);
    }

    public static final SubLObject numerical_tree_p_alt(SubLObject numerical_tree) {
        return interfaces.subloop_instanceof_interface(numerical_tree, NUMERICAL_TREE);
    }

    public static SubLObject numerical_tree_p(final SubLObject numerical_tree) {
        return interfaces.subloop_instanceof_interface(numerical_tree, NUMERICAL_TREE);
    }

    public static final SubLObject finite_verbal_word_tree_p_alt(SubLObject finite_verbal_word_tree) {
        return interfaces.subloop_instanceof_interface(finite_verbal_word_tree, FINITE_VERBAL_WORD_TREE);
    }

    public static SubLObject finite_verbal_word_tree_p(final SubLObject finite_verbal_word_tree) {
        return interfaces.subloop_instanceof_interface(finite_verbal_word_tree, FINITE_VERBAL_WORD_TREE);
    }

    public static final SubLObject quantifier_tree_p_alt(SubLObject quantifier_tree) {
        return interfaces.subloop_instanceof_interface(quantifier_tree, QUANTIFIER_TREE);
    }

    public static SubLObject quantifier_tree_p(final SubLObject quantifier_tree) {
        return interfaces.subloop_instanceof_interface(quantifier_tree, QUANTIFIER_TREE);
    }

    public static final SubLObject pronoun_tree_p_alt(SubLObject pronoun_tree) {
        return interfaces.subloop_instanceof_interface(pronoun_tree, PRONOUN_TREE);
    }

    public static SubLObject pronoun_tree_p(final SubLObject pronoun_tree) {
        return interfaces.subloop_instanceof_interface(pronoun_tree, PRONOUN_TREE);
    }

    public static final SubLObject get_parse_tree_context_alt(SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, SIX_INTEGER, CONTEXT);
    }

    public static SubLObject get_parse_tree_context(final SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, SIX_INTEGER, CONTEXT);
    }

    public static final SubLObject set_parse_tree_context_alt(SubLObject v_parse_tree, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, SIX_INTEGER, CONTEXT);
    }

    public static SubLObject set_parse_tree_context(final SubLObject v_parse_tree, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, SIX_INTEGER, CONTEXT);
    }

    public static final SubLObject get_parse_tree_modifiers_alt(SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, FIVE_INTEGER, MODIFIERS);
    }

    public static SubLObject get_parse_tree_modifiers(final SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, FIVE_INTEGER, MODIFIERS);
    }

    public static final SubLObject set_parse_tree_modifiers_alt(SubLObject v_parse_tree, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, FIVE_INTEGER, MODIFIERS);
    }

    public static SubLObject set_parse_tree_modifiers(final SubLObject v_parse_tree, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, FIVE_INTEGER, MODIFIERS);
    }

    public static final SubLObject get_parse_tree_idx_alt(SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, FOUR_INTEGER, IDX);
    }

    public static SubLObject get_parse_tree_idx(final SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, FOUR_INTEGER, IDX);
    }

    public static final SubLObject set_parse_tree_idx_alt(SubLObject v_parse_tree, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, FOUR_INTEGER, IDX);
    }

    public static SubLObject set_parse_tree_idx(final SubLObject v_parse_tree, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, FOUR_INTEGER, IDX);
    }

    public static final SubLObject get_parse_tree_mother_alt(SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, THREE_INTEGER, MOTHER);
    }

    public static SubLObject get_parse_tree_mother(final SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, THREE_INTEGER, MOTHER);
    }

    public static final SubLObject set_parse_tree_mother_alt(SubLObject v_parse_tree, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, THREE_INTEGER, MOTHER);
    }

    public static SubLObject set_parse_tree_mother(final SubLObject v_parse_tree, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, THREE_INTEGER, MOTHER);
    }

    public static final SubLObject get_parse_tree_category_alt(SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, TWO_INTEGER, CATEGORY);
    }

    public static SubLObject get_parse_tree_category(final SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, TWO_INTEGER, CATEGORY);
    }

    public static final SubLObject set_parse_tree_category_alt(SubLObject v_parse_tree, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, TWO_INTEGER, CATEGORY);
    }

    public static SubLObject set_parse_tree_category(final SubLObject v_parse_tree, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, TWO_INTEGER, CATEGORY);
    }

    public static final SubLObject get_parse_tree_string_alt(SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, ONE_INTEGER, STRING);
    }

    public static SubLObject get_parse_tree_string(final SubLObject v_parse_tree) {
        return classes.subloop_get_access_protected_instance_slot(v_parse_tree, ONE_INTEGER, STRING);
    }

    public static final SubLObject set_parse_tree_string_alt(SubLObject v_parse_tree, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, ONE_INTEGER, STRING);
    }

    public static SubLObject set_parse_tree_string(final SubLObject v_parse_tree, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(v_parse_tree, value, ONE_INTEGER, STRING);
    }

    public static final SubLObject parse_tree_get_cyclifier_method_alt(SubLObject self, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject catch_var_for_parse_tree_method = NIL;
                SubLObject v_context = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_context(self);
                try {
                    try {
                        {
                            SubLObject datum = v_properties;
                            SubLObject current = datum;
                            SubLObject lexicon_tail = property_list_member($LEXICON, current);
                            SubLObject lexicon = (NIL != lexicon_tail) ? ((SubLObject) (cadr(lexicon_tail))) : object.new_class_instance(CYCLIFIER_LEXICON);
                            SubLObject score_function_tail = property_list_member($SCORE_FUNCTION, current);
                            SubLObject score_function = (NIL != score_function_tail) ? ((SubLObject) (cadr(score_function_tail))) : CYCLIFICATION_SCORE;
                            SubLObject output_tail = property_list_member($OUTPUT, current);
                            SubLObject output = (NIL != output_tail) ? ((SubLObject) (cadr(output_tail))) : $BRIEF;
                            SubLObject wff_checkP_tail = property_list_member($kw36$WFF_CHECK_, current);
                            SubLObject wff_checkP = (NIL != wff_checkP_tail) ? ((SubLObject) (cadr(wff_checkP_tail))) : T;
                            SubLObject context_tail = property_list_member($CONTEXT, current);
                            SubLObject v_context_1 = (NIL != context_tail) ? ((SubLObject) (cadr(context_tail))) : NIL;
                            SubLObject disambiguator_tail = property_list_member($DISAMBIGUATOR, current);
                            SubLObject disambiguator = (NIL != disambiguator_tail) ? ((SubLObject) (cadr(disambiguator_tail))) : NIL;
                            SubLObject coreference_resolver_tail = property_list_member($COREFERENCE_RESOLVER, current);
                            SubLObject v_coreference_resolver = (NIL != coreference_resolver_tail) ? ((SubLObject) (cadr(coreference_resolver_tail))) : NIL;
                            SubLObject error_handling_tail = property_list_member($ERROR_HANDLING, current);
                            SubLObject error_handling = (NIL != error_handling_tail) ? ((SubLObject) (cadr(error_handling_tail))) : $THROW;
                            SubLObject subcyclifier_pool_tail = property_list_member($SUBCYCLIFIER_POOL, current);
                            SubLObject subcyclifier_pool = (NIL != subcyclifier_pool_tail) ? ((SubLObject) (cadr(subcyclifier_pool_tail))) : subcyclifier.new_cyclifier_pool(list(object.new_class_instance(DATE_CYCLIFIER)));
                            SubLTrampolineFile.checkType(lexicon, CYCLIFIER_LEXICON_P);
                            SubLTrampolineFile.checkType(score_function, FUNCTION_SPEC_P);
                            SubLTrampolineFile.checkType(output, KEYWORDP);
                            if (NIL != v_context_1) {
                                SubLTrampolineFile.checkType(v_context_1, PARSE_TREE_CONTEXT_P);
                            }
                            SubLTrampolineFile.checkType(wff_checkP, BOOLEANP);
                            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                if (NIL == member(error_handling, $list_alt49, UNPROVIDED, UNPROVIDED)) {
                                    Errors.error($str_alt50$Invalid_error_handling_tag__a, error_handling);
                                }
                            }
                            if (NIL != subcyclifier_pool) {
                                SubLTrampolineFile.checkType(subcyclifier_pool, CYCLIFIER_POOL_P);
                            }
                            {
                                SubLObject v_cyclifier = object.new_class_instance(CYCLIFIER);
                                cyclifier.set_cyclifier_score_function(v_cyclifier, score_function);
                                cyclifier.set_cyclifier_output(v_cyclifier, output);
                                cyclifier.set_cyclifier_error_handling(v_cyclifier, error_handling);
                                cyclifier.set_cyclifier_subcyclifier_pool(v_cyclifier, subcyclifier_pool);
                                if (NIL != lexicon) {
                                    methods.funcall_instance_method_with_1_args(self, LEXIFY, lexicon);
                                }
                                if (NIL != disambiguator) {
                                    if (NIL == v_context_1) {
                                        v_context_1 = object.new_class_instance(PARSE_TREE_CONTEXT);
                                    }
                                    methods.funcall_instance_method_with_2_args(disambiguator, DISAMBIGUATE, self, v_context_1);
                                }
                                cyclifier.set_cyclifier_tree(v_cyclifier, self);
                                cyclifier.set_cyclifier_wff_checkP(v_cyclifier, wff_checkP);
                                if (NIL != wff_checkP) {
                                    cyclifier.set_cyclifier_memoization_state(v_cyclifier, memoization_state.new_memoization_state(UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                                    cyclifier.set_cyclifier_sbhl_resource(v_cyclifier, sbhl_marking_vars.new_sbhl_marking_space_resource(TEN_INTEGER));
                                }
                                sublisp_throw($sym29$OUTER_CATCH_FOR_PARSE_TREE_METHOD, v_cyclifier);
                            }
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.parse_tree.set_parse_tree_context(self, v_context);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym29$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
                }
                return catch_var_for_parse_tree_method;
            }
        }
    }

    public static SubLObject parse_tree_get_cyclifier_method(final SubLObject self, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject v_context = get_parse_tree_context(self);
        try {
            thread.throwStack.push($sym29$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                final SubLObject current;
                final SubLObject datum = current = v_properties;
                final SubLObject lexicon_tail = property_list_member($LEXICON, current);
                final SubLObject lexicon = (NIL != lexicon_tail) ? cadr(lexicon_tail) : object.new_class_instance(CYCLIFIER_LEXICON);
                final SubLObject score_function_tail = property_list_member($SCORE_FUNCTION, current);
                final SubLObject score_function = (NIL != score_function_tail) ? cadr(score_function_tail) : CYCLIFICATION_SCORE;
                final SubLObject output_tail = property_list_member($OUTPUT, current);
                final SubLObject output = (NIL != output_tail) ? cadr(output_tail) : $BRIEF;
                final SubLObject wff_checkP_tail = property_list_member($kw36$WFF_CHECK_, current);
                final SubLObject wff_checkP = (NIL != wff_checkP_tail) ? cadr(wff_checkP_tail) : T;
                final SubLObject context_tail = property_list_member($CONTEXT, current);
                SubLObject v_context_$1 = (NIL != context_tail) ? cadr(context_tail) : NIL;
                final SubLObject disambiguator_tail = property_list_member($DISAMBIGUATOR, current);
                final SubLObject disambiguator = (NIL != disambiguator_tail) ? cadr(disambiguator_tail) : NIL;
                final SubLObject coreference_resolver_tail = property_list_member($COREFERENCE_RESOLVER, current);
                final SubLObject v_coreference_resolver = (NIL != coreference_resolver_tail) ? cadr(coreference_resolver_tail) : NIL;
                final SubLObject error_handling_tail = property_list_member($ERROR_HANDLING, current);
                final SubLObject error_handling = (NIL != error_handling_tail) ? cadr(error_handling_tail) : $THROW;
                final SubLObject subcyclifier_pool_tail = property_list_member($SUBCYCLIFIER_POOL, current);
                final SubLObject subcyclifier_pool = (NIL != subcyclifier_pool_tail) ? cadr(subcyclifier_pool_tail) : subcyclifier.new_cyclifier_pool(list(object.new_class_instance(DATE_CYCLIFIER)));
                assert NIL != cyclifier_lexicon.cyclifier_lexicon_p(lexicon) : "! cyclifier_lexicon.cyclifier_lexicon_p(lexicon) " + ("cyclifier_lexicon.cyclifier_lexicon_p(lexicon) " + "CommonSymbols.NIL != cyclifier_lexicon.cyclifier_lexicon_p(lexicon) ") + lexicon;
                assert NIL != function_spec_p(score_function) : "! function_spec_p(score_function) " + ("Types.function_spec_p(score_function) " + "CommonSymbols.NIL != Types.function_spec_p(score_function) ") + score_function;
                assert NIL != keywordp(output) : "! keywordp(output) " + ("Types.keywordp(output) " + "CommonSymbols.NIL != Types.keywordp(output) ") + output;
                if (((NIL != v_context_$1) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == context.parse_tree_context_p(v_context_$1))) {
                    throw new AssertionError(v_context_$1);
                }
                assert NIL != booleanp(wff_checkP) : "! booleanp(wff_checkP) " + ("Types.booleanp(wff_checkP) " + "CommonSymbols.NIL != Types.booleanp(wff_checkP) ") + wff_checkP;
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == member(error_handling, $list49, UNPROVIDED, UNPROVIDED))) {
                    Errors.error($str50$Invalid_error_handling_tag__a, error_handling);
                }
                if (((NIL != subcyclifier_pool) && (!SubLTrampolineFile.assertionsDisabledInClass)) && (NIL == subcyclifier.cyclifier_pool_p(subcyclifier_pool))) {
                    throw new AssertionError(subcyclifier_pool);
                }
                final SubLObject v_cyclifier = object.new_class_instance(CYCLIFIER);
                cyclifier.set_cyclifier_score_function(v_cyclifier, score_function);
                cyclifier.set_cyclifier_output(v_cyclifier, output);
                cyclifier.set_cyclifier_error_handling(v_cyclifier, error_handling);
                cyclifier.set_cyclifier_subcyclifier_pool(v_cyclifier, subcyclifier_pool);
                if (NIL != lexicon) {
                    methods.funcall_instance_method_with_1_args(self, LEXIFY, lexicon);
                }
                if (NIL != disambiguator) {
                    if (NIL == v_context_$1) {
                        v_context_$1 = object.new_class_instance(PARSE_TREE_CONTEXT);
                    }
                    methods.funcall_instance_method_with_2_args(disambiguator, DISAMBIGUATE, self, v_context_$1);
                }
                cyclifier.set_cyclifier_tree(v_cyclifier, self);
                cyclifier.set_cyclifier_wff_checkP(v_cyclifier, wff_checkP);
                if (NIL != wff_checkP) {
                    cyclifier.set_cyclifier_memoization_state(v_cyclifier, memoization_state.new_memoization_state(UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                    cyclifier.set_cyclifier_sbhl_resource(v_cyclifier, sbhl_marking_vars.new_sbhl_marking_space_resource(TEN_INTEGER));
                }
                sublisp_throw($sym29$OUTER_CATCH_FOR_PARSE_TREE_METHOD, v_cyclifier);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_context(self, v_context);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym29$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    public static final SubLObject parse_tree_cyclify_method_alt(SubLObject self, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        {
            SubLObject v_cyclifier = methods.funcall_instance_method_with_1_args(self, GET_CYCLIFIER, v_properties);
            SubLObject cycls = NIL;
            while (NIL != methods.funcall_instance_method_with_0_args(v_cyclifier, $sym59$HAS_NEXT_)) {
                cycls = cons(methods.funcall_instance_method_with_0_args(v_cyclifier, NEXT), cycls);
            } 
            return nreverse(cycls);
        }
    }

    public static SubLObject parse_tree_cyclify_method(final SubLObject self, SubLObject v_properties) {
        if (v_properties == UNPROVIDED) {
            v_properties = NIL;
        }
        final SubLObject v_cyclifier = methods.funcall_instance_method_with_1_args(self, GET_CYCLIFIER, v_properties);
        SubLObject cycls = NIL;
        while (NIL != methods.funcall_instance_method_with_0_args(v_cyclifier, $sym59$HAS_NEXT_)) {
            cycls = cons(methods.funcall_instance_method_with_0_args(v_cyclifier, NEXT), cycls);
        } 
        return nreverse(cycls);
    }

    public static final SubLObject subloop_reserved_initialize_parse_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_parse_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_parse_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_parse_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        return NIL;
    }

    public static final SubLObject parse_tree_p_alt(SubLObject v_parse_tree) {
        return classes.subloop_instanceof_class(v_parse_tree, PARSE_TREE);
    }

    public static SubLObject parse_tree_p(final SubLObject v_parse_tree) {
        return classes.subloop_instanceof_class(v_parse_tree, PARSE_TREE);
    }

    /**
     *
     *
     * @return parse-tree-p; a deep copy of this parse tree.
     * @unknown bertolo.
     */
    @LispMethod(comment = "@return parse-tree-p; a deep copy of this parse tree.\r\n@unknown bertolo.")
    public static final SubLObject parse_tree_deep_copy_method_alt(SubLObject self) {
        {
            SubLObject class_of_self = subloop_structures.instance_class(self);
            SubLObject v_new = methods.funcall_class_method_with_0_args(class_of_self, NEW);
            SubLObject all_instance_slots = instances.instances_all_instance_slots(self);
            SubLObject cdolist_list_var = all_instance_slots;
            SubLObject instance_slot = NIL;
            for (instance_slot = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , instance_slot = cdolist_list_var.first()) {
                {
                    SubLObject slot_value = instances.get_slot(self, instance_slot);
                    SubLObject value_copy = NIL;
                    if (NIL != object.object_p(slot_value)) {
                        value_copy = methods.funcall_instance_method_with_0_args(slot_value, DEEP_COPY);
                    } else {
                        if (slot_value.isList()) {
                            value_copy = copy_list(slot_value);
                        } else {
                            if (slot_value.isVector()) {
                                value_copy = vector_utilities.copy_vector(slot_value);
                            } else {
                                if (slot_value.isHashtable()) {
                                    value_copy = hash_table_utilities.copy_hashtable(slot_value, UNPROVIDED);
                                } else {
                                    value_copy = slot_value;
                                }
                            }
                        }
                    }
                    instances.set_slot(v_new, instance_slot, value_copy);
                }
            }
            return v_new;
        }
    }

    /**
     *
     *
     * @return parse-tree-p; a deep copy of this parse tree.
     * @unknown bertolo.
     */
    @LispMethod(comment = "@return parse-tree-p; a deep copy of this parse tree.\r\n@unknown bertolo.")
    public static SubLObject parse_tree_deep_copy_method(final SubLObject self) {
        final SubLObject class_of_self = subloop_structures.instance_class(self);
        final SubLObject v_new = methods.funcall_class_method_with_0_args(class_of_self, NEW);
        SubLObject cdolist_list_var;
        final SubLObject all_instance_slots = cdolist_list_var = instances.instances_all_instance_slots(self);
        SubLObject instance_slot = NIL;
        instance_slot = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject slot_value = instances.get_slot(self, instance_slot);
            SubLObject value_copy = NIL;
            if (NIL != object.object_p(slot_value)) {
                value_copy = methods.funcall_instance_method_with_0_args(slot_value, DEEP_COPY);
            } else
                if (slot_value.isList()) {
                    value_copy = copy_list(slot_value);
                } else
                    if (slot_value.isVector()) {
                        value_copy = vector_utilities.copy_vector(slot_value);
                    } else
                        if (slot_value.isHashtable()) {
                            value_copy = hash_table_utilities.copy_hashtable(slot_value, UNPROVIDED);
                        } else {
                            value_copy = slot_value;
                        }



            instances.set_slot(v_new, instance_slot, value_copy);
            cdolist_list_var = cdolist_list_var.rest();
            instance_slot = cdolist_list_var.first();
        } 
        return v_new;
    }

    /**
     *
     *
     * @return phrase-tree-p; the root of this tree
     */
    @LispMethod(comment = "@return phrase-tree-p; the root of this tree")
    public static final SubLObject parse_tree_get_root_method_alt(SubLObject self) {
        {
            SubLObject tree = self;
            while (NIL == methods.funcall_instance_method_with_0_args(tree, ROOT_P)) {
                tree = methods.funcall_instance_method_with_0_args(tree, GET_MOTHER);
            } 
            return tree;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; the root of this tree
     */
    @LispMethod(comment = "@return phrase-tree-p; the root of this tree")
    public static SubLObject parse_tree_get_root_method(final SubLObject self) {
        SubLObject tree;
        for (tree = self; NIL == methods.funcall_instance_method_with_0_args(tree, ROOT_P); tree = methods.funcall_instance_method_with_0_args(tree, GET_MOTHER)) {
        }
        return tree;
    }

    /**
     *
     *
     * @return keywordp; the category of this parse-tree
     */
    @LispMethod(comment = "@return keywordp; the category of this parse-tree")
    public static final SubLObject parse_tree_get_category_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject category = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_category(self);
            try {
                try {
                    sublisp_throw($sym78$OUTER_CATCH_FOR_PARSE_TREE_METHOD, category);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_category(self, category);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym78$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return keywordp; the category of this parse-tree
     */
    @LispMethod(comment = "@return keywordp; the category of this parse-tree")
    public static SubLObject parse_tree_get_category_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject category = get_parse_tree_category(self);
        try {
            thread.throwStack.push($sym78$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                sublisp_throw($sym78$OUTER_CATCH_FOR_PARSE_TREE_METHOD, category);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_category(self, category);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym78$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     *
     *
     * @return non-negative-integer-p; the number of sisters left to this parse tree
     */
    @LispMethod(comment = "@return non-negative-integer-p; the number of sisters left to this parse tree")
    public static final SubLObject parse_tree_get_index_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject idx = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_idx(self);
            try {
                try {
                    sublisp_throw($sym82$OUTER_CATCH_FOR_PARSE_TREE_METHOD, idx);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_idx(self, idx);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym82$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return non-negative-integer-p; the number of sisters left to this parse tree
     */
    @LispMethod(comment = "@return non-negative-integer-p; the number of sisters left to this parse tree")
    public static SubLObject parse_tree_get_index_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject idx = get_parse_tree_idx(self);
        try {
            thread.throwStack.push($sym82$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                sublisp_throw($sym82$OUTER_CATCH_FOR_PARSE_TREE_METHOD, idx);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_idx(self, idx);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym82$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     *
     *
     * @return listp; the path from the root to this parse tree
     */
    @LispMethod(comment = "@return listp; the path from the root to this parse tree")
    public static final SubLObject parse_tree_get_path_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject idx = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_idx(self);
            try {
                try {
                    {
                        SubLObject path = (NIL != idx) ? ((SubLObject) (list(idx))) : NIL;
                        SubLObject cdolist_list_var = butlast(methods.funcall_instance_method_with_0_args(self, GET_ANCESTORS), UNPROVIDED);
                        SubLObject ancestor = NIL;
                        for (ancestor = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , ancestor = cdolist_list_var.first()) {
                            path = cons(methods.funcall_instance_method_with_0_args(ancestor, GET_INDEX), path);
                        }
                        sublisp_throw($sym86$OUTER_CATCH_FOR_PARSE_TREE_METHOD, path);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_idx(self, idx);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym86$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return listp; the path from the root to this parse tree
     */
    @LispMethod(comment = "@return listp; the path from the root to this parse tree")
    public static SubLObject parse_tree_get_path_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject idx = get_parse_tree_idx(self);
        try {
            thread.throwStack.push($sym86$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                SubLObject path = (NIL != idx) ? list(idx) : NIL;
                SubLObject cdolist_list_var = butlast(methods.funcall_instance_method_with_0_args(self, GET_ANCESTORS), UNPROVIDED);
                SubLObject ancestor = NIL;
                ancestor = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    path = cons(methods.funcall_instance_method_with_0_args(ancestor, GET_INDEX), path);
                    cdolist_list_var = cdolist_list_var.rest();
                    ancestor = cdolist_list_var.first();
                } 
                sublisp_throw($sym86$OUTER_CATCH_FOR_PARSE_TREE_METHOD, path);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_idx(self, idx);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym86$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     *
     *
     * @return parse-tree-p; the mother of this tree
     */
    @LispMethod(comment = "@return parse-tree-p; the mother of this tree")
    public static final SubLObject parse_tree_get_mother_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    sublisp_throw($sym90$OUTER_CATCH_FOR_PARSE_TREE_METHOD, mother);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym90$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return parse-tree-p; the mother of this tree
     */
    @LispMethod(comment = "@return parse-tree-p; the mother of this tree")
    public static SubLObject parse_tree_get_mother_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym90$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                sublisp_throw($sym90$OUTER_CATCH_FOR_PARSE_TREE_METHOD, mother);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym90$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     *
     *
     * @return word-tree-p; the (first) semantic head of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return word-tree-p; the (first) semantic head of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static final SubLObject parse_tree_get_semantic_head_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEADS).first();
    }

    /**
     *
     *
     * @return word-tree-p; the (first) semantic head of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return word-tree-p; the (first) semantic head of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static SubLObject parse_tree_get_semantic_head_method(final SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEADS).first();
    }

    /**
     *
     *
     * @return listp; a list of all complements of this tree, in surface order
     */
    @LispMethod(comment = "@return listp; a list of all complements of this tree, in surface order")
    public static final SubLObject parse_tree_get_complements_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_COMPLEMENTS))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of all complements of this tree, in surface order
     */
    @LispMethod(comment = "@return listp; a list of all complements of this tree, in surface order")
    public static SubLObject parse_tree_get_complements_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_COMPLEMENTS) : NIL;
    }

    /**
     *
     *
     * @return listp; a list of modifiers of this tree
     */
    @LispMethod(comment = "@return listp; a list of modifiers of this tree")
    public static final SubLObject parse_tree_get_modifiers_method_alt(SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return listp; a list of modifiers of this tree
     */
    @LispMethod(comment = "@return listp; a list of modifiers of this tree")
    public static SubLObject parse_tree_get_modifiers_method(final SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return listp; the constituents this tree modifies
     */
    @LispMethod(comment = "@return listp; the constituents this tree modifies")
    public static final SubLObject parse_tree_get_modifieds_method_alt(SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return listp; the constituents this tree modifies
     */
    @LispMethod(comment = "@return listp; the constituents this tree modifies")
    public static SubLObject parse_tree_get_modifieds_method(final SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return parse-tree-context-p; other parse trees created in this tree's context
     */
    @LispMethod(comment = "@return parse-tree-context-p; other parse trees created in this tree\'s context")
    public static final SubLObject parse_tree_get_context_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject v_context = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_context(self);
            try {
                try {
                    sublisp_throw($sym108$OUTER_CATCH_FOR_PARSE_TREE_METHOD, v_context);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_context(self, v_context);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym108$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return parse-tree-context-p; other parse trees created in this tree's context
     */
    @LispMethod(comment = "@return parse-tree-context-p; other parse trees created in this tree\'s context")
    public static SubLObject parse_tree_get_context_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject v_context = get_parse_tree_context(self);
        try {
            thread.throwStack.push($sym108$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                sublisp_throw($sym108$OUTER_CATCH_FOR_PARSE_TREE_METHOD, v_context);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_context(self, v_context);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym108$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     *
     *
     * @return booleanp; t if this tree is temporal, nil otherwise
     * @unknown this is just a fallback; specific trees override this
     */
    @LispMethod(comment = "@return booleanp; t if this tree is temporal, nil otherwise\r\n@unknown this is just a fallback; specific trees override this")
    public static final SubLObject parse_tree_temporal_tree_p_method_alt(SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this tree is temporal, nil otherwise
     * @unknown this is just a fallback; specific trees override this
     */
    @LispMethod(comment = "@return booleanp; t if this tree is temporal, nil otherwise\r\n@unknown this is just a fallback; specific trees override this")
    public static SubLObject parse_tree_temporal_tree_p_method(final SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return boolean; t if this is a question, nil otherwise
     */
    @LispMethod(comment = "@return boolean; t if this is a question, nil otherwise")
    public static final SubLObject parse_tree_cyclifiable_question_p_method_alt(SubLObject self) {
        return com.cyc.cycjava.cycl.parse_tree.question_tree_p(self);
    }

    /**
     *
     *
     * @return boolean; t if this is a question, nil otherwise
     */
    @LispMethod(comment = "@return boolean; t if this is a question, nil otherwise")
    public static SubLObject parse_tree_cyclifiable_question_p_method(final SubLObject self) {
        return question_tree_p(self);
    }

    /**
     *
     *
     * @return booleanp; t if this tree is the root, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is the root, nil otherwise")
    public static final SubLObject parse_tree_root_p_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    sublisp_throw($sym117$OUTER_CATCH_FOR_PARSE_TREE_METHOD, makeBoolean(NIL == mother));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym117$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this tree is the root, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is the root, nil otherwise")
    public static SubLObject parse_tree_root_p_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym117$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                sublisp_throw($sym117$OUTER_CATCH_FOR_PARSE_TREE_METHOD, makeBoolean(NIL == mother));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym117$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     *
     *
     * @return parse-tree-p; this parse-tree with lexical entries added
     */
    @LispMethod(comment = "@return parse-tree-p; this parse-tree with lexical entries added")
    public static final SubLObject parse_tree_lexify_method_alt(SubLObject self, SubLObject lexicon) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject _prev_bind_0 = cyclifier_interface.$allow_duplicate_cyclificationsP$.currentBinding(thread);
                SubLObject _prev_bind_1 = cyclifier_interface.$cyclification_complete$.currentBinding(thread);
                try {
                    cyclifier_interface.$allow_duplicate_cyclificationsP$.bind(T, thread);
                    cyclifier_interface.$cyclification_complete$.bind(NIL, thread);
                    methods.funcall_instance_method_with_1_args(self, LEXIFY_INT, lexicon);
                } finally {
                    cyclifier_interface.$cyclification_complete$.rebind(_prev_bind_1, thread);
                    cyclifier_interface.$allow_duplicate_cyclificationsP$.rebind(_prev_bind_0, thread);
                }
            }
            return self;
        }
    }

    /**
     *
     *
     * @return parse-tree-p; this parse-tree with lexical entries added
     */
    @LispMethod(comment = "@return parse-tree-p; this parse-tree with lexical entries added")
    public static SubLObject parse_tree_lexify_method(final SubLObject self, final SubLObject lexicon) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject _prev_bind_0 = cyclifier_interface.$allow_duplicate_cyclificationsP$.currentBinding(thread);
        final SubLObject _prev_bind_2 = cyclifier_interface.$cyclification_complete$.currentBinding(thread);
        try {
            cyclifier_interface.$allow_duplicate_cyclificationsP$.bind(T, thread);
            cyclifier_interface.$cyclification_complete$.bind(NIL, thread);
            methods.funcall_instance_method_with_1_args(self, LEXIFY_INT, lexicon);
        } finally {
            cyclifier_interface.$cyclification_complete$.rebind(_prev_bind_2, thread);
            cyclifier_interface.$allow_duplicate_cyclificationsP$.rebind(_prev_bind_0, thread);
        }
        return self;
    }

    /**
     *
     *
     * @return parse-tree-p; this parse-tree with lexical entries added
     */
    @LispMethod(comment = "@return parse-tree-p; this parse-tree with lexical entries added")
    public static final SubLObject parse_tree_lexify_int_method_alt(SubLObject self, SubLObject lexicon) {
        return self;
    }

    /**
     *
     *
     * @return parse-tree-p; this parse-tree with lexical entries added
     */
    @LispMethod(comment = "@return parse-tree-p; this parse-tree with lexical entries added")
    public static SubLObject parse_tree_lexify_int_method(final SubLObject self, final SubLObject lexicon) {
        return self;
    }

    /**
     *
     *
     * @return listp; the CycL representation of the meaning of this parse-tree
     */
    @LispMethod(comment = "@return listp; the CycL representation of the meaning of this parse-tree")
    public static final SubLObject parse_tree_cyclify_int_method_alt(SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return listp; the CycL representation of the meaning of this parse-tree
     */
    @LispMethod(comment = "@return listp; the CycL representation of the meaning of this parse-tree")
    public static SubLObject parse_tree_cyclify_int_method(final SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return listp; the list of ancestors of this parse tree
     */
    @LispMethod(comment = "@return listp; the list of ancestors of this parse tree")
    public static final SubLObject parse_tree_get_ancestors_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    sublisp_throw($sym130$OUTER_CATCH_FOR_PARSE_TREE_METHOD, NIL != mother ? ((SubLObject) (cons(mother, methods.funcall_instance_method_with_0_args(mother, GET_ANCESTORS)))) : NIL);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym130$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return listp; the list of ancestors of this parse tree
     */
    @LispMethod(comment = "@return listp; the list of ancestors of this parse tree")
    public static SubLObject parse_tree_get_ancestors_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym130$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                sublisp_throw($sym130$OUTER_CATCH_FOR_PARSE_TREE_METHOD, NIL != mother ? cons(mother, methods.funcall_instance_method_with_0_args(mother, GET_ANCESTORS)) : NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym130$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     *
     *
     * @return parse-tree-p; the sister INDEX positions to the right of this
    parse tree if INDEX is positive and to the left if INDEX is negative;
    nil if there is no such sister
     */
    @LispMethod(comment = "@return parse-tree-p; the sister INDEX positions to the right of this\r\nparse tree if INDEX is positive and to the left if INDEX is negative;\r\nnil if there is no such sister")
    public static final SubLObject parse_tree_get_sister_method_alt(SubLObject self, SubLObject index) {
        {
            SubLObject catch_var_for_parse_tree_method = NIL;
            SubLObject idx = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_idx(self);
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    SubLTrampolineFile.checkType(index, INTEGERP);
                    if (NIL != idx) {
                        {
                            SubLObject sister_index = add(idx, index);
                            sublisp_throw($sym135$OUTER_CATCH_FOR_PARSE_TREE_METHOD, (NIL != mother) && (NIL != subl_promotions.non_negative_integer_p(sister_index)) ? ((SubLObject) (methods.funcall_instance_method_with_1_args(mother, GET_DAUGHTER, add(idx, index)))) : NIL);
                        }
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_idx(self, idx);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym135$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            }
            return catch_var_for_parse_tree_method;
        }
    }

    /**
     *
     *
     * @return parse-tree-p; the sister INDEX positions to the right of this
    parse tree if INDEX is positive and to the left if INDEX is negative;
    nil if there is no such sister
     */
    @LispMethod(comment = "@return parse-tree-p; the sister INDEX positions to the right of this\r\nparse tree if INDEX is positive and to the left if INDEX is negative;\r\nnil if there is no such sister")
    public static SubLObject parse_tree_get_sister_method(final SubLObject self, final SubLObject index) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_parse_tree_method = NIL;
        final SubLObject idx = get_parse_tree_idx(self);
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym135$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
            try {
                assert NIL != integerp(index) : "! integerp(index) " + ("Types.integerp(index) " + "CommonSymbols.NIL != Types.integerp(index) ") + index;
                if (NIL != idx) {
                    final SubLObject sister_index = add(idx, index);
                    sublisp_throw($sym135$OUTER_CATCH_FOR_PARSE_TREE_METHOD, (NIL != mother) && (NIL != subl_promotions.non_negative_integer_p(sister_index)) ? methods.funcall_instance_method_with_1_args(mother, GET_DAUGHTER, add(idx, index)) : NIL);
                }
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_idx(self, idx);
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_parse_tree_method = Errors.handleThrowable(ccatch_env_var, $sym135$OUTER_CATCH_FOR_PARSE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_parse_tree_method;
    }

    /**
     * Pretty-prints this phrase-tree to STREAM, ignoring DEPTH
     */
    @LispMethod(comment = "Pretty-prints this phrase-tree to STREAM, ignoring DEPTH")
    public static final SubLObject parse_tree_print_method_alt(SubLObject self, SubLObject stream, SubLObject depth) {
        return com.cyc.cycjava.cycl.parse_tree.pprint_parse_tree(self, stream, ZERO_INTEGER);
    }

    /**
     * Pretty-prints this phrase-tree to STREAM, ignoring DEPTH
     */
    @LispMethod(comment = "Pretty-prints this phrase-tree to STREAM, ignoring DEPTH")
    public static SubLObject parse_tree_print_method(final SubLObject self, final SubLObject stream, final SubLObject depth) {
        return pprint_parse_tree(self, stream, ZERO_INTEGER);
    }

    /**
     * Pretty prings WT to STREAM, indenting LEVEL
     */
    @LispMethod(comment = "Pretty prings WT to STREAM, indenting LEVEL")
    public static final SubLObject pprint_word_tree_alt(SubLObject wt, SubLObject stream, SubLObject level) {
        {
            SubLObject indent = THREE_INTEGER;
            SubLObject base_column = multiply(level, indent);
            string_utilities.indent(stream, base_column);
            write_string($str_alt143$__W_, stream, UNPROVIDED, UNPROVIDED);
            princ(methods.funcall_instance_method_with_0_args(wt, GET_CATEGORY), stream);
            write_string($str_alt144$_, stream, UNPROVIDED, UNPROVIDED);
            if (NIL != methods.funcall_instance_method_with_0_args(wt, GET_RANKED_LEXES)) {
                write_string($str_alt146$_, stream, UNPROVIDED, UNPROVIDED);
            }
            write_string($str_alt147$__, stream, UNPROVIDED, UNPROVIDED);
            if (NIL != methods.funcall_instance_method_with_0_args(wt, GET_STRING)) {
                write_string(methods.funcall_instance_method_with_0_args(wt, GET_STRING), stream, UNPROVIDED, UNPROVIDED);
            }
            write_string($str_alt149$_, stream, UNPROVIDED, UNPROVIDED);
            terpri(stream);
            return wt;
        }
    }

    /**
     * Pretty prings WT to STREAM, indenting LEVEL
     */
    @LispMethod(comment = "Pretty prings WT to STREAM, indenting LEVEL")
    public static SubLObject pprint_word_tree(final SubLObject wt, final SubLObject stream, final SubLObject level) {
        final SubLObject indent = THREE_INTEGER;
        final SubLObject base_column = multiply(level, indent);
        string_utilities.indent(stream, base_column);
        write_string($str143$__W_, stream, UNPROVIDED, UNPROVIDED);
        princ(methods.funcall_instance_method_with_0_args(wt, GET_CATEGORY), stream);
        write_string($str144$_, stream, UNPROVIDED, UNPROVIDED);
        if (NIL != methods.funcall_instance_method_with_0_args(wt, GET_RANKED_LEXES)) {
            write_string($str146$_, stream, UNPROVIDED, UNPROVIDED);
        }
        write_string($str147$__, stream, UNPROVIDED, UNPROVIDED);
        if (NIL != methods.funcall_instance_method_with_0_args(wt, GET_STRING)) {
            write_string(methods.funcall_instance_method_with_0_args(wt, GET_STRING), stream, UNPROVIDED, UNPROVIDED);
        }
        write_string($str149$_, stream, UNPROVIDED, UNPROVIDED);
        terpri(stream);
        return wt;
    }

    /**
     * Pretty-prints this parse-tree to STREAM, indenting LEVEL
     */
    @LispMethod(comment = "Pretty-prints this parse-tree to STREAM, indenting LEVEL")
    public static final SubLObject pprint_parse_tree_alt(SubLObject pt, SubLObject stream, SubLObject level) {
        return NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(pt) ? ((SubLObject) (com.cyc.cycjava.cycl.parse_tree.pprint_phrase_tree(pt, stream, level))) : com.cyc.cycjava.cycl.parse_tree.pprint_word_tree(pt, stream, level);
    }

    /**
     * Pretty-prints this parse-tree to STREAM, indenting LEVEL
     */
    @LispMethod(comment = "Pretty-prints this parse-tree to STREAM, indenting LEVEL")
    public static SubLObject pprint_parse_tree(final SubLObject pt, final SubLObject stream, final SubLObject level) {
        return NIL != phrase_tree_p(pt) ? pprint_phrase_tree(pt, stream, level) : pprint_word_tree(pt, stream, level);
    }

    /**
     *
     *
     * @param PARSE-EXPRESSION
    listp;n
     * 		
     * @return parse-tree; a parse tree corresponding to PARSE-EXPRESSION
     */
    @LispMethod(comment = "@param PARSE-EXPRESSION\nlistp;n\r\n\t\t\r\n@return parse-tree; a parse tree corresponding to PARSE-EXPRESSION")
    public static final SubLObject new_parse_tree_alt(SubLObject parse_expression, SubLObject lexicon, SubLObject v_context) {
        if (lexicon == UNPROVIDED) {
            lexicon = NIL;
        }
        if (v_context == UNPROVIDED) {
            v_context = object.new_class_instance(PARSE_TREE_CONTEXT);
        }
        return com.cyc.cycjava.cycl.parse_tree.process_modifiers(com.cyc.cycjava.cycl.parse_tree.process_abs_index(com.cyc.cycjava.cycl.parse_tree.retag_aux_verbs(com.cyc.cycjava.cycl.parse_tree.new_parse_tree_int(parse_expression, NIL, ZERO_INTEGER, lexicon, v_context))));
    }

    /**
     *
     *
     * @param PARSE-EXPRESSION
    listp;n
     * 		
     * @return parse-tree; a parse tree corresponding to PARSE-EXPRESSION
     */
    @LispMethod(comment = "@param PARSE-EXPRESSION\nlistp;n\r\n\t\t\r\n@return parse-tree; a parse tree corresponding to PARSE-EXPRESSION")
    public static SubLObject new_parse_tree(final SubLObject parse_expression, SubLObject lexicon, SubLObject v_context) {
        if (lexicon == UNPROVIDED) {
            lexicon = NIL;
        }
        if (v_context == UNPROVIDED) {
            v_context = object.new_class_instance(PARSE_TREE_CONTEXT);
        }
        return process_modifiers(process_abs_index(retag_aux_verbs(new_parse_tree_int(parse_expression, NIL, ZERO_INTEGER, lexicon, v_context))));
    }

    /**
     *
     *
     * @param PARSE-EXPRESSION
    listp;
     * 		
     * @return parse-tree; a parse tree corresponding to PARSE-EXPRESSION
     */
    @LispMethod(comment = "@param PARSE-EXPRESSION\nlistp;\r\n\t\t\r\n@return parse-tree; a parse tree corresponding to PARSE-EXPRESSION")
    public static final SubLObject new_parse_tree_int_alt(SubLObject parse_expression, SubLObject mother, SubLObject index, SubLObject lexicon, SubLObject v_context) {
        if (mother == UNPROVIDED) {
            mother = NIL;
        }
        if (index == UNPROVIDED) {
            index = NIL;
        }
        if (lexicon == UNPROVIDED) {
            lexicon = NIL;
        }
        if (v_context == UNPROVIDED) {
            v_context = object.new_class_instance(PARSE_TREE_CONTEXT);
        }
        {
            SubLObject tree = (NIL != word_tree.phrase_expression_p(parse_expression)) ? ((SubLObject) (com.cyc.cycjava.cycl.parse_tree.parse_expression_to_phrase_tree(parse_expression, mother, index, lexicon, v_context))) : word_tree.parse_expression_to_word_tree(parse_expression, mother, index, v_context);
            if (NIL != lexicon) {
                methods.funcall_instance_method_with_1_args(tree, RETOKENIZE, lexicon);
            }
            return tree;
        }
    }

    /**
     *
     *
     * @param PARSE-EXPRESSION
    listp;
     * 		
     * @return parse-tree; a parse tree corresponding to PARSE-EXPRESSION
     */
    @LispMethod(comment = "@param PARSE-EXPRESSION\nlistp;\r\n\t\t\r\n@return parse-tree; a parse tree corresponding to PARSE-EXPRESSION")
    public static SubLObject new_parse_tree_int(final SubLObject parse_expression, SubLObject mother, SubLObject index, SubLObject lexicon, SubLObject v_context) {
        if (mother == UNPROVIDED) {
            mother = NIL;
        }
        if (index == UNPROVIDED) {
            index = NIL;
        }
        if (lexicon == UNPROVIDED) {
            lexicon = NIL;
        }
        if (v_context == UNPROVIDED) {
            v_context = object.new_class_instance(PARSE_TREE_CONTEXT);
        }
        final SubLObject tree = (NIL != word_tree.phrase_expression_p(parse_expression)) ? parse_expression_to_phrase_tree(parse_expression, mother, index, lexicon, v_context) : word_tree.parse_expression_to_word_tree(parse_expression, mother, index, v_context);
        if (NIL != lexicon) {
            methods.funcall_instance_method_with_1_args(tree, RETOKENIZE, lexicon);
        }
        return tree;
    }

    /**
     * Evaluate BODY with SUB-TREE bound to every sub tree in PARSE-TREE
     *
     * @param CATEGORIES
     * 		listp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed
     * @param ORDER
     * 		keywordp; :DFR2L (depth-first right-to-left) or :DFL2R (depth-first left-to-right)
     */
    @LispMethod(comment = "Evaluate BODY with SUB-TREE bound to every sub tree in PARSE-TREE\r\n\r\n@param CATEGORIES\r\n\t\tlistp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed\r\n@param ORDER\r\n\t\tkeywordp; :DFR2L (depth-first right-to-left) or :DFL2R (depth-first left-to-right)")
    public static final SubLObject do_parse_tree_subtrees_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt151);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject sub_tree = NIL;
                    SubLObject v_parse_tree = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt151);
                    sub_tree = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt151);
                    v_parse_tree = current.first();
                    current = current.rest();
                    {
                        SubLObject doneP = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt151);
                        current = current.rest();
                        {
                            SubLObject categories = (current.isCons()) ? ((SubLObject) (current.first())) : $ALL;
                            destructuring_bind_must_listp(current, datum, $list_alt151);
                            current = current.rest();
                            {
                                SubLObject order = (current.isCons()) ? ((SubLObject) (current.first())) : $DFR2L;
                                destructuring_bind_must_listp(current, datum, $list_alt151);
                                current = current.rest();
                                if (NIL == current) {
                                    current = temp;
                                    {
                                        SubLObject body = current;
                                        if (categories == $ALL) {
                                            {
                                                SubLObject stack = $sym154$STACK;
                                                SubLObject order_var = $sym155$ORDER_VAR;
                                                SubLObject possible_orders = $sym156$POSSIBLE_ORDERS;
                                                return list(CLET, list(bq_cons(stack, $list_alt158), list(order_var, order), bq_cons(possible_orders, $list_alt159), sub_tree), list(MUST, listS($sym161$MEMBER_, order_var, possible_orders, $list_alt162), $str_alt163$_S_is_not_one_of__S, order_var, possible_orders), list(STACK_PUSH, v_parse_tree, stack), listS(UNTIL, list(COR, list(STACK_EMPTY_P, stack), doneP), list(CSETQ, sub_tree, list(STACK_POP, stack)), append(body, list(list(PWHEN, list(PHRASE_TREE_P, sub_tree), list(DO_VECTOR, list(DAUGHTER, list(GET_PHRASE_TREE_DAUGHTERS, sub_tree), $BACKWARD_, listS(EQ, order_var, $list_alt176)), list(STACK_PUSH, DAUGHTER, stack)))))));
                                            }
                                        } else {
                                            {
                                                SubLObject categories_var = $sym177$CATEGORIES_VAR;
                                                return list(CLET, list(list(categories_var, categories)), list(DO_PARSE_TREE_SUBTREES, list(sub_tree, v_parse_tree, doneP, $ALL, order), listS(PWHEN, list(COR, listS(EQ, categories_var, $list_alt179), list($sym161$MEMBER_, listS(FIM, sub_tree, $list_alt181), categories_var)), append(body, NIL))));
                                            }
                                        }
                                    }
                                } else {
                                    cdestructuring_bind_error(datum, $list_alt151);
                                }
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Evaluate BODY with SUB-TREE bound to every sub tree in PARSE-TREE
     *
     * @param CATEGORIES
     * 		listp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed
     * @param ORDER
     * 		keywordp; :DFR2L (depth-first right-to-left) or :DFL2R (depth-first left-to-right)
     */
    @LispMethod(comment = "Evaluate BODY with SUB-TREE bound to every sub tree in PARSE-TREE\r\n\r\n@param CATEGORIES\r\n\t\tlistp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed\r\n@param ORDER\r\n\t\tkeywordp; :DFR2L (depth-first right-to-left) or :DFL2R (depth-first left-to-right)")
    public static SubLObject do_parse_tree_subtrees(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list151);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject sub_tree = NIL;
        SubLObject v_parse_tree = NIL;
        destructuring_bind_must_consp(current, datum, $list151);
        sub_tree = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list151);
        v_parse_tree = current.first();
        current = current.rest();
        final SubLObject doneP = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list151);
        current = current.rest();
        final SubLObject categories = (current.isCons()) ? current.first() : $ALL;
        destructuring_bind_must_listp(current, datum, $list151);
        current = current.rest();
        final SubLObject order = (current.isCons()) ? current.first() : $DFR2L;
        destructuring_bind_must_listp(current, datum, $list151);
        current = current.rest();
        if (NIL != current) {
            cdestructuring_bind_error(datum, $list151);
            return NIL;
        }
        final SubLObject body;
        current = body = temp;
        if (categories == $ALL) {
            final SubLObject stack = $sym154$STACK;
            final SubLObject order_var = $sym155$ORDER_VAR;
            final SubLObject possible_orders = $sym156$POSSIBLE_ORDERS;
            return list(CLET, list(bq_cons(stack, $list158), list(order_var, order), bq_cons(possible_orders, $list159), sub_tree), list(MUST, listS($sym161$MEMBER_, order_var, possible_orders, $list162), $str163$_S_is_not_one_of__S, order_var, possible_orders), list(STACK_PUSH, v_parse_tree, stack), listS(UNTIL, list(COR, list(STACK_EMPTY_P, stack), doneP), list(CSETQ, sub_tree, list(STACK_POP, stack)), append(body, list(list(PWHEN, list(PHRASE_TREE_P, sub_tree), list(DO_VECTOR, list(DAUGHTER, list(GET_PHRASE_TREE_DAUGHTERS, sub_tree), $BACKWARD_, listS(EQ, order_var, $list176)), list(STACK_PUSH, DAUGHTER, stack)))))));
        }
        final SubLObject categories_var = $sym177$CATEGORIES_VAR;
        return list(CLET, list(list(categories_var, categories)), list(DO_PARSE_TREE_SUBTREES, list(sub_tree, v_parse_tree, doneP, $ALL, order), listS(PWHEN, list(COR, listS(EQ, categories_var, $list179), list($sym161$MEMBER_, listS(FIM, sub_tree, $list181), categories_var)), append(body, NIL))));
    }

    /**
     * Evaluate BODY with WORD-TREE bound to each word-tree-p in PARSE-TREE, moving from left to right
     *
     * @param CATEGORIES
     * 		listp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed
     */
    @LispMethod(comment = "Evaluate BODY with WORD-TREE bound to each word-tree-p in PARSE-TREE, moving from left to right\r\n\r\n@param CATEGORIES\r\n\t\tlistp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed")
    public static final SubLObject do_parse_tree_word_trees_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt182);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject v_word_tree = NIL;
                    SubLObject v_parse_tree = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt182);
                    v_word_tree = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt182);
                    v_parse_tree = current.first();
                    current = current.rest();
                    {
                        SubLObject doneP = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt182);
                        current = current.rest();
                        {
                            SubLObject categories = (current.isCons()) ? ((SubLObject) (current.first())) : $ALL;
                            destructuring_bind_must_listp(current, datum, $list_alt182);
                            current = current.rest();
                            if (NIL == current) {
                                current = temp;
                                {
                                    SubLObject body = current;
                                    return list(DO_PARSE_TREE_SUBTREES, listS(v_word_tree, v_parse_tree, doneP, categories, $list_alt176), listS(PWHEN, list(WORD_TREE_P, v_word_tree), append(body, NIL)));
                                }
                            } else {
                                cdestructuring_bind_error(datum, $list_alt182);
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     * Evaluate BODY with WORD-TREE bound to each word-tree-p in PARSE-TREE, moving from left to right
     *
     * @param CATEGORIES
     * 		listp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed
     */
    @LispMethod(comment = "Evaluate BODY with WORD-TREE bound to each word-tree-p in PARSE-TREE, moving from left to right\r\n\r\n@param CATEGORIES\r\n\t\tlistp or :ALL; if listp, only evaluate BODY on subtrees whose category is listed")
    public static SubLObject do_parse_tree_word_trees(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list182);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject v_word_tree = NIL;
        SubLObject v_parse_tree = NIL;
        destructuring_bind_must_consp(current, datum, $list182);
        v_word_tree = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list182);
        v_parse_tree = current.first();
        current = current.rest();
        final SubLObject doneP = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, datum, $list182);
        current = current.rest();
        final SubLObject categories = (current.isCons()) ? current.first() : $ALL;
        destructuring_bind_must_listp(current, datum, $list182);
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return list(DO_PARSE_TREE_SUBTREES, listS(v_word_tree, v_parse_tree, doneP, categories, $list176), listS(PWHEN, list(WORD_TREE_P, v_word_tree), append(body, NIL)));
        }
        cdestructuring_bind_error(datum, $list182);
        return NIL;
    }

    public static final SubLObject get_phrase_tree_daughters_alt(SubLObject phrase_tree) {
        return classes.subloop_get_access_protected_instance_slot(phrase_tree, SEVEN_INTEGER, DAUGHTERS);
    }

    public static SubLObject get_phrase_tree_daughters(final SubLObject phrase_tree) {
        return classes.subloop_get_access_protected_instance_slot(phrase_tree, SEVEN_INTEGER, DAUGHTERS);
    }

    public static final SubLObject set_phrase_tree_daughters_alt(SubLObject phrase_tree, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(phrase_tree, value, SEVEN_INTEGER, DAUGHTERS);
    }

    public static SubLObject set_phrase_tree_daughters(final SubLObject phrase_tree, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(phrase_tree, value, SEVEN_INTEGER, DAUGHTERS);
    }

    public static final SubLObject subloop_reserved_initialize_phrase_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_phrase_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_phrase_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_phrase_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject phrase_tree_p_alt(SubLObject phrase_tree) {
        return classes.subloop_instanceof_class(phrase_tree, PHRASE_TREE);
    }

    public static SubLObject phrase_tree_p(final SubLObject phrase_tree) {
        return classes.subloop_instanceof_class(phrase_tree, PHRASE_TREE);
    }

    /**
     *
     *
     * @return listp; the list of head daughers of this phrase
     */
    @LispMethod(comment = "@return listp; the list of head daughers of this phrase")
    public static final SubLObject phrase_tree_get_head_daughters_method_alt(SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return listp; the list of head daughers of this phrase
     */
    @LispMethod(comment = "@return listp; the list of head daughers of this phrase")
    public static SubLObject phrase_tree_get_head_daughters_method(final SubLObject self) {
        return NIL;
    }

    /**
     *
     *
     * @return parse-tree-p; the (first) head daughter of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return parse-tree-p; the (first) head daughter of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static final SubLObject phrase_tree_get_head_daughter_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS).first();
    }

    /**
     *
     *
     * @return parse-tree-p; the (first) head daughter of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return parse-tree-p; the (first) head daughter of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static SubLObject phrase_tree_get_head_daughter_method(final SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS).first();
    }

    /**
     *
     *
     * @return word-tree-p; the (first) head of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return word-tree-p; the (first) head of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static final SubLObject phrase_tree_get_head_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_HEADS).first();
    }

    /**
     *
     *
     * @return word-tree-p; the (first) head of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return word-tree-p; the (first) head of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static SubLObject phrase_tree_get_head_method(final SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_HEADS).first();
    }

    /**
     *
     *
     * @return listp; the list of head daughers of this phrase
     */
    @LispMethod(comment = "@return listp; the list of head daughers of this phrase")
    public static final SubLObject phrase_tree_get_semantic_head_daughters_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS);
    }

    /**
     *
     *
     * @return listp; the list of head daughers of this phrase
     */
    @LispMethod(comment = "@return listp; the list of head daughers of this phrase")
    public static SubLObject phrase_tree_get_semantic_head_daughters_method(final SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS);
    }

    /**
     *
     *
     * @return parse-tree-p; the (first) semantic head daughter of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return parse-tree-p; the (first) semantic head daughter of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static final SubLObject phrase_tree_get_semantic_head_daughter_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD_DAUGHTERS).first();
    }

    /**
     *
     *
     * @return parse-tree-p; the (first) semantic head daughter of this phrase
     * @unknown currently wasteful, since all heads are computed, then discarded
     */
    @LispMethod(comment = "@return parse-tree-p; the (first) semantic head daughter of this phrase\r\n@unknown currently wasteful, since all heads are computed, then discarded")
    public static SubLObject phrase_tree_get_semantic_head_daughter_method(final SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD_DAUGHTERS).first();
    }

    /**
     *
     *
     * @return listp; the unique referents of this phrase
     */
    @LispMethod(comment = "@return listp; the unique referents of this phrase")
    public static final SubLObject phrase_tree_get_refs_method_alt(SubLObject self) {
        {
            SubLObject heads = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEADS);
            SubLObject refs = NIL;
            if ((NIL == heads) && methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(ONE_INTEGER)) {
                {
                    SubLObject daughter = methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER);
                    if (((NIL != com.cyc.cycjava.cycl.parse_tree.quantifier_tree_p(daughter)) && (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(daughter))) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(daughter, GET_DAUGHTER, ZERO_INTEGER), GET_CATEGORY).eql($kw208$_)) {
                        heads = cons(methods.funcall_instance_method_with_1_args(daughter, GET_DAUGHTER, ZERO_INTEGER), heads);
                    } else {
                        heads = cons(daughter, heads);
                    }
                }
            }
            {
                SubLObject cdolist_list_var = heads;
                SubLObject head = NIL;
                for (head = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head = cdolist_list_var.first()) {
                    {
                        SubLObject cdolist_list_var_2 = methods.funcall_instance_method_with_0_args(head, GET_REFS);
                        SubLObject ref = NIL;
                        for (ref = cdolist_list_var_2.first(); NIL != cdolist_list_var_2; cdolist_list_var_2 = cdolist_list_var_2.rest() , ref = cdolist_list_var_2.first()) {
                            refs = cons(ref, refs);
                        }
                    }
                }
            }
            return refs;
        }
    }

    /**
     *
     *
     * @return listp; the unique referents of this phrase
     */
    @LispMethod(comment = "@return listp; the unique referents of this phrase")
    public static SubLObject phrase_tree_get_refs_method(final SubLObject self) {
        SubLObject heads = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEADS);
        SubLObject refs = NIL;
        if ((NIL == heads) && methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(ONE_INTEGER)) {
            final SubLObject daughter = methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER);
            if (((NIL != quantifier_tree_p(daughter)) && (NIL != phrase_tree_p(daughter))) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(daughter, GET_DAUGHTER, ZERO_INTEGER), GET_CATEGORY).eql($kw208$_)) {
                heads = cons(methods.funcall_instance_method_with_1_args(daughter, GET_DAUGHTER, ZERO_INTEGER), heads);
            } else {
                heads = cons(daughter, heads);
            }
        }
        SubLObject cdolist_list_var = heads;
        SubLObject head = NIL;
        head = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$2 = methods.funcall_instance_method_with_0_args(head, GET_REFS);
            SubLObject ref = NIL;
            ref = cdolist_list_var_$2.first();
            while (NIL != cdolist_list_var_$2) {
                refs = cons(ref, refs);
                cdolist_list_var_$2 = cdolist_list_var_$2.rest();
                ref = cdolist_list_var_$2.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            head = cdolist_list_var.first();
        } 
        return refs;
    }

    /**
     *
     *
     * @return listp; the list of all modifiers of this phrase
     */
    @LispMethod(comment = "@return listp; the list of all modifiers of this phrase")
    public static final SubLObject phrase_tree_get_modifiers_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_MODIFIERS))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; the list of all modifiers of this phrase
     */
    @LispMethod(comment = "@return listp; the list of all modifiers of this phrase")
    public static SubLObject phrase_tree_get_modifiers_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_MODIFIERS) : NIL;
    }

    /**
     *
     *
     * @return object; the conjunction of this tree if there is one, nil otherwise
     */
    @LispMethod(comment = "@return object; the conjunction of this tree if there is one, nil otherwise")
    public static final SubLObject phrase_tree_get_conjunction_method_alt(SubLObject self) {
        return com.cyc.cycjava.cycl.parse_tree.phrase_tree_find_first_method(self, COORDINATE_WORD_TREE_P, UNPROVIDED);
    }

    /**
     *
     *
     * @return object; the conjunction of this tree if there is one, nil otherwise
     */
    @LispMethod(comment = "@return object; the conjunction of this tree if there is one, nil otherwise")
    public static SubLObject phrase_tree_get_conjunction_method(final SubLObject self) {
        return phrase_tree_find_first_method(self, COORDINATE_WORD_TREE_P, UNPROVIDED);
    }

    /**
     *
     *
     * @return phrase-tree-p; this phrase-tree with lexical entries added
     */
    @LispMethod(comment = "@return phrase-tree-p; this phrase-tree with lexical entries added")
    public static final SubLObject phrase_tree_lexify_int_method_alt(SubLObject self, SubLObject lexicon) {
        {
            SubLObject words = NIL;
            SubLObject orig_spelling = NIL;
            SubLObject normalized_spelling = NIL;
            SubLObject first_word1 = NIL;
            SubLObject first_word2 = NIL;
            SubLObject yield = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            if (NIL != methods.funcall_instance_method_with_0_args(self, ROOT_P)) {
                yield = methods.funcall_instance_method_with_0_args(self, YIELD);
                words = com.cyc.cycjava.cycl.parse_tree.tree_yield_to_words(yield);
                orig_spelling = document.word_string(aref(words, ZERO_INTEGER));
                pos_tagger.tagger_normalize_initial_capitalization(pos_tagger.get_tagger(), words);
                normalized_spelling = document.word_string(aref(words, ZERO_INTEGER));
                first_word1 = yield.first();
                com.cyc.cycjava.cycl.parse_tree.set_parse_tree_string(first_word1, normalized_spelling);
            }
            {
                SubLObject vector_var = daughters;
                SubLObject backwardP_var = NIL;
                SubLObject length = length(vector_var);
                SubLObject v_iteration = NIL;
                for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    {
                        SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                        SubLObject daughter = aref(vector_var, element_num);
                        methods.funcall_instance_method_with_1_args(daughter, LEXIFY_INT, lexicon);
                    }
                }
            }
            if (NIL != methods.funcall_instance_method_with_0_args(self, ROOT_P)) {
                first_word2 = methods.funcall_instance_method_with_0_args(self, YIELD).first();
                if (methods.funcall_instance_method_with_0_args(first_word1, GET_STRING).equalp(methods.funcall_instance_method_with_0_args(first_word2, GET_STRING))) {
                    com.cyc.cycjava.cycl.parse_tree.set_parse_tree_string(first_word2, orig_spelling);
                } else {
                    {
                        SubLObject tokens = string_utilities.split_string(com.cyc.cycjava.cycl.parse_tree.get_parse_tree_string(first_word2), UNPROVIDED);
                        SubLObject new_string = string_utilities.bunge(cons(orig_spelling, tokens.rest()), UNPROVIDED);
                        com.cyc.cycjava.cycl.parse_tree.set_parse_tree_string(first_word2, new_string);
                    }
                }
                com.cyc.cycjava.cycl.parse_tree.process_modifiers(com.cyc.cycjava.cycl.parse_tree.process_abs_index(self));
            }
            return self;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; this phrase-tree with lexical entries added
     */
    @LispMethod(comment = "@return phrase-tree-p; this phrase-tree with lexical entries added")
    public static SubLObject phrase_tree_lexify_int_method(final SubLObject self, final SubLObject lexicon) {
        SubLObject words = NIL;
        SubLObject orig_spelling = NIL;
        SubLObject normalized_spelling = NIL;
        SubLObject first_word1 = NIL;
        SubLObject first_word2 = NIL;
        SubLObject yield = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        if (NIL != methods.funcall_instance_method_with_0_args(self, ROOT_P)) {
            yield = methods.funcall_instance_method_with_0_args(self, YIELD);
            words = tree_yield_to_words(yield);
            orig_spelling = document.word_string(aref(words, ZERO_INTEGER));
            pos_tagger.tagger_normalize_initial_capitalization(pos_tagger.get_tagger(), words);
            normalized_spelling = document.word_string(aref(words, ZERO_INTEGER));
            first_word1 = yield.first();
            set_parse_tree_string(first_word1, normalized_spelling);
        }
        final SubLObject vector_var = daughters;
        final SubLObject backwardP_var = NIL;
        SubLObject length;
        SubLObject v_iteration;
        SubLObject element_num;
        SubLObject daughter;
        for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
            element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
            daughter = aref(vector_var, element_num);
            methods.funcall_instance_method_with_1_args(daughter, LEXIFY_INT, lexicon);
        }
        if (NIL != methods.funcall_instance_method_with_0_args(self, ROOT_P)) {
            first_word2 = methods.funcall_instance_method_with_0_args(self, YIELD).first();
            if (methods.funcall_instance_method_with_0_args(first_word1, GET_STRING).equalp(methods.funcall_instance_method_with_0_args(first_word2, GET_STRING))) {
                set_parse_tree_string(first_word2, orig_spelling);
            } else {
                final SubLObject tokens = string_utilities.split_string(get_parse_tree_string(first_word2), UNPROVIDED);
                final SubLObject new_string = string_utilities.bunge(cons(orig_spelling, tokens.rest()), UNPROVIDED);
                set_parse_tree_string(first_word2, new_string);
            }
            process_modifiers(process_abs_index(self));
        }
        return self;
    }

    public static final SubLObject phrase_tree_retokenize_method_alt(SubLObject self, SubLObject lexicon) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                if (NIL != methods.funcall_instance_method_with_0_args(self, PRETERMINAL_P)) {
                    result = methods.funcall_instance_method_with_2_args(self, RETOKENIZE_INT, vector_utilities.vector_elements(instances.get_slot(self, DAUGHTERS), UNPROVIDED), lexicon);
                } else {
                    if (NIL != methods.funcall_instance_method_with_1_args(lexicon, GET, methods.funcall_instance_method_with_0_args(self, GET_STRING))) {
                        result = methods.funcall_instance_method_with_2_args(self, RETOKENIZE_INT, methods.funcall_instance_method_with_0_args(self, YIELD), lexicon);
                    } else {
                        if ((NIL != $phrase_tree_allow_adjacent_daughters_to_merge$.getDynamicValue(thread)) && (NIL != methods.funcall_instance_method_with_1_args(self, ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY, lexicon))) {
                            {
                                SubLObject datum = methods.funcall_instance_method_with_1_args(self, ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY, lexicon);
                                SubLObject current = datum;
                                SubLObject word_lists = NIL;
                                SubLObject constit_lists = NIL;
                                destructuring_bind_must_consp(current, datum, $list_alt225);
                                word_lists = current.first();
                                current = current.rest();
                                destructuring_bind_must_consp(current, datum, $list_alt225);
                                constit_lists = current.first();
                                current = current.rest();
                                if (NIL == current) {
                                    result = methods.funcall_instance_method_with_3_args(self, PARTIAL_RETOKENIZE_INT, word_lists, constit_lists, lexicon);
                                } else {
                                    cdestructuring_bind_error(datum, $list_alt225);
                                }
                            }
                        } else {
                            result = self;
                        }
                    }
                }
                return result;
            }
        }
    }

    public static SubLObject phrase_tree_retokenize_method(final SubLObject self, final SubLObject lexicon) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        if (NIL != methods.funcall_instance_method_with_0_args(self, PRETERMINAL_P)) {
            result = methods.funcall_instance_method_with_2_args(self, RETOKENIZE_INT, vector_utilities.vector_elements(instances.get_slot(self, DAUGHTERS), UNPROVIDED), lexicon);
        } else
            if (NIL != methods.funcall_instance_method_with_1_args(lexicon, GET, methods.funcall_instance_method_with_0_args(self, GET_STRING))) {
                result = methods.funcall_instance_method_with_2_args(self, RETOKENIZE_INT, methods.funcall_instance_method_with_0_args(self, YIELD), lexicon);
            } else
                if ((NIL != $phrase_tree_allow_adjacent_daughters_to_merge$.getDynamicValue(thread)) && (NIL != methods.funcall_instance_method_with_1_args(self, ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY, lexicon))) {
                    SubLObject current;
                    final SubLObject datum = current = methods.funcall_instance_method_with_1_args(self, ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY, lexicon);
                    SubLObject word_lists = NIL;
                    SubLObject constit_lists = NIL;
                    destructuring_bind_must_consp(current, datum, $list225);
                    word_lists = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list225);
                    constit_lists = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        result = methods.funcall_instance_method_with_3_args(self, PARTIAL_RETOKENIZE_INT, word_lists, constit_lists, lexicon);
                    } else {
                        cdestructuring_bind_error(datum, $list225);
                    }
                } else {
                    result = self;
                }


        return result;
    }

    public static final SubLObject phrase_tree_retokenize_int_method_alt(SubLObject self, SubLObject words, SubLObject lexicon) {
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject v_context = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_context(self);
            try {
                try {
                    {
                        SubLObject tokens = methods.funcall_instance_method_with_1_args(lexicon, LEXIFY_WORDS, words);
                        SubLObject new_daughters = make_vector(length(tokens), UNPROVIDED);
                        SubLObject i = ZERO_INTEGER;
                        SubLObject cdolist_list_var = tokens;
                        SubLObject token = NIL;
                        for (token = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , token = cdolist_list_var.first()) {
                            {
                                SubLObject new_daughter = word_tree.new_word_tree(token.first(), second(token), self, i, NIL, v_context);
                                set_aref(new_daughters, i, new_daughter);
                                i = add(i, ONE_INTEGER);
                            }
                        }
                        com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, new_daughters);
                        sublisp_throw($sym230$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, self);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_context(self, v_context);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym230$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    public static SubLObject phrase_tree_retokenize_int_method(final SubLObject self, final SubLObject words, final SubLObject lexicon) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject v_context = get_parse_tree_context(self);
        try {
            thread.throwStack.push($sym230$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                final SubLObject tokens = methods.funcall_instance_method_with_1_args(lexicon, LEXIFY_WORDS, words);
                final SubLObject new_daughters = make_vector(length(tokens), UNPROVIDED);
                SubLObject i = ZERO_INTEGER;
                SubLObject cdolist_list_var = tokens;
                SubLObject token = NIL;
                token = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject new_daughter = word_tree.new_word_tree(token.first(), second(token), self, i, NIL, v_context);
                    set_aref(new_daughters, i, new_daughter);
                    i = add(i, ONE_INTEGER);
                    cdolist_list_var = cdolist_list_var.rest();
                    token = cdolist_list_var.first();
                } 
                set_phrase_tree_daughters(self, new_daughters);
                sublisp_throw($sym230$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_context(self, v_context);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym230$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     * retokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE
     * that do not get retokenized. Those words will be re-attached to the phrase with no modification.
     */
    @LispMethod(comment = "retokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE\r\nthat do not get retokenized. Those words will be re-attached to the phrase with no modification.\nretokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE\nthat do not get retokenized. Those words will be re-attached to the phrase with no modification.")
    public static final SubLObject phrase_tree_partial_retokenize_int_method_alt(SubLObject self, SubLObject word_lists, SubLObject daughter_lists, SubLObject lexicon) {
        {
            SubLObject new_daughters = NIL;
            SubLObject words = NIL;
            SubLObject words_3 = NIL;
            SubLObject daughters = NIL;
            SubLObject daughters_4 = NIL;
            for (words = word_lists, words_3 = words.first(), daughters = daughter_lists, daughters_4 = daughters.first(); !((NIL == daughters) && (NIL == words)); words = words.rest() , words_3 = words.first() , daughters = daughters.rest() , daughters_4 = daughters.first()) {
                if (NIL != words_3) {
                    {
                        SubLObject tokens = methods.funcall_instance_method_with_1_args(lexicon, LEXIFY_WORDS, words_3);
                        SubLObject i = ZERO_INTEGER;
                        SubLObject cdolist_list_var = tokens;
                        SubLObject token = NIL;
                        for (token = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , token = cdolist_list_var.first()) {
                            new_daughters = cons(word_tree.new_word_tree(token.first(), second(token), self, i, NIL, instances.get_slot(self, CONTEXT)), new_daughters);
                        }
                    }
                } else {
                    {
                        SubLObject items_var = daughters_4;
                        if (items_var.isVector()) {
                            {
                                SubLObject vector_var = daughters_4;
                                SubLObject backwardP_var = NIL;
                                SubLObject length = length(vector_var);
                                SubLObject v_iteration = NIL;
                                for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                    {
                                        SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                        SubLObject item = aref(vector_var, element_num);
                                        new_daughters = cons(item, new_daughters);
                                    }
                                }
                            }
                        } else {
                            {
                                SubLObject cdolist_list_var = daughters_4;
                                SubLObject item = NIL;
                                for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                                    new_daughters = cons(item, new_daughters);
                                }
                            }
                        }
                    }
                }
            }
            {
                SubLObject replacement_daughters = list_utilities.list2vector(nreverse(new_daughters));
                com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, replacement_daughters);
            }
            return self;
        }
    }

    /**
     * retokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE
     * that do not get retokenized. Those words will be re-attached to the phrase with no modification.
     */
    @LispMethod(comment = "retokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE\r\nthat do not get retokenized. Those words will be re-attached to the phrase with no modification.\nretokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE\nthat do not get retokenized. Those words will be re-attached to the phrase with no modification.")
    public static SubLObject phrase_tree_partial_retokenize_int_method(final SubLObject self, final SubLObject word_lists, final SubLObject daughter_lists, final SubLObject lexicon) {
        SubLObject new_daughters = NIL;
        SubLObject words = NIL;
        SubLObject words_$3 = NIL;
        SubLObject daughters = NIL;
        SubLObject daughters_$4 = NIL;
        words = word_lists;
        words_$3 = words.first();
        daughters = daughter_lists;
        daughters_$4 = daughters.first();
        while ((NIL != daughters) || (NIL != words)) {
            if (NIL != words_$3) {
                final SubLObject tokens = methods.funcall_instance_method_with_1_args(lexicon, LEXIFY_WORDS, words_$3);
                final SubLObject i = ZERO_INTEGER;
                SubLObject cdolist_list_var = tokens;
                SubLObject token = NIL;
                token = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    new_daughters = cons(word_tree.new_word_tree(token.first(), second(token), self, i, NIL, instances.get_slot(self, CONTEXT)), new_daughters);
                    cdolist_list_var = cdolist_list_var.rest();
                    token = cdolist_list_var.first();
                } 
            } else {
                final SubLObject items_var = daughters_$4;
                if (items_var.isVector()) {
                    final SubLObject vector_var = items_var;
                    final SubLObject backwardP_var = NIL;
                    SubLObject length;
                    SubLObject v_iteration;
                    SubLObject element_num;
                    SubLObject item;
                    for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                        element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                        item = aref(vector_var, element_num);
                        new_daughters = cons(item, new_daughters);
                    }
                } else {
                    SubLObject cdolist_list_var2 = items_var;
                    SubLObject item2 = NIL;
                    item2 = cdolist_list_var2.first();
                    while (NIL != cdolist_list_var2) {
                        new_daughters = cons(item2, new_daughters);
                        cdolist_list_var2 = cdolist_list_var2.rest();
                        item2 = cdolist_list_var2.first();
                    } 
                }
            }
            words = words.rest();
            words_$3 = words.first();
            daughters = daughters.rest();
            daughters_$4 = daughters.first();
        } 
        final SubLObject replacement_daughters = list_utilities.list2vector(nreverse(new_daughters));
        set_phrase_tree_daughters(self, replacement_daughters);
        return self;
    }

    public static final SubLObject phrase_tree_adjacent_daughters_have_lexical_entry_method_alt(SubLObject self, SubLObject lexicon) {
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            SubLObject string = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_string(self);
            try {
                try {
                    {
                        SubLObject word_lists = NIL;
                        SubLObject daughter_lists = NIL;
                        SubLObject all_daughters = list_utilities.vector2list(com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self));
                        SubLObject daughter_count = length(all_daughters);
                        SubLObject string_5 = methods.funcall_instance_method_with_0_args(self, GET_STRING);
                        SubLObject current_start = ZERO_INTEGER;
                        while (current_start.numL(daughter_count)) {
                            {
                                SubLObject num_daughters_used = NIL;
                                SubLObject start_for_iteration = current_start;
                                if (NIL == num_daughters_used) {
                                    {
                                        SubLObject end_var = start_for_iteration;
                                        SubLObject num = NIL;
                                        for (num = daughter_count; !((NIL != num_daughters_used) || num.numLE(end_var)); num = add(num, MINUS_ONE_INTEGER)) {
                                            {
                                                SubLObject daughters_6 = list_utilities.first_n(num, all_daughters);
                                                SubLObject yield = Mapping.mapcan(symbol_function(COPY_LIST), Mapping.mapcar(PHRASE_TREE_YIELD, daughters_6), EMPTY_SUBL_OBJECT_ARRAY);
                                                SubLObject raw_string = Mapping.mapcar(PHRASE_TREE_STRING, yield);
                                                SubLObject current_string = string_utilities.bunge_according_to_string(raw_string, string_5, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                                if (NIL != methods.funcall_instance_method_with_1_args(lexicon, GET, current_string)) {
                                                    word_lists = cons(yield, word_lists);
                                                    daughter_lists = cons(list_utilities.first_n(num, all_daughters), daughter_lists);
                                                    num_daughters_used = num;
                                                    current_start = add(current_start, num_daughters_used);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (NIL == num_daughters_used) {
                                    word_lists = cons(NIL, word_lists);
                                    daughter_lists = cons(list(nth(current_start, all_daughters)), daughter_lists);
                                    current_start = add(current_start, ONE_INTEGER);
                                }
                            }
                        } 
                        sublisp_throw($sym237$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, list(nreverse(word_lists), nreverse(daughter_lists)));
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_string(self, string);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym237$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    public static SubLObject phrase_tree_adjacent_daughters_have_lexical_entry_method(final SubLObject self, final SubLObject lexicon) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        final SubLObject string = get_parse_tree_string(self);
        try {
            thread.throwStack.push($sym237$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                SubLObject word_lists = NIL;
                SubLObject daughter_lists = NIL;
                final SubLObject all_daughters = list_utilities.vector2list(get_phrase_tree_daughters(self));
                final SubLObject daughter_count = length(all_daughters);
                final SubLObject string_$5 = methods.funcall_instance_method_with_0_args(self, GET_STRING);
                for (SubLObject current_start = ZERO_INTEGER; current_start.numL(daughter_count); current_start = add(current_start, ONE_INTEGER)) {
                    SubLObject num_daughters_used = NIL;
                    final SubLObject start_for_iteration = current_start;
                    if (NIL == num_daughters_used) {
                        SubLObject end_var;
                        SubLObject num;
                        SubLObject daughters_$6;
                        SubLObject yield;
                        SubLObject raw_string;
                        SubLObject current_string;
                        for (end_var = start_for_iteration, num = NIL, num = daughter_count; (NIL == num_daughters_used) && (!num.numLE(end_var)); num = add(num, MINUS_ONE_INTEGER)) {
                            daughters_$6 = list_utilities.first_n(num, all_daughters);
                            yield = Mapping.mapcan(symbol_function(COPY_LIST), Mapping.mapcar(PHRASE_TREE_YIELD, daughters_$6), EMPTY_SUBL_OBJECT_ARRAY);
                            raw_string = Mapping.mapcar(PHRASE_TREE_STRING, yield);
                            current_string = string_utilities.bunge_according_to_string(raw_string, string_$5, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            if (NIL != methods.funcall_instance_method_with_1_args(lexicon, GET, current_string)) {
                                word_lists = cons(yield, word_lists);
                                daughter_lists = cons(list_utilities.first_n(num, all_daughters), daughter_lists);
                                num_daughters_used = num;
                                current_start = add(current_start, num_daughters_used);
                            }
                        }
                    }
                    if (NIL == num_daughters_used) {
                        word_lists = cons(NIL, word_lists);
                        daughter_lists = cons(list(nth(current_start, all_daughters)), daughter_lists);
                    }
                }
                sublisp_throw($sym237$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, list(nreverse(word_lists), nreverse(daughter_lists)));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    set_parse_tree_string(self, string);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym237$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @return listp; the CycL representation of the meaning of this phrase-tree
     */
    @LispMethod(comment = "@return listp; the CycL representation of the meaning of this phrase-tree")
    public static final SubLObject phrase_tree_cyclify_int_method_alt(SubLObject self) {
        {
            SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
            if (NIL != scycls) {
                return scycls;
            }
            {
                SubLObject heads = methods.funcall_instance_method_with_0_args(self, GET_HEADS);
                SubLObject cycls = NIL;
                SubLObject cdolist_list_var = heads;
                SubLObject head = NIL;
                for (head = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head = cdolist_list_var.first()) {
                    cycls = cons(methods.funcall_instance_method_with_0_args(head, CYCLIFY_INT), cycls);
                }
                return Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cycls));
            }
        }
    }

    /**
     *
     *
     * @return listp; the CycL representation of the meaning of this phrase-tree
     */
    @LispMethod(comment = "@return listp; the CycL representation of the meaning of this phrase-tree")
    public static SubLObject phrase_tree_cyclify_int_method(final SubLObject self) {
        final SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
        if (NIL != scycls) {
            return scycls;
        }
        final SubLObject heads = methods.funcall_instance_method_with_0_args(self, GET_HEADS);
        SubLObject cycls = NIL;
        SubLObject cdolist_list_var = heads;
        SubLObject head = NIL;
        head = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            cycls = cons(methods.funcall_instance_method_with_0_args(head, CYCLIFY_INT), cycls);
            cdolist_list_var = cdolist_list_var.rest();
            head = cdolist_list_var.first();
        } 
        return Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cycls));
    }

    /**
     *
     *
     * @return phrase-tree-p; a new phrase tree from PHRASE-EXPRESSION, with mother MOTHER and
    index INDEX
     */
    @LispMethod(comment = "@return phrase-tree-p; a new phrase tree from PHRASE-EXPRESSION, with mother MOTHER and\r\nindex INDEX")
    public static final SubLObject parse_expression_to_phrase_tree_alt(SubLObject phrase_expression, SubLObject mother, SubLObject index, SubLObject lexicon, SubLObject v_context) {
        if (mother == UNPROVIDED) {
            mother = NIL;
        }
        if (index == UNPROVIDED) {
            index = NIL;
        }
        if (lexicon == UNPROVIDED) {
            lexicon = NIL;
        }
        if (v_context == UNPROVIDED) {
            v_context = object.new_class_instance(PARSE_TREE_CONTEXT);
        }
        {
            SubLObject self = object.new_class_instance(com.cyc.cycjava.cycl.parse_tree.tree_for_category(word_tree.parse_expression_category(phrase_expression)));
            SubLObject daughters = make_vector(length(word_tree.phrase_expression_daughters(phrase_expression)), UNPROVIDED);
            SubLObject i = ZERO_INTEGER;
            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_category(self, word_tree.parse_expression_category(phrase_expression));
            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_idx(self, index);
            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_context(self, v_context);
            {
                SubLObject cdolist_list_var = word_tree.phrase_expression_daughters(phrase_expression);
                SubLObject daughter = NIL;
                for (daughter = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , daughter = cdolist_list_var.first()) {
                    set_aref(daughters, i, com.cyc.cycjava.cycl.parse_tree.new_parse_tree_int(daughter, self, i, lexicon, v_context));
                    i = add(i, ONE_INTEGER);
                }
            }
            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
            return self;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; a new phrase tree from PHRASE-EXPRESSION, with mother MOTHER and
    index INDEX
     */
    @LispMethod(comment = "@return phrase-tree-p; a new phrase tree from PHRASE-EXPRESSION, with mother MOTHER and\r\nindex INDEX")
    public static SubLObject parse_expression_to_phrase_tree(final SubLObject phrase_expression, SubLObject mother, SubLObject index, SubLObject lexicon, SubLObject v_context) {
        if (mother == UNPROVIDED) {
            mother = NIL;
        }
        if (index == UNPROVIDED) {
            index = NIL;
        }
        if (lexicon == UNPROVIDED) {
            lexicon = NIL;
        }
        if (v_context == UNPROVIDED) {
            v_context = object.new_class_instance(PARSE_TREE_CONTEXT);
        }
        final SubLObject self = object.new_class_instance(tree_for_category(word_tree.parse_expression_category(phrase_expression)));
        final SubLObject daughters = make_vector(length(word_tree.phrase_expression_daughters(phrase_expression)), UNPROVIDED);
        SubLObject i = ZERO_INTEGER;
        set_parse_tree_category(self, word_tree.parse_expression_category(phrase_expression));
        set_parse_tree_mother(self, mother);
        set_parse_tree_idx(self, index);
        set_parse_tree_context(self, v_context);
        SubLObject cdolist_list_var = word_tree.phrase_expression_daughters(phrase_expression);
        SubLObject daughter = NIL;
        daughter = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            set_aref(daughters, i, new_parse_tree_int(daughter, self, i, lexicon, v_context));
            i = add(i, ONE_INTEGER);
            cdolist_list_var = cdolist_list_var.rest();
            daughter = cdolist_list_var.first();
        } 
        set_phrase_tree_daughters(self, daughters);
        return self;
    }

    /**
     *
     *
     * @return symbolp; the class of part-of-speech tag CATEGORY
     */
    @LispMethod(comment = "@return symbolp; the class of part-of-speech tag CATEGORY")
    public static final SubLObject tree_for_category_alt(SubLObject category) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject tree = assoc(category, $category_to_phrase$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED).rest();
                if (NIL == tree) {
                    tree = PHRASE_TREE;
                }
                return tree;
            }
        }
    }

    /**
     *
     *
     * @return symbolp; the class of part-of-speech tag CATEGORY
     */
    @LispMethod(comment = "@return symbolp; the class of part-of-speech tag CATEGORY")
    public static SubLObject tree_for_category(final SubLObject category) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject tree = assoc(category, $category_to_phrase$.getDynamicValue(thread), UNPROVIDED, UNPROVIDED).rest();
        if (NIL == tree) {
            tree = PHRASE_TREE;
        }
        return tree;
    }

    /**
     * Prints phrase tree PT to STREAM with indentation level
     */
    @LispMethod(comment = "Prints phrase tree PT to STREAM with indentation level")
    public static final SubLObject pprint_phrase_tree_alt(SubLObject pt, SubLObject stream, SubLObject level) {
        {
            SubLObject indent = THREE_INTEGER;
            SubLObject base_column = multiply(level, indent);
            string_utilities.indent(stream, base_column);
            write_string($str_alt246$__P_, stream, UNPROVIDED, UNPROVIDED);
            princ(com.cyc.cycjava.cycl.parse_tree.get_parse_tree_category(pt), stream);
            write_string($str_alt247$__, stream, UNPROVIDED, UNPROVIDED);
            terpri(stream);
            {
                SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(pt);
                SubLObject backwardP_var = NIL;
                SubLObject length = length(vector_var);
                SubLObject v_iteration = NIL;
                for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    {
                        SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                        SubLObject daughter = aref(vector_var, element_num);
                        com.cyc.cycjava.cycl.parse_tree.pprint_parse_tree(daughter, stream, add(level, ONE_INTEGER));
                    }
                }
            }
            string_utilities.indent(stream, base_column);
            write_string($str_alt149$_, stream, UNPROVIDED, UNPROVIDED);
            terpri(stream);
            return pt;
        }
    }

    /**
     * Prints phrase tree PT to STREAM with indentation level
     */
    @LispMethod(comment = "Prints phrase tree PT to STREAM with indentation level")
    public static SubLObject pprint_phrase_tree(final SubLObject pt, final SubLObject stream, final SubLObject level) {
        final SubLObject indent = THREE_INTEGER;
        final SubLObject base_column = multiply(level, indent);
        string_utilities.indent(stream, base_column);
        write_string($str246$__P_, stream, UNPROVIDED, UNPROVIDED);
        princ(get_parse_tree_category(pt), stream);
        write_string($str247$__, stream, UNPROVIDED, UNPROVIDED);
        terpri(stream);
        final SubLObject vector_var = get_phrase_tree_daughters(pt);
        final SubLObject backwardP_var = NIL;
        SubLObject length;
        SubLObject v_iteration;
        SubLObject element_num;
        SubLObject daughter;
        for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
            element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
            daughter = aref(vector_var, element_num);
            pprint_parse_tree(daughter, stream, add(level, ONE_INTEGER));
        }
        string_utilities.indent(stream, base_column);
        write_string($str149$_, stream, UNPROVIDED, UNPROVIDED);
        terpri(stream);
        return pt;
    }

    /**
     *
     *
     * @return stringp; the string of this phrase-tree
     */
    @LispMethod(comment = "@return stringp; the string of this phrase-tree")
    public static final SubLObject phrase_tree_get_string_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject string = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_string(self);
            try {
                try {
                    if (!string.isString()) {
                        if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_get_daughter_method(self, ZERO_INTEGER)) {
                            {
                                SubLObject s = methods.funcall_instance_method_with_0_args(com.cyc.cycjava.cycl.parse_tree.phrase_tree_get_daughter_method(self, ZERO_INTEGER), GET_STRING);
                                SubLObject daughter_string = NIL;
                                SubLObject cdotimes_end_var = subtract(com.cyc.cycjava.cycl.parse_tree.phrase_tree_daughter_count_method(self), ONE_INTEGER);
                                SubLObject i = NIL;
                                for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                                    daughter_string = methods.funcall_instance_method_with_0_args(com.cyc.cycjava.cycl.parse_tree.phrase_tree_get_daughter_method(self, number_utilities.f_1X(i)), GET_STRING);
                                    s = cconcatenate(s, new SubLObject[]{ NIL != com.cyc.cycjava.cycl.parse_tree.contract_stringP(daughter_string) ? ((SubLObject) ($str_alt250$)) : $str_alt251$_, daughter_string });
                                }
                                string = s;
                            }
                        } else {
                            string = $str_alt250$;
                        }
                    }
                    sublisp_throw($sym249$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, string);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_string(self, string);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym249$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @return stringp; the string of this phrase-tree
     */
    @LispMethod(comment = "@return stringp; the string of this phrase-tree")
    public static SubLObject phrase_tree_get_string_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        SubLObject string = get_parse_tree_string(self);
        try {
            thread.throwStack.push($sym249$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                if (!string.isString()) {
                    if (NIL != phrase_tree_get_daughter_method(self, ZERO_INTEGER)) {
                        SubLObject s = methods.funcall_instance_method_with_0_args(phrase_tree_get_daughter_method(self, ZERO_INTEGER), GET_STRING);
                        SubLObject daughter_string = NIL;
                        SubLObject cdotimes_end_var;
                        SubLObject i;
                        for (cdotimes_end_var = subtract(phrase_tree_daughter_count_method(self), ONE_INTEGER), i = NIL, i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                            daughter_string = methods.funcall_instance_method_with_0_args(phrase_tree_get_daughter_method(self, number_utilities.f_1X(i)), GET_STRING);
                            s = cconcatenate(s, new SubLObject[]{ NIL != contract_stringP(daughter_string) ? $str250$ : $$$_, daughter_string });
                        }
                        string = s;
                    } else {
                        string = $str250$;
                    }
                }
                sublisp_throw($sym249$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, string);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_string(self, string);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym249$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    public static final SubLObject phrase_tree_string_alt(SubLObject phrase_tree) {
        return methods.funcall_instance_method_with_0_args(phrase_tree, GET_STRING);
    }

    public static SubLObject phrase_tree_string(final SubLObject phrase_tree) {
        return methods.funcall_instance_method_with_0_args(phrase_tree, GET_STRING);
    }

    /**
     *
     *
     * @return listp; the list of words of this phrase-tree
     */
    @LispMethod(comment = "@return listp; the list of words of this phrase-tree")
    public static final SubLObject phrase_tree_yield_method_alt(SubLObject self) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject yield = NIL;
                SubLObject stack = stacks.create_stack();
                SubLObject order_var = $DFR2L;
                SubLObject possible_orders = list($DFL2R, $DFR2L);
                SubLObject daughter = NIL;
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED)) {
                        Errors.error($str_alt163$_S_is_not_one_of__S, order_var, possible_orders);
                    }
                }
                stacks.stack_push(self, stack);
                while (NIL == stacks.stack_empty_p(stack)) {
                    daughter = stacks.stack_pop(stack);
                    if (NIL != word_tree.word_tree_p(daughter)) {
                        yield = cons(daughter, yield);
                    }
                    if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(daughter)) {
                        {
                            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(daughter);
                            SubLObject backwardP_var = eq(order_var, $DFL2R);
                            SubLObject length = length(vector_var);
                            SubLObject v_iteration = NIL;
                            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                {
                                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                    SubLObject daughter_7 = aref(vector_var, element_num);
                                    stacks.stack_push(daughter_7, stack);
                                }
                            }
                        }
                    }
                } 
                return yield;
            }
        }
    }

    /**
     *
     *
     * @return listp; the list of words of this phrase-tree
     */
    @LispMethod(comment = "@return listp; the list of words of this phrase-tree")
    public static SubLObject phrase_tree_yield_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject yield = NIL;
        final SubLObject stack = stacks.create_stack();
        final SubLObject order_var = $DFR2L;
        final SubLObject possible_orders = list($DFL2R, $DFR2L);
        SubLObject daughter = NIL;
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED))) {
            Errors.error($str163$_S_is_not_one_of__S, order_var, possible_orders);
        }
        stacks.stack_push(self, stack);
        while (NIL == stacks.stack_empty_p(stack)) {
            daughter = stacks.stack_pop(stack);
            if (NIL != word_tree.word_tree_p(daughter)) {
                yield = cons(daughter, yield);
            }
            if (NIL != phrase_tree_p(daughter)) {
                final SubLObject vector_var = get_phrase_tree_daughters(daughter);
                final SubLObject backwardP_var = eq(order_var, $DFL2R);
                SubLObject length;
                SubLObject v_iteration;
                SubLObject element_num;
                SubLObject daughter_$7;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    daughter_$7 = aref(vector_var, element_num);
                    stacks.stack_push(daughter_$7, stack);
                }
            }
        } 
        return yield;
    }

    public static final SubLObject phrase_tree_yield_alt(SubLObject tree) {
        return methods.funcall_instance_method_with_0_args(tree, YIELD);
    }

    public static SubLObject phrase_tree_yield(final SubLObject tree) {
        return methods.funcall_instance_method_with_0_args(tree, YIELD);
    }

    /**
     *
     *
     * @return listp; the parse-expression of this phrase-tree
     * @unknown bertolo
     */
    @LispMethod(comment = "@return listp; the parse-expression of this phrase-tree\r\n@unknown bertolo")
    public static final SubLObject phrase_tree_get_parse_expression_method_alt(SubLObject self) {
        {
            SubLObject expression = list(methods.funcall_instance_method_with_0_args(self, GET_CATEGORY));
            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            SubLObject backwardP_var = NIL;
            SubLObject length = length(vector_var);
            SubLObject v_iteration = NIL;
            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                {
                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                    SubLObject daughter = aref(vector_var, element_num);
                    expression = cons(methods.funcall_instance_method_with_0_args(daughter, GET_PARSE_EXPRESSION), expression);
                }
            }
            return nreverse(expression);
        }
    }

    /**
     *
     *
     * @return listp; the parse-expression of this phrase-tree
     * @unknown bertolo
     */
    @LispMethod(comment = "@return listp; the parse-expression of this phrase-tree\r\n@unknown bertolo")
    public static SubLObject phrase_tree_get_parse_expression_method(final SubLObject self) {
        SubLObject expression = list(methods.funcall_instance_method_with_0_args(self, GET_CATEGORY));
        final SubLObject vector_var = get_phrase_tree_daughters(self);
        final SubLObject backwardP_var = NIL;
        SubLObject length;
        SubLObject v_iteration;
        SubLObject element_num;
        SubLObject daughter;
        for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
            element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
            daughter = aref(vector_var, element_num);
            expression = cons(methods.funcall_instance_method_with_0_args(daughter, GET_PARSE_EXPRESSION), expression);
        }
        return nreverse(expression);
    }

    /**
     *
     *
     * @return parse-tree-p; the heads of this phrase
     */
    @LispMethod(comment = "@return parse-tree-p; the heads of this phrase")
    public static final SubLObject phrase_tree_get_heads_method_alt(SubLObject self) {
        {
            SubLObject heads = NIL;
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS);
            SubLObject head_daughter = NIL;
            for (head_daughter = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head_daughter = cdolist_list_var.first()) {
                {
                    SubLObject cdolist_list_var_8 = methods.funcall_instance_method_with_0_args(head_daughter, GET_HEADS);
                    SubLObject head = NIL;
                    for (head = cdolist_list_var_8.first(); NIL != cdolist_list_var_8; cdolist_list_var_8 = cdolist_list_var_8.rest() , head = cdolist_list_var_8.first()) {
                        heads = cons(head, heads);
                    }
                }
            }
            return nreverse(heads);
        }
    }

    /**
     *
     *
     * @return parse-tree-p; the heads of this phrase
     */
    @LispMethod(comment = "@return parse-tree-p; the heads of this phrase")
    public static SubLObject phrase_tree_get_heads_method(final SubLObject self) {
        SubLObject heads = NIL;
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS);
        SubLObject head_daughter = NIL;
        head_daughter = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$8 = methods.funcall_instance_method_with_0_args(head_daughter, GET_HEADS);
            SubLObject head = NIL;
            head = cdolist_list_var_$8.first();
            while (NIL != cdolist_list_var_$8) {
                heads = cons(head, heads);
                cdolist_list_var_$8 = cdolist_list_var_$8.rest();
                head = cdolist_list_var_$8.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            head_daughter = cdolist_list_var.first();
        } 
        return nreverse(heads);
    }

    /**
     *
     *
     * @return parse-tree-p; the semantic heads of this phrase
     */
    @LispMethod(comment = "@return parse-tree-p; the semantic heads of this phrase")
    public static final SubLObject phrase_tree_get_semantic_heads_method_alt(SubLObject self) {
        {
            SubLObject heads = NIL;
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD_DAUGHTERS);
            SubLObject head_daughter = NIL;
            for (head_daughter = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head_daughter = cdolist_list_var.first()) {
                {
                    SubLObject cdolist_list_var_9 = methods.funcall_instance_method_with_0_args(head_daughter, GET_SEMANTIC_HEADS);
                    SubLObject head = NIL;
                    for (head = cdolist_list_var_9.first(); NIL != cdolist_list_var_9; cdolist_list_var_9 = cdolist_list_var_9.rest() , head = cdolist_list_var_9.first()) {
                        heads = cons(head, heads);
                    }
                }
            }
            return nreverse(heads);
        }
    }

    /**
     *
     *
     * @return parse-tree-p; the semantic heads of this phrase
     */
    @LispMethod(comment = "@return parse-tree-p; the semantic heads of this phrase")
    public static SubLObject phrase_tree_get_semantic_heads_method(final SubLObject self) {
        SubLObject heads = NIL;
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD_DAUGHTERS);
        SubLObject head_daughter = NIL;
        head_daughter = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject cdolist_list_var_$9 = methods.funcall_instance_method_with_0_args(head_daughter, GET_SEMANTIC_HEADS);
            SubLObject head = NIL;
            head = cdolist_list_var_$9.first();
            while (NIL != cdolist_list_var_$9) {
                heads = cons(head, heads);
                cdolist_list_var_$9 = cdolist_list_var_$9.rest();
                head = cdolist_list_var_$9.first();
            } 
            cdolist_list_var = cdolist_list_var.rest();
            head_daughter = cdolist_list_var.first();
        } 
        return nreverse(heads);
    }

    /**
     *
     *
     * @param INDEX
    integerp
     * 		
     * @return parse-tree-p; the INDEX'th daughter of this parse tree, or nil if it doesn't exist
     */
    @LispMethod(comment = "@param INDEX\nintegerp\r\n\t\t\r\n@return parse-tree-p; the INDEX\'th daughter of this parse tree, or nil if it doesn\'t exist")
    public static final SubLObject phrase_tree_get_daughter_method_alt(SubLObject self, SubLObject index) {
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            try {
                try {
                    SubLTrampolineFile.checkType(index, NON_NEGATIVE_INTEGER_P);
                    if (index.numGE(length(daughters))) {
                        sublisp_throw($sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, NIL);
                    }
                    sublisp_throw($sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, aref(daughters, index));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @param INDEX
    integerp
     * 		
     * @return parse-tree-p; the INDEX'th daughter of this parse tree, or nil if it doesn't exist
     */
    @LispMethod(comment = "@param INDEX\nintegerp\r\n\t\t\r\n@return parse-tree-p; the INDEX\'th daughter of this parse tree, or nil if it doesn\'t exist")
    public static SubLObject phrase_tree_get_daughter_method(final SubLObject self, final SubLObject index) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        try {
            thread.throwStack.push($sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                assert NIL != subl_promotions.non_negative_integer_p(index) : "! subl_promotions.non_negative_integer_p(index) " + ("subl_promotions.non_negative_integer_p(index) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(index) ") + index;
                if (index.numGE(length(daughters))) {
                    sublisp_throw($sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, NIL);
                }
                sublisp_throw($sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, aref(daughters, index));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym264$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @param PATH
     * 		listp; a list of non-negative integers
     * @return parse-tree-p; the parse tree a location PATH, starting from the root
    of this phrase-tree, or nil if there is no such parse tree
     */
    @LispMethod(comment = "@param PATH\r\n\t\tlistp; a list of non-negative integers\r\n@return parse-tree-p; the parse tree a location PATH, starting from the root\r\nof this phrase-tree, or nil if there is no such parse tree")
    public static final SubLObject phrase_tree_get_descendant_method_alt(SubLObject self, SubLObject path) {
        SubLTrampolineFile.checkType(path, LISTP);
        {
            SubLObject tree = self;
            SubLObject dtrs = NIL;
            SubLObject cdolist_list_var = path;
            SubLObject i = NIL;
            for (i = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , i = cdolist_list_var.first()) {
                dtrs = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(tree);
                if (i.numGE(length(dtrs))) {
                    return NIL;
                }
                tree = aref(dtrs, i);
            }
            return tree;
        }
    }

    /**
     *
     *
     * @param PATH
     * 		listp; a list of non-negative integers
     * @return parse-tree-p; the parse tree a location PATH, starting from the root
    of this phrase-tree, or nil if there is no such parse tree
     */
    @LispMethod(comment = "@param PATH\r\n\t\tlistp; a list of non-negative integers\r\n@return parse-tree-p; the parse tree a location PATH, starting from the root\r\nof this phrase-tree, or nil if there is no such parse tree")
    public static SubLObject phrase_tree_get_descendant_method(final SubLObject self, final SubLObject path) {
        assert NIL != listp(path) : "! listp(path) " + ("Types.listp(path) " + "CommonSymbols.NIL != Types.listp(path) ") + path;
        SubLObject tree = self;
        SubLObject dtrs = NIL;
        SubLObject cdolist_list_var = path;
        SubLObject i = NIL;
        i = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            dtrs = get_phrase_tree_daughters(tree);
            if (i.numGE(length(dtrs))) {
                return NIL;
            }
            tree = aref(dtrs, i);
            cdolist_list_var = cdolist_list_var.rest();
            i = cdolist_list_var.first();
        } 
        return tree;
    }

    public static final SubLObject proper_subtree_p_alt(SubLObject little_tree, SubLObject big_tree) {
        return makeBoolean((little_tree != big_tree) && (NIL != com.cyc.cycjava.cycl.parse_tree.subtree_p(little_tree, big_tree)));
    }

    public static SubLObject proper_subtree_p(final SubLObject little_tree, final SubLObject big_tree) {
        return makeBoolean((!little_tree.eql(big_tree)) && (NIL != subtree_p(little_tree, big_tree)));
    }

    /**
     *
     *
     * @return boolean; t if LITTLE-TREE is contained within BIG-TREE, nil otherwise
     */
    @LispMethod(comment = "@return boolean; t if LITTLE-TREE is contained within BIG-TREE, nil otherwise")
    public static final SubLObject subtree_p_alt(SubLObject little_tree, SubLObject big_tree) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(little_tree, PARSE_TREE_P);
            SubLTrampolineFile.checkType(big_tree, PARSE_TREE_P);
            {
                SubLObject stack = stacks.create_stack();
                SubLObject order_var = $DFR2L;
                SubLObject possible_orders = list($DFL2R, $DFR2L);
                SubLObject sub = NIL;
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED)) {
                        Errors.error($str_alt163$_S_is_not_one_of__S, order_var, possible_orders);
                    }
                }
                stacks.stack_push(big_tree, stack);
                while (NIL == stacks.stack_empty_p(stack)) {
                    sub = stacks.stack_pop(stack);
                    if (little_tree == sub) {
                        return T;
                    }
                    if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(sub)) {
                        {
                            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(sub);
                            SubLObject backwardP_var = eq(order_var, $DFL2R);
                            SubLObject length = length(vector_var);
                            SubLObject v_iteration = NIL;
                            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                {
                                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                    SubLObject daughter = aref(vector_var, element_num);
                                    stacks.stack_push(daughter, stack);
                                }
                            }
                        }
                    }
                } 
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @return boolean; t if LITTLE-TREE is contained within BIG-TREE, nil otherwise
     */
    @LispMethod(comment = "@return boolean; t if LITTLE-TREE is contained within BIG-TREE, nil otherwise")
    public static SubLObject subtree_p(final SubLObject little_tree, final SubLObject big_tree) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != parse_tree_p(little_tree) : "! parse_tree.parse_tree_p(little_tree) " + ("parse_tree.parse_tree_p(little_tree) " + "CommonSymbols.NIL != parse_tree.parse_tree_p(little_tree) ") + little_tree;
        assert NIL != parse_tree_p(big_tree) : "! parse_tree.parse_tree_p(big_tree) " + ("parse_tree.parse_tree_p(big_tree) " + "CommonSymbols.NIL != parse_tree.parse_tree_p(big_tree) ") + big_tree;
        final SubLObject stack = stacks.create_stack();
        final SubLObject order_var = $DFR2L;
        final SubLObject possible_orders = list($DFL2R, $DFR2L);
        SubLObject sub = NIL;
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED))) {
            Errors.error($str163$_S_is_not_one_of__S, order_var, possible_orders);
        }
        stacks.stack_push(big_tree, stack);
        while (NIL == stacks.stack_empty_p(stack)) {
            sub = stacks.stack_pop(stack);
            if (little_tree.eql(sub)) {
                return T;
            }
            if (NIL == phrase_tree_p(sub)) {
                continue;
            }
            final SubLObject vector_var = get_phrase_tree_daughters(sub);
            final SubLObject backwardP_var = eq(order_var, $DFL2R);
            SubLObject length;
            SubLObject v_iteration;
            SubLObject element_num;
            SubLObject daughter;
            for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                daughter = aref(vector_var, element_num);
                stacks.stack_push(daughter, stack);
            }
        } 
        return NIL;
    }

    /**
     *
     *
     * @param STRING
     * 		stringp; the string to search for
     * @param N
     * 		positive-integer-p; the desired occurrence
     * @return parse-tree-p; the first tree whose string is equal to STRING
     */
    @LispMethod(comment = "@param STRING\r\n\t\tstringp; the string to search for\r\n@param N\r\n\t\tpositive-integer-p; the desired occurrence\r\n@return parse-tree-p; the first tree whose string is equal to STRING")
    public static final SubLObject phrase_tree_search_method_alt(SubLObject self, SubLObject search_string, SubLObject n) {
        if (n == UNPROVIDED) {
            n = ONE_INTEGER;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(search_string, STRINGP);
            SubLTrampolineFile.checkType(n, POSITIVE_INTEGER_P);
            {
                SubLObject m = ZERO_INTEGER;
                SubLObject stack = stacks.create_stack();
                SubLObject order_var = $DFL2R;
                SubLObject possible_orders = list($DFL2R, $DFR2L);
                SubLObject word = NIL;
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED)) {
                        Errors.error($str_alt163$_S_is_not_one_of__S, order_var, possible_orders);
                    }
                }
                stacks.stack_push(self, stack);
                while (NIL == stacks.stack_empty_p(stack)) {
                    word = stacks.stack_pop(stack);
                    if (NIL != word_tree.word_tree_p(word)) {
                        if (search_string.equalp(methods.funcall_instance_method_with_0_args(word, GET_STRING))) {
                            m = add(m, ONE_INTEGER);
                            if (m.numE(n)) {
                                return word;
                            }
                        }
                    }
                    if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(word)) {
                        {
                            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(word);
                            SubLObject backwardP_var = eq(order_var, $DFL2R);
                            SubLObject length = length(vector_var);
                            SubLObject v_iteration = NIL;
                            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                {
                                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                    SubLObject daughter = aref(vector_var, element_num);
                                    stacks.stack_push(daughter, stack);
                                }
                            }
                        }
                    }
                } 
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @param STRING
     * 		stringp; the string to search for
     * @param N
     * 		positive-integer-p; the desired occurrence
     * @return parse-tree-p; the first tree whose string is equal to STRING
     */
    @LispMethod(comment = "@param STRING\r\n\t\tstringp; the string to search for\r\n@param N\r\n\t\tpositive-integer-p; the desired occurrence\r\n@return parse-tree-p; the first tree whose string is equal to STRING")
    public static SubLObject phrase_tree_search_method(final SubLObject self, final SubLObject search_string, SubLObject n) {
        if (n == UNPROVIDED) {
            n = ONE_INTEGER;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != stringp(search_string) : "! stringp(search_string) " + ("Types.stringp(search_string) " + "CommonSymbols.NIL != Types.stringp(search_string) ") + search_string;
        assert NIL != subl_promotions.positive_integer_p(n) : "! subl_promotions.positive_integer_p(n) " + ("subl_promotions.positive_integer_p(n) " + "CommonSymbols.NIL != subl_promotions.positive_integer_p(n) ") + n;
        SubLObject m = ZERO_INTEGER;
        final SubLObject stack = stacks.create_stack();
        final SubLObject order_var = $DFL2R;
        final SubLObject possible_orders = list($DFL2R, $DFR2L);
        SubLObject word = NIL;
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED))) {
            Errors.error($str163$_S_is_not_one_of__S, order_var, possible_orders);
        }
        stacks.stack_push(self, stack);
        while (NIL == stacks.stack_empty_p(stack)) {
            word = stacks.stack_pop(stack);
            if ((NIL != word_tree.word_tree_p(word)) && search_string.equalp(methods.funcall_instance_method_with_0_args(word, GET_STRING))) {
                m = add(m, ONE_INTEGER);
                if (m.numE(n)) {
                    return word;
                }
            }
            if (NIL != phrase_tree_p(word)) {
                final SubLObject vector_var = get_phrase_tree_daughters(word);
                final SubLObject backwardP_var = eq(order_var, $DFL2R);
                SubLObject length;
                SubLObject v_iteration;
                SubLObject element_num;
                SubLObject daughter;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    daughter = aref(vector_var, element_num);
                    stacks.stack_push(daughter, stack);
                }
            }
        } 
        return NIL;
    }

    /**
     *
     *
     * @return non-negative-integerp; the number of daughters of this parse tree
     */
    @LispMethod(comment = "@return non-negative-integerp; the number of daughters of this parse tree")
    public static final SubLObject phrase_tree_daughter_count_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            try {
                try {
                    sublisp_throw($sym280$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, length(daughters));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym280$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @return non-negative-integerp; the number of daughters of this parse tree
     */
    @LispMethod(comment = "@return non-negative-integerp; the number of daughters of this parse tree")
    public static SubLObject phrase_tree_daughter_count_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        try {
            thread.throwStack.push($sym280$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                sublisp_throw($sym280$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, length(daughters));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym280$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @return booleanp; t if this phrase tree has all words as daughters, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase tree has all words as daughters, nil otherwise")
    public static final SubLObject phrase_tree_preterminal_p_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            try {
                try {
                    sublisp_throw($sym283$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, makeBoolean(NIL == list_utilities.find_if_not(WORD_TREE_P, daughters, UNPROVIDED, UNPROVIDED, UNPROVIDED)));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym283$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this phrase tree has all words as daughters, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase tree has all words as daughters, nil otherwise")
    public static SubLObject phrase_tree_preterminal_p_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        try {
            thread.throwStack.push($sym283$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                sublisp_throw($sym283$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, makeBoolean(NIL == list_utilities.find_if_not(WORD_TREE_P, daughters, UNPROVIDED, UNPROVIDED, UNPROVIDED)));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym283$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @return booleanp; t if this phrase tree is multi-headed (a hydra), nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase tree is multi-headed (a hydra), nil otherwise")
    public static final SubLObject phrase_tree_hydra_p_method_alt(SubLObject self) {
        return numG(length(methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS)), ONE_INTEGER);
    }

    /**
     *
     *
     * @return booleanp; t if this phrase tree is multi-headed (a hydra), nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase tree is multi-headed (a hydra), nil otherwise")
    public static SubLObject phrase_tree_hydra_p_method(final SubLObject self) {
        return numG(length(methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTERS)), ONE_INTEGER);
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return listp; a list of all daughters of this phrase-tree which satisfies
    PREDICATE, from left to right
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return listp; a list of all daughters of this phrase-tree which satisfies\r\nPREDICATE, from left to right")
    public static final SubLObject phrase_tree_find_method_alt(SubLObject self, SubLObject predicate, SubLObject start, SubLObject end) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        if (end == UNPROVIDED) {
            end = NIL;
        }
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            try {
                try {
                    {
                        SubLObject daughter = NIL;
                        SubLObject stop = (NIL != end) ? ((SubLObject) (end)) : length(daughters);
                        SubLObject found = NIL;
                        if (start.numG(stop)) {
                            Errors.warn($str_alt292$start__A__after_stop__A__when_fin, new SubLObject[]{ start, stop, predicate, self });
                        }
                        {
                            SubLObject i = NIL;
                            for (i = start; !i.numGE(stop); i = number_utilities.f_1X(i)) {
                                daughter = com.cyc.cycjava.cycl.parse_tree.phrase_tree_get_daughter_method(self, i);
                                if (NIL != funcall(predicate, daughter)) {
                                    found = cons(daughter, found);
                                }
                            }
                        }
                        sublisp_throw($sym291$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, nreverse(found));
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym291$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return listp; a list of all daughters of this phrase-tree which satisfies
    PREDICATE, from left to right
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return listp; a list of all daughters of this phrase-tree which satisfies\r\nPREDICATE, from left to right")
    public static SubLObject phrase_tree_find_method(final SubLObject self, final SubLObject predicate, SubLObject start, SubLObject end) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        if (end == UNPROVIDED) {
            end = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        try {
            thread.throwStack.push($sym291$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                SubLObject daughter = NIL;
                final SubLObject stop = (NIL != end) ? end : length(daughters);
                SubLObject found = NIL;
                if (start.numG(stop)) {
                    Errors.warn($str292$start__A__after_stop__A__when_fin, new SubLObject[]{ start, stop, predicate, self });
                }
                SubLObject i;
                for (i = NIL, i = start; !i.numGE(stop); i = number_utilities.f_1X(i)) {
                    daughter = phrase_tree_get_daughter_method(self, i);
                    if (NIL != funcall(predicate, daughter)) {
                        found = cons(daughter, found);
                    }
                }
                sublisp_throw($sym291$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, nreverse(found));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym291$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return parse-tree-p; the first daughter of this phrase passing PREDICATE, starting
    at daughter START
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return parse-tree-p; the first daughter of this phrase passing PREDICATE, starting\r\nat daughter START")
    public static final SubLObject phrase_tree_find_first_method_alt(SubLObject self, SubLObject predicate, SubLObject start) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            try {
                try {
                    {
                        SubLObject daughter = NIL;
                        SubLObject i = NIL;
                        for (i = start; !i.numGE(length(daughters)); i = number_utilities.f_1X(i)) {
                            daughter = com.cyc.cycjava.cycl.parse_tree.phrase_tree_get_daughter_method(self, i);
                            if (NIL != funcall(predicate, daughter)) {
                                sublisp_throw($sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, daughter);
                            }
                        }
                        sublisp_throw($sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return parse-tree-p; the first daughter of this phrase passing PREDICATE, starting
    at daughter START
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return parse-tree-p; the first daughter of this phrase passing PREDICATE, starting\r\nat daughter START")
    public static SubLObject phrase_tree_find_first_method(final SubLObject self, final SubLObject predicate, SubLObject start) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        try {
            thread.throwStack.push($sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                SubLObject daughter = NIL;
                SubLObject i;
                for (i = NIL, i = start; !i.numGE(length(daughters)); i = number_utilities.f_1X(i)) {
                    daughter = phrase_tree_get_daughter_method(self, i);
                    if (NIL != funcall(predicate, daughter)) {
                        sublisp_throw($sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, daughter);
                    }
                }
                sublisp_throw($sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym297$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return parse-tree-p; the last daughter of this phrase passing PREDICATE, ending
    at daughter END
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return parse-tree-p; the last daughter of this phrase passing PREDICATE, ending\r\nat daughter END")
    public static final SubLObject phrase_tree_find_last_method_alt(SubLObject self, SubLObject predicate, SubLObject end) {
        if (end == UNPROVIDED) {
            end = NIL;
        }
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            try {
                try {
                    {
                        SubLObject daughter = NIL;
                        SubLObject i = NIL;
                        for (i = (NIL != end) ? ((SubLObject) (end)) : number_utilities.f_1_(length(daughters)); !i.isNegative(); i = number_utilities.f_1_(i)) {
                            daughter = com.cyc.cycjava.cycl.parse_tree.phrase_tree_get_daughter_method(self, i);
                            if (NIL != funcall(predicate, daughter)) {
                                sublisp_throw($sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, daughter);
                            }
                        }
                        sublisp_throw($sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, NIL);
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return parse-tree-p; the last daughter of this phrase passing PREDICATE, ending
    at daughter END
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return parse-tree-p; the last daughter of this phrase passing PREDICATE, ending\r\nat daughter END")
    public static SubLObject phrase_tree_find_last_method(final SubLObject self, final SubLObject predicate, SubLObject end) {
        if (end == UNPROVIDED) {
            end = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        try {
            thread.throwStack.push($sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                SubLObject daughter = NIL;
                SubLObject i;
                for (i = NIL, i = (NIL != end) ? end : number_utilities.f_1_(length(daughters)); !i.isNegative(); i = number_utilities.f_1_(i)) {
                    daughter = phrase_tree_get_daughter_method(self, i);
                    if (NIL != funcall(predicate, daughter)) {
                        sublisp_throw($sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, daughter);
                    }
                }
                sublisp_throw($sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym302$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return listp; a list of all daughters of this phrase-tree whose category is
    CATEGORY, from left to right, recursing down into daughters
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return listp; a list of all daughters of this phrase-tree whose category is\r\nCATEGORY, from left to right, recursing down into daughters")
    public static final SubLObject phrase_tree_find_all_method_alt(SubLObject self, SubLObject predicate, SubLObject start, SubLObject end) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        if (end == UNPROVIDED) {
            end = NIL;
        }
        {
            SubLObject catch_var_for_phrase_tree_method = NIL;
            SubLObject daughters = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            try {
                try {
                    {
                        SubLObject daughter = NIL;
                        SubLObject stop = (NIL != end) ? ((SubLObject) (end)) : length(daughters);
                        SubLObject found = NIL;
                        SubLObject i = NIL;
                        for (i = start; !i.numE(stop); i = number_utilities.f_1X(i)) {
                            daughter = com.cyc.cycjava.cycl.parse_tree.phrase_tree_get_daughter_method(self, i);
                            if (NIL != funcall(predicate, daughter)) {
                                found = cons(daughter, found);
                            }
                            if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(daughter)) {
                                {
                                    SubLObject cdolist_list_var = methods.funcall_instance_method_with_1_args(daughter, FIND_ALL, predicate);
                                    SubLObject the_descendant = NIL;
                                    for (the_descendant = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , the_descendant = cdolist_list_var.first()) {
                                        found = cons(the_descendant, found);
                                    }
                                }
                            }
                        }
                        sublisp_throw($sym306$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, nreverse(found));
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_phrase_tree_daughters(self, daughters);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym306$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            }
            return catch_var_for_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @param PREDICATE
    symbolp
     * 		
     * @return listp; a list of all daughters of this phrase-tree whose category is
    CATEGORY, from left to right, recursing down into daughters
     */
    @LispMethod(comment = "@param PREDICATE\nsymbolp\r\n\t\t\r\n@return listp; a list of all daughters of this phrase-tree whose category is\r\nCATEGORY, from left to right, recursing down into daughters")
    public static SubLObject phrase_tree_find_all_method(final SubLObject self, final SubLObject predicate, SubLObject start, SubLObject end) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        if (end == UNPROVIDED) {
            end = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_phrase_tree_method = NIL;
        final SubLObject daughters = get_phrase_tree_daughters(self);
        try {
            thread.throwStack.push($sym306$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
            try {
                SubLObject daughter = NIL;
                final SubLObject stop = (NIL != end) ? end : length(daughters);
                SubLObject found = NIL;
                SubLObject i;
                SubLObject cdolist_list_var;
                SubLObject the_descendant;
                for (i = NIL, i = start; !i.numE(stop); i = number_utilities.f_1X(i)) {
                    daughter = phrase_tree_get_daughter_method(self, i);
                    if (NIL != funcall(predicate, daughter)) {
                        found = cons(daughter, found);
                    }
                    if (NIL != phrase_tree_p(daughter)) {
                        cdolist_list_var = methods.funcall_instance_method_with_1_args(daughter, FIND_ALL, predicate);
                        the_descendant = NIL;
                        the_descendant = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            found = cons(the_descendant, found);
                            cdolist_list_var = cdolist_list_var.rest();
                            the_descendant = cdolist_list_var.first();
                        } 
                    }
                }
                sublisp_throw($sym306$OUTER_CATCH_FOR_PHRASE_TREE_METHOD, nreverse(found));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_phrase_tree_daughters(self, daughters);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym306$OUTER_CATCH_FOR_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_phrase_tree_method;
    }

    /**
     *
     *
     * @param pos-list
     * 		listp; a list of keyword syntactic categories
     * @return listp; a list of all subtrees of self that have any of the categories in CATEGORY-LIST
     */
    @LispMethod(comment = "@param pos-list\r\n\t\tlistp; a list of keyword syntactic categories\r\n@return listp; a list of all subtrees of self that have any of the categories in CATEGORY-LIST")
    public static final SubLObject phrase_tree_find_all_subtrees_of_categories_method_alt(SubLObject self, SubLObject category_list, SubLObject start, SubLObject end) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        if (end == UNPROVIDED) {
            end = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                SubLObject stack = stacks.create_stack();
                SubLObject order_var = $DFR2L;
                SubLObject possible_orders = list($DFL2R, $DFR2L);
                SubLObject subtree = NIL;
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED)) {
                        Errors.error($str_alt163$_S_is_not_one_of__S, order_var, possible_orders);
                    }
                }
                stacks.stack_push(self, stack);
                while (NIL == stacks.stack_empty_p(stack)) {
                    subtree = stacks.stack_pop(stack);
                    {
                        SubLObject cdolist_list_var = category_list;
                        SubLObject cat = NIL;
                        for (cat = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , cat = cdolist_list_var.first()) {
                            if (com.cyc.cycjava.cycl.parse_tree.get_parse_tree_category(subtree) == cat) {
                                result = cons(subtree, result);
                            }
                        }
                    }
                    if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(subtree)) {
                        {
                            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(subtree);
                            SubLObject backwardP_var = eq(order_var, $DFL2R);
                            SubLObject length = length(vector_var);
                            SubLObject v_iteration = NIL;
                            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                {
                                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                    SubLObject daughter = aref(vector_var, element_num);
                                    stacks.stack_push(daughter, stack);
                                }
                            }
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
     * @param pos-list
     * 		listp; a list of keyword syntactic categories
     * @return listp; a list of all subtrees of self that have any of the categories in CATEGORY-LIST
     */
    @LispMethod(comment = "@param pos-list\r\n\t\tlistp; a list of keyword syntactic categories\r\n@return listp; a list of all subtrees of self that have any of the categories in CATEGORY-LIST")
    public static SubLObject phrase_tree_find_all_subtrees_of_categories_method(final SubLObject self, final SubLObject category_list, SubLObject start, SubLObject end) {
        if (start == UNPROVIDED) {
            start = ZERO_INTEGER;
        }
        if (end == UNPROVIDED) {
            end = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        final SubLObject stack = stacks.create_stack();
        final SubLObject order_var = $DFR2L;
        final SubLObject possible_orders = list($DFL2R, $DFR2L);
        SubLObject subtree = NIL;
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED))) {
            Errors.error($str163$_S_is_not_one_of__S, order_var, possible_orders);
        }
        stacks.stack_push(self, stack);
        while (NIL == stacks.stack_empty_p(stack)) {
            subtree = stacks.stack_pop(stack);
            SubLObject cdolist_list_var = category_list;
            SubLObject cat = NIL;
            cat = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (get_parse_tree_category(subtree).eql(cat)) {
                    result = cons(subtree, result);
                }
                cdolist_list_var = cdolist_list_var.rest();
                cat = cdolist_list_var.first();
            } 
            if (NIL != phrase_tree_p(subtree)) {
                final SubLObject vector_var = get_phrase_tree_daughters(subtree);
                final SubLObject backwardP_var = eq(order_var, $DFL2R);
                SubLObject length;
                SubLObject v_iteration;
                SubLObject element_num;
                SubLObject daughter;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    daughter = aref(vector_var, element_num);
                    stacks.stack_push(daughter, stack);
                }
            }
        } 
        return result;
    }

    public static final SubLObject tree_yield_to_words_alt(SubLObject yield) {
        {
            SubLObject words = make_vector(length(yield), UNPROVIDED);
            SubLObject i = ZERO_INTEGER;
            SubLObject cdolist_list_var = yield;
            SubLObject word = NIL;
            for (word = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , word = cdolist_list_var.first()) {
                set_aref(words, i, document.new_word(list($STRING, methods.funcall_instance_method_with_0_args(word, GET_STRING))));
                i = add(i, ONE_INTEGER);
            }
            return words;
        }
    }

    public static SubLObject tree_yield_to_words(final SubLObject yield) {
        final SubLObject words = make_vector(length(yield), UNPROVIDED);
        SubLObject i = ZERO_INTEGER;
        SubLObject cdolist_list_var = yield;
        SubLObject word = NIL;
        word = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            set_aref(words, i, document.new_word(list($STRING, methods.funcall_instance_method_with_0_args(word, GET_STRING))));
            i = add(i, ONE_INTEGER);
            cdolist_list_var = cdolist_list_var.rest();
            word = cdolist_list_var.first();
        } 
        return words;
    }

    /**
     *
     *
     * @return constant-p; the main collection of el formula CYCL
     */
    @LispMethod(comment = "@return constant-p; the main collection of el formula CYCL")
    public static final SubLObject main_collection_alt(SubLObject cycl, SubLObject var) {
        SubLTrampolineFile.checkType(cycl, EL_FORMULA_P);
        {
            SubLObject col = genls.min_col(at_var_types.formula_variable_isa_constraints(var, cycl, $$EverythingPSC, UNPROVIDED), UNPROVIDED, UNPROVIDED);
            return NIL != nart_handles.nart_p(col) ? ((SubLObject) (narts_high.nart_el_formula(col))) : col;
        }
    }

    /**
     *
     *
     * @return constant-p; the main collection of el formula CYCL
     */
    @LispMethod(comment = "@return constant-p; the main collection of el formula CYCL")
    public static SubLObject main_collection(final SubLObject cycl, final SubLObject var) {
        assert NIL != el_formula_p(cycl) : "! el_utilities.el_formula_p(cycl) " + ("el_utilities.el_formula_p(cycl) " + "CommonSymbols.NIL != el_utilities.el_formula_p(cycl) ") + cycl;
        final SubLObject col = genls.min_col(at_var_types.formula_variable_isa_constraints(var, cycl, $$EverythingPSC, UNPROVIDED), UNPROVIDED, UNPROVIDED);
        return NIL != nart_handles.nart_p(col) ? narts_high.nart_el_formula(col) : col;
    }

    /**
     *
     *
     * @param WORD
     * 		stringpp; returns t if WORD is a word that should be directly
     * 		attached to the preceding word, like 's or punctuation
     */
    @LispMethod(comment = "@param WORD\r\n\t\tstringpp; returns t if WORD is a word that should be directly\r\n\t\tattached to the preceding word, like \'s or punctuation")
    public static final SubLObject contract_stringP_alt(SubLObject word) {
        return member(word, $list_alt315, $sym316$STRING_, UNPROVIDED);
    }

    /**
     *
     *
     * @param WORD
     * 		stringpp; returns t if WORD is a word that should be directly
     * 		attached to the preceding word, like 's or punctuation
     */
    @LispMethod(comment = "@param WORD\r\n\t\tstringpp; returns t if WORD is a word that should be directly\r\n\t\tattached to the preceding word, like \'s or punctuation")
    public static SubLObject contract_stringP(final SubLObject word) {
        return member(word, $list315, $sym316$STRING_, UNPROVIDED);
    }

    /**
     *
     *
     * @return word-tree-p; the rightmost nominal multi-word lemma, combining a sequence
    into a single word
    heuristic: find a contiuous string of nominal daughters, all with the same category
     */
    @LispMethod(comment = "@return word-tree-p; the rightmost nominal multi-word lemma, combining a sequence\r\ninto a single word\r\nheuristic: find a contiuous string of nominal daughters, all with the same category")
    public static final SubLObject rightmost_nominal_word_alt(SubLObject np, SubLObject i) {
        if (i == UNPROVIDED) {
            i = number_utilities.f_1_(methods.funcall_instance_method_with_0_args(np, DAUGHTER_COUNT));
        }
        while (!((NIL != word_tree.nominal_word_tree_p(methods.funcall_instance_method_with_1_args(np, GET_DAUGHTER, i))) || i.isZero())) {
            i = subtract(i, ONE_INTEGER);
        } 
        return com.cyc.cycjava.cycl.parse_tree.conflate_nominals(np, i);
    }

    /**
     *
     *
     * @return word-tree-p; the rightmost nominal multi-word lemma, combining a sequence
    into a single word
    heuristic: find a contiuous string of nominal daughters, all with the same category
     */
    @LispMethod(comment = "@return word-tree-p; the rightmost nominal multi-word lemma, combining a sequence\r\ninto a single word\r\nheuristic: find a contiuous string of nominal daughters, all with the same category")
    public static SubLObject rightmost_nominal_word(final SubLObject np, SubLObject i) {
        if (i == UNPROVIDED) {
            i = number_utilities.f_1_(methods.funcall_instance_method_with_0_args(np, DAUGHTER_COUNT));
        }
        while ((NIL == word_tree.nominal_word_tree_p(methods.funcall_instance_method_with_1_args(np, GET_DAUGHTER, i))) && (!i.isZero())) {
            i = subtract(i, ONE_INTEGER);
        } 
        return conflate_nominals(np, i);
    }

    /**
     *
     *
     * @param NP
    nominal-phrase-tree-p;
     * 		
     * @return listp; a list of all conflated nominal words in NP
     */
    @LispMethod(comment = "@param NP\nnominal-phrase-tree-p;\r\n\t\t\r\n@return listp; a list of all conflated nominal words in NP")
    public static final SubLObject nominal_words_alt(SubLObject np) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject words = NIL;
                SubLObject next = number_utilities.f_1_(methods.funcall_instance_method_with_0_args(np, DAUGHTER_COUNT));
                while (NIL != subl_promotions.non_negative_integer_p(next)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject word = com.cyc.cycjava.cycl.parse_tree.rightmost_nominal_word(np, next);
                        SubLObject i = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (NIL != word) {
                            words = cons(word, words);
                            next = number_utilities.f_1_(i);
                        } else {
                            next = MINUS_ONE_INTEGER;
                        }
                    }
                } 
                return words;
            }
        }
    }

    /**
     *
     *
     * @param NP
    nominal-phrase-tree-p;
     * 		
     * @return listp; a list of all conflated nominal words in NP
     */
    @LispMethod(comment = "@param NP\nnominal-phrase-tree-p;\r\n\t\t\r\n@return listp; a list of all conflated nominal words in NP")
    public static SubLObject nominal_words(final SubLObject np) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject words = NIL;
        SubLObject next = number_utilities.f_1_(methods.funcall_instance_method_with_0_args(np, DAUGHTER_COUNT));
        while (NIL != subl_promotions.non_negative_integer_p(next)) {
            thread.resetMultipleValues();
            final SubLObject word = rightmost_nominal_word(np, next);
            final SubLObject i = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != word) {
                words = cons(word, words);
                next = number_utilities.f_1_(i);
            } else {
                next = MINUS_ONE_INTEGER;
            }
        } 
        return words;
    }

    /**
     *
     *
     * @param TREE
     * 		parse-tree-p; the parse tree whose nominals are to be conflated
     * @param I
     * 		non-negative-integer-p; the index into WORDS where to start conflating,
     * 		moving from right to left
     * @return values word-tree-p, non-negative-integer-p; the conflated word and the
    index in WORDS where conflating stopped
     */
    @LispMethod(comment = "@param TREE\r\n\t\tparse-tree-p; the parse tree whose nominals are to be conflated\r\n@param I\r\n\t\tnon-negative-integer-p; the index into WORDS where to start conflating,\r\n\t\tmoving from right to left\r\n@return values word-tree-p, non-negative-integer-p; the conflated word and the\r\nindex in WORDS where conflating stopped")
    public static final SubLObject conflate_nominals_alt(SubLObject tree, SubLObject start) {
        SubLTrampolineFile.checkType(tree, PARSE_TREE_P);
        SubLTrampolineFile.checkType(start, NON_NEGATIVE_INTEGER_P);
        {
            SubLObject word = methods.funcall_instance_method_with_1_args(tree, GET_DAUGHTER, start);
            SubLObject conflated = NIL;
            SubLObject i = start;
            SubLObject category = NIL;
            SubLObject sister = NIL;
            if (NIL != word_tree.nominal_word_tree_p(word)) {
                conflated = cons(word, conflated);
                category = methods.funcall_instance_method_with_0_args(word, GET_CATEGORY);
                while (NIL != subl_promotions.positive_integer_p(i)) {
                    i = subtract(i, ONE_INTEGER);
                    sister = methods.funcall_instance_method_with_1_args(tree, GET_DAUGHTER, i);
                    if (methods.funcall_instance_method_with_0_args(sister, GET_CATEGORY) == category) {
                        conflated = cons(sister, conflated);
                    } else {
                        i = ZERO_INTEGER;
                    }
                } 
            }
            if (length(conflated).numG(ONE_INTEGER)) {
                return values(word_tree.new_multi_noun_tree(conflated), number_utilities.f_1X(subtract(start, length(conflated))));
            } else {
                return values(conflated.first(), number_utilities.f_1X(subtract(start, length(conflated))));
            }
        }
    }

    /**
     *
     *
     * @param TREE
     * 		parse-tree-p; the parse tree whose nominals are to be conflated
     * @param I
     * 		non-negative-integer-p; the index into WORDS where to start conflating,
     * 		moving from right to left
     * @return values word-tree-p, non-negative-integer-p; the conflated word and the
    index in WORDS where conflating stopped
     */
    @LispMethod(comment = "@param TREE\r\n\t\tparse-tree-p; the parse tree whose nominals are to be conflated\r\n@param I\r\n\t\tnon-negative-integer-p; the index into WORDS where to start conflating,\r\n\t\tmoving from right to left\r\n@return values word-tree-p, non-negative-integer-p; the conflated word and the\r\nindex in WORDS where conflating stopped")
    public static SubLObject conflate_nominals(final SubLObject tree, final SubLObject start) {
        assert NIL != parse_tree_p(tree) : "! parse_tree.parse_tree_p(tree) " + ("parse_tree.parse_tree_p(tree) " + "CommonSymbols.NIL != parse_tree.parse_tree_p(tree) ") + tree;
        assert NIL != subl_promotions.non_negative_integer_p(start) : "! subl_promotions.non_negative_integer_p(start) " + ("subl_promotions.non_negative_integer_p(start) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(start) ") + start;
        final SubLObject word = methods.funcall_instance_method_with_1_args(tree, GET_DAUGHTER, start);
        SubLObject conflated = NIL;
        SubLObject i = start;
        SubLObject category = NIL;
        SubLObject sister = NIL;
        if (NIL != word_tree.nominal_word_tree_p(word)) {
            conflated = cons(word, conflated);
            category = methods.funcall_instance_method_with_0_args(word, GET_CATEGORY);
            while (NIL != subl_promotions.positive_integer_p(i)) {
                i = subtract(i, ONE_INTEGER);
                sister = methods.funcall_instance_method_with_1_args(tree, GET_DAUGHTER, i);
                if (methods.funcall_instance_method_with_0_args(sister, GET_CATEGORY).eql(category)) {
                    conflated = cons(sister, conflated);
                } else {
                    i = ZERO_INTEGER;
                }
            } 
        }
        if (length(conflated).numG(ONE_INTEGER)) {
            return values(word_tree.new_multi_noun_tree(conflated), number_utilities.f_1X(subtract(start, length(conflated))));
        }
        return values(conflated.first(), number_utilities.f_1X(subtract(start, length(conflated))));
    }

    /**
     * Store the modifiers of a word with each word
     */
    @LispMethod(comment = "Store the modifiers of a word with each word")
    public static final SubLObject process_modifiers_alt(SubLObject tree) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject stack = stacks.create_stack();
                SubLObject order_var = $DFR2L;
                SubLObject possible_orders = list($DFL2R, $DFR2L);
                SubLObject sub = NIL;
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED)) {
                        Errors.error($str_alt163$_S_is_not_one_of__S, order_var, possible_orders);
                    }
                }
                stacks.stack_push(com.cyc.cycjava.cycl.parse_tree.clear_modifiers(tree), stack);
                while (NIL == stacks.stack_empty_p(stack)) {
                    sub = stacks.stack_pop(stack);
                    {
                        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(sub, GET_MODIFIEDS);
                        SubLObject modified = NIL;
                        for (modified = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , modified = cdolist_list_var.first()) {
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_modifiers(modified, cons(sub, com.cyc.cycjava.cycl.parse_tree.get_parse_tree_modifiers(modified)));
                        }
                    }
                    if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(sub)) {
                        {
                            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(sub);
                            SubLObject backwardP_var = eq(order_var, $DFL2R);
                            SubLObject length = length(vector_var);
                            SubLObject v_iteration = NIL;
                            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                {
                                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                    SubLObject daughter = aref(vector_var, element_num);
                                    stacks.stack_push(daughter, stack);
                                }
                            }
                        }
                    }
                } 
            }
            return tree;
        }
    }

    /**
     * Store the modifiers of a word with each word
     */
    @LispMethod(comment = "Store the modifiers of a word with each word")
    public static SubLObject process_modifiers(final SubLObject tree) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject stack = stacks.create_stack();
        final SubLObject order_var = $DFR2L;
        final SubLObject possible_orders = list($DFL2R, $DFR2L);
        SubLObject sub = NIL;
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED))) {
            Errors.error($str163$_S_is_not_one_of__S, order_var, possible_orders);
        }
        stacks.stack_push(clear_modifiers(tree), stack);
        while (NIL == stacks.stack_empty_p(stack)) {
            sub = stacks.stack_pop(stack);
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(sub, GET_MODIFIEDS);
            SubLObject modified = NIL;
            modified = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                set_parse_tree_modifiers(modified, cons(sub, get_parse_tree_modifiers(modified)));
                cdolist_list_var = cdolist_list_var.rest();
                modified = cdolist_list_var.first();
            } 
            if (NIL != phrase_tree_p(sub)) {
                final SubLObject vector_var = get_phrase_tree_daughters(sub);
                final SubLObject backwardP_var = eq(order_var, $DFL2R);
                SubLObject length;
                SubLObject v_iteration;
                SubLObject element_num;
                SubLObject daughter;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    daughter = aref(vector_var, element_num);
                    stacks.stack_push(daughter, stack);
                }
            }
        } 
        return tree;
    }

    public static final SubLObject process_abs_index_alt(SubLObject tree) {
        {
            SubLObject i = ZERO_INTEGER;
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(tree, YIELD);
            SubLObject word = NIL;
            for (word = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , word = cdolist_list_var.first()) {
                word_tree.set_word_tree_abs_idx(word, i);
                i = add(i, ONE_INTEGER);
            }
            return tree;
        }
    }

    public static SubLObject process_abs_index(final SubLObject tree) {
        SubLObject i = ZERO_INTEGER;
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(tree, YIELD);
        SubLObject word = NIL;
        word = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            word_tree.set_word_tree_abs_idx(word, i);
            i = add(i, ONE_INTEGER);
            cdolist_list_var = cdolist_list_var.rest();
            word = cdolist_list_var.first();
        } 
        return tree;
    }

    /**
     * retag verbs tagged as :AUX if they are in fact main verbs
     */
    @LispMethod(comment = "retag verbs tagged as :AUX if they are in fact main verbs")
    public static final SubLObject retag_aux_verbs_alt(SubLObject tree) {
        {
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(tree, YIELD);
            SubLObject word = NIL;
            for (word = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , word = cdolist_list_var.first()) {
                if (((NIL != word_tree.aux_verb_tree_p(word)) && (NIL == subl_promotions.memberP(methods.funcall_instance_method_with_0_args(word, GET_STRING), word_tree.$to_be_forms$.getGlobalValue(), EQUALP, UNPROVIDED))) && (NIL == methods.funcall_instance_method_with_0_args(word, GET_VERBAL_COMPLEMENT))) {
                    {
                        SubLObject new_word = word_tree.new_word_tree(methods.funcall_instance_method_with_0_args(word, GET_STRING), cyclifier_interface.compute_main_verb_tag(word), methods.funcall_instance_method_with_0_args(word, GET_MOTHER), methods.funcall_instance_method_with_0_args(word, GET_INDEX), NIL, methods.funcall_instance_method_with_0_args(word, GET_CONTEXT));
                        set_aref(instances.get_slot(methods.funcall_instance_method_with_0_args(word, GET_MOTHER), DAUGHTERS), methods.funcall_instance_method_with_0_args(word, GET_INDEX), new_word);
                    }
                }
            }
        }
        return tree;
    }

    /**
     * retag verbs tagged as :AUX if they are in fact main verbs
     */
    @LispMethod(comment = "retag verbs tagged as :AUX if they are in fact main verbs")
    public static SubLObject retag_aux_verbs(final SubLObject tree) {
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(tree, YIELD);
        SubLObject word = NIL;
        word = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (((NIL != word_tree.aux_verb_tree_p(word)) && (NIL == subl_promotions.memberP(methods.funcall_instance_method_with_0_args(word, GET_STRING), word_tree.$to_be_forms$.getGlobalValue(), EQUALP, UNPROVIDED))) && (NIL == methods.funcall_instance_method_with_0_args(word, GET_VERBAL_COMPLEMENT))) {
                final SubLObject new_word = word_tree.new_word_tree(methods.funcall_instance_method_with_0_args(word, GET_STRING), cyclifier_interface.compute_main_verb_tag(word), methods.funcall_instance_method_with_0_args(word, GET_MOTHER), methods.funcall_instance_method_with_0_args(word, GET_INDEX), NIL, methods.funcall_instance_method_with_0_args(word, GET_CONTEXT));
                set_aref(instances.get_slot(methods.funcall_instance_method_with_0_args(word, GET_MOTHER), DAUGHTERS), methods.funcall_instance_method_with_0_args(word, GET_INDEX), new_word);
            }
            cdolist_list_var = cdolist_list_var.rest();
            word = cdolist_list_var.first();
        } 
        return tree;
    }

    /**
     * Reset all modifiers of all constituents in TREE to nil
     */
    @LispMethod(comment = "Reset all modifiers of all constituents in TREE to nil")
    public static final SubLObject clear_modifiers_alt(SubLObject tree) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject stack = stacks.create_stack();
                SubLObject order_var = $DFR2L;
                SubLObject possible_orders = list($DFL2R, $DFR2L);
                SubLObject sub = NIL;
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED)) {
                        Errors.error($str_alt163$_S_is_not_one_of__S, order_var, possible_orders);
                    }
                }
                stacks.stack_push(tree, stack);
                while (NIL == stacks.stack_empty_p(stack)) {
                    sub = stacks.stack_pop(stack);
                    com.cyc.cycjava.cycl.parse_tree.set_parse_tree_modifiers(sub, NIL);
                    if (NIL != com.cyc.cycjava.cycl.parse_tree.phrase_tree_p(sub)) {
                        {
                            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(sub);
                            SubLObject backwardP_var = eq(order_var, $DFL2R);
                            SubLObject length = length(vector_var);
                            SubLObject v_iteration = NIL;
                            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                                {
                                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                                    SubLObject daughter = aref(vector_var, element_num);
                                    stacks.stack_push(daughter, stack);
                                }
                            }
                        }
                    }
                } 
            }
            return tree;
        }
    }

    /**
     * Reset all modifiers of all constituents in TREE to nil
     */
    @LispMethod(comment = "Reset all modifiers of all constituents in TREE to nil")
    public static SubLObject clear_modifiers(final SubLObject tree) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject stack = stacks.create_stack();
        final SubLObject order_var = $DFR2L;
        final SubLObject possible_orders = list($DFL2R, $DFR2L);
        SubLObject sub = NIL;
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == subl_promotions.memberP(order_var, possible_orders, symbol_function(EQ), UNPROVIDED))) {
            Errors.error($str163$_S_is_not_one_of__S, order_var, possible_orders);
        }
        stacks.stack_push(tree, stack);
        while (NIL == stacks.stack_empty_p(stack)) {
            sub = stacks.stack_pop(stack);
            set_parse_tree_modifiers(sub, NIL);
            if (NIL != phrase_tree_p(sub)) {
                final SubLObject vector_var = get_phrase_tree_daughters(sub);
                final SubLObject backwardP_var = eq(order_var, $DFL2R);
                SubLObject length;
                SubLObject v_iteration;
                SubLObject element_num;
                SubLObject daughter;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    daughter = aref(vector_var, element_num);
                    stacks.stack_push(daughter, stack);
                }
            }
        } 
        return tree;
    }

    /**
     *
     *
     * @param FORMULA
    el-formula
     * 		
     * @param VARIABLES
    listp
     * 		
     * @return el-formula; FORMULA with each of its variables existentially quantified
     */
    @LispMethod(comment = "@param FORMULA\nel-formula\r\n\t\t\r\n@param VARIABLES\nlistp\r\n\t\t\r\n@return el-formula; FORMULA with each of its variables existentially quantified")
    public static final SubLObject quantify_existentially_alt(SubLObject formula, SubLObject v_variables) {
        if (v_variables == UNPROVIDED) {
            v_variables = sentence_free_variables(formula, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        {
            SubLObject cycl = formula;
            SubLObject cdolist_list_var = v_variables;
            SubLObject var = NIL;
            for (var = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , var = cdolist_list_var.first()) {
                cycl = list($$thereExists, var, cycl);
            }
            return cycl;
        }
    }

    /**
     *
     *
     * @param FORMULA
    el-formula
     * 		
     * @param VARIABLES
    listp
     * 		
     * @return el-formula; FORMULA with each of its variables existentially quantified
     */
    @LispMethod(comment = "@param FORMULA\nel-formula\r\n\t\t\r\n@param VARIABLES\nlistp\r\n\t\t\r\n@return el-formula; FORMULA with each of its variables existentially quantified")
    public static SubLObject quantify_existentially(final SubLObject formula, SubLObject v_variables) {
        if (v_variables == UNPROVIDED) {
            v_variables = sentence_free_variables(formula, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        SubLObject cycl = formula;
        SubLObject cdolist_list_var = v_variables;
        SubLObject var = NIL;
        var = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            cycl = list($$thereExists, var, cycl);
            cdolist_list_var = cdolist_list_var.rest();
            var = cdolist_list_var.first();
        } 
        return cycl;
    }

    /**
     *
     *
     * @param TREE
    parse-tree
     * 		
     * @return listp; a list of all variables being asked about in TREE
     */
    @LispMethod(comment = "@param TREE\nparse-tree\r\n\t\t\r\n@return listp; a list of all variables being asked about in TREE")
    public static final SubLObject question_referents_alt(SubLObject tree) {
        SubLTrampolineFile.checkType(tree, PARSE_TREE_P);
        {
            SubLObject words = methods.funcall_instance_method_with_0_args(tree, YIELD);
            SubLObject word_string = NIL;
            SubLObject i = ZERO_INTEGER;
            SubLObject question_referents = NIL;
            SubLObject cdolist_list_var = words;
            SubLObject word = NIL;
            for (word = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , word = cdolist_list_var.first()) {
                word_string = methods.funcall_instance_method_with_0_args(word, GET_STRING);
                i = add(i, ONE_INTEGER);
                if ((((NIL != methods.funcall_instance_method_with_0_args(word, GET_MOTHER)) && (NIL != methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_0_args(word, GET_MOTHER), GET_MOTHER))) && (NIL == word_tree.relative_clause_p(methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_0_args(word, GET_MOTHER), GET_MOTHER)))) && (NIL != member(word_string, $list_alt319, EQUALP, UNPROVIDED))) {
                    question_referents = append(methods.funcall_instance_method_with_0_args(word, GET_REFS), question_referents);
                } else {
                    if ((NIL != subl_promotions.memberP(word_string, $list_alt320, EQUALP, UNPROVIDED)) && (NIL != com.cyc.cycjava.cycl.parse_tree.nominal_tree_p(nth(i, words)))) {
                        question_referents = append(methods.funcall_instance_method_with_0_args(nth(i, words), GET_REFS), question_referents);
                    } else {
                        if (word_string.equalp($$$what)) {
                            question_referents = append(methods.funcall_instance_method_with_0_args(word, GET_REFS), question_referents);
                        }
                    }
                }
            }
            return question_referents;
        }
    }

    /**
     *
     *
     * @param TREE
    parse-tree
     * 		
     * @return listp; a list of all variables being asked about in TREE
     */
    @LispMethod(comment = "@param TREE\nparse-tree\r\n\t\t\r\n@return listp; a list of all variables being asked about in TREE")
    public static SubLObject question_referents(final SubLObject tree) {
        assert NIL != parse_tree_p(tree) : "! parse_tree.parse_tree_p(tree) " + ("parse_tree.parse_tree_p(tree) " + "CommonSymbols.NIL != parse_tree.parse_tree_p(tree) ") + tree;
        final SubLObject words = methods.funcall_instance_method_with_0_args(tree, YIELD);
        SubLObject word_string = NIL;
        SubLObject i = ZERO_INTEGER;
        SubLObject question_referents = NIL;
        SubLObject cdolist_list_var = words;
        SubLObject word = NIL;
        word = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            word_string = methods.funcall_instance_method_with_0_args(word, GET_STRING);
            i = add(i, ONE_INTEGER);
            if ((((NIL != methods.funcall_instance_method_with_0_args(word, GET_MOTHER)) && (NIL != methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_0_args(word, GET_MOTHER), GET_MOTHER))) && (NIL == word_tree.relative_clause_p(methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_0_args(word, GET_MOTHER), GET_MOTHER)))) && (NIL != member(word_string, $list319, EQUALP, UNPROVIDED))) {
                question_referents = append(methods.funcall_instance_method_with_0_args(word, GET_REFS), question_referents);
            } else
                if ((NIL != subl_promotions.memberP(word_string, $list320, EQUALP, UNPROVIDED)) && (NIL != nominal_tree_p(nth(i, words)))) {
                    question_referents = append(methods.funcall_instance_method_with_0_args(nth(i, words), GET_REFS), question_referents);
                } else
                    if (word_string.equalp($$$what)) {
                        question_referents = append(methods.funcall_instance_method_with_0_args(word, GET_REFS), question_referents);
                    }


            cdolist_list_var = cdolist_list_var.rest();
            word = cdolist_list_var.first();
        } 
        return question_referents;
    }

    public static final SubLObject subloop_reserved_initialize_nominal_phrase_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_nominal_phrase_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_nominal_phrase_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_nominal_phrase_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject nominal_phrase_tree_p_alt(SubLObject nominal_phrase_tree) {
        return classes.subloop_instanceof_class(nominal_phrase_tree, NOMINAL_PHRASE_TREE);
    }

    public static SubLObject nominal_phrase_tree_p(final SubLObject nominal_phrase_tree) {
        return classes.subloop_instanceof_class(nominal_phrase_tree, NOMINAL_PHRASE_TREE);
    }

    /**
     *
     *
     * @return word-tree-p; the determiner of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the determiner of this NP, or nil if there is none")
    public static final SubLObject nominal_phrase_tree_get_determiner_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_DETERMINER))) : NIL;
        }
    }

    /**
     *
     *
     * @return word-tree-p; the determiner of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the determiner of this NP, or nil if there is none")
    public static SubLObject nominal_phrase_tree_get_determiner_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_DETERMINER) : NIL;
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static final SubLObject nominal_phrase_tree_get_overt_quantifier_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_OVERT_QUANTIFIER))) : NIL;
        }
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static SubLObject nominal_phrase_tree_get_overt_quantifier_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_OVERT_QUANTIFIER) : NIL;
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static final SubLObject nominal_phrase_tree_get_quantifier_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_QUANTIFIER))) : NIL;
        }
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static SubLObject nominal_phrase_tree_get_quantifier_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_QUANTIFIER) : NIL;
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static final SubLObject nominal_phrase_tree_get_possessor_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_POSSESSOR))) : NIL;
        }
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static SubLObject nominal_phrase_tree_get_possessor_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_POSSESSOR) : NIL;
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static final SubLObject nominal_phrase_tree_get_specifier_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_SPECIFIER))) : NIL;
        }
    }

    /**
     *
     *
     * @return word-tree-p; the quantifier of this NP, or nil if there is none
     */
    @LispMethod(comment = "@return word-tree-p; the quantifier of this NP, or nil if there is none")
    public static SubLObject nominal_phrase_tree_get_specifier_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_SPECIFIER) : NIL;
    }

    /**
     *
     *
     * @return boolean; t if this phrase is a possessive, nil otherwise
     */
    @LispMethod(comment = "@return boolean; t if this phrase is a possessive, nil otherwise")
    public static final SubLObject nominal_phrase_tree_possessive_p_method_alt(SubLObject self) {
        return word_tree.possessive_word_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, number_utilities.f_1_(methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT))));
    }

    /**
     *
     *
     * @return boolean; t if this phrase is a possessive, nil otherwise
     */
    @LispMethod(comment = "@return boolean; t if this phrase is a possessive, nil otherwise")
    public static SubLObject nominal_phrase_tree_possessive_p_method(final SubLObject self) {
        return word_tree.possessive_word_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, number_utilities.f_1_(methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT))));
    }

    /**
     *
     *
     * @return booleanp; t if this tree is temporal, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is temporal, nil otherwise")
    public static final SubLObject nominal_phrase_tree_temporal_tree_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            if (NIL != head) {
                return methods.funcall_instance_method_with_0_args(head, TEMPORAL_TREE_P);
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this tree is temporal, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is temporal, nil otherwise")
    public static SubLObject nominal_phrase_tree_temporal_tree_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        if (NIL != head) {
            return methods.funcall_instance_method_with_0_args(head, TEMPORAL_TREE_P);
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this tree is a definite description, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is a definite description, nil otherwise")
    public static final SubLObject nominal_phrase_tree_definite_description_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return (NIL != head) && (NIL != com.cyc.cycjava.cycl.parse_tree.nominal_tree_p(head)) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, DEFINITE_DESCRIPTION_P))) : NIL;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this tree is a definite description, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is a definite description, nil otherwise")
    public static SubLObject nominal_phrase_tree_definite_description_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return (NIL != head) && (NIL != nominal_tree_p(head)) ? methods.funcall_instance_method_with_0_args(head, DEFINITE_DESCRIPTION_P) : NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this tree is a name, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is a name, nil otherwise")
    public static final SubLObject nominal_phrase_tree_name_tree_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return makeBoolean((NIL != head) && (NIL != word_tree.name_word_tree_p(head)));
        }
    }

    /**
     *
     *
     * @return booleanp; t if this tree is a name, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is a name, nil otherwise")
    public static SubLObject nominal_phrase_tree_name_tree_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return makeBoolean((NIL != head) && (NIL != word_tree.name_word_tree_p(head)));
    }

    /**
     *
     *
     * @return listp; a list of genders of this np with possible gender values
    :FEMININE, :MASCULINE and :NEUTER
     */
    @LispMethod(comment = "@return listp; a list of genders of this np with possible gender values\r\n:FEMININE, :MASCULINE and :NEUTER")
    public static final SubLObject nominal_phrase_tree_get_gender_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_GENDER))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of genders of this np with possible gender values
    :FEMININE, :MASCULINE and :NEUTER
     */
    @LispMethod(comment = "@return listp; a list of genders of this np with possible gender values\r\n:FEMININE, :MASCULINE and :NEUTER")
    public static SubLObject nominal_phrase_tree_get_gender_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_GENDER) : NIL;
    }

    /**
     *
     *
     * @return listp; a list of numbers of this np with possible number values
    :SINGULAR and :PLURAL
     */
    @LispMethod(comment = "@return listp; a list of numbers of this np with possible number values\r\n:SINGULAR and :PLURAL")
    public static final SubLObject nominal_phrase_tree_get_number_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_NUMBER))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of numbers of this np with possible number values
    :SINGULAR and :PLURAL
     */
    @LispMethod(comment = "@return listp; a list of numbers of this np with possible number values\r\n:SINGULAR and :PLURAL")
    public static SubLObject nominal_phrase_tree_get_number_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_NUMBER) : NIL;
    }

    /**
     *
     *
     * @return numberp; the person of this noun; 1, 2, or 3
     */
    @LispMethod(comment = "@return numberp; the person of this noun; 1, 2, or 3")
    public static final SubLObject nominal_phrase_tree_get_person_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_PERSON))) : NIL;
        }
    }

    /**
     *
     *
     * @return numberp; the person of this noun; 1, 2, or 3
     */
    @LispMethod(comment = "@return numberp; the person of this noun; 1, 2, or 3")
    public static SubLObject nominal_phrase_tree_get_person_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_PERSON) : NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this tree is singular, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is singular, nil otherwise")
    public static final SubLObject nominal_phrase_tree_singular_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, SINGULAR_P))) : NIL;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this tree is singular, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is singular, nil otherwise")
    public static SubLObject nominal_phrase_tree_singular_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, SINGULAR_P) : NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this tree is plural, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is plural, nil otherwise")
    public static final SubLObject nominal_phrase_tree_plural_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, PLURAL_P))) : NIL;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this tree is plural, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this tree is plural, nil otherwise")
    public static SubLObject nominal_phrase_tree_plural_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, PLURAL_P) : NIL;
    }

    /**
     *
     *
     * @return parse-tree-p; a version of this np in which multi-word
    lemmata have been conflated into single words and each word has
    lexical entries added
     * @unknown this tree's daughters will be destructively modified
     */
    @LispMethod(comment = "@return parse-tree-p; a version of this np in which multi-word\r\nlemmata have been conflated into single words and each word has\r\nlexical entries added\r\n@unknown this tree\'s daughters will be destructively modified")
    public static final SubLObject nominal_phrase_tree_lexify_int_method_alt(SubLObject self, SubLObject lexicon) {
        SubLTrampolineFile.checkType(lexicon, ABSTRACT_LEXICON_P);
        {
            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            SubLObject backwardP_var = NIL;
            SubLObject length = length(vector_var);
            SubLObject v_iteration = NIL;
            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                {
                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                    SubLObject daughter = aref(vector_var, element_num);
                    methods.funcall_instance_method_with_1_args(daughter, LEXIFY_INT, lexicon);
                }
            }
        }
        if (NIL != methods.funcall_instance_method_with_0_args(self, DEFINITE_DESCRIPTION_P)) {
            {
                SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
                SubLObject augmented_lexes = NIL;
                if (NIL != head) {
                    {
                        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(head, GET_RANKED_LEXES);
                        SubLObject rle = NIL;
                        for (rle = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , rle = cdolist_list_var.first()) {
                            {
                                SubLObject lex = (NIL != rle) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(cyclifier.rle_lex(rle), COPY))) : NIL;
                                if (NIL == com.cyc.cycjava.cycl.parse_tree.introduces_termP(lex)) {
                                    {
                                        SubLObject instance = methods.funcall_instance_method_with_1_args(methods.funcall_instance_method_with_0_args(self, GET_CONTEXT), GET_INSTANCE, self);
                                        SubLObject semtrans = methods.funcall_instance_method_with_1_args(lex, GET, $SEMTRANS);
                                        methods.funcall_instance_method_with_2_args(lex, SET, $SEMTRANS, simplifier.conjoin(list(semtrans, list($$equals, $NOUN, instance)), UNPROVIDED));
                                        cyclifier.rle_set_confidence(rle, ONE_INTEGER);
                                    }
                                }
                                augmented_lexes = cons(rle, augmented_lexes);
                            }
                        }
                    }
                    instances.set_slot(head, LEXES, augmented_lexes);
                }
            }
        }
        return self;
    }

    /**
     *
     *
     * @return parse-tree-p; a version of this np in which multi-word
    lemmata have been conflated into single words and each word has
    lexical entries added
     * @unknown this tree's daughters will be destructively modified
     */
    @LispMethod(comment = "@return parse-tree-p; a version of this np in which multi-word\r\nlemmata have been conflated into single words and each word has\r\nlexical entries added\r\n@unknown this tree\'s daughters will be destructively modified")
    public static SubLObject nominal_phrase_tree_lexify_int_method(final SubLObject self, final SubLObject lexicon) {
        assert NIL != abstract_lexicon.abstract_lexicon_p(lexicon) : "! abstract_lexicon.abstract_lexicon_p(lexicon) " + ("abstract_lexicon.abstract_lexicon_p(lexicon) " + "CommonSymbols.NIL != abstract_lexicon.abstract_lexicon_p(lexicon) ") + lexicon;
        final SubLObject vector_var = get_phrase_tree_daughters(self);
        final SubLObject backwardP_var = NIL;
        SubLObject length;
        SubLObject v_iteration;
        SubLObject element_num;
        SubLObject daughter;
        for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
            element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
            daughter = aref(vector_var, element_num);
            methods.funcall_instance_method_with_1_args(daughter, LEXIFY_INT, lexicon);
        }
        if (NIL != methods.funcall_instance_method_with_0_args(self, DEFINITE_DESCRIPTION_P)) {
            final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            SubLObject augmented_lexes = NIL;
            if (NIL != head) {
                SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(head, GET_RANKED_LEXES);
                SubLObject rle = NIL;
                rle = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject lex = (NIL != rle) ? methods.funcall_instance_method_with_0_args(cyclifier.rle_lex(rle), COPY) : NIL;
                    if (NIL == introduces_termP(lex)) {
                        final SubLObject instance = methods.funcall_instance_method_with_1_args(methods.funcall_instance_method_with_0_args(self, GET_CONTEXT), GET_INSTANCE, self);
                        final SubLObject semtrans = methods.funcall_instance_method_with_1_args(lex, GET, $SEMTRANS);
                        methods.funcall_instance_method_with_2_args(lex, SET, $SEMTRANS, simplifier.conjoin(list(semtrans, list($$equals, $NOUN, instance)), UNPROVIDED));
                        cyclifier.rle_set_confidence(rle, ONE_INTEGER);
                    }
                    augmented_lexes = cons(rle, augmented_lexes);
                    cdolist_list_var = cdolist_list_var.rest();
                    rle = cdolist_list_var.first();
                } 
                instances.set_slot(head, LEXES, augmented_lexes);
            }
        }
        return self;
    }

    public static final SubLObject presupposing_tree_p_alt(SubLObject tree) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(tree, GET_HEAD);
            return makeBoolean((((NIL != head) && (NIL != word_tree.name_word_tree_p(head))) && (NIL != methods.funcall_instance_method_with_0_args(head, SINGULAR_P))) || (NIL != methods.funcall_instance_method_with_0_args(tree, DEFINITE_DESCRIPTION_P)));
        }
    }

    public static SubLObject presupposing_tree_p(final SubLObject tree) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(tree, GET_HEAD);
        return makeBoolean((((NIL != head) && (NIL != word_tree.name_word_tree_p(head))) && (NIL != methods.funcall_instance_method_with_0_args(head, SINGULAR_P))) || (NIL != methods.funcall_instance_method_with_0_args(tree, DEFINITE_DESCRIPTION_P)));
    }

    public static final SubLObject introduces_termP_alt(SubLObject lex) {
        {
            SubLObject semtrans = methods.funcall_instance_method_with_1_args(lex, GET, $SEMTRANS);
            return makeBoolean(cycl_utilities.formula_operator(semtrans).eql($$equals) && cycl_utilities.formula_arg1(semtrans, UNPROVIDED).eql($NOUN));
        }
    }

    public static SubLObject introduces_termP(final SubLObject lex) {
        final SubLObject semtrans = methods.funcall_instance_method_with_1_args(lex, GET, $SEMTRANS);
        return makeBoolean(cycl_utilities.formula_operator(semtrans).eql($$equals) && cycl_utilities.formula_arg1(semtrans, UNPROVIDED).eql($NOUN));
    }

    public static final SubLObject lex_entry_sem_equal_alt(SubLObject lex1, SubLObject lex2) {
        return equal(methods.funcall_instance_method_with_1_args(lex1, GET, $SEMTRANS), methods.funcall_instance_method_with_1_args(lex2, GET, $SEMTRANS));
    }

    public static SubLObject lex_entry_sem_equal(final SubLObject lex1, final SubLObject lex2) {
        return equal(methods.funcall_instance_method_with_1_args(lex1, GET, $SEMTRANS), methods.funcall_instance_method_with_1_args(lex2, GET, $SEMTRANS));
    }

    /**
     *
     *
     * @return listp; a list of all head daughters of this phrase
     */
    @LispMethod(comment = "@return listp; a list of all head daughters of this phrase")
    public static final SubLObject nominal_phrase_tree_get_head_daughters_method_alt(SubLObject self) {
        if ((methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(TWO_INTEGER) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER), GET_CATEGORY).eql($kw208$_)) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ONE_INTEGER), GET_CATEGORY).eql($CD)) {
            return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER));
        } else {
            if (methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(ONE_INTEGER) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER), GET_CATEGORY).eql($CD)) {
                return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER));
            } else {
                if (NIL != methods.funcall_instance_method_with_0_args(self, COORDINATE_PHRASE_P)) {
                    return methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P);
                } else {
                    if ((methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(TWO_INTEGER) && (NIL != word_tree.vbg_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ONE_INTEGER)))) && (NIL != word_tree.determiner_word_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER)))) {
                        return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ONE_INTEGER));
                    } else {
                        if (NIL != methods.funcall_instance_method_with_0_args(self, PRETERMINAL_P)) {
                            {
                                SubLObject head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_LAST, NOMINAL_WORD_TREE_P);
                                return NIL != head_daughter ? ((SubLObject) (list(head_daughter))) : NIL;
                            }
                        } else {
                            {
                                SubLObject head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_LAST, NOMINAL_WORD_TREE_P);
                                if (NIL != head_daughter) {
                                    return list(head_daughter);
                                } else {
                                    head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_FIRST, NOMINAL_PHRASE_TREE_P);
                                    return NIL != head_daughter ? ((SubLObject) (list(head_daughter))) : NIL;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *
     *
     * @return listp; a list of all head daughters of this phrase
     */
    @LispMethod(comment = "@return listp; a list of all head daughters of this phrase")
    public static SubLObject nominal_phrase_tree_get_head_daughters_method(final SubLObject self) {
        if ((methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(TWO_INTEGER) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER), GET_CATEGORY).eql($kw208$_)) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ONE_INTEGER), GET_CATEGORY).eql($CD)) {
            return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER));
        }
        if (methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(ONE_INTEGER) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER), GET_CATEGORY).eql($CD)) {
            return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER));
        }
        if (NIL != methods.funcall_instance_method_with_0_args(self, COORDINATE_PHRASE_P)) {
            return methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P);
        }
        if ((methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(TWO_INTEGER) && (NIL != word_tree.vbg_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ONE_INTEGER)))) && (NIL != word_tree.determiner_word_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER)))) {
            return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ONE_INTEGER));
        }
        if (NIL != methods.funcall_instance_method_with_0_args(self, PRETERMINAL_P)) {
            final SubLObject head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_LAST, NOMINAL_WORD_TREE_P);
            return NIL != head_daughter ? list(head_daughter) : NIL;
        }
        SubLObject head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_LAST, NOMINAL_WORD_TREE_P);
        if (NIL != head_daughter) {
            return list(head_daughter);
        }
        head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_FIRST, NOMINAL_PHRASE_TREE_P);
        return NIL != head_daughter ? list(head_daughter) : NIL;
    }

    public static final SubLObject subloop_reserved_initialize_np_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_np_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_np_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_np_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject np_tree_p_alt(SubLObject np_tree) {
        return classes.subloop_instanceof_class(np_tree, NP_TREE);
    }

    public static SubLObject np_tree_p(final SubLObject np_tree) {
        return classes.subloop_instanceof_class(np_tree, NP_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_npp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_npp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_npp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_npp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject npp_tree_p_alt(SubLObject npp_tree) {
        return classes.subloop_instanceof_class(npp_tree, NPP_TREE);
    }

    public static SubLObject npp_tree_p(final SubLObject npp_tree) {
        return classes.subloop_instanceof_class(npp_tree, NPP_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_nx_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_nx_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_nx_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_nx_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject nx_tree_p_alt(SubLObject nx_tree) {
        return classes.subloop_instanceof_class(nx_tree, NX_TREE);
    }

    public static SubLObject nx_tree_p(final SubLObject nx_tree) {
        return classes.subloop_instanceof_class(nx_tree, NX_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_pnp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_pnp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_pnp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_pnp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject pnp_tree_p_alt(SubLObject pnp_tree) {
        return classes.subloop_instanceof_class(pnp_tree, PNP_TREE);
    }

    public static SubLObject pnp_tree_p(final SubLObject pnp_tree) {
        return classes.subloop_instanceof_class(pnp_tree, PNP_TREE);
    }

    /**
     *
     *
     * @return listp; the head daughters of this pnp tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this pnp tree")
    public static final SubLObject pnp_tree_get_head_daughters_method_alt(SubLObject self) {
        return list(methods.funcall_instance_method_with_1_args(self, FIND_LAST, NOMINAL_TREE_P));
    }

    /**
     *
     *
     * @return listp; the head daughters of this pnp tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this pnp tree")
    public static SubLObject pnp_tree_get_head_daughters_method(final SubLObject self) {
        return list(methods.funcall_instance_method_with_1_args(self, FIND_LAST, NOMINAL_TREE_P));
    }

    public static final SubLObject subloop_reserved_initialize_whnp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whnp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_whnp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whnp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject whnp_tree_p_alt(SubLObject whnp_tree) {
        return classes.subloop_instanceof_class(whnp_tree, WHNP_TREE);
    }

    public static SubLObject whnp_tree_p(final SubLObject whnp_tree) {
        return classes.subloop_instanceof_class(whnp_tree, WHNP_TREE);
    }

    public static final SubLObject verbal_phrase_tree_cyclify_int_vp_coordination_method_alt(SubLObject self) {
        {
            SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
            if (NIL != scycls) {
                return scycls;
            }
            {
                SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
                SubLObject subj_cycls = (NIL != subject) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(subject, CYCLIFY_INT))) : NIL;
                SubLObject conjunction = methods.funcall_instance_method_with_0_args(self, GET_CONJUNCTION);
                SubLObject vps = methods.funcall_instance_method_with_0_args(conjunction, GET_COMPLEMENTS);
                SubLObject cycls = NIL;
                SubLObject cdolist_list_var = vps;
                SubLObject vp = NIL;
                for (vp = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , vp = cdolist_list_var.first()) {
                    cycls = cons(methods.funcall_instance_method_with_0_args(vp, CYCLIFY_INT_VP), cycls);
                }
                return Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cons(subj_cycls, cycls)));
            }
        }
    }

    public static SubLObject verbal_phrase_tree_cyclify_int_vp_coordination_method(final SubLObject self) {
        final SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
        if (NIL != scycls) {
            return scycls;
        }
        final SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
        final SubLObject subj_cycls = (NIL != subject) ? methods.funcall_instance_method_with_0_args(subject, CYCLIFY_INT) : NIL;
        final SubLObject conjunction = methods.funcall_instance_method_with_0_args(self, GET_CONJUNCTION);
        final SubLObject vps = methods.funcall_instance_method_with_0_args(conjunction, GET_COMPLEMENTS);
        SubLObject cycls = NIL;
        SubLObject cdolist_list_var = vps;
        SubLObject vp = NIL;
        vp = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            cycls = cons(methods.funcall_instance_method_with_0_args(vp, CYCLIFY_INT_VP), cycls);
            cdolist_list_var = cdolist_list_var.rest();
            vp = cdolist_list_var.first();
        } 
        return Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cons(subj_cycls, cycls)));
    }

    public static final SubLObject verbal_phrase_tree_cyclify_int_vp_method_alt(SubLObject self) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
                if (NIL != scycls) {
                    return scycls;
                }
                {
                    SubLObject heads = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEADS);
                    SubLObject all_conjunction_cycls = NIL;
                    SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
                    SubLObject complements = (NIL != heads) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(heads.first(), GET_SEMANTIC_COMPLEMENTS))) : NIL;
                    SubLObject comp_cycls = NIL;
                    SubLObject cycls = NIL;
                    complements = cyclifier_interface.delete_cyclifiable(subject, complements);
                    if (NIL == subl_promotions.memberP(self, cyclifier_interface.$cyclification_in_progress$.getDynamicValue(thread), EQ, UNPROVIDED)) {
                        {
                            SubLObject _prev_bind_0 = cyclifier_interface.$cyclification_in_progress$.currentBinding(thread);
                            SubLObject _prev_bind_1 = cyclifier_interface.$cyclification_in_progress$.currentBinding(thread);
                            try {
                                cyclifier_interface.$cyclification_in_progress$.bind(cons(self, cyclifier_interface.$cyclification_in_progress$.getDynamicValue(thread)), thread);
                                cyclifier_interface.$cyclification_in_progress$.bind(append(heads, cyclifier_interface.$cyclification_in_progress$.getDynamicValue(thread)), thread);
                                {
                                    SubLObject cdolist_list_var = heads;
                                    SubLObject head = NIL;
                                    for (head = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , head = cdolist_list_var.first()) {
                                        {
                                            SubLObject all_head_cycls = NIL;
                                            SubLObject mod_cycls = cyclifier_interface.get_mod_cycls(head);
                                            SubLObject renamings = methods.funcall_instance_method_with_1_args(head, GET_KEYWORD_RENAMINGS, cyclifier.required_keywords(methods.funcall_instance_method_with_0_args(head, GET_RANKED_LEXES)));
                                            SubLObject cdolist_list_var_10 = renamings;
                                            SubLObject conjunction = NIL;
                                            for (conjunction = cdolist_list_var_10.first(); NIL != cdolist_list_var_10; cdolist_list_var_10 = cdolist_list_var_10.rest() , conjunction = cdolist_list_var_10.first()) {
                                                {
                                                    SubLObject all_rle_cycls = NIL;
                                                    SubLObject cdolist_list_var_11 = methods.funcall_instance_method_with_0_args(head, GET_RANKED_LEXES);
                                                    SubLObject rle = NIL;
                                                    for (rle = cdolist_list_var_11.first(); NIL != cdolist_list_var_11; cdolist_list_var_11 = cdolist_list_var_11.rest() , rle = cdolist_list_var_11.first()) {
                                                        {
                                                            SubLObject composite_verbal_cycls = NIL;
                                                            {
                                                                SubLObject cdolist_list_var_12 = conjunction;
                                                                SubLObject renaming = NIL;
                                                                for (renaming = cdolist_list_var_12.first(); NIL != cdolist_list_var_12; cdolist_list_var_12 = cdolist_list_var_12.rest() , renaming = cdolist_list_var_12.first()) {
                                                                    {
                                                                        SubLObject renamed0 = cyclifier_interface.apply_lex_renaming(head, rle, renaming);
                                                                        SubLObject renamed = (NIL != renamed0) ? ((SubLObject) (list(cyclifier.quantify_implicit_subject(renamed0)))) : NIL;
                                                                        composite_verbal_cycls = list_utilities.alist_pushnew(composite_verbal_cycls, cyclifier_interface.renaming_key(renaming), Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cons(renamed, mod_cycls))), EQL, UNPROVIDED);
                                                                    }
                                                                }
                                                            }
                                                            {
                                                                SubLObject sense_cycls = NIL;
                                                                SubLObject cdolist_list_var_13 = list_utilities.alist_values(composite_verbal_cycls);
                                                                SubLObject value = NIL;
                                                                for (value = cdolist_list_var_13.first(); NIL != cdolist_list_var_13; cdolist_list_var_13 = cdolist_list_var_13.rest() , value = cdolist_list_var_13.first()) {
                                                                    sense_cycls = cons(Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.indexed_products(value)), sense_cycls);
                                                                }
                                                                all_rle_cycls = cons(sense_cycls, all_rle_cycls);
                                                            }
                                                        }
                                                    }
                                                    all_head_cycls = cons(list_utilities.indexed_products(all_rle_cycls), all_head_cycls);
                                                }
                                            }
                                            all_conjunction_cycls = cons(all_head_cycls, all_conjunction_cycls);
                                        }
                                    }
                                }
                                {
                                    SubLObject cdolist_list_var = complements;
                                    SubLObject complement = NIL;
                                    for (complement = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , complement = cdolist_list_var.first()) {
                                        {
                                            SubLObject comp_cycl = methods.funcall_instance_method_with_0_args(complement, CYCLIFY_INT);
                                            if (NIL != comp_cycl) {
                                                comp_cycls = cons(comp_cycl, comp_cycls);
                                            }
                                        }
                                    }
                                }
                                {
                                    SubLObject cdolist_list_var = list_utilities.indexed_products(all_conjunction_cycls);
                                    SubLObject all_head_cycls = NIL;
                                    for (all_head_cycls = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , all_head_cycls = cdolist_list_var.first()) {
                                        {
                                            SubLObject cycls0 = NIL;
                                            SubLObject all_subjects_with_objects_cycls = NIL;
                                            SubLObject cdolist_list_var_14 = list_utilities.indexed_products(all_head_cycls);
                                            SubLObject item = NIL;
                                            for (item = cdolist_list_var_14.first(); NIL != cdolist_list_var_14; cdolist_list_var_14 = cdolist_list_var_14.rest() , item = cdolist_list_var_14.first()) {
                                                {
                                                    SubLObject complete_cycl_for_one_subject = NIL;
                                                    SubLObject cdolist_list_var_15 = list_utilities.cross_products(item);
                                                    SubLObject item2 = NIL;
                                                    for (item2 = cdolist_list_var_15.first(); NIL != cdolist_list_var_15; cdolist_list_var_15 = cdolist_list_var_15.rest() , item2 = cdolist_list_var_15.first()) {
                                                        complete_cycl_for_one_subject = append(Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.indexed_products(item2)), complete_cycl_for_one_subject);
                                                    }
                                                    all_subjects_with_objects_cycls = cons(Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cons(complete_cycl_for_one_subject, comp_cycls))), all_subjects_with_objects_cycls);
                                                }
                                            }
                                            cycls0 = Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.indexed_products(all_subjects_with_objects_cycls));
                                            cycls = append(cycls0, cycls);
                                        }
                                    }
                                }
                            } finally {
                                cyclifier_interface.$cyclification_in_progress$.rebind(_prev_bind_1, thread);
                                cyclifier_interface.$cyclification_in_progress$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                    return cycls;
                }
            }
        }
    }

    public static SubLObject verbal_phrase_tree_cyclify_int_vp_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
        if (NIL != scycls) {
            return scycls;
        }
        final SubLObject heads = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEADS);
        SubLObject all_conjunction_cycls = NIL;
        final SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
        SubLObject complements = (NIL != heads) ? methods.funcall_instance_method_with_0_args(heads.first(), GET_SEMANTIC_COMPLEMENTS) : NIL;
        SubLObject comp_cycls = NIL;
        SubLObject cycls = NIL;
        complements = cyclifier_interface.delete_cyclifiable(subject, complements);
        if (NIL == subl_promotions.memberP(self, cyclifier_interface.$cyclification_in_progress$.getDynamicValue(thread), EQ, UNPROVIDED)) {
            final SubLObject _prev_bind_0 = cyclifier_interface.$cyclification_in_progress$.currentBinding(thread);
            final SubLObject _prev_bind_2 = cyclifier_interface.$cyclification_in_progress$.currentBinding(thread);
            try {
                cyclifier_interface.$cyclification_in_progress$.bind(cons(self, cyclifier_interface.$cyclification_in_progress$.getDynamicValue(thread)), thread);
                cyclifier_interface.$cyclification_in_progress$.bind(append(heads, cyclifier_interface.$cyclification_in_progress$.getDynamicValue(thread)), thread);
                SubLObject cdolist_list_var = heads;
                SubLObject head = NIL;
                head = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject all_head_cycls = NIL;
                    final SubLObject mod_cycls = cyclifier_interface.get_mod_cycls(head);
                    SubLObject cdolist_list_var_$10;
                    final SubLObject renamings = cdolist_list_var_$10 = methods.funcall_instance_method_with_1_args(head, GET_KEYWORD_RENAMINGS, cyclifier.required_keywords(methods.funcall_instance_method_with_0_args(head, GET_RANKED_LEXES)));
                    SubLObject conjunction = NIL;
                    conjunction = cdolist_list_var_$10.first();
                    while (NIL != cdolist_list_var_$10) {
                        SubLObject all_rle_cycls = NIL;
                        SubLObject cdolist_list_var_$11 = methods.funcall_instance_method_with_0_args(head, GET_RANKED_LEXES);
                        SubLObject rle = NIL;
                        rle = cdolist_list_var_$11.first();
                        while (NIL != cdolist_list_var_$11) {
                            SubLObject composite_verbal_cycls = NIL;
                            SubLObject cdolist_list_var_$12 = conjunction;
                            SubLObject renaming = NIL;
                            renaming = cdolist_list_var_$12.first();
                            while (NIL != cdolist_list_var_$12) {
                                final SubLObject renamed0 = cyclifier_interface.apply_lex_renaming(head, rle, renaming);
                                final SubLObject renamed2 = (NIL != renamed0) ? list(cyclifier.quantify_implicit_subject(renamed0)) : NIL;
                                composite_verbal_cycls = list_utilities.alist_pushnew(composite_verbal_cycls, cyclifier_interface.renaming_key(renaming), Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cons(renamed2, mod_cycls))), EQL, UNPROVIDED);
                                cdolist_list_var_$12 = cdolist_list_var_$12.rest();
                                renaming = cdolist_list_var_$12.first();
                            } 
                            SubLObject sense_cycls = NIL;
                            SubLObject cdolist_list_var_$13 = list_utilities.alist_values(composite_verbal_cycls);
                            SubLObject value = NIL;
                            value = cdolist_list_var_$13.first();
                            while (NIL != cdolist_list_var_$13) {
                                sense_cycls = cons(Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.indexed_products(value)), sense_cycls);
                                cdolist_list_var_$13 = cdolist_list_var_$13.rest();
                                value = cdolist_list_var_$13.first();
                            } 
                            all_rle_cycls = cons(sense_cycls, all_rle_cycls);
                            cdolist_list_var_$11 = cdolist_list_var_$11.rest();
                            rle = cdolist_list_var_$11.first();
                        } 
                        all_head_cycls = cons(list_utilities.indexed_products(all_rle_cycls), all_head_cycls);
                        cdolist_list_var_$10 = cdolist_list_var_$10.rest();
                        conjunction = cdolist_list_var_$10.first();
                    } 
                    all_conjunction_cycls = cons(all_head_cycls, all_conjunction_cycls);
                    cdolist_list_var = cdolist_list_var.rest();
                    head = cdolist_list_var.first();
                } 
                cdolist_list_var = complements;
                SubLObject complement = NIL;
                complement = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject comp_cycl = methods.funcall_instance_method_with_0_args(complement, CYCLIFY_INT);
                    if (NIL != comp_cycl) {
                        comp_cycls = cons(comp_cycl, comp_cycls);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    complement = cdolist_list_var.first();
                } 
                cdolist_list_var = list_utilities.indexed_products(all_conjunction_cycls);
                SubLObject all_head_cycls2 = NIL;
                all_head_cycls2 = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    SubLObject cycls2 = NIL;
                    SubLObject all_subjects_with_objects_cycls = NIL;
                    SubLObject cdolist_list_var_$14 = list_utilities.indexed_products(all_head_cycls2);
                    SubLObject item = NIL;
                    item = cdolist_list_var_$14.first();
                    while (NIL != cdolist_list_var_$14) {
                        SubLObject complete_cycl_for_one_subject = NIL;
                        SubLObject cdolist_list_var_$15 = list_utilities.cross_products(item);
                        SubLObject item2 = NIL;
                        item2 = cdolist_list_var_$15.first();
                        while (NIL != cdolist_list_var_$15) {
                            complete_cycl_for_one_subject = append(Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.indexed_products(item2)), complete_cycl_for_one_subject);
                            cdolist_list_var_$15 = cdolist_list_var_$15.rest();
                            item2 = cdolist_list_var_$15.first();
                        } 
                        all_subjects_with_objects_cycls = cons(Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(cons(complete_cycl_for_one_subject, comp_cycls))), all_subjects_with_objects_cycls);
                        cdolist_list_var_$14 = cdolist_list_var_$14.rest();
                        item = cdolist_list_var_$14.first();
                    } 
                    cycls2 = Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.indexed_products(all_subjects_with_objects_cycls));
                    cycls = append(cycls2, cycls);
                    cdolist_list_var = cdolist_list_var.rest();
                    all_head_cycls2 = cdolist_list_var.first();
                } 
            } finally {
                cyclifier_interface.$cyclification_in_progress$.rebind(_prev_bind_2, thread);
                cyclifier_interface.$cyclification_in_progress$.rebind(_prev_bind_0, thread);
            }
        }
        return cycls;
    }

    public static final SubLObject verbal_phrase_tree_cyclify_int_method_alt(SubLObject self) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (!((NIL == cyclifier_interface.$allow_duplicate_cyclificationsP$.getDynamicValue(thread)) && (NIL != member(self, cyclifier_interface.$cyclification_complete$.getDynamicValue(thread), EQ, UNPROVIDED)))) {
                cyclifier_interface.$cyclification_complete$.setDynamicValue(cons(self, cyclifier_interface.$cyclification_complete$.getDynamicValue(thread)), thread);
                {
                    SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
                    if (NIL != scycls) {
                        return scycls;
                    }
                }
                if (NIL != cyclifier_interface.cyclifiable_sentential_phrase_p(self)) {
                    {
                        SubLObject head_dtr = methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTER);
                        if (NIL != head_dtr) {
                            return methods.funcall_instance_method_with_0_args(head_dtr, CYCLIFY_INT);
                        }
                    }
                } else {
                    if (NIL != methods.funcall_instance_method_with_0_args(self, VP_COORDINATE_PHRASE_P)) {
                        return methods.funcall_instance_method_with_0_args(self, CYCLIFY_INT_VP_COORDINATION);
                    } else {
                        {
                            SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
                            SubLObject subj_cycls = (NIL != subject) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(subject, CYCLIFY_INT))) : NIL;
                            SubLObject vp_cycls = methods.funcall_instance_method_with_0_args(self, CYCLIFY_INT_VP);
                            SubLObject subj_vp_cycls = (NIL != subj_cycls) ? ((SubLObject) (list(subj_cycls))) : NIL;
                            if (NIL != vp_cycls) {
                                subj_vp_cycls = cons(vp_cycls, subj_vp_cycls);
                            }
                            return Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(subj_vp_cycls));
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject verbal_phrase_tree_cyclify_int_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL != cyclifier_interface.$allow_duplicate_cyclificationsP$.getDynamicValue(thread)) || (NIL == member(self, cyclifier_interface.$cyclification_complete$.getDynamicValue(thread), EQ, UNPROVIDED))) {
            cyclifier_interface.$cyclification_complete$.setDynamicValue(cons(self, cyclifier_interface.$cyclification_complete$.getDynamicValue(thread)), thread);
            final SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
            if (NIL != scycls) {
                return scycls;
            }
            if (NIL != cyclifier_interface.cyclifiable_sentential_phrase_p(self)) {
                final SubLObject head_dtr = methods.funcall_instance_method_with_0_args(self, GET_HEAD_DAUGHTER);
                if (NIL != head_dtr) {
                    return methods.funcall_instance_method_with_0_args(head_dtr, CYCLIFY_INT);
                }
            } else {
                if (NIL != methods.funcall_instance_method_with_0_args(self, VP_COORDINATE_PHRASE_P)) {
                    return methods.funcall_instance_method_with_0_args(self, CYCLIFY_INT_VP_COORDINATION);
                }
                final SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
                final SubLObject subj_cycls = (NIL != subject) ? methods.funcall_instance_method_with_0_args(subject, CYCLIFY_INT) : NIL;
                final SubLObject vp_cycls = methods.funcall_instance_method_with_0_args(self, CYCLIFY_INT_VP);
                SubLObject subj_vp_cycls = (NIL != subj_cycls) ? list(subj_cycls) : NIL;
                if (NIL != vp_cycls) {
                    subj_vp_cycls = cons(vp_cycls, subj_vp_cycls);
                }
                return Mapping.mapcar(INSTANTIATE_SCOPE, list_utilities.cross_products(subj_vp_cycls));
            }
        }
        return NIL;
    }

    public static final SubLObject verbal_phrase_tree_vp_coordinate_phrase_p_method_alt(SubLObject self) {
        {
            SubLObject conjunction = methods.funcall_instance_method_with_0_args(self, GET_CONJUNCTION);
            return makeBoolean((NIL != conjunction) && (NIL == list_utilities.member_if_not(CYCLIFIABLE_VERB_PHRASE_P, methods.funcall_instance_method_with_0_args(conjunction, GET_COMPLEMENTS), UNPROVIDED)));
        }
    }

    public static SubLObject verbal_phrase_tree_vp_coordinate_phrase_p_method(final SubLObject self) {
        final SubLObject conjunction = methods.funcall_instance_method_with_0_args(self, GET_CONJUNCTION);
        return makeBoolean((NIL != conjunction) && (NIL == list_utilities.member_if_not(CYCLIFIABLE_VERB_PHRASE_P, methods.funcall_instance_method_with_0_args(conjunction, GET_COMPLEMENTS), UNPROVIDED)));
    }

    public static final SubLObject subloop_reserved_initialize_verbal_phrase_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_verbal_phrase_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_verbal_phrase_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_verbal_phrase_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject verbal_phrase_tree_p_alt(SubLObject verbal_phrase_tree) {
        return classes.subloop_instanceof_class(verbal_phrase_tree, VERBAL_PHRASE_TREE);
    }

    public static SubLObject verbal_phrase_tree_p(final SubLObject verbal_phrase_tree) {
        return classes.subloop_instanceof_class(verbal_phrase_tree, VERBAL_PHRASE_TREE);
    }

    /**
     *
     *
     * @return word-tree-p; the predicate of this sentence
     */
    @LispMethod(comment = "@return word-tree-p; the predicate of this sentence")
    public static final SubLObject verbal_phrase_tree_get_predicate_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD);
    }

    /**
     *
     *
     * @return word-tree-p; the predicate of this sentence
     */
    @LispMethod(comment = "@return word-tree-p; the predicate of this sentence")
    public static SubLObject verbal_phrase_tree_get_predicate_method(final SubLObject self) {
        return methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD);
    }

    /**
     *
     *
     * @return listp; a list of numbers of this vp with possible number values
    :SINGULAR and :PLURAL
     */
    @LispMethod(comment = "@return listp; a list of numbers of this vp with possible number values\r\n:SINGULAR and :PLURAL")
    public static final SubLObject verbal_phrase_tree_get_number_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_NUMBER))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of numbers of this vp with possible number values
    :SINGULAR and :PLURAL
     */
    @LispMethod(comment = "@return listp; a list of numbers of this vp with possible number values\r\n:SINGULAR and :PLURAL")
    public static SubLObject verbal_phrase_tree_get_number_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_NUMBER) : NIL;
    }

    /**
     *
     *
     * @return numberp; the person of this vp; 1, 2, or 3
     */
    @LispMethod(comment = "@return numberp; the person of this vp; 1, 2, or 3")
    public static final SubLObject verbal_phrase_tree_get_person_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_PERSON))) : NIL;
        }
    }

    /**
     *
     *
     * @return numberp; the person of this vp; 1, 2, or 3
     */
    @LispMethod(comment = "@return numberp; the person of this vp; 1, 2, or 3")
    public static SubLObject verbal_phrase_tree_get_person_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_PERSON) : NIL;
    }

    /**
     *
     *
     * @return keywordp; the tense of this vp; either :PRESENT, :PAST, :FUTURE,
    or :INFINITIVE
     */
    @LispMethod(comment = "@return keywordp; the tense of this vp; either :PRESENT, :PAST, :FUTURE,\r\nor :INFINITIVE")
    public static final SubLObject verbal_phrase_tree_get_tense_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_TENSE))) : NIL;
        }
    }

    /**
     *
     *
     * @return keywordp; the tense of this vp; either :PRESENT, :PAST, :FUTURE,
    or :INFINITIVE
     */
    @LispMethod(comment = "@return keywordp; the tense of this vp; either :PRESENT, :PAST, :FUTURE,\r\nor :INFINITIVE")
    public static SubLObject verbal_phrase_tree_get_tense_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_TENSE) : NIL;
    }

    /**
     *
     *
     * @return phrase-tree-p; the subject of this verbal tree
     */
    @LispMethod(comment = "@return phrase-tree-p; the subject of this verbal tree")
    public static final SubLObject verbal_phrase_tree_get_subject_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_verbal_phrase_tree_method = NIL;
            SubLObject idx = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_idx(self);
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    if (NIL != mother) {
                        {
                            SubLObject subject = methods.funcall_instance_method_with_2_args(mother, FIND_LAST, NOMINAL_TREE_P, number_utilities.f_1_(idx));
                            if ((NIL == subject) && (NIL != com.cyc.cycjava.cycl.parse_tree.verbal_tree_p(mother))) {
                                subject = methods.funcall_instance_method_with_0_args(mother, GET_SUBJECT);
                            }
                            sublisp_throw($sym435$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD, subject);
                        }
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_idx(self, idx);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_verbal_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym435$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD);
            }
            return catch_var_for_verbal_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; the subject of this verbal tree
     */
    @LispMethod(comment = "@return phrase-tree-p; the subject of this verbal tree")
    public static SubLObject verbal_phrase_tree_get_subject_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_verbal_phrase_tree_method = NIL;
        final SubLObject idx = get_parse_tree_idx(self);
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym435$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD);
            try {
                if (NIL != mother) {
                    SubLObject subject = methods.funcall_instance_method_with_2_args(mother, FIND_LAST, NOMINAL_TREE_P, number_utilities.f_1_(idx));
                    if ((NIL == subject) && (NIL != verbal_tree_p(mother))) {
                        subject = methods.funcall_instance_method_with_0_args(mother, GET_SUBJECT);
                    }
                    sublisp_throw($sym435$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD, subject);
                }
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_idx(self, idx);
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_verbal_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym435$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_verbal_phrase_tree_method;
    }

    static private final SubLList $list_alt1 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NUMBER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-GENDER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PERSON"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SINGULAR-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PLURAL-P"), NIL, makeKeyword("PUBLIC")));

    static private final SubLList $list_alt3 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PREDICATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SUBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-SUBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PASSIVE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FINITE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-LOCAL-OBJECTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NONLOCAL-OBJECTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INVERTED-P"), NIL, makeKeyword("PROTECTED")) });

    /**
     *
     *
     * @return listp; a list of all objects of this tree, in surface order
     */
    @LispMethod(comment = "@return listp; a list of all objects of this tree, in surface order")
    public static final SubLObject verbal_phrase_tree_get_objects_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_OBJECTS))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of all objects of this tree, in surface order
     */
    @LispMethod(comment = "@return listp; a list of all objects of this tree, in surface order")
    public static SubLObject verbal_phrase_tree_get_objects_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_OBJECTS) : NIL;
    }

    /**
     *
     *
     * @return phrase-tree-p; the direct object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the direct object of this verb phrase")
    public static final SubLObject verbal_phrase_tree_get_direct_object_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_DIRECT_OBJECT))) : NIL;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; the direct object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the direct object of this verb phrase")
    public static SubLObject verbal_phrase_tree_get_direct_object_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_DIRECT_OBJECT) : NIL;
    }

    /**
     *
     *
     * @return phrase-tree-p; the indirect object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the indirect object of this verb phrase")
    public static final SubLObject verbal_phrase_tree_get_indirect_object_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_INDIRECT_OBJECT))) : NIL;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; the indirect object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the indirect object of this verb phrase")
    public static SubLObject verbal_phrase_tree_get_indirect_object_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_INDIRECT_OBJECT) : NIL;
    }

    /**
     *
     *
     * @return phrase-tree-p; the semantic direct object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the semantic direct object of this verb phrase")
    public static final SubLObject verbal_phrase_tree_get_semantic_direct_object_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_DIRECT_OBJECT))) : NIL;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; the semantic direct object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the semantic direct object of this verb phrase")
    public static SubLObject verbal_phrase_tree_get_semantic_direct_object_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_DIRECT_OBJECT) : NIL;
    }

    /**
     *
     *
     * @return phrase-tree-p; the semantic indirect object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the semantic indirect object of this verb phrase")
    public static final SubLObject verbal_phrase_tree_get_semantic_indirect_object_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_INDIRECT_OBJECT))) : NIL;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; the semantic indirect object of this verb phrase
     */
    @LispMethod(comment = "@return phrase-tree-p; the semantic indirect object of this verb phrase")
    public static SubLObject verbal_phrase_tree_get_semantic_indirect_object_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_INDIRECT_OBJECT) : NIL;
    }

    /**
     *
     *
     * @return phrase-tree-p; the semantic subject of this verbal tree
     */
    @LispMethod(comment = "@return phrase-tree-p; the semantic subject of this verbal tree")
    public static final SubLObject verbal_phrase_tree_get_semantic_subject_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_SUBJECT))) : NIL;
        }
    }

    /**
     *
     *
     * @return phrase-tree-p; the semantic subject of this verbal tree
     */
    @LispMethod(comment = "@return phrase-tree-p; the semantic subject of this verbal tree")
    public static SubLObject verbal_phrase_tree_get_semantic_subject_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_SUBJECT) : NIL;
    }

    static private final SubLList $list_alt13 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIED"), NIL, makeKeyword("PUBLIC")));

    /**
     *
     *
     * @return listp; a list of the semantic subject of this verbal tree
     */
    @LispMethod(comment = "@return listp; a list of the semantic subject of this verbal tree")
    public static final SubLObject verbal_phrase_tree_get_semantic_objects_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_OBJECTS))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of the semantic subject of this verbal tree
     */
    @LispMethod(comment = "@return listp; a list of the semantic subject of this verbal tree")
    public static SubLObject verbal_phrase_tree_get_semantic_objects_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_SEMANTIC_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_SEMANTIC_OBJECTS) : NIL;
    }

    static private final SubLList $list_alt17 = list(makeSymbol("CYCLIFIABLE"), makeSymbol("PARSE-TREE-INTERFACE"));

    static private final SubLList $list_alt18 = list(new SubLObject[]{ list(makeSymbol("STRING"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("CATEGORY"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("MOTHER"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("IDX"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("MODIFIERS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("CONTEXT"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DEEP-COPY"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ROOT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-STRING"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CATEGORY"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-INDEX"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PATH"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MOTHER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SISTER"), list(makeSymbol("INDEX")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("YIELD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PARSE-EXPRESSION"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEAD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-COMPLEMENTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MODIFIERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MODIFIEDS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CONTEXT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("TEMPORAL-TREE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFIABLE-QUESTION-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ROOT-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY"), list(makeSymbol("LEXICON")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LOGICAL-FORM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY"), list(makeSymbol("PROPERTIES")), makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CYCLIFIER"), list(makeSymbol("PROPERTIES")), makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COVERS"), list(makeSymbol("LIST-OF-STRINGS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MINIMALLY-COVERING-SUBTREES"), list(makeSymbol("LIST-OF-STRINGS")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), list(makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COVERS-MINIMALLY"), list(makeSymbol("LIST-OF-STRINGS")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTORS"), NIL, makeKeyword("PROTECTED")) });

    /**
     *
     *
     * @return listp; a list of all verbal complements of this verbal tree
     */
    @LispMethod(comment = "@return listp; a list of all verbal complements of this verbal tree")
    public static final SubLObject verbal_phrase_tree_get_verbal_complement_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_VERBAL_COMPLEMENT))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of all verbal complements of this verbal tree
     */
    @LispMethod(comment = "@return listp; a list of all verbal complements of this verbal tree")
    public static SubLObject verbal_phrase_tree_get_verbal_complement_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_VERBAL_COMPLEMENT) : NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this phrase-tree is a copula construction, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase-tree is a copula construction, nil otherwise")
    public static final SubLObject verbal_phrase_tree_copula_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != cyclifier_interface.aux_verb_word_p(head) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, COPULA_P))) : NIL;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this phrase-tree is a copula construction, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase-tree is a copula construction, nil otherwise")
    public static SubLObject verbal_phrase_tree_copula_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != cyclifier_interface.aux_verb_word_p(head) ? methods.funcall_instance_method_with_0_args(head, COPULA_P) : NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this phrase-tree is a dummy 'to' construction, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase-tree is a dummy \'to\' construction, nil otherwise")
    public static final SubLObject verbal_phrase_tree_dummy_to_p_method_alt(SubLObject self) {
        return makeBoolean((NIL != word_tree.verbal_word_tree_p(methods.funcall_instance_method_with_0_args(self, GET_HEAD))) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER), GET_STRING).equal($$$to));
    }

    /**
     *
     *
     * @return booleanp; t if this phrase-tree is a dummy 'to' construction, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase-tree is a dummy \'to\' construction, nil otherwise")
    public static SubLObject verbal_phrase_tree_dummy_to_p_method(final SubLObject self) {
        return makeBoolean((NIL != word_tree.verbal_word_tree_p(methods.funcall_instance_method_with_0_args(self, GET_HEAD))) && methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER), GET_STRING).equal($$$to));
    }

    /**
     *
     *
     * @return booleanp; t if this phrase-tree is a passive construction, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase-tree is a passive construction, nil otherwise")
    public static final SubLObject verbal_phrase_tree_passive_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, PASSIVE_P))) : NIL;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this phrase-tree is a passive construction, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase-tree is a passive construction, nil otherwise")
    public static SubLObject verbal_phrase_tree_passive_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, PASSIVE_P) : NIL;
    }

    /**
     *
     *
     * @return booleanp; t if this phrase tree is finite, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase tree is finite, nil otherwise")
    public static final SubLObject verbal_phrase_tree_finite_p_method_alt(SubLObject self) {
        return com.cyc.cycjava.cycl.parse_tree.finite_verbal_word_tree_p(methods.funcall_instance_method_with_0_args(self, GET_HEAD));
    }

    /**
     *
     *
     * @return booleanp; t if this phrase tree is finite, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this phrase tree is finite, nil otherwise")
    public static SubLObject verbal_phrase_tree_finite_p_method(final SubLObject self) {
        return finite_verbal_word_tree_p(methods.funcall_instance_method_with_0_args(self, GET_HEAD));
    }

    public static final SubLObject verbal_phrase_tree_partition_semantic_complements_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, PARTITION_SEMANTIC_COMPLEMENTS))) : NIL;
        }
    }

    public static SubLObject verbal_phrase_tree_partition_semantic_complements_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, PARTITION_SEMANTIC_COMPLEMENTS) : NIL;
    }

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static final SubLObject verbal_phrase_tree_get_ancestor_objects_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_verbal_phrase_tree_method = NIL;
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    if (NIL != methods.funcall_instance_method_with_0_args(self, INVERTED_P)) {
                        sublisp_throw($sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD, NIL != com.cyc.cycjava.cycl.parse_tree.verbal_phrase_tree_p(mother) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(mother, GET_ANCESTOR_OBJECTS))) : NIL);
                    } else {
                        sublisp_throw($sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD, append(NIL != com.cyc.cycjava.cycl.parse_tree.verbal_phrase_tree_p(mother) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(mother, GET_ANCESTOR_OBJECTS))) : NIL, methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P)));
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_verbal_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD);
            }
            return catch_var_for_verbal_phrase_tree_method;
        }
    }

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static SubLObject verbal_phrase_tree_get_ancestor_objects_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_verbal_phrase_tree_method = NIL;
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD);
            try {
                if (NIL != methods.funcall_instance_method_with_0_args(self, INVERTED_P)) {
                    sublisp_throw($sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD, NIL != verbal_phrase_tree_p(mother) ? methods.funcall_instance_method_with_0_args(mother, GET_ANCESTOR_OBJECTS) : NIL);
                } else {
                    sublisp_throw($sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD, append(NIL != verbal_phrase_tree_p(mother) ? methods.funcall_instance_method_with_0_args(mother, GET_ANCESTOR_OBJECTS) : NIL, methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P)));
                }
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_verbal_phrase_tree_method = Errors.handleThrowable(ccatch_env_var, $sym478$OUTER_CATCH_FOR_VERBAL_PHRASE_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_verbal_phrase_tree_method;
    }

    /**
     *
     *
     * @return listp; the head daughters of this verbal phrase
     */
    @LispMethod(comment = "@return listp; the head daughters of this verbal phrase")
    public static final SubLObject verbal_phrase_tree_get_head_daughters_method_alt(SubLObject self) {
        {
            SubLObject head_daughters = methods.funcall_instance_method_with_1_args(self, FIND, VERBAL_WORD_TREE_P);
            return NIL != head_daughters ? ((SubLObject) (head_daughters)) : methods.funcall_instance_method_with_1_args(self, FIND, VERBAL_PHRASE_TREE_P);
        }
    }

    /**
     *
     *
     * @return listp; the head daughters of this verbal phrase
     */
    @LispMethod(comment = "@return listp; the head daughters of this verbal phrase")
    public static SubLObject verbal_phrase_tree_get_head_daughters_method(final SubLObject self) {
        final SubLObject head_daughters = methods.funcall_instance_method_with_1_args(self, FIND, VERBAL_WORD_TREE_P);
        return NIL != head_daughters ? head_daughters : methods.funcall_instance_method_with_1_args(self, FIND, VERBAL_PHRASE_TREE_P);
    }

    static private final SubLList $list_alt26 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list_alt27 = list(makeSymbol("&OPTIONAL"), makeSymbol("PROPERTIES"));

    static private final SubLList $list_alt28 = list(list(new SubLObject[]{ makeSymbol("CDESTRUCTURING-BIND"), list(new SubLObject[]{ makeSymbol("&KEY"), list(makeSymbol("LEXICON"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("CYCLIFIER-LEXICON")))), list(makeSymbol("SCORE-FUNCTION"), list(QUOTE, makeSymbol("CYCLIFICATION-SCORE"))), list(makeSymbol("OUTPUT"), makeKeyword("BRIEF")), list(makeSymbol("WFF-CHECK?"), T), makeSymbol("CONTEXT"), list(makeSymbol("DISAMBIGUATOR"), NIL), list(makeSymbol("COREFERENCE-RESOLVER"), NIL), list(makeSymbol("ERROR-HANDLING"), makeKeyword("THROW")), list(makeSymbol("SUBCYCLIFIER-POOL"), list(makeSymbol("NEW-CYCLIFIER-POOL"), list(makeSymbol("LIST"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("DATE-CYCLIFIER")))))), makeSymbol("&ALLOW-OTHER-KEYS") }), makeSymbol("PROPERTIES"), list(makeSymbol("CHECK-TYPE"), makeSymbol("LEXICON"), makeSymbol("CYCLIFIER-LEXICON-P")), list(makeSymbol("CHECK-TYPE"), makeSymbol("SCORE-FUNCTION"), makeSymbol("FUNCTION-SPEC-P")), list(makeSymbol("CHECK-TYPE"), makeSymbol("OUTPUT"), makeSymbol("KEYWORDP")), list(makeSymbol("CHECK-TYPE-IF-PRESENT"), makeSymbol("CONTEXT"), makeSymbol("PARSE-TREE-CONTEXT-P")), list(makeSymbol("CHECK-TYPE"), makeSymbol("WFF-CHECK?"), makeSymbol("BOOLEANP")), list(makeSymbol("MUST"), list(makeSymbol("MEMBER"), makeSymbol("ERROR-HANDLING"), list(QUOTE, list(makeKeyword("THROW"), $WARN, makeKeyword("IGNORE")))), makeString("Invalid error-handling tag ~a"), makeSymbol("ERROR-HANDLING")), list(makeSymbol("CHECK-TYPE-IF-PRESENT"), makeSymbol("SUBCYCLIFIER-POOL"), makeSymbol("CYCLIFIER-POOL-P")), list(new SubLObject[]{ makeSymbol("CLET"), list(list(makeSymbol("CYCLIFIER"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("CYCLIFIER"))))), list(makeSymbol("SET-CYCLIFIER-SCORE-FUNCTION"), makeSymbol("CYCLIFIER"), makeSymbol("SCORE-FUNCTION")), list(makeSymbol("SET-CYCLIFIER-OUTPUT"), makeSymbol("CYCLIFIER"), makeSymbol("OUTPUT")), list(makeSymbol("SET-CYCLIFIER-ERROR-HANDLING"), makeSymbol("CYCLIFIER"), makeSymbol("ERROR-HANDLING")), list(makeSymbol("SET-CYCLIFIER-SUBCYCLIFIER-POOL"), makeSymbol("CYCLIFIER"), makeSymbol("SUBCYCLIFIER-POOL")), list(makeSymbol("PWHEN"), makeSymbol("LEXICON"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("LEXIFY")), makeSymbol("LEXICON"))), list(makeSymbol("PWHEN"), makeSymbol("DISAMBIGUATOR"), list(makeSymbol("PWHEN"), list(makeSymbol("NULL"), makeSymbol("CONTEXT")), list(makeSymbol("CSETQ"), makeSymbol("CONTEXT"), list(makeSymbol("NEW-CLASS-INSTANCE"), list(QUOTE, makeSymbol("PARSE-TREE-CONTEXT"))))), list(makeSymbol("FIM"), makeSymbol("DISAMBIGUATOR"), list(QUOTE, makeSymbol("DISAMBIGUATE")), makeSymbol("SELF"), makeSymbol("CONTEXT"))), list(makeSymbol("IGNORE"), makeSymbol("COREFERENCE-RESOLVER")), list(makeSymbol("SET-CYCLIFIER-TREE"), makeSymbol("CYCLIFIER"), makeSymbol("SELF")), list(makeSymbol("SET-CYCLIFIER-WFF-CHECK?"), makeSymbol("CYCLIFIER"), makeSymbol("WFF-CHECK?")), list(makeSymbol("PWHEN"), makeSymbol("WFF-CHECK?"), list(makeSymbol("SET-CYCLIFIER-MEMOIZATION-STATE"), makeSymbol("CYCLIFIER"), list(makeSymbol("NEW-MEMOIZATION-STATE"))), list(makeSymbol("SET-CYCLIFIER-SBHL-RESOURCE"), makeSymbol("CYCLIFIER"), list(makeSymbol("NEW-SBHL-MARKING-SPACE-RESOURCE"), TEN_INTEGER))), list(RET, makeSymbol("CYCLIFIER")) }) }));

    /**
     *
     *
     * @return booleanp; t if this verbal tree is inverted, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this verbal tree is inverted, nil otherwise")
    public static final SubLObject verbal_phrase_tree_inverted_p_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, INVERTED_P))) : NIL;
        }
    }

    /**
     *
     *
     * @return booleanp; t if this verbal tree is inverted, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; t if this verbal tree is inverted, nil otherwise")
    public static SubLObject verbal_phrase_tree_inverted_p_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, INVERTED_P) : NIL;
    }

    public static final SubLObject subloop_reserved_initialize_sentential_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sentential_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_sentential_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sentential_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject sentential_tree_p_alt(SubLObject sentential_tree) {
        return classes.subloop_instanceof_class(sentential_tree, SENTENTIAL_TREE);
    }

    public static SubLObject sentential_tree_p(final SubLObject sentential_tree) {
        return classes.subloop_instanceof_class(sentential_tree, SENTENTIAL_TREE);
    }

    /**
     *
     *
     * @return np-tree-p; the subject of this sentence
     */
    @LispMethod(comment = "@return np-tree-p; the subject of this sentence")
    public static final SubLObject sentential_tree_get_subject_method_alt(SubLObject self) {
        if (NIL != methods.funcall_instance_method_with_0_args(self, INVERTED_P)) {
            {
                SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
                SubLObject head_mother = (NIL != head) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_MOTHER))) : NIL;
                if (NIL != head_mother) {
                    return methods.funcall_instance_method_with_2_args(head_mother, FIND_FIRST, NOMINAL_TREE_P, number_utilities.f_1X(methods.funcall_instance_method_with_0_args(head, GET_INDEX)));
                }
            }
        } else {
            {
                SubLObject subject = methods.funcall_instance_method_with_1_args(self, FIND_FIRST, NOMINAL_TREE_P);
                if (NIL != subject) {
                    return subject;
                } else {
                    {
                        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, GET_ANCESTORS);
                        SubLObject ancestor = NIL;
                        for (ancestor = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , ancestor = cdolist_list_var.first()) {
                            if (NIL != com.cyc.cycjava.cycl.parse_tree.verbal_tree_p(ancestor)) {
                                return methods.funcall_instance_method_with_0_args(ancestor, GET_SUBJECT);
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return np-tree-p; the subject of this sentence
     */
    @LispMethod(comment = "@return np-tree-p; the subject of this sentence")
    public static SubLObject sentential_tree_get_subject_method(final SubLObject self) {
        if (NIL != methods.funcall_instance_method_with_0_args(self, INVERTED_P)) {
            final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            final SubLObject head_mother = (NIL != head) ? methods.funcall_instance_method_with_0_args(head, GET_MOTHER) : NIL;
            if (NIL != head_mother) {
                return methods.funcall_instance_method_with_2_args(head_mother, FIND_FIRST, NOMINAL_TREE_P, number_utilities.f_1X(methods.funcall_instance_method_with_0_args(head, GET_INDEX)));
            }
        } else {
            final SubLObject subject = methods.funcall_instance_method_with_1_args(self, FIND_FIRST, NOMINAL_TREE_P);
            if (NIL != subject) {
                return subject;
            }
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, GET_ANCESTORS);
            SubLObject ancestor = NIL;
            ancestor = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL != verbal_tree_p(ancestor)) {
                    return methods.funcall_instance_method_with_0_args(ancestor, GET_SUBJECT);
                }
                cdolist_list_var = cdolist_list_var.rest();
                ancestor = cdolist_list_var.first();
            } 
        }
        return NIL;
    }

    /**
     *
     *
     * @return listp; the constituents this sentence modifies
     */
    @LispMethod(comment = "@return listp; the constituents this sentence modifies")
    public static final SubLObject sentential_tree_get_modifieds_method_alt(SubLObject self) {
        if (NIL != word_tree.relative_clause_p(self)) {
            {
                SubLObject first_daughter = methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER);
                SubLObject first_head = (NIL != first_daughter) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(first_daughter, GET_HEAD))) : NIL;
                SubLObject modified = (NIL != first_head) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(first_head, GET_ANTECEDENT))) : NIL;
                SubLObject mod_head = (NIL != modified) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(modified, GET_HEAD))) : NIL;
                return NIL != mod_head ? ((SubLObject) (list(mod_head))) : NIL;
            }
        }
        return NIL;
    }

    /**
     *
     *
     * @return listp; the constituents this sentence modifies
     */
    @LispMethod(comment = "@return listp; the constituents this sentence modifies")
    public static SubLObject sentential_tree_get_modifieds_method(final SubLObject self) {
        if (NIL != word_tree.relative_clause_p(self)) {
            final SubLObject first_daughter = methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER);
            final SubLObject first_head = (NIL != word_tree.word_tree_p(first_daughter)) ? methods.funcall_instance_method_with_0_args(first_daughter, GET_HEAD) : NIL;
            final SubLObject modified = (NIL != word_tree.wp_tree_p(first_head)) ? methods.funcall_instance_method_with_0_args(first_head, GET_ANTECEDENT) : NIL;
            final SubLObject mod_head = (NIL != word_tree.word_tree_p(modified)) ? methods.funcall_instance_method_with_0_args(modified, GET_HEAD) : NIL;
            return NIL != mod_head ? list(mod_head) : NIL;
        }
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_vp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_vp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    static private final SubLList $list_alt49 = list(makeKeyword("THROW"), $WARN, makeKeyword("IGNORE"));

    public static final SubLObject subloop_reserved_initialize_vp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_vp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLString $str_alt50$Invalid_error_handling_tag__a = makeString("Invalid error-handling tag ~a");

    static private final SubLList $list_alt58 = list(list(makeSymbol("CLET"), list(list(makeSymbol("CYCLIFIER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CYCLIFIER")), makeSymbol("PROPERTIES"))), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("WHILE"), list(makeSymbol("FIM"), makeSymbol("CYCLIFIER"), list(QUOTE, makeSymbol("HAS-NEXT?"))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("CYCLIFIER"), list(QUOTE, makeSymbol("NEXT"))), makeSymbol("CYCLS"))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("CYCLS")))));

    public static final SubLObject vp_tree_p_alt(SubLObject vp_tree) {
        return classes.subloop_instanceof_class(vp_tree, VP_TREE);
    }

    public static SubLObject vp_tree_p(final SubLObject vp_tree) {
        return classes.subloop_instanceof_class(vp_tree, VP_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_vg_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_vg_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_vg_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_vg_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt68 = list(makeString("@return parse-tree-p; a deep copy of this parse tree.\n   @owner bertolo."), list(makeSymbol("CLET"), list(list(makeSymbol("CLASS-OF-SELF"), list(makeSymbol("INSTANCE-CLASS"), makeSymbol("SELF"))), list(makeSymbol("NEW"), list(makeSymbol("FUNCALL-CLASS-METHOD"), makeSymbol("CLASS-OF-SELF"), list(QUOTE, makeSymbol("NEW")))), list(makeSymbol("ALL-INSTANCE-SLOTS"), list(makeSymbol("INSTANCES-ALL-INSTANCE-SLOTS"), makeSymbol("SELF")))), list(makeSymbol("CDOLIST"), list(makeSymbol("INSTANCE-SLOT"), makeSymbol("ALL-INSTANCE-SLOTS")), list(makeSymbol("CLET"), list(list(makeSymbol("SLOT-VALUE"), list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), makeSymbol("INSTANCE-SLOT"))), list(makeSymbol("VALUE-COPY"), NIL)), list(makeSymbol("PCOND"), list(list(makeSymbol("OBJECT-P"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("FIM"), makeSymbol("SLOT-VALUE"), list(QUOTE, makeSymbol("DEEP-COPY"))))), list(list(makeSymbol("LISTP"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("COPY-LIST"), makeSymbol("SLOT-VALUE")))), list(list(makeSymbol("VECTORP"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("COPY-VECTOR"), makeSymbol("SLOT-VALUE")))), list(list(makeSymbol("HASH-TABLE-P"), makeSymbol("SLOT-VALUE")), list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), list(makeSymbol("COPY-HASHTABLE"), makeSymbol("SLOT-VALUE")))), list(T, list(makeSymbol("CSETQ"), makeSymbol("VALUE-COPY"), makeSymbol("SLOT-VALUE")))), list(makeSymbol("SET-SLOT"), makeSymbol("NEW"), makeSymbol("INSTANCE-SLOT"), makeSymbol("VALUE-COPY")))), list(RET, makeSymbol("NEW"))));

    public static final SubLObject vg_tree_p_alt(SubLObject vg_tree) {
        return classes.subloop_instanceof_class(vg_tree, VG_TREE);
    }

    public static SubLObject vg_tree_p(final SubLObject vg_tree) {
        return classes.subloop_instanceof_class(vg_tree, VG_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_s1_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_s1_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_s1_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_s1_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject s1_tree_p_alt(SubLObject s1_tree) {
        return classes.subloop_instanceof_class(s1_tree, S1_TREE);
    }

    public static SubLObject s1_tree_p(final SubLObject s1_tree) {
        return classes.subloop_instanceof_class(s1_tree, S1_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_s_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_s_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    static private final SubLList $list_alt72 = list(makeString("@return phrase-tree-p; the root of this tree"), list(makeSymbol("CLET"), list(list(makeSymbol("TREE"), makeSymbol("SELF"))), list(makeSymbol("UNTIL"), list(makeSymbol("FIM"), makeSymbol("TREE"), list(QUOTE, makeSymbol("ROOT-P"))), list(makeSymbol("CSETQ"), makeSymbol("TREE"), list(makeSymbol("FIM"), makeSymbol("TREE"), list(QUOTE, makeSymbol("GET-MOTHER"))))), list(RET, makeSymbol("TREE"))));

    public static final SubLObject subloop_reserved_initialize_s_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_s_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt77 = list(makeString("@return keywordp; the category of this parse-tree"), list(RET, makeSymbol("CATEGORY")));

    public static final SubLObject s_tree_p_alt(SubLObject s_tree) {
        return classes.subloop_instanceof_class(s_tree, S_TREE);
    }

    public static SubLObject s_tree_p(final SubLObject s_tree) {
        return classes.subloop_instanceof_class(s_tree, S_TREE);
    }

    static private final SubLList $list_alt81 = list(makeString("@return non-negative-integer-p; the number of sisters left to this parse tree"), list(RET, makeSymbol("IDX")));

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static final SubLObject s_tree_get_ancestor_objects_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_s_tree_method = NIL;
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    {
                        SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
                        if ((NIL != subject) && (NIL != methods.funcall_instance_method_with_0_args(self, INVERTED_P))) {
                            sublisp_throw($sym511$OUTER_CATCH_FOR_S_TREE_METHOD, list(subject));
                        } else {
                            if ((NIL != mother) && (NIL != word_tree.relative_clause_p(mother))) {
                                sublisp_throw($sym511$OUTER_CATCH_FOR_S_TREE_METHOD, methods.funcall_instance_method_with_0_args(mother, GET_MODIFIEDS));
                            }
                        }
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_s_tree_method = Errors.handleThrowable(ccatch_env_var, $sym511$OUTER_CATCH_FOR_S_TREE_METHOD);
            }
            return catch_var_for_s_tree_method;
        }
    }

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static SubLObject s_tree_get_ancestor_objects_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_s_tree_method = NIL;
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym511$OUTER_CATCH_FOR_S_TREE_METHOD);
            try {
                final SubLObject subject = methods.funcall_instance_method_with_0_args(self, GET_SUBJECT);
                if ((NIL != subject) && (NIL != methods.funcall_instance_method_with_0_args(self, INVERTED_P))) {
                    sublisp_throw($sym511$OUTER_CATCH_FOR_S_TREE_METHOD, list(subject));
                } else
                    if ((NIL != mother) && (NIL != word_tree.relative_clause_p(mother))) {
                        sublisp_throw($sym511$OUTER_CATCH_FOR_S_TREE_METHOD, methods.funcall_instance_method_with_0_args(mother, GET_MODIFIEDS));
                    }

            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_s_tree_method = Errors.handleThrowable(ccatch_env_var, $sym511$OUTER_CATCH_FOR_S_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_s_tree_method;
    }

    static private final SubLList $list_alt85 = list(makeString("@return listp; the path from the root to this parse tree"), list(makeSymbol("CLET"), list(list(makeSymbol("PATH"), list(makeSymbol("FWHEN"), makeSymbol("IDX"), list(makeSymbol("LIST"), makeSymbol("IDX"))))), list(makeSymbol("CDOLIST"), list(makeSymbol("ANCESTOR"), list(makeSymbol("BUTLAST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-ANCESTORS"))))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("ANCESTOR"), list(QUOTE, makeSymbol("GET-INDEX"))), makeSymbol("PATH"))), list(RET, makeSymbol("PATH"))));

    static private final SubLList $list_alt89 = list(makeString("@return parse-tree-p; the mother of this tree"), list(RET, makeSymbol("MOTHER")));

    static private final SubLList $list_alt93 = list(makeString("@return word-tree-p; the (first) semantic head of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS"))))));

    public static final SubLObject subloop_reserved_initialize_sbar_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sbar_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_sbar_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sbar_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt97 = list(makeString("@return listp; a list of all complements of this tree, in surface order"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-COMPLEMENTS")))))));

    static private final SubLList $list_alt101 = list(makeString("@return listp; a list of modifiers of this tree"), list(RET, NIL));

    public static final SubLObject sbar_tree_p_alt(SubLObject sbar_tree) {
        return classes.subloop_instanceof_class(sbar_tree, SBAR_TREE);
    }

    public static SubLObject sbar_tree_p(final SubLObject sbar_tree) {
        return classes.subloop_instanceof_class(sbar_tree, SBAR_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_sbarq_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sbarq_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    static private final SubLList $list_alt104 = list(makeString("@return listp; the constituents this tree modifies"), list(RET, NIL));

    public static final SubLObject subloop_reserved_initialize_sbarq_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sbarq_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt107 = list(makeString("@return parse-tree-context-p; other parse trees created in this tree's context"), list(RET, makeSymbol("CONTEXT")));

    static private final SubLList $list_alt111 = list(makeString("@return booleanp; t if this tree is temporal, nil otherwise\n   @note this is just a fallback; specific trees override this"), list(RET, NIL));

    public static final SubLObject sbarq_tree_p_alt(SubLObject sbarq_tree) {
        return classes.subloop_instanceof_class(sbarq_tree, SBARQ_TREE);
    }

    public static SubLObject sbarq_tree_p(final SubLObject sbarq_tree) {
        return classes.subloop_instanceof_class(sbarq_tree, SBARQ_TREE);
    }

    static private final SubLList $list_alt114 = list(makeString("@return boolean; t if this is a question, nil otherwise"), list(RET, list(makeSymbol("QUESTION-TREE-P"), makeSymbol("SELF"))));

    /**
     *
     *
     * @return listp; the head daughters of this sbarq tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this sbarq tree")
    public static final SubLObject sbarq_tree_get_head_daughters_method_alt(SubLObject self) {
        {
            SubLObject head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_FIRST, VERBAL_TREE_P);
            return NIL != head_daughter ? ((SubLObject) (list(head_daughter))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; the head daughters of this sbarq tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this sbarq tree")
    public static SubLObject sbarq_tree_get_head_daughters_method(final SubLObject self) {
        final SubLObject head_daughter = methods.funcall_instance_method_with_1_args(self, FIND_FIRST, VERBAL_TREE_P);
        return NIL != head_daughter ? list(head_daughter) : NIL;
    }

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static final SubLObject sbarq_tree_get_ancestor_objects_method_alt(SubLObject self) {
        return delete(methods.funcall_instance_method_with_0_args(self, GET_SUBJECT), methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P), EQ, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static SubLObject sbarq_tree_get_ancestor_objects_method(final SubLObject self) {
        return delete(methods.funcall_instance_method_with_0_args(self, GET_SUBJECT), methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P), EQ, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    static private final SubLList $list_alt116 = list(makeString("@return booleanp; t if this tree is the root, nil otherwise"), list(RET, list(makeSymbol("CNOT"), makeSymbol("MOTHER"))));

    public static final SubLObject subloop_reserved_initialize_sq_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sq_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    static private final SubLList $list_alt119 = list(makeSymbol("LEXICON"));

    public static final SubLObject subloop_reserved_initialize_sq_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sq_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt120 = list(makeString("@return parse-tree-p; this parse-tree with lexical entries added"), list(makeSymbol("CLET"), list(list(makeSymbol("*ALLOW-DUPLICATE-CYCLIFICATIONS?*"), T), list(makeSymbol("*CYCLIFICATION-COMPLETE*"), NIL)), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt123 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list_alt124 = list(makeString("@return parse-tree-p; this parse-tree with lexical entries added"), list(makeSymbol("IGNORE"), makeSymbol("LEXICON")), list(RET, makeSymbol("SELF")));

    public static final SubLObject sq_tree_p_alt(SubLObject sq_tree) {
        return classes.subloop_instanceof_class(sq_tree, SQ_TREE);
    }

    public static SubLObject sq_tree_p(final SubLObject sq_tree) {
        return classes.subloop_instanceof_class(sq_tree, SQ_TREE);
    }

    static private final SubLList $list_alt127 = list(makeString("@return listp; the CycL representation of the meaning of this parse-tree"), list(RET, NIL));

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static final SubLObject sq_tree_get_ancestor_objects_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_sq_tree_method = NIL;
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    sublisp_throw($sym531$OUTER_CATCH_FOR_SQ_TREE_METHOD, append(methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P).rest(), NIL != com.cyc.cycjava.cycl.parse_tree.verbal_phrase_tree_p(mother) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(mother, GET_ANCESTOR_OBJECTS))) : NIL));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_sq_tree_method = Errors.handleThrowable(ccatch_env_var, $sym531$OUTER_CATCH_FOR_SQ_TREE_METHOD);
            }
            return catch_var_for_sq_tree_method;
        }
    }

    /**
     *
     *
     * @return listp; the local syntactic objects of this tree and its ancestors
     */
    @LispMethod(comment = "@return listp; the local syntactic objects of this tree and its ancestors")
    public static SubLObject sq_tree_get_ancestor_objects_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_sq_tree_method = NIL;
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym531$OUTER_CATCH_FOR_SQ_TREE_METHOD);
            try {
                sublisp_throw($sym531$OUTER_CATCH_FOR_SQ_TREE_METHOD, append(methods.funcall_instance_method_with_1_args(self, FIND, NOMINAL_TREE_P).rest(), NIL != verbal_phrase_tree_p(mother) ? methods.funcall_instance_method_with_0_args(mother, GET_ANCESTOR_OBJECTS) : NIL));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_sq_tree_method = Errors.handleThrowable(ccatch_env_var, $sym531$OUTER_CATCH_FOR_SQ_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_sq_tree_method;
    }

    static private final SubLList $list_alt129 = list(makeString("@return listp; the list of ancestors of this parse tree"), list(RET, list(makeSymbol("FWHEN"), makeSymbol("MOTHER"), list(makeSymbol("CONS"), makeSymbol("MOTHER"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTORS")))))));

    static private final SubLList $list_alt133 = list(makeSymbol("INDEX"));

    static private final SubLList $list_alt134 = list(makeString("@return parse-tree-p; the sister INDEX positions to the right of this\n   parse tree if INDEX is positive and to the left if INDEX is negative;\n   nil if there is no such sister"), list(makeSymbol("CHECK-TYPE"), makeSymbol("INDEX"), makeSymbol("INTEGERP")), list(makeSymbol("PWHEN"), makeSymbol("IDX"), list(makeSymbol("CLET"), list(list(makeSymbol("SISTER-INDEX"), list(makeSymbol("+"), makeSymbol("IDX"), makeSymbol("INDEX")))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("CAND"), makeSymbol("MOTHER"), list(makeSymbol("NON-NEGATIVE-INTEGER-P"), makeSymbol("SISTER-INDEX"))), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-DAUGHTER")), list(makeSymbol("+"), makeSymbol("IDX"), makeSymbol("INDEX"))))))));

    public static final SubLObject subloop_reserved_initialize_sinv_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sinv_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_sinv_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sinv_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt140 = list(makeSymbol("STREAM"), makeSymbol("DEPTH"));

    static private final SubLList $list_alt141 = list(makeString("Pretty-prints this phrase-tree to STREAM, ignoring DEPTH"), list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(RET, list(makeSymbol("PPRINT-PARSE-TREE"), makeSymbol("SELF"), makeSymbol("STREAM"), ZERO_INTEGER)));

    static private final SubLString $str_alt143$__W_ = makeString("#<W(");

    public static final SubLObject sinv_tree_p_alt(SubLObject sinv_tree) {
        return classes.subloop_instanceof_class(sinv_tree, SINV_TREE);
    }

    public static SubLObject sinv_tree_p(final SubLObject sinv_tree) {
        return classes.subloop_instanceof_class(sinv_tree, SINV_TREE);
    }

    static private final SubLString $str_alt144$_ = makeString(")");

    static private final SubLString $str_alt146$_ = makeString("*");

    static private final SubLString $str_alt147$__ = makeString(": ");

    public static final SubLObject subloop_reserved_initialize_prepositional_or_particle_phrase_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_prepositional_or_particle_phrase_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    static private final SubLString $str_alt149$_ = makeString(">");

    static private final SubLList $list_alt151 = list(list(makeSymbol("SUB-TREE"), makeSymbol("PARSE-TREE"), makeSymbol("&OPTIONAL"), makeSymbol("DONE?"), list(makeSymbol("CATEGORIES"), makeKeyword("ALL")), list(makeSymbol("ORDER"), makeKeyword("DFR2L"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final SubLObject subloop_reserved_initialize_prepositional_or_particle_phrase_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_prepositional_or_particle_phrase_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt158 = list(list(makeSymbol("CREATE-STACK")));

    static private final SubLList $list_alt159 = list(list(makeSymbol("LIST"), makeKeyword("DFL2R"), makeKeyword("DFR2L")));

    static private final SubLList $list_alt162 = list(list(makeSymbol("FUNCTION"), EQ));

    public static final SubLObject prepositional_or_particle_phrase_tree_p_alt(SubLObject prepositional_or_particle_phrase_tree) {
        return classes.subloop_instanceof_class(prepositional_or_particle_phrase_tree, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE);
    }

    public static SubLObject prepositional_or_particle_phrase_tree_p(final SubLObject prepositional_or_particle_phrase_tree) {
        return classes.subloop_instanceof_class(prepositional_or_particle_phrase_tree, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE);
    }

    static private final SubLString $str_alt163$_S_is_not_one_of__S = makeString("~S is not one of ~S");

    /**
     *
     *
     * @return listp; the head daughters of this prepositional-or-particle-phrase-tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this prepositional-or-particle-phrase-tree")
    public static final SubLObject prepositional_or_particle_phrase_tree_get_head_daughters_method_alt(SubLObject self) {
        if (NIL != word_tree.vbg_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER))) {
            return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER));
        }
        return append(methods.funcall_instance_method_with_1_args(self, FIND, PREPOSITIONAL_TREE_P), methods.funcall_instance_method_with_1_args(self, FIND, PARTICLE_TREE_P));
    }

    /**
     *
     *
     * @return listp; the head daughters of this prepositional-or-particle-phrase-tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this prepositional-or-particle-phrase-tree")
    public static SubLObject prepositional_or_particle_phrase_tree_get_head_daughters_method(final SubLObject self) {
        if (NIL != word_tree.vbg_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER))) {
            return list(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER));
        }
        return append(methods.funcall_instance_method_with_1_args(self, FIND, PREPOSITIONAL_TREE_P), methods.funcall_instance_method_with_1_args(self, FIND, PARTICLE_TREE_P));
    }

    static private final SubLList $list_alt176 = list(makeKeyword("DFL2R"));

    static private final SubLList $list_alt179 = list(makeKeyword("ALL"));

    static private final SubLList $list_alt181 = list(list(QUOTE, makeSymbol("GET-CATEGORY")));

    static private final SubLList $list_alt182 = list(list(makeSymbol("WORD-TREE"), makeSymbol("PARSE-TREE"), makeSymbol("&OPTIONAL"), makeSymbol("DONE?"), list(makeSymbol("CATEGORIES"), makeKeyword("ALL"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    /**
     *
     *
     * @return nominal-phrase-tree; the complement np of the prepositional head of this phrase
     */
    @LispMethod(comment = "@return nominal-phrase-tree; the complement np of the prepositional head of this phrase")
    public static final SubLObject prepositional_or_particle_phrase_tree_get_oblique_object_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_OBLIQUE_OBJECT))) : NIL;
        }
    }

    /**
     *
     *
     * @return nominal-phrase-tree; the complement np of the prepositional head of this phrase
     */
    @LispMethod(comment = "@return nominal-phrase-tree; the complement np of the prepositional head of this phrase")
    public static SubLObject prepositional_or_particle_phrase_tree_get_oblique_object_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_OBLIQUE_OBJECT) : NIL;
    }

    static private final SubLList $list_alt185 = list(new SubLObject[]{ list(makeSymbol("DAUGHTERS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-STRING"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DAUGHTER"), list(makeSymbol("INDEX")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DESCENDANT"), list(makeSymbol("PATH")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SEARCH"), list(makeSymbol("STRING")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-REFS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DAUGHTER-COUNT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("YIELD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PARSE-EXPRESSION"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEAD-DAUGHTER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-HEADS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-MODIFIERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-CONJUNCTION"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COORDINATE-PHRASE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), list(makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RETOKENIZE"), list(makeSymbol("WORDS"), makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RETOKENIZE-INT"), list(makeSymbol("WORDS"), makeSymbol("LEXICON")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LOGICAL-FORM-INT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FIND"), list(makeSymbol("PREDICATE")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FIND-FIRST"), list(makeSymbol("PREDICATE")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FIND-LAST"), list(makeSymbol("PREDICATE")), makeKeyword("PROTECTED")) });

    /**
     *
     *
     * @return booleanp; non-nil if the preposition or particle of this phrase is
    stranded, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; non-nil if the preposition or particle of this phrase is\r\nstranded, nil otherwise")
    public static final SubLObject prepositional_or_particle_phrase_tree_stranded_p_method_alt(SubLObject self) {
        return makeBoolean(methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(ONE_INTEGER) && (NIL != word_tree.prepositional_or_particle_word_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER))));
    }

    /**
     *
     *
     * @return booleanp; non-nil if the preposition or particle of this phrase is
    stranded, nil otherwise
     */
    @LispMethod(comment = "@return booleanp; non-nil if the preposition or particle of this phrase is\r\nstranded, nil otherwise")
    public static SubLObject prepositional_or_particle_phrase_tree_stranded_p_method(final SubLObject self) {
        return makeBoolean(methods.funcall_instance_method_with_0_args(self, DAUGHTER_COUNT).numE(ONE_INTEGER) && (NIL != word_tree.prepositional_or_particle_word_tree_p(methods.funcall_instance_method_with_1_args(self, GET_DAUGHTER, ZERO_INTEGER))));
    }

    public static final SubLObject subloop_reserved_initialize_prepositional_phrase_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_prepositional_phrase_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_prepositional_phrase_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_prepositional_phrase_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject prepositional_phrase_tree_p_alt(SubLObject prepositional_phrase_tree) {
        return classes.subloop_instanceof_class(prepositional_phrase_tree, PREPOSITIONAL_PHRASE_TREE);
    }

    public static SubLObject prepositional_phrase_tree_p(final SubLObject prepositional_phrase_tree) {
        return classes.subloop_instanceof_class(prepositional_phrase_tree, PREPOSITIONAL_PHRASE_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_pp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_pp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_pp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_pp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject pp_tree_p_alt(SubLObject pp_tree) {
        return classes.subloop_instanceof_class(pp_tree, PP_TREE);
    }

    public static SubLObject pp_tree_p(final SubLObject pp_tree) {
        return classes.subloop_instanceof_class(pp_tree, PP_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_whpp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whpp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_whpp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whpp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt190 = list(makeString("@return listp; the list of head daughers of this phrase"), list(RET, NIL));

    static private final SubLList $list_alt193 = list(makeString("@return parse-tree-p; the (first) head daughter of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS"))))));

    public static final SubLObject whpp_tree_p_alt(SubLObject whpp_tree) {
        return classes.subloop_instanceof_class(whpp_tree, WHPP_TREE);
    }

    public static SubLObject whpp_tree_p(final SubLObject whpp_tree) {
        return classes.subloop_instanceof_class(whpp_tree, WHPP_TREE);
    }

    static private final SubLList $list_alt195 = list(makeString("@return word-tree-p; the (first) head of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEADS"))))));

    public static final SubLObject subloop_reserved_initialize_prt_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_prt_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_prt_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_prt_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt199 = list(makeString("@return listp; the list of head daughers of this phrase"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS")))));

    static private final SubLList $list_alt202 = list(makeString("@return parse-tree-p; the (first) semantic head daughter of this phrase\n   @note currently wasteful, since all heads are computed, then discarded"), list(RET, list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD-DAUGHTERS"))))));

    public static final SubLObject prt_tree_p_alt(SubLObject prt_tree) {
        return classes.subloop_instanceof_class(prt_tree, PRT_TREE);
    }

    public static SubLObject prt_tree_p(final SubLObject prt_tree) {
        return classes.subloop_instanceof_class(prt_tree, PRT_TREE);
    }

    /**
     *
     *
     * @return listp; the head daughters of this prepositional-or-particle-phrase-tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this prepositional-or-particle-phrase-tree")
    public static final SubLObject prt_tree_get_head_daughters_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_1_args(self, FIND, PARTICLE_TREE_P);
    }

    /**
     *
     *
     * @return listp; the head daughters of this prepositional-or-particle-phrase-tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this prepositional-or-particle-phrase-tree")
    public static SubLObject prt_tree_get_head_daughters_method(final SubLObject self) {
        return methods.funcall_instance_method_with_1_args(self, FIND, PARTICLE_TREE_P);
    }

    public static final SubLObject subloop_reserved_initialize_adjp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_adjp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    static private final SubLList $list_alt205 = list(makeKeyword("READ-ONLY"), makeKeyword("PUBLIC"));

    static private final SubLList $list_alt206 = list(makeString("@return listp; the unique referents of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS")))), list(makeSymbol("REFS"), NIL)), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("CNOT"), makeSymbol("HEADS")), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), ONE_INTEGER)), list(makeSymbol("CLET"), list(list(makeSymbol("DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER))), list(makeSymbol("PIF"), list(makeSymbol("CAND"), list(makeSymbol("QUANTIFIER-TREE-P"), makeSymbol("DAUGHTER")), list(makeSymbol("PHRASE-TREE-P"), makeSymbol("DAUGHTER")), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("$"))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), makeSymbol("HEADS")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("HEADS"))))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), makeSymbol("HEADS")), list(makeSymbol("CDOLIST"), list(makeSymbol("REF"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-REFS")))), list(makeSymbol("CPUSH"), makeSymbol("REF"), makeSymbol("REFS")))), list(RET, makeSymbol("REFS"))));

    public static final SubLObject subloop_reserved_initialize_adjp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_adjp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject adjp_tree_p_alt(SubLObject adjp_tree) {
        return classes.subloop_instanceof_class(adjp_tree, ADJP_TREE);
    }

    public static SubLObject adjp_tree_p(final SubLObject adjp_tree) {
        return classes.subloop_instanceof_class(adjp_tree, ADJP_TREE);
    }

    /**
     *
     *
     * @return listp; the head daughters of this adjp-tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this adjp-tree")
    public static final SubLObject adjp_tree_get_head_daughters_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_1_args(self, FIND, ADJECTIVAL_TREE_P);
    }

    /**
     *
     *
     * @return listp; the head daughters of this adjp-tree
     */
    @LispMethod(comment = "@return listp; the head daughters of this adjp-tree")
    public static SubLObject adjp_tree_get_head_daughters_method(final SubLObject self) {
        return methods.funcall_instance_method_with_1_args(self, FIND, ADJECTIVAL_TREE_P);
    }

    /**
     *
     *
     * @return listp; the complement of this adjective
     */
    @LispMethod(comment = "@return listp; the complement of this adjective")
    public static final SubLObject adjp_tree_get_modifieds_method_alt(SubLObject self) {
        {
            SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
            return NIL != head ? ((SubLObject) (methods.funcall_instance_method_with_0_args(head, GET_MODIFIEDS))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; the complement of this adjective
     */
    @LispMethod(comment = "@return listp; the complement of this adjective")
    public static SubLObject adjp_tree_get_modifieds_method(final SubLObject self) {
        final SubLObject head = methods.funcall_instance_method_with_0_args(self, GET_HEAD);
        return NIL != head ? methods.funcall_instance_method_with_0_args(head, GET_MODIFIEDS) : NIL;
    }

    public static final SubLObject subloop_reserved_initialize_whadjp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whadjp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_whadjp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whadjp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt210 = list(makeString("@return listp; the list of all modifiers of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-MODIFIERS")))))));

    static private final SubLList $list_alt213 = list(makeString("@return object; the conjunction of this tree if there is one, nil otherwise"), list(RET, list(makeSymbol("FIND-FIRST"), makeSymbol("SELF"), list(QUOTE, makeSymbol("COORDINATE-WORD-TREE-P")))));

    public static final SubLObject whadjp_tree_p_alt(SubLObject whadjp_tree) {
        return classes.subloop_instanceof_class(whadjp_tree, WHADJP_TREE);
    }

    public static SubLObject whadjp_tree_p(final SubLObject whadjp_tree) {
        return classes.subloop_instanceof_class(whadjp_tree, WHADJP_TREE);
    }

    /**
     *
     *
     * @return parse-tree-p; a version of this np in which multi-word
    lemmata have been conflated into single words and each word has
    lexical entries added
     * @unknown this tree's daughters will be destructively modified
     */
    @LispMethod(comment = "@return parse-tree-p; a version of this np in which multi-word\r\nlemmata have been conflated into single words and each word has\r\nlexical entries added\r\n@unknown this tree\'s daughters will be destructively modified")
    public static final SubLObject whadjp_tree_lexify_int_method_alt(SubLObject self, SubLObject lexicon) {
        SubLTrampolineFile.checkType(lexicon, ABSTRACT_LEXICON_P);
        {
            SubLObject vector_var = com.cyc.cycjava.cycl.parse_tree.get_phrase_tree_daughters(self);
            SubLObject backwardP_var = NIL;
            SubLObject length = length(vector_var);
            SubLObject v_iteration = NIL;
            for (v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                {
                    SubLObject element_num = (NIL != backwardP_var) ? ((SubLObject) (subtract(length, v_iteration, ONE_INTEGER))) : v_iteration;
                    SubLObject daughter = aref(vector_var, element_num);
                    methods.funcall_instance_method_with_1_args(daughter, LEXIFY_INT, lexicon);
                }
            }
        }
        return self;
    }

    /**
     *
     *
     * @return parse-tree-p; a version of this np in which multi-word
    lemmata have been conflated into single words and each word has
    lexical entries added
     * @unknown this tree's daughters will be destructively modified
     */
    @LispMethod(comment = "@return parse-tree-p; a version of this np in which multi-word\r\nlemmata have been conflated into single words and each word has\r\nlexical entries added\r\n@unknown this tree\'s daughters will be destructively modified")
    public static SubLObject whadjp_tree_lexify_int_method(final SubLObject self, final SubLObject lexicon) {
        assert NIL != abstract_lexicon.abstract_lexicon_p(lexicon) : "! abstract_lexicon.abstract_lexicon_p(lexicon) " + ("abstract_lexicon.abstract_lexicon_p(lexicon) " + "CommonSymbols.NIL != abstract_lexicon.abstract_lexicon_p(lexicon) ") + lexicon;
        final SubLObject vector_var = get_phrase_tree_daughters(self);
        final SubLObject backwardP_var = NIL;
        SubLObject length;
        SubLObject v_iteration;
        SubLObject element_num;
        SubLObject daughter;
        for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
            element_num = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
            daughter = aref(vector_var, element_num);
            methods.funcall_instance_method_with_1_args(daughter, LEXIFY_INT, lexicon);
        }
        return self;
    }

    static private final SubLList $list_alt216 = list(makeKeyword("NO-MEMBER-VARIABLES"), makeKeyword("PROTECTED"));

    static private final SubLList $list_alt217 = list(makeString("@return phrase-tree-p; this phrase-tree with lexical entries added"), list(makeSymbol("CLET"), list(makeSymbol("WORDS"), makeSymbol("ORIG-SPELLING"), makeSymbol("NORMALIZED-SPELLING"), makeSymbol("FIRST-WORD1"), makeSymbol("FIRST-WORD2"), makeSymbol("YIELD"), list(makeSymbol("DAUGHTERS"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF")))), list(new SubLObject[]{ makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ROOT-P"))), list(makeSymbol("CSETQ"), makeSymbol("YIELD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("YIELD")))), list(makeSymbol("CSETQ"), makeSymbol("WORDS"), list(makeSymbol("TREE-YIELD-TO-WORDS"), makeSymbol("YIELD"))), list(makeSymbol("CSETQ"), makeSymbol("ORIG-SPELLING"), list(makeSymbol("WORD-STRING"), list(makeSymbol("AREF"), makeSymbol("WORDS"), ZERO_INTEGER))), list(makeSymbol("TAGGER-NORMALIZE-INITIAL-CAPITALIZATION"), list(makeSymbol("GET-TAGGER")), makeSymbol("WORDS")), list(makeSymbol("CSETQ"), makeSymbol("NORMALIZED-SPELLING"), list(makeSymbol("WORD-STRING"), list(makeSymbol("AREF"), makeSymbol("WORDS"), ZERO_INTEGER))), list(makeSymbol("CSETQ"), makeSymbol("FIRST-WORD1"), list(makeSymbol("FIRST"), makeSymbol("YIELD"))), list(makeSymbol("SET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD1"), makeSymbol("NORMALIZED-SPELLING")) }), list(makeSymbol("CDOVECTOR"), list(makeSymbol("DAUGHTER"), makeSymbol("DAUGHTERS")), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ROOT-P"))), list(makeSymbol("CSETQ"), makeSymbol("FIRST-WORD2"), list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("YIELD"))))), list(makeSymbol("PIF"), list(EQUALP, list(makeSymbol("FIM"), makeSymbol("FIRST-WORD1"), list(QUOTE, makeSymbol("GET-STRING"))), list(makeSymbol("FIM"), makeSymbol("FIRST-WORD2"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("SET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD2"), makeSymbol("ORIG-SPELLING")), list(makeSymbol("CLET"), list(list(makeSymbol("TOKENS"), list(makeSymbol("SPLIT-STRING"), list(makeSymbol("GET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD2")))), list(makeSymbol("NEW-STRING"), list(makeSymbol("BUNGE"), list(makeSymbol("CONS"), makeSymbol("ORIG-SPELLING"), list(makeSymbol("CDR"), makeSymbol("TOKENS")))))), list(makeSymbol("SET-PARSE-TREE-STRING"), makeSymbol("FIRST-WORD2"), makeSymbol("NEW-STRING")))), list(makeSymbol("PROCESS-MODIFIERS"), list(makeSymbol("PROCESS-ABS-INDEX"), makeSymbol("SELF")))), list(RET, makeSymbol("SELF"))));

    public static final SubLObject subloop_reserved_initialize_advp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_advp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_advp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_advp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject advp_tree_p_alt(SubLObject advp_tree) {
        return classes.subloop_instanceof_class(advp_tree, ADVP_TREE);
    }

    public static SubLObject advp_tree_p(final SubLObject advp_tree) {
        return classes.subloop_instanceof_class(advp_tree, ADVP_TREE);
    }

    /**
     *
     *
     * @return listp; a list of head daughters of this adverbial tree
     */
    @LispMethod(comment = "@return listp; a list of head daughters of this adverbial tree")
    public static final SubLObject advp_tree_get_head_daughters_method_alt(SubLObject self) {
        {
            SubLObject head_dtr = methods.funcall_instance_method_with_1_args(self, FIND_LAST, ADVERBIAL_TREE_P);
            return NIL != head_dtr ? ((SubLObject) (list(head_dtr))) : NIL;
        }
    }

    /**
     *
     *
     * @return listp; a list of head daughters of this adverbial tree
     */
    @LispMethod(comment = "@return listp; a list of head daughters of this adverbial tree")
    public static SubLObject advp_tree_get_head_daughters_method(final SubLObject self) {
        final SubLObject head_dtr = methods.funcall_instance_method_with_1_args(self, FIND_LAST, ADVERBIAL_TREE_P);
        return NIL != head_dtr ? list(head_dtr) : NIL;
    }

    public static final SubLObject subloop_reserved_initialize_whadvp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whadvp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_whadvp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_whadvp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt220 = list(list(makeSymbol("CLET"), list(makeSymbol("RESULT")), list(makeSymbol("PCOND"), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PRETERMINAL-P"))), list(makeSymbol("CSETQ"), makeSymbol("RESULT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("RETOKENIZE-INT")), list(makeSymbol("VECTOR-ELEMENTS"), list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTERS")))), makeSymbol("LEXICON")))), list(list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("GET")), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CSETQ"), makeSymbol("RESULT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("RETOKENIZE-INT")), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("YIELD"))), makeSymbol("LEXICON")))), list(list(makeSymbol("CAND"), makeSymbol("*PHRASE-TREE-ALLOW-ADJACENT-DAUGHTERS-TO-MERGE*"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ADJACENT-DAUGHTERS-HAVE-LEXICAL-ENTRY")), makeSymbol("LEXICON"))), list(makeSymbol("CDESTRUCTURING-BIND"), list(makeSymbol("WORD-LISTS"), makeSymbol("CONSTIT-LISTS")), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ADJACENT-DAUGHTERS-HAVE-LEXICAL-ENTRY")), makeSymbol("LEXICON")), list(makeSymbol("CSETQ"), makeSymbol("RESULT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PARTIAL-RETOKENIZE-INT")), makeSymbol("WORD-LISTS"), makeSymbol("CONSTIT-LISTS"), makeSymbol("LEXICON"))))), list(T, list(makeSymbol("CSETQ"), makeSymbol("RESULT"), makeSymbol("SELF")))), list(RET, makeSymbol("RESULT"))));

    public static final SubLObject whadvp_tree_p_alt(SubLObject whadvp_tree) {
        return classes.subloop_instanceof_class(whadvp_tree, WHADVP_TREE);
    }

    public static SubLObject whadvp_tree_p(final SubLObject whadvp_tree) {
        return classes.subloop_instanceof_class(whadvp_tree, WHADVP_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_frag_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_frag_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_frag_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_frag_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static final SubLObject frag_tree_p_alt(SubLObject frag_tree) {
        return classes.subloop_instanceof_class(frag_tree, FRAG_TREE);
    }

    public static SubLObject frag_tree_p(final SubLObject frag_tree) {
        return classes.subloop_instanceof_class(frag_tree, FRAG_TREE);
    }

    public static final SubLObject subloop_reserved_initialize_qp_tree_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_qp_tree_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_qp_tree_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_qp_tree_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, STRING, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CATEGORY, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MOTHER, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, IDX, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, MODIFIERS, NIL);
        classes.subloop_initialize_slot(new_instance, PARSE_TREE, CONTEXT, NIL);
        classes.subloop_initialize_slot(new_instance, PHRASE_TREE, DAUGHTERS, NIL);
        return NIL;
    }

    static private final SubLList $list_alt225 = list(makeSymbol("WORD-LISTS"), makeSymbol("CONSTIT-LISTS"));

    static private final SubLList $list_alt228 = list(makeSymbol("WORDS"), makeSymbol("LEXICON"));

    static private final SubLList $list_alt229 = list(list(makeSymbol("CLET"), list(list(makeSymbol("TOKENS"), list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("LEXIFY-WORDS")), makeSymbol("WORDS"))), list(makeSymbol("NEW-DAUGHTERS"), list(makeSymbol("MAKE-VECTOR"), list(makeSymbol("LENGTH"), makeSymbol("TOKENS")))), list(makeSymbol("I"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("TOKEN"), makeSymbol("TOKENS")), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-DAUGHTER"), list(makeSymbol("NEW-WORD-TREE"), list(makeSymbol("FIRST"), makeSymbol("TOKEN")), list(makeSymbol("SECOND"), makeSymbol("TOKEN")), makeSymbol("SELF"), makeSymbol("I"), NIL, makeSymbol("CONTEXT")))), list(makeSymbol("SET-AREF"), makeSymbol("NEW-DAUGHTERS"), makeSymbol("I"), makeSymbol("NEW-DAUGHTER")), list(makeSymbol("CINC"), makeSymbol("I")))), list(makeSymbol("SET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"), makeSymbol("NEW-DAUGHTERS"))), list(RET, makeSymbol("SELF")));

    public static final SubLObject qp_tree_p_alt(SubLObject qp_tree) {
        return classes.subloop_instanceof_class(qp_tree, QP_TREE);
    }

    public static SubLObject qp_tree_p(final SubLObject qp_tree) {
        return classes.subloop_instanceof_class(qp_tree, QP_TREE);
    }

    /**
     *
     *
     * @return listp; a list of all head daughters of this phrase
     */
    @LispMethod(comment = "@return listp; a list of all head daughters of this phrase")
    public static final SubLObject qp_tree_get_head_daughters_method_alt(SubLObject self) {
        return com.cyc.cycjava.cycl.parse_tree.phrase_tree_find_method(self, CARDINAL_WORD_TREE_P, UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return listp; a list of all head daughters of this phrase
     */
    @LispMethod(comment = "@return listp; a list of all head daughters of this phrase")
    public static SubLObject qp_tree_get_head_daughters_method(final SubLObject self) {
        return phrase_tree_find_method(self, CARDINAL_WORD_TREE_P, UNPROVIDED, UNPROVIDED);
    }

    /**
     *
     *
     * @return word-tree; the word that restricts the variable that this determiner quantifies
     */
    @LispMethod(comment = "@return word-tree; the word that restricts the variable that this determiner quantifies")
    public static final SubLObject qp_tree_get_quantified_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_qp_tree_method = NIL;
            SubLObject mother = com.cyc.cycjava.cycl.parse_tree.get_parse_tree_mother(self);
            try {
                try {
                    sublisp_throw($sym604$OUTER_CATCH_FOR_QP_TREE_METHOD, NIL != mother ? ((SubLObject) (methods.funcall_instance_method_with_0_args(mother, GET_HEAD))) : NIL);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.parse_tree.set_parse_tree_mother(self, mother);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_qp_tree_method = Errors.handleThrowable(ccatch_env_var, $sym604$OUTER_CATCH_FOR_QP_TREE_METHOD);
            }
            return catch_var_for_qp_tree_method;
        }
    }

    /**
     *
     *
     * @return word-tree; the word that restricts the variable that this determiner quantifies
     */
    @LispMethod(comment = "@return word-tree; the word that restricts the variable that this determiner quantifies")
    public static SubLObject qp_tree_get_quantified_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_qp_tree_method = NIL;
        final SubLObject mother = get_parse_tree_mother(self);
        try {
            thread.throwStack.push($sym604$OUTER_CATCH_FOR_QP_TREE_METHOD);
            try {
                sublisp_throw($sym604$OUTER_CATCH_FOR_QP_TREE_METHOD, NIL != mother ? methods.funcall_instance_method_with_0_args(mother, GET_HEAD) : NIL);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_parse_tree_mother(self, mother);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_qp_tree_method = Errors.handleThrowable(ccatch_env_var, $sym604$OUTER_CATCH_FOR_QP_TREE_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_qp_tree_method;
    }

    static private final SubLList $list_alt233 = list(makeSymbol("WORD-LISTS"), makeSymbol("DAUGHTER-LISTS"), makeSymbol("LEXICON"));

    static private final SubLList $list_alt234 = list(makeString("retokenize only WORD-LISTS according to LEXICON.  There may be additional words in PHRASE-TREE\n   that do not get retokenized. Those words will be re-attached to the phrase with no modification."), list(makeSymbol("CLET"), list(list(makeSymbol("NEW-DAUGHTERS"))), list(makeSymbol("CDOLIST-MULTIPLE"), list(list(makeSymbol("WORDS"), makeSymbol("WORD-LISTS")), list(makeSymbol("DAUGHTERS"), makeSymbol("DAUGHTER-LISTS"))), list(makeSymbol("PCOND"), list(makeSymbol("WORDS"), list(makeSymbol("CLET"), list(list(makeSymbol("TOKENS"), list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("LEXIFY-WORDS")), makeSymbol("WORDS"))), list(makeSymbol("I"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("TOKEN"), makeSymbol("TOKENS")), list(makeSymbol("CPUSH"), list(makeSymbol("NEW-WORD-TREE"), list(makeSymbol("FIRST"), makeSymbol("TOKEN")), list(makeSymbol("SECOND"), makeSymbol("TOKEN")), makeSymbol("SELF"), makeSymbol("I"), NIL, list(makeSymbol("GET-SLOT"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CONTEXT")))), makeSymbol("NEW-DAUGHTERS"))))), list(T, list(makeSymbol("CPUSH-ALL"), makeSymbol("DAUGHTERS"), makeSymbol("NEW-DAUGHTERS"))))), list(makeSymbol("CLET"), list(list(makeSymbol("REPLACEMENT-DAUGHTERS"), list(makeSymbol("LIST2VECTOR"), list(makeSymbol("NREVERSE"), makeSymbol("NEW-DAUGHTERS"))))), list(makeSymbol("SET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"), makeSymbol("REPLACEMENT-DAUGHTERS")))), list(RET, makeSymbol("SELF")));

    /**
     *
     *
     * @return listp; the CycL representation of the meaning of this quantifier phrase
     */
    @LispMethod(comment = "@return listp; the CycL representation of the meaning of this quantifier phrase")
    public static final SubLObject qp_tree_cyclify_int_method_alt(SubLObject self) {
        {
            SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
            if (NIL != scycls) {
                return scycls;
            }
            {
                SubLObject cycls = NIL;
                SubLObject number = numeral_parser.string_to_interval(methods.funcall_instance_method_with_0_args(self, GET_STRING));
                SubLObject quantified = methods.funcall_instance_method_with_0_args(self, GET_QUANTIFIED);
                SubLObject restrictions = (NIL != quantified) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(quantified, CYCLIFY_NUCLEUS))) : NIL;
                SubLObject variable = (NIL != quantified) ? ((SubLObject) (methods.funcall_instance_method_with_0_args(quantified, GET_REFS).first())) : NIL;
                if (NIL != number) {
                    {
                        SubLObject cdolist_list_var = restrictions;
                        SubLObject restriction = NIL;
                        for (restriction = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , restriction = cdolist_list_var.first()) {
                            cycls = cons(cyclifier.new_cyclification(list($$thereExistAtLeast, number, variable, listS($$and, restriction, $list_alt610)), self, ONE_INTEGER), cycls);
                        }
                    }
                }
                return cycls;
            }
        }
    }

    /**
     *
     *
     * @return listp; the CycL representation of the meaning of this quantifier phrase
     */
    @LispMethod(comment = "@return listp; the CycL representation of the meaning of this quantifier phrase")
    public static SubLObject qp_tree_cyclify_int_method(final SubLObject self) {
        final SubLObject scycls = subcyclifier.subcyclifier_cyclify(self);
        if (NIL != scycls) {
            return scycls;
        }
        SubLObject cycls = NIL;
        final SubLObject number = numeral_parser.string_to_interval(methods.funcall_instance_method_with_0_args(self, GET_STRING));
        final SubLObject quantified = methods.funcall_instance_method_with_0_args(self, GET_QUANTIFIED);
        final SubLObject restrictions = (NIL != quantified) ? methods.funcall_instance_method_with_0_args(quantified, CYCLIFY_NUCLEUS) : NIL;
        final SubLObject variable = (NIL != quantified) ? methods.funcall_instance_method_with_0_args(quantified, GET_REFS).first() : NIL;
        if (NIL != number) {
            SubLObject cdolist_list_var = restrictions;
            SubLObject restriction = NIL;
            restriction = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                cycls = cons(cyclifier.new_cyclification(list($$thereExistAtLeast, number, variable, listS($$and, restriction, $list610)), self, ONE_INTEGER), cycls);
                cdolist_list_var = cdolist_list_var.rest();
                restriction = cdolist_list_var.first();
            } 
        }
        return cycls;
    }

    static private final SubLList $list_alt236 = list(list(makeSymbol("CLET"), list(list(makeSymbol("WORD-LISTS")), list(makeSymbol("DAUGHTER-LISTS")), list(makeSymbol("ALL-DAUGHTERS"), list(makeSymbol("VECTOR2LIST"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF")))), list(makeSymbol("DAUGHTER-COUNT"), list(makeSymbol("LENGTH"), makeSymbol("ALL-DAUGHTERS"))), list(makeSymbol("STRING"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CURRENT-START"), ZERO_INTEGER)), list(makeSymbol("WHILE"), list(makeSymbol("<"), makeSymbol("CURRENT-START"), makeSymbol("DAUGHTER-COUNT")), list(makeSymbol("CLET"), list(list(makeSymbol("NUM-DAUGHTERS-USED"), NIL), list(makeSymbol("START-FOR-ITERATION"), makeSymbol("CURRENT-START"))), list(makeSymbol("DO-NUMBERS"), list(new SubLObject[]{ makeSymbol("NUM"), makeKeyword("START"), makeSymbol("DAUGHTER-COUNT"), makeKeyword("END"), makeSymbol("START-FOR-ITERATION"), makeKeyword("DELTA"), MINUS_ONE_INTEGER, $DONE, makeSymbol("NUM-DAUGHTERS-USED") }), list(makeSymbol("CLET"), list(list(makeSymbol("DAUGHTERS"), list(makeSymbol("FIRST-N"), makeSymbol("NUM"), makeSymbol("ALL-DAUGHTERS"))), list(makeSymbol("YIELD"), list(makeSymbol("MAPCAN"), list(makeSymbol("FUNCTION"), makeSymbol("COPY-LIST")), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("PHRASE-TREE-YIELD")), makeSymbol("DAUGHTERS")))), list(makeSymbol("RAW-STRING"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("PHRASE-TREE-STRING")), makeSymbol("YIELD"))), list(makeSymbol("CURRENT-STRING"), list(makeSymbol("BUNGE-ACCORDING-TO-STRING"), makeSymbol("RAW-STRING"), makeSymbol("STRING")))), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("LEXICON"), list(QUOTE, makeSymbol("GET")), makeSymbol("CURRENT-STRING")), list(makeSymbol("CPUSH"), makeSymbol("YIELD"), makeSymbol("WORD-LISTS")), list(makeSymbol("CPUSH"), list(makeSymbol("FIRST-N"), makeSymbol("NUM"), makeSymbol("ALL-DAUGHTERS")), makeSymbol("DAUGHTER-LISTS")), list(makeSymbol("CSETQ"), makeSymbol("NUM-DAUGHTERS-USED"), makeSymbol("NUM")), list(makeSymbol("CINC"), makeSymbol("CURRENT-START"), makeSymbol("NUM-DAUGHTERS-USED"))))), list(makeSymbol("PUNLESS"), makeSymbol("NUM-DAUGHTERS-USED"), list(makeSymbol("CPUSH"), NIL, makeSymbol("WORD-LISTS")), list(makeSymbol("CPUSH"), list(makeSymbol("LIST"), list(makeSymbol("NTH"), makeSymbol("CURRENT-START"), makeSymbol("ALL-DAUGHTERS"))), makeSymbol("DAUGHTER-LISTS")), list(makeSymbol("CINC"), makeSymbol("CURRENT-START"))))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("NREVERSE"), makeSymbol("WORD-LISTS")), list(makeSymbol("NREVERSE"), makeSymbol("DAUGHTER-LISTS"))))));

    public static SubLObject declare_parse_tree_file() {
        declareFunction("nominal_tree_p", "NOMINAL-TREE-P", 1, 0, false);
        declareFunction("verbal_tree_p", "VERBAL-TREE-P", 1, 0, false);
        declareFunction("adverbial_tree_p", "ADVERBIAL-TREE-P", 1, 0, false);
        declareFunction("prepositional_tree_p", "PREPOSITIONAL-TREE-P", 1, 0, false);
        declareFunction("particle_tree_p", "PARTICLE-TREE-P", 1, 0, false);
        declareFunction("adjectival_tree_p", "ADJECTIVAL-TREE-P", 1, 0, false);
        declareFunction("question_tree_p", "QUESTION-TREE-P", 1, 0, false);
        declareFunction("wh_tree_p", "WH-TREE-P", 1, 0, false);
        declareFunction("numerical_tree_p", "NUMERICAL-TREE-P", 1, 0, false);
        declareFunction("finite_verbal_word_tree_p", "FINITE-VERBAL-WORD-TREE-P", 1, 0, false);
        declareFunction("quantifier_tree_p", "QUANTIFIER-TREE-P", 1, 0, false);
        declareFunction("pronoun_tree_p", "PRONOUN-TREE-P", 1, 0, false);
        declareFunction("get_parse_tree_context", "GET-PARSE-TREE-CONTEXT", 1, 0, false);
        declareFunction("set_parse_tree_context", "SET-PARSE-TREE-CONTEXT", 2, 0, false);
        declareFunction("get_parse_tree_modifiers", "GET-PARSE-TREE-MODIFIERS", 1, 0, false);
        declareFunction("set_parse_tree_modifiers", "SET-PARSE-TREE-MODIFIERS", 2, 0, false);
        declareFunction("get_parse_tree_idx", "GET-PARSE-TREE-IDX", 1, 0, false);
        declareFunction("set_parse_tree_idx", "SET-PARSE-TREE-IDX", 2, 0, false);
        declareFunction("get_parse_tree_mother", "GET-PARSE-TREE-MOTHER", 1, 0, false);
        declareFunction("set_parse_tree_mother", "SET-PARSE-TREE-MOTHER", 2, 0, false);
        declareFunction("get_parse_tree_category", "GET-PARSE-TREE-CATEGORY", 1, 0, false);
        declareFunction("set_parse_tree_category", "SET-PARSE-TREE-CATEGORY", 2, 0, false);
        declareFunction("get_parse_tree_string", "GET-PARSE-TREE-STRING", 1, 0, false);
        declareFunction("set_parse_tree_string", "SET-PARSE-TREE-STRING", 2, 0, false);
        declareFunction("parse_tree_get_cyclifier_method", "PARSE-TREE-GET-CYCLIFIER-METHOD", 1, 1, false);
        declareFunction("parse_tree_cyclify_method", "PARSE-TREE-CYCLIFY-METHOD", 1, 1, false);
        declareFunction("subloop_reserved_initialize_parse_tree_class", "SUBLOOP-RESERVED-INITIALIZE-PARSE-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_parse_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-PARSE-TREE-INSTANCE", 1, 0, false);
        declareFunction("parse_tree_p", "PARSE-TREE-P", 1, 0, false);
        declareFunction("parse_tree_deep_copy_method", "PARSE-TREE-DEEP-COPY-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_root_method", "PARSE-TREE-GET-ROOT-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_category_method", "PARSE-TREE-GET-CATEGORY-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_index_method", "PARSE-TREE-GET-INDEX-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_path_method", "PARSE-TREE-GET-PATH-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_mother_method", "PARSE-TREE-GET-MOTHER-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_semantic_head_method", "PARSE-TREE-GET-SEMANTIC-HEAD-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_complements_method", "PARSE-TREE-GET-COMPLEMENTS-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_modifiers_method", "PARSE-TREE-GET-MODIFIERS-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_modifieds_method", "PARSE-TREE-GET-MODIFIEDS-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_context_method", "PARSE-TREE-GET-CONTEXT-METHOD", 1, 0, false);
        declareFunction("parse_tree_temporal_tree_p_method", "PARSE-TREE-TEMPORAL-TREE-P-METHOD", 1, 0, false);
        declareFunction("parse_tree_cyclifiable_question_p_method", "PARSE-TREE-CYCLIFIABLE-QUESTION-P-METHOD", 1, 0, false);
        declareFunction("parse_tree_root_p_method", "PARSE-TREE-ROOT-P-METHOD", 1, 0, false);
        declareFunction("parse_tree_lexify_method", "PARSE-TREE-LEXIFY-METHOD", 2, 0, false);
        declareFunction("parse_tree_lexify_int_method", "PARSE-TREE-LEXIFY-INT-METHOD", 2, 0, false);
        declareFunction("parse_tree_cyclify_int_method", "PARSE-TREE-CYCLIFY-INT-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_ancestors_method", "PARSE-TREE-GET-ANCESTORS-METHOD", 1, 0, false);
        declareFunction("parse_tree_get_sister_method", "PARSE-TREE-GET-SISTER-METHOD", 2, 0, false);
        declareFunction("parse_tree_print_method", "PARSE-TREE-PRINT-METHOD", 3, 0, false);
        declareFunction("pprint_word_tree", "PPRINT-WORD-TREE", 3, 0, false);
        declareFunction("pprint_parse_tree", "PPRINT-PARSE-TREE", 3, 0, false);
        declareFunction("new_parse_tree", "NEW-PARSE-TREE", 1, 2, false);
        declareFunction("new_parse_tree_int", "NEW-PARSE-TREE-INT", 1, 4, false);
        declareMacro("do_parse_tree_subtrees", "DO-PARSE-TREE-SUBTREES");
        declareMacro("do_parse_tree_word_trees", "DO-PARSE-TREE-WORD-TREES");
        declareFunction("get_phrase_tree_daughters", "GET-PHRASE-TREE-DAUGHTERS", 1, 0, false);
        declareFunction("set_phrase_tree_daughters", "SET-PHRASE-TREE-DAUGHTERS", 2, 0, false);
        declareFunction("subloop_reserved_initialize_phrase_tree_class", "SUBLOOP-RESERVED-INITIALIZE-PHRASE-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_phrase_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-PHRASE-TREE-INSTANCE", 1, 0, false);
        declareFunction("phrase_tree_p", "PHRASE-TREE-P", 1, 0, false);
        declareFunction("phrase_tree_get_head_daughters_method", "PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_head_daughter_method", "PHRASE-TREE-GET-HEAD-DAUGHTER-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_head_method", "PHRASE-TREE-GET-HEAD-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_semantic_head_daughters_method", "PHRASE-TREE-GET-SEMANTIC-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_semantic_head_daughter_method", "PHRASE-TREE-GET-SEMANTIC-HEAD-DAUGHTER-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_refs_method", "PHRASE-TREE-GET-REFS-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_modifiers_method", "PHRASE-TREE-GET-MODIFIERS-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_conjunction_method", "PHRASE-TREE-GET-CONJUNCTION-METHOD", 1, 0, false);
        declareFunction("phrase_tree_lexify_int_method", "PHRASE-TREE-LEXIFY-INT-METHOD", 2, 0, false);
        declareFunction("phrase_tree_retokenize_method", "PHRASE-TREE-RETOKENIZE-METHOD", 2, 0, false);
        declareFunction("phrase_tree_retokenize_int_method", "PHRASE-TREE-RETOKENIZE-INT-METHOD", 3, 0, false);
        declareFunction("phrase_tree_partial_retokenize_int_method", "PHRASE-TREE-PARTIAL-RETOKENIZE-INT-METHOD", 4, 0, false);
        declareFunction("phrase_tree_adjacent_daughters_have_lexical_entry_method", "PHRASE-TREE-ADJACENT-DAUGHTERS-HAVE-LEXICAL-ENTRY-METHOD", 2, 0, false);
        declareFunction("phrase_tree_cyclify_int_method", "PHRASE-TREE-CYCLIFY-INT-METHOD", 1, 0, false);
        declareFunction("parse_expression_to_phrase_tree", "PARSE-EXPRESSION-TO-PHRASE-TREE", 1, 4, false);
        declareFunction("tree_for_category", "TREE-FOR-CATEGORY", 1, 0, false);
        declareFunction("pprint_phrase_tree", "PPRINT-PHRASE-TREE", 3, 0, false);
        declareFunction("phrase_tree_get_string_method", "PHRASE-TREE-GET-STRING-METHOD", 1, 0, false);
        declareFunction("phrase_tree_string", "PHRASE-TREE-STRING", 1, 0, false);
        declareFunction("phrase_tree_yield_method", "PHRASE-TREE-YIELD-METHOD", 1, 0, false);
        declareFunction("phrase_tree_yield", "PHRASE-TREE-YIELD", 1, 0, false);
        declareFunction("phrase_tree_get_parse_expression_method", "PHRASE-TREE-GET-PARSE-EXPRESSION-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_heads_method", "PHRASE-TREE-GET-HEADS-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_semantic_heads_method", "PHRASE-TREE-GET-SEMANTIC-HEADS-METHOD", 1, 0, false);
        declareFunction("phrase_tree_get_daughter_method", "PHRASE-TREE-GET-DAUGHTER-METHOD", 2, 0, false);
        declareFunction("phrase_tree_get_descendant_method", "PHRASE-TREE-GET-DESCENDANT-METHOD", 2, 0, false);
        declareFunction("proper_subtree_p", "PROPER-SUBTREE-P", 2, 0, false);
        declareFunction("subtree_p", "SUBTREE-P", 2, 0, false);
        declareFunction("phrase_tree_search_method", "PHRASE-TREE-SEARCH-METHOD", 2, 1, false);
        declareFunction("phrase_tree_daughter_count_method", "PHRASE-TREE-DAUGHTER-COUNT-METHOD", 1, 0, false);
        declareFunction("phrase_tree_preterminal_p_method", "PHRASE-TREE-PRETERMINAL-P-METHOD", 1, 0, false);
        declareFunction("phrase_tree_hydra_p_method", "PHRASE-TREE-HYDRA-P-METHOD", 1, 0, false);
        declareFunction("phrase_tree_find_method", "PHRASE-TREE-FIND-METHOD", 2, 2, false);
        declareFunction("phrase_tree_find_first_method", "PHRASE-TREE-FIND-FIRST-METHOD", 2, 1, false);
        declareFunction("phrase_tree_find_last_method", "PHRASE-TREE-FIND-LAST-METHOD", 2, 1, false);
        declareFunction("phrase_tree_find_all_method", "PHRASE-TREE-FIND-ALL-METHOD", 2, 2, false);
        declareFunction("phrase_tree_find_all_subtrees_of_categories_method", "PHRASE-TREE-FIND-ALL-SUBTREES-OF-CATEGORIES-METHOD", 2, 2, false);
        declareFunction("tree_yield_to_words", "TREE-YIELD-TO-WORDS", 1, 0, false);
        declareFunction("main_collection", "MAIN-COLLECTION", 2, 0, false);
        declareFunction("contract_stringP", "CONTRACT-STRING?", 1, 0, false);
        declareFunction("rightmost_nominal_word", "RIGHTMOST-NOMINAL-WORD", 1, 1, false);
        declareFunction("nominal_words", "NOMINAL-WORDS", 1, 0, false);
        declareFunction("conflate_nominals", "CONFLATE-NOMINALS", 2, 0, false);
        declareFunction("process_modifiers", "PROCESS-MODIFIERS", 1, 0, false);
        declareFunction("process_abs_index", "PROCESS-ABS-INDEX", 1, 0, false);
        declareFunction("retag_aux_verbs", "RETAG-AUX-VERBS", 1, 0, false);
        declareFunction("clear_modifiers", "CLEAR-MODIFIERS", 1, 0, false);
        declareFunction("quantify_existentially", "QUANTIFY-EXISTENTIALLY", 1, 1, false);
        declareFunction("question_referents", "QUESTION-REFERENTS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_nominal_phrase_tree_class", "SUBLOOP-RESERVED-INITIALIZE-NOMINAL-PHRASE-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_nominal_phrase_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-NOMINAL-PHRASE-TREE-INSTANCE", 1, 0, false);
        declareFunction("nominal_phrase_tree_p", "NOMINAL-PHRASE-TREE-P", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_determiner_method", "NOMINAL-PHRASE-TREE-GET-DETERMINER-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_overt_quantifier_method", "NOMINAL-PHRASE-TREE-GET-OVERT-QUANTIFIER-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_quantifier_method", "NOMINAL-PHRASE-TREE-GET-QUANTIFIER-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_possessor_method", "NOMINAL-PHRASE-TREE-GET-POSSESSOR-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_specifier_method", "NOMINAL-PHRASE-TREE-GET-SPECIFIER-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_possessive_p_method", "NOMINAL-PHRASE-TREE-POSSESSIVE-P-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_temporal_tree_p_method", "NOMINAL-PHRASE-TREE-TEMPORAL-TREE-P-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_definite_description_p_method", "NOMINAL-PHRASE-TREE-DEFINITE-DESCRIPTION-P-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_name_tree_p_method", "NOMINAL-PHRASE-TREE-NAME-TREE-P-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_gender_method", "NOMINAL-PHRASE-TREE-GET-GENDER-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_number_method", "NOMINAL-PHRASE-TREE-GET-NUMBER-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_get_person_method", "NOMINAL-PHRASE-TREE-GET-PERSON-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_singular_p_method", "NOMINAL-PHRASE-TREE-SINGULAR-P-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_plural_p_method", "NOMINAL-PHRASE-TREE-PLURAL-P-METHOD", 1, 0, false);
        declareFunction("nominal_phrase_tree_lexify_int_method", "NOMINAL-PHRASE-TREE-LEXIFY-INT-METHOD", 2, 0, false);
        declareFunction("presupposing_tree_p", "PRESUPPOSING-TREE-P", 1, 0, false);
        declareFunction("introduces_termP", "INTRODUCES-TERM?", 1, 0, false);
        declareFunction("lex_entry_sem_equal", "LEX-ENTRY-SEM-EQUAL", 2, 0, false);
        declareFunction("nominal_phrase_tree_get_head_daughters_method", "NOMINAL-PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_np_tree_class", "SUBLOOP-RESERVED-INITIALIZE-NP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_np_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-NP-TREE-INSTANCE", 1, 0, false);
        declareFunction("np_tree_p", "NP-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_npp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-NPP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_npp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-NPP-TREE-INSTANCE", 1, 0, false);
        declareFunction("npp_tree_p", "NPP-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_nx_tree_class", "SUBLOOP-RESERVED-INITIALIZE-NX-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_nx_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-NX-TREE-INSTANCE", 1, 0, false);
        declareFunction("nx_tree_p", "NX-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_pnp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-PNP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_pnp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-PNP-TREE-INSTANCE", 1, 0, false);
        declareFunction("pnp_tree_p", "PNP-TREE-P", 1, 0, false);
        declareFunction("pnp_tree_get_head_daughters_method", "PNP-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whnp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-WHNP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whnp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-WHNP-TREE-INSTANCE", 1, 0, false);
        declareFunction("whnp_tree_p", "WHNP-TREE-P", 1, 0, false);
        declareFunction("verbal_phrase_tree_cyclify_int_vp_coordination_method", "VERBAL-PHRASE-TREE-CYCLIFY-INT-VP-COORDINATION-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_cyclify_int_vp_method", "VERBAL-PHRASE-TREE-CYCLIFY-INT-VP-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_cyclify_int_method", "VERBAL-PHRASE-TREE-CYCLIFY-INT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_vp_coordinate_phrase_p_method", "VERBAL-PHRASE-TREE-VP-COORDINATE-PHRASE-P-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_verbal_phrase_tree_class", "SUBLOOP-RESERVED-INITIALIZE-VERBAL-PHRASE-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_verbal_phrase_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-VERBAL-PHRASE-TREE-INSTANCE", 1, 0, false);
        declareFunction("verbal_phrase_tree_p", "VERBAL-PHRASE-TREE-P", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_predicate_method", "VERBAL-PHRASE-TREE-GET-PREDICATE-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_number_method", "VERBAL-PHRASE-TREE-GET-NUMBER-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_person_method", "VERBAL-PHRASE-TREE-GET-PERSON-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_tense_method", "VERBAL-PHRASE-TREE-GET-TENSE-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_subject_method", "VERBAL-PHRASE-TREE-GET-SUBJECT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_objects_method", "VERBAL-PHRASE-TREE-GET-OBJECTS-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_direct_object_method", "VERBAL-PHRASE-TREE-GET-DIRECT-OBJECT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_indirect_object_method", "VERBAL-PHRASE-TREE-GET-INDIRECT-OBJECT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_semantic_direct_object_method", "VERBAL-PHRASE-TREE-GET-SEMANTIC-DIRECT-OBJECT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_semantic_indirect_object_method", "VERBAL-PHRASE-TREE-GET-SEMANTIC-INDIRECT-OBJECT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_semantic_subject_method", "VERBAL-PHRASE-TREE-GET-SEMANTIC-SUBJECT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_semantic_objects_method", "VERBAL-PHRASE-TREE-GET-SEMANTIC-OBJECTS-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_verbal_complement_method", "VERBAL-PHRASE-TREE-GET-VERBAL-COMPLEMENT-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_copula_p_method", "VERBAL-PHRASE-TREE-COPULA-P-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_dummy_to_p_method", "VERBAL-PHRASE-TREE-DUMMY-TO-P-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_passive_p_method", "VERBAL-PHRASE-TREE-PASSIVE-P-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_finite_p_method", "VERBAL-PHRASE-TREE-FINITE-P-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_partition_semantic_complements_method", "VERBAL-PHRASE-TREE-PARTITION-SEMANTIC-COMPLEMENTS-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_ancestor_objects_method", "VERBAL-PHRASE-TREE-GET-ANCESTOR-OBJECTS-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_get_head_daughters_method", "VERBAL-PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("verbal_phrase_tree_inverted_p_method", "VERBAL-PHRASE-TREE-INVERTED-P-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sentential_tree_class", "SUBLOOP-RESERVED-INITIALIZE-SENTENTIAL-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sentential_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-SENTENTIAL-TREE-INSTANCE", 1, 0, false);
        declareFunction("sentential_tree_p", "SENTENTIAL-TREE-P", 1, 0, false);
        declareFunction("sentential_tree_get_subject_method", "SENTENTIAL-TREE-GET-SUBJECT-METHOD", 1, 0, false);
        declareFunction("sentential_tree_get_modifieds_method", "SENTENTIAL-TREE-GET-MODIFIEDS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_vp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-VP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_vp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-VP-TREE-INSTANCE", 1, 0, false);
        declareFunction("vp_tree_p", "VP-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_vg_tree_class", "SUBLOOP-RESERVED-INITIALIZE-VG-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_vg_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-VG-TREE-INSTANCE", 1, 0, false);
        declareFunction("vg_tree_p", "VG-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_s1_tree_class", "SUBLOOP-RESERVED-INITIALIZE-S1-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_s1_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-S1-TREE-INSTANCE", 1, 0, false);
        declareFunction("s1_tree_p", "S1-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_s_tree_class", "SUBLOOP-RESERVED-INITIALIZE-S-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_s_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-S-TREE-INSTANCE", 1, 0, false);
        declareFunction("s_tree_p", "S-TREE-P", 1, 0, false);
        declareFunction("s_tree_get_ancestor_objects_method", "S-TREE-GET-ANCESTOR-OBJECTS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sbar_tree_class", "SUBLOOP-RESERVED-INITIALIZE-SBAR-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sbar_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-SBAR-TREE-INSTANCE", 1, 0, false);
        declareFunction("sbar_tree_p", "SBAR-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sbarq_tree_class", "SUBLOOP-RESERVED-INITIALIZE-SBARQ-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sbarq_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-SBARQ-TREE-INSTANCE", 1, 0, false);
        declareFunction("sbarq_tree_p", "SBARQ-TREE-P", 1, 0, false);
        declareFunction("sbarq_tree_get_head_daughters_method", "SBARQ-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("sbarq_tree_get_ancestor_objects_method", "SBARQ-TREE-GET-ANCESTOR-OBJECTS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sq_tree_class", "SUBLOOP-RESERVED-INITIALIZE-SQ-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sq_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-SQ-TREE-INSTANCE", 1, 0, false);
        declareFunction("sq_tree_p", "SQ-TREE-P", 1, 0, false);
        declareFunction("sq_tree_get_ancestor_objects_method", "SQ-TREE-GET-ANCESTOR-OBJECTS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sinv_tree_class", "SUBLOOP-RESERVED-INITIALIZE-SINV-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sinv_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-SINV-TREE-INSTANCE", 1, 0, false);
        declareFunction("sinv_tree_p", "SINV-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_prepositional_or_particle_phrase_tree_class", "SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_prepositional_or_particle_phrase_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-INSTANCE", 1, 0, false);
        declareFunction("prepositional_or_particle_phrase_tree_p", "PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-P", 1, 0, false);
        declareFunction("prepositional_or_particle_phrase_tree_get_head_daughters_method", "PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("prepositional_or_particle_phrase_tree_get_oblique_object_method", "PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-GET-OBLIQUE-OBJECT-METHOD", 1, 0, false);
        declareFunction("prepositional_or_particle_phrase_tree_stranded_p_method", "PREPOSITIONAL-OR-PARTICLE-PHRASE-TREE-STRANDED-P-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_prepositional_phrase_tree_class", "SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-PHRASE-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_prepositional_phrase_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-PREPOSITIONAL-PHRASE-TREE-INSTANCE", 1, 0, false);
        declareFunction("prepositional_phrase_tree_p", "PREPOSITIONAL-PHRASE-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_pp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-PP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_pp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-PP-TREE-INSTANCE", 1, 0, false);
        declareFunction("pp_tree_p", "PP-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whpp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-WHPP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whpp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-WHPP-TREE-INSTANCE", 1, 0, false);
        declareFunction("whpp_tree_p", "WHPP-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_prt_tree_class", "SUBLOOP-RESERVED-INITIALIZE-PRT-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_prt_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-PRT-TREE-INSTANCE", 1, 0, false);
        declareFunction("prt_tree_p", "PRT-TREE-P", 1, 0, false);
        declareFunction("prt_tree_get_head_daughters_method", "PRT-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_adjp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-ADJP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_adjp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-ADJP-TREE-INSTANCE", 1, 0, false);
        declareFunction("adjp_tree_p", "ADJP-TREE-P", 1, 0, false);
        declareFunction("adjp_tree_get_head_daughters_method", "ADJP-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("adjp_tree_get_modifieds_method", "ADJP-TREE-GET-MODIFIEDS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whadjp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-WHADJP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whadjp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-WHADJP-TREE-INSTANCE", 1, 0, false);
        declareFunction("whadjp_tree_p", "WHADJP-TREE-P", 1, 0, false);
        declareFunction("whadjp_tree_lexify_int_method", "WHADJP-TREE-LEXIFY-INT-METHOD", 2, 0, false);
        declareFunction("subloop_reserved_initialize_advp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-ADVP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_advp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-ADVP-TREE-INSTANCE", 1, 0, false);
        declareFunction("advp_tree_p", "ADVP-TREE-P", 1, 0, false);
        declareFunction("advp_tree_get_head_daughters_method", "ADVP-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whadvp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-WHADVP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_whadvp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-WHADVP-TREE-INSTANCE", 1, 0, false);
        declareFunction("whadvp_tree_p", "WHADVP-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_frag_tree_class", "SUBLOOP-RESERVED-INITIALIZE-FRAG-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_frag_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-FRAG-TREE-INSTANCE", 1, 0, false);
        declareFunction("frag_tree_p", "FRAG-TREE-P", 1, 0, false);
        declareFunction("subloop_reserved_initialize_qp_tree_class", "SUBLOOP-RESERVED-INITIALIZE-QP-TREE-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_qp_tree_instance", "SUBLOOP-RESERVED-INITIALIZE-QP-TREE-INSTANCE", 1, 0, false);
        declareFunction("qp_tree_p", "QP-TREE-P", 1, 0, false);
        declareFunction("qp_tree_get_head_daughters_method", "QP-TREE-GET-HEAD-DAUGHTERS-METHOD", 1, 0, false);
        declareFunction("qp_tree_get_quantified_method", "QP-TREE-GET-QUANTIFIED-METHOD", 1, 0, false);
        declareFunction("qp_tree_cyclify_int_method", "QP-TREE-CYCLIFY-INT-METHOD", 1, 0, false);
        return NIL;
    }

    static private final SubLList $list_alt242 = list(makeString("@return listp; the CycL representation of the meaning of this phrase-tree"), list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEADS")))), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), makeSymbol("HEADS")), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("CYCLIFY-INT"))), makeSymbol("CYCLS"))), list(RET, list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), makeSymbol("CYCLS"))))));

    static private final SubLList $list_alt245 = list(new SubLObject[]{ cons(makeKeyword("NP"), makeSymbol("NP-TREE")), cons(makeKeyword("NPP"), makeSymbol("NPP-TREE")), cons(makeKeyword("CNP"), makeSymbol("NOMINAL-PHRASE-TREE")), cons(makeKeyword("NX"), makeSymbol("NX-TREE")), cons(makeKeyword("PNP"), makeSymbol("PNP-TREE")), cons($WHNP, makeSymbol("WHNP-TREE")), cons(makeKeyword("PRT"), makeSymbol("PRT-TREE")), cons(makeKeyword("QP"), makeSymbol("QP-TREE")), cons(makeKeyword("SUB"), makeSymbol("NOMINAL-PHRASE-TREE")), cons(makeKeyword("OBJ"), makeSymbol("NOMINAL-PHRASE-TREE")), cons(makeKeyword("CONJP"), makeSymbol("COORDINATE-PHRASE-TREE")), cons(makeKeyword("UCP"), makeSymbol("UNLIKE-COORDINATE-PHRASE-TREE")), cons(makeKeyword("PRT"), makeSymbol("PARTICLE-PHRASE-TREE")), cons(makeKeyword("VP"), makeSymbol("VP-TREE")), cons(makeKeyword("VG"), makeSymbol("VG-TREE")), cons(makeKeyword("S1"), makeSymbol("S1-TREE")), cons(makeKeyword("S"), makeSymbol("S-TREE")), cons($SBAR, makeSymbol("SBAR-TREE")), cons(makeKeyword("SBARQ"), makeSymbol("SBARQ-TREE")), cons(makeKeyword("SQ"), makeSymbol("SQ-TREE")), cons($SINV, makeSymbol("SINV-TREE")), cons(makeKeyword("PP"), makeSymbol("PP-TREE")), cons($WHPP, makeSymbol("WHPP-TREE")), cons($ADVP, makeSymbol("ADVP-TREE")), cons(makeKeyword("WHADVP"), makeSymbol("WHADVP-TREE")), cons($ADJP, makeSymbol("ADJP-TREE")), cons(makeKeyword("WHADJP"), makeSymbol("WHADJP-TREE")), cons($FRAG, makeSymbol("FRAG-TREE")), cons(makeKeyword("X"), makeSymbol("PHRASE-TREE")) });

    static private final SubLString $str_alt246$__P_ = makeString("#<P(");

    static private final SubLString $str_alt247$__ = makeString("):");

    static private final SubLList $list_alt248 = list(makeString("@return stringp; the string of this phrase-tree"), list(makeSymbol("PUNLESS"), list(makeSymbol("STRINGP"), makeSymbol("STRING")), list(makeSymbol("PIF"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), ZERO_INTEGER), list(makeSymbol("CLET"), list(list(makeSymbol("S"), list(makeSymbol("FIM"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-STRING")))), makeSymbol("DAUGHTER-STRING")), list(makeSymbol("CDOTIMES"), list(makeSymbol("I"), list(makeSymbol("-"), list(makeSymbol("DAUGHTER-COUNT"), makeSymbol("SELF")), ONE_INTEGER)), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER-STRING"), list(makeSymbol("FIM"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), list(makeSymbol("1+"), makeSymbol("I"))), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CSETQ"), makeSymbol("S"), list(makeSymbol("CCONCATENATE"), makeSymbol("S"), list(makeSymbol("FIF"), list(makeSymbol("CONTRACT-STRING?"), makeSymbol("DAUGHTER-STRING")), makeString(""), makeString(" ")), makeSymbol("DAUGHTER-STRING")))), list(makeSymbol("CSETQ"), makeSymbol("STRING"), makeSymbol("S"))), list(makeSymbol("CSETQ"), makeSymbol("STRING"), makeString("")))), list(RET, makeSymbol("STRING")));

    static private final SubLString $str_alt251$_ = makeString(" ");

    static private final SubLList $list_alt253 = list(makeString("@return listp; the list of words of this phrase-tree"), list(makeSymbol("CLET"), list(list(makeSymbol("YIELD"), NIL)), list(makeSymbol("DO-PARSE-TREE-SUBTREES"), list(makeSymbol("DAUGHTER"), makeSymbol("SELF")), list(makeSymbol("PWHEN"), list(makeSymbol("WORD-TREE-P"), makeSymbol("DAUGHTER")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("YIELD")))), list(RET, makeSymbol("YIELD"))));

    static private final SubLList $list_alt257 = list(makeString("@return listp; the parse-expression of this phrase-tree\n   @owner bertolo"), list(makeSymbol("CLET"), list(list(makeSymbol("EXPRESSION"), list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CATEGORY")))))), list(makeSymbol("DO-VECTOR"), list(makeSymbol("DAUGHTER"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"))), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("GET-PARSE-EXPRESSION"))), makeSymbol("EXPRESSION"))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("EXPRESSION")))));

    static private final SubLList $list_alt259 = list(makeString("@return parse-tree-p; the heads of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD-DAUGHTER"), list(QUOTE, makeSymbol("GET-HEADS")))), list(makeSymbol("CPUSH"), makeSymbol("HEAD"), makeSymbol("HEADS")))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("HEADS")))));

    static private final SubLList $list_alt261 = list(makeString("@return parse-tree-p; the semantic heads of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD-DAUGHTERS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD-DAUGHTER"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS")))), list(makeSymbol("CPUSH"), makeSymbol("HEAD"), makeSymbol("HEADS")))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("HEADS")))));

    static private final SubLList $list_alt263 = list(makeString("@param INDEX integerp\n   @return parse-tree-p; the INDEX\'th daughter of this parse tree, or nil if it doesn\'t exist"), list(makeSymbol("CHECK-TYPE"), makeSymbol("INDEX"), makeSymbol("NON-NEGATIVE-INTEGER-P")), list(makeSymbol("PWHEN"), list(makeSymbol(">="), makeSymbol("INDEX"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS"))), list(RET, NIL)), list(RET, list(makeSymbol("AREF"), makeSymbol("DAUGHTERS"), makeSymbol("INDEX"))));

    static private final SubLList $list_alt268 = list(makeSymbol("PATH"));

    static private final SubLList $list_alt269 = list(makeString("@param PATH listp; a list of non-negative integers\n   @return parse-tree-p; the parse tree a location PATH, starting from the root\n   of this phrase-tree, or nil if there is no such parse tree"), list(makeSymbol("CHECK-TYPE"), makeSymbol("PATH"), makeSymbol("LISTP")), list(makeSymbol("CLET"), list(list(makeSymbol("TREE"), makeSymbol("SELF")), makeSymbol("DTRS")), list(makeSymbol("CDOLIST"), list(makeSymbol("I"), makeSymbol("PATH")), list(makeSymbol("CSETQ"), makeSymbol("DTRS"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("TREE"))), list(makeSymbol("PWHEN"), list(makeSymbol(">="), makeSymbol("I"), list(makeSymbol("LENGTH"), makeSymbol("DTRS"))), list(RET, NIL)), list(makeSymbol("CSETQ"), makeSymbol("TREE"), list(makeSymbol("AREF"), makeSymbol("DTRS"), makeSymbol("I")))), list(RET, makeSymbol("TREE"))));

    static private final SubLList $list_alt274 = list(makeSymbol("SEARCH-STRING"), makeSymbol("&OPTIONAL"), list(makeSymbol("N"), ONE_INTEGER));

    static private final SubLList $list_alt275 = list(makeString("@param STRING stringp; the string to search for \n   @param N positive-integer-p; the desired occurrence\n   @return parse-tree-p; the first tree whose string is equal to STRING"), list(makeSymbol("CHECK-TYPE"), makeSymbol("SEARCH-STRING"), makeSymbol("STRINGP")), list(makeSymbol("CHECK-TYPE"), makeSymbol("N"), makeSymbol("POSITIVE-INTEGER-P")), list(makeSymbol("CLET"), list(list(makeSymbol("M"), ZERO_INTEGER)), list(makeSymbol("DO-PARSE-TREE-WORD-TREES"), list(makeSymbol("WORD"), makeSymbol("SELF")), list(makeSymbol("PWHEN"), list(EQUALP, makeSymbol("SEARCH-STRING"), list(makeSymbol("FIM"), makeSymbol("WORD"), list(QUOTE, makeSymbol("GET-STRING")))), list(makeSymbol("CINC"), makeSymbol("M")), list(makeSymbol("PWHEN"), list(makeSymbol("="), makeSymbol("M"), makeSymbol("N")), list(RET, makeSymbol("WORD")))))));

    static private final SubLList $list_alt279 = list(makeString("@return non-negative-integerp; the number of daughters of this parse tree"), list(RET, list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS"))));

    static private final SubLList $list_alt282 = list(makeString("@return booleanp; t if this phrase tree has all words as daughters, nil otherwise"), list(RET, list(makeSymbol("CNOT"), list(makeSymbol("FIND-IF-NOT"), list(QUOTE, makeSymbol("WORD-TREE-P")), makeSymbol("DAUGHTERS")))));

    static private final SubLList $list_alt286 = list(makeString("@return booleanp; t if this phrase tree is multi-headed (a hydra), nil otherwise"), list(RET, list(makeSymbol(">"), list(makeSymbol("LENGTH"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTERS")))), ONE_INTEGER)));

    static private final SubLList $list_alt289 = list(makeSymbol("PREDICATE"), makeSymbol("&OPTIONAL"), list(makeSymbol("START"), ZERO_INTEGER), makeSymbol("END"));

    static private final SubLList $list_alt290 = list(makeString("@param PREDICATE symbolp\n   @return listp; a list of all daughters of this phrase-tree which satisfies\n   PREDICATE, from left to right"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER"), list(makeSymbol("STOP"), list(makeSymbol("FIF"), makeSymbol("END"), makeSymbol("END"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("FOUND"), NIL)), list(makeSymbol("PWHEN"), list(makeSymbol(">"), makeSymbol("START"), makeSymbol("STOP")), list(makeSymbol("WARN"), makeString("start(~A) after stop(~A) when finding ~S in ~S~%"), makeSymbol("START"), makeSymbol("STOP"), makeSymbol("PREDICATE"), makeSymbol("SELF"))), list(makeSymbol("CDO"), list(list(makeSymbol("I"), makeSymbol("START"), list(makeSymbol("1+"), makeSymbol("I")))), list(list(makeSymbol(">="), makeSymbol("I"), makeSymbol("STOP"))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("FOUND")))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("FOUND")))));

    static private final SubLString $str_alt292$start__A__after_stop__A__when_fin = makeString("start(~A) after stop(~A) when finding ~S in ~S~%");

    static private final SubLList $list_alt295 = list(makeSymbol("PREDICATE"), makeSymbol("&OPTIONAL"), list(makeSymbol("START"), ZERO_INTEGER));

    static private final SubLList $list_alt296 = list(makeString("@param PREDICATE symbolp\n   @return parse-tree-p; the first daughter of this phrase passing PREDICATE, starting\n   at daughter START"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER")), list(makeSymbol("CDO"), list(list(makeSymbol("I"), makeSymbol("START"), list(makeSymbol("1+"), makeSymbol("I")))), list(list(makeSymbol(">="), makeSymbol("I"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(RET, makeSymbol("DAUGHTER"), makeSymbol("I")))), list(RET, NIL)));

    static private final SubLList $list_alt300 = list(makeSymbol("PREDICATE"), makeSymbol("&OPTIONAL"), makeSymbol("END"));

    static private final SubLList $list_alt301 = list(makeString("@param PREDICATE symbolp\n   @return parse-tree-p; the last daughter of this phrase passing PREDICATE, ending\n   at daughter END"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER")), list(makeSymbol("CDO"), list(list(makeSymbol("I"), list(makeSymbol("FIF"), makeSymbol("END"), makeSymbol("END"), list(makeSymbol("1-"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("1-"), makeSymbol("I")))), list(list(makeSymbol("MINUSP"), makeSymbol("I"))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(RET, makeSymbol("DAUGHTER")))), list(RET, NIL)));

    static private final SubLList $list_alt305 = list(makeString("@param PREDICATE symbolp\n   @return listp; a list of all daughters of this phrase-tree whose category is\n   CATEGORY, from left to right, recursing down into daughters"), list(makeSymbol("CLET"), list(makeSymbol("DAUGHTER"), list(makeSymbol("STOP"), list(makeSymbol("FIF"), makeSymbol("END"), makeSymbol("END"), list(makeSymbol("LENGTH"), makeSymbol("DAUGHTERS")))), list(makeSymbol("FOUND"), NIL)), list(makeSymbol("CDO"), list(list(makeSymbol("I"), makeSymbol("START"), list(makeSymbol("1+"), makeSymbol("I")))), list(list(makeSymbol("="), makeSymbol("I"), makeSymbol("STOP"))), list(makeSymbol("CSETQ"), makeSymbol("DAUGHTER"), list(makeSymbol("GET-DAUGHTER"), makeSymbol("SELF"), makeSymbol("I"))), list(makeSymbol("PWHEN"), list(makeSymbol("FUNCALL"), makeSymbol("PREDICATE"), makeSymbol("DAUGHTER")), list(makeSymbol("CPUSH"), makeSymbol("DAUGHTER"), makeSymbol("FOUND"))), list(makeSymbol("PWHEN"), list(makeSymbol("PHRASE-TREE-P"), makeSymbol("DAUGHTER")), list(makeSymbol("CDOLIST"), list(makeSymbol("THE-DESCENDANT"), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("FIND-ALL")), makeSymbol("PREDICATE"))), list(makeSymbol("CPUSH"), makeSymbol("THE-DESCENDANT"), makeSymbol("FOUND"))))), list(RET, list(makeSymbol("NREVERSE"), makeSymbol("FOUND")))));

    static private final SubLList $list_alt309 = list(makeSymbol("CATEGORY-LIST"), makeSymbol("&OPTIONAL"), list(makeSymbol("START"), ZERO_INTEGER), makeSymbol("END"));

    static private final SubLList $list_alt310 = list(makeString("@param pos-list listp; a list of keyword syntactic categories\n   @return listp; a list of all subtrees of self that have any of the categories in CATEGORY-LIST"), list(makeSymbol("IGNORE"), makeSymbol("START"), makeSymbol("END")), list(makeSymbol("CLET"), list(list(makeSymbol("RESULT"))), list(makeSymbol("DO-PARSE-TREE-SUBTREES"), list(makeSymbol("SUBTREE"), makeSymbol("SELF")), list(makeSymbol("CDOLIST"), list(makeSymbol("CAT"), makeSymbol("CATEGORY-LIST")), list(makeSymbol("PWHEN"), list(EQ, list(makeSymbol("GET-PARSE-TREE-CATEGORY"), makeSymbol("SUBTREE")), makeSymbol("CAT")), list(makeSymbol("CPUSH"), makeSymbol("SUBTREE"), makeSymbol("RESULT"))))), list(RET, makeSymbol("RESULT"))));

    static private final SubLList $list_alt315 = list(makeString("'s"), makeString(","), makeString("."), makeString("!"), makeString("?"), makeString(";"));

    static private final SubLList $list_alt319 = list(makeString("who"), makeString("where"), makeString("when"), makeString("how"));

    static private final SubLList $list_alt320 = list(makeString("which"), makeString("what"));

    static private final SubLList $list_alt323 = list(makeSymbol("NOMINAL-TREE"), makeSymbol("CYCLIFIABLE-NOUN-PHRASE"));

    static private final SubLList $list_alt324 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DETERMINER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OVERT-QUANTIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-POSSESSOR"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SPECIFIER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POSSESSIVE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("TEMPORAL-TREE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NAME-TREE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NUMBER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PERSON"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-GENDER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SINGULAR-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PLURAL-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DEFINITE-DESCRIPTION-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-EXISTENTIAL-NULL-DETERMINER"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), list(makeSymbol("LEXICON")), makeKeyword("PROTECTED")) });

    static private final SubLList $list_alt328 = list(makeString("@return word-tree-p; the determiner of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-DETERMINER")))))));

    static private final SubLList $list_alt331 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-OVERT-QUANTIFIER")))))));

    static private final SubLList $list_alt334 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-QUANTIFIER")))))));

    static private final SubLList $list_alt337 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-POSSESSOR")))))));

    public static SubLObject init_parse_tree_file() {
        defparameter("*PHRASE-TREE-ALLOW-ADJACENT-DAUGHTERS-TO-MERGE*", NIL);
        defparameter("*CATEGORY-TO-PHRASE*", $list245);
        return NIL;
    }

    public static SubLObject setup_parse_tree_file() {
        interfaces.new_interface(NOMINAL_TREE, NIL, NIL, $list1);
        interfaces.new_interface(VERBAL_TREE, NIL, NIL, $list3);
        interfaces.new_interface(ADVERBIAL_TREE, NIL, NIL, NIL);
        interfaces.new_interface(PREPOSITIONAL_TREE, NIL, NIL, NIL);
        interfaces.new_interface(PARTICLE_TREE, NIL, NIL, NIL);
        interfaces.new_interface(ADJECTIVAL_TREE, NIL, NIL, NIL);
        interfaces.new_interface(QUESTION_TREE, NIL, NIL, NIL);
        interfaces.new_interface(WH_TREE, NIL, NIL, NIL);
        interfaces.new_interface(NUMERICAL_TREE, NIL, NIL, NIL);
        interfaces.new_interface(FINITE_VERBAL_WORD_TREE, NIL, NIL, NIL);
        interfaces.new_interface(QUANTIFIER_TREE, NIL, NIL, $list13);
        interfaces.new_interface(PRONOUN_TREE, NIL, NIL, NIL);
        classes.subloop_new_class(PARSE_TREE, OBJECT, $list17, T, $list18);
        classes.class_set_implements_slot_listeners(PARSE_TREE, NIL);
        methods.methods_incorporate_instance_method(GET_CYCLIFIER, PARSE_TREE, $list26, $list27, $list28);
        methods.subloop_register_instance_method(PARSE_TREE, GET_CYCLIFIER, PARSE_TREE_GET_CYCLIFIER_METHOD);
        methods.methods_incorporate_instance_method(CYCLIFY, PARSE_TREE, $list26, $list27, $list58);
        methods.subloop_register_instance_method(PARSE_TREE, CYCLIFY, PARSE_TREE_CYCLIFY_METHOD);
        classes.subloop_note_class_initialization_function(PARSE_TREE, SUBLOOP_RESERVED_INITIALIZE_PARSE_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(PARSE_TREE, SUBLOOP_RESERVED_INITIALIZE_PARSE_TREE_INSTANCE);
        subloop_reserved_initialize_parse_tree_class(PARSE_TREE);
        methods.methods_incorporate_instance_method(DEEP_COPY, PARSE_TREE, $list26, NIL, $list68);
        methods.subloop_register_instance_method(PARSE_TREE, DEEP_COPY, PARSE_TREE_DEEP_COPY_METHOD);
        methods.methods_incorporate_instance_method(GET_ROOT, PARSE_TREE, $list26, NIL, $list72);
        methods.subloop_register_instance_method(PARSE_TREE, GET_ROOT, PARSE_TREE_GET_ROOT_METHOD);
        methods.methods_incorporate_instance_method(GET_CATEGORY, PARSE_TREE, $list26, NIL, $list77);
        methods.subloop_register_instance_method(PARSE_TREE, GET_CATEGORY, PARSE_TREE_GET_CATEGORY_METHOD);
        methods.methods_incorporate_instance_method(GET_INDEX, PARSE_TREE, $list26, NIL, $list81);
        methods.subloop_register_instance_method(PARSE_TREE, GET_INDEX, PARSE_TREE_GET_INDEX_METHOD);
        methods.methods_incorporate_instance_method(GET_PATH, PARSE_TREE, $list26, NIL, $list85);
        methods.subloop_register_instance_method(PARSE_TREE, GET_PATH, PARSE_TREE_GET_PATH_METHOD);
        methods.methods_incorporate_instance_method(GET_MOTHER, PARSE_TREE, $list26, NIL, $list89);
        methods.subloop_register_instance_method(PARSE_TREE, GET_MOTHER, PARSE_TREE_GET_MOTHER_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_HEAD, PARSE_TREE, $list26, NIL, $list93);
        methods.subloop_register_instance_method(PARSE_TREE, GET_SEMANTIC_HEAD, PARSE_TREE_GET_SEMANTIC_HEAD_METHOD);
        methods.methods_incorporate_instance_method(GET_COMPLEMENTS, PARSE_TREE, $list26, NIL, $list97);
        methods.subloop_register_instance_method(PARSE_TREE, GET_COMPLEMENTS, PARSE_TREE_GET_COMPLEMENTS_METHOD);
        methods.methods_incorporate_instance_method(GET_MODIFIERS, PARSE_TREE, $list26, NIL, $list101);
        methods.subloop_register_instance_method(PARSE_TREE, GET_MODIFIERS, PARSE_TREE_GET_MODIFIERS_METHOD);
        methods.methods_incorporate_instance_method(GET_MODIFIEDS, PARSE_TREE, $list26, NIL, $list104);
        methods.subloop_register_instance_method(PARSE_TREE, GET_MODIFIEDS, PARSE_TREE_GET_MODIFIEDS_METHOD);
        methods.methods_incorporate_instance_method(GET_CONTEXT, PARSE_TREE, $list26, NIL, $list107);
        methods.subloop_register_instance_method(PARSE_TREE, GET_CONTEXT, PARSE_TREE_GET_CONTEXT_METHOD);
        methods.methods_incorporate_instance_method(TEMPORAL_TREE_P, PARSE_TREE, $list26, NIL, $list111);
        methods.subloop_register_instance_method(PARSE_TREE, TEMPORAL_TREE_P, PARSE_TREE_TEMPORAL_TREE_P_METHOD);
        methods.methods_incorporate_instance_method(CYCLIFIABLE_QUESTION_P, PARSE_TREE, $list26, NIL, $list114);
        methods.subloop_register_instance_method(PARSE_TREE, CYCLIFIABLE_QUESTION_P, PARSE_TREE_CYCLIFIABLE_QUESTION_P_METHOD);
        methods.methods_incorporate_instance_method(ROOT_P, PARSE_TREE, $list26, NIL, $list116);
        methods.subloop_register_instance_method(PARSE_TREE, ROOT_P, PARSE_TREE_ROOT_P_METHOD);
        methods.methods_incorporate_instance_method(LEXIFY, PARSE_TREE, $list26, $list119, $list120);
        methods.subloop_register_instance_method(PARSE_TREE, LEXIFY, PARSE_TREE_LEXIFY_METHOD);
        methods.methods_incorporate_instance_method(LEXIFY_INT, PARSE_TREE, $list123, $list119, $list124);
        methods.subloop_register_instance_method(PARSE_TREE, LEXIFY_INT, PARSE_TREE_LEXIFY_INT_METHOD);
        methods.methods_incorporate_instance_method(CYCLIFY_INT, PARSE_TREE, $list123, NIL, $list127);
        methods.subloop_register_instance_method(PARSE_TREE, CYCLIFY_INT, PARSE_TREE_CYCLIFY_INT_METHOD);
        methods.methods_incorporate_instance_method(GET_ANCESTORS, PARSE_TREE, $list123, NIL, $list129);
        methods.subloop_register_instance_method(PARSE_TREE, GET_ANCESTORS, PARSE_TREE_GET_ANCESTORS_METHOD);
        methods.methods_incorporate_instance_method(GET_SISTER, PARSE_TREE, $list26, $list133, $list134);
        methods.subloop_register_instance_method(PARSE_TREE, GET_SISTER, PARSE_TREE_GET_SISTER_METHOD);
        methods.methods_incorporate_instance_method(PRINT, PARSE_TREE, $list123, $list140, $list141);
        methods.subloop_register_instance_method(PARSE_TREE, PRINT, PARSE_TREE_PRINT_METHOD);
        classes.subloop_new_class(PHRASE_TREE, PARSE_TREE, NIL, NIL, $list185);
        classes.class_set_implements_slot_listeners(PHRASE_TREE, NIL);
        classes.subloop_note_class_initialization_function(PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_PHRASE_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_PHRASE_TREE_INSTANCE);
        subloop_reserved_initialize_phrase_tree_class(PHRASE_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, PHRASE_TREE, $list26, NIL, $list190);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_HEAD_DAUGHTERS, PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTER, PHRASE_TREE, $list26, NIL, $list193);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_HEAD_DAUGHTER, PHRASE_TREE_GET_HEAD_DAUGHTER_METHOD);
        methods.methods_incorporate_instance_method(GET_HEAD, PHRASE_TREE, $list26, NIL, $list195);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_HEAD, PHRASE_TREE_GET_HEAD_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_HEAD_DAUGHTERS, PHRASE_TREE, $list26, NIL, $list199);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_SEMANTIC_HEAD_DAUGHTERS, PHRASE_TREE_GET_SEMANTIC_HEAD_DAUGHTERS_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_HEAD_DAUGHTER, PHRASE_TREE, $list26, NIL, $list202);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_SEMANTIC_HEAD_DAUGHTER, PHRASE_TREE_GET_SEMANTIC_HEAD_DAUGHTER_METHOD);
        methods.methods_incorporate_instance_method(GET_REFS, PHRASE_TREE, $list205, NIL, $list206);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_REFS, PHRASE_TREE_GET_REFS_METHOD);
        methods.methods_incorporate_instance_method(GET_MODIFIERS, PHRASE_TREE, $list26, NIL, $list210);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_MODIFIERS, PHRASE_TREE_GET_MODIFIERS_METHOD);
        methods.methods_incorporate_instance_method(GET_CONJUNCTION, PHRASE_TREE, $list26, NIL, $list213);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_CONJUNCTION, PHRASE_TREE_GET_CONJUNCTION_METHOD);
        methods.methods_incorporate_instance_method(LEXIFY_INT, PHRASE_TREE, $list216, $list119, $list217);
        methods.subloop_register_instance_method(PHRASE_TREE, LEXIFY_INT, PHRASE_TREE_LEXIFY_INT_METHOD);
        methods.methods_incorporate_instance_method(RETOKENIZE, PHRASE_TREE, $list216, $list119, $list220);
        methods.subloop_register_instance_method(PHRASE_TREE, RETOKENIZE, PHRASE_TREE_RETOKENIZE_METHOD);
        methods.methods_incorporate_instance_method(RETOKENIZE_INT, PHRASE_TREE, $list123, $list228, $list229);
        methods.subloop_register_instance_method(PHRASE_TREE, RETOKENIZE_INT, PHRASE_TREE_RETOKENIZE_INT_METHOD);
        methods.methods_incorporate_instance_method(PARTIAL_RETOKENIZE_INT, PHRASE_TREE, $list216, $list233, $list234);
        methods.subloop_register_instance_method(PHRASE_TREE, PARTIAL_RETOKENIZE_INT, PHRASE_TREE_PARTIAL_RETOKENIZE_INT_METHOD);
        methods.methods_incorporate_instance_method(ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY, PHRASE_TREE, $list123, $list119, $list236);
        methods.subloop_register_instance_method(PHRASE_TREE, ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY, PHRASE_TREE_ADJACENT_DAUGHTERS_HAVE_LEXICAL_ENTRY_METHOD);
        methods.methods_incorporate_instance_method(CYCLIFY_INT, PHRASE_TREE, $list123, NIL, $list242);
        methods.subloop_register_instance_method(PHRASE_TREE, CYCLIFY_INT, PHRASE_TREE_CYCLIFY_INT_METHOD);
        methods.methods_incorporate_instance_method(GET_STRING, PHRASE_TREE, $list26, NIL, $list248);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_STRING, PHRASE_TREE_GET_STRING_METHOD);
        methods.methods_incorporate_instance_method(YIELD, PHRASE_TREE, $list26, NIL, $list253);
        methods.subloop_register_instance_method(PHRASE_TREE, YIELD, PHRASE_TREE_YIELD_METHOD);
        methods.methods_incorporate_instance_method(GET_PARSE_EXPRESSION, PHRASE_TREE, $list26, NIL, $list257);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_PARSE_EXPRESSION, PHRASE_TREE_GET_PARSE_EXPRESSION_METHOD);
        methods.methods_incorporate_instance_method(GET_HEADS, PHRASE_TREE, $list26, NIL, $list259);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_HEADS, PHRASE_TREE_GET_HEADS_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_HEADS, PHRASE_TREE, $list26, NIL, $list261);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_SEMANTIC_HEADS, PHRASE_TREE_GET_SEMANTIC_HEADS_METHOD);
        methods.methods_incorporate_instance_method(GET_DAUGHTER, PHRASE_TREE, $list26, $list133, $list263);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_DAUGHTER, PHRASE_TREE_GET_DAUGHTER_METHOD);
        methods.methods_incorporate_instance_method(GET_DESCENDANT, PHRASE_TREE, $list26, $list268, $list269);
        methods.subloop_register_instance_method(PHRASE_TREE, GET_DESCENDANT, PHRASE_TREE_GET_DESCENDANT_METHOD);
        methods.methods_incorporate_instance_method(SEARCH, PHRASE_TREE, $list26, $list274, $list275);
        methods.subloop_register_instance_method(PHRASE_TREE, SEARCH, PHRASE_TREE_SEARCH_METHOD);
        methods.methods_incorporate_instance_method(DAUGHTER_COUNT, PHRASE_TREE, $list26, NIL, $list279);
        methods.subloop_register_instance_method(PHRASE_TREE, DAUGHTER_COUNT, PHRASE_TREE_DAUGHTER_COUNT_METHOD);
        methods.methods_incorporate_instance_method(PRETERMINAL_P, PHRASE_TREE, $list26, NIL, $list282);
        methods.subloop_register_instance_method(PHRASE_TREE, PRETERMINAL_P, PHRASE_TREE_PRETERMINAL_P_METHOD);
        methods.methods_incorporate_instance_method(HYDRA_P, PHRASE_TREE, $list26, NIL, $list286);
        methods.subloop_register_instance_method(PHRASE_TREE, HYDRA_P, PHRASE_TREE_HYDRA_P_METHOD);
        methods.methods_incorporate_instance_method(FIND, PHRASE_TREE, $list123, $list289, $list290);
        methods.subloop_register_instance_method(PHRASE_TREE, FIND, PHRASE_TREE_FIND_METHOD);
        methods.methods_incorporate_instance_method(FIND_FIRST, PHRASE_TREE, $list123, $list295, $list296);
        methods.subloop_register_instance_method(PHRASE_TREE, FIND_FIRST, PHRASE_TREE_FIND_FIRST_METHOD);
        methods.methods_incorporate_instance_method(FIND_LAST, PHRASE_TREE, $list123, $list300, $list301);
        methods.subloop_register_instance_method(PHRASE_TREE, FIND_LAST, PHRASE_TREE_FIND_LAST_METHOD);
        methods.methods_incorporate_instance_method(FIND_ALL, PHRASE_TREE, $list123, $list289, $list305);
        methods.subloop_register_instance_method(PHRASE_TREE, FIND_ALL, PHRASE_TREE_FIND_ALL_METHOD);
        methods.methods_incorporate_instance_method(FIND_ALL_SUBTREES_OF_CATEGORIES, PHRASE_TREE, $list123, $list309, $list310);
        methods.subloop_register_instance_method(PHRASE_TREE, FIND_ALL_SUBTREES_OF_CATEGORIES, PHRASE_TREE_FIND_ALL_SUBTREES_OF_CATEGORIES_METHOD);
        classes.subloop_new_class(NOMINAL_PHRASE_TREE, PHRASE_TREE, $list323, NIL, $list324);
        classes.class_set_implements_slot_listeners(NOMINAL_PHRASE_TREE, NIL);
        classes.subloop_note_class_initialization_function(NOMINAL_PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_NOMINAL_PHRASE_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(NOMINAL_PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_NOMINAL_PHRASE_TREE_INSTANCE);
        subloop_reserved_initialize_nominal_phrase_tree_class(NOMINAL_PHRASE_TREE);
        methods.methods_incorporate_instance_method(GET_DETERMINER, NOMINAL_PHRASE_TREE, $list26, NIL, $list328);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_DETERMINER, NOMINAL_PHRASE_TREE_GET_DETERMINER_METHOD);
        methods.methods_incorporate_instance_method(GET_OVERT_QUANTIFIER, NOMINAL_PHRASE_TREE, $list26, NIL, $list331);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_OVERT_QUANTIFIER, NOMINAL_PHRASE_TREE_GET_OVERT_QUANTIFIER_METHOD);
        methods.methods_incorporate_instance_method(GET_QUANTIFIER, NOMINAL_PHRASE_TREE, $list26, NIL, $list334);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_QUANTIFIER, NOMINAL_PHRASE_TREE_GET_QUANTIFIER_METHOD);
        methods.methods_incorporate_instance_method(GET_POSSESSOR, NOMINAL_PHRASE_TREE, $list26, NIL, $list337);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_POSSESSOR, NOMINAL_PHRASE_TREE_GET_POSSESSOR_METHOD);
        methods.methods_incorporate_instance_method(GET_SPECIFIER, NOMINAL_PHRASE_TREE, $list26, NIL, $list340);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_SPECIFIER, NOMINAL_PHRASE_TREE_GET_SPECIFIER_METHOD);
        methods.methods_incorporate_instance_method(POSSESSIVE_P, NOMINAL_PHRASE_TREE, $list26, NIL, $list343);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, POSSESSIVE_P, NOMINAL_PHRASE_TREE_POSSESSIVE_P_METHOD);
        methods.methods_incorporate_instance_method(TEMPORAL_TREE_P, NOMINAL_PHRASE_TREE, $list26, NIL, $list345);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, TEMPORAL_TREE_P, NOMINAL_PHRASE_TREE_TEMPORAL_TREE_P_METHOD);
        methods.methods_incorporate_instance_method(DEFINITE_DESCRIPTION_P, NOMINAL_PHRASE_TREE, $list26, NIL, $list348);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, DEFINITE_DESCRIPTION_P, NOMINAL_PHRASE_TREE_DEFINITE_DESCRIPTION_P_METHOD);
        methods.methods_incorporate_instance_method(NAME_TREE_P, NOMINAL_PHRASE_TREE, $list26, NIL, $list351);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, NAME_TREE_P, NOMINAL_PHRASE_TREE_NAME_TREE_P_METHOD);
        methods.methods_incorporate_instance_method(GET_GENDER, NOMINAL_PHRASE_TREE, $list26, NIL, $list354);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_GENDER, NOMINAL_PHRASE_TREE_GET_GENDER_METHOD);
        methods.methods_incorporate_instance_method(GET_NUMBER, NOMINAL_PHRASE_TREE, $list26, NIL, $list357);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_NUMBER, NOMINAL_PHRASE_TREE_GET_NUMBER_METHOD);
        methods.methods_incorporate_instance_method(GET_PERSON, NOMINAL_PHRASE_TREE, $list26, NIL, $list360);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_PERSON, NOMINAL_PHRASE_TREE_GET_PERSON_METHOD);
        methods.methods_incorporate_instance_method(SINGULAR_P, NOMINAL_PHRASE_TREE, $list26, NIL, $list363);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, SINGULAR_P, NOMINAL_PHRASE_TREE_SINGULAR_P_METHOD);
        methods.methods_incorporate_instance_method(PLURAL_P, NOMINAL_PHRASE_TREE, $list26, NIL, $list366);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, PLURAL_P, NOMINAL_PHRASE_TREE_PLURAL_P_METHOD);
        methods.methods_incorporate_instance_method(LEXIFY_INT, NOMINAL_PHRASE_TREE, $list216, $list119, $list368);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, LEXIFY_INT, NOMINAL_PHRASE_TREE_LEXIFY_INT_METHOD);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, NOMINAL_PHRASE_TREE, $list26, NIL, $list378);
        methods.subloop_register_instance_method(NOMINAL_PHRASE_TREE, GET_HEAD_DAUGHTERS, NOMINAL_PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD);
        classes.subloop_new_class(NP_TREE, NOMINAL_PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(NP_TREE, NIL);
        classes.subloop_note_class_initialization_function(NP_TREE, SUBLOOP_RESERVED_INITIALIZE_NP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(NP_TREE, SUBLOOP_RESERVED_INITIALIZE_NP_TREE_INSTANCE);
        subloop_reserved_initialize_np_tree_class(NP_TREE);
        classes.subloop_new_class(NPP_TREE, NOMINAL_PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(NPP_TREE, NIL);
        classes.subloop_note_class_initialization_function(NPP_TREE, SUBLOOP_RESERVED_INITIALIZE_NPP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(NPP_TREE, SUBLOOP_RESERVED_INITIALIZE_NPP_TREE_INSTANCE);
        subloop_reserved_initialize_npp_tree_class(NPP_TREE);
        classes.subloop_new_class(NX_TREE, NOMINAL_PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(NX_TREE, NIL);
        classes.subloop_note_class_initialization_function(NX_TREE, SUBLOOP_RESERVED_INITIALIZE_NX_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(NX_TREE, SUBLOOP_RESERVED_INITIALIZE_NX_TREE_INSTANCE);
        subloop_reserved_initialize_nx_tree_class(NX_TREE);
        classes.subloop_new_class(PNP_TREE, NOMINAL_PHRASE_TREE, NIL, NIL, $list395);
        classes.class_set_implements_slot_listeners(PNP_TREE, NIL);
        classes.subloop_note_class_initialization_function(PNP_TREE, SUBLOOP_RESERVED_INITIALIZE_PNP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(PNP_TREE, SUBLOOP_RESERVED_INITIALIZE_PNP_TREE_INSTANCE);
        subloop_reserved_initialize_pnp_tree_class(PNP_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, PNP_TREE, $list26, NIL, $list398);
        methods.subloop_register_instance_method(PNP_TREE, GET_HEAD_DAUGHTERS, PNP_TREE_GET_HEAD_DAUGHTERS_METHOD);
        classes.subloop_new_class(WHNP_TREE, NOMINAL_PHRASE_TREE, $list401, NIL, NIL);
        classes.class_set_implements_slot_listeners(WHNP_TREE, NIL);
        classes.subloop_note_class_initialization_function(WHNP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHNP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(WHNP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHNP_TREE_INSTANCE);
        subloop_reserved_initialize_whnp_tree_class(WHNP_TREE);
        classes.subloop_new_class(VERBAL_PHRASE_TREE, PHRASE_TREE, $list405, NIL, $list406);
        classes.class_set_implements_slot_listeners(VERBAL_PHRASE_TREE, NIL);
        methods.methods_incorporate_instance_method(CYCLIFY_INT_VP_COORDINATION, VERBAL_PHRASE_TREE, $list123, NIL, $list408);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, CYCLIFY_INT_VP_COORDINATION, VERBAL_PHRASE_TREE_CYCLIFY_INT_VP_COORDINATION_METHOD);
        methods.methods_incorporate_instance_method(CYCLIFY_INT_VP, VERBAL_PHRASE_TREE, $list123, NIL, $list412);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, CYCLIFY_INT_VP, VERBAL_PHRASE_TREE_CYCLIFY_INT_VP_METHOD);
        methods.methods_incorporate_instance_method(CYCLIFY_INT, VERBAL_PHRASE_TREE, $list123, NIL, $list416);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, CYCLIFY_INT, VERBAL_PHRASE_TREE_CYCLIFY_INT_METHOD);
        methods.methods_incorporate_instance_method(VP_COORDINATE_PHRASE_P, VERBAL_PHRASE_TREE, NIL, NIL, $list419);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, VP_COORDINATE_PHRASE_P, VERBAL_PHRASE_TREE_VP_COORDINATE_PHRASE_P_METHOD);
        classes.subloop_note_class_initialization_function(VERBAL_PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_VERBAL_PHRASE_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(VERBAL_PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_VERBAL_PHRASE_TREE_INSTANCE);
        subloop_reserved_initialize_verbal_phrase_tree_class(VERBAL_PHRASE_TREE);
        methods.methods_incorporate_instance_method(GET_PREDICATE, VERBAL_PHRASE_TREE, $list26, NIL, $list425);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_PREDICATE, VERBAL_PHRASE_TREE_GET_PREDICATE_METHOD);
        methods.methods_incorporate_instance_method(GET_NUMBER, VERBAL_PHRASE_TREE, $list26, NIL, $list427);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_NUMBER, VERBAL_PHRASE_TREE_GET_NUMBER_METHOD);
        methods.methods_incorporate_instance_method(GET_PERSON, VERBAL_PHRASE_TREE, $list26, NIL, $list429);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_PERSON, VERBAL_PHRASE_TREE_GET_PERSON_METHOD);
        methods.methods_incorporate_instance_method(GET_TENSE, VERBAL_PHRASE_TREE, $list26, NIL, $list432);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_TENSE, VERBAL_PHRASE_TREE_GET_TENSE_METHOD);
        methods.methods_incorporate_instance_method(GET_SUBJECT, VERBAL_PHRASE_TREE, $list26, NIL, $list434);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_SUBJECT, VERBAL_PHRASE_TREE_GET_SUBJECT_METHOD);
        methods.methods_incorporate_instance_method(GET_OBJECTS, VERBAL_PHRASE_TREE, $list26, NIL, $list438);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_OBJECTS, VERBAL_PHRASE_TREE_GET_OBJECTS_METHOD);
        methods.methods_incorporate_instance_method(GET_DIRECT_OBJECT, VERBAL_PHRASE_TREE, $list26, NIL, $list441);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_DIRECT_OBJECT, VERBAL_PHRASE_TREE_GET_DIRECT_OBJECT_METHOD);
        methods.methods_incorporate_instance_method(GET_INDIRECT_OBJECT, VERBAL_PHRASE_TREE, $list26, NIL, $list444);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_INDIRECT_OBJECT, VERBAL_PHRASE_TREE_GET_INDIRECT_OBJECT_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_DIRECT_OBJECT, VERBAL_PHRASE_TREE, $list26, NIL, $list447);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_SEMANTIC_DIRECT_OBJECT, VERBAL_PHRASE_TREE_GET_SEMANTIC_DIRECT_OBJECT_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_INDIRECT_OBJECT, VERBAL_PHRASE_TREE, $list26, NIL, $list450);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_SEMANTIC_INDIRECT_OBJECT, VERBAL_PHRASE_TREE_GET_SEMANTIC_INDIRECT_OBJECT_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_SUBJECT, VERBAL_PHRASE_TREE, $list26, NIL, $list453);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_SEMANTIC_SUBJECT, VERBAL_PHRASE_TREE_GET_SEMANTIC_SUBJECT_METHOD);
        methods.methods_incorporate_instance_method(GET_SEMANTIC_OBJECTS, VERBAL_PHRASE_TREE, $list26, NIL, $list456);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_SEMANTIC_OBJECTS, VERBAL_PHRASE_TREE_GET_SEMANTIC_OBJECTS_METHOD);
        methods.methods_incorporate_instance_method(GET_VERBAL_COMPLEMENT, VERBAL_PHRASE_TREE, $list26, NIL, $list458);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_VERBAL_COMPLEMENT, VERBAL_PHRASE_TREE_GET_VERBAL_COMPLEMENT_METHOD);
        methods.methods_incorporate_instance_method(COPULA_P, VERBAL_PHRASE_TREE, $list26, NIL, $list461);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, COPULA_P, VERBAL_PHRASE_TREE_COPULA_P_METHOD);
        methods.methods_incorporate_instance_method(DUMMY_TO_P, VERBAL_PHRASE_TREE, $list26, NIL, $list464);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, DUMMY_TO_P, VERBAL_PHRASE_TREE_DUMMY_TO_P_METHOD);
        methods.methods_incorporate_instance_method(PASSIVE_P, VERBAL_PHRASE_TREE, $list26, NIL, $list468);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, PASSIVE_P, VERBAL_PHRASE_TREE_PASSIVE_P_METHOD);
        methods.methods_incorporate_instance_method(FINITE_P, VERBAL_PHRASE_TREE, $list26, NIL, $list471);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, FINITE_P, VERBAL_PHRASE_TREE_FINITE_P_METHOD);
        methods.methods_incorporate_instance_method(PARTITION_SEMANTIC_COMPLEMENTS, VERBAL_PHRASE_TREE, $list123, NIL, $list474);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, PARTITION_SEMANTIC_COMPLEMENTS, VERBAL_PHRASE_TREE_PARTITION_SEMANTIC_COMPLEMENTS_METHOD);
        methods.methods_incorporate_instance_method(GET_ANCESTOR_OBJECTS, VERBAL_PHRASE_TREE, $list123, NIL, $list477);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_ANCESTOR_OBJECTS, VERBAL_PHRASE_TREE_GET_ANCESTOR_OBJECTS_METHOD);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, VERBAL_PHRASE_TREE, $list26, NIL, $list481);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, GET_HEAD_DAUGHTERS, VERBAL_PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD);
        methods.methods_incorporate_instance_method(INVERTED_P, VERBAL_PHRASE_TREE, $list26, NIL, $list485);
        methods.subloop_register_instance_method(VERBAL_PHRASE_TREE, INVERTED_P, VERBAL_PHRASE_TREE_INVERTED_P_METHOD);
        classes.subloop_new_class(SENTENTIAL_TREE, VERBAL_PHRASE_TREE, $list488, NIL, $list489);
        classes.class_set_implements_slot_listeners(SENTENTIAL_TREE, NIL);
        classes.subloop_note_class_initialization_function(SENTENTIAL_TREE, SUBLOOP_RESERVED_INITIALIZE_SENTENTIAL_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(SENTENTIAL_TREE, SUBLOOP_RESERVED_INITIALIZE_SENTENTIAL_TREE_INSTANCE);
        subloop_reserved_initialize_sentential_tree_class(SENTENTIAL_TREE);
        methods.methods_incorporate_instance_method(GET_SUBJECT, SENTENTIAL_TREE, $list26, NIL, $list492);
        methods.subloop_register_instance_method(SENTENTIAL_TREE, GET_SUBJECT, SENTENTIAL_TREE_GET_SUBJECT_METHOD);
        methods.methods_incorporate_instance_method(GET_MODIFIEDS, SENTENTIAL_TREE, $list26, NIL, $list494);
        methods.subloop_register_instance_method(SENTENTIAL_TREE, GET_MODIFIEDS, SENTENTIAL_TREE_GET_MODIFIEDS_METHOD);
        classes.subloop_new_class(VP_TREE, VERBAL_PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(VP_TREE, NIL);
        classes.subloop_note_class_initialization_function(VP_TREE, SUBLOOP_RESERVED_INITIALIZE_VP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(VP_TREE, SUBLOOP_RESERVED_INITIALIZE_VP_TREE_INSTANCE);
        subloop_reserved_initialize_vp_tree_class(VP_TREE);
        classes.subloop_new_class(VG_TREE, VERBAL_PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(VG_TREE, NIL);
        classes.subloop_note_class_initialization_function(VG_TREE, SUBLOOP_RESERVED_INITIALIZE_VG_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(VG_TREE, SUBLOOP_RESERVED_INITIALIZE_VG_TREE_INSTANCE);
        subloop_reserved_initialize_vg_tree_class(VG_TREE);
        classes.subloop_new_class(S1_TREE, PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(S1_TREE, NIL);
        classes.subloop_note_class_initialization_function(S1_TREE, SUBLOOP_RESERVED_INITIALIZE_S1_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(S1_TREE, SUBLOOP_RESERVED_INITIALIZE_S1_TREE_INSTANCE);
        subloop_reserved_initialize_s1_tree_class(S1_TREE);
        classes.subloop_new_class(S_TREE, SENTENTIAL_TREE, NIL, NIL, $list507);
        classes.class_set_implements_slot_listeners(S_TREE, NIL);
        classes.subloop_note_class_initialization_function(S_TREE, SUBLOOP_RESERVED_INITIALIZE_S_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(S_TREE, SUBLOOP_RESERVED_INITIALIZE_S_TREE_INSTANCE);
        subloop_reserved_initialize_s_tree_class(S_TREE);
        methods.methods_incorporate_instance_method(GET_ANCESTOR_OBJECTS, S_TREE, $list26, NIL, $list510);
        methods.subloop_register_instance_method(S_TREE, GET_ANCESTOR_OBJECTS, S_TREE_GET_ANCESTOR_OBJECTS_METHOD);
        classes.subloop_new_class(SBAR_TREE, SENTENTIAL_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(SBAR_TREE, NIL);
        classes.subloop_note_class_initialization_function(SBAR_TREE, SUBLOOP_RESERVED_INITIALIZE_SBAR_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(SBAR_TREE, SUBLOOP_RESERVED_INITIALIZE_SBAR_TREE_INSTANCE);
        subloop_reserved_initialize_sbar_tree_class(SBAR_TREE);
        classes.subloop_new_class(SBARQ_TREE, SENTENTIAL_TREE, $list517, NIL, $list518);
        classes.class_set_implements_slot_listeners(SBARQ_TREE, NIL);
        classes.subloop_note_class_initialization_function(SBARQ_TREE, SUBLOOP_RESERVED_INITIALIZE_SBARQ_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(SBARQ_TREE, SUBLOOP_RESERVED_INITIALIZE_SBARQ_TREE_INSTANCE);
        subloop_reserved_initialize_sbarq_tree_class(SBARQ_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, SBARQ_TREE, $list26, NIL, $list521);
        methods.subloop_register_instance_method(SBARQ_TREE, GET_HEAD_DAUGHTERS, SBARQ_TREE_GET_HEAD_DAUGHTERS_METHOD);
        methods.methods_incorporate_instance_method(GET_ANCESTOR_OBJECTS, SBARQ_TREE, $list123, NIL, $list524);
        methods.subloop_register_instance_method(SBARQ_TREE, GET_ANCESTOR_OBJECTS, SBARQ_TREE_GET_ANCESTOR_OBJECTS_METHOD);
        classes.subloop_new_class(SQ_TREE, SENTENTIAL_TREE, $list517, NIL, $list527);
        classes.class_set_implements_slot_listeners(SQ_TREE, NIL);
        classes.subloop_note_class_initialization_function(SQ_TREE, SUBLOOP_RESERVED_INITIALIZE_SQ_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(SQ_TREE, SUBLOOP_RESERVED_INITIALIZE_SQ_TREE_INSTANCE);
        subloop_reserved_initialize_sq_tree_class(SQ_TREE);
        methods.methods_incorporate_instance_method(GET_ANCESTOR_OBJECTS, SQ_TREE, $list123, NIL, $list530);
        methods.subloop_register_instance_method(SQ_TREE, GET_ANCESTOR_OBJECTS, SQ_TREE_GET_ANCESTOR_OBJECTS_METHOD);
        classes.subloop_new_class(SINV_TREE, VERBAL_PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(SINV_TREE, NIL);
        classes.subloop_note_class_initialization_function(SINV_TREE, SUBLOOP_RESERVED_INITIALIZE_SINV_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(SINV_TREE, SUBLOOP_RESERVED_INITIALIZE_SINV_TREE_INSTANCE);
        subloop_reserved_initialize_sinv_tree_class(SINV_TREE);
        classes.subloop_new_class(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, PHRASE_TREE, NIL, NIL, $list537);
        classes.class_set_implements_slot_listeners(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, NIL);
        classes.subloop_note_class_initialization_function(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, $sym538$SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE);
        classes.subloop_note_instance_initialization_function(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, $sym539$SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE);
        subloop_reserved_initialize_prepositional_or_particle_phrase_tree_class(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, $list26, NIL, $list540);
        methods.subloop_register_instance_method(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, GET_HEAD_DAUGHTERS, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE_GET_HEAD_DAUGHTERS_METHOD);
        methods.methods_incorporate_instance_method(GET_OBLIQUE_OBJECT, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, $list26, NIL, $list545);
        methods.subloop_register_instance_method(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, GET_OBLIQUE_OBJECT, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE_GET_OBLIQUE_OBJECT_METHOD);
        methods.methods_incorporate_instance_method(STRANDED_P, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, $list26, NIL, $list548);
        methods.subloop_register_instance_method(PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, STRANDED_P, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE_STRANDED_P_METHOD);
        classes.subloop_new_class(PREPOSITIONAL_PHRASE_TREE, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, $list551, NIL, NIL);
        classes.class_set_implements_slot_listeners(PREPOSITIONAL_PHRASE_TREE, NIL);
        classes.subloop_note_class_initialization_function(PREPOSITIONAL_PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_PHRASE_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(PREPOSITIONAL_PHRASE_TREE, SUBLOOP_RESERVED_INITIALIZE_PREPOSITIONAL_PHRASE_TREE_INSTANCE);
        subloop_reserved_initialize_prepositional_phrase_tree_class(PREPOSITIONAL_PHRASE_TREE);
        classes.subloop_new_class(PP_TREE, PREPOSITIONAL_PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(PP_TREE, NIL);
        classes.subloop_note_class_initialization_function(PP_TREE, SUBLOOP_RESERVED_INITIALIZE_PP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(PP_TREE, SUBLOOP_RESERVED_INITIALIZE_PP_TREE_INSTANCE);
        subloop_reserved_initialize_pp_tree_class(PP_TREE);
        classes.subloop_new_class(WHPP_TREE, PREPOSITIONAL_PHRASE_TREE, $list401, NIL, NIL);
        classes.class_set_implements_slot_listeners(WHPP_TREE, NIL);
        classes.subloop_note_class_initialization_function(WHPP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHPP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(WHPP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHPP_TREE_INSTANCE);
        subloop_reserved_initialize_whpp_tree_class(WHPP_TREE);
        classes.subloop_new_class(PRT_TREE, PREPOSITIONAL_OR_PARTICLE_PHRASE_TREE, $list561, NIL, $list395);
        classes.class_set_implements_slot_listeners(PRT_TREE, NIL);
        classes.subloop_note_class_initialization_function(PRT_TREE, SUBLOOP_RESERVED_INITIALIZE_PRT_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(PRT_TREE, SUBLOOP_RESERVED_INITIALIZE_PRT_TREE_INSTANCE);
        subloop_reserved_initialize_prt_tree_class(PRT_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, PRT_TREE, $list26, NIL, $list564);
        methods.subloop_register_instance_method(PRT_TREE, GET_HEAD_DAUGHTERS, PRT_TREE_GET_HEAD_DAUGHTERS_METHOD);
        classes.subloop_new_class(ADJP_TREE, PHRASE_TREE, $list567, NIL, $list395);
        classes.class_set_implements_slot_listeners(ADJP_TREE, NIL);
        classes.subloop_note_class_initialization_function(ADJP_TREE, SUBLOOP_RESERVED_INITIALIZE_ADJP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(ADJP_TREE, SUBLOOP_RESERVED_INITIALIZE_ADJP_TREE_INSTANCE);
        subloop_reserved_initialize_adjp_tree_class(ADJP_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, ADJP_TREE, $list26, NIL, $list570);
        methods.subloop_register_instance_method(ADJP_TREE, GET_HEAD_DAUGHTERS, ADJP_TREE_GET_HEAD_DAUGHTERS_METHOD);
        methods.methods_incorporate_instance_method(GET_MODIFIEDS, ADJP_TREE, $list26, NIL, $list573);
        methods.subloop_register_instance_method(ADJP_TREE, GET_MODIFIEDS, ADJP_TREE_GET_MODIFIEDS_METHOD);
        classes.subloop_new_class(WHADJP_TREE, ADJP_TREE, $list401, NIL, $list576);
        classes.class_set_implements_slot_listeners(WHADJP_TREE, NIL);
        classes.subloop_note_class_initialization_function(WHADJP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHADJP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(WHADJP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHADJP_TREE_INSTANCE);
        subloop_reserved_initialize_whadjp_tree_class(WHADJP_TREE);
        methods.methods_incorporate_instance_method(LEXIFY_INT, WHADJP_TREE, $list216, $list119, $list579);
        methods.subloop_register_instance_method(WHADJP_TREE, LEXIFY_INT, WHADJP_TREE_LEXIFY_INT_METHOD);
        classes.subloop_new_class(ADVP_TREE, PHRASE_TREE, $list582, NIL, $list395);
        classes.class_set_implements_slot_listeners(ADVP_TREE, NIL);
        classes.subloop_note_class_initialization_function(ADVP_TREE, SUBLOOP_RESERVED_INITIALIZE_ADVP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(ADVP_TREE, SUBLOOP_RESERVED_INITIALIZE_ADVP_TREE_INSTANCE);
        subloop_reserved_initialize_advp_tree_class(ADVP_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, ADVP_TREE, $list26, NIL, $list585);
        methods.subloop_register_instance_method(ADVP_TREE, GET_HEAD_DAUGHTERS, ADVP_TREE_GET_HEAD_DAUGHTERS_METHOD);
        classes.subloop_new_class(WHADVP_TREE, ADVP_TREE, $list401, NIL, NIL);
        classes.class_set_implements_slot_listeners(WHADVP_TREE, NIL);
        classes.subloop_note_class_initialization_function(WHADVP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHADVP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(WHADVP_TREE, SUBLOOP_RESERVED_INITIALIZE_WHADVP_TREE_INSTANCE);
        subloop_reserved_initialize_whadvp_tree_class(WHADVP_TREE);
        classes.subloop_new_class(FRAG_TREE, PHRASE_TREE, NIL, NIL, NIL);
        classes.class_set_implements_slot_listeners(FRAG_TREE, NIL);
        classes.subloop_note_class_initialization_function(FRAG_TREE, SUBLOOP_RESERVED_INITIALIZE_FRAG_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(FRAG_TREE, SUBLOOP_RESERVED_INITIALIZE_FRAG_TREE_INSTANCE);
        subloop_reserved_initialize_frag_tree_class(FRAG_TREE);
        classes.subloop_new_class(QP_TREE, PHRASE_TREE, $list595, NIL, $list596);
        classes.class_set_implements_slot_listeners(QP_TREE, NIL);
        classes.subloop_note_class_initialization_function(QP_TREE, SUBLOOP_RESERVED_INITIALIZE_QP_TREE_CLASS);
        classes.subloop_note_instance_initialization_function(QP_TREE, SUBLOOP_RESERVED_INITIALIZE_QP_TREE_INSTANCE);
        subloop_reserved_initialize_qp_tree_class(QP_TREE);
        methods.methods_incorporate_instance_method(GET_HEAD_DAUGHTERS, QP_TREE, $list26, NIL, $list599);
        methods.subloop_register_instance_method(QP_TREE, GET_HEAD_DAUGHTERS, QP_TREE_GET_HEAD_DAUGHTERS_METHOD);
        methods.methods_incorporate_instance_method(GET_QUANTIFIED, QP_TREE, $list26, NIL, $list603);
        methods.subloop_register_instance_method(QP_TREE, GET_QUANTIFIED, QP_TREE_GET_QUANTIFIED_METHOD);
        methods.methods_incorporate_instance_method(CYCLIFY_INT, QP_TREE, $list123, NIL, $list606);
        methods.subloop_register_instance_method(QP_TREE, CYCLIFY_INT, QP_TREE_CYCLIFY_INT_METHOD);
        return NIL;
    }

    static private final SubLList $list_alt340 = list(makeString("@return word-tree-p; the quantifier of this NP, or nil if there is none"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SPECIFIER")))))));

    static private final SubLList $list_alt343 = list(makeString("@return boolean; t if this phrase is a possessive, nil otherwise"), list(RET, list(makeSymbol("POSSESSIVE-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), list(makeSymbol("1-"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))))))));

    static private final SubLList $list_alt345 = list(makeString("@return booleanp; t if this tree is temporal, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(makeSymbol("PWHEN"), makeSymbol("HEAD"), list(RET, list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("TEMPORAL-TREE-P")))))));

    static private final SubLList $list_alt348 = list(makeString("@return booleanp; t if this tree is a definite description, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("CAND"), makeSymbol("HEAD"), list(makeSymbol("NOMINAL-TREE-P"), makeSymbol("HEAD"))), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("DEFINITE-DESCRIPTION-P")))))));

    static private final SubLList $list_alt351 = list(makeString("@return booleanp; t if this tree is a name, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("CAND"), makeSymbol("HEAD"), list(makeSymbol("NAME-WORD-TREE-P"), makeSymbol("HEAD"))))));

    static private final SubLList $list_alt354 = list(makeString("@return listp; a list of genders of this np with possible gender values \n   :FEMININE, :MASCULINE and :NEUTER"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-GENDER")))))));

    static private final SubLList $list_alt357 = list(makeString("@return listp; a list of numbers of this np with possible number values\n   :SINGULAR and :PLURAL"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-NUMBER")))))));

    static private final SubLList $list_alt360 = list(makeString("@return numberp; the person of this noun; 1, 2, or 3"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-PERSON")))))));

    static private final SubLList $list_alt363 = list(makeString("@return booleanp; t if this tree is singular, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("SINGULAR-P")))))));

    static private final SubLList $list_alt366 = list(makeString("@return booleanp; t if this tree is plural, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("PLURAL-P")))))));

    static private final SubLList $list_alt368 = list(makeString("@return parse-tree-p; a version of this np in which multi-word\n   lemmata have been conflated into single words and each word has\n   lexical entries added\n   @side-effects this tree\'s daughters will be destructively modified"), list(makeSymbol("CHECK-TYPE"), makeSymbol("LEXICON"), makeSymbol("ABSTRACT-LEXICON-P")), list(makeSymbol("CDOVECTOR"), list(makeSymbol("DAUGHTER"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"))), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DEFINITE-DESCRIPTION-P"))), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD")))), list(makeSymbol("AUGMENTED-LEXES"), NIL)), list(makeSymbol("PWHEN"), makeSymbol("HEAD"), list(makeSymbol("CDOLIST"), list(makeSymbol("RLE"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-RANKED-LEXES")))), list(makeSymbol("CLET"), list(list(makeSymbol("LEX"), list(makeSymbol("FWHEN"), makeSymbol("RLE"), list(makeSymbol("FIM"), list(makeSymbol("RLE-LEX"), makeSymbol("RLE")), list(QUOTE, makeSymbol("COPY")))))), list(makeSymbol("PUNLESS"), list(makeSymbol("INTRODUCES-TERM?"), makeSymbol("LEX")), list(makeSymbol("CLET"), list(list(makeSymbol("INSTANCE"), list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CONTEXT"))), list(QUOTE, makeSymbol("GET-INSTANCE")), makeSymbol("SELF"))), list(makeSymbol("SEMTRANS"), list(makeSymbol("FIM"), makeSymbol("LEX"), list(QUOTE, makeSymbol("GET")), makeKeyword("SEMTRANS")))), list(makeSymbol("FIM"), makeSymbol("LEX"), list(QUOTE, makeSymbol("SET")), makeKeyword("SEMTRANS"), list(makeSymbol("CONJOIN"), list(makeSymbol("LIST"), makeSymbol("SEMTRANS"), list(makeSymbol("BQ-LIST"), reader_make_constant_shell("equals"), $NOUN, makeSymbol("INSTANCE"))))), list(makeSymbol("RLE-SET-CONFIDENCE"), makeSymbol("RLE"), ONE_INTEGER))), list(makeSymbol("CPUSH"), makeSymbol("RLE"), makeSymbol("AUGMENTED-LEXES")))), list(makeSymbol("SET-SLOT"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("LEXES")), makeSymbol("AUGMENTED-LEXES"))))), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt378 = list(makeString("@return listp; a list of all head daughters of this phrase"), list(makeSymbol("PCOND"), list(list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), TWO_INTEGER), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("$")), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ONE_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("CD"))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))), list(list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), ONE_INTEGER), list(EQL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-CATEGORY"))), makeKeyword("CD"))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("COORDINATE-PHRASE-P"))), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))))), list(list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), TWO_INTEGER), list(makeSymbol("VBG-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ONE_INTEGER)), list(makeSymbol("DETERMINER-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER))), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ONE_INTEGER)))), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("PRETERMINAL-P"))), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-WORD-TREE-P"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER")))))), list(T, list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-WORD-TREE-P"))))), list(makeSymbol("PIF"), makeSymbol("HEAD-DAUGHTER"), list(RET, list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER"))), list(makeSymbol("PROGN"), list(makeSymbol("CSETQ"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("NOMINAL-PHRASE-TREE-P")))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER"))))))))));

    static private final SubLList $list_alt395 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")));

    static private final SubLList $list_alt398 = list(makeString("@return listp; the head daughters of this pnp tree"), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))))));

    static private final SubLList $list_alt401 = list(makeSymbol("WH-TREE"));

    static private final SubLList $list_alt405 = list(makeSymbol("VERBAL-TREE"), makeSymbol("CYCLIFIABLE-VERB-PHRASE"));

    static private final SubLList $list_alt406 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-NUMBER"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PERSON"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-TENSE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-PREDICATE"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SUBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-DIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-INDIRECT-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SEMANTIC-OBJECTS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-VERBAL-COMPLEMENT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("COPULA-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("DUMMY-TO-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PASSIVE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FINITE-P"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("VP-COORDINATE-PHRASE-P"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PARTITION-SEMANTIC-COMPLEMENTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT-VP"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT-VP-COORDINATION"), NIL, makeKeyword("INSTANTIATE-TEMPLATE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INVERTED-P"), NIL, makeKeyword("PROTECTED")) });

    static private final SubLList $list_alt408 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT")))), list(makeSymbol("SUBJ-CYCLS"), list(makeSymbol("FWHEN"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SUBJECT"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))), list(makeSymbol("CONJUNCTION"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CONJUNCTION")))), list(makeSymbol("VPS"), list(makeSymbol("FIM"), makeSymbol("CONJUNCTION"), list(QUOTE, makeSymbol("GET-COMPLEMENTS")))), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("VP"), makeSymbol("VPS")), list(makeSymbol("CPUSH"), list(makeSymbol("FIM"), makeSymbol("VP"), list(QUOTE, makeSymbol("CYCLIFY-INT-VP"))), makeSymbol("CYCLS"))), list(RET, list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), list(makeSymbol("CONS"), makeSymbol("SUBJ-CYCLS"), makeSymbol("CYCLS")))))));

    static private final SubLList $list_alt412 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("HEADS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEADS")))), list(makeSymbol("ALL-CONJUNCTION-CYCLS"), NIL), list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT")))), list(makeSymbol("COMPLEMENTS"), list(makeSymbol("FWHEN"), makeSymbol("HEADS"), list(makeSymbol("FIM"), list(makeSymbol("FIRST"), makeSymbol("HEADS")), list(QUOTE, makeSymbol("GET-SEMANTIC-COMPLEMENTS"))))), list(makeSymbol("COMP-CYCLS"), NIL), list(makeSymbol("CYCLS"), NIL)), list(makeSymbol("CSETQ"), makeSymbol("COMPLEMENTS"), list(makeSymbol("DELETE-CYCLIFIABLE"), makeSymbol("SUBJECT"), makeSymbol("COMPLEMENTS"))), list(makeSymbol("WITHOUT-RECURSIVE-CYCLIFICATION"), list(makeSymbol("CLET"), list(list(makeSymbol("*CYCLIFICATION-IN-PROGRESS*"), list(makeSymbol("APPEND"), makeSymbol("HEADS"), makeSymbol("*CYCLIFICATION-IN-PROGRESS*")))), list(makeSymbol("CDOLIST"), list(makeSymbol("HEAD"), makeSymbol("HEADS")), list(makeSymbol("CLET"), list(list(makeSymbol("ALL-HEAD-CYCLS"), NIL), list(makeSymbol("MOD-CYCLS"), list(makeSymbol("GET-MOD-CYCLS"), makeSymbol("HEAD"))), list(makeSymbol("RENAMINGS"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-KEYWORD-RENAMINGS")), list(makeSymbol("REQUIRED-KEYWORDS"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-RANKED-LEXES"))))))), list(makeSymbol("CDOLIST"), list(makeSymbol("CONJUNCTION"), makeSymbol("RENAMINGS")), list(makeSymbol("CLET"), list(list(makeSymbol("ALL-RLE-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("RLE"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-RANKED-LEXES")))), list(makeSymbol("CLET"), list(list(makeSymbol("COMPOSITE-VERBAL-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("RENAMING"), makeSymbol("CONJUNCTION")), list(makeSymbol("CLET"), list(list(makeSymbol("RENAMED0"), list(makeSymbol("APPLY-LEX-RENAMING"), makeSymbol("HEAD"), makeSymbol("RLE"), makeSymbol("RENAMING"))), list(makeSymbol("RENAMED"), list(makeSymbol("FWHEN"), makeSymbol("RENAMED0"), list(makeSymbol("LIST"), list(makeSymbol("QUANTIFY-IMPLICIT-SUBJECT"), makeSymbol("RENAMED0")))))), list(makeSymbol("ALIST-CPUSHNEW"), makeSymbol("COMPOSITE-VERBAL-CYCLS"), list(makeSymbol("RENAMING-KEY"), makeSymbol("RENAMING")), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), list(makeSymbol("CONS"), makeSymbol("RENAMED"), makeSymbol("MOD-CYCLS"))))))), list(makeSymbol("CLET"), list(list(makeSymbol("SENSE-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("VALUE"), list(makeSymbol("ALIST-VALUES"), makeSymbol("COMPOSITE-VERBAL-CYCLS"))), list(makeSymbol("CPUSH"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("VALUE"))), makeSymbol("SENSE-CYCLS"))), list(makeSymbol("CPUSH"), makeSymbol("SENSE-CYCLS"), makeSymbol("ALL-RLE-CYCLS"))))), list(makeSymbol("CPUSH"), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-RLE-CYCLS")), makeSymbol("ALL-HEAD-CYCLS")))), list(makeSymbol("CPUSH"), makeSymbol("ALL-HEAD-CYCLS"), makeSymbol("ALL-CONJUNCTION-CYCLS")))), list(makeSymbol("CDOLIST"), list(makeSymbol("COMPLEMENT"), makeSymbol("COMPLEMENTS")), list(makeSymbol("CLET"), list(list(makeSymbol("COMP-CYCL"), list(makeSymbol("FIM"), makeSymbol("COMPLEMENT"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))), list(makeSymbol("PWHEN"), makeSymbol("COMP-CYCL"), list(makeSymbol("CPUSH"), makeSymbol("COMP-CYCL"), makeSymbol("COMP-CYCLS"))))), list(makeSymbol("CDOLIST"), list(makeSymbol("ALL-HEAD-CYCLS"), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-CONJUNCTION-CYCLS"))), list(makeSymbol("CLET"), list(list(makeSymbol("CYCLS0"), NIL), list(makeSymbol("ALL-SUBJECTS-WITH-OBJECTS-CYCLS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("ITEM"), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-HEAD-CYCLS"))), list(makeSymbol("CLET"), list(list(makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("ITEM2"), list(makeSymbol("CROSS-PRODUCTS"), makeSymbol("ITEM"))), list(makeSymbol("CSETQ"), makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT"), list(makeSymbol("APPEND"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ITEM2"))), makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT")))), list(makeSymbol("CPUSH"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), list(makeSymbol("CONS"), makeSymbol("COMPLETE-CYCL-FOR-ONE-SUBJECT"), makeSymbol("COMP-CYCLS")))), makeSymbol("ALL-SUBJECTS-WITH-OBJECTS-CYCLS")))), list(makeSymbol("CSETQ"), makeSymbol("CYCLS0"), list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("INDEXED-PRODUCTS"), makeSymbol("ALL-SUBJECTS-WITH-OBJECTS-CYCLS")))), list(makeSymbol("CSETQ"), makeSymbol("CYCLS"), list(makeSymbol("APPEND"), makeSymbol("CYCLS0"), makeSymbol("CYCLS"))))))), list(RET, makeSymbol("CYCLS"))));

    static private final SubLList $list_alt416 = list(list(makeSymbol("WITHOUT-DUPLICATE-CYCLIFICATION"), list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("PCOND"), list(list(makeSymbol("CYCLIFIABLE-SENTENTIAL-PHRASE-P"), makeSymbol("SELF")), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DTR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD-DAUGHTER"))))), list(makeSymbol("PWHEN"), makeSymbol("HEAD-DTR"), list(RET, list(makeSymbol("FIM"), makeSymbol("HEAD-DTR"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))))), list(list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("VP-COORDINATE-PHRASE-P"))), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CYCLIFY-INT-VP-COORDINATION"))))), list(T, list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT")))), list(makeSymbol("SUBJ-CYCLS"), list(makeSymbol("FWHEN"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SUBJECT"), list(QUOTE, makeSymbol("CYCLIFY-INT"))))), list(makeSymbol("VP-CYCLS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CYCLIFY-INT-VP")))), list(makeSymbol("SUBJ-VP-CYCLS"), list(makeSymbol("FWHEN"), makeSymbol("SUBJ-CYCLS"), list(makeSymbol("LIST"), makeSymbol("SUBJ-CYCLS"))))), list(makeSymbol("PWHEN"), makeSymbol("VP-CYCLS"), list(makeSymbol("CPUSH"), makeSymbol("VP-CYCLS"), makeSymbol("SUBJ-VP-CYCLS"))), list(RET, list(makeSymbol("MAPCAR"), list(QUOTE, makeSymbol("INSTANTIATE-SCOPE")), list(makeSymbol("CROSS-PRODUCTS"), makeSymbol("SUBJ-VP-CYCLS")))))))));

    static private final SubLList $list_alt419 = list(list(makeSymbol("CLET"), list(list(makeSymbol("CONJUNCTION"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-CONJUNCTION"))))), list(RET, list(makeSymbol("CAND"), makeSymbol("CONJUNCTION"), list(makeSymbol("CNOT"), list(makeSymbol("MEMBER-IF-NOT"), list(QUOTE, makeSymbol("CYCLIFIABLE-VERB-PHRASE-P")), list(makeSymbol("FIM"), makeSymbol("CONJUNCTION"), list(QUOTE, makeSymbol("GET-COMPLEMENTS")))))))));

    static private final SubLList $list_alt425 = list(makeString("@return word-tree-p; the predicate of this sentence"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD")))));

    static private final SubLList $list_alt427 = list(makeString("@return listp; a list of numbers of this vp with possible number values\n   :SINGULAR and :PLURAL"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-NUMBER")))))));

    static private final SubLList $list_alt429 = list(makeString("@return numberp; the person of this vp; 1, 2, or 3"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-PERSON")))))));

    static private final SubLList $list_alt432 = list(makeString("@return keywordp; the tense of this vp; either :PRESENT, :PAST, :FUTURE,\n   or :INFINITIVE"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-TENSE")))))));

    static private final SubLList $list_alt434 = list(makeString("@return phrase-tree-p; the subject of this verbal tree"), list(makeSymbol("PWHEN"), makeSymbol("MOTHER"), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")), list(makeSymbol("1-"), makeSymbol("IDX"))))), list(makeSymbol("PWHEN"), list(makeSymbol("CAND"), list(makeSymbol("CNOT"), makeSymbol("SUBJECT")), list(makeSymbol("VERBAL-TREE-P"), makeSymbol("MOTHER"))), list(makeSymbol("CSETQ"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-SUBJECT"))))), list(RET, makeSymbol("SUBJECT")))));

    static private final SubLList $list_alt438 = list(makeString("@return listp; a list of all objects of this tree, in surface order"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-OBJECTS")))))));

    static private final SubLList $list_alt441 = list(makeString("@return phrase-tree-p; the direct object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-DIRECT-OBJECT")))))));

    static private final SubLList $list_alt444 = list(makeString("@return phrase-tree-p; the indirect object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-INDIRECT-OBJECT")))))));

    static private final SubLList $list_alt447 = list(makeString("@return phrase-tree-p; the semantic direct object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-DIRECT-OBJECT")))))));

    static private final SubLList $list_alt450 = list(makeString("@return phrase-tree-p; the semantic indirect object of this verb phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-INDIRECT-OBJECT")))))));

    static private final SubLList $list_alt453 = list(makeString("@return phrase-tree-p; the semantic subject of this verbal tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-SUBJECT")))))));

    static private final SubLList $list_alt456 = list(makeString("@return listp; a list of the semantic subject of this verbal tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SEMANTIC-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-SEMANTIC-OBJECTS")))))));

    static private final SubLList $list_alt458 = list(makeString("@return listp; a list of all verbal complements of this verbal tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-VERBAL-COMPLEMENT")))))));

    static private final SubLList $list_alt461 = list(makeString("@return booleanp; t if this phrase-tree is a copula construction, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("AUX-VERB-WORD-P"), makeSymbol("HEAD")), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("COPULA-P")))))));

    static private final SubLList $list_alt464 = list(makeString("@return booleanp; t if this phrase-tree is a dummy 'to' construction, nil otherwise"), list(RET, list(makeSymbol("CAND"), list(makeSymbol("VERBAL-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD")))), list(EQUAL, list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER), list(QUOTE, makeSymbol("GET-STRING"))), makeString("to")))));

    static private final SubLList $list_alt468 = list(makeString("@return booleanp; t if this phrase-tree is a passive construction, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("PASSIVE-P")))))));

    static private final SubLList $list_alt471 = list(makeString("@return booleanp; t if this phrase tree is finite, nil otherwise"), list(RET, list(makeSymbol("FINITE-VERBAL-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))));

    static private final SubLList $list_alt474 = list(list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("PARTITION-SEMANTIC-COMPLEMENTS")))))));

    static private final SubLList $list_alt477 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(makeSymbol("PIF"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVERTED-P"))), list(RET, list(makeSymbol("FWHEN"), list(makeSymbol("VERBAL-PHRASE-TREE-P"), makeSymbol("MOTHER")), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTOR-OBJECTS"))))), list(RET, list(makeSymbol("APPEND"), list(makeSymbol("FWHEN"), list(makeSymbol("VERBAL-PHRASE-TREE-P"), makeSymbol("MOTHER")), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTOR-OBJECTS")))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")))))));

    @Override
    public void declareFunctions() {
        declare_parse_tree_file();
    }

    @Override
    public void initializeVariables() {
        init_parse_tree_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_parse_tree_file();
    }

    static {
    }

    static private final SubLList $list_alt481 = list(makeString("@return listp; the head daughters of this verbal phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTERS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("VERBAL-WORD-TREE-P"))))), list(RET, list(makeSymbol("FIF"), makeSymbol("HEAD-DAUGHTERS"), makeSymbol("HEAD-DAUGHTERS"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("VERBAL-PHRASE-TREE-P")))))));

    static private final SubLList $list_alt485 = list(makeString("@return booleanp; t if this verbal tree is inverted, nil otherwise"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("INVERTED-P")))))));

    static private final SubLList $list_alt488 = list(makeSymbol("CYCLIFIABLE-SENTENTIAL-PHRASE"));

    static private final SubLList $list_alt489 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-SUBJECT"), NIL, makeKeyword("PUBLIC")));

    static private final SubLList $list_alt492 = list(makeString("@return np-tree-p; the subject of this sentence"), list(makeSymbol("PIF"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVERTED-P"))), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD")))), list(makeSymbol("HEAD-MOTHER"), list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-MOTHER")))))), list(makeSymbol("PWHEN"), makeSymbol("HEAD-MOTHER"), list(RET, list(makeSymbol("FIM"), makeSymbol("HEAD-MOTHER"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")), list(makeSymbol("1+"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-INDEX")))))))), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))))), list(makeSymbol("PIF"), makeSymbol("SUBJECT"), list(RET, makeSymbol("SUBJECT")), list(makeSymbol("CDOLIST"), list(makeSymbol("ANCESTOR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-ANCESTORS")))), list(makeSymbol("PWHEN"), list(makeSymbol("VERBAL-TREE-P"), makeSymbol("ANCESTOR")), list(RET, list(makeSymbol("FIM"), makeSymbol("ANCESTOR"), list(QUOTE, makeSymbol("GET-SUBJECT"))))))))));

    static private final SubLList $list_alt494 = list(makeString("@return listp; the constituents this sentence modifies"), list(makeSymbol("PWHEN"), list(makeSymbol("RELATIVE-CLAUSE-P"), makeSymbol("SELF")), list(makeSymbol("CLET"), list(list(makeSymbol("FIRST-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)), list(makeSymbol("FIRST-HEAD"), list(makeSymbol("FWHEN"), makeSymbol("FIRST-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("FIRST-DAUGHTER"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(makeSymbol("MODIFIED"), list(makeSymbol("FWHEN"), makeSymbol("FIRST-HEAD"), list(makeSymbol("FIM"), makeSymbol("FIRST-HEAD"), list(QUOTE, makeSymbol("GET-ANTECEDENT"))))), list(makeSymbol("MOD-HEAD"), list(makeSymbol("FWHEN"), makeSymbol("MODIFIED"), list(makeSymbol("FIM"), makeSymbol("MODIFIED"), list(QUOTE, makeSymbol("GET-HEAD")))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("MOD-HEAD"), list(makeSymbol("LIST"), makeSymbol("MOD-HEAD")))))));

    static private final SubLList $list_alt507 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PUBLIC")));

    static private final SubLList $list_alt510 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(makeSymbol("CLET"), list(list(makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT"))))), list(makeSymbol("PCOND"), list(list(makeSymbol("CAND"), makeSymbol("SUBJECT"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVERTED-P")))), list(RET, list(makeSymbol("LIST"), makeSymbol("SUBJECT")))), list(list(makeSymbol("CAND"), makeSymbol("MOTHER"), list(makeSymbol("RELATIVE-CLAUSE-P"), makeSymbol("MOTHER"))), list(RET, list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-MODIFIEDS"))))))));

    static private final SubLList $list_alt517 = list(makeSymbol("QUESTION-TREE"));

    static private final SubLList $list_alt518 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PROTECTED")));

    static private final SubLList $list_alt521 = list(makeString("@return listp; the head daughters of this sbarq tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-FIRST")), list(QUOTE, makeSymbol("VERBAL-TREE-P"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DAUGHTER"), list(makeSymbol("LIST"), makeSymbol("HEAD-DAUGHTER"))))));

    static private final SubLList $list_alt524 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(RET, list(makeSymbol("DELETE"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-SUBJECT"))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P"))), list(QUOTE, EQ))));

    static private final SubLList $list_alt527 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-ANCESTOR-OBJECTS"), NIL, makeKeyword("PROTECTED")));

    static private final SubLList $list_alt530 = list(makeString("@return listp; the local syntactic objects of this tree and its ancestors"), list(RET, list(makeSymbol("APPEND"), list(makeSymbol("CDR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("NOMINAL-TREE-P")))), list(makeSymbol("FWHEN"), list(makeSymbol("VERBAL-PHRASE-TREE-P"), makeSymbol("MOTHER")), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-ANCESTOR-OBJECTS")))))));

    static private final SubLList $list_alt537 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-OBLIQUE-OBJECT"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("STRANDED-P"), NIL, makeKeyword("PUBLIC")));

    static private final SubLList $list_alt540 = list(makeString("@return listp; the head daughters of this prepositional-or-particle-phrase-tree"), list(makeSymbol("PWHEN"), list(makeSymbol("VBG-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)), list(RET, list(makeSymbol("LIST"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))), list(RET, list(makeSymbol("APPEND"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("PREPOSITIONAL-TREE-P"))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("PARTICLE-TREE-P"))))));

    static private final SubLList $list_alt545 = list(makeString("@return nominal-phrase-tree; the complement np of the prepositional head of this phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-OBLIQUE-OBJECT")))))));

    static private final SubLList $list_alt548 = list(makeString("@return booleanp; non-nil if the preposition or particle of this phrase is \n   stranded, nil otherwise"), list(RET, list(makeSymbol("CAND"), list(makeSymbol("="), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("DAUGHTER-COUNT"))), ONE_INTEGER), list(makeSymbol("PREPOSITIONAL-OR-PARTICLE-WORD-TREE-P"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-DAUGHTER")), ZERO_INTEGER)))));

    static private final SubLList $list_alt551 = list(makeSymbol("PREPOSITIONAL-TREE"));

    static private final SubLList $list_alt561 = list(makeSymbol("PARTICLE-TREE"));

    static private final SubLList $list_alt564 = list(makeString("@return listp; the head daughters of this prepositional-or-particle-phrase-tree"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("PARTICLE-TREE-P")))));

    static private final SubLList $list_alt567 = list(makeSymbol("ADJECTIVAL-TREE"));

    static private final SubLList $list_alt570 = list(makeString("@return listp; the head daughters of this adjp-tree"), list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND")), list(QUOTE, makeSymbol("ADJECTIVAL-TREE-P")))));

    static private final SubLList $list_alt573 = list(makeString("@return listp; the complement of this adjective"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-HEAD"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD"), list(makeSymbol("FIM"), makeSymbol("HEAD"), list(QUOTE, makeSymbol("GET-MODIFIEDS")))))));

    static private final SubLList $list_alt576 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LEXIFY-INT"), NIL, makeKeyword("PROTECTED")));

    static private final SubLList $list_alt579 = list(makeString("@return parse-tree-p; a version of this np in which multi-word\n   lemmata have been conflated into single words and each word has\n   lexical entries added\n   @side-effects this tree\'s daughters will be destructively modified"), list(makeSymbol("CHECK-TYPE"), makeSymbol("LEXICON"), makeSymbol("ABSTRACT-LEXICON-P")), list(makeSymbol("CDOVECTOR"), list(makeSymbol("DAUGHTER"), list(makeSymbol("GET-PHRASE-TREE-DAUGHTERS"), makeSymbol("SELF"))), list(makeSymbol("FIM"), makeSymbol("DAUGHTER"), list(QUOTE, makeSymbol("LEXIFY-INT")), makeSymbol("LEXICON"))), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt582 = list(makeSymbol("ADVERBIAL-TREE"));

    static private final SubLList $list_alt585 = list(makeString("@return listp; a list of head daughters of this adverbial tree"), list(makeSymbol("CLET"), list(list(makeSymbol("HEAD-DTR"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FIND-LAST")), list(QUOTE, makeSymbol("ADVERBIAL-TREE-P"))))), list(RET, list(makeSymbol("FWHEN"), makeSymbol("HEAD-DTR"), list(makeSymbol("LIST"), makeSymbol("HEAD-DTR"))))));

    static private final SubLList $list_alt595 = list(makeSymbol("NUMERICAL-TREE"), makeSymbol("QUANTIFIER-TREE"));

    static private final SubLList $list_alt596 = list(list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-HEAD-DAUGHTERS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GET-QUANTIFIED"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("CYCLIFY-INT"), NIL, makeKeyword("PROTECTED")));

    static private final SubLList $list_alt599 = list(makeString("@return listp; a list of all head daughters of this phrase"), list(RET, list(makeSymbol("FIND"), makeSymbol("SELF"), list(QUOTE, makeSymbol("CARDINAL-WORD-TREE-P")))));

    static private final SubLList $list_alt603 = list(makeString("@return word-tree; the word that restricts the variable that this determiner quantifies"), list(RET, list(makeSymbol("FWHEN"), makeSymbol("MOTHER"), list(makeSymbol("FIM"), makeSymbol("MOTHER"), list(QUOTE, makeSymbol("GET-HEAD"))))));

    static private final SubLList $list_alt606 = list(makeString("@return listp; the CycL representation of the meaning of this quantifier phrase"), list(makeSymbol("CLET"), list(list(makeSymbol("SCYCLS"), list(makeSymbol("SUBCYCLIFIER-CYCLIFY"), makeSymbol("SELF")))), list(makeSymbol("PWHEN"), makeSymbol("SCYCLS"), list(RET, makeSymbol("SCYCLS")))), list(makeSymbol("CLET"), list(list(makeSymbol("CYCLS"), NIL), list(makeSymbol("NUMBER"), list(makeSymbol("STRING-TO-INTERVAL"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-STRING"))))), list(makeSymbol("QUANTIFIED"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("GET-QUANTIFIED")))), list(makeSymbol("RESTRICTIONS"), list(makeSymbol("FWHEN"), makeSymbol("QUANTIFIED"), list(makeSymbol("FIM"), makeSymbol("QUANTIFIED"), list(QUOTE, makeSymbol("CYCLIFY-NUCLEUS"))))), list(makeSymbol("VARIABLE"), list(makeSymbol("FWHEN"), makeSymbol("QUANTIFIED"), list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("QUANTIFIED"), list(QUOTE, makeSymbol("GET-REFS"))))))), list(makeSymbol("PWHEN"), makeSymbol("NUMBER"), list(makeSymbol("CDOLIST"), list(makeSymbol("RESTRICTION"), makeSymbol("RESTRICTIONS")), list(makeSymbol("CPUSH"), list(makeSymbol("NEW-CYCLIFICATION"), list(makeSymbol("BQ-LIST"), reader_make_constant_shell("thereExistAtLeast"), makeSymbol("NUMBER"), makeSymbol("VARIABLE"), list(makeSymbol("BQ-LIST*"), reader_make_constant_shell("and"), makeSymbol("RESTRICTION"), list(QUOTE, list(makeKeyword("SCOPE"))))), makeSymbol("SELF"), ONE_INTEGER), makeSymbol("CYCLS")))), list(RET, makeSymbol("CYCLS"))));

    static private final SubLList $list_alt610 = list(makeKeyword("SCOPE"));
}

/**
 * Total time: 3167 ms synthetic
 */
