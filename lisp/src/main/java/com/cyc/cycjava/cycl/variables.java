/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.define_obsolete_register;
import static com.cyc.cycjava.cycl.control_vars.$variable_names$;
import static com.cyc.cycjava.cycl.cycl_utilities.expression_find_if;
import static com.cyc.cycjava.cycl.cycl_utilities.expression_transform;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.cycjava.cycl.utilities_macros.register_cyc_api_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_question;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.make_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.rplaca;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numL;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_name;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.fixnump;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.resetMultipleValues;
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
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_tree;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.intersection;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.nsubst;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.sublis;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.prin1_to_string;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.princ;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.read_from_string_ignoring_errors;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import org.armedbear.lisp.Lisp;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.BinaryFunction;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Mapping;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sort;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLSpecialOperatorDeclarations;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDecl;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLStructDeclNative;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sxhash;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.UnaryFunction;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLStructNative;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.random;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      VARIABLES
 * source file: /cyc/top/cycl/variables.lisp
 * created:     2019/07/03 17:37:21
 */
public final class variables extends SubLTranslatedFile implements V12 {
    public static final class $variable_native extends SubLStructNative {
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        public SubLObject getField2() {
            return com.cyc.cycjava.cycl.variables.$variable_native.this.$id;
        }

        public SubLObject setField2(SubLObject value) {
            return com.cyc.cycjava.cycl.variables.$variable_native.this.$id = value;
        }

        public SubLObject $id = Lisp.NIL;

        private static final SubLStructDeclNative structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.variables.$variable_native.class, VARIABLE, VARIABLE_P, $list_alt3, $list_alt4, new String[]{ "$id" }, $list_alt5, $list_alt6, PRINT_VARIABLE);
    }

    public static final SubLFile me = new variables();

 public static final String myName = "com.cyc.cycjava.cycl.variables";


    // defconstant
    // Definitions
    /**
     * The character used as the first character of an HL variable's name.
     */
    @LispMethod(comment = "The character used as the first character of an HL variable\'s name.\ndefconstant")
    private static final SubLSymbol $hl_variable_prefix_char$ = makeSymbol("*HL-VARIABLE-PREFIX-CHAR*");

    // defconstant
    /**
     * The prefix for all default EL vars. By no coincidence, it is the upcase
     * version of the prefix in @xref print-variable
     */
    @LispMethod(comment = "The prefix for all default EL vars. By no coincidence, it is the upcase\r\nversion of the prefix in @xref print-variable\ndefconstant\nThe prefix for all default EL vars. By no coincidence, it is the upcase\nversion of the prefix in @xref print-variable")
    private static final SubLSymbol $default_el_variable_prefix$ = makeSymbol("*DEFAULT-EL-VARIABLE-PREFIX*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_variable$ = makeSymbol("*DTP-VARIABLE*");

    // deflexical
    // The total number of interned HL variables.
    /**
     * The total number of interned HL variables.
     */
    @LispMethod(comment = "The total number of interned HL variables.\ndeflexical")
    private static final SubLSymbol $variable_max$ = makeSymbol("*VARIABLE-MAX*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $var0$ = makeSymbol("*VAR0*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $var1$ = makeSymbol("*VAR1*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $var2$ = makeSymbol("*VAR2*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $var3$ = makeSymbol("*VAR3*");

    // deflexical
    @LispMethod(comment = "deflexical")
    public static final SubLSymbol $var4$ = makeSymbol("*VAR4*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLString $str0$_VAR = makeString("?VAR");

    static private final SubLList $list3 = list(makeSymbol("ID"));

    static private final SubLList $list4 = list(makeKeyword("ID"));

    static private final SubLList $list5 = list(makeSymbol("VAR-ID"));

    static private final SubLList $list6 = list(makeSymbol("_CSETF-VAR-ID"));

    private static final SubLSymbol PRINT_VARIABLE = makeSymbol("PRINT-VARIABLE");

    private static final SubLSymbol VARIABLE_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("VARIABLE-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list9 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("VARIABLE-P"));

    private static final SubLSymbol VAR_ID = makeSymbol("VAR-ID");

    private static final SubLSymbol _CSETF_VAR_ID = makeSymbol("_CSETF-VAR-ID");

    private static final SubLString $str13$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_VARIABLE = makeSymbol("MAKE-VARIABLE");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_VARIABLE_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-VARIABLE-METHOD");

    private static final SubLString $str19$_Cvar_D = makeString("~Cvar~D");

    private static final SubLInteger $int$99 = makeInteger(99);

    private static final SubLSymbol SXHASH_VARIABLE_METHOD = makeSymbol("SXHASH-VARIABLE-METHOD");

    private static final SubLList $list22 = list(makeSymbol("OBJECT"));

    private static final SubLString $str23$Return_T_iff_OBJECT_is_an_HL_vari = makeString("Return T iff OBJECT is an HL variable.");

    static private final SubLList $list24 = list(makeSymbol("BOOLEANP"));

    private static final SubLInteger $int$200 = makeInteger(200);

    private static final SubLSymbol $variable_array$ = makeSymbol("*VARIABLE-ARRAY*");

    private static final SubLSymbol $sym27$_VAR0_ = makeSymbol("*VAR0*");

    private static final SubLSymbol $sym28$_VAR1_ = makeSymbol("*VAR1*");

    private static final SubLSymbol $sym29$_VAR2_ = makeSymbol("*VAR2*");

    private static final SubLSymbol $sym30$_VAR3_ = makeSymbol("*VAR3*");

    private static final SubLSymbol $sym31$_VAR4_ = makeSymbol("*VAR4*");

    private static final SubLString $str33$The_maximum_number_of_HL_variable = makeString("The maximum number of HL variables (");

    private static final SubLString $str34$__has_been_reached_ = makeString(") has been reached.");

    private static final SubLSymbol VARIABLE_COUNT = makeSymbol("VARIABLE-COUNT");

    private static final SubLString $str36$Return_the_total_number_of_HL_var = makeString("Return the total number of HL ");

    static private final SubLList $list37 = list(makeSymbol("INTEGERP"));

    private static final SubLList $list38 = list(list(makeSymbol("HL-VAR"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list39 = list($DONE);

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    private static final SubLSymbol $sym42$NUM = makeUninternedSymbol("NUM");

    private static final SubLSymbol DO_NUMBERS = makeSymbol("DO-NUMBERS");

    private static final SubLList $list45 = list(makeSymbol("VARIABLE-COUNT"));

    private static final SubLSymbol FIND_VARIABLE_BY_ID = makeSymbol("FIND-VARIABLE-BY-ID");

    private static final SubLSymbol VARIABLE_ID = makeSymbol("VARIABLE-ID");

    private static final SubLList $list49 = list(makeSymbol("VARIABLE"));

    private static final SubLString $str50$Return_id_of_HL_variable_VARIABLE = makeString("Return id of HL variable VARIABLE.");

    private static final SubLList $list51 = list(list(makeSymbol("VARIABLE"), makeSymbol("VARIABLE-P")));

    private static final SubLString $str53$Return_the_HL_variable_with_ID__o = makeString("Return the HL variable with ID, or NIL if not present.");

    private static final SubLList $list54 = list(list(makeSymbol("ID"), makeSymbol("INTEGERP")));

    private static final SubLList $list55 = list(list(makeSymbol("NIL-OR"), makeSymbol("VARIABLE-P")));

    private static final SubLSymbol DEFAULT_EL_VAR_FOR_HL_VAR = makeSymbol("DEFAULT-EL-VAR-FOR-HL-VAR");

    private static final SubLString $str57$Return_a_readable_EL_var_from_HL_ = makeString("Return a readable EL var from HL var VARIABLE.");

    private static final SubLSymbol DEFAULT_EL_VAR_FOR_HL_VAR_CACHED = makeSymbol("DEFAULT-EL-VAR-FOR-HL-VAR-CACHED");

    private static final SubLSymbol $default_el_var_for_hl_var_cached_caching_state$ = makeSymbol("*DEFAULT-EL-VAR-FOR-HL-VAR-CACHED-CACHING-STATE*");

    private static final SubLSymbol HL_VAR_FOR_DEFAULT_EL_VAR = makeSymbol("HL-VAR-FOR-DEFAULT-EL-VAR");

    private static final SubLSymbol $sym61$EL_VAR_ = makeSymbol("EL-VAR?");

    private static final SubLSymbol $hl_var_for_default_el_var_caching_state$ = makeSymbol("*HL-VAR-FOR-DEFAULT-EL-VAR-CACHING-STATE*");

    private static final SubLSymbol $sym63$VARIABLE__ = makeSymbol("VARIABLE-<");

    private static final SubLSymbol SORT_HL_VARIABLE_LIST_MEMOIZED_INT = makeSymbol("SORT-HL-VARIABLE-LIST-MEMOIZED-INT");

    private static final SubLSymbol $sym65$HL_VAR_ = makeSymbol("HL-VAR?");

    private static final SubLSymbol $sym66$DEFAULT_EL_VAR_ = makeSymbol("DEFAULT-EL-VAR?");

    private static final SubLSymbol $sym67$NON_DEFAULT_EL_VAR_ = makeSymbol("NON-DEFAULT-EL-VAR?");

    private static final SubLString $str68$_s_contained_the_non_default_EL_v = makeString("~s contained the non-default EL variable ~s, which is not handled yet.");

    private static final SubLSymbol VARIABLE_SYMBOLP = makeSymbol("VARIABLE-SYMBOLP");

    private static final SubLList $list70 = list(makeSymbol("EL-VAR?"));

    private static final SubLSymbol $sym72$_ = makeSymbol("<");

    private static final SubLString $str74$Return_T_iff_OBJECT_contains_no_H = makeString("Return T iff OBJECT contains no HL variables, and therefore is fully bound.");

    private static final SubLSymbol NOT_FULLY_BOUND_P = makeSymbol("NOT-FULLY-BOUND-P");

    private static final SubLString $str76$Return_T_iff_OBJECT_contains_some = makeString("Return T iff OBJECT contains some HL variable, and therefore is not fully bound.");

    private static final SubLSymbol $sym77$CYC_VAR_ = makeSymbol("CYC-VAR?");

    public static final SubLObject variable_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        print_variable(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject variable_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        print_variable(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject variable_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.variables.$variable_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject variable_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.variables.$variable_native.class ? T : NIL;
    }

    public static final SubLObject var_id_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, VARIABLE_P);
        return v_object.getField2();
    }

    public static SubLObject var_id(final SubLObject v_object) {
        assert NIL != variable_p(v_object) : "! variables.variable_p(v_object) " + "variables.variable_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject _csetf_var_id_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, VARIABLE_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_var_id(final SubLObject v_object, final SubLObject value) {
        assert NIL != variable_p(v_object) : "! variables.variable_p(v_object) " + "variables.variable_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject make_variable_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.variables.$variable_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($ID)) {
                        _csetf_var_id(v_new, current_value);
                    } else {
                        Errors.error($str_alt12$Invalid_slot__S_for_construction_, current_arg);
                    }
                }
            }
            return v_new;
        }
    }

    public static SubLObject make_variable(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.variables.$variable_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($ID)) {
                _csetf_var_id(v_new, current_value);
            } else {
                Errors.error($str13$Invalid_slot__S_for_construction_, current_arg);
            }
        }
        return v_new;
    }

    public static SubLObject visit_defstruct_variable(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_VARIABLE, ONE_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $ID, var_id(obj));
        funcall(visitor_fn, obj, $END, MAKE_VARIABLE, ONE_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_variable_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_variable(obj, visitor_fn);
    }

    public static final SubLObject print_variable_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject id = variable_id(v_object);
                SubLObject name = nth(id, $variable_names$.getDynamicValue(thread));
                if (NIL != name) {
                    princ(name, stream);
                } else {
                    format(stream, $str_alt13$_Cvar_D, $hl_variable_prefix_char$.getGlobalValue(), id);
                }
            }
            return v_object;
        }
    }

    public static SubLObject print_variable(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject id = variable_id(v_object);
        final SubLObject name = nth(id, $variable_names$.getDynamicValue(thread));
        if (NIL != name) {
            princ(name, stream);
        } else {
            format(stream, $str19$_Cvar_D, $hl_variable_prefix_char$.getGlobalValue(), id);
        }
        return v_object;
    }

    public static final SubLObject sxhash_variable_method_alt(SubLObject v_object) {
        {
            SubLObject id = var_id(v_object);
            if (id.isInteger()) {
                return id;
            }
        }
        return $int$99;
    }

    public static SubLObject sxhash_variable_method(final SubLObject v_object) {
        final SubLObject id = var_id(v_object);
        if (id.isInteger()) {
            return id;
        }
        return $int$99;
    }

    /**
     * Return HL variable number NUM.
     */
    @LispMethod(comment = "Return HL variable number NUM.")
    public static final SubLObject get_variable_alt(SubLObject num) {
        SubLTrampolineFile.checkType(num, FIXNUMP);
        return aref($variable_array$.getGlobalValue(), num);
    }

    /**
     * Return HL variable number NUM.
     */
    @LispMethod(comment = "Return HL variable number NUM.")
    public static SubLObject get_variable(final SubLObject num) {
        assert NIL != fixnump(num) : "! fixnump(num) " + ("Types.fixnump(num) " + "CommonSymbols.NIL != Types.fixnump(num) ") + num;
        if (num.numGE($variable_max$.getGlobalValue())) {
            Errors.error(cconcatenate($str33$The_maximum_number_of_HL_variable, new SubLObject[]{ format_nil.format_nil_a_no_copy($variable_max$.getGlobalValue()), $str34$__has_been_reached_ }));
        }
        return aref($variable_array$.getGlobalValue(), num);
    }

    /**
     * Setup the array of interned HL variables.
     */
    @LispMethod(comment = "Setup the array of interned HL variables.")
    public static final SubLObject setup_variable_table_alt() {
        if (!$variable_array$.getGlobalValue().isVector()) {
            {
                SubLObject array = make_vector($variable_max$.getGlobalValue(), NIL);
                SubLObject i = NIL;
                for (i = ZERO_INTEGER; i.numL($variable_max$.getGlobalValue()); i = add(i, ONE_INTEGER)) {
                    {
                        SubLObject var = make_variable(UNPROVIDED);
                        _csetf_var_id(var, i);
                        set_aref(array, i, var);
                    }
                }
                $variable_array$.setGlobalValue(array);
            }
            return T;
        }
        return NIL;
    }

    /**
     * Setup the array of interned HL variables.
     */
    @LispMethod(comment = "Setup the array of interned HL variables.")
    public static SubLObject setup_variable_table() {
        if (!$variable_array$.getGlobalValue().isVector()) {
            final SubLObject array = make_vector($variable_max$.getGlobalValue(), NIL);
            SubLObject i;
            SubLObject var;
            for (i = NIL, i = ZERO_INTEGER; i.numL($variable_max$.getGlobalValue()); i = add(i, ONE_INTEGER)) {
                var = make_variable(UNPROVIDED);
                _csetf_var_id(var, i);
                set_aref(array, i, var);
            }
            $variable_array$.setGlobalValue(array);
            setup_convenience_variables();
            return T;
        }
        return NIL;
    }

    public static SubLObject setup_convenience_variables() {
        if (NIL == variable_p($var0$.getGlobalValue())) {
            $var0$.setGlobalValue(get_variable(ZERO_INTEGER));
            $var1$.setGlobalValue(get_variable(ONE_INTEGER));
            $var2$.setGlobalValue(get_variable(TWO_INTEGER));
            $var3$.setGlobalValue(get_variable(THREE_INTEGER));
            $var4$.setGlobalValue(get_variable(FOUR_INTEGER));
            return NIL;
        }
        return NIL;
    }

    /**
     * Return the total number of HL variables.
     */
    @LispMethod(comment = "Return the total number of HL variables.")
    public static final SubLObject variable_count_alt() {
        return $variable_max$.getGlobalValue();
    }

    /**
     * Return the total number of HL variables.
     */
    @LispMethod(comment = "Return the total number of HL variables.")
    public static SubLObject variable_count() {
        return $variable_max$.getGlobalValue();
    }

    public static final SubLObject do_hl_variables_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt25);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject hl_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt25);
                    hl_var = current.first();
                    current = current.rest();
                    {
                        SubLObject allow_other_keys_p = NIL;
                        SubLObject rest = current;
                        SubLObject bad = NIL;
                        SubLObject current_1 = NIL;
                        for (; NIL != rest;) {
                            destructuring_bind_must_consp(rest, datum, $list_alt25);
                            current_1 = rest.first();
                            rest = rest.rest();
                            destructuring_bind_must_consp(rest, datum, $list_alt25);
                            if (NIL == member(current_1, $list_alt26, UNPROVIDED, UNPROVIDED)) {
                                bad = T;
                            }
                            if (current_1 == $ALLOW_OTHER_KEYS) {
                                allow_other_keys_p = rest.first();
                            }
                            rest = rest.rest();
                        }
                        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                            cdestructuring_bind_error(datum, $list_alt25);
                        }
                        {
                            SubLObject done_tail = property_list_member($DONE, current);
                            SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
                            current = temp;
                            {
                                SubLObject body = current;
                                SubLObject num = $sym29$NUM;
                                return list(DO_NUMBERS, list(num, $START, ZERO_INTEGER, $END, $list_alt33, $DONE, done), listS(CLET, list(list(hl_var, list(FIND_VARIABLE_BY_ID, num))), append(body, NIL)));
                            }
                        }
                    }
                }
            }
        }
    }

    public static SubLObject do_hl_variables(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list38);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject hl_var = NIL;
        destructuring_bind_must_consp(current, datum, $list38);
        hl_var = current.first();
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$1 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list38);
            current_$1 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list38);
            if (NIL == member(current_$1, $list39, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$1 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list38);
        }
        final SubLObject done_tail = property_list_member($DONE, current);
        final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
        final SubLObject body;
        current = body = temp;
        final SubLObject num = $sym42$NUM;
        return list(DO_NUMBERS, list(num, $START, ZERO_INTEGER, $END, $list45, $DONE, done), listS(CLET, list(list(hl_var, list(FIND_VARIABLE_BY_ID, num))), append(body, NIL)));
    }

    /**
     * Return id of HL variable VARIABLE.
     */
    @LispMethod(comment = "Return id of HL variable VARIABLE.")
    public static final SubLObject variable_id_alt(SubLObject variable) {
        SubLTrampolineFile.checkType(variable, VARIABLE_P);
        return var_id(variable);
    }

    /**
     * Return id of HL variable VARIABLE.
     */
    @LispMethod(comment = "Return id of HL variable VARIABLE.")
    public static SubLObject variable_id(final SubLObject variable) {
        SubLTrampolineFile.enforceType(variable, VARIABLE_P);
        return var_id(variable);
    }

    /**
     * Return the HL variable with ID, or NIL if not present.
     */
    @LispMethod(comment = "Return the HL variable with ID, or NIL if not present.")
    public static final SubLObject find_variable_by_id_alt(SubLObject id) {
        SubLTrampolineFile.checkType(id, INTEGERP);
        return get_variable(id);
    }

    /**
     * Return the HL variable with ID, or NIL if not present.
     */
    @LispMethod(comment = "Return the HL variable with ID, or NIL if not present.")
    public static SubLObject find_variable_by_id(final SubLObject id) {
        SubLTrampolineFile.enforceType(id, INTEGERP);
        return get_variable(id);
    }

    public static final SubLObject variable_L_alt(SubLObject var1, SubLObject var2) {
        return numL(variable_id(var1), variable_id(var2));
    }

    public static SubLObject variable_L(final SubLObject var1, final SubLObject var2) {
        return numL(variable_id(var1), variable_id(var2));
    }

    /**
     * Return a random HL variable
     */
    @LispMethod(comment = "Return a random HL variable")
    public static final SubLObject random_variable_alt() {
        if (NIL != $variable_array$.getGlobalValue()) {
            return find_variable_by_id(random.random(variable_count()));
        }
        return NIL;
    }

    /**
     * Return a random HL variable
     */
    @LispMethod(comment = "Return a random HL variable")
    public static SubLObject random_variable() {
        if (NIL != $variable_array$.getGlobalValue()) {
            return find_variable_by_id(random.random(variable_count()));
        }
        return NIL;
    }

    /**
     * Return a readable EL var from HL var VARIABLE.
     */
    @LispMethod(comment = "Return a readable EL var from HL var VARIABLE.")
    public static final SubLObject default_el_var_for_hl_var_alt(SubLObject variable) {
        SubLTrampolineFile.checkType(variable, VARIABLE_P);
        return default_el_var_for_hl_var_cached(variable);
    }

    /**
     * Return a readable EL var from HL var VARIABLE.
     */
    @LispMethod(comment = "Return a readable EL var from HL var VARIABLE.")
    public static SubLObject default_el_var_for_hl_var(final SubLObject variable) {
        SubLTrampolineFile.enforceType(variable, VARIABLE_P);
        return default_el_var_for_hl_var_cached(variable);
    }

    public static final SubLObject clear_default_el_var_for_hl_var_cached_alt() {
        {
            SubLObject cs = $default_el_var_for_hl_var_cached_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_default_el_var_for_hl_var_cached() {
        final SubLObject cs = $default_el_var_for_hl_var_cached_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_default_el_var_for_hl_var_cached_alt(SubLObject variable) {
        return memoization_state.caching_state_remove_function_results_with_args($default_el_var_for_hl_var_cached_caching_state$.getGlobalValue(), list(variable), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_default_el_var_for_hl_var_cached(final SubLObject variable) {
        return memoization_state.caching_state_remove_function_results_with_args($default_el_var_for_hl_var_cached_caching_state$.getGlobalValue(), list(variable), UNPROVIDED, UNPROVIDED);
    }

    /**
     * Return a readable EL var from HL var VARIABLE.
     */
    @LispMethod(comment = "Return a readable EL var from HL var VARIABLE.")
    public static final SubLObject default_el_var_for_hl_var_cached_internal_alt(SubLObject variable) {
        SubLTrampolineFile.checkType(variable, VARIABLE_P);
        return cycl_variables.make_el_var(prin1_to_string(variable));
    }

    /**
     * Return a readable EL var from HL var VARIABLE.
     */
    @LispMethod(comment = "Return a readable EL var from HL var VARIABLE.")
    public static SubLObject default_el_var_for_hl_var_cached_internal(final SubLObject variable) {
        assert NIL != variable_p(variable) : "! variables.variable_p(variable) " + ("variables.variable_p(variable) " + "CommonSymbols.NIL != variables.variable_p(variable) ") + variable;
        final SubLObject el_var = cycl_variables.make_el_var(prin1_to_string(variable));
        return el_var;
    }

    public static final SubLObject default_el_var_for_hl_var_cached_alt(SubLObject variable) {
        {
            SubLObject caching_state = $default_el_var_for_hl_var_cached_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(DEFAULT_EL_VAR_FOR_HL_VAR_CACHED, $default_el_var_for_hl_var_cached_caching_state$, NIL, EQ, ONE_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject results = memoization_state.caching_state_lookup(caching_state, variable, $kw48$_MEMOIZED_ITEM_NOT_FOUND_);
                if (results == $kw48$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(default_el_var_for_hl_var_cached_internal(variable)));
                    memoization_state.caching_state_put(caching_state, variable, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject default_el_var_for_hl_var_cached(final SubLObject variable) {
        SubLObject caching_state = $default_el_var_for_hl_var_cached_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(DEFAULT_EL_VAR_FOR_HL_VAR_CACHED, $default_el_var_for_hl_var_cached_caching_state$, NIL, EQ, ONE_INTEGER, ZERO_INTEGER);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, variable, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(default_el_var_for_hl_var_cached_internal(variable)));
            memoization_state.caching_state_put(caching_state, variable, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    public static final SubLObject get_default_el_var_alt(SubLObject num) {
        return cycl_variables.make_el_var(cconcatenate($default_el_variable_prefix$.getGlobalValue(), string_utilities.object_to_string(num)));
    }

    public static SubLObject get_default_el_var(final SubLObject num) {
        return cycl_variables.make_el_var(cconcatenate($default_el_variable_prefix$.getGlobalValue(), string_utilities.object_to_string(num)));
    }

    public static final SubLObject clear_hl_var_for_default_el_var_alt() {
        {
            SubLObject cs = $hl_var_for_default_el_var_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_hl_var_for_default_el_var() {
        final SubLObject cs = $hl_var_for_default_el_var_caching_state$.getGlobalValue();
        if (NIL != cs) {
            memoization_state.caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_hl_var_for_default_el_var_alt(SubLObject el_variable) {
        return memoization_state.caching_state_remove_function_results_with_args($hl_var_for_default_el_var_caching_state$.getGlobalValue(), list(el_variable), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_hl_var_for_default_el_var(final SubLObject el_variable) {
        return memoization_state.caching_state_remove_function_results_with_args($hl_var_for_default_el_var_caching_state$.getGlobalValue(), list(el_variable), UNPROVIDED, UNPROVIDED);
    }

    /**
     * This function is the inverse of default-el-var-for-hl-var
     */
    @LispMethod(comment = "This function is the inverse of default-el-var-for-hl-var")
    public static final SubLObject hl_var_for_default_el_var_internal_alt(SubLObject el_variable) {
        SubLTrampolineFile.checkType(el_variable, $sym50$EL_VAR_);
        return find_variable_by_id(read_from_string_ignoring_errors(symbol_name(el_variable), NIL, NIL, FOUR_INTEGER, UNPROVIDED));
    }

    /**
     * This function is the inverse of default-el-var-for-hl-var
     */
    @LispMethod(comment = "This function is the inverse of default-el-var-for-hl-var")
    public static SubLObject hl_var_for_default_el_var_internal(final SubLObject el_variable) {
        assert NIL != cycl_variables.el_varP(el_variable) : "! cycl_variables.el_varP(el_variable) " + ("cycl_variables.el_varP(el_variable) " + "CommonSymbols.NIL != cycl_variables.el_varP(el_variable) ") + el_variable;
        return find_variable_by_id(read_from_string_ignoring_errors(symbol_name(el_variable), NIL, NIL, FOUR_INTEGER, UNPROVIDED));
    }

    public static final SubLObject hl_var_for_default_el_var_alt(SubLObject el_variable) {
        {
            SubLObject caching_state = $hl_var_for_default_el_var_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(HL_VAR_FOR_DEFAULT_EL_VAR, $hl_var_for_default_el_var_caching_state$, NIL, EQ, ONE_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject results = memoization_state.caching_state_lookup(caching_state, el_variable, $kw48$_MEMOIZED_ITEM_NOT_FOUND_);
                if (results == $kw48$_MEMOIZED_ITEM_NOT_FOUND_) {
                    results = arg2(resetMultipleValues(), multiple_value_list(hl_var_for_default_el_var_internal(el_variable)));
                    memoization_state.caching_state_put(caching_state, el_variable, results, UNPROVIDED);
                }
                return memoization_state.caching_results(results);
            }
        }
    }

    public static SubLObject hl_var_for_default_el_var(final SubLObject el_variable) {
        SubLObject caching_state = $hl_var_for_default_el_var_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = memoization_state.create_global_caching_state_for_name(HL_VAR_FOR_DEFAULT_EL_VAR, $hl_var_for_default_el_var_caching_state$, NIL, EQ, ONE_INTEGER, ZERO_INTEGER);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, el_variable, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(resetMultipleValues(), multiple_value_list(hl_var_for_default_el_var_internal(el_variable)));
            memoization_state.caching_state_put(caching_state, el_variable, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    public static final SubLObject sort_hl_variable_list_alt(SubLObject hl_variable_list) {
        return Sort.sort(hl_variable_list, $sym52$VARIABLE__, UNPROVIDED);
    }

    public static SubLObject sort_hl_variable_list(final SubLObject hl_variable_list) {
        return Sort.sort(hl_variable_list, $sym63$VARIABLE__, UNPROVIDED);
    }

    public static SubLObject sort_hl_variable_list_memoized(final SubLObject hl_variable_list) {
        return copy_list(sort_hl_variable_list_memoized_int(hl_variable_list));
    }

    public static SubLObject sort_hl_variable_list_memoized_int_internal(final SubLObject hl_variable_list) {
        return sort_hl_variable_list(copy_list(hl_variable_list));
    }

    public static SubLObject sort_hl_variable_list_memoized_int(final SubLObject hl_variable_list) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return sort_hl_variable_list_memoized_int_internal(hl_variable_list);
        }
        caching_state = memoization_state.memoization_state_lookup(v_memoization_state, SORT_HL_VARIABLE_LIST_MEMOIZED_INT, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), SORT_HL_VARIABLE_LIST_MEMOIZED_INT, ONE_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state.memoization_state_put(v_memoization_state, SORT_HL_VARIABLE_LIST_MEMOIZED_INT, caching_state);
        }
        SubLObject results = memoization_state.caching_state_lookup(caching_state, hl_variable_list, memoization_state.$memoized_item_not_found$.getGlobalValue());
        if (results.eql(memoization_state.$memoized_item_not_found$.getGlobalValue())) {
            results = arg2(thread.resetMultipleValues(), multiple_value_list(sort_hl_variable_list_memoized_int_internal(hl_variable_list)));
            memoization_state.caching_state_put(caching_state, hl_variable_list, results, UNPROVIDED);
        }
        return memoization_state.caching_results(results);
    }

    public static SubLObject sorted_hl_variables_in_expressions_intersection(final SubLObject expression1, final SubLObject expression2) {
        return sort_hl_variable_list_memoized(intersection(cycl_utilities.expression_gather(expression1, $sym65$HL_VAR_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), cycl_utilities.expression_gather(expression2, $sym65$HL_VAR_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED), UNPROVIDED, UNPROVIDED));
    }

    /**
     * Canonicalizes EXPRESSION's default EL variables into their corresponding HL variables.
     *
     * @see default-el-var?
     */
    @LispMethod(comment = "Canonicalizes EXPRESSION\'s default EL variables into their corresponding HL variables.\r\n\r\n@see default-el-var?")
    public static final SubLObject canonicalize_default_el_vars_alt(SubLObject expression) {
        return expression_transform(expression, $sym53$DEFAULT_EL_VAR_, HL_VAR_FOR_DEFAULT_EL_VAR, T, UNPROVIDED);
    }

    /**
     * Canonicalizes EXPRESSION's default EL variables into their corresponding HL variables.
     *
     * @see default-el-var?
     */
    @LispMethod(comment = "Canonicalizes EXPRESSION\'s default EL variables into their corresponding HL variables.\r\n\r\n@see default-el-var?")
    public static SubLObject canonicalize_default_el_vars(final SubLObject expression) {
        return cycl_utilities.expression_transform(expression, $sym66$DEFAULT_EL_VAR_, HL_VAR_FOR_DEFAULT_EL_VAR, T, UNPROVIDED);
    }

    /**
     * UNcanonicalizes EXPRESSION's HL variables into their corresponding default EL variables.
     *
     * @see default-el-var?
     */
    @LispMethod(comment = "UNcanonicalizes EXPRESSION\'s HL variables into their corresponding default EL variables.\r\n\r\n@see default-el-var?")
    public static final SubLObject uncanonicalize_hl_vars_alt(SubLObject expression) {
        return expression_transform(expression, VARIABLE_P, DEFAULT_EL_VAR_FOR_HL_VAR, T, UNPROVIDED);
    }

    /**
     * UNcanonicalizes EXPRESSION's HL variables into their corresponding default EL variables.
     *
     * @see default-el-var?
     */
    @LispMethod(comment = "UNcanonicalizes EXPRESSION\'s HL variables into their corresponding default EL variables.\r\n\r\n@see default-el-var?")
    public static SubLObject uncanonicalize_hl_vars(final SubLObject expression) {
        return cycl_utilities.expression_transform(expression, VARIABLE_P, DEFAULT_EL_VAR_FOR_HL_VAR, T, UNPROVIDED);
    }

    public static final SubLObject error_unless_canonicalize_default_el_vars_alt(SubLObject expression) {
        error_if_expression_contains_non_default_el_var(expression);
        return canonicalize_default_el_vars(expression);
    }

    public static SubLObject error_unless_canonicalize_default_el_vars(final SubLObject expression) {
        error_if_expression_contains_non_default_el_var(expression);
        return canonicalize_default_el_vars(expression);
    }

    public static final SubLObject error_if_expression_contains_non_default_el_var_alt(SubLObject expression) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject non_default_el_var = expression_find_if($sym54$NON_DEFAULT_EL_VAR_, expression, UNPROVIDED, UNPROVIDED);
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL != non_default_el_var) {
                        Errors.error($str_alt55$_s_contained_the_non_default_EL_v, expression, non_default_el_var);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject error_if_expression_contains_non_default_el_var(final SubLObject expression) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject non_default_el_var = cycl_utilities.expression_find_if($sym67$NON_DEFAULT_EL_VAR_, expression, UNPROVIDED, UNPROVIDED);
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL != non_default_el_var)) {
            Errors.error($str68$_s_contained_the_non_default_EL_v, expression, non_default_el_var);
        }
        return NIL;
    }

    public static final SubLObject variable_symbolp_alt(SubLObject symbol) {
        return cycl_variables.el_varP(symbol);
    }

    public static SubLObject variable_symbolp(final SubLObject symbol) {
        return cycl_variables.el_varP(symbol);
    }

    public static final SubLObject gather_variable_symbols_alt(SubLObject formula) {
        return list_utilities.tree_gather(formula, $sym50$EL_VAR_, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject gather_variable_symbols(final SubLObject formula) {
        return list_utilities.tree_gather(formula, $sym61$EL_VAR_, UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject replace_variables_alt(SubLObject form, SubLObject variable_symbols) {
        {
            SubLObject subs = Mapping.mapcar(symbol_function(CONS), variable_symbols, new SubLObject[]{ Mapping.mapcar(symbol_function(FIND_VARIABLE_BY_ID), list_utilities.num_list(length(variable_symbols), UNPROVIDED)) });
            return sublis(subs, form, UNPROVIDED, UNPROVIDED);
        }
    }

    public static SubLObject replace_variables(final SubLObject form, final SubLObject variable_symbols) {
        final SubLObject subs = Mapping.mapcar(symbol_function(CONS), variable_symbols, new SubLObject[]{ Mapping.mapcar(symbol_function(FIND_VARIABLE_BY_ID), list_utilities.num_list(length(variable_symbols), UNPROVIDED)) });
        return sublis(subs, form, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject restore_variables_alt(SubLObject form, SubLObject original_variables) {
        {
            SubLObject subs = Mapping.mapcar(symbol_function(CONS), Mapping.mapcar(symbol_function(FIND_VARIABLE_BY_ID), list_utilities.num_list(length(original_variables), UNPROVIDED)), new SubLObject[]{ original_variables });
            return sublis(subs, form, UNPROVIDED, UNPROVIDED);
        }
    }

    public static SubLObject restore_variables(final SubLObject form, final SubLObject original_variables) {
        final SubLObject subs = Mapping.mapcar(symbol_function(CONS), Mapping.mapcar(symbol_function(FIND_VARIABLE_BY_ID), list_utilities.num_list(length(original_variables), UNPROVIDED)), new SubLObject[]{ original_variables });
        return sublis(subs, form, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject subst_hl_variables(final SubLObject el_vars, SubLObject tree) {
        tree = copy_tree(tree);
        SubLObject list_var = NIL;
        SubLObject el_var = NIL;
        SubLObject hl_var_id = NIL;
        list_var = el_vars;
        el_var = list_var.first();
        for (hl_var_id = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , el_var = list_var.first() , hl_var_id = add(ONE_INTEGER, hl_var_id)) {
            final SubLObject hl_var = find_variable_by_id(hl_var_id);
            tree = nsubst(hl_var, el_var, tree, symbol_function(EQL), UNPROVIDED);
        }
        return tree;
    }

    /**
     * Generate a list of hl variables from 0 to N exclusive.
     */
    @LispMethod(comment = "Generate a list of hl variables from 0 to N exclusive.")
    public static final SubLObject generate_hl_variable_list_alt(SubLObject n) {
        {
            SubLObject variable_list = make_list(n, UNPROVIDED);
            SubLObject index = NIL;
            SubLObject rest = NIL;
            for (index = ZERO_INTEGER, rest = variable_list; NIL != rest; index = add(index, ONE_INTEGER) , rest = rest.rest()) {
                rplaca(rest, find_variable_by_id(index));
            }
            return variable_list;
        }
    }

    /**
     * Generate a list of hl variables from 0 to N exclusive.
     */
    @LispMethod(comment = "Generate a list of hl variables from 0 to N exclusive.")
    public static SubLObject generate_hl_variable_list(final SubLObject n) {
        final SubLObject variable_list = make_list(n, UNPROVIDED);
        SubLObject index = NIL;
        SubLObject rest = NIL;
        index = ZERO_INTEGER;
        for (rest = variable_list; NIL != rest; rest = rest.rest()) {
            rplaca(rest, find_variable_by_id(index));
            index = add(index, ONE_INTEGER);
        }
        return variable_list;
    }

    public static final SubLObject gather_hl_variables_alt(SubLObject v_object) {
        return Sort.sort(list_utilities.tree_gather(v_object, symbol_function(VARIABLE_P), UNPROVIDED, UNPROVIDED, UNPROVIDED), symbol_function($sym59$_), symbol_function(VARIABLE_ID));
    }

    public static SubLObject gather_hl_variables(final SubLObject v_object) {
        return Sort.sort(list_utilities.tree_gather(v_object, symbol_function(VARIABLE_P), UNPROVIDED, UNPROVIDED, UNPROVIDED), symbol_function($sym72$_), symbol_function(VARIABLE_ID));
    }

    public static final SubLObject max_hl_variable_alt(SubLObject v_object) {
        return number_utilities.maximum(gather_hl_variables(v_object), symbol_function(VARIABLE_ID));
    }

    public static SubLObject max_hl_variable(final SubLObject v_object) {
        return number_utilities.maximum(gather_hl_variables(v_object), symbol_function(VARIABLE_ID));
    }

    public static final SubLObject max_hl_variable_id_alt(SubLObject v_object) {
        {
            SubLObject var = max_hl_variable(v_object);
            return NIL != var ? ((SubLObject) (variable_id(var))) : NIL;
        }
    }

    public static SubLObject max_hl_variable_id(final SubLObject v_object) {
        final SubLObject var = max_hl_variable(v_object);
        return NIL != var ? variable_id(var) : NIL;
    }

    /**
     * Return T iff OBJECT contains no HL variables, and therefore is fully bound.
     */
    @LispMethod(comment = "Return T iff OBJECT contains no HL variables, and therefore is fully bound.")
    public static final SubLObject fully_bound_p_alt(SubLObject v_object) {
        return makeBoolean(NIL == not_fully_bound_p(v_object));
    }

    /**
     * Return T iff OBJECT contains no HL variables, and therefore is fully bound.
     */
    @LispMethod(comment = "Return T iff OBJECT contains no HL variables, and therefore is fully bound.")
    public static SubLObject fully_bound_p(final SubLObject v_object) {
        return makeBoolean(NIL == not_fully_bound_p(v_object));
    }

    /**
     * Return T iff OBJECT contains some HL variable, and therefore is not fully bound.
     */
    @LispMethod(comment = "Return T iff OBJECT contains some HL variable, and therefore is not fully bound.")
    public static final SubLObject not_fully_bound_p_alt(SubLObject v_object) {
        if (v_object.isAtom()) {
            return variable_p(v_object);
        } else {
            {
                SubLObject rest = NIL;
                SubLObject next = NIL;
                for (rest = v_object, next = rest.first(); !rest.rest().isAtom(); rest = rest.rest() , next = rest.first()) {
                    if (NIL != not_fully_bound_p(next)) {
                        return T;
                    }
                }
                return makeBoolean((NIL != not_fully_bound_p(next)) || (NIL != variable_p(rest.rest())));
            }
        }
    }

    /**
     * Return T iff OBJECT contains some HL variable, and therefore is not fully bound.
     */
    @LispMethod(comment = "Return T iff OBJECT contains some HL variable, and therefore is not fully bound.")
    public static SubLObject not_fully_bound_p(final SubLObject v_object) {
        if (v_object.isAtom()) {
            return variable_p(v_object);
        }
        SubLObject rest = NIL;
        SubLObject next = NIL;
        rest = v_object;
        next = rest.first();
        while (!rest.rest().isAtom()) {
            if (NIL != not_fully_bound_p(next)) {
                return T;
            }
            rest = rest.rest();
            next = rest.first();
        } 
        return makeBoolean((NIL != not_fully_bound_p(next)) || (NIL != variable_p(rest.rest())));
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLString $str_alt0$_VAR = makeString("?VAR");

    public static final SubLObject cycl_ground_expression_p_alt(SubLObject expression) {
        return makeBoolean(NIL == expression_find_if($sym64$CYC_VAR_, expression, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject cycl_ground_expression_p(final SubLObject expression) {
        return makeBoolean(NIL == cycl_utilities.expression_find_if($sym77$CYC_VAR_, expression, UNPROVIDED, UNPROVIDED));
    }

    static private final SubLList $list_alt3 = list(makeSymbol("ID"));

    static private final SubLList $list_alt4 = list(makeKeyword("ID"));

    static private final SubLList $list_alt5 = list(makeSymbol("VAR-ID"));

    public static final SubLObject hl_ground_expression_p_alt(SubLObject expression) {
        return makeBoolean(NIL == expression_find_if(symbol_function(VARIABLE_P), expression, UNPROVIDED, UNPROVIDED));
    }

    public static SubLObject hl_ground_expression_p(final SubLObject expression) {
        return makeBoolean(NIL == cycl_utilities.expression_find_if(symbol_function(VARIABLE_P), expression, UNPROVIDED, UNPROVIDED));
    }

    static private final SubLList $list_alt6 = list(makeSymbol("_CSETF-VAR-ID"));

    public static SubLObject declare_variables_file() {
        declareFunction("variable_print_function_trampoline", "VARIABLE-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("variable_p", "VARIABLE-P", 1, 0, false);
        new variables.$variable_p$UnaryFunction();
        declareFunction("var_id", "VAR-ID", 1, 0, false);
        declareFunction("_csetf_var_id", "_CSETF-VAR-ID", 2, 0, false);
        declareFunction("make_variable", "MAKE-VARIABLE", 0, 1, false);
        declareFunction("visit_defstruct_variable", "VISIT-DEFSTRUCT-VARIABLE", 2, 0, false);
        declareFunction("visit_defstruct_object_variable_method", "VISIT-DEFSTRUCT-OBJECT-VARIABLE-METHOD", 2, 0, false);
        declareFunction("print_variable", "PRINT-VARIABLE", 3, 0, false);
        declareFunction("sxhash_variable_method", "SXHASH-VARIABLE-METHOD", 1, 0, false);
        new variables.$sxhash_variable_method$UnaryFunction();
        declareFunction("get_variable", "GET-VARIABLE", 1, 0, false);
        declareFunction("setup_variable_table", "SETUP-VARIABLE-TABLE", 0, 0, false);
        declareFunction("setup_convenience_variables", "SETUP-CONVENIENCE-VARIABLES", 0, 0, false);
        declareFunction("variable_count", "VARIABLE-COUNT", 0, 0, false);
        declareMacro("do_hl_variables", "DO-HL-VARIABLES");
        declareFunction("variable_id", "VARIABLE-ID", 1, 0, false);
        declareFunction("find_variable_by_id", "FIND-VARIABLE-BY-ID", 1, 0, false);
        declareFunction("variable_L", "VARIABLE-<", 2, 0, false);
        new variables.$variable_L$BinaryFunction();
        declareFunction("random_variable", "RANDOM-VARIABLE", 0, 0, false);
        declareFunction("default_el_var_for_hl_var", "DEFAULT-EL-VAR-FOR-HL-VAR", 1, 0, false);
        declareFunction("clear_default_el_var_for_hl_var_cached", "CLEAR-DEFAULT-EL-VAR-FOR-HL-VAR-CACHED", 0, 0, false);
        declareFunction("remove_default_el_var_for_hl_var_cached", "REMOVE-DEFAULT-EL-VAR-FOR-HL-VAR-CACHED", 1, 0, false);
        declareFunction("default_el_var_for_hl_var_cached_internal", "DEFAULT-EL-VAR-FOR-HL-VAR-CACHED-INTERNAL", 1, 0, false);
        declareFunction("default_el_var_for_hl_var_cached", "DEFAULT-EL-VAR-FOR-HL-VAR-CACHED", 1, 0, false);
        declareFunction("get_default_el_var", "GET-DEFAULT-EL-VAR", 1, 0, false);
        declareFunction("clear_hl_var_for_default_el_var", "CLEAR-HL-VAR-FOR-DEFAULT-EL-VAR", 0, 0, false);
        declareFunction("remove_hl_var_for_default_el_var", "REMOVE-HL-VAR-FOR-DEFAULT-EL-VAR", 1, 0, false);
        declareFunction("hl_var_for_default_el_var_internal", "HL-VAR-FOR-DEFAULT-EL-VAR-INTERNAL", 1, 0, false);
        declareFunction("hl_var_for_default_el_var", "HL-VAR-FOR-DEFAULT-EL-VAR", 1, 0, false);
        declareFunction("sort_hl_variable_list", "SORT-HL-VARIABLE-LIST", 1, 0, false);
        declareFunction("sort_hl_variable_list_memoized", "SORT-HL-VARIABLE-LIST-MEMOIZED", 1, 0, false);
        declareFunction("sort_hl_variable_list_memoized_int_internal", "SORT-HL-VARIABLE-LIST-MEMOIZED-INT-INTERNAL", 1, 0, false);
        declareFunction("sort_hl_variable_list_memoized_int", "SORT-HL-VARIABLE-LIST-MEMOIZED-INT", 1, 0, false);
        declareFunction("sorted_hl_variables_in_expressions_intersection", "SORTED-HL-VARIABLES-IN-EXPRESSIONS-INTERSECTION", 2, 0, false);
        declareFunction("canonicalize_default_el_vars", "CANONICALIZE-DEFAULT-EL-VARS", 1, 0, false);
        declareFunction("uncanonicalize_hl_vars", "UNCANONICALIZE-HL-VARS", 1, 0, false);
        declareFunction("error_unless_canonicalize_default_el_vars", "ERROR-UNLESS-CANONICALIZE-DEFAULT-EL-VARS", 1, 0, false);
        declareFunction("error_if_expression_contains_non_default_el_var", "ERROR-IF-EXPRESSION-CONTAINS-NON-DEFAULT-EL-VAR", 1, 0, false);
        declareFunction("variable_symbolp", "VARIABLE-SYMBOLP", 1, 0, false);
        declareFunction("gather_variable_symbols", "GATHER-VARIABLE-SYMBOLS", 1, 0, false);
        declareFunction("replace_variables", "REPLACE-VARIABLES", 2, 0, false);
        declareFunction("restore_variables", "RESTORE-VARIABLES", 2, 0, false);
        declareFunction("subst_hl_variables", "SUBST-HL-VARIABLES", 2, 0, false);
        declareFunction("generate_hl_variable_list", "GENERATE-HL-VARIABLE-LIST", 1, 0, false);
        declareFunction("gather_hl_variables", "GATHER-HL-VARIABLES", 1, 0, false);
        declareFunction("max_hl_variable", "MAX-HL-VARIABLE", 1, 0, false);
        declareFunction("max_hl_variable_id", "MAX-HL-VARIABLE-ID", 1, 0, false);
        declareFunction("fully_bound_p", "FULLY-BOUND-P", 1, 0, false);
        new variables.$fully_bound_p$UnaryFunction();
        declareFunction("not_fully_bound_p", "NOT-FULLY-BOUND-P", 1, 0, false);
        new variables.$not_fully_bound_p$UnaryFunction();
        declareFunction("cycl_ground_expression_p", "CYCL-GROUND-EXPRESSION-P", 1, 0, false);
        declareFunction("hl_ground_expression_p", "HL-GROUND-EXPRESSION-P", 1, 0, false);
        return NIL;
    }

    static private final SubLString $str_alt12$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLString $str_alt13$_Cvar_D = makeString("~Cvar~D");

    static private final SubLList $list_alt16 = list(makeSymbol("OBJECT"));

    static private final SubLString $str_alt17$Return_T_iff_OBJECT_is_an_HL_vari = makeString("Return T iff OBJECT is an HL variable.");

    static private final SubLList $list_alt18 = list(makeSymbol("BOOLEANP"));

    static private final SubLString $str_alt23$Return_the_total_number_of_HL_var = makeString("Return the total number of HL variables.");

    static private final SubLList $list_alt24 = list(makeSymbol("INTEGERP"));

    static private final SubLList $list_alt25 = list(list(makeSymbol("HL-VAR"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt26 = list($DONE);

    static private final SubLSymbol $sym29$NUM = makeUninternedSymbol("NUM");

    static private final SubLList $list_alt33 = list(makeSymbol("VARIABLE-COUNT"));

    static private final SubLList $list_alt37 = list(makeSymbol("VARIABLE"));

    static private final SubLString $str_alt38$Return_id_of_HL_variable_VARIABLE = makeString("Return id of HL variable VARIABLE.");

    static private final SubLList $list_alt39 = list(list(makeSymbol("VARIABLE"), makeSymbol("VARIABLE-P")));

    static private final SubLString $str_alt41$Return_the_HL_variable_with_ID__o = makeString("Return the HL variable with ID, or NIL if not present.");

    static private final SubLList $list_alt42 = list(list(makeSymbol("ID"), makeSymbol("INTEGERP")));

    static private final SubLList $list_alt43 = list(list(makeSymbol("NIL-OR"), makeSymbol("VARIABLE-P")));

    static private final SubLString $str_alt45$Return_a_readable_EL_var_from_HL_ = makeString("Return a readable EL var from HL var VARIABLE.");

    public static final SubLSymbol $kw48$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLSymbol $sym50$EL_VAR_ = makeSymbol("EL-VAR?");

    static private final SubLSymbol $sym52$VARIABLE__ = makeSymbol("VARIABLE-<");

    static private final SubLSymbol $sym53$DEFAULT_EL_VAR_ = makeSymbol("DEFAULT-EL-VAR?");

    static private final SubLSymbol $sym54$NON_DEFAULT_EL_VAR_ = makeSymbol("NON-DEFAULT-EL-VAR?");

    static private final SubLString $str_alt55$_s_contained_the_non_default_EL_v = makeString("~s contained the non-default EL variable ~s, which is not handled yet.");

    static private final SubLList $list_alt57 = list(makeSymbol("EL-VAR?"));

    static private final SubLSymbol $sym59$_ = makeSymbol("<");

    static private final SubLString $str_alt61$Return_T_iff_OBJECT_contains_no_H = makeString("Return T iff OBJECT contains no HL variables, and therefore is fully bound.");

    static private final SubLString $str_alt63$Return_T_iff_OBJECT_contains_some = makeString("Return T iff OBJECT contains some HL variable, and therefore is not fully bound.");

    static private final SubLSymbol $sym64$CYC_VAR_ = makeSymbol("CYC-VAR?");

    public static final SubLObject init_variables_file_alt() {
        defconstant("*HL-VARIABLE-PREFIX-CHAR*", CHAR_question);
        defconstant("*DEFAULT-EL-VARIABLE-PREFIX*", $str_alt0$_VAR);
        defconstant("*DTP-VARIABLE*", VARIABLE);
        deflexical("*VARIABLE-MAX*", $int$200);
        deflexical("*VARIABLE-ARRAY*", NIL != boundp($variable_array$) ? ((SubLObject) ($variable_array$.getGlobalValue())) : NIL);
        deflexical("*DEFAULT-EL-VAR-FOR-HL-VAR-CACHED-CACHING-STATE*", NIL);
        deflexical("*HL-VAR-FOR-DEFAULT-EL-VAR-CACHING-STATE*", NIL);
        return NIL;
    }

    public static SubLObject init_variables_file() {
        if (SubLFiles.USE_V1) {
            defconstant("*HL-VARIABLE-PREFIX-CHAR*", CHAR_question);
            defconstant("*DEFAULT-EL-VARIABLE-PREFIX*", $str0$_VAR);
            defconstant("*DTP-VARIABLE*", VARIABLE);
            deflexical("*VARIABLE-MAX*", $int$200);
            deflexical("*VARIABLE-ARRAY*", SubLTrampolineFile.maybeDefault($variable_array$, $variable_array$, NIL));
            deflexical("*VAR0*", SubLTrampolineFile.maybeDefault($sym27$_VAR0_, $var0$, NIL));
            deflexical("*VAR1*", SubLTrampolineFile.maybeDefault($sym28$_VAR1_, $var1$, NIL));
            deflexical("*VAR2*", SubLTrampolineFile.maybeDefault($sym29$_VAR2_, $var2$, NIL));
            deflexical("*VAR3*", SubLTrampolineFile.maybeDefault($sym30$_VAR3_, $var3$, NIL));
            deflexical("*VAR4*", SubLTrampolineFile.maybeDefault($sym31$_VAR4_, $var4$, NIL));
            deflexical("*DEFAULT-EL-VAR-FOR-HL-VAR-CACHED-CACHING-STATE*", NIL);
            deflexical("*HL-VAR-FOR-DEFAULT-EL-VAR-CACHING-STATE*", NIL);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*VARIABLE-ARRAY*", NIL != boundp($variable_array$) ? ((SubLObject) ($variable_array$.getGlobalValue())) : NIL);
        }
        return NIL;
    }

    public static SubLObject init_variables_file_Previous() {
        defconstant("*HL-VARIABLE-PREFIX-CHAR*", CHAR_question);
        defconstant("*DEFAULT-EL-VARIABLE-PREFIX*", $str0$_VAR);
        defconstant("*DTP-VARIABLE*", VARIABLE);
        deflexical("*VARIABLE-MAX*", $int$200);
        deflexical("*VARIABLE-ARRAY*", SubLTrampolineFile.maybeDefault($variable_array$, $variable_array$, NIL));
        deflexical("*VAR0*", SubLTrampolineFile.maybeDefault($sym27$_VAR0_, $var0$, NIL));
        deflexical("*VAR1*", SubLTrampolineFile.maybeDefault($sym28$_VAR1_, $var1$, NIL));
        deflexical("*VAR2*", SubLTrampolineFile.maybeDefault($sym29$_VAR2_, $var2$, NIL));
        deflexical("*VAR3*", SubLTrampolineFile.maybeDefault($sym30$_VAR3_, $var3$, NIL));
        deflexical("*VAR4*", SubLTrampolineFile.maybeDefault($sym31$_VAR4_, $var4$, NIL));
        deflexical("*DEFAULT-EL-VAR-FOR-HL-VAR-CACHED-CACHING-STATE*", NIL);
        deflexical("*HL-VAR-FOR-DEFAULT-EL-VAR-CACHING-STATE*", NIL);
        return NIL;
    }

    public static final SubLObject setup_variables_file_alt() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(VARIABLE_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(VAR_ID, _CSETF_VAR_ID);
        identity(VARIABLE);
        register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(SXHASH_VARIABLE_METHOD));
        register_cyc_api_function(VARIABLE_P, $list_alt16, $str_alt17$Return_T_iff_OBJECT_is_an_HL_vari, NIL, $list_alt18);
        declare_defglobal($variable_array$);
        register_cyc_api_function(VARIABLE_COUNT, NIL, $str_alt23$Return_the_total_number_of_HL_var, NIL, $list_alt24);
        register_cyc_api_function(VARIABLE_ID, $list_alt37, $str_alt38$Return_id_of_HL_variable_VARIABLE, $list_alt39, $list_alt24);
        register_cyc_api_function(FIND_VARIABLE_BY_ID, $list_alt3, $str_alt41$Return_the_HL_variable_with_ID__o, $list_alt42, $list_alt43);
        register_cyc_api_function(DEFAULT_EL_VAR_FOR_HL_VAR, $list_alt37, $str_alt45$Return_a_readable_EL_var_from_HL_, $list_alt39, NIL);
        memoization_state.note_globally_cached_function(DEFAULT_EL_VAR_FOR_HL_VAR_CACHED);
        memoization_state.note_globally_cached_function(HL_VAR_FOR_DEFAULT_EL_VAR);
        define_obsolete_register(VARIABLE_SYMBOLP, $list_alt57);
        register_cyc_api_function(FULLY_BOUND_P, $list_alt16, $str_alt61$Return_T_iff_OBJECT_contains_no_H, NIL, $list_alt18);
        register_cyc_api_function(NOT_FULLY_BOUND_P, $list_alt16, $str_alt63$Return_T_iff_OBJECT_contains_some, NIL, $list_alt18);
        return NIL;
    }

    public static SubLObject setup_variables_file() {
        if (SubLFiles.USE_V1) {
            register_method($print_object_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(VARIABLE_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim($list9);
            def_csetf(VAR_ID, _CSETF_VAR_ID);
            identity(VARIABLE);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_VARIABLE_METHOD));
            register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(SXHASH_VARIABLE_METHOD));
            register_cyc_api_function(VARIABLE_P, $list22, $str23$Return_T_iff_OBJECT_is_an_HL_vari, NIL, $list24);
            declare_defglobal($variable_array$);
            declare_defglobal($sym27$_VAR0_);
            declare_defglobal($sym28$_VAR1_);
            declare_defglobal($sym29$_VAR2_);
            declare_defglobal($sym30$_VAR3_);
            declare_defglobal($sym31$_VAR4_);
            setup_variable_table();
            register_cyc_api_function(VARIABLE_COUNT, NIL, $str36$Return_the_total_number_of_HL_var, NIL, $list37);
            register_cyc_api_function(VARIABLE_ID, $list49, $str50$Return_id_of_HL_variable_VARIABLE, $list51, $list37);
            register_cyc_api_function(FIND_VARIABLE_BY_ID, $list3, $str53$Return_the_HL_variable_with_ID__o, $list54, $list55);
            register_cyc_api_function(DEFAULT_EL_VAR_FOR_HL_VAR, $list49, $str57$Return_a_readable_EL_var_from_HL_, $list51, NIL);
            memoization_state.note_globally_cached_function(DEFAULT_EL_VAR_FOR_HL_VAR_CACHED);
            memoization_state.note_globally_cached_function(HL_VAR_FOR_DEFAULT_EL_VAR);
            memoization_state.note_memoized_function(SORT_HL_VARIABLE_LIST_MEMOIZED_INT);
            define_obsolete_register(VARIABLE_SYMBOLP, $list70);
            register_cyc_api_function(FULLY_BOUND_P, $list22, $str74$Return_T_iff_OBJECT_contains_no_H, NIL, $list24);
            register_cyc_api_function(NOT_FULLY_BOUND_P, $list22, $str76$Return_T_iff_OBJECT_contains_some, NIL, $list24);
        }
        if (SubLFiles.USE_V2) {
            register_cyc_api_function(VARIABLE_P, $list_alt16, $str_alt17$Return_T_iff_OBJECT_is_an_HL_vari, NIL, $list_alt18);
            register_cyc_api_function(VARIABLE_COUNT, NIL, $str_alt23$Return_the_total_number_of_HL_var, NIL, $list_alt24);
            register_cyc_api_function(VARIABLE_ID, $list_alt37, $str_alt38$Return_id_of_HL_variable_VARIABLE, $list_alt39, $list_alt24);
            register_cyc_api_function(FIND_VARIABLE_BY_ID, $list_alt3, $str_alt41$Return_the_HL_variable_with_ID__o, $list_alt42, $list_alt43);
            register_cyc_api_function(DEFAULT_EL_VAR_FOR_HL_VAR, $list_alt37, $str_alt45$Return_a_readable_EL_var_from_HL_, $list_alt39, NIL);
            define_obsolete_register(VARIABLE_SYMBOLP, $list_alt57);
            register_cyc_api_function(FULLY_BOUND_P, $list_alt16, $str_alt61$Return_T_iff_OBJECT_contains_no_H, NIL, $list_alt18);
            register_cyc_api_function(NOT_FULLY_BOUND_P, $list_alt16, $str_alt63$Return_T_iff_OBJECT_contains_some, NIL, $list_alt18);
        }
        return NIL;
    }

    public static SubLObject setup_variables_file_Previous() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(VARIABLE_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list9);
        def_csetf(VAR_ID, _CSETF_VAR_ID);
        identity(VARIABLE);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_VARIABLE_METHOD));
        register_method(Sxhash.$sxhash_method_table$.getGlobalValue(), $dtp_variable$.getGlobalValue(), symbol_function(SXHASH_VARIABLE_METHOD));
        register_cyc_api_function(VARIABLE_P, $list22, $str23$Return_T_iff_OBJECT_is_an_HL_vari, NIL, $list24);
        declare_defglobal($variable_array$);
        declare_defglobal($sym27$_VAR0_);
        declare_defglobal($sym28$_VAR1_);
        declare_defglobal($sym29$_VAR2_);
        declare_defglobal($sym30$_VAR3_);
        declare_defglobal($sym31$_VAR4_);
        setup_variable_table();
        register_cyc_api_function(VARIABLE_COUNT, NIL, $str36$Return_the_total_number_of_HL_var, NIL, $list37);
        register_cyc_api_function(VARIABLE_ID, $list49, $str50$Return_id_of_HL_variable_VARIABLE, $list51, $list37);
        register_cyc_api_function(FIND_VARIABLE_BY_ID, $list3, $str53$Return_the_HL_variable_with_ID__o, $list54, $list55);
        register_cyc_api_function(DEFAULT_EL_VAR_FOR_HL_VAR, $list49, $str57$Return_a_readable_EL_var_from_HL_, $list51, NIL);
        memoization_state.note_globally_cached_function(DEFAULT_EL_VAR_FOR_HL_VAR_CACHED);
        memoization_state.note_globally_cached_function(HL_VAR_FOR_DEFAULT_EL_VAR);
        memoization_state.note_memoized_function(SORT_HL_VARIABLE_LIST_MEMOIZED_INT);
        define_obsolete_register(VARIABLE_SYMBOLP, $list70);
        register_cyc_api_function(FULLY_BOUND_P, $list22, $str74$Return_T_iff_OBJECT_contains_no_H, NIL, $list24);
        register_cyc_api_function(NOT_FULLY_BOUND_P, $list22, $str76$Return_T_iff_OBJECT_contains_some, NIL, $list24);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_variables_file();
    }

    @Override
    public void initializeVariables() {
        init_variables_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_variables_file();
    }

    static {
    }

    public static final class $variable_p$UnaryFunction extends UnaryFunction {
        public $variable_p$UnaryFunction() {
            super(extractFunctionNamed("VARIABLE-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return variable_p(arg1);
        }
    }

    public static final class $sxhash_variable_method$UnaryFunction extends UnaryFunction {
        public $sxhash_variable_method$UnaryFunction() {
            super(extractFunctionNamed("SXHASH-VARIABLE-METHOD"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return sxhash_variable_method(arg1);
        }
    }

    public static final class $variable_L$BinaryFunction extends BinaryFunction {
        public $variable_L$BinaryFunction() {
            super(extractFunctionNamed("VARIABLE-<"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1, final SubLObject arg2) {
            return variable_L(arg1, arg2);
        }
    }

    public static final class $fully_bound_p$UnaryFunction extends UnaryFunction {
        public $fully_bound_p$UnaryFunction() {
            super(extractFunctionNamed("FULLY-BOUND-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return fully_bound_p(arg1);
        }
    }

    public static final class $not_fully_bound_p$UnaryFunction extends UnaryFunction {
        public $not_fully_bound_p$UnaryFunction() {
            super(extractFunctionNamed("NOT-FULLY-BOUND-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return not_fully_bound_p(arg1);
        }
    }
}

/**
 * Total time: 258 ms
 */
