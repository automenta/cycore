/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl;


import static com.cyc.cycjava.cycl.el_utilities.el_binary_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.el_unary_formula_p;
import static com.cyc.cycjava.cycl.el_utilities.formula_arityE;
import static com.cyc.cycjava.cycl.el_utilities.formula_with_sequence_varP;
import static com.cyc.cycjava.cycl.el_utilities.list_to_elf;
import static com.cyc.cycjava.cycl.el_utilities.make_binary_formula;
import static com.cyc.cycjava.cycl.el_utilities.make_conjunction;
import static com.cyc.cycjava.cycl.el_utilities.make_existential;
import static com.cyc.cycjava.cycl.el_utilities.make_ternary_formula;
import static com.cyc.cycjava.cycl.el_utilities.replace_formula_arg;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;

import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLList;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTranslatedFile;


public final class reformulator_module_vpp_non_state extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new reformulator_module_vpp_non_state();

 public static final String myName = "com.cyc.cycjava.cycl.reformulator_module_vpp_non_state";


    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $vppns_microseconds$ = makeSymbol("*VPPNS-MICROSECONDS*");



    static private final SubLList $list1 = list(new SubLObject[]{ makeKeyword("REQUIRED"), makeSymbol("VPPNS-REQUIRED"), $COST, makeSymbol("VPPNS-COST"), makeKeyword("REFORMULATE"), makeSymbol("VPPNS-REFORMULATE"), makeKeyword("DOCUMENTATION"), makeString("Transforms the output of the RTP and VPP into usable CycL."), makeKeyword("EXAMPLE-SOURCE"), makeString("(SubcollectionOfWithRelationFromFn Monkey playsActiveSubjectRole \n           (TheList \n               (SomeFn ProcessingALoanApplication) doneBy))"), makeKeyword("EXAMPLE-TARGET"), makeString("(SubcollectionOfWithRelationFromFn Monkey doneBy \n           (SomeFn ProcessingALoanApplication))") });









    private static final SubLInteger $int$6000 = makeInteger(6000);











    // Definitions
    public static final SubLObject binary_the_vp_parse_expressionP_alt(SubLObject v_object) {
        return makeBoolean(((NIL != el_binary_formula_p(v_object)) && $$TheVPParse.eql(cycl_utilities.formula_operator(v_object))) && (NIL == formula_with_sequence_varP(v_object)));
    }

    // Definitions
    public static SubLObject binary_the_vp_parse_expressionP(final SubLObject v_object) {
        return makeBoolean(((NIL != el_binary_formula_p(v_object)) && $$TheVPParse.eql(cycl_utilities.formula_operator(v_object))) && (NIL == formula_with_sequence_varP(v_object)));
    }

    public static final SubLObject binary_the_vp_parse_some_fn_expressionP_alt(SubLObject v_object) {
        return makeBoolean((((NIL != el_binary_formula_p(v_object)) && $$TheVPParse.eql(cycl_utilities.formula_operator(v_object))) && (NIL == formula_with_sequence_varP(v_object))) && $$SomeFn.eql(cycl_utilities.formula_operator(cycl_utilities.formula_arg1(v_object, UNPROVIDED))));
    }

    public static SubLObject binary_the_vp_parse_some_fn_expressionP(final SubLObject v_object) {
        return makeBoolean((((NIL != el_binary_formula_p(v_object)) && $$TheVPParse.eql(cycl_utilities.formula_operator(v_object))) && (NIL == formula_with_sequence_varP(v_object))) && $$SomeFn.eql(cycl_utilities.formula_operator(cycl_utilities.formula_arg1(v_object, UNPROVIDED))));
    }

    public static final SubLObject subject_role_predicateP_alt(SubLObject v_object, SubLObject mt) {
        return makeBoolean((NIL != forts.fort_p(v_object)) && (NIL != isa.isaP(v_object, $$SubjectRolePredicate, mt, UNPROVIDED)));
    }

    public static SubLObject subject_role_predicateP(final SubLObject v_object, final SubLObject mt) {
        return makeBoolean((NIL != forts.fort_p(v_object)) && (NIL != isa.isaP(v_object, $$SubjectRolePredicate, mt, UNPROVIDED)));
    }

    public static final SubLObject unary_some_fn_expressionP_alt(SubLObject v_object) {
        return makeBoolean(((NIL != el_unary_formula_p(v_object)) && $$SomeFn.eql(cycl_utilities.formula_operator(v_object))) && (NIL == formula_with_sequence_varP(v_object)));
    }

    public static SubLObject unary_some_fn_expressionP(final SubLObject v_object) {
        return makeBoolean(((NIL != el_unary_formula_p(v_object)) && $$SomeFn.eql(cycl_utilities.formula_operator(v_object))) && (NIL == formula_with_sequence_varP(v_object)));
    }

    public static final SubLObject vppns_required_alt(SubLObject expression, SubLObject mt, SubLObject settings) {
        if (NIL != clauses.clauses_p(expression)) {
            if ((NIL != list_utilities.singletonP(expression)) && (NIL != clause_utilities.pos_atomic_cnf_p(expression.first()))) {
                {
                    SubLObject asent = clause_utilities.gaf_cnf_literal(expression.first());
                    SubLObject pred = cycl_utilities.atomic_sentence_predicate(asent);
                    SubLObject args = cycl_utilities.formula_args(asent, $IGNORE);
                    SubLObject cdolist_list_var = args;
                    SubLObject arg = NIL;
                    for (arg = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , arg = cdolist_list_var.first()) {
                        if (((NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.binary_the_vp_parse_expressionP(arg)) || ((NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.unary_some_fn_expressionP(arg)) && (NIL == com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.subject_role_predicateP(pred, mt)))) || (NIL != reformulator_module_quantifier_unifier_3.subcollection_function_nautP(arg, mt))) {
                            return T;
                        }
                    }
                }
            }
        } else {
            if (NIL != el_formula_p(expression)) {
                return makeBoolean(($$SubcollectionOfWithRelationFromFn.eql(cycl_utilities.formula_operator(expression)) && (NIL != formula_arityE(expression, THREE_INTEGER, UNPROVIDED))) && (NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.binary_the_vp_parse_expressionP(cycl_utilities.formula_arg3(expression, UNPROVIDED))));
            }
        }
        return NIL;
    }

    public static SubLObject vppns_required(final SubLObject expression, final SubLObject mt, final SubLObject settings) {
        if (NIL != clauses.clauses_p(expression)) {
            if ((NIL != list_utilities.singletonP(expression)) && (NIL != clause_utilities.pos_atomic_cnf_p(expression.first()))) {
                final SubLObject asent = clause_utilities.gaf_cnf_literal(expression.first());
                final SubLObject pred = cycl_utilities.atomic_sentence_predicate(asent);
                SubLObject cdolist_list_var;
                final SubLObject args = cdolist_list_var = cycl_utilities.formula_args(asent, $IGNORE);
                SubLObject arg = NIL;
                arg = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if (((NIL != binary_the_vp_parse_expressionP(arg)) || ((NIL != unary_some_fn_expressionP(arg)) && (NIL == subject_role_predicateP(pred, mt)))) || (NIL != reformulator_module_quantifier_unifier_3.subcollection_function_nautP(arg, mt))) {
                        return T;
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    arg = cdolist_list_var.first();
                } 
            }
        } else
            if (NIL != el_formula_p(expression)) {
                return makeBoolean(($$SubcollectionOfWithRelationFromFn.eql(cycl_utilities.formula_operator(expression)) && (NIL != formula_arityE(expression, THREE_INTEGER, UNPROVIDED))) && (NIL != binary_the_vp_parse_expressionP(cycl_utilities.formula_arg3(expression, UNPROVIDED))));
            }

        return NIL;
    }

    public static final SubLObject vppns_cost_alt(SubLObject expression, SubLObject mt, SubLObject settings) {
        return $vppns_microseconds$.getGlobalValue();
    }

    public static SubLObject vppns_cost(final SubLObject expression, final SubLObject mt, final SubLObject settings) {
        return $vppns_microseconds$.getGlobalValue();
    }

    public static final SubLObject vppns_reformulate_alt(SubLObject expression, SubLObject original_clause, SubLObject mt, SubLObject settings) {
        {
            SubLObject result = NIL;
            if (NIL != clauses.clauses_p(expression)) {
                {
                    SubLObject asent = clause_utilities.gaf_cnf_literal(expression.first());
                    SubLObject argnum = ZERO_INTEGER;
                    SubLObject args = cycl_utilities.formula_args(asent, $IGNORE);
                    SubLObject rest = NIL;
                    for (rest = args; !((NIL != result) || (NIL == rest)); rest = rest.rest()) {
                        {
                            SubLObject arg = rest.first();
                            argnum = add(argnum, ONE_INTEGER);
                            if (NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.binary_the_vp_parse_some_fn_expressionP(arg)) {
                                result = com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.vppns_reformulate_vp_parse_some_fn_asent(asent, argnum, mt, settings, UNPROVIDED);
                            }
                            if ((NIL == result) && (NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.binary_the_vp_parse_expressionP(arg))) {
                                result = com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.vppns_reformulate_role_pred_asent(asent, argnum, mt, settings, UNPROVIDED);
                            }
                            if ((NIL == result) && (NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.unary_some_fn_expressionP(arg))) {
                                result = com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.vppns_reformulate_some_fn_asent(asent, argnum, mt, settings, original_clause);
                            }
                            if ((NIL == result) && (NIL != reformulator_module_quantifier_unifier_3.subcollection_function_nautP(arg, mt))) {
                                result = com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.vppns_reformulate_subcol_naut(asent, argnum, mt, settings, original_clause);
                            }
                        }
                    }
                    if (NIL != result) {
                        result = list(clause_utilities.make_gaf_cnf(result));
                    }
                }
            } else {
                result = com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.vppns_reformulate_naut(expression, mt, settings);
            }
            if (NIL != result) {
                return values(result, T);
            } else {
                return values(expression, NIL);
            }
        }
    }

    public static SubLObject vppns_reformulate(final SubLObject expression, final SubLObject original_clause, final SubLObject mt, final SubLObject settings) {
        SubLObject result = NIL;
        if (NIL != clauses.clauses_p(expression)) {
            final SubLObject asent = clause_utilities.gaf_cnf_literal(expression.first());
            SubLObject argnum = ZERO_INTEGER;
            final SubLObject args = cycl_utilities.formula_args(asent, $IGNORE);
            SubLObject rest;
            SubLObject arg;
            for (rest = NIL, rest = args; (NIL == result) && (NIL != rest); rest = rest.rest()) {
                arg = rest.first();
                argnum = add(argnum, ONE_INTEGER);
                if (NIL != binary_the_vp_parse_some_fn_expressionP(arg)) {
                    result = vppns_reformulate_vp_parse_some_fn_asent(asent, argnum, mt, settings, UNPROVIDED);
                }
                if ((NIL == result) && (NIL != binary_the_vp_parse_expressionP(arg))) {
                    result = vppns_reformulate_role_pred_asent(asent, argnum, mt, settings, UNPROVIDED);
                }
                if ((NIL == result) && (NIL != unary_some_fn_expressionP(arg))) {
                    result = vppns_reformulate_some_fn_asent(asent, argnum, mt, settings, original_clause);
                }
                if ((NIL == result) && (NIL != reformulator_module_quantifier_unifier_3.subcollection_function_nautP(arg, mt))) {
                    result = vppns_reformulate_subcol_naut(asent, argnum, mt, settings, original_clause);
                }
            }
            if (NIL != result) {
                result = list(clause_utilities.make_gaf_cnf(result));
            }
        } else {
            result = vppns_reformulate_naut(expression, mt, settings);
        }
        if (NIL != result) {
            return values(result, T);
        }
        return values(expression, NIL);
    }

    public static final SubLObject vppns_reformulate_role_pred_asent_alt(SubLObject asent, SubLObject role_list_argnum, SubLObject mt, SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        if (NIL != fort_types_interface.isa_predicateP(cycl_utilities.atomic_sentence_predicate(asent), mt)) {
            {
                SubLObject role_pred_argnum = NIL;
                SubLObject role_list_arg = cycl_utilities.sentence_arg(asent, role_list_argnum, UNPROVIDED);
                SubLObject thing = cycl_utilities.nat_arg1(role_list_arg, UNPROVIDED);
                SubLObject role = cycl_utilities.nat_arg2(role_list_arg, UNPROVIDED);
                SubLObject terms = cycl_utilities.formula_terms(asent, $IGNORE);
                SubLObject list_var = NIL;
                SubLObject arg = NIL;
                SubLObject argnum = NIL;
                for (list_var = terms, arg = list_var.first(), argnum = ZERO_INTEGER; !((NIL != role_pred_argnum) || (NIL == list_var)); list_var = list_var.rest() , arg = list_var.first() , argnum = number_utilities.f_1X(argnum)) {
                    if (NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.subject_role_predicateP(arg, mt)) {
                        role_pred_argnum = argnum;
                    }
                }
                if (NIL != role_pred_argnum) {
                    {
                        SubLObject new_asent = asent;
                        new_asent = replace_formula_arg(role_list_argnum, thing, new_asent);
                        new_asent = replace_formula_arg(role_pred_argnum, role, new_asent);
                        return new_asent;
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject vppns_reformulate_role_pred_asent(final SubLObject asent, final SubLObject role_list_argnum, final SubLObject mt, final SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        if (NIL != fort_types_interface.isa_predicateP(cycl_utilities.atomic_sentence_predicate(asent), mt)) {
            SubLObject role_pred_argnum = NIL;
            final SubLObject role_list_arg = cycl_utilities.sentence_arg(asent, role_list_argnum, UNPROVIDED);
            final SubLObject thing = cycl_utilities.nat_arg1(role_list_arg, UNPROVIDED);
            final SubLObject role = cycl_utilities.nat_arg2(role_list_arg, UNPROVIDED);
            final SubLObject terms = cycl_utilities.formula_terms(asent, $IGNORE);
            SubLObject list_var = NIL;
            SubLObject arg = NIL;
            SubLObject argnum = NIL;
            list_var = terms;
            arg = list_var.first();
            for (argnum = ZERO_INTEGER; (NIL == role_pred_argnum) && (NIL != list_var); list_var = list_var.rest() , arg = list_var.first() , argnum = number_utilities.f_1X(argnum)) {
                if (NIL != subject_role_predicateP(arg, mt)) {
                    role_pred_argnum = argnum;
                }
            }
            if (NIL != role_pred_argnum) {
                SubLObject new_asent = replace_formula_arg(role_list_argnum, thing, asent);
                new_asent = replace_formula_arg(role_pred_argnum, role, new_asent);
                return new_asent;
            }
        }
        return NIL;
    }

    public static final SubLObject vppns_reformulate_vp_parse_some_fn_asent_alt(SubLObject asent, SubLObject argnum, SubLObject mt, SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        if (NIL != fort_types_interface.isa_predicateP(cycl_utilities.atomic_sentence_predicate(asent), mt)) {
            {
                SubLObject terms = cycl_utilities.formula_terms(asent, $IGNORE);
                SubLObject list_var = NIL;
                SubLObject arg = NIL;
                SubLObject argnum_1 = NIL;
                for (list_var = terms, arg = list_var.first(), argnum_1 = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , arg = list_var.first() , argnum_1 = number_utilities.f_1X(argnum_1)) {
                    if (NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.subject_role_predicateP(arg, mt)) {
                        return NIL;
                    }
                }
            }
            {
                SubLObject vp_parse_arg = cycl_utilities.formula_arg(asent, argnum, UNPROVIDED);
                SubLObject some_fn_nat = cycl_utilities.formula_arg1(vp_parse_arg, UNPROVIDED);
                return replace_formula_arg(argnum, some_fn_nat, asent);
            }
        }
        return NIL;
    }

    public static SubLObject vppns_reformulate_vp_parse_some_fn_asent(final SubLObject asent, final SubLObject argnum, final SubLObject mt, final SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        if (NIL != fort_types_interface.isa_predicateP(cycl_utilities.atomic_sentence_predicate(asent), mt)) {
            final SubLObject terms = cycl_utilities.formula_terms(asent, $IGNORE);
            SubLObject list_var = NIL;
            SubLObject arg = NIL;
            SubLObject argnum_$1 = NIL;
            list_var = terms;
            arg = list_var.first();
            for (argnum_$1 = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , arg = list_var.first() , argnum_$1 = number_utilities.f_1X(argnum_$1)) {
                if (NIL != subject_role_predicateP(arg, mt)) {
                    return NIL;
                }
            }
            final SubLObject vp_parse_arg = cycl_utilities.formula_arg(asent, argnum, UNPROVIDED);
            final SubLObject some_fn_nat = cycl_utilities.formula_arg1(vp_parse_arg, UNPROVIDED);
            return replace_formula_arg(argnum, some_fn_nat, asent);
        }
        return NIL;
    }

    public static final SubLObject vppns_reformulate_some_fn_asent_alt(SubLObject asent, SubLObject some_fn_argnum, SubLObject mt, SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        {
            SubLObject pred = cycl_utilities.atomic_sentence_predicate(asent);
            SubLObject some_fn_expr = cycl_utilities.sentence_arg(asent, some_fn_argnum, UNPROVIDED);
            SubLObject v_term = cycl_utilities.nat_arg1(some_fn_expr, UNPROVIDED);
            SubLObject choice = NIL;
            if (NIL != at_admitted.admitted_argumentP(some_fn_expr, some_fn_argnum, pred, mt)) {
                choice = $$isa;
            } else {
                if (NIL != reformulator_module_quantifier_unifier_3.subcollection_function_nautP(v_term, mt)) {
                    choice = $$genls;
                } else {
                    return replace_formula_arg(some_fn_argnum, v_term, asent);
                }
            }
            {
                SubLObject new_var = czer_utilities.unique_el_var_wrt_expression(list(original_clause, asent), UNPROVIDED);
                return make_existential(new_var, make_conjunction(list(make_binary_formula(choice, new_var, v_term), replace_formula_arg(some_fn_argnum, new_var, asent))));
            }
        }
    }

    public static SubLObject vppns_reformulate_some_fn_asent(final SubLObject asent, final SubLObject some_fn_argnum, final SubLObject mt, final SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        final SubLObject pred = cycl_utilities.atomic_sentence_predicate(asent);
        final SubLObject some_fn_expr = cycl_utilities.sentence_arg(asent, some_fn_argnum, UNPROVIDED);
        final SubLObject v_term = cycl_utilities.nat_arg1(some_fn_expr, UNPROVIDED);
        SubLObject choice = NIL;
        if (NIL != at_admitted.admitted_argumentP(some_fn_expr, some_fn_argnum, pred, mt)) {
            choice = $$isa;
        } else {
            if (NIL == reformulator_module_quantifier_unifier_3.subcollection_function_nautP(v_term, mt)) {
                return replace_formula_arg(some_fn_argnum, v_term, asent);
            }
            choice = $$genls;
        }
        final SubLObject new_var = czer_utilities.unique_el_var_wrt_expression(list(original_clause, asent), UNPROVIDED);
        return make_existential(new_var, make_conjunction(list(make_binary_formula(choice, new_var, v_term), replace_formula_arg(some_fn_argnum, new_var, asent))));
    }

    public static final SubLObject vppns_reformulate_naut_alt(SubLObject naut, SubLObject mt, SubLObject settings) {
        {
            SubLObject col = cycl_utilities.nat_arg1(naut, UNPROVIDED);
            SubLObject role_pred = cycl_utilities.nat_arg2(naut, UNPROVIDED);
            SubLObject role_list = cycl_utilities.nat_arg3(naut, UNPROVIDED);
            SubLObject thing = cycl_utilities.nat_arg1(role_list, UNPROVIDED);
            SubLObject role = cycl_utilities.nat_arg2(role_list, UNPROVIDED);
            if (NIL != com.cyc.cycjava.cycl.reformulator_module_vpp_non_state.subject_role_predicateP(role_pred, mt)) {
                return make_ternary_formula($$SubcollectionOfWithRelationFromFn, col, role, thing);
            }
        }
        return NIL;
    }

    public static SubLObject vppns_reformulate_naut(final SubLObject naut, final SubLObject mt, final SubLObject settings) {
        final SubLObject col = cycl_utilities.nat_arg1(naut, UNPROVIDED);
        final SubLObject role_pred = cycl_utilities.nat_arg2(naut, UNPROVIDED);
        final SubLObject role_list = cycl_utilities.nat_arg3(naut, UNPROVIDED);
        final SubLObject thing = cycl_utilities.nat_arg1(role_list, UNPROVIDED);
        final SubLObject role = cycl_utilities.nat_arg2(role_list, UNPROVIDED);
        if (NIL != subject_role_predicateP(role_pred, mt)) {
            return make_ternary_formula($$SubcollectionOfWithRelationFromFn, col, role, thing);
        }
        return NIL;
    }

    public static final SubLObject vppns_reformulate_subcol_naut_alt(SubLObject asent, SubLObject argnum, SubLObject mt, SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        {
            SubLObject pred = cycl_utilities.atomic_sentence_predicate(asent);
            if ((($$isa != pred) && ($$genls != pred)) && (NIL == subl_promotions.memberP($$Thing, kb_accessors.argn_isa(pred, argnum, mt), UNPROVIDED, UNPROVIDED))) {
                if (NIL != subl_promotions.memberP($$genls, ke_tools.transitive_via_arg_inverses(pred, argnum, mt), UNPROVIDED, UNPROVIDED)) {
                    return NIL;
                }
                {
                    SubLObject col = cycl_utilities.sentence_arg(asent, argnum, UNPROVIDED);
                    SubLObject new_var = czer_utilities.unique_el_var_wrt_expression(list(original_clause, asent), UNPROVIDED);
                    SubLObject subsentence = replace_formula_arg(argnum, new_var, asent);
                    return list_to_elf(list($$thereExists, new_var, list($$and, list($$genls, new_var, col), subsentence)));
                }
            }
        }
        return NIL;
    }

    public static SubLObject vppns_reformulate_subcol_naut(final SubLObject asent, final SubLObject argnum, final SubLObject mt, final SubLObject settings, SubLObject original_clause) {
        if (original_clause == UNPROVIDED) {
            original_clause = NIL;
        }
        final SubLObject pred = cycl_utilities.atomic_sentence_predicate(asent);
        if (($$isa.eql(pred) || $$genls.eql(pred)) || (NIL != subl_promotions.memberP($$Thing, kb_accessors.argn_isa(pred, argnum, mt), UNPROVIDED, UNPROVIDED))) {
            return NIL;
        }
        if (NIL != subl_promotions.memberP($$genls, ke_tools.transitive_via_arg_inverses(pred, argnum, mt), UNPROVIDED, UNPROVIDED)) {
            return NIL;
        }
        final SubLObject col = cycl_utilities.sentence_arg(asent, argnum, UNPROVIDED);
        final SubLObject new_var = czer_utilities.unique_el_var_wrt_expression(list(original_clause, asent), UNPROVIDED);
        final SubLObject subsentence = replace_formula_arg(argnum, new_var, asent);
        return list_to_elf(list($$thereExists, new_var, list($$and, list($$genls, new_var, col), subsentence)));
    }

    public static SubLObject declare_reformulator_module_vpp_non_state_file() {
        declareFunction("binary_the_vp_parse_expressionP", "BINARY-THE-VP-PARSE-EXPRESSION?", 1, 0, false);
        declareFunction("binary_the_vp_parse_some_fn_expressionP", "BINARY-THE-VP-PARSE-SOME-FN-EXPRESSION?", 1, 0, false);
        declareFunction("subject_role_predicateP", "SUBJECT-ROLE-PREDICATE?", 2, 0, false);
        declareFunction("unary_some_fn_expressionP", "UNARY-SOME-FN-EXPRESSION?", 1, 0, false);
        declareFunction("vppns_required", "VPPNS-REQUIRED", 3, 0, false);
        declareFunction("vppns_cost", "VPPNS-COST", 3, 0, false);
        declareFunction("vppns_reformulate", "VPPNS-REFORMULATE", 4, 0, false);
        declareFunction("vppns_reformulate_role_pred_asent", "VPPNS-REFORMULATE-ROLE-PRED-ASENT", 4, 1, false);
        declareFunction("vppns_reformulate_vp_parse_some_fn_asent", "VPPNS-REFORMULATE-VP-PARSE-SOME-FN-ASENT", 4, 1, false);
        declareFunction("vppns_reformulate_some_fn_asent", "VPPNS-REFORMULATE-SOME-FN-ASENT", 4, 1, false);
        declareFunction("vppns_reformulate_naut", "VPPNS-REFORMULATE-NAUT", 3, 0, false);
        declareFunction("vppns_reformulate_subcol_naut", "VPPNS-REFORMULATE-SUBCOL-NAUT", 4, 1, false);
        return NIL;
    }

    static private final SubLList $list_alt1 = list(new SubLObject[]{ makeKeyword("REQUIRED"), makeSymbol("VPPNS-REQUIRED"), $COST, makeSymbol("VPPNS-COST"), makeKeyword("REFORMULATE"), makeSymbol("VPPNS-REFORMULATE"), makeKeyword("DOCUMENTATION"), makeString("Transforms the output of the RTP and VPP into usable CycL."), makeKeyword("EXAMPLE-SOURCE"), makeString("(SubcollectionOfWithRelationFromFn Monkey playsActiveSubjectRole \n           (TheList \n               (SomeFn ProcessingALoanApplication) doneBy))"), makeKeyword("EXAMPLE-TARGET"), makeString("(SubcollectionOfWithRelationFromFn Monkey doneBy \n           (SomeFn ProcessingALoanApplication))") });

    public static SubLObject init_reformulator_module_vpp_non_state_file() {
        deflexical("*VPPNS-MICROSECONDS*", $int$6000);
        return NIL;
    }

    public static SubLObject setup_reformulator_module_vpp_non_state_file() {
        reformulator_module_harness.declare_rl_module($$VPPProcessingNonStateRLModule, $list1);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_reformulator_module_vpp_non_state_file();
    }

    @Override
    public void initializeVariables() {
        init_reformulator_module_vpp_non_state_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_reformulator_module_vpp_non_state_file();
    }

    static {
    }
}

/**
 * Total time: 51 ms
 */
