/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.inference.harness;


import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_strategy.set_tactic_set_aside_wrt;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_strategy.set_tactic_thrown_away_wrt;
import static com.cyc.cycjava.cycl.inference.harness.inference_tactician.motivation_strategem_link_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_tactician.strategem_invalid_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_tactician.strategem_problem;
import static com.cyc.cycjava.cycl.inference.harness.inference_tactician.strategy_sort;
import static com.cyc.cycjava.cycl.inference.harness.inference_tactician.strategy_throws_away_all_transformationP;
import static com.cyc.cycjava.cycl.utilities_macros.note_funcall_helper_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.plusp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.reverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeUninternedSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.copy_list;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.dictionary;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.set_contents;
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


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      TRANSFORMATION-TACTICIAN-MOTIVATION
 * source file: /cyc/top/cycl/inference/harness/transformation-tactician-motivation.lisp
 * created:     2019/07/03 17:37:41
 */
public final class transformation_tactician_motivation extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new transformation_tactician_motivation();

 public static final String myName = "com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation";


    // defparameter
    /**
     * When deciding whether a problem is motivated via residual transformation, if
     * the rule used on the transformation link is a self looping rule and this fix
     * is enabled, prevents the motivation from flowing.
     */
    @LispMethod(comment = "When deciding whether a problem is motivated via residual transformation, if\r\nthe rule used on the transformation link is a self looping rule and this fix\r\nis enabled, prevents the motivation from flowing.\ndefparameter\nWhen deciding whether a problem is motivated via residual transformation, if\nthe rule used on the transformation link is a self looping rule and this fix\nis enabled, prevents the motivation from flowing.")
    private static final SubLSymbol $transformation_strategy_self_looping_rule_fix_enabledP$ = makeSymbol("*TRANSFORMATION-STRATEGY-SELF-LOOPING-RULE-FIX-ENABLED?*");

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list0 = list(list(makeSymbol("STRATEGY"), makeSymbol("STRATEGEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLSymbol $sym1$PROBLEM = makeUninternedSymbol("PROBLEM");

    static private final SubLSymbol $sym2$STRATEGEM_VAR = makeUninternedSymbol("STRATEGEM-VAR");

    private static final SubLSymbol STRATEGEM_PROBLEM = makeSymbol("STRATEGEM-PROBLEM");

    private static final SubLSymbol TRANSFORMATION_STRATEGY_DEACTIVATE_STRATEGEM = makeSymbol("TRANSFORMATION-STRATEGY-DEACTIVATE-STRATEGEM");

    private static final SubLSymbol TRANSFORMATION_STRATEGY_POSSIBLY_DEACTIVATE_PROBLEM = makeSymbol("TRANSFORMATION-STRATEGY-POSSIBLY-DEACTIVATE-PROBLEM");

    private static final SubLSymbol MOTIVATION_STRATEGEM_P = makeSymbol("MOTIVATION-STRATEGEM-P");

    private static final SubLSymbol TRANSFORMATION_STRATEGY_P = makeSymbol("TRANSFORMATION-STRATEGY-P");

    private static final SubLSymbol $SUBSTRATEGY_STRATEGEM_MOTIVATED = makeKeyword("SUBSTRATEGY-STRATEGEM-MOTIVATED");

    static private final SubLString $str10$_s_tried_to_propagate_T_to__s_but = makeString("~s tried to propagate T to ~s but it throws away all transformation");

    private static final SubLSymbol TRANSFORMATION_STRATEGY_NOTE_NEW_TACTIC = makeSymbol("TRANSFORMATION-STRATEGY-NOTE-NEW-TACTIC");

    static private final SubLSymbol $sym16$LOGICAL_TACTIC_BETTER_WRT_REMOVAL_ = makeSymbol("LOGICAL-TACTIC-BETTER-WRT-REMOVAL?");

    private static final SubLSymbol TRANSFORMATION_STRATEGY_NOTE_SPLIT_TACTICS_STRATEGICALLY_POSSIBLE = makeSymbol("TRANSFORMATION-STRATEGY-NOTE-SPLIT-TACTICS-STRATEGICALLY-POSSIBLE");

    private static final SubLSymbol EXECUTABLE_STRATEGEM_P = makeSymbol("EXECUTABLE-STRATEGEM-P");

    private static final SubLSymbol TRANSFORMATION_STRATEGEM_P = makeSymbol("TRANSFORMATION-STRATEGEM-P");

    private static final SubLSymbol $sym22$TRANSFORMATION_STRATEGY_PROBLEM_NOTHING_TO_DO_ = makeSymbol("TRANSFORMATION-STRATEGY-PROBLEM-NOTHING-TO-DO?");

    // Definitions
    public static final SubLObject transformation_strategy_with_strategically_active_strategem_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt0);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject strategy = NIL;
                    SubLObject strategem = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt0);
                    strategy = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt0);
                    strategem = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            SubLObject problem = $sym1$PROBLEM;
                            SubLObject strategem_var = $sym2$STRATEGEM_VAR;
                            return listS(CLET, list(list(strategem_var, strategem), list(problem, list(STRATEGEM_PROBLEM, strategem_var))), list(TRANSFORMATION_STRATEGY_DEACTIVATE_STRATEGEM, strategy, strategem_var), append(body, list(list(TRANSFORMATION_STRATEGY_POSSIBLY_DEACTIVATE_PROBLEM, strategy, problem))));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt0);
                    }
                }
            }
        }
        return NIL;
    }

    // Definitions
    public static SubLObject transformation_strategy_with_strategically_active_strategem(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, $list0);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject strategy = NIL;
        SubLObject strategem = NIL;
        destructuring_bind_must_consp(current, datum, $list0);
        strategy = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, $list0);
        strategem = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            final SubLObject problem = $sym1$PROBLEM;
            final SubLObject strategem_var = $sym2$STRATEGEM_VAR;
            return listS(CLET, list(list(strategem_var, strategem), list(problem, list(STRATEGEM_PROBLEM, strategem_var))), list(TRANSFORMATION_STRATEGY_DEACTIVATE_STRATEGEM, strategy, strategem_var), append(body, list(list(TRANSFORMATION_STRATEGY_POSSIBLY_DEACTIVATE_PROBLEM, strategy, problem))));
        }
        cdestructuring_bind_error(datum, $list0);
        return NIL;
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static final SubLObject transformation_strategy_possibly_propagate_motivation_to_link_head_alt(SubLObject strategy, SubLObject link_head) {
        SubLTrampolineFile.checkType(link_head, MOTIVATION_STRATEGEM_P);
        {
            SubLObject already_motivatedP = transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, link_head);
            if (NIL == already_motivatedP) {
                com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_propagate_motivation_to_link_head(strategy, link_head);
                return T;
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static SubLObject transformation_strategy_possibly_propagate_motivation_to_link_head(final SubLObject strategy, final SubLObject link_head) {
        assert NIL != inference_tactician.motivation_strategem_p(link_head) : "! inference_tactician.motivation_strategem_p(link_head) " + ("inference_tactician.motivation_strategem_p(link_head) " + "CommonSymbols.NIL != inference_tactician.motivation_strategem_p(link_head) ") + link_head;
        final SubLObject already_motivatedP = transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, link_head);
        if (NIL == already_motivatedP) {
            transformation_strategy_propagate_motivation_to_link_head(strategy, link_head);
            return T;
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_propagate_motivation_to_link_head_alt(SubLObject strategy, SubLObject link_head) {
        SubLTrampolineFile.checkType(strategy, TRANSFORMATION_STRATEGY_P);
        SubLTrampolineFile.checkType(link_head, MOTIVATION_STRATEGEM_P);
        transformation_tactician_datastructures.transformation_strategy_note_link_head_motivated(strategy, link_head);
        if (NIL != inference_worker_transformation.transformation_link_p(link_head)) {
        } else {
            if (NIL != motivation_strategem_link_p(link_head)) {
                {
                    SubLObject link = link_head;
                    SubLObject supporting_problem = inference_datastructures_problem_link.problem_link_sole_supporting_problem(link);
                    com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_propagate_motivation_to_problem(strategy, supporting_problem);
                }
            } else {
                {
                    SubLObject tactic = link_head;
                    if (NIL != inference_worker_join.join_tactic_p(tactic)) {
                        {
                            SubLObject join_link = inference_worker_join.join_tactic_link(tactic);
                            SubLObject first_problem = inference_worker_join.join_link_first_problem(join_link);
                            SubLObject second_problem = inference_worker_join.join_link_second_problem(join_link);
                            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_propagate_motivation_to_problem(strategy, first_problem);
                            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_propagate_motivation_to_problem(strategy, second_problem);
                        }
                    } else {
                        {
                            SubLObject lookahead_problem = inference_worker.logical_tactic_lookahead_problem(tactic);
                            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_propagate_motivation_to_problem(strategy, lookahead_problem);
                        }
                    }
                }
            }
        }
        inference_datastructures_strategy.controlling_strategy_callback(strategy, $SUBSTRATEGY_STRATEGEM_MOTIVATED, link_head, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        return NIL;
    }

    public static SubLObject transformation_strategy_propagate_motivation_to_link_head(final SubLObject strategy, final SubLObject link_head) {
        assert NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) : "! transformation_tactician_datastructures.transformation_strategy_p(strategy) " + ("transformation_tactician_datastructures.transformation_strategy_p(strategy) " + "CommonSymbols.NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) ") + strategy;
        assert NIL != inference_tactician.motivation_strategem_p(link_head) : "! inference_tactician.motivation_strategem_p(link_head) " + ("inference_tactician.motivation_strategem_p(link_head) " + "CommonSymbols.NIL != inference_tactician.motivation_strategem_p(link_head) ") + link_head;
        transformation_tactician_datastructures.transformation_strategy_note_link_head_motivated(strategy, link_head);
        if (NIL == inference_worker_transformation.transformation_link_p(link_head)) {
            if (NIL != inference_tactician.motivation_strategem_link_p(link_head)) {
                final SubLObject supporting_problem = inference_datastructures_problem_link.problem_link_sole_supporting_problem(link_head);
                transformation_strategy_possibly_propagate_motivation_to_problem(strategy, supporting_problem);
            } else
                if (NIL != inference_worker_join.join_tactic_p(link_head)) {
                    final SubLObject join_link = inference_worker_join.join_tactic_link(link_head);
                    final SubLObject first_problem = inference_worker_join.join_link_first_problem(join_link);
                    final SubLObject second_problem = inference_worker_join.join_link_second_problem(join_link);
                    transformation_strategy_possibly_propagate_motivation_to_problem(strategy, first_problem);
                    transformation_strategy_possibly_propagate_motivation_to_problem(strategy, second_problem);
                } else {
                    final SubLObject lookahead_problem = inference_worker.logical_tactic_lookahead_problem(link_head);
                    transformation_strategy_possibly_propagate_motivation_to_problem(strategy, lookahead_problem);
                }

        }
        inference_datastructures_strategy.controlling_strategy_callback(strategy, $SUBSTRATEGY_STRATEGEM_MOTIVATED, link_head, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        return NIL;
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static final SubLObject transformation_strategy_link_motivates_problemP_alt(SubLObject strategy, SubLObject link, SubLObject problem) {
        if (problem == UNPROVIDED) {
            problem = NIL;
        }
        if (NIL == inference_worker_split.split_link_p(link)) {
            return com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_link_motivates_lookahead_problemP(strategy, link);
        } else {
            {
                SubLObject motivatedP = NIL;
                SubLObject problem_var = problem;
                SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
                SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                SubLObject state = NIL;
                for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); !((NIL != motivatedP) || (NIL != set_contents.do_set_contents_doneP(basis_object, state))); state = set_contents.do_set_contents_update_state(state)) {
                    {
                        SubLObject dependent_link = set_contents.do_set_contents_next(basis_object, state);
                        if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
                            {
                                SubLObject link_var = dependent_link;
                                SubLObject rest = NIL;
                                for (rest = inference_datastructures_problem_link.problem_link_supporting_mapped_problems(link_var); !((NIL != motivatedP) || (NIL == rest)); rest = rest.rest()) {
                                    {
                                        SubLObject mapped_problem = rest.first();
                                        if (NIL != inference_macros.do_problem_link_open_matchP(T, link_var, mapped_problem)) {
                                            if (problem_var == inference_datastructures_problem_link.mapped_problem_problem(mapped_problem)) {
                                                {
                                                    SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(dependent_link);
                                                    SubLObject rest_1 = NIL;
                                                    for (rest_1 = inference_datastructures_problem.problem_tactics(supported_problem); !((NIL != motivatedP) || (NIL == rest_1)); rest_1 = rest_1.rest()) {
                                                        {
                                                            SubLObject tactic = rest_1.first();
                                                            if (NIL != inference_worker_split.split_tactic_p(tactic)) {
                                                                {
                                                                    SubLObject supporting_mapped_problem = inference_worker_split.find_split_tactic_supporting_mapped_problem(tactic);
                                                                    if (mapped_problem == supporting_mapped_problem) {
                                                                        if (NIL != transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, tactic)) {
                                                                            motivatedP = T;
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
                            }
                        }
                    }
                }
                return motivatedP;
            }
        }
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static SubLObject transformation_strategy_link_motivates_problemP(final SubLObject strategy, final SubLObject link, SubLObject problem) {
        if (problem == UNPROVIDED) {
            problem = NIL;
        }
        if (NIL == inference_worker_split.split_link_p(link)) {
            return transformation_strategy_link_motivates_lookahead_problemP(strategy, link);
        }
        SubLObject motivatedP = NIL;
        final SubLObject problem_var = problem;
        final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject dependent_link;
        SubLObject link_var;
        SubLObject rest;
        SubLObject mapped_problem;
        SubLObject supported_problem;
        SubLObject rest_$1;
        SubLObject tactic;
        SubLObject supporting_mapped_problem;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); (NIL == motivatedP) && (NIL == set_contents.do_set_contents_doneP(basis_object, state)); state = set_contents.do_set_contents_update_state(state)) {
            dependent_link = set_contents.do_set_contents_next(basis_object, state);
            if (NIL != set_contents.do_set_contents_element_validP(state, dependent_link)) {
                link_var = dependent_link;
                for (rest = NIL, rest = inference_datastructures_problem_link.problem_link_supporting_mapped_problems(link_var); (NIL == motivatedP) && (NIL != rest); rest = rest.rest()) {
                    mapped_problem = rest.first();
                    if ((NIL != inference_macros.do_problem_link_open_matchP(T, link_var, mapped_problem)) && problem_var.eql(inference_datastructures_problem_link.mapped_problem_problem(mapped_problem))) {
                        supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(dependent_link);
                        for (rest_$1 = NIL, rest_$1 = inference_datastructures_problem.problem_tactics(supported_problem); (NIL == motivatedP) && (NIL != rest_$1); rest_$1 = rest_$1.rest()) {
                            tactic = rest_$1.first();
                            if (NIL != inference_worker_split.split_tactic_p(tactic)) {
                                supporting_mapped_problem = inference_worker_split.find_split_tactic_supporting_mapped_problem(tactic);
                                if (mapped_problem.eql(supporting_mapped_problem) && (NIL != transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, tactic))) {
                                    motivatedP = T;
                                }
                            }
                        }
                    }
                }
            }
        }
        return motivatedP;
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static final SubLObject transformation_strategy_link_motivates_lookahead_problemP_alt(SubLObject strategy, SubLObject link) {
        if (NIL != motivation_strategem_link_p(link)) {
            return transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, link);
        } else {
            if (NIL != inference_worker_split.split_link_p(link)) {
                return NIL;
            } else {
                if (NIL != inference_worker.logical_link_p(link)) {
                    {
                        SubLObject tactic = inference_worker.logical_link_unique_tactic(link);
                        return transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, tactic);
                    }
                } else {
                    return NIL;
                }
            }
        }
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static SubLObject transformation_strategy_link_motivates_lookahead_problemP(final SubLObject strategy, final SubLObject link) {
        if (NIL != inference_tactician.motivation_strategem_link_p(link)) {
            return transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, link);
        }
        if (NIL != inference_worker_split.split_link_p(link)) {
            return NIL;
        }
        if (NIL != inference_worker.logical_link_p(link)) {
            final SubLObject tactic = inference_worker.logical_link_unique_tactic(link);
            return transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, tactic);
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static final SubLObject transformation_strategy_possibly_propagate_motivation_to_problem_alt(SubLObject strategy, SubLObject problem) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                if (NIL != strategy_throws_away_all_transformationP(strategy)) {
                    Errors.error($str_alt10$_s_tried_to_propagate_T_to__s_but, strategy, problem);
                }
            }
            {
                SubLObject already_motivatedP = transformation_tactician_datastructures.transformation_strategy_problem_motivatedP(strategy, problem);
                if (NIL == already_motivatedP) {
                    com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_propagate_motivation_to_problem(strategy, problem);
                    return T;
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @return booleanp
     */
    @LispMethod(comment = "@return booleanp")
    public static SubLObject transformation_strategy_possibly_propagate_motivation_to_problem(final SubLObject strategy, final SubLObject problem) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (NIL != inference_tactician.strategy_throws_away_all_transformationP(strategy))) {
            Errors.error($str10$_s_tried_to_propagate_T_to__s_but, strategy, problem);
        }
        final SubLObject already_motivatedP = transformation_tactician_datastructures.transformation_strategy_problem_motivatedP(strategy, problem);
        if (NIL == already_motivatedP) {
            transformation_strategy_propagate_motivation_to_problem(strategy, problem);
            return T;
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_propagate_motivation_to_problem_alt(SubLObject strategy, SubLObject problem) {
        transformation_tactician_datastructures.transformation_strategy_note_problem_motivated(strategy, problem);
        if (NIL != inference_datastructures_problem.problem_relevant_to_strategyP(problem, strategy)) {
            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_activate_problem(strategy, problem);
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_propagate_motivation_to_problem(final SubLObject strategy, final SubLObject problem) {
        transformation_tactician_datastructures.transformation_strategy_note_problem_motivated(strategy, problem);
        if (NIL != inference_datastructures_problem.problem_relevant_to_strategyP(problem, strategy)) {
            transformation_strategy_possibly_activate_problem(strategy, problem);
        }
        return NIL;
    }

    /**
     *
     *
     * @return booleanp; whether STRATEGY chose to activate PROBLEM.
     */
    @LispMethod(comment = "@return booleanp; whether STRATEGY chose to activate PROBLEM.")
    public static final SubLObject transformation_strategy_possibly_activate_problem_alt(SubLObject strategy, SubLObject problem) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_chooses_not_to_examine_problemP(strategy, problem)) {
                return NIL;
            }
            inference_worker.determine_strategic_status_wrt(problem, strategy);
            if (NIL != com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_chooses_not_to_activate_problemP(strategy, problem)) {
                return NIL;
            }
            if (NIL != inference_worker_restriction.problem_is_a_simplificationP(problem)) {
                if (NIL != inference_worker_restriction.$simplification_tactics_execute_early_and_pass_down_transformation_motivationP$.getDynamicValue(thread)) {
                    com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_propagate_motivation_to_problem(strategy, problem);
                }
            }
            if (NIL != com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_problem_is_the_rest_of_a_join_orderedP(problem, strategy)) {
                com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_propagate_proof_spec_to_restricted_non_focals(strategy, problem);
            }
            {
                SubLObject activateP = makeBoolean((NIL != transformation_tactician_datastructures.transformation_strategy_problem_motivatedP(strategy, problem)) && (NIL == com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_chooses_not_to_activate_problemP(strategy, problem)));
                if (NIL != activateP) {
                    if (NIL != com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_activate_problem(strategy, problem)) {
                        return T;
                    } else {
                        com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_make_problem_pending(strategy, problem);
                        return NIL;
                    }
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @return booleanp; whether STRATEGY chose to activate PROBLEM.
     */
    @LispMethod(comment = "@return booleanp; whether STRATEGY chose to activate PROBLEM.")
    public static SubLObject transformation_strategy_possibly_activate_problem(final SubLObject strategy, final SubLObject problem) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != transformation_strategy_chooses_not_to_examine_problemP(strategy, problem)) {
            return NIL;
        }
        inference_worker.determine_strategic_status_wrt(problem, strategy);
        if (NIL != transformation_strategy_chooses_not_to_activate_problemP(strategy, problem)) {
            return NIL;
        }
        if ((NIL != inference_worker_restriction.problem_is_a_simplificationP(problem)) && (NIL != inference_worker_restriction.$simplification_tactics_execute_early_and_pass_down_transformation_motivationP$.getDynamicValue(thread))) {
            transformation_strategy_possibly_propagate_motivation_to_problem(strategy, problem);
        }
        if (NIL != transformation_strategy_problem_is_the_rest_of_a_join_orderedP(problem, strategy)) {
            transformation_strategy_possibly_propagate_proof_spec_to_restricted_non_focals(strategy, problem);
        }
        final SubLObject activateP = makeBoolean((NIL != transformation_tactician_datastructures.transformation_strategy_problem_motivatedP(strategy, problem)) && (NIL == transformation_strategy_chooses_not_to_activate_problemP(strategy, problem)));
        if (NIL == activateP) {
            return NIL;
        }
        if (NIL != transformation_strategy_activate_problem(strategy, problem)) {
            return T;
        }
        transformation_strategy_make_problem_pending(strategy, problem);
        return NIL;
    }

    public static final SubLObject transformation_strategy_problem_is_the_rest_of_a_join_orderedP_alt(SubLObject problem, SubLObject strategy) {
        {
            SubLObject part_of_join_orderedP = NIL;
            SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); !((NIL != part_of_join_orderedP) || (NIL != set_contents.do_set_contents_doneP(basis_object, state))); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject restriction_link = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, restriction_link)) {
                        if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(restriction_link, $RESTRICTION)) {
                            {
                                SubLObject non_focal_problem = inference_datastructures_problem_link.problem_link_supported_problem(restriction_link);
                                SubLObject set_contents_var_2 = inference_datastructures_problem.problem_dependent_links(non_focal_problem);
                                SubLObject basis_object_3 = set_contents.do_set_contents_basis_object(set_contents_var_2);
                                SubLObject state_4 = NIL;
                                for (state_4 = set_contents.do_set_contents_initial_state(basis_object_3, set_contents_var_2); !((NIL != part_of_join_orderedP) || (NIL != set_contents.do_set_contents_doneP(basis_object_3, state_4))); state_4 = set_contents.do_set_contents_update_state(state_4)) {
                                    {
                                        SubLObject join_ordered_link = set_contents.do_set_contents_next(basis_object_3, state_4);
                                        if (NIL != set_contents.do_set_contents_element_validP(state_4, join_ordered_link)) {
                                            if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(join_ordered_link, $JOIN_ORDERED)) {
                                                if (NIL != inference_worker_join_ordered.join_ordered_link_restricted_non_focal_linkP(join_ordered_link, restriction_link)) {
                                                    if (non_focal_problem == inference_worker_join_ordered.join_ordered_link_non_focal_problem(join_ordered_link)) {
                                                        part_of_join_orderedP = T;
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
            return part_of_join_orderedP;
        }
    }

    public static SubLObject transformation_strategy_problem_is_the_rest_of_a_join_orderedP(final SubLObject problem, final SubLObject strategy) {
        SubLObject part_of_join_orderedP = NIL;
        final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject restriction_link;
        SubLObject non_focal_problem;
        SubLObject set_contents_var_$2;
        SubLObject basis_object_$3;
        SubLObject state_$4;
        SubLObject join_ordered_link;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); (NIL == part_of_join_orderedP) && (NIL == set_contents.do_set_contents_doneP(basis_object, state)); state = set_contents.do_set_contents_update_state(state)) {
            restriction_link = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, restriction_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(restriction_link, $RESTRICTION))) {
                non_focal_problem = inference_datastructures_problem_link.problem_link_supported_problem(restriction_link);
                set_contents_var_$2 = inference_datastructures_problem.problem_dependent_links(non_focal_problem);
                for (basis_object_$3 = set_contents.do_set_contents_basis_object(set_contents_var_$2), state_$4 = NIL, state_$4 = set_contents.do_set_contents_initial_state(basis_object_$3, set_contents_var_$2); (NIL == part_of_join_orderedP) && (NIL == set_contents.do_set_contents_doneP(basis_object_$3, state_$4)); state_$4 = set_contents.do_set_contents_update_state(state_$4)) {
                    join_ordered_link = set_contents.do_set_contents_next(basis_object_$3, state_$4);
                    if ((((NIL != set_contents.do_set_contents_element_validP(state_$4, join_ordered_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(join_ordered_link, $JOIN_ORDERED))) && (NIL != inference_worker_join_ordered.join_ordered_link_restricted_non_focal_linkP(join_ordered_link, restriction_link))) && non_focal_problem.eql(inference_worker_join_ordered.join_ordered_link_non_focal_problem(join_ordered_link))) {
                        part_of_join_orderedP = T;
                    }
                }
            }
        }
        return part_of_join_orderedP;
    }

    public static final SubLObject transformation_strategy_possibly_propagate_proof_spec_to_restricted_non_focals_alt(SubLObject strategy, SubLObject problem) {
        return NIL;
    }

    public static SubLObject transformation_strategy_possibly_propagate_proof_spec_to_restricted_non_focals(final SubLObject strategy, final SubLObject problem) {
        return NIL;
    }

    public static final SubLObject transformation_strategy_chooses_not_to_examine_problemP_alt(SubLObject strategy, SubLObject problem) {
        return makeBoolean((NIL != inference_tactician_strategic_uninterestingness.strategy_deems_problem_tactically_uninterestingP(strategy, problem)) || (NIL != inference_tactician_strategic_uninterestingness.problem_or_lookahead_problem_has_executed_a_complete_removal_tacticP(problem, strategy)));
    }

    public static SubLObject transformation_strategy_chooses_not_to_examine_problemP(final SubLObject strategy, final SubLObject problem) {
        return makeBoolean((NIL != inference_tactician_strategic_uninterestingness.strategy_deems_problem_tactically_uninterestingP(strategy, problem)) || (NIL != inference_tactician_strategic_uninterestingness.problem_has_executed_a_complete_removal_tacticP(problem, strategy)));
    }

    public static final SubLObject transformation_strategy_chooses_not_to_activate_problemP_alt(SubLObject strategy, SubLObject problem) {
        return makeBoolean((NIL != transformation_tactician_datastructures.transformation_strategy_problem_activeP(strategy, problem)) || (NIL != transformation_tactician_datastructures.transformation_strategy_problem_pendingP(strategy, problem)));
    }

    public static SubLObject transformation_strategy_chooses_not_to_activate_problemP(final SubLObject strategy, final SubLObject problem) {
        return makeBoolean((NIL != transformation_tactician_datastructures.transformation_strategy_problem_activeP(strategy, problem)) || (NIL != transformation_tactician_datastructures.transformation_strategy_problem_pendingP(strategy, problem)));
    }

    /**
     * add all transformation strategems to the T-box or set-asides.
     *
     * @return booleanp; T unless STRATEGY chooses to throw away PROBLEM.
     */
    @LispMethod(comment = "add all transformation strategems to the T-box or set-asides.\r\n\r\n@return booleanp; T unless STRATEGY chooses to throw away PROBLEM.")
    public static final SubLObject transformation_strategy_activate_problem_alt(SubLObject strategy, SubLObject problem) {
        SubLTrampolineFile.checkType(strategy, TRANSFORMATION_STRATEGY_P);
        SubLTrampolineFile.checkType(problem, PROBLEM_P);
        return plusp(com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_activate_strategems(strategy, problem));
    }

    @LispMethod(comment = "add all transformation strategems to the T-box or set-asides.\r\n\r\n@return booleanp; T unless STRATEGY chooses to throw away PROBLEM.")
    public static SubLObject transformation_strategy_activate_problem(final SubLObject strategy, final SubLObject problem) {
        assert NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) : "! transformation_tactician_datastructures.transformation_strategy_p(strategy) " + ("transformation_tactician_datastructures.transformation_strategy_p(strategy) " + "CommonSymbols.NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) ") + strategy;
        assert NIL != inference_datastructures_problem.problem_p(problem) : "! inference_datastructures_problem.problem_p(problem) " + ("inference_datastructures_problem.problem_p(problem) " + "CommonSymbols.NIL != inference_datastructures_problem.problem_p(problem) ") + problem;
        return plusp(transformation_strategy_possibly_activate_strategems(strategy, problem));
    }

    public static final SubLObject transformation_strategy_possibly_activate_strategems_alt(SubLObject strategy, SubLObject problem) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject strategems_to_activate = com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_categorize_strategems(strategy, problem);
                SubLObject strategems_to_set_aside = thread.secondMultipleValue();
                SubLObject strategems_to_throw_away = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                {
                    SubLObject cdolist_list_var = strategems_to_activate;
                    SubLObject strategem = NIL;
                    for (strategem = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , strategem = cdolist_list_var.first()) {
                        transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, strategem);
                    }
                }
                {
                    SubLObject cdolist_list_var = strategems_to_set_aside;
                    SubLObject strategem = NIL;
                    for (strategem = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , strategem = cdolist_list_var.first()) {
                        transformation_tactician_datastructures.transformation_strategy_note_strategem_set_aside(strategy, strategem);
                        if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
                            set_tactic_set_aside_wrt(strategem, strategy, $TRANSFORMATION);
                        }
                    }
                }
                {
                    SubLObject cdolist_list_var = strategems_to_throw_away;
                    SubLObject strategem = NIL;
                    for (strategem = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , strategem = cdolist_list_var.first()) {
                        transformation_tactician_datastructures.transformation_strategy_note_strategem_thrown_away(strategy, strategem);
                        if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
                            set_tactic_thrown_away_wrt(strategem, strategy, $TRANSFORMATION);
                        }
                    }
                }
                return length(strategems_to_activate);
            }
        }
    }

    public static SubLObject transformation_strategy_possibly_activate_strategems(final SubLObject strategy, final SubLObject problem) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject strategems_to_activate = transformation_strategy_categorize_strategems(strategy, problem);
        final SubLObject strategems_to_set_aside = thread.secondMultipleValue();
        final SubLObject strategems_to_throw_away = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        SubLObject cdolist_list_var = strategems_to_activate;
        SubLObject strategem = NIL;
        strategem = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, strategem);
            cdolist_list_var = cdolist_list_var.rest();
            strategem = cdolist_list_var.first();
        } 
        cdolist_list_var = strategems_to_set_aside;
        strategem = NIL;
        strategem = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            transformation_tactician_datastructures.transformation_strategy_note_strategem_set_aside(strategy, strategem);
            if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
                inference_datastructures_strategy.set_tactic_set_aside(strategem, strategy, UNPROVIDED);
            }
            cdolist_list_var = cdolist_list_var.rest();
            strategem = cdolist_list_var.first();
        } 
        cdolist_list_var = strategems_to_throw_away;
        strategem = NIL;
        strategem = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            transformation_tactician_datastructures.transformation_strategy_note_strategem_thrown_away(strategy, strategem);
            if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
                inference_datastructures_strategy.set_tactic_thrown_away(strategem, strategy, UNPROVIDED);
            }
            cdolist_list_var = cdolist_list_var.rest();
            strategem = cdolist_list_var.first();
        } 
        return length(strategems_to_activate);
    }

    public static final SubLObject transformation_strategy_activate_transformation_argument_links_alt(SubLObject strategy, SubLObject problem) {
        {
            SubLObject count = ZERO_INTEGER;
            SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject transformation_link = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) {
                        if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION)) {
                            {
                                SubLObject supporting_transformed_problem = inference_worker_transformation.transformation_link_supporting_problem(transformation_link);
                                if ((NIL != supporting_transformed_problem) && (NIL == inference_datastructures_problem.problem_relevant_to_inferenceP(supporting_transformed_problem, inference_datastructures_strategy.strategy_inference(strategy)))) {
                                    inference_datastructures_inference.add_inference_relevant_problem(inference_datastructures_strategy.strategy_inference(strategy), supporting_transformed_problem);
                                    inference_worker.possibly_activate_problem(strategy, supporting_transformed_problem);
                                }
                            }
                            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_activate_transformation_link(strategy, transformation_link);
                            count = add(count, ONE_INTEGER);
                        }
                    }
                }
            }
            return count;
        }
    }

    public static SubLObject transformation_strategy_activate_transformation_argument_links(final SubLObject strategy, final SubLObject problem) {
        SubLObject count = ZERO_INTEGER;
        final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject transformation_link;
        SubLObject supporting_transformed_problem;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            transformation_link = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION))) {
                supporting_transformed_problem = inference_worker_transformation.transformation_link_supporting_problem(transformation_link);
                if ((NIL != supporting_transformed_problem) && (NIL == inference_datastructures_problem.problem_relevant_to_inferenceP(supporting_transformed_problem, inference_datastructures_strategy.strategy_inference(strategy)))) {
                    inference_datastructures_inference.add_inference_relevant_problem(inference_datastructures_strategy.strategy_inference(strategy), supporting_transformed_problem);
                    inference_worker.possibly_activate_problem(strategy, supporting_transformed_problem);
                }
                transformation_strategy_possibly_activate_transformation_link(strategy, transformation_link);
                count = add(count, ONE_INTEGER);
            }
        }
        return count;
    }

    public static final SubLObject transformation_strategy_note_argument_link_added_alt(SubLObject strategy, SubLObject link) {
        if (NIL != inference_worker_transformation.transformation_link_p(link)) {
            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_activate_transformation_link(strategy, link);
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_note_argument_link_added(final SubLObject strategy, final SubLObject link) {
        if (NIL != inference_worker_transformation.transformation_link_p(link)) {
            transformation_strategy_possibly_activate_transformation_link(strategy, link);
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_possibly_activate_transformation_link_alt(SubLObject strategy, SubLObject transformation_link) {
        if (NIL != inference_datastructures_problem_link.problem_link_with_supporting_problem_p(transformation_link)) {
            if (NIL == transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, transformation_link)) {
                com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_activate_transformation_link(strategy, transformation_link);
            }
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_possibly_activate_transformation_link(final SubLObject strategy, final SubLObject transformation_link) {
        if ((NIL != inference_datastructures_problem_link.problem_link_with_supporting_problem_p(transformation_link)) && (NIL == transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, transformation_link))) {
            transformation_strategy_activate_transformation_link(strategy, transformation_link);
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_activate_transformation_link_alt(SubLObject strategy, SubLObject transformation_link) {
        transformation_tactician_datastructures.transformation_strategy_note_problem_unpending(strategy, inference_datastructures_problem_link.problem_link_supported_problem(transformation_link));
        {
            SubLObject transformation_tactic = inference_worker_transformation.transformation_link_tactic(transformation_link);
            if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, transformation_tactic, UNPROVIDED, UNPROVIDED)) {
                if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, transformation_tactic, NIL, NIL, T)) {
                    transformation_tactician_datastructures.transformation_strategy_note_strategem_set_aside(strategy, transformation_link);
                } else {
                    transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, transformation_link);
                }
            }
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_activate_transformation_link(final SubLObject strategy, final SubLObject transformation_link) {
        transformation_tactician_datastructures.transformation_strategy_note_problem_unpending(strategy, inference_datastructures_problem_link.problem_link_supported_problem(transformation_link));
        final SubLObject transformation_tactic = inference_worker_transformation.transformation_link_tactic(transformation_link);
        if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, transformation_tactic, UNPROVIDED, UNPROVIDED)) {
            if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, transformation_tactic, NIL, NIL, T)) {
                transformation_tactician_datastructures.transformation_strategy_note_strategem_set_aside(strategy, transformation_link);
            } else {
                transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, transformation_link);
            }
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_note_new_tactic_alt(SubLObject strategy, SubLObject tactic) {
        inference_worker.default_compute_strategic_properties_of_tactic(strategy, tactic);
        if (!(((NIL != inference_worker_split.split_tactic_p(tactic)) && (NIL != inference_worker_split.meta_split_tactics_enabledP())) || (NIL != inference_tactician_strategic_uninterestingness.simple_strategy_chooses_to_ignore_tacticP(strategy, tactic)))) {
            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_note_new_tactic_possible(strategy, tactic);
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_note_new_tactic(final SubLObject strategy, final SubLObject tactic) {
        inference_worker.default_compute_strategic_properties_of_tactic(strategy, tactic);
        if ((NIL == inference_worker_split.split_tactic_p(tactic)) || (NIL == inference_worker_split.meta_split_tactics_enabledP())) {
            if (NIL == inference_tactician_strategic_uninterestingness.simple_strategy_chooses_to_throw_away_tacticP(strategy, tactic)) {
                if (((NIL != inference_tactician.strategy_set_aside_non_continuable_implies_throw_awayP(strategy)) || (NIL != inference_datastructures_inference.inference_continuableP(inference_datastructures_strategy.strategy_inference(strategy)))) && (NIL != inference_tactician_strategic_uninterestingness.simple_strategy_chooses_to_set_aside_tacticP(strategy, tactic))) {
                    if (NIL != inference_tactician.transformation_strategem_p(tactic)) {
                        transformation_tactician_datastructures.transformation_strategy_note_strategem_set_aside(strategy, tactic);
                    }
                } else {
                    transformation_strategy_note_new_tactic_possible(strategy, tactic);
                }
            }
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_note_split_tactics_strategically_possible_alt(SubLObject strategy, SubLObject split_tactics) {
        {
            SubLObject sorted_split_tactics = strategy_sort(strategy, copy_list(split_tactics), $sym16$LOGICAL_TACTIC_BETTER_WRT_REMOVAL_);
            SubLObject cdolist_list_var = reverse(sorted_split_tactics);
            SubLObject split_tactic = NIL;
            for (split_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , split_tactic = cdolist_list_var.first()) {
                com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_note_new_tactic_possible(strategy, split_tactic);
            }
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_note_split_tactics_strategically_possible(final SubLObject strategy, final SubLObject split_tactics) {
        final SubLObject problem = inference_datastructures_tactic.tactic_problem(split_tactics.first());
        if (NIL != inference_datastructures_strategy.problem_motivatedP(problem, strategy)) {
            final SubLObject sorted_split_tactics = inference_tactician.strategy_sort(strategy, copy_list(split_tactics), $sym16$LOGICAL_TACTIC_BETTER_WRT_REMOVAL_);
            SubLObject cdolist_list_var = reverse(sorted_split_tactics);
            SubLObject split_tactic = NIL;
            split_tactic = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                transformation_strategy_note_new_tactic_possible(strategy, split_tactic);
                cdolist_list_var = cdolist_list_var.rest();
                split_tactic = cdolist_list_var.first();
            } 
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_note_new_tactic_possible_alt(SubLObject strategy, SubLObject tactic) {
        {
            SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
            inference_datastructures_strategy.problem_note_tactic_strategically_possible(problem, tactic, strategy);
        }
        if (((NIL != inference_worker_split.meta_split_tactics_enabledP()) && (NIL != inference_worker_split.split_tactic_p(tactic))) || ((NIL != inference_worker_transformation.transformation_tactic_p(tactic)) && (NIL == inference_worker_transformation.meta_transformation_tactic_p(tactic)))) {
            {
                SubLObject problem_already_consideredP = T;
                transformation_tactician_datastructures.transformation_strategy_note_problem_unpending(strategy, inference_datastructures_tactic.tactic_problem(tactic));
                if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, tactic, problem_already_consideredP, NIL)) {
                    transformation_tactician_datastructures.transformation_strategy_note_strategem_thrown_away(strategy, tactic);
                } else {
                    if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, tactic, problem_already_consideredP, NIL, T)) {
                        transformation_tactician_datastructures.transformation_strategy_note_strategem_set_aside(strategy, tactic);
                    } else {
                        transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, tactic);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_note_new_tactic_possible(final SubLObject strategy, final SubLObject tactic) {
        final SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
        inference_datastructures_strategy.problem_note_tactic_strategically_possible(problem, tactic, strategy);
        if (((NIL != inference_worker_split.meta_split_tactics_enabledP()) && (NIL != inference_worker_split.split_tactic_p(tactic))) || ((NIL != inference_worker_transformation.transformation_tactic_p(tactic)) && (NIL == inference_worker_transformation.meta_transformation_tactic_p(tactic)))) {
            final SubLObject problem_already_consideredP = T;
            transformation_tactician_datastructures.transformation_strategy_note_problem_unpending(strategy, inference_datastructures_tactic.tactic_problem(tactic));
            if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, tactic, problem_already_consideredP, NIL)) {
                transformation_tactician_datastructures.transformation_strategy_note_strategem_thrown_away(strategy, tactic);
            } else
                if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, tactic, problem_already_consideredP, NIL, T)) {
                    transformation_tactician_datastructures.transformation_strategy_note_strategem_set_aside(strategy, tactic);
                } else {
                    transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, tactic);
                }

        }
        return NIL;
    }

    /**
     *
     *
     * @return 0 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to activate wrt transformation.
    Strategems are ordered in intended order of activation.
     * @return 1 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to set aside wrt transformation.
     * @return 2 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to throw away wrt transformation.
     */
    @LispMethod(comment = "@return 0 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to activate wrt transformation.\r\nStrategems are ordered in intended order of activation.\r\n@return 1 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to set aside wrt transformation.\r\n@return 2 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to throw away wrt transformation.")
    public static final SubLObject transformation_strategy_categorize_strategems_alt(SubLObject strategy, SubLObject problem) {
        {
            SubLObject strategems_to_activate = NIL;
            SubLObject strategems_to_set_aside = NIL;
            if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_problemP(strategy, problem, UNPROVIDED)) {
                {
                    SubLObject problem_set_asideP = transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_problemP(strategy, problem, UNPROVIDED, UNPROVIDED);
                    {
                        SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
                        SubLObject transformation_tactic = NIL;
                        for (transformation_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , transformation_tactic = cdolist_list_var.first()) {
                            if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(transformation_tactic, $TRANSFORMATION)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(transformation_tactic, $POSSIBLE))) {
                                if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, transformation_tactic, T, UNPROVIDED)) {
                                    if ((NIL != problem_set_asideP) || (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, transformation_tactic, T, UNPROVIDED, UNPROVIDED))) {
                                        strategems_to_set_aside = cons(transformation_tactic, strategems_to_set_aside);
                                    } else {
                                        strategems_to_activate = cons(transformation_tactic, strategems_to_activate);
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
                        SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                        SubLObject state = NIL;
                        for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                            {
                                SubLObject transformation_link = set_contents.do_set_contents_next(basis_object, state);
                                if (NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) {
                                    if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION)) {
                                        if (NIL != inference_datastructures_problem_link.problem_link_with_supporting_problem_p(transformation_link)) {
                                            if (NIL == transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, transformation_link)) {
                                                {
                                                    SubLObject transformation_tactic = inference_worker_transformation.transformation_link_tactic(transformation_link);
                                                    if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, transformation_tactic, T, UNPROVIDED)) {
                                                        if ((NIL != problem_set_asideP) || (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, transformation_tactic, T, UNPROVIDED, UNPROVIDED))) {
                                                            strategems_to_set_aside = cons(transformation_tactic, strategems_to_set_aside);
                                                        } else {
                                                            strategems_to_activate = cons(transformation_tactic, strategems_to_activate);
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
                    if (NIL == inference_datastructures_problem.single_literal_problem_p(problem)) {
                        {
                            SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
                            SubLObject logical_tactic = NIL;
                            for (logical_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , logical_tactic = cdolist_list_var.first()) {
                                if (NIL != inference_datastructures_problem.do_problem_tactics_type_match(logical_tactic, $LOGICAL)) {
                                    if (NIL == transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, logical_tactic)) {
                                        if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, logical_tactic, T, UNPROVIDED)) {
                                            if ((NIL != problem_set_asideP) || (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, logical_tactic, T, UNPROVIDED, UNPROVIDED))) {
                                                strategems_to_set_aside = cons(logical_tactic, strategems_to_set_aside);
                                            } else {
                                                strategems_to_activate = cons(logical_tactic, strategems_to_activate);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            strategems_to_activate = nreverse(strategems_to_activate);
            strategems_to_set_aside = nreverse(strategems_to_set_aside);
            {
                SubLObject strategems_to_throw_away = NIL;
                {
                    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
                    SubLObject transformation_tactic = NIL;
                    for (transformation_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , transformation_tactic = cdolist_list_var.first()) {
                        if ((NIL != inference_datastructures_problem.do_problem_tactics_type_match(transformation_tactic, $TRANSFORMATION)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(transformation_tactic, $POSSIBLE))) {
                            if (!((NIL != list_utilities.member_eqP(transformation_tactic, strategems_to_activate)) || (NIL != list_utilities.member_eqP(transformation_tactic, strategems_to_set_aside)))) {
                                strategems_to_throw_away = cons(transformation_tactic, strategems_to_throw_away);
                            }
                        }
                    }
                }
                {
                    SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
                    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                    SubLObject state = NIL;
                    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                        {
                            SubLObject transformation_link = set_contents.do_set_contents_next(basis_object, state);
                            if (NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) {
                                if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION)) {
                                    if (!((NIL != list_utilities.member_eqP(transformation_link, strategems_to_activate)) || (NIL != list_utilities.member_eqP(transformation_link, strategems_to_set_aside)))) {
                                        strategems_to_throw_away = cons(transformation_link, strategems_to_throw_away);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
                    SubLObject logical_tactic = NIL;
                    for (logical_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , logical_tactic = cdolist_list_var.first()) {
                        if (NIL != inference_datastructures_problem.do_problem_tactics_type_match(logical_tactic, $LOGICAL)) {
                            if (!((NIL != list_utilities.member_eqP(logical_tactic, strategems_to_activate)) || (NIL != list_utilities.member_eqP(logical_tactic, strategems_to_set_aside)))) {
                                strategems_to_throw_away = cons(logical_tactic, strategems_to_throw_away);
                            }
                        }
                    }
                }
                return values(strategems_to_activate, strategems_to_set_aside, strategems_to_throw_away);
            }
        }
    }

    /**
     *
     *
     * @return 0 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to activate wrt transformation.
    Strategems are ordered in intended order of activation.
     * @return 1 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to set aside wrt transformation.
     * @return 2 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to throw away wrt transformation.
     */
    @LispMethod(comment = "@return 0 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to activate wrt transformation.\r\nStrategems are ordered in intended order of activation.\r\n@return 1 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to set aside wrt transformation.\r\n@return 2 listp of strategem-p; an ordered list of strategems on PROBLEM which STRATEGY may want to throw away wrt transformation.")
    public static SubLObject transformation_strategy_categorize_strategems(final SubLObject strategy, final SubLObject problem) {
        SubLObject strategems_to_activate = NIL;
        SubLObject strategems_to_set_aside = NIL;
        if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_problemP(strategy, problem, UNPROVIDED)) {
            final SubLObject problem_set_asideP = transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_problemP(strategy, problem, UNPROVIDED, UNPROVIDED);
            SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
            SubLObject transformation_tactic = NIL;
            transformation_tactic = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (((NIL != inference_datastructures_problem.do_problem_tactics_type_match(transformation_tactic, $TRANSFORMATION)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(transformation_tactic, $POSSIBLE))) && (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, transformation_tactic, T, UNPROVIDED))) {
                    if ((NIL != problem_set_asideP) || (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, transformation_tactic, T, UNPROVIDED, UNPROVIDED))) {
                        strategems_to_set_aside = cons(transformation_tactic, strategems_to_set_aside);
                    } else {
                        strategems_to_activate = cons(transformation_tactic, strategems_to_activate);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                transformation_tactic = cdolist_list_var.first();
            } 
            final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
            SubLObject basis_object;
            SubLObject state;
            SubLObject transformation_link;
            SubLObject transformation_tactic2;
            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                transformation_link = set_contents.do_set_contents_next(basis_object, state);
                if ((((NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION))) && (NIL != inference_datastructures_problem_link.problem_link_with_supporting_problem_p(transformation_link))) && (NIL == transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, transformation_link))) {
                    transformation_tactic2 = inference_worker_transformation.transformation_link_tactic(transformation_link);
                    if (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, transformation_tactic2, T, UNPROVIDED)) {
                        if ((NIL != problem_set_asideP) || (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, transformation_tactic2, T, UNPROVIDED, UNPROVIDED))) {
                            strategems_to_set_aside = cons(transformation_tactic2, strategems_to_set_aside);
                        } else {
                            strategems_to_activate = cons(transformation_tactic2, strategems_to_activate);
                        }
                    }
                }
            }
            if (NIL == inference_datastructures_problem.single_literal_problem_p(problem)) {
                cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
                SubLObject logical_tactic = NIL;
                logical_tactic = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if (((NIL != inference_datastructures_problem.do_problem_tactics_type_match(logical_tactic, $LOGICAL)) && (NIL == transformation_tactician_datastructures.transformation_strategy_link_head_motivatedP(strategy, logical_tactic))) && (NIL == transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_tacticP(strategy, logical_tactic, T, UNPROVIDED))) {
                        if ((NIL != problem_set_asideP) || (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_set_aside_tacticP(strategy, logical_tactic, T, UNPROVIDED, UNPROVIDED))) {
                            strategems_to_set_aside = cons(logical_tactic, strategems_to_set_aside);
                        } else {
                            strategems_to_activate = cons(logical_tactic, strategems_to_activate);
                        }
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    logical_tactic = cdolist_list_var.first();
                } 
            }
        }
        strategems_to_activate = nreverse(strategems_to_activate);
        strategems_to_set_aside = nreverse(strategems_to_set_aside);
        SubLObject strategems_to_throw_away = NIL;
        SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
        SubLObject transformation_tactic = NIL;
        transformation_tactic = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if ((((NIL != inference_datastructures_problem.do_problem_tactics_type_match(transformation_tactic, $TRANSFORMATION)) && (NIL != inference_datastructures_problem.do_problem_tactics_status_match(transformation_tactic, $POSSIBLE))) && (NIL == list_utilities.member_eqP(transformation_tactic, strategems_to_activate))) && (NIL == list_utilities.member_eqP(transformation_tactic, strategems_to_set_aside))) {
                strategems_to_throw_away = cons(transformation_tactic, strategems_to_throw_away);
            }
            cdolist_list_var = cdolist_list_var.rest();
            transformation_tactic = cdolist_list_var.first();
        } 
        final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject transformation_link;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            transformation_link = set_contents.do_set_contents_next(basis_object, state);
            if ((((NIL != set_contents.do_set_contents_element_validP(state, transformation_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(transformation_link, $TRANSFORMATION))) && (NIL == list_utilities.member_eqP(transformation_link, strategems_to_activate))) && (NIL == list_utilities.member_eqP(transformation_link, strategems_to_set_aside))) {
                strategems_to_throw_away = cons(transformation_link, strategems_to_throw_away);
            }
        }
        cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
        SubLObject logical_tactic = NIL;
        logical_tactic = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (((NIL != inference_datastructures_problem.do_problem_tactics_type_match(logical_tactic, $LOGICAL)) && (NIL == list_utilities.member_eqP(logical_tactic, strategems_to_activate))) && (NIL == list_utilities.member_eqP(logical_tactic, strategems_to_set_aside))) {
                strategems_to_throw_away = cons(logical_tactic, strategems_to_throw_away);
            }
            cdolist_list_var = cdolist_list_var.rest();
            logical_tactic = cdolist_list_var.first();
        } 
        return values(strategems_to_activate, strategems_to_set_aside, strategems_to_throw_away);
    }

    public static final SubLObject transformation_strategy_reactivate_executable_strategem_alt(SubLObject strategy, SubLObject strategem) {
        SubLTrampolineFile.checkType(strategy, TRANSFORMATION_STRATEGY_P);
        SubLTrampolineFile.checkType(strategem, EXECUTABLE_STRATEGEM_P);
        if (NIL != inference_worker_transformation.transformation_tactic_p(strategem)) {
            return transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, strategem);
        } else {
            if (NIL != inference_worker.meta_structural_tactic_p(strategem)) {
                return strategem;
            }
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_reactivate_executable_strategem(final SubLObject strategy, final SubLObject strategem) {
        assert NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) : "! transformation_tactician_datastructures.transformation_strategy_p(strategy) " + ("transformation_tactician_datastructures.transformation_strategy_p(strategy) " + "CommonSymbols.NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) ") + strategy;
        assert NIL != inference_tactician.executable_strategem_p(strategem) : "! inference_tactician.executable_strategem_p(strategem) " + ("inference_tactician.executable_strategem_p(strategem) " + "CommonSymbols.NIL != inference_tactician.executable_strategem_p(strategem) ") + strategem;
        if (NIL != inference_worker_transformation.transformation_tactic_p(strategem)) {
            return transformation_tactician_datastructures.transformation_strategy_activate_strategem(strategy, strategem);
        }
        if (NIL != inference_worker.meta_structural_tactic_p(strategem)) {
            return strategem;
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_strategically_deactivate_strategem_alt(SubLObject strategy, SubLObject strategem) {
        if (NIL != strategem_invalid_p(strategem)) {
            return NIL;
        }
        {
            SubLObject strategem_var = strategem;
            SubLObject problem = strategem_problem(strategem_var);
            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_deactivate_strategem(strategy, strategem_var);
            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_deactivate_problem(strategy, problem);
        }
        if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
            inference_worker.consider_strategic_ramifications_of_possibly_executed_tactic(strategy, strategem);
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_strategically_deactivate_strategem(final SubLObject strategy, final SubLObject strategem) {
        if (NIL != inference_tactician.strategem_invalid_p(strategem)) {
            return NIL;
        }
        final SubLObject problem = inference_tactician.strategem_problem(strategem);
        transformation_strategy_deactivate_strategem(strategy, strategem);
        transformation_strategy_possibly_deactivate_problem(strategy, problem);
        if (NIL != inference_datastructures_tactic.tactic_p(strategem)) {
            inference_worker.consider_strategic_ramifications_of_possibly_executed_tactic(strategy, strategem);
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_deactivate_strategem_alt(SubLObject strategy, SubLObject strategem) {
        SubLTrampolineFile.checkType(strategy, TRANSFORMATION_STRATEGY_P);
        SubLTrampolineFile.checkType(strategem, TRANSFORMATION_STRATEGEM_P);
        {
            SubLObject problem = strategem_problem(strategem);
            SubLObject index = transformation_tactician_datastructures.transformation_strategy_problem_total_strategems_active(strategy);
            SubLObject count = dictionary.dictionary_lookup_without_values(index, problem, ZERO_INTEGER);
            count = subtract(count, ONE_INTEGER);
            if (count.isPositive()) {
                dictionary.dictionary_enter(index, problem, count);
            } else {
                dictionary.dictionary_remove(index, problem);
                transformation_tactician_datastructures.transformation_strategy_note_problem_pending(strategy, problem);
            }
            return count;
        }
    }

    public static SubLObject transformation_strategy_deactivate_strategem(final SubLObject strategy, final SubLObject strategem) {
        assert NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) : "! transformation_tactician_datastructures.transformation_strategy_p(strategy) " + ("transformation_tactician_datastructures.transformation_strategy_p(strategy) " + "CommonSymbols.NIL != transformation_tactician_datastructures.transformation_strategy_p(strategy) ") + strategy;
        assert NIL != inference_tactician.transformation_strategem_p(strategem) : "! inference_tactician.transformation_strategem_p(strategem) " + ("inference_tactician.transformation_strategem_p(strategem) " + "CommonSymbols.NIL != inference_tactician.transformation_strategem_p(strategem) ") + strategem;
        final SubLObject problem = inference_tactician.strategem_problem(strategem);
        final SubLObject index = transformation_tactician_datastructures.transformation_strategy_problem_total_strategems_active(strategy);
        SubLObject count = dictionary.dictionary_lookup_without_values(index, problem, ZERO_INTEGER);
        count = subtract(count, ONE_INTEGER);
        if (count.isPositive()) {
            dictionary.dictionary_enter(index, problem, count);
        } else {
            dictionary.dictionary_remove(index, problem);
            transformation_tactician_datastructures.transformation_strategy_note_problem_pending(strategy, problem);
        }
        return count;
    }

    public static final SubLObject transformation_strategy_possibly_deactivate_problem_alt(SubLObject strategy, SubLObject problem) {
        if (NIL == transformation_tactician_datastructures.transformation_strategy_problem_activeP(strategy, problem)) {
            inference_datastructures_strategy.strategy_note_problem_inactive(strategy, problem);
            if (NIL != transformation_tactician_datastructures.transformation_strategy_problem_set_asideP(strategy, problem)) {
                inference_datastructures_strategy.strategy_note_problem_set_aside(strategy, problem);
                return T;
            }
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_possibly_deactivate_problem(final SubLObject strategy, final SubLObject problem) {
        if (NIL == transformation_tactician_datastructures.transformation_strategy_problem_activeP(strategy, problem)) {
            inference_datastructures_strategy.strategy_note_problem_inactive(strategy, problem);
            if (NIL != transformation_tactician_datastructures.transformation_strategy_problem_set_asideP(strategy, problem)) {
                inference_datastructures_strategy.strategy_note_problem_set_aside(strategy, problem);
                return T;
            }
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_consider_that_problem_could_be_strategically_totally_pending_alt(SubLObject strategy, SubLObject problem) {
        {
            SubLObject pendingP = com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_consider_that_problem_could_be_strategically_pending(strategy, problem);
            return pendingP;
        }
    }

    public static SubLObject transformation_strategy_consider_that_problem_could_be_strategically_totally_pending(final SubLObject strategy, final SubLObject problem) {
        final SubLObject pendingP = transformation_strategy_consider_that_problem_could_be_strategically_pending(strategy, problem);
        return pendingP;
    }

    public static final SubLObject transformation_strategy_consider_that_problem_could_be_strategically_pending_alt(SubLObject strategy, SubLObject problem) {
        if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_problemP(strategy, problem, UNPROVIDED)) {
            com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_make_problem_pending(strategy, problem);
            return T;
        }
        return NIL;
    }

    public static SubLObject transformation_strategy_consider_that_problem_could_be_strategically_pending(final SubLObject strategy, final SubLObject problem) {
        if (NIL != transformation_tactician_uninterestingness.transformation_strategy_chooses_to_throw_away_problemP(strategy, problem, UNPROVIDED)) {
            transformation_strategy_make_problem_pending(strategy, problem);
            return T;
        }
        return NIL;
    }

    public static final SubLObject transformation_strategy_make_problem_pending_alt(SubLObject strategy, SubLObject problem) {
        transformation_tactician_datastructures.transformation_strategy_note_problem_pending(strategy, problem);
        com.cyc.cycjava.cycl.inference.harness.transformation_tactician_motivation.transformation_strategy_possibly_deactivate_problem(strategy, problem);
        return NIL;
    }

    public static SubLObject transformation_strategy_make_problem_pending(final SubLObject strategy, final SubLObject problem) {
        transformation_tactician_datastructures.transformation_strategy_note_problem_pending(strategy, problem);
        transformation_strategy_possibly_deactivate_problem(strategy, problem);
        return NIL;
    }

    public static SubLObject transformation_strategy_problem_nothing_to_doP(final SubLObject strategy, final SubLObject problem) {
        final SubLObject master = inference_tactician.controlling_strategy(strategy);
        SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(problem);
        SubLObject tactic = NIL;
        tactic = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL == inference_tactician_strategic_uninterestingness.strategy_chooses_to_throw_away_tacticP(master, tactic, NIL, NIL)) {
                return NIL;
            }
            cdolist_list_var = cdolist_list_var.rest();
            tactic = cdolist_list_var.first();
        } 
        return T;
    }

    public static SubLObject declare_transformation_tactician_motivation_file() {
        declareMacro("transformation_strategy_with_strategically_active_strategem", "TRANSFORMATION-STRATEGY-WITH-STRATEGICALLY-ACTIVE-STRATEGEM");
        declareFunction("transformation_strategy_possibly_propagate_motivation_to_link_head", "TRANSFORMATION-STRATEGY-POSSIBLY-PROPAGATE-MOTIVATION-TO-LINK-HEAD", 2, 0, false);
        declareFunction("transformation_strategy_propagate_motivation_to_link_head", "TRANSFORMATION-STRATEGY-PROPAGATE-MOTIVATION-TO-LINK-HEAD", 2, 0, false);
        declareFunction("transformation_strategy_link_motivates_problemP", "TRANSFORMATION-STRATEGY-LINK-MOTIVATES-PROBLEM?", 2, 1, false);
        declareFunction("transformation_strategy_link_motivates_lookahead_problemP", "TRANSFORMATION-STRATEGY-LINK-MOTIVATES-LOOKAHEAD-PROBLEM?", 2, 0, false);
        declareFunction("transformation_strategy_possibly_propagate_motivation_to_problem", "TRANSFORMATION-STRATEGY-POSSIBLY-PROPAGATE-MOTIVATION-TO-PROBLEM", 2, 0, false);
        declareFunction("transformation_strategy_propagate_motivation_to_problem", "TRANSFORMATION-STRATEGY-PROPAGATE-MOTIVATION-TO-PROBLEM", 2, 0, false);
        declareFunction("transformation_strategy_possibly_activate_problem", "TRANSFORMATION-STRATEGY-POSSIBLY-ACTIVATE-PROBLEM", 2, 0, false);
        declareFunction("transformation_strategy_problem_is_the_rest_of_a_join_orderedP", "TRANSFORMATION-STRATEGY-PROBLEM-IS-THE-REST-OF-A-JOIN-ORDERED?", 2, 0, false);
        declareFunction("transformation_strategy_possibly_propagate_proof_spec_to_restricted_non_focals", "TRANSFORMATION-STRATEGY-POSSIBLY-PROPAGATE-PROOF-SPEC-TO-RESTRICTED-NON-FOCALS", 2, 0, false);
        declareFunction("transformation_strategy_chooses_not_to_examine_problemP", "TRANSFORMATION-STRATEGY-CHOOSES-NOT-TO-EXAMINE-PROBLEM?", 2, 0, false);
        declareFunction("transformation_strategy_chooses_not_to_activate_problemP", "TRANSFORMATION-STRATEGY-CHOOSES-NOT-TO-ACTIVATE-PROBLEM?", 2, 0, false);
        declareFunction("transformation_strategy_activate_problem", "TRANSFORMATION-STRATEGY-ACTIVATE-PROBLEM", 2, 0, false);
        declareFunction("transformation_strategy_possibly_activate_strategems", "TRANSFORMATION-STRATEGY-POSSIBLY-ACTIVATE-STRATEGEMS", 2, 0, false);
        declareFunction("transformation_strategy_activate_transformation_argument_links", "TRANSFORMATION-STRATEGY-ACTIVATE-TRANSFORMATION-ARGUMENT-LINKS", 2, 0, false);
        declareFunction("transformation_strategy_note_argument_link_added", "TRANSFORMATION-STRATEGY-NOTE-ARGUMENT-LINK-ADDED", 2, 0, false);
        declareFunction("transformation_strategy_possibly_activate_transformation_link", "TRANSFORMATION-STRATEGY-POSSIBLY-ACTIVATE-TRANSFORMATION-LINK", 2, 0, false);
        declareFunction("transformation_strategy_activate_transformation_link", "TRANSFORMATION-STRATEGY-ACTIVATE-TRANSFORMATION-LINK", 2, 0, false);
        declareFunction("transformation_strategy_note_new_tactic", "TRANSFORMATION-STRATEGY-NOTE-NEW-TACTIC", 2, 0, false);
        declareFunction("transformation_strategy_note_split_tactics_strategically_possible", "TRANSFORMATION-STRATEGY-NOTE-SPLIT-TACTICS-STRATEGICALLY-POSSIBLE", 2, 0, false);
        declareFunction("transformation_strategy_note_new_tactic_possible", "TRANSFORMATION-STRATEGY-NOTE-NEW-TACTIC-POSSIBLE", 2, 0, false);
        declareFunction("transformation_strategy_categorize_strategems", "TRANSFORMATION-STRATEGY-CATEGORIZE-STRATEGEMS", 2, 0, false);
        declareFunction("transformation_strategy_reactivate_executable_strategem", "TRANSFORMATION-STRATEGY-REACTIVATE-EXECUTABLE-STRATEGEM", 2, 0, false);
        declareFunction("transformation_strategy_strategically_deactivate_strategem", "TRANSFORMATION-STRATEGY-STRATEGICALLY-DEACTIVATE-STRATEGEM", 2, 0, false);
        declareFunction("transformation_strategy_deactivate_strategem", "TRANSFORMATION-STRATEGY-DEACTIVATE-STRATEGEM", 2, 0, false);
        declareFunction("transformation_strategy_possibly_deactivate_problem", "TRANSFORMATION-STRATEGY-POSSIBLY-DEACTIVATE-PROBLEM", 2, 0, false);
        declareFunction("transformation_strategy_consider_that_problem_could_be_strategically_totally_pending", "TRANSFORMATION-STRATEGY-CONSIDER-THAT-PROBLEM-COULD-BE-STRATEGICALLY-TOTALLY-PENDING", 2, 0, false);
        declareFunction("transformation_strategy_consider_that_problem_could_be_strategically_pending", "TRANSFORMATION-STRATEGY-CONSIDER-THAT-PROBLEM-COULD-BE-STRATEGICALLY-PENDING", 2, 0, false);
        declareFunction("transformation_strategy_make_problem_pending", "TRANSFORMATION-STRATEGY-MAKE-PROBLEM-PENDING", 2, 0, false);
        declareFunction("transformation_strategy_problem_nothing_to_doP", "TRANSFORMATION-STRATEGY-PROBLEM-NOTHING-TO-DO?", 2, 0, false);
        return NIL;
    }

    public static SubLObject init_transformation_tactician_motivation_file() {
        defparameter("*TRANSFORMATION-STRATEGY-SELF-LOOPING-RULE-FIX-ENABLED?*", T);
        return NIL;
    }

    public static SubLObject setup_transformation_tactician_motivation_file() {
        note_funcall_helper_function(TRANSFORMATION_STRATEGY_NOTE_NEW_TACTIC);
        note_funcall_helper_function(TRANSFORMATION_STRATEGY_NOTE_SPLIT_TACTICS_STRATEGICALLY_POSSIBLE);
        note_funcall_helper_function($sym22$TRANSFORMATION_STRATEGY_PROBLEM_NOTHING_TO_DO_);
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_transformation_tactician_motivation_file();
    }

    @Override
    public void initializeVariables() {
        init_transformation_tactician_motivation_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_transformation_tactician_motivation_file();
    }

    static {
    }

    // Internal Constants
    @LispMethod(comment = "Internal Constants")
    static private final SubLList $list_alt0 = list(list(makeSymbol("STRATEGY"), makeSymbol("STRATEGEM")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLString $str_alt10$_s_tried_to_propagate_T_to__s_but = makeString("~s tried to propagate T to ~s but it throws away all transformation");
}

/**
 * Total time: 337 ms
 */
