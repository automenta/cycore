/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.access_macros.register_macro_helper;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_greater;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_space;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.pointer;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.make_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.release_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.seize_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.write;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.type_of;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.property_list_member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_readably$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.princ;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.print_not_readable;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_char;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.write_string;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

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
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_macros;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      GLOB
 * source file: /cyc/top/cycl/glob.lisp
 * created:     2019/07/03 17:37:14
 */
public final class glob extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new glob();

 public static final String myName = "com.cyc.cycjava.cycl.glob";


    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_glob$ = makeSymbol("*DTP-GLOB*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $default_initial_glob_size$ = makeSymbol("*DEFAULT-INITIAL-GLOB-SIZE*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    private static final SubLSymbol GLOB = makeSymbol("GLOB");

    private static final SubLSymbol GLOB_P = makeSymbol("GLOB-P");

    static private final SubLList $list2 = list(makeSymbol("TEST"), makeSymbol("MAX-SIZE"), makeSymbol("ID-STATE"), makeSymbol("LOCK"), makeSymbol("INDEX"), makeSymbol("BACK-INDEX"), makeSymbol("OWNER"));

    static private final SubLList $list3 = list($TEST, makeKeyword("MAX-SIZE"), makeKeyword("ID-STATE"), $LOCK, makeKeyword("INDEX"), makeKeyword("BACK-INDEX"), makeKeyword("OWNER"));

    static private final SubLList $list4 = list(makeSymbol("GLOB-STRUC-TEST"), makeSymbol("GLOB-STRUC-MAX-SIZE"), makeSymbol("GLOB-STRUC-ID-STATE"), makeSymbol("GLOB-STRUC-LOCK"), makeSymbol("GLOB-STRUC-INDEX"), makeSymbol("GLOB-STRUC-BACK-INDEX"), makeSymbol("GLOB-STRUC-OWNER"));

    static private final SubLList $list5 = list(makeSymbol("_CSETF-GLOB-STRUC-TEST"), makeSymbol("_CSETF-GLOB-STRUC-MAX-SIZE"), makeSymbol("_CSETF-GLOB-STRUC-ID-STATE"), makeSymbol("_CSETF-GLOB-STRUC-LOCK"), makeSymbol("_CSETF-GLOB-STRUC-INDEX"), makeSymbol("_CSETF-GLOB-STRUC-BACK-INDEX"), makeSymbol("_CSETF-GLOB-STRUC-OWNER"));

    private static final SubLSymbol PRINT_GLOB = makeSymbol("PRINT-GLOB");

    private static final SubLSymbol GLOB_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("GLOB-PRINT-FUNCTION-TRAMPOLINE");

    private static final SubLList $list8 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("GLOB-P"));

    private static final SubLSymbol GLOB_STRUC_TEST = makeSymbol("GLOB-STRUC-TEST");

    private static final SubLSymbol _CSETF_GLOB_STRUC_TEST = makeSymbol("_CSETF-GLOB-STRUC-TEST");

    private static final SubLSymbol GLOB_STRUC_MAX_SIZE = makeSymbol("GLOB-STRUC-MAX-SIZE");

    private static final SubLSymbol _CSETF_GLOB_STRUC_MAX_SIZE = makeSymbol("_CSETF-GLOB-STRUC-MAX-SIZE");

    private static final SubLSymbol GLOB_STRUC_ID_STATE = makeSymbol("GLOB-STRUC-ID-STATE");

    private static final SubLSymbol _CSETF_GLOB_STRUC_ID_STATE = makeSymbol("_CSETF-GLOB-STRUC-ID-STATE");

    private static final SubLSymbol GLOB_STRUC_LOCK = makeSymbol("GLOB-STRUC-LOCK");

    private static final SubLSymbol _CSETF_GLOB_STRUC_LOCK = makeSymbol("_CSETF-GLOB-STRUC-LOCK");

    private static final SubLSymbol GLOB_STRUC_INDEX = makeSymbol("GLOB-STRUC-INDEX");

    private static final SubLSymbol _CSETF_GLOB_STRUC_INDEX = makeSymbol("_CSETF-GLOB-STRUC-INDEX");

    private static final SubLSymbol GLOB_STRUC_BACK_INDEX = makeSymbol("GLOB-STRUC-BACK-INDEX");

    private static final SubLSymbol _CSETF_GLOB_STRUC_BACK_INDEX = makeSymbol("_CSETF-GLOB-STRUC-BACK-INDEX");

    private static final SubLSymbol GLOB_STRUC_OWNER = makeSymbol("GLOB-STRUC-OWNER");

    private static final SubLSymbol _CSETF_GLOB_STRUC_OWNER = makeSymbol("_CSETF-GLOB-STRUC-OWNER");

    private static final SubLString $str30$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_GLOB = makeSymbol("MAKE-GLOB");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_GLOB_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-GLOB-METHOD");

    private static final SubLString $str36$_ = makeString("(");

    private static final SubLString $str37$_ = makeString(")");

    private static final SubLString $str38$_size_ = makeString(" size=");

    private static final SubLString $str39$_owner_ = makeString(" owner=");

    private static final SubLList $list40 = list(makeSymbol("GLOB"), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLInteger $int$50 = makeInteger(50);

    private static final SubLString $$$glob_lock = makeString("glob lock");

    private static final SubLSymbol GLOB_ID_P = makeSymbol("GLOB-ID-P");

    private static final SubLList $list49 = list(list(makeSymbol("ID"), makeSymbol("OBJECT"), makeSymbol("GLOB"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLList $list50 = list($DONE);

    private static final SubLSymbol $ALLOW_OTHER_KEYS = makeKeyword("ALLOW-OTHER-KEYS");

    private static final SubLSymbol DO_GLOB_INDEX = makeSymbol("DO-GLOB-INDEX");

    private static final SubLSymbol DO_GLOB = makeSymbol("DO-GLOB");

    static final boolean assertionsDisabledSynth = true;

    public static final SubLObject glob_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        print_glob(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject glob_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        print_glob(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject glob_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.glob.$glob_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject glob_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.glob.$glob_native.class ? T : NIL;
    }

    public static final SubLObject glob_struc_test_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.getField2();
    }

    public static SubLObject glob_struc_test(final SubLObject v_object) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject glob_struc_max_size_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.getField3();
    }

    public static SubLObject glob_struc_max_size(final SubLObject v_object) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject glob_struc_id_state_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.getField4();
    }

    public static SubLObject glob_struc_id_state(final SubLObject v_object) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject glob_struc_lock_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.getField5();
    }

    public static SubLObject glob_struc_lock(final SubLObject v_object) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject glob_struc_index_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.getField6();
    }

    public static SubLObject glob_struc_index(final SubLObject v_object) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject glob_struc_back_index_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.getField7();
    }

    public static SubLObject glob_struc_back_index(final SubLObject v_object) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject glob_struc_owner_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.getField8();
    }

    public static SubLObject glob_struc_owner(final SubLObject v_object) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.getField8();
    }

    public static final SubLObject _csetf_glob_struc_test_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_glob_struc_test(final SubLObject v_object, final SubLObject value) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_glob_struc_max_size_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_glob_struc_max_size(final SubLObject v_object, final SubLObject value) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_glob_struc_id_state_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_glob_struc_id_state(final SubLObject v_object, final SubLObject value) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_glob_struc_lock_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_glob_struc_lock(final SubLObject v_object, final SubLObject value) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_glob_struc_index_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_glob_struc_index(final SubLObject v_object, final SubLObject value) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_glob_struc_back_index_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_glob_struc_back_index(final SubLObject v_object, final SubLObject value) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject _csetf_glob_struc_owner_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, GLOB_P);
        return v_object.setField8(value);
    }

    public static SubLObject _csetf_glob_struc_owner(final SubLObject v_object, final SubLObject value) {
        assert NIL != glob_p(v_object) : "! glob.glob_p(v_object) " + "glob.glob_p error :" + v_object;
        return v_object.setField8(value);
    }

    public static final SubLObject make_glob_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.glob.$glob_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($TEST)) {
                        _csetf_glob_struc_test(v_new, current_value);
                    } else {
                        if (pcase_var.eql($MAX_SIZE)) {
                            _csetf_glob_struc_max_size(v_new, current_value);
                        } else {
                            if (pcase_var.eql($ID_STATE)) {
                                _csetf_glob_struc_id_state(v_new, current_value);
                            } else {
                                if (pcase_var.eql($LOCK)) {
                                    _csetf_glob_struc_lock(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($INDEX)) {
                                        _csetf_glob_struc_index(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($BACK_INDEX)) {
                                            _csetf_glob_struc_back_index(v_new, current_value);
                                        } else {
                                            if (pcase_var.eql($OWNER)) {
                                                _csetf_glob_struc_owner(v_new, current_value);
                                            } else {
                                                Errors.error($str_alt29$Invalid_slot__S_for_construction_, current_arg);
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

    public static SubLObject make_glob(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.glob.$glob_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($TEST)) {
                _csetf_glob_struc_test(v_new, current_value);
            } else
                if (pcase_var.eql($MAX_SIZE)) {
                    _csetf_glob_struc_max_size(v_new, current_value);
                } else
                    if (pcase_var.eql($ID_STATE)) {
                        _csetf_glob_struc_id_state(v_new, current_value);
                    } else
                        if (pcase_var.eql($LOCK)) {
                            _csetf_glob_struc_lock(v_new, current_value);
                        } else
                            if (pcase_var.eql($INDEX)) {
                                _csetf_glob_struc_index(v_new, current_value);
                            } else
                                if (pcase_var.eql($BACK_INDEX)) {
                                    _csetf_glob_struc_back_index(v_new, current_value);
                                } else
                                    if (pcase_var.eql($OWNER)) {
                                        _csetf_glob_struc_owner(v_new, current_value);
                                    } else {
                                        Errors.error($str30$Invalid_slot__S_for_construction_, current_arg);
                                    }






        }
        return v_new;
    }

    public static SubLObject visit_defstruct_glob(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, MAKE_GLOB, SEVEN_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $TEST, glob_struc_test(obj));
        funcall(visitor_fn, obj, $SLOT, $MAX_SIZE, glob_struc_max_size(obj));
        funcall(visitor_fn, obj, $SLOT, $ID_STATE, glob_struc_id_state(obj));
        funcall(visitor_fn, obj, $SLOT, $LOCK, glob_struc_lock(obj));
        funcall(visitor_fn, obj, $SLOT, $INDEX, glob_struc_index(obj));
        funcall(visitor_fn, obj, $SLOT, $BACK_INDEX, glob_struc_back_index(obj));
        funcall(visitor_fn, obj, $SLOT, $OWNER, glob_struc_owner(obj));
        funcall(visitor_fn, obj, $END, MAKE_GLOB, SEVEN_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_glob_method(final SubLObject obj, final SubLObject visitor_fn) {
        return visit_defstruct_glob(obj, visitor_fn);
    }

    public static final SubLObject print_glob_alt(SubLObject v_object, SubLObject stream, SubLObject depth) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != $print_readably$.getDynamicValue(thread)) {
                print_not_readable(v_object, stream);
            } else {
                {
                    SubLObject v_object_1 = v_object;
                    SubLObject stream_2 = stream;
                    write_string($str_alt30$__, stream_2, UNPROVIDED, UNPROVIDED);
                    write(type_of(v_object_1), new SubLObject[]{ $STREAM, stream_2 });
                    write_char(CHAR_space, stream_2);
                    write_string($str_alt32$_, stream, UNPROVIDED, UNPROVIDED);
                    princ(hash_table_utilities.hash_test_to_symbol(glob_test(v_object)), stream);
                    write_string($str_alt33$_, stream, UNPROVIDED, UNPROVIDED);
                    write_string($str_alt34$_size_, stream, UNPROVIDED, UNPROVIDED);
                    princ(glob_size(v_object), stream);
                    if (NIL != glob_owner(v_object)) {
                        write_string($str_alt35$_owner_, stream, UNPROVIDED, UNPROVIDED);
                        princ(glob_owner(v_object), stream);
                    }
                    write_char(CHAR_space, stream_2);
                    write(pointer(v_object_1), new SubLObject[]{ $STREAM, stream_2, $BASE, SIXTEEN_INTEGER });
                    write_char(CHAR_greater, stream_2);
                }
            }
            return v_object;
        }
    }

    public static SubLObject print_glob(final SubLObject v_object, final SubLObject stream, final SubLObject depth) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $print_readably$.getDynamicValue(thread)) {
            print_not_readable(v_object, stream);
        } else {
            print_macros.print_unreadable_object_preamble(stream, v_object, T, T);
            write_string($str36$_, stream, UNPROVIDED, UNPROVIDED);
            princ(hash_table_utilities.hash_test_to_symbol(glob_test(v_object)), stream);
            write_string($str37$_, stream, UNPROVIDED, UNPROVIDED);
            write_string($str38$_size_, stream, UNPROVIDED, UNPROVIDED);
            princ(glob_size(v_object), stream);
            if (NIL != glob_owner(v_object)) {
                write_string($str39$_owner_, stream, UNPROVIDED, UNPROVIDED);
                princ(glob_owner(v_object), stream);
            }
            print_macros.print_unreadable_object_postamble(stream, v_object, T, T);
        }
        return v_object;
    }

    public static final SubLObject with_glob_lock_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject v_glob = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt37);
            v_glob = current.first();
            current = current.rest();
            {
                SubLObject body = current;
                return listS(WITH_LOCK_HELD, list(list(GLOB_STRUC_LOCK, v_glob)), append(body, NIL));
            }
        }
    }

    public static SubLObject with_glob_lock(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject v_glob = NIL;
        destructuring_bind_must_consp(current, datum, $list40);
        v_glob = current.first();
        final SubLObject body;
        current = body = current.rest();
        return listS(WITH_LOCK_HELD, list(list(GLOB_STRUC_LOCK, v_glob)), append(body, NIL));
    }

    public static final SubLObject new_glob_id_state_alt() {
        return integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject new_glob_id_state() {
        return integer_sequence_generator.new_integer_sequence_generator(UNPROVIDED, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject glob_id_state_reset_alt(SubLObject v_glob) {
        {
            SubLObject id_state = glob_struc_id_state(v_glob);
            return integer_sequence_generator.integer_sequence_generator_reset(id_state);
        }
    }

    public static SubLObject glob_id_state_reset(final SubLObject v_glob) {
        final SubLObject id_state = glob_struc_id_state(v_glob);
        return integer_sequence_generator.integer_sequence_generator_reset(id_state);
    }

    public static final SubLObject glob_id_state_next_alt(SubLObject v_glob) {
        {
            SubLObject id_state = glob_struc_id_state(v_glob);
            return integer_sequence_generator.integer_sequence_generator_next(id_state);
        }
    }

    public static SubLObject glob_id_state_next(final SubLObject v_glob) {
        final SubLObject id_state = glob_struc_id_state(v_glob);
        return integer_sequence_generator.integer_sequence_generator_next(id_state);
    }

    public static final SubLObject new_glob_index_alt(SubLObject test, SubLObject initial_size) {
        return dictionary.new_dictionary(test, initial_size);
    }

    public static SubLObject new_glob_index(final SubLObject test, final SubLObject initial_size) {
        return dictionary.new_dictionary(test, initial_size);
    }

    public static final SubLObject glob_index_reset_alt(SubLObject v_glob) {
        {
            SubLObject index = glob_struc_index(v_glob);
            return dictionary.clear_dictionary(index);
        }
    }

    public static SubLObject glob_index_reset(final SubLObject v_glob) {
        final SubLObject index = glob_struc_index(v_glob);
        return dictionary.clear_dictionary(index);
    }

    public static final SubLObject glob_index_style_alt(SubLObject v_glob) {
        {
            SubLObject index = glob_struc_index(v_glob);
            if (NIL != dictionary.dictionary_p(index)) {
                return $DICTIONARY;
            } else {
                return $UNKNOWN;
            }
        }
    }

    public static SubLObject glob_index_style(final SubLObject v_glob) {
        final SubLObject index = glob_struc_index(v_glob);
        if (NIL != dictionary.dictionary_p(index)) {
            return $DICTIONARY;
        }
        return $UNKNOWN;
    }

    public static final SubLObject glob_index_size_alt(SubLObject v_glob) {
        {
            SubLObject index = glob_struc_index(v_glob);
            return dictionary.dictionary_length(index);
        }
    }

    public static SubLObject glob_index_size(final SubLObject v_glob) {
        final SubLObject index = glob_struc_index(v_glob);
        return dictionary.dictionary_length(index);
    }

    public static final SubLObject glob_index_enter_alt(SubLObject v_glob, SubLObject id, SubLObject v_object) {
        {
            SubLObject index = glob_struc_index(v_glob);
            return dictionary.dictionary_enter(index, id, v_object);
        }
    }

    public static SubLObject glob_index_enter(final SubLObject v_glob, final SubLObject id, final SubLObject v_object) {
        final SubLObject index = glob_struc_index(v_glob);
        return dictionary.dictionary_enter(index, id, v_object);
    }

    public static final SubLObject glob_index_lookup_alt(SubLObject v_glob, SubLObject id, SubLObject v_default) {
        {
            SubLObject index = glob_struc_index(v_glob);
            return dictionary.dictionary_lookup(index, id, v_default);
        }
    }

    public static SubLObject glob_index_lookup(final SubLObject v_glob, final SubLObject id, final SubLObject v_default) {
        final SubLObject index = glob_struc_index(v_glob);
        return dictionary.dictionary_lookup(index, id, v_default);
    }

    public static final SubLObject glob_index_remove_alt(SubLObject v_glob, SubLObject id) {
        {
            SubLObject index = glob_struc_index(v_glob);
            return dictionary.dictionary_remove(index, id);
        }
    }

    public static SubLObject glob_index_remove(final SubLObject v_glob, final SubLObject id) {
        final SubLObject index = glob_struc_index(v_glob);
        return dictionary.dictionary_remove(index, id);
    }

    public static final SubLObject new_glob_back_index_alt(SubLObject test, SubLObject initial_size) {
        return dictionary.new_dictionary(test, initial_size);
    }

    public static SubLObject new_glob_back_index(final SubLObject test, final SubLObject initial_size) {
        return dictionary.new_dictionary(test, initial_size);
    }

    public static final SubLObject glob_back_index_reset_alt(SubLObject v_glob) {
        {
            SubLObject back_index = glob_struc_back_index(v_glob);
            return dictionary.clear_dictionary(back_index);
        }
    }

    public static SubLObject glob_back_index_reset(final SubLObject v_glob) {
        final SubLObject back_index = glob_struc_back_index(v_glob);
        return dictionary.clear_dictionary(back_index);
    }

    public static final SubLObject glob_back_index_style_alt(SubLObject v_glob) {
        {
            SubLObject back_index = glob_struc_back_index(v_glob);
            if (NIL != dictionary.dictionary_p(back_index)) {
                return $DICTIONARY;
            } else {
                return $UNKNOWN;
            }
        }
    }

    public static SubLObject glob_back_index_style(final SubLObject v_glob) {
        final SubLObject back_index = glob_struc_back_index(v_glob);
        if (NIL != dictionary.dictionary_p(back_index)) {
            return $DICTIONARY;
        }
        return $UNKNOWN;
    }

    public static final SubLObject glob_back_index_enter_alt(SubLObject v_glob, SubLObject v_object, SubLObject id) {
        {
            SubLObject back_index = glob_struc_back_index(v_glob);
            return dictionary.dictionary_enter(back_index, v_object, id);
        }
    }

    public static SubLObject glob_back_index_enter(final SubLObject v_glob, final SubLObject v_object, final SubLObject id) {
        final SubLObject back_index = glob_struc_back_index(v_glob);
        return dictionary.dictionary_enter(back_index, v_object, id);
    }

    public static final SubLObject glob_back_index_lookup_alt(SubLObject v_glob, SubLObject v_object, SubLObject v_default) {
        {
            SubLObject back_index = glob_struc_back_index(v_glob);
            return dictionary.dictionary_lookup(back_index, v_object, v_default);
        }
    }

    public static SubLObject glob_back_index_lookup(final SubLObject v_glob, final SubLObject v_object, final SubLObject v_default) {
        final SubLObject back_index = glob_struc_back_index(v_glob);
        return dictionary.dictionary_lookup(back_index, v_object, v_default);
    }

    public static final SubLObject glob_back_index_remove_alt(SubLObject v_glob, SubLObject v_object) {
        {
            SubLObject back_index = glob_struc_back_index(v_glob);
            return dictionary.dictionary_remove(back_index, v_object);
        }
    }

    public static SubLObject glob_back_index_remove(final SubLObject v_glob, final SubLObject v_object) {
        final SubLObject back_index = glob_struc_back_index(v_glob);
        return dictionary.dictionary_remove(back_index, v_object);
    }

    /**
     * Return T iff OBJECT is a valid GLOB id.
     */
    @LispMethod(comment = "Return T iff OBJECT is a valid GLOB id.")
    public static final SubLObject glob_id_p_alt(SubLObject v_object) {
        return subl_promotions.non_negative_integer_p(v_object);
    }

    @LispMethod(comment = "Return T iff OBJECT is a valid GLOB id.")
    public static SubLObject glob_id_p(final SubLObject v_object) {
        return subl_promotions.non_negative_integer_p(v_object);
    }

    /**
     * Return T iff OBJECT is not a valid GLOB id.
     */
    @LispMethod(comment = "Return T iff OBJECT is not a valid GLOB id.")
    public static final SubLObject invalid_glob_id_p_alt(SubLObject v_object) {
        return makeBoolean(NIL == glob_id_p(v_object));
    }

    @LispMethod(comment = "Return T iff OBJECT is not a valid GLOB id.")
    public static SubLObject invalid_glob_id_p(final SubLObject v_object) {
        return makeBoolean(NIL == glob_id_p(v_object));
    }

    /**
     * Return a new GLOB that uses TEST as its equality test.
     * If non-nil, INITIAL-SIZE is used as an initial capacity estimate.
     * If MAX-SIZE is non-nil, the glob only stores the MAX-SIZE most recently-entered objects.
     */
    @LispMethod(comment = "Return a new GLOB that uses TEST as its equality test.\r\nIf non-nil, INITIAL-SIZE is used as an initial capacity estimate.\r\nIf MAX-SIZE is non-nil, the glob only stores the MAX-SIZE most recently-entered objects.\nReturn a new GLOB that uses TEST as its equality test.\nIf non-nil, INITIAL-SIZE is used as an initial capacity estimate.\nIf MAX-SIZE is non-nil, the glob only stores the MAX-SIZE most recently-entered objects.")
    public static final SubLObject new_glob_alt(SubLObject test, SubLObject initial_size, SubLObject max_size) {
        if (test == UNPROVIDED) {
            test = symbol_function(EQL);
        }
        if (initial_size == UNPROVIDED) {
            initial_size = NIL;
        }
        if (max_size == UNPROVIDED) {
            max_size = NIL;
        }
        SubLTrampolineFile.checkType(test, VALID_HASH_TEST_P);
        if (NIL != initial_size) {
            SubLTrampolineFile.checkType(initial_size, NON_NEGATIVE_INTEGER_P);
        }
        if (NIL != max_size) {
            SubLTrampolineFile.checkType(max_size, NON_NEGATIVE_INTEGER_P);
            max_size = NIL;
        }
        {
            SubLObject v_glob = make_glob(UNPROVIDED);
            _csetf_glob_struc_test(v_glob, hash_table_utilities.hash_test_to_symbol(test));
            _csetf_glob_struc_max_size(v_glob, max_size);
            initialize_glob_indices(v_glob, initial_size);
            return v_glob;
        }
    }

    @LispMethod(comment = "Return a new GLOB that uses TEST as its equality test.\r\nIf non-nil, INITIAL-SIZE is used as an initial capacity estimate.\r\nIf MAX-SIZE is non-nil, the glob only stores the MAX-SIZE most recently-entered objects.\nReturn a new GLOB that uses TEST as its equality test.\nIf non-nil, INITIAL-SIZE is used as an initial capacity estimate.\nIf MAX-SIZE is non-nil, the glob only stores the MAX-SIZE most recently-entered objects.")
    public static SubLObject new_glob(SubLObject test, SubLObject initial_size, SubLObject max_size) {
        if (test == UNPROVIDED) {
            test = symbol_function(EQL);
        }
        if (initial_size == UNPROVIDED) {
            initial_size = NIL;
        }
        if (max_size == UNPROVIDED) {
            max_size = NIL;
        }
        assert NIL != hash_table_utilities.valid_hash_test_p(test) : "! hash_table_utilities.valid_hash_test_p(test) " + ("hash_table_utilities.valid_hash_test_p(test) " + "CommonSymbols.NIL != hash_table_utilities.valid_hash_test_p(test) ") + test;
        if (((NIL != initial_size) && (!assertionsDisabledSynth)) && (NIL == subl_promotions.non_negative_integer_p(initial_size))) {
            throw new AssertionError(initial_size);
        }
        if (NIL != max_size) {
            assert NIL != subl_promotions.non_negative_integer_p(max_size) : "! subl_promotions.non_negative_integer_p(max_size) " + ("subl_promotions.non_negative_integer_p(max_size) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(max_size) ") + max_size;
            max_size = NIL;
        }
        final SubLObject v_glob = make_glob(UNPROVIDED);
        _csetf_glob_struc_test(v_glob, hash_table_utilities.hash_test_to_symbol(test));
        _csetf_glob_struc_max_size(v_glob, max_size);
        initialize_glob_indices(v_glob, initial_size);
        return v_glob;
    }

    /**
     * Reset GLOB to an empty state.
     */
    @LispMethod(comment = "Reset GLOB to an empty state.")
    public static final SubLObject glob_reset_alt(SubLObject v_glob) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        return reinitialize_glob_indices(v_glob);
    }

    @LispMethod(comment = "Reset GLOB to an empty state.")
    public static SubLObject glob_reset(final SubLObject v_glob) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        return reinitialize_glob_indices(v_glob);
    }

    public static final SubLObject initialize_glob_indices_alt(SubLObject v_glob, SubLObject initial_size) {
        if (NIL == initial_size) {
            initial_size = $default_initial_glob_size$.getGlobalValue();
        }
        {
            SubLObject test = glob_test(v_glob);
            _csetf_glob_struc_id_state(v_glob, new_glob_id_state());
            _csetf_glob_struc_lock(v_glob, make_lock($$$glob_lock));
            _csetf_glob_struc_index(v_glob, new_glob_index(test, initial_size));
            _csetf_glob_struc_back_index(v_glob, new_glob_back_index(test, initial_size));
        }
        return v_glob;
    }

    public static SubLObject initialize_glob_indices(final SubLObject v_glob, SubLObject initial_size) {
        if (NIL == initial_size) {
            initial_size = $default_initial_glob_size$.getGlobalValue();
        }
        final SubLObject test = glob_test(v_glob);
        _csetf_glob_struc_id_state(v_glob, new_glob_id_state());
        _csetf_glob_struc_lock(v_glob, make_lock($$$glob_lock));
        _csetf_glob_struc_index(v_glob, new_glob_index(test, initial_size));
        _csetf_glob_struc_back_index(v_glob, new_glob_back_index(test, initial_size));
        return v_glob;
    }

    public static final SubLObject reinitialize_glob_indices_alt(SubLObject v_glob) {
        {
            SubLObject lock = glob_struc_lock(v_glob);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                glob_id_state_reset(v_glob);
                glob_index_reset(v_glob);
                glob_back_index_reset(v_glob);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_glob;
    }

    public static SubLObject reinitialize_glob_indices(final SubLObject v_glob) {
        final SubLObject lock = glob_struc_lock(v_glob);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            glob_id_state_reset(v_glob);
            glob_index_reset(v_glob);
            glob_back_index_reset(v_glob);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_glob;
    }

    /**
     * Return the equality test used in GLOB
     */
    @LispMethod(comment = "Return the equality test used in GLOB")
    public static final SubLObject glob_test_alt(SubLObject v_glob) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        return glob_struc_test(v_glob);
    }

    @LispMethod(comment = "Return the equality test used in GLOB")
    public static SubLObject glob_test(final SubLObject v_glob) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        return glob_struc_test(v_glob);
    }

    /**
     * Return the maximum size limit on GLOB, or NIL if none.
     */
    @LispMethod(comment = "Return the maximum size limit on GLOB, or NIL if none.")
    public static final SubLObject glob_max_size_alt(SubLObject v_glob) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        return glob_struc_max_size(v_glob);
    }

    @LispMethod(comment = "Return the maximum size limit on GLOB, or NIL if none.")
    public static SubLObject glob_max_size(final SubLObject v_glob) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        return glob_struc_max_size(v_glob);
    }

    /**
     * Return the current number of objects in GLOB
     */
    @LispMethod(comment = "Return the current number of objects in GLOB")
    public static final SubLObject glob_size_alt(SubLObject v_glob) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        return glob_index_size(v_glob);
    }

    @LispMethod(comment = "Return the current number of objects in GLOB")
    public static SubLObject glob_size(final SubLObject v_glob) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        return glob_index_size(v_glob);
    }

    /**
     * Return the owner of GLOB
     */
    @LispMethod(comment = "Return the owner of GLOB")
    public static final SubLObject glob_owner_alt(SubLObject v_glob) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        return glob_struc_owner(v_glob);
    }

    @LispMethod(comment = "Return the owner of GLOB")
    public static SubLObject glob_owner(final SubLObject v_glob) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        return glob_struc_owner(v_glob);
    }

    /**
     * Return the object with ID in GLOB, or DEFAULT if none.
     * Second value returned is NIL if ID was not present.
     */
    @LispMethod(comment = "Return the object with ID in GLOB, or DEFAULT if none.\r\nSecond value returned is NIL if ID was not present.\nReturn the object with ID in GLOB, or DEFAULT if none.\nSecond value returned is NIL if ID was not present.")
    public static final SubLObject glob_lookup_alt(SubLObject v_glob, SubLObject id, SubLObject v_default) {
        if (v_default == UNPROVIDED) {
            v_default = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(v_glob, GLOB_P);
            SubLTrampolineFile.checkType(id, GLOB_ID_P);
            {
                SubLObject v_object = NIL;
                SubLObject validP = NIL;
                SubLObject lock = glob_struc_lock(v_glob);
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    thread.resetMultipleValues();
                    {
                        SubLObject v_object_3 = glob_index_lookup(v_glob, id, v_default);
                        SubLObject validP_4 = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        v_object = v_object_3;
                        validP = validP_4;
                    }
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
                return values(v_object, validP);
            }
        }
    }

    @LispMethod(comment = "Return the object with ID in GLOB, or DEFAULT if none.\r\nSecond value returned is NIL if ID was not present.\nReturn the object with ID in GLOB, or DEFAULT if none.\nSecond value returned is NIL if ID was not present.")
    public static SubLObject glob_lookup(final SubLObject v_glob, final SubLObject id, SubLObject v_default) {
        if (v_default == UNPROVIDED) {
            v_default = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        assert NIL != glob_id_p(id) : "! glob.glob_id_p(id) " + ("glob.glob_id_p(id) " + "CommonSymbols.NIL != glob.glob_id_p(id) ") + id;
        SubLObject v_object = NIL;
        SubLObject validP = NIL;
        final SubLObject lock = glob_struc_lock(v_glob);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            thread.resetMultipleValues();
            final SubLObject v_object_$1 = glob_index_lookup(v_glob, id, v_default);
            final SubLObject validP_$2 = thread.secondMultipleValue();
            thread.resetMultipleValues();
            v_object = v_object_$1;
            validP = validP_$2;
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return values(v_object, validP);
    }

    /**
     * Return the id of OBJECT in GLOB, or DEFAULT if none.
     * Second value returned is NIL if OBJECT was not present.
     */
    @LispMethod(comment = "Return the id of OBJECT in GLOB, or DEFAULT if none.\r\nSecond value returned is NIL if OBJECT was not present.\nReturn the id of OBJECT in GLOB, or DEFAULT if none.\nSecond value returned is NIL if OBJECT was not present.")
    public static final SubLObject glob_object_id_alt(SubLObject v_glob, SubLObject v_object, SubLObject v_default) {
        if (v_default == UNPROVIDED) {
            v_default = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(v_glob, GLOB_P);
            {
                SubLObject id = NIL;
                SubLObject validP = NIL;
                SubLObject lock = glob_struc_lock(v_glob);
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    thread.resetMultipleValues();
                    {
                        SubLObject id_5 = glob_back_index_lookup(v_glob, v_object, v_default);
                        SubLObject validP_6 = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        id = id_5;
                        validP = validP_6;
                    }
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
                return values(id, validP);
            }
        }
    }

    @LispMethod(comment = "Return the id of OBJECT in GLOB, or DEFAULT if none.\r\nSecond value returned is NIL if OBJECT was not present.\nReturn the id of OBJECT in GLOB, or DEFAULT if none.\nSecond value returned is NIL if OBJECT was not present.")
    public static SubLObject glob_object_id(final SubLObject v_glob, final SubLObject v_object, SubLObject v_default) {
        if (v_default == UNPROVIDED) {
            v_default = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        SubLObject id = NIL;
        SubLObject validP = NIL;
        final SubLObject lock = glob_struc_lock(v_glob);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            thread.resetMultipleValues();
            final SubLObject id_$3 = glob_back_index_lookup(v_glob, v_object, v_default);
            final SubLObject validP_$4 = thread.secondMultipleValue();
            thread.resetMultipleValues();
            id = id_$3;
            validP = validP_$4;
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return values(id, validP);
    }

    /**
     * Enter OBJECT in GLOB, and return its id.
     */
    @LispMethod(comment = "Enter OBJECT in GLOB, and return its id.")
    public static final SubLObject glob_enter_alt(SubLObject v_glob, SubLObject v_object) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        {
            SubLObject existing_id = glob_object_id(v_glob, v_object, NIL);
            if (NIL != glob_id_p(existing_id)) {
                return existing_id;
            }
        }
        {
            SubLObject new_id = glob_id_state_next(v_glob);
            glob_enter_internal(v_glob, new_id, v_object);
            return new_id;
        }
    }

    @LispMethod(comment = "Enter OBJECT in GLOB, and return its id.")
    public static SubLObject glob_enter(final SubLObject v_glob, final SubLObject v_object) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        final SubLObject existing_id = glob_object_id(v_glob, v_object, NIL);
        if (NIL != glob_id_p(existing_id)) {
            return existing_id;
        }
        final SubLObject new_id = glob_id_state_next(v_glob);
        glob_enter_internal(v_glob, new_id, v_object);
        return new_id;
    }

    /**
     * Remove OBJECT from GLOB.
     */
    @LispMethod(comment = "Remove OBJECT from GLOB.")
    public static final SubLObject glob_remove_alt(SubLObject v_glob, SubLObject v_object) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        {
            SubLObject id = glob_object_id(v_glob, v_object, NIL);
            return glob_remove_internal(v_glob, id, v_object);
        }
    }

    @LispMethod(comment = "Remove OBJECT from GLOB.")
    public static SubLObject glob_remove(final SubLObject v_glob, final SubLObject v_object) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        final SubLObject id = glob_object_id(v_glob, v_object, NIL);
        return glob_remove_internal(v_glob, id, v_object);
    }

    /**
     * Remove the object with ID from GLOB.
     */
    @LispMethod(comment = "Remove the object with ID from GLOB.")
    public static final SubLObject glob_remove_id_alt(SubLObject v_glob, SubLObject id) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        SubLTrampolineFile.checkType(id, GLOB_ID_P);
        {
            SubLObject v_object = glob_lookup(v_glob, id, NIL);
            return glob_remove_internal(v_glob, id, v_object);
        }
    }

    @LispMethod(comment = "Remove the object with ID from GLOB.")
    public static SubLObject glob_remove_id(final SubLObject v_glob, final SubLObject id) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        assert NIL != glob_id_p(id) : "! glob.glob_id_p(id) " + ("glob.glob_id_p(id) " + "CommonSymbols.NIL != glob.glob_id_p(id) ") + id;
        final SubLObject v_object = glob_lookup(v_glob, id, NIL);
        return glob_remove_internal(v_glob, id, v_object);
    }

    /**
     * Change the owner of GLOB to OWNER.
     */
    @LispMethod(comment = "Change the owner of GLOB to OWNER.")
    public static final SubLObject glob_note_owner_alt(SubLObject v_glob, SubLObject owner) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        {
            SubLObject lock = glob_struc_lock(v_glob);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                _csetf_glob_struc_owner(v_glob, owner);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_glob;
    }

    @LispMethod(comment = "Change the owner of GLOB to OWNER.")
    public static SubLObject glob_note_owner(final SubLObject v_glob, final SubLObject owner) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        final SubLObject lock = glob_struc_lock(v_glob);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            _csetf_glob_struc_owner(v_glob, owner);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_glob;
    }

    public static final SubLObject glob_enter_internal_alt(SubLObject v_glob, SubLObject id, SubLObject v_object) {
        {
            SubLObject lock = glob_struc_lock(v_glob);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                glob_index_enter(v_glob, id, v_object);
                glob_back_index_enter(v_glob, v_object, id);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_glob;
    }

    public static SubLObject glob_enter_internal(final SubLObject v_glob, final SubLObject id, final SubLObject v_object) {
        final SubLObject lock = glob_struc_lock(v_glob);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            glob_index_enter(v_glob, id, v_object);
            glob_back_index_enter(v_glob, v_object, id);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_glob;
    }

    public static final SubLObject glob_remove_internal_alt(SubLObject v_glob, SubLObject id, SubLObject v_object) {
        {
            SubLObject lock = glob_struc_lock(v_glob);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                glob_index_remove(v_glob, id);
                glob_back_index_remove(v_glob, v_object);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_glob;
    }

    public static SubLObject glob_remove_internal(final SubLObject v_glob, final SubLObject id, final SubLObject v_object) {
        final SubLObject lock = glob_struc_lock(v_glob);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            glob_index_remove(v_glob, id);
            glob_back_index_remove(v_glob, v_object);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_glob;
    }

    /**
     * Returns an iterator for GLOB.
     * Values returned are tuples of the form (<id> <object>).
     */
    @LispMethod(comment = "Returns an iterator for GLOB.\r\nValues returned are tuples of the form (<id> <object>).\nReturns an iterator for GLOB.\nValues returned are tuples of the form (<id> <object>).")
    public static final SubLObject new_glob_iterator_alt(SubLObject v_glob) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        return dictionary.new_dictionary_iterator(glob_struc_index(v_glob));
    }

    @LispMethod(comment = "Returns an iterator for GLOB.\r\nValues returned are tuples of the form (<id> <object>).\nReturns an iterator for GLOB.\nValues returned are tuples of the form (<id> <object>).")
    public static SubLObject new_glob_iterator(final SubLObject v_glob) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        return dictionary.new_dictionary_iterator(glob_struc_index(v_glob));
    }

    /**
     * Iterate over all ID -> OBJECT mappings indexed in GLOB evaluating BODY.
     * Iteration halts when DONE becomes non-nil.
     */
    @LispMethod(comment = "Iterate over all ID -> OBJECT mappings indexed in GLOB evaluating BODY.\r\nIteration halts when DONE becomes non-nil.\nIterate over all ID -> OBJECT mappings indexed in GLOB evaluating BODY.\nIteration halts when DONE becomes non-nil.")
    public static final SubLObject do_glob_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt46);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject id = NIL;
                    SubLObject v_object = NIL;
                    SubLObject v_glob = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt46);
                    id = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt46);
                    v_object = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt46);
                    v_glob = current.first();
                    current = current.rest();
                    {
                        SubLObject allow_other_keys_p = NIL;
                        SubLObject rest = current;
                        SubLObject bad = NIL;
                        SubLObject current_7 = NIL;
                        for (; NIL != rest;) {
                            destructuring_bind_must_consp(rest, datum, $list_alt46);
                            current_7 = rest.first();
                            rest = rest.rest();
                            destructuring_bind_must_consp(rest, datum, $list_alt46);
                            if (NIL == member(current_7, $list_alt47, UNPROVIDED, UNPROVIDED)) {
                                bad = T;
                            }
                            if (current_7 == $ALLOW_OTHER_KEYS) {
                                allow_other_keys_p = rest.first();
                            }
                            rest = rest.rest();
                        }
                        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
                            cdestructuring_bind_error(datum, $list_alt46);
                        }
                        {
                            SubLObject done_tail = property_list_member($DONE, current);
                            SubLObject done = (NIL != done_tail) ? ((SubLObject) (cadr(done_tail))) : NIL;
                            current = temp;
                            {
                                SubLObject body = current;
                                return listS(DO_DICTIONARY, listS(id, v_object, list(DO_GLOB_INDEX, v_glob), append(NIL != done ? ((SubLObject) (list(done))) : NIL, NIL)), append(body, NIL));
                            }
                        }
                    }
                }
            }
        }
    }

    @LispMethod(comment = "Iterate over all ID -> OBJECT mappings indexed in GLOB evaluating BODY.\r\nIteration halts when DONE becomes non-nil.\nIterate over all ID -> OBJECT mappings indexed in GLOB evaluating BODY.\nIteration halts when DONE becomes non-nil.")
    public static SubLObject do_glob(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list49);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject id = NIL;
        SubLObject v_object = NIL;
        SubLObject v_glob = NIL;
        destructuring_bind_must_consp(current, datum, $list49);
        id = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list49);
        v_object = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list49);
        v_glob = current.first();
        current = current.rest();
        SubLObject allow_other_keys_p = NIL;
        SubLObject rest = current;
        SubLObject bad = NIL;
        SubLObject current_$5 = NIL;
        while (NIL != rest) {
            destructuring_bind_must_consp(rest, datum, $list49);
            current_$5 = rest.first();
            rest = rest.rest();
            destructuring_bind_must_consp(rest, datum, $list49);
            if (NIL == member(current_$5, $list50, UNPROVIDED, UNPROVIDED)) {
                bad = T;
            }
            if (current_$5 == $ALLOW_OTHER_KEYS) {
                allow_other_keys_p = rest.first();
            }
            rest = rest.rest();
        } 
        if ((NIL != bad) && (NIL == allow_other_keys_p)) {
            cdestructuring_bind_error(datum, $list49);
        }
        final SubLObject done_tail = property_list_member($DONE, current);
        final SubLObject done = (NIL != done_tail) ? cadr(done_tail) : NIL;
        final SubLObject body;
        current = body = temp;
        return listS(DO_DICTIONARY, listS(id, v_object, list(DO_GLOB_INDEX, v_glob), append(NIL != done ? list(done) : NIL, NIL)), append(body, NIL));
    }

    public static final SubLObject do_glob_index_alt(SubLObject v_glob) {
        SubLTrampolineFile.checkType(v_glob, GLOB_P);
        return glob_struc_index(v_glob);
    }

    public static SubLObject do_glob_index(final SubLObject v_glob) {
        assert NIL != glob_p(v_glob) : "! glob.glob_p(v_glob) " + ("glob.glob_p(v_glob) " + "CommonSymbols.NIL != glob.glob_p(v_glob) ") + v_glob;
        return glob_struc_index(v_glob);
    }

    public static final SubLObject print_glob_contents_alt(SubLObject v_glob) {
        return dictionary_utilities.print_dictionary_contents(glob_struc_index(v_glob), UNPROVIDED);
    }

    public static SubLObject print_glob_contents(final SubLObject v_glob) {
        return dictionary_utilities.print_dictionary_contents(glob_struc_index(v_glob), UNPROVIDED);
    }

    public static SubLObject declare_glob_file() {
        declareFunction("glob_print_function_trampoline", "GLOB-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("glob_p", "GLOB-P", 1, 0, false);
        new glob.$glob_p$UnaryFunction();
        declareFunction("glob_struc_test", "GLOB-STRUC-TEST", 1, 0, false);
        declareFunction("glob_struc_max_size", "GLOB-STRUC-MAX-SIZE", 1, 0, false);
        declareFunction("glob_struc_id_state", "GLOB-STRUC-ID-STATE", 1, 0, false);
        declareFunction("glob_struc_lock", "GLOB-STRUC-LOCK", 1, 0, false);
        declareFunction("glob_struc_index", "GLOB-STRUC-INDEX", 1, 0, false);
        declareFunction("glob_struc_back_index", "GLOB-STRUC-BACK-INDEX", 1, 0, false);
        declareFunction("glob_struc_owner", "GLOB-STRUC-OWNER", 1, 0, false);
        declareFunction("_csetf_glob_struc_test", "_CSETF-GLOB-STRUC-TEST", 2, 0, false);
        declareFunction("_csetf_glob_struc_max_size", "_CSETF-GLOB-STRUC-MAX-SIZE", 2, 0, false);
        declareFunction("_csetf_glob_struc_id_state", "_CSETF-GLOB-STRUC-ID-STATE", 2, 0, false);
        declareFunction("_csetf_glob_struc_lock", "_CSETF-GLOB-STRUC-LOCK", 2, 0, false);
        declareFunction("_csetf_glob_struc_index", "_CSETF-GLOB-STRUC-INDEX", 2, 0, false);
        declareFunction("_csetf_glob_struc_back_index", "_CSETF-GLOB-STRUC-BACK-INDEX", 2, 0, false);
        declareFunction("_csetf_glob_struc_owner", "_CSETF-GLOB-STRUC-OWNER", 2, 0, false);
        declareFunction("make_glob", "MAKE-GLOB", 0, 1, false);
        declareFunction("visit_defstruct_glob", "VISIT-DEFSTRUCT-GLOB", 2, 0, false);
        declareFunction("visit_defstruct_object_glob_method", "VISIT-DEFSTRUCT-OBJECT-GLOB-METHOD", 2, 0, false);
        declareFunction("print_glob", "PRINT-GLOB", 3, 0, false);
        declareMacro("with_glob_lock", "WITH-GLOB-LOCK");
        declareFunction("new_glob_id_state", "NEW-GLOB-ID-STATE", 0, 0, false);
        declareFunction("glob_id_state_reset", "GLOB-ID-STATE-RESET", 1, 0, false);
        declareFunction("glob_id_state_next", "GLOB-ID-STATE-NEXT", 1, 0, false);
        declareFunction("new_glob_index", "NEW-GLOB-INDEX", 2, 0, false);
        declareFunction("glob_index_reset", "GLOB-INDEX-RESET", 1, 0, false);
        declareFunction("glob_index_style", "GLOB-INDEX-STYLE", 1, 0, false);
        declareFunction("glob_index_size", "GLOB-INDEX-SIZE", 1, 0, false);
        declareFunction("glob_index_enter", "GLOB-INDEX-ENTER", 3, 0, false);
        declareFunction("glob_index_lookup", "GLOB-INDEX-LOOKUP", 3, 0, false);
        declareFunction("glob_index_remove", "GLOB-INDEX-REMOVE", 2, 0, false);
        declareFunction("new_glob_back_index", "NEW-GLOB-BACK-INDEX", 2, 0, false);
        declareFunction("glob_back_index_reset", "GLOB-BACK-INDEX-RESET", 1, 0, false);
        declareFunction("glob_back_index_style", "GLOB-BACK-INDEX-STYLE", 1, 0, false);
        declareFunction("glob_back_index_enter", "GLOB-BACK-INDEX-ENTER", 3, 0, false);
        declareFunction("glob_back_index_lookup", "GLOB-BACK-INDEX-LOOKUP", 3, 0, false);
        declareFunction("glob_back_index_remove", "GLOB-BACK-INDEX-REMOVE", 2, 0, false);
        declareFunction("glob_id_p", "GLOB-ID-P", 1, 0, false);
        declareFunction("invalid_glob_id_p", "INVALID-GLOB-ID-P", 1, 0, false);
        declareFunction("new_glob", "NEW-GLOB", 0, 3, false);
        declareFunction("glob_reset", "GLOB-RESET", 1, 0, false);
        declareFunction("initialize_glob_indices", "INITIALIZE-GLOB-INDICES", 2, 0, false);
        declareFunction("reinitialize_glob_indices", "REINITIALIZE-GLOB-INDICES", 1, 0, false);
        declareFunction("glob_test", "GLOB-TEST", 1, 0, false);
        declareFunction("glob_max_size", "GLOB-MAX-SIZE", 1, 0, false);
        declareFunction("glob_size", "GLOB-SIZE", 1, 0, false);
        declareFunction("glob_owner", "GLOB-OWNER", 1, 0, false);
        declareFunction("glob_lookup", "GLOB-LOOKUP", 2, 1, false);
        declareFunction("glob_object_id", "GLOB-OBJECT-ID", 2, 1, false);
        declareFunction("glob_enter", "GLOB-ENTER", 2, 0, false);
        declareFunction("glob_remove", "GLOB-REMOVE", 2, 0, false);
        declareFunction("glob_remove_id", "GLOB-REMOVE-ID", 2, 0, false);
        declareFunction("glob_note_owner", "GLOB-NOTE-OWNER", 2, 0, false);
        declareFunction("glob_enter_internal", "GLOB-ENTER-INTERNAL", 3, 0, false);
        declareFunction("glob_remove_internal", "GLOB-REMOVE-INTERNAL", 3, 0, false);
        declareFunction("new_glob_iterator", "NEW-GLOB-ITERATOR", 1, 0, false);
        declareMacro("do_glob", "DO-GLOB");
        declareFunction("do_glob_index", "DO-GLOB-INDEX", 1, 0, false);
        declareFunction("print_glob_contents", "PRINT-GLOB-CONTENTS", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_glob_file() {
        defconstant("*DTP-GLOB*", GLOB);
        deflexical("*DEFAULT-INITIAL-GLOB-SIZE*", $int$50);
        return NIL;
    }

    public static SubLObject setup_glob_file() {
        register_method($print_object_method_table$.getGlobalValue(), $dtp_glob$.getGlobalValue(), symbol_function(GLOB_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim($list8);
        def_csetf(GLOB_STRUC_TEST, _CSETF_GLOB_STRUC_TEST);
        def_csetf(GLOB_STRUC_MAX_SIZE, _CSETF_GLOB_STRUC_MAX_SIZE);
        def_csetf(GLOB_STRUC_ID_STATE, _CSETF_GLOB_STRUC_ID_STATE);
        def_csetf(GLOB_STRUC_LOCK, _CSETF_GLOB_STRUC_LOCK);
        def_csetf(GLOB_STRUC_INDEX, _CSETF_GLOB_STRUC_INDEX);
        def_csetf(GLOB_STRUC_BACK_INDEX, _CSETF_GLOB_STRUC_BACK_INDEX);
        def_csetf(GLOB_STRUC_OWNER, _CSETF_GLOB_STRUC_OWNER);
        identity(GLOB);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), $dtp_glob$.getGlobalValue(), symbol_function(VISIT_DEFSTRUCT_OBJECT_GLOB_METHOD));
        register_macro_helper(DO_GLOB_INDEX, DO_GLOB);
        return NIL;
    }

    static private final SubLList $list_alt2 = list(makeSymbol("TEST"), makeSymbol("MAX-SIZE"), makeSymbol("ID-STATE"), makeSymbol("LOCK"), makeSymbol("INDEX"), makeSymbol("BACK-INDEX"), makeSymbol("OWNER"));

    @Override
    public void declareFunctions() {
        declare_glob_file();
    }

    static private final SubLList $list_alt3 = list($TEST, makeKeyword("MAX-SIZE"), makeKeyword("ID-STATE"), $LOCK, makeKeyword("INDEX"), makeKeyword("BACK-INDEX"), makeKeyword("OWNER"));

    @Override
    public void initializeVariables() {
        init_glob_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_glob_file();
    }

    static private final SubLList $list_alt4 = list(makeSymbol("GLOB-STRUC-TEST"), makeSymbol("GLOB-STRUC-MAX-SIZE"), makeSymbol("GLOB-STRUC-ID-STATE"), makeSymbol("GLOB-STRUC-LOCK"), makeSymbol("GLOB-STRUC-INDEX"), makeSymbol("GLOB-STRUC-BACK-INDEX"), makeSymbol("GLOB-STRUC-OWNER"));

    static {
    }

    public static final class $glob_native extends SubLStructNative {
        public SubLObject $test;

        public SubLObject $max_size;

        public SubLObject $id_state;

        public SubLObject $lock;

        public SubLObject $index;

        public SubLObject $back_index;

        public SubLObject $owner;

        private static final SubLStructDeclNative structDecl;

        public $glob_native() {
            glob.$glob_native.this.$test = Lisp.NIL;
            glob.$glob_native.this.$max_size = Lisp.NIL;
            glob.$glob_native.this.$id_state = Lisp.NIL;
            glob.$glob_native.this.$lock = Lisp.NIL;
            glob.$glob_native.this.$index = Lisp.NIL;
            glob.$glob_native.this.$back_index = Lisp.NIL;
            glob.$glob_native.this.$owner = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return glob.$glob_native.this.$test;
        }

        @Override
        public SubLObject getField3() {
            return glob.$glob_native.this.$max_size;
        }

        @Override
        public SubLObject getField4() {
            return glob.$glob_native.this.$id_state;
        }

        @Override
        public SubLObject getField5() {
            return glob.$glob_native.this.$lock;
        }

        @Override
        public SubLObject getField6() {
            return glob.$glob_native.this.$index;
        }

        @Override
        public SubLObject getField7() {
            return glob.$glob_native.this.$back_index;
        }

        @Override
        public SubLObject getField8() {
            return glob.$glob_native.this.$owner;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return glob.$glob_native.this.$test = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return glob.$glob_native.this.$max_size = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return glob.$glob_native.this.$id_state = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return glob.$glob_native.this.$lock = value;
        }

        @Override
        public SubLObject setField6(final SubLObject value) {
            return glob.$glob_native.this.$index = value;
        }

        @Override
        public SubLObject setField7(final SubLObject value) {
            return glob.$glob_native.this.$back_index = value;
        }

        @Override
        public SubLObject setField8(final SubLObject value) {
            return glob.$glob_native.this.$owner = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.glob.$glob_native.class, GLOB, GLOB_P, $list2, $list3, new String[]{ "$test", "$max_size", "$id_state", "$lock", "$index", "$back_index", "$owner" }, $list4, $list5, PRINT_GLOB);
        }
    }

    static private final SubLList $list_alt5 = list(makeSymbol("_CSETF-GLOB-STRUC-TEST"), makeSymbol("_CSETF-GLOB-STRUC-MAX-SIZE"), makeSymbol("_CSETF-GLOB-STRUC-ID-STATE"), makeSymbol("_CSETF-GLOB-STRUC-LOCK"), makeSymbol("_CSETF-GLOB-STRUC-INDEX"), makeSymbol("_CSETF-GLOB-STRUC-BACK-INDEX"), makeSymbol("_CSETF-GLOB-STRUC-OWNER"));

    static private final SubLString $str_alt29$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLString $str_alt30$__ = makeString("#<");

    static private final SubLString $str_alt32$_ = makeString("(");

    static private final SubLString $str_alt33$_ = makeString(")");

    static private final SubLString $str_alt34$_size_ = makeString(" size=");

    static private final SubLString $str_alt35$_owner_ = makeString(" owner=");

    static private final SubLList $list_alt37 = list(makeSymbol("GLOB"), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final class $glob_p$UnaryFunction extends UnaryFunction {
        public $glob_p$UnaryFunction() {
            super(extractFunctionNamed("GLOB-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return glob_p(arg1);
        }
    }

    static private final SubLList $list_alt46 = list(list(makeSymbol("ID"), makeSymbol("OBJECT"), makeSymbol("GLOB"), makeSymbol("&KEY"), makeSymbol("DONE")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLList $list_alt47 = list($DONE);
}

/**
 * Total time: 199 ms synthetic
 */
