/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.quirk;


import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.sublisp_throw;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Eval.eval;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.princ;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_string;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.classes;
import com.cyc.cycjava.cycl.instances;
import com.cyc.cycjava.cycl.methods;
import com.cyc.cycjava.cycl.object;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
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


public final class wordnet_proxy extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new wordnet_proxy();

 public static final String myName = "com.cyc.cycjava.cycl.quirk.wordnet_proxy";


    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list0 = list(makeSymbol("CLASS"));

    static private final SubLList $list3 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list4 = list(NIL, list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol DEFAULT_INITIALIZER = makeSymbol("DEFAULT-INITIALIZER");

    static private final SubLList $list7 = list(makeSymbol("FUNCTION"), makeSymbol("LINK"));

    static private final SubLList $list9 = list(makeSymbol("PROXY"));

    static private final SubLList $list13 = list(QUOTE, makeSymbol("LINKS"));

    private static final SubLSymbol WORDNET_PROXY = makeSymbol("WORDNET-PROXY");

    static private final SubLList $list16 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ABSOLUTE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("WN-CLASS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LINKS"), list(makeSymbol("TYPE")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INVOKE"), list(makeSymbol("METHOD"), makeSymbol("&OPTIONAL"), makeSymbol("PARAMS")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SENSES"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SYNSETS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")) });

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WORDNET_PROXY_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WORDNET-PROXY-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WORDNET_PROXY_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WORDNET-PROXY-INSTANCE");

    private static final SubLSymbol ABSOLUTE_KEY = makeSymbol("ABSOLUTE-KEY");

    static private final SubLList $list23 = list(list(RET, list(makeSymbol("CONS"), list(makeSymbol("BQ-LIST"), makeString("class"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("WN-CLASS")))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("RELATIVE-KEY"))))));

    static private final SubLString $$$class = makeString("class");

    private static final SubLSymbol WN_CLASS = makeSymbol("WN-CLASS");

    private static final SubLSymbol RELATIVE_KEY = makeSymbol("RELATIVE-KEY");

    private static final SubLSymbol WORDNET_PROXY_ABSOLUTE_KEY_METHOD = makeSymbol("WORDNET-PROXY-ABSOLUTE-KEY-METHOD");

    private static final SubLSymbol SENSES = makeSymbol("SENSES");

    static private final SubLList $list29 = list(list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVOKE")), makeString("getSenses"))));

    private static final SubLSymbol INVOKE = makeSymbol("INVOKE");

    static private final SubLString $$$getSenses = makeString("getSenses");

    private static final SubLSymbol WORDNET_PROXY_SENSES_METHOD = makeSymbol("WORDNET-PROXY-SENSES-METHOD");

    static private final SubLList $list34 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SYNSETS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("SENSE"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("SENSES")))), list(makeSymbol("CPUSH"), list(makeSymbol("GET-SLOT"), makeSymbol("SENSE"), list(QUOTE, makeSymbol("SYNSET"))), makeSymbol("SYNSETS"))), list(RET, makeSymbol("SYNSETS"))));

    private static final SubLSymbol WORDNET_PROXY_SYNSETS_METHOD = makeSymbol("WORDNET-PROXY-SYNSETS-METHOD");

    static private final SubLList $list38 = list(makeSymbol("TYPE"));

    static private final SubLList $list39 = list(list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVOKE")), makeString("getPointerTargets"), list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("pointerType"), makeSymbol("TYPE"))))));

    static private final SubLString $$$getPointerTargets = makeString("getPointerTargets");

    static private final SubLString $$$pointerType = makeString("pointerType");

    private static final SubLSymbol WORDNET_PROXY_LINKS_METHOD = makeSymbol("WORDNET-PROXY-LINKS-METHOD");

    static private final SubLList $list43 = list(makeSymbol("METHOD"), makeSymbol("&OPTIONAL"), makeSymbol("PARAMS"));

    static private final SubLList $list44 = list(list(RET, list(makeSymbol("EVAL"), list(makeSymbol("INVOKE-WORDNET"), list(makeSymbol("APPEND"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ABSOLUTE-KEY"))), list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("method"), makeSymbol("METHOD"))), makeSymbol("PARAMS"))))));

    static private final SubLString $$$method = makeString("method");

    private static final SubLSymbol WORDNET_PROXY_INVOKE_METHOD = makeSymbol("WORDNET-PROXY-INVOKE-METHOD");

    private static final SubLSymbol WORD_PROXY = makeSymbol("WORD-PROXY");

    static private final SubLList $list48 = list(list(makeSymbol("FORM"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("POS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FORM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), EQUAL, list(makeSymbol("OBJECT")), makeKeyword("PUBLIC")));

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WORD_PROXY_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WORD-PROXY-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_WORD_PROXY_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-WORD-PROXY-INSTANCE");

    static private final SubLList $list53 = list(list(RET, makeString("word")));

    static private final SubLString $$$word = makeString("word");

    private static final SubLSymbol WORD_PROXY_WN_CLASS_METHOD = makeSymbol("WORD-PROXY-WN-CLASS-METHOD");

    static private final SubLList $list56 = list(list(RET, list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("pos"), makeSymbol("POS")), list(makeSymbol("BQ-LIST"), makeString("form"), makeSymbol("FORM")))));

    static private final SubLSymbol $sym57$OUTER_CATCH_FOR_WORD_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-WORD-PROXY-METHOD");

    static private final SubLString $$$pos = makeString("pos");

    static private final SubLString $$$form = makeString("form");

    private static final SubLSymbol WORD_PROXY_RELATIVE_KEY_METHOD = makeSymbol("WORD-PROXY-RELATIVE-KEY-METHOD");

    static private final SubLList $list62 = list(makeSymbol("STREAM"), makeSymbol("DEPTH"));

    static private final SubLList $list63 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("WRITE-STRING"), makeString("#<WORD-PROXY "), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeSymbol("FORM"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(">"), makeSymbol("STREAM")), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym64$OUTER_CATCH_FOR_WORD_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-WORD-PROXY-METHOD");

    static private final SubLString $str65$__WORD_PROXY_ = makeString("#<WORD-PROXY ");

    static private final SubLString $str66$_ = makeString(">");

    private static final SubLSymbol WORD_PROXY_PRINT_METHOD = makeSymbol("WORD-PROXY-PRINT-METHOD");

    static private final SubLList $list68 = list(list(makeSymbol("CLET"), list(list(makeSymbol("RES"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("SENSE"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("SENSES")))), list(makeSymbol("CSETF"), makeSymbol("RES"), list(makeSymbol("APPEND"), list(makeSymbol("FIM"), makeSymbol("SENSE"), list(QUOTE, makeSymbol("LINKS")), makeSymbol("TYPE")), makeSymbol("RES")))), list(RET, makeSymbol("RES"))));

    private static final SubLSymbol WORD_PROXY_LINKS_METHOD = makeSymbol("WORD-PROXY-LINKS-METHOD");

    static private final SubLList $list70 = list(makeKeyword("PUBLIC"));

    static private final SubLList $list71 = list(list(RET, makeSymbol("POS")));

    static private final SubLSymbol $sym72$OUTER_CATCH_FOR_WORD_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-WORD-PROXY-METHOD");

    private static final SubLSymbol WORD_PROXY_POS_METHOD = makeSymbol("WORD-PROXY-POS-METHOD");

    static private final SubLList $list74 = list(list(RET, makeSymbol("FORM")));

    static private final SubLSymbol $sym75$OUTER_CATCH_FOR_WORD_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-WORD-PROXY-METHOD");

    private static final SubLSymbol WORD_PROXY_FORM_METHOD = makeSymbol("WORD-PROXY-FORM-METHOD");

    static private final SubLList $list77 = list(makeSymbol("OBJECT"));

    static private final SubLList $list78 = list(list(RET, list(makeSymbol("CAND"), list(makeSymbol("WORD-PROXY-P"), makeSymbol("OBJECT")), list(EQUAL, makeSymbol("FORM"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("FORM")))), list(EQUAL, makeSymbol("POS"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("POS")))))));

    static private final SubLSymbol $sym79$OUTER_CATCH_FOR_WORD_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-WORD-PROXY-METHOD");

    private static final SubLSymbol WORD_PROXY_EQUAL_METHOD = makeSymbol("WORD-PROXY-EQUAL-METHOD");

    private static final SubLSymbol SYNSET_PROXY = makeSymbol("SYNSET-PROXY");

    static private final SubLList $list82 = list(new SubLObject[]{ list(makeSymbol("POS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("OFFSET"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("GLOSS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("OFFSET"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GLOSS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), EQUAL, list(makeSymbol("OBJECT")), makeKeyword("PUBLIC")) });

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SYNSET_PROXY_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SYNSET-PROXY-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SYNSET_PROXY_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SYNSET-PROXY-INSTANCE");

    static private final SubLList $list87 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("WRITE-STRING"), makeString("#<SYNSET-PROXY "), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeSymbol("POS"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(" "), makeSymbol("STREAM")), list(makeSymbol("PRINC"), makeSymbol("OFFSET"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(">"), makeSymbol("STREAM")), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym88$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SYNSET-PROXY-METHOD");

    static private final SubLString $str89$__SYNSET_PROXY_ = makeString("#<SYNSET-PROXY ");

    static private final SubLString $$$_ = makeString(" ");

    private static final SubLSymbol SYNSET_PROXY_PRINT_METHOD = makeSymbol("SYNSET-PROXY-PRINT-METHOD");

    static private final SubLList $list92 = list(list(RET, makeString("synset")));

    static private final SubLString $$$synset = makeString("synset");

    private static final SubLSymbol SYNSET_PROXY_WN_CLASS_METHOD = makeSymbol("SYNSET-PROXY-WN-CLASS-METHOD");

    static private final SubLList $list95 = list(list(RET, list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("pos"), makeSymbol("POS")), list(makeSymbol("BQ-LIST"), makeString("offset"), makeSymbol("OFFSET")))));

    static private final SubLSymbol $sym96$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SYNSET-PROXY-METHOD");

    static private final SubLString $$$offset = makeString("offset");

    private static final SubLSymbol SYNSET_PROXY_RELATIVE_KEY_METHOD = makeSymbol("SYNSET-PROXY-RELATIVE-KEY-METHOD");

    static private final SubLSymbol $sym99$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SYNSET-PROXY-METHOD");

    private static final SubLSymbol SYNSET_PROXY_POS_METHOD = makeSymbol("SYNSET-PROXY-POS-METHOD");

    static private final SubLList $list101 = list(list(RET, makeSymbol("OFFSET")));

    static private final SubLSymbol $sym102$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SYNSET-PROXY-METHOD");

    private static final SubLSymbol SYNSET_PROXY_OFFSET_METHOD = makeSymbol("SYNSET-PROXY-OFFSET-METHOD");

    static private final SubLList $list104 = list(list(RET, makeSymbol("GLOSS")));

    static private final SubLSymbol $sym105$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SYNSET-PROXY-METHOD");

    private static final SubLSymbol SYNSET_PROXY_GLOSS_METHOD = makeSymbol("SYNSET-PROXY-GLOSS-METHOD");

    static private final SubLList $list107 = list(list(RET, list(makeSymbol("CAND"), list(makeSymbol("SYNSET-PROXY-P"), makeSymbol("OBJECT")), list(makeSymbol("="), makeSymbol("OFFSET"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("OFFSET")))), list(EQUAL, makeSymbol("POS"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("POS")))))));

    static private final SubLSymbol $sym108$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SYNSET-PROXY-METHOD");

    private static final SubLSymbol SYNSET_PROXY_EQUAL_METHOD = makeSymbol("SYNSET-PROXY-EQUAL-METHOD");

    private static final SubLSymbol SENSE_PROXY = makeSymbol("SENSE-PROXY");

    static private final SubLList $list111 = list(new SubLObject[]{ list(makeSymbol("SYNSET"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("FORM"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FORM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT"), list(makeSymbol("STREAM"), makeSymbol("DEPTH")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("WORD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NUM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), EQUAL, list(makeSymbol("OBJECT")), makeKeyword("PUBLIC")) });

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SENSE_PROXY_CLASS = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SENSE-PROXY-CLASS");

    private static final SubLSymbol SUBLOOP_RESERVED_INITIALIZE_SENSE_PROXY_INSTANCE = makeSymbol("SUBLOOP-RESERVED-INITIALIZE-SENSE-PROXY-INSTANCE");

    private static final SubLSymbol SENSE_PROXY_P = makeSymbol("SENSE-PROXY-P");

    static private final SubLList $list115 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("WRITE-STRING"), makeString("#<SENSE-PROXY "), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FORM"))), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(" "), makeSymbol("STREAM")), list(makeSymbol("PRINC"), makeSymbol("SYNSET"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(">"), makeSymbol("STREAM")), list(RET, makeSymbol("SELF")));

    static private final SubLSymbol $sym116$OUTER_CATCH_FOR_SENSE_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SENSE-PROXY-METHOD");

    static private final SubLString $str117$__SENSE_PROXY_ = makeString("#<SENSE-PROXY ");

    private static final SubLSymbol SENSE_PROXY_PRINT_METHOD = makeSymbol("SENSE-PROXY-PRINT-METHOD");

    static private final SubLList $list119 = list(list(RET, makeString("sense")));

    static private final SubLString $$$sense = makeString("sense");

    private static final SubLSymbol SENSE_PROXY_WN_CLASS_METHOD = makeSymbol("SENSE-PROXY-WN-CLASS-METHOD");

    static private final SubLList $list122 = list(list(RET, list(makeSymbol("CONS"), list(makeSymbol("BQ-LIST"), makeString("form"), makeSymbol("FORM")), list(makeSymbol("FIM"), makeSymbol("SYNSET"), list(QUOTE, makeSymbol("RELATIVE-KEY"))))));

    static private final SubLSymbol $sym123$OUTER_CATCH_FOR_SENSE_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SENSE-PROXY-METHOD");

    private static final SubLSymbol SENSE_PROXY_RELATIVE_KEY_METHOD = makeSymbol("SENSE-PROXY-RELATIVE-KEY-METHOD");

    static private final SubLList $list125 = list(list(RET, list(makeSymbol("LIST"), makeSymbol("SELF"))));

    private static final SubLSymbol SENSE_PROXY_SENSES_METHOD = makeSymbol("SENSE-PROXY-SENSES-METHOD");

    static private final SubLSymbol $sym127$OUTER_CATCH_FOR_SENSE_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SENSE-PROXY-METHOD");

    private static final SubLSymbol SENSE_PROXY_FORM_METHOD = makeSymbol("SENSE-PROXY-FORM-METHOD");

    static private final SubLList $list129 = list(list(RET, list(makeSymbol("FIM"), makeSymbol("SYNSET"), list(QUOTE, makeSymbol("POS")))));

    static private final SubLSymbol $sym130$OUTER_CATCH_FOR_SENSE_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SENSE-PROXY-METHOD");

    private static final SubLSymbol SENSE_PROXY_POS_METHOD = makeSymbol("SENSE-PROXY-POS-METHOD");

    static private final SubLList $list133 = list(list(RET, list(makeSymbol("NEW-WORD-PROXY"), makeSymbol("FORM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("POS"))))));

    static private final SubLSymbol $sym134$OUTER_CATCH_FOR_SENSE_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SENSE-PROXY-METHOD");

    private static final SubLSymbol SENSE_PROXY_WORD_METHOD = makeSymbol("SENSE-PROXY-WORD-METHOD");

    static private final SubLList $list137 = list(list(makeSymbol("CLET"), list(list(makeSymbol("I"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("SENSE"), list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("WORD"))), list(QUOTE, makeSymbol("SENSES")))), list(makeSymbol("CINC"), makeSymbol("I")), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, EQUAL), makeSymbol("SENSE")), list(RET, makeSymbol("I"))))));

    private static final SubLSymbol SENSE_PROXY_NUM_METHOD = makeSymbol("SENSE-PROXY-NUM-METHOD");

    static private final SubLList $list139 = list(list(RET, list(makeSymbol("CAND"), list(makeSymbol("SENSE-PROXY-P"), makeSymbol("OBJECT")), list(makeSymbol("FIM"), makeSymbol("SYNSET"), list(QUOTE, EQUAL), list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("SYNSETS"))))))));

    static private final SubLSymbol $sym140$OUTER_CATCH_FOR_SENSE_PROXY_METHOD = makeUninternedSymbol("OUTER-CATCH-FOR-SENSE-PROXY-METHOD");

    private static final SubLSymbol SENSE_PROXY_EQUAL_METHOD = makeSymbol("SENSE-PROXY-EQUAL-METHOD");

    static private final SubLList $list142 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(RET, makeSymbol("SELF")));

    private static final SubLSymbol SYNSET_PROXY_INITIALIZE_METHOD = makeSymbol("SYNSET-PROXY-INITIALIZE-METHOD");

    private static final SubLSymbol SENSE_PROXY_INITIALIZE_METHOD = makeSymbol("SENSE-PROXY-INITIALIZE-METHOD");

    private static final SubLSymbol WORD_PROXY_INITIALIZE_METHOD = makeSymbol("WORD-PROXY-INITIALIZE-METHOD");

    private static final SubLSymbol WORDNET_PROXY_INITIALIZE_METHOD = makeSymbol("WORDNET-PROXY-INITIALIZE-METHOD");

    static private final SubLString $$$gloss = makeString("gloss");

    static private final SubLString $$$adjective = makeString("adjective");

    static private final SubLString $$$noun = makeString("noun");

    static private final SubLString $$$verb = makeString("verb");

    static private final SubLString $$$adverb = makeString("adverb");

    static private final SubLString $$$antonym = makeString("antonym");

    static private final SubLString $$$hypernym = makeString("hypernym");

    static private final SubLString $$$hyponym = makeString("hyponym");

    static private final SubLString $$$attribute = makeString("attribute");

    static private final SubLString $$$also_see = makeString("also see");

    static private final SubLString $$$entailment = makeString("entailment");

    static private final SubLString $$$cause = makeString("cause");

    static private final SubLString $$$verb_group = makeString("verb group");

    static private final SubLString $$$member_meronym = makeString("member meronym");

    static private final SubLString $$$substance_meronym = makeString("substance meronym");

    static private final SubLString $$$part_meronym = makeString("part meronym");

    static private final SubLString $$$member_holonym = makeString("member holonym");

    static private final SubLString $$$substance_holonym = makeString("substance holonym");

    static private final SubLString $$$part_holonym = makeString("part holonym");

    static private final SubLString $$$similar = makeString("similar");

    static private final SubLString $$$participle_of = makeString("participle of");

    static private final SubLString $$$pertainym = makeString("pertainym");

    // Definitions
    public static final SubLObject default_initializer_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject v_class = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt0);
            v_class = current.first();
            current = current.rest();
            if (NIL == current) {
                return listS(DEF_INSTANCE_METHOD, listS(INITIALIZE, v_class, $list_alt3), $list_alt4);
            } else {
                cdestructuring_bind_error(datum, $list_alt0);
            }
        }
        return NIL;
    }

    // Definitions
    public static SubLObject default_initializer(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject v_class = NIL;
        destructuring_bind_must_consp(current, datum, $list0);
        v_class = current.first();
        current = current.rest();
        if (NIL == current) {
            return listS(DEF_INSTANCE_METHOD, listS(INITIALIZE, v_class, $list3), $list4);
        }
        cdestructuring_bind_error(datum, $list0);
        return NIL;
    }

    public static final SubLObject default_initializers_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject v_classes = current;
            SubLObject defs = NIL;
            SubLObject cdolist_list_var = v_classes;
            SubLObject v_class = NIL;
            for (v_class = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , v_class = cdolist_list_var.first()) {
                defs = cons(list(DEFAULT_INITIALIZER, v_class), defs);
            }
            return cons(PROGN, defs);
        }
    }

    public static SubLObject default_initializers(final SubLObject macroform, final SubLObject environment) {
        final SubLObject datum = macroform.rest();
        final SubLObject v_classes;
        final SubLObject current = v_classes = datum;
        SubLObject defs = NIL;
        SubLObject cdolist_list_var = v_classes;
        SubLObject v_class = NIL;
        v_class = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            defs = cons(list(DEFAULT_INITIALIZER, v_class), defs);
            cdolist_list_var = cdolist_list_var.rest();
            v_class = cdolist_list_var.first();
        } 
        return cons(PROGN, defs);
    }

    public static final SubLObject deflink_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject function = NIL;
            SubLObject link = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt7);
            function = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt7);
            link = current.first();
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_PUBLIC, function, $list_alt9, list(RET, list(FIM, PROXY, $list_alt13, link)));
            } else {
                cdestructuring_bind_error(datum, $list_alt7);
            }
        }
        return NIL;
    }

    public static SubLObject deflink(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject function = NIL;
        SubLObject link = NIL;
        destructuring_bind_must_consp(current, datum, $list7);
        function = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list7);
        link = current.first();
        current = current.rest();
        if (NIL == current) {
            return list(DEFINE_PUBLIC, function, $list9, list(RET, list(FIM, PROXY, $list13, link)));
        }
        cdestructuring_bind_error(datum, $list7);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_wordnet_proxy_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_wordnet_proxy_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_wordnet_proxy_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_wordnet_proxy_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        return NIL;
    }

    public static final SubLObject wordnet_proxy_p_alt(SubLObject v_wordnet_proxy) {
        return classes.subloop_instanceof_class(v_wordnet_proxy, WORDNET_PROXY);
    }

    public static SubLObject wordnet_proxy_p(final SubLObject v_wordnet_proxy) {
        return classes.subloop_instanceof_class(v_wordnet_proxy, WORDNET_PROXY);
    }

    public static final SubLObject wordnet_proxy_absolute_key_method_alt(SubLObject self) {
        return cons(list($$$class, methods.funcall_instance_method_with_0_args(self, WN_CLASS)), methods.funcall_instance_method_with_0_args(self, RELATIVE_KEY));
    }

    public static SubLObject wordnet_proxy_absolute_key_method(final SubLObject self) {
        return cons(list($$$class, methods.funcall_instance_method_with_0_args(self, WN_CLASS)), methods.funcall_instance_method_with_0_args(self, RELATIVE_KEY));
    }

    public static final SubLObject wordnet_proxy_senses_method_alt(SubLObject self) {
        return methods.funcall_instance_method_with_1_args(self, INVOKE, $$$getSenses);
    }

    public static SubLObject wordnet_proxy_senses_method(final SubLObject self) {
        return methods.funcall_instance_method_with_1_args(self, INVOKE, $$$getSenses);
    }

    public static final SubLObject wordnet_proxy_synsets_method_alt(SubLObject self) {
        {
            SubLObject synsets = NIL;
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, SENSES);
            SubLObject sense = NIL;
            for (sense = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sense = cdolist_list_var.first()) {
                synsets = cons(instances.get_slot(sense, SYNSET), synsets);
            }
            return synsets;
        }
    }

    public static SubLObject wordnet_proxy_synsets_method(final SubLObject self) {
        SubLObject synsets = NIL;
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, SENSES);
        SubLObject sense = NIL;
        sense = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            synsets = cons(instances.get_slot(sense, SYNSET), synsets);
            cdolist_list_var = cdolist_list_var.rest();
            sense = cdolist_list_var.first();
        } 
        return synsets;
    }

    public static final SubLObject wordnet_proxy_links_method_alt(SubLObject self, SubLObject type) {
        return methods.funcall_instance_method_with_2_args(self, INVOKE, $$$getPointerTargets, list(list($$$pointerType, type)));
    }

    public static SubLObject wordnet_proxy_links_method(final SubLObject self, final SubLObject type) {
        return methods.funcall_instance_method_with_2_args(self, INVOKE, $$$getPointerTargets, list(list($$$pointerType, type)));
    }

    public static final SubLObject wordnet_proxy_invoke_method_alt(SubLObject self, SubLObject method, SubLObject params) {
        if (params == UNPROVIDED) {
            params = NIL;
        }
        return eval(external_interfaces.invoke_wordnet(append(methods.funcall_instance_method_with_0_args(self, ABSOLUTE_KEY), list(list($$$method, method)), params), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject wordnet_proxy_invoke_method(final SubLObject self, final SubLObject method, SubLObject params) {
        if (params == UNPROVIDED) {
            params = NIL;
        }
        return eval(external_interfaces.invoke_wordnet(append(methods.funcall_instance_method_with_0_args(self, ABSOLUTE_KEY), list(list($$$method, method)), params), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject get_word_proxy_pos_alt(SubLObject word_proxy) {
        return classes.subloop_get_access_protected_instance_slot(word_proxy, TWO_INTEGER, POS);
    }

    public static SubLObject get_word_proxy_pos(final SubLObject word_proxy) {
        return classes.subloop_get_access_protected_instance_slot(word_proxy, TWO_INTEGER, POS);
    }

    public static final SubLObject set_word_proxy_pos_alt(SubLObject word_proxy, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(word_proxy, value, TWO_INTEGER, POS);
    }

    public static SubLObject set_word_proxy_pos(final SubLObject word_proxy, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(word_proxy, value, TWO_INTEGER, POS);
    }

    public static final SubLObject get_word_proxy_form_alt(SubLObject word_proxy) {
        return classes.subloop_get_access_protected_instance_slot(word_proxy, ONE_INTEGER, FORM);
    }

    public static SubLObject get_word_proxy_form(final SubLObject word_proxy) {
        return classes.subloop_get_access_protected_instance_slot(word_proxy, ONE_INTEGER, FORM);
    }

    public static final SubLObject set_word_proxy_form_alt(SubLObject word_proxy, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(word_proxy, value, ONE_INTEGER, FORM);
    }

    public static SubLObject set_word_proxy_form(final SubLObject word_proxy, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(word_proxy, value, ONE_INTEGER, FORM);
    }

    public static final SubLObject subloop_reserved_initialize_word_proxy_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_word_proxy_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_word_proxy_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, WORD_PROXY, FORM, NIL);
        classes.subloop_initialize_slot(new_instance, WORD_PROXY, POS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_word_proxy_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, WORD_PROXY, FORM, NIL);
        classes.subloop_initialize_slot(new_instance, WORD_PROXY, POS, NIL);
        return NIL;
    }

    public static final SubLObject word_proxy_p_alt(SubLObject word_proxy) {
        return classes.subloop_instanceof_class(word_proxy, WORD_PROXY);
    }

    public static SubLObject word_proxy_p(final SubLObject word_proxy) {
        return classes.subloop_instanceof_class(word_proxy, WORD_PROXY);
    }

    public static final SubLObject word_proxy_wn_class_method_alt(SubLObject self) {
        return $$$word;
    }

    public static SubLObject word_proxy_wn_class_method(final SubLObject self) {
        return $$$word;
    }

    public static final SubLObject word_proxy_relative_key_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_word_proxy_method = NIL;
            SubLObject pos = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_word_proxy_pos(self);
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_word_proxy_form(self);
            try {
                try {
                    sublisp_throw($sym57$OUTER_CATCH_FOR_WORD_PROXY_METHOD, list(list($$$pos, pos), list($$$form, form)));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_pos(self, pos);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_form(self, form);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym57$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            }
            return catch_var_for_word_proxy_method;
        }
    }

    public static SubLObject word_proxy_relative_key_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_word_proxy_method = NIL;
        final SubLObject pos = get_word_proxy_pos(self);
        final SubLObject form = get_word_proxy_form(self);
        try {
            thread.throwStack.push($sym57$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            try {
                sublisp_throw($sym57$OUTER_CATCH_FOR_WORD_PROXY_METHOD, list(list($$$pos, pos), list($$$form, form)));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_word_proxy_pos(self, pos);
                    set_word_proxy_form(self, form);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym57$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_word_proxy_method;
    }

    public static final SubLObject word_proxy_print_method_alt(SubLObject self, SubLObject stream, SubLObject depth) {
        {
            SubLObject catch_var_for_word_proxy_method = NIL;
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_word_proxy_form(self);
            try {
                try {
                    write_string($str_alt65$__WORD_PROXY_, stream, UNPROVIDED, UNPROVIDED);
                    write_string(form, stream, UNPROVIDED, UNPROVIDED);
                    write_string($str_alt66$_, stream, UNPROVIDED, UNPROVIDED);
                    sublisp_throw($sym64$OUTER_CATCH_FOR_WORD_PROXY_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_form(self, form);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym64$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            }
            return catch_var_for_word_proxy_method;
        }
    }

    public static SubLObject word_proxy_print_method(final SubLObject self, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_word_proxy_method = NIL;
        final SubLObject form = get_word_proxy_form(self);
        try {
            thread.throwStack.push($sym64$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            try {
                write_string($str65$__WORD_PROXY_, stream, UNPROVIDED, UNPROVIDED);
                write_string(form, stream, UNPROVIDED, UNPROVIDED);
                write_string($str66$_, stream, UNPROVIDED, UNPROVIDED);
                sublisp_throw($sym64$OUTER_CATCH_FOR_WORD_PROXY_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_word_proxy_form(self, form);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym64$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_word_proxy_method;
    }

    public static final SubLObject word_proxy_links_method_alt(SubLObject self, SubLObject type) {
        {
            SubLObject res = NIL;
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, SENSES);
            SubLObject sense = NIL;
            for (sense = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sense = cdolist_list_var.first()) {
                res = append(methods.funcall_instance_method_with_1_args(sense, LINKS, type), res);
            }
            return res;
        }
    }

    public static SubLObject word_proxy_links_method(final SubLObject self, final SubLObject type) {
        SubLObject res = NIL;
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(self, SENSES);
        SubLObject sense = NIL;
        sense = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            res = append(methods.funcall_instance_method_with_1_args(sense, LINKS, type), res);
            cdolist_list_var = cdolist_list_var.rest();
            sense = cdolist_list_var.first();
        } 
        return res;
    }

    public static final SubLObject word_proxy_pos_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_word_proxy_method = NIL;
            SubLObject pos = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_word_proxy_pos(self);
            try {
                try {
                    sublisp_throw($sym72$OUTER_CATCH_FOR_WORD_PROXY_METHOD, pos);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_pos(self, pos);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym72$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            }
            return catch_var_for_word_proxy_method;
        }
    }

    public static SubLObject word_proxy_pos_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_word_proxy_method = NIL;
        final SubLObject pos = get_word_proxy_pos(self);
        try {
            thread.throwStack.push($sym72$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            try {
                sublisp_throw($sym72$OUTER_CATCH_FOR_WORD_PROXY_METHOD, pos);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_word_proxy_pos(self, pos);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym72$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_word_proxy_method;
    }

    public static final SubLObject word_proxy_form_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_word_proxy_method = NIL;
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_word_proxy_form(self);
            try {
                try {
                    sublisp_throw($sym75$OUTER_CATCH_FOR_WORD_PROXY_METHOD, form);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_form(self, form);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym75$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            }
            return catch_var_for_word_proxy_method;
        }
    }

    public static SubLObject word_proxy_form_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_word_proxy_method = NIL;
        final SubLObject form = get_word_proxy_form(self);
        try {
            thread.throwStack.push($sym75$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            try {
                sublisp_throw($sym75$OUTER_CATCH_FOR_WORD_PROXY_METHOD, form);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_word_proxy_form(self, form);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym75$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_word_proxy_method;
    }

    public static final SubLObject word_proxy_equal_method_alt(SubLObject self, SubLObject v_object) {
        {
            SubLObject catch_var_for_word_proxy_method = NIL;
            SubLObject pos = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_word_proxy_pos(self);
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_word_proxy_form(self);
            try {
                try {
                    sublisp_throw($sym79$OUTER_CATCH_FOR_WORD_PROXY_METHOD, makeBoolean(((NIL != com.cyc.cycjava.cycl.quirk.wordnet_proxy.word_proxy_p(v_object)) && form.equal(methods.funcall_instance_method_with_0_args(v_object, FORM))) && pos.equal(methods.funcall_instance_method_with_0_args(v_object, POS))));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_pos(self, pos);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_form(self, form);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym79$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            }
            return catch_var_for_word_proxy_method;
        }
    }

    public static SubLObject word_proxy_equal_method(final SubLObject self, final SubLObject v_object) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_word_proxy_method = NIL;
        final SubLObject pos = get_word_proxy_pos(self);
        final SubLObject form = get_word_proxy_form(self);
        try {
            thread.throwStack.push($sym79$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
            try {
                sublisp_throw($sym79$OUTER_CATCH_FOR_WORD_PROXY_METHOD, makeBoolean(((NIL != word_proxy_p(v_object)) && form.equal(methods.funcall_instance_method_with_0_args(v_object, FORM))) && pos.equal(methods.funcall_instance_method_with_0_args(v_object, POS))));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_word_proxy_pos(self, pos);
                    set_word_proxy_form(self, form);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_word_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym79$OUTER_CATCH_FOR_WORD_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_word_proxy_method;
    }

    public static final SubLObject get_synset_proxy_gloss_alt(SubLObject synset_proxy) {
        return classes.subloop_get_access_protected_instance_slot(synset_proxy, THREE_INTEGER, GLOSS);
    }

    public static SubLObject get_synset_proxy_gloss(final SubLObject synset_proxy) {
        return classes.subloop_get_access_protected_instance_slot(synset_proxy, THREE_INTEGER, GLOSS);
    }

    public static final SubLObject set_synset_proxy_gloss_alt(SubLObject synset_proxy, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(synset_proxy, value, THREE_INTEGER, GLOSS);
    }

    public static SubLObject set_synset_proxy_gloss(final SubLObject synset_proxy, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(synset_proxy, value, THREE_INTEGER, GLOSS);
    }

    public static final SubLObject get_synset_proxy_offset_alt(SubLObject synset_proxy) {
        return classes.subloop_get_access_protected_instance_slot(synset_proxy, TWO_INTEGER, OFFSET);
    }

    public static SubLObject get_synset_proxy_offset(final SubLObject synset_proxy) {
        return classes.subloop_get_access_protected_instance_slot(synset_proxy, TWO_INTEGER, OFFSET);
    }

    public static final SubLObject set_synset_proxy_offset_alt(SubLObject synset_proxy, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(synset_proxy, value, TWO_INTEGER, OFFSET);
    }

    public static SubLObject set_synset_proxy_offset(final SubLObject synset_proxy, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(synset_proxy, value, TWO_INTEGER, OFFSET);
    }

    public static final SubLObject get_synset_proxy_pos_alt(SubLObject synset_proxy) {
        return classes.subloop_get_access_protected_instance_slot(synset_proxy, ONE_INTEGER, POS);
    }

    public static SubLObject get_synset_proxy_pos(final SubLObject synset_proxy) {
        return classes.subloop_get_access_protected_instance_slot(synset_proxy, ONE_INTEGER, POS);
    }

    public static final SubLObject set_synset_proxy_pos_alt(SubLObject synset_proxy, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(synset_proxy, value, ONE_INTEGER, POS);
    }

    public static SubLObject set_synset_proxy_pos(final SubLObject synset_proxy, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(synset_proxy, value, ONE_INTEGER, POS);
    }

    public static final SubLObject subloop_reserved_initialize_synset_proxy_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_synset_proxy_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_synset_proxy_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SYNSET_PROXY, POS, NIL);
        classes.subloop_initialize_slot(new_instance, SYNSET_PROXY, OFFSET, NIL);
        classes.subloop_initialize_slot(new_instance, SYNSET_PROXY, GLOSS, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_synset_proxy_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SYNSET_PROXY, POS, NIL);
        classes.subloop_initialize_slot(new_instance, SYNSET_PROXY, OFFSET, NIL);
        classes.subloop_initialize_slot(new_instance, SYNSET_PROXY, GLOSS, NIL);
        return NIL;
    }

    public static final SubLObject synset_proxy_p_alt(SubLObject synset_proxy) {
        return classes.subloop_instanceof_class(synset_proxy, SYNSET_PROXY);
    }

    public static SubLObject synset_proxy_p(final SubLObject synset_proxy) {
        return classes.subloop_instanceof_class(synset_proxy, SYNSET_PROXY);
    }

    public static final SubLObject synset_proxy_print_method_alt(SubLObject self, SubLObject stream, SubLObject depth) {
        {
            SubLObject catch_var_for_synset_proxy_method = NIL;
            SubLObject offset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_offset(self);
            SubLObject pos = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_pos(self);
            try {
                try {
                    write_string($str_alt89$__SYNSET_PROXY_, stream, UNPROVIDED, UNPROVIDED);
                    write_string(pos, stream, UNPROVIDED, UNPROVIDED);
                    write_string($str_alt90$_, stream, UNPROVIDED, UNPROVIDED);
                    princ(offset, stream);
                    write_string($str_alt66$_, stream, UNPROVIDED, UNPROVIDED);
                    sublisp_throw($sym88$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_offset(self, offset);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_pos(self, pos);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym88$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            }
            return catch_var_for_synset_proxy_method;
        }
    }

    public static SubLObject synset_proxy_print_method(final SubLObject self, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_synset_proxy_method = NIL;
        final SubLObject offset = get_synset_proxy_offset(self);
        final SubLObject pos = get_synset_proxy_pos(self);
        try {
            thread.throwStack.push($sym88$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            try {
                write_string($str89$__SYNSET_PROXY_, stream, UNPROVIDED, UNPROVIDED);
                write_string(pos, stream, UNPROVIDED, UNPROVIDED);
                write_string($$$_, stream, UNPROVIDED, UNPROVIDED);
                princ(offset, stream);
                write_string($str66$_, stream, UNPROVIDED, UNPROVIDED);
                sublisp_throw($sym88$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_synset_proxy_offset(self, offset);
                    set_synset_proxy_pos(self, pos);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym88$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_synset_proxy_method;
    }

    public static final SubLObject synset_proxy_wn_class_method_alt(SubLObject self) {
        return $$$synset;
    }

    public static SubLObject synset_proxy_wn_class_method(final SubLObject self) {
        return $$$synset;
    }

    public static final SubLObject synset_proxy_relative_key_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_synset_proxy_method = NIL;
            SubLObject offset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_offset(self);
            SubLObject pos = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_pos(self);
            try {
                try {
                    sublisp_throw($sym96$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, list(list($$$pos, pos), list($$$offset, offset)));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_offset(self, offset);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_pos(self, pos);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym96$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            }
            return catch_var_for_synset_proxy_method;
        }
    }

    public static SubLObject synset_proxy_relative_key_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_synset_proxy_method = NIL;
        final SubLObject offset = get_synset_proxy_offset(self);
        final SubLObject pos = get_synset_proxy_pos(self);
        try {
            thread.throwStack.push($sym96$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            try {
                sublisp_throw($sym96$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, list(list($$$pos, pos), list($$$offset, offset)));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_synset_proxy_offset(self, offset);
                    set_synset_proxy_pos(self, pos);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym96$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_synset_proxy_method;
    }

    public static final SubLObject synset_proxy_pos_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_synset_proxy_method = NIL;
            SubLObject pos = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_pos(self);
            try {
                try {
                    sublisp_throw($sym99$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, pos);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_pos(self, pos);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym99$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            }
            return catch_var_for_synset_proxy_method;
        }
    }

    public static SubLObject synset_proxy_pos_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_synset_proxy_method = NIL;
        final SubLObject pos = get_synset_proxy_pos(self);
        try {
            thread.throwStack.push($sym99$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            try {
                sublisp_throw($sym99$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, pos);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_synset_proxy_pos(self, pos);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym99$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_synset_proxy_method;
    }

    public static final SubLObject synset_proxy_offset_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_synset_proxy_method = NIL;
            SubLObject offset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_offset(self);
            try {
                try {
                    sublisp_throw($sym102$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, offset);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_offset(self, offset);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym102$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            }
            return catch_var_for_synset_proxy_method;
        }
    }

    public static SubLObject synset_proxy_offset_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_synset_proxy_method = NIL;
        final SubLObject offset = get_synset_proxy_offset(self);
        try {
            thread.throwStack.push($sym102$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            try {
                sublisp_throw($sym102$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, offset);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_synset_proxy_offset(self, offset);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym102$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_synset_proxy_method;
    }

    public static final SubLObject synset_proxy_gloss_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_synset_proxy_method = NIL;
            SubLObject gloss = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_gloss(self);
            try {
                try {
                    sublisp_throw($sym105$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, gloss);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_gloss(self, gloss);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym105$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            }
            return catch_var_for_synset_proxy_method;
        }
    }

    public static SubLObject synset_proxy_gloss_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_synset_proxy_method = NIL;
        final SubLObject gloss = get_synset_proxy_gloss(self);
        try {
            thread.throwStack.push($sym105$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            try {
                sublisp_throw($sym105$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, gloss);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_synset_proxy_gloss(self, gloss);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym105$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_synset_proxy_method;
    }

    public static final SubLObject synset_proxy_equal_method_alt(SubLObject self, SubLObject v_object) {
        {
            SubLObject catch_var_for_synset_proxy_method = NIL;
            SubLObject offset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_offset(self);
            SubLObject pos = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_synset_proxy_pos(self);
            try {
                try {
                    sublisp_throw($sym108$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, makeBoolean(((NIL != com.cyc.cycjava.cycl.quirk.wordnet_proxy.synset_proxy_p(v_object)) && offset.numE(methods.funcall_instance_method_with_0_args(v_object, OFFSET))) && pos.equal(methods.funcall_instance_method_with_0_args(v_object, POS))));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_offset(self, offset);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_pos(self, pos);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym108$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            }
            return catch_var_for_synset_proxy_method;
        }
    }

    public static SubLObject synset_proxy_equal_method(final SubLObject self, final SubLObject v_object) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_synset_proxy_method = NIL;
        final SubLObject offset = get_synset_proxy_offset(self);
        final SubLObject pos = get_synset_proxy_pos(self);
        try {
            thread.throwStack.push($sym108$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
            try {
                sublisp_throw($sym108$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD, makeBoolean(((NIL != synset_proxy_p(v_object)) && offset.numE(methods.funcall_instance_method_with_0_args(v_object, OFFSET))) && pos.equal(methods.funcall_instance_method_with_0_args(v_object, POS))));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_synset_proxy_offset(self, offset);
                    set_synset_proxy_pos(self, pos);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_synset_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym108$OUTER_CATCH_FOR_SYNSET_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_synset_proxy_method;
    }

    public static final SubLObject get_sense_proxy_form_alt(SubLObject sense_proxy) {
        return classes.subloop_get_access_protected_instance_slot(sense_proxy, TWO_INTEGER, FORM);
    }

    public static SubLObject get_sense_proxy_form(final SubLObject sense_proxy) {
        return classes.subloop_get_access_protected_instance_slot(sense_proxy, TWO_INTEGER, FORM);
    }

    public static final SubLObject set_sense_proxy_form_alt(SubLObject sense_proxy, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(sense_proxy, value, TWO_INTEGER, FORM);
    }

    public static SubLObject set_sense_proxy_form(final SubLObject sense_proxy, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(sense_proxy, value, TWO_INTEGER, FORM);
    }

    public static final SubLObject get_sense_proxy_synset_alt(SubLObject sense_proxy) {
        return classes.subloop_get_access_protected_instance_slot(sense_proxy, ONE_INTEGER, SYNSET);
    }

    public static SubLObject get_sense_proxy_synset(final SubLObject sense_proxy) {
        return classes.subloop_get_access_protected_instance_slot(sense_proxy, ONE_INTEGER, SYNSET);
    }

    public static final SubLObject set_sense_proxy_synset_alt(SubLObject sense_proxy, SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(sense_proxy, value, ONE_INTEGER, SYNSET);
    }

    public static SubLObject set_sense_proxy_synset(final SubLObject sense_proxy, final SubLObject value) {
        return classes.subloop_set_access_protected_instance_slot(sense_proxy, value, ONE_INTEGER, SYNSET);
    }

    public static final SubLObject subloop_reserved_initialize_sense_proxy_class_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sense_proxy_class(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_COUNT, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject subloop_reserved_initialize_sense_proxy_instance_alt(SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SENSE_PROXY, SYNSET, NIL);
        classes.subloop_initialize_slot(new_instance, SENSE_PROXY, FORM, NIL);
        return NIL;
    }

    public static SubLObject subloop_reserved_initialize_sense_proxy_instance(final SubLObject new_instance) {
        classes.subloop_initialize_slot(new_instance, OBJECT, ISOLATED_P, NIL);
        classes.subloop_initialize_slot(new_instance, OBJECT, INSTANCE_NUMBER, NIL);
        classes.subloop_initialize_slot(new_instance, SENSE_PROXY, SYNSET, NIL);
        classes.subloop_initialize_slot(new_instance, SENSE_PROXY, FORM, NIL);
        return NIL;
    }

    public static final SubLObject sense_proxy_p_alt(SubLObject sense_proxy) {
        return classes.subloop_instanceof_class(sense_proxy, SENSE_PROXY);
    }

    public static SubLObject sense_proxy_p(final SubLObject sense_proxy) {
        return classes.subloop_instanceof_class(sense_proxy, SENSE_PROXY);
    }

    /**
     *
     *
     * @param SENSE1
    sense-proxy-p
     * 		
     * @param SENSE2
    sense-proxy-p
     * 		
     * @return boolean; t if SENSE1 is equal to SENSE2, nil otherwise
     */
    @LispMethod(comment = "@param SENSE1\nsense-proxy-p\r\n\t\t\r\n@param SENSE2\nsense-proxy-p\r\n\t\t\r\n@return boolean; t if SENSE1 is equal to SENSE2, nil otherwise")
    public static final SubLObject sense_proxy_equal_alt(SubLObject sense1, SubLObject sense2) {
        SubLTrampolineFile.checkType(sense1, SENSE_PROXY_P);
        return methods.funcall_instance_method_with_1_args(sense1, EQUAL, sense2);
    }

    /**
     *
     *
     * @param SENSE1
    sense-proxy-p
     * 		
     * @param SENSE2
    sense-proxy-p
     * 		
     * @return boolean; t if SENSE1 is equal to SENSE2, nil otherwise
     */
    @LispMethod(comment = "@param SENSE1\nsense-proxy-p\r\n\t\t\r\n@param SENSE2\nsense-proxy-p\r\n\t\t\r\n@return boolean; t if SENSE1 is equal to SENSE2, nil otherwise")
    public static SubLObject sense_proxy_equal(final SubLObject sense1, final SubLObject sense2) {
        assert NIL != sense_proxy_p(sense1) : "! wordnet_proxy.sense_proxy_p(sense1) " + ("wordnet_proxy.sense_proxy_p(sense1) " + "CommonSymbols.NIL != wordnet_proxy.sense_proxy_p(sense1) ") + sense1;
        return methods.funcall_instance_method_with_1_args(sense1, EQUAL, sense2);
    }

    public static final SubLObject sense_proxy_print_method_alt(SubLObject self, SubLObject stream, SubLObject depth) {
        {
            SubLObject catch_var_for_sense_proxy_method = NIL;
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_form(self);
            SubLObject synset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_synset(self);
            try {
                try {
                    write_string($str_alt117$__SENSE_PROXY_, stream, UNPROVIDED, UNPROVIDED);
                    write_string(methods.funcall_instance_method_with_0_args(self, FORM), stream, UNPROVIDED, UNPROVIDED);
                    write_string($str_alt90$_, stream, UNPROVIDED, UNPROVIDED);
                    princ(synset, stream);
                    write_string($str_alt66$_, stream, UNPROVIDED, UNPROVIDED);
                    sublisp_throw($sym116$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, self);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_form(self, form);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_synset(self, synset);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym116$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            }
            return catch_var_for_sense_proxy_method;
        }
    }

    public static SubLObject sense_proxy_print_method(final SubLObject self, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_sense_proxy_method = NIL;
        final SubLObject form = get_sense_proxy_form(self);
        final SubLObject synset = get_sense_proxy_synset(self);
        try {
            thread.throwStack.push($sym116$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            try {
                write_string($str117$__SENSE_PROXY_, stream, UNPROVIDED, UNPROVIDED);
                write_string(methods.funcall_instance_method_with_0_args(self, FORM), stream, UNPROVIDED, UNPROVIDED);
                write_string($$$_, stream, UNPROVIDED, UNPROVIDED);
                princ(synset, stream);
                write_string($str66$_, stream, UNPROVIDED, UNPROVIDED);
                sublisp_throw($sym116$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, self);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_sense_proxy_form(self, form);
                    set_sense_proxy_synset(self, synset);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym116$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_sense_proxy_method;
    }

    public static final SubLObject sense_proxy_wn_class_method_alt(SubLObject self) {
        return $$$sense;
    }

    public static SubLObject sense_proxy_wn_class_method(final SubLObject self) {
        return $$$sense;
    }

    public static final SubLObject sense_proxy_relative_key_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_sense_proxy_method = NIL;
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_form(self);
            SubLObject synset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_synset(self);
            try {
                try {
                    sublisp_throw($sym123$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, cons(list($$$form, form), methods.funcall_instance_method_with_0_args(synset, RELATIVE_KEY)));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_form(self, form);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_synset(self, synset);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym123$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            }
            return catch_var_for_sense_proxy_method;
        }
    }

    public static SubLObject sense_proxy_relative_key_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_sense_proxy_method = NIL;
        final SubLObject form = get_sense_proxy_form(self);
        final SubLObject synset = get_sense_proxy_synset(self);
        try {
            thread.throwStack.push($sym123$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            try {
                sublisp_throw($sym123$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, cons(list($$$form, form), methods.funcall_instance_method_with_0_args(synset, RELATIVE_KEY)));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_sense_proxy_form(self, form);
                    set_sense_proxy_synset(self, synset);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym123$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_sense_proxy_method;
    }

    public static final SubLObject sense_proxy_senses_method_alt(SubLObject self) {
        return list(self);
    }

    public static SubLObject sense_proxy_senses_method(final SubLObject self) {
        return list(self);
    }

    public static final SubLObject sense_proxy_form_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_sense_proxy_method = NIL;
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_form(self);
            try {
                try {
                    sublisp_throw($sym127$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, form);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_form(self, form);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym127$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            }
            return catch_var_for_sense_proxy_method;
        }
    }

    public static SubLObject sense_proxy_form_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_sense_proxy_method = NIL;
        final SubLObject form = get_sense_proxy_form(self);
        try {
            thread.throwStack.push($sym127$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            try {
                sublisp_throw($sym127$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, form);
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_sense_proxy_form(self, form);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym127$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_sense_proxy_method;
    }

    public static final SubLObject sense_proxy_pos_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_sense_proxy_method = NIL;
            SubLObject synset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_synset(self);
            try {
                try {
                    sublisp_throw($sym130$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, methods.funcall_instance_method_with_0_args(synset, POS));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_synset(self, synset);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym130$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            }
            return catch_var_for_sense_proxy_method;
        }
    }

    public static SubLObject sense_proxy_pos_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_sense_proxy_method = NIL;
        final SubLObject synset = get_sense_proxy_synset(self);
        try {
            thread.throwStack.push($sym130$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            try {
                sublisp_throw($sym130$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, methods.funcall_instance_method_with_0_args(synset, POS));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_sense_proxy_synset(self, synset);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym130$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_sense_proxy_method;
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list_alt0 = list(makeSymbol("CLASS"));

    static private final SubLList $list_alt3 = list(makeKeyword("PROTECTED"));

    static private final SubLList $list_alt4 = list(NIL, list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(RET, makeSymbol("SELF")));

    static private final SubLList $list_alt7 = list(makeSymbol("FUNCTION"), makeSymbol("LINK"));

    public static final SubLObject sense_proxy_word_method_alt(SubLObject self) {
        {
            SubLObject catch_var_for_sense_proxy_method = NIL;
            SubLObject form = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_form(self);
            try {
                try {
                    sublisp_throw($sym134$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, com.cyc.cycjava.cycl.quirk.wordnet_proxy.new_word_proxy(form, methods.funcall_instance_method_with_0_args(self, POS)));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_form(self, form);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym134$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            }
            return catch_var_for_sense_proxy_method;
        }
    }

    public static SubLObject sense_proxy_word_method(final SubLObject self) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_sense_proxy_method = NIL;
        final SubLObject form = get_sense_proxy_form(self);
        try {
            thread.throwStack.push($sym134$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            try {
                sublisp_throw($sym134$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, new_word_proxy(form, methods.funcall_instance_method_with_0_args(self, POS)));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_sense_proxy_form(self, form);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym134$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_sense_proxy_method;
    }

    static private final SubLList $list_alt9 = list(makeSymbol("PROXY"));

    static private final SubLList $list_alt13 = list(QUOTE, makeSymbol("LINKS"));

    static private final SubLList $list_alt16 = list(new SubLObject[]{ list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("ABSOLUTE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("WN-CLASS"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("LINKS"), list(makeSymbol("TYPE")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INVOKE"), list(makeSymbol("METHOD"), makeSymbol("&OPTIONAL"), makeSymbol("PARAMS")), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SENSES"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("SYNSETS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")) });

    public static final SubLObject sense_proxy_num_method_alt(SubLObject self) {
        {
            SubLObject i = ZERO_INTEGER;
            SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_0_args(self, WORD), SENSES);
            SubLObject sense = NIL;
            for (sense = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sense = cdolist_list_var.first()) {
                i = add(i, ONE_INTEGER);
                if (NIL != methods.funcall_instance_method_with_1_args(self, EQUAL, sense)) {
                    return i;
                }
            }
        }
        return NIL;
    }

    public static SubLObject sense_proxy_num_method(final SubLObject self) {
        SubLObject i = ZERO_INTEGER;
        SubLObject cdolist_list_var = methods.funcall_instance_method_with_0_args(methods.funcall_instance_method_with_0_args(self, WORD), SENSES);
        SubLObject sense = NIL;
        sense = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            i = add(i, ONE_INTEGER);
            if (NIL != methods.funcall_instance_method_with_1_args(self, EQUAL, sense)) {
                return i;
            }
            cdolist_list_var = cdolist_list_var.rest();
            sense = cdolist_list_var.first();
        } 
        return NIL;
    }

    static private final SubLList $list_alt23 = list(list(RET, list(makeSymbol("CONS"), list(makeSymbol("BQ-LIST"), makeString("class"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("WN-CLASS")))), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("RELATIVE-KEY"))))));

    public static final SubLObject sense_proxy_equal_method_alt(SubLObject self, SubLObject v_object) {
        {
            SubLObject catch_var_for_sense_proxy_method = NIL;
            SubLObject synset = com.cyc.cycjava.cycl.quirk.wordnet_proxy.get_sense_proxy_synset(self);
            try {
                try {
                    sublisp_throw($sym140$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, makeBoolean((NIL != com.cyc.cycjava.cycl.quirk.wordnet_proxy.sense_proxy_p(v_object)) && (NIL != methods.funcall_instance_method_with_1_args(synset, EQUAL, methods.funcall_instance_method_with_0_args(v_object, SYNSETS).first()))));
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_synset(self, synset);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
            } catch (Throwable ccatch_env_var) {
                catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym140$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            }
            return catch_var_for_sense_proxy_method;
        }
    }

    public static SubLObject sense_proxy_equal_method(final SubLObject self, final SubLObject v_object) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject catch_var_for_sense_proxy_method = NIL;
        final SubLObject synset = get_sense_proxy_synset(self);
        try {
            thread.throwStack.push($sym140$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
            try {
                sublisp_throw($sym140$OUTER_CATCH_FOR_SENSE_PROXY_METHOD, makeBoolean((NIL != sense_proxy_p(v_object)) && (NIL != methods.funcall_instance_method_with_1_args(synset, EQUAL, methods.funcall_instance_method_with_0_args(v_object, SYNSETS).first()))));
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    set_sense_proxy_synset(self, synset);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
        } catch (final Throwable ccatch_env_var) {
            catch_var_for_sense_proxy_method = Errors.handleThrowable(ccatch_env_var, $sym140$OUTER_CATCH_FOR_SENSE_PROXY_METHOD);
        } finally {
            thread.throwStack.pop();
        }
        return catch_var_for_sense_proxy_method;
    }

    static private final SubLList $list_alt29 = list(list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVOKE")), makeString("getSenses"))));

    static private final SubLList $list_alt34 = list(list(makeSymbol("CLET"), list(list(makeSymbol("SYNSETS"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("SENSE"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("SENSES")))), list(makeSymbol("CPUSH"), list(makeSymbol("GET-SLOT"), makeSymbol("SENSE"), list(QUOTE, makeSymbol("SYNSET"))), makeSymbol("SYNSETS"))), list(RET, makeSymbol("SYNSETS"))));

    static private final SubLList $list_alt38 = list(makeSymbol("TYPE"));

    public static final SubLObject synset_proxy_initialize_method_alt(SubLObject self) {
        com.cyc.cycjava.cycl.quirk.wordnet_proxy.wordnet_proxy_initialize_method(self);
        return self;
    }

    public static SubLObject synset_proxy_initialize_method(final SubLObject self) {
        wordnet_proxy_initialize_method(self);
        return self;
    }

    static private final SubLList $list_alt39 = list(list(RET, list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("INVOKE")), makeString("getPointerTargets"), list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("pointerType"), makeSymbol("TYPE"))))));

    public static final SubLObject sense_proxy_initialize_method_alt(SubLObject self) {
        com.cyc.cycjava.cycl.quirk.wordnet_proxy.wordnet_proxy_initialize_method(self);
        return self;
    }

    public static SubLObject sense_proxy_initialize_method(final SubLObject self) {
        wordnet_proxy_initialize_method(self);
        return self;
    }

    public static final SubLObject word_proxy_initialize_method_alt(SubLObject self) {
        com.cyc.cycjava.cycl.quirk.wordnet_proxy.wordnet_proxy_initialize_method(self);
        return self;
    }

    public static SubLObject word_proxy_initialize_method(final SubLObject self) {
        wordnet_proxy_initialize_method(self);
        return self;
    }

    public static final SubLObject wordnet_proxy_initialize_method_alt(SubLObject self) {
        object.object_initialize_method(self);
        return self;
    }

    public static SubLObject wordnet_proxy_initialize_method(final SubLObject self) {
        object.object_initialize_method(self);
        return self;
    }

    static private final SubLList $list_alt43 = list(makeSymbol("METHOD"), makeSymbol("&OPTIONAL"), makeSymbol("PARAMS"));

    public static final SubLObject new_word_proxy_alt(SubLObject form, SubLObject pos) {
        {
            SubLObject proxy = object.new_class_instance(WORD_PROXY);
            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_form(proxy, form);
            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_word_proxy_pos(proxy, pos);
            return proxy;
        }
    }

    public static SubLObject new_word_proxy(final SubLObject form, final SubLObject pos) {
        final SubLObject proxy = object.new_class_instance(WORD_PROXY);
        set_word_proxy_form(proxy, form);
        set_word_proxy_pos(proxy, pos);
        return proxy;
    }

    static private final SubLList $list_alt44 = list(list(RET, list(makeSymbol("EVAL"), list(makeSymbol("INVOKE-WORDNET"), list(makeSymbol("APPEND"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("ABSOLUTE-KEY"))), list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("method"), makeSymbol("METHOD"))), makeSymbol("PARAMS"))))));

    public static final SubLObject new_sense_proxy_alt(SubLObject synset, SubLObject form) {
        {
            SubLObject proxy = object.new_class_instance(SENSE_PROXY);
            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_synset(proxy, synset);
            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_sense_proxy_form(proxy, form);
            return proxy;
        }
    }

    public static SubLObject new_sense_proxy(final SubLObject synset, final SubLObject form) {
        final SubLObject proxy = object.new_class_instance(SENSE_PROXY);
        set_sense_proxy_synset(proxy, synset);
        set_sense_proxy_form(proxy, form);
        return proxy;
    }

    public static final SubLObject new_synset_proxy_alt(SubLObject pos, SubLObject offset, SubLObject gloss) {
        if (gloss == UNPROVIDED) {
            gloss = NIL;
        }
        {
            SubLObject proxy = object.new_class_instance(SYNSET_PROXY);
            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_pos(proxy, pos);
            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_offset(proxy, offset);
            if (NIL == gloss) {
                gloss = methods.funcall_instance_method_with_1_args(proxy, INVOKE, $$$gloss);
            }
            com.cyc.cycjava.cycl.quirk.wordnet_proxy.set_synset_proxy_gloss(proxy, gloss);
            return proxy;
        }
    }

    public static SubLObject new_synset_proxy(final SubLObject pos, final SubLObject offset, SubLObject gloss) {
        if (gloss == UNPROVIDED) {
            gloss = NIL;
        }
        final SubLObject proxy = object.new_class_instance(SYNSET_PROXY);
        set_synset_proxy_pos(proxy, pos);
        set_synset_proxy_offset(proxy, offset);
        if (NIL == gloss) {
            gloss = methods.funcall_instance_method_with_1_args(proxy, INVOKE, $$$gloss);
        }
        set_synset_proxy_gloss(proxy, gloss);
        return proxy;
    }

    static private final SubLList $list_alt48 = list(list(makeSymbol("FORM"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("POS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FORM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), EQUAL, list(makeSymbol("OBJECT")), makeKeyword("PUBLIC")));

    public static final SubLObject new_synset_alt(SubLObject pos, SubLObject offset) {
        return com.cyc.cycjava.cycl.quirk.wordnet_proxy.new_synset_proxy(pos, offset, UNPROVIDED);
    }

    public static SubLObject new_synset(final SubLObject pos, final SubLObject offset) {
        return new_synset_proxy(pos, offset, UNPROVIDED);
    }

    public static final SubLObject new_adjective_alt(SubLObject form) {
        return com.cyc.cycjava.cycl.quirk.wordnet_proxy.new_word_proxy(form, $$$adjective);
    }

    public static SubLObject new_adjective(final SubLObject form) {
        return new_word_proxy(form, $$$adjective);
    }

    public static final SubLObject new_noun_alt(SubLObject form) {
        return com.cyc.cycjava.cycl.quirk.wordnet_proxy.new_word_proxy(form, $$$noun);
    }

    public static SubLObject new_noun(final SubLObject form) {
        return new_word_proxy(form, $$$noun);
    }

    public static final SubLObject new_verb_alt(SubLObject form) {
        return com.cyc.cycjava.cycl.quirk.wordnet_proxy.new_word_proxy(form, $$$verb);
    }

    public static SubLObject new_verb(final SubLObject form) {
        return new_word_proxy(form, $$$verb);
    }

    public static final SubLObject new_adverb_alt(SubLObject form) {
        return com.cyc.cycjava.cycl.quirk.wordnet_proxy.new_word_proxy(form, $$$adverb);
    }

    public static SubLObject new_adverb(final SubLObject form) {
        return new_word_proxy(form, $$$adverb);
    }

    public static final SubLObject antonyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$antonym);
    }

    public static SubLObject antonyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$antonym);
    }

    static private final SubLList $list_alt53 = list(list(RET, makeString("word")));

    public static final SubLObject hypernyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$hypernym);
    }

    public static SubLObject hypernyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$hypernym);
    }

    static private final SubLList $list_alt56 = list(list(RET, list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("pos"), makeSymbol("POS")), list(makeSymbol("BQ-LIST"), makeString("form"), makeSymbol("FORM")))));

    public static final SubLObject hyponyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$hyponym);
    }

    public static SubLObject hyponyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$hyponym);
    }

    public static final SubLObject attributes_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$attribute);
    }

    public static SubLObject attributes(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$attribute);
    }

    public static final SubLObject also_see_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$also_see);
    }

    public static SubLObject also_see(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$also_see);
    }

    public static final SubLObject entailments_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$entailment);
    }

    public static SubLObject entailments(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$entailment);
    }

    static private final SubLList $list_alt62 = list(makeSymbol("STREAM"), makeSymbol("DEPTH"));

    public static final SubLObject causes_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$cause);
    }

    public static SubLObject causes(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$cause);
    }

    static private final SubLList $list_alt63 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("WRITE-STRING"), makeString("#<WORD-PROXY "), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeSymbol("FORM"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(">"), makeSymbol("STREAM")), list(RET, makeSymbol("SELF")));

    public static final SubLObject verb_groups_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$verb_group);
    }

    public static SubLObject verb_groups(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$verb_group);
    }

    public static final SubLObject member_meronyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$member_meronym);
    }

    public static SubLObject member_meronyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$member_meronym);
    }

    public static final SubLObject substance_meronyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$substance_meronym);
    }

    public static SubLObject substance_meronyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$substance_meronym);
    }

    static private final SubLString $str_alt65$__WORD_PROXY_ = makeString("#<WORD-PROXY ");

    static private final SubLString $str_alt66$_ = makeString(">");

    public static final SubLObject part_meronyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$part_meronym);
    }

    public static SubLObject part_meronyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$part_meronym);
    }

    static private final SubLList $list_alt68 = list(list(makeSymbol("CLET"), list(list(makeSymbol("RES"), NIL)), list(makeSymbol("CDOLIST"), list(makeSymbol("SENSE"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("SENSES")))), list(makeSymbol("CSETF"), makeSymbol("RES"), list(makeSymbol("APPEND"), list(makeSymbol("FIM"), makeSymbol("SENSE"), list(QUOTE, makeSymbol("LINKS")), makeSymbol("TYPE")), makeSymbol("RES")))), list(RET, makeSymbol("RES"))));

    public static final SubLObject member_holonyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$member_holonym);
    }

    public static SubLObject member_holonyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$member_holonym);
    }

    public static final SubLObject substance_holonyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$substance_holonym);
    }

    public static SubLObject substance_holonyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$substance_holonym);
    }

    public static final SubLObject part_holonyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$part_holonym);
    }

    public static SubLObject part_holonyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$part_holonym);
    }

    public static final SubLObject similar_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$similar);
    }

    public static SubLObject similar(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$similar);
    }

    static private final SubLList $list_alt70 = list(makeKeyword("PUBLIC"));

    public static final SubLObject participle_of_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$participle_of);
    }

    public static SubLObject participle_of(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$participle_of);
    }

    static private final SubLList $list_alt71 = list(list(RET, makeSymbol("POS")));

    public static final SubLObject pertainyms_alt(SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$pertainym);
    }

    public static SubLObject pertainyms(final SubLObject proxy) {
        return methods.funcall_instance_method_with_1_args(proxy, LINKS, $$$pertainym);
    }

    public static SubLObject declare_wordnet_proxy_file() {
        declareMacro("default_initializer", "DEFAULT-INITIALIZER");
        declareMacro("default_initializers", "DEFAULT-INITIALIZERS");
        declareMacro("deflink", "DEFLINK");
        declareFunction("subloop_reserved_initialize_wordnet_proxy_class", "SUBLOOP-RESERVED-INITIALIZE-WORDNET-PROXY-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_wordnet_proxy_instance", "SUBLOOP-RESERVED-INITIALIZE-WORDNET-PROXY-INSTANCE", 1, 0, false);
        declareFunction("wordnet_proxy_p", "WORDNET-PROXY-P", 1, 0, false);
        declareFunction("wordnet_proxy_absolute_key_method", "WORDNET-PROXY-ABSOLUTE-KEY-METHOD", 1, 0, false);
        declareFunction("wordnet_proxy_senses_method", "WORDNET-PROXY-SENSES-METHOD", 1, 0, false);
        declareFunction("wordnet_proxy_synsets_method", "WORDNET-PROXY-SYNSETS-METHOD", 1, 0, false);
        declareFunction("wordnet_proxy_links_method", "WORDNET-PROXY-LINKS-METHOD", 2, 0, false);
        declareFunction("wordnet_proxy_invoke_method", "WORDNET-PROXY-INVOKE-METHOD", 2, 1, false);
        declareFunction("get_word_proxy_pos", "GET-WORD-PROXY-POS", 1, 0, false);
        declareFunction("set_word_proxy_pos", "SET-WORD-PROXY-POS", 2, 0, false);
        declareFunction("get_word_proxy_form", "GET-WORD-PROXY-FORM", 1, 0, false);
        declareFunction("set_word_proxy_form", "SET-WORD-PROXY-FORM", 2, 0, false);
        declareFunction("subloop_reserved_initialize_word_proxy_class", "SUBLOOP-RESERVED-INITIALIZE-WORD-PROXY-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_word_proxy_instance", "SUBLOOP-RESERVED-INITIALIZE-WORD-PROXY-INSTANCE", 1, 0, false);
        declareFunction("word_proxy_p", "WORD-PROXY-P", 1, 0, false);
        declareFunction("word_proxy_wn_class_method", "WORD-PROXY-WN-CLASS-METHOD", 1, 0, false);
        declareFunction("word_proxy_relative_key_method", "WORD-PROXY-RELATIVE-KEY-METHOD", 1, 0, false);
        declareFunction("word_proxy_print_method", "WORD-PROXY-PRINT-METHOD", 3, 0, false);
        declareFunction("word_proxy_links_method", "WORD-PROXY-LINKS-METHOD", 2, 0, false);
        declareFunction("word_proxy_pos_method", "WORD-PROXY-POS-METHOD", 1, 0, false);
        declareFunction("word_proxy_form_method", "WORD-PROXY-FORM-METHOD", 1, 0, false);
        declareFunction("word_proxy_equal_method", "WORD-PROXY-EQUAL-METHOD", 2, 0, false);
        declareFunction("get_synset_proxy_gloss", "GET-SYNSET-PROXY-GLOSS", 1, 0, false);
        declareFunction("set_synset_proxy_gloss", "SET-SYNSET-PROXY-GLOSS", 2, 0, false);
        declareFunction("get_synset_proxy_offset", "GET-SYNSET-PROXY-OFFSET", 1, 0, false);
        declareFunction("set_synset_proxy_offset", "SET-SYNSET-PROXY-OFFSET", 2, 0, false);
        declareFunction("get_synset_proxy_pos", "GET-SYNSET-PROXY-POS", 1, 0, false);
        declareFunction("set_synset_proxy_pos", "SET-SYNSET-PROXY-POS", 2, 0, false);
        declareFunction("subloop_reserved_initialize_synset_proxy_class", "SUBLOOP-RESERVED-INITIALIZE-SYNSET-PROXY-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_synset_proxy_instance", "SUBLOOP-RESERVED-INITIALIZE-SYNSET-PROXY-INSTANCE", 1, 0, false);
        declareFunction("synset_proxy_p", "SYNSET-PROXY-P", 1, 0, false);
        declareFunction("synset_proxy_print_method", "SYNSET-PROXY-PRINT-METHOD", 3, 0, false);
        declareFunction("synset_proxy_wn_class_method", "SYNSET-PROXY-WN-CLASS-METHOD", 1, 0, false);
        declareFunction("synset_proxy_relative_key_method", "SYNSET-PROXY-RELATIVE-KEY-METHOD", 1, 0, false);
        declareFunction("synset_proxy_pos_method", "SYNSET-PROXY-POS-METHOD", 1, 0, false);
        declareFunction("synset_proxy_offset_method", "SYNSET-PROXY-OFFSET-METHOD", 1, 0, false);
        declareFunction("synset_proxy_gloss_method", "SYNSET-PROXY-GLOSS-METHOD", 1, 0, false);
        declareFunction("synset_proxy_equal_method", "SYNSET-PROXY-EQUAL-METHOD", 2, 0, false);
        declareFunction("get_sense_proxy_form", "GET-SENSE-PROXY-FORM", 1, 0, false);
        declareFunction("set_sense_proxy_form", "SET-SENSE-PROXY-FORM", 2, 0, false);
        declareFunction("get_sense_proxy_synset", "GET-SENSE-PROXY-SYNSET", 1, 0, false);
        declareFunction("set_sense_proxy_synset", "SET-SENSE-PROXY-SYNSET", 2, 0, false);
        declareFunction("subloop_reserved_initialize_sense_proxy_class", "SUBLOOP-RESERVED-INITIALIZE-SENSE-PROXY-CLASS", 1, 0, false);
        declareFunction("subloop_reserved_initialize_sense_proxy_instance", "SUBLOOP-RESERVED-INITIALIZE-SENSE-PROXY-INSTANCE", 1, 0, false);
        declareFunction("sense_proxy_p", "SENSE-PROXY-P", 1, 0, false);
        declareFunction("sense_proxy_equal", "SENSE-PROXY-EQUAL", 2, 0, false);
        declareFunction("sense_proxy_print_method", "SENSE-PROXY-PRINT-METHOD", 3, 0, false);
        declareFunction("sense_proxy_wn_class_method", "SENSE-PROXY-WN-CLASS-METHOD", 1, 0, false);
        declareFunction("sense_proxy_relative_key_method", "SENSE-PROXY-RELATIVE-KEY-METHOD", 1, 0, false);
        declareFunction("sense_proxy_senses_method", "SENSE-PROXY-SENSES-METHOD", 1, 0, false);
        declareFunction("sense_proxy_form_method", "SENSE-PROXY-FORM-METHOD", 1, 0, false);
        declareFunction("sense_proxy_pos_method", "SENSE-PROXY-POS-METHOD", 1, 0, false);
        declareFunction("sense_proxy_word_method", "SENSE-PROXY-WORD-METHOD", 1, 0, false);
        declareFunction("sense_proxy_num_method", "SENSE-PROXY-NUM-METHOD", 1, 0, false);
        declareFunction("sense_proxy_equal_method", "SENSE-PROXY-EQUAL-METHOD", 2, 0, false);
        declareFunction("synset_proxy_initialize_method", "SYNSET-PROXY-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("sense_proxy_initialize_method", "SENSE-PROXY-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("word_proxy_initialize_method", "WORD-PROXY-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("wordnet_proxy_initialize_method", "WORDNET-PROXY-INITIALIZE-METHOD", 1, 0, false);
        declareFunction("new_word_proxy", "NEW-WORD-PROXY", 2, 0, false);
        declareFunction("new_sense_proxy", "NEW-SENSE-PROXY", 2, 0, false);
        declareFunction("new_synset_proxy", "NEW-SYNSET-PROXY", 2, 1, false);
        declareFunction("new_synset", "NEW-SYNSET", 2, 0, false);
        declareFunction("new_adjective", "NEW-ADJECTIVE", 1, 0, false);
        declareFunction("new_noun", "NEW-NOUN", 1, 0, false);
        declareFunction("new_verb", "NEW-VERB", 1, 0, false);
        declareFunction("new_adverb", "NEW-ADVERB", 1, 0, false);
        declareFunction("antonyms", "ANTONYMS", 1, 0, false);
        declareFunction("hypernyms", "HYPERNYMS", 1, 0, false);
        declareFunction("hyponyms", "HYPONYMS", 1, 0, false);
        declareFunction("attributes", "ATTRIBUTES", 1, 0, false);
        declareFunction("also_see", "ALSO-SEE", 1, 0, false);
        declareFunction("entailments", "ENTAILMENTS", 1, 0, false);
        declareFunction("causes", "CAUSES", 1, 0, false);
        declareFunction("verb_groups", "VERB-GROUPS", 1, 0, false);
        declareFunction("member_meronyms", "MEMBER-MERONYMS", 1, 0, false);
        declareFunction("substance_meronyms", "SUBSTANCE-MERONYMS", 1, 0, false);
        declareFunction("part_meronyms", "PART-MERONYMS", 1, 0, false);
        declareFunction("member_holonyms", "MEMBER-HOLONYMS", 1, 0, false);
        declareFunction("substance_holonyms", "SUBSTANCE-HOLONYMS", 1, 0, false);
        declareFunction("part_holonyms", "PART-HOLONYMS", 1, 0, false);
        declareFunction("similar", "SIMILAR", 1, 0, false);
        declareFunction("participle_of", "PARTICIPLE-OF", 1, 0, false);
        declareFunction("pertainyms", "PERTAINYMS", 1, 0, false);
        return NIL;
    }

    static private final SubLList $list_alt74 = list(list(RET, makeSymbol("FORM")));

    static private final SubLList $list_alt77 = list(makeSymbol("OBJECT"));

    static private final SubLList $list_alt78 = list(list(RET, list(makeSymbol("CAND"), list(makeSymbol("WORD-PROXY-P"), makeSymbol("OBJECT")), list(EQUAL, makeSymbol("FORM"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("FORM")))), list(EQUAL, makeSymbol("POS"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("POS")))))));

    static private final SubLList $list_alt82 = list(new SubLObject[]{ list(makeSymbol("POS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("OFFSET"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("GLOSS"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("OFFSET"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("GLOSS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), EQUAL, list(makeSymbol("OBJECT")), makeKeyword("PUBLIC")) });

    static private final SubLList $list_alt87 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("WRITE-STRING"), makeString("#<SYNSET-PROXY "), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeSymbol("POS"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(" "), makeSymbol("STREAM")), list(makeSymbol("PRINC"), makeSymbol("OFFSET"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(">"), makeSymbol("STREAM")), list(RET, makeSymbol("SELF")));

    static private final SubLString $str_alt89$__SYNSET_PROXY_ = makeString("#<SYNSET-PROXY ");

    static private final SubLString $str_alt90$_ = makeString(" ");

    static private final SubLList $list_alt92 = list(list(RET, makeString("synset")));

    static private final SubLList $list_alt95 = list(list(RET, list(makeSymbol("BQ-LIST"), list(makeSymbol("BQ-LIST"), makeString("pos"), makeSymbol("POS")), list(makeSymbol("BQ-LIST"), makeString("offset"), makeSymbol("OFFSET")))));

    static private final SubLList $list_alt101 = list(list(RET, makeSymbol("OFFSET")));

    static private final SubLList $list_alt104 = list(list(RET, makeSymbol("GLOSS")));

    static private final SubLList $list_alt107 = list(list(RET, list(makeSymbol("CAND"), list(makeSymbol("SYNSET-PROXY-P"), makeSymbol("OBJECT")), list(makeSymbol("="), makeSymbol("OFFSET"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("OFFSET")))), list(EQUAL, makeSymbol("POS"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("POS")))))));

    static private final SubLList $list_alt111 = list(new SubLObject[]{ list(makeSymbol("SYNSET"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("FORM"), makeKeyword("INSTANCE"), makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("INITIALIZE"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("RELATIVE-KEY"), NIL, makeKeyword("PROTECTED")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("FORM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("POS"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("PRINT"), list(makeSymbol("STREAM"), makeSymbol("DEPTH")), makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("WORD"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), makeSymbol("NUM"), NIL, makeKeyword("PUBLIC")), list(makeSymbol("DEF-INSTANCE-METHOD"), EQUAL, list(makeSymbol("OBJECT")), makeKeyword("PUBLIC")) });

    static private final SubLList $list_alt115 = list(list(makeSymbol("IGNORE"), makeSymbol("DEPTH")), list(makeSymbol("WRITE-STRING"), makeString("#<SENSE-PROXY "), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("FORM"))), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(" "), makeSymbol("STREAM")), list(makeSymbol("PRINC"), makeSymbol("SYNSET"), makeSymbol("STREAM")), list(makeSymbol("WRITE-STRING"), makeString(">"), makeSymbol("STREAM")), list(RET, makeSymbol("SELF")));

    public static SubLObject init_wordnet_proxy_file() {
        return NIL;
    }

    public static SubLObject setup_wordnet_proxy_file() {
        classes.subloop_new_class(WORDNET_PROXY, OBJECT, NIL, T, $list16);
        classes.class_set_implements_slot_listeners(WORDNET_PROXY, NIL);
        classes.subloop_note_class_initialization_function(WORDNET_PROXY, SUBLOOP_RESERVED_INITIALIZE_WORDNET_PROXY_CLASS);
        classes.subloop_note_instance_initialization_function(WORDNET_PROXY, SUBLOOP_RESERVED_INITIALIZE_WORDNET_PROXY_INSTANCE);
        subloop_reserved_initialize_wordnet_proxy_class(WORDNET_PROXY);
        methods.methods_incorporate_instance_method(ABSOLUTE_KEY, WORDNET_PROXY, $list3, NIL, $list23);
        methods.subloop_register_instance_method(WORDNET_PROXY, ABSOLUTE_KEY, WORDNET_PROXY_ABSOLUTE_KEY_METHOD);
        methods.methods_incorporate_instance_method(SENSES, WORDNET_PROXY, $list3, NIL, $list29);
        methods.subloop_register_instance_method(WORDNET_PROXY, SENSES, WORDNET_PROXY_SENSES_METHOD);
        methods.methods_incorporate_instance_method(SYNSETS, WORDNET_PROXY, $list3, NIL, $list34);
        methods.subloop_register_instance_method(WORDNET_PROXY, SYNSETS, WORDNET_PROXY_SYNSETS_METHOD);
        methods.methods_incorporate_instance_method(LINKS, WORDNET_PROXY, $list3, $list38, $list39);
        methods.subloop_register_instance_method(WORDNET_PROXY, LINKS, WORDNET_PROXY_LINKS_METHOD);
        methods.methods_incorporate_instance_method(INVOKE, WORDNET_PROXY, $list3, $list43, $list44);
        methods.subloop_register_instance_method(WORDNET_PROXY, INVOKE, WORDNET_PROXY_INVOKE_METHOD);
        classes.subloop_new_class(WORD_PROXY, WORDNET_PROXY, NIL, NIL, $list48);
        classes.class_set_implements_slot_listeners(WORD_PROXY, NIL);
        classes.subloop_note_class_initialization_function(WORD_PROXY, SUBLOOP_RESERVED_INITIALIZE_WORD_PROXY_CLASS);
        classes.subloop_note_instance_initialization_function(WORD_PROXY, SUBLOOP_RESERVED_INITIALIZE_WORD_PROXY_INSTANCE);
        subloop_reserved_initialize_word_proxy_class(WORD_PROXY);
        methods.methods_incorporate_instance_method(WN_CLASS, WORD_PROXY, $list3, NIL, $list53);
        methods.subloop_register_instance_method(WORD_PROXY, WN_CLASS, WORD_PROXY_WN_CLASS_METHOD);
        methods.methods_incorporate_instance_method(RELATIVE_KEY, WORD_PROXY, $list3, NIL, $list56);
        methods.subloop_register_instance_method(WORD_PROXY, RELATIVE_KEY, WORD_PROXY_RELATIVE_KEY_METHOD);
        methods.methods_incorporate_instance_method(PRINT, WORD_PROXY, $list3, $list62, $list63);
        methods.subloop_register_instance_method(WORD_PROXY, PRINT, WORD_PROXY_PRINT_METHOD);
        methods.methods_incorporate_instance_method(LINKS, WORD_PROXY, $list3, $list38, $list68);
        methods.subloop_register_instance_method(WORD_PROXY, LINKS, WORD_PROXY_LINKS_METHOD);
        methods.methods_incorporate_instance_method(POS, WORD_PROXY, $list70, NIL, $list71);
        methods.subloop_register_instance_method(WORD_PROXY, POS, WORD_PROXY_POS_METHOD);
        methods.methods_incorporate_instance_method(FORM, WORD_PROXY, $list70, NIL, $list74);
        methods.subloop_register_instance_method(WORD_PROXY, FORM, WORD_PROXY_FORM_METHOD);
        methods.methods_incorporate_instance_method(EQUAL, WORD_PROXY, $list70, $list77, $list78);
        methods.subloop_register_instance_method(WORD_PROXY, EQUAL, WORD_PROXY_EQUAL_METHOD);
        classes.subloop_new_class(SYNSET_PROXY, WORDNET_PROXY, NIL, NIL, $list82);
        classes.class_set_implements_slot_listeners(SYNSET_PROXY, NIL);
        classes.subloop_note_class_initialization_function(SYNSET_PROXY, SUBLOOP_RESERVED_INITIALIZE_SYNSET_PROXY_CLASS);
        classes.subloop_note_instance_initialization_function(SYNSET_PROXY, SUBLOOP_RESERVED_INITIALIZE_SYNSET_PROXY_INSTANCE);
        subloop_reserved_initialize_synset_proxy_class(SYNSET_PROXY);
        methods.methods_incorporate_instance_method(PRINT, SYNSET_PROXY, $list3, $list62, $list87);
        methods.subloop_register_instance_method(SYNSET_PROXY, PRINT, SYNSET_PROXY_PRINT_METHOD);
        methods.methods_incorporate_instance_method(WN_CLASS, SYNSET_PROXY, $list3, NIL, $list92);
        methods.subloop_register_instance_method(SYNSET_PROXY, WN_CLASS, SYNSET_PROXY_WN_CLASS_METHOD);
        methods.methods_incorporate_instance_method(RELATIVE_KEY, SYNSET_PROXY, $list3, NIL, $list95);
        methods.subloop_register_instance_method(SYNSET_PROXY, RELATIVE_KEY, SYNSET_PROXY_RELATIVE_KEY_METHOD);
        methods.methods_incorporate_instance_method(POS, SYNSET_PROXY, $list70, NIL, $list71);
        methods.subloop_register_instance_method(SYNSET_PROXY, POS, SYNSET_PROXY_POS_METHOD);
        methods.methods_incorporate_instance_method(OFFSET, SYNSET_PROXY, $list70, NIL, $list101);
        methods.subloop_register_instance_method(SYNSET_PROXY, OFFSET, SYNSET_PROXY_OFFSET_METHOD);
        methods.methods_incorporate_instance_method(GLOSS, SYNSET_PROXY, $list70, NIL, $list104);
        methods.subloop_register_instance_method(SYNSET_PROXY, GLOSS, SYNSET_PROXY_GLOSS_METHOD);
        methods.methods_incorporate_instance_method(EQUAL, SYNSET_PROXY, $list70, $list77, $list107);
        methods.subloop_register_instance_method(SYNSET_PROXY, EQUAL, SYNSET_PROXY_EQUAL_METHOD);
        classes.subloop_new_class(SENSE_PROXY, WORDNET_PROXY, NIL, NIL, $list111);
        classes.class_set_implements_slot_listeners(SENSE_PROXY, NIL);
        classes.subloop_note_class_initialization_function(SENSE_PROXY, SUBLOOP_RESERVED_INITIALIZE_SENSE_PROXY_CLASS);
        classes.subloop_note_instance_initialization_function(SENSE_PROXY, SUBLOOP_RESERVED_INITIALIZE_SENSE_PROXY_INSTANCE);
        subloop_reserved_initialize_sense_proxy_class(SENSE_PROXY);
        methods.methods_incorporate_instance_method(PRINT, SENSE_PROXY, $list3, $list62, $list115);
        methods.subloop_register_instance_method(SENSE_PROXY, PRINT, SENSE_PROXY_PRINT_METHOD);
        methods.methods_incorporate_instance_method(WN_CLASS, SENSE_PROXY, $list3, NIL, $list119);
        methods.subloop_register_instance_method(SENSE_PROXY, WN_CLASS, SENSE_PROXY_WN_CLASS_METHOD);
        methods.methods_incorporate_instance_method(RELATIVE_KEY, SENSE_PROXY, $list3, NIL, $list122);
        methods.subloop_register_instance_method(SENSE_PROXY, RELATIVE_KEY, SENSE_PROXY_RELATIVE_KEY_METHOD);
        methods.methods_incorporate_instance_method(SENSES, SENSE_PROXY, $list3, NIL, $list125);
        methods.subloop_register_instance_method(SENSE_PROXY, SENSES, SENSE_PROXY_SENSES_METHOD);
        methods.methods_incorporate_instance_method(FORM, SENSE_PROXY, $list70, NIL, $list74);
        methods.subloop_register_instance_method(SENSE_PROXY, FORM, SENSE_PROXY_FORM_METHOD);
        methods.methods_incorporate_instance_method(POS, SENSE_PROXY, $list70, NIL, $list129);
        methods.subloop_register_instance_method(SENSE_PROXY, POS, SENSE_PROXY_POS_METHOD);
        methods.methods_incorporate_instance_method(WORD, SENSE_PROXY, $list70, NIL, $list133);
        methods.subloop_register_instance_method(SENSE_PROXY, WORD, SENSE_PROXY_WORD_METHOD);
        methods.methods_incorporate_instance_method(NUM, SENSE_PROXY, $list70, NIL, $list137);
        methods.subloop_register_instance_method(SENSE_PROXY, NUM, SENSE_PROXY_NUM_METHOD);
        methods.methods_incorporate_instance_method(EQUAL, SENSE_PROXY, $list70, $list77, $list139);
        methods.subloop_register_instance_method(SENSE_PROXY, EQUAL, SENSE_PROXY_EQUAL_METHOD);
        methods.methods_incorporate_instance_method(INITIALIZE, SYNSET_PROXY, $list3, NIL, $list142);
        methods.subloop_register_instance_method(SYNSET_PROXY, INITIALIZE, SYNSET_PROXY_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(INITIALIZE, SENSE_PROXY, $list3, NIL, $list142);
        methods.subloop_register_instance_method(SENSE_PROXY, INITIALIZE, SENSE_PROXY_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(INITIALIZE, WORD_PROXY, $list3, NIL, $list142);
        methods.subloop_register_instance_method(WORD_PROXY, INITIALIZE, WORD_PROXY_INITIALIZE_METHOD);
        methods.methods_incorporate_instance_method(INITIALIZE, WORDNET_PROXY, $list3, NIL, $list142);
        methods.subloop_register_instance_method(WORDNET_PROXY, INITIALIZE, WORDNET_PROXY_INITIALIZE_METHOD);
        return NIL;
    }

    static private final SubLString $str_alt117$__SENSE_PROXY_ = makeString("#<SENSE-PROXY ");

    static private final SubLList $list_alt119 = list(list(RET, makeString("sense")));

    static private final SubLList $list_alt122 = list(list(RET, list(makeSymbol("CONS"), list(makeSymbol("BQ-LIST"), makeString("form"), makeSymbol("FORM")), list(makeSymbol("FIM"), makeSymbol("SYNSET"), list(QUOTE, makeSymbol("RELATIVE-KEY"))))));

    static private final SubLList $list_alt125 = list(list(RET, list(makeSymbol("LIST"), makeSymbol("SELF"))));

    static private final SubLList $list_alt129 = list(list(RET, list(makeSymbol("FIM"), makeSymbol("SYNSET"), list(QUOTE, makeSymbol("POS")))));

    static private final SubLList $list_alt133 = list(list(RET, list(makeSymbol("NEW-WORD-PROXY"), makeSymbol("FORM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("POS"))))));

    static private final SubLList $list_alt137 = list(list(makeSymbol("CLET"), list(list(makeSymbol("I"), ZERO_INTEGER)), list(makeSymbol("CDOLIST"), list(makeSymbol("SENSE"), list(makeSymbol("FIM"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, makeSymbol("WORD"))), list(QUOTE, makeSymbol("SENSES")))), list(makeSymbol("CINC"), makeSymbol("I")), list(makeSymbol("PWHEN"), list(makeSymbol("FIM"), makeSymbol("SELF"), list(QUOTE, EQUAL), makeSymbol("SENSE")), list(RET, makeSymbol("I"))))));

    static private final SubLList $list_alt139 = list(list(RET, list(makeSymbol("CAND"), list(makeSymbol("SENSE-PROXY-P"), makeSymbol("OBJECT")), list(makeSymbol("FIM"), makeSymbol("SYNSET"), list(QUOTE, EQUAL), list(makeSymbol("FIRST"), list(makeSymbol("FIM"), makeSymbol("OBJECT"), list(QUOTE, makeSymbol("SYNSETS"))))))));

    static private final SubLList $list_alt142 = list(list(makeSymbol("INITIALIZE"), makeSymbol("SUPER")), list(RET, makeSymbol("SELF")));

    @Override
    public void declareFunctions() {
        declare_wordnet_proxy_file();
    }

    @Override
    public void initializeVariables() {
        init_wordnet_proxy_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_wordnet_proxy_file();
    }

    static {
    }
}

/**
 * Total time: 451 ms
 */
