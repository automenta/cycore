/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 */
package com.cyc.cycjava.cycl.sksi.sksi_infrastructure;


import static com.cyc.cycjava.cycl.access_macros.register_external_symbol;
import static com.cyc.cycjava.cycl.arguments.make_hl_support;
import static com.cyc.cycjava.cycl.constant_handles.reader_make_constant_shell;
import static com.cyc.cycjava.cycl.cyc_kernel.closed_query_success_token;
import static com.cyc.cycjava.cycl.cyc_testing.generic_testing.define_test_case_table_int;
import static com.cyc.cycjava.cycl.cycl_utilities.formula_operator;
import static com.cyc.cycjava.cycl.dictionary.clear_dictionary;
import static com.cyc.cycjava.cycl.dictionary.dictionary_contents;
import static com.cyc.cycjava.cycl.dictionary.dictionary_enter;
import static com.cyc.cycjava.cycl.dictionary.dictionary_lookup_without_values;
import static com.cyc.cycjava.cycl.dictionary.dictionary_p;
import static com.cyc.cycjava.cycl.dictionary.dictionary_remove;
import static com.cyc.cycjava.cycl.dictionary.new_dictionary;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_doneP;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_finalize;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_key_value;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_next;
import static com.cyc.cycjava.cycl.dictionary_contents.do_dictionary_contents_state;
import static com.cyc.cycjava.cycl.dictionary_utilities.copy_dictionary;
import static com.cyc.cycjava.cycl.el_utilities.formula_arityE;
import static com.cyc.cycjava.cycl.el_utilities.make_el_formula;
import static com.cyc.cycjava.cycl.el_utilities.possibly_naut_p;
import static com.cyc.cycjava.cycl.file_hash_table.create_file_hash_table;
import static com.cyc.cycjava.cycl.file_hash_table.file_hash_table_p;
import static com.cyc.cycjava.cycl.file_hash_table.finalize_file_hash_table;
import static com.cyc.cycjava.cycl.file_hash_table.get_file_hash_table;
import static com.cyc.cycjava.cycl.file_hash_table.get_file_hash_table_any;
import static com.cyc.cycjava.cycl.file_hash_table.open_file_hash_table;
import static com.cyc.cycjava.cycl.file_hash_table.put_file_hash_table;
import static com.cyc.cycjava.cycl.file_hash_table.remove_file_hash_table;
import static com.cyc.cycjava.cycl.file_utilities.slurp_file;
import static com.cyc.cycjava.cycl.format_nil.format_nil_a_no_copy;
import static com.cyc.cycjava.cycl.format_nil.format_nil_d_no_copy;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.inference_cumulative_time_so_far;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.inference_max_time;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.inference_time_so_far;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.possibly_add_inference_sksi_query_start_time;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.possibly_add_inference_sparql_query_profile;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.possibly_increment_inference_sksi_query_total_time;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.possibly_signal_sksi_query_end;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference.possibly_signal_sksi_query_start;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_query.single_literal_problem_query_atomic_sentence;
import static com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_query.single_literal_problem_query_p;
import static com.cyc.cycjava.cycl.inference.harness.inference_macros.current_controlling_inference;
import static com.cyc.cycjava.cycl.inference.harness.inference_macros.current_controlling_inferences;
import static com.cyc.cycjava.cycl.inference.harness.inference_worker.currently_active_problem_query;
import static com.cyc.cycjava.cycl.inference.harness.inference_worker.currently_executing_hl_module;
import static com.cyc.cycjava.cycl.inference.harness.inference_worker.currently_executing_tactic;
import static com.cyc.cycjava.cycl.inference.kbq_query_run.$kbq_progress_stream$;
import static com.cyc.cycjava.cycl.iteration.iteration_done;
import static com.cyc.cycjava.cycl.iteration.iteration_next;
import static com.cyc.cycjava.cycl.iteration.new_iterator;
import static com.cyc.cycjava.cycl.iteration.new_list_iterator;
import static com.cyc.cycjava.cycl.iteration.new_null_iterator;
import static com.cyc.cycjava.cycl.list_utilities.alist_enter;
import static com.cyc.cycjava.cycl.list_utilities.alist_p;
import static com.cyc.cycjava.cycl.list_utilities.last_one;
import static com.cyc.cycjava.cycl.list_utilities.member_eqP;
import static com.cyc.cycjava.cycl.list_utilities.nreplace_nested_nth;
import static com.cyc.cycjava.cycl.list_utilities.sequence_elements;
import static com.cyc.cycjava.cycl.list_utilities.singletonP;
import static com.cyc.cycjava.cycl.list_utilities.sublisp_boolean;
import static com.cyc.cycjava.cycl.list_utilities.tree_find;
import static com.cyc.cycjava.cycl.list_utilities.tree_gather;
import static com.cyc.cycjava.cycl.memoization_state.$memoization_state$;
import static com.cyc.cycjava.cycl.memoization_state.$memoized_item_not_found$;
import static com.cyc.cycjava.cycl.memoization_state.caching_results;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_clear;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_enter_multi_key_n;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_lookup;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_put;
import static com.cyc.cycjava.cycl.memoization_state.caching_state_remove_function_results_with_args;
import static com.cyc.cycjava.cycl.memoization_state.create_caching_state;
import static com.cyc.cycjava.cycl.memoization_state.create_global_caching_state_for_name;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_lock;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_lookup;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_original_process;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_possibly_clear_original_process;
import static com.cyc.cycjava.cycl.memoization_state.memoization_state_put;
import static com.cyc.cycjava.cycl.memoization_state.note_globally_cached_function;
import static com.cyc.cycjava.cycl.memoization_state.note_memoized_function;
import static com.cyc.cycjava.cycl.memoization_state.sxhash_calc_2;
import static com.cyc.cycjava.cycl.memoization_state.sxhash_calc_4;
import static com.cyc.cycjava.cycl.memoization_state.sxhash_calc_7;
import static com.cyc.cycjava.cycl.mt_relevance_macros.$mt$;
import static com.cyc.cycjava.cycl.mt_relevance_macros.$relevant_mt_function$;
import static com.cyc.cycjava.cycl.mt_relevance_macros.$relevant_mts$;
import static com.cyc.cycjava.cycl.mt_relevance_macros.possibly_in_mt_determine_function;
import static com.cyc.cycjava.cycl.mt_relevance_macros.possibly_in_mt_determine_mt;
import static com.cyc.cycjava.cycl.mt_relevance_macros.update_inference_mt_relevance_function;
import static com.cyc.cycjava.cycl.mt_relevance_macros.update_inference_mt_relevance_mt;
import static com.cyc.cycjava.cycl.mt_relevance_macros.update_inference_mt_relevance_mt_list;
import static com.cyc.cycjava.cycl.mt_relevance_macros.with_inference_mt_relevance_validate;
import static com.cyc.cycjava.cycl.number_utilities.positive_number_p;
import static com.cyc.cycjava.cycl.numeric_date_utilities.elapsed_seconds_between_internal_real_times;
import static com.cyc.cycjava.cycl.numeric_date_utilities.universal_time_p;
import static com.cyc.cycjava.cycl.pattern_match.pattern_matches_tree;
import static com.cyc.cycjava.cycl.queues.create_queue;
import static com.cyc.cycjava.cycl.queues.dequeue;
import static com.cyc.cycjava.cycl.queues.enqueue;
import static com.cyc.cycjava.cycl.queues.queue_empty_p;
import static com.cyc.cycjava.cycl.queues.queue_p;
import static com.cyc.cycjava.cycl.queues.queue_size;
import static com.cyc.cycjava.cycl.relation_evaluation.cyc_evaluate;
import static com.cyc.cycjava.cycl.sdbc.$sql_port$;
import static com.cyc.cycjava.cycl.sdbc.new_sql_connection;
import static com.cyc.cycjava.cycl.sdbc.sdbc_error_message;
import static com.cyc.cycjava.cycl.sdbc.sdbc_error_p;
import static com.cyc.cycjava.cycl.sdbc.sdbc_error_warn;
import static com.cyc.cycjava.cycl.sdbc.sql_connection_p;
import static com.cyc.cycjava.cycl.sdbc.sql_open_connection_p;
import static com.cyc.cycjava.cycl.sdbc.sql_open_result_set_p;
import static com.cyc.cycjava.cycl.sdbc.sql_open_statement_p;
import static com.cyc.cycjava.cycl.sdbc.sql_result_set_p;
import static com.cyc.cycjava.cycl.sdbc.sqlc_close;
import static com.cyc.cycjava.cycl.sdbc.sqlc_commit;
import static com.cyc.cycjava.cycl.sdbc.sqlc_create_statement;
import static com.cyc.cycjava.cycl.sdbc.sqlc_get_auto_commit;
import static com.cyc.cycjava.cycl.sdbc.sqlc_set_auto_commit;
import static com.cyc.cycjava.cycl.sdbc.sqlc_set_error_handling;
import static com.cyc.cycjava.cycl.sdbc.sqlrs_close;
import static com.cyc.cycjava.cycl.sdbc.sqlrs_get_object;
import static com.cyc.cycjava.cycl.sdbc.sqlrs_next;
import static com.cyc.cycjava.cycl.sdbc.sqls_add_batch;
import static com.cyc.cycjava.cycl.sdbc.sqls_cancel;
import static com.cyc.cycjava.cycl.sdbc.sqls_close;
import static com.cyc.cycjava.cycl.sdbc.sqls_execute_batch;
import static com.cyc.cycjava.cycl.sdbc.sqls_execute_query;
import static com.cyc.cycjava.cycl.sdbc.sqls_execute_update;
import static com.cyc.cycjava.cycl.sdbc.sqls_get_connection;
import static com.cyc.cycjava.cycl.sdbc.sqls_handle_commit_error;
import static com.cyc.cycjava.cycl.sdbc.sqls_handle_rollback;
import static com.cyc.cycjava.cycl.sdbc.sqls_handle_transaction_errors;
import static com.cyc.cycjava.cycl.sdbc.sqls_sql;
import static com.cyc.cycjava.cycl.set.clear_set;
import static com.cyc.cycjava.cycl.set.new_set;
import static com.cyc.cycjava.cycl.set.non_empty_set_p;
import static com.cyc.cycjava.cycl.set.set_add;
import static com.cyc.cycjava.cycl.set.set_memberP;
import static com.cyc.cycjava.cycl.set.set_p;
import static com.cyc.cycjava.cycl.set.set_remove;
import static com.cyc.cycjava.cycl.sksi.query_sks.sksi_hl_support_utilities.note_sksi_support;
import static com.cyc.cycjava.cycl.sksi.query_sks.sksi_query_utilities.sksi_suspend_cost_recording_for_current_iterator;
import static com.cyc.cycjava.cycl.sksi.sksi_testing.sksi_cache.generate_language_spoken_boolean_from_csql;
import static com.cyc.cycjava.cycl.sksi.sksi_testing.sksi_cache.generate_language_spoken_iterator_from_csql;
import static com.cyc.cycjava.cycl.string_utilities.empty_string_p;
import static com.cyc.cycjava.cycl.string_utilities.first_char;
import static com.cyc.cycjava.cycl.string_utilities.left_trim_whitespace;
import static com.cyc.cycjava.cycl.string_utilities.non_empty_string_p;
import static com.cyc.cycjava.cycl.string_utilities.reduce_whitespace;
import static com.cyc.cycjava.cycl.string_utilities.split_string;
import static com.cyc.cycjava.cycl.string_utilities.starts_with_by_test;
import static com.cyc.cycjava.cycl.string_utilities.string_to_integer;
import static com.cyc.cycjava.cycl.string_utilities.string_tokenize;
import static com.cyc.cycjava.cycl.string_utilities.strip_first;
import static com.cyc.cycjava.cycl.string_utilities.substring;
import static com.cyc.cycjava.cycl.string_utilities.trim_whitespace;
import static com.cyc.cycjava.cycl.subl_macro_promotions.$catch_error_message_target$;
import static com.cyc.cycjava.cycl.subl_macro_promotions.declare_defglobal;
import static com.cyc.cycjava.cycl.subl_promotions.elapsed_universal_time;
import static com.cyc.cycjava.cycl.subl_promotions.memberP;
import static com.cyc.cycjava.cycl.subl_promotions.open_tcp_stream_with_timeout;
import static com.cyc.cycjava.cycl.variables.get_variable;
import static com.cyc.cycjava.cycl.web_utilities.html_assocation_list_to_url_parameters;
import static com.cyc.cycjava.cycl.web_utilities.html_url_encode;
import static com.cyc.cycjava.cycl.web_utilities.send_http_request;
import static com.cyc.cycjava.cycl.web_utilities.test_http_request;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_dollar;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_question;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_semicolon;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.CHAR_space;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Characters.code_char;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.append;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.cons;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.listS;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.ConsesLow.set_nth;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.bind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.currentBinding;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Dynamic.rebind;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.eq;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Equality.identity;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Eval.eval;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Functions.funcall;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.make_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.release_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Locks.seize_lock;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.add;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.ceiling;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.min;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.numG;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Numbers.subtract;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.PrintLow.format;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.cconcatenate;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.delete;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.length;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.nreverse;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.position;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Sequences.search;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.def_csetf;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.makeStructDeclNative;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Structures.register_method;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.boundp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.gensym;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Symbols.symbol_function;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.$is_thread_performing_cleanupP$;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Threads.sleep;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.get_internal_real_time;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Time.get_universal_time;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.stringp;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Types.sublisp_null;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.arg2;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.getValuesAsVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.multiple_value_list;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.resetMultipleValues;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.restoreValuesFromVector;
import static com.cyc.tool.subl.jrtl.nativeCode.subLisp.Values.values;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeBoolean;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeDouble;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeInteger;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeKeyword;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeString;
import static com.cyc.tool.subl.jrtl.nativeCode.type.core.SubLObjectFactory.makeSymbol;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.cdestructuring_bind_error;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_consp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.cdestructuring_bind.destructuring_bind_must_listp;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cadr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.cddr;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.list_length;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.member;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.second;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.conses_high.subst;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.$print_object_method_table$;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.print_high.princ;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.reader.bq_cons;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.close;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.force_output;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.get_output_stream_string;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.make_private_string_output_stream;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.read_char;
import static com.cyc.tool.subl.jrtl.translatedCode.sublisp.streams_high.unread_char;
import static com.cyc.tool.subl.util.SubLFiles.declareFunction;
import static com.cyc.tool.subl.util.SubLFiles.declareMacro;
import static com.cyc.tool.subl.util.SubLFiles.defconstant;
import static com.cyc.tool.subl.util.SubLFiles.deflexical;
import static com.cyc.tool.subl.util.SubLFiles.defparameter;
import static com.cyc.tool.subl.util.SubLFiles.defvar;

import org.armedbear.lisp.Lisp;

import com.cyc.cycjava.cycl.V12;
import com.cyc.cycjava.cycl.arguments;
import com.cyc.cycjava.cycl.cyc_kernel;
import com.cyc.cycjava.cycl.cycl_utilities;
import com.cyc.cycjava.cycl.dictionary;
import com.cyc.cycjava.cycl.dictionary_contents;
import com.cyc.cycjava.cycl.dictionary_utilities;
import com.cyc.cycjava.cycl.file_hash_table;
import com.cyc.cycjava.cycl.file_utilities;
import com.cyc.cycjava.cycl.format_nil;
import com.cyc.cycjava.cycl.iteration;
import com.cyc.cycjava.cycl.list_utilities;
import com.cyc.cycjava.cycl.memoization_state;
import com.cyc.cycjava.cycl.mt_relevance_macros;
import com.cyc.cycjava.cycl.number_utilities;
import com.cyc.cycjava.cycl.numeric_date_utilities;
import com.cyc.cycjava.cycl.pattern_match;
import com.cyc.cycjava.cycl.queues;
import com.cyc.cycjava.cycl.relation_evaluation;
import com.cyc.cycjava.cycl.sdbc;
import com.cyc.cycjava.cycl.set;
import com.cyc.cycjava.cycl.string_utilities;
import com.cyc.cycjava.cycl.subl_promotions;
import com.cyc.cycjava.cycl.variables;
import com.cyc.cycjava.cycl.web_utilities;
import com.cyc.cycjava.cycl.xml_parsing_utilities;
import com.cyc.cycjava.cycl.inference.kbq_query_run;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_inference;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_query;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_problem_store;
import com.cyc.cycjava.cycl.inference.harness.inference_datastructures_tactic;
import com.cyc.cycjava.cycl.inference.harness.inference_macros;
import com.cyc.cycjava.cycl.inference.harness.inference_modules;
import com.cyc.cycjava.cycl.inference.harness.inference_strategist;
import com.cyc.cycjava.cycl.inference.harness.inference_worker;
import com.cyc.cycjava.cycl.rdf.sparql_utilities;
import com.cyc.cycjava.cycl.sksi.query_sks.sksi_hl_support_utilities;
import com.cyc.cycjava.cycl.sksi.query_sks.sksi_query_utilities;
import com.cyc.cycjava.cycl.sksi.sksi_testing.sksi_cache;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Errors;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Filesys;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Guids;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.StreamsLow;
import com.cyc.tool.subl.jrtl.nativeCode.subLisp.Strings;
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
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLFloat;
import com.cyc.tool.subl.jrtl.nativeCode.type.number.SubLInteger;
import com.cyc.tool.subl.jrtl.nativeCode.type.symbol.SubLSymbol;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.compatibility;
import com.cyc.tool.subl.jrtl.translatedCode.sublisp.visitation;
import com.cyc.tool.subl.util.SubLFile;
import com.cyc.tool.subl.util.SubLFiles;
import com.cyc.tool.subl.util.SubLFiles.LispMethod;
import com.cyc.tool.subl.util.SubLTrampolineFile;
import com.cyc.tool.subl.util.SubLTranslatedFile;


/**
 * Copyright (c) 1995 - 2019 Cycorp, Inc.  All rights reserved.
 * module:      SKSI-SKS-INTERACTION
 * source file: /cyc/top/cycl/sksi/sksi-infrastructure/sksi-sks-interaction.lisp
 * created:     2019/07/03 17:37:54
 */
public final class sksi_sks_interaction extends SubLTranslatedFile implements V12 {
    // Internal Constant Initializer Methods
    @LispMethod(comment = "Internal Constant Initializer Methods")
    private static final SubLObject _constant_180_initializer() {
        return list(new SubLObject[]{ list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:05 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 393\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal>"), makeString("1885"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("1885")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:08 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 313\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), NIL), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:09 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">"), makeString("78.04222106933594"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("78.04222106933594")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:23 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">"), makeString("27.17416572570801"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("27.17416572570801")))), list(list(NIL, list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), NIL), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:32 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 448\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#date\">"), makeString("1998-07-21"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("1998-07-21")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:37 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 418\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Edinburgh"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Edinburgh>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:38 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:38 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:39 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 521\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Brooklyn"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/New_York"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Brooklyn>")), list(makeString("<http://dbpedia.org/resource/New_York>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:39 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 518\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/England"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/London"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/England>")), list(makeString("<http://dbpedia.org/resource/London>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:40 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 741\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Acid_rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Blues-rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Hard_rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Psychedelic_rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Acid_rock>")), list(makeString("<http://dbpedia.org/resource/Blues-rock>")), list(makeString("<http://dbpedia.org/resource/Hard_rock>")), list(makeString("<http://dbpedia.org/resource/Psychedelic_rock>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:41 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:41 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 422\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Truman_Capote"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Truman_Capote>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:43 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:51:08 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 448\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#date\">"), makeString("1923-11-18"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("1923-11-18")))) });
    }

    static private final SubLString $str_alt145$ = makeString("");

    /**
     * When non-nil, we cache the most recent SPARQL queries via an MRU cache.
     * This is useful control variable for profiling the Cyc side of an
     * SKSI SPARQL query.
     */
    // defvar
    @LispMethod(comment = "When non-nil, we cache the most recent SPARQL queries via an MRU cache.\r\nThis is useful control variable for profiling the Cyc side of an\r\nSKSI SPARQL query.\nWhen non-nil, we cache the most recent SPARQL queries via an MRU cache.\nThis is useful control variable for profiling the Cyc side of an\nSKSI SPARQL query.\ndefvar")
    private static final SubLSymbol $sparql_caching_enabledP$ = makeSymbol("*SPARQL-CACHING-ENABLED?*");

    public static final SubLFile me = new sksi_sks_interaction();

 public static final String myName = "com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction";


    // defparameter
    /**
     * Temporary variable that gives the default caching strategy for the results of
     * an SKSI http request.
     */
    @LispMethod(comment = "Temporary variable that gives the default caching strategy for the results of\r\nan SKSI http request.\ndefparameter\nTemporary variable that gives the default caching strategy for the results of\nan SKSI http request.")
    private static final SubLSymbol $sksi_http_request_caching_strategy$ = makeSymbol("*SKSI-HTTP-REQUEST-CACHING-STRATEGY*");

    // defparameter
    /**
     * Temporary variable that gives the value for Keep-Alive in an SKSI http
     * request.
     */
    @LispMethod(comment = "Temporary variable that gives the value for Keep-Alive in an SKSI http\r\nrequest.\ndefparameter\nTemporary variable that gives the value for Keep-Alive in an SKSI http\nrequest.")
    private static final SubLSymbol $sksi_http_request_keep_alive$ = makeSymbol("*SKSI-HTTP-REQUEST-KEEP-ALIVE*");

    // defparameter
    /**
     * Temporary variable that indicates whether or not SKSI http requests should
     * use wide-newlines.
     */
    @LispMethod(comment = "Temporary variable that indicates whether or not SKSI http requests should\r\nuse wide-newlines.\ndefparameter\nTemporary variable that indicates whether or not SKSI http requests should\nuse wide-newlines.")
    private static final SubLSymbol $sksi_http_request_wide_newlines$ = makeSymbol("*SKSI-HTTP-REQUEST-WIDE-NEWLINES*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $sksi_sparql_request_wide_newlines$ = makeSymbol("*SKSI-SPARQL-REQUEST-WIDE-NEWLINES*");

    // defparameter
    /**
     * Temporary variable that determines the timeout to be given to an SKSI http
     * request for opening a TCP connection.
     */
    @LispMethod(comment = "Temporary variable that determines the timeout to be given to an SKSI http\r\nrequest for opening a TCP connection.\ndefparameter\nTemporary variable that determines the timeout to be given to an SKSI http\nrequest for opening a TCP connection.")
    private static final SubLSymbol $sksi_http_request_open_connection_timeout$ = makeSymbol("*SKSI-HTTP-REQUEST-OPEN-CONNECTION-TIMEOUT*");

    // defparameter
    /**
     * Temporary variable that determines the timeout to be given to an SKSI http
     * request for the overall communication plus tokenization of the output page
     * returned.
     */
    @LispMethod(comment = "Temporary variable that determines the timeout to be given to an SKSI http\r\nrequest for the overall communication plus tokenization of the output page\r\nreturned.\ndefparameter\nTemporary variable that determines the timeout to be given to an SKSI http\nrequest for the overall communication plus tokenization of the output page\nreturned.")
    private static final SubLSymbol $sksi_http_request_overall_timeout$ = makeSymbol("*SKSI-HTTP-REQUEST-OVERALL-TIMEOUT*");

    // defparameter
    /**
     * Temporary variable that determines whether or not SKSI http requests should
     * be automatically redirected.
     */
    @LispMethod(comment = "Temporary variable that determines whether or not SKSI http requests should\r\nbe automatically redirected.\ndefparameter\nTemporary variable that determines whether or not SKSI http requests should\nbe automatically redirected.")
    private static final SubLSymbol $sksi_http_automatically_redirect$ = makeSymbol("*SKSI-HTTP-AUTOMATICALLY-REDIRECT*");

    // defvar
    @LispMethod(comment = "defvar")
    private static final SubLSymbol $oracle_sparql_progress_stream$ = makeSymbol("*ORACLE-SPARQL-PROGRESS-STREAM*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $sksi_sparql_iterativity_limits$ = makeSymbol("*SKSI-SPARQL-ITERATIVITY-LIMITS*");

    // defparameter
    @LispMethod(comment = "defparameter\ndeflexical")
    // deflexical
    public static final SubLSymbol $sksi_open_sql_connection_default_timeout$ = makeSymbol("*SKSI-OPEN-SQL-CONNECTION-DEFAULT-TIMEOUT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $sksi_open_web_connection_default_timeout$ = makeSymbol("*SKSI-OPEN-WEB-CONNECTION-DEFAULT-TIMEOUT*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $sksi_open_sql_source_default_max_tries$ = makeSymbol("*SKSI-OPEN-SQL-SOURCE-DEFAULT-MAX-TRIES*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $sksi_open_sql_source_default_interval$ = makeSymbol("*SKSI-OPEN-SQL-SOURCE-DEFAULT-INTERVAL*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $sksi_fht_source_default_size$ = makeSymbol("*SKSI-FHT-SOURCE-DEFAULT-SIZE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $sksi_fht_source_default_average_bucket_size$ = makeSymbol("*SKSI-FHT-SOURCE-DEFAULT-AVERAGE-BUCKET-SIZE*");

    // defparameter
    /**
     * The timeout (in minutes)to be given to an SKSI sparql request for opening a
     * TCP connection.
     */
    @LispMethod(comment = "The timeout (in minutes)to be given to an SKSI sparql request for opening a\r\nTCP connection.\ndefparameter\nThe timeout (in minutes)to be given to an SKSI sparql request for opening a\nTCP connection.")
    private static final SubLSymbol $sksi_sparql_request_open_connection_timeout$ = makeSymbol("*SKSI-SPARQL-REQUEST-OPEN-CONNECTION-TIMEOUT*");

    // defparameter
    /**
     * Temporary variable (in seconds) that determines the timeout to be given to an
     * SKSI http request for the overall communication plus tokenization of the
     * output page returned.
     */
    @LispMethod(comment = "Temporary variable (in seconds) that determines the timeout to be given to an\r\nSKSI http request for the overall communication plus tokenization of the\r\noutput page returned.\ndefparameter\nTemporary variable (in seconds) that determines the timeout to be given to an\nSKSI http request for the overall communication plus tokenization of the\noutput page returned.")
    private static final SubLSymbol $sksi_sparql_request_overall_timeout$ = makeSymbol("*SKSI-SPARQL-REQUEST-OVERALL-TIMEOUT*");

    // defvar
    // A useful control variable for profiling time to first SPARQL execution.
    /**
     * A useful control variable for profiling time to first SPARQL execution.
     */
    @LispMethod(comment = "A useful control variable for profiling time to first SPARQL execution.\ndefvar")
    private static final SubLSymbol $query_interrupt_on_sparql_execution$ = makeSymbol("*QUERY-INTERRUPT-ON-SPARQL-EXECUTION*");

    // defvar
    /**
     * When non-nil, the SPARQL queries are not actually evaluated. This is a useful
     * control variable for profiling the non-SPARQL portions of an SKSI SPARQL
     * query.
     */
    @LispMethod(comment = "When non-nil, the SPARQL queries are not actually evaluated. This is a useful\r\ncontrol variable for profiling the non-SPARQL portions of an SKSI SPARQL\r\nquery.\ndefvar\nWhen non-nil, the SPARQL queries are not actually evaluated. This is a useful\ncontrol variable for profiling the non-SPARQL portions of an SKSI SPARQL\nquery.")
    private static final SubLSymbol $sparql_evaluation_enabledP$ = makeSymbol("*SPARQL-EVALUATION-ENABLED?*");

    // defvar
    // When non-nil, the optimized SPARQL result stream iterator should be used.
    /**
     * When non-nil, the optimized SPARQL result stream iterator should be used.
     */
    @LispMethod(comment = "When non-nil, the optimized SPARQL result stream iterator should be used.\ndefvar")
    public static final SubLSymbol $sparql_result_stream_iterator_enabledP$ = makeSymbol("*SPARQL-RESULT-STREAM-ITERATOR-ENABLED?*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $sparql_oracle_joseki_subprotocol$ = makeSymbol("*SPARQL-ORACLE-JOSEKI-SUBPROTOCOL*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $sparql_oracle_sesame_subprotocol$ = makeSymbol("*SPARQL-ORACLE-SESAME-SUBPROTOCOL*");

    // defconstant
    @LispMethod(comment = "defconstant")
    private static final SubLSymbol $sparql_triclops_subprotocol$ = makeSymbol("*SPARQL-TRICLOPS-SUBPROTOCOL*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $sparql_oracle_default_server_parallelism$ = makeSymbol("*SPARQL-ORACLE-DEFAULT-SERVER-PARALLELISM*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $sparql_oracle_fs_prefix_pragma_template$ = makeSymbol("*SPARQL-ORACLE-FS-PREFIX-PRAGMA-TEMPLATE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $sparql_oracle_ht_prefix_pragma_template$ = makeSymbol("*SPARQL-ORACLE-HT-PREFIX-PRAGMA-TEMPLATE*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $sparql_oracle_timeout$ = makeSymbol("*SPARQL-ORACLE-TIMEOUT*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $structured_knowledge_sources_assumed_available$ = makeSymbol("*STRUCTURED-KNOWLEDGE-SOURCES-ASSUMED-AVAILABLE*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $sksi_sql_statement_pool_max_size$ = makeSymbol("*SKSI-SQL-STATEMENT-POOL-MAX-SIZE*");

    // defparameter
    @LispMethod(comment = "defparameter")
    public static final SubLSymbol $sksi_global_resourcingP$ = makeSymbol("*SKSI-GLOBAL-RESOURCING?*");

    // deflexical
    @LispMethod(comment = "deflexical")
    private static final SubLSymbol $sksi_default_connection_max_idle_seconds$ = makeSymbol("*SKSI-DEFAULT-CONNECTION-MAX-IDLE-SECONDS*");

    // defconstant
    @LispMethod(comment = "defconstant")
    public static final SubLSymbol $dtp_sksi_resourcing_cache$ = makeSymbol("*DTP-SKSI-RESOURCING-CACHE*");

    // defparameter
    @LispMethod(comment = "defparameter")
    private static final SubLSymbol $sksi_salient_query_string$ = makeSymbol("*SKSI-SALIENT-QUERY-STRING*");

    private static final SubLObject $$Database_Physical = reader_make_constant_shell("Database-Physical");

    private static final SubLObject $$WebPage_AIS = reader_make_constant_shell("WebPage-AIS");



    private static final SubLObject $$FileHashTable_File = reader_make_constant_shell("FileHashTable-File");

    private static final SubLObject $$RDFTripleStore_Physical = reader_make_constant_shell("RDFTripleStore-Physical");

    static private final SubLList $list7 = list(makeSymbol("SELECT-KEYWORD"), makeSymbol("FIELDS"), makeSymbol("TABLES"), makeSymbol("&OPTIONAL"), makeSymbol("WHERE"));

    static private final SubLList $list8 = list(makeSymbol("WHERE-KEYWORD"), makeSymbol("WHERE-CLAUSES"));

    static private final SubLString $str10$malformed_csql_subexpression__a__ = makeString("malformed csql subexpression ~a, expected :where");









    private static final SubLObject $$MySQL_TheProgram = reader_make_constant_shell("MySQL-TheProgram");





    private static final SubLObject $$IBM_DB2_TheProgram = reader_make_constant_shell("IBM-DB2-TheProgram");

    private static final SubLObject $$Sybase_IQ = reader_make_constant_shell("Sybase-IQ");

    private static final SubLObject $$OracleDatabaseServer_TheProgram = reader_make_constant_shell("OracleDatabaseServer-TheProgram");

    static private final SubLList $list22 = list(makeSymbol("SELECT-KEYWORD"), makeSymbol("CSQL-FIELDS"), makeSymbol("TABLES"), makeSymbol("&OPTIONAL"), makeSymbol("WHERE"));

    private static final SubLList $list23 = list(makeSymbol("&OPTIONAL"), makeSymbol("WHERE-KEYWORD"), makeSymbol("WHERE-CLAUSES"));

    private static final SubLSymbol PHYSICAL_FIELD_INDEXICAL_P = makeSymbol("PHYSICAL-FIELD-INDEXICAL-P");

    private static final SubLList $list25 = list(makeSymbol("WHERE-OPERATOR"), list(makeSymbol("FIELD-TYPE"), makeSymbol("FIELD-NAME"), makeSymbol("FIELD-TABLE")), makeSymbol("VALUE"));



    private static final SubLString $str27$time_to_support__S_operator = makeString("time to support ~S operator");

    private static final SubLString $str28$No_default_value_provided_for__A = makeString("No default value provided for ~A");

    static private final SubLList $list30 = list(makeSymbol("WHERE-OPERATOR"), list(makeSymbol("WHERE-FIELD-TYPE"), makeSymbol("WHERE-FIELD-NAME"), makeSymbol("WHERE-TABLE")), makeSymbol("VALUE"));



    private static final SubLList $list33 = list(makeSymbol("FIELD-KEYWORD"), makeSymbol("FIELD-NAME"), makeSymbol("&OPTIONAL"), makeSymbol("TABLE"), makeSymbol("NAMESPACE"));

    private static final SubLString $str38$Parsing_method__S_is_not_yet_supp = makeString("Parsing method ~S is not yet supported");

    private static final SubLString $str41$_S_not_a_valid_value_for__sksi_ht = makeString("~S not a valid value for *sksi-http-request-caching-strategy*");

    private static final SubLSymbol GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED = makeSymbol("GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-MEMOIZED");

    private static final SubLSymbol GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_CACHED = makeSymbol("GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED");

    private static final SubLSymbol $get_results_from_sksi_web_page_subl_method_cached_caching_state$ = makeSymbol("*GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED-CACHING-STATE*");

    private static final SubLList $list45 = list(makeSymbol("SERVER"), makeSymbol("PORT"), makeSymbol("METHOD"), makeSymbol("URL"), makeSymbol("TIMEOUT"), makeSymbol("QUERY"));



    private static final SubLSymbol SKSI_TOKENIZED_HTTP_REQUEST = makeSymbol("SKSI-TOKENIZED-HTTP-REQUEST");

    private static final SubLInteger $int$80 = makeInteger(80);

    private static final SubLString $str50$http___ = makeString("http://");

    private static final SubLString $str51$_ = makeString("?");



    private static final SubLString $str55$Opening_the_TCP_connection_timed_ = makeString("Opening the TCP connection timed out.");

    private static final SubLString $str57$The_HTTP_request_timed_out_ = makeString("The HTTP request timed out.");

    private static final SubLSymbol SKSI_SUBL_PARSING_PROGRAM_P = makeSymbol("SKSI-SUBL-PARSING-PROGRAM-P");

    private static final SubLList $list59 = list(makeSymbol("EXPAND-SUBL"), list(makeSymbol("TOKENS-VAR")), makeSymbol("PROGRAM-BODY"));





    private static final SubLList $list64 = list(makeSymbol("SELECT-KEYWORD"), makeSymbol("FIELDS"), makeSymbol("FROM"), makeSymbol("&OPTIONAL"), makeSymbol("WHERE"));

    private static final SubLString $str66$malformed_csql_expression__a = makeString("malformed csql expression ~a");

    private static final SubLList $list67 = list(makeKeyword("TABLE"), makeString("Language_Spoken"));

    private static final SubLString $str68$unknown_cache_for_csql__a_and_acc = makeString("unknown cache for csql ~a and access path ~a");

    private static final SubLString $str69$___A____ = makeString("~&~A~%~%");

    private static final SubLList $list70 = list(NIL);

    private static final SubLSymbol SPARQL_RESULT_SET_ITERATOR_DONE_P = makeSymbol("SPARQL-RESULT-SET-ITERATOR-DONE-P");

    private static final SubLSymbol SPARQL_RESULT_SET_ITERATOR_NEXT = makeSymbol("SPARQL-RESULT-SET-ITERATOR-NEXT");

    static private final SubLList $list73 = list(makeSymbol("PENDING-RESULTS"), makeSymbol("PENDING-LIMITS"), makeSymbol("&REST"), makeSymbol("REST"));

    private static final SubLList $list74 = list(makeSymbol("PENDING-RESULTS"), makeSymbol("PENDING-LIMITS"), makeSymbol("PROCESSED-RESULTS"), makeSymbol("CSQL"), makeSymbol("ACCESS-PATH"));

    private static final SubLList $list75 = list(list(makeSymbol("RESULT-SET"), makeSymbol("SQL-STRING"), makeSymbol("ACCESS-PATH")), makeSymbol("&BODY"), makeSymbol("BODY"));

    private static final SubLSymbol SKSI_EXECUTE_SQL_QUERY = makeSymbol("SKSI-EXECUTE-SQL-QUERY");

    private static final SubLSymbol SQL_RESULT_SET_P = makeSymbol("SQL-RESULT-SET-P");

    private static final SubLString $str83$Problem_executing_query___a_ = makeString("Problem executing query: ~a.");

    private static final SubLSymbol FINALIZE_RESULT_SET = makeSymbol("FINALIZE-RESULT-SET");

    private static final SubLSymbol ACCESS_PATH_P = makeSymbol("ACCESS-PATH-P");

    private static final SubLString $str87$__SKSI__A_ = makeString("~&SKSI-~A ");

    private static final SubLString $str88$Executing_SQL_____Source___S__ = makeString("Executing SQL ~%  Source: ~S~%");

    private static final SubLString $str89$Module___S__ = makeString("Module: ~S~%");

    private static final SubLString $str90$Query____S__ = makeString("Query:  ~S~%");

    private static final SubLString $str91$Query_Result___S__ = makeString("Query Result: ~S~%");

    private static final SubLString $str92$Executing_SQL_____Source___S____U = makeString("Executing SQL ~%  Source: ~S~%  Update:~% ~S~%");

    private static final SubLString $str93$SQL_Update_Result___S__ = makeString("SQL Update Result: ~S~%");

    private static final SubLString $str94$Batch_executing__s_SQL_statements = makeString("Batch executing ~s SQL statements.~% Source: ~s.~%");

    private static final SubLString $$$LOCK_TABLE_ = makeString("LOCK TABLE ");

    private static final SubLString $$$_IN_EXCLUSIVE_MODE = makeString(" IN EXCLUSIVE MODE");

    private static final SubLString $$$_WRITE = makeString(" WRITE");

    private static final SubLString $str106$Batch_execution_resulted_in_error = makeString("Batch execution resulted in error: ~s~%");

    private static final SubLString $str107$Batch_execution_completed_success = makeString("Batch execution completed successfully.~%");

    private static final SubLString $$$SELECT_ = makeString("SELECT ");

    private static final SubLString $$$_FROM_ = makeString(" FROM ");

    private static final SubLString $$$_ORDER_BY_ = makeString(" ORDER BY ");

    private static final SubLString $$$_DESC_LIMIT_ = makeString(" DESC LIMIT ");

    private static final SubLString $str112$SELECT___FROM__SELECT_ = makeString("SELECT * FROM (SELECT ");

    private static final SubLString $str113$_DESC__WHERE_rownum_BETWEEN_ = makeString(" DESC) WHERE rownum BETWEEN ");

    private static final SubLString $$$_AND_ = makeString(" AND ");

    private static final SubLString $str115$Auto_generated_key_retrieval_is_n = makeString("Auto-generated key retrieval is not yet supported for ~a");

    private static final SubLSymbol SKSI_EXECUTE_SQL_SCRIPT_FROM_FILE = makeSymbol("SKSI-EXECUTE-SQL-SCRIPT-FROM-FILE");

    private static final SubLList $list117 = list(CHAR_semicolon);

    private static final SubLSymbol SKSI_EXECUTE_SQL_SCRIPT = makeSymbol("SKSI-EXECUTE-SQL-SCRIPT");

    private static final SubLString $$$SELECT = makeString("SELECT");

    private static final SubLFloat $float$0_1 = makeDouble(0.1);

    private static final SubLSymbol $KEY_VALUE_FIRST = makeKeyword("KEY-VALUE-FIRST");

    private static final SubLList $list127 = list(makeKeyword("COUNT"), makeKeyword("ALL"));

    private static final SubLString $str131$The_file_already_exists_ = makeString("The file already exists.");

    private static final SubLSymbol $result_set_sql_connection_statement_cache$ = makeSymbol("*RESULT-SET-SQL-CONNECTION-STATEMENT-CACHE*");

    private static final SubLSymbol CACHED_GET_RESULT_SET_FROM_SQL_SOURCE = makeSymbol("CACHED-GET-RESULT-SET-FROM-SQL-SOURCE");

    private static final SubLSymbol $cached_get_result_set_from_sql_source_caching_state$ = makeSymbol("*CACHED-GET-RESULT-SET-FROM-SQL-SOURCE-CACHING-STATE*");

    private static final SubLInteger $int$500 = makeInteger(500);

    private static final SubLInteger $int$3600 = makeInteger(3600);

    private static final SubLString $$$ticket = makeString("ticket");

    private static final SubLString $str140$oracle_joseki = makeString("oracle-joseki");

    private static final SubLString $str141$oracle_sesame = makeString("oracle-sesame");

    private static final SubLString $$$triclops = makeString("triclops");

    private static final SubLString $str143$_ticket = makeString("/ticket");

    private static final SubLString $str144$type_id = makeString("type=id");

    private static final SubLList $list145 = list(makeString("text/plan"), makeString("text/html"));

    private static final SubLSymbol $append_stack_traces_to_error_messagesP$ = makeSymbol("*APPEND-STACK-TRACES-TO-ERROR-MESSAGES?*");

    private static final SubLList $list147 = list(makeSymbol("CSETQ"), makeSymbol("*APPEND-STACK-TRACES-TO-ERROR-MESSAGES?*"), NIL);

    private static final SubLString $str148$_A = makeString("~A");

    private static final SubLString $str149$http___oracle_com_semtech_ordered = makeString("http://oracle.com/semtech#ordered,s2s,plain_sql_opt=f,ods=4,qid=~A~A~A");

    private static final SubLString $str150$http___oracle_com_semtech_ALL_PUL = makeString("http://oracle.com/semtech#ALL_PULL_PARENT_BGP,ALL_BGP_HASH,ALL_ORDERED,NO_PUSH_VALUE");

    private static final SubLString $str151$ORACLE_SEM_FS_NS = makeString("ORACLE_SEM_FS_NS");

    private static final SubLString $str152$_timeout_ = makeString(",timeout=");

    private static final SubLString $str153$ = makeString("");

    private static final SubLString $str154$_dop_ = makeString(",dop=");

    private static final SubLString $str155$ORACLE_SEM_HT_NS = makeString("ORACLE_SEM_HT_NS");

    private static final SubLString $str156$_joseki_querymgt = makeString("/joseki/querymgt");

    private static final SubLString $str157$abortqid_ = makeString("abortqid=");

    private static final SubLString $str158$_openrdf_sesame_querymgt = makeString("/openrdf-sesame/querymgt");

    private static final SubLString $str159$_processes = makeString("/processes");

    private static final SubLString $str160$ticket_ = makeString("ticket=");

    private static final SubLSymbol $kw164$KEEP_ALIVE_ = makeKeyword("KEEP-ALIVE?");

    private static final SubLSymbol $kw165$WIDE_NEWLINES_ = makeKeyword("WIDE-NEWLINES?");

    private static final SubLSymbol CACHED_GET_RESULT_SET_FROM_SPARQL_SOURCE = makeSymbol("CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE");

    private static final SubLSymbol $cached_get_result_set_from_sparql_source_caching_state$ = makeSymbol("*CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-CACHING-STATE*");

    private static final SubLString $str169$Needs_to_be_re_implemented_withou = makeString("Needs to be re-implemented without function caching macros.");

    private static final SubLString $$$WHERE = makeString("WHERE");

    private static final SubLList $list171 = list(CHAR_space);

    private static final SubLList $list172 = list(CHAR_question, CHAR_dollar);

    private static final SubLString $str173$query_ = makeString("query=");

    private static final SubLString $str174$jonny2_cyc_com = makeString("jonny2.cyc.com");

    private static final SubLInteger $int$8080 = makeInteger(8080);

    private static final SubLString $str176$_SemanticDB_SPARQL_ = makeString("/SemanticDB/SPARQL/");

    private static final SubLList $list177 = list(makeString("application/sparql-results+xml"), makeString("application/xml"));

    private static final SubLString $str178$sparql_query_xml_tokens_error___S = makeString("sparql-query-xml-tokens error (~S ~S)");

    private static final SubLString $$$query = makeString("query");

    public static final SubLSymbol $access_path$ = makeSymbol("*ACCESS-PATH*");

    private static final SubLString $str184$ASK__ = makeString("ASK {");

    private static final SubLList $list185 = list(cons(makeString("owl"), makeString("http://www.w3.org/2000/07/owl#")), cons(makeString("ptrec"), makeString("tag:info@semanticdb.ccf.org,2007:PatientRecordTerms#")), cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")), cons(makeString("xsd"), makeString("http://www.w3.org/2001/XMLSchema#")));

    private static final SubLString $str186$http___www_clevelandclinic_org_he = makeString("http://www.clevelandclinic.org/heartcenter/ontologies/DataNodes.owl#");





    private static final SubLList $list189 = list(makeString("CONTEXT"));

    private static final SubLString $str190$_sparql = makeString("<sparql");

    private static final SubLString $$$results = makeString("results");

    private static final SubLSymbol TEST_SKSI_IST_GRAPH = makeSymbol("TEST-SKSI-IST-GRAPH");

    private static final SubLList $list199 = list(list(list(list(new SubLObject[]{ makeString("HTTP/1.0 200 OK\n\nServer: SimpleHTTP/0.6 Python/2.4.2\n\nDate: Fri, 23 Jan 2009 20:55:16 GMT\n\nContent-type: application/xml\n\nContent-Length: 428\n\n\n\n"), makeString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"), makeString("\n"), makeString("<?xml-stylesheet type='text/xml' href='/xslt/xml-to-html.xslt'?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">"), makeString("\n  "), makeString("<head>"), makeString("\n    "), makeString("<variable name=\"CONTEXT\"/>"), makeString("\n  "), makeString("</head>"), makeString("\n  "), makeString("<results distinct=\"false\" ordered=\"false\">"), makeString("\n    "), makeString("<result>"), makeString("\n      "), makeString("<binding name=\"CONTEXT\">"), makeString("\n        "), makeString("<uri>"), makeString("ahttp://semanticdb.ccf.org/SemanticDB/CVIR/39932466.xml"), makeString("</uri>"), makeString("\n      "), makeString("</binding>"), makeString("\n    "), makeString("</result>"), makeString("\n  "), makeString("</results>"), makeString("\n"), makeString("</sparql>"), makeString("\n") }), list(makeString("CONTEXT")), list(new SubLObject[]{ cons(makeString("csqr"), makeString("tag:info@semanticdb.ccf.org,2008:CardiacSurgeryQualityReport#")), cons(makeString("cyc"), makeString("http://sw.cyc.com/2006/07/27/cyc#")), cons(makeString("dnode"), makeString("http://www.clevelandclinic.org/heartcenter/ontologies/DataNodes.owl#")), cons(makeString("owl"), makeString("http://www.w3.org/2000/07/owl#")), cons(makeString("ptrec"), makeString("tag:info@semanticdb.ccf.org,2007:PatientRecordTerms#")), cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")), cons(makeString("sts"), makeString("tag:info@semanticdb.ccf.org,2008:STS.2.61#")), cons(makeString("xsd"), makeString("http://www.w3.org/2001/XMLSchema#")) })), makeString("<ahttp://semanticdb.ccf.org/SemanticDB/CVIR/39932466.xml>")));

    private static final SubLSymbol SKSI_SPARQL_XML_TOKENS_TO_RESULT_SET = makeSymbol("SKSI-SPARQL-XML-TOKENS-TO-RESULT-SET");

    private static final SubLObject $list201 = sksi_sks_interaction._constant_201_initializer();

    private static final SubLString $str202$File_hash_table_does_not_exist_ = makeString("File hash table does not exist.");

    private static final SubLString $str203$The_following_problem_was_encount = makeString("The following problem was encountered while trying to create the knowledge source ~A :~%");

    private static final SubLString $str204$__The_following_problem_was_encou = makeString("~%The following problem was encountered while trying to execute the following statement in the knowledge source ~A :~%~A~%");

    private static final SubLString $str205$__The_following_problem_was_encou = makeString("~%The following problem was encountered while trying to access the knowledge source ~A :~%");

    private static final SubLString $$$Continue_without_accessing_ = makeString("Continue without accessing ");

    private static final SubLString $str207$_ = makeString(".");

    private static final SubLSymbol $sksi_resourcing_cache$ = makeSymbol("*SKSI-RESOURCING-CACHE*");

    private static final SubLInteger $int$600 = makeInteger(600);

    private static final SubLSymbol SKSI_RESOURCING_CACHE = makeSymbol("SKSI-RESOURCING-CACHE");

    private static final SubLSymbol SKSI_RESOURCING_CACHE_P = makeSymbol("SKSI-RESOURCING-CACHE-P");

    private static final SubLList $list216 = list(makeSymbol("CONNECTIONS"), makeSymbol("STATEMENTS"), makeSymbol("USED-STATEMENTS"), makeSymbol("ACCESS-TIMES"), makeSymbol("LOCK"), makeSymbol("MAX-POOL-SIZE"), makeSymbol("MAX-IDLE"));

    private static final SubLList $list217 = list(makeKeyword("CONNECTIONS"), makeKeyword("STATEMENTS"), makeKeyword("USED-STATEMENTS"), makeKeyword("ACCESS-TIMES"), $LOCK, makeKeyword("MAX-POOL-SIZE"), makeKeyword("MAX-IDLE"));

    private static final SubLList $list218 = list(makeSymbol("SKSI-RESOURCING-CACHE-CONNECTIONS"), makeSymbol("SKSI-RESOURCING-CACHE-STATEMENTS"), makeSymbol("SKSI-RESOURCING-CACHE-USED-STATEMENTS"), makeSymbol("SKSI-RESOURCING-CACHE-ACCESS-TIMES"), makeSymbol("SKSI-RESOURCING-CACHE-LOCK"), makeSymbol("SKSI-RESOURCING-CACHE-MAX-POOL-SIZE"), makeSymbol("SKSI-RESOURCING-CACHE-MAX-IDLE"));

    private static final SubLList $list219 = list(makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-CONNECTIONS"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-STATEMENTS"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-USED-STATEMENTS"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-ACCESS-TIMES"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-LOCK"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-MAX-POOL-SIZE"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-MAX-IDLE"));

    private static final SubLSymbol SKSI_RESOURCING_CACHE_PRINT_FUNCTION_TRAMPOLINE = makeSymbol("SKSI-RESOURCING-CACHE-PRINT-FUNCTION-TRAMPOLINE");

    static private final SubLList $list222 = list(makeSymbol("OPTIMIZE-FUNCALL"), makeSymbol("SKSI-RESOURCING-CACHE-P"));

    private static final SubLSymbol SKSI_RESOURCING_CACHE_CONNECTIONS = makeSymbol("SKSI-RESOURCING-CACHE-CONNECTIONS");

    private static final SubLSymbol _CSETF_SKSI_RESOURCING_CACHE_CONNECTIONS = makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-CONNECTIONS");

    private static final SubLSymbol SKSI_RESOURCING_CACHE_STATEMENTS = makeSymbol("SKSI-RESOURCING-CACHE-STATEMENTS");

    private static final SubLSymbol _CSETF_SKSI_RESOURCING_CACHE_STATEMENTS = makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-STATEMENTS");

    private static final SubLSymbol SKSI_RESOURCING_CACHE_USED_STATEMENTS = makeSymbol("SKSI-RESOURCING-CACHE-USED-STATEMENTS");

    private static final SubLSymbol _CSETF_SKSI_RESOURCING_CACHE_USED_STATEMENTS = makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-USED-STATEMENTS");

    private static final SubLSymbol SKSI_RESOURCING_CACHE_ACCESS_TIMES = makeSymbol("SKSI-RESOURCING-CACHE-ACCESS-TIMES");

    private static final SubLSymbol _CSETF_SKSI_RESOURCING_CACHE_ACCESS_TIMES = makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-ACCESS-TIMES");

    private static final SubLSymbol SKSI_RESOURCING_CACHE_LOCK = makeSymbol("SKSI-RESOURCING-CACHE-LOCK");

    private static final SubLSymbol _CSETF_SKSI_RESOURCING_CACHE_LOCK = makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-LOCK");

    private static final SubLSymbol SKSI_RESOURCING_CACHE_MAX_POOL_SIZE = makeSymbol("SKSI-RESOURCING-CACHE-MAX-POOL-SIZE");

    private static final SubLSymbol _CSETF_SKSI_RESOURCING_CACHE_MAX_POOL_SIZE = makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-MAX-POOL-SIZE");

    private static final SubLSymbol SKSI_RESOURCING_CACHE_MAX_IDLE = makeSymbol("SKSI-RESOURCING-CACHE-MAX-IDLE");

    private static final SubLSymbol _CSETF_SKSI_RESOURCING_CACHE_MAX_IDLE = makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-MAX-IDLE");

    private static final SubLSymbol $MAX_POOL_SIZE = makeKeyword("MAX-POOL-SIZE");

    private static final SubLString $str244$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    private static final SubLSymbol MAKE_SKSI_RESOURCING_CACHE = makeSymbol("MAKE-SKSI-RESOURCING-CACHE");

    private static final SubLSymbol VISIT_DEFSTRUCT_OBJECT_SKSI_RESOURCING_CACHE_METHOD = makeSymbol("VISIT-DEFSTRUCT-OBJECT-SKSI-RESOURCING-CACHE-METHOD");

    private static final SubLString $$$SKSI_SQL_resourcing_cache_ = makeString("SKSI SQL resourcing cache ");

    private static final SubLList $list251 = list(makeSymbol("CACHE"), makeSymbol("&BODY"), makeSymbol("BODY"));

    public static final SubLSymbol $sksi_connection_global_cache_lock$ = makeSymbol("*SKSI-CONNECTION-GLOBAL-CACHE-LOCK*");

    private static final SubLString $str254$SKSI_SQL_global_connection_cache_ = makeString("SKSI SQL global connection cache lock ");

    private static final SubLSymbol $sym255$_EXIT = makeSymbol("%EXIT");

    private static final SubLSymbol $IGNORE_ERRORS_TARGET = makeKeyword("IGNORE-ERRORS-TARGET");

    private static final SubLSymbol IGNORE_ERRORS_HANDLER = makeSymbol("IGNORE-ERRORS-HANDLER", "SUBLISP");

    private static final SubLFloat $float$0_2 = makeDouble(0.2);

    private static final SubLSymbol VALID_SKSI_ERROR_HANDLING_MODE_P = makeSymbol("VALID-SKSI-ERROR-HANDLING-MODE-P");

    private static final SubLList $list263 = list(makeKeyword("REMOVAL"), makeKeyword("REMOVAL-CONJUNCTIVE"));

    private static final SubLSymbol MEMOIZED_SALIENT_SKSI_QUERY_STRING = makeSymbol("MEMOIZED-SALIENT-SKSI-QUERY-STRING");

    private static final SubLSymbol SKSI_TACTIC_P = makeSymbol("SKSI-TACTIC-P");

    private static final SubLSymbol $EXECUTED_OR_IN_PROGRESS = makeKeyword("EXECUTED-OR-IN-PROGRESS");

    private static final SubLSymbol SPARQL_EXTRACT_VARIABLE_NAMES = makeSymbol("SPARQL-EXTRACT-VARIABLE-NAMES");

    private static final SubLList $list270 = list(list(list(makeString("BASE <http://www.clevelandclinic.org/heartcenter/ontologies/DataNodes.owl#>\nPREFIX owl: <http://www.w3.org/2000/07/owl#>\nPREFIX ptrec: <tag:info@semanticdb.ccf.org,2007:PatientRecordTerms#>\nPREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\nPREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\nPREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\nSELECT ?VAR0 ?VAR1 ?VAR2 ?VAR3\nWHERE {\n  ?VAR0 ptrec:hasCoronaryBypassConduit  \"internal thoracic artery\" .\n  ?VAR1 <http://www.clevelandclinic.org/heartcenter/ontologies/DataNodes.owl#contains> ?VAR0 .\n  ?VAR2 <http://www.clevelandclinic.org/heartcenter/ontologies/DataNodes.owl#contains> ?VAR1 .\n  ?VAR2 rdf:type ?VAR3 .\n}")), list(makeString("VAR0"), makeString("VAR1"), makeString("VAR2"), makeString("VAR3"))), list(list(makeString("select distinct ?Concept where {<http://dbpedia.org/resource/Tina_Turner> rdf:type ?Concept}")), list(makeString("Concept"))), list(list(makeString("select distinct $Concept where {<http://dbpedia.org/resource/Tina_Turner> rdf:type $Concept}")), list(makeString("Concept"))));

    // Definitions
    public static final SubLObject generate_iterator_from_csql_alt(SubLObject csql, SubLObject sk_source, SubLObject content_mt, SubLObject start_row, SubLObject end_row, SubLObject translation_style) {
        if (content_mt == UNPROVIDED) {
            content_mt = NIL;
        }
        if (start_row == UNPROVIDED) {
            start_row = ZERO_INTEGER;
        }
        if (end_row == UNPROVIDED) {
            end_row = NIL;
        }
        if (translation_style == UNPROVIDED) {
            translation_style = $NORMAL;
        }
        if (NIL != sksi_csql_utilities.invalid_csql_expressionP(csql)) {
            return iteration.new_null_iterator();
        }
        if (NIL != sksi_csql_utilities.boolean_csqlP(csql)) {
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_boolean_from_csql(csql, sk_source, content_mt);
        }
        {
            SubLObject access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
            SubLObject type = sksi_access_path.structured_knowledge_source_type(access_path);
            SubLObject result = NIL;
            SubLObject pcase_var = type;
            if (pcase_var.eql($$Database_Physical)) {
                result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_db_iterator_from_csql(csql, access_path, start_row, end_row, translation_style);
            } else {
                if (pcase_var.eql($$WebPage_AIS)) {
                    result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_web_page_iterator_from_csql(csql, access_path);
                } else {
                    if (pcase_var.eql($$CycKB)) {
                        result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_kb_iterator_from_csql(csql, access_path);
                    } else {
                        if (pcase_var.eql($$FileHashTable_File)) {
                            result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_fht_iterator_from_csql(csql, access_path);
                        } else {
                            if (pcase_var.eql($$RDFTripleStore_Physical)) {
                                result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_rdf_iterator_from_csql(csql, access_path);
                            } else {
                                if (pcase_var.eql($CACHE)) {
                                    result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_cache_iterator_from_csql(csql, access_path);
                                }
                            }
                        }
                    }
                }
            }
            return result;
        }
    }

    // Definitions
    public static SubLObject generate_iterator_from_csql(final SubLObject csql, final SubLObject sk_source, SubLObject content_mt, SubLObject start_row, SubLObject end_row, SubLObject translation_style) {
        if (content_mt == UNPROVIDED) {
            content_mt = NIL;
        }
        if (start_row == UNPROVIDED) {
            start_row = ZERO_INTEGER;
        }
        if (end_row == UNPROVIDED) {
            end_row = NIL;
        }
        if (translation_style == UNPROVIDED) {
            translation_style = $NORMAL;
        }
        if (NIL != sksi_csql_utilities.invalid_csql_expressionP(csql)) {
            return new_null_iterator();
        }
        if (NIL != sksi_csql_utilities.boolean_csqlP(csql)) {
            return sksi_sks_interaction.generate_boolean_from_csql(csql, sk_source, content_mt);
        }
        final SubLObject access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
        final SubLObject type = sksi_access_path.structured_knowledge_source_type(access_path);
        SubLObject result = NIL;
        final SubLObject pcase_var = type;
        if (pcase_var.eql(sksi_sks_interaction.$$Database_Physical)) {
            result = sksi_sks_interaction.generate_db_iterator_from_csql(csql, access_path, start_row, end_row, translation_style);
        } else
            if (pcase_var.eql(sksi_sks_interaction.$$WebPage_AIS)) {
                result = sksi_sks_interaction.generate_web_page_iterator_from_csql(csql, access_path);
            } else
                if (pcase_var.eql(sksi_sks_interaction.$$CycKB)) {
                    result = sksi_sks_interaction.generate_kb_iterator_from_csql(csql, access_path);
                } else
                    if (pcase_var.eql(sksi_sks_interaction.$$FileHashTable_File)) {
                        result = sksi_sks_interaction.generate_fht_iterator_from_csql(csql, access_path);
                    } else
                        if (pcase_var.eql(sksi_sks_interaction.$$RDFTripleStore_Physical)) {
                            result = sksi_sks_interaction.generate_rdf_iterator_from_csql(csql, access_path);
                        } else
                            if (pcase_var.eql($CACHE)) {
                                result = sksi_sks_interaction.generate_cache_iterator_from_csql(csql, access_path);
                            }





        return result;
    }

    /**
     * CONTENT-MT is only used to generate supports if we're in justification mode
     */
    @LispMethod(comment = "CONTENT-MT is only used to generate supports if we\'re in justification mode")
    public static final SubLObject generate_boolean_from_csql_alt(SubLObject csql, SubLObject sk_source, SubLObject content_mt) {
        if (content_mt == UNPROVIDED) {
            content_mt = NIL;
        }
        if (NIL != sksi_csql_utilities.invalid_csql_expressionP(csql)) {
            return NIL;
        }
        {
            SubLObject access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
            SubLObject result = NIL;
            SubLObject type = sksi_access_path.structured_knowledge_source_type(access_path);
            SubLObject pcase_var = type;
            if (pcase_var.eql($$Database_Physical)) {
                result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_db_boolean_from_csql(csql, access_path);
            } else {
                if (pcase_var.eql($$WebPage_AIS)) {
                    result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_web_page_boolean_from_csql(csql, access_path);
                } else {
                    if (pcase_var.eql($$CycKB)) {
                        result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_kb_boolean_from_csql(csql, access_path);
                    } else {
                        if (pcase_var.eql($$FileHashTable_File)) {
                            result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_fht_boolean_from_csql(csql, access_path);
                        } else {
                            if (pcase_var.eql($$RDFTripleStore_Physical)) {
                                result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_rdf_boolean_from_csql(csql, access_path);
                            } else {
                                if (pcase_var.eql($CACHE)) {
                                    result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_cache_boolean_from_csql(csql, access_path);
                                }
                            }
                        }
                    }
                }
            }
            if (((NIL != result) && (NIL != sk_source)) && (NIL != content_mt)) {
                {
                    SubLObject supports = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.boolean_csql_to_sksi_supports(csql, sk_source, content_mt);
                    SubLObject cdolist_list_var = supports;
                    SubLObject support = NIL;
                    for (support = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , support = cdolist_list_var.first()) {
                        sksi_hl_support_utilities.note_sksi_support(support);
                    }
                }
            }
            return NIL != result ? ((SubLObject) (list(result))) : result;
        }
    }

    /**
     * CONTENT-MT is only used to generate supports if we're in justification mode
     */
    @LispMethod(comment = "CONTENT-MT is only used to generate supports if we\'re in justification mode")
    public static SubLObject generate_boolean_from_csql(final SubLObject csql, final SubLObject sk_source, SubLObject content_mt) {
        if (content_mt == UNPROVIDED) {
            content_mt = NIL;
        }
        if (NIL != sksi_csql_utilities.invalid_csql_expressionP(csql)) {
            return NIL;
        }
        final SubLObject access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
        SubLObject result = NIL;
        final SubLObject pcase_var;
        final SubLObject type = pcase_var = sksi_access_path.structured_knowledge_source_type(access_path);
        if (pcase_var.eql(sksi_sks_interaction.$$Database_Physical)) {
            result = sksi_sks_interaction.generate_db_boolean_from_csql(csql, access_path);
        } else
            if (pcase_var.eql(sksi_sks_interaction.$$WebPage_AIS)) {
                result = sksi_sks_interaction.generate_web_page_boolean_from_csql(csql, access_path);
            } else
                if (pcase_var.eql(sksi_sks_interaction.$$CycKB)) {
                    result = sksi_sks_interaction.generate_kb_boolean_from_csql(csql, access_path);
                } else
                    if (pcase_var.eql(sksi_sks_interaction.$$FileHashTable_File)) {
                        result = sksi_sks_interaction.generate_fht_boolean_from_csql(csql, access_path);
                    } else
                        if (pcase_var.eql(sksi_sks_interaction.$$RDFTripleStore_Physical)) {
                            result = sksi_sks_interaction.generate_rdf_boolean_from_csql(csql, access_path);
                        } else
                            if (pcase_var.eql($CACHE)) {
                                result = sksi_sks_interaction.generate_cache_boolean_from_csql(csql, access_path);
                            }





        if (((NIL != result) && (NIL != sk_source)) && (NIL != content_mt)) {
            SubLObject cdolist_list_var;
            final SubLObject supports = cdolist_list_var = sksi_sks_interaction.boolean_csql_to_sksi_supports(csql, sk_source, content_mt);
            SubLObject support = NIL;
            support = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                note_sksi_support(support);
                cdolist_list_var = cdolist_list_var.rest();
                support = cdolist_list_var.first();
            } 
        }
        return NIL != result ? list(result) : result;
    }

    public static final SubLObject boolean_csql_to_sksi_supports_alt(SubLObject csql, SubLObject sk_source, SubLObject content_mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = csql;
                SubLObject current = datum;
                SubLObject select_keyword = NIL;
                SubLObject fields = NIL;
                SubLObject tables = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt7);
                select_keyword = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt7);
                fields = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt7);
                tables = current.first();
                current = current.rest();
                {
                    SubLObject where = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                    destructuring_bind_must_listp(current, datum, $list_alt7);
                    current = current.rest();
                    if (NIL == current) {
                        if (NIL == where) {
                            return NIL;
                        }
                        {
                            SubLObject datum_1 = where;
                            SubLObject current_2 = datum_1;
                            SubLObject where_keyword = NIL;
                            SubLObject where_clauses = NIL;
                            destructuring_bind_must_consp(current_2, datum_1, $list_alt8);
                            where_keyword = current_2.first();
                            current_2 = current_2.rest();
                            destructuring_bind_must_consp(current_2, datum_1, $list_alt8);
                            where_clauses = current_2.first();
                            current_2 = current_2.rest();
                            if (NIL == current_2) {
                                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                    if ($WHERE != where_keyword) {
                                        Errors.error($str_alt10$malformed_csql_subexpression__a__, where);
                                    }
                                }
                                {
                                    SubLObject supports = NIL;
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject row_in_source_claims = sksi_csql_utilities.csql_to_cycl_int(where_clauses, sk_source, second(tables));
                                        SubLObject source_claims = thread.secondMultipleValue();
                                        thread.resetMultipleValues();
                                        if (NIL != sksi_infrastructure_utilities.sksi_true_cycl_expression_p(row_in_source_claims)) {
                                            {
                                                SubLObject support_sentence = make_el_formula($$rowInSourceClaims, list(sk_source, row_in_source_claims), UNPROVIDED);
                                                supports = cons(arguments.make_hl_support($CSQL, support_sentence, content_mt, UNPROVIDED), supports);
                                            }
                                        }
                                        if (NIL != sksi_infrastructure_utilities.sksi_true_cycl_expression_p(source_claims)) {
                                            {
                                                SubLObject support_sentence = make_el_formula($$sourceClaims, list(sk_source, source_claims), UNPROVIDED);
                                                supports = cons(arguments.make_hl_support($CSQL, support_sentence, content_mt, UNPROVIDED), supports);
                                            }
                                        }
                                    }
                                    return supports;
                                }
                            } else {
                                cdestructuring_bind_error(datum_1, $list_alt8);
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt7);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject boolean_csql_to_sksi_supports(final SubLObject csql, final SubLObject sk_source, final SubLObject content_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject select_keyword = NIL;
        SubLObject fields = NIL;
        SubLObject tables = NIL;
        destructuring_bind_must_consp(csql, csql, sksi_sks_interaction.$list7);
        select_keyword = csql.first();
        SubLObject current = csql.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list7);
        fields = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list7);
        tables = current.first();
        current = current.rest();
        final SubLObject where = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, csql, sksi_sks_interaction.$list7);
        current = current.rest();
        if (NIL == current) {
            if (NIL == where) {
                return NIL;
            }
            SubLObject current_$2;
            final SubLObject datum_$1 = current_$2 = where;
            SubLObject where_keyword = NIL;
            SubLObject where_clauses = NIL;
            destructuring_bind_must_consp(current_$2, datum_$1, sksi_sks_interaction.$list8);
            where_keyword = current_$2.first();
            current_$2 = current_$2.rest();
            destructuring_bind_must_consp(current_$2, datum_$1, sksi_sks_interaction.$list8);
            where_clauses = current_$2.first();
            current_$2 = current_$2.rest();
            if (NIL == current_$2) {
                if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && ($WHERE != where_keyword)) {
                    Errors.error(sksi_sks_interaction.$str10$malformed_csql_subexpression__a__, where);
                }
                SubLObject supports = NIL;
                thread.resetMultipleValues();
                final SubLObject row_in_source_claims = sksi_csql_utilities.csql_to_cycl_int(where_clauses, sk_source, second(tables));
                final SubLObject source_claims = thread.secondMultipleValue();
                thread.resetMultipleValues();
                if (NIL != sksi_infrastructure_utilities.sksi_true_cycl_expression_p(row_in_source_claims)) {
                    final SubLObject support_sentence = make_el_formula(sksi_sks_interaction.$$rowInSourceClaims, list(sk_source, row_in_source_claims), UNPROVIDED);
                    supports = cons(make_hl_support($CSQL, support_sentence, content_mt, UNPROVIDED), supports);
                }
                if (NIL != sksi_infrastructure_utilities.sksi_true_cycl_expression_p(source_claims)) {
                    final SubLObject support_sentence = make_el_formula(sksi_sks_interaction.$$sourceClaims, list(sk_source, source_claims), UNPROVIDED);
                    supports = cons(make_hl_support($CSQL, support_sentence, content_mt, UNPROVIDED), supports);
                }
                return supports;
            }
            cdestructuring_bind_error(datum_$1, sksi_sks_interaction.$list8);
        } else {
            cdestructuring_bind_error(csql, sksi_sks_interaction.$list7);
        }
        return NIL;
    }

    /**
     * Uses an external database as the knowledge source
     */
    @LispMethod(comment = "Uses an external database as the knowledge source")
    public static final SubLObject generate_db_iterator_from_csql_alt(SubLObject csql, SubLObject access_path, SubLObject start_row, SubLObject end_row, SubLObject translation_style) {
        if (start_row == UNPROVIDED) {
            start_row = ZERO_INTEGER;
        }
        if (end_row == UNPROVIDED) {
            end_row = NIL;
        }
        if (translation_style == UNPROVIDED) {
            translation_style = $NORMAL;
        }
        {
            SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_sdbc_result_set_from_csql(csql, access_path, translation_style);
            if (NIL != sdbc.sql_result_set_p(result_set)) {
                {
                    SubLObject iterator = sksi_result_set_iterators.new_sqlrs_iterator(result_set, start_row, end_row);
                    return iterator;
                }
            }
        }
        return NIL;
    }

    /**
     * Uses an external database as the knowledge source
     */
    @LispMethod(comment = "Uses an external database as the knowledge source")
    public static SubLObject generate_db_iterator_from_csql(final SubLObject csql, final SubLObject access_path, SubLObject start_row, SubLObject end_row, SubLObject translation_style) {
        if (start_row == UNPROVIDED) {
            start_row = ZERO_INTEGER;
        }
        if (end_row == UNPROVIDED) {
            end_row = NIL;
        }
        if (translation_style == UNPROVIDED) {
            translation_style = $NORMAL;
        }
        final SubLObject result_set = sksi_sks_interaction.generate_sdbc_result_set_from_csql(csql, access_path, translation_style);
        if (NIL != sql_result_set_p(result_set)) {
            final SubLObject iterator = sksi_result_set_iterators.new_sqlrs_iterator(result_set, start_row, end_row);
            return iterator;
        }
        return NIL;
    }

    /**
     * Uses an external database as the knowledge source
     */
    @LispMethod(comment = "Uses an external database as the knowledge source")
    public static final SubLObject generate_db_boolean_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_sdbc_result_set_from_csql(csql, access_path, UNPROVIDED);
            if (NIL != sdbc.sql_result_set_p(result_set)) {
                {
                    SubLObject sql_flavor = sksi_access_path.access_path_sql_flavor(access_path);
                    SubLObject v_answer = NIL;
                    SubLObject pcase_var = sql_flavor;
                    if (pcase_var.eql($$MSAccess)) {
                        v_answer = sdbc.sqlrs_next(result_set);
                    } else {
                        if (pcase_var.eql($$PostgreSQL) || pcase_var.eql($$MySQL_TheProgram)) {
                            v_answer = sdbc.sqlrs_next(result_set);
                        } else {
                            if (pcase_var.eql($$MSSQLServer)) {
                                sdbc.sqlrs_next(result_set);
                                {
                                    SubLObject row = sksi_result_set_iterators.sqlrs_current_row_as_list(result_set);
                                    if (NIL != list_utilities.singletonP(row)) {
                                        v_answer = makeBoolean(!row.first().isZero());
                                    }
                                }
                            } else {
                                if (pcase_var.eql($$OracleDatabaseServer_TheProgram)) {
                                    sdbc.sqlrs_next(result_set);
                                    {
                                        SubLObject row = sksi_result_set_iterators.sqlrs_current_row_as_list(result_set);
                                        if (NIL != list_utilities.singletonP(row)) {
                                            {
                                                SubLObject value = row.first();
                                                v_answer = makeBoolean((value.isString() && (!string_utilities.string_to_integer(value).isZero())) || (value.isInteger() && (!value.isZero())));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    sksi_result_set_iterators.finalize_result_set(result_set);
                    return v_answer;
                }
            }
        }
        return NIL;
    }

    /**
     * Uses an external database as the knowledge source
     */
    @LispMethod(comment = "Uses an external database as the knowledge source")
    public static SubLObject generate_db_boolean_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLObject result_set = sksi_sks_interaction.generate_sdbc_result_set_from_csql(csql, access_path, UNPROVIDED);
        if (NIL != sql_result_set_p(result_set)) {
            final SubLObject sql_flavor = sksi_access_path.access_path_sql_flavor(access_path);
            SubLObject v_answer = NIL;
            final SubLObject pcase_var = sql_flavor;
            if (pcase_var.eql(sksi_sks_interaction.$$MSAccess)) {
                v_answer = sqlrs_next(result_set);
            } else
                if (pcase_var.eql(sksi_sks_interaction.$$PostgreSQL) || pcase_var.eql(sksi_sks_interaction.$$MySQL_TheProgram)) {
                    v_answer = sqlrs_next(result_set);
                } else
                    if (((pcase_var.eql(sksi_sks_interaction.$$ApacheDerby) || pcase_var.eql(sksi_sks_interaction.$$MSSQLServer)) || pcase_var.eql(sksi_sks_interaction.$$IBM_DB2_TheProgram)) || pcase_var.eql(sksi_sks_interaction.$$Sybase_IQ)) {
                        sqlrs_next(result_set);
                        final SubLObject row = sksi_result_set_iterators.sqlrs_current_row_as_list(result_set);
                        if (NIL != singletonP(row)) {
                            v_answer = makeBoolean(!row.first().isZero());
                        }
                    } else
                        if (pcase_var.eql(sksi_sks_interaction.$$OracleDatabaseServer_TheProgram)) {
                            sqlrs_next(result_set);
                            final SubLObject row = sksi_result_set_iterators.sqlrs_current_row_as_list(result_set);
                            if (NIL != singletonP(row)) {
                                final SubLObject value = row.first();
                                v_answer = makeBoolean((value.isString() && (!string_to_integer(value).isZero())) || (value.isInteger() && (!value.isZero())));
                            }
                        }



            sksi_result_set_iterators.finalize_result_set(result_set);
            return v_answer;
        }
        return NIL;
    }

    public static final SubLObject generate_sdbc_result_set_from_csql_alt(SubLObject csql, SubLObject access_path, SubLObject translation_style) {
        if (translation_style == UNPROVIDED) {
            translation_style = $NORMAL;
        }
        {
            SubLObject sql_flavor = sksi_access_path.access_path_sql_flavor(access_path);
            SubLObject sql = sksi_csql_interpretation.csql_to_sql(csql, sql_flavor, translation_style);
            return sql.isString() ? ((SubLObject) (com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_set_from_sql_source(sql, access_path))) : NIL;
        }
    }

    public static SubLObject generate_sdbc_result_set_from_csql(final SubLObject csql, final SubLObject access_path, SubLObject translation_style) {
        if (translation_style == UNPROVIDED) {
            translation_style = $NORMAL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject sql_flavor = sksi_access_path.access_path_sql_flavor(access_path);
        final SubLObject sql = sksi_csql_interpretation.csql_to_sql(csql, sql_flavor, translation_style);
        if (!sql.isString()) {
            return NIL;
        }
        if (NIL != sksi_result_set_iterators.$sksi_caching_enabledP$.getDynamicValue(thread)) {
            return sksi_sks_interaction.cached_get_result_set_from_sql_source(sql, access_path);
        }
        return sksi_sks_interaction.get_result_set_from_sql_source(sql, access_path);
    }

    /**
     * Uses a web page as the knowledge source.
     */
    @LispMethod(comment = "Uses a web page as the knowledge source.")
    public static final SubLObject generate_web_page_iterator_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_web_page_result_set(csql, access_path, UNPROVIDED);
            return iteration.new_list_iterator(result_set);
        }
    }

    /**
     * Uses a web page as the knowledge source.
     */
    @LispMethod(comment = "Uses a web page as the knowledge source.")
    public static SubLObject generate_web_page_iterator_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLObject result_set = sksi_sks_interaction.generate_web_page_result_set(csql, access_path, UNPROVIDED);
        return new_list_iterator(result_set);
    }

    /**
     * Uses a web page as the knowledge source
     */
    @LispMethod(comment = "Uses a web page as the knowledge source")
    public static final SubLObject generate_web_page_boolean_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_web_page_result_set(csql, access_path, T);
            return list_utilities.sublisp_boolean(result_set);
        }
    }

    /**
     * Uses a web page as the knowledge source
     */
    @LispMethod(comment = "Uses a web page as the knowledge source")
    public static SubLObject generate_web_page_boolean_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLObject result_set = sksi_sks_interaction.generate_web_page_result_set(csql, access_path, T);
        return sublisp_boolean(result_set);
    }

    public static final SubLObject generate_web_page_result_set_alt(SubLObject csql, SubLObject access_path, SubLObject use_boolean_if_availableP) {
        if (use_boolean_if_availableP == UNPROVIDED) {
            use_boolean_if_availableP = T;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = csql;
                SubLObject current = datum;
                SubLObject select_keyword = NIL;
                SubLObject csql_fields = NIL;
                SubLObject tables = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt19);
                select_keyword = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt19);
                csql_fields = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt19);
                tables = current.first();
                current = current.rest();
                {
                    SubLObject where = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                    destructuring_bind_must_listp(current, datum, $list_alt19);
                    current = current.rest();
                    if (NIL == current) {
                        {
                            SubLObject datum_3 = where;
                            SubLObject current_4 = datum_3;
                            SubLObject where_keyword = (current_4.isCons()) ? ((SubLObject) (current_4.first())) : NIL;
                            destructuring_bind_must_listp(current_4, datum_3, $list_alt20);
                            current_4 = current_4.rest();
                            {
                                SubLObject where_clauses = (current_4.isCons()) ? ((SubLObject) (current_4.first())) : NIL;
                                destructuring_bind_must_listp(current_4, datum_3, $list_alt20);
                                current_4 = current_4.rest();
                                if (NIL == current_4) {
                                    {
                                        SubLObject query_template = sksi_access_path.access_path_query_template(access_path);
                                        SubLObject query = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_query_template_to_query(query_template, where_clauses, sksi_access_path.access_path_sks(access_path));
                                        SubLObject physical_field_names = sksi_access_path.access_path_physical_field_names(access_path);
                                        thread.resetMultipleValues();
                                        {
                                            SubLObject where_pattern = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_query_patterns(physical_field_names, csql_fields, where_clauses);
                                            SubLObject field_positions = thread.secondMultipleValue();
                                            thread.resetMultipleValues();
                                            {
                                                SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_page_result_set(access_path, query, use_boolean_if_availableP, where_pattern);
                                                SubLObject filtered_result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_filter_result_set(result_set, where_pattern, field_positions);
                                                return filtered_result_set;
                                            }
                                        }
                                    }
                                } else {
                                    cdestructuring_bind_error(datum_3, $list_alt20);
                                }
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt19);
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject generate_web_page_result_set(final SubLObject csql, final SubLObject access_path, SubLObject use_boolean_if_availableP) {
        if (use_boolean_if_availableP == UNPROVIDED) {
            use_boolean_if_availableP = T;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject select_keyword = NIL;
        SubLObject csql_fields = NIL;
        SubLObject tables = NIL;
        destructuring_bind_must_consp(csql, csql, sksi_sks_interaction.$list22);
        select_keyword = csql.first();
        SubLObject current = csql.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list22);
        csql_fields = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list22);
        tables = current.first();
        current = current.rest();
        final SubLObject where = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, csql, sksi_sks_interaction.$list22);
        current = current.rest();
        if (NIL == current) {
            SubLObject current_$4;
            final SubLObject datum_$3 = current_$4 = where;
            final SubLObject where_keyword = (current_$4.isCons()) ? current_$4.first() : NIL;
            destructuring_bind_must_listp(current_$4, datum_$3, sksi_sks_interaction.$list23);
            current_$4 = current_$4.rest();
            final SubLObject where_clauses = (current_$4.isCons()) ? current_$4.first() : NIL;
            destructuring_bind_must_listp(current_$4, datum_$3, sksi_sks_interaction.$list23);
            current_$4 = current_$4.rest();
            if (NIL == current_$4) {
                final SubLObject query_template = sksi_access_path.access_path_query_template(access_path);
                final SubLObject query = sksi_sks_interaction.sksi_web_query_template_to_query(query_template, where_clauses, sksi_access_path.access_path_sks(access_path));
                final SubLObject physical_field_names = sksi_access_path.access_path_physical_field_names(access_path);
                thread.resetMultipleValues();
                final SubLObject where_pattern = sksi_sks_interaction.sksi_web_query_patterns(physical_field_names, csql_fields, where_clauses);
                final SubLObject field_positions = thread.secondMultipleValue();
                thread.resetMultipleValues();
                final SubLObject result_set = sksi_sks_interaction.sksi_web_page_result_set(access_path, query, use_boolean_if_availableP, where_pattern);
                final SubLObject filtered_result_set = sksi_sks_interaction.sksi_filter_result_set(result_set, where_pattern, field_positions);
                return filtered_result_set;
            }
            cdestructuring_bind_error(datum_$3, sksi_sks_interaction.$list23);
        } else {
            cdestructuring_bind_error(csql, sksi_sks_interaction.$list22);
        }
        return NIL;
    }

    public static final SubLObject sksi_web_query_template_to_query_alt(SubLObject raw_query_template, SubLObject where_clauses, SubLObject sks) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject query_template = raw_query_template;
                SubLObject relevant_physical_field_indexicals = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(sksi_kb_accessors.sk_source_mapping_mt(sks));
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        relevant_physical_field_indexicals = list_utilities.tree_gather(raw_query_template, PHYSICAL_FIELD_INDEXICAL_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                        {
                            SubLObject cdolist_list_var = relevant_physical_field_indexicals;
                            SubLObject relevant_physical_field_indexical = NIL;
                            for (relevant_physical_field_indexical = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , relevant_physical_field_indexical = cdolist_list_var.first()) {
                                {
                                    SubLObject physical_field_indexical_name = sksi_kb_accessors.physical_field_indexical_name(relevant_physical_field_indexical);
                                    SubLObject cdolist_list_var_5 = where_clauses;
                                    SubLObject where_clause = NIL;
                                    for (where_clause = cdolist_list_var_5.first(); NIL != cdolist_list_var_5; cdolist_list_var_5 = cdolist_list_var_5.rest() , where_clause = cdolist_list_var_5.first()) {
                                        {
                                            SubLObject datum = where_clause;
                                            SubLObject current = datum;
                                            SubLObject where_operator = NIL;
                                            destructuring_bind_must_consp(current, datum, $list_alt22);
                                            where_operator = current.first();
                                            current = current.rest();
                                            destructuring_bind_must_consp(current, datum, $list_alt22);
                                            {
                                                SubLObject temp = current.rest();
                                                current = current.first();
                                                {
                                                    SubLObject field_type = NIL;
                                                    SubLObject field_name = NIL;
                                                    SubLObject field_table = NIL;
                                                    destructuring_bind_must_consp(current, datum, $list_alt22);
                                                    field_type = current.first();
                                                    current = current.rest();
                                                    destructuring_bind_must_consp(current, datum, $list_alt22);
                                                    field_name = current.first();
                                                    current = current.rest();
                                                    destructuring_bind_must_consp(current, datum, $list_alt22);
                                                    field_table = current.first();
                                                    current = current.rest();
                                                    if (NIL == current) {
                                                        current = temp;
                                                        {
                                                            SubLObject value = NIL;
                                                            destructuring_bind_must_consp(current, datum, $list_alt22);
                                                            value = current.first();
                                                            current = current.rest();
                                                            if (NIL == current) {
                                                                if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                                                    if ($$CSQLStrictlyEquals != where_operator) {
                                                                        Errors.error($str_alt24$time_to_support__S_operator, where_operator);
                                                                    }
                                                                }
                                                                if (field_name.equal(physical_field_indexical_name)) {
                                                                    query_template = subst(value, relevant_physical_field_indexical, query_template, UNPROVIDED, UNPROVIDED);
                                                                }
                                                            } else {
                                                                cdestructuring_bind_error(datum, $list_alt22);
                                                            }
                                                        }
                                                    } else {
                                                        cdestructuring_bind_error(datum, $list_alt22);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        {
                            SubLObject relevant_physical_field_indexicals_6 = list_utilities.tree_gather(query_template, PHYSICAL_FIELD_INDEXICAL_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            SubLObject cdolist_list_var = relevant_physical_field_indexicals_6;
                            SubLObject relevant_physical_field_indexical = NIL;
                            for (relevant_physical_field_indexical = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , relevant_physical_field_indexical = cdolist_list_var.first()) {
                                {
                                    SubLObject current_physical_field = sksi_kb_accessors.physical_field_for_indexical(relevant_physical_field_indexical);
                                    SubLObject default_value = sksi_kb_accessors.physical_field_default_value(current_physical_field, sksi_kb_accessors.physical_field_schema(current_physical_field), UNPROVIDED);
                                    if (NIL == default_value) {
                                        Errors.error($str_alt25$No_default_value_provided_for__A, current_physical_field);
                                    }
                                    query_template = subst(default_value, relevant_physical_field_indexical, query_template, UNPROVIDED, UNPROVIDED);
                                }
                            }
                        }
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                {
                    SubLObject query = NIL;
                    if (NIL != query_template) {
                        query = relation_evaluation.cyc_evaluate(query_template);
                    }
                    return query;
                }
            }
        }
    }

    public static SubLObject sksi_web_query_template_to_query(final SubLObject raw_query_template, final SubLObject where_clauses, final SubLObject sks) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject query_template = raw_query_template;
        SubLObject relevant_physical_field_indexicals = NIL;
        final SubLObject mt_var = with_inference_mt_relevance_validate(sksi_kb_accessors.sk_source_mapping_mt(sks));
        final SubLObject _prev_bind_0 = $mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $relevant_mts$.currentBinding(thread);
        try {
            $mt$.bind(update_inference_mt_relevance_mt(mt_var), thread);
            $relevant_mt_function$.bind(update_inference_mt_relevance_function(mt_var), thread);
            $relevant_mts$.bind(update_inference_mt_relevance_mt_list(mt_var), thread);
            SubLObject cdolist_list_var;
            relevant_physical_field_indexicals = cdolist_list_var = tree_gather(raw_query_template, sksi_sks_interaction.PHYSICAL_FIELD_INDEXICAL_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject relevant_physical_field_indexical = NIL;
            relevant_physical_field_indexical = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject physical_field_indexical_name = sksi_kb_accessors.physical_field_indexical_name(relevant_physical_field_indexical);
                SubLObject cdolist_list_var_$5 = where_clauses;
                SubLObject where_clause = NIL;
                where_clause = cdolist_list_var_$5.first();
                while (NIL != cdolist_list_var_$5) {
                    SubLObject current;
                    final SubLObject datum = current = where_clause;
                    SubLObject where_operator = NIL;
                    destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list25);
                    where_operator = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list25);
                    final SubLObject temp = current.rest();
                    current = current.first();
                    SubLObject field_type = NIL;
                    SubLObject field_name = NIL;
                    SubLObject field_table = NIL;
                    destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list25);
                    field_type = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list25);
                    field_name = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list25);
                    field_table = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        SubLObject value = NIL;
                        destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list25);
                        value = current.first();
                        current = current.rest();
                        if (NIL == current) {
                            if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (!sksi_sks_interaction.$$CSQLStrictlyEquals.eql(where_operator))) {
                                Errors.error(sksi_sks_interaction.$str27$time_to_support__S_operator, where_operator);
                            }
                            if (field_name.equal(physical_field_indexical_name)) {
                                query_template = subst(value, relevant_physical_field_indexical, query_template, UNPROVIDED, UNPROVIDED);
                            }
                        } else {
                            cdestructuring_bind_error(datum, sksi_sks_interaction.$list25);
                        }
                    } else {
                        cdestructuring_bind_error(datum, sksi_sks_interaction.$list25);
                    }
                    cdolist_list_var_$5 = cdolist_list_var_$5.rest();
                    where_clause = cdolist_list_var_$5.first();
                } 
                cdolist_list_var = cdolist_list_var.rest();
                relevant_physical_field_indexical = cdolist_list_var.first();
            } 
            SubLObject cdolist_list_var2;
            final SubLObject relevant_physical_field_indexicals_$6 = cdolist_list_var2 = tree_gather(query_template, sksi_sks_interaction.PHYSICAL_FIELD_INDEXICAL_P, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            SubLObject relevant_physical_field_indexical2 = NIL;
            relevant_physical_field_indexical2 = cdolist_list_var2.first();
            while (NIL != cdolist_list_var2) {
                final SubLObject current_physical_field = sksi_kb_accessors.physical_field_for_indexical(relevant_physical_field_indexical2);
                final SubLObject default_value = sksi_kb_accessors.physical_field_default_value(current_physical_field, sksi_kb_accessors.physical_field_schema(current_physical_field), UNPROVIDED);
                if (NIL == default_value) {
                    Errors.error(sksi_sks_interaction.$str28$No_default_value_provided_for__A, current_physical_field);
                }
                query_template = subst(default_value, relevant_physical_field_indexical2, query_template, UNPROVIDED, UNPROVIDED);
                cdolist_list_var2 = cdolist_list_var2.rest();
                relevant_physical_field_indexical2 = cdolist_list_var2.first();
            } 
        } finally {
            $relevant_mts$.rebind(_prev_bind_3, thread);
            $relevant_mt_function$.rebind(_prev_bind_2, thread);
            $mt$.rebind(_prev_bind_0, thread);
        }
        SubLObject query = NIL;
        if (NIL != query_template) {
            query = cyc_evaluate(query_template);
        }
        return query;
    }

    public static final SubLObject sksi_web_query_patterns_alt(SubLObject physical_field_names, SubLObject csql_fields, SubLObject where_clauses) {
        {
            SubLObject where_pattern = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_query_where_pattern(physical_field_names, where_clauses);
            SubLObject field_positions = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_query_field_positions(physical_field_names, csql_fields);
            return values(where_pattern, field_positions);
        }
    }

    public static SubLObject sksi_web_query_patterns(final SubLObject physical_field_names, final SubLObject csql_fields, final SubLObject where_clauses) {
        final SubLObject where_pattern = sksi_sks_interaction.sksi_web_query_where_pattern(physical_field_names, where_clauses);
        final SubLObject field_positions = sksi_sks_interaction.sksi_web_query_field_positions(physical_field_names, csql_fields);
        return values(where_pattern, field_positions);
    }

    public static final SubLObject sksi_web_query_where_pattern_alt(SubLObject physical_field_names, SubLObject where_clauses) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL == where_clauses) {
                return $INPUT;
            }
            {
                SubLObject where_pattern = NIL;
                SubLObject cdolist_list_var = physical_field_names;
                SubLObject field_name = NIL;
                for (field_name = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , field_name = cdolist_list_var.first()) {
                    {
                        SubLObject filter_conditions = NIL;
                        SubLObject cdolist_list_var_7 = where_clauses;
                        SubLObject where_clause = NIL;
                        for (where_clause = cdolist_list_var_7.first(); NIL != cdolist_list_var_7; cdolist_list_var_7 = cdolist_list_var_7.rest() , where_clause = cdolist_list_var_7.first()) {
                            {
                                SubLObject datum = where_clause;
                                SubLObject current = datum;
                                SubLObject where_operator = NIL;
                                destructuring_bind_must_consp(current, datum, $list_alt27);
                                where_operator = current.first();
                                current = current.rest();
                                destructuring_bind_must_consp(current, datum, $list_alt27);
                                {
                                    SubLObject temp = current.rest();
                                    current = current.first();
                                    {
                                        SubLObject where_field_type = NIL;
                                        SubLObject where_field_name = NIL;
                                        SubLObject where_table = NIL;
                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                        where_field_type = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                        where_field_name = current.first();
                                        current = current.rest();
                                        destructuring_bind_must_consp(current, datum, $list_alt27);
                                        where_table = current.first();
                                        current = current.rest();
                                        if (NIL == current) {
                                            current = temp;
                                            {
                                                SubLObject value = NIL;
                                                destructuring_bind_must_consp(current, datum, $list_alt27);
                                                value = current.first();
                                                current = current.rest();
                                                if (NIL == current) {
                                                    if (field_name.equal(where_field_name)) {
                                                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                                                            if ($$CSQLStrictlyEquals != where_operator) {
                                                                Errors.error($str_alt24$time_to_support__S_operator, where_operator);
                                                            }
                                                        }
                                                        if (where_field_name.equal(field_name)) {
                                                            filter_conditions = cons(value, filter_conditions);
                                                        }
                                                    }
                                                } else {
                                                    cdestructuring_bind_error(datum, $list_alt27);
                                                }
                                            }
                                        } else {
                                            cdestructuring_bind_error(datum, $list_alt27);
                                        }
                                    }
                                }
                            }
                        }
                        filter_conditions = nreverse(filter_conditions);
                        if (NIL == filter_conditions) {
                            where_pattern = cons($ANYTHING, where_pattern);
                        } else {
                            if (NIL != list_utilities.singletonP(filter_conditions)) {
                                where_pattern = cons(filter_conditions.first(), where_pattern);
                            } else {
                                where_pattern = cons(listS($$CSQLAnd, filter_conditions), where_pattern);
                            }
                        }
                    }
                }
                return nreverse(where_pattern);
            }
        }
    }

    public static SubLObject sksi_web_query_where_pattern(final SubLObject physical_field_names, final SubLObject where_clauses) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL == where_clauses) {
            return $INPUT;
        }
        SubLObject where_pattern = NIL;
        SubLObject cdolist_list_var = physical_field_names;
        SubLObject field_name = NIL;
        field_name = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject filter_conditions = NIL;
            SubLObject cdolist_list_var_$7 = where_clauses;
            SubLObject where_clause = NIL;
            where_clause = cdolist_list_var_$7.first();
            while (NIL != cdolist_list_var_$7) {
                SubLObject current;
                final SubLObject datum = current = where_clause;
                SubLObject where_operator = NIL;
                destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list30);
                where_operator = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list30);
                final SubLObject temp = current.rest();
                current = current.first();
                SubLObject where_field_type = NIL;
                SubLObject where_field_name = NIL;
                SubLObject where_table = NIL;
                destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list30);
                where_field_type = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list30);
                where_field_name = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list30);
                where_table = current.first();
                current = current.rest();
                if (NIL == current) {
                    current = temp;
                    SubLObject value = NIL;
                    destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list30);
                    value = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        if (field_name.equal(where_field_name)) {
                            if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && (!sksi_sks_interaction.$$CSQLStrictlyEquals.eql(where_operator))) {
                                Errors.error(sksi_sks_interaction.$str27$time_to_support__S_operator, where_operator);
                            }
                            if (where_field_name.equal(field_name)) {
                                filter_conditions = cons(value, filter_conditions);
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, sksi_sks_interaction.$list30);
                    }
                } else {
                    cdestructuring_bind_error(datum, sksi_sks_interaction.$list30);
                }
                cdolist_list_var_$7 = cdolist_list_var_$7.rest();
                where_clause = cdolist_list_var_$7.first();
            } 
            filter_conditions = nreverse(filter_conditions);
            if (NIL == filter_conditions) {
                where_pattern = cons($ANYTHING, where_pattern);
            } else
                if (NIL != singletonP(filter_conditions)) {
                    where_pattern = cons(filter_conditions.first(), where_pattern);
                } else {
                    where_pattern = cons(listS(sksi_sks_interaction.$$CSQLAnd, filter_conditions), where_pattern);
                }

            cdolist_list_var = cdolist_list_var.rest();
            field_name = cdolist_list_var.first();
        } 
        return nreverse(where_pattern);
    }

    public static final SubLObject sksi_web_query_field_positions_alt(SubLObject physical_field_names, SubLObject csql_fields) {
        if (NIL != sksi_csql_utilities.boolean_csql_selectP(csql_fields)) {
            return NIL;
        }
        {
            SubLObject positions = NIL;
            SubLObject cdolist_list_var = csql_fields;
            SubLObject field = NIL;
            for (field = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , field = cdolist_list_var.first()) {
                {
                    SubLObject datum = field;
                    SubLObject current = datum;
                    SubLObject field_keyword = NIL;
                    SubLObject field_name = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt30);
                    field_keyword = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt30);
                    field_name = current.first();
                    current = current.rest();
                    {
                        SubLObject table = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                        destructuring_bind_must_listp(current, datum, $list_alt30);
                        current = current.rest();
                        if (NIL == current) {
                            {
                                SubLObject position = position(field_name, physical_field_names, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                                SubLTrampolineFile.checkType(position, NON_NEGATIVE_INTEGER_P);
                                positions = cons(position, positions);
                            }
                        } else {
                            cdestructuring_bind_error(datum, $list_alt30);
                        }
                    }
                }
            }
            return nreverse(positions);
        }
    }

    public static SubLObject sksi_web_query_field_positions(final SubLObject physical_field_names, final SubLObject csql_fields) {
        if (NIL != sksi_csql_utilities.boolean_csql_selectP(csql_fields)) {
            return NIL;
        }
        SubLObject positions = NIL;
        SubLObject cdolist_list_var = csql_fields;
        SubLObject field = NIL;
        field = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            SubLObject current;
            final SubLObject datum = current = field;
            SubLObject field_keyword = NIL;
            SubLObject field_name = NIL;
            destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list33);
            field_keyword = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list33);
            field_name = current.first();
            current = current.rest();
            final SubLObject table = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, sksi_sks_interaction.$list33);
            current = current.rest();
            final SubLObject namespace = (current.isCons()) ? current.first() : NIL;
            destructuring_bind_must_listp(current, datum, sksi_sks_interaction.$list33);
            current = current.rest();
            if (NIL == current) {
                final SubLObject position = position(field_name, physical_field_names, symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED);
                assert NIL != subl_promotions.non_negative_integer_p(position) : "! subl_promotions.non_negative_integer_p(position) " + ("subl_promotions.non_negative_integer_p(position) " + "CommonSymbols.NIL != subl_promotions.non_negative_integer_p(position) ") + position;
                positions = cons(position, positions);
            } else {
                cdestructuring_bind_error(datum, sksi_sks_interaction.$list33);
            }
            cdolist_list_var = cdolist_list_var.rest();
            field = cdolist_list_var.first();
        } 
        return nreverse(positions);
    }

    public static final SubLObject sksi_web_page_result_set_alt(SubLObject access_path, SubLObject query, SubLObject use_boolean_if_availableP, SubLObject where_pattern) {
        if (use_boolean_if_availableP == UNPROVIDED) {
            use_boolean_if_availableP = NIL;
        }
        if (where_pattern == UNPROVIDED) {
            where_pattern = NIL;
        }
        {
            SubLObject server = sksi_access_path.access_path_server(access_path);
            SubLObject port = sksi_access_path.access_path_port(access_path);
            SubLObject method = sksi_access_path.access_path_request_method(access_path);
            SubLObject url = sksi_access_path.access_path_url(access_path);
            SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
            SubLObject connection_data = list(server, port, method, url, timeout, query);
            SubLObject parsing_method = sksi_access_path.access_path_parsing_method(access_path);
            SubLObject parsing_program = sksi_access_path.access_path_parsing_program(access_path);
            if (NIL != use_boolean_if_availableP) {
                {
                    SubLObject pcase_var = parsing_method;
                    if (pcase_var.eql($SUBLBOOLEAN)) {
                        return NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_subl_parsing_function(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program) ? ((SubLObject) (list(where_pattern))) : NIL;
                    } else {
                        if (pcase_var.eql($SUBL)) {
                            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_subl_parsing_function(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program);
                        } else {
                            if (pcase_var.eql($SUBLUNBOUNDONLY)) {
                                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_page_result_set_for_unbound_only(access_path, connection_data, parsing_program, where_pattern);
                            } else {
                                Errors.error($str_alt35$Parsing_method__S_is_not_yet_supp, parsing_method);
                            }
                        }
                    }
                }
            } else {
                if (parsing_method == $SUBLUNBOUNDONLY) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_page_result_set_for_unbound_only(access_path, connection_data, parsing_program, where_pattern);
                } else {
                    if (parsing_method == $SUBL) {
                        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_subl_parsing_function(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program);
                    } else {
                        Errors.error($str_alt35$Parsing_method__S_is_not_yet_supp, parsing_method);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_web_page_result_set(final SubLObject access_path, final SubLObject query, SubLObject use_boolean_if_availableP, SubLObject where_pattern) {
        if (use_boolean_if_availableP == UNPROVIDED) {
            use_boolean_if_availableP = NIL;
        }
        if (where_pattern == UNPROVIDED) {
            where_pattern = NIL;
        }
        final SubLObject server = sksi_access_path.access_path_server(access_path);
        final SubLObject port = sksi_access_path.access_path_port(access_path);
        final SubLObject method = sksi_access_path.access_path_request_method(access_path);
        final SubLObject url = sksi_access_path.access_path_url(access_path);
        final SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
        final SubLObject connection_data = list(server, port, method, url, timeout, query);
        final SubLObject parsing_method = sksi_access_path.access_path_parsing_method(access_path);
        final SubLObject parsing_program = sksi_access_path.access_path_parsing_program(access_path);
        if (NIL != use_boolean_if_availableP) {
            final SubLObject pcase_var = parsing_method;
            if (pcase_var.eql($SUBLBOOLEAN)) {
                return NIL != sksi_sks_interaction.get_results_from_subl_parsing_function(sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program) ? list(where_pattern) : NIL;
            }
            if (pcase_var.eql($SUBL)) {
                return sksi_sks_interaction.get_results_from_subl_parsing_function(sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program);
            }
            if (pcase_var.eql($SUBLUNBOUNDONLY)) {
                return sksi_sks_interaction.sksi_web_page_result_set_for_unbound_only(access_path, connection_data, parsing_program, where_pattern);
            }
            Errors.error(sksi_sks_interaction.$str38$Parsing_method__S_is_not_yet_supp, parsing_method);
        } else {
            if (parsing_method == $SUBLUNBOUNDONLY) {
                return sksi_sks_interaction.sksi_web_page_result_set_for_unbound_only(access_path, connection_data, parsing_program, where_pattern);
            }
            if (parsing_method == $SUBL) {
                return sksi_sks_interaction.get_results_from_subl_parsing_function(sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program);
            }
            Errors.error(sksi_sks_interaction.$str38$Parsing_method__S_is_not_yet_supp, parsing_method);
        }
        return NIL;
    }

    public static final SubLObject sksi_web_page_result_set_for_unbound_only_alt(SubLObject access_path, SubLObject connection_data, SubLObject parsing_program, SubLObject where_pattern) {
        {
            SubLObject results = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_subl_parsing_function(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program);
            SubLObject final_result = NIL;
            SubLObject cdolist_list_var = results;
            SubLObject result = NIL;
            for (result = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , result = cdolist_list_var.first()) {
                final_result = cons(list_utilities.nreplace_nested_nth(where_pattern, list(result.first()), cadr(result)), final_result);
            }
            return final_result;
        }
    }

    public static SubLObject sksi_web_page_result_set_for_unbound_only(final SubLObject access_path, final SubLObject connection_data, final SubLObject parsing_program, final SubLObject where_pattern) {
        final SubLObject results = sksi_sks_interaction.get_results_from_subl_parsing_function(sksi_sks_interaction.get_results_from_sksi_web_page_subl_method(access_path, connection_data), parsing_program);
        SubLObject final_result = NIL;
        SubLObject cdolist_list_var = results;
        SubLObject result = NIL;
        result = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            final_result = cons(nreplace_nested_nth(where_pattern, list(result.first()), cadr(result)), final_result);
            cdolist_list_var = cdolist_list_var.rest();
            result = cdolist_list_var.first();
        } 
        return final_result;
    }

    /**
     * Utility to test the result set generation for web page SK-SOURCE
     * given QUERY, as described in MAPPING-MT.
     */
    @LispMethod(comment = "Utility to test the result set generation for web page SK-SOURCE\r\ngiven QUERY, as described in MAPPING-MT.\nUtility to test the result set generation for web page SK-SOURCE\ngiven QUERY, as described in MAPPING-MT.")
    public static final SubLObject test_sksi_web_page_result_set_alt(SubLObject sk_source, SubLObject query, SubLObject mapping_mt) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject access_path = NIL;
                SubLObject mt_var = mt_relevance_macros.with_inference_mt_relevance_validate(mapping_mt);
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$mt$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_2 = mt_relevance_macros.$relevant_mts$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.update_inference_mt_relevance_mt(mt_var), thread);
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.update_inference_mt_relevance_function(mt_var), thread);
                        mt_relevance_macros.$relevant_mts$.bind(mt_relevance_macros.update_inference_mt_relevance_mt_list(mt_var), thread);
                        access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
                    } finally {
                        mt_relevance_macros.$relevant_mts$.rebind(_prev_bind_2, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$mt$.rebind(_prev_bind_0, thread);
                    }
                }
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_web_page_result_set(access_path, query, UNPROVIDED, UNPROVIDED);
            }
        }
    }

    /**
     * Utility to test the result set generation for web page SK-SOURCE
     * given QUERY, as described in MAPPING-MT.
     */
    @LispMethod(comment = "Utility to test the result set generation for web page SK-SOURCE\r\ngiven QUERY, as described in MAPPING-MT.\nUtility to test the result set generation for web page SK-SOURCE\ngiven QUERY, as described in MAPPING-MT.")
    public static SubLObject test_sksi_web_page_result_set(final SubLObject sk_source, final SubLObject query, final SubLObject mapping_mt) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject access_path = NIL;
        final SubLObject mt_var = with_inference_mt_relevance_validate(mapping_mt);
        final SubLObject _prev_bind_0 = $mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $relevant_mts$.currentBinding(thread);
        try {
            $mt$.bind(update_inference_mt_relevance_mt(mt_var), thread);
            $relevant_mt_function$.bind(update_inference_mt_relevance_function(mt_var), thread);
            $relevant_mts$.bind(update_inference_mt_relevance_mt_list(mt_var), thread);
            access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
        } finally {
            $relevant_mts$.rebind(_prev_bind_3, thread);
            $relevant_mt_function$.rebind(_prev_bind_2, thread);
            $mt$.rebind(_prev_bind_0, thread);
        }
        return sksi_sks_interaction.sksi_web_page_result_set(access_path, query, UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject get_results_from_sksi_web_page_subl_method_alt(SubLObject access_path, SubLObject connection_data) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject pcase_var = $sksi_http_request_caching_strategy$.getDynamicValue(thread);
                if (pcase_var.eql($MEMOIZE)) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_memoized(access_path, connection_data);
                } else {
                    if (pcase_var.eql($CACHE)) {
                        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_cached(access_path, connection_data);
                    } else {
                        if (pcase_var.eql($NONE)) {
                            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_none(access_path, connection_data);
                        } else {
                            Errors.error($str_alt38$_S_not_a_valid_value_for__sksi_ht, $sksi_http_request_caching_strategy$.getDynamicValue(thread));
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject get_results_from_sksi_web_page_subl_method(final SubLObject access_path, final SubLObject connection_data) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject pcase_var = sksi_sks_interaction.$sksi_http_request_caching_strategy$.getDynamicValue(thread);
        if (pcase_var.eql($MEMOIZE)) {
            return sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_memoized(access_path, connection_data);
        }
        if (pcase_var.eql($CACHE)) {
            return sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_cached(access_path, connection_data);
        }
        if (pcase_var.eql($NONE)) {
            return sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_none(access_path, connection_data);
        }
        Errors.error(sksi_sks_interaction.$str41$_S_not_a_valid_value_for__sksi_ht, sksi_sks_interaction.$sksi_http_request_caching_strategy$.getDynamicValue(thread));
        return NIL;
    }

    public static final SubLObject get_results_from_sksi_web_page_subl_method_memoized_internal_alt(SubLObject access_path, SubLObject connection_data) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_int(access_path, connection_data);
    }

    public static SubLObject get_results_from_sksi_web_page_subl_method_memoized_internal(final SubLObject access_path, final SubLObject connection_data) {
        return sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_int(access_path, connection_data);
    }

    public static final SubLObject get_results_from_sksi_web_page_subl_method_memoized_alt(SubLObject access_path, SubLObject connection_data) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_memoized_internal(access_path, connection_data);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED, TWO_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_2(access_path, connection_data);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw40$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (access_path.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && connection_data.equal(cached_args.first())) {
                                            return memoization_state.caching_results(results2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_memoized_internal(access_path, connection_data)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(access_path, connection_data));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject get_results_from_sksi_web_page_subl_method_memoized(final SubLObject access_path, final SubLObject connection_data) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = $memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_memoized_internal(access_path, connection_data);
        }
        caching_state = memoization_state_lookup(v_memoization_state, sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = create_caching_state(memoization_state_lock(v_memoization_state), sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED, TWO_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state_put(v_memoization_state, sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED, caching_state);
        }
        final SubLObject sxhash = sxhash_calc_2(access_path, connection_data);
        final SubLObject collisions = caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql($memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (access_path.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && connection_data.equal(cached_args.first())) {
                        return caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_memoized_internal(access_path, connection_data)));
        caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(access_path, connection_data));
        return caching_results(results3);
    }

    public static final SubLObject clear_get_results_from_sksi_web_page_subl_method_cached_alt() {
        {
            SubLObject cs = $get_results_from_sksi_web_page_subl_method_cached_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_get_results_from_sksi_web_page_subl_method_cached() {
        final SubLObject cs = sksi_sks_interaction.$get_results_from_sksi_web_page_subl_method_cached_caching_state$.getGlobalValue();
        if (NIL != cs) {
            caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_get_results_from_sksi_web_page_subl_method_cached_alt(SubLObject access_path, SubLObject connection_data) {
        return memoization_state.caching_state_remove_function_results_with_args($get_results_from_sksi_web_page_subl_method_cached_caching_state$.getGlobalValue(), list(access_path, connection_data), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_get_results_from_sksi_web_page_subl_method_cached(final SubLObject access_path, final SubLObject connection_data) {
        return caching_state_remove_function_results_with_args(sksi_sks_interaction.$get_results_from_sksi_web_page_subl_method_cached_caching_state$.getGlobalValue(), list(access_path, connection_data), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject get_results_from_sksi_web_page_subl_method_cached_internal_alt(SubLObject access_path, SubLObject connection_data) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_int(access_path, connection_data);
    }

    public static SubLObject get_results_from_sksi_web_page_subl_method_cached_internal(final SubLObject access_path, final SubLObject connection_data) {
        return sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_int(access_path, connection_data);
    }

    public static final SubLObject get_results_from_sksi_web_page_subl_method_cached_alt(SubLObject access_path, SubLObject connection_data) {
        {
            SubLObject caching_state = $get_results_from_sksi_web_page_subl_method_cached_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_CACHED, $get_results_from_sksi_web_page_subl_method_cached_caching_state$, NIL, EQUAL, TWO_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(access_path, connection_data);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw40$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (access_path.equal(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && connection_data.equal(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_cached_internal(access_path, connection_data)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(access_path, connection_data));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject get_results_from_sksi_web_page_subl_method_cached(final SubLObject access_path, final SubLObject connection_data) {
        SubLObject caching_state = sksi_sks_interaction.$get_results_from_sksi_web_page_subl_method_cached_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = create_global_caching_state_for_name(sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_CACHED, sksi_sks_interaction.$get_results_from_sksi_web_page_subl_method_cached_caching_state$, NIL, EQUAL, TWO_INTEGER, ZERO_INTEGER);
        }
        final SubLObject sxhash = sxhash_calc_2(access_path, connection_data);
        final SubLObject collisions = caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql($memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (access_path.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && connection_data.equal(cached_args.first())) {
                        return caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_cached_internal(access_path, connection_data)));
        caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(access_path, connection_data));
        return caching_results(results3);
    }

    public static final SubLObject get_results_from_sksi_web_page_subl_method_none_alt(SubLObject access_path, SubLObject connection_data) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_int(access_path, connection_data);
    }

    public static SubLObject get_results_from_sksi_web_page_subl_method_none(final SubLObject access_path, final SubLObject connection_data) {
        return sksi_sks_interaction.get_results_from_sksi_web_page_subl_method_int(access_path, connection_data);
    }

    public static final SubLObject get_results_from_sksi_web_page_subl_method_int_alt(SubLObject access_path, SubLObject connection_data) {
        {
            SubLObject datum = connection_data;
            SubLObject current = datum;
            SubLObject server = NIL;
            SubLObject port = NIL;
            SubLObject method = NIL;
            SubLObject url = NIL;
            SubLObject timeout = NIL;
            SubLObject query = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt43);
            server = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt43);
            port = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt43);
            method = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt43);
            url = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt43);
            timeout = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt43);
            query = current.first();
            current = current.rest();
            if (NIL == current) {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_tokenized_http_request(access_path, server, url, timeout, query, method, port);
            } else {
                cdestructuring_bind_error(datum, $list_alt43);
            }
        }
        return NIL;
    }

    public static SubLObject get_results_from_sksi_web_page_subl_method_int(final SubLObject access_path, final SubLObject connection_data) {
        SubLObject server = NIL;
        SubLObject port = NIL;
        SubLObject method = NIL;
        SubLObject url = NIL;
        SubLObject timeout = NIL;
        SubLObject query = NIL;
        destructuring_bind_must_consp(connection_data, connection_data, sksi_sks_interaction.$list45);
        server = connection_data.first();
        SubLObject current = connection_data.rest();
        destructuring_bind_must_consp(current, connection_data, sksi_sks_interaction.$list45);
        port = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, connection_data, sksi_sks_interaction.$list45);
        method = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, connection_data, sksi_sks_interaction.$list45);
        url = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, connection_data, sksi_sks_interaction.$list45);
        timeout = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, connection_data, sksi_sks_interaction.$list45);
        query = current.first();
        current = current.rest();
        if (NIL == current) {
            return sksi_sks_interaction.sksi_tokenized_http_request(access_path, server, url, timeout, query, method, port);
        }
        cdestructuring_bind_error(connection_data, sksi_sks_interaction.$list45);
        return NIL;
    }

    public static final SubLObject sksi_tokenized_http_request_internal_alt(SubLObject access_path, SubLObject machine, SubLObject url, SubLObject connection_timeout, SubLObject query, SubLObject method, SubLObject port) {
        if (connection_timeout == UNPROVIDED) {
            connection_timeout = NIL;
        }
        if (query == UNPROVIDED) {
            query = NIL;
        }
        if (method == UNPROVIDED) {
            method = $GET;
        }
        if (port == UNPROVIDED) {
            port = $int$80;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject support_url = cconcatenate($str_alt48$http___, new SubLObject[]{ machine, url });
                SubLObject cyc_query = inference_worker.currently_active_problem_query();
                if (NIL != inference_datastructures_problem_query.single_literal_problem_query_p(cyc_query)) {
                    cyc_query = inference_datastructures_problem_query.single_literal_problem_query_atomic_sentence(cyc_query);
                    if (query.isString()) {
                        support_url = cconcatenate(support_url, new SubLObject[]{ $str_alt49$_, query });
                    }
                    sksi_hl_support_utilities.note_sksi_support(arguments.make_hl_support($CSQL, list($$salientURL, cyc_query, support_url), UNPROVIDED, UNPROVIDED));
                }
            }
            if (NIL == connection_timeout) {
                connection_timeout = $sksi_http_request_open_connection_timeout$.getDynamicValue(thread);
            }
            thread.resetMultipleValues();
            {
                SubLObject tokens = web_utilities.xml_tokenized_http_request(machine, url, query, method, port, $sksi_http_request_keep_alive$.getDynamicValue(thread), $sksi_http_request_wide_newlines$.getDynamicValue(thread), connection_timeout, $sksi_http_request_overall_timeout$.getDynamicValue(thread), $sksi_http_automatically_redirect$.getDynamicValue(thread), UNPROVIDED);
                SubLObject status = thread.secondMultipleValue();
                SubLObject error_message = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                if (NIL != tokens) {
                    return tokens;
                }
                {
                    SubLObject pcase_var = status;
                    if (pcase_var.eql($ERROR)) {
                        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_sks_error(access_path, error_message);
                    } else {
                        if (pcase_var.eql($OPEN_CONNECTION)) {
                            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_sks_error(access_path, $str_alt53$Opening_the_TCP_connection_timed_);
                        } else {
                            if (pcase_var.eql($OVERALL)) {
                                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_sks_error(access_path, $str_alt55$The_HTTP_request_timed_out_);
                            }
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject sksi_tokenized_http_request_internal(final SubLObject access_path, final SubLObject machine, final SubLObject url, SubLObject connection_timeout, SubLObject query, SubLObject method, SubLObject port) {
        if (connection_timeout == UNPROVIDED) {
            connection_timeout = NIL;
        }
        if (query == UNPROVIDED) {
            query = NIL;
        }
        if (method == UNPROVIDED) {
            method = $GET;
        }
        if (port == UNPROVIDED) {
            port = sksi_sks_interaction.$int$80;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject support_url = cconcatenate(sksi_sks_interaction.$str50$http___, new SubLObject[]{ machine, url });
        SubLObject cyc_query = currently_active_problem_query();
        if (NIL != single_literal_problem_query_p(cyc_query)) {
            cyc_query = single_literal_problem_query_atomic_sentence(cyc_query);
            if (query.isString()) {
                support_url = cconcatenate(support_url, new SubLObject[]{ sksi_sks_interaction.$str51$_, query });
            }
            note_sksi_support(make_hl_support($CSQL, list(sksi_sks_interaction.$$salientURL, cyc_query, support_url), UNPROVIDED, UNPROVIDED));
        }
        if (NIL == connection_timeout) {
            connection_timeout = sksi_sks_interaction.$sksi_http_request_open_connection_timeout$.getDynamicValue(thread);
        }
        thread.resetMultipleValues();
        final SubLObject tokens = xml_parsing_utilities.xml_tokenized_http_request(machine, url, query, method, port, sksi_sks_interaction.$sksi_http_request_keep_alive$.getDynamicValue(thread), sksi_sks_interaction.$sksi_http_request_wide_newlines$.getDynamicValue(thread), connection_timeout, sksi_sks_interaction.$sksi_http_request_overall_timeout$.getDynamicValue(thread), sksi_sks_interaction.$sksi_http_automatically_redirect$.getDynamicValue(thread), UNPROVIDED);
        final SubLObject status = thread.secondMultipleValue();
        final SubLObject error_message = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        if (NIL != tokens) {
            return tokens;
        }
        final SubLObject pcase_var = status;
        if (pcase_var.eql($ERROR)) {
            return sksi_sks_interaction.sksi_unreachable_sks_error(access_path, error_message);
        }
        if (pcase_var.eql($OPEN_CONNECTION)) {
            return sksi_sks_interaction.sksi_unreachable_sks_error(access_path, sksi_sks_interaction.$str55$Opening_the_TCP_connection_timed_);
        }
        if (pcase_var.eql($OVERALL)) {
            return sksi_sks_interaction.sksi_unreachable_sks_error(access_path, sksi_sks_interaction.$str57$The_HTTP_request_timed_out_);
        }
        return NIL;
    }

    public static final SubLObject sksi_tokenized_http_request_alt(SubLObject access_path, SubLObject machine, SubLObject url, SubLObject connection_timeout, SubLObject query, SubLObject method, SubLObject port) {
        if (connection_timeout == UNPROVIDED) {
            connection_timeout = NIL;
        }
        if (query == UNPROVIDED) {
            query = NIL;
        }
        if (method == UNPROVIDED) {
            method = $GET;
        }
        if (port == UNPROVIDED) {
            port = $int$80;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject v_memoization_state = memoization_state.$memoization_state$.getDynamicValue(thread);
                SubLObject caching_state = NIL;
                if (NIL == v_memoization_state) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_tokenized_http_request_internal(access_path, machine, url, connection_timeout, query, method, port);
                }
                caching_state = memoization_state.memoization_state_lookup(v_memoization_state, SKSI_TOKENIZED_HTTP_REQUEST, UNPROVIDED);
                if (NIL == caching_state) {
                    caching_state = memoization_state.create_caching_state(memoization_state.memoization_state_lock(v_memoization_state), SKSI_TOKENIZED_HTTP_REQUEST, SEVEN_INTEGER, NIL, EQUAL, UNPROVIDED);
                    memoization_state.memoization_state_put(v_memoization_state, SKSI_TOKENIZED_HTTP_REQUEST, caching_state);
                }
                {
                    SubLObject sxhash = memoization_state.sxhash_calc_7(access_path, machine, url, connection_timeout, query, method, port);
                    SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                    if (collisions != $kw40$_MEMOIZED_ITEM_NOT_FOUND_) {
                        {
                            SubLObject cdolist_list_var = collisions;
                            SubLObject collision = NIL;
                            for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                                {
                                    SubLObject cached_args = collision.first();
                                    SubLObject results2 = second(collision);
                                    if (access_path.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (machine.equal(cached_args.first())) {
                                            cached_args = cached_args.rest();
                                            if (url.equal(cached_args.first())) {
                                                cached_args = cached_args.rest();
                                                if (connection_timeout.equal(cached_args.first())) {
                                                    cached_args = cached_args.rest();
                                                    if (query.equal(cached_args.first())) {
                                                        cached_args = cached_args.rest();
                                                        if (method.equal(cached_args.first())) {
                                                            cached_args = cached_args.rest();
                                                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && port.equal(cached_args.first())) {
                                                                return memoization_state.caching_results(results2);
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
                    {
                        SubLObject results = arg2(thread.resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_tokenized_http_request_internal(access_path, machine, url, connection_timeout, query, method, port)));
                        memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(access_path, machine, url, connection_timeout, query, method, port));
                        return memoization_state.caching_results(results);
                    }
                }
            }
        }
    }

    public static SubLObject sksi_tokenized_http_request(final SubLObject access_path, final SubLObject machine, final SubLObject url, SubLObject connection_timeout, SubLObject query, SubLObject method, SubLObject port) {
        if (connection_timeout == UNPROVIDED) {
            connection_timeout = NIL;
        }
        if (query == UNPROVIDED) {
            query = NIL;
        }
        if (method == UNPROVIDED) {
            method = $GET;
        }
        if (port == UNPROVIDED) {
            port = sksi_sks_interaction.$int$80;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = $memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return sksi_sks_interaction.sksi_tokenized_http_request_internal(access_path, machine, url, connection_timeout, query, method, port);
        }
        caching_state = memoization_state_lookup(v_memoization_state, sksi_sks_interaction.SKSI_TOKENIZED_HTTP_REQUEST, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = create_caching_state(memoization_state_lock(v_memoization_state), sksi_sks_interaction.SKSI_TOKENIZED_HTTP_REQUEST, SEVEN_INTEGER, NIL, EQUAL, UNPROVIDED);
            memoization_state_put(v_memoization_state, sksi_sks_interaction.SKSI_TOKENIZED_HTTP_REQUEST, caching_state);
        }
        final SubLObject sxhash = sxhash_calc_7(access_path, machine, url, connection_timeout, query, method, port);
        final SubLObject collisions = caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql($memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (access_path.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (machine.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (url.equal(cached_args.first())) {
                            cached_args = cached_args.rest();
                            if (connection_timeout.equal(cached_args.first())) {
                                cached_args = cached_args.rest();
                                if (query.equal(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (method.equal(cached_args.first())) {
                                        cached_args = cached_args.rest();
                                        if (((NIL != cached_args) && (NIL == cached_args.rest())) && port.equal(cached_args.first())) {
                                            return caching_results(results2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(thread.resetMultipleValues(), multiple_value_list(sksi_sks_interaction.sksi_tokenized_http_request_internal(access_path, machine, url, connection_timeout, query, method, port)));
        caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(access_path, machine, url, connection_timeout, query, method, port));
        return caching_results(results3);
    }

    public static final SubLObject get_results_from_subl_parsing_function_alt(SubLObject tokens, SubLObject parsing_program) {
        SubLTrampolineFile.checkType(parsing_program, SKSI_SUBL_PARSING_PROGRAM_P);
        {
            SubLObject datum = parsing_program;
            SubLObject current = datum;
            SubLObject expand_subl = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt57);
            expand_subl = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt57);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject tokens_var = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt57);
                    tokens_var = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject program_body = NIL;
                            destructuring_bind_must_consp(current, datum, $list_alt57);
                            program_body = current.first();
                            current = current.rest();
                            if (NIL == current) {
                                return eval(list(CLET, list(list(tokens_var, list(QUOTE, append(tokens, NIL)))), program_body));
                            } else {
                                cdestructuring_bind_error(datum, $list_alt57);
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt57);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject get_results_from_subl_parsing_function(final SubLObject tokens, final SubLObject parsing_program) {
        assert NIL != sksi_sks_interaction.sksi_subl_parsing_program_p(parsing_program) : "! sksi_sks_interaction.sksi_subl_parsing_program_p(parsing_program) " + ("sksi_sks_interaction.sksi_subl_parsing_program_p(parsing_program) " + "CommonSymbols.NIL != sksi_sks_interaction.sksi_subl_parsing_program_p(parsing_program) ") + parsing_program;
        SubLObject expand_subl = NIL;
        destructuring_bind_must_consp(parsing_program, parsing_program, sksi_sks_interaction.$list59);
        expand_subl = parsing_program.first();
        SubLObject current = parsing_program.rest();
        destructuring_bind_must_consp(current, parsing_program, sksi_sks_interaction.$list59);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject tokens_var = NIL;
        destructuring_bind_must_consp(current, parsing_program, sksi_sks_interaction.$list59);
        tokens_var = current.first();
        current = current.rest();
        if (NIL == current) {
            current = temp;
            SubLObject program_body = NIL;
            destructuring_bind_must_consp(current, parsing_program, sksi_sks_interaction.$list59);
            program_body = current.first();
            current = current.rest();
            if (NIL == current) {
                return eval(list(CLET, list(list(tokens_var, list(QUOTE, append(tokens, NIL)))), program_body));
            }
            cdestructuring_bind_error(parsing_program, sksi_sks_interaction.$list59);
        } else {
            cdestructuring_bind_error(parsing_program, sksi_sks_interaction.$list59);
        }
        return NIL;
    }

    public static final SubLObject sksi_subl_parsing_program_p_alt(SubLObject v_object) {
        if (NIL != possibly_naut_p(v_object)) {
            if ((NIL != formula_arityE(v_object, TWO_INTEGER, UNPROVIDED)) && ($$ExpandSubLFn == cycl_utilities.formula_operator(v_object))) {
                return T;
            } else {
                if ((NIL != formula_arityE(v_object, ONE_INTEGER, UNPROVIDED)) && ($$SubLQuoteFn == cycl_utilities.formula_operator(v_object))) {
                    return T;
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_subl_parsing_program_p(final SubLObject v_object) {
        if (NIL != possibly_naut_p(v_object)) {
            if ((NIL != formula_arityE(v_object, TWO_INTEGER, UNPROVIDED)) && sksi_sks_interaction.$$ExpandSubLFn.eql(formula_operator(v_object))) {
                return T;
            }
            if ((NIL != formula_arityE(v_object, ONE_INTEGER, UNPROVIDED)) && sksi_sks_interaction.$$SubLQuoteFn.eql(formula_operator(v_object))) {
                return T;
            }
        }
        return NIL;
    }

    public static final SubLObject sksi_filter_result_set_alt(SubLObject result_set, SubLObject where_pattern, SubLObject field_positions) {
        {
            SubLObject filtered_result_set = NIL;
            if ($INPUT == where_pattern) {
                filtered_result_set = result_set;
            } else {
                {
                    SubLObject cdolist_list_var = result_set;
                    SubLObject tuple = NIL;
                    for (tuple = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , tuple = cdolist_list_var.first()) {
                        if (NIL != pattern_match.pattern_matches_tree(where_pattern, tuple)) {
                            filtered_result_set = cons(tuple, filtered_result_set);
                        }
                    }
                    filtered_result_set = nreverse(filtered_result_set);
                }
            }
            if (NIL != field_positions) {
                {
                    SubLObject selected_result_set = NIL;
                    SubLObject cdolist_list_var = filtered_result_set;
                    SubLObject tuple = NIL;
                    for (tuple = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , tuple = cdolist_list_var.first()) {
                        {
                            SubLObject selected_tuple = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.compute_selected_tuple(tuple, field_positions);
                            selected_result_set = cons(selected_tuple, selected_result_set);
                        }
                    }
                    return nreverse(selected_result_set);
                }
            } else {
                return filtered_result_set;
            }
        }
    }

    public static SubLObject sksi_filter_result_set(final SubLObject result_set, final SubLObject where_pattern, final SubLObject field_positions) {
        SubLObject filtered_result_set = NIL;
        if ($INPUT == where_pattern) {
            filtered_result_set = result_set;
        } else {
            SubLObject cdolist_list_var = result_set;
            SubLObject tuple = NIL;
            tuple = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                if (NIL != pattern_matches_tree(where_pattern, tuple)) {
                    filtered_result_set = cons(tuple, filtered_result_set);
                }
                cdolist_list_var = cdolist_list_var.rest();
                tuple = cdolist_list_var.first();
            } 
            filtered_result_set = nreverse(filtered_result_set);
        }
        if (NIL != field_positions) {
            SubLObject selected_result_set = NIL;
            SubLObject cdolist_list_var2 = filtered_result_set;
            SubLObject tuple2 = NIL;
            tuple2 = cdolist_list_var2.first();
            while (NIL != cdolist_list_var2) {
                final SubLObject selected_tuple = sksi_sks_interaction.compute_selected_tuple(tuple2, field_positions);
                selected_result_set = cons(selected_tuple, selected_result_set);
                cdolist_list_var2 = cdolist_list_var2.rest();
                tuple2 = cdolist_list_var2.first();
            } 
            return nreverse(selected_result_set);
        }
        return filtered_result_set;
    }

    public static final SubLObject compute_selected_tuple_alt(SubLObject tuple, SubLObject field_positions) {
        return list_utilities.sequence_elements(tuple, field_positions);
    }

    public static SubLObject compute_selected_tuple(final SubLObject tuple, final SubLObject field_positions) {
        return sequence_elements(tuple, field_positions);
    }

    /**
     * Uses the KB as the knowledge source.
     */
    @LispMethod(comment = "Uses the KB as the knowledge source.")
    public static final SubLObject generate_kb_iterator_from_csql_alt(SubLObject csql, SubLObject access_path) {
        return NIL;
    }

    /**
     * Uses the KB as the knowledge source.
     */
    @LispMethod(comment = "Uses the KB as the knowledge source.")
    public static SubLObject generate_kb_iterator_from_csql(final SubLObject csql, final SubLObject access_path) {
        return NIL;
    }

    /**
     * Uses the KB as the knowledge source
     */
    @LispMethod(comment = "Uses the KB as the knowledge source")
    public static final SubLObject generate_kb_boolean_from_csql_alt(SubLObject csql, SubLObject access_path) {
        return NIL;
    }

    /**
     * Uses the KB as the knowledge source
     */
    @LispMethod(comment = "Uses the KB as the knowledge source")
    public static SubLObject generate_kb_boolean_from_csql(final SubLObject csql, final SubLObject access_path) {
        return NIL;
    }

    /**
     * Uses a file hash table as the knowledge source.
     */
    @LispMethod(comment = "Uses a file hash table as the knowledge source.")
    public static final SubLObject generate_fht_iterator_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject key = sksi_csql_interpretation.csql_to_key_and_value_expressions(csql, UNPROVIDED);
                SubLObject key_value = thread.secondMultipleValue();
                SubLObject where_clause_existsP = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                if (NIL == where_clause_existsP) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_iterator_from_fht_source(csql, access_path);
                }
                {
                    SubLObject result = NIL;
                    if (NIL != key) {
                        result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_for_key_from_fht_source(key, access_path);
                    } else {
                        if (NIL != key_value) {
                            result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_for_key_value_from_fht_source(key_value, access_path);
                        }
                    }
                    return sksi_result_set_iterators.new_fht_result_iterator(result);
                }
            }
        }
    }

    /**
     * Uses a file hash table as the knowledge source.
     */
    @LispMethod(comment = "Uses a file hash table as the knowledge source.")
    public static SubLObject generate_fht_iterator_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject key = sksi_csql_interpretation.csql_to_key_and_value_expressions(csql, UNPROVIDED);
        final SubLObject key_value = thread.secondMultipleValue();
        final SubLObject where_clause_existsP = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        if (NIL == where_clause_existsP) {
            return sksi_sks_interaction.get_result_iterator_from_fht_source(csql, access_path);
        }
        SubLObject result = NIL;
        if (NIL != key) {
            result = sksi_sks_interaction.get_result_for_key_from_fht_source(key, access_path);
        } else
            if (NIL != key_value) {
                result = sksi_sks_interaction.get_result_for_key_value_from_fht_source(key_value, access_path);
            }

        return sksi_result_set_iterators.new_fht_result_iterator(result);
    }

    /**
     * Uses a file hash table as the knowledge source
     */
    @LispMethod(comment = "Uses a file hash table as the knowledge source")
    public static final SubLObject generate_fht_boolean_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            thread.resetMultipleValues();
            {
                SubLObject key = sksi_csql_interpretation.csql_to_key_and_value_expressions(csql, UNPROVIDED);
                SubLObject key_value = thread.secondMultipleValue();
                SubLObject where_clause_existsP = thread.thirdMultipleValue();
                thread.resetMultipleValues();
                {
                    SubLObject value_test_fn = sksi_access_path.access_path_value_test_function(access_path);
                    SubLObject result = funcall(value_test_fn, key_value, com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_for_key_from_fht_source(key, access_path));
                    if (NIL != result) {
                        return list(list(EMPTY_SUBL_OBJECT_ARRAY));
                    } else {
                        return NIL;
                    }
                }
            }
        }
    }

    /**
     * Uses a file hash table as the knowledge source
     */
    @LispMethod(comment = "Uses a file hash table as the knowledge source")
    public static SubLObject generate_fht_boolean_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        thread.resetMultipleValues();
        final SubLObject key = sksi_csql_interpretation.csql_to_key_and_value_expressions(csql, UNPROVIDED);
        final SubLObject key_value = thread.secondMultipleValue();
        final SubLObject where_clause_existsP = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        final SubLObject value_test_fn = sksi_access_path.access_path_value_test_function(access_path);
        final SubLObject result = funcall(value_test_fn, key_value, sksi_sks_interaction.get_result_for_key_from_fht_source(key, access_path));
        if (NIL != result) {
            return list(list(EMPTY_SUBL_OBJECT_ARRAY));
        }
        return NIL;
    }

    /**
     * Uses an internal cache as the knowledge source.
     */
    @LispMethod(comment = "Uses an internal cache as the knowledge source.")
    public static final SubLObject generate_cache_iterator_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = csql;
                SubLObject current = datum;
                SubLObject select_keyword = NIL;
                SubLObject fields = NIL;
                SubLObject from = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt62);
                select_keyword = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt62);
                fields = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt62);
                from = current.first();
                current = current.rest();
                {
                    SubLObject where = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                    destructuring_bind_must_listp(current, datum, $list_alt62);
                    current = current.rest();
                    if (NIL == current) {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if ($SELECT != select_keyword) {
                                Errors.error($str_alt64$malformed_csql_expression__a, csql);
                            }
                        }
                        {
                            SubLObject tables = second(from);
                            if ((NIL != list_utilities.singletonP(tables)) && $list_alt65.equal(tables.first())) {
                                return sksi_cache.generate_language_spoken_iterator_from_csql(csql);
                            } else {
                                Errors.error($str_alt66$unknown_cache_for_csql__a_and_acc, csql, access_path);
                                return NIL;
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt62);
                    }
                }
            }
            return NIL;
        }
    }

    /**
     * Uses an internal cache as the knowledge source.
     */
    @LispMethod(comment = "Uses an internal cache as the knowledge source.")
    public static SubLObject generate_cache_iterator_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject select_keyword = NIL;
        SubLObject fields = NIL;
        SubLObject from = NIL;
        destructuring_bind_must_consp(csql, csql, sksi_sks_interaction.$list64);
        select_keyword = csql.first();
        SubLObject current = csql.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list64);
        fields = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list64);
        from = current.first();
        current = current.rest();
        final SubLObject where = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, csql, sksi_sks_interaction.$list64);
        current = current.rest();
        if (NIL != current) {
            cdestructuring_bind_error(csql, sksi_sks_interaction.$list64);
            return NIL;
        }
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && ($SELECT != select_keyword)) {
            Errors.error(sksi_sks_interaction.$str66$malformed_csql_expression__a, csql);
        }
        final SubLObject tables = second(from);
        if ((NIL != singletonP(tables)) && sksi_sks_interaction.$list67.equal(tables.first())) {
            return generate_language_spoken_iterator_from_csql(csql);
        }
        Errors.error(sksi_sks_interaction.$str68$unknown_cache_for_csql__a_and_acc, csql, access_path);
        return NIL;
    }

    /**
     * Uses an internal cache as the knowledge source
     */
    @LispMethod(comment = "Uses an internal cache as the knowledge source")
    public static final SubLObject generate_cache_boolean_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = csql;
                SubLObject current = datum;
                SubLObject select_keyword = NIL;
                SubLObject fields = NIL;
                SubLObject from = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt62);
                select_keyword = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt62);
                fields = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt62);
                from = current.first();
                current = current.rest();
                {
                    SubLObject where = (current.isCons()) ? ((SubLObject) (current.first())) : NIL;
                    destructuring_bind_must_listp(current, datum, $list_alt62);
                    current = current.rest();
                    if (NIL == current) {
                        if (NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) {
                            if ($SELECT != select_keyword) {
                                Errors.error($str_alt64$malformed_csql_expression__a, csql);
                            }
                        }
                        {
                            SubLObject tables = second(from);
                            if ((NIL != list_utilities.singletonP(tables)) && $list_alt65.equal(tables.first())) {
                                return sksi_cache.generate_language_spoken_boolean_from_csql(csql);
                            } else {
                                Errors.error($str_alt66$unknown_cache_for_csql__a_and_acc, csql, access_path);
                                return NIL;
                            }
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt62);
                    }
                }
            }
            return NIL;
        }
    }

    /**
     * Uses an internal cache as the knowledge source
     */
    @LispMethod(comment = "Uses an internal cache as the knowledge source")
    public static SubLObject generate_cache_boolean_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject select_keyword = NIL;
        SubLObject fields = NIL;
        SubLObject from = NIL;
        destructuring_bind_must_consp(csql, csql, sksi_sks_interaction.$list64);
        select_keyword = csql.first();
        SubLObject current = csql.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list64);
        fields = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, csql, sksi_sks_interaction.$list64);
        from = current.first();
        current = current.rest();
        final SubLObject where = (current.isCons()) ? current.first() : NIL;
        destructuring_bind_must_listp(current, csql, sksi_sks_interaction.$list64);
        current = current.rest();
        if (NIL != current) {
            cdestructuring_bind_error(csql, sksi_sks_interaction.$list64);
            return NIL;
        }
        if ((NIL == Errors.$ignore_mustsP$.getDynamicValue(thread)) && ($SELECT != select_keyword)) {
            Errors.error(sksi_sks_interaction.$str66$malformed_csql_expression__a, csql);
        }
        final SubLObject tables = second(from);
        if ((NIL != singletonP(tables)) && sksi_sks_interaction.$list67.equal(tables.first())) {
            return generate_language_spoken_boolean_from_csql(csql);
        }
        Errors.error(sksi_sks_interaction.$str68$unknown_cache_for_csql__a_and_acc, csql, access_path);
        return NIL;
    }

    /**
     * Uses an RDF triple store accessible via SPARQL as the knowledge source.
     */
    @LispMethod(comment = "Uses an RDF triple store accessible via SPARQL as the knowledge source.")
    public static final SubLObject generate_rdf_iterator_from_csql_alt(SubLObject csql, SubLObject access_path) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.new_sparql_result_set_iterator(csql, access_path, UNPROVIDED);
    }

    /**
     * Uses an RDF triple store accessible via SPARQL as the knowledge source.
     */
    @LispMethod(comment = "Uses an RDF triple store accessible via SPARQL as the knowledge source.")
    public static SubLObject generate_rdf_iterator_from_csql(final SubLObject csql, final SubLObject access_path) {
        return sksi_sks_interaction.new_sparql_result_set_iterator(csql, access_path, UNPROVIDED);
    }

    /**
     * Uses an RDF triple store accessible via SPARQL as the knowledge source.
     */
    @LispMethod(comment = "Uses an RDF triple store accessible via SPARQL as the knowledge source.")
    public static final SubLObject generate_rdf_boolean_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_sparql_result_set_from_csql(csql, access_path);
            return list_utilities.sublisp_boolean(result_set);
        }
    }

    /**
     * Uses an RDF triple store accessible via SPARQL as the knowledge source.
     */
    @LispMethod(comment = "Uses an RDF triple store accessible via SPARQL as the knowledge source.")
    public static SubLObject generate_rdf_boolean_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLObject result_set = sksi_sks_interaction.generate_sparql_result_set_from_csql(csql, access_path);
        return sublisp_boolean(result_set);
    }

    public static final SubLObject generate_sparql_result_set_from_csql_alt(SubLObject csql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject sparql = sksi_csql_sparql_interpretation.csql_to_sparql(csql, access_path);
                if (NIL != $oracle_sparql_progress_stream$.getDynamicValue(thread)) {
                    {
                        SubLObject oracle_sparql = sksi_csql_oracle_sparql_interpretation.csql_to_oracle_sparql(csql, access_path);
                        format($oracle_sparql_progress_stream$.getDynamicValue(thread), $str_alt67$___A____, oracle_sparql);
                    }
                }
                return sparql.isString() ? ((SubLObject) (com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_set_from_sparql_source(sparql, access_path))) : NIL;
            }
        }
    }

    public static SubLObject generate_sparql_result_set_from_csql(final SubLObject csql, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject sparql = sksi_csql_sparql_interpretation.csql_to_sparql(csql, access_path);
        if (NIL != sksi_sks_interaction.$oracle_sparql_progress_stream$.getDynamicValue(thread)) {
            final SubLObject oracle_sparql = sksi_csql_oracle_sparql_interpretation.csql_to_oracle_sparql(csql, access_path);
            format(sksi_sks_interaction.$oracle_sparql_progress_stream$.getDynamicValue(thread), sksi_sks_interaction.$str69$___A____, oracle_sparql);
        }
        return sparql.isString() ? sksi_sks_interaction.get_result_set_from_sparql_source(sparql, access_path, UNPROVIDED, UNPROVIDED) : NIL;
    }

    /**
     * Returns an iterator for the result set determined by the CSQL query applied to the SPARQL ACCESS-PATH.
     * LIMITS, if present is a list of increasing LIMIT overrides.
     */
    @LispMethod(comment = "Returns an iterator for the result set determined by the CSQL query applied to the SPARQL ACCESS-PATH.\r\nLIMITS, if present is a list of increasing LIMIT overrides.\nReturns an iterator for the result set determined by the CSQL query applied to the SPARQL ACCESS-PATH.\nLIMITS, if present is a list of increasing LIMIT overrides.")
    public static final SubLObject new_sparql_result_set_iterator_alt(SubLObject csql, SubLObject access_path, SubLObject limits) {
        if (limits == UNPROVIDED) {
            limits = $sksi_sparql_iterativity_limits$.getDynamicValue();
        }
        if (limits.equal($list_alt68)) {
            {
                SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_sparql_result_set_from_csql(csql, access_path);
                return iteration.new_list_iterator(result_set);
            }
        }
        return iteration.new_iterator(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.make_sparql_result_set_iterator_state(csql, access_path, limits), SPARQL_RESULT_SET_ITERATOR_DONE_P, SPARQL_RESULT_SET_ITERATOR_NEXT, UNPROVIDED);
    }

    /**
     * Returns an iterator for the result set determined by the CSQL query applied to the SPARQL ACCESS-PATH.
     * LIMITS, if present is a list of increasing LIMIT overrides.
     */
    @LispMethod(comment = "Returns an iterator for the result set determined by the CSQL query applied to the SPARQL ACCESS-PATH.\r\nLIMITS, if present is a list of increasing LIMIT overrides.\nReturns an iterator for the result set determined by the CSQL query applied to the SPARQL ACCESS-PATH.\nLIMITS, if present is a list of increasing LIMIT overrides.")
    public static SubLObject new_sparql_result_set_iterator(final SubLObject csql, final SubLObject access_path, SubLObject limits) {
        if (limits == UNPROVIDED) {
            limits = sksi_sks_interaction.$sksi_sparql_iterativity_limits$.getDynamicValue();
        }
        if (limits.equal(sksi_sks_interaction.$list70)) {
            final SubLObject result_set = sksi_sks_interaction.generate_sparql_result_set_from_csql(csql, access_path);
            return new_list_iterator(result_set);
        }
        return new_iterator(sksi_sks_interaction.make_sparql_result_set_iterator_state(csql, access_path, limits), sksi_sks_interaction.SPARQL_RESULT_SET_ITERATOR_DONE_P, sksi_sks_interaction.SPARQL_RESULT_SET_ITERATOR_NEXT, UNPROVIDED);
    }

    public static final SubLObject make_sparql_result_set_iterator_state_alt(SubLObject csql, SubLObject access_path, SubLObject limits) {
        {
            SubLObject pending_results = iteration.new_null_iterator();
            SubLObject pending_limits = iteration.new_list_iterator(limits);
            SubLObject processed_results = set.new_set(symbol_function(EQUAL), UNPROVIDED);
            return list(pending_results, pending_limits, processed_results, csql, access_path);
        }
    }

    public static SubLObject make_sparql_result_set_iterator_state(final SubLObject csql, final SubLObject access_path, final SubLObject limits) {
        final SubLObject pending_results = new_null_iterator();
        final SubLObject pending_limits = new_list_iterator(limits);
        final SubLObject processed_results = new_set(symbol_function(EQUAL), UNPROVIDED);
        return list(pending_results, pending_limits, processed_results, csql, access_path);
    }

    public static final SubLObject sparql_result_set_iterator_done_p_alt(SubLObject state) {
        {
            SubLObject datum = state;
            SubLObject current = datum;
            SubLObject pending_results = NIL;
            SubLObject pending_limits = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt71);
            pending_results = current.first();
            current = current.rest();
            destructuring_bind_must_consp(current, datum, $list_alt71);
            pending_limits = current.first();
            current = current.rest();
            {
                SubLObject rest = current;
                return makeBoolean((NIL != iteration.iteration_done(pending_results)) && (NIL != iteration.iteration_done(pending_limits)));
            }
        }
    }

    public static SubLObject sparql_result_set_iterator_done_p(final SubLObject state) {
        SubLObject pending_results = NIL;
        SubLObject pending_limits = NIL;
        destructuring_bind_must_consp(state, state, sksi_sks_interaction.$list73);
        pending_results = state.first();
        SubLObject current = state.rest();
        destructuring_bind_must_consp(current, state, sksi_sks_interaction.$list73);
        pending_limits = current.first();
        final SubLObject rest;
        current = rest = current.rest();
        return makeBoolean((NIL != iteration_done(pending_results)) && (NIL != iteration_done(pending_limits)));
    }

    public static final SubLObject sparql_result_set_iterator_next_alt(SubLObject state) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject datum = state;
                SubLObject current = datum;
                SubLObject pending_results = NIL;
                SubLObject pending_limits = NIL;
                SubLObject processed_results = NIL;
                SubLObject csql = NIL;
                SubLObject access_path = NIL;
                destructuring_bind_must_consp(current, datum, $list_alt72);
                pending_results = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt72);
                pending_limits = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt72);
                processed_results = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt72);
                csql = current.first();
                current = current.rest();
                destructuring_bind_must_consp(current, datum, $list_alt72);
                access_path = current.first();
                current = current.rest();
                if (NIL == current) {
                    while (!((NIL != iteration.iteration_done(pending_results)) && (NIL != iteration.iteration_done(pending_limits)))) {
                        while (NIL == iteration.iteration_done(pending_results)) {
                            thread.resetMultipleValues();
                            {
                                SubLObject pending_result = iteration.iteration_next(pending_results);
                                SubLObject validP = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                if (NIL != validP) {
                                    if (NIL == set.set_memberP(pending_result, processed_results)) {
                                        set.set_add(pending_result, processed_results);
                                        return values(pending_result, state, NIL);
                                    }
                                }
                            }
                        } 
                        if (NIL == iteration.iteration_done(pending_limits)) {
                            thread.resetMultipleValues();
                            {
                                SubLObject pending_limit = iteration.iteration_next(pending_limits);
                                SubLObject validP = thread.secondMultipleValue();
                                thread.resetMultipleValues();
                                if (NIL != validP) {
                                    {
                                        SubLObject result_set = NIL;
                                        {
                                            SubLObject _prev_bind_0 = sksi_csql_sparql_interpretation.$sparql_limit_override$.currentBinding(thread);
                                            try {
                                                sksi_csql_sparql_interpretation.$sparql_limit_override$.bind(pending_limit, thread);
                                                result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.generate_sparql_result_set_from_csql(csql, access_path);
                                            } finally {
                                                sksi_csql_sparql_interpretation.$sparql_limit_override$.rebind(_prev_bind_0, thread);
                                            }
                                        }
                                        pending_results = iteration.new_list_iterator(result_set);
                                        set_nth(ZERO_INTEGER, state, pending_results);
                                        if ((NIL != pending_limit) && length(result_set).numL(pending_limit)) {
                                            pending_limits = iteration.new_null_iterator();
                                            set_nth(ONE_INTEGER, state, pending_limits);
                                        }
                                    }
                                }
                            }
                        }
                    } 
                    set.clear_set(processed_results);
                    return values(NIL, state, T);
                } else {
                    cdestructuring_bind_error(datum, $list_alt72);
                }
            }
            return NIL;
        }
    }

    public static SubLObject sparql_result_set_iterator_next(final SubLObject state) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject pending_results = NIL;
        SubLObject pending_limits = NIL;
        SubLObject processed_results = NIL;
        SubLObject csql = NIL;
        SubLObject access_path = NIL;
        destructuring_bind_must_consp(state, state, sksi_sks_interaction.$list74);
        pending_results = state.first();
        SubLObject current = state.rest();
        destructuring_bind_must_consp(current, state, sksi_sks_interaction.$list74);
        pending_limits = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, state, sksi_sks_interaction.$list74);
        processed_results = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, state, sksi_sks_interaction.$list74);
        csql = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, state, sksi_sks_interaction.$list74);
        access_path = current.first();
        current = current.rest();
        if (NIL == current) {
            while ((NIL == iteration_done(pending_results)) || (NIL == iteration_done(pending_limits))) {
                while (NIL == iteration_done(pending_results)) {
                    thread.resetMultipleValues();
                    final SubLObject pending_result = iteration_next(pending_results);
                    final SubLObject validP = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if ((NIL != validP) && (NIL == set_memberP(pending_result, processed_results))) {
                        set_add(pending_result, processed_results);
                        return values(pending_result, state, NIL);
                    }
                } 
                if (NIL == iteration_done(pending_limits)) {
                    thread.resetMultipleValues();
                    final SubLObject pending_limit = iteration_next(pending_limits);
                    final SubLObject validP = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL == validP) {
                        continue;
                    }
                    SubLObject result_set = NIL;
                    final SubLObject _prev_bind_0 = sksi_csql_sparql_interpretation.$sparql_limit_override$.currentBinding(thread);
                    try {
                        sksi_csql_sparql_interpretation.$sparql_limit_override$.bind(pending_limit, thread);
                        result_set = sksi_sks_interaction.generate_sparql_result_set_from_csql(csql, access_path);
                    } finally {
                        sksi_csql_sparql_interpretation.$sparql_limit_override$.rebind(_prev_bind_0, thread);
                    }
                    pending_results = new_list_iterator(result_set);
                    set_nth(ZERO_INTEGER, state, pending_results);
                    if ((NIL == pending_limit) || (!length(result_set).numL(pending_limit))) {
                        continue;
                    }
                    pending_limits = new_null_iterator();
                    set_nth(ONE_INTEGER, state, pending_limits);
                }
            } 
            clear_set(processed_results);
            return values(NIL, state, T);
        }
        cdestructuring_bind_error(state, sksi_sks_interaction.$list74);
        return NIL;
    }

    public static final SubLObject with_sksi_result_set_from_execute_query_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            destructuring_bind_must_consp(current, datum, $list_alt73);
            {
                SubLObject temp = current.rest();
                current = current.first();
                {
                    SubLObject result_set = NIL;
                    SubLObject sql_string = NIL;
                    SubLObject access_path = NIL;
                    destructuring_bind_must_consp(current, datum, $list_alt73);
                    result_set = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt73);
                    sql_string = current.first();
                    current = current.rest();
                    destructuring_bind_must_consp(current, datum, $list_alt73);
                    access_path = current.first();
                    current = current.rest();
                    if (NIL == current) {
                        current = temp;
                        {
                            SubLObject body = current;
                            return list(CLET, list(result_set), list(CUNWIND_PROTECT, listS(PROGN, list(CSETQ, result_set, list(SKSI_EXECUTE_SQL_QUERY, sql_string, access_path)), list(PUNLESS, list(SQL_RESULT_SET_P, result_set), list(ERROR, $str_alt81$Problem_executing_query___a_, sql_string)), append(body, NIL)), list(FINALIZE_RESULT_SET, result_set)));
                        }
                    } else {
                        cdestructuring_bind_error(datum, $list_alt73);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject with_sksi_result_set_from_execute_query(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list75);
        final SubLObject temp = current.rest();
        current = current.first();
        SubLObject result_set = NIL;
        SubLObject sql_string = NIL;
        SubLObject access_path = NIL;
        destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list75);
        result_set = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list75);
        sql_string = current.first();
        current = current.rest();
        destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list75);
        access_path = current.first();
        current = current.rest();
        if (NIL == current) {
            final SubLObject body;
            current = body = temp;
            return list(CLET, list(result_set), list(CUNWIND_PROTECT, listS(PROGN, list(CSETQ, result_set, list(sksi_sks_interaction.SKSI_EXECUTE_SQL_QUERY, sql_string, access_path)), list(PUNLESS, list(sksi_sks_interaction.SQL_RESULT_SET_P, result_set), list(ERROR, sksi_sks_interaction.$str83$Problem_executing_query___a_, sql_string)), append(body, NIL)), list(sksi_sks_interaction.FINALIZE_RESULT_SET, result_set)));
        }
        cdestructuring_bind_error(datum, sksi_sks_interaction.$list75);
        return NIL;
    }

    public static final SubLObject sksi_execute_sqls_query_alt(SubLObject statement, SubLObject sql) {
        {
            SubLObject result = NIL;
            SubLObject inferences_var = inference_macros.current_controlling_inferences();
            SubLObject inference_var = inference_macros.current_controlling_inference();
            SubLObject tactic_var = inference_worker.currently_executing_tactic();
            SubLObject start_time = NIL;
            try {
                inference_datastructures_inference.possibly_signal_sksi_query_start(inference_var, tactic_var);
                start_time = inference_datastructures_inference.inference_time_so_far(inference_var, NIL);
                inference_datastructures_inference.possibly_add_inference_sksi_query_start_time(inferences_var, start_time);
                result = sdbc.sqls_execute_query(statement, sql, UNPROVIDED);
            } finally {
                {
                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                    try {
                        bind($is_thread_performing_cleanupP$, T);
                        inference_datastructures_inference.possibly_signal_sksi_query_end(inference_var, tactic_var);
                        inference_datastructures_inference.possibly_increment_inference_sksi_query_total_time(inferences_var, subtract(inference_datastructures_inference.inference_time_so_far(inference_var, NIL), start_time));
                    } finally {
                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                    }
                }
            }
            return result;
        }
    }

    public static SubLObject sksi_execute_sqls_query(final SubLObject statement, final SubLObject sql) {
        SubLObject result = NIL;
        final SubLObject inferences_var = current_controlling_inferences();
        final SubLObject inference_var = current_controlling_inference();
        final SubLObject tactic_var = currently_executing_tactic();
        SubLObject start_time = NIL;
        try {
            possibly_signal_sksi_query_start(inference_var, tactic_var);
            start_time = inference_time_so_far(inference_var, NIL);
            possibly_add_inference_sksi_query_start_time(inferences_var, start_time);
            sksi_sks_interaction.inference_possibly_note_salient_sksi_query_string(sql);
            result = sqls_execute_query(statement, sql, UNPROVIDED);
        } finally {
            final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
            try {
                bind($is_thread_performing_cleanupP$, T);
                final SubLObject _values = getValuesAsVector();
                possibly_signal_sksi_query_end(inference_var, tactic_var);
                possibly_increment_inference_sksi_query_total_time(inferences_var, subtract(inference_time_so_far(inference_var, NIL), start_time));
                restoreValuesFromVector(_values);
            } finally {
                rebind($is_thread_performing_cleanupP$, _prev_bind_0);
            }
        }
        return result;
    }

    public static final SubLObject sksi_execute_sqls_update_alt(SubLObject statement, SubLObject sql) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != sksi_infrastructure_macros.sksi_profiling_onP()) {
                {
                    SubLObject start_time = get_internal_real_time();
                    SubLObject end_time = NIL;
                    SubLObject result = NIL;
                    result = sdbc.sqls_execute_update(statement, sql);
                    end_time = get_internal_real_time();
                    sksi_infrastructure_macros.$sksi_sql_update_time$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_time$.getDynamicValue(thread), numeric_date_utilities.elapsed_seconds_between_internal_real_times(start_time, end_time)), thread);
                    sksi_infrastructure_macros.$sksi_sql_update_count$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_count$.getDynamicValue(thread), ONE_INTEGER), thread);
                    return result;
                }
            }
            return sdbc.sqls_execute_update(statement, sql);
        }
    }

    public static SubLObject sksi_execute_sqls_update(final SubLObject statement, final SubLObject sql) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_infrastructure_macros.sksi_profiling_onP()) {
            final SubLObject start_time = get_internal_real_time();
            SubLObject end_time = NIL;
            SubLObject result = NIL;
            result = sqls_execute_update(statement, sql);
            end_time = get_internal_real_time();
            sksi_infrastructure_macros.$sksi_sql_update_time$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_time$.getDynamicValue(thread), elapsed_seconds_between_internal_real_times(start_time, end_time)), thread);
            sksi_infrastructure_macros.$sksi_sql_update_count$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_count$.getDynamicValue(thread), ONE_INTEGER), thread);
            return result;
        }
        return sqls_execute_update(statement, sql);
    }

    /**
     *
     *
     * @return vectorp; a vector of update counts
    OR sdbc-error-p
     */
    @LispMethod(comment = "@return vectorp; a vector of update counts\r\nOR sdbc-error-p")
    public static final SubLObject sksi_execute_batch_sql_updates_alt(SubLObject statement) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != sksi_infrastructure_macros.sksi_profiling_onP()) {
                {
                    SubLObject start_time = get_internal_real_time();
                    SubLObject end_time = NIL;
                    SubLObject result = NIL;
                    result = sdbc.sqls_execute_batch(statement);
                    end_time = get_internal_real_time();
                    sksi_infrastructure_macros.$sksi_sql_update_time$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_time$.getDynamicValue(thread), numeric_date_utilities.elapsed_seconds_between_internal_real_times(start_time, end_time)), thread);
                    sksi_infrastructure_macros.$sksi_sql_batch_update_count$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_batch_update_count$.getDynamicValue(thread), ONE_INTEGER), thread);
                    return result;
                }
            } else {
                return sdbc.sqls_execute_batch(statement);
            }
        }
    }

    /**
     *
     *
     * @return vectorp; a vector of update counts
    OR sdbc-error-p
     */
    @LispMethod(comment = "@return vectorp; a vector of update counts\r\nOR sdbc-error-p")
    public static SubLObject sksi_execute_batch_sql_updates(final SubLObject statement) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_infrastructure_macros.sksi_profiling_onP()) {
            final SubLObject start_time = get_internal_real_time();
            SubLObject end_time = NIL;
            SubLObject result = NIL;
            result = sqls_execute_batch(statement);
            end_time = get_internal_real_time();
            sksi_infrastructure_macros.$sksi_sql_update_time$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_time$.getDynamicValue(thread), elapsed_seconds_between_internal_real_times(start_time, end_time)), thread);
            sksi_infrastructure_macros.$sksi_sql_batch_update_count$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_batch_update_count$.getDynamicValue(thread), ONE_INTEGER), thread);
            return result;
        }
        return sqls_execute_batch(statement);
    }

    public static final SubLObject sksi_add_batch_sql_update_alt(SubLObject sql, SubLObject statement) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != sksi_infrastructure_macros.sksi_profiling_onP()) {
                sksi_infrastructure_macros.$sksi_sql_update_count$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_count$.getDynamicValue(thread), ONE_INTEGER), thread);
            }
            return sdbc.sqls_add_batch(statement, sql);
        }
    }

    public static SubLObject sksi_add_batch_sql_update(final SubLObject sql, final SubLObject statement) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_infrastructure_macros.sksi_profiling_onP()) {
            sksi_infrastructure_macros.$sksi_sql_update_count$.setDynamicValue(add(sksi_infrastructure_macros.$sksi_sql_update_count$.getDynamicValue(thread), ONE_INTEGER), thread);
        }
        return sqls_add_batch(statement, sql);
    }

    public static final SubLObject sksi_execute_sql_query_alt(SubLObject sql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(sql, STRINGP);
            SubLTrampolineFile.checkType(access_path, ACCESS_PATH_P);
            thread.resetMultipleValues();
            {
                SubLObject connection = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_sql_connection_and_statement(access_path);
                SubLObject statement = thread.secondMultipleValue();
                thread.resetMultipleValues();
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_sql_query_int(sql, connection, statement, access_path);
            }
        }
    }

    public static SubLObject sksi_execute_sql_query(final SubLObject sql, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != stringp(sql) : "! stringp(sql) " + ("Types.stringp(sql) " + "CommonSymbols.NIL != Types.stringp(sql) ") + sql;
        assert NIL != sksi_access_path.access_path_p(access_path) : "! sksi_access_path.access_path_p(access_path) " + ("sksi_access_path.access_path_p(access_path) " + "CommonSymbols.NIL != sksi_access_path.access_path_p(access_path) ") + access_path;
        thread.resetMultipleValues();
        final SubLObject connection = sksi_sks_interaction.get_sql_connection_and_statement(access_path);
        final SubLObject statement = thread.secondMultipleValue();
        thread.resetMultipleValues();
        return sksi_sks_interaction.sksi_execute_sql_query_int(sql, connection, statement, access_path);
    }

    public static final SubLObject sksi_execute_sql_query_int_alt(SubLObject sql, SubLObject connection, SubLObject statement, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                if (NIL != sdbc.sql_open_statement_p(statement)) {
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt86$Executing_SQL_____Source___S__, $str_alt87$__ }), sksi_access_path.access_path_sks(access_path));
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt88$Module___S__, $str_alt87$__ }), inference_worker.currently_executing_hl_module());
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt89$Query____S__, $str_alt87$__ }), sql);
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                    {
                        SubLObject success_var = NIL;
                        try {
                            result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_sqls_query(statement, sql);
                            success_var = T;
                        } finally {
                            {
                                SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    if (NIL == success_var) {
                                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_abort_and_release_sql_statement_and_connection(statement, connection);
                                    }
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                                }
                            }
                        }
                    }
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt90$Query_Result___S__, $str_alt87$__ }), result);
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                    if (NIL != sdbc.sql_result_set_p(result)) {
                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.cache_sql_connection_statement_for_result_set(result, connection, statement);
                    } else {
                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_abort_and_release_sql_statement_and_connection(statement, connection);
                        if (NIL != sdbc.sdbc_error_p(result)) {
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_db_execution_error(access_path, result, sql);
                            result = NIL;
                        }
                    }
                }
                return result;
            }
        }
    }

    public static SubLObject sksi_execute_sql_query_int(final SubLObject sql, final SubLObject connection, final SubLObject statement, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        if (NIL != sql_open_statement_p(statement)) {
            if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str88$Executing_SQL_____Source___S__), ONE_INTEGER, sksi_access_path.access_path_sks(access_path));
                force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
            }
            if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str89$Module___S__), TWO_INTEGER, currently_executing_hl_module());
                force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
            }
            if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str90$Query____S__), ONE_INTEGER, sql);
                force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
            }
            SubLObject success_var = NIL;
            try {
                result = sksi_sks_interaction.sksi_execute_sqls_query(statement, sql);
                success_var = T;
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    if (NIL == success_var) {
                        sksi_sks_interaction.sksi_abort_and_release_sql_statement_and_connection(statement, connection);
                    }
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
            if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str91$Query_Result___S__), ONE_INTEGER, result);
                force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
            }
            if (NIL != sql_result_set_p(result)) {
                sksi_sks_interaction.cache_sql_connection_statement_for_result_set(result, connection, statement);
            } else {
                sksi_sks_interaction.sksi_abort_and_release_sql_statement_and_connection(statement, connection);
                if (NIL != sdbc_error_p(result)) {
                    sksi_sks_interaction.sksi_db_execution_error(access_path, result, sql);
                    result = NIL;
                }
            }
        }
        return result;
    }

    public static final SubLObject sksi_abort_and_release_sql_statement_and_connection_alt(SubLObject statement, SubLObject connection) {
        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_sql_connection_resourcingP()) {
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_abort_sql_statement_and_connection(statement, connection, NIL);
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.release_sql_connection_and_statement(connection, statement);
        } else {
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_abort_sql_statement_and_connection(statement, connection, T);
        }
        return NIL;
    }

    public static SubLObject sksi_abort_and_release_sql_statement_and_connection(final SubLObject statement, final SubLObject connection) {
        if (NIL != sksi_sks_interaction.within_sksi_sql_connection_resourcingP()) {
            sksi_sks_interaction.sksi_abort_sql_statement_and_connection(statement, connection, NIL);
            sksi_sks_interaction.release_sql_connection_and_statement(connection, statement);
        } else {
            sksi_sks_interaction.sksi_abort_sql_statement_and_connection(statement, connection, T);
        }
        return NIL;
    }

    public static final SubLObject sksi_execute_sql_update_alt(SubLObject sql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(sql, STRINGP);
            SubLTrampolineFile.checkType(access_path, ACCESS_PATH_P);
            thread.resetMultipleValues();
            {
                SubLObject connection = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_sql_connection_and_statement(access_path);
                SubLObject statement = thread.secondMultipleValue();
                thread.resetMultipleValues();
                {
                    SubLObject result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_sql_update_int(sql, connection, statement, access_path);
                    com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.release_sql_connection_and_statement(connection, statement);
                    return result;
                }
            }
        }
    }

    public static SubLObject sksi_execute_sql_update(final SubLObject sql, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != stringp(sql) : "! stringp(sql) " + ("Types.stringp(sql) " + "CommonSymbols.NIL != Types.stringp(sql) ") + sql;
        assert NIL != sksi_access_path.access_path_p(access_path) : "! sksi_access_path.access_path_p(access_path) " + ("sksi_access_path.access_path_p(access_path) " + "CommonSymbols.NIL != sksi_access_path.access_path_p(access_path) ") + access_path;
        thread.resetMultipleValues();
        final SubLObject connection = sksi_sks_interaction.get_sql_connection_and_statement(access_path);
        final SubLObject statement = thread.secondMultipleValue();
        thread.resetMultipleValues();
        final SubLObject result = sksi_sks_interaction.sksi_execute_sql_update_int(sql, connection, statement, access_path);
        sksi_sks_interaction.release_sql_connection_and_statement(connection, statement);
        return result;
    }

    public static final SubLObject sksi_execute_sql_update_int_alt(SubLObject sql, SubLObject connection, SubLObject statement, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                if (NIL != sdbc.sql_open_statement_p(statement)) {
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt91$Executing_SQL_____Source___S____U, $str_alt87$__ }), sksi_access_path.access_path_sks(access_path), sql);
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                    {
                        SubLObject success_var = NIL;
                        try {
                            result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_sqls_update(statement, sql);
                            success_var = T;
                        } finally {
                            {
                                SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    if (NIL == success_var) {
                                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_abort_sql_statement_and_connection(statement, connection, makeBoolean(NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_sql_connection_resourcingP()));
                                    }
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                                }
                            }
                        }
                    }
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt92$SQL_Update_Result___S__, $str_alt87$__ }), result);
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                    if (NIL != sdbc.sdbc_error_p(result)) {
                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_db_execution_error(access_path, result, sql);
                        result = NIL;
                    }
                }
                return result;
            }
        }
    }

    public static SubLObject sksi_execute_sql_update_int(final SubLObject sql, final SubLObject connection, final SubLObject statement, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        if (NIL != sql_open_statement_p(statement)) {
            if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str92$Executing_SQL_____Source___S____U), new SubLObject[]{ ONE_INTEGER, sksi_access_path.access_path_sks(access_path), sql });
                force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
            }
            SubLObject success_var = NIL;
            try {
                result = sksi_sks_interaction.sksi_execute_sqls_update(statement, sql);
                success_var = T;
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    if (NIL == success_var) {
                        sksi_sks_interaction.sksi_abort_sql_statement_and_connection(statement, connection, makeBoolean(NIL == sksi_sks_interaction.within_sksi_sql_connection_resourcingP()));
                    }
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
            if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(ONE_INTEGER)) {
                format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str93$SQL_Update_Result___S__), ONE_INTEGER, result);
                force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
            }
            if (NIL != sdbc_error_p(result)) {
                sksi_sks_interaction.sksi_db_execution_error(access_path, result, sql);
                result = NIL;
            }
        }
        return result;
    }

    /**
     *
     *
     * @return vectorp or sdbc-error-p; a vector of update counts or NIL if there was an error
     * @return listp; a list of auto-generated keys, if there are any
     */
    @LispMethod(comment = "@return vectorp or sdbc-error-p; a vector of update counts or NIL if there was an error\r\n@return listp; a list of auto-generated keys, if there are any")
    public static final SubLObject sksi_batch_execute_sql_statements_alt(SubLObject sk_source, SubLObject access_path, SubLObject sql_statements, SubLObject get_auto_generated_keysP, SubLObject auto_key_fields) {
        if (get_auto_generated_keysP == UNPROVIDED) {
            get_auto_generated_keysP = NIL;
        }
        if (auto_key_fields == UNPROVIDED) {
            auto_key_fields = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                SubLObject auto_generated_keys = NIL;
                if (NIL != sql_statements) {
                    if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                        format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt93$Batch_executing__s_SQL_statements, $str_alt87$__ }), length(sql_statements), sk_source);
                        force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                    }
                    {
                        SubLObject table_name = (NIL != get_auto_generated_keysP) ? ((SubLObject) (sksi_kb_accessors.sk_source_name(sk_source))) : NIL;
                        SubLObject sql_flavor = sksi_access_path.access_path_sql_flavor(access_path);
                        SubLObject error = NIL;
                        SubLObject db = sksi_access_path.access_path_db(access_path);
                        SubLObject user = sksi_access_path.access_path_user(access_path);
                        SubLObject password = sksi_access_path.access_path_password(access_path);
                        SubLObject server = sksi_access_path.access_path_server(access_path);
                        SubLObject port = sksi_access_path.access_path_port(access_path);
                        SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
                        SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
                        SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
                        SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
                        SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
                        SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
                        SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : $sksi_open_sql_connection_default_timeout$.getGlobalValue();
                        SubLObject statement = NIL;
                        SubLObject connection = NIL;
                        try {
                            connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                            if (NIL != sdbc.sql_open_connection_p(connection)) {
                                statement = sdbc.sqlc_create_statement(connection);
                            } else {
                                statement = connection;
                            }
                            if (NIL != sdbc.sql_open_statement_p(statement)) {
                                {
                                    SubLObject connection_8 = sdbc.sqls_get_connection(statement);
                                    SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_8);
                                    SubLObject commit_error = NIL;
                                    SubLObject rollback_result = NIL;
                                    SubLObject auto_commit_result = NIL;
                                    SubLObject errors = NIL;
                                    if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                                        errors = cons(auto_commit, errors);
                                    } else {
                                        sdbc.sqlc_set_error_handling(connection_8, $THROW);
                                        try {
                                            {
                                                SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                                                try {
                                                    Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                    try {
                                                        if (NIL != auto_commit) {
                                                            sdbc.sqlc_set_auto_commit(connection_8, NIL);
                                                        }
                                                        {
                                                            SubLObject cdolist_list_var = sql_statements;
                                                            SubLObject sql = NIL;
                                                            for (sql = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sql = cdolist_list_var.first()) {
                                                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_add_batch_sql_update(sql, statement);
                                                            }
                                                        }
                                                        if (NIL != get_auto_generated_keysP) {
                                                            {
                                                                SubLObject pcase_var = sql_flavor;
                                                                if (pcase_var.eql($$OracleDatabaseServer_TheProgram) || pcase_var.eql($$PostgreSQL)) {
                                                                    sdbc.sqls_execute_update(statement, cconcatenate($str_alt101$LOCK_TABLE_, new SubLObject[]{ format_nil.format_nil_a_no_copy(table_name), $str_alt102$_IN_EXCLUSIVE_MODE }));
                                                                } else {
                                                                    if (pcase_var.eql($$MySQL_TheProgram)) {
                                                                        sdbc.sqls_execute_update(statement, cconcatenate($str_alt101$LOCK_TABLE_, new SubLObject[]{ format_nil.format_nil_a_no_copy(table_name), $str_alt103$_WRITE }));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_batch_sql_updates(statement);
                                                        if (NIL != sdbc.sdbc_error_p(result)) {
                                                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_db_execution_error(access_path, result, sdbc.sqls_sql(statement));
                                                        } else {
                                                            if (NIL != get_auto_generated_keysP) {
                                                                auto_generated_keys = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_n_largest_auto_generated_keys(statement, table_name, auto_key_fields, list_length(sql_statements), sql_flavor);
                                                            }
                                                        }
                                                        sdbc.sqlc_commit(connection_8);
                                                    } catch (Throwable catch_var) {
                                                        Errors.handleThrowable(catch_var, NIL);
                                                    }
                                                } finally {
                                                    Errors.$error_handler$.rebind(_prev_bind_0, thread);
                                                }
                                            }
                                        } catch (Throwable ccatch_env_var) {
                                            commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                        }
                                        sdbc.sqlc_set_error_handling(connection_8, $RETURN);
                                        if (NIL != commit_error) {
                                            errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                            rollback_result = sdbc.sqls_handle_rollback(connection_8);
                                            if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                                errors = cons(rollback_result, errors);
                                            }
                                        }
                                        if (NIL != auto_commit) {
                                            auto_commit_result = sdbc.sqlc_set_auto_commit(connection_8, T);
                                        }
                                        if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                            errors = cons(auto_commit_result, errors);
                                        }
                                    }
                                    if (NIL != errors) {
                                        error = sdbc.sqls_handle_transaction_errors(errors);
                                    }
                                }
                            }
                        } finally {
                            {
                                SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                try {
                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                    if (NIL != sdbc.sql_connection_p(connection)) {
                                        sdbc.sqlc_close(connection);
                                    }
                                } finally {
                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                                }
                            }
                        }
                        if (NIL != sdbc.sdbc_error_p(error)) {
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_db_execution_error(access_path, error, NIL);
                            result = error;
                        }
                    }
                    if (NIL != sdbc.sdbc_error_p(result)) {
                        if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                            format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt105$Batch_execution_resulted_in_error, $str_alt87$__ }), sdbc.sdbc_error_message(result));
                            force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                        }
                    } else {
                        if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                            format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate($str_alt85$SKSI_, new SubLObject[]{ $str_alt106$Batch_execution_completed_success, $str_alt87$__ }));
                            force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                        }
                    }
                }
                return values(result, auto_generated_keys);
            }
        }
    }

    /**
     *
     *
     * @return vectorp or sdbc-error-p; a vector of update counts or NIL if there was an error
     * @return listp; a list of auto-generated keys, if there are any
     */
    @LispMethod(comment = "@return vectorp or sdbc-error-p; a vector of update counts or NIL if there was an error\r\n@return listp; a list of auto-generated keys, if there are any")
    public static SubLObject sksi_batch_execute_sql_statements(final SubLObject sk_source, final SubLObject access_path, final SubLObject sql_statements, SubLObject get_auto_generated_keysP, SubLObject auto_key_fields) {
        if (get_auto_generated_keysP == UNPROVIDED) {
            get_auto_generated_keysP = NIL;
        }
        if (auto_key_fields == UNPROVIDED) {
            auto_key_fields = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        SubLObject auto_generated_keys = NIL;
        if (NIL != sql_statements) {
            if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str94$Batch_executing__s_SQL_statements), new SubLObject[]{ TWO_INTEGER, length(sql_statements), sk_source });
                force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
            }
            final SubLObject table_name = (NIL != get_auto_generated_keysP) ? sksi_kb_accessors.sk_source_name(sk_source) : NIL;
            final SubLObject sql_flavor = sksi_access_path.access_path_sql_flavor(access_path);
            SubLObject error = NIL;
            final SubLObject db = sksi_access_path.access_path_db(access_path);
            final SubLObject user = sksi_access_path.access_path_user(access_path);
            final SubLObject password = sksi_access_path.access_path_password(access_path);
            final SubLObject server = sksi_access_path.access_path_server(access_path);
            final SubLObject port = sksi_access_path.access_path_port(access_path);
            final SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
            final SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
            final SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
            final SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
            final SubLObject conn_server = (NIL != proxy_server) ? proxy_server : server;
            final SubLObject conn_port = (NIL != proxy_port) ? proxy_port : port;
            final SubLObject conn_timeout = (NIL != timeout) ? timeout : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
            SubLObject statement = NIL;
            SubLObject connection = NIL;
            try {
                connection = new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                if (NIL != sql_open_connection_p(connection)) {
                    statement = sqlc_create_statement(connection);
                } else {
                    statement = connection;
                }
                if (NIL != sql_open_statement_p(statement)) {
                    final SubLObject connection_$8 = sqls_get_connection(statement);
                    final SubLObject auto_commit = sqlc_get_auto_commit(connection_$8);
                    SubLObject commit_error = NIL;
                    SubLObject rollback_result = NIL;
                    SubLObject auto_commit_result = NIL;
                    SubLObject errors = NIL;
                    if (NIL != sdbc_error_p(auto_commit)) {
                        errors = cons(auto_commit, errors);
                    } else {
                        sqlc_set_error_handling(connection_$8, $THROW);
                        try {
                            thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                            try {
                                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                try {
                                    if (NIL != auto_commit) {
                                        sqlc_set_auto_commit(connection_$8, NIL);
                                    }
                                    SubLObject cdolist_list_var = sql_statements;
                                    SubLObject sql = NIL;
                                    sql = cdolist_list_var.first();
                                    while (NIL != cdolist_list_var) {
                                        sksi_sks_interaction.sksi_add_batch_sql_update(sql, statement);
                                        cdolist_list_var = cdolist_list_var.rest();
                                        sql = cdolist_list_var.first();
                                    } 
                                    if (NIL != get_auto_generated_keysP) {
                                        final SubLObject pcase_var = sql_flavor;
                                        if (pcase_var.eql(sksi_sks_interaction.$$OracleDatabaseServer_TheProgram) || pcase_var.eql(sksi_sks_interaction.$$PostgreSQL)) {
                                            sqls_execute_update(statement, cconcatenate(sksi_sks_interaction.$$$LOCK_TABLE_, new SubLObject[]{ format_nil_a_no_copy(table_name), sksi_sks_interaction.$$$_IN_EXCLUSIVE_MODE }));
                                        } else
                                            if (pcase_var.eql(sksi_sks_interaction.$$MySQL_TheProgram)) {
                                                sqls_execute_update(statement, cconcatenate(sksi_sks_interaction.$$$LOCK_TABLE_, new SubLObject[]{ format_nil_a_no_copy(table_name), sksi_sks_interaction.$$$_WRITE }));
                                            }

                                    }
                                    result = sksi_sks_interaction.sksi_execute_batch_sql_updates(statement);
                                    if (NIL != sdbc_error_p(result)) {
                                        sksi_sks_interaction.sksi_db_execution_error(access_path, result, sqls_sql(statement));
                                    } else
                                        if (NIL != get_auto_generated_keysP) {
                                            auto_generated_keys = sksi_sks_interaction.get_n_largest_auto_generated_keys(statement, table_name, auto_key_fields, list_length(sql_statements), sql_flavor);
                                        }

                                    sqlc_commit(connection_$8);
                                } catch (final Throwable catch_var) {
                                    Errors.handleThrowable(catch_var, NIL);
                                }
                            } finally {
                                Errors.$error_handler$.rebind(_prev_bind_0, thread);
                            }
                        } catch (final Throwable ccatch_env_var) {
                            commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                        } finally {
                            thread.throwStack.pop();
                        }
                        sqlc_set_error_handling(connection_$8, $RETURN);
                        if (NIL != commit_error) {
                            errors = cons(sqls_handle_commit_error(commit_error), errors);
                            rollback_result = sqls_handle_rollback(connection_$8);
                            if (NIL != sdbc_error_p(rollback_result)) {
                                errors = cons(rollback_result, errors);
                            }
                        }
                        if (NIL != auto_commit) {
                            auto_commit_result = sqlc_set_auto_commit(connection_$8, T);
                        }
                        if (NIL != sdbc_error_p(auto_commit_result)) {
                            errors = cons(auto_commit_result, errors);
                        }
                    }
                    if (NIL != errors) {
                        error = sqls_handle_transaction_errors(errors);
                    }
                }
            } finally {
                final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    if (NIL != sql_connection_p(connection)) {
                        sqlc_close(connection);
                    }
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
                }
            }
            if (NIL != sdbc_error_p(error)) {
                sksi_sks_interaction.sksi_db_execution_error(access_path, error, NIL);
                result = error;
            }
            if (NIL != sdbc_error_p(result)) {
                if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                    format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str106$Batch_execution_resulted_in_error), TWO_INTEGER, sdbc_error_message(result));
                    force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                }
            } else
                if (NIL != sksi_debugging.sksi_trace_level_exceeds_minimumP(TWO_INTEGER)) {
                    format(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread), cconcatenate(sksi_sks_interaction.$str87$__SKSI__A_, sksi_sks_interaction.$str107$Batch_execution_completed_success), TWO_INTEGER);
                    force_output(sksi_debugging.$sksi_trace_stream$.getDynamicValue(thread));
                }

        }
        return values(result, auto_generated_keys);
    }

    public static final SubLObject get_n_largest_auto_generated_keys_alt(SubLObject statement, SubLObject table_name, SubLObject auto_key_fields, SubLObject n, SubLObject sql_flavor) {
        {
            SubLObject auto_generated_keys = NIL;
            SubLObject pcase_var = sql_flavor;
            if (pcase_var.eql($$PostgreSQL) || pcase_var.eql($$MySQL_TheProgram)) {
                {
                    SubLObject cdolist_list_var = auto_key_fields;
                    SubLObject auto_key_field = NIL;
                    for (auto_key_field = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , auto_key_field = cdolist_list_var.first()) {
                        {
                            SubLObject sql = cconcatenate($str_alt107$SELECT_, new SubLObject[]{ format_nil.format_nil_a_no_copy(auto_key_field), $str_alt108$_FROM_, format_nil.format_nil_a_no_copy(table_name), $str_alt109$_ORDER_BY_, format_nil.format_nil_a_no_copy(auto_key_field), $str_alt110$_DESC_LIMIT_, format_nil.format_nil_d_no_copy(n) });
                            SubLObject field_auto_keys = NIL;
                            SubLObject result = NIL;
                            result = sdbc.sqls_execute_query(statement, sql, UNPROVIDED);
                            if (NIL != sdbc.sql_result_set_p(result)) {
                                while (NIL != sdbc.sqlrs_next(result)) {
                                    field_auto_keys = cons(sdbc.sqlrs_get_object(result, ONE_INTEGER), field_auto_keys);
                                } 
                            }
                            auto_generated_keys = cons(list(auto_key_field, field_auto_keys), auto_generated_keys);
                        }
                    }
                }
            } else {
                if (pcase_var.eql($$OracleDatabaseServer_TheProgram)) {
                    {
                        SubLObject cdolist_list_var = auto_key_fields;
                        SubLObject auto_key_field = NIL;
                        for (auto_key_field = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , auto_key_field = cdolist_list_var.first()) {
                            {
                                SubLObject sql = cconcatenate($str_alt111$SELECT___FROM__SELECT_, new SubLObject[]{ format_nil.format_nil_a_no_copy(auto_key_field), $str_alt108$_FROM_, format_nil.format_nil_a_no_copy(table_name), $str_alt109$_ORDER_BY_, format_nil.format_nil_a_no_copy(auto_key_field), $str_alt112$_DESC__WHERE_rownum_BETWEEN_, format_nil.format_nil_d_no_copy(ONE_INTEGER), $str_alt113$_AND_, format_nil.format_nil_d_no_copy(n) });
                                SubLObject field_auto_keys = NIL;
                                SubLObject result = NIL;
                                result = sdbc.sqls_execute_query(statement, sql, UNPROVIDED);
                                if (NIL != sdbc.sql_result_set_p(result)) {
                                    while (NIL != sdbc.sqlrs_next(result)) {
                                        field_auto_keys = cons(sdbc.sqlrs_get_object(result, ONE_INTEGER), field_auto_keys);
                                    } 
                                }
                                auto_generated_keys = cons(list(auto_key_field, field_auto_keys), auto_generated_keys);
                            }
                        }
                    }
                } else {
                    Errors.error($str_alt114$Auto_generated_key_retrieval_is_n, sql_flavor);
                }
            }
            return nreverse(auto_generated_keys);
        }
    }

    public static SubLObject get_n_largest_auto_generated_keys(final SubLObject statement, final SubLObject table_name, final SubLObject auto_key_fields, final SubLObject n, final SubLObject sql_flavor) {
        SubLObject auto_generated_keys = NIL;
        if (sql_flavor.eql(sksi_sks_interaction.$$PostgreSQL) || sql_flavor.eql(sksi_sks_interaction.$$MySQL_TheProgram)) {
            SubLObject cdolist_list_var = auto_key_fields;
            SubLObject auto_key_field = NIL;
            auto_key_field = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                final SubLObject sql = cconcatenate(sksi_sks_interaction.$$$SELECT_, new SubLObject[]{ format_nil_a_no_copy(auto_key_field), sksi_sks_interaction.$$$_FROM_, format_nil_a_no_copy(table_name), sksi_sks_interaction.$$$_ORDER_BY_, format_nil_a_no_copy(auto_key_field), sksi_sks_interaction.$$$_DESC_LIMIT_, format_nil_d_no_copy(n) });
                SubLObject field_auto_keys = NIL;
                SubLObject result = NIL;
                result = sqls_execute_query(statement, sql, UNPROVIDED);
                if (NIL != sql_result_set_p(result)) {
                    while (NIL != sqlrs_next(result)) {
                        field_auto_keys = cons(sqlrs_get_object(result, ONE_INTEGER), field_auto_keys);
                    } 
                }
                auto_generated_keys = cons(list(auto_key_field, field_auto_keys), auto_generated_keys);
                cdolist_list_var = cdolist_list_var.rest();
                auto_key_field = cdolist_list_var.first();
            } 
        } else
            if (sql_flavor.eql(sksi_sks_interaction.$$OracleDatabaseServer_TheProgram)) {
                SubLObject cdolist_list_var = auto_key_fields;
                SubLObject auto_key_field = NIL;
                auto_key_field = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    final SubLObject sql = cconcatenate(sksi_sks_interaction.$str112$SELECT___FROM__SELECT_, new SubLObject[]{ format_nil_a_no_copy(auto_key_field), sksi_sks_interaction.$$$_FROM_, format_nil_a_no_copy(table_name), sksi_sks_interaction.$$$_ORDER_BY_, format_nil_a_no_copy(auto_key_field), sksi_sks_interaction.$str113$_DESC__WHERE_rownum_BETWEEN_, format_nil_d_no_copy(ONE_INTEGER), sksi_sks_interaction.$$$_AND_, format_nil_d_no_copy(n) });
                    SubLObject field_auto_keys = NIL;
                    SubLObject result = NIL;
                    result = sqls_execute_query(statement, sql, UNPROVIDED);
                    if (NIL != sql_result_set_p(result)) {
                        while (NIL != sqlrs_next(result)) {
                            field_auto_keys = cons(sqlrs_get_object(result, ONE_INTEGER), field_auto_keys);
                        } 
                    }
                    auto_generated_keys = cons(list(auto_key_field, field_auto_keys), auto_generated_keys);
                    cdolist_list_var = cdolist_list_var.rest();
                    auto_key_field = cdolist_list_var.first();
                } 
            } else {
                Errors.error(sksi_sks_interaction.$str115$Auto_generated_key_retrieval_is_n, sql_flavor);
            }

        return nreverse(auto_generated_keys);
    }

    /**
     * Executes the SQL from text file FILENAME on SK-SOURCE.  See the
     * documentation on SKSI-EXECUTE-SQL-SCRIPT for more information.
     */
    @LispMethod(comment = "Executes the SQL from text file FILENAME on SK-SOURCE.  See the\r\ndocumentation on SKSI-EXECUTE-SQL-SCRIPT for more information.\nExecutes the SQL from text file FILENAME on SK-SOURCE.  See the\ndocumentation on SKSI-EXECUTE-SQL-SCRIPT for more information.")
    public static final SubLObject sksi_execute_sql_script_from_file_alt(SubLObject filename, SubLObject sk_source) {
        {
            SubLObject file_string = file_utilities.slurp_file(filename);
            SubLObject script = (NIL != string_utilities.non_empty_string_p(file_string)) ? ((SubLObject) (string_utilities.string_tokenize(file_string, $list_alt116, NIL, NIL, T, UNPROVIDED, UNPROVIDED))) : NIL;
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_sql_script(script, sk_source);
        }
    }

    /**
     * Executes the SQL from text file FILENAME on SK-SOURCE.  See the
     * documentation on SKSI-EXECUTE-SQL-SCRIPT for more information.
     */
    @LispMethod(comment = "Executes the SQL from text file FILENAME on SK-SOURCE.  See the\r\ndocumentation on SKSI-EXECUTE-SQL-SCRIPT for more information.\nExecutes the SQL from text file FILENAME on SK-SOURCE.  See the\ndocumentation on SKSI-EXECUTE-SQL-SCRIPT for more information.")
    public static SubLObject sksi_execute_sql_script_from_file(final SubLObject filename, final SubLObject sk_source) {
        final SubLObject file_string = slurp_file(filename);
        final SubLObject script = (NIL != non_empty_string_p(file_string)) ? string_tokenize(file_string, sksi_sks_interaction.$list117, NIL, NIL, T, UNPROVIDED, UNPROVIDED) : NIL;
        return sksi_sks_interaction.sksi_execute_sql_script(script, sk_source);
    }

    /**
     * Executes SCRIPT on SK-SOURCE, where SCRIPT is a list of SQL strings
     * which may be either queries or updates.  Note, however, that the
     * results of neither the queries nor the updates will be returned.
     * The only return value will be whether there was an error during
     * execution.  If there was an error, then all DB operations will be
     * rolled back.
     */
    @LispMethod(comment = "Executes SCRIPT on SK-SOURCE, where SCRIPT is a list of SQL strings\r\nwhich may be either queries or updates.  Note, however, that the\r\nresults of neither the queries nor the updates will be returned.\r\nThe only return value will be whether there was an error during\r\nexecution.  If there was an error, then all DB operations will be\r\nrolled back.\nExecutes SCRIPT on SK-SOURCE, where SCRIPT is a list of SQL strings\nwhich may be either queries or updates.  Note, however, that the\nresults of neither the queries nor the updates will be returned.\nThe only return value will be whether there was an error during\nexecution.  If there was an error, then all DB operations will be\nrolled back.")
    public static final SubLObject sksi_execute_sql_script_alt(SubLObject script, SubLObject sk_source) {
        {
            SubLObject access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
            SubLObject error = NIL;
            SubLObject db = sksi_access_path.access_path_db(access_path);
            SubLObject user = sksi_access_path.access_path_user(access_path);
            SubLObject password = sksi_access_path.access_path_password(access_path);
            SubLObject server = sksi_access_path.access_path_server(access_path);
            SubLObject port = sksi_access_path.access_path_port(access_path);
            SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
            SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
            SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
            SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
            SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
            SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
            SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : $sksi_open_sql_connection_default_timeout$.getGlobalValue();
            SubLObject statement = NIL;
            SubLObject connection = NIL;
            try {
                connection = sdbc.new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
                if (NIL != sdbc.sql_open_connection_p(connection)) {
                    statement = sdbc.sqlc_create_statement(connection);
                } else {
                    statement = connection;
                }
                if (NIL != sdbc.sql_open_statement_p(statement)) {
                    {
                        SubLObject connection_9 = sdbc.sqls_get_connection(statement);
                        SubLObject auto_commit = sdbc.sqlc_get_auto_commit(connection_9);
                        SubLObject commit_error = NIL;
                        SubLObject rollback_result = NIL;
                        SubLObject auto_commit_result = NIL;
                        SubLObject errors = NIL;
                        if (NIL != sdbc.sdbc_error_p(auto_commit)) {
                            errors = cons(auto_commit, errors);
                        } else {
                            sdbc.sqlc_set_error_handling(connection_9, $THROW);
                            try {
                                {
                                    SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                                    try {
                                        bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                                        try {
                                            if (NIL != auto_commit) {
                                                sdbc.sqlc_set_auto_commit(connection_9, NIL);
                                            }
                                            {
                                                SubLObject cdolist_list_var = script;
                                                SubLObject sql = NIL;
                                                for (sql = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , sql = cdolist_list_var.first()) {
                                                    sql = string_utilities.left_trim_whitespace(sql);
                                                    if (NIL == string_utilities.empty_string_p(sql)) {
                                                        if (NIL != string_utilities.starts_with_by_test(sql, $$$SELECT, symbol_function(EQUALP))) {
                                                            {
                                                                SubLObject result_set = sdbc.sqls_execute_query(statement, sql, UNPROVIDED);
                                                                if (NIL != sdbc.sql_open_result_set_p(result_set)) {
                                                                    sdbc.sqlrs_close(result_set);
                                                                }
                                                            }
                                                        } else {
                                                            sdbc.sqls_execute_update(statement, sql);
                                                        }
                                                    }
                                                }
                                            }
                                            sdbc.sqlc_commit(connection_9);
                                        } catch (Throwable catch_var) {
                                            Errors.handleThrowable(catch_var, NIL);
                                        }
                                    } finally {
                                        rebind(Errors.$error_handler$, _prev_bind_0);
                                    }
                                }
                            } catch (Throwable ccatch_env_var) {
                                commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                            }
                            sdbc.sqlc_set_error_handling(connection_9, $RETURN);
                            if (NIL != commit_error) {
                                errors = cons(sdbc.sqls_handle_commit_error(commit_error), errors);
                                rollback_result = sdbc.sqls_handle_rollback(connection_9);
                                if (NIL != sdbc.sdbc_error_p(rollback_result)) {
                                    errors = cons(rollback_result, errors);
                                }
                            }
                            if (NIL != auto_commit) {
                                auto_commit_result = sdbc.sqlc_set_auto_commit(connection_9, T);
                            }
                            if (NIL != sdbc.sdbc_error_p(auto_commit_result)) {
                                errors = cons(auto_commit_result, errors);
                            }
                        }
                        if (NIL != errors) {
                            error = sdbc.sqls_handle_transaction_errors(errors);
                        }
                    }
                }
            } finally {
                {
                    SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                    try {
                        bind($is_thread_performing_cleanupP$, T);
                        if (NIL != sdbc.sql_connection_p(connection)) {
                            sdbc.sqlc_close(connection);
                        }
                    } finally {
                        rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                    }
                }
            }
            if (NIL != error) {
                sdbc.sdbc_error_warn(error);
            }
            return sublisp_null(error);
        }
    }

    /**
     * Executes SCRIPT on SK-SOURCE, where SCRIPT is a list of SQL strings
     * which may be either queries or updates.  Note, however, that the
     * results of neither the queries nor the updates will be returned.
     * The only return value will be whether there was an error during
     * execution.  If there was an error, then all DB operations will be
     * rolled back.
     */
    @LispMethod(comment = "Executes SCRIPT on SK-SOURCE, where SCRIPT is a list of SQL strings\r\nwhich may be either queries or updates.  Note, however, that the\r\nresults of neither the queries nor the updates will be returned.\r\nThe only return value will be whether there was an error during\r\nexecution.  If there was an error, then all DB operations will be\r\nrolled back.\nExecutes SCRIPT on SK-SOURCE, where SCRIPT is a list of SQL strings\nwhich may be either queries or updates.  Note, however, that the\nresults of neither the queries nor the updates will be returned.\nThe only return value will be whether there was an error during\nexecution.  If there was an error, then all DB operations will be\nrolled back.")
    public static SubLObject sksi_execute_sql_script(final SubLObject script, final SubLObject sk_source) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject access_path = sksi_access_path.external_source_access_path(sk_source, UNPROVIDED, UNPROVIDED);
        SubLObject error = NIL;
        final SubLObject db = sksi_access_path.access_path_db(access_path);
        final SubLObject user = sksi_access_path.access_path_user(access_path);
        final SubLObject password = sksi_access_path.access_path_password(access_path);
        final SubLObject server = sksi_access_path.access_path_server(access_path);
        final SubLObject port = sksi_access_path.access_path_port(access_path);
        final SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
        final SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
        final SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
        final SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
        final SubLObject conn_server = (NIL != proxy_server) ? proxy_server : server;
        final SubLObject conn_port = (NIL != proxy_port) ? proxy_port : port;
        final SubLObject conn_timeout = (NIL != timeout) ? timeout : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
        SubLObject statement = NIL;
        SubLObject connection = NIL;
        try {
            connection = new_sql_connection(db, user, password, list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout }));
            if (NIL != sql_open_connection_p(connection)) {
                statement = sqlc_create_statement(connection);
            } else {
                statement = connection;
            }
            if (NIL != sql_open_statement_p(statement)) {
                final SubLObject connection_$9 = sqls_get_connection(statement);
                final SubLObject auto_commit = sqlc_get_auto_commit(connection_$9);
                SubLObject commit_error = NIL;
                SubLObject rollback_result = NIL;
                SubLObject auto_commit_result = NIL;
                SubLObject errors = NIL;
                if (NIL != sdbc_error_p(auto_commit)) {
                    errors = cons(auto_commit, errors);
                } else {
                    sqlc_set_error_handling(connection_$9, $THROW);
                    try {
                        thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                        final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                        try {
                            Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                            try {
                                if (NIL != auto_commit) {
                                    sqlc_set_auto_commit(connection_$9, NIL);
                                }
                                SubLObject cdolist_list_var = script;
                                SubLObject sql = NIL;
                                sql = cdolist_list_var.first();
                                while (NIL != cdolist_list_var) {
                                    sql = left_trim_whitespace(sql);
                                    if (NIL == empty_string_p(sql)) {
                                        if (NIL != starts_with_by_test(sql, sksi_sks_interaction.$$$SELECT, symbol_function(EQUALP))) {
                                            final SubLObject result_set = sqls_execute_query(statement, sql, UNPROVIDED);
                                            if (NIL != sql_open_result_set_p(result_set)) {
                                                sqlrs_close(result_set);
                                            }
                                        } else {
                                            sqls_execute_update(statement, sql);
                                        }
                                    }
                                    cdolist_list_var = cdolist_list_var.rest();
                                    sql = cdolist_list_var.first();
                                } 
                                sqlc_commit(connection_$9);
                            } catch (final Throwable catch_var) {
                                Errors.handleThrowable(catch_var, NIL);
                            }
                        } finally {
                            Errors.$error_handler$.rebind(_prev_bind_0, thread);
                        }
                    } catch (final Throwable ccatch_env_var) {
                        commit_error = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                    } finally {
                        thread.throwStack.pop();
                    }
                    sqlc_set_error_handling(connection_$9, $RETURN);
                    if (NIL != commit_error) {
                        errors = cons(sqls_handle_commit_error(commit_error), errors);
                        rollback_result = sqls_handle_rollback(connection_$9);
                        if (NIL != sdbc_error_p(rollback_result)) {
                            errors = cons(rollback_result, errors);
                        }
                    }
                    if (NIL != auto_commit) {
                        auto_commit_result = sqlc_set_auto_commit(connection_$9, T);
                    }
                    if (NIL != sdbc_error_p(auto_commit_result)) {
                        errors = cons(auto_commit_result, errors);
                    }
                }
                if (NIL != errors) {
                    error = sqls_handle_transaction_errors(errors);
                }
            }
        } finally {
            final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values = getValuesAsVector();
                if (NIL != sql_connection_p(connection)) {
                    sqlc_close(connection);
                }
                restoreValuesFromVector(_values);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
            }
        }
        if (NIL != error) {
            sdbc_error_warn(error);
        }
        return sublisp_null(error);
    }

    /**
     * Opens a connection to the source specified by ACCESS-PATH.  MAX-TRIES
     * indicates the maximum number of attempts that will be made to open the
     * connection.  MAX-TRIES should be a positive integer, but regardless of
     * its value, at least one attempt will be made.  INTERVAL is the number
     * of seconds the function will wait between successive attempts to open
     * a connection.
     */
    @LispMethod(comment = "Opens a connection to the source specified by ACCESS-PATH.  MAX-TRIES\r\nindicates the maximum number of attempts that will be made to open the\r\nconnection.  MAX-TRIES should be a positive integer, but regardless of\r\nits value, at least one attempt will be made.  INTERVAL is the number\r\nof seconds the function will wait between successive attempts to open\r\na connection.\nOpens a connection to the source specified by ACCESS-PATH.  MAX-TRIES\nindicates the maximum number of attempts that will be made to open the\nconnection.  MAX-TRIES should be a positive integer, but regardless of\nits value, at least one attempt will be made.  INTERVAL is the number\nof seconds the function will wait between successive attempts to open\na connection.")
    public static final SubLObject open_sql_source_alt(SubLObject access_path, SubLObject max_tries, SubLObject interval) {
        if (max_tries == UNPROVIDED) {
            max_tries = TEN_INTEGER;
        }
        if (interval == UNPROVIDED) {
            interval = $float$0_1;
        }
        {
            SubLObject db = sksi_access_path.access_path_db(access_path);
            SubLObject user = sksi_access_path.access_path_user(access_path);
            SubLObject password = sksi_access_path.access_path_password(access_path);
            SubLObject server = sksi_access_path.access_path_server(access_path);
            SubLObject port = sksi_access_path.access_path_port(access_path);
            SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
            SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
            SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
            SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
            SubLObject conn_server = (NIL != proxy_server) ? ((SubLObject) (proxy_server)) : server;
            SubLObject conn_port = (NIL != proxy_port) ? ((SubLObject) (proxy_port)) : port;
            SubLObject conn_timeout = (NIL != timeout) ? ((SubLObject) (timeout)) : $sksi_open_sql_connection_default_timeout$.getGlobalValue();
            SubLObject plist = list(new SubLObject[]{ $DBMS_SERVER, server, $PORT, conn_port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, conn_server, $TIMEOUT, conn_timeout });
            SubLObject result = sdbc.new_sql_connection(db, user, password, plist);
            if (NIL == sdbc.sql_open_connection_p(result)) {
                if (NIL != number_utilities.positive_number_p(interval)) {
                    {
                        SubLObject v_tries = NIL;
                        for (v_tries = ONE_INTEGER; !((NIL != sdbc.sql_open_connection_p(result)) || v_tries.numGE(max_tries)); v_tries = add(v_tries, ONE_INTEGER)) {
                            sleep(interval);
                            result = sdbc.new_sql_connection(db, user, password, plist);
                        }
                    }
                } else {
                    {
                        SubLObject v_tries = NIL;
                        for (v_tries = ONE_INTEGER; !((NIL != sdbc.sql_open_connection_p(result)) || v_tries.numGE(max_tries)); v_tries = add(v_tries, ONE_INTEGER)) {
                            result = sdbc.new_sql_connection(db, user, password, plist);
                        }
                    }
                }
            }
            if (NIL != sdbc.sdbc_error_p(result)) {
                result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_db_sks_error(access_path, result);
            }
            return result;
        }
    }

    /**
     * Opens a connection to the source specified by ACCESS-PATH.  MAX-TRIES
     * indicates the maximum number of attempts that will be made to open the
     * connection.  MAX-TRIES should be a positive integer, but regardless of
     * its value, at least one attempt will be made.  INTERVAL is the number
     * of seconds the function will wait between successive attempts to open
     * a connection.
     */
    @LispMethod(comment = "Opens a connection to the source specified by ACCESS-PATH.  MAX-TRIES\r\nindicates the maximum number of attempts that will be made to open the\r\nconnection.  MAX-TRIES should be a positive integer, but regardless of\r\nits value, at least one attempt will be made.  INTERVAL is the number\r\nof seconds the function will wait between successive attempts to open\r\na connection.\nOpens a connection to the source specified by ACCESS-PATH.  MAX-TRIES\nindicates the maximum number of attempts that will be made to open the\nconnection.  MAX-TRIES should be a positive integer, but regardless of\nits value, at least one attempt will be made.  INTERVAL is the number\nof seconds the function will wait between successive attempts to open\na connection.")
    public static SubLObject open_sql_source(final SubLObject access_path, SubLObject max_tries, SubLObject interval) {
        if (max_tries == UNPROVIDED) {
            max_tries = sksi_sks_interaction.$sksi_open_sql_source_default_max_tries$.getDynamicValue();
        }
        if (interval == UNPROVIDED) {
            interval = sksi_sks_interaction.$sksi_open_sql_source_default_interval$.getDynamicValue();
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject db = sksi_access_path.access_path_db(access_path);
        final SubLObject user = sksi_access_path.access_path_user(access_path);
        final SubLObject password = sksi_access_path.access_path_password(access_path);
        final SubLObject server = sksi_access_path.access_path_server(access_path);
        final SubLObject port = sksi_access_path.access_path_port(access_path);
        SubLObject proxy_server = sksi_access_path.access_path_proxy_server(access_path);
        SubLObject proxy_port = sksi_access_path.access_path_proxy_port(access_path);
        final SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
        final SubLObject timeout = sksi_access_path.access_path_timeout(access_path);
        final SubLObject conn_timeout = (NIL != timeout) ? timeout : sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.getDynamicValue(thread);
        if (NIL == proxy_port) {
            proxy_port = $sql_port$.getGlobalValue();
        }
        if (NIL == proxy_server) {
            proxy_server = server;
        }
        final SubLObject plist = list(new SubLObject[]{ $DBMS_SERVER, server, $DBMS_PORT, port, $SUBPROTOCOL, subprotocol, $PROXY_SERVER, proxy_server, $PORT, proxy_port, $TIMEOUT, conn_timeout });
        SubLObject result = new_sql_connection(db, user, password, plist);
        if (NIL == sql_open_connection_p(result)) {
            if (NIL != positive_number_p(interval)) {
                SubLObject v_tries;
                for (v_tries = NIL, v_tries = ONE_INTEGER; (NIL == sql_open_connection_p(result)) && (!v_tries.numGE(max_tries)); result = new_sql_connection(db, user, password, plist) , v_tries = add(v_tries, ONE_INTEGER)) {
                    sleep(interval);
                }
            } else {
                SubLObject v_tries;
                for (v_tries = NIL, v_tries = ONE_INTEGER; (NIL == sql_open_connection_p(result)) && (!v_tries.numGE(max_tries)); result = new_sql_connection(db, user, password, plist) , v_tries = add(v_tries, ONE_INTEGER)) {
                }
            }
        }
        if (NIL != sdbc_error_p(result)) {
            result = sksi_sks_interaction.sksi_unreachable_db_sks_error(access_path, result);
        }
        return result;
    }

    public static final SubLObject sksi_open_sql_connection_and_statement_alt(SubLObject access_path) {
        {
            SubLObject connection = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_sql_source(access_path, UNPROVIDED, UNPROVIDED);
            SubLObject statement = (NIL != sdbc.sql_open_connection_p(connection)) ? ((SubLObject) (sdbc.sqlc_create_statement(connection))) : NIL;
            return values(connection, statement);
        }
    }

    public static SubLObject sksi_open_sql_connection_and_statement(final SubLObject access_path) {
        final SubLObject connection = sksi_sks_interaction.open_sql_source(access_path, UNPROVIDED, UNPROVIDED);
        final SubLObject statement = (NIL != sql_open_connection_p(connection)) ? sqlc_create_statement(connection) : NIL;
        return values(connection, statement);
    }

    public static final SubLObject sksi_abort_sql_statement_and_connection_alt(SubLObject statement, SubLObject connection, SubLObject close_statement_connectionP) {
        if (close_statement_connectionP == UNPROVIDED) {
            close_statement_connectionP = T;
        }
        try {
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_abort_sql_statement(statement);
        } finally {
            {
                SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                try {
                    bind($is_thread_performing_cleanupP$, T);
                    if (NIL != close_statement_connectionP) {
                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_close_sql_statement_and_connection(statement, connection);
                    }
                } finally {
                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_abort_sql_statement_and_connection(final SubLObject statement, final SubLObject connection, SubLObject close_statement_connectionP) {
        if (close_statement_connectionP == UNPROVIDED) {
            close_statement_connectionP = T;
        }
        try {
            sksi_sks_interaction.sksi_abort_sql_statement(statement);
        } finally {
            final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
            try {
                bind($is_thread_performing_cleanupP$, T);
                final SubLObject _values = getValuesAsVector();
                if (NIL != close_statement_connectionP) {
                    sksi_sks_interaction.sksi_close_sql_statement_and_connection(statement, connection);
                }
                restoreValuesFromVector(_values);
            } finally {
                rebind($is_thread_performing_cleanupP$, _prev_bind_0);
            }
        }
        return NIL;
    }

    public static final SubLObject sksi_abort_sql_statement_alt(SubLObject statement) {
        if (NIL != sdbc.sql_open_statement_p(statement)) {
            sdbc.sqls_cancel(statement);
        }
        return NIL;
    }

    public static SubLObject sksi_abort_sql_statement(final SubLObject statement) {
        if (NIL != sql_open_statement_p(statement)) {
            sqls_cancel(statement);
        }
        return NIL;
    }

    public static final SubLObject sksi_close_sql_statement_and_connection_alt(SubLObject statement, SubLObject connection) {
        try {
            if (NIL != sdbc.sql_open_statement_p(statement)) {
                sdbc.sqls_close(statement);
            }
        } finally {
            {
                SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                try {
                    bind($is_thread_performing_cleanupP$, T);
                    com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_close_sql_connection(connection);
                } finally {
                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_close_sql_statement_and_connection(final SubLObject statement, final SubLObject connection) {
        try {
            if (NIL != sql_open_statement_p(statement)) {
                sqls_close(statement);
            }
        } finally {
            final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
            try {
                bind($is_thread_performing_cleanupP$, T);
                final SubLObject _values = getValuesAsVector();
                sksi_sks_interaction.sksi_close_sql_connection(connection);
                restoreValuesFromVector(_values);
            } finally {
                rebind($is_thread_performing_cleanupP$, _prev_bind_0);
            }
        }
        return NIL;
    }

    public static final SubLObject sksi_close_sql_connection_alt(SubLObject connection) {
        return NIL != sdbc.sql_open_connection_p(connection) ? ((SubLObject) (sdbc.sqlc_close(connection))) : NIL;
    }

    public static SubLObject sksi_close_sql_connection(final SubLObject connection) {
        return NIL != sql_open_connection_p(connection) ? sqlc_close(connection) : NIL;
    }

    public static final SubLObject get_result_iterator_from_fht_source_alt(SubLObject csql, SubLObject access_path) {
        {
            SubLObject fht = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_fht_source(access_path);
            SubLObject selected_field_type = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_selected_field_type_from_csql(csql);
            SubLObject pcase_var = selected_field_type;
            if (pcase_var.eql($ALL)) {
                return sksi_result_set_iterators.new_sksi_file_hash_table_iterator(fht);
            } else {
                if (pcase_var.eql($KEY_FIRST)) {
                    return sksi_result_set_iterators.new_sksi_file_hash_table_iterator(fht);
                } else {
                    if (pcase_var.eql($KEY_VALUE_FIRST)) {
                        return sksi_result_set_iterators.new_sksi_reverse_file_hash_table_iterator(fht);
                    } else {
                        if (pcase_var.eql($KEY)) {
                            return sksi_result_set_iterators.new_sksi_file_hash_table_keys_iterator(fht);
                        } else {
                            if (pcase_var.eql($KEY_VALUE)) {
                                return sksi_result_set_iterators.new_sksi_file_hash_table_values_iterator(fht);
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject get_result_iterator_from_fht_source(final SubLObject csql, final SubLObject access_path) {
        final SubLObject fht = sksi_sks_interaction.open_fht_source(access_path);
        final SubLObject pcase_var;
        final SubLObject selected_field_type = pcase_var = sksi_sks_interaction.get_selected_field_type_from_csql(csql);
        if (pcase_var.eql($ALL)) {
            return sksi_result_set_iterators.new_sksi_file_hash_table_iterator(fht);
        }
        if (pcase_var.eql($KEY_FIRST)) {
            return sksi_result_set_iterators.new_sksi_file_hash_table_iterator(fht);
        }
        if (pcase_var.eql(sksi_sks_interaction.$KEY_VALUE_FIRST)) {
            return sksi_result_set_iterators.new_sksi_reverse_file_hash_table_iterator(fht);
        }
        if (pcase_var.eql($KEY)) {
            return sksi_result_set_iterators.new_sksi_file_hash_table_keys_iterator(fht);
        }
        if (pcase_var.eql($KEY_VALUE)) {
            return sksi_result_set_iterators.new_sksi_file_hash_table_values_iterator(fht);
        }
        return NIL;
    }

    public static final SubLObject get_selected_field_type_from_csql_alt(SubLObject csql) {
        {
            SubLObject fields = second(csql);
            if (fields.equal($list_alt125)) {
                return $ALL;
            } else {
                if ((NIL != list_utilities.tree_find($KEY, fields, UNPROVIDED, UNPROVIDED)) && (NIL != list_utilities.tree_find($KEY_VALUE, fields, UNPROVIDED, UNPROVIDED))) {
                    if (position($KEY, fields, symbol_function(EQ), symbol_function(CAR), UNPROVIDED, UNPROVIDED).numL(position($KEY_VALUE, fields, symbol_function(EQ), symbol_function(CAR), UNPROVIDED, UNPROVIDED))) {
                        return $KEY_FIRST;
                    } else {
                        return $KEY_VALUE_FIRST;
                    }
                } else {
                    if (NIL != list_utilities.tree_find($KEY, fields, UNPROVIDED, UNPROVIDED)) {
                        return $KEY;
                    } else {
                        if (NIL != list_utilities.tree_find($KEY_VALUE, fields, UNPROVIDED, UNPROVIDED)) {
                            return $KEY_VALUE;
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject get_selected_field_type_from_csql(final SubLObject csql) {
        final SubLObject fields = second(csql);
        if (fields.equal(sksi_sks_interaction.$list127)) {
            return $ALL;
        }
        if ((NIL != tree_find($KEY, fields, UNPROVIDED, UNPROVIDED)) && (NIL != tree_find($KEY_VALUE, fields, UNPROVIDED, UNPROVIDED))) {
            if (position($KEY, fields, symbol_function(EQ), symbol_function(CAR), UNPROVIDED, UNPROVIDED).numL(position($KEY_VALUE, fields, symbol_function(EQ), symbol_function(CAR), UNPROVIDED, UNPROVIDED))) {
                return $KEY_FIRST;
            }
            return sksi_sks_interaction.$KEY_VALUE_FIRST;
        } else {
            if (NIL != tree_find($KEY, fields, UNPROVIDED, UNPROVIDED)) {
                return $KEY;
            }
            if (NIL != tree_find($KEY_VALUE, fields, UNPROVIDED, UNPROVIDED)) {
                return $KEY_VALUE;
            }
            return NIL;
        }
    }

    public static final SubLObject get_result_for_key_from_fht_source_alt(SubLObject key, SubLObject access_path) {
        {
            SubLObject fht = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_fht_source(access_path);
            SubLObject result = NIL;
            if (NIL != file_hash_table.file_hash_table_p(fht)) {
                try {
                    result = file_hash_table.get_file_hash_table(key, fht, UNPROVIDED);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.finalize_fht_source(fht);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
                return result;
            }
        }
        return NIL;
    }

    public static SubLObject get_result_for_key_from_fht_source(final SubLObject key, final SubLObject access_path) {
        final SubLObject fht = sksi_sks_interaction.open_fht_source(access_path);
        SubLObject result = NIL;
        if (NIL != file_hash_table_p(fht)) {
            try {
                result = get_file_hash_table(key, fht, UNPROVIDED);
            } finally {
                final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                try {
                    bind($is_thread_performing_cleanupP$, T);
                    final SubLObject _values = getValuesAsVector();
                    sksi_sks_interaction.finalize_fht_source(fht);
                    restoreValuesFromVector(_values);
                } finally {
                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                }
            }
            return result;
        }
        return NIL;
    }

    public static final SubLObject get_result_for_key_value_from_fht_source_alt(SubLObject key_value, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject fht = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_fht_source(access_path);
                SubLObject value_test_fn = sksi_access_path.access_path_value_test_function(access_path);
                SubLObject v_answer = NIL;
                if (NIL != file_hash_table.file_hash_table_p(fht)) {
                    try {
                        {
                            SubLObject continuation = NIL;
                            SubLObject completeP = NIL;
                            while (NIL == completeP) {
                                thread.resetMultipleValues();
                                {
                                    SubLObject the_key = file_hash_table.get_file_hash_table_any(fht, continuation, NIL);
                                    SubLObject the_value = thread.secondMultipleValue();
                                    SubLObject next = thread.thirdMultipleValue();
                                    thread.resetMultipleValues();
                                    if (NIL != next) {
                                        {
                                            SubLObject key = the_key;
                                            SubLObject value = the_value;
                                            if (NIL != funcall(value_test_fn, value, key_value)) {
                                                v_answer = cons(list(key), v_answer);
                                            }
                                        }
                                    }
                                    continuation = next;
                                    completeP = sublisp_null(next);
                                }
                            } 
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.finalize_fht_source(fht);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                    return v_answer;
                }
            }
            return NIL;
        }
    }

    public static SubLObject get_result_for_key_value_from_fht_source(final SubLObject key_value, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject fht = sksi_sks_interaction.open_fht_source(access_path);
        final SubLObject value_test_fn = sksi_access_path.access_path_value_test_function(access_path);
        SubLObject v_answer = NIL;
        if (NIL != file_hash_table_p(fht)) {
            try {
                SubLObject continuation = NIL;
                SubLObject next;
                for (SubLObject completeP = NIL; NIL == completeP; completeP = sublisp_null(next)) {
                    thread.resetMultipleValues();
                    final SubLObject the_key = get_file_hash_table_any(fht, continuation, NIL);
                    final SubLObject the_value = thread.secondMultipleValue();
                    next = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL != next) {
                        final SubLObject key = the_key;
                        final SubLObject value = the_value;
                        if (NIL != funcall(value_test_fn, value, key_value)) {
                            v_answer = cons(list(key), v_answer);
                        }
                    }
                    continuation = next;
                }
            } finally {
                final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    sksi_sks_interaction.finalize_fht_source(fht);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                }
            }
            return v_answer;
        }
        return NIL;
    }

    public static final SubLObject put_key_value_under_key_in_fht_source_alt(SubLObject key, SubLObject key_value, SubLObject access_path) {
        {
            SubLObject fht = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_fht_source(access_path);
            SubLObject result = NIL;
            if (NIL != file_hash_table.file_hash_table_p(fht)) {
                try {
                    result = file_hash_table.put_file_hash_table(key, fht, key_value);
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.finalize_fht_source(fht);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
                return result;
            }
        }
        return NIL;
    }

    public static SubLObject put_key_value_under_key_in_fht_source(final SubLObject key, final SubLObject key_value, final SubLObject access_path) {
        final SubLObject fht = sksi_sks_interaction.open_fht_source(access_path);
        SubLObject result = NIL;
        if (NIL != file_hash_table_p(fht)) {
            try {
                result = put_file_hash_table(key, fht, key_value);
            } finally {
                final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                try {
                    bind($is_thread_performing_cleanupP$, T);
                    final SubLObject _values = getValuesAsVector();
                    sksi_sks_interaction.finalize_fht_source(fht);
                    restoreValuesFromVector(_values);
                } finally {
                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                }
            }
            return result;
        }
        return NIL;
    }

    public static final SubLObject remove_key_value_under_key_in_fht_source_alt(SubLObject key, SubLObject key_value, SubLObject access_path) {
        {
            SubLObject fht = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_fht_source(access_path);
            SubLObject result = NIL;
            if (NIL != file_hash_table.file_hash_table_p(fht)) {
                try {
                    {
                        SubLObject actual_value = file_hash_table.get_file_hash_table(key, fht, UNPROVIDED);
                        SubLObject test_fn = sksi_access_path.access_path_value_test_function(access_path);
                        if (NIL != funcall(test_fn, actual_value, key_value)) {
                            result = file_hash_table.remove_file_hash_table(key, fht);
                        }
                    }
                } finally {
                    {
                        SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                        try {
                            bind($is_thread_performing_cleanupP$, T);
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.finalize_fht_source(fht);
                        } finally {
                            rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                        }
                    }
                }
                return result;
            }
        }
        return NIL;
    }

    public static SubLObject remove_key_value_under_key_in_fht_source(final SubLObject key, final SubLObject key_value, final SubLObject access_path) {
        final SubLObject fht = sksi_sks_interaction.open_fht_source(access_path);
        SubLObject result = NIL;
        if (NIL != file_hash_table_p(fht)) {
            try {
                final SubLObject actual_value = get_file_hash_table(key, fht, UNPROVIDED);
                final SubLObject test_fn = sksi_access_path.access_path_value_test_function(access_path);
                if (NIL != funcall(test_fn, actual_value, key_value)) {
                    result = remove_file_hash_table(key, fht);
                }
            } finally {
                final SubLObject _prev_bind_0 = currentBinding($is_thread_performing_cleanupP$);
                try {
                    bind($is_thread_performing_cleanupP$, T);
                    final SubLObject _values = getValuesAsVector();
                    sksi_sks_interaction.finalize_fht_source(fht);
                    restoreValuesFromVector(_values);
                } finally {
                    rebind($is_thread_performing_cleanupP$, _prev_bind_0);
                }
            }
            return result;
        }
        return NIL;
    }

    public static final SubLObject create_fht_source_alt(SubLObject access_path) {
        {
            SubLObject filename = sksi_access_path.access_path_filename(access_path);
            SubLObject test_fn = sksi_access_path.access_path_key_test_function(access_path);
            SubLObject serialization = sksi_access_path.access_path_serialization_type(access_path);
            if ((NIL != filename) && (NIL != Filesys.probe_file(filename))) {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_create_fht_sks_error(access_path, $str_alt129$The_file_already_exists_);
            }
            {
                SubLObject fht = NIL;
                SubLObject error_message = NIL;
                try {
                    {
                        SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                        try {
                            bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                            try {
                                fht = file_hash_table.create_file_hash_table(filename, $sksi_fht_source_default_size$.getGlobalValue(), $sksi_fht_source_default_average_bucket_size$.getGlobalValue(), test_fn, serialization);
                            } catch (Throwable catch_var) {
                                Errors.handleThrowable(catch_var, NIL);
                            }
                        } finally {
                            rebind(Errors.$error_handler$, _prev_bind_0);
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    error_message = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                }
                if (NIL != error_message) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_create_fht_sks_error(access_path, error_message);
                } else {
                    return file_hash_table.finalize_file_hash_table(fht);
                }
            }
        }
    }

    public static SubLObject create_fht_source(final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject filename = sksi_access_path.access_path_filename(access_path);
        final SubLObject test_fn = sksi_access_path.access_path_key_test_function(access_path);
        final SubLObject serialization = sksi_access_path.access_path_serialization_type(access_path);
        if ((NIL != filename) && (NIL != Filesys.probe_file(filename))) {
            return sksi_sks_interaction.sksi_create_fht_sks_error(access_path, sksi_sks_interaction.$str131$The_file_already_exists_);
        }
        SubLObject fht = NIL;
        SubLObject error_message = NIL;
        try {
            thread.throwStack.push($catch_error_message_target$.getGlobalValue());
            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
            try {
                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                try {
                    fht = create_file_hash_table(filename, sksi_sks_interaction.$sksi_fht_source_default_size$.getGlobalValue(), sksi_sks_interaction.$sksi_fht_source_default_average_bucket_size$.getGlobalValue(), test_fn, serialization);
                } catch (final Throwable catch_var) {
                    Errors.handleThrowable(catch_var, NIL);
                }
            } finally {
                Errors.$error_handler$.rebind(_prev_bind_0, thread);
            }
        } catch (final Throwable ccatch_env_var) {
            error_message = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
        } finally {
            thread.throwStack.pop();
        }
        if (NIL != error_message) {
            return sksi_sks_interaction.sksi_create_fht_sks_error(access_path, error_message);
        }
        return finalize_file_hash_table(fht);
    }

    public static final SubLObject open_fht_source_alt(SubLObject access_path) {
        {
            SubLObject filename = sksi_access_path.access_path_filename(access_path);
            SubLObject test_fn = sksi_access_path.access_path_key_test_function(access_path);
            SubLObject serialization = sksi_access_path.access_path_serialization_type(access_path);
            if (!((NIL != filename) && (NIL != Filesys.probe_file(filename)))) {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_fht_sks_error(access_path, NIL);
            }
            {
                SubLObject fht = NIL;
                SubLObject error_message = NIL;
                try {
                    {
                        SubLObject _prev_bind_0 = currentBinding(Errors.$error_handler$);
                        try {
                            bind(Errors.$error_handler$, CATCH_ERROR_MESSAGE_HANDLER);
                            try {
                                fht = file_hash_table.open_file_hash_table(filename, test_fn, serialization);
                            } catch (Throwable catch_var) {
                                Errors.handleThrowable(catch_var, NIL);
                            }
                        } finally {
                            rebind(Errors.$error_handler$, _prev_bind_0);
                        }
                    }
                } catch (Throwable ccatch_env_var) {
                    error_message = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                }
                if (NIL != error_message) {
                    return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_fht_sks_error(access_path, error_message);
                } else {
                    return fht;
                }
            }
        }
    }

    public static SubLObject open_fht_source(final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject filename = sksi_access_path.access_path_filename(access_path);
        final SubLObject test_fn = sksi_access_path.access_path_key_test_function(access_path);
        final SubLObject serialization = sksi_access_path.access_path_serialization_type(access_path);
        if ((NIL == filename) || (NIL == Filesys.probe_file(filename))) {
            return sksi_sks_interaction.sksi_unreachable_fht_sks_error(access_path, NIL);
        }
        SubLObject fht = NIL;
        SubLObject error_message = NIL;
        try {
            thread.throwStack.push($catch_error_message_target$.getGlobalValue());
            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
            try {
                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                try {
                    fht = open_file_hash_table(filename, test_fn, serialization);
                } catch (final Throwable catch_var) {
                    Errors.handleThrowable(catch_var, NIL);
                }
            } finally {
                Errors.$error_handler$.rebind(_prev_bind_0, thread);
            }
        } catch (final Throwable ccatch_env_var) {
            error_message = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
        } finally {
            thread.throwStack.pop();
        }
        if (NIL != error_message) {
            return sksi_sks_interaction.sksi_unreachable_fht_sks_error(access_path, error_message);
        }
        return fht;
    }

    public static final SubLObject finalize_fht_source_alt(SubLObject fht) {
        return file_hash_table.finalize_file_hash_table(fht);
    }

    public static SubLObject finalize_fht_source(final SubLObject fht) {
        return finalize_file_hash_table(fht);
    }

    public static final SubLObject cache_sql_connection_statement_for_result_set_alt(SubLObject result_set, SubLObject connection, SubLObject statement) {
        return dictionary.dictionary_enter($result_set_sql_connection_statement_cache$.getGlobalValue(), result_set, list(connection, statement));
    }

    public static SubLObject cache_sql_connection_statement_for_result_set(final SubLObject result_set, final SubLObject connection, final SubLObject statement) {
        return dictionary_utilities.synchronized_dictionary_enter(sksi_sks_interaction.$result_set_sql_connection_statement_cache$.getGlobalValue(), result_set, list(connection, statement));
    }

    public static final SubLObject uncache_sql_connection_statement_for_result_set_alt(SubLObject result_set) {
        return dictionary.dictionary_remove($result_set_sql_connection_statement_cache$.getGlobalValue(), result_set);
    }

    public static SubLObject uncache_sql_connection_statement_for_result_set(final SubLObject result_set) {
        return dictionary_utilities.synchronized_dictionary_remove(sksi_sks_interaction.$result_set_sql_connection_statement_cache$.getGlobalValue(), result_set);
    }

    public static final SubLObject get_sql_connection_statement_for_result_set_alt(SubLObject result_set) {
        return dictionary.dictionary_lookup_without_values($result_set_sql_connection_statement_cache$.getGlobalValue(), result_set, UNPROVIDED);
    }

    public static SubLObject get_sql_connection_statement_for_result_set(final SubLObject result_set) {
        return dictionary_utilities.synchronized_dictionary_lookup_without_values(sksi_sks_interaction.$result_set_sql_connection_statement_cache$.getGlobalValue(), result_set, UNPROVIDED);
    }

    public static SubLObject clear_cached_get_result_set_from_sql_source() {
        final SubLObject cs = sksi_sks_interaction.$cached_get_result_set_from_sql_source_caching_state$.getGlobalValue();
        if (NIL != cs) {
            caching_state_clear(cs);
        }
        return NIL;
    }

    public static SubLObject remove_cached_get_result_set_from_sql_source(final SubLObject sql, final SubLObject access_path) {
        return caching_state_remove_function_results_with_args(sksi_sks_interaction.$cached_get_result_set_from_sql_source_caching_state$.getGlobalValue(), list(sql, access_path), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject cached_get_result_set_from_sql_source_internal(final SubLObject sql, final SubLObject access_path) {
        return sksi_sks_interaction.get_result_set_from_sql_source(sql, access_path);
    }

    public static SubLObject cached_get_result_set_from_sql_source(final SubLObject sql, final SubLObject access_path) {
        SubLObject caching_state = sksi_sks_interaction.$cached_get_result_set_from_sql_source_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = create_global_caching_state_for_name(sksi_sks_interaction.CACHED_GET_RESULT_SET_FROM_SQL_SOURCE, sksi_sks_interaction.$cached_get_result_set_from_sql_source_caching_state$, sksi_sks_interaction.$int$500, EQUAL, TWO_INTEGER, ZERO_INTEGER);
        }
        final SubLObject sxhash = sxhash_calc_2(sql, access_path);
        final SubLObject collisions = caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql($memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (sql.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && access_path.equal(cached_args.first())) {
                        return caching_results(results2);
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(sksi_sks_interaction.cached_get_result_set_from_sql_source_internal(sql, access_path)));
        caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(sql, access_path));
        return caching_results(results3);
    }

    public static final SubLObject get_result_set_from_sql_source_alt(SubLObject sql, SubLObject access_path) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_sql_query(sql, access_path);
    }

    public static SubLObject get_result_set_from_sql_source(final SubLObject sql, final SubLObject access_path) {
        return sksi_sks_interaction.sksi_execute_sql_query(sql, access_path);
    }

    public static final SubLObject get_result_set_from_sparql_source(SubLObject sparql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != kbq_query_run.$kbq_progress_stream$.getDynamicValue(thread)) {
                format(kbq_query_run.$kbq_progress_stream$.getDynamicValue(thread), $str_alt67$___A____, sparql);
            }
            if ((NIL != $sparql_evaluation_enabledP$.getDynamicValue(thread)) && (NIL != $sparql_caching_enabledP$.getDynamicValue(thread))) {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.cached_get_result_set_from_sparql_source(sparql, access_path);
            } else {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_set_from_sparql_source_guts(sparql, access_path);
            }
        }
    }

    public static SubLObject get_result_set_from_sparql_source(final SubLObject sparql, final SubLObject access_path, SubLObject params, SubLObject extract_rdf_literalsP) {
        if (params == UNPROVIDED) {
            params = NIL;
        }
        if (extract_rdf_literalsP == UNPROVIDED) {
            extract_rdf_literalsP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != $kbq_progress_stream$.getDynamicValue(thread)) {
            format($kbq_progress_stream$.getDynamicValue(thread), sksi_sks_interaction.$str69$___A____, sparql);
        }
        if ((NIL != sksi_sks_interaction.$sparql_evaluation_enabledP$.getDynamicValue(thread)) && (NIL != sksi_result_set_iterators.$sksi_caching_enabledP$.getDynamicValue(thread))) {
            return sksi_sks_interaction.cached_get_result_set_from_sparql_source(sparql, access_path, params, extract_rdf_literalsP);
        }
        return sksi_sks_interaction.get_result_set_from_sparql_source_guts(sparql, access_path, params, extract_rdf_literalsP);
    }

    public static final SubLObject get_result_set_from_sparql_source_guts(SubLObject sparql, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            SubLTrampolineFile.checkType(sparql, STRINGP);
            SubLTrampolineFile.checkType(access_path, DICTIONARY_P);
            {
                SubLObject variable_names = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_extract_variable_names(sparql);
                SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
                SubLObject result_set = NIL;
                if (NIL != $query_interrupt_on_sparql_execution$.getDynamicValue(thread)) {
                    inference_strategist.query_interrupt(TEN_INTEGER);
                    return NIL;
                }
                {
                    SubLObject inferences = inference_macros.current_controlling_inferences();
                    SubLObject inference = list_utilities.last_one(inferences);
                    SubLObject start = NIL;
                    SubLObject end = NIL;
                    SubLObject count = NIL;
                    try {
                        start = (NIL != inference) ? ((SubLObject) (inference_datastructures_inference.inference_cumulative_time_so_far(inference, NIL))) : NIL;
                        if (NIL != $sparql_result_stream_iterator_enabledP$.getDynamicValue(thread)) {
                            {
                                SubLObject sparql_ticket = NIL;
                                SubLObject xml_stream = NIL;
                                SubLObject completedP = NIL;
                                try {
                                    {
                                        SubLObject inferences_var = inference_macros.current_controlling_inferences();
                                        SubLObject inference_var = inference_macros.current_controlling_inference();
                                        SubLObject tactic_var = inference_worker.currently_executing_tactic();
                                        SubLObject start_time = NIL;
                                        try {
                                            inference_datastructures_inference.possibly_signal_sksi_query_start(inference_var, tactic_var);
                                            start_time = inference_datastructures_inference.inference_time_so_far(inference_var, NIL);
                                            inference_datastructures_inference.possibly_add_inference_sksi_query_start_time(inferences_var, start_time);
                                            if (NIL != $sparql_evaluation_enabledP$.getDynamicValue(thread)) {
                                                sparql_ticket = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_request_query_ticket(access_path);
                                                xml_stream = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_sparql_query_result_stream(sparql, sparql_ticket, access_path);
                                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.stream_wait_until_read_char(xml_stream);
                                            } else {
                                                sksi_query_utilities.sksi_suspend_cost_recording_for_current_iterator();
                                            }
                                        } finally {
                                            {
                                                SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                                try {
                                                    $is_thread_performing_cleanupP$.bind(T, thread);
                                                    inference_datastructures_inference.possibly_signal_sksi_query_end(inference_var, tactic_var);
                                                    inference_datastructures_inference.possibly_increment_inference_sksi_query_total_time(inferences_var, subtract(inference_datastructures_inference.inference_time_so_far(inference_var, NIL), start_time));
                                                } finally {
                                                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                                                }
                                            }
                                        }
                                        result_set = sparql_result_stream.batch_process_sparql_result_stream(xml_stream, variable_names, prefix_map);
                                        completedP = T;
                                    }
                                } finally {
                                    {
                                        SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                        try {
                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                            if (NIL != xml_stream) {
                                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.close_sparql_result_stream(xml_stream);
                                                if (NIL == completedP) {
                                                    if (NIL != sparql_ticket) {
                                                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_abort_query(sparql_ticket, access_path);
                                                    }
                                                }
                                            }
                                        } finally {
                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                                        }
                                    }
                                }
                            }
                        } else {
                            {
                                SubLObject xml_tokens = NIL;
                                SubLObject inferences_var = inference_macros.current_controlling_inferences();
                                SubLObject inference_var = inference_macros.current_controlling_inference();
                                SubLObject tactic_var = inference_worker.currently_executing_tactic();
                                SubLObject start_time = NIL;
                                try {
                                    inference_datastructures_inference.possibly_signal_sksi_query_start(inference_var, tactic_var);
                                    start_time = inference_datastructures_inference.inference_time_so_far(inference_var, NIL);
                                    inference_datastructures_inference.possibly_add_inference_sksi_query_start_time(inferences_var, start_time);
                                    if (NIL != $sparql_evaluation_enabledP$.getDynamicValue(thread)) {
                                        xml_tokens = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_query_xml_tokens(sparql, access_path);
                                    } else {
                                        sksi_query_utilities.sksi_suspend_cost_recording_for_current_iterator();
                                    }
                                } finally {
                                    {
                                        SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                                        try {
                                            $is_thread_performing_cleanupP$.bind(T, thread);
                                            inference_datastructures_inference.possibly_signal_sksi_query_end(inference_var, tactic_var);
                                            inference_datastructures_inference.possibly_increment_inference_sksi_query_total_time(inferences_var, subtract(inference_datastructures_inference.inference_time_so_far(inference_var, NIL), start_time));
                                        } finally {
                                            $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                                        }
                                    }
                                }
                                result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_sparql_xml_tokens_to_result_set(xml_tokens, variable_names, prefix_map);
                            }
                        }
                    } finally {
                        {
                            SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                            try {
                                $is_thread_performing_cleanupP$.bind(T, thread);
                                end = (NIL != inference) ? ((SubLObject) (inference_datastructures_inference.inference_cumulative_time_so_far(inference, NIL))) : NIL;
                                count = length(result_set);
                                inference_datastructures_inference.possibly_add_inference_sparql_query_profile(inferences, start, end, count);
                            } finally {
                                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                            }
                        }
                    }
                }
                return result_set;
            }
        }
    }

    public static SubLObject get_result_set_from_sparql_source_guts(SubLObject sparql, final SubLObject access_path, SubLObject params, final SubLObject extract_rdf_literalsP) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != stringp(sparql) : "! stringp(sparql) " + ("Types.stringp(sparql) " + "CommonSymbols.NIL != Types.stringp(sparql) ") + sparql;
        assert NIL != dictionary_p(access_path) : "! dictionary.dictionary_p(access_path) " + ("dictionary.dictionary_p(access_path) " + "CommonSymbols.NIL != dictionary.dictionary_p(access_path) ") + access_path;
        assert NIL != alist_p(params) : "! list_utilities.alist_p(params) " + ("list_utilities.alist_p(params) " + "CommonSymbols.NIL != list_utilities.alist_p(params) ") + params;
        final SubLObject variable_names = sksi_sks_interaction.sparql_extract_variable_names(sparql);
        final SubLObject prefix_map = sksi_access_path.access_path_rdf_prefix_map(access_path);
        final SubLObject booleanP = sksi_sks_interaction.sparql_extract_booleanP(sparql);
        SubLObject result_set = NIL;
        if (NIL != sksi_sks_interaction.$query_interrupt_on_sparql_execution$.getDynamicValue(thread)) {
            inference_strategist.query_interrupt(TEN_INTEGER);
            return NIL;
        }
        final SubLObject inferences = current_controlling_inferences();
        final SubLObject inference = last_one(inferences);
        SubLObject start = NIL;
        SubLObject end = NIL;
        SubLObject count = NIL;
        try {
            start = (NIL != inference) ? inference_cumulative_time_so_far(inference, NIL) : NIL;
            SubLObject sparql_ticket = NIL;
            SubLObject xml_stream = NIL;
            SubLObject completedP = NIL;
            try {
                final SubLObject inferences_var = current_controlling_inferences();
                final SubLObject inference_var = current_controlling_inference();
                final SubLObject tactic_var = currently_executing_tactic();
                SubLObject start_time = NIL;
                try {
                    possibly_signal_sksi_query_start(inference_var, tactic_var);
                    start_time = inference_time_so_far(inference_var, NIL);
                    possibly_add_inference_sksi_query_start_time(inferences_var, start_time);
                    if (NIL == sksi_sks_interaction.$sparql_evaluation_enabledP$.getDynamicValue(thread)) {
                        sksi_suspend_cost_recording_for_current_iterator();
                    } else {
                        sparql_ticket = sksi_sks_interaction.sparql_request_query_ticket(access_path);
                        if (NIL != sparql_ticket) {
                            params = alist_enter(params, sksi_sks_interaction.$$$ticket, sparql_ticket, UNPROVIDED);
                        }
                        sparql = sksi_sks_interaction.preprocess_sparql_for_backend(sparql, sparql_ticket, access_path);
                        sksi_sks_interaction.inference_possibly_note_salient_sksi_query_string(sparql);
                        xml_stream = sksi_sks_interaction.open_sparql_query_result_stream(sparql, access_path, params);
                        sksi_sks_interaction.stream_wait_until_read_char(xml_stream);
                        result_set = sparql_result_stream.batch_process_sparql_result_stream(xml_stream, variable_names, prefix_map, booleanP, extract_rdf_literalsP);
                    }
                } finally {
                    final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values = getValuesAsVector();
                        possibly_signal_sksi_query_end(inference_var, tactic_var);
                        possibly_increment_inference_sksi_query_total_time(inferences_var, subtract(inference_time_so_far(inference_var, NIL), start_time));
                        restoreValuesFromVector(_values);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
                    }
                }
                completedP = T;
            } finally {
                final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values2 = getValuesAsVector();
                    if (NIL != xml_stream) {
                        sksi_sks_interaction.close_sparql_result_stream(xml_stream);
                        if ((NIL == completedP) && (NIL != sparql_ticket)) {
                            sksi_sks_interaction.sparql_abort_query(sparql_ticket, access_path);
                        }
                    }
                    restoreValuesFromVector(_values2);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
                }
            }
        } finally {
            final SubLObject _prev_bind_3 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values3 = getValuesAsVector();
                end = (NIL != inference) ? inference_cumulative_time_so_far(inference, NIL) : NIL;
                count = length(result_set);
                possibly_add_inference_sparql_query_profile(inferences, start, end, count);
                restoreValuesFromVector(_values3);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_3, thread);
            }
        }
        return result_set;
    }

    public static SubLObject sparql_oracle_subprotocolP(final SubLObject subprotocol) {
        return makeBoolean(subprotocol.equal(sksi_sks_interaction.$sparql_oracle_joseki_subprotocol$.getGlobalValue()) || subprotocol.equal(sksi_sks_interaction.$sparql_oracle_sesame_subprotocol$.getGlobalValue()));
    }

    public static final SubLObject sparql_request_query_ticket_alt(SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject machine = sksi_access_path.access_path_server(access_path);
                SubLObject port = sksi_access_path.access_path_port(access_path);
                SubLObject url = $str_alt133$_ticket;
                SubLObject query = $str_alt134$type_id;
                SubLObject method = $GET;
                SubLObject keep_aliveP = NIL;
                SubLObject wide_newlinesP = T;
                SubLObject open_connection_timeout = $sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
                SubLObject overall_timeout = TEN_INTEGER;
                thread.resetMultipleValues();
                {
                    SubLObject tokens = web_utilities.xml_tokenized_http_request(machine, url, query, method, port, keep_aliveP, wide_newlinesP, open_connection_timeout, overall_timeout, T, $list_alt135);
                    SubLObject errorP = thread.secondMultipleValue();
                    SubLObject error_message = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    {
                        SubLObject sparql_ticket = NIL;
                        if (NIL == errorP) {
                            if (NIL != tokens) {
                                {
                                    SubLObject message_var = NIL;
                                    try {
                                        {
                                            SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                                            try {
                                                Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                                                try {
                                                    {
                                                        SubLObject token = list_utilities.last_one(tokens);
                                                        SubLObject parsed = string_utilities.split_string(token, list(code_char(THIRTEEN_INTEGER)));
                                                        SubLObject candidate = string_utilities.trim_whitespace(list_utilities.last_one(parsed));
                                                        if (NIL != Guids.guid_string_p(candidate)) {
                                                            sparql_ticket = candidate;
                                                        }
                                                    }
                                                } catch (Throwable catch_var) {
                                                    Errors.handleThrowable(catch_var, NIL);
                                                }
                                            } finally {
                                                Errors.$error_handler$.rebind(_prev_bind_0, thread);
                                            }
                                        }
                                    } catch (Throwable ccatch_env_var) {
                                        message_var = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                                    }
                                    if (message_var.isString()) {
                                        Errors.warn($str_alt136$_A, message_var);
                                    }
                                }
                            }
                        }
                        return sparql_ticket;
                    }
                }
            }
        }
    }

    public static SubLObject sparql_request_query_ticket(final SubLObject access_path) {
        final SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
        SubLObject sparql_ticket = NIL;
        if (subprotocol.equal(sksi_sks_interaction.$sparql_oracle_joseki_subprotocol$.getGlobalValue())) {
            sparql_ticket = sksi_sks_interaction.sparql_oracle_joseki_request_query_ticket(access_path);
        } else
            if (subprotocol.equal(sksi_sks_interaction.$sparql_oracle_sesame_subprotocol$.getGlobalValue())) {
                sparql_ticket = sksi_sks_interaction.sparql_oracle_sesame_request_query_ticket(access_path);
            } else
                if (subprotocol.equal(sksi_sks_interaction.$sparql_triclops_subprotocol$.getGlobalValue())) {
                    sparql_ticket = sksi_sks_interaction.sparql_triclops_request_query_ticket(access_path);
                }


        return sparql_ticket;
    }

    public static SubLObject sparql_triclops_request_query_ticket(final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject machine = sksi_access_path.access_path_server(access_path);
        final SubLObject port = sksi_access_path.access_path_port(access_path);
        final SubLObject url = sksi_sks_interaction.$str143$_ticket;
        final SubLObject query = sksi_sks_interaction.$str144$type_id;
        final SubLObject method = $GET;
        final SubLObject keep_aliveP = NIL;
        final SubLObject wide_newlinesP = sksi_sks_interaction.$sksi_sparql_request_wide_newlines$.getDynamicValue(thread);
        final SubLObject open_connection_timeout = sksi_sks_interaction.$sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
        final SubLObject overall_timeout = TEN_INTEGER;
        thread.resetMultipleValues();
        final SubLObject tokens = xml_parsing_utilities.xml_tokenized_http_request(machine, url, query, method, port, keep_aliveP, wide_newlinesP, open_connection_timeout, overall_timeout, T, sksi_sks_interaction.$list145);
        final SubLObject errorP = thread.secondMultipleValue();
        final SubLObject error_message = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        SubLObject sparql_ticket = NIL;
        if ((NIL == errorP) && (NIL != tokens)) {
            SubLObject message_var = NIL;
            final SubLObject was_appendingP = eval(sksi_sks_interaction.$append_stack_traces_to_error_messagesP$);
            eval(sksi_sks_interaction.$list147);
            try {
                try {
                    thread.throwStack.push($catch_error_message_target$.getGlobalValue());
                    final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                    try {
                        Errors.$error_handler$.bind(CATCH_ERROR_MESSAGE_HANDLER, thread);
                        try {
                            final SubLObject token = last_one(tokens);
                            final SubLObject parsed = split_string(token, list(code_char(THIRTEEN_INTEGER)));
                            final SubLObject candidate = trim_whitespace(last_one(parsed));
                            if (NIL != Guids.guid_string_p(candidate)) {
                                sparql_ticket = candidate;
                            }
                        } catch (final Throwable catch_var) {
                            Errors.handleThrowable(catch_var, NIL);
                        }
                    } finally {
                        Errors.$error_handler$.rebind(_prev_bind_0, thread);
                    }
                } catch (final Throwable ccatch_env_var) {
                    message_var = Errors.handleThrowable(ccatch_env_var, $catch_error_message_target$.getGlobalValue());
                } finally {
                    thread.throwStack.pop();
                }
            } finally {
                final SubLObject _prev_bind_2 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    eval(list(CSETQ, sksi_sks_interaction.$append_stack_traces_to_error_messagesP$, was_appendingP));
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_2, thread);
                }
            }
            if (message_var.isString()) {
                Errors.warn(sksi_sks_interaction.$str148$_A, message_var);
            }
        }
        return sparql_ticket;
    }

    public static SubLObject sparql_oracle_joseki_request_query_ticket(SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = NIL;
        }
        return Sxhash.sxhash(Guids.new_guid());
    }

    public static SubLObject sparql_oracle_sesame_request_query_ticket(SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = NIL;
        }
        return sksi_sks_interaction.sparql_oracle_joseki_request_query_ticket(access_path);
    }

    public static SubLObject preprocess_sparql_for_backend(SubLObject sparql, final SubLObject sparql_ticket, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = NIL;
        }
        if (NIL != sparql_ticket) {
            final SubLObject protocol = sksi_access_path.access_path_subprotocol(access_path);
            if (protocol.equal(sksi_sks_interaction.$sparql_oracle_joseki_subprotocol$.getGlobalValue()) || protocol.equal(sksi_sks_interaction.$sparql_oracle_sesame_subprotocol$.getGlobalValue())) {
                final SubLObject select_start = search(sksi_sks_interaction.$$$SELECT, sparql, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                if (NIL != select_start) {
                    final SubLObject pre_select = substring(sparql, ZERO_INTEGER, select_start);
                    final SubLObject select_remainder = substring(sparql, select_start, NIL);
                    final SubLObject prefix_pragma = sksi_sks_interaction.compute_sparql_oracle_prefix_pragma(sparql_ticket);
                    final SubLObject processed_sparql = sparql = cconcatenate(format_nil_a_no_copy(pre_select), new SubLObject[]{ format_nil_a_no_copy(prefix_pragma), format_nil_a_no_copy(select_remainder) });
                }
            }
        }
        return sparql;
    }

    public static SubLObject compute_sparql_oracle_prefix_pragma(final SubLObject sparql_ticket) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject prefix_pragma = NIL;
        SubLObject stream = NIL;
        try {
            stream = make_private_string_output_stream();
            SubLObject prefix = sksi_sks_interaction.$str151$ORACLE_SEM_FS_NS;
            final SubLObject timeout = sksi_sks_interaction.compute_sparql_oracle_query_timeout();
            final SubLObject parallelism = sksi_sks_interaction.$sparql_oracle_default_server_parallelism$.getDynamicValue(thread);
            final SubLObject timeout_pragma = (NIL != timeout) ? cconcatenate(sksi_sks_interaction.$str152$_timeout_, format_nil_a_no_copy(timeout)) : sksi_sks_interaction.$str153$;
            final SubLObject dop = (parallelism.numG(ONE_INTEGER)) ? cconcatenate(sksi_sks_interaction.$str154$_dop_, format_nil_a_no_copy(parallelism)) : sksi_sks_interaction.$str153$;
            final SubLObject prefix_uri = format(NIL, sksi_sks_interaction.$sparql_oracle_fs_prefix_pragma_template$.getGlobalValue(), new SubLObject[]{ sparql_ticket, timeout_pragma, dop });
            sksi_csql_sparql_interpretation.sparql_write_prefix_uri_pair(prefix, prefix_uri, stream);
            prefix = sksi_sks_interaction.$str155$ORACLE_SEM_HT_NS;
            final SubLObject prefix_uri2 = format(NIL, sksi_sks_interaction.$sparql_oracle_ht_prefix_pragma_template$.getGlobalValue());
            sksi_csql_sparql_interpretation.sparql_write_prefix_uri_pair(prefix, prefix_uri2, stream);
            prefix_pragma = get_output_stream_string(stream);
        } finally {
            final SubLObject _prev_bind_0 = $is_thread_performing_cleanupP$.currentBinding(thread);
            try {
                $is_thread_performing_cleanupP$.bind(T, thread);
                final SubLObject _values = getValuesAsVector();
                close(stream, UNPROVIDED);
                restoreValuesFromVector(_values);
            } finally {
                $is_thread_performing_cleanupP$.rebind(_prev_bind_0, thread);
            }
        }
        return prefix_pragma;
    }

    public static SubLObject compute_sparql_oracle_query_timeout() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_sks_interaction.$sparql_oracle_timeout$.getDynamicValue(thread)) {
            final SubLObject inference = current_controlling_inference();
            if (NIL != inference) {
                final SubLObject max_time = inference_max_time(inference);
                if (max_time.isNumber()) {
                    return ceiling(min(max_time, sksi_sks_interaction.$sparql_oracle_timeout$.getDynamicValue(thread)), UNPROVIDED);
                }
            }
            return ceiling(sksi_sks_interaction.$sparql_oracle_timeout$.getDynamicValue(thread), UNPROVIDED);
        }
        return NIL;
    }

    public static final SubLObject close_sparql_result_stream_alt(SubLObject stream) {
        return close(stream, UNPROVIDED);
    }

    public static SubLObject close_sparql_result_stream(final SubLObject stream) {
        return close(stream, UNPROVIDED);
    }

    public static final SubLObject sparql_abort_query_alt(SubLObject sparql_ticket, SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject machine = sksi_access_path.access_path_server(access_path);
                SubLObject port = sksi_access_path.access_path_port(access_path);
                SubLObject url = $str_alt137$_processes;
                SubLObject query = cconcatenate($str_alt138$ticket_, format_nil.format_nil_a_no_copy(sparql_ticket));
                SubLObject method = $POST;
                SubLObject keep_aliveP = NIL;
                SubLObject wide_newlinesP = T;
                SubLObject open_connection_timeout = $sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
                SubLObject overall_timeout = TEN_INTEGER;
                thread.resetMultipleValues();
                {
                    SubLObject tokens = web_utilities.xml_tokenized_http_request(machine, url, query, method, port, keep_aliveP, wide_newlinesP, open_connection_timeout, overall_timeout, T, $list_alt135);
                    SubLObject errorP = thread.secondMultipleValue();
                    SubLObject error_message = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    return makeBoolean(NIL == errorP);
                }
            }
        }
    }

    public static SubLObject sparql_abort_query(final SubLObject sparql_ticket, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject machine = sksi_access_path.access_path_server(access_path);
        final SubLObject port = sksi_access_path.access_path_port(access_path);
        final SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
        SubLObject url = NIL;
        SubLObject query = NIL;
        SubLObject method = NIL;
        if (subprotocol.equal(sksi_sks_interaction.$sparql_oracle_joseki_subprotocol$.getGlobalValue())) {
            url = sksi_sks_interaction.$str156$_joseki_querymgt;
            query = cconcatenate(sksi_sks_interaction.$str157$abortqid_, format_nil_a_no_copy(sparql_ticket));
            method = $GET;
        } else
            if (subprotocol.equal(sksi_sks_interaction.$sparql_oracle_sesame_subprotocol$.getGlobalValue())) {
                url = sksi_sks_interaction.$str158$_openrdf_sesame_querymgt;
                query = cconcatenate(sksi_sks_interaction.$str157$abortqid_, format_nil_a_no_copy(sparql_ticket));
                method = $GET;
            } else {
                if (!subprotocol.equal(sksi_sks_interaction.$sparql_triclops_subprotocol$.getGlobalValue())) {
                    return NIL;
                }
                url = sksi_sks_interaction.$str159$_processes;
                query = cconcatenate(sksi_sks_interaction.$str160$ticket_, format_nil_a_no_copy(sparql_ticket));
                method = $POST;
            }

        final SubLObject keep_aliveP = NIL;
        final SubLObject wide_newlinesP = sksi_sks_interaction.$sksi_sparql_request_wide_newlines$.getDynamicValue(thread);
        final SubLObject open_connection_timeout = sksi_sks_interaction.$sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
        final SubLObject overall_timeout = TEN_INTEGER;
        thread.resetMultipleValues();
        final SubLObject tokens = xml_parsing_utilities.xml_tokenized_http_request(machine, url, query, method, port, keep_aliveP, wide_newlinesP, open_connection_timeout, overall_timeout, T, sksi_sks_interaction.$list145);
        final SubLObject errorP = thread.secondMultipleValue();
        final SubLObject error_message = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        return makeBoolean(NIL == errorP);
    }

    public static SubLObject test_sparql_abort_query(final SubLObject sparql_ticket, final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject machine = sksi_access_path.access_path_server(access_path);
        final SubLObject port = sksi_access_path.access_path_port(access_path);
        final SubLObject subprotocol = sksi_access_path.access_path_subprotocol(access_path);
        SubLObject url = NIL;
        SubLObject query = NIL;
        SubLObject method = NIL;
        if (subprotocol.equal(sksi_sks_interaction.$sparql_oracle_joseki_subprotocol$.getGlobalValue())) {
            url = sksi_sks_interaction.$str156$_joseki_querymgt;
            query = cconcatenate(sksi_sks_interaction.$str157$abortqid_, format_nil_a_no_copy(sparql_ticket));
            method = $GET;
        } else {
            if (!subprotocol.equal(sksi_sks_interaction.$sparql_triclops_subprotocol$.getGlobalValue())) {
                return NIL;
            }
            url = sksi_sks_interaction.$str159$_processes;
            query = cconcatenate(sksi_sks_interaction.$str160$ticket_, format_nil_a_no_copy(sparql_ticket));
            method = $POST;
        }
        final SubLObject keep_aliveP = NIL;
        final SubLObject wide_newlinesP = sksi_sks_interaction.$sksi_sparql_request_wide_newlines$.getDynamicValue(thread);
        final SubLObject overall_timeout = TEN_INTEGER;
        return test_http_request(machine, url, list(new SubLObject[]{ $QUERY, query, $METHOD, method, $PORT, port, sksi_sks_interaction.$kw164$KEEP_ALIVE_, keep_aliveP, sksi_sks_interaction.$kw165$WIDE_NEWLINES_, wide_newlinesP, $TIMEOUT, overall_timeout, $ACCEPT_TYPES, sksi_sks_interaction.$list145 }));
    }

    public static final SubLObject clear_cached_get_result_set_from_sparql_source_alt() {
        {
            SubLObject cs = $cached_get_result_set_from_sparql_source_caching_state$.getGlobalValue();
            if (NIL != cs) {
                memoization_state.caching_state_clear(cs);
            }
        }
        return NIL;
    }

    public static SubLObject clear_cached_get_result_set_from_sparql_source() {
        final SubLObject cs = sksi_sks_interaction.$cached_get_result_set_from_sparql_source_caching_state$.getGlobalValue();
        if (NIL != cs) {
            caching_state_clear(cs);
        }
        return NIL;
    }

    public static final SubLObject remove_cached_get_result_set_from_sparql_source(SubLObject sparql, SubLObject access_path) {
        return memoization_state.caching_state_remove_function_results_with_args($cached_get_result_set_from_sparql_source_caching_state$.getGlobalValue(), list(sparql, access_path), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject remove_cached_get_result_set_from_sparql_source(final SubLObject sparql, final SubLObject access_path, final SubLObject params, final SubLObject extract_rdf_literalsP) {
        return caching_state_remove_function_results_with_args(sksi_sks_interaction.$cached_get_result_set_from_sparql_source_caching_state$.getGlobalValue(), list(sparql, access_path, params, extract_rdf_literalsP), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject cached_get_result_set_from_sparql_source_internal(SubLObject sparql, SubLObject access_path) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_set_from_sparql_source_guts(sparql, access_path);
    }

    public static SubLObject cached_get_result_set_from_sparql_source_internal(final SubLObject sparql, final SubLObject access_path, final SubLObject params, final SubLObject extract_rdf_literalsP) {
        return sksi_sks_interaction.get_result_set_from_sparql_source_guts(sparql, access_path, params, extract_rdf_literalsP);
    }

    public static final SubLObject cached_get_result_set_from_sparql_source(SubLObject sparql, SubLObject access_path) {
        {
            SubLObject caching_state = $cached_get_result_set_from_sparql_source_caching_state$.getGlobalValue();
            if (NIL == caching_state) {
                caching_state = memoization_state.create_global_caching_state_for_name(CACHED_GET_RESULT_SET_FROM_SPARQL_SOURCE, $cached_get_result_set_from_sparql_source_caching_state$, $int$500, EQUAL, TWO_INTEGER, ZERO_INTEGER);
            }
            {
                SubLObject sxhash = memoization_state.sxhash_calc_2(sparql, access_path);
                SubLObject collisions = memoization_state.caching_state_lookup(caching_state, sxhash, UNPROVIDED);
                if (collisions != $kw40$_MEMOIZED_ITEM_NOT_FOUND_) {
                    {
                        SubLObject cdolist_list_var = collisions;
                        SubLObject collision = NIL;
                        for (collision = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , collision = cdolist_list_var.first()) {
                            {
                                SubLObject cached_args = collision.first();
                                SubLObject results2 = second(collision);
                                if (sparql.equal(cached_args.first())) {
                                    cached_args = cached_args.rest();
                                    if (((NIL != cached_args) && (NIL == cached_args.rest())) && access_path.equal(cached_args.first())) {
                                        return memoization_state.caching_results(results2);
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    SubLObject results = arg2(resetMultipleValues(), multiple_value_list(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.cached_get_result_set_from_sparql_source_internal(sparql, access_path)));
                    memoization_state.caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results, list(sparql, access_path));
                    return memoization_state.caching_results(results);
                }
            }
        }
    }

    public static SubLObject cached_get_result_set_from_sparql_source(final SubLObject sparql, final SubLObject access_path, final SubLObject params, final SubLObject extract_rdf_literalsP) {
        SubLObject caching_state = sksi_sks_interaction.$cached_get_result_set_from_sparql_source_caching_state$.getGlobalValue();
        if (NIL == caching_state) {
            caching_state = create_global_caching_state_for_name(sksi_sks_interaction.CACHED_GET_RESULT_SET_FROM_SPARQL_SOURCE, sksi_sks_interaction.$cached_get_result_set_from_sparql_source_caching_state$, sksi_sks_interaction.$int$500, EQUAL, FOUR_INTEGER, ZERO_INTEGER);
        }
        final SubLObject sxhash = sxhash_calc_4(sparql, access_path, params, extract_rdf_literalsP);
        final SubLObject collisions = caching_state_lookup(caching_state, sxhash, UNPROVIDED);
        if (!collisions.eql($memoized_item_not_found$.getGlobalValue())) {
            SubLObject cdolist_list_var = collisions;
            SubLObject collision = NIL;
            collision = cdolist_list_var.first();
            while (NIL != cdolist_list_var) {
                SubLObject cached_args = collision.first();
                final SubLObject results2 = second(collision);
                if (sparql.equal(cached_args.first())) {
                    cached_args = cached_args.rest();
                    if (access_path.equal(cached_args.first())) {
                        cached_args = cached_args.rest();
                        if (params.equal(cached_args.first())) {
                            cached_args = cached_args.rest();
                            if (((NIL != cached_args) && (NIL == cached_args.rest())) && extract_rdf_literalsP.equal(cached_args.first())) {
                                return caching_results(results2);
                            }
                        }
                    }
                }
                cdolist_list_var = cdolist_list_var.rest();
                collision = cdolist_list_var.first();
            } 
        }
        final SubLObject results3 = arg2(resetMultipleValues(), multiple_value_list(sksi_sks_interaction.cached_get_result_set_from_sparql_source_internal(sparql, access_path, params, extract_rdf_literalsP)));
        caching_state_enter_multi_key_n(caching_state, sxhash, collisions, results3, list(sparql, access_path, params, extract_rdf_literalsP));
        return caching_results(results3);
    }

    public static final SubLObject save_sparql_result_set_cache_alt(SubLObject filename) {
        return Errors.error($str_alt143$Needs_to_be_re_implemented_withou);
    }

    public static SubLObject save_sparql_result_set_cache(final SubLObject filename) {
        return Errors.error(sksi_sks_interaction.$str169$Needs_to_be_re_implemented_withou);
    }

    public static final SubLObject load_sparql_result_set_cache_alt(SubLObject filename) {
        return Errors.error($str_alt143$Needs_to_be_re_implemented_withou);
    }

    public static SubLObject load_sparql_result_set_cache(final SubLObject filename) {
        return Errors.error(sksi_sks_interaction.$str169$Needs_to_be_re_implemented_withou);
    }

    public static final SubLObject sparql_extract_variable_names_alt(SubLObject sparql) {
        {
            SubLObject select_start = search($$$SELECT, sparql, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
            if (NIL != select_start) {
                {
                    SubLObject where_start = search($$$WHERE, sparql, symbol_function(EQL), symbol_function(IDENTITY), ZERO_INTEGER, NIL, select_start, NIL);
                    if (NIL != where_start) {
                        {
                            SubLObject variables_string = string_utilities.reduce_whitespace(string_utilities.substring(sparql, add(select_start, SIX_INTEGER), where_start));
                            SubLObject variable_names = delete($str_alt145$, string_utilities.split_string(variables_string, $list_alt146), symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                            return variable_names;
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sparql_extract_variable_names(final SubLObject sparql) {
        final SubLObject select_start = search(sksi_sks_interaction.$$$SELECT, sparql, EQUALP, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
        if (NIL != select_start) {
            final SubLObject where_start = search(sksi_sks_interaction.$$$WHERE, sparql, symbol_function(EQUALP), symbol_function(IDENTITY), ZERO_INTEGER, NIL, select_start, NIL);
            if (NIL != where_start) {
                final SubLObject variables_string = reduce_whitespace(substring(sparql, add(select_start, SIX_INTEGER), where_start));
                final SubLObject possible_raw_variable_names = delete(sksi_sks_interaction.$str153$, split_string(variables_string, sksi_sks_interaction.$list171), symbol_function(EQUAL), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED);
                SubLObject variable_names = NIL;
                SubLObject cdolist_list_var = possible_raw_variable_names;
                SubLObject possible_var_name = NIL;
                possible_var_name = cdolist_list_var.first();
                while (NIL != cdolist_list_var) {
                    if (NIL != member(first_char(possible_var_name), sksi_sks_interaction.$list172, UNPROVIDED, UNPROVIDED)) {
                        variable_names = cons(strip_first(possible_var_name), variable_names);
                    }
                    cdolist_list_var = cdolist_list_var.rest();
                    possible_var_name = cdolist_list_var.first();
                } 
                return nreverse(variable_names);
            }
        }
        return NIL;
    }

    public static final SubLObject sparql_query_xml_tokens_alt(SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject machine = sksi_access_path.access_path_server(access_path);
                SubLObject port = sksi_access_path.access_path_port(access_path);
                SubLObject url = sksi_access_path.access_path_api_access_relative_path(access_path);
                SubLObject query = cconcatenate($str_alt147$query_, format_nil.format_nil_a_no_copy(web_utilities.html_url_encode(sparql, UNPROVIDED)));
                SubLObject method = $GET;
                SubLObject keep_aliveP = NIL;
                SubLObject wide_newlinesP = T;
                SubLObject open_connection_timeout = $sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
                SubLObject overall_timeout = $sksi_sparql_request_overall_timeout$.getDynamicValue(thread);
                if (NIL == machine) {
                    machine = $str_alt148$jonny2_cyc_com;
                }
                if (NIL == port) {
                    port = $int$8080;
                }
                if (NIL == url) {
                    url = $str_alt150$_SemanticDB_SPARQL_;
                }
                thread.resetMultipleValues();
                {
                    SubLObject tokens = web_utilities.xml_tokenized_http_request(machine, url, query, method, port, keep_aliveP, wide_newlinesP, open_connection_timeout, overall_timeout, T, $list_alt151);
                    SubLObject errorP = thread.secondMultipleValue();
                    SubLObject error_message = thread.thirdMultipleValue();
                    thread.resetMultipleValues();
                    if (NIL != errorP) {
                        sksi_query_utilities.sksi_suspend_cost_recording_for_current_iterator();
                        Errors.warn($str_alt152$sparql_query_xml_tokens_error___S, errorP, error_message);
                    }
                    return tokens;
                }
            }
        }
    }

    public static SubLObject sparql_query_xml_tokens(final SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = new_dictionary(UNPROVIDED, UNPROVIDED);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject machine = sksi_access_path.access_path_server(access_path);
        SubLObject port = sksi_access_path.access_path_port(access_path);
        SubLObject url = sksi_access_path.access_path_api_access_relative_path(access_path);
        final SubLObject query = cconcatenate(sksi_sks_interaction.$str173$query_, format_nil_a_no_copy(html_url_encode(sparql, UNPROVIDED)));
        final SubLObject method = $GET;
        final SubLObject keep_aliveP = NIL;
        final SubLObject wide_newlinesP = sksi_sks_interaction.$sksi_sparql_request_wide_newlines$.getDynamicValue(thread);
        final SubLObject open_connection_timeout = sksi_sks_interaction.$sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
        final SubLObject overall_timeout = sksi_sks_interaction.$sksi_sparql_request_overall_timeout$.getDynamicValue(thread);
        if (NIL == machine) {
            machine = sksi_sks_interaction.$str174$jonny2_cyc_com;
        }
        if (NIL == port) {
            port = sksi_sks_interaction.$int$8080;
        }
        if (NIL == url) {
            url = sksi_sks_interaction.$str176$_SemanticDB_SPARQL_;
        }
        thread.resetMultipleValues();
        final SubLObject tokens = xml_parsing_utilities.xml_tokenized_http_request(machine, url, query, method, port, keep_aliveP, wide_newlinesP, open_connection_timeout, overall_timeout, T, sksi_sks_interaction.$list177);
        final SubLObject errorP = thread.secondMultipleValue();
        final SubLObject error_message = thread.thirdMultipleValue();
        thread.resetMultipleValues();
        if (NIL != errorP) {
            sksi_suspend_cost_recording_for_current_iterator();
            Errors.warn(sksi_sks_interaction.$str178$sparql_query_xml_tokens_error___S, errorP, error_message);
        }
        return tokens;
    }

    public static final SubLObject open_sparql_query_result_stream_alt(SubLObject sparql, SubLObject sparql_ticket, SubLObject access_path) {
        if (sparql_ticket == UNPROVIDED) {
            sparql_ticket = NIL;
        }
        if (access_path == UNPROVIDED) {
            access_path = dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject machine = sksi_access_path.access_path_server(access_path);
                SubLObject port = sksi_access_path.access_path_port(access_path);
                SubLObject url = sksi_access_path.access_path_api_access_relative_path(access_path);
                SubLObject ticket_string = (NIL != sparql_ticket) ? ((SubLObject) (cconcatenate($str_alt153$_ticket_, format_nil.format_nil_a_no_copy(sparql_ticket)))) : $str_alt145$;
                SubLObject query = cconcatenate($str_alt147$query_, new SubLObject[]{ format_nil.format_nil_a_no_copy(web_utilities.html_url_encode(sparql, UNPROVIDED)), format_nil.format_nil_a_no_copy(ticket_string) });
                SubLObject method = $GET;
                SubLObject keep_aliveP = NIL;
                SubLObject wide_newlinesP = T;
                SubLObject open_connection_timeout = $sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
                if (NIL == machine) {
                    machine = $str_alt148$jonny2_cyc_com;
                }
                if (NIL == port) {
                    port = $int$8080;
                }
                if (NIL == url) {
                    url = $str_alt150$_SemanticDB_SPARQL_;
                }
                {
                    SubLObject stream = open_tcp_stream_with_timeout(machine, port, open_connection_timeout, $PRIVATE);
                    web_utilities.send_http_request(stream, list(new SubLObject[]{ $MACHINE, machine, $PORT, port, $METHOD, method, $URL, url, $QUERY, query, $kw159$KEEP_ALIVE_, keep_aliveP, $kw160$WIDE_NEWLINES_, wide_newlinesP, $ACCEPT_TYPES, $list_alt151 }));
                    return stream;
                }
            }
        }
    }

    public static SubLObject open_sparql_query_result_stream(final SubLObject sparql, SubLObject access_path, SubLObject params) {
        if (access_path == UNPROVIDED) {
            access_path = new_dictionary(UNPROVIDED, UNPROVIDED);
        }
        if (params == UNPROVIDED) {
            params = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject machine = sksi_access_path.access_path_server(access_path);
        SubLObject port = sksi_access_path.access_path_port(access_path);
        SubLObject url = sksi_access_path.access_path_api_access_relative_path(access_path);
        final SubLObject all_params = cons(bq_cons(sksi_sks_interaction.$$$query, sparql), params);
        final SubLObject query = html_assocation_list_to_url_parameters(all_params);
        final SubLObject method = $GET;
        final SubLObject keep_aliveP = NIL;
        final SubLObject wide_newlinesP = sksi_sks_interaction.$sksi_sparql_request_wide_newlines$.getDynamicValue(thread);
        final SubLObject open_connection_timeout = sksi_sks_interaction.$sksi_sparql_request_open_connection_timeout$.getDynamicValue(thread);
        if (NIL == machine) {
            machine = sksi_sks_interaction.$str174$jonny2_cyc_com;
        }
        if (NIL == port) {
            port = sksi_sks_interaction.$int$8080;
        }
        if (NIL == url) {
            url = sksi_sks_interaction.$str176$_SemanticDB_SPARQL_;
        }
        final SubLObject stream = subl_promotions.open_tcp_stream_with_timeout(machine, port, open_connection_timeout, $PRIVATE);
        send_http_request(stream, list(new SubLObject[]{ $MACHINE, machine, $PORT, port, $METHOD, method, $URL, url, $QUERY, query, sksi_sks_interaction.$kw164$KEEP_ALIVE_, keep_aliveP, sksi_sks_interaction.$kw165$WIDE_NEWLINES_, wide_newlinesP, $ACCEPT_TYPES, sksi_sks_interaction.$list177 }));
        return stream;
    }

    public static final SubLObject stream_wait_until_read_char_alt(SubLObject stream) {
        {
            SubLObject v_char = read_char(stream, NIL, NIL, UNPROVIDED);
            if (NIL != v_char) {
                unread_char(v_char, stream);
            }
        }
        return stream;
    }

    public static SubLObject stream_wait_until_read_char(final SubLObject stream) {
        final SubLObject v_char = read_char(stream, NIL, NIL, UNPROVIDED);
        if (NIL != v_char) {
            unread_char(v_char, stream);
        }
        return stream;
    }

    public static final SubLObject sparql_test_alt(SubLObject sparql, SubLObject stream, SubLObject count_onlyP) {
        if (stream == UNPROVIDED) {
            stream = StreamsLow.$standard_output$.getDynamicValue();
        }
        if (count_onlyP == UNPROVIDED) {
            count_onlyP = NIL;
        }
        {
            SubLObject access_path = (NIL != $access_path$.getGlobalValue()) ? ((SubLObject) ($access_path$.getGlobalValue())) : dictionary.new_dictionary(UNPROVIDED, UNPROVIDED);
            SubLObject tokens = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_query_xml_tokens(sparql, access_path);
            SubLObject count = ZERO_INTEGER;
            SubLObject cdolist_list_var = tokens;
            SubLObject token = NIL;
            for (token = cdolist_list_var.first(); NIL != cdolist_list_var; cdolist_list_var = cdolist_list_var.rest() , token = cdolist_list_var.first()) {
                count = add(count, ONE_INTEGER);
                if (NIL == count_onlyP) {
                    princ(token, stream);
                }
            }
            if (NIL == count_onlyP) {
                force_output(stream);
            }
            return count;
        }
    }

    public static SubLObject sparql_test(final SubLObject sparql, SubLObject stream, SubLObject count_onlyP) {
        if (stream == UNPROVIDED) {
            stream = StreamsLow.$standard_output$.getDynamicValue();
        }
        if (count_onlyP == UNPROVIDED) {
            count_onlyP = NIL;
        }
        final SubLObject access_path = (NIL != sksi_sks_interaction.$access_path$.getGlobalValue()) ? sksi_sks_interaction.$access_path$.getGlobalValue() : new_dictionary(UNPROVIDED, UNPROVIDED);
        final SubLObject tokens = sksi_sks_interaction.sparql_query_xml_tokens(sparql, access_path);
        SubLObject count = ZERO_INTEGER;
        SubLObject cdolist_list_var = tokens;
        SubLObject token = NIL;
        token = cdolist_list_var.first();
        while (NIL != cdolist_list_var) {
            count = add(count, ONE_INTEGER);
            if (NIL == count_onlyP) {
                princ(token, stream);
            }
            cdolist_list_var = cdolist_list_var.rest();
            token = cdolist_list_var.first();
        } 
        if (NIL == count_onlyP) {
            force_output(stream);
        }
        return count;
    }

    public static final SubLObject sparql_test_result_set_alt(SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = $access_path$.getGlobalValue();
        }
        if (NIL == access_path) {
            {
                SubLObject base = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_extract_rdf_base(sparql);
                SubLObject prefix_map = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_extract_rdf_prefix_map(sparql);
                access_path = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sparql_prefix_map_to_minimal_access_path(base, prefix_map);
            }
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_set_from_sparql_source(sparql, access_path);
    }

    public static SubLObject sparql_test_result_set(final SubLObject sparql, SubLObject access_path) {
        if (access_path == UNPROVIDED) {
            access_path = sksi_sks_interaction.$access_path$.getGlobalValue();
        }
        if (NIL == access_path) {
            final SubLObject base = sksi_sks_interaction.sparql_extract_rdf_base(sparql);
            final SubLObject prefix_map = sksi_sks_interaction.sparql_extract_rdf_prefix_map(sparql);
            access_path = sksi_sks_interaction.sparql_prefix_map_to_minimal_access_path(base, prefix_map);
        }
        return sksi_sks_interaction.get_result_set_from_sparql_source(sparql, access_path, UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject sparql_extract_booleanP(final SubLObject sparql) {
        return sublisp_boolean(search(sksi_sks_interaction.$str184$ASK__, sparql, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
    }

    public static final SubLObject sparql_extract_rdf_prefix_map_alt(SubLObject sparql) {
        return $list_alt163;
    }

    public static SubLObject sparql_extract_rdf_prefix_map(final SubLObject sparql) {
        return sksi_sks_interaction.$list185;
    }

    public static final SubLObject sparql_extract_rdf_base_alt(SubLObject sparql) {
        return $str_alt164$http___www_clevelandclinic_org_he;
    }

    public static SubLObject sparql_extract_rdf_base(final SubLObject sparql) {
        return sksi_sks_interaction.$str186$http___www_clevelandclinic_org_he;
    }

    public static final SubLObject sparql_prefix_map_to_minimal_access_path_alt(SubLObject base, SubLObject prefix_map) {
        {
            SubLObject access_path = dictionary.new_dictionary(symbol_function(EQ), ONE_INTEGER);
            dictionary.dictionary_enter(access_path, $$baseNamespaceForSKS, base);
            dictionary.dictionary_enter(access_path, $$nameSpacePrefixForSKS, prefix_map);
            return access_path;
        }
    }

    public static SubLObject sparql_prefix_map_to_minimal_access_path(final SubLObject base, final SubLObject prefix_map) {
        final SubLObject access_path = new_dictionary(symbol_function(EQ), ONE_INTEGER);
        dictionary_enter(access_path, sksi_sks_interaction.$$baseNamespaceForSKS, base);
        dictionary_enter(access_path, sksi_sks_interaction.$$nameSpacePrefixForSKS, prefix_map);
        return access_path;
    }

    public static final SubLObject sksi_sparql_xml_tokens_to_result_set_alt(SubLObject xml_tokens, SubLObject variable_names, SubLObject prefix_map) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.getDynamicValue(thread)) {
                variable_names = $list_alt167;
            }
            {
                SubLObject sparql_start = web_utilities.advance_xml_tokens_to(xml_tokens, $str_alt168$_sparql, UNPROVIDED);
                SubLObject results_tokens = web_utilities.xml_extract_token_sequence(sparql_start, $$$results);
                SubLObject result_set = sparql_utilities.sparql_extract_result_set(variable_names, results_tokens, prefix_map);
                if ((NIL != sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.getDynamicValue(thread)) && (NIL != result_set)) {
                    sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.setDynamicValue(result_set.first().first(), thread);
                    result_set = list(cyc_kernel.closed_query_success_token());
                }
                return result_set;
            }
        }
    }

    public static SubLObject sksi_sparql_xml_tokens_to_result_set(final SubLObject xml_tokens, SubLObject variable_names, final SubLObject prefix_map) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.getDynamicValue(thread)) {
            variable_names = sksi_sks_interaction.$list189;
        }
        final SubLObject sparql_start = xml_parsing_utilities.advance_xml_tokens_to(xml_tokens, sksi_sks_interaction.$str190$_sparql, UNPROVIDED);
        final SubLObject results_tokens = xml_parsing_utilities.xml_extract_token_sequence(sparql_start, sksi_sks_interaction.$$$results);
        SubLObject result_set = sparql_utilities.sparql_extract_result_set(variable_names, results_tokens, prefix_map);
        if ((NIL != sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.getDynamicValue(thread)) && (NIL != result_set)) {
            sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.setDynamicValue(result_set.first().first(), thread);
            result_set = list(closed_query_success_token());
        }
        return result_set;
    }

    public static final SubLObject test_sksi_ist_graph_alt(SubLObject xml_tokens, SubLObject variable_names, SubLObject prefix_map) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject result = NIL;
                {
                    SubLObject _prev_bind_0 = sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.currentBinding(thread);
                    SubLObject _prev_bind_1 = sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.currentBinding(thread);
                    try {
                        sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.bind(variables.get_variable(ZERO_INTEGER), thread);
                        sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.bind(NIL, thread);
                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_sparql_xml_tokens_to_result_set(xml_tokens, variable_names, prefix_map);
                        result = sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.getDynamicValue(thread);
                    } finally {
                        sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.rebind(_prev_bind_1, thread);
                        sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.rebind(_prev_bind_0, thread);
                    }
                }
                return result;
            }
        }
    }

    public static SubLObject test_sksi_ist_graph(final SubLObject xml_tokens, final SubLObject variable_names, final SubLObject prefix_map) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject result = NIL;
        final SubLObject _prev_bind_0 = sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.currentBinding(thread);
        final SubLObject _prev_bind_2 = sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.currentBinding(thread);
        try {
            sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.bind(get_variable(ZERO_INTEGER), thread);
            sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.bind(NIL, thread);
            sksi_sks_interaction.sksi_sparql_xml_tokens_to_result_set(xml_tokens, variable_names, prefix_map);
            result = sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.getDynamicValue(thread);
        } finally {
            sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_result$.rebind(_prev_bind_2, thread);
            sksi_csql_sparql_interpretation.$sksi_sparql_ist_graph_variable$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static final SubLObject sksi_unreachable_db_sks_error_alt(SubLObject access_path, SubLObject error) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_sks_error(access_path, sdbc.sdbc_error_message(error));
    }

    public static SubLObject sksi_unreachable_db_sks_error(final SubLObject access_path, final SubLObject error) {
        return sksi_sks_interaction.sksi_unreachable_sks_error(access_path, sdbc_error_message(error));
    }

    public static final SubLObject sksi_db_execution_error_alt(SubLObject access_path, SubLObject error, SubLObject sql) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_sks_execution_error(access_path, sdbc.sdbc_error_message(error), sql);
    }

    public static SubLObject sksi_db_execution_error(final SubLObject access_path, final SubLObject error, final SubLObject sql) {
        return sksi_sks_interaction.sksi_sks_execution_error(access_path, sdbc_error_message(error), sql, T);
    }

    public static final SubLObject sksi_unreachable_fht_sks_error_alt(SubLObject access_path, SubLObject error_message) {
        if (NIL == error_message) {
            error_message = $str_alt181$File_hash_table_does_not_exist_;
        }
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_unreachable_sks_error(access_path, error_message);
    }

    public static SubLObject sksi_unreachable_fht_sks_error(final SubLObject access_path, SubLObject error_message) {
        if (NIL == error_message) {
            error_message = sksi_sks_interaction.$str202$File_hash_table_does_not_exist_;
        }
        return sksi_sks_interaction.sksi_unreachable_sks_error(access_path, error_message);
    }

    public static final SubLObject sksi_create_fht_sks_error_alt(SubLObject access_path, SubLObject error_message) {
        {
            SubLObject sks = sksi_access_path.access_path_sks(access_path);
            format(T, $str_alt182$The_following_problem_was_encount, sks);
            {
                SubLObject cont_string = cconcatenate($str_alt183$Continue_without_creating_, new SubLObject[]{ format_nil.format_nil_a_no_copy(sks), $str_alt184$_ });
                return Errors.cerror(cont_string, error_message);
            }
        }
    }

    public static SubLObject sksi_create_fht_sks_error(final SubLObject access_path, final SubLObject error_message) {
        final SubLObject sks = sksi_access_path.access_path_sks(access_path);
        format(T, sksi_sks_interaction.$str203$The_following_problem_was_encount, sks);
        return Errors.warn(error_message);
    }

    public static final SubLObject all_structured_knowledge_sources_assumed_availableP_alt() {
        return eq($structured_knowledge_sources_assumed_available$.getGlobalValue(), $ALL);
    }

    public static SubLObject all_structured_knowledge_sources_assumed_availableP() {
        return eq(sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.getGlobalValue(), $ALL);
    }

    public static final SubLObject note_sks_available_alt(SubLObject sks) {
        if (NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.all_structured_knowledge_sources_assumed_availableP()) {
            {
                SubLObject item_var = sks;
                if (NIL == member(item_var, $structured_knowledge_sources_assumed_available$.getGlobalValue(), symbol_function(EQL), symbol_function(IDENTITY))) {
                    $structured_knowledge_sources_assumed_available$.setGlobalValue(cons(item_var, $structured_knowledge_sources_assumed_available$.getGlobalValue()));
                }
            }
        }
        return $structured_knowledge_sources_assumed_available$.getGlobalValue();
    }

    public static SubLObject note_sks_available(final SubLObject sks) {
        if ((NIL == sksi_sks_interaction.all_structured_knowledge_sources_assumed_availableP()) && (NIL == member(sks, sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.getGlobalValue(), symbol_function(EQL), symbol_function(IDENTITY)))) {
            sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.setGlobalValue(cons(sks, sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.getGlobalValue()));
        }
        return sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.getGlobalValue();
    }

    public static final SubLObject unnote_sks_available_alt(SubLObject sks) {
        if (NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.all_structured_knowledge_sources_assumed_availableP()) {
            $structured_knowledge_sources_assumed_available$.setGlobalValue(delete(sks, $structured_knowledge_sources_assumed_available$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        }
        return $structured_knowledge_sources_assumed_available$.getGlobalValue();
    }

    public static SubLObject unnote_sks_available(final SubLObject sks) {
        if (NIL == sksi_sks_interaction.all_structured_knowledge_sources_assumed_availableP()) {
            sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.setGlobalValue(delete(sks, sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.getGlobalValue(), UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED, UNPROVIDED));
        }
        return sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.getGlobalValue();
    }

    public static final SubLObject sks_assumed_availableP_alt(SubLObject sks) {
        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.all_structured_knowledge_sources_assumed_availableP()) {
            return T;
        }
        return memberP(sks, $structured_knowledge_sources_assumed_available$.getGlobalValue(), UNPROVIDED, UNPROVIDED);
    }

    public static SubLObject sks_assumed_availableP(final SubLObject sks) {
        if (NIL != sksi_sks_interaction.all_structured_knowledge_sources_assumed_availableP()) {
            return T;
        }
        return subl_promotions.memberP(sks, sksi_sks_interaction.$structured_knowledge_sources_assumed_available$.getGlobalValue(), UNPROVIDED, UNPROVIDED);
    }

    public static final SubLObject sksi_sks_error(SubLObject access_path, SubLObject error_message, SubLObject statement) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        {
            SubLObject sks = sksi_access_path.access_path_sks(access_path);
            if (NIL != sksi_debugging.sksi_ignore_sks_errorsP()) {
                return NIL;
            } else {
                if ((NIL != sksi_debugging.sksi_warn_on_sks_errorsP()) || (NIL != sksi_debugging.sksi_break_on_sks_errorsP())) {
                    if (statement.isString()) {
                        format(T, $str_alt185$__The_following_problem_was_encou, sks, statement);
                    } else {
                        format(T, $str_alt186$__The_following_problem_was_encou, sks);
                    }
                    if (NIL != sksi_debugging.sksi_warn_on_sks_errorsP()) {
                        return Errors.warn(error_message);
                    } else {
                        if (NIL != sksi_debugging.sksi_break_on_sks_errorsP()) {
                            {
                                SubLObject cont_string = cconcatenate($str_alt187$Continue_without_accessing_, new SubLObject[]{ format_nil.format_nil_a_no_copy(sks), $str_alt184$_ });
                                return Errors.cerror(cont_string, error_message);
                            }
                        }
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_sks_error(final SubLObject access_path, final SubLObject error_message, SubLObject statement, SubLObject error_type) {
        if (statement == UNPROVIDED) {
            statement = NIL;
        }
        if (error_type == UNPROVIDED) {
            error_type = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject sks = sksi_access_path.access_path_sks(access_path);
        if ((NIL != sksi_debugging.sksi_warn_on_sks_errorsP()) || (NIL != sksi_debugging.sksi_break_on_sks_errorsP())) {
            if (statement.isString()) {
                format(T, sksi_sks_interaction.$str204$__The_following_problem_was_encou, sks, statement);
            } else {
                format(T, sksi_sks_interaction.$str205$__The_following_problem_was_encou, sks);
            }
        }
        if (NIL != sksi_debugging.sksi_possibly_act_on_custom_sks_error_action(error_message, sks, error_type)) {
            return NIL;
        }
        if (NIL != sksi_debugging.sksi_ignore_sks_errorsP()) {
            return NIL;
        }
        if (NIL != sksi_debugging.sksi_warn_on_sks_errorsP()) {
            return Errors.warn(error_message);
        }
        if (NIL == sksi_debugging.sksi_break_on_sks_errorsP()) {
            return NIL;
        }
        if ((NIL != Errors.$continue_cerrorP$.getDynamicValue(thread)) || (NIL == Errors.$break_on_errorP$.getDynamicValue(thread))) {
            return Errors.warn(error_message);
        }
        final SubLObject cont_string = cconcatenate(sksi_sks_interaction.$$$Continue_without_accessing_, new SubLObject[]{ format_nil_a_no_copy(sks), sksi_sks_interaction.$str207$_ });
        return Errors.cerror(cont_string, error_message);
    }

    public static final SubLObject sksi_unreachable_sks_error_alt(SubLObject access_path, SubLObject error_message) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_sks_error(access_path, error_message, UNPROVIDED);
    }

    public static SubLObject sksi_unreachable_sks_error(final SubLObject access_path, final SubLObject error_message) {
        return sksi_sks_interaction.sksi_sks_error(access_path, error_message, NIL, $UNREACHABLE);
    }

    public static final SubLObject sksi_sks_execution_error(SubLObject access_path, SubLObject error_message, SubLObject statement) {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_sks_error(access_path, error_message, statement);
    }

    public static SubLObject sksi_sks_execution_error(final SubLObject access_path, final SubLObject error_message, final SubLObject statement, final SubLObject db_errorP) {
        return sksi_sks_interaction.sksi_sks_error(access_path, error_message, statement, NIL != db_errorP ? $DB_EXECUTION : $SKS_EXECUTION);
    }

    public static final SubLObject get_sql_connection_and_statement_alt(SubLObject access_path) {
        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_global_resourcingP()) {
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_sql_connection_and_statement_from_global_cache(access_path);
        } else {
            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_local_resourcingP()) {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_sql_connection_and_statement_from_local_cache(access_path);
            } else {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_open_sql_connection_and_statement(access_path);
            }
        }
    }

    public static SubLObject get_sql_connection_and_statement(final SubLObject access_path) {
        if (NIL != sksi_sks_interaction.within_sksi_global_resourcingP()) {
            return sksi_sks_interaction.get_sql_connection_and_statement_from_global_cache(access_path);
        }
        if (NIL != sksi_sks_interaction.within_sksi_local_resourcingP()) {
            return sksi_sks_interaction.get_sql_connection_and_statement_from_local_cache(access_path);
        }
        return sksi_sks_interaction.sksi_open_sql_connection_and_statement(access_path);
    }

    public static final SubLObject release_sql_connection_and_statement_alt(SubLObject connection, SubLObject statement) {
        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_global_resourcingP()) {
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.release_sql_connection_and_statement_to_global_cache(connection, statement);
        } else {
            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_local_resourcingP()) {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.release_sql_connection_and_statement_to_local_cache(connection, statement);
            } else {
                return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_close_sql_statement_and_connection(statement, connection);
            }
        }
    }

    public static SubLObject release_sql_connection_and_statement(final SubLObject connection, final SubLObject statement) {
        if (NIL != sksi_sks_interaction.within_sksi_global_resourcingP()) {
            return sksi_sks_interaction.release_sql_connection_and_statement_to_global_cache(connection, statement);
        }
        if (NIL != sksi_sks_interaction.within_sksi_local_resourcingP()) {
            return sksi_sks_interaction.release_sql_connection_and_statement_to_local_cache(connection, statement);
        }
        return sksi_sks_interaction.sksi_close_sql_statement_and_connection(statement, connection);
    }

    public static final SubLObject within_sksi_sql_connection_resourcingP_alt() {
        return makeBoolean((NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_global_resourcingP()) || (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_local_resourcingP()));
    }

    public static SubLObject within_sksi_sql_connection_resourcingP() {
        return makeBoolean((NIL != sksi_sks_interaction.within_sksi_global_resourcingP()) || (NIL != sksi_sks_interaction.within_sksi_local_resourcingP()));
    }

    /**
     * Whether we are within the context of SKSI SQL connection resourcing
     */
    @LispMethod(comment = "Whether we are within the context of SKSI SQL connection resourcing")
    public static final SubLObject within_sksi_local_resourcingP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return dictionary.dictionary_p(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread));
        }
    }

    /**
     * Whether we are within the context of SKSI SQL connection resourcing
     */
    @LispMethod(comment = "Whether we are within the context of SKSI SQL connection resourcing")
    public static SubLObject within_sksi_local_resourcingP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return dictionary_p(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread));
    }

    public static final SubLObject get_sql_connection_and_statement_from_local_cache_alt(SubLObject access_path) {
        {
            SubLObject connection = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_sql_connection_from_local_cache(access_path);
            SubLObject statement = (NIL != sdbc.sql_open_connection_p(connection)) ? ((SubLObject) (com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_sql_statement_from_local_cache(connection))) : NIL;
            return values(connection, statement);
        }
    }

    public static SubLObject get_sql_connection_and_statement_from_local_cache(final SubLObject access_path) {
        final SubLObject connection = sksi_sks_interaction.get_sql_connection_from_local_cache(access_path);
        final SubLObject statement = (NIL != sql_open_connection_p(connection)) ? sksi_sks_interaction.get_sql_statement_from_local_cache(connection) : NIL;
        return values(connection, statement);
    }

    public static final SubLObject get_sql_connection_from_local_cache_alt(SubLObject access_path) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject copy_access_path = copy_dictionary(access_path);
                dictionary.dictionary_remove(copy_access_path, $SKS);
                {
                    SubLObject connection = dictionary.dictionary_lookup_without_values(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread), copy_access_path, UNPROVIDED);
                    if (NIL == sdbc.sql_open_connection_p(connection)) {
                        if (NIL != sdbc.sql_connection_p(connection)) {
                            dictionary.dictionary_remove(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection);
                        }
                        connection = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_sql_source(copy_access_path, UNPROVIDED, UNPROVIDED);
                    }
                    dictionary.dictionary_enter(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread), copy_access_path, connection);
                    return connection;
                }
            }
        }
    }

    public static SubLObject get_sql_connection_from_local_cache(final SubLObject access_path) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject copy_access_path = dictionary_utilities.copy_dictionary(access_path);
        dictionary_remove(copy_access_path, $SKS);
        SubLObject connection = dictionary_lookup_without_values(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread), copy_access_path, UNPROVIDED);
        if (NIL == sql_open_connection_p(connection)) {
            if (NIL != sql_connection_p(connection)) {
                dictionary_remove(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection);
            }
            connection = sksi_sks_interaction.open_sql_source(copy_access_path, UNPROVIDED, UNPROVIDED);
        }
        dictionary_enter(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread), copy_access_path, connection);
        return connection;
    }

    public static final SubLObject get_sql_statement_from_local_cache_alt(SubLObject connection) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject statement_queue = dictionary.dictionary_lookup_without_values(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection, UNPROVIDED);
                SubLObject statement = NIL;
                if (NIL != queues.queue_p(statement_queue)) {
                    {
                        SubLObject lock = sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread);
                        SubLObject release = NIL;
                        try {
                            release = seize_lock(lock);
                            {
                                SubLObject q = statement_queue;
                                SubLObject done_var = makeBoolean((NIL != statement) || (NIL != queues.queue_empty_p(q)));
                                while (NIL == done_var) {
                                    {
                                        SubLObject candidate = queues.dequeue(q);
                                        if (NIL != sdbc.sql_open_statement_p(candidate)) {
                                            statement = candidate;
                                        }
                                        done_var = makeBoolean((NIL != statement) || (NIL != queues.queue_empty_p(q)));
                                    }
                                } 
                            }
                        } finally {
                            if (NIL != release) {
                                release_lock(lock);
                            }
                        }
                    }
                }
                if (NIL == sdbc.sql_open_statement_p(statement)) {
                    statement = sdbc.sqlc_create_statement(connection);
                }
                return statement;
            }
        }
    }

    public static SubLObject get_sql_statement_from_local_cache(final SubLObject connection) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject statement_queue = dictionary_lookup_without_values(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection, UNPROVIDED);
        SubLObject statement = NIL;
        if (NIL != queue_p(statement_queue)) {
            SubLObject release = NIL;
            try {
                release = seize_lock(sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread));
                for (SubLObject q = statement_queue, done_var = makeBoolean((NIL != statement) || (NIL != queue_empty_p(q))); NIL == done_var; done_var = makeBoolean((NIL != statement) || (NIL != queue_empty_p(q)))) {
                    final SubLObject candidate = dequeue(q);
                    if (NIL != sql_open_statement_p(candidate)) {
                        statement = candidate;
                    }
                }
            } finally {
                if (NIL != release) {
                    release_lock(sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread));
                }
            }
        }
        if (NIL == sql_open_statement_p(statement)) {
            statement = sqlc_create_statement(connection);
        }
        return statement;
    }

    public static final SubLObject release_sql_connection_and_statement_to_local_cache_alt(SubLObject connection, SubLObject statement) {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if ((NIL != sdbc.sql_open_connection_p(connection)) && (NIL != sdbc.sql_open_statement_p(statement))) {
                {
                    SubLObject lock = sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread);
                    SubLObject release = NIL;
                    try {
                        release = seize_lock(lock);
                        {
                            SubLObject statement_queue = dictionary.dictionary_lookup_without_values(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection, UNPROVIDED);
                            if (NIL == queues.queue_p(statement_queue)) {
                                statement_queue = queues.create_queue();
                                dictionary.dictionary_enter(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection, statement_queue);
                            }
                            if (!($sksi_sql_statement_pool_max_size$.getGlobalValue().isInteger() && queues.queue_size(statement_queue).numGE($sksi_sql_statement_pool_max_size$.getGlobalValue()))) {
                                queues.enqueue(statement, statement_queue);
                            }
                        }
                    } finally {
                        if (NIL != release) {
                            release_lock(lock);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject release_sql_connection_and_statement_to_local_cache(final SubLObject connection, final SubLObject statement) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if ((NIL != sql_open_connection_p(connection)) && (NIL != sql_open_statement_p(statement))) {
            SubLObject release = NIL;
            try {
                release = seize_lock(sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread));
                SubLObject statement_queue = dictionary_lookup_without_values(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection, UNPROVIDED);
                if (NIL == queue_p(statement_queue)) {
                    statement_queue = create_queue(UNPROVIDED);
                    dictionary_enter(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection, statement_queue);
                }
                if ((!sksi_sks_interaction.$sksi_sql_statement_pool_max_size$.getGlobalValue().isInteger()) || (!queue_size(statement_queue).numGE(sksi_sks_interaction.$sksi_sql_statement_pool_max_size$.getGlobalValue()))) {
                    enqueue(statement, statement_queue);
                }
            } finally {
                if (NIL != release) {
                    release_lock(sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread));
                }
            }
        }
        return NIL;
    }

    public static final SubLObject finalize_sql_connection_and_statement_cache_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_local_resourcingP()) {
                {
                    SubLObject lock = sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread);
                    SubLObject release = NIL;
                    try {
                        release = seize_lock(lock);
                        {
                            SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread)));
                            while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                                thread.resetMultipleValues();
                                {
                                    SubLObject connection = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                    SubLObject statement_queue = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    dictionary.dictionary_remove(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection);
                                    iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                }
                            } 
                            dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                        }
                    } finally {
                        if (NIL != release) {
                            release_lock(lock);
                        }
                    }
                }
                {
                    SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread)));
                    while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                        thread.resetMultipleValues();
                        {
                            SubLObject access_path = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                            SubLObject connection = thread.secondMultipleValue();
                            thread.resetMultipleValues();
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_close_sql_connection(connection);
                            dictionary.dictionary_remove(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread), access_path);
                            iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                        }
                    } 
                    dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                }
            }
            return T;
        }
    }

    public static SubLObject finalize_sql_connection_and_statement_cache() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_sks_interaction.within_sksi_local_resourcingP()) {
            SubLObject release = NIL;
            try {
                release = seize_lock(sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread));
                SubLObject iteration_state;
                for (iteration_state = do_dictionary_contents_state(dictionary_contents(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread))); NIL == do_dictionary_contents_doneP(iteration_state); iteration_state = do_dictionary_contents_next(iteration_state)) {
                    thread.resetMultipleValues();
                    final SubLObject connection = do_dictionary_contents_key_value(iteration_state);
                    final SubLObject statement_queue = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    dictionary_remove(sksi_macros.$sksi_sql_statement_cache$.getDynamicValue(thread), connection);
                }
                do_dictionary_contents_finalize(iteration_state);
            } finally {
                if (NIL != release) {
                    release_lock(sksi_macros.$sksi_sql_statement_pool_lock$.getDynamicValue(thread));
                }
            }
            SubLObject iteration_state2;
            for (iteration_state2 = do_dictionary_contents_state(dictionary_contents(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread))); NIL == do_dictionary_contents_doneP(iteration_state2); iteration_state2 = do_dictionary_contents_next(iteration_state2)) {
                thread.resetMultipleValues();
                final SubLObject access_path = do_dictionary_contents_key_value(iteration_state2);
                final SubLObject connection = thread.secondMultipleValue();
                thread.resetMultipleValues();
                sksi_sks_interaction.sksi_close_sql_connection(connection);
                dictionary_remove(sksi_macros.$sksi_sql_connection_cache$.getDynamicValue(thread), access_path);
            }
            do_dictionary_contents_finalize(iteration_state2);
        }
        return T;
    }

    public static final SubLObject within_sksi_global_resourcingP_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            return $sksi_global_resourcingP$.getDynamicValue(thread);
        }
    }

    public static SubLObject within_sksi_global_resourcingP() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return sksi_sks_interaction.$sksi_global_resourcingP$.getDynamicValue(thread);
    }

    public static final SubLObject sksi_global_resourcing_initializedP_alt() {
        return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_p($sksi_resourcing_cache$.getGlobalValue());
    }

    public static SubLObject sksi_global_resourcing_initializedP() {
        return sksi_sks_interaction.sksi_resourcing_cache_p(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
    }

    public static final SubLObject sksi_resourcing_cache_print_function_trampoline_alt(SubLObject v_object, SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static SubLObject sksi_resourcing_cache_print_function_trampoline(final SubLObject v_object, final SubLObject stream) {
        compatibility.default_struct_print_function(v_object, stream, ZERO_INTEGER);
        return NIL;
    }

    public static final SubLObject sksi_resourcing_cache_p_alt(SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.$sksi_resourcing_cache_native.class ? ((SubLObject) (T)) : NIL;
    }

    public static SubLObject sksi_resourcing_cache_p(final SubLObject v_object) {
        return v_object.getClass() == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.$sksi_resourcing_cache_native.class ? T : NIL;
    }

    public static final SubLObject sksi_resourcing_cache_connections_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.getField2();
    }

    public static SubLObject sksi_resourcing_cache_connections(final SubLObject v_object) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.getField2();
    }

    public static final SubLObject sksi_resourcing_cache_statements_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.getField3();
    }

    public static SubLObject sksi_resourcing_cache_statements(final SubLObject v_object) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.getField3();
    }

    public static final SubLObject sksi_resourcing_cache_used_statements_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.getField4();
    }

    public static SubLObject sksi_resourcing_cache_used_statements(final SubLObject v_object) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.getField4();
    }

    public static final SubLObject sksi_resourcing_cache_access_times_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.getField5();
    }

    public static SubLObject sksi_resourcing_cache_access_times(final SubLObject v_object) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.getField5();
    }

    public static final SubLObject sksi_resourcing_cache_lock_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.getField6();
    }

    public static SubLObject sksi_resourcing_cache_lock(final SubLObject v_object) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.getField6();
    }

    public static final SubLObject sksi_resourcing_cache_max_pool_size_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.getField7();
    }

    public static SubLObject sksi_resourcing_cache_max_pool_size(final SubLObject v_object) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.getField7();
    }

    public static final SubLObject sksi_resourcing_cache_max_idle_alt(SubLObject v_object) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.getField8();
    }

    public static SubLObject sksi_resourcing_cache_max_idle(final SubLObject v_object) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.getField8();
    }

    public static final SubLObject _csetf_sksi_resourcing_cache_connections_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.setField2(value);
    }

    public static SubLObject _csetf_sksi_resourcing_cache_connections(final SubLObject v_object, final SubLObject value) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.setField2(value);
    }

    public static final SubLObject _csetf_sksi_resourcing_cache_statements_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.setField3(value);
    }

    public static SubLObject _csetf_sksi_resourcing_cache_statements(final SubLObject v_object, final SubLObject value) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.setField3(value);
    }

    public static final SubLObject _csetf_sksi_resourcing_cache_used_statements_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.setField4(value);
    }

    public static SubLObject _csetf_sksi_resourcing_cache_used_statements(final SubLObject v_object, final SubLObject value) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.setField4(value);
    }

    public static final SubLObject _csetf_sksi_resourcing_cache_access_times_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.setField5(value);
    }

    public static SubLObject _csetf_sksi_resourcing_cache_access_times(final SubLObject v_object, final SubLObject value) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.setField5(value);
    }

    public static final SubLObject _csetf_sksi_resourcing_cache_lock_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.setField6(value);
    }

    public static SubLObject _csetf_sksi_resourcing_cache_lock(final SubLObject v_object, final SubLObject value) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.setField6(value);
    }

    public static final SubLObject _csetf_sksi_resourcing_cache_max_pool_size_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.setField7(value);
    }

    public static SubLObject _csetf_sksi_resourcing_cache_max_pool_size(final SubLObject v_object, final SubLObject value) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.setField7(value);
    }

    public static final SubLObject _csetf_sksi_resourcing_cache_max_idle_alt(SubLObject v_object, SubLObject value) {
        SubLTrampolineFile.checkType(v_object, SKSI_RESOURCING_CACHE_P);
        return v_object.setField8(value);
    }

    public static SubLObject _csetf_sksi_resourcing_cache_max_idle(final SubLObject v_object, final SubLObject value) {
        assert NIL != sksi_sks_interaction.sksi_resourcing_cache_p(v_object) : "! sksi_sks_interaction.sksi_resourcing_cache_p(v_object) " + "sksi_sks_interaction.sksi_resourcing_cache_p error :" + v_object;
        return v_object.setField8(value);
    }

    public static final SubLObject make_sksi_resourcing_cache_alt(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        {
            SubLObject v_new = new com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.$sksi_resourcing_cache_native();
            SubLObject next = NIL;
            for (next = arglist; NIL != next; next = cddr(next)) {
                {
                    SubLObject current_arg = next.first();
                    SubLObject current_value = cadr(next);
                    SubLObject pcase_var = current_arg;
                    if (pcase_var.eql($CONNECTIONS)) {
                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_connections(v_new, current_value);
                    } else {
                        if (pcase_var.eql($STATEMENTS)) {
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_statements(v_new, current_value);
                        } else {
                            if (pcase_var.eql($USED_STATEMENTS)) {
                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_used_statements(v_new, current_value);
                            } else {
                                if (pcase_var.eql($ACCESS_TIMES)) {
                                    com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_access_times(v_new, current_value);
                                } else {
                                    if (pcase_var.eql($LOCK)) {
                                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_lock(v_new, current_value);
                                    } else {
                                        if (pcase_var.eql($MAX_POOL_SIZE)) {
                                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_max_pool_size(v_new, current_value);
                                        } else {
                                            if (pcase_var.eql($MAX_IDLE)) {
                                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_max_idle(v_new, current_value);
                                            } else {
                                                Errors.error($str_alt220$Invalid_slot__S_for_construction_, current_arg);
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

    public static SubLObject make_sksi_resourcing_cache(SubLObject arglist) {
        if (arglist == UNPROVIDED) {
            arglist = NIL;
        }
        final SubLObject v_new = new com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.$sksi_resourcing_cache_native();
        SubLObject next;
        SubLObject current_arg;
        SubLObject current_value;
        SubLObject pcase_var;
        for (next = NIL, next = arglist; NIL != next; next = cddr(next)) {
            current_arg = next.first();
            current_value = cadr(next);
            pcase_var = current_arg;
            if (pcase_var.eql($CONNECTIONS)) {
                sksi_sks_interaction._csetf_sksi_resourcing_cache_connections(v_new, current_value);
            } else
                if (pcase_var.eql($STATEMENTS)) {
                    sksi_sks_interaction._csetf_sksi_resourcing_cache_statements(v_new, current_value);
                } else
                    if (pcase_var.eql($USED_STATEMENTS)) {
                        sksi_sks_interaction._csetf_sksi_resourcing_cache_used_statements(v_new, current_value);
                    } else
                        if (pcase_var.eql($ACCESS_TIMES)) {
                            sksi_sks_interaction._csetf_sksi_resourcing_cache_access_times(v_new, current_value);
                        } else
                            if (pcase_var.eql($LOCK)) {
                                sksi_sks_interaction._csetf_sksi_resourcing_cache_lock(v_new, current_value);
                            } else
                                if (pcase_var.eql(sksi_sks_interaction.$MAX_POOL_SIZE)) {
                                    sksi_sks_interaction._csetf_sksi_resourcing_cache_max_pool_size(v_new, current_value);
                                } else
                                    if (pcase_var.eql($MAX_IDLE)) {
                                        sksi_sks_interaction._csetf_sksi_resourcing_cache_max_idle(v_new, current_value);
                                    } else {
                                        Errors.error(sksi_sks_interaction.$str244$Invalid_slot__S_for_construction_, current_arg);
                                    }






        }
        return v_new;
    }

    public static SubLObject visit_defstruct_sksi_resourcing_cache(final SubLObject obj, final SubLObject visitor_fn) {
        funcall(visitor_fn, obj, $BEGIN, sksi_sks_interaction.MAKE_SKSI_RESOURCING_CACHE, SEVEN_INTEGER);
        funcall(visitor_fn, obj, $SLOT, $CONNECTIONS, sksi_sks_interaction.sksi_resourcing_cache_connections(obj));
        funcall(visitor_fn, obj, $SLOT, $STATEMENTS, sksi_sks_interaction.sksi_resourcing_cache_statements(obj));
        funcall(visitor_fn, obj, $SLOT, $USED_STATEMENTS, sksi_sks_interaction.sksi_resourcing_cache_used_statements(obj));
        funcall(visitor_fn, obj, $SLOT, $ACCESS_TIMES, sksi_sks_interaction.sksi_resourcing_cache_access_times(obj));
        funcall(visitor_fn, obj, $SLOT, $LOCK, sksi_sks_interaction.sksi_resourcing_cache_lock(obj));
        funcall(visitor_fn, obj, $SLOT, sksi_sks_interaction.$MAX_POOL_SIZE, sksi_sks_interaction.sksi_resourcing_cache_max_pool_size(obj));
        funcall(visitor_fn, obj, $SLOT, $MAX_IDLE, sksi_sks_interaction.sksi_resourcing_cache_max_idle(obj));
        funcall(visitor_fn, obj, $END, sksi_sks_interaction.MAKE_SKSI_RESOURCING_CACHE, SEVEN_INTEGER);
        return obj;
    }

    public static SubLObject visit_defstruct_object_sksi_resourcing_cache_method(final SubLObject obj, final SubLObject visitor_fn) {
        return sksi_sks_interaction.visit_defstruct_sksi_resourcing_cache(obj, visitor_fn);
    }

    public static final SubLObject new_sksi_resourcing_cache_alt(SubLObject max_pool_size, SubLObject max_idle) {
        if (max_pool_size == UNPROVIDED) {
            max_pool_size = NIL;
        }
        if (max_idle == UNPROVIDED) {
            max_idle = NIL;
        }
        {
            SubLObject v_cache = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.make_sksi_resourcing_cache(UNPROVIDED);
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_connections(v_cache, dictionary.new_dictionary(symbol_function(EQUALP), UNPROVIDED));
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_statements(v_cache, dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_used_statements(v_cache, dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_access_times(v_cache, dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_lock(v_cache, make_lock(Strings.string(gensym($str_alt221$SKSI_SQL_resourcing_cache_))));
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_max_pool_size(v_cache, max_pool_size);
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_max_idle(v_cache, max_idle);
            return v_cache;
        }
    }

    public static SubLObject new_sksi_resourcing_cache(SubLObject max_pool_size, SubLObject max_idle) {
        if (max_pool_size == UNPROVIDED) {
            max_pool_size = NIL;
        }
        if (max_idle == UNPROVIDED) {
            max_idle = NIL;
        }
        final SubLObject v_cache = sksi_sks_interaction.make_sksi_resourcing_cache(UNPROVIDED);
        sksi_sks_interaction._csetf_sksi_resourcing_cache_connections(v_cache, new_dictionary(symbol_function(EQUALP), UNPROVIDED));
        sksi_sks_interaction._csetf_sksi_resourcing_cache_statements(v_cache, new_dictionary(symbol_function(EQ), UNPROVIDED));
        sksi_sks_interaction._csetf_sksi_resourcing_cache_used_statements(v_cache, new_dictionary(symbol_function(EQ), UNPROVIDED));
        sksi_sks_interaction._csetf_sksi_resourcing_cache_access_times(v_cache, new_dictionary(symbol_function(EQ), UNPROVIDED));
        sksi_sks_interaction._csetf_sksi_resourcing_cache_lock(v_cache, make_lock(Strings.string(gensym(sksi_sks_interaction.$$$SKSI_SQL_resourcing_cache_))));
        sksi_sks_interaction._csetf_sksi_resourcing_cache_max_pool_size(v_cache, max_pool_size);
        sksi_sks_interaction._csetf_sksi_resourcing_cache_max_idle(v_cache, max_idle);
        return v_cache;
    }

    public static final SubLObject with_sksi_global_resourcing_lock_held_alt(SubLObject macroform, SubLObject environment) {
        {
            SubLObject datum = macroform.rest();
            SubLObject current = datum;
            SubLObject v_cache = NIL;
            destructuring_bind_must_consp(current, datum, $list_alt222);
            v_cache = current.first();
            current = current.rest();
            {
                SubLObject body = current;
                return listS(WITH_LOCK_HELD, list(list(SKSI_RESOURCING_CACHE_LOCK, v_cache)), append(body, NIL));
            }
        }
    }

    public static SubLObject with_sksi_global_resourcing_lock_held(final SubLObject macroform, final SubLObject environment) {
        SubLObject current;
        final SubLObject datum = current = macroform.rest();
        SubLObject v_cache = NIL;
        destructuring_bind_must_consp(current, datum, sksi_sks_interaction.$list251);
        v_cache = current.first();
        final SubLObject body;
        current = body = current.rest();
        return listS(WITH_LOCK_HELD, list(list(sksi_sks_interaction.SKSI_RESOURCING_CACHE_LOCK, v_cache)), append(body, NIL));
    }

    public static final SubLObject sksi_add_connection_to_cache_alt(SubLObject v_cache, SubLObject access_path, SubLObject connection) {
        {
            SubLObject key = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_generate_cache_key_from_access_path(access_path);
            SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                dictionary.dictionary_enter(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_connections(v_cache), key, connection);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_cache;
    }

    public static SubLObject sksi_add_connection_to_cache(final SubLObject v_cache, final SubLObject access_path, final SubLObject connection) {
        final SubLObject key = sksi_sks_interaction.sksi_generate_cache_key_from_access_path(access_path);
        final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            dictionary_enter(sksi_sks_interaction.sksi_resourcing_cache_connections(v_cache), key, connection);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_cache;
    }

    public static final SubLObject sksi_add_statement_to_cache_alt(SubLObject v_cache, SubLObject connection, SubLObject statement) {
        {
            SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                {
                    SubLObject statement_cache = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_statements(v_cache);
                    SubLObject statement_queue = dictionary.dictionary_lookup_without_values(statement_cache, connection, UNPROVIDED);
                    if (NIL == statement_queue) {
                        statement_queue = queues.create_queue();
                        dictionary.dictionary_enter(statement_cache, connection, statement_queue);
                    }
                    if (!(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_max_pool_size(v_cache).isInteger() && queues.queue_size(statement_queue).numGE(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_max_pool_size(v_cache)))) {
                        queues.enqueue(statement, statement_queue);
                    }
                    {
                        SubLObject used_statements_set = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, UNPROVIDED);
                        if (NIL != set.set_p(used_statements_set)) {
                            set.set_remove(statement, used_statements_set);
                        }
                    }
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_cache;
    }

    public static SubLObject sksi_add_statement_to_cache(final SubLObject v_cache, final SubLObject connection, final SubLObject statement) {
        final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            final SubLObject statement_cache = sksi_sks_interaction.sksi_resourcing_cache_statements(v_cache);
            SubLObject statement_queue = dictionary_lookup_without_values(statement_cache, connection, UNPROVIDED);
            if (NIL == statement_queue) {
                statement_queue = create_queue(UNPROVIDED);
                dictionary_enter(statement_cache, connection, statement_queue);
            }
            if ((!sksi_sks_interaction.sksi_resourcing_cache_max_pool_size(v_cache).isInteger()) || (!queue_size(statement_queue).numGE(sksi_sks_interaction.sksi_resourcing_cache_max_pool_size(v_cache)))) {
                enqueue(statement, statement_queue);
            }
            final SubLObject used_statements_set = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, UNPROVIDED);
            if (NIL != set_p(used_statements_set)) {
                set_remove(statement, used_statements_set);
            }
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_cache;
    }

    public static final SubLObject sksi_note_access_time_in_cache_alt(SubLObject v_cache, SubLObject connection) {
        {
            SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                dictionary.dictionary_enter(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_access_times(v_cache), connection, get_universal_time());
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_cache;
    }

    public static SubLObject sksi_note_access_time_in_cache(final SubLObject v_cache, final SubLObject connection) {
        final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            dictionary_enter(sksi_sks_interaction.sksi_resourcing_cache_access_times(v_cache), connection, get_universal_time());
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_cache;
    }

    public static final SubLObject sksi_retrieve_connection_from_cache_alt(SubLObject v_cache, SubLObject access_path) {
        {
            SubLObject key = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_generate_cache_key_from_access_path(access_path);
            SubLObject connection = NIL;
            SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                connection = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_connections(v_cache), key, UNPROVIDED);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
            return connection;
        }
    }

    public static SubLObject sksi_retrieve_connection_from_cache(final SubLObject v_cache, final SubLObject access_path) {
        final SubLObject key = sksi_sks_interaction.sksi_generate_cache_key_from_access_path(access_path);
        SubLObject connection = NIL;
        final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            connection = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_connections(v_cache), key, UNPROVIDED);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return connection;
    }

    public static final SubLObject sksi_retrieve_statement_from_cache_alt(SubLObject v_cache, SubLObject connection) {
        {
            SubLObject statement = NIL;
            SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                {
                    SubLObject statement_queue = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_statements(v_cache), connection, UNPROVIDED);
                    if (NIL != queues.queue_p(statement_queue)) {
                        {
                            SubLObject q = statement_queue;
                            SubLObject done_var = makeBoolean((NIL != statement) || (NIL != queues.queue_empty_p(q)));
                            while (NIL == done_var) {
                                {
                                    SubLObject candidate = queues.dequeue(q);
                                    if (NIL != sdbc.sql_open_statement_p(candidate)) {
                                        statement = candidate;
                                        {
                                            SubLObject used_statements_set = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, UNPROVIDED);
                                            if (NIL == set.set_p(used_statements_set)) {
                                                used_statements_set = set.new_set(symbol_function(EQ), UNPROVIDED);
                                                dictionary.dictionary_enter(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, used_statements_set);
                                            }
                                            set.set_add(statement, used_statements_set);
                                        }
                                    }
                                    done_var = makeBoolean((NIL != statement) || (NIL != queues.queue_empty_p(q)));
                                }
                            } 
                        }
                    }
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
            return statement;
        }
    }

    public static SubLObject sksi_retrieve_statement_from_cache(final SubLObject v_cache, final SubLObject connection) {
        SubLObject statement = NIL;
        final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            final SubLObject statement_queue = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_statements(v_cache), connection, UNPROVIDED);
            if (NIL != queue_p(statement_queue)) {
                for (SubLObject q = statement_queue, done_var = makeBoolean((NIL != statement) || (NIL != queue_empty_p(q))); NIL == done_var; done_var = makeBoolean((NIL != statement) || (NIL != queue_empty_p(q)))) {
                    final SubLObject candidate = dequeue(q);
                    if (NIL != sql_open_statement_p(candidate)) {
                        statement = candidate;
                        SubLObject used_statements_set = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, UNPROVIDED);
                        if (NIL == set_p(used_statements_set)) {
                            used_statements_set = new_set(symbol_function(EQ), UNPROVIDED);
                            dictionary_enter(sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, used_statements_set);
                        }
                        set_add(statement, used_statements_set);
                    }
                }
            }
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return statement;
    }

    public static final SubLObject sksi_add_statement_to_used_statements_set_alt(SubLObject v_cache, SubLObject connection, SubLObject statement) {
        {
            SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                {
                    SubLObject used_statements_set = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, UNPROVIDED);
                    if (NIL == set.set_p(used_statements_set)) {
                        used_statements_set = set.new_set(symbol_function(EQ), UNPROVIDED);
                        dictionary.dictionary_enter(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, used_statements_set);
                    }
                    set.set_add(statement, used_statements_set);
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return v_cache;
    }

    public static SubLObject sksi_add_statement_to_used_statements_set(final SubLObject v_cache, final SubLObject connection, final SubLObject statement) {
        final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            SubLObject used_statements_set = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, UNPROVIDED);
            if (NIL == set_p(used_statements_set)) {
                used_statements_set = new_set(symbol_function(EQ), UNPROVIDED);
                dictionary_enter(sksi_sks_interaction.sksi_resourcing_cache_used_statements(v_cache), connection, used_statements_set);
            }
            set_add(statement, used_statements_set);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return v_cache;
    }

    public static final SubLObject sksi_last_cache_access_time_alt(SubLObject v_cache, SubLObject connection) {
        {
            SubLObject time = NIL;
            SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                time = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_access_times(v_cache), connection, UNPROVIDED);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
            return time;
        }
    }

    public static SubLObject sksi_last_cache_access_time(final SubLObject v_cache, final SubLObject connection) {
        SubLObject time = NIL;
        final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(v_cache);
        SubLObject release = NIL;
        try {
            release = seize_lock(lock);
            time = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_access_times(v_cache), connection, UNPROVIDED);
        } finally {
            if (NIL != release) {
                release_lock(lock);
            }
        }
        return time;
    }

    public static final SubLObject sksi_generate_cache_key_from_access_path_alt(SubLObject access_path) {
        {
            SubLObject new_access_path = copy_dictionary(access_path);
            dictionary.dictionary_remove(new_access_path, $SKS);
            return new_access_path;
        }
    }

    public static SubLObject sksi_generate_cache_key_from_access_path(final SubLObject access_path) {
        final SubLObject new_access_path = dictionary_utilities.copy_dictionary(access_path);
        dictionary_remove(new_access_path, $SKS);
        return new_access_path;
    }

    public static final SubLObject get_sql_connection_and_statement_from_global_cache_alt(SubLObject access_path) {
        {
            SubLObject connection = NIL;
            SubLObject statement = NIL;
            if (NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_initialize_global_resourcing(UNPROVIDED, UNPROVIDED);
            }
            connection = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_retrieve_connection_from_cache($sksi_resourcing_cache$.getGlobalValue(), access_path);
            if (NIL == sdbc.sql_open_connection_p(connection)) {
                connection = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.open_sql_source(access_path, UNPROVIDED, UNPROVIDED);
                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_add_connection_to_cache($sksi_resourcing_cache$.getGlobalValue(), access_path, connection);
            }
            if (NIL != sdbc.sql_open_connection_p(connection)) {
                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_note_access_time_in_cache($sksi_resourcing_cache$.getGlobalValue(), connection);
                statement = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_retrieve_statement_from_cache($sksi_resourcing_cache$.getGlobalValue(), connection);
                if (NIL == sdbc.sql_open_statement_p(statement)) {
                    statement = sdbc.sqlc_create_statement(connection);
                    com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_add_statement_to_used_statements_set($sksi_resourcing_cache$.getGlobalValue(), connection, statement);
                }
            }
            return values(connection, statement);
        }
    }

    public static SubLObject get_sql_connection_and_statement_from_global_cache(final SubLObject access_path) {
        SubLObject release = NIL;
        try {
            release = seize_lock(sksi_sks_interaction.$sksi_connection_global_cache_lock$.getGlobalValue());
            SubLObject connection = NIL;
            SubLObject statement = NIL;
            if (NIL == sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
                sksi_sks_interaction.sksi_initialize_global_resourcing(UNPROVIDED, UNPROVIDED);
            }
            connection = sksi_sks_interaction.sksi_retrieve_connection_from_cache(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), access_path);
            if (NIL == sql_open_connection_p(connection)) {
                connection = sksi_sks_interaction.open_sql_source(access_path, UNPROVIDED, UNPROVIDED);
                sksi_sks_interaction.sksi_add_connection_to_cache(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), access_path, connection);
            }
            if (NIL != sql_open_connection_p(connection)) {
                sksi_sks_interaction.sksi_note_access_time_in_cache(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), connection);
                statement = sksi_sks_interaction.sksi_retrieve_statement_from_cache(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), connection);
                if (NIL == sql_open_statement_p(statement)) {
                    statement = sqlc_create_statement(connection);
                    sksi_sks_interaction.sksi_add_statement_to_used_statements_set(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), connection, statement);
                }
            }
            return values(connection, statement);
        } finally {
            if (NIL != release) {
                release_lock(sksi_sks_interaction.$sksi_connection_global_cache_lock$.getGlobalValue());
            }
        }
    }

    public static final SubLObject release_sql_connection_and_statement_to_global_cache_alt(SubLObject connection, SubLObject statement) {
        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_note_access_time_in_cache($sksi_resourcing_cache$.getGlobalValue(), connection);
        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_add_statement_to_cache($sksi_resourcing_cache$.getGlobalValue(), connection, statement);
        return NIL;
    }

    public static SubLObject release_sql_connection_and_statement_to_global_cache(final SubLObject connection, final SubLObject statement) {
        SubLObject release = NIL;
        try {
            release = seize_lock(sksi_sks_interaction.$sksi_connection_global_cache_lock$.getGlobalValue());
            sksi_sks_interaction.sksi_note_access_time_in_cache(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), connection);
            sksi_sks_interaction.sksi_add_statement_to_cache(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), connection, statement);
            return NIL;
        } finally {
            if (NIL != release) {
                release_lock(sksi_sks_interaction.$sksi_connection_global_cache_lock$.getGlobalValue());
            }
        }
    }

    public static final SubLObject sksi_resourced_connection_has_active_statementsP_alt(SubLObject connection) {
        {
            SubLObject activeP = NIL;
            if ((NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_global_resourcingP()) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_global_resourcing_initializedP())) {
                {
                    SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock($sksi_resourcing_cache$.getGlobalValue());
                    SubLObject release = NIL;
                    try {
                        release = seize_lock(lock);
                        {
                            SubLObject used_statements_set = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements($sksi_resourcing_cache$.getGlobalValue()), connection, UNPROVIDED);
                            activeP = set.non_empty_set_p(used_statements_set);
                        }
                    } finally {
                        if (NIL != release) {
                            release_lock(lock);
                        }
                    }
                }
            }
            return activeP;
        }
    }

    public static SubLObject sksi_resourced_connection_has_active_statementsP(final SubLObject connection) {
        SubLObject activeP = NIL;
        if ((NIL != sksi_sks_interaction.within_sksi_global_resourcingP()) && (NIL != sksi_sks_interaction.sksi_global_resourcing_initializedP())) {
            final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                final SubLObject used_statements_set = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_used_statements(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()), connection, UNPROVIDED);
                activeP = non_empty_set_p(used_statements_set);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return activeP;
    }

    public static final SubLObject sksi_resourced_connection_idle_too_longP_alt(SubLObject connection) {
        {
            SubLObject too_idleP = NIL;
            if ((NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_global_resourcingP()) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_global_resourcing_initializedP())) {
                {
                    SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock($sksi_resourcing_cache$.getGlobalValue());
                    SubLObject release = NIL;
                    try {
                        release = seize_lock(lock);
                        {
                            SubLObject last_access_time = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_last_cache_access_time($sksi_resourcing_cache$.getGlobalValue(), connection);
                            SubLObject max_idle = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_max_idle($sksi_resourcing_cache$.getGlobalValue());
                            if (NIL != numeric_date_utilities.universal_time_p(last_access_time)) {
                                too_idleP = numG(elapsed_universal_time(last_access_time, UNPROVIDED), max_idle);
                            }
                        }
                    } finally {
                        if (NIL != release) {
                            release_lock(lock);
                        }
                    }
                }
            }
            return too_idleP;
        }
    }

    public static SubLObject sksi_resourced_connection_idle_too_longP(final SubLObject connection) {
        SubLObject too_idleP = NIL;
        if ((NIL != sksi_sks_interaction.within_sksi_global_resourcingP()) && (NIL != sksi_sks_interaction.sksi_global_resourcing_initializedP())) {
            final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                final SubLObject last_access_time = sksi_sks_interaction.sksi_last_cache_access_time(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), connection);
                final SubLObject max_idle = sksi_sks_interaction.sksi_resourcing_cache_max_idle(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
                if (NIL != universal_time_p(last_access_time)) {
                    too_idleP = numG(subl_promotions.elapsed_universal_time(last_access_time, UNPROVIDED), max_idle);
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return too_idleP;
    }

    public static final SubLObject sksi_initialize_global_resourcing_alt(SubLObject max_pool_size, SubLObject max_idle) {
        if (max_pool_size == UNPROVIDED) {
            max_pool_size = $sksi_sql_statement_pool_max_size$.getGlobalValue();
        }
        if (max_idle == UNPROVIDED) {
            max_idle = $sksi_default_connection_max_idle_seconds$.getGlobalValue();
        }
        $sksi_resourcing_cache$.setGlobalValue(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.new_sksi_resourcing_cache(max_pool_size, max_idle));
        $sksi_global_resourcingP$.setDynamicValue(T);
        return NIL;
    }

    public static SubLObject sksi_initialize_global_resourcing(SubLObject max_pool_size, SubLObject max_idle) {
        if (max_pool_size == UNPROVIDED) {
            max_pool_size = sksi_sks_interaction.$sksi_sql_statement_pool_max_size$.getGlobalValue();
        }
        if (max_idle == UNPROVIDED) {
            max_idle = sksi_sks_interaction.$sksi_default_connection_max_idle_seconds$.getGlobalValue();
        }
        sksi_sks_interaction.$sksi_resourcing_cache$.setGlobalValue(sksi_sks_interaction.new_sksi_resourcing_cache(max_pool_size, max_idle));
        sksi_sks_interaction.$sksi_global_resourcingP$.setDynamicValue(T);
        return NIL;
    }

    public static final SubLObject sksi_finalize_global_resourcing_alt() {
        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_close_resourced_connections();
            {
                SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock($sksi_resourcing_cache$.getGlobalValue());
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    $sksi_resourcing_cache$.setGlobalValue(NIL);
                    $sksi_global_resourcingP$.setDynamicValue(NIL);
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_finalize_global_resourcing() {
        if (NIL != sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
            sksi_sks_interaction.sksi_close_resourced_connections();
            final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                sksi_sks_interaction.$sksi_resourcing_cache$.setGlobalValue(NIL);
                sksi_sks_interaction.$sksi_global_resourcingP$.setDynamicValue(NIL);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return NIL;
    }

    public static final SubLObject sksi_close_resourced_connections_alt() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
                {
                    SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock($sksi_resourcing_cache$.getGlobalValue());
                    SubLObject release = NIL;
                    try {
                        release = seize_lock(lock);
                        {
                            SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_connections($sksi_resourcing_cache$.getGlobalValue())));
                            while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                                thread.resetMultipleValues();
                                {
                                    SubLObject key = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                                    SubLObject connection = thread.secondMultipleValue();
                                    thread.resetMultipleValues();
                                    {
                                        SubLObject ignore_errors_tag = NIL;
                                        try {
                                            {
                                                SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                                                try {
                                                    Errors.$error_handler$.bind(symbol_function(IGNORE_ERRORS_HANDLER), thread);
                                                    try {
                                                        com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_close_sql_connection(connection);
                                                    } catch (Throwable catch_var) {
                                                        Errors.handleThrowable(catch_var, NIL);
                                                    }
                                                } finally {
                                                    Errors.$error_handler$.rebind(_prev_bind_0, thread);
                                                }
                                            }
                                        } catch (Throwable ccatch_env_var) {
                                            ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, $IGNORE_ERRORS_TARGET);
                                        }
                                    }
                                    iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                                }
                            } 
                            dictionary_contents.do_dictionary_contents_finalize(iteration_state);
                        }
                        dictionary.clear_dictionary(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_connections($sksi_resourcing_cache$.getGlobalValue()));
                        dictionary.clear_dictionary(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_statements($sksi_resourcing_cache$.getGlobalValue()));
                        dictionary.clear_dictionary(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements($sksi_resourcing_cache$.getGlobalValue()));
                    } finally {
                        if (NIL != release) {
                            release_lock(lock);
                        }
                    }
                }
            }
            return NIL;
        }
    }

    public static SubLObject sksi_close_resourced_connections() {
        final SubLThread thread = SubLProcess.currentSubLThread();
        if (NIL != sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
            final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                SubLObject iteration_state;
                for (iteration_state = do_dictionary_contents_state(dictionary_contents(sksi_sks_interaction.sksi_resourcing_cache_connections(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()))); NIL == do_dictionary_contents_doneP(iteration_state); iteration_state = do_dictionary_contents_next(iteration_state)) {
                    thread.resetMultipleValues();
                    final SubLObject key = do_dictionary_contents_key_value(iteration_state);
                    final SubLObject connection = thread.secondMultipleValue();
                    thread.resetMultipleValues();
                    SubLObject ignore_errors_tag = NIL;
                    try {
                        thread.throwStack.push(sksi_sks_interaction.$IGNORE_ERRORS_TARGET);
                        final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
                        try {
                            Errors.$error_handler$.bind(symbol_function(sksi_sks_interaction.IGNORE_ERRORS_HANDLER), thread);
                            try {
                                sksi_sks_interaction.sksi_close_sql_connection(connection);
                            } catch (final Throwable catch_var) {
                                Errors.handleThrowable(catch_var, NIL);
                            }
                        } finally {
                            Errors.$error_handler$.rebind(_prev_bind_0, thread);
                        }
                    } catch (final Throwable ccatch_env_var) {
                        ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, sksi_sks_interaction.$IGNORE_ERRORS_TARGET);
                    } finally {
                        thread.throwStack.pop();
                    }
                }
                do_dictionary_contents_finalize(iteration_state);
                clear_dictionary(sksi_sks_interaction.sksi_resourcing_cache_connections(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()));
                clear_dictionary(sksi_sks_interaction.sksi_resourcing_cache_statements(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()));
                clear_dictionary(sksi_sks_interaction.sksi_resourcing_cache_used_statements(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()));
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return NIL;
    }

    public static final SubLObject sksi_close_resourced_connections_for_sks_alt(SubLObject sks) {
        {
            SubLObject access_path = sksi_access_path.external_source_access_path(sks, UNPROVIDED, UNPROVIDED);
            if (NIL != sksi_access_path.access_path_p(access_path)) {
                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_reap_connection_for_access_path(access_path);
            }
        }
        return NIL;
    }

    public static SubLObject sksi_close_resourced_connections_for_sks(final SubLObject sks) {
        final SubLObject access_path = sksi_access_path.external_source_access_path(sks, UNPROVIDED, UNPROVIDED);
        if (NIL != sksi_access_path.access_path_p(access_path)) {
            sksi_sks_interaction.sksi_reap_connection_for_access_path(access_path);
        }
        return NIL;
    }

    public static final SubLObject sksi_reset_global_resourcing_max_idle_time_alt(SubLObject seconds) {
        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
            {
                SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock($sksi_resourcing_cache$.getGlobalValue());
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._csetf_sksi_resourcing_cache_max_idle($sksi_resourcing_cache$.getGlobalValue(), seconds);
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_reset_global_resourcing_max_idle_time(final SubLObject seconds) {
        if (NIL != sksi_sks_interaction.sksi_global_resourcing_initializedP()) {
            final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                sksi_sks_interaction._csetf_sksi_resourcing_cache_max_idle(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue(), seconds);
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return NIL;
    }

    public static final SubLObject sksi_possibly_reap_connections() {
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject iteration_state = dictionary_contents.do_dictionary_contents_state(dictionary.dictionary_contents(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_connections($sksi_resourcing_cache$.getGlobalValue())));
                while (NIL == dictionary_contents.do_dictionary_contents_doneP(iteration_state)) {
                    thread.resetMultipleValues();
                    {
                        SubLObject key = dictionary_contents.do_dictionary_contents_key_value(iteration_state);
                        SubLObject connection = thread.secondMultipleValue();
                        thread.resetMultipleValues();
                        if (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_possibly_remove_from_resourcing_cacheP(connection)) {
                            com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_reap_connection(key);
                        } else {
                            if (NIL == sdbc.sql_open_connection_p(connection)) {
                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_reap_connection(key);
                            }
                        }
                        iteration_state = dictionary_contents.do_dictionary_contents_next(iteration_state);
                    }
                } 
                dictionary_contents.do_dictionary_contents_finalize(iteration_state);
            }
            return NIL;
        }
    }

    public static SubLObject sksi_possibly_reap_connections(SubLObject forceP) {
        if (forceP == UNPROVIDED) {
            forceP = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject iteration_state;
        for (iteration_state = do_dictionary_contents_state(dictionary_contents(sksi_sks_interaction.sksi_resourcing_cache_connections(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()))); NIL == do_dictionary_contents_doneP(iteration_state); iteration_state = do_dictionary_contents_next(iteration_state)) {
            thread.resetMultipleValues();
            final SubLObject key = do_dictionary_contents_key_value(iteration_state);
            final SubLObject connection = thread.secondMultipleValue();
            thread.resetMultipleValues();
            if ((NIL != forceP) || (NIL != sksi_sks_interaction.sksi_possibly_remove_from_resourcing_cacheP(connection))) {
                sksi_sks_interaction.sksi_reap_connection(key);
            } else
                if (NIL == sql_open_connection_p(connection)) {
                    sksi_sks_interaction.sksi_reap_connection(key);
                }

        }
        do_dictionary_contents_finalize(iteration_state);
        return NIL;
    }

    public static final SubLObject sksi_reap_connection_for_access_path_alt(SubLObject access_path) {
        {
            SubLObject key = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_generate_cache_key_from_access_path(access_path);
            return com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_reap_connection(key);
        }
    }

    public static SubLObject sksi_reap_connection_for_access_path(final SubLObject access_path) {
        final SubLObject key = sksi_sks_interaction.sksi_generate_cache_key_from_access_path(access_path);
        return sksi_sks_interaction.sksi_reap_connection(key);
    }

    public static final SubLObject sksi_possibly_remove_from_resourcing_cacheP_alt(SubLObject connection) {
        return makeBoolean((NIL == com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourced_connection_has_active_statementsP(connection)) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourced_connection_idle_too_longP(connection)));
    }

    public static SubLObject sksi_possibly_remove_from_resourcing_cacheP(final SubLObject connection) {
        return makeBoolean((NIL == sksi_sks_interaction.sksi_resourced_connection_has_active_statementsP(connection)) && (NIL != sksi_sks_interaction.sksi_resourced_connection_idle_too_longP(connection)));
    }

    public static final SubLObject sksi_reap_connection_alt(SubLObject key) {
        if ((NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.within_sksi_global_resourcingP()) && (NIL != com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_global_resourcing_initializedP())) {
            {
                SubLObject lock = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_lock($sksi_resourcing_cache$.getGlobalValue());
                SubLObject release = NIL;
                try {
                    release = seize_lock(lock);
                    {
                        SubLObject connection = dictionary.dictionary_lookup_without_values(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_connections($sksi_resourcing_cache$.getGlobalValue()), key, UNPROVIDED);
                        if (NIL != connection) {
                            dictionary.dictionary_remove(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_statements($sksi_resourcing_cache$.getGlobalValue()), connection);
                            dictionary.dictionary_remove(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_used_statements($sksi_resourcing_cache$.getGlobalValue()), connection);
                            dictionary.dictionary_remove(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_access_times($sksi_resourcing_cache$.getGlobalValue()), connection);
                            if (NIL != sdbc.sql_open_connection_p(connection)) {
                                com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_close_sql_connection(connection);
                            }
                            dictionary.dictionary_remove(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_resourcing_cache_connections($sksi_resourcing_cache$.getGlobalValue()), key);
                        }
                    }
                } finally {
                    if (NIL != release) {
                        release_lock(lock);
                    }
                }
            }
        }
        return NIL;
    }

    public static SubLObject sksi_reap_connection(final SubLObject key) {
        if ((NIL != sksi_sks_interaction.within_sksi_global_resourcingP()) && (NIL != sksi_sks_interaction.sksi_global_resourcing_initializedP())) {
            final SubLObject lock = sksi_sks_interaction.sksi_resourcing_cache_lock(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue());
            SubLObject release = NIL;
            try {
                release = seize_lock(lock);
                final SubLObject connection = dictionary_lookup_without_values(sksi_sks_interaction.sksi_resourcing_cache_connections(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()), key, UNPROVIDED);
                if (NIL != connection) {
                    dictionary_remove(sksi_sks_interaction.sksi_resourcing_cache_statements(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()), connection);
                    dictionary_remove(sksi_sks_interaction.sksi_resourcing_cache_used_statements(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()), connection);
                    dictionary_remove(sksi_sks_interaction.sksi_resourcing_cache_access_times(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()), connection);
                    if (NIL != sql_open_connection_p(connection)) {
                        sksi_sks_interaction.sksi_close_sql_connection(connection);
                    }
                    dictionary_remove(sksi_sks_interaction.sksi_resourcing_cache_connections(sksi_sks_interaction.$sksi_resourcing_cache$.getGlobalValue()), key);
                }
            } finally {
                if (NIL != release) {
                    release_lock(lock);
                }
            }
        }
        return NIL;
    }

    public static SubLObject sk_source_status_fast(final SubLObject sks, SubLObject meta_mt) {
        if (meta_mt == UNPROVIDED) {
            meta_mt = sksi_kb_accessors.sk_source_mapping_mt(sks);
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject status = $ERROR;
        SubLObject ignore_errors_tag = NIL;
        try {
            thread.throwStack.push(sksi_sks_interaction.$IGNORE_ERRORS_TARGET);
            final SubLObject _prev_bind_0 = Errors.$error_handler$.currentBinding(thread);
            try {
                Errors.$error_handler$.bind(symbol_function(sksi_sks_interaction.IGNORE_ERRORS_HANDLER), thread);
                try {
                    final SubLObject _prev_bind_0_$10 = sksi_sks_interaction.$sksi_open_sql_source_default_max_tries$.currentBinding(thread);
                    final SubLObject _prev_bind_2 = sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.currentBinding(thread);
                    final SubLObject _prev_bind_3 = sksi_sks_interaction.$sksi_open_web_connection_default_timeout$.currentBinding(thread);
                    try {
                        sksi_sks_interaction.$sksi_open_sql_source_default_max_tries$.bind(ONE_INTEGER, thread);
                        sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.bind(sksi_sks_interaction.$float$0_2, thread);
                        sksi_sks_interaction.$sksi_open_web_connection_default_timeout$.bind(sksi_sks_interaction.$float$0_2, thread);
                        assert NIL != sksi_debugging.valid_sksi_error_handling_mode_p($IGNORE) : "! sksi_debugging.valid_sksi_error_handling_mode_p( _KW.$IGNORE) " + ("sksi_debugging.valid_sksi_error_handling_mode_p(sksi_sks_interaction.$kw259$IGNORE) " + "CommonSymbols.NIL != sksi_debugging.valid_sksi_error_handling_mode_p(sksi_sks_interaction.$kw259$IGNORE) ") + $IGNORE;
                        final SubLObject _prev_bind_0_$11 = sksi_debugging.$sksi_error_handling_mode$.currentBinding(thread);
                        try {
                            sksi_debugging.$sksi_error_handling_mode$.bind($IGNORE, thread);
                            if (NIL != sksi_infrastructure_utilities.sk_source_accessibleP(sks, meta_mt)) {
                                status = $UP;
                            } else {
                                status = $DOWN;
                            }
                        } finally {
                            sksi_debugging.$sksi_error_handling_mode$.rebind(_prev_bind_0_$11, thread);
                        }
                    } finally {
                        sksi_sks_interaction.$sksi_open_web_connection_default_timeout$.rebind(_prev_bind_3, thread);
                        sksi_sks_interaction.$sksi_open_sql_connection_default_timeout$.rebind(_prev_bind_2, thread);
                        sksi_sks_interaction.$sksi_open_sql_source_default_max_tries$.rebind(_prev_bind_0_$10, thread);
                    }
                } catch (final Throwable catch_var) {
                    Errors.handleThrowable(catch_var, NIL);
                }
            } finally {
                Errors.$error_handler$.rebind(_prev_bind_0, thread);
            }
        } catch (final Throwable ccatch_env_var) {
            ignore_errors_tag = Errors.handleThrowable(ccatch_env_var, sksi_sks_interaction.$IGNORE_ERRORS_TARGET);
        } finally {
            thread.throwStack.pop();
        }
        return status;
    }

    /**
     *
     *
     * @param SKS;
     * 		fort-p : the FORT for the structured knowledge source
     * @param SQL;
     * 		stringp : the SQL query to be sent to the kowledge source
     * @param META-MT;
     * 		fort-p : the mt that contains all meta info for SKS
     * @return iterator-p : an iterator to the result set returned by the knowledge
    source for the given SQL query.
     */
    @LispMethod(comment = "@param SKS;\r\n\t\tfort-p : the FORT for the structured knowledge source\r\n@param SQL;\r\n\t\tstringp : the SQL query to be sent to the kowledge source\r\n@param META-MT;\r\n\t\tfort-p : the mt that contains all meta info for SKS\r\n@return iterator-p : an iterator to the result set returned by the knowledge\r\nsource for the given SQL query.")
    public static final SubLObject get_rs_iterator_for_sql_query_from_db_sks_alt(SubLObject sks, SubLObject sql, SubLObject meta_mt) {
        if (meta_mt == UNPROVIDED) {
            meta_mt = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject rs_iterator = NIL;
                SubLObject mt_var = meta_mt;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
                        {
                            SubLObject access_path = sksi_access_path.external_source_access_path(sks, NIL, meta_mt);
                            SubLObject result_set = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.get_result_set_from_sql_source(sql, access_path);
                            if (NIL != sdbc.sql_result_set_p(result_set)) {
                                rs_iterator = sksi_result_set_iterators.new_sqlrs_iterator(result_set, UNPROVIDED, UNPROVIDED);
                            }
                        }
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return rs_iterator;
            }
        }
    }

    /**
     *
     *
     * @param SKS;
     * 		fort-p : the FORT for the structured knowledge source
     * @param SQL;
     * 		stringp : the SQL query to be sent to the kowledge source
     * @param META-MT;
     * 		fort-p : the mt that contains all meta info for SKS
     * @return iterator-p : an iterator to the result set returned by the knowledge
    source for the given SQL query.
     */
    @LispMethod(comment = "@param SKS;\r\n\t\tfort-p : the FORT for the structured knowledge source\r\n@param SQL;\r\n\t\tstringp : the SQL query to be sent to the kowledge source\r\n@param META-MT;\r\n\t\tfort-p : the mt that contains all meta info for SKS\r\n@return iterator-p : an iterator to the result set returned by the knowledge\r\nsource for the given SQL query.")
    public static SubLObject get_rs_iterator_for_sql_query_from_db_sks(final SubLObject sks, final SubLObject sql, SubLObject meta_mt) {
        if (meta_mt == UNPROVIDED) {
            meta_mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject rs_iterator = NIL;
        final SubLObject mt_var = meta_mt;
        final SubLObject _prev_bind_0 = $relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $mt$.currentBinding(thread);
        try {
            $relevant_mt_function$.bind(possibly_in_mt_determine_function(mt_var), thread);
            $mt$.bind(possibly_in_mt_determine_mt(mt_var), thread);
            final SubLObject access_path = sksi_access_path.external_source_access_path(sks, NIL, meta_mt);
            final SubLObject result_set = sksi_sks_interaction.get_result_set_from_sql_source(sql, access_path);
            if (NIL != sql_result_set_p(result_set)) {
                rs_iterator = sksi_result_set_iterators.new_sqlrs_iterator(result_set, UNPROVIDED, UNPROVIDED);
            }
        } finally {
            $mt$.rebind(_prev_bind_2, thread);
            $relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return rs_iterator;
    }

    /**
     *
     *
     * @param SKS;
     * 		fort-p : the FORT for the structured knowledge source
     * @param SQL;
     * 		stringp : the SQL update statement to be sent to the kowledge source
     * @param META-MT;
     * 		fort-p : the mt that contains all meta info for SKS
     * @return integerp : the number of rows in SKS affected by executing SQL
     */
    @LispMethod(comment = "@param SKS;\r\n\t\tfort-p : the FORT for the structured knowledge source\r\n@param SQL;\r\n\t\tstringp : the SQL update statement to be sent to the kowledge source\r\n@param META-MT;\r\n\t\tfort-p : the mt that contains all meta info for SKS\r\n@return integerp : the number of rows in SKS affected by executing SQL")
    public static final SubLObject sksi_execute_db_sks_update_alt(SubLObject sks, SubLObject sql, SubLObject meta_mt) {
        if (meta_mt == UNPROVIDED) {
            meta_mt = NIL;
        }
        {
            final SubLThread thread = SubLProcess.currentSubLThread();
            {
                SubLObject update_result = NIL;
                SubLObject mt_var = meta_mt;
                {
                    SubLObject _prev_bind_0 = mt_relevance_macros.$relevant_mt_function$.currentBinding(thread);
                    SubLObject _prev_bind_1 = mt_relevance_macros.$mt$.currentBinding(thread);
                    try {
                        mt_relevance_macros.$relevant_mt_function$.bind(mt_relevance_macros.possibly_in_mt_determine_function(mt_var), thread);
                        mt_relevance_macros.$mt$.bind(mt_relevance_macros.possibly_in_mt_determine_mt(mt_var), thread);
                        {
                            SubLObject access_path = sksi_access_path.external_source_access_path(sks, NIL, meta_mt);
                            if (NIL != sksi_access_path.access_path_p(access_path)) {
                                update_result = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.sksi_execute_sql_update(sql, access_path);
                            }
                        }
                    } finally {
                        mt_relevance_macros.$mt$.rebind(_prev_bind_1, thread);
                        mt_relevance_macros.$relevant_mt_function$.rebind(_prev_bind_0, thread);
                    }
                }
                return update_result;
            }
        }
    }

    /**
     *
     *
     * @param SKS;
     * 		fort-p : the FORT for the structured knowledge source
     * @param SQL;
     * 		stringp : the SQL update statement to be sent to the kowledge source
     * @param META-MT;
     * 		fort-p : the mt that contains all meta info for SKS
     * @return integerp : the number of rows in SKS affected by executing SQL
     */
    @LispMethod(comment = "@param SKS;\r\n\t\tfort-p : the FORT for the structured knowledge source\r\n@param SQL;\r\n\t\tstringp : the SQL update statement to be sent to the kowledge source\r\n@param META-MT;\r\n\t\tfort-p : the mt that contains all meta info for SKS\r\n@return integerp : the number of rows in SKS affected by executing SQL")
    public static SubLObject sksi_execute_db_sks_update(final SubLObject sks, final SubLObject sql, SubLObject meta_mt) {
        if (meta_mt == UNPROVIDED) {
            meta_mt = NIL;
        }
        final SubLThread thread = SubLProcess.currentSubLThread();
        SubLObject update_result = NIL;
        final SubLObject mt_var = meta_mt;
        final SubLObject _prev_bind_0 = $relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $mt$.currentBinding(thread);
        try {
            $relevant_mt_function$.bind(possibly_in_mt_determine_function(mt_var), thread);
            $mt$.bind(possibly_in_mt_determine_mt(mt_var), thread);
            final SubLObject access_path = sksi_access_path.external_source_access_path(sks, NIL, meta_mt);
            if (NIL != sksi_access_path.access_path_p(access_path)) {
                update_result = sksi_sks_interaction.sksi_execute_sql_update(sql, access_path);
            }
        } finally {
            $mt$.rebind(_prev_bind_2, thread);
            $relevant_mt_function$.rebind(_prev_bind_0, thread);
        }
        return update_result;
    }

    public static SubLObject sksi_execute_db_sks_update_in_mapping_mt(final SubLObject sks, final SubLObject sql) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject mapping_mt = sksi_kb_accessors.sk_source_mapping_mt(sks);
        final SubLObject mt_var = with_inference_mt_relevance_validate(mapping_mt);
        final SubLObject _prev_bind_0 = $mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $relevant_mts$.currentBinding(thread);
        try {
            $mt$.bind(update_inference_mt_relevance_mt(mt_var), thread);
            $relevant_mt_function$.bind(update_inference_mt_relevance_function(mt_var), thread);
            $relevant_mts$.bind(update_inference_mt_relevance_mt_list(mt_var), thread);
            return sksi_sks_interaction.sksi_execute_db_sks_update(sks, sql, UNPROVIDED);
        } finally {
            $relevant_mts$.rebind(_prev_bind_3, thread);
            $relevant_mt_function$.rebind(_prev_bind_2, thread);
            $mt$.rebind(_prev_bind_0, thread);
        }
    }

    public static SubLObject sksi_execute_csql(final SubLObject sks, final SubLObject csql, SubLObject mapping_mt) {
        if (mapping_mt == UNPROVIDED) {
            mapping_mt = NIL;
        }
        final SubLObject sql_flavor = sksi_kb_accessors.sk_source_sql_flavor(sks);
        final SubLObject sql = sksi_csql_interpretation.csql_to_sql(csql, sql_flavor, UNPROVIDED);
        return sksi_sks_interaction.sksi_execute_db_sks_update(sks, sql, mapping_mt);
    }

    public static SubLObject sksi_execute_csql_in_mapping_mt(final SubLObject sks, final SubLObject csql) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject mapping_mt = sksi_kb_accessors.sk_source_mapping_mt(sks);
        final SubLObject mt_var = with_inference_mt_relevance_validate(mapping_mt);
        final SubLObject _prev_bind_0 = $mt$.currentBinding(thread);
        final SubLObject _prev_bind_2 = $relevant_mt_function$.currentBinding(thread);
        final SubLObject _prev_bind_3 = $relevant_mts$.currentBinding(thread);
        try {
            $mt$.bind(update_inference_mt_relevance_mt(mt_var), thread);
            $relevant_mt_function$.bind(update_inference_mt_relevance_function(mt_var), thread);
            $relevant_mts$.bind(update_inference_mt_relevance_mt_list(mt_var), thread);
            return sksi_sks_interaction.sksi_execute_csql(sks, csql, UNPROVIDED);
        } finally {
            $relevant_mts$.rebind(_prev_bind_3, thread);
            $relevant_mt_function$.rebind(_prev_bind_2, thread);
            $mt$.rebind(_prev_bind_0, thread);
        }
    }

    public static SubLObject sksi_tactic_p(final SubLObject v_object) {
        if (NIL != inference_datastructures_tactic.tactic_p(v_object)) {
            final SubLObject hl_module = inference_datastructures_tactic.tactic_hl_module(v_object);
            return makeBoolean((NIL != member_eqP(inference_modules.hl_module_type(hl_module), sksi_sks_interaction.$list263)) || (NIL != member_eqP($SKSI, inference_modules.hl_module_subtypes(hl_module))));
        }
        return NIL;
    }

    public static SubLObject inference_possibly_note_salient_sksi_query_string(final SubLObject sksi_query_string) {
        assert NIL != stringp(sksi_query_string) : "! stringp(sksi_query_string) " + ("Types.stringp(sksi_query_string) " + "CommonSymbols.NIL != Types.stringp(sksi_query_string) ") + sksi_query_string;
        final SubLObject tactic = currently_executing_tactic();
        if (NIL != sksi_sks_interaction.sksi_tactic_p(tactic)) {
            return sksi_sks_interaction.sksi_tactic_note_salient_query_string(tactic, sksi_query_string);
        }
        return NIL;
    }

    public static SubLObject memoized_salient_sksi_query_string_internal(final SubLObject tactic) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        return sksi_sks_interaction.$sksi_salient_query_string$.getDynamicValue(thread);
    }

    public static SubLObject memoized_salient_sksi_query_string(final SubLObject tactic) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        final SubLObject v_memoization_state = $memoization_state$.getDynamicValue(thread);
        SubLObject caching_state = NIL;
        if (NIL == v_memoization_state) {
            return sksi_sks_interaction.memoized_salient_sksi_query_string_internal(tactic);
        }
        caching_state = memoization_state_lookup(v_memoization_state, sksi_sks_interaction.MEMOIZED_SALIENT_SKSI_QUERY_STRING, UNPROVIDED);
        if (NIL == caching_state) {
            caching_state = create_caching_state(memoization_state_lock(v_memoization_state), sksi_sks_interaction.MEMOIZED_SALIENT_SKSI_QUERY_STRING, ONE_INTEGER, NIL, EQ, UNPROVIDED);
            memoization_state_put(v_memoization_state, sksi_sks_interaction.MEMOIZED_SALIENT_SKSI_QUERY_STRING, caching_state);
        }
        SubLObject results = caching_state_lookup(caching_state, tactic, $memoized_item_not_found$.getGlobalValue());
        if (results.eql($memoized_item_not_found$.getGlobalValue())) {
            results = arg2(thread.resetMultipleValues(), multiple_value_list(sksi_sks_interaction.memoized_salient_sksi_query_string_internal(tactic)));
            caching_state_put(caching_state, tactic, results, UNPROVIDED);
        }
        return caching_results(results);
    }

    public static SubLObject sksi_tactic_salient_query_string(final SubLObject sksi_tactic) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != sksi_sks_interaction.sksi_tactic_p(sksi_tactic) : "! sksi_sks_interaction.sksi_tactic_p(sksi_tactic) " + ("sksi_sks_interaction.sksi_tactic_p(sksi_tactic) " + "CommonSymbols.NIL != sksi_sks_interaction.sksi_tactic_p(sksi_tactic) ") + sksi_tactic;
        if (NIL != inference_datastructures_tactic.tactic_has_statusP(sksi_tactic, sksi_sks_interaction.$EXECUTED_OR_IN_PROGRESS)) {
            final SubLObject store = inference_datastructures_tactic.tactic_store(sksi_tactic);
            SubLObject string = NIL;
            final SubLObject local_state = inference_datastructures_problem_store.problem_store_memoization_state(store);
            final SubLObject _prev_bind_0 = $memoization_state$.currentBinding(thread);
            try {
                $memoization_state$.bind(local_state, thread);
                final SubLObject original_memoization_process = memoization_state_original_process(local_state);
                try {
                    string = sksi_sks_interaction.memoized_salient_sksi_query_string(sksi_tactic);
                } finally {
                    final SubLObject _prev_bind_0_$12 = $is_thread_performing_cleanupP$.currentBinding(thread);
                    try {
                        $is_thread_performing_cleanupP$.bind(T, thread);
                        final SubLObject _values = getValuesAsVector();
                        memoization_state_possibly_clear_original_process(local_state, original_memoization_process);
                        restoreValuesFromVector(_values);
                    } finally {
                        $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$12, thread);
                    }
                }
            } finally {
                $memoization_state$.rebind(_prev_bind_0, thread);
            }
            return string;
        }
        return NIL;
    }

    public static SubLObject sksi_tactic_note_salient_query_string(final SubLObject sksi_tactic, final SubLObject sksi_query_string) {
        final SubLThread thread = SubLProcess.currentSubLThread();
        assert NIL != inference_datastructures_tactic.tactic_p(sksi_tactic) : "! inference_datastructures_tactic.tactic_p(sksi_tactic) " + ("inference_datastructures_tactic.tactic_p(sksi_tactic) " + "CommonSymbols.NIL != inference_datastructures_tactic.tactic_p(sksi_tactic) ") + sksi_tactic;
        assert NIL != stringp(sksi_query_string) : "! stringp(sksi_query_string) " + ("Types.stringp(sksi_query_string) " + "CommonSymbols.NIL != Types.stringp(sksi_query_string) ") + sksi_query_string;
        final SubLObject store = inference_datastructures_tactic.tactic_store(sksi_tactic);
        SubLObject result = NIL;
        final SubLObject local_state = inference_datastructures_problem_store.problem_store_memoization_state(store);
        final SubLObject _prev_bind_0 = $memoization_state$.currentBinding(thread);
        try {
            $memoization_state$.bind(local_state, thread);
            final SubLObject original_memoization_process = memoization_state_original_process(local_state);
            try {
                final SubLObject _prev_bind_0_$13 = sksi_sks_interaction.$sksi_salient_query_string$.currentBinding(thread);
                try {
                    sksi_sks_interaction.$sksi_salient_query_string$.bind(sksi_query_string, thread);
                    result = sksi_sks_interaction.memoized_salient_sksi_query_string(sksi_tactic);
                } finally {
                    sksi_sks_interaction.$sksi_salient_query_string$.rebind(_prev_bind_0_$13, thread);
                }
            } finally {
                final SubLObject _prev_bind_0_$14 = $is_thread_performing_cleanupP$.currentBinding(thread);
                try {
                    $is_thread_performing_cleanupP$.bind(T, thread);
                    final SubLObject _values = getValuesAsVector();
                    memoization_state_possibly_clear_original_process(local_state, original_memoization_process);
                    restoreValuesFromVector(_values);
                } finally {
                    $is_thread_performing_cleanupP$.rebind(_prev_bind_0_$14, thread);
                }
            }
        } finally {
            $memoization_state$.rebind(_prev_bind_0, thread);
        }
        return result;
    }

    public static final SubLObject declare_sksi_sks_interaction_file_alt() {
        declareFunction("generate_iterator_from_csql", "GENERATE-ITERATOR-FROM-CSQL", 2, 4, false);
        declareFunction("generate_boolean_from_csql", "GENERATE-BOOLEAN-FROM-CSQL", 2, 1, false);
        declareFunction("boolean_csql_to_sksi_supports", "BOOLEAN-CSQL-TO-SKSI-SUPPORTS", 3, 0, false);
        declareFunction("generate_db_iterator_from_csql", "GENERATE-DB-ITERATOR-FROM-CSQL", 2, 3, false);
        declareFunction("generate_db_boolean_from_csql", "GENERATE-DB-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_sdbc_result_set_from_csql", "GENERATE-SDBC-RESULT-SET-FROM-CSQL", 2, 1, false);
        declareFunction("generate_web_page_iterator_from_csql", "GENERATE-WEB-PAGE-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_web_page_boolean_from_csql", "GENERATE-WEB-PAGE-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_web_page_result_set", "GENERATE-WEB-PAGE-RESULT-SET", 2, 1, false);
        declareFunction("sksi_web_query_template_to_query", "SKSI-WEB-QUERY-TEMPLATE-TO-QUERY", 3, 0, false);
        declareFunction("sksi_web_query_patterns", "SKSI-WEB-QUERY-PATTERNS", 3, 0, false);
        declareFunction("sksi_web_query_where_pattern", "SKSI-WEB-QUERY-WHERE-PATTERN", 2, 0, false);
        declareFunction("sksi_web_query_field_positions", "SKSI-WEB-QUERY-FIELD-POSITIONS", 2, 0, false);
        declareFunction("sksi_web_page_result_set", "SKSI-WEB-PAGE-RESULT-SET", 2, 2, false);
        declareFunction("sksi_web_page_result_set_for_unbound_only", "SKSI-WEB-PAGE-RESULT-SET-FOR-UNBOUND-ONLY", 4, 0, false);
        declareFunction("test_sksi_web_page_result_set", "TEST-SKSI-WEB-PAGE-RESULT-SET", 3, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_memoized_internal", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-MEMOIZED-INTERNAL", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_memoized", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-MEMOIZED", 2, 0, false);
        declareFunction("clear_get_results_from_sksi_web_page_subl_method_cached", "CLEAR-GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 0, 0, false);
        declareFunction("remove_get_results_from_sksi_web_page_subl_method_cached", "REMOVE-GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_cached_internal", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED-INTERNAL", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_cached", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_none", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-NONE", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_int", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-INT", 2, 0, false);
        declareFunction("sksi_tokenized_http_request_internal", "SKSI-TOKENIZED-HTTP-REQUEST-INTERNAL", 3, 4, false);
        declareFunction("sksi_tokenized_http_request", "SKSI-TOKENIZED-HTTP-REQUEST", 3, 4, false);
        declareFunction("get_results_from_subl_parsing_function", "GET-RESULTS-FROM-SUBL-PARSING-FUNCTION", 2, 0, false);
        declareFunction("sksi_subl_parsing_program_p", "SKSI-SUBL-PARSING-PROGRAM-P", 1, 0, false);
        declareFunction("sksi_filter_result_set", "SKSI-FILTER-RESULT-SET", 3, 0, false);
        declareFunction("compute_selected_tuple", "COMPUTE-SELECTED-TUPLE", 2, 0, false);
        declareFunction("generate_kb_iterator_from_csql", "GENERATE-KB-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_kb_boolean_from_csql", "GENERATE-KB-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_fht_iterator_from_csql", "GENERATE-FHT-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_fht_boolean_from_csql", "GENERATE-FHT-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_cache_iterator_from_csql", "GENERATE-CACHE-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_cache_boolean_from_csql", "GENERATE-CACHE-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_rdf_iterator_from_csql", "GENERATE-RDF-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_rdf_boolean_from_csql", "GENERATE-RDF-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_sparql_result_set_from_csql", "GENERATE-SPARQL-RESULT-SET-FROM-CSQL", 2, 0, false);
        declareFunction("new_sparql_result_set_iterator", "NEW-SPARQL-RESULT-SET-ITERATOR", 2, 1, false);
        declareFunction("make_sparql_result_set_iterator_state", "MAKE-SPARQL-RESULT-SET-ITERATOR-STATE", 3, 0, false);
        declareFunction("sparql_result_set_iterator_done_p", "SPARQL-RESULT-SET-ITERATOR-DONE-P", 1, 0, false);
        declareFunction("sparql_result_set_iterator_next", "SPARQL-RESULT-SET-ITERATOR-NEXT", 1, 0, false);
        declareMacro("with_sksi_result_set_from_execute_query", "WITH-SKSI-RESULT-SET-FROM-EXECUTE-QUERY");
        declareFunction("sksi_execute_sqls_query", "SKSI-EXECUTE-SQLS-QUERY", 2, 0, false);
        declareFunction("sksi_execute_sqls_update", "SKSI-EXECUTE-SQLS-UPDATE", 2, 0, false);
        declareFunction("sksi_execute_batch_sql_updates", "SKSI-EXECUTE-BATCH-SQL-UPDATES", 1, 0, false);
        declareFunction("sksi_add_batch_sql_update", "SKSI-ADD-BATCH-SQL-UPDATE", 2, 0, false);
        declareFunction("sksi_execute_sql_query", "SKSI-EXECUTE-SQL-QUERY", 2, 0, false);
        declareFunction("sksi_execute_sql_query_int", "SKSI-EXECUTE-SQL-QUERY-INT", 4, 0, false);
        declareFunction("sksi_abort_and_release_sql_statement_and_connection", "SKSI-ABORT-AND-RELEASE-SQL-STATEMENT-AND-CONNECTION", 2, 0, false);
        declareFunction("sksi_execute_sql_update", "SKSI-EXECUTE-SQL-UPDATE", 2, 0, false);
        declareFunction("sksi_execute_sql_update_int", "SKSI-EXECUTE-SQL-UPDATE-INT", 4, 0, false);
        declareFunction("sksi_batch_execute_sql_statements", "SKSI-BATCH-EXECUTE-SQL-STATEMENTS", 3, 2, false);
        declareFunction("get_n_largest_auto_generated_keys", "GET-N-LARGEST-AUTO-GENERATED-KEYS", 5, 0, false);
        declareFunction("sksi_execute_sql_script_from_file", "SKSI-EXECUTE-SQL-SCRIPT-FROM-FILE", 2, 0, false);
        declareFunction("sksi_execute_sql_script", "SKSI-EXECUTE-SQL-SCRIPT", 2, 0, false);
        declareFunction("open_sql_source", "OPEN-SQL-SOURCE", 1, 2, false);
        declareFunction("sksi_open_sql_connection_and_statement", "SKSI-OPEN-SQL-CONNECTION-AND-STATEMENT", 1, 0, false);
        declareFunction("sksi_abort_sql_statement_and_connection", "SKSI-ABORT-SQL-STATEMENT-AND-CONNECTION", 2, 1, false);
        declareFunction("sksi_abort_sql_statement", "SKSI-ABORT-SQL-STATEMENT", 1, 0, false);
        declareFunction("sksi_close_sql_statement_and_connection", "SKSI-CLOSE-SQL-STATEMENT-AND-CONNECTION", 2, 0, false);
        declareFunction("sksi_close_sql_connection", "SKSI-CLOSE-SQL-CONNECTION", 1, 0, false);
        declareFunction("get_result_iterator_from_fht_source", "GET-RESULT-ITERATOR-FROM-FHT-SOURCE", 2, 0, false);
        declareFunction("get_selected_field_type_from_csql", "GET-SELECTED-FIELD-TYPE-FROM-CSQL", 1, 0, false);
        declareFunction("get_result_for_key_from_fht_source", "GET-RESULT-FOR-KEY-FROM-FHT-SOURCE", 2, 0, false);
        declareFunction("get_result_for_key_value_from_fht_source", "GET-RESULT-FOR-KEY-VALUE-FROM-FHT-SOURCE", 2, 0, false);
        declareFunction("put_key_value_under_key_in_fht_source", "PUT-KEY-VALUE-UNDER-KEY-IN-FHT-SOURCE", 3, 0, false);
        declareFunction("remove_key_value_under_key_in_fht_source", "REMOVE-KEY-VALUE-UNDER-KEY-IN-FHT-SOURCE", 3, 0, false);
        declareFunction("create_fht_source", "CREATE-FHT-SOURCE", 1, 0, false);
        declareFunction("open_fht_source", "OPEN-FHT-SOURCE", 1, 0, false);
        declareFunction("finalize_fht_source", "FINALIZE-FHT-SOURCE", 1, 0, false);
        declareFunction("cache_sql_connection_statement_for_result_set", "CACHE-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 3, 0, false);
        declareFunction("uncache_sql_connection_statement_for_result_set", "UNCACHE-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 1, 0, false);
        declareFunction("get_sql_connection_statement_for_result_set", "GET-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 1, 0, false);
        declareFunction("get_result_set_from_sql_source", "GET-RESULT-SET-FROM-SQL-SOURCE", 2, 0, false);
        declareFunction("get_result_set_from_sparql_source", "GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 0, false);
        declareFunction("get_result_set_from_sparql_source_guts", "GET-RESULT-SET-FROM-SPARQL-SOURCE-GUTS", 2, 0, false);
        declareFunction("sparql_request_query_ticket", "SPARQL-REQUEST-QUERY-TICKET", 1, 0, false);
        declareFunction("close_sparql_result_stream", "CLOSE-SPARQL-RESULT-STREAM", 1, 0, false);
        declareFunction("sparql_abort_query", "SPARQL-ABORT-QUERY", 2, 0, false);
        declareFunction("clear_cached_get_result_set_from_sparql_source", "CLEAR-CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 0, 0, false);
        declareFunction("remove_cached_get_result_set_from_sparql_source", "REMOVE-CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 0, false);
        declareFunction("cached_get_result_set_from_sparql_source_internal", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-INTERNAL", 2, 0, false);
        declareFunction("cached_get_result_set_from_sparql_source", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 0, false);
        declareFunction("save_sparql_result_set_cache", "SAVE-SPARQL-RESULT-SET-CACHE", 1, 0, false);
        declareFunction("load_sparql_result_set_cache", "LOAD-SPARQL-RESULT-SET-CACHE", 1, 0, false);
        declareFunction("sparql_extract_variable_names", "SPARQL-EXTRACT-VARIABLE-NAMES", 1, 0, false);
        declareFunction("sparql_query_xml_tokens", "SPARQL-QUERY-XML-TOKENS", 1, 1, false);
        declareFunction("open_sparql_query_result_stream", "OPEN-SPARQL-QUERY-RESULT-STREAM", 1, 2, false);
        declareFunction("stream_wait_until_read_char", "STREAM-WAIT-UNTIL-READ-CHAR", 1, 0, false);
        declareFunction("sparql_test", "SPARQL-TEST", 1, 2, false);
        declareFunction("sparql_test_result_set", "SPARQL-TEST-RESULT-SET", 1, 1, false);
        declareFunction("sparql_extract_rdf_prefix_map", "SPARQL-EXTRACT-RDF-PREFIX-MAP", 1, 0, false);
        declareFunction("sparql_extract_rdf_base", "SPARQL-EXTRACT-RDF-BASE", 1, 0, false);
        declareFunction("sparql_prefix_map_to_minimal_access_path", "SPARQL-PREFIX-MAP-TO-MINIMAL-ACCESS-PATH", 2, 0, false);
        declareFunction("sksi_sparql_xml_tokens_to_result_set", "SKSI-SPARQL-XML-TOKENS-TO-RESULT-SET", 3, 0, false);
        declareFunction("test_sksi_ist_graph", "TEST-SKSI-IST-GRAPH", 3, 0, false);
        declareFunction("sksi_unreachable_db_sks_error", "SKSI-UNREACHABLE-DB-SKS-ERROR", 2, 0, false);
        declareFunction("sksi_db_execution_error", "SKSI-DB-EXECUTION-ERROR", 3, 0, false);
        declareFunction("sksi_unreachable_fht_sks_error", "SKSI-UNREACHABLE-FHT-SKS-ERROR", 2, 0, false);
        declareFunction("sksi_create_fht_sks_error", "SKSI-CREATE-FHT-SKS-ERROR", 2, 0, false);
        declareFunction("all_structured_knowledge_sources_assumed_availableP", "ALL-STRUCTURED-KNOWLEDGE-SOURCES-ASSUMED-AVAILABLE?", 0, 0, false);
        declareFunction("note_sks_available", "NOTE-SKS-AVAILABLE", 1, 0, false);
        declareFunction("unnote_sks_available", "UNNOTE-SKS-AVAILABLE", 1, 0, false);
        declareFunction("sks_assumed_availableP", "SKS-ASSUMED-AVAILABLE?", 1, 0, false);
        declareFunction("sksi_sks_error", "SKSI-SKS-ERROR", 2, 1, false);
        declareFunction("sksi_unreachable_sks_error", "SKSI-UNREACHABLE-SKS-ERROR", 2, 0, false);
        declareFunction("sksi_sks_execution_error", "SKSI-SKS-EXECUTION-ERROR", 3, 0, false);
        declareFunction("get_sql_connection_and_statement", "GET-SQL-CONNECTION-AND-STATEMENT", 1, 0, false);
        declareFunction("release_sql_connection_and_statement", "RELEASE-SQL-CONNECTION-AND-STATEMENT", 2, 0, false);
        declareFunction("within_sksi_sql_connection_resourcingP", "WITHIN-SKSI-SQL-CONNECTION-RESOURCING?", 0, 0, false);
        declareFunction("within_sksi_local_resourcingP", "WITHIN-SKSI-LOCAL-RESOURCING?", 0, 0, false);
        declareFunction("get_sql_connection_and_statement_from_local_cache", "GET-SQL-CONNECTION-AND-STATEMENT-FROM-LOCAL-CACHE", 1, 0, false);
        declareFunction("get_sql_connection_from_local_cache", "GET-SQL-CONNECTION-FROM-LOCAL-CACHE", 1, 0, false);
        declareFunction("get_sql_statement_from_local_cache", "GET-SQL-STATEMENT-FROM-LOCAL-CACHE", 1, 0, false);
        declareFunction("release_sql_connection_and_statement_to_local_cache", "RELEASE-SQL-CONNECTION-AND-STATEMENT-TO-LOCAL-CACHE", 2, 0, false);
        declareFunction("finalize_sql_connection_and_statement_cache", "FINALIZE-SQL-CONNECTION-AND-STATEMENT-CACHE", 0, 0, false);
        declareFunction("within_sksi_global_resourcingP", "WITHIN-SKSI-GLOBAL-RESOURCING?", 0, 0, false);
        declareFunction("sksi_global_resourcing_initializedP", "SKSI-GLOBAL-RESOURCING-INITIALIZED?", 0, 0, false);
        declareFunction("sksi_resourcing_cache_print_function_trampoline", "SKSI-RESOURCING-CACHE-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("sksi_resourcing_cache_p", "SKSI-RESOURCING-CACHE-P", 1, 0, false);
        new com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.$sksi_resourcing_cache_p$UnaryFunction();
        declareFunction("sksi_resourcing_cache_connections", "SKSI-RESOURCING-CACHE-CONNECTIONS", 1, 0, false);
        declareFunction("sksi_resourcing_cache_statements", "SKSI-RESOURCING-CACHE-STATEMENTS", 1, 0, false);
        declareFunction("sksi_resourcing_cache_used_statements", "SKSI-RESOURCING-CACHE-USED-STATEMENTS", 1, 0, false);
        declareFunction("sksi_resourcing_cache_access_times", "SKSI-RESOURCING-CACHE-ACCESS-TIMES", 1, 0, false);
        declareFunction("sksi_resourcing_cache_lock", "SKSI-RESOURCING-CACHE-LOCK", 1, 0, false);
        declareFunction("sksi_resourcing_cache_max_pool_size", "SKSI-RESOURCING-CACHE-MAX-POOL-SIZE", 1, 0, false);
        declareFunction("sksi_resourcing_cache_max_idle", "SKSI-RESOURCING-CACHE-MAX-IDLE", 1, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_connections", "_CSETF-SKSI-RESOURCING-CACHE-CONNECTIONS", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_statements", "_CSETF-SKSI-RESOURCING-CACHE-STATEMENTS", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_used_statements", "_CSETF-SKSI-RESOURCING-CACHE-USED-STATEMENTS", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_access_times", "_CSETF-SKSI-RESOURCING-CACHE-ACCESS-TIMES", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_lock", "_CSETF-SKSI-RESOURCING-CACHE-LOCK", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_max_pool_size", "_CSETF-SKSI-RESOURCING-CACHE-MAX-POOL-SIZE", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_max_idle", "_CSETF-SKSI-RESOURCING-CACHE-MAX-IDLE", 2, 0, false);
        declareFunction("make_sksi_resourcing_cache", "MAKE-SKSI-RESOURCING-CACHE", 0, 1, false);
        declareFunction("new_sksi_resourcing_cache", "NEW-SKSI-RESOURCING-CACHE", 0, 2, false);
        declareMacro("with_sksi_global_resourcing_lock_held", "WITH-SKSI-GLOBAL-RESOURCING-LOCK-HELD");
        declareFunction("sksi_add_connection_to_cache", "SKSI-ADD-CONNECTION-TO-CACHE", 3, 0, false);
        declareFunction("sksi_add_statement_to_cache", "SKSI-ADD-STATEMENT-TO-CACHE", 3, 0, false);
        declareFunction("sksi_note_access_time_in_cache", "SKSI-NOTE-ACCESS-TIME-IN-CACHE", 2, 0, false);
        declareFunction("sksi_retrieve_connection_from_cache", "SKSI-RETRIEVE-CONNECTION-FROM-CACHE", 2, 0, false);
        declareFunction("sksi_retrieve_statement_from_cache", "SKSI-RETRIEVE-STATEMENT-FROM-CACHE", 2, 0, false);
        declareFunction("sksi_add_statement_to_used_statements_set", "SKSI-ADD-STATEMENT-TO-USED-STATEMENTS-SET", 3, 0, false);
        declareFunction("sksi_last_cache_access_time", "SKSI-LAST-CACHE-ACCESS-TIME", 2, 0, false);
        declareFunction("sksi_generate_cache_key_from_access_path", "SKSI-GENERATE-CACHE-KEY-FROM-ACCESS-PATH", 1, 0, false);
        declareFunction("get_sql_connection_and_statement_from_global_cache", "GET-SQL-CONNECTION-AND-STATEMENT-FROM-GLOBAL-CACHE", 1, 0, false);
        declareFunction("release_sql_connection_and_statement_to_global_cache", "RELEASE-SQL-CONNECTION-AND-STATEMENT-TO-GLOBAL-CACHE", 2, 0, false);
        declareFunction("sksi_resourced_connection_has_active_statementsP", "SKSI-RESOURCED-CONNECTION-HAS-ACTIVE-STATEMENTS?", 1, 0, false);
        declareFunction("sksi_resourced_connection_idle_too_longP", "SKSI-RESOURCED-CONNECTION-IDLE-TOO-LONG?", 1, 0, false);
        declareFunction("sksi_initialize_global_resourcing", "SKSI-INITIALIZE-GLOBAL-RESOURCING", 0, 2, false);
        declareFunction("sksi_finalize_global_resourcing", "SKSI-FINALIZE-GLOBAL-RESOURCING", 0, 0, false);
        declareFunction("sksi_close_resourced_connections", "SKSI-CLOSE-RESOURCED-CONNECTIONS", 0, 0, false);
        declareFunction("sksi_close_resourced_connections_for_sks", "SKSI-CLOSE-RESOURCED-CONNECTIONS-FOR-SKS", 1, 0, false);
        declareFunction("sksi_reset_global_resourcing_max_idle_time", "SKSI-RESET-GLOBAL-RESOURCING-MAX-IDLE-TIME", 1, 0, false);
        declareFunction("sksi_possibly_reap_connections", "SKSI-POSSIBLY-REAP-CONNECTIONS", 0, 0, false);
        declareFunction("sksi_reap_connection_for_access_path", "SKSI-REAP-CONNECTION-FOR-ACCESS-PATH", 1, 0, false);
        declareFunction("sksi_possibly_remove_from_resourcing_cacheP", "SKSI-POSSIBLY-REMOVE-FROM-RESOURCING-CACHE?", 1, 0, false);
        declareFunction("sksi_reap_connection", "SKSI-REAP-CONNECTION", 1, 0, false);
        declareFunction("get_rs_iterator_for_sql_query_from_db_sks", "GET-RS-ITERATOR-FOR-SQL-QUERY-FROM-DB-SKS", 2, 1, false);
        declareFunction("sksi_execute_db_sks_update", "SKSI-EXECUTE-DB-SKS-UPDATE", 2, 1, false);
        return NIL;
    }

    public static SubLObject declare_sksi_sks_interaction_file() {
        if (SubLFiles.USE_V1) {
            declareFunction("generate_iterator_from_csql", "GENERATE-ITERATOR-FROM-CSQL", 2, 4, false);
            declareFunction("generate_boolean_from_csql", "GENERATE-BOOLEAN-FROM-CSQL", 2, 1, false);
            declareFunction("boolean_csql_to_sksi_supports", "BOOLEAN-CSQL-TO-SKSI-SUPPORTS", 3, 0, false);
            declareFunction("generate_db_iterator_from_csql", "GENERATE-DB-ITERATOR-FROM-CSQL", 2, 3, false);
            declareFunction("generate_db_boolean_from_csql", "GENERATE-DB-BOOLEAN-FROM-CSQL", 2, 0, false);
            declareFunction("generate_sdbc_result_set_from_csql", "GENERATE-SDBC-RESULT-SET-FROM-CSQL", 2, 1, false);
            declareFunction("generate_web_page_iterator_from_csql", "GENERATE-WEB-PAGE-ITERATOR-FROM-CSQL", 2, 0, false);
            declareFunction("generate_web_page_boolean_from_csql", "GENERATE-WEB-PAGE-BOOLEAN-FROM-CSQL", 2, 0, false);
            declareFunction("generate_web_page_result_set", "GENERATE-WEB-PAGE-RESULT-SET", 2, 1, false);
            declareFunction("sksi_web_query_template_to_query", "SKSI-WEB-QUERY-TEMPLATE-TO-QUERY", 3, 0, false);
            declareFunction("sksi_web_query_patterns", "SKSI-WEB-QUERY-PATTERNS", 3, 0, false);
            declareFunction("sksi_web_query_where_pattern", "SKSI-WEB-QUERY-WHERE-PATTERN", 2, 0, false);
            declareFunction("sksi_web_query_field_positions", "SKSI-WEB-QUERY-FIELD-POSITIONS", 2, 0, false);
            declareFunction("sksi_web_page_result_set", "SKSI-WEB-PAGE-RESULT-SET", 2, 2, false);
            declareFunction("sksi_web_page_result_set_for_unbound_only", "SKSI-WEB-PAGE-RESULT-SET-FOR-UNBOUND-ONLY", 4, 0, false);
            declareFunction("test_sksi_web_page_result_set", "TEST-SKSI-WEB-PAGE-RESULT-SET", 3, 0, false);
            declareFunction("get_results_from_sksi_web_page_subl_method", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD", 2, 0, false);
            declareFunction("get_results_from_sksi_web_page_subl_method_memoized_internal", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-MEMOIZED-INTERNAL", 2, 0, false);
            declareFunction("get_results_from_sksi_web_page_subl_method_memoized", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-MEMOIZED", 2, 0, false);
            declareFunction("clear_get_results_from_sksi_web_page_subl_method_cached", "CLEAR-GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 0, 0, false);
            declareFunction("remove_get_results_from_sksi_web_page_subl_method_cached", "REMOVE-GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 2, 0, false);
            declareFunction("get_results_from_sksi_web_page_subl_method_cached_internal", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED-INTERNAL", 2, 0, false);
            declareFunction("get_results_from_sksi_web_page_subl_method_cached", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 2, 0, false);
            declareFunction("get_results_from_sksi_web_page_subl_method_none", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-NONE", 2, 0, false);
            declareFunction("get_results_from_sksi_web_page_subl_method_int", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-INT", 2, 0, false);
            declareFunction("sksi_tokenized_http_request_internal", "SKSI-TOKENIZED-HTTP-REQUEST-INTERNAL", 3, 4, false);
            declareFunction("sksi_tokenized_http_request", "SKSI-TOKENIZED-HTTP-REQUEST", 3, 4, false);
            declareFunction("get_results_from_subl_parsing_function", "GET-RESULTS-FROM-SUBL-PARSING-FUNCTION", 2, 0, false);
            declareFunction("sksi_subl_parsing_program_p", "SKSI-SUBL-PARSING-PROGRAM-P", 1, 0, false);
            declareFunction("sksi_filter_result_set", "SKSI-FILTER-RESULT-SET", 3, 0, false);
            declareFunction("compute_selected_tuple", "COMPUTE-SELECTED-TUPLE", 2, 0, false);
            declareFunction("generate_kb_iterator_from_csql", "GENERATE-KB-ITERATOR-FROM-CSQL", 2, 0, false);
            declareFunction("generate_kb_boolean_from_csql", "GENERATE-KB-BOOLEAN-FROM-CSQL", 2, 0, false);
            declareFunction("generate_fht_iterator_from_csql", "GENERATE-FHT-ITERATOR-FROM-CSQL", 2, 0, false);
            declareFunction("generate_fht_boolean_from_csql", "GENERATE-FHT-BOOLEAN-FROM-CSQL", 2, 0, false);
            declareFunction("generate_cache_iterator_from_csql", "GENERATE-CACHE-ITERATOR-FROM-CSQL", 2, 0, false);
            declareFunction("generate_cache_boolean_from_csql", "GENERATE-CACHE-BOOLEAN-FROM-CSQL", 2, 0, false);
            declareFunction("generate_rdf_iterator_from_csql", "GENERATE-RDF-ITERATOR-FROM-CSQL", 2, 0, false);
            declareFunction("generate_rdf_boolean_from_csql", "GENERATE-RDF-BOOLEAN-FROM-CSQL", 2, 0, false);
            declareFunction("generate_sparql_result_set_from_csql", "GENERATE-SPARQL-RESULT-SET-FROM-CSQL", 2, 0, false);
            declareFunction("new_sparql_result_set_iterator", "NEW-SPARQL-RESULT-SET-ITERATOR", 2, 1, false);
            declareFunction("make_sparql_result_set_iterator_state", "MAKE-SPARQL-RESULT-SET-ITERATOR-STATE", 3, 0, false);
            declareFunction("sparql_result_set_iterator_done_p", "SPARQL-RESULT-SET-ITERATOR-DONE-P", 1, 0, false);
            declareFunction("sparql_result_set_iterator_next", "SPARQL-RESULT-SET-ITERATOR-NEXT", 1, 0, false);
            declareMacro("with_sksi_result_set_from_execute_query", "WITH-SKSI-RESULT-SET-FROM-EXECUTE-QUERY");
            declareFunction("sksi_execute_sqls_query", "SKSI-EXECUTE-SQLS-QUERY", 2, 0, false);
            declareFunction("sksi_execute_sqls_update", "SKSI-EXECUTE-SQLS-UPDATE", 2, 0, false);
            declareFunction("sksi_execute_batch_sql_updates", "SKSI-EXECUTE-BATCH-SQL-UPDATES", 1, 0, false);
            declareFunction("sksi_add_batch_sql_update", "SKSI-ADD-BATCH-SQL-UPDATE", 2, 0, false);
            declareFunction("sksi_execute_sql_query", "SKSI-EXECUTE-SQL-QUERY", 2, 0, false);
            declareFunction("sksi_execute_sql_query_int", "SKSI-EXECUTE-SQL-QUERY-INT", 4, 0, false);
            declareFunction("sksi_abort_and_release_sql_statement_and_connection", "SKSI-ABORT-AND-RELEASE-SQL-STATEMENT-AND-CONNECTION", 2, 0, false);
            declareFunction("sksi_execute_sql_update", "SKSI-EXECUTE-SQL-UPDATE", 2, 0, false);
            declareFunction("sksi_execute_sql_update_int", "SKSI-EXECUTE-SQL-UPDATE-INT", 4, 0, false);
            declareFunction("sksi_batch_execute_sql_statements", "SKSI-BATCH-EXECUTE-SQL-STATEMENTS", 3, 2, false);
            declareFunction("get_n_largest_auto_generated_keys", "GET-N-LARGEST-AUTO-GENERATED-KEYS", 5, 0, false);
            declareFunction("sksi_execute_sql_script_from_file", "SKSI-EXECUTE-SQL-SCRIPT-FROM-FILE", 2, 0, false);
            declareFunction("sksi_execute_sql_script", "SKSI-EXECUTE-SQL-SCRIPT", 2, 0, false);
            declareFunction("open_sql_source", "OPEN-SQL-SOURCE", 1, 2, false);
            declareFunction("sksi_open_sql_connection_and_statement", "SKSI-OPEN-SQL-CONNECTION-AND-STATEMENT", 1, 0, false);
            declareFunction("sksi_abort_sql_statement_and_connection", "SKSI-ABORT-SQL-STATEMENT-AND-CONNECTION", 2, 1, false);
            declareFunction("sksi_abort_sql_statement", "SKSI-ABORT-SQL-STATEMENT", 1, 0, false);
            declareFunction("sksi_close_sql_statement_and_connection", "SKSI-CLOSE-SQL-STATEMENT-AND-CONNECTION", 2, 0, false);
            declareFunction("sksi_close_sql_connection", "SKSI-CLOSE-SQL-CONNECTION", 1, 0, false);
            declareFunction("get_result_iterator_from_fht_source", "GET-RESULT-ITERATOR-FROM-FHT-SOURCE", 2, 0, false);
            declareFunction("get_selected_field_type_from_csql", "GET-SELECTED-FIELD-TYPE-FROM-CSQL", 1, 0, false);
            declareFunction("get_result_for_key_from_fht_source", "GET-RESULT-FOR-KEY-FROM-FHT-SOURCE", 2, 0, false);
            declareFunction("get_result_for_key_value_from_fht_source", "GET-RESULT-FOR-KEY-VALUE-FROM-FHT-SOURCE", 2, 0, false);
            declareFunction("put_key_value_under_key_in_fht_source", "PUT-KEY-VALUE-UNDER-KEY-IN-FHT-SOURCE", 3, 0, false);
            declareFunction("remove_key_value_under_key_in_fht_source", "REMOVE-KEY-VALUE-UNDER-KEY-IN-FHT-SOURCE", 3, 0, false);
            declareFunction("create_fht_source", "CREATE-FHT-SOURCE", 1, 0, false);
            declareFunction("open_fht_source", "OPEN-FHT-SOURCE", 1, 0, false);
            declareFunction("finalize_fht_source", "FINALIZE-FHT-SOURCE", 1, 0, false);
            declareFunction("cache_sql_connection_statement_for_result_set", "CACHE-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 3, 0, false);
            declareFunction("uncache_sql_connection_statement_for_result_set", "UNCACHE-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 1, 0, false);
            declareFunction("get_sql_connection_statement_for_result_set", "GET-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 1, 0, false);
            declareFunction("clear_cached_get_result_set_from_sql_source", "CLEAR-CACHED-GET-RESULT-SET-FROM-SQL-SOURCE", 0, 0, false);
            declareFunction("remove_cached_get_result_set_from_sql_source", "REMOVE-CACHED-GET-RESULT-SET-FROM-SQL-SOURCE", 2, 0, false);
            declareFunction("cached_get_result_set_from_sql_source_internal", "CACHED-GET-RESULT-SET-FROM-SQL-SOURCE-INTERNAL", 2, 0, false);
            declareFunction("cached_get_result_set_from_sql_source", "CACHED-GET-RESULT-SET-FROM-SQL-SOURCE", 2, 0, false);
            declareFunction("get_result_set_from_sql_source", "GET-RESULT-SET-FROM-SQL-SOURCE", 2, 0, false);
            declareFunction("get_result_set_from_sparql_source", "GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 2, false);
            declareFunction("get_result_set_from_sparql_source_guts", "GET-RESULT-SET-FROM-SPARQL-SOURCE-GUTS", 4, 0, false);
            declareFunction("sparql_oracle_subprotocolP", "SPARQL-ORACLE-SUBPROTOCOL?", 1, 0, false);
            declareFunction("sparql_request_query_ticket", "SPARQL-REQUEST-QUERY-TICKET", 1, 0, false);
            declareFunction("sparql_triclops_request_query_ticket", "SPARQL-TRICLOPS-REQUEST-QUERY-TICKET", 1, 0, false);
            declareFunction("sparql_oracle_joseki_request_query_ticket", "SPARQL-ORACLE-JOSEKI-REQUEST-QUERY-TICKET", 0, 1, false);
            declareFunction("sparql_oracle_sesame_request_query_ticket", "SPARQL-ORACLE-SESAME-REQUEST-QUERY-TICKET", 0, 1, false);
            declareFunction("preprocess_sparql_for_backend", "PREPROCESS-SPARQL-FOR-BACKEND", 2, 1, false);
            declareFunction("compute_sparql_oracle_prefix_pragma", "COMPUTE-SPARQL-ORACLE-PREFIX-PRAGMA", 1, 0, false);
            declareFunction("compute_sparql_oracle_query_timeout", "COMPUTE-SPARQL-ORACLE-QUERY-TIMEOUT", 0, 0, false);
            declareFunction("close_sparql_result_stream", "CLOSE-SPARQL-RESULT-STREAM", 1, 0, false);
            declareFunction("sparql_abort_query", "SPARQL-ABORT-QUERY", 2, 0, false);
            declareFunction("test_sparql_abort_query", "TEST-SPARQL-ABORT-QUERY", 2, 0, false);
            declareFunction("clear_cached_get_result_set_from_sparql_source", "CLEAR-CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 0, 0, false);
            declareFunction("remove_cached_get_result_set_from_sparql_source", "REMOVE-CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 4, 0, false);
            declareFunction("cached_get_result_set_from_sparql_source_internal", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-INTERNAL", 4, 0, false);
            declareFunction("cached_get_result_set_from_sparql_source", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 4, 0, false);
            declareFunction("save_sparql_result_set_cache", "SAVE-SPARQL-RESULT-SET-CACHE", 1, 0, false);
            declareFunction("load_sparql_result_set_cache", "LOAD-SPARQL-RESULT-SET-CACHE", 1, 0, false);
            declareFunction("sparql_extract_variable_names", "SPARQL-EXTRACT-VARIABLE-NAMES", 1, 0, false);
            declareFunction("sparql_query_xml_tokens", "SPARQL-QUERY-XML-TOKENS", 1, 1, false);
            declareFunction("open_sparql_query_result_stream", "OPEN-SPARQL-QUERY-RESULT-STREAM", 1, 2, false);
            declareFunction("stream_wait_until_read_char", "STREAM-WAIT-UNTIL-READ-CHAR", 1, 0, false);
            declareFunction("sparql_test", "SPARQL-TEST", 1, 2, false);
            declareFunction("sparql_test_result_set", "SPARQL-TEST-RESULT-SET", 1, 1, false);
            declareFunction("sparql_extract_booleanP", "SPARQL-EXTRACT-BOOLEAN?", 1, 0, false);
            declareFunction("sparql_extract_rdf_prefix_map", "SPARQL-EXTRACT-RDF-PREFIX-MAP", 1, 0, false);
            declareFunction("sparql_extract_rdf_base", "SPARQL-EXTRACT-RDF-BASE", 1, 0, false);
            declareFunction("sparql_prefix_map_to_minimal_access_path", "SPARQL-PREFIX-MAP-TO-MINIMAL-ACCESS-PATH", 2, 0, false);
            declareFunction("sksi_sparql_xml_tokens_to_result_set", "SKSI-SPARQL-XML-TOKENS-TO-RESULT-SET", 3, 0, false);
            declareFunction("test_sksi_ist_graph", "TEST-SKSI-IST-GRAPH", 3, 0, false);
            declareFunction("sksi_unreachable_db_sks_error", "SKSI-UNREACHABLE-DB-SKS-ERROR", 2, 0, false);
            declareFunction("sksi_db_execution_error", "SKSI-DB-EXECUTION-ERROR", 3, 0, false);
            declareFunction("sksi_unreachable_fht_sks_error", "SKSI-UNREACHABLE-FHT-SKS-ERROR", 2, 0, false);
            declareFunction("sksi_create_fht_sks_error", "SKSI-CREATE-FHT-SKS-ERROR", 2, 0, false);
            declareFunction("all_structured_knowledge_sources_assumed_availableP", "ALL-STRUCTURED-KNOWLEDGE-SOURCES-ASSUMED-AVAILABLE?", 0, 0, false);
            declareFunction("note_sks_available", "NOTE-SKS-AVAILABLE", 1, 0, false);
            declareFunction("unnote_sks_available", "UNNOTE-SKS-AVAILABLE", 1, 0, false);
            declareFunction("sks_assumed_availableP", "SKS-ASSUMED-AVAILABLE?", 1, 0, false);
            declareFunction("sksi_sks_error", "SKSI-SKS-ERROR", 2, 2, false);
            declareFunction("sksi_unreachable_sks_error", "SKSI-UNREACHABLE-SKS-ERROR", 2, 0, false);
            declareFunction("sksi_sks_execution_error", "SKSI-SKS-EXECUTION-ERROR", 4, 0, false);
            declareFunction("get_sql_connection_and_statement", "GET-SQL-CONNECTION-AND-STATEMENT", 1, 0, false);
            declareFunction("release_sql_connection_and_statement", "RELEASE-SQL-CONNECTION-AND-STATEMENT", 2, 0, false);
            declareFunction("within_sksi_sql_connection_resourcingP", "WITHIN-SKSI-SQL-CONNECTION-RESOURCING?", 0, 0, false);
            declareFunction("within_sksi_local_resourcingP", "WITHIN-SKSI-LOCAL-RESOURCING?", 0, 0, false);
            declareFunction("get_sql_connection_and_statement_from_local_cache", "GET-SQL-CONNECTION-AND-STATEMENT-FROM-LOCAL-CACHE", 1, 0, false);
            declareFunction("get_sql_connection_from_local_cache", "GET-SQL-CONNECTION-FROM-LOCAL-CACHE", 1, 0, false);
            declareFunction("get_sql_statement_from_local_cache", "GET-SQL-STATEMENT-FROM-LOCAL-CACHE", 1, 0, false);
            declareFunction("release_sql_connection_and_statement_to_local_cache", "RELEASE-SQL-CONNECTION-AND-STATEMENT-TO-LOCAL-CACHE", 2, 0, false);
            declareFunction("finalize_sql_connection_and_statement_cache", "FINALIZE-SQL-CONNECTION-AND-STATEMENT-CACHE", 0, 0, false);
            declareFunction("within_sksi_global_resourcingP", "WITHIN-SKSI-GLOBAL-RESOURCING?", 0, 0, false);
            declareFunction("sksi_global_resourcing_initializedP", "SKSI-GLOBAL-RESOURCING-INITIALIZED?", 0, 0, false);
            declareFunction("sksi_resourcing_cache_print_function_trampoline", "SKSI-RESOURCING-CACHE-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
            declareFunction("sksi_resourcing_cache_p", "SKSI-RESOURCING-CACHE-P", 1, 0, false);
            new sksi_sks_interaction.$sksi_resourcing_cache_p$UnaryFunction();
            declareFunction("sksi_resourcing_cache_connections", "SKSI-RESOURCING-CACHE-CONNECTIONS", 1, 0, false);
            declareFunction("sksi_resourcing_cache_statements", "SKSI-RESOURCING-CACHE-STATEMENTS", 1, 0, false);
            declareFunction("sksi_resourcing_cache_used_statements", "SKSI-RESOURCING-CACHE-USED-STATEMENTS", 1, 0, false);
            declareFunction("sksi_resourcing_cache_access_times", "SKSI-RESOURCING-CACHE-ACCESS-TIMES", 1, 0, false);
            declareFunction("sksi_resourcing_cache_lock", "SKSI-RESOURCING-CACHE-LOCK", 1, 0, false);
            declareFunction("sksi_resourcing_cache_max_pool_size", "SKSI-RESOURCING-CACHE-MAX-POOL-SIZE", 1, 0, false);
            declareFunction("sksi_resourcing_cache_max_idle", "SKSI-RESOURCING-CACHE-MAX-IDLE", 1, 0, false);
            declareFunction("_csetf_sksi_resourcing_cache_connections", "_CSETF-SKSI-RESOURCING-CACHE-CONNECTIONS", 2, 0, false);
            declareFunction("_csetf_sksi_resourcing_cache_statements", "_CSETF-SKSI-RESOURCING-CACHE-STATEMENTS", 2, 0, false);
            declareFunction("_csetf_sksi_resourcing_cache_used_statements", "_CSETF-SKSI-RESOURCING-CACHE-USED-STATEMENTS", 2, 0, false);
            declareFunction("_csetf_sksi_resourcing_cache_access_times", "_CSETF-SKSI-RESOURCING-CACHE-ACCESS-TIMES", 2, 0, false);
            declareFunction("_csetf_sksi_resourcing_cache_lock", "_CSETF-SKSI-RESOURCING-CACHE-LOCK", 2, 0, false);
            declareFunction("_csetf_sksi_resourcing_cache_max_pool_size", "_CSETF-SKSI-RESOURCING-CACHE-MAX-POOL-SIZE", 2, 0, false);
            declareFunction("_csetf_sksi_resourcing_cache_max_idle", "_CSETF-SKSI-RESOURCING-CACHE-MAX-IDLE", 2, 0, false);
            declareFunction("make_sksi_resourcing_cache", "MAKE-SKSI-RESOURCING-CACHE", 0, 1, false);
            declareFunction("visit_defstruct_sksi_resourcing_cache", "VISIT-DEFSTRUCT-SKSI-RESOURCING-CACHE", 2, 0, false);
            declareFunction("visit_defstruct_object_sksi_resourcing_cache_method", "VISIT-DEFSTRUCT-OBJECT-SKSI-RESOURCING-CACHE-METHOD", 2, 0, false);
            declareFunction("new_sksi_resourcing_cache", "NEW-SKSI-RESOURCING-CACHE", 0, 2, false);
            declareMacro("with_sksi_global_resourcing_lock_held", "WITH-SKSI-GLOBAL-RESOURCING-LOCK-HELD");
            declareFunction("sksi_add_connection_to_cache", "SKSI-ADD-CONNECTION-TO-CACHE", 3, 0, false);
            declareFunction("sksi_add_statement_to_cache", "SKSI-ADD-STATEMENT-TO-CACHE", 3, 0, false);
            declareFunction("sksi_note_access_time_in_cache", "SKSI-NOTE-ACCESS-TIME-IN-CACHE", 2, 0, false);
            declareFunction("sksi_retrieve_connection_from_cache", "SKSI-RETRIEVE-CONNECTION-FROM-CACHE", 2, 0, false);
            declareFunction("sksi_retrieve_statement_from_cache", "SKSI-RETRIEVE-STATEMENT-FROM-CACHE", 2, 0, false);
            declareFunction("sksi_add_statement_to_used_statements_set", "SKSI-ADD-STATEMENT-TO-USED-STATEMENTS-SET", 3, 0, false);
            declareFunction("sksi_last_cache_access_time", "SKSI-LAST-CACHE-ACCESS-TIME", 2, 0, false);
            declareFunction("sksi_generate_cache_key_from_access_path", "SKSI-GENERATE-CACHE-KEY-FROM-ACCESS-PATH", 1, 0, false);
            declareFunction("get_sql_connection_and_statement_from_global_cache", "GET-SQL-CONNECTION-AND-STATEMENT-FROM-GLOBAL-CACHE", 1, 0, false);
            declareFunction("release_sql_connection_and_statement_to_global_cache", "RELEASE-SQL-CONNECTION-AND-STATEMENT-TO-GLOBAL-CACHE", 2, 0, false);
            declareFunction("sksi_resourced_connection_has_active_statementsP", "SKSI-RESOURCED-CONNECTION-HAS-ACTIVE-STATEMENTS?", 1, 0, false);
            declareFunction("sksi_resourced_connection_idle_too_longP", "SKSI-RESOURCED-CONNECTION-IDLE-TOO-LONG?", 1, 0, false);
            declareFunction("sksi_initialize_global_resourcing", "SKSI-INITIALIZE-GLOBAL-RESOURCING", 0, 2, false);
            declareFunction("sksi_finalize_global_resourcing", "SKSI-FINALIZE-GLOBAL-RESOURCING", 0, 0, false);
            declareFunction("sksi_close_resourced_connections", "SKSI-CLOSE-RESOURCED-CONNECTIONS", 0, 0, false);
            declareFunction("sksi_close_resourced_connections_for_sks", "SKSI-CLOSE-RESOURCED-CONNECTIONS-FOR-SKS", 1, 0, false);
            declareFunction("sksi_reset_global_resourcing_max_idle_time", "SKSI-RESET-GLOBAL-RESOURCING-MAX-IDLE-TIME", 1, 0, false);
            declareFunction("sksi_possibly_reap_connections", "SKSI-POSSIBLY-REAP-CONNECTIONS", 0, 1, false);
            declareFunction("sksi_reap_connection_for_access_path", "SKSI-REAP-CONNECTION-FOR-ACCESS-PATH", 1, 0, false);
            declareFunction("sksi_possibly_remove_from_resourcing_cacheP", "SKSI-POSSIBLY-REMOVE-FROM-RESOURCING-CACHE?", 1, 0, false);
            declareFunction("sksi_reap_connection", "SKSI-REAP-CONNECTION", 1, 0, false);
            declareFunction("sk_source_status_fast", "SK-SOURCE-STATUS-FAST", 1, 1, false);
            declareFunction("get_rs_iterator_for_sql_query_from_db_sks", "GET-RS-ITERATOR-FOR-SQL-QUERY-FROM-DB-SKS", 2, 1, false);
            declareFunction("sksi_execute_db_sks_update", "SKSI-EXECUTE-DB-SKS-UPDATE", 2, 1, false);
            declareFunction("sksi_execute_db_sks_update_in_mapping_mt", "SKSI-EXECUTE-DB-SKS-UPDATE-IN-MAPPING-MT", 2, 0, false);
            declareFunction("sksi_execute_csql", "SKSI-EXECUTE-CSQL", 2, 1, false);
            declareFunction("sksi_execute_csql_in_mapping_mt", "SKSI-EXECUTE-CSQL-IN-MAPPING-MT", 2, 0, false);
            declareFunction("sksi_tactic_p", "SKSI-TACTIC-P", 1, 0, false);
            declareFunction("inference_possibly_note_salient_sksi_query_string", "INFERENCE-POSSIBLY-NOTE-SALIENT-SKSI-QUERY-STRING", 1, 0, false);
            declareFunction("memoized_salient_sksi_query_string_internal", "MEMOIZED-SALIENT-SKSI-QUERY-STRING-INTERNAL", 1, 0, false);
            declareFunction("memoized_salient_sksi_query_string", "MEMOIZED-SALIENT-SKSI-QUERY-STRING", 1, 0, false);
            declareFunction("sksi_tactic_salient_query_string", "SKSI-TACTIC-SALIENT-QUERY-STRING", 1, 0, false);
            declareFunction("sksi_tactic_note_salient_query_string", "SKSI-TACTIC-NOTE-SALIENT-QUERY-STRING", 2, 0, false);
        }
        if (SubLFiles.USE_V2) {
            declareFunction("get_result_set_from_sparql_source", "GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 0, false);
            declareFunction("get_result_set_from_sparql_source_guts", "GET-RESULT-SET-FROM-SPARQL-SOURCE-GUTS", 2, 0, false);
            declareFunction("remove_cached_get_result_set_from_sparql_source", "REMOVE-CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 0, false);
            declareFunction("cached_get_result_set_from_sparql_source_internal", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-INTERNAL", 2, 0, false);
            declareFunction("cached_get_result_set_from_sparql_source", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 0, false);
            declareFunction("sksi_sks_error", "SKSI-SKS-ERROR", 2, 1, false);
            declareFunction("sksi_sks_execution_error", "SKSI-SKS-EXECUTION-ERROR", 3, 0, false);
            declareFunction("sksi_possibly_reap_connections", "SKSI-POSSIBLY-REAP-CONNECTIONS", 0, 0, false);
        }
        return NIL;
    }

    public static SubLObject declare_sksi_sks_interaction_file_Previous() {
        declareFunction("generate_iterator_from_csql", "GENERATE-ITERATOR-FROM-CSQL", 2, 4, false);
        declareFunction("generate_boolean_from_csql", "GENERATE-BOOLEAN-FROM-CSQL", 2, 1, false);
        declareFunction("boolean_csql_to_sksi_supports", "BOOLEAN-CSQL-TO-SKSI-SUPPORTS", 3, 0, false);
        declareFunction("generate_db_iterator_from_csql", "GENERATE-DB-ITERATOR-FROM-CSQL", 2, 3, false);
        declareFunction("generate_db_boolean_from_csql", "GENERATE-DB-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_sdbc_result_set_from_csql", "GENERATE-SDBC-RESULT-SET-FROM-CSQL", 2, 1, false);
        declareFunction("generate_web_page_iterator_from_csql", "GENERATE-WEB-PAGE-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_web_page_boolean_from_csql", "GENERATE-WEB-PAGE-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_web_page_result_set", "GENERATE-WEB-PAGE-RESULT-SET", 2, 1, false);
        declareFunction("sksi_web_query_template_to_query", "SKSI-WEB-QUERY-TEMPLATE-TO-QUERY", 3, 0, false);
        declareFunction("sksi_web_query_patterns", "SKSI-WEB-QUERY-PATTERNS", 3, 0, false);
        declareFunction("sksi_web_query_where_pattern", "SKSI-WEB-QUERY-WHERE-PATTERN", 2, 0, false);
        declareFunction("sksi_web_query_field_positions", "SKSI-WEB-QUERY-FIELD-POSITIONS", 2, 0, false);
        declareFunction("sksi_web_page_result_set", "SKSI-WEB-PAGE-RESULT-SET", 2, 2, false);
        declareFunction("sksi_web_page_result_set_for_unbound_only", "SKSI-WEB-PAGE-RESULT-SET-FOR-UNBOUND-ONLY", 4, 0, false);
        declareFunction("test_sksi_web_page_result_set", "TEST-SKSI-WEB-PAGE-RESULT-SET", 3, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_memoized_internal", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-MEMOIZED-INTERNAL", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_memoized", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-MEMOIZED", 2, 0, false);
        declareFunction("clear_get_results_from_sksi_web_page_subl_method_cached", "CLEAR-GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 0, 0, false);
        declareFunction("remove_get_results_from_sksi_web_page_subl_method_cached", "REMOVE-GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_cached_internal", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED-INTERNAL", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_cached", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_none", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-NONE", 2, 0, false);
        declareFunction("get_results_from_sksi_web_page_subl_method_int", "GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-INT", 2, 0, false);
        declareFunction("sksi_tokenized_http_request_internal", "SKSI-TOKENIZED-HTTP-REQUEST-INTERNAL", 3, 4, false);
        declareFunction("sksi_tokenized_http_request", "SKSI-TOKENIZED-HTTP-REQUEST", 3, 4, false);
        declareFunction("get_results_from_subl_parsing_function", "GET-RESULTS-FROM-SUBL-PARSING-FUNCTION", 2, 0, false);
        declareFunction("sksi_subl_parsing_program_p", "SKSI-SUBL-PARSING-PROGRAM-P", 1, 0, false);
        declareFunction("sksi_filter_result_set", "SKSI-FILTER-RESULT-SET", 3, 0, false);
        declareFunction("compute_selected_tuple", "COMPUTE-SELECTED-TUPLE", 2, 0, false);
        declareFunction("generate_kb_iterator_from_csql", "GENERATE-KB-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_kb_boolean_from_csql", "GENERATE-KB-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_fht_iterator_from_csql", "GENERATE-FHT-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_fht_boolean_from_csql", "GENERATE-FHT-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_cache_iterator_from_csql", "GENERATE-CACHE-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_cache_boolean_from_csql", "GENERATE-CACHE-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_rdf_iterator_from_csql", "GENERATE-RDF-ITERATOR-FROM-CSQL", 2, 0, false);
        declareFunction("generate_rdf_boolean_from_csql", "GENERATE-RDF-BOOLEAN-FROM-CSQL", 2, 0, false);
        declareFunction("generate_sparql_result_set_from_csql", "GENERATE-SPARQL-RESULT-SET-FROM-CSQL", 2, 0, false);
        declareFunction("new_sparql_result_set_iterator", "NEW-SPARQL-RESULT-SET-ITERATOR", 2, 1, false);
        declareFunction("make_sparql_result_set_iterator_state", "MAKE-SPARQL-RESULT-SET-ITERATOR-STATE", 3, 0, false);
        declareFunction("sparql_result_set_iterator_done_p", "SPARQL-RESULT-SET-ITERATOR-DONE-P", 1, 0, false);
        declareFunction("sparql_result_set_iterator_next", "SPARQL-RESULT-SET-ITERATOR-NEXT", 1, 0, false);
        declareMacro("with_sksi_result_set_from_execute_query", "WITH-SKSI-RESULT-SET-FROM-EXECUTE-QUERY");
        declareFunction("sksi_execute_sqls_query", "SKSI-EXECUTE-SQLS-QUERY", 2, 0, false);
        declareFunction("sksi_execute_sqls_update", "SKSI-EXECUTE-SQLS-UPDATE", 2, 0, false);
        declareFunction("sksi_execute_batch_sql_updates", "SKSI-EXECUTE-BATCH-SQL-UPDATES", 1, 0, false);
        declareFunction("sksi_add_batch_sql_update", "SKSI-ADD-BATCH-SQL-UPDATE", 2, 0, false);
        declareFunction("sksi_execute_sql_query", "SKSI-EXECUTE-SQL-QUERY", 2, 0, false);
        declareFunction("sksi_execute_sql_query_int", "SKSI-EXECUTE-SQL-QUERY-INT", 4, 0, false);
        declareFunction("sksi_abort_and_release_sql_statement_and_connection", "SKSI-ABORT-AND-RELEASE-SQL-STATEMENT-AND-CONNECTION", 2, 0, false);
        declareFunction("sksi_execute_sql_update", "SKSI-EXECUTE-SQL-UPDATE", 2, 0, false);
        declareFunction("sksi_execute_sql_update_int", "SKSI-EXECUTE-SQL-UPDATE-INT", 4, 0, false);
        declareFunction("sksi_batch_execute_sql_statements", "SKSI-BATCH-EXECUTE-SQL-STATEMENTS", 3, 2, false);
        declareFunction("get_n_largest_auto_generated_keys", "GET-N-LARGEST-AUTO-GENERATED-KEYS", 5, 0, false);
        declareFunction("sksi_execute_sql_script_from_file", "SKSI-EXECUTE-SQL-SCRIPT-FROM-FILE", 2, 0, false);
        declareFunction("sksi_execute_sql_script", "SKSI-EXECUTE-SQL-SCRIPT", 2, 0, false);
        declareFunction("open_sql_source", "OPEN-SQL-SOURCE", 1, 2, false);
        declareFunction("sksi_open_sql_connection_and_statement", "SKSI-OPEN-SQL-CONNECTION-AND-STATEMENT", 1, 0, false);
        declareFunction("sksi_abort_sql_statement_and_connection", "SKSI-ABORT-SQL-STATEMENT-AND-CONNECTION", 2, 1, false);
        declareFunction("sksi_abort_sql_statement", "SKSI-ABORT-SQL-STATEMENT", 1, 0, false);
        declareFunction("sksi_close_sql_statement_and_connection", "SKSI-CLOSE-SQL-STATEMENT-AND-CONNECTION", 2, 0, false);
        declareFunction("sksi_close_sql_connection", "SKSI-CLOSE-SQL-CONNECTION", 1, 0, false);
        declareFunction("get_result_iterator_from_fht_source", "GET-RESULT-ITERATOR-FROM-FHT-SOURCE", 2, 0, false);
        declareFunction("get_selected_field_type_from_csql", "GET-SELECTED-FIELD-TYPE-FROM-CSQL", 1, 0, false);
        declareFunction("get_result_for_key_from_fht_source", "GET-RESULT-FOR-KEY-FROM-FHT-SOURCE", 2, 0, false);
        declareFunction("get_result_for_key_value_from_fht_source", "GET-RESULT-FOR-KEY-VALUE-FROM-FHT-SOURCE", 2, 0, false);
        declareFunction("put_key_value_under_key_in_fht_source", "PUT-KEY-VALUE-UNDER-KEY-IN-FHT-SOURCE", 3, 0, false);
        declareFunction("remove_key_value_under_key_in_fht_source", "REMOVE-KEY-VALUE-UNDER-KEY-IN-FHT-SOURCE", 3, 0, false);
        declareFunction("create_fht_source", "CREATE-FHT-SOURCE", 1, 0, false);
        declareFunction("open_fht_source", "OPEN-FHT-SOURCE", 1, 0, false);
        declareFunction("finalize_fht_source", "FINALIZE-FHT-SOURCE", 1, 0, false);
        declareFunction("cache_sql_connection_statement_for_result_set", "CACHE-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 3, 0, false);
        declareFunction("uncache_sql_connection_statement_for_result_set", "UNCACHE-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 1, 0, false);
        declareFunction("get_sql_connection_statement_for_result_set", "GET-SQL-CONNECTION-STATEMENT-FOR-RESULT-SET", 1, 0, false);
        declareFunction("clear_cached_get_result_set_from_sql_source", "CLEAR-CACHED-GET-RESULT-SET-FROM-SQL-SOURCE", 0, 0, false);
        declareFunction("remove_cached_get_result_set_from_sql_source", "REMOVE-CACHED-GET-RESULT-SET-FROM-SQL-SOURCE", 2, 0, false);
        declareFunction("cached_get_result_set_from_sql_source_internal", "CACHED-GET-RESULT-SET-FROM-SQL-SOURCE-INTERNAL", 2, 0, false);
        declareFunction("cached_get_result_set_from_sql_source", "CACHED-GET-RESULT-SET-FROM-SQL-SOURCE", 2, 0, false);
        declareFunction("get_result_set_from_sql_source", "GET-RESULT-SET-FROM-SQL-SOURCE", 2, 0, false);
        declareFunction("get_result_set_from_sparql_source", "GET-RESULT-SET-FROM-SPARQL-SOURCE", 2, 2, false);
        declareFunction("get_result_set_from_sparql_source_guts", "GET-RESULT-SET-FROM-SPARQL-SOURCE-GUTS", 4, 0, false);
        declareFunction("sparql_oracle_subprotocolP", "SPARQL-ORACLE-SUBPROTOCOL?", 1, 0, false);
        declareFunction("sparql_request_query_ticket", "SPARQL-REQUEST-QUERY-TICKET", 1, 0, false);
        declareFunction("sparql_triclops_request_query_ticket", "SPARQL-TRICLOPS-REQUEST-QUERY-TICKET", 1, 0, false);
        declareFunction("sparql_oracle_joseki_request_query_ticket", "SPARQL-ORACLE-JOSEKI-REQUEST-QUERY-TICKET", 0, 1, false);
        declareFunction("sparql_oracle_sesame_request_query_ticket", "SPARQL-ORACLE-SESAME-REQUEST-QUERY-TICKET", 0, 1, false);
        declareFunction("preprocess_sparql_for_backend", "PREPROCESS-SPARQL-FOR-BACKEND", 2, 1, false);
        declareFunction("compute_sparql_oracle_prefix_pragma", "COMPUTE-SPARQL-ORACLE-PREFIX-PRAGMA", 1, 0, false);
        declareFunction("compute_sparql_oracle_query_timeout", "COMPUTE-SPARQL-ORACLE-QUERY-TIMEOUT", 0, 0, false);
        declareFunction("close_sparql_result_stream", "CLOSE-SPARQL-RESULT-STREAM", 1, 0, false);
        declareFunction("sparql_abort_query", "SPARQL-ABORT-QUERY", 2, 0, false);
        declareFunction("test_sparql_abort_query", "TEST-SPARQL-ABORT-QUERY", 2, 0, false);
        declareFunction("clear_cached_get_result_set_from_sparql_source", "CLEAR-CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 0, 0, false);
        declareFunction("remove_cached_get_result_set_from_sparql_source", "REMOVE-CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 4, 0, false);
        declareFunction("cached_get_result_set_from_sparql_source_internal", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-INTERNAL", 4, 0, false);
        declareFunction("cached_get_result_set_from_sparql_source", "CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE", 4, 0, false);
        declareFunction("save_sparql_result_set_cache", "SAVE-SPARQL-RESULT-SET-CACHE", 1, 0, false);
        declareFunction("load_sparql_result_set_cache", "LOAD-SPARQL-RESULT-SET-CACHE", 1, 0, false);
        declareFunction("sparql_extract_variable_names", "SPARQL-EXTRACT-VARIABLE-NAMES", 1, 0, false);
        declareFunction("sparql_query_xml_tokens", "SPARQL-QUERY-XML-TOKENS", 1, 1, false);
        declareFunction("open_sparql_query_result_stream", "OPEN-SPARQL-QUERY-RESULT-STREAM", 1, 2, false);
        declareFunction("stream_wait_until_read_char", "STREAM-WAIT-UNTIL-READ-CHAR", 1, 0, false);
        declareFunction("sparql_test", "SPARQL-TEST", 1, 2, false);
        declareFunction("sparql_test_result_set", "SPARQL-TEST-RESULT-SET", 1, 1, false);
        declareFunction("sparql_extract_booleanP", "SPARQL-EXTRACT-BOOLEAN?", 1, 0, false);
        declareFunction("sparql_extract_rdf_prefix_map", "SPARQL-EXTRACT-RDF-PREFIX-MAP", 1, 0, false);
        declareFunction("sparql_extract_rdf_base", "SPARQL-EXTRACT-RDF-BASE", 1, 0, false);
        declareFunction("sparql_prefix_map_to_minimal_access_path", "SPARQL-PREFIX-MAP-TO-MINIMAL-ACCESS-PATH", 2, 0, false);
        declareFunction("sksi_sparql_xml_tokens_to_result_set", "SKSI-SPARQL-XML-TOKENS-TO-RESULT-SET", 3, 0, false);
        declareFunction("test_sksi_ist_graph", "TEST-SKSI-IST-GRAPH", 3, 0, false);
        declareFunction("sksi_unreachable_db_sks_error", "SKSI-UNREACHABLE-DB-SKS-ERROR", 2, 0, false);
        declareFunction("sksi_db_execution_error", "SKSI-DB-EXECUTION-ERROR", 3, 0, false);
        declareFunction("sksi_unreachable_fht_sks_error", "SKSI-UNREACHABLE-FHT-SKS-ERROR", 2, 0, false);
        declareFunction("sksi_create_fht_sks_error", "SKSI-CREATE-FHT-SKS-ERROR", 2, 0, false);
        declareFunction("all_structured_knowledge_sources_assumed_availableP", "ALL-STRUCTURED-KNOWLEDGE-SOURCES-ASSUMED-AVAILABLE?", 0, 0, false);
        declareFunction("note_sks_available", "NOTE-SKS-AVAILABLE", 1, 0, false);
        declareFunction("unnote_sks_available", "UNNOTE-SKS-AVAILABLE", 1, 0, false);
        declareFunction("sks_assumed_availableP", "SKS-ASSUMED-AVAILABLE?", 1, 0, false);
        declareFunction("sksi_sks_error", "SKSI-SKS-ERROR", 2, 2, false);
        declareFunction("sksi_unreachable_sks_error", "SKSI-UNREACHABLE-SKS-ERROR", 2, 0, false);
        declareFunction("sksi_sks_execution_error", "SKSI-SKS-EXECUTION-ERROR", 4, 0, false);
        declareFunction("get_sql_connection_and_statement", "GET-SQL-CONNECTION-AND-STATEMENT", 1, 0, false);
        declareFunction("release_sql_connection_and_statement", "RELEASE-SQL-CONNECTION-AND-STATEMENT", 2, 0, false);
        declareFunction("within_sksi_sql_connection_resourcingP", "WITHIN-SKSI-SQL-CONNECTION-RESOURCING?", 0, 0, false);
        declareFunction("within_sksi_local_resourcingP", "WITHIN-SKSI-LOCAL-RESOURCING?", 0, 0, false);
        declareFunction("get_sql_connection_and_statement_from_local_cache", "GET-SQL-CONNECTION-AND-STATEMENT-FROM-LOCAL-CACHE", 1, 0, false);
        declareFunction("get_sql_connection_from_local_cache", "GET-SQL-CONNECTION-FROM-LOCAL-CACHE", 1, 0, false);
        declareFunction("get_sql_statement_from_local_cache", "GET-SQL-STATEMENT-FROM-LOCAL-CACHE", 1, 0, false);
        declareFunction("release_sql_connection_and_statement_to_local_cache", "RELEASE-SQL-CONNECTION-AND-STATEMENT-TO-LOCAL-CACHE", 2, 0, false);
        declareFunction("finalize_sql_connection_and_statement_cache", "FINALIZE-SQL-CONNECTION-AND-STATEMENT-CACHE", 0, 0, false);
        declareFunction("within_sksi_global_resourcingP", "WITHIN-SKSI-GLOBAL-RESOURCING?", 0, 0, false);
        declareFunction("sksi_global_resourcing_initializedP", "SKSI-GLOBAL-RESOURCING-INITIALIZED?", 0, 0, false);
        declareFunction("sksi_resourcing_cache_print_function_trampoline", "SKSI-RESOURCING-CACHE-PRINT-FUNCTION-TRAMPOLINE", 2, 0, false);
        declareFunction("sksi_resourcing_cache_p", "SKSI-RESOURCING-CACHE-P", 1, 0, false);
        new sksi_sks_interaction.$sksi_resourcing_cache_p$UnaryFunction();
        declareFunction("sksi_resourcing_cache_connections", "SKSI-RESOURCING-CACHE-CONNECTIONS", 1, 0, false);
        declareFunction("sksi_resourcing_cache_statements", "SKSI-RESOURCING-CACHE-STATEMENTS", 1, 0, false);
        declareFunction("sksi_resourcing_cache_used_statements", "SKSI-RESOURCING-CACHE-USED-STATEMENTS", 1, 0, false);
        declareFunction("sksi_resourcing_cache_access_times", "SKSI-RESOURCING-CACHE-ACCESS-TIMES", 1, 0, false);
        declareFunction("sksi_resourcing_cache_lock", "SKSI-RESOURCING-CACHE-LOCK", 1, 0, false);
        declareFunction("sksi_resourcing_cache_max_pool_size", "SKSI-RESOURCING-CACHE-MAX-POOL-SIZE", 1, 0, false);
        declareFunction("sksi_resourcing_cache_max_idle", "SKSI-RESOURCING-CACHE-MAX-IDLE", 1, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_connections", "_CSETF-SKSI-RESOURCING-CACHE-CONNECTIONS", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_statements", "_CSETF-SKSI-RESOURCING-CACHE-STATEMENTS", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_used_statements", "_CSETF-SKSI-RESOURCING-CACHE-USED-STATEMENTS", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_access_times", "_CSETF-SKSI-RESOURCING-CACHE-ACCESS-TIMES", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_lock", "_CSETF-SKSI-RESOURCING-CACHE-LOCK", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_max_pool_size", "_CSETF-SKSI-RESOURCING-CACHE-MAX-POOL-SIZE", 2, 0, false);
        declareFunction("_csetf_sksi_resourcing_cache_max_idle", "_CSETF-SKSI-RESOURCING-CACHE-MAX-IDLE", 2, 0, false);
        declareFunction("make_sksi_resourcing_cache", "MAKE-SKSI-RESOURCING-CACHE", 0, 1, false);
        declareFunction("visit_defstruct_sksi_resourcing_cache", "VISIT-DEFSTRUCT-SKSI-RESOURCING-CACHE", 2, 0, false);
        declareFunction("visit_defstruct_object_sksi_resourcing_cache_method", "VISIT-DEFSTRUCT-OBJECT-SKSI-RESOURCING-CACHE-METHOD", 2, 0, false);
        declareFunction("new_sksi_resourcing_cache", "NEW-SKSI-RESOURCING-CACHE", 0, 2, false);
        declareMacro("with_sksi_global_resourcing_lock_held", "WITH-SKSI-GLOBAL-RESOURCING-LOCK-HELD");
        declareFunction("sksi_add_connection_to_cache", "SKSI-ADD-CONNECTION-TO-CACHE", 3, 0, false);
        declareFunction("sksi_add_statement_to_cache", "SKSI-ADD-STATEMENT-TO-CACHE", 3, 0, false);
        declareFunction("sksi_note_access_time_in_cache", "SKSI-NOTE-ACCESS-TIME-IN-CACHE", 2, 0, false);
        declareFunction("sksi_retrieve_connection_from_cache", "SKSI-RETRIEVE-CONNECTION-FROM-CACHE", 2, 0, false);
        declareFunction("sksi_retrieve_statement_from_cache", "SKSI-RETRIEVE-STATEMENT-FROM-CACHE", 2, 0, false);
        declareFunction("sksi_add_statement_to_used_statements_set", "SKSI-ADD-STATEMENT-TO-USED-STATEMENTS-SET", 3, 0, false);
        declareFunction("sksi_last_cache_access_time", "SKSI-LAST-CACHE-ACCESS-TIME", 2, 0, false);
        declareFunction("sksi_generate_cache_key_from_access_path", "SKSI-GENERATE-CACHE-KEY-FROM-ACCESS-PATH", 1, 0, false);
        declareFunction("get_sql_connection_and_statement_from_global_cache", "GET-SQL-CONNECTION-AND-STATEMENT-FROM-GLOBAL-CACHE", 1, 0, false);
        declareFunction("release_sql_connection_and_statement_to_global_cache", "RELEASE-SQL-CONNECTION-AND-STATEMENT-TO-GLOBAL-CACHE", 2, 0, false);
        declareFunction("sksi_resourced_connection_has_active_statementsP", "SKSI-RESOURCED-CONNECTION-HAS-ACTIVE-STATEMENTS?", 1, 0, false);
        declareFunction("sksi_resourced_connection_idle_too_longP", "SKSI-RESOURCED-CONNECTION-IDLE-TOO-LONG?", 1, 0, false);
        declareFunction("sksi_initialize_global_resourcing", "SKSI-INITIALIZE-GLOBAL-RESOURCING", 0, 2, false);
        declareFunction("sksi_finalize_global_resourcing", "SKSI-FINALIZE-GLOBAL-RESOURCING", 0, 0, false);
        declareFunction("sksi_close_resourced_connections", "SKSI-CLOSE-RESOURCED-CONNECTIONS", 0, 0, false);
        declareFunction("sksi_close_resourced_connections_for_sks", "SKSI-CLOSE-RESOURCED-CONNECTIONS-FOR-SKS", 1, 0, false);
        declareFunction("sksi_reset_global_resourcing_max_idle_time", "SKSI-RESET-GLOBAL-RESOURCING-MAX-IDLE-TIME", 1, 0, false);
        declareFunction("sksi_possibly_reap_connections", "SKSI-POSSIBLY-REAP-CONNECTIONS", 0, 1, false);
        declareFunction("sksi_reap_connection_for_access_path", "SKSI-REAP-CONNECTION-FOR-ACCESS-PATH", 1, 0, false);
        declareFunction("sksi_possibly_remove_from_resourcing_cacheP", "SKSI-POSSIBLY-REMOVE-FROM-RESOURCING-CACHE?", 1, 0, false);
        declareFunction("sksi_reap_connection", "SKSI-REAP-CONNECTION", 1, 0, false);
        declareFunction("sk_source_status_fast", "SK-SOURCE-STATUS-FAST", 1, 1, false);
        declareFunction("get_rs_iterator_for_sql_query_from_db_sks", "GET-RS-ITERATOR-FOR-SQL-QUERY-FROM-DB-SKS", 2, 1, false);
        declareFunction("sksi_execute_db_sks_update", "SKSI-EXECUTE-DB-SKS-UPDATE", 2, 1, false);
        declareFunction("sksi_execute_db_sks_update_in_mapping_mt", "SKSI-EXECUTE-DB-SKS-UPDATE-IN-MAPPING-MT", 2, 0, false);
        declareFunction("sksi_execute_csql", "SKSI-EXECUTE-CSQL", 2, 1, false);
        declareFunction("sksi_execute_csql_in_mapping_mt", "SKSI-EXECUTE-CSQL-IN-MAPPING-MT", 2, 0, false);
        declareFunction("sksi_tactic_p", "SKSI-TACTIC-P", 1, 0, false);
        declareFunction("inference_possibly_note_salient_sksi_query_string", "INFERENCE-POSSIBLY-NOTE-SALIENT-SKSI-QUERY-STRING", 1, 0, false);
        declareFunction("memoized_salient_sksi_query_string_internal", "MEMOIZED-SALIENT-SKSI-QUERY-STRING-INTERNAL", 1, 0, false);
        declareFunction("memoized_salient_sksi_query_string", "MEMOIZED-SALIENT-SKSI-QUERY-STRING", 1, 0, false);
        declareFunction("sksi_tactic_salient_query_string", "SKSI-TACTIC-SALIENT-QUERY-STRING", 1, 0, false);
        declareFunction("sksi_tactic_note_salient_query_string", "SKSI-TACTIC-NOTE-SALIENT-QUERY-STRING", 2, 0, false);
        return NIL;
    }

    static private final SubLList $list_alt7 = list(makeSymbol("SELECT-KEYWORD"), makeSymbol("FIELDS"), makeSymbol("TABLES"), makeSymbol("&OPTIONAL"), makeSymbol("WHERE"));

    static private final SubLList $list_alt8 = list(makeSymbol("WHERE-KEYWORD"), makeSymbol("WHERE-CLAUSES"));

    static private final SubLString $str_alt10$malformed_csql_subexpression__a__ = makeString("malformed csql subexpression ~a, expected :where");

    static private final SubLList $list_alt19 = list(makeSymbol("SELECT-KEYWORD"), makeSymbol("CSQL-FIELDS"), makeSymbol("TABLES"), makeSymbol("&OPTIONAL"), makeSymbol("WHERE"));

    static private final SubLList $list_alt20 = list(makeSymbol("&OPTIONAL"), makeSymbol("WHERE-KEYWORD"), makeSymbol("WHERE-CLAUSES"));

    static private final SubLList $list_alt22 = list(makeSymbol("WHERE-OPERATOR"), list(makeSymbol("FIELD-TYPE"), makeSymbol("FIELD-NAME"), makeSymbol("FIELD-TABLE")), makeSymbol("VALUE"));

    static private final SubLString $str_alt24$time_to_support__S_operator = makeString("time to support ~S operator");

    static private final SubLString $str_alt25$No_default_value_provided_for__A = makeString("No default value provided for ~A");

    static private final SubLList $list_alt27 = list(makeSymbol("WHERE-OPERATOR"), list(makeSymbol("WHERE-FIELD-TYPE"), makeSymbol("WHERE-FIELD-NAME"), makeSymbol("WHERE-TABLE")), makeSymbol("VALUE"));

    static private final SubLList $list_alt30 = list(makeSymbol("FIELD-KEYWORD"), makeSymbol("FIELD-NAME"), makeSymbol("&OPTIONAL"), makeSymbol("TABLE"));

    static private final SubLString $str_alt35$Parsing_method__S_is_not_yet_supp = makeString("Parsing method ~S is not yet supported");

    static private final SubLString $str_alt38$_S_not_a_valid_value_for__sksi_ht = makeString("~S not a valid value for *sksi-http-request-caching-strategy*");

    public static final SubLSymbol $kw40$_MEMOIZED_ITEM_NOT_FOUND_ = makeKeyword("&MEMOIZED-ITEM-NOT-FOUND&");

    static private final SubLList $list_alt43 = list(makeSymbol("SERVER"), makeSymbol("PORT"), makeSymbol("METHOD"), makeSymbol("URL"), makeSymbol("TIMEOUT"), makeSymbol("QUERY"));

    static private final SubLString $str_alt48$http___ = makeString("http://");

    static private final SubLString $str_alt49$_ = makeString("?");

    static private final SubLString $str_alt53$Opening_the_TCP_connection_timed_ = makeString("Opening the TCP connection timed out.");

    static private final SubLString $str_alt55$The_HTTP_request_timed_out_ = makeString("The HTTP request timed out.");

    static private final SubLList $list_alt57 = list(makeSymbol("EXPAND-SUBL"), list(makeSymbol("TOKENS-VAR")), makeSymbol("PROGRAM-BODY"));

    static private final SubLList $list_alt62 = list(makeSymbol("SELECT-KEYWORD"), makeSymbol("FIELDS"), makeSymbol("FROM"), makeSymbol("&OPTIONAL"), makeSymbol("WHERE"));

    static private final SubLString $str_alt64$malformed_csql_expression__a = makeString("malformed csql expression ~a");

    static private final SubLList $list_alt65 = list(makeKeyword("TABLE"), makeString("Language_Spoken"));

    static private final SubLString $str_alt66$unknown_cache_for_csql__a_and_acc = makeString("unknown cache for csql ~a and access path ~a");

    static private final SubLString $str_alt67$___A____ = makeString("~&~A~%~%");

    static private final SubLList $list_alt68 = list(NIL);

    static private final SubLList $list_alt71 = list(makeSymbol("PENDING-RESULTS"), makeSymbol("PENDING-LIMITS"), makeSymbol("&REST"), makeSymbol("REST"));

    static private final SubLList $list_alt72 = list(makeSymbol("PENDING-RESULTS"), makeSymbol("PENDING-LIMITS"), makeSymbol("PROCESSED-RESULTS"), makeSymbol("CSQL"), makeSymbol("ACCESS-PATH"));

    static private final SubLList $list_alt73 = list(list(makeSymbol("RESULT-SET"), makeSymbol("SQL-STRING"), makeSymbol("ACCESS-PATH")), makeSymbol("&BODY"), makeSymbol("BODY"));

    static private final SubLString $str_alt81$Problem_executing_query___a_ = makeString("Problem executing query: ~a.");

    static private final SubLString $str_alt85$SKSI_ = makeString("SKSI ");

    static private final SubLString $str_alt86$Executing_SQL_____Source___S__ = makeString("Executing SQL ~%  Source: ~S~%");

    static private final SubLString $str_alt87$__ = makeString("~%");

    static private final SubLString $str_alt88$Module___S__ = makeString("Module: ~S~%");

    static private final SubLString $str_alt89$Query____S__ = makeString("Query:  ~S~%");

    static private final SubLString $str_alt90$Query_Result___S__ = makeString("Query Result: ~S~%");

    static private final SubLString $str_alt91$Executing_SQL_____Source___S____U = makeString("Executing SQL ~%  Source: ~S~%  Update:~% ~S~%");

    static private final SubLString $str_alt92$SQL_Update_Result___S__ = makeString("SQL Update Result: ~S~%");

    static private final SubLString $str_alt93$Batch_executing__s_SQL_statements = makeString("Batch executing ~s SQL statements.~% Source: ~s.~%");

    static private final SubLString $str_alt101$LOCK_TABLE_ = makeString("LOCK TABLE ");

    static private final SubLString $str_alt102$_IN_EXCLUSIVE_MODE = makeString(" IN EXCLUSIVE MODE");

    static private final SubLString $str_alt103$_WRITE = makeString(" WRITE");

    static private final SubLString $str_alt105$Batch_execution_resulted_in_error = makeString("Batch execution resulted in error: ~s~%");

    static private final SubLString $str_alt106$Batch_execution_completed_success = makeString("Batch execution completed successfully.~%");

    static private final SubLString $str_alt107$SELECT_ = makeString("SELECT ");

    static private final SubLString $str_alt108$_FROM_ = makeString(" FROM ");

    static private final SubLString $str_alt109$_ORDER_BY_ = makeString(" ORDER BY ");

    static private final SubLString $str_alt110$_DESC_LIMIT_ = makeString(" DESC LIMIT ");

    static private final SubLString $str_alt111$SELECT___FROM__SELECT_ = makeString("SELECT * FROM (SELECT ");

    static private final SubLString $str_alt112$_DESC__WHERE_rownum_BETWEEN_ = makeString(" DESC) WHERE rownum BETWEEN ");

    static private final SubLString $str_alt113$_AND_ = makeString(" AND ");

    static private final SubLString $str_alt114$Auto_generated_key_retrieval_is_n = makeString("Auto-generated key retrieval is not yet supported for ~a");

    static private final SubLList $list_alt116 = list(CHAR_semicolon);

    static private final SubLList $list_alt125 = list(makeKeyword("COUNT"), makeKeyword("ALL"));

    static private final SubLString $str_alt129$The_file_already_exists_ = makeString("The file already exists.");

    static private final SubLString $str_alt133$_ticket = makeString("/ticket");

    static private final SubLString $str_alt134$type_id = makeString("type=id");

    static private final SubLList $list_alt135 = list(makeString("text/plan"), makeString("text/html"));

    static private final SubLString $str_alt136$_A = makeString("~A");

    static private final SubLString $str_alt137$_processes = makeString("/processes");

    static private final SubLString $str_alt138$ticket_ = makeString("ticket=");

    static private final SubLString $str_alt143$Needs_to_be_re_implemented_withou = makeString("Needs to be re-implemented without function caching macros.");

    static private final SubLList $list_alt146 = list(CHAR_space, CHAR_question);

    static private final SubLString $str_alt147$query_ = makeString("query=");

    static private final SubLString $str_alt148$jonny2_cyc_com = makeString("jonny2.cyc.com");

    static private final SubLString $str_alt150$_SemanticDB_SPARQL_ = makeString("/SemanticDB/SPARQL/");

    static private final SubLList $list_alt151 = list(makeString("application/sparql-results+xml"), makeString("application/xml"));

    static private final SubLString $str_alt152$sparql_query_xml_tokens_error___S = makeString("sparql-query-xml-tokens error (~S ~S)");

    static private final SubLString $str_alt153$_ticket_ = makeString("&ticket=");

    public static final SubLSymbol $kw159$KEEP_ALIVE_ = makeKeyword("KEEP-ALIVE?");

    public static final SubLSymbol $kw160$WIDE_NEWLINES_ = makeKeyword("WIDE-NEWLINES?");

    static private final SubLList $list_alt163 = list(cons(makeString("owl"), makeString("http://www.w3.org/2000/07/owl#")), cons(makeString("ptrec"), makeString("tag:info@semanticdb.ccf.org,2007:PatientRecordTerms#")), cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")), cons(makeString("xsd"), makeString("http://www.w3.org/2001/XMLSchema#")));

    public static final SubLObject init_sksi_sks_interaction_file_alt() {
        defparameter("*SKSI-HTTP-REQUEST-CACHING-STRATEGY*", $MEMOIZE);
        deflexical("*GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED-CACHING-STATE*", NIL);
        defparameter("*SKSI-HTTP-REQUEST-KEEP-ALIVE*", T);
        defparameter("*SKSI-HTTP-REQUEST-WIDE-NEWLINES*", T);
        defparameter("*SKSI-HTTP-REQUEST-OPEN-CONNECTION-TIMEOUT*", ONE_INTEGER);
        defparameter("*SKSI-HTTP-REQUEST-OVERALL-TIMEOUT*", $int$30);
        defparameter("*SKSI-HTTP-AUTOMATICALLY-REDIRECT*", T);
        defvar("*ORACLE-SPARQL-PROGRESS-STREAM*", NIL);
        defparameter("*SKSI-SPARQL-ITERATIVITY-LIMITS*", $list_alt68);
        deflexical("*SKSI-OPEN-SQL-CONNECTION-DEFAULT-TIMEOUT*", THREE_INTEGER);
        deflexical("*SKSI-FHT-SOURCE-DEFAULT-SIZE*", $int$32);
        deflexical("*SKSI-FHT-SOURCE-DEFAULT-AVERAGE-BUCKET-SIZE*", $int$256);
        deflexical("*RESULT-SET-SQL-CONNECTION-STATEMENT-CACHE*", NIL != boundp($result_set_sql_connection_statement_cache$) ? ((SubLObject) ($result_set_sql_connection_statement_cache$.getGlobalValue())) : dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
        defparameter("*SKSI-SPARQL-REQUEST-OPEN-CONNECTION-TIMEOUT*", FIVE_INTEGER);
        defparameter("*SKSI-SPARQL-REQUEST-OVERALL-TIMEOUT*", $int$3600);
        defvar("*QUERY-INTERRUPT-ON-SPARQL-EXECUTION*", NIL);
        defvar("*SPARQL-EVALUATION-ENABLED?*", T);
        defvar("*SPARQL-CACHING-ENABLED?*", NIL);
        defvar("*SPARQL-RESULT-STREAM-ITERATOR-ENABLED?*", NIL);
        deflexical("*CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-CACHING-STATE*", NIL);
        deflexical("*ACCESS-PATH*", NIL != boundp($access_path$) ? ((SubLObject) ($access_path$.getGlobalValue())) : NIL);
        deflexical("*STRUCTURED-KNOWLEDGE-SOURCES-ASSUMED-AVAILABLE*", NIL);
        deflexical("*SKSI-SQL-STATEMENT-POOL-MAX-SIZE*", TEN_INTEGER);
        deflexical("*SKSI-RESOURCING-CACHE*", NIL != boundp($sksi_resourcing_cache$) ? ((SubLObject) ($sksi_resourcing_cache$.getGlobalValue())) : NIL);
        defparameter("*SKSI-GLOBAL-RESOURCING?*", T);
        deflexical("*SKSI-DEFAULT-CONNECTION-MAX-IDLE-SECONDS*", $int$600);
        defconstant("*DTP-SKSI-RESOURCING-CACHE*", SKSI_RESOURCING_CACHE);
        return NIL;
    }

    public static SubLObject init_sksi_sks_interaction_file() {
        if (SubLFiles.USE_V1) {
            defparameter("*SKSI-HTTP-REQUEST-CACHING-STRATEGY*", $MEMOIZE);
            deflexical("*GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED-CACHING-STATE*", NIL);
            defparameter("*SKSI-HTTP-REQUEST-KEEP-ALIVE*", T);
            defparameter("*SKSI-HTTP-REQUEST-WIDE-NEWLINES*", T);
            defparameter("*SKSI-SPARQL-REQUEST-WIDE-NEWLINES*", T);
            defparameter("*SKSI-HTTP-REQUEST-OPEN-CONNECTION-TIMEOUT*", ONE_INTEGER);
            defparameter("*SKSI-HTTP-REQUEST-OVERALL-TIMEOUT*", sksi_sks_interaction.$int$30);
            defparameter("*SKSI-HTTP-AUTOMATICALLY-REDIRECT*", T);
            defvar("*ORACLE-SPARQL-PROGRESS-STREAM*", NIL);
            defparameter("*SKSI-SPARQL-ITERATIVITY-LIMITS*", sksi_sks_interaction.$list70);
            defparameter("*SKSI-OPEN-SQL-CONNECTION-DEFAULT-TIMEOUT*", THREE_INTEGER);
            defparameter("*SKSI-OPEN-WEB-CONNECTION-DEFAULT-TIMEOUT*", TEN_INTEGER);
            defparameter("*SKSI-OPEN-SQL-SOURCE-DEFAULT-MAX-TRIES*", TEN_INTEGER);
            defparameter("*SKSI-OPEN-SQL-SOURCE-DEFAULT-INTERVAL*", sksi_sks_interaction.$float$0_1);
            deflexical("*SKSI-FHT-SOURCE-DEFAULT-SIZE*", $int$32);
            deflexical("*SKSI-FHT-SOURCE-DEFAULT-AVERAGE-BUCKET-SIZE*", $int$256);
            deflexical("*RESULT-SET-SQL-CONNECTION-STATEMENT-CACHE*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$result_set_sql_connection_statement_cache$, sksi_sks_interaction.$result_set_sql_connection_statement_cache$, () -> dictionary_utilities.new_synchronized_dictionary(symbol_function(EQ), UNPROVIDED)));
            deflexical("*CACHED-GET-RESULT-SET-FROM-SQL-SOURCE-CACHING-STATE*", NIL);
            defparameter("*SKSI-SPARQL-REQUEST-OPEN-CONNECTION-TIMEOUT*", FIVE_INTEGER);
            defparameter("*SKSI-SPARQL-REQUEST-OVERALL-TIMEOUT*", sksi_sks_interaction.$int$3600);
            defvar("*QUERY-INTERRUPT-ON-SPARQL-EXECUTION*", NIL);
            defvar("*SPARQL-EVALUATION-ENABLED?*", T);
            defvar("*SPARQL-RESULT-STREAM-ITERATOR-ENABLED?*", T);
            defconstant("*SPARQL-ORACLE-JOSEKI-SUBPROTOCOL*", sksi_sks_interaction.$str140$oracle_joseki);
            defconstant("*SPARQL-ORACLE-SESAME-SUBPROTOCOL*", sksi_sks_interaction.$str141$oracle_sesame);
            defconstant("*SPARQL-TRICLOPS-SUBPROTOCOL*", sksi_sks_interaction.$$$triclops);
            defparameter("*SPARQL-ORACLE-DEFAULT-SERVER-PARALLELISM*", FOUR_INTEGER);
            deflexical("*SPARQL-ORACLE-FS-PREFIX-PRAGMA-TEMPLATE*", sksi_sks_interaction.$str149$http___oracle_com_semtech_ordered);
            deflexical("*SPARQL-ORACLE-HT-PREFIX-PRAGMA-TEMPLATE*", sksi_sks_interaction.$str150$http___oracle_com_semtech_ALL_PUL);
            defparameter("*SPARQL-ORACLE-TIMEOUT*", NIL);
            deflexical("*CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-CACHING-STATE*", NIL);
            deflexical("*ACCESS-PATH*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$access_path$, sksi_sks_interaction.$access_path$, NIL));
            deflexical("*STRUCTURED-KNOWLEDGE-SOURCES-ASSUMED-AVAILABLE*", NIL);
            deflexical("*SKSI-SQL-STATEMENT-POOL-MAX-SIZE*", TEN_INTEGER);
            deflexical("*SKSI-RESOURCING-CACHE*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$sksi_resourcing_cache$, sksi_sks_interaction.$sksi_resourcing_cache$, NIL));
            defparameter("*SKSI-GLOBAL-RESOURCING?*", T);
            deflexical("*SKSI-DEFAULT-CONNECTION-MAX-IDLE-SECONDS*", sksi_sks_interaction.$int$600);
            defconstant("*DTP-SKSI-RESOURCING-CACHE*", sksi_sks_interaction.SKSI_RESOURCING_CACHE);
            deflexical("*SKSI-CONNECTION-GLOBAL-CACHE-LOCK*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$sksi_connection_global_cache_lock$, sksi_sks_interaction.$sksi_connection_global_cache_lock$, () -> make_lock(Strings.string(gensym(sksi_sks_interaction.$str254$SKSI_SQL_global_connection_cache_)))));
            defparameter("*SKSI-SALIENT-QUERY-STRING*", NIL);
        }
        if (SubLFiles.USE_V2) {
            defparameter("*SKSI-SPARQL-ITERATIVITY-LIMITS*", $list_alt68);
            deflexical("*SKSI-OPEN-SQL-CONNECTION-DEFAULT-TIMEOUT*", THREE_INTEGER);
            deflexical("*RESULT-SET-SQL-CONNECTION-STATEMENT-CACHE*", NIL != boundp($result_set_sql_connection_statement_cache$) ? ((SubLObject) ($result_set_sql_connection_statement_cache$.getGlobalValue())) : dictionary.new_dictionary(symbol_function(EQ), UNPROVIDED));
            defvar("*SPARQL-CACHING-ENABLED?*", NIL);
            defvar("*SPARQL-RESULT-STREAM-ITERATOR-ENABLED?*", NIL);
            deflexical("*ACCESS-PATH*", NIL != boundp($access_path$) ? ((SubLObject) ($access_path$.getGlobalValue())) : NIL);
            deflexical("*SKSI-RESOURCING-CACHE*", NIL != boundp($sksi_resourcing_cache$) ? ((SubLObject) ($sksi_resourcing_cache$.getGlobalValue())) : NIL);
        }
        return NIL;
    }

    public static SubLObject init_sksi_sks_interaction_file_Previous() {
        defparameter("*SKSI-HTTP-REQUEST-CACHING-STRATEGY*", $MEMOIZE);
        deflexical("*GET-RESULTS-FROM-SKSI-WEB-PAGE-SUBL-METHOD-CACHED-CACHING-STATE*", NIL);
        defparameter("*SKSI-HTTP-REQUEST-KEEP-ALIVE*", T);
        defparameter("*SKSI-HTTP-REQUEST-WIDE-NEWLINES*", T);
        defparameter("*SKSI-SPARQL-REQUEST-WIDE-NEWLINES*", T);
        defparameter("*SKSI-HTTP-REQUEST-OPEN-CONNECTION-TIMEOUT*", ONE_INTEGER);
        defparameter("*SKSI-HTTP-REQUEST-OVERALL-TIMEOUT*", sksi_sks_interaction.$int$30);
        defparameter("*SKSI-HTTP-AUTOMATICALLY-REDIRECT*", T);
        defvar("*ORACLE-SPARQL-PROGRESS-STREAM*", NIL);
        defparameter("*SKSI-SPARQL-ITERATIVITY-LIMITS*", sksi_sks_interaction.$list70);
        defparameter("*SKSI-OPEN-SQL-CONNECTION-DEFAULT-TIMEOUT*", THREE_INTEGER);
        defparameter("*SKSI-OPEN-WEB-CONNECTION-DEFAULT-TIMEOUT*", TEN_INTEGER);
        defparameter("*SKSI-OPEN-SQL-SOURCE-DEFAULT-MAX-TRIES*", TEN_INTEGER);
        defparameter("*SKSI-OPEN-SQL-SOURCE-DEFAULT-INTERVAL*", sksi_sks_interaction.$float$0_1);
        deflexical("*SKSI-FHT-SOURCE-DEFAULT-SIZE*", $int$32);
        deflexical("*SKSI-FHT-SOURCE-DEFAULT-AVERAGE-BUCKET-SIZE*", $int$256);
        deflexical("*RESULT-SET-SQL-CONNECTION-STATEMENT-CACHE*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$result_set_sql_connection_statement_cache$, sksi_sks_interaction.$result_set_sql_connection_statement_cache$, () -> dictionary_utilities.new_synchronized_dictionary(symbol_function(EQ), UNPROVIDED)));
        deflexical("*CACHED-GET-RESULT-SET-FROM-SQL-SOURCE-CACHING-STATE*", NIL);
        defparameter("*SKSI-SPARQL-REQUEST-OPEN-CONNECTION-TIMEOUT*", FIVE_INTEGER);
        defparameter("*SKSI-SPARQL-REQUEST-OVERALL-TIMEOUT*", sksi_sks_interaction.$int$3600);
        defvar("*QUERY-INTERRUPT-ON-SPARQL-EXECUTION*", NIL);
        defvar("*SPARQL-EVALUATION-ENABLED?*", T);
        defvar("*SPARQL-RESULT-STREAM-ITERATOR-ENABLED?*", T);
        defconstant("*SPARQL-ORACLE-JOSEKI-SUBPROTOCOL*", sksi_sks_interaction.$str140$oracle_joseki);
        defconstant("*SPARQL-ORACLE-SESAME-SUBPROTOCOL*", sksi_sks_interaction.$str141$oracle_sesame);
        defconstant("*SPARQL-TRICLOPS-SUBPROTOCOL*", sksi_sks_interaction.$$$triclops);
        defparameter("*SPARQL-ORACLE-DEFAULT-SERVER-PARALLELISM*", FOUR_INTEGER);
        deflexical("*SPARQL-ORACLE-FS-PREFIX-PRAGMA-TEMPLATE*", sksi_sks_interaction.$str149$http___oracle_com_semtech_ordered);
        deflexical("*SPARQL-ORACLE-HT-PREFIX-PRAGMA-TEMPLATE*", sksi_sks_interaction.$str150$http___oracle_com_semtech_ALL_PUL);
        defparameter("*SPARQL-ORACLE-TIMEOUT*", NIL);
        deflexical("*CACHED-GET-RESULT-SET-FROM-SPARQL-SOURCE-CACHING-STATE*", NIL);
        deflexical("*ACCESS-PATH*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$access_path$, sksi_sks_interaction.$access_path$, NIL));
        deflexical("*STRUCTURED-KNOWLEDGE-SOURCES-ASSUMED-AVAILABLE*", NIL);
        deflexical("*SKSI-SQL-STATEMENT-POOL-MAX-SIZE*", TEN_INTEGER);
        deflexical("*SKSI-RESOURCING-CACHE*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$sksi_resourcing_cache$, sksi_sks_interaction.$sksi_resourcing_cache$, NIL));
        defparameter("*SKSI-GLOBAL-RESOURCING?*", T);
        deflexical("*SKSI-DEFAULT-CONNECTION-MAX-IDLE-SECONDS*", sksi_sks_interaction.$int$600);
        defconstant("*DTP-SKSI-RESOURCING-CACHE*", sksi_sks_interaction.SKSI_RESOURCING_CACHE);
        deflexical("*SKSI-CONNECTION-GLOBAL-CACHE-LOCK*", SubLTrampolineFile.maybeDefault(sksi_sks_interaction.$sksi_connection_global_cache_lock$, sksi_sks_interaction.$sksi_connection_global_cache_lock$, () -> make_lock(Strings.string(gensym(sksi_sks_interaction.$str254$SKSI_SQL_global_connection_cache_)))));
        defparameter("*SKSI-SALIENT-QUERY-STRING*", NIL);
        return NIL;
    }

    static private final SubLString $str_alt164$http___www_clevelandclinic_org_he = makeString("http://www.clevelandclinic.org/heartcenter/ontologies/DataNodes.owl#");

    static private final SubLList $list_alt167 = list(makeString("CONTEXT"));

    static private final SubLString $str_alt168$_sparql = makeString("<sparql");

    static private final SubLString $$$baxter = makeString("baxter");

    static private final SubLList $list_alt178 = list(list(list(list(new SubLObject[]{ makeString("HTTP/1.0 200 OK\n\nServer: SimpleHTTP/0.6 Python/2.4.2\n\nDate: Fri, 23 Jan 2009 20:55:16 GMT\n\nContent-type: application/xml\n\nContent-Length: 428\n\n\n\n"), makeString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"), makeString("\n"), makeString("<?xml-stylesheet type='text/xml' href='/xslt/xml-to-html.xslt'?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">"), makeString("\n  "), makeString("<head>"), makeString("\n    "), makeString("<variable name=\"CONTEXT\"/>"), makeString("\n  "), makeString("</head>"), makeString("\n  "), makeString("<results distinct=\"false\" ordered=\"false\">"), makeString("\n    "), makeString("<result>"), makeString("\n      "), makeString("<binding name=\"CONTEXT\">"), makeString("\n        "), makeString("<uri>"), makeString("ahttp://semanticdb.ccf.org/SemanticDB/CVIR/39932466.xml"), makeString("</uri>"), makeString("\n      "), makeString("</binding>"), makeString("\n    "), makeString("</result>"), makeString("\n  "), makeString("</results>"), makeString("\n"), makeString("</sparql>"), makeString("\n") }), list(makeString("CONTEXT")), list(new SubLObject[]{ cons(makeString("csqr"), makeString("tag:info@semanticdb.ccf.org,2008:CardiacSurgeryQualityReport#")), cons(makeString("cyc"), makeString("http://sw.cyc.com/2006/07/27/cyc#")), cons(makeString("dnode"), makeString("http://www.clevelandclinic.org/heartcenter/ontologies/DataNodes.owl#")), cons(makeString("owl"), makeString("http://www.w3.org/2000/07/owl#")), cons(makeString("ptrec"), makeString("tag:info@semanticdb.ccf.org,2007:PatientRecordTerms#")), cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")), cons(makeString("sts"), makeString("tag:info@semanticdb.ccf.org,2008:STS.2.61#")), cons(makeString("xsd"), makeString("http://www.w3.org/2001/XMLSchema#")) })), makeString("<ahttp://semanticdb.ccf.org/SemanticDB/CVIR/39932466.xml>")));

    public static final SubLObject $list_alt180 = com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction._constant_180_initializer();

    static private final SubLString $str_alt181$File_hash_table_does_not_exist_ = makeString("File hash table does not exist.");

    static private final SubLString $str_alt182$The_following_problem_was_encount = makeString("The following problem was encountered while trying to create the knowledge source ~A :~%");

    public static final SubLObject setup_sksi_sks_interaction_file_alt() {
        memoization_state.note_memoized_function(GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED);
        memoization_state.note_globally_cached_function(GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_CACHED);
        memoization_state.note_memoized_function(SKSI_TOKENIZED_HTTP_REQUEST);
        register_external_symbol(SKSI_EXECUTE_SQL_SCRIPT_FROM_FILE);
        register_external_symbol(SKSI_EXECUTE_SQL_SCRIPT);
        declare_defglobal($result_set_sql_connection_statement_cache$);
        memoization_state.note_globally_cached_function(CACHED_GET_RESULT_SET_FROM_SPARQL_SOURCE);
        declare_defglobal($access_path$);
        define_test_case_table_int(TEST_SKSI_IST_GRAPH, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, $$$baxter, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list_alt178);
        define_test_case_table_int(SKSI_SPARQL_XML_TOKENS_TO_RESULT_SET, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, $$$baxter, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list_alt180);
        declare_defglobal($sksi_resourcing_cache$);
        register_method($print_object_method_table$.getGlobalValue(), $dtp_sksi_resourcing_cache$.getGlobalValue(), symbol_function(SKSI_RESOURCING_CACHE_PRINT_FUNCTION_TRAMPOLINE));
        def_csetf(SKSI_RESOURCING_CACHE_CONNECTIONS, _CSETF_SKSI_RESOURCING_CACHE_CONNECTIONS);
        def_csetf(SKSI_RESOURCING_CACHE_STATEMENTS, _CSETF_SKSI_RESOURCING_CACHE_STATEMENTS);
        def_csetf(SKSI_RESOURCING_CACHE_USED_STATEMENTS, _CSETF_SKSI_RESOURCING_CACHE_USED_STATEMENTS);
        def_csetf(SKSI_RESOURCING_CACHE_ACCESS_TIMES, _CSETF_SKSI_RESOURCING_CACHE_ACCESS_TIMES);
        def_csetf(SKSI_RESOURCING_CACHE_LOCK, _CSETF_SKSI_RESOURCING_CACHE_LOCK);
        def_csetf(SKSI_RESOURCING_CACHE_MAX_POOL_SIZE, _CSETF_SKSI_RESOURCING_CACHE_MAX_POOL_SIZE);
        def_csetf(SKSI_RESOURCING_CACHE_MAX_IDLE, _CSETF_SKSI_RESOURCING_CACHE_MAX_IDLE);
        identity(SKSI_RESOURCING_CACHE);
        return NIL;
    }

    public static SubLObject setup_sksi_sks_interaction_file() {
        if (SubLFiles.USE_V1) {
            note_memoized_function(sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED);
            note_globally_cached_function(sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_CACHED);
            note_memoized_function(sksi_sks_interaction.SKSI_TOKENIZED_HTTP_REQUEST);
            register_external_symbol(sksi_sks_interaction.SKSI_EXECUTE_SQL_SCRIPT_FROM_FILE);
            register_external_symbol(sksi_sks_interaction.SKSI_EXECUTE_SQL_SCRIPT);
            declare_defglobal(sksi_sks_interaction.$result_set_sql_connection_statement_cache$);
            note_globally_cached_function(sksi_sks_interaction.CACHED_GET_RESULT_SET_FROM_SQL_SOURCE);
            note_globally_cached_function(sksi_sks_interaction.CACHED_GET_RESULT_SET_FROM_SPARQL_SOURCE);
            declare_defglobal(sksi_sks_interaction.$access_path$);
            define_test_case_table_int(sksi_sks_interaction.TEST_SKSI_IST_GRAPH, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), sksi_sks_interaction.$list199);
            define_test_case_table_int(sksi_sks_interaction.SKSI_SPARQL_XML_TOKENS_TO_RESULT_SET, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), sksi_sks_interaction.$list201);
            declare_defglobal(sksi_sks_interaction.$sksi_resourcing_cache$);
            register_method($print_object_method_table$.getGlobalValue(), sksi_sks_interaction.$dtp_sksi_resourcing_cache$.getGlobalValue(), symbol_function(sksi_sks_interaction.SKSI_RESOURCING_CACHE_PRINT_FUNCTION_TRAMPOLINE));
            SubLSpecialOperatorDeclarations.proclaim(sksi_sks_interaction.$list222);
            def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_CONNECTIONS, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_CONNECTIONS);
            def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_STATEMENTS, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_STATEMENTS);
            def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_USED_STATEMENTS, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_USED_STATEMENTS);
            def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_ACCESS_TIMES, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_ACCESS_TIMES);
            def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_LOCK, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_LOCK);
            def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_MAX_POOL_SIZE, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_MAX_POOL_SIZE);
            def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_MAX_IDLE, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_MAX_IDLE);
            identity(sksi_sks_interaction.SKSI_RESOURCING_CACHE);
            register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), sksi_sks_interaction.$dtp_sksi_resourcing_cache$.getGlobalValue(), symbol_function(sksi_sks_interaction.VISIT_DEFSTRUCT_OBJECT_SKSI_RESOURCING_CACHE_METHOD));
            declare_defglobal(sksi_sks_interaction.$sksi_connection_global_cache_lock$);
            note_memoized_function(sksi_sks_interaction.MEMOIZED_SALIENT_SKSI_QUERY_STRING);
            define_test_case_table_int(sksi_sks_interaction.SPARQL_EXTRACT_VARIABLE_NAMES, list(new SubLObject[]{ $TEST, EQUAL, $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), sksi_sks_interaction.$list270);
        }
        if (SubLFiles.USE_V2) {
            define_test_case_table_int(TEST_SKSI_IST_GRAPH, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, $$$baxter, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list_alt178);
            define_test_case_table_int(SKSI_SPARQL_XML_TOKENS_TO_RESULT_SET, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, $$$baxter, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), $list_alt180);
        }
        return NIL;
    }

    public static SubLObject setup_sksi_sks_interaction_file_Previous() {
        note_memoized_function(sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_MEMOIZED);
        note_globally_cached_function(sksi_sks_interaction.GET_RESULTS_FROM_SKSI_WEB_PAGE_SUBL_METHOD_CACHED);
        note_memoized_function(sksi_sks_interaction.SKSI_TOKENIZED_HTTP_REQUEST);
        register_external_symbol(sksi_sks_interaction.SKSI_EXECUTE_SQL_SCRIPT_FROM_FILE);
        register_external_symbol(sksi_sks_interaction.SKSI_EXECUTE_SQL_SCRIPT);
        declare_defglobal(sksi_sks_interaction.$result_set_sql_connection_statement_cache$);
        note_globally_cached_function(sksi_sks_interaction.CACHED_GET_RESULT_SET_FROM_SQL_SOURCE);
        note_globally_cached_function(sksi_sks_interaction.CACHED_GET_RESULT_SET_FROM_SPARQL_SOURCE);
        declare_defglobal(sksi_sks_interaction.$access_path$);
        define_test_case_table_int(sksi_sks_interaction.TEST_SKSI_IST_GRAPH, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), sksi_sks_interaction.$list199);
        define_test_case_table_int(sksi_sks_interaction.SKSI_SPARQL_XML_TOKENS_TO_RESULT_SET, list(new SubLObject[]{ $TEST, symbol_function(EQUAL), $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), sksi_sks_interaction.$list201);
        declare_defglobal(sksi_sks_interaction.$sksi_resourcing_cache$);
        register_method($print_object_method_table$.getGlobalValue(), sksi_sks_interaction.$dtp_sksi_resourcing_cache$.getGlobalValue(), symbol_function(sksi_sks_interaction.SKSI_RESOURCING_CACHE_PRINT_FUNCTION_TRAMPOLINE));
        SubLSpecialOperatorDeclarations.proclaim(sksi_sks_interaction.$list222);
        def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_CONNECTIONS, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_CONNECTIONS);
        def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_STATEMENTS, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_STATEMENTS);
        def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_USED_STATEMENTS, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_USED_STATEMENTS);
        def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_ACCESS_TIMES, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_ACCESS_TIMES);
        def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_LOCK, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_LOCK);
        def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_MAX_POOL_SIZE, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_MAX_POOL_SIZE);
        def_csetf(sksi_sks_interaction.SKSI_RESOURCING_CACHE_MAX_IDLE, sksi_sks_interaction._CSETF_SKSI_RESOURCING_CACHE_MAX_IDLE);
        identity(sksi_sks_interaction.SKSI_RESOURCING_CACHE);
        register_method(visitation.$visit_defstruct_object_method_table$.getGlobalValue(), sksi_sks_interaction.$dtp_sksi_resourcing_cache$.getGlobalValue(), symbol_function(sksi_sks_interaction.VISIT_DEFSTRUCT_OBJECT_SKSI_RESOURCING_CACHE_METHOD));
        declare_defglobal(sksi_sks_interaction.$sksi_connection_global_cache_lock$);
        note_memoized_function(sksi_sks_interaction.MEMOIZED_SALIENT_SKSI_QUERY_STRING);
        define_test_case_table_int(sksi_sks_interaction.SPARQL_EXTRACT_VARIABLE_NAMES, list(new SubLObject[]{ $TEST, EQUAL, $OWNER, NIL, $CLASSES, NIL, $KB, $TINY, $WORKING_, T }), sksi_sks_interaction.$list270);
        return NIL;
    }

    static private final SubLString $str_alt183$Continue_without_creating_ = makeString("Continue without creating ");

    static private final SubLString $str_alt184$_ = makeString(".");

    static private final SubLString $str_alt185$__The_following_problem_was_encou = makeString("~%The following problem was encountered while trying to execute the following statement in the knowledge source ~A :~%~A~%");

    static private final SubLString $str_alt186$__The_following_problem_was_encou = makeString("~%The following problem was encountered while trying to access the knowledge source ~A :~%");

    static private final SubLString $str_alt187$Continue_without_accessing_ = makeString("Continue without accessing ");

    static private final SubLList $list_alt193 = list(makeSymbol("CONNECTIONS"), makeSymbol("STATEMENTS"), makeSymbol("USED-STATEMENTS"), makeSymbol("ACCESS-TIMES"), makeSymbol("LOCK"), makeSymbol("MAX-POOL-SIZE"), makeSymbol("MAX-IDLE"));

    static private final SubLList $list_alt194 = list(makeKeyword("CONNECTIONS"), makeKeyword("STATEMENTS"), makeKeyword("USED-STATEMENTS"), makeKeyword("ACCESS-TIMES"), $LOCK, makeKeyword("MAX-POOL-SIZE"), makeKeyword("MAX-IDLE"));

    static private final SubLList $list_alt195 = list(makeSymbol("SKSI-RESOURCING-CACHE-CONNECTIONS"), makeSymbol("SKSI-RESOURCING-CACHE-STATEMENTS"), makeSymbol("SKSI-RESOURCING-CACHE-USED-STATEMENTS"), makeSymbol("SKSI-RESOURCING-CACHE-ACCESS-TIMES"), makeSymbol("SKSI-RESOURCING-CACHE-LOCK"), makeSymbol("SKSI-RESOURCING-CACHE-MAX-POOL-SIZE"), makeSymbol("SKSI-RESOURCING-CACHE-MAX-IDLE"));

    static private final SubLList $list_alt196 = list(makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-CONNECTIONS"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-STATEMENTS"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-USED-STATEMENTS"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-ACCESS-TIMES"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-LOCK"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-MAX-POOL-SIZE"), makeSymbol("_CSETF-SKSI-RESOURCING-CACHE-MAX-IDLE"));

    private static SubLObject _constant_201_initializer() {
        return list(new SubLObject[]{ list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:05 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 393\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal>"), makeString("1885"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("1885")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:08 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 313\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), NIL), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:09 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">"), makeString("78.04222106933594"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("78.04222106933594")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:23 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">"), makeString("27.17416572570801"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("27.17416572570801")))), list(list(NIL, list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), NIL), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:32 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 448\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#date\">"), makeString("1998-07-21"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("1998-07-21")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:37 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 418\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Edinburgh"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Edinburgh>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:38 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:38 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:39 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 521\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Brooklyn"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/New_York"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Brooklyn>")), list(makeString("<http://dbpedia.org/resource/New_York>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:39 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 518\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/England"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/London"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/England>")), list(makeString("<http://dbpedia.org/resource/London>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:40 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 741\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Acid_rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Blues-rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Hard_rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Psychedelic_rock"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Acid_rock>")), list(makeString("<http://dbpedia.org/resource/Blues-rock>")), list(makeString("<http://dbpedia.org/resource/Hard_rock>")), list(makeString("<http://dbpedia.org/resource/Psychedelic_rock>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:41 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:41 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 422\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<uri>"), makeString("http://dbpedia.org/resource/Truman_Capote"), makeString("</uri>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("<http://dbpedia.org/resource/Truman_Capote>")))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:50:43 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 456\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"_star_fake\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"_star_fake\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">"), makeString("1"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("*")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(NIL))), list(list(list(new SubLObject[]{ makeString("HTTP/1.1 200 OK\n\nServer: Virtuoso/05.09.3035 (Solaris) x86_64-sun-solaris2.10-64  VDB\n\nConnection: close\n\nDate: Fri, 23 Jan 2009 18:51:08 GMT\n\nAccept-Ranges: bytes\n\nContent-Type: application/sparql-results+xml; charset=UTF-8\n\nContent-Length: 448\n\n\n\n"), makeString("<?xml version=\"1.0\" ?>"), makeString("\n"), makeString("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2001/sw/DataAccess/rf1/result2.xsd\">"), makeString("\n "), makeString("<head>"), makeString("\n  "), makeString("<variable name=\"obj\"/>"), makeString("\n "), makeString("</head>"), makeString("\n "), makeString("<results distinct=\"false\" ordered=\"true\">"), makeString("\n  "), makeString("<result>"), makeString("\n   "), makeString("<binding name=\"obj\">"), makeString("<literal datatype=\"http://www.w3.org/2001/XMLSchema#date\">"), makeString("1923-11-18"), makeString("</literal>"), makeString("</binding>"), makeString("\n  "), makeString("</result>"), makeString("\n "), makeString("</results>"), makeString("\n"), makeString("</sparql>") }), list(makeString("obj")), list(cons(makeString("rdf"), makeString("http://www.w3.org/1999/02/22-rdf-syntax-ns#")), cons(makeString("rdfs"), makeString("http://www.w3.org/2000/01/rdf-schema#")))), list(list(makeString("1923-11-18")))) });
    }

    static private final SubLString $str_alt220$Invalid_slot__S_for_construction_ = makeString("Invalid slot ~S for construction function");

    static private final SubLString $str_alt221$SKSI_SQL_resourcing_cache_ = makeString("SKSI SQL resourcing cache ");

    static private final SubLList $list_alt222 = list(makeSymbol("CACHE"), makeSymbol("&BODY"), makeSymbol("BODY"));

    @Override
    public void declareFunctions() {
        sksi_sks_interaction.declare_sksi_sks_interaction_file();
    }

    @Override
    public void initializeVariables() {
        sksi_sks_interaction.init_sksi_sks_interaction_file();
    }

    @Override
    public void runTopLevelForms() {
        sksi_sks_interaction.setup_sksi_sks_interaction_file();
    }

    static {
    }

    public static final class $sksi_resourcing_cache_native extends SubLStructNative {
        public SubLObject $connections;

        public SubLObject $statements;

        public SubLObject $used_statements;

        public SubLObject $access_times;

        public SubLObject $lock;

        public SubLObject $max_pool_size;

        public SubLObject $max_idle;

        private static final SubLStructDeclNative structDecl;

        public $sksi_resourcing_cache_native() {
            this.$connections = Lisp.NIL;
            this.$statements = Lisp.NIL;
            this.$used_statements = Lisp.NIL;
            this.$access_times = Lisp.NIL;
            this.$lock = Lisp.NIL;
            this.$max_pool_size = Lisp.NIL;
            this.$max_idle = Lisp.NIL;
        }

        @Override
        public SubLStructDecl getStructDecl() {
            return structDecl;
        }

        @Override
        public SubLObject getField2() {
            return this.$connections;
        }

        @Override
        public SubLObject getField3() {
            return this.$statements;
        }

        @Override
        public SubLObject getField4() {
            return this.$used_statements;
        }

        @Override
        public SubLObject getField5() {
            return this.$access_times;
        }

        @Override
        public SubLObject getField6() {
            return this.$lock;
        }

        @Override
        public SubLObject getField7() {
            return this.$max_pool_size;
        }

        @Override
        public SubLObject getField8() {
            return this.$max_idle;
        }

        @Override
        public SubLObject setField2(final SubLObject value) {
            return this.$connections = value;
        }

        @Override
        public SubLObject setField3(final SubLObject value) {
            return this.$statements = value;
        }

        @Override
        public SubLObject setField4(final SubLObject value) {
            return this.$used_statements = value;
        }

        @Override
        public SubLObject setField5(final SubLObject value) {
            return this.$access_times = value;
        }

        @Override
        public SubLObject setField6(final SubLObject value) {
            return this.$lock = value;
        }

        @Override
        public SubLObject setField7(final SubLObject value) {
            return this.$max_pool_size = value;
        }

        @Override
        public SubLObject setField8(final SubLObject value) {
            return this.$max_idle = value;
        }

        static {
            structDecl = makeStructDeclNative(com.cyc.cycjava.cycl.sksi.sksi_infrastructure.sksi_sks_interaction.$sksi_resourcing_cache_native.class, sksi_sks_interaction.SKSI_RESOURCING_CACHE, sksi_sks_interaction.SKSI_RESOURCING_CACHE_P, sksi_sks_interaction.$list216, sksi_sks_interaction.$list217, new String[]{ "$connections", "$statements", "$used_statements", "$access_times", "$lock", "$max_pool_size", "$max_idle" }, sksi_sks_interaction.$list218, sksi_sks_interaction.$list219, DEFAULT_STRUCT_PRINT_FUNCTION);
        }
    }

    public static final class $sksi_resourcing_cache_p$UnaryFunction extends UnaryFunction {
        public $sksi_resourcing_cache_p$UnaryFunction() {
            super(extractFunctionNamed("SKSI-RESOURCING-CACHE-P"));
        }

        @Override
        public SubLObject processItem(final SubLObject arg1) {
            return sksi_sks_interaction.sksi_resourcing_cache_p(arg1);
        }
    }
}

/**
 * Total time: 1485 ms
 */
