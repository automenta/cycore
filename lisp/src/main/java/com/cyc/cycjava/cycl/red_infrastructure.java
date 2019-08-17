/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_period;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.apply;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.reverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.set;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_value;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.function_spec_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.keywordp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.third;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import org.armedbear.lisp.Lisp;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLSpecialOperatorDeclarations;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDecl;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDeclNative;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.UnaryFunction;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLStructNative;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      RED-INFRASTRUCTURE
 * source file: /cyc/top/cycl/red-infrastructure.lisp
 * created:     2019/07/03 17:37:15
 */
public final class red_infrastructure extends SubLTranslatedFile implements V12 {
    static private final SubLString $str_alt43$ = makeString("");

    public static final SubLFile me = new red_infrastructure();

 public static final String myName = "com.cyc.cycjava.cycl.red_infrastructure";


    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_red_symbol$ = makeSymbol("*DTP-RED-SYMBOL*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    public static final SubLSymbol $red_variables_dictionary$ = makeSymbol("*RED-VARIABLES-DICTIONARY*");

    public static final SubLSymbol $red_symbols_list$ = makeSymbol("*RED-SYMBOLS-LIST*");

    public static final SubLSymbol $red_keys_dictionary$ = makeSymbol("*RED-KEYS-DICTIONARY*");

    public static final SubLSymbol $red_reload_callback_moniker_dictionary$ = makeSymbol("*RED-RELOAD-CALLBACK-MONIKER-DICTIONARY*");

    public static final SubLSymbol $repositories_loaded$ = makeSymbol("*REPOSITORIES-LOADED*");

    private static final SubLSymbol RED_SYMBOL = makeSymbol("RED-SYMBOL");

    private static final SubLSymbol RED_SYMBOL_P = makeSymbol("RED-SYMBOL-P");

    static private final SubLList $list7 = list(makeSymbol("NAME"), makeSymbol("RED-KEY"), makeSymbol("DEFAULT-VALUE"), makeSymbol("LTYPE"), makeSymbol("SET-FROM-RED"), makeSymbol("VALUETYPE"));

    static private final SubLList $list8 = list($NAME, makeKeyword("RED-KEY"), makeKeyword("DEFAULT-VALUE"), makeKeyword("LTYPE"), makeKeyword("SET-FROM-RED"), makeKeyword("VALUETYPE"));

    static private final SubLList $list9 = list(makeSymbol("RED-SYMBOL-NAME"), makeSymbol("RED-SYMBOL-RED-KEY"), makeSymbol("RED-SYMBOL-DEFAULT-VALUE"), makeSymbol("RED-SYMBOL-LTYPE"), makeSymbol("RED-SYMBOL-SET-FROM-RED"), makeSymbol("RED-SYMBOL-VALUETYPE"));

    static private final SubLList $list10 = list(makeSymbol("_CSETF-RED-SYMBOL-NAME"), makeSymbol("_CSETF-RED-SYMBOL-RED-KEY"), makeSymbol("_CSETF-RED-SYMBOL-DEFAULT-VALUE"), makeSymbol("_CSETF-RED-SYMBOL-LTYPE"), makeSymbol("_CSETF-RED-SYMBOL-SET-FROM-RED"), makeSymbol("_CSETF-RED-SYMBOL-VALUETYPE"));

    private static final SubLSymbol PRINT_RED_SYMBOL = makeSymbol("PRINT-RED-SYMBOL");

    private static final SubLSymbol RED_SYMBOL_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("RED-SYMBOL-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list13 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("RED-SYMBOL-P"));

    private static final SubLSymbol RED_SYMBOL_NAME = makeSymbol("RED-SYMBOL-NAME");

    private static final SubLSymbol _CSETF_RED_SYMBOL_NAME = makeSymbol("_CSETF-RED-SYMBOL-NAME");

    private static final SubLSymbol RED_SYMBOL_RED_KEY = makeSymbol("RED-SYMBOL-RED-KEY");

    private static final SubLSymbol _CSETF_RED_SYMBOL_RED_KEY = makeSymbol("_CSETF-RED-SYMBOL-RED-KEY");

    private static final SubLSymbol RED_SYMBOL_DEFAULT_VALUE = makeSymbol("RED-SYMBOL-DEFAULT-VALUE");

    private static final SubLSymbol _CSETF_RED_SYMBOL_DEFAULT_VALUE = makeSymbol("_CSETF-RED-SYMBOL-DEFAULT-VALUE");

    private static final SubLSymbol RED_SYMBOL_LTYPE = makeSymbol("RED-SYMBOL-LTYPE");

    private static final SubLSymbol _CSETF_RED_SYMBOL_LTYPE = makeSymbol("_CSETF-RED-SYMBOL-LTYPE");

    private static final SubLSymbol RED_SYMBOL_SET_FROM_RED = makeSymbol("RED-SYMBOL-SET-FROM-RED");

    private static final SubLSymbol _CSETF_RED_SYMBOL_SET_FROM_RED = makeSymbol("_CSETF-RED-SYMBOL-SET-FROM-RED");

    private static final SubLSymbol RED_SYMBOL_VALUETYPE = makeSymbol("RED-SYMBOL-VALUETYPE");

    private static final SubLSymbol _CSETF_RED_SYMBOL_VALUETYPE = makeSymbol("_CSETF-RED-SYMBOL-VALUETYPE");

    private static final SubLSymbol $SET_FROM_RED = makeKeyword("SET-FROM-RED");

    private static final SubLString $str32$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_RED_SYMBOL = makeSymbol("MAKE-RED-SYMBOL");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_RED_SYMBOL_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-RED-SYMBOL-METHOD");

    private static final SubLString $str39$__red_symbol_name___s__red_key___ = makeString("#<red-symbol name: ~s  red-key = ~s  default value = ~s ltype = ~s set-from-red = ~s value-type = ~s>");

    private static final SubLString $str40$_s____s__ = makeString("~s = ~s~%");

    private static final SubLString $str47$_ = makeString(".");

    private static final SubLString $str49$ = makeString("");

    private static final SubLList $list52 = list(makeSymbol("MONIKER"), makeSymbol("FUNCSPEC"));

    private static final SubLList $list55 = list(makeSymbol("KEYWORDP"));

    static private final SubLList $list56 = list(makeSymbol("FUNCTION-SPEC-P"));

    private static final SubLSymbol MONIKER_FUN_LIST = makeSymbol("MONIKER-FUN-LIST");

    static private final SubLList $list62 = list(makeSymbol("MONIKER-FUN-LIST"));

    private static final SubLList $list65 = list(list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("*RED-RELOAD-CALLBACK-MONIKER-DICTIONARY*"), makeSymbol("MONIKER"), makeSymbol("MONIKER-FUN-LIST")));

    private static final SubLList $list67 = list(makeSymbol("PROGN"));

    private static final SubLList $list68 = list(makeSymbol("NAME"), makeSymbol("ARGLIST"), makeSymbol("MONIKERS"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLString $str69$RED_reload_callback__A_has_an_inv = makeString("RED reload callback ~A has an invalid arglist of:~%  ~S~%Use DEFINE instead.");

    private static final SubLSymbol MONIKER = makeSymbol("MONIKER");

    private static final SubLSymbol RED_REPOSITORY_REGISTER_RELOAD_CALLBACK = makeSymbol("RED-REPOSITORY-REGISTER-RELOAD-CALLBACK");

    public static final SubLObject red_symbol_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        print_red_symbol(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject red_symbol_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        print_red_symbol(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject red_symbol_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.red_infrastructure.$red_symbol_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject red_symbol_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.red_infrastructure.$red_symbol_native.class ? T : NIL;
    }

    public static final SubLObject red_symbol_name_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.getField2();
    }

    public static SubLObject red_symbol_name(final SubLObject v_object) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject red_symbol_red_key_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.getField3();
    }

    public static SubLObject red_symbol_red_key(final SubLObject v_object) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject red_symbol_default_value_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.getField4();
    }

    public static SubLObject red_symbol_default_value(final SubLObject v_object) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject red_symbol_ltype_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.getField5();
    }

    public static SubLObject red_symbol_ltype(final SubLObject v_object) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject red_symbol_set_from_red_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.getField6();
    }

    public static SubLObject red_symbol_set_from_red(final SubLObject v_object) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject red_symbol_valuetype_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.getField7();
    }

    public static SubLObject red_symbol_valuetype(final SubLObject v_object) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject _csetf_red_symbol_name_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_red_symbol_name(final SubLObject v_object, final SubLObject value) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_red_symbol_red_key_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_red_symbol_red_key(final SubLObject v_object, final SubLObject value) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_red_symbol_default_value_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_red_symbol_default_value(final SubLObject v_object, final SubLObject value) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_red_symbol_ltype_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_red_symbol_ltype(final SubLObject v_object, final SubLObject value) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_red_symbol_set_from_red_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_red_symbol_set_from_red(final SubLObject v_object, final SubLObject value) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_red_symbol_valuetype_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RED_SYMBOL_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_red_symbol_valuetype(final SubLObject v_object, final SubLObject value) {
        assert NIL != red_symbol_p(v_object) : "! red_infrastructure.red_symbol_p(v_object) " + "red_infrastructure.red_symbol_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject make_red_symbol_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.red_infrastructure.$red_symbol_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($NAME)) {
                        _csetf_red_symbol_name(v_new, current_value);
                    } else {
                        if (pcase_var.eql($RED_KEY)) {
                            _csetf_red_symbol_red_key(v_new, current_value);
                        } else {
                            if (pcase_var.eql($DEFAULT_VALUE)) {
                                _csetf_red_symbol_default_value(v_new, current_value);
                            } else {
                                if (pcase_var.eql($LTYPE)) {
                                    _csetf_red_symbol_ltype(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($SET_FROM_RED)) {
                                        _csetf_red_symbol_set_from_red(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($VALUETYPE)) {
                                            _csetf_red_symbol_valuetype(v_new, current_value);
                                        } else {
                                            Errors.error($str_alt31$Invalid_slot__S_for_construction_, current_arg);
                                        }
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

    public static SubLObject make_red_symbol(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.red_infrastructure.$red_symbol_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($NAME)) {
                _csetf_red_symbol_name(v_new, current_value);
            } else
                if (pcase_var.eql($RED_KEY)) {
                    _csetf_red_symbol_red_key(v_new, current_value);
                } else
                    if (pcase_var.eql($DEFAULT_VALUE)) {
                        _csetf_red_symbol_default_value(v_new, current_value);
                    } else
                        if (pcase_var.eql($LTYPE)) {
                            _csetf_red_symbol_ltype(v_new, current_value);
                        } else
                            if (pcase_var.eql($SET_FROM_RED)) {
                                _csetf_red_symbol_set_from_red(v_new, current_value);
                            } else
                                if (pcase_var.eql($VALUETYPE)) {
                                    _csetf_red_symbol_valuetype(v_new, current_value);
                                } else {
                                    Errors.error($str32$Invalid_slot__S_for_construction_, current_arg);
                                }





        }
        return v_new;
    }

    public static SubLObject visit_defstruct_red_symbol(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_RED_SYMBOL, SIX_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $NAME, red_symbol_name(obj));
        funcall(visitor_fn, obj, $SLOT, $RED_KEY, red_symbol_red_key(obj));
        funcall(visitor_fn, obj, $SLOT, $DEFAULT_VALUE, red_symbol_default_value(obj));
        funcall(visitor_fn, obj, $SLOT, $LTYPE, red_symbol_ltype(obj));
        funcall(visitor_fn, obj, $SLOT, $SET_FROM_RED, red_symbol_set_from_red(obj));
        funcall(visitor_fn, obj, $SLOT, $VALUETYPE, red_symbol_valuetype(obj));
        funcall(visitor_fn, obj, $END, MAKE_RED_SYMBOL, SIX_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_red_symbol_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_red_symbol(obj, visitor_fn);
    }

    public static final SubLObject new_red_symbol_alt(SubLObject red_key, SubLObject name, SubLObject defaultval, SubLObject ltype, SubLObject valuetype) {
        if (valuetype == UNPROVIDED) {
            valuetype = $SIMPLE;
        }
        {
            SubLObject red_sym_obj = make_red_symbol(UNPROVIDED);
            _csetf_red_symbol_name(red_sym_obj, name);
            _csetf_red_symbol_red_key(red_sym_obj, red_key);
            _csetf_red_symbol_default_value(red_sym_obj, defaultval);
            _csetf_red_symbol_ltype(red_sym_obj, ltype);
            _csetf_red_symbol_set_from_red(red_sym_obj, NIL);
            _csetf_red_symbol_valuetype(red_sym_obj, valuetype);
            return red_sym_obj;
        }
    }

    public static SubLObject new_red_symbol(final SubLObject red_key, final SubLObject name, final SubLObject defaultval, final SubLObject ltype, SubLObject valuetype) {
        if (valuetype == UNPROVIDED) {
            valuetype = $SIMPLE;
        }
        final SubLObject red_sym_obj = make_red_symbol(UNPROVIDED);
        _csetf_red_symbol_name(red_sym_obj, name);
        _csetf_red_symbol_red_key(red_sym_obj, red_key);
        _csetf_red_symbol_default_value(red_sym_obj, defaultval);
        _csetf_red_symbol_ltype(red_sym_obj, ltype);
        _csetf_red_symbol_set_from_red(red_sym_obj, NIL);
        _csetf_red_symbol_valuetype(red_sym_obj, valuetype);
        return red_sym_obj;
    }

    public static final SubLObject print_red_symbol_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        format(stream, $str_alt33$__red_symbol_name___s__red_key___, new SubLObject[]{ red_symbol_name(v_object), red_symbol_red_key(v_object), red_symbol_default_value(v_object), red_symbol_ltype(v_object), red_symbol_set_from_red(v_object), red_symbol_valuetype(v_object) });
        return v_object;
    }

    public static SubLObject print_red_symbol(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        format(stream, $str39$__red_symbol_name___s__red_key___, new SubLObject[]{ red_symbol_name(v_object), red_symbol_red_key(v_object), red_symbol_default_value(v_object), red_symbol_ltype(v_object), red_symbol_set_from_red(v_object), red_symbol_valuetype(v_object) });
        return v_object;
    }

    public static final SubLObject set_red_symbols_alt() {
        if (NIL != $repositories_loaded$.getGlobalValue()) {
            return NIL;
        }
        $repositories_loaded$.setGlobalValue(T);
        {
            SubLObject cdolist_list_var = red_ordered_var_list();
            SubLObject red_sym = NIL;
            for (red_sym = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , red_sym = cdolist_list_var.first()) {
                red_conditional_set(red_sym);
            }
        }
        return NIL;
    }

    public static SubLObject set_red_symbols() {
        if (NIL != $repositories_loaded$.getGlobalValue()) {
            return NIL;
        }
        $repositories_loaded$.setGlobalValue(T);
        SubLObject cdolist_list_var = red_ordered_var_list();
        SubLObject red_sym = NIL;
        red_sym = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            red_conditional_set(red_sym);
            cdolist_list_var = cdolist_list_var.rest();
            red_sym = cdolist_list_var.first();
        } 
        return NIL;
    }

    public static final SubLObject list_def_red_non_repository_initialized_variables_alt() {
        {
            SubLObject unset_ones = NIL;
            SubLObject cdolist_list_var = red_ordered_var_list();
            SubLObject red_sym = NIL;
            for (red_sym = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , red_sym = cdolist_list_var.first()) {
                if (NIL == red_symbol_set_from_red(red_sym)) {
                    format(T, $str_alt34$_s____s__, red_sym, symbol_value(red_symbol_name(red_sym)));
                    unset_ones = T;
                }
            }
            return unset_ones;
        }
    }

    public static SubLObject list_def_red_non_repository_initialized_variables() {
        SubLObject unset_ones = NIL;
        SubLObject cdolist_list_var = red_ordered_var_list();
        SubLObject red_sym = NIL;
        red_sym = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == red_symbol_set_from_red(red_sym)) {
                format(T, $str40$_s____s__, red_sym, symbol_value(red_symbol_name(red_sym)));
                unset_ones = T;
            }
            cdolist_list_var = cdolist_list_var.rest();
            red_sym = cdolist_list_var.first();
        } 
        return unset_ones;
    }

    public static final SubLObject list_def_red_variables_alt() {
        {
            SubLObject count = ZERO_INTEGER;
            SubLObject cdolist_list_var = red_ordered_var_list();
            SubLObject red_sym = NIL;
            for (red_sym = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , red_sym = cdolist_list_var.first()) {
                format(T, $str_alt34$_s____s__, red_sym, symbol_value(red_symbol_name(red_sym)));
                count = add(count, ONE_INTEGER);
            }
            return count;
        }
    }

    public static SubLObject list_def_red_variables() {
        SubLObject count = ZERO_INTEGER;
        SubLObject cdolist_list_var = red_ordered_var_list();
        SubLObject red_sym = NIL;
        red_sym = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            format(T, $str40$_s____s__, red_sym, symbol_value(red_symbol_name(red_sym)));
            count = add(count, ONE_INTEGER);
            cdolist_list_var = cdolist_list_var.rest();
            red_sym = cdolist_list_var.first();
        } 
        return count;
    }

    public static final SubLObject red_utilities_initialization_alt() {
        {
            SubLObject cdolist_list_var = $red_symbols_list$.getGlobalValue();
            SubLObject red_sym = NIL;
            for (red_sym = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , red_sym = cdolist_list_var.first()) {
                {
                    SubLObject pcase_var = red_symbol_ltype(red_sym);
                    if (pcase_var.eql($PARAMETER) || pcase_var.eql($LEXICAL)) {
                        _csetf_red_symbol_set_from_red(red_sym, NIL);
                    }
                }
            }
        }
        $repositories_loaded$.setGlobalValue(NIL);
        set_red_symbols();
        return NIL;
    }

    public static SubLObject red_utilities_initialization() {
        SubLObject cdolist_list_var = $red_symbols_list$.getGlobalValue();
        SubLObject red_sym = NIL;
        red_sym = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject pcase_var = red_symbol_ltype(red_sym);
            if (pcase_var.eql($PARAMETER) || pcase_var.eql($LEXICAL)) {
                _csetf_red_symbol_set_from_red(red_sym, NIL);
            }
            cdolist_list_var = cdolist_list_var.rest();
            red_sym = cdolist_list_var.first();
        } 
        $repositories_loaded$.setGlobalValue(NIL);
        set_red_symbols();
        return NIL;
    }

    public static final SubLObject register_red_alt(SubLObject red_sym) {
        {
            SubLObject red_sym_q = dictionary.dictionary_lookup($red_variables_dictionary$.getGlobalValue(), red_symbol_name(red_sym), UNPROVIDED);
            if (NIL != red_sym_q) {
                _csetf_red_symbol_default_value(red_sym_q, red_symbol_default_value(red_sym));
                if (!red_symbol_red_key(red_sym_q).equal(red_symbol_red_key(red_sym))) {
                    {
                        SubLObject oldkey = red_symbol_red_key(red_sym_q);
                        SubLObject newkey = red_symbol_red_key(red_sym);
                        SubLObject oldkeydictentry = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), oldkey, UNPROVIDED);
                        SubLObject newkeydictentry = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), newkey, UNPROVIDED);
                        SubLObject newlist = NIL;
                        SubLObject cdolist_list_var = oldkeydictentry;
                        SubLObject elt = NIL;
                        for (elt = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , elt = cdolist_list_var.first()) {
                            if (elt != red_sym_q) {
                                newlist = cons(elt, newlist);
                            }
                        }
                        if (NIL != newlist) {
                            dictionary.dictionary_enter($red_keys_dictionary$.getGlobalValue(), oldkey, newlist);
                        } else {
                            dictionary.dictionary_remove($red_keys_dictionary$.getGlobalValue(), oldkey);
                        }
                        _csetf_red_symbol_red_key(red_sym_q, newkey);
                        if (NIL != newkeydictentry) {
                            newkeydictentry = cons(red_sym_q, newkeydictentry);
                        } else {
                            newkeydictentry = list(red_sym_q);
                        }
                        dictionary.dictionary_enter($red_keys_dictionary$.getGlobalValue(), newkey, newkeydictentry);
                    }
                }
                return NIL;
            }
            $red_symbols_list$.setGlobalValue(cons(red_sym, $red_symbols_list$.getGlobalValue()));
            dictionary.dictionary_enter($red_variables_dictionary$.getGlobalValue(), red_symbol_name(red_sym), red_sym);
            {
                SubLObject newkey = red_symbol_red_key(red_sym);
                SubLObject newkeydictentry = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), newkey, UNPROVIDED);
                if (NIL != newkeydictentry) {
                    newkeydictentry = cons(red_sym, newkeydictentry);
                } else {
                    newkeydictentry = list(red_sym);
                }
                dictionary.dictionary_enter($red_keys_dictionary$.getGlobalValue(), newkey, newkeydictentry);
            }
            return red_sym;
        }
    }

    public static SubLObject register_red(final SubLObject red_sym) {
        final SubLObject red_sym_q = dictionary.dictionary_lookup($red_variables_dictionary$.getGlobalValue(), red_symbol_name(red_sym), UNPROVIDED);
        if (NIL != red_sym_q) {
            _csetf_red_symbol_default_value(red_sym_q, red_symbol_default_value(red_sym));
            if (!red_symbol_red_key(red_sym_q).equal(red_symbol_red_key(red_sym))) {
                final SubLObject oldkey = red_symbol_red_key(red_sym_q);
                final SubLObject newkey = red_symbol_red_key(red_sym);
                final SubLObject oldkeydictentry = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), oldkey, UNPROVIDED);
                SubLObject newkeydictentry = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), newkey, UNPROVIDED);
                SubLObject newlist = NIL;
                SubLObject cdolist_list_var = oldkeydictentry;
                SubLObject elt = NIL;
                elt = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if (!elt.eql(red_sym_q)) {
                        newlist = cons(elt, newlist);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    elt = cdolist_list_var.first();
                } 
                if (NIL != newlist) {
                    dictionary.dictionary_enter($red_keys_dictionary$.getGlobalValue(), oldkey, newlist);
                } else {
                    dictionary.dictionary_remove($red_keys_dictionary$.getGlobalValue(), oldkey);
                }
                _csetf_red_symbol_red_key(red_sym_q, newkey);
                if (NIL != newkeydictentry) {
                    newkeydictentry = cons(red_sym_q, newkeydictentry);
                } else {
                    newkeydictentry = list(red_sym_q);
                }
                dictionary.dictionary_enter($red_keys_dictionary$.getGlobalValue(), newkey, newkeydictentry);
            }
            return NIL;
        }
        $red_symbols_list$.setGlobalValue(cons(red_sym, $red_symbols_list$.getGlobalValue()));
        dictionary.dictionary_enter($red_variables_dictionary$.getGlobalValue(), red_symbol_name(red_sym), red_sym);
        final SubLObject newkey2 = red_symbol_red_key(red_sym);
        SubLObject newkeydictentry2 = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), newkey2, UNPROVIDED);
        if (NIL != newkeydictentry2) {
            newkeydictentry2 = cons(red_sym, newkeydictentry2);
        } else {
            newkeydictentry2 = list(red_sym);
        }
        dictionary.dictionary_enter($red_keys_dictionary$.getGlobalValue(), newkey2, newkeydictentry2);
        return red_sym;
    }

    public static final SubLObject red_conditional_set_alt(SubLObject red_sym) {
        if (NIL != def_red_should_be_set(red_sym)) {
            set(red_symbol_name(red_sym), red_value(red_sym));
            return red_symbol_name(red_sym);
        }
        return NIL;
    }

    public static SubLObject red_conditional_set(final SubLObject red_sym) {
        if (NIL != def_red_should_be_set(red_sym)) {
            set(red_symbol_name(red_sym), red_value(red_sym));
            return red_symbol_name(red_sym);
        }
        return NIL;
    }

    public static final SubLObject red_ordered_var_list_alt() {
        return reverse($red_symbols_list$.getGlobalValue());
    }

    public static SubLObject red_ordered_var_list() {
        return reverse($red_symbols_list$.getGlobalValue());
    }

    public static final SubLObject def_red_should_be_set_alt(SubLObject red_sym) {
        {
            SubLObject pcase_var = red_symbol_ltype(red_sym);
            if (pcase_var.eql($GLOBAL) || pcase_var.eql($VAR)) {
                if (NIL != red_symbol_set_from_red(red_sym)) {
                    return NIL;
                } else {
                    return T;
                }
            } else {
                if (pcase_var.eql($PARAMETER) || pcase_var.eql($LEXICAL)) {
                    return T;
                } else {
                    return NIL;
                }
            }
        }
    }

    public static SubLObject def_red_should_be_set(final SubLObject red_sym) {
        final SubLObject pcase_var = red_symbol_ltype(red_sym);
        if (pcase_var.eql($GLOBAL) || pcase_var.eql($VAR)) {
            if (NIL != red_symbol_set_from_red(red_sym)) {
                return NIL;
            }
            return T;
        } else {
            if (pcase_var.eql($PARAMETER) || pcase_var.eql($LEXICAL)) {
                return T;
            }
            return NIL;
        }
    }

    public static final SubLObject red_value_alt(SubLObject red_sym) {
        return red_symbol_default_value(red_sym);
    }

    public static SubLObject red_value(final SubLObject red_sym) {
        return red_symbol_default_value(red_sym);
    }

    public static final SubLObject red_make_list_alt(SubLObject red_elt) {
        {
            SubLObject retlist = NIL;
            retlist = cons(red_get_relative_key(red_api.red_element_get_key(red_elt)), retlist);
            retlist = cons(red_api.red_element_get_value(red_elt), retlist);
            {
                SubLObject subeltlist = red_api.red_element_get_sub_elements(red_elt);
                SubLObject cdolist_list_var = subeltlist;
                SubLObject elt = NIL;
                for (elt = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , elt = cdolist_list_var.first()) {
                    retlist = cons(red_make_list(elt), retlist);
                }
                return nreverse(retlist);
            }
        }
    }

    public static SubLObject red_make_list(final SubLObject red_elt) {
        SubLObject retlist = NIL;
        retlist = cons(red_get_relative_key(red_api.red_element_get_key(red_elt)), retlist);
        retlist = cons(red_api.red_element_get_value(red_elt), retlist);
        SubLObject cdolist_list_var;
        final SubLObject subeltlist = cdolist_list_var = red_api.red_element_get_sub_elements(red_elt);
        SubLObject elt = NIL;
        elt = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            retlist = cons(red_make_list(elt), retlist);
            cdolist_list_var = cdolist_list_var.rest();
            elt = cdolist_list_var.first();
        } 
        return nreverse(retlist);
    }

    public static final SubLObject red_get_relative_key_alt(SubLObject key) {
        {
            SubLObject index = length(key);
            while (index.numGE(ZERO_INTEGER) && (string_utilities.char_at(key, index) != CHAR_period)) {
                index = subtract(index, ONE_INTEGER);
            } 
            if (index.isNegative()) {
                return key;
            }
            return string_utilities.substring(key, add(index, ONE_INTEGER), UNPROVIDED);
        }
    }

    public static SubLObject red_get_relative_key(final SubLObject key) {
        SubLObject index;
        for (index = length(key); index.numGE(ZERO_INTEGER) && (!string_utilities.char_at(key, index).eql(CHAR_period)); index = subtract(index, ONE_INTEGER)) {
        }
        if (index.isNegative()) {
            return key;
        }
        return string_utilities.substring(key, add(index, ONE_INTEGER), UNPROVIDED);
    }

    public static final SubLObject redu_translate_to_key_alt(SubLObject key_list) {
        {
            SubLObject key_parts = NIL;
            SubLObject notfirst = NIL;
            SubLObject cdolist_list_var = key_list;
            SubLObject v_ke = NIL;
            for (v_ke = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , v_ke = cdolist_list_var.first()) {
                if (NIL != notfirst) {
                    key_parts = cons($str_alt41$_, key_parts);
                } else {
                    notfirst = T;
                }
                key_parts = cons(string_utilities.to_string(v_ke), key_parts);
            }
            if (NIL != key_parts) {
                return apply(symbol_function(CCONCATENATE), nreverse(key_parts));
            }
        }
        return $str_alt43$;
    }

    public static SubLObject redu_translate_to_key(final SubLObject key_list) {
        SubLObject key_parts = NIL;
        SubLObject notfirst = NIL;
        SubLObject cdolist_list_var = key_list;
        SubLObject v_ke = NIL;
        v_ke = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != notfirst) {
                key_parts = cons($str47$_, key_parts);
            } else {
                notfirst = T;
            }
            key_parts = cons(string_utilities.to_string(v_ke), key_parts);
            cdolist_list_var = cdolist_list_var.rest();
            v_ke = cdolist_list_var.first();
        } 
        if (NIL != key_parts) {
            return apply(symbol_function(CCONCATENATE), nreverse(key_parts));
        }
        return $str49$;
    }

    public static final SubLObject red_reload_repository_alt(SubLObject moniker, SubLObject filename) {
        SubLTrampolineFile.checkType(moniker, KEYWORDP);
        SubLTrampolineFile.checkType(filename, STRINGP);
        red_api.red_reload_repository_simple(filename);
        {
            SubLObject rr = red_api.red_lookup_repository_struct_by_filename(filename);
            if (NIL == rr) {
                return NIL;
            }
            {
                SubLObject updated_symbol_triples = red_api.red_get_all_elements_of_repository(rr);
                red_update_def_red_from_repository(updated_symbol_triples);
                red_execute_callbacks(moniker, updated_symbol_triples);
                return rr;
            }
        }
    }

    public static SubLObject red_reload_repository(final SubLObject moniker, final SubLObject filename) {
        assert NIL != keywordp(moniker) : "! keywordp(moniker) " + ("Types.keywordp(moniker) " + "CommonSymbols.NIL != Types.keywordp(moniker) ") + moniker;
        assert NIL != stringp(filename) : "! stringp(filename) " + ("Types.stringp(filename) " + "CommonSymbols.NIL != Types.stringp(filename) ") + filename;
        red_api.red_reload_repository_simple(filename);
        final SubLObject rr = red_api.red_lookup_repository_struct_by_filename(filename);
        if (NIL == rr) {
            return NIL;
        }
        final SubLObject updated_symbol_triples = red_api.red_get_all_elements_of_repository(rr);
        red_update_def_red_from_repository(updated_symbol_triples);
        red_execute_callbacks(moniker, updated_symbol_triples);
        return rr;
    }

    public static final SubLObject red_update_def_red_from_repository_alt(SubLObject updated_symbol_triples) {
        {
            SubLObject cdolist_list_var = updated_symbol_triples;
            SubLObject elt = NIL;
            for (elt = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , elt = cdolist_list_var.first()) {
                {
                    SubLObject key = elt.first();
                    SubLObject value = second(elt);
                    SubLObject type = third(elt);
                    SubLObject reddictelt = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), key, UNPROVIDED);
                    if (NIL != reddictelt) {
                        {
                            SubLObject cdolist_list_var_1 = reddictelt;
                            SubLObject redelt = NIL;
                            for (redelt = cdolist_list_var_1.first(); NIL != cdolist_list_var_1; cdolist_list_var_1 = cdolist_list_var_1.rest() , redelt = cdolist_list_var_1.first()) {
                                _csetf_red_symbol_default_value(redelt, value);
                                _csetf_red_symbol_ltype(redelt, type);
                                _csetf_red_symbol_set_from_red(redelt, T);
                                set(red_symbol_name(redelt), value);
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject red_update_def_red_from_repository(final SubLObject updated_symbol_triples) {
        SubLObject cdolist_list_var = updated_symbol_triples;
        SubLObject elt = NIL;
        elt = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final SubLObject key = elt.first();
            final SubLObject value = second(elt);
            final SubLObject type = third(elt);
            final SubLObject reddictelt = dictionary.dictionary_lookup($red_keys_dictionary$.getGlobalValue(), key, UNPROVIDED);
            if (NIL != reddictelt) {
                SubLObject cdolist_list_var_$1 = reddictelt;
                SubLObject redelt = NIL;
                redelt = cdolist_list_var_$1.first();
                while (NIL != cdolist_list_var_$1) {
                    _csetf_red_symbol_default_value(redelt, value);
                    _csetf_red_symbol_ltype(redelt, type);
                    _csetf_red_symbol_set_from_red(redelt, T);
                    set(red_symbol_name(redelt), value);
                    cdolist_list_var_$1 = cdolist_list_var_$1.rest();
                    redelt = cdolist_list_var_$1.first();
                } 
            }
            cdolist_list_var = cdolist_list_var.rest();
            elt = cdolist_list_var.first();
        } 
        return NIL;
    }

    public static final SubLObject red_execute_callbacks_alt(SubLObject moniker, SubLObject updated_symbol_triples) {
        {
            SubLObject moniker_fun_list = dictionary.dictionary_lookup($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker, UNPROVIDED);
            if (NIL == moniker_fun_list) {
                return NIL;
            }
            {
                SubLObject cdolist_list_var = moniker_fun_list;
                SubLObject funcspec = NIL;
                for (funcspec = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , funcspec = cdolist_list_var.first()) {
                    funcall(funcspec, moniker, updated_symbol_triples);
                }
            }
            return NIL;
        }
    }

    public static SubLObject red_execute_callbacks(final SubLObject moniker, final SubLObject updated_symbol_triples) {
        final SubLObject moniker_fun_list = dictionary.dictionary_lookup($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker, UNPROVIDED);
        if (NIL == moniker_fun_list) {
            return NIL;
        }
        SubLObject cdolist_list_var = moniker_fun_list;
        SubLObject funcspec = NIL;
        funcspec = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            funcall(funcspec, moniker, updated_symbol_triples);
            cdolist_list_var = cdolist_list_var.rest();
            funcspec = cdolist_list_var.first();
        } 
        return NIL;
    }

    public static final SubLObject red_repository_register_reload_callback_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject moniker = NIL;
            SubLObject funcspec = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt46);
            moniker = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt46);
            funcspec = current.first();
            current = current.rest();
            if (NIL == current) {
                return list(PROGN, listS(CHECK_TYPE, moniker, $list_alt49), listS(CHECK_TYPE, funcspec, $list_alt50), listS(CLET, list(list(MONIKER_FUN_LIST, list(DICTIONARY_LOOKUP, $red_reload_callback_moniker_dictionary$, moniker))), list(PIF, MONIKER_FUN_LIST, listS(CPUSH, funcspec, $list_alt56), list(CSETQ, MONIKER_FUN_LIST, list(LIST, funcspec))), $list_alt59));
            } else {
                cdestructuring_bind_error(datum, $list_alt46);
            }
        }
        return NIL;
    }

    public static SubLObject red_repository_register_reload_callback(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject moniker = NIL;
        SubLObject funcspec = NIL;
        destructuring_bind_must_consp(current, datum, $list52);
        moniker = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list52);
        funcspec = current.first();
        current = current.rest();
        if (NIL == current) {
            return list(PROGN, listS(CHECK_TYPE, moniker, $list55), listS(CHECK_TYPE, funcspec, $list56), listS(CLET, list(list(MONIKER_FUN_LIST, list(DICTIONARY_LOOKUP, $red_reload_callback_moniker_dictionary$, moniker))), list(PIF, MONIKER_FUN_LIST, listS(CPUSH, funcspec, $list62), list(CSETQ, MONIKER_FUN_LIST, list(LIST, funcspec))), $list65));
        }
        cdestructuring_bind_error(datum, $list52);
        return NIL;
    }

    public static final SubLObject red_repository_unregister_reload_callback_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject moniker = NIL;
            SubLObject funcspec = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt46);
            moniker = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt46);
            funcspec = current.first();
            current = current.rest();
            if (NIL == current) {
                SubLTrampolineFile.checkType(moniker, KEYWORDP);
                SubLTrampolineFile.checkType(funcspec, FUNCTION_SPEC_P);
                {
                    SubLObject moniker_fun_list = dictionary.dictionary_lookup($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker, UNPROVIDED);
                    SubLObject newlist = NIL;
                    if (NIL == moniker_fun_list) {
                        return $list_alt61;
                    }
                    {
                        SubLObject cdolist_list_var = moniker_fun_list;
                        SubLObject spec = NIL;
                        for (spec = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , spec = cdolist_list_var.first()) {
                            if (!spec.equal(funcspec)) {
                                newlist = cons(spec, newlist);
                            }
                        }
                    }
                    if (NIL == newlist) {
                        dictionary.dictionary_remove($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker);
                        return $list_alt61;
                    }
                    dictionary.dictionary_enter($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker, nreverse(newlist));
                }
                return $list_alt61;
            } else {
                cdestructuring_bind_error(datum, $list_alt46);
            }
        }
        return NIL;
    }

    public static SubLObject red_repository_unregister_reload_callback(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject moniker = NIL;
        SubLObject funcspec = NIL;
        destructuring_bind_must_consp(current, datum, $list52);
        moniker = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list52);
        funcspec = current.first();
        current = current.rest();
        if (NIL != current) {
            cdestructuring_bind_error(datum, $list52);
            return NIL;
        }
        assert NIL != keywordp(moniker) : "! keywordp(moniker) " + ("Types.keywordp(moniker) " + "CommonSymbols.NIL != Types.keywordp(moniker) ") + moniker;
        assert NIL != function_spec_p(funcspec) : "! function_spec_p(funcspec) " + ("Types.function_spec_p(funcspec) " + "CommonSymbols.NIL != Types.function_spec_p(funcspec) ") + funcspec;
        final SubLObject moniker_fun_list = dictionary.dictionary_lookup($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker, UNPROVIDED);
        SubLObject newlist = NIL;
        if (NIL == moniker_fun_list) {
            return $list67;
        }
        SubLObject cdolist_list_var = moniker_fun_list;
        SubLObject spec = NIL;
        spec = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (!spec.equal(funcspec)) {
                newlist = cons(spec, newlist);
            }
            cdolist_list_var = cdolist_list_var.rest();
            spec = cdolist_list_var.first();
        } 
        if (NIL == newlist) {
            dictionary.dictionary_remove($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker);
            return $list67;
        }
        dictionary.dictionary_enter($red_reload_callback_moniker_dictionary$.getGlobalValue(), moniker, nreverse(newlist));
        return $list67;
    }

    /**
     * Define a function to be a RED repository reload function
     */
    @LispMethod(comment = "Define a function to be a RED repository reload function")
    public static final SubLObject red_reload_callback_define_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject name = NIL;
            SubLObject arglist = NIL;
            SubLObject monikers = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt62);
            name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt62);
            arglist = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt62);
            monikers = current.first();
            current = current.rest();
            {
                SubLObject body = current;
                SubLObject max_args = list_utilities.function_arglist_max_args(arglist);
                if (!max_args.numE(TWO_INTEGER)) {
                    Errors.warn($str_alt63$RED_reload_callback__A_has_an_inv, name, arglist);
                }
                return list(PROGN, listS(DEFINE, name, arglist, append(body, NIL)), list(CDOLIST, list(MONIKER, monikers), list(RED_REPOSITORY_REGISTER_RELOAD_CALLBACK, MONIKER, list(QUOTE, name))));
            }
        }
    }

    /**
     * Define a function to be a RED repository reload function
     */
    @LispMethod(comment = "Define a function to be a RED repository reload function")
    public static SubLObject red_reload_callback_define(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject name = NIL;
        SubLObject arglist = NIL;
        SubLObject monikers = NIL;
        destructuring_bind_must_consp(current, datum, $list68);
        name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list68);
        arglist = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list68);
        monikers = current.first();
        final SubLObject body;
        current = body = current.rest();
        final SubLObject max_args = list_utilities.function_arglist_max_args(arglist);
        if (!max_args.numE(TWO_INTEGER)) {
            Errors.warn($str69$RED_reload_callback__A_has_an_inv, name, arglist);
        }
        return list(PROGN, listS(DEFINE, name, arglist, append(body, NIL)), list(CDOLIST, list(MONIKER, monikers), list(RED_REPOSITORY_REGISTER_RELOAD_CALLBACK, MONIKER, list(QUOTE, name))));
    }

    public static SubLObject declare_red_infrastructure_file() {
        declareFunction("red_symbol_print_function_trampoline", "RED-SYMBOL-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("red_symbol_p", "RED-SYMBOL-P", 1, 0, false);
        new red_infrastructure.$red_symbol_p$UnaryFunction();
        declareFunction("red_symbol_name", "RED-SYMBOL-NAME", 1, 0, false);
        declareFunction("red_symbol_red_key", "RED-SYMBOL-RED-KEY", 1, 0, false);
        declareFunction("red_symbol_default_value", "RED-SYMBOL-DEFAULT-VALUE", 1, 0, false);
        declareFunction("red_symbol_ltype", "RED-SYMBOL-LTYPE", 1, 0, false);
        declareFunction("red_symbol_set_from_red", "RED-SYMBOL-SET-FROM-RED", 1, 0, false);
        declareFunction("red_symbol_valuetype", "RED-SYMBOL-VALUETYPE", 1, 0, false);
        declareFunction("_csetf_red_symbol_name", "_CSETF-RED-SYMBOL-NAME", 2, 0, false);
        declareFunction("_csetf_red_symbol_red_key", "_CSETF-RED-SYMBOL-RED-KEY", 2, 0, false);
        declareFunction("_csetf_red_symbol_default_value", "_CSETF-RED-SYMBOL-DEFAULT-VALUE", 2, 0, false);
        declareFunction("_csetf_red_symbol_ltype", "_CSETF-RED-SYMBOL-LTYPE", 2, 0, false);
        declareFunction("_csetf_red_symbol_set_from_red", "_CSETF-RED-SYMBOL-SET-FROM-RED", 2, 0, false);
        declareFunction("_csetf_red_symbol_valuetype", "_CSETF-RED-SYMBOL-VALUETYPE", 2, 0, false);
        declareFunction("make_red_symbol", "MAKE-RED-SYMBOL", 0, 1, false);
        declareFunction("visit_defstruct_red_symbol", "VISIT-DEFSTRUCT-RED-SYMBOL", 2, 0, false);
        declareFunction("visit_defstruct_object_red_symbol_method", "VISIT-DEFSTRUCT-OBJECT-RED-SYMBOL-METHOD", 2, 0, false);
        declareFunction("new_red_symbol", "NEW-RED-SYMBOL", 4, 1, false);
        declareFunction("print_red_symbol", "PRINT-RED-SYMBOL", 3, 0, false);
        declareFunction("set_red_symbols", "SET-RED-SYMBOLS", 0, 0, false);
        declareFunction("list_def_red_non_repository_initialized_variables", "LIST-DEF-RED-NON-REPOSITORY-INITIALIZED-VARIABLES", 0, 0, false);
        declareFunction("list_def_red_variables", "LIST-DEF-RED-VARIABLES", 0, 0, false);
        declareFunction("red_utilities_initialization", "RED-UTILITIES-INITIALIZATION", 0, 0, false);
        declareFunction("register_red", "REGISTER-RED", 1, 0, false);
        declareFunction("red_conditional_set", "RED-CONDITIONAL-SET", 1, 0, false);
        declareFunction("red_ordered_var_list", "RED-ORDERED-VAR-LIST", 0, 0, false);
        declareFunction("def_red_should_be_set", "DEF-RED-SHOULD-BE-SET", 1, 0, false);
        declareFunction("red_value", "RED-VALUE", 1, 0, false);
        declareFunction("red_make_list", "RED-MAKE-LIST", 1, 0, false);
        declareFunction("red_get_relative_key", "RED-GET-RELATIVE-KEY", 1, 0, false);
        declareFunction("redu_translate_to_key", "REDU-TRANSLATE-TO-KEY", 1, 0, false);
        declareFunction("red_reload_repository", "RED-RELOAD-REPOSITORY", 2, 0, false);
        declareFunction("red_update_def_red_from_repository", "RED-UPDATE-DEF-RED-FROM-REPOSITORY", 1, 0, false);
        declareFunction("red_execute_callbacks", "RED-EXECUTE-CALLBACKS", 2, 0, false);
        declareMacro("red_repository_register_reload_callback", "RED-REPOSITORY-REGISTER-RELOAD-CALLBACK");
        declareMacro("red_repository_unregister_reload_callback", "RED-REPOSITORY-UNREGISTER-RELOAD-CALLBACK");
        declareMacro("red_reload_callback_define", "RED-RELOAD-CALLBACK-DEFINE");
        return NIL;
    }

    static private final SubLList $list_alt7 = list(makeSymbol("NAME"), makeSymbol("RED-KEY"), makeSymbol("DEFAULT-VALUE"), makeSymbol("LTYPE"), makeSymbol("SET-FROM-RED"), makeSymbol("VALUETYPE"));

    static private final SubLList $list_alt8 = list($NAME, makeKeyword("RED-KEY"), makeKeyword("DEFAULT-VALUE"), makeKeyword("LTYPE"), makeKeyword("SET-FROM-RED"), makeKeyword("VALUETYPE"));

    static private final SubLList $list_alt9 = list(makeSymbol("RED-SYMBOL-NAME"), makeSymbol("RED-SYMBOL-RED-KEY"), makeSymbol("RED-SYMBOL-DEFAULT-VALUE"), makeSymbol("RED-SYMBOL-LTYPE"), makeSymbol("RED-SYMBOL-SET-FROM-RED"), makeSymbol("RED-SYMBOL-VALUETYPE"));

    public static final SubLObject init_red_infrastructure_file_alt() {
        deflexical("*RED-VARIABLES-DICTIONARY*", NIL != boundp($red_variables_dictionary$) ? ((SubLObject) ($red_variables_dictionary$.getGlobalValue())) : dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        deflexical("*RED-SYMBOLS-LIST*", NIL != boundp($red_symbols_list$) ? ((SubLObject) ($red_symbols_list$.getGlobalValue())) : NIL);
        deflexical("*RED-KEYS-DICTIONARY*", NIL != boundp($red_keys_dictionary$) ? ((SubLObject) ($red_keys_dictionary$.getGlobalValue())) : dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        deflexical("*RED-RELOAD-CALLBACK-MONIKER-DICTIONARY*", NIL != boundp($red_reload_callback_moniker_dictionary$) ? ((SubLObject) ($red_reload_callback_moniker_dictionary$.getGlobalValue())) : dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
        deflexical("*REPOSITORIES-LOADED*", NIL != boundp($repositories_loaded$) ? ((SubLObject) ($repositories_loaded$.getGlobalValue())) : NIL);
        defconstant("*DTP-RED-SYMBOL*", RED_SYMBOL);
        return NIL;
    }

    public static SubLObject init_red_infrastructure_file() {
        if (SubLFiles.USE_V1) {
            deflexical("*RED-VARIABLES-DICTIONARY*", SubLTrampolineFile.maybeDefault($red_variables_dictionary$, $red_variables_dictionary$, () -> dictionary.new_dictionary(UNPROVIDED, UNPROVIDED)));
            deflexical("*RED-SYMBOLS-LIST*", SubLTrampolineFile.maybeDefault($red_symbols_list$, $red_symbols_list$, NIL));
            deflexical("*RED-KEYS-DICTIONARY*", SubLTrampolineFile.maybeDefault($red_keys_dictionary$, $red_keys_dictionary$, () -> dictionary.new_dictionary(UNPROVIDED, UNPROVIDED)));
            deflexical("*RED-RELOAD-CALLBACK-MONIKER-DICTIONARY*", SubLTrampolineFile.maybeDefault($red_reload_callback_moniker_dictionary$, $red_reload_callback_moniker_dictionary$, () -> dictionary.new_dictionary(UNPROVIDED, UNPROVIDED)));
            deflexical("*REPOSITORIES-LOADED*", SubLTrampolineFile.maybeDefault($repositories_loaded$, $repositories_loaded$, NIL));
            defconstant("*DTP-RED-SYMBOL*", RED_SYMBOL);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*RED-VARIABLES-DICTIONARY*", NIL != boundp($red_variables_dictionary$) ? ((SubLObject) ($red_variables_dictionary$.getGlobalValue())) : dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            deflexical("*RED-SYMBOLS-LIST*", NIL != boundp($red_symbols_list$) ? ((SubLObject) ($red_symbols_list$.getGlobalValue())) : NIL);
            deflexical("*RED-KEYS-DICTIONARY*", NIL != boundp($red_keys_dictionary$) ? ((SubLObject) ($red_keys_dictionary$.getGlobalValue())) : dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            deflexical("*RED-RELOAD-CALLBACK-MONIKER-DICTIONARY*", NIL != boundp($red_reload_callback_moniker_dictionary$) ? ((SubLObject) ($red_reload_callback_moniker_dictionary$.getGlobalValue())) : dictionary.new_dictionary(UNPROVIDED, UNPROVIDED));
            deflexical("*REPOSITORIES-LOADED*", NIL != boundp($repositories_loaded$) ? ((SubLObject) ($repositories_loaded$.getGlobalValue())) : NIL);
        }
        return NIL;
    }

    public static SubLObject init_red_infrastructure_file_Previous() {
        deflexical("*RED-VARIABLES-DICTIONARY*", SubLTrampolineFile.maybeDefault($red_variables_dictionary$, $red_variables_dictionary$, () -> dictionary.new_dictionary(UNPROVIDED, UNPROVIDED)));
        deflexical("*RED-SYMBOLS-LIST*", SubLTrampolineFile.maybeDefault($red_symbols_list$, $red_symbols_list$, NIL));
        deflexical("*RED-KEYS-DICTIONARY*", SubLTrampolineFile.maybeDefault($red_keys_dictionary$, $red_keys_dictionary$, () -> dictionary.new_dictionary(UNPROVIDED, UNPROVIDED)));
        deflexical("*RED-RELOAD-CALLBACK-MONIKER-DICTIONARY*", SubLTrampolineFile.maybeDefault($red_reload_callback_moniker_dictionary$, $red_reload_callback_moniker_dictionary$, () -> dictionary.new_dictionary(UNPROVIDED, UNPROVIDED)));
        deflexical("*REPOSITORIES-LOADED*", SubLTrampolineFile.maybeDefault($repositories_loaded$, $repositories_loaded$, NIL));
        defconstant("*DTP-RED-SYMBOL*", RED_SYMBOL);
        return NIL;
    }

    static private final SubLList $list_alt10 = list(makeSymbol("_CSETF-RED-SYMBOL-NAME"), makeSymbol("_CSETF-RED-SYMBOL-RED-KEY"), makeSymbol("_CSETF-RED-SYMBOL-DEFAULT-VALUE"), makeSymbol("_CSETF-RED-SYMBOL-LTYPE"), makeSymbol("_CSETF-RED-SYMBOL-SET-FROM-RED"), makeSymbol("_CSETF-RED-SYMBOL-VALUETYPE"));

    public static SubLObject setup_red_infrastructure_file() {
        declare_defglobal($red_variables_dictionary$);
        declare_defglobal($red_symbols_list$);
        declare_defglobal($red_keys_dictionary$);
        declare_defglobal($red_reload_callback_moniker_dictionary$);
        declare_defglobal($repositories_loaded$);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_red_symbol$.getGlobalValue(), symbol_function(RED_SYMBOL_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list13);
        def_csetf(RED_SYMBOL_NAME, _CSETF_RED_SYMBOL_NAME);
        def_csetf(RED_SYMBOL_RED_KEY, _CSETF_RED_SYMBOL_RED_KEY);
        def_csetf(RED_SYMBOL_DEFAULT_VALUE, _CSETF_RED_SYMBOL_DEFAULT_VALUE);
        def_csetf(RED_SYMBOL_LTYPE, _CSETF_RED_SYMBOL_LTYPE);
        def_csetf(RED_SYMBOL_SET_FROM_RED, _CSETF_RED_SYMBOL_SET_FROM_RED);
        def_csetf(RED_SYMBOL_VALUETYPE, _CSETF_RED_SYMBOL_VALUETYPE);
        identity(RED_SYMBOL);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_red_symbol$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_RED_SYMBOL_METHOD));
        return NIL;
    }

    static private final SubLString $str_alt31$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    @Override
    public void declareFunctions() {
        declare_red_infrastructure_file();
    }

    static private final SubLString $str_alt33$__red_symbol_name___s__red_key___ = makeString("#<red-symbol name: ~s  red-key = ~s  default value = ~s ltype = ~s set-from-red = ~s value-type = ~s>");

    @Override
    public void initializeVariables() {
        init_red_infrastructure_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_red_infrastructure_file();
    }

    static private final SubLString $str_alt34$_s____s__ = makeString("~s = ~s~%");

    static {
    }

    static private final SubLString $str_alt41$_ = makeString(".");

    static private final SubLList $list_alt46 = list(makeSymbol("MONIKER"), makeSymbol("FUNCSPEC"));

    public static final class $red_symbol_native extends SubLStructNative {
        public SubLObject $name;

        public SubLObject $red_key;

        public SubLObject $default_value;

        public SubLObject $ltype;

        public SubLObject $set_from_red;

        public SubLObject $valuetype;

        private static final SubLStructDeclNative structDecl;

        public $red_symbol_native() {
            red_infrastructure.$red_symbol_native.this.$name = Lisp.NIL;
            red_infrastructure.$red_symbol_native.this.$red_key = Lisp.NIL;
            red_infrastructure.$red_symbol_native.this.$default_value = Lisp.NIL;
            red_infrastructure.$red_symbol_native.this.$ltype = Lisp.NIL;
            red_infrastructure.$red_symbol_native.this.$set_from_red = Lisp.NIL;
            red_infrastructure.$red_symbol_native.this.$valuetype = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return red_infrastructure.$red_symbol_native.this.$name;
        }

        @Override
        public SubLObject getField3() {
            return red_infrastructure.$red_symbol_native.this.$red_key;
        }

        @Override
        public SubLObject getField4() {
            return red_infrastructure.$red_symbol_native.this.$default_value;
        }

        @Override
        public SubLObject getField5() {
            return red_infrastructure.$red_symbol_native.this.$ltype;
        }

        @Override
        public SubLObject getField6() {
            return red_infrastructure.$red_symbol_native.this.$set_from_red;
        }

        @Override
        public SubLObject getField7() {
            return red_infrastructure.$red_symbol_native.this.$valuetype;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return red_infrastructure.$red_symbol_native.this.$name = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return red_infrastructure.$red_symbol_native.this.$red_key = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return red_infrastructure.$red_symbol_native.this.$default_value = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return red_infrastructure.$red_symbol_native.this.$ltype = value;
        }

        @Override
        public SubLObject setField6(final SubLObject value) {
            return red_infrastructure.$red_symbol_native.this.$set_from_red = value;
        }

        @Override
        public SubLObject setField7(final SubLObject value) {
            return red_infrastructure.$red_symbol_native.this.$valuetype = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.red_infrastructure.$red_symbol_native.class, RED_SYMBOL, RED_SYMBOL_P, $list7, $list8, new String[]{ "$name", "$red_key", "$default_value", "$ltype", "$set_from_red", "$valuetype" }, $list9, $list10, PRINT_RED_SYMBOL);
        }
    }

    static private final SubLList $list_alt49 = list(makeSymbol("KEYWORDP"));

    static private final SubLList $list_alt50 = list(makeSymbol("FUNCTION-SPEC-P"));

    static private final SubLList $list_alt56 = list(makeSymbol("MONIKER-FUN-LIST"));

    static private final SubLList $list_alt59 = list(list(makeSymbol("DICTIONARY-ENTER"), makeSymbol("*RED-RELOAD-CALLBACK-MONIKER-DICTIONARY*"), makeSymbol("MONIKER"), makeSymbol("MONIKER-FUN-LIST")));

    static private final SubLList $list_alt61 = list(makeSymbol("PROGN"));

    static private final SubLList $list_alt62 = list(makeSymbol("NAME"), makeSymbol("ARGLIST"), makeSymbol("MONIKERS"), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLString $str_alt63$RED_reload_callback__A_has_an_inv = makeString("RED reload callback ~A has an invalid arglist of:~%  ~S~%Use DEFINE instead.");

    public static final class $red_symbol_p$UnaryFunction extends UnaryFunction {
        public $red_symbol_p$UnaryFunction() {
            super(extractFunctionNamed("RED-SYMBOL-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return red_symbol_p(arg1);
        }
    }
}

/**
 * Total time: 211 ms
 */
