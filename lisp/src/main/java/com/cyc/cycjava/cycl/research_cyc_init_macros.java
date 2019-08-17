package com.cyc.cycjava.cycl;


import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.set_nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.find_package;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.makeSymbolIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.symbolIteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.symbolIteratorNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.fboundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;

import java.util.Iterator;

import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class research_cyc_init_macros extends SubLTranslatedFile implements V10 {
    public static final SubLFile me = new research_cyc_init_macros();

    public static final String myName = "com.cyc.cycjava_2.cycl.research_cyc_init_macros";


    private static final SubLString $$$CYC = makeString("CYC");



    private static final SubLList $list2 = list(list(list(ZERO_INTEGER), list(makeSymbol("RESEARCH-CYC-LICENSE-KEY-ERROR"))), list(list(new SubLObject[]{ makeInteger(3622), makeInteger(2379), makeInteger(874), makeInteger(3729), makeInteger(3456), makeInteger(2344), makeInteger(1538), makeInteger(3655), makeInteger(1856), makeInteger(2951), makeInteger(3458), makeInteger(444), makeInteger(2275), makeInteger(3141), makeInteger(3339), makeInteger(2018) }), list(new SubLObject[]{ makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-1"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-2"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-3"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-4"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-5"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-6"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-7"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-8"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-9"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-10"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-11"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-12"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-13"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-14"), makeSymbol("UNSTASH-KB-OBJECT-LOOKUP-VARS-15") })), list(list(new SubLObject[]{ makeInteger(400), makeInteger(471), makeInteger(2535), makeInteger(317), makeInteger(2086), makeInteger(509), makeInteger(251), makeInteger(2454), makeInteger(3364), makeInteger(3144), makeInteger(1176), makeInteger(2559), makeInteger(3014), makeInteger(212), makeInteger(1091), makeInteger(2890) }), list(new SubLObject[]{ makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-1"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-2"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-3"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-4"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-5"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-6"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-7"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-8"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-9"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-10"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-11"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-12"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-13"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-14"), makeSymbol("UNSTASH-KB-OBJECT-CONTENT-VARS-15") })), list(list(new SubLObject[]{ makeInteger(1738), makeInteger(371), makeInteger(3974), makeInteger(1927), makeInteger(543), makeInteger(762), makeInteger(79), makeInteger(3025), makeInteger(2151), makeInteger(2940), makeInteger(1380), makeInteger(270), makeInteger(2995), makeInteger(1066), makeInteger(315), makeInteger(3286) }), list(new SubLObject[]{ makeSymbol("UNSTASH-KB-INDEXING-VARS"), makeSymbol("UNSTASH-KB-INDEXING-VARS-1"), makeSymbol("UNSTASH-KB-INDEXING-VARS-2"), makeSymbol("UNSTASH-KB-INDEXING-VARS-3"), makeSymbol("UNSTASH-KB-INDEXING-VARS-4"), makeSymbol("UNSTASH-KB-INDEXING-VARS-5"), makeSymbol("UNSTASH-KB-INDEXING-VARS-6"), makeSymbol("UNSTASH-KB-INDEXING-VARS-7"), makeSymbol("UNSTASH-KB-INDEXING-VARS-8"), makeSymbol("UNSTASH-KB-INDEXING-VARS-9"), makeSymbol("UNSTASH-KB-INDEXING-VARS-10"), makeSymbol("UNSTASH-KB-INDEXING-VARS-11"), makeSymbol("UNSTASH-KB-INDEXING-VARS-12"), makeSymbol("UNSTASH-KB-INDEXING-VARS-13"), makeSymbol("UNSTASH-KB-INDEXING-VARS-14"), makeSymbol("UNSTASH-KB-INDEXING-VARS-15") })), list(list(new SubLObject[]{ makeInteger(319), makeInteger(3892), makeInteger(975), makeInteger(1249), makeInteger(1059), makeInteger(1062), makeInteger(3937), FOUR_INTEGER, makeInteger(3166), makeInteger(3946), makeInteger(4073), makeInteger(2987), makeInteger(2637), makeInteger(3274), makeInteger(2400), makeInteger(3092) }), list(new SubLObject[]{ makeSymbol("UNSTASH-INFERENCE-VARS"), makeSymbol("UNSTASH-INFERENCE-VARS-1"), makeSymbol("UNSTASH-INFERENCE-VARS-2"), makeSymbol("UNSTASH-INFERENCE-VARS-3"), makeSymbol("UNSTASH-INFERENCE-VARS-4"), makeSymbol("UNSTASH-INFERENCE-VARS-5"), makeSymbol("UNSTASH-INFERENCE-VARS-6"), makeSymbol("UNSTASH-INFERENCE-VARS-7"), makeSymbol("UNSTASH-INFERENCE-VARS-8"), makeSymbol("UNSTASH-INFERENCE-VARS-9"), makeSymbol("UNSTASH-INFERENCE-VARS-10"), makeSymbol("UNSTASH-INFERENCE-VARS-11"), makeSymbol("UNSTASH-INFERENCE-VARS-12"), makeSymbol("UNSTASH-INFERENCE-VARS-13"), makeSymbol("UNSTASH-INFERENCE-VARS-14"), makeSymbol("UNSTASH-INFERENCE-VARS-15") })), list(list(new SubLObject[]{ makeInteger(2392), makeInteger(2253), makeInteger(3022), makeInteger(2648), makeInteger(634), makeInteger(403), makeInteger(505), makeInteger(1872), makeInteger(3087), makeInteger(827), makeInteger(3890), makeInteger(1932), makeInteger(3377), makeInteger(2071), makeInteger(730), makeInteger(1127) }), list(new SubLObject[]{ makeSymbol("UNSTASH-OTHER-VARS"), makeSymbol("UNSTASH-OTHER-VARS-1"), makeSymbol("UNSTASH-OTHER-VARS-2"), makeSymbol("UNSTASH-OTHER-VARS-3"), makeSymbol("UNSTASH-OTHER-VARS-4"), makeSymbol("UNSTASH-OTHER-VARS-5"), makeSymbol("UNSTASH-OTHER-VARS-6"), makeSymbol("UNSTASH-OTHER-VARS-7"), makeSymbol("UNSTASH-OTHER-VARS-8"), makeSymbol("UNSTASH-OTHER-VARS-9"), makeSymbol("UNSTASH-OTHER-VARS-10"), makeSymbol("UNSTASH-OTHER-VARS-11"), makeSymbol("UNSTASH-OTHER-VARS-12"), makeSymbol("UNSTASH-OTHER-VARS-13"), makeSymbol("UNSTASH-OTHER-VARS-14"), makeSymbol("UNSTASH-OTHER-VARS-15") })));

    private static final SubLList $list3 = list(makeSymbol("POSITIONS"), makeSymbol("CHOICES"));

    private static final SubLSymbol METHOD_FORMAL_ARGLIST = makeSymbol("METHOD-FORMAL-ARGLIST");

    public static SubLObject compute_research_cyc_initialization_methods(final SubLObject macroform, final SubLObject environment) {
        final SubLObject datum = macroform.rest();
        if (NIL != datum) {
            cdestructuring_bind_error(datum, NIL);
        }
        final SubLObject symbol_set = set.new_set(symbol_function(EQ), UNPROVIDED);
        final SubLObject cdo_symbols_package = find_package($$$CYC);
        final SubLObject cdo_symbols_iterator = NIL;
        SubLObject symbol = NIL;
        final Iterator cdo_symbols_iterator_$1 = makeSymbolIterator(cdo_symbols_package);
        while (symbolIteratorHasNext(cdo_symbols_iterator_$1)) {
            symbol = symbolIteratorNext(cdo_symbols_iterator_$1);
            if (NIL != function_symbol_with_no_args_p(symbol)) {
                set.set_add(symbol, symbol_set);
            }
        } 
        SubLObject initialization_methods = NIL;
        SubLObject i;
        for (i = NIL, i = ZERO_INTEGER; i.numL($int$4096); i = add(i, ONE_INTEGER)) {
            initialization_methods = cons(set.set_random_element(symbol_set), initialization_methods);
        }
        SubLObject cdolist_list_var;
        final SubLObject class_table = cdolist_list_var = $list2;
        SubLObject class_info = NIL;
        class_info = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum_$2 = current = class_info;
            SubLObject positions = NIL;
            SubLObject choices = NIL;
            destructuring_bind_must_consp(current, datum_$2, $list3);
            positions = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum_$2, $list3);
            choices = current.first();
            current = current.rest();
            if (NIL == current) {
                SubLObject cdolist_list_var_$3 = positions;
                SubLObject position = NIL;
                position = cdolist_list_var_$3.first();
                while (NIL != cdolist_list_var_$3) {
                    set_nth(position, initialization_methods, list_utilities.random_element(choices));
                    cdolist_list_var_$3 = cdolist_list_var_$3.rest();
                    position = cdolist_list_var_$3.first();
                } 
            } else {
                cdestructuring_bind_error(datum_$2, $list3);
            }
            cdolist_list_var = cdolist_list_var.rest();
            class_info = cdolist_list_var.first();
        } 
        return list_utilities.quotify(initialization_methods);
    }

    public static SubLObject function_symbol_with_no_args_p(final SubLObject v_object) {
        if (NIL != subl_promotions.function_symbol_p(v_object)) {
            final SubLObject arglist = (NIL != fboundp(METHOD_FORMAL_ARGLIST)) ? xref_database.method_formal_arglist(v_object) : subl_promotions.function_symbol_arglist(v_object);
            return sublisp_null(arglist);
        }
        return NIL;
    }

    public static SubLObject declare_research_cyc_init_macros_file() {
        declareMacro("compute_research_cyc_initialization_methods", "COMPUTE-RESEARCH-CYC-INITIALIZATION-METHODS");
        declareFunction("function_symbol_with_no_args_p", "FUNCTION-SYMBOL-WITH-NO-ARGS-P", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_research_cyc_init_macros_file() {
        return NIL;
    }

    public static SubLObject setup_research_cyc_init_macros_file() {
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_research_cyc_init_macros_file();
    }

    @Override
    public void initializeVariables() {
        init_research_cyc_init_macros_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_research_cyc_init_macros_file();
    }

    static {






    }
}

/**
 * Total time: 79 ms
 */
