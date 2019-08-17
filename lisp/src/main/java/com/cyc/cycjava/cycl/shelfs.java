/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.utilities_macros.$last_percent_progress_index$;
import static com.cyc.cycjava.cycl.utilities_macros.$last_percent_progress_prediction$;
import static com.cyc.cycjava.cycl.utilities_macros.$percent_progress_start_time$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_sofar$;
import static com.cyc.cycjava.cycl.utilities_macros.$progress_start_time$;
import static com.cyc.cycjava.cycl.utilities_macros.$within_noting_percent_progress$;
import static com.cyc.cycjava.cycl.utilities_macros.note_percent_progress;
import static com.cyc.cycjava.cycl.utilities_macros.noting_percent_progress_postamble;
import static com.cyc.cycjava.cycl.utilities_macros.noting_percent_progress_preamble;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_greater;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_space;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.rplaca;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.rplacd;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.set_nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.pointer;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.apply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.integerDivide;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numGE;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.write;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.find;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.get_universal_time;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.type_of;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.vectorp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.make_vector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.set_aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.caar;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cdar;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.last;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_readably$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.print_not_readable;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_char;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_string;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;

import org.armedbear.lisp.Lisp;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Storage;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLSpecialOperatorDeclarations;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDecl;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDeclNative;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.UnaryFunction;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLStructNative;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.compatibility;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_macros;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      SHELFS
 * source file: /cyc/top/cycl/shelfs.lisp
 * created:     2019/07/03 17:37:14
 */
public final class shelfs extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new shelfs();

 public static final String myName = "com.cyc.cycjava.cycl.shelfs";


    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_shelf_info$ = makeSymbol("*DTP-SHELF-INFO*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_shelf$ = makeSymbol("*DTP-SHELF*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol SHELF_INFO = makeSymbol("SHELF-INFO");

    private static final SubLSymbol SHELF_INFO_P = makeSymbol("SHELF-INFO-P");

    private static final SubLInteger $int$134 = makeInteger(134);

    static private final SubLList $list3 = list(makeSymbol("KEY"), makeSymbol("TEST"), makeSymbol("COMPARE"), makeSymbol("MINOR-LIMIT"), makeSymbol("MAJOR-LIMIT"));

    static private final SubLList $list4 = list(makeKeyword("KEY"), $TEST, makeKeyword("COMPARE"), makeKeyword("MINOR-LIMIT"), makeKeyword("MAJOR-LIMIT"));

    static private final SubLList $list5 = list(makeSymbol("SHELF-INFO-KEY"), makeSymbol("SHELF-INFO-TEST"), makeSymbol("SHELF-INFO-COMPARE"), makeSymbol("SHELF-INFO-MINOR-LIMIT"), makeSymbol("SHELF-INFO-MAJOR-LIMIT"));

    static private final SubLList $list6 = list(makeSymbol("_CSETF-SHELF-INFO-KEY"), makeSymbol("_CSETF-SHELF-INFO-TEST"), makeSymbol("_CSETF-SHELF-INFO-COMPARE"), makeSymbol("_CSETF-SHELF-INFO-MINOR-LIMIT"), makeSymbol("_CSETF-SHELF-INFO-MAJOR-LIMIT"));

    private static final SubLSymbol SHELF_INFO_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("SHELF-INFO-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list9 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("SHELF-INFO-P"));

    private static final SubLSymbol SHELF_INFO_KEY = makeSymbol("SHELF-INFO-KEY");

    private static final SubLSymbol _CSETF_SHELF_INFO_KEY = makeSymbol("_CSETF-SHELF-INFO-KEY");

    private static final SubLSymbol SHELF_INFO_TEST = makeSymbol("SHELF-INFO-TEST");

    private static final SubLSymbol _CSETF_SHELF_INFO_TEST = makeSymbol("_CSETF-SHELF-INFO-TEST");

    private static final SubLSymbol SHELF_INFO_COMPARE = makeSymbol("SHELF-INFO-COMPARE");

    private static final SubLSymbol _CSETF_SHELF_INFO_COMPARE = makeSymbol("_CSETF-SHELF-INFO-COMPARE");

    private static final SubLSymbol SHELF_INFO_MINOR_LIMIT = makeSymbol("SHELF-INFO-MINOR-LIMIT");

    private static final SubLSymbol _CSETF_SHELF_INFO_MINOR_LIMIT = makeSymbol("_CSETF-SHELF-INFO-MINOR-LIMIT");

    private static final SubLSymbol SHELF_INFO_MAJOR_LIMIT = makeSymbol("SHELF-INFO-MAJOR-LIMIT");

    private static final SubLSymbol _CSETF_SHELF_INFO_MAJOR_LIMIT = makeSymbol("_CSETF-SHELF-INFO-MAJOR-LIMIT");

    private static final SubLString $str25$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_SHELF_INFO = makeSymbol("MAKE-SHELF-INFO");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_SHELF_INFO_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-SHELF-INFO-METHOD");

    private static final SubLSymbol SHELF = makeSymbol("SHELF");

    private static final SubLSymbol SHELF_P = makeSymbol("SHELF-P");

    private static final SubLInteger $int$135 = makeInteger(135);

    private static final SubLList $list34 = list(makeSymbol("INFO"), makeSymbol("PAYLOAD"), makeSymbol("SIZE"));

    private static final SubLList $list35 = list($INFO, makeKeyword("PAYLOAD"), $SIZE);

    private static final SubLList $list36 = list(makeSymbol("SHELF-INFO"), makeSymbol("SHELF-PAYLOAD"), makeSymbol("SHELF-SIZE"));

    private static final SubLList $list37 = list(makeSymbol("_CSETF-SHELF-INFO"), makeSymbol("_CSETF-SHELF-PAYLOAD"), makeSymbol("_CSETF-SHELF-SIZE"));

    private static final SubLSymbol PRINT_SHELF = makeSymbol("PRINT-SHELF");

    private static final SubLSymbol SHELF_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("SHELF-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list40 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("SHELF-P"));

    private static final SubLSymbol _CSETF_SHELF_INFO = makeSymbol("_CSETF-SHELF-INFO");

    private static final SubLSymbol SHELF_PAYLOAD = makeSymbol("SHELF-PAYLOAD");

    private static final SubLSymbol _CSETF_SHELF_PAYLOAD = makeSymbol("_CSETF-SHELF-PAYLOAD");

    private static final SubLSymbol SHELF_SIZE = makeSymbol("SHELF-SIZE");

    private static final SubLSymbol _CSETF_SHELF_SIZE = makeSymbol("_CSETF-SHELF-SIZE");

    private static final SubLSymbol MAKE_SHELF = makeSymbol("MAKE-SHELF");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_SHELF_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-SHELF-METHOD");

    private static final SubLString $str51$_D_items = makeString("~D items");

    private static final SubLString $str52$__unfinalized_ = makeString(" (unfinalized)");

    private static final SubLString $str53$Finalizing__S_before_its_time_ = makeString("Finalizing ~S before its time.");

    private static final SubLString $str54$_S_has_already_been_finalized_ = makeString("~S has already been finalized.");

    private static final SubLString $str55$Shelf__S_being_finalized_with_les = makeString("Shelf ~S being finalized with less than expected ~D items.");

    private static final SubLString $str57$Finalize_it_ = makeString("Finalize it.");

    private static final SubLString $str58$_S_has_not_been_finalized_ = makeString("~S has not been finalized.");

    private static final SubLList $list59 = list(list(makeSymbol("VAR"), makeSymbol("SHELF"), makeSymbol("&OPTIONAL"), list(makeSymbol("MESSAGE"), makeString("do-shelf"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLString $str60$do_shelf = makeString("do-shelf");

    private static final SubLSymbol $sym61$SHELF_VAR = makeUninternedSymbol("SHELF-VAR");

    private static final SubLSymbol $sym62$PAYLOAD = makeUninternedSymbol("PAYLOAD");

    private static final SubLSymbol $sym63$I = makeUninternedSymbol("I");

    private static final SubLSymbol $sym64$VECTOR = makeUninternedSymbol("VECTOR");

    private static final SubLSymbol $sym65$DATA = makeUninternedSymbol("DATA");

    private static final SubLSymbol $progress_note$ = makeSymbol("*PROGRESS-NOTE*");

    private static final SubLList $list69 = list(makeSymbol("CSETQ"), makeSymbol("*PROGRESS-START-TIME*"), list(makeSymbol("GET-UNIVERSAL-TIME")));

    private static final SubLSymbol $progress_total$ = makeSymbol("*PROGRESS-TOTAL*");

    private static final SubLSymbol SHELF_APPROXIMATE_COUNT = makeSymbol("SHELF-APPROXIMATE-COUNT");

    private static final SubLList $list72 = list(makeSymbol("CSETQ"), makeSymbol("*PROGRESS-SOFAR*"), ZERO_INTEGER);

    private static final SubLSymbol SHELF_FINALIZED_P = makeSymbol("SHELF-FINALIZED-P");

    static private final SubLList $list81 = list(list(makeSymbol("NOTE-PERCENT-PROGRESS"), makeSymbol("*PROGRESS-SOFAR*"), makeSymbol("*PROGRESS-TOTAL*")), list(makeSymbol("CINC"), makeSymbol("*PROGRESS-SOFAR*")));

    private static final SubLList $list87 = list(list(makeSymbol("VAR"), makeSymbol("SHELF"), makeSymbol("ITEM"), makeSymbol("FORWARDP")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol $sym88$SHELF_VAR = makeUninternedSymbol("SHELF-VAR");

    private static final SubLSymbol $sym89$VECTOR = makeUninternedSymbol("VECTOR");

    private static final SubLSymbol $sym90$LOW = makeUninternedSymbol("LOW");

    private static final SubLSymbol $sym91$HIGH = makeUninternedSymbol("HIGH");

    private static final SubLSymbol $sym92$I = makeUninternedSymbol("I");

    private static final SubLSymbol $sym93$LENGTH = makeUninternedSymbol("LENGTH");

    private static final SubLSymbol FINALIZE_SHELF = makeSymbol("FINALIZE-SHELF");

    private static final SubLSymbol REARRANGE_SHELF = makeSymbol("REARRANGE-SHELF");

    private static final SubLSymbol BSEARCH_SHELF_VECTOR_RANGE = makeSymbol("BSEARCH-SHELF-VECTOR-RANGE");

    private static final SubLSymbol $sym100$_ = makeSymbol("+");

    private static final SubLList $list101 = list(ONE_INTEGER);

    private static final SubLSymbol $sym103$_ = makeSymbol("<");

    private static final SubLSymbol $sym105$_ = makeSymbol("-");

    private static final SubLList $list106 = list(ZERO_INTEGER);

    private static final SubLString $str107$Ignore_the_addition_ = makeString("Ignore the addition.");

    private static final SubLString $str108$Attempt_to_add__S_to__S_ = makeString("Attempt to add ~S to ~S.");

    private static final SubLSymbol ITERATOR_SHELF_DONE = makeSymbol("ITERATOR-SHELF-DONE");

    private static final SubLSymbol ITERATOR_SHELF_NEXT = makeSymbol("ITERATOR-SHELF-NEXT");

    public static final SubLObject shelf_info_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject shelf_info_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject shelf_info_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.shelfs.$shelf_info_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject shelf_info_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.shelfs.$shelf_info_native.class ? T : NIL;
    }

    public static final SubLObject shelf_info_key_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.getField2();
    }

    public static SubLObject shelf_info_key(final SubLObject v_object) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject shelf_info_test_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.getField3();
    }

    public static SubLObject shelf_info_test(final SubLObject v_object) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject shelf_info_compare_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.getField4();
    }

    public static SubLObject shelf_info_compare(final SubLObject v_object) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject shelf_info_minor_limit_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.getField5();
    }

    public static SubLObject shelf_info_minor_limit(final SubLObject v_object) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject shelf_info_major_limit_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.getField6();
    }

    public static SubLObject shelf_info_major_limit(final SubLObject v_object) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject _csetf_shelf_info_key_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_shelf_info_key(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_shelf_info_test_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_shelf_info_test(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_shelf_info_compare_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_shelf_info_compare(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_shelf_info_minor_limit_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_shelf_info_minor_limit(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_shelf_info_major_limit_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_INFO_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_shelf_info_major_limit(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_info_p(v_object) : "! shelfs.shelf_info_p(v_object) " + "shelfs.shelf_info_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject make_shelf_info_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.shelfs.$shelf_info_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($KEY)) {
                        _csetf_shelf_info_key(v_new, current_value);
                    } else {
                        if (pcase_var.eql($TEST)) {
                            _csetf_shelf_info_test(v_new, current_value);
                        } else {
                            if (pcase_var.eql($COMPARE)) {
                                _csetf_shelf_info_compare(v_new, current_value);
                            } else {
                                if (pcase_var.eql($MINOR_LIMIT)) {
                                    _csetf_shelf_info_minor_limit(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($MAJOR_LIMIT)) {
                                        _csetf_shelf_info_major_limit(v_new, current_value);
                                    } else {
                                        Errors.error($str_alt24$Invalid_slot__S_for_construction_, current_arg);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return v_new;
        }
    }

    public static SubLObject make_shelf_info(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.shelfs.$shelf_info_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($KEY)) {
                _csetf_shelf_info_key(v_new, current_value);
            } else
                if (pcase_var.eql($TEST)) {
                    _csetf_shelf_info_test(v_new, current_value);
                } else
                    if (pcase_var.eql($COMPARE)) {
                        _csetf_shelf_info_compare(v_new, current_value);
                    } else
                        if (pcase_var.eql($MINOR_LIMIT)) {
                            _csetf_shelf_info_minor_limit(v_new, current_value);
                        } else
                            if (pcase_var.eql($MAJOR_LIMIT)) {
                                _csetf_shelf_info_major_limit(v_new, current_value);
                            } else {
                                Errors.error($str25$Invalid_slot__S_for_construction_, current_arg);
                            }




        }
        return v_new;
    }

    public static SubLObject visit_defstruct_shelf_info(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_SHELF_INFO, FIVE_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $KEY, shelf_info_key(obj));
        funcall(visitor_fn, obj, $SLOT, $TEST, shelf_info_test(obj));
        funcall(visitor_fn, obj, $SLOT, $COMPARE, shelf_info_compare(obj));
        funcall(visitor_fn, obj, $SLOT, $MINOR_LIMIT, shelf_info_minor_limit(obj));
        funcall(visitor_fn, obj, $SLOT, $MAJOR_LIMIT, shelf_info_major_limit(obj));
        funcall(visitor_fn, obj, $END, MAKE_SHELF_INFO, FIVE_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_shelf_info_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_shelf_info(obj, visitor_fn);
    }

    public static final SubLObject shelf_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        print_shelf(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject shelf_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        print_shelf(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject shelf_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.shelfs.$shelf_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject shelf_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.shelfs.$shelf_native.class ? T : NIL;
    }

    public static final SubLObject shelf_info_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_P);
        return v_object.getField2();
    }

    public static SubLObject shelf_info(final SubLObject v_object) {
        assert NIL != shelf_p(v_object) : "! shelfs.shelf_p(v_object) " + "shelfs.shelf_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject shelf_payload_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_P);
        return v_object.getField3();
    }

    public static SubLObject shelf_payload(final SubLObject v_object) {
        assert NIL != shelf_p(v_object) : "! shelfs.shelf_p(v_object) " + "shelfs.shelf_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject shelf_size_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SHELF_P);
        return v_object.getField4();
    }

    public static SubLObject shelf_size(final SubLObject v_object) {
        assert NIL != shelf_p(v_object) : "! shelfs.shelf_p(v_object) " + "shelfs.shelf_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject _csetf_shelf_info_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_shelf_info(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_p(v_object) : "! shelfs.shelf_p(v_object) " + "shelfs.shelf_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_shelf_payload_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_shelf_payload(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_p(v_object) : "! shelfs.shelf_p(v_object) " + "shelfs.shelf_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_shelf_size_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SHELF_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_shelf_size(final SubLObject v_object, final SubLObject value) {
        assert NIL != shelf_p(v_object) : "! shelfs.shelf_p(v_object) " + "shelfs.shelf_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject make_shelf_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.shelfs.$shelf_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($INFO)) {
                        _csetf_shelf_info(v_new, current_value);
                    } else {
                        if (pcase_var.eql($PAYLOAD)) {
                            _csetf_shelf_payload(v_new, current_value);
                        } else {
                            if (pcase_var.eql($SIZE)) {
                                _csetf_shelf_size(v_new, current_value);
                            } else {
                                Errors.error($str_alt24$Invalid_slot__S_for_construction_, current_arg);
                            }
                        }
                    }
                }
            }
            return v_new;
        }
    }

    public static SubLObject make_shelf(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.shelfs.$shelf_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($INFO)) {
                _csetf_shelf_info(v_new, current_value);
            } else
                if (pcase_var.eql($PAYLOAD)) {
                    _csetf_shelf_payload(v_new, current_value);
                } else
                    if (pcase_var.eql($SIZE)) {
                        _csetf_shelf_size(v_new, current_value);
                    } else {
                        Errors.error($str25$Invalid_slot__S_for_construction_, current_arg);
                    }


        }
        return v_new;
    }

    public static SubLObject visit_defstruct_shelf(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_SHELF, THREE_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $INFO, shelf_info(obj));
        funcall(visitor_fn, obj, $SLOT, $PAYLOAD, shelf_payload(obj));
        funcall(visitor_fn, obj, $SLOT, $SIZE, shelf_size(obj));
        funcall(visitor_fn, obj, $END, MAKE_SHELF, THREE_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_shelf_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_shelf(obj, visitor_fn);
    }

    public static final SubLObject shelf_finalized_p_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            return makeBoolean(payload.isVector() || (!payload.first().isNumber()));
        }
    }

    public static SubLObject shelf_finalized_p(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        return makeBoolean(payload.isVector() || (!payload.first().isNumber()));
    }

    public static final SubLObject print_shelf_alt(SubLObject shelf, SubLObject stream, SubLObject depth) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != $print_readably$.getDynamicValue(thread)) {
                print_not_readable(shelf, stream);
            } else {
                {
                    SubLObject v_object = shelf;
                    SubLObject stream_1 = stream;
                    write_string($str_alt42$__, stream_1, UNPROVIDED, UNPROVIDED);
                    write(type_of(v_object), new SubLObject[]{ $STREAM, stream_1 });
                    write_char(CHAR_space, stream_1);
                    format(stream, $str_alt44$_D_items, shelf_approximate_count(shelf));
                    if (NIL == shelf_finalized_p(shelf)) {
                        write_string($str_alt45$__unfinalized_, stream, UNPROVIDED, UNPROVIDED);
                    }
                    write_char(CHAR_space, stream_1);
                    write(pointer(v_object), new SubLObject[]{ $STREAM, stream_1, $BASE, SIXTEEN_INTEGER });
                    write_char(CHAR_greater, stream_1);
                }
            }
            return shelf;
        }
    }

    public static SubLObject print_shelf(final SubLObject shelf, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $print_readably$.getDynamicValue(thread)) {
            print_not_readable(shelf, stream);
        } else {
            print_macros.print_unreadable_object_preamble(stream, shelf, T, T);
            format(stream, $str51$_D_items, shelf_approximate_count(shelf));
            if (NIL == shelf_finalized_p(shelf)) {
                write_string($str52$__unfinalized_, stream, UNPROVIDED, UNPROVIDED);
            }
            print_macros.print_unreadable_object_postamble(stream, shelf, T, T);
        }
        return shelf;
    }

    public static final SubLObject allocate_shelf_alt(SubLObject size, SubLObject info) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject shelf = make_shelf(UNPROVIDED);
                _csetf_shelf_info(shelf, info);
                if (NIL != size) {
                    {
                        SubLObject vector = NIL;
                        {
                            SubLObject _prev_bind_0 = Storage.$current_area$.currentBinding(thread);
                            try {
                                Storage.$current_area$.bind(Storage.get_static_area(), thread);
                                vector = make_vector(size, UNPROVIDED);
                            } finally {
                                Storage.$current_area$.rebind(_prev_bind_0, thread);
                            }
                        }
                        _csetf_shelf_payload(shelf, cons(ZERO_INTEGER, vector));
                    }
                } else {
                    _csetf_shelf_payload(shelf, cons(ZERO_INTEGER, NIL));
                }
                return shelf;
            }
        }
    }

    public static SubLObject allocate_shelf(final SubLObject size, final SubLObject info) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject shelf = make_shelf(UNPROVIDED);
        _csetf_shelf_info(shelf, info);
        if (NIL != size) {
            SubLObject vector = NIL;
            final SubLObject _prev_bind_0 = Storage.$current_area$.currentBinding(thread);
            try {
                Storage.$current_area$.bind(Storage.get_static_area(), thread);
                vector = make_vector(size, UNPROVIDED);
            } finally {
                Storage.$current_area$.rebind(_prev_bind_0, thread);
            }
            _csetf_shelf_payload(shelf, cons(ZERO_INTEGER, vector));
        } else {
            _csetf_shelf_payload(shelf, cons(ZERO_INTEGER, NIL));
        }
        return shelf;
    }

    public static final SubLObject initial_add_to_shelf_alt(SubLObject item, SubLObject shelf) {
        if (NIL != shelf_finalized_p(shelf)) {
            add_to_shelf(item, shelf);
        } else {
            {
                SubLObject payload = shelf_payload(shelf);
                SubLObject data = payload.rest();
                SubLObject idx = payload.first();
                if (data.isVector()) {
                    if (idx.numGE(length(data))) {
                        Errors.warn($str_alt47$Finalizing__S_before_its_time_, shelf);
                        finalize_shelf(shelf);
                        add_to_shelf(item, shelf);
                    } else {
                        set_aref(data, idx, item);
                        rplaca(payload, add(payload.first(), ONE_INTEGER));
                    }
                } else {
                    rplacd(payload, cons(item, payload.rest()));
                    rplaca(payload, add(payload.first(), ONE_INTEGER));
                }
            }
        }
        return item;
    }

    public static SubLObject initial_add_to_shelf(final SubLObject item, final SubLObject shelf) {
        if (NIL != shelf_finalized_p(shelf)) {
            add_to_shelf(item, shelf);
        } else {
            final SubLObject payload = shelf_payload(shelf);
            final SubLObject data = payload.rest();
            final SubLObject idx = payload.first();
            if (data.isVector()) {
                if (idx.numGE(length(data))) {
                    Errors.warn($str53$Finalizing__S_before_its_time_, shelf);
                    finalize_shelf(shelf);
                    add_to_shelf(item, shelf);
                } else {
                    set_aref(data, idx, item);
                    rplaca(payload, add(payload.first(), ONE_INTEGER));
                }
            } else {
                rplacd(payload, cons(item, payload.rest()));
                rplaca(payload, add(payload.first(), ONE_INTEGER));
            }
        }
        return item;
    }

    public static final SubLObject finalize_shelf_alt(SubLObject shelf) {
        if (NIL != shelf_finalized_p(shelf)) {
            Errors.warn($str_alt48$_S_has_already_been_finalized_, shelf);
        } else {
            {
                SubLObject payload = shelf_payload(shelf);
                SubLObject count = payload.first();
                SubLObject info = shelf_info(shelf);
                SubLObject data = payload.rest();
                SubLObject vector = NIL;
                if (data.isVector()) {
                    if (count.numL(length(data))) {
                        Errors.warn($str_alt49$Shelf__S_being_finalized_with_les, shelf, length(data));
                        vector = make_vector(count, UNPROVIDED);
                        {
                            SubLObject i = NIL;
                            for (i = ZERO_INTEGER; i.numL(count); i = add(i, ONE_INTEGER)) {
                                set_aref(vector, i, aref(data, i));
                            }
                        }
                    } else {
                        vector = data;
                    }
                } else {
                    vector = make_vector(payload.first(), UNPROVIDED);
                    {
                        SubLObject current = NIL;
                        SubLObject i = NIL;
                        for (current = data, i = ZERO_INTEGER; NIL != current; current = current.rest() , i = add(i, ONE_INTEGER)) {
                            set_aref(vector, i, current.first());
                        }
                    }
                }
                Sort.sort(vector, shelf_info_compare(info), shelf_info_key(info));
                _csetf_shelf_payload(shelf, vector);
                payload = NIL;
                data = NIL;
                vector = NIL;
            }
        }
        return shelf;
    }

    public static SubLObject finalize_shelf(final SubLObject shelf) {
        if (NIL != shelf_finalized_p(shelf)) {
            Errors.warn($str54$_S_has_already_been_finalized_, shelf);
        } else {
            SubLObject payload = shelf_payload(shelf);
            final SubLObject count = payload.first();
            final SubLObject info = shelf_info(shelf);
            SubLObject data = payload.rest();
            SubLObject vector = NIL;
            if (data.isVector()) {
                if (count.numL(length(data))) {
                    Errors.warn($str55$Shelf__S_being_finalized_with_les, shelf, length(data));
                    vector = make_vector(count, UNPROVIDED);
                    SubLObject i;
                    for (i = NIL, i = ZERO_INTEGER; i.numL(count); i = add(i, ONE_INTEGER)) {
                        set_aref(vector, i, aref(data, i));
                    }
                } else {
                    vector = data;
                }
            } else {
                vector = make_vector(payload.first(), UNPROVIDED);
                SubLObject current;
                SubLObject j;
                for (current = NIL, j = NIL, current = data, j = ZERO_INTEGER; NIL != current; current = current.rest() , j = add(j, ONE_INTEGER)) {
                    set_aref(vector, j, current.first());
                }
            }
            Sort.sort(vector, shelf_info_compare(info), shelf_info_key(info));
            _csetf_shelf_payload(shelf, vector);
            payload = NIL;
            data = NIL;
            vector = NIL;
        }
        return shelf;
    }

    /**
     * The number of items in SHELF.
     */
    @LispMethod(comment = "The number of items in SHELF.")
    public static final SubLObject shelf_count_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            if (NIL != shelf_finalized_p(shelf)) {
                if (payload.isVector()) {
                    return list_utilities.count_if_not(symbol_function(NULL), payload, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                } else {
                    {
                        SubLObject count = ZERO_INTEGER;
                        count = add(count, caar(payload));
                        {
                            SubLObject cdolist_list_var = payload.rest();
                            SubLObject vector = NIL;
                            for (vector = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , vector = cdolist_list_var.first()) {
                                count = add(count, list_utilities.count_if_not(symbol_function(NULL), vector, UNPROVIDED, UNPROVIDED, UNPROVIDED));
                            }
                        }
                        return count;
                    }
                }
            } else {
                return payload.first();
            }
        }
    }

    /**
     * The number of items in SHELF.
     */
    @LispMethod(comment = "The number of items in SHELF.")
    public static SubLObject shelf_count(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        if (NIL == shelf_finalized_p(shelf)) {
            return payload.first();
        }
        if (payload.isVector()) {
            return list_utilities.count_if_not(symbol_function(NULL), payload, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        }
        SubLObject count = ZERO_INTEGER;
        count = add(count, caar(payload));
        SubLObject cdolist_list_var = payload.rest();
        SubLObject vector = NIL;
        vector = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            count = add(count, list_utilities.count_if_not(symbol_function(NULL), vector, UNPROVIDED, UNPROVIDED, UNPROVIDED));
            cdolist_list_var = cdolist_list_var.rest();
            vector = cdolist_list_var.first();
        } 
        return count;
    }

    /**
     * The number of items in SHELF, not counting deleted entries.
     */
    @LispMethod(comment = "The number of items in SHELF, not counting deleted entries.")
    public static final SubLObject shelf_approximate_count_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            if (NIL != shelf_finalized_p(shelf)) {
                if (payload.isVector()) {
                    return length(payload);
                } else {
                    {
                        SubLObject count = ZERO_INTEGER;
                        count = add(count, caar(payload));
                        {
                            SubLObject cdolist_list_var = payload.rest();
                            SubLObject vector = NIL;
                            for (vector = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , vector = cdolist_list_var.first()) {
                                count = add(count, length(vector));
                            }
                        }
                        return count;
                    }
                }
            } else {
                return payload.first();
            }
        }
    }

    /**
     * The number of items in SHELF, not counting deleted entries.
     */
    @LispMethod(comment = "The number of items in SHELF, not counting deleted entries.")
    public static SubLObject shelf_approximate_count(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        if (NIL == shelf_finalized_p(shelf)) {
            return payload.first();
        }
        if (payload.isVector()) {
            return length(payload);
        }
        SubLObject count = ZERO_INTEGER;
        count = add(count, caar(payload));
        SubLObject cdolist_list_var = payload.rest();
        SubLObject vector = NIL;
        vector = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            count = add(count, length(vector));
            cdolist_list_var = cdolist_list_var.rest();
            vector = cdolist_list_var.first();
        } 
        return count;
    }

    /**
     * The approximate number of items in the major storage of SHELF.
     */
    @LispMethod(comment = "The approximate number of items in the major storage of SHELF.")
    public static final SubLObject shelf_major_count_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            if (NIL != shelf_finalized_p(shelf)) {
                if (payload.isVector()) {
                    return length(payload);
                } else {
                    return length(last(payload, UNPROVIDED).first());
                }
            } else {
                return payload.first();
            }
        }
    }

    /**
     * The approximate number of items in the major storage of SHELF.
     */
    @LispMethod(comment = "The approximate number of items in the major storage of SHELF.")
    public static SubLObject shelf_major_count(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        if (NIL == shelf_finalized_p(shelf)) {
            return payload.first();
        }
        if (payload.isVector()) {
            return length(payload);
        }
        return length(last(payload, UNPROVIDED).first());
    }

    /**
     * The number of items in the minor storage of SHELF.
     */
    @LispMethod(comment = "The number of items in the minor storage of SHELF.")
    public static final SubLObject shelf_minor_count_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            if (NIL != shelf_finalized_p(shelf)) {
                if (payload.isVector()) {
                    return ZERO_INTEGER;
                } else {
                    {
                        SubLObject count = ZERO_INTEGER;
                        SubLObject vectors = NIL;
                        for (vectors = payload.rest(); NIL != vectors.rest(); vectors = vectors.rest()) {
                            count = add(count, length(vectors.first()));
                        }
                        return count;
                    }
                }
            } else {
                return ZERO_INTEGER;
            }
        }
    }

    /**
     * The number of items in the minor storage of SHELF.
     */
    @LispMethod(comment = "The number of items in the minor storage of SHELF.")
    public static SubLObject shelf_minor_count(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        if (NIL == shelf_finalized_p(shelf)) {
            return ZERO_INTEGER;
        }
        if (payload.isVector()) {
            return ZERO_INTEGER;
        }
        SubLObject count = ZERO_INTEGER;
        SubLObject vectors;
        for (vectors = NIL, vectors = payload.rest(); NIL != vectors.rest(); vectors = vectors.rest()) {
            count = add(count, length(vectors.first()));
        }
        return count;
    }

    /**
     * The number of items in the extra storage of SHELF.
     */
    @LispMethod(comment = "The number of items in the extra storage of SHELF.")
    public static final SubLObject shelf_extra_count_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            if (NIL != shelf_finalized_p(shelf)) {
                if (payload.isVector()) {
                    return ZERO_INTEGER;
                } else {
                    return caar(payload);
                }
            } else {
                return ZERO_INTEGER;
            }
        }
    }

    /**
     * The number of items in the extra storage of SHELF.
     */
    @LispMethod(comment = "The number of items in the extra storage of SHELF.")
    public static SubLObject shelf_extra_count(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        if (NIL == shelf_finalized_p(shelf)) {
            return ZERO_INTEGER;
        }
        if (payload.isVector()) {
            return ZERO_INTEGER;
        }
        return caar(payload);
    }

    public static final SubLObject rearrange_shelf_alt(SubLObject shelf) {
        if (NIL == shelf_finalized_p(shelf)) {
            Errors.cerror($str_alt51$Finalize_it_, $str_alt52$_S_has_not_been_finalized_, shelf);
            finalize_shelf(shelf);
        }
        {
            SubLObject new_vector = make_vector(shelf_count(shelf), UNPROVIDED);
            SubLObject payload = shelf_payload(shelf);
            SubLObject idx = ZERO_INTEGER;
            if (payload.isVector()) {
                {
                    SubLObject cdotimes_end_var = length(payload);
                    SubLObject i = NIL;
                    for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                        {
                            SubLObject item = aref(payload, i);
                            if (NIL != item) {
                                set_aref(new_vector, idx, item);
                                idx = add(idx, ONE_INTEGER);
                            }
                        }
                    }
                }
            } else {
                {
                    SubLObject cdolist_list_var = cdar(payload);
                    SubLObject item = NIL;
                    for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                        set_aref(new_vector, idx, item);
                        idx = add(idx, ONE_INTEGER);
                    }
                }
                {
                    SubLObject cdolist_list_var = payload.rest();
                    SubLObject vector = NIL;
                    for (vector = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , vector = cdolist_list_var.first()) {
                        {
                            SubLObject cdotimes_end_var = length(vector);
                            SubLObject i = NIL;
                            for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                                {
                                    SubLObject item = aref(vector, i);
                                    if (NIL != item) {
                                        set_aref(new_vector, idx, item);
                                        idx = add(idx, ONE_INTEGER);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            {
                SubLObject info = shelf_info(shelf);
                Sort.sort(new_vector, shelf_info_compare(info), shelf_info_key(info));
            }
            _csetf_shelf_payload(shelf, new_vector);
        }
        return shelf;
    }

    public static SubLObject rearrange_shelf(final SubLObject shelf) {
        if (NIL == shelf_finalized_p(shelf)) {
            Errors.cerror($str57$Finalize_it_, $str58$_S_has_not_been_finalized_, shelf);
            finalize_shelf(shelf);
        }
        final SubLObject new_vector = make_vector(shelf_count(shelf), UNPROVIDED);
        final SubLObject payload = shelf_payload(shelf);
        SubLObject idx = ZERO_INTEGER;
        if (payload.isVector()) {
            SubLObject cdotimes_end_var;
            SubLObject i;
            SubLObject item;
            for (cdotimes_end_var = length(payload), i = NIL, i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                item = aref(payload, i);
                if (NIL != item) {
                    set_aref(new_vector, idx, item);
                    idx = add(idx, ONE_INTEGER);
                }
            }
        } else {
            SubLObject cdolist_list_var = cdar(payload);
            SubLObject item2 = NIL;
            item2 = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                set_aref(new_vector, idx, item2);
                idx = add(idx, ONE_INTEGER);
                cdolist_list_var = cdolist_list_var.rest();
                item2 = cdolist_list_var.first();
            } 
            cdolist_list_var = payload.rest();
            SubLObject vector = NIL;
            vector = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cdotimes_end_var2;
                SubLObject j;
                SubLObject item3;
                for (cdotimes_end_var2 = length(vector), j = NIL, j = ZERO_INTEGER; j.numL(cdotimes_end_var2); j = add(j, ONE_INTEGER)) {
                    item3 = aref(vector, j);
                    if (NIL != item3) {
                        set_aref(new_vector, idx, item3);
                        idx = add(idx, ONE_INTEGER);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                vector = cdolist_list_var.first();
            } 
        }
        final SubLObject info = shelf_info(shelf);
        Sort.sort(new_vector, shelf_info_compare(info), shelf_info_key(info));
        _csetf_shelf_payload(shelf, new_vector);
        return shelf;
    }

    public static final SubLObject tidy_shelf_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            SubLObject vector = make_vector(caar(payload), UNPROVIDED);
            SubLObject idx = ZERO_INTEGER;
            SubLObject cdolist_list_var = cdar(payload);
            SubLObject item = NIL;
            for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                set_aref(vector, idx, item);
                idx = add(idx, ONE_INTEGER);
            }
            {
                SubLObject info = shelf_info(shelf);
                Sort.sort(vector, shelf_info_compare(info), shelf_info_key(info));
            }
            _csetf_shelf_payload(shelf, listS(cons(ZERO_INTEGER, NIL), vector, payload.rest()));
        }
        return shelf;
    }

    public static SubLObject tidy_shelf(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        final SubLObject vector = make_vector(caar(payload), UNPROVIDED);
        SubLObject idx = ZERO_INTEGER;
        SubLObject cdolist_list_var = cdar(payload);
        SubLObject item = NIL;
        item = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            set_aref(vector, idx, item);
            idx = add(idx, ONE_INTEGER);
            cdolist_list_var = cdolist_list_var.rest();
            item = cdolist_list_var.first();
        } 
        final SubLObject info = shelf_info(shelf);
        Sort.sort(vector, shelf_info_compare(info), shelf_info_key(info));
        _csetf_shelf_payload(shelf, listS(cons(ZERO_INTEGER, NIL), vector, payload.rest()));
        return shelf;
    }

    public static final SubLObject do_shelf_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt53);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject var = NIL;
                    SubLObject shelf = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt53);
                    var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt53);
                    shelf = current.first();
                    current = current.rest();
                    {
                        SubLObject message = (current.isCons()) ? ((SubLObject) (current.first())) : $str_alt54$do_shelf;
                        destructuring_bind_must_listp(current, datum, $list_alt53);
                        current = current.rest();
                        if (NIL == current) {
                            current = temp;
                            {
                                SubLObject body = current;
                                SubLObject shelf_var = $sym55$SHELF_VAR;
                                SubLObject payload = $sym56$PAYLOAD;
                                SubLObject i = $sym57$I;
                                SubLObject vector = $sym58$VECTOR;
                                SubLObject data = $sym59$DATA;
                                return list(CLET, list(list(shelf_var, shelf), list(payload, list(SHELF_PAYLOAD, shelf_var))), list(CSETQ, $progress_note$, message), $list_alt63, list(CSETQ, $progress_total$, list(SHELF_APPROXIMATE_COUNT, shelf_var)), $list_alt66, list(NOTING_PERCENT_PROGRESS, $progress_note$, list(PIF, list(SHELF_FINALIZED_P, shelf_var), list(PIF, list(VECTORP, payload), list(CDOTIMES, list(i, list(LENGTH, payload)), listS(CLET, list(list(var, list(AREF, payload, i))), listS(PWHEN, var, append(body, NIL)), $list_alt75)), list(PROGN, listS(CDOLIST, list(var, list(CDAR, payload)), append(body, $list_alt75)), list(CDOLIST, list(vector, list(CDR, payload)), list(CDOTIMES, list(i, list(LENGTH, vector)), listS(CLET, list(list(var, list(AREF, vector, i))), listS(PWHEN, var, append(body, NIL)), $list_alt75))))), list(PROGN, list(CLET, list(list(data, list(CDR, payload))), list(PIF, list(VECTORP, data), list(CDOTIMES, list(i, list(CAR, payload)), listS(CLET, list(list(var, list(AREF, payload, i))), listS(PWHEN, var, append(body, NIL)), $list_alt75)), listS(CDOLIST, list(var, data), append(body, $list_alt75))))))));
                            }
                        } else {
                            cdestructuring_bind_error(datum, $list_alt53);
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject do_shelf(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list59);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject var = NIL;
        SubLObject shelf = NIL;
        destructuring_bind_must_consp(current, datum, $list59);
        var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list59);
        shelf = current.first();
        current = current.rest();
        final SubLObject message = (current.isCons()) ? current.first() : $str60$do_shelf;
        destructuring_bind_must_listp(current, datum, $list59);
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            final SubLObject shelf_var = $sym61$SHELF_VAR;
            final SubLObject payload = $sym62$PAYLOAD;
            final SubLObject i = $sym63$I;
            final SubLObject vector = $sym64$VECTOR;
            final SubLObject data = $sym65$DATA;
            return list(CLET, list(list(shelf_var, shelf), list(payload, list(SHELF_PAYLOAD, shelf_var))), list(CSETQ, $progress_note$, message), $list69, list(CSETQ, $progress_total$, list(SHELF_APPROXIMATE_COUNT, shelf_var)), $list72, list(NOTING_PERCENT_PROGRESS, $progress_note$, list(PIF, list(SHELF_FINALIZED_P, shelf_var), list(PIF, list(VECTORP, payload), list(CDOTIMES, list(i, list(LENGTH, payload)), listS(CLET, list(list(var, list(AREF, payload, i))), listS(PWHEN, var, append(body, NIL)), $list81)), list(PROGN, listS(CDOLIST, list(var, list(CDAR, payload)), append(body, $list81)), list(CDOLIST, list(vector, list(CDR, payload)), list(CDOTIMES, list(i, list(LENGTH, vector)), listS(CLET, list(list(var, list(AREF, vector, i))), listS(PWHEN, var, append(body, NIL)), $list81))))), list(PROGN, list(CLET, list(list(data, list(CDR, payload))), list(PIF, list(VECTORP, data), list(CDOTIMES, list(i, list(CAR, payload)), listS(CLET, list(list(var, list(AREF, payload, i))), listS(PWHEN, var, append(body, NIL)), $list81)), listS(CDOLIST, list(var, data), append(body, $list81))))))));
        }
        cdestructuring_bind_error(datum, $list59);
        return NIL;
    }

    public static final SubLObject map_shelf_alt(SubLObject function, SubLObject shelf, SubLObject message) {
        if (message == UNPROVIDED) {
            message = $str_alt54$do_shelf;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject shelf_var = shelf;
                SubLObject payload = shelf_payload(shelf_var);
                $progress_note$.setDynamicValue(message, thread);
                $progress_start_time$.setDynamicValue(get_universal_time(), thread);
                $progress_total$.setDynamicValue(shelf_approximate_count(shelf_var), thread);
                $progress_sofar$.setDynamicValue(ZERO_INTEGER, thread);
                {
                    SubLObject _prev_bind_0 = $last_percent_progress_index$.currentBinding(thread);
                    SubLObject _prev_bind_1 = $last_percent_progress_prediction$.currentBinding(thread);
                    SubLObject _prev_bind_2 = $within_noting_percent_progress$.currentBinding(thread);
                    SubLObject _prev_bind_3 = $percent_progress_start_time$.currentBinding(thread);
                    try {
                        $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
                        $last_percent_progress_prediction$.bind(NIL, thread);
                        $within_noting_percent_progress$.bind(T, thread);
                        $percent_progress_start_time$.bind(get_universal_time(), thread);
                        noting_percent_progress_preamble($progress_note$.getDynamicValue(thread));
                        if (NIL != shelf_finalized_p(shelf_var)) {
                            if (payload.isVector()) {
                                {
                                    SubLObject cdotimes_end_var = length(payload);
                                    SubLObject i = NIL;
                                    for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                                        {
                                            SubLObject item = aref(payload, i);
                                            if (NIL != item) {
                                                funcall(function, item);
                                            }
                                            note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                                            $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                                        }
                                    }
                                }
                            } else {
                                {
                                    SubLObject cdolist_list_var = cdar(payload);
                                    SubLObject item = NIL;
                                    for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                                        funcall(function, item);
                                        note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                                        $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                                    }
                                }
                                {
                                    SubLObject cdolist_list_var = payload.rest();
                                    SubLObject vector = NIL;
                                    for (vector = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , vector = cdolist_list_var.first()) {
                                        {
                                            SubLObject cdotimes_end_var = length(vector);
                                            SubLObject i = NIL;
                                            for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                                                {
                                                    SubLObject item = aref(vector, i);
                                                    if (NIL != item) {
                                                        funcall(function, item);
                                                    }
                                                    note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                                                    $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            {
                                SubLObject data = payload.rest();
                                if (data.isVector()) {
                                    {
                                        SubLObject cdotimes_end_var = payload.first();
                                        SubLObject i = NIL;
                                        for (i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                                            {
                                                SubLObject item = aref(payload, i);
                                                if (NIL != item) {
                                                    funcall(function, item);
                                                }
                                                note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                                                $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                                            }
                                        }
                                    }
                                } else {
                                    {
                                        SubLObject cdolist_list_var = data;
                                        SubLObject item = NIL;
                                        for (item = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , item = cdolist_list_var.first()) {
                                            funcall(function, item);
                                            note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                                            $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                                        }
                                    }
                                }
                            }
                        }
                        noting_percent_progress_postamble();
                    } finally {
                        $percent_progress_start_time$.rebind(_prev_bind_3, thread);
                        $within_noting_percent_progress$.rebind(_prev_bind_2, thread);
                        $last_percent_progress_prediction$.rebind(_prev_bind_1, thread);
                        $last_percent_progress_index$.rebind(_prev_bind_0, thread);
                    }
                }
            }
            return shelf;
        }
    }

    public static SubLObject map_shelf(final SubLObject function, final SubLObject shelf, SubLObject message) {
        if (message == UNPROVIDED) {
            message = $str60$do_shelf;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject payload = shelf_payload(shelf);
        $progress_note$.setDynamicValue(message, thread);
        $progress_start_time$.setDynamicValue(get_universal_time(), thread);
        $progress_total$.setDynamicValue(shelf_approximate_count(shelf), thread);
        $progress_sofar$.setDynamicValue(ZERO_INTEGER, thread);
        final SubLObject _prev_bind_0 = $last_percent_progress_index$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $last_percent_progress_prediction$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $within_noting_percent_progress$.currentBinding(thread);
        final SubLObject _prev_bind_4 = $percent_progress_start_time$.currentBinding(thread);
        try {
            $last_percent_progress_index$.bind(ZERO_INTEGER, thread);
            $last_percent_progress_prediction$.bind(NIL, thread);
            $within_noting_percent_progress$.bind(T, thread);
            $percent_progress_start_time$.bind(get_universal_time(), thread);
            try {
                noting_percent_progress_preamble($progress_note$.getDynamicValue(thread));
                if (NIL != shelf_finalized_p(shelf)) {
                    if (payload.isVector()) {
                        SubLObject cdotimes_end_var;
                        SubLObject i;
                        SubLObject item;
                        for (cdotimes_end_var = length(payload), i = NIL, i = ZERO_INTEGER; i.numL(cdotimes_end_var); i = add(i, ONE_INTEGER)) {
                            item = aref(payload, i);
                            if (NIL != item) {
                                funcall(function, item);
                            }
                            note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                            $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                        }
                    } else {
                        SubLObject cdolist_list_var = cdar(payload);
                        SubLObject item2 = NIL;
                        item2 = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            funcall(function, item2);
                            note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                            $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                            cdolist_list_var = cdolist_list_var.rest();
                            item2 = cdolist_list_var.first();
                        } 
                        cdolist_list_var = payload.rest();
                        SubLObject vector = NIL;
                        vector = cdolist_list_var.first();
                        while (NIL != cdolist_list_var) {
                            SubLObject cdotimes_end_var2;
                            SubLObject j;
                            SubLObject item3;
                            for (cdotimes_end_var2 = length(vector), j = NIL, j = ZERO_INTEGER; j.numL(cdotimes_end_var2); j = add(j, ONE_INTEGER)) {
                                item3 = aref(vector, j);
                                if (NIL != item3) {
                                    funcall(function, item3);
                                }
                                note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                                $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                            }
                            cdolist_list_var = cdolist_list_var.rest();
                            vector = cdolist_list_var.first();
                        } 
                    }
                } else {
                    final SubLObject data = payload.rest();
                    if (data.isVector()) {
                        SubLObject cdotimes_end_var3;
                        SubLObject k;
                        SubLObject item4;
                        for (cdotimes_end_var3 = payload.first(), k = NIL, k = ZERO_INTEGER; k.numL(cdotimes_end_var3); k = add(k, ONE_INTEGER)) {
                            item4 = aref(payload, k);
                            if (NIL != item4) {
                                funcall(function, item4);
                            }
                            note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                            $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                        }
                    } else {
                        SubLObject cdolist_list_var2 = data;
                        SubLObject item = NIL;
                        item = cdolist_list_var2.first();
                        while (NIL != cdolist_list_var2) {
                            funcall(function, item);
                            note_percent_progress($progress_sofar$.getDynamicValue(thread), $progress_total$.getDynamicValue(thread));
                            $progress_sofar$.setDynamicValue(add($progress_sofar$.getDynamicValue(thread), ONE_INTEGER), thread);
                            cdolist_list_var2 = cdolist_list_var2.rest();
                            item = cdolist_list_var2.first();
                        } 
                    }
                }
            } finally {
                final SubLObject _prev_bind_0_$1 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    noting_percent_progress_postamble();
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$1, thread);
                }
            }
        } finally {
            $percent_progress_start_time$.rebind(_prev_bind_4, thread);
            $within_noting_percent_progress$.rebind(_prev_bind_3, thread);
            $last_percent_progress_prediction$.rebind(_prev_bind_2, thread);
            $last_percent_progress_index$.rebind(_prev_bind_0, thread);
        }
        return shelf;
    }

    public static final SubLObject do_shelf_from_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt81);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject var = NIL;
                    SubLObject shelf = NIL;
                    SubLObject item = NIL;
                    SubLObject forwardp = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt81);
                    var = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt81);
                    shelf = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt81);
                    item = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt81);
                    forwardp = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            SubLObject shelf_var = $sym82$SHELF_VAR;
                            SubLObject vector = $sym83$VECTOR;
                            SubLObject low = $sym84$LOW;
                            SubLObject high = $sym85$HIGH;
                            SubLObject i = $sym86$I;
                            SubLObject length = $sym87$LENGTH;
                            return list(CLET, list(list(shelf_var, shelf), var), list(PUNLESS, list(SHELF_FINALIZED_P, shelf_var), list(FINALIZE_SHELF, shelf_var)), list(PUNLESS, list(VECTORP, list(SHELF_PAYLOAD, shelf_var)), list(REARRANGE_SHELF, shelf_var)), list(CLET, list(list(vector, list(SHELF_PAYLOAD, shelf_var)), list(length, list(LENGTH, vector))), list(CMULTIPLE_VALUE_BIND, list(low, high), list(BSEARCH_SHELF_VECTOR_RANGE, item, vector, list(SHELF_INFO, shelf_var)), list(PIF, forwardp, listS(CDO, list(list(i, high, listS($sym94$_, i, $list_alt95))), list(list(CNOT, list($sym97$_, i, length))), list(CSETF, var, list(AREF, vector, i)), append(body, NIL)), listS(CDO, list(list(i, low, listS($sym99$_, i, $list_alt95))), list(listS($sym97$_, i, $list_alt100)), list(CSETF, var, list(AREF, vector, i)), append(body, NIL))))));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt81);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject do_shelf_from(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list87);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject var = NIL;
        SubLObject shelf = NIL;
        SubLObject item = NIL;
        SubLObject forwardp = NIL;
        destructuring_bind_must_consp(current, datum, $list87);
        var = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list87);
        shelf = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list87);
        item = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list87);
        forwardp = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            final SubLObject shelf_var = $sym88$SHELF_VAR;
            final SubLObject vector = $sym89$VECTOR;
            final SubLObject low = $sym90$LOW;
            final SubLObject high = $sym91$HIGH;
            final SubLObject i = $sym92$I;
            final SubLObject length = $sym93$LENGTH;
            return list(CLET, list(list(shelf_var, shelf), var), list(PUNLESS, list(SHELF_FINALIZED_P, shelf_var), list(FINALIZE_SHELF, shelf_var)), list(PUNLESS, list(VECTORP, list(SHELF_PAYLOAD, shelf_var)), list(REARRANGE_SHELF, shelf_var)), list(CLET, list(list(vector, list(SHELF_PAYLOAD, shelf_var)), list(length, list(LENGTH, vector))), list(CMULTIPLE_VALUE_BIND, list(low, high), list(BSEARCH_SHELF_VECTOR_RANGE, item, vector, list(SHELF_INFO, shelf_var)), list(PIF, forwardp, listS(CDO, list(list(i, high, listS($sym100$_, i, $list101))), list(list(CNOT, list($sym103$_, i, length))), list(CSETF, var, list(AREF, vector, i)), append(body, NIL)), listS(CDO, list(list(i, low, listS($sym105$_, i, $list101))), list(listS($sym103$_, i, $list106)), list(CSETF, var, list(AREF, vector, i)), append(body, NIL))))));
        }
        cdestructuring_bind_error(datum, $list87);
        return NIL;
    }

    public static final SubLObject map_shelf_from_alt(SubLObject function, SubLObject shelf, SubLObject item, SubLObject forwardp) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject shelf_var = shelf;
                SubLObject elt = NIL;
                if (NIL == shelf_finalized_p(shelf_var)) {
                    finalize_shelf(shelf_var);
                }
                if (!shelf_payload(shelf_var).isVector()) {
                    rearrange_shelf(shelf_var);
                }
                {
                    SubLObject vector = shelf_payload(shelf_var);
                    SubLObject length = length(vector);
                    thread.resetMultipleValues();
                    {
                        SubLObject low = bsearch_shelf_vector_range(item, vector, shelf_info(shelf_var));
                        SubLObject high = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (NIL != forwardp) {
                            {
                                SubLObject i = NIL;
                                for (i = high; i.numL(length); i = add(i, ONE_INTEGER)) {
                                    elt = aref(vector, i);
                                    funcall(function, elt);
                                }
                            }
                        } else {
                            {
                                SubLObject i = NIL;
                                for (i = low; !i.numL(ZERO_INTEGER); i = subtract(i, ONE_INTEGER)) {
                                    elt = aref(vector, i);
                                    funcall(function, elt);
                                }
                            }
                        }
                    }
                }
            }
            return shelf;
        }
    }

    public static SubLObject map_shelf_from(final SubLObject function, final SubLObject shelf, final SubLObject item, final SubLObject forwardp) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject elt = NIL;
        if (NIL == shelf_finalized_p(shelf)) {
            finalize_shelf(shelf);
        }
        if (!shelf_payload(shelf).isVector()) {
            rearrange_shelf(shelf);
        }
        final SubLObject vector = shelf_payload(shelf);
        final SubLObject length = length(vector);
        thread.resetMultipleValues();
        final SubLObject low = bsearch_shelf_vector_range(item, vector, shelf_info(shelf));
        final SubLObject high = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != forwardp) {
            SubLObject i;
            for (i = NIL, i = high; i.numL(length); i = add(i, ONE_INTEGER)) {
                elt = aref(vector, i);
                funcall(function, elt);
            }
        } else {
            SubLObject i;
            for (i = NIL, i = low; !i.numL(ZERO_INTEGER); i = subtract(i, ONE_INTEGER)) {
                elt = aref(vector, i);
                funcall(function, elt);
            }
        }
        return shelf;
    }

    public static final SubLObject add_to_shelf_alt(SubLObject item, SubLObject shelf) {
        if (NIL != item) {
            if (NIL != shelf_finalized_p(shelf)) {
                {
                    SubLObject info = shelf_info(shelf);
                    SubLObject payload = shelf_payload(shelf);
                    if (!payload.isCons()) {
                        payload = list(cons(ZERO_INTEGER, NIL), payload);
                        _csetf_shelf_payload(shelf, payload);
                    }
                    rplacd(payload.first(), cons(item, cdar(payload)));
                    rplaca(payload.first(), add(caar(payload), ONE_INTEGER));
                    {
                        SubLObject major = shelf_info_major_limit(info);
                        if (major.isFunction()) {
                            if (NIL != funcall(major, shelf)) {
                                rearrange_shelf(shelf);
                                return item;
                            }
                        } else {
                            if (major.isNumber()) {
                                if (shelf_approximate_count(shelf).numGE(major)) {
                                    rearrange_shelf(shelf);
                                    return item;
                                }
                            }
                        }
                        {
                            SubLObject minor = shelf_info_minor_limit(info);
                            if (minor.isFunction()) {
                                if (NIL != funcall(minor, shelf)) {
                                    tidy_shelf(shelf);
                                    return item;
                                }
                            } else {
                                if (minor.isNumber()) {
                                    if (caar(payload).numGE(minor)) {
                                        tidy_shelf(shelf);
                                        return item;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                initial_add_to_shelf(item, shelf);
            }
        } else {
            Errors.cerror($str_alt101$Ignore_the_addition_, $str_alt102$Attempt_to_add__S_to__S_, item, shelf);
        }
        return item;
    }

    public static SubLObject add_to_shelf(final SubLObject item, final SubLObject shelf) {
        if (NIL != item) {
            if (NIL != shelf_finalized_p(shelf)) {
                final SubLObject info = shelf_info(shelf);
                SubLObject payload = shelf_payload(shelf);
                if (!payload.isCons()) {
                    payload = list(cons(ZERO_INTEGER, NIL), payload);
                    _csetf_shelf_payload(shelf, payload);
                }
                rplacd(payload.first(), cons(item, cdar(payload)));
                rplaca(payload.first(), add(caar(payload), ONE_INTEGER));
                final SubLObject major = shelf_info_major_limit(info);
                if (major.isFunction()) {
                    if (NIL != funcall(major, shelf)) {
                        rearrange_shelf(shelf);
                        return item;
                    }
                } else
                    if (major.isNumber() && shelf_approximate_count(shelf).numGE(major)) {
                        rearrange_shelf(shelf);
                        return item;
                    }

                final SubLObject minor = shelf_info_minor_limit(info);
                if (minor.isFunction()) {
                    if (NIL != funcall(minor, shelf)) {
                        tidy_shelf(shelf);
                        return item;
                    }
                } else
                    if (minor.isNumber() && caar(payload).numGE(minor)) {
                        tidy_shelf(shelf);
                        return item;
                    }

            } else {
                initial_add_to_shelf(item, shelf);
            }
        } else {
            Errors.cerror($str107$Ignore_the_addition_, $str108$Attempt_to_add__S_to__S_, item, shelf);
        }
        return item;
    }

    public static final SubLObject bsearch_shelf_vector_alt(SubLObject item, SubLObject vector, SubLObject info) {
        {
            SubLObject compare = shelf_info_compare(info);
            SubLObject test = shelf_info_test(info);
            SubLObject key = shelf_info_key(info);
            return vector_utilities.binary_search_vector(item, vector, compare, test, key, symbol_function(NULL));
        }
    }

    public static SubLObject bsearch_shelf_vector(final SubLObject item, final SubLObject vector, final SubLObject info) {
        final SubLObject compare = shelf_info_compare(info);
        final SubLObject test = shelf_info_test(info);
        final SubLObject key = shelf_info_key(info);
        return vector_utilities.binary_search_vector(item, vector, compare, test, key, symbol_function(NULL));
    }

    public static final SubLObject bsearch_shelf_vector_range_alt(SubLObject item, SubLObject vector, SubLObject info) {
        {
            SubLObject compare = shelf_info_compare(info);
            SubLObject test = shelf_info_test(info);
            SubLObject key = shelf_info_key(info);
            return vector_utilities.binary_search_vector_range(item, vector, compare, test, key, symbol_function(NULL));
        }
    }

    public static SubLObject bsearch_shelf_vector_range(final SubLObject item, final SubLObject vector, final SubLObject info) {
        final SubLObject compare = shelf_info_compare(info);
        final SubLObject test = shelf_info_test(info);
        final SubLObject key = shelf_info_key(info);
        return vector_utilities.binary_search_vector_range(item, vector, compare, test, key, symbol_function(NULL));
    }

    public static final SubLObject delete_from_shelf_alt(SubLObject item, SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            SubLObject info = shelf_info(shelf);
            if (NIL != shelf_finalized_p(shelf)) {
                if (payload.isVector()) {
                    {
                        SubLObject idx = bsearch_shelf_vector(item, payload, info);
                        if (NIL != idx) {
                            set_aref(payload, idx, NIL);
                            return item;
                        }
                    }
                } else {
                    {
                        SubLObject cdolist_list_var = payload.rest();
                        SubLObject vector = NIL;
                        for (vector = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , vector = cdolist_list_var.first()) {
                            {
                                SubLObject idx = bsearch_shelf_vector(item, vector, info);
                                if (NIL != idx) {
                                    set_aref(vector, idx, NIL);
                                    return item;
                                }
                            }
                        }
                        {
                            SubLObject extras = payload.first();
                            rplacd(extras, delete(item, extras.rest(), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED, UNPROVIDED));
                            rplaca(extras, length(extras.rest()));
                            return item;
                        }
                    }
                }
            } else {
                rplacd(payload, delete(item, payload.rest(), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED, UNPROVIDED));
                rplaca(payload, length(payload.rest()));
                return item;
            }
        }
        return NIL;
    }

    public static SubLObject delete_from_shelf(final SubLObject item, final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        final SubLObject info = shelf_info(shelf);
        if (NIL == shelf_finalized_p(shelf)) {
            rplacd(payload, delete(item, payload.rest(), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED, UNPROVIDED));
            rplaca(payload, length(payload.rest()));
            return item;
        }
        if (!payload.isVector()) {
            SubLObject cdolist_list_var = payload.rest();
            SubLObject vector = NIL;
            vector = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject idx = bsearch_shelf_vector(item, vector, info);
                if (NIL != idx) {
                    set_aref(vector, idx, NIL);
                    return item;
                }
                cdolist_list_var = cdolist_list_var.rest();
                vector = cdolist_list_var.first();
            } 
            final SubLObject extras = payload.first();
            rplacd(extras, delete(item, extras.rest(), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED, UNPROVIDED));
            rplaca(extras, length(extras.rest()));
            return item;
        }
        final SubLObject idx2 = bsearch_shelf_vector(item, payload, info);
        if (NIL != idx2) {
            set_aref(payload, idx2, NIL);
            return item;
        }
        return NIL;
    }

    public static final SubLObject clear_shelf_alt(SubLObject shelf) {
        if (NIL != shelf_finalized_p(shelf)) {
            _csetf_shelf_payload(shelf, cons(cons(ZERO_INTEGER, NIL), NIL));
        } else {
            _csetf_shelf_payload(shelf, cons(ZERO_INTEGER, NIL));
        }
        return shelf;
    }

    public static SubLObject clear_shelf(final SubLObject shelf) {
        if (NIL != shelf_finalized_p(shelf)) {
            _csetf_shelf_payload(shelf, cons(cons(ZERO_INTEGER, NIL), NIL));
        } else {
            _csetf_shelf_payload(shelf, cons(ZERO_INTEGER, NIL));
        }
        return shelf;
    }

    public static final SubLObject find_in_shelf_alt(SubLObject item, SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            SubLObject info = shelf_info(shelf);
            if (NIL != shelf_finalized_p(shelf)) {
                if (payload.isVector()) {
                    {
                        SubLObject idx = bsearch_shelf_vector(item, payload, info);
                        if (NIL != idx) {
                            return aref(payload, idx);
                        } else {
                            return NIL;
                        }
                    }
                } else {
                    {
                        SubLObject cdolist_list_var = payload.rest();
                        SubLObject vector = NIL;
                        for (vector = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , vector = cdolist_list_var.first()) {
                            {
                                SubLObject idx = bsearch_shelf_vector(item, vector, info);
                                if (NIL != idx) {
                                    return aref(vector, idx);
                                }
                            }
                        }
                        return find(item, cdar(payload), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED);
                    }
                }
            } else {
                return find(item, payload.rest(), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED);
            }
        }
    }

    public static SubLObject find_in_shelf(final SubLObject item, final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        final SubLObject info = shelf_info(shelf);
        if (NIL == shelf_finalized_p(shelf)) {
            return find(item, payload.rest(), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED);
        }
        if (!payload.isVector()) {
            SubLObject cdolist_list_var = payload.rest();
            SubLObject vector = NIL;
            vector = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject idx = bsearch_shelf_vector(item, vector, info);
                if (NIL != idx) {
                    return aref(vector, idx);
                }
                cdolist_list_var = cdolist_list_var.rest();
                vector = cdolist_list_var.first();
            } 
            return find(item, cdar(payload), shelf_info_test(info), shelf_info_key(info), UNPROVIDED, UNPROVIDED);
        }
        final SubLObject idx2 = bsearch_shelf_vector(item, payload, info);
        if (NIL != idx2) {
            return aref(payload, idx2);
        }
        return NIL;
    }

    public static final SubLObject shelf_ten_percent_full_p_alt(SubLObject shelf) {
        return numGE(shelf_extra_count(shelf), integerDivide(shelf_major_count(shelf), TEN_INTEGER));
    }

    public static SubLObject shelf_ten_percent_full_p(final SubLObject shelf) {
        return numGE(shelf_extra_count(shelf), integerDivide(shelf_major_count(shelf), TEN_INTEGER));
    }

    public static final SubLObject shelf_full_p_alt(SubLObject shelf) {
        return numGE(shelf_minor_count(shelf), shelf_major_count(shelf));
    }

    public static SubLObject shelf_full_p(final SubLObject shelf) {
        return numGE(shelf_minor_count(shelf), shelf_major_count(shelf));
    }

    public static final SubLObject new_shelf_iterator_alt(SubLObject shelf) {
        SubLTrampolineFile.checkType(shelf, SHELF_P);
        return iteration.new_iterator(make_iterator_shelf_state(shelf), ITERATOR_SHELF_DONE, ITERATOR_SHELF_NEXT, UNPROVIDED);
    }

    public static SubLObject new_shelf_iterator(final SubLObject shelf) {
        assert NIL != shelf_p(shelf) : "! shelfs.shelf_p(shelf) " + ("shelfs.shelf_p(shelf) " + "CommonSymbols.NIL != shelfs.shelf_p(shelf) ") + shelf;
        return iteration.new_iterator(make_iterator_shelf_state(shelf), ITERATOR_SHELF_DONE, ITERATOR_SHELF_NEXT, UNPROVIDED);
    }

    public static final SubLObject make_iterator_shelf_state_alt(SubLObject shelf) {
        {
            SubLObject payload = shelf_payload(shelf);
            SubLObject vectors = NIL;
            if (payload.isVector()) {
                vectors = list(payload);
            } else {
                if (NIL != shelf_finalized_p(shelf)) {
                    vectors = cons(apply(symbol_function(VECTOR), cdar(payload)), copy_list(payload.rest()));
                } else {
                    vectors = list(apply(symbol_function(VECTOR), payload));
                }
            }
            {
                SubLObject initial_iterator = new_shelf_vector_iterator(vectors.first());
                return list(initial_iterator, vectors);
            }
        }
    }

    public static SubLObject make_iterator_shelf_state(final SubLObject shelf) {
        final SubLObject payload = shelf_payload(shelf);
        SubLObject vectors = NIL;
        if (payload.isVector()) {
            vectors = list(payload);
        } else
            if (NIL != shelf_finalized_p(shelf)) {
                vectors = cons(apply(symbol_function(VECTOR), cdar(payload)), copy_list(payload.rest()));
            } else {
                vectors = list(apply(symbol_function(VECTOR), payload));
            }

        final SubLObject initial_iterator = new_shelf_vector_iterator(vectors.first());
        return list(initial_iterator, vectors);
    }

    public static final SubLObject new_shelf_vector_iterator_alt(SubLObject vector) {
        SubLTrampolineFile.checkType(vector, VECTORP);
        return iteration.new_filter_iterator(iteration.new_vector_iterator(vector), symbol_function(BOOLEAN), UNPROVIDED);
    }

    public static SubLObject new_shelf_vector_iterator(final SubLObject vector) {
        assert NIL != vectorp(vector) : "! vectorp(vector) " + ("Types.vectorp(vector) " + "CommonSymbols.NIL != Types.vectorp(vector) ") + vector;
        return iteration.new_filter_iterator(iteration.new_vector_iterator(vector), symbol_function(BOOLEAN), UNPROVIDED);
    }

    public static final SubLObject iterator_shelf_done_alt(SubLObject state) {
        return makeBoolean((NIL != iteration.iteration_done(state.first())) && (NIL == second(state).rest()));
    }

    public static SubLObject iterator_shelf_done(final SubLObject state) {
        return makeBoolean((NIL != iteration.iteration_done(state.first())) && (NIL == second(state).rest()));
    }

    public static final SubLObject iterator_shelf_next_alt(SubLObject state) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject iterator = state.first();
                SubLObject vectors = second(state);
                while (true) {
                    thread.resetMultipleValues();
                    {
                        SubLObject iterator_item = iteration.iteration_next(iterator);
                        SubLObject iterator_valid = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (NIL != iterator_valid) {
                            return values(iterator_item, state, NIL);
                        } else {
                            if (NIL == vectors.rest()) {
                                return values(NIL, state, T);
                            } else {
                                vectors = vectors.rest();
                                set_nth(ONE_INTEGER, state, vectors);
                                iterator = new_shelf_vector_iterator(vectors.first());
                                set_nth(ZERO_INTEGER, state, iterator);
                            }
                        }
                    }
                } 
            }
        }
    }

    public static SubLObject iterator_shelf_next(final SubLObject state) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject iterator = state.first();
        SubLObject vectors = second(state);
        while (true) {
            thread.resetMultipleValues();
            final SubLObject iterator_item = iteration.iteration_next(iterator);
            final SubLObject iterator_valid = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if (NIL != iterator_valid) {
                return values(iterator_item, state, NIL);
            }
            if (NIL == vectors.rest()) {
                return values(NIL, state, T);
            }
            vectors = vectors.rest();
            set_nth(ONE_INTEGER, state, vectors);
            iterator = new_shelf_vector_iterator(vectors.first());
            set_nth(ZERO_INTEGER, state, iterator);
        } 
    }

    public static SubLObject declare_shelfs_file() {
        declareFunction("shelf_info_print_function_trampoline", "SHELF-INFO-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("shelf_info_p", "SHELF-INFO-P", 1, 0, false);
        new shelfs.$shelf_info_p$UnaryFunction();
        declareFunction("shelf_info_key", "SHELF-INFO-KEY", 1, 0, false);
        declareFunction("shelf_info_test", "SHELF-INFO-TEST", 1, 0, false);
        declareFunction("shelf_info_compare", "SHELF-INFO-COMPARE", 1, 0, false);
        declareFunction("shelf_info_minor_limit", "SHELF-INFO-MINOR-LIMIT", 1, 0, false);
        declareFunction("shelf_info_major_limit", "SHELF-INFO-MAJOR-LIMIT", 1, 0, false);
        declareFunction("_csetf_shelf_info_key", "_CSETF-SHELF-INFO-KEY", 2, 0, false);
        declareFunction("_csetf_shelf_info_test", "_CSETF-SHELF-INFO-TEST", 2, 0, false);
        declareFunction("_csetf_shelf_info_compare", "_CSETF-SHELF-INFO-COMPARE", 2, 0, false);
        declareFunction("_csetf_shelf_info_minor_limit", "_CSETF-SHELF-INFO-MINOR-LIMIT", 2, 0, false);
        declareFunction("_csetf_shelf_info_major_limit", "_CSETF-SHELF-INFO-MAJOR-LIMIT", 2, 0, false);
        declareFunction("make_shelf_info", "MAKE-SHELF-INFO", 0, 1, false);
        declareFunction("visit_defstruct_shelf_info", "VISIT-DEFSTRUCT-SHELF-INFO", 2, 0, false);
        declareFunction("visit_defstruct_object_shelf_info_method", "VISIT-DEFSTRUCT-OBJECT-SHELF-INFO-METHOD", 2, 0, false);
        declareFunction("shelf_print_function_trampoline", "SHELF-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("shelf_p", "SHELF-P", 1, 0, false);
        new shelfs.$shelf_p$UnaryFunction();
        declareFunction("shelf_info", "SHELF-INFO", 1, 0, false);
        declareFunction("shelf_payload", "SHELF-PAYLOAD", 1, 0, false);
        declareFunction("shelf_size", "SHELF-SIZE", 1, 0, false);
        declareFunction("_csetf_shelf_info", "_CSETF-SHELF-INFO", 2, 0, false);
        declareFunction("_csetf_shelf_payload", "_CSETF-SHELF-PAYLOAD", 2, 0, false);
        declareFunction("_csetf_shelf_size", "_CSETF-SHELF-SIZE", 2, 0, false);
        declareFunction("make_shelf", "MAKE-SHELF", 0, 1, false);
        declareFunction("visit_defstruct_shelf", "VISIT-DEFSTRUCT-SHELF", 2, 0, false);
        declareFunction("visit_defstruct_object_shelf_method", "VISIT-DEFSTRUCT-OBJECT-SHELF-METHOD", 2, 0, false);
        declareFunction("shelf_finalized_p", "SHELF-FINALIZED-P", 1, 0, false);
        declareFunction("print_shelf", "PRINT-SHELF", 3, 0, false);
        declareFunction("allocate_shelf", "ALLOCATE-SHELF", 2, 0, false);
        declareFunction("initial_add_to_shelf", "INITIAL-ADD-TO-SHELF", 2, 0, false);
        declareFunction("finalize_shelf", "FINALIZE-SHELF", 1, 0, false);
        declareFunction("shelf_count", "SHELF-COUNT", 1, 0, false);
        declareFunction("shelf_approximate_count", "SHELF-APPROXIMATE-COUNT", 1, 0, false);
        declareFunction("shelf_major_count", "SHELF-MAJOR-COUNT", 1, 0, false);
        declareFunction("shelf_minor_count", "SHELF-MINOR-COUNT", 1, 0, false);
        declareFunction("shelf_extra_count", "SHELF-EXTRA-COUNT", 1, 0, false);
        declareFunction("rearrange_shelf", "REARRANGE-SHELF", 1, 0, false);
        declareFunction("tidy_shelf", "TIDY-SHELF", 1, 0, false);
        declareMacro("do_shelf", "DO-SHELF");
        declareFunction("map_shelf", "MAP-SHELF", 2, 1, false);
        declareMacro("do_shelf_from", "DO-SHELF-FROM");
        declareFunction("map_shelf_from", "MAP-SHELF-FROM", 4, 0, false);
        declareFunction("add_to_shelf", "ADD-TO-SHELF", 2, 0, false);
        declareFunction("bsearch_shelf_vector", "BSEARCH-SHELF-VECTOR", 3, 0, false);
        declareFunction("bsearch_shelf_vector_range", "BSEARCH-SHELF-VECTOR-RANGE", 3, 0, false);
        declareFunction("delete_from_shelf", "DELETE-FROM-SHELF", 2, 0, false);
        declareFunction("clear_shelf", "CLEAR-SHELF", 1, 0, false);
        declareFunction("find_in_shelf", "FIND-IN-SHELF", 2, 0, false);
        declareFunction("shelf_ten_percent_full_p", "SHELF-TEN-PERCENT-FULL-P", 1, 0, false);
        declareFunction("shelf_full_p", "SHELF-FULL-P", 1, 0, false);
        declareFunction("new_shelf_iterator", "NEW-SHELF-ITERATOR", 1, 0, false);
        declareFunction("make_iterator_shelf_state", "MAKE-ITERATOR-SHELF-STATE", 1, 0, false);
        declareFunction("new_shelf_vector_iterator", "NEW-SHELF-VECTOR-ITERATOR", 1, 0, false);
        declareFunction("iterator_shelf_done", "ITERATOR-SHELF-DONE", 1, 0, false);
        declareFunction("iterator_shelf_next", "ITERATOR-SHELF-NEXT", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_shelfs_file() {
        defconstant("*DTP-SHELF-INFO*", SHELF_INFO);
        defconstant("*DTP-SHELF*", SHELF);
        return NIL;
    }

    public static SubLObject setup_shelfs_file() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_shelf_info$.getGlobalValue(), symbol_function(SHELF_INFO_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list9);
        def_csetf(SHELF_INFO_KEY, _CSETF_SHELF_INFO_KEY);
        def_csetf(SHELF_INFO_TEST, _CSETF_SHELF_INFO_TEST);
        def_csetf(SHELF_INFO_COMPARE, _CSETF_SHELF_INFO_COMPARE);
        def_csetf(SHELF_INFO_MINOR_LIMIT, _CSETF_SHELF_INFO_MINOR_LIMIT);
        def_csetf(SHELF_INFO_MAJOR_LIMIT, _CSETF_SHELF_INFO_MAJOR_LIMIT);
        identity(SHELF_INFO);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_shelf_info$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_SHELF_INFO_METHOD));
        register_method($print_object_method_table$.getGlobalValue(), $dtp_shelf$.getGlobalValue(), symbol_function(SHELF_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list40);
        def_csetf(SHELF_INFO, _CSETF_SHELF_INFO);
        def_csetf(SHELF_PAYLOAD, _CSETF_SHELF_PAYLOAD);
        def_csetf(SHELF_SIZE, _CSETF_SHELF_SIZE);
        identity(SHELF);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_shelf$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_SHELF_METHOD));
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_shelfs_file();
    }

    @Override
    public void initializeVariables() {
        init_shelfs_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_shelfs_file();
    }

    static {
    }

    public static final class $shelf_info_native extends SubLStructNative {
        public SubLObject $key;

        public SubLObject $test;

        public SubLObject $compare;

        public SubLObject $minor_limit;

        public SubLObject $major_limit;

        private static final SubLStructDeclNative structDecl;

        public $shelf_info_native() {
            shelfs.$shelf_info_native.this.$key = Lisp.NIL;
            shelfs.$shelf_info_native.this.$test = Lisp.NIL;
            shelfs.$shelf_info_native.this.$compare = Lisp.NIL;
            shelfs.$shelf_info_native.this.$minor_limit = Lisp.NIL;
            shelfs.$shelf_info_native.this.$major_limit = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return shelfs.$shelf_info_native.this.$key;
        }

        @Override
        public SubLObject getField3() {
            return shelfs.$shelf_info_native.this.$test;
        }

        @Override
        public SubLObject getField4() {
            return shelfs.$shelf_info_native.this.$compare;
        }

        @Override
        public SubLObject getField5() {
            return shelfs.$shelf_info_native.this.$minor_limit;
        }

        @Override
        public SubLObject getField6() {
            return shelfs.$shelf_info_native.this.$major_limit;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return shelfs.$shelf_info_native.this.$key = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return shelfs.$shelf_info_native.this.$test = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return shelfs.$shelf_info_native.this.$compare = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return shelfs.$shelf_info_native.this.$minor_limit = value;
        }

        @Override
        public SubLObject setField6(final SubLObject value) {
            return shelfs.$shelf_info_native.this.$major_limit = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.shelfs.$shelf_info_native.class, SHELF_INFO, SHELF_INFO_P, $list3, $list4, new String[]{ "$key", "$test", "$compare", "$minor_limit", "$major_limit" }, $list5, $list6, DEFAULT_STRUCT_PRINT_FUNCTION);
        }
    }

    static private final SubLList $list_alt3 = list(makeSymbol("KEY"), makeSymbol("TEST"), makeSymbol("COMPARE"), makeSymbol("MINOR-LIMIT"), makeSymbol("MAJOR-LIMIT"));

    static private final SubLList $list_alt4 = list(makeKeyword("KEY"), $TEST, makeKeyword("COMPARE"), makeKeyword("MINOR-LIMIT"), makeKeyword("MAJOR-LIMIT"));

    static private final SubLList $list_alt5 = list(makeSymbol("SHELF-INFO-KEY"), makeSymbol("SHELF-INFO-TEST"), makeSymbol("SHELF-INFO-COMPARE"), makeSymbol("SHELF-INFO-MINOR-LIMIT"), makeSymbol("SHELF-INFO-MAJOR-LIMIT"));

    static private final SubLList $list_alt6 = list(makeSymbol("_CSETF-SHELF-INFO-KEY"), makeSymbol("_CSETF-SHELF-INFO-TEST"), makeSymbol("_CSETF-SHELF-INFO-COMPARE"), makeSymbol("_CSETF-SHELF-INFO-MINOR-LIMIT"), makeSymbol("_CSETF-SHELF-INFO-MAJOR-LIMIT"));

    public static final class $shelf_info_p$UnaryFunction extends UnaryFunction {
        public $shelf_info_p$UnaryFunction() {
            super(extractFunctionNamed("SHELF-INFO-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return shelf_info_p(arg1);
        }
    }

    public static final class $shelf_native extends SubLStructNative {
        public SubLObject $info;

        public SubLObject $payload;

        public SubLObject $size;

        private static final SubLStructDeclNative structDecl;

        public $shelf_native() {
            shelfs.$shelf_native.this.$info = Lisp.NIL;
            shelfs.$shelf_native.this.$payload = Lisp.NIL;
            shelfs.$shelf_native.this.$size = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return shelfs.$shelf_native.this.$info;
        }

        @Override
        public SubLObject getField3() {
            return shelfs.$shelf_native.this.$payload;
        }

        @Override
        public SubLObject getField4() {
            return shelfs.$shelf_native.this.$size;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return shelfs.$shelf_native.this.$info = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return shelfs.$shelf_native.this.$payload = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return shelfs.$shelf_native.this.$size = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.shelfs.$shelf_native.class, SHELF, SHELF_P, $list34, $list35, new String[]{ "$info", "$payload", "$size" }, $list36, $list37, PRINT_SHELF);
        }
    }

    static private final SubLString $str_alt24$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLList $list_alt28 = list(makeSymbol("INFO"), makeSymbol("PAYLOAD"), makeSymbol("SIZE"));

    static private final SubLList $list_alt29 = list($INFO, makeKeyword("PAYLOAD"), $SIZE);

    static private final SubLList $list_alt30 = list(makeSymbol("SHELF-INFO"), makeSymbol("SHELF-PAYLOAD"), makeSymbol("SHELF-SIZE"));

    static private final SubLList $list_alt31 = list(makeSymbol("_CSETF-SHELF-INFO"), makeSymbol("_CSETF-SHELF-PAYLOAD"), makeSymbol("_CSETF-SHELF-SIZE"));

    public static final class $shelf_p$UnaryFunction extends UnaryFunction {
        public $shelf_p$UnaryFunction() {
            super(extractFunctionNamed("SHELF-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return shelf_p(arg1);
        }
    }

    static private final SubLString $str_alt42$__ = makeString("#<");

    static private final SubLString $str_alt44$_D_items = makeString("~D items");

    static private final SubLString $str_alt45$__unfinalized_ = makeString(" (unfinalized)");

    static private final SubLString $str_alt47$Finalizing__S_before_its_time_ = makeString("Finalizing ~S before its time.");

    static private final SubLString $str_alt48$_S_has_already_been_finalized_ = makeString("~S has already been finalized.");

    static private final SubLString $str_alt49$Shelf__S_being_finalized_with_les = makeString("Shelf ~S being finalized with less than expected ~D items.");

    static private final SubLString $str_alt51$Finalize_it_ = makeString("Finalize it.");

    static private final SubLString $str_alt52$_S_has_not_been_finalized_ = makeString("~S has not been finalized.");

    static private final SubLList $list_alt53 = list(list(makeSymbol("VAR"), makeSymbol("SHELF"), makeSymbol("&OPTIONAL"), list(makeSymbol("MESSAGE"), makeString("do-shelf"))), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLString $str_alt54$do_shelf = makeString("do-shelf");

    static private final SubLSymbol $sym55$SHELF_VAR = makeUninternedSymbol("SHELF-VAR");

    static private final SubLSymbol $sym56$PAYLOAD = makeUninternedSymbol("PAYLOAD");

    static private final SubLSymbol $sym57$I = makeUninternedSymbol("I");

    static private final SubLSymbol $sym58$VECTOR = makeUninternedSymbol("VECTOR");

    static private final SubLSymbol $sym59$DATA = makeUninternedSymbol("DATA");

    static private final SubLList $list_alt63 = list(makeSymbol("CSETQ"), makeSymbol("*PROGRESS-START-TIME*"), list(makeSymbol("GET-UNIVERSAL-TIME")));

    static private final SubLList $list_alt66 = list(makeSymbol("CSETQ"), makeSymbol("*PROGRESS-SOFAR*"), ZERO_INTEGER);

    static private final SubLList $list_alt75 = list(list(makeSymbol("NOTE-PERCENT-PROGRESS"), makeSymbol("*PROGRESS-SOFAR*"), makeSymbol("*PROGRESS-TOTAL*")), list(makeSymbol("CINC"), makeSymbol("*PROGRESS-SOFAR*")));

    static private final SubLList $list_alt81 = list(list(makeSymbol("VAR"), makeSymbol("SHELF"), makeSymbol("ITEM"), makeSymbol("FORWARDP")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym82$SHELF_VAR = makeUninternedSymbol("SHELF-VAR");

    static private final SubLSymbol $sym83$VECTOR = makeUninternedSymbol("VECTOR");

    static private final SubLSymbol $sym84$LOW = makeUninternedSymbol("LOW");

    static private final SubLSymbol $sym85$HIGH = makeUninternedSymbol("HIGH");

    static private final SubLSymbol $sym86$I = makeUninternedSymbol("I");

    static private final SubLSymbol $sym87$LENGTH = makeUninternedSymbol("LENGTH");

    static private final SubLSymbol $sym94$_ = makeSymbol("+");

    static private final SubLList $list_alt95 = list(ONE_INTEGER);

    static private final SubLSymbol $sym97$_ = makeSymbol("<");

    static private final SubLSymbol $sym99$_ = makeSymbol("-");

    static private final SubLList $list_alt100 = list(ZERO_INTEGER);

    static private final SubLString $str_alt101$Ignore_the_addition_ = makeString("Ignore the addition.");

    static private final SubLString $str_alt102$Attempt_to_add__S_to__S_ = makeString("Attempt to add ~S to ~S.");
}

/**
 * Total time: 349 ms
 */
