/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.cycjava.cycl.utilities_macros.generate_instance_variable_bindings_for_structure_slots;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_lbrace;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_lbracket;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_lparen;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_rbrace;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_rbracket;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_rparen;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.gethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.make_hash_table;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.remhash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.sethash;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.make_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.release_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.seize_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Packages.intern;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.position;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.make_symbol;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.intersection;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import org.armedbear.lisp.Lisp;

import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
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
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      RANGES
 * source file: /cyc/top/cycl/ranges.lisp
 * created:     2019/07/03 17:37:07
 */
public final class ranges extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new ranges();

 public static final String myName = "com.cyc.cycjava.cycl.ranges";


    // defconstant
    @LispMethod(comment = "Enumerated values of type RANGE-BOUND-TYPE.\ndefconstant")
    private static final SubLSymbol $valid_range_bound_types$ = makeSymbol("*VALID-RANGE-BOUND-TYPES*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $ranges_initial_hashtable_size$ = makeSymbol("*RANGES-INITIAL-HASHTABLE-SIZE*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $ranges_table_update_lock$ = makeSymbol("*RANGES-TABLE-UPDATE-LOCK*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_range$ = makeSymbol("*DTP-RANGE*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list0 = list(makeKeyword("INCLUSIVE"), makeKeyword("EXCLUSIVE"));

    private static final SubLSymbol RANGE_BOUND_TYPE = makeSymbol("RANGE-BOUND-TYPE");

    static private final SubLString $str2$_S___S_is_not_a_member_of_the__S_ = makeString("~S: ~S is not a member of the ~S enumeration.");

    private static final SubLSymbol ENCODE_RANGE_BOUND_TYPE = makeSymbol("ENCODE-RANGE-BOUND-TYPE");

    static private final SubLString $str4$_S___S_is_not_a_valid_encoding_of = makeString("~S: ~S is not a valid encoding of the ~S enumeration.");

    private static final SubLSymbol DECODE_RANGE_BOUND_TYPE = makeSymbol("DECODE-RANGE-BOUND-TYPE");

    static private final SubLString $str6$_S___S_was_expected_to_be_a_membe = makeString("~S: ~S was expected to be a member of the enumeration ~S.");

    private static final SubLSymbol RANGE_BOUND_TYPE_P = makeSymbol("RANGE-BOUND-TYPE-P");

    static private final SubLString $$$Ranges_Table_Lock = makeString("Ranges Table Lock");

    public static final SubLSymbol $ranges_table$ = makeSymbol("*RANGES-TABLE*");

    private static final SubLSymbol RANGE_P = makeSymbol("RANGE-P");

    static private final SubLList $list13 = list(new SubLObject[]{ makeSymbol("TYPE-NAME"), makeSymbol("BASE-TYPE-PREDICATE"), makeSymbol("ENUMERATION-TYPE"), makeSymbol("LOW-BOUND"), makeSymbol("HIGH-BOUND"), makeSymbol("LOW-BOUND-PREDICATE"), makeSymbol("HIGH-BOUND-PREDICATE"), makeSymbol("LOW-BOUND-TYPE"), makeSymbol("HIGH-BOUND-TYPE") });

    static private final SubLList $list14 = list(new SubLObject[]{ makeKeyword("TYPE-NAME"), makeKeyword("BASE-TYPE-PREDICATE"), makeKeyword("ENUMERATION-TYPE"), makeKeyword("LOW-BOUND"), makeKeyword("HIGH-BOUND"), makeKeyword("LOW-BOUND-PREDICATE"), makeKeyword("HIGH-BOUND-PREDICATE"), makeKeyword("LOW-BOUND-TYPE"), makeKeyword("HIGH-BOUND-TYPE") });

    static private final SubLList $list15 = list(new SubLObject[]{ makeSymbol("RANGE-TYPE-NAME"), makeSymbol("RANGE-BASE-TYPE-PREDICATE"), makeSymbol("RANGE-ENUMERATION-TYPE"), makeSymbol("RANGE-LOW-BOUND"), makeSymbol("RANGE-HIGH-BOUND"), makeSymbol("RANGE-LOW-BOUND-PREDICATE"), makeSymbol("RANGE-HIGH-BOUND-PREDICATE"), makeSymbol("RANGE-LOW-BOUND-TYPE"), makeSymbol("RANGE-HIGH-BOUND-TYPE") });

    static private final SubLList $list16 = list(new SubLObject[]{ makeSymbol("_CSETF-RANGE-TYPE-NAME"), makeSymbol("_CSETF-RANGE-BASE-TYPE-PREDICATE"), makeSymbol("_CSETF-RANGE-ENUMERATION-TYPE"), makeSymbol("_CSETF-RANGE-LOW-BOUND"), makeSymbol("_CSETF-RANGE-HIGH-BOUND"), makeSymbol("_CSETF-RANGE-LOW-BOUND-PREDICATE"), makeSymbol("_CSETF-RANGE-HIGH-BOUND-PREDICATE"), makeSymbol("_CSETF-RANGE-LOW-BOUND-TYPE"), makeSymbol("_CSETF-RANGE-HIGH-BOUND-TYPE") });

    private static final SubLSymbol PRINT_RANGE = makeSymbol("PRINT-RANGE");

    private static final SubLSymbol RANGE_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("RANGE-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list19 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("RANGE-P"));

    private static final SubLSymbol RANGE_TYPE_NAME = makeSymbol("RANGE-TYPE-NAME");

    private static final SubLSymbol _CSETF_RANGE_TYPE_NAME = makeSymbol("_CSETF-RANGE-TYPE-NAME");

    private static final SubLSymbol RANGE_BASE_TYPE_PREDICATE = makeSymbol("RANGE-BASE-TYPE-PREDICATE");

    private static final SubLSymbol _CSETF_RANGE_BASE_TYPE_PREDICATE = makeSymbol("_CSETF-RANGE-BASE-TYPE-PREDICATE");

    private static final SubLSymbol RANGE_ENUMERATION_TYPE = makeSymbol("RANGE-ENUMERATION-TYPE");

    private static final SubLSymbol _CSETF_RANGE_ENUMERATION_TYPE = makeSymbol("_CSETF-RANGE-ENUMERATION-TYPE");

    private static final SubLSymbol RANGE_LOW_BOUND = makeSymbol("RANGE-LOW-BOUND");

    private static final SubLSymbol _CSETF_RANGE_LOW_BOUND = makeSymbol("_CSETF-RANGE-LOW-BOUND");

    private static final SubLSymbol RANGE_HIGH_BOUND = makeSymbol("RANGE-HIGH-BOUND");

    private static final SubLSymbol _CSETF_RANGE_HIGH_BOUND = makeSymbol("_CSETF-RANGE-HIGH-BOUND");

    private static final SubLSymbol RANGE_LOW_BOUND_PREDICATE = makeSymbol("RANGE-LOW-BOUND-PREDICATE");

    private static final SubLSymbol _CSETF_RANGE_LOW_BOUND_PREDICATE = makeSymbol("_CSETF-RANGE-LOW-BOUND-PREDICATE");

    private static final SubLSymbol RANGE_HIGH_BOUND_PREDICATE = makeSymbol("RANGE-HIGH-BOUND-PREDICATE");

    private static final SubLSymbol _CSETF_RANGE_HIGH_BOUND_PREDICATE = makeSymbol("_CSETF-RANGE-HIGH-BOUND-PREDICATE");

    private static final SubLSymbol RANGE_LOW_BOUND_TYPE = makeSymbol("RANGE-LOW-BOUND-TYPE");

    private static final SubLSymbol _CSETF_RANGE_LOW_BOUND_TYPE = makeSymbol("_CSETF-RANGE-LOW-BOUND-TYPE");

    private static final SubLSymbol RANGE_HIGH_BOUND_TYPE = makeSymbol("RANGE-HIGH-BOUND-TYPE");

    private static final SubLSymbol _CSETF_RANGE_HIGH_BOUND_TYPE = makeSymbol("_CSETF-RANGE-HIGH-BOUND-TYPE");

    private static final SubLSymbol $BASE_TYPE_PREDICATE = makeKeyword("BASE-TYPE-PREDICATE");

    private static final SubLSymbol $LOW_BOUND_PREDICATE = makeKeyword("LOW-BOUND-PREDICATE");

    private static final SubLSymbol $HIGH_BOUND_PREDICATE = makeKeyword("HIGH-BOUND-PREDICATE");

    private static final SubLSymbol $LOW_BOUND_TYPE = makeKeyword("LOW-BOUND-TYPE");

    private static final SubLSymbol $HIGH_BOUND_TYPE = makeKeyword("HIGH-BOUND-TYPE");

    private static final SubLString $str47$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_RANGE = makeSymbol("MAKE-RANGE");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_RANGE_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-RANGE-METHOD");

    private static final SubLList $list53 = list(makeSymbol("RANGE"), makeSymbol("&BODY"), makeSymbol("FORMS"));

    private static final SubLString $$$range = makeString("range");

    private static final SubLSymbol RANGE_ = makeSymbol("RANGE-");

    private static final SubLString $str58$_C_S___S_C = makeString("~C~S, ~S~C");

    private static final SubLSymbol $sym63$_ = makeSymbol(">");

    private static final SubLSymbol $sym64$_ = makeSymbol("<");

    private static final SubLSymbol $sym65$CHAR_ = makeSymbol("CHAR>");

    private static final SubLSymbol $sym66$CHAR_ = makeSymbol("CHAR<");

    private static final SubLSymbol $sym67$STRING_ = makeSymbol("STRING>");

    private static final SubLSymbol $sym68$STRING_ = makeSymbol("STRING<");

    private static final SubLSymbol ENUMERATION_GREATER_P = makeSymbol("ENUMERATION-GREATER-P");

    private static final SubLSymbol ENUMERATION_LESS_P = makeSymbol("ENUMERATION-LESS-P");

    private static final SubLSymbol $sym71$__ = makeSymbol(">=");

    private static final SubLSymbol $sym72$__ = makeSymbol("<=");

    private static final SubLSymbol $sym73$CHAR__ = makeSymbol("CHAR>=");

    private static final SubLSymbol $sym74$CHAR__ = makeSymbol("CHAR<=");

    private static final SubLSymbol $sym75$STRING__ = makeSymbol("STRING>=");

    private static final SubLSymbol $sym76$STRING__ = makeSymbol("STRING<=");

    private static final SubLSymbol ENUMERATION_GREATER_OR_EQUAL_P = makeSymbol("ENUMERATION-GREATER-OR-EQUAL-P");

    private static final SubLSymbol ENUMERATION_LESS_OR_EQUAL_P = makeSymbol("ENUMERATION-LESS-OR-EQUAL-P");

    private static final SubLString $str79$RANGES_INCORPORATE_DEFINITION___S = makeString("RANGES-INCORPORATE-DEFINITION: ~S is not a legal range bound type.");

    private static final SubLString $str80$RANGES_INCORPORATE_DEFINITION__co = makeString("RANGES-INCORPORATE-DEFINITION: could not determine a lower bound predicate for range type ~S.");

    private static final SubLString $str81$RANGES_INCORPORATE_DEFINITION__co = makeString("RANGES-INCORPORATE-DEFINITION: could not determine an upper bound predicate for range type ~S.");

    private static final SubLString $str82$_S_P = makeString("~S-P");

    private static final SubLList $list83 = list(makeSymbol("OBJECT"));

    private static final SubLList $list87 = list(T);

    private static final SubLSymbol RANGES_INCORPORATE_DEFINITION = makeSymbol("RANGES-INCORPORATE-DEFINITION");

    private static final SubLList $list91 = list(makeSymbol("DEFINING-FORM"), makeSymbol("ENUM-NAME"), list(makeSymbol("LOW-BOUND-VALUE"), makeSymbol("HIGH-BOUND-VALUE")), makeSymbol("&OPTIONAL"), list(makeSymbol("GREATER-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("LESS-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("BASE-TYPE-PREDICATE"), NIL));

    private static final SubLList $list92 = list(makeSymbol("ENUM-NAME"), list(makeSymbol("LOW-BOUND-VALUE"), makeSymbol("HIGH-BOUND-VALUE")), makeSymbol("&OPTIONAL"), list(makeSymbol("GREATER-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("LESS-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("BASE-TYPE-PREDICATE"), NIL));

    private static final SubLSymbol DEFINE_INCLUSIVE_RANGE_WITH_FORM = makeSymbol("DEFINE-INCLUSIVE-RANGE-WITH-FORM");

    private static final SubLSymbol DEFINE_API = makeSymbol("DEFINE-API");

    private static final SubLSymbol DEFINE_EXCLUSIVE_RANGE_WITH_FORM = makeSymbol("DEFINE-EXCLUSIVE-RANGE-WITH-FORM");

    private static final SubLString $str100$RANGE_MEMBER_P___S_is_not_a_valid = makeString("RANGE-MEMBER-P: ~S is not a valid range.");

    /**
     * Returns a list of all valid members of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Returns a list of all valid members of the RANGE-BOUND-TYPE enumeration.")
    public static final SubLObject valid_range_bound_types_alt() {
        return $valid_range_bound_types$.getGlobalValue();
    }

    /**
     * Returns a list of all valid members of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Returns a list of all valid members of the RANGE-BOUND-TYPE enumeration.")
    public static SubLObject valid_range_bound_types() {
        return $valid_range_bound_types$.getGlobalValue();
    }

    /**
     * Return T iff OBJECT is a member of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Return T iff OBJECT is a member of the RANGE-BOUND-TYPE enumeration.")
    public static final SubLObject range_bound_type_p_alt(SubLObject v_object) {
        return NIL != member(v_object, $valid_range_bound_types$.getGlobalValue(), UNPROVIDED, UNPROVIDED) ? ((SubLObject) (T)) : NIL;
    }

    /**
     * Return T iff OBJECT is a member of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Return T iff OBJECT is a member of the RANGE-BOUND-TYPE enumeration.")
    public static SubLObject range_bound_type_p(final SubLObject v_object) {
        return NIL != member(v_object, $valid_range_bound_types$.getGlobalValue(), UNPROVIDED, UNPROVIDED) ? T : NIL;
    }

    /**
     * Maps a member of the RANGE-BOUND-TYPE enumeration to an integer encoding.
     */
    @LispMethod(comment = "Maps a member of the RANGE-BOUND-TYPE enumeration to an integer encoding.")
    public static final SubLObject encode_range_bound_type_alt(SubLObject value) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject pos = position(value, $valid_range_bound_types$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == pos) {
                        Errors.error($str_alt2$_S___S_is_not_a_member_of_the__S_, ENCODE_RANGE_BOUND_TYPE, value, RANGE_BOUND_TYPE);
                    }
                }
                return pos;
            }
        }
    }

    /**
     * Maps a member of the RANGE-BOUND-TYPE enumeration to an integer encoding.
     */
    @LispMethod(comment = "Maps a member of the RANGE-BOUND-TYPE enumeration to an integer encoding.")
    public static SubLObject encode_range_bound_type(final SubLObject value) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject pos = position(value, $valid_range_bound_types$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == pos)) {
            Errors.error($str2$_S___S_is_not_a_member_of_the__S_, ENCODE_RANGE_BOUND_TYPE, value, RANGE_BOUND_TYPE);
        }
        return pos;
    }

    /**
     * Maps an encoded value to a member of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Maps an encoded value to a member of the RANGE-BOUND-TYPE enumeration.")
    public static final SubLObject decode_range_bound_type_alt(SubLObject value) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject element = nth(value, $valid_range_bound_types$.getGlobalValue());
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == element) {
                        Errors.error($str_alt4$_S___S_is_not_a_valid_encoding_of, DECODE_RANGE_BOUND_TYPE, value, RANGE_BOUND_TYPE);
                    }
                }
                return element;
            }
        }
    }

    /**
     * Maps an encoded value to a member of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Maps an encoded value to a member of the RANGE-BOUND-TYPE enumeration.")
    public static SubLObject decode_range_bound_type(final SubLObject value) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject element = nth(value, $valid_range_bound_types$.getGlobalValue());
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == element)) {
            Errors.error($str4$_S___S_is_not_a_valid_encoding_of, DECODE_RANGE_BOUND_TYPE, value, RANGE_BOUND_TYPE);
        }
        return element;
    }

    /**
     * Provides a LESSP predicate for members of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Provides a LESSP predicate for members of the RANGE-BOUND-TYPE enumeration.")
    public static final SubLObject range_bound_type_less_p_alt(SubLObject value1, SubLObject value2) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == range_bound_type_p(value1)) {
                    Errors.error($str_alt6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value1, RANGE_BOUND_TYPE);
                }
            }
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == range_bound_type_p(value2)) {
                    Errors.error($str_alt6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value2, RANGE_BOUND_TYPE);
                }
            }
            {
                SubLObject cdolist_list_var = $valid_range_bound_types$.getGlobalValue();
                SubLObject value = NIL;
                for (value = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , value = cdolist_list_var.first()) {
                    if (value == value1) {
                        return makeBoolean(value != value2);
                    } else {
                        if (value == value2) {
                            return NIL;
                        }
                    }
                }
            }
            return NIL;
        }
    }

    /**
     * Provides a LESSP predicate for members of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Provides a LESSP predicate for members of the RANGE-BOUND-TYPE enumeration.")
    public static SubLObject range_bound_type_less_p(final SubLObject value1, final SubLObject value2) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == range_bound_type_p(value1))) {
            Errors.error($str6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value1, RANGE_BOUND_TYPE);
        }
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == range_bound_type_p(value2))) {
            Errors.error($str6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value2, RANGE_BOUND_TYPE);
        }
        SubLObject cdolist_list_var = $valid_range_bound_types$.getGlobalValue();
        SubLObject value3 = NIL;
        value3 = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (value3.eql(value1)) {
                return makeBoolean(!value3.eql(value2));
            }
            if (value3.eql(value2)) {
                return NIL;
            }
            cdolist_list_var = cdolist_list_var.rest();
            value3 = cdolist_list_var.first();
        } 
        return NIL;
    }

    /**
     * Provides a GREATERP predicate for members of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Provides a GREATERP predicate for members of the RANGE-BOUND-TYPE enumeration.")
    public static final SubLObject range_bound_type_greater_p_alt(SubLObject value1, SubLObject value2) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == range_bound_type_p(value1)) {
                    Errors.error($str_alt6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value1, RANGE_BOUND_TYPE);
                }
            }
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == range_bound_type_p(value2)) {
                    Errors.error($str_alt6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value2, RANGE_BOUND_TYPE);
                }
            }
            {
                SubLObject cdolist_list_var = $valid_range_bound_types$.getGlobalValue();
                SubLObject value = NIL;
                for (value = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , value = cdolist_list_var.first()) {
                    if (value == value2) {
                        return makeBoolean(value != value1);
                    } else {
                        if (value == value1) {
                            return NIL;
                        }
                    }
                }
            }
            return NIL;
        }
    }

    /**
     * Provides a GREATERP predicate for members of the RANGE-BOUND-TYPE enumeration.
     */
    @LispMethod(comment = "Provides a GREATERP predicate for members of the RANGE-BOUND-TYPE enumeration.")
    public static SubLObject range_bound_type_greater_p(final SubLObject value1, final SubLObject value2) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == range_bound_type_p(value1))) {
            Errors.error($str6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value1, RANGE_BOUND_TYPE);
        }
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == range_bound_type_p(value2))) {
            Errors.error($str6$_S___S_was_expected_to_be_a_membe, RANGE_BOUND_TYPE_P, value2, RANGE_BOUND_TYPE);
        }
        SubLObject cdolist_list_var = $valid_range_bound_types$.getGlobalValue();
        SubLObject value3 = NIL;
        value3 = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (value3.eql(value2)) {
                return makeBoolean(!value3.eql(value1));
            }
            if (value3.eql(value1)) {
                return NIL;
            }
            cdolist_list_var = cdolist_list_var.rest();
            value3 = cdolist_list_var.first();
        } 
        return NIL;
    }

    public static final SubLObject range_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        print_range(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject range_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        print_range(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject range_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.ranges.$range_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject range_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.ranges.$range_native.class ? T : NIL;
    }

    public static final SubLObject range_type_name_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField2();
    }

    public static SubLObject range_type_name(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject range_base_type_predicate_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField3();
    }

    public static SubLObject range_base_type_predicate(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject range_enumeration_type_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField4();
    }

    public static SubLObject range_enumeration_type(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject range_low_bound_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField5();
    }

    public static SubLObject range_low_bound(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject range_high_bound_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField6();
    }

    public static SubLObject range_high_bound(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject range_low_bound_predicate_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField7();
    }

    public static SubLObject range_low_bound_predicate(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject range_high_bound_predicate_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField8();
    }

    public static SubLObject range_high_bound_predicate(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField8();
    }

    public static final SubLObject range_low_bound_type_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField9();
    }

    public static SubLObject range_low_bound_type(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField9();
    }

    public static final SubLObject range_high_bound_type_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.getField10();
    }

    public static SubLObject range_high_bound_type(final SubLObject v_object) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.getField10();
    }

    public static final SubLObject _csetf_range_type_name_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_range_type_name(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_range_base_type_predicate_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_range_base_type_predicate(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_range_enumeration_type_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_range_enumeration_type(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_range_low_bound_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_range_low_bound(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_range_high_bound_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_range_high_bound(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_range_low_bound_predicate_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_range_low_bound_predicate(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject _csetf_range_high_bound_predicate_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField8(value);
    }

    public static SubLObject _csetf_range_high_bound_predicate(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField8(value);
    }

    public static final SubLObject _csetf_range_low_bound_type_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField9(value);
    }

    public static SubLObject _csetf_range_low_bound_type(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField9(value);
    }

    public static final SubLObject _csetf_range_high_bound_type_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, RANGE_P);
        return v_object.setField10(value);
    }

    public static SubLObject _csetf_range_high_bound_type(final SubLObject v_object, final SubLObject value) {
        assert NIL != range_p(v_object) : "! ranges.range_p(v_object) " + "ranges.range_p error :" + v_object;
        return v_object.setField10(value);
    }

    public static final SubLObject make_range_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.ranges.$range_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($TYPE_NAME)) {
                        _csetf_range_type_name(v_new, current_value);
                    } else {
                        if (pcase_var.eql($BASE_TYPE_PREDICATE)) {
                            _csetf_range_base_type_predicate(v_new, current_value);
                        } else {
                            if (pcase_var.eql($ENUMERATION_TYPE)) {
                                _csetf_range_enumeration_type(v_new, current_value);
                            } else {
                                if (pcase_var.eql($LOW_BOUND)) {
                                    _csetf_range_low_bound(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($HIGH_BOUND)) {
                                        _csetf_range_high_bound(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($LOW_BOUND_PREDICATE)) {
                                            _csetf_range_low_bound_predicate(v_new, current_value);
                                        } else {
                                            if (pcase_var.eql($HIGH_BOUND_PREDICATE)) {
                                                _csetf_range_high_bound_predicate(v_new, current_value);
                                            } else {
                                                if (pcase_var.eql($LOW_BOUND_TYPE)) {
                                                    _csetf_range_low_bound_type(v_new, current_value);
                                                } else {
                                                    if (pcase_var.eql($HIGH_BOUND_TYPE)) {
                                                        _csetf_range_high_bound_type(v_new, current_value);
                                                    } else {
                                                        Errors.error($str_alt46$Invalid_slot__S_for_construction_, current_arg);
                                                    }
                                                }
                                            }
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

    public static SubLObject make_range(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.ranges.$range_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($TYPE_NAME)) {
                _csetf_range_type_name(v_new, current_value);
            } else
                if (pcase_var.eql($BASE_TYPE_PREDICATE)) {
                    _csetf_range_base_type_predicate(v_new, current_value);
                } else
                    if (pcase_var.eql($ENUMERATION_TYPE)) {
                        _csetf_range_enumeration_type(v_new, current_value);
                    } else
                        if (pcase_var.eql($LOW_BOUND)) {
                            _csetf_range_low_bound(v_new, current_value);
                        } else
                            if (pcase_var.eql($HIGH_BOUND)) {
                                _csetf_range_high_bound(v_new, current_value);
                            } else
                                if (pcase_var.eql($LOW_BOUND_PREDICATE)) {
                                    _csetf_range_low_bound_predicate(v_new, current_value);
                                } else
                                    if (pcase_var.eql($HIGH_BOUND_PREDICATE)) {
                                        _csetf_range_high_bound_predicate(v_new, current_value);
                                    } else
                                        if (pcase_var.eql($LOW_BOUND_TYPE)) {
                                            _csetf_range_low_bound_type(v_new, current_value);
                                        } else
                                            if (pcase_var.eql($HIGH_BOUND_TYPE)) {
                                                _csetf_range_high_bound_type(v_new, current_value);
                                            } else {
                                                Errors.error($str47$Invalid_slot__S_for_construction_, current_arg);
                                            }








        }
        return v_new;
    }

    public static SubLObject visit_defstruct_range(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_RANGE, NINE_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $TYPE_NAME, range_type_name(obj));
        funcall(visitor_fn, obj, $SLOT, $BASE_TYPE_PREDICATE, range_base_type_predicate(obj));
        funcall(visitor_fn, obj, $SLOT, $ENUMERATION_TYPE, range_enumeration_type(obj));
        funcall(visitor_fn, obj, $SLOT, $LOW_BOUND, range_low_bound(obj));
        funcall(visitor_fn, obj, $SLOT, $HIGH_BOUND, range_high_bound(obj));
        funcall(visitor_fn, obj, $SLOT, $LOW_BOUND_PREDICATE, range_low_bound_predicate(obj));
        funcall(visitor_fn, obj, $SLOT, $HIGH_BOUND_PREDICATE, range_high_bound_predicate(obj));
        funcall(visitor_fn, obj, $SLOT, $LOW_BOUND_TYPE, range_low_bound_type(obj));
        funcall(visitor_fn, obj, $SLOT, $HIGH_BOUND_TYPE, range_high_bound_type(obj));
        funcall(visitor_fn, obj, $END, MAKE_RANGE, NINE_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_range_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_range(obj, visitor_fn);
    }

    public static final SubLObject with_range_read_only_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject range = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt47);
            range = current.first();
            current = current.rest();
            {
                SubLObject forms = current;
                SubLObject range_var = make_symbol($$$range);
                return generate_instance_variable_bindings_for_structure_slots(range_var, range, RANGE_, $list_alt13, forms, UNPROVIDED);
            }
        }
    }

    public static SubLObject with_range_read_only(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject range = NIL;
        destructuring_bind_must_consp(current, datum, $list53);
        range = current.first();
        final SubLObject forms;
        current = forms = current.rest();
        final SubLObject range_var = make_symbol($$$range);
        return generate_instance_variable_bindings_for_structure_slots(range_var, range, RANGE_, $list13, forms, UNPROVIDED);
    }

    public static final SubLObject with_range_read_write_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject range = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt47);
            range = current.first();
            current = current.rest();
            {
                SubLObject forms = current;
                SubLObject range_var = make_symbol($$$range);
                return generate_instance_variable_bindings_for_structure_slots(range_var, range, RANGE_, $list_alt13, forms, NIL);
            }
        }
    }

    public static SubLObject with_range_read_write(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject range = NIL;
        destructuring_bind_must_consp(current, datum, $list53);
        range = current.first();
        final SubLObject forms;
        current = forms = current.rest();
        final SubLObject range_var = make_symbol($$$range);
        return generate_instance_variable_bindings_for_structure_slots(range_var, range, RANGE_, $list13, forms, NIL);
    }

    public static final SubLObject print_range_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        {
            SubLObject range = v_object;
            SubLObject low_bound_type = range_low_bound_type(range);
            SubLObject high_bound_type = range_high_bound_type(range);
            SubLObject low_bound_bracket = (low_bound_type == $INCLUSIVE) ? ((SubLObject) (CHAR_lbracket)) : low_bound_type == $EXCLUSIVE ? ((SubLObject) (CHAR_lparen)) : CHAR_lbrace;
            SubLObject high_bound_bracket = (high_bound_type == $INCLUSIVE) ? ((SubLObject) (CHAR_rbracket)) : high_bound_type == $EXCLUSIVE ? ((SubLObject) (CHAR_rparen)) : CHAR_rbrace;
            format(stream, $str_alt52$_C_S___S_C, low_bound_bracket, high_bound_bracket);
        }
        return v_object;
    }

    public static SubLObject print_range(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        final SubLObject low_bound_type = range_low_bound_type(v_object);
        final SubLObject high_bound_type = range_high_bound_type(v_object);
        final SubLObject low_bound_bracket = (low_bound_type == $INCLUSIVE) ? CHAR_lbracket : low_bound_type == $EXCLUSIVE ? CHAR_lparen : CHAR_lbrace;
        final SubLObject high_bound_bracket = (high_bound_type == $INCLUSIVE) ? CHAR_rbracket : high_bound_type == $EXCLUSIVE ? CHAR_rparen : CHAR_rbrace;
        format(stream, $str58$_C_S___S_C, low_bound_bracket, high_bound_bracket);
        return v_object;
    }

    public static final SubLObject ranges_retrieve_range_alt(SubLObject range_name) {
        if (range_name.isSymbol()) {
            return gethash(range_name, $ranges_table$.getGlobalValue(), UNPROVIDED);
        }
        return NIL;
    }

    public static SubLObject ranges_retrieve_range(final SubLObject range_name) {
        if (range_name.isSymbol()) {
            return gethash(range_name, $ranges_table$.getGlobalValue(), UNPROVIDED);
        }
        return NIL;
    }

    public static final SubLObject range_intern_range_alt(SubLObject range_name, SubLObject range) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject lock = $ranges_table_update_lock$.getDynamicValue(thread);
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    if (range_name.isSymbol() && (NIL != range_p(range))) {
                        sethash(range_name, $ranges_table$.getGlobalValue(), range);
                    }
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
            return range;
        }
    }

    public static SubLObject range_intern_range(final SubLObject range_name, final SubLObject range) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject release = NIL;
        try {
            release = seize_lock($ranges_table_update_lock$.getDynamicValue(thread));
            if (range_name.isSymbol() && (NIL != range_p(range))) {
                sethash(range_name, $ranges_table$.getGlobalValue(), range);
            }
        } finally {
            if (NIL != release) {
                release_lock($ranges_table_update_lock$.getDynamicValue(thread));
            }
        }
        return range;
    }

    public static final SubLObject ranges_delete_range_alt(SubLObject range_name) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject lock = $ranges_table_update_lock$.getDynamicValue(thread);
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    if (range_name.isSymbol()) {
                        remhash(range_name, $ranges_table$.getGlobalValue());
                    }
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject ranges_delete_range(final SubLObject range_name) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject release = NIL;
        try {
            release = seize_lock($ranges_table_update_lock$.getDynamicValue(thread));
            if (range_name.isSymbol()) {
                remhash(range_name, $ranges_table$.getGlobalValue());
            }
        } finally {
            if (NIL != release) {
                release_lock($ranges_table_update_lock$.getDynamicValue(thread));
            }
        }
        return NIL;
    }

    public static final SubLObject ranges_compute_base_type_predicate_alt(SubLObject low_bound_value, SubLObject high_bound_value, SubLObject given_predicate) {
        if (NIL != given_predicate) {
            return given_predicate;
        }
        if (low_bound_value.isInteger()) {
            if (high_bound_value.isInteger()) {
                return INTEGERP;
            } else {
                if (high_bound_value.isDouble()) {
                    return FLOATP;
                } else {
                    return NIL;
                }
            }
        } else {
            if (low_bound_value.isDouble()) {
                if (high_bound_value.isDouble()) {
                    return FLOATP;
                } else {
                    if (high_bound_value.isInteger()) {
                        return FLOATP;
                    } else {
                        return NIL;
                    }
                }
            } else {
                if (low_bound_value.isChar()) {
                    if (high_bound_value.isChar()) {
                        return CHARACTERP;
                    } else {
                        return NIL;
                    }
                } else {
                    if (low_bound_value.isString()) {
                        if (high_bound_value.isString()) {
                            return STRINGP;
                        } else {
                            return NIL;
                        }
                    } else {
                        return NIL;
                    }
                }
            }
        }
    }

    public static SubLObject ranges_compute_base_type_predicate(final SubLObject low_bound_value, final SubLObject high_bound_value, final SubLObject given_predicate) {
        if (NIL != given_predicate) {
            return given_predicate;
        }
        if (low_bound_value.isInteger()) {
            if (high_bound_value.isInteger()) {
                return INTEGERP;
            }
            if (high_bound_value.isDouble()) {
                return FLOATP;
            }
            return NIL;
        } else
            if (low_bound_value.isDouble()) {
                if (high_bound_value.isDouble()) {
                    return FLOATP;
                }
                if (high_bound_value.isInteger()) {
                    return FLOATP;
                }
                return NIL;
            } else
                if (low_bound_value.isChar()) {
                    if (high_bound_value.isChar()) {
                        return CHARACTERP;
                    }
                    return NIL;
                } else {
                    if (!low_bound_value.isString()) {
                        return NIL;
                    }
                    if (high_bound_value.isString()) {
                        return STRINGP;
                    }
                    return NIL;
                }


    }

    public static final SubLObject ranges_compute_exclusive_bound_predicates_alt(SubLObject low_bound_value, SubLObject high_bound_value, SubLObject given_low_bound_predicate, SubLObject given_high_bound_predicate) {
        if ((NIL != given_low_bound_predicate) && (NIL != given_high_bound_predicate)) {
            return values(given_low_bound_predicate, given_high_bound_predicate, NIL);
        }
        if (low_bound_value.isInteger()) {
            if (high_bound_value.isInteger()) {
                return values($sym57$_, $sym58$_, NIL);
            } else {
                if (high_bound_value.isDouble()) {
                    return values($sym57$_, $sym58$_, NIL);
                } else {
                    return values(NIL, NIL, NIL);
                }
            }
        } else {
            if (low_bound_value.isDouble()) {
                if (high_bound_value.isDouble()) {
                    return values($sym57$_, $sym58$_, NIL);
                } else {
                    if (high_bound_value.isInteger()) {
                        return values($sym57$_, $sym58$_, NIL);
                    } else {
                        return values(NIL, NIL, NIL);
                    }
                }
            } else {
                if (low_bound_value.isChar()) {
                    if (high_bound_value.isChar()) {
                        return values($sym59$CHAR_, $sym60$CHAR_, NIL);
                    } else {
                        return values(NIL, NIL, NIL);
                    }
                } else {
                    if (low_bound_value.isString()) {
                        if (high_bound_value.isString()) {
                            return values($sym61$STRING_, $sym62$STRING_, NIL);
                        } else {
                            return values(NIL, NIL, NIL);
                        }
                    } else {
                        {
                            SubLObject low_bound_enumerations = enumerations.enumerate_enumerations(low_bound_value);
                            SubLObject high_bound_enumerations = enumerations.enumerate_enumerations(high_bound_value);
                            SubLObject common_enumerations = intersection(low_bound_enumerations, high_bound_enumerations, UNPROVIDED, UNPROVIDED);
                            if (NIL != list_utilities.singletonP(common_enumerations)) {
                                {
                                    SubLObject enumeration_type = common_enumerations.first();
                                    return values(ENUMERATION_GREATER_P, ENUMERATION_LESS_P, enumeration_type);
                                }
                            } else {
                                return values(NIL, NIL, NIL);
                            }
                        }
                    }
                }
            }
        }
    }

    public static SubLObject ranges_compute_exclusive_bound_predicates(final SubLObject low_bound_value, final SubLObject high_bound_value, final SubLObject given_low_bound_predicate, final SubLObject given_high_bound_predicate) {
        if ((NIL != given_low_bound_predicate) && (NIL != given_high_bound_predicate)) {
            return values(given_low_bound_predicate, given_high_bound_predicate, NIL);
        }
        if (low_bound_value.isInteger()) {
            if (high_bound_value.isInteger()) {
                return values($sym63$_, $sym64$_, NIL);
            }
            if (high_bound_value.isDouble()) {
                return values($sym63$_, $sym64$_, NIL);
            }
            return values(NIL, NIL, NIL);
        } else
            if (low_bound_value.isDouble()) {
                if (high_bound_value.isDouble()) {
                    return values($sym63$_, $sym64$_, NIL);
                }
                if (high_bound_value.isInteger()) {
                    return values($sym63$_, $sym64$_, NIL);
                }
                return values(NIL, NIL, NIL);
            } else
                if (low_bound_value.isChar()) {
                    if (high_bound_value.isChar()) {
                        return values($sym65$CHAR_, $sym66$CHAR_, NIL);
                    }
                    return values(NIL, NIL, NIL);
                } else
                    if (low_bound_value.isString()) {
                        if (high_bound_value.isString()) {
                            return values($sym67$STRING_, $sym68$STRING_, NIL);
                        }
                        return values(NIL, NIL, NIL);
                    } else {
                        final SubLObject low_bound_enumerations = enumerations.enumerate_enumerations(low_bound_value);
                        final SubLObject high_bound_enumerations = enumerations.enumerate_enumerations(high_bound_value);
                        final SubLObject common_enumerations = intersection(low_bound_enumerations, high_bound_enumerations, UNPROVIDED, UNPROVIDED);
                        if (NIL != list_utilities.singletonP(common_enumerations)) {
                            final SubLObject enumeration_type = common_enumerations.first();
                            return values(ENUMERATION_GREATER_P, ENUMERATION_LESS_P, enumeration_type);
                        }
                        return values(NIL, NIL, NIL);
                    }



    }

    public static final SubLObject ranges_compute_inclusive_bound_predicates_alt(SubLObject low_bound_value, SubLObject high_bound_value, SubLObject given_low_bound_predicate, SubLObject given_high_bound_predicate) {
        if ((NIL != given_low_bound_predicate) && (NIL != given_high_bound_predicate)) {
            return values(given_low_bound_predicate, given_high_bound_predicate);
        }
        if (low_bound_value.isInteger()) {
            if (high_bound_value.isInteger()) {
                return values($sym65$__, $sym66$__, NIL);
            } else {
                if (high_bound_value.isDouble()) {
                    return values($sym65$__, $sym66$__, NIL);
                } else {
                    return values(NIL, NIL, NIL);
                }
            }
        } else {
            if (low_bound_value.isDouble()) {
                if (high_bound_value.isDouble()) {
                    return values($sym65$__, $sym66$__, NIL);
                } else {
                    if (high_bound_value.isInteger()) {
                        return values($sym65$__, $sym66$__, NIL);
                    } else {
                        return values(NIL, NIL, NIL);
                    }
                }
            } else {
                if (low_bound_value.isChar()) {
                    if (high_bound_value.isChar()) {
                        return values($sym67$CHAR__, $sym68$CHAR__, NIL);
                    } else {
                        return values(NIL, NIL, NIL);
                    }
                } else {
                    if (low_bound_value.isString()) {
                        if (high_bound_value.isString()) {
                            return values($sym69$STRING__, $sym70$STRING__, NIL);
                        } else {
                            return values(NIL, NIL, NIL);
                        }
                    } else {
                        {
                            SubLObject low_bound_enumerations = enumerations.enumerate_enumerations(low_bound_value);
                            SubLObject high_bound_enumerations = enumerations.enumerate_enumerations(high_bound_value);
                            SubLObject common_enumerations = intersection(low_bound_enumerations, high_bound_enumerations, UNPROVIDED, UNPROVIDED);
                            if (NIL != list_utilities.singletonP(common_enumerations)) {
                                {
                                    SubLObject enumeration_type = common_enumerations.first();
                                    return values(ENUMERATION_GREATER_OR_EQUAL_P, ENUMERATION_LESS_OR_EQUAL_P, enumeration_type);
                                }
                            } else {
                                return values(NIL, NIL, NIL);
                            }
                        }
                    }
                }
            }
        }
    }

    public static SubLObject ranges_compute_inclusive_bound_predicates(final SubLObject low_bound_value, final SubLObject high_bound_value, final SubLObject given_low_bound_predicate, final SubLObject given_high_bound_predicate) {
        if ((NIL != given_low_bound_predicate) && (NIL != given_high_bound_predicate)) {
            return values(given_low_bound_predicate, given_high_bound_predicate);
        }
        if (low_bound_value.isInteger()) {
            if (high_bound_value.isInteger()) {
                return values($sym71$__, $sym72$__, NIL);
            }
            if (high_bound_value.isDouble()) {
                return values($sym71$__, $sym72$__, NIL);
            }
            return values(NIL, NIL, NIL);
        } else
            if (low_bound_value.isDouble()) {
                if (high_bound_value.isDouble()) {
                    return values($sym71$__, $sym72$__, NIL);
                }
                if (high_bound_value.isInteger()) {
                    return values($sym71$__, $sym72$__, NIL);
                }
                return values(NIL, NIL, NIL);
            } else
                if (low_bound_value.isChar()) {
                    if (high_bound_value.isChar()) {
                        return values($sym73$CHAR__, $sym74$CHAR__, NIL);
                    }
                    return values(NIL, NIL, NIL);
                } else
                    if (low_bound_value.isString()) {
                        if (high_bound_value.isString()) {
                            return values($sym75$STRING__, $sym76$STRING__, NIL);
                        }
                        return values(NIL, NIL, NIL);
                    } else {
                        final SubLObject low_bound_enumerations = enumerations.enumerate_enumerations(low_bound_value);
                        final SubLObject high_bound_enumerations = enumerations.enumerate_enumerations(high_bound_value);
                        final SubLObject common_enumerations = intersection(low_bound_enumerations, high_bound_enumerations, UNPROVIDED, UNPROVIDED);
                        if (NIL != list_utilities.singletonP(common_enumerations)) {
                            final SubLObject enumeration_type = common_enumerations.first();
                            return values(ENUMERATION_GREATER_OR_EQUAL_P, ENUMERATION_LESS_OR_EQUAL_P, enumeration_type);
                        }
                        return values(NIL, NIL, NIL);
                    }



    }

    public static final SubLObject ranges_incorporate_definition_alt(SubLObject enum_name, SubLObject new_low_bound, SubLObject new_high_bound, SubLObject new_low_bound_type, SubLObject new_high_bound_type, SubLObject new_low_bound_predicate, SubLObject new_high_bound_predicate, SubLObject new_base_type_predicate) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == range_bound_type_p(new_low_bound_type)) {
                    Errors.error($str_alt73$RANGES_INCORPORATE_DEFINITION___S, new_low_bound_type);
                }
            }
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL == range_bound_type_p(new_low_bound_type)) {
                    Errors.error($str_alt73$RANGES_INCORPORATE_DEFINITION___S, new_high_bound_type);
                }
            }
            {
                SubLObject range = ranges_retrieve_range(enum_name);
                if (NIL != range) {
                    ranges_delete_range(enum_name);
                } else {
                    range = make_range(UNPROVIDED);
                }
                {
                    SubLObject range_1 = range;
                    SubLObject type_name = range_type_name(range_1);
                    SubLObject base_type_predicate = range_base_type_predicate(range_1);
                    SubLObject enumeration_type = range_enumeration_type(range_1);
                    SubLObject low_bound = range_low_bound(range_1);
                    SubLObject high_bound = range_high_bound(range_1);
                    SubLObject low_bound_predicate = range_low_bound_predicate(range_1);
                    SubLObject high_bound_predicate = range_high_bound_predicate(range_1);
                    SubLObject low_bound_type = range_low_bound_type(range_1);
                    SubLObject high_bound_type = range_high_bound_type(range_1);
                    try {
                        type_name = enum_name;
                        base_type_predicate = ranges_compute_base_type_predicate(new_low_bound, new_high_bound, new_base_type_predicate);
                        low_bound_type = new_low_bound_type;
                        high_bound_type = new_high_bound_type;
                        low_bound = new_low_bound;
                        high_bound = new_high_bound;
                        if (new_low_bound_type == $INCLUSIVE) {
                            if (new_high_bound_type == $INCLUSIVE) {
                                thread.resetMultipleValues();
                                {
                                    SubLObject low_bound_pred = ranges_compute_inclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                                    SubLObject high_bound_pred = thread.secondMultipleValue();
                                    SubLObject enum_type = thread.thirdMultipleValue();
                                    thread.resetMultipleValues();
                                    if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                        if (NIL == low_bound_pred) {
                                            Errors.error($str_alt74$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                        }
                                    }
                                    if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                        if (NIL == high_bound_pred) {
                                            Errors.error($str_alt75$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                        }
                                    }
                                    low_bound_predicate = low_bound_pred;
                                    high_bound_predicate = high_bound_pred;
                                    enumeration_type = enum_type;
                                }
                            } else {
                                thread.resetMultipleValues();
                                {
                                    SubLObject low_bound_pred = ranges_compute_inclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                                    SubLObject ignore1 = thread.secondMultipleValue();
                                    SubLObject enum_type = thread.thirdMultipleValue();
                                    thread.resetMultipleValues();
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject ignore2 = ranges_compute_exclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                                        SubLObject high_bound_pred = thread.secondMultipleValue();
                                        SubLObject ignore3 = thread.thirdMultipleValue();
                                        thread.resetMultipleValues();
                                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                            if (NIL == low_bound_pred) {
                                                Errors.error($str_alt74$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                            }
                                        }
                                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                            if (NIL == high_bound_pred) {
                                                Errors.error($str_alt75$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                            }
                                        }
                                        low_bound_predicate = low_bound_pred;
                                        high_bound_predicate = high_bound_pred;
                                        enumeration_type = enum_type;
                                    }
                                }
                            }
                        } else {
                            if (new_high_bound_type == $INCLUSIVE) {
                                thread.resetMultipleValues();
                                {
                                    SubLObject low_bound_pred = ranges_compute_exclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                                    SubLObject ignore1 = thread.secondMultipleValue();
                                    SubLObject enum_type = thread.thirdMultipleValue();
                                    thread.resetMultipleValues();
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject ignore2 = ranges_compute_inclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                                        SubLObject high_bound_pred = thread.secondMultipleValue();
                                        SubLObject ignore3 = thread.thirdMultipleValue();
                                        thread.resetMultipleValues();
                                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                            if (NIL == low_bound_pred) {
                                                Errors.error($str_alt74$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                            }
                                        }
                                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                            if (NIL == high_bound_pred) {
                                                Errors.error($str_alt75$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                            }
                                        }
                                        low_bound_predicate = low_bound_pred;
                                        high_bound_predicate = high_bound_pred;
                                        enumeration_type = enum_type;
                                    }
                                }
                            } else {
                                thread.resetMultipleValues();
                                {
                                    SubLObject low_bound_pred = ranges_compute_exclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                                    SubLObject high_bound_pred = thread.secondMultipleValue();
                                    SubLObject enum_type = thread.thirdMultipleValue();
                                    thread.resetMultipleValues();
                                    if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                        if (NIL == low_bound_pred) {
                                            Errors.error($str_alt74$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                        }
                                    }
                                    if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                        if (NIL == high_bound_pred) {
                                            Errors.error($str_alt75$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                                        }
                                    }
                                    low_bound_predicate = low_bound_pred;
                                    high_bound_predicate = high_bound_pred;
                                    enumeration_type = enum_type;
                                }
                            }
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                _csetf_range_type_name(range_1, type_name);
                                _csetf_range_base_type_predicate(range_1, base_type_predicate);
                                _csetf_range_enumeration_type(range_1, enumeration_type);
                                _csetf_range_low_bound(range_1, low_bound);
                                _csetf_range_high_bound(range_1, high_bound);
                                _csetf_range_low_bound_predicate(range_1, low_bound_predicate);
                                _csetf_range_high_bound_predicate(range_1, high_bound_predicate);
                                _csetf_range_low_bound_type(range_1, low_bound_type);
                                _csetf_range_high_bound_type(range_1, high_bound_type);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                }
                range_intern_range(enum_name, range);
                return range;
            }
        }
    }

    public static SubLObject ranges_incorporate_definition(final SubLObject enum_name, final SubLObject new_low_bound, final SubLObject new_high_bound, final SubLObject new_low_bound_type, final SubLObject new_high_bound_type, final SubLObject new_low_bound_predicate, final SubLObject new_high_bound_predicate, final SubLObject new_base_type_predicate) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == range_bound_type_p(new_low_bound_type))) {
            Errors.error($str79$RANGES_INCORPORATE_DEFINITION___S, new_low_bound_type);
        }
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == range_bound_type_p(new_low_bound_type))) {
            Errors.error($str79$RANGES_INCORPORATE_DEFINITION___S, new_high_bound_type);
        }
        SubLObject range = ranges_retrieve_range(enum_name);
        if (NIL != range) {
            ranges_delete_range(enum_name);
        } else {
            range = make_range(UNPROVIDED);
        }
        final SubLObject range_$1 = range;
        SubLObject type_name = range_type_name(range_$1);
        SubLObject base_type_predicate = range_base_type_predicate(range_$1);
        SubLObject enumeration_type = range_enumeration_type(range_$1);
        SubLObject low_bound = range_low_bound(range_$1);
        SubLObject high_bound = range_high_bound(range_$1);
        SubLObject low_bound_predicate = range_low_bound_predicate(range_$1);
        SubLObject high_bound_predicate = range_high_bound_predicate(range_$1);
        SubLObject low_bound_type = range_low_bound_type(range_$1);
        SubLObject high_bound_type = range_high_bound_type(range_$1);
        try {
            type_name = enum_name;
            base_type_predicate = ranges_compute_base_type_predicate(new_low_bound, new_high_bound, new_base_type_predicate);
            low_bound_type = new_low_bound_type;
            high_bound_type = new_high_bound_type;
            low_bound = new_low_bound;
            high_bound = new_high_bound;
            if (new_low_bound_type == $INCLUSIVE) {
                if (new_high_bound_type == $INCLUSIVE) {
                    thread.resetMultipleValues();
                    final SubLObject low_bound_pred = ranges_compute_inclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                    final SubLObject high_bound_pred = thread.secondMultipleValue();
                    final SubLObject enum_type = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == low_bound_pred)) {
                        Errors.error($str80$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == high_bound_pred)) {
                        Errors.error($str81$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    low_bound_predicate = low_bound_pred;
                    high_bound_predicate = high_bound_pred;
                    enumeration_type = enum_type;
                } else {
                    thread.resetMultipleValues();
                    final SubLObject low_bound_pred = ranges_compute_inclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                    final SubLObject ignore1 = thread.secondMultipleValue();
                    final SubLObject enum_type = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    thread.resetMultipleValues();
                    final SubLObject ignore2 = ranges_compute_exclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                    final SubLObject high_bound_pred2 = thread.secondMultipleValue();
                    final SubLObject ignore3 = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == low_bound_pred)) {
                        Errors.error($str80$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == high_bound_pred2)) {
                        Errors.error($str81$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    low_bound_predicate = low_bound_pred;
                    high_bound_predicate = high_bound_pred2;
                    enumeration_type = enum_type;
                }
            } else
                if (new_high_bound_type == $INCLUSIVE) {
                    thread.resetMultipleValues();
                    final SubLObject low_bound_pred = ranges_compute_exclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                    final SubLObject ignore1 = thread.secondMultipleValue();
                    final SubLObject enum_type = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    thread.resetMultipleValues();
                    final SubLObject ignore2 = ranges_compute_inclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                    final SubLObject high_bound_pred2 = thread.secondMultipleValue();
                    final SubLObject ignore3 = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == low_bound_pred)) {
                        Errors.error($str80$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == high_bound_pred2)) {
                        Errors.error($str81$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    low_bound_predicate = low_bound_pred;
                    high_bound_predicate = high_bound_pred2;
                    enumeration_type = enum_type;
                } else {
                    thread.resetMultipleValues();
                    final SubLObject low_bound_pred = ranges_compute_exclusive_bound_predicates(new_low_bound, new_high_bound, new_low_bound_predicate, new_high_bound_predicate);
                    final SubLObject high_bound_pred = thread.secondMultipleValue();
                    final SubLObject enum_type = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == low_bound_pred)) {
                        Errors.error($str80$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == high_bound_pred)) {
                        Errors.error($str81$RANGES_INCORPORATE_DEFINITION__co, enum_name);
                    }
                    low_bound_predicate = low_bound_pred;
                    high_bound_predicate = high_bound_pred;
                    enumeration_type = enum_type;
                }

        } finally {
            final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values = getValuesAsVector();
                _csetf_range_type_name(range_$1, type_name);
                _csetf_range_base_type_predicate(range_$1, base_type_predicate);
                _csetf_range_enumeration_type(range_$1, enumeration_type);
                _csetf_range_low_bound(range_$1, low_bound);
                _csetf_range_high_bound(range_$1, high_bound);
                _csetf_range_low_bound_predicate(range_$1, low_bound_predicate);
                _csetf_range_high_bound_predicate(range_$1, high_bound_predicate);
                _csetf_range_low_bound_type(range_$1, low_bound_type);
                _csetf_range_high_bound_type(range_$1, high_bound_type);
                restoreValuesFromVector(_values);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
            }
        }
        range_intern_range(enum_name, range);
        return range;
    }

    public static final SubLObject ranges_generate_predicate_name_alt(SubLObject range) {
        if (NIL != range) {
            {
                SubLObject range_2 = range;
                SubLObject type_name = range_type_name(range_2);
                return intern(format(NIL, $str_alt76$_S_P, type_name), UNPROVIDED);
            }
        } else {
            return NIL;
        }
    }

    public static SubLObject ranges_generate_predicate_name(final SubLObject range) {
        if (NIL != range) {
            final SubLObject type_name = range_type_name(range);
            return intern(format(NIL, $str82$_S_P, type_name), UNPROVIDED);
        }
        return NIL;
    }

    public static final SubLObject expand_ranges_predicator_alt(SubLObject defining_form, SubLObject range) {
        {
            SubLObject predicate_name = ranges_generate_predicate_name(range);
            if (NIL != predicate_name) {
                {
                    SubLObject range_3 = range;
                    SubLObject base_type_predicate = range_base_type_predicate(range_3);
                    SubLObject low_bound = range_low_bound(range_3);
                    SubLObject high_bound = range_high_bound(range_3);
                    SubLObject low_bound_predicate = range_low_bound_predicate(range_3);
                    SubLObject high_bound_predicate = range_high_bound_predicate(range_3);
                    return list(list(defining_form, predicate_name, $list_alt77, list(RET, bq_cons(CAND, append(new SubLObject[]{ NIL != base_type_predicate ? ((SubLObject) (list(bq_cons(base_type_predicate, $list_alt77)))) : NIL, NIL != low_bound_predicate ? ((SubLObject) (list(list(low_bound_predicate, OBJECT, low_bound)))) : NIL, NIL != high_bound_predicate ? ((SubLObject) (list(list(high_bound_predicate, OBJECT, high_bound)))) : NIL, ((NIL != base_type_predicate) || (NIL != low_bound_predicate)) || (NIL != high_bound_predicate) ? ((SubLObject) (NIL)) : $list_alt81, NIL })))));
                }
            } else {
                return NIL;
            }
        }
    }

    public static SubLObject expand_ranges_predicator(final SubLObject defining_form, final SubLObject range) {
        final SubLObject predicate_name = ranges_generate_predicate_name(range);
        if (NIL != predicate_name) {
            final SubLObject base_type_predicate = range_base_type_predicate(range);
            final SubLObject low_bound = range_low_bound(range);
            final SubLObject high_bound = range_high_bound(range);
            final SubLObject low_bound_predicate = range_low_bound_predicate(range);
            final SubLObject high_bound_predicate = range_high_bound_predicate(range);
            return list(list(defining_form, predicate_name, $list83, list(RET, bq_cons(CAND, append(new SubLObject[]{ NIL != base_type_predicate ? list(bq_cons(base_type_predicate, $list83)) : NIL, NIL != low_bound_predicate ? list(list(low_bound_predicate, OBJECT, low_bound)) : NIL, NIL != high_bound_predicate ? list(list(high_bound_predicate, OBJECT, high_bound)) : NIL, ((NIL != base_type_predicate) || (NIL != low_bound_predicate)) || (NIL != high_bound_predicate) ? NIL : $list87, NIL })))));
        }
        return NIL;
    }

    public static final SubLObject expand_inclusive_range_alt(SubLObject defining_form, SubLObject new_enum_name, SubLObject new_low_bound_value, SubLObject new_high_bound_value, SubLObject new_greater_than_or_equal_to_predicate, SubLObject new_less_than_or_equal_to_predicate, SubLObject new_base_type_predicate) {
        {
            SubLObject range = ranges_incorporate_definition(new_enum_name, new_low_bound_value, new_high_bound_value, $INCLUSIVE, $INCLUSIVE, new_greater_than_or_equal_to_predicate, new_less_than_or_equal_to_predicate, new_base_type_predicate);
            return listS(PROGN, list(new SubLObject[]{ RANGES_INCORPORATE_DEFINITION, list(QUOTE, new_enum_name), list(QUOTE, new_low_bound_value), list(QUOTE, new_high_bound_value), $INCLUSIVE, $INCLUSIVE, list(QUOTE, new_greater_than_or_equal_to_predicate), list(QUOTE, new_less_than_or_equal_to_predicate), list(QUOTE, new_base_type_predicate) }), append(expand_ranges_predicator(defining_form, range), NIL));
        }
    }

    public static SubLObject expand_inclusive_range(final SubLObject defining_form, final SubLObject new_enum_name, final SubLObject new_low_bound_value, final SubLObject new_high_bound_value, final SubLObject new_greater_than_or_equal_to_predicate, final SubLObject new_less_than_or_equal_to_predicate, final SubLObject new_base_type_predicate) {
        final SubLObject range = ranges_incorporate_definition(new_enum_name, new_low_bound_value, new_high_bound_value, $INCLUSIVE, $INCLUSIVE, new_greater_than_or_equal_to_predicate, new_less_than_or_equal_to_predicate, new_base_type_predicate);
        return listS(PROGN, list(new SubLObject[]{ RANGES_INCORPORATE_DEFINITION, list(QUOTE, new_enum_name), list(QUOTE, new_low_bound_value), list(QUOTE, new_high_bound_value), $INCLUSIVE, $INCLUSIVE, list(QUOTE, new_greater_than_or_equal_to_predicate), list(QUOTE, new_less_than_or_equal_to_predicate), list(QUOTE, new_base_type_predicate) }), append(expand_ranges_predicator(defining_form, range), NIL));
    }

    public static final SubLObject define_inclusive_range_with_form_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject defining_form = NIL;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt85);
            defining_form = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt85);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt85);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt85);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt85);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt85);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt85);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt85);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return expand_inclusive_range(defining_form, enum_name, low_bound_value, high_bound_value, less_than_or_equal_to_predicate, greater_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt85);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt85);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_inclusive_range_with_form(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject defining_form = NIL;
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list91);
        defining_form = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list91);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list91);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list91);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list91);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list91);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list91);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list91);
            current = current.rest();
            if (NIL == current) {
                return expand_inclusive_range(defining_form, enum_name, low_bound_value, high_bound_value, less_than_or_equal_to_predicate, greater_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list91);
        } else {
            cdestructuring_bind_error(datum, $list91);
        }
        return NIL;
    }

    public static final SubLObject define_inclusive_range_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_inclusive_range(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_inclusive_range_api_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_API, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_inclusive_range_api(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_API, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_inclusive_range_public_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_PUBLIC, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_inclusive_range_public(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_PUBLIC, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_inclusive_range_protected_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_PROTECTED, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_inclusive_range_protected(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_PROTECTED, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_inclusive_range_private_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_PRIVATE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_inclusive_range_private(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_INCLUSIVE_RANGE_WITH_FORM, DEFINE_PRIVATE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject expand_exclusive_range_alt(SubLObject defining_form, SubLObject new_enum_name, SubLObject new_low_bound_value, SubLObject new_high_bound_value, SubLObject new_greater_than_or_equal_to_predicate, SubLObject new_less_than_or_equal_to_predicate, SubLObject new_base_type_predicate) {
        {
            SubLObject range = ranges_incorporate_definition(new_enum_name, new_low_bound_value, new_high_bound_value, $EXCLUSIVE, $EXCLUSIVE, new_greater_than_or_equal_to_predicate, new_less_than_or_equal_to_predicate, new_base_type_predicate);
            return listS(PROGN, list(new SubLObject[]{ RANGES_INCORPORATE_DEFINITION, list(QUOTE, new_enum_name), list(QUOTE, new_low_bound_value), list(QUOTE, new_high_bound_value), $EXCLUSIVE, $EXCLUSIVE, list(QUOTE, new_greater_than_or_equal_to_predicate), list(QUOTE, new_less_than_or_equal_to_predicate), list(QUOTE, new_base_type_predicate) }), append(expand_ranges_predicator(defining_form, range), NIL));
        }
    }

    public static SubLObject expand_exclusive_range(final SubLObject defining_form, final SubLObject new_enum_name, final SubLObject new_low_bound_value, final SubLObject new_high_bound_value, final SubLObject new_greater_than_or_equal_to_predicate, final SubLObject new_less_than_or_equal_to_predicate, final SubLObject new_base_type_predicate) {
        final SubLObject range = ranges_incorporate_definition(new_enum_name, new_low_bound_value, new_high_bound_value, $EXCLUSIVE, $EXCLUSIVE, new_greater_than_or_equal_to_predicate, new_less_than_or_equal_to_predicate, new_base_type_predicate);
        return listS(PROGN, list(new SubLObject[]{ RANGES_INCORPORATE_DEFINITION, list(QUOTE, new_enum_name), list(QUOTE, new_low_bound_value), list(QUOTE, new_high_bound_value), $EXCLUSIVE, $EXCLUSIVE, list(QUOTE, new_greater_than_or_equal_to_predicate), list(QUOTE, new_less_than_or_equal_to_predicate), list(QUOTE, new_base_type_predicate) }), append(expand_ranges_predicator(defining_form, range), NIL));
    }

    public static final SubLObject define_exclusive_range_with_form_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject defining_form = NIL;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt85);
            defining_form = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt85);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt85);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt85);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt85);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt85);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt85);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt85);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return expand_exclusive_range(defining_form, enum_name, low_bound_value, high_bound_value, less_than_or_equal_to_predicate, greater_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt85);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt85);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_exclusive_range_with_form(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject defining_form = NIL;
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list91);
        defining_form = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list91);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list91);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list91);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list91);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list91);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list91);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list91);
            current = current.rest();
            if (NIL == current) {
                return expand_exclusive_range(defining_form, enum_name, low_bound_value, high_bound_value, less_than_or_equal_to_predicate, greater_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list91);
        } else {
            cdestructuring_bind_error(datum, $list91);
        }
        return NIL;
    }

    public static final SubLObject define_exclusive_range_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_exclusive_range(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_exclusive_range_api_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_API, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_exclusive_range_api(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_API, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_exclusive_range_public_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_PUBLIC, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_exclusive_range_public(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_PUBLIC, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_exclusive_range_protected_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_PROTECTED, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_exclusive_range_protected(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_PROTECTED, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject define_exclusive_range_private_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject enum_name = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt86);
            enum_name = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt86);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject low_bound_value = NIL;
                    SubLObject high_bound_value = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    low_bound_value = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt86);
                    high_bound_value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                            destructuring_bind_must_listp(current, datum, $list_alt86);
                            current = current.rest();
                            {
                                SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                destructuring_bind_must_listp(current, datum, $list_alt86);
                                current = current.rest();
                                {
                                    SubLObject base_type_predicate = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                                    destructuring_bind_must_listp(current, datum, $list_alt86);
                                    current = current.rest();
                                    if (NIL == current) {
                                        return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_PRIVATE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
                                    } else {
                                        cdestructuring_bind_error(datum, $list_alt86);
                                    }
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt86);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject define_exclusive_range_private(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject enum_name = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        enum_name = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject low_bound_value = NIL;
        SubLObject high_bound_value = NIL;
        destructuring_bind_must_consp(current, datum, $list92);
        low_bound_value = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list92);
        high_bound_value = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            final SubLObject greater_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject less_than_or_equal_to_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            final SubLObject base_type_predicate = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, $list92);
            current = current.rest();
            if (NIL == current) {
                return list(DEFINE_EXCLUSIVE_RANGE_WITH_FORM, DEFINE_PRIVATE, enum_name, list(low_bound_value, high_bound_value), greater_than_or_equal_to_predicate, less_than_or_equal_to_predicate, base_type_predicate);
            }
            cdestructuring_bind_error(datum, $list92);
        } else {
            cdestructuring_bind_error(datum, $list92);
        }
        return NIL;
    }

    public static final SubLObject range_member_p_alt(SubLObject range_name, SubLObject value) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (!((NIL != range_name) && range_name.isSymbol())) {
                    Errors.error($str_alt94$RANGE_MEMBER_P___S_is_not_a_valid, range_name);
                }
            }
            {
                SubLObject true_range = ranges_retrieve_range(range_name);
                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                    if (NIL == true_range) {
                        Errors.error($str_alt94$RANGE_MEMBER_P___S_is_not_a_valid, range_name);
                    }
                }
                {
                    SubLObject range = true_range;
                    SubLObject low_bound = range_low_bound(range);
                    SubLObject high_bound = range_high_bound(range);
                    SubLObject low_bound_predicate = range_low_bound_predicate(range);
                    SubLObject high_bound_predicate = range_high_bound_predicate(range);
                    return makeBoolean((NIL != funcall(low_bound_predicate, value, low_bound)) && (NIL != funcall(high_bound_predicate, value, high_bound)));
                }
            }
        }
    }

    public static SubLObject range_member_p(final SubLObject range_name, final SubLObject value) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && ((NIL == range_name) || (!range_name.isSymbol()))) {
            Errors.error($str100$RANGE_MEMBER_P___S_is_not_a_valid, range_name);
        }
        final SubLObject true_range = ranges_retrieve_range(range_name);
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL == true_range)) {
            Errors.error($str100$RANGE_MEMBER_P___S_is_not_a_valid, range_name);
        }
        final SubLObject range = true_range;
        final SubLObject low_bound = range_low_bound(range);
        final SubLObject high_bound = range_high_bound(range);
        final SubLObject low_bound_predicate = range_low_bound_predicate(range);
        final SubLObject high_bound_predicate = range_high_bound_predicate(range);
        return makeBoolean((NIL != funcall(low_bound_predicate, value, low_bound)) && (NIL != funcall(high_bound_predicate, value, high_bound)));
    }

    public static SubLObject declare_ranges_file() {
        declareFunction("valid_range_bound_types", "VALID-RANGE-BOUND-TYPES", 0, 0, false);
        declareFunction("range_bound_type_p", "RANGE-BOUND-TYPE-P", 1, 0, false);
        declareFunction("encode_range_bound_type", "ENCODE-RANGE-BOUND-TYPE", 1, 0, false);
        declareFunction("decode_range_bound_type", "DECODE-RANGE-BOUND-TYPE", 1, 0, false);
        declareFunction("range_bound_type_less_p", "RANGE-BOUND-TYPE-LESS-P", 2, 0, false);
        declareFunction("range_bound_type_greater_p", "RANGE-BOUND-TYPE-GREATER-P", 2, 0, false);
        declareFunction("range_print_function_trampoline", "RANGE-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("range_p", "RANGE-P", 1, 0, false);
        new ranges.$range_p$UnaryFunction();
        declareFunction("range_type_name", "RANGE-TYPE-NAME", 1, 0, false);
        declareFunction("range_base_type_predicate", "RANGE-BASE-TYPE-PREDICATE", 1, 0, false);
        declareFunction("range_enumeration_type", "RANGE-ENUMERATION-TYPE", 1, 0, false);
        declareFunction("range_low_bound", "RANGE-LOW-BOUND", 1, 0, false);
        declareFunction("range_high_bound", "RANGE-HIGH-BOUND", 1, 0, false);
        declareFunction("range_low_bound_predicate", "RANGE-LOW-BOUND-PREDICATE", 1, 0, false);
        declareFunction("range_high_bound_predicate", "RANGE-HIGH-BOUND-PREDICATE", 1, 0, false);
        declareFunction("range_low_bound_type", "RANGE-LOW-BOUND-TYPE", 1, 0, false);
        declareFunction("range_high_bound_type", "RANGE-HIGH-BOUND-TYPE", 1, 0, false);
        declareFunction("_csetf_range_type_name", "_CSETF-RANGE-TYPE-NAME", 2, 0, false);
        declareFunction("_csetf_range_base_type_predicate", "_CSETF-RANGE-BASE-TYPE-PREDICATE", 2, 0, false);
        declareFunction("_csetf_range_enumeration_type", "_CSETF-RANGE-ENUMERATION-TYPE", 2, 0, false);
        declareFunction("_csetf_range_low_bound", "_CSETF-RANGE-LOW-BOUND", 2, 0, false);
        declareFunction("_csetf_range_high_bound", "_CSETF-RANGE-HIGH-BOUND", 2, 0, false);
        declareFunction("_csetf_range_low_bound_predicate", "_CSETF-RANGE-LOW-BOUND-PREDICATE", 2, 0, false);
        declareFunction("_csetf_range_high_bound_predicate", "_CSETF-RANGE-HIGH-BOUND-PREDICATE", 2, 0, false);
        declareFunction("_csetf_range_low_bound_type", "_CSETF-RANGE-LOW-BOUND-TYPE", 2, 0, false);
        declareFunction("_csetf_range_high_bound_type", "_CSETF-RANGE-HIGH-BOUND-TYPE", 2, 0, false);
        declareFunction("make_range", "MAKE-RANGE", 0, 1, false);
        declareFunction("visit_defstruct_range", "VISIT-DEFSTRUCT-RANGE", 2, 0, false);
        declareFunction("visit_defstruct_object_range_method", "VISIT-DEFSTRUCT-OBJECT-RANGE-METHOD", 2, 0, false);
        declareMacro("with_range_read_only", "WITH-RANGE-READ-ONLY");
        declareMacro("with_range_read_write", "WITH-RANGE-READ-WRITE");
        declareFunction("print_range", "PRINT-RANGE", 3, 0, false);
        declareFunction("ranges_retrieve_range", "RANGES-RETRIEVE-RANGE", 1, 0, false);
        declareFunction("range_intern_range", "RANGE-INTERN-RANGE", 2, 0, false);
        declareFunction("ranges_delete_range", "RANGES-DELETE-RANGE", 1, 0, false);
        declareFunction("ranges_compute_base_type_predicate", "RANGES-COMPUTE-BASE-TYPE-PREDICATE", 3, 0, false);
        declareFunction("ranges_compute_exclusive_bound_predicates", "RANGES-COMPUTE-EXCLUSIVE-BOUND-PREDICATES", 4, 0, false);
        declareFunction("ranges_compute_inclusive_bound_predicates", "RANGES-COMPUTE-INCLUSIVE-BOUND-PREDICATES", 4, 0, false);
        declareFunction("ranges_incorporate_definition", "RANGES-INCORPORATE-DEFINITION", 8, 0, false);
        declareFunction("ranges_generate_predicate_name", "RANGES-GENERATE-PREDICATE-NAME", 1, 0, false);
        declareFunction("expand_ranges_predicator", "EXPAND-RANGES-PREDICATOR", 2, 0, false);
        declareFunction("expand_inclusive_range", "EXPAND-INCLUSIVE-RANGE", 7, 0, false);
        declareMacro("define_inclusive_range_with_form", "DEFINE-INCLUSIVE-RANGE-WITH-FORM");
        declareMacro("define_inclusive_range", "DEFINE-INCLUSIVE-RANGE");
        declareMacro("define_inclusive_range_api", "DEFINE-INCLUSIVE-RANGE-API");
        declareMacro("define_inclusive_range_public", "DEFINE-INCLUSIVE-RANGE-PUBLIC");
        declareMacro("define_inclusive_range_protected", "DEFINE-INCLUSIVE-RANGE-PROTECTED");
        declareMacro("define_inclusive_range_private", "DEFINE-INCLUSIVE-RANGE-PRIVATE");
        declareFunction("expand_exclusive_range", "EXPAND-EXCLUSIVE-RANGE", 7, 0, false);
        declareMacro("define_exclusive_range_with_form", "DEFINE-EXCLUSIVE-RANGE-WITH-FORM");
        declareMacro("define_exclusive_range", "DEFINE-EXCLUSIVE-RANGE");
        declareMacro("define_exclusive_range_api", "DEFINE-EXCLUSIVE-RANGE-API");
        declareMacro("define_exclusive_range_public", "DEFINE-EXCLUSIVE-RANGE-PUBLIC");
        declareMacro("define_exclusive_range_protected", "DEFINE-EXCLUSIVE-RANGE-PROTECTED");
        declareMacro("define_exclusive_range_private", "DEFINE-EXCLUSIVE-RANGE-PRIVATE");
        declareFunction("range_member_p", "RANGE-MEMBER-P", 2, 0, false);
        return NIL;
    }

    public static final SubLObject init_ranges_file_alt() {
        defconstant("*VALID-RANGE-BOUND-TYPES*", $list_alt0);
        defconstant("*RANGES-INITIAL-HASHTABLE-SIZE*", $int$100);
        defparameter("*RANGES-TABLE-UPDATE-LOCK*", make_lock($$$Ranges_Table_Lock));
        deflexical("*RANGES-TABLE*", NIL != boundp($ranges_table$) ? ((SubLObject) ($ranges_table$.getGlobalValue())) : make_hash_table($ranges_initial_hashtable_size$.getGlobalValue(), UNPROVIDED, UNPROVIDED));
        defconstant("*DTP-RANGE*", RANGE);
        return NIL;
    }

    public static SubLObject init_ranges_file() {
        if (SubLFiles.USE_V1) {
            defconstant("*VALID-RANGE-BOUND-TYPES*", $list0);
            defconstant("*RANGES-INITIAL-HASHTABLE-SIZE*", $int$100);
            defparameter("*RANGES-TABLE-UPDATE-LOCK*", make_lock($$$Ranges_Table_Lock));
            deflexical("*RANGES-TABLE*", SubLTrampolineFile.maybeDefault($ranges_table$, $ranges_table$, () -> make_hash_table($ranges_initial_hashtable_size$.getGlobalValue(), UNPROVIDED, UNPROVIDED)));
            defconstant("*DTP-RANGE*", RANGE);
        }
        if (SubLFiles.USE_V2) {
            deflexical("*RANGES-TABLE*", NIL != boundp($ranges_table$) ? ((SubLObject) ($ranges_table$.getGlobalValue())) : make_hash_table($ranges_initial_hashtable_size$.getGlobalValue(), UNPROVIDED, UNPROVIDED));
        }
        return NIL;
    }

    public static SubLObject init_ranges_file_Previous() {
        defconstant("*VALID-RANGE-BOUND-TYPES*", $list0);
        defconstant("*RANGES-INITIAL-HASHTABLE-SIZE*", $int$100);
        defparameter("*RANGES-TABLE-UPDATE-LOCK*", make_lock($$$Ranges_Table_Lock));
        deflexical("*RANGES-TABLE*", SubLTrampolineFile.maybeDefault($ranges_table$, $ranges_table$, () -> make_hash_table($ranges_initial_hashtable_size$.getGlobalValue(), UNPROVIDED, UNPROVIDED)));
        defconstant("*DTP-RANGE*", RANGE);
        return NIL;
    }

    public static SubLObject setup_ranges_file() {
        enumerations.enumerations_incorporate_definition(RANGE_BOUND_TYPE, $list0);
        declare_defglobal($ranges_table$);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_range$.getGlobalValue(), symbol_function(RANGE_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list19);
        def_csetf(RANGE_TYPE_NAME, _CSETF_RANGE_TYPE_NAME);
        def_csetf(RANGE_BASE_TYPE_PREDICATE, _CSETF_RANGE_BASE_TYPE_PREDICATE);
        def_csetf(RANGE_ENUMERATION_TYPE, _CSETF_RANGE_ENUMERATION_TYPE);
        def_csetf(RANGE_LOW_BOUND, _CSETF_RANGE_LOW_BOUND);
        def_csetf(RANGE_HIGH_BOUND, _CSETF_RANGE_HIGH_BOUND);
        def_csetf(RANGE_LOW_BOUND_PREDICATE, _CSETF_RANGE_LOW_BOUND_PREDICATE);
        def_csetf(RANGE_HIGH_BOUND_PREDICATE, _CSETF_RANGE_HIGH_BOUND_PREDICATE);
        def_csetf(RANGE_LOW_BOUND_TYPE, _CSETF_RANGE_LOW_BOUND_TYPE);
        def_csetf(RANGE_HIGH_BOUND_TYPE, _CSETF_RANGE_HIGH_BOUND_TYPE);
        identity(RANGE);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_range$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_RANGE_METHOD));
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_ranges_file();
    }

    @Override
    public void initializeVariables() {
        init_ranges_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_ranges_file();
    }

    static {
    }

    public static final class $range_native extends SubLStructNative {
        public SubLObject $type_name;

        public SubLObject $base_type_predicate;

        public SubLObject $enumeration_type;

        public SubLObject $low_bound;

        public SubLObject $high_bound;

        public SubLObject $low_bound_predicate;

        public SubLObject $high_bound_predicate;

        public SubLObject $low_bound_type;

        public SubLObject $high_bound_type;

        private static final SubLStructDeclNative structDecl;

        public $range_native() {
            ranges.$range_native.this.$type_name = Lisp.NIL;
            ranges.$range_native.this.$base_type_predicate = Lisp.NIL;
            ranges.$range_native.this.$enumeration_type = Lisp.NIL;
            ranges.$range_native.this.$low_bound = Lisp.NIL;
            ranges.$range_native.this.$high_bound = Lisp.NIL;
            ranges.$range_native.this.$low_bound_predicate = Lisp.NIL;
            ranges.$range_native.this.$high_bound_predicate = Lisp.NIL;
            ranges.$range_native.this.$low_bound_type = Lisp.NIL;
            ranges.$range_native.this.$high_bound_type = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return ranges.$range_native.this.$type_name;
        }

        @Override
        public SubLObject getField3() {
            return ranges.$range_native.this.$base_type_predicate;
        }

        @Override
        public SubLObject getField4() {
            return ranges.$range_native.this.$enumeration_type;
        }

        @Override
        public SubLObject getField5() {
            return ranges.$range_native.this.$low_bound;
        }

        @Override
        public SubLObject getField6() {
            return ranges.$range_native.this.$high_bound;
        }

        @Override
        public SubLObject getField7() {
            return ranges.$range_native.this.$low_bound_predicate;
        }

        @Override
        public SubLObject getField8() {
            return ranges.$range_native.this.$high_bound_predicate;
        }

        @Override
        public SubLObject getField9() {
            return ranges.$range_native.this.$low_bound_type;
        }

        @Override
        public SubLObject getField10() {
            return ranges.$range_native.this.$high_bound_type;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return ranges.$range_native.this.$type_name = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return ranges.$range_native.this.$base_type_predicate = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return ranges.$range_native.this.$enumeration_type = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return ranges.$range_native.this.$low_bound = value;
        }

        @Override
        public SubLObject setField6(final SubLObject value) {
            return ranges.$range_native.this.$high_bound = value;
        }

        @Override
        public SubLObject setField7(final SubLObject value) {
            return ranges.$range_native.this.$low_bound_predicate = value;
        }

        @Override
        public SubLObject setField8(final SubLObject value) {
            return ranges.$range_native.this.$high_bound_predicate = value;
        }

        @Override
        public SubLObject setField9(final SubLObject value) {
            return ranges.$range_native.this.$low_bound_type = value;
        }

        @Override
        public SubLObject setField10(final SubLObject value) {
            return ranges.$range_native.this.$high_bound_type = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.ranges.$range_native.class, RANGE, RANGE_P, $list13, $list14, new String[]{ "$type_name", "$base_type_predicate", "$enumeration_type", "$low_bound", "$high_bound", "$low_bound_predicate", "$high_bound_predicate", "$low_bound_type", "$high_bound_type" }, $list15, $list16, PRINT_RANGE);
        }
    }

    public static final class $range_p$UnaryFunction extends UnaryFunction {
        public $range_p$UnaryFunction() {
            super(extractFunctionNamed("RANGE-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return range_p(arg1);
        }
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list_alt0 = list(makeKeyword("INCLUSIVE"), makeKeyword("EXCLUSIVE"));

    static private final SubLString $str_alt2$_S___S_is_not_a_member_of_the__S_ = makeString("~S: ~S is not a member of the ~S enumeration.");

    static private final SubLString $str_alt4$_S___S_is_not_a_valid_encoding_of = makeString("~S: ~S is not a valid encoding of the ~S enumeration.");

    static private final SubLString $str_alt6$_S___S_was_expected_to_be_a_membe = makeString("~S: ~S was expected to be a member of the enumeration ~S.");

    static private final SubLList $list_alt13 = list(new SubLObject[]{ makeSymbol("TYPE-NAME"), makeSymbol("BASE-TYPE-PREDICATE"), makeSymbol("ENUMERATION-TYPE"), makeSymbol("LOW-BOUND"), makeSymbol("HIGH-BOUND"), makeSymbol("LOW-BOUND-PREDICATE"), makeSymbol("HIGH-BOUND-PREDICATE"), makeSymbol("LOW-BOUND-TYPE"), makeSymbol("HIGH-BOUND-TYPE") });

    static private final SubLList $list_alt14 = list(new SubLObject[]{ makeKeyword("TYPE-NAME"), makeKeyword("BASE-TYPE-PREDICATE"), makeKeyword("ENUMERATION-TYPE"), makeKeyword("LOW-BOUND"), makeKeyword("HIGH-BOUND"), makeKeyword("LOW-BOUND-PREDICATE"), makeKeyword("HIGH-BOUND-PREDICATE"), makeKeyword("LOW-BOUND-TYPE"), makeKeyword("HIGH-BOUND-TYPE") });

    static private final SubLList $list_alt15 = list(new SubLObject[]{ makeSymbol("RANGE-TYPE-NAME"), makeSymbol("RANGE-BASE-TYPE-PREDICATE"), makeSymbol("RANGE-ENUMERATION-TYPE"), makeSymbol("RANGE-LOW-BOUND"), makeSymbol("RANGE-HIGH-BOUND"), makeSymbol("RANGE-LOW-BOUND-PREDICATE"), makeSymbol("RANGE-HIGH-BOUND-PREDICATE"), makeSymbol("RANGE-LOW-BOUND-TYPE"), makeSymbol("RANGE-HIGH-BOUND-TYPE") });

    static private final SubLList $list_alt16 = list(new SubLObject[]{ makeSymbol("_CSETF-RANGE-TYPE-NAME"), makeSymbol("_CSETF-RANGE-BASE-TYPE-PREDICATE"), makeSymbol("_CSETF-RANGE-ENUMERATION-TYPE"), makeSymbol("_CSETF-RANGE-LOW-BOUND"), makeSymbol("_CSETF-RANGE-HIGH-BOUND"), makeSymbol("_CSETF-RANGE-LOW-BOUND-PREDICATE"), makeSymbol("_CSETF-RANGE-HIGH-BOUND-PREDICATE"), makeSymbol("_CSETF-RANGE-LOW-BOUND-TYPE"), makeSymbol("_CSETF-RANGE-HIGH-BOUND-TYPE") });

    static private final SubLString $str_alt46$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLList $list_alt47 = list(makeSymbol("RANGE"), makeSymbol("&BODY"), makeSymbol("FORMS"));

    static private final SubLString $str_alt52$_C_S___S_C = makeString("~C~S, ~S~C");

    static private final SubLSymbol $sym57$_ = makeSymbol(">");

    static private final SubLSymbol $sym58$_ = makeSymbol("<");

    static private final SubLSymbol $sym59$CHAR_ = makeSymbol("CHAR>");

    static private final SubLSymbol $sym60$CHAR_ = makeSymbol("CHAR<");

    static private final SubLSymbol $sym61$STRING_ = makeSymbol("STRING>");

    static private final SubLSymbol $sym62$STRING_ = makeSymbol("STRING<");

    static private final SubLSymbol $sym65$__ = makeSymbol(">=");

    static private final SubLSymbol $sym66$__ = makeSymbol("<=");

    static private final SubLSymbol $sym67$CHAR__ = makeSymbol("CHAR>=");

    static private final SubLSymbol $sym68$CHAR__ = makeSymbol("CHAR<=");

    static private final SubLSymbol $sym69$STRING__ = makeSymbol("STRING>=");

    static private final SubLSymbol $sym70$STRING__ = makeSymbol("STRING<=");

    static private final SubLString $str_alt73$RANGES_INCORPORATE_DEFINITION___S = makeString("RANGES-INCORPORATE-DEFINITION: ~S is not a legal range bound type.");

    static private final SubLString $str_alt74$RANGES_INCORPORATE_DEFINITION__co = makeString("RANGES-INCORPORATE-DEFINITION: could not determine a lower bound predicate for range type ~S.");

    static private final SubLString $str_alt75$RANGES_INCORPORATE_DEFINITION__co = makeString("RANGES-INCORPORATE-DEFINITION: could not determine an upper bound predicate for range type ~S.");

    static private final SubLString $str_alt76$_S_P = makeString("~S-P");

    static private final SubLList $list_alt77 = list(makeSymbol("OBJECT"));

    static private final SubLList $list_alt81 = list(T);

    static private final SubLList $list_alt85 = list(makeSymbol("DEFINING-FORM"), makeSymbol("ENUM-NAME"), list(makeSymbol("LOW-BOUND-VALUE"), makeSymbol("HIGH-BOUND-VALUE")), makeSymbol("&OPTIONAL"), list(makeSymbol("GREATER-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("LESS-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("BASE-TYPE-PREDICATE"), NIL));

    static private final SubLList $list_alt86 = list(makeSymbol("ENUM-NAME"), list(makeSymbol("LOW-BOUND-VALUE"), makeSymbol("HIGH-BOUND-VALUE")), makeSymbol("&OPTIONAL"), list(makeSymbol("GREATER-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("LESS-THAN-OR-EQUAL-TO-PREDICATE"), NIL), list(makeSymbol("BASE-TYPE-PREDICATE"), NIL));

    static private final SubLString $str_alt94$RANGE_MEMBER_P___S_is_not_a_valid = makeString("RANGE-MEMBER-P: ~S is not a valid range.");
}

/**
 * Total time: 352 ms
 */
