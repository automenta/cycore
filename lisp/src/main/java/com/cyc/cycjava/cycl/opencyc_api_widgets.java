package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.utilities_macros.register_cyc_api_function;
import static subl.ConsesLow.list;
import static subl.type.core.SubLObjectFactory.makeKeyword;
import static subl.type.core.SubLObjectFactory.makeString;
import static subl.type.core.SubLObjectFactory.makeSymbol;
import static subl.util.SubLFiles.declareFunction;

import subl.type.core.SubLList;
import subl.type.core.SubLObject;
import subl.type.core.SubLString;
import subl.type.symbol.SubLSymbol;
import subl.util.SubLFile;
import subl.util.SubLTrampolineFile;
import subl.util.SubLTranslatedFile;


public final class opencyc_api_widgets extends SubLTranslatedFile implements V10 {
    public static final SubLFile me = new opencyc_api_widgets();

    public static final String myName = "com.cyc.cycjava_2.cycl.opencyc_api_widgets";


    private static final SubLObject $const0$CurrentWorldDataCollectorMt_NonHo = reader_make_constant_shell(makeString("CurrentWorldDataCollectorMt-NonHomocentric"));





    private static final SubLList $list3 = list(makeKeyword("IO-MODE"), makeKeyword("NL"));

    private static final SubLSymbol OPENCYC_EXPLANATION_OF_WHY_NOT_WFF_ASSERT = makeSymbol("OPENCYC-EXPLANATION-OF-WHY-NOT-WFF-ASSERT");

    private static final SubLList $list5 = list(makeSymbol("SENTENCE"), makeSymbol("&OPTIONAL"), list(makeSymbol("MT"), reader_make_constant_shell(makeString("CurrentWorldDataCollectorMt-NonHomocentric"))));

    private static final SubLString $str6$Returns_an_English_explanation_of = makeString("Returns an English explanation of why SENTENCE is not assertible in MT.  An assertible sentence results in a NIL return value.");

    private static final SubLList $list7 = list(list(makeSymbol("SENTENCE"), makeSymbol("CYCL-SENTENCE-P")), list(makeSymbol("MT"), makeSymbol("HLMT-P")));

    private static final SubLList $list8 = list(makeSymbol("STRING-OR-NIL-P"));

    private static final SubLSymbol OPENCYC_EXPLANATION_OF_WHY_NOT_WFF = makeSymbol("OPENCYC-EXPLANATION-OF-WHY-NOT-WFF");

    public static SubLObject opencyc_explanation_of_why_not_wff_assert(final SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $const0$CurrentWorldDataCollectorMt_NonHo;
        }
        SubLTrampolineFile.enforceType(sentence, CYCL_SENTENCE_P);
        SubLTrampolineFile.enforceType(mt, HLMT_P);
        return wff.explanation_of_why_not_wff_assert(sentence, mt, $list3);
    }

    public static SubLObject opencyc_explanation_of_why_not_wff(final SubLObject sentence, SubLObject mt) {
        if (mt == UNPROVIDED) {
            mt = $const0$CurrentWorldDataCollectorMt_NonHo;
        }
        SubLTrampolineFile.enforceType(sentence, CYCL_SENTENCE_P);
        SubLTrampolineFile.enforceType(mt, HLMT_P);
        return wff.explanation_of_why_not_wff(sentence, mt, $list3);
    }

    public static SubLObject declare_opencyc_api_widgets_file() {
        declareFunction("opencyc_explanation_of_why_not_wff_assert", "OPENCYC-EXPLANATION-OF-WHY-NOT-WFF-ASSERT", 1, 1, false);
        declareFunction("opencyc_explanation_of_why_not_wff", "OPENCYC-EXPLANATION-OF-WHY-NOT-WFF", 1, 1, false);
        return NIL;
    }

    public static SubLObject init_opencyc_api_widgets_file() {
        return NIL;
    }

    public static SubLObject setup_opencyc_api_widgets_file() {
        register_cyc_api_function(OPENCYC_EXPLANATION_OF_WHY_NOT_WFF_ASSERT, $list5, $str6$Returns_an_English_explanation_of, $list7, $list8);
        register_cyc_api_function(OPENCYC_EXPLANATION_OF_WHY_NOT_WFF, $list5, $str6$Returns_an_English_explanation_of, $list7, $list8);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_opencyc_api_widgets_file();
    }

    @Override
    public void initializeVariables() {
        init_opencyc_api_widgets_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_opencyc_api_widgets_file();
    }

    static {











    }
}

/**
 * Total time: 89 ms
 */
