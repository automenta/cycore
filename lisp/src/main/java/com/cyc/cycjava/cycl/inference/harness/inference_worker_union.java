/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.inference.harness;


import static com.cyc.cycjava.cycl.id_index.do_id_index_empty_p;
import static com.cyc.cycjava.cycl.id_index.do_id_index_id_and_object_validP;
import static com.cyc.cycjava.cycl.id_index.do_id_index_next_id;
import static com.cyc.cycjava.cycl.id_index.do_id_index_next_state;
import static com.cyc.cycjava.cycl.id_index.do_id_index_state_object;
import static com.cyc.cycjava.cycl.id_index.id_index_dense_objects;
import static com.cyc.cycjava.cycl.id_index.id_index_dense_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_skip_tombstones_p;
import static com.cyc.cycjava.cycl.id_index.id_index_sparse_objects;
import static com.cyc.cycjava.cycl.id_index.id_index_sparse_objects_empty_p;
import static com.cyc.cycjava.cycl.id_index.id_index_tombstone_p;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryKey;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.getEntryValue;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorHasNext;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.iteratorNextEntry;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Hashtables.releaseEntrySetIterator;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Vectors.aref;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.intersection;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;

import java.util.Iterator;
import java.util.Map;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.bindings;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.set;
import com.cyc.cycjava.cycl.set_contents;
import com.cyc.cycjava.cycl.variables;
import com.cyc.cycjava.cycl.inference.modules.preference_modules;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.SubLThread;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObject;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLProcess;
import com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLString;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      INFERENCE-WORKER-UNION
 * source file: /cyc/top/cycl/inference/harness/inference-worker-union.lisp
 * created:     2019/07/03 17:37:40
 */
public final class inference_worker_union extends SubLTranslatedFile implements V12 {
    public static final SubLFile me = new inference_worker_union();

 public static final String myName = "com.cyc.cycjava.cycl.inference.harness.inference_worker_union";


    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $union_module$ = makeSymbol("*UNION-MODULE*");

    // deflexical
    /**
     * The preference level used for union tactics. Union tactics are independent of
     * each other, so no bindings from one half could possibly make the other half
     * any more solvable. Hence, all union tactics should be preferred.
     */
    @LispMethod(comment = "The preference level used for union tactics. Union tactics are independent of\r\neach other, so no bindings from one half could possibly make the other half\r\nany more solvable. Hence, all union tactics should be preferred.\ndeflexical\nThe preference level used for union tactics. Union tactics are independent of\neach other, so no bindings from one half could possibly make the other half\nany more solvable. Hence, all union tactics should be preferred.")
    public static final SubLSymbol $union_tactic_preference_level$ = makeSymbol("*UNION-TACTIC-PREFERENCE-LEVEL*");

    // deflexical
    // the preference level for all union tactics
    /**
     * the preference level for all union tactics
     */
    @LispMethod(comment = "the preference level for all union tactics\ndeflexical")
    public static final SubLSymbol $union_tactic_preference_level_justification$ = makeSymbol("*UNION-TACTIC-PREFERENCE-LEVEL-JUSTIFICATION*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $disjunction_assumption_module$ = makeSymbol("*DISJUNCTION-ASSUMPTION-MODULE*");

    static private final SubLString $str1$couldn_t_find_the_union_tactic_fo = makeString("couldn't find the union tactic for ~s");

    static private final SubLString $str3$Could_not_find_the_link_for__a = makeString("Could not find the link for ~a");

    private static final SubLSymbol $sym4$HL_VAR_ = makeSymbol("HL-VAR?");

    private static final SubLSymbol UNION_TACTIC_P = makeSymbol("UNION-TACTIC-P");

    // Definitions
    public static final SubLObject union_link_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_problem_link.problem_link_p(v_object)) && ($UNION == inference_datastructures_problem_link.problem_link_type(v_object)));
    }

    // Definitions
    public static SubLObject union_link_p(final SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_problem_link.problem_link_p(v_object)) && ($UNION == inference_datastructures_problem_link.problem_link_type(v_object)));
    }

    /**
     *
     *
     * @return union-link-p, either the already existing one or a new one.
     */
    @LispMethod(comment = "@return union-link-p, either the already existing one or a new one.")
    public static final SubLObject maybe_new_union_link_alt(SubLObject supported_problem, SubLObject supporting_mapped_problem) {
        {
            SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(supported_problem);
            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
            SubLObject state = NIL;
            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                {
                    SubLObject candidate_link = set_contents.do_set_contents_next(basis_object, state);
                    if (NIL != set_contents.do_set_contents_element_validP(state, candidate_link)) {
                        if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(candidate_link, $UNION)) {
                            {
                                SubLObject candidate_mapped_problem = inference_datastructures_problem_link.problem_link_sole_supporting_mapped_problem(candidate_link);
                                if (NIL != inference_datastructures_problem_link.mapped_problem_equal(supporting_mapped_problem, candidate_mapped_problem)) {
                                    return candidate_link;
                                }
                            }
                        }
                    }
                }
            }
        }
        return com.cyc.cycjava.cycl.inference.harness.inference_worker_union.new_union_link(supported_problem, supporting_mapped_problem);
    }

    /**
     *
     *
     * @return union-link-p, either the already existing one or a new one.
     */
    @LispMethod(comment = "@return union-link-p, either the already existing one or a new one.")
    public static SubLObject maybe_new_union_link(final SubLObject supported_problem, final SubLObject supporting_mapped_problem) {
        final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(supported_problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject candidate_link;
        SubLObject candidate_mapped_problem;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            candidate_link = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, candidate_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(candidate_link, $UNION))) {
                candidate_mapped_problem = inference_datastructures_problem_link.problem_link_sole_supporting_mapped_problem(candidate_link);
                if (NIL != inference_datastructures_problem_link.mapped_problem_equal(supporting_mapped_problem, candidate_mapped_problem)) {
                    return candidate_link;
                }
            }
        }
        return new_union_link(supported_problem, supporting_mapped_problem);
    }

    /**
     * Union links are one-to-one.
     */
    @LispMethod(comment = "Union links are one-to-one.")
    public static final SubLObject new_union_link_alt(SubLObject supported_problem, SubLObject supporting_mapped_problem) {
        {
            SubLObject link = inference_datastructures_problem_link.new_problem_link($UNION, supported_problem);
            inference_datastructures_problem_link.connect_supporting_mapped_problem_with_dependent_link(supporting_mapped_problem, link);
            inference_worker.propagate_problem_link(link);
            return link;
        }
    }

    /**
     * Union links are one-to-one.
     */
    @LispMethod(comment = "Union links are one-to-one.")
    public static SubLObject new_union_link(final SubLObject supported_problem, final SubLObject supporting_mapped_problem) {
        final SubLObject link = inference_datastructures_problem_link.new_problem_link($UNION, supported_problem);
        inference_datastructures_problem_link.connect_supporting_mapped_problem_with_dependent_link(supporting_mapped_problem, link);
        inference_worker.propagate_problem_link(link);
        return link;
    }

    public static final SubLObject destroy_union_link_alt(SubLObject union_link) {
        {
            SubLObject tactic = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.union_link_tactic(union_link);
            if (NIL != inference_datastructures_tactic.valid_tactic_p(tactic)) {
                inference_datastructures_tactic.destroy_problem_tactic_and_backpointers(tactic);
            }
        }
        return union_link;
    }

    public static SubLObject destroy_union_link(final SubLObject union_link) {
        final SubLObject tactic = union_link_tactic_int(union_link);
        if (NIL != inference_datastructures_tactic.valid_tactic_p(tactic)) {
            inference_datastructures_tactic.destroy_problem_tactic_and_backpointers(tactic);
        }
        return union_link;
    }

    public static final SubLObject union_link_supporting_problem_alt(SubLObject union_link) {
        return inference_datastructures_problem_link.problem_link_sole_supporting_problem(union_link);
    }

    public static SubLObject union_link_supporting_problem(final SubLObject union_link) {
        return inference_datastructures_problem_link.problem_link_sole_supporting_problem(union_link);
    }

    public static final SubLObject union_link_tactic_alt(SubLObject union_link) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(union_link);
                SubLObject disjunct_mapped_problem = inference_datastructures_problem_link.problem_link_sole_supporting_mapped_problem(union_link);
                SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(supported_problem);
                SubLObject union_tactic = NIL;
                for (union_tactic = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , union_tactic = cdolist_list_var.first()) {
                    if (NIL != inference_datastructures_problem.do_problem_tactics_hl_module_match(union_tactic, $union_module$.getDynamicValue(thread))) {
                        {
                            SubLObject candidate_mapped_problem = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.find_or_create_union_tactic_disjunct_mapped_problem(union_tactic);
                            if (NIL != inference_datastructures_problem_link.mapped_problem_equal(disjunct_mapped_problem, candidate_mapped_problem)) {
                                return union_tactic;
                            }
                        }
                    }
                }
                if (NIL == inference_datastructures_problem.tactically_unexamined_problem_p(supported_problem)) {
                    return Errors.error($str_alt1$couldn_t_find_the_union_tactic_fo, union_link);
                }
            }
            return NIL;
        }
    }

    public static SubLObject union_link_tactic(final SubLObject union_link) {
        final SubLObject tactic = union_link_tactic_int(union_link);
        if ((NIL == tactic) && (NIL == inference_datastructures_problem.tactically_unexamined_problem_p(inference_datastructures_problem_link.problem_link_supported_problem(union_link)))) {
            return Errors.error($str1$couldn_t_find_the_union_tactic_fo, union_link);
        }
        return tactic;
    }

    public static SubLObject union_link_tactic_int(final SubLObject union_link) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject supported_problem = inference_datastructures_problem_link.problem_link_supported_problem(union_link);
        final SubLObject disjunct_mapped_problem = inference_datastructures_problem_link.problem_link_sole_supporting_mapped_problem(union_link);
        final SubLObject disjunct_variable_map = inference_datastructures_problem_link.mapped_problem_variable_map(disjunct_mapped_problem);
        final SubLObject disjunct_problem = inference_datastructures_problem_link.mapped_problem_problem(disjunct_mapped_problem);
        final SubLObject disjunct_query = inference_datastructures_problem.problem_query(disjunct_problem);
        SubLObject cdolist_list_var = inference_datastructures_problem.problem_tactics(supported_problem);
        SubLObject union_tactic = NIL;
        union_tactic = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            if (NIL != inference_datastructures_problem.do_problem_tactics_hl_module_match(union_tactic, $union_module$.getDynamicValue(thread))) {
                final SubLObject candidate_mapped_problem = find_union_tactic_disjunct_mapped_problem(union_tactic, NIL);
                if (((NIL != candidate_mapped_problem) && disjunct_query.equal(inference_datastructures_problem.problem_query(inference_datastructures_problem_link.mapped_problem_problem(candidate_mapped_problem)))) && disjunct_variable_map.equal(inference_datastructures_problem_link.mapped_problem_variable_map(candidate_mapped_problem))) {
                    return union_tactic;
                }
            }
            cdolist_list_var = cdolist_list_var.rest();
            union_tactic = cdolist_list_var.first();
        } 
        return NIL;
    }

    public static final SubLObject union_tactic_p_alt(SubLObject v_object) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(v_object)) && ($union_module$.getDynamicValue(thread) == inference_datastructures_tactic.tactic_hl_module(v_object)));
        }
    }

    public static SubLObject union_tactic_p(final SubLObject v_object) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(v_object)) && $union_module$.getDynamicValue(thread).eql(inference_datastructures_tactic.tactic_hl_module(v_object)));
    }

    public static final SubLObject new_union_tactic_alt(SubLObject problem, SubLObject disjunct_index) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject tactic = inference_datastructures_tactic.new_tactic(problem, $union_module$.getDynamicValue(thread), disjunct_index);
                SubLObject prob = problem;
                SubLObject store = inference_datastructures_problem.problem_store(prob);
                SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
                if (NIL == do_id_index_empty_p(idx, $SKIP)) {
                    {
                        SubLObject id = do_id_index_next_id(idx, NIL, NIL, NIL);
                        SubLObject state_var = do_id_index_next_state(idx, NIL, id, NIL);
                        SubLObject inference = NIL;
                        while (NIL != id) {
                            inference = do_id_index_state_object(idx, $SKIP, id, state_var);
                            if (NIL != do_id_index_id_and_object_validP(id, inference, $SKIP)) {
                                if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(prob, inference)) {
                                    {
                                        SubLObject inference_var = inference;
                                        SubLObject set_contents_var = set.do_set_internal(inference_datastructures_inference.inference_strategy_set(inference_var));
                                        SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                                        SubLObject state = NIL;
                                        for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                            {
                                                SubLObject strategy = set_contents.do_set_contents_next(basis_object, state);
                                                if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                                    inference_tactician.strategy_note_new_tactic(strategy, tactic);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            id = do_id_index_next_id(idx, NIL, id, state_var);
                            state_var = do_id_index_next_state(idx, NIL, id, state_var);
                        } 
                    }
                }
                return tactic;
            }
        }
    }

    public static SubLObject new_union_tactic(final SubLObject problem, final SubLObject disjunct_index) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject tactic = inference_datastructures_tactic.new_tactic(problem, $union_module$.getDynamicValue(thread), disjunct_index);
        final SubLObject store = inference_datastructures_problem.problem_store(problem);
        final SubLObject idx = inference_datastructures_problem_store.problem_store_inference_id_index(store);
        if (NIL == id_index_objects_empty_p(idx, $SKIP)) {
            final SubLObject idx_$1 = idx;
            if (NIL == id_index_dense_objects_empty_p(idx_$1, $SKIP)) {
                final SubLObject vector_var = id_index_dense_objects(idx_$1);
                final SubLObject backwardP_var = NIL;
                SubLObject length;
                SubLObject v_iteration;
                SubLObject id;
                SubLObject inference;
                SubLObject inference_var;
                SubLObject set_var;
                SubLObject set_contents_var;
                SubLObject basis_object;
                SubLObject state;
                SubLObject strategy;
                for (length = length(vector_var), v_iteration = NIL, v_iteration = ZERO_INTEGER; v_iteration.numL(length); v_iteration = add(v_iteration, ONE_INTEGER)) {
                    id = (NIL != backwardP_var) ? subtract(length, v_iteration, ONE_INTEGER) : v_iteration;
                    inference = aref(vector_var, id);
                    if ((NIL == id_index_tombstone_p(inference)) || (NIL == id_index_skip_tombstones_p($SKIP))) {
                        if (NIL != id_index_tombstone_p(inference)) {
                            inference = $SKIP;
                        }
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference)) {
                            inference_var = inference;
                            set_var = inference_datastructures_inference.inference_strategy_set(inference_var);
                            set_contents_var = set.do_set_internal(set_var);
                            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                strategy = set_contents.do_set_contents_next(basis_object, state);
                                if (NIL != set_contents.do_set_contents_element_validP(state, strategy)) {
                                    inference_tactician.strategy_note_new_tactic(strategy, tactic);
                                }
                            }
                        }
                    }
                }
            }
            final SubLObject idx_$2 = idx;
            if (NIL == id_index_sparse_objects_empty_p(idx_$2)) {
                final SubLObject cdohash_table = id_index_sparse_objects(idx_$2);
                SubLObject id2 = NIL;
                SubLObject inference2 = NIL;
                final Iterator cdohash_iterator = getEntrySetIterator(cdohash_table);
                try {
                    while (iteratorHasNext(cdohash_iterator)) {
                        final Map.Entry cdohash_entry = iteratorNextEntry(cdohash_iterator);
                        id2 = getEntryKey(cdohash_entry);
                        inference2 = getEntryValue(cdohash_entry);
                        if (NIL != inference_datastructures_problem.problem_relevant_to_inferenceP(problem, inference2)) {
                            final SubLObject inference_var2 = inference2;
                            final SubLObject set_var2 = inference_datastructures_inference.inference_strategy_set(inference_var2);
                            final SubLObject set_contents_var2 = set.do_set_internal(set_var2);
                            SubLObject basis_object2;
                            SubLObject state2;
                            SubLObject strategy2;
                            for (basis_object2 = set_contents.do_set_contents_basis_object(set_contents_var2), state2 = NIL, state2 = set_contents.do_set_contents_initial_state(basis_object2, set_contents_var2); NIL == set_contents.do_set_contents_doneP(basis_object2, state2); state2 = set_contents.do_set_contents_update_state(state2)) {
                                strategy2 = set_contents.do_set_contents_next(basis_object2, state2);
                                if (NIL != set_contents.do_set_contents_element_validP(state2, strategy2)) {
                                    inference_tactician.strategy_note_new_tactic(strategy2, tactic);
                                }
                            }
                        }
                    } 
                } finally {
                    releaseEntrySetIterator(cdohash_iterator);
                }
            }
        }
        return tactic;
    }

    public static final SubLObject union_tactic_disjunct_index_alt(SubLObject union_tactic) {
        return inference_datastructures_tactic.tactic_data(union_tactic);
    }

    public static SubLObject union_tactic_disjunct_index(final SubLObject union_tactic) {
        return inference_datastructures_tactic.tactic_data(union_tactic);
    }

    /**
     *
     *
     * @return nil or problem-link-p; the link created by UNION-TACTIC
    NIL should only occur if the tactic has been discarded.
     */
    @LispMethod(comment = "@return nil or problem-link-p; the link created by UNION-TACTIC\r\nNIL should only occur if the tactic has been discarded.")
    public static final SubLObject union_tactic_link_alt(SubLObject union_tactic) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject disjunction_problem = inference_datastructures_tactic.tactic_problem(union_tactic);
                {
                    SubLObject disjunct_mapped_problem = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.find_or_create_union_tactic_disjunct_mapped_problem(union_tactic);
                    SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(disjunction_problem);
                    SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                    SubLObject state = NIL;
                    for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                        {
                            SubLObject union_link = set_contents.do_set_contents_next(basis_object, state);
                            if (NIL != set_contents.do_set_contents_element_validP(state, union_link)) {
                                if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(union_link, $UNION)) {
                                    {
                                        SubLObject candidate_mapped_problem = inference_datastructures_problem_link.problem_link_sole_supporting_mapped_problem(union_link);
                                        if (NIL != inference_datastructures_problem_link.mapped_problem_equal(disjunct_mapped_problem, candidate_mapped_problem)) {
                                            return union_link;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (NIL == inference_datastructures_tactic.tactic_discardedP(union_tactic)) {
                    if (NIL != inference_datastructures_problem_store.$problem_store_modification_permittedP$.getDynamicValue(thread)) {
                        {
                            SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(disjunction_problem);
                            SubLObject basis_object = set_contents.do_set_contents_basis_object(set_contents_var);
                            SubLObject state = NIL;
                            for (state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                                {
                                    SubLObject union_link = set_contents.do_set_contents_next(basis_object, state);
                                    if (NIL != set_contents.do_set_contents_element_validP(state, union_link)) {
                                        if (NIL != inference_datastructures_problem_link.problem_link_has_typeP(union_link, $UNION)) {
                                            {
                                                SubLObject candidate_mapped_problem = inference_datastructures_problem_link.problem_link_sole_supporting_mapped_problem(union_link);
                                                SubLObject candidate_problem = inference_datastructures_problem_link.mapped_problem_problem(candidate_mapped_problem);
                                                SubLObject clean_problem = inference_datastructures_problem_store.find_problem_by_query(inference_datastructures_tactic.tactic_store(union_tactic), inference_datastructures_problem.problem_query(candidate_problem));
                                                if (candidate_problem != clean_problem) {
                                                    {
                                                        SubLObject clean_mapped_problem = inference_datastructures_problem_link.new_mapped_problem(clean_problem, inference_datastructures_problem_link.mapped_problem_variable_map(candidate_mapped_problem));
                                                        SubLObject clean_union_link = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.new_union_link(disjunction_problem, clean_mapped_problem);
                                                        return clean_union_link;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return Errors.error($str_alt3$Could_not_find_the_link_for__a, union_tactic);
                }
            }
            return NIL;
        }
    }

    /**
     *
     *
     * @return nil or problem-link-p; the link created by UNION-TACTIC
    NIL should only occur if the tactic has been discarded.
     */
    @LispMethod(comment = "@return nil or problem-link-p; the link created by UNION-TACTIC\r\nNIL should only occur if the tactic has been discarded.")
    public static SubLObject union_tactic_link(final SubLObject union_tactic) {
        final SubLObject union_link = union_tactic_link_int(union_tactic);
        if (NIL == inference_datastructures_problem_link.problem_link_p(union_link)) {
            return Errors.error($str3$Could_not_find_the_link_for__a, union_tactic);
        }
        return union_link;
    }

    public static SubLObject union_tactic_link_int(final SubLObject union_tactic) {
        final SubLObject disjunction_problem = inference_datastructures_tactic.tactic_problem(union_tactic);
        final SubLObject disjunct_mapped_problem = find_union_tactic_disjunct_mapped_problem(union_tactic, NIL);
        if (NIL != disjunct_mapped_problem) {
            final SubLObject disjunct_problem = inference_datastructures_problem_link.mapped_problem_problem(disjunct_mapped_problem);
            final SubLObject set_contents_var = inference_datastructures_problem.problem_dependent_links(disjunct_problem);
            SubLObject basis_object;
            SubLObject state;
            SubLObject union_link;
            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
                union_link = set_contents.do_set_contents_next(basis_object, state);
                if (((NIL != set_contents.do_set_contents_element_validP(state, union_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(union_link, $UNION))) && disjunction_problem.eql(inference_datastructures_problem_link.problem_link_supported_problem(union_link))) {
                    return union_link;
                }
            }
        }
        return NIL;
    }

    public static SubLObject find_union_tactic_disjunct_mapped_problem(final SubLObject tactic, final SubLObject must_be_cleanP) {
        final SubLObject disjunct_index = union_tactic_disjunct_index(tactic);
        final SubLObject disjunction_problem = inference_datastructures_tactic.tactic_problem(tactic);
        return find_union_link_supporting_mapped_problem(disjunction_problem, disjunct_index, must_be_cleanP);
    }

    public static SubLObject find_union_link_supporting_mapped_problem(final SubLObject supported_problem, final SubLObject disjunct_index, final SubLObject must_be_cleanP) {
        final SubLObject supported_query = inference_datastructures_problem.problem_query(supported_problem);
        final SubLObject disjunct_clause = nth(disjunct_index, supported_query);
        return find_disjunct_problem_from_contextualized_clause(supported_problem, disjunct_clause, must_be_cleanP);
    }

    public static SubLObject find_disjunct_problem_from_contextualized_clause(final SubLObject disjunction_problem, final SubLObject disjunct_clause, final SubLObject must_be_cleanP) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject canonical_query = inference_czer.canonicalize_problem_query(list(disjunct_clause));
        final SubLObject canonical_variable_map = thread.secondMultipleValue();
        thread.resetMultipleValues();
        final SubLObject canonical_disjunct_clause = inference_datastructures_problem.problem_query_sole_clause(canonical_query);
        final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(disjunction_problem);
        SubLObject basis_object;
        SubLObject state;
        SubLObject union_link;
        SubLObject candidate_disjunct_problem;
        SubLObject candidate_query;
        SubLObject candidate_clause;
        for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); NIL == set_contents.do_set_contents_doneP(basis_object, state); state = set_contents.do_set_contents_update_state(state)) {
            union_link = set_contents.do_set_contents_next(basis_object, state);
            if ((NIL != set_contents.do_set_contents_element_validP(state, union_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(union_link, $UNION))) {
                candidate_disjunct_problem = union_link_supporting_problem(union_link);
                candidate_query = inference_datastructures_problem.problem_query(candidate_disjunct_problem);
                candidate_clause = inference_datastructures_problem.problem_query_sole_clause(candidate_query);
                if (candidate_clause.equal(canonical_disjunct_clause) && ((NIL == must_be_cleanP) || (NIL != inference_worker.problem_cleanP(candidate_disjunct_problem)))) {
                    return inference_datastructures_problem_link.new_mapped_problem(candidate_disjunct_problem, canonical_variable_map);
                }
            }
        }
        return NIL;
    }

    public static final SubLObject find_or_create_union_tactic_disjunct_mapped_problem_alt(SubLObject tactic) {
        {
            SubLObject disjunct_index = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.union_tactic_disjunct_index(tactic);
            SubLObject disjunction_problem = inference_datastructures_tactic.tactic_problem(tactic);
            return com.cyc.cycjava.cycl.inference.harness.inference_worker_union.find_or_create_union_link_supporting_mapped_problem(disjunction_problem, disjunct_index);
        }
    }

    public static SubLObject find_or_create_union_tactic_disjunct_mapped_problem(final SubLObject tactic) {
        final SubLObject disjunct_index = union_tactic_disjunct_index(tactic);
        final SubLObject disjunction_problem = inference_datastructures_tactic.tactic_problem(tactic);
        return find_or_create_union_link_supporting_mapped_problem(disjunction_problem, disjunct_index);
    }

    public static final SubLObject find_or_create_union_link_supporting_mapped_problem_alt(SubLObject supported_problem, SubLObject disjunct_index) {
        {
            SubLObject store = inference_datastructures_problem.problem_store(supported_problem);
            SubLObject supported_query = inference_datastructures_problem.problem_query(supported_problem);
            SubLObject disjunct_clause = nth(disjunct_index, supported_query);
            return inference_worker.find_or_create_problem_from_contextualized_clause(store, disjunct_clause);
        }
    }

    public static SubLObject find_or_create_union_link_supporting_mapped_problem(final SubLObject supported_problem, final SubLObject disjunct_index) {
        final SubLObject store = inference_datastructures_problem.problem_store(supported_problem);
        final SubLObject supported_query = inference_datastructures_problem.problem_query(supported_problem);
        final SubLObject disjunct_clause = nth(disjunct_index, supported_query);
        final SubLObject disjunct_vars = cycl_utilities.expression_gather(disjunct_clause, $sym4$HL_VAR_, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        final SubLObject disjunct_free_hl_vars = (NIL != inference_datastructures_problem_store.problem_store_allows_problem_hl_free_vars_optimizationP(store)) ? variables.sort_hl_variable_list_memoized(intersection(disjunct_vars, inference_datastructures_problem.problem_free_hl_vars(supported_problem), UNPROVIDED, UNPROVIDED)) : NIL;
        return inference_worker.find_or_create_problem_from_contextualized_clause(store, disjunct_clause, disjunct_free_hl_vars);
    }

    public static final SubLObject determine_new_union_tactics_alt(SubLObject problem, SubLObject disjunction_clauses) {
        {
            SubLObject list_var = NIL;
            SubLObject disjunct_clause = NIL;
            SubLObject disjunct_index = NIL;
            for (list_var = disjunction_clauses, disjunct_clause = list_var.first(), disjunct_index = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , disjunct_clause = list_var.first() , disjunct_index = add(ONE_INTEGER, disjunct_index)) {
                {
                    SubLObject disjunct_mapped_problem = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.find_or_create_union_link_supporting_mapped_problem(problem, disjunct_index);
                    com.cyc.cycjava.cycl.inference.harness.inference_worker_union.maybe_new_union_link(problem, disjunct_mapped_problem);
                }
                com.cyc.cycjava.cycl.inference.harness.inference_worker_union.new_union_tactic(problem, disjunct_index);
            }
        }
        return problem;
    }

    public static SubLObject determine_new_union_tactics(final SubLObject problem, final SubLObject disjunction_clauses) {
        SubLObject list_var = NIL;
        SubLObject disjunct_clause = NIL;
        SubLObject disjunct_index = NIL;
        list_var = disjunction_clauses;
        disjunct_clause = list_var.first();
        for (disjunct_index = ZERO_INTEGER; NIL != list_var; list_var = list_var.rest() , disjunct_clause = list_var.first() , disjunct_index = add(ONE_INTEGER, disjunct_index)) {
            final SubLObject disjunct_mapped_problem = find_or_create_union_link_supporting_mapped_problem(problem, disjunct_index);
            maybe_new_union_link(problem, disjunct_mapped_problem);
            new_union_tactic(problem, disjunct_index);
        }
        return problem;
    }

    public static final SubLObject compute_strategic_properties_of_union_tactic_alt(SubLObject tactic, SubLObject disjunct_index, SubLObject strategy) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
                if (NIL == preference_modules.preference_level_p(inference_datastructures_tactic.tactic_preference_level(tactic))) {
                    thread.resetMultipleValues();
                    {
                        SubLObject preference_level = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.compute_union_tactic_preference_level(problem, disjunct_index, $TACTICAL);
                        SubLObject justification = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        inference_datastructures_tactic.set_tactic_preference_level(tactic, preference_level, justification);
                    }
                }
                thread.resetMultipleValues();
                {
                    SubLObject strategic_preference_level = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.compute_union_tactic_preference_level(problem, disjunct_index, strategy);
                    SubLObject strategic_justification = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    inference_datastructures_strategy.set_tactic_strategic_preference_level(tactic, strategy, strategic_preference_level, strategic_justification);
                }
                {
                    SubLObject productivity = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.compute_union_tactic_productivity(problem, disjunct_index, strategy);
                    inference_datastructures_strategy.set_tactic_strategic_productivity(tactic, strategy, productivity);
                }
            }
            return tactic;
        }
    }

    public static SubLObject compute_strategic_properties_of_union_tactic(final SubLObject tactic, final SubLObject disjunct_index, final SubLObject strategy) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject problem = inference_datastructures_tactic.tactic_problem(tactic);
        if (NIL == preference_modules.preference_level_p(inference_datastructures_tactic.tactic_preference_level(tactic))) {
            thread.resetMultipleValues();
            final SubLObject preference_level = compute_union_tactic_preference_level(problem, disjunct_index, $TACTICAL);
            final SubLObject justification = thread.secondMultipleValue();
            thread.resetMultipleValues();
            inference_datastructures_tactic.set_tactic_preference_level(tactic, preference_level, justification);
        }
        thread.resetMultipleValues();
        final SubLObject strategic_preference_level = compute_union_tactic_preference_level(problem, disjunct_index, strategy);
        final SubLObject strategic_justification = thread.secondMultipleValue();
        thread.resetMultipleValues();
        inference_datastructures_strategy.set_tactic_strategic_preference_level(tactic, strategy, strategic_preference_level, strategic_justification);
        final SubLObject productivity = compute_union_tactic_productivity(problem, disjunct_index, strategy);
        inference_datastructures_strategy.set_tactic_strategic_productivity(tactic, strategy, productivity);
        return tactic;
    }

    public static final SubLObject compute_union_tactic_productivity_alt(SubLObject supported_problem, SubLObject disjunct_index, SubLObject strategy) {
        {
            SubLObject disjunct_mapped_problem = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.find_or_create_union_link_supporting_mapped_problem(supported_problem, disjunct_index);
            SubLObject disjunct_problem = inference_datastructures_problem_link.mapped_problem_problem(disjunct_mapped_problem);
            SubLObject disjunct_productivity = inference_lookahead_productivity.memoized_problem_max_removal_productivity(disjunct_problem, strategy);
            return disjunct_productivity;
        }
    }

    public static SubLObject compute_union_tactic_productivity(final SubLObject supported_problem, final SubLObject disjunct_index, final SubLObject strategy) {
        final SubLObject disjunct_mapped_problem = find_union_link_supporting_mapped_problem(supported_problem, disjunct_index, NIL);
        final SubLObject disjunct_problem = inference_datastructures_problem_link.mapped_problem_problem(disjunct_mapped_problem);
        final SubLObject disjunct_productivity = inference_lookahead_productivity.memoized_problem_max_removal_productivity(disjunct_problem, strategy);
        return disjunct_productivity;
    }

    public static final SubLObject compute_union_tactic_preference_level_alt(SubLObject supported_problem, SubLObject disjunct_index, SubLObject strategic_context) {
        return values($union_tactic_preference_level$.getGlobalValue(), $union_tactic_preference_level_justification$.getGlobalValue());
    }

    public static SubLObject compute_union_tactic_preference_level(final SubLObject supported_problem, final SubLObject disjunct_index, final SubLObject strategic_context) {
        return values($union_tactic_preference_level$.getGlobalValue(), $union_tactic_preference_level_justification$.getGlobalValue());
    }

    public static final SubLObject union_tactic_lookahead_problem_alt(SubLObject union_tactic) {
        {
            SubLObject supporting_mapped_problem = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.find_or_create_union_tactic_disjunct_mapped_problem(union_tactic);
            return inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem);
        }
    }

    public static SubLObject union_tactic_lookahead_problem(final SubLObject union_tactic) {
        final SubLObject supporting_mapped_problem = find_union_tactic_disjunct_mapped_problem(union_tactic, NIL);
        return inference_datastructures_problem_link.mapped_problem_problem(supporting_mapped_problem);
    }

    public static final SubLObject execute_union_tactic_alt(SubLObject tactic) {
        {
            SubLObject union_link = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.union_tactic_link(tactic);
            inference_worker.problem_link_open_and_repropagate_sole_supporting_mapped_problem(union_link);
        }
        return tactic;
    }

    public static SubLObject execute_union_tactic(final SubLObject tactic) {
        possibly_clean_union_tactic(tactic);
        final SubLObject union_link = union_tactic_link(tactic);
        inference_worker.problem_link_open_and_repropagate_sole_supporting_mapped_problem(union_link);
        return tactic;
    }

    public static SubLObject possibly_clean_union_tactic(final SubLObject tactic) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != union_tactic_p(tactic) : "! inference_worker_union.union_tactic_p(tactic) " + ("inference_worker_union.union_tactic_p(tactic) " + "CommonSymbols.NIL != inference_worker_union.union_tactic_p(tactic) ") + tactic;
        final SubLObject clean_disjunct_mapped_problem = find_union_tactic_disjunct_mapped_problem(tactic, T);
        if (((NIL == clean_disjunct_mapped_problem) && (NIL == inference_datastructures_tactic.tactic_discardedP(tactic))) && (NIL != inference_datastructures_problem_store.$problem_store_modification_permittedP$.getDynamicValue(thread))) {
            final SubLObject disjunction_problem = inference_datastructures_tactic.tactic_problem(tactic);
            SubLObject dirty_union_link = NIL;
            final SubLObject dirty_disjunct_mapped_problem = find_union_tactic_disjunct_mapped_problem(tactic, NIL);
            final SubLObject set_contents_var = inference_datastructures_problem.problem_argument_links(disjunction_problem);
            SubLObject basis_object;
            SubLObject state;
            SubLObject union_link;
            SubLObject candidate_mapped_problem;
            for (basis_object = set_contents.do_set_contents_basis_object(set_contents_var), state = NIL, state = set_contents.do_set_contents_initial_state(basis_object, set_contents_var); (NIL == dirty_union_link) && (NIL == set_contents.do_set_contents_doneP(basis_object, state)); state = set_contents.do_set_contents_update_state(state)) {
                union_link = set_contents.do_set_contents_next(basis_object, state);
                if ((NIL != set_contents.do_set_contents_element_validP(state, union_link)) && (NIL != inference_datastructures_problem_link.problem_link_has_typeP(union_link, $UNION))) {
                    candidate_mapped_problem = inference_datastructures_problem_link.problem_link_sole_supporting_mapped_problem(union_link);
                    if (NIL != inference_datastructures_problem_link.mapped_problem_equal(candidate_mapped_problem, dirty_disjunct_mapped_problem)) {
                        dirty_union_link = union_link;
                    }
                }
            }
            if (NIL != dirty_union_link) {
                inference_datastructures_problem_link.destroy_problem_link(dirty_union_link);
            }
            final SubLObject clean_disjunct_mapped_problem_$3 = find_or_create_union_tactic_disjunct_mapped_problem(tactic);
            final SubLObject clean_union_link = new_union_link(disjunction_problem, clean_disjunct_mapped_problem_$3);
            union_link_tactic(clean_union_link);
            return clean_union_link;
        }
        return NIL;
    }

    static private final SubLString $str_alt1$couldn_t_find_the_union_tactic_fo = makeString("couldn't find the union tactic for ~s");

    static private final SubLString $str_alt3$Could_not_find_the_link_for__a = makeString("Could not find the link for ~a");

    /**
     *
     *
     * @return 0 proof-p
     * @return 1 whether the returned proof was newly created
     */
    @LispMethod(comment = "@return 0 proof-p\r\n@return 1 whether the returned proof was newly created")
    public static final SubLObject new_union_proof_alt(SubLObject union_link, SubLObject subproof, SubLObject subproof_bindings) {
        {
            SubLObject subproofs = list(subproof);
            subproof_bindings = inference_worker.canonicalize_proof_bindings(subproof_bindings);
            return inference_worker.propose_new_proof_with_bindings(union_link, subproof_bindings, subproofs);
        }
    }

    /**
     *
     *
     * @return 0 proof-p
     * @return 1 whether the returned proof was newly created
     */
    @LispMethod(comment = "@return 0 proof-p\r\n@return 1 whether the returned proof was newly created")
    public static SubLObject new_union_proof(final SubLObject union_link, final SubLObject subproof, SubLObject subproof_bindings) {
        final SubLObject subproofs = list(subproof);
        subproof_bindings = inference_worker.canonicalize_proof_bindings(subproof_bindings);
        return inference_worker.propose_new_proof_with_bindings(union_link, subproof_bindings, subproofs);
    }

    public static final SubLObject bubble_up_proof_to_union_link_alt(SubLObject disjunct_proof, SubLObject disjunct_variable_map, SubLObject union_link) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject disjunct_bindings = inference_datastructures_proof.proof_bindings(disjunct_proof);
                SubLObject disjunction_bindings = bindings.transfer_variable_map_to_bindings(disjunct_variable_map, disjunct_bindings);
                thread.resetMultipleValues();
                {
                    SubLObject disjunction_proof = com.cyc.cycjava.cycl.inference.harness.inference_worker_union.new_union_proof(union_link, disjunct_proof, disjunction_bindings);
                    SubLObject newP = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL != newP) {
                        inference_worker.bubble_up_proof(disjunction_proof);
                    } else {
                        inference_worker.possibly_note_proof_processed(disjunct_proof);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject bubble_up_proof_to_union_link(final SubLObject disjunct_proof, final SubLObject disjunct_variable_map, final SubLObject union_link) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject disjunct_bindings = inference_datastructures_proof.proof_bindings(disjunct_proof);
        final SubLObject disjunction_bindings = bindings.transfer_variable_map_to_bindings(disjunct_variable_map, disjunct_bindings);
        thread.resetMultipleValues();
        final SubLObject disjunction_proof = new_union_proof(union_link, disjunct_proof, disjunction_bindings);
        final SubLObject newP = thread.secondMultipleValue();
        thread.resetMultipleValues();
        if (NIL != newP) {
            inference_worker.bubble_up_proof(disjunction_proof);
        } else {
            inference_worker.possibly_note_proof_processed(disjunct_proof);
        }
        return NIL;
    }

    public static final SubLObject disjunctive_assumption_link_p_alt(SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_problem_link.problem_link_p(v_object)) && ($DISJUNCTIVE_ASSUMPTION == inference_datastructures_problem_link.problem_link_type(v_object)));
    }

    public static SubLObject disjunctive_assumption_link_p(final SubLObject v_object) {
        return makeBoolean((NIL != inference_datastructures_problem_link.problem_link_p(v_object)) && ($DISJUNCTIVE_ASSUMPTION == inference_datastructures_problem_link.problem_link_type(v_object)));
    }

    public static final SubLObject disjunctive_assumption_tactic_p_alt(SubLObject v_object) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(v_object)) && ($disjunction_assumption_module$.getDynamicValue(thread) == inference_datastructures_tactic.tactic_hl_module(v_object)));
        }
    }

    public static SubLObject disjunctive_assumption_tactic_p(final SubLObject v_object) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return makeBoolean((NIL != inference_datastructures_tactic.tactic_p(v_object)) && $disjunction_assumption_module$.getDynamicValue(thread).eql(inference_datastructures_tactic.tactic_hl_module(v_object)));
    }

    public static SubLObject declare_inference_worker_union_file() {
        declareFunction("union_link_p", "UNION-LINK-P", 1, 0, false);
        declareFunction("maybe_new_union_link", "MAYBE-NEW-UNION-LINK", 2, 0, false);
        declareFunction("new_union_link", "NEW-UNION-LINK", 2, 0, false);
        declareFunction("destroy_union_link", "DESTROY-UNION-LINK", 1, 0, false);
        declareFunction("union_link_supporting_problem", "UNION-LINK-SUPPORTING-PROBLEM", 1, 0, false);
        declareFunction("union_link_tactic", "UNION-LINK-TACTIC", 1, 0, false);
        declareFunction("union_link_tactic_int", "UNION-LINK-TACTIC-INT", 1, 0, false);
        declareFunction("union_tactic_p", "UNION-TACTIC-P", 1, 0, false);
        declareFunction("new_union_tactic", "NEW-UNION-TACTIC", 2, 0, false);
        declareFunction("union_tactic_disjunct_index", "UNION-TACTIC-DISJUNCT-INDEX", 1, 0, false);
        declareFunction("union_tactic_link", "UNION-TACTIC-LINK", 1, 0, false);
        declareFunction("union_tactic_link_int", "UNION-TACTIC-LINK-INT", 1, 0, false);
        declareFunction("find_union_tactic_disjunct_mapped_problem", "FIND-UNION-TACTIC-DISJUNCT-MAPPED-PROBLEM", 2, 0, false);
        declareFunction("find_union_link_supporting_mapped_problem", "FIND-UNION-LINK-SUPPORTING-MAPPED-PROBLEM", 3, 0, false);
        declareFunction("find_disjunct_problem_from_contextualized_clause", "FIND-DISJUNCT-PROBLEM-FROM-CONTEXTUALIZED-CLAUSE", 3, 0, false);
        declareFunction("find_or_create_union_tactic_disjunct_mapped_problem", "FIND-OR-CREATE-UNION-TACTIC-DISJUNCT-MAPPED-PROBLEM", 1, 0, false);
        declareFunction("find_or_create_union_link_supporting_mapped_problem", "FIND-OR-CREATE-UNION-LINK-SUPPORTING-MAPPED-PROBLEM", 2, 0, false);
        declareFunction("determine_new_union_tactics", "DETERMINE-NEW-UNION-TACTICS", 2, 0, false);
        declareFunction("compute_strategic_properties_of_union_tactic", "COMPUTE-STRATEGIC-PROPERTIES-OF-UNION-TACTIC", 3, 0, false);
        declareFunction("compute_union_tactic_productivity", "COMPUTE-UNION-TACTIC-PRODUCTIVITY", 3, 0, false);
        declareFunction("compute_union_tactic_preference_level", "COMPUTE-UNION-TACTIC-PREFERENCE-LEVEL", 3, 0, false);
        declareFunction("union_tactic_lookahead_problem", "UNION-TACTIC-LOOKAHEAD-PROBLEM", 1, 0, false);
        declareFunction("execute_union_tactic", "EXECUTE-UNION-TACTIC", 1, 0, false);
        declareFunction("possibly_clean_union_tactic", "POSSIBLY-CLEAN-UNION-TACTIC", 1, 0, false);
        declareFunction("new_union_proof", "NEW-UNION-PROOF", 3, 0, false);
        declareFunction("bubble_up_proof_to_union_link", "BUBBLE-UP-PROOF-TO-UNION-LINK", 3, 0, false);
        declareFunction("disjunctive_assumption_link_p", "DISJUNCTIVE-ASSUMPTION-LINK-P", 1, 0, false);
        declareFunction("disjunctive_assumption_tactic_p", "DISJUNCTIVE-ASSUMPTION-TACTIC-P", 1, 0, false);
        return NIL;
    }

    public static SubLObject init_inference_worker_union_file() {
        defparameter("*UNION-MODULE*", inference_modules.inference_structural_module($UNION, UNPROVIDED));
        deflexical("*UNION-TACTIC-PREFERENCE-LEVEL*", $PREFERRED);
        deflexical("*UNION-TACTIC-PREFERENCE-LEVEL-JUSTIFICATION*", $PREFERRED);
        defparameter("*DISJUNCTION-ASSUMPTION-MODULE*", inference_modules.inference_structural_module($DISJUNCTIVE_ASSUMPTION, UNPROVIDED));
        return NIL;
    }

    public static SubLObject setup_inference_worker_union_file() {
        return NIL;
    }

    @Override
    public void declareFunctions() {
        declare_inference_worker_union_file();
    }

    @Override
    public void initializeVariables() {
        init_inference_worker_union_file();
    }

    @Override
    public void runTopLevelForms() {
        setup_inference_worker_union_file();
    }

    static {
    }
}

/**
 * Total time: 96 ms
 */
